package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.HitboxesModule;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.state.ArmedEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={HeldItemFeatureRenderer.class})
public abstract class HeldItemFeatureRendererMixin {
    @Inject(method={"method_17162(Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_10426;FF)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void divine$optimalHideHeldItem(MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, ArmedEntityRenderState armedEntityRenderState, float f, float f2, CallbackInfo callbackInfo) {
        HitboxesModule hitboxesModule = HitboxesModule.get();
        if (hitboxesModule != null && hitboxesModule.hidesHeldItem() && armedEntityRenderState instanceof PlayerEntityRenderState) {
            callbackInfo.cancel();
        }
    }
}
