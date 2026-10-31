package dev.quelchi.aurora.module.modules;

public final class MotionBlurModule$Strength
extends Enum<MotionBlurModule$Strength> {
    public static final /* enum */ MotionBlurModule$Strength Low;
    public static final /* enum */ MotionBlurModule$Strength Medium;
    public static final /* enum */ MotionBlurModule$Strength High;
    private static final MotionBlurModule$Strength[] $VALUES;

    public static MotionBlurModule$Strength[] values() {
        return (MotionBlurModule$Strength[])$VALUES.clone();
    }

    public static MotionBlurModule$Strength valueOf(String string) {
        return Enum.valueOf(MotionBlurModule$Strength.class, string);
    }

    private static MotionBlurModule$Strength[] $values() {
        return new MotionBlurModule$Strength[]{Low, Medium, High};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "$\u001b\u001a\u0013\u0003 \u001d\n\u0006!\u0017\u0019\u0012\r\u001a";
            var5_3 = "$\u001b\u001a\u0013\u0003 \u001d\n\u0006!\u0017\u0019\u0012\r\u001a".length();
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
                        v6 = 108;
                        break;
                    }
                    case 1: {
                        v6 = 114;
                        break;
                    }
                    case 2: {
                        v6 = 125;
                        break;
                    }
                    case 3: {
                        v6 = 123;
                        break;
                    }
                    case 4: {
                        v6 = 120;
                        break;
                    }
                    case 5: {
                        v6 = 119;
                        break;
                    }
                    default: {
                        v6 = 25;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        MotionBlurModule$Strength.Low = new MotionBlurModule$Strength();
        MotionBlurModule$Strength.Medium = new MotionBlurModule$Strength();
        MotionBlurModule$Strength.High = new MotionBlurModule$Strength();
        MotionBlurModule$Strength.$VALUES = MotionBlurModule$Strength.$values();
    }
}
