package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.ambience.AmbienceColors;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.ColorResolver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ClientWorld.class})
public abstract class AmbienceClientWorldColorMixin {
    @Inject(method={"method_23752"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$overrideWorldColor(BlockPos blockPos, ColorResolver colorResolver, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        int n = AmbienceColors.resolve((BlockRenderView)((ClientWorld)this), blockPos, colorResolver);
        if (n != -1) {
            callbackInfoReturnable.setReturnValue((Object)n);
        }
    }
}
