package dev.quelchi.aurora.module.modules;

public final class MusicPlayerModule$Layout
extends Enum<MusicPlayerModule$Layout> {
    public static final /* enum */ MusicPlayerModule$Layout Lines;
    public static final /* enum */ MusicPlayerModule$Layout Ticker;
    public static final /* enum */ MusicPlayerModule$Layout World;
    private static final MusicPlayerModule$Layout[] $VALUES;

    public static MusicPlayerModule$Layout[] values() {
        return (MusicPlayerModule$Layout[])$VALUES.clone();
    }

    public static MusicPlayerModule$Layout valueOf(String string) {
        return Enum.valueOf(MusicPlayerModule$Layout.class, string);
    }

    private static MusicPlayerModule$Layout[] $values() {
        return new MusicPlayerModule$Layout[]{Lines, Ticker, World};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "v%85t\u0006u#)2uZ\u0005m#$<c";
            var5_3 = "v%85t\u0006u#)2uZ\u0005m#$<c".length();
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
                        v6 = 33;
                        break;
                    }
                    case 1: {
                        v6 = 74;
                        break;
                    }
                    case 2: {
                        v6 = 74;
                        break;
                    }
                    case 3: {
                        v6 = 89;
                        break;
                    }
                    case 4: {
                        v6 = 16;
                        break;
                    }
                    case 5: {
                        v6 = 40;
                        break;
                    }
                    default: {
                        v6 = 69;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        MusicPlayerModule$Layout.Lines = new MusicPlayerModule$Layout();
        MusicPlayerModule$Layout.Ticker = new MusicPlayerModule$Layout();
        MusicPlayerModule$Layout.World = new MusicPlayerModule$Layout();
        MusicPlayerModule$Layout.$VALUES = MusicPlayerModule$Layout.$values();
    }
}
