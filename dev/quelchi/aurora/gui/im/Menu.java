package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Mc;
import dev.quelchi.aurora.gui.im.ModsScreen;
import dev.quelchi.aurora.gui.im.Motion;
import dev.quelchi.aurora.gui.im.PlusScreen;
import dev.quelchi.aurora.gui.im.ProfilesScreen;
import dev.quelchi.aurora.gui.im.SettingsScreen;
import dev.quelchi.aurora.gui.im.SocialScreen;
import dev.quelchi.aurora.gui.im.Themes;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.gui.lq.HudBridge;
import dev.quelchi.aurora.gui.shell.LockerScreen;
import dev.quelchi.aurora.gui.shell.RewardsBadge;
import dev.quelchi.aurora.social.Plus;
import dev.quelchi.aurora.social.WorldShare;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.type.ImString;
import java.util.Locale;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public final class Menu {
    public static final float W = 1066.0f;
    public static final float H = 611.0f;
    public static final float SIDE = 144.0f;
    public static final float TOP = 56.0f;
    public static final float[] ACCENT;
    public static final float[] FILL;
    public static final float[] ICON_TOP;
    public static final float[] ICON_FOOT;
    public static final float[] GRAD_A;
    public static final float[] GRAD_B;
    public static final float[] KNOB_ON;
    public static final float[] SOFT;
    public static final float[] CHIP_TO;
    public static final float[] SIDE_BG;
    public static float x;
    public static float y;
    public static float w;
    public static float h;
    public static final float BAR = 64.0f;
    private static final String[] PAGES_C;
    private static final float R = 18.0f;
    public static float fade;
    private static boolean leaving;
    private static long last;
    private static final String[] PAGES;
    private static final float NAV = 32.0f;
    private static final float PITCH = 36.0f;
    private static final String[] a;

    private Menu() {
    }

    public static void begin(String string, float f) {
        block18: {
            MinecraftClient minecraftClient;
            block19: {
                boolean bl;
                boolean bl2;
                MinecraftClient minecraftClient2;
                block16: {
                    float f2;
                    float f3;
                    float f4;
                    block17: {
                        float f5;
                        ImDrawList imDrawList;
                        block14: {
                            block15: {
                                minecraftClient2 = MinecraftClient.method_1551();
                                bl2 = ImScreen.a;
                                Menu.window(f, 56.0f);
                                imDrawList = ImGui.getWindowDrawList();
                                f5 = SIDE_BG[3];
                                if (bl2) break block14;
                                if (!(f5 > 0.0f)) break block15;
                                imDrawList.addRectFilled(x, y, x + 144.0f, y + h, Ui.col(SIDE_BG), 18.0f, 80);
                            }
                            Ui.box(imDrawList, x, y, x + w, y + h, null, Im.WIN_LINE, 18.0f, 1.0f);
                            imDrawList.addLine(x + 144.0f, y, x + 144.0f, y + h, Ui.col(Im.LINE));
                            imDrawList.addLine(x + 144.0f, y + 56.0f, x + w, y + 56.0f, Ui.col(Im.LINE));
                            Menu.side(string);
                            f5 = x + w - 45.0f;
                        }
                        f4 = f5;
                        f3 = y + 11.0f;
                        ImGui.setCursorScreenPos((float)f4, (float)f3);
                        String[] stringArray = a;
                        bl = ImGui.invisibleButton((String)stringArray[25], (float)34.0f, (float)34.0f);
                        f2 = Ui.hot();
                        Ui.box(imDrawList, f4, f3, f4 + 34.0f, f3 + 34.0f, Im.FIELD, Im.FIELD_LINE, 8.0f, 1.0f);
                        if (bl2) break block16;
                        if (!(f2 > 0.01f)) break block17;
                        imDrawList.addRectFilled(f4, f3, f4 + 34.0f, f3 + 34.0f, Ui.wash(0.06f * f2), 8);
                    }
                    Ui.icon(a[4], f4 + 9.5f, f3 + 9.5f, 15.0f, Ui.mix(Im.SUB, Im.INK, f2));
                }
                if (!bl) break block18;
                minecraftClient = minecraftClient2;
                if (bl2) break block19;
                if (minecraftClient.field_1755 == null) break block18;
                minecraftClient = minecraftClient2;
            }
            minecraftClient.field_1755.method_25419();
        }
    }

    public static int top(String string, String[] stringArray, int n, float f) {
        return Menu.top(string, stringArray, null, n, f);
    }

    public static int top(String string, String[] stringArray, int[] nArray, int n, float f) {
        int n2;
        block81: {
            float f2;
            boolean bl;
            ImDrawList imDrawList;
            MinecraftClient minecraftClient;
            block90: {
                String[] stringArray2;
                block63: {
                    block64: {
                        block89: {
                            minecraftClient = MinecraftClient.method_1551();
                            Menu.window(f, 64.0f);
                            imDrawList = ImGui.getWindowDrawList();
                            bl = ImScreen.a;
                            Ui.box(imDrawList, x, y, x + w, y + h, null, Im.WIN_LINE, 18.0f, 1.0f);
                            imDrawList.addLine(x, y + 64.0f, x + w, y + 64.0f, Ui.col(Im.LINE));
                            if (!Plus.active()) break block89;
                            f2 = Menu.plusWord(x + 24.0f, y + 21.0f, 140.0f);
                            if (!bl) break block90;
                        }
                        stringArray2 = a;
                        long l = Ui.tex(Identifier.method_60655((String)stringArray2[12], (String)stringArray2[32]));
                        if (bl) break block63;
                        if (l == 0L) break block64;
                        imDrawList.addImage(l, x + 24.0f, y + 21.0f, x + 46.0f, y + 43.0f, 0.0f, 0.0f, 1.0f, 1.0f, Ui.col(Im.INK));
                    }
                    stringArray2 = a;
                    Ui.text(Im.word, x + 56.0f, Ui.mid(Im.word, y, 64.0f), Im.INK, stringArray2[22]);
                }
                stringArray2 = a;
                f2 = x + 56.0f + Ui.width(Im.word, stringArray2[22]);
            }
            int n3 = -1;
            f2 += 32.0f;
            for (int i = 0; i < stringArray.length; ++i) {
                int n4;
                float f3;
                block76: {
                    int n5;
                    block77: {
                        String string2;
                        float f4;
                        block80: {
                            int n6;
                            block78: {
                                int n7;
                                block79: {
                                    int n8;
                                    block74: {
                                        block75: {
                                            block72: {
                                                block73: {
                                                    float f5;
                                                    block91: {
                                                        block68: {
                                                            String string3;
                                                            ImFont imFont;
                                                            float f6;
                                                            block71: {
                                                                int n9;
                                                                block69: {
                                                                    block70: {
                                                                        int n10;
                                                                        block67: {
                                                                            int[] nArray2;
                                                                            block65: {
                                                                                block66: {
                                                                                    nArray2 = nArray;
                                                                                    if (bl) break block65;
                                                                                    if (nArray2 != null) break block66;
                                                                                    n10 = 0;
                                                                                    break block67;
                                                                                }
                                                                                nArray2 = nArray;
                                                                            }
                                                                            n10 = nArray2[i];
                                                                        }
                                                                        n7 = n10;
                                                                        if (n7 <= 0) break block68;
                                                                        f6 = 16.0f;
                                                                        imFont = Im.label;
                                                                        n9 = n7;
                                                                        if (bl) break block69;
                                                                        if (n9 <= 9) break block70;
                                                                        string3 = a[28];
                                                                        break block71;
                                                                    }
                                                                    n9 = n7;
                                                                }
                                                                string3 = String.valueOf(n9);
                                                            }
                                                            f5 = Math.max(f6, Ui.width(imFont, string3) + 10.0f);
                                                            break block91;
                                                        }
                                                        f5 = 0.0f;
                                                    }
                                                    f4 = f5;
                                                    float f8 = Ui.width(Im.sora14, stringArray[i]);
                                                    f8 = n7 > 0 ? 6.0f + f4 : 0.0f;
                                                    f3 = f7 + f8;
                                                    ImGui.setCursorScreenPos((float)(f2 - 4.0f), (float)y);
                                                    n8 = i;
                                                    if (bl) break block72;
                                                    if (!ImGui.invisibleButton((String)(a[9] + n8), (float)(f3 + 8.0f), (float)64.0f)) break block73;
                                                    n8 = i;
                                                    if (bl) break block72;
                                                    if (n8 == n) break block73;
                                                    n3 = i;
                                                }
                                                n8 = i;
                                            }
                                            if (bl) break block74;
                                            if (n8 != n) break block75;
                                            n8 = 1;
                                            break block74;
                                        }
                                        n8 = 0;
                                    }
                                    n5 = n8;
                                    ImFont imFont = Im.sora14;
                                    float f10 = f2;
                                    f10 = Ui.mid(Im.sora14, y, 64.0f);
                                    float[] fArray = n5 != 0 ? Im.INK : Ui.mix(Im.MUTE, Im.SUB, Ui.hot());
                                    Ui.text(imFont, f9, f10, fArray, stringArray[i]);
                                    n4 = n7;
                                    if (bl) break block76;
                                    if (n4 <= 0) break block77;
                                    n6 = n7;
                                    if (bl) break block78;
                                    if (n6 <= 9) break block79;
                                    string2 = a[28];
                                    break block80;
                                }
                                n6 = n7;
                            }
                            string2 = String.valueOf(n6);
                        }
                        String string4 = string2;
                        float f11 = f2 + f3 - f4;
                        float f12 = y + 32.0f - 8.0f;
                        Ui.accent(imDrawList, f11, f12, f11 + f4, f12 + 16.0f, Ui.col(FILL), 8.0f);
                        Ui.text(Im.label, f11 + (float)Math.floor((f4 - Ui.width(Im.label, string4)) / 2.0f), Ui.mid(Im.label, f12, 16.0f), Im.ON_FILL, string4);
                    }
                    n4 = n5;
                }
                if (n4 != 0) {
                    Ui.underline(a[1] + string, x, f2 - x, f3, y + 64.0f);
                }
                f2 += f3 + 28.0f;
                if (!bl) continue;
            }
            float f13 = x + w - 24.0f - (float)(PAGES_C.length * 34) - (float)((PAGES_C.length - 1) * 4);
            float f14 = y + 15.0f;
            imDrawList.addLine(f13 - 10.5f, f14 + 7.0f, f13 - 10.5f, f14 + 27.0f, Ui.col(Im.FIELD_LINE));
            if (Menu.pageIcon(a[40], a[26], f13 - 55.0f, f14, false)) {
                minecraftClient.method_1507((Screen)ModsScreen.find());
            }
            for (String string4 : PAGES_C) {
                int n11;
                block87: {
                    block88: {
                        String string5;
                        block85: {
                            block86: {
                                int n12;
                                block84: {
                                    block82: {
                                        block83: {
                                            n2 = string4.equals(string) ? 1 : 0;
                                            if (bl) break block81;
                                            int n13 = n2;
                                            string5 = string4.toLowerCase(Locale.ROOT);
                                            if (bl) break block82;
                                            if (!Menu.pageIcon(a[37] + string5, a[18] + string4, f13, f14, n13 != 0)) break block83;
                                            n12 = n13;
                                            if (bl) break block84;
                                            if (n12 != 0) break block83;
                                            Menu.go(string4);
                                        }
                                        string5 = string4;
                                    }
                                    if (bl) break block85;
                                    n12 = string5.equals(a[5]);
                                }
                                if (n12 == 0) break block86;
                                n11 = RewardsBadge.ready();
                                break block87;
                            }
                            string5 = string4;
                        }
                        n11 = string5.equals(a[10]);
                        if (bl) break block87;
                        if (n11 == 0) break block88;
                        n11 = WorldShare.invites().size();
                        break block87;
                    }
                    n11 = 0;
                }
                int n14 = n11;
                float f12 = f13 + 30.0f;
                f12 = f14 + 4.0f;
                int n13 = n14;
                float[] fArray = string4.equals(a[5]) ? Im.WARN : Im.GREEN;
                Menu.badge(f11, f12, n13, fArray);
                f13 += 38.0f;
                if (!bl) continue;
            }
            n2 = n3;
        }
        return n2;
    }

    
    private static void go(String string) {
        int n;
        boolean bl;
        MinecraftClient minecraftClient;
        block35: {
            minecraftClient = MinecraftClient.method_1551();
            String string2 = string;
            bl = ImScreen.a;
            int n2 = -1;
            n = string2.hashCode();
            if (bl) break block35;
            switch (n) {
                case -1402880153: {
                    n = string2.equals(a[15]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 0;
                        if (!bl) break;
                    }
                    break block35;
                }
                case -1813183603: {
                    n = string2.equals(a[10]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 1;
                        if (!bl) break;
                    }
                    break block35;
                }
                case 553768924: {
                    n = string2.equals(a[5]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 2;
                        if (!bl) break;
                    }
                    break block35;
                }
                case -937619446: {
                    n = string2.equals(a[16]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 3;
                        if (!bl) break;
                    }
                    break block35;
                }
                case 1499275331: {
                    n = string2.equals(a[33]) ? 1 : 0;
                    if (bl) break block35;
                    if (n == 0) break;
                    n2 = 4;
                }
            }
            n = n2;
        }
        switch (n) {
            case 0: {
                minecraftClient.method_1507((Screen)new ModsScreen());
                if (!bl) return;
            }
            case 1: {
                minecraftClient.method_1507((Screen)new SocialScreen());
                if (!bl) return;
            }
            case 2: {
                minecraftClient.method_1507((Screen)new LockerScreen());
                if (!bl) return;
            }
            case 3: {
                minecraftClient.method_1507((Screen)new ProfilesScreen());
                if (!bl) return;
            }
            case 4: {
                minecraftClient.method_1507((Screen)new SettingsScreen());
                if (!bl) return;
            }
        }
        minecraftClient.method_1507((Screen)new PlusScreen());
    }

    private static boolean pageIcon(String string, String string2, float f, float f2, boolean n) {
        float f3;
        boolean bl;
        block15: {
            int n2;
            ImDrawList imDrawList;
            block13: {
                float f4;
                block14: {
                    ImGui.setCursorScreenPos((float)f, (float)f2);
                    boolean bl2 = ImScreen.a;
                    bl = ImGui.invisibleButton((String)string2, (float)34.0f, (float)34.0f);
                    float f5 = n != 0 ? 0.0f : Ui.hot();
                    f3 = f5;
                    imDrawList = ImGui.getWindowDrawList();
                    n2 = n;
                    if (bl2) break block13;
                    if (n2 == 0) break block14;
                    imDrawList.addRectFilled(f, f2, f + 34.0f, f2 + 34.0f, Ui.wash(0.08f), 10.0f);
                    if (!bl2) break block15;
                }
                n2 = (f4 = f3 - 0.01f) == 0.0f ? 0 : (f4 > 0.0f ? 1 : -1);
            }
            if (n2 > 0) {
                imDrawList.addRectFilled(f, f2, f + 34.0f, f2 + 34.0f, Ui.wash(0.05f * f3), 10.0f);
            }
        }
        String string3 = string;
        float f6 = f + 9.0f;
        float f7 = f2 + 9.0f;
        float f8 = 16.0f;
        float[] fArray = n != 0 ? Im.INK : Ui.mix(Im.MUTE, Im.SUB, f3);
        Ui.icon(string3, f6, f7, f8, fArray);
        return bl;
    }

    public static boolean ghost(String string, String string2, float f, float f2, float f3) {
        boolean bl;
        block16: {
            boolean bl2;
            block17: {
                String string3;
                float f4;
                float[] fArray;
                String string4;
                boolean bl3;
                block12: {
                    block13: {
                        int n;
                        int n2;
                        float f5;
                        block14: {
                            block15: {
                                String[] stringArray = a;
                                int n3 = string.indexOf(stringArray[24]);
                                bl3 = ImScreen.a;
                                String string5 = n3 < 0 ? string : string.substring(0, n3);
                                string4 = string5;
                                float f6 = Menu.ghostWidth(string4, string2);
                                float f7 = f - f6;
                                ImGui.setCursorScreenPos((float)f7, (float)f2);
                                String string6 = string2;
                                String string7 = string;
                                bl2 = ImGui.invisibleButton((String)(a[6] + string7 + string6), (float)f6, (float)f3);
                                float f8 = Ui.hot();
                                fArray = Ui.mix(Im.MUTE, Im.INK, f8);
                                f4 = f7 + 6.0f;
                                string3 = string2;
                                if (bl3) break block12;
                                if (string3 == null) break block13;
                                Ui.icon(string2, f4, f2 + (f3 - 14.0f) / 2.0f, 14.0f, fArray);
                                f5 = f4;
                                int n = 14;
                                n = string4.isEmpty();
                                if (bl3) break block14;
                                if (n == 0) break block15;
                                n = 0;
                                break block14;
                            }
                            n = 7;
                        }
                        f4 = f5 + (float)(n2 + n);
                    }
                    string3 = string4;
                }
                bl = string3.isEmpty();
                if (bl3) break block16;
                if (bl) break block17;
                Ui.text(Im.sub, f4, Ui.mid(Im.sub, f2, f3), fArray, string4);
            }
            bl = bl2;
        }
        return bl;
    }

    public static float ghostWidth(String string, String string2) {
        int n;
        int n2;
        String string3;
        block12: {
            block11: {
                String string4;
                boolean bl;
                block10: {
                    int n3 = string.indexOf(a[24]);
                    bl = ImScreen.a;
                    String string5 = n3 < 0 ? string : string.substring(0, n3);
                    string3 = string5;
                    int n2 = 12;
                    n2 = string2 != null ? 14 : 0;
                    n2 = n4 + n2;
                    string4 = string2;
                    if (bl) break block10;
                    if (string4 == null) break block11;
                    string4 = string3;
                }
                n = string4.isEmpty();
                if (bl) break block12;
                if (n != 0) break block11;
                n = 7;
                break block12;
            }
            n = 0;
        }
        float f2 = n2 + n;
        f2 = string3.isEmpty() ? 0.0f : Ui.width(Im.sub, string3);
        return f + f2;
    }

    private static void window(float f, float f2) {
        float f3;
        float f4;
        ImDrawList imDrawList;
        float[] fArray;
        block55: {
            float f5;
            block53: {
                block54: {
                    boolean bl;
                    block52: {
                        float f6;
                        block50: {
                            block51: {
                                ImDrawList imDrawList2;
                                block49: {
                                    int n;
                                    block47: {
                                        block48: {
                                            float f7;
                                            float f8;
                                            block45: {
                                                float f9;
                                                float f10;
                                                block46: {
                                                    float f11;
                                                    block42: {
                                                        block43: {
                                                            MinecraftClient minecraftClient;
                                                            block44: {
                                                                boolean bl2;
                                                                String string;
                                                                MinecraftClient minecraftClient2;
                                                                block40: {
                                                                    block41: {
                                                                        block38: {
                                                                            block39: {
                                                                                bl = ImScreen.a;
                                                                                minecraftClient2 = MinecraftClient.method_1551();
                                                                                if (bl) break block38;
                                                                                if (Im.frames() - last <= 1L) break block39;
                                                                                leaving = false;
                                                                                Motion.to(a[47], false, 0.0f);
                                                                            }
                                                                            last = Im.frames();
                                                                        }
                                                                        string = a[29];
                                                                        bl2 = leaving;
                                                                        if (bl) break block40;
                                                                        if (bl2) break block41;
                                                                        bl2 = true;
                                                                        break block40;
                                                                    }
                                                                    bl2 = false;
                                                                }
                                                                float f12 = leaving ? 0.15f : 0.2f;
                                                                f11 = Motion.to(string, bl2, f12);
                                                                if (bl) break block42;
                                                                fade = f11;
                                                                if (!leaving) break block43;
                                                                f11 = fade;
                                                                if (bl) break block42;
                                                                if (!(f11 <= 0.0f)) break block43;
                                                                minecraftClient = minecraftClient2;
                                                                if (bl) break block44;
                                                                if (minecraftClient.field_1755 == null) break block43;
                                                                minecraftClient = minecraftClient2;
                                                            }
                                                            minecraftClient.field_1755.method_25419();
                                                        }
                                                        f11 = ImGui.getIO().getDisplaySizeX();
                                                    }
                                                    f10 = f11;
                                                    f9 = ImGui.getIO().getDisplaySizeY();
                                                    f8 = f;
                                                    f7 = 0.0f;
                                                    if (bl) break block45;
                                                    if (!(f8 <= f7)) break block46;
                                                    ImGui.getBackgroundDrawList().addRectFilled(0.0f, 0.0f, f10, f9, Ui.col(Im.DIM, 0.62f * fade));
                                                }
                                                ImGui.setNextWindowPos((float)0.0f, (float)0.0f);
                                                ImGui.setNextWindowSize((float)f10, (float)f9);
                                                ImGui.pushStyleVar((int)2, (float)0.0f, (float)0.0f);
                                                ImGui.begin((String)a[29], (int)8367);
                                                ImGui.popStyleVar();
                                                ImGui.pushStyleVar((int)0, (float)Math.max(0.02f, fade));
                                                w = Math.min(1066.0f, f10 - 40.0f);
                                                h = Math.min(611.0f, f9 - 40.0f);
                                                x = (f10 - w) / 2.0f;
                                                f8 = (f9 - h) / 2.0f;
                                                f7 = 16.0f * (1.0f - fade);
                                            }
                                            y = f8 + f7;
                                            imDrawList2 = ImGui.getWindowDrawList();
                                            n = Menu.bg();
                                            if (bl) break block47;
                                            if (!(f <= 0.0f)) break block48;
                                            imDrawList2.addRectFilled(x, y, x + w, y + h, n, 18.0f);
                                            if (!bl) break block49;
                                        }
                                        imDrawList2.addRectFilled(x, y, x + f, y + h, n, 18.0f, 80);
                                        imDrawList2.addRectFilled(x + f, y, x + w, y + f2, n, 18.0f, 32);
                                    }
                                    imDrawList2.addRectFilled(x + w - 18.0f, y + f2, x + w, y + h, n, 18.0f, 128);
                                }
                                fArray = Themes.glow();
                                imDrawList = imDrawList2;
                                f6 = fArray[0];
                                if (bl) break block50;
                                if (!(f6 < 0.0f)) break block51;
                                f4 = x + w + fArray[0];
                                break block52;
                            }
                            f6 = x;
                        }
                        f4 = f6 + fArray[0];
                    }
                    f5 = fArray[1];
                    if (bl) break block53;
                    if (!(f5 < 0.0f)) break block54;
                    f3 = y + h + fArray[1];
                    break block55;
                }
                f5 = y;
            }
            f3 = f5 + fArray[1];
        }
        Ui.glow(imDrawList, f4, f3, fArray[2], fArray[3], fArray, x, y, w, h, 18.0f);
    }

    static int bg() {
        return Ui.col(Im.WINDOW);
    }

    public static void end() {
        ImGui.popStyleVar();
        ImGui.end();
    }

    static boolean leave() {
        int n;
        block12: {
            block13: {
                long l;
                block10: {
                    block11: {
                        boolean bl = ImScreen.a;
                        long l2 = Im.frames() - last;
                        l = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                        if (bl) break block10;
                        if (l != false) break block11;
                        n = leaving;
                        if (bl) break block12;
                        if (n == 0) break block13;
                        float f = fade - 0.0f;
                        n = f == 0.0f ? 0 : (f < 0.0f ? -1 : 1);
                        if (bl) break block12;
                        if (n > 0) break block13;
                    }
                    l = 0;
                }
                return (boolean)l;
            }
            leaving = true;
            n = 1;
        }
        return n != 0;
    }

    private static void side(String string) {
        block67: {
            float f;
            float f2;
            float f3;
            float f4;
            String[] stringArray;
            ImDrawList imDrawList;
            block66: {
                float f5;
                boolean bl;
                block62: {
                    block63: {
                        float f6;
                        block60: {
                            block61: {
                                float f7;
                                String string2;
                                MinecraftClient minecraftClient;
                                block58: {
                                    float f8;
                                    block59: {
                                        float f9;
                                        block65: {
                                            String string3;
                                            float f10;
                                            block56: {
                                                block57: {
                                                    block54: {
                                                        block55: {
                                                            block52: {
                                                                block53: {
                                                                    block64: {
                                                                        block51: {
                                                                            block50: {
                                                                                minecraftClient = MinecraftClient.method_1551();
                                                                                bl = ImScreen.a;
                                                                                imDrawList = ImGui.getWindowDrawList();
                                                                                if (bl) break block50;
                                                                                if (!Plus.active()) break block51;
                                                                                Menu.plusWord(x + 16.0f, y + 17.0f, 87.0f);
                                                                            }
                                                                            if (!bl) break block64;
                                                                        }
                                                                        stringArray = a;
                                                                        long l = Ui.tex(Identifier.method_60655((String)stringArray[36], (String)stringArray[17]));
                                                                        if (l != 0L) {
                                                                            imDrawList.addImage(l, x + 16.0f, y + 17.0f, x + 38.0f, y + 39.0f, 0.0f, 0.0f, 1.0f, 1.0f, Ui.col(Im.INK));
                                                                        }
                                                                        stringArray = a;
                                                                        Ui.text(Im.word, x + 47.0f, Ui.mid(Im.word, y, 56.0f), Im.INK, stringArray[30]);
                                                                    }
                                                                    for (int i = 0; i < PAGES.length; ++i) {
                                                                        if (!PAGES[i].equals(string)) {
                                                                            continue;
                                                                        }
                                                                        stringArray = a;
                                                                        f4 = y + Ui.slide(stringArray[38], 66.0f + 36.0f * (float)i, 0.15f);
                                                                        imDrawList.addRectFilled(x + 10.0f, f4, x + 144.0f - 10.0f, f4 + 32.0f, Ui.col(Ui.tint(0.16f)), 8.0f);
                                                                        if (!bl) continue;
                                                                    }
                                                                    f8 = y + 66.0f;
                                                                    string2 = a[15];
                                                                    if (bl) break block52;
                                                                    if (!Menu.item(string2, f8, string)) break block53;
                                                                    minecraftClient.method_1507((Screen)new ModsScreen());
                                                                }
                                                                stringArray = a;
                                                                string2 = stringArray[33];
                                                            }
                                                            f10 = f8 = f8 + 36.0f;
                                                            string3 = string;
                                                            if (bl) break block54;
                                                            if (!Menu.item(string2, f10, string3)) break block55;
                                                            minecraftClient.method_1507((Screen)new SettingsScreen());
                                                        }
                                                        stringArray = a;
                                                        string2 = stringArray[10];
                                                        f10 = f8 = f8 + 36.0f;
                                                        string3 = string;
                                                    }
                                                    if (bl) break block56;
                                                    if (!Menu.item(string2, f10, string3)) break block57;
                                                    minecraftClient.method_1507((Screen)new SocialScreen());
                                                }
                                                stringArray = a;
                                                string2 = stringArray[5];
                                                f7 = f8;
                                                f9 = 36.0f;
                                                if (bl) break block65;
                                                f10 = f8 = f7 + f9;
                                                string3 = string;
                                            }
                                            if (Menu.item(string2, f10, string3)) {
                                                minecraftClient.method_1507((Screen)new LockerScreen());
                                            }
                                            stringArray = a;
                                            string2 = stringArray[16];
                                            float f11 = f8 + 36.0f;
                                            f9 = f11;
                                            f7 = f11;
                                        }
                                        f8 = f9;
                                        if (bl) break block58;
                                        if (!Menu.item(string2, f7, string)) break block59;
                                        minecraftClient.method_1507((Screen)new ProfilesScreen());
                                    }
                                    stringArray = a;
                                    string2 = stringArray[14];
                                    f7 = f8 = f8 + 36.0f;
                                }
                                if (Menu.item(string2, f7, string)) {
                                    minecraftClient.method_1507((Screen)new PlusScreen());
                                }
                                f4 = x + 10.0f;
                                f3 = y + h - 82.0f;
                                f2 = 124.0f;
                                f6 = f4 + 6.0f;
                                if (bl) break block60;
                                Menu.you(f6, f3 - 52.0f, f2 - 12.0f);
                                ImGui.setCursorScreenPos((float)f4, (float)f3);
                                if (!ImGui.invisibleButton((String)a[43], (float)f2, (float)32.0f)) break block61;
                                HudBridge.openEditor();
                            }
                            f6 = Ui.hot();
                        }
                        f = f6;
                        float f12 = 1.5f * Ui.held();
                        Ui.box(imDrawList, f4 + f12, f3 + f12, f4 + f2 - f12, f3 + 32.0f - f12, Im.FIELD, Im.FIELD_LINE, 8.0f, 1.0f);
                        f5 = f;
                        if (bl) break block62;
                        if (!(f5 > 0.01f)) break block63;
                        imDrawList.addRectFilled(f4 + f12, f3 + f12, f4 + f2 - f12, f3 + 32.0f - f12, Ui.wash(0.06f * f), 8.0f);
                    }
                    stringArray = a;
                    f5 = 23.0f + Ui.width(Im.sora13, stringArray[11]);
                }
                float f13 = f5;
                float f14 = f4 + (float)Math.floor((f2 - f13) / 2.0f);
                stringArray = a;
                Ui.icon(stringArray[21], f14, f3 + 8.5f, 15.0f, Ui.soft());
                Ui.text(Im.sora13, f14 + 23.0f, Ui.mid(Im.sora13, f3, 32.0f), Im.INK, stringArray[23]);
                if (bl) break block66;
                f3 = y + h - 44.0f;
                ImGui.setCursorScreenPos((float)f4, (float)f3);
                if (ImGui.invisibleButton((String)stringArray[48], (float)f2, (float)30.0f)) {
                    Util.method_668().method_670(stringArray[3]);
                }
                f = Ui.hot();
                if (bl) break block67;
            }
            if (f > 0.01f) {
                imDrawList.addRectFilled(f4, f3, f4 + f2, f3 + 30.0f, Ui.wash(0.05f * f), 8.0f);
            }
            stringArray = a;
            Ui.text(Im.sora13, f4 + (float)Math.floor((f2 - Ui.width(Im.sora13, stringArray[39])) / 2.0f), Ui.mid(Im.sora13, f3, 30.0f), Ui.mix(Im.MUTE, Im.INK, f), stringArray[45]);
        }
    }

    private static float plusWord(float f, float f2, float f3) {
        ImFont imFont;
        ImFont imFont2;
        block7: {
            Ui.plusLogo(f, f2, 22.0f, false);
            imFont2 = Im.word;
            ImFont[] imFontArray = new ImFont[]{Im.word, Im.sora16, Im.sora14};
            int n = imFontArray.length;
            boolean bl = ImScreen.a;
            int n2 = 0;
            while (n2 < n) {
                ImFont imFont3 = imFontArray[n2];
                if (!bl) {
                    imFont2 = imFont3;
                    imFont = imFont2;
                    if (bl) break block7;
                    if (Ui.width(imFont, a[19]) <= f3) {
                        break;
                    }
                    ++n2;
                }
                if (!bl) continue;
            }
            imFont = imFont2;
        }
        float f4 = Ui.ink(imFont, f2, 22.0f);
        String[] stringArray = a;
        Ui.text(imFont2, f + 31.0f, f4, Im.INK, stringArray[22]);
        Ui.plusText(imFont2, f + 31.0f + Ui.width(imFont2, stringArray[22]), f4, stringArray[14]);
        return f + 31.0f + Ui.width(imFont2, stringArray[35]);
    }

    private static void you(float f, float f2, float f3) {
        MinecraftClient minecraftClient;
        block4: {
            block5: {
                boolean bl = ImScreen.a;
                minecraftClient = MinecraftClient.method_1551();
                if (bl) break block4;
                if (minecraftClient.method_1548() != null) break block5;
                return;
            }
            Ui.head(minecraftClient.method_1548().method_44717(), f, f2 + 7.0f, 28.0f);
        }
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        imDrawList.pushClipRect(f + 36.0f, f2, f + f3, f2 + 42.0f, true);
        Ui.text(Im.sora13, f + 38.0f, Ui.ink(Im.sora13, f2 + 7.0f, 28.0f), Im.INK, minecraftClient.method_1548().method_1676());
        imDrawList.popClipRect();
    }

    private static boolean item(String string, float f, String string2) {
        int n;
        float[] fArray;
        boolean bl;
        block29: {
            block30: {
                String string3;
                boolean bl2;
                block27: {
                    block28: {
                        float f2;
                        float f3;
                        block25: {
                            boolean bl3;
                            block26: {
                                boolean bl4;
                                block24: {
                                    block23: {
                                        block22: {
                                            bl3 = string.equals(string2);
                                            ImGui.setCursorScreenPos((float)(x + 10.0f), (float)f);
                                            bl2 = ImScreen.a;
                                            bl4 = ImGui.invisibleButton((String)(a[34] + string), (float)124.0f, (float)32.0f);
                                            if (bl2) break block22;
                                            if (!bl4) break block23;
                                            bl4 = bl3;
                                        }
                                        if (bl2) break block24;
                                        if (bl4) break block23;
                                        bl4 = true;
                                        break block24;
                                    }
                                    bl4 = false;
                                }
                                bl = bl4;
                                float f4 = bl3 ? 0.0f : Ui.hot();
                                f3 = f4;
                                ImDrawList imDrawList = ImGui.getWindowDrawList();
                                float f5 = f3 - 0.01f;
                                f2 = f5 == 0.0f ? 0 : (f5 > 0.0f ? 1 : -1);
                                if (bl2) break block25;
                                if (f2 <= 0) break block26;
                                imDrawList.addRectFilled(x + 10.0f, f, x + 144.0f - 10.0f, f + 32.0f, Ui.wash(0.05f * f3), 8.0f);
                            }
                            f2 = (float)bl3;
                        }
                        float[] fArray2 = f2 != false ? Im.INK : Ui.mix(Im.MUTE, Im.INK, f3);
                        fArray = fArray2;
                        string3 = string;
                        if (bl2) break block27;
                        if (!string3.equals(a[5])) break block28;
                        n = RewardsBadge.ready();
                        break block29;
                    }
                    string3 = string;
                }
                n = string3.equals(a[10]);
                if (bl2) break block29;
                if (n == 0) break block30;
                n = WorldShare.invites().size();
                break block29;
            }
            n = 0;
        }
        int n2 = n;
        float f6 = x + 144.0f - 30.0f;
        float f7 = f + 16.0f;
        int n3 = n2;
        float[] fArray3 = string.equals(a[5]) ? Im.WARN : Im.GREEN;
        Menu.badge(f6, f7, n3, fArray3);
        Ui.icon(a[0] + string.toLowerCase(Locale.ROOT), x + 20.0f, f + 8.0f, 16.0f, fArray);
        Ui.text(Im.sora13, x + 45.0f, Ui.mid(Im.sora13, f, 32.0f), fArray, string);
        return bl;
    }

    public static void search(String string, String string2, ImString imString, float f, float f2, float f3) {
        ImGui.setCursorScreenPos((float)f, (float)f2);
        ImGui.setNextItemWidth((float)f3);
        ImGui.pushStyleVar((int)11, (float)38.0f, (float)10.5f);
        ImGui.pushStyleVar((int)13, (float)1.0f);
        ImGui.pushFont((ImFont)Im.sub);
        ImGui.inputTextWithHint((String)string, (String)string2, (ImString)imString);
        ImGui.popFont();
        ImGui.popStyleVar((int)2);
        Ui.icon(a[2], f + 14.0f, f2 + 9.5f, 15.0f, Im.MUTE);
    }

    public static void badge(float f, float f2, int n, float[] fArray) {
        Menu.badge(f, f2, n, fArray, false);
    }

    
    public static void badge(float var0, float var1_1, int var2_2, float[] var3_3, boolean var4_4) {
        block22: {
            block21: {
                block20: {
                    block18: {
                        block19: {
                            block16: {
                                block17: {
                                    var5_5 = ImScreen.a;
                                    v0 = var2_2;
                                    if (var5_5) break block16;
                                    if (v0 > 0) break block17;
                                    return;
                                }
                                v0 = var2_2;
                            }
                            if (var5_5) break block18;
                            if (v0 <= 9) break block19;
                            v3 = Menu.a[44];
                            break block20;
                        }
                        v0 = var2_2;
                    }
                    v3 = String.valueOf(v0);
                }
                var6_6 = v3;
                v5 = var4_4 != false ? Im.mc : Im.label;
                var7_7 = Ui.width(v5, var6_6);
                var8_8 = Math.max(16.0f, var7_7 + 10.0f);
                var9_9 = ImGui.getWindowDrawList();
                if (var5_5) break block21;
                Ui.fill(var9_9, var0 - var8_8 / 2.0f, var1_1 - 8.0f, var0 + var8_8 / 2.0f, var1_1 + 8.0f, Ui.col(var3_3), 8.0f);
                if (var4_4) {
                }
                Ui.mcText(var0 - (float)Math.floor(var7_7 / 2.0f), Ui.mid(Im.mc, var1_1 - 8.0f, 16.0f), Ui.col(Im.BG), var6_6, false);
            }
            if (!var5_5) break block22;
lbl53:

            Ui.text(Im.label, var0 - (float)Math.floor(var7_7 / 2.0f), Ui.mid(Im.label, var1_1 - 8.0f, 16.0f), Im.BG, var6_6);
        }
    }

    public static float chevron(float f, float f2) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        imDrawList.addLine(f, f2 + 3.5f, f + 3.5f, f2 + 7.0f, Ui.col(Im.MUTE), 1.2f);
        imDrawList.addLine(f + 3.5f, f2 + 7.0f, f, f2 + 10.5f, Ui.col(Im.MUTE), 1.2f);
        return f + 12.0f;
    }

    public static void tick(float f, float f2, float f3, int n) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        imDrawList.addLine(f - f3, f2, f - f3 / 3.0f, f2 + f3 * 2.0f / 3.0f, n, 1.5f);
        imDrawList.addLine(f - f3 / 3.0f, f2 + f3 * 2.0f / 3.0f, f + f3, f2 - f3 * 2.0f / 3.0f, n, 1.5f);
    }

    public static boolean tag(String string, float f, float f2, boolean bl) {
        return Menu.tag(string, f, f2, bl, false);
    }

    public static boolean tag(String string, float f, float f2, boolean bl, boolean bl2) {
        boolean bl3;
        block10: {
            float[] fArray;
            float f3;
            float f4;
            String string2;
            block11: {
                String[] stringArray = a;
                int n = string.indexOf(stringArray[24]);
                boolean bl4 = ImScreen.a;
                String string3 = n < 0 ? string : string.substring(0, n);
                string2 = string3;
                float f5 = bl2 ? 14.0f : 0.0f;
                f4 = f5;
                float f6 = Menu.tagWidth(string2, bl2);
                f3 = f - f6;
                ImGui.setCursorScreenPos((float)f3, (float)f2);
                bl3 = ImGui.invisibleButton((String)string, (float)f6, (float)20.0f);
                float f7 = Ui.hot();
                ImDrawList imDrawList = ImGui.getWindowDrawList();
                Menu.chip(imDrawList, f3, f2, f3 + f6, f2 + 20.0f, bl, f7, 6.0f);
                float[] fArray2 = bl ? Im.CHIP_ON_INK : Ui.mix(Im.SUB, Im.INK, f7);
                fArray = fArray2;
                if (bl4) break block10;
                if (!bl2) break block11;
                Ui.icon(a[31], f3 + 8.0f, f2 + 5.0f, 10.0f, fArray);
            }
            Ui.text(Im.label, f3 + 8.0f + f4, Ui.mid(Im.label, f2, 20.0f), fArray, string2);
        }
        return bl3;
    }

    public static float tagWidth(String string, boolean n) {
        int n2;
        float f;
        block2: {
            block3: {
                boolean bl = ImScreen.a;
                f = Ui.width(Im.label, string) + 16.0f;
                n2 = n;
                if (bl) break block2;
                if (n2 == 0) break block3;
                n2 = 14;
                break block2;
            }
            n2 = 0;
        }
        return f + (float)n2;
    }

    public static void chip(ImDrawList imDrawList, float f, float f2, float f3, float f4, boolean n, float f5, float f6) {
        block11: {
            float f7;
            float f8;
            ImDrawList imDrawList2;
            block10: {
                int n2;
                block8: {
                    boolean bl;
                    block9: {
                        bl = ImScreen.a;
                        n2 = n;
                        if (bl) break block8;
                        if (n2 == 0) break block9;
                        Ui.gradient(imDrawList, f, f2, f3, f4, Im.CHIP_ON, CHIP_TO, f6);
                        return;
                    }
                    imDrawList2 = imDrawList;
                    f8 = f;
                    f7 = f2;
                    if (bl) break block10;
                    Ui.box(imDrawList2, f8, f7, f3, f4, Im.FIELD, Im.FIELD_LINE, f6, 1.0f);
                    float f9 = f5 - 0.01f;
                    n2 = f9 == 0.0f ? 0 : (f9 > 0.0f ? 1 : -1);
                }
                if (n2 <= 0) break block11;
                imDrawList2 = imDrawList;
                f8 = f;
                f7 = f2;
            }
            imDrawList2.addRectFilled(f8, f7, f3, f4, Ui.wash(0.06f * f5), f6);
        }
    }

    public static boolean quiet(String string, String string2, float f, float f2, float f3, float f4) {
        boolean bl;
        block8: {
            float f5;
            float f6;
            String string3;
            block9: {
                ImGui.setCursorScreenPos((float)f, (float)f2);
                String[] stringArray = a;
                bl = ImGui.invisibleButton((String)(string + stringArray[46]), (float)f3, (float)f4);
                boolean bl2 = ImScreen.a;
                float f7 = Ui.hot();
                Ui.fill(ImGui.getWindowDrawList(), f, f2, f + f3, f2 + f4, Ui.wash(0.07f + 0.05f * f7), 8.0f);
                int n = string.indexOf(stringArray[24]);
                String string4 = n < 0 ? string : string.substring(0, n);
                string3 = string4;
                float f8 = string2 == null ? 0.0f : 20.0f;
                f6 = f8;
                f5 = f + (float)Math.floor((f3 - Ui.width(Im.sora12, string3) - f6) / 2.0f);
                if (bl2) break block8;
                if (string2 == null) break block9;
                Ui.icon(string2, f5, f2 + (f4 - 13.0f) / 2.0f, 13.0f, Im.INK);
            }
            Ui.text(Im.sora12, f5 + f6, Ui.mid(Im.sora12, f2, f4), Im.INK, string3);
        }
        return bl;
    }

    public static float quietWidth(String string, String string2) {
        int n = string.indexOf(a[8]);
        float f = 24.0f;
        ImFont imFont = Im.sora12;
        String string3 = n < 0 ? string : string.substring(0, n);
        float f2 = f + Ui.width(imFont, string3);
        int n2 = string2 == null ? 0 : 20;
        return f2 + (float)n2;
    }

    public static boolean button(String string, float f, float f2, float f3, float f4, boolean bl) {
        return Menu.button(string, f, f2, f3, f4, bl, false);
    }

    public static boolean button(String string, float f, float f2, float f3, float f4, boolean bl, boolean bl2) {
        return Menu.button(string, null, f, f2, f3, f4, bl, bl2);
    }

    public static boolean button(String string, String string2, float f, float f2, float f3, float f4, boolean bl) {
        return Menu.button(string, string2, f, f2, f3, f4, bl, false);
    }

    public static float buttonWidth(String string, String string2) {
        float f = 28.0f + Ui.width(Im.sora12r, string);
        int n = string2 == null ? 0 : 21;
        return f + (float)n;
    }

    private static boolean button(String string, String string2, float f, float f2, float f3, float f4, boolean bl, boolean bl2) {
        boolean bl3;
        block36: {
            float f5;
            float f6;
            float[] fArray;
            ImFont imFont;
            String string3;
            block37: {
                float f7;
                boolean bl4;
                float f8;
                block32: {
                    block33: {
                        float[] fArray2;
                        float f9;
                        float f10;
                        block35: {
                            block34: {
                                block31: {
                                    block30: {
                                        ImDrawList imDrawList;
                                        float f11;
                                        float f12;
                                        block28: {
                                            block29: {
                                                ImGui.setCursorScreenPos((float)f, (float)f2);
                                                bl3 = ImGui.invisibleButton((String)string, (float)f3, (float)f4);
                                                f8 = Ui.hot();
                                                float f13 = 0.015f * Ui.held();
                                                f12 = f3 * f13;
                                                bl4 = ImScreen.a;
                                                f11 = f4 * f13;
                                                imDrawList = ImGui.getWindowDrawList();
                                                int n = string.indexOf(a[24]);
                                                String string4 = n < 0 ? string : string.substring(0, n);
                                                string3 = string4;
                                                if (bl4) break block28;
                                                if (!bl) break block29;
                                                Ui.accent(imDrawList, f + f12, f2 + f11, f + f3 - f12, f2 + f4 - f11, Ui.col(Ui.mix(FILL, ICON_FOOT, f8)), 8.0f);
                                                if (!bl4) break block30;
                                            }
                                            Ui.box(imDrawList, f + f12, f2 + f11, f + f3 - f12, f2 + f4 - f11, Im.FIELD, Im.FIELD_LINE, 8.0f, 1.0f);
                                        }
                                        float f14 = f8 - 0.01f;
                                        f7 = f14 == 0.0f ? 0 : (f14 > 0.0f ? 1 : -1);
                                        if (bl4) break block31;
                                        if (f7 <= 0) break block30;
                                        imDrawList.addRectFilled(f + f12, f2 + f11, f + f3 - f12, f2 + f4 - f11, Ui.wash(0.06f * f8), 8.0f);
                                    }
                                    f7 = (float)bl2;
                                }
                                if (bl4) break block32;
                                if (f7 == false) break block33;
                                f10 = f + Mc.labelX(f3, Ui.width(Im.mc, string3));
                                f9 = f2 + Mc.labelY(f4);
                                if (!bl) break block34;
                                fArray2 = Im.ON_FILL;
                                break block35;
                            }
                            fArray2 = Ui.mix(Im.SUB, Im.INK, f8);
                        }
                        Ui.mcText(f10, f9, Ui.col(fArray2), string3, true);
                        return bl3;
                    }
                    float f15 = f4 - 34.0f;
                    f7 = f15 == 0.0f ? 0 : (f15 > 0.0f ? 1 : -1);
                }
                ImFont imFont2 = f7 > 0 ? Im.sora14r : Im.sora12r;
                imFont = imFont2;
                float[] fArray3 = bl ? Im.ON_FILL : Ui.mix(Im.SUB, Im.INK, f8);
                fArray = fArray3;
                float f16 = string2 == null ? 0.0f : 21.0f;
                f6 = f16;
                f5 = f + (float)Math.floor((f3 - Ui.width(imFont, string3) - f6) / 2.0f);
                if (bl4) break block36;
                if (string2 == null) break block37;
                Ui.icon(string2, f5, f2 + (f4 - 14.0f) / 2.0f, 14.0f, fArray);
            }
            Ui.text(imFont, f5 + f6, Ui.mid(imFont, f2, f4), fArray, string3);
        }
        return bl3;
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var9 = new String[49];
                    var7_1 = 0;
                    var6_2 = "itNL\u0006szHg-=\u0006tpYa/7\u001doaLc?eM(qQ`/0\u0010c;_tc704D\u0001V\r;4p\u0005dyW`)\bPtJw>0\u0000b\u0005`}W`8\u0006Tz[z-3\u0002$6\u0006szHg-=\u0006Tz[z-3\bBqQgl\u00177C\u0006f`J|>>\u0004WyM`\u0004WyM`\u0007Jz\\f :\u0011\bWgWu%3\u0007t\u0015sp@g9-\u0007t:_f%p\u000ehrW=<1\u0005\u0003szH\nF`J|>>2k`K\bTpLg%1\u0005t\bbqQg\u00137\u0017c\u0006F`J|>>\bBqQgl\u00177C\u0002$6\u0005dyW`)\tszH`)>\u0010d}\bWgWu%3\u0007t\u0002>>\u0004jpVf\u0006F`J|>>\u0004kz[x\u0015sp@g9-\u0007t:_f%p\u000ehrW=<1\u0005\bTpLg%1\u0005t\u0003itN\nF`J|>>2k`K\u0006f`J|>>\u0004itNL\u0003itN\u0007C|Kp#-\u0006\u0006tpYa/7\bPtJw>0\u0000b\u0007Jz\\f :\u0011\u000bitNV(6\u0016']mW\u0002>>\u0007C|Kp#-\u0006\u0007$6If%:\u0016";
                    var8_3 = "itNL\u0006szHg-=\u0006tpYa/7\u001doaLc?eM(qQ`/0\u0010c;_tc704D\u0001V\r;4p\u0005dyW`)\bPtJw>0\u0000b\u0005`}W`8\u0006Tz[z-3\u0002$6\u0006szHg-=\u0006Tz[z-3\bBqQgl\u00177C\u0006f`J|>>\u0004WyM`\u0004WyM`\u0007Jz\\f :\u0011\bWgWu%3\u0007t\u0015sp@g9-\u0007t:_f%p\u000ehrW=<1\u0005\u0003szH\nF`J|>>2k`K\bTpLg%1\u0005t\bbqQg\u00137\u0017c\u0006F`J|>>\bBqQgl\u00177C\u0002$6\u0005dyW`)\tszH`)>\u0010d}\bWgWu%3\u0007t\u0002>>\u0004jpVf\u0006F`J|>>\u0004kz[x\u0015sp@g9-\u0007t:_f%p\u000ehrW=<1\u0005\bTpLg%1\u0005t\u0003itN\nF`J|>>2k`K\u0006f`J|>>\u0004itNL\u0003itN\u0007C|Kp#-\u0006\u0006tpYa/7\bPtJw>0\u0000b\u0007Jz\\f :\u0011\u000bitNV(6\u0016']mW\u0002>>\u0007C|Kp#-\u0006\u0007$6If%:\u0016".length();
                    var5_4 = 4;
                    var4_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var4_5;
                        v1 = var6_2.substring(v0, v0 + var5_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var9[var7_1++] = v3.intern();
                        if ((var4_5 += var5_4) < var8_3) {
                            var5_4 = var6_2.charAt(var4_5);
                            continue;
                        }
                        var6_2 = "jpVf\nitNW%,\u0001hg\\";
                        var8_3 = "jpVf\nitNW%,\u0001hg\\".length();
                        var5_4 = 4;
                        var4_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var4_5;
                            v1 = var6_2.substring(v4, v4 + var5_4);
                            v2 = 0;
                            break block19;
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
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var10_7 = 0;
                do {
                    v6 = v6;
                    v8 = var10_7;
                    v9 = v6[v8];
                    switch (var10_7 % 7) {
                        case 0: {
                            v10 = 7;
                            break;
                        }
                        case 1: {
                            v10 = 21;
                            break;
                        }
                        case 2: {
                            v10 = 56;
                            break;
                        }
                        case 3: {
                            v10 = 19;
                            break;
                        }
                        case 4: {
                            v10 = 76;
                            break;
                        }
                        case 5: {
                            v10 = 95;
                            break;
                        }
                        default: {
                            v10 = 98;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var10_7;
lbl65:

                    v7 = v7;
                } while (v7 > var10_7);
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
            Menu.a = var9;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var2_8 = 8800974791745060228L;
        while (true)
        var0_9 = -8800974791745060237L ^ var2_8;
        Menu.ACCENT = new float[]{0.545f, 0.486f, 0.965f, 1.0f};
        Menu.FILL = new float[]{0.349f, 0.259f, 0.875f, 1.0f};
        Menu.ICON_TOP = new float[]{0.741f, 0.706f, 0.941f, 1.0f};
        Menu.ICON_FOOT = new float[]{0.467f, 0.38f, 0.957f, 1.0f};
        Menu.GRAD_A = (float[])Menu.FILL.clone();
        Menu.GRAD_B = (float[])Menu.FILL.clone();
        Menu.KNOB_ON = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
        Menu.SOFT = new float[]{0.663f, 0.608f, 0.949f, 1.0f};
        Menu.CHIP_TO = new float[]{0.945f, 0.953f, 0.969f, 1.0f};
        Menu.SIDE_BG = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        v11 = new String[5];
        var4_6 = Menu.a;
        v11[0] = var4_6[42];
        v11[1] = var4_6[7];
        v11[2] = var4_6[41];
        v11[3] = var4_6[27];
        v11[4] = var4_6[20];
        Menu.PAGES_C = v11;
        Menu.fade = 1.0f;
        Menu.last = var0_9;
        Menu.PAGES = new String[]{var4_6[15], var4_6[33], var4_6[10], var4_6[5], var4_6[16], var4_6[13]};
    }
}
