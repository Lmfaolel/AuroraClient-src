package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.Round$1;
import dev.quelchi.aurora.gui.Round$Key;
import dev.quelchi.aurora.gui.Round$Size;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.joml.Matrix3x2fStack;

public final class Round {
    private static final Map<Round$Key, Identifier> CACHE;
    private static final int WARM_MAX;
    private static int warmRadius;
    private static int warmScale;
    private static int idCounter;
    private static final int BAKE_AFTER;
    private static final int MAX_BAKED;
    private static final int MAX_BAKE_PIXELS;
    private static final Map<Round$Size, Identifier> WHOLE;
    private static final Map<Round$Size, long[]> STREAK;
    private static long frame;
    private static final Map<Round$Key, Identifier> RING_CACHE;
    private static final double CORNER_POWER = 2.7;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private Round() {
    }

    public static void warmStep() {
        int n;
        block10: {
            int n2;
            block11: {
                int n3;
                block8: {
                    block9: {
                        n2 = Math.max(1, MinecraftClient.method_1551().method_22683().method_4495());
                        n3 = PreviewScene.a;
                        n = n2;
                        if (n3 != 0) break block8;
                        if (n == warmScale) break block9;
                        warmScale = n2;
                        warmRadius = 1;
                    }
                    n = warmRadius;
                }
                if (n3 != 0) break block10;
                if (n <= 24) break block11;
                return;
            }
            Round.corners(warmRadius, n2);
            Round.ringCorners(warmRadius, n2);
            n = warmRadius + 1;
        }
        warmRadius = n;
    }

    public static void newFrame() {
        block4: {
            ++frame;
            int n = PreviewScene.a;
            long l = (frame & Round.b(23569, 6536601683227203148L)) - 0L;
            long l2 = l == 0L ? 0 : (l < 0L ? -1 : 1);
            if (n != 0 || l2 != false) break block4;
            l2 = (long)STREAK.entrySet().removeIf(Round::lambda$newFrame$0);
        }
    }

    public static void framed(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        if (n6 >>> 24 != 0) {
            Round.rect(drawContext, n, n2, n3, n4, n5, n7);
            Round.rect(drawContext, n + 1, n2 + 1, n3 - 2, n4 - 2, Math.max(0, n5 - 1), n6);
            return;
        }
        Round.outline(drawContext, n, n2, n3, n4, n5, n7);
    }

    public static void outline(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, int n6) {
        int n7;
        int n8;
        block21: {
            int n9;
            block20: {
                int n10;
                block22: {
                    block18: {
                        int n11;
                        block19: {
                            block17: {
                                n10 = PreviewScene.a;
                                n11 = n3;
                                if (n10 != 0) break block17;
                                if (n11 <= 0) break block18;
                                n11 = n4;
                            }
                            if (n10 != 0) break block19;
                            if (n11 <= 0) break block18;
                            n8 = n6;
                            n9 = 24;
                            if (n10 != 0) break block20;
                            n11 = n8 >>> n9;
                        }
                        if (n11 != 0) break block22;
                    }
                    return;
                }
                n8 = 0;
                n7 = n5;
                if (n10 != 0) break block21;
                n9 = Math.min(n7, Math.min(n3, n4) / 2);
            }
            n5 = Math.max(n8, n9);
            drawContext.method_25294(n + n5, n2, n + n3 - n5, n2 + 1, n6);
            drawContext.method_25294(n + n5, n2 + n4 - 1, n + n3 - n5, n2 + n4, n6);
            drawContext.method_25294(n, n2 + n5, n + 1, n2 + n4 - n5, n6);
            drawContext.method_25294(n + n3 - 1, n2 + n5, n + n3, n2 + n4 - n5, n6);
            if (n5 == 0) {
                return;
            }
            n8 = 1;
            n7 = MinecraftClient.method_1551().method_22683().method_4495();
        }
        int n12 = Math.max(n8, n7);
        Identifier identifier = Round.ringCorners(n5, n12);
        int n13 = n5 * n12;
        int n14 = n13 * 2;
        drawContext.method_25293(RenderPipelines.field_56883, identifier, n, n2, 0.0f, 0.0f, n5, n5, n13, n13, n14, n14, n6);
        drawContext.method_25293(RenderPipelines.field_56883, identifier, n + n3 - n5, n2, (float)n13, 0.0f, n5, n5, n13, n13, n14, n14, n6);
        drawContext.method_25293(RenderPipelines.field_56883, identifier, n, n2 + n4 - n5, 0.0f, (float)n13, n5, n5, n13, n13, n14, n14, n6);
        drawContext.method_25293(RenderPipelines.field_56883, identifier, n + n3 - n5, n2 + n4 - n5, (float)n13, (float)n13, n5, n5, n13, n13, n14, n14, n6);
    }

