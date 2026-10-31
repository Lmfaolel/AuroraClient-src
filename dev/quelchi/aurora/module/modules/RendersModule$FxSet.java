package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.RendersModule;
import dev.quelchi.aurora.module.modules.RendersModule$HandFx;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import java.util.ArrayList;
import java.util.Collections;

final class RendersModule$FxSet {
    final NumberSetting chromaSpeed;
    final NumberSetting chromaSaturation;
    final NumberSetting chromaBrightness;
    final ColorSetting solidColor1;
    final ColorSetting solidColor2;
    final NumberSetting gradientSpeed;
    final NumberSetting solidStrength;
    final NumberSetting balatroSpeed;
    final ColorSetting balatroColor1;
    final ColorSetting balatroColor2;
    final ColorSetting balatroColor3;
    final ColorSetting balatroColor4;
    final ColorSetting smokeColor;
    final NumberSetting smokeIntensity;
    final NumberSetting smokeSpeed;
    final ColorSetting stripesColor1;
    final ColorSetting stripesColor2;
    final NumberSetting stripesWidth;
    final NumberSetting stripesSpeed;
    final BooleanSetting glowFill;
    final NumberSetting glowRadius;
    final NumberSetting glowPower;
    final ColorSetting glowColor;
    final NumberSetting glowDispersion;
    final NumberSetting glassBlurSize;
    final NumberSetting glassQuality;
    final NumberSetting glassDirection;
    final NumberSetting glassRefraction;
    final NumberSetting glassBrightness;
    final BooleanSetting glassChromatic;
    final BooleanSetting glassDistortion;
    final BooleanSetting glassHideHand;
    final NumberSetting glassBackgroundBlur;
    final BooleanSetting glassOutline;
    final ColorSetting glassOutlineColor;
    final NumberSetting glassOutlineWidth;
    final BooleanSetting invertHideHand;
    final NumberSetting invertIntensity;
    final BooleanSetting invertHue;
    final BooleanSetting invertSaturation;
    final BooleanSetting invertBrightness;
    final NumberSetting invertEdgeGlow;
    final ColorSetting invertGlowColor;
    final ColorSetting snowColor;
    final NumberSetting snowIntensity;
    final NumberSetting snowSpeed;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private RendersModule$FxSet() {
        String[] stringArray = a;
        this.chromaSpeed = new NumberSetting(stringArray[3], 0.05, 1.0, 0.3, 0.05);
        this.chromaSaturation = new NumberSetting(stringArray[41], 0.1, 1.0, 0.5, 0.05);
        this.chromaBrightness = new NumberSetting(stringArray[43], 0.5, 1.5, 1.0, 0.05);
        this.solidColor1 = new ColorSetting(stringArray[16], RendersModule$FxSet.a(32047, 8999493873550266917L), false);
        this.solidColor2 = new ColorSetting(stringArray[18], RendersModule$FxSet.a(30666, 7441240349426522311L), false);
        this.gradientSpeed = new NumberSetting(stringArray[0], 0.1, 1.0, 0.5, 0.05);
        this.solidStrength = new NumberSetting(stringArray[12], 0.0, 1.0, 0.85, 0.05);
        this.balatroSpeed = new NumberSetting(stringArray[37], 0.1, 2.0, 0.5, 0.1);
        this.balatroColor1 = new ColorSetting(stringArray[33], RendersModule$FxSet.a(5683, 9121317527851858237L), false);
        this.balatroColor2 = new ColorSetting(stringArray[1], RendersModule$FxSet.a(12977, 6040093073814248883L), false);
        this.balatroColor3 = new ColorSetting(stringArray[35], RendersModule$FxSet.a(18727, 8314733077698389547L), false);
        this.balatroColor4 = new ColorSetting(stringArray[38], RendersModule$FxSet.a(3468, 1987564123895259791L), false);
        this.smokeColor = new ColorSetting(stringArray[4], RendersModule$FxSet.a(29007, 7860633849333489231L), false);
        this.smokeIntensity = new NumberSetting(stringArray[13], 0.1, 3.0, 1.0, 0.1);
        this.smokeSpeed = new NumberSetting(stringArray[14], 0.1, 2.0, 0.5, 0.1);
        this.stripesColor1 = new ColorSetting(stringArray[17], RendersModule$FxSet.a(10838, 7697148424245593433L), false);
        this.stripesColor2 = new ColorSetting(stringArray[6], RendersModule$FxSet.a(18498, 8833964690796193603L), false);
        this.stripesWidth = new NumberSetting(stringArray[31], 0.05, 0.5, 0.3, 0.05);
        this.stripesSpeed = new NumberSetting(stringArray[5], 0.1, 2.0, 0.3, 0.1);
        this.glowFill = new BooleanSetting(stringArray[40], true);
        this.glowRadius = new NumberSetting(stringArray[29], 0.01, 0.2, 0.04, 0.01);
        this.glowPower = new NumberSetting(stringArray[30], 0.1, 2.0, 0.75, 0.05);
        this.glowColor = new ColorSetting(stringArray[32], RendersModule$FxSet.a(9675, 509308664535993026L), false);
        this.glowDispersion = new NumberSetting(stringArray[8], 1.0, 10.0, 5.0, 1.0);
        this.glassBlurSize = new NumberSetting(stringArray[9], 5.0, 200.0, 50.0, 5.0);
        this.glassQuality = new NumberSetting(stringArray[45], 3.0, 20.0, 10.0, 1.0);
        this.glassDirection = new NumberSetting(stringArray[24], 4.0, 16.0, 10.0, 1.0);
        this.glassRefraction = new NumberSetting(stringArray[15], 0.0, 3.0, 1.0, 0.1);
        this.glassBrightness = new NumberSetting(stringArray[11], 0.5, 2.0, 1.1, 0.05);
        this.glassChromatic = new BooleanSetting(stringArray[7], true);
        this.glassDistortion = new BooleanSetting(stringArray[25], false);
        this.glassHideHand = new BooleanSetting(stringArray[36], true);
        this.glassBackgroundBlur = new NumberSetting(stringArray[26], 10.0, 100.0, 70.0, 5.0);
        this.glassOutline = new BooleanSetting(stringArray[27], true);
        this.glassOutlineColor = new ColorSetting(stringArray[44], RendersModule$FxSet.a(14038, 1364577723746376157L), true);
        this.glassOutlineWidth = new NumberSetting(stringArray[21], 0.5, 4.0, 1.5, 0.5);
        this.invertHideHand = new BooleanSetting(stringArray[10], true);
        this.invertIntensity = new NumberSetting(stringArray[42], 0.0, 1.0, 1.0, 0.05);
        this.invertHue = new BooleanSetting(stringArray[20], false);
        this.invertSaturation = new BooleanSetting(stringArray[19], false);
        this.invertBrightness = new BooleanSetting(stringArray[39], false);
        this.invertEdgeGlow = new NumberSetting(stringArray[2], 0.0, 2.0, 0.5, 0.1);
        this.invertGlowColor = new ColorSetting(stringArray[28], RendersModule$FxSet.a(24780, 6219184961209208772L), false);
        this.snowColor = new ColorSetting(stringArray[22], -1, false);
        this.snowIntensity = new NumberSetting(stringArray[34], 0.1, 3.0, 1.0, 0.1);
        this.snowSpeed = new NumberSetting(stringArray[23], 0.1, 2.0, 1.0, 0.1);
    }

    
    Setting[] group(RendersModule$HandFx rendersModule$HandFx) {
        Setting[] settingArray;
        boolean bl = AmbienceModule.b;
        int n = rendersModule$HandFx.ordinal();
        if (!bl) {
            switch (n) {
                case 2: {
                    Setting[] settingArray2 = new Setting[3];
                    settingArray2[0] = this.chromaSpeed;
                    settingArray2[1] = this.chromaSaturation;
                    settingArray = settingArray2;
                    settingArray2[2] = this.chromaBrightness;
                    return settingArray;
                }
                case 1: {
                    Setting[] settingArray3 = new Setting[4];
                    settingArray3[0] = this.solidColor1;
                    settingArray3[1] = this.solidColor2;
                    settingArray3[2] = this.gradientSpeed;
                    settingArray = settingArray3;
                    settingArray3[3] = this.solidStrength;
                    return settingArray;
                }
                case 3: {
                    Setting[] settingArray4 = new Setting[5];
                    settingArray4[0] = this.balatroSpeed;
                    settingArray4[1] = this.balatroColor1;
                    settingArray4[2] = this.balatroColor2;
                    settingArray4[3] = this.balatroColor3;
                    settingArray = settingArray4;
                    settingArray4[4] = this.balatroColor4;
                    return settingArray;
                }
                case 4: {
                    Setting[] settingArray5 = new Setting[3];
                    settingArray5[0] = this.smokeColor;
                    settingArray5[1] = this.smokeIntensity;
                    settingArray = settingArray5;
                    settingArray5[2] = this.smokeSpeed;
                    return settingArray;
                }
                case 5: {
                    Setting[] settingArray6 = new Setting[4];
                    settingArray6[0] = this.stripesColor1;
                    settingArray6[1] = this.stripesColor2;
                    settingArray6[2] = this.stripesWidth;
                    settingArray = settingArray6;
                    settingArray6[3] = this.stripesSpeed;
                    return settingArray;
                }
                case 6: {
                    Setting[] settingArray7 = new Setting[5];
                    settingArray7[0] = this.glowFill;
                    settingArray7[1] = this.glowRadius;
                    settingArray7[2] = this.glowPower;
                    settingArray7[3] = this.glowColor;
                    settingArray = settingArray7;
                    settingArray7[4] = this.glowDispersion;
                    return settingArray;
                }
                case 7: {
                    Setting[] settingArray8 = new Setting[12];
                    settingArray8[0] = this.glassBlurSize;
                    settingArray8[1] = this.glassQuality;
                    settingArray8[2] = this.glassDirection;
                    settingArray8[3] = this.glassRefraction;
                    settingArray8[4] = this.glassBrightness;
                    settingArray8[5] = this.glassChromatic;
                    settingArray8[6] = this.glassDistortion;
                    settingArray8[7] = this.glassHideHand;
                    settingArray8[8] = this.glassBackgroundBlur;
                    settingArray8[9] = this.glassOutline;
                    settingArray8[10] = this.glassOutlineColor;
                    settingArray = settingArray8;
                    settingArray8[11] = this.glassOutlineWidth;
                    return settingArray;
                }
                case 8: {
                    Setting[] settingArray9 = new Setting[7];
                    settingArray9[0] = this.invertHideHand;
                    settingArray9[1] = this.invertIntensity;
                    settingArray9[2] = this.invertHue;
                    settingArray9[3] = this.invertSaturation;
                    settingArray9[4] = this.invertBrightness;
                    settingArray9[5] = this.invertEdgeGlow;
                    settingArray = settingArray9;
                    settingArray9[6] = this.invertGlowColor;
                    return settingArray;
                }
                case 9: {
                    Setting[] settingArray10 = new Setting[3];
                    settingArray10[0] = this.snowColor;
                    settingArray10[1] = this.snowIntensity;
                    settingArray = settingArray10;
                    settingArray10[2] = this.snowSpeed;
                    return settingArray;
                }
            }
            n = 0;
        }
        settingArray = new Setting[n];
        return settingArray;
    }

