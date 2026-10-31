package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CapePacks;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.cosmetics.backend.RemoteContent;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.gui.Theme;
import java.io.InputStream;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.joml.Matrix3x2fStack;

public final class AuroraSplash {
    public static volatile boolean broken;
    public static boolean revealing;
    private static final String LOGO_PATH;
    private static final Identifier LOGO_ID;
    private static final Identifier GLOW_ID;
    private static final int GLOW_TEX;
    private static final int GROUND;
    private static final int BAR_FROM;
    private static final int BAR_TO;
    private static NativeImage source;
    private static boolean sourceFailed;
    private static int logoPx;
    private static boolean glowBaked;
    private static long shownAt;
    private static long lastFrame;
    private static long assetsAt;
    private static float assetsBar;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long d;

    private AuroraSplash() {
    }

    private static int markSize(int n, int n2) {
        int n3 = Math.max(1, MinecraftClient.method_1551().method_22683().method_4495());
        return Math.max(40, Math.min(Math.round((float)Math.min(n, n2) * 0.12f), 500 / n3));
    }

    private static int markTop(int n, int n2) {
        return n / 2 - n2 / 2 - Math.round((float)n2 * 0.42f);
    }

    private static int gap(int n) {
        return Math.round((float)(n * 22) / 74.0f);
    }

    private static int barH(int n) {
        return Math.max(2, Math.round((float)(n * 4) / 74.0f));
    }

