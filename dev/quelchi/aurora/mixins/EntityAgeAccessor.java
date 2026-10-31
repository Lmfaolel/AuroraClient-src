package dev.quelchi.aurora.mixins;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={Entity.class})
public interface EntityAgeAccessor {
    @Accessor(value="field_6012")
    public int aurora$age();
}
