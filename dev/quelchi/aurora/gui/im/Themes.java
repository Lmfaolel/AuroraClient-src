package dev.quelchi.aurora.gui.im;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Themes$Look;
import dev.quelchi.aurora.social.Plus;
import java.awt.Color;

public final class Themes {
    private static final float[][] TARGETS;
    private static final float[] INK_ON;
    private static final float[] DARK_ON;
    private static final float[][][] PALETTES;
    private static final float[][] GLASSY;
    private static final int[] SEE_THROUGH;
    private static final float[][] ENDER_ACCENT;
    private static final float[][] NETHER_ACCENT;
    private static final float[][] DEEP_DARK_ACCENT;
    private static final float[][] CHERRY_ACCENT;
    private static final float[][] VIOLET;
    private static final float[][][] ACCENTS;
    public static final String[] ACCENT_NAMES;
    public static final int[] PLAIN;
    public static final int[] PLUS;
    private static final int WHITE;
    private static final int DEFAULT_ACCENT = 0;
    private static final float[][] BLACK;
    private static Themes$Look look;
    private static Themes$Look chosen;
    private static int accent;
    private static float[] glow;
    private static float glass;
    private static int chosenAccent;
    public static final String[] SIZE_NAMES;
    private static final float[] SIZES;
    private static int size;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private Themes() {
    }

    private static float[] c(float f, float f2, float f3, float f4) {
        return new float[]{f, f2, f3, f4};
    }

    private static float[] rgb(int n) {
        return Themes.c((float)(n >> 16 & 0xFF) / 255.0f, (float)(n >> 8 & 0xFF) / 255.0f, (float)(n & 0xFF) / 255.0f, 1.0f);
    }

    private static float[] mixed(float[] fArray, float[] fArray2, float f) {
        return Themes.c(fArray[0] + (fArray2[0] - fArray[0]) * f, fArray[1] + (fArray2[1] - fArray[1]) * f, fArray[2] + (fArray2[2] - fArray[2]) * f, 1.0f);
    }

    private static float[] deep(float[] fArray) {
        if (0.2126f * fArray[0] + 0.7152f * fArray[1] + 0.0722f * fArray[2] > 0.8f) {
            return (float[])fArray.clone();
        }
        float[] fArray2 = Color.RGBtoHSB(Math.round(fArray[0] * 255.0f), Math.round(fArray[1] * 255.0f), Math.round(fArray[2] * 255.0f), null);
        int n = Color.HSBtoRGB(fArray2[0], Math.min(1.0f, fArray2[1] * 1.45f + 0.05f), fArray2[2] * 0.86f);
        return Themes.rgb(n & Themes.a(13282, 3954865671437832977L));
    }

    private static float[][] solid(int n) {
        float[] fArray = Themes.rgb(n);
        float[] fArray2 = Themes.deep(fArray);
        return new float[][]{fArray, fArray2, Themes.mixed(fArray, Themes.c(1.0f, 1.0f, 1.0f, 1.0f), 0.45f), Themes.mixed(fArray, fArray2, 0.5f)};
    }

    private static float[][] grad(int n, int n2) {
        float[] fArray = Themes.rgb(n);
        float[] fArray2 = Themes.rgb(n2);
        float[] fArray3 = Themes.mixed(fArray, fArray2, 0.5f);
        return new float[][]{fArray3, Themes.deep(fArray3), Themes.mixed(fArray, Themes.c(1.0f, 1.0f, 1.0f, 1.0f), 0.35f), fArray2, Themes.deep(fArray), Themes.deep(fArray2), fArray, fArray2};
    }

    public static boolean plusAccent(int n) {
        int n2;
        block13: {
            block11: {
                boolean bl;
                block12: {
                    block10: {
                        bl = ImScreen.a;
                        n2 = n;
                        if (bl) break block10;
                        if (n2 < 0) break block11;
                        n2 = n;
                    }
                    if (bl) break block12;
                    if (n2 >= ACCENTS.length) break block11;
                    n2 = ACCENTS[n].length;
                }
                if (bl) break block13;
                if (n2 <= 4) break block11;
                n2 = 1;
                break block13;
            }
            n2 = 0;
        }
        return n2 != 0;
    }

    private static float[][] snapshot() {
        float[][] fArrayArray = new float[TARGETS.length][];
        for (int i = 0; i < TARGETS.length; ++i) {
            fArrayArray[i] = (float[])TARGETS[i].clone();
        }
        return fArrayArray;
    }

    public static Themes$Look look() {
        return look;
    }

    public static Themes$Look chosen() {
        return chosen;
    }

    public static int accent() {
        return accent;
    }

    
    private static float[][] resolve(int n) {
        float[][] fArray;
        boolean bl = ImScreen.a;
        int n2 = look.ordinal();
        if (!bl) {
            switch (n2) {
                case 4: {
                    return ENDER_ACCENT;
                }
                case 5: {
                    return NETHER_ACCENT;
                }
                case 6: {
                    return DEEP_DARK_ACCENT;
                }
                case 7: {
                    return CHERRY_ACCENT;
                }
            }
            n2 = n;
        }
        if (n2 == 5 && look == Themes$Look.DAYLIGHT) {
            fArray = BLACK;
            return fArray;
        }
        fArray = ACCENTS[n];
        return fArray;
    }

    public static boolean ownAccent() {
        return Themes.look.plus;
    }

    
    private static float[][] board(Themes$Look var0) {
        var1_1 = ImScreen.a;
        v0 = var0.ordinal();
        if (!var1_1) {
        }
        {
            switch (v0)
        }
lbl_neg_1000:

        {
            case 4: {
                v0 = 4;
lbl12:

                v2 = new float[v0][];
                v2[0] = Themes.c(0.914f, 0.722f, 1.0f, 1.0f);
                v2[1] = Themes.c(1.0f, 0.541f, 0.847f, 1.0f);
                v2[2] = Themes.c(0.0f, 0.0f, 0.0f, 0.18f);
                v3 = v2;
                v2[3] = new float[]{-170.0f, -80.0f, 340.0f, 280.0f, 0.78f, 0.49f, 1.0f, 0.3f, 1.0f, 0.541f, 0.847f, 0.1f};
                break;
            }
lbl19:

            case 5: {
                v4 = new float[4][];
                v4[0] = Themes.c(1.0f, 0.604f, 0.478f, 1.0f);
                v4[1] = Themes.c(0.761f, 0.102f, 0.227f, 1.0f);
                v4[2] = Themes.c(0.0f, 0.0f, 0.0f, 0.2f);
                v3 = v4;
                v4[3] = new float[]{220.0f, -20.0f, 380.0f, 260.0f, 1.0f, 0.431f, 0.235f, 0.3f, 0.761f, 0.102f, 0.227f, 0.12f};
                break;
            }
lbl27:

            case 6: {
                v5 = new float[4][];
                v5[0] = Themes.c(0.494f, 0.922f, 0.961f, 1.0f);
                v5[1] = null;
                v5[2] = Themes.c(0.0f, 0.0f, 0.0f, 0.25f);
                v3 = v5;
                v5[3] = new float[]{-190.0f, 70.0f, 310.0f, 230.0f, 0.098f, 0.827f, 0.902f, 0.18f, 0.043f, 0.431f, 0.549f, 0.08f};
                break;
            }
lbl35:

            case 7: {
                v6 = new float[4][];
                v6[0] = Themes.c(1.0f, 0.761f, 0.863f, 1.0f);
                v6[1] = Themes.c(1.0f, 0.561f, 0.753f, 1.0f);
                v6[2] = Themes.c(0.0f, 0.0f, 0.0f, 0.15f);
                v3 = v6;
                v6[3] = new float[]{180.0f, 60.0f, 320.0f, 240.0f, 1.0f, 0.62f, 0.78f, 0.24f, 0.851f, 0.314f, 0.541f, 0.08f};
                break;
            }
lbl43:

            default: {
                v3 = null;
            }
        }
        return v3;
    }

