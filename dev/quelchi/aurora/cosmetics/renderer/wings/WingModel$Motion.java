package dev.quelchi.aurora.cosmetics.renderer.wings;

public final class WingModel$Motion
extends Enum<WingModel$Motion> {
    public static final /* enum */ WingModel$Motion FEATHER;
    public static final /* enum */ WingModel$Motion BAT;
    public static final /* enum */ WingModel$Motion FLUTTER;
    public static final /* enum */ WingModel$Motion ANGEL;
    private static final WingModel$Motion[] $VALUES;

    public static WingModel$Motion[] values() {
        return (WingModel$Motion[])$VALUES.clone();
    }

    public static WingModel$Motion valueOf(String string) {
        return Enum.valueOf(WingModel$Motion.class, string);
    }

    private static WingModel$Motion[] $values() {
        return new WingModel$Motion[]{FEATHER, BAT, FLUTTER, ANGEL};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "Z\u000b9mE\u0007]\u0000?|A++";
                var5_3 = "Z\u000b9mE\u0007]\u0000?|A++".length();
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
                    var3_2 = "Y\u0004*\u0007]\t+|]++";
                    var5_3 = "Y\u0004*\u0007]\t+|]++".length();
                    var2_4 = 3;
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
                        v10 = 27;
                        break;
                    }
                    case 1: {
                        v10 = 69;
                        break;
                    }
                    case 2: {
                        v10 = 126;
                        break;
                    }
                    case 3: {
                        v10 = 40;
                        break;
                    }
                    case 4: {
                        v10 = 9;
                        break;
                    }
                    case 5: {
                        v10 = 110;
                        break;
                    }
                    default: {
                        v10 = 121;
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
        WingModel$Motion.FEATHER = new WingModel$Motion();
        WingModel$Motion.BAT = new WingModel$Motion();
        WingModel$Motion.FLUTTER = new WingModel$Motion();
        WingModel$Motion.ANGEL = new WingModel$Motion();
        WingModel$Motion.$VALUES = WingModel$Motion.$values();
    }
}
