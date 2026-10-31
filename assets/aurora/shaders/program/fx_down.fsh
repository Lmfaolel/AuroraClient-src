#version 150

uniform sampler2D InSampler;

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec2 px = 1.0 / vec2(textureSize(InSampler, 0));
    vec3 s = texture(InSampler, texCoord).rgb * 4.0
        + texture(InSampler, texCoord - px).rgb + texture(InSampler, texCoord + px).rgb
        + texture(InSampler, texCoord + vec2(px.x, -px.y)).rgb + texture(InSampler, texCoord - vec2(px.x, -px.y)).rgb;
    fragColor = vec4(s / 8.0, 1.0);
}
