package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.Interpolator;

public abstract class Interpolator$LinearInterpolator
implements Interpolator {
    protected boolean goingIn = true;
    public static int a;

    @Override
    public double tickInterpolation(double d, double d2, double d3) {
        double d4;
        block11: {
            double d5;
            block9: {
                int n;
                block10: {
                    n = a;
                    d4 = d;
                    d5 = d2;
                    if (n != 0) break block9;
                    if (!(d4 > d5)) break block10;
                    this.goingIn = true;
                    return Math.min(d2 + this.increment(false, d3, d, d2), d);
                }
                d4 = d;
                if (n != 0) break block11;
                d5 = d2;
            }
            if (d4 < d5) {
                this.goingIn = false;
                return Math.max(d2 - this.increment(true, d3, d, d2), d);
            }
            this.goingIn = true;
            d4 = d;
        }
        return d4;
    }

    @Override
    public boolean isSmooth() {
        return true;
    }

    abstract double increment(boolean var1, double var2, double var4, double var6);
}
