package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.quelchi.aurora.render.SwingStyles;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={HeldItemRenderer.class})
public abstract class HeldItemSwingMixin {
    @Unique
    private static final float AURORA$HAND_X = 0.56f;
    @Unique
    private static final float AURORA$HAND_Z = 0.72f;

    @WrapOperation(method={"method_3228"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_759;method_65816(FLnet/minecraft/class_4587;ILnet/minecraft/class_1306;)V", ordinal=2)})
    private void aurora$swingStyle(HeldItemRenderer heldItemRenderer, float f, MatrixStack matrixStack, int n, Arm arm, Operation<Void> operation, @Local(ordinal=3, argsOnly=true) float f2) {
        int n2 = arm == Arm.field_6183 ? 1 : -1;
        float f3 = -0.52f + f2 * -0.6f;
        matrixStack.method_46416((float)(-n2) * 0.56f, -f3, 0.72f);
        if (SwingStyles.apply(matrixStack, n, arm, f, true)) {
            return;
        }
        matrixStack.method_46416((float)n2 * 0.56f, f3, -0.72f);
        operation.call(new Object[]{heldItemRenderer, Float.valueOf(f), matrixStack, n, arm});
    }
}
