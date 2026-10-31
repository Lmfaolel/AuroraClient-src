package dev.quelchi.aurora.util;

public final class Daemon {
    private Daemon() {
    }

    public static Thread run(String string, Runnable runnable) {
        Thread thread = new Thread(runnable, string);
        thread.setDaemon(true);
        thread.start();
        return thread;
    }
}
