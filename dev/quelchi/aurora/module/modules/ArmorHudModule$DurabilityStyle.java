package dev.quelchi.aurora.module.modules;

public final class ArmorHudModule$DurabilityStyle
extends Enum<ArmorHudModule$DurabilityStyle> {
    public static final /* enum */ ArmorHudModule$DurabilityStyle Numbers;
    public static final /* enum */ ArmorHudModule$DurabilityStyle Percentage;
    public static final /* enum */ ArmorHudModule$DurabilityStyle Bars;
    private static final ArmorHudModule$DurabilityStyle[] $VALUES;

    public static ArmorHudModule$DurabilityStyle[] values() {
        return (ArmorHudModule$DurabilityStyle[])$VALUES.clone();
    }

    public static ArmorHudModule$DurabilityStyle valueOf(String string) {
        return Enum.valueOf(ArmorHudModule$DurabilityStyle.class, string);
    }

    private static ArmorHudModule$DurabilityStyle[] $values() {
        return new ArmorHudModule$DurabilityStyle[]{Numbers, Percentage, Bars};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "/\u001fPj~d\u001b\u001e\u001dG\u0004=\u001bPz\u00071\u000fOk~x\u001c";
            var5_3 = "/\u001fPj~d\u001b\u001e\u001dG\u0004=\u001bPz\u00071\u000fOk~x\u001c".length();
            var2_4 = 10;
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
                        v6 = 127;
                        break;
                    }
                    case 1: {
                        v6 = 122;
                        break;
                    }
                    case 2: {
                        v6 = 34;
                        break;
                    }
                    case 3: {
                        v6 = 9;
                        break;
                    }
                    case 4: {
                        v6 = 27;
                        break;
                    }
                    case 5: {
                        v6 = 10;
                        break;
                    }
                    default: {
                        v6 = 111;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        ArmorHudModule$DurabilityStyle.Numbers = new ArmorHudModule$DurabilityStyle();
        ArmorHudModule$DurabilityStyle.Percentage = new ArmorHudModule$DurabilityStyle();
        ArmorHudModule$DurabilityStyle.Bars = new ArmorHudModule$DurabilityStyle();
        ArmorHudModule$DurabilityStyle.$VALUES = ArmorHudModule$DurabilityStyle.$values();
    }
}
