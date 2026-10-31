package dev.quelchi.aurora.render;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.modules.RendersModule;
import dev.quelchi.aurora.render.EffectEngine;
import java.util.Iterator;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.BatchingRenderCommandQueue;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.OrderedRenderCommandQueueImpl;
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3fc;

public final class PlayerMask {
    private static final BufferAllocator OPAQUE;
    private static final BufferAllocator BLENDED;
    private static final ModelCommandRenderer COMMANDS;
    private static SimpleFramebuffer maskFbo;
    private static int fboW;
    private static int fboH;
    private static final String[] a;

    private PlayerMask() {
    }

    private static boolean behindCamera(Entity entity, Vec3d vec3d, Vector3fc vector3fc) {
        double d;
        block2: {
            block3: {
                Vec3d vec3d2 = entity.method_5829().method_1005();
                int n = EffectEngine.a;
                double d2 = (vec3d2.field_1352 - vec3d.field_1352) * (double)vector3fc.x() + (vec3d2.field_1351 - vec3d.field_1351) * (double)vector3fc.y() + (vec3d2.field_1350 - vec3d.field_1350) * (double)vector3fc.z();
                double d3 = d2 + 2.0 - 0.0;
                d = d3 == 0.0 ? 0 : (d3 < 0.0 ? -1 : 1);
                if (n != 0) break block2;
                if (d >= 0) break block3;
                d = 1.0;
                break block2;
            }
            d = 0.0;
        }
        return (boolean)d;
    }

