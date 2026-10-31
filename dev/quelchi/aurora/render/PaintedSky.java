package dev.quelchi.aurora.render;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.buffers.Std140SizeCalculator;
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
import dev.quelchi.aurora.module.modules.SkyModule;
import dev.quelchi.aurora.module.modules.SkyModule$Style;
import dev.quelchi.aurora.module.modules.SkyModule$Uniform;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.PaintedSky$1;
import java.lang.invoke.LambdaMetafactory;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Supplier;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.MappableRingBuffer;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.state.SkyRenderState;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.lwjgl.system.MemoryStack;
import org.slf4j.LoggerFactory;

public final class PaintedSky {
    private static final boolean VULKAN;
    private static final boolean IRIS;
    private static final Identifier VERTEX;
    private static final int PARAMS_SIZE;
    private static final int FANTASY_SIZE;
    private static final int ZEN;
    private static final int MID;
    private static final int HOR;
    private static final int GLOW;
    private static final int BELT;
    private static final int LIT;
    private static final int SHADE;
    private static final int RIM;
    private static final int HAZE;
    private static final float[] WHITE;
    private static boolean broken;
    private static RenderPipeline skyPipeline;
    private static RenderPipeline blitPipeline;
    private static RenderPipeline fantasyPipeline;
    private static final Map<SkyModule$Style, RenderPipeline> styledPipelines;
    private static final Map<SkyModule$Style, MappableRingBuffer> styledParams;
    private static MappableRingBuffer params;
    private static MappableRingBuffer fantasyParams;
    private static GpuBuffer quad;
    private static GpuTexture noiseTex;
    private static GpuTexture lowTex;
    private static GpuTextureView noiseView;
    private static GpuTextureView lowView;
    private static Method irisInUse;
    private static Object irisApi;
    private static final Matrix4f view;
    private static final Matrix4f proj;
    private static long lastDraw;
    private static long lastNanos;
    private static double sheetX;
    private static double lastYaw;
    private static float cloudX;
    private static float cloudY;
    private static float[] fog;
    private static final Matrix4f invViewProj;
    private static final Matrix4f viewProj;
    private static final Matrix4f celMat;
    private static final Vector3f sunDir;
    private static final Vector3f planetPos;
    private static final Vector3f planetRight;
    private static final Vector3f planetUp;
    private static final Vector3f ringAxis;
    private static final Vector3f bodyL;
    private static final Vector3f moonPos;
    private static final Vector3f eclipse;
    private static final double[] zen;
    private static final double[] atm;
    private static final double[] trans;
    private static final double[] tau;
    private static final double[] sR;
    private static final double[] sM;
    private static final float[] fantasyFog;
    private static final double RG = 6360.0;
    private static final double RA = 6420.0;
    private static final double CAM_ALT = 0.02;
    private static final double BM_S = 0.003996;
    private static final double BM_E = 0.0044;
    private static final double[] BR;
    private static final double[] BO;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long d;

    private PaintedSky() {
    }

    private static double sheetTurn(double d, double d2, double d3) {
        block2: {
            double d4;
            block3: {
                int n = EffectEngine.a;
                d4 = Math.atan2(d, d3);
                if (n != 0) break block2;
                if (Double.isNaN(lastYaw)) break block3;
                double d5 = Math.IEEEremainder(d4 - lastYaw, Math.PI * 2) * Math.sqrt(Math.max(0.0, 1.0 - d2 * d2));
                sheetX = Math.IEEEremainder(sheetX + d5, 201.06192982974676);
            }
            lastYaw = d4;
        }
        return sheetX;
    }

    public static void capture(Matrix4f matrix4f, Matrix4f matrix4f2) {
        view.set((Matrix4fc)matrix4f);
        proj.set((Matrix4fc)matrix4f2);
    }

    public static boolean active() {
        long l;
        block2: {
            block3: {
                int n = EffectEngine.a;
                long l2 = System.nanoTime() - lastDraw - d;
                l = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                if (n != 0) break block2;
                if (l >= 0) break block3;
                l = 1;
                break block2;
            }
            l = 0;
        }
        return (boolean)l;
    }

