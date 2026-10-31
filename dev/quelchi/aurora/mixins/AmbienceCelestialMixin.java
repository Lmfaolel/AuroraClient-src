package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import net.minecraft.client.render.SkyRendering;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value={SkyRendering.class})
public abstract class AmbienceCelestialMixin {
    @ModifyConstant(method={"method_62303(FLnet/minecraft/class_4587;)V"}, constant={@Constant(floatValue=30.0f)})
    private float aurora$sunSize(float f) {
        AmbienceModule ambienceModule = AmbienceModule.get();
        return ambienceModule != null && ambienceModule.isEnabled() && ambienceModule.sunMoon() ? f * ambienceModule.sunScale() : f;
    }

    @ModifyConstant(method={"method_62304(Lnet/minecraft/class_12131;FLnet/minecraft/class_4587;)V"}, constant={@Constant(floatValue=20.0f)})
    private float aurora$moonSize(float f) {
        AmbienceModule ambienceModule = AmbienceModule.get();
        return ambienceModule != null && ambienceModule.isEnabled() && ambienceModule.sunMoon() ? f * ambienceModule.moonScale() : f;
    }
}
