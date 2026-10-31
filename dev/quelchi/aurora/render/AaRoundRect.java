package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.AaRoundRect$Baked;
import dev.quelchi.aurora.render.AaRoundRect$Key;
import dev.quelchi.aurora.render.EffectEngine;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

public final class AaRoundRect {
    private static final int SLOTS;
    private static final LinkedHashMap<AaRoundRect$Key, AaRoundRect$Baked> CACHE;
    private static int nextId;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private AaRoundRect() {
    }

    public static void draw(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, int n6) {
        AaRoundRect.draw(drawContext, n, n2, n3, n4, n5, n6, 0);
    }

    public static void draw(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        block18: {
            AaRoundRect$Baked aaRoundRect$Baked;
            block19: {
                float f;
                float f2;
                int n8;
                int n9;
                block16: {
                    block17: {
                        int n10;
                        block14: {
                            block15: {
                                block13: {
                                    block12: {
                                        n9 = EffectEngine.a;
                                        n10 = n3;
                                        if (n9 != 0) break block12;
                                        if (n10 <= 0) break block13;
                                        n10 = n4;
                                    }
                                    if (n9 != 0) break block14;
                                    if (n10 > 0) break block15;
                                }
                                return;
                            }
                            n10 = Math.max(1, MinecraftClient.method_1551().method_22683().method_4495());
                        }
                        int n11 = n10;
                        float f3 = drawContext.method_51448().m00();
                        n8 = 1;
                        f2 = n11;
                        f = f3;
                        if (n9 != 0) break block16;
                        if (!(f > 0.0f)) break block17;
                        f = f3;
                        break block16;
                    }
                    f = 1.0f;
                }
                int n12 = Math.max(n8, Math.round(f2 * f));
                int n13 = Math.max(0, Math.min(n5, Math.min(n3, n4) / 2));
                AaRoundRect$Key aaRoundRect$Key = new AaRoundRect$Key(n12, n3, n4, n13, n6, n7);
                aaRoundRect$Baked = CACHE.get(aaRoundRect$Key);
                if (n9 != 0) break block18;
                if (aaRoundRect$Baked != null) break block19;
                aaRoundRect$Baked = AaRoundRect.bake(aaRoundRect$Key);
                if (n9 != 0) break block18;
                CACHE.put(aaRoundRect$Key, aaRoundRect$Baked);
                if (CACHE.size() <= 16) break block19;
                Map.Entry<AaRoundRect$Key, AaRoundRect$Baked> entry = CACHE.entrySet().iterator().next();
                MinecraftClient.method_1551().method_1531().method_4615(entry.getValue().id());
                CACHE.remove(entry.getKey());
            }
            drawContext.method_25293(RenderPipelines.field_56883, aaRoundRect$Baked.id(), n, n2, 0.0f, 0.0f, n3, n4, aaRoundRect$Baked.texW(), aaRoundRect$Baked.texH(), aaRoundRect$Baked.texW(), aaRoundRect$Baked.texH(), -1);
        }
    }

