package dev.quelchi.aurora.module.modules;

public final class TotemCounterModule$CounterIcon
extends Enum<TotemCounterModule$CounterIcon> {
    public static final /* enum */ TotemCounterModule$CounterIcon Totem;
    public static final /* enum */ TotemCounterModule$CounterIcon Cross;
    public static final /* enum */ TotemCounterModule$CounterIcon Star;
    public static final /* enum */ TotemCounterModule$CounterIcon Heart;
    public static final /* enum */ TotemCounterModule$CounterIcon None;
    private static final TotemCounterModule$CounterIcon[] $VALUES;

    public static TotemCounterModule$CounterIcon[] values() {
        return (TotemCounterModule$CounterIcon[])$VALUES.clone();
    }

    public static TotemCounterModule$CounterIcon valueOf(String string) {
        return Enum.valueOf(TotemCounterModule$CounterIcon.class, string);
    }

    private static TotemCounterModule$CounterIcon[] $values() {
        return new TotemCounterModule$CounterIcon[]{Totem, Cross, Star, Heart, None};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[5];
                var4_1 = 0;
                var3_2 = "\u0005\u0017:X\u0005\u001f\u0017 Xn\u0005\u0003\u001d5Ow";
                var5_3 = "\u0005\u0017:X\u0005\u001f\u0017 Xn\u0005\u0003\u001d5Ow".length();
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
                    var3_2 = "\u0018\f5O\u0005\b\n;Np";
                    var5_3 = "\u0018\f5O\u0005\b\n;Np".length();
                    var2_4 = 4;
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
                        v10 = 75;
                        break;
                    }
                    case 1: {
                        v10 = 120;
                        break;
                    }
                    case 2: {
                        v10 = 84;
                        break;
                    }
                    case 3: {
                        v10 = 61;
                        break;
                    }
                    case 4: {
                        v10 = 3;
                        break;
                    }
                    case 5: {
                        v10 = 3;
                        break;
                    }
                    default: {
                        v10 = 82;
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
        TotemCounterModule$CounterIcon.Totem = new TotemCounterModule$CounterIcon();
        TotemCounterModule$CounterIcon.Cross = new TotemCounterModule$CounterIcon();
        TotemCounterModule$CounterIcon.Star = new TotemCounterModule$CounterIcon();
        TotemCounterModule$CounterIcon.Heart = new TotemCounterModule$CounterIcon();
        TotemCounterModule$CounterIcon.None = new TotemCounterModule$CounterIcon();
        TotemCounterModule$CounterIcon.$VALUES = TotemCounterModule$CounterIcon.$values();
    }
}
