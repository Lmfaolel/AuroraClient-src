package dev.quelchi.aurora.gui.im;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.gui.VectorIcons;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Motion;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.profile.Profile;
import dev.quelchi.aurora.profile.ProfileManager;
import dev.quelchi.aurora.profile.ProfileService;
import dev.quelchi.aurora.profile.ProfileService$Listed;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.type.ImString;
import java.io.IOException;
import java.lang.invoke.LambdaMetafactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import net.minecraft.client.MinecraftClient;

public final class ProfilesScreen
extends ImScreen {
    private static final float LIST = 300.0f;
    private static final float ROW = 46.0f;
    private static final float CHIP = 34.0f;
    private static final float CARD = 100.0f;
    private static final float CARD_MIN = 240.0f;
    private static final float LINE_H = 50.0f;
    private static final String[] TAB;
    private static final String[] SORT;
    private static final String[] SORT_NAMES;
    private static final float[] RED;
    private static String used;
    private int tab;
    private int sort;
    private final ImString search = new ImString(48);
    private final ImString name = new ImString(128);
    private final ImString code = new ImString(8);
    private List<Profile> mine = ProfileManager.list();
    private List<ProfileService$Listed> shared = List.of();
    private String queried;
    private boolean naming;
    private boolean renaming;
    private boolean focus;
    private String picked = used;
    private Profile target;
    private String note = "";
    private float popX;
    private float popY;
    private String open;
    private boolean up;
    private boolean esc;
    private String askTitle = "";
    private String askBody = "";
    private String askVerb = "";
    private boolean askRed;
    private Runnable askRun;
    private JsonObject found;
    private boolean wrong;
    private boolean shook;
    private String checked = "";
    private final Set<String> saving = new HashSet<String>();
    private static final float[] SHAKE;
    private static final String[] b;

    public ProfilesScreen() {
        super(b[74]);
    }

    @Override
    protected boolean key(int n) {
        block20: {
            ProfilesScreen profilesScreen;
            block21: {
                boolean bl;
                block19: {
                    int n2;
                    block17: {
                        block18: {
                            bl = ImScreen.a;
                            n2 = n;
                            if (bl) break block17;
                            if (n2 == 256) break block18;
                            return false;
                        }
                        profilesScreen = this;
                        if (bl) break block19;
                        n2 = profilesScreen.up ? 1 : 0;
                    }
                    if (n2 != 0) {
                        this.esc = true;
                        return true;
                    }
                    profilesScreen = this;
                }
                if (bl) break block20;
                if (profilesScreen.naming) break block21;
                profilesScreen = this;
                if (bl) break block20;
                if (profilesScreen.renaming) break block21;
                return false;
            }
            this.naming = false;
            profilesScreen = this;
        }
        profilesScreen.renaming = false;
        return true;
    }

    private void reload() {
        this.mine = ProfileManager.list();
    }

    private void ask(String string, String string2, String string3, boolean bl, Runnable runnable) {
        this.askTitle = string;
        this.askBody = string2;
        this.askVerb = string3;
        this.askRed = bl;
        this.askRun = runnable;
        this.open = b[11];
    }

    private void use(Profile profile) {
        block5: {
            String string;
            block4: {
                this.note = ProfileManager.apply(profile.data());
                boolean bl = ImScreen.a;
                string = this.note;
                if (bl) break block4;
                if (!string.isEmpty()) break block5;
                string = profile.name();
            }
            used = string;
        }
    }

    @Override
    public boolean shutsOnMenuKey() {
        return true;
    }

    @Override
    protected void draw() {
        block19: {
            ProfilesScreen profilesScreen;
            block20: {
                boolean bl;
                block18: {
                    ProfilesScreen profilesScreen2;
                    float f;
                    float f2;
                    float f3;
                    float f4;
                    block16: {
                        block17: {
                            block15: {
                                block14: {
                                    bl = ImScreen.a;
                                    int n = Menu.top(b[103], TAB, this.tab, 0.0f);
                                    if (bl) break block14;
                                    if (n < 0) break block15;
                                    this.tab = n;
                                    this.note = "";
                                    this.naming = false;
                                }
                                this.renaming = false;
                            }
                            f4 = Menu.x;
                            f3 = Menu.y + 64.0f;
                            f2 = Menu.w;
                            f = Menu.h - 64.0f;
                            profilesScreen2 = this;
                            if (bl) break block16;
                            if (profilesScreen2.tab != 0) break block17;
                            this.mine(f4, f3, f2, f);
                            if (!bl) break block18;
                        }
                        profilesScreen2 = this;
                    }
                    profilesScreen2.community(f4, f3, f2, f);
                }
                profilesScreen = this;
                if (bl) break block19;
                if (profilesScreen.open == null) break block20;
                ImGui.openPopup((String)this.open);
                this.open = null;
            }
            this.popups();
            profilesScreen = this;
        }
        profilesScreen.esc = false;
        Menu.end();
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

    private static void child(String string, float f, float f2, float f3, float f4) {
        ImGui.setCursorScreenPos((float)f, (float)f2);
        ImGui.pushStyleColor((int)3, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        ImGui.pushStyleVar((int)8, (float)0.0f);
        ImGui.beginChild((String)string, (float)f3, (float)f4, (boolean)false);
    }

    private static void endChild(float f, float f2) {
        ImGui.setCursorScreenPos((float)f, (float)f2);
        ImGui.dummy((float)1.0f, (float)1.0f);
        ImGui.endChild();
        ImGui.popStyleVar();
        ImGui.popStyleColor();
    }

    private Profile shown() {
        Profile profile;
        block15: {
            Object object;
            block14: {
                block11: {
                    Iterator<Profile> iterator = this.mine.iterator();
                    boolean bl = ImScreen.a;
                    while (iterator.hasNext()) {
                        block13: {
                            Profile profile2;
                            block12: {
                                Profile profile3 = iterator.next();
                                if (bl) break block11;
                                profile2 = profile3;
                                if (bl) break block12;
                                if (!profile2.name().equals(this.picked)) break block13;
                                profile2 = profile3;
                            }
                            return profile2;
                        }
                        if (!bl) continue;
                    }
                    object = this;
                    if (bl) break block14;
                    if (!object.mine.isEmpty()) break block11;
                    profile = null;
                    break block15;
                }
                object = this.mine.get(0);
            }
            profile = (Profile)object;
        }
        return profile;
    }

    
    private void mine(float f, float f2, float f3, float f4) {
        block27: {
            ProfilesScreen profilesScreen;
            float f5;
            float f6;
            float f7;
            boolean bl;
            block28: {
                block29: {
                    block31: {
                        ProfilesScreen profilesScreen2;
                        block30: {
                            ImGui.getWindowDrawList().addLine(f + 300.0f, f2, f + 300.0f, f2 + f4, Ui.col(Im.LINE));
                            float f8 = f + 16.0f;
                            bl = ImScreen.a;
                            float f9 = f2 + 16.0f;
                            float f10 = 268.0f;
                            String[] stringArray = b;
                            float f11 = Menu.buttonWidth(stringArray[33], stringArray[72]);
                            String string = stringArray[63];
                            String string2 = stringArray[101];
                            float f12 = f8;
                            float f13 = f9;
                            float f14 = f10 - f11 - 8.0f;
                            float f15 = 34.0f;
                            boolean bl2 = true;
                            if (!bl) {
                                if (Menu.button(string, string2, f12, f13, f14, f15, bl2)) {
                                    this.naming = true;
                                    this.focus = true;
                                    this.name.set("");
                                }
                                stringArray = b;
                                string = stringArray[42];
                                string2 = stringArray[57];
                                f12 = f8 + f10 - f11;
                                f13 = f9;
                                f14 = f11;
                                f15 = 34.0f;
                                bl2 = false;
                            }
                            if (Menu.button(string, string2, f12, f13, f14, f15, bl2)) {
                                this.code.set("");
                                this.checked = "";
                                this.found = null;
                                this.wrong = false;
                                this.popX = f8 + f10 - 246.0f;
                                this.popY = f9 + 40.0f;
                                this.open = b[8];
                            }
                            f7 = f + 12.0f;
                            f6 = 276.0f;
                            stringArray = b;
                            ProfilesScreen.child(stringArray[25], f + 1.0f, f9 + 46.0f, 298.0f, f2 + f4 - f9 - 47.0f);
                            f5 = ImGui.getCursorScreenPosY();
                            profilesScreen = this;
                            if (bl) break block28;
                            if (!profilesScreen.naming) break block29;
                            profilesScreen2 = this;
                            if (bl) break block30;
                            if (!profilesScreen2.field(b[51], this.name, f7, f5 + 6.0f, f6)) break block31;
                            profilesScreen2 = this;
                        }
                        profilesScreen2.save();
                    }
                    f5 += 46.0f;
                }
                profilesScreen = this;
            }
            Profile profile = profilesScreen.shown();
            ImDrawList imDrawList = ImGui.getWindowDrawList();
            int n = 0;
            while (n < this.mine.size()) {
                block36: {
                    float f16;
                    Profile profile2;
                    block35: {
                        boolean bl3;
                        block34: {
                            float f17;
                            float f18;
                            block33: {
                                block32: {
                                    profile2 = this.mine.get(n);
                                    if (bl) break block27;
                                    ImGui.setCursorScreenPos((float)f7, (float)f5);
                                    bl3 = ImGui.invisibleButton((String)(b[29] + n), (float)f6, (float)46.0f);
                                    f18 = Ui.hot();
                                    if (bl) break block32;
                                    if (profile2 != profile) break block33;
                                    imDrawList.addRectFilled(f7, f5, f7 + f6, f5 + 46.0f, Ui.col(Ui.tint(0.1f)), 10.0f);
                                }
                                if (!bl) break block34;
                            }
                            f16 = (f17 = f18 - 0.01f) == 0.0f ? 0 : (f17 > 0.0f ? 1 : -1);
                            if (bl) break block35;
                            if (f16 > 0) {
                                imDrawList.addRectFilled(f7, f5, f7 + f6, f5 + 46.0f, Ui.wash(0.04f * f18), 10.0f);
                            }
                        }
                        Ui.text(Im.sora14, f7 + 16.0f, Ui.mid(Im.sora14, f5, 46.0f), Im.INK, ProfilesScreen.fit(Im.sora14, profile2.name(), f6 - 32.0f));
                        if (bl) break block36;
                        f16 = (float)bl3;
                    }
                    if (f16 != false && profile2 != profile) {
                        this.picked = profile2.name();
                        this.renaming = false;
                    }
                    f5 += 46.0f;
                    ++n;
                }
                if (!bl) continue;
            }
            ProfilesScreen.endChild(f7, f5 + 8.0f);
            this.detail(profile, f + 300.0f, f2, f3 - 300.0f);
        }
    }

    private void save() {
        try {
            Profile profile = ProfileManager.save(this.name.get().trim());
            used = profile.name();
            this.picked = profile.name();
            this.naming = false;
            this.note = "";
            this.reload();
        }
        catch (IOException iOException) {
            this.note = iOException.getMessage();
        }
    }

    private boolean field(String string, ImString imString, float f, float f2, float f3) {
        block4: {
            block5: {
                ImGui.setCursorScreenPos((float)f, (float)f2);
                boolean bl = ImScreen.a;
                ImGui.setNextItemWidth((float)f3);
                if (bl) break block4;
                ImGui.pushStyleVar((int)11, (float)12.0f, (float)9.0f);
                ImGui.pushStyleVar((int)13, (float)1.0f);
                ImGui.pushFont((ImFont)Im.sora12r);
                if (!this.focus) break block5;
                ImGui.setKeyboardFocusHere();
                this.focus = false;
            }
            ImGui.inputTextWithHint((String)string, (String)b[58], (ImString)imString);
        }
        boolean bl = Ui.entered();
        ImGui.popFont();
        ImGui.popStyleVar((int)2);
        return bl;
    }

    
    private void detail(Profile var1_1, float var2_2, float var3_3, float var4_4) {
        block126: {
            block110: {
                block103: {
                    block104: {
                        block108: {
                            block109: {
                                block107: {
                                    block106: {
                                        block102: {
                                            block124: {
                                                block101: {
                                                    block121: {
                                                        block100: {
                                                            block123: {
                                                                block122: {
                                                                    block98: {
                                                                        block99: {
                                                                            block120: {
                                                                                block96: {
                                                                                    block97: {
                                                                                        block119: {
                                                                                            block94: {
                                                                                                block95: {
                                                                                                    block118: {
                                                                                                        block92: {
                                                                                                            block93: {
                                                                                                                block117: {
                                                                                                                    block116: {
                                                                                                                        block90: {
                                                                                                                            block91: {
                                                                                                                                block87: {
                                                                                                                                    block89: {
                                                                                                                                        block113: {
                                                                                                                                            block88: {
                                                                                                                                                block115: {
                                                                                                                                                    block114: {
                                                                                                                                                        var6_5 = var2_2 + 32.0f;
                                                                                                                                                        var7_6 = var3_3 + 24.0f;
                                                                                                                                                        var8_7 = var2_2 + var4_4 - 32.0f;
                                                                                                                                                        var5_8 = ImScreen.a;
                                                                                                                                                        if (var1_1 != null) break block87;
                                                                                                                                                        v0 = this;
                                                                                                                                                        if (var5_8) break block113;
                                                                                                                                                        break block114;
                                                                                                                                                        catch (IOException v1) {
                                                                                                                                                            throw v1;
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                    if (!v0.note.isEmpty()) break block88;
                                                                                                                                                    break block115;
                                                                                                                                                    catch (IOException v2) {
                                                                                                                                                        throw v2;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                v3 = ProfilesScreen.b[76];
                                                                                                                                                break block89;
                                                                                                                                            }
                                                                                                                                            v0 = this;
                                                                                                                                        }
                                                                                                                                        v3 = v0.note;
                                                                                                                                    }
                                                                                                                                    Ui.text(Im.sub, var6_5, var7_6 + 10.0f, Im.MUTE, v3);
                                                                                                                                    return;
                                                                                                                                }
                                                                                                                                var9_9 = var8_7;
                                                                                                                                var10_10 = var7_6 + 3.0f;
                                                                                                                                v5 = var1_1.name();
                                                                                                                                if (var5_8) break block90;
                                                                                                                                if (!v5.equals(ProfilesScreen.used)) break block91;
                                                                                                                                var16_11 = ProfilesScreen.b;
                                                                                                                                Ui.text(Im.sora13, var9_9 - Ui.width(Im.sora13, var16_11[6]), Ui.mid(Im.sora13, var10_10, 34.0f), Im.MUTE, var16_11[65]);
                                                                                                                                var9_9 -= Ui.width(Im.sora13, var16_11[65]) + 14.0f;
                                                                                                                                if (!var5_8) break block116;
                                                                                                                            }
                                                                                                                            var16_11 = ProfilesScreen.b;
                                                                                                                            v5 = var16_11[91];
                                                                                                                        }
                                                                                                                        var11_12 = Menu.buttonWidth(v5, null);
                                                                                                                        if (Menu.button(ProfilesScreen.b[4], var9_9 - var11_12, var10_10, var11_12, 34.0f, true)) {
                                                                                                                            this.use(var1_1);
                                                                                                                        }
                                                                                                                        var9_9 -= var11_12 + 14.0f;
                                                                                                                    }
                                                                                                                    v8 = Menu.ghost(ProfilesScreen.b[61], ProfilesScreen.b[44], var9_9, var10_10, 34.0f);
                                                                                                                    if (var5_8) break block92;
                                                                                                                    if (!v8) break block93;
                                                                                                                    break block117;
                                                                                                                    catch (IOException v9) {
                                                                                                                        throw v9;
                                                                                                                    }
                                                                                                                }
                                                                                                                this.ask(ProfilesScreen.b[23] + var1_1.name() + "?", var1_1.name() + ProfilesScreen.b[68], ProfilesScreen.b[69], true, (Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$detail$0(dev.quelchi.aurora.profile.Profile ), ()V)((ProfilesScreen)this, (Profile)var1_1));
                                                                                                            }
                                                                                                            v8 = ImGui.isItemHovered();
                                                                                                        }
                                                                                                        if (var5_8) break block94;
                                                                                                        if (!v8) break block95;
                                                                                                        break block118;
                                                                                                        catch (IOException v11) {
                                                                                                            throw v11;
                                                                                                        }
                                                                                                    }
                                                                                                    ImGui.setTooltip((String)ProfilesScreen.b[32]);
                                                                                                }
                                                                                                var16_11 = ProfilesScreen.b;
                                                                                                v8 = Menu.ghost(var16_11[89], var16_11[39], var9_9 -= Menu.ghostWidth("", var16_11[71]) + 4.0f, var10_10, 34.0f);
                                                                                            }
                                                                                            if (var5_8) break block96;
                                                                                            if (!v8) break block97;
                                                                                            break block119;
                                                                                            catch (IOException v13) {
                                                                                                throw v13;
                                                                                            }
                                                                                        }
                                                                                        this.ask(ProfilesScreen.b[52] + var1_1.name() + "?", ProfilesScreen.b[9] + var1_1.name() + ProfilesScreen.b[31], ProfilesScreen.b[34], true, (Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$detail$1(dev.quelchi.aurora.profile.Profile ), ()V)((ProfilesScreen)this, (Profile)var1_1));
                                                                                    }
                                                                                    v8 = ImGui.isItemHovered();
                                                                                }
                                                                                if (var5_8) break block98;
                                                                                if (!v8) break block99;
                                                                                break block120;
                                                                                catch (IOException v15) {
                                                                                    throw v15;
                                                                                }
                                                                            }
                                                                            ImGui.setTooltip((String)ProfilesScreen.b[3]);
                                                                        }
                                                                        v17 = var9_9;
                                                                        v18 = "";
                                                                        var16_11 = ProfilesScreen.b;
                                                                        v19 = var16_11[80];
                                                                        if (var5_8) break block121;
                                                                        var9_9 = v17 - (Menu.ghostWidth(v18, v19) + 4.0f);
                                                                        v8 = Menu.ghost(var16_11[67], var16_11[62], var9_9, var10_10, 34.0f);
                                                                    }
                                                                    if (!v8) break block100;
                                                                    this.target = var1_1;
                                                                    if (var5_8) break block101;
                                                                    break block122;
                                                                    catch (IOException v20) {
                                                                        throw v20;
                                                                    }
                                                                }
                                                                this.popX = var9_9 - 246.0f;
                                                                this.popY = var10_10 + 40.0f;
                                                                this.open = ProfilesScreen.b[40];
                                                                ProfileService.status = "";
                                                                if (!var1_1.code().isEmpty()) break block100;
                                                                break block123;
                                                                catch (IOException v21) {
                                                                    throw v21;
                                                                }
                                                            }
                                                            ProfileService.share(var1_1, (Consumer<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$detail$2(java.lang.String ), (Ljava/lang/String;)V)((ProfilesScreen)this));
                                                        }
                                                        v17 = var9_9;
                                                        if (var5_8) break block102;
                                                        v18 = ProfilesScreen.b[83];
                                                        v19 = ProfilesScreen.b[40];
                                                    }
                                                    var9_9 = v17 - (Menu.ghostWidth(v18, v19) + 12.0f);
                                                }
                                                if (!this.renaming) // goto
                                                v24 = this;
                                                if (var5_8) break block103;
                                                break block124;
                                                catch (IOException v25) {
                                                    throw v25;
                                                }
                                            }
                                            if (!v24.field(ProfilesScreen.b[12], this.name, var6_5, var10_10, Math.max(120.0f, var9_9 - var6_5))) break block104;
                                            try {
                                                block105: {
                                                    block125: {
                                                        var11_13 = ProfileManager.rename(var1_1, this.name.get().trim());
                                                        if (var5_8) break block104;
                                                        if (!var1_1.name().equals(ProfilesScreen.used)) break block105;
                                                        break block125;
                                                        catch (IOException v27) {
                                                            throw v27;
                                                        }
                                                    }
                                                    ProfilesScreen.used = var11_13.name();
                                                }
                                                this.picked = var11_13.name();
                                                this.renaming = false;
                                                this.note = "";
                                                this.reload();
                                                break block104;
                                            }
                                            catch (IOException var11_14) {
                                                this.note = var11_14.getMessage();
                                                if (!var5_8) break block104;
lbl198:

                                                v17 = Menu.ghostWidth("", ProfilesScreen.b[78]);
                                            }
                                        }
                                        var11_12 = v17;
                                        var12_16 = ProfilesScreen.fit(Im.head, var1_1.name(), var9_9 - var6_5 - var11_12 - 4.0f);
                                        var13_17 = Ui.width(Im.head, var12_16);
                                        ImGui.setCursorScreenPos((float)var6_5, (float)var7_6);
                                        v30 = ImGui.invisibleButton((String)ProfilesScreen.b[94], (float)Math.max(1.0f, var13_17), (float)40.0f);
                                        if (var5_8) break block106;
                                        if (ImGui.isItemHovered()) {
                                        }
                                        v32 = 0;
                                        if (var5_8) break block107;
                                        v30 = ImGui.isMouseDoubleClicked((int)v32);
                                    }
                                    if (v30) {
                                        v32 = 1;
                                    } else lbl_neg_1000:

                                    {
                                        v32 = 0;
                                    }
                                }
                                var14_19 = v32;
                                Ui.text(Im.head, var6_5, Ui.mid(Im.head, var7_6, 40.0f), Im.INK, var12_16);
                                v35 = ImGui.isWindowHovered();
                                if (var5_8) break block108;
                                if (v35 == 0) break block109;
                                v35 = (int)ImGui.isMouseHoveringRect((float)var6_5, (float)var7_6, (float)(var6_5 + var13_17 + 4.0f + var11_12), (float)(var7_6 + 40.0f));
                                if (var5_8) break block108;
                                if (v35 == 0) break block109;
                                v35 = var14_19 | Menu.ghost(ProfilesScreen.b[64], ProfilesScreen.b[35], var6_5 + var13_17 + 4.0f + var11_12, var7_6 + 3.0f, 34.0f);
                                if (var5_8) break block108;
                                var14_19 = v35;
                                if (ImGui.isItemHovered()) {
                                    ImGui.setTooltip((String)ProfilesScreen.b[79]);
                                }
                            }
                            v35 = var14_19;
                        }
                        if (var5_8) break block110;
                        if (v35 == 0) break block104;
                        this.renaming = true;
                        this.focus = true;
                        this.name.set(var1_1.name());
                    }
                    v43 = var7_6;
                    v44 = 62.0f;
                    if (var5_8) break block126;
                    var7_6 = v43 + v44;
                    Ui.text(Im.sora13, var6_5, Ui.mid(Im.sora13, var7_6, 16.0f), Im.INK, ProfilesScreen.b[104]);
                    v24 = this;
                }
                v35 = v24.note.isEmpty();
            }
            if (v35 == 0) {
                Ui.text(Im.sub, var8_7 - Ui.width(Im.sub, this.note), Ui.mid(Im.sub, var7_6, 16.0f), Im.WARN, this.note);
            }
            v43 = var7_6;
            v44 = 26.0f;
        }
        var7_6 = v43 + v44;
        var11_15 = var6_5;
        var12_16 = ImGui.getWindowDrawList();
        for (String var14_20 : ProfilesScreen.enabled(var1_1.data())) {
            block111: {
                block112: {
                    var15_21 = 33.0f + Ui.width(Im.sora12, var14_20) + 12.0f;
                    v46 = var11_15;
                    if (var5_8) break block111;
                    if (!(v46 > var6_5)) break block112;
                    v46 = var11_15 + var15_21;
                    if (var5_8) break block111;
                    if (!(v46 > var8_7)) break block112;
                    var11_15 = var6_5;
                    var7_6 += 42.0f;
                }
                Ui.box((ImDrawList)var12_16, var11_15, var7_6, var11_15 + var15_21, var7_6 + 34.0f, Im.SURFACE, Im.SURFACE_LINE, 9.0f, 1.0f);
                Ui.icon(ProfilesScreen.icon(var14_20), var11_15 + 10.0f, var7_6 + 9.5f, 15.0f, Ui.soft());
                Ui.text(Im.sora12, var11_15 + 33.0f, Ui.mid(Im.sora12, var7_6, 34.0f), Im.INK, var14_20);
                v46 = var11_15 = var11_15 + (var15_21 + 8.0f);
            }
            if (!var5_8) continue;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static List<String> enabled(JsonObject jsonObject) {
        JsonObject jsonObject2;
        boolean bl;
        ArrayList<String> arrayList;
        block28: {
            String string;
            block27: {
                block26: {
                    arrayList = new ArrayList<String>();
                    bl = ImScreen.a;
                    jsonObject2 = jsonObject;
                    if (bl) break block26;
                    if (jsonObject2 == null) return arrayList;
                    jsonObject2 = jsonObject;
                }
                string = b[86];
                if (bl) break block27;
                if (!jsonObject2.has(string)) return arrayList;
                jsonObject2 = jsonObject;
                if (bl) break block28;
                string = b[93];
            }
            if (!jsonObject2.get(string).isJsonObject()) {
                return arrayList;
            }
            String[] stringArray = b;
            jsonObject2 = jsonObject.getAsJsonObject(stringArray[93]);
        }
        JsonObject jsonObject3 = jsonObject2;
        for (String string : jsonObject3.keySet()) {
            block32: {
                String string2;
                JsonObject jsonObject4;
                block33: {
                    JsonObject jsonObject5;
                    block31: {
                        JsonObject jsonObject6;
                        block29: {
                            block30: {
                                jsonObject6 = jsonObject3;
                                if (bl) break block29;
                                if (!jsonObject6.get(string).isJsonObject()) break block30;
                                jsonObject6 = jsonObject3.getAsJsonObject(string);
                                break block29;
                            }
                            jsonObject6 = null;
                        }
                        jsonObject5 = jsonObject6;
                        jsonObject4 = jsonObject5;
                        if (bl) break block31;
                        if (jsonObject4 == null) break block32;
                        jsonObject4 = jsonObject5;
                    }
                    string2 = b[28];
                    if (bl) break block33;
                    if (!jsonObject4.has(string2)) break block32;
                    jsonObject4 = jsonObject5;
                    string2 = b[41];
                }
                boolean bl2 = jsonObject4.get(string2).getAsBoolean();
                if (bl || !bl2) break block32;
                bl2 = arrayList.add(string);
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    private static String icon(String string) {
        Object object;
        block4: {
            String string2;
            block5: {
                String[] stringArray = b;
                string2 = stringArray[97] + string.toLowerCase(Locale.ROOT).replace(' ', '_');
                boolean bl = ImScreen.a;
                object = string2;
                if (bl) break block4;
                if (VectorIcons.texture((String)object, 30) != null) break block5;
                object = b[82];
                break block4;
            }
            object = string2;
        }
        return object;
    }

    private static String uses(int n) {
        block5: {
            int n2;
            block4: {
                boolean bl = ImScreen.a;
                n2 = n;
                if (bl) break block4;
                if (n2 >= 1000) break block5;
                n2 = n;
            }
            String[] stringArray = b;
            return n2 + stringArray[75];
        }
        String[] stringArray = b;
        return String.format(Locale.ROOT, stringArray[70], Float.valueOf((float)n / 1000.0f)).replace(stringArray[0], "k");
    }

    private boolean saved(ProfileService$Listed profileService$Listed) {
        boolean bl;
        block5: {
            Iterator<Profile> iterator = this.mine.iterator();
            boolean bl2 = ImScreen.a;
            while (iterator.hasNext()) {
                block7: {
                    boolean bl3;
                    block6: {
                        Profile profile = iterator.next();
                        bl = profileService$Listed.code().equals(profile.from());
                        if (bl2) break block5;
                        if (bl2) break block6;
                        if (!bl) break block7;
                        bl3 = true;
                    }
                    return bl3;
                }
                if (!bl2) continue;
            }
            bl = false;
        }
        return bl;
    }

    private void keep(ProfileService$Listed profileService$Listed) {
        if (!this.saving.add(profileService$Listed.code())) {
            return;
        }
        ProfileService.fetch(profileService$Listed.code(), arg_0 -> this.lambda$keep$3(profileService$Listed, arg_0));
    }

    private static void head(String string, float f, float f2, float f3) {
        try {
            Ui.head(UUID.fromString(string), f, f2, f3);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            Ui.fill(ImGui.getWindowDrawList(), f, f2, f + f3, f2 + f3, Ui.wash(0.16f), f3 / 4.0f);
        }
    }

    
    private void community(float f, float f2, float f3, float f4) {
        float f5;
        float f6;
        block48: {
            float f7;
            float f8;
            block50: {
                ArrayList<ProfileService$Listed> arrayList;
                String[] stringArray;
                ImDrawList imDrawList;
                boolean bl;
                float f9;
                block51: {
                    block52: {
                        ProfilesScreen profilesScreen;
                        block47: {
                            boolean bl2;
                            block49: {
                                boolean bl3;
                                ArrayList arrayList2;
                                block46: {
                                    float f10;
                                    float f11;
                                    f8 = f + 28.0f;
                                    f9 = f3 - 56.0f;
                                    f7 = f2 + 22.0f;
                                    bl = ImScreen.a;
                                    imDrawList = ImGui.getWindowDrawList();
                                    String string = SORT_NAMES[this.sort];
                                    float f12 = 12.0f + Ui.width(Im.bold13, string) + 8.0f + 12.0f + 10.0f;
                                    this.typeToSearch();
                                    stringArray = b;
                                    Menu.search(stringArray[73], stringArray[48], this.search, f8, f7, f9 - f12 - 10.0f);
                                    float f13 = f11 = f8 + f9 - f12;
                                    if (!bl) {
                                        ImGui.setCursorScreenPos((float)f13, (float)f7);
                                        if (ImGui.invisibleButton((String)stringArray[36], (float)f12, (float)34.0f)) {
                                            this.popX = f11 + f12 - 150.0f;
                                            this.popY = f7 + 40.0f;
                                            this.open = b[56];
                                        }
                                        f13 = f10 = Ui.hot();
                                    }
                                    if (!bl) {
                                        Ui.box(imDrawList, f11, f7, f11 + f12, f7 + 34.0f, null, Im.FIELD_LINE, 8.0f, 1.0f);
                                        if (f10 > 0.01f) {
                                            imDrawList.addRectFilled(f11, f7, f11 + f12, f7 + 34.0f, Ui.wash(0.04f * f10), 8.0f);
                                        }
                                        Ui.text(Im.bold13, f11 + 12.0f, Ui.mid(Im.bold13, f7, 34.0f), Im.SUB, string);
                                        stringArray = b;
                                        Ui.icon(stringArray[46], f11 + f12 - 22.0f, f7 + 11.0f, 12.0f, Im.SUB);
                                    }
                                    String string2 = this.search.get().trim().toLowerCase(Locale.ROOT);
                                    String string3 = SORT[this.sort] + "|" + string2;
                                    Object object = string3;
                                    if (!bl) {
                                        if (!((String)object).equals(this.queried)) {
                                            this.queried = string3;
                                            ProfileService.publicList(SORT[this.sort], string2, arg_0 -> this.lambda$community$4(string3, arg_0));
                                        }
                                        f7 += 56.0f;
                                        stringArray = b;
                                        object = stringArray[27];
                                    }
                                    ProfilesScreen.child((String)object, f + 1.0f, f7, f3 - 2.0f, f2 + f4 - f7 - 1.0f);
                                    f7 = ImGui.getCursorScreenPosY();
                                    imDrawList = ImGui.getWindowDrawList();
                                    if (this.shared.isEmpty()) {
                                        String string4 = ProfileService.status;
                                        if (!bl) {
                                            string4 = string4.isEmpty() ? b[38] : ProfileService.status;
                                        }
                                        Ui.text(Im.sub, f8, f7, Im.MUTE, string4);
                                        ProfilesScreen.endChild(f8, f7 + 20.0f);
                                        return;
                                    }
                                    arrayList2 = new ArrayList();
                                    arrayList = new ArrayList<ProfileService$Listed>();
                                    for (Object object2 : this.shared) {
                                        bl3 = ((ProfileService$Listed)object2).media();
                                        if (!bl) {
                                            (bl3 ? arrayList2 : arrayList).add(object2);
                                            if (!bl) continue;
                                        }
                                        break block46;
                                    }
                                    bl3 = false;
                                }
                                boolean bl4 = bl3;
                                ArrayList arrayList3 = arrayList2;
                                if (!bl) {
                                    if (!arrayList3.isEmpty()) {
                                        stringArray = b;
                                        Ui.text(Im.sora13, f8, Ui.mid(Im.sora13, f7, 16.0f), Im.INK, stringArray[55]);
                                        bl4 = this.noteAt(f8 + f9, f7);
                                        f7 += 26.0f;
                                        int n = Math.max(1, (int)((f9 + 14.0f) / 254.0f));
                                        float f14 = (f9 - (float)((n - 1) * 14)) / (float)n;
                                        for (int i = 0; i < arrayList2.size(); ++i) {
                                            profilesScreen = this;
                                            if (!bl) {
                                                profilesScreen.card((ProfileService$Listed)arrayList2.get(i), f8 + (float)(i % n) * (f14 + 14.0f), f7 + (float)(i / n) * 114.0f, f14);
                                                if (!bl) continue;
                                            }
                                            break block47;
                                        }
                                        f7 += (float)((arrayList2.size() + n - 1) / n) * 114.0f + 10.0f;
                                    }
                                    arrayList3 = arrayList;
                                }
                                bl2 = arrayList3.isEmpty();
                                if (bl) break block49;
                                if (bl2) break block50;
                                Ui.text(Im.sora13, f8, Ui.mid(Im.sora13, f7, 16.0f), Im.INK, b[60]);
                                if (bl) break block51;
                                bl2 = bl4;
                            }
                            if (bl2) break block52;
                            profilesScreen = this;
                        }
                        profilesScreen.noteAt(f8 + f9, f7);
                    }
                    f7 += 20.0f;
                }
                for (ProfileService$Listed profileService$Listed : arrayList) {
                    String string;
                    float f15;
                    block56: {
                        block55: {
                            boolean bl5;
                            block53: {
                                block54: {
                                    imDrawList.addLine(f8, f7 + 50.0f - 0.5f, f8 + f9, f7 + 50.0f - 0.5f, Ui.col(Im.LINE));
                                    ProfilesScreen.head(profileService$Listed.owner(), f8, f7 + 12.0f, 26.0f);
                                    f6 = f8;
                                    f5 = f9;
                                    if (bl) break block48;
                                    f15 = f6 + f5;
                                    bl5 = this.saved(profileService$Listed);
                                    if (bl) break block53;
                                    if (!bl5) break block54;
                                    stringArray = b;
                                    float f16 = 19.0f + Ui.width(Im.sora12r, stringArray[84]);
                                    float f17 = f15 - 38.0f - f16 / 2.0f;
                                    Ui.icon(stringArray[66], f17, f7 + 18.5f, 13.0f, Im.MUTE);
                                    Ui.text(Im.sora12r, f17 + 19.0f, Ui.mid(Im.sora12r, f7, 50.0f), Im.MUTE, stringArray[84]);
                                    if (!bl) break block55;
                                }
                                string = profileService$Listed.code();
                                if (bl) break block56;
                                bl5 = Menu.button(b[14] + string, f15 - 76.0f, f7 + 10.0f, 76.0f, 30.0f, false);
                            }
                            if (bl5) {
                                this.keep(profileService$Listed);
                            }
                        }
                        string = ProfilesScreen.uses(profileService$Listed.loads());
                    }
                    String string5 = string;
                    Ui.text(Im.sub, f15 - 88.0f - Ui.width(Im.sub, string5), Ui.mid(Im.sub, f7, 50.0f), Im.MUTE, string5);
                    Ui.text(Im.sora14, f8 + 38.0f, Ui.mid(Im.sora14, f7, 50.0f), Im.INK, ProfilesScreen.fit(Im.sora14, profileService$Listed.name(), f9 - 38.0f - 180.0f));
                    f7 += 50.0f;
                    if (!bl) continue;
                }
            }
            f6 = f8;
            f5 = f7 + 20.0f;
        }
        ProfilesScreen.endChild(f6, f5);
    }

    private boolean noteAt(float f, float f2) {
        boolean bl;
        block4: {
            block5: {
                boolean bl2 = ImScreen.a;
                bl = this.note.isEmpty();
                if (bl2) break block4;
                if (!bl) break block5;
                return false;
            }
            Ui.text(Im.sub, f - Ui.width(Im.sub, this.note), Ui.mid(Im.sub, f2, 16.0f), Im.WARN, this.note);
            bl = true;
        }
        return bl;
    }

    
    private void card(ProfileService$Listed profileService$Listed, float f, float f2, float f3) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        Ui.box(imDrawList, f, f2, f + f3, f2 + 100.0f, Im.SURFACE, null, 14.0f, 0.0f);
        float[] fArray = Menu.ACCENT;
        boolean bl = ImScreen.a;
        Ui.glow(imDrawList, f + f3, f2, f3 * 0.9f, 80.0f, new float[]{0.0f, 0.0f, 0.0f, 0.0f, fArray[0], fArray[1], fArray[2], 0.16f, fArray[0], fArray[1], fArray[2], 0.06f}, f, f2, f3, 100.0f, 14.0f);
        Ui.box(imDrawList, f, f2, f + f3, f2 + 100.0f, null, Im.SURFACE_LINE, 14.0f, 1.0f);
        ProfilesScreen.head(profileService$Listed.owner(), f + 16.0f, f2 + 16.0f, 22.0f);
        Ui.text(Im.sora16, f + 46.0f, Ui.mid(Im.sora16, f2 + 16.0f, 22.0f), Im.INK, ProfilesScreen.fit(Im.sora16, profileService$Listed.name(), f3 - 62.0f));
        String string = ProfilesScreen.uses(profileService$Listed.loads());
        float f4 = Ui.width(Im.sub, string);
        float f5 = f3 - 32.0f - f4 - 12.0f;
        float f6 = f2 + 52.0f;
        Ui.text(Im.sub, f + f3 - 16.0f - f4, Ui.mid(Im.sub, f6, 32.0f), Im.MUTE, string);
        boolean bl2 = this.saved(profileService$Listed);
        if (!bl) {
            if (bl2) {
                String[] stringArray = b;
                float f7 = 19.0f + Ui.width(Im.sora12r, stringArray[13]);
                float f8 = f + 16.0f + (f5 - f7) / 2.0f;
                Ui.icon(stringArray[92], f8, f6 + 9.5f, 13.0f, Im.MUTE);
                Ui.text(Im.sora12r, f8 + 19.0f, Ui.mid(Im.sora12r, f6, 32.0f), Im.MUTE, stringArray[84]);
                if (!bl) return;
            }
            bl2 = Menu.button(b[43] + profileService$Listed.code(), f + 16.0f, f6, f5, 32.0f, true);
        }
        if (!bl2) return;
        this.keep(profileService$Listed);
    }

    private Profile current() {
        ProfilesScreen profilesScreen;
        boolean bl;
        block9: {
            block10: {
                bl = ImScreen.a;
                profilesScreen = this;
                if (bl) break block9;
                if (profilesScreen.target != null) break block10;
                return null;
            }
            profilesScreen = this;
        }
        for (Profile profile : profilesScreen.mine) {
            block12: {
                Profile profile2;
                block11: {
                    profile2 = profile;
                    if (bl) break block11;
                    if (!profile2.name().equals(this.target.name())) break block12;
                    profile2 = profile;
                }
                return profile2;
            }
            if (!bl) continue;
        }
        return null;
    }

    private static void tiles(float f, float f2, String string, float f3, float f4, boolean n) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        boolean bl = ImScreen.a;
        int n2 = Ui.col(Ui.mix(Ui.mix(new float[]{1.0f, 1.0f, 1.0f, 0.16f}, Im.GREEN, f4), RED, f3));
        for (int i = 0; i < 6; ++i) {
            int n3;
            float f5;
            block32: {
                block33: {
                    int n4;
                    block31: {
                        float f6;
                        float f7;
                        float f8;
                        float f9;
                        ImDrawList imDrawList2;
                        block29: {
                            block30: {
                                int n5;
                                block28: {
                                    block27: {
                                        block26: {
                                            int n6;
                                            float f10;
                                            block24: {
                                                block25: {
                                                    f10 = f;
                                                    n6 = i * 36;
                                                    if (bl) break block24;
                                                    f10 = f10 + (float)n6;
                                                    if (i <= 2) break block25;
                                                    n6 = 8;
                                                    break block24;
                                                }
                                                n6 = 0;
                                            }
                                            f5 = f10 + (float)n6;
                                            n5 = n;
                                            if (bl) break block26;
                                            if (n5 == 0) break block27;
                                            n5 = i;
                                        }
                                        if (bl) break block28;
                                        if (n5 != string.length()) break block27;
                                        n5 = 1;
                                        break block28;
                                    }
                                    n5 = 0;
                                }
                                n4 = n5;
                                imDrawList2 = imDrawList;
                                f9 = f5;
                                f8 = f2;
                                f7 = f5 + 30.0f;
                                f6 = f2;
                                if (bl) break block29;
                                Ui.fill(imDrawList2, f9, f8, f7, f6 + 38.0f, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)0.28f), 8.0f);
                                if (n4 == 0) break block30;
                                Ui.rim(f5, f2, 30.0f, 38.0f, 8.0f, Menu.ICON_TOP, Menu.ICON_FOOT);
                                if (!bl) break block31;
                            }
                            imDrawList2 = imDrawList;
                            f9 = f5;
                            f8 = f2;
                            f7 = f5 + 30.0f;
                            f6 = f2 + 38.0f;
                        }
                        Ui.rect(imDrawList2, f9, f8, f7, f6, n2, 8.0f);
                    }
                    n3 = n4;
                    if (bl) break block32;
                    if (n3 == 0) break block33;
                    imDrawList.addLine(f5 + 15.0f, f2 + 11.0f, f5 + 15.0f, f2 + 27.0f, Ui.col(Im.INK));
                }
                n3 = i;
            }
            if (n3 >= string.length()) {
                continue;
            }
            String string2 = string.substring(i, i + 1);
            Ui.text(Im.sora13, f5 + (float)Math.floor((30.0f - Ui.width(Im.sora13, string2)) / 2.0f), Ui.mid(Im.sora13, f2, 38.0f), Ui.mix(Im.INK, RED, f3), string2);
            if (!bl) continue;
        }
    }

    
    private void popups() {
        block180: {
            block181: {
                block188: {
                    block189: {
                        block190: {
                            block187: {
                                block186: {
                                    block184: {
                                        block185: {
                                            block182: {
                                                block183: {
                                                    block160: {
                                                        block161: {
                                                            block176: {
                                                                block177: {
                                                                    block217: {
                                                                        block216: {
                                                                            block215: {
                                                                                block174: {
                                                                                    block175: {
                                                                                        block172: {
                                                                                            block173: {
                                                                                                block170: {
                                                                                                    block171: {
                                                                                                        block214: {
                                                                                                            block213: {
                                                                                                                block169: {
                                                                                                                    block168: {
                                                                                                                        block167: {
                                                                                                                            block212: {
                                                                                                                                block165: {
                                                                                                                                    block166: {
                                                                                                                                        block163: {
                                                                                                                                            block164: {
                                                                                                                                                block211: {
                                                                                                                                                    block162: {
                                                                                                                                                        block210: {
                                                                                                                                                            block147: {
                                                                                                                                                                block202: {
                                                                                                                                                                    block159: {
                                                                                                                                                                        block158: {
                                                                                                                                                                            block157: {
                                                                                                                                                                                block149: {
                                                                                                                                                                                    block156: {
                                                                                                                                                                                        block150: {
                                                                                                                                                                                            block151: {
                                                                                                                                                                                                block209: {
                                                                                                                                                                                                    block154: {
                                                                                                                                                                                                        block155: {
                                                                                                                                                                                                            block208: {
                                                                                                                                                                                                                block153: {
                                                                                                                                                                                                                    block152: {
                                                                                                                                                                                                                        block207: {
                                                                                                                                                                                                                            block206: {
                                                                                                                                                                                                                                block205: {
                                                                                                                                                                                                                                    block204: {
                                                                                                                                                                                                                                        block148: {
                                                                                                                                                                                                                                            block203: {
                                                                                                                                                                                                                                                block144: {
                                                                                                                                                                                                                                                    block140: {
                                                                                                                                                                                                                                                        block141: {
                                                                                                                                                                                                                                                            block142: {
                                                                                                                                                                                                                                                                block143: {
                                                                                                                                                                                                                                                                    block199: {
                                                                                                                                                                                                                                                                        block198: {
                                                                                                                                                                                                                                                                            block197: {
                                                                                                                                                                                                                                                                                block137: {
                                                                                                                                                                                                                                                                                    block139: {
                                                                                                                                                                                                                                                                                        block138: {
                                                                                                                                                                                                                                                                                            block196: {
                                                                                                                                                                                                                                                                                                block195: {
                                                                                                                                                                                                                                                                                                    block194: {
                                                                                                                                                                                                                                                                                                        block193: {
                                                                                                                                                                                                                                                                                                            block192: {
                                                                                                                                                                                                                                                                                                                block191: {
                                                                                                                                                                                                                                                                                                                    var1_1 = ImScreen.a;
                                                                                                                                                                                                                                                                                                                    v0 = this;
                                                                                                                                                                                                                                                                                                                    v1 = ImGui.isPopupOpen((String)ProfilesScreen.b[56]);
                                                                                                                                                                                                                                                                                                                    if (var1_1) break block137;
                                                                                                                                                                                                                                                                                                                    if (v1) break block138;
                                                                                                                                                                                                                                                                                                                    break block191;
                                                                                                                                                                                                                                                                                                                    catch (IOException v2) {
                                                                                                                                                                                                                                                                                                                        throw v2;
                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                v1 = ImGui.isPopupOpen((String)ProfilesScreen.b[40]);
                                                                                                                                                                                                                                                                                                                if (var1_1) break block137;
                                                                                                                                                                                                                                                                                                                break block192;
                                                                                                                                                                                                                                                                                                                catch (IOException v3) {
                                                                                                                                                                                                                                                                                                                    throw v3;
                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                            if (v1) break block138;
                                                                                                                                                                                                                                                                                                            break block193;
                                                                                                                                                                                                                                                                                                            catch (IOException v4) {
                                                                                                                                                                                                                                                                                                                throw v4;
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                        v1 = ImGui.isPopupOpen((String)ProfilesScreen.b[90]);
                                                                                                                                                                                                                                                                                                        if (var1_1) break block137;
                                                                                                                                                                                                                                                                                                        break block194;
                                                                                                                                                                                                                                                                                                        catch (IOException v5) {
                                                                                                                                                                                                                                                                                                            throw v5;
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                    if (v1) break block138;
                                                                                                                                                                                                                                                                                                    break block195;
                                                                                                                                                                                                                                                                                                    catch (IOException v6) {
                                                                                                                                                                                                                                                                                                        throw v6;
                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                v1 = ImGui.isPopupOpen((String)ProfilesScreen.b[1]);
                                                                                                                                                                                                                                                                                                if (var1_1) break block137;
                                                                                                                                                                                                                                                                                                break block196;
                                                                                                                                                                                                                                                                                                catch (IOException v7) {
                                                                                                                                                                                                                                                                                                    throw v7;
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                            if (!v1) break block139;
                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                        v1 = true;
                                                                                                                                                                                                                                                                                        break block137;
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                    v1 = false;
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                v0.up = v1;
                                                                                                                                                                                                                                                                                ImGui.pushStyleVar((int)9, (float)9.0f);
                                                                                                                                                                                                                                                                                ImGui.pushStyleVar((int)14, (float)0.0f, (float)2.0f);
                                                                                                                                                                                                                                                                                ImGui.pushStyleColor((int)4, (float)Im.POP[0], (float)Im.POP[1], (float)Im.POP[2], (float)Im.POP[3]);
                                                                                                                                                                                                                                                                                v9 = this.popX;
                                                                                                                                                                                                                                                                                v10 = this.popY;
                                                                                                                                                                                                                                                                                v11 = Ui.drop(ProfilesScreen.b[56]);
                                                                                                                                                                                                                                                                                if (var1_1) break block140;
                                                                                                                                                                                                                                                                                ImGui.setNextWindowPos((float)v9, (float)(v10 + v11));
                                                                                                                                                                                                                                                                                ImGui.setNextWindowSize((float)150.0f, (float)0.0f);
                                                                                                                                                                                                                                                                                ImGui.pushStyleVar((int)2, (float)5.0f, (float)5.0f);
                                                                                                                                                                                                                                                                                ImGui.pushStyleVar((int)11, (float)9.0f, (float)7.0f);
                                                                                                                                                                                                                                                                                if (!ImGui.beginPopup((String)ProfilesScreen.b[56])) break block141;
                                                                                                                                                                                                                                                                                break block197;
                                                                                                                                                                                                                                                                                catch (IOException v12) {
                                                                                                                                                                                                                                                                                    throw v12;
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                            Ui.lift();
                                                                                                                                                                                                                                                                            if (var1_1) break block142;
                                                                                                                                                                                                                                                                            break block198;
                                                                                                                                                                                                                                                                            catch (IOException v13) {
                                                                                                                                                                                                                                                                                throw v13;
                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                        if (!this.esc) break block143;
                                                                                                                                                                                                                                                                        break block199;
                                                                                                                                                                                                                                                                        catch (IOException v14) {
                                                                                                                                                                                                                                                                            throw v14;
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                    ImGui.closeCurrentPopup();
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                ImGui.pushFont((ImFont)Im.sora12r);
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                            for (var2_2 = 0; var2_2 < ProfilesScreen.SORT.length; ++var2_2) {
                                                                                                                                                                                                                                                                block145: {
                                                                                                                                                                                                                                                                    block146: {
                                                                                                                                                                                                                                                                        block201: {
                                                                                                                                                                                                                                                                            block200: {
                                                                                                                                                                                                                                                                                v16 = ProfilesScreen.SORT_NAMES[var2_2];
                                                                                                                                                                                                                                                                                if (var1_1) break block144;
                                                                                                                                                                                                                                                                                v17 = v16 + ProfilesScreen.b[88];
                                                                                                                                                                                                                                                                                v18 = this.sort;
                                                                                                                                                                                                                                                                                if (var1_1) break block145;
                                                                                                                                                                                                                                                                                break block200;
                                                                                                                                                                                                                                                                                catch (IOException v19) {
                                                                                                                                                                                                                                                                                    throw v19;
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                            if (v18 != var2_2) break block146;
                                                                                                                                                                                                                                                                            break block201;
                                                                                                                                                                                                                                                                            catch (IOException v20) {
                                                                                                                                                                                                                                                                                throw v20;
                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                        v18 = 1;
                                                                                                                                                                                                                                                                        break block145;
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                    v18 = 0;
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                if (!Ui.option(v17, (boolean)v18)) continue;
                                                                                                                                                                                                                                                                this.sort = var2_2;
                                                                                                                                                                                                                                                                continue;
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                            ImGui.popFont();
                                                                                                                                                                                                                                                            ImGui.endPopup();
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                        ImGui.popStyleVar((int)3);
                                                                                                                                                                                                                                                        if (var1_1) break block147;
                                                                                                                                                                                                                                                        ImGui.pushStyleVar((int)2, (float)12.0f, (float)12.0f);
                                                                                                                                                                                                                                                        v9 = this.popX;
                                                                                                                                                                                                                                                        v10 = this.popY;
                                                                                                                                                                                                                                                        v11 = Ui.drop(ProfilesScreen.b[40]);
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                    ImGui.setNextWindowPos((float)v9, (float)(v10 + v11));
                                                                                                                                                                                                                                                    ImGui.setNextWindowSize((float)246.0f, (float)0.0f);
                                                                                                                                                                                                                                                    var12_6 = ProfilesScreen.b;
                                                                                                                                                                                                                                                    v16 = var12_6[40];
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                if (!ImGui.beginPopup((String)v16)) break block202;
                                                                                                                                                                                                                                                Ui.lift();
                                                                                                                                                                                                                                                var2_3 = this.current();
                                                                                                                                                                                                                                                if (this.esc) // goto
                                                                                                                                                                                                                                                if (var2_3 != null) break block148;
                                                                                                                                                                                                                                                break block203;
                                                                                                                                                                                                                                                catch (IOException v24) {
                                                                                                                                                                                                                                                    throw v24;
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                            ImGui.closeCurrentPopup();
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                        var3_7 = ImGui.getCursorScreenPosX();
                                                                                                                                                                                                                                        var4_8 = ImGui.getCursorScreenPosY();
                                                                                                                                                                                                                                        if (var1_1) break block149;
                                                                                                                                                                                                                                        if (var2_3 == null) break block150;
                                                                                                                                                                                                                                        break block204;
                                                                                                                                                                                                                                        catch (IOException v26) {
                                                                                                                                                                                                                                            throw v26;
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                    Ui.text(Im.sora13, var3_7, var4_8, Im.INK, ProfilesScreen.fit(Im.sora13, ProfilesScreen.b[81] + var2_3.name(), 222.0f));
                                                                                                                                                                                                                                    if (var1_1) break block151;
                                                                                                                                                                                                                                    break block205;
                                                                                                                                                                                                                                    catch (IOException v27) {
                                                                                                                                                                                                                                        throw v27;
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                Ui.text(Im.label, var3_7, var4_8 + 22.0f, Im.MUTE, ProfilesScreen.b[59]);
                                                                                                                                                                                                                                Ui.text(Im.label, var3_7, var4_8 + 37.0f, Im.MUTE, ProfilesScreen.b[95]);
                                                                                                                                                                                                                                if (!var2_3.code().isEmpty()) // goto
                                                                                                                                                                                                                                break block206;
                                                                                                                                                                                                                                catch (IOException v28) {
                                                                                                                                                                                                                                    throw v28;
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                            v29 = Im.label;
                                                                                                                                                                                                                            v30 = var3_7;
                                                                                                                                                                                                                            v31 = var4_8 + 70.0f;
                                                                                                                                                                                                                            if (!ProfileService.status.isEmpty()) break block152;
                                                                                                                                                                                                                            break block207;
                                                                                                                                                                                                                            catch (IOException v32) {
                                                                                                                                                                                                                                throw v32;
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                        v33 = Im.MUTE;
                                                                                                                                                                                                                        break block153;
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    v33 = Im.WARN;
                                                                                                                                                                                                                }
                                                                                                                                                                                                                v35 = ProfileService.status;
                                                                                                                                                                                                                if (var1_1) break block154;
                                                                                                                                                                                                                if (!v35.isEmpty()) break block155;
                                                                                                                                                                                                                break block208;
                                                                                                                                                                                                                catch (IOException v36) {
                                                                                                                                                                                                                    throw v36;
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                            v35 = ProfilesScreen.b[87];
                                                                                                                                                                                                            break block154;
                                                                                                                                                                                                        }
                                                                                                                                                                                                        v35 = ProfileService.status;
                                                                                                                                                                                                    }
                                                                                                                                                                                                    Ui.text(v29, v30, v31, v33, v35);
                                                                                                                                                                                                    if (!var1_1) break block150;
lbl221:

                                                                                                                                                                                                    v38 = var3_7;
                                                                                                                                                                                                    v39 = var4_8 + 62.0f;
                                                                                                                                                                                                    if (var1_1) break block156;
                                                                                                                                                                                                    break block209;
                                                                                                                                                                                                    catch (IOException v40) {
                                                                                                                                                                                                        throw v40;
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                                ProfilesScreen.tiles(v38, v39, var2_3.code(), 0.0f, 0.0f, false);
                                                                                                                                                                                            }
                                                                                                                                                                                            if (Menu.button(ProfilesScreen.b[77], var3_7, var4_8 + 110.0f, 222.0f, 32.0f, true)) {
                                                                                                                                                                                                MinecraftClient.method_1551().field_1774.method_1455(var2_3.code());
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                        v38 = var3_7;
                                                                                                                                                                                        v39 = var4_8;
                                                                                                                                                                                    }
                                                                                                                                                                                    ImGui.setCursorScreenPos((float)v38, (float)v39);
                                                                                                                                                                                }
                                                                                                                                                                                v43 = 222.0f;
                                                                                                                                                                                v44 = var2_3;
                                                                                                                                                                                if (var1_1) break block157;
                                                                                                                                                                                if (v44 == null) break block158;
                                                                                                                                                                                v44 = var2_3;
                                                                                                                                                                            }
                                                                                                                                                                            if (v44.code().isEmpty()) break block158;
                                                                                                                                                                            v46 = 142.0f;
                                                                                                                                                                            break block159;
                                                                                                                                                                        }
                                                                                                                                                                        v46 = 90.0f;
                                                                                                                                                                    }
                                                                                                                                                                    ImGui.dummy((float)v43, (float)v46);
                                                                                                                                                                    ImGui.endPopup();
                                                                                                                                                                }
                                                                                                                                                                ImGui.popStyleVar();
                                                                                                                                                                v48 = this.popX;
                                                                                                                                                                if (var1_1) break block160;
                                                                                                                                                                ImGui.setNextWindowPos((float)v48, (float)(this.popY + Ui.drop(ProfilesScreen.b[90])));
                                                                                                                                                                ImGui.setNextWindowSize((float)246.0f, (float)0.0f);
                                                                                                                                                            }
                                                                                                                                                            if (!ImGui.beginPopup((String)ProfilesScreen.b[90])) break block161;
                                                                                                                                                            Ui.lift();
                                                                                                                                                            if (!this.esc) break block162;
                                                                                                                                                            break block210;
                                                                                                                                                            catch (IOException v50) {
                                                                                                                                                                throw v50;
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                        ImGui.closeCurrentPopup();
                                                                                                                                                    }
                                                                                                                                                    var2_4 = ImGui.getCursorScreenPosX();
                                                                                                                                                    var3_7 = ImGui.getCursorScreenPosY();
                                                                                                                                                    if (var1_1) break block163;
                                                                                                                                                    Ui.text(Im.sora13, var2_4, var3_7, Im.INK, ProfilesScreen.b[99]);
                                                                                                                                                    Ui.text(Im.label, var2_4, var3_7 + 22.0f, Im.MUTE, ProfilesScreen.b[10]);
                                                                                                                                                    ImGui.setCursorScreenPos((float)var2_4, (float)(var3_7 + 47.0f));
                                                                                                                                                    ImGui.setNextItemWidth((float)222.0f);
                                                                                                                                                    ImGui.pushStyleVar((int)11, (float)0.0f, (float)12.0f);
                                                                                                                                                    ImGui.pushStyleColor((int)0, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                                                                                                                                                    ImGui.pushStyleColor((int)7, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                                                                                                                                                    ImGui.pushStyleColor((int)8, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                                                                                                                                                    ImGui.pushStyleColor((int)9, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                                                                                                                                                    ImGui.pushStyleColor((int)51, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                                                                                                                                                    if (!ImGui.isWindowAppearing()) break block164;
                                                                                                                                                    break block211;
                                                                                                                                                    catch (IOException v52) {
                                                                                                                                                        throw v52;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                ImGui.setKeyboardFocusHere();
                                                                                                                                            }
                                                                                                                                            var12_6 = ProfilesScreen.b;
                                                                                                                                            ImGui.inputText((String)var12_6[50], (ImString)this.code, (int)24);
                                                                                                                                        }
                                                                                                                                        var4_9 = ImGui.isItemActive();
                                                                                                                                        ImGui.popStyleColor((int)5);
                                                                                                                                        ImGui.popStyleVar();
                                                                                                                                        var5_10 = this.code.get().trim();
                                                                                                                                        v54 = var5_10.length();
                                                                                                                                        if (var1_1) break block165;
                                                                                                                                        if (v54 <= 6) break block166;
                                                                                                                                        var5_10 = var5_10.substring(0, 6);
                                                                                                                                    }
                                                                                                                                    v56 = var5_10;
                                                                                                                                    if (var1_1) break block167;
                                                                                                                                    v54 = v56.length();
                                                                                                                                }
                                                                                                                                if (v54 >= 6) // goto
                                                                                                                                this.checked = "";
                                                                                                                                this.found = null;
                                                                                                                                this.wrong = false;
                                                                                                                                if (!var1_1) break block168;
                                                                                                                                break block212;
                                                                                                                                catch (IOException v58) {
                                                                                                                                    throw v58;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            v56 = var5_10;
                                                                                                                        }
                                                                                                                        v60 = v56.equals(this.checked);
                                                                                                                        if (var1_1) break block169;
                                                                                                                        if (v60) break block168;
                                                                                                                        var6_12 = var5_10;
                                                                                                                        this.checked = var5_10;
                                                                                                                        this.found = null;
                                                                                                                        this.wrong = false;
                                                                                                                        ProfileService.fetch(var5_10, (Consumer<JsonObject>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$popups$5(java.lang.String com.google.gson.JsonObject ), (Lcom/google/gson/JsonObject;)V)((ProfilesScreen)this, (String)var6_12));
                                                                                                                    }
                                                                                                                    v62 = this;
                                                                                                                    if (var1_1) break block170;
                                                                                                                    v60 = v62.wrong;
                                                                                                                }
                                                                                                                if (!v60) break block171;
                                                                                                                v62 = this;
                                                                                                                if (var1_1) break block170;
                                                                                                                break block213;
                                                                                                                catch (IOException v64) {
                                                                                                                    throw v64;
                                                                                                                }
                                                                                                            }
                                                                                                            if (v62.shook) break block171;
                                                                                                            break block214;
                                                                                                            catch (IOException v65) {
                                                                                                                throw v65;
                                                                                                            }
                                                                                                        }
                                                                                                        Motion.restart(ProfilesScreen.b[100]);
                                                                                                    }
                                                                                                    v62 = this;
                                                                                                }
                                                                                                v62.shook = this.wrong;
                                                                                                var12_6 = ProfilesScreen.b;
                                                                                                var6_13 = Motion.since(var12_6[102]) / 0.26f * 5.0f;
                                                                                                var7_14 = 0.0f;
                                                                                                v67 = var6_13;
                                                                                                if (var1_1) break block172;
                                                                                                if (!(v67 < 5.0f)) break block173;
                                                                                                var7_14 = ProfilesScreen.SHAKE[(int)var6_13] + (ProfilesScreen.SHAKE[(int)var6_13 + 1] - ProfilesScreen.SHAKE[(int)var6_13]) * (var6_13 - (float)((int)var6_13));
                                                                                            }
                                                                                            v67 = var2_4 + var7_14;
                                                                                        }
                                                                                        v69 = var3_7 + 47.0f;
                                                                                        v70 = var5_10;
                                                                                        v71 = Motion.to(ProfilesScreen.b[37], this.wrong, 0.15f);
                                                                                        v72 = ProfilesScreen.b[19];
                                                                                        v73 = this.found != null;
                                                                                        ProfilesScreen.tiles(v67, v69, v70, v71, Motion.to(v72, v73, 0.15f), var4_9);
                                                                                        var8_15 = var3_7 + 97.0f;
                                                                                        v75 = this;
                                                                                        if (var1_1) break block174;
                                                                                        if (!v75.wrong) break block175;
                                                                                        var12_6 = ProfilesScreen.b;
                                                                                        Ui.text(Im.label, var2_4, var8_15, ProfilesScreen.RED, var12_6[16]);
                                                                                        Ui.text(Im.label, var2_4, var8_15 + 15.0f, ProfilesScreen.RED, var12_6[22]);
                                                                                        var8_15 += 40.0f;
                                                                                        if (!var1_1) break block215;
                                                                                    }
                                                                                    v75 = this;
                                                                                }
                                                                                if (v75.found != null) {
                                                                                    var9_16 = ImGui.getWindowDrawList();
                                                                                    Ui.fill((ImDrawList)var9_16, var2_4, var8_15, var2_4 + 222.0f, var8_15 + 46.0f, Ui.wash(0.03f), 8.0f);
                                                                                    Ui.rect((ImDrawList)var9_16, var2_4, var8_15, var2_4 + 222.0f, var8_15 + 46.0f, Ui.wash(0.08f), 8.0f);
                                                                                    var12_6 = ProfilesScreen.b;
                                                                                    Ui.text(Im.sora13, var2_4 + 11.0f, var8_15 + 7.0f, Im.INK, ProfilesScreen.fit(Im.sora13, this.found.get(var12_6[30]).getAsString(), 200.0f));
                                                                                    var10_19 = this.found.get(var12_6[98]).getAsString();
                                                                                    var11_20 = var10_19.indexOf(44);
                                                                                    v77 = Im.label;
                                                                                    v78 = var2_4 + 11.0f;
                                                                                    v79 = var8_15 + 26.0f;
                                                                                    v80 = Im.MUTE;
                                                                                    v81 = var11_20 < 0 ? var10_19 : var10_19.substring(0, var11_20);
                                                                                    Ui.text(v77, v78, v79, v80, (String)v81);
                                                                                    var8_15 += 56.0f;
                                                                                }
                                                                            }
                                                                            v83 = 0;
                                                                            v84 = ImGui.getStyle().getAlpha();
                                                                            v85 = this.found == null ? 0.35f : 1.0f;
                                                                            ImGui.pushStyleVar((int)v83, (float)(v84 * v85));
                                                                            if (var1_1) break block176;
                                                                            if (!Menu.button(ProfilesScreen.b[2], var2_4, var8_15, 222.0f, 32.0f, true)) break block177;
                                                                            break block216;
                                                                            catch (IOException v87) {
                                                                                throw v87;
                                                                            }
                                                                        }
                                                                        v88 = this;
                                                                        if (var1_1) // goto
                                                                        break block217;
                                                                        catch (IOException v89) {
                                                                            throw v89;
                                                                        }
                                                                    }
                                                                    if (v88.found == null) break block177;
                                                                    try {
                                                                        block179: {
                                                                            block178: {
                                                                                block218: {
                                                                                    v88 = this;
lbl487:

                                                                                    var12_6 = ProfilesScreen.b;
                                                                                    var9_16 = v88.found.get(var12_6[96]).getAsString();
                                                                                    v91 = var9_16;
                                                                                    if (var1_1) break block179;
                                                                                    if (!v91.isEmpty()) break block178;
                                                                                    break block218;
                                                                                    catch (IOException v92) {
                                                                                        throw v92;
                                                                                    }
                                                                                }
                                                                                v91 = ProfilesScreen.b[24];
                                                                                break block179;
                                                                            }
                                                                            v91 = var9_16;
                                                                        }
                                                                        var12_6 = ProfilesScreen.b;
                                                                        var10_19 = ProfileManager.keep((String)v91, this.found.getAsJsonObject(var12_6[20]), this.found.get(var12_6[17]).getAsString());
                                                                        this.picked = var10_19.name();
                                                                        this.note = "";
                                                                        this.reload();
                                                                        ImGui.closeCurrentPopup();
                                                                    }
                                                                    catch (IOException var9_17) {
                                                                        this.note = var9_17.getMessage();
                                                                    }
                                                                }
                                                                ImGui.popStyleVar();
                                                                ImGui.setCursorScreenPos((float)var2_4, (float)var3_7);
                                                                ImGui.dummy((float)222.0f, (float)(var8_15 + 32.0f - var3_7));
                                                            }
                                                            ImGui.endPopup();
                                                        }
                                                        ImGui.popStyleVar((int)2);
                                                        v48 = ImGui.getIO().getDisplaySizeX();
                                                    }
                                                    var2_5 = v48;
                                                    var3_7 = ImGui.getIO().getDisplaySizeY();
                                                    var12_6 = ProfilesScreen.b;
                                                    var4_8 = Motion.to(var12_6[1], ImGui.isPopupOpen((String)var12_6[1]), 0.15f);
                                                    var5_11 = 0.96f + 0.04f * var4_8;
                                                    ImGui.setNextWindowPos((float)(var2_5 / 2.0f - 170.0f * var5_11), (float)(var3_7 / 2.0f - 80.0f + 4.0f * (1.0f - var4_8)));
                                                    ImGui.setNextWindowSize((float)(340.0f * var5_11), (float)0.0f);
                                                    ImGui.pushStyleVar((int)2, (float)(20.0f * var5_11), (float)(20.0f * var5_11));
                                                    v94 = 9;
                                                    if (var1_1) break block180;
                                                    ImGui.pushStyleVar((int)v94, (float)14.0f);
                                                    ImGui.pushStyleVar((int)14, (float)0.0f, (float)6.0f);
                                                    ImGui.pushStyleVar((int)0, (float)(ImGui.getStyle().getAlpha() * Math.max(0.02f, var4_8)));
                                                    if (!ImGui.beginPopupModal((String)var12_6[1], (int)47)) break block181;
                                                    Ui.lift();
                                                    if (var1_1) break block182;
                                                    if (!this.esc) break block183;
                                                    ImGui.closeCurrentPopup();
                                                }
                                                ImGui.setWindowFontScale((float)var5_11);
                                                ImGui.pushFont((ImFont)Im.bold);
                                                ImGui.text((String)Im.need(this.askTitle));
                                                ImGui.popFont();
                                                ImGui.pushFont((ImFont)Im.sub);
                                                ImGui.pushStyleColor((int)0, (float)Im.SUB[0], (float)Im.SUB[1], (float)Im.SUB[2], (float)1.0f);
                                                ImGui.pushTextWrapPos((float)(ImGui.getCursorPosX() + 300.0f * var5_11));
                                                ImGui.textWrapped((String)Im.need(this.askBody));
                                                ImGui.popTextWrapPos();
                                                ImGui.popStyleColor();
                                                ImGui.popFont();
                                                ImGui.dummy((float)(300.0f * var5_11), (float)8.0f);
                                            }
                                            var6_13 = ImGui.getCursorScreenPosX();
                                            var7_14 = ImGui.getCursorScreenPosY();
                                            var8_15 = 146.0f * var5_11;
                                            v99 = Menu.button(ProfilesScreen.b[53], var6_13, var7_14, var8_15, 34.0f, false);
                                            if (var1_1) break block184;
                                            if (!v99) break block185;
                                            ImGui.closeCurrentPopup();
                                        }
                                        v101 = this;
                                        if (var1_1) break block186;
                                        v99 = v101.askRed;
                                    }
                                    if (!v99) // goto
                                    ImGui.setCursorScreenPos((float)(var6_13 + 154.0f * var5_11), (float)var7_14);
                                    var12_6 = ProfilesScreen.b;
                                    var9_18 = ImGui.invisibleButton((String)var12_6[45], (float)var8_15, (float)34.0f);
                                    Ui.fill(ImGui.getWindowDrawList(), var6_13 + 154.0f * var5_11, var7_14, var6_13 + 300.0f * var5_11, var7_14 + 34.0f, Ui.col(ProfilesScreen.RED, 0.88f + 0.12f * Ui.hot()), 8.0f);
                                    Ui.text(Im.sora12r, var6_13 + 154.0f * var5_11 + (float)Math.floor((var8_15 - Ui.width(Im.sora12r, this.askVerb)) / 2.0f), Ui.mid(Im.sora12r, var7_14, 34.0f), Im.INK, this.askVerb);
                                    if (!var1_1) break block187;
lbl600:

                                    v101 = this;
                                }
                                var9_18 = Menu.button(v101.askVerb + ProfilesScreen.b[85], var6_13 + 154.0f * var5_11, var7_14, var8_15, 34.0f, true);
                            }
                            if (!var9_18) break block188;
                            v104 = this;
                            if (var1_1) break block189;
                            if (v104.askRun == null) break block190;
                            this.askRun.run();
                        }
                        v104 = this;
                    }
                    v104.askRun = null;
                    ImGui.closeCurrentPopup();
                }
                ImGui.setCursorScreenPos((float)var6_13, (float)var7_14);
                ImGui.dummy((float)(300.0f * var5_11), (float)34.0f);
                ImGui.endPopup();
            }
            ImGui.popStyleVar((int)4);
            ImGui.popStyleColor();
            v94 = 2;
        }
        ImGui.popStyleVar((int)v94);
    }

    private void lambda$popups$5(String string, JsonObject jsonObject) {
        if (!string.equals(this.checked)) {
            return;
        }
        this.found = jsonObject;
        ProfilesScreen profilesScreen = this;
        boolean bl = jsonObject == null;
        profilesScreen.wrong = bl;
    }

    private void lambda$community$4(String string, List list) {
        if (string.equals(this.queried)) {
            this.shared = list;
        }
    }

    
    private void lambda$keep$3(ProfileService$Listed var1_1, JsonObject var2_2) {
        block10: {
            block13: {
                this.saving.remove(var1_1.code());
                var3_3 = ImScreen.a;
                v0 = var2_2;
                if (var3_3) // goto
                if (v0 != null) break block10;
                break block13;
                catch (IOException v1) {
                    throw v1;
                }
            }
            this.note = ProfileService.status;
            return;
        }
        try {
            block11: {
                block12: {
                    block14: {
                        var5_4 = ProfilesScreen.b;
                        v0 = var2_2.get(var5_4[96]);
lbl21:

                        var4_5 = v0.getAsString();
                        v3 = var4_5;
                        if (var3_3) break block11;
                        if (!v3.isEmpty()) break block12;
                        break block14;
                        catch (IOException v4) {
                            throw v4;
                        }
                    }
                    v3 = var1_1.name();
                    break block11;
                }
                v3 = var4_5;
            }
            ProfileManager.keep(v3, var2_2.getAsJsonObject(ProfilesScreen.b[15]), var1_1.code());
            this.note = "";
            this.reload();
        }
        catch (IOException var4_6) {
            this.note = var4_6.getMessage();
        }
    }

    private void lambda$detail$2(String string) {
        this.reload();
    }

    private void lambda$detail$1(Profile profile) {
        try {
            ProfileManager.update(profile);
            used = profile.name();
            this.note = "";
            this.reload();
        }
        catch (IOException iOException) {
            this.note = iOException.getMessage();
        }
    }

    private void lambda$detail$0(Profile profile) {
        block4: {
            block5: {
                boolean bl = ImScreen.a;
                ProfileService.forget(profile.code());
                if (bl) break block4;
                ProfileManager.delete(profile);
                if (!profile.name().equals(used)) break block5;
                used = "";
            }
            this.reload();
        }
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[105];
                var3_1 = 0;
                var2_2 = "?jK\u0003p)K\nX7P\u001eHsh2=O!D*D\u0010Nbkf3T\u0019\u001a~$d(\u0000\u0012Ou9t4TQIb?e3N\u0016I\u000bD)EQJu$w3L\u0014\bE(E\u001f^n%v\u0006X4\u0000\u0004Ib\u000b\\#\u0000\u0001Hh-x6E\u0002\u0006x7P\u001eHs\u000eE2I\u0002\u001au.a6A\u0012_tk'E#P\u0014\u001ah91*A\u0002NbkpzC\u001e^bkpzF\u0003Sb%uzS\u0014Tskh5U_\u0003p)K\b2yR\u0014Tf&t\u0005B;V\u0014^\u0006B;V\u0014\u0019$\u0004u;T\u0010\u0019_5\u0000\u0001Hh-x6EQRf81.H\u0010N'(~>E_\u0004r5D\u0014\be(E\u001f^n%v\tr5D\u0014\u001a`$~>\u0004u;T\u0010\tR5M\u001cOi\"e#\u0017R2E\u0012Q'\"ezA\u001f^'?c#\u0000\u0010]f\"\u007ft\u0007U?L\u0014Nbk\bX7P\u001eHs.u\u0004}3S\u0005\t\\5S\u0005\u001ar8t>\tr5M\u001cOi\"e#\u0007t4A\u0013Vb/\u0003c5W\u0004\u007f;M\u0014K1-I\u0005R'?y?\u0000\u0002_s?x4G\u0002\u001a~$dzH\u0010Lbk~4\u0000\u0003S`#ezN\u001eM)kE2EQUk/1,E\u0003In$\u007fzI\u0002\u001a`$\u007f?\u0000\u0017Uukv5O\u0015\u0014\u0006U?L\u0014Nb\u0006X7P\u001eHs\u0006D*D\u0010Nb\u0004t>I\u0005\u0004b5R\u0005\br5D\u0014\u001ae*u\u0016_5T\u0019Si,1*U\u0013Vn8y?DQCb??\u0007c?F\u0003_t#\u0005b2A\u0003_\u0007t4A\u0013Vb/\u0006X7P\u001eHs\u0007B;V\u0014\u0019$-\u0005e(A\u0002R\u0002v5\u0005r;R\u0014N\u0007a5P\u0004Vf9\u000fB?A\u0003Yoka(O\u0017Sk.b\u0003\u007f?W\u00062yC\u001e^b\u00062yN\u0010Wb\u0007D*D\u0010Nbk\u000bR;N\u0012_kh2;S\u001a\u0006_?W\u0014Is\bW?A\u0005Ou.u\u0004b5R\u0005\bu5W\u001fVh*u\u0007_;M\u0014\u001an?\u001bB?N\u0015\u001as#x)\u0000\u0012Uc.1.OQ['-c3E\u001f^)\u0007A5P\u0004Vf9\b2yD\u0014Vb?t\u0005b2A\u0003_\u000bB;V\u0014\u001ad$\u007f<I\u0016\u000b2yR\u0014Tf&t*E\u001f\u0006X4\u0000\u0004Ib\u0005r2E\u0012Q\u0005B2A\u0003_613SQHb&~,E\u0015\u001aa9~7\u0000\u0005Rn819O\u001cJr?t(\u000eQno\"bzC\u0010Ti$ezB\u0014\u001ar%u5N\u0014\u0014\u0006U?L\u0014Nb\n4t\u0011\u0017Q'>b?S\u0005e(A\u0002R\bu5W\u001fVh*u\b2yS\u0014[u(y\bA(O\u0017Sk.b\u00051/S\u0014I\u0010_5\u0000\u0001Hh-x6E\u0002\u001a~.et\tR5P\b\u001ad$u?\u0004t>I\u0005\u0006C?N\u0010Wb\u0007c?F\u0003_t#\u0006B2A\u0003_'\u0004|5D\u0002\u0005B2A\u0003_\u0005B;V\u0014^\u00042yG\u001e\u0007|5D\u0004Vb8\u0011V?T\u0005Si,1;\u0000\u0012Uc.?t\u000e\u00062yS\u001eHs\b2yU\u0001^f?t\u0006x7P\u001eHs\u000bD)EQJu$w3L\u0014\u0005r2E\u0012Q\u0007|5D\u0004Vb8\u0005e3T\u001d_\u0019E2E\b\u001as2a?\u0000\u0018N'\"\u007f.OQsj;~(T_\u0004\u007f;M\u0014\b|5D\u0004Vb8>\u0007b/M\u001c[u2\u0010X7P\u001eHskpzP\u0003Ua\"}?\nr5D\u0014\u001at#p1E\u0004b;V\u0014\nr5D\u0014\u001at#p1E";
                var4_3 = "?jK\u0003p)K\nX7P\u001eHsh2=O!D*D\u0010Nbkf3T\u0019\u001a~$d(\u0000\u0012Ou9t4TQIb?e3N\u0016I\u000bD)EQJu$w3L\u0014\bE(E\u001f^n%v\u0006X4\u0000\u0004Ib\u000b\\#\u0000\u0001Hh-x6E\u0002\u0006x7P\u001eHs\u000eE2I\u0002\u001au.a6A\u0012_tk'E#P\u0014\u001ah91*A\u0002NbkpzC\u001e^bkpzF\u0003Sb%uzS\u0014Tskh5U_\u0003p)K\b2yR\u0014Tf&t\u0005B;V\u0014^\u0006B;V\u0014\u0019$\u0004u;T\u0010\u0019_5\u0000\u0001Hh-x6EQRf81.H\u0010N'(~>E_\u0004r5D\u0014\be(E\u001f^n%v\tr5D\u0014\u001a`$~>\u0004u;T\u0010\tR5M\u001cOi\"e#\u0017R2E\u0012Q'\"ezA\u001f^'?c#\u0000\u0010]f\"\u007ft\u0007U?L\u0014Nbk\bX7P\u001eHs.u\u0004}3S\u0005\t\\5S\u0005\u001ar8t>\tr5M\u001cOi\"e#\u0007t4A\u0013Vb/\u0003c5W\u0004\u007f;M\u0014K1-I\u0005R'?y?\u0000\u0002_s?x4G\u0002\u001a~$dzH\u0010Lbk~4\u0000\u0003S`#ezN\u001eM)kE2EQUk/1,E\u0003In$\u007fzI\u0002\u001a`$\u007f?\u0000\u0017Uukv5O\u0015\u0014\u0006U?L\u0014Nb\u0006X7P\u001eHs\u0006D*D\u0010Nb\u0004t>I\u0005\u0004b5R\u0005\br5D\u0014\u001ae*u\u0016_5T\u0019Si,1*U\u0013Vn8y?DQCb??\u0007c?F\u0003_t#\u0005b2A\u0003_\u0007t4A\u0013Vb/\u0006X7P\u001eHs\u0007B;V\u0014\u0019$-\u0005e(A\u0002R\u0002v5\u0005r;R\u0014N\u0007a5P\u0004Vf9\u000fB?A\u0003Yoka(O\u0017Sk.b\u0003\u007f?W\u00062yC\u001e^b\u00062yN\u0010Wb\u0007D*D\u0010Nbk\u000bR;N\u0012_kh2;S\u001a\u0006_?W\u0014Is\bW?A\u0005Ou.u\u0004b5R\u0005\bu5W\u001fVh*u\u0007_;M\u0014\u001an?\u001bB?N\u0015\u001as#x)\u0000\u0012Uc.1.OQ['-c3E\u001f^)\u0007A5P\u0004Vf9\b2yD\u0014Vb?t\u0005b2A\u0003_\u000bB;V\u0014\u001ad$\u007f<I\u0016\u000b2yR\u0014Tf&t*E\u001f\u0006X4\u0000\u0004Ib\u0005r2E\u0012Q\u0005B2A\u0003_613SQHb&~,E\u0015\u001aa9~7\u0000\u0005Rn819O\u001cJr?t(\u000eQno\"bzC\u0010Ti$ezB\u0014\u001ar%u5N\u0014\u0014\u0006U?L\u0014Nb\n4t\u0011\u0017Q'>b?S\u0005e(A\u0002R\bu5W\u001fVh*u\b2yS\u0014[u(y\bA(O\u0017Sk.b\u00051/S\u0014I\u0010_5\u0000\u0001Hh-x6E\u0002\u001a~.et\tR5P\b\u001ad$u?\u0004t>I\u0005\u0006C?N\u0010Wb\u0007c?F\u0003_t#\u0006B2A\u0003_'\u0004|5D\u0002\u0005B2A\u0003_\u0005B;V\u0014^\u00042yG\u001e\u0007|5D\u0004Vb8\u0011V?T\u0005Si,1;\u0000\u0012Uc.?t\u000e\u00062yS\u001eHs\b2yU\u0001^f?t\u0006x7P\u001eHs\u000bD)EQJu$w3L\u0014\u0005r2E\u0012Q\u0007|5D\u0004Vb8\u0005e3T\u001d_\u0019E2E\b\u001as2a?\u0000\u0018N'\"\u007f.OQsj;~(T_\u0004\u007f;M\u0014\b|5D\u0004Vb8>\u0007b/M\u001c[u2\u0010X7P\u001eHskpzP\u0003Ua\"}?\nr5D\u0014\u001at#p1E\u0004b;V\u0014\nr5D\u0014\u001at#p1E".length();
                var1_4 = 3;
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
                    var2_2 = "A(O\u0017Sk.b\u000fT4A\u0013Vb/1\u0017O\u0015Ok.b";
                    var4_3 = "A(O\u0017Sk.b\u000fT4A\u0013Vb/1\u0017O\u0015Ok.b".length();
                    var1_4 = 8;
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
                        v10 = 17;
                        break;
                    }
                    case 1: {
                        v10 = 90;
                        break;
                    }
                    case 2: {
                        v10 = 32;
                        break;
                    }
                    case 3: {
                        v10 = 113;
                        break;
                    }
                    case 4: {
                        v10 = 58;
                        break;
                    }
                    case 5: {
                        v10 = 7;
                        break;
                    }
                    default: {
                        v10 = 75;
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
        ProfilesScreen.b = var5;
        v11 = new String[2];
        var0_6 = ProfilesScreen.b;
        v11[0] = var0_6[7];
        v11[1] = var0_6[21];
        ProfilesScreen.TAB = v11;
        ProfilesScreen.SORT = new String[]{var0_6[47], var0_6[18], var0_6[49]};
        ProfilesScreen.SORT_NAMES = new String[]{var0_6[26], var0_6[5], var0_6[54]};
        ProfilesScreen.RED = new float[]{0.898f, 0.282f, 0.302f, 1.0f};
        ProfilesScreen.used = "";
        ProfilesScreen.SHAKE = new float[]{0.0f, -5.0f, 5.0f, -3.0f, 3.0f, 0.0f};
    }
}
