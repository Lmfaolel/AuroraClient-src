package dev.quelchi.aurora.mixins;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.SpriteContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={SpriteContents.class})
public interface SpriteContentsAccessor {
    @Accessor(value="field_40540")
    public NativeImage[] aurora$levels();
}
