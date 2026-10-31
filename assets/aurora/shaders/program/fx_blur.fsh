#version 150

// one direction of the outline's silhouette blur, P[0].xy is the step in uv

uniform sampler2D InSampler;

layout(std140) uniform Params {
    vec4 P[1];
};

in vec2 texCoord;
out vec4 fragColor;

void main() {
    float w0 = 0.227, w1 = 0.195, w2 = 0.122, w3 = 0.054, w4 = 0.016;
    vec2 s = P[0].xy;
    float v = texture(InSampler, texCoord).a * w0
        + (texture(InSampler, texCoord + s).a + texture(InSampler, texCoord - s).a) * w1
        + (texture(InSampler, texCoord + s * 2.0).a + texture(InSampler, texCoord - s * 2.0).a) * w2
        + (texture(InSampler, texCoord + s * 3.0).a + texture(InSampler, texCoord - s * 3.0).a) * w3
        + (texture(InSampler, texCoord + s * 4.0).a + texture(InSampler, texCoord - s * 4.0).a) * w4;
    fragColor = vec4(0.0, 0.0, 0.0, v);
}
