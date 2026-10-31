package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Motion;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.social.Social;
import dev.quelchi.aurora.social.Social$Friend;
import dev.quelchi.aurora.social.Social$Presence;
import dev.quelchi.aurora.social.Social$Roster;
import dev.quelchi.aurora.social.WorldShare;
import dev.quelchi.aurora.social.WorldShare$Invite;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.type.ImString;
import java.lang.invoke.LambdaMetafactory;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;

public final class SocialScreen
extends ImScreen {
    private static final float LIST = 300.0f;
    private static final float ROW = 48.0f;
    private static final float ME = 62.0f;
    private static final float HEAD = 65.0f;
    private static final float SEND = 36.0f;
    private static final float PAD = 28.0f;
    private static final String[] STATUS;
    private static final String[] STATUS_NAME;
    private static final String[] TAB;
    private static final DateTimeFormatter CLOCK;
    private static final DateTimeFormatter DAY;
    private static UUID peer;
    private final ImString search;
    private final ImString adding;
    private final ImString draft;
    private int tab;
    private boolean addOpen;
    private boolean focusAdd;
    private boolean focusDraft;
    private boolean removing;
    private float talkH;
    private int talkN;
    private int fresh;
    private final Map<UUID, Integer> unread;
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;
    private static final long g;

    public static UUID viewing() {
        UUID uUID = MinecraftClient.method_1551().field_1755 instanceof SocialScreen ? peer : null;
        return uUID;
    }

    public SocialScreen() {
        block3: {
            UUID uUID;
            block2: {
                super(b[51]);
                this.search = new ImString(32);
                this.adding = new ImString(32);
                this.draft = new ImString(256);
                this.talkN = -1;
                this.fresh = SocialScreen.a(24644, 8668785560952637789L);
                boolean bl = ImScreen.a;
                this.unread = new HashMap<UUID, Integer>();
                uUID = peer;
                if (bl) break block2;
                if (uUID == null) break block3;
                uUID = peer;
            }
            Social.open(uUID);
        }
    }

    
    private static float[] tone(String string) {
        float[] fArray;
        int n;
        block20: {
            String string2 = string;
            boolean bl = ImScreen.a;
            int n2 = -1;
            n = string2.hashCode();
            if (bl) break block20;
            switch (n) {
                case -1012222381: {
                    n = string2.equals(b[4]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 0;
                        if (!bl) break;
                    }
                    break block20;
                }
                case 3227604: {
                    n = string2.equals(b[28]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 1;
                        if (!bl) break;
                    }
                    break block20;
                }
                case 99610: {
                    n = string2.equals(b[14]) ? 1 : 0;
                    if (bl) break block20;
                    if (n == 0) break;
                    n2 = 2;
                }
            }
            n = n2;
        }
        switch (n) {
            case 0: {
                fArray = Im.GREEN;
                return fArray;
            }
            case 1: {
                fArray = Im.WARN;
                return fArray;
            }
            case 2: {
                fArray = Im.DANGER;
                return fArray;
            }
        }
        fArray = Im.MUTE;
        return fArray;
    }

    private static String named(String string) {
        String string2;
        block8: {
            int n = 0;
            boolean bl = ImScreen.a;
            while (n < STATUS.length) {
                block9: {
                    int n2;
                    String[] stringArray;
                    block7: {
                        stringArray = STATUS;
                        n2 = n++;
                        if (bl) break block7;
                        string2 = stringArray[n2];
                        if (bl) break block8;
                        if (!string2.equals(string)) break block9;
                        stringArray = STATUS_NAME;
                        n2 = n;
                    }
                    return stringArray[n2];
                }
                if (!bl) continue;
            }
            string2 = b[3];
        }
        return string2;
    }

    private static boolean joinable(Social$Friend social$Friend) {
        boolean bl;
        block13: {
            block11: {
                String string;
                boolean bl2;
                block12: {
                    Social$Friend social$Friend2;
                    block10: {
                        bl2 = ImScreen.a;
                        social$Friend2 = social$Friend;
                        if (bl2) break block10;
                        if (!social$Friend2.online()) break block11;
                        social$Friend2 = social$Friend;
                    }
                    string = social$Friend2.server();
                    if (bl2) break block12;
                    if (string.isEmpty()) break block11;
                    string = social$Friend.server();
                }
                bl = string.equalsIgnoreCase(SocialScreen.currentServer());
                if (bl2) break block13;
                if (bl) break block11;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    private void open(UUID uUID) {
        peer = uUID;
        this.talkN = -1;
        this.fresh = SocialScreen.a(2855, 1514806090049020476L);
        this.talkH = 0.0f;
        this.draft.set("");
        this.focusDraft = true;
        Social.open(uUID);
    }

    @Override
    public boolean shutsOnMenuKey() {
        return true;
    }

    @Override
    protected void draw() {
        block14: {
            Social.tick(true);
            List<Social$Friend> list = Social.friends();
            Social$Roster social$Roster = Social.roster(list);
            boolean bl = ImScreen.a;
            int n = Menu.top(b[33], TAB, new int[]{0, 0, social$Roster.incoming().size()}, this.tab, 0.0f);
            if (n >= 0) {
                this.tab = n;
            }
            float f = Menu.x;
            float f2 = Menu.y + 64.0f;
            float f3 = Menu.w;
            float f4 = Menu.h - 64.0f;
            Social$Friend social$Friend = null;
            for (Social$Friend social$Friend2 : list) {
                block16: {
                    Social$Friend social$Friend3;
                    block17: {
                        block15: {
                            if (bl) break block14;
                            social$Friend3 = social$Friend2;
                            if (bl) break block15;
                            if (!social$Friend3.accepted()) break block16;
                            social$Friend3 = social$Friend2;
                        }
                        if (bl) break block17;
                        if (!social$Friend3.uuid().equals(peer)) break block16;
                        social$Friend3 = social$Friend2;
                    }
                    social$Friend = social$Friend3;
                }
                if (!bl) continue;
            }
            ImGui.getWindowDrawList().addLine(f + 300.0f, f2, f + 300.0f, f2 + f4, Ui.col(Im.LINE));
            this.bar(f, f2 + 16.0f);
            this.list(f, f2 + 62.0f, f4 - 62.0f - 62.0f - 16.0f, social$Roster);
            this.me(f, f2 + f4 - 16.0f - 62.0f);
            this.chat(f + 300.0f, f2, f3 - 300.0f, f4, social$Friend);
            Menu.end();
        }
    }

    
    private void bar(float f, float f2) {
        block51: {
            boolean bl;
            block49: {
                block50: {
                    String string;
                    boolean bl2;
                    block47: {
                        boolean bl3;
                        block48: {
                            SocialScreen socialScreen;
                            block46: {
                                boolean bl4;
                                boolean bl5;
                                block45: {
                                    String string2;
                                    String[] stringArray;
                                    float f3;
                                    block43: {
                                        block44: {
                                            block41: {
                                                float f4;
                                                float f5;
                                                block42: {
                                                    f5 = f + 16.0f;
                                                    f4 = 226.0f;
                                                    f3 = f5 + f4 + 8.0f;
                                                    bl2 = ImScreen.a;
                                                    bl3 = false;
                                                    if (bl2) break block41;
                                                    if (this.addOpen) break block42;
                                                    if (peer == null) {
                                                        this.typeToSearch();
                                                    }
                                                    Menu.search(b[54], b[71], this.search, f5, f2, f4);
                                                    if (!bl2) break block43;
                                                }
                                                ImGui.setCursorScreenPos((float)f5, (float)f2);
                                                ImGui.setNextItemWidth((float)f4);
                                                ImGui.pushStyleVar((int)11, (float)14.0f, (float)10.5f);
                                                ImGui.pushStyleVar((int)13, (float)1.0f);
                                                if (bl2) break block44;
                                                ImGui.pushFont((ImFont)Im.sub);
                                            }
                                            if (this.focusAdd) {
                                                ImGui.setKeyboardFocusHere();
                                            }
                                            this.focusAdd = false;
                                            stringArray = b;
                                            ImGui.inputTextWithHint((String)stringArray[46], (String)stringArray[85], (ImString)this.adding);
                                            bl3 = Ui.entered();
                                            ImGui.popFont();
                                        }
                                        ImGui.popStyleVar((int)2);
                                    }
                                    ImGui.setCursorScreenPos((float)f3, (float)f2);
                                    stringArray = b;
                                    bl5 = ImGui.invisibleButton((String)stringArray[52], (float)34.0f, (float)34.0f);
                                    ImDrawList imDrawList = ImGui.getWindowDrawList();
                                    Ui.accent(imDrawList, f3, f2, f3 + 34.0f, f2 + 34.0f, Ui.col(Ui.mix(Menu.FILL, Menu.ICON_FOOT, Ui.hot())), 9.0f);
                                    if (this.addOpen) {
                                        string2 = stringArray[17];
                                    } else {
                                        stringArray = b;
                                        string2 = stringArray[76];
                                    }
                                    bl4 = Ui.icon(string2, f3 + 9.5f, f2 + 9.5f, 15.0f, Im.ON_FILL);
                                    if (bl2) break block45;
                                    if (ImGui.isItemHovered()) {
                                        ImGui.setTooltip((String)(this.addOpen ? b[77] : b[20]));
                                    }
                                    socialScreen = this;
                                    if (bl2) break block46;
                                    bl4 = socialScreen.addOpen;
                                }
                                if (!bl4) {
                                    if (!bl5) return;
                                    this.focusAdd = true;
                                    this.addOpen = true;
                                    return;
                                }
                                bl3 |= bl5;
                                socialScreen = this;
                            }
                            string = socialScreen.adding.get().trim();
                            bl = bl3;
                            if (bl2) break block47;
                            if (!bl) break block48;
                            bl = string.isEmpty();
                            if (bl2) break block47;
                            if (!bl) {
                                Social.request(string, null);
                                this.tab = SocialScreen.a(14717, 6635084622684901477L);
                            }
                        }
                        bl = bl3;
                    }
                    if (bl2) break block49;
                    if (!bl) break block50;
                    bl = string.isEmpty();
                    if (bl2) break block49;
                    if (!bl) break block51;
                }
                bl = ImGui.isKeyPressed((int)526);
            }
            if (!bl) return;
        }
        this.adding.set("");
        this.addOpen = false;
    }

    
    private void list(float var1_1, float var2_2, float var3_3, Social$Roster var4_4) {
        block33: {
            block34: {
                block37: {
                    block40: {
                        block41: {
                            block38: {
                                block39: {
                                    block35: {
                                        block36: {
                                            block32: {
                                                block31: {
                                                    block30: {
                                                        block29: {
                                                            block28: {
                                                                block27: {
                                                                    ImGui.setCursorScreenPos((float)(var1_1 + 1.0f), (float)var2_2);
                                                                    ImGui.pushStyleColor((int)3, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                                                                    ImGui.pushStyleVar((int)8, (float)0.0f);
                                                                    var11_5 = SocialScreen.b;
                                                                    ImGui.beginChild((String)var11_5[56], (float)298.0f, (float)var3_3, (boolean)false);
                                                                    var6_6 = this.search.get().trim().toLowerCase(Locale.ROOT);
                                                                    var5_7 = ImScreen.a;
                                                                    var8_9 = var7_8 = ImGui.getCursorScreenPosY();
                                                                    var9_10 = Social.error();
                                                                    v0 = var9_10;
                                                                    if (var5_7) break block27;
                                                                    if (v0 == null) break block28;
                                                                    v0 = var9_10;
                                                                }
                                                                v2 = v0.isEmpty();
                                                                if (var5_7) break block29;
                                                                if (v2 != 0) break block28;
                                                                Ui.text(Im.label, var1_1 + 16.0f, var8_9 + 3.0f, Im.DANGER, var9_10);
                                                                var7_8 = var8_9 += 24.0f;
                                                            }
                                                            v4 = this;
                                                            if (var5_7) break block30;
                                                            v2 = v4.tab;
                                                        }
                                                        if (v2 != 2) // goto
                                                        var11_5 = SocialScreen.b;
                                                        var8_9 = this.section(var11_5[79], var4_4.incoming(), var1_1, var8_9, var6_6);
                                                        var8_9 = this.section(var11_5[67], var4_4.sent(), var1_1, var8_9, var6_6);
                                                        if (!var5_7) break block31;
lbl44:

                                                        v6 = this.invites(var1_1, var8_9);
                                                        if (var5_7) break block32;
                                                        var8_9 = v6;
                                                        var8_9 = this.section(null, var4_4.online(), var1_1, var8_9, var6_6);
                                                        v4 = this;
                                                    }
                                                    if (v4.tab == 1) {
                                                        var8_9 = this.section(null, var4_4.offline(), var1_1, var8_9, var6_6);
                                                    }
                                                }
                                                v6 = var8_9;
                                            }
                                            if (var5_7) break block33;
                                            if (v6 != var7_8) break block34;
                                            v9 = Social.ready();
                                            if (var5_7) break block35;
                                            if (v9 != 0) break block36;
                                            v12 = SocialScreen.b[22];
                                            break block37;
                                        }
                                        v9 = var6_6.isEmpty();
                                    }
                                    if (var5_7) break block38;
                                    if (v9 != 0) break block39;
                                    v12 = SocialScreen.b[37];
                                    break block37;
                                }
                                v16 = this;
                                if (var5_7) break block40;
                                v9 = v16.tab;
                            }
                            if (v9 != 2) break block41;
                            v12 = SocialScreen.b[83];
                            break block37;
                        }
                        v16 = this;
                    }
                    v12 = v16.tab == 0 ? SocialScreen.b[12] : SocialScreen.b[62];
                }
                var10_11 = v12;
                Ui.text(Im.sub, var1_1 + 24.0f, var8_9 + 4.0f, Im.MUTE, var10_11);
                var8_9 += 26.0f;
            }
            ImGui.setCursorScreenPos((float)var1_1, (float)(var8_9 + 6.0f));
            v6 = 1.0f;
        }
        ImGui.dummy((float)v6, (float)1.0f);
        ImGui.endChild();
        ImGui.popStyleVar();
        ImGui.popStyleColor();
    }

    private float invites(float f, float f2) {
        float f3;
        block9: {
            ImDrawList imDrawList = ImGui.getWindowDrawList();
            boolean bl = ImScreen.a;
            float f4 = f + 16.0f;
            float f5 = 268.0f;
            float f6 = 88.0f;
            for (WorldShare$Invite worldShare$Invite : WorldShare.invites()) {
                block13: {
                    WorldShare$Invite worldShare$Invite2;
                    block12: {
                        String string;
                        float f7;
                        block10: {
                            block11: {
                                Ui.fill(imDrawList, f4, f2, f4 + f5, f2 + f6, Ui.col(Menu.ACCENT, 0.1f), 12.0f);
                                Ui.rect(imDrawList, f4, f2, f4 + f5, f2 + f6, Ui.col(Menu.SOFT, 0.25f), 12.0f);
                                Ui.head(worldShare$Invite.who(), f4 + 12.0f, f2 + 12.0f, 26.0f);
                                float f8 = f4 + 48.0f;
                                f3 = Ui.mid(Im.sora12, f2 + 12.0f, 26.0f);
                                if (bl) break block9;
                                float f9 = f3;
                                imDrawList.pushClipRect(f8, f2, f4 + f5 - 12.0f, f2 + 40.0f, true);
                                Ui.text(Im.sora12, f8, f9, Im.INK, worldShare$Invite.name());
                                String[] stringArray = b;
                                Ui.text(Im.sub, f8 + Ui.width(Im.sora12, worldShare$Invite.name()) + 4.0f, Ui.baseline(Im.sora12, f9, Im.sub), Im.SUB, stringArray[19]);
                                imDrawList.popClipRect();
                                f7 = (f5 - 24.0f - 6.0f) / 2.0f;
                                string = stringArray[53] + String.valueOf(worldShare$Invite.who());
                                if (bl) break block10;
                                if (!Menu.quiet(string, null, f4 + 12.0f, f2 + 48.0f, f7, 28.0f)) break block11;
                                WorldShare.dismiss(worldShare$Invite.who());
                            }
                            worldShare$Invite2 = worldShare$Invite;
                            if (bl) break block12;
                            string = b[81] + String.valueOf(worldShare$Invite2.who());
                        }
                        if (!Menu.button(string, f4 + 12.0f + f7 + 6.0f, f2 + 48.0f, f7, 28.0f, true)) break block13;
                        worldShare$Invite2 = worldShare$Invite;
                    }
                    WorldShare.accept(worldShare$Invite2);
                }
                f2 += f6 + 10.0f;
                if (!bl) continue;
            }
            f3 = f2;
        }
        return f3;
    }

    private float section(String string, List<Social$Friend> list, float f, float f2, String string2) {
        float f3;
        block23: {
            block18: {
                boolean bl = false;
                Iterator<Social$Friend> iterator = list.iterator();
                boolean bl2 = ImScreen.a;
                while (iterator.hasNext()) {
                    boolean bl3;
                    Social$Friend social$Friend;
                    block21: {
                        block22: {
                            block19: {
                                block20: {
                                    social$Friend = iterator.next();
                                    if (bl2) break block18;
                                    bl3 = string2.isEmpty();
                                    if (bl2) break block19;
                                    if (bl3) break block20;
                                    bl3 = social$Friend.name().toLowerCase(Locale.ROOT).contains(string2);
                                    if (bl2) break block19;
                                    if (!bl3) {
                                        continue;
                                    }
                                }
                                bl3 = bl;
                            }
                            if (bl2) break block21;
                            if (bl3) break block22;
                            if (string == null) break block22;
                            Ui.label(f + 24.0f, f2 + 5.0f, string);
                            f2 += 24.0f;
                        }
                        bl3 = true;
                    }
                    bl = bl3;
                    this.row(social$Friend, f + 12.0f, f2, 276.0f);
                    f2 += 48.0f;
                    if (!bl2) continue;
                }
                if (!bl || string == null) break block18;
                f3 = f2 + 6.0f;
                break block23;
            }
            f3 = f2;
        }
        return f3;
    }

    private static void face(UUID uUID, float f, float f2, float f3, String string) {
        Ui.head(uUID, f, f2, f3);
        if (string == null) {
            return;
        }
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        imDrawList.addCircleFilled(f + f3 - 2.0f, f2 + f3 - 2.0f, 7.5f, Ui.col(Im.BG), 20);
        imDrawList.addCircleFilled(f + f3 - 2.0f, f2 + f3 - 2.0f, 5.0f, Ui.col(SocialScreen.tone(string)), 20);
    }

    private static boolean round(String string, float f, float f2, boolean bl) {
        boolean bl2;
        block8: {
            ImDrawList imDrawList;
            block6: {
                float f3;
                block7: {
                    ImGui.setCursorScreenPos((float)f, (float)f2);
                    bl2 = ImGui.invisibleButton((String)string, (float)26.0f, (float)26.0f);
                    f3 = Ui.hot();
                    boolean bl3 = ImScreen.a;
                    imDrawList = ImGui.getWindowDrawList();
                    if (bl3) break block6;
                    if (!bl) break block7;
                    imDrawList.addCircleFilled(f + 13.0f, f2 + 13.0f, 13.0f, Ui.col(Ui.mix(Menu.FILL, Menu.ICON_FOOT, f3)), 24);
                    Menu.tick(f + 13.0f, f2 + 13.0f, 4.5f, Ui.col(Im.ON_FILL));
                    if (!bl3) break block8;
                }
                imDrawList.addCircleFilled(f + 13.0f, f2 + 13.0f, 13.0f, Ui.wash(0.07f + 0.05f * f3), 24);
                imDrawList.addLine(f + 9.0f, f2 + 9.0f, f + 17.0f, f2 + 17.0f, Ui.col(Im.SUB), 1.5f);
            }
            imDrawList.addLine(f + 17.0f, f2 + 9.0f, f + 9.0f, f2 + 17.0f, Ui.col(Im.SUB), 1.5f);
        }
        return bl2;
    }

    
    private void row(Social$Friend var1_1, float var2_2, float var3_3, float var4_4) {
        block145: {
            block146: {
                block144: {
                    block132: {
                        block133: {
                            block142: {
                                block143: {
                                    block139: {
                                        block140: {
                                            block141: {
                                                block137: {
                                                    block138: {
                                                        block136: {
                                                            block134: {
                                                                block135: {
                                                                    block130: {
                                                                        block131: {
                                                                            block128: {
                                                                                block129: {
                                                                                    block127: {
                                                                                        block123: {
                                                                                            block124: {
                                                                                                block126: {
                                                                                                    block125: {
                                                                                                        block118: {
                                                                                                            block122: {
                                                                                                                block121: {
                                                                                                                    block119: {
                                                                                                                        block120: {
                                                                                                                            block147: {
                                                                                                                                block117: {
                                                                                                                                    block116: {
                                                                                                                                        block115: {
                                                                                                                                            block114: {
                                                                                                                                                block113: {
                                                                                                                                                    block111: {
                                                                                                                                                        block112: {
                                                                                                                                                            block109: {
                                                                                                                                                                block110: {
                                                                                                                                                                    block108: {
                                                                                                                                                                        block107: {
                                                                                                                                                                            block106: {
                                                                                                                                                                                ImGui.setCursorScreenPos((float)var2_2, (float)var3_3);
                                                                                                                                                                                ImGui.setNextItemAllowOverlap();
                                                                                                                                                                                var19_5 = SocialScreen.b;
                                                                                                                                                                                var6_6 = ImGui.invisibleButton((String)(var19_5[23] + String.valueOf(var1_1.uuid())), (float)var4_4, (float)48.0f);
                                                                                                                                                                                var5_7 = ImScreen.a;
                                                                                                                                                                                v0 = var1_1;
                                                                                                                                                                                if (var5_7) break block106;
                                                                                                                                                                                if (!v0.accepted()) break block107;
                                                                                                                                                                                v0 = var1_1;
                                                                                                                                                                            }
                                                                                                                                                                            v3 = v0.uuid().equals(SocialScreen.peer);
                                                                                                                                                                            if (var5_7) break block108;
                                                                                                                                                                            if (v3 == 0) break block107;
                                                                                                                                                                            v3 = 1;
                                                                                                                                                                            break block108;
                                                                                                                                                                        }
                                                                                                                                                                        v3 = 0;
                                                                                                                                                                    }
                                                                                                                                                                    var7_8 = v3;
                                                                                                                                                                    var8_9 = ImGui.getWindowDrawList();
                                                                                                                                                                    var9_10 = Ui.hot();
                                                                                                                                                                    var19_5 = SocialScreen.b;
                                                                                                                                                                    var10_11 = Motion.to(var19_5[42] + String.valueOf(var1_1.uuid()), (boolean)var7_8, 0.15f);
                                                                                                                                                                    v5 = var10_11;
                                                                                                                                                                    if (var5_7) break block109;
                                                                                                                                                                    if (!(v5 > 0.01f)) break block110;
                                                                                                                                                                    var8_9.addRectFilled(var2_2, var3_3, var2_2 + var4_4, var3_3 + 48.0f, Ui.col(Ui.tint(0.1f * var10_11)), 10.0f);
                                                                                                                                                                }
                                                                                                                                                                v5 = var9_10;
                                                                                                                                                            }
                                                                                                                                                            cfr_temp_0 = v5 - 0.01f;
                                                                                                                                                            v8 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
                                                                                                                                                            if (var5_7) break block111;
                                                                                                                                                            if (v8 <= 0) break block112;
                                                                                                                                                            v8 = var7_8;
                                                                                                                                                            if (var5_7) break block111;
                                                                                                                                                            if (v8 != false) break block112;
                                                                                                                                                            var8_9.addRectFilled(var2_2, var3_3, var2_2 + var4_4, var3_3 + 48.0f, Ui.wash(0.04f * var9_10), 10.0f);
                                                                                                                                                        }
                                                                                                                                                        v8 = (float)false;
                                                                                                                                                    }
                                                                                                                                                    v13 = ImGui.getStyle().getAlpha();
                                                                                                                                                    v14 = var1_1;
                                                                                                                                                    if (var5_7) break block113;
                                                                                                                                                    if (!v14.accepted()) break block114;
                                                                                                                                                    v14 = var1_1;
                                                                                                                                                }
                                                                                                                                                if (v14.online()) break block114;
                                                                                                                                                v17 = 0.5f;
                                                                                                                                                break block115;
                                                                                                                                            }
                                                                                                                                            v17 = 1.0f;
                                                                                                                                        }
                                                                                                                                        ImGui.pushStyleVar((int)v8, (float)(v13 * v17));
                                                                                                                                        v19 = var1_1.uuid();
                                                                                                                                        v20 = var2_2 + 12.0f;
                                                                                                                                        v21 = var3_3 + 8.0f;
                                                                                                                                        v22 = 32.0f;
                                                                                                                                        v23 = var1_1;
                                                                                                                                        if (var5_7) break block116;
                                                                                                                                        if (!v23.accepted()) break block117;
                                                                                                                                        v23 = var1_1;
                                                                                                                                    }
                                                                                                                                    v26 = v23.status();
                                                                                                                                    break block147;
                                                                                                                                }
                                                                                                                                v26 = null;
                                                                                                                            }
                                                                                                                            SocialScreen.face(v19, v20, v21, v22, v26);
                                                                                                                            var11_12 = var2_2 + var4_4 - 12.0f;
                                                                                                                            var12_13 = var1_1.uuid().toString();
                                                                                                                            var19_5 = SocialScreen.b;
                                                                                                                            var13_14 = var19_5[70] + var12_13;
                                                                                                                            v27 = var1_1;
                                                                                                                            if (var5_7) break block118;
                                                                                                                            if (v27.incoming()) {
                                                                                                                            }
                                                                                                                            v29 = SocialScreen.b[86] + var13_14;
                                                                                                                            if (var5_7) break block119;
                                                                                                                            if (!SocialScreen.round(v29, var11_12 - 26.0f, var3_3 + 11.0f, true)) break block120;
                                                                                                                            Social.accept(var12_13, null);
                                                                                                                        }
                                                                                                                        v33 = var13_14;
                                                                                                                        if (var5_7) break block121;
                                                                                                                        v29 = SocialScreen.b[24] + (String)v33;
                                                                                                                    }
                                                                                                                    if (!SocialScreen.round(v29, var11_12 - 58.0f, var3_3 + 11.0f, false)) break block122;
                                                                                                                    v33 = var12_13;
                                                                                                                }
                                                                                                                Social.remove((String)v33, null);
                                                                                                            }
                                                                                                            var11_12 -= 70.0f;
                                                                                                            if (!var5_7) break block123;
lbl174:

                                                                                                            v27 = var1_1;
                                                                                                        }
                                                                                                        if (var5_7) break block124;
                                                                                                        if (v27.outgoing()) {
                                                                                                        }
                                                                                                        v38 = var13_14;
                                                                                                        if (var5_7) break block125;
                                                                                                        if (!SocialScreen.round(SocialScreen.b[58] + (String)v38, var11_12 - 26.0f, var3_3 + 11.0f, false)) break block126;
                                                                                                        v38 = var12_13;
                                                                                                    }
                                                                                                    Social.remove((String)v38, null);
                                                                                                }
                                                                                                var11_12 -= 38.0f;
                                                                                                if (!var5_7) break block123;
lbl208:

                                                                                                v27 = var1_1;
                                                                                            }
                                                                                            if (var5_7) break block127;
                                                                                            if (!SocialScreen.joinable(v27)) break block123;
                                                                                            var19_5 = SocialScreen.b;
                                                                                            var14_15 = Ui.width(Im.sora12, var19_5[82]) + 20.0f;
                                                                                            if (Menu.quiet(var19_5[59] + var13_14, null, var11_12 - var14_15, var3_3 + 11.0f, var14_15, 26.0f)) {
                                                                                                SocialScreen.join(var1_1);
                                                                                            }
                                                                                            var11_12 -= var14_15 + 12.0f;
                                                                                        }
                                                                                        v27 = var1_1;
                                                                                    }
                                                                                    if (var5_7) break block128;
                                                                                    if (!v27.accepted()) break block129;
                                                                                    v27 = var1_1;
                                                                                    if (var5_7) break block128;
                                                                                    if (!Social.muted(v27.uuid())) break block129;
                                                                                    var19_5 = SocialScreen.b;
                                                                                    Ui.icon(var19_5[18], var11_12 - 14.0f, var3_3 + 17.0f, 14.0f, Im.MUTE);
                                                                                    var11_12 -= 26.0f;
                                                                                }
                                                                                v27 = var1_1;
                                                                            }
                                                                            if (var5_7) break block130;
                                                                            if (!v27.accepted()) break block131;
                                                                            var14_16 = this.unread.put(var1_1.uuid(), var1_1.unread());
                                                                            if (var14_16 == null) break block131;
                                                                            v27 = var1_1;
                                                                            if (var5_7) break block130;
                                                                            if (v27.unread() <= var14_16) break block131;
                                                                            Motion.restart(SocialScreen.b[26] + String.valueOf(var1_1.uuid()));
                                                                        }
                                                                        v27 = var1_1;
                                                                    }
                                                                    v52 = v27.accepted();
                                                                    if (var5_7) break block132;
                                                                    if (v52 == 0) break block133;
                                                                    v52 = var1_1.unread();
                                                                    if (var5_7) break block132;
                                                                    if (v52 <= 0) break block133;
                                                                    v56 = var1_1;
                                                                    if (var5_7) break block134;
                                                                    if (v56.unread() <= 99) break block135;
                                                                    v59 = SocialScreen.b[55];
                                                                    break block136;
                                                                }
                                                                v56 = var1_1;
                                                            }
                                                            v59 = String.valueOf(v56.unread());
                                                        }
                                                        var14_17 = v59;
                                                        var15_18 = Math.max(18.0f, Ui.width(Im.label, var14_17) + 10.0f);
                                                        var16_19 = var11_12 - var15_18 / 2.0f;
                                                        var17_20 = Motion.since(SocialScreen.b[75] + String.valueOf(var1_1.uuid())) / 0.22f;
                                                        v61 = var17_20;
                                                        if (var5_7) break block137;
                                                        if (!(v61 >= 1.0f)) break block138;
                                                        v63 = 1.0f;
                                                        break block139;
                                                    }
                                                    v61 = var17_20;
                                                }
                                                if (var5_7) break block140;
                                                if (!(v61 < 0.6f)) break block141;
                                                v63 = 1.15f * Motion.out(var17_20 / 0.6f);
                                                break block139;
                                            }
                                            v61 = 1.15f;
                                        }
                                        v63 = v61 - 0.15f * Motion.out((var17_20 - 0.6f) / 0.4f);
                                    }
                                    var18_21 = v63;
                                    Ui.accent(var8_9, var16_19 - var15_18 / 2.0f * var18_21, var3_3 + 24.0f - 9.0f * var18_21, var16_19 + var15_18 / 2.0f * var18_21, var3_3 + 24.0f + 9.0f * var18_21, Ui.col(Menu.FILL), 9.0f * var18_21);
                                    v67 = var18_21;
                                    if (var5_7) break block142;
                                    if (!(v67 > 0.6f)) break block143;
                                    Ui.text(Im.label, var11_12 - var15_18 + (float)Math.floor((var15_18 - Ui.width(Im.label, var14_17)) / 2.0f), Ui.mid(Im.label, var3_3 + 24.0f - 9.0f, 18.0f), Im.ON_FILL, var14_17);
                                }
                                v67 = var11_12 - (var15_18 + 12.0f);
                            }
                            var11_12 = v67;
                        }
                        var8_9.pushClipRect(var2_2, var3_3, var11_12, var3_3 + 48.0f, true);
                        Ui.text(Im.sora14, var2_2 + 56.0f, Ui.mid(Im.sora14, var3_3, 48.0f), Im.INK, var1_1.name());
                        var8_9.popClipRect();
                        ImGui.popStyleVar();
                        v52 = (int)var6_6;
                    }
                    if (var5_7) break block144;
                    if (v52 == 0) break block145;
                    v52 = (int)var1_1.accepted();
                }
                if (var5_7) break block146;
                if (v52 == 0) break block145;
                v52 = var7_8;
            }
            if (v52 == 0) {
                this.open(var1_1.uuid());
            }
        }
    }

    private void me(float f, float f2) {
        block57: {
            block58: {
                block67: {
                    float f3;
                    float f4;
                    block68: {
                        boolean bl;
                        boolean bl2;
                        String string;
                        Social$Presence social$Presence;
                        Social$Presence social$Presence2;
                        block70: {
                            block69: {
                                Social$Presence social$Presence3;
                                String string2;
                                float[] fArray;
                                float f5;
                                float f6;
                                ImFont imFont;
                                float f7;
                                boolean bl3;
                                Social$Presence social$Presence4;
                                block63: {
                                    String[] stringArray;
                                    block64: {
                                        boolean bl4;
                                        String string3;
                                        Social$Presence social$Presence5;
                                        Social$Presence social$Presence6;
                                        block66: {
                                            block65: {
                                                Social$Presence social$Presence7;
                                                String string4;
                                                float f8;
                                                float f9;
                                                float f10;
                                                block55: {
                                                    String string5;
                                                    block56: {
                                                        float f11;
                                                        float f12;
                                                        float f13;
                                                        float f14;
                                                        ImDrawList imDrawList;
                                                        float f15;
                                                        block53: {
                                                            ImDrawList imDrawList2;
                                                            block54: {
                                                                float f16;
                                                                block51: {
                                                                    block52: {
                                                                        boolean bl5;
                                                                        block48: {
                                                                            block50: {
                                                                                block49: {
                                                                                    social$Presence4 = Social.me();
                                                                                    imDrawList2 = ImGui.getWindowDrawList();
                                                                                    imDrawList2.addLine(f + 12.0f, f2, f + 300.0f - 12.0f, f2, Ui.col(Im.LINE));
                                                                                    bl3 = ImScreen.a;
                                                                                    bl5 = social$Presence4.showStatus();
                                                                                    if (bl3) break block48;
                                                                                    if (!bl5) break block49;
                                                                                    bl5 = social$Presence4.status().equals(b[80]);
                                                                                    if (bl3) break block48;
                                                                                    if (!bl5) break block50;
                                                                                }
                                                                                bl5 = true;
                                                                                break block48;
                                                                            }
                                                                            bl5 = false;
                                                                        }
                                                                        boolean bl6 = bl5;
                                                                        UUID uUID = Social.selfUuid();
                                                                        float f17 = f + 20.0f;
                                                                        float f18 = f2 + 16.0f;
                                                                        float f19 = 32.0f;
                                                                        String string6 = bl6 ? b[32] : social$Presence4.status();
                                                                        SocialScreen.face(uUID, f17, f18, f19, string6);
                                                                        string5 = SocialScreen.named(social$Presence4.status());
                                                                        f10 = 12.0f + Ui.width(Im.bold13, string5) + 8.0f + 12.0f + 10.0f;
                                                                        f9 = f + 300.0f - 20.0f - f10;
                                                                        f8 = f2 + 18.0f;
                                                                        imDrawList2.pushClipRect(f + 64.0f, f2, f9 - 8.0f, f2 + 62.0f, true);
                                                                        Ui.text(Im.sora14, f + 64.0f, Ui.mid(Im.sora14, f2 + 16.0f, 32.0f), Im.INK, MinecraftClient.method_1551().method_1548().method_1676());
                                                                        imDrawList2.popClipRect();
                                                                        f16 = f9;
                                                                        if (bl3) break block51;
                                                                        ImGui.setCursorScreenPos((float)f16, (float)f8);
                                                                        if (!ImGui.invisibleButton((String)b[35], (float)f10, (float)28.0f)) break block52;
                                                                        ImGui.openPopup((String)b[69]);
                                                                    }
                                                                    f16 = Ui.hot();
                                                                }
                                                                f15 = f16;
                                                                imDrawList = imDrawList2;
                                                                f14 = f9;
                                                                f13 = f8;
                                                                f12 = f9 + f10;
                                                                f11 = f8;
                                                                if (bl3) break block53;
                                                                Ui.box(imDrawList, f14, f13, f12, f11 + 28.0f, null, Im.FIELD_LINE, 8.0f, 1.0f);
                                                                if (f15 > 0.01f) break block54;
                                                                string4 = b[69];
                                                                if (bl3) break block55;
                                                                if (!ImGui.isPopupOpen((String)string4)) break block56;
                                                            }
                                                            imDrawList = imDrawList2;
                                                            f14 = f9;
                                                            f13 = f8;
                                                            f12 = f9 + f10;
                                                            f11 = f8 + 28.0f;
                                                        }
                                                        float f20 = 0.04f;
                                                        float f21 = f15;
                                                        float f22 = ImGui.isPopupOpen((String)b[69]) ? 1.0f : 0.0f;
                                                        imDrawList.addRectFilled(f14, f13, f12, f11, Ui.wash(f20 * Math.max(f21, f22)), 8.0f);
                                                    }
                                                    Ui.text(Im.bold13, f9 + 12.0f, Ui.mid(Im.bold13, f8, 28.0f), Im.SUB, string5);
                                                    stringArray = b;
                                                    string4 = stringArray[40];
                                                }
                                                Ui.icon(string4, f9 + f10 - 22.0f, f8 + 8.0f, 12.0f, Im.SUB);
                                                f7 = 272.0f;
                                                ImGui.setNextWindowPos((float)(f + 14.0f), (float)(f2 - 8.0f + Ui.drop(b[69])), (int)1, (float)0.0f, (float)1.0f);
                                                if (bl3) break block57;
                                                ImGui.setNextWindowSize((float)f7, (float)0.0f);
                                                SocialScreen.popup(12.0f);
                                                if (!ImGui.beginPopup((String)b[69])) break block58;
                                                Ui.lift();
                                                int n = 0;
                                                while (n < STATUS.length) {
                                                    block61: {
                                                        block62: {
                                                            boolean bl7;
                                                            block60: {
                                                                boolean bl8;
                                                                block59: {
                                                                    if (bl3) break block57;
                                                                    bl7 = STATUS[n].equals(social$Presence4.status());
                                                                    bl8 = SocialScreen.pick(STATUS_NAME[n], 26.0f, bl7, Im.INK, 0);
                                                                    if (bl3) break block59;
                                                                    if (!bl8) break block60;
                                                                    bl8 = bl7;
                                                                }
                                                                if (!bl8) {
                                                                    Social.setPresence(new Social$Presence(STATUS[n], social$Presence4.showServer(), social$Presence4.showStatus()));
                                                                }
                                                            }
                                                            float f23 = (ImGui.getItemRectMinY() + ImGui.getItemRectMaxY()) / 2.0f;
                                                            ImDrawList imDrawList = ImGui.getWindowDrawList();
                                                            float f24 = ImGui.getItemRectMinX() + 13.0f;
                                                            float f25 = f23;
                                                            float f26 = 3.5f;
                                                            String string7 = n == 3 ? b[5] : STATUS[n];
                                                            imDrawList.addCircleFilled(f24, f25, f26, Ui.col(SocialScreen.tone(string7)), 16);
                                                            if (bl3) break block61;
                                                            if (!bl7) break block62;
                                                            Menu.tick(ImGui.getItemRectMaxX() - 13.0f, f23, 3.5f, Ui.col(Menu.ACCENT));
                                                        }
                                                        ++n;
                                                    }
                                                    if (!bl3) continue;
                                                }
                                                f4 = ImGui.getCursorScreenPosX();
                                                f3 = ImGui.getCursorScreenPosY() + 5.0f;
                                                ImGui.getWindowDrawList().addLine(f4 + 4.0f, f3, f4 + f7 - 14.0f, f3, Ui.wash(0.06f));
                                                f3 += 6.0f;
                                                imFont = Im.sora12r;
                                                f6 = f4 + 9.0f;
                                                f5 = Ui.mid(Im.sora12r, f3, 30.0f);
                                                fArray = Im.SUB;
                                                string2 = b[73];
                                                if (bl3) break block63;
                                                Ui.text(imFont, f6, f5, fArray, string2);
                                                if (!Ui.toggle(b[45], f4 + f7 - 51.0f, f3 + 7.0f, 32.0f, 16.0f, social$Presence4.showServer())) break block64;
                                                social$Presence6 = social$Presence7;
                                                social$Presence5 = social$Presence7;
                                                string3 = social$Presence4.status();
                                                if (social$Presence4.showServer()) break block65;
                                                bl4 = true;
                                                break block66;
                                            }
                                            bl4 = false;
                                        }
                                        social$Presence6(string3, bl4, social$Presence4.showStatus());
                                        Social.setPresence(social$Presence5);
                                    }
                                    imFont = Im.sora12r;
                                    f6 = f4 + 9.0f;
                                    f5 = Ui.mid(Im.sora12r, f3 + 32.0f, 30.0f);
                                    fArray = Im.SUB;
                                    stringArray = b;
                                    string2 = stringArray[50];
                                }
                                Ui.text(imFont, f6, f5, fArray, string2);
                                if (bl3) break block67;
                                if (!Ui.toggle(b[6], f4 + f7 - 51.0f, f3 + 39.0f, 32.0f, 16.0f, social$Presence4.showStatus())) break block68;
                                social$Presence2 = social$Presence3;
                                social$Presence = social$Presence3;
                                string = social$Presence4.status();
                                bl2 = social$Presence4.showServer();
                                if (social$Presence4.showStatus()) break block69;
                                bl = true;
                                break block70;
                            }
                            bl = false;
                        }
                        social$Presence2(string, bl2, bl);
                        Social.setPresence(social$Presence);
                    }
                    ImGui.setCursorScreenPos((float)f4, (float)(f3 + 61.0f));
                    ImGui.dummy((float)1.0f, (float)1.0f);
                }
                ImGui.endPopup();
            }
            SocialScreen.unpopup();
            ImGui.popStyleVar();
        }
    }

    private static void popup(float f) {
        ImGui.pushStyleVar((int)2, (float)5.0f, (float)5.0f);
        ImGui.pushStyleVar((int)9, (float)f);
        ImGui.pushStyleVar((int)14, (float)0.0f, (float)2.0f);
        ImGui.pushStyleVar((int)28, (float)0.0f, (float)0.5f);
        ImGui.pushStyleColor((int)4, (float)Im.POP[0], (float)Im.POP[1], (float)Im.POP[2], (float)Im.POP[3]);
        ImGui.pushStyleColor((int)24, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        ImGui.pushStyleColor((int)25, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        ImGui.pushStyleColor((int)26, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        ImGui.pushFont((ImFont)Im.sora12r);
    }

    private static boolean pick(String string, float f, boolean bl, float[] fArray, int n) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        ImDrawList imDrawList;
        float f7;
        float f8;
        float f9;
        boolean bl2;
        block6: {
            float f10;
            block4: {
                block5: {
                    bl2 = ImGui.selectable((String)(b[11] + string), (boolean)false, (int)n, (float)0.0f, (float)30.0f);
                    f9 = ImGui.getItemRectMinX();
                    f8 = ImGui.getItemRectMinY();
                    boolean bl3 = ImScreen.a;
                    f7 = ImGui.getItemRectMaxY() - f8;
                    imDrawList = ImGui.getWindowDrawList();
                    f6 = f9;
                    f5 = f8;
                    f4 = ImGui.getItemRectMaxX();
                    f3 = f8;
                    f10 = f7;
                    if (bl3) break block4;
                    f3 = f3 + f10;
                    if (!bl) break block5;
                    f2 = 0.1f;
                    break block6;
                }
                f10 = 0.07f;
            }
            f2 = f10 * Ui.hot();
        }
        Ui.fill(imDrawList, f6, f5, f4, f3, Ui.wash(f2), f7 / 2.0f);
        Ui.text(Im.sora12r, f9 + f, Ui.mid(Im.sora12r, f8, f7), fArray, string);
        return bl2;
    }

    private static void unpopup() {
        ImGui.popFont();
        ImGui.popStyleColor((int)4);
        ImGui.popStyleVar((int)4);
    }

    private static String whereabouts(Social$Friend social$Friend) {
        Object object;
        block18: {
            Social$Friend social$Friend2;
            block16: {
                block17: {
                    String string;
                    block14: {
                        boolean bl;
                        block15: {
                            Social$Friend social$Friend3;
                            block12: {
                                block13: {
                                    bl = ImScreen.a;
                                    social$Friend3 = social$Friend;
                                    if (bl) break block12;
                                    if (social$Friend3.online()) break block13;
                                    return b[66];
                                }
                                social$Friend3 = social$Friend;
                            }
                            string = social$Friend3.server();
                            if (bl) break block14;
                            if (!string.isEmpty()) break block15;
                            return SocialScreen.named(social$Friend.status());
                        }
                        social$Friend2 = social$Friend;
                        if (bl) break block16;
                        string = social$Friend2.server();
                    }
                    if (!string.startsWith(b[13])) break block17;
                    object = WorldShare.where(social$Friend.server());
                    break block18;
                }
                social$Friend2 = social$Friend;
            }
            object = b[27] + social$Friend2.server();
        }
        return object;
    }

    private void header(float f, float f2, float f3, Social$Friend social$Friend) {
        block66: {
            block80: {
                block76: {
                    boolean bl;
                    block78: {
                        block79: {
                            boolean bl2;
                            block77: {
                                SocialScreen socialScreen;
                                boolean bl3;
                                block75: {
                                    int n;
                                    float[] fArray;
                                    boolean bl4;
                                    float f4;
                                    String string;
                                    block73: {
                                        block74: {
                                            boolean bl5;
                                            block69: {
                                                block70: {
                                                    boolean bl6;
                                                    UUID uUID;
                                                    block71: {
                                                        block72: {
                                                            String string2;
                                                            boolean bl7;
                                                            block68: {
                                                                String[] stringArray;
                                                                block67: {
                                                                    block64: {
                                                                        float f5;
                                                                        block65: {
                                                                            boolean bl8;
                                                                            float f6;
                                                                            ImDrawList imDrawList;
                                                                            block58: {
                                                                                float f7;
                                                                                block61: {
                                                                                    boolean bl9;
                                                                                    UUID uUID2;
                                                                                    block62: {
                                                                                        block63: {
                                                                                            String string3;
                                                                                            boolean bl10;
                                                                                            block60: {
                                                                                                block59: {
                                                                                                    String string4;
                                                                                                    block53: {
                                                                                                        block54: {
                                                                                                            imDrawList = ImGui.getWindowDrawList();
                                                                                                            imDrawList.addLine(f, f2 + 65.0f, f + f3, f2 + 65.0f, Ui.col(Im.LINE));
                                                                                                            bl2 = ImScreen.a;
                                                                                                            SocialScreen.face(social$Friend.uuid(), f + 28.0f, f2 + 14.0f, 38.0f, social$Friend.status());
                                                                                                            f6 = f + f3 - 28.0f;
                                                                                                            f5 = f2 + 18.0f;
                                                                                                            ImGui.setCursorScreenPos((float)(f6 - 22.0f), (float)(f5 + 4.0f));
                                                                                                            string4 = b[34];
                                                                                                            if (bl2) break block53;
                                                                                                            if (!ImGui.invisibleButton((String)string4, (float)22.0f, (float)22.0f)) break block54;
                                                                                                            ImGui.openPopup((String)b[68]);
                                                                                                        }
                                                                                                        stringArray = b;
                                                                                                        string4 = stringArray[68];
                                                                                                    }
                                                                                                    bl8 = ImGui.isPopupOpen((String)string4);
                                                                                                    String string5 = b[68];
                                                                                                    float f10 = f6 - 20.0f;
                                                                                                    f10 = f5 + 6.0f;
                                                                                                    f10 = 18.0f;
                                                                                                    float[] fArray3 = Im.MUTE;
                                                                                                    fArray3 = Im.INK;
                                                                                                    float f11 = bl8 ? 1.0f : Ui.hot();
                                                                                                    boolean bl11 = Ui.icon(string5, f8, f9, f10, Ui.mix(fArray2, fArray3, f11));
                                                                                                    if (!bl2) {
                                                                                                        block55: {
                                                                                                            String string6;
                                                                                                            block56: {
                                                                                                                block57: {
                                                                                                                    f6 -= 34.0f;
                                                                                                                    if (!SocialScreen.joinable(social$Friend)) break block55;
                                                                                                                    string6 = social$Friend.server();
                                                                                                                    if (bl2) break block56;
                                                                                                                    if (!string6.startsWith(b[72])) break block57;
                                                                                                                    string6 = b[10];
                                                                                                                    break block56;
                                                                                                                }
                                                                                                                stringArray = b;
                                                                                                                string6 = stringArray[87];
                                                                                                            }
                                                                                                            String string7 = string6;
                                                                                                            float f12 = Menu.buttonWidth(string7, null);
                                                                                                            if (Menu.button(string7 + b[7], f6 - f12, f5, f12, 30.0f, true)) {
                                                                                                                SocialScreen.join(social$Friend);
                                                                                                            }
                                                                                                            f6 -= f12 + 12.0f;
                                                                                                        }
                                                                                                        bl11 = social$Friend.online();
                                                                                                    }
                                                                                                    if (!bl11 || MinecraftClient.method_1551().method_1576() == null) break block58;
                                                                                                    bl10 = WorldShare.invited(social$Friend.uuid());
                                                                                                    if (!bl10) break block59;
                                                                                                    string3 = b[31];
                                                                                                    break block60;
                                                                                                }
                                                                                                stringArray = b;
                                                                                                string3 = stringArray[78];
                                                                                            }
                                                                                            String string8 = string3;
                                                                                            stringArray = b;
                                                                                            f7 = Menu.quietWidth(string8, stringArray[1]);
                                                                                            if (!Menu.quiet(string8 + stringArray[41], stringArray[15], f6 - f7, f5, f7, 30.0f)) break block61;
                                                                                            uUID2 = social$Friend.uuid();
                                                                                            bl9 = bl10;
                                                                                            if (bl2) break block62;
                                                                                            if (bl9) break block63;
                                                                                            bl9 = true;
                                                                                            break block62;
                                                                                        }
                                                                                        bl9 = false;
                                                                                    }
                                                                                    WorldShare.invite(uUID2, bl9);
                                                                                }
                                                                                f6 -= f7 + 12.0f;
                                                                            }
                                                                            float f13 = f + 28.0f + 38.0f + 12.0f;
                                                                            float f14 = Ui.pair(Im.sora16, Im.sub, f2 + 14.0f, 38.0f);
                                                                            imDrawList.pushClipRect(f13, f2, f6, f2 + 65.0f, true);
                                                                            Ui.text(Im.sora16, f13, f14, Im.INK, social$Friend.name());
                                                                            Ui.text(Im.sub, f13, Ui.under(Im.sora16, Im.sub, f14) + 2.0f, Im.SUB, SocialScreen.whereabouts(social$Friend));
                                                                            if (bl2) break block64;
                                                                            imDrawList.popClipRect();
                                                                            if (bl8) break block65;
                                                                            this.removing = false;
                                                                        }
                                                                        ImGui.setNextWindowPos((float)(f + f3 - 28.0f), (float)(f5 + 32.0f + Ui.drop(b[68])), (int)1, (float)1.0f, (float)0.0f);
                                                                        if (bl2) break block66;
                                                                        ImGui.setNextWindowSize((float)170.0f, (float)0.0f);
                                                                        SocialScreen.popup(9.0f);
                                                                    }
                                                                    if (!ImGui.beginPopup((String)(stringArray = b)[68])) break block80;
                                                                    Ui.lift();
                                                                    bl7 = Social.muted(social$Friend.uuid());
                                                                    if (!bl7) break block67;
                                                                    string2 = stringArray[65];
                                                                    break block68;
                                                                }
                                                                stringArray = b;
                                                                string2 = stringArray[48];
                                                            }
                                                            bl5 = SocialScreen.pick(string2, 12.0f, false, Im.INK, 0);
                                                            if (bl2) break block69;
                                                            if (!bl5) break block70;
                                                            uUID = social$Friend.uuid();
                                                            bl6 = bl7;
                                                            if (bl2) break block71;
                                                            if (bl6) break block72;
                                                            bl6 = true;
                                                            break block71;
                                                        }
                                                        bl6 = false;
                                                    }
                                                    Social.setMuted(uUID, bl6);
                                                }
                                                bl5 = this.removing;
                                            }
                                            string = bl5 ? b[47] : b[16];
                                            f4 = 12.0f;
                                            bl4 = false;
                                            fArray = Im.DANGER;
                                            n = this.removing;
                                            if (bl2) break block73;
                                            if (n == 0) break block74;
                                            n = 0;
                                            break block73;
                                        }
                                        n = 1;
                                    }
                                    bl3 = SocialScreen.pick(string, f4, bl4, fArray, n);
                                    if (bl2) break block75;
                                    if (!bl3) break block76;
                                    socialScreen = this;
                                    if (bl2) break block77;
                                    bl3 = socialScreen.removing;
                                }
                                if (bl3) {
                                    Social.remove(social$Friend.uuid().toString(), null);
                                    peer = null;
                                }
                                socialScreen = this;
                            }
                            bl = this.removing;
                            if (bl2) break block78;
                            if (bl) break block79;
                            bl = true;
                            break block78;
                        }
                        bl = false;
                    }
                    socialScreen.removing = bl;
                }
                ImGui.endPopup();
            }
            SocialScreen.unpopup();
            ImGui.popStyleVar();
        }
    }

    
    private void chat(float var1_1, float var2_2, float var3_3, float var4_4, Social$Friend var5_5) {
        block94: {
            block93: {
                block91: {
                    block92: {
                        block89: {
                            block90: {
                                block88: {
                                    block74: {
                                        block70: {
                                            block71: {
                                                block72: {
                                                    block73: {
                                                        block67: {
                                                            block69: {
                                                                block68: {
                                                                    var6_6 = ImScreen.a;
                                                                    if (var5_5 != null) break block67;
                                                                    if (Social.ready()) break block68;
                                                                    v1 = Social.signInStatus();
                                                                    break block69;
                                                                }
                                                                var28_7 = SocialScreen.b;
                                                                v1 = var28_7[61];
                                                            }
                                                            var7_9 = v1;
                                                            Ui.text(Im.sub, var1_1 + (float)Math.floor((var3_3 - Ui.width(Im.sub, var7_9)) / 2.0f), var2_2 + var4_4 / 2.0f - 8.0f, Im.MUTE, var7_9);
                                                            return;
                                                        }
                                                        this.header(var1_1, var2_2, var3_3, var5_5);
                                                        var7_10 = var2_2 + var4_4 - 18.0f - 36.0f;
                                                        var8_11 = var2_2 + 65.0f;
                                                        var9_12 = var7_10 - 24.0f - var8_11 - 1.0f;
                                                        ImGui.setCursorScreenPos((float)(var1_1 + 1.0f), (float)(var8_11 + 1.0f));
                                                        ImGui.pushStyleColor((int)3, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                                                        ImGui.pushStyleVar((int)8, (float)0.0f);
                                                        var28_8 = SocialScreen.b;
                                                        ImGui.beginChild((String)var28_8[43], (float)(var3_3 - 2.0f), (float)var9_12, (boolean)false);
                                                        var10_13 = Social.conversation(var5_5.uuid());
                                                        var11_14 = MinecraftClient.method_1551().method_1548().method_1676();
                                                        var12_15 = var1_1 + 28.0f;
                                                        var14_17 = var13_16 = ImGui.getCursorScreenPosY() + Math.max(16.0f, var9_12 - this.talkH);
                                                        var15_18 = null;
                                                        var16_19 = 0L;
                                                        var18_20 = null;
                                                        ImGui.pushFont((ImFont)Im.sub);
                                                        v3 = var3_3 - 28.0f;
                                                        v4 = 6.0f;
                                                        if (var6_6) break block70;
                                                        ImGui.pushTextWrapPos((float)(v3 - v4));
                                                        ImGui.pushStyleColor((int)0, (float)Im.SUB[0], (float)Im.SUB[1], (float)Im.SUB[2], (float)1.0f);
                                                        if (var10_13.size() <= this.talkN) break block71;
                                                        v6 = this;
                                                        if (var6_6) break block72;
                                                        if (v6.talkN <= 0) {
                                                        }
                                                        v9 = this.talkN;
                                                        if (var6_6) break block73;
                                                        if (v9 != 0) break block71;
                                                        v9 = var10_13.size();
                                                    }
                                                    if (v9 != 1) break block71;
lbl80:

                                                    v6 = this;
                                                }
                                                v6.fresh = this.talkN;
                                                var28_8 = SocialScreen.b;
                                                Motion.restart(var28_8[57]);
                                            }
                                            var28_8 = SocialScreen.b;
                                            v3 = Motion.since(var28_8[29]);
                                            v4 = 0.22f;
                                        }
                                        var19_21 = Motion.out(v3 / v4);
                                        for (var20_22 = 0; var20_22 < var10_13.size(); ++var20_22) {
                                            block84: {
                                                block85: {
                                                    block86: {
                                                        block87: {
                                                            block82: {
                                                                block83: {
                                                                    block75: {
                                                                        block76: {
                                                                            block81: {
                                                                                block79: {
                                                                                    block80: {
                                                                                        block77: {
                                                                                            block78: {
                                                                                                if (var6_6) break block74;
                                                                                                var21_24 = var10_13.get(var20_22);
                                                                                                v14 = var20_22 >= this.fresh ? var19_21 : 1.0f;
                                                                                                var22_26 = v14;
                                                                                                var23_27 = 8.0f * (1.0f - var22_26);
                                                                                                var24_29 = Instant.ofEpochSecond(var21_24.at()).atZone(ZoneId.systemDefault()).toLocalDate();
                                                                                                if (var6_6) break block75;
                                                                                                if (var24_29.equals(var18_20)) break block76;
                                                                                                v17 = var18_20;
                                                                                                if (var6_6) break block77;
                                                                                                if (v17 == null) break block78;
                                                                                                var14_17 += 14.0f;
                                                                                            }
                                                                                            v17 = var24_29;
                                                                                        }
                                                                                        if (var6_6) break block79;
                                                                                        if (!v17.equals(LocalDate.now())) break block80;
                                                                                        v21 = SocialScreen.b[89];
                                                                                        break block81;
                                                                                    }
                                                                                    v17 = var24_29;
                                                                                }
                                                                                v21 = v17.format(SocialScreen.DAY);
                                                                            }
                                                                            var25_30 = v21;
                                                                            var26_31 = Ui.width(Im.label, var25_30);
                                                                            var27_32 = var1_1 + (float)Math.floor((var3_3 - var26_31) / 2.0f);
                                                                            Ui.text(Im.label, var27_32, var14_17, Im.MUTE, var25_30);
                                                                            var14_17 += 28.0f;
                                                                            var18_20 = var24_29;
                                                                            var15_18 = null;
                                                                        }
                                                                        ImGui.pushStyleVar((int)0, (float)(ImGui.getStyle().getAlpha() * var22_26));
                                                                    }
                                                                    v23 = var21_24.from();
                                                                    if (var6_6) break block82;
                                                                    if (!v23.equals(var15_18)) break block83;
                                                                    v25 = var21_24;
                                                                    if (var6_6) break block84;
                                                                    if (v25.at() - var16_19 <= SocialScreen.g) break block85;
                                                                }
                                                                v23 = var15_18;
                                                            }
                                                            if (var6_6) break block86;
                                                            if (v23 == null) break block87;
                                                            var14_17 += 14.0f;
                                                        }
                                                        v23 = var21_24.from();
                                                    }
                                                    v30 = v23.equals(var5_5.uuid()) != false ? var5_5.name() : var11_14;
                                                    var25_30 = v30;
                                                    Ui.head(var21_24.from(), var12_15, var14_17 + var23_27, 32.0f);
                                                    Ui.text(Im.sora13, var12_15 + 44.0f, var14_17 - 1.0f + var23_27, Im.INK, var25_30);
                                                    Ui.text(Im.label, var12_15 + 52.0f + Ui.width(Im.sora13, var25_30), var14_17 + 2.0f + var23_27, Im.MUTE, Instant.ofEpochSecond(var21_24.at()).atZone(ZoneId.systemDefault()).format(SocialScreen.CLOCK));
                                                    var14_17 += 18.0f;
                                                }
                                                ImGui.setCursorScreenPos((float)(var12_15 + 44.0f), (float)(var14_17 + var23_27));
                                                ImGui.textUnformatted((String)Im.need(var21_24.body()));
                                                var14_17 = ImGui.getItemRectMaxY() + 2.0f - var23_27;
                                                ImGui.popStyleVar();
                                                var15_18 = var21_24.from();
                                                v25 = var21_24;
                                            }
                                            var16_19 = v25.at();
                                            if (!var6_6) continue;
                                        }
                                        ImGui.popStyleColor();
                                        ImGui.popTextWrapPos();
                                        ImGui.popFont();
                                        var14_17 += 14.0f;
                                        if (var6_6) break block88;
                                        ImGui.setCursorScreenPos((float)var12_15, (float)(var14_17 - 1.0f));
                                        ImGui.dummy((float)1.0f, (float)1.0f);
                                        this.talkH = var14_17 - var13_16;
                                    }
                                    if (var10_13.size() != this.talkN) {
                                        ImGui.setScrollHereY((float)1.0f);
                                    }
                                    this.talkN = var10_13.size();
                                    ImGui.endChild();
                                    ImGui.popStyleVar();
                                    ImGui.popStyleColor();
                                }
                                if (var5_5.typing()) {
                                    Ui.text(Im.label, var12_15, var7_10 - 18.0f, Im.MUTE, var5_5.name() + SocialScreen.b[49]);
                                }
                                var20_23 = var3_3 - 56.0f - 40.0f - 8.0f;
                                ImGui.setCursorScreenPos((float)var12_15, (float)var7_10);
                                ImGui.setNextItemWidth((float)var20_23);
                                ImGui.pushStyleVar((int)11, (float)14.0f, (float)11.5f);
                                ImGui.pushStyleVar((int)12, (float)10.0f);
                                if (var6_6) break block89;
                                ImGui.pushStyleVar((int)13, (float)1.0f);
                                ImGui.pushFont((ImFont)Im.sub);
                                if (!this.focusDraft) break block90;
                                ImGui.setKeyboardFocusHere();
                            }
                            this.focusDraft = false;
                            this.typeToSearch();
                            var28_8 = SocialScreen.b;
                            ImGui.inputTextWithHint((String)var28_8[39], (String)(var28_8[30] + var5_5.name()), (ImString)this.draft);
                        }
                        var21_25 = Ui.entered();
                        if (var6_6) break block91;
                        if (!ImGui.isItemEdited()) break block92;
                        Social.typing(var5_5.uuid());
                    }
                    ImGui.popFont();
                    ImGui.popStyleVar((int)3);
                }
                var22_26 = var12_15 + var20_23 + 8.0f;
                ImGui.setCursorScreenPos((float)var22_26, (float)var7_10);
                var28_8 = SocialScreen.b;
                var21_25 |= ImGui.invisibleButton((String)var28_8[0], (float)40.0f, (float)36.0f);
                Ui.accent(ImGui.getWindowDrawList(), var22_26, var7_10, var22_26 + 40.0f, var7_10 + 36.0f, Ui.col(Ui.mix(Menu.FILL, Menu.ICON_FOOT, Ui.hot())), 10.0f);
                Ui.icon(var28_8[17], var22_26 + 12.5f, var7_10 + 10.5f, 15.0f, Im.ON_FILL);
                var23_28 = this.draft.get().trim();
                v39 = var21_25;
                if (var6_6) break block93;
                if (!v39) break block94;
                v39 = var23_28.isEmpty();
            }
            if (!v39) {
                Social.send(var5_5.uuid(), var23_28, (Consumer<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$chat$0(java.lang.String java.lang.String ), (Ljava/lang/String;)V)((SocialScreen)this, (String)var23_28));
                this.draft.set("");
                this.focusDraft = true;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static String currentServer() {
        ServerInfo serverInfo;
        block13: {
            ServerInfo serverInfo2;
            block14: {
                boolean bl;
                block12: {
                    MinecraftClient minecraftClient;
                    block10: {
                        MinecraftClient minecraftClient2;
                        block11: {
                            minecraftClient2 = MinecraftClient.method_1551();
                            bl = ImScreen.a;
                            minecraftClient = minecraftClient2;
                            if (bl) break block10;
                            if (!minecraftClient.method_1542()) break block11;
                            return "";
                        }
                        minecraftClient = minecraftClient2;
                    }
                    serverInfo2 = minecraftClient.method_1558();
                    serverInfo = serverInfo2;
                    if (bl) break block12;
                    if (serverInfo == null) return "";
                    serverInfo = serverInfo2;
                }
                if (bl) break block13;
                if (serverInfo.field_3761 != null) break block14;
                return "";
            }
            serverInfo = serverInfo2;
        }
        String string = serverInfo.field_3761;
        return string;
    }

    static void join(Social$Friend social$Friend) {
        block8: {
            Social$Friend social$Friend2;
            block6: {
                block7: {
                    boolean bl = ImScreen.a;
                    social$Friend2 = social$Friend;
                    if (bl) break block6;
                    if (!social$Friend2.server().startsWith(b[72])) break block7;
                    WorldShare.join(social$Friend.uuid(), social$Friend.name(), social$Friend.server());
                    if (!bl) break block8;
                }
                social$Friend2 = social$Friend;
            }
            SocialScreen.joinServer(social$Friend2.server());
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void joinServer(String string) {
        String string2;
        boolean bl;
        block10: {
            bl = ImScreen.a;
            string2 = string;
            if (bl) break block10;
            if (string2 == null) return;
            string2 = string;
        }
        if (string2.isBlank()) {
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (bl) return;
        try {
            if (minecraftClient.field_1687 != null) {
                minecraftClient.method_76795((Screen)new TitleScreen(), false);
            }
            ConnectScreen.method_36877((Screen)new TitleScreen(), (MinecraftClient)minecraftClient, (ServerAddress)ServerAddress.method_2950((String)string), (ServerInfo)new ServerInfo(string, string, ServerInfo.ServerType.field_45611), (boolean)false, null);
            return;
        }
        catch (Exception exception) {
            Aurora.LOG.warn(b[38], (Throwable)exception);
        }
    }

    private void lambda$chat$0(String string, String string2) {
        block9: {
            SocialScreen socialScreen;
            block10: {
                String string3;
                block8: {
                    boolean bl = ImScreen.a;
                    string3 = string2;
                    if (bl) break block8;
                    if (string3 == null) break block9;
                    socialScreen = this;
                    if (bl) break block10;
                    string3 = socialScreen.draft.get();
                }
                if (!string3.isEmpty()) break block9;
                socialScreen = this;
            }
            socialScreen.draft.set(string);
        }
    }

    
    static {
        block25: {
            block24: {
                block23: {
                    block22: {
                        block21: {
                            var15 = new String[90];
                            var13_1 = 0;
                            var12_2 = "K\u0012\u001f\u000b\u0005_\u001b\u001e\r%\u0004q\u0013\u001d\n\u0007w\u0011\u0017\u0003)O \u0006W\u0019\u001d\u0006.D\u0007W\u0011\u0017\u0003)O \u0006K\u0003\u0010\u001b5R\u0006\u001bT\u0019\n!E\u0005\\W<\"\r\u0004Q\u0013\u001d\n\nr\u0018\u0018\u0001`V*J\u001b\u0015\u0002\u001bT\u0011v\u0018\u0013\u0000$XeQ\u0004Q\u0000.M,V\u0012_\u0006O\u0018\u0003\u0003$\u001b\u0003\\\u0019\u0015\u0005_\u001b\u001e\r%\rj\u0012\u001c\u00006De^\u0005\u0018\n.E\u0004K\u0012\u001f\u000b\u0004Z\u0012\u001d\u0003\u001aQ\u0019\u0007\u00064D!\u0018\u000e\u001e\u001a`U*\u0018\u0003\u0019\n)SeO\u0018\u0003\u0003$\ny\u0013\u0015O&S,]\u0019\u0015\u0003\\\u0019\u0015\u001ck\u001e\u0016\u0001`H+\u0018\u0003\u001eO3D \u0018\u000e\u001e\u001a2\u0001#J\u001e\u0014\u0001$Rk\u0003J\u0018\u0006\u0002V\u0018\u000e|\u0018Q\u0001/Ue\\\u001e\u0002\u001b5S'\u0006M\u0019\u0003\n!E\u000bh\u001b\u0010\u0016)O\"\u0018\u0018\u001fO\u0004Q\u0013\u001d\n\u0003U\u0004\u0016\bu\u0012\u0002\u001c!F \u0018\u0007q\u0019\u0007\u00064D!\u0007W\u0011\u0017\u0003)O \u0006k\u0018\u0012\u0006!M\u0004U\u0018\u0003\n\u0002U\u0012\bj\u0012\u0000\u001a%R1K\u0010v\u0018\u0005\u0007)O\"\u0018\u001a\u0010\u001b#I KY\u0012r\u0018\u0018\u0001`G7Q\u0012\u001f\u000b`G$Q\u001b\u0014\u000b\u0007\u001bT\u0015\u001d!G1\u0005[\u0016\u0003\n4\u0006\u001bT\u0019\n!E\u0003J\u0018\u0006\u0004L\u0016\u001d\u0004\u0006w\u0019\u001d\u0006.D\u0006K\u0012\u0003\u0019%S\u0005\u001bT\u0010\u000b$\u0005k\u0002\u0003\n\u007f\u0004u\u0002\u0005\n\r\u0018\u001e\u0002O4X5Q\u0019\u0016An\u000f\u000e|\u001e\u0002\u001f,@<\u0018\u0004\u0005\u000e4T6\u0006k\u0018\u0012\u0006!M\u0003Y\u0013\u0015\f|\u0012\u0012\u0003)O \u001bT\u0018\u00016\b\u001bT\u0002\n!S&P\u0003\u0001NZ\u0006H\u0012\u001e\u001f,D\u0003U\u0004\u0016\u0002V\u0018\u0004r\u0018\u0018\u0001\tq\u0019\u0007\u00063H'T\u0012 h\u001e\u0012\u0004`@e^\u0005\u0018\n.EeL\u0018Q\u001c4@7LW\u0012\u0007!U1Q\u0019\u0016A\u000fv\u0018Q\t2H V\u0013\u0002O9D1\u0016\u0005p?K\u0002-\tQ\u0019\u0007\u00063H'T\u0012\u0006m\u0019\u001c\u001a4D\u0007w\u0011\u0017\u0003)O \u0004k\u0012\u001f\u001b\u0004U\u0018\u0003\n\u0002U\u0012\u0002\u001bT\u000ek\u0012\u0010\u001d#Ie^\u0005\u0018\n.E6\u0006O\u0018\u0003\u0003$\u001b\u000e|\u001e\u0002\u001f,@<\u0018\u0004\u0014\u001d6D7\u0006W\u0019\u001d\u0006.D\u0006M\u0019\u0003\n!E\tM\u0004\u0014\u001d\u001fQ)M\u0004\fk\u0012\u001f\u000b`S I\u0002\u0014\u001c4\u0006q\u0019\u0007\u00064D\bj\u0012\u0000\u001a%R1K\tQ\u0019\u0007\u00063H'T\u0012\u000fr\u0018\u0018\u0001`V*J\u001b\u0015LcH+N\u0004r\u0018\u0018\u0001\fv\u0018Q\u001d%P0]\u0004\u0005\u001cn\u0006w\u0019\u001d\u0006.D\u000bh\u001b\u0010\u0016%SeV\u0016\u001c\n\u0002W\u001c\u000br\u0018\u0018\u0001`R J\u0001\u0014\u001d";
                            var14_3 = "K\u0012\u001f\u000b\u0005_\u001b\u001e\r%\u0004q\u0013\u001d\n\u0007w\u0011\u0017\u0003)O \u0006W\u0019\u001d\u0006.D\u0007W\u0011\u0017\u0003)O \u0006K\u0003\u0010\u001b5R\u0006\u001bT\u0019\n!E\u0005\\W<\"\r\u0004Q\u0013\u001d\n\nr\u0018\u0018\u0001`V*J\u001b\u0015\u0002\u001bT\u0011v\u0018\u0013\u0000$XeQ\u0004Q\u0000.M,V\u0012_\u0006O\u0018\u0003\u0003$\u001b\u0003\\\u0019\u0015\u0005_\u001b\u001e\r%\rj\u0012\u001c\u00006De^\u0005\u0018\n.E\u0004K\u0012\u001f\u000b\u0004Z\u0012\u001d\u0003\u001aQ\u0019\u0007\u00064D!\u0018\u000e\u001e\u001a`U*\u0018\u0003\u0019\n)SeO\u0018\u0003\u0003$\ny\u0013\u0015O&S,]\u0019\u0015\u0003\\\u0019\u0015\u001ck\u001e\u0016\u0001`H+\u0018\u0003\u001eO3D \u0018\u000e\u001e\u001a2\u0001#J\u001e\u0014\u0001$Rk\u0003J\u0018\u0006\u0002V\u0018\u000e|\u0018Q\u0001/Ue\\\u001e\u0002\u001b5S'\u0006M\u0019\u0003\n!E\u000bh\u001b\u0010\u0016)O\"\u0018\u0018\u001fO\u0004Q\u0013\u001d\n\u0003U\u0004\u0016\bu\u0012\u0002\u001c!F \u0018\u0007q\u0019\u0007\u00064D!\u0007W\u0011\u0017\u0003)O \u0006k\u0018\u0012\u0006!M\u0004U\u0018\u0003\n\u0002U\u0012\bj\u0012\u0000\u001a%R1K\u0010v\u0018\u0005\u0007)O\"\u0018\u001a\u0010\u001b#I KY\u0012r\u0018\u0018\u0001`G7Q\u0012\u001f\u000b`G$Q\u001b\u0014\u000b\u0007\u001bT\u0015\u001d!G1\u0005[\u0016\u0003\n4\u0006\u001bT\u0019\n!E\u0003J\u0018\u0006\u0004L\u0016\u001d\u0004\u0006w\u0019\u001d\u0006.D\u0006K\u0012\u0003\u0019%S\u0005\u001bT\u0010\u000b$\u0005k\u0002\u0003\n\u007f\u0004u\u0002\u0005\n\r\u0018\u001e\u0002O4X5Q\u0019\u0016An\u000f\u000e|\u001e\u0002\u001f,@<\u0018\u0004\u0005\u000e4T6\u0006k\u0018\u0012\u0006!M\u0003Y\u0013\u0015\f|\u0012\u0012\u0003)O \u001bT\u0018\u00016\b\u001bT\u0002\n!S&P\u0003\u0001NZ\u0006H\u0012\u001e\u001f,D\u0003U\u0004\u0016\u0002V\u0018\u0004r\u0018\u0018\u0001\tq\u0019\u0007\u00063H'T\u0012 h\u001e\u0012\u0004`@e^\u0005\u0018\n.EeL\u0018Q\u001c4@7LW\u0012\u0007!U1Q\u0019\u0016A\u000fv\u0018Q\t2H V\u0013\u0002O9D1\u0016\u0005p?K\u0002-\tQ\u0019\u0007\u00063H'T\u0012\u0006m\u0019\u001c\u001a4D\u0007w\u0011\u0017\u0003)O \u0004k\u0012\u001f\u001b\u0004U\u0018\u0003\n\u0002U\u0012\u0002\u001bT\u000ek\u0012\u0010\u001d#Ie^\u0005\u0018\n.E6\u0006O\u0018\u0003\u0003$\u001b\u000e|\u001e\u0002\u001f,@<\u0018\u0004\u0014\u001d6D7\u0006W\u0019\u001d\u0006.D\u0006M\u0019\u0003\n!E\tM\u0004\u0014\u001d\u001fQ)M\u0004\fk\u0012\u001f\u000b`S I\u0002\u0014\u001c4\u0006q\u0019\u0007\u00064D\bj\u0012\u0000\u001a%R1K\tQ\u0019\u0007\u00063H'T\u0012\u000fr\u0018\u0018\u0001`V*J\u001b\u0015LcH+N\u0004r\u0018\u0018\u0001\fv\u0018Q\u001d%P0]\u0004\u0005\u001cn\u0006w\u0019\u001d\u0006.D\u000bh\u001b\u0010\u0016%SeV\u0016\u001c\n\u0002W\u001c\u000br\u0018\u0018\u0001`R J\u0001\u0014\u001d".length();
                            var11_4 = 4;
                            var10_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var10_5;
                                v1 = var12_2.substring(v0, v0 + var11_4);
                                v2 = -1;
                                break block21;
                                break;
                            }
lbl12:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                var12_2 = "y\u001b\u001d\u0005l\u0018\u0015\u000e9";
                                var14_3 = "y\u001b\u001d\u0005l\u0018\u0015\u000e9".length();
                                var11_4 = 3;
                                var10_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var10_5;
                                    v1 = var12_2.substring(v4, v4 + var11_4);
                                    v2 = 0;
                                    break block21;
                                    break;
                                }
                                break;
                            }
lbl26:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                break block22;
                                break;
                            }
                        }
                        v5 = v1.toCharArray();
                        v6 = v5;
                        v7 = v5.length;
                        var16_7 = 0;
                        do {
                            v6 = v6;
                            v8 = var16_7;
                            v9 = v6[v8];
                            switch (var16_7 % 7) {
                                case 0: {
                                    v10 = 56;
                                    break;
                                }
                                case 1: {
                                    v10 = 119;
                                    break;
                                }
                                case 2: {
                                    v10 = 113;
                                    break;
                                }
                                case 3: {
                                    v10 = 111;
                                    break;
                                }
                                case 4: {
                                    v10 = 64;
                                    break;
                                }
                                case 5: {
                                    v10 = 33;
                                    break;
                                }
                                default: {
                                    v10 = 69;
                                }
                            }
                            v6[v8] = (char)(v9 ^ v10);
                            ++var16_7;
lbl65:

                            v7 = v7;
                        } while (v7 > var16_7);
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
                    SocialScreen.b = var15;
                    var2_8 = 1491683867100436853L;
                    var8_9 = new long[3];
                    var5_10 = 0;
                    var6_11 = "\u00b2V\u00bdRB\u00d2/\u00d7\u00a3\u00a6O~\u0082y\u0089\u0012x\u00b9\u00d2Yl\u00b4D\u00b6";
                    var7_12 = "\u00b2V\u00bdRB\u00d2/\u00d7\u00a3\u00a6O~\u0082y\u0089\u0012x\u00b9\u00d2Yl\u00b4D\u00b6".length();
                    var4_13 = 0;
                    while (true) {
                        break block23;
                        break;
                    }
lbl84:

                    while (true) {
                        var8_9[v11] = (((long)var9_14[0] & 255L) << 56 | ((long)var9_14[1] & 255L) << 48 | ((long)var9_14[2] & 255L) << 40 | ((long)var9_14[3] & 255L) << 32 | ((long)var9_14[4] & 255L) << 24 | ((long)var9_14[5] & 255L) << 16 | ((long)var9_14[6] & 255L) << 8 | (long)var9_14[7] & 255L) ^ var2_8;
                        if (var4_13 < var7_12) continue;
                        break block24;
                        break;
                    }
                }
                var9_14 = var6_11.substring(var4_13, var4_13 += 8).getBytes("ISO-8859-1");
                v11 = var5_10++;
                while (true)
            }
            SocialScreen.c = var8_9;
            SocialScreen.d = new Integer[3];
            break block25;
lbl99:

            while (true) {
                continue;
                break;
            }
        }
        var0_15 = 6365258909526217860L;
        while (true)
        SocialScreen.g = 6365258909526218152L ^ var0_15;
        v12 = new String[4];
        var10_6 = SocialScreen.b;
        v12[0] = var10_6[74];
        v12[1] = var10_6[9];
        v12[2] = var10_6[21];
        v12[3] = var10_6[64];
        SocialScreen.STATUS = v12;
        SocialScreen.STATUS_NAME = new String[]{var10_6[84], var10_6[2], var10_6[25], var10_6[60]};
        SocialScreen.TAB = new String[]{var10_6[44], var10_6[88], var10_6[36]};
        SocialScreen.CLOCK = DateTimeFormatter.ofPattern(var10_6[63]);
        SocialScreen.DAY = DateTimeFormatter.ofPattern(var10_6[8]);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6919;
        if (d[n2] == null) {
            SocialScreen.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }
}
