package dev.quelchi.aurora.mixins;

import net.minecraft.client.font.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={TextRenderer.class})
public interface TextRendererAccessor {
    @Accessor(value="field_61529")
    public TextRenderer.GlyphsProvider aurora$fonts();
}
