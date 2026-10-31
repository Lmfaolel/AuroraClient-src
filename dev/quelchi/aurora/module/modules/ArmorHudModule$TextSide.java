package dev.quelchi.aurora.module.modules;

public final class ArmorHudModule$TextSide
extends Enum<ArmorHudModule$TextSide> {
    public static final /* enum */ ArmorHudModule$TextSide Right;
    public static final /* enum */ ArmorHudModule$TextSide Left;
    private static final ArmorHudModule$TextSide[] $VALUES;

    public static ArmorHudModule$TextSide[] values() {
        return (ArmorHudModule$TextSide[])$VALUES.clone();
    }

    public static ArmorHudModule$TextSide valueOf(String string) {
        return Enum.valueOf(ArmorHudModule$TextSide.class, string);
    }

    private static ArmorHudModule$TextSide[] $values() {
        return new ArmorHudModule$TextSide[]{Right, Left};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "2)\u001d\u0016\u0005,%\u001c\nU";
            var5_3 = "2)\u001d\u0016\u0005,%\u001c\nU".length();
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
                        v6 = 126;
                        break;
                    }
                    case 1: {
                        v6 = 76;
                        break;
                    }
                    case 2: {
                        v6 = 123;
                        break;
                    }
                    case 3: {
                        v6 = 98;
                        break;
                    }
                    case 4: {
                        v6 = 33;
                        break;
                    }
                    case 5: {
                        v6 = 24;
                        break;
                    }
                    default: {
                        v6 = 54;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        ArmorHudModule$TextSide.Right = new ArmorHudModule$TextSide();
        ArmorHudModule$TextSide.Left = new ArmorHudModule$TextSide();
        ArmorHudModule$TextSide.$VALUES = ArmorHudModule$TextSide.$values();
    }
}
