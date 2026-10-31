package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PersistentProjectileEntity.class})
public abstract class PersistentProjectileEntityMixin
extends ProjectileEntity {
    protected PersistentProjectileEntityMixin(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method={"method_7454"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$clientArrowHitEntity(EntityHitResult entityHitResult, CallbackInfo callbackInfo) {
        if (this.method_73183() instanceof ClientWorld && ProjectileOptimizer.buffered(this.method_5628())) {
            ProjectileOptimizer.arrowHitEntity(this.method_5628());
            ProjectileOptimizer.remove(this.method_5628());
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_24920"}, at={@At(value="RETURN")})
    private void aurora$clientArrowHitBlock(BlockHitResult blockHitResult, CallbackInfo callbackInfo) {
        World world = this.method_73183();
        if (!(world instanceof ClientWorld)) {
            return;
        }
        ClientWorld clientWorld = (ClientWorld)world;
        if (!ProjectileOptimizer.buffered(this.method_5628())) {
            return;
        }
        world = new Vec3d(this.method_23317(), this.method_23318(), this.method_23321());
        EntitySpawnS2CPacket entitySpawnS2CPacket = ProjectileOptimizer.consumePendingArrow(this.method_5628());
        if (entitySpawnS2CPacket != null) {
            ProjectileOptimizer.addServerArrow(clientWorld, entitySpawnS2CPacket, (Vec3d)world, Vec3d.field_1353);
            ProjectileOptimizer.remove(this.method_5628());
        } else {
            ProjectileOptimizer.markLanded(this.method_5628(), (Vec3d)world);
        }
    }
}
