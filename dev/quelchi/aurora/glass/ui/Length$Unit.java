package dev.quelchi.aurora.glass.ui;

public final class Length$Unit
extends Enum<Length$Unit> {
    public static final /* enum */ Length$Unit AUTO;
    public static final /* enum */ Length$Unit PIXELS;
    public static final /* enum */ Length$Unit PERCENT;
    public static final /* enum */ Length$Unit FILL;
    private static final Length$Unit[] $VALUES;

    public static Length$Unit[] values() {
        return (Length$Unit[])$VALUES.clone();
    }

    public static Length$Unit valueOf(String string) {
        return Enum.valueOf(Length$Unit.class, string);
    }

    private static Length$Unit[] $values() {
        return new Length$Unit[]{AUTO, PIXELS, PERCENT, FILL};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "lo\"P\u0004ks:S";
                var5_3 = "lo\"P\u0004ks:S".length();
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
                    var3_2 = "zo6Yy6\u0007zc<_p+1";
                    var5_3 = "zo6Yy6\u0007zc<_p+1".length();
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
                        v10 = 42;
                        break;
                    }
                    case 1: {
                        v10 = 38;
                        break;
                    }
                    case 2: {
                        v10 = 110;
                        break;
                    }
                    case 3: {
                        v10 = 28;
                        break;
                    }
                    case 4: {
                        v10 = 53;
                        break;
                    }
                    case 5: {
                        v10 = 101;
                        break;
                    }
                    default: {
                        v10 = 101;
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
        Length$Unit.AUTO = new Length$Unit();
        Length$Unit.PIXELS = new Length$Unit();
        Length$Unit.PERCENT = new Length$Unit();
        Length$Unit.FILL = new Length$Unit();
        Length$Unit.$VALUES = Length$Unit.$values();
    }
}
