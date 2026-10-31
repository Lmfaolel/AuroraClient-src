package dev.quelchi.aurora.crosshair;

import dev.quelchi.aurora.crosshair.CrosshairShape$Preset;
import java.util.ArrayList;
import java.util.List;

public final class CrosshairShape {
    public static final int ON = 1;
    private static final int DIM;
    private static final int C;
    private static final String[] CROSS;
    private static final String[] TICKED;
    private static final String[] TICKED_SHORT;
    private static final String[] GAP;
    private static final String[] CORNERS;
    private static final String[] CHEVRON;
    private static final String[] DOT;
    private static final String[] a;

    private CrosshairShape() {
    }

    public static List<CrosshairShape$Preset> presets() {
        ArrayList<CrosshairShape$Preset> arrayList = new ArrayList<CrosshairShape$Preset>();
        String[] stringArray = a;
        arrayList.add(new CrosshairShape$Preset(stringArray[4], CrosshairShape.art(CROSS)));
        arrayList.add(new CrosshairShape$Preset(stringArray[6], CrosshairShape.art(TICKED)));
        arrayList.add(new CrosshairShape$Preset(stringArray[17], CrosshairShape.art(TICKED_SHORT)));
        arrayList.add(new CrosshairShape$Preset(stringArray[2], CrosshairShape.art(GAP)));
        arrayList.add(new CrosshairShape$Preset(stringArray[5], CrosshairShape.art(CORNERS)));
        arrayList.add(new CrosshairShape$Preset(stringArray[3], CrosshairShape.art(CHEVRON)));
        arrayList.add(new CrosshairShape$Preset(stringArray[20], CrosshairShape.art(DOT)));
        return arrayList;
    }

    public static int[][] shipped() {
        return CrosshairShape.art(CROSS);
    }

    private static int[][] art(String[] stringArray) {
        int[][] nArray = CrosshairShape.blank();
        block4: for (int i = 0; i < Math.min(15, stringArray.length); ++i) {
            String string = stringArray[i];
            int n = 0;
            while (true) {
                block6: {
                    if (n >= Math.min(15, string.length())) continue block4;
                    if (string.charAt(n) == '.') break block6;
                    nArray[i][n] = 1;
                }
                ++n;
            }
        }
        return nArray;
    }

