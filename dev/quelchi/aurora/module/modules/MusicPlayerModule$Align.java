package dev.quelchi.aurora.module.modules;

public final class MusicPlayerModule$Align
extends Enum<MusicPlayerModule$Align> {
    public static final /* enum */ MusicPlayerModule$Align Left;
    public static final /* enum */ MusicPlayerModule$Align Center;
    public static final /* enum */ MusicPlayerModule$Align Right;
    private static final MusicPlayerModule$Align[] $VALUES;

    public static MusicPlayerModule$Align[] values() {
        return (MusicPlayerModule$Align[])$VALUES.clone();
    }

    public static MusicPlayerModule$Align valueOf(String string) {
        return Enum.valueOf(MusicPlayerModule$Align.class, string);
    }

    private static MusicPlayerModule$Align[] $values() {
        return new MusicPlayerModule$Align[]{Left, Center, Right};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "N\u0003MA\u0003\u0004P\u000fL]\u0006_\u000fD]\u0012@";
            var5_3 = "N\u0003MA\u0003\u0004P\u000fL]\u0006_\u000fD]\u0012@".length();
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
                        v6 = 28;
                        break;
                    }
                    case 1: {
                        v6 = 106;
                        break;
                    }
                    case 2: {
                        v6 = 42;
                        break;
                    }
                    case 3: {
                        v6 = 41;
                        break;
                    }
                    case 4: {
                        v6 = 119;
                        break;
                    }
                    case 5: {
                        v6 = 50;
                        break;
                    }
                    default: {
                        v6 = 46;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        MusicPlayerModule$Align.Left = new MusicPlayerModule$Align();
        MusicPlayerModule$Align.Center = new MusicPlayerModule$Align();
        MusicPlayerModule$Align.Right = new MusicPlayerModule$Align();
        MusicPlayerModule$Align.$VALUES = MusicPlayerModule$Align.$values();
    }
}
