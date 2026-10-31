package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.util.Updater;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.MutableText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public final class UpdatePrompt {
    private static final int EDGE;
    private static final int H;
    private static final int BTN_H;
    private static int btnX;
    private static int btnY;
    private static int btnW;
    private static int closeX;
    private static boolean later;
    private static long drawnAt;
    private static boolean fromScreen;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long d;

    private UpdatePrompt() {
    }

    public static boolean visible() {
        boolean bl;
        block6: {
            block5: {
                int n;
                block4: {
                    n = PreviewScene.a;
                    bl = Updater.ready.isEmpty();
                    if (n != 0) break block4;
                    if (bl) break block5;
                    bl = later;
                }
                if (n != 0) break block6;
                if (bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    public static void dismiss() {
        later = true;
    }

    public static void renderOverScreen(DrawContext drawContext, int n) {
        fromScreen = true;
        try {
            UpdatePrompt.render(drawContext, n);
        }
        finally {
            fromScreen = false;
        }
    }

    public static void render(DrawContext drawContext, int n) {
        MinecraftClient minecraftClient;
        int n2;
        block10: {
            MinecraftClient minecraftClient2;
            block11: {
                n2 = PreviewScene.a;
                if (!UpdatePrompt.visible()) {
                    return;
                }
                minecraftClient2 = MinecraftClient.method_1551();
                minecraftClient = minecraftClient2;
                if (n2 != 0) break block10;
                if (minecraftClient.field_1755 == null) break block11;
                if (fromScreen) break block11;
                return;
            }
            minecraftClient = minecraftClient2;
        }
        TextRenderer textRenderer = minecraftClient.field_1772;
        drawnAt = System.currentTimeMillis();
        String[] stringArray = a;
        MutableText mutableText = Text.method_43470((String)stringArray[0]).method_10862(Style.field_24360.method_10982(Boolean.valueOf(true)));
        String string = stringArray[1];
        btnW = textRenderer.method_1727(stringArray[3]) + 18;
        int n3 = Math.max(textRenderer.method_27525((StringVisitable)mutableText), textRenderer.method_1727(string));
        int n4 = 9 + n3 + 10 + btnW + 4 + 20 + 6;
        Round.rect(drawContext, 15, 15, n4, 40, 8, UpdatePrompt.a(9054, 682285337849288563L));
        Round.outline(drawContext, 15, 15, n4, 40, 8, UpdatePrompt.a(6019, 228989074116764576L));
        drawContext.method_51439(textRenderer, (Text)mutableText, 24, 25, UpdatePrompt.a(15122, 8150431550515236662L), false);
        drawContext.method_51433(textRenderer, string, 24, 37, UpdatePrompt.a(28535, 2984137944803154775L), false);
        closeX = 15 + n4 - 6 - 20;
        btnX = closeX - 4 - btnW;
        btnY = UpdatePrompt.a(22128, 3625915470414553694L);
        Round.rect(drawContext, btnX, btnY, btnW, 20, 6, UpdatePrompt.a(25238, 6855286916292351665L));
        drawContext.method_51433(textRenderer, stringArray[4], btnX + 9, btnY + 6, -1, false);
        Round.rect(drawContext, closeX, btnY, 20, 20, 6, UpdatePrompt.a(14377, 7601964444666678278L));
        int n5 = closeX + 10;
        int n6 = btnY + 10;
        for (int i = -3; i <= 3; ++i) {
            drawContext.method_25294(n5 + i, n6 + i, n5 + i + 1, n6 + i + 1, UpdatePrompt.a(8234, 6391581928317703179L));
            drawContext.method_25294(n5 + i, n6 - i, n5 + i + 1, n6 - i + 1, UpdatePrompt.a(8234, 6391581928317703179L));
            if (n2 == 0) continue;
        }
    }

    public static boolean click(double d, double d2) {
        double d3;
        block49: {
            block50: {
                double d4;
                block47: {
                    block48: {
                        double d5;
                        double d6;
                        int n;
                        block44: {
                            int n2;
                            block46: {
                                block45: {
                                    long l;
                                    block42: {
                                        block43: {
                                            int n3;
                                            block40: {
                                                block41: {
                                                    n = PreviewScene.a;
                                                    n3 = UpdatePrompt.visible();
                                                    if (n != 0) break block40;
                                                    if (n3 == 0) break block41;
                                                    n3 = btnW;
                                                    if (n != 0) break block40;
                                                    if (n3 == 0) break block41;
                                                    long l2 = System.currentTimeMillis() - drawnAt - UpdatePrompt.d;
                                                    l = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                                                    if (n != 0) break block42;
                                                    if (l <= 0) break block43;
                                                }
                                                n3 = 0;
                                            }
                                            return n3 != 0;
                                        }
                                        d6 = d;
                                        d5 = closeX;
                                        if (n != 0) break block44;
                                        double d7 = d6 - d5;
                                        l = d7 == 0.0 ? 0 : (d7 > 0.0 ? 1 : -1);
                                    }
                                    if (l < 0) break block45;
                                    d6 = d;
                                    n2 = closeX + 20;
                                    if (n != 0) break block46;
                                    if (!(d6 <= (double)n2)) break block45;
                                    d6 = d2;
                                    n2 = btnY;
                                    if (n != 0) break block46;
                                    if (!(d6 >= (double)n2)) break block45;
                                    d6 = d2;
                                    n2 = btnY + 20;
                                    if (n != 0) break block46;
                                    if (!(d6 <= (double)n2)) break block45;
                                    later = true;
                                    SocialToast.system(a[5], a[2]);
                                    return true;
                                }
                                d6 = d;
                                n2 = btnX;
                            }
                            d5 = n2;
                        }
                        double d8 = d6 - d5;
                        d4 = d8 == 0.0 ? 0 : (d8 < 0.0 ? -1 : 1);
                        if (n != 0) break block47;
                        if (d4 < 0) break block48;
                        double d9 = d - (double)(btnX + btnW);
                        d4 = d9 == 0.0 ? 0 : (d9 > 0.0 ? 1 : -1);
                        if (n != 0) break block47;
                        if (d4 > 0) break block48;
                        double d10 = d2 - (double)btnY;
                        d4 = d10 == 0.0 ? 0 : (d10 < 0.0 ? -1 : 1);
                        if (n != 0) break block47;
                        if (d4 < 0) break block48;
                        double d11 = d2 - (double)(btnY + 20);
                        d3 = d11 == 0.0 ? 0 : (d11 > 0.0 ? 1 : -1);
                        if (n != 0) break block49;
                        if (d3 <= 0) break block50;
                    }
                    d4 = 0.0;
                }
                return (boolean)d4;
            }
            MinecraftClient.method_1551().method_1592();
            d3 = 1.0;
        }
        return (boolean)d3;
    }

    
    static {
        block30: {
            block29: {
                block28: {
                    block27: {
                        block26: {
                            var15 = new String[6];
                            var13_1 = 0;
                            var12_2 = "[Aor\u0001k8|Tjw\f\u0010\\Tjw\f.la\u0011b}\u0006zyb](GE+z\u001b}lo]g`U`}vE+g\u001cc}.HdfU}loC\u007f3\u0001f}.Vj~\u0010\u0006[Aor\u0001k";
                            var14_3 = "[Aor\u0001k8|Tjw\f\u0010\\Tjw\f.la\u0011b}\u0006zyb](GE+z\u001b}lo]g`U`}vE+g\u001cc}.HdfU}loC\u007f3\u0001f}.Vj~\u0010\u0006[Aor\u0001k".length();
                            var11_4 = 12;
                            var10_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var10_5;
                                v1 = var12_2.substring(v0, v0 + var11_4);
                                v2 = -1;
                                break block26;
                                break;
                            }
lbl12:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                var12_2 = "[Aor\u0001k\f[Aor\u0001k8bP\u007fv\u0007";
                                var14_3 = "[Aor\u0001k\f[Aor\u0001k8bP\u007fv\u0007".length();
                                var11_4 = 6;
                                var10_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var10_5;
                                    v1 = var12_2.substring(v4, v4 + var11_4);
                                    v2 = 0;
                                    break block26;
                                    break;
                                }
                                break;
                            }
lbl26:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                break block27;
                                break;
                            }
                        }
                        v5 = v1.toCharArray();
                        v6 = v5;
                        v7 = v5.length;
                        var16_6 = 0;
                        do {
                            v6 = v6;
                            v8 = var16_6;
                            v9 = v6[v8];
                            switch (var16_6 % 7) {
                                case 0: {
                                    v10 = 14;
                                    break;
                                }
                                case 1: {
                                    v10 = 49;
                                    break;
                                }
                                case 2: {
                                    v10 = 11;
                                    break;
                                }
                                case 3: {
                                    v10 = 19;
                                    break;
                                }
                                case 4: {
                                    v10 = 117;
                                    break;
                                }
                                case 5: {
                                    v10 = 14;
                                    break;
                                }
                                default: {
                                    v10 = 24;
                                }
                            }
                            v6[v8] = (char)(v9 ^ v10);
                            ++var16_6;
lbl65:

                            v7 = v7;
                        } while (v7 > var16_6);
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
                    UpdatePrompt.a = var15;
                    var2_7 = 2689190139291351480L;
                    var8_8 = new long[11];
                    var5_9 = 0;
                    var6_10 = "\u00b7\u00b3\u0012w\u00d0U\u00ed\u00d6\u000f\u00aa\u00b2\u00cb\u001c\u0093\u0017\u0082\u0014*\u000e\u00cf\u00ea\u00f8\u00c0\u00edvm,\u00eb\u0000\u0098\u0085y\u001fL\u0011\u001c\u00a4\"\u00a5\u00e7\u00a2\u00ca\u001a-M\u0014\u00e4\u0089\u00f9)\u00d9\u0082\u00a0J\u00f1\u0019O\u00c8YD\u0086\u00a5\u00beEI\u00a1N\u008b\u00b2\u008f\u008aA";
                    var7_11 = "\u00b7\u00b3\u0012w\u00d0U\u00ed\u00d6\u000f\u00aa\u00b2\u00cb\u001c\u0093\u0017\u0082\u0014*\u000e\u00cf\u00ea\u00f8\u00c0\u00edvm,\u00eb\u0000\u0098\u0085y\u001fL\u0011\u001c\u00a4\"\u00a5\u00e7\u00a2\u00ca\u001a-M\u0014\u00e4\u0089\u00f9)\u00d9\u0082\u00a0J\u00f1\u0019O\u00c8YD\u0086\u00a5\u00beEI\u00a1N\u008b\u00b2\u008f\u008aA".length();
                    var4_12 = 0;
                    while (true) {
                        var9_13 = var6_10.substring(var4_12, var4_12 += 8).getBytes("ISO-8859-1");
                        v11 = var8_8;
                        v12 = var5_9++;
                        v13 = ((long)var9_13[0] & 255L) << 56 | ((long)var9_13[1] & 255L) << 48 | ((long)var9_13[2] & 255L) << 40 | ((long)var9_13[3] & 255L) << 32 | ((long)var9_13[4] & 255L) << 24 | ((long)var9_13[5] & 255L) << 16 | ((long)var9_13[6] & 255L) << 8 | (long)var9_13[7] & 255L;
                        v14 = -1;
                        break block28;
                        break;
                    }
lbl89:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_12 < var7_11) continue;
                        var6_10 = "H\u001di>\u00c6V\u009b\u00ff\u0085\u00fe\u0018\u0089\u00ab\u0017|\u00d1";
                        var7_11 = "H\u001di>\u00c6V\u009b\u00ff\u0085\u00fe\u0018\u0089\u00ab\u0017|\u00d1".length();
                        var4_12 = 0;
                        while (true) {
                            var9_13 = var6_10.substring(var4_12, var4_12 += 8).getBytes("ISO-8859-1");
                            v11 = var8_8;
                            v12 = var5_9++;
                            v13 = ((long)var9_13[0] & 255L) << 56 | ((long)var9_13[1] & 255L) << 48 | ((long)var9_13[2] & 255L) << 40 | ((long)var9_13[3] & 255L) << 32 | ((long)var9_13[4] & 255L) << 24 | ((long)var9_13[5] & 255L) << 16 | ((long)var9_13[6] & 255L) << 8 | (long)var9_13[7] & 255L;
                            v14 = 0;
                            break block28;
                            break;
                        }
                        break;
                    }
lbl102:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_12 < var7_11) continue;
                        break block29;
                        break;
                    }
                }
                v15 = v13 ^ var2_7;
                switch (v14) {
                    default: {
                        continue;
                    }
                    case 0:
lbl113:

                    continue;
                }
            }
            UpdatePrompt.b = var8_8;
            UpdatePrompt.c = new Integer[11];
            UpdatePrompt.BTN_H = UpdatePrompt.a(10503, 1357691093871058213L);
            UpdatePrompt.H = UpdatePrompt.a(3416, 8512246338300770685L);
            UpdatePrompt.EDGE = UpdatePrompt.a(23059, 2576819497073815093L);
            break block30;
lbl121:

            while (true) {
                continue;
                break;
            }
        }
        var0_14 = 2421932160360922173L;
        while (true)
        UpdatePrompt.d = 2421932160360922311L ^ var0_14;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7427;
        if (c[n2] == null) {
            UpdatePrompt.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
