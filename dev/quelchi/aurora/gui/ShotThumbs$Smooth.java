package dev.quelchi.aurora.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import java.util.function.Supplier;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;

final class ShotThumbs$Smooth
extends NativeImageBackedTexture {
    ShotThumbs$Smooth(Supplier<String> supplier, NativeImage nativeImage) {
        super(supplier, nativeImage);
        this.field_63613 = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
    }
}
