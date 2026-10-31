package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.module.modules.NametagsModule;
import net.minecraft.client.render.command.LabelCommandRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={LabelCommandRenderer.Commands.class})
public abstract class LabelCommandRendererMixin {
    @WrapOperation(method={"method_74829(Lnet/minecraft/class_4587;Lnet/minecraft/class_243;ILnet/minecraft/class_2561;ZIDLnet/minecraft/class_12075;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_4587;method_22905(FFF)V")})
    private void divine$scaleLabel(MatrixStack matrixStack, float f, float f2, float f3, Operation<Void> operation) {
        NametagsModule nametagsModule = NametagsModule.get();
        float f4 = nametagsModule != null ? nametagsModule.labelScale() : 1.0f;
        operation.call(new Object[]{matrixStack, Float.valueOf(f * f4), Float.valueOf(f2 * f4), Float.valueOf(f3 * f4)});
    }
}
