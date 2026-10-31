package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.zoom.Interpolator;
import dev.quelchi.aurora.zoom.Interpolator$LinearInterpolator;
import java.util.function.IntSupplier;
import net.minecraft.util.math.MathHelper;

public final class ZoomHelper {
    private final Interpolator initialInterpolator;
    private final Interpolator scrollInterpolator;
    private final IntSupplier initialZoom;
    private final IntSupplier zoomPerStep;
    private final IntSupplier maxScrollTiers;
    private double prevInitialInterpolation;
    private double initialInterpolation;
    private boolean zoomingLastTick;
    private double prevScrollInterpolation;
    private double scrollInterpolation;
    private int lastScrollTier;
    private boolean resetting;
    private double resetMultiplier;

    public ZoomHelper(Interpolator interpolator, Interpolator interpolator2, IntSupplier intSupplier, IntSupplier intSupplier2, IntSupplier intSupplier3) {
        this.initialInterpolator = interpolator;
        this.scrollInterpolator = interpolator2;
        this.initialZoom = intSupplier;
        this.zoomPerStep = intSupplier2;
        this.maxScrollTiers = intSupplier3;
    }

    public void tick(boolean bl, int n) {
        this.tickInitial(bl, 0.05);
        this.tickScroll(n, 0.05);
    }

    private void tickInitial(boolean bl, double d) {
        block16: {
            block17: {
                boolean bl2;
                int n;
                block14: {
                    block15: {
                        n = Interpolator$LinearInterpolator.a;
                        bl2 = bl;
                        if (n != 0) break block14;
                        if (!bl2) break block15;
                        bl2 = this.zoomingLastTick;
                        if (n != 0) break block14;
                        if (bl2) break block15;
                        this.resetting = false;
                    }
                    bl2 = bl;
                }
                double d2 = bl2 ? 1.0 : 0.0;
                double d3 = d2;
                this.prevInitialInterpolation = this.initialInterpolation;
                if (n != 0) break block16;
                this.initialInterpolation = this.initialInterpolator.tickInterpolation(d3, this.initialInterpolation, d);
                this.prevInitialInterpolation = this.initialInterpolator.modifyPrevInterpolation(this.prevInitialInterpolation);
                if (this.initialInterpolator.isSmooth()) break block17;
                this.prevInitialInterpolation = this.initialInterpolation;
            }
            this.zoomingLastTick = bl;
        }
    }

