package dev.quelchi.aurora.render;

import dev.quelchi.aurora.ambience.AmbienceDimensions;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.AmbienceModule$CloudStyle;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.PaintedSky;
import dev.quelchi.aurora.render.SkyExtras;
import dev.quelchi.aurora.render.SoftLayer;
import dev.quelchi.aurora.render.StarField;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.BufferAllocator;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

public final class CloudLayer {
    private static final BufferAllocator BUFFER;
    private static final int CELL;
    private static final int RIM;
    private static final float[] RX;
    private static final float[] RY;
    private static final float[] RZ;
    private static final int[] RC;
    private static final float[][] LUMPS;
    private static final float[][] ROW_SHADE;
    private static final long[] a;
    private static final Integer[] b;
    private static final long c;

    private CloudLayer() {
    }

    
    public static void onWorldRender(WorldRenderContext var0) {
        block64: {
            block73: {
                block63: {
                    block62: {
                        block61: {
                            block60: {
                                block59: {
                                    block72: {
                                        block57: {
                                            block58: {
                                                block56: {
                                                    var2_1 = AmbienceModule.get();
                                                    var1_2 = EffectEngine.a;
                                                    v0 = var2_1;
                                                    if (var1_2 != 0) break block56;
                                                    if (v0 == null) break block57;
                                                    v0 = var2_1;
                                                }
                                                v2 = v0.isEnabled();
                                                if (var1_2 != 0) break block58;
                                                if (!v2) break block57;
                                                v2 = PaintedSky.hidesClouds();
                                            }
                                            if (!v2) break block72;
                                        }
                                        return;
                                    }
                                    var3_3 = var2_1.cloudStyle();
                                    v4 = var3_3 == AmbienceModule$CloudStyle.Wispy ? 1 : 0;
                                    var4_4 = v4;
                                    if (var3_3 == AmbienceModule$CloudStyle.Soft || var4_4 != 0) break block59;
                                    return;
                                }
                                var5_5 = MinecraftClient.method_1551();
                                var6_6 = var5_5.field_1687;
                                if (var6_6 == null || var5_5.field_1773 == null) break block60;
                                if (AmbienceDimensions.isOverworldLike(var6_6)) break block61;
                            }
                            return;
                        }
                        var7_7 = var5_5.field_1773.method_19418();
                        var8_8 = var7_7.method_71156();
                        var9_9 = var7_7.method_23767();
                        var10_10 = var9_9.transform(new Vector3f(1.0f, 0.0f, 0.0f));
                        var11_11 = var9_9.transform(new Vector3f(0.0f, 1.0f, 0.0f));
                        var12_12 = Math.min(192.0f, var5_5.field_1773.method_32796());
                        var13_13 = (float)(System.currentTimeMillis() % CloudLayer.c) / 1000.0f * 1.2f * var2_1.cloudSpeed();
                        var14_14 = StarField.nightFactor(var6_6);
                        var15_15 = (float)Math.cos(SkyExtras.sunAngle);
                        var16_16 = Math.max(0.0f, 1.0f - Math.abs(var15_15) / 0.3f) * (1.0f - var14_14);
                        var17_17 = CloudLayer.lerp(var2_1.cloudRgb(), CloudLayer.a(21266, 5791796062838982547L), var14_14 * 0.85f);
                        v11 = var17_17;
                        v12 = var2_1;
                        if (var1_2 != 0) break block62;
                        if (!v12.sunsetGlow()) break block63;
                        v12 = var2_1;
                    }
                    v15 = v12.sunsetRgb();
                    break block73;
                }
                v15 = CloudLayer.a(15009, 8487077430953307681L);
            }
            var18_18 = CloudLayer.lerp(v11, v15, var16_16 * 0.6f);
            v16 = var2_1.cloudOpacity();
            v17 = var4_4 != 0 ? 0.55f : 0.92f;
            var19_19 = v16 * v17;
            v19 = var2_1.cloudHeight();
            v20 = var4_4 != 0 ? 40.0f : 0.0f;
            var20_20 = v19 + v20;
            var21_21 = VertexConsumerProvider.method_22991((BufferAllocator)CloudLayer.BUFFER);
            var22_22 = var21_21.method_73477(SoftLayer.layer());
            var23_23 = var0.matrices().method_23760().method_23761();
            var24_24 = (int)Math.floor((var8_8.field_1352 - (double)var12_12 + (double)var13_13) / 32.0);
            var25_25 = (int)Math.floor((var8_8.field_1352 + (double)var12_12 + (double)var13_13) / 32.0);
            var26_26 = (int)Math.floor((var8_8.field_1350 - (double)var12_12) / 32.0);
            var27_27 = (int)Math.floor((var8_8.field_1350 + (double)var12_12) / 32.0);
            var28_28 = var24_24;
            while (var28_28 <= var25_25) {
                block65: {
                    if (var1_2 != 0) break block64;
                    for (var29_29 = var26_26; var29_29 <= var27_27; ++var29_29) {
                        block71: {
                            block69: {
                                block68: {
                                    block66: {
                                        block67: {
                                            if (var1_2 != 0) break block65;
                                            v22 = var28_28;
                                            v23 = var29_29;
                                            v24 = var4_4;
                                            if (var1_2 != 0) break block66;
                                            if (v24 == 0) break block67;
                                            v24 = 7;
                                            break block66;
                                        }
                                        v24 = 3;
                                    }
                                    v27 = CloudLayer.hash(v22, v23, v24);
                                    v28 = var4_4 != 0 ? 0.62f : 0.52f;
                                    cfr_temp_0 = v27 - v28;
                                    v30 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                    if (var1_2 != 0) break block68;
                                    if (v30 < 0) {
                                        continue;
                                    }
                                    v30 = var28_28;
                                }
                                var30_30 = CloudLayer.hash((int)v30, var29_29, 11) * 32.0f * 0.5f;
                                var31_31 = CloudLayer.hash(var28_28, var29_29, 13) * 32.0f * 0.5f;
                                var32_32 = (float)(var28_28 * 32) - var13_13 + var30_30;
                                var34_33 = (float)(var29_29 * 32) + var31_31;
                                var36_34 = var32_32 - var8_8.field_1352;
                                var38_35 = var34_33 - var8_8.field_1350;
                                var40_36 = (float)Math.sqrt(var36_34 * var36_34 + var38_35 * var38_35);
                                v32 = var40_36;
                                if (var1_2 != 0) break block69;
                                if (v32 > var12_12) {
                                    continue;
                                }
                                v32 = 1.0f - var40_36 / var12_12;
                            }
                            var41_37 = v32;
                            var42_38 = Math.round(255.0f * var19_19 * Math.min(1.0f, var41_37 * 2.5f));
                            if (var42_38 < 2) {
                                continue;
                            }
                            var43_39 = 32.0f * (0.55f + 0.5f * CloudLayer.hash(var28_28, var29_29, 17));
                            var44_40 = (float)((double)var20_20 - var8_8.field_1351);
                            if (var1_2 != 0) break block71;
                            if (var4_4 != 0) {
                            }
                            CloudLayer.wisp(var22_22, var23_23, (float)var36_34, var44_40, (float)var38_35, var43_39, var28_28, var29_29, var18_18, var42_38);
                        }
                        if (var1_2 == 0) continue;
lbl194:

                        CloudLayer.cumulus(var22_22, var23_23, var10_10, var11_11, (float)var36_34, var44_40, (float)var38_35, var43_39, var28_28, var29_29, var17_17, var18_18, var42_38);
                        continue;
                    }
                    ++var28_28;
                }
                if (var1_2 == 0) continue;
            }
            var21_21.method_22993();
        }
    }