    Setting[] all() {
        Object[] objectArray;
        block2: {
            ArrayList arrayList = new ArrayList();
            RendersModule$HandFx[] rendersModule$HandFxArray = RendersModule$HandFx.values();
            int n = rendersModule$HandFxArray.length;
            boolean bl = AmbienceModule.b;
            for (int i = 0; i < n; ++i) {
                objectArray = rendersModule$HandFxArray;
                if (!bl) {
                    RendersModule$HandFx rendersModule$HandFx = objectArray[i];
                    Collections.addAll(arrayList, this.group(rendersModule$HandFx));
                    if (!bl) continue;
                }
                break block2;
            }
            objectArray = arrayList.toArray(new Setting[0]);
        }
        return (Setting[])objectArray;
    }

    
    float[] params(RendersModule$HandFx var1_1, float var2_2, float var3_3, float var4_4) {
        var5_5 = AmbienceModule.b;
        v0 = var1_1.ordinal();
        if (!var5_5) {
        }
        {
            switch (v0)
        }
lbl_neg_1000:

        {
            case 2: {
                v0 = 4;
lbl12:

                v2 = new float[v0];
                v2[0] = var2_2;
                v2[1] = (float)this.chromaSpeed.getValue();
                v2[2] = (float)this.chromaSaturation.getValue();
                v3 = v2;
                v2[3] = (float)this.chromaBrightness.getValue();
                break;
            }
lbl19:

            case 1: {
                v4 = new float[12];
                v4[0] = var2_2 * (float)this.gradientSpeed.getValue();
                v4[1] = (float)this.solidStrength.getValue();
                v4[2] = 0.0f;
                v4[3] = 0.0f;
                v4[4] = RendersModule.r(this.solidColor1.getRGB());
                v4[5] = RendersModule.g(this.solidColor1.getRGB());
                v4[6] = RendersModule.b(this.solidColor1.getRGB());
                v4[7] = 0.0f;
                v4[8] = RendersModule.r(this.solidColor2.getRGB());
                v4[9] = RendersModule.g(this.solidColor2.getRGB());
                v4[10] = RendersModule.b(this.solidColor2.getRGB());
                v3 = v4;
                v4[11] = 0.0f;
                break;
            }
lbl35:

            case 3: {
                v5 = new float[20];
                v5[0] = var2_2 * (float)this.balatroSpeed.getValue();
                v5[1] = var3_3;
                v5[2] = var4_4;
                v5[3] = 0.0f;
                v5[4] = RendersModule.r(this.balatroColor1.getRGB());
                v5[5] = RendersModule.g(this.balatroColor1.getRGB());
                v5[6] = RendersModule.b(this.balatroColor1.getRGB());
                v5[7] = 0.0f;
                v5[8] = RendersModule.r(this.balatroColor2.getRGB());
                v5[9] = RendersModule.g(this.balatroColor2.getRGB());
                v5[10] = RendersModule.b(this.balatroColor2.getRGB());
                v5[11] = 0.0f;
                v5[12] = RendersModule.r(this.balatroColor3.getRGB());
                v5[13] = RendersModule.g(this.balatroColor3.getRGB());
                v5[14] = RendersModule.b(this.balatroColor3.getRGB());
                v5[15] = 0.0f;
                v5[16] = RendersModule.r(this.balatroColor4.getRGB());
                v5[17] = RendersModule.g(this.balatroColor4.getRGB());
                v5[18] = RendersModule.b(this.balatroColor4.getRGB());
                v3 = v5;
                v5[19] = 0.0f;
                break;
            }
lbl59:

            case 4: {
                v6 = new float[8];
                v6[0] = var2_2 * (float)this.smokeSpeed.getValue();
                v6[1] = var3_3;
                v6[2] = var4_4;
                v6[3] = (float)this.smokeIntensity.getValue();
                v6[4] = RendersModule.r(this.smokeColor.getRGB());
                v6[5] = RendersModule.g(this.smokeColor.getRGB());
                v6[6] = RendersModule.b(this.smokeColor.getRGB());
                v3 = v6;
                v6[7] = 0.0f;
                break;
            }
lbl71:

            case 5: {
                v7 = new float[16];
                v7[0] = var2_2;
                v7[1] = var3_3;
                v7[2] = var4_4;
                v7[3] = (float)this.stripesWidth.getValue();
                v7[4] = RendersModule.r(this.stripesColor1.getRGB());
                v7[5] = RendersModule.g(this.stripesColor1.getRGB());
                v7[6] = RendersModule.b(this.stripesColor1.getRGB());
                v7[7] = 0.0f;
                v7[8] = RendersModule.r(this.stripesColor2.getRGB());
                v7[9] = RendersModule.g(this.stripesColor2.getRGB());
                v7[10] = RendersModule.b(this.stripesColor2.getRGB());
                v7[11] = 0.0f;
                v7[12] = (float)this.stripesSpeed.getValue();
                v7[13] = 0.0f;
                v7[14] = 0.0f;
                v3 = v7;
                v7[15] = 0.0f;
                break;
            }
lbl91:

            case 6: {
                v8 = new float[12];
                v8[0] = var2_2;
                v8[1] = var3_3;
                v8[2] = var4_4;
                v8[3] = (float)this.glowRadius.getValue();
                v8[4] = RendersModule.r(this.glowColor.getRGB());
                v9 = v8;
                v10 = v8;
                v11 = 5;
                v12 = RendersModule.g(this.glowColor.getRGB());
                if (var5_5) // goto
                v9[v11] = v12;
                v10[6] = RendersModule.b(this.glowColor.getRGB());
                v10[7] = 0.0f;
                v10[8] = (float)this.glowPower.getValue();
                v10[9] = (float)this.glowDispersion.getValue();
                v9 = v10;
                v10 = v10;
                v11 = 10;
                if (this.glowFill.getValue()) {
                }
                v12 = 1.0f;
lbl122:

                v12 = 0.0f;
lbl123:

                v9[v11] = v12;
                v3 = v10;
                v10[11] = 0.0f;
                break;
            }
lbl127:

            case 7: {
                v15 = new float[12];
                v15[0] = var2_2;
                v15[1] = var3_3;
                v15[2] = var4_4;
                v15[3] = (float)this.glassBlurSize.getValue();
                v16 = v15;
                v17 = v15;
                v18 = 4;
                v19 = (float)this.glassQuality.getValue();
                if (var5_5) // goto
                v16[v18] = v19;
                v17[5] = (float)this.glassDirection.getValue();
                v17[6] = (float)this.glassRefraction.getValue();
                v17[7] = (float)this.glassBrightness.getValue();
                v16 = v17;
                v17 = v17;
                v18 = 8;
                if (this.glassChromatic.getValue()) {
                }
                v19 = 1.0f;
lbl156:

                v19 = 0.0f;
lbl158:

                v16[v18] = v19;
                v22 = v17;
                v23 = v17;
                v24 = 9;
                v25 = this.glassDistortion.getValue() != false ? 1.0f : 0.0f;
                v22[v24] = v25;
                v27 = v23;
                v28 = v23;
                v29 = 10;
                v30 = this.glassHideHand.getValue() != false ? 1.0f : 0.0f;
                v27[v29] = v30;
                v3 = v28;
                v28[11] = (float)this.glassBackgroundBlur.getValue();
                break;
            }
lbl177:

            case 8: {
                v32 = new float[16];
                v32[0] = var2_2;
                v32[1] = var3_3;
                v32[2] = var4_4;
                v32[3] = (float)this.invertIntensity.getValue();
                v33 = v32;
                v34 = v32;
                v35 = 4;
                v36 = RendersModule.r(this.invertGlowColor.getRGB());
                if (var5_5) // goto
                v33[v35] = v36;
                v34[5] = RendersModule.g(this.invertGlowColor.getRGB());
                v34[6] = RendersModule.b(this.invertGlowColor.getRGB());
                v34[7] = 0.0f;
                v33 = v34;
                v34 = v34;
                v35 = 8;
                if (this.invertHideHand.getValue()) {
                }
                v36 = 1.0f;
lbl206:

                v36 = 0.0f;
lbl208:

                v33[v35] = v36;
                v39 = v34;
                v40 = v34;
                v41 = 9;
                v42 = this.invertHue.getValue() != false ? 1.0f : 0.0f;
                v39[v41] = v42;
                v44 = v40;
                v45 = v40;
                v46 = 10;
                v47 = this.invertSaturation.getValue() != false ? 1.0f : 0.0f;
                v44[v46] = v47;
                v49 = v45;
                v50 = v45;
                v51 = 11;
                v52 = this.invertBrightness.getValue() != false ? 1.0f : 0.0f;
                v49[v51] = v52;
                v50[12] = (float)this.invertEdgeGlow.getValue();
                v50[13] = 0.0f;
                v50[14] = 0.0f;
                v3 = v50;
                v50[15] = 0.0f;
                break;
            }
lbl238:

            case 9: {
                v54 = new float[12];
                v54[0] = var2_2;
                v54[1] = var3_3;
                v54[2] = var4_4;
                v54[3] = (float)this.snowIntensity.getValue();
                v54[4] = RendersModule.r(this.snowColor.getRGB());
                v54[5] = RendersModule.g(this.snowColor.getRGB());
                v54[6] = RendersModule.b(this.snowColor.getRGB());
                v54[7] = 0.0f;
                v54[8] = (float)this.snowSpeed.getValue();
                v54[9] = 0.0f;
                v54[10] = 0.0f;
                v3 = v54;
                v54[11] = 0.0f;
                break;
            }
lbl254:

            default: {
                v3 = null;
            }
        }
        return v3;
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[46];
                        var11_1 = 0;
                        var10_2 = "a\u0002X297'RPJ&57-\u000fd\u0011U7$ &\u0006\u0013V:? i\u0014\u0010o\u001eO3\"&iC\u0014^3p5%I\u0007\fe\u0018K9=3iU\u0000\\34\u000bu\u001dV=5r*I\u001cV$\ru\u0004K? 7:\u0006\u0003I356\u000fu\u0004K? 7:\u0006\u0013V:? i\u0014\u000fa\u001cX%#r*N\u0002V;1& E\u000fa\u001cV!p6 U\u0000\\$#;&H\u000fa\u001cX%#r+J\u0005Kv#;3C\u0010o\u001eO3\"&iN\u0019]3p:(H\u0014\u0010a\u001cX%#r+T\u0019^>$<,U\u0003\u000eu\u001fU?4r:R\u0002\\87&!\u000fu\u001dV=5r H\u0004\\8#;=_\u000bu\u001dV=5r:V\u0015\\2\u0010a\u001cX%#r;C\u0016K73& I\u001e\ru\u001fU?4r*I\u001cV$pc\u000fu\u0004K? 7:\u0006\u0013V:? i\u0017\ru\u001fU?4r*I\u001cV$p`\u0011o\u001eO3\"&iU\u0011M#\"3=O\u001fW\no\u001eO3\"&iN\u0005\\\u0013a\u001cX%#r&S\u0004U?>7iQ\u0019]\"8\nu\u001eV!p1&J\u001fK\nu\u001eV!p!9C\u0015]\u000fa\u001cX%#r-O\u0002\\5$;&H\u0010a\u001cX%#r-O\u0003M9\"& I\u001e\u0015a\u001cX%#r+G\u0013R1\"=<H\u0014\u00194<';\ra\u001cX%#r&S\u0004U?>7\u0011o\u001eO3\"&iA\u001cV!p1&J\u001fK\u000ba\u001cV!p (B\u0019L%\na\u001cV!p\"&Q\u0015K\ru\u0004K? 7:\u0006\u0007P2$:\na\u001cV!p1&J\u001fK\u000fd\u0011U7$ &\u0006\u0013V:? i\u0017\u000eu\u001eV!p;'R\u0015W%9&0\u000fd\u0011U7$ &\u0006\u0013V:? i\u0015\u0011a\u001cX%#r:C\u0015\u0019\"8 &S\u0017Q\rd\u0011U7$ &\u0006\u0003I356\u0016d\u0011U7$ &\u0006\u0013V8$ (U\u0004\u0016:95!R\u0011o\u001eO3\"&iD\u0002P18&'C\u0003J\u000ea\u001cV!p4 J\u001c\u0019>1<-\u0011e\u0018K9=3iU\u0011M#\"3=O\u001fW\u0010o\u001eO3\"&iO\u001eM3>! R\t\u0011e\u0018K9=3iD\u0002P18&'C\u0003J";
                        var12_3 = "a\u0002X297'RPJ&57-\u000fd\u0011U7$ &\u0006\u0013V:? i\u0014\u0010o\u001eO3\"&iC\u0014^3p5%I\u0007\fe\u0018K9=3iU\u0000\\34\u000bu\u001dV=5r*I\u001cV$\ru\u0004K? 7:\u0006\u0003I356\u000fu\u0004K? 7:\u0006\u0013V:? i\u0014\u000fa\u001cX%#r*N\u0002V;1& E\u000fa\u001cV!p6 U\u0000\\$#;&H\u000fa\u001cX%#r+J\u0005Kv#;3C\u0010o\u001eO3\"&iN\u0019]3p:(H\u0014\u0010a\u001cX%#r+T\u0019^>$<,U\u0003\u000eu\u001fU?4r:R\u0002\\87&!\u000fu\u001dV=5r H\u0004\\8#;=_\u000bu\u001dV=5r:V\u0015\\2\u0010a\u001cX%#r;C\u0016K73& I\u001e\ru\u001fU?4r*I\u001cV$pc\u000fu\u0004K? 7:\u0006\u0013V:? i\u0017\ru\u001fU?4r*I\u001cV$p`\u0011o\u001eO3\"&iU\u0011M#\"3=O\u001fW\no\u001eO3\"&iN\u0005\\\u0013a\u001cX%#r&S\u0004U?>7iQ\u0019]\"8\nu\u001eV!p1&J\u001fK\nu\u001eV!p!9C\u0015]\u000fa\u001cX%#r-O\u0002\\5$;&H\u0010a\u001cX%#r-O\u0003M9\"& I\u001e\u0015a\u001cX%#r+G\u0013R1\"=<H\u0014\u00194<';\ra\u001cX%#r&S\u0004U?>7\u0011o\u001eO3\"&iA\u001cV!p1&J\u001fK\u000ba\u001cV!p (B\u0019L%\na\u001cV!p\"&Q\u0015K\ru\u0004K? 7:\u0006\u0007P2$:\na\u001cV!p1&J\u001fK\u000fd\u0011U7$ &\u0006\u0013V:? i\u0017\u000eu\u001eV!p;'R\u0015W%9&0\u000fd\u0011U7$ &\u0006\u0013V:? i\u0015\u0011a\u001cX%#r:C\u0015\u0019\"8 &S\u0017Q\rd\u0011U7$ &\u0006\u0003I356\u0016d\u0011U7$ &\u0006\u0013V8$ (U\u0004\u0016:95!R\u0011o\u001eO3\"&iD\u0002P18&'C\u0003J\u000ea\u001cV!p4 J\u001c\u0019>1<-\u0011e\u0018K9=3iU\u0011M#\"3=O\u001fW\u0010o\u001eO3\"&iO\u001eM3>! R\t\u0011e\u0018K9=3iD\u0002P18&'C\u0003J".length();
                        var9_4 = 14;
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
                            var10_2 = "a\u001cX%#r&S\u0004U?>7iE\u001fU9\"\ra\u001cX%#r8S\u0011U?$+";
                            var12_3 = "a\u001cX%#r&S\u0004U?>7iE\u001fU9\"\ra\u001cX%#r8S\u0011U?$+".length();
                            var9_4 = 19;
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
                                v10 = 38;
                                break;
                            }
                            case 1: {
                                v10 = 112;
                                break;
                            }
                            case 2: {
                                v10 = 57;
                                break;
                            }
                            case 3: {
                                v10 = 86;
                                break;
                            }
                            case 4: {
                                v10 = 80;
                                break;
                            }
                            case 5: {
                                v10 = 82;
                                break;
                            }
                            default: {
                                v10 = 73;
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
                RendersModule$FxSet.a = var13;
                var0_7 = 4328223634978057322L;
                var6_8 = new long[12];
                var3_9 = 0;
                var4_10 = "\u00e5\u009c_\u00c2\u0095\u00fe\u008aHE\u0081\u00edZ\u0013\u00fc\u008fe\u009e\u00f5\u00d4\u00c3\u001e\u00d7\u00f3s\u00c3GC\u008c\u00b5\u0002\\Q\u0088\u0014>\u0090\u00da\u00b6\u0093\u00bah$\u00cem\u00cb\u00a8\u001d\f\u00c3Y\u00c7<\u0013\u0015}\u001b\u00e6\u00f5_\u00acz \u00ab$`K\u0081|u\u001eN%\u0012\u00ff>-*\u00af\u0098-";
                var5_11 = "\u00e5\u009c_\u00c2\u0095\u00fe\u008aHE\u0081\u00edZ\u0013\u00fc\u008fe\u009e\u00f5\u00d4\u00c3\u001e\u00d7\u00f3s\u00c3GC\u008c\u00b5\u0002\\Q\u0088\u0014>\u0090\u00da\u00b6\u0093\u00bah$\u00cem\u00cb\u00a8\u001d\f\u00c3Y\u00c7<\u0013\u0015}\u001b\u00e6\u00f5_\u00acz \u00ab$`K\u0081|u\u001eN%\u0012\u00ff>-*\u00af\u0098-".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v11 = var6_8;
                    v12 = var3_9++;
                    v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "::\u00c1\b\u00c9JPO\u00aeD\u00c7\u00e6\u009a+\u00a7C";
                    var5_11 = "::\u00c1\b\u00c9JPO\u00aeD\u00c7\u00e6\u009a+\u00a7C".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v11 = var6_8;
                        v12 = var3_9++;
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
                    if (var2_12 < var5_11) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var0_7;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        RendersModule$FxSet.b = var6_8;
        RendersModule$FxSet.c = new Integer[12];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6F0B;
        if (c[n2] == null) {
            RendersModule$FxSet.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