    static float[] accentGlow(float[] fArray) {
        block2: {
            if (fArray != null && !(Math.max(fArray[0], Math.max(fArray[1], fArray[2])) - Math.min(fArray[0], Math.min(fArray[1], fArray[2])) < 0.1f)) break block2;
            fArray = Themes.rgb(Themes.a(4870, 8293850626359260146L));
        }
        return new float[]{-6.0f, -6.0f, 639.60004f, 366.6f, fArray[0], fArray[1], fArray[2], 0.14f, fArray[0], fArray[1], fArray[2], 0.063f};
    }

    public static float[] glow() {
        return glow;
    }

    public static void plusChanged() {
        Themes.set(chosen, chosenAccent);
    }

    public static float[] swatch(int n, boolean bl) {
        float[] fArray;
        block20: {
            float[][] fArray2;
            block16: {
                float[][] fArray3;
                block17: {
                    int n2;
                    float[][] fArray4;
                    block18: {
                        block19: {
                            boolean bl2;
                            block12: {
                                block13: {
                                    int n3;
                                    float[][] fArray5;
                                    block14: {
                                        block15: {
                                            fArray3 = Themes.resolve(n);
                                            bl2 = ImScreen.a;
                                            fArray2 = fArray3;
                                            if (bl2) break block12;
                                            if (fArray2.length <= 6) break block13;
                                            fArray5 = fArray3;
                                            n3 = bl;
                                            if (bl2) break block14;
                                            if (n3 == 0) break block15;
                                            n3 = 7;
                                            break block14;
                                        }
                                        n3 = 6;
                                    }
                                    fArray = fArray5[n3];
                                    break block20;
                                }
                                fArray2 = fArray3;
                            }
                            if (bl2) break block16;
                            if (fArray2.length <= 4) break block17;
                            fArray4 = fArray3;
                            n2 = bl;
                            if (bl2) break block18;
                            if (n2 == 0) break block19;
                            n2 = 5;
                            break block18;
                        }
                        n2 = 4;
                    }
                    fArray = fArray4[n2];
                    break block20;
                }
                fArray2 = fArray3;
            }
            fArray = fArray2[0];
        }
        return fArray;
    }

    public static float glass() {
        return glass;
    }

    public static void setGlass(float f) {
        glass = Math.max(0.0f, Math.min(1.0f, f));
        Themes.set(chosen, chosenAccent);
    }

    public static int chosenAccent() {
        return chosenAccent;
    }

