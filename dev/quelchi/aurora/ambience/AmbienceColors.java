package dev.quelchi.aurora.ambience;

import dev.quelchi.aurora.ambience.AmbienceDimensions;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.ColorResolver;

public final class AmbienceColors {
    public static final Block[] SHORT_GRASS_TARGETS;
    public static final Block[] TALL_GRASS_TARGETS;
    public static final Block[] DRY_SHORT_GRASS_TARGETS;
    public static final Block[] DRY_TALL_GRASS_TARGETS;
    public static final Block[] BIOME_LEAF_TARGETS;
    public static final Block[] NEVER_TINTED_LEAVES;
    private static final long[] a;
    private static final Integer[] b;

    private AmbienceColors() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isShortGrassLike(Block block) {
        Block block2;
        block26: {
            int n;
            block25: {
                block24: {
                    block23: {
                        block22: {
                            n = AmbienceDimensions.a;
                            block2 = block;
                            if (n != 0) break block22;
                            if (block2 == Blocks.field_10479) return true;
                            block2 = block;
                        }
                        if (n != 0) break block23;
                        if (block2 == Blocks.field_10112) return true;
                        block2 = block;
                    }
                    if (n != 0) break block24;
                    if (block2 == Blocks.field_10128) return true;
                    block2 = block;
                }
                if (n != 0) break block25;
                if (block2 == Blocks.field_10424) return true;
                block2 = block;
            }
            if (n != 0) break block26;
            if (block2 == Blocks.field_56562) return true;
            block2 = block;
        }
        if (block2 != Blocks.field_56454) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isTallGrassLike(Block block) {
        Block block2;
        block11: {
            int n;
            block10: {
                n = AmbienceDimensions.a;
                block2 = block;
                if (n != 0) break block10;
                if (block2 == Blocks.field_10214) return true;
                block2 = block;
            }
            if (n != 0) break block11;
            if (block2 == Blocks.field_10313) return true;
            block2 = block;
        }
        if (block2 != Blocks.field_56563) return false;
        return true;
    }

    
    public static int resolve(BlockRenderView var0, BlockPos var1_1, ColorResolver var2_2) {
        block94: {
            block93: {
                block90: {
                    block91: {
                        block92: {
                            block89: {
                                block73: {
                                    block74: {
                                        block87: {
                                            block88: {
                                                block85: {
                                                    block86: {
                                                        block84: {
                                                            block81: {
                                                                block83: {
                                                                    block82: {
                                                                        block80: {
                                                                            block78: {
                                                                                block79: {
                                                                                    block77: {
                                                                                        block75: {
                                                                                            block76: {
                                                                                                block72: {
                                                                                                    block70: {
                                                                                                        block71: {
                                                                                                            block69: {
                                                                                                                var4_3 = AmbienceModule.get();
                                                                                                                var3_4 = AmbienceDimensions.a;
                                                                                                                v0 = var4_3;
                                                                                                                if (var3_4 != 0) break block69;
                                                                                                                if (v0 != null) {
                                                                                                                }
                                                                                                                v0 = var4_3;
                                                                                                            }
                                                                                                            v2 = (int)v0.isEnabled();
                                                                                                            if (var3_4 != 0) break block70;
                                                                                                            if (v2 == 0) break block71;
                                                                                                            if (var0 == null) break block71;
                                                                                                            if (var1_1 != null) break block72;
                                                                                                        }
                                                                                                        v2 = -1;
                                                                                                    }
                                                                                                    return v2;
                                                                                                }
                                                                                                v7 = var2_2;
                                                                                                if (var3_4 != 0) break block73;
                                                                                                if (v7 != BiomeColors.field_5665) break block74;
                                                                                                var5_5 = var0.method_8320(var1_1).method_26204();
                                                                                                v9 = var5_5;
                                                                                                if (var3_4 != 0) break block75;
                                                                                                if (v9 != Blocks.field_10219) break block76;
                                                                                                v11 = var4_3.grassBlocks();
                                                                                                if (var3_4 != 0) break block77;
                                                                                                if (!v11) break block76;
                                                                                                return var4_3.grassBlockRgb();
                                                                                            }
                                                                                            v9 = var5_5;
                                                                                        }
                                                                                        if (var3_4 != 0) break block78;
                                                                                        v11 = AmbienceColors.isShortGrassLike(v9);
                                                                                    }
                                                                                    if (!v11) break block79;
                                                                                    v16 = var4_3.shortGrass();
                                                                                    if (var3_4 != 0) break block80;
                                                                                    if (!v16) break block79;
                                                                                    return var4_3.shortGrassRgb();
                                                                                }
                                                                                v9 = var5_5;
                                                                            }
                                                                            v16 = AmbienceColors.isTallGrassLike(v9);
                                                                        }
                                                                        if (var3_4 != 0) break block81;
                                                                        if (!v16) break block82;
                                                                        v21 = var4_3;
                                                                        if (var3_4 != 0) break block83;
                                                                        if (!v21.tallGrass()) break block82;
                                                                        return var4_3.tallGrassRgb();
                                                                    }
                                                                    v21 = var4_3;
                                                                }
                                                                if (var3_4 != 0) break block84;
                                                                v16 = v21.grassBlocks();
                                                            }
                                                            if (v16) {
                                                                return var4_3.grassBlockRgb();
                                                            }
                                                            v21 = var4_3;
                                                        }
                                                        if (var3_4 != 0) break block85;
                                                        if (!v21.shortGrass()) break block86;
                                                        return var4_3.shortGrassRgb();
                                                    }
                                                    v21 = var4_3;
                                                }
                                                v29 = v21.tallGrass();
                                                if (var3_4 != 0) break block87;
                                                if (v29 == 0) break block88;
                                                return var4_3.tallGrassRgb();
                                            }
                                            v29 = -1;
                                        }
                                        return v29;
                                    }
                                    v7 = var2_2;
                                }
                                if (var3_4 != 0) break block89;
                                if (v7 != BiomeColors.field_5664) {
                                }
                                v7 = var2_2;
                            }
                            if (var3_4 != 0) break block90;
                            if (v7 != BiomeColors.field_57104) break block91;
lbl192:

                            v35 = var4_3;
                            if (var3_4 != 0) break block92;
                            if (!v35.leaves()) break block91;
                            v35 = var4_3;
                        }
                        return v35.leafRgb();
                    }
                    v7 = var2_2;
                }
                if (v7 != BiomeColors.field_5666) break block93;
                v39 = (int)var4_3.water();
                if (var3_4 != 0) break block94;
                if (v39 == 0) break block93;
                return var4_3.waterRgb();
            }
            v39 = -1;
        }
        return v39;
    }

    public static int resolveFogRgb(ClientWorld clientWorld) {
        int n;
        block32: {
            block33: {
                int n2;
                AmbienceModule ambienceModule;
                block36: {
                    ClientWorld clientWorld2;
                    block34: {
                        block35: {
                            block28: {
                                block29: {
                                    AmbienceModule ambienceModule2;
                                    block30: {
                                        block31: {
                                            ambienceModule = AmbienceModule.get();
                                            n2 = AmbienceDimensions.a;
                                            clientWorld2 = clientWorld;
                                            if (n2 != 0) break block28;
                                            if (!AmbienceDimensions.isOverworldLike(clientWorld2)) break block29;
                                            ambienceModule2 = ambienceModule;
                                            if (n2 != 0) break block30;
                                            if (!ambienceModule2.skyGradient()) break block31;
                                            return ambienceModule.gradientRgb();
                                        }
                                        ambienceModule2 = ambienceModule;
                                    }
                                    n = ambienceModule2.overworldSky();
                                    if (n2 != 0) break block32;
                                    if (n == 0) break block33;
                                    return ambienceModule.overworldSkyRgb();
                                }
                                clientWorld2 = clientWorld;
                            }
                            if (n2 != 0) break block34;
                            if (!AmbienceDimensions.isNether(clientWorld2)) break block35;
                            n = ambienceModule.netherSky();
                            if (n2 != 0) break block36;
                            if (n == 0) break block35;
                            return ambienceModule.netherRgb();
                        }
                        clientWorld2 = clientWorld;
                    }
                    n = AmbienceDimensions.isEnd(clientWorld2);
                }
                if (n2 != 0) break block32;
                if (n == 0) break block33;
                n = ambienceModule.endSky() ? 1 : 0;
                if (n2 != 0) break block32;
                if (n == 0) break block33;
                return ambienceModule.endRgb();
            }
            n = -1;
        }
        return n;
    }

    public static int withFullAlpha(int n) {
        return AmbienceColors.a(7011, 6744548281464307556L) | n & AmbienceColors.a(18618, 8869768387801148606L);
    }

    public static int scaleRgb(int n, float f) {
        int n2 = AmbienceColors.clamp(Math.round((float)(n >> 16 & 0xFF) * f), 0, 255);
        int n3 = AmbienceColors.clamp(Math.round((float)(n >> 8 & 0xFF) * f), 0, 255);
        int n4 = AmbienceDimensions.a;
        int n5 = AmbienceColors.clamp(Math.round((float)(n & 0xFF) * f), 0, 255);
        int n6 = n & AmbienceColors.a(878, 6025289872812386152L) | n2 << 16 | n3 << 8 | n5;
        if (Module.a) {
            AmbienceDimensions.a = ++n4;
        }
        return n6;
    }

    public static int lerpArgb(int n, int n2, float f) {
        int n3;
        block4: {
            boolean bl;
            block6: {
                block5: {
                    f = Math.max(0.0f, Math.min(1.0f, f));
                    int n4 = AmbienceDimensions.a;
                    int n5 = Math.round(AmbienceColors.lerp(n >>> 24 & 0xFF, n2 >>> 24 & 0xFF, f));
                    int n6 = Math.round(AmbienceColors.lerp(n >> 16 & 0xFF, n2 >> 16 & 0xFF, f));
                    int n7 = Math.round(AmbienceColors.lerp(n >> 8 & 0xFF, n2 >> 8 & 0xFF, f));
                    int n8 = Math.round(AmbienceColors.lerp(n & 0xFF, n2 & 0xFF, f));
                    n3 = n5 << 24 | n6 << 16 | n7 << 8 | n8;
                    if (n4 == 0) break block4;
                    if (!Module.a) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            Module.a = bl;
        }
        return n3;
    }

    private static float lerp(float f, float f2, float f3) {
        return f + (f2 - f) * f3;
    }

    private static int clamp(int n, int n2, int n3) {
        return Math.max(n2, Math.min(n3, n));
    }

    
    static {
        long l = 5379003717137988350L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "\u00e35\u00d2\u0007\u001b>]\u0096)yU\u00f0\u00d5\u00a5E\u009a\u00fa9\u00b6\u0014g\u009ci\u00bf";
        int n2 = "\u00e35\u00d2\u0007\u001b>]\u0096)yU\u00f0\u00d5\u00a5E\u009a\u00fa9\u00b6\u0014g\u009ci\u00bf".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Integer[3];
        SHORT_GRASS_TARGETS = new Block[]{Blocks.field_10479, Blocks.field_10112, Blocks.field_10128, Blocks.field_56454};
        TALL_GRASS_TARGETS = new Block[]{Blocks.field_10214, Blocks.field_10313};
        DRY_SHORT_GRASS_TARGETS = new Block[]{Blocks.field_56562};
        DRY_TALL_GRASS_TARGETS = new Block[]{Blocks.field_56563};
        BIOME_LEAF_TARGETS = new Block[]{Blocks.field_10503, Blocks.field_10335, Blocks.field_10098, Blocks.field_10035, Blocks.field_37551, Blocks.field_10597};
        NEVER_TINTED_LEAVES = new Block[]{Blocks.field_42731, Blocks.field_54714, Blocks.field_28673, Blocks.field_28674};
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2406;
        if (b[n2] == null) {
            AmbienceColors.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