    private static void cumulus(VertexConsumer vertexConsumer, Matrix4f matrix4f, Vector3f vector3f, Vector3f vector3f2, float f, float f2, float f3, float f4, int n, int n2, int n3, int n4, int n5) {
        int n6 = 0;
        int n7 = EffectEngine.a;
        while (n6 < 3) {
            block9: {
                for (int i = 0; i < LUMPS.length; ++i) {
                    float f5;
                    float[] fArray;
                    block10: {
                        fArray = LUMPS[i];
                        if (n7 != 0) break block9;
                        f5 = fArray[4];
                        if (n7 != 0) break block10;
                        if (f5 != (float)n6) {
                            continue;
                        }
                        f5 = 0.8f + 0.4f * CloudLayer.hash(n * 7 + i, n2, 23);
                    }
                    float f6 = f5;
                    float f7 = fArray[3] * f4 * f6 * 1.35f;
                    float f8 = f + fArray[0] * f4 * (0.8f + 0.4f * CloudLayer.hash(n, n2 + i, 29));
                    float f9 = f3 + fArray[1] * f4;
                    float f10 = f2 + fArray[2] * f4;
                    CloudLayer.puff(vertexConsumer, matrix4f, vector3f, vector3f2, f8, f10, f9, f7, CloudLayer.shade(n3, ROW_SHADE[n6][0]), CloudLayer.shade(n4, ROW_SHADE[n6][1]), n5, n * 31 + i, n2);
                    if (n7 == 0) continue;
                }
                ++n6;
            }
            if (n7 == 0) continue;
        }
    }

