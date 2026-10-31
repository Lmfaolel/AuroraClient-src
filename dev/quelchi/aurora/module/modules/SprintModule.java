package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.SprintModule$Style;
import dev.quelchi.aurora.module.modules.TextHud;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.Window;

public final class SprintModule
extends TextHud {
    private final EnumSetting<SprintModule$Style> style;
    private final BooleanSetting showSprint;
    private final BooleanSetting showSneak;
    private final BooleanSetting showSwim;
    private final BooleanSetting alwaysSprint;
    private boolean forcing;
    private static final String[] d;

    public SprintModule() {
        String[] stringArray = d;
        super(stringArray[3], stringArray[15], stringArray[8], 4, 40);
        this.style = new EnumSetting<SprintModule$Style>(stringArray[18], SprintModule$Style.Short);
        this.showSprint = new BooleanSetting(stringArray[2], true);
        this.showSneak = new BooleanSetting(stringArray[6], false);
        this.showSwim = new BooleanSetting(stringArray[0], true);
        this.alwaysSprint = new BooleanSetting(stringArray[7], false);
        this.addSettings(this.style, this.showSprint, this.showSneak, this.showSwim, this.alwaysSprint);
    }

    
    @Override
    public void onTick() {
        block36: {
            block37: {
                block33: {
                    block35: {
                        block34: {
                            block32: {
                                block29: {
                                    block31: {
                                        block30: {
                                            var2_1 = MinecraftClient.method_1551();
                                            var1_2 = AmbienceModule.b;
                                            if (!this.alwaysSprint.getValue()) break block29;
                                            v0 = var2_1;
                                            if (var1_2) break block30;
                                            if (v0.field_1724 == null) break block29;
                                            v0 = var2_1;
                                        }
                                        if (var1_2) break block31;
                                        if (v0.field_1755 != null) break block29;
                                        v0 = var2_1;
                                    }
                                    v6 = v0.field_1690.field_1894.method_1434();
                                    if (var1_2) break block32;
                                    if (!v6) break block29;
                                    v6 = true;
                                    break block32;
                                }
                                v6 = false;
                            }
                            var3_3 = v6;
                            v8 = var3_3;
                            if (var1_2) break block33;
                            if (v8) {
                            }
                            v10 = var2_1.field_1690;
                            if (var1_2) break block34;
                            if (v10.field_1867.method_1434()) break block35;
                            v10 = var2_1.field_1690;
                        }
                        v10.field_1867.method_23481(true);
                    }
                    this.forcing = true;
                    if (!var1_2) break block36;
lbl77:

                    v14 = this;
                    if (var1_2) break block37;
                    v8 = v14.forcing;
                }
                if (!v8) break block36;
                v14 = this;
            }
            v14.release();
        }
    }

    @Override
    public void onDisable() {
        block5: {
            SprintModule sprintModule;
            block4: {
                boolean bl = AmbienceModule.b;
                sprintModule = this;
                if (bl) break block4;
                if (!sprintModule.forcing) break block5;
                sprintModule = this;
            }
            sprintModule.release();
        }
    }

    @Override
    public void onSettingsChanged() {
        block9: {
            SprintModule sprintModule;
            block10: {
                boolean bl;
                block8: {
                    bl = AmbienceModule.b;
                    sprintModule = this;
                    if (bl) break block8;
                    if (!sprintModule.forcing) break block9;
                    sprintModule = this;
                }
                if (bl) break block10;
                if (sprintModule.alwaysSprint.getValue()) break block9;
                sprintModule = this;
            }
            sprintModule.release();
        }
    }

    
    private void release() {
        block11: {
            KeyBinding keyBinding;
            block12: {
                boolean bl;
                KeyBinding keyBinding2;
                block8: {
                    boolean bl2;
                    block9: {
                        KeyBinding keyBinding3;
                        block10: {
                            this.forcing = false;
                            MinecraftClient minecraftClient = MinecraftClient.method_1551();
                            keyBinding2 = minecraftClient.field_1690.field_1867;
                            bl2 = AmbienceModule.b;
                            bl = (Boolean)minecraftClient.field_1690.method_42450().method_41753();
                            if (bl2) break block8;
                            if (!bl) break block9;
                            keyBinding3 = keyBinding2;
                            if (bl2) break block10;
                            if (!keyBinding3.method_1434()) break block11;
                            keyBinding3 = keyBinding2;
                        }
                        keyBinding3.method_23481(true);
                        if (!bl2) break block11;
                    }
                    keyBinding = keyBinding2;
                    if (bl2) break block12;
                    bl = SprintModule.physicallyDown(keyBinding);
                }
                if (bl) break block11;
                keyBinding = keyBinding2;
            }
            keyBinding.method_23481(false);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean physicallyDown(KeyBinding keyBinding) {
        int n;
        InputUtil.Key key;
        block9: {
            InputUtil.Key key2;
            boolean bl;
            block8: {
                key = InputUtil.method_15981((String)keyBinding.method_1428());
                bl = AmbienceModule.b;
                key2 = key;
                if (bl) break block8;
                if (key2.method_1442() != InputUtil.Type.field_1668) return false;
                key2 = key;
            }
            n = key2.method_1444();
            if (bl) return n != 0;
            if (n != InputUtil.field_16237.method_1444()) break block9;
            return false;
        }
        n = InputUtil.method_15987((Window)MinecraftClient.method_1551().method_22683(), (int)key.method_1444()) ? 1 : 0;
        return n != 0;
    }

    
    @Override
    protected List<String> lines() {
        block72: {
            block75: {
                block74: {
                    block73: {
                        block71: {
                            block66: {
                                block61: {
                                    block70: {
                                        block69: {
                                            block68: {
                                                block67: {
                                                    block64: {
                                                        block65: {
                                                            block62: {
                                                                block63: {
                                                                    block58: {
                                                                        block60: {
                                                                            block59: {
                                                                                block57: {
                                                                                    block56: {
                                                                                        block55: {
                                                                                            var2_1 = MinecraftClient.method_1551();
                                                                                            var3_2 = new ArrayList<String>(2);
                                                                                            var1_3 = AmbienceModule.b;
                                                                                            v0 = this.style.getValue() == SprintModule$Style.Full;
                                                                                            var4_4 = v0;
                                                                                            v2 = this.showSwim.getValue();
                                                                                            if (var1_3) break block55;
                                                                                            if (!v2) break block56;
                                                                                            v2 = var2_1.field_1724.method_5681();
                                                                                        }
                                                                                        if (var1_3) break block57;
                                                                                        if (!v2) break block56;
                                                                                        v2 = true;
                                                                                        break block57;
                                                                                    }
                                                                                    v2 = false;
                                                                                }
                                                                                var5_5 = v2;
                                                                                v6 = this.showSprint;
                                                                                if (var1_3) break block58;
                                                                                if (!v6.getValue()) break block59;
                                                                                v8 = this;
                                                                                if (var1_3) break block60;
                                                                                if (!v8.forcing) break block59;
                                                                                var3_2.add(SprintModule.d[17]);
                                                                                if (!var1_3) break block61;
                                                                            }
                                                                            v8 = this;
                                                                        }
                                                                        v6 = v8.showSprint;
                                                                    }
                                                                    v13 = v6.getValue();
                                                                    if (var1_3) break block62;
                                                                    if (!v13) break block63;
                                                                    v15 = var2_1.field_1690.field_1867.method_1434();
                                                                    if (var1_3) break block64;
                                                                    if (v15) break block65;
                                                                }
                                                                v13 = var4_4;
                                                            }
                                                            if (var1_3) break block66;
                                                            if (v13) break block61;
                                                            v13 = var5_5;
                                                            if (var1_3) break block66;
                                                            if (!v13) break block61;
                                                        }
                                                        v15 = var4_4;
                                                    }
                                                    if (v15) {
                                                        v21 = var3_2;
                                                        if (!((Boolean)var2_1.field_1690.method_42450().method_41753()).booleanValue()) break block67;
                                                    }
                                                    v23 = SprintModule.d[1];
                                                    break block68;
                                                }
                                                var6_6 = SprintModule.d;
                                                v23 = var6_6[14];
                                            }
                                            v21.add(SprintModule.d[11] + v23);
                                            if (!var1_3) break block61;
lbl131:

                                            v25 = var3_2;
                                            if (!var5_5) break block69;
                                            v27 = SprintModule.d[13];
                                            break block70;
                                        }
                                        var6_6 = SprintModule.d;
                                        v27 = var6_6[5];
                                    }
                                    v25.add(v27);
                                }
                                v13 = this.showSneak.getValue();
                            }
                            if (var1_3) break block71;
                            if (!v13) break block72;
                            v13 = var2_1.field_1690.field_1832.method_1434();
                        }
                        if (var1_3) break block73;
                        if (!v13) break block72;
                        v13 = var4_4;
                    }
                    if (var1_3) break block72;
                    if (v13) {
                    }
                    v33 = var3_2;
                    if (!((Boolean)var2_1.field_1690.method_42449().method_41753()).booleanValue()) break block74;
                    v35 = SprintModule.d[9];
                    break block75;
                }
                var6_6 = SprintModule.d;
                v35 = var6_6[4];
            }
            v33.add(SprintModule.d[12] + v35);
            if (!var1_3) break block72;
lbl195:

            v13 = var3_2.add(SprintModule.d[10]);
        }
        return var3_2;
    }

    @Override
    protected List<String> placeholder() {
        String string = this.style.getValue() == SprintModule$Style.Full ? d[19] : d[16];
        return List.of(string);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[20];
                var3_1 = 0;
                var2_2 = ";i\u001e\\g}p\u0001l\u001cB)i\u0007<n\u0016L+kc\u000e;i\u001e\\g}w\u001ah\u001f_.``\u0006;q\u0003B)z\u0004 d\u001dO\t;q\u0003B)zn\u0006f\r;i\u001e\\g}i\r`\u001aB)i\r)m\u0006J>}'\u001bq\u0003B)z\u0006\u001bq\u0003B)z\u0007<n\u0016L+kc\b;o\u0014J,gi\u000f\u0007;q\u0003B)z'\u0006;o\u0014J,.\b;v\u0018F*gi\u000f\u0004 d\u001dO-;q\u0003B)z'\to\u0015\u000b4`b\tjQY\"oc\u0007t\u0005\u0007gyn\u001ciQC(bcHu\u001e\u000b4~u\u0001o\u0005\t;q\u0003B)zn\u0006f\u000b)t\u0005Dg]w\u001ah\u001f_";
                var4_3 = ";i\u001e\\g}p\u0001l\u001cB)i\u0007<n\u0016L+kc\u000e;i\u001e\\g}w\u001ah\u001f_.``\u0006;q\u0003B)z\u0004 d\u001dO\t;q\u0003B)zn\u0006f\r;i\u001e\\g}i\r`\u001aB)i\r)m\u0006J>}'\u001bq\u0003B)z\u0006\u001bq\u0003B)z\u0007<n\u0016L+kc\b;o\u0014J,gi\u000f\u0007;q\u0003B)z'\u0006;o\u0014J,.\b;v\u0018F*gi\u000f\u0004 d\u001dO-;q\u0003B)z'\to\u0015\u000b4`b\tjQY\"oc\u0007t\u0005\u0007gyn\u001ciQC(bcHu\u001e\u000b4~u\u0001o\u0005\t;q\u0003B)zn\u0006f\u000b)t\u0005Dg]w\u001ah\u001f_".length();
                var1_4 = 13;
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
                    var2_2 = ";u\bG\"\u000b;q\u0003B)z' d\u001dO";
                    var4_3 = ";u\bG\"\u000b;q\u0003B)z' d\u001dO".length();
                    var1_4 = 5;
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
                        v10 = 104;
                        break;
                    }
                    case 1: {
                        v10 = 1;
                        break;
                    }
                    case 2: {
                        v10 = 113;
                        break;
                    }
                    case 3: {
                        v10 = 43;
                        break;
                    }
                    case 4: {
                        v10 = 71;
                        break;
                    }
                    case 5: {
                        v10 = 14;
                        break;
                    }
                    default: {
                        v10 = 7;
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
        SprintModule.d = var5;
    }
}