    public static void rect(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, int n6) {
        Identifier identifier;
        int n7;
        block24: {
            block25: {
                int n8;
                int n9;
                int n10;
                block27: {
                    int n11;
                    block23: {
                        block26: {
                            block21: {
                                int n12;
                                block22: {
                                    block20: {
                                        n10 = PreviewScene.a;
                                        n12 = n3;
                                        if (n10 != 0) break block20;
                                        if (n12 <= 0) break block21;
                                        n12 = n4;
                                    }
                                    if (n10 != 0) break block22;
                                    if (n12 <= 0) break block21;
                                    n11 = n6;
                                    if (n10 != 0) break block23;
                                    n12 = n11 >>> 24;
                                }
                                if (n12 != 0) break block26;
                            }
                            return;
                        }
                        n9 = 0;
                        n8 = n5;
                        if (n10 != 0) break block27;
                        n11 = n5 = Math.max(n9, Math.min(n8, Math.min(n3, n4) / 2));
                    }
                    if (n11 == 0) {
                        drawContext.method_25294(n, n2, n + n3, n2 + n4, n6);
                        return;
                    }
                    n9 = 1;
                    n8 = MinecraftClient.method_1551().method_22683().method_4495();
                }
                n7 = Math.max(n9, n8);
                Identifier identifier2 = Round.whole(n3, n4, n5, n7);
                identifier = identifier2;
                if (n10 != 0) break block24;
                if (identifier == null) break block25;
                drawContext.method_25293(RenderPipelines.field_56883, identifier2, n, n2, 0.0f, 0.0f, n3, n4, n3 * n7, n4 * n7, n3 * n7, n4 * n7, n6);
                return;
            }
            identifier = Round.corners(n5, n7);
        }
        Identifier identifier3 = identifier;
        int n13 = n5 * n7;
        int n14 = n13 * 2;
        drawContext.method_25293(RenderPipelines.field_56883, identifier3, n, n2, 0.0f, 0.0f, n5, n5, n13, n13, n14, n14, n6);
        drawContext.method_25293(RenderPipelines.field_56883, identifier3, n + n3 - n5, n2, (float)n13, 0.0f, n5, n5, n13, n13, n14, n14, n6);
        drawContext.method_25293(RenderPipelines.field_56883, identifier3, n, n2 + n4 - n5, 0.0f, (float)n13, n5, n5, n13, n13, n14, n14, n6);
        drawContext.method_25293(RenderPipelines.field_56883, identifier3, n + n3 - n5, n2 + n4 - n5, (float)n13, (float)n13, n5, n5, n13, n13, n14, n14, n6);
        drawContext.method_25294(n + n5, n2, n + n3 - n5, n2 + n5, n6);
        drawContext.method_25294(n + n5, n2 + n4 - n5, n + n3 - n5, n2 + n4, n6);
        drawContext.method_25294(n, n2 + n5, n + n3, n2 + n4 - n5, n6);
    }

