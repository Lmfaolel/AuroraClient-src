package dev.quelchi.aurora.render;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.CompiledRenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.SdfText$1;
import dev.quelchi.aurora.render.SdfText$Face;
import dev.quelchi.aurora.render.SdfText$Glyph;
import dev.quelchi.aurora.render.SdfText$Quads;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import java.lang.invoke.LambdaMetafactory;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;

public final class SdfText {
    public static final SdfText$Face INTER;
    public static final SdfText$Face SORA;
    public static final SdfText$Face SORA_LIGHT;
    private static final Identifier ATLAS_ID;
    private static final int EM;
    private static final int PAD;
    private static final int ATLAS_W;
    private static final int ATLAS_H;
    private static final float BASELINE = 7.0f;
    private static final int STRIDE;
    private static final RenderPipeline GUI;
    private static final RenderPipeline WORLD;
    private static final RenderLayer WORLD_LAYER;
    private static boolean broken;
    private static boolean full;
    private static GpuTexture atlas;
    private static TextureSetup setup;
    private static int penX;
    private static int penY;
    private static int rowH;
    private static final FloatArrayList[] PASSES;
    private static boolean open;
    private static final FloatArrayList WORLD_QUADS;
    private static final SdfText$Glyph MISSING;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private SdfText() {
    }

    private static RenderPipeline.Builder pipeline(String string) {
        String[] stringArray = a;
        return RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(Identifier.method_60655((String)stringArray[4], (String)string)).withVertexShader(Identifier.method_60655((String)stringArray[2], (String)stringArray[9])).withFragmentShader(Identifier.method_60655((String)stringArray[2], (String)stringArray[16])).withUniform(stringArray[11], UniformType.field_60031).withUniform(stringArray[7], UniformType.field_60031).withSampler(stringArray[6]).withBlend(BlendFunction.TRANSLUCENT).withVertexFormat(VertexFormats.field_20888, VertexFormat.DrawMode.field_27382).withDepthWrite(false).withCull(false);
    }

    
    private static boolean ready() {
        int n = EffectEngine.a;
        boolean bl = broken;
        if (n != 0) return bl;
        if (bl) {
            return false;
        }
        if (atlas != null) {
            return true;
        }
        try {
            GpuDevice gpuDevice;
            GpuDevice gpuDevice2;
            block21: {
                CompiledRenderPipeline compiledRenderPipeline;
                block20: {
                    gpuDevice2 = RenderSystem.getDevice();
                    compiledRenderPipeline = gpuDevice2.precompilePipeline(GUI);
                    if (n != 0) break block20;
                    if (!compiledRenderPipeline.isValid()) throw new IllegalStateException(a[15]);
                    gpuDevice = gpuDevice2;
                    if (n != 0) break block21;
                    compiledRenderPipeline = gpuDevice.precompilePipeline(WORLD);
                }
                if (!compiledRenderPipeline.isValid()) {
                    throw new IllegalStateException(a[15]);
                }
                gpuDevice = gpuDevice2;
            }
            String[] stringArray = a;
            GpuTexture gpuTexture = gpuDevice.createTexture(stringArray[0], 5, TextureFormat.RGBA8, 1024, 2048, 1, 1);
            try (NativeImage nativeImage = new NativeImage(NativeImage.Format.field_4997, 1024, 2048, true);){
                gpuDevice2.createCommandEncoder().writeToTexture(gpuTexture, nativeImage);
            }
            nativeImage = gpuDevice2.createTextureView(gpuTexture);
            GpuSampler gpuSampler = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
            SdfText$1 sdfText$1 = new SdfText$1(gpuTexture, (GpuTextureView)nativeImage);
            MinecraftClient.method_1551().method_1531().method_4616(ATLAS_ID, (AbstractTexture)sdfText$1);
            setup = TextureSetup.method_70900((GpuTextureView)nativeImage, (GpuSampler)gpuSampler);
            atlas = gpuTexture;
            return true;
        }
        catch (Exception | LinkageError throwable) {
            broken = true;
            Aurora.LOG.warn(a[3], throwable);
            return false;
        }
    }

