package dev.quelchi.aurora.duck;

import dev.quelchi.aurora.gui.im.Im;
import net.minecraft.client.gui.ScreenRect;

public interface GuiLayerMask {
    public static final long[] a;
    public static final Long[] b;

    public long aurora$mask();

    public Object aurora$hit(ScreenRect var1);

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static long of(ScreenRect screenRect) {
        long l;
        ScreenRect screenRect2;
        boolean bl;
        block16: {
            block17: {
                block15: {
                    block13: {
                        block14: {
                            bl = Im.a;
                            screenRect2 = screenRect;
                            if (bl) break block13;
                            if (screenRect2 != null) break block14;
                            return 0L;
                        }
                        screenRect2 = screenRect;
                    }
                    if (bl) break block15;
                    if (screenRect2.comp_1196() < 0) return GuiLayerMask.a(31141, 7070982790218641865L);
                    screenRect2 = screenRect;
                }
                if (bl) break block16;
                if (screenRect2.comp_1197() >= 0) break block17;
                return GuiLayerMask.a(31141, 7070982790218641865L);
            }
            screenRect2 = screenRect;
        }
        int n = GuiLayerMask.cell(screenRect2.method_49620() >> 7);
        int n2 = GuiLayerMask.cell(screenRect.method_49620() + Math.max(0, screenRect.comp_1196() - 1) >> 7);
        int n3 = GuiLayerMask.cell(screenRect.method_49618() >> 6);
        int n4 = GuiLayerMask.cell(screenRect.method_49618() + Math.max(0, screenRect.comp_1197() - 1) >> 6);
        long l2 = GuiLayerMask.a(814, 5561758721599179584L) << n & GuiLayerMask.a(302, 4221096330214738241L) >>> 7 - n2 & GuiLayerMask.a(302, 4221096330214738241L);
        long l3 = 0L;
        for (int i = n3; i <= n4; ++i) {
            l = l3;
            if (bl) return l;
            l3 = l | l2 << i * 8;
            if (!bl) continue;
        }
        l = l3;
        return l;
    }

    private static int cell(int n) {
        int n2;
        block6: {
            int n3;
            block4: {
                block5: {
                    boolean bl = Im.a;
                    n3 = n;
                    if (bl) break block4;
                    if (n3 >= 0) break block5;
                    n2 = 0;
                    break block6;
                }
                n3 = n;
            }
            n2 = Math.min(n3, 7);
        }
        return n2;
    }

    
    static {
        long l = 493750378694046665L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "K\u00f5qL\u0086\u009cxv<N\u007f\u00e5\u0004\u00e2zw\u009b\u0004\u00f5\u00c7\u00c9\u00db\u00fd\u00ff";
        int n2 = "K\u00f5qL\u0086\u009cxv<N\u007f\u00e5\u0004\u00e2zw\u009b\u0004\u00f5\u00c7\u00c9\u00db\u00fd\u00ff".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Long[3];
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x86E) & Short.MAX_VALUE;
        if (b[n2] == null) {
            GuiLayerMask.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
