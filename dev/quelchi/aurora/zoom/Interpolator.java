package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.Interpolator$1;

public interface Interpolator {
    public static final Interpolator INSTANT = new Interpolator$1();

    public double tickInterpolation(double var1, double var3, double var5);

    default public double modifyInterpolation(double d) {
        return d;
    }

    default public double modifyPrevInterpolation(double d) {
        return d;
    }

    public boolean isSmooth();
}
