#version 150

// Chroma hand shader, ported from the hand-modules pack. A rainbow that only
// paints where the first person hand is, found by its near depth. The pass
// samples a copy of the framebuffer (ColorTexture) and the live depth
// (DepthTexture), and composites over the scene with translucent blend.

uniform sampler2D ColorTexture;
uniform sampler2D DepthTexture;

// P0 = (time, chromaSpeed, chromaSaturation, chromaBrightness).
layout(std140) uniform Params {
    vec4 P[8];
};

in vec2 texCoord;
out vec4 fragColor;

vec3 hsv2rgb(vec3 c) {
    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

void main() {
    float time = P[0].x;
    float chromaSpeed = P[0].y;
    float chromaSaturation = P[0].z;
    float chromaBrightness = P[0].w;

    vec4 originalColor = texture(ColorTexture, texCoord);
    vec2 texelSize = 1.0 / vec2(textureSize(DepthTexture, 0));

    // The hand sits at very near depth, so a cross of samples below a threshold
    // is the mask. Everything farther is discarded, leaving the scene untouched.
    float minDepth = texture(DepthTexture, texCoord).r;
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(-texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, -texelSize.y)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, texelSize.y)).r);

    float mask = smoothstep(0.99, 0.98, minDepth);
    if (mask < 0.01) discard;

    float hue = fract(texCoord.y * 0.5 + texCoord.x * 0.3 + time * chromaSpeed);
    hue += sin(texCoord.y * 10.0 + time * 3.0) * 0.05;
    vec3 chromaColor = hsv2rgb(vec3(hue, chromaSaturation, chromaBrightness));

    float pulse = 0.9 + sin(time * 4.0) * 0.1;
    chromaColor *= pulse;

    vec4 finalHandColor = mix(originalColor, vec4(chromaColor, 1.0), 0.85);
    fragColor = vec4(finalHandColor.rgb, mask);
}
