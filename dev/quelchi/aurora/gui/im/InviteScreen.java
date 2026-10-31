package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.PauseScreen;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.social.Social;
import dev.quelchi.aurora.social.Social$Friend;
import dev.quelchi.aurora.social.WorldShare;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.type.ImString;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

public final class InviteScreen
extends ImScreen {
    private static final float W = 560.0f;
    private static final float H = 600.0f;
    private static final float PAD = 24.0f;
    private static final float ROW = 52.0f;
    private final ImString search = new ImString(32);
    private static final String[] b;
    private static final long c;

    public InviteScreen() {
        super(b[5]);
    }

    @Override
    public boolean method_25421() {
        return true;
    }

    public void method_25394(DrawContext drawContext, int n, int n2, float f) {
        drawContext.method_25294(0, 0, this.field_22789, this.field_22790, (int)c);
        super.method_25394(drawContext, n, n2, f);
    }

    @Override
    public void method_25419() {
        MinecraftClient.method_1551().method_1507((Screen)new PauseScreen());
    }

    @Override
    protected void draw() {
        block46: {
            boolean bl;
            block48: {
                block47: {
                    boolean bl2;
                    boolean bl3;
                    block43: {
                        float f;
                        float f2;
                        block44: {
                            float f3;
                            String[] stringArray;
                            block45: {
                                String string;
                                boolean bl4;
                                float f4;
                                boolean bl5;
                                float f5;
                                float f6;
                                float f7;
                                block42: {
                                    boolean bl6;
                                    block40: {
                                        float f8;
                                        block41: {
                                            boolean bl7;
                                            float f9;
                                            float f10;
                                            float f11;
                                            String string2;
                                            block38: {
                                                block39: {
                                                    String string3;
                                                    ImDrawList imDrawList;
                                                    block35: {
                                                        String string4;
                                                        block37: {
                                                            block36: {
                                                                boolean bl8;
                                                                block34: {
                                                                    String string5;
                                                                    block33: {
                                                                        block32: {
                                                                            Social.tick(true);
                                                                            bl3 = ImScreen.a;
                                                                            float f12 = ImGui.getIO().getDisplaySizeX();
                                                                            float f13 = ImGui.getIO().getDisplaySizeY();
                                                                            ImGui.setNextWindowPos((float)0.0f, (float)0.0f);
                                                                            ImGui.setNextWindowSize((float)f12, (float)f13);
                                                                            ImGui.pushStyleVar((int)2, (float)0.0f, (float)0.0f);
                                                                            stringArray = b;
                                                                            ImGui.begin((String)stringArray[30], (int)8367);
                                                                            ImGui.popStyleVar();
                                                                            f7 = Math.min(600.0f, f13 - 40.0f);
                                                                            f2 = Math.round((f12 - 560.0f) / 2.0f);
                                                                            f6 = Math.round((f13 - f7) / 2.0f);
                                                                            f5 = 512.0f;
                                                                            f3 = f2 + 24.0f;
                                                                            imDrawList = ImGui.getWindowDrawList();
                                                                            Ui.fill(imDrawList, f2, f6, f2 + 560.0f, f6 + f7, Ui.col(Im.POP), 14.0f);
                                                                            Ui.rect(imDrawList, f2, f6, f2 + 560.0f, f6 + f7, Ui.wash(0.16f), 14.0f);
                                                                            bl5 = WorldShare.hosting();
                                                                            string3 = WorldShare.mode();
                                                                            Ui.text(Im.title, f3, f6 + 24.0f, Im.INK, stringArray[21]);
                                                                            Ui.text(Im.sub, f3, f6 + 24.0f + 30.0f, Im.MUTE, WorldShare.worldName());
                                                                            if (!bl5) break block32;
                                                                            string5 = stringArray[36];
                                                                            break block33;
                                                                        }
                                                                        stringArray = b;
                                                                        string5 = stringArray[33];
                                                                    }
                                                                    String string6 = string5;
                                                                    float f14 = Ui.width(Im.sora13, string6);
                                                                    ImFont imFont = Im.sora13;
                                                                    float f15 = f2 + 560.0f - 24.0f - 44.0f - 10.0f - f14;
                                                                    float f16 = Ui.mid(Im.sora13, f6 + 24.0f + 4.0f, 24.0f);
                                                                    float[] fArray = bl5 ? Im.GREEN : Im.MUTE;
                                                                    Ui.text(imFont, f15, f16, fArray, string6);
                                                                    bl8 = Ui.toggle(b[8], f2 + 560.0f - 24.0f - 44.0f, f6 + 24.0f + 4.0f, 44.0f, 24.0f, bl5);
                                                                    if (bl3) break block34;
                                                                    if (!bl8) break block35;
                                                                    bl8 = bl5;
                                                                }
                                                                if (!bl8) break block36;
                                                                string4 = b[28];
                                                                break block37;
                                                            }
                                                            stringArray = b;
                                                            string4 = stringArray[13];
                                                        }
                                                        WorldShare.setMode(string4);
                                                    }
                                                    f4 = f6 + 24.0f + 66.0f;
                                                    stringArray = b;
                                                    Ui.label(f3, f4, stringArray[3]);
                                                    bl4 = string3.equals(stringArray[27]);
                                                    Ui.fill(imDrawList, f3, f4 += 22.0f, f3 + f5, f4 + 44.0f, Ui.col(Im.WELL), 10.0f);
                                                    f8 = (f5 - 12.0f) / 2.0f;
                                                    string2 = stringArray[17];
                                                    f11 = f3 + 4.0f;
                                                    f10 = f4 + 4.0f;
                                                    f9 = f8;
                                                    bl7 = bl4;
                                                    if (bl3) break block38;
                                                    if (bl7) break block39;
                                                    bl7 = true;
                                                    break block38;
                                                }
                                                bl7 = false;
                                            }
                                            bl6 = InviteScreen.segment(string2, f11, f10, f9, bl7);
                                            if (bl3) break block40;
                                            if (!bl6) break block41;
                                            WorldShare.setMode(b[13]);
                                        }
                                        string = b[16];
                                        if (bl3) break block42;
                                        bl6 = InviteScreen.segment(string, f3 + 8.0f + f8, f4 + 4.0f, f8, bl4);
                                    }
                                    if (bl6) {
                                        WorldShare.setMode(b[6]);
                                    }
                                    f4 += 58.0f;
                                    this.typeToSearch();
                                    stringArray = b;
                                    string = stringArray[7];
                                }
                                stringArray = b;
                                Menu.search(string, stringArray[18], this.search, f3, f4, f5);
                                float f17 = 64.0f;
                                float f18 = f6 + f7 - f17 - (f4 += 50.0f) - 8.0f;
                                this.list(f3, f4, f5, f18, bl4);
                                f = f6 + f7 - 24.0f - 36.0f;
                                bl2 = bl5;
                                if (bl3) break block43;
                                if (!bl2) break block44;
                                ImGui.setCursorScreenPos((float)f3, (float)f);
                                if (!ImGui.invisibleButton((String)b[19], (float)(Ui.width(Im.sora13, b[31]) + 8.0f), (float)36.0f)) break block45;
                                WorldShare.stop();
                            }
                            stringArray = b;
                            Ui.text(Im.sora13, f3, Ui.mid(Im.sora13, f, 36.0f), Ui.mix(Im.DANGER, Im.INK, Ui.hot() * 0.3f), stringArray[12]);
                        }
                        bl2 = Menu.button(b[11], f2 + 560.0f - 24.0f - 120.0f, f, 120.0f, 36.0f, false);
                    }
                    if (bl2) {
                        this.method_25419();
                    }
                    ImGui.end();
                    if (!bl3) break block46;
                    if (!Module.a) break block47;
                    bl = false;
                    break block48;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    
    private static boolean segment(String string, float f, float f2, float f3, boolean bl) {
        boolean bl2;
        float f4;
        boolean bl3;
        block12: {
            ImDrawList imDrawList;
            block11: {
                block10: {
                    ImGui.setCursorScreenPos((float)f, (float)f2);
                    bl3 = ImGui.invisibleButton((String)(b[0] + string), (float)f3, (float)36.0f);
                    f4 = Ui.hot();
                    bl2 = ImScreen.a;
                    imDrawList = ImGui.getWindowDrawList();
                    if (bl2) break block10;
                    if (!bl) break block11;
                    Ui.fill(imDrawList, f, f2, f + f3, f2 + 36.0f, Ui.col(Im.ON_FILL), 7.0f);
                }
                if (!bl2) break block12;
            }
            Ui.fill(imDrawList, f, f2, f + f3, f2 + 36.0f, Ui.col(Im.QUIET, f4), 7.0f);
        }
        Ui.text(Im.sora13, f + (float)Math.floor((f3 - Ui.width(Im.sora13, string)) / 2.0f), Ui.mid(Im.sora13, f2, 36.0f), bl ? Im.BG : Ui.mix(Im.SUB, Im.INK, f4), string);
        boolean bl4 = bl3;
        if (!bl2) {
            if (!bl4) return false;
            bl4 = bl;
        }
        if (bl2) return bl4;
        if (bl4) return false;
        return true;
    }

    private void list(float f, float f2, float f3, float f4, boolean bl) {
        float f5;
        String[] stringArray;
        ArrayList<Social$Friend> arrayList;
        ArrayList<Social$Friend> arrayList2;
        ArrayList<Social$Friend> arrayList3;
        boolean bl2;
        String string;
        block29: {
            string = this.search.get().trim().toLowerCase(Locale.ROOT);
            bl2 = ImScreen.a;
            List<UUID> list = WorldShare.inWorld();
            arrayList3 = new ArrayList<Social$Friend>();
            arrayList2 = new ArrayList<Social$Friend>();
            arrayList = new ArrayList<Social$Friend>();
            for (Social$Friend social$Friend : Social.friends()) {
                ArrayList<Social$Friend> arrayList4;
                block35: {
                    boolean bl3;
                    block33: {
                        block34: {
                            block31: {
                                block32: {
                                    block30: {
                                        if (bl2) break block29;
                                        bl3 = social$Friend.accepted();
                                        if (bl2) break block30;
                                        if (!bl3) continue;
                                        bl3 = string.isEmpty();
                                    }
                                    if (bl2) break block31;
                                    if (bl3) break block32;
                                    bl3 = social$Friend.name().toLowerCase(Locale.ROOT).contains(string);
                                    if (bl2) break block31;
                                    if (!bl3) {
                                        continue;
                                    }
                                }
                                bl3 = list.contains(social$Friend.uuid());
                            }
                            if (bl2) break block33;
                            if (!bl3) break block34;
                            arrayList4 = arrayList3;
                            break block35;
                        }
                        bl3 = social$Friend.online();
                    }
                    arrayList4 = bl3 ? arrayList2 : arrayList;
                }
                arrayList4.add(social$Friend);
                if (!bl2) continue;
            }
            ImGui.setCursorScreenPos((float)f, (float)f2);
            ImGui.pushStyleColor((int)3, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
            ImGui.pushStyleVar((int)8, (float)0.0f);
            stringArray = b;
            ImGui.beginChild((String)stringArray[26], (float)f3, (float)f4, (boolean)false);
        }
        float f6 = f5 = ImGui.getCursorScreenPosY();
        stringArray = b;
        f5 = InviteScreen.section(stringArray[2], arrayList3, f, f5, f3, bl, true);
        if (!bl2) {
            block36: {
                String string2;
                block39: {
                    boolean bl4;
                    block37: {
                        block38: {
                            f5 = InviteScreen.section(stringArray[22], arrayList2, f, f5, f3, bl, false);
                            f5 = InviteScreen.section(stringArray[23], arrayList, f, f5, f3, bl, false);
                            if (f5 != f6) break block36;
                            bl4 = Social.ready();
                            if (bl2) break block37;
                            if (bl4) break block38;
                            string2 = b[14];
                            break block39;
                        }
                        string2 = string;
                        if (bl2) break block39;
                        bl4 = string2.isEmpty();
                    }
                    if (!bl4) {
                        stringArray = b;
                        string2 = stringArray[9];
                    } else {
                        string2 = b[24];
                    }
                }
                String string3 = string2;
                Ui.text(Im.sub, f, f5 + 4.0f, Im.MUTE, string3);
                f5 += 26.0f;
            }
            ImGui.setCursorScreenPos((float)f, (float)(f5 + 4.0f));
            ImGui.dummy((float)1.0f, (float)1.0f);
            ImGui.endChild();
            ImGui.popStyleVar();
            ImGui.popStyleColor();
        }
    }

    private static float section(String string, List<Social$Friend> list, float f, float f2, float f3, boolean bl, boolean bl2) {
        float f4;
        block8: {
            List<Social$Friend> list2;
            boolean bl3;
            block6: {
                block7: {
                    bl3 = ImScreen.a;
                    list2 = list;
                    if (bl3) break block6;
                    if (!list2.isEmpty()) break block7;
                    return f2;
                }
                Ui.label(f, f2 + 4.0f, string + b[29] + list.size());
                f2 += 26.0f;
                list2 = list;
            }
            for (Social$Friend social$Friend : list2) {
                InviteScreen.row(social$Friend, f, f2, f3, bl, bl2);
                f4 = f2;
                if (!bl3) {
                    f2 = f4 + 52.0f;
                    if (!bl3) continue;
                }
                break block8;
            }
            f4 = f2 + 8.0f;
        }
        return f4;
    }

    
    private static void row(Social$Friend var0, float var1_1, float var2_2, float var3_3, boolean var4_4, boolean var5_5) {
        block55: {
            block61: {
                block62: {
                    block59: {
                        block60: {
                            block58: {
                                block57: {
                                    block56: {
                                        block53: {
                                            block54: {
                                                block46: {
                                                    block51: {
                                                        block52: {
                                                            block49: {
                                                                block50: {
                                                                    block47: {
                                                                        block48: {
                                                                            block44: {
                                                                                block45: {
                                                                                    block43: {
                                                                                        block42: {
                                                                                            block41: {
                                                                                                var6_6 = ImScreen.a;
                                                                                                v0 = 0;
                                                                                                v1 = ImGui.getStyle().getAlpha();
                                                                                                v2 = var0.online();
                                                                                                if (var6_6) break block41;
                                                                                                if (!v2) {
                                                                                                }
                                                                                                v2 = var5_5;
                                                                                            }
                                                                                            if (!v2) break block42;
lbl16:

                                                                                            v4 = 1.0f;
                                                                                            break block43;
                                                                                        }
                                                                                        v4 = 0.6f;
                                                                                    }
                                                                                    ImGui.pushStyleVar((int)v0, (float)(v1 * v4));
                                                                                    Ui.head(var0.uuid(), var1_1, var2_2 + 10.0f, 32.0f);
                                                                                    var7_7 = WorldShare.invited(var0.uuid());
                                                                                    v6 = var5_5;
                                                                                    if (var6_6) break block44;
                                                                                    if (!v6) break block45;
                                                                                    v8 = InviteScreen.b[10];
                                                                                    break block46;
                                                                                }
                                                                                v6 = var7_7;
                                                                            }
                                                                            if (var6_6) break block47;
                                                                            if (!v6) break block48;
                                                                            v6 = WorldShare.hosting();
                                                                            if (var6_6) break block47;
                                                                            if (!v6) break block48;
                                                                            v8 = InviteScreen.b[34];
                                                                            break block46;
                                                                        }
                                                                        v14 = var0;
                                                                        if (var6_6) break block49;
                                                                        v6 = v14.online();
                                                                    }
                                                                    if (v6) break block50;
                                                                    v8 = InviteScreen.b[15];
                                                                    break block46;
                                                                }
                                                                v14 = var0;
                                                            }
                                                            if (var6_6) break block51;
                                                            if (!v14.server().isEmpty()) break block52;
                                                            v8 = InviteScreen.b[35];
                                                            break block46;
                                                        }
                                                        v14 = var0;
                                                    }
                                                    v8 = WorldShare.where(v14.server());
                                                }
                                                var8_8 = v8;
                                                var9_9 = Ui.pair(Im.sora13, Im.label, var2_2, 52.0f);
                                                Ui.text(Im.sora13, var1_1 + 44.0f, var9_9, Im.INK, var0.name());
                                                Ui.text(Im.label, var1_1 + 44.0f, Ui.under(Im.sora13, Im.label, var9_9), Im.MUTE, var8_8);
                                                var10_10 = var1_1 + var3_3;
                                                v19 = var5_5;
                                                if (var6_6) break block53;
                                                if (!v19) break block54;
                                                Ui.text(Im.sora12r, var10_10 - Ui.width(Im.sora12r, InviteScreen.b[1]), Ui.mid(Im.sora12r, var2_2, 52.0f), Im.GREEN, InviteScreen.b[25]);
                                                if (!var6_6) break block55;
                                            }
                                            v19 = var4_4;
                                        }
                                        if (var6_6) break block56;
                                        if (v19) break block55;
                                        v19 = var7_7;
                                    }
                                    if (!v19) break block57;
                                    v24 = InviteScreen.b[20];
                                    break block58;
                                }
                                var13_11 = InviteScreen.b;
                                v24 = var13_11[32];
                            }
                            var11_12 = v24;
                            var12_13 = Ui.width(Im.sora12r, var11_12) + 28.0f;
                            v26 = var11_12 + InviteScreen.b[4] + String.valueOf(var0.uuid());
                            v27 = var10_10 - var12_13;
                            v28 = var2_2 + 12.0f;
                            v29 = var12_13;
                            v30 = 28.0f;
                            v31 = var7_7;
                            if (var6_6) break block59;
                            if (v31) break block60;
                            v31 = true;
                            break block59;
                        }
                        v31 = false;
                    }
                    if (!Menu.button(v26, v27, v28, v29, v30, v31)) break block55;
                    v33 = var0.uuid();
                    v34 = var7_7;
                    if (var6_6) break block61;
                    if (v34) break block62;
                    v34 = true;
                    break block61;
                }
                v34 = false;
            }
            WorldShare.invite(v33, v34);
        }
        ImGui.popStyleVar();
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[37];
                    var5_1 = 0;
                    var4_2 = "P \u0006\u0004k \u0013T\rj+AHCP!\u00032\u000eC@A\ft-\u000e\u0011OD=\u0003/\u000eXB\u0005\u0000f\b_Z\u000fj+\u0017XX@sW*AfCW?G\u0007E7\bTBA \u000e\u0000f\b_ZL'F6\u0004P^F;\u0005P-\u0000CI\u0010m*\u0015YEK4\u0003(\u0000EOM6Pk\rj+AHCP!\u00032\u000eC@A\fg*\u000fT\u000f\u0006:M3\bEI\fp1\u000eA\fV;B7\b_K\u0006J+\u0017XX@\u0018p,\u0006_\fL=\u00031\u000e\u0011mP!L7\u0000\u0011JL!P1O\u001dd \u0015B\fL'\u00032\tTB\u0005'K \u0018\u0011OJ>Fe\u000e_@L=F\u000bb)\r\u0011JW:F+\u0005B\u0014l+\rH\fU6L5\rT\flsJ+\u0017XX@\u0011p \u0000COMsE7\bTBA \rkO\tP1\u000eA_M2Q \u000ej+\u0017XX@7\u0003\u00f2AdBA<\u000fj+\u0017XX@sW*AfCW?G\u0006l+\rXB@\u0007l#\u0007]EK6\u001bb!\u0005\u0011JW:F+\u0005B\fQ<\u0003,\u000fGEQ6\u00031\tTA\u000b\u0004k \u0013T\nJ+\u0017XX@?J6\u0015\u0007E7\bTBA \u0006M*\u0003^H\\\u0003\u0003\u00f2A\u0006J+\u0017XX@\fp1\u000eA\fV;B7\b_K\u0006j+\u0017XX@\u0003l#\u0007\u000bj+\u0017XX@sP \u000fE";
                    var6_3 = "P \u0006\u0004k \u0013T\rj+AHCP!\u00032\u000eC@A\ft-\u000e\u0011OD=\u0003/\u000eXB\u0005\u0000f\b_Z\u000fj+\u0017XX@sW*AfCW?G\u0007E7\bTBA \u000e\u0000f\b_ZL'F6\u0004P^F;\u0005P-\u0000CI\u0010m*\u0015YEK4\u0003(\u0000EOM6Pk\rj+AHCP!\u00032\u000eC@A\fg*\u000fT\u000f\u0006:M3\bEI\fp1\u000eA\fV;B7\b_K\u0006J+\u0017XX@\u0018p,\u0006_\fL=\u00031\u000e\u0011mP!L7\u0000\u0011JL!P1O\u001dd \u0015B\fL'\u00032\tTB\u0005'K \u0018\u0011OJ>Fe\u000e_@L=F\u000bb)\r\u0011JW:F+\u0005B\u0014l+\rH\fU6L5\rT\flsJ+\u0017XX@\u0011p \u0000COMsE7\bTBA \rkO\tP1\u000eA_M2Q \u000ej+\u0017XX@7\u0003\u00f2AdBA<\u000fj+\u0017XX@sW*AfCW?G\u0006l+\rXB@\u0007l#\u0007]EK6\u001bb!\u0005\u0011JW:F+\u0005B\fQ<\u0003,\u000fGEQ6\u00031\tTA\u000b\u0004k \u0013T\nJ+\u0017XX@?J6\u0015\u0007E7\bTBA \u0006M*\u0003^H\\\u0003\u0003\u00f2A\u0006J+\u0017XX@\fp1\u000eA\fV;B7\b_K\u0006j+\u0017XX@\u0003l#\u0007\u000bj+\u0017XX@sP \u000fE".length();
                    var3_4 = 3;
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
                        var4_2 = "l+\rXB@\u0007p-\u0000CEK4";
                        var6_3 = "l+\rXB@\u0007p-\u0000CEK4".length();
                        var3_4 = 6;
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
                            v10 = 35;
                            break;
                        }
                        case 1: {
                            v10 = 69;
                            break;
                        }
                        case 2: {
                            v10 = 97;
                            break;
                        }
                        case 3: {
                            v10 = 49;
                            break;
                        }
                        case 4: {
                            v10 = 44;
                            break;
                        }
                        case 5: {
                            v10 = 37;
                            break;
                        }
                        default: {
                            v10 = 83;
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
            InviteScreen.b = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 5324760104909653764L;
        while (true)
        InviteScreen.c = -4976756472847613682L ^ var0_7;
    }
}
