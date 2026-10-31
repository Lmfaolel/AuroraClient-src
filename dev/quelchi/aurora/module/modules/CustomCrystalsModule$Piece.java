package dev.quelchi.aurora.module.modules;

public final class CustomCrystalsModule$Piece
extends Enum<CustomCrystalsModule$Piece> {
    public static final /* enum */ CustomCrystalsModule$Piece CORE;
    public static final /* enum */ CustomCrystalsModule$Piece INNER;
    public static final /* enum */ CustomCrystalsModule$Piece OUTER;
    private static final CustomCrystalsModule$Piece[] $VALUES;

    public static CustomCrystalsModule$Piece[] values() {
        return (CustomCrystalsModule$Piece[])$VALUES.clone();
    }

    public static CustomCrystalsModule$Piece valueOf(String string) {
        return Enum.valueOf(CustomCrystalsModule$Piece.class, string);
    }

    private static CustomCrystalsModule$Piece[] $values() {
        return new CustomCrystalsModule$Piece[]{CORE, INNER, OUTER};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "2!\u001eH'\u00054:\u0004H'\u0004>;\u0018H";
            var5_3 = "2!\u001eH'\u00054:\u0004H'\u0004>;\u0018H".length();
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
                        v6 = 125;
                        break;
                    }
                    case 1: {
                        v6 = 116;
                        break;
                    }
                    case 2: {
                        v6 = 74;
                        break;
                    }
                    case 3: {
                        v6 = 13;
                        break;
                    }
                    case 4: {
                        v6 = 117;
                        break;
                    }
                    case 5: {
                        v6 = 87;
                        break;
                    }
                    default: {
                        v6 = 108;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        CustomCrystalsModule$Piece.CORE = new CustomCrystalsModule$Piece();
        CustomCrystalsModule$Piece.INNER = new CustomCrystalsModule$Piece();
        CustomCrystalsModule$Piece.OUTER = new CustomCrystalsModule$Piece();
        CustomCrystalsModule$Piece.$VALUES = CustomCrystalsModule$Piece.$values();
    }
}
