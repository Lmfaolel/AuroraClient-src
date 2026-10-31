#version 150

// Glass hand shader, ported from the hand-modules pack. A liquid-glass blur
// over the hand, with optional chromatic aberration, distortion, and a
// frosted-background mode.
//
// The original hid the hand from the color buffer (colorMask) for its hideHand
// mode. This port has no such mask, so hideHand blurs the hand-inclusive color
// rather than a hand-free background. Everything else matches. The player and
// crystal passes do get a subject-free background: with hideHand on they bind a
// copy taken before entities drew as ColorTexture.

uniform sampler2D ColorTexture;
uniform sampler2D DepthTexture;

// P0 = (time, res.x, res.y, blurSize); P1 = (quality, direction, refraction, brightness);
// P2 = (chromatic, distortion, hideHand, backgroundBlur).
layout(std140) uniform Params {
    vec4 P[8];
};

in vec2 texCoord;
out vec4 fragColor;

const float PI = 3.14159265;

vec2 gResolution;

vec4 liquidGlassBlur(sampler2D tex, vec2 uv, float dir, float qual, float size) {
    vec2 radius = (size / gResolution.y) / gResolution;
    vec4 color = texture(tex, uv);
    float total = 1.0;
    float optQual = min(qual, 6.0);
    float optDir = min(dir, 8.0);
    float angleStep = PI / optDir;
    float radiusStep = 1.0 / optQual;
    for (float d = 0.0; d < PI; d += angleStep) {
        vec2 direction = vec2(cos(d), sin(d));
        for (float i = radiusStep; i <= 1.0; i += radiusStep) {
            color += texture(tex, uv + direction * radius * i);
            total += 1.0;
        }
    }
    return color / total;
}

vec4 matteBlur(sampler2D tex, vec2 uv, float blurRadius) {
    const float TAU = 6.28318530718;
    vec2 radius = blurRadius / gResolution.xy;
    vec4 blur = texture(tex, uv);
    float step = TAU / 8.0;
    for (float d = 0.0; d < TAU; d += step) {
        vec2 dir = vec2(cos(d), sin(d));
        for (float i = 0.33; i <= 1.0; i += 0.33) {
            blur += texture(tex, uv + dir * radius * i);
        }
    }
    return blur / 25.0;
}

vec2 glassDistortion(vec2 uv, float time, float strength) {
    float wave1 = sin(uv.y * 10.0 + time * 2.0) * 0.01 * strength;
    float wave2 = cos(uv.x * 8.0 - time * 1.5) * 0.01 * strength;
    vec2 toCenter = uv - vec2(0.5);
    float dist = length(toCenter);
    vec2 radial = toCenter * sin(dist * 15.0 - time * 3.0) * 0.005 * strength;
    return vec2(wave1 + radial.x, wave2 + radial.y);
}

void main() {
    float time = P[0].x;
    gResolution = P[0].yz;
    float blurSize = P[0].w;
    float quality = P[1].x, direction = P[1].y, refraction = P[1].z, brightness = P[1].w;
    bool enableChromatic = P[2].x > 0.5;
    bool enableDistortion = P[2].y > 0.5;
    bool hideHand = P[2].z > 0.5;
    float backgroundBlur = P[2].w;

    vec2 texelSize = 1.0 / vec2(textureSize(DepthTexture, 0));
    float centerDepth = texture(DepthTexture, texCoord).r;
    float minDepth = centerDepth;
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(-texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, -texelSize.y)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, texelSize.y)).r);

    float mask = smoothstep(0.99, 0.98, minDepth);
    if (mask < 0.01) discard;

    vec2 distortedUV = texCoord;
    if (enableDistortion) distortedUV += glassDistortion(texCoord, time, refraction);
    vec4 handColor = texture(ColorTexture, distortedUV);

    vec3 glassColor;
    if (centerDepth < 0.99) {
        if (hideHand) {
            glassColor = (backgroundBlur > 0.1) ? matteBlur(ColorTexture, texCoord, backgroundBlur).rgb
                                                : texture(ColorTexture, texCoord).rgb;
            glassColor *= brightness;
        } else {
            glassColor = liquidGlassBlur(ColorTexture, distortedUV, direction, quality, blurSize).rgb;
            if (enableChromatic) {
                float aberration = 0.003 * refraction;
                glassColor.r = liquidGlassBlur(ColorTexture, distortedUV + vec2(aberration, 0.0), direction, quality, blurSize * 0.5).r;
                glassColor.b = liquidGlassBlur(ColorTexture, distortedUV - vec2(aberration, 0.0), direction, quality, blurSize * 0.5).b;
            }
            glassColor *= brightness;
        }
    } else {
        glassColor = handColor.rgb;
    }

    float edgeStrength = 0.0;
    if (centerDepth < 0.99) {
        if (texture(DepthTexture, texCoord + vec2(texelSize.x, 0.0)).r > 0.99) edgeStrength += 1.0;
        if (texture(DepthTexture, texCoord + vec2(-texelSize.x, 0.0)).r > 0.99) edgeStrength += 1.0;
        if (texture(DepthTexture, texCoord + vec2(0.0, texelSize.y)).r > 0.99) edgeStrength += 1.0;
        if (texture(DepthTexture, texCoord + vec2(0.0, -texelSize.y)).r > 0.99) edgeStrength += 1.0;
        edgeStrength = clamp(edgeStrength * 0.15, 0.0, 1.0);
    }
    glassColor += vec3(1.0) * edgeStrength * 0.3;

    float reflection = (sin(time * 2.0 + texCoord.x * 20.0) * 0.5 + 0.5) * (sin(time * 1.5 + texCoord.y * 15.0) * 0.5 + 0.5);
    glassColor += vec3(reflection * 0.1);

    vec3 finalColor = mix(handColor.rgb, glassColor, 0.7);
    fragColor = vec4(finalColor, mask);
}
