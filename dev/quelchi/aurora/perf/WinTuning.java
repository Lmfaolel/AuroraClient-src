package dev.quelchi.aurora.perf;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.perf.Tweaks;
import dev.quelchi.aurora.perf.WinTuning$Kernel32;
import dev.quelchi.aurora.perf.WinTuning$Winmm;
import net.minecraft.util.Util;

public final class WinTuning {
    private static final int THREAD_PRIORITY_ABOVE_NORMAL = 1;
    private static final int PROCESS_POWER_THROTTLING;
    private static final int THROTTLE_EXECUTION_SPEED = 1;
    private static final int THROTTLE_IGNORE_TIMER_RESOLUTION;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private WinTuning() {
    }

    
    public static void apply() {
        block6: {
            block7: {
                block8: {
                    var0 = Tweaks.a;
                    if (Util.method_668() != Util.OperatingSystem.field_1133) break block7;
                    v0 = Tweaks.WIN_TUNING;
                    if (var0 != 0) // goto
                    break block8;
                    catch (Throwable v1) {
                        throw v1;
                    }
                }
                if (v0 != 0) break block6;
            }
            return;
        }
        try {
            var3_1 = WinTuning.a;
            v0 = ((WinTuning$Winmm)Native.load((String)var3_1[0], WinTuning$Winmm.class)).timeBeginPeriod(1);
lbl21:

            var3_1 = WinTuning.a;
            var1_2 = (WinTuning$Kernel32)Native.load((String)var3_1[2], WinTuning$Kernel32.class);
            var1_2.SetThreadPriority(var1_2.GetCurrentThread(), 1);
            var2_4 = new Memory(WinTuning.a(13688, 8552773920941601020L));
            var2_4.setInt(0L, 1);
            var2_4.setInt(WinTuning.a(28585, 50639039336786478L), 5);
            var2_4.setInt(WinTuning.a(9920, 6473122208382477126L), 0);
            var1_2.SetProcessInformation(var1_2.GetCurrentProcess(), 4, (Pointer)var2_4, 12);
        }
        catch (Throwable var1_3) {
            Aurora.LOG.warn(WinTuning.a[1], (Object)var1_3.toString());
        }
    }

    
    static {
        block17: {
            block16: {
                block18: {
                    block15: {
                        var17 = new String[3];
                        var15_1 = 0;
                        var14_2 = "_\u001daHi\u001a_\u001daAk\u0003\f\b\u0000zKm\u001a\u0018\b\u0007dLt\u0004\u001aLN/^y\bC\u0011}Ka\u0018L\u001a";
                        var16_3 = "_\u001daHi\u001a_\u001daAk\u0003\f\b\u0000zKm\u001a\u0018\b\u0007dLt\u0004\u001aLN/^y\bC\u0011}Ka\u0018L\u001a".length();
                        var13_4 = 5;
                        var12_5 = -1;
lbl7:

                        while (true) {
                            continue;
                            break;
                        }
lbl9:

                        while (true) {
                            var17[var15_1++] = new String(v0).intern();
                            if ((var12_5 += var13_4) < var16_3) {
                                var13_4 = var14_2.charAt(var12_5);
                                continue;
                            }
                            break block15;
                            break;
                        }
                        v1 = ++var12_5;
                        v2 = var14_2.substring(v1, v1 + var13_4).toCharArray();
                        v0 = v2;
                        v3 = v2.length;
                        var18_6 = 0;
                        do {
                            v0 = v0;
                            v4 = var18_6;
                            v5 = v0[v4];
                            switch (var18_6 % 7) {
                                case 0: {
                                    v6 = 40;
                                    break;
                                }
                                case 1: {
                                    v6 = 116;
                                    break;
                                }
                                case 2: {
                                    v6 = 15;
                                    break;
                                }
                                case 3: {
                                    v6 = 37;
                                    break;
                                }
                                case 4: {
                                    v6 = 4;
                                    break;
                                }
                                case 5: {
                                    v6 = 116;
                                    break;
                                }
                                default: {
                                    v6 = 127;
                                }
                            }
                            v0[v4] = (char)(v5 ^ v6);
                            ++var18_6;
lbl48:

                            v3 = v3;
                        } while (v3 > var18_6);
                        while (true)
                    }
                    WinTuning.a = var17;
                    break block18;
lbl56:

                    while (true) {
                        continue;
                        break;
                    }
                }
                var10_7 = 4903709998318049475L;
                while (true)
                v7 = var8_8 = -87604631759791929L ^ var10_7;
                WinTuning.PROCESS_POWER_THROTTLING = (int)v7;
                WinTuning.THROTTLE_IGNORE_TIMER_RESOLUTION = (int)v7;
                var0_9 = 4744502834682812226L;
                var6_10 = new long[3];
                var3_11 = 0;
                var4_12 = "\u0018\u0002\u00faj\u00ebN4\fAd;\u00c0\u00ceu\u00fdh7fR\u00a1\u00d8\u00e8\u00a7\u00b2";
                var5_13 = "\u0018\u0002\u00faj\u00ebN4\fAd;\u00c0\u00ceu\u00fdh7fR\u00a1\u00d8\u00e8\u00a7\u00b2".length();
                var2_14 = 0;
                while (true) {
                    break block16;
                    break;
                }
lbl76:

                while (true) {
                    var6_10[v8] = (((long)var7_15[0] & 255L) << 56 | ((long)var7_15[1] & 255L) << 48 | ((long)var7_15[2] & 255L) << 40 | ((long)var7_15[3] & 255L) << 32 | ((long)var7_15[4] & 255L) << 24 | ((long)var7_15[5] & 255L) << 16 | ((long)var7_15[6] & 255L) << 8 | (long)var7_15[7] & 255L) ^ var0_9;
                    if (var2_14 < var5_13) continue;
                    break block17;
                    break;
                }
            }
            var7_15 = var4_12.substring(var2_14, var2_14 += 8).getBytes("ISO-8859-1");
            v8 = var3_11++;
            while (true)
        }
        WinTuning.b = var6_10;
        WinTuning.c = new Long[3];
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x4586) & Short.MAX_VALUE;
        if (c[n2] == null) {
            WinTuning.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
