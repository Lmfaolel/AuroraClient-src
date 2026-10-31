#version 330

// Flat silhouette the entity-outline pass drew.
uniform sampler2D InSampler;
// Depth that silhouette wrote. Only meaningful because RenderPipelineMixin turns
// on depth write and a LEQUAL test for pipeline/outline_cull and
// pipeline/outline_no_cull while this chain is in use. Vanilla leaves both off,
// so without that mixin this buffer is still the 1.0 it was cleared to and every
// silhouette reads as infinitely far away, which discards the lot.
uniform sampler2D InDepthSampler;
// Depth of the opaque world. This chain runs immediately after the main world
// pass, so terrain, entities and block entities are all in it, and the player's
// own body has written its depth here too.
uniform sampler2D SceneDepthSampler;

in vec2 texCoord;

out vec4 fragColor;

// Aurora's fair-play rule lives in these two constants.
//
// The comparison is in linear world distance, not raw depth. A fixed epsilon on
// the depth buffer cannot separate armor's tiny forward offset from a real
// occluder, because the same world gap is huge up close and vanishing at range.
// Linearising with the near plane makes the slack mean blocks everywhere.
//
// 0.05 is Minecraft's near clip (GameRenderer.getBasicProjectionMatrix). The
// far plane is dropped from the linearisation on purpose: at Minecraft's far
// distances n / (1 - d) is the limit of the exact expression and the error is
// far below the tolerance.
const float NEAR = 0.05;
const float TOLERANCE = 0.05;

void main() {
    vec4 silhouette = texture(InSampler, texCoord);

    if (silhouette.a == 0.0) {
        fragColor = vec4(0.0);
        return;
    }

    float entityDepth = texture(InDepthSampler, texCoord).r;
    float sceneDepth = texture(SceneDepthSampler, texCoord).r;

    float zEntity = NEAR / max(1.0e-5, 1.0 - entityDepth);
    float zScene = NEAR / max(1.0e-5, 1.0 - sceneDepth);

    // The silhouette sits clearly behind whatever the world drew here, so this
    // pixel of the player is hidden. Drop it and let the outline pass shape
    // itself around what is actually visible.
    if (zEntity - zScene > TOLERANCE) {
        fragColor = vec4(0.0);
        return;
    }

    fragColor = silhouette;
}
