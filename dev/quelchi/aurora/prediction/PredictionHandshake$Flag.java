package dev.quelchi.aurora.prediction;

import java.lang.invoke.LambdaMetafactory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketDecoder;
import net.minecraft.network.codec.ValueFirstEncoder;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record PredictionHandshake$Flag(String check, String detail, int vl) {
    public static final CustomPayload.Id<PredictionHandshake$Flag> ID;
    public static final PacketCodec<PacketByteBuf, PredictionHandshake$Flag> CODEC;

    public CustomPayload.Id<? extends CustomPayload> method_56479() {
        return ID;
    }

    private static PredictionHandshake$Flag lambda$static$1(PacketByteBuf packetByteBuf) {
        return new PredictionHandshake$Flag(packetByteBuf.method_10800(32), packetByteBuf.method_10800(128), packetByteBuf.readInt());
    }

    private static void lambda$static$0(PredictionHandshake$Flag predictionHandshake$Flag, PacketByteBuf packetByteBuf) {
        packetByteBuf.method_10788(predictionHandshake$Flag.check(), 32);
        packetByteBuf.method_10788(predictionHandshake$Flag.detail(), 128);
        packetByteBuf.method_53002(predictionHandshake$Flag.vl());
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "!n \u0011k$\u0004&w3\u0019";
            var5_3 = "!n \u0011k$\u0004&w3\u0019".length();
            var2_4 = 6;
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
                        v6 = 64;
                        break;
                    }
                    case 1: {
                        v6 = 27;
                        break;
                    }
                    case 2: {
                        v6 = 82;
                        break;
                    }
                    case 3: {
                        v6 = 126;
                        break;
                    }
                    case 4: {
                        v6 = 25;
                        break;
                    }
                    case 5: {
                        v6 = 69;
                        break;
                    }
                    default: {
                        v6 = 71;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        PredictionHandshake$Flag.ID = new CustomPayload.Id(Identifier.method_60655((String)var0[0], (String)var0[1]));
        PredictionHandshake$Flag.CODEC = PacketCodec.method_56438((ValueFirstEncoder)(ValueFirstEncoder)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, lambda$static$0(dev.quelchi.aurora.prediction.PredictionHandshake$Flag net.minecraft.network.PacketByteBuf ), (Ldev/quelchi/aurora/prediction/PredictionHandshake$Flag;Lnet/minecraft/network/PacketByteBuf;)V)(), (PacketDecoder)(PacketDecoder)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$static$1(net.minecraft.network.PacketByteBuf ), (Lnet/minecraft/network/PacketByteBuf;)Ldev/quelchi/aurora/prediction/PredictionHandshake$Flag;)());
    }
}
