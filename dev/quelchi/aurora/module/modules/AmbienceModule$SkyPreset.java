package dev.quelchi.aurora.module.modules;

public final class AmbienceModule$SkyPreset
extends Enum<AmbienceModule$SkyPreset> {
    public static final /* enum */ AmbienceModule$SkyPreset Custom;
    public static final /* enum */ AmbienceModule$SkyPreset Dusk;
    public static final /* enum */ AmbienceModule$SkyPreset Midnight;
    public static final /* enum */ AmbienceModule$SkyPreset Synthwave;
    public static final /* enum */ AmbienceModule$SkyPreset Arctic;
    private static final AmbienceModule$SkyPreset[] $VALUES;

    public static AmbienceModule$SkyPreset[] values() {
        return (AmbienceModule$SkyPreset[])$VALUES.clone();
    }

    public static AmbienceModule$SkyPreset valueOf(String string) {
        return Enum.valueOf(AmbienceModule$SkyPreset.class, string);
    }

    private static AmbienceModule$SkyPreset[] $values() {
        return new AmbienceModule$SkyPreset[]{Custom, Dusk, Midnight, Synthwave, Arctic};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[5];
                var4_1 = 0;
                var3_2 = "\u0003i3\u001e12n&u\u0006\u0013e.\u001e6(\b\u001dy9\u00040\"g$";
                var5_3 = "\u0003i3\u001e12n&u\u0006\u0013e.\u001e6(\b\u001dy9\u00040\"g$".length();
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
                    var3_2 = "\u0011b>\u001e0&\u0004\u0014e.\u0001";
                    var5_3 = "\u0011b>\u001e0&\u0004\u0014e.\u0001".length();
                    var2_4 = 6;
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
                        v10 = 80;
                        break;
                    }
                    case 1: {
                        v10 = 16;
                        break;
                    }
                    case 2: {
                        v10 = 93;
                        break;
                    }
                    case 3: {
                        v10 = 106;
                        break;
                    }
                    case 4: {
                        v10 = 89;
                        break;
                    }
                    case 5: {
                        v10 = 69;
                        break;
                    }
                    default: {
                        v10 = 15;
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
        AmbienceModule$SkyPreset.Custom = new AmbienceModule$SkyPreset();
        AmbienceModule$SkyPreset.Dusk = new AmbienceModule$SkyPreset();
        AmbienceModule$SkyPreset.Midnight = new AmbienceModule$SkyPreset();
        AmbienceModule$SkyPreset.Synthwave = new AmbienceModule$SkyPreset();
        AmbienceModule$SkyPreset.Arctic = new AmbienceModule$SkyPreset();
        AmbienceModule$SkyPreset.$VALUES = AmbienceModule$SkyPreset.$values();
    }
}
