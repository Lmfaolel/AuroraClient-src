#version 150

// Lines over a glass player. Three kinds of edge, all taken from PlayerMask's own
// render of the player (never the scene), and only drawn on pixels the occlusion
// mask says are visible, so nothing outlines through a wall:
// the silhouette, where the neighbour has no player at all (a block cutting the
// player is not a rim), a depth step (armour over the body, an arm in front of
// the torso), and a Sobel on the player's colour (armour trim, skin features).

uniform sampler2D Mask;        // 0 where a player is visible, 1 elsewhere
uniform sampler2D PlayerColor; // the player render, alpha is coverage
uniform sampler2D PlayerDepth; // the depth that render wrote

// Size = (res.x, res.y, width px, 0), Color = rgba
layout(std140) uniform Params {
    vec4 Size;
    vec4 Color;
};

in vec2 texCoord;
out vec4 fragColor;

const float NEAR = 0.05;

float linear(vec2 uv) {
    return NEAR / max(1.0e-5, 1.0 - texture(PlayerDepth, uv).r);
}

float luma(vec2 uv) {
    return dot(texture(PlayerColor, uv).rgb, vec3(0.299, 0.587, 0.114));
}

void main() {
    if (texture(Mask, texCoord).r > 0.5) discard;

    vec2 px = Size.z / Size.xy;
    vec2 o[8] = vec2[](vec2(-1.0, -1.0), vec2(0.0, -1.0), vec2(1.0, -1.0), vec2(-1.0, 0.0),
                       vec2(1.0, 0.0), vec2(-1.0, 1.0), vec2(0.0, 1.0), vec2(1.0, 1.0));

    float rim = 0.0;
    float z = linear(texCoord);
    float dz = 0.0;
    for (int i = 0; i < 8; i++) {
        vec2 uv = texCoord + o[i] * px;
        if (texture(PlayerColor, uv).a < 0.5) {
            rim = 1.0;
        } else {
            dz = max(dz, abs(linear(uv) - z));
        }
    }
    // armour sits 1/16 of a block off the body, so a step past half that is a real edge
    float depthEdge = smoothstep(0.03, 0.06, dz);

    float tl = luma(texCoord + o[0] * px), t = luma(texCoord + o[1] * px), tr = luma(texCoord + o[2] * px);
    float l = luma(texCoord + o[3] * px), r = luma(texCoord + o[4] * px);
    float bl = luma(texCoord + o[5] * px), b = luma(texCoord + o[6] * px), br = luma(texCoord + o[7] * px);
    float gx = (tr + 2.0 * r + br) - (tl + 2.0 * l + bl);
    float gy = (bl + 2.0 * b + br) - (tl + 2.0 * t + tr);
    float colorEdge = smoothstep(0.35, 0.7, length(vec2(gx, gy)));

    float e = max(rim, max(depthEdge, colorEdge * 0.8));
    if (e < 0.02) discard;
    fragColor = vec4(Color.rgb, Color.a * e);
}
