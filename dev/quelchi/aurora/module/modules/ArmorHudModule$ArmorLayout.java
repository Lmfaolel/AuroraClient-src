package dev.quelchi.aurora.module.modules;

public final class ArmorHudModule$ArmorLayout
extends Enum<ArmorHudModule$ArmorLayout> {
    public static final /* enum */ ArmorHudModule$ArmorLayout Horizontal;
    public static final /* enum */ ArmorHudModule$ArmorLayout Vertical;
    private static final ArmorHudModule$ArmorLayout[] $VALUES;

    public static ArmorHudModule$ArmorLayout[] values() {
        return (ArmorHudModule$ArmorLayout[])$VALUES.clone();
    }

    public static ArmorHudModule$ArmorLayout valueOf(String string) {
        return Enum.valueOf(ArmorHudModule$ArmorLayout.class, string);
    }

    private static ArmorHudModule$ArmorLayout[] $values() {
        return new ArmorHudModule$ArmorLayout[]{Horizontal, Vertical};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "\u001ef\u001ay\u0007*\u001b$\n\u0000l\u001ad\u0014&\u0014<b\u0004";
            var5_3 = "\u001ef\u001ay\u0007*\u001b$\n\u0000l\u001ad\u0014&\u0014<b\u0004".length();
            var2_4 = 8;
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
                        v6 = 72;
                        break;
                    }
                    case 1: {
                        v6 = 3;
                        break;
                    }
                    case 2: {
                        v6 = 104;
                        break;
                    }
                    case 3: {
                        v6 = 13;
                        break;
                    }
                    case 4: {
                        v6 = 110;
                        break;
                    }
                    case 5: {
                        v6 = 73;
                        break;
                    }
                    default: {
                        v6 = 122;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        ArmorHudModule$ArmorLayout.Horizontal = new ArmorHudModule$ArmorLayout();
        ArmorHudModule$ArmorLayout.Vertical = new ArmorHudModule$ArmorLayout();
        ArmorHudModule$ArmorLayout.$VALUES = ArmorHudModule$ArmorLayout.$values();
    }
}
