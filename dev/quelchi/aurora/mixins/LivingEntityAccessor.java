package dev.quelchi.aurora.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={LivingEntity.class})
public interface LivingEntityAccessor {
    @Accessor(value="field_6257")
    public static TrackedData<Byte> aurora$livingFlags() {
        throw new AssertionError((Object)"LivingEntityAccessor mixin did not apply");
    }
}
