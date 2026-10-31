package dev.quelchi.aurora.module.modules;

public final class NametagsModule$TagSide
extends Enum<NametagsModule$TagSide> {
    public static final /* enum */ NametagsModule$TagSide Left;
    public static final /* enum */ NametagsModule$TagSide Right;
    private static final NametagsModule$TagSide[] $VALUES;

    public static NametagsModule$TagSide[] values() {
        return (NametagsModule$TagSide[])$VALUES.clone();
    }

    public static NametagsModule$TagSide valueOf(String string) {
        return Enum.valueOf(NametagsModule$TagSide.class, string);
    }

    private static NametagsModule$TagSide[] $values() {
        return new NametagsModule$TagSide[]{Left, Right};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "\u000eA&Sg\u0004\u0010M'O";
            var5_3 = "\u000eA&Sg\u0004\u0010M'O".length();
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
                        v6 = 92;
                        break;
                    }
                    case 1: {
                        v6 = 40;
                        break;
                    }
                    case 2: {
                        v6 = 65;
                        break;
                    }
                    case 3: {
                        v6 = 59;
                        break;
                    }
                    case 4: {
                        v6 = 19;
                        break;
                    }
                    case 5: {
                        v6 = 37;
                        break;
                    }
                    default: {
                        v6 = 31;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        NametagsModule$TagSide.Left = new NametagsModule$TagSide();
        NametagsModule$TagSide.Right = new NametagsModule$TagSide();
        NametagsModule$TagSide.$VALUES = NametagsModule$TagSide.$values();
    }
}
