package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.optimize.MarlowProtocol;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record MarlowProtocol$OptOutAck() {
    public static final CustomPayload.Id<MarlowProtocol$OptOutAck> ID;
    public static final PacketCodec<PacketByteBuf, MarlowProtocol$OptOutAck> CODEC;

    public CustomPayload.Id<? extends CustomPayload> method_56479() {
        return ID;
    }

    private static MarlowProtocol$OptOutAck lambda$static$1(PacketByteBuf packetByteBuf) {
        return new MarlowProtocol$OptOutAck();
    }

    private static void lambda$static$0(MarlowProtocol$OptOutAck marlowProtocol$OptOutAck, PacketByteBuf packetByteBuf) {
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "4?x}P[V\u0004.oI".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 91;
                    case 1 -> 79;
                    case 2 -> 12;
                    case 3 -> 34;
                    case 4 -> 63;
                    case 5 -> 46;
                    default -> 34;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        String string = new String(cArray).intern();
        ID = new CustomPayload.Id(MarlowProtocol.chan(string));
        CODEC = PacketCodec.method_56438(MarlowProtocol$OptOutAck::lambda$static$0, MarlowProtocol$OptOutAck::lambda$static$1);
    }
}
