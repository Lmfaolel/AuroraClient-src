package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.Tweaks;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MultiplayerServerListWidget.class})
public abstract class ServerPingPoolMixin {
    @Shadow
    @Final
    private static ThreadPoolExecutor field_19105;

    @Inject(method={"<clinit>"}, at={@At(value="TAIL")})
    private static void aurora$widenPingPool(CallbackInfo callbackInfo) {
        if (!Tweaks.SERVER_PINGS) {
            return;
        }
        field_19105.setKeepAliveTime(30L, TimeUnit.SECONDS);
        field_19105.setCorePoolSize(16);
        field_19105.allowCoreThreadTimeOut(true);
    }
}
