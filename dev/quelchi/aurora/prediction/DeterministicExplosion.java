package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.prediction.BlockPos3;
import dev.quelchi.aurora.prediction.BlockQuery;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class DeterministicExplosion {
    public static final float CRYSTAL_RADIUS = 6.0f;
    public static final float ANCHOR_RADIUS = 5.0f;
    private static final float STEP_DECAY = 0.22500001f;
    private static final double STEP = (double)0.3f;
    private static final long[] a;
    private static final Integer[] b;

    private DeterministicExplosion() {
    }

    public static Set<BlockPos3> destroyedBlocks(double d, double d2, double d3, float f, BlockQuery blockQuery) {
        return DeterministicExplosion.walk(d, d2, d3, f, blockQuery, false);
    }

    public static Set<BlockPos3> fireBlocks(double d, double d2, double d3, float f, BlockQuery blockQuery, Set<BlockPos3> set) {
        HashSet<BlockPos3> hashSet = new HashSet<BlockPos3>();
        Iterator<BlockPos3> iterator = DeterministicExplosion.walk(d, d2, d3, f, blockQuery, true).iterator();
        boolean bl = PredictionHandshake.a;
        while (iterator.hasNext()) {
            block19: {
                int n;
                float f2;
                BlockPos3 blockPos3;
                block18: {
                    int n2;
                    BlockQuery blockQuery2;
                    BlockPos3 blockPos32;
                    block17: {
                        boolean bl2;
                        block16: {
                            block15: {
                                boolean bl3;
                                block14: {
                                    blockPos3 = iterator.next();
                                    bl3 = set.contains(blockPos3);
                                    if (bl) break block14;
                                    if (bl3) break block15;
                                    bl3 = blockQuery.isAir(blockPos3.x(), blockPos3.y(), blockPos3.z());
                                }
                                if (!bl3) continue;
                            }
                            blockPos32 = new BlockPos3(blockPos3.x(), blockPos3.y() - 1, blockPos3.z());
                            bl2 = set.contains(blockPos32);
                            if (bl) break block16;
                            if (bl2) continue;
                            blockQuery2 = blockQuery;
                            n2 = blockPos32.x();
                            if (bl) break block17;
                            bl2 = blockQuery2.topSturdy(n2, blockPos32.y(), blockPos32.z());
                        }
                        if (!bl2) continue;
                        blockQuery2 = blockQuery;
                        n2 = blockPos32.x();
                    }
                    float f3 = blockQuery2.blastResistance(n2, blockPos32.y(), blockPos32.z()) - 1200.0f;
                    f2 = f3 == 0.0f ? 0 : (f3 > 0.0f ? 1 : -1);
                    if (bl) break block18;
                    if (f2 >= 0) {
                        continue;
                    }
                    n = blockPos3.x();
                    if (bl) break block19;
                    f2 = (float)DeterministicExplosion.fireRoll(n, blockPos3.y(), blockPos3.z());
                }
                if (f2 == false) continue;
                n = hashSet.add(blockPos3) ? 1 : 0;
            }
            if (!bl) continue;
        }
        return hashSet;
    }

    private static boolean fireRoll(int n, int n2, int n3) {
        int n4;
        block4: {
            block5: {
                boolean bl = PredictionHandshake.a;
                n4 = n * DeterministicExplosion.a(11420, 2152743328989568599L);
                if (bl) break block4;
                if (Math.floorMod(n4 ^ n2 * DeterministicExplosion.a(9610, 4195855819657697090L) ^ n3 * DeterministicExplosion.a(1433, 4969317225985636179L), 3) != 0) break block5;
                n4 = 1;
                break block4;
            }
            n4 = 0;
        }
        return n4 != 0;
    }

    private static Set<BlockPos3> walk(double d, double d2, double d3, float f, BlockQuery blockQuery, boolean n) {
        HashSet<BlockPos3> hashSet = new HashSet<BlockPos3>();
        int n2 = 0;
        boolean bl = PredictionHandshake.a;
        while (n2 < 16) {
            block44: {
                int n3 = 0;
                while (n3 < 16) {
                    block45: {
                        if (bl) break block44;
                        int n4 = 0;
                        while (n4 < 16) {
                            block49: {
                                block48: {
                                    int n5;
                                    block46: {
                                        block47: {
                                            if (bl) break block45;
                                            n5 = n2;
                                            if (bl) break block46;
                                            if (n5 == 0) break block47;
                                            n5 = n2;
                                            if (bl) break block46;
                                            if (n5 == 15) break block47;
                                            n5 = n3;
                                            if (bl) break block46;
                                            if (n5 == 0) break block47;
                                            n5 = n3;
                                            if (bl) break block46;
                                            if (n5 == 15) break block47;
                                            n5 = n4;
                                            if (bl) break block46;
                                            if (n5 == 0) break block47;
                                            n5 = n4;
                                            if (bl) break block46;
                                            if (n5 == 15) break block47;
                                            break block48;
                                        }
                                        n5 = n2;
                                    }
                                    double d4 = (double)n5 / 15.0 * 2.0 - 1.0;
                                    double d5 = (double)n3 / 15.0 * 2.0 - 1.0;
                                    double d6 = (double)n4 / 15.0 * 2.0 - 1.0;
                                    double d7 = Math.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
                                    d4 /= d7;
                                    d5 /= d7;
                                    d6 /= d7;
                                    float f2 = f * 1.0f;
                                    double d8 = d;
                                    double d9 = d2;
                                    double d10 = d3;
                                    while (f2 > 0.0f) {
                                        block52: {
                                            float f3;
                                            block53: {
                                                int n6;
                                                int n7;
                                                int n8;
                                                int n9;
                                                block54: {
                                                    int n10;
                                                    int n11;
                                                    block50: {
                                                        block51: {
                                                            n9 = DeterministicExplosion.floor(d8);
                                                            n8 = DeterministicExplosion.floor(d9);
                                                            if (bl) break block49;
                                                            n7 = DeterministicExplosion.floor(d10);
                                                            n11 = blockQuery.isAir(n9, n8, n7);
                                                            n10 = n11;
                                                            if (bl) break block50;
                                                            if (n10 != 0) break block51;
                                                            f2 -= (blockQuery.blastResistance(n9, n8, n7) + 0.3f) * 0.3f;
                                                        }
                                                        f3 = f2;
                                                        if (bl) break block52;
                                                        float f4 = f3 - 0.0f;
                                                        n10 = f4 == 0.0f ? 0 : (f4 > 0.0f ? 1 : -1);
                                                    }
                                                    if (n10 <= 0) break block53;
                                                    n6 = n;
                                                    if (bl) break block53;
                                                    if (n6 != 0) break block54;
                                                    n6 = n11;
                                                    if (bl) break block53;
                                                    if (n6 != 0) break block53;
                                                }
                                                n6 = hashSet.add(new BlockPos3(n9, n8, n7)) ? 1 : 0;
                                            }
                                            d8 += d4 * (double)0.3f;
                                            d9 += d5 * (double)0.3f;
                                            d10 += d6 * (double)0.3f;
                                            f3 = f2 = f2 - 0.22500001f;
                                        }
                                        if (!bl) continue;
                                    }
                                }
                                ++n4;
                            }
                            if (!bl) continue;
                        }
                        ++n3;
                    }
                    if (!bl) continue;
                }
                ++n2;
            }
            if (!bl) continue;
        }
        return hashSet;
    }

    private static int floor(double d) {
        return (int)Math.floor(d);
    }

    
    static {
        long l = 8256290782426755662L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "\u0013K\u00b5\u008f+\u0003z\u00aas\u0086V\u00c3\u00fd\u0085\u00d8D*\u009a]X:\u00b4e\u0093";
        int n2 = "\u0013K\u00b5\u008f+\u0003z\u00aas\u0086V\u00c3\u00fd\u0085\u00d8D*\u009a]X:\u00b4e\u0093".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Integer[3];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x72CA;
        if (b[n2] == null) {
            DeterministicExplosion.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
