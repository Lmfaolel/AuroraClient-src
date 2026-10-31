package dev.quelchi.aurora.optimize;

import java.lang.invoke.LambdaMetafactory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketDecoder;
import net.minecraft.network.codec.ValueFirstEncoder;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ConsumableOptimizer$Disable() {
    public static final CustomPayload.Id<ConsumableOptimizer$Disable> ID;
    public static final PacketCodec<PacketByteBuf, ConsumableOptimizer$Disable> CODEC;

    public CustomPayload.Id<? extends CustomPayload> method_56479() {
        return ID;
    }

    private static ConsumableOptimizer$Disable lambda$static$1(PacketByteBuf packetByteBuf) {
        return new ConsumableOptimizer$Disable();
    }

    private static void lambda$static$0(ConsumableOptimizer$Disable consumableOptimizer$Disable, PacketByteBuf packetByteBuf) {
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "*c0S,\u0015\u0012+`;\u007f6\b\u0007 a7Z<\n\u000f-e-A;\u0014\u0016\u0016|?Y5\u0017\u0012-";
            var5_3 = "*c0S,\u0015\u0012+`;\u007f6\b\u0007 a7Z<\n\u000f-e-A;\u0014\u0016\u0016|?Y5\u0017\u0012-".length();
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
                        v6 = 73;
                        break;
                    }
                    case 1: {
                        v6 = 12;
                        break;
                    }
                    case 2: {
                        v6 = 94;
                        break;
                    }
                    case 3: {
                        v6 = 32;
                        break;
                    }
                    case 4: {
                        v6 = 89;
                        break;
                    }
                    case 5: {
                        v6 = 120;
                        break;
                    }
                    default: {
                        v6 = 115;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        ConsumableOptimizer$Disable.ID = new CustomPayload.Id(Identifier.method_60655((String)var0[0], (String)var0[1]));
        ConsumableOptimizer$Disable.CODEC = PacketCodec.method_56438((ValueFirstEncoder)(ValueFirstEncoder)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, lambda$static$0(dev.quelchi.aurora.optimize.ConsumableOptimizer$Disable net.minecraft.network.PacketByteBuf ), (Ldev/quelchi/aurora/optimize/ConsumableOptimizer$Disable;Lnet/minecraft/network/PacketByteBuf;)V)(), (PacketDecoder)(PacketDecoder)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$static$1(net.minecraft.network.PacketByteBuf ), (Lnet/minecraft/network/PacketByteBuf;)Ldev/quelchi/aurora/optimize/ConsumableOptimizer$Disable;)());
    }
}
