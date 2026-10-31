package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.optimize.MarlowProtocol;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record MarlowProtocol$Version(int major, int minor, int patch, boolean snapshot, int loader, String commit, boolean dirty, long buildTimestamp) {
    public static final CustomPayload.Id<MarlowProtocol$Version> ID;
    public static final PacketCodec<PacketByteBuf, MarlowProtocol$Version> CODEC;

    public CustomPayload.Id<? extends CustomPayload> method_56479() {
        return ID;
    }

    private static MarlowProtocol$Version lambda$static$1(PacketByteBuf packetByteBuf) {
        return new MarlowProtocol$Version(packetByteBuf.method_10816(), packetByteBuf.method_10816(), packetByteBuf.method_10816(), packetByteBuf.readBoolean(), packetByteBuf.method_10816(), packetByteBuf.method_10800(40), packetByteBuf.readBoolean(), packetByteBuf.readLong());
    }

    private static void lambda$static$0(MarlowProtocol$Version marlowProtocol$Version, PacketByteBuf packetByteBuf) {
        packetByteBuf.method_10804(marlowProtocol$Version.major());
        packetByteBuf.method_10804(marlowProtocol$Version.minor());
        packetByteBuf.method_10804(marlowProtocol$Version.patch());
        packetByteBuf.method_52964(marlowProtocol$Version.snapshot());
        packetByteBuf.method_10804(marlowProtocol$Version.loader());
        packetByteBuf.method_10788(marlowProtocol$Version.commit(), 40);
        packetByteBuf.method_52964(marlowProtocol$Version.dirty());
        packetByteBuf.method_52974(marlowProtocol$Version.buildTimestamp());
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "[y\u0012\u0016E\u0017A".toCharArray();
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
                    case 0 -> 45;
                    case 1 -> 28;
                    case 2 -> 96;
                    case 3 -> 101;
                    case 4 -> 44;
                    case 5 -> 120;
                    default -> 47;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        String string = new String(cArray).intern();
        ID = new CustomPayload.Id(MarlowProtocol.chan(string));
        CODEC = PacketCodec.method_56438(MarlowProtocol$Version::lambda$static$0, MarlowProtocol$Version::lambda$static$1);
    }
}
