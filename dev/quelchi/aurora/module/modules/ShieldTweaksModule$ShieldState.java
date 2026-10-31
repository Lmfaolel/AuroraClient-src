package dev.quelchi.aurora.module.modules;

public final class ShieldTweaksModule$ShieldState
extends Enum<ShieldTweaksModule$ShieldState> {
    public static final /* enum */ ShieldTweaksModule$ShieldState READY;
    public static final /* enum */ ShieldTweaksModule$ShieldState BLOCKING;
    public static final /* enum */ ShieldTweaksModule$ShieldState DISABLED;
    private static final ShieldTweaksModule$ShieldState[] $VALUES;

    public static ShieldTweaksModule$ShieldState[] values() {
        return (ShieldTweaksModule$ShieldState[])$VALUES.clone();
    }

    public static ShieldTweaksModule$ShieldState valueOf(String string) {
        return Enum.valueOf(ShieldTweaksModule$ShieldState.class, string);
    }

    private static ShieldTweaksModule$ShieldState[] $values() {
        return new ShieldTweaksModule$ShieldState[]{READY, BLOCKING, DISABLED};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "Go@Y2k!G\bAj\\[;n*D\u0005QcR\\)";
            var5_3 = "Go@Y2k!G\bAj\\[;n*D\u0005QcR\\)".length();
            var2_4 = 8;
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
                        v6 = 3;
                        break;
                    }
                    case 1: {
                        v6 = 38;
                        break;
                    }
                    case 2: {
                        v6 = 19;
                        break;
                    }
                    case 3: {
                        v6 = 24;
                        break;
                    }
                    case 4: {
                        v6 = 112;
                        break;
                    }
                    case 5: {
                        v6 = 39;
                        break;
                    }
                    default: {
                        v6 = 100;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        ShieldTweaksModule$ShieldState.READY = new ShieldTweaksModule$ShieldState();
        ShieldTweaksModule$ShieldState.BLOCKING = new ShieldTweaksModule$ShieldState();
        ShieldTweaksModule$ShieldState.DISABLED = new ShieldTweaksModule$ShieldState();
        ShieldTweaksModule$ShieldState.$VALUES = ShieldTweaksModule$ShieldState.$values();
    }
}
