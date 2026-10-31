package dev.quelchi.aurora.render;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.PaintedSky;
import dev.quelchi.aurora.render.SkyGlow;
import java.util.Random;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.world.ClientWorld;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class StarField {
    private static final BufferAllocator BUFFER;
    private static final int MAX_STARS;
    private static final long SEED;
    private static float[] dirX;
    private static float[] dirY;
    private static float[] dirZ;
    private static float[] scale;
    private static float[] phase;
    private static float[] tint;
    public static float viewFov;
    private static final long[] a;
    private static final Long[] b;

    private StarField() {
    }

    
    private static void build() {
        block6: {
            block7: {
                var0 = EffectEngine.a;
                v0 = StarField.dirX;
                if (var0 != 0) break block6;
                if (v0 == null) break block7;
                return;
            }
            StarField.dirX = new float[3000];
            StarField.dirY = new float[3000];
            StarField.dirZ = new float[3000];
            StarField.scale = new float[3000];
            StarField.phase = new float[3000];
            v0 = new float[3000];
        }
        StarField.tint = v0;
        var1_1 = new Random(StarField.a(14140, 264829435125929604L));
        for (var2_2 = 0; var2_2 < 3000; ++var2_2) {
            block3: while (true) {
                if ((var6_6 = (var3_3 = var1_1.nextFloat() * 2.0f - 1.0f) * var3_3 + (var4_4 = var1_1.nextFloat() * 2.0f - 1.0f) * var4_4 + (var5_5 = var1_1.nextFloat() * 2.0f - 1.0f) * var5_5) > 1.0f) {
                    continue;
                }
                v2 = var6_6;
                do {
                    if (!(v2 < 1.0E-4f)) break;
                    continue block3;
                    v2 = (float)Math.sqrt(var6_6);
                } while (var0 != 0);
                break;
            }
            var6_6 = v2;
            StarField.dirX[var2_2] = var3_3 / var6_6;
            StarField.dirY[var2_2] = var4_4 / var6_6;
            StarField.dirZ[var2_2] = var5_5 / var6_6;
            var7_7 = var1_1.nextFloat();
            StarField.scale[var2_2] = 0.35f + 0.65f * var7_7 * var7_7 * var7_7;
            StarField.phase[var2_2] = var1_1.nextFloat() * 6.2831855f;
            StarField.tint[var2_2] = var1_1.nextFloat();
            if (var0 == 0) continue;
        }
    }

    public static void onWorldRender(WorldRenderContext worldRenderContext) {
        block51: {
            float f;
            ClientWorld clientWorld;
            MinecraftClient minecraftClient;
            int n;
            AmbienceModule ambienceModule;
            block49: {
                block50: {
                    float f2;
                    float f3;
                    block47: {
                        block48: {
                            MinecraftClient minecraftClient2;
                            block45: {
                                block46: {
                                    block44: {
                                        block55: {
                                            block41: {
                                                boolean bl;
                                                block43: {
                                                    AmbienceModule ambienceModule2;
                                                    block42: {
                                                        block40: {
                                                            ambienceModule = AmbienceModule.get();
                                                            n = EffectEngine.a;
                                                            ambienceModule2 = ambienceModule;
                                                            if (n != 0) break block40;
                                                            if (ambienceModule2 == null) break block41;
                                                            ambienceModule2 = ambienceModule;
                                                        }
                                                        if (n != 0) break block42;
                                                        if (!ambienceModule2.isEnabled()) break block41;
                                                        ambienceModule2 = ambienceModule;
                                                    }
                                                    bl = ambienceModule2.stars();
                                                    if (n != 0) break block43;
                                                    if (!bl) break block41;
                                                    bl = PaintedSky.active();
                                                }
                                                if (!bl) break block55;
                                            }
                                            return;
                                        }
                                        minecraftClient = MinecraftClient.method_1551();
                                        clientWorld = minecraftClient.field_1687;
                                        if (clientWorld == null) break block44;
                                        minecraftClient2 = minecraftClient;
                                        if (n != 0) break block45;
                                        if (minecraftClient2.field_1773 != null) break block46;
                                    }
                                    return;
                                }
                                minecraftClient2 = minecraftClient;
                            }
                            float f4 = minecraftClient2.method_61966().method_60637(false);
                            float f5 = 1.0f - clientWorld.method_8430(f4);
                            f3 = ambienceModule.starBrightness();
                            f2 = f5;
                            if (n != 0) break block47;
                            f3 = f3 * f2;
                            if (!ambienceModule.starsInDay()) break block48;
                            f2 = 1.0f;
                            break block47;
                        }
                        f2 = StarField.nightFactor(clientWorld);
                    }
                    f = f3 * f2;
                    if (n != 0) break block49;
                    if (!(f <= 0.01f)) break block50;
                    return;
                }
                StarField.build();
            }
            Camera camera = minecraftClient.field_1773.method_19418();
            Quaternionf quaternionf = camera.method_23767();
            Vector3f vector3f = quaternionf.transform(new Vector3f(1.0f, 0.0f, 0.0f));
            Vector3f vector3f2 = quaternionf.transform(new Vector3f(0.0f, 1.0f, 0.0f));
            Vector3f vector3f3 = quaternionf.transform(new Vector3f(0.0f, 0.0f, -1.0f));
            double d = Math.tan(Math.toRadians((double)Math.max(viewFov, (float)((Integer)minecraftClient.field_1690.method_41808().method_41753()).intValue()) * 0.5));
            double d2 = (double)minecraftClient.method_22683().method_4489() / (double)Math.max(1, minecraftClient.method_22683().method_4506());
            float f6 = (float)Math.cos(Math.min(Math.PI, Math.atan(d * Math.sqrt(1.0 + d2 * d2)) + Math.toRadians(10.0)));
            float f7 = minecraftClient.field_1773.method_32796();
            float f8 = Math.min(75.0f, Math.max(32.0f, f7 * 0.5f));
            float f9 = (float)((double)Math.floorMod(clientWorld.method_8532(), StarField.a(4754, 423911264562131759L)) / 24000.0 * Math.PI * 2.0);
            float f10 = (float)Math.sin(f9);
            float f11 = (float)Math.cos(f9);
            int n2 = Math.min(3000, ambienceModule.starCount());
            float f12 = ambienceModule.starSize() * f8 * 0.0016f;
            float f13 = (float)ambienceModule.starTwinkle() / 100.0f;
            float f14 = (float)ambienceModule.starTwinkleSpeed() / 100.0f;
            float f15 = (float)(System.currentTimeMillis() % StarField.a(30507, 5750578598088201883L)) / 1000.0f * 3.0f * f14;
            int n3 = ambienceModule.starRgb();
            int n4 = n3 >> 16 & 0xFF;
            int n5 = n3 >> 8 & 0xFF;
            int n6 = n3 & 0xFF;
            VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.method_22991((BufferAllocator)BUFFER);
            VertexConsumer vertexConsumer = immediate.method_73477(SkyGlow.layer());
            Matrix4f matrix4f = worldRenderContext.matrices().method_23760().method_23761();
            for (int i = 0; i < n2; ++i) {
                float f16;
                float f17;
                float f18;
                float f19;
                float f20;
                block54: {
                    float f21;
                    float f22;
                    block52: {
                        f20 = dirX[i];
                        if (n != 0) break block51;
                        f19 = dirY[i] * f11 - dirZ[i] * f10;
                        f18 = dirY[i] * f10 + dirZ[i] * f11;
                        f22 = f19;
                        f21 = -0.05f;
                        if (n != 0) break block52;
                        if (f22 < f21) {
                            continue;
                        }
                        f17 = f20;
                        f16 = vector3f3.x;
                        if (n != 0) break block54;
                        f22 = f17 * f16 + f19 * vector3f3.y + f18 * vector3f3.z;
                        f21 = f6;
                    }
                    if (f22 < f21) {
                        continue;
                    }
                    f17 = 1.0f;
                    f16 = f13 * 0.5f * (1.0f - (float)Math.sin(f15 + phase[i]));
                }
                float f23 = f17 - f16;
                int n7 = Math.round(255.0f * f * scale[i] * Math.max(0.0f, f23));
                if (n7 <= 1) {
                    continue;
                }
                float f24 = tint[i] - 0.5f;
                int n8 = StarField.clamp(n4 + Math.round(f24 * 40.0f));
                int n9 = StarField.clamp(n5);
                int n10 = StarField.clamp(n6 - Math.round(f24 * 40.0f));
                StarField.spark(vertexConsumer, matrix4f, f20 * f8, f19 * f8, f18 * f8, vector3f, vector3f2, f12 * scale[i], n8, n9, n10, n7);
                if (n == 0) continue;
            }
            immediate.method_22993();
        }
    }

    private static int clamp(int n) {
        int n2;
        block10: {
            block11: {
                int n3;
                block8: {
                    block9: {
                        n3 = EffectEngine.a;
                        n2 = n;
                        if (n3 != 0) break block8;
                        if (n2 >= 0) break block9;
                        n2 = 0;
                        break block10;
                    }
                    n2 = n;
                }
                if (n3 != 0) break block10;
                if (n2 <= 255) break block11;
                n2 = 255;
                break block10;
            }
            n2 = n;
        }
        return n2;
    }

    private static void spark(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, Vector3f vector3f, Vector3f vector3f2, float f4, int n, int n2, int n3, int n4) {
        float f5 = vector3f.x * f4;
        float f6 = vector3f.y * f4;
        float f7 = vector3f.z * f4;
        float f8 = vector3f2.x * f4;
        float f9 = vector3f2.y * f4;
        float f10 = vector3f2.z * f4;
        float f11 = f - f5 - f8;
        float f12 = f2 - f6 - f9;
        float f13 = f3 - f7 - f10;
        float f14 = f - f5 + f8;
        float f15 = f2 - f6 + f9;
        float f16 = f3 - f7 + f10;
        float f17 = f + f5 + f8;
        float f18 = f2 + f6 + f9;
        float f19 = f3 + f7 + f10;
        float f20 = f + f5 - f8;
        float f21 = f2 + f6 - f9;
        float f22 = f3 + f7 - f10;
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f11, f12, f13).method_1336(n, n2, n3, n4);
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f14, f15, f16).method_1336(n, n2, n3, n4);
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f17, f18, f19).method_1336(n, n2, n3, n4);
        vertexConsumer.method_22918((Matrix4fc)matrix4f, f20, f21, f22).method_1336(n, n2, n3, n4);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static float nightFactor(ClientWorld clientWorld) {
        long l;
        long l2;
        block17: {
            block18: {
                int n;
                block15: {
                    block16: {
                        block14: {
                            l2 = Math.floorMod(clientWorld.method_8532(), StarField.a(11734, 7593054361130175594L));
                            n = EffectEngine.a;
                            l = l2;
                            if (n != 0) break block14;
                            if (l < StarField.a(2622, 2941606351900715919L)) return 0.0f;
                            l = l2;
                        }
                        if (n != 0) break block15;
                        if (l <= StarField.a(19525, 2101066359349538303L)) break block16;
                        return 0.0f;
                    }
                    l = l2;
                }
                if (n != 0) break block17;
                if (l >= StarField.a(24687, 8219220592533520861L)) break block18;
                return (float)(l2 - StarField.a(31505, 6813629170304506536L)) / 1500.0f;
            }
            l = l2;
        }
        if (l <= StarField.a(10019, 7216439277547718300L)) return 1.0f;
        return 1.0f - (float)(l2 - StarField.a(21434, 974083776381645316L)) / 1500.0f;
    }

    
    static {
        block12: {
            block11: {
                block10: {
                    block9: {
                        var9 = 5532970617889494717L;
                        var8_1 = new long[2];
                        var12_2 = 0;
                        var13_3 = "E\u00da\u007f\u008eG\u00fb\u0016\u00bd\u00aa\u0086\u001b\u0015G\u00fa\u001d\u0005";
                        var14_4 = "E\u00da\u007f\u008eG\u00fb\u0016\u00bd\u00aa\u0086\u001b\u0015G\u00fa\u001d\u0005".length();
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
                StarField.MAX_STARS = (int)var8_1[1];
                var0_7 = 5833477931547392906L;
                var6_8 = new long[11];
                var3_9 = 0;
                var4_10 = "SX0\u00e7\u000e\u0006\u00bfO\u000ezr\u00e4\u00dd\u00cb\u0011\u00f6M\u00dc\u00d1\u00fdD{M\u00b9L\u0093\u00e8\u008a7\u0090S\u00979\u00ab_\u00f3\u00b9\u0093* U\u0016\u00a4I\u001eu\u0015e]p\tM*D\u00dc~4\u00d1\\\fk\u009e\u00a8\u00e6\u001f:\u0085\u0081\u00ea\u00c2C\u0091";
                var5_11 = "SX0\u00e7\u000e\u0006\u00bfO\u000ezr\u00e4\u00dd\u00cb\u0011\u00f6M\u00dc\u00d1\u00fdD{M\u00b9L\u0093\u00e8\u008a7\u0090S\u00979\u00ab_\u00f3\u00b9\u0093* U\u0016\u00a4I\u001eu\u0015e]p\tM*D\u00dc~4\u00d1\\\fk\u009e\u00a8\u00e6\u001f:\u0085\u0081\u00ea\u00c2C\u0091".length();
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
lbl36:

                while (true) {
                    v1[v2] = v5;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "x&\u0002!\u00c0\u00e5`\u00d1\"\u00e4$\u00d1n\u007f\f\u00e7";
                    var5_11 = "x&\u0002!\u00c0\u00e5`\u00d1\"\u00e4$\u00d1n\u007f\f\u00e7".length();
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
lbl49:

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
lbl60:

                continue;
            }
        }
        StarField.a = var6_8;
        StarField.b = new Long[11];
        StarField.SEED = StarField.a(23527, 2046609619147127388L);
        StarField.BUFFER = new BufferAllocator((int)var8_1[0]);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x21B8) & Short.MAX_VALUE;
        if (b[n2] == null) {
            StarField.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
