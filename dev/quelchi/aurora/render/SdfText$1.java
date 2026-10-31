package dev.quelchi.aurora.render;

import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import net.minecraft.client.texture.AbstractTexture;

class SdfText$1
extends AbstractTexture {
    final GpuTexture val$tex;
    final GpuTextureView val$view;

    SdfText$1(GpuTexture gpuTexture, GpuTextureView gpuTextureView) {
        this.val$tex = gpuTexture;
        this.val$view = gpuTextureView;
        this.field_56974 = this.val$tex;
        this.field_60597 = this.val$view;
        this.field_63613 = this.field_63613;
    }

    public void close() {
    }
}
