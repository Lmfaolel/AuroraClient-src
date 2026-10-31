package dev.quelchi.aurora.util;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public final class KeyUtil {
    private static final Map<Integer, String> specialKeys;
    public static int a;

    private KeyUtil() {
    }

    public static boolean isKeyPressed(int n) {
        boolean bl;
        block22: {
            block23: {
                int n2;
                block18: {
                    long l;
                    block19: {
                        boolean bl2;
                        block20: {
                            block21: {
                                block17: {
                                    int n3;
                                    block16: {
                                        n2 = a;
                                        n3 = n;
                                        if (n2 != 0) break block16;
                                        if (n3 != -1) break block17;
                                        n3 = 0;
                                    }
                                    return n3 != 0;
                                }
                                l = MinecraftClient.method_1551().method_22683().method_4490();
                                bl = n;
                                if (n2 != 0) break block18;
                                if (bl > 8 != 0) break block19;
                                bl2 = GLFW.glfwGetMouseButton((long)l, (int)n);
                                if (n2 != 0) break block20;
                                if (!bl2) break block21;
                                bl2 = true;
                                break block20;
                            }
                            bl2 = false;
                        }
                        return bl2;
                    }
                    bl = GLFW.glfwGetKey((long)l, (int)n);
                }
                if (n2 != 0) break block22;
                if (!bl) break block23;
                bl = true;
                break block22;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean isKeyPressedInGame(int n) {
        int n2;
        block7: {
            block6: {
                int n3 = a;
                if (MinecraftClient.method_1551().field_1755 != null) break block6;
                n2 = n;
                if (n3 != 0) break block7;
                if (!KeyUtil.isKeyPressed(n2)) break block6;
                n2 = 1;
                break block7;
            }
            n2 = 0;
        }
        return n2 != 0;
    }

    
    static {
        var0 = new String[28];
        var4_1 = 0;
        var3_2 = "\u0019\u001d\u0001=o]\u0002\u001e@\u0007\u0019\u001d\u0011<mT\u0014\u0006\u0002^1,oE\u0005\u000e^6,o\u0004\u0003_/,\u0005\u0019\u001d\u0003%i\u0003\u001fQ \u0007\u0007\u001d\u0011!tW\u0012\u0005\u0019Y%!i\u0007\u0007\u001d\u0011<mT\u0014\b\u0005E/\fsE\u00039\u0004\u001bW\u0006'\u0004\u001bW\u00179\u0003\u000eC!\u0003\u0019}\u0000\u0007\u0019\u001d\u0011!tW\u0012\u0005\u0007\u001d\u0003%i\u0006\u000fU.,iT\u0004\bQ2:\u0003\u0006}\u0000\u0003\u0007}\u0000\u0006\u0007\u001d\u0001=o]\u0004\u0007U$=\u0003\u000e^&\t\tQ!\"nA\u0007(U";
        var5_3 = "\u0019\u001d\u0001=o]\u0002\u001e@\u0007\u0019\u001d\u0011<mT\u0014\u0006\u0002^1,oE\u0005\u000e^6,o\u0004\u0003_/,\u0005\u0019\u001d\u0003%i\u0003\u001fQ \u0007\u0007\u001d\u0011!tW\u0012\u0005\u0019Y%!i\u0007\u0007\u001d\u0011<mT\u0014\b\u0005E/\fsE\u00039\u0004\u001bW\u0006'\u0004\u001bW\u00179\u0003\u000eC!\u0003\u0019}\u0000\u0007\u0019\u001d\u0011!tW\u0012\u0005\u0007\u001d\u0003%i\u0006\u000fU.,iT\u0004\bQ2:\u0003\u0006}\u0000\u0003\u0007}\u0000\u0006\u0007\u001d\u0001=o]\u0004\u0007U$=\u0003\u000e^&\t\tQ!\"nA\u0007(U".length();
        var2_4 = 6;
        var1_5 = -1;
        block19: while (true) {
            v0 = ++var1_5;
            v1 = var3_2.substring(v0, v0 + var2_4);
            v2 = -1;
            block20: while (true) {
                var3_2 = "\u0018@#*x\u0004\u000f_5'";
                var5_3 = "\u0018@#*x\u0004\u000f_5'".length();
                var2_4 = 5;
                var1_5 = -1;
                while (true) {
                    v3 = ++var1_5;
                    v1 = var3_2.substring(v3, v3 + var2_4);
                    v2 = 0;
lbl21:

                    v4 = v1.toCharArray();
                    v5 = v4;
                    v6 = v4.length;
                    var6_6 = 0;
                    do {
                        v5 = v5;
                        v7 = var6_6;
                        v8 = v5[v7];
                        switch (var6_6 % 7) {
                            case 0: {
                                v9 = 75;
                                break;
                            }
                            case 1: {
                                v9 = 48;
                                break;
                            }
                            case 2: {
                                v9 = 66;
                                break;
                            }
                            case 3: {
                                v9 = 73;
                                break;
                            }
                            case 4: {
                                v9 = 29;
                                break;
                            }
                            case 5: {
                                v9 = 49;
                                break;
                            }
                            default: {
                                v9 = 102;
                            }
                        }
                        v5[v7] = (char)(v8 ^ v9);
                        ++var6_6;
lbl53:

                        v6 = v6;
                    } while (v6 > var6_6);
                    v10 = new String(v5);
                    switch (v2) {
                        default: {
                            var0[var4_1++] = v10.intern();
                            if ((var1_5 += var2_4) >= var5_3) continue block20;
                            var2_4 = var3_2.charAt(var1_5);
                            continue block19;
                        }
                        case 0: 
                    }
                    var0[var4_1++] = v10.intern();
                    if ((var1_5 += var2_4) >= var5_3) break block19;
                    var2_4 = var3_2.charAt(var1_5);
                }
                break;
            }
            break;
        }
        KeyUtil.specialKeys = new HashMap<Integer, String>();
        KeyUtil.specialKeys.put(340, var0[8]);
        KeyUtil.specialKeys.put(344, var0[16]);
        KeyUtil.specialKeys.put(341, var0[22]);
        KeyUtil.specialKeys.put(345, var0[0]);
        KeyUtil.specialKeys.put(342, var0[17]);
        KeyUtil.specialKeys.put(346, var0[6]);
        KeyUtil.specialKeys.put(343, var0[10]);
        KeyUtil.specialKeys.put(347, var0[2]);
        KeyUtil.specialKeys.put(256, var0[14]);
        KeyUtil.specialKeys.put(258, var0[7]);
        KeyUtil.specialKeys.put(257, var0[4]);
        KeyUtil.specialKeys.put(335, var0[11]);
        KeyUtil.specialKeys.put(259, var0[25]);
        KeyUtil.specialKeys.put(260, var0[3]);
        KeyUtil.specialKeys.put(261, var0[18]);
        KeyUtil.specialKeys.put(268, var0[5]);
        KeyUtil.specialKeys.put(269, var0[24]);
        KeyUtil.specialKeys.put(266, var0[13]);
        KeyUtil.specialKeys.put(267, var0[12]);
        KeyUtil.specialKeys.put(280, var0[19]);
        KeyUtil.specialKeys.put(32, var0[26]);
        KeyUtil.specialKeys.put(265, var0[1]);
        KeyUtil.specialKeys.put(264, var0[27]);
        KeyUtil.specialKeys.put(263, var0[23]);
        KeyUtil.specialKeys.put(262, var0[9]);
        for (var7_7 = 1; var7_7 <= 25; ++var7_7) {
            KeyUtil.specialKeys.put(290 + (var7_7 - 1), "F" + var7_7);
        }
        var7_7 = 0;
        while (true) {
            if (var7_7 > 7) {
                return;
            }
            switch (var7_7) {
                case 0: {
                    v11 = var0[21];
                    break;
                }
                case 1: {
                    v11 = var0[15];
                    break;
                }
                case 2: {
                    v11 = var0[20];
                    break;
                }
                default: {
                    v11 = "M" + (var7_7 + 1);
                }
            }
            var8_8 = v11;
            KeyUtil.specialKeys.put(var7_7, (String)var8_8);
            ++var7_7;
        }
    }
}
