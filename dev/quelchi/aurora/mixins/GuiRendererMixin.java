package dev.quelchi.aurora.mixins;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.quelchi.aurora.gui.PreviewScene$GridRenderer;
import dev.quelchi.aurora.gui.PreviewScene$MainRenderer;
import dev.quelchi.aurora.hud.InventoryScale;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gui.render.SpecialGuiElementRenderer;
import net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState;
import net.minecraft.client.render.VertexConsumerProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={GuiRenderer.class})
public abstract class GuiRendererMixin {
    @Shadow
    @Final
    private VertexConsumerProvider.Immediate field_59917;
    @Unique
    private int aurora$lastItemScale = -1;

    @Shadow
    protected abstract void method_70896();

    @ModifyExpressionValue(method={"<init>"}, at={@At(value="INVOKE", target="Lcom/google/common/collect/ImmutableMap$Builder;buildOrThrow()Lcom/google/common/collect/ImmutableMap;")})
    private ImmutableMap<Class<? extends SpecialGuiElementRenderState>, SpecialGuiElementRenderer<?>> aurora$addRenderers(ImmutableMap<Class<? extends SpecialGuiElementRenderState>, SpecialGuiElementRenderer<?>> immutableMap) {
        PreviewScene$MainRenderer previewScene$MainRenderer = new PreviewScene$MainRenderer(this.field_59917);
        PreviewScene$GridRenderer previewScene$GridRenderer = new PreviewScene$GridRenderer(this.field_59917);
        return ImmutableMap.builder().putAll(immutableMap).put(previewScene$MainRenderer.method_70903(), (Object)previewScene$MainRenderer).put(previewScene$GridRenderer.method_70903(), (Object)previewScene$GridRenderer).buildOrThrow();
    }

    @Inject(method={"method_70895"}, at={@At(value="RETURN")}, cancellable=true)
    private void aurora$inventoryScaleAtlas(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        int n = callbackInfoReturnable.getReturnValueI();
        float f = InventoryScale.atlasFactor();
        if (f != 1.0f) {
            n = (int)Math.ceil((float)n * f);
            callbackInfoReturnable.setReturnValue((Object)n);
        }
        if (n != this.aurora$lastItemScale) {
            if (this.aurora$lastItemScale != -1) {
                this.method_70896();
            }
            this.aurora$lastItemScale = n;
        }
    }
}
