package dev.quelchi.aurora.popchams;

final class ChamExtractor$BodyPart
extends Enum<ChamExtractor$BodyPart> {
    public static final /* enum */ ChamExtractor$BodyPart HEAD;
    public static final /* enum */ ChamExtractor$BodyPart BODY;
    public static final /* enum */ ChamExtractor$BodyPart LEFT_ARM;
    public static final /* enum */ ChamExtractor$BodyPart RIGHT_ARM;
    public static final /* enum */ ChamExtractor$BodyPart LEFT_LEG;
    public static final /* enum */ ChamExtractor$BodyPart RIGHT_LEG;
    static final ChamExtractor$BodyPart[] VALUES;
    private static final ChamExtractor$BodyPart[] $VALUES;

    public static ChamExtractor$BodyPart[] values() {
        return (ChamExtractor$BodyPart[])$VALUES.clone();
    }

    public static ChamExtractor$BodyPart valueOf(String string) {
        return Enum.valueOf(ChamExtractor$BodyPart.class, string);
    }

    private static ChamExtractor$BodyPart[] $values() {
        return new ChamExtractor$BodyPart[]{HEAD, BODY, LEFT_ARM, RIGHT_ARM, LEFT_LEG, RIGHT_LEG};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[6];
                var4_1 = 0;
                var3_2 = "d\b6\u0001\u0004n\u00023\u001c\b`\b1\u0011<tea\t~\u00040\r7j{i\n";
                var5_3 = "d\b6\u0001\u0004n\u00023\u001c\b`\b1\u0011<tea\t~\u00040\r7j{i\n".length();
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
                    var3_2 = "~\u00040\r7jv~\u0000\b`\b1\u0011<yrk";
                    var5_3 = "~\u00040\r7jv~\u0000\b`\b1\u0011<yrk".length();
                    var2_4 = 9;
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
                        v10 = 44;
                        break;
                    }
                    case 1: {
                        v10 = 77;
                        break;
                    }
                    case 2: {
                        v10 = 119;
                        break;
                    }
                    case 3: {
                        v10 = 69;
                        break;
                    }
                    case 4: {
                        v10 = 99;
                        break;
                    }
                    case 5: {
                        v10 = 53;
                        break;
                    }
                    default: {
                        v10 = 55;
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
        ChamExtractor$BodyPart.HEAD = new ChamExtractor$BodyPart();
        ChamExtractor$BodyPart.BODY = new ChamExtractor$BodyPart();
        ChamExtractor$BodyPart.LEFT_ARM = new ChamExtractor$BodyPart();
        ChamExtractor$BodyPart.RIGHT_ARM = new ChamExtractor$BodyPart();
        ChamExtractor$BodyPart.LEFT_LEG = new ChamExtractor$BodyPart();
        ChamExtractor$BodyPart.RIGHT_LEG = new ChamExtractor$BodyPart();
        ChamExtractor$BodyPart.$VALUES = ChamExtractor$BodyPart.$values();
        ChamExtractor$BodyPart.VALUES = ChamExtractor$BodyPart.values();
    }
}
