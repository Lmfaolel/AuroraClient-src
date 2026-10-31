package dev.quelchi.aurora.module.modules;

public final class TotemCounterModule$OwnDisplay
extends Enum<TotemCounterModule$OwnDisplay> {
    public static final /* enum */ TotemCounterModule$OwnDisplay Hotbar;
    public static final /* enum */ TotemCounterModule$OwnDisplay Panel;
    private static final TotemCounterModule$OwnDisplay[] $VALUES;

    public static TotemCounterModule$OwnDisplay[] values() {
        return (TotemCounterModule$OwnDisplay[])$VALUES.clone();
    }

    public static TotemCounterModule$OwnDisplay valueOf(String string) {
        return Enum.valueOf(TotemCounterModule$OwnDisplay.class, string);
    }

    private static TotemCounterModule$OwnDisplay[] $values() {
        return new TotemCounterModule$OwnDisplay[]{Hotbar, Panel};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "_6\fP\fU\u0005G8\u0016W\u0001";
            var5_3 = "_6\fP\fU\u0005G8\u0016W\u0001".length();
            var2_4 = 6;
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
                        v6 = 23;
                        break;
                    }
                    case 1: {
                        v6 = 89;
                        break;
                    }
                    case 2: {
                        v6 = 120;
                        break;
                    }
                    case 3: {
                        v6 = 50;
                        break;
                    }
                    case 4: {
                        v6 = 109;
                        break;
                    }
                    case 5: {
                        v6 = 39;
                        break;
                    }
                    default: {
                        v6 = 82;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        TotemCounterModule$OwnDisplay.Hotbar = new TotemCounterModule$OwnDisplay();
        TotemCounterModule$OwnDisplay.Panel = new TotemCounterModule$OwnDisplay();
        TotemCounterModule$OwnDisplay.$VALUES = TotemCounterModule$OwnDisplay.$values();
    }
}