    private static void puff(VertexConsumer vertexConsumer, Matrix4f matrix4f, Vector3f vector3f, Vector3f vector3f2, float f, float f2, float f3, float f4, int n, int n2, int n3, int n4, int n5) {
        int n6;
        int[] nArray;
        float[] fArray;
        int n7;
        float[] fArray2;
        float[] fArray3;
        int n8;
        float f5;
        block5: {
            f5 = 0.5f;
            n8 = CloudLayer.lerp(n, n2, 0.55f);
            fArray3 = RX;
            fArray2 = RY;
            n7 = EffectEngine.a;
            fArray = RZ;
            nArray = RC;
            for (n6 = 0; n6 <= 14; ++n6) {
                double d = Math.PI * 2 * (double)n6 / 14.0;
                float f6 = (float)Math.cos(d);
                float f7 = (float)Math.sin(d);
                float f8 = 0.85f + 0.3f * CloudLayer.hash(n4, n5 + n6 % 14, 41);
                fArray3[n6] = (vector3f.x * f6 + vector3f2.x * f7) * f4 * f8;
                fArray2[n6] = (vector3f.y * f6 + vector3f2.y * f7) * f4 * f8;
                fArray[n6] = (vector3f.z * f6 + vector3f2.z * f7) * f4 * f8;
                if (n7 == 0) {
                    nArray[n6] = CloudLayer.lerp(n, n2, f7 * 0.5f + 0.5f);
                    if (n7 == 0) continue;
                    break;
                }
                break block5;
            }
            n6 = 0;
        }
        while (n6 < 14) {
            int n9 = CloudLayer.lerp(n8, nArray[n6], f5);
            int n10 = CloudLayer.lerp(n8, nArray[n6 + 1], f5);
            CloudLayer.vert(vertexConsumer, matrix4f, f, f2, f3, n8, n3);
            CloudLayer.vert(vertexConsumer, matrix4f, f + fArray3[n6] * f5, f2 + fArray2[n6] * f5, f3 + fArray[n6] * f5, n9, n3);
            CloudLayer.vert(vertexConsumer, matrix4f, f + fArray3[n6 + 1] * f5, f2 + fArray2[n6 + 1] * f5, f3 + fArray[n6 + 1] * f5, n10, n3);
            CloudLayer.vert(vertexConsumer, matrix4f, f, f2, f3, n8, n3);
            CloudLayer.vert(vertexConsumer, matrix4f, f + fArray3[n6] * f5, f2 + fArray2[n6] * f5, f3 + fArray[n6] * f5, n9, n3);
            CloudLayer.vert(vertexConsumer, matrix4f, f + fArray3[n6 + 1] * f5, f2 + fArray2[n6 + 1] * f5, f3 + fArray[n6 + 1] * f5, n10, n3);
            CloudLayer.vert(vertexConsumer, matrix4f, f + fArray3[n6 + 1], f2 + fArray2[n6 + 1], f3 + fArray[n6 + 1], nArray[n6 + 1], 0);
            CloudLayer.vert(vertexConsumer, matrix4f, f + fArray3[n6], f2 + fArray2[n6], f3 + fArray[n6], nArray[n6], 0);
            ++n6;
            if (n7 == 0) continue;
        }
    }

