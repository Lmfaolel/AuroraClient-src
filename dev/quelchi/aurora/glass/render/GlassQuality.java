package dev.quelchi.aurora.glass.render;

public final class GlassQuality
extends Enum<GlassQuality> {
    public static final /* enum */ GlassQuality FULL_GLASS;
    public static final /* enum */ GlassQuality REDUCED_GLASS;
    public static final /* enum */ GlassQuality BLURRED_GLASS;
    public static final /* enum */ GlassQuality TRANSLUCENT_GLASS;
    public static final /* enum */ GlassQuality BASIC;
    private static final /* synthetic */ GlassQuality[] $VALUES;

    public static GlassQuality[] values() {
        return (GlassQuality[])$VALUES.clone();
    }

    public static GlassQuality valueOf(String string) {
        return Enum.valueOf(GlassQuality.class, string);
    }

    private static /* synthetic */ GlassQuality[] $values() {
        return new GlassQuality[]{FULL_GLASS, REDUCED_GLASS, BLURRED_GLASS, TRANSLUCENT_GLASS, BASIC};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[5];
                var4_1 = 0;
                var3_2 = "c\u001dw$Y\u001e\u0004~\u0016n7X\b\rs\u0014f#H\u001e\u0004~\u0016n7X\b\ng\u0004n:T\u001c\f`\u0002q";
                var5_3 = "c\u001dw$Y\u001e\u0004~\u0016n7X\b\rs\u0014f#H\u001e\u0004~\u0016n7X\b\ng\u0004n:T\u001c\f`\u0002q".length();
                var2_4 = 13;
                var1_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var1_5;
                    v1 = var3_2.substring(v0, v0 + var2_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        continue;
                    }
                    var3_2 = "u\u0003c8X\u0017\u0015b\u0014l\"T\u001c\f`\u0002q\u0005c\u0010q?H";
                    var5_3 = "u\u0003c8X\u0017\u0015b\u0014l\"T\u001c\f`\u0002q\u0005c\u0010q?H".length();
                    var2_4 = 17;
                    var1_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var1_5;
                        v1 = var3_2.substring(v4, v4 + var2_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 33;
                        break;
                    }
                    case 1: {
                        v10 = 81;
                        break;
                    }
                    case 2: {
                        v10 = 34;
                        break;
                    }
                    case 3: {
                        v10 = 118;
                        break;
                    }
                    case 4: {
                        v10 = 11;
                        break;
                    }
                    case 5: {
                        v10 = 91;
                        break;
                    }
                    default: {
                        v10 = 64;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_6;
lbl65:

                v7 = v7;
            } while (v7 > var6_6);
            v3 = new String(v6);
            switch (v2) {
                default: {
                    continue;
                }
                case 0:
lbl73:

                continue;
            }
        }
        GlassQuality.FULL_GLASS = new GlassQuality();
        GlassQuality.REDUCED_GLASS = new GlassQuality();
        GlassQuality.BLURRED_GLASS = new GlassQuality();
        GlassQuality.TRANSLUCENT_GLASS = new GlassQuality();
        GlassQuality.BASIC = new GlassQuality();
        GlassQuality.$VALUES = GlassQuality.$values();
    }
}
