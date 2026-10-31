package dev.quelchi.aurora.module.modules;

public final class AutoDeafenModule$Trigger
extends Enum<AutoDeafenModule$Trigger> {
    public static final /* enum */ AutoDeafenModule$Trigger COMBAT;
    public static final /* enum */ AutoDeafenModule$Trigger POPS;
    private static final AutoDeafenModule$Trigger[] $VALUES;

    public static AutoDeafenModule$Trigger[] values() {
        return (AutoDeafenModule$Trigger[])$VALUES.clone();
    }

    public static AutoDeafenModule$Trigger valueOf(String string) {
        return Enum.valueOf(AutoDeafenModule$Trigger.class, string);
    }

    private static AutoDeafenModule$Trigger[] $values() {
        return new AutoDeafenModule$Trigger[]{COMBAT, POPS};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "Z\u0015\\\u0005\u0006I\u0015A\u0014!\u000f";
            var5_3 = "Z\u0015\\\u0005\u0006I\u0015A\u0014!\u000f".length();
            var2_4 = 4;
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
                        v6 = 10;
                        break;
                    }
                    case 1: {
                        v6 = 90;
                        break;
                    }
                    case 2: {
                        v6 = 12;
                        break;
                    }
                    case 3: {
                        v6 = 86;
                        break;
                    }
                    case 4: {
                        v6 = 96;
                        break;
                    }
                    case 5: {
                        v6 = 91;
                        break;
                    }
                    default: {
                        v6 = 39;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        AutoDeafenModule$Trigger.COMBAT = new AutoDeafenModule$Trigger();
        AutoDeafenModule$Trigger.POPS = new AutoDeafenModule$Trigger();
        AutoDeafenModule$Trigger.$VALUES = AutoDeafenModule$Trigger.$values();
    }
}
