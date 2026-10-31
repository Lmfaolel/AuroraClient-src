package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.render.EffectEngine;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={MinecraftClient.class})
public abstract class EffectOutlineMixin {
    @Inject(method={"method_27022"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$effectOutline(Entity entity, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (EffectEngine.plainOutline(entity) != 0) {
            callbackInfoReturnable.setReturnValue((Object)true);
        }
    }
}
