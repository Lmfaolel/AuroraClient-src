package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.WeatherChangerModule$WeatherPreset;
import dev.quelchi.aurora.module.setting.EnumSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

public final class WeatherChangerModule
extends Module {
    private static WeatherChangerModule instance;
    private final EnumSetting<WeatherChangerModule$WeatherPreset> preset;
    private static final String[] b;

    public WeatherChangerModule() {
        String[] stringArray = b;
        super(stringArray[2], stringArray[0], Category.RENDER, false);
        this.preset = new EnumSetting<WeatherChangerModule$WeatherPreset>(stringArray[1], WeatherChangerModule$WeatherPreset.Clear);
        instance = this;
        this.addSettings(this.preset);
    }

    @Override
    public void onDisable() {
        block4: {
            ClientWorld clientWorld;
            block5: {
                boolean bl = AmbienceModule.b;
                clientWorld = MinecraftClient.method_1551().field_1687;
                if (bl) break block4;
                if (clientWorld != null) break block5;
                return;
            }
            clientWorld.method_8519(0.0f);
            clientWorld.method_8496(0.0f);
        }
    }

    
    @Override
    public void onTick() {
        ClientWorld clientWorld = MinecraftClient.method_1551().field_1687;
        boolean bl = AmbienceModule.b;
        if (clientWorld == null) {
            return;
        }
        switch (this.preset.getValue().ordinal()) {
            case 0: {
                clientWorld.method_8519(0.0f);
                clientWorld.method_8496(0.0f);
                if (!bl) return;
            }
            case 1: {
                clientWorld.method_8519(1.0f);
                clientWorld.method_8496(0.0f);
                if (!bl) return;
            }
            case 2: {
                clientWorld.method_8519(1.0f);
                clientWorld.method_8496(1.0f);
                return;
            }
        }
    }

    
    static {
        block12: {
            var5 = new String[3];
            var3_1 = 0;
            var2_2 = "\u000eF\u0019\u0007bh\\$\u0010\u000b\u001abm\\aG\u0019\u0014di]3\u0007\u0016U\u001d\u0001xdJ\u000f\u0016U\u001d\u0001xdJas\u0014\u0014~f]3";
            var4_3 = "\u000eF\u0019\u0007bh\\$\u0010\u000b\u001abm\\aG\u0019\u0014di]3\u0007\u0016U\u001d\u0001xdJ\u000f\u0016U\u001d\u0001xdJas\u0014\u0014~f]3".length();
            var1_4 = 22;
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
                        v6 = 65;
                        break;
                    }
                    case 1: {
                        v6 = 48;
                        break;
                    }
                    case 2: {
                        v6 = 124;
                        break;
                    }
                    case 3: {
                        v6 = 117;
                        break;
                    }
                    case 4: {
                        v6 = 16;
                        break;
                    }
                    case 5: {
                        v6 = 1;
                        break;
                    }
                    default: {
                        v6 = 56;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        WeatherChangerModule.b = var5;
    }
}
