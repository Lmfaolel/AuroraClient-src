package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.ambience.AmbienceColors;
import dev.quelchi.aurora.ambience.AmbienceDimensions;
import dev.quelchi.aurora.ambience.SkyFogState;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.render.SkyExtras;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.SkyRendering;
import net.minecraft.client.render.state.SkyRenderState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.MoonPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SkyRendering.class})
public abstract class AmbienceSkyRenderingMixin {
    @Inject(method={"method_74926"}, at={@At(value="RETURN")})
    private void aurora$overrideSkyState(ClientWorld clientWorld, float f, Camera camera, SkyRenderState skyRenderState, CallbackInfo callbackInfo) {
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule == null) {
            return;
        }
        if (!ambienceModule.isEnabled()) {
            SkyFogState.reset();
            return;
        }
        if (ambienceModule.stars()) {
            skyRenderState.field_63094 = 0.0f;
        }
        SkyExtras.sunAngle = skyRenderState.field_63091;
        SkyExtras.moonAngle = skyRenderState.field_64465;
        if (ambienceModule.sunMoon()) {
            switch (ambienceModule.moonPhase().name()) {
                case "Full": {
                    skyRenderState.field_63096 = MoonPhase.field_63425;
                    break;
                }
                case "Gibbous": {
                    skyRenderState.field_63096 = MoonPhase.field_63432;
                    break;
                }
                case "Half": {
                    skyRenderState.field_63096 = MoonPhase.field_63431;
                    break;
                }
                case "Crescent": {
                    skyRenderState.field_63096 = MoonPhase.field_63430;
                    break;
                }
                case "New": {
                    skyRenderState.field_63096 = MoonPhase.field_63429;
                    break;
                }
            }
        }
        if (AmbienceDimensions.isOverworldLike(clientWorld)) {
            if (ambienceModule.overworldSky()) {
                skyRenderState.field_63097 = AmbienceColors.withFullAlpha(ambienceModule.overworldSkyRgb());
            }
            if (ambienceModule.skyGradient()) {
                int n = skyRenderState.field_63095 & 0xFF000000;
                if (n == 0) {
                    n = -16777216;
                }
                skyRenderState.field_63095 = n | ambienceModule.gradientRgb() & 0xFFFFFF;
                if (SkyFogState.isSkyFogSuppressed()) {
                    skyRenderState.field_63097 = AmbienceColors.lerpArgb(skyRenderState.field_63097, AmbienceColors.withFullAlpha(ambienceModule.gradientRgb()), 0.55f);
                }
            }
            if (ambienceModule.overworldSky() || ambienceModule.skyGradient()) {
                float f2 = (float)ambienceModule.gradientIntensity() / 16.0f;
                skyRenderState.field_63097 = AmbienceColors.scaleRgb(skyRenderState.field_63097, f2);
                skyRenderState.field_63095 = AmbienceColors.scaleRgb(skyRenderState.field_63095, f2);
            }
        } else if (AmbienceDimensions.isNether(clientWorld) && ambienceModule.netherSky()) {
            SkyFogState.reset();
            skyRenderState.field_63097 = AmbienceColors.withFullAlpha(ambienceModule.netherRgb());
        } else if (AmbienceDimensions.isEnd(clientWorld) && ambienceModule.endSky()) {
            SkyFogState.reset();
            skyRenderState.field_63097 = AmbienceColors.withFullAlpha(ambienceModule.endRgb());
        } else {
            SkyFogState.reset();
        }
    }
}
