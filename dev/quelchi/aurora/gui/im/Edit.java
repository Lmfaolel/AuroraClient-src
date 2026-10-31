package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.ModsScreen;
import dev.quelchi.aurora.gui.im.Ui;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.type.ImString;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

final class Edit {
    static final float PAD = 20.0f;
    static final float ROW = 46.0f;
    static final float SET = 34.0f;
    static final float PANE = 378.0f;
    private static final String[] a;

    private Edit() {
    }

    static void back() {
        ConfigManager.save();
        MinecraftClient.method_1551().method_1507((Screen)new ModsScreen());
    }

    static float gui(float f) {
        return f * Im.scale() / (float)MinecraftClient.method_1551().method_22683().method_4495();
    }

    static float bar(String string, String string2) {
        float f = Menu.x + 144.0f + 20.0f;
        float f2 = Menu.y;
        float f3 = Ui.mid(Im.sora13, f2, 56.0f);
        boolean bl = ImScreen.a;
        ImGui.setCursorScreenPos((float)f, (float)(f2 + 14.0f));
        String[] stringArray = a;
        boolean bl2 = ImGui.invisibleButton((String)stringArray[3], (float)28.0f, (float)28.0f);
        boolean bl3 = ImGui.isItemHovered();
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        ImDrawList imDrawList2 = imDrawList;
        float f4 = f + 14.0f;
        float f5 = f2 + 28.0f;
        float f6 = 14.0f;
        float[] fArray = bl3 ? Im.QUIET_HI : Im.QUIET;
        imDrawList2.addCircleFilled(f4, f5, f6, Ui.col(fArray), 32);
        imDrawList.addCircle(f + 14.0f, f2 + 28.0f, 14.0f, Ui.wash(0.16f), 32);
        float[] fArray2 = bl3 ? Im.INK : Im.SUB;
        int n = Ui.col(fArray2);
        imDrawList.addLine(f + 15.5f, f2 + 23.0f, f + 10.5f, f2 + 28.0f, n, 1.6f);
        imDrawList.addLine(f + 10.5f, f2 + 28.0f, f + 15.5f, f2 + 33.0f, n, 1.6f);
        ImGui.setCursorScreenPos((float)(f += 38.0f), (float)(f2 + 18.0f));
        stringArray = a;
        bl2 |= ImGui.invisibleButton((String)stringArray[2], (float)Ui.width(Im.sora13, stringArray[0]), (float)20.0f);
        ImFont imFont = Im.sora13;
        float f7 = f;
        float f8 = f3;
        float[] fArray3 = ImGui.isItemHovered() ? Im.INK : Im.SUB;
        stringArray = a;
        Ui.text(imFont, f7, f8, fArray3, stringArray[6]);
        float f9 = f;
        float f10 = Ui.width(Im.sora13, stringArray[6]);
        if (!bl) {
            f = Menu.chevron(f9 + f10 + 8.0f, f3);
            Ui.text(Im.sora13, f, f3, Im.SUB, string);
            f = Menu.chevron(f + Ui.width(Im.sora13, string) + 8.0f, f3);
            Ui.text(Im.sora13, f, f3, Im.INK, string2);
            if (bl2) {
                Edit.back();
            }
            f9 = f;
            f10 = Ui.width(Im.sora13, string2);
        }
        return f9 + f10;
    }

