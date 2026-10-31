package dev.quelchi.aurora.input;

public final class RawMouse {
    private static double dx;
    private static double dy;
    private static final double CAP = 4000.0;

    private RawMouse() {
    }

    public static void moved(double d, double d2) {
        dx = Math.max(-4000.0, Math.min(4000.0, dx + d));
        dy = Math.max(-4000.0, Math.min(4000.0, dy + d2));
    }

    public static double takeX() {
        double d = dx;
        dx = 0.0;
        return d;
    }

    public static double takeY() {
        double d = dy;
        dy = 0.0;
        return d;
    }
}
