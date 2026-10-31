package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.Interpolator$LinearInterpolator;
import dev.quelchi.aurora.zoom.TransitionType;

final class TransitionType$14
extends TransitionType {
    @Override
    public double apply(double d) {
        double d2;
        block16: {
            block17: {
                int n;
                block14: {
                    block15: {
                        double d3;
                        block12: {
                            block13: {
                                n = Interpolator$LinearInterpolator.a;
                                d3 = d;
                                if (n != 0) break block12;
                                if (d3 == 0.0) break block13;
                                d2 = d;
                                if (n != 0) break block14;
                                if (d2 != 1.0) break block15;
                            }
                            d3 = d;
                        }
                        return d3;
                    }
                    d2 = d;
                }
                if (n != 0) break block16;
                if (!(d2 < 0.5)) break block17;
                return Math.pow(2.0, 20.0 * d - TransitionType.LOG2_1023) - 9.775171065493646E-4;
            }
            d2 = 1.0 - Math.pow(2.0, 10.0 - TransitionType.LOG2_1023 - 10.0 * d) + 9.775171065493646E-4;
        }
        return d2;
    }
}
