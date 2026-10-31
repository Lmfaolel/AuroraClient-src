#version 150

// Smoke hand shader, ported from the hand-modules pack.

uniform sampler2D ColorTexture;
uniform sampler2D DepthTexture;

// P0 = (time, resolution.x, resolution.y, smokeIntensity); P1.rgb = smokeColor.
layout(std140) uniform Params {
    vec4 P[8];
};

in vec2 texCoord;
out vec4 fragColor;

void main() {
    float time = P[0].x;
    vec2 resolution = P[0].yz;
    float smokeIntensity = P[0].w;
    vec3 smokeColor = P[1].rgb;

    vec4 originalColor = texture(ColorTexture, texCoord);
    vec2 texelSize = 1.0 / vec2(textureSize(DepthTexture, 0));

    float minDepth = texture(DepthTexture, texCoord).r;
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(-texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, -texelSize.y)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, texelSize.y)).r);

    float mask = smoothstep(0.99, 0.98, minDepth);
    if (mask < 0.01) discard;

    vec2 smokeUv = (2.0 * texCoord - 1.0) * resolution.y / min(resolution.x, resolution.y);
    for (float i = 1.0; i < 7.0; i++) {
        smokeUv.x += 0.6 / i * cos(i * 2.5 * smokeUv.y + time);
        smokeUv.y += 0.6 / i * cos(i * 1.5 * smokeUv.x + time);
    }
    float smokePattern = 0.1 / abs(sin(time - smokeUv.y - smokeUv.x));
    vec3 col = smokeColor * smokePattern * smokeIntensity;

    vec4 finalHandColor = mix(originalColor, vec4(col, 1.0), 0.85);
    fragColor = vec4(finalHandColor.rgb, mask);
}
