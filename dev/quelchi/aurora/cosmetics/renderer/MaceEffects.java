package dev.quelchi.aurora.cosmetics.renderer;

import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.renderer.EffectList;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Effect;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;

public final class MaceEffects {
    public static final EffectList LIST;
    private static final long DEBOUNCE_MS;
    private static long lastFired;
    private static Vec3d pendingAt;
    private static boolean pendingHeavy;
    private static long pendingSoundMs;
    private static int pendingCause;
    private static int pendingVictim;
    private static long pendingCauseMs;
    private static final long PAIR_MS;
    private static final Map<UUID, String> REMOTE;
    private static final double ATTACKER_RADIUS = 6.0;
    private static final long[] a;
    private static final Long[] b;

    private MaceEffects() {
    }

    public static void onSmash(Vec3d vec3d, boolean bl) {
        pendingAt = vec3d;
        pendingHeavy = bl;
        pendingSoundMs = System.currentTimeMillis();
        MaceEffects.resolve();
    }

    public static void tick() {
        if (pendingAt != null) {
            MaceEffects.resolve();
        }
    }

    public static void onDamage(int n, int n2) {
        int n3;
        block2: {
            block3: {
                int n4 = EffectList.a;
                n3 = n2;
                if (n4 != 0) break block2;
                if (n3 > 0) break block3;
                return;
            }
            pendingCause = n2;
            n3 = n;
        }
        pendingVictim = n3;
        pendingCauseMs = System.currentTimeMillis();
        MaceEffects.resolve();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void resolve() {
        EffectEngine$Effect effectEngine$Effect;
        boolean bl;
        Entity entity;
        Vec3d vec3d;
        Vec3d vec3d2;
        block63: {
            long l;
            block64: {
                int n;
                MinecraftClient minecraftClient;
                block62: {
                    Entity entity2;
                    block61: {
                        block56: {
                            block65: {
                                int n2;
                                block53: {
                                    long l2;
                                    long l3;
                                    long l4;
                                    block55: {
                                        block54: {
                                            long l5;
                                            long l6;
                                            block51: {
                                                block52: {
                                                    block50: {
                                                        MinecraftClient minecraftClient2;
                                                        block49: {
                                                            minecraftClient = MinecraftClient.method_1551();
                                                            n = EffectList.a;
                                                            minecraftClient2 = minecraftClient;
                                                            if (n != 0) break block49;
                                                            if (minecraftClient2.field_1724 == null) return;
                                                            minecraftClient2 = minecraftClient;
                                                        }
                                                        if (minecraftClient2.field_1687 != null && pendingAt != null) break block50;
                                                        return;
                                                    }
                                                    l = System.currentTimeMillis();
                                                    l6 = l;
                                                    l5 = lastFired;
                                                    if (n != 0) break block51;
                                                    if (l6 - l5 >= MaceEffects.a(32113, 6891623966670453115L)) break block52;
                                                    return;
                                                }
                                                l6 = l - pendingSoundMs;
                                                l5 = MaceEffects.a(12134, 2585222506457803631L);
                                            }
                                            if (l6 > l5) {
                                                pendingAt = null;
                                                return;
                                            }
                                            vec3d2 = null;
                                            n2 = pendingCause;
                                            if (n != 0) break block53;
                                            if (n2 <= 0) break block54;
                                            l4 = l;
                                            l3 = pendingCauseMs;
                                            if (n != 0) break block55;
                                            if (l4 - l3 > MaceEffects.a(5152, 4686335906531872812L)) break block54;
                                            Entity entity3 = minecraftClient.field_1687.method_8469(pendingCause);
                                            n2 = entity3 instanceof PlayerEntity;
                                            if (n != 0) break block53;
                                            if (n2 == 0) break block54;
                                            vec3d2 = vec3d = (PlayerEntity)entity3;
                                            break block65;
                                        }
                                        l4 = l - pendingSoundMs;
                                        l3 = MaceEffects.a(29873, 8577834743632457914L);
                                    }
                                    n2 = (l2 = l4 - l3) == 0L ? 0 : (l2 < 0L ? -1 : 1);
                                }
                                if (n2 > 0) {
                                    double d = 36.0;
                                    for (PlayerEntity playerEntity : minecraftClient.field_1687.method_18456()) {
                                        block60: {
                                            block59: {
                                                PlayerEntity playerEntity2;
                                                block57: {
                                                    if (n != 0) break block56;
                                                    playerEntity2 = playerEntity;
                                                    if (n != 0) break block57;
                                                    if (!playerEntity2.method_6047().method_31574(Items.field_49814)) {
                                                        continue;
                                                    }
                                                    playerEntity2 = playerEntity;
                                                }
                                                double d2 = playerEntity2.method_73189().method_1025(pendingAt);
                                                if (n != 0) break block59;
                                                if (!(d2 < d)) break block60;
                                                d = d2;
                                            }
                                            vec3d2 = playerEntity;
                                        }
                                        if (n == 0) continue;
                                    }
                                }
                            }
                            if (vec3d2 == null) {
                                return;
                            }
                        }
                        vec3d = pendingAt;
                        Entity entity4 = pendingVictim > 0 ? minecraftClient.field_1687.method_8469(pendingVictim) : null;
                        entity = entity4;
                        entity2 = entity;
                        if (n != 0) break block61;
                        if (entity2 == null) break block62;
                        entity2 = entity;
                    }
                    vec3d = entity2.method_73189();
                }
                bl = pendingHeavy;
                pendingAt = null;
                pendingCause = 0;
                pendingVictim = 0;
                EffectList effectList = LIST;
                UUID uUID = vec3d2.method_5667();
                boolean bl2 = vec3d2 == minecraftClient.field_1724;
                effectEngine$Effect = effectList.byId(MaceEffects.effectFor(uUID, bl2));
                if (n != 0) break block63;
                if (effectEngine$Effect != null) break block64;
                return;
            }
            lastFired = l;
        }
        EffectEngine$Effect effectEngine$Effect2 = effectEngine$Effect;
        Entity entity5 = entity;
        Vec3d vec3d3 = vec3d;
        Vec3d vec3d4 = vec3d2.method_33571();
        float f = bl ? 1.9f : 1.0f;
        EffectEngine.play(effectEngine$Effect2, entity5, vec3d3, vec3d4, f, false);
    }

    public static String effectFor(UUID uUID, boolean bl) {
        boolean bl2;
        block7: {
            block8: {
                int n = EffectList.a;
                bl2 = bl;
                if (n != 0) break block7;
                if (!bl2) break block8;
                return Cosmetics.getOwnMaceEffect();
            }
            bl2 = Cosmetics.showOthers();
        }
        if (!bl2) {
            return "";
        }
        return REMOTE.getOrDefault(uUID, "");
    }

    
    public static void setRemote(UUID uUID, String string) {
        block12: {
            String string2;
            block13: {
                int n;
                block10: {
                    String string3;
                    block11: {
                        n = EffectList.a;
                        if (uUID == null) {
                            return;
                        }
                        string3 = string;
                        if (n != 0) break block10;
                        if (string3 == null) break block11;
                        string2 = string;
                        if (n != 0) break block12;
                        if (!string2.isEmpty()) break block13;
                    }
                    string3 = REMOTE.remove(uUID);
                }
                if (n == 0) break block12;
            }
            string2 = REMOTE.put(uUID, string);
        }
    }

    public static void clearRemote() {
        REMOTE.clear();
    }

    
    static {
        block8: {
            block7: {
                var0 = 8981633696962049767L;
                var6_1 = new long[6];
                var3_2 = 0;
                var4_3 = "o\u00f8\u00f0\t\u00f1\u00b4\u00c9\u0001_E\u00a2n\u0090\u00da\u00ddr#\u0006\u00db9@\u00ec\u000f\u00e4\u000b\u00af\u00bb\u0002>.\u0086a";
                var5_4 = "o\u00f8\u00f0\t\u00f1\u00b4\u00c9\u0001_E\u00a2n\u0090\u00da\u00ddr#\u0006\u00db9@\u00ec\u000f\u00e4\u000b\u00af\u00bb\u0002>.\u0086a".length();
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
                    var4_3 = "=\u00ac\u0019\u00ef\u00c4\u00e4f1\u000b\u0085\u00e6\u007f\u00dd\u00c4\u001a\u00c6";
                    var5_4 = "=\u00ac\u0019\u00ef\u00c4\u00e4f1\u000b\u0085\u00e6\u007f\u00dd\u00c4\u001a\u00c6".length();
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
        MaceEffects.a = var6_1;
        MaceEffects.b = new Long[6];
        MaceEffects.PAIR_MS = MaceEffects.a(26838, 8584083254852578523L);
        MaceEffects.DEBOUNCE_MS = MaceEffects.a(15254, 1395513675505024926L);
        MaceEffects.LIST = new EffectList();
        MaceEffects.REMOTE = new ConcurrentHashMap<UUID, String>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x408) & Short.MAX_VALUE;
        if (b[n2] == null) {
            MaceEffects.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
