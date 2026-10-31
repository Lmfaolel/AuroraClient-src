#version 150

// Invert hand shader, ported from the hand-modules pack. Inverts the hand (or
// the background behind it when hideHand is set), in RGB or per HSV channel,
// with an optional edge glow.

uniform sampler2D ColorTexture;
uniform sampler2D DepthTexture;

// P0 = (time, res.x, res.y, invertIntensity); P1.rgb = glowColor;
// P2 = (hideHand, invertHue, invertSaturation, invertBrightness); P3.x = edgeGlow.
layout(std140) uniform Params {
    vec4 P[8];
};

in vec2 texCoord;
out vec4 fragColor;

vec3 rgb2hsv(vec3 c) {
    vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));
    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
}

vec3 hsv2rgb(vec3 c) {
    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

vec3 applyInvert(vec3 col, float intensity, bool hue, bool sat, bool bright) {
    if (hue || sat || bright) {
        vec3 hsv = rgb2hsv(col);
        if (hue) hsv.x = fract(hsv.x + 0.5);
        if (sat) hsv.y = 1.0 - hsv.y;
        if (bright) hsv.z = 1.0 - hsv.z;
        return mix(col, hsv2rgb(hsv), intensity);
    }
    return mix(col, vec3(1.0) - col, intensity);
}

void main() {
    float time = P[0].x;
    float invertIntensity = P[0].w;
    vec3 glowColor = P[1].rgb;
    bool invertHue = P[2].y > 0.5;
    bool invertSaturation = P[2].z > 0.5;
    bool invertBrightness = P[2].w > 0.5;
    float edgeGlow = P[3].x;

    vec4 originalColor = texture(ColorTexture, texCoord);
    vec2 texelSize = 1.0 / vec2(textureSize(DepthTexture, 0));

    float centerDepth = texture(DepthTexture, texCoord).r;
    float minDepth = centerDepth;
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(-texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, -texelSize.y)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, texelSize.y)).r);

    float mask = smoothstep(0.99, 0.98, minDepth);
    if (mask < 0.01) discard;

    vec3 finalColor;
    if (centerDepth < 0.99) {
        finalColor = applyInvert(originalColor.rgb, invertIntensity, invertHue, invertSaturation, invertBrightness);
    } else {
        finalColor = originalColor.rgb;
    }

    if (edgeGlow > 0.01 && centerDepth < 0.99) {
        float edgeStrength = 0.0;
        if (texture(DepthTexture, texCoord + vec2(texelSize.x, 0.0)).r > 0.99) edgeStrength += 1.0;
        if (texture(DepthTexture, texCoord + vec2(-texelSize.x, 0.0)).r > 0.99) edgeStrength += 1.0;
        if (texture(DepthTexture, texCoord + vec2(0.0, texelSize.y)).r > 0.99) edgeStrength += 1.0;
        if (texture(DepthTexture, texCoord + vec2(0.0, -texelSize.y)).r > 0.99) edgeStrength += 1.0;
        edgeStrength = clamp(edgeStrength * 0.25, 0.0, 1.0);
        float pulse = 0.8 + sin(time * 3.0) * 0.2;
        finalColor += glowColor * edgeStrength * edgeGlow * pulse;
    }

    fragColor = vec4(finalColor, mask);
}
