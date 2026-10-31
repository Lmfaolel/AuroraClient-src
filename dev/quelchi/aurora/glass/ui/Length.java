package dev.quelchi.aurora.glass.ui;

import dev.quelchi.aurora.glass.ui.Length$Unit;

public record Length(Length$Unit unit, float value) {
    public static final Length AUTO;
    public static final Length FILL;
    private static final String[] a;

    public Length {
        if (length$Unit == null) {
            String[] stringArray = a;
            throw new IllegalArgumentException(stringArray[1]);
        }
        if (!Float.isFinite(f)) {
            throw new IllegalArgumentException(a[0]);
        }
    }

    public static Length fill() {
        return FILL;
    }

    
    static {
        block12: {
            var5 = new String[2];
            var3_1 = 0;
            var2_2 = "Aq=T\u001c\u0004B~8U";
            var4_3 = "Aq=T\u001c\u0004B~8U".length();
            var1_4 = 5;
            var0_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var5[var3_1++] = new String(v0).intern();
                if ((var0_5 += var1_4) < var4_3) {
                    var1_4 = var2_2.charAt(var0_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var0_5;
            v2 = var2_2.substring(v1, v1 + var1_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 55;
                        break;
                    }
                    case 1: {
                        v6 = 16;
                        break;
                    }
                    case 2: {
                        v6 = 81;
                        break;
                    }
                    case 3: {
                        v6 = 33;
                        break;
                    }
                    case 4: {
                        v6 = 121;
                        break;
                    }
                    case 5: {
                        v6 = 38;
                        break;
                    }
                    default: {
                        v6 = 116;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        Length.a = var5;
        Length.AUTO = new Length(Length$Unit.AUTO, 0.0f);
        Length.FILL = new Length(Length$Unit.FILL, 1.0f);
    }
}