    public static void draw(DrawContext drawContext, int n, int n2, float f, float f2, float f3, float f4, boolean bl) {
        float f5;
        float f6;
        float f7;
        int n3;
        int n4;
        int n5;
        int n6;
        block42: {
            block43: {
                MinecraftClient minecraftClient;
                block40: {
                    long l;
                    block41: {
                        boolean bl2;
                        long l2;
                        block39: {
                            float f8;
                            float f9;
                            block37: {
                                block38: {
                                    n6 = PreviewScene.a;
                                    float f8 = f;
                                    f8 = 0.004f;
                                    if (n6 != 0) break block37;
                                    if (!(f9 > f8)) break block38;
                                    drawContext.method_25294(0, 0, n, n2, Theme.alpha(AuroraSplash.a(25416, 1418388863247422854L), Math.round(255.0f * f)));
                                }
                                float f8 = f2;
                                f8 = 0.004f;
                            }
                            if (f9 <= f8) {
                                return;
                            }
                            minecraftClient = MinecraftClient.method_1551();
                            l2 = Util.method_658();
                            bl2 = bl;
                            if (n6 != 0) break block39;
                            if (!bl2) break block40;
                            l = shownAt;
                            if (n6 != 0) break block41;
                            long l3 = l - 0L;
                            bl2 = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                        }
                        if (bl2) break block40;
                        l = l2;
                    }
                    shownAt = l;
                }
                float f10 = 1.0f;
                int n7 = Math.max(1, minecraftClient.method_22683().method_4495());
                n5 = AuroraSplash.markSize(n, n2);
                n4 = n / 2;
                n3 = AuroraSplash.markTop(n2, n5);
                float f11 = (float)n5 * (0.86f + 0.14f * f10);
                AuroraSplash.glow(drawContext, n, n2, n3 + n5 / 2, f2 *= f10);
                Identifier identifier = AuroraSplash.logo(n5 * n7);
                if (identifier != null) {
                    AuroraSplash.drawMark(drawContext, identifier, n5, n7, (float)n4 - f11 / 2.0f, (float)n3 + ((float)n5 - f11) / 2.0f, f11, f2);
                }
                f7 = f2 * f4;
                float f5 = f7;
                f5 = 0.004f;
                if (n6 != 0) break block42;
                if (!(f6 <= f5)) break block43;
                return;
            }
            float f5 = n5 * 220;
            f5 = 74.0f;
        }
        int n8 = Math.round(f6 / f5);
        int n9 = AuroraSplash.barH(n5);
        int n10 = n4 - n8 / 2;
        int n11 = n3 + n5 + AuroraSplash.gap(n5);
        Round.rect(drawContext, n10, n11, n8, n9, n9 / 2, Theme.alpha(AuroraSplash.a(25686, 7381079040642781850L), Math.round(26.0f * f7)));
        int n12 = Math.round((float)n8 * AuroraSplash.clamp01(f3));
        for (int i = 0; i < n12; ++i) {
            int n7;
            block46: {
                block45: {
                    block47: {
                        block44: {
                            n7 = n9;
                            if (n6 != 0) break block44;
                            if (n7 <= 2) break block45;
                            n7 = i;
                        }
                        if (n6 != 0) break block46;
                        if (n7 == 0) break block47;
                        n7 = i;
                        if (n6 != 0) break block46;
                        if (n7 != n12 - 1) break block45;
                    }
                    n7 = 1;
                    break block46;
                }
                n7 = 0;
            }
            int n14 = n7;
            drawContext.method_25294(n10 + i, n11 + n14, n10 + i + 1, n11 + n9 - n14, Theme.alpha(Theme.mix(AuroraSplash.a(20711, 7325866379153450530L), AuroraSplash.a(11741, 4446811880279366425L), (float)i / (float)Math.max(1, n12 - 1)), Math.round(255.0f * f7)));
            if (n6 == 0) continue;
        }
    }

    
    public static boolean assets(DrawContext drawContext, int n, int n2) {
        int n3;
        int n4;
        long l;
        block20: {
            block21: {
                long l2;
                block18: {
                    block19: {
                        int n5;
                        block16: {
                            long l3;
                            long l4;
                            block17: {
                                block14: {
                                    block15: {
                                        l4 = Util.method_658();
                                        n5 = PreviewScene.a;
                                        long l5 = assetsAt - 0L;
                                        long l6 = l5 == 0L ? 0 : (l5 < 0L ? -1 : 1);
                                        if (n5 == 0) {
                                            if (l6 == false) {
                                                assetsAt = l4;
                                            }
                                            l6 = (long)RemoteContent.applied();
                                        }
                                        l = l6;
                                        n4 = CapePacks.pending();
                                        int n6 = CosmeticsSync.contentTried;
                                        l2 = l;
                                        if (n5 != 0) break block14;
                                        if (l2 != false) break block15;
                                        n3 = n6;
                                        if (n5 != 0) break block16;
                                        if (n3 == 0) break block17;
                                    }
                                    l2 = n4;
                                }
                                if (n5 != 0) break block18;
                                if (l2 == false) break block19;
                            }
                            n3 = (l3 = l4 - assetsAt - d) == 0L ? 0 : (l3 < 0L ? -1 : 1);
                        }
                        if (n5 != 0) break block20;
                        if (n3 <= 0) break block21;
                    }
                    l2 = 1;
                }
                return (boolean)l2;
            }
            n3 = Math.max(1, CapePacks.started());
        }
        int n7 = n3;
        float f = l != false ? 0.2f + 0.8f * (float)(n7 - n4) / (float)n7 : 0.1f;
        assetsBar += (f - assetsBar) * 0.08f;
        AuroraSplash.draw(drawContext, n, n2, 1.0f, 1.0f, assetsBar, 1.0f, true);
        int n8 = AuroraSplash.markSize(n, n2);
        TextRenderer textRenderer = MinecraftClient.method_1551().field_1772;
        String[] stringArray = a;
        drawContext.method_51433(textRenderer, stringArray[4], (n - textRenderer.method_1727(stringArray[1])) / 2, AuroraSplash.markTop(n2, n8) + n8 + AuroraSplash.gap(n8) * 2 + AuroraSplash.barH(n8), AuroraSplash.a(4884, 3473368332244215261L), false);
        return false;
    }

    private static void drawMark(DrawContext drawContext, Identifier identifier, int n, int n2, float f, float f2, float f3, float f4) {
        int n3 = Math.round(255.0f * f4);
        if (n3 <= 0) {
            return;
        }
        Matrix3x2fStack matrix3x2fStack = drawContext.method_51448();
        matrix3x2fStack.pushMatrix();
        matrix3x2fStack.translate(f, f2);
        matrix3x2fStack.scale(f3 / (float)n, f3 / (float)n);
        drawContext.method_25293(RenderPipelines.field_56883, identifier, 0, 0, 0.0f, 0.0f, n, n, n * n2, n * n2, n * n2, n * n2, Theme.alpha(-1, Math.min(255, n3)));
        matrix3x2fStack.popMatrix();
    }

    private static float clamp01(float f) {
        return Math.max(0.0f, Math.min(1.0f, f));
    }

    public static void done() {
        block4: {
            block5: {
                int n = PreviewScene.a;
                revealing = false;
                if (n != 0) break block4;
                lastFrame = 0L;
                if (source == null) break block5;
                source.close();
                source = null;
            }
            sourceFailed = false;
        }
    }

