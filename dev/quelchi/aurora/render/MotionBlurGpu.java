package dev.quelchi.aurora.render;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.quelchi.aurora.render.EffectEngine;
import java.lang.invoke.LambdaMetafactory;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import org.joml.Vector4fc;
import org.lwjgl.system.MemoryStack;
import org.slf4j.LoggerFactory;

public final class MotionBlurGpu {
    private static final Identifier VERTEX;
    private static final Identifier CAMERA_FRAG;
    private static final Identifier BLEND_FRAG;
    private static final int CAMERA_VECS;
    private static final double CUT_DISTANCE = 4.0;
    private static final float MAX_LENGTH = 0.06f;
    private static boolean broken;
    private static GpuBuffer quad;
    private static GpuBuffer cameraParams;
    private static GpuBuffer blendParams;
    private static RenderPipeline cameraPipeline;
    private static RenderPipeline blendPipeline;
    private static GpuTexture colorCopyTex;
    private static GpuTexture historyTex;
    private static GpuTextureView colorCopyView;
    private static GpuTextureView historyView;
    private static boolean historyValid;
    private static Matrix4f prevViewProj;
    private static Vec3d prevCam;
    private static long lastNanos;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private MotionBlurGpu() {
    }

    public static void reload() {
        broken = false;
        cameraPipeline = null;
        blendPipeline = null;
        historyValid = false;
    }

