#version 150

// Balatro-style hand shader, ported from the hand-modules pack. A swirling
// four-color paint over the hand. Color 4's red/green channels carry the
// contrast and lighting, the way the original repurposed them.

uniform sampler2D ColorTexture;
uniform sampler2D DepthTexture;

// P0 = (time, res.x, res.y, 0); P1 = color1; P2 = color2; P3 = color3;
// P4 = color4 (r = contrast/10, g = lighting).
layout(std140) uniform Params {
    vec4 P[8];
};

in vec2 texCoord;
out vec4 fragColor;

const float SPIN_ROTATION = -2.0;
const float SPIN_SPEED = 7.0;
const vec2 OFFSET = vec2(0.0);
const float SPIN_AMOUNT = 0.25;
const float PIXEL_FILTER = 745.0;
const float SPIN_EASE = 1.0;

vec4 effectFn(vec2 screenSize, vec2 screen_coords, float contrast, float lighting,
              float time, vec3 c1, vec3 c2, vec3 c3) {
    float pixel_size = length(screenSize.xy) / PIXEL_FILTER;
    vec2 effectUv = (floor(screen_coords.xy * (1.0 / pixel_size)) * pixel_size - 0.5 * screenSize.xy) / length(screenSize.xy) - OFFSET;
    float uv_len = length(effectUv);

    float speed = (SPIN_ROTATION * SPIN_EASE * 0.2);
    speed += 302.2;
    float new_pixel_angle = atan(effectUv.y, effectUv.x) + speed - SPIN_EASE * 20.0 * (SPIN_AMOUNT * uv_len + (1.0 - SPIN_AMOUNT));
    vec2 mid = (screenSize.xy / length(screenSize.xy)) / 2.0;
    effectUv = (vec2((uv_len * cos(new_pixel_angle) + mid.x), (uv_len * sin(new_pixel_angle) + mid.y)) - mid);

    effectUv *= 30.0;
    speed = time * SPIN_SPEED;
    vec2 uv2 = vec2(effectUv.x + effectUv.y);

    for (int i = 0; i < 5; i++) {
        uv2 += sin(max(effectUv.x, effectUv.y)) + effectUv;
        effectUv += 0.5 * vec2(cos(5.1123314 + 0.353 * uv2.y + speed * 0.131121), sin(uv2.x - 0.113 * speed));
        effectUv -= 1.0 * cos(effectUv.x + effectUv.y) - 1.0 * sin(effectUv.x * 0.711 - effectUv.y);
    }

    float contrast_mod = (0.25 * contrast + 0.5 * SPIN_AMOUNT + 1.2);
    float paint_res = min(2.0, max(0.0, length(effectUv) * 0.035 * contrast_mod));
    float c1p = max(0.0, 1.0 - contrast_mod * abs(1.0 - paint_res));
    float c2p = max(0.0, 1.0 - contrast_mod * abs(paint_res));
    float c3p = 1.0 - min(1.0, c1p + c2p);
    float light = (lighting - 0.2) * max(c1p * 5.0 - 4.0, 0.0) + lighting * max(c2p * 5.0 - 4.0, 0.0);

    vec4 color1 = vec4(c1, 1.0);
    vec4 color2 = vec4(c2, 1.0);
    vec4 color3 = vec4(c3, 1.0);
    return (0.3 / contrast) * color1
        + (1.0 - 0.3 / contrast) * (color1 * c1p + color2 * c2p + vec4(c3p * color3.rgb, c3p * color1.a))
        + light;
}

void main() {
    float time = P[0].x;
    vec2 resolution = P[0].yz;
    vec3 c1 = P[1].rgb, c2 = P[2].rgb, c3 = P[3].rgb;
    float contrast = P[4].r * 10.0;
    float lighting = P[4].g;

    vec4 originalColor = texture(ColorTexture, texCoord);
    vec2 texelSize = 1.0 / vec2(textureSize(DepthTexture, 0));

    float minDepth = texture(DepthTexture, texCoord).r;
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(-texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(texelSize.x, 0.0)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, -texelSize.y)).r);
    minDepth = min(minDepth, texture(DepthTexture, texCoord + vec2(0.0, texelSize.y)).r);

    float mask = smoothstep(0.99, 0.98, minDepth);
    if (mask < 0.01) discard;

    vec4 effectColor = effectFn(resolution, texCoord * resolution, contrast, lighting, time, c1, c2, c3);
    vec4 finalHandColor = mix(originalColor, effectColor, 0.85);
    fragColor = vec4(finalHandColor.rgb, mask);
}
