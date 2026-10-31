package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.HideArmorModule$1;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import net.minecraft.entity.EquipmentSlot;

public final class HideArmorModule
extends Module {
    private static HideArmorModule instance;
    private final BooleanSetting helmet;
    private final BooleanSetting chest;
    private final BooleanSetting legs;
    private final BooleanSetting boots;
    private final BooleanSetting self;
    private final BooleanSetting others;
    private static final String[] b;

    public static HideArmorModule get() {
        return instance;
    }

    public HideArmorModule() {
        String[] stringArray = b;
        super(stringArray[6], stringArray[1], Category.PLAYER, false);
        this.helmet = new BooleanSetting(stringArray[5], false);
        this.chest = new BooleanSetting(stringArray[0], false);
        this.legs = new BooleanSetting(stringArray[7], false);
        this.boots = new BooleanSetting(stringArray[2], false);
        this.self = new BooleanSetting(stringArray[4], true);
        this.others = new BooleanSetting(stringArray[3], true);
        instance = this;
        this.addSettings(this.helmet, this.chest, this.legs, this.boots, this.self, this.others);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean hidesAny(boolean bl) {
        HideArmorModule hideArmorModule;
        boolean bl2;
        block35: {
            block36: {
                boolean bl3;
                block33: {
                    block34: {
                        block31: {
                            block32: {
                                bl2 = AmbienceModule.b;
                                bl3 = this.isEnabled();
                                if (bl2) break block31;
                                if (bl3) break block32;
                                return false;
                            }
                            bl3 = bl;
                        }
                        if (bl2) break block33;
                        if (!bl3) break block34;
                        hideArmorModule = this;
                        if (bl2) break block35;
                        if (hideArmorModule.self.getValue()) break block36;
                        return false;
                    }
                    hideArmorModule = this;
                    if (bl2) break block35;
                    bl3 = hideArmorModule.others.getValue();
                }
                if (bl3) break block36;
                return false;
            }
            hideArmorModule = this;
        }
        boolean bl4 = hideArmorModule.helmet.getValue();
        if (bl2) return bl4;
        if (bl4) return true;
        bl4 = this.chest.getValue();
        if (bl2) return bl4;
        if (bl4) return true;
        bl4 = this.legs.getValue();
        if (bl2) return bl4;
        if (bl4) return true;
        bl4 = this.boots.getValue();
        if (bl2) return bl4;
        if (!bl4) return false;
        return true;
    }

    
    public boolean hides(EquipmentSlot var1_1, boolean var2_2) {
        block22: {
            block23: {
                block20: {
                    block21: {
                        var3_3 = AmbienceModule.b;
                        v0 = this.isEnabled();
                        if (!var3_3) {
                            if (v0 == 0) {
                                return false;
                            }
                            v0 = var2_2;
                        }
                        if (var3_3) break block20;
                        if (v0 == 0) break block21;
                        v0 = this.self.getValue();
                        if (var3_3) break block22;
                        if (v0 == 0) {
                            return false;
                        }
                        break block23;
                    }
                    v0 = this.others.getValue();
                }
                if (!var3_3) {
                    if (v0 == 0) {
                        return false;
                    } else {
                    }
                }
                break block22;
            }
            v0 = HideArmorModule$1.$SwitchMap$net$minecraft$entity$EquipmentSlot[var1_1.ordinal()];
        }
        if (var3_3 != false) return (boolean)v0;
        switch (v0 ? 1 : 0) {
            case 1: {
                v0 = (int)this.helmet.getValue();
                return (boolean)v0;
            }
            case 2: {
                v0 = (int)this.chest.getValue();
                return (boolean)v0;
            }
            case 3: {
                v0 = (int)this.legs.getValue();
                return (boolean)v0;
            }
            case 4: {
                v0 = (int)this.boots.getValue();
                return (boolean)v0;
            }
        }
        return (boolean)0;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[8];
                var3_1 = 0;
                var2_2 = "me2C_bt@\u007f\"V\u0013@h@\u0012me2C_@nHc$\u0006\u001dX<V`9R\nme2C_csJx%\u0006jx>C\rR\u0004vi:@\u000bme2C_iyIa3R";
                var4_3 = "me2C_bt@\u007f\"V\u0013@h@\u0012me2C_@nHc$\u0006\u001dX<V`9R\nme2C_csJx%\u0006jx>C\rR\u0004vi:@\u000bme2C_iyIa3R".length();
                var1_4 = 15;
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
                    var2_2 = "me2C_`nHc$\rme2C_myBk?H\u0018R";
                    var4_3 = "me2C_`nHc$\rme2C_myBk?H\u0018R".length();
                    var1_4 = 10;
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
                        v10 = 37;
                        break;
                    }
                    case 1: {
                        v10 = 12;
                        break;
                    }
                    case 2: {
                        v10 = 86;
                        break;
                    }
                    case 3: {
                        v10 = 38;
                        break;
                    }
                    case 4: {
                        v10 = 127;
                        break;
                    }
                    case 5: {
                        v10 = 33;
                        break;
                    }
                    default: {
                        v10 = 28;
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
        HideArmorModule.b = var5;
    }
}
