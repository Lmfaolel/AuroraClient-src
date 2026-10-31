package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.EffectEngine$Layer;

final class EffectEngine$Prim {
    EffectEngine$Layer l;
    float age;
    float tilt;
    float lean;
    float acc;
    float[] rays;
    long seed;
    int strike = -1;
    float[][] paths;

    EffectEngine$Prim() {
    }
}
