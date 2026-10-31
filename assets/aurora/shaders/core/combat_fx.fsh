#version 150

// the prototype's sprite and strip shaders as one. kind & 31 is the shape (15 is a ribbon strip),
// kind & 32 marks an alpha blended sprite (smoke), everything else is light and adds.
// HDR has nowhere to live in an 8 bit target, so each fragment is squashed by 1 - exp(-c) before it
// blends: a hot core still runs to white and a dim tail stays linear.
// DIRECT is the same sprite drawn straight into whatever is bound (wardrobe preview, VulkanMod), so it
// gamma encodes itself and gives additive light an alpha, or it would vanish on a cleared gui texture

in vec2 uv;
in vec4 col;
in float extra;
flat in int kind;
flat in float param;

out vec4 fragColor;

float hash(vec2 p) { p = fract(p * vec2(123.34, 456.21)); p += dot(p, p + 45.32); return fract(p.x * p.y); }
float vnoise(vec2 p) {
    vec2 i = floor(p), f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    return mix(mix(hash(i), hash(i + vec2(1.0, 0.0)), f.x), mix(hash(i + vec2(0.0, 1.0)), hash(i + vec2(1.0, 1.0)), f.x), f.y);
}
float fbm(vec2 p) {
    float s = 0.0, a = 0.5;
    for (int i = 0; i < 4; i++) { s += a * vnoise(p); p = p * 2.03 + 7.1; a *= 0.5; }
    return s;
}
vec3 hot(vec3 c, float k) { float m = max(c.r, max(c.g, c.b)); return mix(c, vec3(m), clamp(k, 0.0, 1.0)); }

