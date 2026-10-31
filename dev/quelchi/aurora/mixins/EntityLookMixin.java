package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.input.FreeLook;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Entity.class})
public abstract class EntityLookMixin {
    @Inject(method={"method_5872(DD)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$freeLook(double d, double d2, CallbackInfo callbackInfo) {
        if (FreeLook.owns(this)) {
            FreeLook.look(d, d2);
            callbackInfo.cancel();
        }
    }
}
