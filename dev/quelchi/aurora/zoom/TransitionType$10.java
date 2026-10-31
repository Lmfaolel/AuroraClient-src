package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.TransitionType;

final class TransitionType$10
extends TransitionType {
    @Override
    public double apply(double d) {
        return 1.0 - Math.pow(1.0 - d, 3.0);
    }

    @Override
    public double inverse(double d) {
        return -Math.pow(-d + 1.0, 0.3333333333333333) + 1.0;
    }

    @Override
    public boolean hasInverse() {
        return true;
    }
}
