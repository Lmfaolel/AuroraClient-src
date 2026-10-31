package dev.quelchi.aurora.render;

import dev.quelchi.aurora.music.Lyrics;
import dev.quelchi.aurora.music.Lyrics$Line;
import dev.quelchi.aurora.music.Lyrics$Word;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.LyricWords$Floater;
import dev.quelchi.aurora.render.SdfText;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class LyricWords {
    private static final BufferAllocator BUFFER;
    private static final float UNIT = 0.02f;
    private static final long LEAD;
    private static final long POP;
    private static final long FADE;
    private static final float GHOST_A = 0.3f;
    private static final int MAX;
    private static final Matrix4f LEAN;
    private static final List<LyricWords$Floater> live;
    private static String key;
    private static long last;
    private static Vec3d anchor;
    private static final long[] a;
    private static final Long[] b;

    private LyricWords() {
    }

    public static void clear() {
        live.clear();
        key = null;
        last = LyricWords.a(29001, 7108690465899877417L);
    }

    private static Vec3d toWorld(LyricWords$Floater lyricWords$Floater) {
        return anchor.method_1031(lyricWords$Floater.x(), lyricWords$Floater.y(), lyricWords$Floater.z());
    }

    private static Vec3d toLocal(Vec3d vec3d) {
        return vec3d.method_1020(anchor);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void render(WorldRenderContext worldRenderContext, List<Lyrics$Line> list, long l, String string, float f, float f2, float f3, int n, int n2) {
        Object object;
        Object object2;
        int n3;
        long l2;
        Camera camera;
        int n4;
        block73: {
            long l3;
            MinecraftClient minecraftClient;
            block71: {
                block72: {
                    long l4;
                    block70: {
                        block68: {
                            block69: {
                                MinecraftClient minecraftClient2;
                                block66: {
                                    block67: {
                                        block65: {
                                            block64: {
                                                minecraftClient = MinecraftClient.method_1551();
                                                n4 = EffectEngine.a;
                                                minecraftClient2 = minecraftClient;
                                                if (n4 != 0) break block64;
                                                if (minecraftClient2.field_1724 == null) return;
                                                minecraftClient2 = minecraftClient;
                                            }
                                            if (n4 != 0) break block65;
                                            if (minecraftClient2.field_1687 == null) return;
                                            minecraftClient2 = minecraftClient;
                                        }
                                        if (n4 != 0) break block66;
                                        if (minecraftClient2.field_1773 != null) break block67;
                                        return;
                                    }
                                    minecraftClient2 = minecraftClient;
                                }
                                camera = minecraftClient2.field_1773.method_19418();
                                l2 = (long)(f3 * 1000.0f);
                                if (n4 != 0) break block68;
                                if (!string.equals(key)) break block69;
                                l4 = l;
                                if (n4 != 0) break block70;
                                if (l4 < last) break block69;
                                l3 = l;
                                if (n4 != 0) break block71;
                                if (l3 - last <= LyricWords.a(7226, 932528948392360283L)) break block72;
                            }
                            live.clear();
                            key = string;
                        }
                        l4 = l;
                    }
                    last = l4;
                }
                l3 = l + LyricWords.a(1034, 8732855112282759525L);
            }
            long l5 = l3;
            int n5 = Lyrics.at(list, l5);
            for (int i = Math.max(0, n5 - 1); i <= n5 + 1; ++i) {
                n3 = i;
                if (n4 != 0 || n4 != 0) break block73;
                if (n3 >= list.size()) break;
                Iterator<Lyrics$Word> iterator = list.get(i).words().iterator();
                block53: while (true) {
                    object2 = iterator;
                    while (object2.hasNext()) {
                        block75: {
                            long l6;
                            long l7;
                            block74: {
                                iterator = object2;
                                if (n4 != 0) continue block53;
                                object = iterator.next();
                                l7 = ((Lyrics$Word)object).start();
                                l6 = last + LyricWords.a(20394, 6128019022397942473L);
                                if (n4 != 0) break block74;
                                if (l7 <= l6) break block75;
                                l7 = ((Lyrics$Word)object).start();
                                l6 = l5;
                            }
                            if (l7 <= l6) {
                                LyricWords.spawn(minecraftClient, camera, (Lyrics$Word)object, f, f2);
                            }
                        }
                        if (n4 == 0) continue;
                    }
                    break;
                }
                if (n4 == 0) continue;
            }
            last = l;
            live.removeIf(arg_0 -> LyricWords.lambda$render$0(l, l2, arg_0));
            n3 = live.isEmpty() ? 1 : 0;
        }
        if (n3 != 0) {
            return;
        }
        Vec3d vec3d = camera.method_71156();
        object2 = new Quaternionf((Quaternionfc)camera.method_23767()).conjugate();
        object = worldRenderContext.matrices().method_23760().method_23761();
        VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.method_22991((BufferAllocator)BUFFER);
        for (LyricWords$Floater lyricWords$Floater : live) {
            int n6;
            float f4;
            float f5;
            Matrix4f matrix4f;
            int n7;
            block81: {
                block79: {
                    block80: {
                        block78: {
                            float f6;
                            long l8;
                            block83: {
                                float f7;
                                float f8;
                                long l9;
                                block82: {
                                    block76: {
                                        long l10;
                                        block77: {
                                            if (n4 != 0) return;
                                            l9 = l - lyricWords$Floater.start();
                                            long l11 = l9 - 0L;
                                            l10 = l11 == 0L ? 0 : (l11 < 0L ? -1 : 1);
                                            if (n4 != 0) break block76;
                                            if (l10 < 0) break block77;
                                            l10 = 1;
                                            break block76;
                                        }
                                        l10 = l8 = (long)0;
                                    }
                                    if (l8 != false) break block82;
                                    f8 = Math.min(1.0f, (float)(LyricWords.a(20394, 6128019022397942473L) + l9) / 450.0f);
                                    f7 = 0.3f * f8;
                                    f6 = 0.85f;
                                    if (n4 == 0) break block83;
                                }
                                f8 = Math.min(1.0f, (float)l9 / 120.0f);
                                f8 = 1.0f - (1.0f - f8) * (1.0f - f8) * (1.0f - f8);
                                f7 = 0.3f + 0.7f * f8;
                                f6 = 0.85f + 0.2f * f8 - 0.2f * Math.min(1.0f, (float)l9 / (float)l2);
                            }
                            n7 = Math.round(255.0f * (f7 *= Math.min(1.0f, (float)(lyricWords$Floater.start() + l2 - l) / 500.0f)));
                            if (n7 < 6) {
                                continue;
                            }
                            float f9 = 0.02f * lyricWords$Floater.size() * f6;
                            Vec3d vec3d2 = LyricWords.toWorld(lyricWords$Floater);
                            float f10 = -object2.transform((Vector3f)new Vector3f((float)((float)(vec3d2.field_1352 - vec3d.field_1352)), (float)((float)(vec3d2.field_1351 - vec3d.field_1351)), (float)((float)(vec3d2.field_1350 - vec3d.field_1350)))).z;
                            if (f10 < 0.2f) {
                                continue;
                            }
                            matrix4f = new Matrix4f((Matrix4fc)object).translate((float)(vec3d2.field_1352 - vec3d.field_1352), (float)(vec3d2.field_1351 - vec3d.field_1351), (float)(vec3d2.field_1350 - vec3d.field_1350)).rotate((Quaternionfc)camera.method_23767()).scale(f9, -f9, f9).mul((Matrix4fc)LEAN);
                            f5 = -lyricWords$Floater.w() / 2.0f;
                            f4 = -4.0f;
                            n6 = n2;
                            if (n4 != 0) break block78;
                            if (n6 == 0) break block79;
                            n6 = (int)l8;
                        }
                        if (n4 != 0) break block80;
                        if (n6 == 0) break block79;
                        long l12 = l - (lyricWords$Floater.end() + LyricWords.a(16754, 1792036087878525972L));
                        n6 = l12 == 0L ? 0 : (l12 < 0L ? -1 : 1);
                    }
                    if (n4 != 0) break block81;
                    if (n6 >= 0) break block79;
                    n6 = Math.min(255, Math.round((float)n7 * (0.3f + 0.05f * (float)n2)));
                    break block81;
                }
                n6 = 0;
            }
            int n8 = n6;
            SdfText.drawWorld((VertexConsumerProvider)immediate, matrix4f, SdfText.INTER, lyricWords$Floater.text(), f5, f4, 10.0f, n7 << 24 | n, n8 << 24 | n, (float)n2 / 10.0f);
            if (n4 == 0) continue;
        }
        immediate.method_22993();
    }

    private static void spawn(MinecraftClient minecraftClient, Camera camera, Lyrics$Word lyrics$Word, float f, float f2) {
        Vec3d vec3d;
        float f3;
        float f4;
        String string;
        block54: {
            Object object2;
            Vec3d vec3d2;
            int n;
            block43: {
                string = lyrics$Word.text().trim().toUpperCase(Locale.ROOT);
                n = EffectEngine.a;
                if (string.isEmpty()) {
                    return;
                }
                ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
                float f5 = Math.min(1.0f, (float)(lyrics$Word.end() - lyrics$Word.start()) / 700.0f);
                f4 = f * (0.7f + 1.3f * f5 * f5) * (0.85f + 0.3f * threadLocalRandom.nextFloat());
                f3 = SdfText.width(SdfText.INTER, string, 10.0f);
                double d = f3 * 0.02f * f4;
                double d2 = 0.17999999f * f4;
                Vec3d vec3d3 = camera.method_71156();
                Quaternionf quaternionf = camera.method_23767();
                Quaternionf quaternionf2 = new Quaternionf((Quaternionfc)quaternionf).conjugate();
                double d3 = Math.toRadians(((Integer)minecraftClient.field_1690.method_41808().method_41753()).intValue()) / 2.0;
                double d4 = (double)minecraftClient.method_22683().method_4489() / (double)Math.max(1, minecraftClient.method_22683().method_4506());
                double d5 = Math.atan(Math.tan(d3) * d4);
                boolean bl = minecraftClient.field_1690.method_31044().method_31034();
                Vec3d vec3d4 = minecraftClient.field_1724.method_30950(minecraftClient.method_61966().method_60637(true));
                Vector3f vector3f = quaternionf2.transform(new Vector3f((float)(vec3d4.field_1352 - vec3d3.field_1352), (float)(vec3d4.field_1351 + 1.0 - vec3d3.field_1351), (float)(vec3d4.field_1350 - vec3d3.field_1350)));
                Vec3d vec3d5 = minecraftClient.field_1724.method_18798();
                double d6 = Math.min(6.0, Math.hypot(vec3d5.field_1352, vec3d5.field_1350) * 20.0);
                Vec3d vec3d6 = d6 < 0.5 ? Vec3d.field_1353 : new Vec3d(vec3d5.field_1352, 0.0, vec3d5.field_1350).method_1029().method_1021(d6);
                Vec3d vec3d7 = vec3d6;
                double d7 = -1.0E9;
                vec3d = null;
                int n2 = 0;
                while (n2 < 2) {
                    block46: {
                        double d8;
                        block45: {
                            block44: {
                                vec3d2 = vec3d;
                                if (n != 0 || n != 0) break block43;
                                if (vec3d2 != null) break;
                                if (n2 != 0) break block44;
                                d8 = 1.0;
                                break block45;
                            }
                            d8 = 0.5;
                        }
                        double d9 = d8;
                        int n3 = 0;
                        while (n3 < 14) {
                            block48: {
                                block49: {
                                    double d10;
                                    Vec3d vec3d8;
                                    block55: {
                                        boolean bl2;
                                        Vector3f vector3f2;
                                        block52: {
                                            block53: {
                                                block50: {
                                                    block51: {
                                                        block56: {
                                                            double d11;
                                                            block47: {
                                                                if (n != 0) break block46;
                                                                if (!bl) break block47;
                                                                d11 = 4.0 + threadLocalRandom.nextDouble() * (double)f2 * d9;
                                                                if (n2 == 1) {
                                                                    d11 = 2.5 + threadLocalRandom.nextDouble() * 2.0;
                                                                }
                                                                vector3f2 = new Vector3f((float)((threadLocalRandom.nextDouble() * 2.0 - 1.0) * d11 * Math.tan(d5) * 0.8), (float)((threadLocalRandom.nextDouble() * 1.2 - 0.5) * d11 * Math.tan(d3)), (float)(-d11));
                                                                if (n == 0) break block56;
                                                            }
                                                            d11 = ((double)f2 * 0.8 + 1.5) * d9;
                                                            vector3f2 = new Vector3f((float)((double)vector3f.x + (threadLocalRandom.nextDouble() * 2.0 - 1.0) * d11), (float)((double)vector3f.y + threadLocalRandom.nextDouble() * 2.6 - 1.0), (float)((double)vector3f.z + (threadLocalRandom.nextDouble() * 2.0 - 1.0) * 2.0 * d9));
                                                        }
                                                        Vector3f vector3f3 = quaternionf.transform(new Vector3f((Vector3fc)vector3f2));
                                                        vec3d8 = vec3d3.method_1031((double)vector3f3.x + vec3d7.field_1352, (double)vector3f3.y, (double)vector3f3.z + vec3d7.field_1350);
                                                        vector3f2 = quaternionf2.transform(new Vector3f((float)(vec3d8.field_1352 - vec3d3.field_1352), (float)(vec3d8.field_1351 - vec3d3.field_1351), (float)(vec3d8.field_1350 - vec3d3.field_1350)));
                                                        double d12 = -vector3f2.z;
                                                        if (n != 0) break block48;
                                                        if (d12 < 2.5) break block49;
                                                        bl2 = LyricWords.inFrame(vector3f2, d, d2, d5, d3);
                                                        if (n != 0) break block50;
                                                        if (bl2) break block51;
                                                        break block49;
                                                    }
                                                    bl2 = LyricWords.clear(minecraftClient, vec3d3, vec3d8, quaternionf, d);
                                                }
                                                if (n != 0) break block52;
                                                if (bl2) break block53;
                                                break block49;
                                            }
                                            bl2 = live.isEmpty();
                                        }
                                        double d11 = bl2 ? 0.0 : 1.0E9;
                                        double d14 = d11;
                                        for (Object object2 : live) {
                                            if (n == 0) {
                                                LyricWords$Floater lyricWords$Floater = (LyricWords$Floater)object2;
                                                d14 = Math.min(d14, LyricWords.gap(quaternionf2, vec3d3, vector3f2, d, d2, lyricWords$Floater));
                                                if (n == 0) continue;
                                            }
                                            break block54;
                                        }
                                        d10 = d14;
                                        if (n != 0) break block55;
                                        if (!(d10 > d7)) break block49;
                                        d10 = d14;
                                    }
                                    d7 = d10;
                                    vec3d = vec3d8;
                                }
                                ++n3;
                            }
                            if (n == 0) continue;
                        }
                        ++n2;
                    }
                    if (n == 0) continue;
                }
                vec3d2 = vec3d;
            }
            if (vec3d2 == null) {
                return;
            }
            object2 = live;
            if (n != 0 || object2.size() < 60) break block54;
            object2 = live.remove(0);
        }
        Vec3d vec3d9 = LyricWords.toLocal(vec3d);
        live.add(new LyricWords$Floater(string, f3, vec3d9.field_1352, vec3d9.field_1351, vec3d9.field_1350, f4, lyrics$Word.start(), lyrics$Word.end()));
    }

    private static boolean inFrame(Vector3f vector3f, double d, double d2, double d3, double d4) {
        double d5;
        block8: {
            block7: {
                double d6;
                double d7;
                int n;
                block6: {
                    double d8 = -vector3f.z;
                    double d9 = Math.abs(Math.atan2(vector3f.x, d8)) + Math.atan(d / 2.0 / d8);
                    n = EffectEngine.a;
                    double d10 = Math.abs(Math.atan2(vector3f.y, d8)) + Math.atan(d2 / 2.0 / d8);
                    d7 = d9;
                    d6 = d3;
                    if (n != 0) break block6;
                    if (!(d7 < d6 * 0.92)) break block7;
                    d7 = d10;
                    d6 = d4 * 0.9;
                }
                double d11 = d7 - d6;
                d5 = d11 == 0.0 ? 0 : (d11 < 0.0 ? -1 : 1);
                if (n != 0) break block8;
                if (d5 >= 0) break block7;
                d5 = 1.0;
                break block8;
            }
            d5 = 0.0;
        }
        return (boolean)d5;
    }

    private static boolean clear(MinecraftClient minecraftClient, Vec3d vec3d, Vec3d vec3d2, Quaternionf quaternionf, double d) {
        boolean bl;
        block12: {
            block9: {
                Vector3f vector3f = quaternionf.transform(new Vector3f(1.0f, 0.0f, 0.0f));
                int n = EffectEngine.a;
                int n2 = -1;
                while (n2 <= 1) {
                    block10: {
                        block11: {
                            Vec3d vec3d3 = vec3d2.method_1031((double)vector3f.x * d / 2.0 * (double)n2, 0.0, (double)vector3f.z * d / 2.0 * (double)n2);
                            BlockPos blockPos = BlockPos.method_49638((Position)vec3d3);
                            if (n != 0) break block9;
                            if (n != 0) break block10;
                            if (minecraftClient.field_1687.method_8320(blockPos).method_26220((BlockView)minecraftClient.field_1687, blockPos).method_1110()) break block11;
                            return false;
                        }
                        ++n2;
                    }
                    if (n == 0) continue;
                }
                if (minecraftClient.field_1687.method_17742(new RaycastContext(vec3d, vec3d2, RaycastContext.ShapeType.field_17558, RaycastContext.FluidHandling.field_1348, (Entity)minecraftClient.field_1724)).method_17783() != HitResult.Type.field_1333) break block9;
                bl = true;
                break block12;
            }
            bl = false;
        }
        return bl;
    }

    private static double gap(Quaternionf quaternionf, Vec3d vec3d, Vector3f vector3f, double d, double d2, LyricWords$Floater lyricWords$Floater) {
        Vector3f vector3f2;
        Vector3f vector3f3;
        block4: {
            block5: {
                Vec3d vec3d2 = LyricWords.toWorld(lyricWords$Floater);
                int n = EffectEngine.a;
                vector3f3 = quaternionf.transform(new Vector3f((float)(vec3d2.field_1352 - vec3d.field_1352), (float)(vec3d2.field_1351 - vec3d.field_1351), (float)(vec3d2.field_1350 - vec3d.field_1350)));
                vector3f2 = vector3f3;
                if (n != 0) break block4;
                if (!(vector3f2.z >= 0.0f)) break block5;
                return 1.0E9;
            }
            vector3f2 = vector3f;
        }
        double d3 = -vector3f2.z;
        double d4 = -vector3f3.z;
        double d5 = lyricWords$Floater.w() * 0.02f * lyricWords$Floater.size();
        double d6 = 0.17999999f * lyricWords$Floater.size();
        double d7 = Math.abs(Math.atan2(vector3f.x, d3) - Math.atan2(vector3f3.x, d4)) - (Math.atan(d / 2.0 / d3) + Math.atan(d5 / 2.0 / d4));
        double d8 = Math.abs(Math.atan2(vector3f.y, d3) - Math.atan2(vector3f3.y, d4)) - (Math.atan(d2 / 2.0 / d3) + Math.atan(d6 / 2.0 / d4));
        return Math.max(d7, d8);
    }

    private static boolean lambda$render$0(long l, long l2, LyricWords$Floater lyricWords$Floater) {
        long l3;
        block2: {
            block3: {
                int n = EffectEngine.a;
                long l4 = l - (lyricWords$Floater.start() + l2);
                l3 = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                if (n != 0) break block2;
                if (l3 <= 0) break block3;
                l3 = 1;
                break block2;
            }
            l3 = 0;
        }
        return (boolean)l3;
    }

    
    static {
        block12: {
            block11: {
                block10: {
                    block9: {
                        var9 = 2779581679501356619L;
                        var8_1 = new long[2];
                        var12_2 = 0;
                        var13_3 = "g\u0087\u00a1\u00db\u0086\u00dd\u00aeK\u008ep\u0087o\u0086\u00dc\u00aew";
                        var14_4 = "g\u0087\u00a1\u00db\u0086\u00dd\u00aeK\u008ep\u0087o\u0086\u00dc\u00aew".length();
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
                LyricWords.MAX = (int)var8_1[1];
                var0_7 = 7274694635394150810L;
                var6_8 = new long[9];
                var3_9 = 0;
                var4_10 = "\u0015MP\u00a7X\u00a2\u00ed\u00ba|*\u007fi\u0092\u00b5\u0095t/\u00aa\u00fd\u0096\u009e\u00a1>\u00ef\u00bd;3$Bg\u00da\u00bc1\u00ff\u00f0\u00ef\u0016\u00c6\u001a\u0091)n\u0085\u00f7\u009c\u0088_\u00eah\u0005\u00ea\u0019\u001f\u00aaM\u001d";
                var5_11 = "\u0015MP\u00a7X\u00a2\u00ed\u00ba|*\u007fi\u0092\u00b5\u0095t/\u00aa\u00fd\u0096\u009e\u00a1>\u00ef\u00bd;3$Bg\u00da\u00bc1\u00ff\u00f0\u00ef\u0016\u00c6\u001a\u0091)n\u0085\u00f7\u009c\u0088_\u00eah\u0005\u00ea\u0019\u001f\u00aaM\u001d".length();
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
                    var4_10 = "\u00f9\u00ac3\u0095\u0011\u00cfZL\u001d\u00c5\u00bdN\u00e2\u008a\u00d1=";
                    var5_11 = "\u00f9\u00ac3\u0095\u0011\u00cfZL\u001d\u00c5\u00bdN\u00e2\u008a\u00d1=".length();
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
        LyricWords.a = var6_8;
        LyricWords.b = new Long[9];
        LyricWords.LEAD = LyricWords.a(2768, 5591902617867644850L);
        LyricWords.FADE = LyricWords.a(27620, 5430802032468070017L);
        LyricWords.POP = LyricWords.a(14655, 8194783981438852184L);
        LyricWords.BUFFER = new BufferAllocator((int)var8_1[0]);
        LyricWords.LEAN = new Matrix4f().translate(0.0f, 3.0f, 0.0f).mul((Matrix4fc)new Matrix4f().m10(-0.25f)).translate(0.0f, -3.0f, 0.0f);
        LyricWords.live = new ArrayList<LyricWords$Floater>();
        LyricWords.last = LyricWords.a(29117, 2751739924960220377L);
        LyricWords.anchor = Vec3d.field_1353;
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x6567) & Short.MAX_VALUE;
        if (b[n2] == null) {
            LyricWords.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
