package dev.quelchi.aurora.input;

import dev.quelchi.aurora.input.FreeLook;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public final class AuroraKeys {
    private static final KeyBinding.Category CATEGORY;
    public static KeyBinding menu;
    public static KeyBinding emoteWheel;
    private static final String[] a;

    private AuroraKeys() {
    }

    public static void register() {
        String[] stringArray = a;
        menu = KeyBindingHelper.registerKeyBinding((KeyBinding)new KeyBinding(stringArray[3], InputUtil.Type.field_1668, 344, CATEGORY));
        emoteWheel = KeyBindingHelper.registerKeyBinding((KeyBinding)new KeyBinding(stringArray[2], InputUtil.Type.field_1668, 71, CATEGORY));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int codeOf(KeyBinding keyBinding) {
        InputUtil.Key key;
        block13: {
            InputUtil.Key key2;
            block14: {
                int n;
                block12: {
                    KeyBinding keyBinding2;
                    block10: {
                        block11: {
                            n = FreeLook.a;
                            keyBinding2 = keyBinding;
                            if (n != 0) break block10;
                            if (keyBinding2 != null) break block11;
                            return -1;
                        }
                        keyBinding2 = keyBinding;
                    }
                    key2 = KeyBindingHelper.getBoundKeyOf((KeyBinding)keyBinding2);
                    key = key2;
                    if (n != 0) break block12;
                    if (key == null) return -1;
                    key = key2;
                }
                if (n != 0) break block13;
                if (key.method_1444() != -1) break block14;
                return -1;
            }
            key = key2;
        }
        int n = key.method_1444();
        return n;
    }

    public static int menuCode() {
        return AuroraKeys.codeOf(menu);
    }

    public static int emoteCode() {
        return AuroraKeys.codeOf(emoteWheel);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "D\f Pu\u001a\u0006D\f Pu\u001a";
                var4_3 = "D\f Pu\u001a\u0006D\f Pu\u001a".length();
                var1_4 = 6;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "N\u001c+\u0011f\u000etJ\u000b3\u0011b\u0016iQ\u001c\rHo\u001ecI\u000fN\u001c+\u0011f\u000etJ\u000b3\u0011j\u001ehP";
                    var4_3 = "N\u001c+\u0011f\u000etJ\u000b3\u0011b\u0016iQ\u001c\rHo\u001ecI\u000fN\u001c+\u0011f\u000etJ\u000b3\u0011j\u001ehP".length();
                    var1_4 = 22;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 37;
                        break;
                    }
                    case 1: {
                        v10 = 121;
                        break;
                    }
                    case 2: {
                        v10 = 82;
                        break;
                    }
                    case 3: {
                        v10 = 63;
                        break;
                    }
                    case 4: {
                        v10 = 7;
                        break;
                    }
                    case 5: {
                        v10 = 123;
                        break;
                    }
                    default: {
                        v10 = 6;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_7;
lbl65:

                v7 = v7;
            } while (v7 > var6_7);
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
        AuroraKeys.a = var5;
        var0_6 = AuroraKeys.a;
        AuroraKeys.CATEGORY = KeyBinding.Category.method_74698((Identifier)Identifier.method_60655((String)var0_6[0], (String)var0_6[1]));
    }
}