void main() {
    int t = kind & 31;
    bool alpha = (kind & 32) != 0;
    float d = length(uv);
    float a = 0.0, core = 0.0;
    vec3 rgb = col.rgb;
    if (t == 0) { a = exp(-d * d * 4.5) * smoothstep(1.0, 0.75, d); core = exp(-d * d * 30.0); }
    else if (t == 1) { a = pow(smoothstep(1.0, 0.0, d), 2.0); }
    else if (t == 2) {
        float ang = atan(uv.y, uv.x);
        float sp = pow(abs(cos(ang * param * 0.5)), mix(10.0, 140.0, d));
        a = sp * exp(-d * 2.6) * smoothstep(1.0, 0.6, d) * 0.9 + exp(-d * d * 26.0) + exp(-d * d * 5.0) * 0.3;
        core = exp(-d * d * 40.0);
    }
    else if (t == 3) {
        float q = length(vec2(uv.x, max(abs(uv.y) - extra, 0.0)));
        a = exp(-q * q * 3.5) * smoothstep(1.0, 0.7, q);
        a *= mix(0.2, 1.0, clamp(uv.y / (extra + 1.0) * 0.5 + 0.5, 0.0, 1.0));
        core = exp(-q * q * 16.0);
    }
    else if (t == 4) {
        float n = fbm(uv * 1.3 + param * vec2(3.1, 1.7));
        a = smoothstep(1.0, 0.15, d + (n - 0.5) * 0.75) * (0.55 + 0.6 * n);
    }
    else if (t == 5) {
        float w = abs(param);
        float R = 1.0 - w * 1.6;
        float x = (d - R) / w;
        a = exp(-x * x * 2.0) * smoothstep(1.0, 0.96, d) + 0.06 * smoothstep(R, 0.0, d);
        core = exp(-x * x * 14.0) * 0.5;
        // a negative width is a broken ring
        if (param < 0.0) {
            float br = smoothstep(0.2, 0.8, vnoise(uv / max(d, 0.001) * 1.8 + 4.0));
            a *= 0.25 + 0.75 * br;
            core *= br * 1.6;
        }
    }
    else if (t == 6) {
        float y = uv.y;
        a = exp(-uv.x * uv.x * 5.0) * smoothstep(0.0, 0.06, y) * pow(max(1.0 - y, 0.0), 1.2);
        a *= 0.7 + 0.3 * vnoise(vec2(uv.x * 3.0, y * 14.0 - param * 4.0));
        core = exp(-uv.x * uv.x * 40.0);
    }
    else if (t == 7) {
        float sx = mix(0.25, 1.0, abs(sin(param)));
        vec2 q = vec2(uv.x / (0.42 * sx), uv.y);
        float dd = abs(q.x) + abs(q.y);
        float fill = smoothstep(1.0, 0.9, dd);
        float edge = exp(-pow((dd - 0.9) / 0.09, 2.0));
        float facet = step(0.0, q.x * q.y);
        a = fill * (0.35 + 0.35 * facet) + edge * 0.9 + exp(-d * d * 3.0) * 0.22;
        core = fill * smoothstep(0.55, 0.0, dd);
    }
    else if (t == 8) {
        // cherry petal, narrow base at -y, notched tip at +y
        vec2 q = uv;
        float y = q.y * 0.5 + 0.5;
        float e = length(vec2(q.x * (1.0 + max(0.0, -q.y) * 1.1) / 0.58, (q.y - 0.1) / 0.88));
        a = smoothstep(1.0, 0.78, e);
        a *= 1.0 - smoothstep(-0.04, 0.04, q.y - (0.74 + abs(q.x) * 1.5));
        a *= 0.7 + 0.3 * y;
        core = (1.0 - y) * 0.5 + 0.15 * exp(-q.x * q.x * 70.0) * y;
    }
    else if (t == 9) {
        float ang = atan(uv.y, uv.x);
        float f = abs(cos(ang * 2.5));
        float R = 0.5 + 0.42 * pow(f, 0.55) - 0.13 * pow(f, 30.0);
        a = smoothstep(R, R - 0.14, d) * (0.75 + 0.25 * smoothstep(0.0, 0.6, d));
        core = smoothstep(0.38, 0.0, d) * 0.8 + exp(-d * d * 90.0) * 0.6;
        a += exp(-d * d * 60.0) * 0.5;
    }
    else if (t == 10) {
        // paw, main pad plus four toe beans, toes toward +y
        a = smoothstep(1.0, 0.86, length(vec2(uv.x / 0.46, (uv.y + 0.3) / 0.36)));
        a = max(a, smoothstep(1.0, 0.84, length((uv - vec2(-0.56, 0.2)) / vec2(0.16, 0.2))));
        a = max(a, smoothstep(1.0, 0.84, length((uv - vec2(-0.2, 0.5)) / vec2(0.16, 0.2))));
        a = max(a, smoothstep(1.0, 0.84, length((uv - vec2(0.2, 0.5)) / vec2(0.16, 0.2))));
        a = max(a, smoothstep(1.0, 0.84, length((uv - vec2(0.56, 0.2)) / vec2(0.16, 0.2))));
        core = a * 0.35;
    }
    else if (t == 11) {
        // cat eyes, two slanted almonds with slit pupils
        vec2 q = vec2(abs(uv.x) - 0.47, uv.y);
        q.y -= q.x * 0.3;
        float lens = max(length(q - vec2(0.0, -0.26)), length(q - vec2(0.0, 0.26))) - 0.42;
        float inside = smoothstep(0.02, -0.02, lens);
        float pupil = smoothstep(1.0, 0.6, length(vec2(q.x / 0.05, q.y / 0.15)));
        a = inside * (1.0 - pupil * 0.95) + (1.0 - inside) * smoothstep(0.2, 0.0, lens) * 0.35;
        core = inside * (1.0 - pupil) * exp(-dot(q, q) * 14.0) * 0.8;
    }
    else if (t == 12) {
        // heart. param -1 or 1 keeps the left or right half of a jagged crack
        vec2 p = vec2(abs(uv.x) * 0.85, uv.y * 0.85 + 0.5);
        vec2 m = p - 0.5 * max(p.x + p.y, 0.0), n = p - vec2(0.0, 1.0);
        float sd = (p.y + p.x > 1.0) ? length(p - vec2(0.25, 0.75)) - 0.35355 : sqrt(min(dot(n, n), dot(m, m))) * sign(p.x - p.y);
        a = smoothstep(0.03, -0.03, sd) * (0.75 + 0.25 * smoothstep(-0.4, 0.0, sd)) + smoothstep(0.15, 0.0, max(sd, 0.0)) * 0.25 * step(0.0, sd);
        core = smoothstep(0.0, -0.35, sd) * 0.6;
        if (param != 0.0) {
            float edge = 0.07 * sin(uv.y * 16.0) + 0.03 * sin(uv.y * 41.0);
            float k = step(0.0, param * (uv.x - edge));
            a *= k;
            core *= k;
        }
    }
    else if (t == 13) {
        float d2 = length(uv - vec2(0.34, 0.2));
        a = smoothstep(0.8, 0.74, d) * smoothstep(0.6, 0.68, d2) + smoothstep(1.0, 0.5, d) * 0.12 * smoothstep(0.55, 0.75, d2);
        core = a * 0.5;
    }
    else if (t == 14) {
        float m = max(abs(uv.x), abs(uv.y));
        a = smoothstep(0.72, 0.66, m) + smoothstep(1.0, 0.66, m) * 0.2;
        core = smoothstep(0.55, 0.1, m) * 0.6;
    }
    else if (t == 16) {
        // icicle on a beam quad, uv.y runs 0 at the tip to 1 at the wide top
        float y = uv.y;
        float w = mix(0.03, 0.9, pow(max(y, 0.0), 0.8));
        float x = uv.x / w;
        a = smoothstep(1.0, 0.7, abs(x)) * smoothstep(0.0, 0.02, y) * smoothstep(1.0, 0.9, y) * (x > 0.0 ? 0.65 : 1.0);
        core = smoothstep(0.45, 0.0, abs(x + 0.3)) * (1.0 - y * 0.5) * 0.9;
    }
    else if (t == 17) {
        // cat head, round face and two ears, slit eyes cut out, whiskers glowing outside
        float head = smoothstep(0.64, 0.6, length(vec2(uv.x / 1.08, uv.y + 0.12)));
        vec2 e = vec2(abs(uv.x) - 0.4, uv.y - 0.3);
        e = vec2(0.94 * e.x - 0.34 * e.y, 0.34 * e.x + 0.94 * e.y);
        float ear = smoothstep(0.03, -0.03, max(abs(e.x) - (0.5 - e.y) * 0.62, max(-e.y, e.y - 0.5)));
        a = max(head, ear);
        vec2 q = vec2(abs(uv.x) - 0.26, uv.y + 0.06);
        float eye = smoothstep(1.0, 0.7, length(vec2(q.x / 0.13, q.y / 0.075)));
        float pupil = smoothstep(1.0, 0.6, length(vec2(q.x / 0.025, q.y / 0.07)));
        a *= 1.0 - eye * 0.92;
        a += eye * (1.0 - pupil) * 0.35;
        a *= 1.0 - smoothstep(0.06, 0.03, length(vec2(uv.x * 1.4, uv.y + 0.24))) * 0.6;
        float wh = 0.0;
        for (int i = 0; i < 3; i++) {
            vec2 s0 = vec2(0.42, -0.22 + float(i) * 0.07), s1 = vec2(0.98, -0.3 + float(i) * 0.14);
            vec2 pa = vec2(abs(uv.x), uv.y) - s0, ba = s1 - s0;
            float h = clamp(dot(pa, ba) / dot(ba, ba), 0.0, 1.0);
            wh = max(wh, smoothstep(0.022, 0.0, length(pa - ba * h)) * (1.0 - h * 0.6));
        }
        a = max(a, wh * 0.9);
        core = head * (1.0 - eye) * 0.15 + eye * (1.0 - pupil) * 0.6;
    }
    else if (t == 15) {
        float y = uv.y;
        a = exp(-y * y * 3.0) * smoothstep(1.0, 0.8, abs(y));
        core = exp(-y * y * 28.0);
        // strips arrive with intensity and alpha already in the colour, extra is the white core's weight
        rgb = col.rgb * a + vec3(max(col.r, max(col.g, col.b))) * core * extra;
        a = 1.0;
        core = 0.0;
    }

    if (alpha) {
        float k = a * col.a;
        if (k < 0.002) discard;
#ifdef DIRECT
        fragColor = vec4(pow(clamp(rgb, 0.0, 1.0), vec3(1.0 / 2.2)) * k, k);
#else
        fragColor = vec4(clamp(rgb, 0.0, 1.0) * k, k);
#endif
        return;
    }
    vec3 c = t == 15 ? rgb * col.a : hot(rgb, core) * a * col.a;
    c = 1.0 - exp(-max(c, vec3(0.0)));
    if (max(c.r, max(c.g, c.b)) < 0.001) discard;
#ifdef DIRECT
    c = pow(c, vec3(1.0 / 2.2));
    fragColor = vec4(c, max(c.r, max(c.g, c.b)));
#else
    fragColor = vec4(c, 0.0);
#endif
}
