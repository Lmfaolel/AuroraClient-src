package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;
import net.minecraft.util.Util;

public final class MeleePrediction {
    private static final long ECHO_MS;
    private static final Map<Integer, ArrayDeque<Long>> PREDICTED;
    private static final float FLOOR = 0.5f;
    private static final long[] a;
    private static final Long[] b;

    private MeleePrediction() {
    }

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(MeleePrediction::lambda$register$0);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onMelee(PlayerEntity playerEntity, Entity entity) {
        LivingEntity livingEntity;
        LivingEntity livingEntity2;
        block28: {
            boolean bl;
            block27: {
                PlayerEntity playerEntity2;
                block26: {
                    block24: {
                        block25: {
                            bl = PredictionHandshake.a;
                            if (!Prediction.active()) return;
                            playerEntity2 = playerEntity;
                            if (bl) break block24;
                            if (playerEntity2 == MinecraftClient.method_1551().field_1724) break block25;
                            return;
                        }
                        playerEntity2 = entity;
                    }
                    if (bl) break block26;
                    if (!(playerEntity2 instanceof LivingEntity)) return;
                    playerEntity2 = entity;
                }
                livingEntity2 = (LivingEntity)playerEntity2;
                if (bl) {
                    return;
                }
                livingEntity = livingEntity2;
                if (bl) break block27;
                if (livingEntity == playerEntity) return;
                livingEntity = livingEntity2;
            }
            if (bl) break block28;
            if (!livingEntity.method_5805()) return;
            livingEntity = livingEntity2;
        }
        if (livingEntity.method_31481()) {
            return;
        }
        DamageSource damageSource = playerEntity.method_48923().method_48802(playerEntity);
        livingEntity2.method_48922(damageSource);
        MeleePrediction.applyHealth(playerEntity, livingEntity2, damageSource);
        long l = Util.method_658() + MeleePrediction.a(7722, 6258065589363069864L);
        PREDICTED.computeIfAbsent(livingEntity2.method_5628(), MeleePrediction::lambda$onMelee$1).addLast(l);
    }

    private static void applyHealth(PlayerEntity playerEntity, LivingEntity livingEntity, DamageSource damageSource) {
        PlayerEntity playerEntity2;
        float f;
        block2: {
            block3: {
                f = (float)playerEntity.method_45325(EntityAttributes.field_23721);
                boolean bl = PredictionHandshake.a;
                float f2 = playerEntity.method_7261(0.5f);
                f *= 0.2f + f2 * f2 * 0.8f;
                playerEntity2 = playerEntity;
                if (bl) break block2;
                if (!MeleePrediction.isCrit(playerEntity2, f2)) break block3;
                f *= 1.5f;
            }
            playerEntity2 = livingEntity;
        }
        float f3 = playerEntity2.method_6096();
        float f4 = (float)livingEntity.method_45325(EntityAttributes.field_23725);
        float f5 = DamageUtil.method_5496((LivingEntity)livingEntity, (float)f, (DamageSource)damageSource, (float)f3, (float)f4);
        livingEntity.method_6033(Math.max(0.5f, livingEntity.method_6032() - f5));
    }

