#version 150

// Glow hand shader, ported from the hand-modules pack. Edge-detects the hand
// against the cleared background depth and haloes it, or fills it.

uniform sampler2D ColorTexture;
uniform sampler2D DepthTexture;

// P0 = (time, res.x, res.y, glowRadius); P1.rgb = glowColor;
// P2 = (glowPower, glowDispersion, fillGlow, 0).
layout(std140) uniform Params {
    vec4 P[8];
};

in vec2 texCoord;
out vec4 fragColor;

void main() {
    float time = P[0].x;
    float glowRadius = P[0].w;
    vec3 glowColor = P[1].rgb;
    float glowPower = P[2].x;
    float glowDispersion = P[2].y;
    bool fillGlow = P[2].z > 0.5;

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

    float edgeStrength = 0.0;
    float outwardGlow = 0.0;
    int disperseRadius = min(int(glowDispersion), 5);

    for (int ring = 1; ring <= disperseRadius; ring++) {
        float angle = 0.0;
        for (int i = 0; i < 8; i++) {
            vec2 offset = vec2(cos(angle), sin(angle)) * float(ring) * texelSize;
            float neighborDepth = texture(DepthTexture, texCoord + offset).r;
            float dist = float(ring);
            if (neighborDepth > 0.99 && centerDepth < 0.99) {
                edgeStrength += (1.0 / (dist + 0.1));
            }
            if (centerDepth > 0.99 && neighborDepth < 0.99) {
                float falloff = pow(1.0 - (dist / float(disperseRadius)), 2.5);
                outwardGlow += falloff * 0.2;
            }
            angle += 0.785398;
        }
    }

    edgeStrength = clamp(edgeStrength * 0.15, 0.0, 1.0);
    outwardGlow = clamp(outwardGlow, 0.0, 1.0);
    float pulse = 0.9 + sin(time * 2.0) * 0.1;

    if (centerDepth > 0.99 && outwardGlow > 0.01) {
        vec3 dispersedGlow = glowColor * outwardGlow * glowPower * pulse * glowRadius * 10.0;
        fragColor = vec4(dispersedGlow, outwardGlow * 0.8);
        return;
    }

    vec3 finalColor;
    if (fillGlow) {
        vec3 handGlow = glowColor * (glowRadius * 10.0);
        vec3 edgeGlow = glowColor * edgeStrength * glowPower * pulse * 2.5;
        finalColor = mix(originalColor.rgb, handGlow + edgeGlow, 0.75);
    } else {
        vec3 edgeGlow = glowColor * edgeStrength * glowPower * pulse * glowRadius * 25.0;
        finalColor = originalColor.rgb + edgeGlow;
    }
    fragColor = vec4(finalColor, mask);
}
