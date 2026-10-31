package dev.quelchi.aurora.module.modules;

public final class TimeChangerModule$TimePreset
extends Enum<TimeChangerModule$TimePreset> {
    public static final /* enum */ TimeChangerModule$TimePreset Day;
    public static final /* enum */ TimeChangerModule$TimePreset Noon;
    public static final /* enum */ TimeChangerModule$TimePreset Sunset;
    public static final /* enum */ TimeChangerModule$TimePreset Night;
    public static final /* enum */ TimeChangerModule$TimePreset Custom;
    private static final TimeChangerModule$TimePreset[] $VALUES;

    public static TimeChangerModule$TimePreset[] values() {
        return (TimeChangerModule$TimePreset[])$VALUES.clone();
    }

    public static TimeChangerModule$TimePreset valueOf(String string) {
        return Enum.valueOf(TimeChangerModule$TimePreset.class, string);
    }

    private static TimeChangerModule$TimePreset[] $values() {
        return new TimeChangerModule$TimePreset[]{Day, Noon, Sunset, Night, Custom};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[5];
                var4_1 = 0;
                var3_2 = "1/\u000e\u0001\u0006,5\u000f\u001cF`\u00051)\u0006\u0007W";
                var5_3 = "1/\u000e\u0001\u0006,5\u000f\u001cF`\u00051)\u0006\u0007W".length();
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
                    var3_2 = ";!\u0018\u0006<5\u0012\u001bLy";
                    var5_3 = ";!\u0018\u0006<5\u0012\u001bLy".length();
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
                        v10 = 127;
                        break;
                    }
                    case 1: {
                        v10 = 64;
                        break;
                    }
                    case 2: {
                        v10 = 97;
                        break;
                    }
                    case 3: {
                        v10 = 111;
                        break;
                    }
                    case 4: {
                        v10 = 35;
                        break;
                    }
                    case 5: {
                        v10 = 20;
                        break;
                    }
                    default: {
                        v10 = 59;
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
        TimeChangerModule$TimePreset.Day = new TimeChangerModule$TimePreset();
        TimeChangerModule$TimePreset.Noon = new TimeChangerModule$TimePreset();
        TimeChangerModule$TimePreset.Sunset = new TimeChangerModule$TimePreset();
        TimeChangerModule$TimePreset.Night = new TimeChangerModule$TimePreset();
        TimeChangerModule$TimePreset.Custom = new TimeChangerModule$TimePreset();
        TimeChangerModule$TimePreset.$VALUES = TimeChangerModule$TimePreset.$values();
    }
}
