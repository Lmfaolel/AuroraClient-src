package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={ClientWorld.class})
public abstract class DisplayTickMixin {
    @Unique
    private static final Random aurora$tickRandom = Random.method_43053();

    @WrapOperation(method={"method_2943(IIIILnet/minecraft/class_5819;Lnet/minecraft/class_2248;Lnet/minecraft/class_2338$class_2339;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_5819;method_43048(I)I")})
    private int aurora$localRandom(Random random, int n, Operation<Integer> operation) {
        return aurora$tickRandom.method_43048(n);
    }

    @WrapOperation(method={"method_2943(IIIILnet/minecraft/class_5819;Lnet/minecraft/class_2248;Lnet/minecraft/class_2338$class_2339;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_638;method_8316(Lnet/minecraft/class_2338;)Lnet/minecraft/class_3610;")})
    private FluidState aurora$fluidFromState(ClientWorld clientWorld, BlockPos blockPos, Operation<FluidState> operation, @Local(ordinal=0) BlockState blockState) {
        return blockState.method_26227();
    }
}
