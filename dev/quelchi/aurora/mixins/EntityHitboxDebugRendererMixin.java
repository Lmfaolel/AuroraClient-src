package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.HitboxesModule;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.debug.EntityHitboxDebugRenderer;
import net.minecraft.world.debug.DebugDataStore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntityHitboxDebugRenderer.class})
public abstract class EntityHitboxDebugRendererMixin {
    @Inject(method={"method_23109"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$replaceVanillaHitboxes(double d, double d2, double d3, DebugDataStore debugDataStore, Frustum frustum, float f, CallbackInfo callbackInfo) {
        HitboxesModule hitboxesModule = HitboxesModule.get();
        if (hitboxesModule != null && hitboxesModule.isEnabled()) {
            callbackInfo.cancel();
        }
    }
}
