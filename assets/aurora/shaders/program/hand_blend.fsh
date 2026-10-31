#version 150

// Blend stage for dual hand-shader mode, ported from the hand-modules pack.
// Combines two effect layers (each already rendered to its own texture) by one
// of six blend modes, masked to the hand by depth.

uniform sampler2D Shader1Texture;
uniform sampler2D Shader2Texture;
uniform sampler2D DepthTexture;

// P0 = (blendMode, blendIntensity, shader1Strength, shader2Strength).
layout(std140) uniform Params {
    vec4 P[8];
};

in vec2 texCoord;
out vec4 fragColor;

vec3 blendAdd(vec3 a, vec3 b) { return min(a + b, vec3(1.0)); }
vec3 blendScreen(vec3 a, vec3 b) { return vec3(1.0) - (vec3(1.0) - a) * (vec3(1.0) - b); }
vec3 blendOverlay(vec3 a, vec3 b) {
    return vec3(
        a.r < 0.5 ? 2.0 * a.r * b.r : 1.0 - 2.0 * (1.0 - a.r) * (1.0 - b.r),
        a.g < 0.5 ? 2.0 * a.g * b.g : 1.0 - 2.0 * (1.0 - a.g) * (1.0 - b.g),
        a.b < 0.5 ? 2.0 * a.b * b.b : 1.0 - 2.0 * (1.0 - a.b) * (1.0 - b.b));
}

void main() {
    int blendMode = int(P[0].x + 0.5);
    float blendIntensity = P[0].y;
    float shader1Strength = P[0].z;
    float shader2Strength = P[0].w;

    vec4 s1 = texture(Shader1Texture, texCoord);
    vec4 s2 = texture(Shader2Texture, texCoord);

    vec2 texelSize = 1.0 / vec2(textureSize(DepthTexture, 0));
    float minDepth = texture(DepthTexture, texCoord).r;
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(-texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, -texelSize.y)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, texelSize.y)).r);
    float mask = smoothstep(0.99, 0.98, minDepth);
    if (mask < 0.01) discard;

    vec3 c1 = s1.rgb * shader1Strength;
    vec3 c2 = s2.rgb * shader2Strength;

    vec3 blended;
    if (blendMode == 0) blended = mix(c1, c2, blendIntensity);
    else if (blendMode == 1) blended = mix(c1, blendAdd(c1, c2), blendIntensity);
    else if (blendMode == 2) blended = mix(c1, c1 * c2, blendIntensity);
    else if (blendMode == 3) blended = mix(c1, blendScreen(c1, c2), blendIntensity);
    else if (blendMode == 4) blended = mix(c1, blendOverlay(c1, c2), blendIntensity);
    else if (blendMode == 5) blended = mix(c1, abs(c1 - c2), blendIntensity);
    else blended = c1;

    fragColor = vec4(blended, mask);
}
