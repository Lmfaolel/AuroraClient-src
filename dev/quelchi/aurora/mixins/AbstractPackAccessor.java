package dev.quelchi.aurora.mixins;

import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.resource.ResourcePackProfile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={ResourcePackOrganizer.AbstractPack.class})
public interface AbstractPackAccessor {
    @Accessor(value="field_25461")
    public ResourcePackProfile getProfile();
}
