package dev.quelchi.aurora.gui;

public final class PackMix$Look
extends Enum<PackMix$Look> {
    public static final /* enum */ PackMix$Look FLAT;
    public static final /* enum */ PackMix$Look BLOCK;
    public static final /* enum */ PackMix$Look CRYSTAL;
    public static final /* enum */ PackMix$Look SHIELD;
    public static final /* enum */ PackMix$Look SET;
    public static final /* enum */ PackMix$Look GUI;
    private static final PackMix$Look[] $VALUES;

    public static PackMix$Look[] values() {
        return (PackMix$Look[])$VALUES.clone();
    }

    public static PackMix$Look valueOf(String string) {
        return Enum.valueOf(PackMix$Look.class, string);
    }

    private static PackMix$Look[] $values() {
        return new PackMix$Look[]{FLAT, BLOCK, CRYSTAL, SHIELD, SET, GUI};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[6];
                var4_1 = 0;
                var3_2 = "lG\u001e_t\u0004hG\u0010H\u0007mY\bOkxH\u0006}C\u0018Ys}";
                var5_3 = "lG\u001e_t\u0004hG\u0010H\u0007mY\bOkxH\u0006}C\u0018Ys}".length();
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
                    var3_2 = "i^\u0018\u0003}N\u0005";
                    var5_3 = "i^\u0018\u0003}N\u0005".length();
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
                        v10 = 46;
                        break;
                    }
                    case 1: {
                        v10 = 11;
                        break;
                    }
                    case 2: {
                        v10 = 81;
                        break;
                    }
                    case 3: {
                        v10 = 28;
                        break;
                    }
                    case 4: {
                        v10 = 63;
                        break;
                    }
                    case 5: {
                        v10 = 57;
                        break;
                    }
                    default: {
                        v10 = 4;
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
        PackMix$Look.FLAT = new PackMix$Look();
        PackMix$Look.BLOCK = new PackMix$Look();
        PackMix$Look.CRYSTAL = new PackMix$Look();
        PackMix$Look.SHIELD = new PackMix$Look();
        PackMix$Look.SET = new PackMix$Look();
        PackMix$Look.GUI = new PackMix$Look();
        PackMix$Look.$VALUES = PackMix$Look.$values();
    }
}
