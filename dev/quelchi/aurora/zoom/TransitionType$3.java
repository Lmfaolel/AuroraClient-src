package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.TransitionType;

final class TransitionType$3
extends TransitionType {
    @Override
    public double apply(double d) {
        return 1.0 - Math.cos(d * Math.PI / 2.0);
    }

    @Override
    public double inverse(double d) {
        return Math.acos(-(d - 1.0)) * 2.0 / Math.PI;
    }

    @Override
    public boolean hasInverse() {
        return true;
    }
}
