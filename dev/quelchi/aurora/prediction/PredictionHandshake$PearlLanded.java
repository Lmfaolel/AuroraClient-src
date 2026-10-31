package dev.quelchi.aurora.prediction;

import java.lang.invoke.LambdaMetafactory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketDecoder;
import net.minecraft.network.codec.ValueFirstEncoder;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record PredictionHandshake$PearlLanded(double x, double y, double z) {
    public static final CustomPayload.Id<PredictionHandshake$PearlLanded> ID;
    public static final PacketCodec<PacketByteBuf, PredictionHandshake$PearlLanded> CODEC;

    public CustomPayload.Id<? extends CustomPayload> method_56479() {
        return ID;
    }

    private static PredictionHandshake$PearlLanded lambda$static$1(PacketByteBuf packetByteBuf) {
        return new PredictionHandshake$PearlLanded(packetByteBuf.readDouble(), packetByteBuf.readDouble(), packetByteBuf.readDouble());
    }

    private static void lambda$static$0(PredictionHandshake$PearlLanded predictionHandshake$PearlLanded, PacketByteBuf packetByteBuf) {
        packetByteBuf.method_52940(predictionHandshake$PearlLanded.x());
        packetByteBuf.method_52940(predictionHandshake$PearlLanded.y());
        packetByteBuf.method_52940(predictionHandshake$PearlLanded.z());
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "V\u0000Y~T\u0006G\u0010JcJ!";
            var5_3 = "V\u0000Y~T\u0006G\u0010JcJ!".length();
            var2_4 = 5;
            var1_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var0[var4_1++] = new String(v0).intern();
                if ((var1_5 += var2_4) < var5_3) {
                    var2_4 = var3_2.charAt(var1_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var1_5;
            v2 = var3_2.substring(v1, v1 + var2_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 38;
                        break;
                    }
                    case 1: {
                        v6 = 101;
                        break;
                    }
                    case 2: {
                        v6 = 56;
                        break;
                    }
                    case 3: {
                        v6 = 12;
                        break;
                    }
                    case 4: {
                        v6 = 56;
                        break;
                    }
                    case 5: {
                        v6 = 64;
                        break;
                    }
                    default: {
                        v6 = 82;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        PredictionHandshake$PearlLanded.ID = new CustomPayload.Id(Identifier.method_60655((String)var0[1], (String)var0[0]));
        PredictionHandshake$PearlLanded.CODEC = PacketCodec.method_56438((ValueFirstEncoder)(ValueFirstEncoder)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, lambda$static$0(dev.quelchi.aurora.prediction.PredictionHandshake$PearlLanded net.minecraft.network.PacketByteBuf ), (Ldev/quelchi/aurora/prediction/PredictionHandshake$PearlLanded;Lnet/minecraft/network/PacketByteBuf;)V)(), (PacketDecoder)(PacketDecoder)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$static$1(net.minecraft.network.PacketByteBuf ), (Lnet/minecraft/network/PacketByteBuf;)Ldev/quelchi/aurora/prediction/PredictionHandshake$PearlLanded;)());
    }
}
