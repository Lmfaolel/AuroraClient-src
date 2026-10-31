package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={AbstractWindChargeEntity.class})
public abstract class AbstractWindChargeEntityMixin
extends ExplosiveProjectileEntity {
    protected AbstractWindChargeEntityMixin(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method={"method_7488"}, at={@At(value="HEAD")})
    private void aurora$clientBurst(HitResult hitResult, CallbackInfo callbackInfo) {
        World world = this.method_73183();
        if (world instanceof ClientWorld) {
            ClientWorld clientWorld = (ClientWorld)world;
            if (ProjectileOptimizer.on()) {
                this.aurora$explode(clientWorld, hitResult.method_17784());
            }
        }
    }

    @Inject(method={"method_7454"}, at={@At(value="HEAD")})
    private void aurora$clientBurstOnEntity(EntityHitResult entityHitResult, CallbackInfo callbackInfo) {
        World world = this.method_73183();
        if (world instanceof ClientWorld) {
            ClientWorld clientWorld = (ClientWorld)world;
            if (ProjectileOptimizer.on()) {
                this.aurora$explode(clientWorld, entityHitResult.method_17784());
            }
        }
    }

    @Inject(method={"method_24920"}, at={@At(value="HEAD")})
    private void aurora$clientBurstOnBlock(BlockHitResult blockHitResult, CallbackInfo callbackInfo) {
        if (this.method_73183() instanceof ClientWorld) {
            ProjectileOptimizer.removeIfBuffered(this.method_5628());
        }
    }

    @Unique
    private void aurora$explode(ClientWorld clientWorld, Vec3d vec3d) {
        ProjectileOptimizer.explosion(vec3d.method_10216(), vec3d.method_10214(), vec3d.method_10215());
        clientWorld.method_8486(vec3d.method_10216(), vec3d.method_10214(), vec3d.method_10215(), (SoundEvent)SoundEvents.field_49044.comp_349(), SoundCategory.field_15245, 4.0f, (1.0f + (this.field_5974.method_43057() - this.field_5974.method_43057()) * 0.2f) * 0.7f, false);
        clientWorld.method_8406((ParticleEffect)ParticleTypes.field_49139, vec3d.method_10216(), vec3d.method_10214(), vec3d.method_10215(), 1.0, 0.0, 0.0);
        ProjectileOptimizer.removeIfBuffered(this.method_5628());
    }
}
