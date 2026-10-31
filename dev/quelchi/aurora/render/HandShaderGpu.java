package dev.quelchi.aurora.render;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.quelchi.aurora.render.EffectEngine;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.OptionalInt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.lwjgl.system.MemoryStack;
import org.slf4j.LoggerFactory;

public final class HandShaderGpu {
    private static final Identifier VERTEX;
    private static final Identifier BLEND_FRAG;
    private static final int PARAM_VECS;
    private static final Map<String, RenderPipeline> OVER;
    private static final Map<String, RenderPipeline> INTO;
    private static RenderPipeline blendPipeline;
    private static RenderPipeline playerMaskPipeline;
    private static RenderPipeline outlinePipeline;
    private static GpuTexture playerDepthTex;
    private static GpuTextureView playerDepthView;
    private static GpuBuffer quad;
    private static GpuBuffer paramsA;
    private static GpuBuffer paramsB;
    private static GpuBuffer paramsBlend;
    private static GpuBuffer paramsPlayer;
    private static GpuBuffer paramsCrystal;
    private static GpuBuffer paramsPlayerOutline;
    private static GpuBuffer paramsCrystalOutline;
    private static GpuTexture colorCopyTex;
    private static GpuTextureView colorCopyView;
    private static GpuTexture backTex;
    private static GpuTextureView backView;
    private static GpuTexture inter1Tex;
    private static GpuTexture inter2Tex;
    private static GpuTextureView inter1View;
    private static GpuTextureView inter2View;
    private static boolean broken;
    private static final Map<String, Identifier> FRAGMENTS;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private HandShaderGpu() {
    }

