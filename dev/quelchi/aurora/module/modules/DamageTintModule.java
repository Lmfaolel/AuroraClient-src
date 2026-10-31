package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.render.DamageTint;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public final class DamageTintModule
extends Module {
    private static DamageTintModule instance;
    private final ColorSetting color;
    private final BooleanSetting chroma;
    private final NumberSetting speed;
    private final BooleanSetting gradient;
    private final ColorSetting gradientColor;
    private final BooleanSetting armor;
    private final BooleanSetting armorOnly;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public static DamageTintModule get() {
        return instance;
    }

    public DamageTintModule() {
        String[] stringArray = b;
        super(stringArray[5], stringArray[2], Category.COMBAT, false);
        this.color = new ColorSetting(stringArray[7], DamageTintModule.a(23069, 8204205748783283995L));
        this.chroma = new BooleanSetting(stringArray[8], false);
        this.speed = new NumberSetting(stringArray[6], 0.05, 1.0, 0.25, 0.05);
        this.gradient = new BooleanSetting(stringArray[3], false);
        this.gradientColor = new ColorSetting(stringArray[0], DamageTintModule.a(19012, 134493248359296835L));
        this.armor = new BooleanSetting(stringArray[4], true);
        this.armorOnly = new BooleanSetting(stringArray[1], false);
        instance = this;
        this.addSettings(this.color, this.chroma, this.speed, this.gradient, this.gradientColor, this.armor, this.armorOnly);
        this.chroma.children(this.speed);
        this.gradient.children(this.gradientColor);
        this.armor.children(this.armorOnly);
    }

    @Override
    public void onEnable() {
        DamageTint.refresh();
    }

    @Override
    public void onDisable() {
        DamageTint.refresh();
    }

    @Override
    public void onSettingsChanged() {
        DamageTint.refresh();
    }

    @Override
    public void onHudRender(DrawContext drawContext) {
        DamageTint.sync();
    }

    public boolean flashActive() {
        return this.isEnabled();
    }

    public int flashColorArgb() {
        block5: {
            DamageTintModule damageTintModule;
            block4: {
                boolean bl = AmbienceModule.b;
                damageTintModule = this;
                if (bl) break block4;
                if (damageTintModule.chroma.getValue()) break block5;
                damageTintModule = this;
            }
            return damageTintModule.color.getARGB();
        }
        float f = (float)((double)System.currentTimeMillis() * this.speed.getValue() / 2.0 % 360.0 / 360.0);
        return MathHelper.method_60599((float)f, (float)1.0f, (float)1.0f, (int)this.color.getA());
    }

    public int gradientColorArgb() {
        return this.gradientColor.getARGB();
    }

    public boolean gradientActive() {
        boolean bl;
        block8: {
            block7: {
                DamageTintModule damageTintModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    damageTintModule = this;
                    if (bl2) break block6;
                    if (!damageTintModule.isEnabled()) break block7;
                    damageTintModule = this;
                }
                bl = damageTintModule.gradient.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public boolean tintArmor() {
        boolean bl;
        block8: {
            block7: {
                DamageTintModule damageTintModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    damageTintModule = this;
                    if (bl2) break block6;
                    if (!damageTintModule.isEnabled()) break block7;
                    damageTintModule = this;
                }
                bl = damageTintModule.armor.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean armorOnly() {
        boolean bl;
        block11: {
            block9: {
                DamageTintModule damageTintModule;
                boolean bl2;
                block10: {
                    DamageTintModule damageTintModule2;
                    block8: {
                        damageTintModule2 = instance;
                        bl2 = AmbienceModule.b;
                        damageTintModule = damageTintModule2;
                        if (bl2) break block8;
                        if (damageTintModule == null) break block9;
                        damageTintModule = damageTintModule2;
                    }
                    if (bl2) break block10;
                    if (!damageTintModule.tintArmor()) break block9;
                    damageTintModule = damageTintModule2;
                }
                bl = damageTintModule.armorOnly.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[9];
                        var11_1 = 0;
                        var10_2 = "MUp;$Y\u0018~\u0007r0!S\u0004\nKU|0?\u001c\u0019dKh\u0016XBr0!S\u0004*Sy:mT\u0003xS19!]\u0005b\bMUp;$Y\u0018~\u0005KU|0?\u000bNF|>*YV^N\u007f+\u0005YWt:)";
                        var12_3 = "MUp;$Y\u0018~\u0007r0!S\u0004\nKU|0?\u001c\u0019dKh\u0016XBr0!S\u0004*Sy:mT\u0003xS19!]\u0005b\bMUp;$Y\u0018~\u0005KU|0?\u000bNF|>*YV^N\u007f+\u0005YWt:)".length();
                        var9_4 = 14;
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
                            var10_2 = "IH}0?\u0006IOc0 ]";
                            var12_3 = "IH}0?\u0006IOc0 ]".length();
                            var9_4 = 5;
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
                                v10 = 10;
                                break;
                            }
                            case 1: {
                                v10 = 39;
                                break;
                            }
                            case 2: {
                                v10 = 17;
                                break;
                            }
                            case 3: {
                                v10 = 95;
                                break;
                            }
                            case 4: {
                                v10 = 77;
                                break;
                            }
                            case 5: {
                                v10 = 60;
                                break;
                            }
                            default: {
                                v10 = 118;
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
                DamageTintModule.b = var13;
                var0_7 = 2392895507823165574L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "\u00bcf\u00c9\u00b1\u0090\u00b6\u00c5\u00c5/\u0080\u00eb*-\u00e3\u007f\u009d";
                var5_11 = "\u00bcf\u00c9\u00b1\u0090\u00b6\u00c5\u00c5/\u0080\u00eb*-\u00e3\u007f\u009d".length();
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
        DamageTintModule.d = var6_8;
        DamageTintModule.e = new Integer[2];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2507;
        if (e[n2] == null) {
            DamageTintModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
