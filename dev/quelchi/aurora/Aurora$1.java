package dev.quelchi.aurora;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.render.HandShaderGpu;
import dev.quelchi.aurora.render.MotionBlurGpu;
import dev.quelchi.aurora.shield.GreyscaleTextures;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

class Aurora$1
implements SimpleSynchronousResourceReloadListener {
    private static final String[] a;

    Aurora$1(Aurora aurora) {
    }

    public Identifier getFabricId() {
        String[] stringArray = a;
        return Identifier.method_60655((String)stringArray[0], (String)stringArray[1]);
    }

    public void method_14491(ResourceManager resourceManager) {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    Aurora.mc.execute(HandShaderGpu::reload);
                    Aurora.mc.execute(MotionBlurGpu::reload);
                    boolean bl2 = Aurora.a;
                    Aurora.mc.execute(Im::reload);
                    Aurora.mc.execute(GreyscaleTextures::invalidate);
                    if (!Module.a) break block4;
                    if (!bl2) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            Aurora.a = bl;
        }
    }

    
    static {
        block12: {
            var5 = new String[2];
            var3_1 = 0;
            var2_2 = "\"Q%iD \u0005$H6uE";
            var4_3 = "\"Q%iD \u0005$H6uE".length();
            var1_4 = 6;
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
                        v6 = 67;
                        break;
                    }
                    case 1: {
                        v6 = 36;
                        break;
                    }
                    case 2: {
                        v6 = 87;
                        break;
                    }
                    case 3: {
                        v6 = 6;
                        break;
                    }
                    case 4: {
                        v6 = 54;
                        break;
                    }
                    case 5: {
                        v6 = 65;
                        break;
                    }
                    default: {
                        v6 = 88;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        Aurora$1.a = var5;
    }
}
