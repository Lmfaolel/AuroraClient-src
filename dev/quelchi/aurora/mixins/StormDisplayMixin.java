package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.render.StormRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.DisplayEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={DisplayEntity.class})
public abstract class StormDisplayMixin {
    @Inject(method={"method_5640(D)Z"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$hideStorm(double d, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (StormRenderer.hides((Entity)((DisplayEntity)this))) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }
}