    private static void wisp(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, int n, int n2, int n3, int n4) {
        float f5 = (CloudLayer.hash(n, n2, 59) - 0.5f) * 0.9f;
        float f6 = (float)Math.cos(f5);
        int n5 = EffectEngine.a;
        float f7 = (float)Math.sin(f5);
        CloudLayer.veil(vertexConsumer, matrix4f, f, f2, f3, f4 * 2.8f, f4 * 1.1f, f6, f7, n3, Math.round((float)n4 * 0.3f));
        for (int i = 0; i < 3; ++i) {
            float f8 = f4 * (2.2f + 1.2f * CloudLayer.hash(n, n2 + i, 31));
            float f9 = f4 * (0.22f + 0.16f * CloudLayer.hash(n + i, n2, 37));
            float f10 = ((float)i - 1.0f) * f4 * 0.4f + (CloudLayer.hash(n, n2, 61 + i) - 0.5f) * f4 * 0.3f;
            float f11 = (CloudLayer.hash(n + i, n2, 43) - 0.5f) * f4 * 0.9f;
            CloudLayer.streak(vertexConsumer, matrix4f, f, f2 + (float)i * 0.6f, f3, f8, f9, f10, f11, f6, f7, n3, Math.round((float)n4 * (0.4f + 0.3f * CloudLayer.hash(n, n2 + i, 47))));
            if (n5 == 0) continue;
        }
    }

    private static void veil(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, float f7, int n, int n2) {
        int n3 = 16;
        int n4 = EffectEngine.a;
        for (int i = 0; i < n3; ++i) {
            double d = Math.PI * 2 * (double)i / (double)n3;
            double d2 = Math.PI * 2 * (double)(i + 1) / (double)n3;
            float f8 = (float)Math.cos(d) * f4;
            float f9 = (float)Math.sin(d) * f5;
            float f10 = (float)Math.cos(d2) * f4;
            float f11 = (float)Math.sin(d2) * f5;
            CloudLayer.vert(vertexConsumer, matrix4f, f, f2, f3, n, n2);
            CloudLayer.vert(vertexConsumer, matrix4f, f + f8 * f6 - f9 * f7, f2, f3 + f8 * f7 + f9 * f6, n, 0);
            CloudLayer.vert(vertexConsumer, matrix4f, f + f10 * f6 - f11 * f7, f2, f3 + f10 * f7 + f11 * f6, n, 0);
            CloudLayer.vert(vertexConsumer, matrix4f, f, f2, f3, n, n2);
            if (n4 == 0) continue;
        }
    }

