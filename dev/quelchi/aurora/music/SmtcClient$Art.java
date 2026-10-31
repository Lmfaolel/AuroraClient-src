package dev.quelchi.aurora.music;

import dev.quelchi.aurora.music.Lyrics;

public record SmtcClient$Art(String key, int[] argb, int w, int h) {
    public int[] scaled(int n, int n2) {
        int[] nArray = new int[n * n2];
        int n3 = 0;
        boolean bl = Lyrics.a;
        while (n3 < n2) {
            block9: {
                int n4 = n3 * this.h / n2;
                int n5 = Math.max(n4 + 1, (n3 + 1) * this.h / n2);
                for (int i = 0; i < n; ++i) {
                    int n6;
                    block13: {
                        int n7;
                        long l;
                        long l2;
                        block11: {
                            int n8;
                            long l3;
                            long l4;
                            block12: {
                                block10: {
                                    int n9 = i * this.w / n;
                                    if (bl) break block9;
                                    int n10 = Math.max(n9 + 1, (i + 1) * this.w / n);
                                    l2 = 0L;
                                    l4 = 0L;
                                    l3 = 0L;
                                    l = 0L;
                                    n8 = n4;
                                    block6: while (true) {
                                        int n11 = n8;
                                        block7: while (n11 < n5) {
                                            if (bl) break block10;
                                            for (int j = n9; j < n10; ++j) {
                                                int n12;
                                                n11 = n12 = this.argb[n8 * this.w + j];
                                                if (bl) continue block7;
                                                int n13 = n11 >>> 24;
                                                l2 += (long)n13;
                                                l4 += (long)((n12 >> 16 & 0xFF) * n13);
                                                l3 += (long)((n12 >> 8 & 0xFF) * n13);
                                                l += (long)((n12 & 0xFF) * n13);
                                                if (!bl) continue;
                                            }
                                            ++n8;
                                            if (!bl) continue block6;
                                        }
                                        break;
                                    }
                                    n8 = (n5 - n4) * (n10 - n9);
                                }
                                int[] nArray2 = nArray;
                                int n14 = n3;
                                n7 = n;
                                if (bl) break block11;
                                n14 = n14 * n7 + i;
                                if (l2 != 0L) break block12;
                                n6 = 0;
                                break block13;
                            }
                            n7 = (int)(l2 / (long)n8) << 24 | (int)(l4 / l2) << 16 | (int)(l3 / l2) << 8;
                        }
                        n6 = n7 | (int)(l / l2);
                    }
                    nArray2[n14] = n6;
                    if (!bl) continue;
                }
                ++n3;
            }
            if (!bl) continue;
        }
        return nArray;
    }
}
