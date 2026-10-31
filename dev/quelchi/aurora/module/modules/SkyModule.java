package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.SkyModule$Look;
import dev.quelchi.aurora.module.modules.SkyModule$Style;
import dev.quelchi.aurora.module.modules.SkyModule$Uniform;
import dev.quelchi.aurora.module.setting.ActionSetting;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ChoiceSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import dev.quelchi.aurora.module.setting.StringSetting;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class SkyModule
extends Module {
    private static SkyModule instance;
    private final SkyModule$Look painterly;
    private final SkyModule$Look fantasy;
    private final Map<SkyModule$Style, SkyModule$Look> looks;
    private final EnumSetting<SkyModule$Style> style;
    private final ChoiceSetting preset;
    private final StringSetting presetName;
    private final ActionSetting save;
    private final ActionSetting delete;
    private final ActionSetting paste;
    private final NumberSetting bands;
    private final NumberSetting grain;
    private final NumberSetting sunSize;
    private final NumberSetting moonSize;
    private final BooleanSetting clouds;
    private final NumberSetting coverage;
    private final NumberSetting wind;
    private final NumberSetting cloudDetail;
    private final ColorSetting cloudLight;
    private final ColorSetting cloudShadow;
    private final BooleanSetting towers;
    private final NumberSetting towerHeight;
    private final BooleanSetting stars;
    private final NumberSetting starDensity;
    private final NumberSetting twinkle;
    private final BooleanSetting river;
    private final NumberSetting riverStrength;
    private final BooleanSetting aurora;
    private final NumberSetting auroraStrength;
    private final ColorSetting auroraLow;
    private final ColorSetting auroraHigh;
    private final BooleanSetting meteors;
    private final NumberSetting meteorRate;
    private final BooleanSetting atmo;
    private final NumberSetting haze;
    private final NumberSetting dayTint;
    private final NumberSetting exposure;
    private final NumberSetting saturation;
    private final NumberSetting horizonGlow;
    private final NumberSetting sunDisc;
    private final ColorSetting sunlight;
    private final ColorSetting nightZen;
    private final ColorSetting nightHor;
    private final BooleanSetting planet;
    private final NumberSetting planetAz;
    private final NumberSetting planetEl;
    private final NumberSetting planetSize;
    private final NumberSetting planetBright;
    private final NumberSetting banding;
    private final ColorSetting planetLight;
    private final ColorSetting planetDark;
    private final ColorSetting planetAir;
    private final NumberSetting airGlow;
    private final NumberSetting lightPhase;
    private final NumberSetting lightFrom;
    private final BooleanSetting rings;
    private final NumberSetting ringTilt;
    private final NumberSetting ringRoll;
    private final NumberSetting ringInner;
    private final NumberSetting ringOuter;
    private final NumberSetting ringOpacity;
    private final ColorSetting ringColor;
    private final BooleanSetting moon;
    private final NumberSetting moonAz;
    private final NumberSetting moonEl;
    private final NumberSetting moonRadius;
    private final ColorSetting moonColor;
    private final BooleanSetting nebula;
    private final NumberSetting nebBright;
    private final NumberSetting nebWidth;
    private final NumberSetting nebDust;
    private final NumberSetting nebYaw;
    private final NumberSetting nebPitch;
    private final ColorSetting nebGlow;
    private final ColorSetting nebFilament;
    private final ColorSetting nebCore;
    private final BooleanSetting starfield;
    private final NumberSetting fieldDensity;
    private final NumberSetting fieldBright;
    private final NumberSetting shimmer;
    private final BooleanSetting lights;
    private final NumberSetting lightsStrength;
    private final NumberSetting storm;
    private final NumberSetting spread;
    private final NumberSetting lightsDir;
    private final NumberSetting lightsSpeed;
    private final ColorSetting lightsMain;
    private final ColorSetting lightsTop;
    private final ColorSetting lightsFringe;
    private final BooleanSetting shooting;
    private final NumberSetting shootingRate;
    private final NumberSetting resolution;
    private SkyModule$Style shown;
    private String flash;
    private String flashFor;
    private long flashUntil;
    private static final String[] b;
    private static final long d;
    private static final long e;

    public static SkyModule get() {
        return instance;
    }

    public SkyModule() {
        boolean bl;
        block15: {
            Object object2;
            String[] stringArray = b;
            super(stringArray[45], stringArray[114], Category.RENDER, false);
            this.painterly = new SkyModule$Look(stringArray[70], stringArray[81], stringArray[154], stringArray[69]);
            this.fantasy = new SkyModule$Look(stringArray[160], stringArray[166], stringArray[46], stringArray[118]);
            this.looks = new EnumMap<SkyModule$Style, SkyModule$Look>(SkyModule$Style.class);
            this.style = new EnumSetting<SkyModule$Style>(stringArray[75], SkyModule$Style.Painterly);
            this.preset = new ChoiceSetting(stringArray[157], stringArray[83], this::presetNames);
            this.presetName = new StringSetting(stringArray[127], "", 24);
            this.save = new ActionSetting(stringArray[15], this::lambda$new$0, this::savePreset);
            this.delete = new ActionSetting(stringArray[63], this::lambda$new$1, this::deletePreset);
            this.paste = new ActionSetting(stringArray[173], this::lambda$new$2, this::importClipboard);
            this.bands = SkyModule.num(this.painterly, stringArray[195], stringArray[137], 3.0, 16.0, 1.0);
            this.grain = SkyModule.num(this.painterly, stringArray[192], stringArray[94], 0.0, 3.0, 0.05);
            this.sunSize = SkyModule.num(this.painterly, stringArray[174], stringArray[39], 1.0, 12.0, 0.1);
            this.moonSize = SkyModule.num(this.painterly, stringArray[151], stringArray[129], 1.0, 12.0, 0.1);
            this.clouds = SkyModule.bool(this.painterly, stringArray[25], stringArray[97]);
            this.coverage = SkyModule.num(this.painterly, stringArray[90], stringArray[146], 0.0, 1.0, 0.01);
            this.wind = SkyModule.num(this.painterly, stringArray[125], stringArray[65], 0.0, 150.0, 1.0);
            this.cloudDetail = SkyModule.num(this.painterly, stringArray[120], stringArray[158], 2.0, 5.0, 1.0);
            this.cloudLight = SkyModule.color(this.painterly, stringArray[194], stringArray[123]);
            this.cloudShadow = SkyModule.color(this.painterly, stringArray[87], stringArray[126]);
            this.towers = SkyModule.bool(this.painterly, stringArray[180], stringArray[131]);
            this.towerHeight = SkyModule.num(this.painterly, stringArray[98], stringArray[103], 0.05, 0.45, 0.01);
            this.stars = SkyModule.bool(this.painterly, stringArray[30], stringArray[152]);
            this.starDensity = SkyModule.num(this.painterly, stringArray[148], stringArray[86], 0.0, 1.0, 0.01);
            this.twinkle = SkyModule.num(this.painterly, stringArray[128], stringArray[189], 0.0, 1.0, 0.01);
            this.river = SkyModule.bool(this.painterly, stringArray[26], stringArray[47]);
            this.riverStrength = SkyModule.num(this.painterly, stringArray[106], stringArray[164], 0.0, 2.0, 0.01);
            this.aurora = SkyModule.bool(this.painterly, stringArray[34], stringArray[175]);
            this.auroraStrength = SkyModule.num(this.painterly, stringArray[150], stringArray[141], 0.0, 2.0, 0.01);
            this.auroraLow = SkyModule.color(this.painterly, stringArray[6], stringArray[61]);
            this.auroraHigh = SkyModule.color(this.painterly, stringArray[165], stringArray[10]);
            this.meteors = SkyModule.bool(this.painterly, stringArray[50], stringArray[57]);
            this.meteorRate = SkyModule.num(this.painterly, stringArray[88], stringArray[168], 0.0, 1.0, 0.01);
            this.atmo = SkyModule.bool(this.fantasy, stringArray[78], stringArray[4]);
            this.haze = SkyModule.num(this.fantasy, stringArray[200], stringArray[108], 0.3, 8.0, 0.05);
            this.dayTint = SkyModule.num(this.fantasy, stringArray[182], stringArray[176], -60.0, 60.0, 1.0);
            this.exposure = SkyModule.num(this.fantasy, stringArray[24], stringArray[32], 0.1, 4.0, 0.01);
            this.saturation = SkyModule.num(this.fantasy, stringArray[187], stringArray[76], 0.0, 2.0, 0.01);
            this.horizonGlow = SkyModule.num(this.fantasy, stringArray[110], stringArray[202], 0.0, 3.0, 0.01);
            this.sunDisc = SkyModule.num(this.fantasy, stringArray[199], stringArray[161], 0.3, 30.0, 0.1);
            this.sunlight = SkyModule.color(this.fantasy, stringArray[122], stringArray[35]);
            this.nightZen = SkyModule.color(this.fantasy, stringArray[134], stringArray[147]);
            this.nightHor = SkyModule.color(this.fantasy, stringArray[73], stringArray[21]);
            this.planet = SkyModule.bool(this.fantasy, stringArray[209], stringArray[53]);
            this.planetAz = SkyModule.num(this.fantasy, stringArray[74], stringArray[198], 0.0, 360.0, 0.1);
            this.planetEl = SkyModule.num(this.fantasy, stringArray[142], stringArray[124], -20.0, 60.0, 0.1);
            this.planetSize = SkyModule.num(this.fantasy, stringArray[8], stringArray[121], 3.0, 40.0, 0.5);
            this.planetBright = SkyModule.num(this.fantasy, stringArray[89], stringArray[40], 0.0, 3.0, 0.01);
            this.banding = SkyModule.num(this.fantasy, stringArray[100], stringArray[41], 0.0, 1.0, 0.01);
            this.planetLight = SkyModule.color(this.fantasy, stringArray[60], stringArray[11]);
            this.planetDark = SkyModule.color(this.fantasy, stringArray[111], stringArray[101]);
            this.planetAir = SkyModule.color(this.fantasy, stringArray[196], stringArray[159]);
            this.airGlow = SkyModule.num(this.fantasy, stringArray[203], stringArray[115], 0.0, 3.0, 0.01);
            this.lightPhase = SkyModule.num(this.fantasy, stringArray[178], stringArray[7], 0.0, 175.0, 1.0);
            this.lightFrom = SkyModule.num(this.fantasy, stringArray[84], stringArray[59], 0.0, 360.0, 1.0);
            this.rings = SkyModule.bool(this.fantasy, stringArray[132], stringArray[18]);
            this.ringTilt = SkyModule.num(this.fantasy, stringArray[82], stringArray[112], -60.0, 60.0, 0.5);
            this.ringRoll = SkyModule.num(this.fantasy, stringArray[48], stringArray[186], -90.0, 90.0, 0.5);
            this.ringInner = SkyModule.num(this.fantasy, stringArray[133], stringArray[184], 1.05, 3.0, 0.01);
            this.ringOuter = SkyModule.num(this.fantasy, stringArray[93], stringArray[92], 1.2, 4.0, 0.01);
            this.ringOpacity = SkyModule.num(this.fantasy, stringArray[177], stringArray[20], 0.0, 1.5, 0.01);
            this.ringColor = SkyModule.color(this.fantasy, stringArray[43], stringArray[113]);
            this.moon = SkyModule.bool(this.fantasy, stringArray[140], stringArray[0]);
            this.moonAz = SkyModule.num(this.fantasy, stringArray[153], stringArray[193], 0.0, 360.0, 0.1);
            this.moonEl = SkyModule.num(this.fantasy, stringArray[17], stringArray[12], -10.0, 85.0, 0.1);
            this.moonRadius = SkyModule.num(this.fantasy, stringArray[190], stringArray[130], 0.5, 10.0, 0.05);
            this.moonColor = SkyModule.color(this.fantasy, stringArray[138], stringArray[52]);
            this.nebula = SkyModule.bool(this.fantasy, stringArray[171], stringArray[183]);
            this.nebBright = SkyModule.num(this.fantasy, stringArray[67], stringArray[117], 0.0, 3.0, 0.01);
            this.nebWidth = SkyModule.num(this.fantasy, stringArray[179], stringArray[139], 0.1, 1.5, 0.01);
            this.nebDust = SkyModule.num(this.fantasy, stringArray[181], stringArray[36], 0.0, 2.0, 0.01);
            this.nebYaw = SkyModule.num(this.fantasy, stringArray[144], stringArray[44], 0.0, 360.0, 1.0);
            this.nebPitch = SkyModule.num(this.fantasy, stringArray[185], stringArray[79], -90.0, 90.0, 1.0);
            this.nebGlow = SkyModule.color(this.fantasy, stringArray[205], stringArray[42]);
            this.nebFilament = SkyModule.color(this.fantasy, stringArray[136], stringArray[206]);
            this.nebCore = SkyModule.color(this.fantasy, stringArray[116], stringArray[71]);
            this.starfield = SkyModule.bool(this.fantasy, stringArray[109], stringArray[155]);
            this.fieldDensity = SkyModule.num(this.fantasy, stringArray[197], stringArray[143], 0.0, 1.0, 0.01);
            this.fieldBright = SkyModule.num(this.fantasy, stringArray[54], stringArray[117], 0.0, 3.0, 0.01);
            this.shimmer = SkyModule.num(this.fantasy, stringArray[55], stringArray[64], 0.0, 1.0, 0.01);
            this.lights = SkyModule.bool(this.fantasy, stringArray[204], stringArray[119]);
            this.lightsStrength = SkyModule.num(this.fantasy, stringArray[49], stringArray[96], 0.0, 4.0, 0.01);
            this.storm = SkyModule.num(this.fantasy, stringArray[135], stringArray[156], 0.0, 1.0, 0.01);
            this.spread = SkyModule.num(this.fantasy, stringArray[163], stringArray[56], 1.0, 14.0, 0.1);
            this.lightsDir = SkyModule.num(this.fantasy, stringArray[68], stringArray[38], 0.0, 360.0, 1.0);
            this.lightsSpeed = SkyModule.num(this.fantasy, stringArray[22], stringArray[99], 0.0, 4.0, 0.01);
            this.lightsMain = SkyModule.color(this.fantasy, stringArray[62], stringArray[80]);
            this.lightsTop = SkyModule.color(this.fantasy, stringArray[85], stringArray[107]);
            this.lightsFringe = SkyModule.color(this.fantasy, stringArray[9], stringArray[102]);
            this.shooting = SkyModule.bool(this.fantasy, stringArray[191], stringArray[105]);
            this.shootingRate = SkyModule.num(this.fantasy, stringArray[37], stringArray[51], 0.0, 1.0, 0.01);
            this.resolution = new NumberSetting(stringArray[13], 50.0, 100.0, 100.0, 5.0);
            this.shown = SkyModule$Style.Painterly;
            instance = this;
            this.addSettings(this.style, this.preset, this.presetName, this.save, this.delete, this.paste, this.bands, this.grain, this.sunSize, this.moonSize, this.clouds, this.coverage, this.wind, this.cloudDetail, this.cloudLight, this.cloudShadow, this.towers, this.towerHeight, this.stars, this.starDensity, this.twinkle, this.river, this.riverStrength, this.aurora, this.auroraStrength, this.auroraLow, this.auroraHigh, this.meteors, this.meteorRate, this.atmo, this.haze, this.dayTint, this.exposure, this.saturation, this.horizonGlow, this.sunDisc, this.sunlight, this.nightZen, this.nightHor, this.planet, this.planetAz, this.planetEl, this.planetSize, this.planetBright, this.banding, this.planetLight, this.planetDark, this.planetAir, this.airGlow, this.lightPhase, this.lightFrom, this.rings, this.ringTilt, this.ringRoll, this.ringInner, this.ringOuter, this.ringOpacity, this.ringColor, this.moon, this.moonAz, this.moonEl, this.moonRadius, this.moonColor, this.nebula, this.nebBright, this.nebWidth, this.nebDust, this.nebYaw, this.nebPitch, this.nebGlow, this.nebFilament, this.nebCore, this.starfield, this.fieldDensity, this.fieldBright, this.shimmer, this.lights, this.lightsStrength, this.storm, this.spread, this.lightsDir, this.lightsSpeed, this.lightsMain, this.lightsTop, this.lightsFringe, this.shooting, this.shootingRate, this.resolution);
            Setting[] settingArray = new Setting[]{this.bands, this.grain, this.sunSize, this.moonSize, this.clouds, this.towers, this.stars, this.river, this.aurora, this.meteors};
            Setting[] settingArray2 = new Setting[]{this.atmo, this.planet, this.moon, this.nebula, this.starfield, this.lights, this.shooting};
            this.style.children(this.preset);
            this.style.children(settingArray);
            this.style.children(settingArray2);
            this.preset.children(this.presetName, this.save, this.delete, this.paste);
            this.clouds.children(this.coverage, this.wind, this.cloudDetail, this.cloudLight, this.cloudShadow);
            this.towers.children(this.towerHeight);
            this.stars.children(this.starDensity, this.twinkle);
            this.river.children(this.riverStrength);
            this.aurora.children(this.auroraStrength, this.auroraLow, this.auroraHigh);
            this.meteors.children(this.meteorRate);
            this.atmo.children(this.haze, this.dayTint, this.exposure, this.saturation, this.horizonGlow, this.sunDisc, this.sunlight, this.nightZen, this.nightHor);
            this.planet.children(this.planetAz, this.planetEl, this.planetSize, this.planetBright, this.banding, this.planetLight, this.planetDark, this.planetAir, this.airGlow, this.lightPhase, this.lightFrom, this.rings);
            this.rings.children(this.ringTilt, this.ringRoll, this.ringInner, this.ringOuter, this.ringOpacity, this.ringColor);
            this.moon.children(this.moonAz, this.moonEl, this.moonRadius, this.moonColor);
            this.nebula.children(this.nebBright, this.nebWidth, this.nebDust, this.nebYaw, this.nebPitch, this.nebGlow, this.nebFilament, this.nebCore);
            this.starfield.children(this.fieldDensity, this.fieldBright, this.shimmer);
            bl = AmbienceModule.b;
            this.lights.children(this.lightsStrength, this.storm, this.spread, this.lightsDir, this.lightsSpeed, this.lightsMain, this.lightsTop, this.lightsFringe);
            this.shooting.children(this.shootingRate);
            for (Object object2 : settingArray) {
                if (!bl) {
                    Setting setting = object2;
                    setting.visibleWhen(this::lambda$new$3);
                    if (!bl) continue;
                    boolean bl2 = !Module.a;
                    Module.a = bl2;
                    break;
                }
                break block15;
            }
            for (Setting setting : settingArray2) {
                object2 = setting.visibleWhen(this::lambda$new$4);
                if (!bl) {
                    if (!bl) continue;
                    break;
                }
                break block15;
            }
            this.looks.put(SkyModule$Style.Painterly, this.painterly);
            object2 = this.looks.put(SkyModule$Style.Fantasy, this.fantasy);
        }
        for (Setting setting : SkyModule$Style.values()) {
            if (!((SkyModule$Style)((Object)setting)).generated()) {
                continue;
            }
            SkyModule$Look skyModule$Look = new SkyModule$Look(((SkyModule$Style)setting).id);
            Object object3 = this.looks.put((SkyModule$Style)((Object)setting), skyModule$Look);
            while (true) {
                for (Object object3 : this.generatedSettings(skyModule$Look)) {
                    if (bl) continue;
                    Setting setting2 = (Setting)object3;
                    setting2.visibleWhen(() -> this.lambda$new$5((SkyModule$Style)((Object)setting)));
                    this.style.children(setting2);
                    if (!bl) continue;
                }
                break;
            }
            if (!bl) continue;
        }
        for (SkyModule$Look skyModule$Look : this.looks.values()) {
            this.pick(skyModule$Look, skyModule$Look.first);
            if (!bl) continue;
        }
    }

    
    private List<Setting> generatedSettings(SkyModule$Look var1_1) {
        var3_2 = new ArrayList<Setting>();
        var4_3 = var1_1.sections.iterator();
        var2_4 = AmbienceModule.b;
        while (var4_3.hasNext()) {
            block38: {
                block33: {
                    block37: {
                        var5_5 = (JsonElement)var4_3.next();
                        v0 = var6_6 = var5_5.getAsJsonObject();
                        if (var2_4) // goto
                        if (v0.has(SkyModule.b[149])) {
                            v0 = var6_6.get(SkyModule.b[27]);
lbl10:

                            v1 = v0.getAsString();
                        } else {
                            v1 = null;
                        }
                        var7_7 = v1;
                        var8_8 = null;
                        var9_9 = new ArrayList<BooleanSetting>();
                        var19_19 = SkyModule.b;
                        for (JsonElement var11_11 : var6_6.getAsJsonArray(var19_19[104])) {
                            block35: {
                                block36: {
                                    block34: {
                                        var12_12 = var11_11.getAsJsonObject();
                                        if (var2_4) break block33;
                                        var19_19 = SkyModule.b;
                                        var13_13 = var12_12.get(var19_19[31]).getAsString();
                                        var14_14 = var12_12.get(var19_19[29]).getAsString();
                                        var15_15 = var12_12.get(var19_19[167]).getAsString();
                                        var17_17 = var14_14;
                                        var18_18 = -1;
                                        v2 = var17_17.hashCode();
                                        if (var2_4) break block34;
                                        switch (v2) {
                                            case 109446: {
                                                v2 = (int)var17_17.equals(SkyModule.b[16]);
                                                if (!var2_4) {
                                                    if (v2 == 0) break;
                                                    var18_18 = 0;
                                                    if (!var2_4) break;
                                                }
                                                break block34;
                                            }
                                            case 3029738: {
                                                v2 = (int)var17_17.equals(SkyModule.b[172]);
                                                if (var2_4) break block34;
                                                if (v2 == 0) break;
                                                var18_18 = 1;
                                            }
                                        }
                                        v2 = var18_18;
                                    }
                                    switch (v2) {
                                        case 0: {
                                            v3 /* !! */  = SkyModule.num(var1_1, var13_13, var15_15, var12_12.get(SkyModule.b[3]).getAsDouble(), var12_12.get(SkyModule.b[1]).getAsDouble(), var12_12.get(SkyModule.b[72]).getAsDouble());
                                            break;
                                        }
                                        case 1: {
                                            v3 /* !! */  = SkyModule.bool(var1_1, var13_13, var15_15);
                                            break;
                                        }
                                        default: {
                                            v3 /* !! */  = SkyModule.color(var1_1, var13_13, var15_15);
                                        }
                                    }
                                    var16_16 /* !! */  = v3 /* !! */ ;
                                    v4 = var1_1.uniforms.add(new SkyModule$Uniform(var13_13, var14_14.equals(SkyModule.b[188]), var14_14.equals(SkyModule.b[28])));
                                    if (var2_4) break block35;
                                    this.addSettings(new Setting[]{var16_16 /* !! */ });
                                    if (!var13_13.equals(var7_7)) break block36;
                                    var8_8 = var16_16 /* !! */ ;
                                    if (!var2_4) break block35;
                                }
                                v4 = var9_9.add(var16_16 /* !! */ );
                            }
                            if (!var2_4) continue;
                        }
                        v5 = var8_8;
                        if (var2_4) break block37;
                        if (v5 == null) break block33;
                        v5 = var8_8.children(var9_9.toArray(new Setting[0]));
                    }
                    var3_2.add(var8_8);
                    if (!var2_4) break block38;
                }
                var3_2.addAll(var9_9);
            }
            if (!var2_4) continue;
        }
        return var3_2;
    }

    public SkyModule$Style style() {
        return this.style.getValue();
    }

    public float resolution() {
        return this.resolution.getValueFloat() / 100.0f;
    }

    private SkyModule$Look cur() {
        return this.looks.get((Object)this.style.getValue());
    }

    public List<SkyModule$Uniform> uniforms() {
        return this.cur().uniforms;
    }

    public int fogRgb(String string) {
        int n;
        block14: {
            String string2;
            block13: {
                Integer n2;
                block12: {
                    JsonElement jsonElement;
                    boolean bl;
                    block10: {
                        JsonElement jsonElement2;
                        block11: {
                            block8: {
                                block9: {
                                    jsonElement2 = this.cur().fog.get(string);
                                    bl = AmbienceModule.b;
                                    jsonElement = jsonElement2;
                                    if (bl) break block8;
                                    if (jsonElement != null) break block9;
                                    jsonElement2 = this.cur().fog.get(b[210]);
                                }
                                jsonElement = jsonElement2;
                            }
                            if (bl) break block10;
                            if (jsonElement != null) break block11;
                            return 0;
                        }
                        jsonElement = jsonElement2;
                    }
                    string2 = jsonElement.getAsString();
                    Integer n3 = SkyModule.hex(string2);
                    n2 = n3;
                    if (bl) break block12;
                    if (n2 == null) break block13;
                    n2 = n3;
                }
                n = n2;
                break block14;
            }
            n = this.rgb(string2);
        }
        return n;
    }

    public float num(String string) {
        float f;
        block10: {
            block9: {
                JsonElement jsonElement;
                block8: {
                    boolean bl;
                    SkyModule$Look skyModule$Look;
                    block7: {
                        Setting setting;
                        block6: {
                            skyModule$Look = this.cur();
                            bl = AmbienceModule.b;
                            Setting setting2 = skyModule$Look.bound.get(string);
                            setting = setting2;
                            if (bl) break block6;
                            if (!(setting instanceof NumberSetting)) break block7;
                            setting = setting2;
                        }
                        NumberSetting numberSetting = (NumberSetting)setting;
                        return numberSetting.getValueFloat();
                    }
                    JsonElement jsonElement2 = skyModule$Look.look.get(string);
                    jsonElement = jsonElement2;
                    if (bl) break block8;
                    if (jsonElement == null) break block9;
                    jsonElement = jsonElement2;
                }
                f = jsonElement.getAsFloat();
                break block10;
            }
            f = 0.0f;
        }
        return f;
    }

    public boolean on(String string) {
        boolean bl;
        block12: {
            block11: {
                JsonElement jsonElement;
                boolean bl2;
                block10: {
                    SkyModule$Look skyModule$Look;
                    block9: {
                        Setting setting;
                        block8: {
                            skyModule$Look = this.cur();
                            Setting setting2 = skyModule$Look.bound.get(string);
                            bl2 = AmbienceModule.b;
                            setting = setting2;
                            if (bl2) break block8;
                            if (!(setting instanceof BooleanSetting)) break block9;
                            setting = setting2;
                        }
                        BooleanSetting booleanSetting = (BooleanSetting)setting;
                        return booleanSetting.getValue();
                    }
                    JsonElement jsonElement2 = skyModule$Look.look.get(string);
                    jsonElement = jsonElement2;
                    if (bl2) break block10;
                    if (jsonElement == null) break block11;
                    jsonElement = jsonElement2;
                }
                bl = jsonElement.getAsBoolean();
                if (bl2) break block12;
                if (!bl) break block11;
                bl = true;
                break block12;
            }
            bl = false;
        }
        return bl;
    }

    public int rgb(String string) {
        int n;
        block15: {
            block13: {
                Object object;
                block12: {
                    Integer n2;
                    boolean bl;
                    Object object2;
                    block14: {
                        block11: {
                            JsonElement jsonElement;
                            block10: {
                                SkyModule$Look skyModule$Look;
                                block9: {
                                    Setting setting;
                                    block8: {
                                        skyModule$Look = this.cur();
                                        object2 = skyModule$Look.bound.get(string);
                                        bl = AmbienceModule.b;
                                        setting = object2;
                                        if (bl) break block8;
                                        if (!(setting instanceof ColorSetting)) break block9;
                                        setting = object2;
                                    }
                                    ColorSetting colorSetting = (ColorSetting)setting;
                                    return colorSetting.getRGB();
                                }
                                JsonElement jsonElement2 = skyModule$Look.look.get(string);
                                jsonElement = jsonElement2;
                                if (bl) break block10;
                                if (jsonElement == null) break block11;
                                jsonElement = jsonElement2;
                            }
                            n2 = SkyModule.hex(jsonElement.getAsString());
                            break block14;
                        }
                        n2 = null;
                    }
                    object2 = n2;
                    object = object2;
                    if (bl) break block12;
                    if (object == null) break block13;
                    object = object2;
                }
                n = (Integer)object;
                break block15;
            }
            n = 0;
        }
        return n;
    }

    private static NumberSetting num(SkyModule$Look skyModule$Look, String string, String string2, double d, double d2, double d3) {
        NumberSetting numberSetting = new NumberSetting(string2, d, d2, skyModule$Look.defaults.get(string).getAsDouble(), d3);
        skyModule$Look.bound.put(string, numberSetting);
        return numberSetting;
    }

    private static BooleanSetting bool(SkyModule$Look skyModule$Look, String string, String string2) {
        BooleanSetting booleanSetting = new BooleanSetting(string2, skyModule$Look.defaults.get(string).getAsBoolean());
        skyModule$Look.bound.put(string, booleanSetting);
        return booleanSetting;
    }

    private static ColorSetting color(SkyModule$Look skyModule$Look, String string, String string2) {
        ColorSetting colorSetting = new ColorSetting(string2, (int)d | SkyModule.hex(skyModule$Look.defaults.get(string).getAsString()), false);
        skyModule$Look.bound.put(string, colorSetting);
        return colorSetting;
    }

    
    private static Integer hex(String string) {
        boolean bl = AmbienceModule.b;
        String string2 = string;
        if (!bl) {
            if (string2 == null) return null;
            string2 = string;
        }
        if (!bl) {
            if (string2.length() != 7) return null;
            string2 = string;
        }
        int n = 0;
        if (bl) return Integer.parseInt(string2, n);
        if (string2.charAt(n) != '#') {
            return null;
        }
        try {
            string2 = string.substring(1);
            n = 16;
            return Integer.parseInt(string2, n);
        }
        catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    private List<String> presetNames() {
        SkyModule$Look skyModule$Look = this.cur();
        ArrayList<String> arrayList = new ArrayList<String>(skyModule$Look.presets.keySet());
        arrayList.addAll(skyModule$Look.saved.keySet());
        return arrayList;
    }

    private void pick(SkyModule$Look skyModule$Look, String string) {
        skyModule$Look.loaded = string;
        skyModule$Look.loadedSig = skyModule$Look.sig();
        if (skyModule$Look == this.cur()) {
            this.preset.setValue(string);
        }
    }

    
    @Override
    public void onSettingsChanged() {
        block34: {
            block35: {
                block28: {
                    block32: {
                        block33: {
                            block31: {
                                block29: {
                                    block30: {
                                        block26: {
                                            block27: {
                                                var2_1 = this.cur();
                                                var1_2 = AmbienceModule.b;
                                                v0 = this;
                                                if (var1_2) break block26;
                                                if (v0.style.getValue() == this.shown) break block27;
                                                this.shown = this.style.getValue();
                                                this.preset.setValue(var2_1.loaded);
                                                return;
                                            }
                                            v0 = this;
                                        }
                                        var3_3 = v0.preset.getValue();
                                        v3 = var3_3;
                                        v4 = var2_1.loaded;
                                        if (var1_2) break block28;
                                        if (!v3.equals(v4)) {
                                        }
                                        v6 = var2_1;
                                        if (var1_2) break block29;
                                        if (!v6.presets.has(var3_3)) break block30;
                                        v9 = var2_1.presets.getAsJsonObject(var3_3);
                                        break block31;
                                    }
                                    v6 = var2_1.saved.get(var3_3);
                                }
                                v9 = (JsonObject)v6;
                            }
                            var4_4 = v9;
                            if (var1_2) break block32;
                            if (var4_4 == null) break block33;
                            var2_1.load(var4_4);
                        }
                        this.pick(var2_1, var3_3);
                    }
                    if (!var1_2) break block34;
lbl67:

                    v3 = var3_3;
                    v4 = SkyModule.b[14];
                }
                v14 = v3.equals(v4);
                if (var1_2) break block35;
                if (v14 != 0) break block34;
                v14 = var2_1.sig();
            }
            if (v14 != var2_1.loadedSig) {
                this.pick(var2_1, SkyModule.b[14]);
            }
        }
    }

    
    private void savePreset() {
        String string;
        SkyModule$Look skyModule$Look;
        block13: {
            String string2;
            block14: {
                block12: {
                    boolean bl;
                    block11: {
                        skyModule$Look = this.cur();
                        string = this.presetName.getValue().trim();
                        boolean bl2 = AmbienceModule.b;
                        bl = string.isEmpty();
                        if (!bl2) {
                            if (bl) {
                                this.flash(b[211], b[91]);
                                return;
                            }
                            bl = skyModule$Look.presets.has(string);
                        }
                        if (bl2) break block11;
                        if (bl) break block12;
                        string2 = string;
                        if (bl2) break block13;
                        bl = string2.equals(b[162]);
                    }
                    if (!bl) break block14;
                }
                this.flash(b[211], b[23]);
                return;
            }
            string2 = skyModule$Look.saved.put(string, skyModule$Look.snapshot());
        }
        this.presetName.setValue("");
        this.pick(skyModule$Look, string);
        ConfigManager.save();
        String[] stringArray = b;
        this.flash(stringArray[211], stringArray[169]);
    }

    private void deletePreset() {
        block4: {
            SkyModule$Look skyModule$Look;
            block5: {
                boolean bl = AmbienceModule.b;
                skyModule$Look = this.cur();
                if (bl) break block4;
                if (skyModule$Look.saved.remove(this.preset.getValue()) != null) break block5;
                return;
            }
            this.pick(skyModule$Look, b[14]);
            ConfigManager.save();
        }
    }

    
    private void importClipboard() {
        block8: {
            SkyModule$Look skyModule$Look = this.cur();
            boolean bl = AmbienceModule.b;
            try {
                SkyModule$Look skyModule$Look2;
                JsonObject jsonObject;
                block10: {
                    block9: {
                        block12: {
                            block11: {
                                jsonObject = JsonParser.parseString((String)this.mc.field_1774.method_1460()).getAsJsonObject();
                                if (bl) break block8;
                                if (!jsonObject.has(b[19])) break block9;
                                break block11;
                                catch (RuntimeException runtimeException) {
                                    throw runtimeException;
                                }
                            }
                            skyModule$Look2 = skyModule$Look;
                            if (bl) break block10;
                            break block12;
                            catch (RuntimeException runtimeException) {
                                throw runtimeException;
                            }
                        }
                        if (skyModule$Look2.id.equals(jsonObject.get(b[145]).getAsString())) break block9;
                        String[] stringArray = b;
                        this.flash(stringArray[95], stringArray[58] + skyModule$Look.id + stringArray[207]);
                        return;
                    }
                    skyModule$Look2 = skyModule$Look;
                }
                skyModule$Look2.load(jsonObject);
                String[] stringArray = b;
                this.pick(skyModule$Look, stringArray[14]);
                ConfigManager.save();
                this.flash(stringArray[5], stringArray[170]);
            }
            catch (RuntimeException runtimeException) {
                String[] stringArray = b;
                this.flash(stringArray[5], stringArray[208]);
            }
        }
    }

    private void flash(String string, String string2) {
        this.flash = string2;
        this.flashFor = string;
        this.flashUntil = System.currentTimeMillis() + e;
    }

    private String flashOr(String string, String string2) {
        String string3;
        block8: {
            block7: {
                int n;
                block6: {
                    boolean bl = AmbienceModule.b;
                    n = string.equals(this.flashFor);
                    if (bl) break block6;
                    if (n == 0) break block7;
                    long l = System.currentTimeMillis() - this.flashUntil;
                    n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                }
                if (n >= 0) break block7;
                string3 = this.flash;
                break block8;
            }
            string3 = string2;
        }
        return string3;
    }

    @Override
    public void resetDefaults(String string) {
        super.resetDefaults(string);
        this.shown = this.style.getValue();
        Iterator<SkyModule$Look> iterator = this.looks.values().iterator();
        boolean bl = AmbienceModule.b;
        while (iterator.hasNext()) {
            SkyModule$Look skyModule$Look = iterator.next();
            skyModule$Look.look = skyModule$Look.defaults.deepCopy();
            this.pick(skyModule$Look, skyModule$Look.first);
            if (!bl) continue;
        }
    }

    @Override
    public void writeExtra(JsonObject jsonObject) {
        Iterator<SkyModule$Look> iterator = this.looks.values().iterator();
        boolean bl = AmbienceModule.b;
        while (iterator.hasNext()) {
            block5: {
                SkyModule$Look skyModule$Look = iterator.next();
                jsonObject.add(skyModule$Look.id, (JsonElement)skyModule$Look.snapshot());
                JsonObject jsonObject2 = new JsonObject();
                for (Map.Entry<String, JsonObject> entry : skyModule$Look.saved.entrySet()) {
                    jsonObject2.add(entry.getKey(), (JsonElement)entry.getValue());
                    if (!bl) {
                        if (!bl) continue;
                        break;
                    }
                    break block5;
                }
                jsonObject.add(skyModule$Look.savedKey, (JsonElement)jsonObject2);
                jsonObject.addProperty(skyModule$Look.presetKey, skyModule$Look.loaded);
            }
            if (!bl) continue;
        }
    }

    
    @Override
    public void readExtra(JsonObject jsonObject) {
        this.shown = this.style.getValue();
        boolean bl = AmbienceModule.b;
        for (SkyModule$Look skyModule$Look : this.looks.values()) {
            boolean bl2;
            SkyModule$Look skyModule$Look2;
            block14: {
                block6: while (true) {
                    JsonObject jsonObject2;
                    skyModule$Look2 = skyModule$Look;
                    if (bl) return;
                    JsonElement jsonElement = jsonObject.get(skyModule$Look2.id);
                    if (!bl) {
                        if (jsonElement instanceof JsonObject) {
                            jsonObject2 = (JsonObject)jsonElement;
                            skyModule$Look2.load(jsonObject2);
                        }
                        skyModule$Look2.saved.clear();
                    }
                    JsonElement jsonElement2 = jsonObject.get(skyModule$Look2.savedKey);
                    bl2 = jsonElement2 instanceof JsonObject;
                    if (bl) break block14;
                    if (!bl2) break;
                    jsonObject2 = (JsonObject)jsonElement2;
                    for (Map.Entry entry : jsonObject2.entrySet()) {
                        block16: {
                            SkyModule$Look skyModule$Look3;
                            block15: {
                                SkyModule$Look skyModule$Look4;
                                skyModule$Look = entry.getValue();
                                if (bl) continue block6;
                                skyModule$Look3 = skyModule$Look4 = skyModule$Look;
                                if (bl) break block15;
                                if (!(skyModule$Look3 instanceof JsonObject)) break block16;
                                skyModule$Look3 = skyModule$Look4;
                            }
                            jsonElement = (JsonObject)skyModule$Look3;
                            skyModule$Look2.saved.put((String)entry.getKey(), (JsonObject)jsonElement);
                        }
                        if (!bl) continue;
                    }
                    break;
                }
                bl2 = jsonObject.has(skyModule$Look2.presetKey);
            }
            if (bl2) {
                this.pick(skyModule$Look2, jsonObject.get(skyModule$Look2.presetKey).getAsString());
            }
            if (!bl) continue;
        }
        this.preset.setValue(this.cur().loaded);
    }

    private Boolean lambda$new$5(SkyModule$Style skyModule$Style) {
        boolean bl = this.style.getValue() == skyModule$Style;
        return bl;
    }

    private Boolean lambda$new$4() {
        boolean bl = this.style.getValue() == SkyModule$Style.Fantasy;
        return bl;
    }

    private Boolean lambda$new$3() {
        boolean bl = this.style.getValue() == SkyModule$Style.Painterly;
        return bl;
    }

    private String lambda$new$2() {
        String[] stringArray = b;
        return this.flashOr(stringArray[5], stringArray[66]);
    }

    private String lambda$new$1() {
        String string = this.cur().saved.containsKey(this.preset.getValue()) ? b[77] : b[201];
        return string;
    }

    private String lambda$new$0() {
        String[] stringArray = b;
        return this.flashOr(stringArray[2], stringArray[33]);
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var9 = new String[212];
                        var7_1 = 0;
                        var6_2 = "rp\u0017\u0011\u0003R~\u0000\u0004L~\u000e\u001a\u0003Rv\u0016\n~k\u0015\u0010\r~uZm\u001d\u0005O~\u000b\u000b\u001b\u0004^j\nN\u000bsv\u001f\u0017\n.MW~\u000b\u001a\nOs\u0019\u0011\u001bzNVe\u001d\n^j\n\u0010\fo^PsK\u000bjo\b\u001a\f.^Ps\u0017\r\u000bsv\u001f\u0017\n.^Ps\u0017\r\tzs\u001d\t\u001fztPq\nmz\u000b\u0010\u0012{iVp\u0016\u0006|j\u000b\u000b\u0011c\u000bl~\u000e\u001a^^oZl\u001d\u000b\u0003Qj\u0015\u0006Rp\u0017\u0011;b\u0005mv\u0016\u0018\r\u0005Lk\u0001\u0013\u001b\u0007po\u0019\u001c\u0017zd\rqv\u001f\u0017\n.UPm\u0011\u0005\u0011`\u000b^j\n\u0010\foNOz\u001d\u001b\nq~\u0015\u001a^z|Tz\u0016\bZg\b\u0010\r{oZ\b\\s\u0017\n\u001a}RQ\u0007Mv\u000e\u001a\fAs\u0006Kp\u001f\u0018\u0012k\u0004]p\u0017\u0013\u0004Kf\b\u001a\u0007Lk\u0019\r\rAs\u0003Tz\u0001\bzg\b\u0010\r{oZ\u0004l~\u000e\u001a\b^j\n\u0010\foRQ\blj\u0016\u0013\u0017iuK\n{j\u000b\u000b^B|Qz\u000b\nRz\f\u001a\u0011|O^k\u001d\t{v\n\u001a\u001dztPq\blj\u0016_-ggZ\n}m\u0011\u0018\u0016zsZl\u000b\u000bx~\u000b_\u0011|=mp\u001b\u0014\nxs\u0017\b^MrSp\n\u0007Mv\u0016\u0018=aq\u0006mp\f\u001e\nk\u0003lt\u0001\fY~\u0016\u000b\u001f}dl~\u000e\u001a\u001a\nlk\u0019\r^\\tIz\n\bMv\u0016\u0018,aqS\u000e^j\n\u0010\foNKm\u001d\u0011\u0019zu\tRz\f\u001a\u0011|npq\u0004m~\f\u001a\u0005|p\u0014\u0010\f\u0006os\u0019\u0011\u001bz\nLk\u0019\r<|tXw\f\u0007Kh\u0011\u0011\u0015bx\u0006lo\n\u001a\u001fj\u000elw\u0017\u0010\ngsX?+\u000b\u001f|n\u0006qp\f_\u001f.\nsv\u001f\u0017\n.[Mp\u0015\nOs\u0019\u0011\u001bz^PsI\u000bsp\u000f\u001a\f.^Ps\u0017\r\n^j\n\u0010\fo^PsI\r{z\u0014\u001a\nk=om\u001d\f\u001bz\u0007lw\u0011\u0012\u0013ko\u0004hv\u0016\u001b\u000eym\u0017\u0012^mqVo\u001a\u0010\u001f|y\tQz\u001a=\fgzWk\b^j\n\u0010\fo\\E\u0006Om\u001d\f\u001bz\tO~\u0011\u0011\nkoSf\n|p\n\u001a^MrSp\n\u0004Lk\u001d\u000f\bQv\u001f\u0017\nFrM\bOs\u0019\u0011\u001bz\\E\u0005lk\u0001\u0013\u001b\nl~\f\n\foiVp\u0016\u0006{z\u0014\u001a\nk\u0006^k\u0015\u00101`\u0004kv\u0014\u000b\nr~\u0011\u0011^MrSp\n\rlj\u0015\u0012\u001b|=rz\u0019\u001b\u0011y\bMv\u0016\u0018*gqK\rlj\u0015\u0012\u001b|=rz\u0019\u001b\u0011y\tSv\u001f\u0017\n\\rSs\n^j\n\u0010\fo^PsJ\flk\u0019\r^JxQl\u0011\u000b\u0007\u000b\\s\u0017\n\u001a]u^{\u0017\b\nRz\f\u001a\u0011|O^k\u001d\fOs\u0019\u0011\u001bz_Mv\u001f\u0017\n\b\\p\u000e\u001a\fozZ\u0011kf\b\u001a^o=Q~\u0015\u001a^htMl\f\npj\f\u001a\f.X[x\u001d\tMv\u0016\u00181{iZm\u000bo~\b\u001a\f.ZM~\u0011\u0011\u0005O~\u000b\u000b\u001b\blk\n\u001a\u0010iiW\u0006|s\u0017\n\u001a}\n]~\u0016\u00146ktXw\f\u0005lo\u001d\u001a\u001a\u0007]~\u0016\u001b\u0017`z\n{~\n\u0014^MrSp\n\fym\u0011\u0011\u0019k=|p\u0014\u0010\f\fkp\u000f\u001a\f.UZv\u001f\u0017\n\u0005Vk\u001d\u0012\r\u0007rz\f\u001a\u0011|n\u0005Mv\u000e\u001a\f\tkp\b_=aqPm\u0004w~\u0002\u001a\u0007Lk\u0019\r\rAs\u000bWp\n\u0016\u0004asxs\u0017\b\nOs\u0019\u0011\u001bz^PsJ\u0004kv\u0014\u000b\u0005|p\u0014\u0010\f,mz\b\u0013\u001fmxL?\f\u0017\u001b.rIz\n\b\u0011|q[?\u000b\u0014\u0007.jVk\u0010_\u001f.nKf\u0014\u001a\u001a.rQz\b~v\n_9brH\u0007Qz\u001a<\u0011b.\n}m\u0011\u0018\u0016zsZl\u000b\rY~\u0016\u000b\u001f}dom\u001d\f\u001bz\u000fqp\n\u000b\u0016koQ?4\u0016\u0019fiL\b\\s\u0017\n\u001aA~K\u0006m~\u001c\u0016\u000b}\u0006Lj\u0016<\u0011b\tsv\f_.otQk\tzs\u001d\t\u001fztPq\tHv\u0016\u001b-~xZ{\flw\u0019\u001b\u0011y=o~\u0011\u0011\n\u000bom\u001d\f\u001bz=q~\u0015\u001a\u0007Kh\u0011\u0011\u0015bx\trp\u0017\u0011^]tEz\u0006m~\u001c\u0016\u000b}\u000ewp\n\u0016\u0004as\u001fK\u0017\b\u001b|n\u0007Mv\u0016\u0018\rAs\tMv\u0016\u00187`sZm\bQv\u001f\u0017\nTxQ\u000b^j\n\u0010\foNKp\n\u0012\u0007Qz\u001a<\u0011b/\u000b|p\u0014\u0010\f._^q\u001c\f\u0007Rp\u0017\u0011=aq\n}~\u0016\u001b^Yt[k\u0010\u0006Rp\u0017\u00111`\u000f~j\n\u0010\fo=lk\n\u001a\u0010iiW\bOs\u0019\u0011\u001bzXS\u0007{z\u0016\f\u0017zd\u0006Qz\u001a&\u001fy\u0005Lk\u0001\u0013\u001b\b|p\u000e\u001a\fozZ\fqv\u001f\u0017\n.GZq\u0011\u000b\u0016\u000bLk\u0019\r:ksLv\f\u0006\u0006Kp\u001f\u0018\u0012k\u0006^j\n\u0010\fo\bRp\u0017\u0011-ggZ\u0005lk\u0019\r\r\u0006Rp\u0017\u0011?t\u0005L~\u000e\u001a\u001a\tlk\u0019\r\u0018gxS{\u0005lk\u0017\r\u0013\u0006om\u001d\f\u001bz\f|s\u0017\n\u001a.YZk\u0019\u0016\u0012\t~v\n_=aqPm\u0007Y~\u0016\u000b\u001f}d\blj\u0016_:gn\\\u0006|j\u000b\u000b\u0011c\f^j\n\u0010\foNOm\u001d\u001e\u001a\u000emv\u000e\u001a\f.NKm\u001d\u0011\u0019zu\u0004^j\nM\bmv\u0016\u0018\u0018oqS\u0005S~\u001a\u001a\u0012\tym\u001d\u000e\u000bks\\f\u0005l~\u000e\u001a\u001a\bvr\b\u0010\fzx[\u0005Qz\u001a0\u0010\u0004]p\u0017\u0013\u0006vr\b\u0010\fz\u0007Lj\u0016,\u0017tx\u0006~j\n\u0010\fo\b{~\u0001_*gsK\u000bMv\u0016\u00181~|\\v\f\u0006\nSv\u001f\u0017\n^u^l\u001d\bQz\u001a(\u0017jiW\u0006]~\u0016\u00141`\u0007Qz\u001a;\u000b}i\u0003Wj\u001d\u0006qz\u001a\n\u0012o\nvq\u0016\u001a\f.X[x\u001d\bQz\u001a/\u0017z~W\u0004mp\u0014\u0013\nL~\f\n\foiVp\u0016\u0005\\p\u0014\u0010\f\u0007kh\u0011\u0011\u0015bx\bRp\u0017\u0011-ggZ\tRz\f\u001a\u0011|npq\u0005Xm\u0019\u0016\u0010\u0007~e\u0011\u0012\u000bzu\n\\s\u0017\n\u001aBtXw\f\u0005]~\u0016\u001b\r\nOs\u0019\u0011\u001bz\\Kr\u0017\u000bLk\u0019\r:ksLv\f\u0006\u0007~e\u0011\u0012\u000bzu\u0007Lj\u0016,\u0017tx\tKj\n\u001d\u0017jtKf\u0010ov\u001b\u0014^o=L~\u000e\u001a\u001a.rQz\fwp\n\u0016\u0004as\u001fX\u0014\u0010\t\b^k\u0015\u00109brH\b^j\n\u0010\foRQ\u0007Qz\u001a<\u0011b,\u000eyv\u0014\u001e\u0013ksK?;\u0010\u0012ao\u0004\u001fl\u0013\u0006\u0013qpX\f\u0015w=VqX\u001c\u0012gm]p\u0019\r\u001a\bOs\u0019\u0011\u001bzRQ";
                        var8_3 = "rp\u0017\u0011\u0003R~\u0000\u0004L~\u000e\u001a\u0003Rv\u0016\n~k\u0015\u0010\r~uZm\u001d\u0005O~\u000b\u000b\u001b\u0004^j\nN\u000bsv\u001f\u0017\n.MW~\u000b\u001a\nOs\u0019\u0011\u001bzNVe\u001d\n^j\n\u0010\fo^PsK\u000bjo\b\u001a\f.^Ps\u0017\r\u000bsv\u001f\u0017\n.^Ps\u0017\r\tzs\u001d\t\u001fztPq\nmz\u000b\u0010\u0012{iVp\u0016\u0006|j\u000b\u000b\u0011c\u000bl~\u000e\u001a^^oZl\u001d\u000b\u0003Qj\u0015\u0006Rp\u0017\u0011;b\u0005mv\u0016\u0018\r\u0005Lk\u0001\u0013\u001b\u0007po\u0019\u001c\u0017zd\rqv\u001f\u0017\n.UPm\u0011\u0005\u0011`\u000b^j\n\u0010\foNOz\u001d\u001b\nq~\u0015\u001a^z|Tz\u0016\bZg\b\u0010\r{oZ\b\\s\u0017\n\u001a}RQ\u0007Mv\u000e\u001a\fAs\u0006Kp\u001f\u0018\u0012k\u0004]p\u0017\u0013\u0004Kf\b\u001a\u0007Lk\u0019\r\rAs\u0003Tz\u0001\bzg\b\u0010\r{oZ\u0004l~\u000e\u001a\b^j\n\u0010\foRQ\blj\u0016\u0013\u0017iuK\n{j\u000b\u000b^B|Qz\u000b\nRz\f\u001a\u0011|O^k\u001d\t{v\n\u001a\u001dztPq\blj\u0016_-ggZ\n}m\u0011\u0018\u0016zsZl\u000b\u000bx~\u000b_\u0011|=mp\u001b\u0014\nxs\u0017\b^MrSp\n\u0007Mv\u0016\u0018=aq\u0006mp\f\u001e\nk\u0003lt\u0001\fY~\u0016\u000b\u001f}dl~\u000e\u001a\u001a\nlk\u0019\r^\\tIz\n\bMv\u0016\u0018,aqS\u000e^j\n\u0010\foNKm\u001d\u0011\u0019zu\tRz\f\u001a\u0011|npq\u0004m~\f\u001a\u0005|p\u0014\u0010\f\u0006os\u0019\u0011\u001bz\nLk\u0019\r<|tXw\f\u0007Kh\u0011\u0011\u0015bx\u0006lo\n\u001a\u001fj\u000elw\u0017\u0010\ngsX?+\u000b\u001f|n\u0006qp\f_\u001f.\nsv\u001f\u0017\n.[Mp\u0015\nOs\u0019\u0011\u001bz^PsI\u000bsp\u000f\u001a\f.^Ps\u0017\r\n^j\n\u0010\fo^PsI\r{z\u0014\u001a\nk=om\u001d\f\u001bz\u0007lw\u0011\u0012\u0013ko\u0004hv\u0016\u001b\u000eym\u0017\u0012^mqVo\u001a\u0010\u001f|y\tQz\u001a=\fgzWk\b^j\n\u0010\fo\\E\u0006Om\u001d\f\u001bz\tO~\u0011\u0011\nkoSf\n|p\n\u001a^MrSp\n\u0004Lk\u001d\u000f\bQv\u001f\u0017\nFrM\bOs\u0019\u0011\u001bz\\E\u0005lk\u0001\u0013\u001b\nl~\f\n\foiVp\u0016\u0006{z\u0014\u001a\nk\u0006^k\u0015\u00101`\u0004kv\u0014\u000b\nr~\u0011\u0011^MrSp\n\rlj\u0015\u0012\u001b|=rz\u0019\u001b\u0011y\bMv\u0016\u0018*gqK\rlj\u0015\u0012\u001b|=rz\u0019\u001b\u0011y\tSv\u001f\u0017\n\\rSs\n^j\n\u0010\fo^PsJ\flk\u0019\r^JxQl\u0011\u000b\u0007\u000b\\s\u0017\n\u001a]u^{\u0017\b\nRz\f\u001a\u0011|O^k\u001d\fOs\u0019\u0011\u001bz_Mv\u001f\u0017\n\b\\p\u000e\u001a\fozZ\u0011kf\b\u001a^o=Q~\u0015\u001a^htMl\f\npj\f\u001a\f.X[x\u001d\tMv\u0016\u00181{iZm\u000bo~\b\u001a\f.ZM~\u0011\u0011\u0005O~\u000b\u000b\u001b\blk\n\u001a\u0010iiW\u0006|s\u0017\n\u001a}\n]~\u0016\u00146ktXw\f\u0005lo\u001d\u001a\u001a\u0007]~\u0016\u001b\u0017`z\n{~\n\u0014^MrSp\n\fym\u0011\u0011\u0019k=|p\u0014\u0010\f\fkp\u000f\u001a\f.UZv\u001f\u0017\n\u0005Vk\u001d\u0012\r\u0007rz\f\u001a\u0011|n\u0005Mv\u000e\u001a\f\tkp\b_=aqPm\u0004w~\u0002\u001a\u0007Lk\u0019\r\rAs\u000bWp\n\u0016\u0004asxs\u0017\b\nOs\u0019\u0011\u001bz^PsJ\u0004kv\u0014\u000b\u0005|p\u0014\u0010\f,mz\b\u0013\u001fmxL?\f\u0017\u001b.rIz\n\b\u0011|q[?\u000b\u0014\u0007.jVk\u0010_\u001f.nKf\u0014\u001a\u001a.rQz\b~v\n_9brH\u0007Qz\u001a<\u0011b.\n}m\u0011\u0018\u0016zsZl\u000b\rY~\u0016\u000b\u001f}dom\u001d\f\u001bz\u000fqp\n\u000b\u0016koQ?4\u0016\u0019fiL\b\\s\u0017\n\u001aA~K\u0006m~\u001c\u0016\u000b}\u0006Lj\u0016<\u0011b\tsv\f_.otQk\tzs\u001d\t\u001fztPq\tHv\u0016\u001b-~xZ{\flw\u0019\u001b\u0011y=o~\u0011\u0011\n\u000bom\u001d\f\u001bz=q~\u0015\u001a\u0007Kh\u0011\u0011\u0015bx\trp\u0017\u0011^]tEz\u0006m~\u001c\u0016\u000b}\u000ewp\n\u0016\u0004as\u001fK\u0017\b\u001b|n\u0007Mv\u0016\u0018\rAs\tMv\u0016\u00187`sZm\bQv\u001f\u0017\nTxQ\u000b^j\n\u0010\foNKp\n\u0012\u0007Qz\u001a<\u0011b/\u000b|p\u0014\u0010\f._^q\u001c\f\u0007Rp\u0017\u0011=aq\n}~\u0016\u001b^Yt[k\u0010\u0006Rp\u0017\u00111`\u000f~j\n\u0010\fo=lk\n\u001a\u0010iiW\bOs\u0019\u0011\u001bzXS\u0007{z\u0016\f\u0017zd\u0006Qz\u001a&\u001fy\u0005Lk\u0001\u0013\u001b\b|p\u000e\u001a\fozZ\fqv\u001f\u0017\n.GZq\u0011\u000b\u0016\u000bLk\u0019\r:ksLv\f\u0006\u0006Kp\u001f\u0018\u0012k\u0006^j\n\u0010\fo\bRp\u0017\u0011-ggZ\u0005lk\u0019\r\r\u0006Rp\u0017\u0011?t\u0005L~\u000e\u001a\u001a\tlk\u0019\r\u0018gxS{\u0005lk\u0017\r\u0013\u0006om\u001d\f\u001bz\f|s\u0017\n\u001a.YZk\u0019\u0016\u0012\t~v\n_=aqPm\u0007Y~\u0016\u000b\u001f}d\blj\u0016_:gn\\\u0006|j\u000b\u000b\u0011c\f^j\n\u0010\foNOm\u001d\u001e\u001a\u000emv\u000e\u001a\f.NKm\u001d\u0011\u0019zu\u0004^j\nM\bmv\u0016\u0018\u0018oqS\u0005S~\u001a\u001a\u0012\tym\u001d\u000e\u000bks\\f\u0005l~\u000e\u001a\u001a\bvr\b\u0010\fzx[\u0005Qz\u001a0\u0010\u0004]p\u0017\u0013\u0006vr\b\u0010\fz\u0007Lj\u0016,\u0017tx\u0006~j\n\u0010\fo\b{~\u0001_*gsK\u000bMv\u0016\u00181~|\\v\f\u0006\nSv\u001f\u0017\n^u^l\u001d\bQz\u001a(\u0017jiW\u0006]~\u0016\u00141`\u0007Qz\u001a;\u000b}i\u0003Wj\u001d\u0006qz\u001a\n\u0012o\nvq\u0016\u001a\f.X[x\u001d\bQz\u001a/\u0017z~W\u0004mp\u0014\u0013\nL~\f\n\foiVp\u0016\u0005\\p\u0014\u0010\f\u0007kh\u0011\u0011\u0015bx\bRp\u0017\u0011-ggZ\tRz\f\u001a\u0011|npq\u0005Xm\u0019\u0016\u0010\u0007~e\u0011\u0012\u000bzu\n\\s\u0017\n\u001aBtXw\f\u0005]~\u0016\u001b\r\nOs\u0019\u0011\u001bz\\Kr\u0017\u000bLk\u0019\r:ksLv\f\u0006\u0007~e\u0011\u0012\u000bzu\u0007Lj\u0016,\u0017tx\tKj\n\u001d\u0017jtKf\u0010ov\u001b\u0014^o=L~\u000e\u001a\u001a.rQz\fwp\n\u0016\u0004as\u001fX\u0014\u0010\t\b^k\u0015\u00109brH\b^j\n\u0010\foRQ\u0007Qz\u001a<\u0011b,\u000eyv\u0014\u001e\u0013ksK?;\u0010\u0012ao\u0004\u001fl\u0013\u0006\u0013qpX\f\u0015w=VqX\u001c\u0012gm]p\u0019\r\u001a\bOs\u0019\u0011\u001bzRQ".length();
                        var5_4 = 4;
                        var4_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var4_5;
                            v1 = var6_2.substring(v0, v0 + var5_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var9[var7_1++] = v3.intern();
                            if ((var4_5 += var5_4) < var8_3) {
                                var5_4 = var6_2.charAt(var4_5);
                                continue;
                            }
                            var6_2 = "[~\u0001\u0004L~\u000e\u001a";
                            var8_3 = "[~\u0001\u0004L~\u000e\u001a".length();
                            var5_4 = 3;
                            var4_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var4_5;
                                v1 = var6_2.substring(v4, v4 + var5_4);
                                v2 = 0;
                                break block20;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var9[var7_1++] = v3.intern();
                            if ((var4_5 += var5_4) < var8_3) {
                                var5_4 = var6_2.charAt(var4_5);
                                continue;
                            }
                            break block21;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var10_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var10_6;
                        v9 = v6[v8];
                        switch (var10_6 % 7) {
                            case 0: {
                                v10 = 63;
                                break;
                            }
                            case 1: {
                                v10 = 31;
                                break;
                            }
                            case 2: {
                                v10 = 120;
                                break;
                            }
                            case 3: {
                                v10 = 127;
                                break;
                            }
                            case 4: {
                                v10 = 126;
                                break;
                            }
                            case 5: {
                                v10 = 14;
                                break;
                            }
                            default: {
                                v10 = 29;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var10_6;
lbl65:

                        v7 = v7;
                    } while (v7 > var10_6);
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
                SkyModule.b = var9;
                break block22;
lbl77:

                while (true) {
                    continue;
                    break;
                }
            }
            var2_7 = 230154949437980792L;
            while (true)
            SkyModule.d = -5332720996396007304L ^ var2_7;
            break block23;
lbl87:

            while (true) {
                continue;
                break;
            }
        }
        var0_8 = 6307413004857025270L;
        while (true)
        SkyModule.e = 6307413004857025830L ^ var0_8;
    }
}