    private static boolean usable(SdfText$Face sdfText$Face, String string) {
        int n;
        block15: {
            int n2;
            int n3;
            block13: {
                int n4;
                block14: {
                    boolean bl;
                    block11: {
                        block12: {
                            n3 = EffectEngine.a;
                            bl = SdfText.ready();
                            if (n3 != 0) break block11;
                            if (!bl) break block12;
                            n4 = sdfText$Face.load();
                            if (n3 != 0) break block13;
                            if (n4 != 0) break block14;
                        }
                        bl = false;
                    }
                    return bl;
                }
                n4 = n2 = 0;
            }
            while (n2 < string.length()) {
                block16: {
                    int n5;
                    block17: {
                        n = string.codePointAt(n2);
                        if (n3 != 0) break block15;
                        n5 = n;
                        if (n3 != 0) break block16;
                        if (sdfText$Face.glyph(n5) != MISSING) break block17;
                        return false;
                    }
                    n2 += Character.charCount(n5);
                }
                if (n3 == 0) continue;
            }
            n = 1;
        }
        return n != 0;
    }

    public static float width(SdfText$Face sdfText$Face, String string, float f) {
        int n;
        block14: {
            int n2;
            block13: {
                block11: {
                    block12: {
                        n = EffectEngine.a;
                        n2 = string.isEmpty();
                        if (n != 0) break block11;
                        if (n2 == 0) break block12;
                        return 0.0f;
                    }
                    n2 = SdfText.usable(sdfText$Face, string);
                }
                if (n != 0) break block13;
                if (n2 != 0) break block14;
                n2 = MinecraftClient.method_1551().field_1772.method_1727(string);
            }
            return (float)n2 * f / 10.0f;
        }
        float f2 = 0.0f;
        int n3 = -1;
        int n4 = 0;
        while (n4 < string.length()) {
            block15: {
                int n5;
                block16: {
                    n5 = string.codePointAt(n4);
                    if (n != 0) break block15;
                    if (n3 < 0) break block16;
                    f2 += (float)STBTruetype.stbtt_GetCodepointKernAdvance((STBTTFontinfo)sdfText$Face.info, (int)n3, (int)n5) * sdfText$Face.perUnit;
                }
                f2 += sdfText$Face.glyph(n5).advance();
                n3 = n5;
                n4 += Character.charCount(n5);
            }
            if (n == 0) continue;
        }
        return f2 * f;
    }

    public static void begin() {
        open = true;
    }

    public static void end(DrawContext drawContext) {
        open = false;
        SdfText.submit(drawContext);
    }

    public static void draw(DrawContext drawContext, SdfText$Face sdfText$Face, String string, float f, float f2, float f3, int n, boolean bl) {
        block18: {
            Matrix3x2fStack matrix3x2fStack;
            block19: {
                int n2;
                int n3;
                block16: {
                    block17: {
                        block15: {
                            block14: {
                                n3 = EffectEngine.a;
                                n2 = n;
                                if (n3 != 0) break block14;
                                if (n2 >>> 24 == 0) break block15;
                                n2 = string.isEmpty() ? 1 : 0;
                            }
                            if (n3 != 0) break block16;
                            if (n2 == 0) break block17;
                        }
                        return;
                    }
                    n2 = SdfText.usable(sdfText$Face, string) ? 1 : 0;
                }
                if (n2 == 0) {
                    SdfText.vanilla(drawContext, string, f, f2, f3, n, bl);
                    return;
                }
                matrix3x2fStack = drawContext.method_51448();
                if (n3 != 0) break block18;
                if (!bl) break block19;
                float f4 = f3 * 0.08f;
                SdfText.layout(PASSES[1], (Matrix3x2f)matrix3x2fStack, sdfText$Face, string, f + f4 * 0.75f, f2 + f4, f3, Math.round((float)(n >>> 24) * 0.6f) << 24, 0.03f, 0.2f);
            }
            SdfText.layout(PASSES[2], (Matrix3x2f)matrix3x2fStack, sdfText$Face, string, f, f2, f3, n, 0.0f, 0.0f);
        }
        if (!open) {
            SdfText.submit(drawContext);
        }
    }

