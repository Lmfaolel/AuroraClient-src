package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.gui.MenuButton;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TabButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TabButtonWidget.class})
public class TabButtonWidgetMixin {
    @WrapOperation(method={"method_48579"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_52706(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIII)V")})
    private void aurora$noPlate(DrawContext drawContext, RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4, Operation<Void> operation) {
        MenuButton.tab(drawContext, n, n2, n3, n4);
    }

    @Inject(method={"method_58230"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$noPatch(DrawContext drawContext, int n, int n2, int n3, int n4, CallbackInfo callbackInfo) {
        callbackInfo.cancel();
    }

    @Inject(method={"method_49610"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$underline(DrawContext drawContext, TextRenderer textRenderer, int n, CallbackInfo callbackInfo) {
        MenuButton.tabLine(drawContext, (TabButtonWidget)this);
        callbackInfo.cancel();
    }

    @ModifyExpressionValue(method={"method_49608"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_8209;method_49611()Z")})
    private boolean aurora$level(boolean bl) {
        return false;
    }

    @ModifyArg(method={"method_49608"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_12225;method_75770(Lnet/minecraft/class_2561;IIII)V")}, index=0)
    private Text aurora$label(Text text) {
        return MenuButton.tabLabel((TabButtonWidget)this);
    }
}
