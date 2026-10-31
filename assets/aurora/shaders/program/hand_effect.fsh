#version 150

// The depth buffer as it stands immediately after the hand is drawn.
//
// Minecraft clears depth before rendering the hand, so at that exact moment the
// buffer holds the hand and nothing else: hand pixels are near, everything else
// is the cleared far value. That is the whole isolation trick, and it is why
// this pass has to run there and nowhere else.
uniform sampler2D DepthSampler;

// A copy of the color attachment taken just before this pass. It is a copy
// because sampling the attachment being drawn into is a read-write hazard.
// Glow is the one effect that ignores it, since a halo only adds light.
uniform sampler2D ColorSampler;

// Color rgb is the effect's color, a its strength, 0 to 1.
// Params x: which effect. y: speed. z: scale or radius. w: 1 to cover the hand
// itself rather than only its outline.
// Screen xy: screen size in pixels. z: seconds since launch. w: unused.
// no comments inside the block, VulkanMod's GLSL converter throws on them.
layout(std140) uniform Config {
    vec4 Color;
    vec4 Params;
    vec4 Screen;
};

in vec2 texCoord;
out vec4 fragColor;

// Anything nearer than this is hand. The cleared buffer sits at 1.0, and the
// margin absorbs depth precision wobble at the back of the range.
const float FAR = 0.99;

const int GLOW = 1, CHROMA = 2, GLASS = 3, SMOKE = 4, INVERT = 5;
const int STRIPES = 6, SNOW = 7, SWIRL = 8, SOLID = 9;

float hash(vec2 p) {
    return fract(sin(dot(p, vec2(127.1, 311.7))) * 43758.5453);
}

// Value noise. Cheap, and every texture-free effect here wants the same thing:
// something that varies smoothly and does not repeat visibly.
float noise(vec2 p) {
    vec2 i = floor(p), f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    return mix(mix(hash(i), hash(i + vec2(1, 0)), f.x),
               mix(hash(i + vec2(0, 1)), hash(i + vec2(1, 1)), f.x), f.y);
}

vec3 hueShift(vec3 c, float shift) {
    const vec3 k = vec3(0.57735);
    float ca = cos(shift);
    return c * ca + cross(k, c) * sin(shift) + k * dot(k, c) * (1.0 - ca);
}

void main() {
    vec2 texel = 1.0 / Screen.xy;
    float centre = texture(DepthSampler, texCoord).r;
    bool onHand = centre < FAR;

    int effect = int(Params.x + 0.5);
    float time = Screen.z * max(Params.y, 0.0);
    float strength = Color.a;

    // Glow is the only effect that draws OUTSIDE the hand, so it runs before
    // the on-hand test that every other effect starts with.
    if (effect == GLOW) {
        float disperse = max(Params.z, 1.0);
        int rings = int(min(disperse, 5.0));
        float glow = 0.0;
        // Eight directions per ring rather than a filled square. A square of
        // radius 5 is 121 taps per pixel for a halo nobody can tell apart from
        // this one, and it runs over every pixel on screen.
        for (int ring = 1; ring <= rings; ring++) {
            float r = float(ring);
            for (int i = 0; i < 8; i++) {
                float a = float(i) * 0.785398;
                vec2 off = vec2(cos(a), sin(a)) * r * texel * (disperse / float(rings));
                if (!onHand && texture(DepthSampler, texCoord + off).r < FAR) {
                    float falloff = 1.0 - (r / float(rings + 1));
                    glow += pow(falloff, 2.5) * 0.2;
                }
            }
        }
        if (onHand && Params.w > 0.5) glow = max(glow, 1.0);
        glow = clamp(glow, 0.0, 1.0) * strength;
        if (glow < 0.004) discard;
        fragColor = vec4(Color.rgb, glow);
        return;
    }

    // Everything else recolors the hand, so anything that is not hand is not
    // this pass's business.
    if (!onHand) discard;

    vec3 src = texture(ColorSampler, texCoord).rgb;
    vec3 outc = src;
    float alpha = strength;
    float scale = max(Params.z, 0.01);

    if (effect == CHROMA) {
        // Hue cycling driven by position as well as time, so the hand reads as
        // holding a moving gradient rather than flashing one flat color.
        outc = hueShift(src, time + texCoord.x * 3.0 + texCoord.y * 2.0);
    } else if (effect == GLASS) {
        // Sample the scene through a wobble. The offset is deliberately small:
        // past a couple of percent of the screen the hand stops looking like
        // glass and starts looking like a broken frame.
        vec2 wob = vec2(noise(texCoord * scale * 8.0 + time),
                        noise(texCoord * scale * 8.0 - time)) - 0.5;
        outc = texture(ColorSampler, texCoord + wob * 0.02 * scale).rgb;
        outc = mix(outc, outc + Color.rgb * 0.25, 0.5);
    } else if (effect == SMOKE) {
        // Two octaves drifting at different rates. One octave alone reads as a
        // moving texture rather than as smoke.
        float n = noise(texCoord * scale * 6.0 + vec2(time * 0.3, -time * 0.5)) * 0.6
                + noise(texCoord * scale * 14.0 - vec2(time * 0.2, time * 0.4)) * 0.4;
        outc = mix(src, Color.rgb, smoothstep(0.35, 0.85, n));
    } else if (effect == INVERT) {
        outc = vec3(1.0) - src;
    } else if (effect == STRIPES) {
        // Diagonal, because horizontal or vertical stripes line up with the
        // item's own edges and read as z-fighting.
        float s = sin((texCoord.x + texCoord.y) * scale * 60.0 - time * 3.0);
        outc = mix(src, Color.rgb, step(0.0, s));
    } else if (effect == SNOW) {
        // Flakes in a scrolling grid, each cell holding at most one, which is
        // what keeps them evenly spread without looking like a lattice.
        vec2 g = texCoord * Screen.xy / (14.0 / max(scale, 0.1));
        g.y += time * 6.0;
        vec2 cell = floor(g);
        float d = length(fract(g) - vec2(hash(cell), hash(cell + 7.3)));
        outc = mix(src, Color.rgb, smoothstep(0.28, 0.05, d));
    } else if (effect == SWIRL) {
        // Rotating bands about the screen centre, hue cycling as they turn.
        vec2 p = (texCoord - 0.5) * vec2(Screen.x / Screen.y, 1.0);
        float a = atan(p.y, p.x) + length(p) * scale * 6.0 - time;
        float band = 0.5 + 0.5 * sin(a * 4.0);
        outc = mix(src, hueShift(Color.rgb, a), band);
    } else if (effect == SOLID) {
        outc = Color.rgb;
    } else {
        discard;
    }

    fragColor = vec4(outc, alpha);
}
