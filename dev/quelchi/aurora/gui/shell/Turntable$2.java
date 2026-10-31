package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.gui.Mannequin$Act;
import dev.quelchi.aurora.gui.PreviewScene$Geometry;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Effect;
import dev.quelchi.aurora.render.LocalSpeckSink;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

class Turntable$2
implements Mannequin$Act {
    final float val$since;
    final EffectEngine$Effect val$e;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    Turntable$2() {
        void var3_-1;
        void var2_-1;
        this.val$since = var2_-1;
        this.val$e = var3_-1;
    }

    @Override
    public void pose(LivingEntityRenderState livingEntityRenderState) {
        if (this.val$since < 0.0f) {
            return;
        }
        livingEntityRenderState.field_53449 = Math.min(20.0f, this.val$since * 20.0f + 1.0f);
        livingEntityRenderState.field_53460 = true;
    }

    @Override
    public boolean visible() {
        float f;
        block2: {
            block3: {
                boolean bl = SearchIndex.a;
                float f2 = this.val$since - Math.min(1.1f, EffectEngine.hideAt(this.val$e));
                f = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
                if (bl) break block2;
                if (f >= 0) break block3;
                f = 1.0f;
                break block2;
            }
            f = 0.0f;
        }
        return (boolean)f;
    }

    @Override
    public void specks(LocalSpeckSink localSpeckSink, boolean bl) {
        block6: {
            float f;
            block5: {
                boolean bl2 = SearchIndex.a;
                float f2 = this.val$since - 0.0f;
                f = f2 == 0.0f ? 0 : (f2 > 0.0f ? 1 : -1);
                if (bl2) break block5;
                if (f < 0) break block6;
                f = (float)bl;
            }
            if (f == false) {
                EffectEngine.worldPreview(this.val$e, 1.0f, this.val$since, localSpeckSink);
            }
        }
    }

    @Override
    public PreviewScene$Geometry geometry() {
        return EffectEngine.stage(this.val$e, 1.0f, this.val$since);
    }
}
