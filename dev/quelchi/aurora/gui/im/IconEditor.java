package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.crosshair.CrosshairShape;
import dev.quelchi.aurora.crosshair.CrosshairShape$Preset;
import dev.quelchi.aurora.crosshair.IconAnimation;
import dev.quelchi.aurora.crosshair.IconStore;
import dev.quelchi.aurora.gui.im.Edit;
import dev.quelchi.aurora.gui.im.IconEditor$Tool;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Ui;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.type.ImString;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public final class IconEditor
extends ImScreen {
    private static final int[] PALETTE;
    private static final List<CrosshairShape$Preset> PRESETS;
    private static final float OPT = 250.0f;
    private static final int BOTH;
    private static final IconEditor$Tool[] TOOLS;
    private static final String[] TIPS;
    private static final float[][][] GLYPHS;
    private static final float[][] MIRROR;
    private final List<String> icons;
    private String active;
    private final boolean solo;
    private int frame;
    private IconEditor$Tool tool;
    private boolean mirrorX;
    private boolean mirrorY;
    private boolean painting;
    private int paintValue;
    private int lineX;
    private int lineY;
    private int lineToX;
    private int lineToY;
    private int brush;
    private int custom;
    private boolean brushCustom;
    private final float[] hsv;
    private final ImString hex;
    private boolean hexing;
    private long playing;
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;
    private static final long g;

    public IconEditor(String string) {
        String string2;
        block22: {
            Object object;
            block23: {
                block24: {
                    boolean bl;
                    block20: {
                        block21: {
                            block16: {
                                boolean bl2;
                                block19: {
                                    block18: {
                                        String string3;
                                        block17: {
                                            super(b[32]);
                                            bl = ImScreen.a;
                                            this.icons = new ArrayList<String>();
                                            this.tool = IconEditor$Tool.DRAW;
                                            this.lineX = -1;
                                            this.lineY = -1;
                                            this.lineToX = -1;
                                            this.lineToY = -1;
                                            this.brush = 1;
                                            this.custom = IconEditor.a(12177, 7290561262070212945L);
                                            this.hsv = new float[3];
                                            this.hex = new ImString(10);
                                            for (String string4 : IconStore.keys()) {
                                                if (!bl) {
                                                    this.icons.add(string4);
                                                    if (!bl) continue;
                                                    break;
                                                }
                                                break block16;
                                            }
                                            IconEditor iconEditor = this;
                                            string3 = string;
                                            if (bl) break block17;
                                            if (string3 == null) break block18;
                                            string3 = string;
                                        }
                                        if (IconStore.get(string3) == null) break block18;
                                        bl2 = true;
                                        break block19;
                                    }
                                    bl2 = false;
                                }
                                iconEditor.solo = bl2;
                            }
                            IconEditor iconEditor = this;
                            object = this;
                            if (bl) break block20;
                            if (!object.solo) break block21;
                            string2 = string;
                            break block22;
                        }
                        object = this;
                    }
                    if (bl) break block23;
                    if (!object.icons.isEmpty()) break block24;
                    string2 = null;
                    break block22;
                }
                object = this.icons.get(0);
            }
            string2 = (String)object;
        }
        iconEditor.active = string2;
    }

    private static boolean shiftDown() {
        int n;
        block8: {
            block10: {
                block9: {
                    long l = MinecraftClient.method_1551().method_22683().method_4490();
                    boolean bl = ImScreen.a;
                    n = GLFW.glfwGetKey((long)l, (int)340);
                    if (bl) break block8;
                    if (n == 1) break block9;
                    n = GLFW.glfwGetKey((long)l, (int)344);
                    if (bl) break block8;
                    if (n != 1) break block10;
                }
                n = 1;
                break block8;
            }
            n = 0;
        }
        return n != 0;
    }

    
    @Override
    protected boolean key(int n) {
        boolean bl = ImScreen.a;
        int n2 = Im.typing();
        if (!bl) {
            if (n2 != 0) {
                return false;
            }
            n2 = n;
        }
        if (bl) return n2 != 0;
        switch (n2) {
            case 256: {
                Edit.back();
                if (!bl) return true;
            }
            case 66: {
                this.tool = IconEditor$Tool.DRAW;
                if (!bl) return true;
            }
            case 69: {
                this.tool = IconEditor$Tool.ERASE;
                if (!bl) return true;
            }
            case 71: {
                this.tool = IconEditor$Tool.FILL;
                if (!bl) return true;
            }
            case 88: {
                boolean bl2 = this.mirrorX;
                if (!bl) {
                    bl2 = !bl2;
                }
                this.mirrorX = bl2;
                if (!bl) return true;
            }
            case 89: {
                boolean bl3 = this.mirrorY;
                if (!bl) {
                    bl3 = !bl3;
                }
                this.mirrorY = bl3;
                if (!bl) return true;
            }
            case 259: 
            case 261: {
                IconEditor iconEditor = this;
                if (!bl) {
                    if (iconEditor.active == null) return true;
                    iconEditor = this;
                }
                IconStore.clear(iconEditor.active, this.frame);
                ConfigManager.save();
                if (!bl) return true;
            }
        }
        return 0 != 0;
    }

    
    @Override
    protected void draw() {
        block87: {
            block88: {
                block85: {
                    block86: {
                        block84: {
                            block83: {
                                block80: {
                                    block81: {
                                        block82: {
                                            block79: {
                                                block89: {
                                                    block78: {
                                                        block76: {
                                                            block77: {
                                                                block75: {
                                                                    block73: {
                                                                        block72: {
                                                                            block71: {
                                                                                block69: {
                                                                                    block70: {
                                                                                        block68: {
                                                                                            block67: {
                                                                                                block65: {
                                                                                                    block66: {
                                                                                                        block64: {
                                                                                                            block62: {
                                                                                                                block63: {
                                                                                                                    var18_1 = IconEditor.b;
                                                                                                                    Menu.begin(var18_1[5], 0.0f);
                                                                                                                    var1_2 = ImScreen.a;
                                                                                                                    v0 = this;
                                                                                                                    if (var1_2) break block62;
                                                                                                                    if (v0.active != null) break block63;
                                                                                                                    v2 = null;
                                                                                                                    break block64;
                                                                                                                }
                                                                                                                v0 = this;
                                                                                                            }
                                                                                                            v2 = IconStore.get(v0.active);
                                                                                                        }
                                                                                                        var2_3 = v2;
                                                                                                        v4 = IconEditor.b[17].equals(this.active);
                                                                                                        if (var1_2) break block65;
                                                                                                        if (v4) break block66;
                                                                                                        v4 = true;
                                                                                                        break block65;
                                                                                                    }
                                                                                                    v4 = false;
                                                                                                }
                                                                                                var3_4 = v4;
                                                                                                if (!var3_4) break block67;
                                                                                                v6 = IconEditor.b[12];
                                                                                                break block68;
                                                                                            }
                                                                                            var18_1 = IconEditor.b;
                                                                                            v6 = var18_1[17];
                                                                                        }
                                                                                        v8 = var3_4;
                                                                                        if (var1_2) break block69;
                                                                                        if (v8) break block70;
                                                                                        v10 = IconEditor.b[29];
                                                                                        break block71;
                                                                                    }
                                                                                    v12 = this;
                                                                                    if (!var1_2) {
                                                                                        v8 = v12.solo;
                                                                                    }
                                                                                }
                                                                                if (v8) {
                                                                                    v12 = this;
lbl69:

                                                                                    v10 = v12.active;
                                                                                } else {
                                                                                    var18_1 = IconEditor.b;
                                                                                    v10 = var18_1[1];
                                                                                }
                                                                            }
                                                                            var4_5 = Edit.bar(v6, v10) + 16.0f;
                                                                            v14 = this;
                                                                            if (var1_2) break block72;
                                                                            if (v14.solo) break block73;
                                                                            v14 = this;
                                                                        }
                                                                        for (String var6_8 : v14.icons) {
                                                                            block74: {
                                                                                v17 = var4_5;
                                                                                if (var1_2) break block74;
                                                                                v18 = Ui.width(Im.label, var6_8) + 16.0f;
                                                                                if (var1_2) break block75;
                                                                                var4_5 = v17 + v18;
                                                                                if (Menu.tag(var6_8 + IconEditor.b[48], var4_5, Menu.y + 18.0f, var6_8.equals(this.active))) {
                                                                                    this.active = var6_8;
                                                                                    this.frame = 0;
                                                                                    this.playing = 0L;
                                                                                }
                                                                                v17 = var4_5 = var4_5 + 6.0f;
                                                                            }
                                                                            if (!var1_2) continue;
                                                                        }
                                                                    }
                                                                    v21 = Menu.x + Menu.w;
                                                                    v18 = 69.0f;
                                                                }
                                                                var5_7 = v21 - v18;
                                                                var6_9 = Menu.y + 14.0f;
                                                                v22 = IconEditor.b[39];
                                                                v23 = null;
                                                                v24 = var5_7;
                                                                if (var1_2) break block76;
                                                                if (!Edit.button(v22, v23, v24, var6_9, true)) break block77;
                                                                Edit.back();
                                                            }
                                                            v27 = var5_7;
                                                            if (var1_2) break block89;
                                                            var18_1 = IconEditor.b;
                                                            var5_7 = v27 - (Edit.buttonWidth(var18_1[35], false) + 8.0f);
                                                            v22 = var18_1[19];
                                                            v23 = null;
                                                            v24 = var5_7;
                                                        }
                                                        if (!Edit.button(v22, v23, v24, var6_9, false)) break block78;
                                                        v28 = this.active;
                                                        if (var1_2) break block79;
                                                        if (v28 == null) break block78;
                                                        IconStore.reset(this.active);
                                                        this.frame = 0;
                                                        ConfigManager.save();
                                                    }
                                                    v32 = var5_7;
                                                    v33 = Edit.buttonWidth(IconEditor.b[42], false) + 8.0f;
                                                    if (var1_2) break block80;
                                                    v27 = v32 - v33;
                                                }
                                                var5_7 = v27;
                                                var18_1 = IconEditor.b;
                                                v28 = var18_1[6];
                                            }
                                            if (!Edit.button(v28, null, var5_7, var6_9, false)) break block81;
                                            v35 = this;
                                            if (var1_2) break block82;
                                            if (v35.active == null) break block81;
                                            v35 = this;
                                        }
                                        IconStore.clear(v35.active, this.frame);
                                        ConfigManager.save();
                                    }
                                    v32 = Menu.x;
                                    v33 = 144.0f;
                                }
                                var7_10 = v32 + v33;
                                var8_11 = Menu.y + 56.0f;
                                var9_12 = Menu.w - 144.0f;
                                var10_13 = Menu.h - 56.0f;
                                if (var1_2) break block83;
                                if (var2_3 != null) break block84;
                                Ui.text(Im.sub, var7_10 + 20.0f, var8_11 + 20.0f, Im.MUTE, IconEditor.b[43]);
                                Menu.end();
                            }
                            return;
                        }
                        v41 = this.frame;
                        if (var1_2) break block85;
                        if (v41 < var2_3.frameCount()) break block86;
                        this.frame = var2_3.frameCount() - 1;
                    }
                    v41 = var2_3.dim();
                }
                var11_14 = v41;
                v44 = var3_4 != false ? 58.0f : 0.0f;
                var12_15 = v44;
                var13_16 = (float)Math.floor(Math.min(416.0f, Math.min(var9_12 - 40.0f - 80.0f - 250.0f - 40.0f, var10_13 - 40.0f - var12_15)) / (float)var11_14);
                var14_17 = var13_16 * (float)var11_14;
                var15_18 = var7_10 + (var9_12 - (60.0f + var14_17 + 20.0f + 250.0f)) / 2.0f;
                var16_19 = var8_11 + var10_13 / 2.0f;
                this.tools(var15_18, var16_19);
                var17_20 = var16_19 - (var14_17 + var12_15) / 2.0f;
                if (var1_2) break block87;
                this.canvas(var2_3, var15_18 + 60.0f, var17_20, var13_16, var11_14);
                if (!var3_4) break block88;
                this.frames(var2_3, var15_18 + 60.0f, var17_20 + var14_17 + 12.0f, var14_17);
            }
            this.options(var2_3, var3_4, var15_18 + 80.0f + var14_17, var16_19);
            Menu.end();
        }
    }

    private void tools(float f, float f2) {
        block25: {
            boolean bl;
            block26: {
                block27: {
                    float f3;
                    float f4;
                    String string;
                    boolean bl2;
                    block21: {
                        float f5;
                        block22: {
                            boolean bl3;
                            block23: {
                                block24: {
                                    block18: {
                                        f5 = f2 - 142.0f;
                                        bl2 = ImScreen.a;
                                        for (IconEditor$Tool iconEditor$Tool : TOOLS) {
                                            boolean bl4;
                                            float f6;
                                            float f7;
                                            String string2;
                                            block20: {
                                                block19: {
                                                    if (bl2) break block18;
                                                    string2 = iconEditor$Tool.name();
                                                    f7 = f;
                                                    f6 = f5;
                                                    if (this.tool != iconEditor$Tool) break block19;
                                                    bl4 = true;
                                                    break block20;
                                                }
                                                bl4 = false;
                                            }
                                            if (IconEditor.toolButton(string2, f7, f6, bl4, GLYPHS[iconEditor$Tool.ordinal()], false, TIPS[iconEditor$Tool.ordinal()])) {
                                                this.tool = iconEditor$Tool;
                                            }
                                            f5 += 46.0f;
                                            if (!bl2) continue;
                                        }
                                        ImGui.getWindowDrawList().addLine(f + 8.0f, f5 + 4.0f, f + 32.0f, f5 + 4.0f, Ui.col(Im.LINE));
                                        f5 += 15.0f;
                                    }
                                    string = b[47];
                                    f4 = f;
                                    f3 = f5;
                                    if (bl2) break block21;
                                    if (!IconEditor.toolButton(string, f4, f3, this.mirrorX, MIRROR, false, b[23])) break block22;
                                    IconEditor iconEditor = this;
                                    bl3 = this.mirrorX;
                                    if (bl2) break block23;
                                    if (bl3) break block24;
                                    bl3 = true;
                                    break block23;
                                }
                                bl3 = false;
                            }
                            iconEditor.mirrorX = bl3;
                        }
                        String[] stringArray = b;
                        string = stringArray[24];
                        f4 = f;
                        f3 = f5 + 46.0f;
                    }
                    if (!IconEditor.toolButton(string, f4, f3, this.mirrorY, MIRROR, true, b[28])) break block25;
                    IconEditor iconEditor = this;
                    bl = this.mirrorY;
                    if (bl2) break block26;
                    if (bl) break block27;
                    bl = true;
                    break block26;
                }
                bl = false;
            }
            iconEditor.mirrorY = bl;
        }
    }

    private static void tip(String string) {
        ImGui.pushFont((ImFont)Im.label);
        ImGui.setTooltip((String)string);
        ImGui.popFont();
    }

    
    private static boolean toolButton(String var0, float var1_1, float var2_2, boolean var3_3, float[][] var4_4, boolean var5_5, String var6_6) {
        block36: {
            block35: {
                block34: {
                    block33: {
                        block32: {
                            block30: {
                                block31: {
                                    block29: {
                                        block27: {
                                            block28: {
                                                ImGui.setCursorScreenPos((float)var1_1, (float)var2_2);
                                                var8_7 = ImGui.invisibleButton((String)var0, (float)40.0f, (float)40.0f);
                                                var9_8 = ImGui.isItemHovered();
                                                var7_9 = ImScreen.a;
                                                if (var9_8) {
                                                    IconEditor.tip(var6_6);
                                                }
                                                var10_10 = ImGui.getWindowDrawList();
                                                v1 = var10_10;
                                                v2 = var1_1;
                                                v3 = var2_2;
                                                v4 = var1_1 + 40.0f;
                                                v5 = var2_2 + 40.0f;
                                                v6 = var3_3;
                                                if (var7_9) break block27;
                                                if (!v6) break block28;
                                                v8 = Ui.col(Menu.ACCENT, 0.12f);
                                                break block29;
                                            }
                                            v6 = var9_8;
                                        }
                                        v10 = v6 != false ? 0.065f : 0.03f;
                                        v8 = Ui.wash(v10);
                                    }
                                    v1.addRectFilled(v2, v3, v4, v5, v8, 9.0f);
                                    v12 = var10_10;
                                    v13 = var1_1;
                                    v14 = var2_2;
                                    v15 = var1_1 + 40.0f;
                                    v16 = var2_2;
                                    v17 = 40.0f;
                                    if (var7_9) break block30;
                                    v16 = v16 + v17;
                                    if (!var3_3) break block31;
                                    v19 = Ui.col(Menu.ACCENT, 0.85f);
                                    break block32;
                                }
                                v17 = 0.08f;
                            }
                            v19 = Ui.wash(v17);
                        }
                        v12.addRect(v13, v14, v15, v16, v19, 9.0f);
                        v21 = var3_3;
                        if (var7_9) break block33;
                        if (!v21) {
                        }
                        v21 = var9_8;
                    }
                    if (!v21) break block34;
lbl75:

                    v23 = Im.INK;
                    break block35;
                }
                v23 = Im.SUB;
            }
            var11_11 = Ui.col(v23);
            var12_12 = var4_4;
            var13_13 = var12_12.length;
            var14_14 = 0;
            while (var14_14 < var13_13) {
                block37: {
                    var15_15 = var12_12[var14_14];
                    v25 = 2;
                    if (var7_9) break block36;
                    for (var16_16 = v1553331; var16_16 < var15_15.length; var16_16 += 2) {
                        block38: {
                            var17_17 = var15_15[var16_16 - 2] * 0.75f;
                            var18_18 = var15_15[var16_16 - 1] * 0.75f;
                            if (var7_9) break block37;
                            var19_19 = var15_15[var16_16] * 0.75f;
                            var20_20 = var15_15[var16_16 + 1] * 0.75f;
                            if (var7_9) break block38;
                            if (var5_5) {
                            }
                            var10_10.addLine(var1_1 + 11.0f + var18_18, var2_2 + 11.0f + var17_17, var1_1 + 11.0f + var20_20, var2_2 + 11.0f + var19_19, var11_11, 1.5f);
                        }
                        if (!var7_9) continue;
lbl111:

                        var10_10.addLine(var1_1 + 11.0f + var17_17, var2_2 + 11.0f + var18_18, var1_1 + 11.0f + var19_19, var2_2 + 11.0f + var20_20, var11_11, 1.5f);
                        continue;
                    }
                    ++var14_14;
                }
                if (!var7_9) continue;
            }
            v25 = var8_7;
        }
        return (boolean)v25;
    }

    private static int ink(int n, float f) {
        int n2;
        block4: {
            block5: {
                boolean bl = ImScreen.a;
                n2 = n;
                if (bl) break block4;
                if (n2 != 1) break block5;
                return Ui.col(Im.INK, f);
            }
            n2 = ImGui.getColorU32((float)((float)(n >> 16 & 0xFF) / 255.0f), (float)((float)(n >> 8 & 0xFF) / 255.0f), (float)((float)(n & 0xFF) / 255.0f), (float)((float)(n >>> 24) / 255.0f * f));
        }
        return n2;
    }

    private static void pixels(int[][] nArray, float f, float f2, float f3) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        boolean bl = ImScreen.a;
        int n = 0;
        while (n < nArray.length) {
            block6: {
                for (int i = 0; i < nArray[n].length; ++i) {
                    if (bl) break block6;
                    if (nArray[n][i] == 0) continue;
                    imDrawList.addRectFilled(f + (float)i * f3, f2 + (float)n * f3, f + (float)(i + 1) * f3, f2 + (float)(n + 1) * f3, IconEditor.ink(nArray[n][i], 1.0f));
                    continue;
                }
                ++n;
            }
            if (!bl) continue;
        }
    }

    private void canvas(IconAnimation iconAnimation, float f, float f2, float f3, int n) {
        ImDrawList imDrawList;
        float f4;
        block91: {
            IconEditor iconEditor;
            ImDrawList imDrawList2;
            block98: {
                block94: {
                    boolean bl;
                    block92: {
                        block93: {
                            float f5;
                            int n2;
                            int n3;
                            block86: {
                                boolean bl2;
                                int[][] nArray;
                                block82: {
                                    block83: {
                                        IconEditor iconEditor2;
                                        int n4;
                                        int n5;
                                        block84: {
                                            block85: {
                                                float f6;
                                                block80: {
                                                    block81: {
                                                        float f7;
                                                        block79: {
                                                            block76: {
                                                                block78: {
                                                                    block77: {
                                                                        float f8;
                                                                        float f9;
                                                                        block75: {
                                                                            nArray = iconAnimation.frame(this.frame);
                                                                            f4 = f3 * (float)n;
                                                                            float f10 = ImGui.getMousePosX();
                                                                            bl = ImScreen.a;
                                                                            float f11 = ImGui.getMousePosY();
                                                                            ImGui.setCursorScreenPos((float)f, (float)f2);
                                                                            ImGui.invisibleButton((String)b[44], (float)f4, (float)f4, (int)3);
                                                                            n5 = (int)((f10 - f) / f3);
                                                                            n4 = (int)((f11 - f2) / f3);
                                                                            f9 = f10;
                                                                            f8 = f;
                                                                            if (bl) break block75;
                                                                            if (!(f9 >= f8)) break block76;
                                                                            f9 = f11;
                                                                            f8 = f2;
                                                                        }
                                                                        float f12 = f9 - f8;
                                                                        f7 = f12 == 0.0f ? 0 : (f12 > 0.0f ? 1 : -1);
                                                                        if (bl) break block77;
                                                                        if (f7 < 0) break block76;
                                                                        f7 = n5;
                                                                    }
                                                                    if (bl) break block78;
                                                                    if (f7 >= n) break block76;
                                                                    f7 = n4;
                                                                }
                                                                if (bl) break block79;
                                                                if (f7 >= n) break block76;
                                                                f7 = 1.0f;
                                                                break block79;
                                                            }
                                                            f7 = 0.0f;
                                                        }
                                                        f6 = f7;
                                                        bl2 = ImGui.isItemActivated();
                                                        if (bl) break block80;
                                                        if (!bl2) break block81;
                                                        bl2 = f6;
                                                        if (bl) break block82;
                                                        if (!bl2) break block83;
                                                        this.press(nArray, n5, n4, ImGui.isMouseDown((int)1));
                                                        if (!bl) break block83;
                                                    }
                                                    bl2 = ImGui.isItemActive();
                                                }
                                                if (bl) break block82;
                                                if (!bl2) break block83;
                                                bl2 = this.painting;
                                                if (bl) break block82;
                                                if (!bl2) break block83;
                                                bl2 = f6;
                                                if (bl) break block82;
                                                if (!bl2) break block83;
                                                iconEditor2 = this;
                                                if (bl) break block84;
                                                if (iconEditor2.lineX < 0) break block85;
                                                this.lineToX = n5;
                                                this.lineToY = n4;
                                                if (!bl) break block83;
                                            }
                                            iconEditor2 = this;
                                        }
                                        iconEditor2.put(n5, n4, this.paintValue);
                                    }
                                    bl2 = ImGui.isItemDeactivated();
                                }
                                if (bl2) {
                                    this.release();
                                }
                                imDrawList2 = ImGui.getWindowDrawList();
                                imDrawList2.addRectFilled(f, f2, f + f4, f2 + f4, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)0.35f), 8.0f);
                                int[][] nArray2 = iconAnimation.frame(this.frame - 1);
                                n3 = 0;
                                while (n3 < n) {
                                    block87: {
                                        if (bl) break block86;
                                        for (n2 = 0; n2 < n; ++n2) {
                                            int[][] nArray3;
                                            float f13;
                                            block90: {
                                                block88: {
                                                    block89: {
                                                        f13 = f + (float)n2 * f3;
                                                        if (bl) break block87;
                                                        f5 = f2 + (float)n3 * f3;
                                                        nArray3 = nArray;
                                                        if (bl) break block88;
                                                        if (nArray3[n3][n2] == 0) break block89;
                                                        imDrawList2.addRectFilled(f13, f5, f13 + f3, f5 + f3, IconEditor.ink(nArray[n3][n2], 1.0f));
                                                        if (!bl) continue;
                                                    }
                                                    nArray3 = nArray2;
                                                }
                                                if (bl) break block90;
                                                if (nArray3 == null) continue;
                                                nArray3 = nArray2;
                                            }
                                            if (nArray3[n3][n2] == 0) continue;
                                            imDrawList2.addRectFilled(f13, f5, f13 + f3, f5 + f3, IconEditor.ink(nArray2[n3][n2], 0.18f));
                                            continue;
                                        }
                                        ++n3;
                                    }
                                    if (!bl) continue;
                                }
                                n3 = Ui.wash(0.06f);
                            }
                            for (n2 = 1; n2 < n; ++n2) {
                                imDrawList2.addLine(f + (float)n2 * f3, f2, f + (float)n2 * f3, f2 + f4, n3);
                                imDrawList = imDrawList2;
                                if (!bl) {
                                    imDrawList.addLine(f, f2 + (float)n2 * f3, f + f4, f2 + (float)n2 * f3, n3);
                                    if (!bl) continue;
                                    break;
                                }
                                break block91;
                            }
                            iconEditor = this;
                            if (bl) break block92;
                            if (iconEditor.lineX < 0) break block93;
                            for (int[] nArray : IconEditor.line(this.lineX, this.lineY, this.lineToX, this.lineToY)) {
                                int n6;
                                float f14;
                                float f15;
                                float f16;
                                float f17;
                                ImDrawList imDrawList3;
                                block97: {
                                    IconEditor iconEditor3;
                                    block95: {
                                        block96: {
                                            f5 = f + (float)nArray[0] * f3;
                                            if (bl) break block94;
                                            float f18 = f2 + (float)nArray[1] * f3;
                                            imDrawList3 = imDrawList2;
                                            f17 = f5 + 1.0f;
                                            f16 = f18 + 1.0f;
                                            f15 = f5 + f3 - 1.0f;
                                            f14 = f18 + f3 - 1.0f;
                                            iconEditor3 = this;
                                            if (bl) break block95;
                                            if (iconEditor3.paintValue != 0) break block96;
                                            n6 = this.brush;
                                            break block97;
                                        }
                                        iconEditor3 = this;
                                    }
                                    n6 = iconEditor3.paintValue;
                                }
                                imDrawList3.addRectFilled(f17, f16, f15, f14, IconEditor.ink(n6, 0.6f));
                                if (!bl) continue;
                            }
                        }
                        iconEditor = this;
                    }
                    if (bl) break block98;
                    if (!iconEditor.mirrorX) break block94;
                    imDrawList2.addLine(f + f4 / 2.0f, f2, f + f4 / 2.0f, f2 + f4, Ui.col(Menu.ACCENT, 0.6f));
                }
                iconEditor = this;
            }
            if (iconEditor.mirrorY) {
                imDrawList2.addLine(f, f2 + f4 / 2.0f, f + f4, f2 + f4 / 2.0f, Ui.col(Menu.ACCENT, 0.6f));
            }
            imDrawList = imDrawList2;
        }
        imDrawList.addRect(f, f2, f + f4, f2 + f4, Ui.wash(0.16f), 8.0f);
    }

    private void press(int[][] nArray, int n, int n2, boolean n3) {
        block35: {
            IconEditor iconEditor;
            block33: {
                block34: {
                    boolean bl;
                    block31: {
                        block32: {
                            int n4;
                            block27: {
                                IconEditor iconEditor2;
                                block29: {
                                    block30: {
                                        block28: {
                                            block24: {
                                                IconEditor iconEditor3;
                                                block25: {
                                                    block26: {
                                                        bl = ImScreen.a;
                                                        iconEditor3 = this;
                                                        if (bl) break block24;
                                                        if (iconEditor3.tool != IconEditor$Tool.PICK) break block25;
                                                        if (nArray[n2][n] == 0) break block26;
                                                        this.brush = nArray[n2][n];
                                                        this.brushCustom = false;
                                                    }
                                                    return;
                                                }
                                                iconEditor3 = this;
                                            }
                                            n4 = n3;
                                            if (bl) break block27;
                                            if (n4 != 0) break block28;
                                            iconEditor2 = this;
                                            if (bl) break block29;
                                            if (iconEditor2.tool != IconEditor$Tool.ERASE) break block30;
                                        }
                                        n4 = 0;
                                        break block27;
                                    }
                                    iconEditor2 = this;
                                }
                                n4 = iconEditor2.brush;
                            }
                            iconEditor3.paintValue = n4;
                            if (bl) break block31;
                            if (this.tool != IconEditor$Tool.FILL) break block32;
                            this.fill(nArray, n, n2);
                            ConfigManager.save();
                            return;
                        }
                        iconEditor = this;
                        if (bl) break block33;
                        iconEditor.painting = true;
                    }
                    if (!IconEditor.shiftDown()) break block34;
                    this.lineX = this.lineToX = n;
                    this.lineY = this.lineToY = n2;
                    if (!bl) break block35;
                }
                iconEditor = this;
            }
            iconEditor.put(n, n2, this.paintValue);
        }
    }

    private void release() {
        block13: {
            IconEditor iconEditor;
            block10: {
                block12: {
                    boolean bl;
                    block11: {
                        bl = ImScreen.a;
                        iconEditor = this;
                        if (bl) break block10;
                        if (iconEditor.lineX < 0) break block11;
                        for (int[] nArray : IconEditor.line(this.lineX, this.lineY, this.lineToX, this.lineToY)) {
                            if (!bl) {
                                this.put(nArray[0], nArray[1], this.paintValue);
                                if (!bl) continue;
                                break;
                            }
                            break block12;
                        }
                    }
                    this.lineToY = -1;
                    this.lineToX = -1;
                    this.lineY = -1;
                    this.lineX = -1;
                    if (bl) break block13;
                }
                iconEditor = this;
            }
            if (iconEditor.painting) {
                ConfigManager.save();
            }
            this.painting = false;
        }
    }

    private void put(int n, int n2, int n3) {
        block21: {
            IconEditor iconEditor;
            int n4;
            block22: {
                boolean bl;
                block20: {
                    block18: {
                        block19: {
                            block16: {
                                block17: {
                                    n4 = IconStore.get(this.active).dim();
                                    bl = ImScreen.a;
                                    iconEditor = this;
                                    if (bl) break block16;
                                    IconStore.set(iconEditor.active, this.frame, n, n2, n3);
                                    if (!this.mirrorX) break block17;
                                    IconStore.set(this.active, this.frame, n4 - 1 - n, n2, n3);
                                }
                                iconEditor = this;
                            }
                            if (bl) break block18;
                            if (!iconEditor.mirrorY) break block19;
                            IconStore.set(this.active, this.frame, n, n4 - 1 - n2, n3);
                        }
                        iconEditor = this;
                    }
                    if (bl) break block20;
                    if (!iconEditor.mirrorX) break block21;
                    iconEditor = this;
                }
                if (bl) break block22;
                if (!iconEditor.mirrorY) break block21;
                iconEditor = this;
            }
            IconStore.set(iconEditor.active, this.frame, n4 - 1 - n, n4 - 1 - n2, n3);
        }
    }

    private static List<int[]> line(int n, int n2, int n3, int n4) {
        int n5;
        int n6;
        int n7;
        int n8;
        boolean bl;
        ArrayList<int[]> arrayList;
        block23: {
            block24: {
                int n9;
                block21: {
                    block22: {
                        arrayList = new ArrayList<int[]>();
                        bl = ImScreen.a;
                        n8 = Math.abs(n3 - n);
                        n9 = n;
                        if (bl) break block21;
                        if (n9 >= n3) break block22;
                        n9 = 1;
                        break block21;
                    }
                    n9 = -1;
                }
                n7 = n9;
                n6 = -Math.abs(n4 - n2);
                n5 = n2;
                if (bl) break block23;
                if (n5 >= n4) break block24;
                n5 = 1;
                break block23;
            }
            n5 = -1;
        }
        int n10 = n5;
        int n11 = n8 + n6;
        do {
            int n12;
            block27: {
                int n13;
                block28: {
                    int n14;
                    block25: {
                        arrayList.add(new int[]{n, n2});
                        if (n == n3) {
                            n14 = n2;
                            if (bl || bl) break block25;
                            if (n14 == n4) {
                                break;
                            }
                        }
                        n14 = 2 * n11;
                    }
                    n13 = n14;
                    n12 = n13;
                    if (bl) break block27;
                    if (n12 < n6) break block28;
                    n11 += n6;
                    n += n7;
                }
                n12 = n13;
            }
            if (bl || n12 > n8) continue;
            n11 += n8;
            n12 = n2 = n2 + n10;
        } while (!bl);
        return arrayList;
    }

    private void fill(int[][] nArray, int n, int n2) {
        int n3;
        int n4;
        boolean bl;
        int n5;
        block31: {
            block32: {
                n5 = nArray.length;
                bl = ImScreen.a;
                n4 = nArray[n2][n];
                n3 = n4;
                if (bl) break block31;
                if (n3 != this.paintValue) break block32;
                return;
            }
            n3 = n5;
        }
        boolean[][] blArray = new boolean[n3][n5];
        ArrayDeque<int[]> arrayDeque = new ArrayDeque<int[]>();
        arrayDeque.add(new int[]{n, n2});
        blArray[n2][n] = true;
        while (!arrayDeque.isEmpty()) {
            int[] nArray2 = (int[])arrayDeque.poll();
            block29: while (true) {
                int[][] nArrayArray;
                int[] nArray3 = nArray2;
                this.put(nArray3[0], nArray3[1], this.paintValue);
                int[][] nArrayArray2 = nArrayArray = new int[][]{{nArray3[0] - 1, nArray3[1]}, {nArray3[0] + 1, nArray3[1]}, {nArray3[0], nArray3[1] - 1}, {nArray3[0], nArray3[1] + 1}};
                int n6 = nArrayArray2.length;
                int n7 = 0;
                while (n7 < n6) {
                    block33: {
                        block34: {
                            int n8;
                            int[] nArray4;
                            block40: {
                                block39: {
                                    block37: {
                                        block38: {
                                            block36: {
                                                int[] nArray5;
                                                block35: {
                                                    nArray2 = nArrayArray2[n7];
                                                    if (bl) continue block29;
                                                    nArray4 = nArray2;
                                                    if (bl) break block33;
                                                    if (nArray4[0] < 0) break block34;
                                                    nArray5 = nArray4;
                                                    if (bl) break block35;
                                                    if (nArray5[1] < 0) break block34;
                                                    nArray5 = nArray4;
                                                }
                                                n8 = nArray5[0];
                                                if (bl) break block36;
                                                if (n8 >= n5) break block34;
                                                n8 = nArray4[1];
                                            }
                                            if (bl) break block37;
                                            if (n8 < n5) break block38;
                                            break block34;
                                        }
                                        n8 = blArray[nArray4[1]][nArray4[0]];
                                    }
                                    if (bl) break block39;
                                    if (n8 != 0) break block34;
                                    n8 = nArray[nArray4[1]][nArray4[0]];
                                }
                                if (!bl && n8 == n4) break block40;
                                break block34;
                            }
                            blArray[nArray4[1]][nArray4[0]] = true;
                            n8 = arrayDeque.add(nArray4) ? 1 : 0;
                        }
                        ++n7;
                    }
                    if (!bl) continue;
                }
                break;
            }
            if (!bl) continue;
        }
    }

    
    private void frames(IconAnimation var1_1, float var2_2, float var3_3, float var4_4) {
        block77: {
            block80: {
                block79: {
                    block78: {
                        block73: {
                            block75: {
                                block76: {
                                    block74: {
                                        block71: {
                                            block72: {
                                                block57: {
                                                    var6_5 = var1_1.frameCount();
                                                    var7_6 = -1;
                                                    var5_7 = ImScreen.a;
                                                    var8_8 = Math.min(46.0f, (float)Math.floor((var4_4 - (float)(8 * var6_5)) / (float)(var6_5 + 1)));
                                                    var9_9 = var2_2 + (var4_4 - (float)(var6_5 + 1) * var8_8 - (float)(var6_5 * 8)) / 2.0f;
                                                    var10_10 = ImGui.getWindowDrawList();
                                                    var11_11 = 0;
                                                    while (var11_11 < var6_5) {
                                                        block69: {
                                                            block70: {
                                                                block67: {
                                                                    block68: {
                                                                        block66: {
                                                                            block64: {
                                                                                block65: {
                                                                                    block62: {
                                                                                        block63: {
                                                                                            block61: {
                                                                                                block60: {
                                                                                                    block58: {
                                                                                                        block59: {
                                                                                                            ImGui.setCursorScreenPos((float)var9_9, (float)var3_3);
                                                                                                            v0 = var11_11;
                                                                                                            if (var5_7) break block57;
                                                                                                            ImGui.pushID((int)v0);
                                                                                                            var16_16 = IconEditor.b;
                                                                                                            var12_12 = (int)ImGui.invisibleButton((String)var16_16[40], (float)var8_8, (float)var8_8, (int)3);
                                                                                                            ImGui.popID();
                                                                                                            var13_13 = ImGui.isItemHovered();
                                                                                                            v1 = var11_11;
                                                                                                            if (var5_7) break block58;
                                                                                                            if (v1 != this.frame) break block59;
                                                                                                            v1 = 1;
                                                                                                            break block58;
                                                                                                        }
                                                                                                        v1 = 0;
                                                                                                    }
                                                                                                    var14_14 = v1;
                                                                                                    v4 = var13_13;
                                                                                                    if (var5_7) break block60;
                                                                                                    if (v4 == 0) break block61;
                                                                                                    v4 = var6_5;
                                                                                                }
                                                                                                if (v4 > 1) {
                                                                                                    IconEditor.tip(IconEditor.b[41]);
                                                                                                }
                                                                                            }
                                                                                            v7 = var10_10;
                                                                                            v8 = var9_9;
                                                                                            v9 = var3_3;
                                                                                            v10 = var9_9 + var8_8;
                                                                                            v11 = var3_3;
                                                                                            v12 = var8_8;
                                                                                            if (var5_7) break block62;
                                                                                            v11 = v11 + v12;
                                                                                            if (var13_13 == 0) break block63;
                                                                                            v12 = 0.065f;
                                                                                            break block62;
                                                                                        }
                                                                                        v12 = 0.03f;
                                                                                    }
                                                                                    v7.addRectFilled(v8, v9, v10, v11, Ui.wash(v12), 8.0f);
                                                                                    v15 = var10_10;
                                                                                    v16 = var9_9;
                                                                                    v17 = var3_3;
                                                                                    v18 = var9_9 + var8_8;
                                                                                    v19 = var3_3;
                                                                                    v20 = var8_8;
                                                                                    if (var5_7) break block64;
                                                                                    v19 = v19 + v20;
                                                                                    if (var14_14 == 0) break block65;
                                                                                    v22 = Ui.col(Menu.ACCENT, 0.85f);
                                                                                    break block66;
                                                                                }
                                                                                v20 = 0.08f;
                                                                            }
                                                                            v22 = Ui.wash(v20);
                                                                        }
                                                                        v15.addRect(v16, v17, v18, v19, v22, 8.0f);
                                                                        var15_15 = String.valueOf(var11_11 + 1);
                                                                        v24 = Im.label;
                                                                        v25 = var9_9 + (float)Math.floor((var8_8 - Ui.width(Im.label, var15_15)) / 2.0f);
                                                                        v26 = Ui.mid(Im.label, var3_3, var8_8);
                                                                        v27 = var14_14 != 0 ? Im.INK : Im.MUTE;
                                                                        Ui.text(v24, v25, v26, v27, var15_15);
                                                                        v29 = var12_12;
                                                                        if (var5_7) break block67;
                                                                        if (v29 != 0) break block68;
                                                                        break block69;
                                                                    }
                                                                    v31 = 1;
                                                                    if (var5_7) break block70;
                                                                    v29 = (int)ImGui.isMouseReleased((int)v31);
                                                                }
                                                                if (v29 == 0) // goto
                                                                v31 = var11_11;
                                                            }
                                                            var7_6 = v31;
                                                            if (!var5_7) break block69;
lbl126:

                                                            this.frame = var11_11;
                                                        }
                                                        ++var11_11;
                                                        var9_9 += var8_8 + 8.0f;
                                                        if (!var5_7) continue;
                                                    }
                                                    ImGui.setCursorScreenPos((float)var9_9, (float)var3_3);
                                                    var16_16 = IconEditor.b;
                                                    var11_11 = (int)ImGui.invisibleButton((String)var16_16[4], (float)var8_8, (float)var8_8);
                                                    v0 = (int)ImGui.isItemHovered();
                                                }
                                                var12_12 = v0;
                                                v34 = var12_12;
                                                if (var5_7) break block71;
                                                if (v34 == 0) break block72;
                                                IconEditor.tip(IconEditor.b[8]);
                                            }
                                            v34 = var12_12;
                                        }
                                        if (v34 != 0) {
                                            var10_10.addRectFilled(var9_9, var3_3, var9_9 + var8_8, var3_3 + var8_8, Ui.col(Im.QUIET), 8.0f);
                                        }
                                        Edit.dashed(var9_9, var3_3, var8_8, var8_8, 8.0f);
                                        v38 = IconEditor.b[2];
                                        v39 = var9_9 + var8_8 / 2.0f - 8.0f;
                                        v40 = var3_3 + var8_8 / 2.0f - 8.0f;
                                        v41 = 16.0f;
                                        v42 = var12_12 != 0 ? Im.INK : Im.MUTE;
                                        v44 = Ui.icon(v38, v39, v40, v41, v42);
                                        if (var5_7) break block73;
                                        if (var7_6 >= 0) {
                                        }
                                        v46 = var1_1.removeFrame(var7_6);
                                        if (var5_7) break block74;
                                        if (v46 == 0) break block75;
                                        v49 = this;
                                        if (var5_7) break block76;
                                        v46 = v49.frame;
                                    }
                                    if (v46 < var1_1.frameCount()) break block75;
                                    v49 = this;
                                }
                                v49.frame = var1_1.frameCount() - 1;
                            }
                            ConfigManager.save();
                            if (!var5_7) break block77;
lbl213:

                            v44 = var11_11;
                        }
                        if (var5_7) break block78;
                        if (v44 == 0) break block77;
                        v44 = (int)IconEditor.shiftDown();
                    }
                    if (v44 == 0) break block79;
                    var1_1.duplicateFrame(this.frame);
                    ++this.frame;
                    if (!var5_7) break block80;
                }
                var1_1.addFrame();
                this.frame = var1_1.frameCount() - 1;
            }
            ConfigManager.save();
        }
    }

    
    private void options(IconAnimation var1_1, boolean var2_2, float var3_3, float var4_4) {
        block146: {
            block147: {
                block127: {
                    block145: {
                        block144: {
                            block117: {
                                block118: {
                                    block156: {
                                        block155: {
                                            block119: {
                                                block120: {
                                                    block122: {
                                                        block121: {
                                                            block154: {
                                                                block114: {
                                                                    block115: {
                                                                        block116: {
                                                                            block113: {
                                                                                block111: {
                                                                                    block112: {
                                                                                        var6_5 = ImGui.getWindowDrawList();
                                                                                        var5_6 = ImScreen.a;
                                                                                        v0 = var1_1.frameCount();
                                                                                        if (var5_6) break block111;
                                                                                        if (v0 <= 1) break block112;
                                                                                        v0 = 1;
                                                                                        break block111;
                                                                                    }
                                                                                    v0 = 0;
                                                                                }
                                                                                var7_7 = v0;
                                                                                var8_8 = (IconEditor.PRESETS.size() + 3) / 4;
                                                                                v3 = var4_4;
                                                                                v4 = 190 + var8_8 * 58;
                                                                                v5 = 6;
                                                                                if (var5_6) break block113;
                                                                                v4 = v4 - v5;
                                                                                if (!var2_2) break block114;
                                                                                v5 = 122;
                                                                            }
                                                                            v8 = var7_7;
                                                                            if (var5_6) break block115;
                                                                            if (v8 == 0) break block116;
                                                                            v8 = 42;
                                                                            break block115;
                                                                        }
                                                                        v8 = 0;
                                                                    }
                                                                    v10 = v5 + v8;
                                                                    break block154;
                                                                }
                                                                v10 = 0;
                                                            }
                                                            var9_9 = v3 - (float)(v4 + v10) / 2.0f;
                                                            var10_10 = var3_3 + 250.0f - 12.0f;
                                                            if (var5_6) break block117;
                                                            if (!var2_2) break block118;
                                                            var21_11 = IconEditor.b;
                                                            Ui.label(var3_3, var9_9, var21_11[20]);
                                                            var6_5.addRectFilled(var3_3, var9_9 += 22.0f, var3_3 + 84.0f, var9_9 + 84.0f, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)0.35f), 8.0f);
                                                            var6_5.addRect(var3_3, var9_9, var3_3 + 84.0f, var9_9 + 84.0f, Ui.wash(0.16f), 8.0f);
                                                            var11_12 = var1_1.frame(this.frame);
                                                            if (var5_6) break block119;
                                                            if (this.playing == 0L) break block120;
                                                            var12_14 = (int)((System.currentTimeMillis() - this.playing) / ((long)var1_1.frameDelay() * IconEditor.g));
                                                            if (var5_6) break block121;
                                                            if (var12_14 < var1_1.frameCount()) break block122;
                                                            this.playing = 0L;
                                                        }
                                                        if (!var5_6) break block120;
                                                    }
                                                    var11_12 = var1_1.frame(var12_14);
                                                }
                                                IconEditor.pixels(var11_12, var3_3 + (float)(84 - var11_12.length * 4) / 2.0f, var9_9 + (float)(84 - var11_12.length * 4) / 2.0f, 4.0f);
                                                if (var5_6) break block155;
                                            }
                                            if (Edit.button(IconEditor.b[38], IconEditor.b[36], var3_3 + 96.0f + Edit.buttonWidth(IconEditor.b[3], true), var9_9 + 28.0f, true)) {
                                                this.playing = System.currentTimeMillis();
                                            }
                                            v16 = var9_9;
                                            if (var5_6) break block156;
                                            var9_9 = v16 + 84.0f;
                                        }
                                        if (var7_7 != 0) {
                                            block125: {
                                                block126: {
                                                    block123: {
                                                        block124: {
                                                            Edit.group(var3_3, var9_9 += 8.0f, 250.0f, 1);
                                                            var21_11 = IconEditor.b;
                                                            Edit.name(var21_11[33], var3_3, var9_9);
                                                            var12_14 = 20;
                                                            var13_16 = Math.min(1.0f, (float)(var1_1.getSpeed() - 1) / (float)(var12_14 - 1));
                                                            var14_18 = Edit.slider(var21_11[30], var10_10 - 160.0f, var9_9 + 8.0f, 110.0f, var13_16);
                                                            v17 = var14_18 == var13_16 ? 0 : (var14_18 > var13_16 ? 1 : -1);
                                                            if (var5_6) break block123;
                                                            if (v17 == false) break block124;
                                                            var1_1.setSpeed(Math.round(1.0f + var14_18 * (float)(var12_14 - 1)));
                                                        }
                                                        v17 = (float)ImGui.isItemDeactivated();
                                                    }
                                                    if (var5_6) break block125;
                                                    if (v17 == false) break block126;
                                                    ConfigManager.save();
                                                }
                                                v17 = var1_1.frameDelay() * 50;
                                            }
                                            var21_11 = IconEditor.b;
                                            Edit.value((int)v17 + var21_11[9], var10_10, var9_9 + 7.0f);
                                            var9_9 += 34.0f;
                                        }
                                        v16 = var9_9 + 16.0f;
                                    }
                                    var9_9 = v16;
                                }
                                var21_11 = IconEditor.b;
                                Ui.label(var3_3, var9_9, var21_11[22]);
                                var9_9 += 22.0f;
                            }
                            var11_13 = 0;
                            for (var12_14 = 0; var12_14 < IconEditor.PALETTE.length + 2; ++var12_14) {
                                block143: {
                                    block141: {
                                        block142: {
                                            block139: {
                                                block140: {
                                                    block138: {
                                                        block136: {
                                                            block137: {
                                                                block134: {
                                                                    block135: {
                                                                        block132: {
                                                                            block133: {
                                                                                block130: {
                                                                                    block131: {
                                                                                        block128: {
                                                                                            block129: {
                                                                                                if (var5_6) break block127;
                                                                                                v21 = var12_14;
                                                                                                if (var5_6) break block128;
                                                                                                if (v21 != IconEditor.PALETTE.length + 1) break block129;
                                                                                                v21 = 1;
                                                                                                break block128;
                                                                                            }
                                                                                            v21 = 0;
                                                                                        }
                                                                                        var13_17 = v21;
                                                                                        v25 = var12_14;
                                                                                        if (var5_6) break block130;
                                                                                        if (v25 != 0) break block131;
                                                                                        v25 = 1;
                                                                                        break block132;
                                                                                    }
                                                                                    v25 = var13_17;
                                                                                }
                                                                                if (var5_6) break block132;
                                                                                if (v25 == 0) break block133;
                                                                                v25 = this.custom;
                                                                                break block132;
                                                                            }
                                                                            v25 = IconEditor.PALETTE[var12_14 - 1];
                                                                        }
                                                                        var14_19 = v25;
                                                                        var15_20 = var3_3 + (float)(var12_14 % 8 * 32);
                                                                        var16_22 = var9_9 + (float)(var12_14 / 8 * 32);
                                                                        ImGui.setCursorScreenPos((float)var15_20, (float)var16_22);
                                                                        ImGui.pushID((int)var12_14);
                                                                        var21_11 = IconEditor.b;
                                                                        var17_23 = ImGui.invisibleButton((String)var21_11[13], (float)26.0f, (float)26.0f);
                                                                        ImGui.popID();
                                                                        v30 = ImGui.isItemHovered();
                                                                        if (var5_6) break block134;
                                                                        if (v30 == 0) break block135;
                                                                        v30 = var12_14;
                                                                        if (var5_6) break block134;
                                                                        if (v30 != 0) break block135;
                                                                        IconEditor.tip(IconEditor.b[25]);
                                                                    }
                                                                    v30 = ImGui.isItemHovered();
                                                                }
                                                                if (var5_6) break block136;
                                                                if (v30 == 0) break block137;
                                                                v30 = var13_17;
                                                                if (var5_6) break block136;
                                                                if (v30 == 0) break block137;
                                                                IconEditor.tip(IconEditor.b[27]);
                                                            }
                                                            v39 = var6_5;
                                                            v40 = var15_20;
                                                            v41 = var16_22;
                                                            v42 = var15_20 + 26.0f;
                                                            v43 = var16_22 + 26.0f;
                                                            v44 = var14_19;
                                                            if (var5_6) break block138;
                                                            v39.addRectFilled(v40, v41, v42, v43, IconEditor.ink(v44, 1.0f), 6.0f);
                                                            v30 = var12_14;
                                                        }
                                                        if (v30 == 0) {
                                                            var6_5.addRectFilled(var15_20 + 13.0f, var16_22, var15_20 + 26.0f, var16_22 + 26.0f, ImGui.getColorU32((float)0.078f, (float)0.086f, (float)0.122f, (float)1.0f), 6.0f, 160);
                                                        }
                                                        v39 = var6_5;
                                                        v40 = var15_20;
                                                        v41 = var16_22;
                                                        v42 = var15_20 + 26.0f;
                                                        v43 = var16_22 + 26.0f;
                                                        v44 = Ui.wash(0.16f);
                                                    }
                                                    v39.addRect(v40, v41, v42, v43, v44, 6.0f);
                                                    v47 = var13_17;
                                                    if (var5_6) break block139;
                                                    if (v47 == 0) break block140;
                                                    v47 = (int)this.brushCustom;
                                                    if (var5_6) break block141;
                                                    if (v47 == 0) break block142;
                                                }
                                                v47 = (int)this.brushCustom;
                                            }
                                            if (var5_6) break block141;
                                            if (v47 != 0) break block142;
                                            v47 = this.brush;
                                            if (var5_6) break block141;
                                            if (v47 != var14_19) break block142;
lbl321:

                                            var6_5.addRect(var15_20 - 3.0f, var16_22 - 3.0f, var15_20 + 29.0f, var16_22 + 29.0f, Ui.col(Menu.ACCENT), 8.0f, 0, 2.0f);
                                        }
                                        v47 = (int)var17_23;
                                    }
                                    if (var5_6) break block143;
                                    if (v47 == 0) {
                                        continue;
                                    }
                                    this.brushCustom = var13_17;
                                    this.brush = var14_19;
                                    v47 = var13_17;
                                }
                                var11_13 = v47;
                                if (!var5_6) continue;
                            }
                            v56 = var11_13;
                            if (var5_6) break block144;
                            if (v56 == 0) break block145;
                            v56 = 3;
                        }
                        v58 = new float[v56];
                        v58[0] = (float)(this.custom >> 16 & 255) / 255.0f;
                        v58[1] = (float)(this.custom >> 8 & 255) / 255.0f;
                        v58[2] = (float)(this.custom & 255) / 255.0f;
                        ImGui.colorConvertRGBtoHSV((float[])v58, (float[])this.hsv);
                        var21_11 = IconEditor.b;
                        ImGui.openPopup((String)var21_11[7]);
                    }
                    this.picker(var3_3 + 250.0f - 206.0f, var9_9 + 64.0f);
                    var21_11 = IconEditor.b;
                    Ui.label(var3_3, var9_9 += 74.0f, var21_11[45]);
                    Edit.group(var3_3, var9_9 += 22.0f, 250.0f, 1);
                    Edit.name(var21_11[0], var3_3, var9_9);
                }
                var12_15 = (float)Math.sqrt(Math.max(0.0f, (var1_1.getSize() - 0.5f) / 7.5f));
                var21_11 = IconEditor.b;
                var13_16 = Edit.slider(var21_11[21], var10_10 - 160.0f, var9_9 + 8.0f, 110.0f, var12_15);
                v59 = var13_16 == var12_15 ? 0 : (var13_16 > var12_15 ? 1 : -1);
                if (var5_6) break block146;
                if (v59 == false) break block147;
                var1_1.setSize((float)Math.round((0.5f + var13_16 * var13_16 * 7.5f) * 4.0f) / 4.0f);
            }
            v59 = (float)ImGui.isItemDeactivated();
        }
        if (v59 != false) {
            ConfigManager.save();
        }
        var21_11 = IconEditor.b;
        Edit.value(String.format(Locale.ROOT, var21_11[37], new Object[]{Float.valueOf(var1_1.getSize())}), var10_10, var9_9 + 7.0f);
        Ui.label(var3_3, var9_9 += 50.0f, var21_11[16]);
        var9_9 += 22.0f;
        var14_18 = 58.0f;
        var15_21 = 0;
        while (var15_21 < IconEditor.PRESETS.size()) {
            block152: {
                block153: {
                    block150: {
                        block151: {
                            block148: {
                                block149: {
                                    var16_22 = var3_3 + (float)(var15_21 % 4) * (var14_18 + 6.0f);
                                    var17_24 = var9_9 + (float)(var15_21 / 4 * 58);
                                    ImGui.setCursorScreenPos((float)var16_22, (float)var17_24);
                                    var18_25 = ImGui.invisibleButton((String)IconEditor.PRESETS.get(var15_21).name(), (float)var14_18, (float)52.0f);
                                    var19_26 = ImGui.isItemHovered();
                                    if (var19_26) {
                                        IconEditor.tip(IconEditor.PRESETS.get(var15_21).name());
                                    }
                                    v63 = var6_5;
                                    v64 = var16_22;
                                    v65 = var17_24;
                                    v66 = var16_22 + var14_18;
                                    v67 = var17_24;
                                    v68 = 52.0f;
                                    if (var5_6) break block148;
                                    v67 = v67 + v68;
                                    if (!var19_26) break block149;
                                    v68 = 0.065f;
                                    break block148;
                                }
                                v68 = 0.03f;
                            }
                            v63.addRectFilled(v64, v65, v66, v67, Ui.wash(v68), 8.0f);
                            v71 = var6_5;
                            v72 = var16_22;
                            v73 = var17_24;
                            v74 = var16_22 + var14_18;
                            v75 = var17_24;
                            v76 = 52.0f;
                            if (var5_6) break block150;
                            v75 = v75 + v76;
                            if (!var19_26) break block151;
                            v76 = 0.16f;
                            break block150;
                        }
                        v76 = 0.08f;
                    }
                    v71.addRect(v72, v73, v74, v75, Ui.wash(v76), 8.0f);
                    var20_27 = IconEditor.PRESETS.get(var15_21).grid();
                    if (var5_6) break block152;
                    IconEditor.pixels(var20_27, var16_22 + (var14_18 - (float)(var20_27.length * 2)) / 2.0f, var17_24 + (float)(52 - var20_27.length * 2) / 2.0f, 2.0f);
                    if (!var18_25) break block153;
                    IconStore.replace(this.active, this.frame, var20_27);
                    ConfigManager.save();
                }
                ++var15_21;
            }
            if (!var5_6) continue;
        }
    }

    private static int rgb(float[] fArray) {
        float[] fArray2 = new float[3];
        ImGui.colorConvertHSVtoRGB((float[])fArray, (float[])fArray2);
        return Math.round(fArray2[0] * 255.0f) << 16 | Math.round(fArray2[1] * 255.0f) << 8 | Math.round(fArray2[2] * 255.0f);
    }

    
    private void picker(float f, float f2) {
        block26: {
            boolean bl;
            block33: {
                float f3;
                float f4;
                ImDrawList imDrawList;
                float f5;
                block32: {
                    boolean bl2;
                    block31: {
                        int n;
                        block28: {
                            IconEditor iconEditor;
                            block30: {
                                block29: {
                                    int n2;
                                    block27: {
                                        block25: {
                                            int n3;
                                            bl2 = ImScreen.a;
                                            f5 = 186.0f;
                                            if (bl2) return;
                                            ImGui.setNextWindowPos((float)f, (float)f2);
                                            ImGui.setNextWindowSize((float)(f5 + 20.0f), (float)0.0f);
                                            Edit.popup();
                                            ImGui.pushStyleVar((int)2, (float)10.0f, (float)10.0f);
                                            if (!ImGui.beginPopup((String)b[46])) break block26;
                                            Ui.lift();
                                            imDrawList = ImGui.getWindowDrawList();
                                            f4 = ImGui.getCursorScreenPosX();
                                            float f6 = ImGui.getCursorScreenPosY();
                                            float f7 = ImGui.getMousePosX();
                                            float f8 = ImGui.getMousePosY();
                                            bl = false;
                                            int n4 = ImGui.invisibleButton((String)b[49], (float)f5, (float)112.0f);
                                            if (!bl2) {
                                                if (ImGui.isItemActive()) {
                                                    this.hsv[1] = Math.max(0.0f, Math.min(1.0f, (f7 - f4) / f5));
                                                    this.hsv[2] = 1.0f - Math.max(0.0f, Math.min(1.0f, (f8 - f6) / 112.0f));
                                                    bl = true;
                                                }
                                                n4 = Edit.u32(IconEditor.rgb(new float[]{this.hsv[0], 1.0f, 1.0f}));
                                            }
                                            int n5 = n4;
                                            int n6 = Edit.u32(IconEditor.a(27341, 5548175601601866756L));
                                            int n7 = ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                                            int n8 = ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                                            imDrawList.addRectFilledMultiColor(f4, f6, f4 + f5, f6 + 112.0f, n6, n5, n5, n6);
                                            imDrawList.addRectFilledMultiColor(f4, f6, f4 + f5, f6 + 112.0f, n7, n7, n8, n8);
                                            imDrawList.addCircle(f4 + this.hsv[1] * f5, f6 + (1.0f - this.hsv[2]) * 112.0f, 5.0f, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)0.5f), 16, 3.0f);
                                            imDrawList.addCircle(f4 + this.hsv[1] * f5, f6 + (1.0f - this.hsv[2]) * 112.0f, 5.0f, n6, 16, 2.0f);
                                            f3 = f6 + 121.0f;
                                            ImGui.setCursorScreenPos((float)f4, (float)(f3 - 4.0f));
                                            int n9 = ImGui.invisibleButton((String)b[10], (float)f5, (float)16.0f);
                                            if (!bl2) {
                                                if (ImGui.isItemActive()) {
                                                    this.hsv[0] = Math.max(0.0f, Math.min(1.0f, (f7 - f4) / f5));
                                                    bl = true;
                                                }
                                                n9 = n3 = 0;
                                            }
                                            while (n3 < 6) {
                                                n2 = Edit.u32(IconEditor.rgb(new float[]{(float)n3 / 6.0f, 1.0f, 1.0f}));
                                                int n10 = Edit.u32(IconEditor.rgb(new float[]{(float)(n3 + 1) / 6.0f, 1.0f, 1.0f}));
                                                if (!bl2) {
                                                    imDrawList.addRectFilledMultiColor(f4 + f5 * (float)n3 / 6.0f, f3, f4 + f5 * (float)(n3 + 1) / 6.0f, f3 + 8.0f, n2, n10, n10, n2);
                                                    ++n3;
                                                    if (!bl2) continue;
                                                }
                                                break block25;
                                            }
                                            imDrawList.addCircleFilled(f4 + this.hsv[0] * f5, f3 + 4.0f, 6.0f, n6, 16);
                                            imDrawList.addRectFilled(f4, f3 += 18.0f, f4 + 20.0f, f3 + 20.0f, Edit.u32(IconEditor.rgb(this.hsv)), 4.0f);
                                            if (bl2) break block27;
                                            imDrawList.addRect(f4, f3, f4 + 20.0f, f3 + 20.0f, Ui.wash(0.16f), 4.0f);
                                        }
                                        if (!this.hexing) {
                                            this.hex.set(String.format(Locale.ROOT, b[14], IconEditor.rgb(this.hsv)));
                                        }
                                        ImGui.setCursorScreenPos((float)(f4 + 26.0f), (float)f3);
                                        ImGui.setNextItemWidth((float)(f5 - 26.0f));
                                        ImGui.pushStyleVar((int)11, (float)7.0f, (float)4.5f);
                                        n = 12;
                                        if (bl2) break block28;
                                        ImGui.pushStyleVar((int)n, (float)5.0f);
                                        ImGui.pushFont((ImFont)Im.label);
                                    }
                                    if (!ImGui.inputText((String)b[34], (ImString)this.hex, (int)8)) break block29;
                                    iconEditor = this;
                                    if (bl2) break block30;
                                    String string = iconEditor.hex.get().trim().replace("#", "");
                                    if (string.matches(b[15])) {
                                        n2 = Integer.parseInt(string, 16);
                                        ImGui.colorConvertRGBtoHSV((float[])new float[]{(float)(n2 >> 16 & 0xFF) / 255.0f, (float)(n2 >> 8 & 0xFF) / 255.0f, (float)(n2 & 0xFF) / 255.0f}, (float[])this.hsv);
                                        bl = true;
                                    }
                                }
                                this.hexing = ImGui.isItemActive();
                                ImGui.popFont();
                                if (bl2) break block31;
                                ImGui.popStyleVar((int)2);
                                iconEditor = this;
                            }
                            n = iconEditor.hexing;
                        }
                        if (n == 0) break block32;
                        Ui.rim(f4 + 26.0f, f3, f5 - 26.0f, 20.0f, 5.0f, Menu.ICON_TOP, Menu.ICON_FOOT);
                    }
                    if (!bl2) break block33;
                }
                imDrawList.addRect(f4 + 26.0f, f3, f4 + f5, f3 + 20.0f, Ui.wash(0.16f), 5.0f);
            }
            if (bl) {
                this.brush = this.custom = IconEditor.a(2020, 6806737623285399855L) | IconEditor.rgb(this.hsv);
            }
            ImGui.endPopup();
        }
        ImGui.popStyleVar();
        Edit.unpopup();
    }

    
    static {
        block30: {
            block29: {
                block28: {
                    block27: {
                        block26: {
                            var15 = new String[50];
                            var13_1 = 0;
                            var12_2 = "\\\u0007gh\u0005\u0005F\u0007ij\u0013\u0004\u007f\bsw\u0004_\bg}\bn\u0000bb\u0012g=j\u0007B\u000bbq\fc#\u0005L\bce\u0012\b,Geq\u0013r?b#N\u0000b$\u0001&6}\u0005kaL&#g\r`p@r?/\u0007it\u0019&$g\ru$\u000fh5\u0002b\u0017\u0003g\u0011c\u001bK\u0016gsL&8`\bb$\u0013n9i\u0010&b\u000ftpnDjm\u000ec\u0010L\u0016iw\u0013n1f\u0016&E\u0004b?a\u0017\u0006l\u000bjk\u0015t\u0005,A628\u000bTT+=!+\u0016R\u001f0y\n\\\u0010gv\u0014&6}\u000bk\tL\u0016iw\u0013n1f\u0016\r_\reo@gpl\u000bjk\u0015t\u0005]\u0001ua\u0014\u0007_\u0016cr\tc'\u0006,Gum\u001ac\u0006L\u000bjk\u0015t\u0014B\rtv\u000ftpc\u0001`p@r?/\u0016oc\br\u0002b\u001d A\u000b&g\u000fj?z\u0016*$\u0002j5a\u0000u$\u0017o$gDrl\u0005&'`\u0016j`\u0005J\u0016gw\u0005\u000f_\reo@g>vDek\fi%}\u0014B\rtv\u000ftp{\u000bv$\u0014ipm\u000brp\u000fk\u0004K\u0016gs\u0007,Gut\u0005c4\u0004I\rjh\tL\u0016iw\u0013n1f\u0016\u0005\\\u0014ca\u0004\u0005,Gna\u0018\u0004K\u000bha\u0004\u007f\bg}\u0005*J4b\u0018\u0004_\bg}\u0004K\u000bha\u0005i\u0016gi\u0005\u0015]\ral\u0014&3c\reo@r?/\u0016ci\u000fp5\u0005]\u0001ua\u0014\u0010A\u000brl\th7/\u0010i$\u0004t1xJ\u0006l\u0005hr\u0001u\u0004\\\r|a\b,Geq\u0013r?b\u0002b\u001c";
                            var14_3 = "\\\u0007gh\u0005\u0005F\u0007ij\u0013\u0004\u007f\bsw\u0004_\bg}\bn\u0000bb\u0012g=j\u0007B\u000bbq\fc#\u0005L\bce\u0012\b,Geq\u0013r?b#N\u0000b$\u0001&6}\u0005kaL&#g\r`p@r?/\u0007it\u0019&$g\ru$\u000fh5\u0002b\u0017\u0003g\u0011c\u001bK\u0016gsL&8`\bb$\u0013n9i\u0010&b\u000ftpnDjm\u000ec\u0010L\u0016iw\u0013n1f\u0016&E\u0004b?a\u0017\u0006l\u000bjk\u0015t\u0005,A628\u000bTT+=!+\u0016R\u001f0y\n\\\u0010gv\u0014&6}\u000bk\tL\u0016iw\u0013n1f\u0016\r_\reo@gpl\u000bjk\u0015t\u0005]\u0001ua\u0014\u0007_\u0016cr\tc'\u0006,Gum\u001ac\u0006L\u000bjk\u0015t\u0014B\rtv\u000ftpc\u0001`p@r?/\u0016oc\br\u0002b\u001d A\u000b&g\u000fj?z\u0016*$\u0002j5a\u0000u$\u0017o$gDrl\u0005&'`\u0016j`\u0005J\u0016gw\u0005\u000f_\reo@g>vDek\fi%}\u0014B\rtv\u000ftp{\u000bv$\u0014ipm\u000brp\u000fk\u0004K\u0016gs\u0007,Gut\u0005c4\u0004I\rjh\tL\u0016iw\u0013n1f\u0016\u0005\\\u0014ca\u0004\u0005,Gna\u0018\u0004K\u000bha\u0004\u007f\bg}\u0005*J4b\u0018\u0004_\bg}\u0004K\u000bha\u0005i\u0016gi\u0005\u0015]\ral\u0014&3c\reo@r?/\u0016ci\u000fp5\u0005]\u0001ua\u0014\u0010A\u000brl\th7/\u0010i$\u0004t1xJ\u0006l\u0005hr\u0001u\u0004\\\r|a\b,Geq\u0013r?b\u0002b\u001c".length();
                            var11_4 = 5;
                            var10_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var10_5;
                                v1 = var12_2.substring(v0, v0 + var11_4);
                                v2 = -1;
                                break block26;
                                break;
                            }
lbl12:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                var12_2 = ",Gog\u000fh\u0002|\u0012";
                                var14_3 = ",Gog\u000fh\u0002|\u0012".length();
                                var11_4 = 6;
                                var10_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var10_5;
                                    v1 = var12_2.substring(v4, v4 + var11_4);
                                    v2 = 0;
                                    break block26;
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
                                break block27;
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
                                    v10 = 15;
                                    break;
                                }
                                case 1: {
                                    v10 = 100;
                                    break;
                                }
                                case 2: {
                                    v10 = 6;
                                    break;
                                }
                                case 3: {
                                    v10 = 4;
                                    break;
                                }
                                case 4: {
                                    v10 = 96;
                                    break;
                                }
                                case 5: {
                                    v10 = 6;
                                    break;
                                }
                                default: {
                                    v10 = 80;
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
                    IconEditor.b = var15;
                    var2_8 = 7500551120840399024L;
                    var8_9 = new long[11];
                    var5_10 = 0;
                    var6_11 = "L\u00a2\u00a9\u00fe\u0091\u00b9 \u00c8^<&\u001a\u00c5o\u0019\u009f\u000f Nq\u0087\u00d5\u00b4\u000e\"\u00fe\f\u00aaUQ\u008bK\u00e5v/\u0014\u00ae\u001f\u00cf\u0005x\u0004\u001c\u0017\u007f\u00e0\u00f2Ng@l\u00bcfmR\u00f3\u00d6nTaz\u0015p\u00e0C\u001dq3\u00e0\u00e2U\u00bf";
                    var7_12 = "L\u00a2\u00a9\u00fe\u0091\u00b9 \u00c8^<&\u001a\u00c5o\u0019\u009f\u000f Nq\u0087\u00d5\u00b4\u000e\"\u00fe\f\u00aaUQ\u008bK\u00e5v/\u0014\u00ae\u001f\u00cf\u0005x\u0004\u001c\u0017\u007f\u00e0\u00f2Ng@l\u00bcfmR\u00f3\u00d6nTaz\u0015p\u00e0C\u001dq3\u00e0\u00e2U\u00bf".length();
                    var4_13 = 0;
                    while (true) {
                        var9_14 = var6_11.substring(var4_13, var4_13 += 8).getBytes("ISO-8859-1");
                        v11 = var8_9;
                        v12 = var5_10++;
                        v13 = ((long)var9_14[0] & 255L) << 56 | ((long)var9_14[1] & 255L) << 48 | ((long)var9_14[2] & 255L) << 40 | ((long)var9_14[3] & 255L) << 32 | ((long)var9_14[4] & 255L) << 24 | ((long)var9_14[5] & 255L) << 16 | ((long)var9_14[6] & 255L) << 8 | (long)var9_14[7] & 255L;
                        v14 = -1;
                        break block28;
                        break;
                    }
lbl89:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_13 < var7_12) continue;
                        var6_11 = "-\u00efI\u00bc\u00fa\u001c\u0081\u0005\u0096'\u00e6\u00d5\u00f8\u0005%\u0017";
                        var7_12 = "-\u00efI\u00bc\u00fa\u001c\u0081\u0005\u0096'\u00e6\u00d5\u00f8\u0005%\u0017".length();
                        var4_13 = 0;
                        while (true) {
                            var9_14 = var6_11.substring(var4_13, var4_13 += 8).getBytes("ISO-8859-1");
                            v11 = var8_9;
                            v12 = var5_10++;
                            v13 = ((long)var9_14[0] & 255L) << 56 | ((long)var9_14[1] & 255L) << 48 | ((long)var9_14[2] & 255L) << 40 | ((long)var9_14[3] & 255L) << 32 | ((long)var9_14[4] & 255L) << 24 | ((long)var9_14[5] & 255L) << 16 | ((long)var9_14[6] & 255L) << 8 | (long)var9_14[7] & 255L;
                            v14 = 0;
                            break block28;
                            break;
                        }
                        break;
                    }
lbl102:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_13 < var7_12) continue;
                        break block29;
                        break;
                    }
                }
                v15 = v13 ^ var2_8;
                switch (v14) {
                    default: {
                        continue;
                    }
                    case 0:
lbl113:

                    continue;
                }
            }
            IconEditor.c = var8_9;
            IconEditor.d = new Integer[11];
            IconEditor.BOTH = IconEditor.a(8053, 2566595330342984118L);
            break block30;
