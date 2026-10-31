package dev.quelchi.aurora.music;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.music.Lyrics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public final class AlbumColor {
    private static final int HUES;
    private static final float MAX_SAT = 0.55f;
    private static final float MIN_V = 0.16f;
    private static final float MAX_V = 0.3f;
    private static final float VIVID = 0.04f;
    private static final Map<String, int[]> CACHE;
    private static final String[] a;

    private AlbumColor() {
    }

    public static int[] get(String string, Supplier<int[]> supplier) {
        block10: {
            Object object;
            block11: {
                int[] nArray;
                block8: {
                    int[] nArray2;
                    block9: {
                        nArray2 = CACHE.get(string);
                        boolean bl = Lyrics.a;
                        nArray = nArray2;
                        if (bl) break block8;
                        if (nArray != null) break block9;
                        object = CACHE;
                        if (bl) break block10;
                        if (!object.containsKey(string + a[1])) break block11;
                    }
                    nArray = nArray2;
                }
                return nArray;
            }
            String[] stringArray = a;
            object = CACHE.put(string + stringArray[2], new int[0]);
        }
        Thread thread = new Thread(() -> AlbumColor.lambda$get$0(string, supplier), a[0]);
        thread.setDaemon(true);
        thread.start();
        return null;
    }

    static int[] of(int[] nArray) {
        int n;
        int n2;
        int n3;
        block61: {
            float f;
            float f2;
            float f3;
            block62: {
                int n4;
                float[] fArray;
                float[] fArray2;
                float[] fArray3;
                float[] fArray4;
                block60: {
                    block53: {
                        float f4;
                        int n5;
                        boolean bl;
                        block51: {
                            block52: {
                                float f5;
                                float f6;
                                int n6;
                                float f7;
                                block49: {
                                    int n7;
                                    float f8;
                                    block46: {
                                        block47: {
                                            block42: {
                                                fArray4 = new float[12];
                                                fArray3 = new float[12];
                                                fArray2 = new float[12];
                                                fArray = new float[12];
                                                f8 = 0.0f;
                                                f7 = 0.0f;
                                                n6 = 0;
                                                int n8 = Math.max(1, nArray.length / 4096);
                                                bl = Lyrics.a;
                                                float[] fArray5 = new float[3];
                                                for (n5 = 0; n5 < nArray.length; n5 += n8) {
                                                    float f9;
                                                    float f10;
                                                    float f11;
                                                    block45: {
                                                        int n9;
                                                        int n10;
                                                        block43: {
                                                            n10 = nArray[n5];
                                                            n7 = n10;
                                                            if (bl) break block42;
                                                            if (bl) break block43;
                                                            if (n7 >>> 24 < 128) {
                                                                continue;
                                                            }
                                                            n9 = n10 >> 16 & 0xFF;
                                                        }
                                                        n4 = n9;
                                                        n3 = n10 >> 8 & 0xFF;
                                                        n2 = n10 & 0xFF;
                                                        AlbumColor.hsv(n4, n3, n2, fArray5);
                                                        f7 += fArray5[2];
                                                        ++n6;
                                                        f11 = fArray5[1] * fArray5[2];
                                                        f8 += 1.0f;
                                                        float f12 = f11 - 0.0f;
                                                        f10 = f12 == 0.0f ? 0 : (f12 < 0.0f ? -1 : 1);
                                                        if (bl) break block45;
                                                        if (f10 <= 0) {
                                                            continue;
                                                        }
                                                        f10 = Math.min(11, (int)(fArray5[0] * 12.0f));
                                                    }
                                                    float f13 = f9 = f10;
                                                    fArray4[f13] = fArray4[f13] + f11;
                                                    float f14 = f9;
                                                    fArray3[f14] = fArray3[f14] + (float)n4 * f11;
                                                    float f15 = f9;
                                                    fArray2[f15] = fArray2[f15] + (float)n3 * f11;
                                                    float f16 = f9;
                                                    fArray[f16] = fArray[f16] + (float)n2 * f11;
                                                    if (!bl) continue;
                                                    boolean bl2 = !Module.a;
                                                    Module.a = bl2;
                                                    break;
                                                }
                                                n7 = n6;
                                            }
                                            if (bl) break block46;
                                            if (n7 != 0) break block47;
                                            return new int[]{AlbumColor.neutral(0.0f), AlbumColor.neutral(0.0f)};
                                        }
                                        n7 = 0;
                                    }
                                    n5 = n7;
                                    float f17 = 0.0f;
                                    n4 = 0;
                                    while (n4 < 12) {
                                        block48: {
                                            block50: {
                                                f17 += fArray4[n4];
                                                if (bl) break block48;
                                                f6 = fArray4[n4];
                                                f5 = fArray4[n5];
                                                if (bl) break block49;
                                                if (!(f6 > f5)) break block50;
                                                n5 = n4;
                                            }
                                            ++n4;
                                        }
                                        if (!bl) continue;
                                    }
                                    f6 = f17 / f8;
                                    f5 = 0.04f;
                                }
                                float f18 = f6 - f5;
                                f4 = f18 == 0.0f ? 0 : (f18 < 0.0f ? -1 : 1);
                                if (bl) break block51;
                                if (f4 >= 0) break block52;
                                n4 = AlbumColor.neutral(f7 / (float)n6);
                                return new int[]{n4, n4};
                            }
                            n4 = -1;
                            f4 = 0.0f;
                        }
                        n3 = (int)f4;
                        while (n3 < 12) {
                            block54: {
                                block55: {
                                    float f19;
                                    block58: {
                                        block59: {
                                            block56: {
                                                block57: {
                                                    n2 = Math.abs(n3 - n5);
                                                    if (bl) break block53;
                                                    if (bl) break block54;
                                                    if (Math.min(n2, 12 - n2) < 2) break block55;
                                                    float f20 = fArray4[n3] - fArray4[n5] * 0.15f;
                                                    f19 = f20 == 0.0f ? 0 : (f20 < 0.0f ? -1 : 1);
                                                    if (bl) break block56;
                                                    if (f19 >= 0) break block57;
                                                    break block55;
                                                }
                                                f19 = n4;
                                            }
                                            if (bl) break block58;
                                            if (f19 < 0) break block59;
                                            float f21 = fArray4[n3] - fArray4[n4];
                                            f19 = f21 == 0.0f ? 0 : (f21 > 0.0f ? 1 : -1);
                                            if (bl) break block58;
                                            if (f19 <= 0) break block55;
                                        }
                                        f19 = n3;
                                    }
                                    n4 = (int)f19;
                                }
                                ++n3;
                            }
                            if (!bl) continue;
                        }
                        f3 = fArray3[n5] / fArray4[n5];
                        f2 = fArray2[n5] / fArray4[n5];
                        f = fArray[n5];
                        if (bl) break block62;
                        n3 = AlbumColor.mute(f3, f2, f / fArray4[n5]);
                    }
                    if (n4 >= 0) break block60;
                    n = n3;
                    break block61;
                }
                f3 = fArray3[n4] / fArray4[n4];
                f2 = fArray2[n4] / fArray4[n4];
                f = fArray[n4] / fArray4[n4];
            }
            n = AlbumColor.mute(f3, f2, f);
        }
        n2 = n;
        return new int[]{n3, n2};
    }

    private static int neutral(float f) {
        int n = Math.round(255.0f * (0.16f + 0.07000001f * f));
        return n << 16 | n << 8 | n;
    }

    private static int mute(float f, float f2, float f3) {
        float[] fArray = new float[3];
        AlbumColor.hsv(Math.round(f), Math.round(f2), Math.round(f3), fArray);
        float f4 = Math.min(0.55f, fArray[1]);
        float f5 = 0.16f + 0.14000002f * fArray[2];
        return AlbumColor.rgb(fArray[0], f4, f5);
    }

    
    private static void hsv(int var0, int var1_1, int var2_2, float[] var3_3) {
        block25: {
            block23: {
                block24: {
                    block20: {
                        block22: {
                            block21: {
                                block19: {
                                    var5_4 = Math.max(var0, Math.max(var1_1, var2_2));
                                    var4_5 = Lyrics.a;
                                    var6_6 = Math.min(var0, Math.min(var1_1, var2_2));
                                    var7_7 = var5_4 - var6_6;
                                    cfr_temp_0 = var7_7 - 0.0f;
                                    v0 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
                                    if (var4_5) break block19;
                                    if (v0 == false) {
                                    }
                                    var8_8 = 0.0f;
                                    if (!var4_5) break block20;
lbl17:

                                    v0 = var5_4;
                                }
                                if (var4_5) break block21;
                                if (v0 == var0) {
                                }
                                var8_8 = ((float)(var1_1 - var2_2) / var7_7 + 6.0f) % 6.0f;
                                if (!var4_5) break block20;
lbl32:

                                v0 = var5_4;
                            }
                            if (var4_5) break block22;
                            if (v0 == var1_1) {
                            }
                            var8_8 = (float)(var2_2 - var0) / var7_7 + 2.0f;
                            if (!var4_5) break block20;
lbl47:

                            v0 = var0 - var1_1;
                        }
                        var8_8 = (float)v0 / var7_7 + 4.0f;
                    }
                    v7 = var3_3;
                    v8 = 0;
                    v9 = var8_8;
                    if (var4_5) break block23;
                    v7[v8] = v9 / 6.0f;
                    v7 = var3_3;
                    v8 = 1;
                    if (var5_4 != 0) break block24;
                    v11 = 0.0f;
                    break block25;
                }
                v9 = var7_7;
            }
            v11 = v9 / (float)var5_4;
        }
        v7[v8] = v11;
        var3_3[2] = (float)var5_4 / 255.0f;
    }

    private static int rgb(float f, float f2, float f3) {
        float f4;
        float f5;
        float f6;
        float f7 = f3 * f2;
        boolean bl = Lyrics.a;
        float f8 = f7 * (1.0f - Math.abs(f * 6.0f % 2.0f - 1.0f));
        float f9 = f3 - f7;
        int n = (int)(f * 6.0f) % 6;
        switch (n) {
            case 0: {
                f6 = f7;
                f5 = f8;
                f4 = 0.0f;
                if (!bl) break;
            }
            case 1: {
                f6 = f8;
                f5 = f7;
                f4 = 0.0f;
                if (!bl) break;
            }
            case 2: {
                f6 = 0.0f;
                f5 = f7;
                f4 = f8;
                if (!bl) break;
            }
            case 3: {
                f6 = 0.0f;
                f5 = f8;
                f4 = f7;
                if (!bl) break;
            }
            case 4: {
                f6 = f8;
                f5 = 0.0f;
                f4 = f7;
                if (!bl) break;
            }
            default: {
                f6 = f7;
                f5 = 0.0f;
                f4 = f8;
            }
        }
        return Math.round((f6 + f9) * 255.0f) << 16 | Math.round((f5 + f9) * 255.0f) << 8 | Math.round((f4 + f9) * 255.0f);
    }

    
    private static void lambda$get$0(String string, Supplier supplier) {
        boolean bl = Lyrics.a;
        try {
            Object object = CACHE;
            if (bl) return;
            if (object.size() > 64) {
                CACHE.clear();
            }
            object = CACHE.put(string, AlbumColor.of((int[])supplier.get()));
            return;
        }
        catch (RuntimeException runtimeException) {
        }
    }

    
    static {
        var9 = new String[3];
        var7_1 = 0;
        var6_2 = "C/T7bppc6D-}13m6I-b\u0005\u00028S+i\u0005\u00028S+i";
        var8_3 = "C/T7bppc6D-}13m6I-b\u0005\u00028S+i\u0005\u00028S+i".length();
        var5_4 = 19;
        var4_5 = -1;
lbl7:

        while (true) {
            v0 = ++var4_5;
            v1 = var6_2.substring(v0, v0 + var5_4).toCharArray();
            v2 = v1;
            v3 = v1.length;
            var10_6 = 0;
            break;
        }
lbl14:

        while (true) {
            var5_4 = var6_2.charAt(var4_5);
            continue;
            break;
        }
        do {
            v2 = v2;
            v4 = var10_6;
            v5 = v2[v4];
            switch (var10_6 % 7) {
                case 0: {
                    v6 = 2;
                    break;
                }
                case 1: {
                    v6 = 90;
                    break;
                }
                case 2: {
                    v6 = 38;
                    break;
                }
                case 3: {
                    v6 = 88;
                    break;
                }
                case 4: {
                    v6 = 16;
                    break;
                }
                case 5: {
                    v6 = 17;
                    break;
                }
                default: {
                    v6 = 80;
                }
            }
            v2[v4] = (char)(v5 ^ v6);
            ++var10_6;
lbl44:

            v3 = v3;
        } while (v3 > var10_6);
        var9[var7_1++] = new String(v2).intern();
        while ((var4_5 += var5_4) < var8_3)
lbl50:

        AlbumColor.a = var9;
        var2_7 = 2497792937255067338L;
        var0_8 = 5900056546411372230L ^ var2_7;
        AlbumColor.HUES = (int)var0_8;
        AlbumColor.CACHE = new ConcurrentHashMap<String, int[]>();
    }
}
