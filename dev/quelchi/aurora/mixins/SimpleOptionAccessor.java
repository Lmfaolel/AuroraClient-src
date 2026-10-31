package dev.quelchi.aurora.mixins;

import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={SimpleOption.class})
public interface SimpleOptionAccessor {
    @Accessor(value="field_37868")
    public void aurora$setValueUnchecked(Object var1);
}
