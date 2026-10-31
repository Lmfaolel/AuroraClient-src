package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.gui.UpdatePrompt;
import dev.quelchi.aurora.hud.InventoryScale;
import dev.quelchi.aurora.input.ClickTracker;
import dev.quelchi.aurora.input.RawMouse;
import dev.quelchi.aurora.module.modules.ZoomModule;
import dev.quelchi.aurora.prediction.ac.ClickAudit;
import dev.quelchi.aurora.prediction.ac.InputAudit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.Click;
import net.minecraft.client.input.MouseInput;
import org.joml.Vector2i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Mouse.class})
public abstract class MouseMixin {
    @Shadow
    private double field_1789;
    @Shadow
    private double field_1787;

    @Inject(method={"method_1606(D)V"}, at={@At(value="HEAD")})
    private void aurora$mouseTrackerFeed(double d, CallbackInfo callbackInfo) {
        RawMouse.moved(this.field_1789, this.field_1787);
    }

    @Inject(method={"method_1601(JLnet/minecraft/class_11910;I)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void divine$onMouseButton(long l, MouseInput mouseInput, int n, CallbackInfo callbackInfo) {
        InputAudit.onMouse(l, mouseInput.comp_4801(), n);
        if (n == 1) {
            double d;
            double d2;
            ClickTracker.onPress(mouseInput.comp_4801());
            ClickAudit.onPress(mouseInput.comp_4801());
            MinecraftClient minecraftClient = MinecraftClient.method_1551();
            if (minecraftClient.field_1755 != null && mouseInput.comp_4801() == 0 && (UpdatePrompt.click(d2 = minecraftClient.field_1729.method_68879(minecraftClient.method_22683()), d = minecraftClient.field_1729.method_68883(minecraftClient.method_22683())) || SocialToast.click(d2, d))) {
                callbackInfo.cancel();
            }
        }
    }

    @ModifyArg(method={"method_1601(JLnet/minecraft/class_11910;I)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_437;method_25402(Lnet/minecraft/class_11909;Z)Z")}, index=0)
    private Click aurora$scaleClicked(Click click) {
        return InventoryScale.applies() ? InventoryScale.toVirtual(click) : click;
    }

    @ModifyArg(method={"method_1601(JLnet/minecraft/class_11910;I)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_437;method_25406(Lnet/minecraft/class_11909;)Z")}, index=0)
    private Click aurora$scaleReleased(Click click) {
        return InventoryScale.applies() ? InventoryScale.toVirtual(click) : click;
    }

    @ModifyArg(method={"method_55793()V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_437;method_25403(Lnet/minecraft/class_11909;DD)Z")}, index=0)
    private Click aurora$scaleDragged(Click click) {
        return InventoryScale.applies() ? InventoryScale.toVirtual(click) : click;
    }

    @ModifyArg(method={"method_55793()V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_437;method_25403(Lnet/minecraft/class_11909;DD)Z")}, index=1)
    private double aurora$scaleDragDx(double d) {
        return InventoryScale.applies() ? InventoryScale.toVirtual(d) : d;
    }

    @ModifyArg(method={"method_55793()V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_437;method_25403(Lnet/minecraft/class_11909;DD)Z")}, index=2)
    private double aurora$scaleDragDy(double d) {
        return InventoryScale.applies() ? InventoryScale.toVirtual(d) : d;
    }

    @ModifyArg(method={"method_55793()V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_437;method_16014(DD)V")}, index=0)
    private double aurora$scaleMovedX(double d) {
        return InventoryScale.applies() ? InventoryScale.toVirtual(d) : d;
    }

    @ModifyArg(method={"method_55793()V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_437;method_16014(DD)V")}, index=1)
    private double aurora$scaleMovedY(double d) {
        return InventoryScale.applies() ? InventoryScale.toVirtual(d) : d;
    }

    @Inject(method={"method_1598(JDD)V"}, at={@At(value="HEAD")})
    private void aurora$auditScroll(long l, double d, double d2, CallbackInfo callbackInfo) {
        InputAudit.onScroll();
    }

    @Inject(method={"method_1598(JDD)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_746;method_7325()Z")}, cancellable=true)
    private void aurora$scrollZoom(long l, double d, double d2, CallbackInfo callbackInfo, @Local Vector2i vector2i) {
        ZoomModule zoomModule = ZoomModule.get();
        if (zoomModule != null && zoomModule.scrollCaptures() && vector2i.y != 0) {
            zoomModule.mouseZoom(vector2i.y);
            callbackInfo.cancel();
        }
    }

    @ModifyExpressionValue(method={"method_1606(D)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_7172;method_41753()Ljava/lang/Object;", ordinal=0)})
    private Object aurora$relativeSensitivity(Object object) {
        ZoomModule zoomModule = ZoomModule.get();
        if (zoomModule == null || !zoomModule.isEnabled()) {
            return object;
        }
        return (Double)object / zoomModule.sensitivityDivisor();
    }

    @ModifyExpressionValue(method={"method_1606(D)V"}, at={@At(value="FIELD", target="Lnet/minecraft/class_315;field_1914:Z")})
    private boolean aurora$cinematicZoom(boolean bl) {
        ZoomModule zoomModule = ZoomModule.get();
        return bl || zoomModule != null && zoomModule.cinematicActive();
    }

    @ModifyArg(method={"method_1606(D)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_3540;method_15429(DD)D")}, index=1)
    private double aurora$cinematicSmoothness(double d) {
        ZoomModule zoomModule = ZoomModule.get();
        if (zoomModule != null && zoomModule.cinematicActive()) {
            return d / zoomModule.cinematicFactor();
        }
        return d;
    }

    @ModifyArg(method={"method_1598(JDD)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_437;method_25401(DDDD)Z")}, index=0)
    private double aurora$scaleScrollX(double d) {
        return InventoryScale.applies() ? InventoryScale.toVirtual(d) : d;
    }

    @ModifyArg(method={"method_1598(JDD)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_437;method_25401(DDDD)Z")}, index=1)
    private double aurora$scaleScrollY(double d) {
        return InventoryScale.applies() ? InventoryScale.toVirtual(d) : d;
    }
}
