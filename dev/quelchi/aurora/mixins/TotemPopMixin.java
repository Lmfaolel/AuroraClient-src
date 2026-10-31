package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.TotemTweaksModule;
import dev.quelchi.aurora.render.TotemFade;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={InGameOverlayRenderer.class})
public abstract class TotemPopMixin {
    @Shadow
    private float field_59974;
    @Shadow
    private float field_59975;

    @ModifyArgs(method={"method_70939"}, at={@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/class_4587;method_22905(FFF)V")})
    private void aurora$popSize(Args args) {
        TotemTweaksModule totemTweaksModule = TotemTweaksModule.get();
        if (totemTweaksModule == null || !totemTweaksModule.scalesPop()) {
            return;
        }
        float f = totemTweaksModule.popScale();
        args.set(0, (Object)Float.valueOf(((Float)args.get(0)).floatValue() * f));
        args.set(1, (Object)Float.valueOf(((Float)args.get(1)).floatValue() * f));
        args.set(2, (Object)Float.valueOf(((Float)args.get(2)).floatValue() * f));
    }

    @ModifyConstant(method={"method_70938"}, constant={@Constant(intValue=40)})
    private int aurora$popTicks(int n) {
        TotemTweaksModule totemTweaksModule = TotemTweaksModule.get();
        return totemTweaksModule == null || !totemTweaksModule.isEnabled() ? n : totemTweaksModule.popTicks();
    }

    @ModifyConstant(method={"method_70939"}, constant={@Constant(intValue=40)})
    private int aurora$popTicksRender(int n) {
        TotemTweaksModule totemTweaksModule = TotemTweaksModule.get();
        return totemTweaksModule == null || !totemTweaksModule.isEnabled() ? n : totemTweaksModule.popTicks();
    }

    @ModifyConstant(method={"method_70939"}, constant={@Constant(floatValue=40.0f)})
    private float aurora$popTicksCurve(float f) {
        TotemTweaksModule totemTweaksModule = TotemTweaksModule.get();
        return totemTweaksModule == null || !totemTweaksModule.isEnabled() ? f : (float)totemTweaksModule.popTicks();
    }

    @Inject(method={"method_70938"}, at={@At(value="TAIL")})
    private void aurora$centrePop(ItemStack itemStack, Random random, CallbackInfo callbackInfo) {
        TotemTweaksModule totemTweaksModule = TotemTweaksModule.get();
        if (totemTweaksModule == null || !totemTweaksModule.isEnabled() || !totemTweaksModule.centrePop()) {
            return;
        }
        this.field_59974 = 0.0f;
        this.field_59975 = 0.0f;
    }

    @Inject(method={"method_70939"}, at={@At(value="HEAD")})
    private void aurora$popFadeBegin(MatrixStack matrixStack, float f, OrderedRenderCommandQueue orderedRenderCommandQueue, CallbackInfo callbackInfo) {
        TotemTweaksModule totemTweaksModule = TotemTweaksModule.get();
        if (totemTweaksModule != null && totemTweaksModule.fadesPop()) {
            TotemFade.begin(totemTweaksModule.popOpacity());
        }
    }

    @Inject(method={"method_70939"}, at={@At(value="RETURN")})
    private void aurora$popFadeEnd(MatrixStack matrixStack, float f, OrderedRenderCommandQueue orderedRenderCommandQueue, CallbackInfo callbackInfo) {
        TotemFade.end();
    }
}
