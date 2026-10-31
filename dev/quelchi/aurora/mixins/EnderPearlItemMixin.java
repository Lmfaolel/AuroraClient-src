package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EnderPearlItem.class})
public abstract class EnderPearlItemMixin {
    @Inject(method={"method_7836"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1657;method_5998(Lnet/minecraft/class_1268;)Lnet/minecraft/class_1799;")})
    private void aurora$clientPearl(World world, PlayerEntity playerEntity, Hand hand, CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        ClientWorld clientWorld;
        block3: {
            block2: {
                if (!(world instanceof ClientWorld)) break block2;
                clientWorld = (ClientWorld)world;
                if (ProjectileOptimizer.on()) break block3;
            }
            return;
        }
        ProjectileOptimizer.localCooldownUse(playerEntity, hand);
        ProjectileOptimizer.throwSound(clientWorld, (LivingEntity)playerEntity, SoundEvents.field_14757, SoundCategory.field_15254);
        EnderPearlEntity enderPearlEntity = new EnderPearlEntity(world, (LivingEntity)playerEntity, playerEntity.method_5998(hand));
        enderPearlEntity.method_7432((Entity)playerEntity);
        ProjectileOptimizer.spawn(clientWorld, (ProjectileEntity)enderPearlEntity, (LivingEntity)playerEntity, 0.0f, 1.5f);
    }
}
