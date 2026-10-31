package dev.quelchi.aurora.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AtlasManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={MinecraftClient.class})
public interface MinecraftClientAccessor {
    @Accessor(value="field_61502")
    public AtlasManager divine$getAtlasManager();
}
