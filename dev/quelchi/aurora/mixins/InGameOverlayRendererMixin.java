package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.CleanViewModule;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={InGameOverlayRenderer.class})
public abstract class InGameOverlayRendererMixin {
    @Inject(method={"method_23070"}, at={@At(value="HEAD")}, cancellable=true)
    private static void divine$hideFire(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, Sprite sprite, CallbackInfo callbackInfo) {
        CleanViewModule cleanViewModule = CleanViewModule.get();
        if (cleanViewModule != null && cleanViewModule.hideFire()) {
            callbackInfo.cancel();
        }
    }
}
