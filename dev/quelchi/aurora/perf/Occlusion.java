package dev.quelchi.aurora.perf;

import dev.quelchi.aurora.perf.Occlusion$Seen;
import dev.quelchi.aurora.perf.Tweaks;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public final class Occlusion {
    private static final double CLIP = 0.28;
    private static final int MAX_LAYERS;
    private static final int MAX_CELLS;
    private static final WeakHashMap<Object, Occlusion$Seen> SEEN;
    private static final BlockPos.Mutable POS;
    private static final double[] C;
    private static final double[] LO;
    private static final double[] HI;
    private static final int[] CELL;
    private static int provedAxis;
    private static int provedK;
    private static double clipX;
    private static double clipY;
    private static double clipZ;
    private static long clipAt;
    private static WeakReference<World> clipWorld;
    private static boolean clips;
    private static final long[] a;
    private static final Long[] b;

    private Occlusion() {
    }

    
    public static boolean hidden(Object var0, World var1_2, double var2_3, double var4_5, double var6_6, double var8_7, double var10_8, double var12_9, double var14_10, double var16_1, double var18_4) {
        block43: {
            block41: {
                block42: {
                    block40: {
                        block36: {
                            block37: {
                                block38: {
                                    block39: {
                                        block34: {
                                            block35: {
                                                block32: {
                                                    block33: {
                                                        var20_11 = Tweaks.a;
                                                        v0 = var1_2;
                                                        if (var20_11 != 0) break block32;
                                                        if (!Occlusion.cameraClips(v0, var2_3, var4_5, var6_6)) break block33;
                                                        return false;
                                                    }
                                                    Occlusion.C[0] = var2_3;
                                                    Occlusion.C[1] = var4_5;
                                                    Occlusion.C[2] = var6_6;
                                                    Occlusion.LO[0] = var8_7;
                                                    Occlusion.LO[1] = var10_8;
                                                    Occlusion.LO[2] = var12_9;
                                                    Occlusion.HI[0] = var14_10;
                                                    Occlusion.HI[1] = var16_1;
                                                    Occlusion.HI[2] = var18_4;
                                                    v0 = var1_2;
                                                }
                                                var21_12 = v0.method_75260();
                                                var23_13 = Occlusion.SEEN.get(var0);
                                                v3 = var23_13;
                                                if (var20_11 != 0) break block34;
                                                if (v3 != null) break block35;
                                                var23_13 = new Occlusion$Seen();
                                                Occlusion.SEEN.put(var0, var23_13);
                                            }
                                            v3 = var23_13;
                                        }
                                        v5 = v3.axis;
                                        if (var20_11 != 0) break block36;
                                        if (v5 >= 0) {
                                        }
                                        v7 = var23_13;
                                        if (var20_11 != 0) break block37;
                                        if (!Occlusion.between(v7.axis, var23_13.k)) break block38;
                                        v10 = Occlusion.solid(var1_2, var23_13.axis, var23_13.k);
                                        if (var20_11 != 0) break block39;
                                        if (!v10) break block38;
                                        v10 = true;
                                    }
                                    return v10;
                                }
                                v7 = var23_13;
                            }
                            v7.axis = -1;
                            if (var20_11 == 0) break block40;
lbl79:

                            v5 = (cfr_temp_0 = var21_12 - var23_13.next) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                        }
                        if (var20_11 != 0) break block41;
                        if (v5 >= 0) break block40;
                        v15 = var23_13;
                        if (var20_11 != 0) break block42;
                        if (v15.next - var21_12 > Occlusion.a(17192, 535533461385248188L)) break block40;
                        return false;
                    }
                    v15 = var23_13;
                }
                v15.next = var21_12 + Occlusion.a(17086, 2775396893357669416L) + (long)(var0.hashCode() & 3);
                if (var20_11 != 0) break block43;
                v5 = (int)Occlusion.prove(var1_2);
            }
            if (v5 == 0) {
                return false;
            }
            var23_13.axis = Occlusion.provedAxis;
            var23_13.k = Occlusion.provedK;
        }
        return true;
    }

    public static boolean hiddenNow(World world, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        World world2;
        block4: {
            block5: {
                int n = Tweaks.a;
                world2 = world;
                if (n != 0) break block4;
                if (!Occlusion.cameraClips(world2, d, d2, d3)) break block5;
                return false;
            }
            Occlusion.C[0] = d;
            Occlusion.C[1] = d2;
            Occlusion.C[2] = d3;
            Occlusion.LO[0] = d4;
            Occlusion.LO[1] = d5;
            Occlusion.LO[2] = d6;
            Occlusion.HI[0] = d7;
            Occlusion.HI[1] = d8;
            Occlusion.HI[2] = d9;
            world2 = world;
        }
        return Occlusion.prove(world2);
    }

    
    private static boolean prove(World world) {
        int n = 0;
        int n2 = Tweaks.a;
        block7: do {
            block17: {
                int n3;
                int n4;
                int n5;
                block15: {
                    double d;
                    block16: {
                        double d2;
                        block13: {
                            block14: {
                                if (n >= 3) return 0 != 0;
                                d = C[n];
                                d2 = LO[n];
                                if (n2 != 0) break block13;
                                double d3 = d - d2;
                                int n6 = d3 == 0.0 ? 0 : (d3 < 0.0 ? -1 : 1);
                                if (n2 != 0) return n6 != 0;
                                if (n6 >= 0) break block14;
                                n5 = MathHelper.method_15357((double)C[n]) + 1;
                                n4 = MathHelper.method_15357((double)LO[n]) - 1;
                                n3 = 1;
                                if (n2 == 0) break block15;
                            }
                            d = C[n];
                            if (n2 != 0) break block16;
                            d2 = HI[n];
                        }
                        if (!(d > d2)) break block17;
                        d = C[n];
                    }
                    n5 = MathHelper.method_15357((double)d) - 1;
                    n4 = MathHelper.method_15384((double)HI[n]);
                    n3 = -1;
                }
                int n7 = Math.min((n4 - n5) * n3 + 1, 64);
                int n8 = n5;
                for (int i = 0; i < n7; ++i, n8 += n3) {
                    if (n2 != 0) continue block7;
                    boolean bl = Occlusion.solid(world, n, n8);
                    if (n2 != 0) return bl;
                    if (!bl) {
                        if (n2 == 0) continue;
                        break;
                    }
                    provedAxis = n;
                    provedK = n8;
                    return true;
                }
            }
            ++n;
        } while (n2 == 0);
        return 0 != 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean between(int n, int n2) {
        double d;
        block23: {
            int n3;
            block24: {
                double d2;
                double d3;
                int n4;
                block20: {
                    block21: {
                        int n5;
                        block22: {
                            n4 = Tweaks.a;
                            d3 = C[n];
                            d2 = LO[n];
                            if (n4 != 0) break block20;
                            if (!(d3 < d2)) break block21;
                            n5 = n2;
                            if (n4 != 0) break block22;
                            if (!((double)n5 > C[n])) return 0 != 0;
                            int n6 = n2 + 1;
                            if (n4 != 0) return n6 != 0;
                            double d4 = (double)n6 - LO[n];
                            n5 = d4 == 0.0 ? 0 : (d4 < 0.0 ? -1 : 1);
                        }
                        if (n5 > 0) return 0 != 0;
                        return 1 != 0;
                    }
                    d3 = C[n];
                    d2 = HI[n];
                }
                double d5 = d3 - d2;
                d = d5 == 0.0 ? 0 : (d5 > 0.0 ? 1 : -1);
                if (n4 != 0) return (boolean)d;
                if (d <= 0) break block23;
                n3 = n2;
                if (n4 != 0) break block24;
                if (!((double)n3 >= HI[n])) return 0 != 0;
                int n7 = n2;
                if (n4 != 0) return n7 != 0;
                double d6 = (double)(n7 + 1) - C[n];
                n3 = d6 == 0.0 ? 0 : (d6 < 0.0 ? -1 : 1);
            }
            if (n3 > 0) return 0 != 0;
            return 1 != 0;
        }
        d = 0.0;
        return (boolean)d;
    }

    private static boolean solid(World world, int n, int n2) {
        int n3;
        block27: {
            int n4;
            int n5;
            int n6;
            int n7;
            int n8;
            block25: {
                int n9;
                int n10;
                block26: {
                    double d;
                    double d2;
                    double d3;
                    block22: {
                        double d4;
                        double d5;
                        block20: {
                            int n11 = (n + 1) % 3;
                            int n12 = (n + 2) % 3;
                            n8 = Tweaks.a;
                            double d6 = Double.MAX_VALUE;
                            d5 = -1.7976931348623157E308;
                            d4 = Double.MAX_VALUE;
                            d3 = -1.7976931348623157E308;
                            for (n10 = n2; n10 <= n2 + 1; ++n10) {
                                if (n8 != 0) break block20;
                                for (n7 = 0; n7 < 2; ++n7) {
                                    double d7;
                                    double d8;
                                    block24: {
                                        double[] dArray;
                                        block21: {
                                            block23: {
                                                d8 = n10;
                                                dArray = C;
                                                if (n8 != 0) break block21;
                                                d2 = dArray[n];
                                                if (n8 != 0) break block22;
                                                d8 = d8 - d2;
                                                if (n7 != 0) break block23;
                                                d7 = LO[n];
                                                break block24;
                                            }
                                            dArray = HI;
                                        }
                                        d7 = dArray[n];
                                    }
                                    double d9 = d8 / (d7 - C[n]);
                                    double d10 = C[n11] + d9 * (LO[n11] - C[n11]);
                                    double d11 = C[n11] + d9 * (HI[n11] - C[n11]);
                                    double d12 = C[n12] + d9 * (LO[n12] - C[n12]);
                                    double d13 = C[n12] + d9 * (HI[n12] - C[n12]);
                                    d6 = Math.min(d6, Math.min(d10, d11));
                                    d5 = Math.max(d5, Math.max(d10, d11));
                                    d4 = Math.min(d4, Math.min(d12, d13));
                                    d3 = Math.max(d3, Math.max(d12, d13));
                                    if (n8 == 0) continue;
                                }
                                if (n8 == 0) continue;
                            }
                            n10 = MathHelper.method_15357((double)(d6 - 1.0E-4));
                        }
                        n7 = MathHelper.method_15357((double)(d5 + 1.0E-4));
                        d = d4;
                        d2 = 1.0E-4;
                    }
                    n6 = MathHelper.method_15357((double)(d - d2));
                    n5 = MathHelper.method_15357((double)(d3 + 1.0E-4));
                    n9 = n7 - n10;
                    if (n8 != 0) break block25;
                    if ((long)(n9 + 1) * (long)(n5 - n6 + 1) <= Occlusion.a(11386, 7782801472298029807L)) break block26;
                    return false;
                }
                Occlusion.CELL[n] = n2;
                n9 = n4 = n10;
            }
            while (n4 <= n7) {
                block29: {
                    n3 = n6;
                    if (n8 != 0) break block27;
                    int n13 = n3;
                    while (n13 <= n5) {
                        block28: {
                            Occlusion.CELL[n11] = n4;
                            if (n8 != 0) break block28;
                            Occlusion.CELL[n12] = n13++;
                            if (n8 != 0) break block29;
                            if (world.method_8320((BlockPos)POS.method_10103(CELL[0], CELL[1], CELL[2])).method_26216()) break block28;
                            return false;
                        }
                        if (n8 == 0) continue;
                    }
                    ++n4;
                }
                if (n8 == 0) continue;
            }
            n3 = 1;
        }
        return n3 != 0;
    }

    private static boolean cameraClips(World world, double d, double d2, double d3) {
        int n;
        block37: {
            World world2;
            int n2;
            long l;
            block31: {
                block32: {
                    double d4;
                    block36: {
                        double d5;
                        double d6;
                        block35: {
                            block34: {
                                long l2;
                                block33: {
                                    l = world.method_75260();
                                    n2 = Tweaks.a;
                                    world2 = world;
                                    if (n2 != 0) break block31;
                                    if (world2 != clipWorld.get()) break block32;
                                    long l3 = l - clipAt;
                                    l2 = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                                    if (n2 != 0) break block33;
                                    if (l2 != false) break block32;
                                    d6 = d;
                                    d5 = clipX;
                                    if (n2 != 0) break block34;
                                    double d7 = d6 - d5;
                                    l2 = d7 == 0.0 ? 0 : (d7 > 0.0 ? 1 : -1);
                                }
                                if (l2 != false) break block32;
                                d6 = d2;
                                d5 = clipY;
                            }
                            if (n2 != 0) break block35;
                            if (d6 != d5) break block32;
                            d6 = d3;
                            d5 = clipZ;
                        }
                        double d8 = d6 - d5;
                        d4 = d8 == 0.0 ? 0 : (d8 > 0.0 ? 1 : -1);
                        if (n2 != 0) break block36;
                        if (d4 != false) break block32;
                        d4 = (double)clips;
                    }
                    return (boolean)d4;
                }
                world2 = world;
            }
            if (world2 != clipWorld.get()) {
                clipWorld = new WeakReference<World>(world);
            }
            clipAt = l;
            clipX = d;
            clipY = d2;
            clipZ = d3;
            clips = false;
            for (int i = MathHelper.method_15357((double)(d - 0.28)); i <= MathHelper.method_15357((double)(d + 0.28)); ++i) {
                double d9 = d2;
                double d10 = 0.28;
                block27: while (true) {
                    n = MathHelper.method_15357((double)(d9 - d10));
                    if (n2 != 0) break block37;
                    int n3 = n;
                    while (n3 <= MathHelper.method_15357((double)(d2 + 0.28))) {
                        block38: {
                            d9 = d3;
                            d10 = 0.28;
                            if (n2 != 0) continue block27;
                            for (int j = MathHelper.method_15357((double)((v1393520) - (v1393521))); j <= MathHelper.method_15357((double)(d3 + 0.28)); ++j) {
                                boolean bl;
                                block39: {
                                    if (n2 != 0) break block38;
                                    bl = world.method_8320((BlockPos)POS.method_10103(i, n3, j)).method_26216();
                                    if (n2 != 0) break block39;
                                    if (!bl) continue;
                                    bl = clips = true;
                                }
                                return bl;
                            }
                            ++n3;
                        }
                        if (n2 == 0) continue;
                    }
                    break;
                }
                if (n2 == 0) continue;
            }
            n = 0;
        }
        return n != 0;
    }

    
    static {
        long l;
        long l2 = 5669113222107879293L;
        long l3 = l = 0x43B15E957335D73DL ^ l2;
        MAX_CELLS = (int)l3;
        MAX_LAYERS = (int)l3;
        long l4 = 2481206286006820247L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "%\u0001\u009b \u0018\u00d4\u00e0#Nm\u0013L\u00a2Q\u000f8\u0004\u00eb3=\u0088\u00a9a\u00bb";
        int n2 = "%\u0001\u009b \u0018\u00d4\u00e0#Nm\u0013L\u00a2Q\u000f8\u0004\u00eb3=\u0088\u00a9a\u00bb".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l4;
        } while (n3 < n2);
        a = lArray;
        b = new Long[3];
        SEEN = new WeakHashMap();
        POS = new BlockPos.Mutable();
        C = new double[3];
        LO = new double[3];
        HI = new double[3];
        CELL = new int[3];
        clipX = Double.NaN;
        clipWorld = new WeakReference<Object>(null);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x3E94) & Short.MAX_VALUE;
        if (b[n2] == null) {
            Occlusion.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
