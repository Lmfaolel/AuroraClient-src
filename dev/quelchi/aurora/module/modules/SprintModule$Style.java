package dev.quelchi.aurora.module.modules;

public final class SprintModule$Style
extends Enum<SprintModule$Style> {
    public static final /* enum */ SprintModule$Style Full;
    public static final /* enum */ SprintModule$Style Short;
    private static final SprintModule$Style[] $VALUES;

    public static SprintModule$Style[] values() {
        return (SprintModule$Style[])$VALUES.clone();
    }

    public static SprintModule$Style valueOf(String string) {
        return Enum.valueOf(SprintModule$Style.class, string);
    }

    private static SprintModule$Style[] $values() {
        return new SprintModule$Style[]{Full, Short};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "pyC3\u001f\u0004ed@-";
            var5_3 = "pyC3\u001f\u0004ed@-".length();
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
                        v6 = 35;
                        break;
                    }
                    case 1: {
                        v6 = 17;
                        break;
                    }
                    case 2: {
                        v6 = 44;
                        break;
                    }
                    case 3: {
                        v6 = 65;
                        break;
                    }
                    case 4: {
                        v6 = 107;
                        break;
                    }
                    case 5: {
                        v6 = 111;
                        break;
                    }
                    default: {
                        v6 = 117;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        SprintModule$Style.Full = new SprintModule$Style();
        SprintModule$Style.Short = new SprintModule$Style();
        SprintModule$Style.$VALUES = SprintModule$Style.$values();
    }
}
