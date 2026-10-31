#version 150

// Frame blend. Each frame is mixed into the running result, the same idea as
// Trail, but how much of the history survives comes from the frame time (see
// MotionBlurGpu), so the fade lasts the same number of milliseconds at any
// frame rate instead of the same number of frames.

uniform sampler2D ColorTexture;    // this frame
uniform sampler2D HistoryTexture;  // the blended result up to last frame

// P[0].x = how much of the history to keep this frame, 0..1
layout(std140) uniform Params {
    vec4 P[1];
};

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec3 cur = texture(ColorTexture, texCoord).rgb;
    vec3 prev = texture(HistoryTexture, texCoord).rgb;
    fragColor = vec4(mix(cur, prev, clamp(P[0].x, 0.0, 0.98)), 1.0);
}
