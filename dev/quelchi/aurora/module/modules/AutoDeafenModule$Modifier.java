package dev.quelchi.aurora.module.modules;

public final class AutoDeafenModule$Modifier
extends Enum<AutoDeafenModule$Modifier> {
    public static final /* enum */ AutoDeafenModule$Modifier NONE;
    public static final /* enum */ AutoDeafenModule$Modifier CTRL;
    public static final /* enum */ AutoDeafenModule$Modifier SHIFT;
    public static final /* enum */ AutoDeafenModule$Modifier ALT;
    public static final /* enum */ AutoDeafenModule$Modifier CTRL_SHIFT;
    public static final /* enum */ AutoDeafenModule$Modifier CTRL_ALT;
    public static final /* enum */ AutoDeafenModule$Modifier SHIFT_ALT;
    private static final AutoDeafenModule$Modifier[] $VALUES;

    public static AutoDeafenModule$Modifier[] values() {
        return (AutoDeafenModule$Modifier[])$VALUES.clone();
    }

    public static AutoDeafenModule$Modifier valueOf(String string) {
        return Enum.valueOf(AutoDeafenModule$Modifier.class, string);
    }

    private static AutoDeafenModule$Modifier[] $values() {
        return new AutoDeafenModule$Modifier[]{NONE, CTRL, SHIFT, ALT, CTRL_SHIFT, CTRL_ALT, SHIFT_ALT};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[7];
                var4_1 = 0;
                var3_2 = "3F!\u00041^'D\u0004<E;M\n1^'D70e;L!\u0005!B<N<";
                var5_3 = "3F!\u00041^'D\u0004<E;M\n1^'D70e;L!\u0005!B<N<".length();
                var2_4 = 3;
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
                    var3_2 = "1^'D7\"a&\t!B<N<<l>^";
                    var5_3 = "1^'D7\"a&\t!B<N<<l>^".length();
                    var2_4 = 8;
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
                        v10 = 114;
                        break;
                    }
                    case 1: {
                        v10 = 10;
                        break;
                    }
                    case 2: {
                        v10 = 117;
                        break;
                    }
                    case 3: {
                        v10 = 8;
                        break;
                    }
                    case 4: {
                        v10 = 104;
                        break;
                    }
                    case 5: {
                        v10 = 99;
                        break;
                    }
                    default: {
                        v10 = 45;
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
        AutoDeafenModule$Modifier.NONE = new AutoDeafenModule$Modifier();
        AutoDeafenModule$Modifier.CTRL = new AutoDeafenModule$Modifier();
        AutoDeafenModule$Modifier.SHIFT = new AutoDeafenModule$Modifier();
        AutoDeafenModule$Modifier.ALT = new AutoDeafenModule$Modifier();
        AutoDeafenModule$Modifier.CTRL_SHIFT = new AutoDeafenModule$Modifier();
        AutoDeafenModule$Modifier.CTRL_ALT = new AutoDeafenModule$Modifier();
        AutoDeafenModule$Modifier.SHIFT_ALT = new AutoDeafenModule$Modifier();
        AutoDeafenModule$Modifier.$VALUES = AutoDeafenModule$Modifier.$values();
    }
}