    static void list(String string, float f, float f2, float f3, float f4) {
        ImGui.setCursorScreenPos((float)(f - 1.0f), (float)(f2 - 1.0f));
        ImGui.pushStyleColor((int)3, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        ImGui.pushStyleVar((int)8, (float)0.0f);
        ImGui.beginChild((String)string, (float)(f3 + 2.0f), (float)(f4 + 1.0f), (boolean)false);
        ImGui.setCursorScreenPos((float)(ImGui.getCursorScreenPosX() + 1.0f), (float)(ImGui.getCursorScreenPosY() + 1.0f));
    }

    static void endList(float f, float f2) {
        ImGui.setCursorScreenPos((float)f, (float)f2);
        ImGui.dummy((float)1.0f, (float)1.0f);
        ImGui.endChild();
        ImGui.popStyleVar();
        ImGui.popStyleColor();
    }

    static boolean row(String string, float f, float f2, float f3, float f4, boolean bl) {
        boolean bl2;
        block13: {
            boolean bl3;
            block12: {
                ImDrawList imDrawList;
                boolean bl4;
                block10: {
                    block11: {
                        ImGui.setCursorScreenPos((float)f, (float)f2);
                        ImGui.setNextItemAllowOverlap();
                        bl4 = ImScreen.a;
                        bl3 = ImGui.invisibleButton((String)string, (float)f3, (float)f4);
                        imDrawList = ImGui.getWindowDrawList();
                        bl2 = bl;
                        if (bl4) break block10;
                        if (!bl2) break block11;
                        Ui.fill(imDrawList, f, f2, f + f3, f2 + f4, Ui.col(Menu.ACCENT, 0.12f), 10.0f);
                        Ui.rect(imDrawList, f, f2, f + f3, f2 + f4, Ui.col(Menu.ACCENT, 0.85f), 10.0f);
                        if (!bl4) break block12;
                    }
                    bl2 = ImGui.isItemHovered();
                }
                if (bl4) break block13;
                if (!bl2) break block12;
                Ui.fill(imDrawList, f, f2, f + f3, f2 + f4, Ui.col(Im.QUIET), 10.0f);
            }
            bl2 = bl3;
        }
        return bl2;
    }

    static boolean add(String string, float f, float f2, float f3) {
        boolean bl;
        boolean bl2;
        block8: {
            block9: {
                ImGui.setCursorScreenPos((float)f, (float)f2);
                boolean bl3 = ImScreen.a;
                String[] stringArray = a;
                bl2 = ImGui.invisibleButton((String)stringArray[5], (float)f3, (float)46.0f);
                bl = ImGui.isItemHovered();
                if (bl3) break block8;
                if (!bl) break block9;
                Ui.fill(ImGui.getWindowDrawList(), f, f2, f + f3, f2 + 46.0f, Ui.col(Im.QUIET), 10.0f);
            }
            Edit.dashed(f, f2, f3, 46.0f, 10.0f);
        }
        String string2 = a[1];
        float f4 = f + 12.0f;
        float f5 = f2 + 15.5f;
        float f6 = 15.0f;
        float[] fArray = bl ? Im.INK : Im.SUB;
        Ui.icon(string2, f4, f5, f6, fArray);
        ImFont imFont = Im.sora12r;
        float f7 = f + 39.0f;
        float f8 = Ui.mid(Im.sora12r, f2, 46.0f);
        float[] fArray2 = bl ? Im.INK : Im.SUB;
        Ui.text(imFont, f7, f8, fArray2, string);
        return bl2;
    }

    static void dashed(float f, float f2, float f3, float f4, float f5) {
        float[][] fArrayArray;
        float f6;
        int n;
        boolean bl;
        ImDrawList imDrawList;
        block6: {
            float f7;
            block5: {
                imDrawList = ImGui.getWindowDrawList();
                bl = ImScreen.a;
                n = Ui.wash(0.16f);
                for (f6 = f + f5; f6 < f + f3 - f5; f6 += 7.0f) {
                    if (!bl) {
                        f7 = Math.min(f6 + 4.0f, f + f3 - f5);
                        imDrawList.addLine(f6, f2, f7, f2, n);
                        imDrawList.addLine(f6, f2 + f4, f7, f2 + f4, n);
                        if (!bl) continue;
                    }
                    break block5;
                }
                f6 = f2 + f5;
            }
            while (f6 < f2 + f4 - f5) {
                f7 = Math.min(f6 + 4.0f, f2 + f4 - f5);
                imDrawList.addLine(f, f6, f, f7, n);
                if (!bl) {
                    imDrawList.addLine(f + f3, f6, f + f3, f7, n);
                    f6 += 7.0f;
                    if (!bl) continue;
                }
                break block6;
            }
            f6 = 1.5707964f;
        }
        for (float[] fArray : fArrayArray = new float[][]{{f + f5, f2 + f5, 2.0f}, {f + f3 - f5, f2 + f5, 3.0f}, {f + f3 - f5, f2 + f4 - f5, 0.0f}, {f + f5, f2 + f4 - f5, 1.0f}}) {
            imDrawList.pathArcTo(fArray[0], fArray[1], f5, fArray[2] * f6, (fArray[2] + 1.0f) * f6);
            imDrawList.pathStroke(n, 0, 1.0f);
            if (!bl) continue;
        }
    }

    static void field(String string, String string2, ImString imString, float f, float f2, float f3, boolean bl) {
        Edit.field(string, string2, imString, f, f2, f3, bl, false);
    }

    static void field(String string, String string2, ImString imString, float f, float f2, float f3, boolean bl, boolean n) {
        block18: {
            block19: {
                boolean bl2;
                block17: {
                    int n2;
                    block15: {
                        block16: {
                            ImGui.setCursorScreenPos((float)f, (float)f2);
                            ImGui.setNextItemWidth((float)f3);
                            bl2 = ImScreen.a;
                            n2 = n;
                            if (bl2) break block15;
                            if (n2 == 0) break block16;
                            ImGui.pushStyleVar((int)11, (float)6.8f, (float)((34.0f - Im.mc.getFontSize() / Im.bake()) / 2.0f));
                            if (!bl2) break block17;
                        }
                        n2 = 11;
                    }
                    ImGui.pushStyleVar((int)n2, (float)12.0f, (float)10.5f);
                }
                ImGui.pushStyleVar((int)13, (float)1.0f);
                ImFont imFont = n != 0 ? Im.mc : Im.sub;
                ImGui.pushFont((ImFont)imFont);
                if (bl2) break block18;
                if (!bl) break block19;
                ImGui.setKeyboardFocusHere();
            }
            Im.need(imString.get());
            ImGui.inputTextWithHint((String)string, (String)string2, (ImString)imString);
            ImGui.popFont();
            ImGui.popStyleVar((int)2);
        }
        if (ImGui.isItemActive()) {
            Ui.rim(f, f2, f3, 34.0f, 9.0f, Menu.ICON_TOP, Menu.ICON_FOOT);
        }
    }

    static float buttonWidth(String string, boolean n) {
        int n2;
        float f;
        block2: {
            block3: {
                boolean bl = ImScreen.a;
                f = Ui.width(Im.sora12r, string) + 24.0f;
                n2 = n;
                if (bl) break block2;
                if (n2 == 0) break block3;
                n2 = 19;
                break block2;
            }
            n2 = 0;
        }
        return f + (float)n2;
    }

    
    static boolean button(String var0, String var1_1, float var2_2, float var3_3, boolean var4_4) {
        block30: {
            block29: {
                block28: {
                    block26: {
                        block27: {
                            block23: {
                                block25: {
                                    block24: {
                                        block22: {
                                            block21: {
                                                var5_5 = ImScreen.a;
                                                v0 = var0;
                                                v1 = var1_1 != null;
                                                var6_6 = Edit.buttonWidth(v0, v1);
                                                var7_7 = var2_2 - var6_6;
                                                ImGui.setCursorScreenPos((float)var7_7, (float)var3_3);
                                                var8_8 = ImGui.invisibleButton((String)var0, (float)var6_6, (float)28.0f);
                                                var9_9 = ImGui.isItemHovered();
                                                var10_10 = ImGui.getWindowDrawList();
                                                if (var4_4) {
                                                    v3 = var10_10;
                                                    v4 = var7_7;
                                                    v5 = var3_3;
                                                    v6 = var7_7 + var6_6;
                                                    v7 = var3_3 + 28.0f;
                                                    if (!var9_9) break block21;
                                                }
                                                v9 = Menu.ICON_FOOT;
                                                break block22;
                                            }
                                            v9 = Menu.FILL;
                                        }
                                        Ui.accent(v3, v4, v5, v6, v7, Ui.col(v9), 8.0f);
                                        if (!var5_5) break block23;
lbl37:

                                        v11 = var10_10;
                                        v12 = var7_7;
                                        v13 = var3_3;
                                        v14 = var7_7 + var6_6;
                                        v15 = var3_3 + 28.0f;
                                        if (!var9_9) break block24;
                                        v17 = Im.QUIET_HI;
                                        break block25;
                                    }
                                    v17 = Im.QUIET;
                                }
                                Ui.fill(v11, v12, v13, v14, v15, Ui.col(v17), 8.0f);
                                Ui.rect(var10_10, var7_7, var3_3, var7_7 + var6_6, var3_3 + 28.0f, Ui.wash(0.16f), 8.0f);
                            }
                            v19 = var4_4;
                            if (var5_5) break block26;
                            if (!v19) break block27;
                            v21 = Im.ON_FILL;
                            break block28;
                        }
                        v19 = var9_9;
                    }
                    v21 = v19 != false ? Im.INK : Im.SUB;
                }
                var11_11 = v21;
                v24 = var1_1;
                if (var5_5) break block29;
                if (v24 == null) break block30;
                v24 = var1_1;
            }
            Ui.icon(v24, var7_7 + 12.0f, var3_3 + 8.0f, 12.0f, var11_11);
        }
        v26 = Im.sora12r;
        v27 = var7_7 + 12.0f;
        v28 = var1_1 != null ? 19 : 0;
        Ui.text(v26, v27 + (float)v28, Ui.mid(Im.sora12r, var3_3, 28.0f), var11_11, var0);
        return var8_8;
    }

    static void group(float f, float f2, float f3, int n) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        Ui.fill(imDrawList, f, f2, f + f3, f2 + (float)n * 34.0f, Ui.wash(0.03f), 8.0f);
        Ui.rect(imDrawList, f, f2, f + f3, f2 + (float)n * 34.0f, Ui.wash(0.08f), 8.0f);
        boolean bl = ImScreen.a;
        for (int i = 1; i < n; ++i) {
            imDrawList.addLine(f, f2 + (float)i * 34.0f, f + f3, f2 + (float)i * 34.0f, Ui.wash(0.06f));
            if (!bl) continue;
        }
    }

