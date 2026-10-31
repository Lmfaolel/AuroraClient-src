#version 150

// The painted sky, ported from the browser prototype. One full screen pass at
// the sky stage, before terrain. The CPU blends the palette by sun height and
// hands over only the current colours, scalars ride in the w of each vec4.
// Cel is star space to world, InvViewProj rebuilds the view ray per pixel.

uniform sampler2D Noise;

layout(std140) uniform SkyParams {
    mat4 InvViewProj;
    mat4 Cel;
    vec4 ResTime;
    vec4 CamUp;
    vec4 SunDir;
    vec4 MoonDir;
    vec4 LightDir;
    vec4 Zen;
    vec4 Mid;
    vec4 Hor;
    vec4 Haze;
    vec4 Glow;
    vec4 BeltCol;
    vec4 SunCol;
    vec4 MoonCol;
    vec4 CloudLit;
    vec4 CloudShade;
    vec4 CloudRim;
    vec4 CloudOff;
    vec4 Bank;
    vec4 Light;
    vec4 StarCol;
    vec4 Stars;
    vec4 Neb1;
    vec4 Neb2;
    vec4 Aur1;
    vec4 Aur2;
    vec4 Misc;
};

in vec2 texCoord;
out vec4 fragColor;

#define uNoise Noise
#define uCel mat3(Cel)
#define uRes ResTime.xy
#define uTime ResTime.z
#define uCloudOct int(ResTime.w + 0.5)
#define uCamU CamUp.xyz
#define uTanHalfFov CamUp.w
#define uSunDir SunDir.xyz
#define uSunSize SunDir.w
#define uMoonDir MoonDir.xyz
#define uMoonSize MoonDir.w
#define uLightDir LightDir.xyz
#define uMoonPhase LightDir.w
#define uZen Zen.rgb
#define uGlowAmt Zen.w
#define uMid Mid.rgb
#define uBelt Mid.w
#define uHor Hor.rgb
#define uBands Hor.w
#define uHaze Haze.rgb
#define uBandSoft Haze.w
#define uGlow Glow.rgb
#define uBrush Glow.w
#define uBeltCol BeltCol.rgb
#define uGrain BeltCol.w
#define uSunCol SunCol.rgb
#define uSat SunCol.w
#define uMoonCol MoonCol.rgb
#define uBright MoonCol.w
#define uCloudLit CloudLit.rgb
#define uCover CloudLit.w
#define uCloudShade CloudShade.rgb
#define uCloudScale CloudShade.w
#define uCloudRim CloudRim.rgb
#define uCloudSoft CloudRim.w
#define uCloudOff CloudOff.xy
#define uCirrus CloudOff.z
#define uCloudsOn CloudOff.w
#define uBankOn Bank.x
#define uBankHeight Bank.y
#define uBankCover Bank.z
#define uHalo Bank.w
#define uSunUp Light.x
#define uMoonUp Light.y
#define uNight Light.z
#define uAuroraW Light.w
#define uStarCol StarCol.rgb
#define uStarsOn StarCol.w
#define uStarDensity Stars.x
#define uStarBright Stars.y
#define uTwinkle Stars.z
#define uSparkle Stars.w
#define uNeb1 Neb1.rgb
#define uRiverOn Neb1.w
#define uNeb2 Neb2.rgb
#define uRiver Neb2.w
#define uAur1 Aur1.rgb
#define uAuroraOn Aur1.w
#define uAur2 Aur2.rgb
#define uAurora Aur2.w
#define uAuroraSpeed Misc.x
#define uMeteorsOn Misc.y
#define uMeteorRate Misc.z

// smoothstep with edge0 > edge1 is undefined in GLSL, this is the same curve run backwards
float rstep(float a, float b, float x) {
    return 1.0 - smoothstep(b, a, x);
}

const float PI = 3.14159265;

