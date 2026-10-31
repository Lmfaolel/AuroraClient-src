package dev.quelchi.aurora.mixins;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={HandledScreen.class})
public interface HandledScreenSizeAccessor {
    @Accessor(value="field_2792")
    public int aurora$bgWidth();

    @Accessor(value="field_2779")
    public int aurora$bgHeight();
}
