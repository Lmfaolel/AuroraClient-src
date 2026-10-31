package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.zoom.Interpolator$LinearInterpolator;
import dev.quelchi.aurora.zoom.Interpolator$TimedInterpolator;
import dev.quelchi.aurora.zoom.TransitionType;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class Interpolator$TransitionInterpolator
extends Interpolator$TimedInterpolator {
    private final Supplier<TransitionType> transitionIn;
    private final Supplier<TransitionType> transitionOut;
    private TransitionType activeTransition;
    private TransitionType inactiveTransition;
    private double prevTarget = 0.0;
    private boolean justSwapped;

    public Interpolator$TransitionInterpolator(Supplier<TransitionType> supplier, Supplier<TransitionType> supplier2, DoubleSupplier doubleSupplier, DoubleSupplier doubleSupplier2) {
        super(doubleSupplier, doubleSupplier2);
        int n = Interpolator$LinearInterpolator.a;
        this.justSwapped = false;
        this.transitionIn = supplier;
        this.transitionOut = () -> Interpolator$TransitionInterpolator.lambda$new$0(supplier2);
        this.activeTransition = supplier.get();
        this.inactiveTransition = this.transitionOut.get();
        if (Module.a) {
            Interpolator$LinearInterpolator.a = ++n;
        }
    }

    
    @Override
    public double tickInterpolation(double d, double d2, double d3) {
        double d4;
        Interpolator$TransitionInterpolator interpolator$TransitionInterpolator;
        double d5;
        block24: {
            Interpolator$TransitionInterpolator interpolator$TransitionInterpolator2;
            block22: {
                block20: {
                    int n;
                    block21: {
                        double d6;
                        double d7;
                        block23: {
                            block18: {
                                block19: {
                                    d5 = d2;
                                    n = Interpolator$LinearInterpolator.a;
                                    d7 = d;
                                    d6 = d2;
                                    if (n != 0) break block18;
                                    if (!(d7 > d6)) break block19;
                                    this.activeTransition = this.transitionIn.get();
                                    if (n != 0) break block20;
                                    this.inactiveTransition = this.transitionOut.get();
                                    if (!(this.prevTarget < d)) break block21;
                                    interpolator$TransitionInterpolator2 = this;
                                    if (n != 0) break block22;
                                    if (!interpolator$TransitionInterpolator2.activeTransition.hasInverse()) break block21;
                                    this.justSwapped = true;
                                    d5 = this.activeTransition.inverse(this.inactiveTransition.apply(d5));
                                    if (n == 0) break block21;
                                }
                                d7 = d;
                                d6 = d2;
                            }
                            if (n != 0) break block23;
                            if (!(d7 < d6)) break block21;
                            this.activeTransition = this.transitionOut.get();
                            if (n != 0) break block20;
                            this.inactiveTransition = this.transitionIn.get();
                            d7 = this.prevTarget;
                            d6 = d;
                        }
                        if (!(d7 > d6)) break block21;
                        interpolator$TransitionInterpolator2 = this;
                        if (n != 0) break block22;
                        if (interpolator$TransitionInterpolator2.activeTransition.hasInverse()) {
                            this.justSwapped = true;
                            d5 = this.activeTransition.inverse(this.inactiveTransition.apply(d5));
                        }
                    }
                    interpolator$TransitionInterpolator = this;
                    d4 = d;
                    if (n != 0) break block24;
                    interpolator$TransitionInterpolator.prevTarget = d4;
                }
                interpolator$TransitionInterpolator2 = this;
            }
            if (interpolator$TransitionInterpolator2.activeTransition == TransitionType.Instant) {
                return d;
            }
            interpolator$TransitionInterpolator = this;
            d4 = d;
        }
        return super.tickInterpolation(d4, d5, d3);
    }

    @Override
    public double modifyInterpolation(double d) {
        return this.activeTransition.apply(d);
    }

    @Override
    public double modifyPrevInterpolation(double d) {
        block5: {
            Interpolator$TransitionInterpolator interpolator$TransitionInterpolator;
            block4: {
                int n = Interpolator$LinearInterpolator.a;
                interpolator$TransitionInterpolator = this;
                if (n != 0) break block4;
                if (!interpolator$TransitionInterpolator.justSwapped) break block5;
                this.justSwapped = false;
                interpolator$TransitionInterpolator = this;
            }
            return interpolator$TransitionInterpolator.activeTransition.inverse(this.inactiveTransition.apply(d));
        }
        return d;
    }

    @Override
    public boolean isSmooth() {
        boolean bl;
        block13: {
            block11: {
                Interpolator$TransitionInterpolator interpolator$TransitionInterpolator;
                block12: {
                    int n;
                    block10: {
                        n = Interpolator$LinearInterpolator.a;
                        interpolator$TransitionInterpolator = this;
                        if (n != 0) break block10;
                        if (interpolator$TransitionInterpolator.justSwapped) break block11;
                        interpolator$TransitionInterpolator = this;
                    }
                    if (n != 0) break block12;
                    if (!super.isSmooth()) break block11;
                    interpolator$TransitionInterpolator = this;
                }
                if (interpolator$TransitionInterpolator.activeTransition == TransitionType.Instant) break block11;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    private static TransitionType lambda$new$0(Supplier supplier) {
        return ((TransitionType)((Object)supplier.get())).opposite();
    }
}