    public static void circle(DrawContext drawContext, int n, int n2, int n3, int n4) {
        n3 = Math.max(2, n3 & 0xFFFFFFFE);
        Round.rect(drawContext, n - n3 / 2, n2 - n3 / 2, n3, n3, n3 / 2, n4);
    }

    public static void stroke(DrawContext drawContext, float f, float f2, float f3, float f4, int n, int n2) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        float f7 = (float)Math.sqrt(f5 * f5 + f6 * f6);
        int n3 = Math.max(2, n & 0xFFFFFFFE);
        int n4 = n3 / 2;
        Matrix3x2fStack matrix3x2fStack = drawContext.method_51448();
        matrix3x2fStack.pushMatrix();
        matrix3x2fStack.translate((float)Math.round(f), (float)Math.round(f2));
        matrix3x2fStack.rotate((float)Math.atan2(f6, f5));
        drawContext.method_25294(-n4, -n4, Math.max(n3, Math.round(f7)) + n4, n4, n2);
        matrix3x2fStack.popMatrix();
    }

    private static Identifier whole(int n, int n2, int n3, int n4) {
        long l;
        Round$Size round$Size;
        block31: {
            block32: {
                long l2;
                long[] lArray;
                int n5;
                block33: {
                    block30: {
                        int n6;
                        long[] lArray2;
                        block29: {
                            block27: {
                                long[] lArray3;
                                block28: {
                                    block25: {
                                        block26: {
                                            Object object;
                                            block23: {
                                                block24: {
                                                    n5 = PreviewScene.a;
                                                    if (n * n2 > 12000) {
                                                        return null;
                                                    }
                                                    round$Size = new Round$Size(n, n2, n3, n4);
                                                    Identifier identifier = WHOLE.get(round$Size);
                                                    object = identifier;
                                                    if (n5 != 0) break block23;
                                                    if (object == null) break block24;
                                                    return identifier;
                                                }
                                                object = STREAK.get(round$Size);
                                            }
                                            lArray = (long[])object;
                                            lArray3 = lArray;
                                            if (n5 != 0) break block25;
                                            if (lArray3 != null) break block26;
                                            STREAK.put(round$Size, new long[]{1L, frame});
                                            return null;
                                        }
                                        lArray3 = lArray;
                                    }
                                    if (n5 != 0) break block27;
                                    if (lArray3[1] != frame) break block28;
                                    return null;
                                }
                                lArray3 = lArray;
                            }
                            int n7 = 0;
                            lArray2 = lArray;
                            n6 = 1;
                            if (n5 != 0) break block29;
                            if (lArray2[n6] != frame - 1L) break block30;
                            lArray2 = lArray;
                            n6 = 0;
                        }
                        l2 = lArray2[n6] + 1L;
                        break block33;
                    }
                    l2 = 1L;
                }
                lArray3[n7] = l2;
                lArray[1] = frame;
                long l3 = lArray[0] - Round.b(24185, 580040134987535399L);
                l = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                if (n5 != 0) break block31;
                if (l >= 0) break block32;
                return null;
            }
            l = n;
        }
        Identifier identifier = Round.bake((int)l, n2, n3, n4);
        WHOLE.put(round$Size, identifier);
        return identifier;
    }

    private static Identifier bake(int n, int n2, int n3, int n4) {
        int n5 = n * n4;
        int n6 = n2 * n4;
        int n7 = PreviewScene.a;
        float f = n3 * n4;
        NativeImage nativeImage = new NativeImage(NativeImage.Format.field_4997, n5, n6, true);
        int n8 = 0;
        block0: while (true) {
            int n9 = n8;
            block1: while (n9 < n6) {
                for (int i = 0; i < n5; ++i) {
                    float f2 = Math.abs((float)i + 0.5f - (float)n5 / 2.0f) - ((float)n5 / 2.0f - f);
                    n9 = n8;
                    if (n7 != 0) continue block1;
                    float f3 = Math.abs((float)n9 + 0.5f - (float)n6 / 2.0f) - ((float)n6 / 2.0f - f);
                    float f4 = Round.corner(f2, f3, f);
                    int n10 = Math.round(255.0f * Round.clamp01(0.5f - f4));
                    nativeImage.method_61941(i, n8, n10 << 24 | Round.a(2418, 774189322133147512L));
                    if (n7 == 0) continue;
                }
                ++n8;
                if (n7 == 0) continue block0;
            }
            break;
        }
        String[] stringArray = a;
        Identifier identifier = Identifier.method_60655((String)stringArray[5], (String)(stringArray[2] + idCounter++));
        NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(Round::lambda$bake$1, nativeImage);
        nativeImageBackedTexture.method_4524();
        MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)nativeImageBackedTexture);
        return identifier;
    }

    private static Identifier ringCorners(int n, int n2) {
        int n3;
        Round$Key round$Key;
        block10: {
            Identifier identifier;
            block9: {
                round$Key = new Round$Key(n, n2);
                n3 = PreviewScene.a;
                Identifier identifier2 = RING_CACHE.get(round$Key);
                identifier = identifier2;
                if (n3 != 0) break block9;
                if (identifier == null) break block10;
                identifier = identifier2;
            }
            return identifier;
        }
        int n4 = n * 2 * n2;
        float f = n * n2;
        float f2 = (float)n4 / 2.0f;
        float f3 = n2;
        NativeImage nativeImage = new NativeImage(NativeImage.Format.field_4997, n4, n4, true);
        int n5 = 0;
        block6: while (true) {
            int n6 = n5;
            block7: while (n6 < n4) {
                for (int i = 0; i < n4; ++i) {
                    int n7;
                    int n8;
                    int n9;
                    NativeImage nativeImage2;
                    block13: {
                        int n10;
                        block11: {
                            int n11;
                            block12: {
                                float f4 = Math.abs((float)i + 0.5f - f2) - (f2 - f);
                                float f5 = Math.abs((float)n5 + 0.5f - f2) - (f2 - f);
                                float f6 = Round.corner(f4, f5, f);
                                n6 = Math.round(255.0f * Round.clamp01(Math.min(0.5f - f6, f6 + f3 + 0.5f)));
                                if (n3 != 0) continue block7;
                                n11 = n6;
                                nativeImage2 = nativeImage;
                                n9 = i;
                                n8 = n5;
                                n10 = n11;
                                if (n3 != 0) break block11;
                                if (n10 != 0) break block12;
                                n7 = 0;
                                break block13;
                            }
                            n10 = n11 << 24;
                        }
                        n7 = n10 | Round.a(2418, 774189322133147512L);
                    }
                    nativeImage2.method_61941(n9, n8, n7);
                    if (n3 == 0) continue;
                }
                ++n5;
                if (n3 == 0) continue block6;
            }
            break;
        }
        String[] stringArray = a;
        Identifier identifier = Identifier.method_60655((String)stringArray[5], (String)(stringArray[3] + idCounter++));
        NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(Round::lambda$ringCorners$2, nativeImage);
        nativeImageBackedTexture.method_4524();
        MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)nativeImageBackedTexture);
        RING_CACHE.put(round$Key, identifier);
        return identifier;
    }

    private static Identifier corners(int n, int n2) {
        int n3;
        Round$Key round$Key;
        block6: {
            Identifier identifier;
            block5: {
                round$Key = new Round$Key(n, n2);
                Identifier identifier2 = CACHE.get(round$Key);
                n3 = PreviewScene.a;
                identifier = identifier2;
                if (n3 != 0) break block5;
                if (identifier == null) break block6;
                identifier = identifier2;
            }
            return identifier;
        }
        int n4 = n * 2 * n2;
        float f = n * n2;
        float f2 = (float)n4 / 2.0f;
        NativeImage nativeImage = new NativeImage(NativeImage.Format.field_4997, n4, n4, true);
        int n5 = 0;
        while (n5 < n4) {
            block7: {
                for (int i = 0; i < n4; ++i) {
                    float f3 = Math.abs((float)i + 0.5f - f2) - (f2 - f);
                    if (n3 == 0) {
                        float f4 = Math.abs((float)n5 + 0.5f - f2) - (f2 - f);
                        float f5 = Round.corner(f3, f4, f);
                        int n6 = Math.round(255.0f * Round.clamp01(0.5f - f5));
                        nativeImage.method_61941(i, n5, n6 << 24 | Round.a(2421, 2476549437757198201L));
                        if (n3 == 0) continue;
                    }
                    break block7;
                }
                ++n5;
            }
            if (n3 == 0) continue;
        }
        String[] stringArray = a;
        Identifier identifier = Identifier.method_60655((String)stringArray[0], (String)(stringArray[6] + idCounter++));
        NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(Round::lambda$corners$3, nativeImage);
        nativeImageBackedTexture.method_4524();
        MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)nativeImageBackedTexture);
        CACHE.put(round$Key, identifier);
        return identifier;
    }

    private static float corner(float f, float f2, float f3) {
        float f4;
        block10: {
            float f5;
            float f6;
            block11: {
                float f7;
                block8: {
                    block9: {
                        f6 = Math.max(f, 0.0f);
                        int n = PreviewScene.a;
                        f5 = Math.max(f2, 0.0f);
                        f7 = f6;
                        if (n != 0) break block8;
                        if (f7 <= 0.0f) break block9;
                        f4 = f5;
                        if (n != 0) break block10;
                        if (!(f4 <= 0.0f)) break block11;
                    }
                    f7 = f6 + f5 + Math.min(Math.max(f, f2), 0.0f) - f3;
                }
                return f7;
            }
            f4 = (float)Math.pow(Math.pow(f6, 2.7) + Math.pow(f5, 2.7), 0.37037037037037035) - f3;
        }
        return f4;
    }

    private static float clamp01(float f) {
        float f2;
        block10: {
            float f3;
            block8: {
                int n;
                block9: {
                    n = PreviewScene.a;
                    f2 = f;
                    f3 = 0.0f;
                    if (n != 0) break block8;
                    if (!(f2 < f3)) break block9;
                    f2 = 0.0f;
                    break block10;
                }
                f2 = f;
                if (n != 0) break block10;
                f3 = 1.0f;
            }
            f2 = f2 > f3 ? 1.0f : f;
        }
        return f2;
    }

    private static String lambda$corners$3() {
        return a[1];
    }

    private static String lambda$ringCorners$2() {
        return a[7];
    }

    private static String lambda$bake$1() {
        return a[4];
    }

    private static boolean lambda$newFrame$0(Map.Entry entry) {
        long l;
        block2: {
            block3: {
                int n = PreviewScene.a;
                long l2 = frame - ((long[])entry.getValue())[1] - Round.b(3215, 1817156179318277843L);
                l = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                if (n != 0) break block2;
                if (l <= 0) break block3;
                l = 1;
                break block2;
            }
            l = 0;
        }
        return (boolean)l;
    }

    
    static {
        block32: {
            block31: {
                block30: {
                    block29: {
                        block28: {
                            block27: {
                                var21 = new String[8];
                                var19_1 = 0;
                                var18_2 = "818\u001d1\u0011\f818\u001d1\u0011(++?\u001c'\f++?\u001c'/p1+&\u0017l\u000b++?\u001c'/u0*-]\u0012818\u001d1\u0011(++?\u001c'/p1+&\u0017\u0006818\u001d1\u0011";
                                var20_3 = "818\u001d1\u0011\f818\u001d1\u0011(++?\u001c'\f++?\u001c'/p1+&\u0017l\u000b++?\u001c'/u0*-]\u0012818\u001d1\u0011(++?\u001c'/p1+&\u0017\u0006818\u001d1\u0011".length();
                                var17_4 = 6;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block27;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "++?\u001c'_\u0011818\u001d1\u0011(++?\u001c'/u0*-";
                                    var20_3 = "++?\u001c'_\u0011818\u001d1\u0011(++?\u001c'/u0*-".length();
                                    var17_4 = 6;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block27;
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
                                    break block28;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_6 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_6;
                                v9 = v6[v8];
                                switch (var22_6 % 7) {
                                    case 0: {
                                        v10 = 89;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 68;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 74;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 114;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 67;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 112;
                                        break;
                                    }
                                    default: {
                                        v10 = 7;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_6;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_6);
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
                        Round.a = var21;
                        var8_7 = 8969500964802548818L;
                        var14_8 = new long[6];
                        var11_9 = 0;
                        var12_10 = "m\u0094\u0091\u0093\u00deSk\u00c2UX9>\u00f6\u00ca)\u009d\u00b0\u0000c\u0007\u00df\u009b\u0088\u00f22zb%A]\u00a4\u00d4";
                        var13_11 = "m\u0094\u0091\u0093\u00deSk\u00c2UX9>\u00f6\u00ca)\u009d\u00b0\u0000c\u0007\u00df\u009b\u0088\u00f22zb%A]\u00a4\u00d4".length();
                        var10_12 = 0;
                        while (true) {
                            var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                            v11 = var14_8;
                            v12 = var11_9++;
                            v13 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                            v14 = -1;
                            break block29;
                            break;
                        }
lbl89:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_12 < var13_11) continue;
                            var12_10 = "\u00a8KTv}7\u0094\u009cr\u00fd\u0014\u00ba\u00cd\u00ce$\u00d5";
                            var13_11 = "\u00a8KTv}7\u0094\u009cr\u00fd\u0014\u00ba\u00cd\u00ce$\u00d5".length();
                            var10_12 = 0;
                            while (true) {
                                var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                                v11 = var14_8;
                                v12 = var11_9++;
                                v13 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                                v14 = 0;
                                break block29;
                                break;
                            }
                            break;
                        }
lbl102:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_12 < var13_11) continue;
                            break block30;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_7;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl113:

                        continue;
                    }
                }
                Round.b = var14_8;
                Round.c = new Integer[6];
                Round.MAX_BAKED = Round.a(17925, 6859029621520973838L);
                Round.MAX_BAKE_PIXELS = Round.a(6015, 6642974713159553392L);
                Round.WARM_MAX = Round.a(31705, 3689863282604015063L);
                Round.BAKE_AFTER = Round.a(23214, 4526434603691400355L);
                var0_14 = 6086251726686766021L;
                var6_15 = new long[3];
                var3_16 = 0;
                var4_17 = "MA`X/i\u00c7N\u000e\u00c0\u0019+\u00c0?\u0096v\\z\u0002\u00df\u000f\u00a3\u0017\u00e1";
                var5_18 = "MA`X/i\u00c7N\u000e\u00c0\u0019+\u00c0?\u0096v\\z\u0002\u00df\u000f\u00a3\u0017\u00e1".length();
                var2_19 = 0;
                while (true) {
                    break block31;
                    break;
                }
lbl129:

                while (true) {
                    var6_15[v16] = (((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L) ^ var0_14;
                    if (var2_19 < var5_18) continue;
                    break block32;
                    break;
                }
            }
            var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
            v16 = var3_16++;
            while (true)
        }
        Round.d = var6_15;
        Round.e = new Long[3];
        Round.CACHE = new HashMap<Round$Key, Identifier>();
        Round.warmRadius = 1;
        Round.warmScale = -1;
        Round.WHOLE = new Round$1(16, 0.75f, true);
        Round.STREAK = new HashMap<Round$Size, long[]>();
        Round.RING_CACHE = new HashMap<Round$Key, Identifier>();
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x60F;
        if (c[n2] == null) {
            Round.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0xA5C) & Short.MAX_VALUE;
        if (e[n2] == null) {
            Round.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
