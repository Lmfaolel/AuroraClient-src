package dev.quelchi.aurora.render;

import dev.quelchi.aurora.ambience.AmbienceDimensions;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.PaintedSky;
import dev.quelchi.aurora.render.SkyGlow;
import dev.quelchi.aurora.render.StarField;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.world.ClientWorld;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public final class SkyExtras {
    private static final BufferAllocator BUFFER;
    public static volatile float sunAngle;
    public static volatile float moonAngle;
    private static final int GLOW_STEPS;
    private static final int HALO_STEPS;

    private SkyExtras() {
    }

    public static void onWorldRender(WorldRenderContext worldRenderContext) {
        AmbienceModule ambienceModule;
        Matrix4f matrix4f;
        VertexConsumer vertexConsumer;
        VertexConsumerProvider.Immediate immediate;
        float f;
        float f2;
        ClientWorld clientWorld;
        AmbienceModule ambienceModule2;
        block42: {
            block43: {
                float f3;
                int n;
                block40: {
                    MinecraftClient minecraftClient;
                    block41: {
                        block39: {
                            block38: {
                                block44: {
                                    block34: {
                                        boolean bl;
                                        block36: {
                                            block37: {
                                                AmbienceModule ambienceModule3;
                                                block35: {
                                                    block33: {
                                                        ambienceModule2 = AmbienceModule.get();
                                                        n = EffectEngine.a;
                                                        ambienceModule3 = ambienceModule2;
                                                        if (n != 0) break block33;
                                                        if (ambienceModule3 == null) break block34;
                                                        ambienceModule3 = ambienceModule2;
                                                    }
                                                    if (n != 0) break block35;
                                                    if (!ambienceModule3.isEnabled()) break block34;
                                                    ambienceModule3 = ambienceModule2;
                                                }
                                                bl = ambienceModule3.sunsetGlow();
                                                if (n != 0) break block36;
                                                if (bl) break block37;
                                                bl = ambienceModule2.moonHalo();
                                                if (n != 0) break block36;
                                                if (!bl) break block34;
                                            }
                                            bl = PaintedSky.active();
                                        }
                                        if (!bl) break block44;
                                    }
                                    return;
                                }
                                minecraftClient = MinecraftClient.method_1551();
                                clientWorld = minecraftClient.field_1687;
                                if (clientWorld == null || minecraftClient.field_1773 == null) break block38;
                                if (AmbienceDimensions.isOverworldLike(clientWorld)) break block39;
                            }
                            return;
                        }
                        f2 = 1.0f - clientWorld.method_8430(minecraftClient.method_61966().method_60637(false));
                        f3 = f2;
                        if (n != 0) break block40;
                        if (!(f3 <= 0.01f)) break block41;
                        return;
                    }
                    f3 = minecraftClient.field_1773.method_32796();
                }
                float f4 = f3;
                f = Math.min(75.0f, Math.max(32.0f, f4 * 0.5f));
                immediate = VertexConsumerProvider.method_22991((BufferAllocator)BUFFER);
                vertexConsumer = immediate.method_73477(SkyGlow.layer());
                matrix4f = worldRenderContext.matrices().method_23760().method_23761();
                ambienceModule = ambienceModule2;
                if (n != 0) break block42;
                if (!ambienceModule.sunsetGlow()) break block43;
                SkyExtras.glow(vertexConsumer, matrix4f, ambienceModule2, f, f2);
            }
            ambienceModule = ambienceModule2;
        }
        if (ambienceModule.moonHalo()) {
            SkyExtras.halo(vertexConsumer, matrix4f, ambienceModule2, clientWorld, f, f2);
        }
        immediate.method_22993();
    }

    private static void glow(VertexConsumer vertexConsumer, Matrix4f matrix4f, AmbienceModule ambienceModule, float f, float f2) {
        float f3;
        float f4;
        int n;
        block13: {
            block14: {
                block11: {
                    float f5;
                    block12: {
                        f5 = -((float)Math.sin(sunAngle));
                        float f6 = (float)Math.cos(sunAngle);
                        n = EffectEngine.a;
                        float f7 = ambienceModule.sunsetDuskOnly() ? Math.max(0.0f, 1.0f - Math.abs(f6) / 0.35f) : 1.0f;
                        float f8 = f7;
                        f4 = 255.0f * ambienceModule.sunsetStrength() * f8 * f2;
                        f3 = f4;
                        if (n != 0) break block11;
                        if (!(f3 < 2.0f)) break block12;
                        return;
                    }
                    f3 = f5;
                }
                if (n != 0) break block13;
                if (!(f3 >= 0.0f)) break block14;
                f3 = 0.0f;
                break block13;
            }
            f3 = (float)Math.PI;
        }
        float f9 = f3;
        int n2 = ambienceModule.sunsetRgb();
        int n3 = n2 >> 16 & 0xFF;
        int n4 = n2 >> 8 & 0xFF;
        int n5 = n2 & 0xFF;
        float f10 = (float)Math.toRadians(-2.0);
        float f11 = (float)Math.toRadians(ambienceModule.sunsetHeight());
        float f12 = f10 + (f11 - f10) * 0.35f;
        for (int i = 0; i < 64; ++i) {
            float f13 = (float)(Math.PI * 2 * (double)i / 64.0);
            float f14 = (float)(Math.PI * 2 * (double)(i + 1) / 64.0);
            int n6 = Math.round(f4 * SkyExtras.toward(f13, f9));
            int n7 = Math.round(f4 * SkyExtras.toward(f14, f9));
            int n8 = Math.round((float)n6 * 0.35f);
            int n9 = Math.round((float)n7 * 0.35f);
            SkyExtras.vert(vertexConsumer, matrix4f, f, f13, f10, n3, n4, n5, n6);
            SkyExtras.vert(vertexConsumer, matrix4f, f, f14, f10, n3, n4, n5, n7);
            SkyExtras.vert(vertexConsumer, matrix4f, f, f14, f12, n3, n4, n5, n9);
            SkyExtras.vert(vertexConsumer, matrix4f, f, f13, f12, n3, n4, n5, n8);
            SkyExtras.vert(vertexConsumer, matrix4f, f, f13, f12, n3, n4, n5, n8);
            SkyExtras.vert(vertexConsumer, matrix4f, f, f14, f12, n3, n4, n5, n9);
            SkyExtras.vert(vertexConsumer, matrix4f, f, f14, f11, n3, n4, n5, 0);
            SkyExtras.vert(vertexConsumer, matrix4f, f, f13, f11, n3, n4, n5, 0);
            if (n == 0) continue;
        }
    }

    private static float toward(float f, float f2) {
        float f3 = (float)Math.max(0.0, Math.cos(f - f2));
        return 0.3f + 0.7f * f3 * f3;
    }

    private static void vert(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, int n, int n2, int n3, int n4) {
        float f4 = (float)Math.cos(f3);
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f4 * (float)Math.cos(f2) * f, (float)Math.sin(f3) * f, f4 * (float)Math.sin(f2) * f).method_1336(n, n2, n3, n4);
    }

    private static void halo(VertexConsumer vertexConsumer, Matrix4f matrix4f, AmbienceModule ambienceModule, ClientWorld clientWorld, float f, float f2) {
        float f3;
        float f4;
        float f5;
        block4: {
            block5: {
                f5 = -((float)Math.sin(moonAngle));
                f4 = (float)Math.cos(moonAngle);
                int n = EffectEngine.a;
                f3 = f4;
                if (n != 0) break block4;
                if (!(f3 < -0.1f)) break block5;
                return;
            }
            f3 = 114.75f * (0.3f + 0.7f * StarField.nightFactor(clientWorld)) * f2;
        }
        float f6 = f3;
        int n = ambienceModule.haloRgb();
        float f7 = 0.1f * ambienceModule.moonScale() * f;
        float f8 = 0.42f * ambienceModule.moonScale() * f;
        int n2 = Math.round(f6);
        SkyExtras.ring(vertexConsumer, matrix4f, f5 * f, f4 * f, 0.0f, 0.0f, 0.0f, 1.0f, f4, -f5, 0.0f, 0.0f, f7, n, n2, n2);
        SkyExtras.ring(vertexConsumer, matrix4f, f5 * f, f4 * f, 0.0f, 0.0f, 0.0f, 1.0f, f4, -f5, 0.0f, f7, f8, n, n2, 0);
        float f9 = 0.4f * f;
        SkyExtras.ring(vertexConsumer, matrix4f, f5 * f, f4 * f, 0.0f, 0.0f, 0.0f, 1.0f, f4, -f5, 0.0f, f9 * 0.93f, f9, n, 0, n2 / 4);
        SkyExtras.ring(vertexConsumer, matrix4f, f5 * f, f4 * f, 0.0f, 0.0f, 0.0f, 1.0f, f4, -f5, 0.0f, f9, f9 * 1.07f, n, n2 / 4, 0);
    }

    private static void ring(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, int n, int n2, int n3) {
        int n4 = n >> 16 & 0xFF;
        int n5 = n >> 8 & 0xFF;
        int n6 = EffectEngine.a;
        int n7 = n & 0xFF;
        for (int i = 0; i < 32; ++i) {
            double d = Math.PI * 2 * (double)i / 32.0;
            double d2 = Math.PI * 2 * (double)(i + 1) / 32.0;
            float f12 = (float)Math.cos(d);
            float f13 = (float)Math.sin(d);
            float f14 = (float)Math.cos(d2);
            float f15 = (float)Math.sin(d2);
            vertexConsumer.method_22918((Matrix4fc)matrix4f, f + (f4 * f12 + f7 * f13) * f10, f2 + (f5 * f12 + f8 * f13) * f10, f3 + (f6 * f12 + f9 * f13) * f10).method_1336(n4, n5, n7, n2);
            vertexConsumer.method_22918((Matrix4fc)matrix4f, f + (f4 * f14 + f7 * f15) * f10, f2 + (f5 * f14 + f8 * f15) * f10, f3 + (f6 * f14 + f9 * f15) * f10).method_1336(n4, n5, n7, n2);
            vertexConsumer.method_22918((Matrix4fc)matrix4f, f + (f4 * f14 + f7 * f15) * f11, f2 + (f5 * f14 + f8 * f15) * f11, f3 + (f6 * f14 + f9 * f15) * f11).method_1336(n4, n5, n7, n3);
            vertexConsumer.method_22918((Matrix4fc)matrix4f, f + (f4 * f12 + f7 * f13) * f11, f2 + (f5 * f12 + f8 * f13) * f11, f3 + (f6 * f12 + f9 * f13) * f11).method_1336(n4, n5, n7, n3);
            if (n6 == 0) continue;
        }
    }

    
    static {
        long l = 1452422190235330566L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "\u00e1\u00fdt\u008fh\u00df(F;\u00e9\u00f0\u00a6h\u00df(&\u0001\u001c\u00c4eh\u00de(\u0006";
        int n2 = "\u00e1\u00fdt\u008fh\u00df(F;\u00e9\u00f0\u00a6h\u00df(&\u0001\u001c\u00c4eh\u00de(\u0006".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        GLOW_STEPS = (int)lArray[0];
        HALO_STEPS = (int)lArray[1];
        BUFFER = new BufferAllocator((int)lArray[2]);
    }
}
