#version 150

// first bloom level, half size, from the effects layer only. the layer is already squashed to 0..1, so the
// knee sits far lower than the prototype's hdr .85 or almost nothing would get through

uniform sampler2D InSampler;

in vec2 texCoord;
out vec4 fragColor;

vec3 knee(vec3 c) {
    float b = max(c.r, max(c.g, c.b));
    float T = 0.35, K = 0.3;
    float s = clamp(b - T + K, 0.0, 2.0 * K);
    s = s * s / (4.0 * K + 1e-4);
    return c * max(s, b - T) / max(b, 1e-4);
}

void main() {
    vec2 px = 1.0 / vec2(textureSize(InSampler, 0));
    vec3 c = knee(texture(InSampler, texCoord + px * vec2(-1.0, -1.0)).rgb)
        + knee(texture(InSampler, texCoord + px * vec2(1.0, -1.0)).rgb)
        + knee(texture(InSampler, texCoord + px * vec2(-1.0, 1.0)).rgb)
        + knee(texture(InSampler, texCoord + px * vec2(1.0, 1.0)).rgb);
    fragColor = vec4(c * 0.25, 1.0);
}