    private static void glow(DrawContext drawContext, int n, int n2, int n3, float f) {
        int n4;
        block2: {
            block3: {
                int n5 = PreviewScene.a;
                n4 = glowBaked;
                if (n5 != 0) break block2;
                if (n4 != 0) break block3;
                AuroraSplash.bakeGlow();
            }
            n4 = Math.round((float)n * 0.68f);
        }
        int n6 = n4;
        int n7 = Math.round((float)n2 * 0.8f);
        drawContext.method_25293(RenderPipelines.field_56883, GLOW_ID, (n - n6) / 2, n3 - n7 / 2, 0.0f, 0.0f, n6, n7, 128, 128, 128, 128, Theme.alpha(AuroraSplash.a(14838, 8551954854983585597L), Math.round(46.0f * f)));
    }

    private static void bakeGlow() {
        block10: {
            NativeImage nativeImage = new NativeImage(NativeImage.Format.field_4997, 128, 128, true);
            float f = 63.5f;
            int n = 0;
            int n2 = PreviewScene.a;
            while (n < 128) {
                block11: {
                    if (n2 != 0) break block10;
                    for (int i = 0; i < 128; ++i) {
                        int n3;
                        int n4;
                        int n5;
                        NativeImage nativeImage2;
                        block17: {
                            int n6;
                            block15: {
                                int n7;
                                block16: {
                                    float f2;
                                    block14: {
                                        float f3;
                                        float f4;
                                        block12: {
                                            block13: {
                                                if (n2 != 0) break block11;
                                                f4 = (float)Math.hypot((float)i - f, (float)n - f) / f;
                                                f3 = f4;
                                                if (n2 != 0) break block12;
                                                if (!(f3 >= 1.0f)) break block13;
                                                f2 = 0.0f;
                                                break block14;
                                            }
                                            f3 = 1.0f - f4;
                                        }
                                        f2 = f3 * (1.0f - f4);
                                    }
                                    float f5 = f2;
                                    n7 = Math.round(255.0f * f5);
                                    nativeImage2 = nativeImage;
                                    n5 = i;
                                    n4 = n;
                                    n6 = n7;
                                    if (n2 != 0) break block15;
                                    if (n6 != 0) break block16;
                                    n3 = 0;
                                    break block17;
                                }
                                n6 = n7 << 24;
                            }
                            n3 = n6 | AuroraSplash.a(25686, 7381079040642781850L);
                        }
                        nativeImage2.method_61941(n5, n4, n3);
                        if (n2 == 0) continue;
                    }
                    ++n;
                }
                if (n2 == 0) continue;
            }
            AuroraSplash.upload(GLOW_ID, nativeImage);
            glowBaked = true;
        }
    }