float hash12(vec2 p) {
    vec3 p3 = fract(vec3(p.xyx) * 0.1031);
    p3 += dot(p3, p3.yzx + 33.33);
    return fract((p3.x + p3.y) * p3.z);
}
vec3 hash33(vec3 p3) {
    p3 = fract(p3 * vec3(0.1031, 0.1030, 0.0973));
    p3 += dot(p3, p3.yxz + 33.33);
    return fract((p3.xxy + p3.yxx) * p3.zyx);
}

// smoothstep fed into the hardware bilinear filter, 1 fetch instead of 4 hashes
float noise(vec2 p) {
    vec2 i = floor(p), f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    return textureLod(uNoise, (i + f + 0.5) / 256.0, 0.0).r;
}
float vnoise3(vec3 p) {
    vec3 i = floor(p), f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    vec2 uv = i.xy + vec2(37.0, 17.0) * i.z + f.xy;
    vec2 rg = textureLod(uNoise, (uv + 0.5) / 256.0, 0.0).rg;
    return mix(rg.r, rg.g, f.z);
}
float fbm(vec2 p, int oct) {
    float s = 0.0, a = 0.5;
    for (int i = 0; i < oct; i++) {
        s += a * noise(p);
        p = mat2(1.6, 1.2, -1.2, 1.6) * p;
        a *= 0.5;
    }
    return s;
}
float fbm3(vec3 p, int oct) {
    float s = 0.0, a = 0.5;
    for (int i = 0; i < oct; i++) {
        s += a * vnoise3(p);
        p = p * 2.03 + vec3(1.7, 9.2, 3.1);
        a *= 0.5;
    }
    return s;
}

// posterise into n steps, soft = 1 gives the plain ramp back
float band(float x, float n, float soft) {
    float y = x * n;
    float f = fract(y);
    float w = max(soft, 0.02) * 0.5;
    return (floor(y) + mix(smoothstep(0.5 - w, 0.5 + w, f), f, soft * soft)) / n;
}

vec3 sky(vec3 rd, vec3 l) {
    float y = max(rd.y, 0.0);
    // squashed in y so the strokes run sideways like a flat brush
    float b = (vnoise3(vec3(rd.xz * 3.0, y * 26.0)) - 0.5) * 0.10
            + (vnoise3(vec3(rd.xz * 11.0, y * 70.0) + 5.0) - 0.5) * 0.04;
    b *= uBrush;
    float t = band(clamp(sqrt(y) + b, 0.0, 1.0), uBands, uBandSoft);
    vec3 c = mix(uHor, uMid, smoothstep(0.0, 0.5, t));
    c = mix(c, uZen, smoothstep(0.4, 1.0, t));

    vec2 h = rd.xz / max(length(rd.xz), 1e-4);
    vec2 lh = l.xz / max(length(l.xz), 1e-4);
    float g = pow(0.5 + 0.5 * dot(rd, l), 5.0) * (0.3 + 0.7 * exp(-y * 5.0));
    g = band(clamp(g + b * 0.6, 0.0, 1.0), uBands * 0.75, uBandSoft);
    c = mix(c, uGlow, g * uGlowAmt);

    float belt = max(-dot(h, lh), 0.0) * exp(-((y - 0.07) * (y - 0.07)) / 0.0036) * uBelt;
    return mix(c, uBeltCol, band(belt, 3.0, uBandSoft) * 0.6);
}

vec3 starLayer(vec3 d, vec3 up, float scale, float prob, float pix, float seed, float spark) {
    vec3 c = floor(d * scale);
    vec3 h = hash33(c + seed);
    if (h.x > prob) return vec3(0.0);
    // kept off the cell walls so the sparkle arms never get clipped
    vec3 sp = normalize(c + 0.3 + 0.4 * hash33(c + seed + 7.31));
    vec3 dv = d - sp;
    float edge = 0.3 / scale;
    float big = pow(h.y, 8.0);
    float tw = 1.0 + uTwinkle * (noise(vec2(uTime * 1.9 + h.z * 91.0, h.y * 37.0)) * 2.0 - 1.0);
    float sz = pix * (0.9 + 2.2 * big) * max(tw, 0.3);
    float r = length(dv);
    float s = rstep(sz, sz * 0.35, r);
    if (spark > 0.0 && big > 0.05) {
        vec3 u = normalize(cross(sp, up)), v = cross(u, sp);
        float x = abs(dot(dv, u)), y = abs(dot(dv, v));
        float len = min(sz * 2.5, edge * 0.3), w = pix * 0.6;
        s += spark * big * max(exp(-x / len - y * y / (w * w)), exp(-y / len - x * x / (w * w)));
    }
    vec3 col = mix(vec3(1.0), uStarCol, 0.4 + 0.6 * h.z);
    return col * s * (0.4 + 0.6 * sqrt(h.y)) * rstep(edge, edge * 0.7, r);
}

