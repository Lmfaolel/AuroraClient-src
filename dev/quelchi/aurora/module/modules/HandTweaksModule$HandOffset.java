package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;

public final class HandTweaksModule$HandOffset {
    final NumberSetting x;
    final NumberSetting y;
    final NumberSetting z;
    final NumberSetting pitch;
    final NumberSetting yaw;
    final NumberSetting roll;
    final NumberSetting scale;
    private static final String[] a;

    HandTweaksModule$HandOffset(String string) {
        String[] stringArray = a;
        this.x = new NumberSetting(string + stringArray[4], -1.0, 1.0, 0.0, 0.01);
        this.y = new NumberSetting(string + stringArray[5], -1.0, 1.0, 0.0, 0.01);
        this.z = new NumberSetting(string + stringArray[0], -1.0, 1.0, 0.0, 0.01);
        this.pitch = new NumberSetting(string + stringArray[3], -180.0, 180.0, 0.0, 1.0);
        this.yaw = new NumberSetting(string + stringArray[6], -180.0, 180.0, 0.0, 1.0);
        this.roll = new NumberSetting(string + stringArray[2], -180.0, 180.0, 0.0, 1.0);
        this.scale = new NumberSetting(string + stringArray[1], 0.1, 2.0, 1.0, 0.01);
    }

    Setting[] settings() {
        return new Setting[]{this.x, this.y, this.z, this.pitch, this.yaw, this.roll, this.scale};
    }

    public boolean isDefault() {
        boolean bl;
        block28: {
            block23: {
                double d;
                double d2;
                boolean bl2;
                block27: {
                    block26: {
                        block25: {
                            block24: {
                                double d3;
                                block22: {
                                    bl2 = AmbienceModule.b;
                                    double d3 = this.x.getValue();
                                    d3 = 0.0;
                                    if (bl2) break block22;
                                    if (d2 != d3) break block23;
                                    d2 = this.y.getValue();
                                    if (bl2) break block24;
                                    d3 = 0.0;
                                }
                                if (d2 != d3) break block23;
                                d2 = this.z.getValue();
                            }
                            if (bl2) break block25;
                            if (d2 != 0.0) break block23;
                            d2 = this.pitch.getValue();
                        }
                        if (bl2) break block26;
                        if (d2 != 0.0) break block23;
                        d2 = this.yaw.getValue();
                    }
                    d = 0.0;
                    if (bl2) break block27;
                    if (d2 != d) break block23;
                    double d = this.roll.getValue();
                    d = 0.0;
                }
                double d4 = d2 - d;
                bl = d4 == 0.0 ? 0 : (d4 > 0.0 ? 1 : -1);
                if (bl2) break block28;
                if (bl) break block23;
                bl = true;
                break block28;
            }
            bl = false;
        }
        return bl;
    }

    public float scale() {
        return (float)this.scale.getValue();
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "C\u000b\u0006C\u0002zL!>\u0005C\u0003vA!\u0006C\u0001pY.3\u0002C\t";
                var4_3 = "C\u000b\u0006C\u0002zL!>\u0005C\u0003vA!\u0006C\u0001pY.3\u0002C\t".length();
                var1_4 = 2;
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
                    var2_2 = "C\b\u0004C\bxZ";
                    var4_3 = "C\b\u0004C\bxZ".length();
                    var1_4 = 2;
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
                        v10 = 99;
                        break;
                    }
                    case 1: {
                        v10 = 81;
                        break;
                    }
                    case 2: {
                        v10 = 25;
                        break;
                    }
                    case 3: {
                        v10 = 45;
                        break;
                    }
                    case 4: {
                        v10 = 77;
                        break;
                    }
                    case 5: {
                        v10 = 91;
                        break;
                    }
                    default: {
                        v10 = 79;
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
        HandTweaksModule$HandOffset.a = var5;
    }
}
