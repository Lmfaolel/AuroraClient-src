package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.MotionBlurModule$Strength;
import dev.quelchi.aurora.module.modules.MotionBlurModule$Type;
import dev.quelchi.aurora.module.setting.EnumSetting;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public final class MotionBlurModule
extends Module {
    private static MotionBlurModule instance;
    private static final boolean VULKAN;
    private final EnumSetting<MotionBlurModule$Type> type;
    private final EnumSetting<MotionBlurModule$Strength> strength;
    private static final String[] b;

    public MotionBlurModule() {
        String string;
        String[] stringArray = b;
        String string2 = stringArray[7];
        if (VULKAN) {
            string = stringArray[6];
        } else {
            stringArray = b;
            string = stringArray[1];
        }
        super(string2, string, Category.RENDER, false);
        stringArray = b;
        this.type = new EnumSetting<MotionBlurModule$Type>(stringArray[0], MotionBlurModule$Type.TRAIL);
        this.strength = new EnumSetting<MotionBlurModule$Strength>(stringArray[8], MotionBlurModule$Strength.Medium);
        instance = this;
        this.addSettings(this.type, this.strength);
    }

    public static MotionBlurModule get() {
        return instance;
    }

    public boolean active() {
        return this.isEnabled();
    }

    public MotionBlurModule$Type type() {
        MotionBlurModule$Type motionBlurModule$Type = VULKAN ? MotionBlurModule$Type.TRAIL : this.type.getValue();
        return motionBlurModule$Type;
    }

    
    public float shutterSeconds() {
        switch (this.strength.getValue().ordinal()) {
            case 0: {
                return 0.004166667f;
            }
            case 2: {
                return 0.016666668f;
            }
        }
        return 0.008333334f;
    }

    
    public float fadeSeconds() {
        switch (this.strength.getValue().ordinal()) {
            case 0: {
                return 0.004f;
            }
            case 2: {
                return 0.016f;
            }
        }
        return 0.008f;
    }

    
    public Identifier effect() {
        String[] stringArray;
        String string = switch (this.strength.getValue().ordinal()) {
            case 0 -> b[3];
            case 2 -> {
                stringArray = b;
                yield stringArray[10];
            }
            default -> {
                stringArray = b;
                yield stringArray[5];
            }
        };
        stringArray = b;
        return Identifier.method_60655((String)stringArray[2], (String)(stringArray[9] + string));
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[11];
                var3_1 = 0;
                var2_2 = "\u0018\u0002@\u0004\\\u001a\u0018\u001b\\\tK\u0007V#\u0013\u0019\u001ePBUk\u0017JHPS\u0002&\u0019O\rJ\u0006*\u0003K\u0007KF\u0003'\u0019N\t=\u0003U\u0003XIO$\u0012\u0006&\u0013]\u0001LJ6\u0018\u001b\\\tK\u0007V#\u0013\u0019\u001ePBUk\u0017JHPS\u0002&\u0019O\rJ\u0007\n\u001f\u0004X\u0001U\u0007M%\u001a@HNNV#Vo\u001dULC%;V\f\u0010\u000b\u0006\u0019M\u0001VI\u0002\t\u001aL\u001a\b\u0018\u0002K\rW@V#";
                var4_3 = "\u0018\u0002@\u0004\\\u001a\u0018\u001b\\\tK\u0007V#\u0013\u0019\u001ePBUk\u0017JHPS\u0002&\u0019O\rJ\u0006*\u0003K\u0007KF\u0003'\u0019N\t=\u0003U\u0003XIO$\u0012\u0006&\u0013]\u0001LJ6\u0018\u001b\\\tK\u0007V#\u0013\u0019\u001ePBUk\u0017JHPS\u0002&\u0019O\rJ\u0007\n\u001f\u0004X\u0001U\u0007M%\u001a@HNNV#Vo\u001dULC%;V\f\u0010\u000b\u0006\u0019M\u0001VI\u0002\t\u001aL\u001a\b\u0018\u0002K\rW@V#".length();
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
                    var2_2 = "&\u0019M\u0001VI})\u001aL\u001af\u0004#\u001f^\u0000";
                    var4_3 = "&\u0019M\u0001VI})\u001aL\u001af\u0004#\u001f^\u0000".length();
                    var1_4 = 12;
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
                        v10 = 75;
                        break;
                    }
                    case 1: {
                        v10 = 118;
                        break;
                    }
                    case 2: {
                        v10 = 57;
                        break;
                    }
                    case 3: {
                        v10 = 104;
                        break;
                    }
                    case 4: {
                        v10 = 57;
                        break;
                    }
                    case 5: {
                        v10 = 39;
                        break;
                    }
                    default: {
                        v10 = 34;
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
        MotionBlurModule.b = var5;
        MotionBlurModule.VULKAN = FabricLoader.getInstance().isModLoaded(MotionBlurModule.b[4]);
    }
}
