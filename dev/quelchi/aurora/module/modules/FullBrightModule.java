package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.mixins.SimpleOptionAccessor;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.NumberSetting;
import net.minecraft.client.MinecraftClient;

public final class FullBrightModule
extends Module {
    private final NumberSetting brightness;
    private static FullBrightModule instance;
    private Double savedGamma;
    private static final String[] b;

    public FullBrightModule() {
        String[] stringArray = b;
        super(stringArray[1], stringArray[2], Category.RENDER, false);
        this.brightness = new NumberSetting(stringArray[0], 1.0, 15.0, 10.0, 1.0);
        instance = this;
        this.addSettings(this.brightness);
    }

    @Override
    public void onEnable() {
        block6: {
            MinecraftClient minecraftClient;
            block7: {
                minecraftClient = MinecraftClient.method_1551();
                boolean bl = AmbienceModule.b;
                if (minecraftClient.field_1690 == null) break block6;
                FullBrightModule fullBrightModule = this;
                if (bl) break block7;
                if (fullBrightModule.savedGamma != null) break block6;
                fullBrightModule = this;
            }
            fullBrightModule.savedGamma = (Double)minecraftClient.field_1690.method_42473().method_41753();
        }
    }

    @Override
    public void onDisable() {
        block8: {
            block10: {
                FullBrightModule fullBrightModule;
                block9: {
                    boolean bl = AmbienceModule.b;
                    MinecraftClient minecraftClient = MinecraftClient.method_1551();
                    if (bl) break block8;
                    if (minecraftClient.field_1690 == null) break block9;
                    fullBrightModule = this;
                    if (bl) break block10;
                    if (fullBrightModule.savedGamma == null) break block9;
                    FullBrightModule.setGamma(minecraftClient, this.savedGamma);
                }
                fullBrightModule = this;
            }
            fullBrightModule.savedGamma = null;
        }
    }

    @Override
    public void onTick() {
        MinecraftClient minecraftClient;
        block8: {
            block7: {
                minecraftClient = MinecraftClient.method_1551();
                boolean bl = AmbienceModule.b;
                if (minecraftClient.field_1690 == null) {
                    return;
                }
                FullBrightModule fullBrightModule = this;
                if (bl) break block7;
                if (fullBrightModule.savedGamma != null) break block8;
                fullBrightModule = this;
            }
            fullBrightModule.savedGamma = (Double)minecraftClient.field_1690.method_42473().method_41753();
        }
        FullBrightModule.setGamma(minecraftClient, this.brightness.getValue());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void swapForWrite(boolean bl) {
        MinecraftClient minecraftClient;
        FullBrightModule fullBrightModule;
        block16: {
            MinecraftClient minecraftClient2;
            block17: {
                FullBrightModule fullBrightModule2;
                boolean bl2;
                block15: {
                    block14: {
                        fullBrightModule = instance;
                        bl2 = AmbienceModule.b;
                        minecraftClient2 = MinecraftClient.method_1551();
                        fullBrightModule2 = fullBrightModule;
                        if (bl2) break block14;
                        if (fullBrightModule2 == null) return;
                        fullBrightModule2 = fullBrightModule;
                    }
                    if (bl2) break block15;
                    if (!fullBrightModule2.isEnabled()) return;
                    fullBrightModule2 = fullBrightModule;
                }
                if (fullBrightModule2.savedGamma == null) return;
                minecraftClient = minecraftClient2;
                if (bl2) break block16;
                if (minecraftClient.field_1690 != null) break block17;
                return;
            }
            minecraftClient = minecraftClient2;
        }
        double d = bl ? fullBrightModule.savedGamma.doubleValue() : fullBrightModule.brightness.getValue();
        FullBrightModule.setGamma(minecraftClient, d);
    }

    private static void setGamma(MinecraftClient minecraftClient, double d) {
        ((SimpleOptionAccessor)minecraftClient.field_1690.method_42473()).aurora$setValueUnchecked(d);
    }

    
    static {
        block12: {
            var5 = new String[3];
            var3_1 = 0;
            var2_2 = "\u000en~\n\tmp)od\n\ni{\u0001\u0003kw+tc\u0019\u0001}oM\u0003kw+tc\u0003\u0004jmlya\b\u0013`i$ye\b";
            var4_3 = "\u000en~\n\tmp)od\n\ni{\u0001\u0003kw+tc\u0019\u0001}oM\u0003kw+tc\u0003\u0004jmlya\b\u0013`i$ye\b".length();
            var1_4 = 10;
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
                        v6 = 76;
                        break;
                    }
                    case 1: {
                        v6 = 28;
                        break;
                    }
                    case 2: {
                        v6 = 23;
                        break;
                    }
                    case 3: {
                        v6 = 109;
                        break;
                    }
                    case 4: {
                        v6 = 97;
                        break;
                    }
                    case 5: {
                        v6 = 25;
                        break;
                    }
                    default: {
                        v6 = 30;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        FullBrightModule.b = var5;
    }
}
