#version 330

// The 1.21.8 lightmap.fsh, verbatim below the uniform block. The block is the
// 1.21.11 one because vanilla fills it, so the two inputs 1.21.9 removed are
// derived from what it still sends:
//
//   UseBrightLightmap  was true in the End only. The End is now the one
//                      dimension whose AmbientColor has a channel above 1
//                      (0.99, 1.12, 1.0, the same constant the old bright
//                      branch mixed toward), so that is the test.
//   AmbientLightFactor was 0.1 in the Nether and 0.0 everywhere else, and it
//                      lifted the light CURVE, not the colour. The End now
//                      carries 0.25 in the same field to drive the ambient
//                      mix, so the curve lift ignores it there.
//
// SkyFactor and SkyLightColor are the 1.21.8 values as well, computed in
// NostalgiaModule from time of day, rain, thunder and lightning the way
// ClientWorld.getSkyBrightness did, and put into the block by the mixin.
layout(std140) uniform LightmapInfo {
    float AmbientLightFactor;
    float SkyFactor;
    float BlockFactor;
    float NightVisionFactor;
    float DarknessScale;
    float DarkenWorldFactor;
    float BrightnessFactor;
    vec3 SkyLightColor;
    vec3 AmbientColor;
} lightmapInfo;

in vec2 texCoord;

out vec4 fragColor;

float get_brightness(float level, float ambient) {
    float curved_level = level / (4.0 - 3.0 * level);
    return mix(curved_level, 1.0, ambient);
}

// Each channel lifted on its own, which desaturates toward white as light
// gets brighter. 1.21.9 scales the whole colour by its brightest channel
// instead, keeping the R:G:B ratio, which is the "warmer and darker" look.
vec3 notGamma(vec3 x) {
    vec3 nx = 1.0 - x;
    return 1.0 - nx * nx * nx * nx;
}

void main() {
    bool bright = lightmapInfo.AmbientColor.g > 1.0;
    float ambient = bright ? 0.0 : lightmapInfo.AmbientLightFactor;

    float block_brightness = get_brightness(floor(texCoord.x * 16) / 15, ambient) * lightmapInfo.BlockFactor;
    float sky_brightness = get_brightness(floor(texCoord.y * 16) / 15, ambient) * lightmapInfo.SkyFactor;

    // cubic nonsense, dips to yellowish in the middle, white when fully saturated
    vec3 color = vec3(
        block_brightness,
        block_brightness * ((block_brightness * 0.6 + 0.4) * 0.6 + 0.4),
        block_brightness * (block_brightness * block_brightness * 0.6 + 0.4)
    );

    if (bright) {
        color = mix(color, vec3(0.99, 1.12, 1.0), 0.25);
        color = clamp(color, 0.0, 1.0);
    } else {
        color += lightmapInfo.SkyLightColor * sky_brightness;
        color = mix(color, vec3(0.75), 0.04);

        vec3 darkened_color = color * vec3(0.7, 0.6, 0.6);
        color = mix(color, darkened_color, lightmapInfo.DarkenWorldFactor);
    }

    if (lightmapInfo.NightVisionFactor > 0.0) {
        // scale up uniformly until 1.0 is hit by one of the colors
        float max_component = max(color.r, max(color.g, color.b));
        if (max_component < 1.0) {
            vec3 bright_color = color / max_component;
            color = mix(color, bright_color, lightmapInfo.NightVisionFactor);
        }
    }

    if (!bright) {
        color = clamp(color - vec3(lightmapInfo.DarknessScale), 0.0, 1.0);
    }

    vec3 notGamma = notGamma(color);
    color = mix(color, notGamma, lightmapInfo.BrightnessFactor);
    color = mix(color, vec3(0.75), 0.04);
    color = clamp(color, 0.0, 1.0);

    fragColor = vec4(color, 1.0);
}
