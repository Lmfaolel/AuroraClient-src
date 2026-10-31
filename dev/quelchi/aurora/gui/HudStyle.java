package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.HudStyle$Look;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.gui.Theme;
import net.minecraft.client.gui.DrawContext;

public final class HudStyle {
    public static final int BODY;
    public static final int RADIUS;
    private static final int SOLID;
    private static final long[] a;
    private static final Integer[] b;

    private HudStyle() {
    }

    
    public static void panel(DrawContext var0, int var1_1, int var2_2, int var3_3, int var4_4, int var5_5, HudStyle$Look var6_6) {
        var7_7 = PreviewScene.a;
        v0 = var3_3;
        if (var7_7 == 0) {
            if (v0 <= 0) return;
            v0 = var4_4;
        }
        if (v0 <= 0) return;
        v1 = var6_6;
        if (var7_7 == 0) {
            if (v1 == null) {
                return;
            }
            v1 = var6_6;
        }
        switch (v1.ordinal()) {
            case 0: {
                Round.rect(var0, var1_1, var2_2, var3_3, var4_4, 5, HudStyle.a(3433, 1264346743344799517L));
                if (var7_7 == 0) return;
            }
            case 1: {
                Round.rect(var0, var1_1, var2_2, var3_3, var4_4, 2, HudStyle.a(2543, 4095460468383259544L));
                Round.outline(var0, var1_1, var2_2, var3_3, var4_4, 2, Theme.alpha(var5_5, 90));
                if (var7_7 == 0) return;
            }
            case 2: {
                Round.rect(var0, var1_1, var2_2, var3_3, var4_4, 5, Theme.alpha(0, 60));
                Round.outline(var0, var1_1, var2_2, var3_3, var4_4, 5, Theme.alpha(var5_5, 150));
                if (var7_7 == 0) return;
            }
            case 3: {
                var8_8 = Math.max(3, Math.min(8, Math.min(var3_3, var4_4) / 4));
                var9_9 = Theme.alpha(var5_5, 190);
                var10_10 = 0;
                block21: do {
                    if (var10_10 > true) return;
                    if (var7_7 != 0) return;
                    for (var11_11 = 0; var11_11 <= 1; ++var11_11) {
                        if (var7_7 != 0) continue block21;
                        v2 = var10_10;
                        if (var7_7 != 0) // goto
                        if (v2 == 0) {
                            v3 = var1_1;
                        } else {
                            v2 = var1_1 + var3_3;
lbl39:

                            v3 = v2 - var8_8;
                        }
                        var12_12 = v3;
                        v4 = var11_11;
                        if (var7_7 != 0) // goto
                        if (v4 == 0) {
                            v5 = var2_2;
                        } else {
                            v4 = var2_2 + var4_4;
lbl47:

                            v5 = v4 - 1;
                        }
                        var13_13 = v5;
                        var0.method_25294(var12_12, var13_13, var12_12 + var8_8, var13_13 + 1, var9_9);
                        v6 = var10_10;
                        if (var7_7 != 0) // goto
                        if (v6 == 0) {
                            v7 = var1_1;
                        } else {
                            v6 = var1_1 + var3_3;
lbl56:

                            v7 = v6 - 1;
                        }
                        var14_14 = v7;
                        v8 = var11_11;
                        if (var7_7 != 0) // goto
                        if (v8 == 0) {
                            v9 = var2_2;
                        } else {
                            v8 = var2_2 + var4_4;
lbl64:

                            v9 = v8 - var8_8;
                        }
                        var15_15 = v9;
                        var0.method_25294(var14_14, var15_15, var14_14 + 1, var15_15 + var8_8, var9_9);
                        if (var7_7 == 0) continue;
                    }
                    ++var10_10;
                } while (var7_7 == 0);
                return;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean wantsShadow(HudStyle$Look hudStyle$Look) {
        HudStyle$Look hudStyle$Look2;
        block11: {
            int n;
            block10: {
                n = PreviewScene.a;
                hudStyle$Look2 = hudStyle$Look;
                if (n != 0) break block10;
                if (hudStyle$Look2 == HudStyle$Look.None) return true;
                hudStyle$Look2 = hudStyle$Look;
            }
            if (n != 0) break block11;
            if (hudStyle$Look2 == HudStyle$Look.Brackets) return true;
            hudStyle$Look2 = hudStyle$Look;
        }
        if (hudStyle$Look2 != HudStyle$Look.Outline) return false;
        return true;
    }

    
    static {
        block8: {
            block7: {
                var0 = 6238495782288812112L;
                var6_1 = new long[5];
                var3_2 = 0;
                var4_3 = "\u0085dcqM\u00b3y\u00d8\u00b8na\u0017\u0092\u00ec\tj\u0006r\u00a1\u009f\n\u00b6\u00e1\u0087";
                var5_4 = "\u0085dcqM\u00b3y\u00d8\u00b8na\u0017\u0092\u00ec\tj\u0006r\u00a1\u009f\n\u00b6\u00e1\u0087".length();
                var2_5 = 0;
                while (true) {
                    var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                    v0 = var6_1;
                    v1 = var3_2++;
                    v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                    v3 = -1;
                    break block7;
                    break;
                }
lbl14:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    var4_3 = "\u009dN\u00cb\u0007\u0005\u00e9\u00fbA\u00ae\u00dff\u00a6\u0007\u00b1a\u009a";
                    var5_4 = "\u009dN\u00cb\u0007\u0005\u00e9\u00fbA\u00ae\u00dff\u00a6\u0007\u00b1a\u009a".length();
                    var2_5 = 0;
                    while (true) {
                        var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                        v0 = var6_1;
                        v1 = var3_2++;
                        v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                        v3 = 0;
                        break block7;
                        break;
                    }
                    break;
                }
lbl27:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    break block8;
                    break;
                }
            }
            v4 = v2 ^ var0;
            switch (v3) {
                default: {
                    continue;
                }
                case 0:
lbl38:

                continue;
            }
        }
        HudStyle.a = var6_1;
        HudStyle.b = new Integer[5];
        HudStyle.BODY = HudStyle.a(32576, 9071331107709447478L);
        HudStyle.RADIUS = HudStyle.a(7079, 1141808652113283538L);
        HudStyle.SOLID = HudStyle.a(4521, 2435832954541409242L);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1277;
        if (b[n2] == null) {
            HudStyle.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
