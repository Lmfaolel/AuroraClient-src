package dev.quelchi.aurora.module.modules;

public final class TotemCounterModule$CounterStyle
extends Enum<TotemCounterModule$CounterStyle> {
    public static final /* enum */ TotemCounterModule$CounterStyle Text;
    public static final /* enum */ TotemCounterModule$CounterStyle Boxed;
    private static final TotemCounterModule$CounterStyle[] $VALUES;

    public static TotemCounterModule$CounterStyle[] values() {
        return (TotemCounterModule$CounterStyle[])$VALUES.clone();
    }

    public static TotemCounterModule$CounterStyle valueOf(String string) {
        return Enum.valueOf(TotemCounterModule$CounterStyle.class, string);
    }

    private static TotemCounterModule$CounterStyle[] $values() {
        return new TotemCounterModule$CounterStyle[]{Text, Boxed};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "5D\u0001~{\u0004#N\u0001o";
            var5_3 = "5D\u0001~{\u0004#N\u0001o".length();
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
                        v6 = 119;
                        break;
                    }
                    case 1: {
                        v6 = 43;
                        break;
                    }
                    case 2: {
                        v6 = 121;
                        break;
                    }
                    case 3: {
                        v6 = 27;
                        break;
                    }
                    case 4: {
                        v6 = 31;
                        break;
                    }
                    case 5: {
                        v6 = 6;
                        break;
                    }
                    default: {
                        v6 = 65;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        TotemCounterModule$CounterStyle.Text = new TotemCounterModule$CounterStyle();
        TotemCounterModule$CounterStyle.Boxed = new TotemCounterModule$CounterStyle();
        TotemCounterModule$CounterStyle.$VALUES = TotemCounterModule$CounterStyle.$values();
    }
}