    public static boolean hidesClouds() {
        boolean bl;
        block11: {
            block8: {
                block10: {
                    SkyModule skyModule;
                    int n;
                    block9: {
                        SkyModule skyModule2 = SkyModule.get();
                        n = EffectEngine.a;
                        if (!PaintedSky.active()) break block8;
                        skyModule = skyModule2;
                        if (n != 0) break block9;
                        if (skyModule.style() != SkyModule$Style.Painterly) break block10;
                        skyModule = skyModule2;
                    }
                    bl = skyModule.on(a[80]);
                    if (n != 0) break block11;
                    if (!bl) break block8;
                }
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static float[] fogColor() {
        block4: {
            MinecraftClient minecraftClient = MinecraftClient.method_1551();
            if (PaintedSky.active() && minecraftClient.field_1773.method_19418().method_19334() == CameraSubmersionType.field_27888) break block4;
            return null;
        }
        return fog;
    }

    
    public static boolean render(SkyRenderState var0) {
        var2_1 = SkyModule.get();
        var1_2 = EffectEngine.a;
        v0 = var2_1;
        if (var1_2 == 0) {
            if (v0 == null) return false;
            v0 = var2_1;
        }
        v1 = v0.isEnabled();
        if (var1_2 != 0) return v1;
        if (v1 == false) return false;
        v1 = PaintedSky.broken;
        if (var1_2 != 0) return v1;
        if (v1 != false) return false;
        v1 = PaintedSky.VULKAN;
        if (var1_2 != 0) return v1;
        if (v1 != false) return false;
        if (var0.field_64464 != DimensionType.Skybox.field_64386) {
            return false;
        }
        var3_3 = MinecraftClient.method_1551();
        if (var3_3.field_1773.method_19418().method_19334() != CameraSubmersionType.field_27888) return false;
        v2 = PaintedSky.irisPack();
        if (var1_2 != 0) // goto
        if (v2 != 0) {
            return false;
        }
        try {
            v2 = PaintedSky$1.$SwitchMap$dev$quelchi$aurora$module$modules$SkyModule$Style[var2_1.style().ordinal()];
lbl26:

            if (var1_2 == 0) {
                switch (v2 ? 1 : 0) {
                    case 1: {
                        v2 = PaintedSky.painterly(var3_3, var2_1, var0);
                        break;
                    }
                    case 2: {
                        v2 = PaintedSky.fantasy(var3_3, var2_1, var0);
                        break;
                    }
                    default: {
                        v2 = PaintedSky.styled(var3_3, var2_1, var0);
                    }
                }
            }
            v3 = var4_4 = v2;
            if (var1_2 != 0) return v3;
            if (v3) {
                PaintedSky.lastDraw = System.nanoTime();
            }
            v3 = var4_4;
            return v3;
        }
        catch (Throwable var4_5) {
            PaintedSky.disable(var4_5);
            return false;
        }
    }

    
    private static boolean painterly(MinecraftClient var0, SkyModule var1_1, SkyRenderState var2_2) {
        block88: {
            block87: {
                block85: {
                    block86: {
                        block83: {
                            block84: {
                                block104: {
                                    block82: {
                                        block80: {
                                            block81: {
                                                block103: {
                                                    block79: {
                                                        block78: {
                                                            block75: {
                                                                block76: {
                                                                    block77: {
                                                                        block102: {
                                                                            block101: {
                                                                                block100: {
                                                                                    block74: {
                                                                                        block73: {
                                                                                            block99: {
                                                                                                block71: {
                                                                                                    block72: {
                                                                                                        block98: {
                                                                                                            block97: {
                                                                                                                block96: {
                                                                                                                    block69: {
                                                                                                                        block70: {
                                                                                                                            block95: {
                                                                                                                                block67: {
                                                                                                                                    block68: {
                                                                                                                                        block94: {
                                                                                                                                            block66: {
                                                                                                                                                block64: {
                                                                                                                                                    block65: {
                                                                                                                                                        block93: {
                                                                                                                                                            block92: {
                                                                                                                                                                block91: {
                                                                                                                                                                    block90: {
                                                                                                                                                                        var4_3 = var0.method_1522();
                                                                                                                                                                        var5_4 = var4_3.field_1482;
                                                                                                                                                                        var6_5 = var4_3.field_1481;
                                                                                                                                                                        var3_6 = EffectEngine.a;
                                                                                                                                                                        v0 = var5_4;
                                                                                                                                                                        if (var3_6 != 0) break block64;
                                                                                                                                                                        if (v0 <= 0) break block65;
                                                                                                                                                                        break block90;
                                                                                                                                                                        catch (Throwable v1) {
                                                                                                                                                                            throw v1;
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                    v0 = var6_5;
                                                                                                                                                                    if (var3_6 != 0) break block64;
                                                                                                                                                                    break block91;
                                                                                                                                                                    catch (Throwable v2) {
                                                                                                                                                                        throw v2;
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                                if (v0 <= 0) break block65;
                                                                                                                                                                break block92;
                                                                                                                                                                catch (Throwable v3) {
                                                                                                                                                                    throw v3;
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                            v0 = (int)PaintedSky.ensureResources();
                                                                                                                                                            if (var3_6 != 0) break block64;
                                                                                                                                                            break block93;
                                                                                                                                                            catch (Throwable v4) {
                                                                                                                                                                throw v4;
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                        if (v0 != 0) break block66;
                                                                                                                                                    }
                                                                                                                                                    v0 = 0;
                                                                                                                                                }
                                                                                                                                                return (boolean)v0;
                                                                                                                                            }
                                                                                                                                            var7_7 = var1_1.resolution();
                                                                                                                                            cfr_temp_0 = var7_7 - 0.999f;
                                                                                                                                            v6 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                                                                                                                            if (var3_6 != 0) break block67;
                                                                                                                                            if (v6 >= 0) break block68;
                                                                                                                                            break block94;
                                                                                                                                            catch (Throwable v7) {
                                                                                                                                                throw v7;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        v6 = Math.max(1, Math.round((float)var5_4 * var7_7));
                                                                                                                                        break block67;
                                                                                                                                    }
                                                                                                                                    v6 = var5_4;
                                                                                                                                }
                                                                                                                                var8_8 = v6;
                                                                                                                                cfr_temp_1 = var7_7 - 0.999f;
                                                                                                                                v9 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
                                                                                                                                if (var3_6 != 0) break block69;
                                                                                                                                if (v9 >= 0) break block70;
                                                                                                                                break block95;
                                                                                                                                catch (Throwable v10) {
                                                                                                                                    throw v10;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            v9 = Math.max(1, Math.round((float)var6_5 * var7_7));
                                                                                                                            break block69;
                                                                                                                        }
                                                                                                                        v9 = var6_5;
                                                                                                                    }
                                                                                                                    var9_9 = v9;
                                                                                                                    v12 = var8_8;
                                                                                                                    if (var3_6 != 0) break block71;
                                                                                                                    if (v12 != var5_4) // goto
                                                                                                                    break block96;
                                                                                                                    catch (Throwable v13) {
                                                                                                                        throw v13;
                                                                                                                    }
                                                                                                                }
                                                                                                                v12 = var9_9;
                                                                                                                if (var3_6 != 0) break block71;
                                                                                                                break block97;
                                                                                                                catch (Throwable v14) {
                                                                                                                    throw v14;
                                                                                                                }
                                                                                                            }
                                                                                                            if (v12 == var6_5) break block72;
                                                                                                            break block98;
                                                                                                            catch (Throwable v15) {
                                                                                                                throw v15;
                                                                                                            }
                                                                                                        }
                                                                                                        v12 = (float)true;
                                                                                                        break block71;
                                                                                                    }
                                                                                                    v12 = (float)false;
                                                                                                }
                                                                                                var10_10 = v12;
                                                                                                v17 = PaintedSky.skyPipeline;
                                                                                                if (var3_6 != 0) break block73;
                                                                                                if (v17 != null) break block74;
                                                                                                break block99;
                                                                                                catch (Throwable v18) {
                                                                                                    throw v18;
                                                                                                }
                                                                                            }
                                                                                            v17 = PaintedSky.build(PaintedSky.a[110], PaintedSky.a[8]);
                                                                                        }
                                                                                        PaintedSky.skyPipeline = v17;
                                                                                    }
                                                                                    v20 = var10_10;
                                                                                    if (var3_6 != 0) break block75;
                                                                                    if (v20 == false) break block76;
                                                                                    break block100;
                                                                                    catch (Throwable v21) {
                                                                                        throw v21;
                                                                                    }
                                                                                }
                                                                                v22 = PaintedSky.blitPipeline;
                                                                                if (var3_6 != 0) break block77;
                                                                                break block101;
                                                                                catch (Throwable v23) {
                                                                                    throw v23;
                                                                                }
                                                                            }
                                                                            if (v22 != null) break block76;
                                                                            break block102;
                                                                            catch (Throwable v24) {
                                                                                throw v24;
                                                                            }
                                                                        }
                                                                        v22 = PaintedSky.build(PaintedSky.a[72], PaintedSky.a[19]);
                                                                    }
                                                                    PaintedSky.blitPipeline = v22;
                                                                }
                                                                v20 = (float)PaintedSky.broken;
                                                            }
                                                            if (var3_6 != 0) break block78;
                                                            if (v20 == false) break block79;
                                                            v20 = (float)false;
                                                        }
                                                        return (boolean)v20;
                                                    }
                                                    var11_11 = System.nanoTime();
                                                    v27 = PaintedSky.lastNanos;
                                                    if (var3_6 != 0) break block80;
                                                    if (v27 != 0L) break block81;
                                                    break block103;
                                                    catch (Throwable v28) {
                                                        throw v28;
                                                    }
                                                }
                                                v29 = 0.0f;
                                                break block82;
                                            }
                                            v27 = var11_11 - PaintedSky.lastNanos;
                                        }
                                        v29 = Math.clamp((float)v27 / 1.0E9f, 0.0f, 0.1f);
                                    }
                                    var13_12 = v29;
                                    PaintedSky.lastNanos = var11_11;
                                    var45_13 = PaintedSky.a;
                                    var14_14 = Math.toRadians(var1_1.num(var45_13[24]));
                                    var16_15 = var1_1.num(var45_13[157]) * 0.002f;
                                    PaintedSky.cloudX = (PaintedSky.cloudX + (float)Math.cos(var14_14) * var16_15 * var13_12) % 256.0f;
                                    PaintedSky.cloudY = (PaintedSky.cloudY + (float)Math.sin(var14_14) * var16_15 * var13_12) % 256.0f;
                                    var17_16 = (float)((double)var11_11 / 1.0E9 % 3600.0);
                                    var18_17 = PaintedSky.celestial(var2_2.field_63091);
                                    var19_18 = PaintedSky.celestial(var2_2.field_64465);
                                    var20_19 = PaintedSky.celestial(var2_2.field_64466);
                                    var21_20 = new Vector3f(var20_19[0], var20_19[1], var20_19[2]);
                                    var22_21 = new Vector3f(0.0f, 0.0f, 1.0f);
                                    var23_22 = new Vector3f((Vector3fc)var22_21).cross((Vector3fc)var21_20);
                                    var24_23 = new Matrix4f(var21_20.x, var21_20.y, var21_20.z, 0.0f, var23_22.x, var23_22.y, var23_22.z, 0.0f, var22_21.x, var22_21.y, var22_21.z, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
                                    var25_24 = new Matrix4f((Matrix4fc)PaintedSky.proj).mul((Matrix4fc)PaintedSky.view).invert();
                                    var26_25 = new Matrix3f((Matrix4fc)PaintedSky.view).transpose().transform(new Vector3f(0.0f, 1.0f, 0.0f));
                                    v31 = var18_17;
                                    if (var3_6 != 0) break block83;
                                    if (!(v31[1] > -0.06f)) break block84;
                                    break block104;
                                    catch (Throwable v32) {
                                        throw v32;
                                    }
                                }
                                v31 = var18_17;
                                break block83;
                            }
                            v31 = var19_18;
                        }
                        var27_26 = v31;
                        var28_27 = var18_17[1];
                        var29_28 = 1.0f - var2_2.field_63093;
                        var30_29 = PaintedSky.palette(var1_1, var28_27, PaintedSky.sstep(0.25f, -0.25f, var18_17[0]));
                        block58: for (var31_30 = 3; var31_30 < var30_29.length; var31_30 += 3) {
                            v34 = var30_29;
                            if (var3_6 != 0) break block85;
                            var32_32 = (v34[var31_30] * 0.299f + var30_29[var31_30 + 1] * 0.587f + var30_29[var31_30 + 2] * 0.114f) * 0.75f;
                            for (var33_33 = 0; var33_33 < 3; ++var33_33) {
                                v35 = var30_29;
                                v36 = var31_30 + var33_33;
                                if (var3_6 == 0) {
                                    v35[v36] = v35[v36] + (var32_32 - var30_29[var31_30 + var33_33]) * var29_28 * 0.7f;
                                    if (var3_6 == 0) continue;
                                    continue block58;
                                }
                                break block86;
                            }
                            if (var3_6 == 0) continue;
                        }
                        var45_13 = PaintedSky.a;
                        v35 = PaintedSky.rgb(var1_1.rgb(var45_13[40]));
                        v36 = var1_1.rgb(var45_13[82]);
                    }
                    v34 = PaintedSky.mix(v35, PaintedSky.rgb(v36), PaintedSky.sstep(0.0f, 0.35f, var28_27));
                }
                var31_31 = v34;
                var32_32 = PaintedSky.sstep(-0.03f, -0.2f, var28_27) * (1.0f - var29_28);
                var33_34 = PaintedSky.sstep(-0.05f, -0.2f, var28_27) * (1.0f - var29_28);
                var45_13 = PaintedSky.a;
                var34_35 = var1_1.num(var45_13[38]) + (0.9f - var1_1.num(var45_13[97])) * var29_28;
                var35_36 = var1_1.num(var45_13[56]);
                var36_37 = var1_1.num(var45_13[85]);
                var37_38 = var30_29[9] * 0.299f + var30_29[10] * 0.587f + var30_29[11] * 0.114f;
                var38_39 = new float[3];
                for (var39_40 = 0; var39_40 < 3; ++var39_40) {
                    var38_39[var39_40] = Math.clamp((var37_38 + (var30_29[9 + var39_40] - var37_38) * var35_36) * var36_37, 0.0f, 1.0f);
                    if (var3_6 == 0) {
                        if (var3_6 == 0) continue;
                        break;
                    }
                    break block87;
                }
                PaintedSky.fog = var38_39;
            }
            var39_41 = RenderSystem.getDevice().createCommandEncoder();
            var40_42 = PaintedSky.params.method_71119();
            var41_43 = var39_41.mapBuffer(var40_42, false, true);
            try {
                var42_44 = Std140Builder.intoBuffer((ByteBuffer)var41_43.data());
                var42_44.putMat4f((Matrix4fc)var25_24);
                var42_44.putMat4f((Matrix4fc)var24_23);
                var45_13 = PaintedSky.a;
                var42_44.putVec4(var8_8, var9_9, var17_16, var1_1.num(var45_13[69]));
                var42_44.putVec4(var26_25.x, var26_25.y, var26_25.z, 1.0f / PaintedSky.proj.m11());
                var42_44.putVec4(var18_17[0], var18_17[1], var18_17[2], var1_1.num(var45_13[139]));
                var42_44.putVec4(var19_18[0], var19_18[1], var19_18[2], var1_1.num(var45_13[3]));
                var42_44.putVec4(var27_26[0], var27_26[1], var27_26[2], (float)var2_2.field_63096.method_75261() / 8.0f);
                PaintedSky.put(var42_44, var30_29, 3, var30_29[1] * var1_1.num(var45_13[83]));
                PaintedSky.put(var42_44, var30_29, 6, var30_29[2] * var1_1.num(var45_13[59]));
                PaintedSky.put(var42_44, var30_29, 9, var1_1.num(var45_13[49]));
                PaintedSky.put(var42_44, var30_29, 27, var1_1.num(var45_13[124]));
                PaintedSky.put(var42_44, var30_29, 12, var1_1.num(var45_13[159]));
                PaintedSky.put(var42_44, var30_29, 15, var1_1.num(var45_13[116]));
                var42_44.putVec4(var31_31[0], var31_31[1], var31_31[2], var35_36);
                var43_46 = PaintedSky.rgb(var1_1.rgb(var45_13[149]));
                var42_44.putVec4(var43_46[0], var43_46[1], var43_46[2], var36_37);
                var44_48 = PaintedSky.mix(new float[]{var30_29[18], var30_29[19], var30_29[20]}, new float[]{var30_29[9], var30_29[10], var30_29[11]}, 0.08f);
                var42_44.putVec4(var44_48[0], var44_48[1], var44_48[2], var34_35);
                PaintedSky.put(var42_44, var30_29, 21, var1_1.num(var45_13[90]));
                PaintedSky.put(var42_44, var30_29, 24, var1_1.num(var45_13[16]));
                var42_44.putVec4(PaintedSky.cloudX, PaintedSky.cloudY, var1_1.num(var45_13[25]), PaintedSky.flag(var1_1, var45_13[91]));
                var42_44.putVec4(PaintedSky.flag(var1_1, var45_13[65]), var1_1.num(var45_13[39]), var1_1.num(var45_13[41]), var1_1.num(var45_13[93]));
                var42_44.putVec4(PaintedSky.sstep(-0.1f, 0.02f, var28_27), PaintedSky.sstep(-0.05f, 0.05f, var19_18[1]), var32_32, var33_34);
                PaintedSky.putRgb(var42_44, var1_1.rgb(var45_13[145]), PaintedSky.flag(var1_1, var45_13[7]));
                var42_44.putVec4(var1_1.num(var45_13[53]), var1_1.num(var45_13[148]), var1_1.num(var45_13[75]), var1_1.num(var45_13[12]));
                if (var3_6 != 0) break block88;
                PaintedSky.putRgb(var42_44, var1_1.rgb(var45_13[102]), PaintedSky.flag(var1_1, var45_13[29]));
                PaintedSky.putRgb(var42_44, var1_1.rgb(var45_13[121]), var1_1.num(var45_13[156]));
                PaintedSky.putRgb(var42_44, var1_1.rgb(var45_13[126]), PaintedSky.flag(var1_1, var45_13[115]));
                PaintedSky.putRgb(var42_44, var1_1.rgb(var45_13[6]), var1_1.num(var45_13[100]));
                var42_44.putVec4(var1_1.num(var45_13[63]), PaintedSky.flag(var1_1, var45_13[84]), var1_1.num(var45_13[34]), 0.0f);
                if (var41_43 == null) goto lbl_neg_1000
            }
            catch (Throwable var42_45) {
                block89: {
                    if (var3_6 != 0 || var41_43 == null) break block89;
                    try {
                        var41_43.close();
                    }
                    catch (Throwable var43_47) {
                        var42_45.addSuppressed(var43_47);
                    }
                }
                throw var42_45;
            }
lbl_neg_1000:

            {
                var41_43.close();
            }
lbl_neg_1000:

            {
            }
            PaintedSky.draw(var39_41, var4_3, PaintedSky.skyPipeline, var40_42, (int)var8_8, (int)var9_9, (boolean)var10_10, true);
            PaintedSky.params.method_71121();
        }
        return true;
    }

    
    private static boolean styled(MinecraftClient var0, SkyModule var1_1, SkyRenderState var2_2) {
        block97: {
            block103: {
                block102: {
                    block96: {
                        block94: {
                            block95: {
                                block120: {
                                    block90: {
                                        block91: {
                                            block89: {
                                                block88: {
                                                    block118: {
                                                        block117: {
                                                            block86: {
                                                                block87: {
                                                                    block116: {
                                                                        block115: {
                                                                            block84: {
                                                                                block85: {
                                                                                    block114: {
                                                                                        block82: {
                                                                                            block83: {
                                                                                                block113: {
                                                                                                    block112: {
                                                                                                        block111: {
                                                                                                            block80: {
                                                                                                                block81: {
                                                                                                                    block110: {
                                                                                                                        block78: {
                                                                                                                            block79: {
                                                                                                                                block109: {
                                                                                                                                    block77: {
                                                                                                                                        block75: {
                                                                                                                                            block76: {
                                                                                                                                                block108: {
                                                                                                                                                    block107: {
                                                                                                                                                        block106: {
                                                                                                                                                            block105: {
                                                                                                                                                                var4_3 = var0.method_1522();
                                                                                                                                                                var3_4 = EffectEngine.a;
                                                                                                                                                                var5_5 = var4_3.field_1482;
                                                                                                                                                                var6_6 = var4_3.field_1481;
                                                                                                                                                                v0 = var5_5;
                                                                                                                                                                if (var3_4 != 0) break block75;
                                                                                                                                                                if (v0 <= 0) break block76;
                                                                                                                                                                break block105;
                                                                                                                                                                catch (Throwable v1) {
                                                                                                                                                                    throw v1;
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                            v0 = var6_6;
                                                                                                                                                            if (var3_4 != 0) break block75;
                                                                                                                                                            break block106;
                                                                                                                                                            catch (Throwable v2) {
                                                                                                                                                                throw v2;
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                        if (v0 <= 0) break block76;
                                                                                                                                                        break block107;
                                                                                                                                                        catch (Throwable v3) {
                                                                                                                                                            throw v3;
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                    v0 = (int)PaintedSky.ensureResources();
                                                                                                                                                    if (var3_4 != 0) break block75;
                                                                                                                                                    break block108;
                                                                                                                                                    catch (Throwable v4) {
                                                                                                                                                        throw v4;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                if (v0 != 0) break block77;
                                                                                                                                            }
                                                                                                                                            v0 = 0;
                                                                                                                                        }
                                                                                                                                        return (boolean)v0;
                                                                                                                                    }
                                                                                                                                    var7_7 = var1_1.resolution();
                                                                                                                                    cfr_temp_0 = var7_7 - 0.999f;
                                                                                                                                    v6 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                                                                                                                    if (var3_4 != 0) break block78;
                                                                                                                                    if (v6 >= 0) break block79;
                                                                                                                                    break block109;
                                                                                                                                    catch (Throwable v7) {
                                                                                                                                        throw v7;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                v6 = Math.max(1, Math.round((float)var5_5 * var7_7));
                                                                                                                                break block78;
                                                                                                                            }
                                                                                                                            v6 = var5_5;
                                                                                                                        }
                                                                                                                        var8_8 = v6;
                                                                                                                        cfr_temp_1 = var7_7 - 0.999f;
                                                                                                                        v9 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
                                                                                                                        if (var3_4 != 0) break block80;
                                                                                                                        if (v9 >= 0) break block81;
                                                                                                                        break block110;
                                                                                                                        catch (Throwable v10) {
                                                                                                                            throw v10;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    v9 = Math.max(1, Math.round((float)var6_6 * var7_7));
                                                                                                                    break block80;
                                                                                                                }
                                                                                                                v9 = var6_6;
                                                                                                            }
                                                                                                            var9_9 = v9;
                                                                                                            v12 = var8_8;
                                                                                                            if (var3_4 != 0) break block82;
                                                                                                            if (v12 != var5_5) // goto
                                                                                                            break block111;
                                                                                                            catch (Throwable v13) {
                                                                                                                throw v13;
                                                                                                            }
                                                                                                        }
                                                                                                        v12 = var9_9;
                                                                                                        if (var3_4 != 0) break block82;
                                                                                                        break block112;
                                                                                                        catch (Throwable v14) {
                                                                                                            throw v14;
                                                                                                        }
                                                                                                    }
                                                                                                    if (v12 == var6_6) break block83;
                                                                                                    break block113;
                                                                                                    catch (Throwable v15) {
                                                                                                        throw v15;
                                                                                                    }
                                                                                                }
                                                                                                v12 = (float)true;
                                                                                                break block82;
                                                                                            }
                                                                                            v12 = (float)false;
                                                                                        }
                                                                                        var10_10 = v12;
                                                                                        var11_11 = var1_1.style();
                                                                                        v17 = PaintedSky.styledPipelines.containsKey((Object)var11_11);
                                                                                        if (var3_4 != 0) break block84;
                                                                                        if (v17) break block85;
                                                                                        break block114;
                                                                                        catch (Throwable v18) {
                                                                                            throw v18;
                                                                                        }
                                                                                    }
                                                                                    PaintedSky.styledPipelines.put(var11_11, PaintedSky.build(PaintedSky.a[122] + var11_11.id, null));
                                                                                }
                                                                                v17 = var10_10;
                                                                            }
                                                                            if (!v17) break block87;
                                                                            v20 = PaintedSky.blitPipeline;
                                                                            if (var3_4 != 0) break block86;
                                                                            break block115;
                                                                            catch (Throwable v21) {
                                                                                throw v21;
                                                                            }
                                                                        }
                                                                        if (v20 != null) break block87;
                                                                        break block116;
                                                                        catch (Throwable v22) {
                                                                            throw v22;
                                                                        }
                                                                    }
                                                                    PaintedSky.blitPipeline = PaintedSky.build(PaintedSky.a[72], PaintedSky.a[19]);
                                                                }
                                                                v20 = PaintedSky.styledPipelines.get((Object)var11_11);
                                                            }
                                                            var12_12 = v20;
                                                            v24 = PaintedSky.broken;
                                                            if (var3_4 != 0) break block88;
                                                            if (v24) // goto
                                                            break block117;
                                                            catch (Throwable v25) {
                                                                throw v25;
                                                            }
                                                        }
                                                        if (var12_12 != null) break block89;
                                                        break block118;
                                                        catch (Throwable v26) {
                                                            throw v26;
                                                        }
                                                    }
                                                    v24 = false;
                                                }
                                                return v24;
                                            }
                                            var13_13 = var1_1.uniforms();
                                            var14_14 = PaintedSky.styledParams.get((Object)var11_11);
                                            v28 = var14_14;
                                            if (var3_4 != 0) break block90;
                                            if (v28 != null) break block91;
                                            var15_15 = new Std140SizeCalculator().putMat4f().putMat4f().putVec4().putVec4().putVec4().putVec4();
                                            for (SkyModule$Uniform v30 : var13_13) {
                                                block93: {
                                                    block92: {
                                                        block119: {
                                                            if (var3_4 != 0) break block91;
                                                            var17_18 = v30;
                                                            if (var3_4 != 0) break block92;
                                                            if (!var17_18.color()) // goto
                                                            break block119;
                                                            catch (Throwable v31) {
                                                                throw v31;
                                                            }
                                                        }
                                                        var15_15.putVec4();
                                                    }
                                                    if (var3_4 == 0) break block93;
lbl202:

                                                    var15_15.putFloat();
                                                }
                                                if (var3_4 == 0) continue;
                                            }
                                            var14_14 = new MappableRingBuffer((Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$styled$0(dev.quelchi.aurora.module.modules.SkyModule$Style ), ()Ljava/lang/String;)((SkyModule$Style)var11_11), 130, var15_15.get());
                                            v30 = PaintedSky.styledParams.put(var11_11, var14_14);
                                        }
                                        v28 = var14_14;
                                    }
                                    var15_15 = v28.method_71119();
                                    var16_17 = (float)((double)System.nanoTime() / 1.0E9 % 3600.0);
                                    var17_18 = PaintedSky.celestial(var2_2.field_63091);
                                    var18_19 = (float)((((double)var2_2.field_63091 / 6.283185307179586 + 0.5) % 1.0 + 1.0) % 1.0);
                                    PaintedSky.invViewProj.set((Matrix4fc)PaintedSky.proj).mul((Matrix4fc)PaintedSky.view).invert();
                                    var32_20 = PaintedSky.a;
                                    var19_21 = PaintedSky.rgb(var1_1.fogRgb(var32_20[95]));
                                    var20_22 = PaintedSky.rgb(var1_1.fogRgb(var32_20[33]));
                                    var21_23 = PaintedSky.rgb(var1_1.fogRgb(var32_20[5]));
                                    PaintedSky.fog = PaintedSky.mix(var20_22, var19_21, PaintedSky.sstep(-0.15f, 0.2f, (float)var17_18[1]));
                                    var22_24 = Math.hypot(PaintedSky.view.m02(), PaintedSky.view.m22());
                                    v34 = var22_24;
                                    if (var3_4 != 0) break block94;
                                    if (!(v34 < 1.0E-4)) break block95;
                                    break block120;
                                    catch (Throwable v35) {
                                        throw v35;
                                    }
                                }
                                v36 = 0.0;
                                break block96;
                            }
                            v34 = (double)(-PaintedSky.view.m02()) / var22_24;
                        }
                        v36 = v34 * (double)Math.signum((float)var17_18[0]);
                    }
                    var24_25 = v36;
                    PaintedSky.fog = PaintedSky.mix(PaintedSky.fog, var21_23, (float)(Math.exp((double)(-var17_18[1] * var17_18[1] * 28.0f)) * (0.3 + 0.7 * Math.pow(0.5 + 0.5 * var24_25, 2.0))));
                    var26_26 = RenderSystem.getDevice().createCommandEncoder();
                    var27_27 = var26_26.mapBuffer(var15_15, false, true);
                    try {
                        var28_28 = Std140Builder.intoBuffer((ByteBuffer)var27_27.data());
                        var28_28.putMat4f((Matrix4fc)PaintedSky.invViewProj);
                        var28_28.putMat4f((Matrix4fc)PaintedSky.viewProj.set((Matrix4fc)PaintedSky.proj).mul((Matrix4fc)PaintedSky.view));
                        var28_28.putVec4(var8_8, var9_9, var16_17, 1.0f / PaintedSky.proj.m11());
                        var28_28.putVec4((float)var17_18[0], (float)var17_18[1], (float)var17_18[2], var18_19);
                        var28_28.putVec4(-PaintedSky.view.m02(), -PaintedSky.view.m12(), -PaintedSky.view.m22(), (float)PaintedSky.sheetTurn(-PaintedSky.view.m02(), -PaintedSky.view.m12(), -PaintedSky.view.m22()));
                        var28_28.putVec4(PaintedSky.fog[0], PaintedSky.fog[1], PaintedSky.fog[2], 0.0f);
                        for (SkyModule$Uniform var30_32 : var13_13) {
                            block99: {
                                block101: {
                                    block100: {
                                        block123: {
                                            block98: {
                                                block122: {
                                                    block121: {
                                                        if (var3_4 != 0) break block97;
                                                        v38 = var30_32.color();
                                                        if (var3_4 != 0) break block98;
                                                        break block121;
                                                        catch (Throwable v39) {
                                                            throw v39;
                                                        }
                                                    }
                                                    if (v38 == 0) // goto
                                                    break block122;
                                                    catch (Throwable v40) {
                                                        throw v40;
                                                    }
                                                }
                                                v38 = var1_1.rgb(var30_32.key());
                                            }
                                            var31_33 = PaintedSky.rgb(v38);
                                            var28_28.putVec4(var31_33[0], var31_33[1], var31_33[2], 0.0f);
                                            if (var3_4 == 0) break block99;
lbl289:

                                            v42 = var28_28;
                                            if (!var30_32.flag()) break block100;
                                            break block123;
                                            catch (Throwable v43) {
                                                throw v43;
                                            }
                                        }
                                        v44 = PaintedSky.flag(var1_1, var30_32.key());
                                        break block101;
                                    }
                                    v44 = var1_1.num(var30_32.key());
                                }
                                v42.putFloat(v44);
                            }
                            if (var3_4 == 0) continue;
                        }
                    }
                    catch (Throwable var28_29) {
                        block104: {
                            if (var3_4 != 0 || var27_27 == null) break block104;
                            try {
                                var27_27.close();
                            }
                            catch (Throwable var29_31) {
                                var28_29.addSuppressed(var29_31);
                            }
                        }
                        throw var28_29;
                    }
                    v47 = var27_27;
                    if (var3_4 != 0) break block102;
                    if (v47 == null) break block103;
                    v47 = var27_27;
                }
                v47.close();
            }
            PaintedSky.draw(var26_26, var4_3, var12_12, var15_15, (int)var8_8, (int)var9_9, (boolean)var10_10, false);
            var14_14.method_71121();
        }
        return true;
    }

    
    private static void draw(CommandEncoder commandEncoder, Framebuffer framebuffer, RenderPipeline renderPipeline, GpuBuffer gpuBuffer, int n, int n2, boolean bl, boolean bl2) {
        RenderPass renderPass;
        int n3;
        block30: {
            RenderPass renderPass2;
            block27: {
                GpuTextureView gpuTextureView;
                block29: {
                    block28: {
                        n3 = EffectEngine.a;
                        gpuTextureView = framebuffer.method_71639();
                        if (n3 != 0) break block28;
                        if (!bl) break block29;
                        PaintedSky.ensureLow(n, n2);
                    }
                    gpuTextureView = lowView;
                }
                renderPass = commandEncoder.createRenderPass(PaintedSky::lambda$draw$1, gpuTextureView, OptionalInt.empty());
                if (n3 == 0) {
                    try {
                        renderPass.setPipeline(renderPipeline);
                        RenderSystem.bindDefaultUniforms((RenderPass)renderPass);
                        renderPass.setUniform(a[73], gpuBuffer);
                        if (bl2) {
                            renderPass.bindTexture(a[8], noiseView, RenderSystem.getSamplerCache().method_75297(FilterMode.LINEAR));
                        }
                        PaintedSky.bindQuad(renderPass);
                        renderPass2 = renderPass;
                        if (n3 != 0) break block27;
                        renderPass2.drawIndexed(0, 0, 6, 1);
                    }
                    catch (Throwable throwable) {
                        if (n3 != 0) throw throwable;
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
                }
                if (renderPass == null) break block30;
                renderPass2 = renderPass;
            }
            renderPass2.close();
        }
        if (!bl) {
            return;
        }
        renderPass = commandEncoder.createRenderPass(PaintedSky::lambda$draw$2, framebuffer.method_71639(), OptionalInt.empty());
        try {
            renderPass.setPipeline(blitPipeline);
            if (n3 != 0) return;
            RenderSystem.bindDefaultUniforms((RenderPass)renderPass);
            renderPass.bindTexture(a[19], lowView, RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR));
            PaintedSky.bindQuad(renderPass);
            renderPass.drawIndexed(0, 0, 6, 1);
            if (renderPass == null) return;
        }
        catch (Throwable throwable) {
            if (n3 != 0) throw throwable;
            if (renderPass == null) throw throwable;
            try {
                renderPass.close();
                throw throwable;
            }
            catch (Throwable throwable3) {
                throwable.addSuppressed(throwable3);
            }
            throw throwable;
        }
        renderPass.close();
    }

    
    private static boolean fantasy(MinecraftClient var0, SkyModule var1_1, SkyRenderState var2_2) {
        block69: {
            block68: {
                block67: {
                    block84: {
                        block66: {
                            block65: {
                                block62: {
                                    block63: {
                                        block64: {
                                            block83: {
                                                block82: {
                                                    block81: {
                                                        block61: {
                                                            block60: {
                                                                block80: {
                                                                    block58: {
                                                                        block59: {
                                                                            block79: {
                                                                                block78: {
                                                                                    block77: {
                                                                                        block56: {
                                                                                            block57: {
                                                                                                block76: {
                                                                                                    block54: {
                                                                                                        block55: {
                                                                                                            block75: {
                                                                                                                block53: {
                                                                                                                    block51: {
                                                                                                                        block52: {
                                                                                                                            block74: {
                                                                                                                                block73: {
                                                                                                                                    block72: {
                                                                                                                                        block71: {
                                                                                                                                            var4_3 = var0.method_1522();
                                                                                                                                            var3_4 = EffectEngine.a;
                                                                                                                                            var5_5 = var4_3.field_1482;
                                                                                                                                            var6_6 = var4_3.field_1481;
                                                                                                                                            v0 = var5_5;
                                                                                                                                            if (var3_4 != 0) break block51;
                                                                                                                                            if (v0 <= 0) break block52;
                                                                                                                                            break block71;
                                                                                                                                            catch (Throwable v1) {
                                                                                                                                                throw v1;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        v0 = var6_6;
                                                                                                                                        if (var3_4 != 0) break block51;
                                                                                                                                        break block72;
                                                                                                                                        catch (Throwable v2) {
                                                                                                                                            throw v2;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    if (v0 <= 0) break block52;
                                                                                                                                    break block73;
                                                                                                                                    catch (Throwable v3) {
                                                                                                                                        throw v3;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                v0 = (int)PaintedSky.ensureResources();
                                                                                                                                if (var3_4 != 0) break block51;
                                                                                                                                break block74;
                                                                                                                                catch (Throwable v4) {
                                                                                                                                    throw v4;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            if (v0 != 0) break block53;
                                                                                                                        }
                                                                                                                        v0 = 0;
                                                                                                                    }
                                                                                                                    return (boolean)v0;
                                                                                                                }
                                                                                                                var7_7 = var1_1.resolution();
                                                                                                                cfr_temp_0 = var7_7 - 0.999f;
                                                                                                                v6 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                                                                                                if (var3_4 != 0) break block54;
                                                                                                                if (v6 >= 0) break block55;
                                                                                                                break block75;
                                                                                                                catch (Throwable v7) {
                                                                                                                    throw v7;
                                                                                                                }
                                                                                                            }
                                                                                                            v6 = Math.max(1, Math.round((float)var5_5 * var7_7));
                                                                                                            break block54;
                                                                                                        }
                                                                                                        v6 = var5_5;
                                                                                                    }
                                                                                                    var8_8 = v6;
                                                                                                    cfr_temp_1 = var7_7 - 0.999f;
                                                                                                    v9 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
                                                                                                    if (var3_4 != 0) break block56;
                                                                                                    if (v9 >= 0) break block57;
                                                                                                    break block76;
                                                                                                    catch (Throwable v10) {
                                                                                                        throw v10;
                                                                                                    }
                                                                                                }
                                                                                                v9 = Math.max(1, Math.round((float)var6_6 * var7_7));
                                                                                                break block56;
                                                                                            }
                                                                                            v9 = var6_6;
                                                                                        }
                                                                                        var9_9 = v9;
                                                                                        v12 = var8_8;
                                                                                        if (var3_4 != 0) break block58;
                                                                                        if (v12 != var5_5) // goto
                                                                                        break block77;
                                                                                        catch (Throwable v13) {
                                                                                            throw v13;
                                                                                        }
                                                                                    }
                                                                                    v12 = var9_9;
                                                                                    if (var3_4 != 0) break block58;
                                                                                    break block78;
                                                                                    catch (Throwable v14) {
                                                                                        throw v14;
                                                                                    }
                                                                                }
                                                                                if (v12 == var6_6) break block59;
                                                                                break block79;
                                                                                catch (Throwable v15) {
                                                                                    throw v15;
                                                                                }
                                                                            }
                                                                            v12 = (float)true;
                                                                            break block58;
                                                                        }
                                                                        v12 = (float)false;
                                                                    }
                                                                    var10_10 = v12;
                                                                    v17 = PaintedSky.fantasyPipeline;
                                                                    if (var3_4 != 0) break block60;
                                                                    if (v17 != null) break block61;
                                                                    break block80;
                                                                    catch (Throwable v18) {
                                                                        throw v18;
                                                                    }
                                                                }
                                                                v17 = PaintedSky.build(PaintedSky.a[107], PaintedSky.a[151]);
                                                            }
                                                            PaintedSky.fantasyPipeline = v17;
                                                        }
                                                        v20 = var10_10;
                                                        if (var3_4 != 0) break block62;
                                                        if (v20 == false) break block63;
                                                        break block81;
                                                        catch (Throwable v21) {
                                                            throw v21;
                                                        }
                                                    }
                                                    v22 = PaintedSky.blitPipeline;
                                                    if (var3_4 != 0) break block64;
                                                    break block82;
                                                    catch (Throwable v23) {
                                                        throw v23;
                                                    }
                                                }
                                                if (v22 != null) break block63;
                                                break block83;
                                                catch (Throwable v24) {
                                                    throw v24;
                                                }
                                            }
                                            v22 = PaintedSky.build(PaintedSky.a[30], PaintedSky.a[108]);
                                        }
                                        PaintedSky.blitPipeline = v22;
                                    }
                                    v20 = (float)PaintedSky.broken;
                                }
                                if (var3_4 != 0) break block65;
                                if (v20 == false) break block66;
                                v20 = (float)false;
                            }
                            return (boolean)v20;
                        }
                        v27 = PaintedSky.fantasyParams;
                        if (var3_4 != 0) break block67;
                        if (v27 != null) break block68;
                        break block84;
                        catch (Throwable v28) {
                            throw v28;
                        }
                    }
                    v27 = new MappableRingBuffer((Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$fantasy$3(), ()Ljava/lang/String;)(), 130, 608);
                }
                PaintedSky.fantasyParams = v27;
            }
            var11_11 = (float)((double)System.nanoTime() / 1.0E9 % 3600.0);
            var12_12 = var2_2.field_63091 + 1.5707964f;
            var44_13 = PaintedSky.a;
            var13_14 = PaintedSky.rad(var1_1.num(var44_13[17]));
            var14_15 = PaintedSky.cos(var12_12);
            var15_16 = PaintedSky.sin(var12_12);
            var16_17 = PaintedSky.cos(var13_14);
            var17_18 = PaintedSky.sin(var13_14);
            PaintedSky.sunDir.set(var14_15, var15_16 * var16_17, var15_16 * var17_18);
            PaintedSky.celMat.set(var14_15, var15_16 * var16_17, var15_16 * var17_18, 0.0f, -var15_16, var14_15 * var16_17, var14_15 * var17_18, 0.0f, 0.0f, -var17_18, var16_17, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
            PaintedSky.invViewProj.set((Matrix4fc)PaintedSky.proj).mul((Matrix4fc)PaintedSky.view).invert();
            var18_19 = PaintedSky.rad(var1_1.num(var44_13[162]));
            var19_20 = PaintedSky.rad(var1_1.num(var44_13[36]));
            var20_21 = PaintedSky.cos(var19_20) * PaintedSky.cos(var18_19);
            var21_22 = PaintedSky.sin(var19_20);
            var22_23 = PaintedSky.cos(var19_20) * PaintedSky.sin(var18_19);
            var23_24 = PaintedSky.cos(4.712389f);
            var24_25 = PaintedSky.sin(4.712389f);
            var25_26 = var23_24 * var20_21 + var24_25 * var16_17 * var21_22 + var24_25 * var17_18 * var22_23;
            var26_27 = -var24_25 * var20_21 + var23_24 * var16_17 * var21_22 + var23_24 * var17_18 * var22_23;
            var27_28 = -var17_18 * var21_22 + var16_17 * var22_23;
            PaintedSky.azEl(PaintedSky.planetPos, var1_1.num(var44_13[158]), var1_1.num(var44_13[44]));
            PaintedSky.planetRight.set(-PaintedSky.planetPos.z, 0.0f, PaintedSky.planetPos.x).normalize();
            PaintedSky.planetUp.set((Vector3fc)PaintedSky.planetRight).cross((Vector3fc)PaintedSky.planetPos);
            var28_29 = PaintedSky.rad(var1_1.num(var44_13[50]));
            var29_30 = PaintedSky.rad(var1_1.num(var44_13[31]));
            PaintedSky.ringAxis.set((Vector3fc)PaintedSky.planetUp).mul(PaintedSky.cos(var29_30)).fma(PaintedSky.sin(var29_30), (Vector3fc)PaintedSky.planetRight).mul(PaintedSky.cos(var28_29)).fma(-PaintedSky.sin(var28_29), (Vector3fc)PaintedSky.planetPos).normalize();
            var30_31 = PaintedSky.rad(var1_1.num(var44_13[76]));
            var31_32 = PaintedSky.rad(var1_1.num(var44_13[135]));
            var32_33 = var1_1.num(var44_13[98]);
            PaintedSky.bodyL.set((Vector3fc)PaintedSky.planetUp).mul(PaintedSky.cos(var31_32)).fma(PaintedSky.sin(var31_32), (Vector3fc)PaintedSky.planetRight).mul(PaintedSky.sin(var30_31)).fma(-PaintedSky.cos(var30_31), (Vector3fc)PaintedSky.planetPos);
            PaintedSky.bodyL.mul(1.0f - var32_33).fma(var32_33, (Vector3fc)PaintedSky.sunDir).normalize();
            var33_34 = PaintedSky.sin(PaintedSky.rad(var1_1.num(var44_13[1])));
            PaintedSky.azEl(PaintedSky.moonPos, var1_1.num(var44_13[0]), var1_1.num(var44_13[143]));
            var34_35 = PaintedSky.rad(var1_1.num(var44_13[64]));
            var35_36 = var1_1.num(var44_13[92]);
            var36_37 = var1_1.num(var44_13[86]);
            PaintedSky.atmosphere(0.0, 1.0, 0.0, PaintedSky.sunDir, 20.0, 4, var36_37, PaintedSky.zen, PaintedSky.trans);
            var37_38 = 20.0 * Math.min(Math.pow(0.2 / (PaintedSky.lum(PaintedSky.zen) + 2.0E-4), 0.62), 60.0);
            PaintedSky.fog = PaintedSky.fantasyFog(var1_1, var37_38, var33_34, var36_37);
            var39_39 = RenderSystem.getDevice().createCommandEncoder();
            var40_40 = PaintedSky.fantasyParams.method_71119();
            var41_41 = var39_39.mapBuffer(var40_40, false, true);
            try {
                var42_42 = Std140Builder.intoBuffer((ByteBuffer)var41_41.data());
                var42_42.putMat4f((Matrix4fc)PaintedSky.invViewProj);
                var42_42.putMat4f((Matrix4fc)PaintedSky.celMat);
                var42_42.putVec4(var8_8, var9_9, var11_11, 1.0f / PaintedSky.proj.m11());
                var42_42.putVec4((float)PaintedSky.zen[0], (float)PaintedSky.zen[1], (float)PaintedSky.zen[2], (float)var37_38);
                var42_42.putVec4(PaintedSky.sunDir.x, PaintedSky.sunDir.y, PaintedSky.sunDir.z, var1_1.num(var44_13[68]));
                if (var3_4 != 0) break block69;
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[21]), PaintedSky.flag(var1_1, var44_13[142]));
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[123]), var36_37);
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[2]), var1_1.num(var44_13[14]));
                var42_42.putVec4(var1_1.num(var44_13[163]), var1_1.num(var44_13[132]), var1_1.num(var44_13[94]), var1_1.num(var44_13[35]));
                var42_42.putVec4(PaintedSky.flag(var1_1, var44_13[79]), var1_1.num(var44_13[26]), var1_1.num(var44_13[113]), var1_1.num(var44_13[101]));
                var42_42.putVec4(var25_26, var26_27, var27_28, PaintedSky.flag(var1_1, var44_13[27]));
                var42_42.putVec4(var1_1.num(var44_13[57]), var1_1.num(var44_13[78]), var1_1.num(var44_13[119]), var1_1.num(var44_13[144]));
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[105]), var1_1.num(var44_13[127]));
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[152]), 0.0f);
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[99]), 0.0f);
                var42_42.putVec4(PaintedSky.planetPos.x, PaintedSky.planetPos.y, PaintedSky.planetPos.z, PaintedSky.flag(var1_1, var44_13[134]));
                var42_42.putVec4(PaintedSky.ringAxis.x, PaintedSky.ringAxis.y, PaintedSky.ringAxis.z, var33_34);
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[55]), var1_1.num(var44_13[118]));
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[70]), var1_1.num(var44_13[60]));
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[51]), var1_1.num(var44_13[153]));
                var42_42.putVec4(PaintedSky.bodyL.x, PaintedSky.bodyL.y, PaintedSky.bodyL.z, var1_1.num(var44_13[160]));
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[137]), PaintedSky.flag(var1_1, var44_13[74]));
                var42_42.putVec4(var35_36, Math.max(var1_1.num(var44_13[37]), var35_36 + 0.05f), var1_1.num(var44_13[136]), 0.0f);
                var42_42.putVec4(PaintedSky.moonPos.x, PaintedSky.moonPos.y, PaintedSky.moonPos.z, PaintedSky.flag(var1_1, var44_13[131]));
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[129]), PaintedSky.sin(PaintedSky.rad(var1_1.num(var44_13[20]))));
                var42_42.putVec4(PaintedSky.flag(var1_1, var44_13[88]), var1_1.num(var44_13[52]), var1_1.num(var44_13[154]), var1_1.num(var44_13[15]));
                var42_42.putVec4(PaintedSky.cos(var34_35), PaintedSky.sin(var34_35), var1_1.num(var44_13[89]), var1_1.num(var44_13[147]));
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[111]), PaintedSky.flag(var1_1, var44_13[164]));
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[62]), var1_1.num(var44_13[106]));
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[104]), 0.0f);
                PaintedSky.putLin(var42_42, var1_1.rgb(var44_13[11]), 0.0f);
                var42_42.putVec4(PaintedSky.fog[0], PaintedSky.fog[1], PaintedSky.fog[2], 0.0f);
                if (var41_41 == null) goto lbl_neg_1000
            }
            catch (Throwable var42_43) {
                block70: {
                    if (var3_4 != 0 || var41_41 == null) break block70;
                    try {
                        var41_41.close();
                    }
                    catch (Throwable var43_44) {
                        var42_43.addSuppressed(var43_44);
                    }
                }
                throw var42_43;
            }
lbl_neg_1000:

            {
                var41_41.close();
            }
lbl_neg_1000:

            {
            }
            PaintedSky.draw(var39_39, var4_3, PaintedSky.fantasyPipeline, var40_40, (int)var8_8, (int)var9_9, (boolean)var10_10, true);
            PaintedSky.fantasyParams.method_71121();
        }
        return true;
    }

    private static float[] fantasyFog(SkyModule skyModule, double d, float f, float f2) {
        float[] fArray;
        block34: {
            double[] dArray;
            double d2;
            double d3;
            int n;
            block33: {
                SkyModule skyModule2;
                String[] stringArray;
                block30: {
                    block31: {
                        double d4;
                        double d5;
                        float f3;
                        block32: {
                            block27: {
                                float f4;
                                block28: {
                                    block29: {
                                        SkyModule skyModule3;
                                        block26: {
                                            f3 = PaintedSky.sstep(0.03f, -0.18f, PaintedSky.sunDir.y);
                                            n = EffectEngine.a;
                                            skyModule3 = skyModule;
                                            if (n != 0) break block26;
                                            if (!skyModule3.on(a[120])) break block27;
                                            skyModule3 = skyModule;
                                        }
                                        stringArray = a;
                                        float f5 = PaintedSky.rad(skyModule3.num(stringArray[139])) * 0.5f / f;
                                        f4 = sunDir.dot((Vector3fc)planetPos);
                                        if (n != 0) break block28;
                                        if (!(f4 > 0.0f)) break block29;
                                        f4 = PaintedSky.sstep(1.0f + f5, 1.0f - f5, eclipse.set((Vector3fc)sunDir).cross((Vector3fc)planetPos).length() / f);
                                        break block28;
                                    }
                                    f4 = 0.0f;
                                }
                                float f6 = f4;
                                d *= (double)(1.0f - 0.985f * f6);
                                f3 = Math.max(f3, f6 * 0.9f);
                            }
                            d5 = -view.m02();
                            d4 = -view.m22();
                            double d6 = Math.max(Math.hypot(d5, d4), 1.0E-6);
                            d5 /= d6;
                            d4 /= d6;
                            Arrays.fill(atm, 0.0);
                            skyModule2 = skyModule;
                            if (n != 0) break block30;
                            if (!skyModule2.on(a[58])) break block31;
                            if (!(PaintedSky.sunDir.y > -0.3f)) break block32;
                            PaintedSky.atmosphere(d5, 0.0, d4, sunDir, d, 10, f2, atm, trans);
                        }
                        d3 = (d5 * (double)PaintedSky.sunDir.x + d4 * (double)PaintedSky.sunDir.z) / (Math.hypot(PaintedSky.sunDir.x, PaintedSky.sunDir.z) + 1.0E-4);
                        stringArray = a;
                        d2 = 1.0 + (double)skyModule.num(stringArray[13]) * (0.3 + 1.2 * Math.pow(0.5 + 0.5 * d3, 4.0));
                        int n2 = skyModule.rgb(stringArray[82]);
                        int n3 = skyModule.rgb(stringArray[128]);
                        for (int i = 0; i < 3; ++i) {
                            dArray = atm;
                            if (n == 0) {
                                int n4 = i;
                                dArray[n4] = dArray[n4] * (d2 * (double)PaintedSky.lin(n2 >> 16 - 8 * i));
                                if (n == 0) continue;
                                break;
                            }
                            break block33;
                        }
                        stringArray = a;
                        double d7 = Math.toRadians(skyModule.num(stringArray[103]));
                        double d8 = Math.cos(d7);
                        double d9 = Math.sin(d7);
                        double d10 = 0.57735;
                        double d11 = atm[0];
                        double d12 = atm[1];
                        double d13 = atm[2];
                        double d14 = d10 * d10 * (d11 + d12 + d13) * (1.0 - d8);
                        PaintedSky.atm[0] = Math.max(d11 * d8 + d10 * (d13 - d12) * d9 + d14, 0.0);
                        PaintedSky.atm[1] = Math.max(d12 * d8 + d10 * (d11 - d13) * d9 + d14, 0.0);
                        PaintedSky.atm[2] = Math.max(d13 * d8 + d10 * (d12 - d11) * d9 + d14, 0.0);
                        for (int i = 0; i < 3; ++i) {
                            dArray = atm;
                            if (n == 0) {
                                int n5 = i;
                                dArray[n5] = dArray[n5] + (double)PaintedSky.lin(n3 >> 16 - 8 * i) * 1.8 * (double)f3 * 2.0;
                                if (n == 0) continue;
                                break;
                            }
                            break block33;
                        }
                    }
                    skyModule2 = skyModule;
                }
                stringArray = a;
                d3 = skyModule2.num(stringArray[109]);
                d2 = skyModule.num(stringArray[56]);
                dArray = atm;
            }
            double d15 = PaintedSky.lum(dArray) * d3;
            for (int i = 0; i < 3; ++i) {
                double d16 = Math.max(d15 + (atm[i] * d3 - d15) * d2, 0.0);
                d16 = Math.clamp(d16 * (2.51 * d16 + 0.03) / (d16 * (2.43 * d16 + 0.59) + 0.14), 0.0, 1.0);
                fArray = fantasyFog;
                if (n == 0) {
                    fArray[i] = (float)Math.pow(d16, 0.45454545454545453);
                    if (n == 0) continue;
                    break;
                }
                break block34;
            }
            fArray = fantasyFog;
        }
        return fArray;
    }

    private static void atmosphere(double d, double d2, double d3, Vector3f vector3f, double d4, int n, double d5, double[] dArray, double[] dArray2) {
        double d6;
        double d7;
        int n2;
        block7: {
            double d8 = 6360.02;
            double d9 = d8 * d2;
            n2 = EffectEngine.a;
            double d10 = -d9 + Math.sqrt(d9 * d9 - (d8 * d8 - 4.12164E7));
            d7 = d * (double)vector3f.x + d2 * (double)vector3f.y + d3 * (double)vector3f.z;
            Arrays.fill(tau, 0.0);
            Arrays.fill(sR, 0.0);
            Arrays.fill(sM, 0.0);
            int n3 = 0;
            while (n3 < n) {
                block8: {
                    d6 = n3;
                    if (n2 != 0) break block7;
                    double d11 = d6 / (double)n;
                    double d12 = (double)(n3 + 1) / (double)n;
                    double d13 = d10 * d11 * d11;
                    double d14 = d10 * d12 * d12;
                    double d15 = d14 - d13;
                    double d16 = 0.5 * (d13 + d14);
                    double d17 = d * d16;
                    double d18 = d8 + d2 * d16;
                    double d19 = d3 * d16;
                    double d20 = Math.sqrt(d17 * d17 + d18 * d18 + d19 * d19);
                    double d21 = d20 - 6360.0;
                    double d22 = Math.max(d21, 0.0);
                    double d23 = Math.exp(-d21 / 8.0);
                    double d24 = Math.exp(-d21 / 1.2);
                    double d25 = Math.max(0.0, 1.0 - Math.abs(d21 - 25.0) / 15.0);
                    double d26 = (d17 * (double)vector3f.x + d18 * (double)vector3f.y + d19 * (double)vector3f.z) / d20;
                    float f = PaintedSky.sstep(-0.01f, 0.01f, (float)(d26 + Math.sqrt(d22 * (12720.0 + d22)) / d20));
                    double d27 = 8.0 * PaintedSky.chapman(795.0, d22 / 8.0, d26);
                    double d28 = 1.2 * PaintedSky.chapman(5300.0, d22 / 1.2, d26);
                    for (int i = 0; i < 3; ++i) {
                        double d29;
                        double d30;
                        block9: {
                            double[] dArray3;
                            int n4;
                            double d31;
                            block10: {
                                d31 = (BR[i] * d23 + 0.0044 * d5 * d24 + BO[i] * d25) * d15;
                                if (n2 != 0) break block8;
                                int n5 = i;
                                n4 = n5;
                                dArray3 = tau;
                                d30 = tau[n5];
                                d29 = d31 * 0.5;
                                if (n2 != 0) break block9;
                                dArray3[n4] = d30 + d29;
                                if (!(f > 0.0f)) break block10;
                                double d32 = Math.exp(-(tau[i] + BR[i] * d27 + 0.0044 * d5 * d28 + BO[i] * d27 * 1.875)) * (double)f;
                                int n6 = i;
                                sR[n6] = sR[n6] + d32 * d23 * d15;
                                int n7 = i;
                                sM[n7] = sM[n7] + d32 * d24 * d15;
                            }
                            int n8 = i;
                            n4 = n8;
                            dArray3 = tau;
                            d30 = tau[n8];
                            d29 = d31 * 0.5;
                        }
                        dArray3[n4] = d30 + d29;
                        if (n2 == 0) continue;
                    }
                    ++n3;
                }
                if (n2 == 0) continue;
            }
            d6 = 0.5776;
        }
        double d33 = d6;
        double d34 = 0.1193662073189215 * ((1.0 - d33) * (1.0 + d7 * d7)) / ((2.0 + d33) * Math.pow(1.0 + d33 - 1.52 * d7, 1.5));
        for (int i = 0; i < 3; ++i) {
            dArray2[i] = Math.exp(-tau[i]);
            dArray[i] = d4 * (sR[i] * BR[i] * 0.05968310365946075 * (1.0 + d7 * d7) + sM[i] * 0.003996 * d5 * d34);
            if (n2 == 0) continue;
        }
    }

    private static double chapman(double d, double d2, double d3) {
        double d4;
        double d5;
        block4: {
            block5: {
                d5 = Math.sqrt(d + d2);
                int n = EffectEngine.a;
                d4 = d3;
                if (n != 0) break block4;
                if (!(d4 >= 0.0)) break block5;
                return d5 / (d5 * d3 + 1.0) * Math.exp(-d2);
            }
            d4 = Math.sqrt(1.0 - d3 * d3) * (d + d2);
        }
        double d6 = d4;
        return 2.0 * Math.sqrt(d6) * Math.exp(d - d6) - d5 / (1.0 - d5 * d3) * Math.exp(-d2);
    }

    private static double lum(double[] dArray) {
        return dArray[0] * 0.2126 + dArray[1] * 0.7152 + dArray[2] * 0.0722;
    }

    private static void azEl(Vector3f vector3f, float f, float f2) {
        float f3 = PaintedSky.rad(f);
        float f4 = PaintedSky.rad(f2);
        vector3f.set(PaintedSky.cos(f4) * PaintedSky.cos(f3), PaintedSky.sin(f4), PaintedSky.cos(f4) * PaintedSky.sin(f3));
    }

    private static void putLin(Std140Builder std140Builder, int n, float f) {
        std140Builder.putVec4(PaintedSky.lin(n >> 16), PaintedSky.lin(n >> 8), PaintedSky.lin(n), f);
    }

    private static float lin(int n) {
        return (float)Math.pow((double)(n & 0xFF) / 255.0, 2.2);
    }

    private static float rad(float f) {
        return (float)Math.toRadians(f);
    }

    private static float sin(float f) {
        return (float)Math.sin(f);
    }

    private static float cos(float f) {
        return (float)Math.cos(f);
    }

    private static float[] palette(SkyModule skyModule, float f, float f2) {
        float[] fArray;
        block7: {
            int n;
            String[] stringArray = a;
            float[] fArray2 = PaintedSky.rgb(skyModule.rgb(stringArray[125]));
            float[] fArray3 = PaintedSky.rgb(skyModule.rgb(stringArray[32]));
            float[] fArray4 = PaintedSky.rgb(skyModule.rgb(stringArray[128]));
            float[] fArray5 = PaintedSky.mix(PaintedSky.rgb(skyModule.rgb(stringArray[10])), PaintedSky.rgb(skyModule.rgb(stringArray[46])), f2);
            float[] fArray6 = PaintedSky.mix(PaintedSky.rgb(skyModule.rgb(stringArray[117])), PaintedSky.rgb(skyModule.rgb(stringArray[155])), f2);
            float[] fArray7 = PaintedSky.mix(PaintedSky.rgb(skyModule.rgb(stringArray[47])), PaintedSky.rgb(skyModule.rgb(stringArray[71])), f2);
            float[] fArray8 = PaintedSky.rgb(skyModule.rgb(stringArray[42]));
            float[] fArray9 = PaintedSky.rgb(skyModule.rgb(stringArray[149]));
            float[] fArray10 = PaintedSky.rgb(skyModule.rgb(stringArray[61]));
            float[] fArray11 = PaintedSky.rgb(skyModule.rgb(stringArray[146]));
            float[] fArray12 = PaintedSky.rgb(skyModule.rgb(stringArray[161]));
            float[] fArray13 = PaintedSky.rgb(skyModule.rgb(stringArray[4]));
            float[] fArray14 = PaintedSky.rgb(skyModule.rgb(stringArray[112]));
            float[] fArray15 = PaintedSky.rgb(skyModule.rgb(stringArray[23]));
            float[][] fArrayArray = new float[][]{PaintedSky.key(-0.26f, 0.0f, 0.0f, fArray2, fArray3, fArray4, fArray4, fArray4, PaintedSky.mix(PaintedSky.mix(fArray3, fArray4, 0.55f), fArray9, 0.1f), PaintedSky.mix(fArray2, fArray3, 0.6f), PaintedSky.mix(fArray9, fArray4, 0.2f), PaintedSky.mul(PaintedSky.mix(fArray4, fArray12, 0.5f), 0.55f)), PaintedSky.key(-0.11f, 0.55f, 0.9f, PaintedSky.mix(fArray2, fArray5, 0.45f), PaintedSky.mix(fArray3, fArray6, 0.4f), PaintedSky.mix(fArray6, fArray7, 0.55f), fArray7, PaintedSky.mix(fArray6, fArray7, 0.3f), PaintedSky.mix(fArray11, fArray6, 0.5f), PaintedSky.mix(PaintedSky.mix(fArray3, fArray5, 0.4f), fArray11, 0.15f), fArray7, PaintedSky.mul(PaintedSky.mix(fArray5, fArray12, 0.5f), 0.6f)), PaintedSky.key(0.03f, 1.0f, 0.4f, fArray5, fArray6, fArray7, fArray8, fArray6, PaintedSky.mix(fArray10, fArray8, 0.55f), PaintedSky.mix(PaintedSky.mix(fArray11, fArray6, 0.45f), fArray5, 0.2f), PaintedSky.mix(fArray8, WHITE, 0.5f), PaintedSky.mul(PaintedSky.mix(fArray7, fArray12, 0.6f), 0.75f)), PaintedSky.key(0.3f, 0.35f, 0.0f, fArray13, fArray14, fArray15, PaintedSky.mix(PaintedSky.rgb(skyModule.rgb(stringArray[82])), fArray15, 0.4f), fArray15, fArray10, fArray11, WHITE, PaintedSky.mix(fArray15, fArray12, 0.65f))};
            int n2 = EffectEngine.a;
            for (n = 0; n < fArrayArray.length - 2; ++n) {
                if (!(f > fArrayArray[n + 1][0])) break;
                if (n2 == 0) continue;
                break;
            }
            float[] fArray16 = fArrayArray[n];
            float[] fArray17 = fArrayArray[n + 1];
            float[] fArray18 = new float[fArray16.length];
            float f3 = PaintedSky.sstep(fArray16[0], fArray17[0], f);
            for (int i = 0; i < fArray16.length; ++i) {
                fArray = fArray18;
                if (n2 == 0) {
                    fArray[i] = fArray16[i] + (fArray17[i] - fArray16[i]) * f3;
                    if (n2 == 0) continue;
                    break;
                }
                break block7;
            }
            fArray = fArray18;
        }
        return fArray;
    }

    private static float[] key(float f, float f2, float f3, float[] ... fArray) {
        float[] fArray2;
        block4: {
            float[] fArray3 = new float[3 + fArray.length * 3];
            fArray3[0] = f;
            fArray3[1] = f2;
            int n = EffectEngine.a;
            fArray3[2] = f3;
            for (int i = 0; i < fArray.length; ++i) {
                fArray2 = fArray[i];
                if (n == 0) {
                    System.arraycopy(fArray2, 0, fArray3, 3 + i * 3, 3);
                    if (n == 0) continue;
                    break;
                }
                break block4;
            }
            fArray2 = fArray3;
        }
        return fArray2;
    }

    private static float[] celestial(float f) {
        return new float[]{(float)(-Math.sin(f)), (float)Math.cos(f), 0.0f};
    }

    private static void put(Std140Builder std140Builder, float[] fArray, int n, float f) {
        std140Builder.putVec4(fArray[n], fArray[n + 1], fArray[n + 2], f);
    }

    private static void putRgb(Std140Builder std140Builder, int n, float f) {
        float[] fArray = PaintedSky.rgb(n);
        std140Builder.putVec4(fArray[0], fArray[1], fArray[2], f);
    }

    private static float flag(SkyModule skyModule, String string) {
        float f = skyModule.on(string) ? 1.0f : 0.0f;
        return f;
    }

    private static float[] rgb(int n) {
        return new float[]{(float)(n >> 16 & 0xFF) / 255.0f, (float)(n >> 8 & 0xFF) / 255.0f, (float)(n & 0xFF) / 255.0f};
    }

    private static float[] mix(float[] fArray, float[] fArray2, float f) {
        return new float[]{fArray[0] + (fArray2[0] - fArray[0]) * f, fArray[1] + (fArray2[1] - fArray[1]) * f, fArray[2] + (fArray2[2] - fArray[2]) * f};
    }

    private static float[] mul(float[] fArray, float f) {
        return new float[]{fArray[0] * f, fArray[1] * f, fArray[2] * f};
    }

    private static float sstep(float f, float f2, float f3) {
        float f4 = Math.clamp((f3 - f) / (f2 - f), 0.0f, 1.0f);
        return f4 * f4 * (3.0f - 2.0f * f4);
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
                    Class<?> clazz = Class.forName(stringArray[141]);
                    irisApi = clazz.getMethod(stringArray[67], new Class[0]).invoke(null, new Object[0]);
                    irisInUse = clazz.getMethod(stringArray[18], new Class[0]);
                }
                object = irisInUse.invoke(irisApi, new Object[0]);
            }
            return (Boolean)object;
        }
        catch (ReflectiveOperationException | RuntimeException exception) {
            return true;
        }
    }

    
    private static boolean ensureResources() {
        MemoryStack memoryStack;
        GpuDevice gpuDevice;
        int n;
        block25: {
            MappableRingBuffer mappableRingBuffer;
            block24: {
                block23: {
                    n = EffectEngine.a;
                    boolean bl = broken;
                    if (n != 0) return bl;
                    if (bl) {
                        return false;
                    }
                    gpuDevice = RenderSystem.getDevice();
                    if (quad == null) {
                        memoryStack = MemoryStack.stackPush();
                        try {
                            ByteBuffer byteBuffer = memoryStack.malloc(48);
                            byteBuffer.asFloatBuffer().put(new float[]{-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f});
                            if (n != 0) break block23;
                            byteBuffer.rewind();
                            quad = gpuDevice.createBuffer(PaintedSky::lambda$ensureResources$4, 32, byteBuffer);
                            if (memoryStack == null) break block23;
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
                mappableRingBuffer = params;
                if (n != 0) break block24;
                if (mappableRingBuffer != null) break block25;
                mappableRingBuffer = new MappableRingBuffer(PaintedSky::lambda$ensureResources$5, 130, 544);
            }
            params = mappableRingBuffer;
        }
        GpuTexture gpuTexture = noiseTex;
        if (n == 0) {
            if (gpuTexture != null) return true;
            gpuTexture = gpuDevice.createTexture(a[9], 5, TextureFormat.RGBA8, 256, 256, 1, 1);
        }
        noiseTex = gpuTexture;
        noiseView = gpuDevice.createTextureView(noiseTex);
        memoryStack = PaintedSky.noise();
        try {
            gpuDevice.createCommandEncoder().writeToTexture(noiseTex, (NativeImage)memoryStack);
            if (n != 0) return true;
            if (memoryStack == null) return true;
        }
        catch (Throwable throwable) {
            if (n != 0) throw throwable;
            if (memoryStack == null) throw throwable;
            try {
                memoryStack.close();
                throw throwable;
            }
            catch (Throwable throwable3) {
                throwable.addSuppressed(throwable3);
            }
            throw throwable;
        }
        memoryStack.close();
        return true;
    }

    static NativeImage noise() {
        int n = 256;
        int[] nArray = new int[n * n];
        int[] nArray2 = new int[n * n];
        int[] nArray3 = new int[n * n];
        int n2 = 1337;
        int n3 = EffectEngine.a;
        for (int i = 0; i < n * n; ++i) {
            n2 = (int)((double)n2 * 1.103515245E9 + 12345.0) & PaintedSky.a(20945, 3628151962833452784L);
            nArray[i] = n2 >> 16 & 0xFF;
            n2 = (int)((double)n2 * 1.103515245E9 + 12345.0) & PaintedSky.a(7191, 2894780081678299956L);
            nArray2[i] = n2 >> 16 & 0xFF;
            n2 = (int)((double)n2 * 1.103515245E9 + 12345.0) & PaintedSky.a(7191, 2894780081678299956L);
            nArray3[i] = n2 >> 16 & 0xFF;
            if (n3 == 0) continue;
        }
        NativeImage nativeImage = new NativeImage(n, n, false);
        int n4 = 0;
        while (n4 < n) {
            block4: {
                for (int i = 0; i < n; ++i) {
                    if (n3 == 0) {
                        int n5 = n4 * n + i;
                        int n6 = nArray[(n4 + 17 & 0xFF) * n + (i + 37 & 0xFF)];
                        nativeImage.method_61941(i, n4, nArray3[n5] << 24 | nArray[n5] << 16 | n6 << 8 | nArray2[n5]);
                        if (n3 == 0) continue;
                    }
                    break block4;
                }
                ++n4;
            }
            if (n3 == 0) continue;
        }
        return nativeImage;
    }

    private static void ensureLow(int n, int n2) {
        GpuTexture gpuTexture;
        block20: {
            block21: {
                int n3;
                block19: {
                    GpuTextureView gpuTextureView;
                    block18: {
                        block16: {
                            int n4;
                            GpuTexture gpuTexture2;
                            block17: {
                                block15: {
                                    n3 = EffectEngine.a;
                                    gpuTexture2 = lowTex;
                                    if (n3 != 0) break block15;
                                    if (gpuTexture2 == null) break block16;
                                    gpuTexture2 = lowTex;
                                }
                                n4 = 0;
                                if (n3 != 0) break block17;
                                if (gpuTexture2.getWidth(n4) != n) break block16;
                                gpuTexture2 = lowTex;
                                n4 = 0;
                            }
                            if (gpuTexture2.getHeight(n4) == n2) {
                                return;
                            }
                        }
                        gpuTextureView = lowView;
                        if (n3 != 0) break block18;
                        if (gpuTextureView == null) break block19;
                        gpuTextureView = lowView;
                    }
                    gpuTextureView.close();
                }
                gpuTexture = lowTex;
                if (n3 != 0) break block20;
                if (gpuTexture == null) break block21;
                lowTex.close();
            }
            gpuTexture = RenderSystem.getDevice().createTexture(a[28], 12, TextureFormat.RGBA8, n, n2, 1, 1);
        }
        lowTex = gpuTexture;
        lowView = RenderSystem.getDevice().createTextureView(lowTex);
    }

    
    private static RenderPipeline build(String string, String string2) {
        int n;
        RenderPipeline.Builder builder;
        block15: {
            block14: {
                String string3;
                block13: {
                    String[] stringArray = a;
                    builder = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(Identifier.method_60655((String)stringArray[100], (String)(stringArray[66] + string))).withVertexShader(VERTEX).withFragmentShader(Identifier.method_60655((String)stringArray[100], (String)(stringArray[43] + string))).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withVertexFormat(VertexFormats.field_1592, VertexFormat.DrawMode.field_27382).withoutBlend();
                    n = EffectEngine.a;
                    string3 = string2;
                    if (n == 0) {
                        if (string3 != null) {
                            builder.withSampler(string2);
                        }
                        string3 = string2;
                    }
                    if (n != 0) break block13;
                    if (string3 == null) break block14;
                    string3 = string2;
                }
                if (!string3.equals(a[8])) break block15;
            }
            builder.withUniform(a[130], UniformType.field_60031);
        }
        RenderPipeline renderPipeline = builder.build();
        if (n == 0) {
            if (RenderSystem.getDevice().precompilePipeline(renderPipeline, null).isValid()) {
                return renderPipeline;
            }
            PaintedSky.disable(new IllegalStateException(string + a[138]));
        }
        return null;
    }

    private static void bindQuad(RenderPass renderPass) {
        RenderSystem.ShapeIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer((VertexFormat.DrawMode)VertexFormat.DrawMode.field_27382);
        renderPass.setVertexBuffer(0, quad);
        renderPass.setIndexBuffer(shapeIndexBuffer.method_68274(6), shapeIndexBuffer.method_31924());
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
        String[] stringArray = a;
        LoggerFactory.getLogger((String)stringArray[100]).error(stringArray[48], throwable);
    }

    private static String lambda$ensureResources$5() {
        return a[87];
    }

    private static String lambda$ensureResources$4() {
        return a[114];
    }

    private static String lambda$fantasy$3() {
        return a[140];
    }

    private static String lambda$draw$2() {
        return a[81];
    }

    private static String lambda$draw$1() {
        return a[96];
    }

    private static String lambda$styled$0(SkyModule$Style skyModule$Style) {
        String[] stringArray = a;
        return stringArray[45] + skyModule$Style.id + stringArray[22];
    }

    
    static {
        block30: {
            block29: {
                block28: {
                    block27: {
                        block26: {
                            var15 = new String[165];
                            var13_1 = 0;
                            var12_2 = "\u001d\n\u001f!).\n\u0000\t\u0011!\r =\u0019\u001f\u0015\b\u001e\f\u0017'\u001c\u001c\u0001\u0002\b\u001d\n\u001f!;=\u0014\u0015\u0006\u0014\u0004\t\u0015\r:\u0004\u0014\u0010\u0003$\u0004\u0011\u0010\u0002}\u0007\u0003\u0011\u0011=\u001b\u001b\u0000\u0005>\n\u0019<\r\u0018\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6H:\u0001\u0019\u0016\u0015\u0007\u0014\u0004\u0007!21\u0000\t\u0017\u0017\u001f:\u00060-\u001f\t\u0007\u0003\u0015\u0011=\u00038\u000b\u000b\u0018\n\u0002&\u0012;\u00007\t\u001f8\u0003\u0018\u0010\u0015\u000b\u0011\u0010\u0002 \u001a5=\u0000\u0000\u0015+\t\u0013\t\u001f:\f\u0007\u0001\u0016\u0011\u0004\u0004\f\u001c;\u0011\u0019\u0016#'\t0\u000b\u00025\u0011,\u0003\u001d\u0000%\u0016\u0015\t9\u000b#.\u0005$\u0002\u0015\u0017\b\u001d\n\u001f!;=\u0014\u0015\u0006\u0003\u0010\u001e\f\u00078\u000bP\u0016\u001b6H$\u000f\u0002\u0004\u001d<\u0006\u0014\u0004\t\u0007\u0007&\u0007\u0007\f\u001e+,=\u001c\u0006\u0013\f\u0002=\u001d'\u000b\u0003\u0011\u0011=,1\u0000\u0003\f\u00046\u0005\u001e\u0000\u0012\u0000\u0006\u0016\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6H8\u0001\u0007\u0007\u0002\f\u0006*\u001a\u001b\u0000\u0010\u0000\u0004\u0019!\u001c1\n/\u0016\u001b676\u0002\u0019\u0011\b\u0002\f\u001e(:;\u0002\u001c\b\u001e\f\u0017'\u001c\u0019\u0007\u0014\u0005\u001e\f\u0017'\u001c\n\u001d\u0000\u0004*\u0007&<\u0011\u0011\u0015\u0003\u0016\n\u0017\b\u001e\u0000\u0012\u001f\u0001 \r\u0018\t\u0002\f\u001e('!\u001a\u0015\u0017\b\u0013\n\u0006*\u001a5\t\u0015\n\u0012\u0004\u001e$ 1\u0007\u0017\r\u0004\u0006\u0003\u0010\u001e\u0003\u0007#\t\u0012\u0004\u001e$+;\u0018\u0015\u0017\u0006\u0003\u0010\u001e\u0003\u0007#\u0005\u0013\n\u0002*G\b\u0000\t\u0011!\r +\u001c\u0007\u0011\u0010\u0002 \u001a5N\u0007\u0014\u0010\u0003$21\u0000\u0007\u0014\u0004\u0007! ;\u001c. \u0004\u0019!\u001c1\nP\u0016\u001b6H0\u0007\u0003\u0004\u0012#\r0BP\u0013\u0011!\u00018\u0002\u0011E\u0003$\u0011t\b\u0002\n\u001do\u00001\u001c\u0015E\u001f!\u0005\u0012\u0004\u001e+\u001b\b\u0002\f\u001e(<=\u0002\u0004\n\u0000\t\u0011!\r /\u0004\b\u001f\u000e\u0011\u0010\u0002 \u001a5=\u0004\u0017\u0015!\u000f \u0006\u000b\u0003\u0011\u0011=,1\u0000\u0003\f\u00046\u0004\u0019\u0017\u0019<\n\u0000\t\u0011!\r -\u001f\tA\n\u0003\u0004\u0004:\u001a5\u001a\u0019\n\u001e\t\u001e\u0000\u0012\r\u001a=\t\u0018\u0011\u0006\u0011\u0011\u001d ':\u0004\u0012\u0000\u001c;\u0007\u0012\u0004\u001e+\u0001:\t\n\u0013\t\u001f:\f\u0018\u0007\u0017\r\u0004\n\u0011\u0010\u0002 \u001a5-\u001f\tB\u000b\u0011\u0010\u0002 \u001a5=\u0000\u0000\u0015+\b\u0011\u0010\u0002 \u001a5/\n\u0006\u0012\u0004\u001e$':\t\u0000\f\u0000*\u0004=\u0000\u0015J\u000b\u0017\u0000\u0004\u0006\u0006'\u001a\u0011\u000b\u0013*\u0007\u0003\u0010\u001e\u001c\u0001.\u000b\b\u0013\t\u001f:\f\u001b\r\u0004\n\u0000\t\u0011!\r -\u001f\tB\u0007\u0014\u0010\u0003$ ;\u001c\u0010\u0000\u0004\u0019!\u001c1\n/\u0016\u001b676\u0002\u0019\u0011\t#\u000e\t\u001f\t&\u000f\u001d\u0016\u0007\u0002\f\u001e(\u001b\u001b\u0000\u0007\u0004\u0012\u0019!\u00038\u000b\n\u001c\f\u0017'\u001c\u0004\u0006\u0011\u0016\u0015\t\u0006\u0010\u001c$\t:\u0003\u001f\u0001\b\u001e\u0000\u0012\u001c\u000b5\u0002\u0015\u0007\u0003\u0011\u0011=\u001b\u001b\u0000\b\u0013\t\u001f:\f'!\u001e\u001a\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6H!\u001e\u0003\u0006\u0011#\r\u0006\u0003\u0010\u001e\f\u00078\u0004\u0017\t\u001f8\t\u001d\u0000\u0004*\u0007&\u001d?\u000b\n\u0012\u0017\u0019(\u0000 \u0000\u0015\u0016\u0003\t\u0004\u0010\u0002-\u00010\u0007\u0004\u001c\u0019\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6H$\u000f\u0002\u0004\u001d<\b\u0011\u0010\u0002 \u001a5!\u001e\f\u0011\u0010\u0002 \u001a5=\u0000\u0017\u0015.\f\n\u0013\t\u001f:\f\u0007\r\u0011\t\u0015\b\u0013\t\u001f:\f'!\u001e\t\u0002\f\u001e(!:\u0000\u0015\u0017\u0004\u0018\u0004\u001c \u000b\u0018\n\u0002&\u0012;\u00007\t\u001f8\u0003\u0014\u0004\t\u0012\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6\b\u0013\n\u0006*\u001a5\t\u0015\t\u0016\n\u001c#\u0007#=\u0005\u000b\u0007\u001e\u0000\u0012\f\u00078]\u0006\u0011\u0010\u0002 \u001a5\u0007\u0004\u0012\u0019!\u00038\u000b\u0004\u001e\u0000\u0012~\u0003\u0018\u0010\u0015\n\u0011\u0010\u0002 \u001a5-\u001f\tC\u0007\u001e\u0000\u0012\f\u00078_\n\u001d\u0000\u0004*\u0007&<\u0011\u0011\u0015\u000b\u0016\u0004\u001e;\t'\u0017/\u0016\u001b6\t9\u000b#.\u0005$\u0002\u0015\u0017\b\u0015\u001d\u0000 \u001b!\u001c\u0015\u000b\u0000\u0004\u0019!\u001c1\n/\u0016\u001b6\n\u0011\u0010\u0002 \u001a5-\u001f\tA\u0006\u0014\u0004\t\u0002\u00010\n\u0003\u0011\u0011=*&\u0007\u0017\r\u0004\u0017\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6H%\u001b\u0011\u0001\b\u0011\u0010\u0002 \u001a5!\u001e\u0005\u0017\u0017\u0011&\u0006\u0007\u0014\u0004\u0007!%=\n\u000b\u0000\t\u0011!\r ,\u0011\u000b\u0014<\b\u001e\u0000\u0012\u0018\u00010\u001a\u0018\b\u0000\t\u0011!\r !\u001e\u0004\u001e\u0000\u0012}\u0004\u0003\u000e\t\u0010\b\u001e\f\u0017'\u001c\u000e\u000b\u001e\b\u0012\u0004\u001e+;;\b\u0004\b\u001e\f\u0017'\u001c\u000e\u000b\u001e\u0004\u0011\u0010\u0002~\u0007\u001e\u0000\u0012\u0018\t'\u0006\b\u001e\f\u0017'\u001c\u001c\u0001\u0002\u0007\u001d\n\u001f!+;\u0002\t#\u000e\t\u001f\t&\u000f\u001d\u0016\u0006\u001d\n\u001f!':\b\u0015\u001d\u0000 \u001b!\u001c\u0015\u0006\u0011\u0010\u0002 \u001a5\b\u0000\t\u0011!\r !\u001e\t\u001c\f\u0017'\u001c\u0006\u0001\u001c\t\u000b\u0002\f\u001e('$\u000f\u0013\f\u00046\u0007\u0002\f\u001e(+;\u0002#P\u0001\u0019+H:\u0001\u0004E\u0013 \u0005$\u0007\u001c\u0000\\o\u001b1\u000bP\u0011\u0018*H8\u0001\u0017E\u0011-\u0007\"\u000b\u0007\u0003\u0010\u001e\u001c\u0001.\u000b\u0019\u0011\u0010\u0002 \u001a5N\u0016\u0004\u001e;\t'\u0017P\u0016\u001b6H$\u000f\u0002\u0004\u001d<#\u001e\u0000\u0004a\u0001&\u0007\u0003\u0016\u0018.\f1\u001c\u0003K\u0019=\u0001'@\u0011\u0015\u0019a\u001ed@9\u0017\u0019<)$\u0007\u0006\u0011\u0011\u001d ':\u0006\u001d\n\u001f!-8\u0007\u001e\u0000\u0012\u000b\u001d'\u001a\u0007\u0003\u0011\u0011=+;\u0002\u000b\u0013\t\u001f:\f\u0007\u0006\u0011\u0001\u001f8\u000b\u0011\u0010\u0002 \u001a5=\u0004\n\u0002\"\n\u0003\u0011\u0011=*&\u0007\u0017\r\u0004\u0007\u001d\n\u001f!+;\u0002\u000f\u0013\n\u0002*G3\u0002\u0011\u0016\u0003\u0010\n8\u0007\u0004\u0005>\n\u0019<\r\u0007\u001e\u0000\u0012\f\u00078\\\b\u0011\u0011\u001d /8\u0001\u0007\f\u0011\u0010\u0002 \u001a5&\u0015\f\u0017'\u001c\u0007\u0014\u0010\u0003$%=\n\u0005\u0002\f\u0006*\u001a\t\u0007\f\u001e+;$\u000b\u0015\u0001\b\u0000\t\u0011!\r /\n\u0005\u0012\u0017\u0005<\u0000\f\u0000\t\u0011!\r ,\u0002\f\u0017'\u001c\u0004\u0018\u0004\n*\u0006\u001e\u0000\u0012\u0016\t#";
                            var14_3 = "\u001d\n\u001f!).\n\u0000\t\u0011!\r =\u0019\u001f\u0015\b\u001e\f\u0017'\u001c\u001c\u0001\u0002\b\u001d\n\u001f!;=\u0014\u0015\u0006\u0014\u0004\t\u0015\r:\u0004\u0014\u0010\u0003$\u0004\u0011\u0010\u0002}\u0007\u0003\u0011\u0011=\u001b\u001b\u0000\u0005>\n\u0019<\r\u0018\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6H:\u0001\u0019\u0016\u0015\u0007\u0014\u0004\u0007!21\u0000\t\u0017\u0017\u001f:\u00060-\u001f\t\u0007\u0003\u0015\u0011=\u00038\u000b\u000b\u0018\n\u0002&\u0012;\u00007\t\u001f8\u0003\u0018\u0010\u0015\u000b\u0011\u0010\u0002 \u001a5=\u0000\u0000\u0015+\t\u0013\t\u001f:\f\u0007\u0001\u0016\u0011\u0004\u0004\f\u001c;\u0011\u0019\u0016#'\t0\u000b\u00025\u0011,\u0003\u001d\u0000%\u0016\u0015\t9\u000b#.\u0005$\u0002\u0015\u0017\b\u001d\n\u001f!;=\u0014\u0015\u0006\u0003\u0010\u001e\f\u00078\u000bP\u0016\u001b6H$\u000f\u0002\u0004\u001d<\u0006\u0014\u0004\t\u0007\u0007&\u0007\u0007\f\u001e+,=\u001c\u0006\u0013\f\u0002=\u001d'\u000b\u0003\u0011\u0011=,1\u0000\u0003\f\u00046\u0005\u001e\u0000\u0012\u0000\u0006\u0016\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6H8\u0001\u0007\u0007\u0002\f\u0006*\u001a\u001b\u0000\u0010\u0000\u0004\u0019!\u001c1\n/\u0016\u001b676\u0002\u0019\u0011\b\u0002\f\u001e(:;\u0002\u001c\b\u001e\f\u0017'\u001c\u0019\u0007\u0014\u0005\u001e\f\u0017'\u001c\n\u001d\u0000\u0004*\u0007&<\u0011\u0011\u0015\u0003\u0016\n\u0017\b\u001e\u0000\u0012\u001f\u0001 \r\u0018\t\u0002\f\u001e('!\u001a\u0015\u0017\b\u0013\n\u0006*\u001a5\t\u0015\n\u0012\u0004\u001e$ 1\u0007\u0017\r\u0004\u0006\u0003\u0010\u001e\u0003\u0007#\t\u0012\u0004\u001e$+;\u0018\u0015\u0017\u0006\u0003\u0010\u001e\u0003\u0007#\u0005\u0013\n\u0002*G\b\u0000\t\u0011!\r +\u001c\u0007\u0011\u0010\u0002 \u001a5N\u0007\u0014\u0010\u0003$21\u0000\u0007\u0014\u0004\u0007! ;\u001c. \u0004\u0019!\u001c1\nP\u0016\u001b6H0\u0007\u0003\u0004\u0012#\r0BP\u0013\u0011!\u00018\u0002\u0011E\u0003$\u0011t\b\u0002\n\u001do\u00001\u001c\u0015E\u001f!\u0005\u0012\u0004\u001e+\u001b\b\u0002\f\u001e(<=\u0002\u0004\n\u0000\t\u0011!\r /\u0004\b\u001f\u000e\u0011\u0010\u0002 \u001a5=\u0004\u0017\u0015!\u000f \u0006\u000b\u0003\u0011\u0011=,1\u0000\u0003\f\u00046\u0004\u0019\u0017\u0019<\n\u0000\t\u0011!\r -\u001f\tA\n\u0003\u0004\u0004:\u001a5\u001a\u0019\n\u001e\t\u001e\u0000\u0012\r\u001a=\t\u0018\u0011\u0006\u0011\u0011\u001d ':\u0004\u0012\u0000\u001c;\u0007\u0012\u0004\u001e+\u0001:\t\n\u0013\t\u001f:\f\u0018\u0007\u0017\r\u0004\n\u0011\u0010\u0002 \u001a5-\u001f\tB\u000b\u0011\u0010\u0002 \u001a5=\u0000\u0000\u0015+\b\u0011\u0010\u0002 \u001a5/\n\u0006\u0012\u0004\u001e$':\t\u0000\f\u0000*\u0004=\u0000\u0015J\u000b\u0017\u0000\u0004\u0006\u0006'\u001a\u0011\u000b\u0013*\u0007\u0003\u0010\u001e\u001c\u0001.\u000b\b\u0013\t\u001f:\f\u001b\r\u0004\n\u0000\t\u0011!\r -\u001f\tB\u0007\u0014\u0010\u0003$ ;\u001c\u0010\u0000\u0004\u0019!\u001c1\n/\u0016\u001b676\u0002\u0019\u0011\t#\u000e\t\u001f\t&\u000f\u001d\u0016\u0007\u0002\f\u001e(\u001b\u001b\u0000\u0007\u0004\u0012\u0019!\u00038\u000b\n\u001c\f\u0017'\u001c\u0004\u0006\u0011\u0016\u0015\t\u0006\u0010\u001c$\t:\u0003\u001f\u0001\b\u001e\u0000\u0012\u001c\u000b5\u0002\u0015\u0007\u0003\u0011\u0011=\u001b\u001b\u0000\b\u0013\t\u001f:\f'!\u001e\u001a\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6H!\u001e\u0003\u0006\u0011#\r\u0006\u0003\u0010\u001e\f\u00078\u0004\u0017\t\u001f8\t\u001d\u0000\u0004*\u0007&\u001d?\u000b\n\u0012\u0017\u0019(\u0000 \u0000\u0015\u0016\u0003\t\u0004\u0010\u0002-\u00010\u0007\u0004\u001c\u0019\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6H$\u000f\u0002\u0004\u001d<\b\u0011\u0010\u0002 \u001a5!\u001e\f\u0011\u0010\u0002 \u001a5=\u0000\u0017\u0015.\f\n\u0013\t\u001f:\f\u0007\r\u0011\t\u0015\b\u0013\t\u001f:\f'!\u001e\t\u0002\f\u001e(!:\u0000\u0015\u0017\u0004\u0018\u0004\u001c \u000b\u0018\n\u0002&\u0012;\u00007\t\u001f8\u0003\u0014\u0004\t\u0012\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6\b\u0013\n\u0006*\u001a5\t\u0015\t\u0016\n\u001c#\u0007#=\u0005\u000b\u0007\u001e\u0000\u0012\f\u00078]\u0006\u0011\u0010\u0002 \u001a5\u0007\u0004\u0012\u0019!\u00038\u000b\u0004\u001e\u0000\u0012~\u0003\u0018\u0010\u0015\n\u0011\u0010\u0002 \u001a5-\u001f\tC\u0007\u001e\u0000\u0012\f\u00078_\n\u001d\u0000\u0004*\u0007&<\u0011\u0011\u0015\u000b\u0016\u0004\u001e;\t'\u0017/\u0016\u001b6\t9\u000b#.\u0005$\u0002\u0015\u0017\b\u0015\u001d\u0000 \u001b!\u001c\u0015\u000b\u0000\u0004\u0019!\u001c1\n/\u0016\u001b6\n\u0011\u0010\u0002 \u001a5-\u001f\tA\u0006\u0014\u0004\t\u0002\u00010\n\u0003\u0011\u0011=*&\u0007\u0017\r\u0004\u0017\u0011\u0010\u0002 \u001a5N\u0000\u0004\u0019!\u001c1\nP\u0016\u001b6H%\u001b\u0011\u0001\b\u0011\u0010\u0002 \u001a5!\u001e\u0005\u0017\u0017\u0011&\u0006\u0007\u0014\u0004\u0007!%=\n\u000b\u0000\t\u0011!\r ,\u0011\u000b\u0014<\b\u001e\u0000\u0012\u0018\u00010\u001a\u0018\b\u0000\t\u0011!\r !\u001e\u0004\u001e\u0000\u0012}\u0004\u0003\u000e\t\u0010\b\u001e\f\u0017'\u001c\u000e\u000b\u001e\b\u0012\u0004\u001e+;;\b\u0004\b\u001e\f\u0017'\u001c\u000e\u000b\u001e\u0004\u0011\u0010\u0002~\u0007\u001e\u0000\u0012\u0018\t'\u0006\b\u001e\f\u0017'\u001c\u001c\u0001\u0002\u0007\u001d\n\u001f!+;\u0002\t#\u000e\t\u001f\t&\u000f\u001d\u0016\u0006\u001d\n\u001f!':\b\u0015\u001d\u0000 \u001b!\u001c\u0015\u0006\u0011\u0010\u0002 \u001a5\b\u0000\t\u0011!\r !\u001e\t\u001c\f\u0017'\u001c\u0006\u0001\u001c\t\u000b\u0002\f\u001e('$\u000f\u0013\f\u00046\u0007\u0002\f\u001e(+;\u0002#P\u0001\u0019+H:\u0001\u0004E\u0013 \u0005$\u0007\u001c\u0000\\o\u001b1\u000bP\u0011\u0018*H8\u0001\u0017E\u0011-\u0007\"\u000b\u0007\u0003\u0010\u001e\u001c\u0001.\u000b\u0019\u0011\u0010\u0002 \u001a5N\u0016\u0004\u001e;\t'\u0017P\u0016\u001b6H$\u000f\u0002\u0004\u001d<#\u001e\u0000\u0004a\u0001&\u0007\u0003\u0016\u0018.\f1\u001c\u0003K\u0019=\u0001'@\u0011\u0015\u0019a\u001ed@9\u0017\u0019<)$\u0007\u0006\u0011\u0011\u001d ':\u0006\u001d\n\u001f!-8\u0007\u001e\u0000\u0012\u000b\u001d'\u001a\u0007\u0003\u0011\u0011=+;\u0002\u000b\u0013\t\u001f:\f\u0007\u0006\u0011\u0001\u001f8\u000b\u0011\u0010\u0002 \u001a5=\u0004\n\u0002\"\n\u0003\u0011\u0011=*&\u0007\u0017\r\u0004\u0007\u001d\n\u001f!+;\u0002\u000f\u0013\n\u0002*G3\u0002\u0011\u0016\u0003\u0010\n8\u0007\u0004\u0005>\n\u0019<\r\u0007\u001e\u0000\u0012\f\u00078\\\b\u0011\u0011\u001d /8\u0001\u0007\f\u0011\u0010\u0002 \u001a5&\u0015\f\u0017'\u001c\u0007\u0014\u0010\u0003$%=\n\u0005\u0002\f\u0006*\u001a\t\u0007\f\u001e+;$\u000b\u0015\u0001\b\u0000\t\u0011!\r /\n\u0005\u0012\u0017\u0005<\u0000\f\u0000\t\u0011!\r ,\u0002\f\u0017'\u001c\u0004\u0018\u0004\n*\u0006\u001e\u0000\u0012\u0016\t#".length();
                            var11_4 = 6;
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
                                var12_2 = "\u0003\u0004\u0004:\u001a5\u001a\u0019\n\u001e\t\u001d\u0000\u0004*\u0007&\u001d?\u000b";
                                var14_3 = "\u0003\u0004\u0004:\u001a5\u001a\u0019\n\u001e\t\u001d\u0000\u0004*\u0007&\u001d?\u000b".length();
                                var11_4 = 10;
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
                                    v10 = 112;
                                    break;
                                }
                                case 1: {
                                    v10 = 101;
                                    break;
                                }
                                case 2: {
                                    v10 = 112;
                                    break;
                                }
                                case 3: {
                                    v10 = 79;
                                    break;
                                }
                                case 4: {
                                    v10 = 104;
                                    break;
                                }
                                case 5: {
                                    v10 = 84;
                                    break;
                                }
                                default: {
                                    v10 = 110;
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
                    PaintedSky.a = var15;
                    var2_8 = 4115127448817200133L;
                    var8_9 = new long[13];
                    var5_10 = 0;
                    var6_11 = "\u00db\u001a\u0003=x\u0085 \u00ce\u0017\u00c2\u00e4\u00ac\u0096+c.\u00aa\u00a0\u00b6\u00f7\u0018\u0018m\n\u00cfW\u009aI\u00ed\u00b3!\u0083\u00ccr(\u0091\u0017nc\u0015\u00b6\u00be\u00d2\u00f6\u0089%6c\u0089\u0081\u0019b\u00f4\u00dd\u00b6\u00a9\u001b\u00ec\u008a\u00165\u0097\u00ad\t\u00ebJ\u00cd\u00bb5\u0080b:\u00d5=a\u00d2E\u00db@\u00d1\u0087\u00e6x1\u00f2{\u008f\u00d6";
                    var7_12 = "\u00db\u001a\u0003=x\u0085 \u00ce\u0017\u00c2\u00e4\u00ac\u0096+c.\u00aa\u00a0\u00b6\u00f7\u0018\u0018m\n\u00cfW\u009aI\u00ed\u00b3!\u0083\u00ccr(\u0091\u0017nc\u0015\u00b6\u00be\u00d2\u00f6\u0089%6c\u0089\u0081\u0019b\u00f4\u00dd\u00b6\u00a9\u001b\u00ec\u008a\u00165\u0097\u00ad\t\u00ebJ\u00cd\u00bb5\u0080b:\u00d5=a\u00d2E\u00db@\u00d1\u0087\u00e6x1\u00f2{\u008f\u00d6".length();
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
lbl89:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_13 < var7_12) continue;
                        var6_11 = "\u00d5C\u00a8\u00d7\u00daIK\u00d4rJ\u00daY\u00d4\u008e=m";
                        var7_12 = "\u00d5C\u00a8\u00d7\u00daIK\u00d4rJ\u00daY\u00d4\u008e=m".length();
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
lbl102:

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
lbl113:

                    continue;
                }
            }
            PaintedSky.b = var8_9;
            PaintedSky.c = new Integer[13];
            PaintedSky.HOR = PaintedSky.a(1015, 4043143347134409949L);
            PaintedSky.HAZE = PaintedSky.a(8463, 7127601180589755940L);
            PaintedSky.GLOW = PaintedSky.a(32331, 140111872492523876L);
            PaintedSky.MID = PaintedSky.a(28206, 8538191104860930314L);
            PaintedSky.LIT = PaintedSky.a(8219, 3238760926729342777L);
            PaintedSky.SHADE = PaintedSky.a(2284, 152797423570003908L);
            PaintedSky.FANTASY_SIZE = PaintedSky.a(8791, 2564265288910179696L);
            PaintedSky.RIM = PaintedSky.a(19682, 3746462083480216523L);
            PaintedSky.PARAMS_SIZE = PaintedSky.a(24710, 156518719919902630L);
            PaintedSky.BELT = PaintedSky.a(30031, 4318269843616848489L);
            PaintedSky.ZEN = PaintedSky.a(30090, 1907689036809034415L);
            break block30;
lbl129:

            while (true) {
                continue;
                break;
            }
        }
        var0_15 = 8527898975302510596L;
        while (true)
        PaintedSky.d = 8527898974857208324L ^ var0_15;
        var10_6 = PaintedSky.a;
        PaintedSky.VULKAN = FabricLoader.getInstance().isModLoaded(var10_6[77]);
        PaintedSky.IRIS = FabricLoader.getInstance().isModLoaded(var10_6[54]);
        PaintedSky.VERTEX = Identifier.method_60655((String)var10_6[133], (String)var10_6[150]);
        PaintedSky.WHITE = new float[]{1.0f, 1.0f, 1.0f};
        PaintedSky.styledPipelines = new EnumMap<SkyModule$Style, RenderPipeline>(SkyModule$Style.class);
        PaintedSky.styledParams = new EnumMap<SkyModule$Style, MappableRingBuffer>(SkyModule$Style.class);
        PaintedSky.view = new Matrix4f();
        PaintedSky.proj = new Matrix4f();
        PaintedSky.lastYaw = NaN;
        PaintedSky.invViewProj = new Matrix4f();
        PaintedSky.viewProj = new Matrix4f();
        PaintedSky.celMat = new Matrix4f();
        PaintedSky.sunDir = new Vector3f();
        PaintedSky.planetPos = new Vector3f();
        PaintedSky.planetRight = new Vector3f();
        PaintedSky.planetUp = new Vector3f();
        PaintedSky.ringAxis = new Vector3f();
        PaintedSky.bodyL = new Vector3f();
        PaintedSky.moonPos = new Vector3f();
        PaintedSky.eclipse = new Vector3f();
        PaintedSky.zen = new double[3];
        PaintedSky.atm = new double[3];
        PaintedSky.trans = new double[3];
        PaintedSky.tau = new double[3];
        PaintedSky.sR = new double[3];
        PaintedSky.sM = new double[3];
        PaintedSky.fantasyFog = new float[3];
        PaintedSky.BR = new double[]{0.005802, 0.013558, 0.0331};
        PaintedSky.BO = new double[]{6.5E-4, 0.001881, 8.5E-5};
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2F23;
        if (c[n2] == null) {
            PaintedSky.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
