package dev.quelchi.aurora.glass.hud;

public final class Anchor
extends Enum<Anchor> {
    public static final /* enum */ Anchor TOP_LEFT;
    public static final /* enum */ Anchor TOP_CENTER;
    public static final /* enum */ Anchor TOP_RIGHT;
    public static final /* enum */ Anchor CENTER_LEFT;
    public static final /* enum */ Anchor CENTER;
    public static final /* enum */ Anchor CENTER_RIGHT;
    public static final /* enum */ Anchor BOTTOM_LEFT;
    public static final /* enum */ Anchor BOTTOM_CENTER;
    public static final /* enum */ Anchor BOTTOM_RIGHT;
    private final float x;
    private final float y;
    private static final /* synthetic */ Anchor[] $VALUES;

    public static Anchor[] values() {
        return (Anchor[])$VALUES.clone();
    }

    public static Anchor valueOf(String string) {
        return Enum.valueOf(Anchor.class, string);
    }

    private Anchor(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    int x(int n, int n2) {
        return Math.round((float)(n - n2) * this.x);
    }

    int y(int n, int n2) {
        return Math.round((float)(n - n2) * this.y);
    }

    private static /* synthetic */ Anchor[] $values() {
        return new Anchor[]{TOP_LEFT, TOP_CENTER, TOP_RIGHT, CENTER_LEFT, CENTER, CENTER_RIGHT, BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[9];
                var4_1 = 0;
                var3_2 = "p\u001bMS`}o\u007f\u001bES\bg\u0011SXijvg\tg\u0011SXwfw{\n\u0006p\u001bMS`}\fp\u001bMS`}oa\u0017DOq\fq\u0011WSjboa\u0017DOq\u000bq\u0011WSjbo\u007f\u001bES";
                var5_3 = "p\u001bMS`}o\u007f\u001bES\bg\u0011SXijvg\tg\u0011SXwfw{\n\u0006p\u001bMS`}\fp\u001bMS`}oa\u0017DOq\fq\u0011WSjboa\u0017DOq\u000bq\u0011WSjbo\u007f\u001bES".length();
                var2_4 = 11;
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
                    var3_2 = "q\u0011WSjbop\u001bMS`}\ng\u0011SXfj~g\u001bQ";
                    var5_3 = "q\u0011WSjbop\u001bMS`}\ng\u0011SXfj~g\u001bQ".length();
                    var2_4 = 13;
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
                        v10 = 51;
                        break;
                    }
                    case 1: {
                        v10 = 94;
                        break;
                    }
                    case 2: {
                        v10 = 3;
                        break;
                    }
                    case 3: {
                        v10 = 7;
                        break;
                    }
                    case 4: {
                        v10 = 37;
                        break;
                    }
                    case 5: {
                        v10 = 47;
                        break;
                    }
                    default: {
                        v10 = 48;
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
        Anchor.TOP_LEFT = new Anchor(0.0f, 0.0f);
        Anchor.TOP_CENTER = new Anchor(0.5f, 0.0f);
        Anchor.TOP_RIGHT = new Anchor(1.0f, 0.0f);
        Anchor.CENTER_LEFT = new Anchor(0.0f, 0.5f);
        Anchor.CENTER = new Anchor(0.5f, 0.5f);
        Anchor.CENTER_RIGHT = new Anchor(1.0f, 0.5f);
        Anchor.BOTTOM_LEFT = new Anchor(0.0f, 1.0f);
        Anchor.BOTTOM_CENTER = new Anchor(0.5f, 1.0f);
        Anchor.BOTTOM_RIGHT = new Anchor(1.0f, 1.0f);
        Anchor.$VALUES = Anchor.$values();
    }
}
