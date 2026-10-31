#version 150

// Turns the offscreen player render into the near/far mask the effect shaders
// read as hand depth: 0 (near) where a player is genuinely visible, 1 (far)
// elsewhere. Occlusion is decided here, in linear depth, rather than by copying
// the scene depth into the mask framebuffer every frame (which stalled the
// pipeline). Same test the fair-play glow uses.

uniform sampler2D In;          // player coverage (the mask render's color)
uniform sampler2D InDepth;     // depth the player render wrote
uniform sampler2D SceneDepth;  // depth of the opaque world

in vec2 texCoord;
out vec4 fragColor;

const float NEAR = 0.05;
const float TOLERANCE = 0.05;

void main() {
    if (texture(In, texCoord).a < 0.5) {
        fragColor = vec4(1.0); // no player here
        return;
    }
    float entityDepth = texture(InDepth, texCoord).r;
    float sceneDepth = texture(SceneDepth, texCoord).r;
    float zEntity = NEAR / max(1.0e-5, 1.0 - entityDepth);
    float zScene = NEAR / max(1.0e-5, 1.0 - sceneDepth);
    if (zEntity - zScene > TOLERANCE) {
        fragColor = vec4(1.0); // behind the world here: hidden
        return;
    }
    fragColor = vec4(0.0); // visible player
}
