package dev.quelchi.aurora.render;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.StormRenderer$Sample;
import dev.quelchi.aurora.render.StormRenderer$Storm;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.stream.Stream;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.AffineTransformation;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;

public final class StormRenderer {
    private static final boolean ACTIVE;
    private static final String NAMESPACE;
    private static final double HEIGHT = 350.0;
    private static final double FADE = 32.0;
    private static final double NEAR = 16.0;
    private static final double FAR = 64.0;
    private static final int ROW_INSIDE;
    private static final int ROW_OUTSIDE;
    private static final int TILES;
    private static final double PANEL = 2.0;
    private static final double MAX_PANEL = 16.0;
    private static final long SCROLL_MS;
    private static final Identifier TEXTURE;
    private static final Identifier FORCEFIELD;
    private static final BufferAllocator BUFFER;
    private static boolean textureReady;
    private static boolean textureFailed;
    private static final ArrayDeque<StormRenderer$Sample> SAMPLES;
    private static double avgGapMs;
    private static boolean tracking;
    private static final double JUMP = 8.0;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private StormRenderer() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static StormRenderer$Storm read(Entity entity, float f) {
        StormRenderer$Storm stormRenderer$Storm;
        float f2;
        DyedColorComponent dyedColorComponent;
        Vec3d vec3d;
        Vector3fc vector3fc;
        Vector3fc vector3fc2;
        block31: {
            block32: {
                Object object;
                ItemStack itemStack;
                int n;
                block29: {
                    DisplayEntity.RenderState renderState;
                    block30: {
                        String string;
                        String string2;
                        DisplayEntity.ItemDisplayEntity itemDisplayEntity;
                        block28: {
                            DisplayEntity.ItemDisplayEntity itemDisplayEntity2;
                            block26: {
                                block27: {
                                    Entity entity2;
                                    block25: {
                                        n = EffectEngine.a;
                                        entity2 = entity;
                                        if (n != 0) break block25;
                                        if (!(entity2 instanceof DisplayEntity.ItemDisplayEntity)) return null;
                                        entity2 = entity;
                                    }
                                    itemDisplayEntity = (DisplayEntity.ItemDisplayEntity)entity2;
                                    itemDisplayEntity2 = itemDisplayEntity;
                                    if (n != 0) break block26;
                                    if (itemDisplayEntity2.method_49782() != null) break block27;
                                    return null;
                                }
                                itemDisplayEntity2 = itemDisplayEntity;
                            }
                            itemStack = itemDisplayEntity2.method_49782().comp_1322();
                            Identifier identifier = (Identifier)itemStack.method_58694(DataComponentTypes.field_54199);
                            if (identifier == null) return null;
                            String string = a[1];
                            string = identifier.method_12836();
                            if (n != 0) break block28;
                            if (!string2.equals(string)) return null;
                            String string = identifier.method_12832();
                            string = a[6];
                        }
                        if (!string2.equals(string)) {
                            return null;
                        }
                        renderState = itemDisplayEntity.method_49777();
                        object = renderState;
                        if (n != 0) break block29;
                        if (object != null) break block30;
                        return null;
                    }
                    object = renderState.comp_1328().method_48888(1.0f);
                }
                AffineTransformation affineTransformation = (AffineTransformation)object;
                vector3fc2 = affineTransformation.method_35865();
                vector3fc = affineTransformation.method_35866();
                vec3d = entity.method_30950(f);
                dyedColorComponent = (DyedColorComponent)itemStack.method_58694(DataComponentTypes.field_49644);
                float f2 = vector3fc.y() - 1.1f;
                f2 = f2 == 0.0f ? 0 : (f2 > 0.0f ? 1 : -1);
                if (n != 0) break block31;
                if (f2 <= 0) break block32;
                f2 = 1.0f;
                break block31;
            }
            f2 = 0.0f;
        }
        float f4 = f2;
        StormRenderer$Storm stormRenderer$Storm3 = stormRenderer$Storm;
        stormRenderer$Storm3 = stormRenderer$Storm;
        double d5 = vec3d.field_1352 + (double)vector3fc2.x();
        d5 = vec3d.field_1351 + (double)vector3fc2.y();
        d5 = vec3d.field_1350 + (double)vector3fc2.z();
        d5 = vector3fc.z();
        d5 = f4 != false ? (double)vector3fc.y() : 350.0;
        stormRenderer$Storm2(d, d2, d3, d4, d5, (boolean)f4, dyedColorComponent == null ? StormRenderer.a(25027, 887181010978724560L) : dyedColorComponent.comp_2384());
        return stormRenderer$Storm3;
    }

