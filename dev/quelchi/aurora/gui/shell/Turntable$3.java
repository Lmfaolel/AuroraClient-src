package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.gui.Mannequin;
import dev.quelchi.aurora.gui.Mannequin$Act;
import dev.quelchi.aurora.gui.PreviewScene$Actor;
import dev.quelchi.aurora.gui.PreviewScene$Geometry;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Effect;
import dev.quelchi.aurora.render.LocalSpeckSink;
import java.util.List;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Arm;
import net.minecraft.util.SwingAnimationType;

class Turntable$3
implements Mannequin$Act {
    final float val$since;
    final EffectEngine$Effect val$e;
    final OtherClientPlayerEntity val$att;
    final float val$dx;
    final float val$dz;
    final float val$swing;
    final float val$lift;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    Turntable$3() {
        void var8_-1;
        void var7_-1;
        void var6_-1;
        void var5_-1;
        void var4_-1;
        void var3_-1;
        void var2_-1;
        this.val$since = var2_-1;
        this.val$e = var3_-1;
        this.val$att = var4_-1;
        this.val$dx = var5_-1;
        this.val$dz = var6_-1;
        this.val$swing = var7_-1;
        this.val$lift = var8_-1;
    }

    @Override
    public void pose(LivingEntityRenderState livingEntityRenderState) {
        float f;
        block8: {
            block7: {
                float f2;
                float f3;
                boolean bl;
                block6: {
                    bl = SearchIndex.a;
                    LivingEntityRenderState livingEntityRenderState2 = livingEntityRenderState;
                    f3 = this.val$since;
                    f2 = 0.0f;
                    if (bl) break block6;
                    if (!(f3 >= f2)) break block7;
                    f3 = this.val$since;
                    f2 = 0.45f;
                }
                float f4 = f3 - f2;
                f = f4 == 0.0f ? 0 : (f4 < 0.0f ? -1 : 1);
                if (bl) break block8;
                if (f >= 0) break block7;
                f = 1.0f;
                break block8;
            }
            f = 0.0f;
        }
        livingEntityRenderState2.field_53460 = f;
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
                EffectEngine.worldPreview(this.val$e, 1.9f, this.val$since, localSpeckSink);
            }
        }
    }

    @Override
    public PreviewScene$Geometry geometry() {
        return EffectEngine.stage(this.val$e, 1.9f, this.val$since);
    }

    @Override
    public List<PreviewScene$Actor> actors() {
        EntityRenderState entityRenderState;
        block11: {
            EntityRenderState entityRenderState2;
            block10: {
                Turntable$3 turntable$3;
                boolean bl;
                block8: {
                    block9: {
                        bl = SearchIndex.a;
                        turntable$3 = this;
                        if (bl) break block8;
                        if (turntable$3.val$att != null) break block9;
                        return List.of();
                    }
                    turntable$3 = this;
                }
                float f = (float)Math.toDegrees(Math.atan2(-turntable$3.val$dx, -this.val$dz));
                entityRenderState = Mannequin.actor((AbstractClientPlayerEntity)this.val$att, 180.0f - f);
                entityRenderState2 = entityRenderState;
                if (bl) break block10;
                if (!(entityRenderState2 instanceof BipedEntityRenderState)) break block11;
                entityRenderState2 = entityRenderState;
            }
            BipedEntityRenderState bipedEntityRenderState = (BipedEntityRenderState)entityRenderState2;
            bipedEntityRenderState.field_53408 = Arm.field_6183;
            bipedEntityRenderState.field_55303 = Arm.field_6183;
            bipedEntityRenderState.field_63603 = SwingAnimationType.field_63399;
            bipedEntityRenderState.field_63604 = this.val$swing;
            bipedEntityRenderState.field_55304 = BipedEntityModel.ArmPose.field_3410;
        }
        return List.of(new PreviewScene$Actor(entityRenderState, 0.55f, this.val$lift, -1.25f));
    }
}
