#version 150

// lays the effects layer and its bloom over the world. on the way it bends the world through any live
// shockwave (the prototype's distortion, solved per pixel from the ring instead of a separate offset
// target) and lights it with the flashes, rebuilt from depth like the prototype's floor shading

uniform sampler2D SceneSampler;
uniform sampler2D FxSampler;
uniform sampler2D BloomSampler;
uniform sampler2D DepthSampler;

// P[0..3] inverse projection * view, by column, camera relative
// P[4]    (width, height, bloom strength, frame noise seed)
// P[5]    (camera forward xyz, 0)
// P[6..17]  four shockwaves: (centre xyz, radius), (width, strength, ground, 0), (centre uv, 0, 0)
// P[18..25] four lights: (centre xyz, radius), (colour, 0)
layout(std140) uniform Params {
    vec4 P[26];
};

in vec2 texCoord;
out vec4 fragColor;

float hash(vec2 p) { p = fract(p * vec2(123.34, 456.21)); p += dot(p, p + 45.32); return fract(p.x * p.y); }

void main() {
    mat4 inv = mat4(P[0], P[1], P[2], P[3]);
    vec2 res = P[4].xy;
    vec4 far = inv * vec4(texCoord * 2.0 - 1.0, 1.0, 1.0);
    vec3 ray = far.xyz / far.w;
    vec3 fwd = P[5].xyz;

    vec2 off = vec2(0.0);
    for (int i = 0; i < 4; i++) {
        vec4 a = P[6 + i * 3], b = P[7 + i * 3], c = P[8 + i * 3];
        if (b.y == 0.0) continue;
        float dist;
        if (b.z > 0.5) {
            if (abs(ray.y) < 1e-5) continue;
            float t = a.y / ray.y;
            if (t <= 0.0) continue;
            dist = length((ray * t - a.xyz).xz);
        } else {
            float t = dot(a.xyz, fwd) / dot(ray, fwd);
            if (t <= 0.0) continue;
            dist = length(ray * t - a.xyz);
        }
        float x = (dist - a.w) / b.x;
        float prof = -x * exp(-x * x * 2.2);
        vec2 dir = (texCoord - c.xy) * res;
        dir /= max(length(dir), 1.0);
        off += dir * prof * b.y;
    }
    off *= vec2(res.y / res.x, 1.0);

    vec3 scene = vec3(texture(SceneSampler, texCoord - off * 1.12).r, texture(SceneSampler, texCoord - off).g,
        texture(SceneSampler, texCoord - off * 0.88).b);
    scene = pow(scene, vec3(2.2));

    float depth = texture(DepthSampler, texCoord).r;
    if (depth < 1.0) {
        vec4 wp = inv * vec4(texCoord * 2.0 - 1.0, depth * 2.0 - 1.0, 1.0);
        vec3 p = wp.xyz / wp.w;
        vec3 add = vec3(0.0);
        for (int i = 0; i < 4; i++) {
            vec4 l = P[18 + i * 2], lc = P[19 + i * 2];
            if (l.w <= 0.0) continue;
            float dl = length(l.xyz - p);
            float w = clamp(1.0 - pow(dl / l.w, 4.0), 0.0, 1.0);
            // no normals here, so the prototype's lambert term is taken at its average
            add += lc.rgb * (w * w / (1.0 + dl * dl)) * 0.5;
        }
        scene += scene * add;
    }

    vec4 fx = texture(FxSampler, texCoord - off);
    vec3 c = scene * (1.0 - fx.a) + fx.rgb + texture(BloomSampler, texCoord).rgb * P[4].z;
    c = pow(clamp(c, 0.0, 1.0), vec3(1.0 / 2.2)) + (hash(gl_FragCoord.xy + P[4].w) - 0.5) / 255.0;
    fragColor = vec4(c, 1.0);
}
