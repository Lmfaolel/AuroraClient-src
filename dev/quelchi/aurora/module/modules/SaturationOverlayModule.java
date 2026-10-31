package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.gui.IconTex;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;

public final class SaturationOverlayModule
extends Module {
    private static SaturationOverlayModule instance;
    private final ColorSetting color;
    private final NumberSetting opacity;
    private final BooleanSetting hideWhenFull;
    private static final int SHANKS;
    private static final int ICON_PX;
    private static final int STEP;
    private static final int[][] Q1;
    private static final int[][] Q2;
    private static final int[][] Q3;
    private static final int[][] FULL;
    private static final int[][][] FRAMES;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public SaturationOverlayModule() {
        String[] stringArray = b;
        super(stringArray[8], stringArray[23], Category.HUD, false);
        this.color = new ColorSetting(stringArray[26], SaturationOverlayModule.a(18606, 3565168648018272899L), false);
        this.opacity = new NumberSetting(stringArray[9], 10.0, 100.0, 85.0, 5.0);
        this.hideWhenFull = new BooleanSetting(stringArray[14], false);
        instance = this;
        this.addSettings(this.color, this.opacity, this.hideWhenFull);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void drawOnFood(DrawContext drawContext, PlayerEntity playerEntity, int n, int n2) {
        float f;
        float f2;
        boolean bl;
        SaturationOverlayModule saturationOverlayModule;
        block21: {
            SaturationOverlayModule saturationOverlayModule2;
            block19: {
                block20: {
                    float f3;
                    block17: {
                        block18: {
                            SaturationOverlayModule saturationOverlayModule3;
                            block16: {
                                saturationOverlayModule = instance;
                                bl = AmbienceModule.b;
                                saturationOverlayModule3 = saturationOverlayModule;
                                if (bl) break block16;
                                if (saturationOverlayModule3 == null) return;
                                saturationOverlayModule3 = saturationOverlayModule;
                            }
                            if (!saturationOverlayModule3.isEnabled()) {
                                return;
                            }
                            f2 = playerEntity.method_7344().method_7589();
                            float f4 = f2 - 0.0f;
                            f3 = f4 == 0.0f ? 0 : (f4 < 0.0f ? -1 : 1);
                            if (bl) break block17;
                            if (f3 > 0) break block18;
                            return;
                        }
                        saturationOverlayModule2 = saturationOverlayModule;
                        if (bl) break block19;
                        f3 = (float)saturationOverlayModule2.hideWhenFull.getValue();
                    }
                    if (f3 == false) break block20;
                    f = f2;
                    if (bl) break block21;
                    if (!(f >= 20.0f)) break block20;
                    return;
                }
                saturationOverlayModule2 = saturationOverlayModule;
            }
            f = (float)(saturationOverlayModule2.opacity.getValueInt() * 255) / 100.0f;
        }
        int n3 = Math.round(f);
        int n4 = n3 << 24 | saturationOverlayModule.color.getRGB();
        int n5 = (int)Math.ceil(f2 / 2.0f);
        for (int i = 0; i < Math.min(n5, 10); ++i) {
            float f5 = f2 / 2.0f - (float)i;
            IconTex.drawScaled(drawContext, FRAMES[SaturationOverlayModule.frameFor(f5)], n2 - i * 8 - 9, n, n4, false, 1.0f);
            if (!bl) continue;
        }
    }

    private static int frameFor(float f) {
        float f2;
        block16: {
            block17: {
                float f3;
                float f4;
                boolean bl;
                block14: {
                    block15: {
                        block12: {
                            block13: {
                                bl = AmbienceModule.b;
                                f4 = f;
                                f3 = 1.0f;
                                if (bl) break block12;
                                if (!(f4 >= f3)) break block13;
                                return 3;
                            }
                            f4 = f;
                            f3 = 0.5f;
                        }
                        if (bl) break block14;
                        if (!(f4 > f3)) break block15;
                        return 2;
                    }
                    f4 = f;
                    f3 = 0.25f;
                }
                float f5 = f4 - f3;
                f2 = f5 == 0.0f ? 0 : (f5 > 0.0f ? 1 : -1);
                if (bl) break block16;
                if (f2 <= 0) break block17;
                return 1;
            }
            f2 = 0.0f;
        }
        return (int)f2;
    }

    private static int[][] g(String ... stringArray) {
        int[][] nArray = new int[stringArray.length][stringArray[0].length()];
        block4: for (int i = 0; i < stringArray.length; ++i) {
            int n = 0;
            while (true) {
                int n2;
                block7: {
                    block6: {
                        if (n >= stringArray[i].length()) continue block4;
                        int[] nArray2 = nArray[i];
                        int n3 = n;
                        if (stringArray[i].charAt(n) != '#') break block6;
                        n2 = 1;
                        break block7;
                    }
                    n2 = 0;
                }
                nArray2[n3] = n2;
                ++n;
            }
        }
        return nArray;
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[30];
                        var11_1 = 0;
                        var10_2 = ")e\t\u001eU\u0003\u001d)e\t)e\t\u001eU\u0003\u001d)e\t)h\t\u001eU\u000e\u0010)e\t)e\t\u0013X\u0003\u001d$h\t)e\t\u001eU\u0003\u001d$h\t)e\t\u001eU\u000e\u0010)e\t)e\t\u001eU\u000e\u0010$e\t)e\t\u001eU\u000e\u0010$e\u0012T*SE\tAGn$I\u00104VVu'FI\u0007H;FS\u0012TJ\t)e\u0004\u0013U\u000e\u001d)e\t)e\u0004\u001eU\u000e\u0010)e\t)e\t\u001eX\u000e\u001d)e\t)e\t\u001eU\u000e\u0010)e\u000eO\"CU[W[b%\u0007V\u000eL_\t$e\t\u001eU\u000e\u0010)e\t)h\t\u001eX\u000e\u001d)e\t)e\t\u0013X\u0003\u001d$h\t)e\t\u001eU\u000e\u001d)e\t)e\t\u0013U\u000e\u001d)e\t)e\t\u001eU\u000e\u001d)e\t)e\u0004\u0013U\u000e\u001d)e\t$e\t\u001eU\u0003\u001d)e\u001eT*SE\tAGn$I\u0010\u0014VVukSX\u001e\u0000[r%@U\t\u0000Qf9\t)e\u0004\u001eU\u000e\u0010)e\t)e\t\u001eX\u000e\u001d)e\u0005D$K_\t\t)e\t\u001eX\u0003\u001d$h";
                        var12_3 = ")e\t\u001eU\u0003\u001d)e\t)e\t\u001eU\u0003\u001d)e\t)h\t\u001eU\u000e\u0010)e\t)e\t\u0013X\u0003\u001d$h\t)e\t\u001eU\u0003\u001d$h\t)e\t\u001eU\u000e\u0010)e\t)e\t\u001eU\u000e\u0010$e\t)e\t\u001eU\u000e\u0010$e\u0012T*SE\tAGn$I\u00104VVu'FI\u0007H;FS\u0012TJ\t)e\u0004\u0013U\u000e\u001d)e\t)e\u0004\u001eU\u000e\u0010)e\t)e\t\u001eX\u000e\u001d)e\t)e\t\u001eU\u000e\u0010)e\u000eO\"CU[W[b%\u0007V\u000eL_\t$e\t\u001eU\u000e\u0010)e\t)h\t\u001eX\u000e\u001d)e\t)e\t\u0013X\u0003\u001d$h\t)e\t\u001eU\u000e\u001d)e\t)e\t\u0013U\u000e\u001d)e\t)e\t\u001eU\u000e\u001d)e\t)e\u0004\u0013U\u000e\u001d)e\t$e\t\u001eU\u0003\u001d)e\u001eT*SE\tAGn$I\u0010\u0014VVukSX\u001e\u0000[r%@U\t\u0000Qf9\t)e\u0004\u001eU\u000e\u0010)e\t)e\t\u001eX\u000e\u001d)e\u0005D$K_\t\t)e\t\u001eX\u0003\u001d$h".length();
                        var9_4 = 9;
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
                            var10_2 = ")e\t\u001eU\u000e\u0010)h\t)e\t\u001eU\u000e\u0010)h";
                            var12_3 = ")e\t\u001eU\u000e\u0010)h\t)e\t\u001eU\u000e\u0010)h".length();
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
                    var14_7 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_7;
                        v9 = v6[v8];
                        switch (var14_7 % 7) {
                            case 0: {
                                v10 = 7;
                                break;
                            }
                            case 1: {
                                v10 = 75;
                                break;
                            }
                            case 2: {
                                v10 = 39;
                                break;
                            }
                            case 3: {
                                v10 = 48;
                                break;
                            }
                            case 4: {
                                v10 = 123;
                                break;
                            }
                            case 5: {
                                v10 = 32;
                                break;
                            }
                            default: {
                                v10 = 51;
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
                SaturationOverlayModule.b = var13;
                var0_8 = 8120871704968882620L;
                var6_9 = new long[4];
                var3_10 = 0;
                var4_11 = "\u0004\u0006\u00c2\u00e5e\u0013}\u00de\u00ed\u00eb8o\nG\u0012N";
                var5_12 = "\u0004\u0006\u00c2\u00e5e\u0013}\u00de\u00ed\u00eb8o\nG\u0012N".length();
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
                    var4_11 = ")\u008f,q\u00eb\u00b5\u001fe^\u0013bz\u00bapZu";
                    var5_12 = ")\u008f,q\u00eb\u00b5\u001fe^\u0013bz\u00bapZu".length();
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
        SaturationOverlayModule.d = var6_9;
        SaturationOverlayModule.e = new Integer[4];
        SaturationOverlayModule.SHANKS = SaturationOverlayModule.a(32327, 2771576740750005352L);
        SaturationOverlayModule.STEP = SaturationOverlayModule.a(4564, 7070464060234923002L);
        SaturationOverlayModule.ICON_PX = SaturationOverlayModule.a(23020, 2693236426258035648L);
        v16 = new String[9];
        var8_6 = SaturationOverlayModule.b;
        v16[0] = var8_6[20];
        v16[1] = var8_6[18];
        v16[2] = var8_6[18];
        v16[3] = var8_6[18];
        v16[4] = var8_6[13];
        v16[5] = var8_6[5];
        v16[6] = var8_6[4];
        v16[7] = var8_6[29];
        v16[8] = var8_6[7];
        SaturationOverlayModule.Q1 = SaturationOverlayModule.g(v16);
        SaturationOverlayModule.Q2 = SaturationOverlayModule.g(new String[]{var8_6[19], var8_6[12], var8_6[1], var8_6[5], var8_6[5], var8_6[5], var8_6[27], var8_6[28], var8_6[6]});
        SaturationOverlayModule.Q3 = SaturationOverlayModule.g(new String[]{var8_6[21], var8_6[25], var8_6[0], var8_6[5], var8_6[5], var8_6[24], var8_6[3], var8_6[28], var8_6[6]});
        SaturationOverlayModule.FULL = SaturationOverlayModule.g(new String[]{var8_6[10], var8_6[16], var8_6[22], var8_6[15], var8_6[2], var8_6[11], var8_6[17], var8_6[28], var8_6[6]});
        SaturationOverlayModule.FRAMES = new int[][][]{SaturationOverlayModule.Q1, SaturationOverlayModule.Q2, SaturationOverlayModule.Q3, SaturationOverlayModule.FULL};
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6A2F;
        if (e[n2] == null) {
            SaturationOverlayModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
