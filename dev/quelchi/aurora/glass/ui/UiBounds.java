package dev.quelchi.aurora.glass.ui;

public record UiBounds(int x, int y, int width, int height) {
    public static boolean a;

    public UiBounds {
        n3 = Math.max(0, n3);
        n4 = Math.max(0, n4);
    }

    public int right() {
        return this.x + this.width;
    }

    public int bottom() {
        return this.y + this.height;
    }

    public boolean contains(double d, double d2) {
        byte n;
        block18: {
            block15: {
                int n2;
                double d3;
                boolean bl;
                block17: {
                    block16: {
                        double d4;
                        block14: {
                            bl = a;
                            d3 = d;
                            d4 = this.x;
                            if (bl) break block14;
                            if (!(d3 >= d4)) break block15;
                            d3 = d2;
                            n2 = this.y;
                            if (bl) break block16;
                            d4 = n2;
                        }
                        if (!(d3 >= d4)) break block15;
                        d3 = d;
                        n2 = this.right();
                    }
                    if (bl) break block17;
                    if (!(d3 < (double)n2)) break block15;
                    d3 = d2;
                    n2 = this.bottom();
                }
                double d5 = d3 - (double)n2;
                n = d5 == 0.0 ? (byte)0 : (d5 < 0.0 ? (byte)-1 : 1);
                if (bl) break block18;
                if (n >= 0) break block15;
                n = 1;
                break block18;
            }
            n = 0;
        }
        return n != 0;
    }
}
