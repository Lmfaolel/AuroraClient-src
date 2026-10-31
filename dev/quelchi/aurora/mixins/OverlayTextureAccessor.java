package dev.quelchi.aurora.mixins;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.texture.NativeImageBackedTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={OverlayTexture.class})
public interface OverlayTextureAccessor {
    @Accessor(value="field_21013")
    public NativeImageBackedTexture divine$getTexture();
}
