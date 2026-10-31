package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PotionEntity.class})
public abstract class PotionEntityMixin
extends ThrownItemEntity {
    protected PotionEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method={"method_7488"}, at={@At(value="HEAD")})
    private void aurora$clientSplash(HitResult hitResult, CallbackInfo callbackInfo) {
        ClientWorld clientWorld;
        World world;
        block3: {
            block2: {
                world = this.method_73183();
                if (!(world instanceof ClientWorld)) break block2;
                clientWorld = (ClientWorld)world;
                if (ProjectileOptimizer.on()) break block3;
            }
            return;
        }
        world = (PotionContentsComponent)this.method_7495().method_58695(DataComponentTypes.field_49651, (Object)PotionContentsComponent.field_49274);
        int n = world.comp_2378().isPresent() && ((Potion)((RegistryEntry)world.comp_2378().get()).comp_349()).method_8050() ? 2007 : 2002;
        ProjectileOptimizer.worldEvent(clientWorld, n, this.method_24515(), world.method_8064());
        ProjectileOptimizer.removeIfBuffered(this.method_5628());
    }
}
