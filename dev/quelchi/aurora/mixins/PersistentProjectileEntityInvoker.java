package dev.quelchi.aurora.mixins;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={PersistentProjectileEntity.class})
public interface PersistentProjectileEntityInvoker {
    @Invoker(value="method_65058")
    public void aurora$setInGround(boolean var1);
}
