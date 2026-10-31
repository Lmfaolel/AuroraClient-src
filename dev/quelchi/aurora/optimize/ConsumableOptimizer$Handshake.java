package dev.quelchi.aurora.optimize;

import java.lang.invoke.LambdaMetafactory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketDecoder;
import net.minecraft.network.codec.ValueFirstEncoder;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ConsumableOptimizer$Handshake() {
    public static final CustomPayload.Id<ConsumableOptimizer$Handshake> ID;
    public static final PacketCodec<PacketByteBuf, ConsumableOptimizer$Handshake> CODEC;

    public CustomPayload.Id<? extends CustomPayload> method_56479() {
        return ID;
    }

    private static ConsumableOptimizer$Handshake lambda$static$1(PacketByteBuf packetByteBuf) {
        return new ConsumableOptimizer$Handshake();
    }

    private static void lambda$static$0(ConsumableOptimizer$Handshake consumableOptimizer$Handshake, PacketByteBuf packetByteBuf) {
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "7\u001b\\%k-W6\u0018W\tq0B=\u0019[,{2\u0011<\u0015\\2m(W?\u0011m&\u007f9Z;\u0015V";
            var5_3 = "7\u001b\\%k-W6\u0018W\tq0B=\u0019[,{2\u0011<\u0015\\2m(W?\u0011m&\u007f9Z;\u0015V".length();
            var2_4 = 20;
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
                        v6 = 84;
                        break;
                    }
                    case 1: {
                        v6 = 116;
                        break;
                    }
                    case 2: {
                        v6 = 50;
                        break;
                    }
                    case 3: {
                        v6 = 86;
                        break;
                    }
                    case 4: {
                        v6 = 30;
                        break;
                    }
                    case 5: {
                        v6 = 64;
                        break;
                    }
                    default: {
                        v6 = 54;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        ConsumableOptimizer$Handshake.ID = new CustomPayload.Id(Identifier.method_60655((String)var0[0], (String)var0[1]));
        ConsumableOptimizer$Handshake.CODEC = PacketCodec.method_56438((ValueFirstEncoder)(ValueFirstEncoder)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, lambda$static$0(dev.quelchi.aurora.optimize.ConsumableOptimizer$Handshake net.minecraft.network.PacketByteBuf ), (Ldev/quelchi/aurora/optimize/ConsumableOptimizer$Handshake;Lnet/minecraft/network/PacketByteBuf;)V)(), (PacketDecoder)(PacketDecoder)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$static$1(net.minecraft.network.PacketByteBuf ), (Lnet/minecraft/network/PacketByteBuf;)Ldev/quelchi/aurora/optimize/ConsumableOptimizer$Handshake;)());
    }
}
