package dev.quelchi.aurora.module.modules;

public final class TotemCounterModule$CounterSide
extends Enum<TotemCounterModule$CounterSide> {
    public static final /* enum */ TotemCounterModule$CounterSide Left;
    public static final /* enum */ TotemCounterModule$CounterSide Right;
    private static final TotemCounterModule$CounterSide[] $VALUES;

    public static TotemCounterModule$CounterSide[] values() {
        return (TotemCounterModule$CounterSide[])$VALUES.clone();
    }

    public static TotemCounterModule$CounterSide valueOf(String string) {
        return Enum.valueOf(TotemCounterModule$CounterSide.class, string);
    }

    private static TotemCounterModule$CounterSide[] $values() {
        return new TotemCounterModule$CounterSide[]{Left, Right};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "A\u0013c\u001f%\u0004_\u001fb\u0003";
            var5_3 = "A\u0013c\u001f%\u0004_\u001fb\u0003".length();
            var2_4 = 5;
            var1_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var0[var4_1++] = new String(v0).intern();
                if ((var1_5 += var2_4) < var5_3) {
                    var2_4 = var3_2.charAt(var1_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var1_5;
            v2 = var3_2.substring(v1, v1 + var2_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 19;
                        break;
                    }
                    case 1: {
                        v6 = 122;
                        break;
                    }
                    case 2: {
                        v6 = 4;
                        break;
                    }
                    case 3: {
                        v6 = 119;
                        break;
                    }
                    case 4: {
                        v6 = 81;
                        break;
                    }
                    case 5: {
                        v6 = 69;
                        break;
                    }
                    default: {
                        v6 = 123;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        TotemCounterModule$CounterSide.Left = new TotemCounterModule$CounterSide();
        TotemCounterModule$CounterSide.Right = new TotemCounterModule$CounterSide();
        TotemCounterModule$CounterSide.$VALUES = TotemCounterModule$CounterSide.$values();
    }
}
