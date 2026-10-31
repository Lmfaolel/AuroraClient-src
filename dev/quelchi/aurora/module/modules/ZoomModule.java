package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.KeybindSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.util.KeyUtil;
import dev.quelchi.aurora.zoom.Interpolator$SmoothInterpolator;
import dev.quelchi.aurora.zoom.Interpolator$TransitionInterpolator;
import dev.quelchi.aurora.zoom.TransitionType;
import dev.quelchi.aurora.zoom.ZoomHelper;
import net.minecraft.util.math.MathHelper;

public final class ZoomModule
extends Module {
    private static ZoomModule instance;
    private final KeybindSetting zoomKey;
    private final BooleanSetting toggle;
    private final NumberSetting initialZoom;
    private final NumberSetting zoomInTime;
    private final NumberSetting zoomOutTime;
    private final EnumSetting<TransitionType> transitionIn;
    private final EnumSetting<TransitionType> transitionOut;
    private final BooleanSetting scrollZoom;
    private final NumberSetting scrollSteps;
    private final NumberSetting zoomPerStep;
    private final NumberSetting scrollSmoothness;
    private final BooleanSetting retainSteps;
    private final NumberSetting relativeSensitivity;
    private final BooleanSetting relativeViewBobbing;
    private final NumberSetting cinematicCamera;
    private final BooleanSetting affectHandFov;
    private final ZoomHelper helper;
    private boolean zooming;
    private boolean keyWasDown;
    private int steps;
    private double lastDivisor;
    private static final String[] b;

    public static ZoomModule get() {
        return instance;
    }

    public ZoomModule() {
        String[] stringArray = b;
        super(stringArray[1], stringArray[15], Category.PLAYER, false);
        this.zoomKey = new KeybindSetting(stringArray[7], 67);
        this.toggle = new BooleanSetting(stringArray[4], false);
        this.initialZoom = new NumberSetting(stringArray[10], 1.0, 16.0, 4.0, 1.0);
        this.zoomInTime = new NumberSetting(stringArray[5], 0.1, 3.0, 1.0, 0.1);
        this.zoomOutTime = new NumberSetting(stringArray[14], 0.1, 3.0, 0.5, 0.1);
        this.transitionIn = new EnumSetting<TransitionType>(stringArray[12], TransitionType.EaseOutExp);
        this.transitionOut = new EnumSetting<TransitionType>(stringArray[6], TransitionType.EaseOutExp);
        this.scrollZoom = new BooleanSetting(stringArray[8], true);
        this.scrollSteps = new NumberSetting(stringArray[17], 1.0, 30.0, 10.0, 1.0);
        this.zoomPerStep = new NumberSetting(stringArray[11], 110.0, 300.0, 150.0, 10.0);
        this.scrollSmoothness = new NumberSetting(stringArray[13], 0.0, 100.0, 70.0, 5.0);
        this.retainSteps = new BooleanSetting(stringArray[0], false);
        this.relativeSensitivity = new NumberSetting(stringArray[9], 0.0, 100.0, 100.0, 5.0);
        this.relativeViewBobbing = new BooleanSetting(stringArray[2], true);
        this.cinematicCamera = new NumberSetting(stringArray[16], 0.0, 250.0, 0.0, 10.0);
        this.affectHandFov = new BooleanSetting(stringArray[3], true);
        this.helper = new ZoomHelper(new Interpolator$TransitionInterpolator(this.transitionIn::getValue, this.transitionOut::getValue, this.zoomInTime::getValue, this.zoomOutTime::getValue), new Interpolator$SmoothInterpolator(this::lambda$new$0), this.initialZoom::getValueInt, this.zoomPerStep::getValueInt, this.scrollSteps::getValueInt);
        this.lastDivisor = 1.0;
        instance = this;
        this.addSettings(this.zoomKey, this.toggle, this.initialZoom, this.zoomInTime, this.zoomOutTime, this.transitionIn, this.transitionOut, this.scrollZoom, this.scrollSteps, this.zoomPerStep, this.scrollSmoothness, this.retainSteps, this.relativeSensitivity, this.relativeViewBobbing, this.cinematicCamera, this.affectHandFov);
        this.scrollZoom.children(this.scrollSteps, this.zoomPerStep, this.scrollSmoothness, this.retainSteps);
    }

    
    @Override
    public void onTick() {
        block31: {
            block30: {
                block28: {
                    block32: {
                        block33: {
                            block29: {
                                block27: {
                                    block26: {
                                        var2_1 = this.zoomKey.getKey();
                                        var1_2 = AmbienceModule.b;
                                        v0 = var2_1;
                                        if (var1_2) break block26;
                                        if (v0 != -1) {
                                        }
                                        v2 = var2_1;
                                        if (var1_2) break block27;
                                        v0 = (int)KeyUtil.isKeyPressedInGame(v2);
                                    }
                                    if (v0 != 0) {
                                        v2 = 1;
                                    } else lbl_neg_1000:

                                    {
                                        v2 = 0;
                                    }
                                }
                                var3_3 = v2;
                                v5 = this;
                                if (var1_2) break block28;
                                if (v5.toggle.getValue()) {
                                }
                                v7 = var3_3;
                                if (var1_2) break block29;
                                if (v7 == 0) break block30;
                                v10 = this;
                                if (var1_2) break block31;
                                v7 = (int)v10.keyWasDown;
                            }
                            if (v7 != 0) break block30;
                            v13 = this;
                            v14 = this.zooming;
                            if (var1_2) break block32;
                            if (v14) break block33;
                            v14 = true;
                            break block32;
                        }
                        v14 = false;
                    }
                    v13.zooming = v14;
                    if (!var1_2) break block30;
lbl81:

                    v5 = this;
                }
                v5.zooming = var3_3;
            }
            this.keyWasDown = var3_3;
            v10 = this;
        }
        v10.helper.tick(this.zooming, this.steps);
    }

    @Override
    public void onDisable() {
        this.zooming = false;
        this.keyWasDown = false;
        this.steps = 0;
        this.lastDivisor = 1.0;
        this.helper.setToZero();
    }

    public boolean zooming() {
        boolean bl;
        block8: {
            block7: {
                ZoomModule zoomModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    zoomModule = this;
                    if (bl2) break block6;
                    if (!zoomModule.isEnabled()) break block7;
                    zoomModule = this;
                }
                bl = zoomModule.zooming;
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public float divisor(float f) {
        double d;
        ZoomModule zoomModule;
        block14: {
            block15: {
                ZoomModule zoomModule2;
                block16: {
                    block17: {
                        boolean bl;
                        block12: {
                            block13: {
                                bl = AmbienceModule.b;
                                zoomModule = this;
                                if (bl) break block12;
                                if (zoomModule.isEnabled()) break block13;
                                return 1.0f;
                            }
                            zoomModule = this;
                        }
                        if (bl) break block14;
                        if (zoomModule.zooming) break block15;
                        zoomModule2 = this;
                        if (bl) break block16;
                        if (zoomModule2.retainSteps.getValue()) break block17;
                        this.steps = 0;
                    }
                    zoomModule2 = this;
                }
                zoomModule2.helper.reset();
            }
            zoomModule = this;
        }
        this.lastDivisor = d = zoomModule.helper.getZoomDivisor(f);
        return (float)d;
    }

    public double lastDivisor() {
        double d;
        block6: {
            block5: {
                ZoomModule zoomModule;
                block4: {
                    boolean bl = AmbienceModule.b;
                    zoomModule = this;
                    if (bl) break block4;
                    if (!zoomModule.isEnabled()) break block5;
                    zoomModule = this;
                }
                d = zoomModule.lastDivisor;
                break block6;
            }
            d = 1.0;
        }
        return d;
    }

    public boolean scrollCaptures() {
        boolean bl;
        block8: {
            block7: {
                ZoomModule zoomModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    zoomModule = this;
                    if (bl2) break block6;
                    if (!zoomModule.zooming()) break block7;
                    zoomModule = this;
                }
                bl = zoomModule.scrollZoom.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public void mouseZoom(double d) {
        block11: {
            double d2;
            block9: {
                block10: {
                    boolean bl = AmbienceModule.b;
                    d2 = d;
                    if (bl) break block9;
                    if (!(d2 > 0.0)) break block10;
                    ++this.steps;
                    if (!bl) break block11;
                }
                d2 = d;
            }
            if (d2 < 0.0) {
                --this.steps;
            }
        }
        this.steps = MathHelper.method_15340((int)this.steps, (int)0, (int)this.scrollSteps.getValueInt());
    }

    public double sensitivityDivisor() {
        return MathHelper.method_16436((double)(this.relativeSensitivity.getValue() / 100.0), (double)1.0, (double)this.lastDivisor());
    }

    public boolean cinematicActive() {
        int n;
        block8: {
            block7: {
                ZoomModule zoomModule;
                boolean bl;
                block6: {
                    bl = AmbienceModule.b;
                    zoomModule = this;
                    if (bl) break block6;
                    if (!zoomModule.zooming()) break block7;
                    zoomModule = this;
                }
                double d = zoomModule.cinematicCamera.getValue() - 0.0;
                n = d == 0.0 ? 0 : (d > 0.0 ? 1 : -1);
                if (bl) break block8;
                if (n <= 0) break block7;
                n = 1;
                break block8;
            }
            n = 0;
        }
        return n != 0;
    }

    public double cinematicFactor() {
        return this.cinematicCamera.getValue() / 100.0;
    }

    public boolean relativeViewBobbing() {
        return this.relativeViewBobbing.getValue();
    }

    public boolean affectsHandFov() {
        return this.affectHandFov.getValue();
    }

    private double lambda$new$0() {
        return MathHelper.method_16436((double)(this.scrollSmoothness.getValue() / 100.0), (double)1.0, (double)0.1);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[18];
                var3_1 = 0;
                var2_2 = "m\u001f\n0\u0001&\u0002L\u000e\u001b!\u001b\u0004e\u0015\u0011<\u0015m\u001f\u00120\u001c!TZZ\b8\r?\u0002]\u0015\u001c3\u0001&E\u000f~\u001c\u00184\u000b<\u0002W\u001b\u00105H\u000emi\u0006k\u0015\u00196\u0004-\fe\u0015\u0011<H!L\u001f\u000e\u0017<\r\u000ek\b\u001f?\u001b!VV\u0015\u0010q\u0007=V\be\u0015\u0011<H\u0003GF\u000bl\u0019\f>\u0004$\u0002E\u0015\u0011<\u0014m\u001f\u00120\u001c!TZZ\r4\u0006;KK\u0013\b8\u001c1\fv\u0014\u0017%\u0001)N\u001f\u0000\u0011>\u0005\re\u0015\u0011<H8GMZ\r%\r8\rk\b\u001f?\u001b!VV\u0015\u0010q\u0001&\nl\u0017\u0011>\u001c LZ\t\r\re\u0015\u0011<H'WKZ\n8\u0005-\u0010w\u0015\u00125H#GFZ\n>H2MP\u0017";
                var4_3 = "m\u001f\n0\u0001&\u0002L\u000e\u001b!\u001b\u0004e\u0015\u0011<\u0015m\u001f\u00120\u001c!TZZ\b8\r?\u0002]\u0015\u001c3\u0001&E\u000f~\u001c\u00184\u000b<\u0002W\u001b\u00105H\u000emi\u0006k\u0015\u00196\u0004-\fe\u0015\u0011<H!L\u001f\u000e\u0017<\r\u000ek\b\u001f?\u001b!VV\u0015\u0010q\u0007=V\be\u0015\u0011<H\u0003GF\u000bl\u0019\f>\u0004$\u0002E\u0015\u0011<\u0014m\u001f\u00120\u001c!TZZ\r4\u0006;KK\u0013\b8\u001c1\fv\u0014\u0017%\u0001)N\u001f\u0000\u0011>\u0005\re\u0015\u0011<H8GMZ\r%\r8\rk\b\u001f?\u001b!VV\u0015\u0010q\u0001&\nl\u0017\u0011>\u001c LZ\t\r\re\u0015\u0011<H'WKZ\n8\u0005-\u0010w\u0015\u00125H#GFZ\n>H2MP\u0017".length();
                var1_4 = 12;
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
                    var2_2 = "|\u0013\u00104\u0005)VV\u0019^2\t%GM\u001b\u0005l\u000e\u001b!\u001b";
                    var4_3 = "|\u0013\u00104\u0005)VV\u0019^2\t%GM\u001b\u0005l\u000e\u001b!\u001b".length();
                    var1_4 = 16;
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
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 63;
                        break;
                    }
                    case 1: {
                        v10 = 122;
                        break;
                    }
                    case 2: {
                        v10 = 126;
                        break;
                    }
                    case 3: {
                        v10 = 81;
                        break;
                    }
                    case 4: {
                        v10 = 104;
                        break;
                    }
                    case 5: {
                        v10 = 72;
                        break;
                    }
                    default: {
                        v10 = 34;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_6;
lbl65:

                v7 = v7;
            } while (v7 > var6_6);
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
        ZoomModule.b = var5;
    }
}
