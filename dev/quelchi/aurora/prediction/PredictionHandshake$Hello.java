package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.prediction.PredictionHandshake;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record PredictionHandshake$Hello(int protocol, int contract, String build) {
    public static final CustomPayload.Id<PredictionHandshake$Hello> ID = new CustomPayload.Id(PredictionHandshake.chan());
    public static final PacketCodec<PacketByteBuf, PredictionHandshake$Hello> CODEC = PacketCodec.method_56438(PredictionHandshake$Hello::lambda$static$0, PredictionHandshake$Hello::lambda$static$1);

    public CustomPayload.Id<? extends CustomPayload> method_56479() {
        return ID;
    }

    private static PredictionHandshake$Hello lambda$static$1(PacketByteBuf packetByteBuf) {
        return new PredictionHandshake$Hello(packetByteBuf.readInt(), packetByteBuf.readInt(), packetByteBuf.method_10800(64));
    }

    private static void lambda$static$0(PredictionHandshake$Hello predictionHandshake$Hello, PacketByteBuf packetByteBuf) {
        packetByteBuf.method_53002(predictionHandshake$Hello.protocol());
        packetByteBuf.method_53002(predictionHandshake$Hello.contract());
        packetByteBuf.method_10788(predictionHandshake$Hello.build(), 64);
    }
}
