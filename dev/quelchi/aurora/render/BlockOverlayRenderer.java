package dev.quelchi.aurora.render;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.platform.SourceFactor;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.modules.BlockOverlayModule;
import dev.quelchi.aurora.render.BlockOverlayRenderer$1;
import dev.quelchi.aurora.render.EffectEngine;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.OptionalInt;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.render.LayeringTransform;
import net.minecraft.client.render.OutputTarget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.render.state.OutlineRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;

public final class BlockOverlayRenderer {
    private static final float GROW = 0.001f;
    private static final int GLOW_PASSES;
    private static final RenderPipeline LINES_THROUGH_WALLS;
    private static final RenderLayer LINES_THROUGH_WALLS_LAYER;
    private static final RenderPipeline FILL_THROUGH_WALLS;
    private static final RenderLayer FILL_THROUGH_WALLS_LAYER;
    private static final RenderPipeline GLOW_LINES;
    private static final RenderLayer GLOW_LINES_LAYER;
    private static final RenderPipeline BLUR;
    private static final RenderPipeline BLUR_ADD;
    private static boolean glowBroken;
    private static boolean glowCompiled;
    private static GpuTexture glowSrc;
    private static GpuTexture glowA;
    private static GpuTexture glowB;
    private static GpuTextureView glowSrcView;
    private static GpuTextureView glowAView;
    private static GpuTextureView glowBView;
    private static GpuBuffer stepH;
    private static GpuBuffer stepV;
    private static float stepSize;
    private static final String[] a;

    private BlockOverlayRenderer() {
    }

