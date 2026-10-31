package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.setting.BooleanSetting;

public final class HidePlayerModelModule
extends Module {
    private static HidePlayerModelModule instance;
    private final BooleanSetting transparent;
    private static final String[] b;

    public static HidePlayerModelModule get() {
        return instance;
    }

    public HidePlayerModelModule() {
        String[] stringArray = b;
        super(stringArray[2], stringArray[1], Category.PLAYER, false);
        this.transparent = new BooleanSetting(stringArray[0], false);
        instance = this;
        this.addSettings(this.transparent);
    }

    public boolean transparent() {
        return this.transparent.getValue();
    }

    
    static {
        block12: {
            var5 = new String[3];
            var3_1 = 0;
            var2_2 = "ut\u0013\tu\u0014\u0014Sc\u001c\u0013\u0016io\u0016\u0002&\r\u001bWc\u001c\u0013i\u0016\f\u0001v\u0000\u0002p\r\u0010V\u0014io\u0016\u0002&-\u001bWc\u001c\u0013i\u0016\f\u0001K\u001d\u0003c\b";
            var4_3 = "ut\u0013\tu\u0014\u0014Sc\u001c\u0013\u0016io\u0016\u0002&\r\u001bWc\u001c\u0013i\u0016\f\u0001v\u0000\u0002p\r\u0010V\u0014io\u0016\u0002&-\u001bWc\u001c\u0013i\u0016\f\u0001K\u001d\u0003c\b".length();
            var1_4 = 11;
            var0_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var5[var3_1++] = new String(v0).intern();
                if ((var0_5 += var1_4) < var4_3) {
                    var1_4 = var2_2.charAt(var0_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var0_5;
            v2 = var2_2.substring(v1, v1 + var1_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 33;
                        break;
                    }
                    case 1: {
                        v6 = 6;
                        break;
                    }
                    case 2: {
                        v6 = 114;
                        break;
                    }
                    case 3: {
                        v6 = 103;
                        break;
                    }
                    case 4: {
                        v6 = 6;
                        break;
                    }
                    case 5: {
                        v6 = 100;
                        break;
                    }
                    default: {
                        v6 = 117;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        HidePlayerModelModule.b = var5;
    }
}