    static void name(String string, float f, float f2) {
        Ui.text(Im.sora12, f + 12.0f, Ui.mid(Im.sora12, f2, 34.0f), Im.INK, string);
    }

    static float slider(String string, float f, float f2, float f3, float f4) {
        block5: {
            float f5;
            block4: {
                boolean bl = ImScreen.a;
                f5 = f;
                if (bl) break block4;
                ImGui.setCursorScreenPos((float)f5, (float)f2);
                ImGui.invisibleButton((String)string, (float)f3, (float)18.0f);
                if (!ImGui.isItemActive()) break block5;
                f5 = Math.max(0.0f, Math.min(1.0f, (ImGui.getMousePosX() - f - 5.0f) / (f3 - 10.0f)));
            }
            f4 = f5;
        }
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        float f6 = f + 5.0f + f4 * (f3 - 10.0f);
        Ui.fill(imDrawList, f, f2 + 7.0f, f + f3, f2 + 11.0f, Ui.wash(0.12f), 2.0f);
        Ui.accent(imDrawList, f, f2 + 7.0f, f6, f2 + 11.0f, Ui.col(Menu.FILL), 2.0f);
        imDrawList.addCircleFilled(f6, f2 + 9.0f, 5.0f, ImGui.getColorU32((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f), 16);
        return f4;
    }

    static void value(String string, float f, float f2) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        Ui.fill(imDrawList, f - 40.0f, f2, f, f2 + 20.0f, Ui.col(Im.QUIET), 5.0f);
        Ui.rect(imDrawList, f - 40.0f, f2, f, f2 + 20.0f, Ui.wash(0.16f), 5.0f);
        Ui.text(Im.label, f - 20.0f - (float)Math.floor(Ui.width(Im.label, string) / 2.0f), Ui.mid(Im.label, f2, 20.0f), Im.INK, string);
    }

