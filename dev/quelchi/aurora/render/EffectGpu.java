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
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Pool;
import dev.quelchi.aurora.render.EffectGpu$Tex;
import dev.quelchi.aurora.render.EffectGpu$VcOut;
import dev.quelchi.aurora.render.SoftLayer;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;
import org.joml.Vector4fc;
import org.lwjgl.system.MemoryStack;

public final class EffectGpu {
    private static final boolean VULKAN;
    private static final boolean IRIS;
    private static final Identifier BLIT;
    private static final Identifier FX_SHADER;
    private static final int BLOOM_LEVELS;
    private static final float BLOOM = 0.7f;
    private static boolean broken;
    private static boolean outlineBroken;
    private static boolean directBroken;
    private static RenderPipeline prefilter;
    private static RenderPipeline down;
    private static RenderPipeline up;
    private static RenderPipeline blur;
    private static RenderPipeline outlinePipe;
    private static RenderPipeline composite;
    private static RenderLayer fxLayer;
    private static RenderLayer directLayer;
    private static GpuBuffer quad;
    private static GpuBuffer compositeParams;
    private static GpuBuffer blurX;
    private static GpuBuffer blurY;
    private static GpuBuffer outlineParams;
    private static final EffectGpu$Tex fx;
    private static final EffectGpu$Tex scene;
    private static final EffectGpu$Tex mask;
    private static final EffectGpu$Tex maskDepth;
    private static final EffectGpu$Tex blurA;
    private static final EffectGpu$Tex blurB;
    private static final EffectGpu$Tex[] bloom;
    private static int bloomN;
    private static int frame;
    private static Method irisInUse;
    private static Object irisApi;
    private static final BufferAllocator BUFFER;
    private static VertexConsumerProvider.Immediate immediate;
    private static final EffectGpu$VcOut OUT;
    private static final Vector3f RIGHT;
    private static final Vector3f UP;
    private static final Vector3f TMP;
    private static final Matrix4f VP;
    private static final Matrix4f INV;
    private static final Vector4f V4;
    private static final float[] WAVES;
    private static final float[] OUTLINE;
    private static final int[] VICTIM;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private EffectGpu() {
    }

    
    static boolean active() {
        int n = EffectEngine.a;
        boolean bl = VULKAN;
        if (n != 0) return bl;
        if (bl) return false;
        bl = broken;
        if (n != 0) return bl;
        if (bl) return false;
        boolean bl2 = EffectGpu.irisPack();
        if (n != 0) return bl2;
        if (bl2) {
            return false;
        }
        try {
            bl2 = EffectGpu.ready();
            return bl2;
        }
        catch (Throwable throwable) {
            EffectGpu.disable(throwable);
            return false;
        }
    }

    
    private static boolean ready() {
        GpuDevice gpuDevice;
        RenderPipeline renderPipeline;
        block11: {
            int n = EffectEngine.a;
            RenderPipeline renderPipeline2 = composite;
            if (n == 0) {
                if (renderPipeline2 != null) {
                    return true;
                }
                String[] stringArray = a;
                fxLayer = RenderLayer.method_75940((String)stringArray[53], (RenderSetup)RenderSetup.method_75927((RenderPipeline)EffectGpu.spritePipeline(stringArray[51], false)).method_75938());
                prefilter = EffectGpu.post(stringArray[9], null, false, stringArray[32]);
                down = EffectGpu.post(stringArray[4], null, false, stringArray[22]);
                up = EffectGpu.post(stringArray[10], BlendFunction.ADDITIVE, false, stringArray[22]);
                blur = EffectGpu.post(stringArray[31], null, true, stringArray[22]);
                outlinePipe = EffectGpu.post(stringArray[36], BlendFunction.ADDITIVE, true, stringArray[61], stringArray[37]);
                renderPipeline2 = EffectGpu.post(stringArray[7], null, true, stringArray[15], stringArray[24], stringArray[25], stringArray[26]);
            }
            renderPipeline = renderPipeline2;
            boolean bl = broken;
            if (n != 0) return bl;
            if (bl) {
                return false;
            }
            gpuDevice = RenderSystem.getDevice();
            MemoryStack memoryStack = MemoryStack.stackPush();
            try {
                ByteBuffer byteBuffer = memoryStack.malloc(48);
                if (n != 0) break block11;
                byteBuffer.asFloatBuffer().put(new float[]{-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f});
                byteBuffer.rewind();
                quad = gpuDevice.createBuffer(EffectGpu::lambda$ready$0, 32, byteBuffer);
                if (memoryStack == null) break block11;
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
        int n = 130;
        compositeParams = gpuDevice.createBuffer(EffectGpu::lambda$ready$1, n, EffectGpu.a(31962, 4954991999157949232L));
        blurX = gpuDevice.createBuffer(EffectGpu::lambda$ready$2, n, EffectGpu.a(545, 3956041494817680841L));
        blurY = gpuDevice.createBuffer(EffectGpu::lambda$ready$3, n, EffectGpu.a(12742, 4107715879345167917L));
        outlineParams = gpuDevice.createBuffer(EffectGpu::lambda$ready$4, n, EffectGpu.a(9810, 6713977968878127547L));
        composite = renderPipeline;
        return true;
    }

    private static RenderPipeline spritePipeline(String string, boolean bl) {
        String[] stringArray = a;
        RenderPipeline.Builder builder = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{RenderPipelines.field_60125}).withLocation(Identifier.method_60655((String)stringArray[39], (String)(stringArray[14] + string))).withVertexShader(FX_SHADER).withFragmentShader(FX_SHADER).withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.field_1580, VertexFormat.DrawMode.field_27382);
        if (bl) {
            builder.withShaderDefine(stringArray[16]);
        }
        RenderPipeline renderPipeline = builder.build();
        if (!RenderSystem.getDevice().precompilePipeline(renderPipeline, null).isValid()) {
            throw new IllegalStateException(string + a[1]);
        }
        return renderPipeline;
    }

