package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.FrameQueue;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MinecraftClient.class})
public abstract class FrameQueueMixin {
    @Inject(method={"method_1523(Z)V"}, at={@At(value="HEAD")})
    private void aurora$waitForGpu(boolean bl, CallbackInfo callbackInfo) {
        FrameQueue.beforeFrame();
    }
}
