package dev.quelchi.aurora.hud;

public final class ItemHighlights$Style
extends Enum<ItemHighlights$Style> {
    public static final /* enum */ ItemHighlights$Style Glow;
    public static final /* enum */ ItemHighlights$Style Border;
    public static final /* enum */ ItemHighlights$Style Corners;
    public static final /* enum */ ItemHighlights$Style Solid;
    private static final ItemHighlights$Style[] $VALUES;
    public static int a;

    public static ItemHighlights$Style[] values() {
        return (ItemHighlights$Style[])$VALUES.clone();
    }

    public static ItemHighlights$Style valueOf(String string) {
        return Enum.valueOf(ItemHighlights$Style.class, string);
    }

    private static ItemHighlights$Style[] $values() {
        return new ItemHighlights$Style[]{Glow, Border, Corners, Solid};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "F90hwC\u0005W9.ev";
                var5_3 = "F90hwC\u0005W9.ev".length();
                var2_4 = 6;
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
                    var3_2 = "G90bwC*\u0004C:-{";
                    var5_3 = "G90bwC*\u0004C:-{".length();
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
                        v10 = 4;
                        break;
                    }
                    case 1: {
                        v10 = 86;
                        break;
                    }
                    case 2: {
                        v10 = 66;
                        break;
                    }
                    case 3: {
                        v10 = 12;
                        break;
                    }
                    case 4: {
                        v10 = 18;
                        break;
                    }
                    case 5: {
                        v10 = 49;
                        break;
                    }
                    default: {
                        v10 = 89;
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
        ItemHighlights$Style.Glow = new ItemHighlights$Style();
        ItemHighlights$Style.Border = new ItemHighlights$Style();
        ItemHighlights$Style.Corners = new ItemHighlights$Style();
        ItemHighlights$Style.Solid = new ItemHighlights$Style();
        ItemHighlights$Style.$VALUES = ItemHighlights$Style.$values();
    }
}
