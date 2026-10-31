package dev.quelchi.aurora.glass.render.minecraft;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import dev.quelchi.aurora.glass.GlassKit;
import dev.quelchi.aurora.glass.render.GlassQuality;
import dev.quelchi.aurora.glass.render.minecraft.GlassData;
import dev.quelchi.aurora.glass.render.minecraft.GlassPipelines;
import dev.quelchi.aurora.glass.render.minecraft.GlassSurfaceState;
import dev.quelchi.aurora.util.Gpu;
import java.lang.invoke.LambdaMetafactory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.function.Consumer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.TextureSetup;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public final class GlassGpu {
    private static final int TEXTURE_USAGE;
    private static final long MAX_SCENE_PIXELS;
    private static final int MAX_SURFACES;
    private static final int DATA_WIDTH;
    private static final boolean KEEP_TEXTURES;
    private static final ArrayList<GlassSurfaceState> surfaces;
    private static GpuDevice device;
    private static GpuTexture data;
    private static GpuTextureView dataView;
    private static GpuTexture neutral;
    private static GpuTextureView neutralView;
    private static GpuTexture scene;
    private static GpuTextureView sceneView;
    private static GpuTexture blurA;
    private static GpuTextureView blurAView;
    private static GpuTexture blurB;
    private static GpuTextureView blurBView;
    private static GpuBuffer horizontal;
    private static GpuBuffer vertical;
    private static ByteBuffer upload;
    private static GpuTexture lastSource;
    private static int lastWidth;
    private static int lastHeight;
    private static volatile boolean invalidated;
    private static boolean shaderBroken;
    private static boolean blurBroken;
    private static boolean captureBroken;
    private static boolean sampled;
    private static boolean blurred;
    private static int captures;
    private static int blurPasses;
    private static int scale;
    private static int idle;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private GlassGpu() {
    }

    public static void invalidate() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = GlassSurfaceState.a;
                bl = KEEP_TEXTURES;
                if (bl2) break block2;
                if (!bl) break block3;
                return;
            }
            bl = true;
        }
        invalidated = bl;
    }

    
    public static void prepare(GuiRenderState var0) {
        block33: {
            block34: {
                block42: {
                    block45: {
                        block44: {
                            block43: {
                                block32: {
                                    block30: {
                                        block31: {
                                            block41: {
                                                block40: {
                                                    block39: {
                                                        RenderSystem.assertOnRenderThread();
                                                        var1_1 = GlassSurfaceState.a;
                                                        var2_2 = RenderSystem.getDevice();
                                                        if (var1_1) break block30;
                                                        if (GlassGpu.invalidated) // goto
                                                        break block39;
                                                        catch (RuntimeException v0) {
                                                            throw v0;
                                                        }
                                                    }
                                                    v1 = GlassGpu.device;
                                                    if (var1_1) break block31;
                                                    break block40;
                                                    catch (RuntimeException v2) {
                                                        throw v2;
                                                    }
                                                }
                                                if (v1 == var2_2) break block32;
                                                break block41;
                                                catch (RuntimeException v3) {
                                                    throw v3;
                                                }
                                            }
                                            GlassGpu.invalidated = false;
                                            GlassGpu.release();
                                            v1 = var2_2;
                                        }
                                        GlassGpu.device = v1;
                                        GlassGpu.captureBroken = false;
                                        GlassGpu.blurBroken = false;
                                    }
                                    GlassGpu.shaderBroken = false;
                                }
                                GlassGpu.surfaces.clear();
                                GlassGpu.blurred = false;
                                GlassGpu.sampled = false;
                                GlassGpu.blurPasses = 0;
                                if (var1_1) break block42;
                                GlassGpu.captures = 0;
                                var0.method_71060((Consumer<SimpleGuiElementRenderState>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$prepare$0(net.minecraft.client.gui.render.state.SimpleGuiElementRenderState ), (Lnet/minecraft/client/gui/render/state/SimpleGuiElementRenderState;)V)(), GuiRenderState.LayerFilter.field_60315);
                                if (!GlassGpu.surfaces.isEmpty()) // goto
                                break block43;
                                catch (RuntimeException v5) {
                                    throw v5;
                                }
                            }
                            if (++GlassGpu.idle != 120) break block33;
                            break block44;
                            catch (RuntimeException v6) {
                                throw v6;
                            }
                        }
                        GlassGpu.close();
                        if (!var1_1) break block33;
                        break block45;
                        catch (RuntimeException v7) {
                            throw v7;
                        }
                    }
                    GlassGpu.idle = 0;
                    if (!var1_1) {
                    }
                }
                if (GlassGpu.shaderBroken) break block34;
                try {
                    block38: {
                        block46: {
                            block35: {
                                GlassGpu.initialize();
lbl75:

                                var3_3 = false;
                                var4_6 = false;
                                for (GlassSurfaceState var6_9 : GlassGpu.surfaces) {
                                    block37: {
                                        block36: {
                                            v9 = GlassGpu.wantsScene(var6_9);
                                            if (var1_1) break block35;
                                            var7_10 = v9;
                                            v10 = var3_3;
                                            v11 = var7_10;
                                            if (!var1_1) {
                                                var3_3 = v10 | v11;
                                                v10 = var4_6;
                                                if (!var7_10) break block36;
                                                v11 = GlassGpu.wantsBlur(var6_9);
                                            }
                                            if (var1_1) break block37;
                                            if (!v11) break block36;
                                            v11 = true;
                                            break block37;
                                        }
                                        v11 = false;
                                    }
                                    var4_6 = v10 | v11;
                                    if (!var1_1) continue;
                                }
                                v9 = var3_3;
                            }
                            if (!v9) break block38;
                            if (MinecraftClient.method_1551().field_1687 == null) break block38;
                            break block46;
                            catch (RuntimeException v14) {
                                throw v14;
                            }
                        }
                        GlassGpu.capture(var4_6);
                    }
                    GlassGpu.submit();
                    return;
                }
                catch (RuntimeException var3_4) {
                    GlassGpu.shaderBroken = true;
                    GlassGpu.release();
                    GlassKit.LOG.error(GlassGpu.a[10], (Throwable)var3_4);
                }
            }
            for (GlassSurfaceState var4_7 : GlassGpu.surfaces) {
                var4_7.prepare(-1, TextureSetup.method_70899());
                var4_7.stats.prepared(GlassQuality.BASIC, GlassGpu.captures, GlassGpu.blurPasses, 0);
                if (!var1_1) continue;
            }
        }
    }

    private static boolean wantsScene(GlassSurfaceState glassSurfaceState) {
        boolean bl;
        block8: {
            block7: {
                GlassSurfaceState glassSurfaceState2;
                block6: {
                    boolean bl2 = GlassSurfaceState.a;
                    glassSurfaceState2 = glassSurfaceState;
                    if (bl2) break block6;
                    if (!glassSurfaceState2.backdrop) break block7;
                    glassSurfaceState2 = glassSurfaceState;
                }
                if (glassSurfaceState2.requested == GlassQuality.TRANSLUCENT_GLASS) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean wantsBlur(GlassSurfaceState glassSurfaceState) {
        GlassSurfaceState glassSurfaceState2;
        block11: {
            boolean bl;
            block10: {
                bl = GlassSurfaceState.a;
                glassSurfaceState2 = glassSurfaceState;
                if (bl) break block10;
                if (!(glassSurfaceState2.style.blur() > 0.0f)) return false;
                glassSurfaceState2 = glassSurfaceState;
            }
            if (bl) break block11;
            if (glassSurfaceState2.requested == GlassQuality.FULL_GLASS) return true;
            glassSurfaceState2 = glassSurfaceState;
        }
        if (glassSurfaceState2.requested != GlassQuality.BLURRED_GLASS) return false;
        return true;
    }

    private static void initialize() {
        block5: {
            GpuTexture gpuTexture;
            block4: {
                boolean bl = GlassSurfaceState.a;
                gpuTexture = data;
                if (bl) break block4;
                if (gpuTexture != null) break block5;
                GlassGpu.requirePipeline(GlassPipelines.SURFACE);
                upload = MemoryUtil.memAlloc((int)GlassGpu.a(11344, 8673148644315028149L)).order(ByteOrder.LITTLE_ENDIAN);
                data = device.createTexture(a[0], 13, TextureFormat.RGBA8, 36, 1024, 1, 1);
                dataView = device.createTextureView(data);
                gpuTexture = device.createTexture(a[14], 13, TextureFormat.RGBA8, 1, 1, 1, 1);
            }
            neutral = gpuTexture;
            neutralView = device.createTextureView(neutral);
            device.createCommandEncoder().clearColorTexture(neutral, GlassGpu.a(11258, 3867421696762666269L));
        }
    }

    
    private static void capture(boolean bl) {
        boolean bl2;
        block43: {
            boolean bl3;
            block41: {
                block42: {
                    bl2 = GlassSurfaceState.a;
                    bl3 = KEEP_TEXTURES;
                    if (bl2) break block41;
                    if (bl3) break block42;
                    bl3 = Gpu.intel();
                    if (bl2) break block41;
                    if (!bl3) break block43;
                }
                bl3 = true;
            }
            captureBroken = bl3;
            return;
        }
        try {
            int n;
            int n2;
            GpuTexture gpuTexture;
            block54: {
                GpuTexture gpuTexture2;
                block53: {
                    block51: {
                        block52: {
                            block49: {
                                block50: {
                                    int n3;
                                    block47: {
                                        block48: {
                                            int n4;
                                            block46: {
                                                block44: {
                                                    block45: {
                                                        GpuTexture gpuTexture3 = gpuTexture = MinecraftClient.method_1551().method_1522().method_30277();
                                                        if (!bl2) {
                                                            if (gpuTexture3 == null) return;
                                                            gpuTexture3 = gpuTexture;
                                                        }
                                                        if (!bl2) {
                                                            if (gpuTexture3.isClosed()) {
                                                                return;
                                                            }
                                                            gpuTexture3 = gpuTexture;
                                                        }
                                                        n2 = gpuTexture3.getWidth(0);
                                                        n = gpuTexture.getHeight(0);
                                                        if (bl2) break block44;
                                                        if (gpuTexture != lastSource) break block45;
                                                        n4 = n2;
                                                        if (bl2) break block46;
                                                        if (n4 != lastWidth) break block45;
                                                        n3 = n;
                                                        if (bl2) break block47;
                                                        if (n3 == lastHeight) break block48;
                                                    }
                                                    GlassGpu.releaseScene();
                                                    lastSource = gpuTexture;
                                                    lastWidth = n2;
                                                    lastHeight = n;
                                                }
                                                n4 = 0;
                                            }
                                            captureBroken = n4;
                                        }
                                        n3 = captureBroken;
                                    }
                                    if (!bl2) {
                                        if (n3 != 0) {
                                            return;
                                        }
                                        long l = (long)n2 * (long)n - GlassGpu.b(6582, 2356940921638822198L);
                                        n3 = l == 0L ? 0 : (l < 0L ? -1 : 1);
                                    }
                                    if (n3 > 0) break block49;
                                    gpuTexture2 = gpuTexture;
                                    if (bl2) break block50;
                                    if (gpuTexture2.getFormat() != TextureFormat.RGBA8) break block49;
                                    gpuTexture2 = gpuTexture;
                                }
                                if (bl2) break block51;
                                if ((gpuTexture2.usage() & 2) != 0) break block52;
                            }
                            String string = String.valueOf(gpuTexture.getFormat());
                            int n5 = n;
                            int n6 = n2;
                            String[] stringArray = a;
                            throw new IllegalStateException(stringArray[9] + n6 + "x" + n5 + " " + string);
                        }
                        gpuTexture2 = scene;
                    }
                    if (bl2) break block53;
                    if (gpuTexture2 != null) break block54;
                    gpuTexture2 = device.createTexture(a[13], 13, TextureFormat.RGBA8, n2, n, 1, 1);
                }
                scene = gpuTexture2;
                sceneView = device.createTextureView(scene);
            }
            CommandEncoder commandEncoder = device.createCommandEncoder();
            commandEncoder.copyTextureToTexture(gpuTexture, scene, 0, 0, 0, 0, 0, n2, n);
            ++captures;
            boolean bl4 = true;
            if (!bl2) {
                sampled = bl4;
                if (!bl) return;
                bl4 = blurBroken;
            }
            if (bl4) return;
            GlassGpu.blur(commandEncoder, n2, n);
            return;
        }
        catch (RuntimeException runtimeException) {
            captureBroken = true;
            blurred = false;
            sampled = false;
            GlassGpu.releaseScene();
            GlassKit.LOG.warn(a[2], (Throwable)runtimeException);
        }
    }

    
    private static void blur(CommandEncoder commandEncoder, int n, int n2) {
        boolean bl = GlassSurfaceState.a;
        try {
            int n3;
            block10: {
                block11: {
                    int n4;
                    block8: {
                        block9: {
                            n4 = Math.max(1, Math.round(MinecraftClient.method_1551().method_22683().method_4495()));
                            if (bl) break block8;
                            if (blurA == null) break block9;
                            n3 = scale;
                            if (bl) break block10;
                            if (n3 == n4) break block11;
                        }
                        GlassGpu.releaseBlur();
                        GlassGpu.requirePipeline(GlassPipelines.BLUR);
                    }
                    int n5 = Math.max(1, (n + 3) / 4);
                    int n6 = Math.max(1, (n2 + 3) / 4);
                    String[] stringArray = a;
                    blurA = device.createTexture(stringArray[7], 13, TextureFormat.RGBA8, n5, n6, 1, 1);
                    blurAView = device.createTextureView(blurA);
                    blurB = device.createTexture(stringArray[3], 13, TextureFormat.RGBA8, n5, n6, 1, 1);
                    blurBView = device.createTextureView(blurB);
                    scale = n4;
                    horizontal = GlassGpu.config(2.0f * (float)scale / (float)n, 0.0f);
                    vertical = GlassGpu.config(0.0f, 2.0f * (float)scale / (float)n2);
                }
                GlassGpu.pass(commandEncoder, sceneView, blurAView, horizontal);
                ++blurPasses;
                GlassGpu.pass(commandEncoder, blurAView, blurBView, vertical);
                ++blurPasses;
                n3 = 1;
            }
            blurred = n3;
            return;
        }
        catch (RuntimeException runtimeException) {
            blurBroken = true;
            blurred = false;
            GlassGpu.releaseBlur();
            GlassKit.LOG.warn(a[11], (Throwable)runtimeException);
        }
    }

    private static GpuBuffer config(float f, float f2) {
        GpuBuffer gpuBuffer;
        block9: {
            MemoryStack memoryStack;
            block8: {
                MemoryStack memoryStack2 = MemoryStack.stackPush();
                boolean bl = GlassSurfaceState.a;
                try {
                    ByteBuffer byteBuffer = memoryStack2.malloc(16).order(ByteOrder.nativeOrder());
                    byteBuffer.putFloat(f).putFloat(f2).putFloat(0.0f).putFloat(0.0f).flip();
                    gpuBuffer = device.createBuffer(GlassGpu::lambda$config$1, 128, byteBuffer);
                }
                catch (Throwable throwable) {
                    block10: {
                        if (bl || memoryStack2 == null) break block10;
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
                if (bl) break block8;
                if (memoryStack == null) break block9;
                memoryStack = memoryStack2;
            }
            memoryStack.close();
        }
        return gpuBuffer;
    }

    private static void pass(CommandEncoder commandEncoder, GpuTextureView gpuTextureView, GpuTextureView gpuTextureView2, GpuBuffer gpuBuffer) {
        block7: {
            RenderPass renderPass;
            block6: {
                RenderPass renderPass2 = commandEncoder.createRenderPass(GlassGpu::lambda$pass$2, gpuTextureView2, OptionalInt.empty());
                boolean bl = GlassSurfaceState.a;
                try {
                    renderPass2.setPipeline(GlassPipelines.BLUR);
                    String[] stringArray = a;
                    renderPass2.bindTexture(stringArray[4], gpuTextureView, RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR));
                    renderPass = renderPass2;
                    if (bl) break block6;
                    renderPass.setUniform(stringArray[8], gpuBuffer);
                    renderPass2.draw(0, 3);
                    if (renderPass2 == null) break block7;
                    renderPass = renderPass2;
                }
                catch (Throwable throwable) {
                    block8: {
                        if (bl || renderPass2 == null) break block8;
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
    }

    private static void submit() {
        TextureSetup textureSetup;
        int n;
        GlassSurfaceState glassSurfaceState22;
        boolean bl;
        block50: {
            int n2 = Math.max(1, Math.round(MinecraftClient.method_1551().method_22683().method_4495()));
            bl = GlassSurfaceState.a;
            upload.clear();
            for (GlassSurfaceState glassSurfaceState22 : surfaces) {
                block61: {
                    float f;
                    int n3;
                    block62: {
                        float f2;
                        block60: {
                            GlassSurfaceState glassSurfaceState;
                            block58: {
                                block59: {
                                    int n4;
                                    block57: {
                                        block55: {
                                            block56: {
                                                block54: {
                                                    int n5;
                                                    block53: {
                                                        block52: {
                                                            block51: {
                                                                if (bl) break block50;
                                                                n5 = sampled;
                                                                if (bl) break block51;
                                                                if (n5 == 0) break block52;
                                                                n5 = GlassGpu.wantsScene(glassSurfaceState22);
                                                            }
                                                            if (bl) break block53;
                                                            if (n5 == 0) break block52;
                                                            n5 = 1;
                                                            break block53;
                                                        }
                                                        n5 = 0;
                                                    }
                                                    n3 = n5;
                                                    n4 = n3;
                                                    if (bl) break block54;
                                                    if (n4 == 0) break block55;
                                                    n4 = blurred;
                                                }
                                                if (bl) break block56;
                                                if (n4 == 0) break block55;
                                                n4 = GlassGpu.wantsBlur(glassSurfaceState22);
                                            }
                                            if (bl) break block57;
                                            if (n4 == 0) break block55;
                                            n4 = 1;
                                            break block57;
                                        }
                                        n4 = 0;
                                    }
                                    n = n4;
                                    glassSurfaceState = glassSurfaceState22;
                                    if (bl) break block58;
                                    if (glassSurfaceState.requested != GlassQuality.BLURRED_GLASS) break block59;
                                    f2 = 0.0f;
                                    break block60;
                                }
                                glassSurfaceState = glassSurfaceState22;
                            }
                            f2 = glassSurfaceState.style.refraction();
                        }
                        f = f2;
                        if (bl) break block61;
                        if (glassSurfaceState22.requested != GlassQuality.REDUCED_GLASS) break block62;
                        f *= 0.35f;
                    }
                    GlassData.put(upload, glassSurfaceState22, n3 != 0, n != 0, f, n2);
                }
                if (!bl) continue;
            }
            upload.flip();
            device.createCommandEncoder().writeToTexture(data, upload, NativeImage.Format.field_4997, 0, 0, 0, 0, 36, surfaces.size());
        }
        GpuSampler gpuSampler = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
        glassSurfaceState22 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
        TextureSetup textureSetup3 = textureSetup;
        textureSetup3 = textureSetup;
        GpuTextureView gpuTextureView = sampled ? sceneView : neutralView;
        textureSetup2(gpuTextureView, blurred ? blurBView : neutralView, dataView, gpuSampler, gpuSampler, (GpuSampler)glassSurfaceState22);
        TextureSetup textureSetup4 = textureSetup3;
        for (n = 0; n < surfaces.size(); ++n) {
            GlassQuality glassQuality;
            GlassSurfaceState glassSurfaceState4;
            block72: {
                block73: {
                    block74: {
                        boolean bl2;
                        block70: {
                            boolean bl3;
                            block71: {
                                boolean bl4;
                                boolean bl5;
                                block69: {
                                    block67: {
                                        block68: {
                                            block66: {
                                                boolean bl6;
                                                block65: {
                                                    block64: {
                                                        block63: {
                                                            glassSurfaceState4 = surfaces.get(n);
                                                            bl6 = sampled;
                                                            if (bl) break block63;
                                                            if (!bl6) break block64;
                                                            bl6 = GlassGpu.wantsScene(glassSurfaceState4);
                                                        }
                                                        if (bl) break block65;
                                                        if (!bl6) break block64;
                                                        bl6 = true;
                                                        break block65;
                                                    }
                                                    bl6 = false;
                                                }
                                                bl5 = bl6;
                                                bl4 = bl5;
                                                if (bl) break block66;
                                                if (!bl4) break block67;
                                                bl4 = blurred;
                                            }
                                            if (bl) break block68;
                                            if (!bl4) break block67;
                                            bl4 = GlassGpu.wantsBlur(glassSurfaceState4);
                                        }
                                        if (bl) break block69;
                                        if (!bl4) break block67;
                                        bl4 = true;
                                        break block69;
                                    }
                                    bl4 = false;
                                }
                                bl3 = bl4;
                                bl2 = bl5;
                                if (bl) break block70;
                                if (bl2) break block71;
                                glassQuality = GlassQuality.TRANSLUCENT_GLASS;
                                break block72;
                            }
                            bl2 = bl3;
                        }
                        if (!bl2) break block73;
                        glassQuality = glassSurfaceState4.requested;
                        if (bl) break block72;
                        if (glassQuality != GlassQuality.BLURRED_GLASS) break block74;
                        glassQuality = GlassQuality.BLURRED_GLASS;
                        break block72;
                    }
                    glassQuality = GlassQuality.FULL_GLASS;
                    break block72;
                }
                glassQuality = GlassQuality.REDUCED_GLASS;
            }
            GlassQuality glassQuality2 = glassQuality;
            glassSurfaceState4.prepare(n, textureSetup4);
            glassSurfaceState4.stats.prepared(glassQuality2, captures, blurPasses, GlassGpu.targetCount());
            if (!bl) continue;
        }
    }

    private static int targetCount() {
        int n = scene == null ? 0 : 1;
        int n2 = blurA == null ? 0 : 1;
        int n3 = n + n2;
        int n4 = blurB == null ? 0 : 1;
        return n3 + n4;
    }

    private static void requirePipeline(RenderPipeline renderPipeline) {
        if (!device.precompilePipeline(renderPipeline).isValid()) {
            throw new IllegalStateException(a[6] + String.valueOf(renderPipeline.getLocation()));
        }
    }

    public static void close() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = GlassSurfaceState.a;
                bl = KEEP_TEXTURES;
                if (bl2) break block2;
                if (!bl) break block3;
                return;
            }
            RenderSystem.assertOnRenderThread();
            GlassGpu.release();
            surfaces.clear();
            device = null;
            bl = true;
        }
        invalidated = bl;
    }

    private static void release() {
        block21: {
            ByteBuffer byteBuffer;
            block20: {
                boolean bl;
                block19: {
                    GpuTexture gpuTexture;
                    block18: {
                        block17: {
                            GpuTextureView gpuTextureView;
                            block16: {
                                block15: {
                                    GpuTexture gpuTexture2;
                                    block14: {
                                        block13: {
                                            block12: {
                                                bl = GlassSurfaceState.a;
                                                GlassGpu.releaseScene();
                                                if (bl) break block12;
                                                if (dataView == null) break block13;
                                                dataView.close();
                                            }
                                            dataView = null;
                                        }
                                        gpuTexture2 = data;
                                        if (bl) break block14;
                                        if (gpuTexture2 == null) break block15;
                                        gpuTexture2 = data;
                                    }
                                    gpuTexture2.close();
                                    data = null;
                                }
                                gpuTextureView = neutralView;
                                if (bl) break block16;
                                if (gpuTextureView == null) break block17;
                                gpuTextureView = neutralView;
                            }
                            gpuTextureView.close();
                            neutralView = null;
                        }
                        gpuTexture = neutral;
                        if (bl) break block18;
                        if (gpuTexture == null) break block19;
                        gpuTexture = neutral;
                    }
                    gpuTexture.close();
                    neutral = null;
                }
                byteBuffer = upload;
                if (bl) break block20;
                if (byteBuffer == null) break block21;
                byteBuffer = upload;
            }
            MemoryUtil.memFree((Buffer)byteBuffer);
            upload = null;
        }
        lastSource = null;
        blurred = false;
        sampled = false;
    }

    private static void releaseScene() {
        block9: {
            GpuTexture gpuTexture;
            block8: {
                boolean bl;
                block7: {
                    block6: {
                        bl = GlassSurfaceState.a;
                        GlassGpu.releaseBlur();
                        if (bl) break block6;
                        if (sceneView == null) break block7;
                        sceneView.close();
                    }
                    sceneView = null;
                }
                gpuTexture = scene;
                if (bl) break block8;
                if (gpuTexture == null) break block9;
                gpuTexture = scene;
            }
            gpuTexture.close();
            scene = null;
        }
    }

    private static void releaseBlur() {
        block25: {
            GpuTexture gpuTexture;
            block24: {
                boolean bl;
                block23: {
                    GpuTextureView gpuTextureView;
                    block22: {
                        block21: {
                            GpuTexture gpuTexture2;
                            block20: {
                                block19: {
                                    GpuTextureView gpuTextureView2;
                                    block18: {
                                        block17: {
                                            GpuBuffer gpuBuffer;
                                            block16: {
                                                block14: {
                                                    block15: {
                                                        bl = GlassSurfaceState.a;
                                                        gpuBuffer = horizontal;
                                                        if (bl) break block14;
                                                        if (gpuBuffer == null) break block15;
                                                        horizontal.close();
                                                        horizontal = null;
                                                    }
                                                    gpuBuffer = vertical;
                                                }
                                                if (bl) break block16;
                                                if (gpuBuffer == null) break block17;
                                                gpuBuffer = vertical;
                                            }
                                            gpuBuffer.close();
                                            vertical = null;
                                        }
                                        gpuTextureView2 = blurAView;
                                        if (bl) break block18;
                                        if (gpuTextureView2 == null) break block19;
                                        gpuTextureView2 = blurAView;
                                    }
                                    gpuTextureView2.close();
                                    blurAView = null;
                                }
                                gpuTexture2 = blurA;
                                if (bl) break block20;
                                if (gpuTexture2 == null) break block21;
                                gpuTexture2 = blurA;
                            }
                            gpuTexture2.close();
                            blurA = null;
                        }
                        gpuTextureView = blurBView;
                        if (bl) break block22;
                        if (gpuTextureView == null) break block23;
                        gpuTextureView = blurBView;
                    }
                    gpuTextureView.close();
                    blurBView = null;
                }
                gpuTexture = blurB;
                if (bl) break block24;
                if (gpuTexture == null) break block25;
                gpuTexture = blurB;
            }
            gpuTexture.close();
            blurB = null;
        }
    }

    private static String lambda$pass$2() {
        return a[12];
    }

    private static String lambda$config$1() {
        return a[1];
    }

    
    private static void lambda$prepare$0(SimpleGuiElementRenderState simpleGuiElementRenderState) {
        block10: {
            GlassSurfaceState glassSurfaceState;
            block11: {
                GlassSurfaceState glassSurfaceState2;
                block12: {
                    ArrayList<GlassSurfaceState> arrayList;
                    boolean bl;
                    block13: {
                        SimpleGuiElementRenderState simpleGuiElementRenderState2;
                        block9: {
                            bl = GlassSurfaceState.a;
                            simpleGuiElementRenderState2 = simpleGuiElementRenderState;
                            if (bl) break block9;
                            if (!(simpleGuiElementRenderState2 instanceof GlassSurfaceState)) break block10;
                            simpleGuiElementRenderState2 = simpleGuiElementRenderState;
                        }
                        glassSurfaceState = glassSurfaceState2 = (GlassSurfaceState)simpleGuiElementRenderState2;
                        if (bl) break block11;
                        if (glassSurfaceState.requested == GlassQuality.BASIC) break block12;
                        arrayList = surfaces;
                        if (bl) break block13;
                        if (arrayList.size() >= 1024) break block12;
                        arrayList = surfaces;
                    }
                    arrayList.add(glassSurfaceState2);
                    if (!bl) break block10;
                }
                glassSurfaceState = glassSurfaceState2;
            }
            glassSurfaceState.stats.prepared(GlassQuality.BASIC, 0, 0, 0);
        }
    }

    
    static {
        block32: {
            block31: {
                block30: {
                    block29: {
                        block28: {
                            block27: {
                                var21 = new String[15];
                                var19_1 = 0;
                                var18_2 = "\u0015\fu4WU/3\u0014q5M\u0014.!\u000f\u0015\fu4WU >\u0015fgW\u0001'\"a\u0015\fu4WU11\u0005z\"\u0004\u0016#\"\u0014a5AU7<\u0001b&M\u0019#0\fq|\u0004\u00001;\u000esgE\u001b#>\u0019`.GU%>\u0001g4\u0004\u0000,&\txgP\u001405\u0005`gV\u00102>\u0001w\"I\u0010,&@{5\u0004\u0007'!\u000fa5G\u0010b \u0005x(E\u0011\u0013\u0015\fu4WU >\u0015fgR\u00100&\tw&H\b\u0001\u0001y7H\u00100b\t$\u0015x,E\u001b/=\u0004\u0018;\u000eb&H\u001c&r\u0013|&@\u00100r\u0010}7A\u0019+<\u00054\u0015\u0015\fu4WU >\u0015fgL\u001a0;\u001a{)P\u0014.\n\u0010\fa5g\u001a,4\ts\u001a'\u000eg2T\u0005- \u0014q#\u0004\u0006!7\u000eqgP\u001405\u0005`}\u0004S\u0015\fu4WU1'\u0012r&G\u0010b\"\td\"H\u001c,7Op&P\u0014b'\u0010x(E\u0011b4\u0001}+A\u0011yr\u0015g.J\u0012b0\u0001g.GU23\u000eq+WU7<\u0014}+\u0004\u0007'!\u000fa5G\u0010b \u0005x(E\u0011W\u0015\fu4WU1:\u0001f\"@U >\u0015fgT\u001c27\f})AU$3\tx\"@Nb9\u0005q7M\u001b%r\u0013|&V\u0005b!\u0003q)AU07\u0006f&G\u0001+=\u000e42J\u0001+>@f\"W\u001a7 \u0003qgV\u0010.=\u0001p\u0011\u0015\fu4WU1:\u0001f\"@U >\u0015f";
                                var20_3 = "\u0015\fu4WU/3\u0014q5M\u0014.!\u000f\u0015\fu4WU >\u0015fgW\u0001'\"a\u0015\fu4WU11\u0005z\"\u0004\u0016#\"\u0014a5AU7<\u0001b&M\u0019#0\fq|\u0004\u00001;\u000esgE\u001b#>\u0019`.GU%>\u0001g4\u0004\u0000,&\txgP\u001405\u0005`gV\u00102>\u0001w\"I\u0010,&@{5\u0004\u0007'!\u000fa5G\u0010b \u0005x(E\u0011\u0013\u0015\fu4WU >\u0015fgR\u00100&\tw&H\b\u0001\u0001y7H\u00100b\t$\u0015x,E\u001b/=\u0004\u0018;\u000eb&H\u001c&r\u0013|&@\u00100r\u0010}7A\u0019+<\u00054\u0015\u0015\fu4WU >\u0015fgL\u001a0;\u001a{)P\u0014.\n\u0010\fa5g\u001a,4\ts\u001a'\u000eg2T\u0005- \u0014q#\u0004\u0006!7\u000eqgP\u001405\u0005`}\u0004S\u0015\fu4WU1'\u0012r&G\u0010b\"\td\"H\u001c,7Op&P\u0014b'\u0010x(E\u0011b4\u0001}+A\u0011yr\u0015g.J\u0012b0\u0001g.GU23\u000eq+WU7<\u0014}+\u0004\u0007'!\u000fa5G\u0010b \u0005x(E\u0011W\u0015\fu4WU1:\u0001f\"@U >\u0015fgT\u001c27\f})AU$3\tx\"@Nb9\u0005q7M\u001b%r\u0013|&V\u0005b!\u0003q)AU07\u0006f&G\u0001+=\u000e42J\u0001+>@f\"W\u001a7 \u0003qgV\u0010.=\u0001p\u0011\u0015\fu4WU1:\u0001f\"@U >\u0015f".length();
                                var17_4 = 15;
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
                                    var18_2 = "\u0015\fu4WU11\u0005z\"\r\u0015\fu4WU,7\u0015`5E\u0019";
                                    var20_3 = "\u0015\fu4WU11\u0005z\"\r\u0015\fu4WU,7\u0015`5E\u0019".length();
                                    var17_4 = 11;
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
                                        v10 = 82;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 96;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 20;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 71;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 36;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 117;
                                        break;
                                    }
                                    default: {
                                        v10 = 66;
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
                        GlassGpu.a = var21;
                        var8_7 = 3509711589683407219L;
                        var14_8 = new long[5];
                        var11_9 = 0;
                        var12_10 = "\u00aay\u0015\u00a1x+\u00d1^\u00ad\u009f\u00f8*\"D~\u0006Q\u00d6\u00ef\u00a9+\u00aa3\u00c6";
                        var13_11 = "\u00aay\u0015\u00a1x+\u00d1^\u00ad\u009f\u00f8*\"D~\u0006Q\u00d6\u00ef\u00a9+\u00aa3\u00c6".length();
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
                            var12_10 = "\u00bbT@\u00dc\u00be\u00f4\u0087\u00b4\u00f4\u00f7\u001a\u008c[\u00e7^\\";
                            var13_11 = "\u00bbT@\u00dc\u00be\u00f4\u0087\u00b4\u00f4\u00f7\u001a\u008c[\u00e7^\\".length();
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
                GlassGpu.b = var14_8;
                GlassGpu.c = new Integer[5];
                GlassGpu.DATA_WIDTH = GlassGpu.a(8631, 9125842921657128785L);
                GlassGpu.TEXTURE_USAGE = GlassGpu.a(22574, 6232549182596591306L);
                GlassGpu.MAX_SURFACES = GlassGpu.a(1484, 3273952084709471023L);
                var0_14 = 1382078150341858054L;
                var6_15 = new long[2];
                var3_16 = 0;
                var4_17 = ".\u00dem>\u00cbB\u00d1=3\u009b\u00a9\u0086\u0084\u00a9\u00b20";
                var5_18 = ".\u00dem>\u00cbB\u00d1=3\u009b\u00a9\u0086\u0084\u00a9\u00b20".length();
                var2_19 = 0;
                while (true) {
                    break block31;
                    break;
                }
lbl128:

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
        GlassGpu.d = var6_15;
        GlassGpu.e = new Long[2];
        GlassGpu.MAX_SCENE_PIXELS = GlassGpu.b(31418, 4463152990906630715L);
        GlassGpu.KEEP_TEXTURES = FabricLoader.getInstance().isModLoaded(GlassGpu.a[5]);
        GlassGpu.surfaces = new ArrayList<E>(128);
        GlassGpu.invalidated = true;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x76E7;
        if (c[n2] == null) {
            GlassGpu.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x3881) & Short.MAX_VALUE;
        if (e[n2] == null) {
            GlassGpu.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
