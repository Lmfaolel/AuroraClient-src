package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.cosmetics.renderer.wings.WingFeature;
import dev.quelchi.aurora.duck.BlockingState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ElytraFeatureRenderer.class})
public abstract class ElytraFeatureRendererMixin {
    @Inject(method={"method_17161(Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_10034;FF)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$wingsReplaceElytra(MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, BipedEntityRenderState bipedEntityRenderState, float f, float f2, CallbackInfo callbackInfo) {
        if (WingFeature.worn(((BlockingState)bipedEntityRenderState).aurora$getUuid()) != null) {
            callbackInfo.cancel();
        }
    }
}
