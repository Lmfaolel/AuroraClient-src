package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.Anim;

final class Anim$State {
    float value;
    long nanos;

    Anim$State(float f) {
        this.value = f;
        this.nanos = Anim.now();
    }
}
