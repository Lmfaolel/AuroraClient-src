package dev.quelchi.aurora.module.modules;

public final class MusicPlayerModule$Palette
extends Enum<MusicPlayerModule$Palette> {
    public static final /* enum */ MusicPlayerModule$Palette Theme;
    public static final /* enum */ MusicPlayerModule$Palette Custom;
    private static final MusicPlayerModule$Palette[] $VALUES;

    public static MusicPlayerModule$Palette[] values() {
        return (MusicPlayerModule$Palette[])$VALUES.clone();
    }

    public static MusicPlayerModule$Palette valueOf(String string) {
        return Enum.valueOf(MusicPlayerModule$Palette.class, string);
    }

    private static MusicPlayerModule$Palette[] $values() {
        return new MusicPlayerModule$Palette[]{Theme, Custom};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "\u0011o[.r\u0006\u0006rM7xo";
            var5_3 = "\u0011o[.r\u0006\u0006rM7xo".length();
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
                        v6 = 69;
                        break;
                    }
                    case 1: {
                        v6 = 7;
                        break;
                    }
                    case 2: {
                        v6 = 62;
                        break;
                    }
                    case 3: {
                        v6 = 67;
                        break;
                    }
                    case 4: {
                        v6 = 23;
                        break;
                    }
                    case 5: {
                        v6 = 2;
                        break;
                    }
                    default: {
                        v6 = 104;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        MusicPlayerModule$Palette.Theme = new MusicPlayerModule$Palette();
        MusicPlayerModule$Palette.Custom = new MusicPlayerModule$Palette();
        MusicPlayerModule$Palette.$VALUES = MusicPlayerModule$Palette.$values();
    }
}
