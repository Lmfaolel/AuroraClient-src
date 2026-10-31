package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.prediction.GlidePrediction;
import dev.quelchi.aurora.prediction.OffhandSwap;
import dev.quelchi.aurora.prediction.PearlPlaceHold;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import dev.quelchi.aurora.prediction.ac.AntiCheat;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.common.ServerTransferS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ClientCommonNetworkHandler.class})
public abstract class ClientCommonNetworkHandlerMixin {
    @Inject(method={"method_52787(Lnet/minecraft/class_2596;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$holdPearlPlacement(Packet<?> packet, CallbackInfo callbackInfo) {
        if (PearlPlaceHold.holds(packet)) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_52787(Lnet/minecraft/class_2596;)V"}, at={@At(value="TAIL")})
    private void aurora$predictOffhandSwap(Packet<?> packet, CallbackInfo callbackInfo) {
        OffhandSwap.onSent(packet);
        GlidePrediction.onPacketSent(packet);
        AntiCheat.onPacketSent(packet);
    }

    @Inject(method={"method_56150(Lnet/minecraft/class_9151;)V"}, at={@At(value="HEAD")})
    private void aurora$transferring(ServerTransferS2CPacket serverTransferS2CPacket, CallbackInfo callbackInfo) {
        PredictionHandshake.transferring();
    }
}
