package dev.quelchi.aurora.mixins;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dev.quelchi.aurora.perf.Tweaks;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.network.Address;
import net.minecraft.client.network.AllowedAddressResolver;
import net.minecraft.client.network.ServerAddress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={AllowedAddressResolver.class})
public abstract class AllowedAddressResolverMixin {
    @Unique
    private static final Cache<ServerAddress, Address> aurora$resolved = CacheBuilder.newBuilder().expireAfterWrite(30L, TimeUnit.SECONDS).build();

    @Inject(method={"method_36907"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$reuseLookup(ServerAddress serverAddress, CallbackInfoReturnable<Optional<Address>> callbackInfoReturnable) {
        if (!Tweaks.SERVER_PINGS) {
            return;
        }
        Address address = (Address)aurora$resolved.getIfPresent((Object)serverAddress);
        if (address != null) {
            callbackInfoReturnable.setReturnValue(Optional.of(address));
        }
    }

    @Inject(method={"method_36907"}, at={@At(value="RETURN")})
    private void aurora$keepLookup(ServerAddress serverAddress, CallbackInfoReturnable<Optional<Address>> callbackInfoReturnable) {
        if (!Tweaks.SERVER_PINGS) {
            return;
        }
        Optional optional = (Optional)callbackInfoReturnable.getReturnValue();
        if (optional.isPresent() && aurora$resolved.getIfPresent((Object)serverAddress) == null) {
            aurora$resolved.put((Object)serverAddress, (Object)((Address)optional.get()));
        }
    }
}