    public static void glow(DrawContext drawContext, SdfText$Face sdfText$Face, String string, float f, float f2, float f3, int n, float f4) {
        int n2;
        block16: {
            block17: {
                block14: {
                    int n3;
                    block15: {
                        block13: {
                            n3 = EffectEngine.a;
                            n2 = n;
                            if (n3 != 0) break block13;
                            if (n2 >>> 24 == 0) break block14;
                            n2 = string.isEmpty() ? 1 : 0;
                        }
                        if (n3 != 0) break block15;
                        if (n2 != 0) break block14;
                        n2 = SdfText.usable(sdfText$Face, string) ? 1 : 0;
                    }
                    if (n3 != 0) break block16;
                    if (n2 != 0) break block17;
                }
                return;
            }
            SdfText.layout(PASSES[0], (Matrix3x2f)drawContext.method_51448(), sdfText$Face, string, f, f2, f3, n, 0.05f, 0.15f + 0.3f * f4);
            n2 = open ? 1 : 0;
        }
        if (n2 == 0) {
            SdfText.submit(drawContext);
        }
    }

    public static void drawWorld(VertexConsumerProvider vertexConsumerProvider, Matrix4f matrix4f, SdfText$Face sdfText$Face, String string, float f, float f2, float f3, int n, int n2, float f4) {
        block15: {
            FloatArrayList floatArrayList;
            int n3;
            block13: {
                Matrix3x2f matrix3x2f;
                block14: {
                    boolean bl;
                    block11: {
                        block12: {
                            n3 = EffectEngine.a;
                            bl = string.isEmpty();
                            if (n3 != 0) break block11;
                            if (!bl) break block12;
                            return;
                        }
                        bl = SdfText.usable(sdfText$Face, string);
                    }
                    if (!bl) {
                        float f5 = f3 / 10.0f;
                        Matrix4f matrix4f2 = new Matrix4f((Matrix4fc)matrix4f).translate(f, f2 + 7.0f, 0.0f).scale(f5, f5, 1.0f);
                        MinecraftClient.method_1551().field_1772.method_27522((Text)Text.method_43470((String)string), 0.0f, -7.0f, n, false, matrix4f2, vertexConsumerProvider, TextRenderer.TextLayerType.field_33993, 0, SdfText.a(31227, 4446374407164843574L));
                        return;
                    }
                    floatArrayList = WORLD_QUADS;
                    matrix3x2f = new Matrix3x2f();
                    if (n3 != 0) break block13;
                    if (n2 >>> 24 == 0) break block14;
                    SdfText.layout(floatArrayList, matrix3x2f, sdfText$Face, string, f, f2, f3, n2, 0.05f, 0.15f + 0.3f * f4);
                }
                SdfText.layout(floatArrayList, matrix3x2f, sdfText$Face, string, f, f2, f3, n, 0.0f, 0.0f);
            }
            VertexConsumer vertexConsumer = vertexConsumerProvider.method_73477(WORLD_LAYER);
            float[] fArray = floatArrayList.elements();
            int n4 = floatArrayList.size();
            for (int i = 0; i < n4; i += 15) {
                int n5 = Float.floatToRawIntBits(fArray[i + 12]);
                int n6 = (int)fArray[i + 13];
                int n7 = (int)fArray[i + 14];
                vertexConsumer.method_22918((Matrix4fc)matrix4f, fArray[i], fArray[i + 1], 0.0f).method_39415(n5).method_22913(fArray[i + 8], fArray[i + 9]).method_22921(n6, n7);
                vertexConsumer.method_22918((Matrix4fc)matrix4f, fArray[i + 2], fArray[i + 3], 0.0f).method_39415(n5).method_22913(fArray[i + 8], fArray[i + 11]).method_22921(n6, n7);
                if (n3 == 0) {
                    vertexConsumer.method_22918((Matrix4fc)matrix4f, fArray[i + 4], fArray[i + 5], 0.0f).method_39415(n5).method_22913(fArray[i + 10], fArray[i + 11]).method_22921(n6, n7);
                    vertexConsumer.method_22918((Matrix4fc)matrix4f, fArray[i + 6], fArray[i + 7], 0.0f).method_39415(n5).method_22913(fArray[i + 10], fArray[i + 9]).method_22921(n6, n7);
                    if (n3 == 0) continue;
                    break;
                }
                break block15;
            }
            floatArrayList.clear();
        }
    }

