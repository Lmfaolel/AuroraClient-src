package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.EffectEngine$Def;
import dev.quelchi.aurora.render.EffectEngine$Prim;

final class EffectEngine$Inst {
    EffectEngine.Def def;
    float[][] pal;
    double ox;
    double oy;
    double oz;
    float fx;
    float fy;
    float fz;
    float floor;
    float sc;
    float age;
    int victim = -1;
    int parts;
    EffectEngine.Prim[] prims;

    EffectEngine$Inst() {
    }
}