    static boolean swatch(String string, float f, float f2, float f3, int n, boolean bl) {
        int n2;
        float f4;
        float f5;
        float f6;
        float f7;
        ImDrawList imDrawList;
        boolean bl2;
        block6: {
            float f8;
            block4: {
                block5: {
                    ImGui.setCursorScreenPos((float)f, (float)f2);
                    bl2 = ImGui.invisibleButton((String)string, (float)f3, (float)f3);
                    ImDrawList imDrawList2 = ImGui.getWindowDrawList();
                    boolean bl3 = ImScreen.a;
                    imDrawList = imDrawList2;
                    f7 = f;
                    f6 = f2;
                    f5 = f + f3;
                    f4 = f2;
                    f8 = f3;
                    if (bl3) break block4;
                    imDrawList.addRectFilled(f7, f6, f5, f4 + f8, Edit.u32(n), 4.0f);
                    imDrawList = imDrawList2;
                    f7 = f;
                    f6 = f2;
                    f5 = f + f3;
                    f4 = f2 + f3;
                    if (!bl) break block5;
                    n2 = Ui.col(Menu.ACCENT);
                    break block6;
                }
                f8 = 0.16f;
            }
            n2 = Ui.wash(f8);
        }
        Ui.rect(imDrawList, f7, f6, f5, f4, n2, 4.0f);
        return bl2;
    }

