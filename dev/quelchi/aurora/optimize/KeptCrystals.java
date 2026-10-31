package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.duck.KeptCrystal;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;

public final class KeptCrystals {
    private static final long FIRST_RELEASE;
    private static final long SHORTEST_RELEASE;
    private static final long LONGEST_RELEASE;
    private static final long[] confirmations;
    private static int next;
    private static int filled;
    private static long releaseAfter;
    private static boolean kept;
    private static long lastKeptAt;
    private static final long[] a;
    private static final Long[] b;

    private KeptCrystals() {
    }

    public static void register() {
        ClientPlayConnectionEvents.DISCONNECT.register(KeptCrystals::lambda$register$0);
    }

    public static void keep(EndCrystalEntity endCrystalEntity) {
        long l = System.nanoTime();
        ((KeptCrystal)endCrystalEntity).aurora$keep(l);
        kept = true;
        lastKeptAt = l;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void confirmRemoval(Entity entity) {
        long l;
        long l2;
        block16: {
            boolean bl;
            block14: {
                long l3;
                block15: {
                    Entity entity2;
                    block13: {
                        bl = ProjectileOptimizer.a;
                        entity2 = entity;
                        if (bl) break block13;
                        if (!(entity2 instanceof KeptCrystal)) return;
                        entity2 = entity;
                    }
                    KeptCrystal keptCrystal = (KeptCrystal)entity2;
                    if (!keptCrystal.aurora$isKept()) {
                        return;
                    }
                    l3 = System.nanoTime() - keptCrystal.aurora$keptAt();
                    if (bl) break block14;
                    if (l3 <= LONGEST_RELEASE) break block15;
                    return;
                }
                KeptCrystals.confirmations[KeptCrystals.next] = l3;
                next = (next + 1) % confirmations.length;
                filled = Math.min(filled + 1, confirmations.length);
            }
            long l4 = 0L;
            for (int i = 0; i < filled; ++i) {
                l2 = l4;
                l = confirmations[i];
                if (!bl) {
                    l4 = Math.max(l2, l);
                    if (!bl) continue;
                }
                break block16;
            }
            l2 = SHORTEST_RELEASE;
            l = Math.min(LONGEST_RELEASE, l4 * KeptCrystals.a(8762, 5734091828878911477L));
        }
        releaseAfter = Math.max(l2, l);
    }

    public static void reset() {
        next = 0;
        filled = 0;
        releaseAfter = FIRST_RELEASE;
        kept = false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Predicate<? super Entity> hide(Predicate<? super Entity> predicate) {
        long l;
        int n;
        long l2;
        block9: {
            l2 = System.nanoTime() - releaseAfter;
            boolean bl = ProjectileOptimizer.a;
            n = kept;
            if (bl) break block9;
            if (n == 0) return predicate;
            l = lastKeptAt;
            if (bl) return arg_0 -> KeptCrystals.lambda$hide$1(l, predicate, arg_0);
            long l3 = l - l2 - 0L;
            n = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
        }
        if (n <= 0) {
            return predicate;
        }
        l = l2;
        return arg_0 -> KeptCrystals.lambda$hide$1(l, predicate, arg_0);
    }

    private static boolean isKeptSince(Entity entity, long l) {
        int n;
        block13: {
            block11: {
                KeptCrystal keptCrystal;
                boolean bl;
                block12: {
                    Entity entity2;
                    block10: {
                        bl = ProjectileOptimizer.a;
                        entity2 = entity;
                        if (bl) break block10;
                        if (!(entity2 instanceof KeptCrystal)) break block11;
                        entity2 = entity;
                    }
                    KeptCrystal keptCrystal2 = (KeptCrystal)entity2;
                    keptCrystal = keptCrystal2;
                    if (bl) break block12;
                    if (!keptCrystal.aurora$isKept()) break block11;
                    keptCrystal = keptCrystal2;
                }
                long l2 = keptCrystal.aurora$keptAt() - l - 0L;
                n = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                if (bl) break block13;
                if (n <= 0) break block11;
                n = 1;
                break block13;
            }
            n = 0;
        }
        return n != 0;
    }

    private static boolean lambda$hide$1(long l, Predicate predicate, Entity entity) {
        boolean bl;
        block8: {
            block7: {
                boolean bl2;
                block6: {
                    bl2 = ProjectileOptimizer.a;
                    bl = KeptCrystals.isKeptSince(entity, l);
                    if (bl2) break block6;
                    if (bl) break block7;
                    bl = predicate.test(entity);
                }
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    private static void lambda$register$0(ClientPlayNetworkHandler clientPlayNetworkHandler, MinecraftClient minecraftClient) {
        KeptCrystals.reset();
    }

    
    static {
        long l = 5194681481263245437L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "M\u0006_\u0015\u00ed\u0095\u00feO)j/\u00c0r\u00c6W\u00a4\u0007\u0084\u00a1k\u00922\u00cf\u008a";
        int n2 = "M\u0006_\u0015\u00ed\u0095\u00feO)j/\u00c0r\u00c6W\u00a4\u0007\u0084\u00a1k\u00922\u00cf\u008a".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Long[3];
        FIRST_RELEASE = TimeUnit.SECONDS.toNanos(1L);
        SHORTEST_RELEASE = TimeUnit.MILLISECONDS.toNanos(KeptCrystals.a(4619, 365187794347956166L));
        LONGEST_RELEASE = TimeUnit.SECONDS.toNanos(KeptCrystals.a(14864, 7024797969001529308L));
        confirmations = new long[16];
        releaseAfter = FIRST_RELEASE;
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x5CD) & Short.MAX_VALUE;
        if (b[n2] == null) {
            KeptCrystals.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
