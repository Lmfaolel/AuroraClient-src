package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.Interpolator$LinearInterpolator;
import java.util.function.DoubleSupplier;

public class Interpolator$TimedInterpolator
extends Interpolator$LinearInterpolator {
    final DoubleSupplier timeIn;
    final DoubleSupplier timeOut;

    public Interpolator$TimedInterpolator(DoubleSupplier doubleSupplier, DoubleSupplier doubleSupplier2) {
        this.timeIn = doubleSupplier;
        this.timeOut = doubleSupplier2;
    }

    @Override
    double increment(boolean bl, double d, double d2, double d3) {
        double d4 = d;
        double d5 = bl ? this.timeOut.getAsDouble() : this.timeIn.getAsDouble();
        return d4 / d5;
    }

    @Override
    public boolean isSmooth() {
        byte n;
        block14: {
            block17: {
                block15: {
                    Interpolator$TimedInterpolator interpolator$TimedInterpolator;
                    int n2;
                    block16: {
                        block12: {
                            block13: {
                                n2 = Interpolator$LinearInterpolator.a;
                                interpolator$TimedInterpolator = this;
                                if (n2 != 0) break block12;
                                if (!interpolator$TimedInterpolator.goingIn) break block13;
                                double d = this.timeIn.getAsDouble() - 0.0;
                                n = d == 0.0 ? (byte)0 : (d > 0.0 ? (byte)1 : -1);
                                if (n2 != 0) break block14;
                                if (n > 0) break block15;
                            }
                            interpolator$TimedInterpolator = this;
                        }
                        if (n2 != 0) break block16;
                        if (interpolator$TimedInterpolator.goingIn) break block17;
                        interpolator$TimedInterpolator = this;
                    }
                    double d = interpolator$TimedInterpolator.timeOut.getAsDouble() - 0.0;
                    n = d == 0.0 ? (byte)0 : (d > 0.0 ? (byte)1 : -1);
                    if (n2 != 0) break block14;
                    if (n <= 0) break block17;
                }
                n = 1;
                break block14;
            }
            n = 0;
        }
        return n != 0;
    }
}
