package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.module.setting.NumberSetting;
import java.util.Locale;
import net.minecraft.client.font.TextRenderer;
import org.lwjgl.glfw.GLFW;

public final class Switches {
    private static final String[] a;

    private Switches() {
    }

    
    public static String keyName(int var0) {
        var1_1 = SearchIndex.a;
        v0 = var0;
        if (!var1_1) {
            if (v0 == -1) {
                return Switches.a[6];
            }
            v0 = var0;
        }
        v1 = var2_2 = GLFW.glfwGetKeyName((int)v0, (int)0);
        if (var1_1) // goto
        if (v1 != null) {
            v1 = var2_2;
lbl11:

            v2 = v1.isEmpty();
            if (!var1_1) {
                if (v2 == 0) {
                    return var2_2.toUpperCase(Locale.ROOT);
                } else {
                }
            }
        } else lbl_neg_1000:

        {
            v2 = var0;
        }
        if (!var1_1) {
            switch (v2) {
                case 32: {
                    v3 = Switches.a[9];
                    return v3;
                }
                case 340: 
                case 344: {
                    var3_3 = Switches.a;
                    v3 = var3_3[0];
                    return v3;
                }
                case 341: 
                case 345: {
                    var3_4 = Switches.a;
                    v3 = var3_4[3];
                    return v3;
                }
                case 342: 
                case 346: {
                    var3_5 = Switches.a;
                    v3 = var3_5[8];
                    return v3;
                }
                case 258: {
                    var3_6 = Switches.a;
                    v3 = var3_6[11];
                    return v3;
                }
                case 0: {
                    var3_7 = Switches.a;
                    v3 = var3_7[1];
                    return v3;
                }
                case 1: {
                    var3_8 = Switches.a;
                    v3 = var3_8[12];
                    return v3;
                }
                case 2: {
                    var3_9 = Switches.a;
                    v3 = var3_9[5];
                    return v3;
                }
                case 3: {
                    var3_10 = Switches.a;
                    v3 = var3_10[10];
                    return v3;
                }
                case 4: {
                    var3_11 = Switches.a;
                    v3 = var3_11[7];
                    return v3;
                }
            }
            v2 = var0;
        }
        v3 = Switches.a[4] + v2;
        return v3;
    }

    public static String fmtNum(NumberSetting numberSetting) {
        String string;
        block6: {
            double d;
            block5: {
                double d2;
                block4: {
                    d = numberSetting.getValue();
                    boolean bl = SearchIndex.a;
                    d2 = numberSetting.getIncrement();
                    if (bl) break block4;
                    if (!(d2 >= 1.0)) break block5;
                    d2 = d;
                }
                string = String.valueOf((int)Math.round(d2));
                break block6;
            }
            string = String.format(Locale.ROOT, a[2], d);
        }
        return string;
    }

    public static String pretty(String string) {
        Object object;
        block6: {
            String string2;
            block4: {
                block5: {
                    boolean bl = SearchIndex.a;
                    string2 = string;
                    if (bl) break block4;
                    if (string2.length() >= 3) break block5;
                    object = string;
                    break block6;
                }
                string2 = string;
            }
            object = string2.charAt(0) + string.substring(1).toLowerCase(Locale.ROOT).replace('_', ' ');
        }
        return object;
    }

    
    public static String trim(TextRenderer textRenderer, String string, int n) {
        boolean bl = SearchIndex.a;
        int n2 = textRenderer.method_1727(string);
        if (bl) {
            return string + "\u2026";
        } else if (n2 <= n) {
            return string;
        }
        do {
            n2 = string.length();
            if (n2 <= 1 || textRenderer.method_1727(string + "\u2026") <= n) return string + "\u2026";
            string = string.substring(0, string.length() - 1);
        } while (!bl);
        return string + "\u2026";
    }

    public static float clamp01(float f) {
        float f2;
        block10: {
            block11: {
                boolean bl;
                block8: {
                    block9: {
                        bl = SearchIndex.a;
                        f2 = f;
                        if (bl) break block8;
                        if (!(f2 < 0.0f)) break block9;
                        f2 = 0.0f;
                        break block10;
                    }
                    f2 = f;
                }
                if (bl) break block10;
                if (!(f2 > 1.0f)) break block11;
                f2 = 1.0f;
                break block10;
            }
            f2 = f;
        }
        return f2;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[13];
                var3_1 = 0;
                var2_2 = "^j@>\u000e\u0003AOk\u0004(,\u001b>\u0004Nv[4\u0004FgPx\u0003@Ok\u0004CmG=\u0002@7\u0003Ln]\u0005^rH;\u001f\u0002@6";
                var4_3 = "^j@>\u000e\u0003AOk\u0004(,\u001b>\u0004Nv[4\u0004FgPx\u0003@Ok\u0004CmG=\u0002@7\u0003Ln]\u0005^rH;\u001f\u0002@6".length();
                var1_4 = 5;
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
                    var2_2 = "YcK\u0003_Ok";
                    var4_3 = "YcK\u0003_Ok".length();
                    var1_4 = 3;
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
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 13;
                        break;
                    }
                    case 1: {
                        v10 = 2;
                        break;
                    }
                    case 2: {
                        v10 = 41;
                        break;
                    }
                    case 3: {
                        v10 = 88;
                        break;
                    }
                    case 4: {
                        v10 = 122;
                        break;
                    }
                    case 5: {
                        v10 = 64;
                        break;
                    }
                    default: {
                        v10 = 41;
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
        Switches.a = var5;
    }
}
