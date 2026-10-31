package dev.quelchi.aurora.perf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.util.Util;

public final class PackScan {
    public static boolean skip;
    private static CompletableFuture<Void> inFlight;

    private PackScan() {
    }

    public static CompletableFuture<Void> start(ResourcePackManager resourcePackManager) {
        inFlight = CompletableFuture.runAsync(() -> ((ResourcePackManager)resourcePackManager).method_14445(), (Executor)Util.method_27958());
        return inFlight;
    }

    public static void await() {
        try {
            inFlight.join();
        }
        catch (Exception exception) {
        }
    }

    static {
        inFlight = CompletableFuture.completedFuture(null);
    }
}
