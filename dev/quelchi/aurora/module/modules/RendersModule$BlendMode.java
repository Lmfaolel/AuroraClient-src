package dev.quelchi.aurora.module.modules;

public final class RendersModule$BlendMode
extends Enum<RendersModule$BlendMode> {
    public static final /* enum */ RendersModule$BlendMode Mix;
    public static final /* enum */ RendersModule$BlendMode Add;
    public static final /* enum */ RendersModule$BlendMode Multiply;
    public static final /* enum */ RendersModule$BlendMode Screen;
    public static final /* enum */ RendersModule$BlendMode Overlay;
    public static final /* enum */ RendersModule$BlendMode Difference;
    private static final RendersModule$BlendMode[] $VALUES;

    public static RendersModule$BlendMode[] values() {
        return (RendersModule$BlendMode[])$VALUES.clone();
    }

    public static RendersModule$BlendMode valueOf(String string) {
        return Enum.valueOf(RendersModule$BlendMode.class, string);
    }

    private static RendersModule$BlendMode[] $values() {
        return new RendersModule$BlendMode[]{Mix, Add, Multiply, Screen, Overlay, Difference};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[6];
                var4_1 = 0;
                var3_2 = "gXIc2A3MRJ\u0003bUK\u0006pR]`2]\u0003nXW";
                var5_3 = "gXIc2A3MRJ\u0003bUK\u0006pR]`2]\u0003nXW".length();
                var2_4 = 10;
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
                    var3_2 = "lGJw;R/\bnDCq>C:Z";
                    var5_3 = "lGJw;R/\bnDCq>C:Z".length();
                    var2_4 = 7;
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
                        v10 = 35;
                        break;
                    }
                    case 1: {
                        v10 = 49;
                        break;
                    }
                    case 2: {
                        v10 = 47;
                        break;
                    }
                    case 3: {
                        v10 = 5;
                        break;
                    }
                    case 4: {
                        v10 = 87;
                        break;
                    }
                    case 5: {
                        v10 = 51;
                        break;
                    }
                    default: {
                        v10 = 86;
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
        RendersModule$BlendMode.Mix = new RendersModule$BlendMode();
        RendersModule$BlendMode.Add = new RendersModule$BlendMode();
        RendersModule$BlendMode.Multiply = new RendersModule$BlendMode();
        RendersModule$BlendMode.Screen = new RendersModule$BlendMode();
        RendersModule$BlendMode.Overlay = new RendersModule$BlendMode();
        RendersModule$BlendMode.Difference = new RendersModule$BlendMode();
        RendersModule$BlendMode.$VALUES = RendersModule$BlendMode.$values();
    }
}