    private static void layout(FloatArrayList floatArrayList, Matrix3x2f matrix3x2f, SdfText$Face sdfText$Face, String string, float f, float f2, float f3, int n, float f4, float f5) {
        float f6 = Float.intBitsToFloat(n);
        int n2 = EffectEngine.a;
        float f7 = Math.round(f4 * 1000.0f);
        float f8 = Math.round(f5 * 1000.0f);
        float f9 = f2 + 7.0f;
        float f10 = 0.0f;
        int n3 = -1;
        int n4 = 0;
        while (n4 < string.length()) {
            SdfText$Glyph sdfText$Glyph;
            float f11;
            block5: {
                SdfText$Glyph sdfText$Glyph2;
                block6: {
                    int n5 = string.codePointAt(n4);
                    int n6 = n4 + Character.charCount(n5);
                    if (n2 == 0) {
                        n4 = n6;
                        if (n3 >= 0) {
                            f10 += (float)STBTruetype.stbtt_GetCodepointKernAdvance((STBTTFontinfo)sdfText$Face.info, (int)n3, (int)n5) * sdfText$Face.perUnit;
                        }
                        n6 = n5;
                    }
                    n3 = n6;
                    sdfText$Glyph2 = sdfText$Face.glyph(n5);
                    f11 = sdfText$Glyph2.x1();
                    sdfText$Glyph = sdfText$Glyph2;
                    if (n2 != 0) break block5;
                    if (!(f11 > sdfText$Glyph.x0())) break block6;
                    float f12 = f + (f10 + sdfText$Glyph2.x0()) * f3;
                    float f13 = f + (f10 + sdfText$Glyph2.x1()) * f3;
                    float f14 = f9 + sdfText$Glyph2.y0() * f3;
                    float f15 = f9 + sdfText$Glyph2.y1() * f3;
                    SdfText.corner(floatArrayList, matrix3x2f, f12, f14);
                    SdfText.corner(floatArrayList, matrix3x2f, f12, f15);
                    SdfText.corner(floatArrayList, matrix3x2f, f13, f15);
                    SdfText.corner(floatArrayList, matrix3x2f, f13, f14);
                    floatArrayList.add(sdfText$Glyph2.u0());
                    floatArrayList.add(sdfText$Glyph2.v0());
                    floatArrayList.add(sdfText$Glyph2.u1());
                    floatArrayList.add(sdfText$Glyph2.v1());
                    floatArrayList.add(f6);
                    floatArrayList.add(f7);
                    floatArrayList.add(f8);
                }
                f11 = f10;
                sdfText$Glyph = sdfText$Glyph2;
            }
            f10 = f11 + sdfText$Glyph.advance();
            if (n2 == 0) continue;
        }
    }

    private static void corner(FloatArrayList floatArrayList, Matrix3x2f matrix3x2f, float f, float f2) {
        floatArrayList.add(matrix3x2f.m00() * f + matrix3x2f.m10() * f2 + matrix3x2f.m20());
        floatArrayList.add(matrix3x2f.m01() * f + matrix3x2f.m11() * f2 + matrix3x2f.m21());
    }

    private static void submit(DrawContext drawContext) {
        ScreenRect screenRect;
        ScreenRect screenRect2;
        ScreenRect screenRect3;
        float[] fArray;
        block15: {
            block16: {
                float f;
                int n;
                int n2;
                float f2;
                float f3;
                int n3;
                block14: {
                    float f4;
                    block13: {
                        int n4;
                        int n5;
                        block12: {
                            block11: {
                                n5 = 0;
                                FloatArrayList[] objectArray = PASSES;
                                n3 = EffectEngine.a;
                                for (FloatArrayList floatArrayList : objectArray) {
                                    if (n3 == 0) {
                                        n5 += floatArrayList.size();
                                        if (n3 == 0) continue;
                                    }
                                    break block11;
                                }
                                n4 = n5;
                                if (n3 != 0) break block12;
                                if (n4 != 0) break block11;
                                return;
                            }
                            n4 = n5;
                        }
                        fArray = new float[n4];
                        int n6 = 0;
                        for (FloatArrayList f22 : PASSES) {
                            f22.getElements(0, fArray, n6, f22.size());
                            n6 += f22.size();
                            f22.clear();
                            if (n3 == 0) continue;
                        }
                        float f5 = Float.MAX_VALUE;
                        f4 = Float.MAX_VALUE;
                        f3 = -3.4028235E38f;
                        f2 = -3.4028235E38f;
                        for (n2 = 0; n2 < n5; n2 += 15) {
                            if (n3 != 0) break block13;
                            for (n = 0; n < 8; n += 2) {
                                f5 = Math.min(f5, fArray[n2 + n]);
                                f = f3;
                                if (n3 == 0) {
                                    f3 = Math.max(f, fArray[n2 + n]);
                                    f4 = Math.min(f4, fArray[n2 + n + 1]);
                                    f2 = Math.max(f2, fArray[n2 + n + 1]);
                                    if (n3 == 0) continue;
                                }
                                break block14;
                            }
                            if (n3 == 0) continue;
                        }
                        n2 = (int)Math.floor(f5);
                    }
                    f = f4;
                }
                n = (int)Math.floor(f);
                screenRect3 = new ScreenRect(n2, n, Math.max(1, (int)Math.ceil(f3) - n2), Math.max(1, (int)Math.ceil(f2) - n));
                screenRect2 = drawContext.field_44659.method_70863();
                screenRect = screenRect2;
                if (n3 != 0) break block15;
                if (screenRect == null) break block16;
                screenRect3 = screenRect3.method_49701(screenRect2);
            }
            screenRect = screenRect3;
        }
        if (screenRect == null) {
            return;
        }
        drawContext.field_59826.method_70919((SimpleGuiElementRenderState)new SdfText$Quads(fArray, GUI, setup, screenRect2, screenRect3));
    }

