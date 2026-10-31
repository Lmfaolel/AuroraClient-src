package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.TransitionType;

final class TransitionType$5
extends TransitionType {
    @Override
    public double apply(double d) {
        return -(Math.cos(Math.PI * d) - 1.0) / 2.0;
    }
}
