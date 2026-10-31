package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.prediction.GlidePrediction;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={FireworkRocketItem.class})
public abstract class FireworkRocketItemMixin {
    @Inject(method={"method_7836(Lnet/minecraft/class_1937;Lnet/minecraft/class_1657;Lnet/minecraft/class_1268;)Lnet/minecraft/class_1269;"}, at={@At(value="HEAD")})
    private void aurora$clientRocket(World world, PlayerEntity playerEntity, Hand hand, CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        if (world instanceof ClientWorld) {
            ClientWorld clientWorld = (ClientWorld)world;
            GlidePrediction.onRocketUse(clientWorld, playerEntity, hand);
        }
    }
}
