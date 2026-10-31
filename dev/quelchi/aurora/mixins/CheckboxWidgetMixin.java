package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.gui.MenuButton;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={CheckboxWidget.class})
public class CheckboxWidgetMixin {
    @WrapOperation(method={"method_75752"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_52707(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIIII)V")})
    private void aurora$box(DrawContext drawContext, RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4, int n5, Operation<Void> operation) {
        CheckboxWidget checkboxWidget = (CheckboxWidget)this;
        MenuButton.check(drawContext, n, n2, n3, checkboxWidget.method_20372(), checkboxWidget.method_25367(), checkboxWidget.method_75798());
    }
}
