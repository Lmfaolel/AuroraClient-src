package dev.quelchi.aurora.module.modules;

public final class BlockOverlayModule$Style
extends Enum<BlockOverlayModule$Style> {
    public static final /* enum */ BlockOverlayModule$Style Default;
    public static final /* enum */ BlockOverlayModule$Style Glow;
    public static final /* enum */ BlockOverlayModule$Style Gradient;
    public static final /* enum */ BlockOverlayModule$Style Pulse;
    private static final BlockOverlayModule$Style[] $VALUES;

    public static BlockOverlayModule$Style[] values() {
        return (BlockOverlayModule$Style[])$VALUES.clone();
    }

    public static BlockOverlayModule$Style valueOf(String string) {
        return Enum.valueOf(BlockOverlayModule$Style.class, string);
    }

    private static BlockOverlayModule$Style[] $values() {
        return new BlockOverlayModule$Style[]{Default, Glow, Gradient, Pulse};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "}Y@0\u001f,k\b~NG5\u0003%qM";
                var5_3 = "}Y@0\u001f,k\b~NG5\u0003%qM".length();
                var2_4 = 7;
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
                    var3_2 = "~PI&\u0005iIJ\"\u000f";
                    var5_3 = "~PI&\u0005iIJ\"\u000f".length();
                    var2_4 = 4;
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
                        v10 = 57;
                        break;
                    }
                    case 1: {
                        v10 = 60;
                        break;
                    }
                    case 2: {
                        v10 = 38;
                        break;
                    }
                    case 3: {
                        v10 = 81;
                        break;
                    }
                    case 4: {
                        v10 = 106;
                        break;
                    }
                    case 5: {
                        v10 = 64;
                        break;
                    }
                    default: {
                        v10 = 31;
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
        BlockOverlayModule$Style.Default = new BlockOverlayModule$Style();
        BlockOverlayModule$Style.Glow = new BlockOverlayModule$Style();
        BlockOverlayModule$Style.Gradient = new BlockOverlayModule$Style();
        BlockOverlayModule$Style.Pulse = new BlockOverlayModule$Style();
        BlockOverlayModule$Style.$VALUES = BlockOverlayModule$Style.$values();
    }
}
