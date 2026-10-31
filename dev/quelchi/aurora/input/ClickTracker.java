package dev.quelchi.aurora.input;

import dev.quelchi.aurora.input.FreeLook;
import java.util.ArrayDeque;
import java.util.Deque;

public final class ClickTracker {
    private static final long WINDOW_MS;
    private static final Deque<Long> left;
    private static final Deque<Long> right;
    private static final long[] a;
    private static final Long[] b;

    private ClickTracker() {
    }

    
    public static void onPress(int n) {
        Deque<Long> deque2 = ClickTracker.queueFor(n);
        int n2 = FreeLook.a;
        Deque<Long> deque = deque2;
        if (n2 == 0) {
            if (deque == null) {
                return;
            }
            deque = deque2;
        }
        Deque<Long> deque3 = deque;
        synchronized (deque) {
            Deque<Long> deque4;
            long l = System.currentTimeMillis();
            deque2.addLast(l);
            long l2 = l - ClickTracker.a(25261, 4893063350341377395L);
            while (!deque2.isEmpty()) {
                deque4 = deque2;
                if (n2 != 0) return;
                if (n2 != 0) return;
                if (deque4.peekFirst() >= l2) break;
                deque2.pollFirst();
                if (n2 == 0) continue;
            }
            deque4 = deque3;
            return;
        }
    }

    
    public static int cps(int n) {
        Deque<Long> deque = ClickTracker.queueFor(n);
        int n2 = FreeLook.a;
        if (deque == null) {
            return 0;
        }
        long l = System.currentTimeMillis() - ClickTracker.a(15457, 9106381290118038461L);
        Deque<Long> deque2 = deque;
        synchronized (deque2) {
            Deque<Long> deque3;
            block9: {
                while (!deque.isEmpty()) {
                    deque3 = deque;
                    if (n2 == 0 && n2 == 0) {
                        if (deque3.peekFirst() >= l) break;
                        deque.pollFirst();
                        if (n2 == 0) continue;
                    }
                    break block9;
                }
                deque3 = deque;
            }
            return deque3.size();
        }
    }

    private static Deque<Long> queueFor(int n) {
        int n2;
        block7: {
            block8: {
                int n3 = FreeLook.a;
                n2 = n;
                if (n3 != 0) break block7;
                if (n2 != 0) break block8;
                return left;
            }
            n2 = n;
        }
        if (n2 == 1) {
            return right;
        }
        return null;
    }

    
    static {
        long l = 6438329484124276941L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "\u001a\u00be-\u00bc\u0094\u00b2VVH\u00cb\u0085\u00dd\u00ac\t\u0011a'9\u00d7\u0004\u00fa\u0099\b\u0098";
        int n2 = "\u001a\u00be-\u00bc\u0094\u00b2VVH\u00cb\u0085\u00dd\u00ac\t\u0011a'9\u00d7\u0004\u00fa\u0099\b\u0098".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Long[3];
        WINDOW_MS = ClickTracker.a(9627, 1266091283465624132L);
        left = new ArrayDeque<Long>();
        right = new ArrayDeque<Long>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x7FDE) & Short.MAX_VALUE;
        if (b[n2] == null) {
            ClickTracker.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
