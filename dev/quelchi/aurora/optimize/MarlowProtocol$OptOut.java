package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.optimize.MarlowProtocol;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record MarlowProtocol$OptOut() {
    public static final CustomPayload.Id<MarlowProtocol$OptOut> ID;
    public static final PacketCodec<PacketByteBuf, MarlowProtocol$OptOut> CODEC;

    public CustomPayload.Id<? extends CustomPayload> method_56479() {
        return ID;
    }

    private static MarlowProtocol$OptOut lambda$static$1(PacketByteBuf packetByteBuf) {
        return new MarlowProtocol$OptOut();
    }

    private static void lambda$static$0(MarlowProtocol$OptOut marlowProtocol$OptOut, PacketByteBuf packetByteBuf) {
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "eUX\u0000\u0016\u001c\u001f".toCharArray();
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
                    case 0 -> 10;
                    case 1 -> 37;
                    case 2 -> 44;
                    case 3 -> 95;
                    case 4 -> 121;
                    case 5 -> 105;
                    default -> 107;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        String string = new String(cArray).intern();
        ID = new CustomPayload.Id(MarlowProtocol.chan(string));
        CODEC = PacketCodec.method_56438(MarlowProtocol$OptOut::lambda$static$0, MarlowProtocol$OptOut::lambda$static$1);
    }
}
