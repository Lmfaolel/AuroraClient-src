package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import dev.quelchi.aurora.duck.PathTyped;
import dev.quelchi.aurora.perf.PathTypes;
import dev.quelchi.aurora.perf.PathTypes$Entry;
import dev.quelchi.aurora.perf.Tweaks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={LandPathNodeMaker.class})
public abstract class PathTypeMixin {
    @Inject(method={"method_58"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$knownState(BlockView blockView, BlockPos blockPos, CallbackInfoReturnable<PathNodeType> callbackInfoReturnable) {
        if (!Tweaks.PATH_TYPES) {
            return;
        }
        PathTypes$Entry pathTypes$Entry = ((PathTyped)blockView.method_8320(blockPos)).aurora$pathType();
        if (pathTypes$Entry != null && pathTypes$Entry.epoch() == PathTypes.epoch) {
            callbackInfoReturnable.setReturnValue((Object)pathTypes$Entry.type());
        }
    }

    @Inject(method={"method_58"}, at={@At(value="RETURN")})
    private static void aurora$remember(BlockView blockView, BlockPos blockPos, CallbackInfoReturnable<PathNodeType> callbackInfoReturnable, @Local BlockState blockState) {
        if (!Tweaks.PATH_TYPES) {
            return;
        }
        ((PathTyped)blockState).aurora$pathType(new PathTypes$Entry((PathNodeType)callbackInfoReturnable.getReturnValue(), PathTypes.epoch));
    }
}
