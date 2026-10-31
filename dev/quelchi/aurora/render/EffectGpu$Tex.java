package dev.quelchi.aurora.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import dev.quelchi.aurora.render.EffectEngine;

final class EffectGpu$Tex {
    GpuTexture t;
    GpuTextureView v;
    int w;
    int h;
    private static final String a;

    private EffectGpu$Tex() {
    }

    void ensure(String string, int n, TextureFormat textureFormat, int n2, int n3) {
        block24: {
            EffectGpu$Tex effectGpu$Tex;
            block25: {
                int n4;
                block22: {
                    block23: {
                        block20: {
                            block21: {
                                n4 = EffectEngine.a;
                                effectGpu$Tex = this;
                                if (n4 != 0) break block20;
                                if (effectGpu$Tex.t == null) break block21;
                                effectGpu$Tex = this;
                                if (n4 != 0) break block20;
                                if (effectGpu$Tex.w != n2) break block21;
                                effectGpu$Tex = this;
                                if (n4 != 0) break block20;
                                if (effectGpu$Tex.h != n3) break block21;
                                return;
                            }
                            effectGpu$Tex = this;
                        }
                        if (n4 != 0) break block22;
                        if (effectGpu$Tex.v == null) break block23;
                        this.v.close();
                    }
                    effectGpu$Tex = this;
                }
                if (n4 != 0) break block24;
                if (effectGpu$Tex.t == null) break block25;
                this.t.close();
            }
            this.t = RenderSystem.getDevice().createTexture(a + string, n, textureFormat, n2, n3, 1, 1);
            this.v = RenderSystem.getDevice().createTextureView(this.t);
            this.w = n2;
            effectGpu$Tex = this;
        }
        effectGpu$Tex.h = n3;
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "u1x\u00135\u0017+r<*".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 20;
                    case 1 -> 68;
                    case 2 -> 10;
                    case 3 -> 124;
                    case 4 -> 71;
                    case 5 -> 118;
                    default -> 11;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
    }
}
