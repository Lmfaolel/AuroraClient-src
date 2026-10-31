package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.quelchi.aurora.module.modules.CustomCrystalsModule;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={EndCrystalEntityRenderer.class})
public abstract class CrystalShapeMixin {
    private static final String RENDER = "render(Lnet/minecraft/client/render/entity/state/EndCrystalEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/render/state/CameraRenderState;)V";

    @ModifyArgs(method={"method_3908(Lnet/minecraft/class_10014;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_4587;method_22905(FFF)V")})
    private void aurora$crystalScale(Args args) {
        float f = CustomCrystalsModule.scale();
        if (f == 1.0f) {
            return;
        }
        args.set(0, (Object)Float.valueOf(((Float)args.get(0)).floatValue() * f));
        args.set(1, (Object)Float.valueOf(((Float)args.get(1)).floatValue() * f));
        args.set(2, (Object)Float.valueOf(((Float)args.get(2)).floatValue() * f));
    }

    @ModifyReturnValue(method={"method_23155(F)F"}, at={@At(value="RETURN")})
    private static float aurora$crystalBob(float f) {
        float f2 = CustomCrystalsModule.bounceHeight();
        if (f2 == 1.0f) {
            return f;
        }
        return (f + 1.4f) * f2 - 1.4f;
    }
}
