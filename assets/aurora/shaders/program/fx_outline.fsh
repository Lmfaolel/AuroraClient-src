#version 150

// the prototype's outline: a hard edge just outside the silhouette, a soft glow from the blurred mask and
// an inner rim, added onto the effects layer. the mask is the target drawn on its own with its normal
// shaders, so coverage is its alpha

uniform sampler2D MaskSampler;
uniform sampler2D BlurSampler;

// P[0].xy one mask pixel in uv, P[1] edge colour, P[2] glow colour, P[3] inner colour
layout(std140) uniform Params {
    vec4 P[4];
};

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec2 px = P[0].xy;
    float m = texture(MaskSampler, texCoord).a;
    float b = texture(BlurSampler, texCoord).a;
    float d = 0.0;
    for (int i = 0; i < 12; i++) {
        float an = float(i) * 0.5236;
        vec2 dir = vec2(cos(an), sin(an));
        d = max(d, texture(MaskSampler, texCoord + dir * px * 1.5).a);
        d = max(d, texture(MaskSampler, texCoord + dir * px * 2.6).a * 0.7);
    }
    float edge = d * (1.0 - m), glow = b * (1.0 - m), inner = m * clamp(1.0 - b * 1.2, 0.0, 1.0);
    vec3 c = P[1].rgb * edge + P[2].rgb * glow * glow * 1.6 + P[3].rgb * inner;
    fragColor = vec4(1.0 - exp(-c), 0.0);
}
