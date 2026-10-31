package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.AuroraBackdrop$Curtain;
import dev.quelchi.aurora.gui.AuroraBackdrop$Style;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.Theme;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import java.util.Random;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.joml.Matrix3x2fStack;

public final class AuroraBackdrop {
    private static final int CURTAIN_W;
    private static final int CURTAIN_H;
    private static final AuroraBackdrop$Style[] STYLES;
    private static final Identifier[][] CURTAIN_TEX;
    private static int current;
    private static Identifier ridgeId;
    private static int ridgeW;
    private static int ridgeH;
    private static int ridgeStyle;
    private static final int HERO_STARS;
    private static final float[] HERO_X;
    private static final float[] HERO_Y;
    private static final float[] HERO_FREQ;
    private static final float[] HERO_PHASE;
    private static Identifier starsId;
    private static int starsW;
    private static int starsH;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private AuroraBackdrop() {
    }

    public static int current() {
        return current;
    }

    public static void setCurrent(int n) {
        current = Math.floorMod(n, STYLES.length);
    }

    public static String name() {
        String string = AuroraBackdrop.STYLES[AuroraBackdrop.current].name;
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    private static AuroraBackdrop$Style style() {
        return STYLES[current];
    }

    public static void render(DrawContext drawContext, int n, int n2) {
        if (Im.available()) {
            ImScreen.panorama(drawContext, n, n2);
            return;
        }
        AuroraBackdrop.render(drawContext, n, n2, 1.0f);
    }

    public static void render(DrawContext drawContext, int n, int n2, float f) {
        DrawContext drawContext2;
        AuroraBackdrop$Style auroraBackdrop$Style;
        block17: {
            int n3;
            block18: {
                block15: {
                    int n4;
                    block16: {
                        block14: {
                            n3 = PreviewScene.a;
                            n4 = n;
                            if (n3 != 0) break block14;
                            if (n4 <= 0) break block15;
                            n4 = n2;
                        }
                        if (n3 != 0) break block16;
                        if (n4 <= 0) break block15;
                        float f2 = f - 0.0f;
                        n4 = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
                    }
                    if (n4 > 0) break block18;
                }
                return;
            }
            auroraBackdrop$Style = AuroraBackdrop.style();
            float f3 = (float)(System.currentTimeMillis() % AuroraBackdrop.b(1435, 1368852038467250496L)) / 1000.0f;
            int n5 = Math.round(255.0f * Math.min(1.0f, f));
            int n6 = Math.round((float)n2 * 0.6f);
            drawContext.method_25296(0, 0, n, n6, Theme.alpha(auroraBackdrop$Style.skyTop, n5), Theme.alpha(auroraBackdrop$Style.skyMid, n5));
            drawContext.method_25296(0, n6, n, n2, Theme.alpha(auroraBackdrop$Style.skyMid, n5), Theme.alpha(auroraBackdrop$Style.skyBot, n5));
            AuroraBackdrop.stars(drawContext, n, n2, f3, f);
            int n7 = AuroraBackdrop.indexOf(auroraBackdrop$Style);
            for (int i = 0; i < auroraBackdrop$Style.curtains.length; ++i) {
                drawContext2 = drawContext;
                if (n3 == 0) {
                    AuroraBackdrop.curtain(drawContext2, n7, i, auroraBackdrop$Style.curtains[i], n, n2, f3, f);
                    if (n3 == 0) continue;
                    break;
                }
                break block17;
            }
            drawContext2 = drawContext;
        }
        AuroraBackdrop.ridge(drawContext2, auroraBackdrop$Style, n, n2, f);
    }

    private static int indexOf(AuroraBackdrop$Style auroraBackdrop$Style) {
        int n = PreviewScene.a;
        for (int i = 0; i < STYLES.length; ++i) {
            if (STYLES[i] != auroraBackdrop$Style) continue;
            return i;
        }
        return 0;
    }

    private static void curtain(DrawContext drawContext, int n, int n2, AuroraBackdrop$Curtain auroraBackdrop$Curtain, int n3, int n4, float f, float f2) {
        Identifier identifier = AuroraBackdrop.texture(n, n2, auroraBackdrop$Curtain);
        int n5 = Math.round((float)n4 * auroraBackdrop$Curtain.top());
        int n6 = Math.max(1, Math.round((float)n4 * (auroraBackdrop$Curtain.bottom() - auroraBackdrop$Curtain.top())));
        float f3 = (f * auroraBackdrop$Curtain.drift % 1.0f + 1.0f) % 1.0f;
        float f4 = 0.8f + 0.2f * (float)Math.sin(f * 0.6f + auroraBackdrop$Curtain.phase * 2.0f);
        float f5 = (float)n4 * 0.018f * (float)Math.sin(f * 0.45f + auroraBackdrop$Curtain.phase);
        int n7 = Math.round(255.0f * auroraBackdrop$Curtain.alpha * f4 * f2) << 24 | auroraBackdrop$Curtain.rgb;
        Matrix3x2fStack matrix3x2fStack = drawContext.method_51448();
        matrix3x2fStack.pushMatrix();
        matrix3x2fStack.translate(-f3 * (float)n3, f5);
        drawContext.method_25293(RenderPipelines.field_56883, identifier, 0, n5, 0.0f, 0.0f, n3, n6, 512, 256, 512, 256, n7);
        drawContext.method_25293(RenderPipelines.field_56883, identifier, n3, n5, 0.0f, 0.0f, n3, n6, 512, 256, 512, 256, n7);
        matrix3x2fStack.popMatrix();
    }

    
    private static Identifier texture(int var0, int var1_1, AuroraBackdrop$Curtain var2_2) {
        block44: {
            block38: {
                block37: {
                    block36: {
                        block35: {
                            block34: {
                                block32: {
                                    block33: {
                                        var3_3 = PreviewScene.a;
                                        v0 = AuroraBackdrop.CURTAIN_TEX;
                                        if (var3_3 != 0) break block32;
                                        if (v0[var0] != null) break block33;
                                        AuroraBackdrop.CURTAIN_TEX[var0] = new Identifier[AuroraBackdrop.STYLES[var0].curtains.length];
                                    }
                                    v0 = AuroraBackdrop.CURTAIN_TEX;
                                }
                                if (var3_3 != 0) break block34;
                                if (v0[var0][var1_1] == null) break block35;
                                v0 = AuroraBackdrop.CURTAIN_TEX;
                            }
                            return v0[var0][var1_1];
                        }
                        var4_4 = new float[512];
                        var5_5 = new Random(AuroraBackdrop.b(19913, 934315111091970320L) * (long)var0 + (long)var1_1);
                        block24: for (var6_6 = 0; var6_6 < 6; ++var6_6) {
                            v5 = 9 + var5_5.nextInt(40);
                            if (var3_3 != 0) break block36;
                            var7_8 = v5;
                            var8_10 = var5_5.nextDouble() * 3.141592653589793 * 2.0;
                            for (var10_12 = 0; var10_12 < 512; ++var10_12) {
                                v6 = var4_4;
                                if (var3_3 == 0) {
                                    v7 = var10_12;
                                    v6[v7] = v6[v7] + (float)Math.sin(6.283185307179586 * (double)var7_8 * (double)var10_12 / 512.0 + var8_10) / 6.0f;
                                    if (var3_3 == 0) continue;
                                    continue block24;
                                }
                                break block37;
                            }
                            if (var3_3 == 0) continue;
                        }
                        v5 = AuroraBackdrop.a(2910, 3466501700245118836L);
                    }
                    v6 = new float[v5];
                }
                var6_7 = v6;
                for (var7_8 = 0; var7_8 < 512; ++var7_8) {
                    var8_10 = 6.283185307179586 * ((double)var7_8 + 0.5) / 512.0;
                    if (var3_3 != 0) break block38;
                    var10_14 = var2_2.base + var2_2.amp * (float)(Math.sin((double)var2_2.waves * var8_10 + (double)var2_2.phase) + 0.3 * Math.sin((double)var2_2.ripples * var8_10 + (double)var2_2.phase));
                    v9 = var11_15 = 0.55f + 0.45f * (float)Math.sin((double)var2_2.bands * var8_10 + (double)(var2_2.phase * 3.0f));
                    v10 = 1.0f - var2_2.rays * (0.5f - 0.5f * var4_4[var7_8]);
                    block27: while (true) {
                        var11_15 = v9 * v10;
                        for (var12_16 = 0; var12_16 < 256; ++var12_16) {
                            block43: {
                                block39: {
                                    block42: {
                                        block40: {
                                            block41: {
                                                v9 = var2_2.top();
                                                v10 = ((float)var12_16 + 0.5f) / 256.0f * (var2_2.bottom() - var2_2.top());
                                                if (var3_3 != 0) continue block27;
                                                var13_17 = v9 + v10;
                                                var14_18 = var10_14 - var13_17;
                                                v11 = var14_18;
                                                if (var3_3 != 0) break block39;
                                                if (v11 >= 0.0f) {
                                                }
                                                var16_20 = var14_18 / var2_2.height;
                                                v13 = var16_20;
                                                if (var3_3 != 0) break block40;
                                                if (!(v13 <= 0.25f)) break block41;
                                                v15 = 1.0f - 0.45f * (var16_20 / 0.25f);
                                                break block42;
                                            }
                                            v13 = 0.0f;
                                        }
                                        v15 = Math.max(v13, 0.55f * (1.0f - (var16_20 - 0.25f) / 0.75f));
                                    }
                                    var15_19 = v15;
                                    if (var3_3 == 0) break block43;
lbl98:

                                    v11 = (float)Math.exp(var14_18 / 0.012f);
                                }
                                var15_19 = v11;
                            }
                            var6_7[var12_16 * 512 + var7_8] = var15_19 * var11_15;
                            if (var3_3 == 0) continue;
                        }
                        break;
                    }
                    if (var3_3 == 0) continue;
                }
                AuroraBackdrop.blur(var6_7, 1, 4);
                AuroraBackdrop.blur(var6_7, 1, 4);
            }
            var7_9 = new NativeImage(NativeImage.Format.field_4997, 512, 256, true);
            for (var8_11 = 0; var8_11 < var6_7.length; ++var8_11) {
                block47: {
                    block45: {
                        block46: {
                            var9_21 = Math.round(255.0f * Math.min(1.0f, Math.max(0.0f, var6_7[var8_11])));
                            if (var3_3 != 0) break block44;
                            v18 = var7_9;
                            v19 = var8_11 % 512;
                            v20 = var8_11;
                            v21 = 512;
                            if (var3_3 != 0) break block45;
                            v20 = v20 / v21;
                            if (var9_21 != 0) break block46;
                            v24 = 0;
                            break block47;
                        }
                        v21 = var9_21 << 24;
                    }
                    v24 = v21 | AuroraBackdrop.a(18086, 4224646272411898522L);
                }
                v18.method_61941(v19, v20, v24);
                if (var3_3 == 0) continue;
            }
            AuroraBackdrop.CURTAIN_TEX[var0][var1_1] = AuroraBackdrop.upload(AuroraBackdrop.a[2] + var0 + "_" + var1_1, var7_9);
        }
        return AuroraBackdrop.CURTAIN_TEX[var0][var1_1];
    }

    
    private static void blur(float[] var0, int var1_1, int var2_2) {
        block8: {
            var4_3 = new float[var0.length];
            var3_4 = PreviewScene.a;
            var5_5 = 0;
            while (var5_5 < 256) {
                block9: {
                    if (var3_4 != 0) break block8;
                    for (var6_6 = 0; var6_6 < 512; ++var6_6) {
                        if (var3_4 != 0) break block9;
                        var7_7 = 0.0f;
                        for (var8_8 = -var1_1; var8_8 <= var1_1; ++var8_8) {
                            v0 = var7_7;
                            if (var3_4 == 0) {
                                var7_7 = v0 + var0[var5_5 * 512 + Math.floorMod(var6_6 + var8_8, 512)];
                                if (var3_4 == 0) continue;
                            }
                        }
                        var4_3[var5_5 * 512 + var6_6] = var7_7 / (float)(2 * var1_1 + 1);
                        if (var3_4 == 0) continue;
                    }
                    ++var5_5;
                }
                if (var3_4 == 0) continue;
            }
            var5_5 = 0;
        }
        while (var5_5 < 512) {
            block10: {
                var6_6 = 0;
                while (var6_6 < 256) {
                    block11: {
                        v0 = var7_7 = 0.0f;
lbl26:

                        if (var3_4 != 0) break block10;
                        for (var8_8 = -var2_2; var8_8 <= var2_2; ++var8_8) {
                            var9_9 = Math.max(0, Math.min(255, var6_6 + var8_8));
                            if (var3_4 == 0) {
                                var7_7 += var4_3[var9_9 * 512 + var5_5];
                                if (var3_4 == 0) continue;
                            }
                            break block11;
                        }
                        var0[var6_6 * 512 + var5_5] = var7_7 / (float)(2 * var2_2 + 1);
                        ++var6_6;
                    }
                    if (var3_4 == 0) continue;
                }
                ++var5_5;
            }
            if (var3_4 == 0) continue;
        }
    }

    private static void ridge(DrawContext drawContext, AuroraBackdrop$Style auroraBackdrop$Style, int n, int n2, float f) {
        int n3 = Math.round((float)n2 * 0.7f);
        int n4 = Math.round((float)(auroraBackdrop$Style.glow >>> 24 & 0xFF) * f);
        drawContext.method_25296(0, n3, n, n2, Theme.alpha(auroraBackdrop$Style.glow, 0), Theme.alpha(auroraBackdrop$Style.glow, n4));
        Identifier identifier = AuroraBackdrop.ridgeTexture(auroraBackdrop$Style, n, n2);
        if (identifier != null) {
            drawContext.method_25293(RenderPipelines.field_56883, identifier, 0, 0, 0.0f, 0.0f, n, n2, ridgeW, ridgeH, ridgeW, ridgeH, Theme.alpha(-1, Math.round(255.0f * f)));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Identifier ridgeTexture(AuroraBackdrop$Style auroraBackdrop$Style, int n, int n2) {
        Identifier identifier;
        block39: {
            NativeImage nativeImage;
            int n3;
            int n4;
            int n5;
            block43: {
                int n6;
                int n7;
                MinecraftClient minecraftClient;
                block38: {
                    block37: {
                        block36: {
                            block35: {
                                minecraftClient = MinecraftClient.method_1551();
                                int n8 = Math.max(1, minecraftClient.method_22683().method_4495());
                                n5 = n * n8;
                                n4 = n2 * n8;
                                n7 = PreviewScene.a;
                                n3 = AuroraBackdrop.indexOf(auroraBackdrop$Style);
                                if (ridgeId == null) break block35;
                                n6 = ridgeW;
                                if (n7 != 0) break block36;
                                if (n6 != n5) break block35;
                                n6 = ridgeH;
                                if (n7 != 0) break block36;
                                if (n6 != n4) break block35;
                                n6 = ridgeStyle;
                                if (n7 != 0) break block36;
                                if (n6 != n3) break block35;
                                return ridgeId;
                            }
                            n6 = n5;
                        }
                        if (n7 != 0) break block37;
                        if (n6 <= 0) return null;
                        n6 = n4;
                    }
                    if (n7 != 0) break block38;
                    if (n6 <= 0) return null;
                    long l = (long)n5 * (long)n4 - AuroraBackdrop.b(8548, 6280387653752606142L);
                    n6 = l == 0L ? 0 : (l < 0L ? -1 : 1);
                }
                if (n6 > 0) {
                    return null;
                }
                nativeImage = new NativeImage(NativeImage.Format.field_4997, n5, n4, true);
                int n9 = auroraBackdrop$Style.ridge & AuroraBackdrop.a(27191, 4507091993703267854L);
                int n10 = 0;
                while (n10 < n5) {
                    block40: {
                        double d = (double)n10 / (double)n5;
                        double d2 = 0.8 - 0.05 * Math.sin(d * 7.1 + 1.3) - 0.03 * Math.sin(d * 17.3 + 0.4) - 0.012 * Math.sin(d * 53.0);
                        double d3 = 0.87 - 0.04 * Math.sin(d * 4.3 + 2.2) - 0.025 * Math.sin(d * 13.7 + 1.1) - 0.01 * Math.sin(d * 41.0 + 0.7);
                        int n11 = (int)(d2 * (double)n4);
                        if (n7 != 0) break block39;
                        int n12 = (int)(d3 * (double)n4);
                        for (int i = Math.max(0, n11); i < n4; ++i) {
                            int n13;
                            block41: {
                                block42: {
                                    if (n7 != 0) break block40;
                                    n13 = i;
                                    if (n7 != 0) break block41;
                                    if (n13 < n12) break block42;
                                    n13 = 255;
                                    break block41;
                                }
                                n13 = 170;
                            }
                            int n14 = n13;
                            nativeImage.method_61941(n10, i, n14 << 24 | n9);
                            if (n7 == 0) continue;
                        }
                        ++n10;
                    }
                    if (n7 == 0) continue;
                }
                identifier = ridgeId;
                if (n7 != 0) return identifier;
                if (identifier == null) break block43;
                minecraftClient.method_1531().method_4615(ridgeId);
            }
            ridgeId = AuroraBackdrop.upload(a[3], nativeImage);
            ridgeW = n5;
            ridgeH = n4;
            ridgeStyle = n3;
        }
        identifier = ridgeId;
        return identifier;
    }

    private static void stars(DrawContext drawContext, int n, int n2, float f, float f2) {
        Identifier identifier = AuroraBackdrop.starField(n, n2);
        int n3 = PreviewScene.a;
        if (identifier != null) {
            drawContext.method_25293(RenderPipelines.field_56883, identifier, 0, 0, 0.0f, 0.0f, n, n2, starsW, starsH, starsW, starsH, Theme.alpha(-1, Math.round(255.0f * f2)));
        }
        for (int i = 0; i < 8; ++i) {
            int n4 = (int)(HERO_X[i] * (float)n);
            int n5 = (int)(HERO_Y[i] * (float)n2);
            float f3 = Math.abs((float)Math.sin(f * HERO_FREQ[i] + HERO_PHASE[i]));
            drawContext.method_25294(n4, n5, n4 + 1, n5 + 1, Theme.alpha(-1, (int)((70.0f + f3 * 185.0f) * f2)));
            if (n3 == 0) continue;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Identifier starField(int n, int n2) {
        Identifier identifier;
        block35: {
            NativeImage nativeImage;
            int n3;
            int n4;
            block40: {
                int n5;
                int n6;
                int n7;
                MinecraftClient minecraftClient;
                block34: {
                    block33: {
                        block32: {
                            block31: {
                                minecraftClient = MinecraftClient.method_1551();
                                n7 = Math.max(1, minecraftClient.method_22683().method_4495());
                                n4 = n * n7;
                                n6 = PreviewScene.a;
                                n3 = n2 * n7;
                                if (starsId == null) break block31;
                                n5 = starsW;
                                if (n6 != 0) break block32;
                                if (n5 != n4) break block31;
                                n5 = starsH;
                                if (n6 != 0) break block32;
                                if (n5 != n3) break block31;
                                return starsId;
                            }
                            n5 = n4;
                        }
                        if (n6 != 0) break block33;
                        if (n5 <= 0) return null;
                        n5 = n3;
                    }
                    if (n6 != 0) break block34;
                    if (n5 <= 0) return null;
                    long l = (long)n4 * (long)n3 - AuroraBackdrop.b(28773, 4241307978609728699L);
                    n5 = l == 0L ? 0 : (l < 0L ? -1 : 1);
                }
                if (n5 > 0) {
                    return null;
                }
                nativeImage = new NativeImage(NativeImage.Format.field_4997, n4, n3, true);
                Random random = new Random(AuroraBackdrop.b(15297, 6269396973896521502L));
                int n8 = 0;
                while (n8 < 90) {
                    block38: {
                        float f;
                        int n9;
                        int n10;
                        int n11;
                        block36: {
                            block37: {
                                n11 = random.nextInt(n4);
                                if (n6 != 0) break block35;
                                n10 = (int)(random.nextFloat() * (float)n3 * 0.7f);
                                n9 = 1;
                                float f2 = random.nextFloat() - 0.8f;
                                f = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
                                if (n6 != 0) break block36;
                                if (f >= 0) break block37;
                                f = 1.0f;
                                break block36;
                            }
                            f = 2;
                        }
                        int n12 = Math.max(n9, (int)(f * n7 / 2));
                        int n13 = 80 + random.nextInt(150);
                        int n14 = n10;
                        while (n14 < Math.min(n3, n10 + n12)) {
                            block39: {
                                if (n6 != 0) break block38;
                                for (int i = n11; i < Math.min(n4, n11 + n12); ++i) {
                                    nativeImage.method_61941(i, n14, n13 << 24 | AuroraBackdrop.a(27191, 4507091993703267854L));
                                    if (n6 == 0) {
                                        if (n6 == 0) continue;
                                        break;
                                    }
                                    break block39;
                                }
                                ++n14;
                            }
                            if (n6 == 0) continue;
                        }
                        ++n8;
                    }
                    if (n6 == 0) continue;
                }
                identifier = starsId;
                if (n6 != 0) return identifier;
                if (identifier == null) break block40;
                minecraftClient.method_1531().method_4615(starsId);
            }
            starsId = AuroraBackdrop.upload(a[1], nativeImage);
            starsW = n4;
            starsH = n3;
        }
        identifier = starsId;
        return identifier;
    }

    private static Identifier upload(String string, NativeImage nativeImage) {
        Identifier identifier = Identifier.method_60655((String)a[4], (String)string);
        NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(() -> AuroraBackdrop.lambda$upload$0(string), nativeImage);
        nativeImageBackedTexture.method_4524();
        MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)nativeImageBackedTexture);
        return identifier;
    }

    private static String lambda$upload$0(String string) {
        return a[6] + string;
    }

    
    static {
        block40: {
            block39: {
                block38: {
                    block37: {
                        block36: {
                            block35: {
                                var21 = new String[9];
                                var19_1 = 0;
                                var18_2 = "bhX\u000e\u000ed|H\u000e*2\u000bv2X\u0011/2\u0017\u0010d|H\u000e*2\u000bv2H\u0010<4\u0005os\u000ed|H\u000e*2\u000bv2Y\f*'\u0001\u0006ghY\n<!\u0004krE\n\u0007ghY\n<!K";
                                var20_3 = "bhX\u000e\u000ed|H\u000e*2\u000bv2X\u0011/2\u0017\u0010d|H\u000e*2\u000bv2H\u0010<4\u0005os\u000ed|H\u000e*2\u000bv2Y\f*'\u0001\u0006ghY\n<!\u0004krE\n\u0007ghY\n<!K".length();
                                var17_4 = 4;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block35;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "vrG\u0004<\u0007aqJ\u0006'%\u0016";
                                    var20_3 = "vrG\u0004<\u0007aqJ\u0006'%\u0016".length();
                                    var17_4 = 5;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block35;
                                        break;
                                    }
                                    break;
                                }
lbl26:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    break block36;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_7 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_7;
                                v9 = v6[v8];
                                switch (var22_7 % 7) {
                                    case 0: {
                                        v10 = 6;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 29;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 43;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 101;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 78;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 64;
                                        break;
                                    }
                                    default: {
                                        v10 = 100;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_7;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_7);
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
                        AuroraBackdrop.a = var21;
                        var8_8 = 4280888611185763691L;
                        var14_9 = new long[37];
                        var11_10 = 0;
                        var12_11 = "k%\u00dd\u00fa\u00acz\u00a1\u0098\u00b5\u0001\u00a0\u00eb>\u0093\u00dd\u00e6\u00cf\u009c\u00e1+\b\u00cc\u0094i\u00959\u00d2374\u00fa\u00fe\u00fd\u0002\u0088}\u00e1\u0018\u00b3\u00ff\u0088\u00cb0\u00e6\u00e7\u00bf\u00bd0\u0093\u0092\u00e2\u00b1\u0097\u009c\u00b9z\u00a3\u0004\u00df\u009f\u0013\u00ad\u00d7\u00a9\u008d\u00c6\u00b3\u0087\u00b1\u0093\u0098\u00c5\u000b\u0013\u00c2j\u0010\u008b\u00c8\u000e\u00a5{\u008f\u00f3;A\u00bc\u00de\u00c8%\u00d1\u00a2\u00ff\u00bf<\u008d-\u0005\u00ba\u00b2&\u00f1d\u009a\u00a42\u00157\u00af\u0084\u0096\u0012\u00b5#\u00fc\u0018&\u0090\u00f2~\u00ef\u00d9\u00bda\u009c_&\u00bc\u00bd\u00fe'\u00edI/@\u00d4\u00fbxn\u00f9h^3e\u0019a\u00a2b\u00f4\u00a9\u00c2\u00a4<\u00cd\u0091\u009dS\u0011\u00cf\u00efjCq\u0001\u00dae\u0013\u0080\u009e\u00e2\u0085\u00e3\u0090=\u00c3\u0012\u00b9\nKt\u00e8\u00ff\u00c1M\u00de\u00e1\u00ef\u00b7R+\np1\u00d7v\u00dc\u00a3\u009b\u0007w\u00c5\u00ca\u001dR'?M\u00e1\u009f\u00be\u001eE6L3\u0000\u00a6\u0087ed\u0001\u00a2\u0011\u0013\u00e8\u00d85\u001a\u00bcg\u009b\u0019\u00ec\u00bce\u00dc\u00e4\u001a$\u00cbb\u0017\u00ae`i\u001f\u00b5b\u009f\u00ce7,\u00d8\u00d9\u00fa\u001f\u00d7\u00fa(\n\u0081\u00d1\u0001\u00daB/\u0093\u0090Xm\u00aa\u008b\u00b6\u0007\u0000\u00ae\u00d6Vh\u00f2";
                        var13_12 = "k%\u00dd\u00fa\u00acz\u00a1\u0098\u00b5\u0001\u00a0\u00eb>\u0093\u00dd\u00e6\u00cf\u009c\u00e1+\b\u00cc\u0094i\u00959\u00d2374\u00fa\u00fe\u00fd\u0002\u0088}\u00e1\u0018\u00b3\u00ff\u0088\u00cb0\u00e6\u00e7\u00bf\u00bd0\u0093\u0092\u00e2\u00b1\u0097\u009c\u00b9z\u00a3\u0004\u00df\u009f\u0013\u00ad\u00d7\u00a9\u008d\u00c6\u00b3\u0087\u00b1\u0093\u0098\u00c5\u000b\u0013\u00c2j\u0010\u008b\u00c8\u000e\u00a5{\u008f\u00f3;A\u00bc\u00de\u00c8%\u00d1\u00a2\u00ff\u00bf<\u008d-\u0005\u00ba\u00b2&\u00f1d\u009a\u00a42\u00157\u00af\u0084\u0096\u0012\u00b5#\u00fc\u0018&\u0090\u00f2~\u00ef\u00d9\u00bda\u009c_&\u00bc\u00bd\u00fe'\u00edI/@\u00d4\u00fbxn\u00f9h^3e\u0019a\u00a2b\u00f4\u00a9\u00c2\u00a4<\u00cd\u0091\u009dS\u0011\u00cf\u00efjCq\u0001\u00dae\u0013\u0080\u009e\u00e2\u0085\u00e3\u0090=\u00c3\u0012\u00b9\nKt\u00e8\u00ff\u00c1M\u00de\u00e1\u00ef\u00b7R+\np1\u00d7v\u00dc\u00a3\u009b\u0007w\u00c5\u00ca\u001dR'?M\u00e1\u009f\u00be\u001eE6L3\u0000\u00a6\u0087ed\u0001\u00a2\u0011\u0013\u00e8\u00d85\u001a\u00bcg\u009b\u0019\u00ec\u00bce\u00dc\u00e4\u001a$\u00cbb\u0017\u00ae`i\u001f\u00b5b\u009f\u00ce7,\u00d8\u00d9\u00fa\u001f\u00d7\u00fa(\n\u0081\u00d1\u0001\u00daB/\u0093\u0090Xm\u00aa\u008b\u00b6\u0007\u0000\u00ae\u00d6Vh\u00f2".length();
                        var10_13 = 0;
                        while (true) {
                            var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                            v11 = var14_9;
                            v12 = var11_10++;
                            v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                            v14 = -1;
                            break block37;
                            break;
                        }
lbl89:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            var12_11 = "3\u0082\u00a1\u00a97\u00c1rN\u001a\u0096\u00a5\u0090X\u00a5\u00da\u00d0";
                            var13_12 = "3\u0082\u00a1\u00a97\u00c1rN\u001a\u0096\u00a5\u0090X\u00a5\u00da\u00d0".length();
                            var10_13 = 0;
                            while (true) {
                                var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                                v11 = var14_9;
                                v12 = var11_10++;
                                v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                                v14 = 0;
                                break block37;
                                break;
                            }
                            break;
                        }
lbl102:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            break block38;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_8;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl113:

                        continue;
                    }
                }
                AuroraBackdrop.b = var14_9;
                AuroraBackdrop.c = new Integer[37];
                AuroraBackdrop.CURTAIN_W = AuroraBackdrop.a(16038, 139567680586869386L);
                AuroraBackdrop.CURTAIN_H = AuroraBackdrop.a(563, 2763178290754573861L);
                AuroraBackdrop.HERO_STARS = AuroraBackdrop.a(2987, 7357173884351340445L);
                var0_15 = 3375942187619083597L;
                var6_16 = new long[6];
                var3_17 = 0;
                var4_18 = "y\u00f1\u00aa\u00c3\u00aaQ3\u00f3<&\u00e2%U:\u001a\u008d\u0005S\u00b1~D6\u00f2\u00ef\".\u009b,\u00cb\u00a0\u00c4B";
                var5_19 = "y\u00f1\u00aa\u00c3\u00aaQ3\u00f3<&\u00e2%U:\u001a\u008d\u0005S\u00b1~D6\u00f2\u00ef\".\u009b,\u00cb\u00a0\u00c4B".length();
                var2_20 = 0;
                while (true) {
                    var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
                    v16 = var6_16;
                    v17 = var3_17++;
                    v18 = ((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L;
                    v19 = -1;
                    break block39;
                    break;
                }
lbl133:

                while (true) {
                    v16[v17] = v20;
                    if (var2_20 < var5_19) continue;
                    var4_18 = "\u0014\u0005\u00e6\u00aa\u00e3\u00edb\u00f6y\u00d8\u009e\u00dc\u00baH\u00b2y";
                    var5_19 = "\u0014\u0005\u00e6\u00aa\u00e3\u00edb\u00f6y\u00d8\u009e\u00dc\u00baH\u00b2y".length();
                    var2_20 = 0;
                    while (true) {
                        var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
                        v16 = var6_16;
                        v17 = var3_17++;
                        v18 = ((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L;
                        v19 = 0;
                        break block39;
                        break;
                    }
                    break;
                }
lbl146:

                while (true) {
                    v16[v17] = v20;
                    if (var2_20 < var5_19) continue;
                    break block40;
                    break;
                }
            }
            v20 = v18 ^ var0_15;
            switch (v19) {
                default: {
                    continue;
                }
                case 0:
lbl157:

                continue;
            }
        }
        AuroraBackdrop.d = var6_16;
        AuroraBackdrop.e = new Long[6];
        v21 = new AuroraBackdrop$Style[4];
        var16_6 = AuroraBackdrop.a;
        v21[0] = new AuroraBackdrop$Style(var16_6[7], AuroraBackdrop.a(17440, 5913851225658560516L), AuroraBackdrop.a(22587, 4704679605169392648L), AuroraBackdrop.a(6412, 6637259923550227767L), AuroraBackdrop.a(26048, 2574839215705502177L), AuroraBackdrop.a(27710, 2835667426701129769L), new AuroraBackdrop$Curtain[]{new AuroraBackdrop$Curtain(0.44f, 0.07f, 0.42f, 1, 3, 5, 0.4f, AuroraBackdrop.a(3743, 2094180159972760253L), 0.42f, 0.014285714f, 0.55f), new AuroraBackdrop$Curtain(0.3f, 0.08f, 0.3f, 1, 4, 4, 2.1f, AuroraBackdrop.a(16755, 3241518881507612002L), 0.2f, -0.010526316f, 0.4f), new AuroraBackdrop$Curtain(0.52f, 0.05f, 0.26f, 2, 5, 6, 4.0f, AuroraBackdrop.a(4259, 7456060963066496147L), 0.26f, 0.02f, 0.6f)});
        v21[1] = new AuroraBackdrop$Style(var16_6[8], AuroraBackdrop.a(8701, 8253478056045835727L), AuroraBackdrop.a(24468, 7809312861422324651L), AuroraBackdrop.a(31868, 1153397302125275218L), AuroraBackdrop.a(19275, 9199401820248022884L), AuroraBackdrop.a(27302, 7959898763340562054L), new AuroraBackdrop$Curtain[]{new AuroraBackdrop$Curtain(0.42f, 0.065f, 0.4f, 1, 3, 5, 0.9f, AuroraBackdrop.a(22266, 6006610879091442393L), 0.36f, 0.0125f, 0.5f), new AuroraBackdrop$Curtain(0.36f, 0.085f, 0.46f, 1, 3, 4, 2.6f, AuroraBackdrop.a(6746, 6886437497947639403L), 0.22f, -0.009090909f, 0.35f), new AuroraBackdrop$Curtain(0.54f, 0.045f, 0.24f, 2, 5, 6, 4.4f, AuroraBackdrop.a(613, 7624159533645878848L), 0.24f, 0.018181818f, 0.6f)});
        v21[2] = new AuroraBackdrop$Style(var16_6[0], AuroraBackdrop.a(24314, 5122182315393714926L), AuroraBackdrop.a(31674, 184669934632906671L), AuroraBackdrop.a(31357, 3557427461077970516L), AuroraBackdrop.a(27807, 6587154699599067298L), AuroraBackdrop.a(8813, 5339936305373410899L), new AuroraBackdrop$Curtain[]{new AuroraBackdrop$Curtain(0.44f, 0.07f, 0.4f, 1, 3, 5, 0.4f, AuroraBackdrop.a(7102, 8920062096823013254L), 0.36f, 0.013333334f, 0.45f), new AuroraBackdrop$Curtain(0.38f, 0.09f, 0.46f, 1, 3, 4, 2.1f, AuroraBackdrop.a(3319, 3688953910495172826L), 0.24f, -0.010526316f, 0.35f), new AuroraBackdrop$Curtain(0.54f, 0.05f, 0.26f, 2, 5, 6, 4.0f, AuroraBackdrop.a(19257, 7467500244553309964L), 0.22f, 0.02f, 0.55f)});
        v21[3] = new AuroraBackdrop$Style(var16_6[5], AuroraBackdrop.a(14063, 3912841460626744008L), AuroraBackdrop.a(26917, 930086864075406610L), AuroraBackdrop.a(16289, 7545530585813216149L), AuroraBackdrop.a(16938, 3634819249121564161L), AuroraBackdrop.a(10871, 5476908039306739295L), new AuroraBackdrop$Curtain[]{new AuroraBackdrop$Curtain(0.44f, 0.07f, 0.4f, 1, 3, 5, 0.4f, AuroraBackdrop.a(21079, 4987180142410925681L), 0.24f, 0.014285714f, 0.5f), new AuroraBackdrop$Curtain(0.38f, 0.085f, 0.44f, 1, 3, 4, 2.1f, AuroraBackdrop.a(4877, 5091381554237589303L), 0.16f, -0.010526316f, 0.4f), new AuroraBackdrop$Curtain(0.54f, 0.05f, 0.26f, 2, 5, 6, 4.0f, AuroraBackdrop.a(27191, 4507091993703267854L), 0.14f, 0.02f, 0.6f)});
        AuroraBackdrop.STYLES = v21;
        AuroraBackdrop.CURTAIN_TEX = new Identifier[AuroraBackdrop.STYLES.length][];
        AuroraBackdrop.ridgeStyle = -1;
        AuroraBackdrop.HERO_X = new float[8];
        AuroraBackdrop.HERO_Y = new float[8];
        AuroraBackdrop.HERO_FREQ = new float[8];
        AuroraBackdrop.HERO_PHASE = new float[8];
        var23_22 = new Random(AuroraBackdrop.b(32323, 3137444293346366107L));
        for (var24_23 = 0; var24_23 < 8; ++var24_23) {
            AuroraBackdrop.HERO_X[var24_23] = 0.06f + var23_22.nextFloat() * 0.88f;
            AuroraBackdrop.HERO_Y[var24_23] = 0.04f + var23_22.nextFloat() * 0.4f;
            AuroraBackdrop.HERO_FREQ[var24_23] = 0.4f + var23_22.nextFloat() * 0.9f;
            AuroraBackdrop.HERO_PHASE[var24_23] = var23_22.nextFloat() * 6.28f;
        }
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5435;
        if (c[n2] == null) {
            AuroraBackdrop.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x6CDA) & Short.MAX_VALUE;
        if (e[n2] == null) {
            AuroraBackdrop.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