    private void tickScroll(int n, double d) {
        block14: {
            ZoomHelper zoomHelper;
            block15: {
                double d2;
                int n2;
                block16: {
                    block13: {
                        int n3;
                        block12: {
                            block10: {
                                block11: {
                                    n2 = Interpolator$LinearInterpolator.a;
                                    n3 = n;
                                    if (n2 != 0) break block10;
                                    if (n3 <= this.lastScrollTier) break block11;
                                    this.resetting = false;
                                }
                                n3 = this.maxScrollTiers.getAsInt();
                            }
                            if (n2 != 0) break block12;
                            if (n3 <= 0) break block13;
                            n3 = n;
                        }
                        d2 = (double)n3 / (double)this.maxScrollTiers.getAsInt();
                        break block16;
                    }
                    d2 = 0.0;
                }
                double d3 = d2;
                this.prevScrollInterpolation = this.scrollInterpolation;
                zoomHelper = this;
                if (n2 != 0) break block14;
                zoomHelper.scrollInterpolation = this.scrollInterpolator.tickInterpolation(d3, this.scrollInterpolation, d);
                this.prevScrollInterpolation = this.scrollInterpolator.modifyPrevInterpolation(this.prevScrollInterpolation);
                if (this.initialInterpolator.isSmooth()) break block15;
                this.prevInitialInterpolation = this.initialInterpolation;
            }
            zoomHelper = this;
        }
        zoomHelper.lastScrollTier = n;
    }

    
    public double getZoomDivisor(float f) {
        double d;
        block29: {
            block28: {
                ZoomHelper zoomHelper;
                int n;
                block26: {
                    block27: {
                        double d2;
                        double d3;
                        block23: {
                            ZoomHelper zoomHelper2;
                            block24: {
                                block25: {
                                    block21: {
                                        block22: {
                                            double d4 = this.getInitialZoomMultiplier(f);
                                            n = Interpolator$LinearInterpolator.a;
                                            d3 = 1.0 / d4;
                                            zoomHelper2 = this;
                                            if (n != 0) break block21;
                                            if (!zoomHelper2.resetting) break block22;
                                            d2 = 0.0;
                                            if (n == 0) break block23;
                                            Module.a = !Module.a;
                                        }
                                        zoomHelper2 = this;
                                    }
                                    if (n != 0) break block24;
                                    if (!zoomHelper2.scrollInterpolator.isSmooth()) break block25;
                                    d2 = this.scrollInterpolator.modifyInterpolation(MathHelper.method_16436((double)f, (double)this.prevScrollInterpolation, (double)this.scrollInterpolation));
                                    if (n == 0) break block23;
                                }
                                zoomHelper2 = this;
                            }
                            d2 = zoomHelper2.scrollInterpolation;
                        }
                        double d5 = (double)this.zoomPerStep.getAsInt() / 100.0;
                        double d6 = d2 * (double)this.maxScrollTiers.getAsInt();
                        double d7 = d3 * Math.pow(d5, d6);
                        d = MathHelper.method_15350((double)d7, (double)0.5, (double)500.0);
                        zoomHelper = this;
                        if (n != 0) break block26;
                        if (zoomHelper.initialInterpolation != 0.0) break block27;
                        zoomHelper = this;
                        if (n != 0) break block26;
                        if (zoomHelper.scrollInterpolation == 0.0) {
                            this.resetting = false;
                        }
                    }
                    zoomHelper = this;
                }
                if (n != 0) break block28;
                if (zoomHelper.resetting) break block29;
                zoomHelper = this;
            }
            zoomHelper.resetMultiplier = 1.0 / d;
        }
        return d;
    }

    private double getInitialZoomMultiplier(float f) {
        double d;
        double d2;
        double d3;
        block13: {
            ZoomHelper zoomHelper;
            block11: {
                block12: {
                    double d4;
                    int n;
                    block10: {
                        ZoomHelper zoomHelper2;
                        block8: {
                            block9: {
                                n = Interpolator$LinearInterpolator.a;
                                zoomHelper2 = this;
                                if (n != 0) break block8;
                                if (!zoomHelper2.initialInterpolator.isSmooth()) break block9;
                                d4 = this.initialInterpolator.modifyInterpolation(MathHelper.method_16436((double)f, (double)this.prevInitialInterpolation, (double)this.initialInterpolation));
                                break block10;
                            }
                            zoomHelper2 = this;
                        }
                        d4 = zoomHelper2.initialInterpolation;
                    }
                    double d5 = d4;
                    d3 = d5;
                    d2 = 1.0;
                    zoomHelper = this;
                    if (n != 0) break block11;
                    if (zoomHelper.resetting) break block12;
                    d = 1.0 / (double)this.initialZoom.getAsInt();
                    break block13;
                }
                zoomHelper = this;
            }
            d = zoomHelper.resetMultiplier;
        }
        return MathHelper.method_16436((double)d3, (double)d2, (double)d);
    }

    public void reset() {
        block9: {
            block10: {
                ZoomHelper zoomHelper;
                int n;
                block8: {
                    n = Interpolator$LinearInterpolator.a;
                    zoomHelper = this;
                    if (n != 0) break block8;
                    if (zoomHelper.resetting) break block9;
                    zoomHelper = this;
                }
                if (n != 0) break block10;
                if (!(zoomHelper.scrollInterpolation > 0.0)) break block9;
                this.resetting = true;
                this.scrollInterpolation = 0.0;
                zoomHelper = this;
            }
            zoomHelper.prevScrollInterpolation = 0.0;
        }
    }

    public void setToZero() {
        this.initialInterpolation = 0.0;
        this.prevInitialInterpolation = 0.0;
        this.zoomingLastTick = false;
        this.scrollInterpolation = 0.0;
        this.prevScrollInterpolation = 0.0;
        this.lastScrollTier = 0;
        this.resetting = false;
    }
}