    private static RenderPipeline post(String string, BlendFunction blendFunction, boolean bl, String ... renderPipeline) {
        RenderPipeline renderPipeline2;
        block24: {
            block23: {
                RenderPipeline.Builder builder;
                int n;
                block21: {
                    RenderPipeline.Builder builder2;
                    block22: {
                        int n2;
                        block19: {
                            block20: {
                                n = EffectEngine.a;
                                n2 = broken;
                                if (n != 0) break block19;
                                if (n2 == 0) break block20;
                                return null;
                            }
                            n2 = 0;
                        }
                        String[] stringArray = a;
                        builder = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[n2]).withLocation(Identifier.method_60655((String)stringArray[56], (String)(stringArray[42] + string))).withVertexShader(BLIT).withFragmentShader(Identifier.method_60655((String)stringArray[39], (String)(stringArray[48] + string))).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withVertexFormat(VertexFormats.field_1592, VertexFormat.DrawMode.field_27382);
                        if (bl) {
                            builder.withUniform(stringArray[28], UniformType.field_60031);
                        }
                        for (RenderPipeline string2 : renderPipeline) {
                            builder2 = builder.withSampler((String)string2);
                            if (n == 0) {
                                if (n == 0) continue;
                                break;
                            }
                            break block21;
                        }
                        if (blendFunction != null) break block22;
                        builder.withoutBlend();
                        if (n == 0) break block21;
                    }
                    builder2 = builder.withBlend(blendFunction);
                }
                renderPipeline2 = builder.build();
                if (n != 0) break block23;
                if (RenderSystem.getDevice().precompilePipeline(renderPipeline2, null).isValid()) break block24;
                EffectGpu.disable(new IllegalStateException(string + a[45]));
            }
            return null;
        }
        return renderPipeline2;
    }

    
    static void drawWorld(EffectEngine$Pool var0, Matrix4f var1_1, Matrix4f var2_2, Camera var3_3) {
        block66: {
            block69: {
                block54: {
                    block70: {
                        block53: {
                            block68: {
                                block52: {
                                    block67: {
                                        block51: {
                                            var5_4 = MinecraftClient.method_1551();
                                            var6_5 = var5_4.method_1522();
                                            var4_6 = EffectEngine.a;
                                            v0 = var6_5;
                                            if (var4_6 != 0) break block51;
                                            if (v0 != null) {
                                            }
                                            v0 = var6_5;
                                        }
                                        if (var4_6 != 0) break block52;
                                        if (!v0.field_1478) // goto
                                        break block67;
                                        catch (Throwable v2) {
                                            throw v2;
                                        }
                                    }
                                    v0 = var6_5;
                                }
                                if (var4_6 != 0) break block53;
                                if (v0.field_1482 <= 0) // goto
                                break block68;
                                catch (Throwable v4) {
                                    throw v4;
                                }
                            }
                            v0 = var6_5;
                        }
                        if (var4_6 != 0) break block69;
                        if (v0.field_1481 > 0) break block54;
                        break block70;
                        catch (Throwable v6) {
                            throw v6;
                        }
                    }
                    return;
                }
                v0 = var6_5;
            }
            var7_7 = v0.field_1482;
            var8_8 = var6_5.field_1481;
            try {
                block64: {
                    block65: {
                        block74: {
                            var9_9 = 12;
                            var18_11 = EffectGpu.a;
                            EffectGpu.fx.ensure(var18_11[50], var9_9, TextureFormat.RGBA8, var7_7, var8_8);
                            EffectGpu.scene.ensure(var18_11[34], 5, TextureFormat.RGBA8, var7_7, var8_8);
                            EffectGpu.bloomN = 0;
                            var10_12 = 0;
                            var11_14 = var7_7 >> 1;
                            var12_16 = var8_8 >> 1;
                            while (var10_12 < 5) {
                                block56: {
                                    block55: {
                                        block71: {
                                            v8 = var11_14;
                                            if (var4_6 != 0) break block55;
                                            if (v8 < 4) break;
                                            break block71;
                                            catch (Throwable v9) {
                                                throw v9;
                                            }
                                        }
                                        v8 = var12_16;
                                    }
                                    if (var4_6 != 0) break block56;
                                    if (v8 < 4) break;
                                    var18_11 = EffectGpu.a;
                                    EffectGpu.bloom[var10_12].ensure(var18_11[3], var9_9, TextureFormat.RGBA8, var11_14, var12_16);
                                    ++EffectGpu.bloomN;
                                    ++var10_12;
                                    var11_14 >>= 1;
                                    v8 = var12_16 = var12_16 >> 1;
                                }
                                if (var4_6 == 0) continue;
                            }
                            var10_13 = var3_3.method_71156();
                            var3_3.method_23767().transform(EffectGpu.RIGHT.set(1.0f, 0.0f, 0.0f));
                            var3_3.method_23767().transform(EffectGpu.UP.set(0.0f, 1.0f, 0.0f));
                            var11_15 = RenderSystem.getDevice().createCommandEncoder();
                            var11_15.clearColorTexture(EffectGpu.fx.t, 0);
                            var12_16 = 0;
                            var13_17 = RenderSystem.outputColorTextureOverride;
                            var14_18 = RenderSystem.outputDepthTextureOverride;
                            var15_19 = RenderSystem.getModelViewStack();
                            var15_19.pushMatrix();
                            var15_19.set((Matrix4fc)var1_1);
                            RenderSystem.outputColorTextureOverride = EffectGpu.fx.v;
                            RenderSystem.outputDepthTextureOverride = var6_5.method_71640();
                            try {
                                block62: {
                                    block60: {
                                        block61: {
                                            block73: {
                                                block59: {
                                                    block72: {
                                                        block57: {
                                                            block58: {
                                                                var16_20 = EffectGpu.immediate();
                                                                EffectGpu.OUT.vc = var16_20.method_73477(EffectGpu.fxLayer);
                                                                EffectGpu.OUT.m = null;
                                                                v12 = var0;
                                                                v13 = EffectGpu.OUT;
                                                                v14 = var10_13.field_1352;
                                                                v15 = var10_13.field_1351;
                                                                v16 = var10_13.field_1350;
                                                                v17 = 0.0f;
                                                                v18 = 0.0f;
                                                                v19 = 0.0f;
                                                                v20 = EffectGpu.RIGHT;
                                                                v21 = EffectGpu.UP;
                                                                v22 = EffectGpu.outlineBroken;
                                                                if (var4_6 != 0) break block57;
                                                                if (v22) break block58;
                                                                v22 = true;
                                                                break block57;
                                                            }
                                                            v22 = false;
                                                        }
                                                        EffectEngine.build(v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22);
                                                        var16_20.method_22993();
                                                        v24 = EffectGpu.outlineBroken;
                                                        if (var4_6 != 0) break block59;
                                                        if (v24) break block60;
                                                        break block72;
                                                        catch (Throwable v25) {
                                                            throw v25;
                                                        }
                                                    }
                                                    v24 = EffectEngine.outline(var0, EffectGpu.OUTLINE, EffectGpu.VICTIM);
                                                }
                                                if (var4_6 != 0) break block61;
                                                if (!v24) break block60;
                                                break block73;
                                                catch (Throwable v27) {
                                                    throw v27;
                                                }
                                            }
                                            v24 = EffectGpu.mask(var5_4, var3_3, var7_7, var8_8);
                                        }
                                        if (var4_6 != 0) break block62;
                                        if (!v24) break block60;
                                        v24 = true;
                                        break block62;
                                    }
                                    v24 = false;
                                }
                                var12_16 = (int)v24;
                            }
                            finally {
                                EffectGpu.OUT.vc = null;
                                RenderSystem.outputColorTextureOverride = var13_17;
                                RenderSystem.outputDepthTextureOverride = var14_18;
                                var15_19.popMatrix();
                            }
                            v30 = var12_16;
                            if (var4_6 != 0) break block64;
                            if (v30 == 0) break block65;
                            break block74;
                            catch (Throwable v31) {
                                throw v31;
                            }
                        }
                        EffectGpu.outline(var11_15, var7_7, var8_8);
                    }
                    var18_11 = EffectGpu.a;
                    EffectGpu.blit(var11_15, var18_11[20], EffectGpu.prefilter, EffectGpu.bloom[0], null, EffectGpu.fx.v, null);
                    v30 = var16_21 = 1;
                }
                while (var16_21 < EffectGpu.bloomN) {
                    v33 = var11_15;
                    v34 = EffectGpu.a[23];
                    v35 = EffectGpu.down;
                    v36 = EffectGpu.bloom[var16_21];
                    v37 = null;
                    v38 = EffectGpu.bloom;
                    v39 = var16_21 - 1;
                    if (var4_6 == 0) {
                        EffectGpu.blit(v33, v34, v35, v36, v37, v38[v39].v, null);
                        ++var16_21;
                        if (var4_6 == 0) continue;
                        break;
                    }
                }
                for (var16_21 = EffectGpu.bloomN - 2; var16_21 >= 0; --var16_21) {
                    v33 = var11_15;
                    v34 = EffectGpu.a[43];
                    v35 = EffectGpu.up;
                    v36 = EffectGpu.bloom[var16_21];
                    v37 = null;
                    v38 = EffectGpu.bloom;
                    v39 = var16_21 + 1;
lbl231:

                    EffectGpu.blit(v33, v34, v35, v36, v37, v38[v39].v, null);
                    if (var4_6 == 0) {
                        if (var4_6 == 0) continue;
                        break;
                    }
                    break block66;
                }
                var11_15.copyTextureToTexture(var6_5.method_30277(), EffectGpu.scene.t, 0, 0, 0, 0, 0, var7_7, var8_8);
                EffectGpu.composite(var11_15, var0, var6_5, var1_1, var2_2, var10_13, var7_7, var8_8);
            }
            catch (Throwable var9_10) {
                EffectGpu.disable(var9_10);
            }
        }
    }

    
    private static boolean mask(MinecraftClient var0, Camera var1_1, int var2_2, int var3_3) {
        block17: {
            block19: {
                block16: {
                    block15: {
                        block13: {
                            block14: {
                                block18: {
                                    var4_4 = EffectEngine.a;
                                    v0 = var0;
                                    if (var4_4 != 0) break block13;
                                    if (v0.field_1687 != null) break block14;
                                    break block18;
                                    catch (Throwable v1) {
                                        throw v1;
                                    }
                                }
                                v2 = null;
                                break block15;
                            }
                            v0 = var0;
                        }
                        v2 = v0.field_1687.method_8469(EffectGpu.VICTIM[0]);
                    }
                    var5_5 = v2;
                    v4 = var5_5;
                    if (var4_4 != 0) break block16;
                    if (v4 != null) {
                    }
                    v4 = var5_5;
                }
                v6 = EffectEngine.hidden(v4);
                if (var4_4 != 0) // goto
                if (v6 == 0) break block17;
                break block19;
                catch (Throwable v7) {
                    throw v7;
                }
            }
            return false;
        }
        try {
            var12_6 = EffectGpu.a;
            EffectGpu.mask.ensure(var12_6[60], 12, TextureFormat.RGBA8, var2_2, var3_3);
            EffectGpu.maskDepth.ensure(var12_6[54], 8, TextureFormat.DEPTH32, var2_2, var3_3);
            v6 = 12;
lbl49:

            var6_7 = v6;
            var12_6 = EffectGpu.a;
            EffectGpu.blurA.ensure(var12_6[18], var6_7, TextureFormat.RGBA8, Math.max(1, var2_2 >> 1), Math.max(1, var3_3 >> 1));
            EffectGpu.blurB.ensure(var12_6[2], var6_7, TextureFormat.RGBA8, Math.max(1, var2_2 >> 1), Math.max(1, var3_3 >> 1));
            RenderSystem.getDevice().createCommandEncoder().clearColorAndDepthTextures(EffectGpu.mask.t, 0, EffectGpu.maskDepth.t, 1.0);
            RenderSystem.outputColorTextureOverride = EffectGpu.mask.v;
            RenderSystem.outputDepthTextureOverride = EffectGpu.maskDepth.v;
            var7_9 = var0.method_1561();
            var8_10 = var7_9.method_72977(var5_5, var0.method_61966().method_60637(false));
            var8_10.field_53337 = null;
            var8_10.field_61823.clear();
            var8_10.field_61821 = 0;
            var9_11 = var1_1.method_71156();
            var10_12 = new CameraRenderState();
            var10_12.field_63078 = var9_11;
            var10_12.field_63080 = var9_11;
            var10_12.field_63077 = var1_1.method_19328();
            var10_12.field_63081 = new Quaternionf((Quaternionfc)var1_1.method_23767());
            var10_12.field_63079 = true;
            var11_13 = var0.field_1773.method_72911();
            var7_9.method_72976(var8_10, var10_12, var8_10.field_53325 - var9_11.field_1352, var8_10.field_53326 - var9_11.field_1351, var8_10.field_53327 - var9_11.field_1350, new MatrixStack(), (OrderedRenderCommandQueue)var11_13.method_73003());
            var11_13.method_73002();
            var0.method_22940().method_23000().method_22993();
            return true;
        }
        catch (Throwable var6_8) {
            EffectGpu.outlineBroken = true;
            Aurora.LOG.error(EffectGpu.a[52], var6_8);
            return false;
        }
    }

    
    private static void outline(CommandEncoder var0, int var1_1, int var2_2) {
        block19: {
            block18: {
                block17: {
                    var4_3 = Math.max(1.0f, (float)var2_2 / 1080.0f);
                    var3_4 = EffectEngine.a;
                    var5_5 = var0.mapBuffer(EffectGpu.blurX, false, true);
                    try {
                        v0 = Std140Builder.intoBuffer((ByteBuffer)var5_5.data());
                        if (var3_4 == 0) {
                            v0.putVec4(3.0f * var4_3 / (float)var1_1, 0.0f, 0.0f, 0.0f);
                            if (var5_5 == null) break block17;
                        }
                    }
                    catch (Throwable var6_6) {
                        if (var3_4 != 0) throw var6_6;
                        if (var5_5 == null) throw var6_6;
                        try {
                            var5_5.close();
                            throw var6_6;
                        }
                        catch (Throwable var7_10) {
                            var6_6.addSuppressed(var7_10);
                        }
                        throw var6_6;
                    }
                    var5_5.close();
                }
                var5_5 = var0.mapBuffer(EffectGpu.blurY, false, true);
                try {
                    v0 = Std140Builder.intoBuffer((ByteBuffer)var5_5.data()).putVec4(0.0f, 3.0f * var4_3 / (float)var2_2, 0.0f, 0.0f);
lbl26:

                    if (var3_4 == 0) {
                        if (var5_5 == null) break block18;
                    }
                }
                catch (Throwable var6_7) {
                    if (var3_4 != 0) throw var6_7;
                    if (var5_5 == null) throw var6_7;
                    try {
                        var5_5.close();
                        throw var6_7;
                    }
                    catch (Throwable var7_11) {
                        var6_7.addSuppressed(var7_11);
                    }
                    throw var6_7;
                }
                var5_5.close();
            }
            var5_5 = var0.mapBuffer(EffectGpu.outlineParams, false, true);
            try {
                v0 = Std140Builder.intoBuffer((ByteBuffer)var5_5.data());
lbl44:

                var6_8 = v0;
                var6_8.putVec4(var4_3 / (float)var1_1, var4_3 / (float)var2_2, 0.0f, 0.0f);
                var6_8.putVec4(EffectGpu.OUTLINE[0], EffectGpu.OUTLINE[1], EffectGpu.OUTLINE[2], 0.0f);
                if (var3_4 != 0) return;
                var6_8.putVec4(EffectGpu.OUTLINE[3], EffectGpu.OUTLINE[4], EffectGpu.OUTLINE[5], 0.0f);
                var6_8.putVec4(EffectGpu.OUTLINE[6], EffectGpu.OUTLINE[7], EffectGpu.OUTLINE[8], 0.0f);
                if (var5_5 == null) break block19;
            }
            catch (Throwable var6_9) {
                if (var3_4 != 0) throw var6_9;
                if (var5_5 == null) throw var6_9;
                try {
                    var5_5.close();
                    throw var6_9;
                }
                catch (Throwable var7_12) {
                    var6_9.addSuppressed(var7_12);
                }
                throw var6_9;
            }
            var5_5.close();
        }
        var8_13 = EffectGpu.a;
        EffectGpu.blit(var0, var8_13[46], EffectGpu.blur, EffectGpu.blurA, EffectGpu.blurX, EffectGpu.mask.v, null);
        EffectGpu.blit(var0, var8_13[8], EffectGpu.blur, EffectGpu.blurB, EffectGpu.blurY, EffectGpu.blurA.v, null);
        EffectGpu.blit(var0, var8_13[47], EffectGpu.outlinePipe, EffectGpu.fx, EffectGpu.outlineParams, EffectGpu.mask.v, EffectGpu.blurB.v);
    }

    
    private static void composite(CommandEncoder commandEncoder, EffectEngine$Pool effectEngine$Pool, Framebuffer framebuffer, Matrix4f matrix4f, Matrix4f matrix4f2, Vec3d vec3d, int n, int n2) {
        GpuBuffer.MappedView mappedView;
        int n3;
        block40: {
            GpuBuffer.MappedView mappedView2;
            block41: {
                matrix4f2.mul((Matrix4fc)matrix4f, VP);
                VP.invert(INV);
                n3 = EffectEngine.a;
                int n4 = EffectEngine.shockwaves(effectEngine$Pool, WAVES, 4, vec3d.field_1352, vec3d.field_1351, vec3d.field_1350);
                mappedView = commandEncoder.mapBuffer(compositeParams, false, true);
                try {
                    int n5;
                    int n6;
                    Std140Builder std140Builder;
                    block39: {
                        int n7;
                        block38: {
                            Std140Builder std140Builder2;
                            std140Builder = Std140Builder.intoBuffer((ByteBuffer)mappedView.data());
                            for (n6 = 0; n6 < 4; ++n6) {
                                std140Builder2 = std140Builder.putVec4((Vector4fc)INV.getColumn(n6, V4));
                                if (n3 == 0) {
                                    if (n3 == 0) continue;
                                }
                                break block38;
                            }
                            std140Builder.putVec4((float)n, (float)n2, 0.7f, (float)(frame++ % 997) * 1.37f);
                            std140Builder2 = std140Builder.putVec4(-matrix4f.m02(), -matrix4f.m12(), -matrix4f.m22(), 0.0f);
                        }
                        for (n6 = 0; n6 < 4; ++n6) {
                            n7 = n6;
                            if (n3 == 0) {
                                int n8;
                                if (n3 == 0) {
                                    if (n7 >= n4) {
                                        std140Builder.putVec4(0.0f, 0.0f, 0.0f, 0.0f).putVec4(1.0f, 0.0f, 0.0f, 0.0f).putVec4(0.0f, 0.0f, 0.0f, 0.0f);
                                        if (n3 == 0) continue;
                                    }
                                    n8 = n6 * 7;
                                }
                                n5 = n8;
                                float f = WAVES[n5];
                                float f2 = WAVES[n5 + 1];
                                float f3 = WAVES[n5 + 2];
                                VP.transform(V4.set(f, f2, f3, 1.0f));
                                float f4 = (float)Math.sqrt(f * f + f2 * f2 + f3 * f3);
                                float f5 = EffectGpu.V4.w;
                                if (n3 == 0) {
                                    f5 = f5 > 0.01f ? WAVES[n5 + 5] * Math.min(2.0f, 5.0f / Math.max(0.1f, f4)) : 0.0f;
                                }
                                float f6 = f5;
                                std140Builder.putVec4(f, f2, f3, WAVES[n5 + 3]);
                                std140Builder.putVec4(Math.max(0.001f, WAVES[n5 + 4]), f6, WAVES[n5 + 6], 0.0f);
                                std140Builder.putVec4(EffectGpu.V4.x / EffectGpu.V4.w * 0.5f + 0.5f, EffectGpu.V4.y / EffectGpu.V4.w * 0.5f + 0.5f, 0.0f, 0.0f);
                                if (n3 == 0) continue;
                            }
                            break block39;
                        }
                        n7 = n6 = 0;
                    }
                    while (n6 < 4) {
                        block44: {
                            int n9;
                            block42: {
                                block43: {
                                    if (n3 != 0) break block40;
                                    n9 = n6;
                                    if (n3 != 0) break block42;
                                    if (n9 < effectEngine$Pool.lightN) break block43;
                                    std140Builder.putVec4(0.0f, 0.0f, 0.0f, 0.0f).putVec4(0.0f, 0.0f, 0.0f, 0.0f);
                                    if (n3 == 0) break block44;
                                }
                                n9 = n6 * 7;
                            }
                            n5 = n9;
                            std140Builder.putVec4(effectEngine$Pool.lights[n5], effectEngine$Pool.lights[n5 + 1], effectEngine$Pool.lights[n5 + 2], effectEngine$Pool.lights[n5 + 3]);
                            std140Builder.putVec4(effectEngine$Pool.lights[n5 + 4], effectEngine$Pool.lights[n5 + 5], effectEngine$Pool.lights[n5 + 6], 0.0f);
                        }
                        ++n6;
                        if (n3 == 0) continue;
                    }
                    mappedView2 = mappedView;
                    if (n3 != 0) break block41;
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
                if (mappedView2 == null) break block40;
                mappedView2 = mappedView;
            }
            mappedView2.close();
        }
        mappedView = commandEncoder.createRenderPass(EffectGpu::lambda$composite$5, framebuffer.method_71639(), OptionalInt.empty());
        try {
            mappedView.setPipeline(composite);
            RenderSystem.bindDefaultUniforms((RenderPass)mappedView);
            if (n3 != 0) return;
            String[] stringArray = a;
            mappedView.setUniform(stringArray[30], compositeParams);
            mappedView.bindTexture(stringArray[62], EffectGpu.scene.v, EffectGpu.sampler(FilterMode.LINEAR));
            mappedView.bindTexture(stringArray[49], EffectGpu.fx.v, EffectGpu.sampler(FilterMode.LINEAR));
            mappedView.bindTexture(stringArray[5], EffectGpu.bloom[0].v, EffectGpu.sampler(FilterMode.LINEAR));
            mappedView.bindTexture(stringArray[11], framebuffer.method_71640(), EffectGpu.sampler(FilterMode.NEAREST));
            EffectGpu.drawQuad((RenderPass)mappedView);
            if (mappedView == null) return;
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

    
    private static void blit(CommandEncoder commandEncoder, String string, RenderPipeline renderPipeline, EffectGpu$Tex effectGpu$Tex, GpuBuffer gpuBuffer, GpuTextureView gpuTextureView, GpuTextureView gpuTextureView2) {
        RenderPass renderPass = commandEncoder.createRenderPass(() -> EffectGpu.lambda$blit$6(string), effectGpu$Tex.v, OptionalInt.empty());
        int n = EffectEngine.a;
        try {
            block17: {
                block16: {
                    block15: {
                        RenderPass renderPass2;
                        block14: {
                            renderPass2 = renderPass;
                            if (n != 0) break block14;
                            renderPass2.setPipeline(renderPipeline);
                            RenderSystem.bindDefaultUniforms((RenderPass)renderPass);
                            if (gpuBuffer == null) break block15;
                            renderPass2 = renderPass;
                        }
                        String[] stringArray = a;
                        renderPass2.setUniform(stringArray[30], gpuBuffer);
                    }
                    if (gpuTextureView2 != null) break block16;
                    renderPass.bindTexture(a[22], gpuTextureView, EffectGpu.sampler(FilterMode.LINEAR));
                    if (n == 0) break block17;
                }
                renderPass.bindTexture(a[38], gpuTextureView, EffectGpu.sampler(FilterMode.LINEAR));
                renderPass.bindTexture(a[63], gpuTextureView2, EffectGpu.sampler(FilterMode.LINEAR));
            }
            EffectGpu.drawQuad(renderPass);
            if (n != 0) return;
            if (renderPass == null) return;
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
    }

    private static void drawQuad(RenderPass renderPass) {
        RenderSystem.ShapeIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer((VertexFormat.DrawMode)VertexFormat.DrawMode.field_27382);
        renderPass.setVertexBuffer(0, quad);
        renderPass.setIndexBuffer(shapeIndexBuffer.method_68274(6), shapeIndexBuffer.method_31924());
        renderPass.drawIndexed(0, 0, 6, 1);
    }

    private static GpuSampler sampler(FilterMode filterMode) {
        return RenderSystem.getSamplerCache().method_75294(filterMode);
    }

    static void drawDirect(EffectEngine$Pool effectEngine$Pool, Matrix4f matrix4f) {
        MinecraftClient minecraftClient;
        block4: {
            MinecraftClient minecraftClient2;
            block5: {
                minecraftClient2 = MinecraftClient.method_1551();
                int n = EffectEngine.a;
                minecraftClient = minecraftClient2;
                if (n != 0) break block4;
                if (minecraftClient.field_1773 != null) break block5;
                return;
            }
            minecraftClient = minecraftClient2;
        }
        Camera camera = minecraftClient.field_1773.method_19418();
        Vec3d vec3d = camera.method_71156();
        camera.method_23767().transform(RIGHT.set(1.0f, 0.0f, 0.0f));
        camera.method_23767().transform(UP.set(0.0f, 1.0f, 0.0f));
        EffectGpu.draw(effectEngine$Pool, matrix4f, vec3d.field_1352, vec3d.field_1351, vec3d.field_1350, 0.0f, 0.0f, 0.0f);
    }

    static void drawStage(EffectEngine$Pool effectEngine$Pool, Matrix4f matrix4f, Vec3d vec3d) {
        block4: {
            Vector3f vector3f;
            block5: {
                TMP.set((float)vec3d.field_1352, (float)vec3d.field_1351, (float)vec3d.field_1350).normalize();
                int n = EffectEngine.a;
                vector3f = RIGHT.set(EffectGpu.TMP.z, 0.0f, -EffectGpu.TMP.x);
                if (n != 0) break block4;
                if (!(RIGHT.lengthSquared() < 1.0E-6f)) break block5;
                RIGHT.set(1.0f, 0.0f, 0.0f);
            }
            RIGHT.normalize();
            vector3f = TMP.cross((Vector3fc)RIGHT, UP);
        }
        EffectGpu.draw(effectEngine$Pool, matrix4f, 0.0, 0.0, 0.0, (float)vec3d.field_1352, (float)vec3d.field_1351, (float)vec3d.field_1350);
    }

    
    private static void draw(EffectEngine$Pool effectEngine$Pool, Matrix4f matrix4f, double d, double d2, double d3, float f, float f2, float f3) {
        RenderLayer renderLayer = EffectGpu.directLayer();
        VertexConsumerProvider.Immediate immediate = EffectGpu.immediate();
        EffectGpu.OUT.vc = immediate.method_73477(renderLayer);
        EffectGpu.OUT.m = matrix4f;
        try {
            EffectEngine.build(effectEngine$Pool, OUT, d, d2, d3, f, f2, f3, RIGHT, UP, false);
            immediate.method_22993();
        }
        finally {
            EffectGpu.OUT.vc = null;
            EffectGpu.OUT.m = null;
        }
    }

    private static RenderLayer directLayer() {
        block6: {
            RenderLayer renderLayer;
            block5: {
                int n = EffectEngine.a;
                renderLayer = directLayer;
                if (n != 0) break block5;
                if (renderLayer == null) break block6;
                renderLayer = directLayer;
            }
            return renderLayer;
        }
        if (!directBroken) {
            try {
                String[] stringArray = a;
                directLayer = RenderLayer.method_75940((String)stringArray[35], (RenderSetup)RenderSetup.method_75927((RenderPipeline)EffectGpu.spritePipeline(stringArray[27], true)).method_75938());
                return directLayer;
            }
            catch (Throwable throwable) {
                directBroken = true;
                Aurora.LOG.error(a[13], throwable);
            }
        }
        return SoftLayer.layer();
    }

    private static VertexConsumerProvider.Immediate immediate() {
        VertexConsumerProvider.Immediate immediate;
        block4: {
            block5: {
                int n = EffectEngine.a;
                immediate = EffectGpu.immediate;
                if (n != 0) break block4;
                if (immediate != null) break block5;
                EffectGpu.immediate = VertexConsumerProvider.method_22991((BufferAllocator)BUFFER);
            }
            immediate = EffectGpu.immediate;
        }
        return immediate;
    }

    private static boolean irisPack() {
        int n;
        block7: {
            boolean bl;
            block6: {
                n = EffectEngine.a;
                bl = IRIS;
                if (n != 0) break block6;
                if (bl) break block7;
                bl = false;
            }
            return bl;
        }
        try {
            Object object;
            block8: {
                block9: {
                    object = irisInUse;
                    if (n != 0) break block8;
                    if (object != null) break block9;
                    String[] stringArray = a;
                    Class<?> clazz = Class.forName(stringArray[12]);
                    irisApi = clazz.getMethod(stringArray[58], new Class[0]).invoke(null, new Object[0]);
                    irisInUse = clazz.getMethod(stringArray[40], new Class[0]);
                }
                object = irisInUse.invoke(irisApi, new Object[0]);
            }
            return (Boolean)object;
        }
        catch (ReflectiveOperationException | RuntimeException exception) {
            return true;
        }
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
            bl = true;
        }
        broken = bl;
        Aurora.LOG.error(a[57], throwable);
    }

    private static String lambda$blit$6(String string) {
        return a[17] + string;
    }

    private static String lambda$composite$5() {
        return a[0];
    }

    private static String lambda$ready$4() {
        return a[29];
    }

    private static String lambda$ready$3() {
        return a[19];
    }

    private static String lambda$ready$2() {
        return a[21];
    }

    private static String lambda$ready$1() {
        return a[44];
    }

    private static String lambda$ready$0() {
        return a[41];
    }

    
    static {
        block35: {
            block34: {
                block33: {
                    block32: {
                        block31: {
                            block30: {
                                var21 = new String[64];
                                var19_1 = 0;
                                var18_2 = "_p7t\t.dX}ex\u0014\"4Qv,o\u001e#\u001ea,\u007f[!+J%&t\u0016?-R`i;\b*!\u001eq-~[#+Y%$y\u00149!\u0006\\i0i[-\u0005\\i*t\u0016\u0007X}\u001a\u007f\u00148*\f|i*t\u0016\u001c%Su)~\t\u000e]j7~T,+Sg$o$)<\fX}\u001ax\u0014\"4Qv,o\u001e\u0006\\i0i[6\fX}\u001ak\t*\"Wi1~\t\u0005X}\u001an\u000b\fz`5o\u0013\u001c%Su)~\t#P`15\u0012=-Mv-z\u001f*6M+,i\u0012<j_u,5\r\u007fjww,h:?-7{c#~\u0018;dMu7r\u000f*7\u001ep+z\r.-Rd'w\u001ecdXd)w\u0012!#\u001eg$x\u0010o0Q%5w\u001a&*\u001et0z\u001f<\tNl5~\u0017&*[*\fmf u\u001e\u001c%Su)~\t\u0006zL\u0017^8\u001b\n_p7t\t.dX}e\u0006\\i0i[.\u0010_p7t\t.dX}ey\u0017:6\u001e|\tNw }\u0012#0[w\u0010_p7t\t.dX}ey\u0017:6\u001e}\twk\u0016z\u0016?([w\u0004Zj2u\tx}\u0016z\u0016?([w\f|i*t\u0016\u001c%Su)~\t\fz`5o\u0013\u001c%Su)~\t\u0010]j(y\u001a;\u001bX}\u001a\u007f\u0012=!]q\u0006nd7z\u0016<\u0011_p7t\t.dX}et\u000e;(Wk \u0006nd7z\u0016<\u0007X}\u001ay\u0017:6\twk\u0016z\u0016?([w\tHp)p\u001a!)Qa\u0005Mf u\u001e\u0017_p7t\t.\u001b]j(y\u001a;\u001bX}\u001a\u007f\u0012=!]q\nX}\u001at\u000e;(Wk \u000b|i0i(.)Ni i\u000bsd6p(.)Ni i\u0006_p7t\t.\u0011Wv\u0016s\u001a+!LU$x\u0010\u0006*kv \u000e_p7t\t.dX}ej\u000e. \tNl5~\u0017&*[*\u0002Ku\u0013_p7t\t.dX}ex\u0014\"4Qv,o\u001e#\u001ea,\u007f[!+J%&t\u0016?-R`i;\b*!\u001eq-~[#+Y%$y\u00149!\u0006\\i0i[7\u0007Qp1w\u0012!!\bNw*|\t.)\u0011\tx}\u0016z\u0016?([w\u0005Rd<~\t\t]j(y\u001a;\u001bX}\u0017{c#~\u0018;dQp1w\u0012!!\u001ea,h\u001a-([a\u0010_p7t\t.\u001b]j(y\u001a;\u001bX}\nSd6p[+!Nq-\u0004Ww,h\u0006_p7t\t.8{c#~\u0018;d\\i*t\u0016o%Pae\u007f\u0012<0Qw1r\u0014!dZl6z\u0019#!Z)e~\u001d)!]q6;\u001f=%I%5w\u001a&*\u000bY`1R\u0015<0_k&~\u000f]j7~T((_v6D\u0019#-J\u0004Sd6p\u000bsd6p(.)Ni i";
                                var20_3 = "_p7t\t.dX}ex\u0014\"4Qv,o\u001e#\u001ea,\u007f[!+J%&t\u0016?-R`i;\b*!\u001eq-~[#+Y%$y\u00149!\u0006\\i0i[-\u0005\\i*t\u0016\u0007X}\u001a\u007f\u00148*\f|i*t\u0016\u001c%Su)~\t\u000e]j7~T,+Sg$o$)<\fX}\u001ax\u0014\"4Qv,o\u001e\u0006\\i0i[6\fX}\u001ak\t*\"Wi1~\t\u0005X}\u001an\u000b\fz`5o\u0013\u001c%Su)~\t#P`15\u0012=-Mv-z\u001f*6M+,i\u0012<j_u,5\r\u007fjww,h:?-7{c#~\u0018;dMu7r\u000f*7\u001ep+z\r.-Rd'w\u001ecdXd)w\u0012!#\u001eg$x\u0010o0Q%5w\u001a&*\u001et0z\u001f<\tNl5~\u0017&*[*\fmf u\u001e\u001c%Su)~\t\u0006zL\u0017^8\u001b\n_p7t\t.dX}e\u0006\\i0i[.\u0010_p7t\t.dX}ey\u0017:6\u001e|\tNw }\u0012#0[w\u0010_p7t\t.dX}ey\u0017:6\u001e}\twk\u0016z\u0016?([w\u0004Zj2u\tx}\u0016z\u0016?([w\f|i*t\u0016\u001c%Su)~\t\fz`5o\u0013\u001c%Su)~\t\u0010]j(y\u001a;\u001bX}\u001a\u007f\u0012=!]q\u0006nd7z\u0016<\u0011_p7t\t.dX}et\u000e;(Wk \u0006nd7z\u0016<\u0007X}\u001ay\u0017:6\twk\u0016z\u0016?([w\tHp)p\u001a!)Qa\u0005Mf u\u001e\u0017_p7t\t.\u001b]j(y\u001a;\u001bX}\u001a\u007f\u0012=!]q\nX}\u001at\u000e;(Wk \u000b|i0i(.)Ni i\u000bsd6p(.)Ni i\u0006_p7t\t.\u0011Wv\u0016s\u001a+!LU$x\u0010\u0006*kv \u000e_p7t\t.dX}ej\u000e. \tNl5~\u0017&*[*\u0002Ku\u0013_p7t\t.dX}ex\u0014\"4Qv,o\u001e#\u001ea,\u007f[!+J%&t\u0016?-R`i;\b*!\u001eq-~[#+Y%$y\u00149!\u0006\\i0i[7\u0007Qp1w\u0012!!\bNw*|\t.)\u0011\tx}\u0016z\u0016?([w\u0005Rd<~\t\t]j(y\u001a;\u001bX}\u0017{c#~\u0018;dQp1w\u0012!!\u001ea,h\u001a-([a\u0010_p7t\t.\u001b]j(y\u001a;\u001bX}\nSd6p[+!Nq-\u0004Ww,h\u0006_p7t\t.8{c#~\u0018;d\\i*t\u0016o%Pae\u007f\u0012<0Qw1r\u0014!dZl6z\u0019#!Z)e~\u001d)!]q6;\u001f=%I%5w\u001a&*\u000bY`1R\u0015<0_k&~\u000f]j7~T((_v6D\u0019#-J\u0004Sd6p\u000bsd6p(.)Ni i".length();
                                var17_4 = 19;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block30;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "mf u\u001e\u001c%Su)~\t\u000b|i0i(.)Ni i";
                                    var20_3 = "mf u\u001e\u001c%Su)~\t\u000b|i0i(.)Ni i".length();
                                    var17_4 = 12;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block30;
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
                                    break block31;
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
                                        v10 = 62;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 5;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 69;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 27;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 123;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 79;
                                        break;
                                    }
                                    default: {
                                        v10 = 68;
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
                        EffectGpu.a = var21;
                        var9_8 = 3225896902876800288L;
                        var8_9 = new long[2];
                        var12_10 = 0;
                        var13_11 = "4\u008d\u00f9\u00b3B\u0001%%\u0085Rm@B\u0000% ";
                        var14_12 = "4\u008d\u00f9\u00b3B\u0001%%\u0085Rm@B\u0000% ".length();
                        var11_13 = 0;
                        while (true) {
                            break block32;
                            break;
                        }
lbl84:

                        while (true) {
                            var8_9[v11] = (((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L) ^ var9_8;
                            if (var11_13 < var14_12) continue;
                            break block33;
                            break;
                        }
                    }
                    var15_14 = var13_11.substring(var11_13, var11_13 += 8).getBytes("ISO-8859-1");
                    v11 = var12_10++;
                    while (true)
                }
                EffectGpu.BLOOM_LEVELS = (int)var8_9[0];
                var0_15 = 1762371593774695347L;
                var6_16 = new long[4];
                var3_17 = 0;
                var4_18 = "!t\u00bat\u00ab\u00b7\u009d\u008e\\\u00b6\u0099X\u00ff\u0098Q#";
                var5_19 = "!t\u00bat\u00ab\u00b7\u009d\u008e\\\u00b6\u0099X\u00ff\u0098Q#".length();
                var2_20 = 0;
                while (true) {
                    var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
                    v12 = var6_16;
                    v13 = var3_17++;
                    v14 = ((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L;
                    v15 = -1;
                    break block34;
                    break;
                }
lbl111:

                while (true) {
                    v12[v13] = v16;
                    if (var2_20 < var5_19) continue;
                    var4_18 = "EY\u00e4\u00c3q\u00e9\u008aH.\u0093\u009dg\u0006~.j";
                    var5_19 = "EY\u00e4\u00c3q\u00e9\u008aH.\u0093\u009dg\u0006~.j".length();
                    var2_20 = 0;
                    while (true) {
                        var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
                        v12 = var6_16;
                        v13 = var3_17++;
                        v14 = ((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L;
                        v15 = 0;
                        break block34;
                        break;
                    }
                    break;
                }
lbl124:

                while (true) {
                    v12[v13] = v16;
                    if (var2_20 < var5_19) continue;
                    break block35;
                    break;
                }
            }
            v16 = v14 ^ var0_15;
            switch (v15) {
                default: {
                    continue;
                }
                case 0:
lbl135:

                continue;
            }
        }
        EffectGpu.b = var6_16;
        EffectGpu.c = new Long[4];
        var16_6 = EffectGpu.a;
        EffectGpu.VULKAN = FabricLoader.getInstance().isModLoaded(var16_6[33]);
        EffectGpu.IRIS = FabricLoader.getInstance().isModLoaded(var16_6[55]);
        EffectGpu.BLIT = Identifier.method_60655((String)var16_6[39], (String)var16_6[59]);
        EffectGpu.FX_SHADER = Identifier.method_60655((String)var16_6[39], (String)var16_6[6]);
        EffectGpu.fx = new EffectGpu$Tex();
        EffectGpu.scene = new EffectGpu$Tex();
        EffectGpu.mask = new EffectGpu$Tex();
        EffectGpu.maskDepth = new EffectGpu$Tex();
        EffectGpu.blurA = new EffectGpu$Tex();
        EffectGpu.blurB = new EffectGpu$Tex();
        EffectGpu.bloom = new EffectGpu$Tex[5];
        EffectGpu.BUFFER = new BufferAllocator((int)var8_9[1]);
        EffectGpu.OUT = new EffectGpu$VcOut();
        EffectGpu.RIGHT = new Vector3f();
        EffectGpu.UP = new Vector3f();
        EffectGpu.TMP = new Vector3f();
        EffectGpu.VP = new Matrix4f();
        EffectGpu.INV = new Matrix4f();
        EffectGpu.V4 = new Vector4f();
        EffectGpu.WAVES = new float[28];
        EffectGpu.OUTLINE = new float[9];
        EffectGpu.VICTIM = new int[1];
        for (var23_22 = 0; var23_22 < 5; ++var23_22) {
            EffectGpu.bloom[var23_22] = new EffectGpu$Tex();
        }
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x2FEB) & Short.MAX_VALUE;
        if (c[n2] == null) {
            EffectGpu.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