    public static void reload() {
        broken = false;
        OVER.clear();
        INTO.clear();
        blendPipeline = null;
        playerMaskPipeline = null;
        outlinePipeline = null;
    }

    
    private static boolean ensure() {
        int n = EffectEngine.a;
        boolean bl = broken;
        if (n != 0) return bl;
        if (bl) {
            return false;
        }
        try {
            block37: {
                if (quad == null) {
                    MemoryStack memoryStack = MemoryStack.stackPush();
                    try {
                        ByteBuffer byteBuffer = memoryStack.malloc(48);
                        byteBuffer.asFloatBuffer().put(new float[]{-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f});
                        if (n != 0) break block37;
                        byteBuffer.rewind();
                        quad = RenderSystem.getDevice().createBuffer(HandShaderGpu::lambda$ensure$0, 32, byteBuffer);
                        if (memoryStack == null) break block37;
                    }
                    catch (Throwable throwable) {
                        if (n != 0) throw throwable;
                        if (memoryStack == null) throw throwable;
                        try {
                            memoryStack.close();
                            throw throwable;
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                    memoryStack.close();
                }
            }
            int n2 = 130;
            GpuBuffer gpuBuffer = paramsA;
            if (n == 0) {
                if (gpuBuffer == null) {
                    paramsA = RenderSystem.getDevice().createBuffer(HandShaderGpu::lambda$ensure$1, n2, HandShaderGpu.a(15389, 5771727496035064586L));
                }
                gpuBuffer = paramsB;
            }
            if (n == 0) {
                if (gpuBuffer == null) {
                    paramsB = RenderSystem.getDevice().createBuffer(HandShaderGpu::lambda$ensure$2, n2, HandShaderGpu.a(31218, 442910944247881444L));
                }
                gpuBuffer = paramsBlend;
            }
            if (n == 0) {
                if (gpuBuffer == null) {
                    paramsBlend = RenderSystem.getDevice().createBuffer(HandShaderGpu::lambda$ensure$3, n2, HandShaderGpu.a(31218, 442910944247881444L));
                }
                gpuBuffer = paramsPlayer;
            }
            if (n == 0) {
                if (gpuBuffer == null) {
                    paramsPlayer = RenderSystem.getDevice().createBuffer(HandShaderGpu::lambda$ensure$4, n2, HandShaderGpu.a(31218, 442910944247881444L));
                }
                gpuBuffer = paramsCrystal;
            }
            if (n == 0) {
                if (gpuBuffer == null) {
                    paramsCrystal = RenderSystem.getDevice().createBuffer(HandShaderGpu::lambda$ensure$5, n2, HandShaderGpu.a(31218, 442910944247881444L));
                }
                gpuBuffer = paramsPlayerOutline;
            }
            if (n == 0) {
                if (gpuBuffer == null) {
                    paramsPlayerOutline = RenderSystem.getDevice().createBuffer(HandShaderGpu::lambda$ensure$6, n2, HandShaderGpu.a(31218, 442910944247881444L));
                }
                gpuBuffer = paramsCrystalOutline;
            }
            if (n == 0) {
                if (gpuBuffer != null) return true;
                gpuBuffer = RenderSystem.getDevice().createBuffer(HandShaderGpu::lambda$ensure$7, n2, HandShaderGpu.a(31218, 442910944247881444L));
            }
            paramsCrystalOutline = gpuBuffer;
            return true;
        }
        catch (Throwable throwable) {
            HandShaderGpu.disable(throwable);
            return false;
        }
    }

    
    private static RenderPipeline pipeline(String string, boolean bl) {
        Identifier identifier;
        RenderPipeline renderPipeline2;
        int n = EffectEngine.a;
        Map<String, RenderPipeline> map = bl ? OVER : INTO;
        RenderPipeline renderPipeline = renderPipeline2 = map.get(string);
        if (n == 0) {
            if (renderPipeline != null) {
                return renderPipeline2;
            }
            renderPipeline = FRAGMENTS.get(string);
        }
        if ((identifier = (Identifier)renderPipeline) == null) {
            return null;
        }
        try {
            String[] stringArray;
            String string2;
            RenderPipeline.Builder builder = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]);
            String string3 = a[59];
            String string4 = string.toLowerCase();
            if (bl) {
                string2 = "";
            } else {
                stringArray = a;
                string2 = stringArray[43];
            }
            String string5 = string2;
            String string6 = string4;
            stringArray = a;
            RenderPipeline.Builder builder2 = builder.withLocation(Identifier.method_60655((String)string3, (String)(stringArray[38] + string6 + string5))).withVertexShader(VERTEX).withFragmentShader(identifier).withUniform(stringArray[6], UniformType.field_60031).withSampler(stringArray[44]).withSampler(stringArray[26]).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withVertexFormat(VertexFormats.field_1592, VertexFormat.DrawMode.field_27382);
            renderPipeline2 = (bl ? builder2.withBlend(BlendFunction.TRANSLUCENT) : builder2.withoutBlend()).build();
            GpuDevice gpuDevice = RenderSystem.getDevice();
            if (n == 0) {
                if (!gpuDevice.precompilePipeline(renderPipeline2, null).isValid()) {
                    stringArray = a;
                    HandShaderGpu.disable(new IllegalStateException(stringArray[12] + string + stringArray[30]));
                    return null;
                }
                gpuDevice = map.put(string, renderPipeline2);
            }
            return renderPipeline2;
        }
        catch (Throwable throwable) {
            HandShaderGpu.disable(throwable);
            return null;
        }
    }

    
    private static RenderPipeline blend() {
        block8: {
            block11: {
                var0 = EffectEngine.a;
                v0 = HandShaderGpu.blendPipeline;
                if (var0 != 0) // goto
                if (v0 == null) break block8;
                break block11;
                catch (Throwable v1) {
                    throw v1;
                }
            }
            return HandShaderGpu.blendPipeline;
        }
        try {
            block9: {
                block10: {
                    var2_1 = HandShaderGpu.a;
                    v0 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(Identifier.method_60655((String)var2_1[7], (String)var2_1[4])).withVertexShader(HandShaderGpu.VERTEX).withFragmentShader(HandShaderGpu.BLEND_FRAG).withUniform(var2_1[5], UniformType.field_60031).withSampler(var2_1[68]).withSampler(var2_1[73]).withSampler(var2_1[22]).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withVertexFormat(VertexFormats.field_1592, VertexFormat.DrawMode.field_27382).build();
lbl18:

                    var1_2 = v0;
                    if (var0 != 0) break block9;
                    if (RenderSystem.getDevice().precompilePipeline(var1_2, null).isValid()) break block10;
                    HandShaderGpu.disable(new IllegalStateException(HandShaderGpu.a[60]));
                    return null;
                }
                HandShaderGpu.blendPipeline = var1_2;
            }
            return var1_2;
        }
        catch (Throwable var1_3) {
            HandShaderGpu.disable(var1_3);
            return null;
        }
    }

