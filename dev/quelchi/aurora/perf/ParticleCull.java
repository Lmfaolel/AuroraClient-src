package dev.quelchi.aurora.perf;

import dev.quelchi.aurora.perf.Occlusion;
import dev.quelchi.aurora.perf.Tweaks;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import java.lang.ref.WeakReference;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public final class ParticleCull {
    private static final Long2LongOpenHashMap CELLS;
    private static WeakReference<World> world;
    private static long cameraCell;
    private static final long[] a;
    private static final Long[] b;

    private ParticleCull() {
    }

    
    public static boolean hidden(double var0, double var2_2, double var4_1) {
        block42: {
            block43: {
                block38: {
                    block39: {
                        block40: {
                            block41: {
                                block36: {
                                    block37: {
                                        block34: {
                                            block35: {
                                                var7_3 = MinecraftClient.method_1551();
                                                var8_4 = var7_3.field_1687;
                                                var6_5 = Tweaks.a;
                                                if (var8_4 == null) {
                                                    return false;
                                                }
                                                var9_6 = var7_3.field_1773.method_19418().method_71156();
                                                var10_7 = BlockPos.method_10064((int)MathHelper.method_15357((double)var9_6.field_1352), (int)MathHelper.method_15357((double)var9_6.field_1351), (int)MathHelper.method_15357((double)var9_6.field_1350));
                                                v1 = var8_4;
                                                v2 /* !! */  = ParticleCull.world;
                                                if (var6_5 != 0) break block34;
                                                if (v1 == v2 /* !! */ .get()) {
                                                }
                                                cfr_temp_0 = var10_7 - ParticleCull.cameraCell;
                                                v4 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                                if (var6_5 != 0) break block35;
                                                if (v4 == false) {
                                                }
                                                v4 = ParticleCull.CELLS.size();
                                            }
                                            if (var6_5 != 0) break block36;
                                            if (v4 <= 8192) break block37;
lbl47:

                                            ParticleCull.CELLS.clear();
                                            v1 = var8_4;
                                            v2 /* !! */  = ParticleCull.world.get();
                                        }
                                        if (v1 != v2 /* !! */ ) {
                                            ParticleCull.world = new WeakReference<ClientWorld>(var8_4);
                                        }
                                        ParticleCull.cameraCell = var10_7;
                                    }
                                    v4 = MathHelper.method_15357((double)var0);
                                }
                                var12_8 = v4;
                                var13_9 = MathHelper.method_15357((double)var2_2);
                                var14_10 = MathHelper.method_15357((double)var4_1);
                                var15_11 = BlockPos.method_10064((int)var12_8, (int)var13_9, (int)var14_10);
                                var17_12 = var8_4.method_75260();
                                var19_13 = ParticleCull.CELLS.get(var15_11);
                                cfr_temp_1 = var19_13 - 0L;
                                v11 = cfr_temp_1 == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1);
                                if (var6_5 != 0) break block38;
                                if (v11 == false) break block39;
                                cfr_temp_2 = (var19_13 >>> 1) - var17_12;
                                v11 = cfr_temp_2 == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1);
                                if (var6_5 != 0) break block38;
                                if (v11 <= 0) break block39;
                                cfr_temp_3 = (var19_13 & 1L) - 0L;
                                v15 = cfr_temp_3 == 0L ? 0 : (cfr_temp_3 < 0L ? -1 : 1);
                                if (var6_5 != 0) break block40;
                                if (v15 == false) break block41;
                                v15 = 1;
                                break block40;
                            }
                            v15 = 0;
                        }
                        return (boolean)v15;
                    }
                    v11 = (long)Occlusion.hiddenNow((World)var8_4, var9_6.field_1352, var9_6.field_1351, var9_6.field_1350, (double)var12_8 - 0.5, (double)var13_9 - 0.5, (double)var14_10 - 0.5, (double)var12_8 + 1.5, (double)var13_9 + 1.5, (double)var14_10 + 1.5);
                }
                var21_14 = v11;
                v18 = ParticleCull.CELLS;
                v19 = var15_11;
                v20 = var17_12 + ParticleCull.a(9046, 7097903917161172033L);
                v21 = 1;
                if (var6_5 != 0) break block42;
                v20 = v20 << v21;
                if (var21_14 == false) break block43;
                v21 = 1;
                break block42;
            }
            v21 = 0;
        }
        v18.put(v19, v20 | (long)v21);
        return (boolean)var21_14;
    }

    
    static {
        long l = 4475805632754179321L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "\u00f2a\u00054W\u00d1/^\\\u009d\u0093\u0083\u00f8?\u0090\u00ba";
        int n2 = "\u00f2a\u00054W\u00d1/^\\\u009d\u0093\u0083\u00f8?\u0090\u00ba".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Long[2];
        CELLS = new Long2LongOpenHashMap();
        world = new WeakReference<Object>(null);
        cameraCell = ParticleCull.a(7345, 5511355592734700455L);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x6F16) & Short.MAX_VALUE;
        if (b[n2] == null) {
            ParticleCull.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
