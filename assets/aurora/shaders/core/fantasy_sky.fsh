#version 150

// The fantasy sky, ported from the browser prototype the owner signed off. One full screen pass at the sky
// stage, before terrain. World space is Minecraft's (x east, y up, z south) and the prototype used the
// same axes, so directions carry over untouched. Scalars ride in the w of each vec4 and the defines below
// keep the prototype's uniform names so its code stays as tuned. Zen and the eye adaptation are the same
// for every pixel, so the CPU works them out once.

uniform sampler2D Noise;

layout(std140) uniform SkyParams {
    mat4 InvViewProj;
    mat4 Cel;
    vec4 ResTime;
    vec4 Zen;
    vec4 SunDir;
    vec4 SunCol;
    vec4 NightZen;
    vec4 NightHor;
    vec4 Grade;
    vec4 Stars;
    vec4 NebN;
    vec4 Neb;
    vec4 NebCol1;
    vec4 NebCol2;
    vec4 NebCol3;
    vec4 PlanetPos;
    vec4 PlanetAxis;
    vec4 PlanetCol1;
    vec4 PlanetCol2;
    vec4 PlanetAtmo;
    vec4 BodyL;
    vec4 RingCol;
    vec4 Ring;
    vec4 MoonPos;
    vec4 MoonCol;
    vec4 Aurora;
    vec4 AuroraDir;
    vec4 AuroraCol1;
    vec4 AuroraCol2;
    vec4 AuroraCol3;
    vec4 Ground;
    vec4 FogCol;
};

in vec2 texCoord;
out vec4 fragColor;

#define uNoise Noise
#define uCel mat3(Cel)
#define uRes ResTime.xy
#define uTime ResTime.z
#define uTanHalfFov ResTime.w
#define uZen Zen.rgb
#define uSunI Zen.w
#define uSunDir SunDir.xyz
#define uSunSize SunDir.w
#define uSunCol SunCol.rgb
#define uAtmoOn SunCol.w
#define uNightZen NightZen.rgb
#define uTurbidity NightZen.w
#define uNightHor NightHor.rgb
#define uHue NightHor.w
#define uSaturation Grade.x
#define uExposure Grade.y
#define uHorizonGlow Grade.z
#define uStarsOn Stars.x
#define uStarDensity Stars.y
#define uStarBright Stars.z
#define uTwinkle Stars.w
#define uNebN NebN.xyz
#define uNebOn NebN.w
#define uNebBright Neb.x
#define uNebScale Neb.y
#define uNebWidth Neb.z
#define uNebDust Neb.w
#define uNebCol1 NebCol1.rgb
#define uNebWash NebCol1.w
#define uNebCol2 NebCol2.rgb
#define uNebCol3 NebCol3.rgb
#define uPlanetPos PlanetPos.xyz
#define uPlanetOn PlanetPos.w
#define uPlanetAxis PlanetAxis.xyz
#define uPlanetR PlanetAxis.w
#define uPlanetCol1 PlanetCol1.rgb
#define uPlanetBands PlanetCol1.w
#define uPlanetCol2 PlanetCol2.rgb
#define uBanding PlanetCol2.w
#define uPlanetAtmo PlanetAtmo.rgb
#define uAtmoGlow PlanetAtmo.w
#define uBodyL BodyL.xyz
#define uPlanetBright BodyL.w
#define uRingCol RingCol.rgb
#define uRingsOn RingCol.w
#define uRingIn Ring.x
#define uRingOut Ring.y
#define uRingOpacity Ring.z
#define uMoonPos MoonPos.xyz
#define uMoonOn MoonPos.w
#define uMoonCol MoonCol.rgb
#define uMoonR MoonCol.w
#define uAuroraOn Aurora.x
#define uAuroraStrength Aurora.y
#define uAuroraHeight Aurora.z
#define uAuroraSpeed Aurora.w
#define uAuroraDir AuroraDir.xy
#define uAuroraSpread AuroraDir.z
#define uAuroraStorm AuroraDir.w
#define uAuroraCol1 AuroraCol1.rgb
#define uMeteorsOn AuroraCol1.w
#define uAuroraCol2 AuroraCol2.rgb
#define uMeteorRate AuroraCol2.w
#define uAuroraCol3 AuroraCol3.rgb

// the prototype's quality knobs, fixed at the values it was tuned with
#define uCamAlt 0.02
#define uAtmoSteps 10
#define uNebOct 4
#define uAuroraSteps 16

