package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.HandTweaksModule$HandOffset;
import dev.quelchi.aurora.module.modules.HandTweaksModule$SpinOn;
import dev.quelchi.aurora.module.modules.HandTweaksModule$Style;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import java.lang.invoke.LambdaMetafactory;
import java.util.function.Supplier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionfc;

public final class HandTweaksModule
extends Module {
    private static HandTweaksModule instance;
    private final HandTweaksModule$HandOffset mainHand;
    private final HandTweaksModule$HandOffset offHand;
    private final NumberSetting customX;
    private final NumberSetting customY;
    private final NumberSetting customZ;
    private final NumberSetting customPitch;
    private final NumberSetting customYaw;
    private final NumberSetting customRoll;
    private final NumberSetting swingPitch;
    private final NumberSetting swingYaw;
    private final NumberSetting swingRoll;
    private final NumberSetting speed;
    private final BooleanSetting restart;
    private final EnumSetting<HandTweaksModule$Style> style;
    private final BooleanSetting spin;
    private final EnumSetting<HandTweaksModule$SpinOn> spinOn;
    private final NumberSetting spinTicks;
    private final int[] spinAge;
    private boolean wasUsing;
    private static final String[] b;

    
    public HandTweaksModule() {
        block9: {
            var6_1 = HandTweaksModule.b;
            super(var6_1[7], var6_1[5], Category.RENDER, false);
            this.mainHand = new HandTweaksModule$HandOffset(var6_1[23]);
            this.offHand = new HandTweaksModule$HandOffset(var6_1[0]);
            this.customX = new NumberSetting(var6_1[15], -2.0, 2.0, 0.56, 0.01);
            this.customY = new NumberSetting(var6_1[12], -2.0, 2.0, -0.32, 0.01);
            this.customZ = new NumberSetting(var6_1[14], -2.0, 2.0, -0.72, 0.01);
            this.customPitch = new NumberSetting(var6_1[20], -180.0, 180.0, 0.0, 1.0);
            this.customYaw = new NumberSetting(var6_1[13], -180.0, 180.0, 0.0, 1.0);
            this.customRoll = new NumberSetting(var6_1[21], -180.0, 180.0, 0.0, 1.0);
            this.swingPitch = new NumberSetting(var6_1[22], -180.0, 180.0, -60.0, 1.0);
            this.swingYaw = new NumberSetting(var6_1[6], -180.0, 180.0, 0.0, 1.0);
            this.swingRoll = new NumberSetting(var6_1[1], -180.0, 180.0, 0.0, 1.0);
            this.speed = new NumberSetting(var6_1[4], 0.5, 4.0, 1.0, 0.1);
            this.restart = new BooleanSetting(var6_1[9], false);
            this.style = new EnumSetting<HandTweaksModule$Style>(var6_1[8], HandTweaksModule$Style.Normal);
            this.spin = new BooleanSetting(var6_1[17], false);
            this.spinOn = new EnumSetting<HandTweaksModule$SpinOn>(var6_1[10], HandTweaksModule$SpinOn.Both);
            this.spinTicks = new NumberSetting(var6_1[16], 3.0, 20.0, 8.0, 1.0);
            this.spinAge = new int[]{-1, -1};
            HandTweaksModule.instance = this;
            this.addSettings(new Setting[]{this.style, this.speed, this.restart, this.spin, this.spinOn, this.spinTicks});
            this.spin.children(new Setting[]{this.spinOn, this.spinTicks});
            this.addSettings(new Setting[]{this.customX, this.customY, this.customZ, this.customPitch, this.customYaw, this.customRoll, this.swingPitch, this.swingYaw, this.swingRoll});
            this.addSettings(this.mainHand.settings());
            this.addSettings(this.offHand.settings());
            this.style.tab(var6_1[19]);
            this.speed.tab(var6_1[11]);
            this.restart.tab(var6_1[11]);
            var1_2 = AmbienceModule.b;
            this.spin.tab(var6_1[11]);
            var2_3 = new Setting[]{this.customX, this.customY, this.customZ, this.customPitch, this.customYaw, this.customRoll, this.swingPitch, this.swingYaw, this.swingRoll};
            var3_4 = var2_3.length;
            for (var4_5 = 0; var4_5 < var3_4; ++var4_5) {
                var5_6 = var2_3[var4_5];
                if (!var1_2) {
                    var5_6.tab(HandTweaksModule.b[3]);
                    var5_6.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$0(), ()Ljava/lang/Boolean;)((HandTweaksModule)this));
                    if (!var1_2) continue;
                    break;
                }
                break block9;
            }
            var2_3 = this.mainHand.settings();
            var3_4 = var2_3.length;
        }
        for (var4_5 = 0; var4_5 < var3_4; ++var4_5) {
            var5_6 = var2_3[var4_5];
            v1 = var5_6;
            v2 = HandTweaksModule.b[18];
            if (!var1_2) {
                v1.tab(v2);
                if (!var1_2) continue;
                break;
            }
        }
        var2_3 = this.offHand.settings();
        var3_4 = var2_3.length;
        for (var4_5 = 0; var4_5 < var3_4; ++var4_5) {
            v1 = var5_6 = var2_3[var4_5];
            v2 = HandTweaksModule.b[2];
lbl73:

            v1.tab(v2);
            if (!var1_2) continue;
        }
    }

    public static double[] customSwing() {
        HandTweaksModule handTweaksModule = instance;
        if (handTweaksModule == null) {
            return null;
        }
        return new double[]{handTweaksModule.customX.getValue(), handTweaksModule.customY.getValue(), handTweaksModule.customZ.getValue(), handTweaksModule.customPitch.getValue(), handTweaksModule.customYaw.getValue(), handTweaksModule.customRoll.getValue(), handTweaksModule.swingPitch.getValue(), handTweaksModule.swingYaw.getValue(), handTweaksModule.swingRoll.getValue()};
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static HandTweaksModule$HandOffset offsetFor(boolean bl) {
        boolean bl2;
        HandTweaksModule handTweaksModule;
        block9: {
            block10: {
                HandTweaksModule handTweaksModule2;
                boolean bl3;
                block8: {
                    handTweaksModule = instance;
                    bl3 = AmbienceModule.b;
                    handTweaksModule2 = handTweaksModule;
                    if (bl3) break block8;
                    if (handTweaksModule2 == null) return null;
                    handTweaksModule2 = handTweaksModule;
                }
                bl2 = handTweaksModule2.isEnabled();
                if (bl3) break block9;
                if (bl2) break block10;
                return null;
            }
            bl2 = bl;
        }
        HandTweaksModule$HandOffset handTweaksModule$HandOffset = bl2 ? handTweaksModule.mainHand : handTweaksModule.offHand;
        return handTweaksModule$HandOffset;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void applyPlacement(MatrixStack matrixStack, boolean bl) {
        NumberSetting numberSetting;
        HandTweaksModule$HandOffset handTweaksModule$HandOffset;
        block21: {
            block22: {
                HandTweaksModule$HandOffset handTweaksModule$HandOffset2;
                boolean bl2;
                block19: {
                    block20: {
                        block17: {
                            block18: {
                                block16: {
                                    handTweaksModule$HandOffset = HandTweaksModule.offsetFor(bl);
                                    bl2 = AmbienceModule.b;
                                    handTweaksModule$HandOffset2 = handTweaksModule$HandOffset;
                                    if (bl2) break block16;
                                    if (handTweaksModule$HandOffset2 == null) return;
                                    handTweaksModule$HandOffset2 = handTweaksModule$HandOffset;
                                }
                                if (bl2) break block17;
                                if (!handTweaksModule$HandOffset2.isDefault()) break block18;
                                return;
                            }
                            matrixStack.method_46416((float)handTweaksModule$HandOffset.x.getValue(), (float)handTweaksModule$HandOffset.y.getValue(), (float)handTweaksModule$HandOffset.z.getValue());
                            handTweaksModule$HandOffset2 = handTweaksModule$HandOffset;
                        }
                        if (bl2) break block19;
                        if (handTweaksModule$HandOffset2.pitch.getValue() == 0.0) break block20;
                        matrixStack.method_22907((Quaternionfc)RotationAxis.field_40714.rotationDegrees((float)handTweaksModule$HandOffset.pitch.getValue()));
                    }
                    handTweaksModule$HandOffset2 = handTweaksModule$HandOffset;
                }
                numberSetting = handTweaksModule$HandOffset2.yaw;
                if (bl2) break block21;
                if (numberSetting.getValue() == 0.0) break block22;
                matrixStack.method_22907((Quaternionfc)RotationAxis.field_40716.rotationDegrees((float)handTweaksModule$HandOffset.yaw.getValue()));
            }
            numberSetting = handTweaksModule$HandOffset.roll;
        }
        if (numberSetting.getValue() == 0.0) return;
        matrixStack.method_22907((Quaternionfc)RotationAxis.field_40718.rotationDegrees((float)handTweaksModule$HandOffset.roll.getValue()));
        return;
    }

    public static float scaleFor(boolean bl) {
        float f;
        block6: {
            HandTweaksModule$HandOffset handTweaksModule$HandOffset;
            block4: {
                HandTweaksModule$HandOffset handTweaksModule$HandOffset2;
                block5: {
                    handTweaksModule$HandOffset2 = HandTweaksModule.offsetFor(bl);
                    boolean bl2 = AmbienceModule.b;
                    handTweaksModule$HandOffset = handTweaksModule$HandOffset2;
                    if (bl2) break block4;
                    if (handTweaksModule$HandOffset != null) break block5;
                    f = 1.0f;
                    break block6;
                }
                handTweaksModule$HandOffset = handTweaksModule$HandOffset2;
            }
            f = handTweaksModule$HandOffset.scale();
        }
        return f;
    }

    
    public static HandTweaksModule$Style style() {
        HandTweaksModule$Style handTweaksModule$Style;
        HandTweaksModule handTweaksModule;
        block7: {
            block8: {
                block6: {
                    boolean bl;
                    block5: {
                        bl = AmbienceModule.b;
                        handTweaksModule = instance;
                        if (bl) break block5;
                        if (handTweaksModule == null) break block6;
                        handTweaksModule = instance;
                    }
                    if (bl) break block7;
                    if (handTweaksModule.isEnabled()) break block8;
                }
                handTweaksModule$Style = HandTweaksModule$Style.Normal;
                return handTweaksModule$Style;
            }
            handTweaksModule = instance;
        }
        handTweaksModule$Style = handTweaksModule.style.getValue();
        return handTweaksModule$Style;
    }

    public static HandTweaksModule get() {
        return instance;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int duration(int n) {
        int n2;
        block7: {
            HandTweaksModule handTweaksModule;
            block8: {
                HandTweaksModule handTweaksModule2;
                boolean bl;
                block6: {
                    handTweaksModule = instance;
                    bl = AmbienceModule.b;
                    handTweaksModule2 = handTweaksModule;
                    if (bl) break block6;
                    if (handTweaksModule2 == null) return n;
                    handTweaksModule2 = handTweaksModule;
                }
                n2 = handTweaksModule2.isEnabled();
                if (bl) break block7;
                if (n2 != 0) break block8;
                return n;
            }
            n2 = (int)Math.round((double)n / handTweaksModule.speed.getValue());
        }
        int n3 = n2;
        return Math.max(1, n3);
    }

    public static boolean restartsImmediately() {
        boolean bl;
        block11: {
            block9: {
                HandTweaksModule handTweaksModule;
                boolean bl2;
                block10: {
                    HandTweaksModule handTweaksModule2;
                    block8: {
                        handTweaksModule2 = instance;
                        bl2 = AmbienceModule.b;
                        handTweaksModule = handTweaksModule2;
                        if (bl2) break block8;
                        if (handTweaksModule == null) break block9;
                        handTweaksModule = handTweaksModule2;
                    }
                    if (bl2) break block10;
                    if (!handTweaksModule.isEnabled()) break block9;
                    handTweaksModule = handTweaksModule2;
                }
                bl = handTweaksModule.restart.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    @Override
    public void onTick() {
        boolean bl;
        block31: {
            boolean bl2;
            block33: {
                HandTweaksModule handTweaksModule;
                block32: {
                    HandTweaksModule handTweaksModule2;
                    block29: {
                        block30: {
                            bl = AmbienceModule.b;
                            handTweaksModule2 = this;
                            if (bl) break block29;
                            if (handTweaksModule2.mc.field_1724 != null) break block30;
                            return;
                        }
                        handTweaksModule2 = this;
                    }
                    bl2 = handTweaksModule2.mc.field_1724.method_6115();
                    if (bl) break block31;
                    if (!bl2) break block32;
                    handTweaksModule = this;
                    if (bl) break block33;
                    if (handTweaksModule.wasUsing) break block32;
                    handTweaksModule = this;
                    if (bl) break block33;
                    if (!handTweaksModule.spin.getValue()) break block32;
                    handTweaksModule = this;
                    if (bl) break block33;
                    if (handTweaksModule.spinOn.getValue() == HandTweaksModule$SpinOn.Attack) break block32;
                    this.startSpin(this.mc.field_1724.method_6058());
                }
                handTweaksModule = this;
            }
            handTweaksModule.wasUsing = bl2;
        }
        for (int i = 0; i < 2; ++i) {
            block35: {
                HandTweaksModule handTweaksModule;
                int[] nArray;
                block34: {
                    nArray = this.spinAge;
                    if (bl) break block34;
                    if (nArray[i] < 0) continue;
                    handTweaksModule = this;
                    if (bl) break block35;
                    nArray = handTweaksModule.spinAge;
                }
                int n = i;
                nArray[n] = nArray[n] + 1;
                if (nArray[n] <= this.spinTicks.getValueInt()) continue;
                handTweaksModule = this;
            }
            handTweaksModule.spinAge[i] = -1;
            if (!bl) continue;
        }
    }

    private void startSpin(Hand hand) {
        block5: {
            block4: {
                boolean bl = AmbienceModule.b;
                int[] nArray = this.spinAge;
                if (bl) break block4;
                if (nArray[hand.ordinal()] >= 0) break block5;
                nArray = this.spinAge;
            }
            nArray[hand.ordinal()] = 0;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onSwing(Hand hand) {
        HandTweaksModule handTweaksModule;
        block17: {
            HandTweaksModule handTweaksModule2;
            block18: {
                boolean bl;
                block16: {
                    block15: {
                        block14: {
                            handTweaksModule2 = instance;
                            bl = AmbienceModule.b;
                            handTweaksModule = handTweaksModule2;
                            if (bl) break block14;
                            if (handTweaksModule == null) return;
                            handTweaksModule = handTweaksModule2;
                        }
                        if (bl) break block15;
                        if (!handTweaksModule.isEnabled()) return;
                        handTweaksModule = handTweaksModule2;
                    }
                    if (bl) break block16;
                    if (!handTweaksModule.spin.getValue()) return;
                    handTweaksModule = handTweaksModule2;
                }
                if (bl) break block17;
                if (handTweaksModule.spinOn.getValue() != HandTweaksModule$SpinOn.Use) break block18;
                return;
            }
            handTweaksModule = handTweaksModule2;
        }
        handTweaksModule.startSpin(hand);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static float spinAngle(Hand hand, float f) {
        HandTweaksModule handTweaksModule;
        HandTweaksModule handTweaksModule2;
        block15: {
            block16: {
                boolean bl;
                block14: {
                    block13: {
                        handTweaksModule2 = instance;
                        bl = AmbienceModule.b;
                        handTweaksModule = handTweaksModule2;
                        if (bl) break block13;
                        if (handTweaksModule == null) return 0.0f;
                        handTweaksModule = handTweaksModule2;
                    }
                    if (bl) break block14;
                    if (!handTweaksModule.isEnabled()) return 0.0f;
                    handTweaksModule = handTweaksModule2;
                }
                if (bl) break block15;
                if (handTweaksModule.spin.getValue()) break block16;
                return 0.0f;
            }
            handTweaksModule = handTweaksModule2;
        }
        int n = handTweaksModule.spinAge[hand.ordinal()];
        if (n < 1) {
            return 0.0f;
        }
        float f2 = Math.min(1.0f, ((float)(n - 1) + f) / (float)handTweaksModule2.spinTicks.getValueInt());
        float f3 = 1.0f - f2;
        return 360.0f * (1.0f - f3 * f3 * f3);
    }

    private Boolean lambda$new$0() {
        boolean bl = this.style.getValue() == HandTweaksModule$Style.Custom;
        return bl;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[24];
                var3_1 = 0;
                var2_2 = "\u0001[c\n\u001dJl\u001d`B\u0012!Qi\b\u0001[cSo\u0003.*\f\rHv\u0007h\u000f`=Jl\u001d`\u0005\u001dM`\u0016c3\u001eRv\u001as\u000b/ \u0011%\u0000d\u0003,+\u001dd\u001dcB2+Nq\nk\u0007`7Rp\u0001'\u0004)<NqSw\u00072=RkSo\u0003.*N\t\u001dJl\u001d`B\u0019/J\u000b\u0006\\k\u0017'67+\\n\u0000\u0005\u001dI|\u001fb\u0011\u001cXv\u0007f\u00104nTk\u0000s\u0003.:Q|\u0007\u001dMl\u001d'-.\u0005\u001dJl\u001d`\b\rHv\u0007h\u000f`\u0017\n\rHv\u0007h\u000f`\u0017\\r\b\rHv\u0007h\u000f`\u0014\b\rHv\u0007h\u000f`\u0016\n\u001dMl\u001d'6)-Vv\u0004\u001dMl\u001d\t\u0003\\l\u001d'\n! Y\u0005\u001dJl\u001d`\f\rHv\u0007h\u000f`\u001eTq\u0010o\u000b\rHv\u0007h\u000f`\u001cRi\u001f";
                var4_3 = "\u0001[c\n\u001dJl\u001d`B\u0012!Qi\b\u0001[cSo\u0003.*\f\rHv\u0007h\u000f`=Jl\u001d`\u0005\u001dM`\u0016c3\u001eRv\u001as\u000b/ \u0011%\u0000d\u0003,+\u001dd\u001dcB2+Nq\nk\u0007`7Rp\u0001'\u0004)<NqSw\u00072=RkSo\u0003.*N\t\u001dJl\u001d`B\u0019/J\u000b\u0006\\k\u0017'67+\\n\u0000\u0005\u001dI|\u001fb\u0011\u001cXv\u0007f\u00104nTk\u0000s\u0003.:Q|\u0007\u001dMl\u001d'-.\u0005\u001dJl\u001d`\b\rHv\u0007h\u000f`\u0017\n\rHv\u0007h\u000f`\u0017\\r\b\rHv\u0007h\u000f`\u0014\b\rHv\u0007h\u000f`\u0016\n\u001dMl\u001d'6)-Vv\u0004\u001dMl\u001d\t\u0003\\l\u001d'\n! Y\u0005\u001dJl\u001d`\f\rHv\u0007h\u000f`\u001eTq\u0010o\u000b\rHv\u0007h\u000f`\u001cRi\u001f".length();
                var1_4 = 3;
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
                    var2_2 = "\u001dJl\u001d`B\u0010'If\u001b\u0004\u0003\\l\u001d";
                    var4_3 = "\u001dJl\u001d`B\u0010'If\u001b\u0004\u0003\\l\u001d".length();
                    var1_4 = 11;
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
                        v10 = 78;
                        break;
                    }
                    case 1: {
                        v10 = 61;
                        break;
                    }
                    case 2: {
                        v10 = 5;
                        break;
                    }
                    case 3: {
                        v10 = 115;
                        break;
                    }
                    case 4: {
                        v10 = 7;
                        break;
                    }
                    case 5: {
                        v10 = 98;
                        break;
                    }
                    default: {
                        v10 = 64;
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
        HandTweaksModule.b = var5;
    }
}
