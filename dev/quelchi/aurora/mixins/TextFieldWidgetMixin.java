package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.gui.MenuButton;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={TextFieldWidget.class})
public class TextFieldWidgetMixin {
    @WrapOperation(method={"method_48579"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_52706(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIII)V")})
    private void aurora$well(DrawContext drawContext, RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4, Operation<Void> operation) {
        MenuButton.well(drawContext, n, n2, n3, n4, ((ClickableWidget)this).method_25370());
    }
}
