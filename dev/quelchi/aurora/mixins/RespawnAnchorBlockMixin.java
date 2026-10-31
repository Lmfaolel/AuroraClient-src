package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.optimize.AnchorOptimizer;
import net.minecraft.block.BlockState;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={RespawnAnchorBlock.class})
public class RespawnAnchorBlockMixin {
    @Inject(method={"method_55766(Lnet/minecraft/class_2680;Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_1657;Lnet/minecraft/class_3965;)Lnet/minecraft/class_1269;"}, at={@At(value="HEAD")})
    private void aurora$anchorDetonated(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, BlockHitResult blockHitResult, CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        AnchorOptimizer.onDetonate(world, blockPos, blockState);
    }

    @Inject(method={"method_55765(Lnet/minecraft/class_1799;Lnet/minecraft/class_2680;Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_1657;Lnet/minecraft/class_1268;Lnet/minecraft/class_3965;)Lnet/minecraft/class_1269;"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_4969;method_26382(Lnet/minecraft/class_1297;Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)V")})
    private void aurora$anchorCharged(ItemStack itemStack, BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult, CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        AnchorOptimizer.onCharge(blockPos, MinecraftClient.method_1551().field_1687);
    }
}
