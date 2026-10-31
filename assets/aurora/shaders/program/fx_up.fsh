#version 150

// tent upsample of the level below, added onto this level by the pipeline's blend

uniform sampler2D InSampler;

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec2 h = 0.5 / vec2(textureSize(InSampler, 0));
    vec3 s = texture(InSampler, texCoord + vec2(-h.x * 2.0, 0.0)).rgb + texture(InSampler, texCoord + vec2(-h.x, h.y)).rgb * 2.0
        + texture(InSampler, texCoord + vec2(0.0, h.y * 2.0)).rgb + texture(InSampler, texCoord + vec2(h.x, h.y)).rgb * 2.0
        + texture(InSampler, texCoord + vec2(h.x * 2.0, 0.0)).rgb + texture(InSampler, texCoord + vec2(h.x, -h.y)).rgb * 2.0
        + texture(InSampler, texCoord + vec2(0.0, -h.y * 2.0)).rgb + texture(InSampler, texCoord + vec2(-h.x, -h.y)).rgb * 2.0;
    fragColor = vec4(s / 12.0, 1.0);
}