    static void disable(Throwable throwable) {
        boolean bl;
        block2: {
            block3: {
                int n = EffectEngine.a;
                bl = broken;
                if (n != 0) break block2;
                if (!bl) break block3;
                return;
            }
            bl = true;
        }
        broken = bl;
        String[] stringArray = a;
        LoggerFactory.getLogger((String)stringArray[7]).error(stringArray[71], throwable);
    }

    
    public static void render(String string, float[] fArray) {
        int n = EffectEngine.a;
        boolean bl = broken;
        if (n == 0) {
            if (bl) return;
            bl = HandShaderGpu.ensure();
        }
        if (!bl) {
            return;
        }
        RenderPipeline renderPipeline = HandShaderGpu.pipeline(string, true);
        if (renderPipeline == null) {
            return;
        }
        Framebuffer framebuffer = HandShaderGpu.mainFramebuffer();
        if (n != 0) return;
        if (framebuffer == null) {
            return;
        }
        try {
            HandShaderGpu.captureColor(framebuffer, framebuffer.field_1482, framebuffer.field_1481);
            HandShaderGpu.drawEffect(framebuffer, renderPipeline, paramsA, fArray, colorCopyView, framebuffer.method_71640());
            return;
        }
        catch (Throwable throwable) {
            HandShaderGpu.disable(throwable);
        }
    }

    
    public static void renderPlayers(String string, float[] fArray, boolean bl, boolean bl2, float[] fArray2, GpuTextureView gpuTextureView, GpuTextureView gpuTextureView2, GpuTextureView gpuTextureView3) {
        block51: {
            Framebuffer framebuffer;
            Framebuffer framebuffer2;
            RenderPipeline renderPipeline;
            RenderPipeline renderPipeline2;
            int n;
            block44: {
                block45: {
                    block56: {
                        block43: {
                            RenderPipeline renderPipeline3;
                            block42: {
                                block55: {
                                    block40: {
                                        GpuTextureView gpuTextureView4;
                                        block41: {
                                            block39: {
                                                block54: {
                                                    block38: {
                                                        boolean bl3;
                                                        block37: {
                                                            n = EffectEngine.a;
                                                            bl3 = broken;
                                                            if (n != 0) break block37;
                                                            if (bl3) break block38;
                                                            bl3 = HandShaderGpu.ensure();
                                                        }
                                                        if (bl3) break block54;
                                                    }
                                                    return;
                                                }
                                                gpuTextureView4 = gpuTextureView;
                                                if (n != 0) break block39;
                                                if (gpuTextureView4 == null) break block40;
                                                gpuTextureView4 = gpuTextureView2;
                                            }
                                            if (n != 0) break block41;
                                            if (gpuTextureView4 == null) break block40;
                                            gpuTextureView4 = gpuTextureView3;
                                        }
                                        if (gpuTextureView4 != null) break block55;
                                    }
                                    return;
                                }
                                renderPipeline2 = HandShaderGpu.pipeline(string, true);
                                renderPipeline = HandShaderGpu.maskPipeline();
                                renderPipeline3 = renderPipeline2;
                                if (n != 0) break block42;
                                if (renderPipeline3 == null) break block43;
                                renderPipeline3 = renderPipeline;
                            }
                            if (renderPipeline3 != null) break block56;
                        }
                        return;
                    }
                    framebuffer2 = HandShaderGpu.mainFramebuffer();
                    framebuffer = framebuffer2;
                    if (n != 0) break block44;
                    if (framebuffer != null) break block45;
                    return;
                }
                framebuffer = framebuffer2;
            }
            int n2 = framebuffer.field_1482;
            int n3 = framebuffer2.field_1481;
            try {
                GpuBuffer gpuBuffer;
                Framebuffer framebuffer3;
                block53: {
                    block52: {
                        block58: {
                            GpuTextureView gpuTextureView5;
                            RenderPass renderPass;
                            block49: {
                                block50: {
                                    block57: {
                                        block47: {
                                            RenderPass renderPass2;
                                            block46: {
                                                HandShaderGpu.captureColor(framebuffer2, n2, n3);
                                                HandShaderGpu.ensurePlayerDepth(n2, n3);
                                                CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                                                renderPass = commandEncoder.createRenderPass(HandShaderGpu::lambda$renderPlayers$8, playerDepthView, OptionalInt.empty());
                                                try {
                                                    renderPass.setPipeline(renderPipeline);
                                                    RenderSystem.bindDefaultUniforms((RenderPass)renderPass);
                                                    String[] stringArray = a;
                                                    renderPass.bindTexture(stringArray[56], gpuTextureView, HandShaderGpu.linear());
                                                    renderPass.bindTexture(stringArray[15], gpuTextureView2, HandShaderGpu.nearest());
                                                    renderPass.bindTexture(stringArray[28], gpuTextureView3, HandShaderGpu.nearest());
                                                    renderPass2 = renderPass;
                                                    if (n != 0) break block46;
                                                    HandShaderGpu.bindQuad(renderPass2);
                                                    renderPass.drawIndexed(0, 0, 6, 1);
                                                    if (renderPass == null) break block47;
                                                    renderPass2 = renderPass;
                                                }
                                                catch (Throwable throwable) {
                                                    block48: {
                                                        if (n != 0 || renderPass == null) break block48;
                                                        try {
                                                            renderPass.close();
                                                        }
                                                        catch (Throwable throwable2) {
                                                            throwable.addSuppressed(throwable2);
                                                        }
                                                    }
                                                    throw throwable;
                                                }
                                            }
                                            renderPass2.close();
                                        }
                                        if (!bl2) break block50;
                                        gpuTextureView5 = backView;
                                        if (n != 0) break block49;
                                        break block57;
                                        catch (Throwable throwable) {
                                            throw throwable;
                                        }
                                    }
                                    if (gpuTextureView5 == null) break block50;
                                    gpuTextureView5 = backView;
                                    break block49;
                                }
                                gpuTextureView5 = colorCopyView;
                            }
                            renderPass = gpuTextureView5;
                            Framebuffer framebuffer4 = framebuffer2;
                            RenderPipeline renderPipeline4 = renderPipeline2;
                            GpuBuffer gpuBuffer2 = bl ? paramsCrystal : paramsPlayer;
                            HandShaderGpu.drawEffect(framebuffer4, renderPipeline4, gpuBuffer2, fArray, (GpuTextureView)renderPass, playerDepthView);
                            if (fArray2 == null) break block51;
                            framebuffer3 = framebuffer2;
                            if (!bl) break block52;
                            break block58;
                            catch (Throwable throwable) {
                                throw throwable;
                            }
                        }
                        gpuBuffer = paramsCrystalOutline;
                        break block53;
                    }
                    gpuBuffer = paramsPlayerOutline;
                }
                HandShaderGpu.drawOutline(framebuffer3, gpuBuffer, fArray2, gpuTextureView, gpuTextureView2);
            }
            catch (Throwable throwable) {
                HandShaderGpu.disable(throwable);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void drawEffect(Framebuffer framebuffer, RenderPipeline renderPipeline, GpuBuffer gpuBuffer, float[] fArray, GpuTextureView gpuTextureView, GpuTextureView gpuTextureView2) {
        HandShaderGpu.uploadParams(gpuBuffer, fArray);
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        int n = EffectEngine.a;
        RenderPass renderPass = commandEncoder.createRenderPass(HandShaderGpu::lambda$drawEffect$9, framebuffer.method_71639(), OptionalInt.empty());
        if (n != 0) return;
        try {
            renderPass.setPipeline(renderPipeline);
            RenderSystem.bindDefaultUniforms((RenderPass)renderPass);
            String[] stringArray = a;
            renderPass.setUniform(stringArray[5], gpuBuffer);
            renderPass.bindTexture(stringArray[2], gpuTextureView, HandShaderGpu.linear());
            renderPass.bindTexture(stringArray[22], gpuTextureView2, HandShaderGpu.nearest());
            HandShaderGpu.bindQuad(renderPass);
            renderPass.drawIndexed(0, 0, 6, 1);
            if (renderPass == null) return;
        }
        catch (Throwable throwable) {
            if (n != 0 || renderPass == null) throw throwable;
            try {
                renderPass.close();
                throw throwable;
            }
            catch (Throwable throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
        renderPass.close();
    }

    
    private static void drawOutline(Framebuffer framebuffer, GpuBuffer gpuBuffer, float[] fArray, GpuTextureView gpuTextureView, GpuTextureView gpuTextureView2) {
        RenderPass renderPass;
        int n = EffectEngine.a;
        RenderPipeline renderPipeline = HandShaderGpu.outlinePipeline();
        if (n == 0) {
            if (renderPipeline == null) {
                return;
            }
            HandShaderGpu.uploadParams(gpuBuffer, new float[]{framebuffer.field_1482, framebuffer.field_1481, fArray[0], 0.0f, fArray[1], fArray[2], fArray[3], fArray[4]});
        }
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        RenderPass renderPass2 = commandEncoder.createRenderPass(HandShaderGpu::lambda$drawOutline$10, framebuffer.method_71639(), OptionalInt.empty());
        try {
            renderPass2.setPipeline(renderPipeline);
            RenderSystem.bindDefaultUniforms((RenderPass)renderPass2);
            String[] stringArray = a;
            renderPass2.setUniform(stringArray[5], gpuBuffer);
            renderPass2.bindTexture(stringArray[32], playerDepthView, HandShaderGpu.nearest());
            renderPass = renderPass2;
            if (n == 0) {
                renderPass.bindTexture(stringArray[9], gpuTextureView, HandShaderGpu.nearest());
                renderPass2.bindTexture(stringArray[24], gpuTextureView2, HandShaderGpu.nearest());
                HandShaderGpu.bindQuad(renderPass2);
                renderPass2.drawIndexed(0, 0, 6, 1);
                if (renderPass2 == null) return;
                renderPass = renderPass2;
            }
        }
        catch (Throwable throwable) {
            if (n != 0) throw throwable;
            if (renderPass2 == null) throw throwable;
            try {
                renderPass2.close();
                throw throwable;
            }
            catch (Throwable throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
        renderPass.close();
    }

    
    private static RenderPipeline outlinePipeline() {
        block8: {
            block11: {
                var0 = EffectEngine.a;
                v0 = HandShaderGpu.outlinePipeline;
                if (var0 != 0) // goto
                if (v0 == null) break block8;
                break block11;
                catch (Throwable v1) {
                    throw v1;
                }
            }
            return HandShaderGpu.outlinePipeline;
        }
        try {
            block9: {
                block10: {
                    var2_1 = HandShaderGpu.a;
                    v0 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(Identifier.method_60655((String)var2_1[7], (String)var2_1[72])).withVertexShader(HandShaderGpu.VERTEX).withFragmentShader(Identifier.method_60655((String)var2_1[7], (String)var2_1[49])).withUniform(var2_1[5], UniformType.field_60031).withSampler(var2_1[42]).withSampler(var2_1[1]).withSampler(var2_1[65]).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withVertexFormat(VertexFormats.field_1592, VertexFormat.DrawMode.field_27382).build();
lbl18:

                    var1_2 = v0;
                    if (var0 != 0) break block9;
                    if (RenderSystem.getDevice().precompilePipeline(var1_2, null).isValid()) break block10;
                    HandShaderGpu.disable(new IllegalStateException(HandShaderGpu.a[0]));
                    return null;
                }
                HandShaderGpu.outlinePipeline = var1_2;
            }
            return var1_2;
        }
        catch (Throwable var1_3) {
            HandShaderGpu.disable(var1_3);
            return null;
        }
    }

    
    private static RenderPipeline maskPipeline() {
        block8: {
            block11: {
                var0 = EffectEngine.a;
                v0 = HandShaderGpu.playerMaskPipeline;
                if (var0 != 0) // goto
                if (v0 == null) break block8;
                break block11;
                catch (Throwable v1) {
                    throw v1;
                }
            }
            return HandShaderGpu.playerMaskPipeline;
        }
        try {
            block9: {
                block10: {
                    var2_1 = HandShaderGpu.a;
                    v0 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(Identifier.method_60655((String)var2_1[7], (String)var2_1[23])).withVertexShader(HandShaderGpu.VERTEX).withFragmentShader(Identifier.method_60655((String)var2_1[7], (String)var2_1[39])).withSampler(var2_1[34]).withSampler(var2_1[13]).withSampler(var2_1[31]).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withVertexFormat(VertexFormats.field_1592, VertexFormat.DrawMode.field_27382).withoutBlend().build();
lbl18:

                    var1_2 = v0;
                    if (var0 != 0) break block9;
                    if (RenderSystem.getDevice().precompilePipeline(var1_2, null).isValid()) break block10;
                    HandShaderGpu.disable(new IllegalStateException(HandShaderGpu.a[40]));
                    return null;
                }
                HandShaderGpu.playerMaskPipeline = var1_2;
            }
            return var1_2;
        }
        catch (Throwable var1_3) {
            HandShaderGpu.disable(var1_3);
            return null;
        }
    }

    private static void ensurePlayerDepth(int n, int n2) {
        block19: {
            GpuTexture gpuTexture;
            block18: {
                int n3;
                block17: {
                    GpuTextureView gpuTextureView;
                    block16: {
                        block14: {
                            int n4;
                            GpuTexture gpuTexture2;
                            block15: {
                                block13: {
                                    n3 = EffectEngine.a;
                                    gpuTexture2 = playerDepthTex;
                                    if (n3 != 0) break block13;
                                    if (gpuTexture2 == null) break block14;
                                    gpuTexture2 = playerDepthTex;
                                }
                                n4 = 0;
                                if (n3 != 0) break block15;
                                if (gpuTexture2.getWidth(n4) != n) break block14;
                                gpuTexture2 = playerDepthTex;
                                n4 = 0;
                            }
                            if (gpuTexture2.getHeight(n4) == n2) {
                                return;
                            }
                        }
                        gpuTextureView = playerDepthView;
                        if (n3 != 0) break block16;
                        if (gpuTextureView == null) break block17;
                        gpuTextureView = playerDepthView;
                    }
                    gpuTextureView.close();
                }
                gpuTexture = playerDepthTex;
                if (n3 != 0) break block18;
                if (gpuTexture == null) break block19;
                gpuTexture = playerDepthTex;
            }
            gpuTexture.close();
        }
        int n5 = 12;
        playerDepthTex = RenderSystem.getDevice().createTexture(a[52], n5, TextureFormat.RGBA8, n, n2, 1, 1);
        playerDepthView = RenderSystem.getDevice().createTextureView(playerDepthTex);
    }

    public static void renderDual(String string, float[] fArray, String string2, float[] fArray2, float[] fArray3) {
        block32: {
            Framebuffer framebuffer;
            Framebuffer framebuffer2;
            RenderPipeline renderPipeline;
            int n;
            block27: {
                block28: {
                    block34: {
                        block26: {
                            boolean bl;
                            block25: {
                                n = EffectEngine.a;
                                bl = broken;
                                if (n != 0) break block25;
                                if (bl) break block26;
                                bl = HandShaderGpu.ensure();
                            }
                            if (bl) break block34;
                        }
                        return;
                    }
                    renderPipeline = HandShaderGpu.blend();
                    if (renderPipeline == null) {
                        return;
                    }
                    framebuffer2 = HandShaderGpu.mainFramebuffer();
                    framebuffer = framebuffer2;
                    if (n != 0) break block27;
                    if (framebuffer != null) break block28;
                    return;
                }
                framebuffer = framebuffer2;
            }
            int n2 = framebuffer.field_1482;
            int n3 = framebuffer2.field_1481;
            try {
                RenderPass renderPass;
                block31: {
                    GpuTextureView gpuTextureView;
                    GpuTextureView gpuTextureView2;
                    GpuTextureView gpuTextureView3;
                    block35: {
                        block30: {
                            GpuTextureView gpuTextureView4;
                            block29: {
                                HandShaderGpu.captureColor(framebuffer2, n2, n3);
                                HandShaderGpu.ensureIntermediates(n2, n3);
                                gpuTextureView3 = framebuffer2.method_71640();
                                gpuTextureView2 = HandShaderGpu.renderInto(string, fArray, paramsA, inter1Tex, inter1View, gpuTextureView3);
                                gpuTextureView = HandShaderGpu.renderInto(string2, fArray2, paramsB, inter2Tex, inter2View, gpuTextureView3);
                                gpuTextureView4 = gpuTextureView2;
                                if (n != 0) break block29;
                                if (gpuTextureView4 == null) break block30;
                                gpuTextureView4 = gpuTextureView;
                            }
                            if (gpuTextureView4 != null) break block35;
                        }
                        return;
                    }
                    HandShaderGpu.uploadParams(paramsBlend, fArray3);
                    CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                    RenderPass renderPass2 = commandEncoder.createRenderPass(HandShaderGpu::lambda$renderDual$11, framebuffer2.method_71639(), OptionalInt.empty());
                    try {
                        renderPass2.setPipeline(renderPipeline);
                        RenderSystem.bindDefaultUniforms((RenderPass)renderPass2);
                        String[] stringArray = a;
                        renderPass2.setUniform(stringArray[5], paramsBlend);
                        renderPass2.bindTexture(stringArray[53], gpuTextureView2, HandShaderGpu.linear());
                        renderPass = renderPass2;
                        if (n != 0) break block31;
                        renderPass.bindTexture(stringArray[58], gpuTextureView, HandShaderGpu.linear());
                        renderPass2.bindTexture(stringArray[22], gpuTextureView3, HandShaderGpu.nearest());
                        HandShaderGpu.bindQuad(renderPass2);
                        renderPass2.drawIndexed(0, 0, 6, 1);
                        if (renderPass2 == null) break block32;
                        renderPass = renderPass2;
                    }
                    catch (Throwable throwable) {
                        block33: {
                            if (n != 0 || renderPass2 == null) break block33;
                            try {
                                renderPass2.close();
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        }
                        throw throwable;
                    }
                }
                renderPass.close();
            }
            catch (Throwable throwable) {
                HandShaderGpu.disable(throwable);
            }
        }
    }

    
    private static GpuTextureView renderInto(String string, float[] fArray, GpuBuffer gpuBuffer, GpuTexture gpuTexture, GpuTextureView gpuTextureView, GpuTextureView gpuTextureView2) {
        int n = EffectEngine.a;
        if (string == null) return colorCopyView;
        if (!FRAGMENTS.containsKey(string)) {
            return colorCopyView;
        }
        RenderPipeline renderPipeline = HandShaderGpu.pipeline(string, false);
        if (n == 0) {
            if (renderPipeline == null) {
                return null;
            }
            HandShaderGpu.uploadParams(gpuBuffer, fArray);
        }
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        RenderPass renderPass = commandEncoder.createRenderPass(HandShaderGpu::lambda$renderInto$12, gpuTextureView, OptionalInt.of(0));
        if (n != 0) return gpuTextureView;
        try {
            renderPass.setPipeline(renderPipeline);
            RenderSystem.bindDefaultUniforms((RenderPass)renderPass);
            String[] stringArray = a;
            renderPass.setUniform(stringArray[5], gpuBuffer);
            renderPass.bindTexture(stringArray[2], colorCopyView, HandShaderGpu.linear());
            renderPass.bindTexture(stringArray[22], gpuTextureView2, HandShaderGpu.nearest());
            HandShaderGpu.bindQuad(renderPass);
            renderPass.drawIndexed(0, 0, 6, 1);
            if (renderPass == null) return gpuTextureView;
        }
        catch (Throwable throwable) {
            if (n != 0) throw throwable;
            if (renderPass == null) throw throwable;
            try {
                renderPass.close();
                throw throwable;
            }
            catch (Throwable throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
        renderPass.close();
        return gpuTextureView;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Framebuffer mainFramebuffer() {
        Framebuffer framebuffer;
        Framebuffer framebuffer2;
        int n;
        block24: {
            block22: {
                block23: {
                    block21: {
                        Framebuffer framebuffer3;
                        block20: {
                            MinecraftClient minecraftClient;
                            block18: {
                                MinecraftClient minecraftClient2;
                                block19: {
                                    minecraftClient2 = MinecraftClient.method_1551();
                                    n = EffectEngine.a;
                                    minecraftClient = minecraftClient2;
                                    if (n != 0) break block18;
                                    if (minecraftClient != null) break block19;
                                    framebuffer3 = null;
                                    break block20;
                                }
                                minecraftClient = minecraftClient2;
                            }
                            framebuffer3 = minecraftClient.method_1522();
                        }
                        framebuffer2 = framebuffer3;
                        framebuffer = framebuffer2;
                        if (n != 0) break block21;
                        if (framebuffer == null) return null;
                        framebuffer = framebuffer2;
                    }
                    if (n != 0) break block22;
                    if (framebuffer.field_1478) break block23;
                    return null;
                }
                framebuffer = framebuffer2;
            }
            if (n != 0) break block24;
            if (framebuffer.field_1482 <= 0) return null;
            framebuffer = framebuffer2;
        }
        if (n != 0) return framebuffer;
        if (framebuffer.field_1481 <= 0) return null;
        framebuffer = framebuffer2;
        return framebuffer;
    }

    private static void bindQuad(RenderPass renderPass) {
        RenderSystem.ShapeIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer((VertexFormat.DrawMode)VertexFormat.DrawMode.field_27382);
        renderPass.setVertexBuffer(0, quad);
        renderPass.setIndexBuffer(shapeIndexBuffer.method_68274(6), shapeIndexBuffer.method_31924());
    }

    private static GpuSampler linear() {
        return RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
    }

    private static GpuSampler nearest() {
        return RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
    }

    
    private static void captureColor(Framebuffer framebuffer, int n, int n2) {
        block13: {
            block17: {
                GpuTexture gpuTexture;
                block16: {
                    int n3;
                    block15: {
                        GpuTextureView gpuTextureView;
                        block14: {
                            block11: {
                                int n4;
                                GpuTexture gpuTexture2;
                                block12: {
                                    block10: {
                                        n3 = EffectEngine.a;
                                        gpuTexture2 = colorCopyTex;
                                        if (n3 != 0) break block10;
                                        if (gpuTexture2 == null) break block11;
                                        gpuTexture2 = colorCopyTex;
                                    }
                                    n4 = 0;
                                    if (n3 != 0) break block12;
                                    if (gpuTexture2.getWidth(n4) != n) break block11;
                                    gpuTexture2 = colorCopyTex;
                                    n4 = 0;
                                }
                                if (gpuTexture2.getHeight(n4) == n2) break block13;
                            }
                            gpuTextureView = colorCopyView;
                            if (n3 != 0) break block14;
                            if (gpuTextureView == null) break block15;
                            gpuTextureView = colorCopyView;
                        }
                        gpuTextureView.close();
                    }
                    gpuTexture = colorCopyTex;
                    if (n3 != 0) break block16;
                    if (gpuTexture == null) break block17;
                    gpuTexture = colorCopyTex;
                }
                gpuTexture.close();
            }
            int n5 = 5;
            colorCopyTex = RenderSystem.getDevice().createTexture(a[74], n5, TextureFormat.RGBA8, n, n2, 1, 1);
            colorCopyView = RenderSystem.getDevice().createTextureView(colorCopyTex);
        }
        RenderSystem.getDevice().createCommandEncoder().copyTextureToTexture(framebuffer.method_30277(), colorCopyTex, 0, 0, 0, 0, 0, n, n2);
    }

    
    public static void captureBackground() {
        Framebuffer framebuffer;
        int n = EffectEngine.a;
        if (broken) {
            return;
        }
        Framebuffer framebuffer2 = framebuffer = HandShaderGpu.mainFramebuffer();
        if (n == 0) {
            if (framebuffer2 == null) {
                return;
            }
            framebuffer2 = framebuffer;
        }
        int n2 = framebuffer2.field_1482;
        int n3 = framebuffer.field_1481;
        try {
            block22: {
                block26: {
                    GpuTexture gpuTexture;
                    block25: {
                        block24: {
                            GpuTextureView gpuTextureView;
                            block23: {
                                block20: {
                                    GpuTexture gpuTexture2;
                                    block21: {
                                        block19: {
                                            gpuTexture2 = backTex;
                                            if (n != 0) break block19;
                                            if (gpuTexture2 == null) break block20;
                                            gpuTexture2 = backTex;
                                        }
                                        if (n != 0) break block21;
                                        if (gpuTexture2.getWidth(0) != n2) break block20;
                                        gpuTexture2 = backTex;
                                    }
                                    if (gpuTexture2.getHeight(0) == n3) break block22;
                                }
                                gpuTextureView = backView;
                                if (n != 0) break block23;
                                if (gpuTextureView == null) break block24;
                                gpuTextureView = backView;
                            }
                            gpuTextureView.close();
                        }
                        gpuTexture = backTex;
                        if (n != 0) break block25;
                        if (gpuTexture == null) break block26;
                        gpuTexture = backTex;
                    }
                    gpuTexture.close();
                }
                int n4 = 5;
                backTex = RenderSystem.getDevice().createTexture(a[27], n4, TextureFormat.RGBA8, n2, n3, 1, 1);
                backView = RenderSystem.getDevice().createTextureView(backTex);
            }
            RenderSystem.getDevice().createCommandEncoder().copyTextureToTexture(framebuffer.method_30277(), backTex, 0, 0, 0, 0, 0, n2, n3);
            return;
        }
        catch (Throwable throwable) {
            HandShaderGpu.disable(throwable);
        }
    }

    private static void ensureIntermediates(int n, int n2) {
        block27: {
            GpuTexture gpuTexture;
            block26: {
                int n3;
                block25: {
                    GpuTextureView gpuTextureView;
                    block24: {
                        block23: {
                            GpuTexture gpuTexture2;
                            block22: {
                                block21: {
                                    GpuTextureView gpuTextureView2;
                                    block20: {
                                        block18: {
                                            int n4;
                                            block19: {
                                                GpuTexture gpuTexture3;
                                                block17: {
                                                    n3 = EffectEngine.a;
                                                    gpuTexture3 = inter1Tex;
                                                    if (n3 != 0) break block17;
                                                    if (gpuTexture3 == null) break block18;
                                                    gpuTexture3 = inter1Tex;
                                                }
                                                n4 = gpuTexture3.getWidth(0);
                                                if (n3 != 0) break block19;
                                                if (n4 != n) break block18;
                                                n4 = inter1Tex.getHeight(0);
                                            }
                                            if (n4 == n2) {
                                                return;
                                            }
                                        }
                                        gpuTextureView2 = inter1View;
                                        if (n3 != 0) break block20;
                                        if (gpuTextureView2 == null) break block21;
                                        gpuTextureView2 = inter1View;
                                    }
                                    gpuTextureView2.close();
                                }
                                gpuTexture2 = inter1Tex;
                                if (n3 != 0) break block22;
                                if (gpuTexture2 == null) break block23;
                                gpuTexture2 = inter1Tex;
                            }
                            gpuTexture2.close();
                        }
                        gpuTextureView = inter2View;
                        if (n3 != 0) break block24;
                        if (gpuTextureView == null) break block25;
                        gpuTextureView = inter2View;
                    }
                    gpuTextureView.close();
                }
                gpuTexture = inter2Tex;
                if (n3 != 0) break block26;
                if (gpuTexture == null) break block27;
                gpuTexture = inter2Tex;
            }
            gpuTexture.close();
        }
        int n5 = 12;
        String[] stringArray = a;
        inter1Tex = RenderSystem.getDevice().createTexture(stringArray[20], n5, TextureFormat.RGBA8, n, n2, 1, 1);
        inter1View = RenderSystem.getDevice().createTextureView(inter1Tex);
        inter2Tex = RenderSystem.getDevice().createTexture(stringArray[48], n5, TextureFormat.RGBA8, n, n2, 1, 1);
        inter2View = RenderSystem.getDevice().createTextureView(inter2Tex);
    }

    
    private static void uploadParams(GpuBuffer gpuBuffer, float[] fArray) {
        GpuBuffer.MappedView mappedView;
        block8: {
            GpuBuffer.MappedView mappedView2 = RenderSystem.getDevice().createCommandEncoder().mapBuffer(gpuBuffer, false, true);
            int n = EffectEngine.a;
            try {
                Std140Builder std140Builder = Std140Builder.intoBuffer((ByteBuffer)mappedView2.data());
                for (int i = 0; i < 8; ++i) {
                    int n2 = i * 4;
                    std140Builder.putVec4(HandShaderGpu.at(fArray, n2), HandShaderGpu.at(fArray, n2 + 1), HandShaderGpu.at(fArray, n2 + 2), HandShaderGpu.at(fArray, n2 + 3));
                    if (n != 0) return;
                    if (n == 0) continue;
                }
                mappedView = mappedView2;
                if (n != 0) break block8;
            }
            catch (Throwable throwable) {
                if (n != 0) throw throwable;
                if (mappedView2 == null) throw throwable;
                try {
                    mappedView2.close();
                    throw throwable;
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            if (mappedView == null) return;
            mappedView = mappedView2;
        }
        mappedView.close();
    }

    private static float at(float[] fArray, int n) {
        float f;
        block5: {
            block4: {
                if (fArray == null || n >= fArray.length) break block4;
                f = fArray[n];
                break block5;
            }
            f = 0.0f;
        }
        return f;
    }

    private static String lambda$renderInto$12() {
        return a[66];
    }

    private static String lambda$renderDual$11() {
        return a[62];
    }

    private static String lambda$drawOutline$10() {
        return a[25];
    }

    private static String lambda$drawEffect$9() {
        return a[18];
    }

    private static String lambda$renderPlayers$8() {
        return a[17];
    }

    private static String lambda$ensure$7() {
        return a[35];
    }

    private static String lambda$ensure$6() {
        return a[19];
    }

    private static String lambda$ensure$5() {
        return a[16];
    }

    private static String lambda$ensure$4() {
        return a[11];
    }

    private static String lambda$ensure$3() {
        return a[29];
    }

    private static String lambda$ensure$2() {
        return a[14];
    }

    private static String lambda$ensure$1() {
        return a[47];
    }

    private static String lambda$ensure$0() {
        return a[10];
    }

    /*
     * Exception decompiling
     */
    static {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: CONTINUE without a while class org.benf.cfr.reader.bytecode.analysis.parse.statement.AssignmentSimple
         *     at org.benf.cfr.reader.bytecode.analysis.parse.statement.GotoStatement.getTargetStartBlock(GotoStatement.java:102)
         *     at org.benf.cfr.reader.bytecode.analysis.parse.statement.GotoStatement.getStructuredStatement(GotoStatement.java:116)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.getStructuredStatementPlaceHolder(Op03SimpleStatement.java:550)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:727)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:845)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1042)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:929)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:73)
         *     at org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x6716) & Short.MAX_VALUE;
        if (c[n2] == null) {
            HandShaderGpu.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
