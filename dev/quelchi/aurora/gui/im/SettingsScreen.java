package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync$Release;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.gui.im.ChangelogPage;
import dev.quelchi.aurora.gui.im.ChangelogPage$Line;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.PlusScreen;
import dev.quelchi.aurora.gui.im.SettingsScreen$Sections;
import dev.quelchi.aurora.gui.im.Themes;
import dev.quelchi.aurora.gui.im.Themes$Look;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.gui.shell.Switches;
import dev.quelchi.aurora.input.AuroraKeys;
import dev.quelchi.aurora.social.Plus;
import dev.quelchi.aurora.util.BorderlessFit;
import dev.quelchi.aurora.util.KeyUtil;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public final class SettingsScreen
extends ImScreen {
    private static final float LIST = 300.0f;
    private static final float NAV = 46.0f;
    private static final float ROW = 48.0f;
    private static final float KID = 44.0f;
    private static final float PAD = 24.0f;
    private static final float GAP = 44.0f;
    private static final float TILE_H = 52.0f;
    private static final float TILE_MIN = 150.0f;
    private static final float SWATCH = 28.0f;
    private static final String[] SECTIONS;
    private static final DateTimeFormatter WHEN;
    private static float kept;
    private boolean placed;
    private boolean listening;
    private final float[] tops = new float[SECTIONS.length];
    private float aim = -1.0f;
    private float set;
    private int asked;
    private final List<List<ChangelogPage$Line>> notes = new ArrayList<List<ChangelogPage$Line>>();
    private List<CosmeticsSync$Release> wrapped;
    private float wrappedTo;
    private int bound = -1;
    private static final String[] b;

    public SettingsScreen() {
        super(b[33]);
    }

    protected void method_25426() {
        CosmeticsSync.fetchReleases();
    }

    @Override
    protected boolean key(int n) {
        int n2;
        block10: {
            block11: {
                boolean bl;
                block8: {
                    SettingsScreen settingsScreen;
                    block9: {
                        bl = ImScreen.a;
                        settingsScreen = this;
                        if (bl) break block8;
                        if (settingsScreen.listening) break block9;
                        return false;
                    }
                    settingsScreen = this;
                }
                settingsScreen.listening = false;
                n2 = n;
                if (bl) break block10;
                if (n2 == 256) break block11;
                AuroraKeys.menu.method_1422(InputUtil.Type.field_1668.method_1447(n));
                KeyBinding.method_1426();
                MinecraftClient.method_1551().field_1690.method_1640();
                this.bound = n;
            }
            n2 = 1;
        }
        return n2 != 0;
    }

    @Override
    public boolean shutsOnMenuKey() {
        int n;
        block14: {
            block13: {
                block15: {
                    SettingsScreen settingsScreen;
                    boolean bl;
                    block12: {
                        bl = ImScreen.a;
                        settingsScreen = this;
                        if (bl) break block12;
                        if (settingsScreen.listening) break block13;
                        settingsScreen = this;
                    }
                    n = settingsScreen.bound;
                    if (bl) break block14;
                    if (n == -1) break block15;
                    n = this.bound;
                    if (bl) break block14;
                    if (KeyUtil.isKeyPressed(n)) break block13;
                }
                n = 1;
                break block14;
            }
            n = 0;
        }
        return n != 0;
    }

    
    @Override
    protected void draw() {
        block26: {
            block25: {
                var17_1 = SettingsScreen.b;
                Menu.top(var17_1[1], new String[0], -1, 0.0f);
                var2_2 = Menu.x;
                var1_3 = ImScreen.a;
                var3_4 = Menu.y + 64.0f;
                var4_5 = Menu.w;
                var5_6 = Menu.h - 64.0f;
                var6_7 = ImGui.getWindowDrawList();
                var6_7.addLine(var2_2 + 300.0f, var3_4, var2_2 + 300.0f, var3_4 + var5_6, Ui.col(Im.LINE));
                v0 = this;
                if (var1_3) // goto
                if (v0.aim >= 0.0f) {
                    v1 = this.asked;
                } else {
                    v0 = this;
lbl17:

                    v1 = SettingsScreen$Sections.at(v0.tops, SettingsScreen.kept);
                }
                var7_8 = v1;
                var8_9 = var2_2 + 12.0f;
                var9_10 = var3_4 + 16.0f;
                var10_11 = 276.0f;
                var11_12 = 0;
                while (var11_12 < SettingsScreen.SECTIONS.length) {
                    block31: {
                        block30: {
                            block29: {
                                block27: {
                                    block28: {
                                        ImGui.setCursorScreenPos((float)var8_9, (float)var9_10);
                                        v2 = var11_12;
                                        if (var1_3) break block25;
                                        var17_1 = SettingsScreen.b;
                                        var12_14 = ImGui.invisibleButton((String)(var17_1[6] + v2), (float)var10_11, (float)46.0f);
                                        var13_16 = Ui.hot();
                                        v3 = var11_12;
                                        if (var1_3) break block27;
                                        if (v3 != var7_8) break block28;
                                        var6_7.addRectFilled(var8_9, var9_10, var8_9 + var10_11, var9_10 + 46.0f, Ui.col(Ui.tint(0.1f)), 10.0f);
                                        if (!var1_3) break block29;
                                    }
                                    v3 = (cfr_temp_0 = var13_16 - 0.01f) == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
                                }
                                if (var1_3) break block30;
                                if (v3 > 0) {
                                    var6_7.addRectFilled(var8_9, var9_10, var8_9 + var10_11, var9_10 + 46.0f, Ui.wash(0.04f * var13_16), 10.0f);
                                }
                            }
                            Ui.text(Im.sora14, var8_9 + 16.0f, Ui.mid(Im.sora14, var9_10, 46.0f), Im.INK, SettingsScreen.SECTIONS[var11_12]);
                            if (var1_3) break block31;
                            v3 = (int)var12_14;
                        }
                        if (v3 != 0) {
                            this.aim = this.tops[var11_12];
                            this.asked = var11_12;
                            this.set = SettingsScreen.kept;
                            this.listening = false;
                        }
                        var9_10 += 46.0f;
                        ++var11_12;
                    }
                    if (!var1_3) continue;
                }
                ImGui.setCursorScreenPos((float)(var2_2 + 300.0f + 1.0f), (float)(var3_4 + 1.0f));
                ImGui.pushStyleColor((int)3, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                ImGui.pushStyleColor((int)14, (int)Ui.wash(0.04f));
                ImGui.pushStyleColor((int)15, (int)Ui.wash(0.22f));
                ImGui.pushStyleColor((int)16, (int)Ui.wash(0.32f));
                ImGui.pushStyleColor((int)17, (int)Ui.wash(0.32f));
                ImGui.pushStyleVar((int)8, (float)0.0f);
                ImGui.pushStyleVar((int)18, (float)3.0f);
                v2 = 19;
            }
            ImGui.pushStyleVar((int)v2, (float)2.0f);
            var11_13 = var5_6 - 2.0f;
            ImGui.beginChild((String)SettingsScreen.b[26], (float)(var4_5 - 300.0f - 7.0f), (float)var11_13, (boolean)false);
            this.scroll();
            var12_15 = ImGui.getCursorScreenPosY();
            var13_16 = var2_2 + 300.0f + 32.0f;
            var14_17 = var4_5 - 300.0f - 64.0f;
            var15_18 = var12_15 + 24.0f;
            for (var16_19 = 0; var16_19 < SettingsScreen.SECTIONS.length; ++var16_19) {
                block33: {
                    block32: {
                        v4 = this;
                        if (var1_3) break block32;
                        v4.tops[var16_19] = var15_18 - var12_15 - 24.0f;
                        Ui.text(Im.head, var13_16, Ui.mid(Im.head, var15_18, 30.0f), Im.INK, SettingsScreen.SECTIONS[var16_19]);
                        if (var1_3) break block26;
                        var15_18 += 42.0f;
                        switch (var16_19) {
                            case 0: {
                                v5 = this.general(var13_16, var15_18, var14_17);
                                break block33;
                            }
                            case 1: {
                                v5 = this.appearance(var13_16, var15_18 + 2.0f, var14_17);
                                break block33;
                            }
                            case 2: {
                                v5 = this.notifications(var13_16, var15_18, var14_17);
                                break block33;
                            }
                            default: {
                                v4 = this;
                            }
                        }
                    }
                    v5 = v4.about(var13_16, var15_18, var14_17);
                }
                var15_18 = v5 + 44.0f;
                if (!var1_3) continue;
            }
            ImGui.setCursorScreenPos((float)var13_16, (float)Math.max(var15_18, var12_15 + this.tops[SettingsScreen.SECTIONS.length - 1] + var11_13));
            ImGui.dummy((float)1.0f, (float)1.0f);
            SettingsScreen.kept = ImGui.getScrollY();
            ImGui.endChild();
            ImGui.popStyleVar((int)3);
            ImGui.popStyleColor((int)5);
            Menu.end();
        }
    }

    private void scroll() {
        float f;
        float f2;
        block20: {
            block21: {
                float f3;
                float f4;
                boolean bl;
                block18: {
                    float f5;
                    block19: {
                        float f6;
                        block16: {
                            block17: {
                                SettingsScreen settingsScreen;
                                block14: {
                                    block15: {
                                        bl = ImScreen.a;
                                        settingsScreen = this;
                                        if (bl) break block14;
                                        if (settingsScreen.placed) break block15;
                                        this.placed = true;
                                        ImGui.setScrollY((float)kept);
                                        return;
                                    }
                                    settingsScreen = this;
                                }
                                f6 = settingsScreen.aim;
                                if (bl) break block16;
                                if (!(f6 < 0.0f)) break block17;
                                return;
                            }
                            f6 = ImGui.getScrollY();
                        }
                        f5 = f6;
                        f4 = Math.min(this.aim, ImGui.getScrollMaxY());
                        f3 = Math.abs(f5 - this.set);
                        if (bl) break block18;
                        if (!(f3 > 1.5f)) break block19;
                        this.aim = -1.0f;
                        return;
                    }
                    f3 = f5 + (f4 - f5) * Math.min(1.0f, ImGui.getIO().getDeltaTime() * 14.0f);
                }
                f2 = f3;
                f = f4;
                if (bl) break block20;
                if (!(Math.abs(f - f2) < 0.5f)) break block21;
                f2 = f4;
                this.aim = -1.0f;
            }
            f = f2;
        }
        ImGui.setScrollY((float)f);
        this.set = f2;
    }

    private float general(float f, float f2, float f3) {
        float f4;
        block34: {
            block31: {
                boolean bl;
                block32: {
                    block33: {
                        Object object;
                        float f5;
                        boolean bl2;
                        block27: {
                            block28: {
                                boolean bl3;
                                block29: {
                                    block30: {
                                        float f6;
                                        float f7;
                                        block23: {
                                            String[] stringArray;
                                            block24: {
                                                boolean bl4;
                                                block25: {
                                                    block26: {
                                                        String string;
                                                        float f8;
                                                        block22: {
                                                            int n;
                                                            block20: {
                                                                block21: {
                                                                    stringArray = b;
                                                                    f2 = SettingsScreen.row(stringArray[11], f, f2, f3, 48.0f);
                                                                    bl2 = ImScreen.a;
                                                                    f5 = f + f3;
                                                                    f8 = f2 - 48.0f + 14.0f;
                                                                    n = this.listening;
                                                                    if (bl2) break block20;
                                                                    if (n == 0) break block21;
                                                                    string = b[2];
                                                                    break block22;
                                                                }
                                                                n = AuroraKeys.menuCode();
                                                            }
                                                            string = Switches.keyName(n);
                                                        }
                                                        object = string + b[18];
                                                        float f6 = f5;
                                                        f6 = f8;
                                                        if (bl2) break block23;
                                                        if (!Menu.tag((String)object, f7, f6, this.listening)) break block24;
                                                        SettingsScreen settingsScreen = this;
                                                        bl4 = this.listening;
                                                        if (bl2) break block25;
                                                        if (bl4) break block26;
                                                        bl4 = true;
                                                        break block25;
                                                    }
                                                    bl4 = false;
                                                }
                                                settingsScreen.listening = bl4;
                                            }
                                            stringArray = b;
                                            f2 = SettingsScreen.row(stringArray[12], f, f2, f3, 48.0f);
                                            SettingsScreen.size(f5, f2 - 48.0f + 8.0f);
                                            object = stringArray[30];
                                            if (bl2) break block27;
                                            float f6 = f;
                                            f6 = f2;
                                        }
                                        f2 = SettingsScreen.row((String)object, f7, f6, f3, 48.0f);
                                        if (!SettingsScreen.flip(2, f5, f2 - 48.0f, 48.0f, Cosmetics.showOthers())) break block28;
                                        bl3 = Cosmetics.showOthers();
                                        if (bl2) break block29;
                                        if (bl3) break block30;
                                        bl3 = true;
                                        break block29;
                                    }
                                    bl3 = false;
                                }
                                Cosmetics.setShowOthers(bl3);
                                ConfigManager.save();
                            }
                            object = b[31];
                        }
                        f4 = SettingsScreen.row((String)object, f, f2, f3, 48.0f);
                        if (bl2) break block34;
                        f2 = f4;
                        if (!SettingsScreen.flip(11, f5, f2 - 48.0f, 48.0f, BorderlessFit.enabled())) break block31;
                        bl = BorderlessFit.enabled();
                        if (bl2) break block32;
                        if (bl) break block33;
                        bl = true;
                        break block32;
                    }
                    bl = false;
                }
                BorderlessFit.setEnabled(bl);
                ConfigManager.save();
            }
            f4 = f2;
        }
        return f4;
    }

    private static float row(String string, float f, float f2, float f3, float f4) {
        Ui.text(Im.sora14, f, Ui.mid(Im.sora14, f2, f4), Im.INK, string);
        ImGui.getWindowDrawList().addLine(f, f2 + f4 - 0.5f, f + f3, f2 + f4 - 0.5f, Ui.col(Im.LINE));
        return f2 + f4;
    }

    private static void size(float f, float f2) {
        float f3;
        boolean bl;
        int n;
        ImDrawList imDrawList;
        block18: {
            imDrawList = ImGui.getWindowDrawList();
            float f4 = 6 + 2 * (Themes.SIZE_NAMES.length - 1);
            String[] stringArray = Themes.SIZE_NAMES;
            n = stringArray.length;
            bl = ImScreen.a;
            for (int i = 0; i < n; ++i) {
                String string = stringArray[i];
                f3 = f4;
                if (!bl) {
                    f4 = f3 + (Ui.width(Im.bold13, string) + 24.0f);
                    if (!bl) continue;
                }
                break block18;
            }
            f3 = f - f4;
        }
        float f5 = f3;
        Ui.fill(imDrawList, f5, f2, f, f2 + 32.0f, Ui.wash(0.05f), 9.0f);
        f5 += 3.0f;
        for (n = 0; n < Themes.SIZE_NAMES.length; ++n) {
            float f6;
            int n2;
            float f7;
            String string;
            block21: {
                block22: {
                    int n3;
                    block23: {
                        int n4;
                        block19: {
                            block20: {
                                string = Themes.SIZE_NAMES[n];
                                f7 = Ui.width(Im.bold13, string) + 24.0f;
                                n4 = Themes.sizeIndex();
                                if (bl) break block19;
                                if (n4 != n) break block20;
                                n4 = 1;
                                break block19;
                            }
                            n4 = 0;
                        }
                        n2 = n4;
                        f6 = f5;
                        if (bl) break block21;
                        ImGui.setCursorScreenPos((float)f6, (float)(f2 + 3.0f));
                        if (!ImGui.invisibleButton((String)(b[0] + n), (float)f7, (float)26.0f)) break block22;
                        n3 = n2;
                        if (bl) break block23;
                        if (n3 != 0) break block22;
                        n3 = n;
                    }
                    Themes.setSize(n3);
                    ConfigManager.save();
                }
                f6 = Ui.hot();
            }
            float f8 = f6;
            if (n2 != 0) {
                Ui.fill(imDrawList, f5, f2 + 3.0f, f5 + f7, f2 + 29.0f, Ui.wash(0.11f), 7.0f);
            }
            ImFont imFont = Im.bold13;
            float f9 = f5 + 12.0f;
            float f10 = Ui.mid(Im.bold13, f2 + 3.0f, 26.0f);
            float[] fArray = n2 != 0 ? Im.INK : Ui.mix(Im.MUTE, Im.SUB, f8);
            Ui.text(imFont, f9, f10, fArray, string);
            f5 += f7 + 2.0f;
            if (!bl) continue;
        }
    }

    private static boolean flip(int n, float f, float f2, float f3, boolean bl) {
        return Ui.toggle(b[27] + n, f - 32.0f, f2 + (f3 - 16.0f) / 2.0f, 32.0f, 16.0f, bl);
    }

    private float notifications(float f, float f2, float f3) {
        float f4;
        block35: {
            block30: {
                boolean bl;
                block31: {
                    block32: {
                        boolean bl2;
                        float f5;
                        block34: {
                            float f6;
                            float f7;
                            block27: {
                                boolean bl3;
                                block28: {
                                    block29: {
                                        String string;
                                        block33: {
                                            String[] stringArray;
                                            block24: {
                                                boolean bl4;
                                                block25: {
                                                    block26: {
                                                        block21: {
                                                            int n;
                                                            block22: {
                                                                block23: {
                                                                    block20: {
                                                                        f5 = f + f3;
                                                                        bl2 = ImScreen.a;
                                                                        stringArray = b;
                                                                        f2 = SettingsScreen.row(stringArray[32], f, f2, f3, 48.0f);
                                                                        n = 3;
                                                                        if (bl2) break block20;
                                                                        if (!SettingsScreen.flip(n, f5, f2 - 48.0f, 48.0f, SocialToast.enabled())) break block21;
                                                                        n = SocialToast.enabled() ? 1 : 0;
                                                                    }
                                                                    if (bl2) break block22;
                                                                    if (n != 0) break block23;
                                                                    n = 1;
                                                                    break block22;
                                                                }
                                                                n = 0;
                                                            }
                                                            SocialToast.setEnabled(n != 0);
                                                            ConfigManager.save();
                                                        }
                                                        ImDrawList imDrawList = ImGui.getWindowDrawList();
                                                        imDrawList.addRectFilled(f + 2.0f, f2, f + 4.0f, f2 + 132.0f, Ui.col(Menu.SOFT, 0.22f));
                                                        int n = 0;
                                                        float f8 = ImGui.getStyle().getAlpha();
                                                        float f9 = SocialToast.enabled() ? 1.0f : 0.4f;
                                                        ImGui.pushStyleVar((int)n, (float)(f8 * f9));
                                                        f7 = f + 18.0f;
                                                        f6 = f3 - 18.0f;
                                                        stringArray = b;
                                                        string = stringArray[4];
                                                        if (bl2) break block33;
                                                        f2 = SettingsScreen.kid(string, f7, f2, f6);
                                                        if (!SettingsScreen.flip(5, f5, f2 - 44.0f, 44.0f, SocialToast.requests())) break block24;
                                                        bl4 = SocialToast.requests();
                                                        if (bl2) break block25;
                                                        if (bl4) break block26;
                                                        bl4 = true;
                                                        break block25;
                                                    }
                                                    bl4 = false;
                                                }
                                                SocialToast.setRequests(bl4);
                                                ConfigManager.save();
                                            }
                                            stringArray = b;
                                            string = stringArray[15];
                                        }
                                        f4 = SettingsScreen.kid(string, f7, f2, f6);
                                        if (bl2) break block34;
                                        f2 = f4;
                                        if (!SettingsScreen.flip(6, f5, f2 - 44.0f, 44.0f, SocialToast.online())) break block27;
                                        bl3 = SocialToast.online();
                                        if (bl2) break block28;
                                        if (bl3) break block29;
                                        bl3 = true;
                                        break block28;
                                    }
                                    bl3 = false;
                                }
                                SocialToast.setOnline(bl3);
                                ConfigManager.save();
                            }
                            f4 = SettingsScreen.kid(b[17], f7, f2, f6);
                        }
                        if (bl2) break block35;
                        f2 = f4;
                        if (!SettingsScreen.flip(4, f5, f2 - 44.0f, 44.0f, SocialToast.sound())) break block30;
                        bl = SocialToast.sound();
                        if (bl2) break block31;
                        if (bl) break block32;
                        bl = true;
                        break block31;
                    }
                    bl = false;
                }
                SocialToast.setSound(bl);
                ConfigManager.save();
            }
            ImGui.popStyleVar();
            f4 = f2;
        }
        return f4;
    }

    private static float kid(String string, float f, float f2, float f3) {
        Ui.text(Im.sora13, f, Ui.mid(Im.sora13, f2, 44.0f), Im.SUB, string);
        ImGui.getWindowDrawList().addLine(f, f2 + 44.0f - 0.5f, f + f3, f2 + 44.0f - 0.5f, Ui.col(Im.LINE));
        return f2 + 44.0f;
    }

    private float about(float f, float f2, float f3) {
        int n;
        List<ChangelogPage$Line> list;
        ImDrawList imDrawList;
        boolean bl;
        block51: {
            int n2;
            block52: {
                block49: {
                    block50: {
                        CosmeticsSync$Release cosmeticsSync$Release;
                        block48: {
                            boolean bl2;
                            List<Object> list2;
                            block46: {
                                block43: {
                                    List<CosmeticsSync$Release> list22;
                                    block47: {
                                        String string;
                                        String[] stringArray;
                                        String string2;
                                        block41: {
                                            block42: {
                                                boolean bl3;
                                                block39: {
                                                    block40: {
                                                        list22 = CosmeticsSync.releases();
                                                        string2 = CosmeticsSync.myVersion();
                                                        stringArray = b;
                                                        f2 = SettingsScreen.row(stringArray[29], f, f2, f3, 48.0f);
                                                        bl = ImScreen.a;
                                                        bl3 = string2.isEmpty();
                                                        if (bl) break block39;
                                                        if (bl3) break block40;
                                                        string = string2;
                                                        break block41;
                                                    }
                                                    bl3 = list22.isEmpty();
                                                }
                                                if (!bl3) break block42;
                                                string = "\u2026";
                                                break block41;
                                            }
                                            stringArray = b;
                                            string = stringArray[5];
                                        }
                                        String string3 = string;
                                        Ui.text(Im.bold13, f + f3 - Ui.width(Im.bold13, string3), Ui.mid(Im.bold13, f2 - 48.0f, 48.0f), Im.SUB, string3);
                                        f2 += 22.0f;
                                        Object object = Im.sora13;
                                        if (!bl) {
                                            stringArray = b;
                                            Ui.text(object, f, Ui.mid(Im.sora13, f2, 16.0f), Im.INK, stringArray[25]);
                                            f2 += 26.0f;
                                            if (list22.isEmpty()) {
                                                Ui.text(Im.sub, f, Ui.mid(Im.sub, f2, 20.0f), Im.MUTE, stringArray[7]);
                                                return f2 + 20.0f;
                                            }
                                            object = list22.get(0);
                                        }
                                        cosmeticsSync$Release = (CosmeticsSync$Release)object;
                                        for (CosmeticsSync$Release object3 : list22) {
                                            block45: {
                                                CosmeticsSync$Release cosmeticsSync$Release2;
                                                block44: {
                                                    if (bl) break block43;
                                                    cosmeticsSync$Release2 = object3;
                                                    if (bl) break block44;
                                                    if (!cosmeticsSync$Release2.version().equals(string2)) break block45;
                                                    cosmeticsSync$Release2 = object3;
                                                }
                                                cosmeticsSync$Release = cosmeticsSync$Release2;
                                            }
                                            if (!bl) continue;
                                        }
                                        list2 = list22;
                                        if (bl) break block46;
                                        if (list2 != this.wrapped) break block47;
                                        float f4 = f3 - this.wrappedTo;
                                        bl2 = f4 == 0.0f ? 0 : (f4 > 0.0f ? 1 : -1);
                                        if (bl) break block48;
                                        if (!bl2) break block48;
                                    }
                                    this.wrapped = list22;
                                    this.wrappedTo = f3;
                                    this.notes.clear();
                                }
                                list2 = this.notes;
                            }
                            bl2 = list2.add(ChangelogPage.lines(cosmeticsSync$Release.notes(), f3));
                        }
                        imDrawList = ImGui.getWindowDrawList();
                        Ui.text(Im.sora16, f, Ui.mid(Im.sora16, f2, 22.0f), Im.INK, cosmeticsSync$Release.version());
                        if (bl) break block49;
                        if (cosmeticsSync$Release.at() <= 0L) break block50;
                        String string = WHEN.format(Instant.ofEpochSecond(cosmeticsSync$Release.at()));
                        Ui.text(Im.label, f + f3 - Ui.width(Im.label, string), Ui.mid(Im.label, f2, 22.0f), Im.MUTE, string);
                    }
                    f2 += 30.0f;
                }
                list = this.notes.get(0);
                n2 = list.isEmpty();
                if (bl) break block51;
                if (n2 == 0) break block52;
                Ui.text(Im.sub, f, Ui.mid(Im.sub, f2, 22.0f), Im.MUTE, b[8]);
                f2 += 22.0f;
            }
            n2 = n = 0;
        }
        while (n < list.size()) {
            int n3;
            ChangelogPage$Line changelogPage$Line;
            block55: {
                ChangelogPage$Line changelogPage$Line2;
                block53: {
                    block54: {
                        changelogPage$Line = list.get(n);
                        changelogPage$Line2 = changelogPage$Line;
                        if (bl) break block53;
                        if (!changelogPage$Line2.first()) break block54;
                        n3 = n;
                        if (bl) break block55;
                        if (n3 <= 0) break block54;
                        f2 += 3.0f;
                    }
                    changelogPage$Line2 = changelogPage$Line;
                }
                n3 = changelogPage$Line2.first();
            }
            if (n3 != 0) {
                imDrawList.addCircleFilled(f + 2.0f, f2 + 9.5f, 2.0f, Ui.col(Ui.soft()), 12);
            }
            Ui.text(Im.sub, f + 14.0f, Ui.mid(Im.sub, f2, 19.0f), Im.SUB, changelogPage$Line.text());
            f2 += 19.0f;
            ++n;
            if (!bl) continue;
        }
        return f2;
    }

    private static void heading(String string, float f, float f2) {
        Ui.text(Im.sora13, f, Ui.mid(Im.sora13, f2, 16.0f), Im.INK, string);
    }

    private float appearance(float f, float f2, float f3) {
        boolean bl = ImScreen.a;
        String[] stringArray = b;
        String string = stringArray[22];
        float f4 = f;
        float f5 = f2;
        if (!bl) {
            SettingsScreen.heading(string, f4, f5);
            f2 = SettingsScreen.themes(false, f, f2 + 26.0f, f3) + 16.0f;
            SettingsScreen.heading(stringArray[23], f, f2);
            f2 = SettingsScreen.themes(true, f, f2 + 26.0f, f3) + 18.0f;
            f2 = SettingsScreen.glass(f, f2, f3) + 18.0f;
            if (Themes.ownAccent()) {
                return f2;
            }
            stringArray = b;
            SettingsScreen.heading(stringArray[19], f, f2);
            f2 = SettingsScreen.swatches(Themes.PLAIN, false, f, f2 + 26.0f, f3) + 18.0f;
            string = stringArray[10];
            f4 = f;
            f5 = f2;
        }
        SettingsScreen.heading(string, f4, f5);
        return SettingsScreen.swatches(Themes.PLUS, true, f, f2 + 26.0f, f3);
    }

    private static float themes(boolean bl, float f, float f2, float f3) {
        int n = Math.max(1, (int)((f3 + 12.0f) / 162.0f));
        float f4 = (f3 - (float)((n - 1) * 12)) / (float)n;
        boolean bl2 = ImScreen.a;
        int n2 = 0;
        for (Themes$Look themes$Look : Themes$Look.values()) {
            Themes$Look themes$Look2;
            boolean bl3;
            float f5;
            float f6;
            block24: {
                block25: {
                    Themes$Look themes$Look3;
                    block27: {
                        block28: {
                            boolean bl4;
                            block26: {
                                if (themes$Look.plus != bl) {
                                    continue;
                                }
                                f6 = f + (float)(n2 % n) * (f4 + 12.0f);
                                f5 = f2 + (float)(n2 / n) * 87.0f;
                                ++n2;
                                boolean bl5 = Themes.look() == themes$Look;
                                bl3 = bl5;
                                ImFont imFont = Im.sora12;
                                float f7 = f6;
                                float f8 = Ui.mid(Im.sora12, f5, 16.0f);
                                float[] fArray = bl3 ? Im.INK : Im.SUB;
                                Ui.text(imFont, f7, f8, fArray, themes$Look.label);
                                ImGui.setCursorScreenPos((float)f6, (float)(f5 + 23.0f));
                                themes$Look2 = themes$Look;
                                if (bl2) break block24;
                                if (!ImGui.invisibleButton((String)(b[3] + themes$Look2.name()), (float)f4, (float)52.0f)) break block25;
                                bl4 = bl3;
                                if (bl2) break block26;
                                if (bl4) break block25;
                                themes$Look3 = themes$Look;
                                if (bl2) break block27;
                                bl4 = themes$Look3.plus;
                            }
                            if (!bl4 || Plus.active()) break block28;
                            MinecraftClient.method_1551().method_1507((Screen)new PlusScreen());
                            if (!bl2) break block25;
                        }
                        themes$Look3 = themes$Look;
                    }
                    Themes.set(themes$Look3, Themes.chosenAccent());
                    ConfigManager.save();
                }
                themes$Look2 = themes$Look;
            }
            SettingsScreen.mini(themes$Look2, f6, f5 + 23.0f, f4, bl3, Ui.hot());
            if (!bl2) continue;
        }
        return f2 + (float)((n2 + n - 1) / n) * 87.0f - 12.0f;
    }

    private static float[] rgb(int n, float f) {
        return new float[]{(float)(n >> 16 & 0xFF) / 255.0f, (float)(n >> 8 & 0xFF) / 255.0f, (float)(n & 0xFF) / 255.0f, f};
    }

    private static void mini(Themes$Look themes$Look, float f, float f2, float f3, boolean bl, float f4) {
        block31: {
            float f5;
            float f6;
            float f7;
            ImDrawList imDrawList;
            float f8;
            block29: {
                ImDrawList imDrawList2;
                block30: {
                    int n;
                    float f9;
                    float f10;
                    float f11;
                    float f12;
                    ImDrawList imDrawList3;
                    int n2;
                    int n3;
                    int n4;
                    boolean bl2;
                    block28: {
                        int n5;
                        block26: {
                            block27: {
                                float[] fArray;
                                float[] fArray2;
                                int n6;
                                block25: {
                                    int n7;
                                    block23: {
                                        block24: {
                                            int n8;
                                            block22: {
                                                int n9;
                                                block20: {
                                                    block21: {
                                                        imDrawList2 = ImGui.getWindowDrawList();
                                                        bl2 = ImScreen.a;
                                                        f8 = 52.0f;
                                                        int n10 = themes$Look == Themes$Look.DAYLIGHT ? 1 : 0;
                                                        n6 = n10;
                                                        fArray2 = SettingsScreen.rgb(themes$Look.accent, 1.0f);
                                                        n9 = n6;
                                                        if (bl2) break block20;
                                                        if (n9 == 0) break block21;
                                                        n8 = Ui.col(new float[]{0.078f, 0.094f, 0.157f, 0.22f});
                                                        break block22;
                                                    }
                                                    n9 = 4;
                                                }
                                                float[] fArray3 = new float[n9];
                                                fArray3[0] = 1.0f;
                                                fArray3[1] = 1.0f;
                                                fArray3[2] = 1.0f;
                                                fArray3[3] = 0.16f;
                                                n8 = Ui.col(fArray3);
                                            }
                                            n4 = n8;
                                            n3 = Ui.col(fArray2);
                                            n7 = n6;
                                            if (bl2) break block23;
                                            if (n7 == 0) break block24;
                                            float[] fArray4 = new float[4];
                                            fArray4[0] = 1.0f;
                                            fArray4[1] = 1.0f;
                                            fArray4[2] = 1.0f;
                                            fArray = fArray4;
                                            fArray4[3] = 0.7f;
                                            break block25;
                                        }
                                        n7 = 4;
                                    }
                                    float[] fArray5 = new float[n7];
                                    fArray5[0] = 1.0f;
                                    fArray5[1] = 1.0f;
                                    fArray5[2] = 1.0f;
                                    fArray = fArray5;
                                    fArray5[3] = 0.04f;
                                }
                                n2 = Ui.col(fArray);
                                imDrawList2.addRectFilled(f, f2, f + f3, f2 + f8, Ui.col(SettingsScreen.rgb(themes$Look.bg, 1.0f)), 9.0f);
                                Ui.glow(imDrawList2, f + f3 * 0.95f, f2 + f8, f3 * 0.8f, f8 * 0.9f, new float[]{0.0f, 0.0f, 0.0f, 0.0f, fArray2[0], fArray2[1], fArray2[2], 0.25f, fArray2[0], fArray2[1], fArray2[2], 0.08f}, f, f2, f3, f8, 9.0f);
                                imDrawList3 = imDrawList2;
                                f12 = f + 7.0f;
                                f11 = f2 + 7.0f;
                                f10 = f + 13.0f;
                                f9 = f2 + 13.0f;
                                n5 = n3;
                                if (bl2) break block26;
                                imDrawList3.addRectFilled(f12, f11, f10, f9, n5, 2.0f);
                                imDrawList2.addRectFilled(f + 16.0f, f2 + 8.0f, f + 34.0f, f2 + 11.0f, n4, 2.0f);
                                imDrawList3 = imDrawList2;
                                f12 = f;
                                f11 = f2 + 18.5f;
                                f10 = f + f3;
                                f9 = f2 + 18.5f;
                                if (n6 == 0) break block27;
                                n = Ui.col(new float[]{0.078f, 0.094f, 0.157f, 0.11f});
                                break block28;
                            }
                            n5 = 4;
                        }
                        float[] fArray = new float[n5];
                        fArray[0] = 1.0f;
                        fArray[1] = 1.0f;
                        fArray[2] = 1.0f;
                        fArray[3] = 0.08f;
                        n = Ui.col(fArray);
                    }
                    imDrawList3.addLine(f12, f11, f10, f9, n);
                    imDrawList2.addRectFilled(f + 6.0f, f2 + 24.0f, f + 6.0f + f3 * 0.3f, f2 + 32.0f, Ui.col(SettingsScreen.rgb(themes$Look.accent, 0.2f)), 3.0f);
                    imDrawList = imDrawList2;
                    f7 = f + 9.0f;
                    f6 = f2;
                    f5 = 27.0f;
                    if (bl2) break block29;
                    imDrawList.addRectFilled(f7, f6 + f5, f + 9.0f + f3 * 0.18f, f2 + 29.0f, n3, 1.0f);
                    imDrawList2.addRectFilled(f + 9.0f, f2 + 38.0f, f + 9.0f + f3 * 0.2f, f2 + 40.0f, n4, 1.0f);
                    imDrawList2.addRectFilled(f + 9.0f, f2 + 47.0f, f + 9.0f + f3 * 0.16f, f2 + 49.0f, n4, 1.0f);
                    imDrawList2.addRectFilled(f + f3 * 0.4f, f2 + 24.0f, f + f3 - 6.0f, f2 + f8 - 6.0f, n2, 5.0f);
                    imDrawList2.addRectFilled(f + f3 * 0.46f, f2 + 31.0f, f + f3 * 0.68f, f2 + 34.0f, n4, 2.0f);
                    imDrawList2.addRectFilled(f + f3 - 24.0f, f2 + 29.0f, f + f3 - 12.0f, f2 + 36.0f, n3, 3.5f);
                    imDrawList2.addRectFilled(f + f3 * 0.46f, f2 + 41.0f, f + f3 * 0.76f, f2 + 44.0f, n4, 2.0f);
                    if (!bl) break block30;
                    Ui.box(imDrawList2, f, f2, f + f3, f2 + f8, null, new float[]{Menu.SOFT[0], Menu.SOFT[1], Menu.SOFT[2], 0.85f}, 9.0f, 1.5f);
                    if (!bl2) break block31;
                }
                imDrawList = imDrawList2;
                f7 = f;
                f6 = f2;
                f5 = f + f3;
            }
            Ui.box(imDrawList, f7, f6, f5, f2 + f8, null, new float[]{1.0f, 1.0f, 1.0f, 0.08f + 0.12f * f4}, 9.0f, 1.0f);
        }
    }

    private static float glass(float f, float f2, float f3) {
        float f4;
        float f5;
        block11: {
            ImDrawList imDrawList;
            float f6;
            float f7;
            float f8;
            float f9;
            block12: {
                int n;
                boolean bl;
                block9: {
                    block10: {
                        f5 = 20.0f;
                        f9 = f + 52.0f + 16.0f;
                        f8 = f3 - 52.0f - 16.0f - 16.0f - 40.0f;
                        f7 = f2 + f5 / 2.0f;
                        String[] stringArray = b;
                        Ui.text(Im.sora13, f, Ui.mid(Im.sora13, f2, f5), Im.INK, stringArray[20]);
                        ImGui.setCursorScreenPos((float)(f9 - 7.0f), (float)f2);
                        ImGui.invisibleButton((String)stringArray[28], (float)(f8 + 14.0f), (float)f5);
                        f6 = Themes.glass();
                        bl = ImScreen.a;
                        n = ImGui.isItemActive();
                        if (bl) break block9;
                        if (n == 0) break block10;
                        float f10 = Math.max(0.0f, Math.min(1.0f, (ImGui.getMousePosX() - f9) / f8));
                        float f11 = Math.abs(f10 - f6) - 0.001f;
                        n = f11 == 0.0f ? 0 : (f11 > 0.0f ? 1 : -1);
                        if (bl) break block9;
                        if (n <= 0) break block10;
                        f6 = f10;
                        Themes.setGlass(f6);
                    }
                    n = ImGui.isItemDeactivated();
                }
                if (n != 0) {
                    ConfigManager.save();
                }
                imDrawList = ImGui.getWindowDrawList();
                imDrawList.addRectFilled(f9, f7 - 2.0f, f9 + f8, f7 + 2.0f, Ui.wash(0.1f), 2.0f);
                f4 = f6;
                if (bl) break block11;
                if (!(f4 > 0.0f)) break block12;
                Ui.gradient(imDrawList, f9, f7 - 2.0f, f9 + f8 * f6, f7 + 2.0f, Menu.ICON_TOP, Menu.ICON_FOOT, 2.0f);
            }
            imDrawList.addCircleFilled(f9 + f8 * f6, f7, 7.0f, Ui.col(Menu.KNOB_ON), 24);
            f4 = f6 * 100.0f;
        }
        String string = Math.round(f4) + "%";
        Ui.text(Im.sub, f + f3 - Ui.width(Im.sub, string), Ui.mid(Im.sub, f2, f5), Im.SUB, string);
        return f2 + f5;
    }

    
    private static float swatches(int[] nArray, boolean bl, float f, float f2, float f3) {
        int n;
        float f4;
        block21: {
            ImDrawList imDrawList = ImGui.getWindowDrawList();
            int n2 = Math.max(1, (int)((f3 + 10.0f) / 38.0f));
            boolean bl2 = ImScreen.a;
            for (int i = 0; i < nArray.length; ++i) {
                float f5;
                float f6;
                float f7;
                ImDrawList imDrawList2;
                float f8;
                block27: {
                    int n3;
                    float f9;
                    int n4;
                    block26: {
                        block22: {
                            block23: {
                                block25: {
                                    boolean bl3;
                                    block24: {
                                        n4 = nArray[i];
                                        f9 = f + (float)(i % n2) * 38.0f;
                                        f4 = f2;
                                        n = i / n2;
                                        if (bl2) break block21;
                                        f8 = f4 + (float)n * 38.0f;
                                        ImGui.setCursorScreenPos((float)f9, (float)f8);
                                        n3 = n4;
                                        if (bl2) break block22;
                                        if (!ImGui.invisibleButton((String)(b[14] + n3), (float)28.0f, (float)28.0f)) break block23;
                                        n3 = Themes.chosenAccent();
                                        if (bl2) break block22;
                                        if (n3 == n4) break block23;
                                        bl3 = bl;
                                        if (bl2) break block24;
                                        if (!bl3) break block25;
                                        bl3 = Plus.active();
                                    }
                                    if (bl3) break block25;
                                    MinecraftClient.method_1551().method_1507((Screen)new PlusScreen());
                                    if (!bl2) break block23;
                                }
                                Themes.set(Themes.chosen(), n4);
                                ConfigManager.save();
                            }
                            n3 = ImGui.isItemHovered() ? 1 : 0;
                        }
                        if (bl2) break block26;
                        if (n3 != 0) {
                            ImGui.setNextWindowPos((float)(f9 + 14.0f), (float)(f8 - 6.0f), (int)1, (float)0.5f, (float)1.0f);
                            ImGui.pushStyleVar((int)2, (float)8.0f, (float)4.0f);
                            ImGui.setTooltip((String)Themes.ACCENT_NAMES[n4]);
                            ImGui.popStyleVar();
                        }
                        imDrawList2 = imDrawList;
                        f7 = f9;
                        f6 = f8;
                        f5 = f9 + 28.0f;
                        if (bl2) break block27;
                        Ui.gradient(imDrawList2, f7, f6, f5, f8 + 28.0f, Themes.swatch(n4, false), Themes.swatch(n4, true), 8.0f);
                        n3 = Themes.accent();
                    }
                    if (n3 == n4) {
                        Ui.rect(imDrawList, f9 + 1.0f, f8 + 1.0f, f9 + 28.0f - 1.0f, f8 + 28.0f - 1.0f, Ui.col(Im.BG), 7.0f, 0, 2.0f);
                        Ui.rect(imDrawList, f9 - 2.0f, f8 - 2.0f, f9 + 28.0f + 2.0f, f8 + 28.0f + 2.0f, Ui.col(Im.INK), 10.0f, 0, 1.5f);
                        if (!bl2) continue;
                    }
                    imDrawList2 = imDrawList;
                    f7 = f9 + 0.5f;
                    f6 = f8 + 0.5f;
                    f5 = f9 + 28.0f - 0.5f;
                }
                Ui.rect(imDrawList2, f7, f6, f5, f8 + 28.0f - 0.5f, Ui.wash(0.1f), 8.0f, 0, 1.0f);
                if (!bl2) continue;
            }
            f4 = f2;
            n = (nArray.length + n2 - 1) / n2;
        }
        return f4 + (float)n * 38.0f - 10.0f;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[34];
                var3_1 = 0;
                var2_2 = "x`\u0007\u001b\bXl\t\nS\u007f|x\u000b[{\u0018\rI1z+b\u0018\u0007\u0005\u007fa\u0018\u0013_\u000fM{\u0014\u001bTu;yl\f\u000b_box\u000bGf\u001e\u001fV1y~`\u0011\u001a\u0007xl\u001e\nS~u\u0016Ef\t\u0016S\u007f|+y\b\u001cVxhcl\u0019^Cto%\u0016Ef]\u0010Ue~x)\u001b\u0011H1oc`\u000e^U\u007f~%\no)03w1brp\u0004\f[e\b\r\u001apxhl\u0013\nI\bFl\u0013\u000b\u001az~r\tFl\u0013\u000b\u001abrql\u0007Ll\u0013\u001bHpw\u0006jj\u001e\u001bTe\u0015J)\u001b\fStuo)\u001e\u0011Wth+f\u0013\u0012S\u007f~\rEf\t\u0017\\xxj}\u0014\u0011Tb\f[e\u001c\u0007\u001ap;xf\b\u0010^\u0005(*\u0016\u001bC\u0006Jj\u001e\u001bTe\u0005Le\u001c\rI\u0005Jk\u0012\u000bN\u0005_a\u0018\u0013_\u000b[e\b\r\u001aesnd\u0018\r\nJy\r\u001b[czej\u0018\n\\a\u001c\n\u001db;el\n\u0004{h\u001a\u001b\u0003xl\t\u0005le\u001c\rI\u0007]l\u000f\rS~u\u0018D}\u0015\u001bH1kgh\u0004\u001bHb<+j\u0012\rWtobj\u000e\u0017Xj\u000f\u001b_\u007f;xa\u001c\f_1}~e\u0011\rYc~ng";
                var4_3 = "x`\u0007\u001b\bXl\t\nS\u007f|x\u000b[{\u0018\rI1z+b\u0018\u0007\u0005\u007fa\u0018\u0013_\u000fM{\u0014\u001bTu;yl\f\u000b_box\u000bGf\u001e\u001fV1y~`\u0011\u001a\u0007xl\u001e\nS~u\u0016Ef\t\u0016S\u007f|+y\b\u001cVxhcl\u0019^Cto%\u0016Ef]\u0010Ue~x)\u001b\u0011H1oc`\u000e^U\u007f~%\no)03w1brp\u0004\f[e\b\r\u001apxhl\u0013\nI\bFl\u0013\u000b\u001az~r\tFl\u0013\u000b\u001abrql\u0007Ll\u0013\u001bHpw\u0006jj\u001e\u001bTe\u0015J)\u001b\fStuo)\u001e\u0011Wth+f\u0013\u0012S\u007f~\rEf\t\u0017\\xxj}\u0014\u0011Tb\f[e\u001c\u0007\u001ap;xf\b\u0010^\u0005(*\u0016\u001bC\u0006Jj\u001e\u001bTe\u0005Le\u001c\rI\u0005Jk\u0012\u000bN\u0005_a\u0018\u0013_\u000b[e\b\r\u001aesnd\u0018\r\nJy\r\u001b[czej\u0018\n\\a\u001c\n\u001db;el\n\u0004{h\u001a\u001b\u0003xl\t\u0005le\u001c\rI\u0007]l\u000f\rS~u\u0018D}\u0015\u001bH1kgh\u0004\u001bHb<+j\u0012\rWtobj\u000e\u0017Xj\u000f\u001b_\u007f;xa\u001c\f_1}~e\u0011\rYc~ng".length();
                var1_4 = 4;
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
                    var2_2 = "M{\u0014\u001bTu;ef\t\u0017\\xxj}\u0014\u0011Tb\bXl\t\nS\u007f|x";
                    var4_3 = "M{\u0014\u001bTu;ef\t\u0017\\xxj}\u0014\u0011Tb\bXl\t\nS\u007f|x".length();
                    var1_4 = 20;
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
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 11;
                        break;
                    }
                    case 1: {
                        v10 = 9;
                        break;
                    }
                    case 2: {
                        v10 = 125;
                        break;
                    }
                    case 3: {
                        v10 = 126;
                        break;
                    }
                    case 4: {
                        v10 = 58;
                        break;
                    }
                    case 5: {
                        v10 = 17;
                        break;
                    }
                    default: {
                        v10 = 27;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_7;
lbl65:

                v7 = v7;
            } while (v7 > var6_7);
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
        SettingsScreen.b = var5;
        v11 = new String[4];
        var0_6 = SettingsScreen.b;
        v11[0] = var0_6[13];
        v11[1] = var0_6[24];
        v11[2] = var0_6[16];
        v11[3] = var0_6[21];
        SettingsScreen.SECTIONS = v11;
        SettingsScreen.WHEN = DateTimeFormatter.ofPattern(var0_6[9]).withZone(ZoneId.systemDefault());
    }
}