vec4 river(vec3 d) {
    const vec3 N = vec3(0.0, 0.6, 0.8);
    const vec3 GC = vec3(-0.6, 0.64, -0.48);
    float x = dot(d, N);
    float w = 0.15 + 0.1 * max(dot(d, GC), 0.0);
    float bnd = exp(-x * x / (w * w));
    if (bnd < 0.01) return vec4(0.0);
    vec3 bp = vec3(dot(d, GC), dot(d, cross(N, GC)), x);
    float n = fbm3(bp * 3.0 + 1.0, 3);
    // long strokes along the band, fast change across it
    float st = vnoise3(vec3(bp.xy * 2.5, x * 34.0));
    float lane = smoothstep(0.52, 0.66, n) * exp(-x * x / (0.12 * w * w));
    float i = bnd * (0.35 + 1.1 * n) * (0.7 + 0.6 * st) * (1.0 - 0.75 * lane);
    i = band(clamp(i, 0.0, 1.0), 4.0, uBandSoft);
    vec3 c = mix(uNeb1, uNeb2, smoothstep(0.35, 0.7, n + (st - 0.5) * 0.4));
    return vec4(c, i * bnd);
}

vec3 aurora(vec3 rd) {
    vec2 h = rd.xz / max(length(rd.xz), 1e-4);
    // curtains hang to the north (-z)
    float north = smoothstep(-0.3, 0.6, -h.y);
    if (north <= 0.0) return vec3(0.0);
    float phi = atan(h.y, h.x);
    float tm = uTime * uAuroraSpeed;
    vec3 acc = vec3(0.0);
    for (int k = 0; k < 2; k++) {
        float fk = float(k);
        vec2 q = h * (1.6 + fk * 0.9) + vec2(tm * 0.03, fk * 7.0);
        float base = 0.06 + 0.07 * fk + 0.1 * noise(q) + 0.025 * sin(phi * (4.0 + 3.0 * fk) + tm * 0.2);
        float tall = 0.16 + 0.16 * noise(q * 1.9 + 4.0);
        float v = (rd.y - base) / tall;
        if (v < -0.1 || v > 2.5) continue;
        float cur = smoothstep(-0.05, 0.08, v) * exp(-max(v, 0.0) * 1.7);
        float rays = noise(h * (38.0 + 21.0 * fk) + vec2(tm * 0.25, 3.0 * fk));
        rays = 0.3 + 1.1 * rays * rays;
        float I = band(clamp(cur * rays, 0.0, 1.0), 4.0, uBandSoft);
        acc += mix(uAur1, uAur2, smoothstep(0.1, 1.3, v)) * I * (1.0 - 0.35 * fk);
    }
    return acc * north;
}

