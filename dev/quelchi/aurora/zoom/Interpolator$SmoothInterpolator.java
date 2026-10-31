package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.Interpolator$LinearInterpolator;
import java.util.function.DoubleSupplier;

public class Interpolator$SmoothInterpolator
extends Interpolator$LinearInterpolator {
    private final DoubleSupplier smoothness;

    public Interpolator$SmoothInterpolator(DoubleSupplier doubleSupplier) {
        this.smoothness = doubleSupplier;
    }

    @Override
    double increment(boolean bl, double d, double d2, double d3) {
        double d4 = bl ? d3 - d2 : d2 - d3;
        double d5 = d4;
        return d5 * this.smoothness.getAsDouble() / 0.05 * d;
    }

    @Override
    public double tickInterpolation(double d, double d2, double d3) {
        Interpolator$SmoothInterpolator interpolator$SmoothInterpolator;
        block4: {
            block5: {
                int n = Interpolator$LinearInterpolator.a;
                interpolator$SmoothInterpolator = this;
                if (n != 0) break block4;
                if (interpolator$SmoothInterpolator.isSmooth()) break block5;
                return d;
            }
            interpolator$SmoothInterpolator = this;
        }
        return super.tickInterpolation(d, d2, d3);
    }

    @Override
    public boolean isSmooth() {
        double d;
        block2: {
            block3: {
                int n = Interpolator$LinearInterpolator.a;
                double d2 = this.smoothness.getAsDouble() - 1.0;
                d = d2 == 0.0 ? 0 : (d2 > 0.0 ? 1 : -1);
                if (n != 0) break block2;
                if (d == false) break block3;
                d = 1.0;
                break block2;
            }
            d = 0.0;
        }
        return (boolean)d;
    }
}