    private static boolean draw(EntityRenderManager entityRenderManager, Entity entity, CameraRenderState cameraRenderState, Vec3d vec3d, float f, MatrixStack matrixStack, OrderedRenderCommandQueueImpl orderedRenderCommandQueueImpl) {
        try {
            EntityRenderState entityRenderState = entityRenderManager.method_72977(entity, f);
            Vec3d vec3d2 = entity.method_30950(f);
            entityRenderManager.method_72976(entityRenderState, cameraRenderState, vec3d2.field_1352 - vec3d.field_1352, vec3d2.field_1351 - vec3d.field_1351, vec3d2.field_1350 - vec3d.field_1350, matrixStack, (OrderedRenderCommandQueue)orderedRenderCommandQueueImpl);
            return true;
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    
    public static void onWorldRender(WorldRenderContext var0) {
        block38: {
            block37: {
                block36: {
                    block35: {
                        block34: {
                            block45: {
                                block33: {
                                    block44: {
                                        block43: {
                                            block42: {
                                                var1_1 = EffectEngine.a;
                                                if (!RendersModule.maskActive()) {
                                                    return;
                                                }
                                                var2_2 = MinecraftClient.method_1551();
                                                var3_3 = var2_2.method_1522();
                                                var4_4 = var0.matrices();
                                                if (var3_3 == null) // goto
                                                if (var4_4 == null) // goto
                                                break block42;
                                                catch (Throwable v1) {
                                                    throw v1;
                                                }
                                            }
                                            v2 = var2_2;
                                            if (var1_1 != 0) break block33;
                                            break block43;
                                            catch (Throwable v3) {
                                                throw v3;
                                            }
                                        }
                                        if (v2.field_1687 == null) // goto
                                        break block44;
                                        catch (Throwable v4) {
                                            throw v4;
                                        }
                                    }
                                    v2 = var2_2;
                                }
                                if (var1_1 != 0) break block34;
                                if (v2.field_1724 == null) // goto
                                break block45;
                                catch (Throwable v6) {
                                    throw v6;
                                }
                            }
                            v2 = var2_2;
                        }
                        if (v2.field_1773 != null) break block35;
lbl52:

                        return;
                    }
                    var5_5 = var3_3.field_1482;
                    var6_6 = var3_3.field_1481;
                    v9 = var5_5;
                    if (var1_1 != 0) break block36;
                    if (v9 <= 0) break block37;
                    v9 = var6_6;
                }
                if (var1_1 == 0) {
                    if (v9 > 0) break block38;
                }
            }
            return;
        }
        try {
            block39: {
                block40: {
                    block46: {
                        v9 = var5_5;
lbl79:

                        PlayerMask.ensureFbo(v9, var6_6);
                        var7_7 = var2_2.field_1773.method_19418();
                        var8_9 = var7_7.method_71156();
                        var9_10 = var2_2.method_61966().method_60637(false);
                        var10_11 = 128.0;
                        var12_12 = var2_2.method_1561();
                        var13_13 = new CameraRenderState();
                        var13_13.field_63078 = var8_9;
                        var13_13.field_63080 = var8_9;
                        var13_13.field_63081 = var7_7.method_23767();
                        var13_13.field_63077 = BlockPos.method_49638((Position)var8_9);
                        var13_13.field_63079 = true;
                        var14_14 = var7_7.method_19335();
                        v12 = RendersModule.playerShaderActive();
                        if (var1_1 != 0) break block39;
                        if (!v12) break block40;
                        break block46;
                        catch (Throwable v13) {
                            throw v13;
                        }
                    }
                    PlayerMask.pass(var2_2, var3_3, var4_4, var13_13, var8_9, var9_10, var10_11, var14_14, var12_12, false);
                }
                v12 = RendersModule.crystalShaderActive();
            }
            if (v12) {
                PlayerMask.pass(var2_2, var3_3, var4_4, var13_13, var8_9, var9_10, var10_11, var14_14, var12_12, true);
            }
        }
        catch (Throwable var7_8) {
            Aurora.LOG.warn(PlayerMask.a[0], var7_8);
        }
    }

    
    private static void pass(MinecraftClient minecraftClient, Framebuffer framebuffer, MatrixStack matrixStack, CameraRenderState cameraRenderState, Vec3d vec3d, float f, double d, Vector3fc vector3fc, EntityRenderManager entityRenderManager, boolean bl) {
        block72: {
            Entity entity2222;
            OrderedRenderCommandQueueImpl orderedRenderCommandQueueImpl;
            int n;
            block63: {
                boolean bl2;
                block54: {
                    block73: {
                        RenderSystem.getDevice().createCommandEncoder().createRenderPass(PlayerMask::lambda$pass$0, maskFbo.method_71639(), OptionalInt.of(0), maskFbo.method_71640(), OptionalDouble.of(1.0)).close();
                        n = EffectEngine.a;
                        orderedRenderCommandQueueImpl = new OrderedRenderCommandQueueImpl();
                        bl2 = false;
                        if (!bl) break block73;
                        for (Entity entity2222 : minecraftClient.field_1687.method_18112()) {
                            block62: {
                                Entity entity3;
                                block60: {
                                    block61: {
                                        block58: {
                                            block59: {
                                                block57: {
                                                    block55: {
                                                        block56: {
                                                            block74: {
                                                                if (n != 0) break block54;
                                                                entity3 = entity2222;
                                                                if (n != 0) break block55;
                                                                break block74;
                                                                catch (MatchException matchException) {
                                                                    throw matchException;
                                                                }
                                                            }
                                                            if (!(entity3 instanceof EndCrystalEntity)) {
                                                                continue;
                                                            }
                                                            break block56;
                                                            catch (MatchException matchException) {
                                                                throw matchException;
                                                            }
                                                        }
                                                        entity3 = entity2222;
                                                    }
                                                    if (n != 0) break block57;
                                                    if (entity3.method_5767()) continue;
                                                    entity3 = entity2222;
                                                }
                                                if (n != 0) break block58;
                                                if (entity3.method_31481()) {
                                                    continue;
                                                }
                                                break block59;
                                                catch (MatchException matchException) {
                                                    throw matchException;
                                                }
                                            }
                                            entity3 = entity2222;
                                        }
                                        if (n != 0) break block60;
                                        if (entity3.method_5858((Entity)minecraftClient.field_1724) > d * d) {
                                            continue;
                                        }
                                        break block61;
                                        catch (MatchException matchException) {
                                            throw matchException;
                                        }
                                    }
                                    entity3 = entity2222;
                                }
                                boolean bl3 = PlayerMask.behindCamera(entity3, vec3d, vector3fc);
                                if (n != 0) break block62;
                                if (bl3) {
                                    continue;
                                }
                                bl3 = bl2 = bl2 | PlayerMask.draw(entityRenderManager, entity2222, cameraRenderState, vec3d, f, matrixStack, orderedRenderCommandQueueImpl);
                            }
                            if (n == 0) continue;
                        }
                        if (n == 0) break block54;
                    }
                    for (Entity entity2222 : minecraftClient.field_1687.method_18456()) {
                        block71: {
                            Entity entity4;
                            block69: {
                                block70: {
                                    block67: {
                                        block68: {
                                            block66: {
                                                block65: {
                                                    block64: {
                                                        block75: {
                                                            if (n != 0) break block63;
                                                            entity4 = entity2222;
                                                            if (n != 0) break block64;
                                                            break block75;
                                                            catch (MatchException matchException) {
                                                                throw matchException;
                                                            }
                                                        }
                                                        if (entity4 == minecraftClient.field_1724) continue;
                                                        entity4 = entity2222;
                                                    }
                                                    if (n != 0) break block65;
                                                    if (entity4.method_7325()) continue;
                                                    entity4 = entity2222;
                                                }
                                                if (n != 0) break block66;
                                                if (entity4.method_5767()) continue;
                                                entity4 = entity2222;
                                            }
                                            if (n != 0) break block67;
                                            if (entity4.field_6213 > 0) {
                                                continue;
                                            }
                                            break block68;
                                            catch (MatchException matchException) {
                                                throw matchException;
                                            }
                                        }
                                        entity4 = entity2222;
                                    }
                                    if (n != 0) break block69;
                                    if (entity4.method_5858((Entity)minecraftClient.field_1724) > d * d) {
                                        continue;
                                    }
                                    break block70;
                                    catch (MatchException matchException) {
                                        throw matchException;
                                    }
                                }
                                entity4 = entity2222;
                            }
                            boolean bl4 = PlayerMask.behindCamera(entity4, vec3d, vector3fc);
                            if (n != 0) break block71;
                            if (bl4) {
                                continue;
                            }
                            bl4 = bl2 = bl2 | PlayerMask.draw(entityRenderManager, entity2222, cameraRenderState, vec3d, f, matrixStack, orderedRenderCommandQueueImpl);
                        }
                        if (n == 0) continue;
                    }
                }
                if (!bl2) {
                    return;
                }
            }
            Iterator<Object> iterator = RenderSystem.outputColorTextureOverride;
            entity2222 = RenderSystem.outputDepthTextureOverride;
            RenderSystem.outputColorTextureOverride = maskFbo.method_71639();
            RenderSystem.outputDepthTextureOverride = maskFbo.method_71640();
            try {
                VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.method_22991((BufferAllocator)OPAQUE);
                VertexConsumerProvider.Immediate immediate2 = VertexConsumerProvider.method_22991((BufferAllocator)BLENDED);
                OutlineVertexConsumerProvider outlineVertexConsumerProvider = minecraftClient.method_22940().method_23003();
                for (BatchingRenderCommandQueue batchingRenderCommandQueue : orderedRenderCommandQueueImpl.method_73532().values()) {
                    if (n == 0) {
                        COMMANDS.method_73001(batchingRenderCommandQueue, immediate, outlineVertexConsumerProvider, immediate2);
                        if (n == 0) continue;
                        break;
                    }
                    break block72;
                }
                immediate.method_22993();
                immediate2.method_22993();
                RenderSystem.outputColorTextureOverride = iterator;
                RenderSystem.outputDepthTextureOverride = entity2222;
            }
            catch (Throwable throwable) {
                RenderSystem.outputColorTextureOverride = iterator;
                RenderSystem.outputDepthTextureOverride = entity2222;
                throw throwable;
            }
        }
        RendersModule.renderPlayerShaders(bl, maskFbo.method_71639(), maskFbo.method_71640(), framebuffer.method_71640());
    }

    private static void ensureFbo(int n, int n2) {
        SimpleFramebuffer simpleFramebuffer;
        block18: {
            block19: {
                int n3;
                block15: {
                    block16: {
                        int n4;
                        block17: {
                            n3 = EffectEngine.a;
                            simpleFramebuffer = maskFbo;
                            if (n3 != 0) break block15;
                            if (simpleFramebuffer == null) break block16;
                            n4 = fboW;
                            if (n3 != 0) break block17;
                            if (n4 != n) break block16;
                            n4 = fboH;
                        }
                        if (n4 == n2) {
                            return;
                        }
                    }
                    simpleFramebuffer = maskFbo;
                }
                if (n3 != 0) break block18;
                if (simpleFramebuffer == null) break block19;
                maskFbo.method_1238();
            }
            simpleFramebuffer = new SimpleFramebuffer(a[1], n, n2, true);
        }
        maskFbo = simpleFramebuffer;
        fboW = n;
        fboH = n2;
    }

    private static String lambda$pass$0() {
        return a[2];
    }

    
    static {
        block16: {
            block15: {
                block14: {
                    var13 = new String[3];
                    var11_1 = 0;
                    var10_2 = "Nu ttjdsx2f1~%wu$i\u0012\u007fl3bcydnu ttjdsx2f\u0018\u007fl3bcydnu ttjdsx2f1{({x3";
                    var12_3 = "Nu ttjdsx2f1~%wu$i\u0012\u007fl3bcydnu ttjdsx2f\u0018\u007fl3bcydnu ttjdsx2f1{({x3".length();
                    var9_4 = 18;
                    var8_5 = -1;
lbl7:

                    while (true) {
                        continue;
                        break;
                    }
lbl9:

                    while (true) {
                        var13[var11_1++] = new String(v0).intern();
                        if ((var8_5 += var9_4) < var12_3) {
                            var9_4 = var10_2.charAt(var8_5);
                            continue;
                        }
                        break block14;
                        break;
                    }
                    v1 = ++var8_5;
                    v2 = var10_2.substring(v1, v1 + var9_4).toCharArray();
                    v0 = v2;
                    v3 = v2.length;
                    var14_6 = 0;
                    do {
                        v0 = v0;
                        v4 = var14_6;
                        v5 = v0[v4];
                        switch (var14_6 % 7) {
                            case 0: {
                                v6 = 30;
                                break;
                            }
                            case 1: {
                                v6 = 25;
                                break;
                            }
                            case 2: {
                                v6 = 65;
                                break;
                            }
                            case 3: {
                                v6 = 13;
                                break;
                            }
                            case 4: {
                                v6 = 17;
                                break;
                            }
                            case 5: {
                                v6 = 24;
                                break;
                            }
                            default: {
                                v6 = 68;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_6;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_6);
                    while (true)
                }
                PlayerMask.a = var13;
                var1_7 = 2132472714870297650L;
                var0_8 = new long[2];
                var4_9 = 0;
                var5_10 = "\n\u000b\u00a4\u00e7\u00c7\u001e\u00942\u00d8\u009b\u00b8\u00d2\u00c7\u001e\u00942";
                var6_11 = "\n\u000b\u00a4\u00e7\u00c7\u001e\u00942\u00d8\u009b\u00b8\u00d2\u00c7\u001e\u00942".length();
                var3_12 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl63:

                while (true) {
                    var0_8[v7] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var1_7;
                    if (var3_12 < var6_11) continue;
                    break block16;
                    break;
                }
            }
            var7_13 = var5_10.substring(var3_12, var3_12 += 8).getBytes("ISO-8859-1");
            v7 = var4_9++;
            while (true)
        }
        PlayerMask.OPAQUE = new BufferAllocator((int)var0_8[0]);
        PlayerMask.BLENDED = new BufferAllocator((int)var0_8[1]);
        PlayerMask.COMMANDS = new ModelCommandRenderer();
    }
}
