package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.ambience.SkyFogState;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule$CloudStyle;
import dev.quelchi.aurora.module.modules.AmbienceModule$MoonLock;
import dev.quelchi.aurora.module.modules.AmbienceModule$SkyPreset;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import java.lang.ref.WeakReference;
import java.util.Objects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

public final class AmbienceModule
extends Module {
    private static AmbienceModule instance;
    private final BooleanSetting overworldSky;
    private final ColorSetting overworldSkyColor;
    private final BooleanSetting skyGradient;
    private final ColorSetting gradientColor;
    private final NumberSetting gradientHeight;
    private final NumberSetting gradientIntensity;
    private final BooleanSetting netherSky;
    private final ColorSetting netherColor;
    private final BooleanSetting endSky;
    private final ColorSetting endColor;
    private final BooleanSetting clouds;
    private final ColorSetting cloudColor;
    private final EnumSetting<AmbienceModule$SkyPreset> preset;
    private final BooleanSetting sunsetGlow;
    private final ColorSetting sunsetColor;
    private final NumberSetting sunsetStrength;
    private final NumberSetting sunsetHeight;
    private final BooleanSetting sunsetDuskOnly;
    private final BooleanSetting sunMoon;
    private final NumberSetting sunSize;
    private final NumberSetting moonSize;
    private final EnumSetting<AmbienceModule$MoonLock> moonPhase;
    private final BooleanSetting moonHalo;
    private final ColorSetting haloColor;
    private final EnumSetting<AmbienceModule$CloudStyle> cloudStyle;
    private final NumberSetting cloudHeight;
    private final NumberSetting cloudSpeed;
    private final NumberSetting cloudOpacity;
    private final BooleanSetting stars;
    private final NumberSetting starBrightness;
    private final NumberSetting starCount;
    private final NumberSetting starSize;
    private final NumberSetting starTwinkle;
    private final NumberSetting starTwinkleSpeed;
    private final ColorSetting starColor;
    private final BooleanSetting starsInDay;
    private final BooleanSetting aurora;
    private final ColorSetting auroraColor;
    private final ColorSetting auroraTipColor;
    private final NumberSetting auroraOpacity;
    private final NumberSetting auroraBands;
    private final NumberSetting auroraHeight;
    private final NumberSetting auroraSpread;
    private final NumberSetting auroraAltitude;
    private final NumberSetting auroraSpeed;
    private final BooleanSetting auroraNightOnly;
    private final BooleanSetting water;
    private final ColorSetting waterColor;
    private final BooleanSetting lava;
    private final ColorSetting lavaColor;
    private final BooleanSetting fire;
    private final ColorSetting fireColor;
    private final BooleanSetting leaves;
    private final ColorSetting leafColor;
    private final BooleanSetting grassBlocks;
    private final ColorSetting grassBlockColor;
    private final BooleanSetting shortGrass;
    private final ColorSetting shortGrassColor;
    private final BooleanSetting tallGrass;
    private final ColorSetting tallGrassColor;
    private int bakedTextureSig;
    private boolean dirty;
    private WeakReference<ClientWorld> lastWorld;
    private int lastChunkX;
    private int lastChunkZ;
    public static boolean b;
    private static final String[] d;
    private static final long[] e;
    private static final Integer[] f;

    public static AmbienceModule get() {
        return instance;
    }

    public AmbienceModule() {
        String[] stringArray = d;
        super(stringArray[40], stringArray[17], Category.RENDER, false);
        this.overworldSky = new BooleanSetting(stringArray[33], false);
        this.overworldSkyColor = new ColorSetting(stringArray[46], AmbienceModule.a(2614, 6393891102486636921L), false);
        this.skyGradient = new BooleanSetting(stringArray[10], false);
        this.gradientColor = new ColorSetting(stringArray[53], AmbienceModule.a(2444, 2324156846428793562L), false);
        this.gradientHeight = new NumberSetting(stringArray[54], 0.0, 128.0, 10.0, 1.0);
        this.gradientIntensity = new NumberSetting(stringArray[52], 1.0, 64.0, 16.0, 1.0);
        this.netherSky = new BooleanSetting(stringArray[50], false);
        this.netherColor = new ColorSetting(stringArray[4], AmbienceModule.a(22582, 7792148918361422719L), false);
        this.endSky = new BooleanSetting(stringArray[41], false);
        this.endColor = new ColorSetting(stringArray[3], AmbienceModule.a(7938, 2860461283168402498L), false);
        this.clouds = new BooleanSetting(stringArray[18], false);
        this.cloudColor = new ColorSetting(stringArray[5], -1, false);
        this.preset = new EnumSetting<AmbienceModule$SkyPreset>(stringArray[23], AmbienceModule$SkyPreset.Custom);
        this.sunsetGlow = new BooleanSetting(stringArray[28], false);
        this.sunsetColor = new ColorSetting(stringArray[47], -30132, false);
        this.sunsetStrength = new NumberSetting(stringArray[1], 5.0, 100.0, 60.0, 5.0);
        this.sunsetHeight = new NumberSetting(stringArray[36], 5.0, 60.0, 20.0, 1.0);
        this.sunsetDuskOnly = new BooleanSetting(stringArray[35], true);
        this.sunMoon = new BooleanSetting(stringArray[30], false);
        this.sunSize = new NumberSetting(stringArray[51], 25.0, 300.0, 100.0, 5.0);
        this.moonSize = new NumberSetting(stringArray[0], 25.0, 300.0, 100.0, 5.0);
        this.moonPhase = new EnumSetting<AmbienceModule$MoonLock>(stringArray[2], AmbienceModule$MoonLock.Auto);
        this.moonHalo = new BooleanSetting(stringArray[60], false);
        this.haloColor = new ColorSetting(stringArray[67], AmbienceModule.a(2095, 900925777257507703L), false);
        this.cloudStyle = new EnumSetting<AmbienceModule$CloudStyle>(stringArray[22], AmbienceModule$CloudStyle.Vanilla);
        this.cloudHeight = new NumberSetting(stringArray[6], 96.0, 320.0, 192.0, 4.0);
        this.cloudSpeed = new NumberSetting(stringArray[61], 0.0, 300.0, 100.0, 10.0);
        this.cloudOpacity = new NumberSetting(stringArray[24], 10.0, 100.0, 100.0, 5.0);
        this.stars = new BooleanSetting(stringArray[19], false);
        this.starBrightness = new NumberSetting(stringArray[15], 0.0, 100.0, 100.0, 5.0);
        this.starCount = new NumberSetting(stringArray[45], 100.0, 3000.0, 1200.0, 50.0);
        this.starSize = new NumberSetting(stringArray[59], 1.0, 30.0, 8.0, 1.0);
        this.starTwinkle = new NumberSetting(stringArray[68], 0.0, 100.0, 60.0, 5.0);
        this.starTwinkleSpeed = new NumberSetting(stringArray[16], 10.0, 300.0, 100.0, 10.0);
        this.starColor = new ColorSetting(stringArray[44], -1, false);
        this.starsInDay = new BooleanSetting(stringArray[34], false);
        this.aurora = new BooleanSetting(stringArray[29], false);
        this.auroraColor = new ColorSetting(stringArray[31], AmbienceModule.a(24299, 2750127283827670435L), false);
        this.auroraTipColor = new ColorSetting(stringArray[62], AmbienceModule.a(21768, 6627228000433501784L), false);
        this.auroraOpacity = new NumberSetting(stringArray[63], 5.0, 100.0, 55.0, 5.0);
        this.auroraBands = new NumberSetting(stringArray[37], 1.0, 10.0, 5.0, 1.0);
        this.auroraHeight = new NumberSetting(stringArray[56], 5.0, 80.0, 45.0, 5.0);
        this.auroraSpread = new NumberSetting(stringArray[11], 30.0, 180.0, 120.0, 10.0);
        this.auroraAltitude = new NumberSetting(stringArray[64], 0.0, 60.0, 12.0, 1.0);
        this.auroraSpeed = new NumberSetting(stringArray[7], 0.0, 300.0, 100.0, 10.0);
        this.auroraNightOnly = new BooleanSetting(stringArray[12], true);
        this.water = new BooleanSetting(stringArray[57], false);
        this.waterColor = new ColorSetting(stringArray[48], AmbienceModule.a(9849, 6695670925337658677L), false);
        this.lava = new BooleanSetting(stringArray[27], false);
        this.lavaColor = new ColorSetting(stringArray[20], AmbienceModule.a(18407, 6002843954660402362L), false);
        this.fire = new BooleanSetting(stringArray[58], false);
        this.fireColor = new ColorSetting(stringArray[13], AmbienceModule.a(24219, 3345629832111881668L), false);
        this.leaves = new BooleanSetting(stringArray[38], false);
        this.leafColor = new ColorSetting(stringArray[26], AmbienceModule.a(7579, 4334873746388008640L), false);
        this.grassBlocks = new BooleanSetting(stringArray[42], false);
        this.grassBlockColor = new ColorSetting(stringArray[66], AmbienceModule.a(1538, 51915015818814793L), false);
        this.shortGrass = new BooleanSetting(stringArray[69], false);
        this.shortGrassColor = new ColorSetting(stringArray[14], AmbienceModule.a(11657, 301831939134099149L), false);
        this.tallGrass = new BooleanSetting(stringArray[39], false);
        this.tallGrassColor = new ColorSetting(stringArray[8], AmbienceModule.a(1113, 2722215002282764059L), false);
        this.lastWorld = new WeakReference<Object>(null);
        this.lastChunkX = AmbienceModule.a(16258, 2969936787955358935L);
        this.lastChunkZ = AmbienceModule.a(27975, 8407317207028518406L);
        instance = this;
        this.addSettings(this.preset, this.overworldSky, this.overworldSkyColor, this.skyGradient, this.gradientColor, this.gradientHeight, this.gradientIntensity, this.sunsetGlow, this.sunsetColor, this.sunsetStrength, this.sunsetHeight, this.sunsetDuskOnly, this.sunMoon, this.sunSize, this.moonSize, this.moonPhase, this.moonHalo, this.haloColor, this.netherSky, this.netherColor, this.endSky, this.endColor, this.clouds, this.cloudColor, this.cloudStyle, this.cloudHeight, this.cloudSpeed, this.cloudOpacity, this.stars, this.starBrightness, this.starCount, this.starSize, this.starTwinkle, this.starTwinkleSpeed, this.starColor, this.starsInDay, this.aurora, this.auroraColor, this.auroraTipColor, this.auroraOpacity, this.auroraBands, this.auroraHeight, this.auroraSpread, this.auroraAltitude, this.auroraSpeed, this.auroraNightOnly, this.water, this.waterColor, this.lava, this.lavaColor, this.fire, this.fireColor, this.leaves, this.leafColor, this.grassBlocks, this.grassBlockColor, this.shortGrass, this.shortGrassColor, this.tallGrass, this.tallGrassColor);
        this.overworldSky.children(this.overworldSkyColor);
        this.skyGradient.children(this.gradientColor, this.gradientHeight, this.gradientIntensity);
        this.netherSky.children(this.netherColor);
        this.endSky.children(this.endColor);
        this.clouds.children(this.cloudColor, this.cloudStyle, this.cloudHeight, this.cloudSpeed, this.cloudOpacity);
        this.sunsetGlow.children(this.sunsetColor, this.sunsetStrength, this.sunsetHeight, this.sunsetDuskOnly);
        this.sunMoon.children(this.sunSize, this.moonSize, this.moonPhase, this.moonHalo, this.haloColor);
        this.stars.children(this.starBrightness, this.starCount, this.starSize, this.starTwinkle, this.starTwinkleSpeed, this.starColor, this.starsInDay);
        this.aurora.children(this.auroraColor, this.auroraTipColor, this.auroraOpacity, this.auroraBands, this.auroraHeight, this.auroraSpread, this.auroraAltitude, this.auroraSpeed, this.auroraNightOnly);
        this.water.children(this.waterColor);
        this.lava.children(this.lavaColor);
        this.fire.children(this.fireColor);
        this.leaves.children(this.leafColor);
        this.grassBlocks.children(this.grassBlockColor);
        this.shortGrass.children(this.shortGrassColor);
        this.tallGrass.children(this.tallGrassColor);
        this.preset.tab(stringArray[43]);
        this.overworldSky.tab(stringArray[65]);
        this.skyGradient.tab(stringArray[65]);
        this.netherSky.tab(stringArray[65]);
        this.endSky.tab(stringArray[65]);
        this.clouds.tab(stringArray[65]);
        this.sunsetGlow.tab(stringArray[65]);
        this.sunMoon.tab(stringArray[65]);
        this.stars.tab(stringArray[32]);
        this.aurora.tab(stringArray[9]);
        this.water.tab(stringArray[21]);
        this.lava.tab(stringArray[55]);
        this.fire.tab(stringArray[55]);
        this.leaves.tab(stringArray[49]);
        this.grassBlocks.tab(stringArray[25]);
        this.shortGrass.tab(stringArray[25]);
        this.tallGrass.tab(stringArray[25]);
        this.bakedTextureSig = this.textureSig(false);
    }

    public boolean overworldSky() {
        return this.overworldSky.getValue();
    }

    public int overworldSkyRgb() {
        return this.overworldSkyColor.getRGB();
    }

    public boolean skyGradient() {
        return this.skyGradient.getValue();
    }

    public int gradientRgb() {
        return this.gradientColor.getRGB();
    }

    public int gradientHeight() {
        return this.gradientHeight.getValueInt();
    }

    public int gradientIntensity() {
        return this.gradientIntensity.getValueInt();
    }

    public boolean netherSky() {
        return this.netherSky.getValue();
    }

    public int netherRgb() {
        return this.netherColor.getRGB();
    }

    public boolean endSky() {
        return this.endSky.getValue();
    }

    public int endRgb() {
        return this.endColor.getRGB();
    }

    public boolean clouds() {
        return this.clouds.getValue();
    }

    public int cloudRgb() {
        return this.cloudColor.getRGB();
    }

    public AmbienceModule$CloudStyle cloudStyle() {
        AmbienceModule$CloudStyle ambienceModule$CloudStyle;
        block6: {
            block5: {
                AmbienceModule ambienceModule;
                block4: {
                    boolean bl = b;
                    ambienceModule = this;
                    if (bl) break block4;
                    if (!ambienceModule.clouds.getValue()) break block5;
                    ambienceModule = this;
                }
                ambienceModule$CloudStyle = ambienceModule.cloudStyle.getValue();
                break block6;
            }
            ambienceModule$CloudStyle = AmbienceModule$CloudStyle.Vanilla;
        }
        return ambienceModule$CloudStyle;
    }

    public float cloudHeight() {
        return this.cloudHeight.getValueFloat();
    }

    public float cloudSpeed() {
        return this.cloudSpeed.getValueFloat() / 100.0f;
    }

    public float cloudOpacity() {
        return this.cloudOpacity.getValueFloat() / 100.0f;
    }

    public boolean sunsetGlow() {
        return this.sunsetGlow.getValue();
    }

    public int sunsetRgb() {
        return this.sunsetColor.getRGB();
    }

    public float sunsetStrength() {
        return this.sunsetStrength.getValueFloat() / 100.0f;
    }

    public float sunsetHeight() {
        return this.sunsetHeight.getValueFloat();
    }

    public boolean sunsetDuskOnly() {
        return this.sunsetDuskOnly.getValue();
    }

    public boolean sunMoon() {
        return this.sunMoon.getValue();
    }

    public float sunScale() {
        return this.sunSize.getValueFloat() / 100.0f;
    }

    public float moonScale() {
        return this.moonSize.getValueFloat() / 100.0f;
    }

    public AmbienceModule$MoonLock moonPhase() {
        return this.moonPhase.getValue();
    }

    public boolean moonHalo() {
        boolean bl;
        block8: {
            block7: {
                BooleanSetting booleanSetting;
                boolean bl2;
                block6: {
                    bl2 = b;
                    booleanSetting = this.sunMoon;
                    if (bl2) break block6;
                    if (!booleanSetting.getValue()) break block7;
                    booleanSetting = this.moonHalo;
                }
                bl = booleanSetting.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public int haloRgb() {
        return this.haloColor.getRGB();
    }

    public boolean stars() {
        return this.stars.getValue();
    }

    public float starBrightness() {
        return (float)this.starBrightness.getValue() / 100.0f;
    }

    public boolean starsInDay() {
        return this.starsInDay.getValue();
    }

    public int starCount() {
        return this.starCount.getValueInt();
    }

    public float starSize() {
        return (float)this.starSize.getValue();
    }

    public double starTwinkle() {
        return this.starTwinkle.getValue();
    }

    public double starTwinkleSpeed() {
        return this.starTwinkleSpeed.getValue();
    }

    public int starRgb() {
        return this.starColor.getRGB();
    }

    public boolean aurora() {
        return this.aurora.getValue();
    }

    public int auroraRgb() {
        return this.auroraColor.getRGB();
    }

    public int auroraTipRgb() {
        return this.auroraTipColor.getRGB();
    }

    public double auroraOpacity() {
        return this.auroraOpacity.getValue();
    }

    public int auroraBands() {
        return this.auroraBands.getValueInt();
    }

    public float auroraHeight() {
        return (float)this.auroraHeight.getValue();
    }

    public double auroraSpread() {
        return this.auroraSpread.getValue();
    }

    public double auroraAltitude() {
        return this.auroraAltitude.getValue();
    }

    public double auroraSpeed() {
        return this.auroraSpeed.getValue();
    }

    public boolean auroraNightOnly() {
        return this.auroraNightOnly.getValue();
    }

    public boolean water() {
        return this.water.getValue();
    }

    public int waterRgb() {
        return this.waterColor.getRGB();
    }

    public boolean lava() {
        return this.lava.getValue();
    }

    public int lavaRgb() {
        return this.lavaColor.getRGB();
    }

    public boolean fire() {
        return this.fire.getValue();
    }

    public int fireRgb() {
        return this.fireColor.getRGB();
    }

    public boolean leaves() {
        return this.leaves.getValue();
    }

    public int leafRgb() {
        return this.leafColor.getRGB();
    }

    public boolean grassBlocks() {
        return this.grassBlocks.getValue();
    }

    public int grassBlockRgb() {
        return this.grassBlockColor.getRGB();
    }

    public boolean shortGrass() {
        return this.shortGrass.getValue();
    }

    public int shortGrassRgb() {
        return this.shortGrassColor.getRGB();
    }

    public boolean tallGrass() {
        return this.tallGrass.getValue();
    }

    public int tallGrassRgb() {
        return this.tallGrassColor.getRGB();
    }

    @Override
    public void onEnable() {
        this.refreshColors();
        this.applyTextureCheck();
    }

    @Override
    public void onDisable() {
        SkyFogState.reset();
        this.refreshColors();
        this.applyTextureCheck();
    }

    @Override
    public void onSettingsChanged() {
        block4: {
            AmbienceModule ambienceModule;
            block5: {
                boolean bl = b;
                ambienceModule = this;
                if (bl) break block4;
                if (ambienceModule.preset.getValue() == AmbienceModule$SkyPreset.Custom) break block5;
                this.applyPreset(this.preset.getValue());
                this.preset.setValue(AmbienceModule$SkyPreset.Custom);
            }
            this.refreshColors();
            ambienceModule = this;
        }
        ambienceModule.dirty = true;
    }

    
    private void applyPreset(AmbienceModule$SkyPreset ambienceModule$SkyPreset) {
        boolean bl = b;
        switch (ambienceModule$SkyPreset.ordinal()) {
            case 1: {
                this.sky(AmbienceModule.a(28419, 7322309389764807750L), AmbienceModule.a(11135, 5640702977969850421L));
                this.glow(-30132, 70, true);
                this.cloudColor.setARGB(-13904);
                this.stars.setValue(true);
                this.moonHalo.setValue(true);
                this.sunMoon.setValue(true);
                if (!bl) break;
            }
            case 2: {
                this.sky(AmbienceModule.a(7304, 8943940225356906457L), 0);
                this.sunsetGlow.setValue(false);
                this.cloudColor.setARGB(AmbienceModule.a(18992, 4427505313822566755L));
                this.stars.setValue(true);
                this.starCount.setValue(1800.0);
                this.sunMoon.setValue(true);
                this.moonHalo.setValue(true);
                if (!bl) break;
            }
            case 3: {
                this.sky(AmbienceModule.a(8527, 1687503538867831305L), AmbienceModule.a(3586, 198170204377394511L));
                this.glow(AmbienceModule.a(2929, 1996893390099130406L), 80, false);
                this.cloudColor.setARGB(AmbienceModule.a(26990, 6038894258441734706L));
                this.stars.setValue(true);
                this.aurora.setValue(true);
                this.auroraColor.setARGB(AmbienceModule.a(15833, 630407615724035742L));
                this.auroraTipColor.setARGB(AmbienceModule.a(2929, 1996893390099130406L));
                if (!bl) break;
            }
            case 4: {
                this.sky(AmbienceModule.a(15774, 6683775486857158340L), AmbienceModule.a(16148, 3227927652089076800L));
                this.glow(AmbienceModule.a(5316, 1142850438983792541L), 50, true);
                this.cloudColor.setARGB(AmbienceModule.a(1408, 3188105219395659459L));
                this.stars.setValue(true);
                this.aurora.setValue(true);
                this.auroraColor.setARGB(AmbienceModule.a(11696, 3402927702657896190L));
                this.auroraTipColor.setARGB(AmbienceModule.a(313, 8844912480769253995L));
                break;
            }
        }
        this.clouds.setValue(true);
    }

    private void sky(int n, int n2) {
        boolean bl;
        BooleanSetting booleanSetting;
        block7: {
            block8: {
                boolean bl2 = b;
                booleanSetting = this.overworldSky;
                bl = true;
                if (bl2) break block7;
                booleanSetting.setValue(bl);
                this.overworldSkyColor.setARGB(n);
                booleanSetting = this.skyGradient;
                if (n2 == 0) break block8;
                bl = true;
                break block7;
            }
            bl = false;
        }
        booleanSetting.setValue(bl);
        if (n2 != 0) {
            this.gradientColor.setARGB(n2);
        }
    }

    private void glow(int n, int n2, boolean bl) {
        this.sunsetGlow.setValue(true);
        this.sunsetColor.setARGB(n);
        this.sunsetStrength.setValue(n2);
        this.sunsetDuskOnly.setValue(bl);
    }

    
    @Override
    public void onTick() {
        block17: {
            int n;
            int n2;
            block22: {
                block21: {
                    int n3;
                    block20: {
                        AmbienceModule ambienceModule;
                        boolean bl;
                        block18: {
                            block19: {
                                block15: {
                                    block16: {
                                        block13: {
                                            AmbienceModule ambienceModule2;
                                            block14: {
                                                bl = b;
                                                ambienceModule2 = this;
                                                if (bl) break block13;
                                                if (ambienceModule2.mc.field_1687 == null) break block14;
                                                ambienceModule = this;
                                                if (bl) break block15;
                                                if (ambienceModule.mc.field_1724 != null) break block16;
                                            }
                                            this.lastWorld = new WeakReference<Object>(null);
                                            ambienceModule2 = this;
                                        }
                                        ambienceModule2.lastChunkX = this.lastChunkZ = AmbienceModule.a(27975, 8407317207028518406L);
                                        if (!bl) break block17;
                                    }
                                    ambienceModule = this;
                                }
                                if (bl) break block18;
                                if (ambienceModule.mc.field_1687 == this.lastWorld.get()) break block19;
                                this.lastWorld = new WeakReference<ClientWorld>(this.mc.field_1687);
                                this.lastChunkX = this.mc.field_1724.method_31477() >> 4;
                                this.lastChunkZ = this.mc.field_1724.method_31479() >> 4;
                                this.refreshColors();
                                if (!bl) break block17;
                            }
                            ambienceModule = this;
                        }
                        n2 = ambienceModule.mc.field_1724.method_31477() >> 4;
                        n = this.mc.field_1724.method_31479() >> 4;
                        n3 = n2;
                        if (bl) break block20;
                        if (Math.abs(n3 - this.lastChunkX) > 128) break block21;
                        n3 = Math.abs(n - this.lastChunkZ);
                    }
                    if (n3 <= 128) break block22;
                }
                this.refreshColors();
            }
            this.lastChunkX = n2;
            this.lastChunkZ = n;
        }
    }

    private void refreshColors() {
        block11: {
            AmbienceModule ambienceModule;
            block10: {
                MinecraftClient minecraftClient;
                block8: {
                    boolean bl;
                    block9: {
                        bl = b;
                        minecraftClient = this.mc;
                        if (bl) break block8;
                        if (minecraftClient.field_1687 != null) break block9;
                        return;
                    }
                    this.mc.field_1687.method_23784();
                    ambienceModule = this;
                    if (bl) break block10;
                    minecraftClient = ambienceModule.mc;
                }
                if (minecraftClient.field_1769 == null) break block11;
                ambienceModule = this;
            }
            ambienceModule.mc.field_1769.method_3279();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void applyTextureCheck() {
        AmbienceModule ambienceModule;
        block15: {
            block16: {
                boolean bl;
                block14: {
                    block12: {
                        int n;
                        block13: {
                            this.dirty = false;
                            bl = b;
                            n = this.textureSig(this.isEnabled());
                            if (bl) break block12;
                            if (n != this.bakedTextureSig) break block13;
                            return;
                        }
                        ambienceModule = this;
                        if (bl) break block14;
                        ambienceModule.bakedTextureSig = n;
                    }
                    if (this.startupPhase()) return;
                    ambienceModule = this;
                }
                if (bl) break block15;
                if (ambienceModule.mc.method_18506() == null) break block16;
                return;
            }
            ambienceModule = this;
        }
        ambienceModule.mc.method_1521();
    }

    private boolean startupPhase() {
        boolean bl;
        block13: {
            block11: {
                AmbienceModule ambienceModule;
                block12: {
                    boolean bl2;
                    block10: {
                        bl2 = b;
                        ambienceModule = this;
                        if (bl2) break block10;
                        if (ambienceModule.mc.field_1687 != null) break block11;
                        ambienceModule = this;
                    }
                    if (bl2) break block12;
                    if (ambienceModule.mc.field_1755 != null) break block11;
                    ambienceModule = this;
                }
                if (ambienceModule.mc.method_18506() != null) break block11;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    
    private int textureSig(boolean var1_1) {
        block50: {
            block49: {
                block48: {
                    block47: {
                        block46: {
                            block45: {
                                block44: {
                                    block43: {
                                        block42: {
                                            var2_2 = AmbienceModule.b;
                                            v0 = new Object[5];
                                            v1 = v0;
                                            v2 = v0;
                                            v3 = 0;
                                            v4 = var1_1;
                                            if (var2_2) break block42;
                                            if (v4) {
                                            }
                                            v6 = this;
                                            if (!var2_2) {
                                            }
                                            v4 = v6.grassBlocks.getValue();
                                        }
                                        if (v4) {
                                            v6 = this;
lbl29:

                                            v9 = v6.grassBlockColor.getRGB();
                                        } else lbl_neg_1000:

                                        {
                                            v9 = -1;
                                        }
                                        v1[v3] = v9;
                                        v10 = v2;
                                        v11 = v2;
                                        v12 = 1;
                                        v13 = var1_1;
                                        if (var2_2) break block43;
                                        if (!v13) break block44;
                                        v13 = this.shortGrass.getValue();
                                    }
                                    if (var2_2) break block45;
                                    if (!v13) break block44;
                                    v13 = true;
                                    break block45;
                                }
                                v13 = false;
                            }
                            v10[v12] = v13;
                            v17 = v11;
                            v18 = v11;
                            v19 = 2;
                            v20 = var1_1;
                            if (var2_2) break block46;
                            if (!v20) break block47;
                            v20 = this.tallGrass.getValue();
                        }
                        if (var2_2) break block48;
                        if (!v20) break block47;
                        v20 = true;
                        break block48;
                    }
                    v20 = false;
                }
                v17[v19] = v20;
                v24 = v18;
                v25 = v18;
                v26 = 3;
                v27 = var1_1;
                if (var2_2) break block49;
                if (v27) {
                }
                v29 = this;
                if (!var2_2) {
                }
                v27 = v29.lava.getValue();
            }
            if (v27) {
                v29 = this;
lbl115:

                v32 = v29.lavaColor.getRGB();
            } else lbl_neg_1000:

            {
                v32 = -1;
            }
            v24[v26] = v32;
            v33 = v25;
            v34 = v25;
            v35 = 4;
            v36 = var1_1;
            if (var2_2) break block50;
            if (v36) {
            }
            v38 = this;
            if (!var2_2) {
            }
            v36 = v38.fire.getValue();
        }
        if (v36) {
            v38 = this;
lbl145:

            v41 = v38.fireColor.getRGB();
        } else lbl_neg_1000:

        {
            v41 = -1;
        }
        v33[v35] = v41;
        return Objects.hash(v34);
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[70];
                        var11_1 = 0;
                        var10_2 = "\nmj\u0004\u001d\fm=g\r\u0000nj\u001d\u001d\fp5gk\rI7\n\nmj\u0004\u001d\u000fl&q`\t\u0002laJ~0h(p\f\tgq\u0002X-$\u0004mi\u0005O\u000b\u0004nj\u001fY\u007fG(nj\u0018\f\u0004nj\u001fY\u007fL\"kb\u0002I\f\u0006ww\u0005O>$\u0014r`\u000fY\u0010\u0013ci\u0006\u001d\u0018v&qvJ~0h(p\u0007\u0002dc\u000f^+w\f\u0014i|Jz-e#k`\u0004I\r\u0006ww\u0005O>$\u0014rw\u000f\\;\n\tkb\u0002I\u007fK)n|\n\u0001kw\u000f\u001d\u001ck+mw\u0011\u0014jj\u0018I\u007fC5cv\u0019\u001d\u001ck+mw\u000f\u0014vd\u0018\u001d\u001dv.em\u001eS:w4\r\u0013ul\u0004V3agQu\u000fX;*\u0015gf\u0005Q0vgqn\u0013\u0011\u007fs&v`\u0018\u0011\u007fh&tdF\u001d9m5g%\u000bS;$!mi\u0003\\8a\u0006\u0004nj\u001fY,\u0005\u0014vd\u0018N\n\u000bcs\u000b\u001d\u001ck+mw\u0005\u0010mw\u0006Y\u000b\u0004nj\u001fY\u007fW3{i\u000f\u0006\u0017p`\u0019X+\r\u0004nj\u001fY\u007fK7cf\u0003I&\u0007\u0001mi\u0003\\8a\n\u000bgd\f\u001d\u001ck+mw\u0004\u000bcs\u000b\u000b\u0014wk\u0019X+$\u0000nj\u001d\u0006\u0006ww\u0005O>\n\u0014wkJ\u001b\u007fI(mk\f\u0006ww\u0005O>$\u0004mi\u0005O\u0007\u0002dc\u000f^+w\r\bt`\u0018J0v+f%9V&\u0011\u0014vd\u0018N\u007fM)\"A\u000bD3m jq\u0015\bli\u0013\u001d\u001epgFp\u0019V\u007fE)f%.\\(j\u000b\u0000nj\u001d\u001d\u0017a.em\u001e\f\u0006ww\u0005O>$\u0005ck\u000eN\u0006\u000bgd\u001cX,\n\u0013ci\u0006\u001d\u0018v&qv\b\u0006og\u0003X1g\"\u0007\u0002laJn4}\f\u0000pd\u0019N\u007fF+mf\u0001N\u0003\u0014i|\n\u0014vd\u0018\u001d\u001ck+mw\n\u0014vd\u0018\u001d\u001ck2lq\t\u0014i|J~0h(p\n\u0000nj\u001d\u001d\u001ck+mw\u000b\u0010cq\u000fO\u007fG(nj\u0018\u0007\u0001mi\u0003\\8a\n\tgq\u0002X-$\u0014i|\b\u0014wkJn6~\"\u0012\u0000pd\u000eT:j3\"L\u0004I:j4kq\u0013\u000e\u0000pd\u000eT:j3\"F\u0005Q0v\u000f\u0000pd\u000eT:j3\"M\u000fT8l3\u0005\u0010mw\u0006Y\r\u0006ww\u0005O>$\u000fgl\rU+\u0005\u0010cq\u000fO\u0004\u0001kw\u000f\t\u0014vd\u0018\u001d\fm=g\t\nmj\u0004\u001d\u0017e+m\u000b\u0004nj\u001fY\u007fW7g`\u000e\u0010\u0006ww\u0005O>$\u0013kuJ~0h(p\u000e\u0006ww\u0005O>$\brd\tT+}\u000f\u0006ww\u0005O>$\u0006nq\u0003I*`\"\u0003\u0014i|\u0011\u0000pd\u0019N\u007fF+mf\u0001\u001d\u001ck+mw\n\u000fci\u0005\u001d\u001ck+mw";
                        var12_3 = "\nmj\u0004\u001d\fm=g\r\u0000nj\u001d\u001d\fp5gk\rI7\n\nmj\u0004\u001d\u000fl&q`\t\u0002laJ~0h(p\f\tgq\u0002X-$\u0004mi\u0005O\u000b\u0004nj\u001fY\u007fG(nj\u0018\f\u0004nj\u001fY\u007fL\"kb\u0002I\f\u0006ww\u0005O>$\u0014r`\u000fY\u0010\u0013ci\u0006\u001d\u0018v&qvJ~0h(p\u0007\u0002dc\u000f^+w\f\u0014i|Jz-e#k`\u0004I\r\u0006ww\u0005O>$\u0014rw\u000f\\;\n\tkb\u0002I\u007fK)n|\n\u0001kw\u000f\u001d\u001ck+mw\u0011\u0014jj\u0018I\u007fC5cv\u0019\u001d\u001ck+mw\u000f\u0014vd\u0018\u001d\u001dv.em\u001eS:w4\r\u0013ul\u0004V3agQu\u000fX;*\u0015gf\u0005Q0vgqn\u0013\u0011\u007fs&v`\u0018\u0011\u007fh&tdF\u001d9m5g%\u000bS;$!mi\u0003\\8a\u0006\u0004nj\u001fY,\u0005\u0014vd\u0018N\n\u000bcs\u000b\u001d\u001ck+mw\u0005\u0010mw\u0006Y\u000b\u0004nj\u001fY\u007fW3{i\u000f\u0006\u0017p`\u0019X+\r\u0004nj\u001fY\u007fK7cf\u0003I&\u0007\u0001mi\u0003\\8a\n\u000bgd\f\u001d\u001ck+mw\u0004\u000bcs\u000b\u000b\u0014wk\u0019X+$\u0000nj\u001d\u0006\u0006ww\u0005O>\n\u0014wkJ\u001b\u007fI(mk\f\u0006ww\u0005O>$\u0004mi\u0005O\u0007\u0002dc\u000f^+w\r\bt`\u0018J0v+f%9V&\u0011\u0014vd\u0018N\u007fM)\"A\u000bD3m jq\u0015\bli\u0013\u001d\u001epgFp\u0019V\u007fE)f%.\\(j\u000b\u0000nj\u001d\u001d\u0017a.em\u001e\f\u0006ww\u0005O>$\u0005ck\u000eN\u0006\u000bgd\u001cX,\n\u0013ci\u0006\u001d\u0018v&qv\b\u0006og\u0003X1g\"\u0007\u0002laJn4}\f\u0000pd\u0019N\u007fF+mf\u0001N\u0003\u0014i|\n\u0014vd\u0018\u001d\u001ck+mw\n\u0014vd\u0018\u001d\u001ck2lq\t\u0014i|J~0h(p\n\u0000nj\u001d\u001d\u001ck+mw\u000b\u0010cq\u000fO\u007fG(nj\u0018\u0007\u0001mi\u0003\\8a\n\tgq\u0002X-$\u0014i|\b\u0014wkJn6~\"\u0012\u0000pd\u000eT:j3\"L\u0004I:j4kq\u0013\u000e\u0000pd\u000eT:j3\"F\u0005Q0v\u000f\u0000pd\u000eT:j3\"M\u000fT8l3\u0005\u0010mw\u0006Y\r\u0006ww\u0005O>$\u000fgl\rU+\u0005\u0010cq\u000fO\u0004\u0001kw\u000f\t\u0014vd\u0018\u001d\fm=g\t\nmj\u0004\u001d\u0017e+m\u000b\u0004nj\u001fY\u007fW7g`\u000e\u0010\u0006ww\u0005O>$\u0013kuJ~0h(p\u000e\u0006ww\u0005O>$\brd\tT+}\u000f\u0006ww\u0005O>$\u0006nq\u0003I*`\"\u0003\u0014i|\u0011\u0000pd\u0019N\u007fF+mf\u0001\u001d\u001ck+mw\n\u000fci\u0005\u001d\u001ck+mw".length();
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
                            var10_2 = "\u0013ul\u0004V3a\u000b\u0014jj\u0018I\u007fC5cv\u0019";
                            var12_3 = "\u0013ul\u0004V3a\u000b\u0014jj\u0018I\u007fC5cv\u0019".length();
                            var9_4 = 7;
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
                                v10 = 71;
                                break;
                            }
                            case 1: {
                                v10 = 2;
                                break;
                            }
                            case 2: {
                                v10 = 5;
                                break;
                            }
                            case 3: {
                                v10 = 106;
                                break;
                            }
                            case 4: {
                                v10 = 61;
                                break;
                            }
                            case 5: {
                                v10 = 95;
                                break;
                            }
                            default: {
                                v10 = 4;
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
                AmbienceModule.d = var13;
                var0_7 = 7935480178508477818L;
                var6_8 = new long[31];
                var3_9 = 0;
                var4_10 = "0[\u00cd\u0092\u009c\u00d0{|\u00a4\u00a9DB\u00e0<\u0011\u001c\u001ds5\"\u0011w\u00e9F\u00aaTv\u0004p\u001c\u00b9N\u0001\u008a\u001a\u00cf\u0015}\u00d6b\u0081\u0089\u00e0\u0082\u00fc\u00a5\u0090\u0098\u00b1t\u00a09\u00bb\u00c8\u00ce\u001b\u0089w\u00fbDJb89EeE N\u0099\u00ce\u0005f7\u00c8e\u00ca\u00b5 i\u00b0o\u00ca\u0015\u00d9\u00d4\u00adj;\u00f9nPP\u00b8G\u0016(RO\u001bi\"\u00a7\u0091\u00eb\n\u009b\u00f3h\u00b0F\u00ab\u00e8\u00dfG\u00ed\u008da;\u00fcc+r.\u0004_\u00d34w\u000fS\u00b2-\u0094\u001a\u0093\u0015\u00de\u001as\u00ae\u0000\u0098\u00ddC\u00ed\u0080\u00ef\u00aa\u0090\u009ey\u00f5-\u0005\u008f\u00a0\u00f7L\u00ee\u00e0\u00c3^G\u00d9W)\u00ad\u00cc,\u00cb\u008f\u00b2\u00ae]\u00c5\u0099\u00fb+\u00fd\u0006\u00f6\u00a2\u00f8\u00e1\u00ab\u00e4s\u00d4HG_';\u00d8\u00f3\u001a\u009ea\u0018wJ\u00d3t\u00cf\u0084}\u00f2;\u00f9\u0082\u001b\u00d2\u00f1\u00be\u00a2\u00e4My\u001f\u00f1/\u00ecV\u0093\u00c5\u0087\u00cf\u00e2{\u008b\u00c0";
                var5_11 = "0[\u00cd\u0092\u009c\u00d0{|\u00a4\u00a9DB\u00e0<\u0011\u001c\u001ds5\"\u0011w\u00e9F\u00aaTv\u0004p\u001c\u00b9N\u0001\u008a\u001a\u00cf\u0015}\u00d6b\u0081\u0089\u00e0\u0082\u00fc\u00a5\u0090\u0098\u00b1t\u00a09\u00bb\u00c8\u00ce\u001b\u0089w\u00fbDJb89EeE N\u0099\u00ce\u0005f7\u00c8e\u00ca\u00b5 i\u00b0o\u00ca\u0015\u00d9\u00d4\u00adj;\u00f9nPP\u00b8G\u0016(RO\u001bi\"\u00a7\u0091\u00eb\n\u009b\u00f3h\u00b0F\u00ab\u00e8\u00dfG\u00ed\u008da;\u00fcc+r.\u0004_\u00d34w\u000fS\u00b2-\u0094\u001a\u0093\u0015\u00de\u001as\u00ae\u0000\u0098\u00ddC\u00ed\u0080\u00ef\u00aa\u0090\u009ey\u00f5-\u0005\u008f\u00a0\u00f7L\u00ee\u00e0\u00c3^G\u00d9W)\u00ad\u00cc,\u00cb\u008f\u00b2\u00ae]\u00c5\u0099\u00fb+\u00fd\u0006\u00f6\u00a2\u00f8\u00e1\u00ab\u00e4s\u00d4HG_';\u00d8\u00f3\u001a\u009ea\u0018wJ\u00d3t\u00cf\u0084}\u00f2;\u00f9\u0082\u001b\u00d2\u00f1\u00be\u00a2\u00e4My\u001f\u00f1/\u00ecV\u0093\u00c5\u0087\u00cf\u00e2{\u008b\u00c0".length();
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
                    var4_10 = "N`\u008e@s:$\u009eZJ_=i#\u00b7\u00be";
                    var5_11 = "N`\u008e@s:$\u009eZJ_=i#\u00b7\u00be".length();
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
        AmbienceModule.e = var6_8;
        AmbienceModule.f = new Integer[31];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2341;
        if (f[n2] == null) {
            AmbienceModule.f[n2] = (int)(e[n2] ^ l);
        }
        return f[n2];
    }
}
