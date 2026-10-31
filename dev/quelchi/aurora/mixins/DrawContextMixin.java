package dev.quelchi.aurora.mixins;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.duck.GlassContext;
import dev.quelchi.aurora.hud.InventoryScale;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.util.Identifier;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix3x2fc;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={DrawContext.class})
public abstract class DrawContextMixin
implements GlassContext {
    @Shadow
    @Final
    GuiRenderState field_59826;
    @Unique
    private final Deque<ScreenRect> aurora$scissors = new ArrayDeque<ScreenRect>();
    private static final String AURORA$ADD_ENTITY = "addEntity(Lnet/minecraft/client/render/entity/state/EntityRenderState;FLorg/joml/Vector3f;Lorg/joml/Quaternionf;Lorg/joml/Quaternionf;IIII)V";

    @Inject(method={"method_44379"}, at={@At(value="TAIL")})
    private void aurora$pushScissor(int n, int n2, int n3, int n4, CallbackInfo callbackInfo) {
        DrawContext drawContext = (DrawContext)this;
        ScreenRect screenRect = new ScreenRect(n, n2, n3 - n, n4 - n2).method_65185((Matrix3x2fc)drawContext.method_51448());
        ScreenRect screenRect2 = this.aurora$scissors.peekLast();
        if (screenRect2 != null) {
            ScreenRect screenRect3 = screenRect.method_49701(screenRect2);
            screenRect = screenRect3 != null ? screenRect3 : ScreenRect.method_48248();
        }
        this.aurora$scissors.addLast(screenRect);
    }

    @Inject(method={"method_44380"}, at={@At(value="TAIL")})
    private void aurora$popScissor(CallbackInfo callbackInfo) {
        this.aurora$scissors.pollLast();
    }

    @Inject(method={"method_51421"}, at={@At(value="RETURN")}, cancellable=true)
    private void aurora$virtualWidth(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (InventoryScale.isRendering()) {
            callbackInfoReturnable.setReturnValue((Object)InventoryScale.toVirtualSize(callbackInfoReturnable.getReturnValueI()));
        }
    }

    @Inject(method={"method_51443"}, at={@At(value="RETURN")}, cancellable=true)
    private void aurora$virtualHeight(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (InventoryScale.isRendering()) {
            callbackInfoReturnable.setReturnValue((Object)InventoryScale.toVirtualSize(callbackInfoReturnable.getReturnValueI()));
        }
    }

    @ModifyVariable(method={"method_70856(Lnet/minecraft/class_10017;FLorg/joml/Vector3f;Lorg/joml/Quaternionf;Lorg/joml/Quaternionf;IIII)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=0)
    private int aurora$entityX1(int n) {
        return this.aurora$mapX(n);
    }

    @ModifyVariable(method={"method_70856(Lnet/minecraft/class_10017;FLorg/joml/Vector3f;Lorg/joml/Quaternionf;Lorg/joml/Quaternionf;IIII)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=1)
    private int aurora$entityY1(int n) {
        return this.aurora$mapY(n);
    }

    @ModifyVariable(method={"method_70856(Lnet/minecraft/class_10017;FLorg/joml/Vector3f;Lorg/joml/Quaternionf;Lorg/joml/Quaternionf;IIII)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=2)
    private int aurora$entityX2(int n) {
        return this.aurora$mapX(n);
    }

    @ModifyVariable(method={"method_70856(Lnet/minecraft/class_10017;FLorg/joml/Vector3f;Lorg/joml/Quaternionf;Lorg/joml/Quaternionf;IIII)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=3)
    private int aurora$entityY2(int n) {
        return this.aurora$mapY(n);
    }

    @ModifyVariable(method={"method_70856(Lnet/minecraft/class_10017;FLorg/joml/Vector3f;Lorg/joml/Quaternionf;Lorg/joml/Quaternionf;IIII)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=0)
    private float aurora$entityScale(float f) {
        if (!InventoryScale.isRendering()) {
            return f;
        }
        return f * ((DrawContext)this).method_51448().m00();
    }

    @Unique
    private int aurora$mapX(int n) {
        if (!InventoryScale.isRendering()) {
            return n;
        }
        Matrix3x2fStack matrix3x2fStack = ((DrawContext)this).method_51448();
        return Math.round(matrix3x2fStack.m00() * (float)n + matrix3x2fStack.m20());
    }

    @Unique
    private int aurora$mapY(int n) {
        if (!InventoryScale.isRendering()) {
            return n;
        }
        Matrix3x2fStack matrix3x2fStack = ((DrawContext)this).method_51448();
        return Math.round(matrix3x2fStack.m11() * (float)n + matrix3x2fStack.m21());
    }

    @Override
    public GuiRenderState aurora$guiState() {
        return this.field_59826;
    }

    @Override
    public ScreenRect aurora$scissor() {
        return this.aurora$scissors.peekLast();
    }

    @Inject(method={"method_52706(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIII)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$inventoryAlpha(RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4, CallbackInfo callbackInfo) {
        float f = GuiTweaksModule.inventoryAlpha();
        if (f >= 1.0f || !DrawContextMixin.aurora$inContainer()) {
            return;
        }
        ((DrawContext)this).method_71501(renderPipeline, identifier, n, n2, n3, n4, f);
        callbackInfo.cancel();
    }

    @Inject(method={"method_25290(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIFFIIII)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$inventoryPanelAlpha(RenderPipeline renderPipeline, Identifier identifier, int n, int n2, float f, float f2, int n3, int n4, int n5, int n6, CallbackInfo callbackInfo) {
        float f3 = GuiTweaksModule.inventoryAlpha();
        if (f3 >= 1.0f || !DrawContextMixin.aurora$inContainer()) {
            return;
        }
        int n7 = Math.round(f3 * 255.0f) << 24 | 0xFFFFFF;
        ((DrawContext)this).method_25291(renderPipeline, identifier, n, n2, f, f2, n3, n4, n5, n6, n7);
        callbackInfo.cancel();
    }

    @Unique
    private static boolean aurora$inContainer() {
        return InventoryScale.isContainerRendering();
    }
}
