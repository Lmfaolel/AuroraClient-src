package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.render.SwingStyles;
import net.minecraft.client.render.entity.state.Lancing;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Lancing.class})
public abstract class LancingSwingMixin {
    @Inject(method={"method_75391(FLnet/minecraft/class_4587;ILnet/minecraft/class_1306;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$swingStyle(float f, MatrixStack matrixStack, int n, Arm arm, CallbackInfo callbackInfo) {
        if (SwingStyles.apply(matrixStack, n, arm, f)) {
            callbackInfo.cancel();
        }
    }
}
