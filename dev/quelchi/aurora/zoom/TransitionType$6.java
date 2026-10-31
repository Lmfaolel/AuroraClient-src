package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.TransitionType;

final class TransitionType$6
extends TransitionType {
    @Override
    public double apply(double d) {
        return d * d;
    }

    @Override
    public double inverse(double d) {
        return Math.sqrt(d);
    }

    @Override
    public boolean hasInverse() {
        return true;
    }
}
