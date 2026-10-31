package dev.quelchi.aurora.module.modules;

import com.mojang.blaze3d.textures.GpuTextureView;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.RendersModule$BlendMode;
import dev.quelchi.aurora.module.modules.RendersModule$FxSet;
import dev.quelchi.aurora.module.modules.RendersModule$HandFx;
import dev.quelchi.aurora.module.modules.RendersModule$PlayerFx;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import dev.quelchi.aurora.render.HandShaderGpu;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;

public final class RendersModule
extends Module {
    public static final Identifier OCCLUDED_OUTLINE;
    private static RendersModule instance;
    private final BooleanSetting players;
    private final BooleanSetting crystals;
    private final BooleanSetting noArmorGlint;
    private final RendersModule$FxSet playerFx;
    private final RendersModule$FxSet crystalFx;
    private final EnumSetting<RendersModule$PlayerFx> playerShader;
    private final EnumSetting<RendersModule$PlayerFx> crystalShader;
    private final EnumSetting<RendersModule$HandFx> handShader;
    private final NumberSetting chromaSpeed;
    private final NumberSetting chromaSaturation;
    private final NumberSetting chromaBrightness;
    private final ColorSetting solidColor1;
    private final ColorSetting solidColor2;
    private final NumberSetting gradientSpeed;
    private final NumberSetting solidStrength;
    private final NumberSetting balatroSpeed;
    private final ColorSetting balatroColor1;
    private final ColorSetting balatroColor2;
    private final ColorSetting balatroColor3;
    private final ColorSetting balatroColor4;
    private final ColorSetting smokeColor;
    private final NumberSetting smokeIntensity;
    private final NumberSetting smokeSpeed;
    private final ColorSetting stripesColor1;
    private final ColorSetting stripesColor2;
    private final NumberSetting stripesWidth;
    private final NumberSetting stripesSpeed;
    private final BooleanSetting glowFill;
    private final NumberSetting glowRadius;
    private final NumberSetting glowPower;
    private final ColorSetting glowColor;
    private final NumberSetting glowDispersion;
    private final NumberSetting glassBlurSize;
    private final NumberSetting glassQuality;
    private final NumberSetting glassDirection;
    private final NumberSetting glassRefraction;
    private final NumberSetting glassBrightness;
    private final BooleanSetting glassChromatic;
    private final BooleanSetting glassDistortion;
    private final BooleanSetting glassHideHand;
    private final NumberSetting glassBackgroundBlur;
    private final BooleanSetting invertHideHand;
    private final NumberSetting invertIntensity;
    private final BooleanSetting invertHue;
    private final BooleanSetting invertSaturation;
    private final BooleanSetting invertBrightness;
    private final NumberSetting invertEdgeGlow;
    private final ColorSetting invertGlowColor;
    private final ColorSetting snowColor;
    private final NumberSetting snowIntensity;
    private final NumberSetting snowSpeed;
    private final BooleanSetting dualShaderMode;
    private final EnumSetting<RendersModule$HandFx> shader1;
    private final EnumSetting<RendersModule$HandFx> shader2;
    private final EnumSetting<RendersModule$BlendMode> blendMode;
    private final NumberSetting blendIntensity;
    private final NumberSetting shader1Strength;
    private final NumberSetting shader2Strength;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;
    private static final long[] f;
    private static final Long[] g;

    public RendersModule() {
        block9: {
            boolean bl;
            block11: {
                block10: {
                    boolean bl2;
                    block8: {
                        String[] stringArray = b;
                        super(stringArray[48], stringArray[16], Category.RENDER, false);
                        this.players = new BooleanSetting(stringArray[37], true);
                        this.crystals = new BooleanSetting(stringArray[12], false);
                        this.noArmorGlint = new BooleanSetting(stringArray[51], false);
                        this.playerFx = new RendersModule$FxSet();
                        this.crystalFx = new RendersModule$FxSet();
                        this.playerShader = new EnumSetting<RendersModule$PlayerFx>(stringArray[4], RendersModule$PlayerFx.Glow);
                        this.crystalShader = new EnumSetting<RendersModule$PlayerFx>(stringArray[18], RendersModule$PlayerFx.Glow);
                        this.handShader = new EnumSetting<RendersModule$HandFx>(stringArray[56], RendersModule$HandFx.None);
                        this.chromaSpeed = new NumberSetting(stringArray[0], 0.05, 1.0, 0.3, 0.05);
                        this.chromaSaturation = new NumberSetting(stringArray[30], 0.1, 1.0, 0.5, 0.05);
                        this.chromaBrightness = new NumberSetting(stringArray[2], 0.5, 1.5, 1.0, 0.05);
                        this.solidColor1 = new ColorSetting(stringArray[43], RendersModule.a(28853, 3337879529966222277L), false);
                        this.solidColor2 = new ColorSetting(stringArray[25], RendersModule.a(17896, 2858830837743292057L), false);
                        this.gradientSpeed = new NumberSetting(stringArray[28], 0.1, 1.0, 0.5, 0.05);
                        this.solidStrength = new NumberSetting(stringArray[45], 0.0, 1.0, 0.85, 0.05);
                        this.balatroSpeed = new NumberSetting(stringArray[34], 0.1, 2.0, 0.5, 0.1);
                        this.balatroColor1 = new ColorSetting(stringArray[15], RendersModule.a(6087, 5789906847720922290L), false);
                        this.balatroColor2 = new ColorSetting(stringArray[31], RendersModule.a(16996, 8766687163244546327L), false);
                        this.balatroColor3 = new ColorSetting(stringArray[3], RendersModule.a(21039, 5078481530726259033L), false);
                        this.balatroColor4 = new ColorSetting(stringArray[6], RendersModule.a(27146, 5356240421583266174L), false);
                        this.smokeColor = new ColorSetting(stringArray[7], RendersModule.a(6373, 5424241988462072734L), false);
                        this.smokeIntensity = new NumberSetting(stringArray[8], 0.1, 3.0, 1.0, 0.1);
                        this.smokeSpeed = new NumberSetting(stringArray[27], 0.1, 2.0, 0.5, 0.1);
                        this.stripesColor1 = new ColorSetting(stringArray[53], RendersModule.a(28884, 4024058299855059884L), false);
                        this.stripesColor2 = new ColorSetting(stringArray[40], RendersModule.a(3121, 8522415531786848072L), false);
                        this.stripesWidth = new NumberSetting(stringArray[32], 0.05, 0.5, 0.3, 0.05);
                        this.stripesSpeed = new NumberSetting(stringArray[33], 0.1, 2.0, 0.3, 0.1);
                        this.glowFill = new BooleanSetting(stringArray[54], true);
                        this.glowRadius = new NumberSetting(stringArray[57], 0.01, 0.2, 0.04, 0.01);
                        this.glowPower = new NumberSetting(stringArray[21], 0.1, 2.0, 0.75, 0.05);
                        this.glowColor = new ColorSetting(stringArray[41], RendersModule.a(3967, 8393616783653585933L), false);
                        this.glowDispersion = new NumberSetting(stringArray[47], 1.0, 10.0, 5.0, 1.0);
                        this.glassBlurSize = new NumberSetting(stringArray[42], 5.0, 200.0, 50.0, 5.0);
                        this.glassQuality = new NumberSetting(stringArray[52], 3.0, 20.0, 10.0, 1.0);
                        this.glassDirection = new NumberSetting(stringArray[44], 4.0, 16.0, 10.0, 1.0);
                        this.glassRefraction = new NumberSetting(stringArray[35], 0.0, 3.0, 1.0, 0.1);
                        this.glassBrightness = new NumberSetting(stringArray[29], 0.5, 2.0, 1.1, 0.05);
                        this.glassChromatic = new BooleanSetting(stringArray[26], true);
                        this.glassDistortion = new BooleanSetting(stringArray[1], false);
                        this.glassHideHand = new BooleanSetting(stringArray[59], true);
                        this.glassBackgroundBlur = new NumberSetting(stringArray[5], 10.0, 100.0, 70.0, 5.0);
                        this.invertHideHand = new BooleanSetting(stringArray[36], true);
                        this.invertIntensity = new NumberSetting(stringArray[46], 0.0, 1.0, 1.0, 0.05);
                        this.invertHue = new BooleanSetting(stringArray[24], false);
                        this.invertSaturation = new BooleanSetting(stringArray[10], false);
                        this.invertBrightness = new BooleanSetting(stringArray[55], false);
                        this.invertEdgeGlow = new NumberSetting(stringArray[13], 0.0, 2.0, 0.5, 0.1);
                        this.invertGlowColor = new ColorSetting(stringArray[19], RendersModule.a(32020, 1702765621640869475L), false);
                        this.snowColor = new ColorSetting(stringArray[39], -1, false);
                        this.snowIntensity = new NumberSetting(stringArray[17], 0.1, 3.0, 1.0, 0.1);
                        this.snowSpeed = new NumberSetting(stringArray[22], 0.1, 2.0, 1.0, 0.1);
                        this.dualShaderMode = new BooleanSetting(stringArray[49], false);
                        this.shader1 = new EnumSetting<RendersModule$HandFx>(stringArray[9], RendersModule$HandFx.Glow);
                        this.shader2 = new EnumSetting<RendersModule$HandFx>(stringArray[14], RendersModule$HandFx.Smoke);
                        this.blendMode = new EnumSetting<RendersModule$BlendMode>(stringArray[11], RendersModule$BlendMode.Add);
                        this.blendIntensity = new NumberSetting(stringArray[20], 0.0, 1.0, 1.0, 0.05);
                        this.shader1Strength = new NumberSetting(stringArray[50], 0.0, 1.0, 1.0, 0.05);
                        this.shader2Strength = new NumberSetting(stringArray[38], 0.0, 1.0, 1.0, 0.05);
                        instance = this;
                        this.addSettings(this.handShader, this.dualShaderMode, this.shader1, this.shader2, this.blendMode, this.blendIntensity, this.shader1Strength, this.shader2Strength, this.chromaSpeed, this.chromaSaturation, this.chromaBrightness, this.solidColor1, this.solidColor2, this.gradientSpeed, this.solidStrength, this.balatroSpeed, this.balatroColor1, this.balatroColor2, this.balatroColor3, this.balatroColor4, this.smokeColor, this.smokeIntensity, this.smokeSpeed, this.stripesColor1, this.stripesColor2, this.stripesWidth, this.stripesSpeed, this.glowFill, this.glowRadius, this.glowPower, this.glowColor, this.glowDispersion, this.glassBlurSize, this.glassQuality, this.glassDirection, this.glassRefraction, this.glassBrightness, this.glassChromatic, this.glassDistortion, this.glassHideHand, this.glassBackgroundBlur, this.invertHideHand, this.invertIntensity, this.invertHue, this.invertSaturation, this.invertBrightness, this.invertEdgeGlow, this.invertGlowColor, this.snowColor, this.snowIntensity, this.snowSpeed, this.players, this.crystals, this.noArmorGlint);
                        List<Setting> list = RendersModule.shaderRows(this.playerShader, this.playerFx);
                        this.addSettings(list.toArray(new Setting[0]));
                        list.add(this.noArmorGlint);
                        this.players.children(list.toArray(new Setting[0]));
                        Setting[] settingArray = RendersModule.shaderRows(this.crystalShader, this.crystalFx).toArray(new Setting[0]);
                        this.addSettings(settingArray);
                        this.crystals.children(settingArray);
                        this.handShader.children(this.dualShaderMode, this.shader1, this.shader2, this.blendMode, this.blendIntensity, this.shader1Strength, this.shader2Strength, this.chromaSpeed, this.chromaSaturation, this.chromaBrightness, this.solidColor1, this.solidColor2, this.gradientSpeed, this.solidStrength, this.balatroSpeed, this.balatroColor1, this.balatroColor2, this.balatroColor3, this.balatroColor4, this.smokeColor, this.smokeIntensity, this.smokeSpeed, this.stripesColor1, this.stripesColor2, this.stripesWidth, this.stripesSpeed, this.glowFill, this.glowRadius, this.glowPower, this.glowColor, this.glowDispersion, this.glassBlurSize, this.glassQuality, this.glassDirection, this.glassRefraction, this.glassBrightness, this.glassChromatic, this.glassDistortion, this.glassHideHand, this.glassBackgroundBlur, this.invertHideHand, this.invertIntensity, this.invertHue, this.invertSaturation, this.invertBrightness, this.invertEdgeGlow, this.invertGlowColor, this.snowColor, this.snowIntensity, this.snowSpeed);
                        Setting[] settingArray2 = new Setting[]{this.shader1, this.shader2, this.blendMode, this.blendIntensity, this.shader1Strength, this.shader2Strength};
                        bl2 = AmbienceModule.b;
                        for (Setting setting : settingArray2) {
                            if (!bl2) {
                                setting.visibleWhen(this.dualShaderMode::getValue);
                                if (!bl2) continue;
                                break;
                            }
                            break block8;
                        }
                        this.gate(RendersModule$HandFx.Chroma, this.chromaSpeed, this.chromaSaturation, this.chromaBrightness);
                        this.gate(RendersModule$HandFx.Solid, this.solidColor1, this.solidColor2, this.gradientSpeed, this.solidStrength);
                        this.gate(RendersModule$HandFx.Balatro, this.balatroSpeed, this.balatroColor1, this.balatroColor2, this.balatroColor3, this.balatroColor4);
                        this.gate(RendersModule$HandFx.Smoke, this.smokeColor, this.smokeIntensity, this.smokeSpeed);
                        this.gate(RendersModule$HandFx.Stripes, this.stripesColor1, this.stripesColor2, this.stripesWidth, this.stripesSpeed);
                        this.gate(RendersModule$HandFx.Glow, this.glowFill, this.glowRadius, this.glowPower, this.glowColor, this.glowDispersion);
                        this.gate(RendersModule$HandFx.Glass, this.glassBlurSize, this.glassQuality, this.glassDirection, this.glassRefraction, this.glassBrightness, this.glassChromatic, this.glassDistortion, this.glassHideHand, this.glassBackgroundBlur);
                        this.gate(RendersModule$HandFx.Invert, this.invertHideHand, this.invertIntensity, this.invertHue, this.invertSaturation, this.invertBrightness, this.invertEdgeGlow, this.invertGlowColor);
                        this.gate(RendersModule$HandFx.Snow, this.snowColor, this.snowIntensity, this.snowSpeed);
                    }
                    if (!Module.a) break block9;
                    if (!bl2) break block10;
                    bl = false;
                    break block11;
                }
                bl = true;
            }
            AmbienceModule.b = bl;
        }
    }

    private void gate(RendersModule$HandFx rendersModule$HandFx, Setting ... settingArray) {
        Setting[] settingArray2 = settingArray;
        boolean bl = AmbienceModule.b;
        for (Setting setting : settingArray2) {
            setting.visibleWhen(() -> this.lambda$gate$0(rendersModule$HandFx));
            if (!bl) continue;
        }
    }

    private static List<Setting> shaderRows(EnumSetting<RendersModule$PlayerFx> enumSetting, RendersModule$FxSet rendersModule$FxSet) {
        ArrayList<Setting> arrayList = new ArrayList<Setting>();
        arrayList.add(enumSetting);
        RendersModule$PlayerFx[] rendersModule$PlayerFxArray = RendersModule$PlayerFx.values();
        int n = rendersModule$PlayerFxArray.length;
        boolean bl = AmbienceModule.b;
        int n2 = 0;
        while (n2 < n) {
            block5: {
                RendersModule$PlayerFx rendersModule$PlayerFx = rendersModule$PlayerFxArray[n2];
                for (Setting setting : rendersModule$FxSet.group(RendersModule$HandFx.valueOf(rendersModule$PlayerFx.name()))) {
                    setting.visibleWhen(() -> RendersModule.lambda$shaderRows$1(enumSetting, rendersModule$PlayerFx));
                    if (!bl) {
                        arrayList.add(setting);
                        if (!bl) continue;
                        break;
                    }
                    break block5;
                }
                ++n2;
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    public static int chamsColor(Object object) {
        return -1;
    }

    public static boolean noArmorGlint() {
        boolean bl;
        block16: {
            block13: {
                BooleanSetting booleanSetting;
                boolean bl2;
                block15: {
                    RendersModule rendersModule;
                    RendersModule rendersModule2;
                    block14: {
                        block12: {
                            rendersModule2 = instance;
                            bl2 = AmbienceModule.b;
                            rendersModule = rendersModule2;
                            if (bl2) break block12;
                            if (rendersModule == null) break block13;
                            rendersModule = rendersModule2;
                        }
                        if (bl2) break block14;
                        if (!rendersModule.isEnabled()) break block13;
                        rendersModule = rendersModule2;
                    }
                    booleanSetting = rendersModule.players;
                    if (bl2) break block15;
                    if (!booleanSetting.getValue()) break block13;
                    booleanSetting = rendersModule2.noArmorGlint;
                }
                bl = booleanSetting.getValue();
                if (bl2) break block16;
                if (!bl) break block13;
                bl = true;
                break block16;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean playerShaderActive() {
        boolean bl;
        block11: {
            block9: {
                RendersModule rendersModule;
                boolean bl2;
                block10: {
                    RendersModule rendersModule2;
                    block8: {
                        rendersModule2 = instance;
                        bl2 = AmbienceModule.b;
                        rendersModule = rendersModule2;
                        if (bl2) break block8;
                        if (rendersModule == null) break block9;
                        rendersModule = rendersModule2;
                    }
                    if (bl2) break block10;
                    if (!rendersModule.isEnabled()) break block9;
                    rendersModule = rendersModule2;
                }
                bl = rendersModule.players.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean crystalShaderActive() {
        boolean bl;
        block11: {
            block9: {
                RendersModule rendersModule;
                boolean bl2;
                block10: {
                    RendersModule rendersModule2;
                    block8: {
                        rendersModule2 = instance;
                        bl2 = AmbienceModule.b;
                        rendersModule = rendersModule2;
                        if (bl2) break block8;
                        if (rendersModule == null) break block9;
                        rendersModule = rendersModule2;
                    }
                    if (bl2) break block10;
                    if (!rendersModule.isEnabled()) break block9;
                    rendersModule = rendersModule2;
                }
                bl = rendersModule.crystals.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean maskActive() {
        boolean bl;
        block6: {
            block8: {
                block7: {
                    boolean bl2 = AmbienceModule.b;
                    bl = RendersModule.playerShaderActive();
                    if (bl2) break block6;
                    if (bl) break block7;
                    bl = RendersModule.crystalShaderActive();
                    if (bl2) break block6;
                    if (!bl) break block8;
                }
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    public static void renderPlayerShaders(boolean bl, GpuTextureView gpuTextureView, GpuTextureView gpuTextureView2, GpuTextureView gpuTextureView3) {
        float[] fArray;
        float[] fArray2;
        RendersModule$HandFx rendersModule$HandFx;
        RendersModule rendersModule;
        block18: {
            RendersModule$FxSet rendersModule$FxSet;
            RendersModule$FxSet rendersModule$FxSet2;
            Window window;
            block19: {
                boolean bl2;
                block17: {
                    rendersModule = instance;
                    bl2 = AmbienceModule.b;
                    if (rendersModule != null && RendersModule.maskActive()) break block17;
                    return;
                }
                float f = (float)(System.nanoTime() % RendersModule.b(8462, 396814320646953309L)) / 1.0E9f;
                window = MinecraftClient.method_1551().method_22683();
                RendersModule$FxSet rendersModule$FxSet3 = bl ? rendersModule.crystalFx : rendersModule.playerFx;
                rendersModule$FxSet2 = rendersModule$FxSet3;
                EnumSetting<RendersModule$PlayerFx> enumSetting = bl ? rendersModule.crystalShader : rendersModule.playerShader;
                rendersModule$HandFx = RendersModule$HandFx.valueOf(enumSetting.getValue().name());
                fArray2 = rendersModule$FxSet2.params(rendersModule$HandFx, f, window.method_4489(), window.method_4506());
                fArray = null;
                if (rendersModule$HandFx != RendersModule$HandFx.Glass) break block18;
                rendersModule$FxSet = rendersModule$FxSet2;
                if (bl2) break block19;
                if (!rendersModule$FxSet.glassOutline.getValue()) break block18;
                rendersModule$FxSet = rendersModule$FxSet2;
            }
            int n = rendersModule$FxSet.glassOutlineColor.getARGB();
            fArray = new float[]{Math.max(1.0f, (float)rendersModule$FxSet2.glassOutlineWidth.getValue() * (float)window.method_4506() / 1080.0f), RendersModule.r(n), RendersModule.g(n), RendersModule.b(n), (float)(n >>> 24 & 0xFF) / 255.0f};
        }
        if (fArray2 != null) {
            HandShaderGpu.renderPlayers(rendersModule$HandFx.name(), fArray2, bl, RendersModule.seesThrough(rendersModule, bl), fArray, gpuTextureView, gpuTextureView2, gpuTextureView3);
        }
    }

    private static boolean seesThrough(RendersModule rendersModule, boolean bl) {
        boolean bl2;
        block19: {
            block21: {
                RendersModule rendersModule2;
                boolean bl3;
                block20: {
                    block16: {
                        block18: {
                            RendersModule rendersModule3;
                            block17: {
                                bl3 = AmbienceModule.b;
                                if (!bl) break block16;
                                rendersModule3 = rendersModule;
                                if (bl3) break block17;
                                if (rendersModule3.crystalShader.getValue() != RendersModule$PlayerFx.Glass) break block18;
                                rendersModule3 = rendersModule;
                            }
                            bl2 = rendersModule3.crystalFx.glassHideHand.getValue();
                            if (bl3) break block19;
                            if (!bl2) break block18;
                            bl2 = true;
                            break block19;
                        }
                        bl2 = false;
                        break block19;
                    }
                    rendersModule2 = rendersModule;
                    if (bl3) break block20;
                    if (rendersModule2.playerShader.getValue() != RendersModule$PlayerFx.Glass) break block21;
                    rendersModule2 = rendersModule;
                }
                bl2 = rendersModule2.playerFx.glassHideHand.getValue();
                if (bl3) break block19;
                if (!bl2) break block21;
                bl2 = true;
                break block19;
            }
            bl2 = false;
        }
        return bl2;
    }

    public static void captureGlassBackground() {
        block21: {
            block19: {
                boolean bl;
                block20: {
                    boolean bl2;
                    RendersModule rendersModule;
                    block17: {
                        block18: {
                            rendersModule = instance;
                            bl2 = AmbienceModule.b;
                            if (rendersModule == null) {
                                return;
                            }
                            bl = RendersModule.playerShaderActive();
                            if (bl2) break block17;
                            if (!bl) break block18;
                            bl = RendersModule.seesThrough(rendersModule, false);
                            if (bl2) break block17;
                            if (bl) break block19;
                        }
                        bl = RendersModule.crystalShaderActive();
                    }
                    if (bl2) break block20;
                    if (!bl) break block21;
                    bl = RendersModule.seesThrough(rendersModule, true);
                }
                if (!bl) break block21;
            }
            HandShaderGpu.captureBackground();
        }
    }

    private static float r(int n) {
        return (float)(n >> 16 & 0xFF) / 255.0f;
    }

    private static float g(int n) {
        return (float)(n >> 8 & 0xFF) / 255.0f;
    }

    private static float b(int n) {
        return (float)(n & 0xFF) / 255.0f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void renderHandShaders() {
        RendersModule rendersModule;
        float f;
        float f2;
        float f3;
        RendersModule rendersModule2;
        block39: {
            block40: {
                boolean bl;
                block29: {
                    block30: {
                        String string;
                        float[] fArray;
                        String string2;
                        float[] fArray2;
                        RendersModule$HandFx rendersModule$HandFx;
                        block38: {
                            RendersModule$HandFx rendersModule$HandFx2;
                            block36: {
                                block37: {
                                    RendersModule$HandFx rendersModule$HandFx3;
                                    block35: {
                                        RendersModule$HandFx rendersModule$HandFx4;
                                        block33: {
                                            block34: {
                                                block32: {
                                                    RendersModule$HandFx rendersModule$HandFx5;
                                                    block31: {
                                                        RendersModule rendersModule3;
                                                        block28: {
                                                            rendersModule2 = instance;
                                                            bl = AmbienceModule.b;
                                                            rendersModule3 = rendersModule2;
                                                            if (bl) break block28;
                                                            if (rendersModule3 == null) return;
                                                            rendersModule3 = rendersModule2;
                                                        }
                                                        if (!rendersModule3.isEnabled()) {
                                                            return;
                                                        }
                                                        f3 = (float)(System.nanoTime() % RendersModule.b(10897, 2617419814031004355L)) / 1.0E9f;
                                                        Window window = MinecraftClient.method_1551().method_22683();
                                                        f2 = window.method_4489();
                                                        f = window.method_4506();
                                                        rendersModule = rendersModule2;
                                                        if (bl) break block29;
                                                        if (!rendersModule.dualShaderMode.getValue()) break block30;
                                                        rendersModule$HandFx3 = rendersModule2.shader1.getValue();
                                                        rendersModule$HandFx = rendersModule2.shader2.getValue();
                                                        rendersModule$HandFx5 = rendersModule$HandFx3;
                                                        if (bl) break block31;
                                                        if (rendersModule$HandFx5 != RendersModule$HandFx.None) break block32;
                                                        rendersModule$HandFx5 = rendersModule$HandFx;
                                                    }
                                                    if (rendersModule$HandFx5 == RendersModule$HandFx.None) {
                                                        return;
                                                    }
                                                }
                                                fArray2 = new float[]{rendersModule2.blendMode.getValue().ordinal(), (float)rendersModule2.blendIntensity.getValue(), (float)rendersModule2.shader1Strength.getValue(), (float)rendersModule2.shader2Strength.getValue()};
                                                rendersModule$HandFx4 = rendersModule$HandFx3;
                                                if (bl) break block33;
                                                if (rendersModule$HandFx4 != RendersModule$HandFx.None) break block34;
                                                string2 = null;
                                                break block35;
                                            }
                                            rendersModule$HandFx4 = rendersModule$HandFx3;
                                        }
                                        string2 = rendersModule$HandFx4.name();
                                    }
                                    fArray = RendersModule.paramsFor(rendersModule2, rendersModule$HandFx3, f3, f2, f);
                                    rendersModule$HandFx2 = rendersModule$HandFx;
                                    if (bl) break block36;
                                    if (rendersModule$HandFx2 != RendersModule$HandFx.None) break block37;
                                    string = null;
                                    break block38;
                                }
                                rendersModule$HandFx2 = rendersModule$HandFx;
                            }
                            string = rendersModule$HandFx2.name();
                        }
                        HandShaderGpu.renderDual(string2, fArray, string, RendersModule.paramsFor(rendersModule2, rendersModule$HandFx, f3, f2, f), fArray2);
                        return;
                    }
                    rendersModule = rendersModule2;
                }
                if (bl) break block39;
                if (rendersModule.handShader.getValue() != RendersModule$HandFx.None) break block40;
                return;
            }
            rendersModule = rendersModule2;
        }
        float[] fArray = RendersModule.paramsFor(rendersModule, rendersModule2.handShader.getValue(), f3, f2, f);
        if (fArray == null) return;
        HandShaderGpu.render(rendersModule2.handShader.getValue().name(), fArray);
        return;
    }

    
    private static float[] paramsFor(RendersModule var0, RendersModule$HandFx var1_1, float var2_2, float var3_3, float var4_4) {
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
                v2[1] = (float)var0.chromaSpeed.getValue();
                v2[2] = (float)var0.chromaSaturation.getValue();
                v3 = v2;
                v2[3] = (float)var0.chromaBrightness.getValue();
                break;
            }
lbl19:

            case 1: {
                v4 = new float[12];
                v4[0] = var2_2 * (float)var0.gradientSpeed.getValue();
                v4[1] = (float)var0.solidStrength.getValue();
                v4[2] = 0.0f;
                v4[3] = 0.0f;
                v4[4] = RendersModule.r(var0.solidColor1.getRGB());
                v4[5] = RendersModule.g(var0.solidColor1.getRGB());
                v4[6] = RendersModule.b(var0.solidColor1.getRGB());
                v4[7] = 0.0f;
                v4[8] = RendersModule.r(var0.solidColor2.getRGB());
                v4[9] = RendersModule.g(var0.solidColor2.getRGB());
                v4[10] = RendersModule.b(var0.solidColor2.getRGB());
                v3 = v4;
                v4[11] = 0.0f;
                break;
            }
lbl35:

            case 3: {
                v5 = new float[20];
                v5[0] = var2_2 * (float)var0.balatroSpeed.getValue();
                v5[1] = var3_3;
                v5[2] = var4_4;
                v5[3] = 0.0f;
                v5[4] = RendersModule.r(var0.balatroColor1.getRGB());
                v5[5] = RendersModule.g(var0.balatroColor1.getRGB());
                v5[6] = RendersModule.b(var0.balatroColor1.getRGB());
                v5[7] = 0.0f;
                v5[8] = RendersModule.r(var0.balatroColor2.getRGB());
                v5[9] = RendersModule.g(var0.balatroColor2.getRGB());
                v5[10] = RendersModule.b(var0.balatroColor2.getRGB());
                v5[11] = 0.0f;
                v5[12] = RendersModule.r(var0.balatroColor3.getRGB());
                v5[13] = RendersModule.g(var0.balatroColor3.getRGB());
                v5[14] = RendersModule.b(var0.balatroColor3.getRGB());
                v5[15] = 0.0f;
                v5[16] = RendersModule.r(var0.balatroColor4.getRGB());
                v5[17] = RendersModule.g(var0.balatroColor4.getRGB());
                v5[18] = RendersModule.b(var0.balatroColor4.getRGB());
                v3 = v5;
                v5[19] = 0.0f;
                break;
            }
lbl59:

            case 4: {
                v6 = new float[8];
                v6[0] = var2_2 * (float)var0.smokeSpeed.getValue();
                v6[1] = var3_3;
                v6[2] = var4_4;
                v6[3] = (float)var0.smokeIntensity.getValue();
                v6[4] = RendersModule.r(var0.smokeColor.getRGB());
                v6[5] = RendersModule.g(var0.smokeColor.getRGB());
                v6[6] = RendersModule.b(var0.smokeColor.getRGB());
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
                v7[3] = (float)var0.stripesWidth.getValue();
                v7[4] = RendersModule.r(var0.stripesColor1.getRGB());
                v7[5] = RendersModule.g(var0.stripesColor1.getRGB());
                v7[6] = RendersModule.b(var0.stripesColor1.getRGB());
                v7[7] = 0.0f;
                v7[8] = RendersModule.r(var0.stripesColor2.getRGB());
                v7[9] = RendersModule.g(var0.stripesColor2.getRGB());
                v7[10] = RendersModule.b(var0.stripesColor2.getRGB());
                v7[11] = 0.0f;
                v7[12] = (float)var0.stripesSpeed.getValue();
                v7[13] = 0.0f;
                v7[14] = 0.0f;
                v3 = v7;
                v7[15] = 0.0f;
                break;
            }
lbl91:

            case 6: {
                v8 = new float[12];
                v9 = v8;
                v10 = v8;
                v11 = 0;
                v12 = var2_2;
                if (var5_5) // goto
                v9[v11] = v12;
                v10[1] = var3_3;
                v10[2] = var4_4;
                v10[3] = (float)var0.glowRadius.getValue();
                v10[4] = RendersModule.r(var0.glowColor.getRGB());
                v10[5] = RendersModule.g(var0.glowColor.getRGB());
                v10[6] = RendersModule.b(var0.glowColor.getRGB());
                v10[7] = 0.0f;
                v10[8] = (float)var0.glowPower.getValue();
                v10[9] = (float)var0.glowDispersion.getValue();
                v9 = v10;
                v10 = v10;
                v11 = 10;
                if (var0.glowFill.getValue()) {
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
                v15[3] = (float)var0.glassBlurSize.getValue();
                v16 = v15;
                v17 = v15;
                v18 = 4;
                v19 = (float)var0.glassQuality.getValue();
                if (var5_5) // goto
                v16[v18] = v19;
                v17[5] = (float)var0.glassDirection.getValue();
                v17[6] = (float)var0.glassRefraction.getValue();
                v17[7] = (float)var0.glassBrightness.getValue();
                v16 = v17;
                v17 = v17;
                v18 = 8;
                if (var0.glassChromatic.getValue()) {
                }
                v19 = 1.0f;
lbl156:

                v19 = 0.0f;
lbl158:

                v16[v18] = v19;
                v22 = v17;
                v23 = v17;
                v24 = 9;
                v25 = var0.glassDistortion.getValue() != false ? 1.0f : 0.0f;
                v22[v24] = v25;
                v27 = v23;
                v28 = v23;
                v29 = 10;
                v30 = var0.glassHideHand.getValue() != false ? 1.0f : 0.0f;
                v27[v29] = v30;
                v3 = v28;
                v28[11] = (float)var0.glassBackgroundBlur.getValue();
                break;
            }
lbl177:

            case 8: {
                v32 = new float[16];
                v32[0] = var2_2;
                v32[1] = var3_3;
                v33 = v32;
                v34 = v32;
                v35 = 2;
                v36 = var4_4;
                if (var5_5) // goto
                v33[v35] = v36;
                v34[3] = (float)var0.invertIntensity.getValue();
                v34[4] = RendersModule.r(var0.invertGlowColor.getRGB());
                v34[5] = RendersModule.g(var0.invertGlowColor.getRGB());
                v34[6] = RendersModule.b(var0.invertGlowColor.getRGB());
                v34[7] = 0.0f;
                v33 = v34;
                v34 = v34;
                v35 = 8;
                if (var0.invertHideHand.getValue()) {
                }
                v36 = 1.0f;
lbl206:

                v36 = 0.0f;
lbl208:

                v33[v35] = v36;
                v39 = v34;
                v40 = v34;
                v41 = 9;
                v42 = var0.invertHue.getValue() != false ? 1.0f : 0.0f;
                v39[v41] = v42;
                v44 = v40;
                v45 = v40;
                v46 = 10;
                v47 = var0.invertSaturation.getValue() != false ? 1.0f : 0.0f;
                v44[v46] = v47;
                v49 = v45;
                v50 = v45;
                v51 = 11;
                v52 = var0.invertBrightness.getValue() != false ? 1.0f : 0.0f;
                v49[v51] = v52;
                v50[12] = (float)var0.invertEdgeGlow.getValue();
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
                v54[3] = (float)var0.snowIntensity.getValue();
                v54[4] = RendersModule.r(var0.snowColor.getRGB());
                v54[5] = RendersModule.g(var0.snowColor.getRGB());
                v54[6] = RendersModule.b(var0.snowColor.getRGB());
                v54[7] = 0.0f;
                v54[8] = (float)var0.snowSpeed.getValue();
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

    private static Boolean lambda$shaderRows$1(EnumSetting enumSetting, RendersModule$PlayerFx rendersModule$PlayerFx) {
        boolean bl = enumSetting.getValue() == rendersModule$PlayerFx;
        return bl;
    }

    
    private Boolean lambda$gate$0(RendersModule$HandFx rendersModule$HandFx) {
        boolean bl;
        RendersModule rendersModule;
        block11: {
            block12: {
                block14: {
                    EnumSetting<RendersModule$HandFx> enumSetting;
                    block13: {
                        boolean bl2 = AmbienceModule.b;
                        rendersModule = this;
                        if (bl2) break block11;
                        if (!rendersModule.dualShaderMode.getValue()) break block12;
                        enumSetting = this.shader1;
                        if (bl2) break block13;
                        if (enumSetting.getValue() == rendersModule$HandFx) break block14;
                        enumSetting = this.shader2;
                    }
                    if (enumSetting.getValue() != rendersModule$HandFx) {
                        bl = false;
                        return bl;
                    }
                }
                bl = true;
                return bl;
            }
            rendersModule = this;
        }
        if (rendersModule.handShader.getValue() == rendersModule$HandFx) {
            bl = true;
            return bl;
        }
        bl = false;
        return bl;
    }

    
    static {
        block32: {
            block31: {
                block30: {
                    block29: {
                        block28: {
                            block27: {
                                var21 = new String[60];
                                var19_1 = 0;
                                var18_2 = "2\u0017\u0005Kq8/\u0002\u000f\u0012Ax\u00106\u0013\u0016Woyk\u0018\f\u0003Kn-f\u001e\u0011\u00112\u0017\u0005Kq8/\u0013\r\u001eCt-a\u0014\f\u0004\u000f3\u001e\u001bEh+`Q\u001c\u0018Hs+/B\u0006\"\u0017\u0016@y+\u00156\u0013\u0016Woym\u0010\u001c\u001cCn6z\u001f\u001bWFp,}\u00163\u001e\u001bEh+`Q\u001c\u0018Jh+n\u0002\u000bXHu>g\u0005\u000b\"\u0012\u0018Oyyl\u001e\u0013\u0018V\u000f\"\u0012\u0018Oyyf\u001f\u000b\u0012Jo0{\b\b\"\u0017\u0016@y+/@\u00118\u0011\u0001An-/\u0002\u001e\u0003Qn8{\u0018\u0010\u0019\n3\u0013\u0012Jxyb\u001e\u001b\u0012\f4\u0011\u0013\u0004\u007f+v\u0002\u000b\u0016Ho\u00108\u0011\u0001An-/\u0014\u001b\u0010A<>c\u001e\b\b\"\u0017\u0016@y+/C\u000f3\u001e\u001bEh+`Q\u001c\u0018Hs+/@\u001f9\u001e\u0019@<*g\u0010\u001b\u0012V<8a\u0015_\u0010Hs.f\u001f\u0018WTp8v\u0014\r\u0004\u000e\"\u0011\u0018S<0a\u0005\u001a\u0019Wu-v\u0006\"\u0017\u0016@y+\u00118\u0011\u0001An-/\u0016\u0013\u0018S<:`\u001d\u0010\u0005\u000f3\u0013\u0012Jxyf\u001f\u000b\u0012Jo0{\b\n6\u0013\u0018S<)`\u0006\u001a\u0005\n\"\u0011\u0018S<*\u007f\u0014\u001a\u0013\u0017\u0014\u0011\u0003Mh P\u001e\n\u0003Hu7j.\u0010\u0014Gp,k\u0014\u001b\n8\u0011\u0001An-/\u0019\n\u0012\r\"\u0010\u001bMxyl\u001e\u0013\u0018V<k\u000f6\u0013\u0016Woyl\u0019\r\u0018I}-f\u0012\u000b\"\u0012\u0018Oyy|\u0001\u001a\u0012@\u000e6\r\u0016@u<a\u0005_\u0004Ty<k\u00106\u0013\u0016Woym\u0003\u0016\u0010Lh7j\u0002\f\u00112\u0017\u0005Kq8/\u0002\u001e\u0003Qn8{\u0018\u0010\u0019\u000f3\u001e\u001bEh+`Q\u001c\u0018Hs+/C\r\"\u000b\u0005Ml<|Q\b\u001e@h1\r\"\u000b\u0005Ml<|Q\f\u0007Ay=\r3\u001e\u001bEh+`Q\f\u0007Ay=\u00106\u0013\u0016Woy}\u0014\u0019\u0005E\u007f-f\u001e\u0011\u00108\u0011\u0001An-/\u0019\u0016\u0013A<1n\u001f\u001b\u0007!\u0013\u0016]y+|\u0011\"\u0017\u0016@y+/C_\u0004Pn<a\u0016\u000b\u001f\n\"\u0011\u0018S<:`\u001d\u0010\u0005\u000f\"\u000b\u0005Ml<|Q\u001c\u0018Hs+/C\n6\u0013\u0018S<:`\u001d\u0010\u0005\u000f6\u0013\u0016Woym\u001d\n\u0005\u0004o0u\u0014\r\"\u0010\u001bMxyl\u001e\u0013\u0018V<h\u000f6\u0013\u0016Woyk\u0018\r\u0012Gh0`\u001f\u000e\"\u0010\u001bMxy|\u0005\r\u0012J{-g\u00108\u0011\u0001An-/\u0018\u0011\u0003Ar*f\u0005\u0006\u000f6\u0013\u0018S<=f\u0002\u000f\u0012Vo0`\u001f\u0007#\u001a\u0019@y+|\u000b5\n\u0016H<*g\u0010\u001b\u0012V\u0011\"\u0017\u0016@y+/@_\u0004Pn<a\u0016\u000b\u001f\u00135\u0016\u0004E~5jQ\u001e\u0005Is+/\u0016\u0013\u001eJh\r6\u0013\u0016Woy~\u0004\u001e\u001bMh \u000f\"\u000b\u0005Ml<|Q\u001c\u0018Hs+/@\u000e6\u0013\u0018S<?f\u001d\u0013WL}7k\u00118\u0011\u0001An-/\u0013\r\u001eCt-a\u0014\f\u0004\u000b9\u001e\u0019@<*g\u0010\u001b\u0012V\u000b6\u0013\u0018S<+n\u0015\u0016\u0002W";
                                var20_3 = "2\u0017\u0005Kq8/\u0002\u000f\u0012Ax\u00106\u0013\u0016Woyk\u0018\f\u0003Kn-f\u001e\u0011\u00112\u0017\u0005Kq8/\u0013\r\u001eCt-a\u0014\f\u0004\u000f3\u001e\u001bEh+`Q\u001c\u0018Hs+/B\u0006\"\u0017\u0016@y+\u00156\u0013\u0016Woym\u0010\u001c\u001cCn6z\u001f\u001bWFp,}\u00163\u001e\u001bEh+`Q\u001c\u0018Jh+n\u0002\u000bXHu>g\u0005\u000b\"\u0012\u0018Oyyl\u001e\u0013\u0018V\u000f\"\u0012\u0018Oyyf\u001f\u000b\u0012Jo0{\b\b\"\u0017\u0016@y+/@\u00118\u0011\u0001An-/\u0002\u001e\u0003Qn8{\u0018\u0010\u0019\n3\u0013\u0012Jxyb\u001e\u001b\u0012\f4\u0011\u0013\u0004\u007f+v\u0002\u000b\u0016Ho\u00108\u0011\u0001An-/\u0014\u001b\u0010A<>c\u001e\b\b\"\u0017\u0016@y+/C\u000f3\u001e\u001bEh+`Q\u001c\u0018Hs+/@\u001f9\u001e\u0019@<*g\u0010\u001b\u0012V<8a\u0015_\u0010Hs.f\u001f\u0018WTp8v\u0014\r\u0004\u000e\"\u0011\u0018S<0a\u0005\u001a\u0019Wu-v\u0006\"\u0017\u0016@y+\u00118\u0011\u0001An-/\u0016\u0013\u0018S<:`\u001d\u0010\u0005\u000f3\u0013\u0012Jxyf\u001f\u000b\u0012Jo0{\b\n6\u0013\u0018S<)`\u0006\u001a\u0005\n\"\u0011\u0018S<*\u007f\u0014\u001a\u0013\u0017\u0014\u0011\u0003Mh P\u001e\n\u0003Hu7j.\u0010\u0014Gp,k\u0014\u001b\n8\u0011\u0001An-/\u0019\n\u0012\r\"\u0010\u001bMxyl\u001e\u0013\u0018V<k\u000f6\u0013\u0016Woyl\u0019\r\u0018I}-f\u0012\u000b\"\u0012\u0018Oyy|\u0001\u001a\u0012@\u000e6\r\u0016@u<a\u0005_\u0004Ty<k\u00106\u0013\u0016Woym\u0003\u0016\u0010Lh7j\u0002\f\u00112\u0017\u0005Kq8/\u0002\u001e\u0003Qn8{\u0018\u0010\u0019\u000f3\u001e\u001bEh+`Q\u001c\u0018Hs+/C\r\"\u000b\u0005Ml<|Q\b\u001e@h1\r\"\u000b\u0005Ml<|Q\f\u0007Ay=\r3\u001e\u001bEh+`Q\f\u0007Ay=\u00106\u0013\u0016Woy}\u0014\u0019\u0005E\u007f-f\u001e\u0011\u00108\u0011\u0001An-/\u0019\u0016\u0013A<1n\u001f\u001b\u0007!\u0013\u0016]y+|\u0011\"\u0017\u0016@y+/C_\u0004Pn<a\u0016\u000b\u001f\n\"\u0011\u0018S<:`\u001d\u0010\u0005\u000f\"\u000b\u0005Ml<|Q\u001c\u0018Hs+/C\n6\u0013\u0018S<:`\u001d\u0010\u0005\u000f6\u0013\u0016Woym\u001d\n\u0005\u0004o0u\u0014\r\"\u0010\u001bMxyl\u001e\u0013\u0018V<h\u000f6\u0013\u0016Woyk\u0018\r\u0012Gh0`\u001f\u000e\"\u0010\u001bMxy|\u0005\r\u0012J{-g\u00108\u0011\u0001An-/\u0018\u0011\u0003Ar*f\u0005\u0006\u000f6\u0013\u0018S<=f\u0002\u000f\u0012Vo0`\u001f\u0007#\u001a\u0019@y+|\u000b5\n\u0016H<*g\u0010\u001b\u0012V\u0011\"\u0017\u0016@y+/@_\u0004Pn<a\u0016\u000b\u001f\u00135\u0016\u0004E~5jQ\u001e\u0005Is+/\u0016\u0013\u001eJh\r6\u0013\u0016Woy~\u0004\u001e\u001bMh \u000f\"\u000b\u0005Ml<|Q\u001c\u0018Hs+/@\u000e6\u0013\u0018S<?f\u001d\u0013WL}7k\u00118\u0011\u0001An-/\u0013\r\u001eCt-a\u0014\f\u0004\u000b9\u001e\u0019@<*g\u0010\u001b\u0012V\u000b6\u0013\u0018S<+n\u0015\u0016\u0002W".length();
                                var17_4 = 12;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block27;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "\u0010\n\u0005Kn8\u000f6\u0013\u0016Woyg\u0018\u001b\u0012\u0004t8a\u0015";
                                    var20_3 = "\u0010\n\u0005Kn8\u000f6\u0013\u0016Woyg\u0018\u001b\u0012\u0004t8a\u0015".length();
                                    var17_4 = 6;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block27;
                                        break;
                                    }
                                    break;
                                }
lbl26:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    break block28;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_7 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_7;
                                v9 = v6[v8];
                                switch (var22_7 % 7) {
                                    case 0: {
                                        v10 = 113;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 127;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 119;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 36;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 28;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 89;
                                        break;
                                    }
                                    default: {
                                        v10 = 15;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_7;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_7);
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
                        RendersModule.b = var21;
                        var8_8 = 3551677772167989966L;
                        var14_9 = new long[11];
                        var11_10 = 0;
                        var12_11 = "\u00cb\u0005\u00e0\u0082M\u00bd\r\u00e1;\u0097\u0018\u00a8'\u00ff@!k\u00d3\u00e6\u00ad\u00af\u0092*-BA\n\u00c5\u0013\u00e0\u001b\u0018\u008d\u0097\u00b3\u0098Lf\u00de'U1\u0097\u0092\u00ad\u00efkp\u0085\u00e5Ju\u00ec\u001f\u0003R\u00ab\u00b0\u0082\b\u00d4\u00fd\u00f2\u00f2u\u00e5nq\u0081\u008fV\u00e0";
                        var13_12 = "\u00cb\u0005\u00e0\u0082M\u00bd\r\u00e1;\u0097\u0018\u00a8'\u00ff@!k\u00d3\u00e6\u00ad\u00af\u0092*-BA\n\u00c5\u0013\u00e0\u001b\u0018\u008d\u0097\u00b3\u0098Lf\u00de'U1\u0097\u0092\u00ad\u00efkp\u0085\u00e5Ju\u00ec\u001f\u0003R\u00ab\u00b0\u0082\b\u00d4\u00fd\u00f2\u00f2u\u00e5nq\u0081\u008fV\u00e0".length();
                        var10_13 = 0;
                        while (true) {
                            var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                            v11 = var14_9;
                            v12 = var11_10++;
                            v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                            v14 = -1;
                            break block29;
                            break;
                        }
lbl89:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            var12_11 = "s\u0081.\u00c3O\u0091\u008b\u00ebIer\u0092\u00c3&\u008c6";
                            var13_12 = "s\u0081.\u00c3O\u0091\u008b\u00ebIer\u0092\u00c3&\u008c6".length();
                            var10_13 = 0;
                            while (true) {
                                var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                                v11 = var14_9;
                                v12 = var11_10++;
                                v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                                v14 = 0;
                                break block29;
                                break;
                            }
                            break;
                        }
lbl102:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            break block30;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_8;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl113:

                        continue;
                    }
                }
                RendersModule.d = var14_9;
                RendersModule.e = new Integer[11];
                var0_15 = 7756267259441745553L;
                var6_16 = new long[2];
                var3_17 = 0;
                var4_18 = "n\"\u000f\u0097%R\u0087\u00ccO\u00f1:f\u0089\u00c2\u008cR";
                var5_19 = "n\"\u000f\u0097%R\u0087\u00ccO\u00f1:f\u0089\u00c2\u008cR".length();
                var2_20 = 0;
                while (true) {
                    break block31;
                    break;
                }
lbl125:

                while (true) {
                    var6_16[v16] = (((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L) ^ var0_15;
                    if (var2_20 < var5_19) continue;
                    break block32;
                    break;
                }
            }
            var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
            v16 = var3_17++;
            while (true)
        }
        RendersModule.f = var6_16;
        RendersModule.g = new Long[2];
        var16_6 = RendersModule.b;
        RendersModule.OCCLUDED_OUTLINE = Identifier.method_60655((String)var16_6[58], (String)var16_6[23]);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4B71;
        if (e[n2] == null) {
            RendersModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x3853) & Short.MAX_VALUE;
        if (g[n2] == null) {
            RendersModule.g[n2] = f[n2] ^ l;
        }
        return g[n2];
    }
}
