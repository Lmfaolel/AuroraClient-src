package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.Tweaks;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={LanguageOptionsScreen.class})
public abstract class LanguageOptionsScreenMixin {
    @Redirect(method={"method_52184"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_310;method_1521()Ljava/util/concurrent/CompletableFuture;")})
    private CompletableFuture<Void> aurora$reloadLanguageOnly(MinecraftClient minecraftClient) {
        if (!Tweaks.LANGUAGE) {
            return minecraftClient.method_1521();
        }
        minecraftClient.method_1526().method_14491(minecraftClient.method_1478());
        return CompletableFuture.completedFuture(null);
    }
}
