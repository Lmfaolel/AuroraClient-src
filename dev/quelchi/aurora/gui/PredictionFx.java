package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.Anim;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.gui.Theme;
import dev.quelchi.aurora.gui.UiSound;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public final class PredictionFx {
    private static final long LIFE_MS;
    private static final long FADE_MS;
    private static final String WORD;
    private static final int H;
    private static final int DOT;
    private static long startedAt;
    private static boolean skip;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private PredictionFx() {
    }

    public static void skipNext() {
        skip = true;
    }

    public static void play() {
        block4: {
            block5: {
                int n = PreviewScene.a;
                UiSound.play(UiSound.OPEN, 0.5f);
                if (n != 0) break block4;
                if (!skip) break block5;
                skip = false;
                return;
            }
            startedAt = Anim.millis();
        }
    }

    public static void render(DrawContext drawContext) {
        block20: {
            DrawContext drawContext2;
            int n;
            int n2;
            int n3;
            TextRenderer textRenderer;
            block19: {
                long l;
                int n4;
                block18: {
                    block17: {
                        long l2;
                        block15: {
                            block16: {
                                n4 = PreviewScene.a;
                                l2 = startedAt;
                                if (n4 != 0) break block15;
                                if (l2 >= 0L) break block16;
                                return;
                            }
                            l2 = Anim.millis() - startedAt;
                        }
                        l = l2;
                        if (n4 != 0) break block17;
                        if (l <= PredictionFx.b(5060, 4844630872286805105L)) break block18;
                        startedAt = PredictionFx.b(29661, 7241729972686937199L);
                    }
                    return;
                }
                float f = Math.min(1.0f, (float)l / 200.0f);
                float f2 = Math.min(f, Math.min(1.0f, (float)(PredictionFx.b(20199, 1624517030960750932L) - l) / 200.0f));
                if (f2 <= 0.01f) {
                    return;
                }
                textRenderer = MinecraftClient.method_1551().field_1772;
                n3 = Math.round(255.0f * f2);
                String[] stringArray = a;
                int n5 = 25 + textRenderer.method_1727(stringArray[0]) + 9;
                n2 = (drawContext.method_51421() - n5) / 2;
                n = drawContext.method_51443() - 80 + Math.round((1.0f - Anim.outCubic(f)) * 5.0f);
                Round.rect(drawContext, n2, n, n5, 24, 12, Theme.alpha(PredictionFx.a(13743, 4799519115571312286L), n3));
                drawContext2 = drawContext;
                if (n4 != 0) break block19;
                Round.outline(drawContext2, n2, n, n5, 24, 12, Theme.alpha(PredictionFx.a(9154, 5943342501305819382L), Math.round(41.0f * f2)));
                Round.circle(drawContext, n2 + 10 + 3, n + 12, 6, Theme.alpha(PredictionFx.a(4042, 1755263348928711930L), n3));
                if (!(f2 > 0.05f)) break block20;
                drawContext2 = drawContext;
            }
            drawContext2.method_51433(textRenderer, a[1], n2 + 10 + 6 + 9, n + 8, Theme.alpha(PredictionFx.a(19211, 6183515302005753913L), n3), false);
        }
    }

    
    static {
        block30: {
            block29: {
                block28: {
                    block27: {
                        block26: {
                            var21 = new String[2];
                            var19_1 = 0;
                            var18_2 = "d\u000eA\u0002;:x]\u0013JF=7\rd\u000eA\u0002;:x]\u0013JF=7";
                            var20_3 = "d\u000eA\u0002;:x]\u0013JF=7\rd\u000eA\u0002;:x]\u0013JF=7".length();
                            var17_4 = 13;
                            var16_5 = -1;
lbl7:

                            while (true) {
                                continue;
                                break;
                            }
lbl9:

                            while (true) {
                                var21[var19_1++] = new String(v0).intern();
                                if ((var16_5 += var17_4) < var20_3) {
                                    var17_4 = var18_2.charAt(var16_5);
                                    continue;
                                }
                                break block26;
                                break;
                            }
                            v1 = ++var16_5;
                            v2 = var18_2.substring(v1, v1 + var17_4).toCharArray();
                            v0 = v2;
                            v3 = v2.length;
                            var22_6 = 0;
                            do {
                                v0 = v0;
                                v4 = var22_6;
                                v5 = v0[v4];
                                switch (var22_6 % 7) {
                                    case 0: {
                                        v6 = 52;
                                        break;
                                    }
                                    case 1: {
                                        v6 = 124;
                                        break;
                                    }
                                    case 2: {
                                        v6 = 36;
                                        break;
                                    }
                                    case 3: {
                                        v6 = 102;
                                        break;
                                    }
                                    case 4: {
                                        v6 = 82;
                                        break;
                                    }
                                    case 5: {
                                        v6 = 89;
                                        break;
                                    }
                                    default: {
                                        v6 = 12;
                                    }
                                }
                                v0[v4] = (char)(v5 ^ v6);
                                ++var22_6;
lbl48:

                                v3 = v3;
                            } while (v3 > var22_6);
                            while (true)
                        }
                        PredictionFx.a = var21;
                        PredictionFx.WORD = PredictionFx.a[1];
                        var8_7 = 3946898543023177089L;
                        var14_8 = new long[6];
                        var11_9 = 0;
                        var12_10 = "v\u0004B2e\u001bg\u00db\u00d1\u00d7,\u00df\u00dd$\u0019\u0005\n\u00edM-\u0005\u00a8\u0006O\u0019\u00d4\u00f8\u00f9\u008aO%\u00ae";
                        var13_11 = "v\u0004B2e\u001bg\u00db\u00d1\u00d7,\u00df\u00dd$\u0019\u0005\n\u00edM-\u0005\u00a8\u0006O\u0019\u00d4\u00f8\u00f9\u008aO%\u00ae".length();
                        var10_12 = 0;
                        while (true) {
                            var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                            v7 = var14_8;
                            v8 = var11_9++;
                            v9 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                            v10 = -1;
                            break block27;
                            break;
                        }
lbl70:

                        while (true) {
                            v7[v8] = v11;
                            if (var10_12 < var13_11) continue;
                            var12_10 = "\f\u00a9\u00cfn=Hb\u0088?p\u0080\u001e\u009a\u00abK'";
                            var13_11 = "\f\u00a9\u00cfn=Hb\u0088?p\u0080\u001e\u009a\u00abK'".length();
                            var10_12 = 0;
                            while (true) {
                                var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                                v7 = var14_8;
                                v8 = var11_9++;
                                v9 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                                v10 = 0;
                                break block27;
                                break;
                            }
                            break;
                        }
lbl83:

                        while (true) {
                            v7[v8] = v11;
                            if (var10_12 < var13_11) continue;
                            break block28;
                            break;
                        }
                    }
                    v11 = v9 ^ var8_7;
                    switch (v10) {
                        default: {
                            continue;
                        }
                        case 0:
lbl94:

                        continue;
                    }
                }
                PredictionFx.b = var14_8;
                PredictionFx.c = new Integer[6];
                PredictionFx.H = PredictionFx.a(30091, 3374739168160413374L);
                PredictionFx.DOT = PredictionFx.a(6938, 8609624752516503593L);
                var0_14 = 8126921402652127074L;
                var6_15 = new long[6];
                var3_16 = 0;
                var4_17 = "\u00f9lJ\u00f0\u00c8\u00d6\u00b3t|AG\u00bf\u00dbS\u00d5i>\u00a4+O4\u00bf\u0018'3\u00f3\t\u00e9X\u0012\u00d9;";
                var5_18 = "\u00f9lJ\u00f0\u00c8\u00d6\u00b3t|AG\u00bf\u00dbS\u00d5i>\u00a4+O4\u00bf\u0018'3\u00f3\t\u00e9X\u0012\u00d9;".length();
                var2_19 = 0;
                while (true) {
                    var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                    v12 = var6_15;
                    v13 = var3_16++;
                    v14 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                    v15 = -1;
                    break block29;
                    break;
                }
lbl113:

                while (true) {
                    v12[v13] = v16;
                    if (var2_19 < var5_18) continue;
                    var4_17 = "\u00ebH\u00ab\u0000P+L\u00f2fC\u00eeT\u00ebe\u0084\u001e";
                    var5_18 = "\u00ebH\u00ab\u0000P+L\u00f2fC\u00eeT\u00ebe\u0084\u001e".length();
                    var2_19 = 0;
                    while (true) {
                        var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                        v12 = var6_15;
                        v13 = var3_16++;
                        v14 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                        v15 = 0;
                        break block29;
                        break;
                    }
                    break;
                }
lbl126:

                while (true) {
                    v12[v13] = v16;
                    if (var2_19 < var5_18) continue;
                    break block30;
                    break;
                }
            }
            v16 = v14 ^ var0_14;
            switch (v15) {
                default: {
                    continue;
                }
                case 0:
lbl137:

                continue;
            }
        }
        PredictionFx.d = var6_15;
        PredictionFx.e = new Long[6];
        PredictionFx.FADE_MS = PredictionFx.b(5492, 903491015854963395L);
        PredictionFx.LIFE_MS = PredictionFx.b(21209, 5651090344174321005L);
        PredictionFx.startedAt = PredictionFx.b(3167, 8528457573352874985L);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x330;
        if (c[n2] == null) {
            PredictionFx.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x2FB6) & Short.MAX_VALUE;
        if (e[n2] == null) {
            PredictionFx.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