    private static void streak(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n, int n2) {
        int n3 = 12;
        int n4 = EffectEngine.a;
        for (int i = 0; i < n3; ++i) {
            float f10 = (float)i / (float)n3;
            float f11 = (float)(i + 1) / (float)n3;
            float f12 = (float)Math.pow(Math.sin(Math.PI * (double)f10), 0.8);
            float f13 = (float)Math.pow(Math.sin(Math.PI * (double)f11), 0.8);
            float f14 = f5 * f12;
            float f15 = f5 * f13;
            int n5 = Math.round((float)n2 * f12);
            int n6 = Math.round((float)n2 * f13);
            float f16 = (f10 - 0.5f) * f4;
            float f17 = (f11 - 0.5f) * f4;
            float f18 = f6 + f7 * (float)Math.sin(Math.PI * (double)f10);
            float f19 = f6 + f7 * (float)Math.sin(Math.PI * (double)f11);
            float f20 = f + f16 * f8 - f18 * f9;
            float f21 = f3 + f16 * f9 + f18 * f8;
            float f22 = f + f17 * f8 - f19 * f9;
            float f23 = f3 + f17 * f9 + f19 * f8;
            float f24 = -f9;
            float f25 = f8;
            CloudLayer.vert(vertexConsumer, matrix4f, f20 - f24 * f14, f2, f21 - f25 * f14, n, 0);
            CloudLayer.vert(vertexConsumer, matrix4f, f22 - f24 * f15, f2, f23 - f25 * f15, n, 0);
            CloudLayer.vert(vertexConsumer, matrix4f, f22, f2, f23, n, n6);
            CloudLayer.vert(vertexConsumer, matrix4f, f20, f2, f21, n, n5);
            CloudLayer.vert(vertexConsumer, matrix4f, f20, f2, f21, n, n5);
            CloudLayer.vert(vertexConsumer, matrix4f, f22, f2, f23, n, n6);
            CloudLayer.vert(vertexConsumer, matrix4f, f22 + f24 * f15, f2, f23 + f25 * f15, n, 0);
            CloudLayer.vert(vertexConsumer, matrix4f, f20 + f24 * f14, f2, f21 + f25 * f14, n, 0);
            if (n4 == 0) continue;
        }
    }

