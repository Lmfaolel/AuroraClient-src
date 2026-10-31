package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.gui.shell.LockerScreen;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.gui.shell.Wardrobe;
import dev.quelchi.aurora.gui.shell.Wardrobe$Section;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.util.KeyUtil;
import imgui.ImGui;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

public final class EmoteWheel
extends ImScreen {
    static final int SLOTS;
    private static final float DISC = 180.0f;
    private static final float RING = 124.0f;
    private static final float HUB = 66.0f;
    private static final float SLOT = 37.0f;
    private final int key;
    private int hovered = -1;
    private boolean sticky;
    private static final String[] b;

    public EmoteWheel(int n) {
        super(b[10]);
        this.key = n;
    }

    @Override
    public void method_25420(DrawContext drawContext, int n, int n2, float f) {
    }

    public void method_25393() {
        block13: {
            EmoteWheel emoteWheel;
            block15: {
                boolean bl;
                block14: {
                    block12: {
                        bl = SearchIndex.a;
                        emoteWheel = this;
                        if (bl) break block12;
                        if (emoteWheel.key < 0) break block13;
                        emoteWheel = this;
                    }
                    if (bl) break block14;
                    if (emoteWheel.sticky) break block13;
                    emoteWheel = this;
                }
                if (bl) break block15;
                if (KeyUtil.isKeyPressed(emoteWheel.key)) break block13;
                emoteWheel = this;
            }
            emoteWheel.release();
        }
    }

    private void release() {
        EmoteWheel emoteWheel;
        block10: {
            block11: {
                boolean bl;
                block8: {
                    block9: {
                        bl = SearchIndex.a;
                        emoteWheel = this;
                        if (bl) break block8;
                        if (emoteWheel.hovered >= 0) break block9;
                        this.sticky = true;
                        return;
                    }
                    emoteWheel = this;
                }
                if (bl) break block10;
                if (!Cosmetics.wheelSlot(emoteWheel.hovered).isEmpty()) break block11;
                this.field_22787.method_1507((Screen)new LockerScreen(this.hovered));
                return;
            }
            emoteWheel = this;
        }
        emoteWheel.commit();
    }

    private void commit() {
        block8: {
            block9: {
                String string;
                block10: {
                    String string2;
                    block11: {
                        boolean bl = SearchIndex.a;
                        string2 = Cosmetics.wheelSlot(this.hovered);
                        if (bl) break block8;
                        if (string2.isEmpty()) break block9;
                        string = string2;
                        if (bl) break block10;
                        if (!string.equals(Cosmetics.getOwnEmote())) break block11;
                        string = "";
                        break block10;
                    }
                    string = string2;
                }
                Cosmetics.setOwnEmote(string);
            }
            this.method_25419();
        }
    }

    @Override
    public boolean method_25402(Click click, boolean bl) {
        int n;
        block16: {
            block17: {
                boolean bl2;
                block14: {
                    block15: {
                        boolean bl3;
                        block12: {
                            block13: {
                                bl3 = SearchIndex.a;
                                bl2 = this.hovered;
                                if (bl3) break block12;
                                if (bl2 >= false) break block13;
                                this.method_25419();
                                return true;
                            }
                            bl2 = click.method_74245();
                        }
                        if (bl3) break block14;
                        if (bl2) break block15;
                        n = this.hovered;
                        if (bl3) break block16;
                        if (!Cosmetics.wheelSlot(n).isEmpty()) break block17;
                    }
                    this.field_22787.method_1507((Screen)new LockerScreen(this.hovered));
                    bl2 = true;
                }
                return bl2;
            }
            this.commit();
            n = 1;
        }
        return n != 0;
    }

    
    @Override
    protected void draw() {
        block72: {
            block68: {
                block69: {
                    block71: {
                        block70: {
                            block65: {
                                block53: {
                                    block67: {
                                        block66: {
                                            block49: {
                                                block51: {
                                                    block52: {
                                                        block50: {
                                                            var2_1 = ImGui.getIO().getDisplaySizeX();
                                                            var1_2 = SearchIndex.a;
                                                            var3_3 = ImGui.getIO().getDisplaySizeY();
                                                            var4_4 = var2_1 / 2.0f;
                                                            var5_5 = var3_3 / 2.0f;
                                                            ImGui.setNextWindowPos((float)0.0f, (float)0.0f);
                                                            ImGui.setNextWindowSize((float)var2_1, (float)var3_3);
                                                            ImGui.pushStyleVar((int)2, (float)0.0f, (float)0.0f);
                                                            var18_6 = EmoteWheel.b;
                                                            ImGui.begin((String)var18_6[0], (int)175);
                                                            ImGui.popStyleVar();
                                                            var6_7 = ImGui.getWindowDrawList();
                                                            var6_7.addRectFilled(0.0f, 0.0f, var2_1, var3_3, Ui.col(Im.DIM, 0.66f));
                                                            var7_8 = ImGui.getMousePosX() - var4_4;
                                                            var8_9 = ImGui.getMousePosY() - var5_5;
                                                            v0 = this;
                                                            v1 = ImGui.isMousePosValid();
                                                            if (var1_2) break block49;
                                                            if (v1 == 0) break block50;
                                                            v3 = var7_8;
                                                            if (var1_2) break block51;
                                                            if (!(v3 * var7_8 + var8_9 * var8_9 < 4356.0f)) break block52;
                                                        }
                                                        v1 = -1;
                                                        break block49;
                                                    }
                                                    v3 = var7_8;
                                                }
                                                v1 = (int)Math.floor((Math.toDegrees(Math.atan2(v3, -var8_9)) + 360.0 + 30.0) % 360.0 / 60.0);
                                            }
                                            v0.hovered = v1;
                                            var6_7.addCircleFilled(var4_4, var5_5, 180.0f, Ui.col(Im.BG, 0.82f), 96);
                                            var6_7.addCircle(var4_4, var5_5, 180.0f, Ui.col(Im.RIM), 96);
                                            var6_7.addCircleFilled(var4_4, var5_5, 66.0f, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)0.3f), 64);
                                            var6_7.addCircle(var4_4, var5_5, 66.0f, Ui.wash(0.16f), 64);
                                            for (var9_10 = 0; var9_10 < 6; ++var9_10) {
                                                block60: {
                                                    block64: {
                                                        block63: {
                                                            block59: {
                                                                block61: {
                                                                    block62: {
                                                                        block58: {
                                                                            block57: {
                                                                                block56: {
                                                                                    block54: {
                                                                                        block55: {
                                                                                            var10_12 = Math.toRadians((double)var9_10 * 60.0 - 90.0);
                                                                                            var12_14 = var4_4 + (float)Math.cos(var10_12) * 124.0f;
                                                                                            if (var1_2) break block53;
                                                                                            var13_15 = var5_5 + (float)Math.sin(var10_12) * 124.0f;
                                                                                            var14_16 = Cosmetics.wheelSlot(var9_10);
                                                                                            v7 = var9_10;
                                                                                            if (var1_2) break block54;
                                                                                            if (v7 != this.hovered) break block55;
                                                                                            v7 = 1;
                                                                                            break block54;
                                                                                        }
                                                                                        v7 = 0;
                                                                                    }
                                                                                    var15_17 = v7;
                                                                                    v10 = var14_16.isEmpty();
                                                                                    if (var1_2) break block56;
                                                                                    if (v10 != 0) {
                                                                                    }
                                                                                    v10 = var15_17;
                                                                                }
                                                                                if (var1_2) break block57;
                                                                                if (v10 != 0) break block58;
                                                                                v10 = var16_18 = 0;
                                                                            }
                                                                            while (var16_18 < 20) {
                                                                                var17_19 = (float)((double)var16_18 * 3.141592653589793 / 10.0);
                                                                                v13 = var6_7;
                                                                                v14 = var12_14;
                                                                                v15 = var13_15;
                                                                                v16 = 37.0f;
                                                                                v17 = var17_19;
                                                                                if (var1_2) break block59;
                                                                                v13.pathArcTo(v14, v15, v16, v17, var17_19 + 0.19f, 4);
                                                                                var6_7.pathStroke(Ui.wash(0.16f), 0, 1.0f);
                                                                                ++var16_18;
                                                                                if (!var1_2) continue;
                                                                                v19 = Module.a == false;
                                                                                Module.a = v19;
                                                                                break;
                                                                            }
                                                                        }
                                                                        v21 = EmoteWheel.b[9];
                                                                        v22 = var12_14 - 10.0f;
                                                                        v23 = var13_15 - 10.0f;
                                                                        v24 = 20.0f;
                                                                        v25 = var15_17 != 0 ? Im.INK : Im.MUTE;
                                                                        Ui.icon(v21, v22, v23, v24, v25);
                                                                        if (!var1_2) break block60;
lbl129:

                                                                        v13 = var6_7;
                                                                        v14 = var12_14;
                                                                        v15 = var13_15;
                                                                        v16 = 37.0f;
                                                                        v27 = var15_17;
                                                                        if (var1_2) break block61;
                                                                        if (v27 == 0) break block62;
                                                                        v27 = ImGui.getColorU32((float)0.129f, (float)0.11f, (float)0.29f, (float)1.0f);
                                                                        break block61;
                                                                    }
                                                                    v27 = Ui.col(Im.QUIET);
                                                                }
                                                                v31 = 48;
                                                                if (var1_2) break block63;
                                                                v13.addCircleFilled(v14, v15, v16, v27, v31);
                                                                if (var15_17 != 0) break block64;
                                                                v13 = var6_7;
                                                                v14 = var12_14;
                                                                v15 = var13_15;
                                                                v16 = 37.0f;
                                                                v17 = 0.08f;
                                                            }
                                                            v27 = Ui.wash(v17);
                                                            v31 = 48;
                                                        }
                                                        v13.addCircle(v14, v15, v16, v27, v31);
                                                    }
                                                    v34 = var12_14;
                                                    v35 = var13_15 - 8.0f;
                                                    v36 = var14_16;
                                                    v37 = var15_17 != 0 ? Im.INK : Im.SUB;
                                                    LockerScreen.figure(v34, v35, v36, Ui.col(v37), 1.0f);
                                                }
                                                if (var15_17 == 0) continue;
                                                Ui.rim(var12_14 - 37.0f, var13_15 - 37.0f, 74.0f, 74.0f, 37.0f, Menu.ICON_TOP, Menu.ICON_FOOT);
                                                continue;
                                            }
                                            v40 = this;
                                            if (var1_2) break block65;
                                            if (v40.hovered >= 0) break block53;
                                            var18_6 = EmoteWheel.b;
                                            var9_11 = var18_6[5];
                                            if (!this.sticky) break block66;
                                            v42 = var18_6[7];
                                            break block67;
                                        }
                                        var18_6 = EmoteWheel.b;
                                        v42 = var18_6[3];
                                    }
                                    var10_13 = v42;
                                    break block72;
                                }
                                v40 = this;
                            }
                            v44 = Cosmetics.wheelSlot(v40.hovered);
                            if (var1_2) break block68;
                            if (!v44.isEmpty()) break block69;
                            var18_6 = EmoteWheel.b;
                            var9_11 = var18_6[4];
                            if (!this.sticky) break block70;
                            v46 = var18_6[1];
                            break block71;
                        }
                        var18_6 = EmoteWheel.b;
                        v46 = var18_6[2];
                    }
                    var10_13 = v46;
                    break block72;
                }
                v44 = LockerScreen.fit(Im.sora13, Wardrobe.labelOf(Wardrobe$Section.EMOTES, Cosmetics.wheelSlot(this.hovered)), 112.0f);
            }
            var9_11 = v44;
            v48 = this.sticky != false ? EmoteWheel.b[6] : EmoteWheel.b[8];
            var10_13 = v48;
        }
        Ui.text(Im.sora13, var4_4 - (float)Math.floor(Ui.width(Im.sora13, var9_11) / 2.0f), var5_5 - 17.0f, Im.INK, var9_11);
        Ui.text(Im.label, var4_4 - (float)Math.floor(Ui.width(Im.label, var10_13) / 2.0f), var5_5 + 2.0f, Im.MUTE, var10_13);
        ImGui.end();
    }

    
    static {
        var9 = new String[11];
        var7_1 = 0;
        var6_2 = "%fu#s\f\u0011by%tcH=.c#k\u000e\u0000k|#~0Yrz\u007ffl&H\u0011\u0002ay(kc]<j04z/Y3}u\u0005\u0017c`2f\u0006\u0017c\u007f2z0\r\u0011by%tcH=.`*~:\f\u0011by%tc]r}|)k\u000f\u0000k|#~0Yrz\u007ffo/]+";
        var8_3 = "%fu#s\f\u0011by%tcH=.c#k\u000e\u0000k|#~0Yrz\u007ffl&H\u0011\u0002ay(kc]<j04z/Y3}u\u0005\u0017c`2f\u0006\u0017c\u007f2z0\r\u0011by%tcH=.`*~:\f\u0011by%tc]r}|)k\u000f\u0000k|#~0Yrz\u007ffo/]+".length();
        var5_4 = 5;
        var4_5 = -1;
        block11: while (true) {
            v0 = ++var4_5;
            v1 = var6_2.substring(v0, v0 + var5_4);
            v2 = -1;
            block12: while (true) {
                var6_2 = "\"be5\u0006\u0017c\u007f2z0";
                var8_3 = "\"be5\u0006\u0017c\u007f2z0".length();
                var5_4 = 4;
                var4_5 = -1;
                while (true) {
                    v3 = ++var4_5;
                    v1 = var6_2.substring(v3, v3 + var5_4);
                    v2 = 0;
lbl21:

                    v4 = v1.toCharArray();
                    v5 = v4;
                    v6 = v4.length;
                    var10_6 = 0;
                    do {
                        v5 = v5;
                        v7 = var10_6;
                        v8 = v5[v7];
                        switch (var10_6 % 7) {
                            case 0: {
                                v9 = 82;
                                break;
                            }
                            case 1: {
                                v9 = 14;
                                break;
                            }
                            case 2: {
                                v9 = 16;
                                break;
                            }
                            case 3: {
                                v9 = 70;
                                break;
                            }
                            case 4: {
                                v9 = 31;
                                break;
                            }
                            case 5: {
                                v9 = 67;
                                break;
                            }
                            default: {
                                v9 = 60;
                            }
                        }
                        v5[v7] = (char)(v8 ^ v9);
                        ++var10_6;
lbl53:

                        v6 = v6;
                    } while (v6 > var10_6);
                    v10 = new String(v5);
                    switch (v2) {
                        default: {
                            var9[var7_1++] = v10.intern();
                            if ((var4_5 += var5_4) >= var8_3) continue block12;
                            var5_4 = var6_2.charAt(var4_5);
                            continue block11;
                        }
                        case 0: 
                    }
                    var9[var7_1++] = v10.intern();
                    if ((var4_5 += var5_4) >= var8_3) {
                        EmoteWheel.b = var9;
                        var2_7 = 4167889559585694615L;
                        var0_8 = -1837873676969958511L ^ var2_7;
                        EmoteWheel.SLOTS = (int)var0_8;
                        return;
                    }
                    var5_4 = var6_2.charAt(var4_5);
                }
                break;
            }
            break;
        }
    }
}
