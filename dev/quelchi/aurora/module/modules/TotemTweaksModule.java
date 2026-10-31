package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.TotemTweaksModule$ParticleStyle;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import java.util.concurrent.ThreadLocalRandom;

public final class TotemTweaksModule
extends Module {
    private static TotemTweaksModule instance;
    private final NumberSetting handSize;
    private final NumberSetting popSize;
    private final BooleanSetting centrePop;
    private final NumberSetting popTicks;
    private final NumberSetting opacity;
    private final NumberSetting popOpacity;
    private final BooleanSetting noEquipAnimation;
    private final BooleanSetting customParticles;
    private final ColorSetting particleColor;
    private final BooleanSetting multiColor;
    private final ColorSetting particleColor2;
    private final ColorSetting particleColor3;
    private final BooleanSetting hideOwnParticles;
    private final EnumSetting<TotemTweaksModule$ParticleStyle> particleStyle;
    private final NumberSetting particleSpeed;
    private final NumberSetting particleSize;
    private final NumberSetting particleLife;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public static TotemTweaksModule get() {
        return instance;
    }

    public static boolean customParticles() {
        boolean bl;
        block11: {
            block9: {
                TotemTweaksModule totemTweaksModule;
                boolean bl2;
                block10: {
                    TotemTweaksModule totemTweaksModule2;
                    block8: {
                        totemTweaksModule2 = instance;
                        bl2 = AmbienceModule.b;
                        totemTweaksModule = totemTweaksModule2;
                        if (bl2) break block8;
                        if (totemTweaksModule == null) break block9;
                        totemTweaksModule = totemTweaksModule2;
                    }
                    if (bl2) break block10;
                    if (!totemTweaksModule.isEnabled()) break block9;
                    totemTweaksModule = totemTweaksModule2;
                }
                bl = totemTweaksModule.customParticles.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    
    public static int particleColor() {
        TotemTweaksModule totemTweaksModule = instance;
        boolean bl = AmbienceModule.b;
        TotemTweaksModule totemTweaksModule2 = totemTweaksModule;
        if (!bl) {
            if (totemTweaksModule2 == null) {
                return TotemTweaksModule.a(3031, 2982047701661587158L);
            }
            totemTweaksModule2 = totemTweaksModule;
        }
        int n = totemTweaksModule2.multiColor.getValue();
        if (!bl) {
            if (n == 0) {
                return totemTweaksModule.particleColor.getRGB();
            }
            n = ThreadLocalRandom.current().nextInt(3);
        }
        if (bl) return n;
        switch (n) {
            case 0: {
                n = totemTweaksModule.particleColor.getRGB();
                return n;
            }
            case 1: {
                n = totemTweaksModule.particleColor2.getRGB();
                return n;
            }
        }
        n = totemTweaksModule.particleColor3.getRGB();
        return n;
    }

    public static boolean hideOwnParticles() {
        boolean bl;
        block11: {
            block9: {
                TotemTweaksModule totemTweaksModule;
                boolean bl2;
                block10: {
                    TotemTweaksModule totemTweaksModule2;
                    block8: {
                        totemTweaksModule2 = instance;
                        bl2 = AmbienceModule.b;
                        totemTweaksModule = totemTweaksModule2;
                        if (bl2) break block8;
                        if (totemTweaksModule == null) break block9;
                        totemTweaksModule = totemTweaksModule2;
                    }
                    if (bl2) break block10;
                    if (!totemTweaksModule.isEnabled()) break block9;
                    totemTweaksModule = totemTweaksModule2;
                }
                bl = totemTweaksModule.hideOwnParticles.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static TotemTweaksModule$ParticleStyle particleStyle() {
        TotemTweaksModule$ParticleStyle totemTweaksModule$ParticleStyle;
        block6: {
            TotemTweaksModule totemTweaksModule;
            block4: {
                TotemTweaksModule totemTweaksModule2;
                block5: {
                    totemTweaksModule2 = instance;
                    boolean bl = AmbienceModule.b;
                    totemTweaksModule = totemTweaksModule2;
                    if (bl) break block4;
                    if (totemTweaksModule != null) break block5;
                    totemTweaksModule$ParticleStyle = TotemTweaksModule$ParticleStyle.Vanilla;
                    break block6;
                }
                totemTweaksModule = totemTweaksModule2;
            }
            totemTweaksModule$ParticleStyle = totemTweaksModule.particleStyle.getValue();
        }
        return totemTweaksModule$ParticleStyle;
    }

    public static float particleSpeed() {
        float f;
        block6: {
            TotemTweaksModule totemTweaksModule;
            block4: {
                TotemTweaksModule totemTweaksModule2;
                block5: {
                    totemTweaksModule2 = instance;
                    boolean bl = AmbienceModule.b;
                    totemTweaksModule = totemTweaksModule2;
                    if (bl) break block4;
                    if (totemTweaksModule != null) break block5;
                    f = 1.0f;
                    break block6;
                }
                totemTweaksModule = totemTweaksModule2;
            }
            f = (float)totemTweaksModule.particleSpeed.getValue();
        }
        return f;
    }

    public static float particleSize() {
        float f;
        block6: {
            TotemTweaksModule totemTweaksModule;
            block4: {
                TotemTweaksModule totemTweaksModule2;
                block5: {
                    totemTweaksModule2 = instance;
                    boolean bl = AmbienceModule.b;
                    totemTweaksModule = totemTweaksModule2;
                    if (bl) break block4;
                    if (totemTweaksModule != null) break block5;
                    f = 1.0f;
                    break block6;
                }
                totemTweaksModule = totemTweaksModule2;
            }
            f = (float)totemTweaksModule.particleSize.getValue();
        }
        return f;
    }

    public static float particleLife() {
        float f;
        block6: {
            TotemTweaksModule totemTweaksModule;
            block4: {
                TotemTweaksModule totemTweaksModule2;
                block5: {
                    totemTweaksModule2 = instance;
                    boolean bl = AmbienceModule.b;
                    totemTweaksModule = totemTweaksModule2;
                    if (bl) break block4;
                    if (totemTweaksModule != null) break block5;
                    f = 1.0f;
                    break block6;
                }
                totemTweaksModule = totemTweaksModule2;
            }
            f = (float)totemTweaksModule.particleLife.getValue();
        }
        return f;
    }

    public TotemTweaksModule() {
        String[] stringArray = b;
        super(stringArray[5], stringArray[18], Category.RENDER, false);
        boolean bl = AmbienceModule.b;
        this.handSize = new NumberSetting(stringArray[8], 0.05, 3.0, 1.0, 0.01);
        this.popSize = new NumberSetting(stringArray[11], 0.05, 4.0, 1.0, 0.01);
        this.centrePop = new BooleanSetting(stringArray[22], false);
        this.popTicks = new NumberSetting(stringArray[12], 10.0, 120.0, 40.0, 1.0);
        this.opacity = new NumberSetting(stringArray[4], 5.0, 100.0, 100.0, 1.0);
        this.popOpacity = new NumberSetting(stringArray[14], 5.0, 100.0, 100.0, 1.0);
        this.noEquipAnimation = new BooleanSetting(stringArray[16], false);
        this.customParticles = new BooleanSetting(stringArray[15], false);
        this.particleColor = new ColorSetting(stringArray[6], -7046, false);
        this.multiColor = new BooleanSetting(stringArray[19], false);
        this.particleColor2 = new ColorSetting(stringArray[2], TotemTweaksModule.a(23064, 1103420645249959707L), false);
        this.particleColor3 = new ColorSetting(stringArray[7], TotemTweaksModule.a(29996, 4076266016948807724L), false);
        this.hideOwnParticles = new BooleanSetting(stringArray[10], false);
        this.particleStyle = new EnumSetting<TotemTweaksModule$ParticleStyle>(stringArray[20], TotemTweaksModule$ParticleStyle.Vanilla);
        this.particleSpeed = new NumberSetting(stringArray[9], 0.1, 4.0, 1.0, 0.05);
        this.particleSize = new NumberSetting(stringArray[21], 0.2, 4.0, 1.0, 0.05);
        this.particleLife = new NumberSetting(stringArray[23], 0.2, 4.0, 1.0, 0.05);
        instance = this;
        this.addSettings(this.handSize, this.opacity, this.noEquipAnimation, this.popSize, this.popTicks, this.centrePop, this.popOpacity, this.hideOwnParticles, this.customParticles, this.particleColor, this.multiColor, this.particleColor2, this.particleColor3, this.particleStyle, this.particleSpeed, this.particleSize, this.particleLife);
        this.multiColor.children(this.particleColor2, this.particleColor3);
        this.handSize.tab(stringArray[24]);
        this.opacity.tab(stringArray[3]);
        this.noEquipAnimation.tab(stringArray[3]);
        this.popSize.tab(stringArray[13]);
        this.popTicks.tab(stringArray[17]);
        this.centrePop.tab(stringArray[17]);
        this.popOpacity.tab(stringArray[17]);
        this.hideOwnParticles.tab(stringArray[1]);
        this.customParticles.tab(stringArray[0]);
        for (Setting setting : new Setting[]{this.particleColor, this.multiColor, this.particleStyle, this.particleSpeed, this.particleSize, this.particleLife}) {
            setting.tab(b[0]);
            setting.visibleWhen(this.customParticles::getValue);
            if (!bl) continue;
        }
    }

    public float opacity() {
        return (float)(this.opacity.getValue() / 100.0);
    }

    public boolean fadesTotem() {
        int n;
        block8: {
            block7: {
                TotemTweaksModule totemTweaksModule;
                boolean bl;
                block6: {
                    bl = AmbienceModule.b;
                    totemTweaksModule = this;
                    if (bl) break block6;
                    if (!totemTweaksModule.isEnabled()) break block7;
                    totemTweaksModule = this;
                }
                float f = totemTweaksModule.opacity() - 0.999f;
                n = f == 0.0f ? 0 : (f < 0.0f ? -1 : 1);
                if (bl) break block8;
                if (n >= 0) break block7;
                n = 1;
                break block8;
            }
            n = 0;
        }
        return n != 0;
    }

    public boolean noEquipAnimation() {
        boolean bl;
        block8: {
            block7: {
                TotemTweaksModule totemTweaksModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    totemTweaksModule = this;
                    if (bl2) break block6;
                    if (!totemTweaksModule.isEnabled()) break block7;
                    totemTweaksModule = this;
                }
                bl = totemTweaksModule.noEquipAnimation.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public float handScale() {
        return (float)this.handSize.getValue();
    }

    public float popScale() {
        return (float)this.popSize.getValue();
    }

    public boolean centrePop() {
        return this.centrePop.getValue();
    }

    public int popTicks() {
        return this.popTicks.getValueInt();
    }

    public float popOpacity() {
        return (float)(this.popOpacity.getValue() / 100.0);
    }

    public boolean fadesPop() {
        int n;
        block8: {
            block7: {
                TotemTweaksModule totemTweaksModule;
                boolean bl;
                block6: {
                    bl = AmbienceModule.b;
                    totemTweaksModule = this;
                    if (bl) break block6;
                    if (!totemTweaksModule.isEnabled()) break block7;
                    totemTweaksModule = this;
                }
                float f = totemTweaksModule.popOpacity() - 0.999f;
                n = f == 0.0f ? 0 : (f < 0.0f ? -1 : 1);
                if (bl) break block8;
                if (n >= 0) break block7;
                n = 1;
                break block8;
            }
            n = 0;
        }
        return n != 0;
    }

    public boolean scalesHand() {
        int n;
        block8: {
            block7: {
                TotemTweaksModule totemTweaksModule;
                boolean bl;
                block6: {
                    bl = AmbienceModule.b;
                    totemTweaksModule = this;
                    if (bl) break block6;
                    if (!totemTweaksModule.isEnabled()) break block7;
                    totemTweaksModule = this;
                }
                float f = Math.abs(totemTweaksModule.handScale() - 1.0f) - 0.001f;
                n = f == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
                if (bl) break block8;
                if (n <= 0) break block7;
                n = 1;
                break block8;
            }
            n = 0;
        }
        return n != 0;
    }

    public boolean scalesPop() {
        int n;
        block8: {
            block7: {
                TotemTweaksModule totemTweaksModule;
                boolean bl;
                block6: {
                    bl = AmbienceModule.b;
                    totemTweaksModule = this;
                    if (bl) break block6;
                    if (!totemTweaksModule.isEnabled()) break block7;
                    totemTweaksModule = this;
                }
                float f = Math.abs(totemTweaksModule.popScale() - 1.0f) - 0.001f;
                n = f == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
                if (bl) break block8;
                if (n <= 0) break block7;
                n = 1;
                break block8;
            }
            n = 0;
        }
        return n != 0;
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[25];
                        var11_1 = 0;
                        var10_2 = "k\r vxxt^\u001f\tk\r vxxt^\u001f\fh\t1m\u007f\u007f8X\u0003>mc\u0005o\u0003&g|\ro\u0003&g|;wK\r1keb\fo\u0003&g|;LL\t3ib\u000ek\r vxxt^L1m}tj\u000bo\u0004;pu;{T\u0000=p\no\u0003&g|;kR\u00167\u000ek\r vxxt^L!rt~|\u0016s\u00056g1toUL\"ma;hZ\u001e&krw}H\bk\u0003\"\"brb^\fk\u0003\"\"unjZ\u0018;m\u007f\u0003k\u0003\"\u000bk\u0003\"\"~kyX\u0005&{\u0010x\u0019!v~v8K\r vxxt^\u001f\u0017\u007f\u0005!csw}\u001b\t#wxk8Z\u0002;opoqT\u0002\u0003k\u0003\"\u001ci\t!kk~8O\u00047\"etl^\u0001rc\u007f\u007f8R\u0018!\"ath\u000fv\u0019>vxkt^L1m}tjH\u000ek\r vxxt^L!vhw}\rk\r vxxt^L!kk~\u000ex\t<vc~8O\u00047\"ath";
                        var12_3 = "k\r vxxt^\u001f\tk\r vxxt^\u001f\fh\t1m\u007f\u007f8X\u0003>mc\u0005o\u0003&g|\ro\u0003&g|;wK\r1keb\fo\u0003&g|;LL\t3ib\u000ek\r vxxt^L1m}tj\u000bo\u0004;pu;{T\u0000=p\no\u0003&g|;kR\u00167\u000ek\r vxxt^L!rt~|\u0016s\u00056g1toUL\"ma;hZ\u001e&krw}H\bk\u0003\"\"brb^\fk\u0003\"\"unjZ\u0018;m\u007f\u0003k\u0003\"\u000bk\u0003\"\"~kyX\u0005&{\u0010x\u0019!v~v8K\r vxxt^\u001f\u0017\u007f\u0005!csw}\u001b\t#wxk8Z\u0002;opoqT\u0002\u0003k\u0003\"\u001ci\t!kk~8O\u00047\"etl^\u0001rc\u007f\u007f8R\u0018!\"ath\u000fv\u0019>vxkt^L1m}tjH\u000ek\r vxxt^L!vhw}\rk\r vxxt^L!kk~\u000ex\t<vc~8O\u00047\"ath".length();
                        var9_4 = 9;
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
                            var10_2 = "k\r vxxt^L>kw~\u0005o\u0003&g|";
                            var12_3 = "k\r vxxt^L>kw~\u0005o\u0003&g|".length();
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
                                v10 = 59;
                                break;
                            }
                            case 1: {
                                v10 = 108;
                                break;
                            }
                            case 2: {
                                v10 = 82;
                                break;
                            }
                            case 3: {
                                v10 = 2;
                                break;
                            }
                            case 4: {
                                v10 = 17;
                                break;
                            }
                            case 5: {
                                v10 = 27;
                                break;
                            }
                            default: {
                                v10 = 24;
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
                TotemTweaksModule.b = var13;
                var0_7 = 1822849362852073124L;
                var6_8 = new long[3];
                var3_9 = 0;
                var4_10 = "\u008b\u00ea0z\u00a7\u00e8\u00bc\b\u00b2\u0090\u0019\u00c9\u00fe\u001d\u00dc@\u00cd=:\u00d3\u009dV^@";
                var5_11 = "\u008b\u00ea0z\u00a7\u00e8\u00bc\b\u00b2\u0090\u0019\u00c9\u00fe\u001d\u00dc@\u00cd=:\u00d3\u009dV^@".length();
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
        TotemTweaksModule.d = var6_8;
        TotemTweaksModule.e = new Integer[3];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1D01;
        if (e[n2] == null) {
            TotemTweaksModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