    /*
     * WARNING - void declaration
     */
    public static void set(Themes$Look themes$Look, int n) {
        float[] fArray;
        block123: {
            block120: {
                float[][] fArray2;
                block119: {
                    float[] fArray3;
                    float[][] fArray4;
                    boolean bl;
                    block115: {
                        block117: {
                            int n2;
                            float[][] fArray5;
                            block118: {
                                block116: {
                                    block122: {
                                        block114: {
                                            block113: {
                                                float[] fArray6;
                                                block121: {
                                                    block112: {
                                                        float[][] fArray7;
                                                        block111: {
                                                            float[] fArray8;
                                                            block110: {
                                                                float f;
                                                                block109: {
                                                                    block108: {
                                                                        int n3;
                                                                        float[] fArray9;
                                                                        float[] fArray10;
                                                                        block107: {
                                                                            float f2;
                                                                            block105: {
                                                                                block106: {
                                                                                    float[] fArray11;
                                                                                    block104: {
                                                                                        float[][] fArray12;
                                                                                        block102: {
                                                                                            block103: {
                                                                                                int n4;
                                                                                                block101: {
                                                                                                    float[] fArray13;
                                                                                                    int n5;
                                                                                                    int n6;
                                                                                                    block100: {
                                                                                                        block98: {
                                                                                                            block99: {
                                                                                                                int n7;
                                                                                                                block97: {
                                                                                                                    float[] fArray14;
                                                                                                                    block96: {
                                                                                                                        float[][] fArray15;
                                                                                                                        block94: {
                                                                                                                            block95: {
                                                                                                                                int n8;
                                                                                                                                block92: {
                                                                                                                                    block93: {
                                                                                                                                        int n92;
                                                                                                                                        block91: {
                                                                                                                                            void object;
                                                                                                                                            int n10;
                                                                                                                                            block90: {
                                                                                                                                                block89: {
                                                                                                                                                    block88: {
                                                                                                                                                        int n11;
                                                                                                                                                        block85: {
                                                                                                                                                            block87: {
                                                                                                                                                                block86: {
                                                                                                                                                                    Themes$Look themes$Look2;
                                                                                                                                                                    block84: {
                                                                                                                                                                        block83: {
                                                                                                                                                                            boolean bl2;
                                                                                                                                                                            block82: {
                                                                                                                                                                                Themes$Look themes$Look3;
                                                                                                                                                                                block80: {
                                                                                                                                                                                    block81: {
                                                                                                                                                                                        bl = ImScreen.a;
                                                                                                                                                                                        themes$Look3 = themes$Look;
                                                                                                                                                                                        if (bl) break block80;
                                                                                                                                                                                        if (themes$Look3 != null) break block81;
                                                                                                                                                                                        themes$Look3 = Themes$Look.AURORA;
                                                                                                                                                                                        break block80;
                                                                                                                                                                                    }
                                                                                                                                                                                    themes$Look3 = themes$Look;
                                                                                                                                                                                }
                                                                                                                                                                                chosen = themes$Look3;
                                                                                                                                                                                bl2 = Themes.chosen.plus;
                                                                                                                                                                                if (bl) break block82;
                                                                                                                                                                                if (!bl2) break block83;
                                                                                                                                                                                bl2 = Plus.active();
                                                                                                                                                                            }
                                                                                                                                                                            if (bl2) break block83;
                                                                                                                                                                            themes$Look2 = Themes$Look.AURORA;
                                                                                                                                                                            break block84;
                                                                                                                                                                        }
                                                                                                                                                                        themes$Look2 = chosen;
                                                                                                                                                                    }
                                                                                                                                                                    look = themes$Look2;
                                                                                                                                                                    n11 = n;
                                                                                                                                                                    if (bl) break block85;
                                                                                                                                                                    if (n11 < 0) break block86;
                                                                                                                                                                    n11 = n;
                                                                                                                                                                    if (bl) break block85;
                                                                                                                                                                    if (n11 < ACCENTS.length) break block87;
                                                                                                                                                                }
                                                                                                                                                                n11 = 0;
                                                                                                                                                                break block85;
                                                                                                                                                            }
                                                                                                                                                            n11 = n;
                                                                                                                                                        }
                                                                                                                                                        n10 = chosenAccent = n11;
                                                                                                                                                        if (bl) break block88;
                                                                                                                                                        if (!Themes.plusAccent(n10)) break block89;
                                                                                                                                                        n10 = Plus.active() ? 1 : 0;
                                                                                                                                                    }
                                                                                                                                                    if (bl) break block90;
                                                                                                                                                    if (n10 != 0) break block89;
                                                                                                                                                    n10 = 0;
                                                                                                                                                    break block90;
                                                                                                                                                }
                                                                                                                                                n10 = chosenAccent;
                                                                                                                                            }
                                                                                                                                            accent = n10;
                                                                                                                                            float[][] fArray112 = PALETTES[look.ordinal()];
                                                                                                                                            boolean i = false;
                                                                                                                                            while (object < TARGETS.length) {
                                                                                                                                                System.arraycopy(fArray112[object], 0, TARGETS[object], 0, 4);
                                                                                                                                                ++object;
                                                                                                                                                if (!bl) continue;
                                                                                                                                            }
                                                                                                                                            for (int n92 : SEE_THROUGH) {
                                                                                                                                                if (!bl) {
                                                                                                                                                    int n12 = n92;
                                                                                                                                                    Themes.TARGETS[n12][3] = fArray112[n12][3] + (GLASSY[n12][3] - fArray112[n12][3]) * glass;
                                                                                                                                                    if (!bl) continue;
                                                                                                                                                }
                                                                                                                                                break block91;
                                                                                                                                            }
                                                                                                                                            n92 = accent;
                                                                                                                                        }
                                                                                                                                        fArray12 = Themes.resolve(n92);
                                                                                                                                        n8 = fArray12.length;
                                                                                                                                        if (bl) break block92;
                                                                                                                                        if (n8 <= 4) break block93;
                                                                                                                                        n8 = 1;
                                                                                                                                        break block92;
                                                                                                                                    }
                                                                                                                                    n8 = 0;
                                                                                                                                }
                                                                                                                                n6 = n8;
                                                                                                                                System.arraycopy(fArray12[0], 0, Menu.ACCENT, 0, 4);
                                                                                                                                fArray15 = fArray12;
                                                                                                                                if (bl) break block94;
                                                                                                                                System.arraycopy(fArray15[1], 0, Menu.FILL, 0, 4);
                                                                                                                                if (n6 == 0) break block95;
                                                                                                                                fArray14 = fArray12[4];
                                                                                                                                break block96;
                                                                                                                            }
                                                                                                                            fArray15 = fArray12;
                                                                                                                        }
                                                                                                                        fArray14 = fArray15[1];
                                                                                                                    }
                                                                                                                    System.arraycopy(fArray14, 0, Menu.GRAD_A, 0, 4);
                                                                                                                    float[] fArray16 = n6 != 0 ? fArray12[5] : fArray12[1];
                                                                                                                    System.arraycopy(fArray16, 0, Menu.GRAD_B, 0, 4);
                                                                                                                    int n13 = look == Themes$Look.DAYLIGHT ? 1 : 0;
                                                                                                                    n5 = n13;
                                                                                                                    n7 = n5;
                                                                                                                    if (bl) break block97;
                                                                                                                    if (n7 == 0) break block98;
                                                                                                                    n7 = n6;
                                                                                                                }
                                                                                                                if (n7 == 0) break block99;
                                                                                                                fArray13 = fArray12[4];
                                                                                                                break block100;
                                                                                                            }
                                                                                                            fArray13 = fArray12[0];
                                                                                                            break block100;
                                                                                                        }
                                                                                                        fArray13 = fArray12[2];
                                                                                                    }
                                                                                                    System.arraycopy(fArray13, 0, Menu.ICON_TOP, 0, 4);
                                                                                                    n4 = n5;
                                                                                                    if (bl) break block101;
                                                                                                    if (n4 == 0) break block102;
                                                                                                    n4 = n6;
                                                                                                }
                                                                                                if (n4 == 0) break block103;
                                                                                                fArray11 = fArray12[5];
                                                                                                break block104;
                                                                                            }
                                                                                            fArray11 = fArray12[1];
                                                                                            break block104;
                                                                                        }
                                                                                        fArray11 = fArray12[3];
                                                                                    }
                                                                                    System.arraycopy(fArray11, 0, Menu.ICON_FOOT, 0, 4);
                                                                                    fArray10 = Menu.FILL;
                                                                                    float f3 = 0.2126f * fArray10[0] + 0.7152f * fArray10[1] + 0.0722f * fArray10[2] - 0.55f;
                                                                                    f2 = f3 == 0.0f ? 0 : (f3 > 0.0f ? 1 : -1);
                                                                                    if (bl) break block105;
                                                                                    if (f2 <= 0) break block106;
                                                                                    f2 = 1.0f;
                                                                                    break block105;
                                                                                }
                                                                                f2 = 0.0f;
                                                                            }
                                                                            float f4 = f2;
                                                                            fArray9 = f4 != false ? Themes.c(0.078f, 0.094f, 0.129f, 1.0f) : Themes.c(0.973f, 0.976f, 0.984f, 1.0f);
                                                                            n3 = 0;
                                                                            if (bl) break block107;
                                                                            System.arraycopy(fArray9, n3, Im.ON_FILL, 0, 4);
                                                                            if (f4 == false) break block108;
                                                                            fArray9 = fArray10;
                                                                            n3 = 0;
                                                                        }
                                                                        f = fArray9[n3];
                                                                        if (bl) break block109;
                                                                        if (!(f > 0.85f)) break block108;
                                                                        f = fArray10[1];
                                                                        if (bl) break block109;
                                                                        if (!(f > 0.85f)) break block108;
                                                                        fArray8 = Im.ON_FILL;
                                                                        break block110;
                                                                    }
                                                                    f = 1.0f;
                                                                }
                                                                fArray8 = Themes.c(f, 1.0f, 1.0f, 1.0f);
                                                            }
                                                            System.arraycopy(fArray8, 0, Menu.KNOB_ON, 0, 4);
                                                            fArray4 = Themes.board(look);
                                                            fArray7 = fArray4;
                                                            if (bl) break block111;
                                                            if (fArray7 == null) break block112;
                                                            fArray7 = fArray4;
                                                        }
                                                        fArray6 = fArray7[3];
                                                        break block121;
                                                    }
                                                    float[] fArray17 = accent == 0 ? null : Menu.ACCENT;
                                                    fArray6 = Themes.accentGlow(fArray17);
                                                }
                                                glow = fArray6;
                                                if (bl) break block113;
                                                if (fArray4 == null) break block114;
                                                System.arraycopy(fArray4[0], 0, Menu.SOFT, 0, 4);
                                            }
                                            if (!bl) break block122;
                                        }
                                        for (int i = 0; i < 4; ++i) {
                                            fArray3 = Menu.SOFT;
                                            if (!bl) {
                                                fArray3[i] = Menu.ACCENT[i] + (Menu.ICON_TOP[i] - Menu.ACCENT[i]) * 0.45f;
                                                if (!bl) continue;
                                                break;
                                            }
                                            break block115;
                                        }
                                    }
                                    fArray5 = fArray4;
                                    if (bl) break block116;
                                    if (fArray5 == null) break block117;
                                    fArray5 = fArray4;
                                }
                                n2 = 1;
                                if (bl) break block118;
                                if (fArray5[n2] == null) break block117;
                                fArray5 = fArray4;
                                n2 = 1;
                            }
                            fArray3 = fArray5[n2];
                            break block115;
                        }
                        fArray3 = Im.CHIP_ON;
                    }
                    System.arraycopy(fArray3, 0, Menu.CHIP_TO, 0, 4);
                    fArray2 = fArray4;
                    if (bl) break block119;
                    if (fArray2 == null) break block120;
                    fArray2 = fArray4;
                }
                fArray = fArray2[2];
                break block123;
            }
            fArray = Themes.c(0.0f, 0.0f, 0.0f, 0.0f);
        }
        System.arraycopy(fArray, 0, Menu.SIDE_BG, 0, 4);
        Im.restyle();
    }

