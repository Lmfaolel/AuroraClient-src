#version 150

#moj_import <minecraft:dynamictransforms.glsl>

uniform sampler2D Sampler0;

in vec4 vertexColor;
in vec2 texCoord0;
in vec2 effect;

out vec4 fragColor;

// the field is 0.5 on the outline. effect.x pushes the edge outward (a shadow's or glow's body), effect.y fades it
// out over that much more. fwidth keeps the antialiased edge about a screen pixel wide at any scale
void main() {
    float d = texture(Sampler0, texCoord0).r;
    float aa = max(fwidth(d) * 0.5, 0.0001);
    float edge = 0.5 - effect.x;
    float a = smoothstep(edge - effect.y - aa, edge + aa, d);
    if (effect.y > 0.0) a *= a;
    vec4 color = vertexColor * ColorModulator;
    color.a *= a;
    if (color.a < 0.002) {
        discard;
    }
    fragColor = color;
}