const float PI = 3.14159265;
const float RG = 6360.0;
const float RA = 6420.0;
const vec3 BR = vec3(5.802, 13.558, 33.1) * 1e-3;
const float BM_S = 3.996e-3;
const float BM_E = 4.40e-3;
const vec3 BO = vec3(0.650, 1.881, 0.085) * 1e-3;
const float SUN_I = 20.0;

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
// not noise3, desktop glsl still has a builtin by that name
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

vec2 raySphere(vec3 ro, vec3 rd, float r) {
    float b = dot(ro, rd);
    float c = dot(ro, ro) - r * r;
    float d = b * b - c;
    if (d < 0.0) return vec2(-1.0);
    d = sqrt(d);
    return vec2(-b - d, -b + d);
}

// distance along a ray from the camera to altitude h (km), earth curvature included.
// written this way because R^2 - (R+h)^2 in float32 loses everything
float tLayer(float rdy, float h) {
    float b = (RG + uCamAlt) * rdy;
    return -b + sqrt(b * b + (h - uCamAlt) * (2.0 * RG + h + uCamAlt));
}

// schuler's chapman approximation: optical depth to space without a second loop
float chapman(float x, float h, float mu) {
    float c = sqrt(x + h);
    if (mu >= 0.0)
        return c / (c * mu + 1.0) * exp(-h);
    float x0 = sqrt(1.0 - mu * mu) * (x + h);
    return 2.0 * sqrt(x0) * exp(x - x0) - c / (1.0 - c * mu) * exp(-h);
}

vec3 lightTau(vec3 p, vec3 l) {
    float r = length(p);
    float mu = dot(p, l) / r;
    float h = max(r - RG, 0.0);
    float oR = 8.0 * chapman(RG / 8.0, h / 8.0, mu);
    float oM = 1.2 * chapman(RG / 1.2, h / 1.2, mu);
    return BR * oR + BM_E * uTurbidity * oM + BO * oR * 1.875;
}

float sunVis(vec3 p, vec3 l) {
    float r = length(p), h = r - RG;
    return smoothstep(-0.01, 0.01, dot(p, l) / r + sqrt(max(h, 0.0) * (2.0 * RG + h)) / r);
}

float phaseMie(float mu, float g) {
    float g2 = g * g;
    return 3.0 / (8.0 * PI) * ((1.0 - g2) * (1.0 + mu * mu)) / ((2.0 + g2) * pow(1.0 + g2 - 2.0 * g * mu, 1.5));
}

vec3 atmosphere(vec3 rd, vec3 l, float sunI, int n, out vec3 trans) {
    vec3 ro = vec3(0.0, RG + uCamAlt, 0.0);
    float tmax = raySphere(ro, rd, RA).y;
    float mu = dot(rd, l);
    vec3 tau = vec3(0.0), sR = vec3(0.0), sM = vec3(0.0);
    for (int i = 0; i < n; i++) {
        float a = float(i) / float(n), b = float(i + 1) / float(n);
        float t0 = tmax * a * a, t1 = tmax * b * b;
        float ds = t1 - t0;
        vec3 p = ro + rd * (0.5 * (t0 + t1));
        float h = length(p) - RG;
        float dR = exp(-h / 8.0), dM = exp(-h / 1.2), dO = max(0.0, 1.0 - abs(h - 25.0) / 15.0);
        vec3 ext = (BR * dR + BM_E * uTurbidity * dM + BO * dO) * ds;
        tau += ext * 0.5;
        float vis = sunVis(p, l);
        if (vis > 0.0) {
            vec3 att = exp(-(tau + lightTau(p, l))) * vis;
            sR += att * dR * ds;
            sM += att * dM * ds;
        }
        tau += ext * 0.5;
    }
    trans = exp(-tau);
    return sunI * (sR * BR * (3.0 / (16.0 * PI)) * (1.0 + mu * mu) + sM * BM_S * uTurbidity * phaseMie(mu, 0.76));
}

vec3 hueShift(vec3 c, float a) {
    const vec3 k = vec3(0.57735);
    float ca = cos(a);
    return c * ca + cross(k, c) * sin(a) + k * dot(k, c) * (1.0 - ca);
}

float lum(vec3 c) { return dot(c, vec3(0.2126, 0.7152, 0.0722)); }

