package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.im.ImScreen;

public final class Themes$Look
extends Enum<Themes$Look> {
    public static final /* enum */ Themes$Look AURORA;
    public static final /* enum */ Themes$Look MIDNIGHT;
    public static final /* enum */ Themes$Look OLED;
    public static final /* enum */ Themes$Look DAYLIGHT;
    public static final /* enum */ Themes$Look ENDER;
    public static final /* enum */ Themes$Look NETHER;
    public static final /* enum */ Themes$Look DEEP_DARK;
    public static final /* enum */ Themes$Look CHERRY;
    public final String label;
    public final boolean plus;
    public final int bg;
    public final int accent;
    private static final Themes$Look[] $VALUES;
    private static final String[] a;

    public static Themes$Look[] values() {
        return (Themes$Look[])$VALUES.clone();
    }

    public static Themes$Look valueOf(String string) {
        return Enum.valueOf(Themes$Look.class, string);
    }

    private Themes$Look(String string2, boolean bl, int n2, int n3) {
        this.label = string2;
        this.plus = bl;
        this.bg = n2;
        this.accent = n3;
    }

    
    public static Themes$Look saved(String string) {
        boolean bl = ImScreen.a;
        String string2 = string;
        if (bl) return Themes$Look.valueOf(string2);
        if (string2 == null) {
            return AURORA;
        }
        try {
            string2 = string;
            return Themes$Look.valueOf(string2);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return AURORA;
        }
    }

    public static float savedGlass(String string, Float f) {
        if (f != null) {
            return Math.max(0.0f, Math.min(1.0f, f.floatValue()));
        }
        float f2 = a[4].equals(string) ? 1.0f : 0.0f;
        return f2;
    }

    private static Themes$Look[] $values() {
        return new Themes$Look[]{AURORA, MIDNIGHT, OLED, DAYLIGHT, ENDER, NETHER, DEEP_DARK, CHERRY};
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[17];
                        var11_1 = 0;
                        var10_2 = "\u000f\u0018s\r\b\u00045O%8C(4\u0006\u000e1B!4V\t\u0004\u0011s\u0019\u000e`\u0001\u0012\u001f\u0005\u0007\u0018w\u001a\u0002\u0006\u0001\u0001d\u0006\u0003e\u0005\u0005\u001ar\f\u0003\b\r=R'8C(4\u0005\u0005:R,#\u0006\u0003\u001cs\u001b\u0003}\b\u0004\u0015o\u0005\u0018c\b\u0014\u0006\u000e\u0011b\u0001\u0014v\b\r\u001dr\u0007\u0018c\b\u0014\u0004\u000f\u0018s\r\t\u00041S9q`!2?";
                        var12_3 = "\u000f\u0018s\r\b\u00045O%8C(4\u0006\u000e1B!4V\t\u0004\u0011s\u0019\u000e`\u0001\u0012\u001f\u0005\u0007\u0018w\u001a\u0002\u0006\u0001\u0001d\u0006\u0003e\u0005\u0005\u001ar\f\u0003\b\r=R'8C(4\u0005\u0005:R,#\u0006\u0003\u001cs\u001b\u0003}\b\u0004\u0015o\u0005\u0018c\b\u0014\u0006\u000e\u0011b\u0001\u0014v\b\r\u001dr\u0007\u0018c\b\u0014\u0004\u000f\u0018s\r\t\u00041S9q`!2?".length();
                        var9_4 = 4;
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
                            var10_2 = "\u0001!D&#E\f\u0003<S;#]`\u0007&Y?4";
                            var12_3 = "\u0001!D&#E\f\u0003<S;#]`\u0007&Y?4".length();
                            var9_4 = 6;
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
                                v10 = 64;
                                break;
                            }
                            case 1: {
                                v10 = 84;
                                break;
                            }
                            case 2: {
                                v10 = 54;
                                break;
                            }
                            case 3: {
                                v10 = 73;
                                break;
                            }
                            case 4: {
                                v10 = 81;
                                break;
                            }
                            case 5: {
                                v10 = 36;
                                break;
                            }
                            default: {
                                v10 = 64;
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
                Themes$Look.a = var13;
                var1_8 = 4326131721085549906L;
                var0_9 = new long[15];
                var4_10 = 0;
                var5_11 = "K#?\u0000&.\u008b\u00a8\u00f5\u0001\u00a6\u00a4&\u00d0mp\u0090\u00e1T\u0093&\u00c4pE\u0001E\u00c8a&\u00c3\u00ae\u00b4\u001c\u008cc\u0088&%'\u001c\u00f453]&\u00dfv\\\u00edd\u00d2q&\u001d\u0000\u00ad\u00aa\u0014\u00ad\u001c&4\u008d\u00a7\u00a8\u00f3F\u00ef&\u00ad\u001c\u00a6\u0092C\u0082\u0018&%\u00f2\u0092\u00eb\n\u00b6\u0090&\u00df{Xb+I\u0014&\u00c9qs\u00aa\r\u00d6w&\u0083?\u008d";
                var6_12 = "K#?\u0000&.\u008b\u00a8\u00f5\u0001\u00a6\u00a4&\u00d0mp\u0090\u00e1T\u0093&\u00c4pE\u0001E\u00c8a&\u00c3\u00ae\u00b4\u001c\u008cc\u0088&%'\u001c\u00f453]&\u00dfv\\\u00edd\u00d2q&\u001d\u0000\u00ad\u00aa\u0014\u00ad\u001c&4\u008d\u00a7\u00a8\u00f3F\u00ef&\u00ad\u001c\u00a6\u0092C\u0082\u0018&%\u00f2\u0092\u00eb\n\u00b6\u0090&\u00df{Xb+I\u0014&\u00c9qs\u00aa\r\u00d6w&\u0083?\u008d".length();
                var3_13 = 0;
                while (true) {
                    var7_14 = var5_11.substring(var3_13, var3_13 += 8).getBytes("ISO-8859-1");
                    v11 = var0_9;
                    v12 = var4_10++;
                    v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var3_13 < var6_12) continue;
                    var5_11 = "\u0003\u0012\u00f5G&\u00c0t^o\u0087\u001a\u00c2&\u0095\u00f1\u00ad";
                    var6_12 = "\u0003\u0012\u00f5G&\u00c0t^o\u0087\u001a\u00c2&\u0095\u00f1\u00ad".length();
                    var3_13 = 0;
                    while (true) {
                        var7_14 = var5_11.substring(var3_13, var3_13 += 8).getBytes("ISO-8859-1");
                        v11 = var0_9;
                        v12 = var4_10++;
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
                    if (var3_13 < var6_12) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var1_8;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        var8_6 = Themes$Look.a;
        Themes$Look.AURORA = new Themes$Look(var8_6[15], false, (int)var0_9[10], (int)var0_9[8]);
        Themes$Look.MIDNIGHT = new Themes$Look(var8_6[7], false, (int)var0_9[1], (int)var0_9[14]);
        Themes$Look.OLED = new Themes$Look(var8_6[13], false, 0, (int)var0_9[0]);
        Themes$Look.DAYLIGHT = new Themes$Look(var8_6[1], false, (int)var0_9[7], (int)var0_9[12]);
        Themes$Look.ENDER = new Themes$Look(var8_6[8], true, (int)var0_9[11], (int)var0_9[6]);
        Themes$Look.NETHER = new Themes$Look(var8_6[2], true, (int)var0_9[13], (int)var0_9[4]);
        Themes$Look.DEEP_DARK = new Themes$Look(var8_6[14], true, (int)var0_9[5], (int)var0_9[3]);
        Themes$Look.CHERRY = new Themes$Look(var8_6[16], true, (int)var0_9[2], (int)var0_9[9]);
        Themes$Look.$VALUES = Themes$Look.$values();
    }
}
