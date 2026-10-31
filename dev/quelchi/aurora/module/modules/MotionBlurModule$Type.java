package dev.quelchi.aurora.module.modules;

public final class MotionBlurModule$Type
extends Enum<MotionBlurModule$Type> {
    public static final /* enum */ MotionBlurModule$Type TRAIL;
    public static final /* enum */ MotionBlurModule$Type CAMERA;
    public static final /* enum */ MotionBlurModule$Type FRAME_BLEND;
    private final String label;
    private static final MotionBlurModule$Type[] $VALUES;

    public static MotionBlurModule$Type[] values() {
        return (MotionBlurModule$Type[])$VALUES.clone();
    }

    public static MotionBlurModule$Type valueOf(String string) {
        return Enum.valueOf(MotionBlurModule$Type.class, string);
    }

    private MotionBlurModule$Type(String string2) {
        this.label = string2;
    }

    public String toString() {
        return this.label;
    }

    private static MotionBlurModule$Type[] $values() {
        return new MotionBlurModule$Type[]{TRAIL, CAMERA, FRAME_BLEND};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[6];
                var4_1 = 0;
                var3_2 = "$MI\u000bN\u000b6MI\u000fGvQ\u001cZF\u0006\u0005$mi+n\u00063~e'p\u0017";
                var5_3 = "$MI\u000bN\u000b6MI\u000fGvQ\u001cZF\u0006\u0005$mi+n\u00063~e'p\u0017".length();
                var2_4 = 5;
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
                    var3_2 = "6mi/g\tq<zf&\u00063^E\u0007P7";
                    var5_3 = "6mi/g\tq<zf&\u00063^E\u0007P7".length();
                    var2_4 = 11;
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
                        v10 = 112;
                        break;
                    }
                    case 1: {
                        v10 = 63;
                        break;
                    }
                    case 2: {
                        v10 = 40;
                        break;
                    }
                    case 3: {
                        v10 = 98;
                        break;
                    }
                    case 4: {
                        v10 = 34;
                        break;
                    }
                    case 5: {
                        v10 = 86;
                        break;
                    }
                    default: {
                        v10 = 51;
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
        MotionBlurModule$Type.TRAIL = new MotionBlurModule$Type(var0[0]);
        MotionBlurModule$Type.CAMERA = new MotionBlurModule$Type(var0[5]);
        MotionBlurModule$Type.FRAME_BLEND = new MotionBlurModule$Type(var0[1]);
        MotionBlurModule$Type.$VALUES = MotionBlurModule$Type.$values();
    }
}