vec3 blackbody(float t) {
    t /= 100.0;
    vec3 c;
    c.r = t <= 66.0 ? 1.0 : 1.2929 * pow(t - 60.0, -0.1332);
    c.g = t <= 66.0 ? 0.3901 * log(t) - 0.6318 : 1.1298 * pow(t - 60.0, -0.0755);
    c.b = t >= 66.0 ? 1.0 : (t <= 19.0 ? 0.0 : 0.5432 * log(t - 10.0) - 1.1963);
    return pow(clamp(c, 0.0, 1.0), vec3(2.2));
}

vec3 starLayer(vec3 d, float scale, float prob, float flux, float pix, float seed, float horizon) {
    vec3 c = floor(d * scale);
    vec3 h = hash33(c + seed);
    if (h.x > prob) return vec3(0.0);
    // star kept in the middle half of its cell so the gaussian never gets clipped by the cell edge
    vec3 sp = normalize(c + 0.25 + 0.5 * hash33(c + seed + 7.31));
    float a = length(cross(d, sp));
    float b = pow(h.y, 14.0);
    float edge = 0.25 / scale;
    float sz = min(pix * (0.6 + 1.0 * sqrt(b)), edge * 0.35);
    float tw = 1.0 + uTwinkle * (noise(vec2(uTime * 2.7 + h.z * 91.0, h.y * 37.0)) * 2.0 - 1.0) * mix(0.35, 1.0, horizon);
    vec3 col = mix(vec3(1.0), blackbody(3200.0 + 9000.0 * h.z * h.z), 0.75);
    return col * (0.025 + 3.0 * b) * flux * max(tw, 0.0) * exp(-a * a / (sz * sz)) * smoothstep(edge, edge * 0.6, a);
}

// three depths: a soft emission body at the back, ridged filaments in front, dust between that eats
// the back layer and the stars and catches a lit rim where it thins out
vec3 nebula(vec3 d, out vec3 dustT, out float band) {
    dustT = vec3(1.0);
    float x = dot(d, uNebN);
    band = exp(-x * x / (uNebWidth * uNebWidth));
    vec3 p = d * uNebScale;
    float wn = vnoise3(p * 0.55 + 4.0);
    vec3 wash = mix(uNebCol1, uNebCol2, smoothstep(0.3, 0.7, wn)) * wn * wn * uNebWash * (0.03 + 0.05 * band);
    if (band < 0.02) return wash;
    vec3 w = vec3(vnoise3(p * 1.1 + 1.7), vnoise3(p * 1.1 + 8.3), vnoise3(p * 1.1 + 4.1)) - 0.5;
    vec3 q = p + w * 1.5;
    float big = fbm3(q, uNebOct);
    float fil = 1.0 - abs(fbm3(q * 2.3 + 5.0, uNebOct) * 2.0 - 1.0);
    float dust = fbm3(q * 3.2 + 13.0, max(uNebOct - 1, 1));
    float m = band * smoothstep(0.25, 0.7, big + 0.4 * wn - 0.1);
    float cloud = pow(clamp(big * 1.7 - 0.3, 0.0, 1.0), 2.2) * m;
    vec3 back = uNebCol1 * cloud + uNebCol3 * pow(smoothstep(0.5, 0.82, big), 3.0) * m * 1.6;
    vec3 front = mix(uNebCol2, uNebCol3, 0.25) * pow(fil, 7.0) * m * (0.4 + cloud * 2.0);
    float dd = smoothstep(0.42, 0.6, dust) * band * uNebDust;
    dustT = exp(-dd * vec3(2.4, 3.0, 3.8));
    float rim = smoothstep(0.34, 0.44, dust) * (1.0 - smoothstep(0.44, 0.54, dust));
    vec3 lining = (uNebCol1 * 0.6 + uNebCol3 * 0.6) * rim * cloud * uNebDust * 0.8;
    return (back * dustT + front * (0.5 + 0.5 * dustT) + lining) * 0.25 * uNebBright + wash * dustT;
}

// body at unit distance with radius r (sin of the angular radius).
// b = ray-to-centre distance over r, n = surface normal on a hit, the closest-approach vector (length b) on a miss
float sphere(vec3 rd, vec3 c, float r, float pix, out vec3 n, out float b, out float t) {
    float bc = dot(rd, c);
    float d2 = max(1.0 - bc * bc, 0.0);
    b = bc > 0.0 ? sqrt(d2) / r : 1e3;
    t = bc - sqrt(max(r * r - d2, 0.0));
    n = (rd * t - c) / r;
    return bc > 0.0 ? clamp((1.0 - b) * r / pix + 0.5, 0.0, 1.0) : 0.0;
}

