package dev.quelchi.aurora.module.modules;

public final class AmbienceModule$MoonLock
extends Enum<AmbienceModule$MoonLock> {
    public static final /* enum */ AmbienceModule$MoonLock Auto;
    public static final /* enum */ AmbienceModule$MoonLock Full;
    public static final /* enum */ AmbienceModule$MoonLock Gibbous;
    public static final /* enum */ AmbienceModule$MoonLock Half;
    public static final /* enum */ AmbienceModule$MoonLock Crescent;
    public static final /* enum */ AmbienceModule$MoonLock New;
    private static final AmbienceModule$MoonLock[] $VALUES;

    public static AmbienceModule$MoonLock[] values() {
        return (AmbienceModule$MoonLock[])$VALUES.clone();
    }

    public static AmbienceModule$MoonLock valueOf(String string) {
        return Enum.valueOf(AmbienceModule$MoonLock.class, string);
    }

    private static AmbienceModule$MoonLock[] $values() {
        return new AmbienceModule$MoonLock[]{Auto, Full, Gibbous, Half, Crescent, New};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[6];
                var4_1 = 0;
                var3_2 = "?)P8\u00041=P2\u00046=H1\b4:Y-4yl\u0003";
                var5_3 = "?)P8\u00041=P2\u00046=H1\b4:Y-4yl\u0003".length();
                var2_4 = 4;
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
                    var3_2 = "0!^<8iq\u00039-K";
                    var5_3 = "0!^<8iq\u00039-K".length();
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
                        v10 = 119;
                        break;
                    }
                    case 1: {
                        v10 = 72;
                        break;
                    }
                    case 2: {
                        v10 = 60;
                        break;
                    }
                    case 3: {
                        v10 = 94;
                        break;
                    }
                    case 4: {
                        v10 = 87;
                        break;
                    }
                    case 5: {
                        v10 = 28;
                        break;
                    }
                    default: {
                        v10 = 2;
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
        AmbienceModule$MoonLock.Auto = new AmbienceModule$MoonLock();
        AmbienceModule$MoonLock.Full = new AmbienceModule$MoonLock();
        AmbienceModule$MoonLock.Gibbous = new AmbienceModule$MoonLock();
        AmbienceModule$MoonLock.Half = new AmbienceModule$MoonLock();
        AmbienceModule$MoonLock.Crescent = new AmbienceModule$MoonLock();
        AmbienceModule$MoonLock.New = new AmbienceModule$MoonLock();
        AmbienceModule$MoonLock.$VALUES = AmbienceModule$MoonLock.$values();
    }
}
