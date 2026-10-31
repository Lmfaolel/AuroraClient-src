package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.gui.shell.LockerScreen;
import dev.quelchi.aurora.gui.shell.RewardsBadge;
import dev.quelchi.aurora.gui.shell.RewardsScreen;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.gui.shell.Shop;
import dev.quelchi.aurora.gui.shell.StoreScreen;
import imgui.ImDrawList;
import imgui.ImGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

final class WardrobeBar {
    private static final float[] GEM_TOP;
    private static final String[] ICONS;
    private static final String[] a;

    private WardrobeBar() {
    }

    static void gem(float f, float f2, float f3) {
        Ui.icon(a[4], f, f2, f3, GEM_TOP, Menu.ICON_FOOT);
    }

    static float amount(String string, float f, float f2, float f3, float[] fArray) {
        float f4 = f - Ui.width(Im.sora12, string);
        Ui.text(Im.sora12, f4, Ui.ink(Im.sora12, f2, f3), fArray, string);
        WardrobeBar.gem(f4 - 18.0f, f2 + (float)Math.floor((f3 - 13.0f) / 2.0f), 13.0f);
        return f4 - 18.0f;
    }

    static float amountWidth(String string) {
        return Ui.width(Im.sora12, string) + 18.0f;
    }

    static float draw(int n, float f) {
        float f2;
        block26: {
            ImDrawList imDrawList = ImGui.getWindowDrawList();
            String string = Shop.fmt(Cosmetics.shards());
            float f3 = 46.0f + Ui.width(Im.sora13, string);
            boolean bl = SearchIndex.a;
            float f4 = Menu.x + Menu.w - 55.0f - 14.0f - f3;
            float f5 = f + 11.0f;
            imDrawList.addRectFilled(f4, f5, f4 + f3, f5 + 34.0f, Ui.col(Im.QUIET), 9.0f);
            imDrawList.addRect(f4, f5, f4 + f3, f5 + 34.0f, Ui.wash(0.16f), 9.0f);
            WardrobeBar.gem(f4 + 12.0f, f5 + 9.5f, 15.0f);
            Ui.text(Im.sora13, f4 + 34.0f, Ui.ink(Im.sora13, f5, 34.0f), Im.INK, string);
            float f6 = f4 - 10.0f - 114.0f;
            float f7 = f + 10.0f;
            imDrawList.addRectFilled(f6, f7, f6 + 114.0f, f7 + 36.0f, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)0.2f), 10.0f);
            imDrawList.addRect(f6, f7, f6 + 114.0f, f7 + 36.0f, Ui.wash(0.16f), 10.0f);
            for (int i = 0; i < 3; ++i) {
                ImScreen imScreen;
                MinecraftClient minecraftClient;
                block37: {
                    int n2;
                    block35: {
                        block36: {
                            int n3;
                            block34: {
                                block32: {
                                    int n4;
                                    block33: {
                                        float[] fArray;
                                        float f8;
                                        float f9;
                                        float f10;
                                        String string2;
                                        float f11;
                                        float f12;
                                        block31: {
                                            int n5;
                                            block29: {
                                                block30: {
                                                    block28: {
                                                        block27: {
                                                            f12 = f6 + 4.0f + (float)(i * 36);
                                                            f2 = f7;
                                                            if (bl) break block26;
                                                            f11 = f2 + 4.0f;
                                                            ImGui.setCursorScreenPos((float)f12, (float)f11);
                                                            n4 = ImGui.invisibleButton((String)(a[3] + i), (float)34.0f, (float)28.0f);
                                                            if (bl) break block27;
                                                            if (i != n) break block28;
                                                            imDrawList.addRectFilled(f12, f11, f12 + 34.0f, f11 + 28.0f, Ui.col(Menu.ACCENT, 0.18f), 7.0f);
                                                        }
                                                        imDrawList.addRect(f12, f11, f12 + 34.0f, f11 + 28.0f, Ui.col(Menu.ACCENT, 0.7f), 7.0f);
                                                    }
                                                    string2 = ICONS[i];
                                                    f10 = f12 + 9.0f;
                                                    f9 = f11 + 6.0f;
                                                    f8 = 16.0f;
                                                    n5 = i;
                                                    if (bl) break block29;
                                                    if (n5 != n) break block30;
                                                    fArray = Im.INK;
                                                    break block31;
                                                }
                                                n5 = ImGui.isItemHovered() ? 1 : 0;
                                            }
                                            fArray = n5 != 0 ? Im.SUB : Im.MUTE;
                                        }
                                        n3 = Ui.icon(string2, f10, f9, f8, fArray);
                                        if (bl) break block32;
                                        if (i != 2) break block33;
                                        Menu.badge(f12 + 30.0f, f11 + 3.0f, RewardsBadge.ready(), Im.WARN);
                                    }
                                    n3 = n4;
                                }
                                if (bl) break block34;
                                if (n3 == 0) continue;
                                n3 = i;
                            }
                            if (n3 == n) {
                                continue;
                            }
                            minecraftClient = MinecraftClient.method_1551();
                            n2 = i;
                            if (bl) break block35;
                            if (n2 != 0) break block36;
                            imScreen = new LockerScreen();
                            break block37;
                        }
                        n2 = i;
                    }
                    imScreen = n2 == 1 ? new StoreScreen() : new RewardsScreen();
                }
                minecraftClient.method_1507((Screen)imScreen);
                if (!bl) continue;
            }
            f2 = f6;
        }
        return f2;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "f_'$\u0018M\u0003fC\u0005vD;;\u0018\u0007wU#(\u000f]\u0019";
                var4_3 = "f_'$\u0018M\u0003fC\u0005vD;;\u0018\u0007wU#(\u000f]\u0019".length();
                var1_4 = 9;
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
                    var2_2 = "vU3\u0003bU9";
                    var4_3 = "vU3\u0003bU9".length();
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
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 5;
                        break;
                    }
                    case 1: {
                        v10 = 48;
                        break;
                    }
                    case 2: {
                        v10 = 84;
                        break;
                    }
                    case 3: {
                        v10 = 73;
                        break;
                    }
                    case 4: {
                        v10 = 125;
                        break;
                    }
                    case 5: {
                        v10 = 57;
                        break;
                    }
                    default: {
                        v10 = 106;
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
        WardrobeBar.a = var5;
        WardrobeBar.GEM_TOP = new float[]{0.863f, 0.839f, 1.0f, 1.0f};
        v11 = new String[3];
        var0_6 = WardrobeBar.a;
        v11[0] = var0_6[0];
        v11[1] = var0_6[1];
        v11[2] = var0_6[2];
        WardrobeBar.ICONS = v11;
    }
}
