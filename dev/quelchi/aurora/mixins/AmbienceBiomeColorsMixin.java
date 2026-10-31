package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.ambience.AmbienceColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.ColorResolver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={BiomeColors.class})
public abstract class AmbienceBiomeColorsMixin {
    @Inject(method={"method_4965"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$overrideBiomeColor(BlockRenderView blockRenderView, BlockPos blockPos, ColorResolver colorResolver, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        int n = AmbienceColors.resolve(blockRenderView, blockPos, colorResolver);
        if (n != -1) {
            callbackInfoReturnable.setReturnValue((Object)n);
        }
    }
}
