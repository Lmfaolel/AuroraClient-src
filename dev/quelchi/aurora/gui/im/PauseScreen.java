package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.TitleHome;
import dev.quelchi.aurora.gui.im.Edit;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.InviteScreen;
import dev.quelchi.aurora.gui.im.Mc;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.ModsScreen;
import dev.quelchi.aurora.gui.im.SocialScreen;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.social.Social;
import dev.quelchi.aurora.social.Social$Friend;
import dev.quelchi.aurora.social.WorldShare;
import imgui.ImDrawList;
import imgui.ImGui;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.StatsScreen;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.screen.multiplayer.SocialInteractionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;

public final class PauseScreen
extends ImScreen {
    private static final float H = 34.0f;
    private static final Set<String> OWN;
    private final GameMenuScreen menu = new GameMenuScreen(true);
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;

    @Override
    protected boolean vanillaSize() {
        return true;
    }

    public PauseScreen() {
        super(b[34]);
    }

    @Override
    public boolean method_25421() {
        return true;
    }

    public void method_25394(DrawContext drawContext, int n, int n2, float f) {
        drawContext.method_25294(0, 0, this.field_22789, this.field_22790, PauseScreen.a(835, 1634442524361473175L));
        super.method_25394(drawContext, n, n2, f);
        this.icons(drawContext, n, n2, f);
    }

    protected void method_25426() {
        block5: {
            PauseScreen pauseScreen;
            block4: {
                boolean bl = ImScreen.a;
                pauseScreen = this;
                if (bl) break block4;
                if (pauseScreen.field_22787.field_1724 == null) break block5;
                pauseScreen = this;
            }
            pauseScreen.host((Screen)this.menu, OWN);
        }
    }

    private static boolean button(String string, float f, float f2, float f3) {
        return Menu.button(string + b[10], f, f2, f3, 34.0f, false, true);
    }

    private static float px(int n) {
        return (float)n * 1.7f;
    }

    private static boolean invite(float f, float f2, float f3) {
        boolean bl;
        block12: {
            boolean bl2;
            block13: {
                Object object;
                boolean bl3;
                boolean bl4;
                float f4;
                String string;
                ImDrawList imDrawList;
                block14: {
                    block11: {
                        int n;
                        String[] stringArray;
                        block10: {
                            ImGui.setCursorScreenPos((float)f, (float)f2);
                            stringArray = b;
                            bl2 = ImGui.invisibleButton((String)stringArray[24], (float)f3, (float)34.0f);
                            float f5 = Ui.hot();
                            imDrawList = ImGui.getWindowDrawList();
                            Ui.accent(imDrawList, f, f2, f + f3, f2 + 34.0f, Ui.col(Ui.mix(Menu.FILL, Menu.ACCENT, f5)), 8.0f);
                            string = stringArray[2];
                            f4 = Ui.width(Im.mc, string);
                            bl4 = WorldShare.hosting();
                            bl3 = ImScreen.a;
                            n = bl4;
                            if (bl3) break block10;
                            if (n == false) break block11;
                            n = WorldShare.inWorld().size();
                        }
                        stringArray = b;
                        object = stringArray[36] + n + stringArray[11];
                        break block14;
                    }
                    object = "";
                }
                String string2 = object;
                float f5 = bl4 ? Ui.width(Im.mc, string2) + 28.0f : 0.0f;
                float f7 = f5;
                float f8 = f4;
                f8 = bl4 ? 12.0f + f7 : 0.0f;
                float f10 = f6 + f8;
                float f11 = f + Mc.labelX(f3, f10);
                Ui.mcText(f11, f2 + Mc.labelY(34.0f), Ui.col(Im.ON_FILL), string, true);
                bl = bl4;
                if (bl3) break block12;
                if (!bl) break block13;
                float f12 = f11 + f4 + 12.0f;
                float f13 = f2 + 6.0f;
                Ui.fill(imDrawList, f12, f13, f12 + f7, f13 + 22.0f, Ui.col(Im.GREEN, 0.22f), 11.0f);
                imDrawList.addCircleFilled(f12 + 11.0f, f13 + 11.0f, 3.5f, Ui.col(Im.GREEN), 12);
                Ui.mcText(f12 + 19.0f, Ui.mid(Im.mc, f13, 22.0f), Ui.col(Im.ON_FILL), string2, false);
            }
            bl = bl2;
        }
        return bl;
    }

    private static boolean leave(String string, float f, float f2, float f3) {
        ImGui.setCursorScreenPos((float)f, (float)f2);
        boolean bl = ImGui.invisibleButton((String)(b[23] + string), (float)f3, (float)34.0f);
        float f4 = Ui.hot();
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        Ui.fill(imDrawList, f, f2, f + f3, f2 + 34.0f, Ui.col(Ui.mix(Im.QUIET, Im.DANGER, f4)), 8.0f);
        Ui.rect(imDrawList, f, f2, f + f3, f2 + 34.0f, Ui.col(Im.DANGER, 0.5f), 8.0f);
        Ui.mcText(f + Mc.labelX(f3, Ui.width(Im.mc, string)), f2 + Mc.labelY(34.0f), Ui.col(Ui.mix(Im.SUB, Im.INK, f4)), string, true);
        return bl;
    }

    @Override
    protected void draw() {
        float f;
        float f2;
        float f3;
        String string;
        float f4;
        float f5;
        float f6;
        MinecraftClient minecraftClient;
        block113: {
            float f7;
            float f8;
            block117: {
                String[] stringArray;
                block116: {
                    boolean bl;
                    block111: {
                        int n;
                        block110: {
                            block108: {
                                int n2;
                                block109: {
                                    block106: {
                                        float f9;
                                        float f10;
                                        block107: {
                                            block120: {
                                                float f11;
                                                float f12;
                                                float f13;
                                                Object object;
                                                block104: {
                                                    block105: {
                                                        Object object2;
                                                        block119: {
                                                            block100: {
                                                                int n3;
                                                                block103: {
                                                                    int n4;
                                                                    block118: {
                                                                        float f14;
                                                                        float f15;
                                                                        float f16;
                                                                        String string2;
                                                                        block97: {
                                                                            block98: {
                                                                                MinecraftClient minecraftClient2;
                                                                                block99: {
                                                                                    block94: {
                                                                                        block95: {
                                                                                            MinecraftClient minecraftClient3;
                                                                                            block96: {
                                                                                                int n5;
                                                                                                block92: {
                                                                                                    block93: {
                                                                                                        int n6;
                                                                                                        block90: {
                                                                                                            block91: {
                                                                                                                minecraftClient = MinecraftClient.method_1551();
                                                                                                                f6 = ImGui.getIO().getDisplaySizeX();
                                                                                                                bl = ImScreen.a;
                                                                                                                f5 = ImGui.getIO().getDisplaySizeY();
                                                                                                                ImGui.setNextWindowPos((float)0.0f, (float)0.0f);
                                                                                                                ImGui.setNextWindowSize((float)f6, (float)f5);
                                                                                                                ImGui.pushStyleVar((int)2, (float)0.0f, (float)0.0f);
                                                                                                                stringArray = b;
                                                                                                                ImGui.begin((String)stringArray[0], (int)8367);
                                                                                                                ImGui.popStyleVar();
                                                                                                                n2 = minecraftClient.method_1496();
                                                                                                                n6 = n2;
                                                                                                                if (bl) break block90;
                                                                                                                if (n6 == 0) break block91;
                                                                                                                n6 = 6;
                                                                                                                break block90;
                                                                                                            }
                                                                                                            n6 = 5;
                                                                                                        }
                                                                                                        int n7 = n6 + this.added.size();
                                                                                                        int n8 = (this.field_22789 - 212) / 2 + 4;
                                                                                                        int n9 = (int)((float)(this.field_22790 - 70 - 24 * (n7 - 1)) * 0.25f) + 50;
                                                                                                        f8 = PauseScreen.px(n8);
                                                                                                        f10 = PauseScreen.px(n8 + 106);
                                                                                                        f9 = PauseScreen.px(98);
                                                                                                        f7 = PauseScreen.px(204);
                                                                                                        stringArray = b;
                                                                                                        String string3 = Text.method_43471((String)stringArray[14]).getString();
                                                                                                        n5 = this.field_22789;
                                                                                                        if (bl) break block92;
                                                                                                        Ui.mcText(PauseScreen.px(n5 / 2) + Mc.labelX(0.0f, Ui.width(Im.mc, string3)), PauseScreen.px(40), Ui.col(Im.INK), string3, true);
                                                                                                        if (!PauseScreen.button(stringArray[33], f8, PauseScreen.px(n9), f7)) break block93;
                                                                                                        minecraftClient.method_1507(null);
                                                                                                    }
                                                                                                    n5 = n9 += 24;
                                                                                                }
                                                                                                f4 = PauseScreen.px(n5);
                                                                                                string2 = b[28];
                                                                                                f16 = f8;
                                                                                                f15 = f4;
                                                                                                f14 = f9;
                                                                                                if (bl) break block94;
                                                                                                if (!PauseScreen.button(string2, f16, f15, f14)) break block95;
                                                                                                minecraftClient3 = minecraftClient;
                                                                                                if (bl) break block96;
                                                                                                if (minecraftClient3.method_1562() == null) break block95;
                                                                                                minecraftClient3 = minecraftClient;
                                                                                            }
                                                                                            minecraftClient3.method_1507((Screen)new AdvancementsScreen(minecraftClient.method_1562().method_2869(), (Screen)this));
                                                                                        }
                                                                                        stringArray = b;
                                                                                        string2 = stringArray[32];
                                                                                        f16 = f10;
                                                                                        f15 = f4;
                                                                                        f14 = f9;
                                                                                    }
                                                                                    if (bl) break block97;
                                                                                    if (!PauseScreen.button(string2, f16, f15, f14)) break block98;
                                                                                    minecraftClient2 = minecraftClient;
                                                                                    if (bl) break block99;
                                                                                    if (minecraftClient2.field_1724 == null) break block98;
                                                                                    minecraftClient2 = minecraftClient;
                                                                                }
                                                                                minecraftClient2.method_1507((Screen)new StatsScreen((Screen)this, minecraftClient.field_1724.method_3143()));
                                                                            }
                                                                            n4 = n9 += 24;
                                                                            if (bl) break block118;
                                                                            f4 = PauseScreen.px(n4);
                                                                            stringArray = b;
                                                                            string2 = stringArray[22];
                                                                            f16 = f8;
                                                                            f15 = f4;
                                                                            f14 = f9;
                                                                        }
                                                                        if (PauseScreen.button(string2, f16, f15, f14)) {
                                                                            minecraftClient.method_1507((Screen)new ModsScreen());
                                                                        }
                                                                        n4 = 0;
                                                                    }
                                                                    int n10 = n4;
                                                                    for (Social$Friend object4 : Social.friends()) {
                                                                        block102: {
                                                                            Social$Friend social$Friend;
                                                                            block101: {
                                                                                if (bl) break block100;
                                                                                social$Friend = object4;
                                                                                if (bl) break block101;
                                                                                if (!social$Friend.accepted()) break block102;
                                                                                social$Friend = object4;
                                                                            }
                                                                            if (social$Friend.online()) {
                                                                                ++n10;
                                                                            }
                                                                        }
                                                                        if (!bl) continue;
                                                                    }
                                                                    n3 = n10;
                                                                    if (bl) break block103;
                                                                    if (n3 <= 0) break block100;
                                                                    n3 = n10;
                                                                }
                                                                stringArray = b;
                                                                object2 = stringArray[8] + n3;
                                                                break block119;
                                                            }
                                                            stringArray = b;
                                                            object2 = stringArray[16];
                                                        }
                                                        object = (String)object2 + b[13];
                                                        f13 = f10;
                                                        f12 = f4;
                                                        f11 = f9;
                                                        if (bl) break block104;
                                                        if (!Menu.button((String)object, f13, f12, f11, 34.0f, false, true)) break block105;
                                                        minecraftClient.method_1507((Screen)new SocialScreen());
                                                    }
                                                    Menu.badge(f8 + f7 - 16.0f, f4 + 12.0f, WorldShare.invites().size(), Im.GREEN, true);
                                                    n = n9 += 24;
                                                    if (bl) break block120;
                                                    f4 = PauseScreen.px(n);
                                                    stringArray = b;
                                                    object = stringArray[5];
                                                    f13 = f8;
                                                    f12 = f4;
                                                    f11 = f9;
                                                }
                                                if (PauseScreen.button((String)object, f13, f12, f11)) {
                                                    minecraftClient.method_1507((Screen)new OptionsScreen((Screen)this, minecraftClient.field_1690));
                                                }
                                                n = n2;
                                            }
                                            if (bl) break block106;
                                            if (n == 0) break block107;
                                            n = minecraftClient.method_1576().method_3860() ? 1 : 0;
                                            if (bl) break block106;
                                            if (n != 0) break block107;
                                            n = PauseScreen.button(b[25], f10, f4, f9) ? 1 : 0;
                                            if (bl) break block108;
                                            if (n == 0) break block109;
                                            minecraftClient.method_1507((Screen)new OpenToLanScreen((Screen)this));
                                            if (!bl) break block109;
                                        }
                                        n = PauseScreen.button(b[4], f10, f4, f9) ? 1 : 0;
                                    }
                                    if (bl) break block108;
                                    if (n == 0) break block109;
                                    minecraftClient.method_1507((Screen)new SocialInteractionsScreen((Screen)this));
                                }
                                n = n2;
                            }
                            if (bl) break block110;
                            if (n == 0) break block111;
                            n = PauseScreen.invite(f8, PauseScreen.px(n9 += 24), f7) ? 1 : 0;
                        }
                        if (n != 0) {
                            minecraftClient.method_1507((Screen)new InviteScreen());
                        }
                    }
                    PressableWidget pressableWidget = null;
                    for (PressableWidget pressableWidget2 : this.added) {
                        block114: {
                            PressableWidget pressableWidget3;
                            block115: {
                                block112: {
                                    pressableWidget3 = pressableWidget2;
                                    if (bl) break block112;
                                    string = pressableWidget3.method_25369().getString();
                                    f3 = f8;
                                    f2 = PauseScreen.px(n9 += 24);
                                    f = f7;
                                    if (bl) break block113;
                                    if (!PauseScreen.button(string, f3, f2, f)) break block114;
                                    pressableWidget3 = pressableWidget2;
                                }
                                if (bl) break block115;
                                if (!pressableWidget3.field_22763) break block114;
                                pressableWidget3 = pressableWidget2;
                            }
                            pressableWidget = pressableWidget3;
                        }
                        if (!bl) continue;
                    }
                    if (pressableWidget != null) {
                        this.press(pressableWidget);
                    }
                    f4 = PauseScreen.px(n9 += 24);
                    if (!minecraftClient.method_1542()) break block116;
                    string = b[35];
                    break block117;
                }
                stringArray = b;
                string = stringArray[9];
            }
            f3 = f8;
            f2 = f4;
            f = f7;
        }
        if (PauseScreen.leave(string, f3, f2, f)) {
            minecraftClient.method_73360((Text)Text.method_43471((String)b[15]));
        }
        this.underOurs(f4 + 34.0f);
        Ui.mcText(14.0f, f5 - 28.0f, Ui.col(Im.SUB), PauseScreen.where(minecraftClient), true);
        String string3 = TitleHome.versionLine();
        Ui.mcText(f6 - 14.0f - Ui.width(Im.mc, string3), f5 - 28.0f, Ui.col(Im.SUB), string3, true);
        ImGui.end();
    }

    private void underOurs(float f) {
        int n;
        boolean bl;
        block11: {
            int n2;
            block10: {
                n2 = PauseScreen.a(2684, 8257105942970807722L);
                bl = ImScreen.a;
                for (Object object : this.hidden) {
                    if (!bl) {
                        n2 = Math.max(n2, object.method_55443());
                        if (!bl) continue;
                    }
                    break block10;
                }
                n = n2;
                if (bl) break block11;
                if (n != PauseScreen.a(22866, 4380603462429206151L)) break block10;
                return;
            }
            n = Math.round(Edit.gui(f)) - n2;
        }
        int n3 = n;
        if (n3 == 0) {
            return;
        }
        for (PressableWidget pressableWidget : this.hidden) {
            pressableWidget.method_46419(pressableWidget.method_46427() + n3);
            if (!bl) continue;
        }
    }

    
    private static String where(MinecraftClient var0) {
        block28: {
            block26: {
                block27: {
                    block25: {
                        block23: {
                            block24: {
                                block22: {
                                    block20: {
                                        block21: {
                                            var1_1 = ImScreen.a;
                                            v0 = var0;
                                            if (var1_1) break block20;
                                            if (!v0.method_1542()) break block21;
                                            return PauseScreen.b[17];
                                        }
                                        v0 = var0;
                                    }
                                    var2_2 = v0.method_1558();
                                    if (var2_2 == null) {
                                        return "";
                                    }
                                    v4 = var0;
                                    if (var1_1) break block22;
                                    if (v4.method_1562() != null) {
                                    }
                                    v4 = var0;
                                }
                                if (var1_1) break block23;
                                if (v4.field_1724 != null) break block24;
lbl44:

                                v8 = null;
                                break block25;
                            }
                            v4 = var0;
                        }
                        v8 = v4.method_1562().method_2871(var0.field_1724.method_5667());
                    }
                    var3_3 = v8;
                    v10 = var2_2.field_3761;
                    v11 = var3_3;
                    if (var1_1) break block26;
                    if (v11 != null) break block27;
                    v13 = "";
                    break block28;
                }
                v11 = var3_3;
            }
            var4_4 = PauseScreen.b;
            v13 = var4_4[29] + v11.method_2959() + var4_4[6];
        }
        return v10 + (String)v13;
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[37];
                        var11_1 = 0;
                        var10_2 = "\u00061*#\u0011\u0010\u0011%6~\u00152i\u0017><5\u00193q\u0002#\u000f?>)9\u00003?\u0002?\u007f\u0007\u001b$s\u0012\t\u001b51%Z'j\u001f$\u0010&<>)\u0011$?$5/?\u0006\"v\u00187\u00079 +9\u001b8l\u0003V=,\u000f\u001b51%Z%w\u0017\":\u0004\u001b\u001a~\u0018\n0\"65\u001a2lV\u00e7\u007f\n29,3\u001b8q\u00133+\u0007Us/1\u0001%z\u0005V8:\"\u0011\f\u001b51%Z9o\u000290>\u0007\u000fUs| \u0015#l\u00136-9\u00118{\u0005\t\u001b51%Z1~\u001b5\u0010\u001b51%Z%~\u000091783i\u0013<\u00070\"65\u001a2l\f%917\u00183o\u001a1&5\u0006\u000f\u001b51%Z2v\u000530>\u001a3|\u0002\u0014\u001b51%Z&s\u0017):\"&3o\u0019\"+9\u001a1\t\u0011%6~\u0007\"~\u0002#\u000f\u001b51%Z$z\u0006?-$6#x\u0005\u000b7%-?\u00067?\u001b51%\u0005\u00061*#\u0011\u000b\u00061*#\u0011?q\u00009+5\u000b9 :>T\"pV\u001c\u001e\u001e\r\u001b51%Z0z\u00134=1\u0017=\u0011\u001b51%Z%z\u00184\u00195\u00112}\u001734\f74)1\u001a5z\u001b51$\u0007\u0003V\u00e7\u007f\u0011\u001b51%Z$z\u0002%-> 9R\u0013>*\u0011\u001b51%Z$z\u0002%-> 9X\u0017=:\n%$>$\u001d%k\u001f3,\f41<;T\"pV\u0017>=\u0011\u0006&1*#\u00112";
                        var12_3 = "\u00061*#\u0011\u0010\u0011%6~\u00152i\u0017><5\u00193q\u0002#\u000f?>)9\u00003?\u0002?\u007f\u0007\u001b$s\u0012\t\u001b51%Z'j\u001f$\u0010&<>)\u0011$?$5/?\u0006\"v\u00187\u00079 +9\u001b8l\u0003V=,\u000f\u001b51%Z%w\u0017\":\u0004\u001b\u001a~\u0018\n0\"65\u001a2lV\u00e7\u007f\n29,3\u001b8q\u00133+\u0007Us/1\u0001%z\u0005V8:\"\u0011\f\u001b51%Z9o\u000290>\u0007\u000fUs| \u0015#l\u00136-9\u00118{\u0005\t\u001b51%Z1~\u001b5\u0010\u001b51%Z%~\u000091783i\u0013<\u00070\"65\u001a2l\f%917\u00183o\u001a1&5\u0006\u000f\u001b51%Z2v\u000530>\u001a3|\u0002\u0014\u001b51%Z&s\u0017):\"&3o\u0019\"+9\u001a1\t\u0011%6~\u0007\"~\u0002#\u000f\u001b51%Z$z\u0006?-$6#x\u0005\u000b7%-?\u00067?\u001b51%\u0005\u00061*#\u0011\u000b\u00061*#\u0011?q\u00009+5\u000b9 :>T\"pV\u001c\u001e\u001e\r\u001b51%Z0z\u00134=1\u0017=\u0011\u001b51%Z%z\u00184\u00195\u00112}\u001734\f74)1\u001a5z\u001b51$\u0007\u0003V\u00e7\u007f\u0011\u001b51%Z$z\u0002%-> 9R\u0013>*\u0011\u001b51%Z$z\u0002%-> 9X\u0017=:\n%$>$\u001d%k\u001f3,\f41<;T\"pV\u0017>=\u0011\u0006&1*#\u00112".length();
                        var9_4 = 5;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "%1)5T7q\u0012p\u000e%\u001d\"?\u0002?\u007f\u0004\u001d\"s\u0013\n%8>\"\u001d8xV\u00e7\u007f";
                            var12_3 = "%1)5T7q\u0012p\u000e%\u001d\"?\u0002?\u007f\u0004\u001d\"s\u0013\n%8>\"\u001d8xV\u00e7\u007f".length();
                            var9_4 = 22;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block20;
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
                            break block21;
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
                                v10 = 118;
                                break;
                            }
                            case 1: {
                                v10 = 80;
                                break;
                            }
                            case 2: {
                                v10 = 95;
                                break;
                            }
                            case 3: {
                                v10 = 80;
                                break;
                            }
                            case 4: {
                                v10 = 116;
                                break;
                            }
                            case 5: {
                                v10 = 86;
                                break;
                            }
                            default: {
                                v10 = 31;
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
                PauseScreen.b = var13;
                var0_8 = 4551577486198550270L;
                var6_9 = new long[3];
                var3_10 = 0;
                var4_11 = "\u0083s\u00ab\u00deE\u00d9\u0014cF\u00f2\u00bck\u00ffw\u00c8yan\u0007i+\r\u001bT";
                var5_12 = "\u0083s\u00ab\u00deE\u00d9\u0014cF\u00f2\u00bck\u00ffw\u00c8yan\u0007i+\r\u001bT".length();
                var2_13 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl84:

                while (true) {
                    var6_9[v11] = (((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L) ^ var0_8;
                    if (var2_13 < var5_12) continue;
                    break block23;
                    break;
                }
            }
            var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
            v11 = var3_10++;
            while (true)
        }
        PauseScreen.c = var6_9;
        PauseScreen.d = new Integer[3];
        v12 = new String[12];
        var8_6 = PauseScreen.b;
        v12[0] = var8_6[31];
        v12[1] = var8_6[1];
        v12[2] = var8_6[20];
        v12[3] = var8_6[12];
        v12[4] = var8_6[7];
        v12[5] = var8_6[18];
        v12[6] = var8_6[30];
        v12[7] = var8_6[3];
        v12[8] = var8_6[19];
        v12[9] = var8_6[27];
        v12[10] = var8_6[21];
        v12[11] = var8_6[26];
        PauseScreen.OWN = Set.of(v12);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3FD4;
        if (d[n2] == null) {
            PauseScreen.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }
}
