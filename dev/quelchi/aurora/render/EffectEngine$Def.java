package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.EffectEngine$Layer;
import dev.quelchi.aurora.render.EffectEngine$Pool;
import dev.quelchi.aurora.render.EffectEngine$Sound;

public final class EffectEngine$Def {
    final float dur;
    final float hide;
    final int anchor;
    final EffectEngine$Layer[] layers;
    final float[][][] pals;
    final EffectEngine$Sound[] sounds;
    EffectEngine$Pool tile;
    EffectEngine$Pool stage;
    float stagePower;
    float tileScale = 1.0f;
    float tileMid;

    EffectEngine$Def(float f, float f2, int n, EffectEngine$Layer[] effectEngine$LayerArray, float[][][] fArray, EffectEngine$Sound[] effectEngine$SoundArray) {
        this.dur = f;
        this.hide = f2;
        this.anchor = n;
        this.layers = effectEngine$LayerArray;
        this.pals = fArray;
        this.sounds = effectEngine$SoundArray;
    }
}
