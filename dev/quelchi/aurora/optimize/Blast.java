package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.optimize.Blast$Pending;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.Vec3d;

public final class Blast {
    private static final long LIFETIME_MS;
    private static final double MATCH_SQ = 0.01;
    public static final int NONE = 0;
    public static final int SOUND = 1;
    public static final int SOUND_AND_PARTICLE;
    private static final List<Blast$Pending> PENDING;
    private static final long[] a;
    private static final Long[] b;

    private Blast() {
    }

    public static void playLocalWithParticle(ClientWorld clientWorld, Vec3d vec3d) {
        Blast.play(clientWorld, vec3d, true);
    }

    private static void play(ClientWorld clientWorld, Vec3d vec3d, boolean bl) {
        block10: {
            ClientWorld clientWorld2;
            block9: {
                boolean bl2;
                block8: {
                    bl2 = ProjectileOptimizer.a;
                    if (clientWorld != null && vec3d != null) break block8;
                    return;
                }
                Blast.prune();
                PENDING.add(new Blast$Pending(vec3d, Util.method_658() + Blast.a(5389, 3931099270037171569L), bl));
                clientWorld2 = clientWorld;
                if (bl2) break block9;
                clientWorld2.method_8486(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350, (SoundEvent)SoundEvents.field_15152.comp_349(), SoundCategory.field_15245, 4.0f, (1.0f + (clientWorld.field_9229.method_43057() - clientWorld.field_9229.method_43057()) * 0.2f) * 0.7f, false);
                if (!bl) break block10;
                clientWorld2 = clientWorld;
            }
            clientWorld2.method_8406((ParticleEffect)ParticleTypes.field_11221, vec3d.field_1352, vec3d.field_1351, vec3d.field_1350, 1.0, 0.0, 0.0);
        }
    }

    public static int alreadyPlayed(Vec3d vec3d) {
        int n;
        block19: {
            boolean bl;
            block17: {
                int n2;
                block15: {
                    block16: {
                        bl = ProjectileOptimizer.a;
                        n2 = PENDING.isEmpty();
                        if (bl) break block15;
                        if (n2 != 0) break block16;
                        if (vec3d != null) break block17;
                    }
                    n2 = 0;
                }
                return n2;
            }
            Blast.prune();
            for (int i = 0; i < PENDING.size(); ++i) {
                int n3;
                block20: {
                    block21: {
                        Blast$Pending blast$Pending;
                        block18: {
                            blast$Pending = PENDING.get(i);
                            if (bl) break block18;
                            double d = blast$Pending.pos().method_1025(vec3d) - 0.01;
                            n = d == 0.0 ? 0 : (d < 0.0 ? -1 : 1);
                            if (bl) break block19;
                            if (n > 0) continue;
                            blast$Pending = PENDING.remove(i);
                        }
                        n3 = blast$Pending.particle();
                        if (bl) break block20;
                        if (n3 == 0) break block21;
                        n3 = 2;
                        break block20;
                    }
                    n3 = 1;
                }
                return n3;
            }
            n = 0;
        }
        return n;
    }

    public static void tick() {
        Blast.prune();
    }

    private static void prune() {
        if (PENDING.isEmpty()) {
            return;
        }
        long l = Util.method_658();
        PENDING.removeIf(arg_0 -> Blast.lambda$prune$0(l, arg_0));
    }

    public static void clearAll() {
        PENDING.clear();
    }

    private static boolean lambda$prune$0(long l, Blast$Pending blast$Pending) {
        long l2;
        block2: {
            block3: {
                boolean bl = ProjectileOptimizer.a;
                long l3 = l - blast$Pending.expires();
                l2 = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                if (bl) break block2;
                if (l2 <= 0) break block3;
                l2 = 1;
                break block2;
            }
            l2 = 0;
        }
        return (boolean)l2;
    }

    
    static {
        long l = 7852302682594572058L;
        long l2 = 0x79BDFE32C0E57718L ^ l;
        SOUND_AND_PARTICLE = (int)l2;
        long l3 = 8534645975930505949L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "@\u00ff6\u00be\u00a0R\u00e0|;\u00cc\u00ba\u00bd\u00c3\u0001z\u0097";
        int n2 = "@\u00ff6\u00be\u00a0R\u00e0|;\u00cc\u00ba\u00bd\u00c3\u0001z\u0097".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l3;
        } while (n3 < n2);
        a = lArray;
        b = new Long[2];
        LIFETIME_MS = Blast.a(4071, 5601807192145491866L);
        PENDING = new ArrayList<Blast$Pending>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x787C) & Short.MAX_VALUE;
        if (b[n2] == null) {
            Blast.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
