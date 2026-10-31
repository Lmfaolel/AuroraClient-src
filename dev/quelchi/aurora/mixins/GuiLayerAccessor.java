package dev.quelchi.aurora.mixins;

import java.util.List;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets={"net/minecraft/class_11246$class_11276"})
public interface GuiLayerAccessor {
    @Accessor(value="field_60050")
    public List<?> aurora$simple();

    @Accessor(value="field_60051")
    public List<?> aurora$items();

    @Accessor(value="field_60052")
    public List<?> aurora$texts();

    @Accessor(value="field_60053")
    public List<?> aurora$specials();
}