    public static int sizeIndex() {
        return size;
    }

    public static float size() {
        return SIZES[size];
    }

    public static void setSize(int n) {
        int n2;
        block8: {
            block10: {
                block9: {
                    boolean bl = ImScreen.a;
                    n2 = n;
                    if (bl) break block8;
                    if (n2 < 0) break block9;
                    n2 = n;
                    if (bl) break block8;
                    if (n2 < SIZES.length) break block10;
                }
                n2 = 1;
                break block8;
            }
            n2 = n;
        }
        size = n2;
    }

    public static void write(JsonObject jsonObject) {
        JsonObject jsonObject2 = new JsonObject();
        String[] stringArray = a;
        jsonObject2.addProperty(stringArray[20], chosen.name());
        jsonObject2.addProperty(stringArray[10], (Number)chosenAccent);
        jsonObject2.addProperty(stringArray[29], (Number)Float.valueOf(glass));
        jsonObject2.addProperty(stringArray[9], (Number)size);
        jsonObject.add(stringArray[11], (JsonElement)jsonObject2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void read(JsonObject jsonObject) {
        int n;
        block38: {
            block34: {
                JsonObject jsonObject2;
                block33: {
                    int n2;
                    Themes$Look themes$Look;
                    JsonObject jsonObject3;
                    boolean bl;
                    block37: {
                        block32: {
                            String string;
                            JsonObject jsonObject4;
                            block31: {
                                Float f;
                                String string2;
                                String string3;
                                block36: {
                                    block30: {
                                        JsonObject jsonObject5;
                                        block29: {
                                            String string4;
                                            block35: {
                                                block28: {
                                                    JsonObject jsonObject6;
                                                    block27: {
                                                        JsonObject jsonObject7;
                                                        block26: {
                                                            JsonObject jsonObject8;
                                                            block25: {
                                                                bl = ImScreen.a;
                                                                jsonObject8 = jsonObject;
                                                                if (bl) break block25;
                                                                if (!jsonObject8.has(a[22])) return;
                                                                jsonObject7 = jsonObject;
                                                                if (bl) break block26;
                                                                jsonObject8 = jsonObject7.get(a[22]);
                                                            }
                                                            if (!jsonObject8.isJsonObject()) {
                                                                return;
                                                            }
                                                            String[] stringArray = a;
                                                            jsonObject7 = jsonObject.getAsJsonObject(stringArray[22]);
                                                        }
                                                        jsonObject3 = jsonObject7;
                                                        jsonObject6 = jsonObject3;
                                                        if (bl) break block27;
                                                        if (!jsonObject6.has(a[30])) break block28;
                                                        jsonObject6 = jsonObject3.get(a[30]);
                                                    }
                                                    string4 = jsonObject6.getAsString();
                                                    break block35;
                                                }
                                                string4 = null;
                                            }
                                            string3 = string4;
                                            string2 = string3;
                                            jsonObject5 = jsonObject3;
                                            if (bl) break block29;
                                            if (!jsonObject5.has(a[4])) break block30;
                                            jsonObject5 = jsonObject3.get(a[4]);
                                        }
                                        f = Float.valueOf(jsonObject5.getAsFloat());
                                        break block36;
                                    }
                                    f = null;
                                }
                                glass = Themes$Look.savedGlass(string2, f);
                                themes$Look = Themes$Look.saved(string3);
                                jsonObject4 = jsonObject3;
                                string = a[21];
                                if (bl) break block31;
                                if (!jsonObject4.has(string)) break block32;
                                jsonObject4 = jsonObject3;
                                string = a[21];
                            }
                            n2 = jsonObject4.get(string).getAsInt();
                            break block37;
                        }
                        n2 = 0;
                    }
                    Themes.set(themes$Look, n2);
                    jsonObject2 = jsonObject3;
                    if (bl) break block33;
                    if (!jsonObject2.has(a[36])) break block34;
                    jsonObject2 = jsonObject3.get(a[36]);
                }
                n = jsonObject2.getAsInt();
                break block38;
            }
            n = 1;
        }
        Themes.setSize(n);
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[37];
                        var11_1 = 0;
                        var10_2 = "d~\u000b\\.z\u0004zx\u0017Z\u0005fp\u000bV*\u0004nd\nZ\u0005M}\u0018B<\u0005mc\u001cT!\u0004~t\u0018]\u0005er\u001cP!\b~f\u0010]&qu^\u0004Yx\u0003T\u0006Kr\u001aT!b\tGt\u0017D\u001b~xGt\u0005o|\u001bT=\u0004x~\nT\u0004xt\u001cW\u0005k|\u001bT=\u0005y|\u0018]#\u0005}y\u0010E*\u0006yd\u0017B*b\u0004fx\u0014T\u0005^y\u001c\\*\u0006Kr\u001aT!b\tGt\u0017D\u001b~xGt\u0007h}\u0016B<yp\u0006|x\u0016]*b\u0003yz\u0000\u0006dt\u001bD#w\u0006ec\u0018_(s\u0006fp\u001e^ x\u0005M}\u0018B<\u0005^y\u001c\\*\u0006kd\u000b^=w\u0003xt\u001d\u0006gt\u0018U a\u0005zt\u0018C#";
                        var12_3 = "d~\u000b\\.z\u0004zx\u0017Z\u0005fp\u000bV*\u0004nd\nZ\u0005M}\u0018B<\u0005mc\u001cT!\u0004~t\u0018]\u0005er\u001cP!\b~f\u0010]&qu^\u0004Yx\u0003T\u0006Kr\u001aT!b\tGt\u0017D\u001b~xGt\u0005o|\u001bT=\u0004x~\nT\u0004xt\u001cW\u0005k|\u001bT=\u0005y|\u0018]#\u0005}y\u0010E*\u0006yd\u0017B*b\u0004fx\u0014T\u0005^y\u001c\\*\u0006Kr\u001aT!b\tGt\u0017D\u001b~xGt\u0007h}\u0016B<yp\u0006|x\u0016]*b\u0003yz\u0000\u0006dt\u001bD#w\u0006ec\u0018_(s\u0006fp\u001e^ x\u0005M}\u0018B<\u0005^y\u001c\\*\u0006kd\u000b^=w\u0003xt\u001d\u0006gt\u0018U a\u0005zt\u0018C#".length();
                        var9_4 = 6;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block25;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "h}\fT\u0004Yx\u0003T";
                            var12_3 = "h}\fT\u0004Yx\u0003T".length();
                            var9_4 = 4;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block25;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            break block26;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_7 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_7;
                        v9 = v6[v8];
                        switch (var14_7 % 7) {
                            case 0: {
                                v10 = 42;
                                break;
                            }
                            case 1: {
                                v10 = 17;
                                break;
                            }
                            case 2: {
                                v10 = 121;
                                break;
                            }
                            case 3: {
                                v10 = 49;
                                break;
                            }
                            case 4: {
                                v10 = 79;
                                break;
                            }
                            case 5: {
                                v10 = 22;
                                break;
                            }
                            default: {
                                v10 = 29;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_7;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_7);
                    v3 = new String(v6);
                    switch (v2) {
                        default: {
                            continue;
                        }
                        case 0:
lbl73:

                        continue;
                    }
                }
                Themes.a = var13;
                var0_8 = 8630016819126468840L;
                var6_9 = new long[33];
                var3_10 = 0;
                var4_11 = "\u000e\u001f\u00e4\t\u00a8\u008b\u00b2\u007f\t\u0092\u00f0T\u0012\u007f\u00d4\u0083V\u00ab\u0094f\u00a0\u0014`Q\u00bb\u0099\u00cb\u00ab\u0097\u00b6\u0093w\u00d7\u00a7}\u009f\u0016*\u00c5\u0088l&\u0086{\u0092\u00ceT\u00a2\u00a0\u00e1}\u00ff\u008e\u00bf\u00e8p\u00ed@\u00e4\u008ebP\u0011\u00a9%\u00c2\u00f7L\u001e\u0084\u00bbxh\b\u0098\u00b1k\u00aa\u0090g\u007fsoi\u00fb\u00d5o\u00c0\u00eb=\u00beRe!\u00ac\u0006a`;!\t\u0092\u0012\u00ee\u00a2\u0017yp<\u0088\u00bfr>\u00b5\u0019\u00fd\u00db\u00fd\u0090)k\u00e5T\u00b5\u00fa\u008d\u000e]\u009cU\u00d7(7\u0005\u00f7E\u00e9\u00e3K#\u00ce\u00dctq\u00fdo\u009f\u00c4o(\u008f\u0091t.\u0092\u0015e\u009a\u00f5Q\u00a3\u00baF\u00f5I\u00bcG\u00fc\u00ea\u00a5A\u00dbM\u00b1\u00a7\u007f\u00d2\u00b6\u00ef\u00a4a\u009a\u00dc\u001f\u00fc\u00d5\u00e2O\u0016gO\u00ff\u00e1=\u00f8K\u00f1U\u0012zo\u00e9-$}\u00aa\u0088\u00b8\u001c\\\u0084h\u00b2#\u008d$\u00f3>\u0017\u00f2\u0006\u0015\u0080-S1)\u00db:\u00f4\u0081\u00b7\u00b7\u001d\u009a\u00b9\u00a0\u00bcR\u009b\u008e\u00a5v\u0017c\u00cb\u00bd\u0093";
                var5_12 = "\u000e\u001f\u00e4\t\u00a8\u008b\u00b2\u007f\t\u0092\u00f0T\u0012\u007f\u00d4\u0083V\u00ab\u0094f\u00a0\u0014`Q\u00bb\u0099\u00cb\u00ab\u0097\u00b6\u0093w\u00d7\u00a7}\u009f\u0016*\u00c5\u0088l&\u0086{\u0092\u00ceT\u00a2\u00a0\u00e1}\u00ff\u008e\u00bf\u00e8p\u00ed@\u00e4\u008ebP\u0011\u00a9%\u00c2\u00f7L\u001e\u0084\u00bbxh\b\u0098\u00b1k\u00aa\u0090g\u007fsoi\u00fb\u00d5o\u00c0\u00eb=\u00beRe!\u00ac\u0006a`;!\t\u0092\u0012\u00ee\u00a2\u0017yp<\u0088\u00bfr>\u00b5\u0019\u00fd\u00db\u00fd\u0090)k\u00e5T\u00b5\u00fa\u008d\u000e]\u009cU\u00d7(7\u0005\u00f7E\u00e9\u00e3K#\u00ce\u00dctq\u00fdo\u009f\u00c4o(\u008f\u0091t.\u0092\u0015e\u009a\u00f5Q\u00a3\u00baF\u00f5I\u00bcG\u00fc\u00ea\u00a5A\u00dbM\u00b1\u00a7\u007f\u00d2\u00b6\u00ef\u00a4a\u009a\u00dc\u001f\u00fc\u00d5\u00e2O\u0016gO\u00ff\u00e1=\u00f8K\u00f1U\u0012zo\u00e9-$}\u00aa\u0088\u00b8\u001c\\\u0084h\u00b2#\u008d$\u00f3>\u0017\u00f2\u0006\u0015\u0080-S1)\u00db:\u00f4\u0081\u00b7\u00b7\u001d\u009a\u00b9\u00a0\u00bcR\u009b\u008e\u00a5v\u0017c\u00cb\u00bd\u0093".length();
                var2_13 = 0;
                while (true) {
                    var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                    v11 = var6_9;
                    v12 = var3_10++;
                    v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    var4_11 = "\u001f\u0014\u000f\u00d5o6\u00ec\u00e7\u0091\u000f\u009by\u00a2D\u00c2\u00c0";
                    var5_12 = "\u001f\u0014\u000f\u00d5o6\u00ec\u00e7\u0091\u000f\u009by\u00a2D\u00c2\u00c0".length();
                    var2_13 = 0;
                    while (true) {
                        var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                        v11 = var6_9;
                        v12 = var3_10++;
                        v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var0_8;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        Themes.b = var6_9;
        Themes.c = new Integer[33];
        Themes.WHITE = Themes.a(28566, 3162795941573332860L);
        Themes.TARGETS = new float[][]{Im.INK, Im.SUB, Im.MUTE, Im.GLASS, Im.RIM, Im.RIM_HI, Im.WASH, Im.WASH_HI, Im.QUIET, Im.QUIET_HI, Im.WELL, Im.GREEN, Im.WARN, Im.DANGER, Im.BG, Im.POP, Im.DIM, Im.TINT, Im.TRACK, Im.KNOB, Im.WINDOW, Im.WIN_LINE, Im.LINE, Im.SURFACE, Im.SURFACE_LINE, Im.FIELD, Im.FIELD_LINE, Im.PANE, Im.PANE_LINE, Im.CHIP_ON, Im.CHIP_ON_INK};
        Themes.INK_ON = new float[]{0.945f, 0.953f, 0.969f, 1.0f};
        Themes.DARK_ON = new float[]{0.043f, 0.051f, 0.078f, 1.0f};
        Themes.PALETTES = new float[][][]{Themes.snapshot(), new float[][]{Themes.c(0.945f, 0.953f, 0.969f, 1.0f), Themes.c(0.702f, 0.729f, 0.792f, 1.0f), Themes.c(0.541f, 0.573f, 0.639f, 1.0f), Themes.c(0.035f, 0.039f, 0.063f, 1.0f), Themes.c(1.0f, 1.0f, 1.0f, 0.1f), Themes.c(1.0f, 1.0f, 1.0f, 0.45f), Themes.c(1.0f, 1.0f, 1.0f, 0.14f), Themes.c(1.0f, 1.0f, 1.0f, 0.22f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(1.0f, 1.0f, 1.0f, 0.1f), Themes.c(0.0f, 0.0f, 0.0f, 0.35f), Themes.c(0.329f, 0.839f, 0.627f, 1.0f), Themes.c(0.965f, 0.757f, 0.467f, 1.0f), Themes.c(0.878f, 0.443f, 0.373f, 1.0f), Themes.c(0.031f, 0.035f, 0.055f, 1.1f), Themes.c(0.051f, 0.059f, 0.086f, 1.0f), Themes.c(0.0f, 0.0f, 0.0f, 1.1f), Themes.c(1.0f, 1.0f, 1.0f, 0.9f), Themes.c(0.118f, 0.137f, 0.192f, 1.0f), Themes.c(0.365f, 0.396f, 0.471f, 1.0f), Themes.c(0.027f, 0.031f, 0.051f, 0.97f), Themes.c(1.0f, 1.0f, 1.0f, 0.1f), Themes.c(1.0f, 1.0f, 1.0f, 0.07f), Themes.c(1.0f, 1.0f, 1.0f, 0.035f), Themes.c(1.0f, 1.0f, 1.0f, 0.07f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(1.0f, 1.0f, 1.0f, 0.09f), Themes.c(1.0f, 1.0f, 1.0f, 0.03f), Themes.c(1.0f, 1.0f, 1.0f, 0.08f), Themes.INK_ON, Themes.DARK_ON}, new float[][]{Themes.c(1.0f, 1.0f, 1.0f, 1.0f), Themes.c(0.741f, 0.741f, 0.741f, 1.0f), Themes.c(0.549f, 0.549f, 0.549f, 1.0f), Themes.c(0.0f, 0.0f, 0.0f, 1.0f), Themes.c(1.0f, 1.0f, 1.0f, 0.11f), Themes.c(1.0f, 1.0f, 1.0f, 0.45f), Themes.c(1.0f, 1.0f, 1.0f, 0.12f), Themes.c(1.0f, 1.0f, 1.0f, 0.2f), Themes.c(1.0f, 1.0f, 1.0f, 0.05f), Themes.c(1.0f, 1.0f, 1.0f, 0.09f), Themes.c(1.0f, 1.0f, 1.0f, 0.04f), Themes.c(0.329f, 0.839f, 0.627f, 1.0f), Themes.c(0.965f, 0.757f, 0.467f, 1.0f), Themes.c(0.878f, 0.443f, 0.373f, 1.0f), Themes.c(0.0f, 0.0f, 0.0f, 1.1f), Themes.c(0.024f, 0.024f, 0.024f, 1.0f), Themes.c(0.0f, 0.0f, 0.0f, 1.2f), Themes.c(1.0f, 1.0f, 1.0f, 0.8f), Themes.c(0.102f, 0.102f, 0.102f, 1.0f), Themes.c(0.361f, 0.361f, 0.361f, 1.0f), Themes.c(0.0f, 0.0f, 0.0f, 1.0f), Themes.c(1.0f, 1.0f, 1.0f, 0.12f), Themes.c(1.0f, 1.0f, 1.0f, 0.07f), Themes.c(1.0f, 1.0f, 1.0f, 0.035f), Themes.c(1.0f, 1.0f, 1.0f, 0.08f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(1.0f, 1.0f, 1.0f, 0.09f), Themes.c(1.0f, 1.0f, 1.0f, 0.03f), Themes.c(1.0f, 1.0f, 1.0f, 0.08f), Themes.c(1.0f, 1.0f, 1.0f, 1.0f), Themes.c(0.0f, 0.0f, 0.0f, 1.0f)}, new float[][]{Themes.c(0.078f, 0.094f, 0.129f, 1.0f), Themes.c(0.239f, 0.267f, 0.322f, 1.0f), Themes.c(0.369f, 0.396f, 0.459f, 1.0f), Themes.c(0.957f, 0.961f, 0.973f, 0.92f), Themes.c(0.0f, 0.0f, 0.0f, 0.12f), Themes.c(0.0f, 0.0f, 0.0f, 0.45f), Themes.c(0.0f, 0.0f, 0.0f, 0.1f), Themes.c(0.0f, 0.0f, 0.0f, 0.16f), Themes.c(0.0f, 0.0f, 0.0f, 0.04f), Themes.c(0.0f, 0.0f, 0.0f, 0.07f), Themes.c(0.0f, 0.0f, 0.0f, 0.05f), Themes.c(0.11f, 0.51f, 0.345f, 1.0f), Themes.c(0.69f, 0.44f, 0.06f, 1.0f), Themes.c(0.76f, 0.25f, 0.2f, 1.0f), Themes.c(0.957f, 0.961f, 0.973f, 1.02f), Themes.c(0.984f, 0.984f, 0.988f, 1.0f), Themes.c(0.86f, 0.88f, 0.92f, 0.7f), Themes.c(0.06f, 0.08f, 0.12f, 1.0f), Themes.c(0.82f, 0.84f, 0.88f, 1.0f), Themes.c(1.0f, 1.0f, 1.0f, 1.0f), Themes.c(0.957f, 0.961f, 0.973f, 0.98f), Themes.c(0.0f, 0.0f, 0.0f, 0.1f), Themes.c(0.0f, 0.0f, 0.0f, 0.08f), Themes.c(1.0f, 1.0f, 1.0f, 1.0f), Themes.c(0.0f, 0.0f, 0.0f, 0.08f), Themes.c(1.0f, 1.0f, 1.0f, 1.0f), Themes.c(0.0f, 0.0f, 0.0f, 0.12f), Themes.c(1.0f, 1.0f, 1.0f, 1.0f), Themes.c(0.0f, 0.0f, 0.0f, 0.08f), Themes.c(0.078f, 0.094f, 0.129f, 1.0f), Themes.c(1.0f, 1.0f, 1.0f, 1.0f)}, new float[][]{Themes.c(0.957f, 0.925f, 1.0f, 1.0f), Themes.c(0.804f, 0.741f, 0.902f, 1.0f), Themes.c(0.608f, 0.545f, 0.722f, 1.0f), Themes.c(0.075f, 0.047f, 0.129f, 0.9f), Themes.c(0.78f, 0.49f, 1.0f, 0.16f), Themes.c(0.78f, 0.49f, 1.0f, 0.5f), Themes.c(1.0f, 1.0f, 1.0f, 0.14f), Themes.c(1.0f, 1.0f, 1.0f, 0.22f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(1.0f, 1.0f, 1.0f, 0.1f), Themes.c(0.0f, 0.0f, 0.0f, 0.35f), Themes.c(0.329f, 0.839f, 0.627f, 1.0f), Themes.c(0.965f, 0.757f, 0.467f, 1.0f), Themes.c(0.878f, 0.443f, 0.373f, 1.0f), Themes.c(0.075f, 0.047f, 0.129f, 1.1f), Themes.c(0.11f, 0.071f, 0.176f, 1.0f), Themes.c(0.02f, 0.01f, 0.04f, 1.1f), Themes.c(1.0f, 1.0f, 1.0f, 0.9f), Themes.c(1.0f, 1.0f, 1.0f, 0.1f), Themes.c(0.608f, 0.545f, 0.722f, 1.0f), Themes.c(0.075f, 0.047f, 0.129f, 0.97f), Themes.c(0.78f, 0.49f, 1.0f, 0.18f), Themes.c(0.78f, 0.49f, 1.0f, 0.1f), Themes.c(1.0f, 1.0f, 1.0f, 0.04f), Themes.c(1.0f, 1.0f, 1.0f, 0.07f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(0.78f, 0.49f, 1.0f, 0.18f), Themes.c(1.0f, 1.0f, 1.0f, 0.04f), Themes.c(0.78f, 0.49f, 1.0f, 0.14f), Themes.c(0.78f, 0.49f, 1.0f, 1.0f), Themes.c(0.102f, 0.059f, 0.169f, 1.0f)}, new float[][]{Themes.c(1.0f, 0.941f, 0.925f, 1.0f), Themes.c(0.89f, 0.761f, 0.729f, 1.0f), Themes.c(0.702f, 0.561f, 0.525f, 1.0f), Themes.c(0.102f, 0.035f, 0.047f, 0.9f), Themes.c(1.0f, 0.353f, 0.306f, 0.16f), Themes.c(1.0f, 0.353f, 0.306f, 0.5f), Themes.c(1.0f, 1.0f, 1.0f, 0.14f), Themes.c(1.0f, 1.0f, 1.0f, 0.22f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(1.0f, 1.0f, 1.0f, 0.1f), Themes.c(0.0f, 0.0f, 0.0f, 0.35f), Themes.c(0.329f, 0.839f, 0.627f, 1.0f), Themes.c(0.965f, 0.757f, 0.467f, 1.0f), Themes.c(0.878f, 0.443f, 0.373f, 1.0f), Themes.c(0.102f, 0.035f, 0.047f, 1.1f), Themes.c(0.137f, 0.065f, 0.087f, 1.0f), Themes.c(0.02f, 0.01f, 0.02f, 1.1f), Themes.c(1.0f, 1.0f, 1.0f, 0.9f), Themes.c(1.0f, 1.0f, 1.0f, 0.1f), Themes.c(0.702f, 0.561f, 0.525f, 1.0f), Themes.c(0.102f, 0.035f, 0.047f, 0.97f), Themes.c(1.0f, 0.353f, 0.306f, 0.2f), Themes.c(1.0f, 0.353f, 0.306f, 0.1f), Themes.c(1.0f, 1.0f, 1.0f, 0.04f), Themes.c(1.0f, 1.0f, 1.0f, 0.07f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(1.0f, 0.353f, 0.306f, 0.18f), Themes.c(1.0f, 1.0f, 1.0f, 0.04f), Themes.c(1.0f, 0.353f, 0.306f, 0.14f), Themes.c(1.0f, 0.478f, 0.306f, 1.0f), Themes.c(1.0f, 0.941f, 0.925f, 1.0f)}, new float[][]{Themes.c(0.902f, 0.969f, 0.98f, 1.0f), Themes.c(0.663f, 0.776f, 0.8f, 1.0f), Themes.c(0.435f, 0.557f, 0.584f, 1.0f), Themes.c(0.02f, 0.043f, 0.055f, 0.9f), Themes.c(0.098f, 0.827f, 0.902f, 0.16f), Themes.c(0.098f, 0.827f, 0.902f, 0.5f), Themes.c(1.0f, 1.0f, 1.0f, 0.14f), Themes.c(1.0f, 1.0f, 1.0f, 0.22f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(1.0f, 1.0f, 1.0f, 0.1f), Themes.c(0.0f, 0.0f, 0.0f, 0.35f), Themes.c(0.329f, 0.839f, 0.627f, 1.0f), Themes.c(0.965f, 0.757f, 0.467f, 1.0f), Themes.c(0.878f, 0.443f, 0.373f, 1.0f), Themes.c(0.02f, 0.043f, 0.055f, 1.1f), Themes.c(0.055f, 0.073f, 0.095f, 1.0f), Themes.c(0.02f, 0.01f, 0.02f, 1.1f), Themes.c(1.0f, 1.0f, 1.0f, 0.9f), Themes.c(1.0f, 1.0f, 1.0f, 0.08f), Themes.c(0.435f, 0.557f, 0.584f, 1.0f), Themes.c(0.02f, 0.043f, 0.055f, 0.97f), Themes.c(0.098f, 0.827f, 0.902f, 0.16f), Themes.c(0.098f, 0.827f, 0.902f, 0.08f), Themes.c(1.0f, 1.0f, 1.0f, 0.04f), Themes.c(1.0f, 1.0f, 1.0f, 0.07f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(0.098f, 0.827f, 0.902f, 0.16f), Themes.c(1.0f, 1.0f, 1.0f, 0.04f), Themes.c(0.098f, 0.827f, 0.902f, 0.12f), Themes.c(0.098f, 0.827f, 0.902f, 1.0f), Themes.c(0.012f, 0.075f, 0.09f, 1.0f)}, new float[][]{Themes.c(1.0f, 0.949f, 0.969f, 1.0f), Themes.c(0.898f, 0.765f, 0.824f, 1.0f), Themes.c(0.71f, 0.569f, 0.635f, 1.0f), Themes.c(0.118f, 0.051f, 0.09f, 0.9f), Themes.c(1.0f, 0.561f, 0.753f, 0.16f), Themes.c(1.0f, 0.561f, 0.753f, 0.5f), Themes.c(1.0f, 1.0f, 1.0f, 0.14f), Themes.c(1.0f, 1.0f, 1.0f, 0.22f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(1.0f, 1.0f, 1.0f, 0.1f), Themes.c(0.0f, 0.0f, 0.0f, 0.35f), Themes.c(0.329f, 0.839f, 0.627f, 1.0f), Themes.c(0.965f, 0.757f, 0.467f, 1.0f), Themes.c(0.878f, 0.443f, 0.373f, 1.0f), Themes.c(0.118f, 0.051f, 0.09f, 1.1f), Themes.c(0.153f, 0.081f, 0.13f, 1.0f), Themes.c(0.02f, 0.01f, 0.02f, 1.1f), Themes.c(1.0f, 1.0f, 1.0f, 0.9f), Themes.c(1.0f, 1.0f, 1.0f, 0.1f), Themes.c(0.71f, 0.569f, 0.635f, 1.0f), Themes.c(0.118f, 0.051f, 0.09f, 0.96f), Themes.c(1.0f, 0.561f, 0.753f, 0.2f), Themes.c(1.0f, 0.561f, 0.753f, 0.1f), Themes.c(1.0f, 1.0f, 1.0f, 0.04f), Themes.c(1.0f, 1.0f, 1.0f, 0.07f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(1.0f, 0.561f, 0.753f, 0.18f), Themes.c(1.0f, 1.0f, 1.0f, 0.04f), Themes.c(1.0f, 0.561f, 0.753f, 0.14f), Themes.c(1.0f, 0.761f, 0.863f, 1.0f), Themes.c(0.169f, 0.047f, 0.102f, 1.0f)}};
        Themes.GLASSY = new float[][]{Themes.c(1.0f, 1.0f, 1.0f, 1.0f), Themes.c(0.82f, 0.84f, 0.88f, 1.0f), Themes.c(0.66f, 0.69f, 0.75f, 1.0f), Themes.c(0.031f, 0.035f, 0.055f, 0.55f), Themes.c(1.0f, 1.0f, 1.0f, 0.34f), Themes.c(1.0f, 1.0f, 1.0f, 0.6f), Themes.c(1.0f, 1.0f, 1.0f, 0.2f), Themes.c(1.0f, 1.0f, 1.0f, 0.3f), Themes.c(1.0f, 1.0f, 1.0f, 0.16f), Themes.c(1.0f, 1.0f, 1.0f, 0.26f), Themes.c(0.0f, 0.0f, 0.0f, 0.4f), Themes.c(0.329f, 0.839f, 0.627f, 1.0f), Themes.c(0.965f, 0.757f, 0.467f, 1.0f), Themes.c(0.878f, 0.443f, 0.373f, 1.0f), Themes.c(0.031f, 0.035f, 0.055f, 1.0f), Themes.c(0.051f, 0.059f, 0.086f, 1.0f), Themes.c(0.02f, 0.024f, 0.04f, 0.65f), Themes.c(1.0f, 1.0f, 1.0f, 0.85f), Themes.c(1.0f, 1.0f, 1.0f, 0.18f), Themes.c(0.82f, 0.84f, 0.88f, 1.0f), Themes.c(0.031f, 0.035f, 0.055f, 0.55f), Themes.c(1.0f, 1.0f, 1.0f, 0.2f), Themes.c(1.0f, 1.0f, 1.0f, 0.07f), Themes.c(1.0f, 1.0f, 1.0f, 0.08f), Themes.c(1.0f, 1.0f, 1.0f, 0.14f), Themes.c(1.0f, 1.0f, 1.0f, 0.1f), Themes.c(1.0f, 1.0f, 1.0f, 0.16f), Themes.c(1.0f, 1.0f, 1.0f, 0.06f), Themes.c(1.0f, 1.0f, 1.0f, 0.14f), Themes.INK_ON, Themes.DARK_ON};
        Themes.SEE_THROUGH = new int[]{3, 16, 20};
        Themes.ENDER_ACCENT = new float[][]{Themes.c(0.78f, 0.49f, 1.0f, 1.0f), Themes.c(0.639f, 0.337f, 0.902f, 1.0f), Themes.c(1.0f, 0.761f, 0.925f, 1.0f), Themes.c(0.78f, 0.49f, 1.0f, 1.0f), Themes.c(0.78f, 0.49f, 1.0f, 1.0f), Themes.c(1.0f, 0.541f, 0.847f, 1.0f)};
        Themes.NETHER_ACCENT = new float[][]{Themes.c(1.0f, 0.353f, 0.306f, 1.0f), Themes.c(0.82f, 0.289f, 0.251f, 1.0f), Themes.c(1.0f, 0.816f, 0.541f, 1.0f), Themes.c(1.0f, 0.353f, 0.306f, 1.0f), Themes.c(1.0f, 0.478f, 0.306f, 1.0f), Themes.c(0.761f, 0.102f, 0.227f, 1.0f)};
        Themes.DEEP_DARK_ACCENT = new float[][]{Themes.c(0.098f, 0.827f, 0.902f, 1.0f), Themes.c(0.08f, 0.679f, 0.74f, 1.0f), Themes.c(0.784f, 0.98f, 1.0f, 1.0f), Themes.c(0.098f, 0.827f, 0.902f, 1.0f)};
        Themes.CHERRY_ACCENT = new float[][]{Themes.c(1.0f, 0.561f, 0.753f, 1.0f), Themes.c(0.82f, 0.46f, 0.617f, 1.0f), Themes.c(1.0f, 0.761f, 0.863f, 1.0f), Themes.c(1.0f, 0.561f, 0.753f, 1.0f), Themes.c(1.0f, 0.761f, 0.863f, 1.0f), Themes.c(1.0f, 0.561f, 0.753f, 1.0f)};
        Themes.VIOLET = new float[][]{(float[])Menu.ACCENT.clone(), (float[])Menu.FILL.clone(), (float[])Menu.ICON_TOP.clone(), (float[])Menu.ICON_FOOT.clone()};
        Themes.ACCENTS = new float[][][]{Themes.VIOLET, Themes.solid(Themes.a(14445, 3774707671179876500L)), Themes.solid(Themes.a(29820, 8585340107103868068L)), Themes.solid(Themes.a(7633, 7062127406709701933L)), Themes.solid(Themes.a(27609, 1353433354686761763L)), new float[][]{Themes.c(0.96f, 0.965f, 0.975f, 1.0f), Themes.c(0.93f, 0.937f, 0.953f, 1.0f), Themes.c(1.0f, 1.0f, 1.0f, 1.0f), Themes.c(0.78f, 0.8f, 0.85f, 1.0f)}, Themes.grad(Themes.a(15510, 1838824484110097512L), Themes.a(3209, 2124673169053674620L)), Themes.grad(Themes.a(11325, 7441288389972577492L), Themes.a(1716, 8725752491929691734L)), Themes.grad(Themes.a(31944, 5375637591156333630L), Themes.a(393, 1066778393879273841L)), Themes.grad(Themes.a(4490, 6083907124972482939L), Themes.a(8074, 1311539654122241889L)), Themes.grad(Themes.a(2354, 942599351167543746L), Themes.a(11325, 7441288389972577492L)), Themes.grad(Themes.a(13994, 7368037345989645897L), Themes.a(481, 1609599775950004495L)), Themes.grad(Themes.a(13138, 4370079049318519725L), Themes.a(14981, 5624926858372504168L)), Themes.grad(Themes.a(15101, 6521940426085030410L), Themes.a(4490, 6083907124972482939L)), Themes.grad(Themes.a(29575, 5291761909834519395L), Themes.a(19790, 2345108077772549555L)), Themes.grad(Themes.a(29040, 3922598706108961176L), Themes.a(23397, 49121529259539351L)), Themes.grad(Themes.a(24677, 8732226918081691779L), Themes.a(23310, 1935957719661493218L)), Themes.grad(Themes.a(7755, 8815992442151009962L), Themes.a(18324, 754297567581644655L)), Themes.solid(Themes.a(24677, 8732226918081691779L)), Themes.solid(Themes.a(23397, 49121529259539351L)), Themes.solid(Themes.a(27357, 8746038952881490490L)), Themes.solid(Themes.a(24654, 366488267857625262L)), Themes.solid(Themes.a(32170, 2002145595413306693L)), Themes.solid(Themes.a(19311, 1005476884155870090L))};
        v16 = new String[24];
        var8_6 = Themes.a;
        v16[0] = var8_6[24];
        v16[1] = var8_6[35];
        v16[2] = var8_6[5];
        v16[3] = var8_6[15];
        v16[4] = var8_6[13];
        v16[5] = var8_6[17];
        v16[6] = var8_6[31];
        v16[7] = var8_6[18];
        v16[8] = var8_6[7];
        v16[9] = var8_6[26];
        v16[10] = var8_6[12];
        v16[11] = var8_6[28];
        v16[12] = var8_6[23];
        v16[13] = var8_6[14];
        v16[14] = var8_6[3];
        v16[15] = var8_6[33];
        v16[16] = var8_6[8];
        v16[17] = var8_6[34];
        v16[18] = var8_6[25];
        v16[19] = var8_6[6];
        v16[20] = var8_6[19];
        v16[21] = var8_6[27];
        v16[22] = var8_6[32];
        v16[23] = var8_6[1];
        Themes.ACCENT_NAMES = v16;
        Themes.PLAIN = new int[]{0, 1, 18, 19, 2, 20, 3, 21, 22, 4, 23, 5};
        Themes.PLUS = new int[]{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        Themes.BLACK = new float[][]{Themes.c(0.1f, 0.11f, 0.14f, 1.0f), Themes.c(0.07f, 0.08f, 0.11f, 1.0f), Themes.c(0.25f, 0.27f, 0.32f, 1.0f), Themes.c(0.07f, 0.08f, 0.11f, 1.0f)};
        Themes.look = Themes$Look.AURORA;
        Themes.chosen = Themes$Look.AURORA;
        Themes.glow = Themes.accentGlow(null);
        Themes.SIZE_NAMES = new String[]{var8_6[16], var8_6[0], var8_6[2]};
        Themes.SIZES = new float[]{0.85f, 1.0f, 1.1f};
        Themes.size = 1;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x60F8;
        if (c[n2] == null) {
            Themes.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