    private static void vanilla(DrawContext drawContext, String string, float f, float f2, float f3, int n, boolean bl) {
        Matrix3x2fStack matrix3x2fStack = drawContext.method_51448();
        matrix3x2fStack.pushMatrix();
        matrix3x2fStack.translate(f, f2 + 7.0f);
        matrix3x2fStack.scale(f3 / 10.0f, f3 / 10.0f);
        drawContext.method_51433(MinecraftClient.method_1551().field_1772, string, 0, -7, n, bl);
        matrix3x2fStack.popMatrix();
    }

    private static GpuSampler lambda$static$0() {
        return RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[18];
                        var11_1 = 0;
                        var10_2 = "\b{s[\u00037h\u001ajg\u0014\u000530\u001d\f\u001aasU\\bxY u@\u0017\u0006\b{s[\u00037::JG\u0014\u000530\u001d.uA\u00038-\r.nR\u0017zh\u001dky@Q0)\u0005br\u0014\u00137+\u0002.u[Q )\u0007gmX\u0010q;I|dZ\u00153:\f|\u0006\b{s[\u00037\u0015\u0019gqQ\u001d?&\f!rP\u0017\t<\fvuk\u0016#!\b:olD\u001d3:Y\n9|n^\u00145<\u0000ao\u0017\u0019gqQ\u001d?&\f!rP\u0017\t<\fvuk\u00069:\u0005j\r\nasQ^%,\u000fQuQ\t\"\f\u001aasU\\`xY u@\u0017\u0011-woU\u001c?+=|`Z\u00020'\u001bcr\b:olD\u001d3:Y\b\u001ajgk\u000530\u001d\r\u0000`uQ\u0003{\u007fY>/@\u000505\u001ajg\u0014\u000530\u001d.q]\u00013$\u0000`dGQ2!\r.o[\u0005v+\u0006cq]\u001d3dI}dQQ\" \f.m[\u0016v)\u000bawQ";
                        var12_3 = "\b{s[\u00037h\u001ajg\u0014\u000530\u001d\f\u001aasU\\bxY u@\u0017\u0006\b{s[\u00037::JG\u0014\u000530\u001d.uA\u00038-\r.nR\u0017zh\u001dky@Q0)\u0005br\u0014\u00137+\u0002.u[Q )\u0007gmX\u0010q;I|dZ\u00153:\f|\u0006\b{s[\u00037\u0015\u0019gqQ\u001d?&\f!rP\u0017\t<\fvuk\u0016#!\b:olD\u001d3:Y\n9|n^\u00145<\u0000ao\u0017\u0019gqQ\u001d?&\f!rP\u0017\t<\fvuk\u00069:\u0005j\r\nasQ^%,\u000fQuQ\t\"\f\u001aasU\\`xY u@\u0017\u0011-woU\u001c?+=|`Z\u00020'\u001bcr\b:olD\u001d3:Y\b\u001ajgk\u000530\u001d\r\u0000`uQ\u0003{\u007fY>/@\u000505\u001ajg\u0014\u000530\u001d.q]\u00013$\u0000`dGQ2!\r.o[\u0005v+\u0006cq]\u001d3dI}dQQ\" \f.m[\u0016v)\u000bawQ".length();
                        var9_4 = 15;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block25;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "\nasQ^%,\u000fQuQ\t\"\u000f\b{s[\u00037\u0017\u001ajgk\u000530\u001d";
                            var12_3 = "\nasQ^%,\u000fQuQ\t\"\u000f\b{s[\u00037\u0017\u001ajgk\u000530\u001d".length();
                            var9_4 = 13;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block25;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            break block26;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_7 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_7;
                        v9 = v6[v8];
                        switch (var14_7 % 7) {
                            case 0: {
                                v10 = 105;
                                break;
                            }
                            case 1: {
                                v10 = 14;
                                break;
                            }
                            case 2: {
                                v10 = 1;
                                break;
                            }
                            case 3: {
                                v10 = 52;
                                break;
                            }
                            case 4: {
                                v10 = 113;
                                break;
                            }
                            case 5: {
                                v10 = 86;
                                break;
                            }
                            default: {
                                v10 = 72;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_7;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_7);
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
                SdfText.a = var13;
                var0_8 = 2140606390944897991L;
                var6_9 = new long[6];
                var3_10 = 0;
                var4_11 = "\u00d6\u009b\u00bd\u0081\u00e0\u0002:\u00896\u00c6\u00ba%\u00c5;s*\u00a8\u00df\u00b0:\u00dd\u00f2t\u00ff\u0084\u00b9\u00d8\\\u00f5\u00b1M\u0001";
                var5_12 = "\u00d6\u009b\u00bd\u0081\u00e0\u0002:\u00896\u00c6\u00ba%\u00c5;s*\u00a8\u00df\u00b0:\u00dd\u00f2t\u00ff\u0084\u00b9\u00d8\\\u00f5\u00b1M\u0001".length();
                var2_13 = 0;
                while (true) {
                    var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                    v11 = var6_9;
                    v12 = var3_10++;
                    v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    var4_11 = "v\u00a9K\u009f\u00d5\u0092\u009d`\u00cf\u00fe\u008f\u00f9\u00c5\u00bb)n";
                    var5_12 = "v\u00a9K\u009f\u00d5\u0092\u009d`\u00cf\u00fe\u008f\u00f9\u00c5\u00bb)n".length();
                    var2_13 = 0;
                    while (true) {
                        var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                        v11 = var6_9;
                        v12 = var3_10++;
                        v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var0_8;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        SdfText.b = var6_9;
        SdfText.c = new Integer[6];
        SdfText.ATLAS_H = SdfText.a(18676, 6422085287349970744L);
        SdfText.STRIDE = SdfText.a(10594, 922246310246024872L);
        SdfText.PAD = SdfText.a(3724, 5077855580495913282L);
        SdfText.ATLAS_W = SdfText.a(6498, 8032003754901943977L);
        SdfText.EM = SdfText.a(18194, 3867124966680488157L);
        var8_6 = SdfText.a;
        SdfText.INTER = new SdfText$Face(var8_6[14]);
        SdfText.SORA = new SdfText$Face(var8_6[10]);
        SdfText.SORA_LIGHT = new SdfText$Face(var8_6[1]);
        SdfText.ATLAS_ID = Identifier.method_60655((String)var8_6[2], (String)var8_6[13]);
        SdfText.GUI = RenderPipelines.method_67887((RenderPipeline)SdfText.pipeline(var8_6[5]).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).build());
        SdfText.WORLD = RenderPipelines.method_67887((RenderPipeline)SdfText.pipeline(var8_6[8]).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).build());
        SdfText.WORLD_LAYER = RenderLayer.method_75940((String)var8_6[17], (RenderSetup)RenderSetup.method_75927((RenderPipeline)SdfText.WORLD).method_76560(var8_6[12], SdfText.ATLAS_ID, (Supplier<GpuSampler>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$static$0(), ()Lnet/minecraft/client/gl/GpuSampler;)()).method_75938());
        SdfText.PASSES = new FloatArrayList[]{new FloatArrayList(), new FloatArrayList(), new FloatArrayList()};
        SdfText.WORLD_QUADS = new FloatArrayList();
        SdfText.MISSING = new SdfText$Glyph(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2FCE;
        if (c[n2] == null) {
            SdfText.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
