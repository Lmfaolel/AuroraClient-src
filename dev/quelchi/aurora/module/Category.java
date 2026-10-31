package dev.quelchi.aurora.module;

public final class Category
extends Enum<Category> {
    public static final /* enum */ Category COMBAT;
    public static final /* enum */ Category HUD;
    public static final /* enum */ Category RENDER;
    public static final /* enum */ Category PLAYER;
    public static final /* enum */ Category MISC;
    public final String displayName;
    private static final /* synthetic */ Category[] $VALUES;
    public static int a;

    public static Category[] values() {
        return (Category[])$VALUES.clone();
    }

    public static Category valueOf(String string) {
        return Enum.valueOf(Category.class, string);
    }

    private Category(String string2) {
        this.displayName = string2;
    }

    private static /* synthetic */ Category[] $values() {
        return new Category[]{COMBAT, HUD, RENDER, PLAYER, MISC};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[10];
                var4_1 = 0;
                var3_2 = "V'J5\u0006X!T4_!\u0006K\"X/['\u0007M\u0007j\u0003\u007f\u00198\u0006I+W2['\u0003K\u0018I\u0006K\u0002x\u000f{\u0007\u0003S;]";
                var5_3 = "V'J5\u0006X!T4_!\u0006K\"X/['\u0007M\u0007j\u0003\u007f\u00198\u0006I+W2['\u0003K\u0018I\u0006K\u0002x\u000f{\u0007\u0003S;]".length();
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
                    var3_2 = "V\u0007j\u0015\u0003S;]";
                    var5_3 = "V\u0007j\u0015\u0003S;]".length();
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
                        v10 = 27;
                        break;
                    }
                    case 1: {
                        v10 = 110;
                        break;
                    }
                    case 2: {
                        v10 = 25;
                        break;
                    }
                    case 3: {
                        v10 = 118;
                        break;
                    }
                    case 4: {
                        v10 = 30;
                        break;
                    }
                    case 5: {
                        v10 = 117;
                        break;
                    }
                    default: {
                        v10 = 75;
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
        Category.COMBAT = new Category(var0[5]);
        Category.HUD = new Category(var0[7]);
        Category.RENDER = new Category(var0[3]);
        Category.PLAYER = new Category(var0[6]);
        Category.MISC = new Category(var0[8]);
        Category.$VALUES = Category.$values();
    }
}