vec3 meteors(vec3 rd, float pix) {
    vec3 acc = vec3(0.0);
    float period = mix(40.0, 1.5, uMeteorRate);
    for (int k = 0; k < 2; k++) {
        float tt = uTime / period + float(k) * 0.5;
        float id = floor(tt) + float(k) * 913.0;
        float s = fract(tt) * period / 0.9;
        if (s > 1.0) continue;
        vec3 h = hash33(vec3(id, 3.1, 7.7));
        if (h.x > 0.75) continue;
        float az = h.y * 2.0 * PI, el = mix(0.35, 1.1, h.z);
        vec3 a = vec3(cos(az) * cos(el), sin(el), sin(az) * cos(el));
        vec3 e1 = normalize(cross(a, vec3(0.0, 1.0, 0.0)));
        vec3 e2 = cross(e1, a);
        float ang = hash12(vec2(id, 1.3)) * 2.0 * PI;
        vec3 dir = normalize(e1 * cos(ang) - e2 * (0.4 + 0.6 * abs(sin(ang))));
        float dist = abs(dot(rd, cross(a, dir)));
        float behind = s * 0.4 - atan(dot(rd, dir), dot(rd, a));
        float trail = clamp(1.0 - behind / 0.25, 0.0, 1.0) * smoothstep(-2.0 * pix, 0.0, behind);
        float w = pix * (0.6 + 0.9 * trail);
        acc += mix(uStarCol, vec3(1.0), 0.5) * trail * trail * rstep(w, w * 0.4, dist) * sin(PI * s) * 1.6;
    }
    return acc;
}

vec3 sunDisc(vec3 rd, vec3 col, float pix) {
    float cs = dot(rd, uSunDir);
    if (cs < 0.5) return col;
    float r = radians(uSunSize) * 0.5;
    float x = acos(min(cs, 1.0)) / r;
    float halo = band(exp(-max(x - 1.0, 0.0) * 0.16), 5.0, 0.3) * smoothstep(1.0, 1.3, x);
    col = mix(col, mix(uSunCol, uGlow, 0.45), halo * 0.5 * uHalo * uSunUp);
    vec3 disc = mix(uSunCol, vec3(1.0, 0.99, 0.95), 0.7 * (1.0 - x * x));
    return mix(col, disc, rstep(1.0, 1.0 - 1.5 * pix / r, x));
}

vec3 moonDisc(vec3 rd, vec3 col, float pix) {
    vec3 m = uMoonDir;
    float cm = dot(rd, m);
    if (cm < 0.5) return col;
    float r = radians(uMoonSize) * 0.5;
    float x = acos(min(cm, 1.0)) / r;
    float illum = 0.5 + 0.5 * cos(uMoonPhase * 2.0 * PI);
    float halo = band(exp(-max(x - 1.0, 0.0) * 0.22), 4.0, 0.35) * smoothstep(1.0, 1.25, x);
    col = mix(col, mix(uMoonCol, uMid, 0.35), halo * 0.4 * uHalo * uMoonUp * (0.3 + 0.7 * illum));

    vec3 mr = normalize(cross(m, Cel[2].xyz));
    vec3 mu = cross(mr, m);
    vec2 uv = vec2(dot(rd, mr), dot(rd, mu)) / r;
    if (dot(uv, uv) > 1.1) return col;
    float ph = fract(uMoonPhase);
    // phase as a second disc sliding across, gives the flat painted crescent
    float off = (1.0 + cos(ph * 2.0 * PI)) * 1.1 * (ph < 0.5 ? 1.0 : -1.0);
    float lit = smoothstep(0.96, 1.06, length(uv - vec2(off, 0.0)));
    float maria = smoothstep(0.52, 0.6, fbm(uv * 1.4 + 3.7, 3));
    vec3 face = uMoonCol * (1.0 - 0.14 * maria);
    vec3 dark = mix(col, uMoonCol, 0.08) * 0.8;
    return mix(col, mix(dark, face, lit), rstep(1.0, 1.0 - 1.5 * pix / r, length(uv)));
}

float cloudField(vec2 p, int oct) {
    float s = noise(p * 0.7) * 0.6;
    float a = 0.36;
    p *= 1.9;
    // abs octaves: round bumps, sharp creases, the cauliflower look
    for (int i = 1; i < oct; i++) {
        s += a * abs(noise(p) * 2.0 - 1.0);
        p = mat2(1.6, 1.2, -1.2, 1.6) * p + 3.1;
        a *= 0.5;
    }
    return s;
}

