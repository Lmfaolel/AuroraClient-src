package dev.quelchi.aurora.popchams;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.PopChamsModule;
import dev.quelchi.aurora.popchams.CapturedPlayer;
import dev.quelchi.aurora.popchams.PositionHandler;
import net.minecraft.util.math.MathHelper;

final class ChamConfigState {
    boolean renderFillModel;
    boolean renderWireframe;
    boolean disperse;
    boolean fadeOut;
    double disperseSpeed;
    double disperseMaxDistance;
    double lifeTime;
    double wireframeThickness;
    int filledColor;
    int wireframeColor;
    float displacementAmount;
    float alpha;

    ChamConfigState() {
    }

    void updateConfigRenderState(PopChamsModule popChamsModule, CapturedPlayer capturedPlayer, float f) {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    this.renderFillModel = popChamsModule.filledModel();
                    boolean bl2 = PositionHandler.a;
                    this.renderWireframe = popChamsModule.wireframe();
                    this.disperse = popChamsModule.disperse();
                    this.disperseSpeed = popChamsModule.disperseSpeed();
                    this.disperseMaxDistance = popChamsModule.disperseMaxDistance();
                    this.filledColor = popChamsModule.filledColor();
                    this.wireframeColor = popChamsModule.wireframeColor();
                    this.fadeOut = popChamsModule.fadeOut();
                    this.lifeTime = popChamsModule.lifeTime();
                    this.wireframeThickness = popChamsModule.wireframeThickness();
                    this.displacementAmount = this.displacement(capturedPlayer, this.alpha);
                    this.alpha = this.alpha(capturedPlayer, f);
                    if (!Module.a) break block4;
                    if (!bl2) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            PositionHandler.a = bl;
        }
    }

    private float displacement(CapturedPlayer capturedPlayer, float f) {
        float f2 = Math.max(0.0f, (float)(capturedPlayer.field_6012 - 1) + f);
        float f3 = (float)MathHelper.method_15350((double)this.disperseSpeed, (double)1.0, (double)10.0);
        float f4 = (float)Math.pow(f3 / 10.0f, 2.0) * 2.0f;
        float f5 = this.disperse ? (float)Math.min((double)(f2 * f4), this.disperseMaxDistance) : 0.0f;
        return f5;
    }

    private float alpha(CapturedPlayer capturedPlayer, float f) {
        float f2;
        block9: {
            block10: {
                boolean bl = PositionHandler.a;
                if (!this.fadeOut) {
                    return 1.0f;
                }
                float f3 = Math.max(0.0f, (float)(capturedPlayer.field_6012 - 1) + f);
                float f4 = (float)(this.lifeTime * (double)0.8f);
                f2 = f3;
                if (bl) break block9;
                if (!(f2 >= f4)) break block10;
                f2 = (float)(1.0 - (double)(f3 - f4) / (this.lifeTime - (double)f4));
                break block9;
            }
            f2 = 1.0f;
        }
        float f5 = f2;
        float f6 = this.disperse ? (float)(1.0 - (double)this.displacementAmount / this.disperseMaxDistance) : 1.0f;
        float f7 = f6;
        return MathHelper.method_15363((float)(f5 * f7), (float)0.0f, (float)1.0f);
    }
}