    /*
     * Exception decompiling
     */
    public static void afterWorld(Matrix4f var0, Matrix4f var1_1, Vec3d var2_2) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [20[CASE]], but top level block is 17[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
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

    
    private static void camera(Matrix4f var0, Vec3d var1_1, float var2_2, float var3_3) {
        block33: {
            block30: {
                block35: {
                    var5_4 = MotionBlurGpu.mainFramebuffer();
                    var4_5 = EffectEngine.a;
                    if (var5_4 == null) // goto
                    if (MotionBlurGpu.ensureBuffers()) break block30;
                    break block35;
                    catch (Throwable v0) {
                        throw v0;
                    }
                }
                return;
            }
            var6_6 = MotionBlurGpu.cameraPipeline();
            if (var6_6 == null) {
                return;
            }
            var7_7 = var5_4.field_1482;
            var8_8 = var5_4.field_1481;
            MotionBlurGpu.copyColor(var5_4, var7_7, var8_8);
            var9_9 = var3_3 / var2_2;
            var10_10 = new Matrix4f((Matrix4fc)var0).invert();
            var11_11 = new Vector4f();
            var12_12 = RenderSystem.getDevice().createCommandEncoder().mapBuffer(MotionBlurGpu.cameraParams, false, true);
            try {
                block31: {
                    var13_13 = Std140Builder.intoBuffer((ByteBuffer)var12_12.data());
                    var14_16 = 0;
                    while (var14_16 < 4) {
                        v3 = var13_13;
                        v4 = var10_10;
                        v5 = var14_16++;
                        if (var4_5 == 0) {
                            v3.putVec4((Vector4fc)v4.getColumn(v5, var11_11));
                            if (var4_5 == 0) continue;
                            break;
                        }
                    }
                    for (var14_16 = 0; var14_16 < 4; ++var14_16) {
                        v3 = var13_13;
                        if (var4_5 != 0) break block31;
                        v4 = MotionBlurGpu.prevViewProj;
                        v5 = var14_16;
lbl54:

                        v3.putVec4((Vector4fc)v4.getColumn(v5, var11_11));
                        if (var4_5 == 0) continue;
                    }
                    var13_13.putVec4((float)(var1_1.field_1352 - MotionBlurGpu.prevCam.field_1352), (float)(var1_1.field_1351 - MotionBlurGpu.prevCam.field_1351), (float)(var1_1.field_1350 - MotionBlurGpu.prevCam.field_1350), var9_9);
                    v3 = var13_13.putVec4(0.06f, 12.0f, 0.0f, 0.0f);
                }
                if (var4_5 != 0) goto lbl_neg_1000
            }
            catch (Throwable var13_14) {
                block32: {
                    if (var4_5 != 0 || var12_12 == null) break block32;
                    try {
                        var12_12.close();
                    }
                    catch (Throwable var14_17) {
                        var13_14.addSuppressed(var14_17);
                    }
                }
                throw var13_14;
            }
lbl_neg_1000:

            {
                if (var12_12 == null) // goto
                var12_12.close();
            }
lbl_neg_1000:

            {
            }
lbl82:

            var12_12 = RenderSystem.getDevice().createCommandEncoder().createRenderPass((Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$camera$0(), ()Ljava/lang/String;)(), var5_4.method_71639(), OptionalInt.empty());
            try {
                var12_12.setPipeline(var6_6);
                RenderSystem.bindDefaultUniforms((RenderPass)var12_12);
                var15_19 = MotionBlurGpu.a;
                var12_12.setUniform(var15_19[0], MotionBlurGpu.cameraParams);
                var12_12.bindTexture(var15_19[13], MotionBlurGpu.colorCopyView, MotionBlurGpu.sampler(FilterMode.LINEAR));
                if (var4_5 != 0) break block33;
                var12_12.bindTexture(var15_19[17], var5_4.method_71640(), MotionBlurGpu.sampler(FilterMode.NEAREST));
                MotionBlurGpu.bindQuad((RenderPass)var12_12);
                var12_12.drawIndexed(0, 0, 6, 1);
                if (var12_12 == null) goto lbl_neg_1000
            }
            catch (Throwable var13_15) {
                block34: {
                    if (var4_5 != 0 || var12_12 == null) break block34;
                    try {
                        var12_12.close();
                    }
                    catch (Throwable var14_18) {
                        var13_15.addSuppressed(var14_18);
                    }
                }
                throw var13_15;
            }
lbl_neg_1000:

            {
                var12_12.close();
            }
lbl_neg_1000:

            {
            }
        }
    }

    
    private static void frameBlend(float f, float f2) {
        CommandEncoder commandEncoder;
        int n;
        int n2;
        Framebuffer framebuffer;
        block23: {
            GpuBuffer.MappedView mappedView;
            RenderPipeline renderPipeline;
            int n3;
            block22: {
                GpuBuffer.MappedView mappedView2;
                block21: {
                    framebuffer = MotionBlurGpu.mainFramebuffer();
                    n3 = EffectEngine.a;
                    if (framebuffer == null) return;
                    int n4 = MotionBlurGpu.ensureBuffers();
                    if (n3 == 0) {
                        if (n4 == 0) {
                            return;
                        }
                        n4 = framebuffer.field_1482;
                    }
                    n2 = n4;
                    n = framebuffer.field_1481;
                    MotionBlurGpu.ensureHistory(n2, n);
                    commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                    if (n3 != 0) return;
                    if (!historyValid) {
                        commandEncoder.copyTextureToTexture(framebuffer.method_30277(), historyTex, 0, 0, 0, 0, 0, n2, n);
                        historyValid = true;
                        return;
                    }
                    renderPipeline = MotionBlurGpu.blendPipeline();
                    if (n3 == 0) {
                        if (renderPipeline == null) {
                            return;
                        }
                        MotionBlurGpu.copyColor(framebuffer, n2, n);
                    }
                    float f3 = (float)Math.exp(-f / Math.max(5.0E-4f, f2));
                    mappedView = commandEncoder.mapBuffer(blendParams, false, true);
                    try {
                        mappedView2 = mappedView;
                        if (n3 != 0) break block21;
                        Std140Builder.intoBuffer((ByteBuffer)mappedView2.data()).putVec4(f3, 0.0f, 0.0f, 0.0f);
                        if (mappedView == null) break block22;
                        mappedView2 = mappedView;
                    }
                    catch (Throwable throwable) {
                        if (n3 != 0) throw throwable;
                        if (mappedView == null) throw throwable;
                        try {
                            mappedView.close();
                            throw throwable;
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                }
                mappedView2.close();
            }
            mappedView = commandEncoder.createRenderPass(MotionBlurGpu::lambda$frameBlend$1, framebuffer.method_71639(), OptionalInt.empty());
            try {
                mappedView.setPipeline(renderPipeline);
                RenderSystem.bindDefaultUniforms((RenderPass)mappedView);
                String[] stringArray = a;
                mappedView.setUniform(stringArray[14], blendParams);
                if (n3 != 0) return;
                mappedView.bindTexture(stringArray[23], colorCopyView, MotionBlurGpu.sampler(FilterMode.NEAREST));
                mappedView.bindTexture(stringArray[16], historyView, MotionBlurGpu.sampler(FilterMode.NEAREST));
                MotionBlurGpu.bindQuad((RenderPass)mappedView);
                mappedView.drawIndexed(0, 0, 6, 1);
                if (mappedView == null) break block23;
            }
            catch (Throwable throwable) {
                if (n3 != 0) throw throwable;
                if (mappedView == null) throw throwable;
                try {
                    mappedView.close();
                    throw throwable;
                }
                catch (Throwable throwable3) {
                    throwable.addSuppressed(throwable3);
                }
                throw throwable;
            }
            mappedView.close();
        }
        commandEncoder.copyTextureToTexture(framebuffer.method_30277(), historyTex, 0, 0, 0, 0, 0, n2, n);
    }

    
    private static boolean ensureBuffers() {
        int n;
        block15: {
            n = EffectEngine.a;
            boolean bl = broken;
            if (n != 0) return bl;
            if (bl) {
                return false;
            }
            if (quad == null) {
                MemoryStack memoryStack = MemoryStack.stackPush();
                try {
                    ByteBuffer byteBuffer = memoryStack.malloc(48);
                    byteBuffer.asFloatBuffer().put(new float[]{-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f});
                    if (n != 0) break block15;
                    byteBuffer.rewind();
                    quad = RenderSystem.getDevice().createBuffer(MotionBlurGpu::lambda$ensureBuffers$2, 32, byteBuffer);
                    if (memoryStack == null) break block15;
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
        GpuBuffer gpuBuffer = cameraParams;
        if (n == 0) {
            if (gpuBuffer == null) {
                cameraParams = RenderSystem.getDevice().createBuffer(MotionBlurGpu::lambda$ensureBuffers$3, n2, MotionBlurGpu.a(11745, 4162541974128668315L));
            }
            gpuBuffer = blendParams;
        }
        if (n == 0) {
            if (gpuBuffer != null) return true;
            gpuBuffer = RenderSystem.getDevice().createBuffer(MotionBlurGpu::lambda$ensureBuffers$4, n2, MotionBlurGpu.a(939, 5815719750291947728L));
        }
        blendParams = gpuBuffer;
        return true;
    }

    private static RenderPipeline cameraPipeline() {
        RenderPipeline renderPipeline;
        block4: {
            block5: {
                int n = EffectEngine.a;
                renderPipeline = cameraPipeline;
                if (n != 0) break block4;
                if (renderPipeline != null) break block5;
                cameraPipeline = MotionBlurGpu.build(a[2], CAMERA_FRAG, a[4]);
            }
            renderPipeline = cameraPipeline;
        }
        return renderPipeline;
    }

    private static RenderPipeline blendPipeline() {
        RenderPipeline renderPipeline;
        block4: {
            block5: {
                int n = EffectEngine.a;
                renderPipeline = blendPipeline;
                if (n != 0) break block4;
                if (renderPipeline != null) break block5;
                blendPipeline = MotionBlurGpu.build(a[21], BLEND_FRAG, a[11]);
            }
            renderPipeline = blendPipeline;
        }
        return renderPipeline;
    }

    private static RenderPipeline build(String string, Identifier identifier, String string2) {
        RenderPipeline renderPipeline;
        block5: {
            block4: {
                int n = EffectEngine.a;
                String[] stringArray = a;
                renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(Identifier.method_60655((String)stringArray[10], (String)(stringArray[18] + string))).withVertexShader(VERTEX).withFragmentShader(identifier).withUniform(stringArray[0], UniformType.field_60031).withSampler(stringArray[13]).withSampler(string2).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withVertexFormat(VertexFormats.field_1592, VertexFormat.DrawMode.field_27382).withoutBlend().build();
                if (n != 0) break block4;
                if (RenderSystem.getDevice().precompilePipeline(renderPipeline, null).isValid()) break block5;
                MotionBlurGpu.disable(new IllegalStateException(string + a[7]));
            }
            return null;
        }
        return renderPipeline;
    }

    
    private static void copyColor(Framebuffer framebuffer, int n, int n2) {
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
                                block12: {
                                    GpuTexture gpuTexture2;
                                    block10: {
                                        n3 = EffectEngine.a;
                                        gpuTexture2 = colorCopyTex;
                                        if (n3 != 0) break block10;
                                        if (gpuTexture2 == null) break block11;
                                        gpuTexture2 = colorCopyTex;
                                    }
                                    n4 = gpuTexture2.getWidth(0);
                                    if (n3 != 0) break block12;
                                    if (n4 != n) break block11;
                                    n4 = colorCopyTex.getHeight(0);
                                }
                                if (n4 == n2) break block13;
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
            colorCopyTex = RenderSystem.getDevice().createTexture(a[12], n5, TextureFormat.RGBA8, n, n2, 1, 1);
            colorCopyView = RenderSystem.getDevice().createTextureView(colorCopyTex);
        }
        RenderSystem.getDevice().createCommandEncoder().copyTextureToTexture(framebuffer.method_30277(), colorCopyTex, 0, 0, 0, 0, 0, n, n2);
    }

    private static void ensureHistory(int n, int n2) {
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
                                    gpuTexture2 = historyTex;
                                    if (n3 != 0) break block13;
                                    if (gpuTexture2 == null) break block14;
                                    gpuTexture2 = historyTex;
                                }
                                n4 = 0;
                                if (n3 != 0) break block15;
                                if (gpuTexture2.getWidth(n4) != n) break block14;
                                gpuTexture2 = historyTex;
                                n4 = 0;
                            }
                            if (gpuTexture2.getHeight(n4) == n2) {
                                return;
                            }
                        }
                        gpuTextureView = historyView;
                        if (n3 != 0) break block16;
                        if (gpuTextureView == null) break block17;
                        gpuTextureView = historyView;
                    }
                    gpuTextureView.close();
                }
                gpuTexture = historyTex;
                if (n3 != 0) break block18;
                if (gpuTexture == null) break block19;
                gpuTexture = historyTex;
            }
            gpuTexture.close();
        }
        int n5 = 5;
        historyTex = RenderSystem.getDevice().createTexture(a[9], n5, TextureFormat.RGBA8, n, n2, 1, 1);
        historyView = RenderSystem.getDevice().createTextureView(historyTex);
        historyValid = false;
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

    private static GpuSampler sampler(FilterMode filterMode) {
        return RenderSystem.getSamplerCache().method_75294(filterMode);
    }

    private static void disable(Throwable throwable) {
        boolean bl;
        block2: {
            block3: {
                int n = EffectEngine.a;
                bl = broken;
                if (n != 0) break block2;
                if (!bl) break block3;
                return;
            }
            broken = true;
            bl = false;
        }
        historyValid = bl;
        String[] stringArray = a;
        LoggerFactory.getLogger((String)stringArray[10]).error(stringArray[3], throwable);
    }

    private static String lambda$ensureBuffers$4() {
        return a[6];
    }

    private static String lambda$ensureBuffers$3() {
        return a[5];
    }

    private static String lambda$ensureBuffers$2() {
        return a[22];
    }

    private static String lambda$frameBlend$1() {
        return a[15];
    }

    private static String lambda$camera$0() {
        return a[20];
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
        int n2 = (n ^ (int)l ^ 0x37B) & Short.MAX_VALUE;
        if (c[n2] == null) {
            MotionBlurGpu.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
