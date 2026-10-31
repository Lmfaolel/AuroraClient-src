package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.CleanViewModule;
import dev.quelchi.aurora.render.EffectEngine;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Entity.class})
public abstract class EntityMixin {
    @Inject(method={"method_5839"}, at={@At(value="HEAD")}, cancellable=true)
    private void divine$hideSprintDust(CallbackInfo callbackInfo) {
        CleanViewModule cleanViewModule = CleanViewModule.get();
        if (cleanViewModule != null && cleanViewModule.hideSprintDust()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_22861"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$effectOutlineColor(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        int n = EffectEngine.plainOutline((Entity)this);
        if (n != 0) {
            callbackInfoReturnable.setReturnValue((Object)n);
        }
    }
}
