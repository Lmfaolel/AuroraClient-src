package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.hud.InventoryScale;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Screen.class})
public abstract class ScreenScaleMixin {
    private boolean aurora$scaled() {
        return this instanceof HandledScreen && InventoryScale.factor() != 1.0f;
    }

    @ModifyVariable(method={"method_25423(II)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=0)
    private int aurora$initWidth(int n) {
        return this.aurora$scaled() ? InventoryScale.toVirtualSize(n) : n;
    }

    @ModifyVariable(method={"method_25423(II)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=1)
    private int aurora$initHeight(int n) {
        return this.aurora$scaled() ? InventoryScale.toVirtualSize(n) : n;
    }

    @ModifyVariable(method={"method_25410(II)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=0)
    private int aurora$resizeWidth(int n) {
        return this.aurora$scaled() ? InventoryScale.toVirtualSize(n) : n;
    }

    @ModifyVariable(method={"method_25410(II)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=1)
    private int aurora$resizeHeight(int n) {
        return this.aurora$scaled() ? InventoryScale.toVirtualSize(n) : n;
    }

    @ModifyVariable(method={"method_47413"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=0)
    private int aurora$renderMouseX(int n) {
        return this.aurora$scaled() ? Math.round((float)InventoryScale.toVirtual(n)) : n;
    }

    @ModifyVariable(method={"method_47413"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=1)
    private int aurora$renderMouseY(int n) {
        return this.aurora$scaled() ? Math.round((float)InventoryScale.toVirtual(n)) : n;
    }

    @Inject(method={"method_47413"}, at={@At(value="HEAD")})
    private void aurora$pushScale(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        if (this instanceof HandledScreen) {
            InventoryScale.setContainerRendering(true);
        }
        if (!this.aurora$scaled()) {
            return;
        }
        float f2 = InventoryScale.factor();
        drawContext.method_51448().pushMatrix();
        drawContext.method_51448().scale(f2, f2);
        InventoryScale.setRendering(true);
    }

    @Inject(method={"method_47413"}, at={@At(value="RETURN")})
    private void aurora$popScale(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        InventoryScale.setContainerRendering(false);
        if (!InventoryScale.isRendering()) {
            return;
        }
        InventoryScale.setRendering(false);
        drawContext.method_51448().popMatrix();
    }
}
