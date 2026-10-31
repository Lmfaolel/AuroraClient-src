package dev.quelchi.aurora.mixins;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.module.modules.CrosshairAddonsModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={DrawContext.class})
public abstract class CrosshairIndicatorMixin {
    @Unique
    private static boolean aurora$reentrant;

    
    @Inject(method={"method_52707(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIIII)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$shiftAttackIndicator(RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4, int n5, CallbackInfo callbackInfo) {
        if (aurora$reentrant || !CrosshairAddonsModule.movesAttackIndicator()) {
            return;
        }
        if (identifier == null || !identifier.method_12832().contains("hud/crosshair_attack")) {
            return;
        }
        int n6 = CrosshairAddonsModule.attackIndicatorY(n2, n4);
        if (n6 == n2) {
            return;
        }
        aurora$reentrant = true;
        try {
            ((DrawContext)this).method_52707(renderPipeline, identifier, n, n6, n3, n4, n5);
        }
        finally {
            aurora$reentrant = false;
        }
        callbackInfo.cancel();
    }

    
    @Inject(method={"method_52708(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIIIIIIII)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$shiftAttackIndicatorFill(RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, CallbackInfo callbackInfo) {
        if (aurora$reentrant || !CrosshairAddonsModule.movesAttackIndicator()) {
            return;
        }
        if (identifier == null || !identifier.method_12832().contains("hud/crosshair_attack")) {
            return;
        }
        int n10 = CrosshairAddonsModule.attackIndicatorY(n6, n8);
        if (n10 == n6) {
            return;
        }
        aurora$reentrant = true;
        try {
            ((DrawContext)this).method_52708(renderPipeline, identifier, n, n2, n3, n4, n5, n10, n7, n8, n9);
        }
        finally {
            aurora$reentrant = false;
        }
        callbackInfo.cancel();
    }
}
