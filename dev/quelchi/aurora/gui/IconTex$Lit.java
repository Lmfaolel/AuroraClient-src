package dev.quelchi.aurora.gui;

final class IconTex$Lit
extends Enum<IconTex$Lit> {
    public static final /* enum */ IconTex$Lit MASK;
    public static final /* enum */ IconTex$Lit PAINT;
    public static final /* enum */ IconTex$Lit ANY;
    private static final IconTex$Lit[] $VALUES;

    public static IconTex$Lit[] values() {
        return (IconTex$Lit[])$VALUES.clone();
    }

    public static IconTex$Lit valueOf(String string) {
        return Enum.valueOf(IconTex$Lit.class, string);
    }

    private static IconTex$Lit[] $values() {
        return new IconTex$Lit[]{MASK, PAINT, ANY};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "n\u0013\nM\\\u0004s\u0013\u0010H\u0003\u007f\u001c\u001a";
            var5_3 = "n\u0013\nM\\\u0004s\u0013\u0010H\u0003\u007f\u001c\u001a".length();
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
                        v6 = 62;
                        break;
                    }
                    case 1: {
                        v6 = 82;
                        break;
                    }
                    case 2: {
                        v6 = 67;
                        break;
                    }
                    case 3: {
                        v6 = 3;
                        break;
                    }
                    case 4: {
                        v6 = 8;
                        break;
                    }
                    case 5: {
                        v6 = 58;
                        break;
                    }
                    default: {
                        v6 = 109;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        IconTex$Lit.MASK = new IconTex$Lit();
        IconTex$Lit.PAINT = new IconTex$Lit();
        IconTex$Lit.ANY = new IconTex$Lit();
        IconTex$Lit.$VALUES = IconTex$Lit.$values();
    }
}
