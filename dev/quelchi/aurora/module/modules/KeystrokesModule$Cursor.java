package dev.quelchi.aurora.module.modules;

public final class KeystrokesModule$Cursor
extends Enum<KeystrokesModule$Cursor> {
    public static final /* enum */ KeystrokesModule$Cursor Dot;
    public static final /* enum */ KeystrokesModule$Cursor Ring;
    public static final /* enum */ KeystrokesModule$Cursor Cross;
    public static final /* enum */ KeystrokesModule$Cursor Square;
    private static final KeystrokesModule$Cursor[] $VALUES;

    public static KeystrokesModule$Cursor[] values() {
        return (KeystrokesModule$Cursor[])$VALUES.clone();
    }

    public static KeystrokesModule$Cursor valueOf(String string) {
        return Enum.valueOf(KeystrokesModule$Cursor.class, string);
    }

    private static KeystrokesModule$Cursor[] $values() {
        return new KeystrokesModule$Cursor[]{Dot, Ring, Cross, Square};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "\u0012\f;EY\u0003\u0015\u0011 ";
                var5_3 = "\u0012\f;EY\u0003\u0015\u0011 ".length();
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
                    var3_2 = "\u0003\u0017:Q\u0006\u0002\u000f!WXD";
                    var5_3 = "\u0003\u0017:Q\u0006\u0002\u000f!WXD".length();
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
                        v10 = 81;
                        break;
                    }
                    case 1: {
                        v10 = 126;
                        break;
                    }
                    case 2: {
                        v10 = 84;
                        break;
                    }
                    case 3: {
                        v10 = 54;
                        break;
                    }
                    case 4: {
                        v10 = 42;
                        break;
                    }
                    case 5: {
                        v10 = 33;
                        break;
                    }
                    default: {
                        v10 = 43;
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
        KeystrokesModule$Cursor.Dot = new KeystrokesModule$Cursor();
        KeystrokesModule$Cursor.Ring = new KeystrokesModule$Cursor();
        KeystrokesModule$Cursor.Cross = new KeystrokesModule$Cursor();
        KeystrokesModule$Cursor.Square = new KeystrokesModule$Cursor();
        KeystrokesModule$Cursor.$VALUES = KeystrokesModule$Cursor.$values();
    }
}
