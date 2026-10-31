package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.CustomCrystalsModule;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;

final class CustomCrystalsModule$PartSettings {
    final BooleanSetting show;
    final NumberSetting scale;
    final NumberSetting offset;
    final NumberSetting rotation;
    final NumberSetting bounceHeight;
    final NumberSetting bounceSpeed;
    final ColorSetting color;
    final BooleanSetting chroma;
    final NumberSetting chromaSpeed;
    final NumberSetting chromaDelay;
    final CustomCrystalsModule this$0;
    private static final String[] a;

    CustomCrystalsModule$PartSettings(CustomCrystalsModule customCrystalsModule, String string) {
        this.this$0 = customCrystalsModule;
        String[] stringArray = a;
        this.scale = new NumberSetting(stringArray[0], 0.1, 3.0, 1.0, 0.05);
        this.offset = new NumberSetting(stringArray[2], -1.0, 1.0, 0.0, 0.05);
        this.rotation = new NumberSetting(stringArray[5], 0.0, 10.0, 1.0, 0.1);
        this.bounceHeight = new NumberSetting(stringArray[7], 0.0, 3.0, 1.0, 0.05);
        this.bounceSpeed = new NumberSetting(stringArray[4], 0.0, 3.0, 1.0, 0.05);
        this.color = new ColorSetting(stringArray[8], -1, false);
        this.chroma = new BooleanSetting(stringArray[3], false);
        this.chromaSpeed = new NumberSetting(stringArray[6], 0.1, 5.0, 1.0, 0.1);
        this.chromaDelay = new NumberSetting(stringArray[1], 0.0, 1.0, 0.0, 0.05);
        this.show = new BooleanSetting(string, true);
    }

    void register(String string) {
        CustomCrystalsModule.access$000(this.this$0, new Setting[]{this.show, this.scale, this.offset, this.rotation, this.bounceHeight, this.bounceSpeed, this.color, this.chroma, this.chromaSpeed, this.chromaDelay});
        this.show.children(this.scale, this.offset, this.rotation, this.bounceHeight, this.bounceSpeed, this.color, this.chroma);
        this.chroma.children(this.chromaSpeed, this.chromaDelay);
        this.color.visibleWhen(this.this$0::tinted);
        this.chroma.visibleWhen(this.this$0::tinted);
        this.show.tab(string);
        this.scale.tab(string);
        this.offset.tab(string);
        this.rotation.tab(string);
        this.bounceHeight.tab(string);
        this.bounceSpeed.tab(string);
        this.color.tab(string);
        this.chroma.tab(string);
        this.chromaSpeed.tab(string);
        this.chromaDelay.tab(string);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[9];
                var3_1 = 0;
                var2_2 = "1\r\u001a./\f!\u0006\t-'Q0\u0006\u000b\u0017#3\u0006-\b\u001d1/D\u0006!\u0006\t-'Q\f \u0001\u000e,)U0\u0011\u001e\u001e'.\u000e0\u0001\u000f#>Y\u007f\fN\b2/Ut\f!\u0006\t-'Q0\u0011\u001e\u001e'.";
                var4_3 = "1\r\u001a./\f!\u0006\t-'Q0\u0006\u000b\u0017#3\u0006-\b\u001d1/D\u0006!\u0006\t-'Q\f \u0001\u000e,)U0\u0011\u001e\u001e'.\u000e0\u0001\u000f#>Y\u007f\fN\b2/Ut\f!\u0006\t-'Q0\u0011\u001e\u001e'.".length();
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
                    var2_2 = " \u0001\u000e,)U0\n\u000b\u0012%\"D\u0005!\u0001\u0017-8";
                    var4_3 = " \u0001\u000e,)U0\n\u000b\u0012%\"D\u0005!\u0001\u0017-8".length();
                    var1_4 = 13;
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
                        v10 = 98;
                        break;
                    }
                    case 1: {
                        v10 = 110;
                        break;
                    }
                    case 2: {
                        v10 = 123;
                        break;
                    }
                    case 3: {
                        v10 = 66;
                        break;
                    }
                    case 4: {
                        v10 = 74;
                        break;
                    }
                    case 5: {
                        v10 = 48;
                        break;
                    }
                    default: {
                        v10 = 16;
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
        CustomCrystalsModule$PartSettings.a = var5;
    }
}