    private static RenderPipeline blurPipeline(String string, BlendFunction blendFunction) {
        String[] stringArray = a;
        RenderPipeline.Builder builder = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(Identifier.method_60655((String)stringArray[19], (String)(stringArray[2] + string))).withVertexShader(Identifier.method_60655((String)stringArray[10], (String)stringArray[7])).withFragmentShader(Identifier.method_60655((String)stringArray[10], (String)stringArray[16])).withVertexFormat(VertexFormats.field_60033, VertexFormat.DrawMode.field_27379).withUniform(stringArray[4], UniformType.field_60031).withSampler(stringArray[18]).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false);
        RenderPipeline.Builder builder2 = blendFunction == null ? builder.withoutBlend() : builder.withBlend(blendFunction);
        return builder2.build();
    }

    
    private static void blurGlow(VertexConsumerProvider.Immediate immediate, MatrixStack matrixStack, VoxelShape voxelShape, double d, double d2, double d3, int n, float f, boolean bl) {
        GpuDevice gpuDevice;
        int n2;
        Framebuffer framebuffer;
        block52: {
            block54: {
                GpuBuffer gpuBuffer;
                block53: {
                    int n3;
                    block50: {
                        float f2;
                        block51: {
                            int n4;
                            int n5;
                            block48: {
                                GpuTexture gpuTexture;
                                block49: {
                                    block47: {
                                        block45: {
                                            GpuDevice gpuDevice2;
                                            block46: {
                                                boolean bl2;
                                                block44: {
                                                    framebuffer = MinecraftClient.method_1551().method_1522();
                                                    n2 = EffectEngine.a;
                                                    n5 = framebuffer.field_1482;
                                                    n4 = framebuffer.field_1481;
                                                    int n6 = n5;
                                                    if (n2 == 0) {
                                                        if (n6 <= 0) return;
                                                        n6 = n4;
                                                    }
                                                    if (n6 <= 0) {
                                                        return;
                                                    }
                                                    gpuDevice = RenderSystem.getDevice();
                                                    bl2 = glowCompiled;
                                                    if (n2 != 0) break block44;
                                                    if (bl2) break block45;
                                                    gpuDevice2 = gpuDevice;
                                                    if (n2 != 0) break block46;
                                                    bl2 = gpuDevice2.precompilePipeline(BLUR).isValid();
                                                }
                                                if (!bl2) throw new IllegalStateException(a[9]);
                                                gpuDevice2 = gpuDevice;
                                            }
                                            boolean bl3 = gpuDevice2.precompilePipeline(BLUR_ADD).isValid();
                                            if (n2 == 0) {
                                                if (!bl3) {
                                                    throw new IllegalStateException(a[9]);
                                                }
                                                bl3 = true;
                                            }
                                            glowCompiled = bl3;
                                        }
                                        gpuTexture = glowSrc;
                                        if (n2 != 0) break block47;
                                        if (gpuTexture == null) break block48;
                                        gpuTexture = glowSrc;
                                    }
                                    if (n2 != 0) break block49;
                                    if (gpuTexture.getWidth(0) != n5) break block48;
                                    gpuTexture = glowSrc;
                                }
                                n3 = gpuTexture.getHeight(0);
                                if (n2 != 0) break block50;
                                if (n3 == n4) break block51;
                            }
                            BlockOverlayRenderer.resizeGlow(gpuDevice, n5, n4);
                        }
                        n3 = (f2 = stepSize - f) == 0.0f ? 0 : (f2 > 0.0f ? 1 : -1);
                    }
                    if (n3 == 0) break block52;
                    gpuBuffer = stepH;
                    if (n2 != 0) break block53;
                    if (gpuBuffer == null) break block54;
                    stepH.close();
                    gpuBuffer = stepV;
                }
                gpuBuffer.close();
            }
            float f3 = 0.6f + f * 0.15f;
            stepH = BlockOverlayRenderer.step(gpuDevice, f3 / (float)glowA.getWidth(0), 0.0f);
            stepV = BlockOverlayRenderer.step(gpuDevice, 0.0f, f3 / (float)glowA.getHeight(0));
            stepSize = f;
        }
        CommandEncoder commandEncoder = gpuDevice.createCommandEncoder();
        commandEncoder.clearColorTexture(glowSrc, 0);
        RenderLayer renderLayer = bl ? LINES_THROUGH_WALLS_LAYER : GLOW_LINES_LAYER;
        GpuTextureView gpuTextureView = RenderSystem.outputColorTextureOverride;
        RenderSystem.outputColorTextureOverride = glowSrcView;
        try {
            BlockOverlayRenderer.edges(matrixStack, immediate.method_73477(renderLayer), voxelShape, d, d2, d3, n, n, 0.0f, 3.0f + f);
            immediate.method_22994(renderLayer);
        }
        finally {
            RenderSystem.outputColorTextureOverride = gpuTextureView;
        }
        int n7 = 1 + (int)(f / 3.0f);
        BlockOverlayRenderer.blur(commandEncoder, BLUR, glowSrcView, glowAView, stepH);
        for (int i = 1; i < n7; ++i) {
            BlockOverlayRenderer.blur(commandEncoder, BLUR, glowAView, glowBView, stepV);
            if (n2 != 0) return;
            BlockOverlayRenderer.blur(commandEncoder, BLUR, glowBView, glowAView, stepH);
            if (n2 == 0) continue;
        }
        BlockOverlayRenderer.blur(commandEncoder, BLUR_ADD, glowAView, framebuffer.method_71639(), stepV);
    }

    private static void resizeGlow(GpuDevice gpuDevice, int n, int n2) {
        block5: {
            GpuTextureView gpuTextureView;
            block4: {
                int n3 = EffectEngine.a;
                gpuTextureView = glowSrcView;
                if (n3 != 0) break block4;
                if (gpuTextureView == null) break block5;
                glowSrcView.close();
                glowSrc.close();
                glowAView.close();
                glowA.close();
                gpuTextureView = glowBView;
            }
            gpuTextureView.close();
            glowB.close();
        }
        int n4 = 13;
        int n5 = Math.max(1, n / 2);
        int n6 = Math.max(1, n2 / 2);
        String[] stringArray = a;
        glowSrc = gpuDevice.createTexture(stringArray[13], n4, TextureFormat.RGBA8, n, n2, 1, 1);
        glowSrcView = gpuDevice.createTextureView(glowSrc);
        glowA = gpuDevice.createTexture(stringArray[0], n4, TextureFormat.RGBA8, n5, n6, 1, 1);
        glowAView = gpuDevice.createTextureView(glowA);
        glowB = gpuDevice.createTexture(stringArray[15], n4, TextureFormat.RGBA8, n5, n6, 1, 1);
        glowBView = gpuDevice.createTextureView(glowB);
        stepSize = -1.0f;
    }

    private static GpuBuffer step(GpuDevice gpuDevice, float f, float f2) {
        GpuBuffer gpuBuffer;
        block9: {
            MemoryStack memoryStack;
            block8: {
                MemoryStack memoryStack2 = MemoryStack.stackPush();
                int n = EffectEngine.a;
                try {
                    ByteBuffer byteBuffer = memoryStack2.malloc(16).order(ByteOrder.nativeOrder());
                    byteBuffer.putFloat(f).putFloat(f2).putFloat(0.0f).putFloat(0.0f).flip();
                    gpuBuffer = gpuDevice.createBuffer(BlockOverlayRenderer::lambda$step$0, 128, byteBuffer);
                }
                catch (Throwable throwable) {
                    block10: {
                        if (n != 0 || memoryStack2 == null) break block10;
                        try {
                            memoryStack2.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                memoryStack = memoryStack2;
                if (n != 0) break block8;
                if (memoryStack == null) break block9;
                memoryStack = memoryStack2;
            }
            memoryStack.close();
        }
        return gpuBuffer;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void blur(CommandEncoder commandEncoder, RenderPipeline renderPipeline, GpuTextureView gpuTextureView, GpuTextureView gpuTextureView2, GpuBuffer gpuBuffer) {
        RenderPass renderPass = commandEncoder.createRenderPass(BlockOverlayRenderer::lambda$blur$1, gpuTextureView2, OptionalInt.empty());
        int n = EffectEngine.a;
        try {
            renderPass.setPipeline(renderPipeline);
            String[] stringArray = a;
            renderPass.bindTexture(stringArray[17], gpuTextureView, RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR));
            renderPass.setUniform(stringArray[21], gpuBuffer);
            renderPass.draw(0, 3);
            if (n != 0) return;
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
        if (renderPass == null) return;
        renderPass.close();
        return;
    }

    
    public static void render(VertexConsumerProvider.Immediate var0, MatrixStack var1_1, Vec3d var2_2, OutlineRenderState var3_3, BlockOverlayModule var4_4) {
        var6_5 = var3_3.comp_4932();
        var7_6 = var3_3.comp_4935();
        var8_7 = (double)var6_5.method_10263() - var2_2.field_1352;
        var5_8 = EffectEngine.a;
        var10_9 = (double)var6_5.method_10264() - var2_2.field_1351;
        var12_10 = (double)var6_5.method_10260() - var2_2.field_1350;
        var14_11 = var4_4.throughWalls();
        var15_12 = var14_11 != false ? BlockOverlayRenderer.LINES_THROUGH_WALLS_LAYER : RenderLayers.method_76015();
        var16_13 = var14_11 != false ? BlockOverlayRenderer.FILL_THROUGH_WALLS_LAYER : RenderLayers.method_76019();
        v0 = BlockOverlayRenderer$1.$SwitchMap$dev$quelchi$aurora$module$modules$BlockOverlayModule$Style[var4_4.style().ordinal()];
        if (var5_8 != 0) // goto
        switch (v0) {
            case 1: {
                v1 = var4_4;
                if (var5_8 != 0) // goto
                v0 = (int)v1.fills();
lbl17:

                if (v0 != 0) {
                    BlockOverlayRenderer.faces(var0, var16_13, var1_1, var7_6, var8_7, var10_9, var12_10, var4_4.faceArgb(), var4_4.faceArgb(), 0.0f);
                }
                v1 = var4_4;
lbl20:

                if (!v1.outlines()) return;
                VertexRendering.method_62296((MatrixStack)var1_1, (VertexConsumer)var0.method_73477(var15_12), (VoxelShape)var7_6, (double)var8_7, (double)var10_9, (double)var12_10, (int)var4_4.outlineArgb(), (float)var4_4.lineWidth());
                var0.method_22994(var15_12);
                if (var5_8 == 0) return;
            }
            case 2: {
                var17_14 = var4_4.glowColor();
                var18_16 = ColorHelper.method_61330((int)ColorHelper.method_61320((int)var17_14), (int)ColorHelper.method_61319((float)0.6f, (int)var17_14, (int)-1));
                if (var5_8 == 0 && !BlockOverlayRenderer.glowBroken) {
                    try {
                        BlockOverlayRenderer.blurGlow(var0, var1_1, var7_6, var8_7, var10_9, var12_10, var17_14, var4_4.glowSize(), var14_11);
                    }
                    catch (Throwable var19_17) {
                        BlockOverlayRenderer.glowBroken = true;
                        Aurora.LOG.warn(BlockOverlayRenderer.a[11], var19_17);
                    }
                }
                var19_18 = var0.method_73477(var15_12);
                if (var5_8 == 0) {
                    if (BlockOverlayRenderer.glowBroken) {
                        var20_21 = ColorHelper.method_75601((int)var17_14, (float)0.12f);
                        for (var21_22 = 8; var21_22 > 0; --var21_22) {
                            v2 = var1_1;
                            v3 = var19_18;
                            v4 = var7_6;
                            v5 = var8_7;
                            if (var5_8 == 0) {
                                BlockOverlayRenderer.edges(v2, v3, v4, v5, var10_9, var12_10, var20_21, var20_21, 0.0f, 2.0f + var4_4.glowSize() * 4.0f * (float)var21_22 / 8.0f);
                                if (var5_8 == 0) continue;
                            }
                            break;
                        }
                    } else {
                        v2 = var1_1;
                        v3 = var19_18;
                        v4 = var7_6;
                        v5 = var8_7;
                    }
                    BlockOverlayRenderer.edges(v2, v3, v4, v5, var10_9, var12_10, var18_16, var18_16, 0.0f, 2.0f);
                    var0.method_22994(var15_12);
                }
                if (var5_8 == 0) return;
            }
            case 3: {
                var17_14 = var4_4.gradientColor1();
                var18_16 = var4_4.gradientColor2();
                var19_19 = var4_4.gradientTime();
                if (var5_8 == 0) {
                    if (var4_4.gradientFaces()) {
                        BlockOverlayRenderer.faces(var0, var16_13, var1_1, var7_6, var8_7, var10_9, var12_10, ColorHelper.method_75601((int)var17_14, (float)var4_4.gradientFaceOpacity()), ColorHelper.method_75601((int)var18_16, (float)var4_4.gradientFaceOpacity()), var19_19);
                    }
                    BlockOverlayRenderer.edges(var1_1, var0.method_73477(var15_12), var7_6, var8_7, var10_9, var12_10, var17_14, var18_16, var19_19, 2.5f);
                    var0.method_22994(var15_12);
                }
                if (var5_8 == 0) return;
            }
            case 4: {
                var17_15 = var4_4.pulse();
                var18_16 = ColorHelper.method_75601((int)var4_4.pulseColor(), (float)(0.3f + 0.7f * var17_15));
                var19_20 = ColorHelper.method_75601((int)var18_16, (float)0.3f);
                BlockOverlayRenderer.faces(var0, var16_13, var1_1, var7_6, var8_7, var10_9, var12_10, var19_20, var19_20, 0.0f);
                BlockOverlayRenderer.edges(var1_1, var0.method_73477(var15_12), var7_6, var8_7, var10_9, var12_10, var18_16, var18_16, 0.0f, 2.5f * (0.85f + 0.3f * var17_15));
                var0.method_22994(var15_12);
                return;
            }
        }
    }

    private static void faces(VertexConsumerProvider.Immediate immediate, RenderLayer renderLayer, MatrixStack matrixStack, VoxelShape voxelShape, double d, double d2, double d3, int n, int n2, float f) {
        VertexConsumer vertexConsumer = immediate.method_73477(renderLayer);
        matrixStack.method_22903();
        matrixStack.method_22904(d, d2, d3);
        Matrix4f matrix4f = matrixStack.method_23760().method_23761();
        voxelShape.method_1089((arg_0, arg_1, arg_2, arg_3, arg_4, arg_5) -> BlockOverlayRenderer.lambda$faces$2(matrix4f, vertexConsumer, n, n2, f, arg_0, arg_1, arg_2, arg_3, arg_4, arg_5));
        matrixStack.method_22909();
        immediate.method_22994(renderLayer);
    }

    private static void edges(MatrixStack matrixStack, VertexConsumer vertexConsumer, VoxelShape voxelShape, double d, double d2, double d3, int n, int n2, float f, float f2) {
        int n3;
        MatrixStack.Entry entry;
        block4: {
            block5: {
                entry = matrixStack.method_23760();
                int n4 = EffectEngine.a;
                n3 = n;
                if (n4 != 0) break block4;
                if (n3 != n2) break block5;
                n3 = 1;
                break block4;
            }
            n3 = 8;
        }
        int n5 = n3;
        voxelShape.method_1104((arg_0, arg_1, arg_2, arg_3, arg_4, arg_5) -> BlockOverlayRenderer.lambda$edges$3(n5, vertexConsumer, entry, d, d2, d3, n, n2, f, f2, arg_0, arg_1, arg_2, arg_3, arg_4, arg_5));
    }

    private static int tint(int n, int n2, float f, float f2, float f3, float f4) {
        block5: {
            int n3;
            block4: {
                int n4 = EffectEngine.a;
                n3 = n;
                if (n4 != 0) break block4;
                if (n3 != n2) break block5;
                n3 = n;
            }
            return n3;
        }
        float f5 = 0.5f + 0.5f * (float)Math.sin(((f2 + f3 + f4) / 3.0f - f) * ((float)Math.PI * 2));
        return ColorHelper.method_61319((float)f5, (int)n, (int)n2);
    }

    private static void box(Matrix4f matrix4f, VertexConsumer vertexConsumer, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, float f7) {
        BlockOverlayRenderer.quad(matrix4f, vertexConsumer, f, f2, f3, f4, f2, f3, f4, f2, f6, f, f2, f6, n, n2, f7);
        BlockOverlayRenderer.quad(matrix4f, vertexConsumer, f, f5, f3, f, f5, f6, f4, f5, f6, f4, f5, f3, n, n2, f7);
        BlockOverlayRenderer.quad(matrix4f, vertexConsumer, f, f2, f3, f, f5, f3, f4, f5, f3, f4, f2, f3, n, n2, f7);
        BlockOverlayRenderer.quad(matrix4f, vertexConsumer, f, f2, f6, f4, f2, f6, f4, f5, f6, f, f5, f6, n, n2, f7);
        BlockOverlayRenderer.quad(matrix4f, vertexConsumer, f, f2, f3, f, f2, f6, f, f5, f6, f, f5, f3, n, n2, f7);
        BlockOverlayRenderer.quad(matrix4f, vertexConsumer, f4, f2, f3, f4, f5, f3, f4, f5, f6, f4, f2, f6, n, n2, f7);
    }

    private static void quad(Matrix4f matrix4f, VertexConsumer vertexConsumer, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, int n, int n2, float f13) {
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f, f2, f3).method_39415(BlockOverlayRenderer.tint(n, n2, f13, f, f2, f3));
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f4, f5, f6).method_39415(BlockOverlayRenderer.tint(n, n2, f13, f4, f5, f6));
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f7, f8, f9).method_39415(BlockOverlayRenderer.tint(n, n2, f13, f7, f8, f9));
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f10, f11, f12).method_39415(BlockOverlayRenderer.tint(n, n2, f13, f10, f11, f12));
    }

    private static void lambda$edges$3(int n, VertexConsumer vertexConsumer, MatrixStack.Entry entry, double d, double d2, double d3, int n2, int n3, float f, float f2, double d4, double d5, double d6, double d7, double d8, double d9) {
        float f3 = (float)(d7 - d4);
        float f4 = (float)(d8 - d5);
        float f5 = (float)(d9 - d6);
        int n4 = EffectEngine.a;
        Vector3f vector3f = new Vector3f(f3, f4, f5).normalize();
        for (int i = 0; i < n; ++i) {
            float f6 = (float)i / (float)n;
            float f7 = (float)(i + 1) / (float)n;
            float f8 = (float)d4 + f3 * f6;
            float f9 = (float)d5 + f4 * f6;
            float f10 = (float)d6 + f5 * f6;
            float f11 = (float)d4 + f3 * f7;
            float f12 = (float)d5 + f4 * f7;
            float f13 = (float)d6 + f5 * f7;
            vertexConsumer.method_56824(entry, (float)((double)f8 + d), (float)((double)f9 + d2), (float)((double)f10 + d3)).method_39415(BlockOverlayRenderer.tint(n2, n3, f, f8, f9, f10)).method_61959(entry, vector3f).method_75298(f2);
            vertexConsumer.method_56824(entry, (float)((double)f11 + d), (float)((double)f12 + d2), (float)((double)f13 + d3)).method_39415(BlockOverlayRenderer.tint(n2, n3, f, f11, f12, f13)).method_61959(entry, vector3f).method_75298(f2);
            if (n4 == 0) continue;
        }
    }

    private static void lambda$faces$2(Matrix4f matrix4f, VertexConsumer vertexConsumer, int n, int n2, float f, double d, double d2, double d3, double d4, double d5, double d6) {
        BlockOverlayRenderer.box(matrix4f, vertexConsumer, (float)d - 0.001f, (float)d2 - 0.001f, (float)d3 - 0.001f, (float)d4 + 0.001f, (float)d5 + 0.001f, (float)d6 + 0.001f, n, n2, f);
    }

    private static String lambda$blur$1() {
        return a[12];
    }

    private static String lambda$step$0() {
        return a[3];
    }

    
    static {
        var9 = new String[25];
        var7_1 = 0;
        var6_2 = "\n5Uy 7u\t,Hu9v2\u0007/P6\u0013\u0012\t,Hu9\t:\u001d%Uz3/\n\t,Rd\t\u001b)Ws>?;\u000eo\u0016\n5Uy 7u\t,Hu9v2\u0007/P6!\"0\u001b\n),Rd\u00119;\r)@\u0016\t,Hu9\t:\u001d%Uz3/\n\t,Rd\r71\u000f(\n5Uy 7\n\t,Hu9\t:\u001d%Uz3/\n\u0007)Is!\t!\u00032Hc5>\n\u001c!Kz!\t\b/Us}49\u001e21\n5Uy 7z\u001b)Ws>?;\u000eoEz=5>4/Qs :4\u0012\u001fK\u007f<3&44Od=#2\u0003\u001fPw>:&.\t,Rdr%=\n$Bdr2<\u000f`Iy&v6\u0004-W\u007f>3yK3Bsr\"=\u000e`Ky5v4\t/Qs\u0006\n5Uy 7=),Hu9v:\u001d%Uz3/u\f,Har&4\u00183\u0007p3?9\u000e$\u000b6479\u0007)Iqr44\b+\u0007b=v!\u0003%\u0007z;80K'Ky%\u0011\n5Uy 7u\t,Hu9v2\u0007/P\u0011\n5Uy 7u\t,Hu9v2\u0007/P0\n5Uy 7z\u001b)Ws>?;\u000eoEz=5>4/Qs :4\u0012\u001fA\u007f>:\n\u001f(Uy'1=47Fz>%\u0013\n5Uy 7u\t,Hu9v2\u0007/P6\u0010\t\b/Us}49\u001e2\b8!Jf>3'[\b8!Jf>3'[\u0006\n5Uy 7'\n5Uy 7\n\t,Hu9\t:\u001d%Uz3/\n\r)Kz\r\"=\u0019/Rq:\t\"\n,Ke\n),Rd\u00119;\r)@(\n5Uy 7z\u001b)Ws>?;\u000eoEz=5>4/Qs :4\u0012\u001f@z=!\n\u0007)Is!";
        var8_3 = "\n5Uy 7u\t,Hu9v2\u0007/P6\u0013\u0012\t,Hu9\t:\u001d%Uz3/\n\t,Rd\t\u001b)Ws>?;\u000eo\u0016\n5Uy 7u\t,Hu9v2\u0007/P6!\"0\u001b\n),Rd\u00119;\r)@\u0016\t,Hu9\t:\u001d%Uz3/\n\t,Rd\r71\u000f(\n5Uy 7\n\t,Hu9\t:\u001d%Uz3/\n\u0007)Is!\t!\u00032Hc5>\n\u001c!Kz!\t\b/Us}49\u001e21\n5Uy 7z\u001b)Ws>?;\u000eoEz=5>4/Qs :4\u0012\u001fK\u007f<3&44Od=#2\u0003\u001fPw>:&.\t,Rdr%=\n$Bdr2<\u000f`Iy&v6\u0004-W\u007f>3yK3Bsr\"=\u000e`Ky5v4\t/Qs\u0006\n5Uy 7=),Hu9v:\u001d%Uz3/u\f,Har&4\u00183\u0007p3?9\u000e$\u000b6479\u0007)Iqr44\b+\u0007b=v!\u0003%\u0007z;80K'Ky%\u0011\n5Uy 7u\t,Hu9v2\u0007/P\u0011\n5Uy 7u\t,Hu9v2\u0007/P0\n5Uy 7z\u001b)Ws>?;\u000eoEz=5>4/Qs :4\u0012\u001fA\u007f>:\n\u001f(Uy'1=47Fz>%\u0013\n5Uy 7u\t,Hu9v2\u0007/P6\u0010\t\b/Us}49\u001e2\b8!Jf>3'[\b8!Jf>3'[\u0006\n5Uy 7'\n5Uy 7\n\t,Hu9\t:\u001d%Uz3/\n\r)Kz\r\"=\u0019/Rq:\t\"\n,Ke\n),Rd\u00119;\r)@(\n5Uy 7z\u001b)Ws>?;\u000eoEz=5>4/Qs :4\u0012\u001f@z=!\n\u0007)Is!".length();
        var5_4 = 19;
        var4_5 = -1;
        block11: while (true) {
            v0 = ++var4_5;
            v1 = var6_2.substring(v0, v0 + var5_4);
            v2 = -1;
            block12: while (true) {
                var6_2 = "\u001d5K}388\u0004$\u001f\n5Uy 7\n\t,Hu9\t:\u001d%Uz3/\n\f,Ha\r:<\u0005%T";
                var8_3 = "\u001d5K}388\u0004$\u001f\n5Uy 7\n\t,Hu9\t:\u001d%Uz3/\n\f,Ha\r:<\u0005%T".length();
                var5_4 = 9;
                var4_5 = -1;
                while (true) {
                    v3 = ++var4_5;
                    v1 = var6_2.substring(v3, v3 + var5_4);
                    v2 = 0;
lbl21:

                    v4 = v1.toCharArray();
                    v5 = v4;
                    v6 = v4.length;
                    var10_7 = 0;
                    do {
                        v5 = v5;
                        v7 = var10_7;
                        v8 = v5[v7];
                        switch (var10_7 % 7) {
                            case 0: {
                                v9 = 107;
                                break;
                            }
                            case 1: {
                                v9 = 64;
                                break;
                            }
                            case 2: {
                                v9 = 39;
                                break;
                            }
                            case 3: {
                                v9 = 22;
                                break;
                            }
                            case 4: {
                                v9 = 82;
                                break;
                            }
                            case 5: {
                                v9 = 86;
                                break;
                            }
                            default: {
                                v9 = 85;
                            }
                        }
                        v5[v7] = (char)(v8 ^ v9);
                        ++var10_7;
lbl53:

                        v6 = v6;
                    } while (v6 > var10_7);
                    v10 = new String(v5);
                    switch (v2) {
                        default: {
                            var9[var7_1++] = v10.intern();
                            if ((var4_5 += var5_4) >= var8_3) continue block12;
                            var5_4 = var6_2.charAt(var4_5);
                            continue block11;
                        }
                        case 0: 
                    }
                    var9[var7_1++] = v10.intern();
                    if ((var4_5 += var5_4) >= var8_3) {
                        BlockOverlayRenderer.a = var9;
                        var2_8 = 4023583624561589283L;
                        var0_9 = 2991012468002815019L ^ var2_8;
                        BlockOverlayRenderer.GLOW_PASSES = (int)var0_9;
                        var4_6 = BlockOverlayRenderer.a;
                        BlockOverlayRenderer.LINES_THROUGH_WALLS = RenderPipelines.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{RenderPipelines.field_56859}).withLocation(var4_6[8]).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).build());
                        BlockOverlayRenderer.LINES_THROUGH_WALLS_LAYER = RenderLayer.method_75940((String)var4_6[6], (RenderSetup)RenderSetup.method_75927((RenderPipeline)BlockOverlayRenderer.LINES_THROUGH_WALLS).method_75930(LayeringTransform.field_63976).method_75931(OutputTarget.field_63983).method_75938());
                        BlockOverlayRenderer.FILL_THROUGH_WALLS = RenderPipelines.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{RenderPipelines.field_56860}).withLocation(var4_6[14]).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).build());
                        BlockOverlayRenderer.FILL_THROUGH_WALLS_LAYER = RenderLayer.method_75940((String)var4_6[20], (RenderSetup)RenderSetup.method_75927((RenderPipeline)BlockOverlayRenderer.FILL_THROUGH_WALLS).method_75937().method_75930(LayeringTransform.field_63976).method_75938());
                        BlockOverlayRenderer.GLOW_LINES = RenderPipelines.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{RenderPipelines.field_56859}).withLocation(var4_6[22]).withDepthWrite(false).build());
                        BlockOverlayRenderer.GLOW_LINES_LAYER = RenderLayer.method_75940((String)var4_6[24], (RenderSetup)RenderSetup.method_75927((RenderPipeline)BlockOverlayRenderer.GLOW_LINES).method_75930(LayeringTransform.field_63976).method_75931(OutputTarget.field_63983).method_75938());
                        BlockOverlayRenderer.BLUR = BlockOverlayRenderer.blurPipeline(var4_6[1], null);
                        BlockOverlayRenderer.BLUR_ADD = BlockOverlayRenderer.blurPipeline(var4_6[5], new BlendFunction(SourceFactor.ONE, DestFactor.ONE, SourceFactor.ZERO, DestFactor.ONE));
                        BlockOverlayRenderer.glowBroken = FabricLoader.getInstance().isModLoaded(var4_6[23]);
                        BlockOverlayRenderer.stepSize = -1.0f;
                        return;
                    }
                    var5_4 = var6_2.charAt(var4_5);
                }
                break;
            }
            break;
        }
    }
}