// right, up, toward the viewer
mat3 viewFrame(vec3 c) {
    vec3 x = normalize(vec3(-c.z, 0.0, c.x));
    return mat3(x, cross(x, c), -c);
}

// azimuthal-equidistant map of the visible face, so craters squash toward the limb like they should
vec2 faceUV(vec3 fn) {
    float a = acos(clamp(fn.z, -1.0, 1.0));
    return fn.xy * (a / max(length(fn.xy), 1e-4));
}

// returns albedo change, adds the height slope to g. slope is relative to crater size so it works at any scale
float craters(vec2 uv, float sc, inout vec2 g) {
    vec2 p = uv * sc, i = floor(p), f = fract(p);
    float alb = 0.0;
    for (int y = -1; y <= 1; y++)
    for (int x = -1; x <= 1; x++) {
        vec2 o = vec2(x, y);
        vec3 h = hash33(vec3(i + o, sc));
        if (h.z > 0.6) continue;
        vec2 v = f - o - (0.25 + 0.5 * h.xy);
        float rad = 0.12 + 0.3 * h.z;
        float lv = length(v);
        float d = lv / rad;
        if (d > 2.2) continue;
        // bowl, raised rim, apron
        float s = d < 1.0 ? 1.6 * d * d : -1.4 * exp(-(d - 1.0) * 4.0);
        g += v / max(lv, 1e-4) * s * 0.35;
        alb += d < 1.0 ? -0.18 * (1.0 - d * d) + 0.2 * smoothstep(0.75, 1.0, d) : 0.12 * exp(-(d - 1.0) * 5.0);
    }
    return alb;
}

vec3 gasBands(vec3 ln) {
    float t1 = fbm3(ln * vec3(1.3, 1.3, 5.5) + 3.1, 4);
    float t2 = vnoise3(ln * vec3(3.0, 3.0, 28.0) + t1 * 3.0);
    float lat = ln.z + (t1 - 0.47) * 0.3;
    float b = lat * uPlanetBands;
    float stripes = 0.5 + 0.5 * sin(b * 6.2832 + 1.5 * sin(b * 2.1));
    float thin = 0.5 + 0.5 * sin(b * 23.0 + t1 * 7.0);
    stripes = mix(mix(stripes, thin, 0.3), t2, 0.25);
    vec3 col = mix(uPlanetCol2, uPlanetCol1, stripes);
    col = mix(col, mix(uPlanetCol1, vec3(lum(uPlanetCol1)), 0.4) * 0.75, smoothstep(0.78, 0.97, abs(ln.z)));
    vec3 sd = normalize(vec3(0.8, 0.45, -0.32));
    float st = length((ln - sd) * vec3(1.0, 1.0, 2.6)) + (t1 - 0.47) * 0.12;
    col = mix(col, uPlanetCol1 * 1.1, smoothstep(0.24, 0.17, st));
    col = mix(col, uPlanetCol2 * 0.8, smoothstep(0.15, 0.06, st));
    return col * (0.85 + 0.3 * t2);
}

float ringDensity(float x, float fw) {
    if (x <= 0.0 || x >= 1.0) return 0.0;
    // faint inner ring, dense middle ring, gap, outer ring
    float d = 0.35 + 0.65 * smoothstep(0.12, 0.3, x) - 0.3 * smoothstep(0.62, 0.75, x);
    d *= smoothstep(0.0, 0.06, x) * smoothstep(1.0, 0.96, x);
    d *= mix(0.05, 1.0, smoothstep(0.015, 0.035, abs(x - 0.6)));
    d *= mix(0.25, 1.0, smoothstep(0.003, 0.008, abs(x - 0.88)));
    d *= 0.55 + 0.9 * noise(vec2(x * 31.0, 0.5));
    // fine ringlets alias into moire when a pixel spans several, fade them out
    d *= mix(1.0, 0.45 + 1.1 * noise(vec2(x * 170.0, 7.5)), clamp(1.5 - fw * 170.0, 0.0, 1.0));
    return clamp(d, 0.0, 1.0);
}