    private static Identifier logo(int n) {
        block7: {
            NativeImage nativeImage;
            block8: {
                int n2 = PreviewScene.a;
                if (n == logoPx) {
                    return LOGO_ID;
                }
                nativeImage = AuroraSplash.source();
                if (n2 != 0) break block7;
                if (nativeImage != null) break block8;
                return null;
            }
            AuroraSplash.upload(LOGO_ID, AuroraSplash.resample(nativeImage, n));
            logoPx = n;
        }
        return LOGO_ID;
    }

    
    private static NativeImage source() {
        InputStream inputStream;
        String[] stringArray;
        int n;
        block29: {
            n = PreviewScene.a;
            NativeImage nativeImage = source;
            if (n != 0) return nativeImage;
            if (nativeImage != null || sourceFailed) {
                nativeImage = source;
                return nativeImage;
            }
            try {
                InputStream inputStream2;
                block28: {
                    stringArray = a;
                    inputStream = AuroraSplash.class.getResourceAsStream(stringArray[0]);
                    try {
                        inputStream2 = inputStream;
                        if (n == 0) {
                            if (inputStream2 != null) {
                                source = NativeImage.method_4309((InputStream)inputStream);
                            }
                            inputStream2 = inputStream;
                        }
                        if (n != 0) break block28;
                    }
                    catch (Throwable throwable) {
                        if (n != 0) throw throwable;
                        if (inputStream == null) throw throwable;
                        try {
                            inputStream.close();
                            throw throwable;
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                    if (inputStream2 == null) break block29;
                    inputStream2 = inputStream;
                }
                inputStream2.close();
            }
            catch (Exception exception) {
                stringArray = a;
                Aurora.LOG.warn(stringArray[3], (Object)exception.toString());
            }
        }
        NativeImage nativeImage = source;
        if (n == 0) {
            block31: {
                if (nativeImage == null) {
                    try {
                        InputStream inputStream3;
                        block30: {
                            stringArray = a;
                            inputStream = MinecraftClient.method_1551().method_1478().getResourceOrThrow(Identifier.method_60655((String)stringArray[8], (String)stringArray[9])).method_14482();
                            try {
                                inputStream3 = inputStream;
                                if (n != 0) break block30;
                                source = NativeImage.method_4309((InputStream)inputStream3);
                                if (inputStream == null) break block31;
                                inputStream3 = inputStream;
                            }
                            catch (Throwable throwable) {
                                if (n != 0) throw throwable;
                                if (inputStream == null) throw throwable;
                                try {
                                    inputStream.close();
                                    throw throwable;
                                }
                                catch (Throwable throwable3) {
                                    throwable.addSuppressed(throwable3);
                                }
                                throw throwable;
                            }
                        }
                        inputStream3.close();
                    }
                    catch (Exception exception) {
                        Aurora.LOG.warn(a[11], (Object)exception.toString());
                    }
                }
            }
            nativeImage = source;
        }
        sourceFailed = nativeImage == null;
        return source;
    }

    
    private static NativeImage resample(NativeImage var0, int var1_1) {
        block21: {
            block19: {
                var3_2 = var0.method_4307();
                var4_3 = var0.method_4323();
                var5_4 = new float[var3_2 * var4_3];
                var6_5 = new float[var1_1 * var4_3];
                var7_6 = new float[var1_1 * var1_1];
                var2_7 = PreviewScene.a;
                block12: for (var8_8 = 0; var8_8 < var4_3; ++var8_8) {
                    if (var2_7 != 0) break block19;
                    for (var9_10 = 0; var9_10 < var3_2; ++var9_10) {
                        v0 = var5_4;
                        v1 = var8_8 * var3_2 + var9_10;
                        v2 = var0.method_61940(var9_10, var8_8) >>> 24;
                        if (var2_7 == 0) {
                            v0[v1] = (float)v2 / 255.0f;
                            if (var2_7 == 0) continue;
                            continue block12;
                        }
                    }
                    if (var2_7 == 0) continue;
                }
                var8_8 = 0;
            }
            do {
                if (var8_8 >= var4_3) break;
                v0 = var5_4;
                v1 = var8_8;
                if (var2_7 == 0) {
                }
                AuroraSplash.span(v0, v1 * var3_2, 1, var3_2, var6_5, var8_8 * var1_1, 1, var1_1);
                ++var8_8;
            } while (var2_7 == 0);
            for (var8_8 = 0; var8_8 < var1_1; ++var8_8) {
                v0 = var6_5;
                v1 = var8_8;
lbl45:

                v2 = var1_1;
lbl46:

                AuroraSplash.span(v0, v1, v2, var4_3, var7_6, var8_8, var1_1, var1_1);
                if (var2_7 == 0) continue;
            }
            var8_9 = new NativeImage(NativeImage.Format.field_4997, var1_1, var1_1, true);
            for (var9_10 = 0; var9_10 < var1_1 * var1_1; ++var9_10) {
                block24: {
                    block22: {
                        block23: {
                            var10_11 = Math.min(255, Math.round(var7_6[var9_10] * 255.0f));
                            v6 = var8_9;
                            if (var2_7 != 0) break block21;
                            v7 = var9_10 % var1_1;
                            v8 = var9_10;
                            v9 = var1_1;
                            if (var2_7 != 0) break block22;
                            v8 = v8 / v9;
                            if (var10_11 != 0) break block23;
                            v12 = 0;
                            break block24;
                        }
                        v9 = var10_11 << 24;
                    }
                    v12 = v9 | AuroraSplash.a(16441, 1512006887867987700L);
                }
                v6.method_61941(v7, v8, v12);
                if (var2_7 == 0) continue;
            }
            v6 = var8_9;
        }
        return v6;
    }

    private static void span(float[] fArray, int n, int n2, int n3, float[] fArray2, int n4, int n5, int n6) {
        float f = (float)n3 / (float)n6;
        int n7 = PreviewScene.a;
        for (int i = 0; i < n6; ++i) {
            float f2;
            float f3;
            float f4 = f3 = (float)i * f;
            float f5 = f;
            block3: while (true) {
                float f6 = f4 + f5;
                int n8 = (int)f3;
                int n9 = Math.min(n3 - 1, (int)Math.ceil(f6) - 1);
                f2 = 0.0f;
                int n10 = n8;
                while (n10 <= n9) {
                    block5: {
                        block6: {
                            f4 = Math.min(f6, (float)(n10 + 1));
                            f5 = f3;
                            if (n7 != 0) continue block3;
                            float f7 = f4 - Math.max(f5, (float)n10);
                            if (n7 != 0) break block5;
                            if (!(f7 > 0.0f)) break block6;
                            f2 += fArray[n + n10 * n2] * f7;
                        }
                        ++n10;
                    }
                    if (n7 == 0) continue;
                }
                break;
            }
            fArray2[n4 + i * n5] = f2 / f;
            if (n7 == 0) continue;
        }
    }

    private static void upload(Identifier identifier, NativeImage nativeImage) {
        NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(() -> AuroraSplash.lambda$upload$0(identifier), nativeImage);
        nativeImageBackedTexture.method_4524();
        MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)nativeImageBackedTexture);
    }

