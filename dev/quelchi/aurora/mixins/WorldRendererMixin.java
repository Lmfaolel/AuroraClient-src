package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.BlockOverlayModule;
import dev.quelchi.aurora.optimize.AnchorPrediction;
import dev.quelchi.aurora.render.BlockOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.state.OutlineRenderState;
import net.minecraft.client.render.state.WorldRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={WorldRenderer.class})
public abstract class WorldRendererMixin {
    @Inject(method={"method_62210"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$hidePredictedAnchorOutline(VertexConsumerProvider.Immediate immediate, MatrixStack matrixStack, boolean bl, WorldRenderState worldRenderState, CallbackInfo callbackInfo) {
        if (AnchorPrediction.hidesOutline()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_62210"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$blockOverlay(VertexConsumerProvider.Immediate immediate, MatrixStack matrixStack, boolean bl, WorldRenderState worldRenderState, CallbackInfo callbackInfo) {
        BlockOverlayModule blockOverlayModule = BlockOverlayModule.get();
        if (blockOverlayModule == null || !blockOverlayModule.isEnabled() || !blockOverlayModule.draws()) {
            return;
        }
        if (AnchorPrediction.hidesOutline()) {
            return;
        }
        callbackInfo.cancel();
        OutlineRenderState outlineRenderState = worldRenderState.field_63083;
        if (outlineRenderState == null || outlineRenderState.comp_4933() != bl) {
            return;
        }
        BlockOverlayRenderer.render(immediate, matrixStack, worldRenderState.field_63082.field_63078, outlineRenderState, blockOverlayModule);
    }
}
