package dev.quelchi.aurora.module.modules;

public final class KeystrokesModule$Tail
extends Enum<KeystrokesModule$Tail> {
    public static final /* enum */ KeystrokesModule$Tail Line;
    public static final /* enum */ KeystrokesModule$Tail Comet;
    public static final /* enum */ KeystrokesModule$Tail Dots;
    public static final /* enum */ KeystrokesModule$Tail Spark;
    private static final KeystrokesModule$Tail[] $VALUES;

    public static KeystrokesModule$Tail[] values() {
        return (KeystrokesModule$Tail[])$VALUES.clone();
    }

    public static KeystrokesModule$Tail valueOf(String string) {
        return Enum.valueOf(KeystrokesModule$Tail.class, string);
    }

    private static KeystrokesModule$Tail[] $values() {
        return new KeystrokesModule$Tail[]{Line, Comet, Dots, Spark};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "{0Jr\u0005d)Ee@";
                var5_3 = "{0Jr\u0005d)Ee@".length();
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
                    var3_2 = "t6Ir_\u0004s6Pd";
                    var5_3 = "t6Ir_\u0004s6Pd".length();
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
                        v10 = 55;
                        break;
                    }
                    case 1: {
                        v10 = 89;
                        break;
                    }
                    case 2: {
                        v10 = 36;
                        break;
                    }
                    case 3: {
                        v10 = 23;
                        break;
                    }
                    case 4: {
                        v10 = 43;
                        break;
                    }
                    case 5: {
                        v10 = 11;
                        break;
                    }
                    default: {
                        v10 = 85;
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
        KeystrokesModule$Tail.Line = new KeystrokesModule$Tail();
        KeystrokesModule$Tail.Comet = new KeystrokesModule$Tail();
        KeystrokesModule$Tail.Dots = new KeystrokesModule$Tail();
        KeystrokesModule$Tail.Spark = new KeystrokesModule$Tail();
        KeystrokesModule$Tail.$VALUES = KeystrokesModule$Tail.$values();
    }
}
