package dev.quelchi.aurora.mixins;

import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={NativeImage.class})
public interface NativeImageAccessor {
    @Accessor(value="field_4988")
    public long aurora$pointer();
}
