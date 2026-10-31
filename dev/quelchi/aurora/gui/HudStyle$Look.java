package dev.quelchi.aurora.gui;

public final class HudStyle$Look
extends Enum<HudStyle$Look> {
    public static final /* enum */ HudStyle$Look Glass;
    public static final /* enum */ HudStyle$Look Box;
    public static final /* enum */ HudStyle$Look Outline;
    public static final /* enum */ HudStyle$Look Brackets;
    public static final /* enum */ HudStyle$Look None;
    private static final HudStyle$Look[] $VALUES;

    public static HudStyle$Look[] values() {
        return (HudStyle$Look[])$VALUES.clone();
    }

    public static HudStyle$Look valueOf(String string) {
        return Enum.valueOf(HudStyle$Look.class, string);
    }

    private static HudStyle$Look[] $values() {
        return new HudStyle$Look[]{Glass, Box, Outline, Brackets, None};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[5];
                var4_1 = 0;
                var3_2 = "%\u0013oc){h\u0004$\tuj\u0003(\tc";
                var5_3 = "%\u0013oc){h\u0004$\tuj\u0003(\tc".length();
                var2_4 = 7;
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
                    var3_2 = "-\nz|3\b(\u0014zl+py\u0019";
                    var5_3 = "-\nz|3\b(\u0014zl+py\u0019".length();
                    var2_4 = 5;
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
                        v10 = 106;
                        break;
                    }
                    case 1: {
                        v10 = 102;
                        break;
                    }
                    case 2: {
                        v10 = 27;
                        break;
                    }
                    case 3: {
                        v10 = 15;
                        break;
                    }
                    case 4: {
                        v10 = 64;
                        break;
                    }
                    case 5: {
                        v10 = 21;
                        break;
                    }
                    default: {
                        v10 = 13;
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
        HudStyle$Look.Glass = new HudStyle$Look();
        HudStyle$Look.Box = new HudStyle$Look();
        HudStyle$Look.Outline = new HudStyle$Look();
        HudStyle$Look.Brackets = new HudStyle$Look();
        HudStyle$Look.None = new HudStyle$Look();
        HudStyle$Look.$VALUES = HudStyle$Look.$values();
    }
}
