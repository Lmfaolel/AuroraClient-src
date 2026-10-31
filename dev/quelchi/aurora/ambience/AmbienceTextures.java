package dev.quelchi.aurora.ambience;

import dev.quelchi.aurora.ambience.AmbienceDimensions;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;

public final class AmbienceTextures {
    private static final Set<String> LAVA_TEXTURES;
    private static final Set<String> FIRE_TEXTURES;
    private static final Set<String> GRASS_BLOCK_TEXTURES;
    private static final Set<String> SHORT_GRASS_TEXTURES;
    private static final Set<String> TALL_GRASS_TEXTURES;
    private static final Map<NativeImage, Integer> PROCESSED_SIGNATURES;
    private static final String[] a;

    private AmbienceTextures() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void maybeTint(Identifier identifier, NativeImage nativeImage) {
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        AmbienceModule ambienceModule;
        int n6;
        block113: {
            block112: {
                Integer n7;
                block111: {
                    int n8;
                    block109: {
                        String string;
                        block110: {
                            block107: {
                                block108: {
                                    block105: {
                                        block106: {
                                            block103: {
                                                int n9;
                                                block104: {
                                                    block101: {
                                                        int n10;
                                                        block102: {
                                                            block99: {
                                                                block100: {
                                                                    block97: {
                                                                        int n11;
                                                                        block98: {
                                                                            AmbienceModule ambienceModule2;
                                                                            block96: {
                                                                                block95: {
                                                                                    n6 = AmbienceDimensions.a;
                                                                                    if (identifier != null && nativeImage != null) break block95;
                                                                                    return;
                                                                                }
                                                                                if (!a[0].equals(identifier.method_12836())) {
                                                                                    return;
                                                                                }
                                                                                ambienceModule = AmbienceModule.get();
                                                                                ambienceModule2 = ambienceModule;
                                                                                if (n6 != 0) break block96;
                                                                                if (ambienceModule2 == null) return;
                                                                                ambienceModule2 = ambienceModule;
                                                                            }
                                                                            if (!ambienceModule2.isEnabled()) {
                                                                                return;
                                                                            }
                                                                            string = AmbienceTextures.normalize(identifier.method_12832());
                                                                            n11 = GRASS_BLOCK_TEXTURES.contains(string);
                                                                            n5 = a[4].equals(string);
                                                                            n10 = SHORT_GRASS_TEXTURES.contains(string);
                                                                            n9 = TALL_GRASS_TEXTURES.contains(string);
                                                                            n4 = LAVA_TEXTURES.contains(string);
                                                                            n3 = FIRE_TEXTURES.contains(string);
                                                                            n8 = n11;
                                                                            if (n6 != 0) break block97;
                                                                            if (n8 != 0) break block98;
                                                                            n8 = n5;
                                                                            if (n6 != 0) break block97;
                                                                            if (n8 != 0) break block98;
                                                                            n8 = n10;
                                                                            if (n6 != 0) break block97;
                                                                            if (n8 != 0) break block98;
                                                                            n8 = n9;
                                                                            if (n6 != 0) break block97;
                                                                            if (n8 != 0) break block98;
                                                                            n8 = n4;
                                                                            if (n6 != 0) break block97;
                                                                            if (n8 != 0) break block98;
                                                                            n8 = n3;
                                                                            if (n6 != 0) break block97;
                                                                            if (n8 != 0) break block98;
                                                                            return;
                                                                        }
                                                                        n8 = n11;
                                                                    }
                                                                    if (n6 != 0) break block99;
                                                                    if (n8 != 0) break block100;
                                                                    n8 = n5;
                                                                    if (n6 != 0) break block101;
                                                                    if (n8 == 0) break block102;
                                                                }
                                                                n8 = ambienceModule.grassBlocks();
                                                            }
                                                            if (n6 != 0) break block101;
                                                            if (n8 != 0) break block102;
                                                            return;
                                                        }
                                                        n8 = n10;
                                                    }
                                                    if (n6 != 0) break block103;
                                                    if (n8 == 0) break block104;
                                                    n8 = ambienceModule.shortGrass();
                                                    if (n6 != 0) break block103;
                                                    if (n8 != 0) break block104;
                                                    return;
                                                }
                                                n8 = n9;
                                            }
                                            if (n6 != 0) break block105;
                                            if (n8 == 0) break block106;
                                            n8 = ambienceModule.tallGrass();
                                            if (n6 != 0) break block105;
                                            if (n8 != 0) break block106;
                                            return;
                                        }
                                        n8 = n4;
                                    }
                                    if (n6 != 0) break block107;
                                    if (n8 == 0) break block108;
                                    n8 = ambienceModule.lava();
                                    if (n6 != 0) break block107;
                                    if (n8 != 0) break block108;
                                    return;
                                }
                                n8 = n3;
                            }
                            if (n6 != 0) break block109;
                            if (n8 == 0) break block110;
                            n8 = ambienceModule.fire();
                            if (n6 != 0) break block109;
                            if (n8 != 0) break block110;
                            return;
                        }
                        n8 = Objects.hash(string, ambienceModule.grassBlocks(), ambienceModule.shortGrass(), ambienceModule.tallGrass(), ambienceModule.lava(), ambienceModule.fire(), ambienceModule.grassBlockRgb(), ambienceModule.shortGrassRgb(), ambienceModule.tallGrassRgb(), ambienceModule.lavaRgb(), ambienceModule.fireRgb());
                    }
                    n2 = n8;
                    Integer n12 = PROCESSED_SIGNATURES.get(nativeImage);
                    n7 = n12;
                    if (n6 != 0) break block111;
                    if (n7 == null) break block112;
                    n7 = n12;
                }
                n = n7;
                if (n6 != 0) break block113;
                if (n != n2) break block112;
                return;
            }
            n = nativeImage.method_4307();
        }
        int n13 = n;
        int n14 = nativeImage.method_4323();
        int n15 = 0;
        while (n15 < n14) {
            block114: {
                if (n6 != 0) return;
                for (int i = 0; i < n13; ++i) {
                    int n16;
                    int n17;
                    block117: {
                        block118: {
                            block115: {
                                block116: {
                                    n17 = nativeImage.method_61940(i, n15);
                                    if (n6 != 0) break block114;
                                    n16 = n4;
                                    if (n6 != 0) break block115;
                                    if (n16 == 0) break block116;
                                    nativeImage.method_61941(i, n15, AmbienceTextures.tintByLuminance(n17, ambienceModule.lavaRgb()));
                                    if (n6 == 0) continue;
                                }
                                n16 = n3;
                            }
                            if (n6 != 0) break block117;
                            if (n16 == 0) break block118;
                            nativeImage.method_61941(i, n15, AmbienceTextures.tintByLuminance(n17, ambienceModule.fireRgb()));
                            if (n6 == 0) continue;
                        }
                        n16 = n5;
                    }
                    if (n16 != 0) {
                        nativeImage.method_61941(i, n15, AmbienceTextures.tintGreenDominant(n17, ambienceModule.grassBlockRgb()));
                        if (n6 == 0) continue;
                    }
                    nativeImage.method_61941(i, n15, AmbienceTextures.toGrayscale(n17));
                    continue;
                }
                ++n15;
            }
            if (n6 == 0) continue;
        }
        PROCESSED_SIGNATURES.put(nativeImage, n2);
    }

