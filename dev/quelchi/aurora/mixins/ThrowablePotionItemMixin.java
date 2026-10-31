package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ThrowablePotionItem.class})
public abstract class ThrowablePotionItemMixin {
    @Inject(method={"method_7836"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1657;method_5998(Lnet/minecraft/class_1268;)Lnet/minecraft/class_1799;")})
    private void aurora$clientPotion(World world, PlayerEntity playerEntity, Hand hand, CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        SoundCategory soundCategory;
        SoundEvent soundEvent;
        ClientWorld clientWorld;
        block8: {
            block7: {
                if (!(world instanceof ClientWorld)) break block7;
                clientWorld = (ClientWorld)world;
                if (ProjectileOptimizer.on()) break block8;
            }
            return;
        }
        ThrowablePotionItemMixin throwablePotionItemMixin = this;
        if (throwablePotionItemMixin instanceof SplashPotionItem) {
            soundEvent = SoundEvents.field_14910;
            soundCategory = SoundCategory.field_15248;
        } else if (throwablePotionItemMixin instanceof LingeringPotionItem) {
            soundEvent = SoundEvents.field_14767;
            soundCategory = SoundCategory.field_15254;
        } else {
            return;
        }
        ProjectileOptimizer.throwSound(clientWorld, (LivingEntity)playerEntity, soundEvent, soundCategory);
        Vec3d vec3d = playerEntity.method_33571().method_1023(0.0, 0.1, 0.0);
        ProjectileEntity projectileEntity = ((ThrowablePotionItem)throwablePotionItemMixin).method_58648(world, (Position)vec3d, playerEntity.method_5998(hand), playerEntity.method_5735());
        projectileEntity.method_7432((Entity)playerEntity);
        ProjectileOptimizer.spawn(clientWorld, projectileEntity, (LivingEntity)playerEntity, -20.0f, 0.5f);
    }
}
