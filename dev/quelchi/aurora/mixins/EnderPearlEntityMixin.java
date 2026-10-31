package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import dev.quelchi.aurora.prediction.Prediction;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EnderPearlEntity.class})
public abstract class EnderPearlEntityMixin
extends ThrownItemEntity {
    protected EnderPearlEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method={"method_7488"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$clientPearlCollision(HitResult hitResult, CallbackInfo callbackInfo) {
        if (this.method_73183() instanceof ClientWorld && ProjectileOptimizer.on()) {
            if (ProjectileOptimizer.buffered(this.method_5628())) {
                Prediction.onLocalPearlLanded(this.method_61411());
            }
            ProjectileOptimizer.removeIfBuffered(this.method_5628());
            callbackInfo.cancel();
        }
    }
}
