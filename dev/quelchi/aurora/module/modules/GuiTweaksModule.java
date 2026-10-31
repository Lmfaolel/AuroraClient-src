package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.hud.InventoryScale;
import dev.quelchi.aurora.hud.ItemHighlights;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.ExtraHuds;
import dev.quelchi.aurora.module.HudScales;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.ModuleManager;
import dev.quelchi.aurora.module.UnscaledHud;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Part;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import java.lang.invoke.LambdaMetafactory;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.client.gui.DrawContext;

public final class GuiTweaksModule
extends Module
implements ExtraHuds,
UnscaledHud {
    private static GuiTweaksModule instance;
    private final Map<GuiTweaksModule$Key, GuiTweaksModule$Part> parts;
    private static final EnumSet<GuiTweaksModule$Key> CLUSTER_CHILDREN;
    private final BooleanSetting hudTweaks;
    private final BooleanSetting inventoryTweaks;
    private final BooleanSetting noItemName;
    private final BooleanSetting hideSelector;
    private final BooleanSetting hideActionBar;
    private final ColorSetting scoreboardBackground;
    private final NumberSetting scoreboardScale;
    private final NumberSetting inventoryScale;
    private final NumberSetting inventoryAlpha;
    private final NumberSetting containerDim;
    private final NumberSetting tooltipAlpha;
    private static final String[] b;
    private static final long d;

    public static GuiTweaksModule get() {
        return instance;
    }

    
    public GuiTweaksModule() {
        block13: {
            block12: {
                var6_1 = GuiTweaksModule.b;
                super(var6_1[3], var6_1[20], Category.HUD, false);
                this.parts = new EnumMap<GuiTweaksModule$Key, GuiTweaksModule$Part>(GuiTweaksModule$Key.class);
                this.hudTweaks = new BooleanSetting(var6_1[16], true);
                this.inventoryTweaks = new BooleanSetting(var6_1[24], true);
                this.noItemName = new BooleanSetting(var6_1[4], false);
                this.hideSelector = new BooleanSetting(var6_1[22], false);
                this.hideActionBar = new BooleanSetting(var6_1[8], false);
                this.scoreboardBackground = new ColorSetting(var6_1[17], (int)GuiTweaksModule.d, true);
                this.scoreboardScale = new NumberSetting(var6_1[9], 0.5, 2.0, 1.0, 0.05);
                this.inventoryScale = new NumberSetting(var6_1[11], 0.0, 6.0, 4.0, 0.1);
                var1_2 = AmbienceModule.b;
                this.inventoryAlpha = new NumberSetting(var6_1[21], 0.2, 1.0, 1.0, 0.05);
                this.containerDim = new NumberSetting(var6_1[12], 0.0, 1.0, 1.0, 0.05);
                this.tooltipAlpha = new NumberSetting(var6_1[13], 0.1, 1.0, 1.0, 0.05);
                GuiTweaksModule.instance = this;
                var2_3 = GuiTweaksModule$Key.values();
                var3_4 = var2_3.length;
                for (var4_5 = 0; var4_5 < var3_4; ++var4_5) {
                    var5_6 = var2_3[var4_5];
                    v0 = this.parts;
                    if (!var1_2) {
                        v0.put((GuiTweaksModule$Key)var5_6, (GuiTweaksModule$Part)new GuiTweaksModule$Part(this, var5_6));
                        if (!var1_2) continue;
                        break;
                    }
                }
                this.addSettings(new Setting[]{this.hudTweaks, this.scoreboardScale, this.scoreboardBackground, this.noItemName, this.hideSelector, this.hideActionBar, this.inventoryTweaks, this.inventoryScale, this.inventoryAlpha, this.containerDim, this.tooltipAlpha});
                for (GuiTweaksModule$Key var5_6 : GuiTweaksModule$Key.values()) {
                    v2 = this;
                    if (!var1_2) {
                        v2.addSettings(new Setting[]{this.parts.get((Object)var5_6).shown});
                        if (!var1_2) continue;
                        break;
                    }
                    break block12;
                }
                this.scoreboardScale.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$0(), ()Ljava/lang/Boolean;)((GuiTweaksModule)this));
                this.scoreboardBackground.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$1(), ()Ljava/lang/Boolean;)((GuiTweaksModule)this));
                var6_1 = GuiTweaksModule.b;
                this.hudTweaks.tab(var6_1[6]);
                this.scoreboardScale.tab(var6_1[6]);
                this.noItemName.tab(var6_1[6]);
                this.hideSelector.tab(var6_1[6]);
                this.hideActionBar.tab(var6_1[6]);
                v2 = this;
            }
            var6_1 = GuiTweaksModule.b;
            v2.scoreboardBackground.tab(var6_1[6]);
            for (GuiTweaksModule$Key var5_6 : GuiTweaksModule$Key.values()) {
                v0 = this.parts.get((Object)var5_6);
lbl65:

                ((GuiTweaksModule$Part)v0).shown.tab(GuiTweaksModule.b[15]);
                if (!var1_2) {
                    if (!var1_2) continue;
                    break;
                }
                break block13;
            }
            var6_1 = GuiTweaksModule.b;
            this.inventoryTweaks.tab(var6_1[1]);
            this.inventoryScale.tab(var6_1[2]);
            this.inventoryAlpha.tab(var6_1[2]);
            this.containerDim.tab(var6_1[2]);
            this.tooltipAlpha.tab(var6_1[2]);
        }
    }

    public GuiTweaksModule$Part part(GuiTweaksModule$Key guiTweaksModule$Key) {
        return this.parts.get((Object)guiTweaksModule$Key);
    }

    @Override
    public void onTick() {
        InventoryScale.tick();
    }

    @Override
    public void onEnable() {
        InventoryScale.invalidate();
    }

    @Override
    public void onDisable() {
        InventoryScale.invalidate();
    }

    @Override
    public void onSettingsChanged() {
        block4: {
            float f;
            String string;
            block5: {
                string = this.part(GuiTweaksModule$Key.SCOREBOARD).hudKey();
                boolean bl = AmbienceModule.b;
                f = HudScales.get(string);
                if (bl) break block4;
                if (f != 1.0f) break block5;
                return;
            }
            this.scoreboardScale.setValue(f);
            HudScales.set(string, 1.0f);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static GuiTweaksModule$Part partOrNull(GuiTweaksModule$Key guiTweaksModule$Key) {
        Object object;
        block12: {
            GuiTweaksModule guiTweaksModule;
            block13: {
                boolean bl;
                block11: {
                    block10: {
                        guiTweaksModule = instance;
                        bl = AmbienceModule.b;
                        object = guiTweaksModule;
                        if (bl) break block10;
                        if (object == null) return null;
                        object = guiTweaksModule;
                    }
                    if (bl) break block11;
                    if (!((Module)object).isEnabled()) return null;
                    object = guiTweaksModule;
                }
                if (bl) break block12;
                if (((GuiTweaksModule)object).hudTweaks.getValue()) break block13;
                return null;
            }
            object = guiTweaksModule.parts.get((Object)guiTweaksModule$Key);
        }
        GuiTweaksModule$Part guiTweaksModule$Part = (GuiTweaksModule$Part)object;
        return guiTweaksModule$Part;
    }

    public static boolean partHidden(GuiTweaksModule$Key guiTweaksModule$Key) {
        boolean bl;
        block6: {
            block5: {
                GuiTweaksModule$Part guiTweaksModule$Part;
                boolean bl2;
                block4: {
                    GuiTweaksModule$Part guiTweaksModule$Part2 = GuiTweaksModule.partOrNull(guiTweaksModule$Key);
                    bl2 = AmbienceModule.b;
                    guiTweaksModule$Part = guiTweaksModule$Part2;
                    if (bl2) break block4;
                    if (guiTweaksModule$Part == null) break block5;
                    guiTweaksModule$Part = guiTweaksModule$Part2;
                }
                bl = guiTweaksModule$Part.hidden();
                if (bl2) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean partHiddenInPlay(GuiTweaksModule$Key guiTweaksModule$Key) {
        boolean bl;
        block6: {
            block5: {
                boolean bl2;
                block4: {
                    bl2 = AmbienceModule.b;
                    bl = GuiTweaksModule.partHidden(guiTweaksModule$Key);
                    if (bl2) break block4;
                    if (!bl) break block5;
                    bl = ModuleManager.editing();
                }
                if (bl2) break block6;
                if (bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean beginPart(DrawContext drawContext, GuiTweaksModule$Key guiTweaksModule$Key, int n, int n2, int n3, int n4, boolean bl) {
        block10: {
            GuiTweaksModule$Part guiTweaksModule$Part;
            block8: {
                boolean bl2;
                block9: {
                    bl2 = AmbienceModule.b;
                    guiTweaksModule$Part = GuiTweaksModule.partOrNull(guiTweaksModule$Key);
                    if (bl2) break block8;
                    if (guiTweaksModule$Part != null) break block9;
                    return false;
                }
                guiTweaksModule$Part.nested = bl;
                guiTweaksModule$Part.reportAnchor(n, n2, n3, n4);
                if (bl2) break block10;
            }
            if (guiTweaksModule$Part.isDefault()) {
                return false;
            }
            drawContext.method_51448().pushMatrix();
            guiTweaksModule$Part.applyTransform(drawContext);
        }
        return true;
    }

    public static void pushOverlay(DrawContext drawContext, GuiTweaksModule$Key guiTweaksModule$Key) {
        block12: {
            GuiTweaksModule$Part guiTweaksModule$Part;
            block11: {
                GuiTweaksModule$Part guiTweaksModule$Part2;
                boolean bl;
                block10: {
                    GuiTweaksModule$Key guiTweaksModule$Key2;
                    block8: {
                        block9: {
                            drawContext.method_51448().pushMatrix();
                            bl = AmbienceModule.b;
                            guiTweaksModule$Key2 = guiTweaksModule$Key;
                            if (bl) break block8;
                            if (guiTweaksModule$Key2 == GuiTweaksModule$Key.SURVIVAL) break block9;
                            guiTweaksModule$Part2 = GuiTweaksModule.partOrNull(GuiTweaksModule$Key.SURVIVAL);
                            if (bl) break block10;
                            if (guiTweaksModule$Part2 == null) break block9;
                            guiTweaksModule$Part2.applyTransform(drawContext);
                        }
                        guiTweaksModule$Key2 = guiTweaksModule$Key;
                    }
                    guiTweaksModule$Part2 = GuiTweaksModule.partOrNull(guiTweaksModule$Key2);
                }
                guiTweaksModule$Part = guiTweaksModule$Part2;
                if (bl) break block11;
                if (guiTweaksModule$Part == null) break block12;
                guiTweaksModule$Part = guiTweaksModule$Part2;
            }
            guiTweaksModule$Part.applyTransform(drawContext);
        }
    }

    public static void popOverlay(DrawContext drawContext) {
        drawContext.method_51448().popMatrix();
    }

    @Override
    protected void resetHudDefaults(String string) {
        boolean bl;
        block7: {
            String string2;
            block6: {
                bl = AmbienceModule.b;
                string2 = string;
                if (bl) break block6;
                if (string2 == null) break block7;
                string2 = b[7];
            }
            if (!string2.equals(string)) {
                return;
            }
        }
        for (GuiTweaksModule$Part guiTweaksModule$Part : this.parts.values()) {
            guiTweaksModule$Part.dx = 0;
            guiTweaksModule$Part.dy = 0;
            guiTweaksModule$Part.setHudScale(1.0f);
            if (!bl) continue;
        }
    }

    public boolean hideItemName() {
        boolean bl;
        block13: {
            block11: {
                GuiTweaksModule guiTweaksModule;
                boolean bl2;
                block12: {
                    block10: {
                        bl2 = AmbienceModule.b;
                        guiTweaksModule = this;
                        if (bl2) break block10;
                        if (!guiTweaksModule.isEnabled()) break block11;
                        guiTweaksModule = this;
                    }
                    if (bl2) break block12;
                    if (!guiTweaksModule.hudTweaks.getValue()) break block11;
                    guiTweaksModule = this;
                }
                bl = guiTweaksModule.noItemName.getValue();
                if (bl2) break block13;
                if (!bl) break block11;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    public boolean hideSelector() {
        boolean bl;
        block13: {
            block11: {
                GuiTweaksModule guiTweaksModule;
                boolean bl2;
                block12: {
                    block10: {
                        bl2 = AmbienceModule.b;
                        guiTweaksModule = this;
                        if (bl2) break block10;
                        if (!guiTweaksModule.isEnabled()) break block11;
                        guiTweaksModule = this;
                    }
                    if (bl2) break block12;
                    if (!guiTweaksModule.hudTweaks.getValue()) break block11;
                    guiTweaksModule = this;
                }
                bl = guiTweaksModule.hideSelector.getValue();
                if (bl2) break block13;
                if (!bl) break block11;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    public boolean hideActionBar() {
        boolean bl;
        block13: {
            block11: {
                BooleanSetting booleanSetting;
                boolean bl2;
                block12: {
                    GuiTweaksModule guiTweaksModule;
                    block10: {
                        bl2 = AmbienceModule.b;
                        guiTweaksModule = this;
                        if (bl2) break block10;
                        if (!guiTweaksModule.isEnabled()) break block11;
                        guiTweaksModule = this;
                    }
                    booleanSetting = guiTweaksModule.hudTweaks;
                    if (bl2) break block12;
                    if (!booleanSetting.getValue()) break block11;
                    booleanSetting = this.hideActionBar;
                }
                bl = booleanSetting.getValue();
                if (bl2) break block13;
                if (!bl) break block11;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    public int scoreboardBackgroundArgb() {
        return this.scoreboardBackground.getARGB();
    }

    public boolean scoreboardBackgroundActive() {
        boolean bl;
        block8: {
            block7: {
                GuiTweaksModule guiTweaksModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    guiTweaksModule = this;
                    if (bl2) break block6;
                    if (!guiTweaksModule.isEnabled()) break block7;
                    guiTweaksModule = this;
                }
                bl = guiTweaksModule.hudTweaks.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public double inventoryScaleValue() {
        double d;
        block11: {
            block9: {
                GuiTweaksModule guiTweaksModule;
                block10: {
                    boolean bl;
                    block8: {
                        bl = AmbienceModule.b;
                        guiTweaksModule = this;
                        if (bl) break block8;
                        if (!guiTweaksModule.isEnabled()) break block9;
                        guiTweaksModule = this;
                    }
                    if (bl) break block10;
                    if (!guiTweaksModule.inventoryTweaks.getValue()) break block9;
                    guiTweaksModule = this;
                }
                d = guiTweaksModule.inventoryScale.getValue();
                break block11;
            }
            d = 0.0;
        }
        return d;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static float inventoryAlpha() {
        GuiTweaksModule guiTweaksModule;
        block12: {
            GuiTweaksModule guiTweaksModule2;
            block13: {
                boolean bl;
                block11: {
                    block10: {
                        guiTweaksModule2 = instance;
                        bl = AmbienceModule.b;
                        guiTweaksModule = guiTweaksModule2;
                        if (bl) break block10;
                        if (guiTweaksModule == null) return 1.0f;
                        guiTweaksModule = guiTweaksModule2;
                    }
                    if (bl) break block11;
                    if (!guiTweaksModule.isEnabled()) return 1.0f;
                    guiTweaksModule = guiTweaksModule2;
                }
                if (bl) break block12;
                if (guiTweaksModule.inventoryTweaks.getValue()) break block13;
                return 1.0f;
            }
            guiTweaksModule = guiTweaksModule2;
        }
        float f = (float)guiTweaksModule.inventoryAlpha.getValue();
        return f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static float containerDim() {
        GuiTweaksModule guiTweaksModule;
        block12: {
            GuiTweaksModule guiTweaksModule2;
            block13: {
                boolean bl;
                block11: {
                    block10: {
                        guiTweaksModule2 = instance;
                        bl = AmbienceModule.b;
                        guiTweaksModule = guiTweaksModule2;
                        if (bl) break block10;
                        if (guiTweaksModule == null) return 1.0f;
                        guiTweaksModule = guiTweaksModule2;
                    }
                    if (bl) break block11;
                    if (!guiTweaksModule.isEnabled()) return 1.0f;
                    guiTweaksModule = guiTweaksModule2;
                }
                if (bl) break block12;
                if (guiTweaksModule.inventoryTweaks.getValue()) break block13;
                return 1.0f;
            }
            guiTweaksModule = guiTweaksModule2;
        }
        float f = (float)guiTweaksModule.containerDim.getValue();
        return f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static float tooltipAlpha() {
        GuiTweaksModule guiTweaksModule;
        block12: {
            GuiTweaksModule guiTweaksModule2;
            block13: {
                boolean bl;
                block11: {
                    block10: {
                        guiTweaksModule2 = instance;
                        bl = AmbienceModule.b;
                        guiTweaksModule = guiTweaksModule2;
                        if (bl) break block10;
                        if (guiTweaksModule == null) return 1.0f;
                        guiTweaksModule = guiTweaksModule2;
                    }
                    if (bl) break block11;
                    if (!guiTweaksModule.isEnabled()) return 1.0f;
                    guiTweaksModule = guiTweaksModule2;
                }
                if (bl) break block12;
                if (guiTweaksModule.inventoryTweaks.getValue()) break block13;
                return 1.0f;
            }
            guiTweaksModule = guiTweaksModule2;
        }
        float f = (float)guiTweaksModule.tooltipAlpha.getValue();
        return f;
    }

    @Override
    public List<DraggableHud> extraHuds() {
        boolean bl = AmbienceModule.b;
        if (!this.hudTweaks.getValue()) {
            return List.of();
        }
        ArrayList<DraggableHud> arrayList = new ArrayList<DraggableHud>(this.parts.size());
        for (GuiTweaksModule$Part guiTweaksModule$Part : this.parts.values()) {
            block8: {
                boolean bl2 = guiTweaksModule$Part.drawnThisSession;
                if (bl || !bl2) break block8;
                bl2 = arrayList.add(guiTweaksModule$Part);
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    @Override
    public void writeExtra(JsonObject jsonObject) {
        block21: {
            JsonObject jsonObject2;
            JsonObject jsonObject3;
            block26: {
                jsonObject3 = new JsonObject();
                Iterator<GuiTweaksModule$Part> iterator = this.parts.values().iterator();
                boolean bl = AmbienceModule.b;
                while (iterator.hasNext()) {
                    GuiTweaksModule$Part guiTweaksModule$Part;
                    JsonObject jsonObject4;
                    GuiTweaksModule$Part guiTweaksModule$Part2;
                    block24: {
                        block25: {
                            block23: {
                                GuiTweaksModule$Part guiTweaksModule$Part3;
                                block22: {
                                    guiTweaksModule$Part2 = iterator.next();
                                    if (bl) break block21;
                                    guiTweaksModule$Part3 = guiTweaksModule$Part2;
                                    if (bl) break block22;
                                    if (guiTweaksModule$Part3.dx != 0) break block23;
                                    guiTweaksModule$Part3 = guiTweaksModule$Part2;
                                }
                                if (guiTweaksModule$Part3.dy == 0) {
                                    continue;
                                }
                            }
                            jsonObject4 = new JsonObject();
                            guiTweaksModule$Part = guiTweaksModule$Part2;
                            if (bl) break block24;
                            if (guiTweaksModule$Part.dx == 0) break block25;
                            jsonObject4.addProperty(b[14], (Number)guiTweaksModule$Part2.dx);
                        }
                        guiTweaksModule$Part = guiTweaksModule$Part2;
                    }
                    if (guiTweaksModule$Part.dy != 0) {
                        jsonObject4.addProperty(b[19], (Number)guiTweaksModule$Part2.dy);
                    }
                    jsonObject3.add(guiTweaksModule$Part2.key.name(), (JsonElement)jsonObject4);
                    if (!bl) continue;
                }
                jsonObject2 = jsonObject3;
                if (bl) break block26;
                if (jsonObject2.size() == 0) break block21;
                jsonObject2 = jsonObject;
            }
            jsonObject2.add(b[10], (JsonElement)jsonObject3);
        }
    }

    @Override
    public void readExtra(JsonObject jsonObject) {
        JsonObject jsonObject2;
        block2: {
            block3: {
                boolean bl = AmbienceModule.b;
                jsonObject2 = jsonObject;
                if (bl) break block2;
                if (jsonObject2 != null) break block3;
                return;
            }
            this.readParts(jsonObject);
            jsonObject2 = jsonObject;
        }
        ItemHighlights.read(jsonObject2);
    }

    
    private void readParts(JsonObject jsonObject) {
        String[] stringArray;
        JsonObject jsonObject2;
        boolean bl;
        block30: {
            block31: {
                block29: {
                    JsonObject jsonObject3;
                    block28: {
                        bl = AmbienceModule.b;
                        jsonObject3 = jsonObject;
                        if (bl) break block28;
                        if (!jsonObject3.has(b[23])) break block29;
                        jsonObject2 = jsonObject;
                        if (bl) break block30;
                        jsonObject3 = jsonObject2.get(b[23]);
                    }
                    if (jsonObject3.isJsonObject()) break block31;
                }
                return;
            }
            stringArray = b;
            jsonObject2 = jsonObject.getAsJsonObject(stringArray[23]);
        }
        JsonObject jsonObject4 = jsonObject2;
        for (String string : jsonObject4.keySet()) {
            block33: {
                String string2;
                JsonObject jsonObject5;
                GuiTweaksModule$Part guiTweaksModule$Part;
                block32: {
                    JsonObject jsonObject6;
                    try {
                        guiTweaksModule$Part = this.parts.get((Object)GuiTweaksModule$Key.valueOf(string));
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        continue;
                    }
                    if (guiTweaksModule$Part == null) continue;
                    JsonObject jsonObject7 = jsonObject4;
                    if (!bl) {
                        if (!jsonObject7.get(string).isJsonObject()) continue;
                        jsonObject7 = jsonObject4.getAsJsonObject(string);
                    }
                    jsonObject5 = jsonObject6 = jsonObject7;
                    if (!bl) {
                        if (jsonObject5.has(b[25])) {
                            guiTweaksModule$Part.dx = jsonObject6.get(b[25]).getAsInt();
                        }
                        jsonObject5 = jsonObject6;
                    }
                    string2 = b[0];
                    if (!bl) {
                        if (jsonObject5.has(string2)) {
                            guiTweaksModule$Part.dy = jsonObject6.get(b[0]).getAsInt();
                        }
                        jsonObject5 = jsonObject6;
                        stringArray = b;
                        string2 = stringArray[5];
                    }
                    if (bl) break block32;
                    if (!jsonObject5.has(string2)) break block33;
                    jsonObject5 = jsonObject6;
                    string2 = b[18];
                }
                if (jsonObject5.get(string2).getAsBoolean()) {
                    guiTweaksModule$Part.setHidden(true);
                }
            }
            if (!bl) continue;
        }
    }

    private Boolean lambda$new$1() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = AmbienceModule.b;
                bl = this.part(GuiTweaksModule$Key.SCOREBOARD).hidden();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private Boolean lambda$new$0() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = AmbienceModule.b;
                bl = this.part(GuiTweaksModule$Key.SCOREBOARD).hidden();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[26];
                    var5_1 = 0;
                    var4_2 = "3C\t\u001eT\rA\u0013'-%C\t\u001eT\rA\u0013'-%C\n\u0010o2\u0004)$'6Q\b\u0013\u001fS\u001fA];';^[M\t6/wT\u001aI\u0018\u0006?S\u001f@\u0018=\u0003\u001fo?\u0003\u001fo?\u000f\u001fS\u001fA]\u0012!#S\u0014J]\u0011#%\u0010\u0004Y\u0014V\u00181-6H\u001f\u0004\u000e0#;_\u0005'[\tP\u000e\u000f\u001eT\rA\u0013'-%C[W\u001e2.2\u001c\u0014U\u0015P\u001c:,2H[F\u001c0)0H\u0014Q\u00137b8J\u001aG\u0014';\u000f\u0003U\u0014H\t:2wU\u000bE\u001e:6.\u00023B\b\u0012V\u001eI\u0018=6$\n\u001fo?\u0004)$'6Q\b\u0015\u0004Y\u0014V\u00181-6H\u001f\u0004\u001f2!<]\tK\b=&\u0006?S\u001f@\u0018=\u00023C@\u0004Y\u001aH\u0018s6?_[W\u001e<02X\u0014E\u000f7nwR\u0012@\u0018s-%\u001a\u0016K\u000b6b\u001fo?\u0004\r20#IW\u0004\u000f61>@\u001e\u0004\u0014=42T\u000fK\u000f:'$\u0016\u001eT\rA\u0013'-%C[P\u000f2,$J\u001aV\u0018=!.\u0014\u001fS\u001fA];-#X\u001aV] ';_\u0018P\u0012!\u0005'[\tP\u000e";
                    var6_3 = "3C\t\u001eT\rA\u0013'-%C\t\u001eT\rA\u0013'-%C\n\u0010o2\u0004)$'6Q\b\u0013\u001fS\u001fA];';^[M\t6/wT\u001aI\u0018\u0006?S\u001f@\u0018=\u0003\u001fo?\u0003\u001fo?\u000f\u001fS\u001fA]\u0012!#S\u0014J]\u0011#%\u0010\u0004Y\u0014V\u00181-6H\u001f\u0004\u000e0#;_\u0005'[\tP\u000e\u000f\u001eT\rA\u0013'-%C[W\u001e2.2\u001c\u0014U\u0015P\u001c:,2H[F\u001c0)0H\u0014Q\u00137b8J\u001aG\u0014';\u000f\u0003U\u0014H\t:2wU\u000bE\u001e:6.\u00023B\b\u0012V\u001eI\u0018=6$\n\u001fo?\u0004)$'6Q\b\u0015\u0004Y\u0014V\u00181-6H\u001f\u0004\u001f2!<]\tK\b=&\u0006?S\u001f@\u0018=\u00023C@\u0004Y\u001aH\u0018s6?_[W\u001e<02X\u0014E\u000f7nwR\u0012@\u0018s-%\u001a\u0016K\u000b6b\u001fo?\u0004\r20#IW\u0004\u000f61>@\u001e\u0004\u0014=42T\u000fK\u000f:'$\u0016\u001eT\rA\u0013'-%C[P\u000f2,$J\u001aV\u0018=!.\u0014\u001fS\u001fA];-#X\u001aV] ';_\u0018P\u0012!\u0005'[\tP\u000e".length();
                    var3_4 = 2;
                    var2_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var2_5;
                        v1 = var4_2.substring(v0, v0 + var3_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        var4_2 = "\u001eT\rA\u0013'-%C[p\n6#<I\u00023B";
                        var6_3 = "\u001eT\rA\u0013'-%C[p\n6#<I\u00023B".length();
                        var3_4 = 16;
                        var2_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var2_5;
                            v1 = var4_2.substring(v4, v4 + var3_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var8_6 = 0;
                do {
                    v6 = v6;
                    v8 = var8_6;
                    v9 = v6[v8];
                    switch (var8_6 % 7) {
                        case 0: {
                            v10 = 87;
                            break;
                        }
                        case 1: {
                            v10 = 58;
                            break;
                        }
                        case 2: {
                            v10 = 123;
                            break;
                        }
                        case 3: {
                            v10 = 36;
                            break;
                        }
                        case 4: {
                            v10 = 125;
                            break;
                        }
                        case 5: {
                            v10 = 83;
                            break;
                        }
                        default: {
                            v10 = 66;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var8_6;
lbl65:

                    v7 = v7;
                } while (v7 > var8_6);
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
            GuiTweaksModule.b = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 203455248928758645L;
        while (true)
        GuiTweaksModule.d = 251134481423554425L ^ var0_7;
        GuiTweaksModule.CLUSTER_CHILDREN = EnumSet.of(GuiTweaksModule$Key.HOTBAR, new GuiTweaksModule$Key[]{GuiTweaksModule$Key.HEALTH, GuiTweaksModule$Key.ARMOR, GuiTweaksModule$Key.HUNGER, GuiTweaksModule$Key.AIR, GuiTweaksModule$Key.MOUNT});
    }
}
