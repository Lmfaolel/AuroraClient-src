package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.mixins.RenderSystemInvoker;
import dev.quelchi.aurora.perf.Tweaks;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MinecraftClient.class})
public abstract class InputPollMixin {
    @Inject(method={"method_1523(Z)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_312;method_55793()V")})
    private void aurora$pollBeforeLook(boolean bl, CallbackInfo callbackInfo) {
        if (bl && Tweaks.INPUT_POLL) {
            RenderSystemInvoker.aurora$pollEvents();
        }
    }
}