// drawn as if seen from far away (orthographic about the planet). at true perspective a ring this wide
// sweeps past the camera and reads as a glitch. rp is the disc radius on the plane through the planet
vec4 ringLayer(vec3 rd, vec3 c, float r, float pix, out float behind) {
    vec3 A = uPlanetAxis, L = uBodyL;
    behind = 0.0;
    float k = dot(rd, c);
    if (k < 0.05) return vec4(0.0);
    float rp = r * inversesqrt(1.0 - r * r);
    vec3 g = rd / k - c;
    float ca = dot(c, A);
    float s = -dot(g, A) / ca;
    vec3 h = g + c * s;
    float x = (length(h) / rp - uRingIn) / (uRingOut - uRingIn);
    float fw = pix / (max(abs(ca), 0.02) * rp * (uRingOut - uRingIn));
    float a = clamp(ringDensity(x, fw) * uRingOpacity, 0.0, 0.97);
    if (a <= 0.0) return vec4(0.0);
    float g2 = dot(g, g);
    behind = g2 < rp * rp && s > -sqrt(rp * rp - g2) ? 1.0 : 0.0;
    float bb = dot(h, L);
    float sh = bb < 0.0 ? smoothstep(0.97, 1.03, length(h - L * bb) / rp) : 1.0;
    float la = dot(L, A);
    // lit face toward us: reflects. unlit face: only the thin parts let light through
    float lit = la * -ca > 0.0 ? 0.25 + 0.75 * sqrt(abs(la)) : (1.0 - a) * 0.7 * sqrt(abs(la));
    lit += pow(max(dot(rd, L), 0.0), 10.0) * 1.2 * (1.0 - 0.5 * a);
    vec3 col = uRingCol * (0.7 + 0.6 * noise(vec2(x * 13.0, 3.5)));
    col *= (lit * sh + 0.012) * uPlanetBright;
    return vec4(col * a, a);
}

vec3 shadePlanet(vec3 n, vec3 rd, vec3 c, float r, float tP) {
    vec3 L = uBodyL, A = uPlanetAxis;
    float ndl = dot(n, L);
    float ndv = max(dot(n, -rd), 0.0);
    vec3 alb = vec3(0.0);
    if (uBanding > 0.01) {
        vec3 e1 = normalize(cross(A, c)), e2 = cross(A, e1);
        vec3 ln = vec3(dot(n, e1), dot(n, e2), dot(n, A));
        float s = uTime * 0.004, cs = cos(s), ss = sin(s);
        ln.xy = mat2(cs, ss, -ss, cs) * ln.xy;
        alb += gasBands(ln) * uBanding;
    }
    if (uBanding < 0.99) {
        mat3 F = viewFrame(c);
        vec3 fn = n * F;
        vec2 g = vec2(0.0);
        vec2 fuv = faceUV(fn);
        float cr = craters(fuv, 3.0, g) + craters(fuv, 7.5, g) * 0.6;
        float m = fbm3(fn * 1.7 + 7.0, 4);
        vec3 rock = mix(uPlanetCol2, uPlanetCol1, smoothstep(0.38, 0.58, m)) * (0.85 + 0.3 * vnoise3(fn * 14.0)) * max(1.0 + cr, 0.2);
        alb += rock * (1.0 - uBanding);
        ndl = mix(dot(normalize(n - F * vec3(g, 0.0)), L), ndl, uBanding);
    }
    float sh = 1.0;
    if (uRingsOn > 0.5) {
        vec3 p = rd * tP;
        float tr = dot(c - p, A) / dot(L, A);
        if (tr > 0.0) {
            float x = (length(p + L * tr - c) / (r * inversesqrt(1.0 - r * r)) - uRingIn) / (uRingOut - uRingIn);
            sh = 1.0 - clamp(ringDensity(x, 1.0) * uRingOpacity, 0.0, 0.97) * 0.9;
        }
    }
    // gas giants have a wide terminator, a hard max() reads as a cut when the light is side-on
    float diff = pow(clamp((ndl + 0.1) / 1.1, 0.0, 1.0), 1.4);
    // light bleeding past the terminator through the planet's air, reddened on the way
    float soft = smoothstep(-0.15, 0.25, ndl) * uAtmoGlow;
    vec3 tint = mix(vec3(1.0), mix(vec3(1.0, 0.35, 0.16), vec3(1.0), smoothstep(-0.05, 0.35, ndl)), min(uAtmoGlow, 1.0));
    float limb = mix(1.0, 0.45 + 0.55 * sqrt(ndv), uBanding);
    vec3 lit = alb * (diff * 0.9 + soft * 0.12) * tint * limb * sh;
    float ringshine = uRingsOn > 0.5 ? 0.03 * max(dot(n, A) * sign(dot(L, A)), 0.0) : 0.0;
    vec3 amb = alb * (0.004 + ringshine);
    // rayleigh-ish limb haze on the lit side
    vec3 rim = uPlanetAtmo * pow(1.0 - ndv, 3.0) * smoothstep(-0.2, 0.5, ndl) * uAtmoGlow * 0.6;
    return (lit * 1.15 + amb + rim) * uPlanetBright;
}

