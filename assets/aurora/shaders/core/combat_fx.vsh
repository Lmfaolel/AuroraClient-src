#version 150

#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <minecraft:projection.glsl>

// EffectEngine builds every quad on the cpu, so this only unpacks. Color is the hue, UV2.x/1024 how much
// brighter than 1 it really is (8 bit colour can't hold hdr), UV1 is sprite kind and its shape param,
// UV2.y/256 the streak stretch or a strip's core weight
in vec3 Position;
in vec4 Color;
in vec2 UV0;
in ivec2 UV1;
in ivec2 UV2;

out vec2 uv;
out vec4 col;
out float extra;
flat out int kind;
flat out float param;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    uv = UV0;
    col = vec4(Color.rgb * (float(UV2.x) / 1024.0), Color.a);
    extra = float(UV2.y) / 256.0;
    kind = UV1.x;
    param = float(UV1.y) / 256.0;
}
