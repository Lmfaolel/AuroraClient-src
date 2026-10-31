package dev.quelchi.aurora.perf;

import dev.quelchi.aurora.perf.Tweaks;
import java.util.ArrayDeque;
import net.fabricmc.loader.api.FabricLoader;
import org.lwjgl.opengl.GL32;

public final class FrameQueue {
    private static final boolean GL;
    private static final long WAIT_NS;
    private static final ArrayDeque<Long> FENCES;
    private static final long a;
    private static final long[] b;
    private static final Long[] c;

    private FrameQueue() {
    }

    public static void swapped() {
        boolean bl;
        block5: {
            block6: {
                int n = Tweaks.a;
                bl = GL;
                if (n != 0) break block5;
                if (bl) break block6;
                return;
            }
            bl = Tweaks.lowLatency();
        }
        if (!bl) {
            FrameQueue.clear();
            return;
        }
        FENCES.addLast(GL32.glFenceSync((int)((int)a), (int)0));
    }

    public static void beforeFrame() {
        int n = Tweaks.a;
        while (FENCES.size() > 1) {
            long l = FENCES.removeFirst();
            GL32.glClientWaitSync((long)l, (int)1, (long)FrameQueue.a(18570, 1200646933550290033L));
            GL32.glDeleteSync((long)l);
            if (n == 0) continue;
        }
    }

    private static void clear() {
        int n = Tweaks.a;
        while (!FENCES.isEmpty()) {
            GL32.glDeleteSync((long)FENCES.removeFirst());
            if (n == 0) continue;
        }
    }

    
    static {
        char[] cArray;
        block13: {
            int n;
            int n2;
            block12: {
                char[] cArray2 = "c[%\"w\u00184zJ".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block12;
                n2 = n2;
                if (n2 <= n) break block13;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 21;
                    case 1 -> 46;
                    case 2 -> 73;
                    case 3 -> 73;
                    case 4 -> 22;
                    case 5 -> 118;
                    default -> 89;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        String string = new String(cArray).intern();
        long l = 488578942717506160L;
        a = 0x27CB4723B5692367L ^ l;
        long l2 = 5511416784018443962L;
        long[] lArray = new long[2];
        int n = 0;
        String string2 = "Y\u008d\u00dd\u00fbJ\u00a8\u00f1\u009e\\\u00d5\u00f2[\u0010\u009c*K";
        int n4 = "Y\u008d\u00dd\u00fbJ\u00a8\u00f1\u009e\\\u00d5\u00f2[\u0010\u009c*K".length();
        int n5 = 0;
        do {
            byte[] byArray = string2.substring(n5, n5 += 8).getBytes("ISO-8859-1");
            int n6 = n++;
            lArray[n6] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l2;
        } while (n5 < n4);
        b = lArray;
        c = new Long[2];
        WAIT_NS = FrameQueue.a(4958, 1581222678735982500L);
        GL = !FabricLoader.getInstance().isModLoaded(string);
        FENCES = new ArrayDeque();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x1CFA) & Short.MAX_VALUE;
        if (c[n2] == null) {
            FrameQueue.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
