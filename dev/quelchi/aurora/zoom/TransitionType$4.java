package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.TransitionType;

final class TransitionType$4
extends TransitionType {
    @Override
    public double apply(double d) {
        return Math.sin(d * Math.PI / 2.0);
    }

    @Override
    public double inverse(double d) {
        return Math.asin(d) * 2.0 / Math.PI;
    }

    @Override
    public boolean hasInverse() {
        return true;
    }
}
