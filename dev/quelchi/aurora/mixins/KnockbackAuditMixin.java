package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.prediction.ac.MoveAudit;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ClientConnection.class})
public abstract class KnockbackAuditMixin {
    @Inject(method={"method_10770(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/class_2596;)V"}, at={@At(value="HEAD")})
    private void aurora$seeKnockback(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo callbackInfo) {
        ExplosionS2CPacket explosionS2CPacket;
        EntityVelocityUpdateS2CPacket entityVelocityUpdateS2CPacket;
        ClientPlayerEntity clientPlayerEntity = MinecraftClient.method_1551().field_1724;
        if (clientPlayerEntity == null) {
            return;
        }
        if (packet instanceof EntityVelocityUpdateS2CPacket && (entityVelocityUpdateS2CPacket = (EntityVelocityUpdateS2CPacket)packet).method_11818() == clientPlayerEntity.method_5628()) {
            MoveAudit.onKnockback(entityVelocityUpdateS2CPacket.method_73085(), false);
        } else if (packet instanceof ExplosionS2CPacket && (explosionS2CPacket = (ExplosionS2CPacket)packet).comp_2884().isPresent()) {
            MoveAudit.onKnockback((Vec3d)explosionS2CPacket.comp_2884().get(), true);
        }
    }
}
