#version 330

#moj_import <minecraft:fog.glsl>
#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <minecraft:projection.glsl>

in vec3 Position;
in vec4 Color;

out float sphericalVertexDistance;
out float cylindricalVertexDistance;
out vec4 vertexColor;

// vanilla's lightning vertex shader, with the depth pinned just short of the far plane.
// the sky pass writes no depth, so only pixels nothing was drawn over still read the cleared
// far value, and the depth test keeps the glow to those. drawn at its real distance it showed
// through any terrain further out than the sphere it sits on.
void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    gl_Position.z = gl_Position.w * 0.9999999;

    sphericalVertexDistance = fog_spherical_distance(Position);
    cylindricalVertexDistance = fog_cylindrical_distance(Position);
    vertexColor = Color;
}
