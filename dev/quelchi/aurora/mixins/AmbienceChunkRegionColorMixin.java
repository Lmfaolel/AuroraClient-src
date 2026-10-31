package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.ambience.AmbienceColors;
import net.minecraft.client.render.chunk.ChunkRendererRegion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.ColorResolver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ChunkRendererRegion.class})
public abstract class AmbienceChunkRegionColorMixin {
    @Inject(method={"method_23752"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$overrideRegionColor(BlockPos blockPos, ColorResolver colorResolver, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        int n = AmbienceColors.resolve((BlockRenderView)((ChunkRendererRegion)this), blockPos, colorResolver);
        if (n != -1) {
            callbackInfoReturnable.setReturnValue((Object)n);
        }
    }
}
