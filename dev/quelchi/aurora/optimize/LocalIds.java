package dev.quelchi.aurora.optimize;

public final class LocalIds {
    private static int next;

    private LocalIds() {
    }

    public static int next() {
        return next--;
    }

    
    static {
        long l = 6374927004153585167L;
        long l2 = 0xE0488F91606E23CFL ^ l;
        next = (int)l2;
    }
}
