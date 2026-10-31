package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.cosmetics.renderer.cape.CapeRenderer;
import dev.quelchi.aurora.module.modules.HitboxesModule;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={CapeFeatureRenderer.class})
public abstract class CapeFeatureRendererMixin {
    @Shadow
    @Final
    private BipedEntityModel<PlayerEntityRenderState> field_53206;

    @Inject(method={"method_4177(Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_10055;FF)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$renderCape(MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, PlayerEntityRenderState playerEntityRenderState, float f, float f2, CallbackInfo callbackInfo) {
        HitboxesModule hitboxesModule = HitboxesModule.get();
        if (hitboxesModule != null && hitboxesModule.hidesArmor()) {
            callbackInfo.cancel();
            return;
        }
        if (CapeRenderer.render(matrixStack, orderedRenderCommandQueue, n, playerEntityRenderState, this.field_53206)) {
            callbackInfo.cancel();
        }
    }
}
