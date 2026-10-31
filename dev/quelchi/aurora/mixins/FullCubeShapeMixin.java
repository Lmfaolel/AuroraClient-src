package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.Tweaks;
import net.minecraft.block.Block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Block.class})
public abstract class FullCubeShapeMixin {
    @Inject(method={"method_9614"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$knownShapes(VoxelShape voxelShape, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (!Tweaks.FULL_CUBE) {
            return;
        }
        if (voxelShape == VoxelShapes.method_1077()) {
            callbackInfoReturnable.setReturnValue((Object)true);
        } else if (voxelShape == VoxelShapes.method_1073()) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }
}
