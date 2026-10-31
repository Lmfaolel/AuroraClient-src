package dev.quelchi.aurora.ambience;

import dev.quelchi.aurora.ambience.AmbienceDimensions;
import net.minecraft.client.MinecraftClient;

public final class SkyFogState {
    private static final long STALE_AFTER_NANOS;
    private static boolean skyFogSuppressed;
    private static long lastUpdateNanos;
    private static final long[] a;
    private static final Long[] b;

    private SkyFogState() {
    }

    public static void record(float f, float f2) {
        lastUpdateNanos = System.nanoTime();
        skyFogSuppressed = SkyFogState.isSuppressed(f, f2);
    }

    public static boolean isSkyFogSuppressed() {
        long l;
        block10: {
            block11: {
                long l2;
                int n;
                block8: {
                    block9: {
                        n = AmbienceDimensions.a;
                        l2 = lastUpdateNanos;
                        if (n != 0) break block8;
                        if (l2 != 0L) break block9;
                        return false;
                    }
                    l2 = System.nanoTime() - lastUpdateNanos;
                }
                long l3 = l2 - SkyFogState.a(32249, 5983554219596474321L);
                l = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                if (n != 0) break block10;
                if (l <= 0) break block11;
                return false;
            }
            l = (long)skyFogSuppressed;
        }
        return (boolean)l;
    }

    public static void reset() {
        skyFogSuppressed = false;
        lastUpdateNanos = 0L;
    }

    private static boolean isSuppressed(float f, float f2) {
        float f3;
        block24: {
            block26: {
                block25: {
                    float f4;
                    int n;
                    block22: {
                        block23: {
                            block20: {
                                block21: {
                                    boolean bl;
                                    block18: {
                                        block19: {
                                            n = AmbienceDimensions.a;
                                            bl = Float.isFinite(f);
                                            if (n != 0) break block18;
                                            if (!bl) break block19;
                                            f4 = f2;
                                            if (n != 0) break block20;
                                            if (Float.isFinite(f4)) break block21;
                                        }
                                        bl = true;
                                    }
                                    return bl;
                                }
                                f4 = f2;
                            }
                            if (n != 0) break block22;
                            if (!(f4 <= f)) break block23;
                            return true;
                        }
                        f4 = SkyFogState.resolveViewDistanceBlocks();
                    }
                    float f5 = f4;
                    float f6 = Math.max(1024.0f, f5 * 3.0f);
                    float f7 = f5 * 2.0f;
                    f3 = f2 == f6 ? 0 : (f2 > f6 ? 1 : -1);
                    if (n != 0) break block24;
                    if (f3 >= 0) break block25;
                    float f8 = f2 - f - f7;
                    f3 = f8 == 0.0f ? 0 : (f8 > 0.0f ? 1 : -1);
                    if (n != 0) break block24;
                    if (f3 < 0) break block26;
                }
                f3 = 1.0f;
                break block24;
            }
            f3 = 0.0f;
        }
        return (boolean)f3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static float resolveViewDistanceBlocks() {
        Object object;
        block10: {
            MinecraftClient minecraftClient;
            block11: {
                int n;
                block9: {
                    minecraftClient = MinecraftClient.method_1551();
                    n = AmbienceDimensions.a;
                    object = minecraftClient;
                    if (n != 0) break block9;
                    if (object == null) return 1024.0f;
                    object = minecraftClient;
                }
                if (n != 0) break block10;
                if (object.field_1690 != null) break block11;
                return 1024.0f;
            }
            object = minecraftClient.field_1690.method_42503().method_41753();
        }
        Integer n = (Integer)object;
        if (n == null) {
            return 1024.0f;
        }
        float f = (float)Math.max(2, n) * 16.0f;
        return f;
    }

    
    static {
        long l = 3813334336832905046L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "g\u00e2u\u00bcnu2\u0087,.\u00aa5R\u00a9\u00bf\u00fd";
        int n2 = "g\u00e2u\u00bcnu2\u0087,.\u00aa5R\u00a9\u00bf\u00fd".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Long[2];
        STALE_AFTER_NANOS = SkyFogState.a(28802, 1784838691924667051L);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x1A28) & Short.MAX_VALUE;
        if (b[n2] == null) {
            SkyFogState.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
