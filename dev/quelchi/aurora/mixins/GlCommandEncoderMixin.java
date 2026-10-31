package dev.quelchi.aurora.mixins;

import com.mojang.blaze3d.buffers.GpuFence;
import dev.quelchi.aurora.perf.SharedFences;
import net.minecraft.client.gl.GlCommandEncoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={GlCommandEncoder.class})
public abstract class GlCommandEncoderMixin {
    @Inject(method={"createFence"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$reuseFence(CallbackInfoReturnable<GpuFence> callbackInfoReturnable) {
        GpuFence gpuFence = SharedFences.reuse();
        if (gpuFence != null) {
            callbackInfoReturnable.setReturnValue((Object)gpuFence);
        }
    }

    @Inject(method={"createFence"}, at={@At(value="RETURN")}, cancellable=true)
    private void aurora$adoptFence(CallbackInfoReturnable<GpuFence> callbackInfoReturnable) {
        GpuFence gpuFence = (GpuFence)callbackInfoReturnable.getReturnValue();
        GpuFence gpuFence2 = SharedFences.adopt(gpuFence);
        if (gpuFence2 != gpuFence) {
            callbackInfoReturnable.setReturnValue((Object)gpuFence2);
        }
    }
}