    private static int[][] blank() {
        return new int[15][15];
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[25];
                        var11_1 = 0;
                        var10_2 = "/x2c5\u000fm/x2n8\u000fm/\u000f/x2c8\u000f`/u2c8\u000fm/\tF7lmuS,r%\u0007B>y;dN-\u0005B$s>e\u0007B9n#sS0\u0006U?\u007f&sE\u000f/x2c5\u000fm\"x2n8\u000fm/\u000f/x2c5\u0002m/x?n8\u000fm/\u000f/x2n5\u0002`\"u?n5\u000fm/\u000f/x2c5\u0002m/x?n8\u000fm/\u000f/x2c8\u000fm/x2c8\u000fm/\u000f/x2c5\u000fm/x2n8\u000fm/\u000f/x2c8\u000fm/x2c8\u000fm/\u000f/x2c8\u000fm\"x2c8\u000fm/\u000f/x2n5\u0002`\"u?n5\u000fm/\u000f/x2c5\u0002m\"x?n8\u000fm/\fU?\u007f&sEcr>s?b\u000f/x2c5\u0002`\"u?n8\u000fm/\u000f/x2n5\u0002m/x?n5\u000fm/\u0003E9h\u000f/x2c5\u000fm\"x2n8\u000fm/\u000f/x2c5\u0002m\"x?n8\u000fm/";
                        var12_3 = "/x2c5\u000fm/x2n8\u000fm/\u000f/x2c8\u000f`/u2c8\u000fm/\tF7lmuS,r%\u0007B>y;dN-\u0005B$s>e\u0007B9n#sS0\u0006U?\u007f&sE\u000f/x2c5\u000fm\"x2n8\u000fm/\u000f/x2c5\u0002m/x?n8\u000fm/\u000f/x2n5\u0002`\"u?n5\u000fm/\u000f/x2c5\u0002m/x?n8\u000fm/\u000f/x2c8\u000fm/x2c8\u000fm/\u000f/x2c5\u000fm/x2n8\u000fm/\u000f/x2c8\u000fm/x2c8\u000fm/\u000f/x2c8\u000fm\"x2c8\u000fm/\u000f/x2n5\u0002`\"u?n5\u000fm/\u000f/x2c5\u0002m\"x?n8\u000fm/\fU?\u007f&sEcr>s?b\u000f/x2c5\u0002`\"u?n8\u000fm/\u000f/x2n5\u0002m/x?n5\u000fm/\u0003E9h\u000f/x2c5\u000fm\"x2n8\u000fm/\u000f/x2c5\u0002m\"x?n8\u000fm/".length();
                        var9_4 = 15;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "/x2c8\u0002m/x?c8\u000fm/\u000f/x2c8\u000fm\"x2c8\u000fm/";
                            var12_3 = "/x2c8\u0002m/x?c8\u000fm/\u000f/x2c8\u000fm\"x2c8\u000fm/".length();
                            var9_4 = 15;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block20;
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
                            break block21;
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
                                v10 = 1;
                                break;
                            }
                            case 1: {
                                v10 = 86;
                                break;
                            }
                            case 2: {
                                v10 = 28;
                                break;
                            }
                            case 3: {
                                v10 = 77;
                                break;
                            }
                            case 4: {
                                v10 = 22;
                                break;
                            }
                            case 5: {
                                v10 = 33;
                                break;
                            }
                            default: {
                                v10 = 67;
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
                CrosshairShape.a = var13;
                var1_8 = 3801188217735650567L;
                var0_9 = new long[2];
                var4_10 = 0;
                var5_11 = "\u00a2g\u00afMb\u0088\u009d\u0000./v\\b\u0088\u009d\b";
                var6_12 = "\u00a2g\u00afMb\u0088\u009d\u0000./v\\b\u0088\u009d\b".length();
                var3_13 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl84:

                while (true) {
                    var0_9[v11] = (((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L) ^ var1_8;
                    if (var3_13 < var6_12) continue;
                    break block23;
                    break;
                }
            }
            var7_14 = var5_11.substring(var3_13, var3_13 += 8).getBytes("ISO-8859-1");
            v11 = var4_10++;
            while (true)
        }
        CrosshairShape.C = (int)var0_9[0];
        CrosshairShape.DIM = (int)var0_9[1];
        v12 = new String[15];
        var8_6 = CrosshairShape.a;
        v12[0] = var8_6[13];
        v12[1] = var8_6[11];
        v12[2] = var8_6[11];
        v12[3] = var8_6[14];
        v12[4] = var8_6[24];
        v12[5] = var8_6[24];
        v12[6] = var8_6[24];
        v12[7] = var8_6[15];
        v12[8] = var8_6[24];
        v12[9] = var8_6[24];
        v12[10] = var8_6[24];
        v12[11] = var8_6[24];
        v12[12] = var8_6[11];
        v12[13] = var8_6[11];
        v12[14] = var8_6[11];
        CrosshairShape.CROSS = v12;
        CrosshairShape.TICKED = new String[]{var8_6[11], var8_6[11], var8_6[11], var8_6[24], var8_6[22], var8_6[7], var8_6[24], var8_6[9], var8_6[24], var8_6[21], var8_6[16], var8_6[24], var8_6[11], var8_6[11], var8_6[11]};
        CrosshairShape.TICKED_SHORT = new String[]{var8_6[11], var8_6[11], var8_6[11], var8_6[24], var8_6[16], var8_6[21], var8_6[24], var8_6[18], var8_6[24], var8_6[21], var8_6[16], var8_6[24], var8_6[11], var8_6[11], var8_6[11]};
        CrosshairShape.GAP = new String[]{var8_6[11], var8_6[11], var8_6[11], var8_6[24], var8_6[24], var8_6[24], var8_6[11], var8_6[19], var8_6[11], var8_6[24], var8_6[24], var8_6[24], var8_6[11], var8_6[11], var8_6[11]};
        CrosshairShape.CORNERS = new String[]{var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[8], var8_6[0], var8_6[11], var8_6[11], var8_6[11], var8_6[12], var8_6[10], var8_6[11], var8_6[11], var8_6[11], var8_6[11]};
        CrosshairShape.CHEVRON = new String[]{var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[24], var8_6[1], var8_6[23], var8_6[12], var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[11]};
        CrosshairShape.DOT = new String[]{var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[24], var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[11], var8_6[11]};
    }
}
