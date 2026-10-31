package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.gui.VectorIcons;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.ModsScreen$Row;
import dev.quelchi.aurora.gui.im.Motion;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.gui.lq.HudBridge;
import dev.quelchi.aurora.gui.shell.ModuleIcons;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.gui.shell.SearchIndex$Result;
import dev.quelchi.aurora.gui.shell.SearchIndex$Span;
import dev.quelchi.aurora.gui.shell.Switches;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.ExtraHuds;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.ShareCode;
import dev.quelchi.aurora.module.ShareCode$Decoded;
import dev.quelchi.aurora.module.setting.ActionSetting;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ChoiceSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.KeybindSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import dev.quelchi.aurora.module.setting.StringSetting;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.type.ImString;
import java.lang.invoke.LambdaMetafactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.client.MinecraftClient;

public final class ModsScreen
extends ImScreen {
    private static final float LIST = 300.0f;
    private static final float ROW = 44.0f;
    private static final float SET_H = 34.0f;
    private static final float TABS = 34.0f;
    private static final float FOOT = 62.0f;
    private static final Category[] CATS;
    private static Category category;
    private Module open;
    private boolean focusFind;
    private String asked = "";
    private Setting reveal;
    private Setting mark;
    private final ImString search = new ImString(48);
    private final Map<Setting, ImString> texts = new IdentityHashMap<Setting, ImString>();
    private KeybindSetting listening;
    private Setting sliding;
    private Setting typing;
    private final ImString num = new ImString(16);
    private final ImString hex = new ImString(10);
    private NumberSetting editing;
    private boolean focusNum;
    private boolean hexing;
    private boolean dirty;
    private Module shown;
    private String tab;
    private final Set<Setting> unfolded = Collections.newSetFromMap(new IdentityHashMap());
    private ColorSetting picking;
    private final float[] hsv = new float[3];
    private float alpha;
    private static final List<Integer> RECENT;
    private String code = "";
    private String said = "";
    private final ImString paste = new ImString(8192);
    private boolean copied;
    private boolean shareEsc;
    private boolean sayBad;
    private boolean shareOpen;
    private int asks;
    private int looks;
    private float shareX;
    private float shareY;
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;

    public ModsScreen() {
        super(b[52]);
    }

    public ModsScreen(Module module) {
        this();
        category = module.getCategory();
        this.open = module;
    }

    public static ModsScreen find() {
        ModsScreen modsScreen = new ModsScreen();
        modsScreen.focusFind = true;
        return modsScreen;
    }

    @Override
    protected boolean key(int n) {
        int n2;
        KeybindSetting keybindSetting;
        block22: {
            block23: {
                ModsScreen modsScreen;
                boolean bl;
                block20: {
                    block21: {
                        block19: {
                            block18: {
                                bl = ImScreen.a;
                                if (n != 256) break block18;
                                modsScreen = this;
                                if (bl) break block19;
                                if (modsScreen.listening != null) break block18;
                                modsScreen = this;
                                if (bl) break block19;
                                if (!modsScreen.shareOpen) break block18;
                                this.shareEsc = true;
                                return true;
                            }
                            modsScreen = this;
                        }
                        if (bl) break block20;
                        if (modsScreen.listening != null) break block21;
                        return false;
                    }
                    modsScreen = this;
                }
                keybindSetting = modsScreen.listening;
                n2 = n;
                if (bl) break block22;
                if (n2 != 256) break block23;
                n2 = -1;
                break block22;
            }
            n2 = n;
        }
        keybindSetting.setKey(n2);
        this.listening = null;
        ConfigManager.save();
        return true;
    }

    private boolean searching() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = ImScreen.a;
                bl = this.search.get().isBlank();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private void pick(SearchIndex$Result searchIndex$Result) {
        this.open = searchIndex$Result.module();
        this.reveal = this.mark = searchIndex$Result.setting();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String why(SearchIndex$Result searchIndex$Result) {
        StringBuilder stringBuilder;
        SearchIndex$Result searchIndex$Result2;
        boolean bl;
        block13: {
            bl = ImScreen.a;
            searchIndex$Result2 = searchIndex$Result;
            if (bl) break block13;
            if (searchIndex$Result2 == null) return null;
            searchIndex$Result2 = searchIndex$Result;
        }
        if (searchIndex$Result2.setting() == null) {
            return null;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        block10: for (List<SearchIndex$Span> list : searchIndex$Result.why()) {
            block14: {
                stringBuilder = stringBuilder2;
                if (bl) return stringBuilder.toString();
                if (bl) break block14;
                if (stringBuilder.length() <= 0) break block14;
                stringBuilder2.append(b[28]);
            }
            for (SearchIndex$Span searchIndex$Span : list) {
                stringBuilder2.append(searchIndex$Span.text());
                if (bl) continue block10;
                if (!bl) continue;
            }
            if (!bl) continue;
        }
        stringBuilder = stringBuilder2;
        return stringBuilder.toString();
    }

    @Override
    public boolean shutsOnMenuKey() {
        boolean bl = this.listening == null;
        return bl;
    }

    public void method_25432() {
        this.dropPick();
        super.method_25432();
    }

    private void dropPick() {
        block12: {
            ModsScreen modsScreen;
            block13: {
                boolean bl = ImScreen.a;
                modsScreen = this;
                if (bl) break block12;
                if (modsScreen.picking == null) break block13;
                modsScreen = this;
                if (bl) break block12;
                if (!modsScreen.dirty) break block13;
                modsScreen = this;
                if (bl) break block12;
                if (modsScreen.shown == null) break block13;
                this.shown.onSettingsChanged();
                ConfigManager.save();
            }
            modsScreen = this;
        }
        modsScreen.picking = null;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    protected void draw() {
        block116: {
            block112: {
                ModsScreen modsScreen;
                block113: {
                    block114: {
                        boolean bl;
                        boolean bl2;
                        block110: {
                            block111: {
                                String[] stringArray;
                                float f;
                                float f2;
                                float f3;
                                float f4;
                                block104: {
                                    void var15_21;
                                    ArrayList<Module> arrayList;
                                    List<Object> list;
                                    int n;
                                    block99: {
                                        int n2;
                                        float f5;
                                        block100: {
                                            Module module;
                                            block103: {
                                                ArrayList<Module> arrayList2;
                                                block101: {
                                                    block102: {
                                                        block97: {
                                                            ModsScreen modsScreen2;
                                                            block98: {
                                                                block92: {
                                                                    block95: {
                                                                        ModsScreen modsScreen3;
                                                                        block96: {
                                                                            boolean bl3;
                                                                            block94: {
                                                                                String string;
                                                                                block91: {
                                                                                    block115: {
                                                                                        boolean bl4;
                                                                                        block89: {
                                                                                            block90: {
                                                                                                block87: {
                                                                                                    block88: {
                                                                                                        block86: {
                                                                                                            block85: {
                                                                                                                Category[] categoryArray;
                                                                                                                int n3;
                                                                                                                block81: {
                                                                                                                    int n4;
                                                                                                                    String[] stringArray2;
                                                                                                                    String string2;
                                                                                                                    block83: {
                                                                                                                        int n5;
                                                                                                                        block84: {
                                                                                                                            n = this.searching();
                                                                                                                            String[] stringArray3 = new String[CATS.length];
                                                                                                                            n5 = -1;
                                                                                                                            bl2 = ImScreen.a;
                                                                                                                            n3 = 0;
                                                                                                                            while (n3 < CATS.length) {
                                                                                                                                block80: {
                                                                                                                                    block82: {
                                                                                                                                        stringArray3[n3] = ModsScreen.CATS[n3].displayName;
                                                                                                                                        if (bl2) break block80;
                                                                                                                                        categoryArray = CATS;
                                                                                                                                        if (bl2) break block81;
                                                                                                                                        if (categoryArray[n3] != category) break block82;
                                                                                                                                        n5 = n3;
                                                                                                                                    }
                                                                                                                                    ++n3;
                                                                                                                                }
                                                                                                                                if (!bl2) continue;
                                                                                                                            }
                                                                                                                            string2 = b[34];
                                                                                                                            stringArray2 = stringArray3;
                                                                                                                            n4 = n;
                                                                                                                            if (bl2) break block83;
                                                                                                                            if (n4 == 0) break block84;
                                                                                                                            n4 = -1;
                                                                                                                            break block83;
                                                                                                                        }
                                                                                                                        n4 = n5;
                                                                                                                    }
                                                                                                                    n3 = Menu.top(string2, stringArray2, n4, 0.0f);
                                                                                                                    if (bl2) break block85;
                                                                                                                    if (n3 < 0) break block86;
                                                                                                                    categoryArray = CATS;
                                                                                                                }
                                                                                                                category = categoryArray[n3];
                                                                                                                this.search.set("");
                                                                                                            }
                                                                                                            this.open = null;
                                                                                                        }
                                                                                                        f4 = Menu.x;
                                                                                                        f3 = Menu.y + 64.0f;
                                                                                                        f2 = Menu.w;
                                                                                                        f = Menu.h - 64.0f;
                                                                                                        ImGui.getWindowDrawList().addLine(f4 + 300.0f, f3, f4 + 300.0f, f3 + f, Ui.col(Im.LINE));
                                                                                                        f5 = f3 + 16.0f;
                                                                                                        this.typeToSearch();
                                                                                                        if (bl2) break block87;
                                                                                                        if (!this.focusFind) break block88;
                                                                                                        ImGui.setKeyboardFocusHere();
                                                                                                    }
                                                                                                    this.focusFind = false;
                                                                                                    stringArray = b;
                                                                                                    Menu.search(stringArray[36], stringArray[12], this.search, f4 + 16.0f, f5, 268.0f);
                                                                                                    f5 += 44.0f;
                                                                                                }
                                                                                                string = this.search.get().trim();
                                                                                                bl4 = string.isEmpty();
                                                                                                if (bl2) break block89;
                                                                                                if (bl4) break block90;
                                                                                                bl4 = true;
                                                                                                break block89;
                                                                                            }
                                                                                            bl4 = false;
                                                                                        }
                                                                                        n = bl4 ? 1 : 0;
                                                                                        List<Object> list2 = n != 0 ? SearchIndex.search(string) : List.of();
                                                                                        list = list2;
                                                                                        arrayList = new ArrayList<Module>();
                                                                                        if (n == 0) break block115;
                                                                                        for (SearchIndex$Result object : list) {
                                                                                            if (!bl2) {
                                                                                                arrayList.add(object.module());
                                                                                                if (!bl2) continue;
                                                                                                break;
                                                                                            }
                                                                                            break block91;
                                                                                        }
                                                                                        if (!bl2) break block91;
                                                                                    }
                                                                                    for (Module f7 : Aurora.INSTANCE.getModuleManager().getModules()) {
                                                                                        block93: {
                                                                                            if (bl2) break block92;
                                                                                            if (f7.getCategory() != category) break block93;
                                                                                            arrayList.add(f7);
                                                                                        }
                                                                                        if (!bl2) continue;
                                                                                    }
                                                                                }
                                                                                bl3 = string.equals(this.asked);
                                                                                if (bl2) break block94;
                                                                                if (bl3) break block92;
                                                                                modsScreen3 = this;
                                                                                if (bl2) break block95;
                                                                                modsScreen3.asked = string;
                                                                                bl3 = list.isEmpty();
                                                                            }
                                                                            if (bl3) break block96;
                                                                            this.pick((SearchIndex$Result)list.get(0));
                                                                            if (!bl2) break block92;
                                                                        }
                                                                        modsScreen3 = this;
                                                                    }
                                                                    this.mark = null;
                                                                    modsScreen3.reveal = null;
                                                                }
                                                                modsScreen2 = this;
                                                                if (bl2) break block97;
                                                                if (modsScreen2.open == null) break block98;
                                                                n2 = n;
                                                                if (bl2) break block99;
                                                                if (n2 != 0) break block100;
                                                                n2 = arrayList.contains(this.open) ? 1 : 0;
                                                                if (bl2) break block99;
                                                                if (n2 != 0) break block100;
                                                            }
                                                            modsScreen2 = this;
                                                        }
                                                        arrayList2 = arrayList;
                                                        if (bl2) break block101;
                                                        if (!arrayList2.isEmpty()) break block102;
                                                        module = null;
                                                        break block103;
                                                    }
                                                    arrayList2 = arrayList.get(0);
                                                }
                                                module = (Module)((Object)arrayList2);
                                            }
                                            modsScreen2.open = module;
                                        }
                                        ImGui.setCursorScreenPos((float)(f4 + 1.0f), (float)f5);
                                        ImGui.pushStyleColor((int)3, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                                        ImGui.pushStyleVar((int)8, (float)0.0f);
                                        stringArray = b;
                                        n2 = ImGui.beginChild((String)stringArray[44], (float)298.0f, (float)(f3 + f - f5 - 1.0f - 62.0f), (boolean)false) ? 1 : 0;
                                    }
                                    float f6 = ImGui.getCursorScreenPosY();
                                    boolean bl5 = false;
                                    while (var15_21 < arrayList.size()) {
                                        block107: {
                                            block108: {
                                                block109: {
                                                    SearchIndex$Result searchIndex$Result;
                                                    block106: {
                                                        block105: {
                                                            if (bl2) break block104;
                                                            if (n == 0) break block105;
                                                            searchIndex$Result = (SearchIndex$Result)list.get((int)var15_21);
                                                            break block106;
                                                        }
                                                        searchIndex$Result = null;
                                                    }
                                                    SearchIndex$Result searchIndex$Result2 = searchIndex$Result;
                                                    if (bl2) break block107;
                                                    if (!this.row((Module)arrayList.get((int)var15_21), ModsScreen.why(searchIndex$Result2), f4 + 12.0f, f6, 276.0f)) break block108;
                                                    if (searchIndex$Result2 == null) break block109;
                                                    this.pick(searchIndex$Result2);
                                                    if (!bl2) break block108;
                                                }
                                                this.mark = null;
                                                this.reveal = null;
                                            }
                                            f6 += 44.0f;
                                            ++var15_21;
                                        }
                                        if (!bl2) continue;
                                    }
                                    if (arrayList.isEmpty()) {
                                        Ui.text(Im.sub, f4 + 24.0f, f6 + 4.0f, Im.MUTE, b[2]);
                                    }
                                    ImGui.setCursorScreenPos((float)f4, (float)(f6 + 16.0f));
                                    ImGui.dummy((float)1.0f, (float)1.0f);
                                    ImGui.endChild();
                                    ImGui.popStyleVar();
                                    ImGui.popStyleColor();
                                }
                                float f5 = f3 + f - 16.0f - 34.0f;
                                ImGui.getWindowDrawList().addLine(f4 + 12.0f, f5 - 12.0f, f4 + 300.0f - 12.0f, f5 - 12.0f, Ui.col(Im.LINE));
                                stringArray = b;
                                bl = Menu.quiet(stringArray[24], stringArray[13], f4 + 16.0f, f5, 268.0f, 34.0f);
                                this.shareOpen = false;
                                if (bl2) break block110;
                                if (this.open == null) break block111;
                                this.options(this.open, f4 + 300.0f, f3, f2 - 300.0f, f);
                            }
                            this.shareEsc = false;
                            if (bl2) break block116;
                        }
                        if (!bl) break block112;
                        modsScreen = this;
                        if (bl2) break block113;
                        if (modsScreen.open == null) break block114;
                        modsScreen = new ModsScreen(this.open);
                        break block113;
                    }
                    modsScreen = new ModsScreen();
                }
                HudBridge.openEditor(modsScreen);
            }
            Menu.end();
        }
    }

    private static String icon(Module module) {
        Object object;
        block4: {
            String string;
            block5: {
                String[] stringArray = b;
                string = stringArray[3] + ModuleIcons.slug(module);
                boolean bl = ImScreen.a;
                object = string;
                if (bl) break block4;
                if (VectorIcons.texture((String)object, 40) != null) break block5;
                object = b[51];
                break block4;
            }
            object = string;
        }
        return object;
    }

    
    private boolean row(Module module, String string, float f, float f2, float f3) {
        float f4;
        ImDrawList imDrawList;
        boolean bl;
        boolean bl2;
        boolean bl3;
        block29: {
            block27: {
                float f5;
                block28: {
                    float f6;
                    float f7;
                    block26: {
                        block25: {
                            float f8;
                            block24: {
                                block23: {
                                    bl3 = module.isEnabled();
                                    ImGui.setCursorScreenPos((float)f, (float)f2);
                                    ImGui.setNextItemAllowOverlap();
                                    String[] stringArray = b;
                                    bl2 = ImGui.invisibleButton((String)(stringArray[9] + module.getName()), (float)f3, (float)44.0f);
                                    bl = ImScreen.a;
                                    f8 = Ui.hot();
                                    f5 = Motion.to(stringArray[43] + module.getName(), bl3, 0.15f);
                                    imDrawList = ImGui.getWindowDrawList();
                                    if (bl) break block23;
                                    if (module != this.open) break block24;
                                    imDrawList.addRectFilled(f, f2, f + f3, f2 + 44.0f, Ui.col(Ui.tint(0.1f)), 10.0f);
                                }
                                if (!bl) break block25;
                            }
                            f7 = f8;
                            f6 = 0.01f;
                            if (bl) break block26;
                            if (f7 > f6) {
                                imDrawList.addRectFilled(f, f2, f + f3, f2 + 44.0f, Ui.wash(0.04f * f8), 10.0f);
                            }
                        }
                        Ui.icon(ModsScreen.icon(module), f + 15.0f, f2 + 14.0f, 16.0f, Ui.mix(Im.MUTE, Ui.soft(), f5));
                        f7 = f + f3 - 12.0f;
                        f6 = 38.0f;
                    }
                    f4 = f7 - f6;
                    if (bl) break block27;
                    imDrawList.pushClipRect(f, f2, f4 - 8.0f, f2 + 44.0f, true);
                    if (string != null) break block28;
                    Ui.text(Im.sora14, f + 46.0f, Ui.mid(Im.sora14, f2, 44.0f), Ui.mix(Im.MUTE, Im.INK, f5), module.getName());
                    if (!bl) break block29;
                }
                Ui.text(Im.sora14, f + 46.0f, Ui.mid(Im.sora14, f2 + 3.0f, 20.0f), Ui.mix(Im.MUTE, Im.INK, f5), module.getName());
            }
            Ui.text(Im.label, f + 46.0f, Ui.mid(Im.label, f2 + 22.0f, 16.0f), Im.MUTE, ModsScreen.fit(Im.label, string, f4 - 8.0f - f - 46.0f));
        }
        imDrawList.popClipRect();
        boolean bl4 = Ui.toggle(b[49] + module.getName(), f4, f2 + 11.0f, 38.0f, 22.0f, bl3);
        if (!bl) {
            if (bl4) {
                module.toggle();
                ConfigManager.save();
                return false;
            }
            bl4 = bl2;
        }
        if (!bl) {
            if (bl4) {
                this.open = module;
            }
            bl4 = bl2;
        }
        return bl4;
    }

    private float fold(Setting setting) {
        return Motion.to(b[31] + System.identityHashCode(setting), this.unfolded.contains(setting), 0.15f);
    }

    private float height(Setting setting) {
        float f;
        block9: {
            float f2;
            block10: {
                boolean bl = ImScreen.a;
                if (!setting.isVisible()) {
                    return 0.0f;
                }
                f2 = 34.0f;
                float f3 = setting.hasChildren() ? this.fold(setting) : 0.0f;
                float f4 = f3;
                f = f4;
                if (bl) break block9;
                if (!(f > 0.0f)) break block10;
                for (Setting setting2 : setting.getChildren()) {
                    f = f2;
                    if (!bl) {
                        f2 = f + f4 * this.height(setting2);
                        if (!bl) continue;
                    }
                    break block9;
                }
            }
            f = f2;
        }
        return f;
    }

    private float collect(Setting setting, int n, float f, float f2, List<ModsScreen$Row> list) {
        float f3;
        block13: {
            float f4;
            block14: {
                float f5;
                boolean bl;
                block11: {
                    block12: {
                        bl = ImScreen.a;
                        if (!setting.isVisible()) {
                            return f;
                        }
                        f4 = f + this.height(setting);
                        f5 = f + 34.0f;
                        f3 = f;
                        if (bl) break block11;
                        if (!(f3 < f2)) break block12;
                        list.add(new ModsScreen$Row(setting, n, f, f2));
                    }
                    f3 = f4;
                }
                if (bl) break block13;
                if (!(f3 > f5)) break block14;
                for (Setting setting2 : setting.getChildren()) {
                    f3 = this.collect(setting2, n + 1, f5, Math.min(f2, f4), list);
                    if (!bl) {
                        f5 = f3;
                        if (!bl) continue;
                    }
                    break block13;
                }
            }
            f3 = f4;
        }
        return f3;
    }

    private static String tabOf(Setting setting) {
        String string;
        block6: {
            Setting setting2;
            block4: {
                block5: {
                    boolean bl = ImScreen.a;
                    setting2 = setting;
                    if (bl) break block4;
                    if (setting2.getTab() != null) break block5;
                    string = b[42];
                    break block6;
                }
                setting2 = setting;
            }
            string = setting2.getTab();
        }
        return string;
    }

    
    private void options(Module var1_1, float var2_2, float var3_3, float var4_4, float var5_5) {
        block161: {
            block160: {
                block155: {
                    block156: {
                        block159: {
                            block158: {
                                block157: {
                                    block153: {
                                        block147: {
                                            block154: {
                                                block142: {
                                                    block136: {
                                                        block133: {
                                                            block135: {
                                                                block134: {
                                                                    block131: {
                                                                        block132: {
                                                                            block128: {
                                                                                block129: {
                                                                                    block130: {
                                                                                        block127: {
                                                                                            block125: {
                                                                                                block126: {
                                                                                                    block124: {
                                                                                                        block122: {
                                                                                                            block123: {
                                                                                                                block118: {
                                                                                                                    block116: {
                                                                                                                        block117: {
                                                                                                                            var6_6 = ImScreen.a;
                                                                                                                            v0 = var1_1;
                                                                                                                            if (var6_6) break block116;
                                                                                                                            if (v0 == this.shown) break block117;
                                                                                                                            this.dropPick();
                                                                                                                            this.shown = var1_1;
                                                                                                                            this.unfolded.clear();
                                                                                                                            this.tab = null;
                                                                                                                            this.editing = null;
                                                                                                                        }
                                                                                                                        v0 = var1_1;
                                                                                                                    }
                                                                                                                    var7_7 = ModsScreen.nested(v0);
                                                                                                                    var8_8 = new ArrayList<String>();
                                                                                                                    for (Setting v3 : var1_1.getSettings()) {
                                                                                                                        block120: {
                                                                                                                            block121: {
                                                                                                                                block119: {
                                                                                                                                    if (var6_6) break block118;
                                                                                                                                    var10_10 = v3;
                                                                                                                                    v4 = var7_7.contains(var10_10);
                                                                                                                                    if (var6_6) break block119;
                                                                                                                                    if (v4) break block120;
                                                                                                                                    v6 = var8_8;
                                                                                                                                    v7 = ModsScreen.tabOf(var10_10);
                                                                                                                                    if (var6_6) break block121;
                                                                                                                                    v4 = v6.contains(v7);
                                                                                                                                }
                                                                                                                                if (v4) break block120;
                                                                                                                                v6 = var8_8;
                                                                                                                                v7 = ModsScreen.tabOf(var10_10);
                                                                                                                            }
                                                                                                                            v6.add(v7);
                                                                                                                        }
                                                                                                                        if (!var6_6) continue;
                                                                                                                    }
                                                                                                                    v3 = this.reveal;
                                                                                                                }
                                                                                                                v11 = v3 == null ? null : SearchIndex.chain(var1_1, this.reveal);
                                                                                                                var9_9 = v11;
                                                                                                                if (var6_6) break block122;
                                                                                                                if (var9_9 != null) break block123;
                                                                                                                this.reveal = null;
                                                                                                                if (!var6_6) break block124;
                                                                                                            }
                                                                                                            this.tab = ModsScreen.tabOf((Setting)var9_9.get(0));
                                                                                                        }
                                                                                                        this.unfolded.addAll(var9_9.subList(0, var9_9.size() - 1));
                                                                                                    }
                                                                                                    v16 = var8_8.size();
                                                                                                    if (var6_6) break block125;
                                                                                                    if (v16 <= 1) break block126;
                                                                                                    v16 = 1;
                                                                                                    break block125;
                                                                                                }
                                                                                                v16 = 0;
                                                                                            }
                                                                                            var10_11 = v16;
                                                                                            if (var6_6) break block127;
                                                                                            if (var10_11 == 0) {
                                                                                            }
                                                                                            this.tab = null;
                                                                                        }
                                                                                        if (!var6_6) break block128;
lbl121:

                                                                                        v21 = this;
                                                                                        if (var6_6) break block129;
                                                                                        if (v21.tab == null) break block130;
                                                                                        if (var8_8.contains(this.tab)) break block128;
                                                                                    }
                                                                                    v21 = this;
                                                                                }
                                                                                v21.tab = (String)var8_8.get(0);
                                                                            }
                                                                            var11_12 = ImGui.getWindowDrawList();
                                                                            var12_13 = var2_2 + 32.0f;
                                                                            var13_14 = var2_2 + var4_4 - 32.0f;
                                                                            var14_15 = var3_3 + 24.0f;
                                                                            var15_16 = var13_14 - 38.0f;
                                                                            v26 = ModsScreen.b[22] + var1_1.getName();
                                                                            if (var6_6) break block131;
                                                                            if (!Ui.toggle((String)v26, var15_16, var14_15 + 6.0f, 38.0f, 22.0f, var1_1.isEnabled())) break block132;
                                                                            var1_1.toggle();
                                                                            ConfigManager.save();
                                                                        }
                                                                        var15_16 -= 14.0f;
                                                                        var28_17 = ModsScreen.b;
                                                                        v26 = var28_17[56];
                                                                    }
                                                                    var28_17 = ModsScreen.b;
                                                                    var16_18 = Menu.ghost((String)v26, var28_17[21], var15_16, var14_15, 34.0f);
                                                                    if (!var6_6) {
                                                                        if (Menu.ghost(var28_17[20], var28_17[47], var15_16 -= Menu.ghostWidth(var28_17[46], var28_17[59]) + 6.0f, var14_15, 34.0f)) {
                                                                            this.code = "";
                                                                            var17_19 = ++this.asks;
                                                                            ShareCode.share(var1_1.getName(), ConfigManager.shareSettings(var1_1), (Consumer<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$options$0(int java.lang.String ), (Ljava/lang/String;)V)((ModsScreen)this, (int)var17_19));
                                                                            this.paste.set("");
                                                                            this.said = "";
                                                                            this.copied = false;
                                                                            this.shareX = var15_16 + 8.0f;
                                                                            this.shareY = var14_15 + 40.0f;
                                                                            ImGui.openPopup((String)var28_17[4]);
                                                                        }
                                                                        var28_17 = ModsScreen.b;
                                                                        var15_16 -= Menu.ghostWidth(var28_17[53], var28_17[4]) + 6.0f;
                                                                    }
                                                                    var17_19 = 0;
                                                                    if (var6_6) break block133;
                                                                    if (var1_1 instanceof DraggableHud) break block134;
                                                                    v30 = var1_1 instanceof ExtraHuds;
                                                                    if (var6_6) break block135;
                                                                    if (v30 == 0) break block136;
                                                                }
                                                                v30 = Menu.ghost(ModsScreen.b[37], ModsScreen.b[40], var15_16, var14_15, 34.0f);
                                                            }
                                                            var17_19 = v30;
                                                        }
                                                        var28_17 = ModsScreen.b;
                                                        var15_16 -= Menu.ghostWidth(var28_17[11], var28_17[40]) + 6.0f;
                                                    }
                                                    var11_12.pushClipRect(var12_13, var14_15 - 4.0f, var15_16 - 8.0f, var14_15 + 38.0f, true);
                                                    Ui.text(Im.head, var12_13, Ui.mid(Im.head, var14_15, 34.0f), Im.INK, var1_1.getName());
                                                    var11_12.popClipRect();
                                                    var18_20 = var14_15 + 34.0f + 14.0f;
                                                    if (var10_11 != 0) {
                                                        block137: {
                                                            var19_21 = var12_13;
                                                            for (Object var21_25 : var8_8) {
                                                                block140: {
                                                                    block141: {
                                                                        block138: {
                                                                            block139: {
                                                                                var22_27 = Ui.width(Im.sora12, (String)var21_25);
                                                                                v34 = var19_21 - 4.0f;
                                                                                v35 = var18_20;
                                                                                if (var6_6) break block137;
                                                                                ImGui.setCursorScreenPos((float)v34, (float)v35);
                                                                                v36 = var21_25;
                                                                                if (var6_6) break block138;
                                                                                if (!ImGui.invisibleButton((String)(ModsScreen.b[16] + (String)v36), (float)(var22_27 + 8.0f), (float)34.0f)) break block139;
                                                                                this.tab = var21_25;
                                                                            }
                                                                            v36 = var21_25;
                                                                        }
                                                                        var23_31 = v36.equals(this.tab);
                                                                        v40 = Im.sora12;
                                                                        v41 = var19_21;
                                                                        v42 = Ui.mid(Im.sora12, var18_20, 34.0f);
                                                                        v43 = var23_31 != false ? Im.INK : Ui.mix(Im.MUTE, Im.SUB, Ui.hot());
                                                                        Ui.text(v40, v41, v42, v43, (String)var21_25);
                                                                        if (var6_6) break block140;
                                                                        if (!var23_31) break block141;
                                                                        Ui.underline(ModsScreen.b[14] + var1_1.getName(), var2_2, var19_21 - var2_2, var22_27, var18_20 + 34.0f);
                                                                    }
                                                                    var19_21 += var22_27 + 22.0f;
                                                                }
                                                                if (!var6_6) continue;
                                                            }
                                                            var11_12.addLine(var12_13, var18_20 + 34.0f - 0.5f, var13_14, var18_20 + 34.0f - 0.5f, Ui.col(Im.LINE));
                                                            v34 = var18_20;
                                                            v35 = 38.0f;
                                                        }
                                                        var18_20 = v34 + v35;
                                                    }
                                                    var19_22 = new ArrayList<ModsScreen$Row>();
                                                    var20_24 = 0.0f;
                                                    for (Setting var22_28 : var1_1.getSettings()) {
                                                        block144: {
                                                            block146: {
                                                                block145: {
                                                                    block143: {
                                                                        if (var6_6) break block142;
                                                                        v47 = var7_7.contains(var22_28);
                                                                        if (var6_6) break block143;
                                                                        if (v47 != 0) break block144;
                                                                        v47 = var10_11;
                                                                    }
                                                                    if (var6_6) break block145;
                                                                    if (v47 == 0) break block146;
                                                                    v47 = (int)ModsScreen.tabOf(var22_28).equals(this.tab);
                                                                }
                                                                if (v47 == 0) break block144;
                                                            }
                                                            var20_24 = this.collect(var22_28, 0, var20_24, 3.4028235E38f, var19_22);
                                                        }
                                                        if (!var6_6) continue;
                                                    }
                                                    ImGui.setCursorScreenPos((float)(var2_2 + 1.0f), (float)var18_20);
                                                    ImGui.pushStyleColor((int)3, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                                                    ImGui.pushStyleVar((int)8, (float)0.0f);
                                                    var28_17 = ModsScreen.b;
                                                    ImGui.beginChild((String)var28_17[29], (float)(var4_4 - 2.0f), (float)(var3_3 + var5_5 - 8.0f - var18_20), (boolean)false);
                                                }
                                                var21_26 = ImGui.getCursorScreenPosY() + 2.0f;
                                                var11_12 = ImGui.getWindowDrawList();
                                                for (var22_29 = 0; var22_29 < var19_22.size(); ++var22_29) {
                                                    block151: {
                                                        block152: {
                                                            block150: {
                                                                block148: {
                                                                    block149: {
                                                                        var23_32 = (ModsScreen$Row)var19_22.get(var22_29);
                                                                        v52 = var21_26;
                                                                        v53 = var23_32.y();
                                                                        if (var6_6) break block147;
                                                                        var24_33 = v52 + v53;
                                                                        v54 = var23_32.s();
                                                                        v55 = this;
                                                                        if (var6_6) break block148;
                                                                        if (v54 != v55.reveal) break block149;
                                                                        ImGui.setScrollY((float)Math.max(0.0f, var23_32.y() - 68.0f));
                                                                        this.reveal = null;
                                                                    }
                                                                    v54 = var23_32.s();
                                                                    v55 = this;
                                                                }
                                                                if (v54 != v55.mark || !this.searching()) break block150;
                                                                var11_12.addRectFilled(var12_13 - 8.0f, var24_33, var13_14 + 8.0f, var24_33 + 34.0f, Ui.col(Ui.tint(0.1f)), 8.0f);
                                                            }
                                                            ImGui.pushClipRect((float)(var12_13 - 4.0f), (float)var24_33, (float)(var13_14 + 4.0f), (float)Math.min(var24_33 + 34.0f, var21_26 + var23_32.clip()), (boolean)true);
                                                            var25_34 = var12_13;
                                                            for (var26_35 = 0; var26_35 < var23_32.depth(); ++var26_35) {
                                                                var27_36 = var12_13 + 4.0f + (float)(var26_35 * 18);
                                                                var11_12.addRectFilled(var27_36, var24_33, var27_36 + 2.0f, var24_33 + 34.0f, Ui.col(Ui.tint(0.3f)));
                                                                v52 = var27_36;
                                                                v53 = 16.0f;
                                                                if (!var6_6) {
                                                                    var25_34 = v52 + v53;
                                                                    if (!var6_6) continue;
                                                                }
                                                                break block147;
                                                            }
                                                            ImGui.pushID((int)var22_29);
                                                            v60 = this;
                                                            v61 = var1_1;
                                                            v62 = var23_32.s();
                                                            v63 = var25_34;
                                                            v64 = var24_33;
                                                            v65 = var13_14;
                                                            v66 = var23_32.depth();
                                                            if (var6_6) break block151;
                                                            if (v66 <= false) break block152;
                                                            v66 = true;
                                                            break block151;
                                                        }
                                                        v66 = false;
                                                    }
                                                    v60.setting(v61, v62, v63, v64, v65, v66);
                                                    ImGui.popID();
                                                    ImGui.popClipRect();
                                                    if (!var6_6) continue;
                                                }
                                                v68 = var19_22.size();
                                                if (var6_6) break block153;
                                                if (v68 >= 2) break block154;
                                                v68 = var10_11;
                                                if (var6_6) break block153;
                                                if (v68 != 0) break block154;
                                                var28_17 = ModsScreen.b;
                                                var22_30 = var28_17[26];
                                                Ui.text(Im.label, var2_2 + (var4_4 - Ui.width(Im.label, var22_30)) / 2.0f, var21_26 + var20_24 + 24.0f, Im.MUTE, var22_30);
                                            }
                                            ImGui.setCursorScreenPos((float)var2_2, (float)(var21_26 + var20_24 + 9.0f));
                                            v52 = 1.0f;
                                            v53 = 1.0f;
                                        }
                                        ImGui.dummy((float)v52, (float)v53);
                                        ImGui.endChild();
                                        ImGui.popStyleVar();
                                        ImGui.popStyleColor();
                                        if (var6_6) break block155;
                                        v68 = (int)var16_18;
                                    }
                                    if (v68 == 0) break block156;
                                    v73 = var1_1;
                                    v74 = var10_11;
                                    if (var6_6) break block157;
                                    if (v74 == 0) break block158;
                                    v77 = ModsScreen.b[32];
                                    if (var6_6) break block159;
                                    v74 = (int)v77.equals(this.tab);
                                }
                                if (v74 != 0) break block158;
                                v77 = this.tab;
                                break block159;
                            }
                            v77 = null;
                        }
                        v73.resetDefaults(v77);
                        var1_1.onSettingsChanged();
                        ConfigManager.save();
                    }
                    v81 = this;
                    if (var6_6) break block160;
                    v81.share(var1_1);
                }
                if (var17_19 == 0) break block161;
                v81 = new ModsScreen(var1_1);
            }
            HudBridge.openEditor(v81);
        }
    }

    
    private static String fit(ImFont imFont, String string, float f) {
        boolean bl = ImScreen.a;
        float f2 = Ui.width(imFont, string) - f;
        float f3 = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
        if (bl) {
            return string + "\u2026";
        } else if (f3 <= 0) {
            return string;
        }
        do {
            f3 = string.length();
            if (f3 <= true || !(Ui.width(imFont, string + "\u2026") > f)) return string + "\u2026";
            string = string.substring(0, string.length() - 1);
        } while (!bl);
        return string + "\u2026";
    }

    private void share(Module module) {
        block30: {
            block31: {
                float f;
                float f2;
                block43: {
                    float f3;
                    float f4;
                    float f5;
                    float f6;
                    block39: {
                        float[] fArray;
                        float f7;
                        float f8;
                        ImFont imFont;
                        block41: {
                            block40: {
                                block42: {
                                    String string;
                                    String[] stringArray;
                                    boolean bl;
                                    block37: {
                                        block38: {
                                            String string2;
                                            float[] fArray2;
                                            float f9;
                                            float f10;
                                            ImFont imFont2;
                                            float f11;
                                            String string3;
                                            block35: {
                                                block36: {
                                                    String string4;
                                                    ImDrawList imDrawList;
                                                    block34: {
                                                        block33: {
                                                            block32: {
                                                                float f12 = 290.0f;
                                                                bl = ImScreen.a;
                                                                f6 = f12 - 32.0f;
                                                                if (bl) break block30;
                                                                ImGui.setNextWindowPos((float)(this.shareX - f12), (float)(this.shareY + Ui.drop(b[4])));
                                                                ImGui.setNextWindowSize((float)f12, (float)0.0f);
                                                                ImGui.pushStyleVar((int)2, (float)16.0f, (float)16.0f);
                                                                ImGui.pushStyleVar((int)9, (float)14.0f);
                                                                ImGui.pushStyleColor((int)4, (float)Im.POP[0], (float)Im.POP[1], (float)Im.POP[2], (float)Im.POP[3]);
                                                                this.shareOpen = ImGui.beginPopup((String)b[4]);
                                                                if (!this.shareOpen) break block31;
                                                                Ui.lift();
                                                                if (!this.shareEsc) break block32;
                                                                ImGui.closeCurrentPopup();
                                                            }
                                                            imDrawList = ImGui.getWindowDrawList();
                                                            f5 = ImGui.getCursorScreenPosX();
                                                            f4 = ImGui.getCursorScreenPosY();
                                                            stringArray = b;
                                                            Ui.text(Im.sora16, f5, Ui.mid(Im.sora16, f4, 20.0f), Im.INK, ModsScreen.fit(Im.sora16, stringArray[30] + module.getName(), f6));
                                                            f3 = f4 + 32.0f;
                                                            if (!this.copied) break block33;
                                                            string4 = stringArray[50];
                                                            break block34;
                                                        }
                                                        stringArray = b;
                                                        string4 = stringArray[0];
                                                    }
                                                    string3 = string4;
                                                    stringArray = b;
                                                    f11 = Menu.buttonWidth(string3, stringArray[54]);
                                                    Ui.fill(imDrawList, f5, f3, f5 + f6, f3 + 44.0f, Ui.wash(0.05f), 10.0f);
                                                    imFont2 = Im.sora14;
                                                    f10 = f5 + 14.0f;
                                                    f9 = Ui.mid(Im.sora14, f3, 44.0f);
                                                    fArray2 = this.code.isEmpty() ? Im.MUTE : Im.INK;
                                                    string2 = this.code;
                                                    if (bl) break block35;
                                                    if (!string2.isEmpty()) break block36;
                                                    string2 = b[8];
                                                    break block35;
                                                }
                                                string2 = ModsScreen.fit(Im.sora14, this.code, f6 - 14.0f - f11 - 17.0f);
                                            }
                                            Ui.text(imFont2, f10, f9, fArray2, string2);
                                            string = string3;
                                            if (bl) break block37;
                                            if (!Menu.button(string + b[6], b[35], f5 + f6 - 7.0f - f11, f3 + 7.0f, f11, 30.0f, true)) break block38;
                                            string = this.code;
                                            if (bl) break block37;
                                            if (string.isEmpty()) break block38;
                                            MinecraftClient.method_1551().field_1774.method_1455(this.code);
                                            this.copied = true;
                                        }
                                        stringArray = b;
                                        Ui.text(Im.sora12, f5, Ui.mid(Im.sora12, f3 += 62.0f, 16.0f), Im.SUB, stringArray[57]);
                                        f3 += 24.0f;
                                        string = stringArray[5];
                                    }
                                    float f13 = Menu.ghostWidth(string, null);
                                    ImGui.setCursorScreenPos((float)f5, (float)f3);
                                    ImGui.setNextItemWidth((float)(f6 - f13 - 6.0f));
                                    ImGui.pushStyleVar((int)11, (float)12.0f, (float)9.5f);
                                    ImGui.pushStyleVar((int)13, (float)1.0f);
                                    ImGui.pushFont((ImFont)Im.sub);
                                    stringArray = b;
                                    ImGui.inputTextWithHint((String)stringArray[27], (String)stringArray[55], (ImString)this.paste);
                                    boolean bl2 = Ui.entered();
                                    if (bl) break block42;
                                    ImGui.popFont();
                                    ImGui.popStyleVar((int)2);
                                    bl2 |= Menu.ghost(stringArray[33], null, f5 + f6, f3, 32.0f);
                                    if (bl2) {
                                        this.apply(this.paste.get());
                                    }
                                    f2 = f3;
                                    f = 32.0f;
                                    if (bl) break block43;
                                    f3 = f2 + f;
                                }
                                if (this.said.isEmpty()) break block39;
                                imFont = Im.label;
                                f8 = f5;
                                f7 = f3 + 8.0f;
                                if (!this.sayBad) break block40;
                                fArray = Im.DANGER;
                                break block41;
                            }
                            fArray = Im.MUTE;
                        }
                        Ui.text(imFont, f8, f7, fArray, this.said);
                        f3 += 24.0f;
                    }
                    ImGui.setCursorScreenPos((float)f5, (float)f4);
                    f2 = f6;
                    f = f3 - f4;
                }
                ImGui.dummy((float)f2, (float)f);
                ImGui.endPopup();
            }
            ImGui.popStyleColor();
            ImGui.popStyleVar((int)3);
        }
    }

    private void apply(String string) {
        ShareCode$Decoded shareCode$Decoded;
        block16: {
            String string2;
            block15: {
                boolean bl;
                boolean bl2;
                block14: {
                    block13: {
                        shareCode$Decoded = ShareCode.decode(string);
                        bl2 = ImScreen.a;
                        String string3 = shareCode$Decoded == null ? ShareCode.shortCode(string) : null;
                        string2 = string3;
                        ModsScreen modsScreen = this;
                        if (shareCode$Decoded != null || string2 != null) break block13;
                        bl = true;
                        break block14;
                    }
                    bl = false;
                }
                modsScreen.sayBad = bl;
                if (bl2) break block15;
                if (string2 == null) break block16;
                this.said = b[23] + string2 + "\u2026";
            }
            int n = ++this.looks;
            ShareCode.fetch(string2, (arg_0, arg_1) -> this.lambda$apply$1(n, arg_0, arg_1));
            return;
        }
        if (shareCode$Decoded == null) {
            this.said = b[38];
            return;
        }
        this.apply(shareCode$Decoded);
    }

    private void apply(ShareCode$Decoded shareCode$Decoded) {
        block12: {
            this.sayBad = true;
            Iterator<Module> iterator = Aurora.INSTANCE.getModuleManager().getModules().iterator();
            boolean bl = ImScreen.a;
            while (iterator.hasNext()) {
                Module module;
                block15: {
                    ModsScreen modsScreen;
                    block16: {
                        Module module2;
                        block13: {
                            module = iterator.next();
                            if (bl) break block12;
                            module2 = module;
                            if (bl) break block13;
                            if (!module2.getName().equals(shareCode$Decoded.module())) {
                                continue;
                            }
                            module2 = module;
                        }
                        ConfigManager.applyShared(module2, shareCode$Decoded.settings());
                        modsScreen = this;
                        if (bl) break block15;
                        modsScreen.open = module;
                        if (this.searching()) break block16;
                        category = module.getCategory();
                    }
                    this.paste.set("");
                    this.sayBad = false;
                    modsScreen = this;
                }
                String[] stringArray = b;
                modsScreen.said = stringArray[10] + module.getName() + ".";
                return;
            }
            this.said = b[7] + shareCode$Decoded.module() + ".";
        }
    }

    private static float btnWidth(String string) {
        return Ui.width(Im.sora12, string) + 24.0f;
    }

    private boolean button(String string, String string2, float f, float f2, float f3, boolean bl, boolean bl2) {
        float f4;
        float f5;
        boolean bl3;
        float f6;
        block8: {
            block9: {
                f6 = ModsScreen.btnWidth(string);
                ImGui.setCursorScreenPos((float)f, (float)f2);
                bl3 = ImGui.invisibleButton((String)(string + string2), (float)f6, (float)f3);
                f5 = Ui.hot();
                ImDrawList imDrawList = ImGui.getWindowDrawList();
                boolean bl4 = ImScreen.a;
                ImDrawList imDrawList2 = imDrawList;
                float f7 = f;
                float f8 = f2;
                float f9 = f + f6;
                float f10 = f2 + f3;
                float[] fArray = bl2 ? null : Im.FIELD;
                Ui.box(imDrawList2, f7, f8, f9, f10, fArray, null, 7.0f, 0.0f);
                f4 = f5;
                if (bl4) break block8;
                if (!(f4 > 0.01f)) break block9;
                imDrawList.addRectFilled(f, f2, f + f6, f2 + f3, Ui.wash(0.06f * f5), 7.0f);
            }
            f4 = f;
        }
        ModsScreen.rim(f4, f2, f6, f3, bl, 7.0f);
        ImFont imFont = Im.sora12;
        float f11 = f + 12.0f;
        float f12 = Ui.mid(Im.sora12, f2, f3);
        float[] fArray = bl2 ? Ui.mix(Im.SUB, Im.INK, f5) : Im.INK;
        Ui.text(imFont, f11, f12, fArray, string);
        return bl3;
    }

    private static void rim(float f, float f2, float f3, float f4, boolean bl, float f5) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        float f6 = f;
        float f7 = f2;
        float f8 = f + f3;
        float f9 = f2 + f4;
        float[] fArray = null;
        float[] fArray2 = bl ? Ui.soft() : Im.FIELD_LINE;
        float f10 = f5;
        float f11 = bl ? 1.5f : 1.0f;
        Ui.box(imDrawList, f6, f7, f8, f9, fArray, fArray2, f10, f11);
    }

    private static Set<Setting> nested(Module module) {
        Set<Setting> set;
        block4: {
            Set<Setting> set2 = Collections.newSetFromMap(new IdentityHashMap());
            boolean bl = ImScreen.a;
            for (Setting setting : module.getSettings()) {
                set = set2;
                if (!bl) {
                    set.addAll(setting.getChildren());
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            set = set2;
        }
        return set;
    }

    
    private void setting(Module var1_1, Setting var2_2, float var3_3, float var4_4, float var5_5, boolean var6_6) {
        block120: {
            block121: {
                block154: {
                    block155: {
                        block156: {
                            block153: {
                                block152: {
                                    block151: {
                                        block149: {
                                            block150: {
                                                block148: {
                                                    block136: {
                                                        block146: {
                                                            block147: {
                                                                block142: {
                                                                    block143: {
                                                                        block145: {
                                                                            block144: {
                                                                                block140: {
                                                                                    block141: {
                                                                                        block139: {
                                                                                            block137: {
                                                                                                block138: {
                                                                                                    block131: {
                                                                                                        block132: {
                                                                                                            block135: {
                                                                                                                block133: {
                                                                                                                    block134: {
                                                                                                                        block128: {
                                                                                                                            block130: {
                                                                                                                                block129: {
                                                                                                                                    block126: {
                                                                                                                                        block127: {
                                                                                                                                            block122: {
                                                                                                                                                block124: {
                                                                                                                                                    block125: {
                                                                                                                                                        block123: {
                                                                                                                                                            block118: {
                                                                                                                                                                block119: {
                                                                                                                                                                    block115: {
                                                                                                                                                                        block116: {
                                                                                                                                                                            block117: {
                                                                                                                                                                                block114: {
                                                                                                                                                                                    var25_7 = ModsScreen.b;
                                                                                                                                                                                    var8_8 = var25_7[39] + var2_2.getName();
                                                                                                                                                                                    var7_9 = ImScreen.a;
                                                                                                                                                                                    v0 = var6_6 != false ? Im.sora13 : Im.sora14;
                                                                                                                                                                                    var9_10 = v0;
                                                                                                                                                                                    var10_11 = ImGui.getWindowDrawList();
                                                                                                                                                                                    if (var7_9) break block114;
                                                                                                                                                                                    if (!var2_2.hasChildren()) break block115;
                                                                                                                                                                                    ImGui.setCursorScreenPos((float)(var3_3 - 4.0f), (float)(var4_4 + 5.0f));
                                                                                                                                                                                }
                                                                                                                                                                                v4 = ImGui.invisibleButton((String)(ModsScreen.b[45] + var8_8), (float)(Ui.width(var9_10, var2_2.getName()) + 22.0f), (float)24.0f);
                                                                                                                                                                                if (var7_9) break block116;
                                                                                                                                                                                if (v4 == 0) break block117;
                                                                                                                                                                                v4 = this.unfolded.remove(var2_2);
                                                                                                                                                                                if (var7_9) break block116;
                                                                                                                                                                                if (v4 != 0) break block117;
                                                                                                                                                                                this.unfolded.add(var2_2);
                                                                                                                                                                            }
                                                                                                                                                                            v4 = Ui.col(Ui.mix(Im.MUTE, Im.INK, Ui.hot()));
                                                                                                                                                                        }
                                                                                                                                                                        var11_12 = v4;
                                                                                                                                                                        var12_14 = var3_3 + 3.0f;
                                                                                                                                                                        var13_16 = var4_4 + 17.0f;
                                                                                                                                                                        var14_18 = this.fold(var2_2) * 1.5708f;
                                                                                                                                                                        var15_20 = (float)Math.cos(var14_18);
                                                                                                                                                                        var16_22 = (float)Math.sin(var14_18);
                                                                                                                                                                        var10_11.addLine(var12_14 - 1.5f * var15_20 + 3.0f * var16_22, var13_16 - 1.5f * var16_22 - 3.0f * var15_20, var12_14 + 1.5f * var15_20, var13_16 + 1.5f * var16_22, var11_12, 1.4f);
                                                                                                                                                                        var10_11.addLine(var12_14 + 1.5f * var15_20, var13_16 + 1.5f * var16_22, var12_14 - 1.5f * var15_20 - 3.0f * var16_22, var13_16 - 1.5f * var16_22 + 3.0f * var15_20, var11_12, 1.4f);
                                                                                                                                                                        var3_3 += 14.0f;
                                                                                                                                                                    }
                                                                                                                                                                    v9 = var9_10;
                                                                                                                                                                    v10 = var3_3;
                                                                                                                                                                    v11 = Ui.mid(var9_10, var4_4, 34.0f);
                                                                                                                                                                    v12 = var6_6 != false ? Im.SUB : Im.INK;
                                                                                                                                                                    Ui.text(v9, v10, v11, v12, var2_2.getName());
                                                                                                                                                                    var11_13 = var4_4 + 4.0f;
                                                                                                                                                                    var12_15 = false;
                                                                                                                                                                    v14 = var2_2;
                                                                                                                                                                    if (var7_9) break block118;
                                                                                                                                                                    if (!(v14 instanceof BooleanSetting)) break block119;
                                                                                                                                                                    var13_17 = (BooleanSetting)var2_2;
                                                                                                                                                                    v16 = Ui.toggle(var8_8, var5_5 - 38.0f, var4_4 + 6.0f, 38.0f, 22.0f, var13_17.getValue());
                                                                                                                                                                    if (var7_9) break block120;
                                                                                                                                                                    if (!v16) break block121;
                                                                                                                                                                    var13_17.toggle();
                                                                                                                                                                    var12_15 = true;
                                                                                                                                                                    if (!var7_9) break block121;
                                                                                                                                                                }
                                                                                                                                                                v14 = var2_2;
                                                                                                                                                            }
                                                                                                                                                            if (var7_9) break block122;
                                                                                                                                                            if (v14 instanceof NumberSetting) {
                                                                                                                                                            }
                                                                                                                                                            var14_19 = (NumberSetting)var2_2;
                                                                                                                                                            var12_15 = this.slider(var8_8, var14_19, var5_5 - 170.0f, var4_4, 120.0f);
                                                                                                                                                            var21_24 = Switches.fmtNum(var14_19);
                                                                                                                                                            if (var7_9) break block123;
                                                                                                                                                            if (this.editing == var14_19) {
                                                                                                                                                            }
                                                                                                                                                            var12_15 |= this.number(var14_19, var5_5 - 40.0f, var11_13);
                                                                                                                                                            if (!var7_9) break block124;
lbl111:

                                                                                                                                                            v20 = var5_5;
                                                                                                                                                            if (var7_9) break block125;
                                                                                                                                                            ImGui.setCursorScreenPos((float)(v20 - 40.0f), (float)var11_13);
                                                                                                                                                        }
                                                                                                                                                        if (ImGui.invisibleButton((String)(var8_8 + "v"), (float)40.0f, (float)26.0f)) {
                                                                                                                                                            this.editing = var14_19;
                                                                                                                                                            this.num.set((String)var21_24);
                                                                                                                                                            this.focusNum = true;
                                                                                                                                                        }
                                                                                                                                                        v20 = Ui.hot();
                                                                                                                                                    }
                                                                                                                                                    var22_28 = v20;
                                                                                                                                                    if (var22_28 > 0.01f) {
                                                                                                                                                        var10_11.addRectFilled(var5_5 - 40.0f, var11_13, var5_5, var11_13 + 26.0f, Ui.wash(0.06f * var22_28), 6.0f);
                                                                                                                                                    }
                                                                                                                                                    Ui.text(Im.sora12, var5_5 - 4.0f - Ui.width(Im.sora12, (String)var21_24), Ui.mid(Im.sora12, var11_13, 26.0f), Im.INK, (String)var21_24);
                                                                                                                                                }
                                                                                                                                                if (!var7_9) break block121;
lbl142:

                                                                                                                                                v14 = var2_2;
                                                                                                                                            }
                                                                                                                                            if (var7_9) break block126;
                                                                                                                                            if (!(v14 instanceof EnumSetting)) break block127;
                                                                                                                                            var15_21 = (EnumSetting)var2_2;
                                                                                                                                            var12_15 = this.dropdown(var8_8, var15_21, var5_5, var11_13);
                                                                                                                                            if (!var7_9) break block121;
                                                                                                                                        }
                                                                                                                                        v14 = var2_2;
                                                                                                                                    }
                                                                                                                                    if (var7_9) break block128;
                                                                                                                                    if (v14 instanceof ChoiceSetting) {
                                                                                                                                    }
                                                                                                                                    var16_23 = (ChoiceSetting)var2_2;
                                                                                                                                    var21_24 = var16_23.getOptions();
                                                                                                                                    var22_29 = this.dropdown(var8_8, (List<String>)var21_24, var21_24.indexOf(var16_23.getValue()), var16_23.getValue(), var5_5, var11_13);
                                                                                                                                    if (var7_9) break block129;
                                                                                                                                    if (var22_29 < 0) break block130;
                                                                                                                                    var16_23.setValue((String)var21_24.get(var22_29));
                                                                                                                                }
                                                                                                                                var12_15 = true;
                                                                                                                            }
                                                                                                                            if (!var7_9) break block121;
lbl185:

                                                                                                                            v14 = var2_2;
                                                                                                                        }
                                                                                                                        if (var7_9) break block131;
                                                                                                                        if (!(v14 instanceof ColorSetting)) break block132;
                                                                                                                        var17_32 = (ColorSetting)var2_2;
                                                                                                                        var21_25 = var5_5 - 11.0f;
                                                                                                                        var22_30 = var4_4 + 17.0f;
                                                                                                                        if (var7_9) break block133;
                                                                                                                        ImGui.setCursorScreenPos((float)(var5_5 - 26.0f), (float)(var22_30 - 13.0f));
                                                                                                                        if (!ImGui.invisibleButton((String)var8_8, (float)26.0f, (float)26.0f)) break block134;
                                                                                                                        ImGui.openPopup((String)var8_8);
                                                                                                                    }
                                                                                                                    v34 = var10_11;
                                                                                                                    v35 = var21_25;
                                                                                                                    v36 = var22_30;
                                                                                                                    v37 = 11.0f;
                                                                                                                    v38 = var17_32.getR();
                                                                                                                    if (var7_9) break block135;
                                                                                                                    v34.addCircleFilled(v35, v36, v37, ImGui.getColorU32((float)((float)v38 / 255.0f), (float)((float)var17_32.getG() / 255.0f), (float)((float)var17_32.getB() / 255.0f), (float)1.0f), 32);
                                                                                                                    var10_11.addCircle(var21_25, var22_30, 11.0f, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)0.25f), 32, 1.0f);
                                                                                                                }
                                                                                                                v34 = var10_11;
                                                                                                                v35 = var21_25;
                                                                                                                v36 = var22_30;
                                                                                                                v37 = 14.5f;
                                                                                                                v38 = ImGui.isPopupOpen((String)var8_8) != false ? Ui.col(Ui.soft()) : Ui.col(Im.FIELD_LINE);
                                                                                                            }
                                                                                                            v41 = 32;
                                                                                                            v42 = ImGui.isPopupOpen((String)var8_8) != false ? 1.5f : 1.0f;
                                                                                                            v34.addCircle(v35, v36, v37, v38, v41, v42);
                                                                                                            var12_15 = this.picker(var8_8, var17_32, var5_5, var4_4 + 30.0f);
                                                                                                            if (!var7_9) break block121;
                                                                                                        }
                                                                                                        v14 = var2_2;
                                                                                                    }
                                                                                                    if (var7_9) break block136;
                                                                                                    if (v14 instanceof KeybindSetting) {
                                                                                                    }
                                                                                                    var18_33 = (KeybindSetting)var2_2;
                                                                                                    v45 = this.listening;
                                                                                                    if (var7_9) break block137;
                                                                                                    if (v45 != var18_33) break block138;
                                                                                                    v47 = ModsScreen.b[25];
                                                                                                    break block139;
                                                                                                }
                                                                                                v45 = var18_33;
                                                                                            }
                                                                                            v47 = Switches.keyName(v45.getKey());
                                                                                        }
                                                                                        var21_26 = v47;
                                                                                        var22_31 = Math.max(26.0f, Ui.width(Im.sora12, var21_26) + 16.0f);
                                                                                        var23_34 = var5_5 - var22_31;
                                                                                        if (var7_9) break block140;
                                                                                        if (this.listening != var18_33) break block141;
                                                                                        var24_35 = ModsScreen.b[48];
                                                                                        Ui.text(Im.label, var23_34 - 10.0f - Ui.width(Im.label, var24_35), Ui.mid(Im.label, var4_4, 34.0f), Im.MUTE, var24_35);
                                                                                    }
                                                                                    v50 = var23_34;
                                                                                    if (var7_9) break block142;
                                                                                    ImGui.setCursorScreenPos((float)v50, (float)var11_13);
                                                                                }
                                                                                if (!ImGui.invisibleButton((String)var8_8, (float)var22_31, (float)26.0f)) break block143;
                                                                                v52 = this;
                                                                                if (this.listening != var18_33) break block144;
                                                                                v54 = null;
                                                                                break block145;
                                                                            }
                                                                            v54 = var18_33;
                                                                        }
                                                                        v52.listening = v54;
                                                                    }
                                                                    v50 = Ui.hot();
                                                                }
                                                                var24_36 = v50;
                                                                Ui.box(var10_11, var23_34, var11_13, var5_5, var11_13 + 26.0f, Im.FIELD, null, 6.0f, 0.0f);
                                                                v56 = var24_36;
                                                                if (var7_9) break block146;
                                                                if (!(v56 > 0.01f)) break block147;
                                                                var10_11.addRectFilled(var23_34, var11_13, var5_5, var11_13 + 26.0f, Ui.wash(0.06f * var24_36), 6.0f);
                                                            }
                                                            v56 = var23_34;
                                                        }
                                                        v59 = var11_13;
                                                        v60 = var22_31;
                                                        v61 = 26.0f;
                                                        v62 = this.listening == var18_33;
                                                        ModsScreen.rim(v56, v59, v60, v61, v62, 6.0f);
                                                        Ui.text(Im.sora12, var23_34 + (float)Math.floor((var22_31 - Ui.width(Im.sora12, var21_26)) / 2.0f), Ui.mid(Im.sora12, var11_13, 26.0f), Im.INK, var21_26);
                                                        if (!var7_9) break block121;
lbl337:

                                                        v14 = var2_2;
                                                    }
                                                    if (var7_9) break block148;
                                                    if (v14 instanceof ActionSetting) {
                                                    }
                                                    var19_37 = (ActionSetting)var2_2;
                                                    v16 = this.button(var19_37.getLabel(), var8_8, var5_5 - ModsScreen.btnWidth(var19_37.getLabel()), var11_13, 26.0f, false, false);
                                                    if (var7_9) break block120;
                                                    if (!v16) break block121;
                                                    var19_37.run();
                                                    if (!var7_9) break block121;
lbl364:

                                                    v14 = var2_2;
                                                }
                                                v16 = v14 instanceof StringSetting;
                                                if (var7_9) break block120;
                                                if (!v16) break block121;
                                                var20_38 = (StringSetting)var2_2;
                                                var21_27 = this.texts.computeIfAbsent(var2_2, (Function<Setting, ImString>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$setting$2(dev.quelchi.aurora.module.setting.Setting ), (Ldev/quelchi/aurora/module/setting/Setting;)Limgui/type/ImString;)());
                                                if (var7_9) break block149;
                                                if (this.typing == var2_2) break block150;
                                                var21_27.set(Im.need(var20_38.getValue()));
                                            }
                                            ImGui.setCursorScreenPos((float)(var5_5 - 130.0f), (float)var11_13);
                                            if (var7_9) break block151;
                                            ImGui.setNextItemWidth((float)130.0f);
                                            ModsScreen.field(true);
                                        }
                                        if (!ImGui.inputText((String)var8_8, (ImString)var21_27)) break block152;
                                        var20_38.setValue(var21_27.get());
                                    }
                                    var12_15 = true;
                                }
                                v74 = this;
                                if (!ImGui.isItemActive()) break block153;
                                v75 = var2_2;
                                break block154;
                            }
                            v77 = this;
                            if (var7_9) break block155;
                            if (v77.typing != var2_2) break block156;
                            v75 = null;
                            break block154;
                        }
                        v77 = this;
                    }
                    v75 = v77.typing;
                }
                v74.typing = v75;
                ModsScreen.unfield();
                v80 = var5_5 - 130.0f;
                v81 = var11_13;
                v82 = 130.0f;
                v83 = 26.0f;
                v84 = this.typing == var2_2;
                ModsScreen.rim(v80, v81, v82, v83, v84, 6.0f);
            }
            v16 = var12_15;
        }
        if (v16) {
            var1_1.onSettingsChanged();
            ConfigManager.save();
        }
    }

    private static void field(boolean bl) {
        int n = 11;
        float f = bl ? 8.0f : 7.0f;
        float f2 = bl ? 6.5f : 4.5f;
        ImGui.pushStyleVar((int)n, (float)f, (float)f2);
        int n2 = 12;
        float f3 = bl ? 6.0f : 5.0f;
        ImGui.pushStyleVar((int)n2, (float)f3);
        ImFont imFont = bl ? Im.sora12 : Im.label;
        ImGui.pushFont((ImFont)imFont);
    }

    private static void unfield() {
        ImGui.popFont();
        ImGui.popStyleVar((int)2);
    }

    
    private boolean number(NumberSetting numberSetting, float f, float f2) {
        ImGui.setCursorScreenPos((float)f, (float)f2);
        boolean bl = ImScreen.a;
        ImGui.setNextItemWidth((float)40.0f);
        if (!bl) {
            ModsScreen.field(true);
            if (this.focusNum) {
                ImGui.setKeyboardFocusHere();
            }
            this.focusNum = false;
            ImGui.inputText((String)b[17], (ImString)this.num, (int)4097);
        }
        boolean bl2 = ImGui.isItemDeactivated();
        ModsScreen.unfield();
        if (bl) return true;
        ModsScreen.rim(f, f2, 40.0f, 26.0f, true, 6.0f);
        if (!bl2) {
            return false;
        }
        this.editing = null;
        try {
            numberSetting.setExact(Double.parseDouble(this.num.get().trim()));
            return true;
        }
        catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    
    private boolean slider(String string, NumberSetting numberSetting, float f, float f2, float f3) {
        float f4;
        boolean bl;
        boolean bl2;
        float f5;
        block21: {
            block22: {
                block20: {
                    double d;
                    f5 = f2 + 13.0f;
                    ImGui.setCursorScreenPos((float)f, (float)(f2 + 6.0f));
                    bl2 = ImScreen.a;
                    ImGui.invisibleButton((String)string, (float)f3, (float)22.0f);
                    bl = false;
                    if (!ImGui.isItemActive()) break block20;
                    double d2 = Math.max(0.0f, Math.min(1.0f, (ImGui.getMousePosX() - f) / f3));
                    double d3 = numberSetting.getMin() + d2 * (numberSetting.getMax() - numberSetting.getMin());
                    double d4 = d = numberSetting.getIncrement();
                    double d5 = 0.0;
                    if (!bl2) {
                        if (d4 > d5) {
                            d3 = (double)Math.round(d3 / d) * d;
                        }
                        d4 = d3;
                        d5 = numberSetting.getValue();
                    }
                    if (d4 != d5) {
                        numberSetting.setValue(d3);
                    }
                    this.sliding = numberSetting;
                    if (!bl2) break block21;
                }
                ModsScreen modsScreen = this;
                if (bl2) break block22;
                if (modsScreen.sliding != numberSetting) break block21;
                modsScreen = this;
            }
            modsScreen.sliding = null;
            bl = true;
        }
        float f6 = Math.max(Ui.hot(), ImGui.isItemActive() ? 1.0f : 0.0f);
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        float f7 = f + (float)numberSetting.getFraction() * f3;
        imDrawList.addRectFilled(f, f5, f + f3, f5 + 8.0f, Ui.col(Im.TRACK), 4.0f);
        float f8 = f7;
        if (!bl2) {
            if (f8 > f) {
                Ui.badge(imDrawList, f, f5, Math.max(f7, f + 8.0f), f5 + 8.0f, 4.0f);
            }
            f8 = f6;
        }
        float f9 = (f4 = f8 - 0.01f) == 0.0f ? 0 : (f4 > 0.0f ? 1 : -1);
        if (!bl2) {
            if (f9 > 0) {
                imDrawList.addCircleFilled(f7, f5 + 4.0f, 6.5f, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)(0.25f * f6)), 20);
                imDrawList.addCircleFilled(f7, f5 + 4.0f, 6.0f, Ui.col(Menu.KNOB_ON, f6), 20);
            }
            f9 = (float)bl;
        }
        return (boolean)f9;
    }

    private boolean dropdown(String string, EnumSetting<?> enumSetting, float f, float f2) {
        block9: {
            int n;
            Enum[] enumArray;
            block10: {
                int n2;
                boolean bl;
                block8: {
                    enumArray = (Enum[])((Enum)enumSetting.getValue()).getDeclaringClass().getEnumConstants();
                    ArrayList<String> arrayList = new ArrayList<String>();
                    Enum[] enumArray2 = enumArray;
                    int n3 = enumArray2.length;
                    bl = ImScreen.a;
                    for (int i = 0; i < n3; ++i) {
                        Enum enum_ = enumArray2[i];
                        n2 = arrayList.add(Switches.pretty(enum_.name()));
                        if (!bl) {
                            if (!bl) continue;
                            break;
                        }
                        break block8;
                    }
                    n2 = this.dropdown(string, arrayList, ((Enum)enumSetting.getValue()).ordinal(), (String)arrayList.get(((Enum)enumSetting.getValue()).ordinal()), f, f2);
                }
                n = n2;
                if (bl) break block9;
                if (n >= 0) break block10;
                return false;
            }
            enumSetting.setByName(enumArray[n].name());
        }
        return true;
    }

    private int dropdown(String string, List<String> list, int n, String string2, float f, float f2) {
        int n2;
        block30: {
            int n3;
            block32: {
                block31: {
                    float f3;
                    ImDrawList imDrawList;
                    float f4;
                    float f5;
                    float f6;
                    boolean bl;
                    block28: {
                        block29: {
                            float f7;
                            block26: {
                                block27: {
                                    float f8;
                                    float f9;
                                    float f10;
                                    block25: {
                                        f10 = 0.0f;
                                        Iterator<String> iterator = list.iterator();
                                        bl = ImScreen.a;
                                        while (iterator.hasNext()) {
                                            String string3 = iterator.next();
                                            float f8 = f10;
                                            f8 = Ui.width(Im.sora12, string3);
                                            if (!bl) {
                                                f10 = Math.max(f9, f8);
                                                if (!bl) continue;
                                            }
                                            break block25;
                                        }
                                        float f8 = 96.0f;
                                        f8 = Math.min(180.0f, f10 + 36.0f);
                                    }
                                    f6 = Math.max(f9, f8);
                                    f5 = f - f6;
                                    f4 = Math.max(f6, f10 + 44.0f);
                                    f7 = f5;
                                    if (bl) break block26;
                                    ImGui.setCursorScreenPos((float)f7, (float)f2);
                                    if (!ImGui.invisibleButton((String)string, (float)f6, (float)26.0f)) break block27;
                                    ImGui.openPopup((String)string);
                                }
                                f7 = Ui.hot();
                            }
                            float f11 = f7;
                            imDrawList = ImGui.getWindowDrawList();
                            Ui.box(imDrawList, f5, f2, f5 + f6, f2 + 26.0f, Im.FIELD, null, 6.0f, 0.0f);
                            f3 = f11;
                            if (bl) break block28;
                            if (!(f3 > 0.01f)) break block29;
                            imDrawList.addRectFilled(f5, f2, f5 + f6, f2 + 26.0f, Ui.wash(0.06f * f11), 6.0f);
                        }
                        f3 = f5;
                    }
                    ModsScreen.rim(f3, f2, f6, 26.0f, ImGui.isPopupOpen((String)string), 6.0f);
                    imDrawList.pushClipRect(f5, f2, f5 + f6 - 20.0f, f2 + 26.0f, true);
                    Ui.text(Im.sora12, f5 + 10.0f, Ui.mid(Im.sora12, f2, 26.0f), Im.INK, string2);
                    imDrawList.popClipRect();
                    imDrawList.addLine(f5 + f6 - 16.0f, f2 + 11.5f, f5 + f6 - 12.5f, f2 + 15.0f, Ui.col(Im.SUB), 1.3f);
                    imDrawList.addLine(f5 + f6 - 12.5f, f2 + 15.0f, f5 + f6 - 9.0f, f2 + 11.5f, Ui.col(Im.SUB), 1.3f);
                    n3 = -1;
                    ImGui.setNextWindowPos((float)(f - f4), (float)(f2 + 29.0f + Ui.drop(string)));
                    ImGui.setNextWindowSize((float)f4, (float)0.0f);
                    ImGui.pushStyleVar((int)2, (float)4.0f, (float)4.0f);
                    ImGui.pushStyleVar((int)9, (float)8.0f);
                    n2 = 14;
                    if (bl) break block30;
                    ImGui.pushStyleVar((int)n2, (float)0.0f, (float)2.0f);
                    ImGui.pushStyleVar((int)11, (float)7.0f, (float)4.0f);
                    ImGui.pushStyleColor((int)4, (float)Im.POP[0], (float)Im.POP[1], (float)Im.POP[2], (float)Im.POP[3]);
                    ImGui.pushStyleColor((int)24, (float)1.0f, (float)1.0f, (float)1.0f, (float)0.1f);
                    ImGui.pushStyleColor((int)25, (float)1.0f, (float)1.0f, (float)1.0f, (float)0.05f);
                    ImGui.pushStyleColor((int)26, (float)1.0f, (float)1.0f, (float)1.0f, (float)0.1f);
                    ImGui.pushFont((ImFont)Im.sora12);
                    if (!ImGui.beginPopup((String)string)) break block31;
                    Ui.lift();
                    for (int i = 0; i < list.size(); ++i) {
                        int n4;
                        block35: {
                            int n5;
                            block36: {
                                int n6;
                                block33: {
                                    block34: {
                                        if (bl) break block32;
                                        n6 = i;
                                        if (bl) break block33;
                                        if (n6 != n) break block34;
                                        n6 = 1;
                                        break block33;
                                    }
                                    n6 = 0;
                                }
                                n5 = n6;
                                n4 = Ui.option(" " + list.get(i), n5 != 0);
                                if (bl) break block35;
                                if (n4 == 0) break block36;
                                n4 = n5;
                                if (bl) break block35;
                                if (n4 != 0) break block36;
                                n3 = i;
                            }
                            n4 = n5;
                        }
                        if (n4 == 0) continue;
                        float f12 = ImGui.getItemRectMaxX() - 12.0f;
                        float f13 = (ImGui.getItemRectMinY() + ImGui.getItemRectMaxY()) / 2.0f;
                        ImDrawList imDrawList2 = ImGui.getWindowDrawList();
                        imDrawList2.addLine(f12 - 3.5f, f13, f12 - 1.0f, f13 + 2.5f, Ui.col(Menu.ACCENT), 1.3f);
                        imDrawList2.addLine(f12 - 1.0f, f13 + 2.5f, f12 + 3.5f, f13 - 2.5f, Ui.col(Menu.ACCENT), 1.3f);
                        if (!bl) continue;
                    }
                    ImGui.endPopup();
                }
                ImGui.popFont();
                ImGui.popStyleColor((int)4);
                ImGui.popStyleVar((int)5);
            }
            n2 = n3;
        }
        return n2;
    }

    private static int rgb(float[] fArray) {
        float[] fArray2 = new float[3];
        ImGui.colorConvertHSVtoRGB((float[])fArray, (float[])fArray2);
        return Math.round(fArray2[0] * 255.0f) << 16 | Math.round(fArray2[1] * 255.0f) << 8 | Math.round(fArray2[2] * 255.0f);
    }

    private static int u32(int n, float f) {
        return ImGui.getColorU32((float)((float)(n >> 16 & 0xFF) / 255.0f), (float)((float)(n >> 8 & 0xFF) / 255.0f), (float)((float)(n & 0xFF) / 255.0f), (float)f);
    }

    private void pick(int n) {
        ImGui.colorConvertRGBtoHSV((float[])new float[]{(float)(n >> 16 & 0xFF) / 255.0f, (float)(n >> 8 & 0xFF) / 255.0f, (float)(n & 0xFF) / 255.0f}, (float[])this.hsv);
    }

    private boolean picker(String string, ColorSetting colorSetting, float f, float f2) {
        boolean bl;
        block63: {
            boolean bl2;
            block64: {
                block86: {
                    float f3;
                    ColorSetting colorSetting2;
                    block89: {
                        ColorSetting colorSetting3;
                        block87: {
                            block88: {
                                boolean bl3;
                                boolean bl4;
                                block83: {
                                    float f4;
                                    float f5;
                                    ImDrawList imDrawList;
                                    float f6;
                                    block82: {
                                        float f7;
                                        float f8;
                                        float f9;
                                        block79: {
                                            block81: {
                                                ModsScreen modsScreen;
                                                block80: {
                                                    block77: {
                                                        block78: {
                                                            int n;
                                                            float f10;
                                                            block75: {
                                                                block76: {
                                                                    float f11;
                                                                    float f12;
                                                                    float f13;
                                                                    ImDrawList imDrawList2;
                                                                    block72: {
                                                                        int n2;
                                                                        int n3;
                                                                        float f14;
                                                                        block71: {
                                                                            int n4;
                                                                            block69: {
                                                                                int n5;
                                                                                block70: {
                                                                                    int n6;
                                                                                    float f15;
                                                                                    block67: {
                                                                                        block68: {
                                                                                            block66: {
                                                                                                block65: {
                                                                                                    block58: {
                                                                                                        boolean bl5;
                                                                                                        block59: {
                                                                                                            boolean bl6;
                                                                                                            block61: {
                                                                                                                block62: {
                                                                                                                    block60: {
                                                                                                                        boolean bl7;
                                                                                                                        block56: {
                                                                                                                            block57: {
                                                                                                                                f6 = 186.0f;
                                                                                                                                ImGui.setNextWindowPos((float)(f - f6 - 20.0f), (float)(f2 + Ui.drop(string)));
                                                                                                                                ImGui.setNextWindowSize((float)(f6 + 20.0f), (float)0.0f);
                                                                                                                                ImGui.pushStyleVar((int)2, (float)10.0f, (float)10.0f);
                                                                                                                                bl4 = ImScreen.a;
                                                                                                                                ImGui.pushStyleVar((int)9, (float)8.0f);
                                                                                                                                ImGui.pushStyleColor((int)4, (float)Im.POP[0], (float)Im.POP[1], (float)Im.POP[2], (float)Im.POP[3]);
                                                                                                                                bl5 = ImGui.beginPopup((String)string);
                                                                                                                                bl7 = bl5;
                                                                                                                                if (bl4) break block56;
                                                                                                                                if (!bl7) break block57;
                                                                                                                                Ui.lift();
                                                                                                                            }
                                                                                                                            bl7 = false;
                                                                                                                        }
                                                                                                                        bl2 = bl7;
                                                                                                                        bl = bl5;
                                                                                                                        if (bl4) break block58;
                                                                                                                        if (bl) break block59;
                                                                                                                        ModsScreen modsScreen2 = this;
                                                                                                                        if (bl4) break block60;
                                                                                                                        if (modsScreen2.picking != colorSetting) break block59;
                                                                                                                        modsScreen2 = this;
                                                                                                                    }
                                                                                                                    modsScreen2.picking = null;
                                                                                                                    bl6 = RECENT.remove((Object)(colorSetting.getRGB() & ModsScreen.a(32596, 2600015848524159316L)));
                                                                                                                    if (bl4) break block61;
                                                                                                                    RECENT.add(0, colorSetting.getRGB() & ModsScreen.a(32596, 2600015848524159316L));
                                                                                                                    if (RECENT.size() <= 7) break block62;
                                                                                                                    RECENT.remove(7);
                                                                                                                }
                                                                                                                bl6 = this.dirty;
                                                                                                            }
                                                                                                            bl2 = bl6;
                                                                                                        }
                                                                                                        bl = bl5;
                                                                                                    }
                                                                                                    if (bl4) break block63;
                                                                                                    if (!bl) break block64;
                                                                                                    ModsScreen modsScreen3 = this;
                                                                                                    if (bl4) break block65;
                                                                                                    if (modsScreen3.picking == colorSetting) break block66;
                                                                                                    this.picking = colorSetting;
                                                                                                    this.dirty = false;
                                                                                                    this.pick(colorSetting.getRGB());
                                                                                                    modsScreen3 = this;
                                                                                                }
                                                                                                modsScreen3.alpha = (float)colorSetting.getA() / 255.0f;
                                                                                            }
                                                                                            imDrawList = ImGui.getWindowDrawList();
                                                                                            f5 = ImGui.getCursorScreenPosX();
                                                                                            f15 = ImGui.getCursorScreenPosY();
                                                                                            f14 = ImGui.getMousePosX();
                                                                                            float f16 = ImGui.getMousePosY();
                                                                                            bl3 = false;
                                                                                            n6 = ImGui.invisibleButton((String)b[1], (float)f6, (float)112.0f);
                                                                                            if (bl4) break block67;
                                                                                            if (!ImGui.isItemActive()) break block68;
                                                                                            this.hsv[1] = Math.max(0.0f, Math.min(1.0f, (f14 - f5) / f6));
                                                                                            this.hsv[2] = 1.0f - Math.max(0.0f, Math.min(1.0f, (f16 - f15) / 112.0f));
                                                                                            bl3 = true;
                                                                                        }
                                                                                        n6 = ModsScreen.u32(ModsScreen.rgb(new float[]{this.hsv[0], 1.0f, 1.0f}), 1.0f);
                                                                                    }
                                                                                    int n7 = n6;
                                                                                    n3 = ModsScreen.u32(ModsScreen.a(32596, 2600015848524159316L), 1.0f);
                                                                                    imDrawList.addRectFilledMultiColor(f5, f15, f5 + f6, f15 + 112.0f, n3, n7, n7, n3);
                                                                                    imDrawList.addRectFilledMultiColor(f5, f15, f5 + f6, f15 + 112.0f, ModsScreen.u32(0, 0.0f), ModsScreen.u32(0, 0.0f), ModsScreen.u32(0, 1.0f), ModsScreen.u32(0, 1.0f));
                                                                                    imDrawList.addCircle(f5 + this.hsv[1] * f6, f15 + (1.0f - this.hsv[2]) * 112.0f, 5.0f, ModsScreen.u32(0, 0.5f), 16, 3.0f);
                                                                                    imDrawList.addCircle(f5 + this.hsv[1] * f6, f15 + (1.0f - this.hsv[2]) * 112.0f, 5.0f, n3, 16, 2.0f);
                                                                                    f4 = f15 + 121.0f;
                                                                                    ImGui.setCursorScreenPos((float)f5, (float)(f4 - 4.0f));
                                                                                    n5 = ImGui.invisibleButton((String)b[15], (float)f6, (float)16.0f);
                                                                                    if (bl4) break block69;
                                                                                    if (!ImGui.isItemActive()) break block70;
                                                                                    this.hsv[0] = Math.max(0.0f, Math.min(1.0f, (f14 - f5) / f6));
                                                                                    bl3 = true;
                                                                                }
                                                                                n5 = n4 = 0;
                                                                            }
                                                                            while (n4 < 6) {
                                                                                int n8 = ModsScreen.u32(ModsScreen.rgb(new float[]{(float)n4 / 6.0f, 1.0f, 1.0f}), 1.0f);
                                                                                n2 = ModsScreen.rgb(new float[]{(float)(n4 + 1) / 6.0f, 1.0f, 1.0f});
                                                                                if (!bl4) {
                                                                                    int n9 = ModsScreen.u32(n2, 1.0f);
                                                                                    imDrawList.addRectFilledMultiColor(f5 + f6 * (float)n4 / 6.0f, f4, f5 + f6 * (float)(n4 + 1) / 6.0f, f4 + 8.0f, n8, n9, n9, n8);
                                                                                    ++n4;
                                                                                    if (!bl4) continue;
                                                                                }
                                                                                break block71;
                                                                            }
                                                                            imDrawList2 = imDrawList;
                                                                            float f11 = f5 + this.hsv[0] * f6;
                                                                            f11 = f4 + 4.0f;
                                                                            f11 = 6.0f;
                                                                            if (bl4) break block72;
                                                                            imDrawList2.addCircleFilled(f13, f12, f11, n3, 16);
                                                                            n2 = colorSetting.hasAlpha();
                                                                        }
                                                                        if (n2 != 0) {
                                                                            block73: {
                                                                                block74: {
                                                                                    f4 += 17.0f;
                                                                                    if (bl4) break block73;
                                                                                    ImGui.setCursorScreenPos((float)f5, (float)(f4 - 4.0f));
                                                                                    ImGui.invisibleButton((String)b[18], (float)f6, (float)16.0f);
                                                                                    if (!ImGui.isItemActive()) break block74;
                                                                                    this.alpha = Math.max(0.0f, Math.min(1.0f, (f14 - f5) / f6));
                                                                                    bl3 = true;
                                                                                }
                                                                                Ui.fill(imDrawList, f5, f4, f5 + f6, f4 + 8.0f, Ui.wash(0.1f));
                                                                                imDrawList.addRectFilledMultiColor(f5, f4, f5 + f6, f4 + 8.0f, ModsScreen.u32(ModsScreen.rgb(this.hsv), 0.0f), ModsScreen.u32(ModsScreen.rgb(this.hsv), 1.0f), ModsScreen.u32(ModsScreen.rgb(this.hsv), 1.0f), ModsScreen.u32(ModsScreen.rgb(this.hsv), 0.0f));
                                                                            }
                                                                            imDrawList.addCircleFilled(f5 + this.alpha * f6, f4 + 4.0f, 6.0f, n3, 16);
                                                                        }
                                                                        Ui.fill(imDrawList, f5, f4 += 18.0f, f5 + 20.0f, f4 + 20.0f, ModsScreen.u32(ModsScreen.rgb(this.hsv), 1.0f), 4.0f);
                                                                        imDrawList2 = imDrawList;
                                                                        float f11 = f5;
                                                                        f11 = f4;
                                                                        f11 = f5 + 20.0f;
                                                                    }
                                                                    Ui.rect(imDrawList2, f13, f12, f11, f4 + 20.0f, Ui.wash(0.16f), 4.0f);
                                                                    f10 = f6 - 26.0f;
                                                                    n = colorSetting.hasAlpha();
                                                                    if (bl4) break block75;
                                                                    if (n == 0) break block76;
                                                                    n = 46;
                                                                    break block75;
                                                                }
                                                                n = 0;
                                                            }
                                                            f9 = f10 - (float)n;
                                                            if (bl4) break block77;
                                                            if (this.hexing) break block78;
                                                            this.hex.set(String.format(Locale.ROOT, b[60], ModsScreen.rgb(this.hsv)));
                                                        }
                                                        float f7 = f5 + 26.0f;
                                                        f7 = f4;
                                                        if (bl4) break block79;
                                                        ImGui.setCursorScreenPos((float)f8, (float)f7);
                                                        ImGui.setNextItemWidth((float)f9);
                                                        ModsScreen.field(false);
                                                    }
                                                    if (!ImGui.inputText((String)b[58], (ImString)this.hex, (int)8)) break block80;
                                                    modsScreen = this;
                                                    if (bl4) break block81;
                                                    String string2 = modsScreen.hex.get().trim().replace("#", "");
                                                    String[] stringArray = b;
                                                    if (string2.matches(stringArray[19])) {
                                                        this.pick(Integer.parseInt(string2, 16));
                                                        bl3 = true;
                                                    }
                                                }
                                                modsScreen = this;
                                            }
                                            modsScreen.hexing = ImGui.isItemActive();
                                            if (bl4) break block82;
                                            ModsScreen.unfield();
                                            float f7 = f5 + 26.0f;
                                            f7 = f4;
                                        }
                                        ModsScreen.rim(f8, f7, f9, 20.0f, this.hexing, 5.0f);
                                        if (colorSetting.hasAlpha()) {
                                            String string3 = Math.round(this.alpha * 100.0f) + "%";
                                            Ui.fill(imDrawList, f5 + f6 - 40.0f, f4, f5 + f6, f4 + 20.0f, Ui.col(Im.QUIET), 5.0f);
                                            ModsScreen.rim(f5 + f6 - 40.0f, f4, 40.0f, 20.0f, false, 5.0f);
                                            Ui.text(Im.label, f5 + f6 - 20.0f - Ui.width(Im.label, string3) / 2.0f, Ui.mid(Im.label, f4, 20.0f), Im.INK, string3);
                                        }
                                        f4 += 29.0f;
                                    }
                                    int n = 0;
                                    while (n < RECENT.size()) {
                                        block84: {
                                            float f17;
                                            block85: {
                                                if (bl4) break block83;
                                                f17 = f5 + (float)(n * 22);
                                                if (bl4) break block84;
                                                ImGui.setCursorScreenPos((float)f17, (float)f4);
                                                if (!ImGui.invisibleButton((String)(b[41] + n), (float)16.0f, (float)16.0f)) break block85;
                                                this.pick(RECENT.get(n));
                                                bl3 = true;
                                            }
                                            Ui.fill(imDrawList, f17, f4, f17 + 16.0f, f4 + 16.0f, ModsScreen.u32(RECENT.get(n), 1.0f), 4.0f);
                                            Ui.rect(imDrawList, f17, f4, f17 + 16.0f, f4 + 16.0f, Ui.wash(0.16f), 4.0f);
                                            ++n;
                                        }
                                        if (!bl4) continue;
                                    }
                                    ImGui.setCursorScreenPos((float)f5, (float)(f4 + 16.0f));
                                    ImGui.dummy((float)f6, (float)0.0f);
                                }
                                if (!bl3) break block86;
                                ColorSetting colorSetting3 = colorSetting;
                                colorSetting3 = colorSetting;
                                if (bl4) break block87;
                                if (!colorSetting3.hasAlpha()) break block88;
                                f3 = this.alpha;
                                break block89;
                            }
                            colorSetting3 = colorSetting;
                        }
                        f3 = (float)colorSetting3.getA() / 255.0f;
                    }
                    colorSetting2.setARGB(Math.round(f3 * 255.0f) << 24 | ModsScreen.rgb(this.hsv));
                    this.dirty = true;
                }
                ImGui.endPopup();
            }
            ImGui.popStyleColor();
            ImGui.popStyleVar((int)3);
            bl = bl2;
        }
        return bl;
    }

    private static ImString lambda$setting$2(Setting setting) {
        return new ImString(512);
    }

    private void lambda$apply$1(int n, ShareCode$Decoded shareCode$Decoded, String string) {
        block8: {
            block7: {
                boolean bl = ImScreen.a;
                if (n != this.looks) {
                    return;
                }
                if (shareCode$Decoded == null) break block7;
                this.apply(shareCode$Decoded);
                if (!bl) break block8;
            }
            this.sayBad = true;
            this.said = string;
        }
    }

    private void lambda$options$0(int n, String string) {
        if (n == this.asks) {
            this.code = string;
        }
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[61];
                        var11_1 = 0;
                        var10_2 = "\u0012\u00135\u000b\u0002\"\n\u0010\u001f\u00131\u001ahX9q\u0011$\u0006b^;\"R\b<\u0013!\u0007mS-~\u0005\"\u0014$\u0000d\u0005\u0010\f5\u001ex\u0006r_&\u001dqO\u0011\u001f\u0013e\u001fnR+=\u0019e\u0011`Z24\u0018e\u000f\u0016\u00191\u0006hX9q\u001de\u0011nR;\u2077\u0003#\u00132\u000b\u0010\f5\u001ehS:q\b*R\b\u0014\u0018,\u0006!~\u000b\u0015\u001b\u0002\u0019$\u0000b^~<\u0013!\u0007mS-q\u001d+\u0016!E;%\b,\u001cfE\b4\u0018,\u0006^^+5\u0003%\u001d'\u00039\t \u0003%\u001d'\u0005r_+\u0007l\u00050\u00105\u001a`\u000b\nLhK@\u001b\u0018\f\u0007s\u000f\u0005\u0002\u0014$\u0000d\u0007#\u0019#\u0000dE6\u00049\u0019$\u0016\u000b\u001d\u0013*\u0019hX9q\t5R\u000e\u0014\u0018,\u0006!~\u000b\u0015_f\u001ehE*\u000b\u0001\u000e \u0001r\u0016?q\u0017 \u000b!\u001f\u0013e\u001du^;#\\*\u0002u_1?\u000fe\u0014nD~%\u0014,\u0001![15\t)\u0017/\u0007r_5\u0013rB;\u0003q\u00cbe\u0007>\f1\u001bnX-\u0006\u0002\u0014$\u0000d\u0016\u00047\u0013)\u0016\u0007\u0016\u0019+\u0017sW2\u0005\u0010\f5\u001ex\u0007\u001c\u0013!\u0007mS-\u00042\u00135\u000b\br_6\u0017`D=9\b\u0014\u0018,\u0006!~\u000b\u0015\u001a\u0005\u0014$\u0006!_-q\u0012*\u0006!W~<\u0013!\u0007mS~2\u0013!\u0017/\u0002r_\b4\u0018,\u0006^^+5\u0006#\u0019&\u0017oB\u0007\u0016\u0019+\u0017sW2\u0003=\u00151\u0004=\u00156\u0006\u00047\u0013)\u0016\u0005\u0003\u00196\u0017u\u0005\"\u0014$\u0000d\f\u0014\u000f&RuY~2\u0010 \u0013s\u0002>\u0012\u0006\u0012\u00135\u001bdR\u0004<\u0013!\u0001\u0004\u001c\u0013!\u0001\u0005\u0002\u0014$\u0000d\u00042\u00135\u000b\n\u0001\u001d6\u0006d\u0016=>\u0018 \u0005\u0003\u00196\u0017u\n\u0004\u000f R`\u0016=>\u0018 \u0005r_-\u0017y";
                        var12_3 = "\u0012\u00135\u000b\u0002\"\n\u0010\u001f\u00131\u001ahX9q\u0011$\u0006b^;\"R\b<\u0013!\u0007mS-~\u0005\"\u0014$\u0000d\u0005\u0010\f5\u001ex\u0006r_&\u001dqO\u0011\u001f\u0013e\u001fnR+=\u0019e\u0011`Z24\u0018e\u000f\u0016\u00191\u0006hX9q\u001de\u0011nR;\u2077\u0003#\u00132\u000b\u0010\f5\u001ehS:q\b*R\b\u0014\u0018,\u0006!~\u000b\u0015\u001b\u0002\u0019$\u0000b^~<\u0013!\u0007mS-q\u001d+\u0016!E;%\b,\u001cfE\b4\u0018,\u0006^^+5\u0003%\u001d'\u00039\t \u0003%\u001d'\u0005r_+\u0007l\u00050\u00105\u001a`\u000b\nLhK@\u001b\u0018\f\u0007s\u000f\u0005\u0002\u0014$\u0000d\u0007#\u0019#\u0000dE6\u00049\u0019$\u0016\u000b\u001d\u0013*\u0019hX9q\t5R\u000e\u0014\u0018,\u0006!~\u000b\u0015_f\u001ehE*\u000b\u0001\u000e \u0001r\u0016?q\u0017 \u000b!\u001f\u0013e\u001du^;#\\*\u0002u_1?\u000fe\u0014nD~%\u0014,\u0001![15\t)\u0017/\u0007r_5\u0013rB;\u0003q\u00cbe\u0007>\f1\u001bnX-\u0006\u0002\u0014$\u0000d\u0016\u00047\u0013)\u0016\u0007\u0016\u0019+\u0017sW2\u0005\u0010\f5\u001ex\u0007\u001c\u0013!\u0007mS-\u00042\u00135\u000b\br_6\u0017`D=9\b\u0014\u0018,\u0006!~\u000b\u0015\u001a\u0005\u0014$\u0006!_-q\u0012*\u0006!W~<\u0013!\u0007mS~2\u0013!\u0017/\u0002r_\b4\u0018,\u0006^^+5\u0006#\u0019&\u0017oB\u0007\u0016\u0019+\u0017sW2\u0003=\u00151\u0004=\u00156\u0006\u00047\u0013)\u0016\u0005\u0003\u00196\u0017u\u0005\"\u0014$\u0000d\f\u0014\u000f&RuY~2\u0010 \u0013s\u0002>\u0012\u0006\u0012\u00135\u001bdR\u0004<\u0013!\u0001\u0004\u001c\u0013!\u0001\u0005\u0002\u0014$\u0000d\u00042\u00135\u000b\n\u0001\u001d6\u0006d\u0016=>\u0018 \u0005\u0003\u00196\u0017u\n\u0004\u000f R`\u0016=>\u0018 \u0005r_-\u0017y".length();
                        var9_4 = 4;
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
                            var10_2 = "#\u0019#\u0000dE6\u0005rYuDY";
                            var12_3 = "#\u0019#\u0000dE6\u0005rYuDY".length();
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
                                v10 = 81;
                                break;
                            }
                            case 1: {
                                v10 = 124;
                                break;
                            }
                            case 2: {
                                v10 = 69;
                                break;
                            }
                            case 3: {
                                v10 = 114;
                                break;
                            }
                            case 4: {
                                v10 = 1;
                                break;
                            }
                            case 5: {
                                v10 = 54;
                                break;
                            }
                            default: {
                                v10 = 94;
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
                ModsScreen.b = var13;
                var0_7 = 4325560462388602806L;
                var6_8 = new long[8];
                var3_9 = 0;
                var4_10 = "\f.\u00c8\u00f6\u00f6\u00beE\u00d8\u00dd\u001dq\u00c2\u001aqA\u001d\u00bf\u00dc;\u00ab5\u0096n\u00c9\u00cf\u00ea\u00f4\u00ff\u00b69\u00cf\u001b;\u008d\u00f0\u0015\u00ed&\u00d8\u00b8X\u00dfV\u00de\u00dd\u00192\u0087";
                var5_11 = "\f.\u00c8\u00f6\u00f6\u00beE\u00d8\u00dd\u001dq\u00c2\u001aqA\u001d\u00bf\u00dc;\u00ab5\u0096n\u00c9\u00cf\u00ea\u00f4\u00ff\u00b69\u00cf\u001b;\u008d\u00f0\u0015\u00ed&\u00d8\u00b8X\u00dfV\u00de\u00dd\u00192\u0087".length();
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
                    var4_10 = "\u00c5\u000ee \u00a2\u009f\u00dbC\u00ed\u0003n5t\u001b;w";
                    var5_11 = "\u00c5\u000ee \u00a2\u009f\u00dbC\u00ed\u0003n5t\u001b;w".length();
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
        ModsScreen.c = var6_8;
        ModsScreen.d = new Integer[8];
        ModsScreen.CATS = new Category[]{Category.COMBAT, Category.RENDER, Category.HUD, Category.PLAYER, Category.MISC};
        ModsScreen.category = Category.COMBAT;
        ModsScreen.RECENT = new ArrayList<Integer>(List.of(Integer.valueOf(ModsScreen.a(20611, 3148957272802866816L)), Integer.valueOf(ModsScreen.a(1585, 5592298794897498167L)), Integer.valueOf(ModsScreen.a(18096, 5024105212508545201L)), Integer.valueOf(ModsScreen.a(9621, 5292236240566371217L)), Integer.valueOf(ModsScreen.a(20440, 5963860995153242586L)), Integer.valueOf(ModsScreen.a(27565, 9038427026676649386L)), Integer.valueOf(ModsScreen.a(6411, 5650762626439610126L))));
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6601;
        if (d[n2] == null) {
            ModsScreen.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }
}
