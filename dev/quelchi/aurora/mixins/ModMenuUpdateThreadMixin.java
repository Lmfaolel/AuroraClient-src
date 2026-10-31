package dev.quelchi.aurora.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets={"com/terraformersmc/modmenu/util/UpdateCheckerThreadFactory"}, remap=false)
public abstract class ModMenuUpdateThreadMixin {
    @Inject(method={"newThread"}, at={@At(value="HEAD")}, cancellable=true, require=0, remap=false)
    private void aurora$platformThread(Runnable runnable, CallbackInfoReturnable<Thread> callbackInfoReturnable) {
        Thread thread = new Thread(runnable, "ModMenu/Update Checker");
        thread.setDaemon(true);
        callbackInfoReturnable.setReturnValue((Object)thread);
    }
}
