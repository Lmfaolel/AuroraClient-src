package dev.quelchi.aurora.module.modules;

public final class MusicPlayerModule$Highlight
extends Enum<MusicPlayerModule$Highlight> {
    public static final /* enum */ MusicPlayerModule$Highlight Line;
    public static final /* enum */ MusicPlayerModule$Highlight Fill;
    private static final MusicPlayerModule$Highlight[] $VALUES;

    public static MusicPlayerModule$Highlight[] values() {
        return (MusicPlayerModule$Highlight[])$VALUES.clone();
    }

    public static MusicPlayerModule$Highlight valueOf(String string) {
        return Enum.valueOf(MusicPlayerModule$Highlight.class, string);
    }

    private static MusicPlayerModule$Highlight[] $values() {
        return new MusicPlayerModule$Highlight[]{Line, Fill};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "F\u000e\u001a>\u0004L\u000e\u00187";
            var5_3 = "F\u000e\u001a>\u0004L\u000e\u00187".length();
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
                        v6 = 10;
                        break;
                    }
                    case 1: {
                        v6 = 103;
                        break;
                    }
                    case 2: {
                        v6 = 116;
                        break;
                    }
                    case 3: {
                        v6 = 91;
                        break;
                    }
                    case 4: {
                        v6 = 46;
                        break;
                    }
                    case 5: {
                        v6 = 87;
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
        MusicPlayerModule$Highlight.Line = new MusicPlayerModule$Highlight();
        MusicPlayerModule$Highlight.Fill = new MusicPlayerModule$Highlight();
        MusicPlayerModule$Highlight.$VALUES = MusicPlayerModule$Highlight.$values();
    }
}
