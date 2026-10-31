package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.CleanViewModule;
import net.minecraft.client.render.entity.feature.StuckArrowsFeatureRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={StuckArrowsFeatureRenderer.class})
public abstract class StuckArrowsFeatureRendererMixin {
    @Inject(method={"method_22134"}, at={@At(value="HEAD")}, cancellable=true)
    private void divine$hideArrows(PlayerEntityRenderState playerEntityRenderState, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        CleanViewModule cleanViewModule = CleanViewModule.get();
        if (cleanViewModule != null && cleanViewModule.hideArrows()) {
            callbackInfoReturnable.setReturnValue((Object)0);
        }
    }
}
