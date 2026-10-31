package dev.quelchi.aurora.gui;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

record PackPreview$Slot(String texture, String side, Item fallback, Identifier sprite) {
    private static final String[] a;

    static PackPreview$Slot of(String string, Item item) {
        return new PackPreview$Slot(string, null, item, null);
    }

    static PackPreview$Slot block(String string, String string2, Item item) {
        return new PackPreview$Slot(string, string2, item, null);
    }

    static PackPreview$Slot hud(String string) {
        String[] stringArray = a;
        return new PackPreview$Slot(stringArray[1] + string, null, null, Identifier.method_60656((String)(stringArray[0] + string)));
    }

    
    static {
        block12: {
            var5 = new String[2];
            var3_1 = 0;
            var2_2 = "\u007f\t\u000e7#Y/e\bE\u0016p\t\u000378L<~\b\u000fkdT;sS\u0002}*N:8";
            var4_3 = "\u007f\t\u000e7#Y/e\bE\u0016p\t\u000378L<~\b\u000fkdT;sS\u0002}*N:8".length();
            var1_4 = 10;
            var0_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var5[var3_1++] = new String(v0).intern();
                if ((var0_5 += var1_4) < var4_3) {
                    var1_4 = var2_2.charAt(var0_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var0_5;
            v2 = var2_2.substring(v1, v1 + var1_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 23;
                        break;
                    }
                    case 1: {
                        v6 = 124;
                        break;
                    }
                    case 2: {
                        v6 = 106;
                        break;
                    }
                    case 3: {
                        v6 = 24;
                        break;
                    }
                    case 4: {
                        v6 = 75;
                        break;
                    }
                    case 5: {
                        v6 = 60;
                        break;
                    }
                    default: {
                        v6 = 78;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        PackPreview$Slot.a = var5;
    }
}
