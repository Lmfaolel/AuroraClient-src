package dev.quelchi.aurora.gui.im;

final class IconEditor$Tool
extends Enum<IconEditor$Tool> {
    public static final /* enum */ IconEditor$Tool DRAW;
    public static final /* enum */ IconEditor$Tool ERASE;
    public static final /* enum */ IconEditor$Tool FILL;
    public static final /* enum */ IconEditor$Tool PICK;
    private static final IconEditor$Tool[] $VALUES;

    public static IconEditor$Tool[] values() {
        return (IconEditor$Tool[])$VALUES.clone();
    }

    public static IconEditor$Tool valueOf(String string) {
        return Enum.valueOf(IconEditor$Tool.class, string);
    }

    private static IconEditor$Tool[] $values() {
        return new IconEditor$Tool[]{DRAW, ERASE, FILL, PICK};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "}=k{)\u0004|=k\u007f";
                var5_3 = "}=k{)\u0004|=k\u007f".length();
                var2_4 = 5;
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
                    var3_2 = "~&fd\u0004h&ic";
                    var5_3 = "~&fd\u0004h&ic".length();
                    var2_4 = 4;
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
                        v10 = 56;
                        break;
                    }
                    case 1: {
                        v10 = 111;
                        break;
                    }
                    case 2: {
                        v10 = 42;
                        break;
                    }
                    case 3: {
                        v10 = 40;
                        break;
                    }
                    case 4: {
                        v10 = 108;
                        break;
                    }
                    case 5: {
                        v10 = 23;
                        break;
                    }
                    default: {
                        v10 = 77;
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
        IconEditor$Tool.DRAW = new IconEditor$Tool();
        IconEditor$Tool.ERASE = new IconEditor$Tool();
        IconEditor$Tool.FILL = new IconEditor$Tool();
        IconEditor$Tool.PICK = new IconEditor$Tool();
        IconEditor$Tool.$VALUES = IconEditor$Tool.$values();
    }
}
