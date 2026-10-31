package dev.quelchi.aurora.render;

import dev.quelchi.aurora.ambience.AmbienceDimensions;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.PaintedSky;
import dev.quelchi.aurora.render.SkyGlow;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public final class AuroraSky {
    private static final BufferAllocator BUFFER;
    private static final int COLUMNS;
    private static final int SHEETS;
    private static final float LOWEST;
    private static final float HIGHEST;
    private static final float HIGHEST_BASE;
    private static final float WAIST = 0.32f;
    private static final long[] a;
    private static final Long[] b;

    private AuroraSky() {
    }

    public static void onWorldRender(WorldRenderContext worldRenderContext) {
        block41: {
            float f;
            float f2;
            MinecraftClient minecraftClient;
            int n;
            AmbienceModule ambienceModule;
            block39: {
                ClientWorld clientWorld;
                block40: {
                    boolean bl;
                    block37: {
                        block38: {
                            block36: {
                                block43: {
                                    block33: {
                                        boolean bl2;
                                        block35: {
                                            AmbienceModule ambienceModule2;
                                            block34: {
                                                block32: {
                                                    ambienceModule = AmbienceModule.get();
                                                    n = EffectEngine.a;
                                                    ambienceModule2 = ambienceModule;
                                                    if (n != 0) break block32;
                                                    if (ambienceModule2 == null) break block33;
                                                    ambienceModule2 = ambienceModule;
                                                }
                                                if (n != 0) break block34;
                                                if (!ambienceModule2.isEnabled()) break block33;
                                                ambienceModule2 = ambienceModule;
                                            }
                                            bl2 = ambienceModule2.aurora();
                                            if (n != 0) break block35;
                                            if (!bl2) break block33;
                                            bl2 = PaintedSky.active();
                                        }
                                        if (!bl2) break block43;
                                    }
                                    return;
                                }
                                minecraftClient = MinecraftClient.method_1551();
                                clientWorld = minecraftClient.field_1687;
                                if (clientWorld != null && minecraftClient.field_1773 != null) break block36;
                                return;
                            }
                            bl = AmbienceDimensions.isOverworldLike(clientWorld);
                            if (n != 0) break block37;
                            if (bl) break block38;
                            return;
                        }
                        bl = ambienceModule.auroraNightOnly();
                    }
                    float f3 = bl ? AuroraSky.nightFactor(clientWorld) : 1.0f;
                    f2 = f3;
                    f = f2;
                    if (n != 0) break block39;
                    if (!(f <= 0.01f)) break block40;
                    return;
                }
                f = f2 *= 1.0f - clientWorld.method_8430(minecraftClient.method_61966().method_60637(false));
            }
            if (f <= 0.01f) {
                return;
            }
            Camera camera = minecraftClient.field_1773.method_19418();
            Vec3d vec3d = camera.method_71156();
            float f4 = minecraftClient.field_1773.method_32796();
            float f5 = Math.min(80.0f, Math.max(32.0f, f4 * 0.5f));
            float f6 = (float)ambienceModule.auroraSpeed() / 100.0f;
            float f7 = (float)(System.currentTimeMillis() % AuroraSky.a(8906, 276035840363584093L)) / 1000.0f * 0.05f * f6;
            int n2 = ambienceModule.auroraBands();
            float f8 = (float)Math.toRadians(ambienceModule.auroraAltitude());
            float f9 = (float)Math.toRadians(ambienceModule.auroraHeight());
            float f10 = (float)Math.toRadians(ambienceModule.auroraSpread());
            float f11 = (float)ambienceModule.auroraOpacity() / 100.0f * f2;
            int n3 = ambienceModule.auroraRgb();
            int n4 = ambienceModule.auroraTipRgb();
            VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.method_22991((BufferAllocator)BUFFER);
            VertexConsumer vertexConsumer = immediate.method_73477(SkyGlow.layer());
            Matrix4f matrix4f = worldRenderContext.matrices().method_23760().method_23761();
            float f12 = f11 * 0.5f / 3.0f * 1.6f;
            int n5 = 0;
            while (n5 < n2) {
                block42: {
                    if (n != 0) break block41;
                    for (int i = 0; i < 3; ++i) {
                        AuroraSky.ribbonSpherical(vertexConsumer, matrix4f, n5, n2, i, f5, f8, f9, f10, f7, f12, n3, n4);
                        if (n == 0) {
                            if (n == 0) continue;
                            break;
                        }
                        break block42;
                    }
                    ++n5;
                }
                if (n == 0) continue;
            }
            immediate.method_22993();
        }
    }

    
    private static void ribbonSpherical(VertexConsumer vertexConsumer, Matrix4f matrix4f, int n, int n2, int n3, float f, float f2, float f3, float f4, float f5, float f6, int n4, int n5) {
        float f7 = (float)(Math.PI * 2 * (double)n / (double)n2) + f5 * 0.03f + (float)n3 * 0.02f;
        float f8 = f5 + (float)n * 2.1f + (float)n3 * 0.9f;
        float f9 = f3 * (0.8f + 0.16f * (float)(n % 3)) * (1.0f + (float)n3 * 0.07f);
        float f10 = 0.0f;
        int n6 = EffectEngine.a;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = 0.0f;
        float f16 = 0.0f;
        float f17 = 0.0f;
        int n7 = 0;
        while (n7 <= 64) {
            block9: {
                float f18;
                block13: {
                    float f19;
                    float f20;
                    float f21;
                    float f22;
                    float f23;
                    float f24;
                    float f25;
                    float f26;
                    block10: {
                        block12: {
                            float f27;
                            block11: {
                                float f28 = (float)n7 / 64.0f;
                                float f29 = f7 + (f28 - 0.5f) * f4;
                                float f30 = (float)Math.sin((double)f28 * 7.0 + (double)f8) * 0.06f + (float)Math.sin((double)f28 * 17.0 + (double)f8 * 1.7) * 0.025f;
                                float f31 = f2 + (float)Math.sin((double)f28 * 5.0 + (double)f8 * 0.8) * 0.04f;
                                f31 = Math.max(LOWEST, Math.min(f31, HIGHEST_BASE));
                                float f32 = Math.max(LOWEST, HIGHEST - f31);
                                float f33 = Math.min(f9, f32);
                                float f34 = f31 + f33 * (0.55f + 0.45f * (0.5f + 0.5f * (float)Math.sin((double)f28 * 11.0 + (double)f8 * 1.3)));
                                f26 = AuroraSky.dirX(f29 + f30, f31) * f;
                                f25 = AuroraSky.dirY(f31) * f;
                                f24 = AuroraSky.dirZ(f29 + f30, f31) * f;
                                f23 = AuroraSky.dirX(f29 + f30 * 1.6f, f34) * f;
                                f22 = AuroraSky.dirY(f34) * f;
                                f21 = AuroraSky.dirZ(f29 + f30 * 1.6f, f34) * f;
                                float f35 = 0.5f + 0.5f * (float)Math.sin((double)f28 * 90.0 + (double)f8 * 2.1);
                                float f36 = 0.45f + 0.55f * f35 * f35;
                                float f37 = 0.72f + 0.28f * (float)Math.sin((double)f28 * 4.0 - (double)f8 * 2.5);
                                f20 = (float)Math.sin(Math.PI * (double)f28) * f6 * f36 * f37;
                                f19 = 0.5f + 0.5f * (float)Math.sin((double)f28 * 6.0 + (double)f8 * 0.6);
                                if (n6 != 0) break block9;
                                if (n7 <= 0) break block10;
                                f27 = 255.0f * f16;
                                if (n6 != 0) break block11;
                                if (Math.round(f27) > 0) break block12;
                                f18 = 255.0f;
                                if (n6 != 0) break block13;
                                f27 = f18 * f20;
                            }
                            if (Math.round(f27) <= 0) break block10;
                        }
                        AuroraSky.quad(vertexConsumer, matrix4f, f10, f11, f12, f13, f14, f15, f16, f17, f26, f25, f24, f23, f22, f21, f20, f19, n4, n5);
                    }
                    f10 = f26;
                    f11 = f25;
                    f12 = f24;
                    f13 = f23;
                    f14 = f22;
                    f15 = f21;
                    f16 = f20;
                    f18 = f19;
                }
                f17 = f18;
                ++n7;
            }
            if (n6 == 0) continue;
        }
    }

    private static float dirX(float f, float f2) {
        return (float)(Math.cos(f2) * Math.cos(f));
    }

    private static float dirY(float f) {
        return (float)Math.sin(f);
    }

    private static float dirZ(float f, float f2) {
        return (float)(Math.cos(f2) * Math.sin(f));
    }

    private static void quad(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, int n, int n2) {
        int n3 = AuroraSky.lerpRgb(n, n2, f8 * 0.45f);
        int n4 = AuroraSky.lerpRgb(n, n2, f16 * 0.45f);
        int n5 = Math.round(255.0f * f7);
        int n6 = Math.round(255.0f * f15);
        int n7 = Math.round((float)n5 * 0.12f);
        int n8 = Math.round((float)n6 * 0.12f);
        int n9 = Math.round((float)n5 * 0.06f);
        int n10 = Math.round((float)n6 * 0.06f);
        float f17 = f + (f4 - f) * 0.32f;
        float f18 = f2 + (f5 - f2) * 0.32f;
        float f19 = f3 + (f6 - f3) * 0.32f;
        float f20 = f9 + (f12 - f9) * 0.32f;
        float f21 = f10 + (f13 - f10) * 0.32f;
        float f22 = f11 + (f14 - f11) * 0.32f;
        AuroraSky.band(vertexConsumer, matrix4f, f, f2, f3, f9, f10, f11, f20, f21, f22, f17, f18, f19, n3, n7, n4, n8, n4, n6, n3, n5);
        AuroraSky.band(vertexConsumer, matrix4f, f17, f18, f19, f20, f21, f22, f12, f13, f14, f4, f5, f6, n3, n5, n4, n6, n2, n10, n2, n9);
    }

    private static void band(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        AuroraSky.vertex(vertexConsumer, matrix4f, f, f2, f3, n, n2);
        AuroraSky.vertex(vertexConsumer, matrix4f, f4, f5, f6, n3, n4);
        AuroraSky.vertex(vertexConsumer, matrix4f, f7, f8, f9, n5, n6);
        AuroraSky.vertex(vertexConsumer, matrix4f, f10, f11, f12, n7, n8);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, int n, int n2) {
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f, f2, f3).method_1336(n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF, n2);
    }

    private static int lerpRgb(int n, int n2, float f) {
        int n3 = n >> 16 & 0xFF;
        int n4 = n >> 8 & 0xFF;
        int n5 = n & 0xFF;
        int n6 = n2 >> 16 & 0xFF;
        int n7 = n2 >> 8 & 0xFF;
        int n8 = n2 & 0xFF;
        int n9 = Math.round((float)n3 + (float)(n6 - n3) * f);
        int n10 = Math.round((float)n4 + (float)(n7 - n4) * f);
        int n11 = Math.round((float)n5 + (float)(n8 - n5) * f);
        return n9 << 16 | n10 << 8 | n11;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static float nightFactor(ClientWorld clientWorld) {
        long l;
        long l2;
        block17: {
            block18: {
                int n;
                block15: {
                    block16: {
                        block14: {
                            l2 = Math.floorMod(clientWorld.method_8532(), AuroraSky.a(31663, 4784883571078529850L));
                            n = EffectEngine.a;
                            l = l2;
                            if (n != 0) break block14;
                            if (l < AuroraSky.a(9345, 31779382543440919L)) return 0.0f;
                            l = l2;
                        }
                        if (n != 0) break block15;
                        if (l <= AuroraSky.a(22944, 273931434500268337L)) break block16;
                        return 0.0f;
                    }
                    l = l2;
                }
                if (n != 0) break block17;
                if (l >= AuroraSky.a(16879, 7978984216505760125L)) break block18;
                return (float)(l2 - AuroraSky.a(5477, 8443782174483377653L)) / 1500.0f;
            }
            l = l2;
        }
        if (l <= AuroraSky.a(1095, 2126772412576726227L)) return 1.0f;
        return 1.0f - (float)(l2 - AuroraSky.a(22446, 8383829533024399165L)) / 1500.0f;
    }

    
    static {
        block12: {
            block11: {
                block10: {
                    block9: {
                        var9 = 4994426674073674822L;
                        var8_1 = new long[3];
                        var12_2 = 0;
                        var13_3 = "]\u008dEM4\u00e5\u00a4E\t0\u00b6g4\u00e5\u00a4\u0006\u0000\u000f\u00f7\u00124\u00e4\u00a4F";
                        var14_4 = "]\u008dEM4\u00e5\u00a4E\t0\u00b6g4\u00e5\u00a4\u0006\u0000\u000f\u00f7\u00124\u00e4\u00a4F".length();
                        var11_5 = 0;
                        while (true) {
                            break block9;
                            break;
                        }
lbl9:

                        while (true) {
                            var8_1[v0] = (((long)var15_6[0] & 255L) << 56 | ((long)var15_6[1] & 255L) << 48 | ((long)var15_6[2] & 255L) << 40 | ((long)var15_6[3] & 255L) << 32 | ((long)var15_6[4] & 255L) << 24 | ((long)var15_6[5] & 255L) << 16 | ((long)var15_6[6] & 255L) << 8 | (long)var15_6[7] & 255L) ^ var9;
                            if (var11_5 < var14_4) continue;
                            break block10;
                            break;
                        }
                    }
                    var15_6 = var13_3.substring(var11_5, var11_5 += 8).getBytes("ISO-8859-1");
                    v0 = var12_2++;
                    while (true)
                }
                AuroraSky.SHEETS = (int)var8_1[0];
                AuroraSky.COLUMNS = (int)var8_1[1];
                var0_7 = 1155419029864949526L;
                var6_8 = new long[8];
                var3_9 = 0;
                var4_10 = "e&\u0088Z\u00e7\b\u00fa7\u0013\u00c5\u00ecL_\u00e0]\u00eb~\u00b3\u00d4\u00a2\u00bc\u0095\u00a8\u00dbdQ\u0087\u00f6\u001c\u008a\u00dd\u00db\r\u008b\u000fA\u00d0o\u008e5Ro\u008d=\u00c0n\u00f9\u00ec";
                var5_11 = "e&\u0088Z\u00e7\b\u00fa7\u0013\u00c5\u00ecL_\u00e0]\u00eb~\u00b3\u00d4\u00a2\u00bc\u0095\u00a8\u00dbdQ\u0087\u00f6\u001c\u008a\u00dd\u00db\r\u008b\u000fA\u00d0o\u008e5Ro\u008d=\u00c0n\u00f9\u00ec".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v1 = var6_8;
                    v2 = var3_9++;
                    v3 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v4 = -1;
                    break block11;
                    break;
                }
lbl37:

                while (true) {
                    v1[v2] = v5;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "\u0010x8R~}K\u00d5\u0013\u00dcr^\u000b\u00c0\u0093\u00cb";
                    var5_11 = "\u0010x8R~}K\u00d5\u0013\u00dcr^\u000b\u00c0\u0093\u00cb".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v1 = var6_8;
                        v2 = var3_9++;
                        v3 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v4 = 0;
                        break block11;
                        break;
                    }
                    break;
                }
lbl50:

                while (true) {
                    v1[v2] = v5;
                    if (var2_12 < var5_11) continue;
                    break block12;
                    break;
                }
            }
            v5 = v3 ^ var0_7;
            switch (v4) {
                default: {
                    continue;
                }
                case 0:
lbl61:

                continue;
            }
        }
        AuroraSky.a = var6_8;
        AuroraSky.b = new Long[8];
        AuroraSky.BUFFER = new BufferAllocator((int)var8_1[2]);
        AuroraSky.LOWEST = (float)Math.toRadians(2.0);
        AuroraSky.HIGHEST = (float)Math.toRadians(72.0);
        AuroraSky.HIGHEST_BASE = (float)Math.toRadians(45.0);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x7890) & Short.MAX_VALUE;
        if (b[n2] == null) {
            AuroraSky.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
