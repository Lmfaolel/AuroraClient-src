package dev.quelchi.aurora.glass.render;

import dev.quelchi.aurora.glass.render.GlassQuality;
import dev.quelchi.aurora.glass.render.GlassRenderer;
import dev.quelchi.aurora.module.Module;

public final class RenderStats {
    private int surfaces;
    private int fills;
    private int blurPasses;
    private int captures;
    private int targets;
    private int clipped;
    private int shaderSurfaces;
    private int fallbackSurfaces;
    private GlassQuality quality = GlassQuality.BASIC;

    void reset(GlassQuality glassQuality) {
        this.fallbackSurfaces = 0;
        this.shaderSurfaces = 0;
        this.clipped = 0;
        this.targets = 0;
        this.captures = 0;
        this.blurPasses = 0;
        this.fills = 0;
        this.surfaces = 0;
        int n = GlassRenderer.a;
        this.quality = glassQuality;
        if (Module.a) {
            GlassRenderer.a = ++n;
        }
    }

    public void clip() {
        ++this.clipped;
    }

    public void prepared(GlassQuality glassQuality, int n, int n2, int n3) {
        block21: {
            block19: {
                RenderStats renderStats;
                block20: {
                    int n4;
                    block16: {
                        RenderStats renderStats2;
                        block14: {
                            block15: {
                                boolean bl;
                                block18: {
                                    block17: {
                                        n4 = GlassRenderer.a;
                                        renderStats2 = this;
                                        if (n4 != 0) break block14;
                                        renderStats2.captures = n;
                                        this.blurPasses = n2;
                                        this.targets = n3;
                                        if (glassQuality != GlassQuality.BASIC) break block15;
                                        ++this.fallbackSurfaces;
                                        if (n4 == 0) break block16;
                                        if (!Module.a) break block17;
                                        bl = false;
                                        break block18;
                                    }
                                    bl = true;
                                }
                                Module.a = bl;
                            }
                            renderStats2 = this;
                        }
                        ++renderStats2.shaderSurfaces;
                    }
                    renderStats = this;
                    if (n4 != 0) break block19;
                    if (renderStats.quality == GlassQuality.BASIC) break block20;
                    if (glassQuality.ordinal() >= this.quality.ordinal()) break block21;
                }
                renderStats = this;
            }
            renderStats.quality = glassQuality;
        }
    }
}
