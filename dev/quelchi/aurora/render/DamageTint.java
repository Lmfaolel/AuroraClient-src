package dev.quelchi.aurora.render;

import dev.quelchi.aurora.mixins.OverlayTextureAccessor;
import dev.quelchi.aurora.module.modules.DamageTintModule;
import dev.quelchi.aurora.render.EffectEngine;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;

public final class DamageTint {
    public static final int VANILLA;
    public static final int HURT_V;
    public static final int GRADIENT_V;
    private static Object lastTextureIdentity;
    private static int lastTop;
    private static int lastBottom;
    private static final long[] a;
    private static final Integer[] b;

    private DamageTint() {
    }

    
    public static void sync() {
        block70: {
            block68: {
                block65: {
                    block66: {
                        block67: {
                            block64: {
                                block62: {
                                    block63: {
                                        block60: {
                                            block61: {
                                                block58: {
                                                    block59: {
                                                        block57: {
                                                            var1 = DamageTintModule.get();
                                                            var0_1 = EffectEngine.a;
                                                            if (var1 == null) {
                                                                return;
                                                            }
                                                            var2_2 = MinecraftClient.method_1551();
                                                            v1 = var2_2;
                                                            if (var0_1 != 0) break block57;
                                                            if (v1 != null) {
                                                            }
                                                            v1 = var2_2;
                                                        }
                                                        if (var0_1 != 0) break block58;
                                                        if (v1.field_1773 != null) break block59;
lbl27:

                                                        return;
                                                    }
                                                    v1 = var2_2;
                                                }
                                                var3_3 = v1.field_1773.method_22975();
                                                v5 = var3_3;
                                                if (var0_1 != 0) break block60;
                                                if (v5 != null) break block61;
                                                return;
                                            }
                                            v5 = var3_3;
                                        }
                                        var4_4 = ((OverlayTextureAccessor)v5).divine$getTexture();
                                        if (var4_4 == null) {
                                            return;
                                        }
                                        var5_5 = var1.isEnabled();
                                        v8 = var5_5;
                                        if (var0_1 != 0) break block62;
                                        if (v8 == 0) break block63;
                                        v8 = var1.flashColorArgb();
                                        break block62;
                                    }
                                    v8 = DamageTint.a(14453, 3264191437327166851L);
                                }
                                var6_6 = v8;
                                v11 = var5_5;
                                if (var0_1 != 0) break block64;
                                if (v11 != 0) {
                                }
                                v13 = var1;
                                if (var0_1 == 0) {
                                }
                                v11 = (int)v13.gradientActive();
                            }
                            if (v11 != 0) {
                                v13 = var1;
lbl92:

                                v16 = v13.gradientColorArgb();
                            } else lbl_neg_1000:

                            {
                                v16 = var6_6;
                            }
                            var7_7 = v16;
                            v17 = var4_4;
                            if (var0_1 != 0) break block65;
                            if (v17 != DamageTint.lastTextureIdentity) break block66;
                            v19 = var6_6;
                            if (var0_1 != 0) break block67;
                            if (v19 != DamageTint.lastTop) break block66;
                            v19 = var7_7;
                        }
                        if (v19 == DamageTint.lastBottom) {
                            return;
                        }
                    }
                    v17 = var4_4;
                }
                var8_8 = v17.method_4525();
                if (var8_8 == null) {
                    return;
                }
                var9_9 = DamageTint.texel(var6_6);
                var10_10 = 0;
                while (var10_10 < 8) {
                    block69: {
                        if (var0_1 != 0) break block68;
                        for (var11_11 = 0; var11_11 < 16; ++var11_11) {
                            var8_8.method_61941(var11_11, var10_10, var9_9);
                            if (var0_1 == 0) {
                                if (var0_1 == 0) continue;
                                break;
                            }
                            break block69;
                        }
                        ++var10_10;
                    }
                    if (var0_1 == 0) continue;
                }
                var10_10 = 0;
            }
            do {
                block73: {
                    block71: {
                        block72: {
                            if (var10_10 >= 16) break;
                            if (var0_1 != 0) break block70;
                            v27 = var8_8;
                            v28 = var10_10;
                            v29 = 2;
                            v30 = var6_6;
                            if (var0_1 != 0) break block71;
                            if (v30 != var7_7) break block72;
                            v33 = var9_9;
                            break block73;
                        }
                        v30 = DamageTint.lerp(var6_6, var7_7, (float)var10_10 / 15.0f);
                    }
                    v33 = DamageTint.texel(v30);
                }
                v27.method_61941(v28, v29, v33);
                ++var10_10;
            } while (var0_1 == 0);
            var4_4.method_4524();
            DamageTint.lastTextureIdentity = var4_4;
            DamageTint.lastTop = var6_6;
            DamageTint.lastBottom = var7_7;
        }
    }

    public static void refresh() {
        lastTop = DamageTint.a(7300, 5751901093084285299L);
        lastBottom = DamageTint.a(10258, 4861547373013958115L);
        DamageTint.sync();
    }

    private static int texel(int n) {
        return 255 - (n >>> 24) << 24 | n & DamageTint.a(24289, 3969046577057237779L);
    }

    private static int lerp(int n, int n2, float f) {
        int n3 = Math.round((float)(n >>> 24 & 0xFF) + (float)((n2 >>> 24 & 0xFF) - (n >>> 24 & 0xFF)) * f);
        int n4 = Math.round((float)(n >>> 16 & 0xFF) + (float)((n2 >>> 16 & 0xFF) - (n >>> 16 & 0xFF)) * f);
        int n5 = Math.round((float)(n >>> 8 & 0xFF) + (float)((n2 >>> 8 & 0xFF) - (n >>> 8 & 0xFF)) * f);
        int n6 = Math.round((float)(n & 0xFF) + (float)((n2 & 0xFF) - (n & 0xFF)) * f);
        return n3 << 24 | n4 << 16 | n5 << 8 | n6;
    }

    
    static {
        block8: {
            block7: {
                var0 = 8552734491109840385L;
                var6_1 = new long[6];
                var3_2 = 0;
                var4_3 = "Ock\u007f\u00aaP\u00a2\u00edc-\u00fe\u001a2\u0082Y\u00dc\u00e5d\u00aad\u0094\u00c6E\u00fa\u00c4\u009c\u0099\u00f8\u0013\u00ef\u00ab\u00e2";
                var5_4 = "Ock\u007f\u00aaP\u00a2\u00edc-\u00fe\u001a2\u0082Y\u00dc\u00e5d\u00aad\u0094\u00c6E\u00fa\u00c4\u009c\u0099\u00f8\u0013\u00ef\u00ab\u00e2".length();
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
                    var4_3 = "\u00c9\u0086N\u00e8\u0081\u00eb\u00bb\u0082\u00a1\u009b\u00d0\u00de\u00ac\u00ad\u001fr";
                    var5_4 = "\u00c9\u0086N\u00e8\u0081\u00eb\u00bb\u0082\u00a1\u009b\u00d0\u00de\u00ac\u00ad\u001fr".length();
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
        DamageTint.a = var6_1;
        DamageTint.b = new Integer[6];
        DamageTint.GRADIENT_V = DamageTint.a(17929, 4973674997598065657L);
        DamageTint.VANILLA = DamageTint.a(23086, 5376841949538422749L);
        DamageTint.HURT_V = OverlayTexture.method_23212((boolean)true);
        DamageTint.lastTop = DamageTint.a(10258, 4861547373013958115L);
        DamageTint.lastBottom = DamageTint.a(10258, 4861547373013958115L);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x55F2;
        if (b[n2] == null) {
            DamageTint.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
