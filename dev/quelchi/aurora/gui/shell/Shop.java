package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.cosmetics.content.Colorways;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.content.PremiumCapes;
import dev.quelchi.aurora.cosmetics.content.Trails;
import dev.quelchi.aurora.cosmetics.content.Trails$Trail;
import dev.quelchi.aurora.cosmetics.content.Wings;
import dev.quelchi.aurora.cosmetics.content.Wings$Wing;
import dev.quelchi.aurora.cosmetics.renderer.EffectList;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.render.EffectEngine$Effect;
import java.util.Locale;

final class Shop {
    static final int TILE_R;
    static final int[] RARITY_ARGB;
    private static final String[] a;

    private Shop() {
    }

    static boolean onSale(String string, String string2) {
        boolean bl;
        block8: {
            block7: {
                String string3;
                boolean bl2;
                block6: {
                    bl2 = SearchIndex.a;
                    string3 = string;
                    if (bl2) break block6;
                    if (!Cosmetics.forSale(string3, string2)) break block7;
                    string3 = string;
                }
                bl = Cosmetics.grantOnly(string3, string2);
                if (bl2) break block8;
                if (bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    static String fmt(long l) {
        return String.format(Locale.ROOT, a[3], l);
    }

    
    static String label(String string, String string2) {
        int n;
        boolean bl;
        String string3;
        block38: {
            string3 = Colorways.designOf(string2);
            String string4 = string;
            int n2 = -1;
            bl = SearchIndex.a;
            n = string4.hashCode();
            if (bl) break block38;
            switch (n) {
                case 3046099: {
                    n = string4.equals(a[6]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 0;
                        if (!bl) break;
                    }
                    break block38;
                }
                case 113136072: {
                    n = string4.equals(a[1]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 1;
                        if (!bl) break;
                    }
                    break block38;
                }
                case 110621190: {
                    n = string4.equals(a[15]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 2;
                        if (!bl) break;
                    }
                    break block38;
                }
                case -1972411761: {
                    n = string4.equals(a[14]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 3;
                        if (!bl) break;
                    }
                    break block38;
                }
                case -1213687257: {
                    n = string4.equals(a[16]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 4;
                        if (!bl) break;
                    }
                    break block38;
                }
                case 1258749700: {
                    n = string4.equals(a[17]) ? 1 : 0;
                    if (bl) break block38;
                    if (n == 0) break;
                    n2 = 5;
                }
            }
            n = n2;
        }
        switch (n) {
            case 0: {
                return PremiumCapes.displayName(string2);
            }
            case 1: {
                Record record;
                Record record2 = record = Wings.design(string3);
                if (bl) return ((Wings$Wing)record2).label();
                if (record2 != null) {
                    record2 = record;
                    return ((Wings$Wing)record2).label();
                }
                if (!bl) return string3;
            }
            case 2: {
                Record record;
                Record record3 = record = Trails.design(string3);
                if (bl) return ((Trails$Trail)record3).label();
                if (record3 != null) {
                    record3 = record;
                    return ((Trails$Trail)record3).label();
                }
                if (!bl) return string3;
            }
            case 3: 
            case 4: 
            case 5: {
                Record record;
                Record record4 = record = EffectList.of(string).design(string3);
                if (bl) return ((EffectEngine$Effect)record4).label();
                if (record4 == null) return string3;
                record4 = record;
                return ((EffectEngine$Effect)record4).label();
            }
        }
        return string3;
    }

    
    static void wear(String string, String string2) {
        int n;
        boolean bl;
        block41: {
            String string3 = string;
            bl = SearchIndex.a;
            int n2 = -1;
            n = string3.hashCode();
            if (bl) break block41;
            switch (n) {
                case 3046099: {
                    n = string3.equals(a[0]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 0;
                        if (!bl) break;
                    }
                    break block41;
                }
                case 113136072: {
                    n = string3.equals(a[2]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 1;
                        if (!bl) break;
                    }
                    break block41;
                }
                case 110621190: {
                    n = string3.equals(a[12]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 2;
                        if (!bl) break;
                    }
                    break block41;
                }
                case -1972411761: {
                    n = string3.equals(a[8]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 3;
                        if (!bl) break;
                    }
                    break block41;
                }
                case -1213687257: {
                    n = string3.equals(a[9]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 4;
                        if (!bl) break;
                    }
                    break block41;
                }
                case 1258749700: {
                    n = string3.equals(a[13]) ? 1 : 0;
                    if (bl) break block41;
                    if (n == 0) break;
                    n2 = 5;
                }
            }
            n = n2;
        }
        switch (n) {
            case 0: {
                Cosmetics.setOwnCape(string2);
                if (!bl) break;
            }
            case 1: {
                Cosmetics.setOwnWings(string2);
                if (!bl) break;
            }
            case 2: {
                Cosmetics.setOwnTrail(string2);
                if (!bl) break;
            }
            case 3: {
                Cosmetics.setOwnKillEffect(string2);
                if (!bl) break;
            }
            case 4: {
                Cosmetics.setOwnMaceEffect(string2);
                if (!bl) break;
            }
            case 5: {
                Cosmetics.setOwnHitEffect(string2);
                if (!bl) break;
            }
            default: {
                return;
            }
        }
        ConfigManager.save();
    }

    
    static String kind(String string) {
        String string2;
        int n;
        block35: {
            String string3 = string;
            int n2 = -1;
            boolean bl = SearchIndex.a;
            n = string3.hashCode();
            if (bl) break block35;
            switch (n) {
                case 3046099: {
                    n = string3.equals(a[6]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 0;
                        if (!bl) break;
                    }
                    break block35;
                }
                case 113136072: {
                    n = string3.equals(a[1]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 1;
                        if (!bl) break;
                    }
                    break block35;
                }
                case 110621190: {
                    n = string3.equals(a[15]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 2;
                        if (!bl) break;
                    }
                    break block35;
                }
                case -1972411761: {
                    n = string3.equals(a[14]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 3;
                        if (!bl) break;
                    }
                    break block35;
                }
                case -1213687257: {
                    n = string3.equals(a[16]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 4;
                        if (!bl) break;
                    }
                    break block35;
                }
                case 1258749700: {
                    n = string3.equals(a[17]) ? 1 : 0;
                    if (bl) break block35;
                    if (n == 0) break;
                    n2 = 5;
                }
            }
            n = n2;
        }
        switch (n) {
            case 0: {
                string2 = a[10];
                return string2;
            }
            case 1: {
                String[] stringArray = a;
                string2 = stringArray[7];
                return string2;
            }
            case 2: {
                String[] stringArray = a;
                string2 = stringArray[11];
                return string2;
            }
            case 3: {
                String[] stringArray = a;
                string2 = stringArray[4];
                return string2;
            }
            case 4: {
                String[] stringArray = a;
                string2 = stringArray[5];
                return string2;
            }
            case 5: {
                string2 = a[18];
                return string2;
            }
        }
        string2 = string;
        return string2;
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[19];
                        var11_1 = 0;
                        var10_2 = "=RH\u001d\u0005)ZV\u001f1\u0005)ZV\u001f1\u0003{\u001f\\\u000b\u0015ZT\u0014bUX8V[\f\u000b\u0013R[\u001dbUX8V[\f\u0004=RH\u001d\u0005\tZV\u001f1\n5ZT\u0014\u0007VX;PL\n3R[\u001d\u0007VX;PL\u0004\u001dRH\u001d\u0005\nAY\u0011.\u0005*AY\u0011.\t6ZL=$V[=G\n5ZT\u0014\u0007VX;PL\u0005*AY\u0011.\n3R[\u001d\u0007VX;PL";
                        var12_3 = "=RH\u001d\u0005)ZV\u001f1\u0005)ZV\u001f1\u0003{\u001f\\\u000b\u0015ZT\u0014bUX8V[\f\u000b\u0013R[\u001dbUX8V[\f\u0004=RH\u001d\u0005\tZV\u001f1\n5ZT\u0014\u0007VX;PL\n3R[\u001d\u0007VX;PL\u0004\u001dRH\u001d\u0005\nAY\u0011.\u0005*AY\u0011.\t6ZL=$V[=G\n5ZT\u0014\u0007VX;PL\u0005*AY\u0011.\n3R[\u001d\u0007VX;PL".length();
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
                            var10_2 = "6ZL=$V[=G\n\u0016ZLX'VX;PL";
                            var12_3 = "6ZL=$V[=G\n\u0016ZLX'VX;PL".length();
                            var9_4 = 9;
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
                    var14_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_6;
                        v9 = v6[v8];
                        switch (var14_6 % 7) {
                            case 0: {
                                v10 = 94;
                                break;
                            }
                            case 1: {
                                v10 = 51;
                                break;
                            }
                            case 2: {
                                v10 = 56;
                                break;
                            }
                            case 3: {
                                v10 = 120;
                                break;
                            }
                            case 4: {
                                v10 = 66;
                                break;
                            }
                            case 5: {
                                v10 = 48;
                                break;
                            }
                            default: {
                                v10 = 62;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_6;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_6);
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
                Shop.a = var13;
                var1_7 = 8651504731908876967L;
                var0_8 = new long[5];
                var4_9 = 0;
                var5_10 = "6U$\u00dcN\n*\u00a1d`\u009f\u00f0\u00b1\u00e2\u0090\u009d\u0095\u00de\u00bc\u009a\u00b1D\u00a1X";
                var6_11 = "6U$\u00dcN\n*\u00a1d`\u009f\u00f0\u00b1\u00e2\u0090\u009d\u0095\u00de\u00bc\u009a\u00b1D\u00a1X".length();
                var3_12 = 0;
                while (true) {
                    var7_13 = var5_10.substring(var3_12, var3_12 += 8).getBytes("ISO-8859-1");
                    v11 = var0_8;
                    v12 = var4_9++;
                    v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var3_12 < var6_11) continue;
                    var5_10 = "\u00b4c\u001bl\u00b1Q\u00fa\u00cd\u0002\u008e\u00d8}\u00b1\u00ce\u00e2u";
                    var6_11 = "\u00b4c\u001bl\u00b1Q\u00fa\u00cd\u0002\u008e\u00d8}\u00b1\u00ce\u00e2u".length();
                    var3_12 = 0;
                    while (true) {
                        var7_13 = var5_10.substring(var3_12, var3_12 += 8).getBytes("ISO-8859-1");
                        v11 = var0_8;
                        v12 = var4_9++;
                        v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var3_12 < var6_11) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var1_7;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        Shop.TILE_R = (int)var0_8[0];
        Shop.RARITY_ARGB = new int[]{(int)var0_8[4], (int)var0_8[3], (int)var0_8[2], (int)var0_8[1]};
    }
}