    private static String lambda$upload$0(Identifier identifier) {
        return a[5] + identifier.method_12832();
    }

    
    static {
        block30: {
            block29: {
                block28: {
                    block27: {
                        block26: {
                            var15 = new String[12];
                            var13_1 = 0;
                            var12_2 = "k\u007f3uSK k\u007f5tYM2kj%~BJ!!moaCV|(q'iiS26y%(FQ4\u000e\bq!b_Q4d\u007f3uSK *k\u007f3uSK k\u007f5tYM2kj%~BJ!!moaCV|(q'iiS26y%(FQ4/\u0005k2iD^s7n,gEWidr/aY\u001f&*l%gR^1({``DP>dj(c\u0016U26$`}K\u000e\bq!b_Q4d\u007f3uSK \u0007%k2iD^|\u0006%k2iD^\u000b7n,gEW|#r/q\u0006%k2iD^\u001b0{8rCM671's_\u0010?+y/YZ^!#{nvXX";
                            var14_3 = "k\u007f3uSK k\u007f5tYM2kj%~BJ!!moaCV|(q'iiS26y%(FQ4\u000e\bq!b_Q4d\u007f3uSK *k\u007f3uSK k\u007f5tYM2kj%~BJ!!moaCV|(q'iiS26y%(FQ4/\u0005k2iD^s7n,gEWidr/aY\u001f&*l%gR^1({``DP>dj(c\u0016U26$`}K\u000e\bq!b_Q4d\u007f3uSK \u0007%k2iD^|\u0006%k2iD^\u000b7n,gEW|#r/q\u0006%k2iD^\u001b0{8rCM671's_\u0010?+y/YZ^!#{nvXX".length();
                            var11_4 = 42;
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
                                var12_2 = "7n,gEW|(q'i.\u0005k2iD^s7n,gEWidp/&ZP4+2`bD^$-p'&AV',q5r\u0016V'~>;{";
                                var14_3 = "7n,gEW|(q'i.\u0005k2iD^s7n,gEWidp/&ZP4+2`bD^$-p'&AV',q5r\u0016V'~>;{".length();
                                var11_4 = 11;
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
                        var16_7 = 0;
                        do {
                            v6 = v6;
                            v8 = var16_7;
                            v9 = v6[v8];
                            switch (var16_7 % 7) {
                                case 0: {
                                    v10 = 68;
                                    break;
                                }
                                case 1: {
                                    v10 = 30;
                                    break;
                                }
                                case 2: {
                                    v10 = 64;
                                    break;
                                }
                                case 3: {
                                    v10 = 6;
                                    break;
                                }
                                case 4: {
                                    v10 = 54;
                                    break;
                                }
                                case 5: {
                                    v10 = 63;
                                    break;
                                }
                                default: {
                                    v10 = 83;
                                }
                            }
                            v6[v8] = (char)(v9 ^ v10);
                            ++var16_7;
lbl65:

                            v7 = v7;
                        } while (v7 > var16_7);
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
                    AuroraSplash.a = var15;
                    AuroraSplash.LOGO_PATH = AuroraSplash.a[2];
                    var2_8 = 5237202675882062506L;
                    var8_9 = new long[11];
                    var5_10 = 0;
                    var6_11 = "P\u00a6@\t\u001c\u00cb\u001f\u00cf\u0080W\u00de\u00c9\u0011\u0003\u00bb\u00a1\u00c9\u00e3)\u00c6\u00fc\u0090o \u00f8\u0007Q(\u0005o\u0004_\u00e7\u00c9\u00d9\u000e\u009fJ\u0082#W\u00b7\u009b\u0005v\u00b8\u008b\u00dd}\u00d1\u00a1r\u00f02\u00d3\u00e6\u00a55\u009b\u00fa\u009a^\u00c1a\u0001F\u00c7\u00bdL\u00d2HG";
                    var7_12 = "P\u00a6@\t\u001c\u00cb\u001f\u00cf\u0080W\u00de\u00c9\u0011\u0003\u00bb\u00a1\u00c9\u00e3)\u00c6\u00fc\u0090o \u00f8\u0007Q(\u0005o\u0004_\u00e7\u00c9\u00d9\u000e\u009fJ\u0082#W\u00b7\u009b\u0005v\u00b8\u008b\u00dd}\u00d1\u00a1r\u00f02\u00d3\u00e6\u00a55\u009b\u00fa\u009a^\u00c1a\u0001F\u00c7\u00bdL\u00d2HG".length();
                    var4_13 = 0;
                    while (true) {
                        var9_14 = var6_11.substring(var4_13, var4_13 += 8).getBytes("ISO-8859-1");
                        v11 = var8_9;
                        v12 = var5_10++;
                        v13 = ((long)var9_14[0] & 255L) << 56 | ((long)var9_14[1] & 255L) << 48 | ((long)var9_14[2] & 255L) << 40 | ((long)var9_14[3] & 255L) << 32 | ((long)var9_14[4] & 255L) << 24 | ((long)var9_14[5] & 255L) << 16 | ((long)var9_14[6] & 255L) << 8 | (long)var9_14[7] & 255L;
                        v14 = -1;
                        break block28;
                        break;
                    }
lbl91:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_13 < var7_12) continue;
                        var6_11 = "\u0085\u00d2\u009b\u00d4fz`x\u0017u\u00ee\u0082\u00b2/\u00f2\u0010";
                        var7_12 = "\u0085\u00d2\u009b\u00d4fz`x\u0017u\u00ee\u0082\u00b2/\u00f2\u0010".length();
                        var4_13 = 0;
                        while (true) {
                            var9_14 = var6_11.substring(var4_13, var4_13 += 8).getBytes("ISO-8859-1");
                            v11 = var8_9;
                            v12 = var5_10++;
                            v13 = ((long)var9_14[0] & 255L) << 56 | ((long)var9_14[1] & 255L) << 48 | ((long)var9_14[2] & 255L) << 40 | ((long)var9_14[3] & 255L) << 32 | ((long)var9_14[4] & 255L) << 24 | ((long)var9_14[5] & 255L) << 16 | ((long)var9_14[6] & 255L) << 8 | (long)var9_14[7] & 255L;
                            v14 = 0;
                            break block28;
                            break;
                        }
                        break;
                    }
lbl104:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_13 < var7_12) continue;
                        break block29;
                        break;
                    }
                }
                v15 = v13 ^ var2_8;
                switch (v14) {
                    default: {
                        continue;
                    }
                    case 0:
lbl115:

                    continue;
                }
            }
            AuroraSplash.b = var8_9;
            AuroraSplash.c = new Integer[11];
            AuroraSplash.BAR_TO = AuroraSplash.a(25038, 4195988692377310977L);
            AuroraSplash.GLOW_TEX = AuroraSplash.a(1729, 610777478317109257L);
            AuroraSplash.BAR_FROM = AuroraSplash.a(17036, 4830375332294865994L);
            AuroraSplash.GROUND = AuroraSplash.a(24458, 606661965755525440L);
            break block30;
lbl124:

            while (true) {
                continue;
                break;
            }
        }
        var0_15 = 3117380003806808333L;
        while (true)
        AuroraSplash.d = 3117380003806824237L ^ var0_15;
        var10_6 = AuroraSplash.a;
        AuroraSplash.LOGO_ID = Identifier.method_60655((String)var10_6[6], (String)var10_6[10]);
        AuroraSplash.GLOW_ID = Identifier.method_60655((String)var10_6[6], (String)var10_6[7]);
        AuroraSplash.logoPx = -1;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x76CC;
        if (c[n2] == null) {
            AuroraSplash.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
