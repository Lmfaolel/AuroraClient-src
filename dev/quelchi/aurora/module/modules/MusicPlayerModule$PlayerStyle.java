package dev.quelchi.aurora.module.modules;

public final class MusicPlayerModule$PlayerStyle
extends Enum<MusicPlayerModule$PlayerStyle> {
    public static final /* enum */ MusicPlayerModule$PlayerStyle Card;
    public static final /* enum */ MusicPlayerModule$PlayerStyle Classic;
    private static final MusicPlayerModule$PlayerStyle[] $VALUES;

    public static MusicPlayerModule$PlayerStyle[] values() {
        return (MusicPlayerModule$PlayerStyle[])$VALUES.clone();
    }

    public static MusicPlayerModule$PlayerStyle valueOf(String string) {
        return Enum.valueOf(MusicPlayerModule$PlayerStyle.class, string);
    }

    private static MusicPlayerModule$PlayerStyle[] $values() {
        return new MusicPlayerModule$PlayerStyle[]{Card, Classic};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "\u00191\u0019\u0006\u0007\u0019<\n\u0011\u001dNz";
            var5_3 = "\u00191\u0019\u0006\u0007\u0019<\n\u0011\u001dNz".length();
            var2_4 = 4;
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
                        v6 = 90;
                        break;
                    }
                    case 1: {
                        v6 = 80;
                        break;
                    }
                    case 2: {
                        v6 = 107;
                        break;
                    }
                    case 3: {
                        v6 = 98;
                        break;
                    }
                    case 4: {
                        v6 = 110;
                        break;
                    }
                    case 5: {
                        v6 = 39;
                        break;
                    }
                    default: {
                        v6 = 25;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        MusicPlayerModule$PlayerStyle.Card = new MusicPlayerModule$PlayerStyle();
        MusicPlayerModule$PlayerStyle.Classic = new MusicPlayerModule$PlayerStyle();
        MusicPlayerModule$PlayerStyle.$VALUES = MusicPlayerModule$PlayerStyle.$values();
    }
}
