#version 150

// Stripes hand shader, ported from the hand-modules pack.

uniform sampler2D ColorTexture;
uniform sampler2D DepthTexture;

// P0 = (time, res.x, res.y, stripesWidth); P1.rgb = color 1; P2.rgb = color 2;
// P3.x = stripesSpeed.
layout(std140) uniform Params {
    vec4 P[8];
};

in vec2 texCoord;
out vec4 fragColor;

void main() {
    float time = P[0].x;
    vec2 resolution = P[0].yz;
    float stripesWidth = P[0].w;
    vec3 stripesColor1 = P[1].rgb;
    vec3 stripesColor2 = P[2].rgb;
    float stripesSpeed = P[3].x;

    vec4 originalColor = texture(ColorTexture, texCoord);
    vec2 texelSize = 1.0 / vec2(textureSize(DepthTexture, 0));

    float minDepth = texture(DepthTexture, texCoord).r;
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(-texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, -texelSize.y)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, texelSize.y)).r);

    float mask = smoothstep(0.99, 0.98, minDepth);
    if (mask < 0.01) discard;

    vec2 effectUv = texCoord;
    effectUv.x *= resolution.x / resolution.y;
    float diagonal = (effectUv.x + effectUv.y + time * stripesSpeed) / stripesWidth;
    float pattern = mod(floor(diagonal), 2.0);
    vec3 col = mix(stripesColor1, stripesColor2, pattern);

    vec4 finalHandColor = mix(originalColor, vec4(col, 1.0), 0.85);
    fragColor = vec4(finalHandColor.rgb, mask);
}
