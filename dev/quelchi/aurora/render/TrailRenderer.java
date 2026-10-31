package dev.quelchi.aurora.render;

import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.content.Trails;
import dev.quelchi.aurora.cosmetics.content.Trails$Origin;
import dev.quelchi.aurora.cosmetics.content.Trails$Trail;
import dev.quelchi.aurora.gui.PreviewScene$Geometry;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.LocalSpeckSink;
import dev.quelchi.aurora.render.PlayerSpace;
import dev.quelchi.aurora.render.TrailRenderer$1;
import dev.quelchi.aurora.render.TrailRenderer$Path;
import dev.quelchi.aurora.render.TrailRenderer$Pt;
import dev.quelchi.aurora.render.TrailRenderer$Strand;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public final class TrailRenderer {
    private static final BufferAllocator BUFFER;
    private static final Map<TrailRenderer$Strand, Deque<TrailRenderer$Pt>> TRAILS;
    private static final Map<TrailRenderer$Strand, Integer> NEXT_SEQ;
    private static final int[] BODY;
    private static final int[] WINGS;
    private static final double MIN_STEP = 0.05;
    private static final double TELEPORT = 8.0;
    private static WeakReference<ClientWorld> lastWorld;
    private static final double BODY_LIFT = 0.35;
    private static final int SUB;
    private static final float TIP_X = 23.45f;
    private static final float TIP_Y = 10.0f;
    private static final float TIP_Z = 9.78f;
    private static final TrailRenderer$Path EMPTY_PATH;
    private static TrailRenderer$Pt[] scratchBuf;
    private static final float PREVIEW_REACH = 2.6f;
    private static final float PREVIEW_MIN_WIDTH = 0.075f;
    private static final float PREVIEW_SPEED = 1.7f;
    private static final float NECK_LOCAL = 1.501f;
    private static final long[] a;
    private static final Integer[] b;

    private TrailRenderer() {
    }

    private static long lifeMs(Trails$Trail trails$Trail) {
        return Math.round((double)trails$Trail.length() * 50.0);
    }

    private static int[] sidesFor(AbstractClientPlayerEntity abstractClientPlayerEntity, Trails$Trail trails$Trail) {
        int[] nArray;
        block9: {
            block8: {
                Trails$Trail trails$Trail2;
                block7: {
                    int n = EffectEngine.a;
                    if (abstractClientPlayerEntity.method_6128()) {
                        return WINGS;
                    }
                    trails$Trail2 = trails$Trail;
                    if (n != 0) break block7;
                    if (trails$Trail2 == null) break block8;
                    trails$Trail2 = trails$Trail;
                }
                if (trails$Trail2.origin() != Trails$Origin.HANDS) break block8;
                nArray = WINGS;
                break block9;
            }
            nArray = BODY;
        }
        return nArray;
    }

    
    private static Vec3d emitPoint(AbstractClientPlayerEntity var0, int var1_1, float var2_2, Trails$Trail var3_3) {
        var4_4 = EffectEngine.a;
        v0 = var3_3;
        if (var4_4 != 0) // goto
        if (v0 == null) {
            v1 = Trails$Origin.BODY;
        } else {
            v0 = var3_3;
lbl8:

            v1 = v0.origin();
        }
        var5_5 = v1;
        v2 = var1_1;
        if (var4_4 == 0) {
            if (v2 != 0) {
                if (var5_5 != Trails$Origin.HANDS) return PlayerSpace.fromModel(var0, var2_2, (float)var1_1 * 23.45f, 10.0f, 9.78f);
                return PlayerSpace.fromModel(var0, var2_2, (float)var1_1 * 5.0f, 10.0f, 0.0f);
            }
            v2 = TrailRenderer$1.$SwitchMap$dev$quelchi$aurora$cosmetics$content$Trails$Origin[var5_5.ordinal()];
        }
        switch (v2) {
            case 1: {
                v3 = var0.method_30950(var2_2);
                return v3;
            }
            case 2: {
                v3 = PlayerSpace.fromModel(var0, var2_2, 0.0f, -4.0f, 0.0f);
                return v3;
            }
            case 3: {
                v3 = PlayerSpace.fromModel(var0, var2_2, 0.0f, 2.0f, 4.0f);
                return v3;
            }
        }
        v3 = var0.method_30950(var2_2).method_1031(0.0, 0.35, 0.0);
        return v3;
    }

    public static void tick() {
        ClientWorld clientWorld;
        int n;
        ClientWorld clientWorld2;
        MinecraftClient minecraftClient;
        block33: {
            block34: {
                minecraftClient = MinecraftClient.method_1551();
                clientWorld2 = minecraftClient.field_1687;
                n = EffectEngine.a;
                clientWorld = clientWorld2;
                if (n != 0) break block33;
                if (clientWorld == lastWorld.get()) break block34;
                TRAILS.clear();
                NEXT_SEQ.clear();
                lastWorld = new WeakReference<ClientWorld>(clientWorld2);
            }
            clientWorld = clientWorld2;
        }
        if (clientWorld == null) {
            return;
        }
        HashSet<UUID> hashSet = new HashSet<UUID>();
        Iterator iterator = clientWorld2.method_18456().iterator();
        block28: while (true) {
            if (!iterator.hasNext()) break;
            Object object = iterator.next();
            block29: while (true) {
                AbstractClientPlayerEntity abstractClientPlayerEntity;
                AbstractClientPlayerEntity abstractClientPlayerEntity2;
                Trails$Trail trails$Trail;
                block35: {
                    block36: {
                        if ((trails$Trail = TrailRenderer.trailOf(abstractClientPlayerEntity2 = (AbstractClientPlayerEntity)object)) == null) continue block28;
                        abstractClientPlayerEntity = abstractClientPlayerEntity2;
                        if (n != 0) break block35;
                        if (abstractClientPlayerEntity != minecraftClient.field_1724) break block36;
                        if (minecraftClient.field_1690.method_31044().method_31034()) {
                            continue block28;
                        }
                    }
                    abstractClientPlayerEntity = abstractClientPlayerEntity2;
                }
                UUID uUID = abstractClientPlayerEntity.method_5667();
                hashSet.add(uUID);
                for (int n2 : TrailRenderer.sidesFor(abstractClientPlayerEntity2, trails$Trail)) {
                    Object object2;
                    Object object3;
                    Deque deque;
                    block39: {
                        TrailRenderer$Strand trailRenderer$Strand;
                        block40: {
                            block37: {
                                block38: {
                                    trailRenderer$Strand = new TrailRenderer$Strand(uUID, n2);
                                    deque = TRAILS.computeIfAbsent(trailRenderer$Strand, TrailRenderer::lambda$tick$0);
                                    object = TrailRenderer.emitPoint(abstractClientPlayerEntity2, n2, 1.0f, trails$Trail);
                                    if (n != 0) continue block29;
                                    object3 = object;
                                    object2 = deque;
                                    if (n != 0) break block37;
                                    if (object2.isEmpty()) break block38;
                                    object2 = deque;
                                    if (n != 0) break block37;
                                    if (!(((TrailRenderer$Pt)object2.peekLast()).pos().method_1025(object3) > 64.0)) break block38;
                                    deque.clear();
                                }
                                object2 = deque;
                            }
                            if (n != 0) break block39;
                            if (object2.isEmpty()) break block40;
                            object2 = (TrailRenderer$Pt)deque.peekLast();
                            if (n != 0) break block39;
                            if (!(((TrailRenderer$Pt)object2).pos().method_1025(object3) > 0.0025000000000000005)) continue;
                        }
                        object2 = NEXT_SEQ.merge(trailRenderer$Strand, 1, Integer::sum);
                    }
                    int n3 = (Integer)object2;
                    deque.addLast(new TrailRenderer$Pt((Vec3d)object3, n3, System.currentTimeMillis()));
                    if (n == 0) continue;
                }
                break;
            }
            if (n != 0) break;
        }
        long l = System.currentTimeMillis();
        TRAILS.entrySet().removeIf(arg_0 -> TrailRenderer.lambda$tick$1(clientWorld2, hashSet, l, arg_0));
        NEXT_SEQ.keySet().retainAll(TRAILS.keySet());
    }

    public static void clear() {
        TRAILS.clear();
        NEXT_SEQ.clear();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Trails$Trail trailOf(AbstractClientPlayerEntity abstractClientPlayerEntity) {
        AbstractClientPlayerEntity abstractClientPlayerEntity2;
        MinecraftClient minecraftClient;
        block9: {
            block10: {
                int n;
                block8: {
                    minecraftClient = MinecraftClient.method_1551();
                    n = EffectEngine.a;
                    abstractClientPlayerEntity2 = abstractClientPlayerEntity;
                    if (n != 0) break block8;
                    if (abstractClientPlayerEntity2 == null) return null;
                    abstractClientPlayerEntity2 = abstractClientPlayerEntity;
                }
                if (n != 0) break block9;
                if (!abstractClientPlayerEntity2.method_7325()) break block10;
                return null;
            }
            abstractClientPlayerEntity2 = abstractClientPlayerEntity;
        }
        UUID uUID = abstractClientPlayerEntity2.method_5667();
        boolean bl = abstractClientPlayerEntity == minecraftClient.field_1724;
        return Trails.byId(Cosmetics.trailFor(uUID, bl));
    }

    public static void onWorldRender(WorldRenderContext worldRenderContext) {
        block33: {
            MinecraftClient minecraftClient;
            ClientWorld clientWorld;
            MinecraftClient minecraftClient2;
            int n;
            block31: {
                block32: {
                    block30: {
                        n = EffectEngine.a;
                        if (TRAILS.isEmpty()) {
                            return;
                        }
                        minecraftClient2 = MinecraftClient.method_1551();
                        clientWorld = minecraftClient2.field_1687;
                        if (clientWorld == null) break block30;
                        minecraftClient = minecraftClient2;
                        if (n != 0) break block31;
                        if (minecraftClient.field_1773 != null) break block32;
                    }
                    return;
                }
                minecraftClient = minecraftClient2;
            }
            Camera camera = minecraftClient.field_1773.method_19418();
            Vec3d vec3d = camera.method_71156();
            float f = minecraftClient2.method_61966().method_60637(false);
            float f2 = ((float)clientWorld.method_75260() + f) / 20.0f;
            VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.method_22991((BufferAllocator)BUFFER);
            VertexConsumer vertexConsumer = immediate.method_73477(RenderLayers.method_76023());
            Matrix4f matrix4f = worldRenderContext.matrices().method_23760().method_23761();
            boolean bl = false;
            Iterator<Map.Entry<TrailRenderer$Strand, Deque<TrailRenderer$Pt>>> iterator = TRAILS.entrySet().iterator();
            block22: while (true) {
                int n2 = iterator.hasNext();
                block23: while (n2 != 0) {
                    boolean bl2;
                    float f3;
                    int n3;
                    int n4;
                    int n5;
                    int n6;
                    float f4;
                    Vec3d vec3d2;
                    Vec3d vec3d3;
                    Trails$Trail trails$Trail;
                    Object object;
                    Matrix4f matrix4f2;
                    VertexConsumer vertexConsumer2;
                    boolean bl3;
                    block38: {
                        block39: {
                            int n7;
                            int n8;
                            int n9;
                            Object object2;
                            Trails$Trail trails$Trail2;
                            TrailRenderer$Strand trailRenderer$Strand;
                            block36: {
                                PlayerEntity playerEntity;
                                Map.Entry<TrailRenderer$Strand, Deque<TrailRenderer$Pt>> entry;
                                block34: {
                                    entry = iterator.next();
                                    if (n != 0) break block33;
                                    trailRenderer$Strand = entry.getKey();
                                    PlayerEntity playerEntity2 = clientWorld.method_18470(trailRenderer$Strand.player());
                                    playerEntity = playerEntity2;
                                    if (n != 0) break block34;
                                    if (!(playerEntity instanceof AbstractClientPlayerEntity)) continue block22;
                                    playerEntity = playerEntity2;
                                }
                                AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)playerEntity;
                                trails$Trail2 = TrailRenderer.trailOf(abstractClientPlayerEntity);
                                if (trails$Trail2 == null) {
                                    continue block22;
                                }
                                Vec3d vec3d4 = null;
                                object2 = TrailRenderer.sidesFor(abstractClientPlayerEntity, trails$Trail2);
                                int n10 = ((int[])object2).length;
                                n9 = 0;
                                while (n9 < n10) {
                                    block35: {
                                        n8 = object2[n9];
                                        if (n != 0) break block35;
                                        n2 = n8;
                                        if (n != 0) continue block23;
                                        if (n2 == trailRenderer$Strand.side()) {
                                            vec3d4 = TrailRenderer.emitPoint(abstractClientPlayerEntity, n8, f, trails$Trail2);
                                        }
                                        ++n9;
                                    }
                                    if (n == 0) continue;
                                }
                                object2 = TrailRenderer.smooth(entry.getValue(), vec3d4, TrailRenderer.lifeMs(trails$Trail2));
                                n7 = ((TrailRenderer$Path)object2).pts().length;
                                if (n != 0) break block36;
                                if (n7 < 2) {
                                    continue block22;
                                }
                                n7 = trails$Trail2.width();
                            }
                            float f5 = (float)n7 / 100.0f;
                            n9 = trails$Trail2.argb();
                            n8 = n9 >>> 24 & 0xFF;
                            int n11 = n9 >>> 16 & 0xFF;
                            int n12 = n9 >>> 8 & 0xFF;
                            int n13 = n9 & 0xFF;
                            bl3 = bl;
                            vertexConsumer2 = vertexConsumer;
                            matrix4f2 = matrix4f;
                            object = object2;
                            trails$Trail = trails$Trail2;
                            Vec3d vec3d2 = vec3d;
                            vec3d2 = vec3d;
                            f4 = f5;
                            int n3 = n11;
                            n3 = n12;
                            n3 = n13;
                            n3 = n8;
                            f3 = f2;
                            bl2 = trailRenderer$Strand.side();
                            if (n != 0) break block38;
                            if (bl2) break block39;
                            bl2 = true;
                            break block38;
                        }
                        bl2 = false;
                    }
                    bl = bl3 | TrailRenderer.drawTrail(vertexConsumer2, matrix4f2, (TrailRenderer$Path)object, trails$Trail, vec3d3, vec3d2, f4, n6, n5, n4, n3, f3, bl2);
                    if (n != 0) break block22;
                    continue block22;
                }
                break;
            }
            if (bl) {
                immediate.method_22993();
            }
        }
    }

    private static TrailRenderer$Path smooth(Deque<TrailRenderer$Pt> deque, Vec3d vec3d, long l) {
        int n;
        int n2;
        float[] fArray;
        float[] fArray2;
        Vec3d[] vec3dArray;
        int n3;
        block43: {
            int n4;
            long l2;
            int n5;
            int n6;
            TrailRenderer$Pt[] trailRenderer$PtArray;
            block41: {
                block42: {
                    block36: {
                        int n7;
                        TrailRenderer$Pt[] trailRenderer$PtArray2;
                        block39: {
                            block40: {
                                int n8;
                                block37: {
                                    block38: {
                                        block35: {
                                            trailRenderer$PtArray = TrailRenderer.scratch(deque.size() + 1);
                                            n6 = 0;
                                            Iterator<TrailRenderer$Pt> iterator = deque.iterator();
                                            n5 = EffectEngine.a;
                                            while (iterator.hasNext()) {
                                                TrailRenderer$Pt trailRenderer$Pt = iterator.next();
                                                trailRenderer$PtArray[n6++] = trailRenderer$Pt;
                                                if (n5 == 0) continue;
                                            }
                                            l2 = System.currentTimeMillis();
                                            if (vec3d != null) break block35;
                                            break block36;
                                        }
                                        n8 = n6;
                                        if (n5 != 0) break block37;
                                        if (n8 != 0) break block38;
                                        trailRenderer$PtArray[n6++] = new TrailRenderer$Pt(vec3d, 0, l2);
                                        if (n5 == 0) break block36;
                                    }
                                    trailRenderer$PtArray2 = trailRenderer$PtArray;
                                    n7 = n6 - 1;
                                    if (n5 != 0) break block39;
                                    double d = trailRenderer$PtArray2[n7].pos().method_1025(vec3d) - 0.0025000000000000005;
                                    n8 = d == 0.0 ? 0 : (d < 0.0 ? -1 : 1);
                                }
                                if (n8 >= 0) break block40;
                                trailRenderer$PtArray[n6 - 1] = new TrailRenderer$Pt(vec3d, trailRenderer$PtArray[n6 - 1].seq(), l2);
                                if (n5 == 0) break block36;
                            }
                            trailRenderer$PtArray2 = trailRenderer$PtArray;
                            n7 = n6 - 1;
                        }
                        int n9 = trailRenderer$PtArray2[n7].seq();
                        trailRenderer$PtArray[n6++] = new TrailRenderer$Pt(vec3d, n9 + 1, l2);
                    }
                    n4 = n6;
                    if (n5 != 0) break block41;
                    if (n4 >= 2) break block42;
                    return EMPTY_PATH;
                }
                n4 = n6;
            }
            if (n4 == 2) {
                return new TrailRenderer$Path(new Vec3d[]{trailRenderer$PtArray[0].pos(), trailRenderer$PtArray[1].pos()}, new float[]{trailRenderer$PtArray[0].seq(), trailRenderer$PtArray[1].seq()}, new float[]{TrailRenderer.fadeOf(trailRenderer$PtArray[0], l2, l), TrailRenderer.fadeOf(trailRenderer$PtArray[1], l2, l)});
            }
            double d = 0.0064;
            n3 = (n6 - 1) * 4 + 1;
            vec3dArray = new Vec3d[n3];
            fArray2 = new float[n3];
            fArray = new float[n3];
            n2 = 0;
            int n10 = 0;
            while (n10 < n6 - 1) {
                block47: {
                    block46: {
                        float f;
                        float f2;
                        float f3;
                        Vec3d vec3d2;
                        Vec3d vec3d3;
                        Vec3d vec3d4;
                        Vec3d vec3d5;
                        block44: {
                            double d2;
                            block45: {
                                vec3d5 = trailRenderer$PtArray[Math.max(0, n10 - 1)].pos();
                                vec3d4 = trailRenderer$PtArray[n10].pos();
                                vec3d3 = trailRenderer$PtArray[n10 + 1].pos();
                                vec3d2 = trailRenderer$PtArray[Math.min(n6 - 1, n10 + 2)].pos();
                                n = trailRenderer$PtArray[n10].seq();
                                if (n5 != 0) break block43;
                                f3 = n;
                                f2 = TrailRenderer.fadeOf(trailRenderer$PtArray[n10], l2, l);
                                f = TrailRenderer.fadeOf(trailRenderer$PtArray[n10 + 1], l2, l);
                                double d3 = vec3d4.method_1025(vec3d3) - 0.0064;
                                d2 = d3 == 0.0 ? 0 : (d3 < 0.0 ? -1 : 1);
                                if (n5 != 0) break block44;
                                if (d2 >= 0) break block45;
                                fArray2[n2] = f3;
                                fArray[n2] = f2;
                                vec3dArray[n2++] = vec3d4;
                                if (n5 == 0) break block46;
                            }
                            d2 = 0.0;
                        }
                        for (double d4 = v858038; d4 < 4; ++d4) {
                            float f4 = (float)d4 / 4.0f;
                            fArray2[n2] = f3 + f4;
                            fArray[n2] = f2 + (f - f2) * f4;
                            vec3dArray[n2++] = TrailRenderer.catmullRom(vec3d5, vec3d4, vec3d3, vec3d2, f4);
                            if (n5 == 0) {
                                if (n5 == 0) continue;
                                break;
                            }
                            break block47;
                        }
                    }
                    ++n10;
                }
                if (n5 == 0) continue;
            }
            fArray2[n2] = trailRenderer$PtArray[n6 - 1].seq();
            fArray[n2] = TrailRenderer.fadeOf(trailRenderer$PtArray[n6 - 1], l2, l);
            vec3dArray[n2++] = trailRenderer$PtArray[n6 - 1].pos();
            n = n2;
        }
        TrailRenderer$Path trailRenderer$Path = n == n3 ? new TrailRenderer$Path(vec3dArray, fArray2, fArray) : new TrailRenderer$Path(Arrays.copyOf(vec3dArray, n2), Arrays.copyOf(fArray2, n2), Arrays.copyOf(fArray, n2));
        return trailRenderer$Path;
    }

    private static TrailRenderer$Pt[] scratch(int n) {
        TrailRenderer$Pt[] trailRenderer$PtArray;
        block4: {
            block5: {
                int n2 = EffectEngine.a;
                trailRenderer$PtArray = scratchBuf;
                if (n2 != 0) break block4;
                if (trailRenderer$PtArray.length >= n) break block5;
                scratchBuf = new TrailRenderer$Pt[Math.max(n, scratchBuf.length * 2)];
            }
            trailRenderer$PtArray = scratchBuf;
        }
        return trailRenderer$PtArray;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Vec3d catmullRom(Vec3d vec3d, Vec3d vec3d2, Vec3d vec3d3, Vec3d vec3d4, float f) {
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        block15: {
            double d6;
            block14: {
                int n;
                block13: {
                    d5 = 0.0;
                    d4 = d5 + Math.sqrt(vec3d.method_1022(vec3d2));
                    d3 = d4 + Math.sqrt(vec3d2.method_1022(vec3d3));
                    d2 = d3 + Math.sqrt(vec3d3.method_1022(vec3d4));
                    n = EffectEngine.a;
                    d = d4;
                    if (n != 0) break block13;
                    if (d <= d5) return vec3d2.method_1019(vec3d3.method_1020(vec3d2).method_1021((double)f));
                    d = d3;
                }
                d6 = d4;
                if (n != 0) break block14;
                if (d <= d6) return vec3d2.method_1019(vec3d3.method_1020(vec3d2).method_1021((double)f));
                d = d2;
                if (n != 0) break block15;
                d6 = d3;
            }
            if (d <= d6) {
                return vec3d2.method_1019(vec3d3.method_1020(vec3d2).method_1021((double)f));
            }
            d = d4 + (d3 - d4) * (double)f;
        }
        double d7 = d;
        Vec3d vec3d5 = TrailRenderer.blend(vec3d, vec3d2, (d4 - d7) / (d4 - d5), (d7 - d5) / (d4 - d5));
        Vec3d vec3d6 = TrailRenderer.blend(vec3d2, vec3d3, (d3 - d7) / (d3 - d4), (d7 - d4) / (d3 - d4));
        Vec3d vec3d7 = TrailRenderer.blend(vec3d3, vec3d4, (d2 - d7) / (d2 - d3), (d7 - d3) / (d2 - d3));
        Vec3d vec3d8 = TrailRenderer.blend(vec3d5, vec3d6, (d3 - d7) / (d3 - d5), (d7 - d5) / (d3 - d5));
        Vec3d vec3d9 = TrailRenderer.blend(vec3d6, vec3d7, (d2 - d7) / (d2 - d4), (d7 - d4) / (d2 - d4));
        return TrailRenderer.blend(vec3d8, vec3d9, (d3 - d7) / (d3 - d4), (d7 - d4) / (d3 - d4));
    }

    private static float fadeOf(TrailRenderer$Pt trailRenderer$Pt, long l, long l2) {
        float f;
        block10: {
            float f2;
            float f3;
            block11: {
                block12: {
                    float f4;
                    block8: {
                        int n;
                        block9: {
                            f3 = (float)(l - trailRenderer$Pt.ms()) / (float)l2;
                            n = EffectEngine.a;
                            f2 = f3;
                            f4 = 0.0f;
                            if (n != 0) break block8;
                            if (!(f2 <= f4)) break block9;
                            f = 1.0f;
                            break block10;
                        }
                        f2 = f3;
                        if (n != 0) break block11;
                        f4 = 1.0f;
                    }
                    if (!(f2 >= f4)) break block12;
                    f = 0.0f;
                    break block10;
                }
                f2 = 1.0f;
            }
            f = f2 - f3;
        }
        return f;
    }

    private static Vec3d blend(Vec3d vec3d, Vec3d vec3d2, double d, double d2) {
        return new Vec3d(vec3d.field_1352 * d + vec3d2.field_1352 * d2, vec3d.field_1351 * d + vec3d2.field_1351 * d2, vec3d.field_1350 * d + vec3d2.field_1350 * d2);
    }

    /*
     * Exception decompiling
     */
    private static boolean drawTrail(VertexConsumer var0, Matrix4f var1_1, TrailRenderer$Path var2_2, Trails$Trail var3_3, Vec3d var4_4, Vec3d var5_5, float var6_6, int var7_7, int var8_8, int var9_9, int var10_10, float var11_11, boolean var12_12) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [37[CASE]], but top level block is 7[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:845)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1042)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:929)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:73)
         *     at org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private static void ring(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, double d, double d2, double d3, float f7, float f8, int n, int n2, int n3, int n4) {
        double d4;
        double d5;
        double d6;
        double d7;
        int n5;
        block24: {
            double d8;
            block25: {
                float f9;
                block23: {
                    block31: {
                        block22: {
                            int n6;
                            block21: {
                                n5 = EffectEngine.a;
                                n6 = n4;
                                if (n5 != 0) break block21;
                                if (n6 <= 2) break block22;
                                f9 = f7;
                                if (n5 != 0) break block23;
                                float f10 = f9 - 0.0f;
                                n6 = f10 == 0.0f ? 0 : (f10 < 0.0f ? -1 : 1);
                            }
                            if (n6 > 0) break block31;
                        }
                        return;
                    }
                    f9 = f5;
                }
                d8 = (double)f9 * d3 - (double)f6 * d2;
                d7 = (double)f6 * d - (double)f4 * d3;
                d6 = (double)f4 * d2 - (double)f5 * d;
                d5 = Math.sqrt(d8 * d8 + d7 * d7 + d6 * d6);
                d4 = d5;
                if (n5 != 0) break block24;
                if (!(d4 < 1.0E-6)) break block25;
                return;
            }
            d4 = d8 / d5;
        }
        float f11 = (float)d4;
        float f12 = (float)(d7 / d5);
        float f13 = (float)(d6 / d5);
        float f14 = Math.max(0.0f, f7 - f8);
        float f15 = f7 + f8;
        int n7 = 14;
        int n8 = 0;
        while (n8 < 14) {
            block26: {
                double d9 = (double)n8 / 14.0 * Math.PI * 2.0;
                double d10 = (double)(n8 + 1) / 14.0 * Math.PI * 2.0;
                float f16 = (float)Math.cos(d9);
                float f17 = (float)Math.sin(d9);
                float f18 = (float)Math.cos(d10);
                float f19 = (float)Math.sin(d10);
                float[][] fArrayArray = new float[][]{TrailRenderer.edge(f, f2, f3, f4, f5, f6, f11, f12, f13, f16, f17, f14), TrailRenderer.edge(f, f2, f3, f4, f5, f6, f11, f12, f13, f16, f17, f15), TrailRenderer.edge(f, f2, f3, f4, f5, f6, f11, f12, f13, f18, f19, f15), TrailRenderer.edge(f, f2, f3, f4, f5, f6, f11, f12, f13, f18, f19, f14)};
                int n9 = 0;
                while (n9 < 2) {
                    block27: {
                        if (n5 != 0) break block26;
                        for (int i = 0; i < 4; ++i) {
                            int n10;
                            float[][] fArrayArray2;
                            block30: {
                                int n11;
                                block28: {
                                    block29: {
                                        if (n5 != 0) break block27;
                                        fArrayArray2 = fArrayArray;
                                        n11 = n9;
                                        if (n5 != 0) break block28;
                                        if (n11 != 0) break block29;
                                        n10 = i;
                                        break block30;
                                    }
                                    n11 = 3;
                                }
                                n10 = n11 - i;
                            }
                            float[] fArray = fArrayArray2[n10];
                            vertexConsumer.method_22918((Matrix4fc)matrix4f, fArray[0], fArray[1], fArray[2]).method_1336(n, n2, n3, n4);
                            if (n5 == 0) continue;
                        }
                        ++n9;
                    }
                    if (n5 == 0) continue;
                }
                ++n8;
            }
            if (n5 == 0) continue;
        }
    }

    private static float[] edge(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12) {
        return new float[]{f + (f4 * f10 + f7 * f11) * f12, f2 + (f5 * f10 + f8 * f11) * f12, f3 + (f6 * f10 + f9 * f11) * f12};
    }

    private static void speck(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, double d, double d2, double d3, float f7, int n, int n2, int n3, int n4) {
        double d4;
        int n5;
        double d5;
        double d6;
        double d7;
        block8: {
            double d8;
            block9: {
                d8 = (double)f5 * d3 - (double)f6 * d2;
                d7 = (double)f6 * d - (double)f4 * d3;
                d6 = (double)f4 * d2 - (double)f5 * d;
                d5 = Math.sqrt(d8 * d8 + d7 * d7 + d6 * d6);
                n5 = EffectEngine.a;
                d4 = d5;
                if (n5 != 0) break block8;
                if (!(d4 < 1.0E-6)) break block9;
                return;
            }
            d4 = d8 / d5;
        }
        float f8 = (float)d4;
        float f9 = (float)(d7 / d5);
        float f10 = (float)(d6 / d5);
        for (int i = 0; i < 2; ++i) {
            float[][] fArrayArray = new float[4][];
            fArrayArray[0] = new float[]{f - f4 * f7 - f8 * f7, f2 - f5 * f7 - f9 * f7, f3 - f6 * f7 - f10 * f7};
            fArrayArray[1] = new float[]{f + f4 * f7 - f8 * f7, f2 + f5 * f7 - f9 * f7, f3 + f6 * f7 - f10 * f7};
            fArrayArray[2] = new float[]{f + f4 * f7 + f8 * f7, f2 + f5 * f7 + f9 * f7, f3 + f6 * f7 + f10 * f7};
            float[][] fArrayArray2 = fArrayArray;
            fArrayArray[3] = new float[]{f - f4 * f7 + f8 * f7, f2 - f5 * f7 + f9 * f7, f3 - f6 * f7 + f10 * f7};
            block5: while (true) {
                Object object;
                float[][] fArrayArray3 = fArrayArray2;
                if (i == 1) {
                    object = fArrayArray3[0];
                    fArrayArray3[0] = fArrayArray3[3];
                    fArrayArray3[3] = (float[])object;
                    object = fArrayArray3[1];
                    fArrayArray3[1] = fArrayArray3[2];
                    fArrayArray3[2] = (float[])object;
                }
                object = fArrayArray3;
                int n6 = ((float[][])object).length;
                for (int j = 0; j < n6; ++j) {
                    fArrayArray2 = object;
                    if (n5 != 0) continue block5;
                    float[] fArray = fArrayArray2[j];
                    vertexConsumer.method_22918((Matrix4fc)matrix4f, fArray[0], fArray[1], fArray[2]).method_1336(n, n2, n3, n4);
                    if (n5 == 0) continue;
                }
                break;
            }
            if (n5 == 0) continue;
        }
    }

    private static void band(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, int n, int n2, int n3, int n4, int n5) {
        int n6 = -1;
        int n7 = EffectEngine.a;
        while (n6 <= 1) {
            block8: {
                for (int i = 0; i < 2; ++i) {
                    int n8;
                    int n9;
                    int n10;
                    int n11;
                    int n12;
                    int n13;
                    float f12;
                    float f13;
                    float f14;
                    float f15;
                    float f16;
                    float f17;
                    float f18;
                    float f19;
                    float f20;
                    float f21;
                    float f22;
                    Matrix4f matrix4f2;
                    VertexConsumer vertexConsumer2;
                    block9: {
                        block10: {
                            if (n7 != 0) break block8;
                            vertexConsumer2 = vertexConsumer;
                            matrix4f2 = matrix4f;
                            f22 = f;
                            f21 = f2;
                            f20 = f3;
                            f19 = f4;
                            f18 = f5;
                            f17 = f6;
                            f16 = f7 * (float)n6;
                            f15 = f8 * (float)n6;
                            f14 = f9 * (float)n6;
                            f13 = f10;
                            f12 = f11;
                            n13 = n;
                            n12 = n2;
                            n11 = n3;
                            n10 = n4;
                            n9 = n5;
                            n8 = i;
                            if (n7 != 0) break block9;
                            if (n8 != true) break block10;
                            n8 = 1;
                            break block9;
                        }
                        n8 = 0;
                    }
                    TrailRenderer.half(vertexConsumer2, matrix4f2, f22, f21, f20, f19, f18, f17, f16, f15, f14, f13, f12, n13, n12, n11, n10, n9, n8 != 0);
                    if (n7 == 0) continue;
                }
                n6 += 2;
            }
            if (n7 == 0) continue;
        }
    }

    private static void half(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, int n, int n2, int n3, int n4, int n5, boolean bl) {
        block8: {
            float f12;
            float f13;
            float f14;
            block6: {
                float f15;
                float f16;
                float f17;
                block7: {
                    f14 = f + f7 * f10;
                    f13 = f2 + f8 * f10;
                    f12 = f3 + f9 * f10;
                    f17 = f4 + f7 * f11;
                    int n6 = EffectEngine.a;
                    f16 = f5 + f8 * f11;
                    f15 = f6 + f9 * f11;
                    if (n6 != 0) break block6;
                    if (!bl) break block7;
                    vertexConsumer.method_22918((Matrix4fc)matrix4f, f, f2, f3).method_1336(n, n2, n3, n4);
                    vertexConsumer.method_22918((Matrix4fc)matrix4f, f14, f13, f12).method_1336(n, n2, n3, 0);
                    vertexConsumer.method_22918((Matrix4fc)matrix4f, f17, f16, f15).method_1336(n, n2, n3, 0);
                    vertexConsumer.method_22918((Matrix4fc)matrix4f, f4, f5, f6).method_1336(n, n2, n3, n5);
                    if (n6 == 0) break block8;
                }
                vertexConsumer.method_22918((Matrix4fc)matrix4f, f, f2, f3).method_1336(n, n2, n3, n4);
                vertexConsumer.method_22918((Matrix4fc)matrix4f, f4, f5, f6).method_1336(n, n2, n3, n5);
                vertexConsumer.method_22918((Matrix4fc)matrix4f, f17, f16, f15).method_1336(n, n2, n3, 0);
            }
            vertexConsumer.method_22918((Matrix4fc)matrix4f, f14, f13, f12).method_1336(n, n2, n3, 0);
        }
    }

    private static boolean isSample(float[] fArray, int n) {
        float f;
        block2: {
            block3: {
                int n2 = EffectEngine.a;
                float f2 = Math.abs(fArray[n] - (float)Math.round(fArray[n])) - 0.001f;
                f = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
                if (n2 != 0) break block2;
                if (f >= 0) break block3;
                f = 1.0f;
                break block2;
            }
            f = 0.0f;
        }
        return (boolean)f;
    }

    private static float hash(int n) {
        int n2 = n * TrailRenderer.a(23475, 9184477793271709141L) + TrailRenderer.a(31016, 8703506376524160844L);
        n2 = (n2 ^ n2 >> 13) * TrailRenderer.a(10796, 8201255222473493579L);
        return (float)((n2 ^ n2 >> 16) & TrailRenderer.a(24669, 3777736247686383164L)) / 65535.0f;
    }

    
    private static float[] source(Trails$Origin trails$Origin) {
        float[] fArray;
        int n = EffectEngine.a;
        int n2 = TrailRenderer$1.$SwitchMap$dev$quelchi$aurora$cosmetics$content$Trails$Origin[trails$Origin.ordinal()];
        if (n == 0) {
            switch (n2) {
                case 1: {
                    float[] fArray2 = new float[3];
                    fArray2[0] = 0.0f;
                    fArray2[1] = 0.12f;
                    fArray = fArray2;
                    fArray2[2] = 0.0f;
                    return fArray;
                }
                case 4: {
                    float[] fArray3 = new float[3];
                    fArray3[0] = 0.42f;
                    fArray3[1] = 1.05f;
                    fArray = fArray3;
                    fArray3[2] = 0.0f;
                    return fArray;
                }
                case 2: {
                    float[] fArray4 = new float[3];
                    fArray4[0] = 0.0f;
                    fArray4[1] = 1.78f;
                    fArray = fArray4;
                    fArray4[2] = 0.0f;
                    return fArray;
                }
                case 3: {
                    float[] fArray5 = new float[3];
                    fArray5[0] = 0.0f;
                    fArray5[1] = 1.25f;
                    fArray = fArray5;
                    fArray5[2] = 0.18f;
                    return fArray;
                }
            }
            n2 = 3;
        }
        float[] fArray6 = new float[n2];
        fArray6[0] = 0.0f;
        fArray6[1] = 1.0f;
        fArray = fArray6;
        fArray6[2] = 0.0f;
        return fArray;
    }

    
    public static void modelPreview(Trails$Trail trails$Trail, float f, LocalSpeckSink localSpeckSink) {
        int n = EffectEngine.a;
        if (trails$Trail == null) {
            return;
        }
        int n2 = Math.max(14, Math.min(56, trails$Trail.length()));
        float f2 = Math.max(0.075f, (float)trails$Trail.width() / 100.0f);
        float[] fArray = TrailRenderer.source(trails$Trail.origin());
        int n3 = trails$Trail.argb() & TrailRenderer.a(27003, 8387604234469986075L);
        for (int i = 0; i < n2; ++i) {
            int n4;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            block10: {
                block9: {
                    f7 = (float)i / (float)(n2 - 1);
                    f6 = fArray[0];
                    f5 = fArray[1];
                    f4 = fArray[2];
                    if (n != 0) break block9;
                    switch (trails$Trail.style()) {
                        case Halo: {
                            f3 = f7 * ((float)Math.PI * 2) + f * 1.6f;
                            f6 += MathHelper.method_15362((double)f3) * 0.55f;
                            f4 += MathHelper.method_15374((double)f3) * 0.55f;
                            f5 += MathHelper.method_15374((double)(f3 * 2.0f)) * 0.06f;
                            if (n == 0) break block10;
                        }
                        case Helix: {
                            f3 = f7 * 9.0f - f * 3.2f;
                            float f8 = 0.3f * (0.35f + f7);
                            f6 += MathHelper.method_15362((double)f3) * f8;
                            f5 += MathHelper.method_15374((double)f3) * f8;
                            f4 += f7 * 2.6f;
                            if (n == 0) break block10;
                        }
                        default: {
                            f6 += MathHelper.method_15374((double)(f7 * 6.0f - f * 3.0f)) * 0.26f * f7;
                            f5 += MathHelper.method_15374((double)(f7 * 4.2f - f * 2.4f)) * 0.11f * f7;
                        }
                    }
                }
                f4 += f7 * 2.6f;
            }
            if ((n4 = Math.round(235.0f * (f3 = (1.0f - f7) * (1.0f - f7 * 0.55f)))) <= 6) continue;
            localSpeckSink.speck(f6, f5, f4, f2 * (1.0f - f7 * 0.4f), n4 << 24 | n3);
            if (n == 0) continue;
        }
    }

    
    private static Vec3d localSource(Trails$Origin trails$Origin, int n) {
        Vec3d vec3d;
        int n2 = EffectEngine.a;
        int n3 = n;
        if (n2 == 0) {
            if (n3 != 0) {
                Vec3d vec3d2;
                if (trails$Origin == Trails$Origin.HANDS) {
                    vec3d2 = new Vec3d((double)((float)(-n) * 5.0f / 16.0f), 0.8760000467300415, 0.0);
                    return vec3d2;
                }
                vec3d2 = new Vec3d((double)((float)(-n) * 23.45f / 16.0f), 0.8760000467300415, (double)0.61125f);
                return vec3d2;
            }
            n3 = TrailRenderer$1.$SwitchMap$dev$quelchi$aurora$cosmetics$content$Trails$Origin[trails$Origin.ordinal()];
        }
        switch (n3) {
            case 1: {
                vec3d = new Vec3d(0.0, 0.02, 0.0);
                return vec3d;
            }
            case 2: {
                vec3d = new Vec3d(0.0, (double)1.751f, 0.0);
                return vec3d;
            }
            case 3: {
                vec3d = new Vec3d(0.0, (double)1.376f, 0.25);
                return vec3d;
            }
        }
        vec3d = new Vec3d(0.0, 0.35, 0.0);
        return vec3d;
    }

    public static PreviewScene$Geometry preview(Trails$Trail trails$Trail, float f) {
        Trails$Trail trails$Trail2;
        block4: {
            block5: {
                int n = EffectEngine.a;
                trails$Trail2 = trails$Trail;
                if (n != 0) break block4;
                if (trails$Trail2 != null) break block5;
                return null;
            }
            trails$Trail2 = trails$Trail;
        }
        return (arg_0, arg_1, arg_2) -> TrailRenderer.lambda$preview$2(trails$Trail2, f, arg_0, arg_1, arg_2);
    }

    private static void lambda$preview$2(Trails$Trail trails$Trail, float f, VertexConsumer vertexConsumer, Matrix4f matrix4f, Vec3d vec3d) {
        int n = EffectEngine.a;
        int[] nArray = trails$Trail.origin() == Trails$Origin.HANDS ? WINGS : BODY;
        int[] nArray2 = nArray;
        float f2 = (float)trails$Trail.width() / 100.0f;
        int n2 = trails$Trail.argb();
        int n3 = n2 >>> 24 & 0xFF;
        int n4 = n2 >>> 16 & 0xFF;
        int n5 = n2 >>> 8 & 0xFF;
        int n6 = n2 & 0xFF;
        long l = TrailRenderer.lifeMs(trails$Trail);
        int n7 = Math.max(6, Math.min(trails$Trail.length(), Math.round(40.0f)));
        long l2 = System.currentTimeMillis();
        float f3 = f * 20.0f;
        int n8 = (int)Math.floor(f3);
        float f4 = f3 - (float)n8;
        int[] nArray3 = nArray2;
        int n9 = nArray3.length;
        int n10 = 0;
        block6: while (true) {
            int n11 = n10;
            block7: while (n11 < n9) {
                block10: {
                    boolean bl;
                    float f5;
                    int n12;
                    int n13;
                    int n14;
                    int n15;
                    float f6;
                    Vec3d vec3d2;
                    Vec3d vec3d3;
                    Trails$Trail trails$Trail2;
                    TrailRenderer$Path trailRenderer$Path;
                    Matrix4f matrix4f2;
                    VertexConsumer vertexConsumer2;
                    block11: {
                        block12: {
                            TrailRenderer$Path trailRenderer$Path2;
                            boolean bl2;
                            block9: {
                                bl2 = nArray3[n10];
                                Vec3d vec3d4 = TrailRenderer.localSource(trails$Trail.origin(), bl2 ? 1 : 0);
                                ArrayDeque<TrailRenderer$Pt> arrayDeque = new ArrayDeque<TrailRenderer$Pt>();
                                for (int i = n7; i >= 1; --i) {
                                    float f7 = ((float)i + f4) * 0.05f;
                                    n11 = n8 - i;
                                    if (n != 0) continue block7;
                                    double d = Math.sin((double)n11 * 0.45) * 0.05;
                                    arrayDeque.addLast(new TrailRenderer$Pt(vec3d4.method_1031(d, 0.0, (double)(1.7f * f7)), n8 - i, l2 - (long)(f7 * 1000.0f)));
                                    if (n == 0) continue;
                                }
                                trailRenderer$Path2 = TrailRenderer.smooth(arrayDeque, vec3d4, l);
                                if (trailRenderer$Path2.pts().length >= 2) break block9;
                                break block10;
                            }
                            vertexConsumer2 = vertexConsumer;
                            matrix4f2 = matrix4f;
                            trailRenderer$Path = trailRenderer$Path2;
                            trails$Trail2 = trails$Trail;
                            vec3d3 = Vec3d.field_1353;
                            vec3d2 = vec3d;
                            f6 = f2;
                            n15 = n4;
                            n14 = n5;
                            n13 = n6;
                            n12 = n3;
                            f5 = f;
                            bl = bl2;
                            if (n != 0) break block11;
                            if (bl) break block12;
                            bl = true;
                            break block11;
                        }
                        bl = false;
                    }
                    TrailRenderer.drawTrail(vertexConsumer2, matrix4f2, trailRenderer$Path, trails$Trail2, vec3d3, vec3d2, f6, n15, n14, n13, n12, f5, bl);
                }
                ++n10;
                if (n == 0) continue block6;
            }
            break;
        }
    }

    
    private static boolean lambda$tick$1(ClientWorld var0, Set var1_1, long var2_2, Map.Entry var4_3) {
        block26: {
            block24: {
                block25: {
                    block23: {
                        block21: {
                            block22: {
                                var6_4 = var0.method_18470(((TrailRenderer$Strand)var4_3.getKey()).player());
                                var5_5 = EffectEngine.a;
                                v0 = var6_4 instanceof AbstractClientPlayerEntity;
                                if (var5_5 != 0) break block21;
                                if (!v0) break block22;
                                var7_6 = (AbstractClientPlayerEntity)var6_4;
                                v0 = var1_1.contains(((TrailRenderer$Strand)var4_3.getKey()).player());
                                if (var5_5 != 0) break block21;
                                if (v0) break block23;
                            }
                            v0 = true;
                        }
                        return v0;
                    }
                    var8_7 = TrailRenderer.trailOf(var7_6);
                    v3 /* !! */  = var8_7;
                    if (var5_5 != 0) break block24;
                    if (v3 /* !! */  != null) break block25;
                    return true;
                }
                v3 /* !! */  = var4_3.getValue();
            }
            var9_8 = (Deque)v3 /* !! */ ;
            var10_9 = var2_2 - TrailRenderer.lifeMs(var8_7);
            while (!var9_8.isEmpty()) {
                v6 = var9_8;
                if (var5_5 != 0) // goto
                v7 /* !! */  = (TrailRenderer$Pt)v6.peekFirst();
                if (var5_5 == 0) {
                }
                if (v7 /* !! */ .ms() >= var10_9) break;
                var9_8.removeFirst();
                if (var5_5 == 0) continue;
                break;
            }
            do {
                v6 = var9_8;
lbl66:

                if (v6.size() <= var8_7.length()) break;
                v11 = var9_8;
                if (var5_5 != 0) break block26;
                v7 /* !! */  = v11.removeFirst();
lbl76:

            } while (var5_5 == 0);
            v11 = var9_8;
        }
        return v11.isEmpty();
    }

    private static Deque lambda$tick$0(TrailRenderer$Strand trailRenderer$Strand) {
        return new ArrayDeque();
    }

    
    static {
        block8: {
            block7: {
                var0 = 3111585283491508227L;
                var6_1 = new long[6];
                var3_2 = 0;
                var4_3 = "\u0017\u00b6\u00bb\u0011\u00c0\u001d\u0004`5q2\u0013\u001e\u00b5\u009d\u00e9\u000b\u00f8\u00c4\u009e{\u00e2*gu\u009f\u00e2$*\u00f0#)";
                var5_4 = "\u0017\u00b6\u00bb\u0011\u00c0\u001d\u0004`5q2\u0013\u001e\u00b5\u009d\u00e9\u000b\u00f8\u00c4\u009e{\u00e2*gu\u009f\u00e2$*\u00f0#)".length();
                var2_5 = 0;
                while (true) {
                    var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                    v0 = var6_1;
                    v1 = var3_2++;
                    v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                    v3 = -1;
                    break block7;
                    break;
                }
lbl14:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    var4_3 = "\u00c9/\u0005\u0018\u00f6\u0002\u0080\u00e7$\u00c5\u00a1\u00acL\u00ae\t\u00c0";
                    var5_4 = "\u00c9/\u0005\u0018\u00f6\u0002\u0080\u00e7$\u00c5\u00a1\u00acL\u00ae\t\u00c0".length();
                    var2_5 = 0;
                    while (true) {
                        var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                        v0 = var6_1;
                        v1 = var3_2++;
                        v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                        v3 = 0;
                        break block7;
                        break;
                    }
                    break;
                }
lbl27:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    break block8;
                    break;
                }
            }
            v4 = v2 ^ var0;
            switch (v3) {
                default: {
                    continue;
                }
                case 0:
lbl38:

                continue;
            }
        }
        TrailRenderer.a = var6_1;
        TrailRenderer.b = new Integer[6];
        TrailRenderer.SUB = TrailRenderer.a(2955, 6164751352131857902L);
        TrailRenderer.BUFFER = new BufferAllocator(4096);
        TrailRenderer.TRAILS = new HashMap<TrailRenderer$Strand, Deque<TrailRenderer$Pt>>();
        TrailRenderer.NEXT_SEQ = new HashMap<TrailRenderer$Strand, Integer>();
        TrailRenderer.BODY = new int[]{0};
        TrailRenderer.WINGS = new int[]{-1, 1};
        TrailRenderer.lastWorld = new WeakReference<Object>(null);
        TrailRenderer.EMPTY_PATH = new TrailRenderer$Path(new Vec3d[0], new float[0], new float[0]);
        TrailRenderer.scratchBuf = new TrailRenderer$Pt[64];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2664;
        if (b[n2] == null) {
            TrailRenderer.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
