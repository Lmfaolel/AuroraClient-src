package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.BooleanSetting;

public final class CleanViewModule
extends Module {
    private static CleanViewModule instance;
    private final BooleanSetting hideArrows;
    private final BooleanSetting hideFire;
    private final BooleanSetting hidePotionSwirl;
    private final BooleanSetting hideSprintDust;
    private final BooleanSetting hideHands;
    private final BooleanSetting noSwing;
    private static final String[] b;

    public static CleanViewModule get() {
        return instance;
    }

    public CleanViewModule() {
        String[] stringArray = b;
        super(stringArray[5], stringArray[0], Category.PLAYER, false);
        this.hideArrows = new BooleanSetting(stringArray[7], true);
        this.hideFire = new BooleanSetting(stringArray[1], true);
        this.hidePotionSwirl = new BooleanSetting(stringArray[2], false);
        this.hideSprintDust = new BooleanSetting(stringArray[3], false);
        this.hideHands = new BooleanSetting(stringArray[4], false);
        this.noSwing = new BooleanSetting(stringArray[6], false);
        instance = this;
        this.addSettings(this.hideArrows, this.hideFire, this.hidePotionSwirl, this.hideSprintDust, this.hideHands, this.noSwing);
    }

    public boolean hideArrows() {
        boolean bl;
        block8: {
            block7: {
                CleanViewModule cleanViewModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    cleanViewModule = this;
                    if (bl2) break block6;
                    if (!cleanViewModule.isEnabled()) break block7;
                    cleanViewModule = this;
                }
                bl = cleanViewModule.hideArrows.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public boolean hideFire() {
        boolean bl;
        block8: {
            block7: {
                CleanViewModule cleanViewModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    cleanViewModule = this;
                    if (bl2) break block6;
                    if (!cleanViewModule.isEnabled()) break block7;
                    cleanViewModule = this;
                }
                bl = cleanViewModule.hideFire.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public boolean hidePotionSwirl() {
        boolean bl;
        block8: {
            block7: {
                CleanViewModule cleanViewModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    cleanViewModule = this;
                    if (bl2) break block6;
                    if (!cleanViewModule.isEnabled()) break block7;
                    cleanViewModule = this;
                }
                bl = cleanViewModule.hidePotionSwirl.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public boolean hideSprintDust() {
        boolean bl;
        block8: {
            block7: {
                CleanViewModule cleanViewModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    cleanViewModule = this;
                    if (bl2) break block6;
                    if (!cleanViewModule.isEnabled()) break block7;
                    cleanViewModule = this;
                }
                bl = cleanViewModule.hideSprintDust.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public boolean hideHands() {
        boolean bl;
        block8: {
            block7: {
                CleanViewModule cleanViewModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    cleanViewModule = this;
                    if (bl2) break block6;
                    if (!cleanViewModule.isEnabled()) break block7;
                    cleanViewModule = this;
                }
                bl = cleanViewModule.hideHands.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public boolean noSwing() {
        boolean bl;
        block8: {
            block7: {
                CleanViewModule cleanViewModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    cleanViewModule = this;
                    if (bl2) break block6;
                    if (!cleanViewModule.isEnabled()) break block7;
                    cleanViewModule = this;
                }
                bl = cleanViewModule.noSwing.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[8];
                var3_1 = 0;
                var2_2 = "hR\u001ae`+OIT\u0005os OY[\u0002~b+\u001d\u0011r^\u0013o6\b\u0006HRWE`+\u001dVV\u000e\u0012r^\u0013o6\u001e\u0000N^\u0018d6\u001d\u0018SE\u001by\u0010r^\u0013o6\u001d\u001fH^\u0019~6\n\u001aIC\u000er^\u0013o6\u0001\u0018T\u0017?kx*\u001c\ny[\u0012kxn9SR\u0000";
                var4_3 = "hR\u001ae`+OIT\u0005os OY[\u0002~b+\u001d\u0011r^\u0013o6\b\u0006HRWE`+\u001dVV\u000e\u0012r^\u0013o6\u001e\u0000N^\u0018d6\u001d\u0018SE\u001by\u0010r^\u0013o6\u001d\u001fH^\u0019~6\n\u001aIC\u000er^\u0013o6\u0001\u0018T\u0017?kx*\u001c\ny[\u0012kxn9SR\u0000".length();
                var1_4 = 21;
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
                    var2_2 = "tXWYa'\u0001]\u00176d\u007f#\u000eN^\u0018d\u0011r^\u0013o6\u001d\u001bOT\u001c*W<\u001dU@\u0004";
                    var4_3 = "tXWYa'\u0001]\u00176d\u007f#\u000eN^\u0018d\u0011r^\u0013o6\u001d\u001bOT\u001c*W<\u001dU@\u0004".length();
                    var1_4 = 18;
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
                        v10 = 58;
                        break;
                    }
                    case 1: {
                        v10 = 55;
                        break;
                    }
                    case 2: {
                        v10 = 119;
                        break;
                    }
                    case 3: {
                        v10 = 10;
                        break;
                    }
                    case 4: {
                        v10 = 22;
                        break;
                    }
                    case 5: {
                        v10 = 78;
                        break;
                    }
                    default: {
                        v10 = 111;
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
        CleanViewModule.b = var5;
    }
}