vec4 clouds(vec3 rd, vec3 l) {
    vec2 p = rd.xz / (rd.y + 0.1) * uCloudScale;
    p += (vec2(noise(p * 0.35 + 1.3), noise(p * 0.35 + 7.9)) - 0.5) * 0.9 + uCloudOff;
    float d = cloudField(p, uCloudOct) + (noise(p * 6.0 + 9.0) - 0.5) * 0.05 * uBrush;
    float thr = mix(0.62, 0.28, uCover);
    float a = smoothstep(thr, thr + 0.015 + 0.12 * uCloudSoft, d) * smoothstep(0.03, 0.2, rd.y);
    if (a <= 0.0) return vec4(0.0);
    // one tap toward the light, sun-facing edges come out positive
    float d2 = cloudField(p + l.xz * 0.45, uCloudOct);
    float inner = clamp((d - thr) / 0.35, 0.0, 1.0);
    float L = 0.7 + (d - d2) * 4.0 - inner * 0.3 + 0.15 * max(l.y, 0.0);
    vec3 c = mix(uCloudShade, uCloudLit, band(clamp(L, 0.0, 1.0), 3.0, uBandSoft));
    float fwd = pow(max(dot(rd, l), 0.0), 4.0);
    float rim = (1.0 - smoothstep(0.0, 0.08, d - thr)) * (0.3 + 1.2 * fwd) * smoothstep(0.0, 0.03, d - d2);
    c = mix(c, uCloudRim, clamp(rim, 0.0, 1.0) * 0.8);
    c = mix(c, uHor, (1.0 - smoothstep(0.0, 0.4, rd.y)) * 0.45);
    return vec4(c, a);
}

vec3 cirrus(vec3 rd, vec3 col) {
    vec2 q = rd.xz / (rd.y + 0.05) * 0.25 + uCloudOff * 0.15;
    float s = noise(vec2(q.x * 0.6, q.y * 7.0));
    float ci = smoothstep(0.55, 0.9, s) * smoothstep(0.35, 0.7, noise(q * 1.3 + 4.0)) * uCirrus;
    ci = band(ci, 3.0, uBandSoft) * smoothstep(0.0, 0.15, rd.y);
    return mix(col, mix(uCloudLit, uCloudRim, 0.4), ci * 0.7);
}

float bankLo(vec2 h, vec2 o) {
    return noise(h * 1.7 + o) - 1.05 + uBankCover + 0.32 * abs(noise(h * 5.0 + o * 1.7) * 2.0 - 1.0);
}
float bankH(vec2 h, float seed) {
    vec2 o = vec2(uTime * 0.004 + seed, seed);
    float s = bankLo(h, o);
    s += 0.14 * abs(noise(h * 12.0 + o * 2.3) * 2.0 - 1.0);
    s += 0.06 * abs(noise(h * 29.0 + o * 3.1) * 2.0 - 1.0);
    return s * uBankHeight;
}

// towering cumulus sat on the horizon, silhouette from azimuth so it wraps cleanly
vec4 bank(vec3 rd, vec3 l, float seed, float scale, float pix) {
    vec2 h = rd.xz / max(length(rd.xz), 1e-4);
    float e = rd.y / scale;
    float H = bankH(h, seed);
    float a = smoothstep(0.0, max(1.5 * pix / scale, 0.002 + 0.01 * uCloudSoft), H - e);
    if (a <= 0.0) return vec4(0.0);
    vec2 lh = l.xz / max(length(l.xz), 1e-4);
    vec2 t = vec2(-h.y, h.x);
    // slope off the low octaves only, the fine ones turn into vertical stripes once posterised
    vec2 o = vec2(uTime * 0.004 + seed, seed);
    float slope = clamp((bankLo(h, o) - bankLo(h + t * sign(dot(t, lh)) * 0.05, o)) * 12.0, -1.5, 1.5);
    float facing = dot(h, lh);
    float v = clamp(e / max(H, 1e-3), 0.0, 1.0);
    float puff = abs(vnoise3(vec3(h * 9.0 + seed, e * 60.0)) * 2.0 - 1.0);
    float L = 0.45 + slope * 0.3 * (1.0 - 0.5 * abs(facing)) + (v - 0.5) * 0.55 - facing * 0.15 + (puff - 0.3) * 0.35;
    vec3 c = mix(uCloudShade, uCloudLit, band(clamp(L, 0.0, 1.0), 3.0, uBandSoft));
    float edge = 1.0 - smoothstep(0.0, 0.015, H - e);
    c = mix(c, uCloudRim, edge * clamp(0.25 + facing * 0.9, 0.0, 1.0) * 0.85);
    c = mix(c, uHor, rstep(0.04, 0.0, e) * 0.6);
    return vec4(c, a);
}

