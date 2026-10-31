package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={InGameOverlayRenderer.class})
public abstract class AmbienceOverlayFireMixin {
    @ModifyArgs(method={"method_23070"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_4588;method_22915(FFFF)Lnet/minecraft/class_4588;")})
    private static void aurora$overrideOverlayFireColor(Args args) {
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule == null || !ambienceModule.isEnabled() || !ambienceModule.fire()) {
            return;
        }
        int n = ambienceModule.fireRgb();
        args.set(0, (Object)Float.valueOf((float)(n >> 16 & 0xFF) / 255.0f));
        args.set(1, (Object)Float.valueOf((float)(n >> 8 & 0xFF) / 255.0f));
        args.set(2, (Object)Float.valueOf((float)(n & 0xFF) / 255.0f));
    }
}
