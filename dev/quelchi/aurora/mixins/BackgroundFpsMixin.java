package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.Tweaks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.InactivityFpsLimiter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={InactivityFpsLimiter.class})
public class BackgroundFpsMixin {
    @Inject(method={"method_61937"}, at={@At(value="RETURN")}, cancellable=true)
    private void aurora$capUnfocused(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (Tweaks.backgroundFps() && !MinecraftClient.method_1551().method_1569() && callbackInfoReturnable.getReturnValueI() > 30) {
            callbackInfoReturnable.setReturnValue((Object)30);
        }
    }
}
