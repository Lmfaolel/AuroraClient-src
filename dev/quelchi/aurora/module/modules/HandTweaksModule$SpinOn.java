package dev.quelchi.aurora.module.modules;

public final class HandTweaksModule$SpinOn
extends Enum<HandTweaksModule$SpinOn> {
    public static final /* enum */ HandTweaksModule$SpinOn Attack;
    public static final /* enum */ HandTweaksModule$SpinOn Use;
    public static final /* enum */ HandTweaksModule$SpinOn Both;
    private static final HandTweaksModule$SpinOn[] $VALUES;

    public static HandTweaksModule$SpinOn[] values() {
        return (HandTweaksModule$SpinOn[])$VALUES.clone();
    }

    public static HandTweaksModule$SpinOn valueOf(String string) {
        return Enum.valueOf(HandTweaksModule$SpinOn.class, string);
    }

    private static HandTweaksModule$SpinOn[] $values() {
        return new HandTweaksModule$SpinOn[]{Attack, Use, Both};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "!!}\"\u007f\u0005\u0004\":}+\u00035&l";
            var5_3 = "!!}\"\u007f\u0005\u0004\":}+\u00035&l".length();
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
                        v6 = 96;
                        break;
                    }
                    case 1: {
                        v6 = 85;
                        break;
                    }
                    case 2: {
                        v6 = 9;
                        break;
                    }
                    case 3: {
                        v6 = 67;
                        break;
                    }
                    case 4: {
                        v6 = 28;
                        break;
                    }
                    case 5: {
                        v6 = 110;
                        break;
                    }
                    default: {
                        v6 = 58;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        HandTweaksModule$SpinOn.Attack = new HandTweaksModule$SpinOn();
        HandTweaksModule$SpinOn.Use = new HandTweaksModule$SpinOn();
        HandTweaksModule$SpinOn.Both = new HandTweaksModule$SpinOn();
        HandTweaksModule$SpinOn.$VALUES = HandTweaksModule$SpinOn.$values();
    }
}
