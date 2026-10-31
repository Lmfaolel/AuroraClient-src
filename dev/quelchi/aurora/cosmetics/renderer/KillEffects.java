package dev.quelchi.aurora.cosmetics.renderer;

import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.renderer.EffectList;
import dev.quelchi.aurora.cosmetics.renderer.KillEffects$Blame;
import dev.quelchi.aurora.render.EffectEngine;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public final class KillEffects {
    private static final long CREDIT_MS;
    public static final EffectList LIST;
    private static final Map<UUID, Long> HIT;
    private static volatile long lastCombat;
    private static final double COMBAT_RADIUS = 12.0;
    private static final Map<Integer, KillEffects$Blame> BLAME;
    private static final long[] a;
    private static final Long[] b;

    private KillEffects() {
    }

    public static void onDamage(int n, int n2) {
        if (n2 <= 0) {
            BLAME.remove(n);
            return;
        }
        BLAME.put(n, new KillEffects$Blame(n2, System.currentTimeMillis()));
    }

    private static PlayerEntity blamedFor(PlayerEntity playerEntity) {
        PlayerEntity playerEntity2;
        block17: {
            block16: {
                PlayerEntity playerEntity3;
                Entity entity;
                block15: {
                    KillEffects$Blame killEffects$Blame;
                    int n;
                    MinecraftClient minecraftClient;
                    block14: {
                        Object object;
                        block12: {
                            block13: {
                                minecraftClient = MinecraftClient.method_1551();
                                n = EffectList.a;
                                object = minecraftClient;
                                if (n != 0) break block12;
                                if (object.field_1687 != null) break block13;
                                return null;
                            }
                            object = BLAME.remove(playerEntity.method_5628());
                        }
                        killEffects$Blame = (KillEffects$Blame)object;
                        if (killEffects$Blame != null && System.currentTimeMillis() - killEffects$Blame.at() <= KillEffects.a(14991, 3294351691352116152L)) break block14;
                        return null;
                    }
                    Entity entity2 = minecraftClient.field_1687.method_8469(killEffects$Blame.causeId());
                    entity = entity2;
                    if (n != 0) break block15;
                    if (!(entity instanceof PlayerEntity)) break block16;
                    entity = entity2;
                }
                playerEntity2 = playerEntity3 = (PlayerEntity)entity;
                break block17;
            }
            playerEntity2 = null;
        }
        return playerEntity2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean localPlayerBlamed(PlayerEntity playerEntity) {
        int n;
        int n2;
        MinecraftClient minecraftClient;
        block23: {
            Object object;
            block21: {
                block22: {
                    block20: {
                        minecraftClient = MinecraftClient.method_1551();
                        n2 = EffectList.a;
                        if (playerEntity == null) return false;
                        object = minecraftClient;
                        if (n2 != 0) break block20;
                        if (object.field_1724 == null) return false;
                        object = minecraftClient;
                    }
                    if (n2 != 0) break block21;
                    if (object.field_1687 != null) break block22;
                    return false;
                }
                object = BLAME.get(playerEntity.method_5628());
            }
            KillEffects$Blame killEffects$Blame = (KillEffects$Blame)object;
            if (killEffects$Blame == null) return 0 != 0;
            long l = System.currentTimeMillis() - killEffects$Blame.at() - KillEffects.a(14991, 3294351691352116152L);
            n = l == 0L ? 0 : (l < 0L ? -1 : 1);
            if (n2 != 0) break block23;
            if (n > 0) return 0 != 0;
            n = killEffects$Blame.causeId();
        }
        if (n2 != 0) return n != 0;
        if (n != minecraftClient.field_1724.method_5628()) return 0 != 0;
        return 1 != 0;
    }

    public static void onAttack(UUID uUID) {
        block4: {
            int n = EffectList.a;
            Comparable<UUID> comparable = uUID;
            if (n != 0 || comparable == null) break block4;
            comparable = HIT.put(uUID, System.currentTimeMillis());
        }
        lastCombat = System.currentTimeMillis();
    }

    public static void onCombat() {
        lastCombat = System.currentTimeMillis();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean onDeath(PlayerEntity playerEntity) {
        MinecraftClient minecraftClient;
        block48: {
            long l;
            int n;
            int n2;
            block47: {
                block46: {
                    block45: {
                        int n3;
                        long l2;
                        block44: {
                            block43: {
                                block40: {
                                    boolean bl;
                                    UUID uUID;
                                    EffectList effectList;
                                    PlayerEntity playerEntity2;
                                    block42: {
                                        block41: {
                                            PlayerEntity playerEntity3;
                                            block38: {
                                                block39: {
                                                    MinecraftClient minecraftClient2;
                                                    block37: {
                                                        minecraftClient = MinecraftClient.method_1551();
                                                        n2 = EffectList.a;
                                                        if (playerEntity == null) return false;
                                                        minecraftClient2 = minecraftClient;
                                                        if (n2 != 0) break block37;
                                                        if (minecraftClient2.field_1724 == null) return false;
                                                        minecraftClient2 = minecraftClient;
                                                    }
                                                    if (minecraftClient2.field_1687 == null) {
                                                        return false;
                                                    }
                                                    playerEntity3 = playerEntity;
                                                    if (n2 != 0) break block38;
                                                    if (playerEntity3 != minecraftClient.field_1724) break block39;
                                                    return false;
                                                }
                                                playerEntity3 = KillEffects.blamedFor(playerEntity);
                                            }
                                            playerEntity2 = playerEntity3;
                                            if (playerEntity2 == null) break block40;
                                            effectList = LIST;
                                            uUID = playerEntity2.method_5667();
                                            if (playerEntity2 != minecraftClient.field_1724) break block41;
                                            bl = true;
                                            break block42;
                                        }
                                        bl = false;
                                    }
                                    EffectEngine.play(effectList.byId(KillEffects.effectFor(uUID, bl)), (Entity)playerEntity, playerEntity.method_73189(), playerEntity2.method_33571(), 1.0f, true);
                                    if (playerEntity2 != minecraftClient.field_1724) return false;
                                    return true;
                                }
                                l2 = System.currentTimeMillis();
                                Long l3 = HIT.remove(playerEntity.method_5667());
                                if (l3 == null) break block43;
                                long l4 = l2 - l3 - KillEffects.a(27179, 734794932864342814L);
                                n3 = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                                if (n2 != 0) break block44;
                                if (n3 > 0) break block43;
                                n3 = 1;
                                break block44;
                            }
                            n3 = 0;
                        }
                        n = n3;
                        long l5 = l2 - lastCombat - KillEffects.a(14991, 3294351691352116152L);
                        l = l5 == 0L ? 0 : (l5 < 0L ? -1 : 1);
                        if (n2 != 0) break block45;
                        if (l > 0) break block46;
                        double d = playerEntity.method_73189().method_1025(minecraftClient.field_1724.method_73189()) - 144.0;
                        l = d == 0.0 ? 0 : (d < 0.0 ? -1 : 1);
                    }
                    if (n2 != 0) break block47;
                    if (l > 0) break block46;
                    l = 1;
                    break block47;
                }
                l = 0;
            }
            long l6 = l;
            if (n2 != 0) return true;
            if (n != 0) break block48;
            boolean bl = l6;
            if (n2 != 0) return bl;
            if (bl) break block48;
            return false;
        }
        EffectEngine.play(LIST.byId(Cosmetics.getOwnKillEffect()), (Entity)playerEntity, playerEntity.method_73189(), minecraftClient.field_1724.method_33571(), 1.0f, true);
        return true;
    }

    public static String effectFor(UUID uUID, boolean bl) {
        return Cosmetics.killEffectFor(uUID, bl);
    }

    public static void clear() {
        HIT.clear();
        BLAME.clear();
    }

    
    static {
        long l = 3623959816477074314L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "\u001f\u00fd\u0007(\u001d'\u00e7\u0092\u0000\u0082p\u0091*\u00f4\u00b2\b8x`Y\u0089E74";
        int n2 = "\u001f\u00fd\u0007(\u001d'\u00e7\u0092\u0000\u0082p\u0091*\u00f4\u00b2\b8x`Y\u0089E74".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Long[3];
        CREDIT_MS = KillEffects.a(28436, 3659338325016403490L);
        LIST = new EffectList();
        HIT = new ConcurrentHashMap<UUID, Long>();
        BLAME = new ConcurrentHashMap<Integer, KillEffects$Blame>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x3137) & Short.MAX_VALUE;
        if (b[n2] == null) {
            KillEffects.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
