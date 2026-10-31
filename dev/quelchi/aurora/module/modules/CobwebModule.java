package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cobweb.CobwebTint;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import net.minecraft.client.MinecraftClient;

public final class CobwebModule
extends Module {
    private static CobwebModule instance;
    private final NumberSetting opacity;
    private final ColorSetting color;
    private volatile boolean dirty;
    private volatile int bakedTint;
    private volatile double baked;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public static CobwebModule get() {
        return instance;
    }

    public CobwebModule() {
        String[] stringArray = b;
        super(stringArray[2], stringArray[3], Category.RENDER, true);
        this.opacity = new NumberSetting(stringArray[1], 0.0, 100.0, 35.0, 1.0);
        this.color = new ColorSetting(stringArray[0], -1, false);
        this.bakedTint = 0;
        this.baked = Double.NaN;
        instance = this;
        this.addSettings(this.opacity, this.color);
    }

    public double getAlpha() {
        return Math.max(0.0, Math.min(1.0, this.opacity.getValue() / 100.0));
    }

    public int getTintRgb() {
        return this.color.getRGB() & CobwebModule.a(2433, 5784433537058666633L);
    }

    private double effective() {
        double d;
        block6: {
            block5: {
                CobwebModule cobwebModule;
                block4: {
                    boolean bl = AmbienceModule.b;
                    cobwebModule = this;
                    if (bl) break block4;
                    if (!cobwebModule.isEnabled()) break block5;
                    cobwebModule = this;
                }
                d = cobwebModule.getAlpha();
                break block6;
            }
            d = 1.0;
        }
        return d;
    }

    private int effectiveTint() {
        int n;
        block6: {
            block5: {
                CobwebModule cobwebModule;
                block4: {
                    boolean bl = AmbienceModule.b;
                    cobwebModule = this;
                    if (bl) break block4;
                    if (!cobwebModule.isEnabled()) break block5;
                    cobwebModule = this;
                }
                n = cobwebModule.getTintRgb();
                break block6;
            }
            n = CobwebModule.a(14465, 7160665367299475848L);
        }
        return n;
    }

    public void markBaked() {
        this.baked = this.effective();
        this.bakedTint = this.effectiveTint();
    }

    private boolean stale() {
        int n;
        block18: {
            block20: {
                block19: {
                    boolean bl;
                    block16: {
                        block17: {
                            bl = AmbienceModule.b;
                            n = Aurora.booted;
                            if (bl) break block16;
                            if (n != 0) break block17;
                            return false;
                        }
                        n = Double.isNaN(this.baked);
                    }
                    if (bl) break block18;
                    if (n != 0) break block19;
                    double d = Math.abs(this.effective() - this.baked) - 1.0E-9;
                    n = d == 0.0 ? 0 : (d > 0.0 ? 1 : -1);
                    if (bl) break block18;
                    if (n > 0) break block19;
                    n = this.effectiveTint();
                    if (bl) break block18;
                    if (n == this.bakedTint) break block20;
                }
                n = 1;
                break block18;
            }
            n = 0;
        }
        return n != 0;
    }

    @Override
    public void onEnable() {
        block5: {
            CobwebModule cobwebModule;
            block4: {
                boolean bl = AmbienceModule.b;
                cobwebModule = this;
                if (bl) break block4;
                if (!cobwebModule.stale()) break block5;
                cobwebModule = this;
            }
            cobwebModule.reloadNow();
        }
    }

    @Override
    public void onDisable() {
        block5: {
            CobwebModule cobwebModule;
            block4: {
                boolean bl = AmbienceModule.b;
                cobwebModule = this;
                if (bl) break block4;
                if (!cobwebModule.stale()) break block5;
                cobwebModule = this;
            }
            cobwebModule.reloadNow();
        }
    }

    @Override
    public void onSettingsChanged() {
        if (Aurora.booted) {
            this.dirty = true;
        }
    }

    private void reloadNow() {
        block16: {
            MinecraftClient minecraftClient;
            block17: {
                MinecraftClient minecraftClient2;
                boolean bl;
                block15: {
                    int n;
                    block13: {
                        CobwebModule cobwebModule;
                        block14: {
                            bl = AmbienceModule.b;
                            cobwebModule = this;
                            n = 0;
                            if (bl) break block13;
                            cobwebModule.dirty = n;
                            if (Aurora.booted) break block14;
                            return;
                        }
                        this.baked = this.effective();
                        cobwebModule = this;
                        n = this.effectiveTint();
                    }
                    cobwebModule.bakedTint = n;
                    if (CobwebTint.retint()) {
                        return;
                    }
                    minecraftClient2 = MinecraftClient.method_1551();
                    minecraftClient = minecraftClient2;
                    if (bl) break block15;
                    if (minecraftClient == null) break block16;
                    minecraftClient = minecraftClient2;
                }
                if (bl) break block17;
                if (minecraftClient.method_1478() == null) break block16;
                minecraftClient = minecraftClient2;
            }
            minecraftClient.method_1521();
        }
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[4];
                        var11_1 = 0;
                        var10_2 = "s@\u0019]\u0019\u0007\u007f_\u0014Q\u0002UH";
                        var12_3 = "s@\u0019]\u0019\u0007\u007f_\u0014Q\u0002UH".length();
                        var9_4 = 5;
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
                            var10_2 = "s@\u0017E\u000eC\u0011dX\u0010S\u0000R\u001fcJ\u0010\u0012\u001fIC_Z\u0012ZK@_T\u000f\u0007W\bN]_]UQ\u0004CFUM\u0006";
                            var12_3 = "s@\u0017E\u000eC\u0011dX\u0010S\u0000R\u001fcJ\u0010\u0012\u001fIC_Z\u0012ZK@_T\u000f\u0007W\bN]_]UQ\u0004CFUM\u0006".length();
                            var9_4 = 13;
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
                    var14_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_6;
                        v9 = v6[v8];
                        switch (var14_6 % 7) {
                            case 0: {
                                v10 = 48;
                                break;
                            }
                            case 1: {
                                v10 = 47;
                                break;
                            }
                            case 2: {
                                v10 = 117;
                                break;
                            }
                            case 3: {
                                v10 = 50;
                                break;
                            }
                            case 4: {
                                v10 = 107;
                                break;
                            }
                            case 5: {
                                v10 = 33;
                                break;
                            }
                            default: {
                                v10 = 49;
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
                CobwebModule.b = var13;
                var0_7 = 8643619156664564203L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = ".w\u00a7\u008d\u00cbl\u008f\u009c6\u007f\u0088;%\u0003\u00be\u009d";
                var5_11 = ".w\u00a7\u008d\u00cbl\u008f\u009c6\u007f\u0088;%\u0003\u00be\u009d".length();
                var2_12 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl84:

                while (true) {
                    var6_8[v11] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block23;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v11 = var3_9++;
            while (true)
        }
        CobwebModule.d = var6_8;
        CobwebModule.e = new Integer[2];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7109;
        if (e[n2] == null) {
            CobwebModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
