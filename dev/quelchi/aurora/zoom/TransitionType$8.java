package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.Interpolator$LinearInterpolator;
import dev.quelchi.aurora.zoom.TransitionType;

final class TransitionType$8
extends TransitionType {
    @Override
    public double apply(double d) {
        double d2;
        block6: {
            double d3;
            block4: {
                block5: {
                    int n = Interpolator$LinearInterpolator.a;
                    d3 = d;
                    if (n != 0) break block4;
                    if (!(d3 < 0.5)) break block5;
                    d2 = 2.0 * d * d;
                    break block6;
                }
                d3 = 1.0;
            }
            d2 = d3 - Math.pow(-2.0 * d + 2.0, 2.0) / 2.0;
        }
        return d2;
    }
}
