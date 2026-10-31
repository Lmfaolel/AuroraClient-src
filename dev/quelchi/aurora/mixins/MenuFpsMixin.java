package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.gui.im.ImScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.InactivityFpsLimiter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={InactivityFpsLimiter.class})
public class MenuFpsMixin {
    @Inject(method={"method_66514"}, at={@At(value="RETURN")}, cancellable=true)
    private void aurora$unlockMenus(CallbackInfoReturnable<InactivityFpsLimiter.LimitReason> callbackInfoReturnable) {
        if (callbackInfoReturnable.getReturnValue() == InactivityFpsLimiter.LimitReason.field_55847 && MinecraftClient.method_1551().field_1755 instanceof ImScreen) {
            callbackInfoReturnable.setReturnValue((Object)InactivityFpsLimiter.LimitReason.field_55843);
        }
    }
}
