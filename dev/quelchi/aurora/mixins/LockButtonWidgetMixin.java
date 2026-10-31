package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.gui.MenuButton;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.LockButtonWidget;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={LockButtonWidget.class})
public class LockButtonWidgetMixin {
    @WrapOperation(method={"method_75752"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_52706(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIII)V")})
    private void aurora$lock(DrawContext drawContext, RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4, Operation<Void> operation) {
        LockButtonWidget lockButtonWidget = (LockButtonWidget)this;
        MenuButton.lock(drawContext, (ClickableWidget)lockButtonWidget, n, n2, n3, n4, lockButtonWidget.method_1896());
    }
}