    public static boolean hides(Entity entity) {
        boolean bl;
        block12: {
            block10: {
                Entity entity2;
                block11: {
                    boolean bl2;
                    block9: {
                        int n = EffectEngine.a;
                        bl2 = ACTIVE;
                        if (n != 0) break block9;
                        if (!bl2) break block10;
                        entity2 = entity;
                        if (n != 0) break block11;
                        bl2 = entity2 instanceof DisplayEntity.ItemDisplayEntity;
                    }
                    if (!bl2) break block10;
                    entity2 = entity;
                }
                if (StormRenderer.read(entity2, 1.0f) == null) break block10;
                bl = true;
                break block12;
            }
            bl = false;
        }
        return bl;
    }

    public static void onWorldRender(WorldRenderContext worldRenderContext) {
        block55: {
            float f;
            StormRenderer$Storm stormRenderer$Storm4;
            ArrayList<StormRenderer$Storm> arrayList;
            MinecraftClient minecraftClient;
            int n;
            block48: {
                Object object;
                block54: {
                    MinecraftClient minecraftClient2;
                    block47: {
                        block46: {
                            block44: {
                                boolean bl;
                                block45: {
                                    n = EffectEngine.a;
                                    if (!ACTIVE) {
                                        return;
                                    }
                                    minecraftClient = MinecraftClient.method_1551();
                                    if (minecraftClient.field_1687 == null) break block44;
                                    bl = textureReady;
                                    if (n != 0) break block45;
                                    if (bl) break block46;
                                    minecraftClient2 = minecraftClient;
                                    if (n != 0) break block47;
                                    bl = StormRenderer.buildTexture(minecraftClient2);
                                }
                                if (bl) break block46;
                            }
                            return;
                        }
                        minecraftClient2 = minecraftClient;
                    }
                    float f2 = minecraftClient2.method_61966().method_60637(false);
                    arrayList = new ArrayList<StormRenderer$Storm>();
                    block34: for (Entity entity : minecraftClient.field_1687.method_18112()) {
                        block53: {
                            double d;
                            StormRenderer$Storm stormRenderer$Storm2;
                            StormRenderer$Storm stormRenderer$Storm3;
                            block49: {
                                if (n != 0) break block48;
                                stormRenderer$Storm3 = StormRenderer.read(entity, f2);
                                stormRenderer$Storm2 = stormRenderer$Storm3;
                                if (n != 0) break block49;
                                if (stormRenderer$Storm2 == null) continue;
                                stormRenderer$Storm2 = stormRenderer$Storm3;
                            }
                            double d2 = stormRenderer$Storm2.r();
                            block35: while (true) {
                                double d3;
                                double d4 = (d3 = d2 - 0.05) == 0.0 ? 0 : (d3 < 0.0 ? -1 : 1);
                                if (n == 0) {
                                    if (d4 <= 0) continue block34;
                                    d4 = 0.0;
                                }
                                d = d4;
                                for (StormRenderer$Storm stormRenderer$Storm4 : arrayList) {
                                    block51: {
                                        double d5;
                                        block52: {
                                            double d6;
                                            double d7;
                                            block50: {
                                                d2 = Math.abs(stormRenderer$Storm4.x() - stormRenderer$Storm3.x());
                                                if (n != 0) continue block35;
                                                d7 = 0.5;
                                                if (n != 0) break block50;
                                                if (!(d2 < d7)) break block51;
                                                d5 = stormRenderer$Storm4.z();
                                                if (n != 0) break block52;
                                                d6 = Math.abs(d5 - stormRenderer$Storm3.z());
                                                d7 = 0.5;
                                            }
                                            if (!(d6 < d7)) break block51;
                                            d5 = Math.abs(stormRenderer$Storm4.r() - stormRenderer$Storm3.r());
                                        }
                                        double d8 = d5 - 0.5;
                                        double d9 = d8 == 0.0 ? 0 : (d8 < 0.0 ? -1 : 1);
                                        if (n != 0 || d9 >= 0) break block51;
                                        d9 = d = 1.0;
                                    }
                                    if (n == 0) continue;
                                }
                                break;
                            }
                            double d10 = d;
                            if (n != 0 || d10 != false) break block53;
                            d10 = (double)arrayList.add(stormRenderer$Storm3);
                        }
                        if (n == 0) continue;
                    }
                    object = arrayList;
                    if (n != 0) break block48;
                    if (!object.isEmpty()) break block54;
                    tracking = false;
                    return;
                }
                object = arrayList.set(0, StormRenderer.smooth((StormRenderer$Storm)arrayList.get(0)));
            }
            Vec3d vec3d = minecraftClient.field_1773.method_19418().method_71156();
            float f3 = f = (float)(Util.method_658() % StormRenderer.b(20664, 2179410603598982414L)) / 3000.0f;
            VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.method_22991((BufferAllocator)BUFFER);
            VertexConsumer vertexConsumer = immediate.method_73477(RenderLayers.method_76002((Identifier)TEXTURE));
            stormRenderer$Storm4 = worldRenderContext.matrices().method_23760().method_23761();
            for (StormRenderer$Storm stormRenderer$Storm5 : arrayList) {
                block57: {
                    StormRenderer$Storm stormRenderer$Storm6;
                    StormRenderer$Storm stormRenderer$Storm7;
                    VertexConsumer vertexConsumer2;
                    double d;
                    double d11;
                    block56: {
                        if (n != 0) break block55;
                        double d12 = Math.PI * 2 * stormRenderer$Storm5.r();
                        d11 = Math.max(1.0, Math.floor(d12));
                        d = d12 - d11;
                        vertexConsumer2 = vertexConsumer;
                        stormRenderer$Storm7 = stormRenderer$Storm4;
                        stormRenderer$Storm6 = stormRenderer$Storm5;
                        if (n != 0) break block56;
                        StormRenderer.wall(vertexConsumer2, (Matrix4f)stormRenderer$Storm7, stormRenderer$Storm6, vec3d, f, f3, d11, 1.0 - d);
                        if (!(d > 0.01)) break block57;
                        vertexConsumer2 = vertexConsumer;
                        stormRenderer$Storm7 = stormRenderer$Storm4;
                        stormRenderer$Storm6 = stormRenderer$Storm5;
                    }
                    StormRenderer.wall(vertexConsumer2, (Matrix4f)stormRenderer$Storm7, stormRenderer$Storm6, vec3d, f, f3, d11 + 1.0, d);
                }
                if (n == 0) continue;
            }
            immediate.method_22993();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static StormRenderer$Storm smooth(StormRenderer$Storm stormRenderer$Storm) {
        StormRenderer$Sample stormRenderer$Sample;
        StormRenderer$Sample stormRenderer$Sample2;
        StormRenderer$Sample stormRenderer$Sample3;
        long l;
        block60: {
            Stream stream;
            int n;
            block58: {
                long l2;
                block61: {
                    block57: {
                        double d;
                        block55: {
                            block56: {
                                StormRenderer$Sample stormRenderer$Sample4;
                                block53: {
                                    StormRenderer$Storm stormRenderer$Storm2;
                                    block54: {
                                        double d2;
                                        block52: {
                                            StormRenderer$Sample stormRenderer$Sample5;
                                            block50: {
                                                block51: {
                                                    StormRenderer$Storm stormRenderer$Storm3;
                                                    block48: {
                                                        block49: {
                                                            l2 = Util.method_658();
                                                            n = EffectEngine.a;
                                                            stormRenderer$Sample4 = SAMPLES.peekLast();
                                                            if (n != 0) break block48;
                                                            if (!tracking) break block49;
                                                            if (stormRenderer$Sample4 == null) break block49;
                                                            stormRenderer$Storm3 = stormRenderer$Storm;
                                                            if (n != 0) return stormRenderer$Storm3;
                                                            if (Math.abs(stormRenderer$Storm3.r() - stormRenderer$Sample4.r()) > 8.0) break block49;
                                                            stormRenderer$Storm3 = stormRenderer$Storm;
                                                            if (n != 0) return stormRenderer$Storm3;
                                                            if (Math.abs(stormRenderer$Storm3.x() - stormRenderer$Sample4.x()) > 8.0) break block49;
                                                            d2 = stormRenderer$Storm.z();
                                                            stormRenderer$Sample5 = stormRenderer$Sample4;
                                                            if (n != 0) break block50;
                                                            if (!(Math.abs(d2 - stormRenderer$Sample5.z()) > 8.0)) break block51;
                                                        }
                                                        tracking = true;
                                                        SAMPLES.clear();
                                                        SAMPLES.add(new StormRenderer$Sample(l2, stormRenderer$Storm.x(), stormRenderer$Storm.z(), stormRenderer$Storm.r()));
                                                    }
                                                    stormRenderer$Storm3 = stormRenderer$Storm;
                                                    return stormRenderer$Storm3;
                                                }
                                                d2 = stormRenderer$Storm.r();
                                                if (n != 0) break block52;
                                                stormRenderer$Sample5 = stormRenderer$Sample4;
                                            }
                                            if (d2 != stormRenderer$Sample5.r()) break block53;
                                            stormRenderer$Storm2 = stormRenderer$Storm;
                                            if (n != 0) break block54;
                                            d2 = stormRenderer$Storm2.x();
                                        }
                                        if (d2 != stormRenderer$Sample4.x()) break block53;
                                        stormRenderer$Storm2 = stormRenderer$Storm;
                                    }
                                    if (stormRenderer$Storm2.z() == stormRenderer$Sample4.z()) break block61;
                                }
                                l = l2 - stormRenderer$Sample4.at();
                                d = l;
                                if (n != 0) break block55;
                                if (!(d > avgGapMs * 3.0)) break block56;
                                SAMPLES.add(new StormRenderer$Sample(l2 - (long)avgGapMs, stormRenderer$Sample4.x(), stormRenderer$Sample4.z(), stormRenderer$Sample4.r()));
                                if (n == 0) break block57;
                            }
                            d = avgGapMs + ((double)Math.clamp(l, 50, 3000) - avgGapMs) * 0.2;
                        }
                        avgGapMs = d;
                    }
                    SAMPLES.add(new StormRenderer$Sample(l2, stormRenderer$Storm.x(), stormRenderer$Storm.z(), stormRenderer$Storm.r()));
                }
                l = l2 - (long)Math.min(avgGapMs * 1.1, 1500.0);
                while (SAMPLES.size() > 2) {
                    stream = SAMPLES.stream();
                    if (n != 0) break block58;
                    stream = stream.skip(1L);
                    if (n != 0) break block58;
                    if (((StormRenderer$Sample)stream.findFirst().get()).at() > l) break;
                    SAMPLES.pollFirst();
                    if (n == 0) continue;
                    break;
                }
                stream = SAMPLES.peekFirst();
            }
            stormRenderer$Sample3 = stream;
            stormRenderer$Sample2 = null;
            for (StormRenderer$Sample stormRenderer$Sample6 : SAMPLES) {
                block59: {
                    if (n != 0) continue;
                    if (stormRenderer$Sample6.at() <= l) break block59;
                    stormRenderer$Sample2 = stormRenderer$Sample6;
                    break;
                }
                stormRenderer$Sample3 = stormRenderer$Sample6;
            }
            stormRenderer$Sample = stormRenderer$Sample2;
            if (n != 0) break block60;
            if (stormRenderer$Sample == null) return new StormRenderer$Storm(stormRenderer$Sample3.x(), stormRenderer$Storm.y(), stormRenderer$Sample3.z(), stormRenderer$Sample3.r(), stormRenderer$Storm.height(), stormRenderer$Storm.capped(), stormRenderer$Storm.rgb());
            stormRenderer$Sample = stormRenderer$Sample2;
        }
        if (stormRenderer$Sample == stormRenderer$Sample3) {
            return new StormRenderer$Storm(stormRenderer$Sample3.x(), stormRenderer$Storm.y(), stormRenderer$Sample3.z(), stormRenderer$Sample3.r(), stormRenderer$Storm.height(), stormRenderer$Storm.capped(), stormRenderer$Storm.rgb());
        }
        double d = Math.clamp((double)(l - stormRenderer$Sample3.at()) / (double)(stormRenderer$Sample2.at() - stormRenderer$Sample3.at()), 0.0, 1.0);
        return new StormRenderer$Storm(stormRenderer$Sample3.x() + (stormRenderer$Sample2.x() - stormRenderer$Sample3.x()) * d, stormRenderer$Storm.y(), stormRenderer$Sample3.z() + (stormRenderer$Sample2.z() - stormRenderer$Sample3.z()) * d, stormRenderer$Sample3.r() + (stormRenderer$Sample2.r() - stormRenderer$Sample3.r()) * d, stormRenderer$Storm.height(), stormRenderer$Storm.capped(), stormRenderer$Storm.rgb());
    }

    private static void wall(VertexConsumer vertexConsumer, Matrix4f matrix4f, StormRenderer$Storm stormRenderer$Storm, Vec3d vec3d, float f, float f2, double d, double d2) {
        double d3;
        double d4;
        double d5;
        int n;
        double d6;
        double d7;
        int n2;
        block40: {
            block41: {
                double d8;
                double d9;
                double d10;
                block38: {
                    block39: {
                        d10 = vec3d.field_1352 - stormRenderer$Storm.x();
                        n2 = EffectEngine.a;
                        d9 = vec3d.field_1350 - stormRenderer$Storm.z();
                        double d11 = d10 * d10 + d9 * d9 - stormRenderer$Storm.r() * stormRenderer$Storm.r();
                        d8 = d11 == 0.0 ? 0 : (d11 < 0.0 ? -1 : 1);
                        if (n2 != 0) break block38;
                        if (d8 >= 0) break block39;
                        d8 = 1.0;
                        break block38;
                    }
                    d8 = 0.0;
                }
                d7 = d8;
                d6 = stormRenderer$Storm.height();
                double d12 = Math.abs(Math.sqrt(d10 * d10 + d9 * d9) - stormRenderer$Storm.r());
                double d13 = d7 != false ? 2.0 : Math.clamp(d12 / 8.0, 2.0, 16.0);
                double d14 = d13;
                n = Math.clamp((long)(Integer.highestOneBit((int)Math.ceil(Math.PI * 2 * stormRenderer$Storm.r() / d14)) * 2), 32, 2048);
                d5 = Math.PI * 2 / (double)n;
                d4 = d / (double)n;
                d3 = d7;
                if (n2 != 0) break block40;
                if (d3 == false) break block41;
                d3 = 4;
                break block40;
            }
            d3 = 16;
        }
        double d15 = d3;
        int n3 = stormRenderer$Storm.rgb() >> 16 & 0xFF;
        int n4 = stormRenderer$Storm.rgb() >> 8 & 0xFF;
        int n5 = stormRenderer$Storm.rgb() & 0xFF;
        block34: for (int i = 0; i < n; ++i) {
            double d16 = (double)i * d5;
            double d17 = d16 + d5;
            double d18 = stormRenderer$Storm.x() + Math.cos(d16) * stormRenderer$Storm.r();
            double d19 = stormRenderer$Storm.z() + Math.sin(d16) * stormRenderer$Storm.r();
            double d20 = stormRenderer$Storm.x() + Math.cos(d17) * stormRenderer$Storm.r();
            double d21 = stormRenderer$Storm.z();
            double d22 = Math.sin(d17) * stormRenderer$Storm.r();
            block35: while (true) {
                double d23;
                double d24;
                double d25;
                block43: {
                    d25 = d21 + d22;
                    double d26 = d7;
                    if (n2 == 0) {
                        block42: {
                            if (d26 == false) break block42;
                            d24 = StormRenderer.horiz(vec3d, d18, d19);
                            d23 = 66.0;
                            if (n2 != 0) break block43;
                            if (!(d24 > d23)) break block42;
                            d24 = StormRenderer.horiz(vec3d, d20, d25);
                            d23 = 66.0;
                            if (n2 != 0) break block43;
                            if (!(d24 > d23)) break block42;
                            if (n2 == 0) continue block34;
                        }
                        d26 = i;
                    }
                    d24 = d26;
                    d23 = d4;
                }
                double d27 = d24 * d23;
                double d28 = d27 - Math.floor(d27);
                float f3 = (float)(((double)f + d28) / 20.0);
                float f4 = (float)(((double)f + d28 + d4) / 20.0);
                double d29 = 0.0;
                while (d29 < d6) {
                    block46: {
                        double d30;
                        block47: {
                            float f5;
                            float f6;
                            float f7;
                            float f8;
                            float f9;
                            double d31;
                            block49: {
                                block50: {
                                    StormRenderer$Storm stormRenderer$Storm2;
                                    block48: {
                                        block45: {
                                            double d32;
                                            block44: {
                                                d21 = d6;
                                                d22 = d29 + (double)d15;
                                                if (n2 != 0) continue block35;
                                                d31 = Math.min(d21, d22);
                                                d32 = d7;
                                                if (n2 != 0) break block44;
                                                if (d32 == false) break block45;
                                                d30 = stormRenderer$Storm.y();
                                                if (n2 != 0) break block46;
                                                double d33 = d30 + d31 - (vec3d.field_1351 - 64.0);
                                                d32 = d33 == 0.0 ? 0 : (d33 < 0.0 ? -1 : 1);
                                            }
                                            if (d32 < 0) break block47;
                                            stormRenderer$Storm2 = stormRenderer$Storm;
                                            if (n2 != 0) break block48;
                                            if (!(stormRenderer$Storm2.y() + d29 > vec3d.field_1351 + 64.0)) break block45;
                                            break block47;
                                        }
                                        stormRenderer$Storm2 = stormRenderer$Storm;
                                    }
                                    f9 = StormRenderer.alpha(stormRenderer$Storm2, vec3d, d18, stormRenderer$Storm.y() + d29, d19, d29, d6, (boolean)d7) * (float)d2;
                                    f8 = StormRenderer.alpha(stormRenderer$Storm, vec3d, d20, stormRenderer$Storm.y() + d29, d25, d29, d6, (boolean)d7) * (float)d2;
                                    f7 = StormRenderer.alpha(stormRenderer$Storm, vec3d, d20, stormRenderer$Storm.y() + d31, d25, d31, d6, (boolean)d7) * (float)d2;
                                    f6 = StormRenderer.alpha(stormRenderer$Storm, vec3d, d18, stormRenderer$Storm.y() + d31, d19, d31, d6, (boolean)d7) * (float)d2;
                                    f5 = f9 + f8 + f7;
                                    if (n2 != 0) break block49;
                                    if (!(f5 + f6 <= 0.0f)) break block50;
                                    break block47;
                                }
                                f5 = (float)(((double)f2 + (d31 - d29)) / 20.0);
                            }
                            float f10 = f5;
                            float f11 = f2 / 20.0f;
                            StormRenderer.quad(vertexConsumer, matrix4f, vec3d, d18, stormRenderer$Storm.y() + d29, d19, f3, f10, f9, d20, stormRenderer$Storm.y() + d29, d25, f4, f10, f8, d20, stormRenderer$Storm.y() + d31, d25, f4, f11, f7, d18, stormRenderer$Storm.y() + d31, d19, f3, f11, f6, n3, n4, n5);
                        }
                        d30 = d29 = d29 + (double)d15;
                    }
                    if (n2 == 0) continue;
                }
                break;
            }
            if (n2 == 0) continue;
        }
    }

    private static float alpha(StormRenderer$Storm stormRenderer$Storm, Vec3d vec3d, double d, double d2, double d3, double d4, double d5, boolean bl) {
        double d6;
        double d7 = d6 = stormRenderer$Storm.capped() ? 1.0 : Math.clamp((d5 - d4) / 32.0, 0.0, 1.0);
        if (bl) {
            double d8 = d - vec3d.field_1352;
            double d9 = d2 - vec3d.field_1351;
            double d10 = d3 - vec3d.field_1350;
            double d11 = Math.sqrt(d8 * d8 + d9 * d9 + d10 * d10);
            double d12 = Math.clamp((d11 - 16.0) / 48.0, 0.0, 1.0);
            d6 *= 1.0 - d12 * d12 * (3.0 - 2.0 * d12);
        }
        return (float)d6;
    }

    private static double horiz(Vec3d vec3d, double d, double d2) {
        return Math.hypot(d - vec3d.field_1352, d2 - vec3d.field_1350);
    }

    private static void quad(VertexConsumer vertexConsumer, Matrix4f matrix4f, Vec3d vec3d, double d, double d2, double d3, float f, float f2, float f3, double d4, double d5, double d6, float f4, float f5, float f6, double d7, double d8, double d9, float f7, float f8, float f9, double d10, double d11, double d12, float f10, float f11, float f12, int n, int n2, int n3) {
        StormRenderer.vertex(vertexConsumer, matrix4f, vec3d, d, d2, d3, f, f2, f3, n, n2, n3);
        StormRenderer.vertex(vertexConsumer, matrix4f, vec3d, d4, d5, d6, f4, f5, f6, n, n2, n3);
        StormRenderer.vertex(vertexConsumer, matrix4f, vec3d, d7, d8, d9, f7, f8, f9, n, n2, n3);
        StormRenderer.vertex(vertexConsumer, matrix4f, vec3d, d10, d11, d12, f10, f11, f12, n, n2, n3);
        StormRenderer.vertex(vertexConsumer, matrix4f, vec3d, d10, d11, d12, f10, f11, f12, n, n2, n3);
        StormRenderer.vertex(vertexConsumer, matrix4f, vec3d, d7, d8, d9, f7, f8, f9, n, n2, n3);
        StormRenderer.vertex(vertexConsumer, matrix4f, vec3d, d4, d5, d6, f4, f5, f6, n, n2, n3);
        StormRenderer.vertex(vertexConsumer, matrix4f, vec3d, d, d2, d3, f, f2, f3, n, n2, n3);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, Vec3d vec3d, double d, double d2, double d3, float f, float f2, float f3, int n, int n2, int n3) {
        vertexConsumer.method_22918((Matrix4fc)matrix4f, (float)(d - vec3d.field_1352), (float)(d2 - vec3d.field_1351), (float)(d3 - vec3d.field_1350)).method_1336(n, n2, n3, Math.round(f3 * 255.0f)).method_22913(f, f2).method_22922(OverlayTexture.field_21444).method_60803(StormRenderer.a(8515, 1244309873250671188L)).method_22914(0.0f, 1.0f, 0.0f);
    }

    private static boolean buildTexture(MinecraftClient minecraftClient) {
        block39: {
            int n;
            block31: {
                boolean bl;
                block30: {
                    n = EffectEngine.a;
                    bl = textureFailed;
                    if (n != 0) break block30;
                    if (!bl) break block31;
                    bl = false;
                }
                return bl;
            }
            try {
                InputStream inputStream;
                block38: {
                    InputStream inputStream2;
                    block36: {
                        inputStream2 = minecraftClient.method_1478().getResourceOrThrow(FORCEFIELD).method_14482();
                        try {
                            NativeImage nativeImage;
                            block32: {
                                nativeImage = NativeImage.method_4309((InputStream)inputStream2);
                                try {
                                    int n2 = nativeImage.method_4307();
                                    int n3 = nativeImage.method_4323();
                                    NativeImage nativeImage2 = new NativeImage(n2 * 20, n3 * 20, false);
                                    int n4 = 0;
                                    while (n4 < 20) {
                                        block33: {
                                            if (n != 0) break block32;
                                            int n5 = 0;
                                            while (n5 < 20) {
                                                block34: {
                                                    if (n != 0) break block33;
                                                    int n6 = 0;
                                                    while (n6 < n3) {
                                                        block35: {
                                                            if (n != 0) break block34;
                                                            for (int i = 0; i < n2; ++i) {
                                                                nativeImage2.method_61941(n5 * n2 + i, n4 * n3 + n6, nativeImage.method_61940(i, n6));
                                                                if (n == 0) {
                                                                    if (n == 0) continue;
                                                                    break;
                                                                }
                                                                break block35;
                                                            }
                                                            ++n6;
                                                        }
                                                        if (n == 0) continue;
                                                    }
                                                    ++n5;
                                                }
                                                if (n == 0) continue;
                                            }
                                            ++n4;
                                        }
                                        if (n == 0) continue;
                                    }
                                    minecraftClient.method_1531().method_4616(TEXTURE, (AbstractTexture)new NativeImageBackedTexture(StormRenderer::lambda$buildTexture$0, nativeImage2));
                                    if (n != 0) break block36;
                                    textureReady = true;
                                }
                                catch (Throwable throwable) {
                                    block37: {
                                        if (n != 0 || nativeImage == null) break block37;
                                        try {
                                            nativeImage.close();
                                        }
                                        catch (Throwable throwable2) {
                                            throwable.addSuppressed(throwable2);
                                        }
                                    }
                                    throw throwable;
                                }
                            }
                            if (nativeImage != null) {
                                nativeImage.close();
                            }
                        }
                        catch (Throwable throwable) {
                            block40: {
                                if (n != 0 || inputStream2 == null) break block40;
                                try {
                                    inputStream2.close();
                                }
                                catch (Throwable throwable3) {
                                    throwable.addSuppressed(throwable3);
                                }
                            }
                            throw throwable;
                        }
                    }
                    inputStream = inputStream2;
                    if (n != 0) break block38;
                    if (inputStream == null) break block39;
                    inputStream = inputStream2;
                }
                inputStream.close();
            }
            catch (Exception exception) {
                textureFailed = true;
                Aurora.LOG.warn(a[3], (Throwable)exception);
            }
        }
        return textureReady;
    }

    private static String lambda$buildTexture$0() {
        return a[0];
    }

    
    static {
        block32: {
            block31: {
                block30: {
                    block29: {
                        block28: {
                            block27: {
                                var21 = new String[9];
                                var19_1 = 0;
                                var18_2 = "\u001a\u001dL\u001d\u007fl\u001f\b\u001cQ\u0000`-Y\u0012\rR\u0016\t\u0017\u0007Z\u0017~yP\u0015\r\u0006\u001a\u001dL\u001d\u007fl)\b\u001cQ\u0000`-Y\u0012\rR\u0016-yZ\u0003\u001cK\u0000h-Y\u001a\u0001R\u0017i!\u001f\u0015\u0007\u001e\u0001ybM\u0016HI\u0013aa\t\u0017\u0007Z\u0017~yP\u0015\r\u000b\b\u001cQ\u0000`RY\u0012\rR\u0016\u0006\u0019\u0007L\u0016h\u007f";
                                var20_3 = "\u001a\u001dL\u001d\u007fl\u001f\b\u001cQ\u0000`-Y\u0012\rR\u0016\t\u0017\u0007Z\u0017~yP\u0015\r\u0006\u001a\u001dL\u001d\u007fl)\b\u001cQ\u0000`-Y\u0012\rR\u0016-yZ\u0003\u001cK\u0000h-Y\u001a\u0001R\u0017i!\u001f\u0015\u0007\u001e\u0001ybM\u0016HI\u0013aa\t\u0017\u0007Z\u0017~yP\u0015\r\u000b\b\u001cQ\u0000`RY\u0012\rR\u0016\u0006\u0019\u0007L\u0016h\u007f".length();
                                var17_4 = 18;
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
                                    var18_2 = "\u000f\rF\u0006x\u007fZ\bGS\u001b~n\u0010\u001d\u0007L\u0011hkV\u001e\u0004Z\\}cX\t\r\u001dR\u0019lcR\u0014\f";
                                    var20_3 = "\u000f\rF\u0006x\u007fZ\bGS\u001b~n\u0010\u001d\u0007L\u0011hkV\u001e\u0004Z\\}cX\t\r\u001dR\u0019lcR\u0014\f".length();
                                    var17_4 = 28;
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
                            var22_7 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_7;
                                v9 = v6[v8];
                                switch (var22_7 % 7) {
                                    case 0: {
                                        v10 = 123;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 104;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 62;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 114;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 13;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 13;
                                        break;
                                    }
                                    default: {
                                        v10 = 63;
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
                        StormRenderer.a = var21;
                        StormRenderer.NAMESPACE = StormRenderer.a[4];
                        var8_8 = 465627011765269139L;
                        var14_9 = new long[6];
                        var11_10 = 0;
                        var12_11 = "v\u00d3\u00c0\u00ae\u00cb\u001a\u001a\u0085\u00e6N\u00e5\u00af\b<W\u00bc\u00d4Xb9\u008a\u00c2\u00d5\u00c7\u0097Y_IS)\u00ee\u00ec";
                        var13_12 = "v\u00d3\u00c0\u00ae\u00cb\u001a\u001a\u0085\u00e6N\u00e5\u00af\b<W\u00bc\u00d4Xb9\u008a\u00c2\u00d5\u00c7\u0097Y_IS)\u00ee\u00ec".length();
                        var10_13 = 0;
                        while (true) {
                            var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                            v11 = var14_9;
                            v12 = var11_10++;
                            v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                            v14 = -1;
                            break block29;
                            break;
                        }
lbl91:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            var12_11 = "\nP\u00f1lC\u00cb\u0015\rkP\u00f4ikvh7";
                            var13_12 = "\nP\u00f1lC\u00cb\u0015\rkP\u00f4ikvh7".length();
                            var10_13 = 0;
                            while (true) {
                                var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                                v11 = var14_9;
                                v12 = var11_10++;
                                v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                                v14 = 0;
                                break block29;
                                break;
                            }
                            break;
                        }
lbl104:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            break block30;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_8;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl115:

                        continue;
                    }
                }
                StormRenderer.b = var14_9;
                StormRenderer.c = new Integer[6];
                StormRenderer.ROW_INSIDE = StormRenderer.a(7232, 7837377875519645520L);
                StormRenderer.TILES = StormRenderer.a(10106, 5452198441812860011L);
                StormRenderer.ROW_OUTSIDE = StormRenderer.a(23704, 4993436264898455438L);
                var0_15 = 6839331500029417173L;
                var6_16 = new long[2];
                var3_17 = 0;
                var4_18 = "@\u00d4\u00fd\u00a6\u00cfX0cG\u00cd\u00bd\u00ff\u009b\bv\u0007";
                var5_19 = "@\u00d4\u00fd\u00a6\u00cfX0cG\u00cd\u00bd\u00ff\u009b\bv\u0007".length();
                var2_20 = 0;
                while (true) {
                    break block31;
                    break;
                }
lbl130:

                while (true) {
                    var6_16[v16] = (((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L) ^ var0_15;
                    if (var2_20 < var5_19) continue;
                    break block32;
                    break;
                }
            }
            var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
            v16 = var3_17++;
            while (true)
        }
        StormRenderer.d = var6_16;
        StormRenderer.e = new Long[2];
        StormRenderer.SCROLL_MS = StormRenderer.b(5853, 1812578164848520042L);
        var16_6 = StormRenderer.a;
        StormRenderer.ACTIVE = FabricLoader.getInstance().isModLoaded(var16_6[8]);
        StormRenderer.TEXTURE = Identifier.method_60655((String)var16_6[2], (String)var16_6[5]);
        StormRenderer.FORCEFIELD = Identifier.method_60656((String)var16_6[7]);
        StormRenderer.BUFFER = new BufferAllocator(StormRenderer.a(21252, 3584194726134442006L));
        StormRenderer.SAMPLES = new ArrayDeque<E>();
        StormRenderer.avgGapMs = 1000.0;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3B12;
        if (c[n2] == null) {
            StormRenderer.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x7DB6) & Short.MAX_VALUE;
        if (e[n2] == null) {
            StormRenderer.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
