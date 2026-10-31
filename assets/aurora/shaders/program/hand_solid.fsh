#version 150

// Solid hand shader, ported from the hand-modules pack. A vertical gradient
// between two colors that pulses over time, masked to the first person hand by
// its near depth. Same pipeline as the chroma pass.

uniform sampler2D ColorTexture;
uniform sampler2D DepthTexture;

// P0 = (time, effectAlpha, 0, 0); P1.rgb = color 1; P2.rgb = color 2.
layout(std140) uniform Params {
    vec4 P[8];
};

in vec2 texCoord;
out vec4 fragColor;

void main() {
    float time = P[0].x;
    float effectAlpha = P[0].y;
    vec3 color1 = P[1].rgb;
    vec3 color2 = P[2].rgb;

    vec4 originalColor = texture(ColorTexture, texCoord);
    vec2 texelSize = 1.0 / vec2(textureSize(DepthTexture, 0));

    float minDepth = texture(DepthTexture, texCoord).r;
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(-texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, -texelSize.y)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, texelSize.y)).r);

    float mask = smoothstep(0.99, 0.98, minDepth);
    if (mask < 0.01) discard;

    float factor = sin((texCoord.y + time) * 3.14159 * 2.0) * 0.5 + 0.5;
    vec3 gradientColor = mix(color1, color2, factor);
    gradientColor *= 0.85 + sin(time * 6.28318) * 0.15;

    vec4 finalHandColor = mix(originalColor, vec4(gradientColor, 1.0), effectAlpha);
    fragColor = vec4(finalHandColor.rgb, mask);
}
