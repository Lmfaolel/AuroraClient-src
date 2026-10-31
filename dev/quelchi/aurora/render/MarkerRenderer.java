package dev.quelchi.aurora.render;

import dev.quelchi.aurora.module.modules.ShieldTweaksModule;
import dev.quelchi.aurora.render.EffectEngine;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public final class MarkerRenderer {
    private static final BufferAllocator BUFFER;
    private static final int SEGMENTS;

    private MarkerRenderer() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onWorldRender(WorldRenderContext worldRenderContext) {
        MinecraftClient minecraftClient;
        ClientWorld clientWorld;
        MinecraftClient minecraftClient2;
        int n;
        block31: {
            block32: {
                block30: {
                    n = EffectEngine.a;
                    if (!ShieldTweaksModule.markerEnabled()) {
                        return;
                    }
                    minecraftClient2 = MinecraftClient.method_1551();
                    clientWorld = minecraftClient2.field_1687;
                    if (clientWorld == null) return;
                    minecraftClient = minecraftClient2;
                    if (n != 0) break block30;
                    if (minecraftClient.field_1724 == null) return;
                    minecraftClient = minecraftClient2;
                }
                if (n != 0) break block31;
                if (minecraftClient.field_1773 != null) break block32;
                return;
            }
            minecraftClient = minecraftClient2;
        }
        Camera camera = minecraftClient.field_1773.method_19418();
        Vec3d vec3d = camera.method_71156();
        float f = minecraftClient2.method_61966().method_60637(true);
        Vec3d vec3d2 = minecraftClient2.field_1724.method_73189();
        VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.method_22991((BufferAllocator)BUFFER);
        boolean bl = false;
        for (AbstractClientPlayerEntity abstractClientPlayerEntity : clientWorld.method_18456()) {
            int n2;
            block37: {
                AbstractClientPlayerEntity abstractClientPlayerEntity2;
                block35: {
                    block36: {
                        block33: {
                            if (n != 0) return;
                            abstractClientPlayerEntity2 = abstractClientPlayerEntity;
                            if (n != 0) break block33;
                            if (!abstractClientPlayerEntity2.method_6039()) {
                                continue;
                            }
                            abstractClientPlayerEntity2 = abstractClientPlayerEntity;
                        }
                        if (n != 0) break block35;
                        if (abstractClientPlayerEntity2 != minecraftClient2.field_1724) break block36;
                        n2 = minecraftClient2.field_1690.method_31044().method_31034() ? 1 : 0;
                        if (n != 0) break block37;
                        if (n2 != 0) {
                            continue;
                        }
                    }
                    abstractClientPlayerEntity2 = abstractClientPlayerEntity;
                }
                n2 = ShieldTweaksModule.markerArgb((LivingEntity)abstractClientPlayerEntity2, vec3d2);
            }
            int n3 = n2;
            int n4 = n3 >>> 24 & 0xFF;
            int n5 = n3 >>> 16 & 0xFF;
            int n6 = n3 >>> 8 & 0xFF;
            int n7 = n3 & 0xFF;
            Vec3d vec3d3 = abstractClientPlayerEntity.method_30950(f);
            float f2 = abstractClientPlayerEntity.method_17681() * 0.75f;
            float f3 = abstractClientPlayerEntity.method_17682();
            float f4 = (float)(vec3d3.field_1351 - vec3d.field_1351);
            float f5 = (float)(vec3d3.field_1352 - vec3d.field_1352);
            float f6 = (float)(vec3d3.field_1350 - vec3d.field_1350);
            float f7 = (float)Math.toRadians(abstractClientPlayerEntity.method_5705(f));
            float f8 = -((float)Math.sin(f7));
            float f9 = (float)Math.cos(f7);
            VertexConsumer vertexConsumer = immediate.method_73477(RenderLayers.method_76023());
            Matrix4f matrix4f = worldRenderContext.matrices().method_23760().method_23761();
            MarkerRenderer.halfCylinder(vertexConsumer, matrix4f, f5, f4, f6, f2, f3, f8, f9, n5, n6, n7, n4);
            bl = true;
            if (n == 0) continue;
        }
        if (!bl) return;
        immediate.method_22993();
        return;
    }

    private static void halfCylinder(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, float f7, int n, int n2, int n3, int n4) {
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        block2: {
            f12 = f2 + f5;
            int n5 = EffectEngine.a;
            f11 = -f7;
            f10 = f6;
            for (int i = 0; i < 16; ++i) {
                double d = Math.PI * (double)i / 16.0 - 1.5707963267948966;
                double d2 = Math.PI * (double)(i + 1) / 16.0 - 1.5707963267948966;
                float f13 = f + (float)(((double)f6 * Math.cos(d) + (double)f11 * Math.sin(d)) * (double)f4);
                float f14 = f3 + (float)(((double)f7 * Math.cos(d) + (double)f10 * Math.sin(d)) * (double)f4);
                float f15 = f + (float)(((double)f6 * Math.cos(d2) + (double)f11 * Math.sin(d2)) * (double)f4);
                f9 = f3;
                f8 = f7;
                if (n5 == 0) {
                    float f16 = f9 + (float)(((double)f8 * Math.cos(d2) + (double)f10 * Math.sin(d2)) * (double)f4);
                    MarkerRenderer.face(vertexConsumer, matrix4f, f13, f2, f14, f15, f2, f16, f15, f12, f16, f13, f12, f14, n, n2, n3, n4);
                    if (n5 == 0) continue;
                }
                break block2;
            }
            f9 = f;
            f8 = f11 * f4;
        }
        float f17 = f9 - f8;
        float f18 = f3 - f10 * f4;
        float f19 = f + f11 * f4;
        float f20 = f3 + f10 * f4;
        MarkerRenderer.face(vertexConsumer, matrix4f, f17, f2, f18, f19, f2, f20, f19, f12, f20, f17, f12, f18, n, n2, n3, n4);
    }

    private static void face(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, int n, int n2, int n3, int n4) {
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f, f2, f3).method_1336(n, n2, n3, n4);
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f4, f5, f6).method_1336(n, n2, n3, n4);
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f7, f8, f9).method_1336(n, n2, n3, n4);
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f10, f11, f12).method_1336(n, n2, n3, n4);
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f10, f11, f12).method_1336(n, n2, n3, n4);
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f7, f8, f9).method_1336(n, n2, n3, n4);
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f4, f5, f6).method_1336(n, n2, n3, n4);
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f, f2, f3).method_1336(n, n2, n3, n4);
    }

    
    static {
        long l = 7135926790702385500L;
        long l2 = 0x8D85C8BBBF02494CL ^ l;
        SEGMENTS = (int)l2;
        BUFFER = new BufferAllocator(1024);
    }
}
