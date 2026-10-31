package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.optimize.KeptCrystals;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value={World.class})
public abstract class KeptCrystalsWorldMixin {
    @ModifyVariable(method={"method_8333(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;"}, at={@At(value="HEAD")}, argsOnly=true)
    private Predicate<? super Entity> aurora$hideKeptCrystals(Predicate<? super Entity> predicate) {
        return ((World)this).method_8608() ? KeptCrystals.hide(predicate) : predicate;
    }
}
