package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.cosmetics.content.Trails$Trail;
import dev.quelchi.aurora.gui.shell.ItemArt$1;
import dev.quelchi.aurora.gui.shell.ItemArt$Fill;
import dev.quelchi.aurora.gui.shell.SearchIndex;

final class ItemArt {
    private static final long[] a;
    private static final Integer[] b;
    private static final long[] c;
    private static final Long[] d;

    private ItemArt() {
    }

    
    static void trail(ItemArt$Fill var0, int var1_1, int var2_2, int var3_3, Trails$Trail var4_4, int var5_5) {
        var7_6 = 30;
        var8_7 = var1_1 + 10;
        var9_8 = var3_3 - 20;
        var10_9 = var2_2 + 36;
        var11_10 = var5_5 >>> 24 & 255;
        var12_11 = var5_5 & ItemArt.a(14229, 7515829017811739792L);
        var6_12 = SearchIndex.a;
        var13_13 = 0;
        while (var13_13 < var7_6) {
            block48: {
                block47: {
                    block46: {
                        var14_14 = (float)var13_13 / (float)(var7_6 - 1);
                        var15_15 = var8_7 + Math.round((float)var9_8 * var14_14);
                        var16_16 = Math.round((float)var11_10 * var14_14 * var14_14);
                        var17_17 = Math.max(1, Math.round((float)var4_4.width() * 0.16f * var14_14));
                        var18_18 = var16_16 << 24 | var12_11;
                        v0 = ItemArt$1.$SwitchMap$dev$quelchi$aurora$cosmetics$content$Trails$Style[var4_4.style().ordinal()];
                        if (var6_12) break block46;
                        block25 : switch (v0) {
                            case 1: {
                                var19_19 = (int)(Math.cos((double)var13_13 * 0.7) * 6.0);
                                var0.fill(var15_15, var10_9 + var19_19 - var17_17, var15_15 + 2, var10_9 + var19_19 + var17_17, var18_18);
                                var0.fill(var15_15, var10_9 - var19_19 - var17_17, var15_15 + 2, var10_9 - var19_19 + var17_17, var18_18);
                                if (!var6_12) break block47;
                            }
                            case 2: {
                                v1 = var13_13;
                                v2 = 3;
                                if (var6_12) // goto
                                if (v1 % v2 != 0) break block47;
                                v1 = 2;
                                v2 = var17_17 * 2;
lbl30:

                                var19_19 = Math.max(v1, v2);
                                var0.fill(var15_15, var10_9 - var19_19, var15_15 + var19_19, var10_9 + var19_19, var18_18);
                                if (!var6_12) break block47;
                            }
                            case 3: {
                                var19_19 = Math.max(1, Math.round((float)var4_4.width() * 0.3f * var14_14 * var14_14 * var14_14));
                                var0.fill(var15_15, var10_9 - var19_19, var15_15 + 2, var10_9 + var19_19, var18_18);
                                if (!var6_12) break block47;
                            }
                            case 4: {
                                var19_19 = Math.round(2.0f + 10.0f * (1.0f - var14_14));
                                var0.fill(var15_15, var10_9 - var19_19, var15_15 + 2, var10_9 + var19_19, Math.round((float)var16_16 * 0.5f) << 24 | var12_11);
                                if (!var6_12) break block47;
                            }
                            case 5: {
                                v3 = var13_13 / 3;
                                v4 = 2;
                                if (var6_12) // goto
                                if (v3 % v4 != 0) break block47;
                                v3 = 1;
                                v4 = var17_17 + 1;
lbl48:

                                var19_19 = Math.max(v3, v4);
                                var0.fill(var15_15, var10_9 - var19_19, var15_15 + 2, var10_9 + var19_19, var18_18);
                                if (!var6_12) break block47;
                            }
                            case 6: {
                                v5 = var13_13;
                                if (var6_12) // goto
                                if (v5 % 6 != 0) break block47;
                                v5 = Math.round(2.0f + 10.0f * (1.0f - var14_14));
lbl56:

                                var19_19 = v5;
                                for (var20_20 = 0; var20_20 < 12; ++var20_20) {
                                    v6 = (double)var20_20 / 12.0;
                                    if (var6_12) break block25;
                                    var21_21 = v6 * 3.141592653589793 * 2.0;
                                    var23_22 = var15_15 + (int)Math.round(Math.cos(var21_21) * (double)var19_19);
                                    var24_23 = var10_9 + (int)Math.round(Math.sin(var21_21) * (double)var19_19);
                                    var0.fill(var23_22, var24_23, var23_22 + 2, var24_23 + 2, var18_18);
                                    if (!var6_12) continue;
                                }
                                if (!var6_12) break block47;
                            }
                            case 7: {
                                var19_19 = (int)(((long)var13_13 * ItemArt.b(15521, 2551303713914563554L) >> 8 & ItemArt.b(10532, 8261763570266816101L)) - ItemArt.b(13929, 2086164560180945195L));
                                var0.fill(var15_15, var10_9 + var19_19, var15_15 + 2, var10_9 + var19_19 + 2, var18_18);
                                if (!var6_12) break block47;
                            }
                            case 8: {
                                var19_19 = Math.round(8.0f * var14_14);
                                var0.fill(var15_15, var10_9 + var19_19 - var17_17, var15_15 + 2, var10_9 + var19_19 + var17_17, var18_18);
                                var0.fill(var15_15, var10_9 - var19_19 - var17_17, var15_15 + 2, var10_9 - var19_19 + var17_17, var18_18);
                                if (!var6_12) break block47;
                            }
                            case 9: {
                                v7 = var13_13;
                                if (!var6_12) {
                                    v7 = v7 / 4 % 2 == 0 ? 1 : -1;
                                }
                                var19_19 = (int)((float)(v7 * 6) * (1.0f - (float)(var13_13 % 4) / 2.0f));
                                var0.fill(var15_15, var10_9 + var19_19 - var17_17, var15_15 + 2, var10_9 + var19_19 + var17_17, var18_18);
                                if (!var6_12) break block47;
                            }
                            case 10: {
                                v8 = var13_13;
                                if (var6_12) // goto
                                if (v8 % 4 != 0) break block47;
                                v8 = Math.round((1.0f - var14_14) * (1.0f - var14_14) * 12.0f);
lbl87:

                                var19_19 = v8;
                                var20_20 = Math.max(2, var17_17 + 2);
                                var0.fill(var15_15, var10_9 + var19_19, var15_15 + var20_20, var10_9 + var19_19 + var20_20, var18_18);
                                if (!var6_12) break block47;
                            }
                            case 11: {
                                var0.fill(var15_15, var10_9 - 6 - var17_17, var15_15 + 2, var10_9 - 6 + var17_17, var16_16 << 24 | var12_11 >> 1 & ItemArt.a(28349, 2482616339967294905L) | ItemArt.a(23951, 3850190133825310345L));
                                var0.fill(var15_15, var10_9 - var17_17, var15_15 + 2, var10_9 + var17_17, var18_18);
                                var0.fill(var15_15, var10_9 + 6 - var17_17, var15_15 + 2, var10_9 + 6 + var17_17, var16_16 << 24 | var12_11 >> 1 & ItemArt.a(13801, 8017011874709071595L) | ItemArt.a(11194, 2969913001155950781L));
                                if (!var6_12) break block47;
                            }
                            case 12: {
                                var0.fill(var15_15, var10_9 - var17_17, var15_15 + 2, var10_9 + var17_17, var18_18);
                                if (var6_12) break block48;
                                if (var13_13 % 3 != 0) break block47;
                                var19_19 = Math.round((1.0f - var14_14) * 10.0f);
                                var0.fill(var15_15, var10_9 - var19_19, var15_15 + 2, var10_9 - var19_19 + 2, Math.round((float)var16_16 * 0.8f) << 24 | var12_11);
                                if (!var6_12) break block47;
                            }
                            case 13: {
                                var0.fill(var15_15, var10_9 - 1, var15_15 + 2, var10_9 + 1, Math.round((float)var16_16 * 0.5f) << 24 | var12_11);
                                if (var6_12) break block48;
                                if (var13_13 % 5 != 0) break block47;
                                var19_19 = Math.max(2, var17_17 + 2);
                                var0.fill(var15_15 - 1, var10_9 - var19_19, var15_15 + var19_19, var10_9 + var19_19, var18_18);
                                if (!var6_12) break block47;
                            }
                            case 14: {
                                var0.fill(var15_15, var10_9 - 1, var15_15 + 2, var10_9 + 1, var18_18);
                                if (var6_12) break block48;
                                if (var13_13 % 3 != 0) break block47;
                                var0.fill(var15_15, var10_9 - 7, var15_15 + 2, var10_9 + 7, var18_18);
                                if (!var6_12) break block47;
                            }
                            default: {
                                v6 = Math.sin((double)var14_14 * 3.141592653589793 * 1.6) * 6.0;
                                break;
                            }
                            {
                            }
                        }
                        v0 = (int)v6;
                    }
                    var19_19 = v0;
                    var0.fill(var15_15, var10_9 + var19_19 - var17_17, var15_15 + 2, var10_9 + var19_19 + var17_17, var18_18);
                }
                ++var13_13;
            }
            if (!var6_12) continue;
        }
    }

    
    static {
        block12: {
            block11: {
                block10: {
                    block9: {
                        var8 = 6047579357014543392L;
                        var14_1 = new long[5];
                        var11_2 = 0;
                        var12_3 = "?\u00d3\u00a1\u00981\tB\u00a9\u007f\u001a\u0002\u00bf\u009a`4\u009d?\u00b6\u00bb\u00eb\\\u00fd\u008e\u00e6";
                        var13_4 = "?\u00d3\u00a1\u00981\tB\u00a9\u007f\u001a\u0002\u00bf\u009a`4\u009d?\u00b6\u00bb\u00eb\\\u00fd\u008e\u00e6".length();
                        var10_5 = 0;
                        while (true) {
                            var15_6 = var12_3.substring(var10_5, var10_5 += 8).getBytes("ISO-8859-1");
                            v0 = var14_1;
                            v1 = var11_2++;
                            v2 = ((long)var15_6[0] & 255L) << 56 | ((long)var15_6[1] & 255L) << 48 | ((long)var15_6[2] & 255L) << 40 | ((long)var15_6[3] & 255L) << 32 | ((long)var15_6[4] & 255L) << 24 | ((long)var15_6[5] & 255L) << 16 | ((long)var15_6[6] & 255L) << 8 | (long)var15_6[7] & 255L;
                            v3 = -1;
                            break block9;
                            break;
                        }
lbl14:

                        while (true) {
                            v0[v1] = v4;
                            if (var10_5 < var13_4) continue;
                            var12_3 = "\u0018\u00a0Xc\u00f2\u0014WO\u0016\u0014\u00f7H\u00c5\u00b6U\u00b4";
                            var13_4 = "\u0018\u00a0Xc\u00f2\u0014WO\u0016\u0014\u00f7H\u00c5\u00b6U\u00b4".length();
                            var10_5 = 0;
                            while (true) {
                                var15_6 = var12_3.substring(var10_5, var10_5 += 8).getBytes("ISO-8859-1");
                                v0 = var14_1;
                                v1 = var11_2++;
                                v2 = ((long)var15_6[0] & 255L) << 56 | ((long)var15_6[1] & 255L) << 48 | ((long)var15_6[2] & 255L) << 40 | ((long)var15_6[3] & 255L) << 32 | ((long)var15_6[4] & 255L) << 24 | ((long)var15_6[5] & 255L) << 16 | ((long)var15_6[6] & 255L) << 8 | (long)var15_6[7] & 255L;
                                v3 = 0;
                                break block9;
                                break;
                            }
                            break;
                        }
lbl27:

                        while (true) {
                            v0[v1] = v4;
                            if (var10_5 < var13_4) continue;
                            break block10;
                            break;
                        }
                    }
                    v4 = v2 ^ var8;
                    switch (v3) {
                        default: {
                            continue;
                        }
                        case 0:
lbl38:

                        continue;
                    }
                }
                ItemArt.a = var14_1;
                ItemArt.b = new Integer[5];
                var0_7 = 5449008866257181000L;
                var6_8 = new long[3];
                var3_9 = 0;
                var4_10 = "h\u00f6\u00c9\u0084\u00db\u0002\u00cb\u001bWmO,\u00cb\u00ae\u00b8d99j\u0080S2\u00a7\"";
                var5_11 = "h\u00f6\u00c9\u0084\u00db\u0002\u00cb\u001bWmO,\u00cb\u00ae\u00b8d99j\u0080S2\u00a7\"".length();
                var2_12 = 0;
                while (true) {
                    break block11;
                    break;
                }
lbl50:

                while (true) {
                    var6_8[v5] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block12;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v5 = var3_9++;
            while (true)
        }
        ItemArt.c = var6_8;
        ItemArt.d = new Long[3];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5306;
        if (b[n2] == null) {
            ItemArt.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x1743) & Short.MAX_VALUE;
        if (d[n2] == null) {
            ItemArt.d[n2] = c[n2] ^ l;
        }
        return d[n2];
    }
}
