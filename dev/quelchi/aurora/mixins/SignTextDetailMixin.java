package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.Tweaks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.AbstractSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.state.SignBlockEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={AbstractSignBlockEntityRenderer.class})
public abstract class SignTextDetailMixin {
    @Inject(method={"method_65828"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$skipFarText(SignBlockEntityRenderState signBlockEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, boolean bl, CallbackInfo callbackInfo) {
        if (!Tweaks.farDetail()) {
            return;
        }
        BlockPos blockPos = signBlockEntityRenderState.field_62673;
        Vec3d vec3d = MinecraftClient.method_1551().field_1773.method_19418().method_71156();
        if (vec3d.method_1028((double)blockPos.method_10263() + 0.5, (double)blockPos.method_10264() + 0.5, (double)blockPos.method_10260() + 0.5) > 576.0) {
            callbackInfo.cancel();
        }
    }
}