    private static String normalize(String string) {
        String string2;
        block18: {
            String string3;
            block19: {
                int n;
                block16: {
                    block17: {
                        String string4;
                        block14: {
                            block15: {
                                String string5;
                                block12: {
                                    block13: {
                                        n = AmbienceDimensions.a;
                                        string5 = string;
                                        if (n != 0) break block12;
                                        if (string5 == null) break block13;
                                        string4 = string;
                                        if (n != 0) break block14;
                                        if (!string4.isBlank()) break block15;
                                    }
                                    string5 = "";
                                }
                                return string5;
                            }
                            string4 = string;
                        }
                        string3 = string4;
                        string2 = string3;
                        if (n != 0) break block16;
                        if (!string2.startsWith(a[1])) break block17;
                        String[] stringArray = a;
                        string3 = string3.substring(stringArray[22].length());
                    }
                    string2 = string3;
                }
                if (n != 0) break block18;
                if (!string2.endsWith(a[15])) break block19;
                string3 = string3.substring(0, string3.length() - 4);
            }
            string2 = string3;
        }
        return string2;
    }

    private static int toGrayscale(int n) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        int n6 = (n3 * 30 + n4 * 59 + n5 * 11 + 50) / 100;
        return n2 << 24 | n6 << 16 | n6 << 8 | n6;
    }

    private static int tintGreenDominant(int n, int n2) {
        int n3;
        block19: {
            block18: {
                int n4;
                block17: {
                    int n5;
                    int n6;
                    block16: {
                        block15: {
                            int n7;
                            int n8;
                            block14: {
                                int n9 = n >>> 16 & 0xFF;
                                int n10 = n >>> 8 & 0xFF;
                                int n11 = n & 0xFF;
                                int n12 = Math.max(n9, Math.max(n10, n11));
                                int n13 = Math.min(n9, Math.min(n10, n11));
                                n6 = n12 - n13;
                                n4 = AmbienceDimensions.a;
                                n5 = n10 * 100;
                                n8 = n9;
                                n7 = 75;
                                if (n4 != 0) break block14;
                                if (n5 < n8 * n7) break block15;
                                n5 = n10;
                                if (n4 != 0) break block16;
                                n8 = n11;
                                n7 = 2;
                            }
                            if (n5 < n8 - n7) break block15;
                            n5 = 1;
                            break block16;
                        }
                        n5 = 0;
                    }
                    int n14 = n5;
                    n3 = n14;
                    if (n4 != 0) break block17;
                    if (n3 == 0) break block18;
                    n3 = n6;
                }
                if (n4 != 0) break block19;
                if (n3 < 6) break block18;
                n3 = AmbienceTextures.tintByLuminance(n, n2);
                break block19;
            }
            n3 = n;
        }
        return n3;
    }

    private static int tintByLuminance(int n, int n2) {
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        int n7 = (n4 * 30 + n5 * 59 + n6 * 11 + 50) / 100;
        int n8 = (n7 * (n2 >>> 16 & 0xFF) + 127) / 255;
        int n9 = (n7 * (n2 >>> 8 & 0xFF) + 127) / 255;
        int n10 = (n7 * (n2 & 0xFF) + 127) / 255;
        return n3 << 24 | n8 << 16 | n9 << 8 | n10;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[24];
                var3_1 = 0;
                var2_2 = "-0\u0018\u0001EO-&-\t4<\u000e\u0010SO)3v\u0015\"5\u0019\u0007M\u0012?(6\u0004\u0010yY>9\u0006\u0011\u0016GN?\u0010\"5\u0019\u0007M\u0012?5>\u0017\u0016y^-.<\u0016\"5\u0019\u0007M\u0012+28\u0005\u0017y_ /:\u001d;UT(%\f\"5\u0019\u0007M\u0012*)+\u0013;\u0016\u0010\"5\u0019\u0007M\u0012 !/\u0017;UI%,5\n\"5\u0019\u0007M\u0012.5*\u001e\u000f\"5\u0019\u0007M\u0012 !/\u0017;@Q#7\u0018\"5\u0019\u0007M\u00128!5\u001a;BO5\u001f>\u0004\u0005UN\u001346\u0006\u0015\"5\u0019\u0007M\u0012+28\u0005\u0017y_ /:\u001d;RR<\n\"5\u0019\u0007M\u0012*%+\u0018\u001b\"5\u0019\u0007M\u00128!5\u001a;BO5\u001f>\u0004\u0005UN\u0013\"6\u0002\u0010IP\u0014\"5\u0019\u0007M\u00128!5\u001a;AO-3*)\u0010IM\u0011\"5\u0019\u0007M\u0012?/,\u001a;@T>%\u0006G\u0004n)\u0018\u0003\u001e\"5\u0019\u0007M\u0012+28\u0005\u0017y_ /:\u001d;UT(%\u0006\u0019\u0012CO ! \u0017\"5\u0019\u0007M\u0012 !+\u0011\u0001y[)27)\u0006II8/4\u0011\"5\u0019\u0007M\u0012?(6\u0004\u0010yZ>!*\u0005\u0011\"5\u0019\u0007M\u0012?/,\u001a;@T>%\u0006F\u0014\"5\u0019\u0007M\u0012 !+\u0011\u0001y[)27)\u0010IM\f\"5\u0019\u0007M\u0012*)+\u0013;\u0017";
                var4_3 = "-0\u0018\u0001EO-&-\t4<\u000e\u0010SO)3v\u0015\"5\u0019\u0007M\u0012?(6\u0004\u0010yY>9\u0006\u0011\u0016GN?\u0010\"5\u0019\u0007M\u0012?5>\u0017\u0016y^-.<\u0016\"5\u0019\u0007M\u0012+28\u0005\u0017y_ /:\u001d;UT(%\f\"5\u0019\u0007M\u0012*)+\u0013;\u0016\u0010\"5\u0019\u0007M\u0012 !/\u0017;UI%,5\n\"5\u0019\u0007M\u0012.5*\u001e\u000f\"5\u0019\u0007M\u0012 !/\u0017;@Q#7\u0018\"5\u0019\u0007M\u00128!5\u001a;BO5\u001f>\u0004\u0005UN\u001346\u0006\u0015\"5\u0019\u0007M\u0012+28\u0005\u0017y_ /:\u001d;RR<\n\"5\u0019\u0007M\u0012*%+\u0018\u001b\"5\u0019\u0007M\u00128!5\u001a;BO5\u001f>\u0004\u0005UN\u0013\"6\u0002\u0010IP\u0014\"5\u0019\u0007M\u00128!5\u001a;AO-3*)\u0010IM\u0011\"5\u0019\u0007M\u0012?/,\u001a;@T>%\u0006G\u0004n)\u0018\u0003\u001e\"5\u0019\u0007M\u0012+28\u0005\u0017y_ /:\u001d;UT(%\u0006\u0019\u0012CO ! \u0017\"5\u0019\u0007M\u0012 !+\u0011\u0001y[)27)\u0006II8/4\u0011\"5\u0019\u0007M\u0012?(6\u0004\u0010yZ>!*\u0005\u0011\"5\u0019\u0007M\u0012?/,\u001a;@T>%\u0006F\u0014\"5\u0019\u0007M\u0012 !+\u0011\u0001y[)27)\u0010IM\f\"5\u0019\u0007M\u0012*)+\u0013;\u0017".length();
                var1_4 = 9;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "4<\u000e\u0010SO)3v\u0017\"5\u0019\u0007M\u00128!5\u001a;AO-3*)\u0006II8/4";
                    var4_3 = "4<\u000e\u0010SO)3v\u0017\"5\u0019\u0007M\u00128!5\u001a;AO-3*)\u0006II8/4".length();
                    var1_4 = 9;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 64;
                        break;
                    }
                    case 1: {
                        v10 = 89;
                        break;
                    }
                    case 2: {
                        v10 = 118;
                        break;
                    }
                    case 3: {
                        v10 = 100;
                        break;
                    }
                    case 4: {
                        v10 = 38;
                        break;
                    }
                    case 5: {
                        v10 = 61;
                        break;
                    }
                    default: {
                        v10 = 76;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_7;
lbl65:

                v7 = v7;
            } while (v7 > var6_7);
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
        AmbienceTextures.a = var5;
        var0_6 = AmbienceTextures.a;
        AmbienceTextures.LAVA_TEXTURES = Set.of(var0_6[6], var0_6[8]);
        AmbienceTextures.FIRE_TEXTURES = Set.of(var0_6[5], var0_6[21], var0_6[19], var0_6[14]);
        AmbienceTextures.GRASS_BLOCK_TEXTURES = Set.of(var0_6[10], var0_6[16]);
        AmbienceTextures.SHORT_GRASS_TEXTURES = Set.of(var0_6[18], var0_6[11], var0_6[2], var0_6[7], var0_6[3]);
        AmbienceTextures.TALL_GRASS_TEXTURES = Set.of(var0_6[23], var0_6[13], var0_6[17], var0_6[20], var0_6[12], var0_6[9]);
        AmbienceTextures.PROCESSED_SIGNATURES = Collections.synchronizedMap(new WeakHashMap<K, V>());
    }
}