lbl119:

            while (true) {
                continue;
                break;
            }
        }
        var0_15 = 7023754958083428001L;
        while (true)
        IconEditor.g = 7023754958083427987L ^ var0_15;
        IconEditor.PALETTE = new int[]{-1, IconEditor.a(10870, 7625498458422258878L), IconEditor.a(4411, 8838074753030363125L), -28640, -8128, IconEditor.a(3153, 1020029988318579358L), IconEditor.a(3709, 2678837205216790704L), IconEditor.a(15949, 4431598011111454855L), IconEditor.a(24379, 4067934384773945849L), IconEditor.a(11343, 8080170518382881411L)};
        IconEditor.PRESETS = CrosshairShape.presets();
        IconEditor.TOOLS = IconEditor$Tool.values();
        v16 = new String[4];
        var10_6 = IconEditor.b;
        v16[0] = var10_6[11];
        v16[1] = var10_6[26];
        v16[2] = var10_6[31];
        v16[3] = var10_6[18];
        IconEditor.TIPS = v16;
        IconEditor.GLYPHS = new float[][][]{new float[][]{{4.0f, 20.0f, 8.0f, 20.0f, 19.0f, 9.0f, 15.0f, 5.0f, 4.0f, 16.0f, 4.0f, 20.0f}, {13.5f, 6.5f, 17.5f, 10.5f}}, new float[][]{{4.0f, 15.0f, 13.0f, 6.0f, 19.0f, 12.0f, 12.0f, 19.0f, 7.0f, 19.0f, 4.0f, 15.0f}, {12.0f, 19.0f, 20.0f, 19.0f}}, new float[][]{{5.0f, 12.0f, 12.0f, 5.0f, 19.0f, 12.0f, 12.0f, 19.0f, 5.0f, 12.0f}, {19.0f, 17.0f, 19.0f, 20.0f}}, new float[][]{{14.0f, 6.0f, 18.0f, 10.0f}, {16.0f, 4.0f, 20.0f, 8.0f, 17.0f, 11.0f, 13.0f, 7.0f, 16.0f, 4.0f}, {13.0f, 9.0f, 5.0f, 17.0f, 5.0f, 20.0f, 8.0f, 20.0f, 16.0f, 12.0f}}};
        IconEditor.MIRROR = new float[][]{{12.0f, 4.0f, 12.0f, 20.0f}, {8.0f, 8.0f, 4.0f, 12.0f, 8.0f, 16.0f}, {16.0f, 8.0f, 20.0f, 12.0f, 16.0f, 16.0f}};
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6CA;
        if (d[n2] == null) {
            IconEditor.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }
}
