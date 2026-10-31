#version 150

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;
uniform sampler2D Sampler2;

in vec2 localPos;
in vec4 tint;
flat in int material;
out vec4 fragColor;

uint bits(int column) {
    uvec4 b = uvec4(round(texelFetch(Sampler2, ivec2(column, material), 0) * 255.0));
    return b.r | (b.g << 8u) | (b.b << 16u) | (b.a << 24u);
}

float value(int column) {
    uint b = bits(column);
    uint exponent = (b >> 23u) & 255u;
    float sign = (b & 2147483648u) == 0u ? 1.0 : -1.0;
    float mantissa = float(b & 8388607u) / 8388608.0;
    return sign * (exponent == 0u ? exp2(-126.0) * mantissa
            : exp2(float(exponent) - 127.0) * (1.0 + mantissa));
}

vec4 color(int column) {
    uint b = bits(column);
    return vec4(float((b >> 16u) & 255u), float((b >> 8u) & 255u),
                float(b & 255u), float(b >> 24u)) / 255.0;
}

vec4 fitRadii(vec2 size, vec4 radii) {
    radii = min(max(radii, 0.0), min(size.x, size.y) * 0.5);
    float scale = 1.0;
    if (radii.x + radii.y > size.x)
        scale = min(scale, size.x / max(radii.x + radii.y, 0.0001));
    if (radii.w + radii.z > size.x)
        scale = min(scale, size.x / max(radii.w + radii.z, 0.0001));
    if (radii.x + radii.w > size.y)
        scale = min(scale, size.y / max(radii.x + radii.w, 0.0001));
    if (radii.y + radii.z > size.y)
        scale = min(scale, size.y / max(radii.y + radii.z, 0.0001));
    return radii * scale;
}

float rounded(vec2 p, vec2 halfSize, vec4 radii) {
    float radius = p.y < 0.0 ? (p.x < 0.0 ? radii.x : radii.y)
            : (p.x > 0.0 ? radii.z : radii.w);
    vec2 q = abs(p) - halfSize + radius;
    return length(max(q, 0.0)) + min(max(q.x, q.y), 0.0) - radius;
}

void over(inout vec4 dst, vec3 rgb, float alpha) {
    float a = clamp(alpha, 0.0, 1.0);
    dst.rgb = rgb * a + dst.rgb * (1.0 - a);
    dst.a = a + dst.a * (1.0 - a);
}

vec2 safeUv(vec2 uv, vec2 size) {
    return clamp(uv, 0.5 / size, 1.0 - 0.5 / size);
}

