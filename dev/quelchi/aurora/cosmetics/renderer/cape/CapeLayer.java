package dev.quelchi.aurora.cosmetics.renderer.cape;

import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer$Animation;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer$FrameBlend;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer$Pass;
import dev.quelchi.aurora.module.Module;
import java.util.List;
import net.minecraft.util.Identifier;

public final class CapeLayer {
    private final CapeLayer$Pass pass;
    private final List<Identifier> frames;
    private final CapeLayer$Animation animation;
    private final float frameRate;
    private final int tint;
    private final float alpha;
    private final float pulse;
    private final float pulseSpeed;
    private final boolean blend;
    public static boolean a;
    private static final String b;
    private static final long c;

    public CapeLayer(CapeLayer$Pass pass, List<Identifier> list, CapeLayer$Animation animation, float f, int n, float f2, float f3, float f4, boolean bl) {
        block8: {
            boolean bl2;
            block10: {
                block9: {
                    boolean bl3 = a;
                    if (!bl3) {
                        if (list.isEmpty()) {
                            throw new IllegalArgumentException(b);
                        }
                        this.pass = pass;
                        this.frames = List.copyOf(list);
                        this.animation = animation;
                        this.frameRate = Math.max(0.0f, f);
                        this.tint = n;
                        this.alpha = CapeLayer.clamp(f2);
                        this.pulse = CapeLayer.clamp(f3);
                        this.pulseSpeed = Math.max(0.0f, f4);
                        this.blend = bl;
                    }
                    if (!bl3) break block8;
                    if (!Module.a) break block9;
                    bl2 = false;
                    break block10;
                }
                bl2 = true;
            }
            Module.a = bl2;
        }
    }

    public static CapeLayer base(Identifier identifier) {
        return CapeLayer.of(CapeLayer$Pass.BASE, List.of(identifier), CapeLayer$Animation.STATIC, 0.0f, -1, 1.0f, 0.0f, 0.0f);
    }

    public static CapeLayer of(CapeLayer$Pass capeLayer$Pass, List<Identifier> list, CapeLayer$Animation capeLayer$Animation, float f, int n, float f2, float f3, float f4) {
        return CapeLayer.of(capeLayer$Pass, list, capeLayer$Animation, f, n, f2, f3, f4, false);
    }

    public static CapeLayer of(CapeLayer$Pass capeLayer$Pass, List<Identifier> list, CapeLayer$Animation capeLayer$Animation, float f, int n, float f2, float f3, float f4, boolean bl) {
        return new CapeLayer(capeLayer$Pass, list, capeLayer$Animation, f, n, f2, f3, f4, bl);
    }

    public CapeLayer$Pass pass() {
        return this.pass;
    }

    public List<Identifier> frames() {
        return this.frames;
    }

    public Identifier frameAt(long l, int n) {
        int n2;
        block26: {
            int n3;
            block25: {
                int n4;
                long l2;
                boolean bl;
                block24: {
                    CapeLayer capeLayer;
                    block23: {
                        block22: {
                            CapeLayer capeLayer2;
                            block20: {
                                block21: {
                                    bl = a;
                                    capeLayer2 = this;
                                    if (bl) break block20;
                                    if (capeLayer2.animation == CapeLayer$Animation.STATIC) break block21;
                                    capeLayer2 = this;
                                    if (bl) break block20;
                                    if (capeLayer2.frames.size() == 1) break block21;
                                    capeLayer2 = this;
                                    if (bl) break block20;
                                    if (!(capeLayer2.frameRate <= 0.0f)) break block22;
                                }
                                capeLayer2 = this.frames.get(0);
                            }
                            return (Identifier)capeLayer2;
                        }
                        l2 = (long)Math.floor((double)l * (double)this.frameRate / 1000.0) + (long)n;
                        n4 = this.frames.size();
                        capeLayer = this;
                        if (bl) break block23;
                        if (capeLayer.animation != CapeLayer$Animation.LOOP) break block24;
                        capeLayer = this.frames.get(Math.floorMod(l2, n4));
                    }
                    return (Identifier)capeLayer;
                }
                int n5 = n4 * 2 - 2;
                n2 = Math.floorMod(l2, n5);
                n3 = n2;
                if (bl) break block25;
                if (n3 < n4) break block26;
                n3 = n5 - n2;
            }
            n2 = n3;
        }
        return this.frames.get(n2);
    }

