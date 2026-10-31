package dev.quelchi.aurora.glass.render;

import dev.quelchi.aurora.glass.render.GlassQuality;
import dev.quelchi.aurora.glass.render.RenderStats;

public final class GlassRenderer {
    private final RenderStats stats = new RenderStats();
    private GlassQuality quality = GlassQuality.FULL_GLASS;
    private boolean backdrop;
    private int mouseX;
    private int mouseY;
    public static int a;

    public void beginFrame(boolean bl, int n, int n2) {
        this.backdrop = bl;
        this.mouseX = n;
        this.mouseY = n2;
        this.stats.reset(GlassQuality.BASIC);
    }

    public RenderStats stats() {
        return this.stats;
    }

    public void invalidate() {
        this.stats.reset(GlassQuality.BASIC);
    }
}
