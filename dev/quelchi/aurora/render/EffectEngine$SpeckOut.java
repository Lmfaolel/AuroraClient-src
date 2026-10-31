package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Out;
import dev.quelchi.aurora.render.LocalSpeckSink;

final class EffectEngine$SpeckOut
implements EffectEngine$Out {
    final LocalSpeckSink sink;
    int i;
    float sx;
    float sy;
    float sz;
    float x0;
    float y0;
    float z0;
    float x1;
    float y1;
    float z1;
    float r;
    float g;
    float b;
    float a;
    int kind;

    EffectEngine$SpeckOut(LocalSpeckSink localSpeckSink) {
        this.sink = localSpeckSink;
    }

    @Override
    public void vert(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n, float f10, float f11) {
        block58: {
            int n2;
            int n3;
            int n4;
            int n5;
            float f12;
            block59: {
                int n6;
                int n7;
                block57: {
                    int n8;
                    block55: {
                        block56: {
                            float f13;
                            float f14;
                            int n9;
                            block53: {
                                block54: {
                                    float f15;
                                    float f16;
                                    float f17;
                                    block51: {
                                        block52: {
                                            float f18;
                                            float f19;
                                            block49: {
                                                block50: {
                                                    int n10;
                                                    block48: {
                                                        int n11;
                                                        block46: {
                                                            block47: {
                                                                EffectEngine$SpeckOut effectEngine$SpeckOut;
                                                                block44: {
                                                                    block45: {
                                                                        block43: {
                                                                            block42: {
                                                                                block40: {
                                                                                    block41: {
                                                                                        n7 = EffectEngine.a;
                                                                                        effectEngine$SpeckOut = this;
                                                                                        if (n7 != 0) break block40;
                                                                                        if (effectEngine$SpeckOut.i != 0) break block41;
                                                                                        this.sz = 0.0f;
                                                                                        this.sy = 0.0f;
                                                                                        this.sx = 0.0f;
                                                                                        this.x0 = f;
                                                                                        this.y0 = f2;
                                                                                        this.z0 = f3;
                                                                                        this.r = f6;
                                                                                        this.g = f7;
                                                                                        this.b = f8;
                                                                                        this.a = f9;
                                                                                        this.kind = n;
                                                                                        if (n7 == 0) break block42;
                                                                                    }
                                                                                    effectEngine$SpeckOut = this;
                                                                                }
                                                                                if (n7 != 0) break block43;
                                                                                if (effectEngine$SpeckOut.i != 1) break block42;
                                                                                this.x1 = f;
                                                                                this.y1 = f2;
                                                                                this.z1 = f3;
                                                                            }
                                                                            this.sx += f;
                                                                            this.sy += f2;
                                                                            this.sz += f3;
                                                                            effectEngine$SpeckOut = this;
                                                                        }
                                                                        if (n7 != 0) break block44;
                                                                        if (++effectEngine$SpeckOut.i >= 4) break block45;
                                                                        return;
                                                                    }
                                                                    this.i = 0;
                                                                    effectEngine$SpeckOut = this;
                                                                }
                                                                f12 = (float)Math.sqrt(EffectEngine.sq(effectEngine$SpeckOut.x1 - this.x0) + EffectEngine.sq(this.y1 - this.y0) + EffectEngine.sq(this.z1 - this.z0)) / 2.0f;
                                                                n11 = this.kind & 0x1F;
                                                                if (n7 != 0) break block46;
                                                                if (n11 != 15) break block47;
                                                                f12 = Math.min(f12, 0.05f);
                                                            }
                                                            n10 = this.kind;
                                                            if (n7 != 0) break block48;
                                                            n11 = n10 & 0x20;
                                                        }
                                                        n10 = n11 != 0 ? 1 : 0;
                                                    }
                                                    n9 = n10;
                                                    float f20 = n9 != 0 ? 1.0f : 0.6f;
                                                    f17 = f20;
                                                    f19 = this.r;
                                                    f18 = f17;
                                                    if (n7 != 0) break block49;
                                                    f19 = f19 * f18;
                                                    if (n9 == 0) break block50;
                                                    f18 = 1.0f;
                                                    break block49;
                                                }
                                                f18 = this.a;
                                            }
                                            n5 = EffectEngine$SpeckOut.srgb(f19 * f18);
                                            f16 = this.g;
                                            f15 = f17;
                                            if (n7 != 0) break block51;
                                            f16 = f16 * f15;
                                            if (n9 == 0) break block52;
                                            f15 = 1.0f;
                                            break block51;
                                        }
                                        f15 = this.a;
                                    }
                                    n4 = EffectEngine$SpeckOut.srgb(f16 * f15);
                                    f14 = this.b;
                                    f13 = f17;
                                    if (n7 != 0) break block53;
                                    f14 = f14 * f13;
                                    if (n9 == 0) break block54;
                                    f13 = 1.0f;
                                    break block53;
                                }
                                f13 = this.a;
                            }
                            n3 = EffectEngine$SpeckOut.srgb(f14 * f13);
                            n8 = n9;
                            if (n7 != 0) break block55;
                            if (n8 == 0) break block56;
                            n6 = Math.round(Math.min(1.0f, this.a) * 200.0f);
                            break block57;
                        }
                        n8 = n5;
                    }
                    n6 = Math.max(n8, Math.max(n4, n3));
                }
                n2 = n6;
                if (n7 != 0) break block58;
                if (n2 >= 8) break block59;
                return;
            }
            this.sink.speck(this.sx / 4.0f, this.sy / 4.0f, this.sz / 4.0f, Math.max(0.01f, f12), n2 << 24 | n5 << 16 | n4 << 8 | n3);
        }
    }

    private static int srgb(float f) {
        float f2 = 1.0f - (float)Math.exp(-Math.max(0.0f, f));
        return Math.min(255, Math.round((float)Math.pow(f2, 0.45454545454545453) * 255.0f));
    }
}
