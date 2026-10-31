package dev.quelchi.aurora.mixins;

import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={Particle.class})
public interface ParticleGravityAccessor {
    @Accessor(value="field_3844")
    public void aurora$setGravity(float var1);
}
