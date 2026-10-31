#version 150

// Camera motion blur. Each pixel's world position is rebuilt from depth,
// carried into last frame's view, and the difference in screen position is
// how far that pixel moved. The colour is averaged along that path, so a still
// camera draws a sharp frame and a turning or strafing one smears in the
// direction things crossed the screen.
//
// Runs on the world before the hand is drawn, so the hand and held item stay
// sharp.

uniform sampler2D ColorTexture;
uniform sampler2D DepthTexture;

// P[0..3]  inverse of this frame's projection * view, by column
// P[4..7]  last frame's projection * view, by column
// P[8]     (xyz how far the camera moved since last frame, w blur scale)
// P[9]     (longest blur allowed in screen widths, sample count, 0, 0)
layout(std140) uniform Params {
    vec4 P[10];
};

in vec2 texCoord;
out vec4 fragColor;

void main() {
    mat4 invViewProj = mat4(P[0], P[1], P[2], P[3]);
    mat4 prevViewProj = mat4(P[4], P[5], P[6], P[7]);
    vec3 camDelta = P[8].xyz;
    float scale = P[8].w;
    float maxLen = P[9].x;
    int samples = int(P[9].y);

    vec4 base = texture(ColorTexture, texCoord);
    float depth = texture(DepthTexture, texCoord).r;

    // Camera relative world position this frame, then relative to where the
    // camera was last frame.
    vec4 rel = invViewProj * vec4(texCoord * 2.0 - 1.0, depth * 2.0 - 1.0, 1.0);
    rel /= rel.w;
    vec4 prevClip = prevViewProj * vec4(rel.xyz + camDelta, 1.0);
    if (prevClip.w <= 0.0) {
        fragColor = base;
        return;
    }
    vec2 prevUv = prevClip.xy / prevClip.w * 0.5 + 0.5;

    vec2 velocity = (texCoord - prevUv) * scale;
    float len = length(velocity);
    if (len < 0.0004) {
        fragColor = base;
        return;
    }
    if (len > maxLen) velocity *= maxLen / len;

    // Centred on the pixel, so the smear sits across the object rather than
    // trailing entirely behind it.
    vec3 sum = vec3(0.0);
    int n = 0;
    for (int i = 0; i < 24; i++) {
        if (i >= samples) break;
        float t = float(i) / float(samples - 1) - 0.5;
        sum += texture(ColorTexture, clamp(texCoord - velocity * t, 0.0, 1.0)).rgb;
        n++;
    }
    fragColor = vec4(sum / float(n), 1.0);
}
