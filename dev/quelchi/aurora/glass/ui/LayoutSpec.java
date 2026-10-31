package dev.quelchi.aurora.glass.ui;

import dev.quelchi.aurora.glass.ui.Insets;
import dev.quelchi.aurora.glass.ui.Length;
import dev.quelchi.aurora.glass.ui.UiBounds;
import dev.quelchi.aurora.module.Module;

public final class LayoutSpec {
    private final Runnable dirty;
    private Length width;
    private Length height;
    private Insets margin;
    private Insets padding;
    private int maxWidth;
    private int maxHeight;
    private float shrink;
    private int x;
    private int y;
    private static final long[] a;
    private static final Integer[] b;

    public LayoutSpec() {
        this(LayoutSpec::lambda$new$0);
    }

    LayoutSpec(Runnable runnable) {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    boolean bl2 = UiBounds.a;
                    this.width = Length.AUTO;
                    this.height = Length.AUTO;
                    this.margin = Insets.ZERO;
                    this.padding = Insets.ZERO;
                    this.maxWidth = LayoutSpec.a(19156, 8304739662665248794L);
                    this.maxHeight = LayoutSpec.a(10272, 1647243292065436399L);
                    this.shrink = 1.0f;
                    this.dirty = runnable;
                    if (!Module.a) break block4;
                    if (!bl2) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            UiBounds.a = bl;
        }
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    private static void lambda$new$0() {
    }

    
    static {
        long l = 8913809230510815470L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "4U\u00a1]\u00fa\u008cw\u000bwQ\u00db\u00ed\u0001\u00fa\u0095\u00fe";
        int n2 = "4U\u00a1]\u00fa\u008cw\u000bwQ\u00db\u00ed\u0001\u00fa\u0095\u00fe".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Integer[2];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3ACE;
        if (b[n2] == null) {
            LayoutSpec.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
