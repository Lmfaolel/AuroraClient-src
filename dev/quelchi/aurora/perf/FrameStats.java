package dev.quelchi.aurora.perf;

import dev.quelchi.aurora.perf.Tweaks;
import java.util.Arrays;

public final class FrameStats {
    private static final int N;
    private static final long[] TIMES;
    private static final long[] SORT;
    private static int head;
    private static int count;
    private static long last;
    private static long lowAt;
    private static int low;
    private static final long a;

    private FrameStats() {
    }

    public static void frame() {
        block8: {
            long l;
            block9: {
                int n = Tweaks.a;
                l = System.nanoTime();
                if (n != 0) break block8;
                if (last == 0L) break block9;
                FrameStats.TIMES[FrameStats.head] = l - last;
                if (n != 0) break block8;
                head = head + 1 & 0x3FF;
                if (count >= 1024) break block9;
                ++count;
            }
            last = l;
        }
    }

    public static int onePercentLow() {
        long l;
        block12: {
            int n;
            block13: {
                block10: {
                    long l2;
                    block11: {
                        n = Tweaks.a;
                        l2 = System.nanoTime();
                        if (n != 0) break block10;
                        if (l2 - lowAt >= a) break block11;
                        return low;
                    }
                    lowAt = l2;
                    if (n != 0) break block13;
                }
                if (count < 100) {
                    low = 0;
                    return 0;
                }
                System.arraycopy(TIMES, 0, SORT, 0, count);
                Arrays.sort(SORT, 0, count);
            }
            int n2 = Math.max(1, count / 100);
            long l3 = 0L;
            for (int i = count - n2; i < count; ++i) {
                l = l3;
                if (n == 0) {
                    l3 = l + SORT[i];
                    if (n == 0) continue;
                }
                break block12;
            }
            l = Math.round(1.0E9 / ((double)l3 / (double)n2));
        }
        low = (int)l;
        return low;
    }

    
    static {
        long l = 520711828081400760L;
        long l2 = 0x7C6D429E1722DFB8L ^ l;
        N = (int)l2;
        long l3 = 5804335402794916772L;
        a = 0x508D244BEB4A51A4L ^ l3;
        TIMES = new long[1024];
        SORT = new long[1024];
    }
}
