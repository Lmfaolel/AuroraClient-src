package dev.quelchi.aurora.mixins;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import net.minecraft.component.type.ProfileComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={ProfileComponent.Dynamic.class})
public abstract class ProfileResolveMixin {
    @Unique
    private static final Executor RESOLVE = Executors.newFixedThreadPool(8, ProfileResolveMixin::lambda$static$0);

    @ModifyArg(method={"method_73306"}, at={@At(value="INVOKE", target="Ljava/util/concurrent/CompletableFuture;supplyAsync(Ljava/util/function/Supplier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;")}, index=1)
    private Executor aurora$fewAtOnce(Executor executor) {
        return RESOLVE;
    }

    private static Thread lambda$static$0(Runnable runnable) {
        Thread thread = new Thread(runnable, "Aurora-ProfileResolve");
        thread.setDaemon(true);
        return thread;
    }
}
