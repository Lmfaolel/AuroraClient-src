package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ExperienceBottleItem.class})
public abstract class ExperienceBottleItemMixin {
    @Inject(method={"method_7836"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1657;method_5998(Lnet/minecraft/class_1268;)Lnet/minecraft/class_1799;")})
    private void aurora$clientBottle(World world, PlayerEntity playerEntity, Hand hand, CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        ClientWorld clientWorld;
        block3: {
            block2: {
                if (!(world instanceof ClientWorld)) break block2;
                clientWorld = (ClientWorld)world;
                if (ProjectileOptimizer.on()) break block3;
            }
            return;
        }
        ProjectileOptimizer.throwSound(clientWorld, (LivingEntity)playerEntity, SoundEvents.field_14637, SoundCategory.field_15254);
        ExperienceBottleEntity experienceBottleEntity = new ExperienceBottleEntity(world, (LivingEntity)playerEntity, playerEntity.method_5998(hand));
        ProjectileOptimizer.spawn(clientWorld, (ProjectileEntity)experienceBottleEntity, (LivingEntity)playerEntity, -20.0f, 0.7f);
    }
}
