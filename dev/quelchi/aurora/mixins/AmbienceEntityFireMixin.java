package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import net.minecraft.client.render.command.FireCommandRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={FireCommandRenderer.class})
public abstract class AmbienceEntityFireMixin {
    @ModifyArg(method={"method_73004"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_4588;method_39415(I)Lnet/minecraft/class_4588;")}, index=0)
    private static int aurora$overrideEntityFireColor(int n) {
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule == null || !ambienceModule.isEnabled() || !ambienceModule.fire()) {
            return n;
        }
        return 0xFF000000 | ambienceModule.fireRgb() & 0xFFFFFF;
    }
}
