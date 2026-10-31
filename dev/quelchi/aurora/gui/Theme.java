package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.module.Module;

public final class Theme {
    public static final int WINDOW;
    public static final int SIDEBAR;
    public static final int CARD;
    public static final int DIVIDER;
    public static final int PANEL;
    public static final int ACCENT = -1;
    public static final int DANGER;
    public static final int OK;
    public static final int WARN;
    public static final int TEXT;
    public static final int TEXT_SUB;
    public static final int TEXT_MUTE;
    public static final int SEL_EDGE;
    public static final int KNOB;
    public static final int R_CTRL;
    public static final int R_CARD;
    public static final int R_PANEL;
    private static final long[] a;
    private static final Integer[] b;

    private Theme() {
    }

    public static int mix(int n, int n2, float f) {
        int n3;
        block16: {
            boolean bl;
            block18: {
                block17: {
                    float f2;
                    int n4;
                    block14: {
                        block15: {
                            block12: {
                                block13: {
                                    n4 = PreviewScene.a;
                                    f2 = f;
                                    if (n4 != 0) break block12;
                                    if (!(f2 < 0.0f)) break block13;
                                    f2 = 0.0f;
                                    break block14;
                                }
                                f2 = f;
                            }
                            if (n4 != 0) break block14;
                            if (!(f2 > 1.0f)) break block15;
                            f2 = 1.0f;
                            break block14;
                        }
                        f2 = f;
                    }
                    f = f2;
                    int n5 = n >>> 24 & 0xFF;
                    int n6 = n >>> 16 & 0xFF;
                    int n7 = n >>> 8 & 0xFF;
                    int n8 = n & 0xFF;
                    int n9 = n2 >>> 24 & 0xFF;
                    int n10 = n2 >>> 16 & 0xFF;
                    int n11 = n2 >>> 8 & 0xFF;
                    int n12 = n2 & 0xFF;
                    int n13 = Math.round((float)n5 + (float)(n9 - n5) * f);
                    int n14 = Math.round((float)n6 + (float)(n10 - n6) * f);
                    int n15 = Math.round((float)n7 + (float)(n11 - n7) * f);
                    int n16 = Math.round((float)n8 + (float)(n12 - n8) * f);
                    n3 = n13 << 24 | n14 << 16 | n15 << 8 | n16;
                    if (n4 == 0) break block16;
                    if (!Module.a) break block17;
                    bl = false;
                    break block18;
                }
                bl = true;
            }
            Module.a = bl;
        }
        return n3;
    }

    public static int alpha(int n, int n2) {
        return Math.min(255, Math.max(0, n2)) << 24 | n & Theme.a(2044, 6067416154232459504L);
    }

    
    static {
        block8: {
            block7: {
                var0 = 9207719309799689598L;
                var6_1 = new long[15];
                var3_2 = 0;
                var4_3 = "m\u009d\u00efO\u00fd\u00f1\u00c2\u00c4\u00fc\u0094\u00fb\u00d6\r_C\u0094,\u0017\u00c6\u0094\u00f8\u0007s\u00d5\u0096\u0093\u00b1!\u0001\u0084\u0011e\u0082\u00d5W\u00c6\u00ea\u0004\u00e5\u0004\u0000\u00ad\u00f0\u00eb\u00b3V;[\u00dd\u009a\u0004\u009d\u0002H9\u00f8\u0091/?\u0003\u00a3\u00f3l L\u001d\u00e0Q\u0017\u0091\u0094\u0001\u00c7\u00f3\u00e9\u00c2\u0086\u0010\u00c2\u00e4\u00c4\u00b8\f.d;\u00d4\u00f4\u00a7\u00fb\u00c7\u0004'\u00dd;\u001f\u0095\u00b8\to\u00c6\u00db\u00b4\u00e4";
                var5_4 = "m\u009d\u00efO\u00fd\u00f1\u00c2\u00c4\u00fc\u0094\u00fb\u00d6\r_C\u0094,\u0017\u00c6\u0094\u00f8\u0007s\u00d5\u0096\u0093\u00b1!\u0001\u0084\u0011e\u0082\u00d5W\u00c6\u00ea\u0004\u00e5\u0004\u0000\u00ad\u00f0\u00eb\u00b3V;[\u00dd\u009a\u0004\u009d\u0002H9\u00f8\u0091/?\u0003\u00a3\u00f3l L\u001d\u00e0Q\u0017\u0091\u0094\u0001\u00c7\u00f3\u00e9\u00c2\u0086\u0010\u00c2\u00e4\u00c4\u00b8\f.d;\u00d4\u00f4\u00a7\u00fb\u00c7\u0004'\u00dd;\u001f\u0095\u00b8\to\u00c6\u00db\u00b4\u00e4".length();
                var2_5 = 0;
                while (true) {
                    var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                    v0 = var6_1;
                    v1 = var3_2++;
                    v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                    v3 = -1;
                    break block7;
                    break;
                }
lbl14:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    var4_3 = "Y\u0084$W\u00fd\u009cbq\u00ffw={m\b$\u00ee";
                    var5_4 = "Y\u0084$W\u00fd\u009cbq\u00ffw={m\b$\u00ee".length();
                    var2_5 = 0;
                    while (true) {
                        var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                        v0 = var6_1;
                        v1 = var3_2++;
                        v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                        v3 = 0;
                        break block7;
                        break;
                    }
                    break;
                }
lbl27:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    break block8;
                    break;
                }
            }
            v4 = v2 ^ var0;
            switch (v3) {
                default: {
                    continue;
                }
                case 0:
lbl38:

                continue;
            }
        }
        Theme.a = var6_1;
        Theme.b = new Integer[15];
        Theme.DIVIDER = Theme.a(4292, 4349682109676160973L);
        Theme.KNOB = Theme.a(8566, 3277132717288568445L);
        Theme.TEXT_SUB = Theme.a(26553, 5141467715994677427L);
        Theme.OK = Theme.a(14360, 8231651035893378832L);
        Theme.WARN = Theme.a(17987, 9004204826794512710L);
        Theme.R_CARD = Theme.a(22709, 5539792551641108404L);
        Theme.WINDOW = Theme.a(7853, 7460397566542023072L);
        Theme.SIDEBAR = Theme.a(22, 5153378886713841428L);
        Theme.PANEL = Theme.a(9611, 4376066103828329100L);
        Theme.R_CTRL = Theme.a(23010, 7637545279847321314L);
        Theme.TEXT_MUTE = Theme.a(15755, 5055518164270328463L);
        Theme.R_PANEL = Theme.a(22709, 5539792551641108404L);
        Theme.CARD = Theme.a(27771, 1619905421895760756L);
        Theme.DANGER = Theme.a(7475, 3800773808110864949L);
        Theme.TEXT = Theme.a(6751, 2796659663287553372L);
        Theme.SEL_EDGE = Theme.alpha(-1, 110);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1701;
        if (b[n2] == null) {
            Theme.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
