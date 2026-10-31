package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;

final class HitboxesModule$Group {
    final BooleanSetting on;
    final BooleanSetting lines;
    final ColorSetting lineColor;
    final BooleanSetting fill;
    final ColorSetting fillColor;
    final NumberSetting width;
    final NumberSetting scale;
    private static final String[] a;
    private static final long b;

    HitboxesModule$Group(String string, boolean bl, Setting ... settingArray) {
        this.on = new BooleanSetting(string, bl);
        String[] stringArray = a;
        this.lines = new BooleanSetting(stringArray[5], true);
        this.lineColor = new ColorSetting(stringArray[1], -1, false);
        this.fill = new BooleanSetting(stringArray[3], false);
        this.fillColor = new ColorSetting(stringArray[4], (int)b, true);
        this.width = new NumberSetting(stringArray[2], 1.0, 8.0, 2.0, 1.0);
        this.scale = new NumberSetting(stringArray[0], 0.25, 3.0, 1.0, 0.05);
        this.on.children(this.lines, this.lineColor, this.fill, this.fillColor, this.width, this.scale);
        this.on.children(settingArray);
    }

    Setting[] all() {
        return new Setting[]{this.on, this.lines, this.lineColor, this.fill, this.fillColor, this.width, this.scale};
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[6];
                    var5_1 = 0;
                    var4_2 = "<\r&{-\r \u001b3{!V\"O\r({'J\n#\u0007)rhO.\u000b\u001a/\u0004)\u0007+{";
                    var6_3 = "<\r&{-\r \u001b3{!V\"O\r({'J\n#\u0007)rhO.\u000b\u001a/\u0004)\u0007+{".length();
                    var3_4 = 5;
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
                        var4_2 = ")\u0007+{h[(\u0003\u00015\u0007 \u001b3{!V\"";
                        var6_3 = ")\u0007+{h[(\u0003\u00015\u0007 \u001b3{!V\"".length();
                        var3_4 = 10;
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
                            v10 = 111;
                            break;
                        }
                        case 1: {
                            v10 = 110;
                            break;
                        }
                        case 2: {
                            v10 = 71;
                            break;
                        }
                        case 3: {
                            v10 = 23;
                            break;
                        }
                        case 4: {
                            v10 = 72;
                            break;
                        }
                        case 5: {
                            v10 = 56;
                            break;
                        }
                        default: {
                            v10 = 71;
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
            HitboxesModule$Group.a = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 8492274797324198489L;
        while (true)
        HitboxesModule$Group.b = -5887399469301031514L ^ var0_7;
    }
}