    private static void vert(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, int n, int n2) {
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f, f2, f3).method_1336(n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF, n2);
    }

    private static int shade(int n, float f) {
        int n2 = Math.min(255, Math.round((float)(n >> 16 & 0xFF) * f));
        int n3 = Math.min(255, Math.round((float)(n >> 8 & 0xFF) * f));
        int n4 = Math.min(255, Math.round((float)(n & 0xFF) * f));
        return n2 << 16 | n3 << 8 | n4;
    }

    static int lerp(int n, int n2, float f) {
        int n3 = n >> 16 & 0xFF;
        int n4 = n >> 8 & 0xFF;
        int n5 = n & 0xFF;
        int n6 = n2 >> 16 & 0xFF;
        int n7 = n2 >> 8 & 0xFF;
        int n8 = n2 & 0xFF;
        return Math.round((float)n3 + (float)(n6 - n3) * f) << 16 | Math.round((float)n4 + (float)(n7 - n4) * f) << 8 | Math.round((float)n5 + (float)(n8 - n5) * f);
    }

    static float hash(int n, int n2, int n3) {
        int n4 = n * CloudLayer.a(25931, 1132868604725506497L) ^ n2 * CloudLayer.a(11538, 5288698269221845393L) ^ n3 * CloudLayer.a(26349, 517614485230498408L);
        n4 ^= n4 >>> 13;
        n4 *= CloudLayer.a(6782, 1134661114410618620L);
        n4 ^= n4 >>> 15;
        return (float)(n4 & CloudLayer.a(3809, 1268350840070101605L)) / 1.6777216E7f;
    }

    
    static {
        block10: {
            block9: {
                block8: {
                    var2 = 4460461588106028647L;
                    var8_1 = new long[10];
                    var5_2 = 0;
                    var6_3 = "[\u00dan\u008f\u0091B\u001fi>\u00b4{\u00cfN`\u0081\u000e>\u00e9\u001b\u00ce\u00b4q\u008b\u00b0L\u008enHp\u009e\u00fa<\u0019s2\u00a8;/\u00c0\u0005\u0092\u00c8\u00ec\r\u0086`\u00a3\u00a6\u0015\u0002z Kn\u00eb\u00b8m\u00ca\u009f\u00f2\u00f5X\u0003\u00fd";
                    var7_4 = "[\u00dan\u008f\u0091B\u001fi>\u00b4{\u00cfN`\u0081\u000e>\u00e9\u001b\u00ce\u00b4q\u008b\u00b0L\u008enHp\u009e\u00fa<\u0019s2\u00a8;/\u00c0\u0005\u0092\u00c8\u00ec\r\u0086`\u00a3\u00a6\u0015\u0002z Kn\u00eb\u00b8m\u00ca\u009f\u00f2\u00f5X\u0003\u00fd".length();
                    var4_5 = 0;
                    while (true) {
                        var9_6 = var6_3.substring(var4_5, var4_5 += 8).getBytes("ISO-8859-1");
                        v0 = var8_1;
                        v1 = var5_2++;
                        v2 = ((long)var9_6[0] & 255L) << 56 | ((long)var9_6[1] & 255L) << 48 | ((long)var9_6[2] & 255L) << 40 | ((long)var9_6[3] & 255L) << 32 | ((long)var9_6[4] & 255L) << 24 | ((long)var9_6[5] & 255L) << 16 | ((long)var9_6[6] & 255L) << 8 | (long)var9_6[7] & 255L;
                        v3 = -1;
                        break block8;
                        break;
                    }
lbl14:

                    while (true) {
                        v0[v1] = v4;
                        if (var4_5 < var7_4) continue;
                        var6_3 = "\u0000\u00e3Z\u0004\u00b4\u00ce\u001a\u0006hq\u00c7W\u00e0\u00e8c\u00fb";
                        var7_4 = "\u0000\u00e3Z\u0004\u00b4\u00ce\u001a\u0006hq\u00c7W\u00e0\u00e8c\u00fb".length();
                        var4_5 = 0;
                        while (true) {
                            var9_6 = var6_3.substring(var4_5, var4_5 += 8).getBytes("ISO-8859-1");
                            v0 = var8_1;
                            v1 = var5_2++;
                            v2 = ((long)var9_6[0] & 255L) << 56 | ((long)var9_6[1] & 255L) << 48 | ((long)var9_6[2] & 255L) << 40 | ((long)var9_6[3] & 255L) << 32 | ((long)var9_6[4] & 255L) << 24 | ((long)var9_6[5] & 255L) << 16 | ((long)var9_6[6] & 255L) << 8 | (long)var9_6[7] & 255L;
                            v3 = 0;
                            break block8;
                            break;
                        }
                        break;
                    }
lbl27:

                    while (true) {
                        v0[v1] = v4;
                        if (var4_5 < var7_4) continue;
                        break block9;
                        break;
                    }
                }
                v4 = v2 ^ var2;
                switch (v3) {
                    default: {
                        continue;
                    }
                    case 0:
lbl38:

                    continue;
                }
            }
            CloudLayer.a = var8_1;
            CloudLayer.b = new Integer[10];
            CloudLayer.RIM = CloudLayer.a(20809, 7796258961265853903L);
            CloudLayer.CELL = CloudLayer.a(26826, 4814235637691169857L);
            break block10;
lbl45:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 2542886620873610648L;
        while (true)
        CloudLayer.c = 2542886620876847896L ^ var0_7;
        CloudLayer.BUFFER = new BufferAllocator(CloudLayer.a(13029, 2779831694640965218L));
        CloudLayer.RX = new float[15];
        CloudLayer.RY = new float[15];
        CloudLayer.RZ = new float[15];
        CloudLayer.RC = new int[15];
        CloudLayer.LUMPS = new float[][]{{-0.75f, 0.05f, 0.2f, 0.5f, 0.0f}, {0.0f, -0.1f, 0.18f, 0.6f, 0.0f}, {0.75f, 0.1f, 0.22f, 0.5f, 0.0f}, {-0.35f, 0.15f, 0.55f, 0.48f, 1.0f}, {0.4f, -0.05f, 0.6f, 0.45f, 1.0f}, {0.05f, 0.0f, 0.88f, 0.38f, 2.0f}};
        CloudLayer.ROW_SHADE = new float[][]{{0.62f, 0.86f}, {0.78f, 0.97f}, {0.9f, 1.05f}};
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5483;
        if (b[n2] == null) {
            CloudLayer.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