    private static AaRoundRect$Baked bake(AaRoundRect$Key aaRoundRect$Key) {
        int n = aaRoundRect$Key.w() * aaRoundRect$Key.scale();
        int n2 = aaRoundRect$Key.h() * aaRoundRect$Key.scale();
        float f = aaRoundRect$Key.r() * aaRoundRect$Key.scale();
        int n3 = EffectEngine.a;
        float f2 = (float)n / 2.0f;
        float f3 = (float)n2 / 2.0f;
        NativeImage nativeImage = new NativeImage(NativeImage.Format.field_4997, n, n2, true);
        int n4 = 0;
        while (n4 < n2) {
            block8: {
                for (int i = 0; i < n; ++i) {
                    int n5;
                    int n6;
                    int n7;
                    NativeImage nativeImage2;
                    block13: {
                        int n8;
                        int n9;
                        block11: {
                            int n10;
                            block12: {
                                int n11;
                                block9: {
                                    float f4;
                                    block10: {
                                        float f5 = Math.abs((float)i + 0.5f - f2) - (f2 - f);
                                        float f6 = Math.abs((float)n4 + 0.5f - f3) - (f3 - f);
                                        float f7 = Math.max(f5, 0.0f);
                                        if (n3 != 0) break block8;
                                        float f8 = Math.max(f6, 0.0f);
                                        float f9 = (float)Math.sqrt(f7 * f7 + f8 * f8) + Math.min(Math.max(f5, f6), 0.0f) - f;
                                        f4 = AaRoundRect.clamp01(0.5f - f9);
                                        n9 = aaRoundRect$Key.argb();
                                        n11 = aaRoundRect$Key.border();
                                        if (n3 != 0) break block9;
                                        if (n11 == 0) break block10;
                                        n9 = AaRoundRect.lerp(n9, aaRoundRect$Key.border(), AaRoundRect.clamp01(f9 + 1.5f));
                                    }
                                    n11 = Math.round((float)(n9 >>> 24 & 0xFF) * f4);
                                }
                                n10 = n11;
                                nativeImage2 = nativeImage;
                                n7 = i;
                                n6 = n4;
                                n8 = n10;
                                if (n3 != 0) break block11;
                                if (n8 > 0) break block12;
                                n5 = 0;
                                break block13;
                            }
                            n8 = n10 << 24;
                        }
                        n5 = n8 | n9 & AaRoundRect.a(17718, 7040245268903037148L);
                    }
                    nativeImage2.method_61941(n7, n6, n5);
                    if (n3 == 0) continue;
                }
                ++n4;
            }
            if (n3 == 0) continue;
        }
        String[] stringArray = a;
        Identifier identifier = Identifier.method_60655((String)stringArray[0], (String)(stringArray[1] + nextId++));
        NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(AaRoundRect::lambda$bake$0, nativeImage);
        nativeImageBackedTexture.method_4524();
        MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)nativeImageBackedTexture);
        return new AaRoundRect$Baked(identifier, nativeImageBackedTexture, n, n2);
    }

    private static int lerp(int n, int n2, float f) {
        int n3;
        block2: {
            int n4 = 0;
            int n5 = EffectEngine.a;
            for (int i = 0; i <= 24; i += 8) {
                int n6 = n >>> i & 0xFF;
                int n7 = n2 >>> i & 0xFF;
                n3 = n4;
                if (n5 == 0) {
                    n4 = n3 | Math.round((float)n6 + (float)(n7 - n6) * f) << i;
                    if (n5 == 0) continue;
                }
                break block2;
            }
            n3 = n4;
        }
        return n3;
    }

    public static void roundCorners(NativeImage nativeImage, float f) {
        int n = nativeImage.method_4307();
        int n2 = nativeImage.method_4323();
        int n3 = EffectEngine.a;
        float f2 = (float)n / 2.0f;
        float f3 = (float)n2 / 2.0f;
        float f4 = Math.max(0.0f, Math.min(f, (float)Math.min(n, n2) / 2.0f));
        int n4 = 0;
        while (n4 < n2) {
            block5: {
                for (int i = 0; i < n; ++i) {
                    float f5;
                    float f6;
                    block6: {
                        float f7 = Math.abs((float)i + 0.5f - f2) - (f2 - f4);
                        float f8 = Math.abs((float)n4 + 0.5f - f3) - (f3 - f4);
                        if (n3 != 0) break block5;
                        float f9 = Math.max(f7, 0.0f);
                        float f10 = Math.max(f8, 0.0f);
                        float f11 = (float)Math.sqrt(f9 * f9 + f10 * f10) + Math.min(Math.max(f7, f8), 0.0f) - f4;
                        f6 = AaRoundRect.clamp01(0.5f - f11);
                        float f12 = f6 - 1.0f;
                        f5 = f12 == 0.0f ? 0 : (f12 > 0.0f ? 1 : -1);
                        if (n3 != 0) break block6;
                        if (f5 >= 0) {
                            continue;
                        }
                        f5 = nativeImage.method_61940(i, n4);
                    }
                    float f13 = f5;
                    int n5 = Math.round((float)(f13 >>> 24 & 0xFF) * f6);
                    nativeImage.method_61941(i, n4, n5 << 24 | f13 & AaRoundRect.a(31345, 697252225630844826L));
                    if (n3 == 0) continue;
                }
                ++n4;
            }
            if (n3 == 0) continue;
        }
    }

    private static float clamp01(float f) {
        float f2;
        block10: {
            block11: {
                int n;
                block8: {
                    block9: {
                        n = EffectEngine.a;
                        f2 = f;
                        if (n != 0) break block8;
                        if (!(f2 < 0.0f)) break block9;
                        f2 = 0.0f;
                        break block10;
                    }
                    f2 = f;
                }
                if (n != 0) break block10;
                if (!(f2 > 1.0f)) break block11;
                f2 = 1.0f;
                break block10;
            }
            f2 = f;
        }
        return f2;
    }

    private static String lambda$bake$0() {
        return a[2];
    }

    
    static {
        block16: {
            block15: {
                block14: {
                    var13 = new String[3];
                    var11_1 = 0;
                    var10_2 = "fQ$\rCH\u000buK#\fUv\u0006bG\"=\u0011fQ$\rCH[uK#\fUv\u0006bG\"";
                    var12_3 = "fQ$\rCH\u000buK#\fUv\u0006bG\"=\u0011fQ$\rCH[uK#\fUv\u0006bG\"".length();
                    var9_4 = 6;
                    var8_5 = -1;
lbl7:

                    while (true) {
                        continue;
                        break;
                    }
lbl9:

                    while (true) {
                        var13[var11_1++] = new String(v0).intern();
                        if ((var8_5 += var9_4) < var12_3) {
                            var9_4 = var10_2.charAt(var8_5);
                            continue;
                        }
                        break block14;
                        break;
                    }
                    v1 = ++var8_5;
                    v2 = var10_2.substring(v1, v1 + var9_4).toCharArray();
                    v0 = v2;
                    v3 = v2.length;
                    var14_6 = 0;
                    do {
                        v0 = v0;
                        v4 = var14_6;
                        v5 = v0[v4];
                        switch (var14_6 % 7) {
                            case 0: {
                                v6 = 7;
                                break;
                            }
                            case 1: {
                                v6 = 36;
                                break;
                            }
                            case 2: {
                                v6 = 86;
                                break;
                            }
                            case 3: {
                                v6 = 98;
                                break;
                            }
                            case 4: {
                                v6 = 49;
                                break;
                            }
                            case 5: {
                                v6 = 41;
                                break;
                            }
                            default: {
                                v6 = 116;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_6;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_6);
                    while (true)
                }
                AaRoundRect.a = var13;
                var0_7 = 8120588848438237462L;
                var6_8 = new long[3];
                var3_9 = 0;
                var4_10 = "\u00cb\u009cvH\u001e@.5\u000eJ>O\u0015D\u0091s\u0096\u00des\u00b8\u0088\u00d39e";
                var5_11 = "\u00cb\u009cvH\u001e@.5\u000eJ>O\u0015D\u0091s\u0096\u00des\u00b8\u0088\u00d39e".length();
                var2_12 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl63:

                while (true) {
                    var6_8[v7] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block16;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v7 = var3_9++;
            while (true)
        }
        AaRoundRect.b = var6_8;
        AaRoundRect.c = new Integer[3];
        AaRoundRect.SLOTS = AaRoundRect.a(11659, 6319227691907932259L);
        AaRoundRect.CACHE = new LinkedHashMap<K, V>(16, 0.75f, true);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4DEA;
        if (c[n2] == null) {
            AaRoundRect.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
