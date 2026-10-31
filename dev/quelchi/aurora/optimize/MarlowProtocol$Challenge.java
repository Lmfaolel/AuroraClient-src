package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.optimize.MarlowProtocol;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record MarlowProtocol$Challenge(int id) {
    public static final CustomPayload.Id<MarlowProtocol$Challenge> ID;
    public static final PacketCodec<PacketByteBuf, MarlowProtocol$Challenge> CODEC;

    public CustomPayload.Id<? extends CustomPayload> method_56479() {
        return ID;
    }

    private static MarlowProtocol$Challenge lambda$static$1(PacketByteBuf packetByteBuf) {
        return new MarlowProtocol$Challenge(packetByteBuf.readInt());
    }

    private static void lambda$static$0(MarlowProtocol$Challenge marlowProtocol$Challenge, PacketByteBuf packetByteBuf) {
        packetByteBuf.method_53002(marlowProtocol$Challenge.id());
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "XU \t8iZ\\X".toCharArray();
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
                    case 0 -> 59;
                    case 1 -> 61;
                    case 2 -> 65;
                    case 3 -> 101;
                    case 4 -> 84;
                    case 5 -> 12;
                    default -> 52;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        String string = new String(cArray).intern();
        ID = new CustomPayload.Id(MarlowProtocol.chan(string));
        CODEC = PacketCodec.method_56438(MarlowProtocol$Challenge::lambda$static$0, MarlowProtocol$Challenge::lambda$static$1);
    }
}
