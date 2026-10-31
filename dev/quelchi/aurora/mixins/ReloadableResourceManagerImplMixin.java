package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.cobweb.CobwebTextureResolver;
import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ReloadableResourceManagerImpl.class})
public class ReloadableResourceManagerImplMixin {
    @Inject(method={"method_18232"}, at={@At(value="HEAD")})
    private void divine$onReloadStart(CallbackInfoReturnable<ResourceReload> callbackInfoReturnable) {
        CobwebTextureResolver.beginReload((ResourceManager)this);
    }
}
