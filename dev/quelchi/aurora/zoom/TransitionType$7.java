package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.TransitionType;

final class TransitionType$7
extends TransitionType {
    @Override
    public double apply(double d) {
        return 1.0 - (1.0 - d) * (1.0 - d);
    }

    @Override
    public double inverse(double d) {
        return -(Math.sqrt(-(d - 1.0)) - 1.0);
    }

    @Override
    public boolean hasInverse() {
        return true;
    }
}
