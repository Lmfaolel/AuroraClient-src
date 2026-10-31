package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.HudData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ClientPlayNetworkHandler.class})
public abstract class ScoreboardPacketMixin {
    @Inject(method={"method_11144", "method_11118", "method_55450", "method_11159", "method_11099"}, at={@At(value="HEAD")})
    private void aurora$scoreboardChanged(CallbackInfo callbackInfo) {
        if (MinecraftClient.method_1551().method_18854()) {
            ++HudData.scoreboard;
        }
    }
}