vec3 aurora(vec3 rd, float dith) {
    float h0 = uAuroraHeight, h1 = uAuroraHeight * 2.2;
    float t0 = tLayer(rd.y, h0);
    float t1 = min(tLayer(rd.y, h1), t0 + 500.0);
    int n = uAuroraSteps;
    float ds = (t1 - t0) / float(n);
    float tm = uTime * uAuroraSpeed;
    float storm = uAuroraStorm;
    vec3 acc = vec3(0.0);
    for (int i = 0; i < n; i++) {
        float t = t0 + ds * (float(i) + dith);
        vec3 p = rd * t;
        float alt = uCamAlt + t * rd.y + t * t / (2.0 * RG);
        float hf = (alt - h0) / (h1 - h0);
        vec2 q = p.xz / 150.0;
        // oval centre sits along uAuroraDir
        q = vec2(dot(q, vec2(-uAuroraDir.y, uAuroraDir.x)), -dot(q, uAuroraDir));
        float mask = exp(-(q.y + 2.2) * (q.y + 2.2) / uAuroraSpread);
        if (mask < 0.02) continue;
        float w = fbm(q * 0.8 + vec2(tm * 0.03, -tm * 0.012), 2);
        float f = q.y * 1.4 + w * (3.2 + 2.6 * storm) + 0.3 * sin(q.x * 1.7 + tm * 0.15);
        float s = fract(f) - 0.5;
        float sheet = exp(-s * s * mix(45.0, 28.0, storm));
        float bottom = 0.08 * noise(vec2(q.x * 3.0 + w * 2.0, tm * 0.15));
        float vert = smoothstep(bottom - 0.01, bottom + 0.04, hf) * exp(-max(hf - bottom, 0.0) * mix(3.5, 2.2, storm));
        float rays = 0.3 + 0.7 * noise(vec2((q.x + w * 1.5) * 18.0, tm * (0.5 + 1.5 * storm)));
        // storms surge in patches
        float surge = mix(1.0, 0.2 + 2.2 * noise(q * 0.9 + vec2(tm * 0.08, 0.0)), storm);
        vec3 c = mix(uAuroraCol3, uAuroraCol1, smoothstep(0.0, 0.05, hf - bottom));
        c = mix(c, uAuroraCol2, smoothstep(0.1, 0.65, hf));
        acc += c * sheet * vert * rays * rays * mask * surge * ds;
    }
    return acc * exp(-t0 / 1400.0) * uAuroraStrength * 0.012;
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
        float trail = clamp(1.0 - behind / 0.2, 0.0, 1.0) * smoothstep(-2.0 * pix, 0.0, behind);
        float w = pix * (0.45 + 0.6 * trail);
        acc += vec3(0.85, 0.92, 1.0) * trail * trail * exp(-dist * dist / (w * w)) * sin(PI * s) * 2.5;
    }
    return acc;
}

vec3 aces(vec3 x) {
    return clamp((x * (2.51 * x + 0.03)) / (x * (2.43 * x + 0.59) + 0.14), 0.0, 1.0);
}

