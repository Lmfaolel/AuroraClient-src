package dev.quelchi.aurora.optimize;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;

@FunctionalInterface
public interface ProjectileOptimizer$Shooter {
    public void shoot(LivingEntity var1, ProjectileEntity var2, int var3, float var4, float var5, float var6, LivingEntity var7);
}
