package dev.quelchi.aurora.module.modules;

public final class CrosshairAddonsModule$Indicator
extends Enum<CrosshairAddonsModule$Indicator> {
    public static final /* enum */ CrosshairAddonsModule$Indicator Vanilla;
    public static final /* enum */ CrosshairAddonsModule$Indicator Crosshair;
    public static final /* enum */ CrosshairAddonsModule$Indicator Hotbar;
    public static final /* enum */ CrosshairAddonsModule$Indicator Off;
    private static final CrosshairAddonsModule$Indicator[] $VALUES;

    public static CrosshairAddonsModule$Indicator[] values() {
        return (CrosshairAddonsModule$Indicator[])$VALUES.clone();
    }

    public static CrosshairAddonsModule$Indicator valueOf(String string) {
        return Enum.valueOf(CrosshairAddonsModule$Indicator.class, string);
    }

    private static CrosshairAddonsModule$Indicator[] $values() {
        return new CrosshairAddonsModule$Indicator[]{Vanilla, Crosshair, Hotbar, Off};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "K]\u0000\tGI\tA:O>mI";
                var5_3 = "K]\u0000\tGI\tA:O>mI".length();
                var2_4 = 3;
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
                    var3_2 = "RZ\b[%K>\u0006LT\u0012P(U";
                    var5_3 = "RZ\b[%K>\u0006LT\u0012P(U".length();
                    var2_4 = 7;
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
                        v10 = 4;
                        break;
                    }
                    case 1: {
                        v10 = 59;
                        break;
                    }
                    case 2: {
                        v10 = 102;
                        break;
                    }
                    case 3: {
                        v10 = 50;
                        break;
                    }
                    case 4: {
                        v10 = 73;
                        break;
                    }
                    case 5: {
                        v10 = 39;
                        break;
                    }
                    default: {
                        v10 = 95;
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
        CrosshairAddonsModule$Indicator.Vanilla = new CrosshairAddonsModule$Indicator();
        CrosshairAddonsModule$Indicator.Crosshair = new CrosshairAddonsModule$Indicator();
        CrosshairAddonsModule$Indicator.Hotbar = new CrosshairAddonsModule$Indicator();
        CrosshairAddonsModule$Indicator.Off = new CrosshairAddonsModule$Indicator();
        CrosshairAddonsModule$Indicator.$VALUES = CrosshairAddonsModule$Indicator.$values();
    }
}