    static int u32(int n) {
        return ImGui.getColorU32((float)((float)(n >> 16 & 0xFF) / 255.0f), (float)((float)(n >> 8 & 0xFF) / 255.0f), (float)((float)(n & 0xFF) / 255.0f), (float)1.0f);
    }

    static void popup() {
        ImGui.pushStyleVar((int)9, (float)8.0f);
        ImGui.pushStyleColor((int)4, (float)Im.POP[0], (float)Im.POP[1], (float)Im.POP[2], (float)Im.POP[3]);
    }

    static void unpopup() {
        ImGui.popStyleColor();
        ImGui.popStyleVar();
    }

    static int dropdown(String string, String string2, String[] stringArray, int n, float f, float f2, float f3) {
        int n2;
        block36: {
            int n3;
            block38: {
                block37: {
                    ImDrawList imDrawList;
                    boolean bl;
                    float f4;
                    float f5;
                    block35: {
                        int n4;
                        float f6;
                        float f7;
                        float f8;
                        float f9;
                        ImDrawList imDrawList2;
                        block33: {
                            block34: {
                                block32: {
                                    String string3;
                                    block31: {
                                        f5 = 96.0f;
                                        f4 = f - f5;
                                        ImGui.setCursorScreenPos((float)f4, (float)f2);
                                        bl = ImScreen.a;
                                        string3 = string;
                                        if (bl) break block31;
                                        if (!ImGui.invisibleButton((String)string3, (float)f5, (float)20.0f)) break block32;
                                        string3 = string;
                                    }
                                    ImGui.openPopup((String)string3);
                                }
                                imDrawList = ImGui.getWindowDrawList();
                                imDrawList2 = imDrawList;
                                f9 = f4;
                                f8 = f2;
                                f7 = f4 + f5;
                                f6 = f2 + 20.0f;
                                float[] fArray = ImGui.isItemHovered() ? Im.QUIET_HI : Im.QUIET;
                                n4 = Ui.col(fArray);
                                if (bl) break block33;
                                Ui.fill(imDrawList2, f9, f8, f7, f6, n4, 5.0f);
                                if (!ImGui.isPopupOpen((String)string)) break block34;
                                Ui.rim(f4, f2, f5, 20.0f, 5.0f, Menu.ICON_TOP, Menu.ICON_FOOT);
                                if (!bl) break block35;
                            }
                            imDrawList2 = imDrawList;
                            f9 = f4;
                            f8 = f2;
                            f7 = f4 + f5;
                            f6 = f2 + 20.0f;
                            n4 = Ui.wash(0.16f);
                        }
                        Ui.rect(imDrawList2, f9, f8, f7, f6, n4, 5.0f);
                    }
                    imDrawList.pushClipRect(f4, f2, f4 + f5 - 18.0f, f2 + 20.0f, true);
                    Ui.text(Im.label, f4 + 8.0f, Ui.mid(Im.label, f2, 20.0f), Im.INK, string2);
                    imDrawList.popClipRect();
                    imDrawList.addLine(f4 + f5 - 14.0f, f2 + 8.5f, f4 + f5 - 11.0f, f2 + 11.5f, Ui.col(Im.SUB), 1.0f);
                    imDrawList.addLine(f4 + f5 - 11.0f, f2 + 11.5f, f4 + f5 - 8.0f, f2 + 8.5f, Ui.col(Im.SUB), 1.0f);
                    n3 = -1;
                    ImGui.setNextWindowPos((float)(f - f3), (float)(f2 + 23.0f));
                    ImGui.setNextWindowSize((float)f3, (float)0.0f);
                    Edit.popup();
                    ImGui.pushStyleVar((int)2, (float)4.0f, (float)4.0f);
                    ImGui.pushStyleVar((int)14, (float)0.0f, (float)2.0f);
                    ImGui.pushStyleVar((int)11, (float)7.0f, (float)4.0f);
                    n2 = 24;
                    if (bl) break block36;
                    ImGui.pushStyleColor((int)n2, (float)1.0f, (float)1.0f, (float)1.0f, (float)0.1f);
                    ImGui.pushStyleColor((int)25, (float)1.0f, (float)1.0f, (float)1.0f, (float)0.05f);
                    ImGui.pushStyleColor((int)26, (float)1.0f, (float)1.0f, (float)1.0f, (float)0.1f);
                    ImGui.pushFont((ImFont)Im.label);
                    if (!ImGui.beginPopup((String)string)) break block37;
                    Ui.lift();
                    for (int i = 0; i < stringArray.length; ++i) {
                        int n5;
                        block41: {
                            block42: {
                                int n6;
                                Object object;
                                block39: {
                                    block40: {
                                        if (bl) break block38;
                                        object = stringArray[i];
                                        n6 = i;
                                        if (!bl) {
                                        }
                                        break block39;
                                        object = " " + (String)object + a[4] + n6;
                                        if (i != n) break block40;
                                        n6 = 1;
                                        break block39;
                                    }
                                    n6 = 0;
                                }
                                n5 = Ui.option((String)object, n6 != 0);
                                if (bl) break block41;
                                if (n5 == 0) break block42;
                                n5 = i;
                                if (bl) break block41;
                                if (n5 == n) break block42;
                                n3 = i;
                            }
                            n5 = i;
                        }
                        if (n5 != n) {
                            continue;
                        }
                        float f10 = ImGui.getItemRectMaxX() - 12.0f;
                        float f11 = (ImGui.getItemRectMinY() + ImGui.getItemRectMaxY()) / 2.0f;
                        Menu.tick(f10, f11, 3.5f, Ui.col(Menu.ACCENT));
                        if (!bl) continue;
                    }
                    ImGui.endPopup();
                }
                ImGui.popFont();
                ImGui.popStyleColor((int)3);
                ImGui.popStyleVar((int)3);
                Edit.unpopup();
            }
            n2 = n3;
        }
        return n2;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "\u0019\u0015PzzJ \u0004$\u0016A|\u00057\bAbt\u00046\u001bWd\u0002wY";
                var4_3 = "\u0019\u0015PzzJ \u0004$\u0016A|\u00057\bAbt\u00046\u001bWd\u0002wY".length();
                var1_4 = 7;
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
                    var2_2 = "5\u001eP\u0007\u0019\u0015PzzJ ";
                    var4_3 = "5\u001eP\u0007\u0019\u0015PzzJ ".length();
                    var1_4 = 3;
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
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 84;
                        break;
                    }
                    case 1: {
                        v10 = 122;
                        break;
                    }
                    case 2: {
                        v10 = 52;
                        break;
                    }
                    case 3: {
                        v10 = 15;
                        break;
                    }
                    case 4: {
                        v10 = 22;
                        break;
                    }
                    case 5: {
                        v10 = 47;
                        break;
                    }
                    default: {
                        v10 = 83;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_6;
lbl65:

                v7 = v7;
            } while (v7 > var6_6);
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
        Edit.a = var5;
    }
}
