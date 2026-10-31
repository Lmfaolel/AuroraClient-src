package dev.quelchi.aurora.cosmetics.backend;

final class CapePacks$Got
extends Enum<CapePacks$Got> {
    public static final /* enum */ CapePacks$Got OK;
    public static final /* enum */ CapePacks$Got RETRY;
    public static final /* enum */ CapePacks$Got BAD;
    private static final CapePacks$Got[] $VALUES;

    public static CapePacks$Got[] values() {
        return (CapePacks$Got[])$VALUES.clone();
    }

    public static CapePacks$Got valueOf(String string) {
        return Enum.valueOf(CapePacks$Got.class, string);
    }

    private static CapePacks$Got[] $values() {
        return new CapePacks$Got[]{OK, RETRY, BAD};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "g\u0019w\u0002j\u0013\u0005w\u001dg\u001e`";
            var5_3 = "g\u0019w\u0002j\u0013\u0005w\u001dg\u001e`".length();
            var2_4 = 3;
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
                        v6 = 37;
                        break;
                    }
                    case 1: {
                        v6 = 88;
                        break;
                    }
                    case 2: {
                        v6 = 51;
                        break;
                    }
                    case 3: {
                        v6 = 76;
                        break;
                    }
                    case 4: {
                        v6 = 57;
                        break;
                    }
                    case 5: {
                        v6 = 111;
                        break;
                    }
                    default: {
                        v6 = 14;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        CapePacks$Got.OK = new CapePacks$Got();
        CapePacks$Got.RETRY = new CapePacks$Got();
        CapePacks$Got.BAD = new CapePacks$Got();
        CapePacks$Got.$VALUES = CapePacks$Got.$values();
    }
}
