package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.TransitionType;

final class TransitionType$9
extends TransitionType {
    @Override
    public double apply(double d) {
        return d * d * d;
    }

    @Override
    public double inverse(double d) {
        return Math.pow(d, 0.3333333333333333);
    }

    @Override
    public boolean hasInverse() {
        return true;
    }
}
