package dev.quelchi.aurora.cosmetics.content;

public final class Trails$Origin
extends Enum<Trails$Origin> {
    public static final /* enum */ Trails$Origin BODY;
    public static final /* enum */ Trails$Origin FEET;
    public static final /* enum */ Trails$Origin HANDS;
    public static final /* enum */ Trails$Origin HEAD;
    public static final /* enum */ Trails$Origin WINGS;
    private static final Trails$Origin[] $VALUES;

    public static Trails$Origin[] values() {
        return (Trails$Origin[])$VALUES.clone();
    }

    public static Trails$Origin valueOf(String string) {
        return Enum.valueOf(Trails$Origin.class, string);
    }

    private static Trails$Origin[] $values() {
        return new Trails$Origin[]{BODY, FEET, HANDS, HEAD, WINGS};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[5];
                var4_1 = 0;
                var3_2 = "v\f\u0017B\u0005c\n\u001d\\h\u0004r\u0006\u0016O";
                var5_3 = "v\f\u0017B\u0005c\n\u001d\\h\u0004r\u0006\u0016O".length();
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
                    var3_2 = "|\u0002\u001d_h\u0004|\u0006\u0012_";
                    var5_3 = "|\u0002\u001d_h\u0004|\u0006\u0012_".length();
                    var2_4 = 5;
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
                        v10 = 52;
                        break;
                    }
                    case 1: {
                        v10 = 67;
                        break;
                    }
                    case 2: {
                        v10 = 83;
                        break;
                    }
                    case 3: {
                        v10 = 27;
                        break;
                    }
                    case 4: {
                        v10 = 59;
                        break;
                    }
                    case 5: {
                        v10 = 85;
                        break;
                    }
                    default: {
                        v10 = 122;
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
        Trails$Origin.BODY = new Trails$Origin();
        Trails$Origin.FEET = new Trails$Origin();
        Trails$Origin.HANDS = new Trails$Origin();
        Trails$Origin.HEAD = new Trails$Origin();
        Trails$Origin.WINGS = new Trails$Origin();
        Trails$Origin.$VALUES = Trails$Origin.$values();
    }
}
