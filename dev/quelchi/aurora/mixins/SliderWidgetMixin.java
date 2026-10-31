package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.gui.MenuButton;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={SliderWidget.class})
public class SliderWidgetMixin {
    @Shadow
    protected double field_22753;

    @WrapOperation(method={"method_48579"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_52707(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIIII)V", ordinal=0)})
    private void aurora$track(DrawContext drawContext, RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4, int n5, Operation<Void> operation) {
        ClickableWidget clickableWidget = (ClickableWidget)this;
        MenuButton.track(drawContext, clickableWidget, n, n2, n3, n4, this.field_22753, clickableWidget.method_25367());
    }

    @WrapOperation(method={"method_48579"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_52707(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIIII)V", ordinal=1)})
    private void aurora$handle(DrawContext drawContext, RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4, int n5, Operation<Void> operation) {
        MenuButton.knob(drawContext, (ClickableWidget)this, n, n2, n3, n4);
    }
}
