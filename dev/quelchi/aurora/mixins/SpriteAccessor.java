package dev.quelchi.aurora.mixins;

import net.minecraft.client.texture.Sprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={Sprite.class})
public interface SpriteAccessor {
    @Accessor(value="field_64248")
    public int aurora$padding();
}