void main() {
    // two points down the pixel's ray, the difference cancels any translation bobbing put in the projection
    vec2 ndc = texCoord * 2.0 - 1.0;
    vec4 pn = InvViewProj * vec4(ndc, -1.0, 1.0);
    vec4 pf = InvViewProj * vec4(ndc, 1.0, 1.0);
    vec3 rd = normalize(pf.xyz / pf.w - pn.xyz / pn.w);
    float pix = 2.0 * uTanHalfFov / uRes.y;
    vec3 l = uLightDir;
    vec3 col = sky(rd, uSunDir);

    if (rd.y > -0.02) {
        if (uNight > 0.0) {
            vec3 cd = rd * uCel;
            float bnd = 0.0;
            if (uRiverOn > 0.5) {
                vec4 rv = river(cd);
                col = mix(col, rv.rgb, clamp(rv.a * 0.6 * uRiver, 0.0, 1.0) * uNight);
                bnd = rv.a;
            }
            if (uStarsOn > 0.5) {
                vec3 up = uCamU * uCel;
                vec3 st = starLayer(cd, up, 38.0, uStarDensity * 0.16, pix, 0.0, uSparkle)
                        + starLayer(cd, up, 105.0, uStarDensity * 0.1 * (1.0 + 2.5 * bnd), pix, 41.0, 0.0);
                col += st * uStarBright * uNight;
            }
            if (uMeteorsOn > 0.5 && rd.y > 0.0)
                col += meteors(rd, pix) * uNight;
        }
        if (uAuroraOn > 0.5 && uAuroraW > 0.0 && rd.y > 0.0) {
            vec3 a = clamp(aurora(rd) * uAurora * uAuroraW, 0.0, 1.0);
            col = 1.0 - (1.0 - clamp(col, 0.0, 1.0)) * (1.0 - a);
        }
        col = sunDisc(rd, col, pix);
        col = moonDisc(rd, col, pix);
        if (uCloudsOn > 0.5 && rd.y > 0.0) {
            col = cirrus(rd, col);
            if (uCover > 0.01) {
                vec4 c = clouds(rd, l);
                col = mix(col, c.rgb, c.a);
            }
        }
        if (uBankOn > 0.5 && rd.y < uBankHeight * 1.3 + 0.02) {
            vec4 f = bank(rd, l, 3.0, 0.6, pix);
            col = mix(col, mix(f.rgb, uHor, 0.45), f.a);
            vec4 n = bank(rd, l, 11.0, 1.0, pix);
            col = mix(col, n.rgb, n.a);
        }
    }

    col = mix(col, uHor, exp(-max(rd.y, 0.0) * 40.0) * 0.35);
    // stays on the horizon colour just under the line, that is the fog colour, so far terrain has no seam
    float gy = max(-rd.y, 0.0);
    vec3 ground = mix(uHor, uHaze, smoothstep(0.02, 0.3, gy));
    col = mix(col, ground, smoothstep(0.0, 0.012, gy));

    col = mix(vec3(dot(col, vec3(0.299, 0.587, 0.114))), col, uSat) * uBright;
    float paper = textureLod(uNoise, gl_FragCoord.xy / 256.0, 0.0).b * 0.6 + textureLod(uNoise, gl_FragCoord.xy / 1024.0, 0.0).a * 0.4;
    col *= 1.0 + (paper - 0.5) * 0.08 * uGrain;
    col += (hash12(gl_FragCoord.xy) - 0.5) / 255.0;
    fragColor = vec4(clamp(col, 0.0, 1.0), 1.0);
}