    public CapeLayer$FrameBlend blendAt(long l, int n) {
        int n2;
        int n3;
        float f;
        block35: {
            int n4;
            block36: {
                int n5;
                int n6;
                int n7;
                int n8;
                boolean bl;
                block33: {
                    block34: {
                        double d;
                        double d2;
                        block31: {
                            block32: {
                                block28: {
                                    CapeLayer capeLayer;
                                    block26: {
                                        block27: {
                                            bl = a;
                                            capeLayer = this;
                                            if (bl) break block26;
                                            if (capeLayer.animation == CapeLayer$Animation.STATIC) break block27;
                                            capeLayer = this;
                                            if (bl) break block26;
                                            if (capeLayer.frames.size() == 1) break block27;
                                            capeLayer = this;
                                            if (bl) break block26;
                                            if (!(capeLayer.frameRate <= 0.0f)) break block28;
                                        }
                                        capeLayer = this.frames.get(0);
                                    }
                                    Identifier identifier = (Identifier)capeLayer;
                                    return new CapeLayer$FrameBlend(identifier, identifier, 0.0f);
                                }
                                double d3 = (double)l * (double)this.frameRate / 1000.0 + (double)n;
                                n8 = this.frames.size();
                                if (this.animation == CapeLayer$Animation.LOOP) {
                                    double d4;
                                    double d5;
                                    block29: {
                                        block30: {
                                            d5 = d3 % (double)n8;
                                            d4 = d5;
                                            if (bl) break block29;
                                            if (!(d4 < 0.0)) break block30;
                                            d5 += (double)n8;
                                        }
                                        d4 = d5;
                                    }
                                    int n9 = (int)d4;
                                    int n10 = (n9 + 1) % n8;
                                    float f2 = (float)(d5 - (double)n9);
                                    return new CapeLayer$FrameBlend(this.frames.get(n9), this.frames.get(n10), f2);
                                }
                                n7 = n8 * 2 - 2;
                                if (n7 <= 0) {
                                    Identifier identifier = this.frames.get(0);
                                    return new CapeLayer$FrameBlend(identifier, identifier, 0.0f);
                                }
                                d2 = d3 % (double)n7;
                                d = d2;
                                if (bl) break block31;
                                if (!(d < 0.0)) break block32;
                                d2 += (double)n7;
                            }
                            d = d2;
                        }
                        n6 = (int)d;
                        f = (float)(d2 - (double)n6);
                        n5 = n6;
                        if (bl) break block33;
                        if (n5 < n8) break block34;
                        n5 = n7 - n6;
                        break block33;
                    }
                    n5 = n6;
                }
                n3 = n5;
                n4 = (n6 + 1) % n7;
                n2 = n4;
                if (bl) break block35;
                if (n2 < n8) break block36;
                n2 = n7 - n4;
                break block35;
            }
            n2 = n4;
        }
        int n11 = n2;
        return new CapeLayer$FrameBlend(this.frames.get(n3), this.frames.get(n11), f);
    }

    public boolean blend() {
        return this.blend;
    }

    public int colorAt(long l, int n) {
        int n2;
        block12: {
            boolean bl;
            block14: {
                block13: {
                    CapeLayer capeLayer;
                    boolean bl2;
                    float f;
                    block10: {
                        block11: {
                            f = this.alpha;
                            bl2 = a;
                            capeLayer = this;
                            if (bl2) break block10;
                            if (!(capeLayer.pulse > 0.0f)) break block11;
                            capeLayer = this;
                            if (bl2) break block10;
                            if (!(capeLayer.pulseSpeed > 0.0f)) break block11;
                            double d = (double)l / 1000.0 * (double)this.pulseSpeed * Math.PI * 2.0 + (double)n * 0.013;
                            f *= 1.0f + (float)Math.sin(d) * this.pulse;
                        }
                        capeLayer = this;
                    }
                    int n3 = Math.round((float)(capeLayer.tint >>> 24 & 0xFF) * CapeLayer.clamp(f));
                    n2 = n3 << 24 | this.tint & (int)c;
                    if (!Module.a) break block12;
                    if (!bl2) break block13;
                    bl = false;
                    break block14;
                }
                bl = true;
            }
            a = bl;
        }
        return n2;
    }

    public float alpha() {
        return this.alpha;
    }

    private static float clamp(float f) {
        return Math.max(0.0f, Math.min(1.0f, f));
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "\u0006~\u0011B/\t\u000e+?\u000bF-L@\";\u0016P\u007f\rZg2\u0017B,\u0018\u000e(0\u0017\u00039\u001eO*;".toCharArray();
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
                    case 0 -> 71;
                    case 1 -> 94;
                    case 2 -> 114;
                    case 3 -> 35;
                    case 4 -> 95;
                    case 5 -> 108;
                    default -> 46;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        b = new String(cArray).intern();
        long l = 1240443577999143260L;
        c = 0x5B912FA2AE724EA3L ^ l;
    }
}
