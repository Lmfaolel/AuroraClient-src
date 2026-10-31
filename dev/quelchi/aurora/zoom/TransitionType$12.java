package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.Interpolator$LinearInterpolator;
import dev.quelchi.aurora.zoom.TransitionType;

final class TransitionType$12
extends TransitionType {
    @Override
    public double apply(double d) {
        double d2;
        block10: {
            block11: {
                double d3;
                block8: {
                    block9: {
                        int n = Interpolator$LinearInterpolator.a;
                        d3 = d;
                        if (n != 0) break block8;
                        if (d3 == 0.0) break block9;
                        d2 = d;
                        if (n != 0) break block10;
                        if (d2 != 1.0) break block11;
                    }
                    d3 = d;
                }
                return d3;
            }
            d2 = Math.pow(2.0, 10.0 * d - TransitionType.LOG2_1023) - 9.775171065493646E-4;
        }
        return d2;
    }

    @Override
    public double inverse(double d) {
        double d2;
        block10: {
            block11: {
                double d3;
                block8: {
                    block9: {
                        int n = Interpolator$LinearInterpolator.a;
                        d3 = d;
                        if (n != 0) break block8;
                        if (d3 == 0.0) break block9;
                        d2 = d;
                        if (n != 0) break block10;
                        if (d2 != 1.0) break block11;
                    }
                    d3 = d;
                }
                return d3;
            }
            d2 = Math.log(1023.0 * d + 1.0) / (10.0 * Math.log(2.0));
        }
        return d2;
    }

    @Override
    public boolean hasInverse() {
        return true;
    }
}
