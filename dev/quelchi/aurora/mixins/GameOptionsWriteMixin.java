package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.FullBrightModule;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GameOptions.class})
public class GameOptionsWriteMixin {
    @Inject(method={"method_1640()V"}, at={@At(value="HEAD")})
    private void aurora$realGamma(CallbackInfo callbackInfo) {
        FullBrightModule.swapForWrite(true);
    }

    @Inject(method={"method_1640()V"}, at={@At(value="RETURN")})
    private void aurora$boostGamma(CallbackInfo callbackInfo) {
        FullBrightModule.swapForWrite(false);
    }
}
