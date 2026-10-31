package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.gui.HudScale;
import dev.quelchi.aurora.module.DraggableHud;

final class MusicPlayerModule$LyricsBar
implements DraggableHud {
    int dx;
    int y = MusicPlayerModule$LyricsBar.a(5154, 2715399539921214803L);
    int[] bounds = new int[]{0, 4, 0, 0};
    private static final String a;
    private static final long[] b;
    private static final Integer[] c;

    private MusicPlayerModule$LyricsBar() {
    }

    @Override
    public int getHudX() {
        return HudScale.width() / 2 + this.dx;
    }

    @Override
    public int getHudY() {
        return this.y;
    }

    @Override
    public void setHudPos(int n, int n2) {
        this.dx = n - HudScale.width() / 2;
        this.y = n2;
    }

    @Override
    public int[] getLastBounds() {
        return this.bounds;
    }

    @Override
    public String hudKey() {
        return a;
    }

    @Override
    public void resetHudPos() {
        this.dx = 0;
        this.y = MusicPlayerModule$LyricsBar.a(6719, 773286362679563087L);
        this.setHudScale(1.0f);
        this.setHudHidden(false);
    }

    
    static {
        char[] cArray;
        block12: {
            int n;
            int n2;
            block11: {
                char[] cArray2 = "Xf\u0010f_7 \u0013f\fyS:'".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block11;
                n2 = n2;
                if (n2 <= n) break block12;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 61;
                    case 1 -> 10;
                    case 2 -> 117;
                    case 3 -> 11;
                    case 4 -> 58;
                    case 5 -> 89;
                    default -> 84;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
        long l = 3065065912379358923L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "\u00d3\u0007\u00f9\u00ca\u00b5\u00ac\u00ed\u0080U\u00ba\u00f8\u00ce\u0090\u00c4\u00e3\u009c";
        int n4 = "\u00d3\u0007\u00f9\u00ca\u00b5\u00ac\u00ed\u0080U\u00ba\u00f8\u00ce\u0090\u00c4\u00e3\u009c".length();
        int n5 = 0;
        do {
            byte[] byArray = string.substring(n5, n5 += 8).getBytes("ISO-8859-1");
            int n6 = n++;
            lArray[n6] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n5 < n4);
        b = lArray;
        c = new Integer[2];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4970;
        if (c[n2] == null) {
            MusicPlayerModule$LyricsBar.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
