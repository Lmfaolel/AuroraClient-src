package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.optimize.AnchorPrediction;
import dev.quelchi.aurora.optimize.KeptCrystals;
import dev.quelchi.aurora.prediction.HolePredictor;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.PendingUpdateManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ClientWorld.class})
public abstract class ClientWorldMixin {
    @Shadow
    @Final
    private PendingUpdateManager field_37951;

    @Inject(method={"method_30092(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;II)Z"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$holdAnchorPrediction(BlockPos blockPos, BlockState blockState, int n, int n2, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (blockState.method_26215()) {
            return;
        }
        if (AnchorPrediction.isSuppressed(blockPos)) {
            callbackInfoReturnable.setReturnValue((Object)true);
        }
    }

    @Inject(method={"method_41928(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;I)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$holdPredictedHole(BlockPos blockPos, BlockState blockState, int n, CallbackInfo callbackInfo) {
        if (!HolePredictor.serverWrite(blockPos, blockState)) {
            return;
        }
        this.field_37951.method_41940(blockPos, blockState);
        callbackInfo.cancel();
    }

    @Inject(method={"method_41926(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_243;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$holdPredictedHoleAck(BlockPos blockPos, BlockState blockState, Vec3d vec3d, CallbackInfo callbackInfo) {
        if (HolePredictor.serverWrite(blockPos, blockState)) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_2945(ILnet/minecraft/class_1297$class_5529;)V"}, at={@At(value="HEAD")})
    private void aurora$confirmKeptCrystal(int n, Entity.RemovalReason removalReason, CallbackInfo callbackInfo) {
        KeptCrystals.confirmRemoval(((ClientWorld)this).method_8469(n));
    }
}
