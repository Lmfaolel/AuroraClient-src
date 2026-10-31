package dev.quelchi.aurora.glass.ui;

public record Insets(int top, int right, int bottom, int left) {
    public static final Insets ZERO = new Insets(0, 0, 0, 0);

    public Insets {
        n = Math.max(0, n);
        n2 = Math.max(0, n2);
        n3 = Math.max(0, n3);
        n4 = Math.max(0, n4);
    }

    public static Insets all(int n) {
        return new Insets(n, n, n, n);
    }

    public static Insets xy(int n, int n2) {
        return new Insets(n2, n, n2, n);
    }

    public int horizontal() {
        return this.left + this.right;
    }

    public int vertical() {
        return this.top + this.bottom;
    }
}
