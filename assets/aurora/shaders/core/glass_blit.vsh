#version 150

in vec3 Position;
out vec2 texCoord;

// The quad is fed corners at -1 and 1, so this maps the visible NDC square
// [-1,1] onto texCoord [0,1] and lets the rest fall outside the viewport.
void main() {
    texCoord = Position.xy;
    gl_Position = vec4(Position.xy * 2.0 - 1.0, 0.0, 1.0);
}
