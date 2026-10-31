package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.optimize.MarlowProtocol;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record MarlowProtocol$ChallengeResponse(int id) {
    public static final CustomPayload.Id<MarlowProtocol$ChallengeResponse> ID;
    public static final PacketCodec<PacketByteBuf, MarlowProtocol$ChallengeResponse> CODEC;

    public CustomPayload.Id<? extends CustomPayload> method_56479() {
        return ID;
    }

    private static MarlowProtocol$ChallengeResponse lambda$static$1(PacketByteBuf packetByteBuf) {
        return new MarlowProtocol$ChallengeResponse(packetByteBuf.readInt());
    }

    private static void lambda$static$0(MarlowProtocol$ChallengeResponse marlowProtocol$ChallengeResponse, PacketByteBuf packetByteBuf) {
        packetByteBuf.method_53002(marlowProtocol$ChallengeResponse.id());
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "{<v\u00049\u0001\u000f\u007f1H\u001a0\u0017\u0011w:d\r".toCharArray();
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
                    case 0 -> 24;
                    case 1 -> 84;
                    case 2 -> 23;
                    case 3 -> 104;
                    case 4 -> 85;
                    case 5 -> 100;
                    default -> 97;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        String string = new String(cArray).intern();
        ID = new CustomPayload.Id(MarlowProtocol.chan(string));
        CODEC = PacketCodec.method_56438(MarlowProtocol$ChallengeResponse::lambda$static$0, MarlowProtocol$ChallengeResponse::lambda$static$1);
    }
}
