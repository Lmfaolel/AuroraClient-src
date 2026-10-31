package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.cosmetics.content.Trails$Trail;
import dev.quelchi.aurora.gui.Mannequin$Act;
import dev.quelchi.aurora.gui.PreviewScene$Geometry;
import dev.quelchi.aurora.render.LocalSpeckSink;
import dev.quelchi.aurora.render.TrailRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

class Turntable$1
implements Mannequin$Act {
    final float val$time;
    final Trails.Trail val$t;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    Turntable$1() {
        void var3_-1;
        void var2_-1;
        this.val$time = var2_-1;
        this.val$t = var3_-1;
    }

    @Override
    public void pose(LivingEntityRenderState livingEntityRenderState) {
        livingEntityRenderState.field_53450 = this.val$time * 9.2f;
        livingEntityRenderState.field_53451 = 0.72f;
    }

    @Override
    public void specks(LocalSpeckSink localSpeckSink, boolean bl) {
        if (!bl) {
            TrailRenderer.modelPreview(this.val$t, this.val$time, localSpeckSink);
        }
    }

    @Override
    public PreviewScene$Geometry geometry() {
        return TrailRenderer.preview(this.val$t, this.val$time);
    }
}
