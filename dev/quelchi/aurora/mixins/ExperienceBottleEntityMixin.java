package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ExperienceBottleEntity.class})
public abstract class ExperienceBottleEntityMixin
extends ThrownItemEntity {
    protected ExperienceBottleEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method={"method_7488"}, at={@At(value="HEAD")})
    private void aurora$clientShatter(HitResult hitResult, CallbackInfo callbackInfo) {
        World world = this.method_73183();
        if (!(world instanceof ClientWorld)) {
            return;
        }
        ClientWorld clientWorld = (ClientWorld)world;
        if (!ProjectileOptimizer.buffered(this.method_5628())) {
            return;
        }
        ProjectileOptimizer.worldEvent(clientWorld, 2002, this.method_24515(), -13083194);
        ProjectileOptimizer.remove(this.method_5628());
    }
}
