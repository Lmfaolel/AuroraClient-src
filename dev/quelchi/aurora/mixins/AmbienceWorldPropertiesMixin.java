package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.HeightLimitView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ClientWorld.Properties.class})
public abstract class AmbienceWorldPropertiesMixin {
    @Inject(method={"method_28105"}, at={@At(value="RETURN")}, cancellable=true)
    private void aurora$overrideSkyDarknessHeight(HeightLimitView heightLimitView, CallbackInfoReturnable<Double> callbackInfoReturnable) {
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule == null || !ambienceModule.isEnabled()) {
            return;
        }
        if (ambienceModule.overworldSky() || ambienceModule.skyGradient()) {
            callbackInfoReturnable.setReturnValue((Object)ambienceModule.gradientHeight());
        }
    }
}
