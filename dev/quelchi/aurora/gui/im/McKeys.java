package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Mc;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Ui;
import imgui.ImGui;
import java.util.Arrays;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public final class McKeys
extends Mc {
    private static final float ROW = 34.0f;
    private static final float KEY = 127.5f;
    private static final float RESET = 85.0f;
    private static final float GAP = 8.5f;
    private final KeyBinding[] keys;
    private KeyBinding setting;
    private static final String[] c;

    public McKeys(Screen screen) {
        super(screen);
        this.keys = (KeyBinding[])MinecraftClient.method_1551().field_1690.field_1839.clone();
        Arrays.sort(this.keys);
    }

    private void bind(InputUtil.Key key) {
        this.setting.method_1422(key);
        this.setting = null;
        KeyBinding.method_1426();
    }

    @Override
    public boolean method_25404(KeyInput keyInput) {
        InputUtil.Key key;
        McKeys mcKeys;
        block12: {
            KeyInput keyInput2;
            block10: {
                block11: {
                    boolean bl;
                    block8: {
                        block9: {
                            bl = ImScreen.a;
                            mcKeys = this;
                            if (bl) break block8;
                            if (mcKeys.setting != null) break block9;
                            return super.method_25404(keyInput);
                        }
                        mcKeys = this;
                    }
                    keyInput2 = keyInput;
                    if (bl) break block10;
                    if (!keyInput2.method_74231()) break block11;
                    key = InputUtil.field_16237;
                    break block12;
                }
                keyInput2 = keyInput;
            }
            key = InputUtil.method_15985((KeyInput)keyInput2);
        }
        mcKeys.bind(key);
        return true;
    }

    @Override
    public boolean method_25402(Click click, boolean bl) {
        McKeys mcKeys;
        block4: {
            block5: {
                boolean bl2 = ImScreen.a;
                mcKeys = this;
                if (bl2) break block4;
                if (mcKeys.setting != null) break block5;
                return super.method_25402(click, bl);
            }
            mcKeys = this;
        }
        mcKeys.bind(InputUtil.Type.field_1672.method_1447(click.method_74245()));
        return true;
    }

    private boolean clashes(KeyBinding keyBinding) {
        boolean bl;
        block18: {
            boolean bl2;
            block17: {
                bl = ImScreen.a;
                bl2 = keyBinding.method_1415();
                if (bl) break block17;
                if (!bl2) break block18;
                bl2 = false;
            }
            return bl2;
        }
        KeyBinding[] keyBindingArray = this.keys;
        int n = keyBindingArray.length;
        int n2 = 0;
        while (n2 < n) {
            block19: {
                block20: {
                    boolean bl3;
                    block22: {
                        block23: {
                            KeyBinding keyBinding2;
                            block21: {
                                KeyBinding keyBinding3 = keyBindingArray[n2];
                                if (bl) break block19;
                                if (keyBinding3 == keyBinding) break block20;
                                keyBinding2 = keyBinding;
                                if (bl) break block21;
                                if (!keyBinding2.method_1435(keyBinding3)) break block20;
                                keyBinding2 = keyBinding3;
                            }
                            bl3 = keyBinding2.method_1427();
                            if (bl) break block22;
                            if (!bl3) break block23;
                            bl3 = keyBinding.method_1427();
                            if (bl) break block22;
                            if (bl3) break block20;
                        }
                        bl3 = true;
                    }
                    return bl3;
                }
                ++n2;
            }
            if (!bl) continue;
        }
        return false;
    }

    
    @Override
    protected void draw() {
        block41: {
            this.begin();
            this.title();
            var2_1 = this.child(ControlsListWidget.class);
            var3_2 = McKeys.y(var2_1.method_46427());
            var4_3 = McKeys.y(var2_1.method_55443()) - var3_2;
            var5_4 = McKeys.x(var2_1.method_25342());
            var6_5 = McKeys.x(var2_1.method_31383() - 2);
            var16_6 = McKeys.c;
            var7_7 = this.band(var16_6[1], (EntryListWidget<?>)var2_1) + McKeys.y(2);
            var8_8 = var6_5 - 85.0f - 8.5f - 127.5f;
            var1_9 = ImScreen.a;
            var9_10 = ImGui.getWindowDrawList();
            var10_11 = null;
            for (var11_12 = 0; var11_12 < this.keys.length; ++var11_12) {
                block60: {
                    block61: {
                        block59: {
                            block58: {
                                block57: {
                                    block55: {
                                        block56: {
                                            block51: {
                                                block54: {
                                                    block52: {
                                                        block53: {
                                                            block49: {
                                                                block50: {
                                                                    block47: {
                                                                        block48: {
                                                                            block46: {
                                                                                block44: {
                                                                                    block45: {
                                                                                        block42: {
                                                                                            block43: {
                                                                                                v0 = this;
                                                                                                if (var1_9) break block41;
                                                                                                var12_13 = v0.keys[var11_12];
                                                                                                v1 = var12_13.method_1423().equals(var10_11);
                                                                                                if (var1_9) break block42;
                                                                                                if (v1 != 0) break block43;
                                                                                                var10_11 = var12_13.method_1423();
                                                                                                var13_14 = var10_11.method_74697().getString();
                                                                                                Ui.mcText(McKeys.x(var2_1.method_46426() + var2_1.method_25368() / 2) + McKeys.labelX(0.0f, Ui.width(Im.mc, var13_14)), var7_7 + 15.3f, Ui.col(Im.MUTE), var13_14, false);
                                                                                                var7_7 += 34.0f;
                                                                                            }
                                                                                            v3 = var7_7;
                                                                                            v4 = var3_2;
                                                                                            if (var1_9) break block44;
                                                                                            cfr_temp_0 = v3 - (v4 + var4_3);
                                                                                            v1 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
                                                                                        }
                                                                                        if (v1 > 0) break block45;
                                                                                        v6 = var7_7 + 34.0f;
                                                                                        if (var1_9) break block46;
                                                                                        if (v6 < var3_2) {
                                                                                        }
                                                                                    }
                                                                                    v3 = var7_7;
                                                                                    v4 = 34.0f;
                                                                                }
                                                                                var7_7 = v3 + v4;
                                                                                if (!var1_9) continue;
lbl64:

                                                                                McKeys.line(var5_4 + 3.4f, var7_7 + McKeys.labelY(34.0f), var8_8 - 8.5f, Im.SUB, Text.method_43471((String)var12_13.method_1431()).getString());
                                                                                v6 = var8_8;
                                                                            }
                                                                            ImGui.setCursorScreenPos((float)v6, (float)var7_7);
                                                                            v11 = var11_12;
                                                                            if (var1_9) break block47;
                                                                            if (!ImGui.invisibleButton((String)(McKeys.c[2] + v11), (float)127.5f, (float)34.0f)) break block48;
                                                                            this.setting = var12_13;
                                                                        }
                                                                        v11 = (int)this.clashes(var12_13);
                                                                    }
                                                                    var13_15 = v11;
                                                                    v14 = var9_10;
                                                                    if (var1_9) break block49;
                                                                    Ui.fill(v14, var8_8, var7_7, var8_8 + 127.5f, var7_7 + 34.0f, Ui.col(Ui.mix(Im.QUIET, Im.QUIET_HI, Ui.hot())), 8.0f);
                                                                    if (this.setting != var12_13) break block50;
                                                                    Ui.rim(var8_8, var7_7, 127.5f, 34.0f, 8.0f, Menu.ICON_TOP, Menu.ICON_FOOT);
                                                                    if (!var1_9) break block51;
                                                                }
                                                                v14 = var9_10;
                                                            }
                                                            v18 = var8_8;
                                                            v19 = var7_7;
                                                            v20 = var8_8 + 127.5f;
                                                            v21 = var7_7;
                                                            v22 = 34.0f;
                                                            if (var1_9) break block52;
                                                            v21 = v21 + v22;
                                                            if (var13_15 == 0) break block53;
                                                            v24 = Ui.col(Im.DANGER);
                                                            break block54;
                                                        }
                                                        v22 = 0.16f;
                                                    }
                                                    v24 = Ui.wash(v22);
                                                }
                                                Ui.rect(v14, v18, v19, v20, v21, v24, 8.0f);
                                            }
                                            v26 = this.setting;
                                            if (var1_9) break block55;
                                            if (v26 != var12_13) break block56;
                                            v28 = McKeys.c[3];
                                            break block57;
                                        }
                                        v26 = var12_13;
                                    }
                                    v28 = v26.method_16007().getString();
                                }
                                var14_16 = v28;
                                var15_17 = Ui.width(Im.mc, var14_16);
                                v30 = var8_8 + Math.max(8.0f, McKeys.labelX(127.5f, var15_17));
                                v31 = var7_7 + McKeys.labelY(34.0f);
                                v32 = var8_8 + 127.5f - 6.0f;
                                if (this.setting != var12_13) break block58;
                                v33 = Im.SUB;
                                break block59;
                            }
                            v33 = var13_15 != 0 ? Im.DANGER : Im.INK;
                        }
                        McKeys.line(v30, v31, v32, v33, var14_16);
                        v36 = McKeys.c[0] + var11_12;
                        v37 = McKeys.c[4];
                        v38 = var6_5 - 85.0f;
                        v39 = var7_7;
                        v40 = 85.0f;
                        v41 = var12_13.method_1427();
                        if (var1_9) break block60;
                        if (v41) break block61;
                        v41 = true;
                        break block60;
                    }
                    v41 = false;
                }
                if (McKeys.button(v36, v37, v38, v39, v40, v41)) {
                    var12_13.method_1422(var12_13.method_1429());
                    KeyBinding.method_1426();
                }
                var7_7 += 34.0f;
                if (!var1_9) continue;
            }
            this.endList(var7_7);
            v0 = this;
        }
        v0.outside();
        ImGui.end();
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "\u000b[\u001e\u0019\u0002\u0004\u0012[\u0014\u000f\u0003\u0012[\u0014";
                var4_3 = "\u000b[\u001e\u0019\u0002\u0004\u0012[\u0014\u000f\u0003\u0012[\u0014".length();
                var1_4 = 5;
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
                    var2_2 = ")L\b\u000f\u0005'#YU\b\u0005\u0005+[\u001e\u0019\u0002";
                    var4_3 = ")L\b\u000f\u0005'#YU\b\u0005\u0005+[\u001e\u0019\u0002".length();
                    var1_4 = 11;
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
                        v10 = 121;
                        break;
                    }
                    case 1: {
                        v10 = 62;
                        break;
                    }
                    case 2: {
                        v10 = 109;
                        break;
                    }
                    case 3: {
                        v10 = 124;
                        break;
                    }
                    case 4: {
                        v10 = 118;
                        break;
                    }
                    case 5: {
                        v10 = 7;
                        break;
                    }
                    default: {
                        v10 = 66;
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
        McKeys.c = var5;
    }
}
