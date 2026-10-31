package dev.quelchi.aurora.glass.hud;

import dev.quelchi.aurora.glass.hud.Anchor;
import dev.quelchi.aurora.glass.hud.GlassHudElement;
import dev.quelchi.aurora.glass.ui.UiBounds;

public final class HudPlacement {
    private Anchor anchor = Anchor.TOP_LEFT;
    private float percentX;
    private float percentY;
    private int offsetX;
    private int offsetY;
    private float scale = 1.0f;
    private float rotation;
    private static final String[] a;

    public Anchor anchor() {
        return this.anchor;
    }

    public HudPlacement anchor(Anchor anchor) {
        Anchor anchor2;
        block2: {
            block3: {
                int n = GlassHudElement.a;
                HudPlacement hudPlacement = this;
                anchor2 = anchor;
                if (n != 0) break block2;
                if (anchor2 != null) break block3;
                anchor2 = Anchor.TOP_LEFT;
                break block2;
            }
            anchor2 = anchor;
        }
        hudPlacement.anchor = anchor2;
        return this;
    }

    public float percentX() {
        return this.percentX;
    }

    public float percentY() {
        return this.percentY;
    }

    public HudPlacement percent(float f, float f2) {
        this.percentX = this.clampPercent(f);
        this.percentY = this.clampPercent(f2);
        return this;
    }

    public int offsetX() {
        return this.offsetX;
    }

    public int offsetY() {
        return this.offsetY;
    }

    public HudPlacement offset(int n, int n2) {
        this.offsetX = n;
        this.offsetY = n2;
        return this;
    }

    public float scale() {
        return this.scale;
    }

    public HudPlacement scale(float f) {
        if (!Float.isFinite(f)) {
            throw new IllegalArgumentException(a[1]);
        }
        this.scale = Math.max(0.1f, Math.min(8.0f, f));
        return this;
    }

    public float rotation() {
        return this.rotation;
    }

    public HudPlacement rotation(float f) {
        if (!Float.isFinite(f)) {
            throw new IllegalArgumentException(a[2]);
        }
        this.rotation = f;
        return this;
    }

    public UiBounds bounds(int n, int n2, int n3, int n4) {
        int n5 = Math.max(0, Math.round((float)n3 * this.scale));
        int n6 = Math.max(0, Math.round((float)n4 * this.scale));
        int n7 = this.anchor.x(n, n5) + Math.round((float)n * this.percentX) + this.offsetX;
        int n8 = this.anchor.y(n2, n6) + Math.round((float)n2 * this.percentY) + this.offsetY;
        return new UiBounds(n7, n8, n5, n6);
    }

    public void reset() {
        this.anchor = Anchor.TOP_LEFT;
        this.percentX = 0.0f;
        this.percentY = 0.0f;
        this.offsetX = 0;
        this.offsetY = 0;
        this.scale = 1.0f;
        this.rotation = 0.0f;
    }

    private float clampPercent(float f) {
        float f2;
        block4: {
            block5: {
                int n = GlassHudElement.a;
                f2 = f;
                if (n != 0) break block4;
                if (Float.isFinite(f2)) break block5;
                throw new IllegalArgumentException(a[0]);
            }
            f2 = Math.max(-1.0f, Math.min(1.0f, f));
        }
        return f2;
    }

    
    static {
        block12: {
            var5 = new String[3];
            var3_1 = 0;
            var2_2 = ">D8?\u0017\u0013g\u0005=B+0\u0017\b<N>=\u0006\u0014| ";
            var4_3 = ">D8?\u0017\u0013g\u0005=B+0\u0017\b<N>=\u0006\u0014| ".length();
            var1_4 = 7;
            var0_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var5[var3_1++] = new String(v0).intern();
                if ((var0_5 += var1_4) < var4_3) {
                    var1_4 = var2_2.charAt(var0_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var0_5;
            v2 = var2_2.substring(v1, v1 + var1_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 78;
                        break;
                    }
                    case 1: {
                        v6 = 33;
                        break;
                    }
                    case 2: {
                        v6 = 74;
                        break;
                    }
                    case 3: {
                        v6 = 92;
                        break;
                    }
                    case 4: {
                        v6 = 114;
                        break;
                    }
                    case 5: {
                        v6 = 125;
                        break;
                    }
                    default: {
                        v6 = 19;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        HudPlacement.a = var5;
    }
}
