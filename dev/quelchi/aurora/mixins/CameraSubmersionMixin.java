package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.Tweaks;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Camera.class})
public abstract class CameraSubmersionMixin {
    @Unique
    private CameraSubmersionType aurora$submersion;

    @Inject(method={"method_19321"}, at={@At(value="HEAD")})
    private void aurora$newFrame(CallbackInfo callbackInfo) {
        this.aurora$submersion = null;
    }

    @Inject(method={"method_19337"}, at={@At(value="HEAD")})
    private void aurora$forget(CallbackInfo callbackInfo) {
        this.aurora$submersion = null;
    }

    @Inject(method={"method_19334"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$reuse(CallbackInfoReturnable<CameraSubmersionType> callbackInfoReturnable) {
        if (Tweaks.CAMERA_SUBMERSION && this.aurora$submersion != null) {
            callbackInfoReturnable.setReturnValue((Object)this.aurora$submersion);
        }
    }

    @Inject(method={"method_19334"}, at={@At(value="RETURN")})
    private void aurora$remember(CallbackInfoReturnable<CameraSubmersionType> callbackInfoReturnable) {
        if (Tweaks.CAMERA_SUBMERSION) {
            this.aurora$submersion = (CameraSubmersionType)callbackInfoReturnable.getReturnValue();
        }
    }
}
