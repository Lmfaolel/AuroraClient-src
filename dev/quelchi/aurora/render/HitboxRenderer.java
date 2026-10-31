package dev.quelchi.aurora.render;

import dev.quelchi.aurora.module.modules.DamageTintModule;
import dev.quelchi.aurora.module.modules.HitboxesModule;
import dev.quelchi.aurora.module.modules.HitboxesModule$Kind;
import dev.quelchi.aurora.render.BoxRenderer;
import dev.quelchi.aurora.render.EffectEngine;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.debug.DebugHudEntries;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public final class HitboxRenderer {
    private static final double MAX_DISTANCE_SQ = 16384.0;
    private static final long[] a;
    private static final Integer[] b;

    private HitboxRenderer() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onWorldRender(WorldRenderContext worldRenderContext) {
        MinecraftClient minecraftClient;
        HitboxesModule hitboxesModule;
        int n;
        ClientWorld clientWorld;
        MinecraftClient minecraftClient2;
        block21: {
            boolean bl;
            block19: {
                block20: {
                    HitboxesModule hitboxesModule2;
                    block18: {
                        MinecraftClient minecraftClient3;
                        block17: {
                            minecraftClient2 = MinecraftClient.method_1551();
                            clientWorld = minecraftClient2.field_1687;
                            n = EffectEngine.a;
                            if (clientWorld == null) return;
                            minecraftClient3 = minecraftClient2;
                            if (n != 0) break block17;
                            if (minecraftClient3.field_1724 == null) return;
                            minecraftClient3 = minecraftClient2;
                        }
                        if (minecraftClient3.field_1773 == null) {
                            return;
                        }
                        hitboxesModule = HitboxesModule.get();
                        hitboxesModule2 = hitboxesModule;
                        if (n != 0) break block18;
                        if (hitboxesModule2 == null) return;
                        hitboxesModule2 = hitboxesModule;
                    }
                    bl = hitboxesModule2.isEnabled();
                    if (n != 0) break block19;
                    if (bl) break block20;
                    return;
                }
                minecraftClient = minecraftClient2;
                if (n != 0) break block21;
                bl = minecraftClient.field_61504.method_72772(DebugHudEntries.field_61581);
            }
            if (!bl) {
                return;
            }
            minecraftClient = minecraftClient2;
        }
        Camera camera = minecraftClient.field_1773.method_19418();
        Vec3d vec3d = camera.method_71156();
        float f = minecraftClient2.method_61966().method_60637(true);
        for (Entity entity : clientWorld.method_18112()) {
            HitboxRenderer.drawEntity(worldRenderContext, minecraftClient2, hitboxesModule, entity, vec3d, f);
            if (n == 0) continue;
        }
    }

    private static void drawEntity(WorldRenderContext worldRenderContext, MinecraftClient minecraftClient, HitboxesModule hitboxesModule, Entity entity, Vec3d vec3d, float f) {
        block55: {
            HitboxesModule hitboxesModule2;
            Box box;
            HitboxesModule$Kind hitboxesModule$Kind;
            block54: {
                HitboxesModule$Kind hitboxesModule$Kind2;
                block53: {
                    int n;
                    int n2;
                    int n3;
                    block52: {
                        block50: {
                            DamageTintModule damageTintModule;
                            DamageTintModule damageTintModule2;
                            block51: {
                                Entity entity2;
                                block49: {
                                    block48: {
                                        boolean bl;
                                        block47: {
                                            block45: {
                                                boolean bl2;
                                                block46: {
                                                    n3 = EffectEngine.a;
                                                    boolean bl3 = entity == minecraftClient.field_1724;
                                                    bl2 = bl3;
                                                    bl = bl2;
                                                    if (n3 != 0) break block45;
                                                    if (!bl) break block46;
                                                    bl = minecraftClient.field_1690.method_31044().method_31034();
                                                    if (n3 != 0) break block45;
                                                    if (!bl) break block46;
                                                    return;
                                                }
                                                bl = bl2;
                                            }
                                            if (n3 != 0) break block47;
                                            if (bl) break block48;
                                            entity2 = entity;
                                            if (n3 != 0) break block49;
                                            bl = entity2.method_5767();
                                        }
                                        if (!bl || hitboxesModule.showsInvisible(entity)) break block48;
                                        return;
                                    }
                                    entity2 = entity;
                                }
                                hitboxesModule$Kind = HitboxesModule.kindOf(entity2);
                                if (!hitboxesModule.draws(hitboxesModule$Kind)) {
                                    return;
                                }
                                Vec3d vec3d2 = entity.method_30950(f);
                                if (vec3d2.method_1025(vec3d) > 16384.0) {
                                    return;
                                }
                                box = HitboxRenderer.scaled(HitboxRenderer.boxOf(entity, vec3d2, vec3d), hitboxesModule.scale(hitboxesModule$Kind));
                                n2 = hitboxesModule.fillArgb(hitboxesModule$Kind, entity);
                                damageTintModule2 = DamageTintModule.get();
                                if (hitboxesModule$Kind != HitboxesModule$Kind.PLAYERS) break block50;
                                damageTintModule = damageTintModule2;
                                if (n3 != 0) break block51;
                                if (damageTintModule == null) break block50;
                                damageTintModule = damageTintModule2;
                            }
                            n = damageTintModule.flashActive();
                            if (n3 != 0) break block52;
                            if (n == 0) break block50;
                            n = entity instanceof LivingEntity;
                            if (n3 != 0) break block52;
                            if (n == 0) break block50;
                            LivingEntity livingEntity = (LivingEntity)entity;
                            n = livingEntity.field_6235;
                            if (n3 != 0) break block52;
                            if (n <= 0) break block50;
                            n2 = n2 & HitboxRenderer.a(20571, 1052623897925800981L) | damageTintModule2.flashColorArgb() & HitboxRenderer.a(1182, 1984283750041518289L);
                        }
                        hitboxesModule2 = hitboxesModule;
                        hitboxesModule$Kind2 = hitboxesModule$Kind;
                        if (n3 != 0) break block53;
                        n = hitboxesModule2.fills(hitboxesModule$Kind2);
                    }
                    if (n != 0) {
                        BoxRenderer.filled(worldRenderContext, box, n2, n2);
                    }
                    hitboxesModule2 = hitboxesModule;
                    if (n3 != 0) break block54;
                    hitboxesModule$Kind2 = hitboxesModule$Kind;
                }
                if (!hitboxesModule2.outlines(hitboxesModule$Kind2)) break block55;
                hitboxesModule2 = hitboxesModule;
            }
            int n = hitboxesModule2.lineArgb(hitboxesModule$Kind, entity);
            BoxRenderer.outline(worldRenderContext, box, n, n, hitboxesModule.lineWidth(hitboxesModule$Kind));
        }
    }

    private static Box boxOf(Entity entity, Vec3d vec3d, Vec3d vec3d2) {
        Vec3d vec3d3 = vec3d.method_1020(entity.method_73189());
        return entity.method_5829().method_997(vec3d3).method_989(-vec3d2.field_1352, -vec3d2.field_1351, -vec3d2.field_1350);
    }

    private static Box scaled(Box box, double d) {
        double d2;
        block4: {
            block5: {
                int n = EffectEngine.a;
                d2 = d;
                if (n != 0) break block4;
                if (d2 != 1.0) break block5;
                return box;
            }
            d2 = (box.field_1323 + box.field_1320) / 2.0;
        }
        double d3 = d2;
        double d4 = (box.field_1322 + box.field_1325) / 2.0;
        double d5 = (box.field_1321 + box.field_1324) / 2.0;
        double d6 = (box.field_1320 - box.field_1323) / 2.0 * d;
        double d7 = (box.field_1325 - box.field_1322) / 2.0 * d;
        double d8 = (box.field_1324 - box.field_1321) / 2.0 * d;
        return new Box(d3 - d6, d4 - d7, d5 - d8, d3 + d6, d4 + d7, d5 + d8);
    }

    
    static {
        long l = 4283804735131790781L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "\u009a\u00eb\u0018\u00a3r\u009bR\u0093\u0004\u00feKV\u0096^\u00f9\u00a8";
        int n2 = "\u009a\u00eb\u0018\u00a3r\u009bR\u0093\u0004\u00feKV\u0096^\u00f9\u00a8".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Integer[2];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x44F;
        if (b[n2] == null) {
            HitboxRenderer.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