    private static boolean isCrit(PlayerEntity playerEntity, float f) {
        boolean bl;
        block27: {
            block21: {
                PlayerEntity playerEntity2;
                boolean bl2;
                block26: {
                    block25: {
                        block24: {
                            block23: {
                                block22: {
                                    float f2;
                                    block20: {
                                        bl2 = PredictionHandshake.a;
                                        float f3 = f - 0.9f;
                                        f2 = f3 == 0.0f ? 0 : (f3 > 0.0f ? 1 : -1);
                                        if (bl2) break block20;
                                        if (f2 <= 0) break block21;
                                        playerEntity2 = playerEntity;
                                        if (bl2) break block22;
                                        double d = playerEntity2.field_6017 - 0.0;
                                        f2 = d == 0.0 ? 0 : (d > 0.0 ? 1 : -1);
                                    }
                                    if (f2 <= 0) break block21;
                                    playerEntity2 = playerEntity;
                                    if (bl2) break block23;
                                }
                                if (playerEntity2.method_24828()) break block21;
                                playerEntity2 = playerEntity;
                                if (bl2) break block24;
                            }
                            if (playerEntity2.method_6101()) break block21;
                            playerEntity2 = playerEntity;
                            if (bl2) break block25;
                        }
                        if (playerEntity2.method_5799()) break block21;
                        playerEntity2 = playerEntity;
                    }
                    if (bl2) break block26;
                    if (playerEntity2.method_6059(StatusEffects.field_5919)) break block21;
                    playerEntity2 = playerEntity;
                }
                bl = playerEntity2.method_5765();
                if (bl2) break block27;
                if (bl) break block21;
                bl = true;
                break block27;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean swallowsDamage(EntityDamageS2CPacket entityDamageS2CPacket) {
        Long l;
        block24: {
            ArrayDeque<Long> arrayDeque;
            long l2;
            block23: {
                Object object;
                boolean bl;
                block21: {
                    block22: {
                        bl = PredictionHandshake.a;
                        object = PREDICTED;
                        if (bl) break block21;
                        if (!object.isEmpty()) break block22;
                        return false;
                    }
                    object = PREDICTED.get(entityDamageS2CPacket.comp_1267());
                }
                ArrayDeque<Long> arrayDeque2 = (ArrayDeque<Long>)object;
                if (arrayDeque2 == null) {
                    return false;
                }
                long l3 = Util.method_658();
                while (true) {
                    l = (Long)arrayDeque2.pollFirst();
                    if (l == null) break;
                    long l4 = l3 - l;
                    l2 = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                    if (bl) break block23;
                    if (bl) break block23;
                    if (l2 <= 0) break;
                }
                arrayDeque = arrayDeque2;
                if (bl) break block24;
                l2 = (long)arrayDeque.isEmpty();
            }
            if (l2 != false) {
                arrayDeque = PREDICTED.remove(entityDamageS2CPacket.comp_1267());
            }
        }
        boolean bl = l != null;
        return bl;
    }

    private static void tick() {
        if (PREDICTED.isEmpty()) {
            return;
        }
        long l = Util.method_658();
        PREDICTED.values().forEach(arg_0 -> MeleePrediction.lambda$tick$3(l, arg_0));
        PREDICTED.values().removeIf(ArrayDeque::isEmpty);
    }

    private static void lambda$tick$3(long l, ArrayDeque arrayDeque) {
        arrayDeque.removeIf(arg_0 -> MeleePrediction.lambda$tick$2(l, arg_0));
    }

    private static boolean lambda$tick$2(long l, Long l2) {
        long l3;
        block2: {
            block3: {
                boolean bl = PredictionHandshake.a;
                long l4 = l - l2;
                l3 = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                if (bl) break block2;
                if (l3 <= 0) break block3;
                l3 = 1;
                break block2;
            }
            l3 = 0;
        }
        return (boolean)l3;
    }

    private static ArrayDeque lambda$onMelee$1(Integer n) {
        return new ArrayDeque();
    }

    private static void lambda$register$0(MinecraftClient minecraftClient) {
        MeleePrediction.tick();
    }

    
    static {
        long l = 2421301923629273755L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "wC,:h\u00f0B\u00db\rr\u00e2\nFW\u00ba\u00c2";
        int n2 = "wC,:h\u00f0B\u00db\rr\u00e2\nFW\u00ba\u00c2".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Long[2];
        ECHO_MS = MeleePrediction.a(26162, 3236068669019859889L);
        PREDICTED = new HashMap<Integer, ArrayDeque<Long>>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x4D82) & Short.MAX_VALUE;
        if (b[n2] == null) {
            MeleePrediction.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
