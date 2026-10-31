package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.prediction.PredictionHandshake;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record PredictionHandshake$Caps(int protocol, int contract, int maxPlace, int maxBreak, double reach) {
    public static final CustomPayload.Id<PredictionHandshake$Caps> ID = new CustomPayload.Id(PredictionHandshake.chan());
    public static final PacketCodec<PacketByteBuf, PredictionHandshake$Caps> CODEC = PacketCodec.method_56438(PredictionHandshake$Caps::lambda$static$0, PredictionHandshake$Caps::lambda$static$1);

    public CustomPayload.Id<? extends CustomPayload> method_56479() {
        return ID;
    }

    private static PredictionHandshake$Caps lambda$static$1(PacketByteBuf packetByteBuf) {
        return new PredictionHandshake$Caps(packetByteBuf.readInt(), packetByteBuf.readInt(), packetByteBuf.readInt(), packetByteBuf.readInt(), packetByteBuf.readDouble());
    }

    private static void lambda$static$0(PredictionHandshake$Caps predictionHandshake$Caps, PacketByteBuf packetByteBuf) {
        packetByteBuf.method_53002(predictionHandshake$Caps.protocol());
        packetByteBuf.method_53002(predictionHandshake$Caps.contract());
        packetByteBuf.method_53002(predictionHandshake$Caps.maxPlace());
        packetByteBuf.method_53002(predictionHandshake$Caps.maxBreak());
        packetByteBuf.method_52940(predictionHandshake$Caps.reach());
    }
}
