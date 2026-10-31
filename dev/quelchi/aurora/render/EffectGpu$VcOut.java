package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Out;
import net.minecraft.client.render.VertexConsumer;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

final class EffectGpu$VcOut
implements EffectEngine$Out {
    VertexConsumer vc;
    Matrix4f m;

    private EffectGpu$VcOut() {
    }

    @Override
    public void vert(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n, float f10, float f11) {
        VertexConsumer vertexConsumer;
        float f12;
        int n2;
        int n3;
        int n4;
        int n5;
        block10: {
            EffectGpu$VcOut effectGpu$VcOut;
            block8: {
                block9: {
                    float f13;
                    int n6;
                    block6: {
                        block7: {
                            float f14 = Math.max(f6, Math.max(f7, f8));
                            n5 = 0;
                            n4 = 0;
                            n6 = EffectEngine.a;
                            n3 = 0;
                            n2 = 0;
                            float f15 = f14 - 1.0E-5f;
                            f13 = f15 == 0.0f ? 0 : (f15 > 0.0f ? 1 : -1);
                            if (n6 != 0) break block6;
                            if (f13 <= 0) break block7;
                            n5 = Math.min(255, (int)(f6 / f14 * 255.0f + 0.5f));
                            n4 = Math.min(255, (int)(f7 / f14 * 255.0f + 0.5f));
                            n3 = Math.min(255, (int)(f8 / f14 * 255.0f + 0.5f));
                            n2 = Math.min(Short.MAX_VALUE, Math.round(f14 * 1024.0f));
                        }
                        f13 = Math.max(0, Math.min(255, Math.round(f9 * 255.0f)));
                    }
                    f12 = f13;
                    effectGpu$VcOut = this;
                    if (n6 != 0) break block8;
                    if (effectGpu$VcOut.m != null) break block9;
                    vertexConsumer = this.vc.method_22912(f, f2, f3);
                    break block10;
                }
                effectGpu$VcOut = this;
            }
            vertexConsumer = effectGpu$VcOut.vc.method_22918((Matrix4fc)this.m, f, f2, f3);
        }
        vertexConsumer.method_1336(n5, n4, n3, (int)f12).method_22913(f4, f5).method_60796(n, Math.max(Short.MIN_VALUE, Math.min(Short.MAX_VALUE, Math.round(f10 * 256.0f)))).method_22921(n2, Math.max(0, Math.min(Short.MAX_VALUE, Math.round(f11 * 256.0f)))).method_22914(0.0f, 1.0f, 0.0f);
    }
}
