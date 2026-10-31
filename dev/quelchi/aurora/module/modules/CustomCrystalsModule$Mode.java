package dev.quelchi.aurora.module.modules;

public final class CustomCrystalsModule$Mode
extends Enum<CustomCrystalsModule$Mode> {
    public static final /* enum */ CustomCrystalsModule$Mode DEFAULT;
    public static final /* enum */ CustomCrystalsModule$Mode WIREFRAME;
    public static final /* enum */ CustomCrystalsModule$Mode END_PORTAL;
    public static final /* enum */ CustomCrystalsModule$Mode END_GATEWAY;
    private final String label;
    private static final CustomCrystalsModule$Mode[] $VALUES;

    public static CustomCrystalsModule$Mode[] values() {
        return (CustomCrystalsModule$Mode[])$VALUES.clone();
    }

    public static CustomCrystalsModule$Mode valueOf(String string) {
        return Enum.valueOf(CustomCrystalsModule$Mode.class, string);
    }

    private CustomCrystalsModule$Mode(String string2) {
        this.label = string2;
    }

    public String toString() {
        return this.label;
    }

    private static CustomCrystalsModule$Mode[] $values() {
        return new CustomCrystalsModule$Mode[]{DEFAULT, WIREFRAME, END_PORTAL, END_GATEWAY};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[8];
                var4_1 = 0;
                var3_2 = "\u0005{c\u0005i;o?w\u0007\u0016WW!Z\u0005Z\u0007\u0016ww\u0001z%z\t\u0005[C%I\u001bO\u001fW\n\u0017|u@\u007f&|&s}\u000b\u0017|u@h(z7ep\u0019";
                var5_3 = "\u0005{c\u0005i;o?w\u0007\u0016WW!Z\u0005Z\u0007\u0016ww\u0001z%z\t\u0005[C%I\u001bO\u001fW\n\u0017|u@\u007f&|&s}\u000b\u0017|u@h(z7ep\u0019".length();
                var2_4 = 9;
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
                    var3_2 = "\u0017\\U?_\u0006\\\u0006S]\u000b\u0017\\U?H\bZ\u0017EP9";
                    var5_3 = "\u0017\\U?_\u0006\\\u0006S]\u000b\u0017\\U?H\bZ\u0017EP9".length();
                    var2_4 = 10;
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
                        v10 = 82;
                        break;
                    }
                    case 1: {
                        v10 = 18;
                        break;
                    }
                    case 2: {
                        v10 = 17;
                        break;
                    }
                    case 3: {
                        v10 = 96;
                        break;
                    }
                    case 4: {
                        v10 = 15;
                        break;
                    }
                    case 5: {
                        v10 = 73;
                        break;
                    }
                    default: {
                        v10 = 14;
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
        CustomCrystalsModule$Mode.DEFAULT = new CustomCrystalsModule$Mode(var0[2]);
        CustomCrystalsModule$Mode.WIREFRAME = new CustomCrystalsModule$Mode(var0[0]);
        CustomCrystalsModule$Mode.END_PORTAL = new CustomCrystalsModule$Mode(var0[4]);
        CustomCrystalsModule$Mode.END_GATEWAY = new CustomCrystalsModule$Mode(var0[5]);
        CustomCrystalsModule$Mode.$VALUES = CustomCrystalsModule$Mode.$values();
    }
}
