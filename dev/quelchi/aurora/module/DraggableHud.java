package dev.quelchi.aurora.module;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.HudScales;

public interface DraggableHud {
    public int getHudX();

    public int getHudY();

    public void setHudPos(int var1, int var2);

    public int[] getLastBounds();

    default public float getHudScale() {
        return HudScales.get(this.hudKey());
    }

    default public void setHudScale(float f) {
        HudScales.set(this.hudKey(), f);
    }

    default public void resetHudPos() {
        block4: {
            block5: {
                int n = Category.a;
                int[] nArray = HudScales.home(this.hudKey());
                if (n != 0) break block4;
                if (nArray == null) break block5;
                this.setHudPos(nArray[0], nArray[1]);
            }
            this.setHudScale(1.0f);
            this.setHudHidden(false);
        }
    }

    default public boolean hudHidden() {
        return HudScales.hidden(this.hudKey());
    }

    default public void setHudHidden(boolean bl) {
        HudScales.setHidden(this.hudKey(), bl);
    }

    default public void rememberHome() {
        HudScales.rememberHome(this.hudKey(), this.getHudX(), this.getHudY());
    }

    default public boolean hudScalable() {
        return true;
    }

    default public boolean hudInGuiSpace() {
        return false;
    }

    default public String hudKey() {
        return this.getClass().getSimpleName();
    }

    default public boolean scalesItself() {
        return false;
    }
}
