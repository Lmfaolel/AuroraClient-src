package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.auth.Accounts;
import dev.quelchi.aurora.auth.Accounts$Saved;
import dev.quelchi.aurora.gui.Anim;
import dev.quelchi.aurora.gui.Mannequin;
import dev.quelchi.aurora.gui.TitleHome;
import dev.quelchi.aurora.gui.UpdatePrompt;
import dev.quelchi.aurora.gui.WhatsNew;
import dev.quelchi.aurora.gui.WhatsNew$Feature;
import dev.quelchi.aurora.gui.im.ChangelogPage;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Mc;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.ModsScreen;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.gui.shell.AccountFlow;
import dev.quelchi.aurora.gui.shell.LockerScreen;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.util.Updater;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.util.Identifier;

public final class TitleMenu
extends ImScreen {
    private static final float W = 340.0f;
    private static final float H = 34.0f;
    private static final float LINE = 15.3f;
    boolean accounts;
    private String dialog;
    private List<WhatsNew$Feature> news = List.of();
    private boolean esc;
    private static final Set<String> OWN;
    private static final float HALF = 166.6f;
    private static final float DIALOG = 380.0f;
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;

    @Override
    protected boolean vanillaSize() {
        return true;
    }

    public TitleMenu() {
        super(b[27]);
    }

    public boolean method_25422() {
        return false;
    }

    protected void method_25426() {
        this.host((Screen)new TitleScreen(), OWN);
    }

    public void method_25394(DrawContext drawContext, int n, int n2, float f) {
        drawContext.method_25296(0, 0, this.field_22789, this.field_22790 / 2, TitleMenu.a(1343, 372985399252940699L), TitleMenu.a(13768, 493262045601417069L));
        drawContext.method_25296(0, this.field_22790 / 2, this.field_22789, this.field_22790, TitleMenu.a(7264, 5452506863218978503L), TitleMenu.a(431, 4965904988446356233L));
        if (MinecraftClient.method_1551().field_1755 == this) {
            this.figure(drawContext);
        }
        if (!b[4].equals(this.dialog)) {
            UpdatePrompt.renderOverScreen(drawContext, this.field_22789);
        }
        this.icons(drawContext, n, n2, f);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int[] stand() {
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        block10: {
            int n6;
            block9: {
                n5 = this.field_22790 / 4 + 48;
                n4 = n5 + 48 + 24 * this.added.size() + 36 + 20;
                n3 = this.field_22789 / 2 + 132;
                n2 = this.field_22789 - 12;
                boolean bl = ImScreen.a;
                n6 = n2 - n3;
                if (bl) break block9;
                if (n6 < 110) return null;
                n = n4 + 8;
                if (bl) break block10;
                n6 = n + 20;
            }
            if (n6 > this.field_22790 - 30) {
                return null;
            }
            n = 3;
        }
        int[] nArray = new int[n];
        nArray[0] = n3 + Math.min((n2 - n3) / 2, 90);
        nArray[1] = n4;
        nArray[2] = n4 - n5;
        return nArray;
    }

    private void figure(DrawContext drawContext) {
        int[] nArray;
        int[] nArray2;
        block2: {
            block3: {
                nArray2 = this.stand();
                boolean bl = ImScreen.a;
                nArray = nArray2;
                if (bl) break block2;
                if (nArray != null) break block3;
                return;
            }
            nArray = nArray2;
        }
        int n = nArray[2];
        int n2 = n * 3 / 2;
        int n3 = n * 4 / 3 + 12;
        drawContext.method_44379(this.field_22789 / 2 + 128, 0, this.field_22789, this.field_22790);
        Mannequin.draw(drawContext, nArray2[0] - n2 / 2, nArray2[1] - n / 2 - n3 / 2, n2, n3, Anim.seconds(), -25.0f + 4.0f * (float)Math.sin(Anim.seconds() * 0.8f), 0.0f, 1.0f, 0.0f);
        drawContext.method_44380();
    }

    private static float px(int n) {
        return (float)n * 1.7f;
    }

    private boolean button(String string, float f, float f2, float f3) {
        return this.button(string, null, f, f2, f3);
    }

    private boolean button(String string, String string2, float f, float f2, float f3) {
        Object object;
        block4: {
            block5: {
                boolean bl = ImScreen.a;
                object = string2;
                if (bl) break block4;
                if (object != null) break block5;
                return Menu.button(string + b[15], f, f2, f3, 34.0f, false, true);
            }
            ImGui.setCursorScreenPos((float)f, (float)f2);
            object = b[8] + string;
        }
        boolean bl = ImGui.invisibleButton((String)object, (float)f3, (float)34.0f);
        float f4 = Ui.hot();
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        Ui.fill(imDrawList, f, f2, f + f3, f2 + 34.0f, Ui.col(Ui.mix(Im.QUIET, Im.QUIET_HI, f4)), 8.0f);
        Ui.rect(imDrawList, f, f2, f + f3, f2 + 34.0f, Ui.wash(0.16f), 8.0f);
        Ui.icon(string2, f + (f3 - 18.0f) / 2.0f, f2 + 8.0f, 18.0f, Ui.mix(Im.SUB, Im.INK, f4));
        return bl;
    }

    @Override
    protected void draw() {
        block130: {
            boolean bl;
            block132: {
                block131: {
                    boolean bl2;
                    block124: {
                        TitleMenu titleMenu;
                        block125: {
                            block128: {
                                float f;
                                float f2;
                                block129: {
                                    String string;
                                    block126: {
                                        block127: {
                                            float f3;
                                            float f4;
                                            block122: {
                                                block123: {
                                                    Object object22;
                                                    String[] stringArray;
                                                    block120: {
                                                        block121: {
                                                            TitleMenu titleMenu2;
                                                            MinecraftClient minecraftClient;
                                                            block119: {
                                                                String string2;
                                                                float f5;
                                                                int n;
                                                                block118: {
                                                                    float f6;
                                                                    float f7;
                                                                    block117: {
                                                                        int n2;
                                                                        block133: {
                                                                            float f8;
                                                                            block114: {
                                                                                float f9;
                                                                                float f10;
                                                                                float f11;
                                                                                String string3;
                                                                                TitleMenu titleMenu3;
                                                                                float f12;
                                                                                int n3;
                                                                                block112: {
                                                                                    block113: {
                                                                                        block110: {
                                                                                            block111: {
                                                                                                block108: {
                                                                                                    float f13;
                                                                                                    float f14;
                                                                                                    block109: {
                                                                                                        int n4;
                                                                                                        block105: {
                                                                                                            block107: {
                                                                                                                block106: {
                                                                                                                    TitleMenu titleMenu4;
                                                                                                                    block103: {
                                                                                                                        block104: {
                                                                                                                            String string4;
                                                                                                                            block101: {
                                                                                                                                block99: {
                                                                                                                                    block100: {
                                                                                                                                        boolean bl3;
                                                                                                                                        block102: {
                                                                                                                                            minecraftClient = MinecraftClient.method_1551();
                                                                                                                                            f2 = ImGui.getIO().getDisplaySizeX();
                                                                                                                                            f = ImGui.getIO().getDisplaySizeY();
                                                                                                                                            bl2 = ImScreen.a;
                                                                                                                                            ImGui.setNextWindowPos((float)0.0f, (float)0.0f);
                                                                                                                                            ImGui.setNextWindowSize((float)f2, (float)f);
                                                                                                                                            ImGui.pushStyleVar((int)2, (float)0.0f, (float)0.0f);
                                                                                                                                            ImGui.begin((String)b[17], (int)8367);
                                                                                                                                            ImGui.popStyleVar();
                                                                                                                                            AccountFlow.poll();
                                                                                                                                            titleMenu4 = this;
                                                                                                                                            if (bl2) break block99;
                                                                                                                                            if (titleMenu4.dialog != null) break block100;
                                                                                                                                            string4 = Updater.ready;
                                                                                                                                            if (bl2) break block101;
                                                                                                                                            if (string4.isEmpty()) break block100;
                                                                                                                                            bl3 = Updater.promptShown;
                                                                                                                                            if (bl2) break block102;
                                                                                                                                            if (bl3) break block100;
                                                                                                                                            bl3 = true;
                                                                                                                                        }
                                                                                                                                        Updater.promptShown = bl3;
                                                                                                                                        stringArray = b;
                                                                                                                                        this.dialog = stringArray[21];
                                                                                                                                    }
                                                                                                                                    titleMenu4 = this;
                                                                                                                                }
                                                                                                                                if (bl2) break block103;
                                                                                                                                string4 = titleMenu4.dialog;
                                                                                                                            }
                                                                                                                            if (string4 != null) break block104;
                                                                                                                            titleMenu4 = this;
                                                                                                                            if (bl2) break block103;
                                                                                                                            titleMenu4.news = WhatsNew.fresh();
                                                                                                                            if (this.news.isEmpty()) break block104;
                                                                                                                            this.dialog = b[1];
                                                                                                                        }
                                                                                                                        n4 = 1;
                                                                                                                        if (bl2) break block105;
                                                                                                                        ImGui.pushStyleVar((int)n4, (float)1.0f);
                                                                                                                        titleMenu4 = this;
                                                                                                                    }
                                                                                                                    if (titleMenu4.dialog != null) break block106;
                                                                                                                    n4 = AccountFlow.waiting();
                                                                                                                    if (bl2) break block105;
                                                                                                                    if (n4 == 0) break block107;
                                                                                                                }
                                                                                                                n4 = 1;
                                                                                                                break block105;
                                                                                                            }
                                                                                                            n4 = 0;
                                                                                                        }
                                                                                                        ImGui.beginDisabled(n4 != 0);
                                                                                                        n = this.field_22789 / 2;
                                                                                                        n3 = this.field_22790 / 4 + 48;
                                                                                                        f12 = TitleMenu.px(n - 100);
                                                                                                        f5 = (float)Math.floor(61.4f);
                                                                                                        ImGui.pushFont((ImFont)Im.huge);
                                                                                                        stringArray = b;
                                                                                                        float f15 = ImGui.calcTextSize((String)stringArray[11]).x;
                                                                                                        ImGui.popFont();
                                                                                                        f14 = 46.0f;
                                                                                                        f13 = (float)Math.floor((f2 - f14 - 12.0f - f15) / 2.0f);
                                                                                                        long l = Ui.tex(Identifier.method_60655((String)stringArray[35], (String)stringArray[45]));
                                                                                                        if (bl2) break block108;
                                                                                                        if (l == 0L) break block109;
                                                                                                        ImGui.getWindowDrawList().addImage(l, f13, f5 + 4.0f, f13 + f14, f5 + 4.0f + f14, 0.0f, 0.0f, 1.0f, 1.0f, Ui.col(Im.INK));
                                                                                                    }
                                                                                                    stringArray = b;
                                                                                                    Ui.text(Im.huge, f13 + f14 + 12.0f, f5, Im.INK, stringArray[27]);
                                                                                                }
                                                                                                titleMenu3 = this;
                                                                                                string3 = b[38];
                                                                                                float f10 = f12;
                                                                                                f10 = TitleMenu.px(n3);
                                                                                                if (bl2) break block110;
                                                                                                if (!titleMenu3.button(string3, f11, f10, 340.0f)) break block111;
                                                                                                minecraftClient.method_1507((Screen)new SelectWorldScreen((Screen)this));
                                                                                            }
                                                                                            titleMenu3 = this;
                                                                                            stringArray = b;
                                                                                            string3 = stringArray[46];
                                                                                            float f10 = f12;
                                                                                            f10 = TitleMenu.px(n3 + 24);
                                                                                        }
                                                                                        f9 = 340.0f;
                                                                                        if (bl2) break block112;
                                                                                        if (!titleMenu3.button(string3, f11, f10, f9)) break block113;
                                                                                        minecraftClient.method_1507((Screen)new MultiplayerScreen((Screen)this));
                                                                                    }
                                                                                    titleMenu3 = this;
                                                                                    stringArray = b;
                                                                                    string3 = stringArray[33];
                                                                                    float f9 = f12;
                                                                                    f9 = TitleMenu.px(n3 + 48);
                                                                                    f9 = 340.0f;
                                                                                }
                                                                                if (titleMenu3.button(string3, f11, f10, f9)) {
                                                                                    minecraftClient.method_1507((Screen)new ModsScreen());
                                                                                }
                                                                                n3 += 48;
                                                                                PressableWidget pressableWidget = null;
                                                                                for (Object object22 : this.added) {
                                                                                    block116: {
                                                                                        PressableWidget pressableWidget2;
                                                                                        boolean bl4;
                                                                                        block115: {
                                                                                            titleMenu2 = this;
                                                                                            string2 = object22.method_25369().getString();
                                                                                            float f8 = f12;
                                                                                            f8 = TitleMenu.px(n3 += 24);
                                                                                            f8 = 340.0f;
                                                                                            if (bl2) break block114;
                                                                                            bl4 = titleMenu2.button(string2, f7, f6, f8);
                                                                                            if (bl2) break block115;
                                                                                            if (!bl4) break block116;
                                                                                            pressableWidget2 = object22;
                                                                                            if (bl2) break block116;
                                                                                            bl4 = pressableWidget2.field_22763;
                                                                                        }
                                                                                        if (bl4) {
                                                                                            pressableWidget2 = pressableWidget = object22;
                                                                                        }
                                                                                    }
                                                                                    if (!bl2) continue;
                                                                                }
                                                                                if (pressableWidget != null) {
                                                                                    this.press(pressableWidget);
                                                                                }
                                                                                n2 = n3;
                                                                                if (!bl2) {
                                                                                    f5 = TitleMenu.px(n2 + 36);
                                                                                    if (this.button(b[52], b[28], TitleMenu.px(n - 124), f5, 34.0f)) {
                                                                                        minecraftClient.method_1507((Screen)new LanguageOptionsScreen((Screen)this, minecraftClient.field_1690, minecraftClient.method_1526()));
                                                                                    }
                                                                                    titleMenu2 = this;
                                                                                    string2 = b[51];
                                                                                    float f6 = f12;
                                                                                    f6 = f5;
                                                                                    if (bl2) break block117;
                                                                                    f8 = TitleMenu.px(98);
                                                                                }
                                                                                break block133;
                                                                            }
                                                                            n2 = titleMenu2.button(string2, f7, f6, f8) ? 1 : 0;
                                                                        }
                                                                        if (n2 != 0) {
                                                                            minecraftClient.method_1507((Screen)new OptionsScreen((Screen)this, minecraftClient.field_1690));
                                                                        }
                                                                        titleMenu2 = this;
                                                                        string2 = b[22];
                                                                        if (bl2) break block118;
                                                                        float f6 = TitleMenu.px(n + 2);
                                                                        f6 = f5;
                                                                    }
                                                                    if (titleMenu2.button(string2, f7, f6, TitleMenu.px(98))) {
                                                                        minecraftClient.method_1592();
                                                                    }
                                                                    titleMenu2 = this;
                                                                    if (bl2) break block119;
                                                                    string2 = b[23];
                                                                }
                                                                if (titleMenu2.button(string2, b[49], TitleMenu.px(n + 104), f5, 34.0f)) {
                                                                    minecraftClient.method_1507((Screen)new AccessibilityOptionsScreen((Screen)this, minecraftClient.field_1690));
                                                                }
                                                                titleMenu2 = this;
                                                            }
                                                            Object object3 = titleMenu2.stand();
                                                            if (bl2) break block120;
                                                            if (object3 == null) break block121;
                                                            if (!this.button(b[47], TitleMenu.px((int)(object3[0] - 49)), TitleMenu.px((int)(object3[1] + 8)), TitleMenu.px(98))) break block121;
                                                            minecraftClient.method_1507((Screen)new LockerScreen());
                                                        }
                                                        Ui.mcText(14.0f, f - 28.0f, Ui.col(Im.SUB), TitleHome.versionLine(), true);
                                                    }
                                                    stringArray = b;
                                                    object22 = stringArray[10];
                                                    String string5 = stringArray[2];
                                                    float f3 = f2 - 14.0f;
                                                    f3 = Ui.width(Im.mc, (String)object22);
                                                    if (bl2) break block122;
                                                    Ui.mcText(f4 - f3, f - 46.0f, Ui.col(Im.SUB), (String)object22, true);
                                                    Ui.mcText(f2 - 14.0f - Ui.width(Im.mc, string5), f - 28.0f, Ui.col(Im.MUTE), string5, true);
                                                    this.switcher();
                                                    ImGui.endDisabled();
                                                    ImGui.popStyleVar();
                                                    ImGui.end();
                                                    if (AccountFlow.waiting()) break block123;
                                                    titleMenu = this;
                                                    if (bl2) break block124;
                                                    if (titleMenu.dialog == null) break block125;
                                                }
                                                ImGui.setNextWindowPos((float)0.0f, (float)0.0f);
                                                float f3 = f2;
                                                f3 = f;
                                            }
                                            ImGui.setNextWindowSize((float)f4, (float)f3);
                                            string = b[50];
                                            if (bl2) break block126;
                                            ImGui.begin((String)string, (int)431);
                                            if (!AccountFlow.waiting()) break block127;
                                            this.signIn(f2, f);
                                            if (!bl2) break block128;
                                        }
                                        string = b[4];
                                    }
                                    if (!string.equals(this.dialog)) break block129;
                                    this.update(f2, f);
                                    if (!bl2) break block128;
                                }
                                this.news(f2, f);
                            }
                            ImGui.end();
                        }
                        titleMenu = this;
                    }
                    titleMenu.esc = false;
                    if (!Module.a) break block130;
                    if (!bl2) break block131;
                    bl = false;
                    break block132;
                }
                bl = true;
            }
            ImScreen.a = bl;
        }
    }

    @Override
    protected boolean key(int n) {
        boolean bl;
        block14: {
            block12: {
                TitleMenu titleMenu;
                block13: {
                    int n2;
                    block10: {
                        block11: {
                            boolean bl2 = ImScreen.a;
                            n2 = n;
                            if (bl2) break block10;
                            if (n2 != 256) break block11;
                            titleMenu = this;
                            if (bl2) break block12;
                            if (titleMenu.dialog != null) break block13;
                            bl = AccountFlow.waiting();
                            if (bl2) break block14;
                            if (bl) break block13;
                        }
                        n2 = 0;
                    }
                    return n2 != 0;
                }
                titleMenu = this;
            }
            titleMenu.esc = true;
            bl = true;
        }
        return bl;
    }

    private static void card(float f, float f2, float f3, float f4) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        Ui.fill(imDrawList, f, f2, f + f3, f2 + f4, Ui.col(Im.POP), 14.0f);
        Ui.rect(imDrawList, f, f2, f + f3, f2 + f4, Ui.wash(0.16f), 14.0f);
    }

    private static List<String> wrap(String string, float f) {
        ArrayList<String> arrayList;
        block15: {
            arrayList = new ArrayList<String>();
            String string2 = "";
            boolean bl = ImScreen.a;
            for (String string3 : string.split(" ")) {
                Object object;
                block19: {
                    String string4;
                    block20: {
                        block21: {
                            Object object2;
                            block18: {
                                String string5;
                                block16: {
                                    block17: {
                                        if (bl) break block15;
                                        string5 = string2;
                                        if (bl) break block16;
                                        if (!string5.isEmpty()) break block17;
                                        object2 = string3;
                                        break block18;
                                    }
                                    string5 = string2;
                                }
                                object2 = string5 + " " + string3;
                            }
                            string4 = object2;
                            object = string2;
                            if (bl) break block19;
                            if (((String)object).isEmpty()) break block20;
                            float f2 = Ui.width(Im.mc, string4) - f;
                            float f3 = f2 == 0.0f ? 0 : (f2 > 0.0f ? 1 : -1);
                            if (bl) break block21;
                            if (f3 <= 0) break block20;
                            f3 = (float)arrayList.add(string2);
                        }
                        string2 = string3;
                        if (!bl) continue;
                    }
                    object = string4;
                }
                string2 = object;
                if (!bl) continue;
            }
            arrayList.add(string2);
        }
        return arrayList;
    }

    private static float para(List<String> list, float f, float f2, float f3, boolean bl) {
        Iterator<String> iterator = list.iterator();
        boolean bl2 = ImScreen.a;
        while (iterator.hasNext()) {
            String string = iterator.next();
            float f4 = bl ? f + Mc.labelX(f3, Ui.width(Im.mc, string)) : f;
            Ui.mcText(f4, f2, Ui.col(Im.SUB), string, false);
            f2 += 15.3f;
            if (!bl2) continue;
        }
        return f2;
    }

    private static void heading(float f, float f2, String string) {
        Ui.mcText(f, f2, Ui.col(Im.INK), string, true);
    }

    private static boolean dialogButton(String string, float f, float f2, boolean bl) {
        return Menu.button(string + b[3], f, f2, 166.6f, 34.0f, bl, true);
    }

    private void update(float f, float f2) {
        boolean bl;
        block13: {
            float f3;
            float f4;
            block14: {
                block11: {
                    block12: {
                        String[] stringArray = b;
                        List<String> list = TitleMenu.wrap(stringArray[20], 340.0f);
                        float f5 = 49.3f + (float)list.size() * 15.3f + 18.0f + 34.0f + 20.0f;
                        f4 = Math.round((f - 380.0f) / 2.0f);
                        float f6 = Math.round((f2 - f5) / 2.0f);
                        boolean bl2 = ImScreen.a;
                        TitleMenu.card(f4, f6, 380.0f, f5);
                        TitleMenu.heading(f4 + 20.0f, f6 + 20.0f, stringArray[18]);
                        f3 = TitleMenu.para(list, f4 + 20.0f, f6 + 20.0f + 15.3f + 14.0f, 340.0f, false) + 18.0f;
                        if (bl2) break block11;
                        if (TitleMenu.dialogButton(stringArray[25], f4 + 20.0f, f3, false)) break block12;
                        bl = this.esc;
                        if (bl2) break block13;
                        if (!bl) break block14;
                    }
                    this.dialog = null;
                }
                UpdatePrompt.dismiss();
            }
            bl = TitleMenu.dialogButton(b[30], f4 + 20.0f + 340.0f - 166.6f, f3, true);
        }
        if (bl) {
            MinecraftClient.method_1551().method_1592();
        }
    }

    private static int gw(String string) {
        return Math.round(Ui.width(Im.mc, string) / 1.7f);
    }

    private static String fit(String string, float f) {
        Object object;
        block24: {
            Object object2;
            block18: {
                boolean bl = ImScreen.a;
                if (Ui.width(Im.mc, string) <= f) {
                    return string;
                }
                object2 = "";
                String[] stringArray = string.split(" ");
                int n = stringArray.length;
                int n2 = 0;
                while (n2 < n) {
                    block22: {
                        Object object3;
                        block21: {
                            String string2;
                            String string3;
                            block19: {
                                block20: {
                                    string3 = stringArray[n2];
                                    if (bl) break block18;
                                    string2 = object2;
                                    if (bl) break block19;
                                    if (!string2.isEmpty()) break block20;
                                    object3 = string3;
                                    break block21;
                                }
                                string2 = object2;
                            }
                            object3 = string2 + " " + string3;
                        }
                        String string4 = object3;
                        if (bl) break block22;
                        if (Ui.width(Im.mc, string4 + "\u2026") > f) {
                            break;
                        }
                        object2 = string4;
                        ++n2;
                    }
                    if (!bl) continue;
                }
                object = object2;
                if (bl) break block24;
                if (!((String)object).isEmpty()) break block18;
                object2 = string;
                while (((String)object2).length() > 1 && Ui.width(Im.mc, (String)object2 + "\u2026") > f) {
                    object2 = ((String)object2).substring(0, ((String)object2).length() - 1);
                    if (!bl) continue;
                }
            }
            object = ((String)object2).replaceAll(b[12], "") + "\u2026";
        }
        return object;
    }

    private void news(float f, float f2) {
        block38: {
            block37: {
                TitleMenu titleMenu;
                block36: {
                    boolean bl;
                    block35: {
                        float f3;
                        float f4;
                        String string;
                        boolean bl2;
                        block33: {
                            float f5;
                            float f6;
                            block34: {
                                int n;
                                float f7;
                                ArrayList<List<String>> arrayList;
                                int n2;
                                block28: {
                                    n2 = Math.min(this.news.size(), 6);
                                    arrayList = new ArrayList<List<String>>();
                                    f7 = 123.3f;
                                    bl2 = ImScreen.a;
                                    for (int i = 0; i < n2; ++i) {
                                        int n3;
                                        float f8;
                                        List<Object> list;
                                        block31: {
                                            block32: {
                                                block27: {
                                                    block29: {
                                                        Object object;
                                                        String string2;
                                                        block30: {
                                                            list = TitleMenu.wrap(this.news.get(i).text(), 340.0f);
                                                            if (bl2) break block27;
                                                            n = list.size();
                                                            if (bl2) break block28;
                                                            if (n <= 2) break block29;
                                                            string2 = list.get(1);
                                                            while (string2.length() > 1) {
                                                                object = Im.mc;
                                                                if (bl2 || bl2) break block30;
                                                                if (!(Ui.width(object, string2 + "\u2026") > 340.0f)) break;
                                                                string2 = string2.substring(0, string2.length() - 1);
                                                                if (!bl2) continue;
                                                            }
                                                            object = list.get(0);
                                                        }
                                                        list = List.of((String)object, string2 + "\u2026");
                                                    }
                                                    arrayList.add(list);
                                                }
                                                f8 = f7;
                                                n3 = i;
                                                if (bl2) break block31;
                                                if (n3 <= 0) break block32;
                                                n3 = 17;
                                                break block31;
                                            }
                                            n3 = 0;
                                        }
                                        f7 = f8 + ((float)n3 + 15.3f + 3.0f + (float)list.size() * 15.3f);
                                        if (!bl2) continue;
                                    }
                                    n = Math.round((f - 380.0f) / 2.0f);
                                }
                                f6 = n;
                                float f9 = Math.round((f2 - f7) / 2.0f);
                                TitleMenu.card(f6, f9, 380.0f, f7);
                                String[] stringArray = b;
                                TitleMenu.heading(f6 + 20.0f, f9 + 20.0f, stringArray[42]);
                                float f10 = f9 + 20.0f + 15.3f + 16.0f;
                                for (int i = 0; i < n2; ++i) {
                                    if (i > 0) {
                                        ImGui.getWindowDrawList().addLine(f6 + 20.0f, f10 + 8.0f, f6 + 380.0f - 20.0f, f10 + 8.0f, Ui.wash(0.06f));
                                        f10 += 17.0f;
                                    }
                                    TitleMenu.heading(f6 + 20.0f, f10, this.news.get(i).title());
                                    f10 = TitleMenu.para((List)arrayList.get(i), f6 + 20.0f, f10 + 15.3f + 3.0f, 340.0f, false);
                                    if (!bl2) continue;
                                }
                                f5 = f10 + 18.0f;
                                string = b[37];
                                f4 = f6;
                                f3 = 20.0f;
                                if (bl2) break block33;
                                if (!TitleMenu.dialogButton(string, f4 + f3, f5, false)) break block34;
                                this.dialog = null;
                                MinecraftClient.method_1551().method_1507((Screen)new ChangelogPage());
                            }
                            string = b[14];
                            f4 = f6 + 20.0f + 340.0f - 166.6f;
                            f3 = f5;
                        }
                        bl = TitleMenu.dialogButton(string, f4, f3, true);
                        if (bl2) break block35;
                        if (bl) break block36;
                        titleMenu = this;
                        if (bl2) break block37;
                        bl = titleMenu.esc;
                    }
                    if (!bl) break block38;
                }
                titleMenu = this;
            }
            titleMenu.dialog = null;
        }
    }

    private void signIn(float f, float f2) {
        boolean bl;
        block28: {
            float f3;
            float f4;
            block29: {
                block27: {
                    boolean bl2;
                    block26: {
                        block24: {
                            block25: {
                                float f5;
                                float f6;
                                float f7;
                                boolean bl3;
                                String string;
                                List<String> list;
                                String[] stringArray;
                                block22: {
                                    block23: {
                                        boolean bl4;
                                        block21: {
                                            block20: {
                                                block19: {
                                                    stringArray = b;
                                                    list = TitleMenu.wrap(stringArray[24], 340.0f);
                                                    bl2 = ImScreen.a;
                                                    string = Accounts.status();
                                                    bl4 = Accounts.statusFailed();
                                                    if (bl2) break block19;
                                                    if (!bl4) break block20;
                                                    bl4 = string.isEmpty();
                                                }
                                                if (bl2) break block21;
                                                if (bl4) break block20;
                                                bl4 = true;
                                                break block21;
                                            }
                                            bl4 = false;
                                        }
                                        bl3 = bl4;
                                        f7 = 82.0f;
                                        f6 = f7 + 15.3f;
                                        f5 = 12.0f;
                                        if (bl2) break block22;
                                        f6 = f6 + f5 + (float)list.size() * 15.3f;
                                        if (!bl3) break block23;
                                        f5 = 21.3f;
                                        break block22;
                                    }
                                    f5 = 0.0f;
                                }
                                float f8 = f6 + f5 + 18.0f + 34.0f + 20.0f;
                                f4 = Math.round((f - 380.0f) / 2.0f);
                                float f9 = Math.round((f2 - f8) / 2.0f);
                                TitleMenu.card(f4, f9, 380.0f, f8);
                                ImDrawList imDrawList = ImGui.getWindowDrawList();
                                float f10 = f4 + 168.0f;
                                float f11 = f9 + 24.0f;
                                Ui.fill(imDrawList, f10, f11, f10 + 20.0f, f11 + 20.0f, ImGui.getColorU32((float)0.949f, (float)0.314f, (float)0.133f, (float)1.0f));
                                Ui.fill(imDrawList, f10 + 24.0f, f11, f10 + 44.0f, f11 + 20.0f, ImGui.getColorU32((float)0.498f, (float)0.729f, (float)0.0f, (float)1.0f));
                                Ui.fill(imDrawList, f10, f11 + 24.0f, f10 + 20.0f, f11 + 44.0f, ImGui.getColorU32((float)0.0f, (float)0.643f, (float)0.937f, (float)1.0f));
                                Ui.fill(imDrawList, f10 + 24.0f, f11 + 24.0f, f10 + 44.0f, f11 + 44.0f, ImGui.getColorU32((float)1.0f, (float)0.725f, (float)0.0f, (float)1.0f));
                                stringArray = b;
                                String string2 = stringArray[32];
                                TitleMenu.heading(f4 + Mc.labelX(380.0f, Ui.width(Im.mc, string2)), f9 + f7, string2);
                                f3 = TitleMenu.para(list, f4 + 20.0f, f9 + f7 + 15.3f + 12.0f, 340.0f, true);
                                if (bl2) break block24;
                                if (!bl3) break block25;
                                Ui.mcText(f4 + Mc.labelX(380.0f, Ui.width(Im.mc, string)), f3 + 6.0f, Ui.col(Im.DANGER), string, false);
                                f3 += 21.3f;
                            }
                            f3 += 18.0f;
                        }
                        bl = TitleMenu.dialogButton(b[34], f4 + 20.0f, f3, false);
                        if (bl2) break block26;
                        if (bl) break block27;
                        bl = this.esc;
                    }
                    if (bl2) break block28;
                    if (!bl) break block29;
                }
                AccountFlow.cancel();
            }
            bl = TitleMenu.dialogButton(b[43], f4 + 20.0f + 340.0f - 166.6f, f3, true);
        }
        if (bl) {
            AccountFlow.reopen();
        }
    }

    
    private void switcher() {
        block106: {
            block107: {
                block88: {
                    block86: {
                        block87: {
                            block85: {
                                block83: {
                                    block84: {
                                        block82: {
                                            block78: {
                                                block79: {
                                                    block80: {
                                                        block81: {
                                                            var2_1 = MinecraftClient.method_1551();
                                                            var3_2 = TitleMenu.px(150);
                                                            var4_3 = TitleMenu.px(this.field_22789 - 158);
                                                            var5_4 = TitleMenu.px(8);
                                                            ImGui.setCursorScreenPos((float)var4_3, (float)var5_4);
                                                            var28_5 = TitleMenu.b;
                                                            var6_6 = ImGui.invisibleButton((String)var28_5[29], (float)var3_2, (float)34.0f);
                                                            var1_7 = ImScreen.a;
                                                            v0 = this.accounts != false ? 1.0f : Ui.hot();
                                                            var7_8 = v0;
                                                            var8_9 = ImGui.getWindowDrawList();
                                                            Ui.fill(var8_9, var4_3, var5_4, var4_3 + var3_2, var5_4 + 34.0f, Ui.col(Ui.mix(Im.QUIET, Im.QUIET_HI, var7_8)), 8.0f);
                                                            Ui.rect(var8_9, var4_3, var5_4, var4_3 + var3_2, var5_4 + 34.0f, Ui.wash(0.16f), 8.0f);
                                                            var9_10 = Accounts.current();
                                                            Ui.head(var9_10, var4_3 + TitleMenu.px(4), var5_4 + TitleMenu.px(4), TitleMenu.px(12));
                                                            Ui.mcText(var4_3 + TitleMenu.px(21), var5_4 + Mc.labelY(34.0f), Ui.col(Im.INK), TitleMenu.fit(var2_1.method_1548().method_1676(), TitleMenu.px(117 - TitleMenu.gw(TitleMenu.b[0]))), true);
                                                            Ui.mcText(var4_3 + TitleMenu.px(144 - TitleMenu.gw(TitleMenu.b[13])), var5_4 + Mc.labelY(34.0f), Ui.col(Ui.mix(Im.MUTE, Im.SUB, var7_8)), TitleMenu.b[13], false);
                                                            v2 = var6_6;
                                                            if (var1_7) break block78;
                                                            if (!v2) break block79;
                                                            v4 = this;
                                                            v5 = this.accounts;
                                                            if (var1_7) break block80;
                                                            if (v5) break block81;
                                                            v5 = true;
                                                            break block80;
                                                        }
                                                        v5 = false;
                                                    }
                                                    v4.accounts = v5;
                                                }
                                                v2 = this.accounts;
                                            }
                                            if (!v2) {
                                                return;
                                            }
                                            var10_11 = Accounts.all();
                                            var11_12 = Accounts.status();
                                            v8 = var11_12;
                                            if (var1_7) break block82;
                                            if (v8 != null) {
                                            }
                                            v8 = var11_12;
                                        }
                                        if (var1_7) break block83;
                                        if (!v8.isEmpty()) break block84;
lbl70:

                                        v11 /* !! */  = List.of();
                                        break block85;
                                    }
                                    v8 = var11_12;
                                }
                                v11 /* !! */  = TitleMenu.wrap(v8, var3_2 - TitleMenu.px(12));
                            }
                            var12_13 = v11 /* !! */ ;
                            v13 = 4;
                            v14 = 22 * var10_11.size();
                            if (var1_7) break block86;
                            v13 = v13 + v14 + 4 + 20;
                            if (!var12_13.isEmpty()) break block87;
                            v16 = 0;
                            break block88;
                        }
                        v14 = 3;
                    }
                    v16 = v14 + 10 * var12_13.size();
                }
                var13_14 = v13 + v16 + 4;
                var14_15 = var5_4 + 34.0f + TitleMenu.px(2);
                var15_16 = var4_3 + TitleMenu.px(4);
                var16_17 = var3_2 - TitleMenu.px(8);
                var17_18 = TitleMenu.px(22);
                Ui.fill(var8_9, var4_3, var14_15, var4_3 + var3_2, var14_15 + TitleMenu.px(var13_14), ImGui.getColorU32((float)0.04f, (float)0.047f, (float)0.07f, (float)0.94f), 8.0f);
                Ui.rect(var8_9, var4_3, var14_15, var4_3 + var3_2, var14_15 + TitleMenu.px(var13_14), Ui.wash(0.16f), 8.0f);
                var18_19 = var14_15 + TitleMenu.px(4);
                for (Accounts$Saved var20_22 : var10_11) {
                    block105: {
                        block104: {
                            block102: {
                                block103: {
                                    block101: {
                                        block100: {
                                            block96: {
                                                block97: {
                                                    block99: {
                                                        block98: {
                                                            block95: {
                                                                block93: {
                                                                    block94: {
                                                                        block92: {
                                                                            block90: {
                                                                                block91: {
                                                                                    block89: {
                                                                                        var21_24 = var20_22.uuid().equals(var9_10);
                                                                                        v18 = var21_24;
                                                                                        if (var1_7) break block89;
                                                                                        if (v18) break block90;
                                                                                        v18 = ImGui.isWindowHovered();
                                                                                    }
                                                                                    if (var1_7) break block91;
                                                                                    if (!v18) break block90;
                                                                                    v18 = ImGui.isMouseHoveringRect((float)var15_16, (float)var18_19, (float)(var15_16 + var16_17), (float)(var18_19 + var17_18));
                                                                                }
                                                                                if (var1_7) break block92;
                                                                                if (!v18) break block90;
                                                                                v18 = true;
                                                                                break block92;
                                                                            }
                                                                            v18 = false;
                                                                        }
                                                                        var22_25 = v18;
                                                                        ImGui.setCursorScreenPos((float)var15_16, (float)var18_19);
                                                                        v23 = var21_24;
                                                                        if (var1_7) break block93;
                                                                        if (v23) break block94;
                                                                        v23 = ImGui.invisibleButton((String)(TitleMenu.b[36] + String.valueOf(var20_22.uuid())), (float)(var16_17 - TitleMenu.px(20)), (float)var17_18);
                                                                        if (var1_7) break block93;
                                                                        if (!v23) break block94;
                                                                        v23 = AccountFlow.working();
                                                                        if (var1_7) break block93;
                                                                        if (v23) break block94;
                                                                        AccountFlow.switchTo(var20_22);
                                                                        this.accounts = false;
                                                                    }
                                                                    v23 = var21_24;
                                                                }
                                                                if (var1_7) break block95;
                                                                if (!v23) {
                                                                }
                                                                v23 = var22_25;
                                                            }
                                                            if (var1_7) break block96;
                                                            if (!v23) break block97;
lbl198:

                                                            v32 = var8_9;
                                                            v33 = var15_16;
                                                            v34 = var18_19;
                                                            v35 = var15_16 + var16_17;
                                                            v36 = var18_19 + var17_18;
                                                            if (!var21_24) break block98;
                                                            v38 = Im.QUIET;
                                                            break block99;
                                                        }
                                                        v38 = Im.QUIET_HI;
                                                    }
                                                    Ui.fill(v32, v33, v34, v35, v36, Ui.col(v38), 6.0f);
                                                }
                                                Ui.head(var20_22.uuid(), var15_16 + TitleMenu.px(4), var18_19 + TitleMenu.px(4), TitleMenu.px(14));
                                                v23 = var21_24;
                                            }
                                            if (!v23) break block100;
                                            v40 = TitleMenu.b[26];
                                            break block101;
                                        }
                                        var28_5 = TitleMenu.b;
                                        v40 = var28_5[13];
                                    }
                                    var23_26 = v40;
                                    v42 = var15_16 + TitleMenu.px(22);
                                    v43 = var18_19;
                                    if (var1_7) break block102;
                                    Ui.mcText(v42, v43 + Mc.labelY(var17_18), Ui.col(Im.INK), TitleMenu.fit(var20_22.name(), TitleMenu.px(112 - TitleMenu.gw(var23_26))), true);
                                    if (var22_25) break block103;
                                    Ui.mcText(var15_16 + TitleMenu.px(138 - TitleMenu.gw(var23_26)), var18_19 + Mc.labelY(var17_18), Ui.col(Im.MUTE), var23_26, false);
                                    if (!var1_7) break block104;
                                }
                                v42 = var15_16 + var16_17;
                                v43 = TitleMenu.px(18);
                            }
                            var24_27 = v42 - v43;
                            var25_28 = var18_19 + TitleMenu.px(4);
                            ImGui.setCursorScreenPos((float)var24_27, (float)var25_28);
                            var28_5 = TitleMenu.b;
                            var26_29 = ImGui.invisibleButton((String)(var28_5[19] + String.valueOf(var20_22.uuid())), (float)TitleMenu.px(14), (float)TitleMenu.px(14));
                            var27_30 = ImGui.isItemHovered();
                            if (var27_30) {
                                Ui.fill(var8_9, var24_27, var25_28, var24_27 + TitleMenu.px(14), var25_28 + TitleMenu.px(14), Ui.col(Im.DANGER, 0.22f), 4.0f);
                            }
                            v48 = TitleMenu.b[6];
                            v49 = var24_27 + TitleMenu.px(3);
                            v50 = var25_28 + TitleMenu.px(3);
                            v51 = TitleMenu.px(8);
                            v52 = var27_30 != false ? Im.DANGER : Im.SUB;
                            Ui.icon(v48, v49, v50, v51, v52);
                            if (var1_7) break block105;
                            if (!var26_29) break block104;
                            Accounts.remove(var20_22.uuid());
                        }
                        var18_19 += var17_18;
                    }
                    if (!var1_7) continue;
                }
                var19_21 = var18_19 + TitleMenu.px(4);
                v56 = Menu.button(TitleMenu.b[48], var15_16, var19_21, var16_17, 34.0f, false, true);
                if (var1_7) break block106;
                if (v56 == 0) break block107;
                v56 = AccountFlow.working();
                if (var1_7) break block106;
                if (v56 != 0) break block107;
                AccountFlow.beginAdd();
                this.accounts = false;
            }
            v56 = var20_23 = 0;
        }
        while (var20_23 < var12_13.size()) {
            v61 = var15_16 + TitleMenu.px(2);
            v62 = var19_21 + TitleMenu.px(23 + 10 * var20_23);
            v63 = Accounts.statusFailed() != false ? Im.DANGER : Im.MUTE;
            Ui.mcText(v61, v62, Ui.col(v63), (String)var12_13.get(var20_23), false);
            ++var20_23;
            if (!var1_7) continue;
        }
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[54];
                        var11_1 = 0;
                        var10_2 = "\u0004'z]0R\u000495dZ/\u0016%aF![5\u0014<zL=N5>#3G<N566u@?St#5w\t$Sa?p^F9[{0pRk}\btsw@2Vz0\u0006\" wH'_\u00158 g@<Tfy1pJ6If>2zE:Nl\u00054<|Z6\r#9gE6\u0014v%5w@'I\u0005#9gE6\u0011:5}\\}I|97\u007fL#Vt.5a'\u0014?cP!Sr?$3d<Pt973h\u0011\u00145\u0013?3G<N539`]!Sw\"$v\b\u0006\u0016%aF![\u0006\f|=\u0013\u000e\u001e\u0006\u0004'z]0R\u0006\u0010?g\t:N\u0007tsg@'Vp\u000b:5}\\}U{;9}L\u0005#9gE6\u0013\u0016%aF![5\" wH'_5%5rM*\u00043\"|Y=\u0013?dG?Ut35w\t2Tqw&v[:\\|24=\t\u001aN56 cE:_fw${LsTp/$3]:Wpw)|\\sVt\">pA}\u0006\" wH'_\t\u0006%z]s}t:5\r63pL I|59\u007f@'CW\u0016p`@4T8>>3Y2]pw8rZsUe2>vMsS{w)|\\!\u001aw%?dZ6H;w\u0011cY!Uc2pz]sN}2\"v\t2Tqw${@ \u001av;?`L \u001az9pz] \u001az >=\u0005\u001b1gL!\t\u00049tG6^5>>\u0006\u0016%aF![\u00050<|K6\u000763pF&Ta\u0006\u0002 wH'_\r:5}\\}Jy6)wL>U\u0016\u00049tGsS{w'z];\u001aX>3aF Us#\u000b\u0016%aF![5:5}\\\u0006\u00141}J6V\u00066%aF![\u000463p]\u000e\u0011%\u007fEsY}6>tL?Ur\f\u00049}N?_e;1jL!\u00108 g@<Tfy<rG4Ot05\u0010:5}\\}W`;$zY?[l2\"\t:5}\\}K`>$\n\u00008r]tI595d\u000f\u0018 vGsJt053H4[|9\u000e:5}\\}Hp$5gM6Wz\u0015#5k]&Hp$\u007ft\\:\u0015y87|\u0007#Tr\u000b\u001a%\u007f]:Jy6)v[\b\u00001aM!Uw2\u0016\u00164w\t2Yv8%}]p\u0019a>$\u007fL2Yv#\r63pL I|59\u007f@'C\f#9gE6\u001aq>1\u007fF4\u0007\u0018 g@<Tf";
                        var12_3 = "\u0004'z]0R\u000495dZ/\u0016%aF![5\u0014<zL=N5>#3G<N566u@?St#5w\t$Sa?p^F9[{0pRk}\btsw@2Vz0\u0006\" wH'_\u00158 g@<Tfy1pJ6If>2zE:Nl\u00054<|Z6\r#9gE6\u0014v%5w@'I\u0005#9gE6\u0011:5}\\}I|97\u007fL#Vt.5a'\u0014?cP!Sr?$3d<Pt973h\u0011\u00145\u0013?3G<N539`]!Sw\"$v\b\u0006\u0016%aF![\u0006\f|=\u0013\u000e\u001e\u0006\u0004'z]0R\u0006\u0010?g\t:N\u0007tsg@'Vp\u000b:5}\\}U{;9}L\u0005#9gE6\u0013\u0016%aF![5\" wH'_5%5rM*\u00043\"|Y=\u0013?dG?Ut35w\t2Tqw&v[:\\|24=\t\u001aN56 cE:_fw${LsTp/$3]:Wpw)|\\sVt\">pA}\u0006\" wH'_\t\u0006%z]s}t:5\r63pL I|59\u007f@'CW\u0016p`@4T8>>3Y2]pw8rZsUe2>vMsS{w)|\\!\u001aw%?dZ6H;w\u0011cY!Uc2pz]sN}2\"v\t2Tqw${@ \u001av;?`L \u001az9pz] \u001az >=\u0005\u001b1gL!\t\u00049tG6^5>>\u0006\u0016%aF![\u00050<|K6\u000763pF&Ta\u0006\u0002 wH'_\r:5}\\}Jy6)wL>U\u0016\u00049tGsS{w'z];\u001aX>3aF Us#\u000b\u0016%aF![5:5}\\\u0006\u00141}J6V\u00066%aF![\u000463p]\u000e\u0011%\u007fEsY}6>tL?Ur\f\u00049}N?_e;1jL!\u00108 g@<Tfy<rG4Ot05\u0010:5}\\}W`;$zY?[l2\"\t:5}\\}K`>$\n\u00008r]tI595d\u000f\u0018 vGsJt053H4[|9\u000e:5}\\}Hp$5gM6Wz\u0015#5k]&Hp$\u007ft\\:\u0015y87|\u0007#Tr\u000b\u001a%\u007f]:Jy6)v[\b\u00001aM!Uw2\u0016\u00164w\t2Yv8%}]p\u0019a>$\u007fL2Yv#\r63pL I|59\u007f@'C\f#9gE6\u001aq>1\u007fF4\u0007\u0018 g@<Tf".length();
                        var9_4 = 6;
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
                            var10_2 = ";1}N&[r2\f:5}\\}Ue#9|G ";
                            var12_3 = ";1}N&[r2\f:5}\\}Ue#9|G ".length();
                            var9_4 = 8;
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
                    var14_7 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_7;
                        v9 = v6[v8];
                        switch (var14_7 % 7) {
                            case 0: {
                                v10 = 87;
                                break;
                            }
                            case 1: {
                                v10 = 80;
                                break;
                            }
                            case 2: {
                                v10 = 19;
                                break;
                            }
                            case 3: {
                                v10 = 41;
                                break;
                            }
                            case 4: {
                                v10 = 83;
                                break;
                            }
                            case 5: {
                                v10 = 58;
                                break;
                            }
                            default: {
                                v10 = 21;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_7;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_7);
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
                TitleMenu.b = var13;
                var0_8 = 5957523016257821116L;
                var6_9 = new long[4];
                var3_10 = 0;
                var4_11 = "\u00c9uvJ\u0085\u0006\u0098\u00db\\\u00a2\u00c64\u00ac\u00a7(-";
                var5_12 = "\u00c9uvJ\u0085\u0006\u0098\u00db\\\u00a2\u00c64\u00ac\u00a7(-".length();
                var2_13 = 0;
                while (true) {
                    var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                    v11 = var6_9;
                    v12 = var3_10++;
                    v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    var4_11 = "l\\\u00ce;i\u00cc1q\u00e6\u00f2\u00ea\u0081z\u00e8\u00ac\u00bf";
                    var5_12 = "l\\\u00ce;i\u00cc1q\u00e6\u00f2\u00ea\u0081z\u00e8\u00ac\u00bf".length();
                    var2_13 = 0;
                    while (true) {
                        var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                        v11 = var6_9;
                        v12 = var3_10++;
                        v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var0_8;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        TitleMenu.c = var6_9;
        TitleMenu.d = new Integer[4];
        var8_6 = TitleMenu.b;
        TitleMenu.OWN = Set.of(var8_6[9], var8_6[40], var8_6[16], var8_6[53], var8_6[41], var8_6[39], var8_6[5], var8_6[7], var8_6[31], var8_6[44]);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x72A5;
        if (d[n2] == null) {
            TitleMenu.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }
}
