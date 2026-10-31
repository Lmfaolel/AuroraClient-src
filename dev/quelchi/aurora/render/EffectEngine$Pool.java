package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Def;
import dev.quelchi.aurora.render.EffectEngine$Inst;
import dev.quelchi.aurora.render.EffectEngine$Layer;
import dev.quelchi.aurora.render.EffectEngine$P;
import dev.quelchi.aurora.render.EffectEngine$Prim;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

final class EffectEngine$Pool {
    final EffectEngine$P[] ps;
    int n;
    final List<EffectEngine$Inst> insts = new ArrayList<EffectEngine$Inst>();
    Random rnd;
    float time;
    final float[] lights = new float[56];
    int lightN;
    private float[] dir = new float[3];

    EffectEngine$Pool(int n, long l) {
        this.ps = new EffectEngine$P[n];
        for (int i = 0; i < n; ++i) {
            this.ps[i] = new EffectEngine$P();
        }
        this.rnd = new Random(l);
    }

    void clear() {
        this.n = 0;
        this.insts.clear();
    }

    
    EffectEngine$Inst start(EffectEngine$Def var1_1, int var2_2, double var3_3, double var5_4, double var7_5, float var9_6, float var10_7, float var11_8, float var12_9, float var13_10) {
        block25: {
            block27: {
                block23: {
                    block24: {
                        var15_11 = new EffectEngine$Inst();
                        var15_11.def = var1_1;
                        var15_11.pal = var1_1.pals[Math.max(0, Math.min(var1_1.pals.length - 1, var2_2))];
                        var15_11.ox = var3_3;
                        var15_11.oy = var5_4;
                        var15_11.oz = var7_5;
                        var14_12 = EffectEngine.a;
                        var16_13 = (float)Math.sqrt(var9_6 * var9_6 + var10_7 * var10_7 + var11_8 * var11_8);
                        if (var14_12 != 0) break block23;
                        if (!(var16_13 < 1.0E-4f)) break block24;
                        var11_8 = 1.0f;
                        var16_13 = 1.0f;
                    }
                    var15_11.fx = var9_6 / var16_13;
                    var15_11.fy = var10_7 / var16_13;
                    var15_11.fz = var11_8 / var16_13;
                    var15_11.floor = var12_9;
                    var15_11.sc = var13_10;
                }
                var17_14 = new ArrayList<E>();
                var18_15 = var1_1.layers;
                var19_16 = var18_15.length;
                var20_17 = 0;
                while (var20_17 < var19_16) {
                    block30: {
                        block28: {
                            block29: {
                                block26: {
                                    var21_18 = var18_15[var20_17];
                                    if (var14_12 != 0) break block25;
                                    v1 = var21_18;
                                    if (var14_12 != 0) break block26;
                                    if (v1.kind == 0) {
                                    }
                                    for (var22_19 = 0; var22_19 < var21_18.count; ++var22_19) {
                                        v4 = this;
                                        if (var14_12 == 0) {
                                            v4.spawn(var15_11, var21_18, var22_19);
                                            if (var14_12 == 0) continue;
                                            break;
                                        }
                                        break block27;
                                    }
                                    if (var14_12 == 0) break block28;
lbl56:

                                    v1 = var21_18;
                                }
                                v7 = v1.kind;
                                if (var14_12 != 0) break block28;
                                if (v7 != 6) break block29;
                                block18: for (EffectEngine$Inst var23_21 : this.insts) {
                                    var24_22 = var23_21.prims;
                                    if (var14_12 != 0) break block30;
                                    var25_23 = var24_22.length;
                                    var26_24 = 0;
                                    while (var26_24 < var25_23) {
                                        block31: {
                                            var27_25 = var24_22[var26_24];
                                            if (var14_12 != 0) break block31;
                                            if (var14_12 != 0) continue block18;
                                            if (var27_25.l.kind == 6) {
                                                var27_25.age = 1.0E9f;
                                            }
                                            ++var26_24;
                                        }
                                        if (var14_12 == 0) continue;
                                    }
                                    if (var14_12 == 0) continue;
                                }
                            }
                            v7 = (int)var17_14.add(this.prim(var21_18));
                        }
                        ++var20_17;
                    }
                    if (var14_12 == 0) continue;
                }
                var15_11.prims = var17_14.toArray(new EffectEngine$Prim[0]);
                v4 = this;
            }
            v4.insts.add(var15_11);
        }
        return var15_11;
    }

    
    private EffectEngine$Prim prim(EffectEngine$Layer effectEngine$Layer) {
        EffectEngine$Prim effectEngine$Prim = new EffectEngine$Prim();
        effectEngine$Prim.l = effectEngine$Layer;
        effectEngine$Prim.age = -effectEngine$Layer.delay;
        int n = EffectEngine.a;
        EffectEngine$Layer effectEngine$Layer2 = effectEngine$Layer;
        if (n == 0) {
            if (effectEngine$Layer2.kind == 3) {
                effectEngine$Prim.tilt = (float)Math.toRadians(EffectEngine.pick(this.rnd, effectEngine$Layer.tilt));
                effectEngine$Prim.lean = (float)Math.toRadians(EffectEngine.pick(this.rnd, effectEngine$Layer.lean));
                if (n == 0) return effectEngine$Prim;
            }
            effectEngine$Layer2 = effectEngine$Layer;
        }
        int n2 = effectEngine$Layer2.kind;
        if (n == 0) {
            if (n2 == 5) {
                effectEngine$Prim.rays = new float[effectEngine$Layer.count * 11];
                for (int i = 0; i < effectEngine$Layer.count; ++i) {
                    float f = this.rnd.nextFloat() * ((float)Math.PI * 2);
                    float f2 = (float)Math.sqrt(this.rnd.nextFloat()) * effectEngine$Layer.base;
                    int n3 = i * 11;
                    effectEngine$Prim.rays[n3] = ((float)i + this.rnd.nextFloat() * 0.85f) / (float)effectEngine$Layer.count * ((float)Math.PI * 2);
                    effectEngine$Prim.rays[n3 + 1] = (float)Math.toRadians(EffectEngine.pick(this.rnd, effectEngine$Layer.tilt));
                    if (n != 0) return effectEngine$Prim;
                    effectEngine$Prim.rays[n3 + 2] = EffectEngine.pick(this.rnd, effectEngine$Layer.length);
                    effectEngine$Prim.rays[n3 + 3] = EffectEngine.pick(this.rnd, effectEngine$Layer.widthVar);
                    effectEngine$Prim.rays[n3 + 4] = this.rnd.nextFloat() * 6.0f;
                    effectEngine$Prim.rays[n3 + 5] = this.rnd.nextFloat() * effectEngine$Layer.stagger;
                    effectEngine$Prim.rays[n3 + 6] = effectEngine$Layer.life[0] * (0.65f + 0.35f * this.rnd.nextFloat());
                    effectEngine$Prim.rays[n3 + 7] = 0.55f + 0.45f * this.rnd.nextFloat();
                    effectEngine$Prim.rays[n3 + 8] = 0.5f + this.rnd.nextFloat();
                    effectEngine$Prim.rays[n3 + 9] = (float)Math.cos(f) * f2;
                    effectEngine$Prim.rays[n3 + 10] = (float)Math.sin(f) * f2;
                    if (n == 0) continue;
                }
                if (n == 0) return effectEngine$Prim;
            }
            n2 = effectEngine$Layer.kind;
        }
        if (n2 != 4) return effectEngine$Prim;
        effectEngine$Prim.seed = this.rnd.nextLong();
        return effectEngine$Prim;
    }

    
    EffectEngine$P spawn(EffectEngine$Inst var1_1, EffectEngine$Layer var2_2, int var3_3) {
        block60: {
            block61: {
                block62: {
                    block63: {
                        var4_4 = EffectEngine.a;
                        v0 = this;
                        if (var4_4 == 0) {
                            if (v0.n >= this.ps.length) {
                                return null;
                            }
                            v0 = this;
                        }
                        var5_5 = v0.ps[this.n++];
                        ++var1_1.parts;
                        var6_6 = this.rnd;
                        var7_7 = var1_1.sc;
                        var8_8 = var2_2.x;
                        var9_9 = var2_2.y;
                        var10_10 = var2_2.z;
                        if (var4_4 != 0) // goto
                        switch (var2_2.emit) {
                            case 1: {
                                EffectEngine.randDir(var6_6, this.dir);
lbl18:

                                var11_11 = var2_2.r * (float)Math.cbrt(var6_6.nextFloat());
                                var8_8 += this.dir[0] * var11_11;
                                var9_9 += this.dir[1] * var11_11;
                                var10_10 += this.dir[2] * var11_11;
                                if (var4_4 == 0) break;
                            }
                            case 2: {
                                var11_11 = var6_6.nextFloat() * 6.2831855f;
                                var12_12 = var2_2.r * (float)Math.sqrt(var6_6.nextFloat());
                                var8_8 += (float)Math.cos(var11_11) * var12_12;
                                var10_10 += (float)Math.sin(var11_11) * var12_12;
                                if (var4_4 == 0) break;
                            }
                            case 3: {
                                var11_11 = ((float)var3_3 / (float)Math.max(1, var2_2.count) + var6_6.nextFloat() * 0.3f / (float)Math.max(1, var2_2.count)) * 6.2831855f;
                                var8_8 += (float)Math.cos(var11_11) * var2_2.r;
                                var10_10 += (float)Math.sin(var11_11) * var2_2.r;
                                if (var4_4 == 0) break;
                            }
                            case 4: {
                                var8_8 += (var6_6.nextFloat() - 0.5f) * var2_2.box[0];
                                var9_9 += var6_6.nextFloat() * var2_2.box[1];
                                var10_10 += (var6_6.nextFloat() - 0.5f) * var2_2.box[2];
                                break;
                            }
                        }
                        var11_11 = 0.0f;
                        var12_12 = 0.0f;
                        var13_13 = 0.0f;
                        if (var4_4 != 0) // goto
                        switch (var2_2.dir) {
                            case 1: {
                                EffectEngine.randDir(var6_6, this.dir);
                                var11_11 = this.dir[0];
                                var12_12 = this.dir[1];
                                var13_13 = this.dir[2];
lbl49:

                                if (var4_4 == 0) break;
                            }
                            case 2: {
                                EffectEngine.randDir(var6_6, this.dir);
                                var11_11 = this.dir[0];
                                var12_12 = Math.abs(this.dir[1]);
                                var13_13 = this.dir[2];
                                if (var4_4 == 0) break;
                            }
                            case 3: {
                                var14_14 = (float)Math.hypot(var8_8, var10_10);
                                var15_15 = var6_6.nextFloat() * 6.2831855f;
                                v1 = var14_14;
                                if (var4_4 != 0) // goto
                                if (v1 > 0.001f) {
                                    v2 = var8_8 / var14_14;
                                } else {
                                    v1 = var15_15;
lbl65:

                                    v2 = (float)Math.cos(v1);
                                }
                                var11_11 = v2;
                                v3 = var14_14;
                                if (var4_4 != 0) // goto
                                if (v3 > 0.001f) {
                                    v4 = var10_10 / var14_14;
                                } else {
                                    v3 = var15_15;
lbl73:

                                    v4 = var13_13 = (float)Math.sin(v3);
                                }
                                if (var4_4 == 0) break;
                            }
                            case 4: 
                            case 5: {
                                var14_14 = var2_2.dir == 4 ? 0.0f : var1_1.fx;
                                var15_15 = var2_2.dir == 4 ? 1.0f : var1_1.fy;
                                var16_16 = var2_2.dir == 4 ? 0.0f : var1_1.fz;
                                EffectEngine.randDir(var6_6, this.dir);
                                var17_17 = (float)Math.sin(Math.min(var2_2.cone, 1.5f)) * var6_6.nextFloat() * 1.5f;
                                var11_11 = var14_14 + this.dir[0] * var17_17;
                                var12_12 = var15_15 + this.dir[1] * var17_17;
                                var13_13 = var16_16 + this.dir[2] * var17_17;
                                var18_18 = (float)Math.sqrt(var11_11 * var11_11 + var12_12 * var12_12 + var13_13 * var13_13);
                                var11_11 /= var18_18;
                                var12_12 /= var18_18;
                                var13_13 /= var18_18;
                                if (var4_4 == 0) break;
                            }
                            case 6: {
                                var14_14 = var8_8 - var2_2.x;
                                var15_15 = var9_9 - var2_2.y;
                                var16_16 = var10_10 - var2_2.z;
                                var17_17 = (float)Math.sqrt(var14_14 * var14_14 + var15_15 * var15_15 + var16_16 * var16_16);
                                if (var4_4 == 0) {
                                    if (var17_17 > 0.001f) {
                                        var11_11 = -var14_14 / var17_17;
                                        var12_12 = -var15_15 / var17_17;
                                        var13_13 = -var16_16 / var17_17;
                                        if (var4_4 == 0) break;
                                    }
                                    EffectEngine.randDir(var6_6, this.dir);
                                    var11_11 = this.dir[0];
                                    var12_12 = this.dir[1];
                                }
                                var13_13 = this.dir[2];
                                break;
                            }
                        }
                        var14_14 = EffectEngine.pick(var6_6, var2_2.speed);
                        var15_15 = var2_2.jitter;
                        var16_16 = var11_11 * var14_14 + (var6_6.nextFloat() - 0.5f) * 2.0f * var15_15;
                        var17_17 = var12_12 * var14_14 + EffectEngine.pick(var6_6, var2_2.vy);
                        var18_18 = var13_13 * var14_14 + (var6_6.nextFloat() - 0.5f) * 2.0f * var15_15;
                        v5 = var2_2;
                        if (var4_4 == 0) {
                            if (v5.swirl != 0.0f) {
                                var19_19 = var8_8 - var2_2.x;
                                var20_20 = var10_10 - var2_2.z;
                                var21_21 = (float)Math.hypot(var19_19, var20_20);
                                if (var4_4 == 0) {
                                    if (var21_21 == 0.0f) {
                                        var21_21 = 1.0f;
                                    }
                                    var16_16 -= var20_20 / var21_21 * var2_2.swirl;
                                }
                                var18_18 += var19_19 / var21_21 * var2_2.swirl;
                            }
                            v5 = var2_2;
                        }
                        if (var4_4 == 0) {
                            if (v5.vel != null) {
                                var16_16 += var2_2.vel[0];
                                var17_17 += var2_2.vel[1];
                                var18_18 += var2_2.vel[2];
                            }
                            v5 = var2_2;
                        }
                        if (v5.orient == 4) {
                            var19_19 = (float)Math.toRadians(EffectEngine.pick(var6_6, var2_2.tilt));
                            var20_20 = (float)Math.toRadians(EffectEngine.pick(var6_6, var2_2.roll));
                            var21_21 = var1_1.fz;
                            var22_22 = -var1_1.fx;
                            v6 = var23_23 = (float)Math.sqrt(var21_21 * var21_21 + var22_22 * var22_22);
                            if (var4_4 == 0) {
                                if (v6 > 1.0E-4f) {
                                    var21_21 /= var23_23;
                                    var22_22 /= var23_23;
                                }
                                v6 = (float)Math.cos(var19_19);
                            }
                            var24_24 = v6;
                            var25_25 = (float)Math.sin(var19_19);
                            var26_26 = (float)Math.sin(var20_20);
                            var27_27 = var1_1.fx * var25_25 + var21_21 * var26_26;
                            var28_28 = var24_24 + var1_1.fy * var25_25;
                            var29_29 = var1_1.fz * var25_25 + var22_22 * var26_26;
                            var30_30 = (float)Math.sqrt(var27_27 * var27_27 + var28_28 * var28_28 + var29_29 * var29_29);
                            var5_5.nx = var27_27 / var30_30;
                            var5_5.ny = var28_28 / var30_30;
                            var5_5.nz = var29_29 / var30_30;
                        }
                        var5_5.e = var1_1;
                        var5_5.l = var2_2;
                        var5_5.x = var8_8 * var7_7;
                        var5_5.y = var9_9 * var7_7;
                        var5_5.z = var10_10 * var7_7;
                        var5_5.vx = var16_16 * var7_7;
                        var5_5.vy = var17_17 * var7_7;
                        v7 = var5_5;
                        v8 = var18_18;
                        v9 = var7_7;
                        if (var4_4 == 0) {
                            v7.vz = v8 * v9;
                            v7 = var5_5;
                            v8 = var2_2.delay;
                            v9 = var2_2.over > 0.0f ? var6_6.nextFloat() * var2_2.over : 0.0f;
                        }
                        v7.age = -(v8 + v9);
                        var5_5.life = Math.max(0.02f, EffectEngine.pick(var6_6, var2_2.life));
                        v10 = var5_5;
                        if (var4_4 != 0) break block60;
                        v10.size = EffectEngine.pick(var6_6, var2_2.size) * var7_7;
                        var5_5.rot = EffectEngine.pick(var6_6, var2_2.rot);
                        var5_5.spin = EffectEngine.pick(var6_6, var2_2.spin);
                        var5_5.seed = var6_6.nextFloat() * 100.0f;
                        var5_5.st = EffectEngine.pick(var6_6, var2_2.stretch);
                        var5_5.tn = 0;
                        var5_5.th = 0;
                        var5_5.tAcc = 0.0f;
                        if (var2_2.trailLen <= 0) break block61;
                        v11 = var5_5;
                        if (var4_4 != 0) break block62;
                        if (v11.trail == null) break block63;
                        v10 = var5_5;
                        if (var4_4 != 0) break block60;
                        if (v10.trail.length >= var2_2.trailLen * 3) break block61;
                    }
                    v11 = var5_5;
                }
                v11.trail = new float[96];
            }
            v10 = var5_5;
        }
        return v10;
    }

    
    void step(float var1_1) {
        this.time += var1_1;
        var3_2 = this.n - 1;
        var2_3 = EffectEngine.a;
        while (var3_2 >= 0) {
            block99: {
                block85: {
                    block92: {
                        block96: {
                            block93: {
                                block98: {
                                    block97: {
                                        block94: {
                                            block95: {
                                                block91: {
                                                    block89: {
                                                        block90: {
                                                            block88: {
                                                                block86: {
                                                                    block87: {
                                                                        block83: {
                                                                            block84: {
                                                                                v0 /* !! */  = this.ps;
                                                                                if (var2_3 != 0) // goto
                                                                                var4_4 = v0 /* !! */ [var3_2];
                                                                                var5_5 = var4_4.l;
                                                                                v1 = var4_4;
                                                                                if (var2_3 != 0) break block83;
                                                                                if (!(v1.age < 0.0f)) break block84;
                                                                                var4_4.age += var1_1;
                                                                                if (var2_3 == 0) break block85;
                                                                            }
                                                                            v1 = var4_4;
                                                                        }
                                                                        var6_6 = v1.e.sc;
                                                                        v5 = var4_4;
                                                                        v6 = v5;
                                                                        v7 = v5;
                                                                        if (var2_3 != 0) break block86;
                                                                        v7.vy = v6.vy - var5_5.gravity * var6_6 * var1_1;
                                                                        if (var5_5.drag == 0.0f) break block87;
                                                                        var7_8 = (float)Math.exp(-var5_5.drag * var1_1);
                                                                        var4_4.vx *= var7_8;
                                                                        var4_4.vy *= var7_8;
                                                                        var4_4.vz *= var7_8;
                                                                    }
                                                                    var4_4.x += var4_4.vx * var1_1;
                                                                    var4_4.y += var4_4.vy * var1_1;
                                                                    v9 = var4_4;
                                                                    if (var2_3 != 0) break block88;
                                                                    v9.z += var4_4.vz * var1_1;
                                                                    v10 = var4_4;
                                                                    v6 = v10;
                                                                    v7 = v10;
                                                                }
                                                                v7.rot = v6.rot + var4_4.spin * var1_1;
                                                                if (var5_5.flutter != null) {
                                                                    var4_4.x += (float)Math.cos(var4_4.age * var5_5.flutter[1] + var4_4.seed) * var5_5.flutter[0] * var6_6 * var1_1;
                                                                    var4_4.z += (float)Math.sin(var4_4.age * var5_5.flutter[1] * 1.3f + var4_4.seed) * var5_5.flutter[0] * var6_6 * var1_1;
                                                                }
                                                                v9 = var4_4;
                                                            }
                                                            var7_8 = (float)((double)v9.e.floor - var4_4.e.oy) + 0.03f;
                                                            v13 = var5_5;
                                                            if (var2_3 != 0) break block89;
                                                            if (!v13.collides) break block90;
                                                            cfr_temp_0 = var4_4.y - var7_8;
                                                            v15 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                                            if (var2_3 != 0) break block91;
                                                            if (v15 >= 0) break block90;
                                                            cfr_temp_1 = var4_4.vy - 0.0f;
                                                            v15 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
                                                            if (var2_3 != 0) break block91;
                                                            if (v15 >= 0) break block90;
                                                            var4_4.y = var7_8;
                                                            var4_4.vy *= -var5_5.collide;
                                                            var4_4.vx *= 0.6f;
                                                            var4_4.vz *= 0.6f;
                                                            var4_4.spin *= 0.5f;
                                                        }
                                                        v13 = var5_5;
                                                    }
                                                    v15 = v13.trailLen;
                                                }
                                                if (var2_3 != 0) break block92;
                                                if (v15 <= 0) break block93;
                                                v22 = var4_4;
                                                if (var2_3 != 0) break block94;
                                                v22.tAcc += var1_1;
                                                if (var4_4.tAcc >= 0.016666668f) break block95;
                                                v25 = var4_4;
                                                if (var2_3 != 0) break block96;
                                                if (v25.tn != 0) break block93;
                                            }
                                            var4_4.tAcc = 0.0f;
                                            v22 = var4_4;
                                        }
                                        var8_11 = (v22.th + var4_4.tn) % var5_5.trailLen;
                                        v29 = var4_4;
                                        if (var2_3 != 0) break block97;
                                        if (v29.tn == var5_5.trailLen) {
                                        }
                                        var8_11 = var4_4.th;
                                        var4_4.th = (var4_4.th + 1) % var5_5.trailLen;
                                        if (var2_3 == 0) break block98;
lbl164:

                                        v29 = var4_4;
                                    }
                                    ++v29.tn;
                                }
                                var4_4.trail[var8_11 * 3] = var4_4.x;
                                var4_4.trail[var8_11 * 3 + 1] = var4_4.y;
                                var4_4.trail[var8_11 * 3 + 2] = var4_4.z;
                            }
                            var4_4.age += var1_1;
                            if (var2_3 != 0) break block99;
                            v25 = var4_4;
                        }
                        v15 = (cfr_temp_2 = v25.age - var4_4.life) == 0.0f ? 0 : (cfr_temp_2 > 0.0f ? 1 : -1);
                    }
                    if (v15 >= 0) {
                        this.remove(var3_2);
                    }
                }
                --var3_2;
            }
            if (var2_3 == 0) continue;
        }
        var3_2 = this.insts.size() - 1;
        while (var3_2 >= 0) {
            block100: {
                block108: {
                    block107: {
                        v0 /* !! */  = this.insts.get(var3_2);
lbl198:

                        block76: while (true) {
                            var4_4 = (EffectEngine$Inst)v0 /* !! */ ;
                            var4_4.age += var1_1;
                            var5_5 = var4_4.prims;
                            var6_7 = var5_5.length;
                            var7_10 = 0;
                            while (var7_10 < var6_7) {
                                block101: {
                                    block102: {
                                        block104: {
                                            block105: {
                                                block103: {
                                                    var8_13 = var5_5[var7_10];
                                                    var8_13.age += var1_1;
                                                    var9_14 = var8_13.l;
                                                    if (var2_3 != 0) break block100;
                                                    if (var2_3 != 0) break block101;
                                                    if (var9_14.kind != 3) break block102;
                                                    if (var9_14.shed == null) break block102;
                                                    v37 = var8_13.age;
                                                    if (var2_3 != 0) break block103;
                                                    if (v37 < 0.0f) break block102;
                                                    v40 = var8_13.age;
                                                    v41 = var9_14;
                                                    if (var2_3 != 0) break block104;
                                                    v37 = v40 / v41.life[0];
                                                }
                                                if (!(v37 > 0.45f)) break block105;
                                                break block102;
                                            }
                                            var8_13.acc += var1_1 * var9_14.shed.rate;
                                            v40 = var8_13.age;
                                            v41 = var9_14;
                                        }
                                        var10_15 = EffectEngine.arcHead(v40 / v41.life[0]);
                                        while (var8_13.acc >= 1.0f) {
                                            block106: {
                                                v0 /* !! */  = this;
                                                if (var2_3 != 0) continue block76;
                                                if (var2_3 != 0) break block106;
                                                if (v0 /* !! */ .n >= this.ps.length) break;
                                                var8_13.acc -= 1.0f;
                                                EffectEngine.arcAt((EffectEngine$Inst)var4_4, var8_13, Math.max(0.0f, var10_15 - this.rnd.nextFloat() * 0.1f), 1.0f, EffectEngine.ARC_OUT);
                                                v46 = this;
                                            }
                                            var11_16 = v46.spawn((EffectEngine$Inst)var4_4, var9_14.shed, 0);
                                            if (var11_16 == null) {
                                                break;
                                            }
                                            var12_17 = var9_14.shed.carry * var4_4.sc;
                                            var11_16.x = EffectEngine.ARC_OUT[0];
                                            var11_16.y = EffectEngine.ARC_OUT[1];
                                            var11_16.z = EffectEngine.ARC_OUT[2];
                                            var11_16.vx += EffectEngine.ARC_OUT[3] * var12_17;
                                            var11_16.vy += EffectEngine.ARC_OUT[4] * var12_17;
                                            var11_16.vz += EffectEngine.ARC_OUT[5] * var12_17;
                                            var11_16.age = 0.0f;
                                            if (var2_3 == 0) continue;
                                        }
                                    }
                                    ++var7_10;
                                }
                                if (var2_3 == 0) continue;
                            }
                            break;
                        }
                        v49 = var4_4;
                        if (var2_3 != 0) break block107;
                        if (!(v49.age > var4_4.def.dur + 0.1f)) break block108;
                        v49 = var4_4;
                    }
                    if (var2_3 != 0 || v49.parts > 0) break block108;
                    v49 = this.insts.remove(var3_2);
                }
                --var3_2;
            }
            if (var2_3 == 0) continue;
        }
    }

    private void remove(int n) {
        EffectEngine$P effectEngine$P = this.ps[n];
        --effectEngine$P.e.parts;
        effectEngine$P.e = null;
        --this.n;
        this.ps[n] = this.ps[this.n];
        this.ps[this.n] = effectEngine$P;
    }

    boolean idle() {
        return this.insts.isEmpty();
    }
}
