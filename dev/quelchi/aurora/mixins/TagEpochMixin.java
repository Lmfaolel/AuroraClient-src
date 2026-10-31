package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.PathTypes;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RegistryEntry.Reference.class})
public abstract class TagEpochMixin {
    @Inject(method={"method_40235"}, at={@At(value="HEAD")})
    private void aurora$tagsChanged(CallbackInfo callbackInfo) {
        ++PathTypes.epoch;
    }
}
