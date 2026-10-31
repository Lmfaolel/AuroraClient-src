#version 330

uniform sampler2D InSampler;    // this frame
uniform sampler2D PrevSampler;  // the accumulation buffer (previous result)

// BlurAmount.x is how much of the previous frame to keep (0..1). no comments
// inside the block, VulkanMod's GLSL converter throws on them.
layout(std140) uniform BlurConfig {
    vec4 BlurAmount;
};

in vec2 texCoord;

out vec4 fragColor;

// Classic accumulation blur: each frame is mixed into the running result, so
// moving edges leave a short trail. Vanilla dropped the old "phosphor" shader
// this used to rely on, so the blend is done explicitly here by sampling both
// the current frame and the accumulation target: no framebuffer blending, no
// extra render state.
void main() {
    vec3 cur  = texture(InSampler, texCoord).rgb;
    vec3 prev = texture(PrevSampler, texCoord).rgb;
    fragColor = vec4(mix(cur, prev, clamp(BlurAmount.x, 0.0, 0.95)), 1.0);
}
