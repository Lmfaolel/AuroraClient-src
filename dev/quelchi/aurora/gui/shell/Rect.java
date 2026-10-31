package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.gui.shell.SearchIndex;

public record Rect(int x, int y, int w, int h) {
    public static final Rect NONE = new Rect(0, 0, 0, 0);

    public boolean has(double d, double d2) {
        int n;
        block18: {
            block15: {
                Rect rect;
                double d3;
                boolean bl;
                block17: {
                    int n2;
                    block16: {
                        block14: {
                            bl = SearchIndex.a;
                            d3 = d;
                            if (bl) break block14;
                            if (!(d3 >= (double)this.x)) break block15;
                            d3 = d;
                        }
                        n2 = this.x;
                        if (bl) break block16;
                        if (!(d3 <= (double)(n2 + this.w))) break block15;
                        d3 = d2;
                        rect = this;
                        if (bl) break block17;
                        n2 = rect.y;
                    }
                    if (!(d3 >= (double)n2)) break block15;
                    d3 = d2;
                    rect = this;
                }
                double d4 = d3 - (double)(rect.y + this.h);
                n = d4 == 0.0 ? 0 : (d4 < 0.0 ? -1 : 1);
                if (bl) break block18;
                if (n > 0) break block15;
                n = 1;
                break block18;
            }
            n = 0;
        }
        return n != 0;
    }

    public int right() {
        return this.x + this.w;
    }

    public int bottom() {
        return this.y + this.h;
    }
}
