package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.duck.PackScanner;
import dev.quelchi.aurora.perf.PackScan;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.resource.ResourcePackManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ResourcePackOrganizer.class})
public abstract class ResourcePackOrganizerMixin
implements PackScanner {
    @Shadow
    @Final
    private ResourcePackManager field_25626;

    @Override
    public ResourcePackManager aurora$manager() {
        return this.field_25626;
    }

    @Redirect(method={"method_29981"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_3283;method_14445()V")})
    private void aurora$scanAlreadyMade(ResourcePackManager resourcePackManager) {
        if (!PackScan.skip) {
            resourcePackManager.method_14445();
        }
    }

    @Inject(method={"method_29642"}, at={@At(value="HEAD")})
    private void aurora$letTheScanLand(CallbackInfo callbackInfo) {
        PackScan.await();
    }
}
