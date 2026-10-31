package dev.quelchi.aurora.module.modules;

public final class AmbienceModule$CloudStyle
extends Enum<AmbienceModule$CloudStyle> {
    public static final /* enum */ AmbienceModule$CloudStyle Vanilla;
    public static final /* enum */ AmbienceModule$CloudStyle Flat;
    public static final /* enum */ AmbienceModule$CloudStyle Soft;
    public static final /* enum */ AmbienceModule$CloudStyle Wispy;
    private static final AmbienceModule$CloudStyle[] $VALUES;

    public static AmbienceModule$CloudStyle[] values() {
        return (AmbienceModule$CloudStyle[])$VALUES.clone();
    }

    public static AmbienceModule$CloudStyle valueOf(String string) {
        return Enum.valueOf(AmbienceModule$CloudStyle.class, string);
    }

    private static AmbienceModule$CloudStyle[] $values() {
        return new AmbienceModule$CloudStyle[]{Vanilla, Flat, Soft, Wispy};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "'\u0003G~Y\u00046\u0006Uz";
                var5_3 = "'\u0003G~Y\u00046\u0006Uz".length();
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
                    var3_2 = "&\u000bZgL7D\u0004#\u0005Rz";
                    var5_3 = "&\u000bZgL7D\u0004#\u0005Rz".length();
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
                        v10 = 112;
                        break;
                    }
                    case 1: {
                        v10 = 106;
                        break;
                    }
                    case 2: {
                        v10 = 52;
                        break;
                    }
                    case 3: {
                        v10 = 14;
                        break;
                    }
                    case 4: {
                        v10 = 32;
                        break;
                    }
                    case 5: {
                        v10 = 91;
                        break;
                    }
                    default: {
                        v10 = 37;
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
        AmbienceModule$CloudStyle.Vanilla = new AmbienceModule$CloudStyle();
        AmbienceModule$CloudStyle.Flat = new AmbienceModule$CloudStyle();
        AmbienceModule$CloudStyle.Soft = new AmbienceModule$CloudStyle();
        AmbienceModule$CloudStyle.Wispy = new AmbienceModule$CloudStyle();
        AmbienceModule$CloudStyle.$VALUES = AmbienceModule$CloudStyle.$values();
    }
}