void main() {
    vec2 size = vec2(value(0), value(1));
    vec2 halfSize = size * 0.5;
    vec2 p = localPos - halfSize;
    mat2 localPerPixel = mat2(dFdx(localPos), dFdy(localPos));
    vec4 radii = fitRadii(size, vec4(value(2), value(22), value(23), value(24)));
    float radius = p.y < 0.0 ? (p.x < 0.0 ? radii.x : radii.y)
            : (p.x > 0.0 ? radii.z : radii.w);
    float d = rounded(p, halfSize, radii);
    float aa = max(fwidth(d), 0.35);
    float coverage = 1.0 - smoothstep(-aa * 0.5, aa * 0.5, d);
    vec4 result = vec4(0.0);
    float shadowSize = value(11);
    if (shadowSize > 0.0) {
        float sd = rounded(p - vec2(0.0, shadowSize * 0.3), halfSize, radii);
        float shadow = exp(-max(sd, 0.0) * max(sd, 0.0) / max(1.0, shadowSize * shadowSize * 0.5));
        vec4 ink = color(10);
        over(result, ink.rgb, ink.a * shadow * (1.0 - coverage));
    }
    vec4 outerGlow = color(13);
    over(result, outerGlow.rgb, outerGlow.a * exp(-max(d, 0.0) / 2.0) * (1.0 - coverage));

    if (coverage > 0.0) {
        float bevel = value(31) > 0.0 ? value(31) : clamp(radius * 0.55, 2.0, 9.0);
        float slope = smoothstep(-bevel, 0.0, d);
        vec2 q = abs(p) - halfSize + radius;
        vec2 gradient = max(q, 0.0) * sign(p);
        if (dot(gradient, gradient) < 0.00001)
            gradient = abs(q.x) < abs(q.y) ? vec2(sign(p.x), 0.0) : vec2(0.0, sign(p.y));
        gradient = normalize(gradient + vec2(0.00001));
        vec3 normal = normalize(vec3(gradient * slope * 1.8, 1.0));
        float fresnel = 0.04 + 0.96 * pow(1.0 - normal.z, 5.0);

        float tintAngle = radians(value(32));
        vec2 axis = vec2(cos(tintAngle), sin(tintAngle));
        vec2 unitPos = localPos / max(size, vec2(1.0)) - 0.5;
        float gradientT = clamp(0.5 + dot(unitPos, axis) / max(abs(axis.x) + abs(axis.y), 0.0001), 0.0, 1.0);
        vec4 tintColor = vec4(mix(tint.rgb, color(28).rgb, gradientT),
                mix(tint.a, color(28).a, gradientT));
        float density = clamp(tintColor.a * value(3), 0.0, 1.0);
        vec3 body = tintColor.rgb;
        float alpha = density;
        if (value(19) > 0.5) {
            vec2 texSize = vec2(textureSize(Sampler0, 0));
            vec2 uv = gl_FragCoord.xy / texSize;
            vec3 ray = refract(vec3(0.0, 0.0, -1.0), normal, 1.0 / 1.5);
            vec2 offset = inverse(localPerPixel) * ray.xy * value(5) * 36.0 / texSize;
            vec2 sampleUv = safeUv(uv + offset, texSize);
            vec2 dispersion = inverse(localPerPixel) * gradient
                    * value(25) * fresnel * slope * 24.0 / texSize;
            vec3 sharp = texture(Sampler0, sampleUv).rgb;
            sharp.r = texture(Sampler0, safeUv(sampleUv + dispersion, texSize)).r;
            sharp.b = texture(Sampler0, safeUv(sampleUv - dispersion, texSize)).b;
            vec3 background = sharp;
            if (value(20) > 0.5) {
                vec3 centerSoft = texture(Sampler1, sampleUv).rgb;
                vec3 soft = centerSoft;
                soft.r = texture(Sampler1, safeUv(sampleUv + dispersion, texSize)).r;
                soft.b = texture(Sampler1, safeUv(sampleUv - dispersion, texSize)).b;
                float spread = value(30);
                if (spread > 0.0) {
                    vec2 stepUv = vec2(spread * value(21) * 12.0) / texSize;
                    vec2 diagonal = stepUv * 0.70710678;
                    vec3 wide = centerSoft * 0.2;
                    wide += texture(Sampler1, safeUv(sampleUv + vec2(stepUv.x, 0.0), texSize)).rgb * 0.13;
                    wide += texture(Sampler1, safeUv(sampleUv - vec2(stepUv.x, 0.0), texSize)).rgb * 0.13;
                    wide += texture(Sampler1, safeUv(sampleUv + vec2(0.0, stepUv.y), texSize)).rgb * 0.13;
                    wide += texture(Sampler1, safeUv(sampleUv - vec2(0.0, stepUv.y), texSize)).rgb * 0.13;
                    wide += texture(Sampler1, safeUv(sampleUv + diagonal, texSize)).rgb * 0.07;
                    wide += texture(Sampler1, safeUv(sampleUv - diagonal, texSize)).rgb * 0.07;
                    wide += texture(Sampler1, safeUv(sampleUv + vec2(diagonal.x, -diagonal.y), texSize)).rgb * 0.07;
                    wide += texture(Sampler1, safeUv(sampleUv + vec2(-diagonal.x, diagonal.y), texSize)).rgb * 0.07;
                    soft = mix(soft, wide, spread);
                }
                background = mix(sharp, soft, value(4));
            }
            float luma = dot(background, vec3(0.2126, 0.7152, 0.0722));
            background = mix(vec3(luma), background, value(6));
            background = (background - 0.5) * value(7) + 0.5;
            body = mix(background, tintColor.rgb, density);
            alpha = 0.92;
        }

        float light = value(14);
        float lightAngle = radians(value(29));
        vec3 key = normalize(vec3(cos(lightAngle) * 0.65, sin(lightAngle) * 0.65, 0.8));
        float roughness = value(27);
        float specPower = mix(64.0, 5.0, roughness);
        float keyDot = max(dot(normal, key), 0.0);
        float specular = pow(keyDot, specPower) * slope * (1.0 - roughness * 0.55);
        specular += pow(keyDot, mix(7.0, 2.0, roughness)) * slope * roughness * 0.18;
        float mouse = 0.0;
        if (value(15) > 0.5) {
            vec2 delta = localPos - vec2(value(16), value(17));
            mouse = exp(-dot(delta, delta) / 1600.0);
            specular += mouse * (0.08 + slope * 0.9);
        }

        vec4 edgeLight = color(33);
        body += edgeLight.rgb * edgeLight.a * light * (specular * 0.9 + fresnel * slope * 0.52);
        vec3 film = 0.5 + 0.5 * cos(6.2831853
                * (vec3(0.0, 0.3333333, 0.6666667) + fresnel * 1.15 + value(29) / 360.0));
        float filmMix = value(26) * clamp(fresnel * 3.5, 0.0, 1.0) * slope;
        body = mix(body, body * (0.72 + film * 0.58), filmMix);
        float grain = fract(sin(dot(floor(localPos * max(value(21), 1.0)),
                vec2(12.9898, 78.233))) * 43758.5453) - 0.5;
        body += grain * value(8) * 0.09;
        over(result, clamp(body, 0.0, 1.0), alpha * coverage);

        vec4 innerGlow = color(12);
        over(result, innerGlow.rgb, innerGlow.a * exp(-abs(d) / 2.5) * coverage);
        vec4 border = color(9);
        float rimWidth = value(31) > 0.0 ? max(0.75, bevel * 0.33) : 1.4;
        float rim = smoothstep(-rimWidth - aa, -0.35, d) * coverage;
        over(result, border.rgb, border.a * rim);
        float upper = max(dot(gradient, normalize(vec2(-0.4, -1.0))), 0.0);
        over(result, edgeLight.rgb, edgeLight.a * rim * light * (0.12 + upper * 0.55 + mouse * 0.2));
    }
    if (result.a < 0.0001)
        discard;
    fragColor = vec4(result.rgb / result.a, result.a * value(18));
}
