package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.popchams.PositionHandler;

public final class PopChamsModule
extends Module {
    private static PopChamsModule instance;
    private final BooleanSetting showOwnPops;
    private final BooleanSetting fadeOut;
    private final NumberSetting lifeTime;
    private final BooleanSetting disperse;
    private final NumberSetting disperseSpeed;
    private final NumberSetting disperseMaxDistance;
    private final BooleanSetting filledModel;
    private final ColorSetting filledColor;
    private final BooleanSetting wireframe;
    private final ColorSetting wireframeColor;
    private final NumberSetting wireframeThickness;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public static PopChamsModule get() {
        return instance;
    }

    public PopChamsModule() {
        String[] stringArray = b;
        super(stringArray[3], stringArray[8], Category.RENDER, false);
        this.showOwnPops = new BooleanSetting(stringArray[7], false);
        this.fadeOut = new BooleanSetting(stringArray[2], true);
        this.lifeTime = new NumberSetting(stringArray[9], 0.0, 250.0, 50.0, 1.0);
        this.disperse = new BooleanSetting(stringArray[6], false);
        this.disperseSpeed = new NumberSetting(stringArray[5], 0.0, 10.0, 1.5, 0.5);
        this.disperseMaxDistance = new NumberSetting(stringArray[10], 0.0, 8.0, 1.0, 0.1);
        this.filledModel = new BooleanSetting(stringArray[0], true);
        this.filledColor = new ColorSetting(stringArray[12], PopChamsModule.a(32007, 1644897973442369354L), true);
        this.wireframe = new BooleanSetting(stringArray[11], true);
        this.wireframeColor = new ColorSetting(stringArray[1], PopChamsModule.a(18784, 7803465646041621292L), true);
        this.wireframeThickness = new NumberSetting(stringArray[4], 0.0, 5.0, 2.0, 0.1);
        instance = this;
        this.addSettings(this.showOwnPops, this.fadeOut, this.lifeTime, this.disperse, this.disperseSpeed, this.disperseMaxDistance, this.filledModel, this.filledColor, this.wireframe, this.wireframeColor, this.wireframeThickness);
        this.fadeOut.children(this.lifeTime);
        this.disperse.children(this.disperseSpeed, this.disperseMaxDistance);
        this.filledModel.children(this.filledColor);
        this.wireframe.children(this.wireframeColor, this.wireframeThickness);
    }

    @Override
    public void onDisable() {
        PositionHandler.clear();
    }

    public boolean showOwnPops() {
        return this.showOwnPops.getValue();
    }

    public boolean fadeOut() {
        return this.fadeOut.getValue();
    }

    public int lifeTime() {
        return this.lifeTime.getValueInt();
    }

    public boolean disperse() {
        return this.disperse.getValue();
    }

    public double disperseSpeed() {
        return this.disperseSpeed.getValue();
    }

    public double disperseMaxDistance() {
        return this.disperseMaxDistance.getValue();
    }

    public boolean filledModel() {
        return this.filledModel.getValue();
    }

    public int filledColor() {
        return this.filledColor.getARGB();
    }

    public boolean wireframe() {
        return this.wireframe.getValue();
    }

    public int wireframeColor() {
        return this.wireframeColor.getARGB();
    }

    public double wireframeThickness() {
        return this.wireframeThickness.getValue();
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[13];
                        var11_1 = 0;
                        var10_2 = "8o'`\u0007TT3i/i\u000e\u00101\u0010g)`\u0007T\u000f)o9i\u0004B\u0015\u0013ckO\r\\\u001b\f\u000e8g/iB\u007f\u0002\u001btkX\u000b]\u0011\t.i;,!X\u0015\u0013u\u0013)o9i\u0004B\u0015\u0013ckX\nY\u0017\u0015h.\u007f\u0011\u000e:o8|\u0007B\u0007\u001b&\u0018|\u0007U\u0010\u0010:o8|\u0007B\u0007\u001b&\u000eb\u0003R\u0018\u001bb\r-n${B\u007f\u0003\u0010&\u001bc\u0012C%?&,d\rC\u0000^q#i\u0010UT\u001f&;`\u0003I\u0011\f&;c\u0012@\u0011\u001a&*,\u0016_\u0000\u001bk\u00108g/e\fWT2o-iBd\u001d\u0013c\u0015:o8|\u0007B\u0007\u001b&\u0006m\u001a\u00100\u0017u?m\fS\u0011";
                        var12_3 = "8o'`\u0007TT3i/i\u000e\u00101\u0010g)`\u0007T\u000f)o9i\u0004B\u0015\u0013ckO\r\\\u001b\f\u000e8g/iB\u007f\u0002\u001btkX\u000b]\u0011\t.i;,!X\u0015\u0013u\u0013)o9i\u0004B\u0015\u0013ckX\nY\u0017\u0015h.\u007f\u0011\u000e:o8|\u0007B\u0007\u001b&\u0018|\u0007U\u0010\u0010:o8|\u0007B\u0007\u001b&\u000eb\u0003R\u0018\u001bb\r-n${B\u007f\u0003\u0010&\u001bc\u0012C%?&,d\rC\u0000^q#i\u0010UT\u001f&;`\u0003I\u0011\f&;c\u0012@\u0011\u001a&*,\u0016_\u0000\u001bk\u00108g/e\fWT2o-iBd\u001d\u0013c\u0015:o8|\u0007B\u0007\u001b&\u0006m\u001a\u00100\u0017u?m\fS\u0011".length();
                        var9_4 = 20;
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
                            var10_2 = ")o9i\u0004B\u0015\u0013ckI\fQ\u0016\u0012c/\u00128o'`\u0007TT3i/i\u000e\u00107\u0011j$~";
                            var12_3 = ")o9i\u0004B\u0015\u0013ckI\fQ\u0016\u0012c/\u00128o'`\u0007TT3i/i\u000e\u00107\u0011j$~".length();
                            var9_4 = 17;
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
                                v10 = 126;
                                break;
                            }
                            case 1: {
                                v10 = 6;
                                break;
                            }
                            case 2: {
                                v10 = 75;
                                break;
                            }
                            case 3: {
                                v10 = 12;
                                break;
                            }
                            case 4: {
                                v10 = 98;
                                break;
                            }
                            case 5: {
                                v10 = 48;
                                break;
                            }
                            default: {
                                v10 = 116;
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
                PopChamsModule.b = var13;
                var0_7 = 8240567530142070815L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "n\u0002\u0080\u00f2m\u00ff\u00af\u00aa\u00a1\u0081\u00b2\u00fa\u0092\u009e\u001b\u00cc";
                var5_11 = "n\u0002\u0080\u00f2m\u00ff\u00af\u00aa\u00a1\u0081\u00b2\u00fa\u0092\u009e\u001b\u00cc".length();
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
        PopChamsModule.d = var6_8;
        PopChamsModule.e = new Integer[2];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2A4D;
        if (e[n2] == null) {
            PopChamsModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
