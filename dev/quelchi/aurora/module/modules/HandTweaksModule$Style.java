package dev.quelchi.aurora.module.modules;

public final class HandTweaksModule$Style
extends Enum<HandTweaksModule$Style> {
    public static final /* enum */ HandTweaksModule$Style Normal;
    public static final /* enum */ HandTweaksModule$Style Smooth;
    public static final /* enum */ HandTweaksModule$Style Swipe;
    public static final /* enum */ HandTweaksModule$Style Stab;
    public static final /* enum */ HandTweaksModule$Style Custom;
    private static final HandTweaksModule$Style[] $VALUES;

    public static HandTweaksModule$Style[] values() {
        return (HandTweaksModule$Style[])$VALUES.clone();
    }

    public static HandTweaksModule$Style valueOf(String string) {
        return Enum.valueOf(HandTweaksModule$Style.class, string);
    }

    private static HandTweaksModule$Style[] $values() {
        return new HandTweaksModule$Style[]{Normal, Smooth, Swipe, Stab, Custom};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[5];
                var4_1 = 0;
                var3_2 = "uZ(\u0012\u0006uC&\u001f\u001b3\u0005uY \u0000\n";
                var5_3 = "uZ(\u0012\u0006uC&\u001f\u001b3\u0005uY \u0000\n".length();
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
                    var3_2 = "e[:\u0004\u00006\u0006hA;\u001d\u000e7";
                    var5_3 = "e[:\u0004\u00006\u0006hA;\u001d\u000e7".length();
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
                        v10 = 38;
                        break;
                    }
                    case 1: {
                        v10 = 46;
                        break;
                    }
                    case 2: {
                        v10 = 73;
                        break;
                    }
                    case 3: {
                        v10 = 112;
                        break;
                    }
                    case 4: {
                        v10 = 111;
                        break;
                    }
                    case 5: {
                        v10 = 91;
                        break;
                    }
                    default: {
                        v10 = 63;
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
        HandTweaksModule$Style.Normal = new HandTweaksModule$Style();
        HandTweaksModule$Style.Smooth = new HandTweaksModule$Style();
        HandTweaksModule$Style.Swipe = new HandTweaksModule$Style();
        HandTweaksModule$Style.Stab = new HandTweaksModule$Style();
        HandTweaksModule$Style.Custom = new HandTweaksModule$Style();
        HandTweaksModule$Style.$VALUES = HandTweaksModule$Style.$values();
    }
}