void main() {
    // two points down the pixel's ray, the difference cancels any translation bobbing put in the projection
    vec2 ndc = texCoord * 2.0 - 1.0;
    vec4 pn = InvViewProj * vec4(ndc, -1.0, 1.0);
    vec4 pf = InvViewProj * vec4(ndc, 1.0, 1.0);
    vec3 rd = normalize(pf.xyz / pf.w - pn.xyz / pn.w);
    // a sky only: under the horizon is the colour terrain fogs into, and the atmosphere isn't marched there
    if (rd.y < -0.04) {
        fragColor = vec4(FogCol.rgb, 1.0);
        return;
    }
    float pix = 2.0 * uTanHalfFov / uRes.y;
    // interleaved gradient noise, spreads the march offset evenly so the aurora doesn't go grainy
    float dith = fract(52.9829189 * fract(dot(gl_FragCoord.xy, vec2(0.06711056, 0.00583715))));
    vec3 l = uSunDir;

    vec3 zen = uZen;
    float sunI = uSunI;
    float night = smoothstep(0.03, -0.18, l.y);
    float dark = smoothstep(0.06, -0.2, l.y);
    // planet over the sun shadows the whole sky, not just the disc. after adaptation so the eye can't undo it
    if (uPlanetOn > 0.5) {
        float sr = radians(uSunSize) * 0.5 / uPlanetR;
        float ecl = dot(l, uPlanetPos) > 0.0 ? smoothstep(1.0 + sr, 1.0 - sr, length(cross(l, uPlanetPos)) / uPlanetR) : 0.0;
        sunI *= 1.0 - 0.985 * ecl;
        night = max(night, ecl * 0.9);
        dark = max(dark, ecl);
    }

    vec3 rs = normalize(vec3(rd.x, max(rd.y, 0.0), rd.z));
    vec3 trans = vec3(1.0);
    vec3 atm = vec3(0.0);
    if (uAtmoOn > 0.5) {
        // sun below -17 deg lights nothing, only the transmittance is needed
        if (l.y > -0.3)
            atm = atmosphere(rs, l, sunI, uAtmoSteps, trans);
        else
            trans = exp(-lightTau(vec3(0.0, RG + uCamAlt, 0.0), rs));
        float toward = dot(rs.xz, l.xz) / (length(rs.xz) * length(l.xz) + 1e-4);
        atm *= 1.0 + uHorizonGlow * exp(-rs.y * 9.0) * (0.3 + 1.2 * pow(0.5 + 0.5 * toward, 4.0));
        atm = max(hueShift(atm * uSunCol, radians(uHue)), 0.0);
        vec3 nightSky = mix(uNightHor, uNightZen, sqrt(rs.y)) + uNightHor * 0.8 * exp(-rs.y * 14.0);
        atm += nightSky * night * 2.0;
    }
    float wash = exp(-lum(atm) * 6.0);

    vec3 col = atm;
    vec3 bodyLight = vec3(0.0);
    vec3 L = uBodyL;
    if (uPlanetOn > 0.5) {
        float phase = 0.5 - 0.5 * dot(uPlanetPos, L);
        bodyLight = mix(uPlanetCol1, uRingCol, 0.3) * uPlanetBright * phase * smoothstep(-uPlanetR, uPlanetR, uPlanetPos.y);
    }

    float occ = 1.0;
    if (rd.y > -0.02) {
        vec3 far = vec3(0.0);
        float sv = dark * wash;
        if (sv > 0.01) {
            vec3 cd = rd * uCel;
            vec3 dustT = vec3(1.0);
            float band = 0.0;
            if (uNebOn > 0.5)
                far += nebula(cd, dustT, band) * sv;
            if (uStarsOn > 0.5) {
                float horizon = 1.0 - clamp(rd.y * 2.0, 0.0, 1.0);
                vec3 st = starLayer(cd, 55.0, uStarDensity * 0.35 * (1.0 + band), 1.0, pix, 0.0, horizon)
                        + starLayer(cd, 130.0, uStarDensity * 0.45 * (1.0 + 2.0 * band), 0.3, pix, 41.0, horizon);
                far += st * uStarBright * dustT * sv;
            }
        }

        if (uPlanetOn > 0.5) {
            vec3 c = uPlanetPos;
            float r = uPlanetR;
            vec3 n;
            float b, tP;
            float pa = sphere(rd, c, r, pix, n, b, tP);
            vec3 nl = normalize(n);
            if (pa > 0.0)
                far = far * (1.0 - pa) + shadePlanet(b < 1.0 ? n : nl, rd, c, r, tP) * pa;
            occ *= 1.0 - pa;
            float hb = b - 1.0;
            float lightAt = smoothstep(-0.45, 0.3, dot(nl, L));
            // backlit air lights up all the way round
            float back = smoothstep(0.3, 1.0, dot(L, c));
            float g = exp(-abs(hb) / 0.03) * (hb < 0.0 ? 0.45 : 1.0) + (hb > 0.0 ? exp(-hb / 0.3) * 0.07 : 0.0);
            far += uPlanetAtmo * g * lightAt * (1.0 + 4.0 * back) * uAtmoGlow * 0.55 * uPlanetBright;
            if (uRingsOn > 0.5) {
                float behind;
                vec4 rg = ringLayer(rd, c, r, pix, behind);
                rg *= 1.0 - pa * behind;
                far = far * (1.0 - rg.a) + rg.rgb;
                occ *= 1.0 - rg.a;
            }
            // our own air scattering the planet's light
            col += bodyLight * 0.02 * exp(-max(hb, 0.0) * r / 0.35) * night;
        }

        if (uMoonOn > 0.5) {
            vec3 n;
            float b, t;
            float ma = sphere(rd, uMoonPos, uMoonR, pix, n, b, t);
            if (ma > 0.0) {
                mat3 F = viewFrame(uMoonPos);
                vec3 fn = n * F;
                vec2 g = vec2(0.0);
                vec2 fuv = faceUV(fn);
                float cr = craters(fuv, 2.2, g);
                if (uMoonR / pix > 40.0) cr += craters(fuv, 6.0, g) * 0.6;
                float m = fbm3(fn * 1.6 + 2.0, 4);
                vec3 alb = uMoonCol * mix(0.5, 1.0, smoothstep(0.38, 0.6, m)) * (0.9 + 0.2 * vnoise3(fn * 10.0)) * max(1.0 + cr, 0.2);
                float ndl = dot(normalize(n - F * vec3(g, 0.0)), L);
                // dusty surface, no limb darkening, hard-ish terminator
                vec3 mc = alb * (max(ndl, 0.0) * smoothstep(-0.03, 0.08, ndl) * 1.1 + 0.01) + alb * bodyLight * 0.05;
                far = far * (1.0 - ma) + mc * ma;
                occ *= 1.0 - ma;
            }
            float mp = 0.5 - 0.5 * dot(uMoonPos, L);
            far += uMoonCol * mp * (0.25 * exp(-max(b - 1.0, 0.0) * 6.0) + 0.04 * exp(-max(b - 1.0, 0.0) * 0.8)) * 0.4 * (b > 1.0 ? 1.0 : 0.0);
        }

        col += far * trans;

        float sa = length(cross(rd, l));
        float sunR = radians(uSunSize) * 0.5;
        if (dot(rd, l) > 0.0 && sa < sunR * 1.2) {
            float x = sa / sunR;
            vec3 limb = pow(vec3(sqrt(max(0.0, 1.0 - x * x))), vec3(0.4, 0.5, 0.65));
            float tm = max(trans.r, max(trans.g, trans.b));
            // convection cells, only worth it once the disc is big enough to see them
            float gran = uSunSize > 4.0 ? 0.75 + 0.5 * fbm3(rd / sunR * 1.5, 3) : 1.0;
            col += limb * gran * uSunCol * smoothstep(1.0, 1.0 - 1.5 * pix / sunR, x) * (trans / max(tm, 1e-5)) * 3.5 / (1.0 + uSunSize * 0.08) * min(tm * 8.0, 1.0) * occ;
        }

        float aN = smoothstep(0.0, -0.15, l.y) * exp(-lum(atm) * 3.0);
        if (uAuroraOn > 0.5 && rd.y > 0.0 && aN > 0.0)
            col += aurora(rd, dith) * aN * trans * (0.08 + 0.92 * occ);
        if (uMeteorsOn > 0.5 && rd.y > 0.0 && sv > 0.01)
            col += meteors(rd, pix) * sv * trans * occ;
    }

    // aurora glow on the air and ground near its side of the sky
    float aGlow = uAuroraOn > 0.5 ? smoothstep(0.0, -0.15, l.y) * uAuroraStrength * (1.0 + uAuroraStorm) : 0.0;
    float toA = max(dot(normalize(rs.xz + 1e-5), uAuroraDir), 0.0);
    vec3 aLight = uAuroraCol1 * aGlow * 0.006;
    col += aLight * toA * toA * exp(-rs.y * 5.0) * occ;

    col *= uExposure;
    col = max(mix(vec3(lum(col)), col, uSaturation), 0.0);
    col = pow(aces(col), vec3(1.0 / 2.2));
    // triangular dither kills banding in the gradients
    col += (hash12(gl_FragCoord.xy * 1.37 + uTime) + hash12(gl_FragCoord.xy + 17.17 - uTime) - 1.0) / 255.0;
    col = mix(col, FogCol.rgb, smoothstep(0.0, -0.04, rd.y));
    fragColor = vec4(col, 1.0);
}
