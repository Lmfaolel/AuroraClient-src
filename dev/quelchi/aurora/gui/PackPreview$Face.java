package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PackTextures$Sprite;

public record PackPreview$Face(PackTextures$Sprite s, int u, int v, int w, int h) {
    public static PackPreview$Face whole(PackTextures$Sprite packTextures$Sprite) {
        return new PackPreview$Face(packTextures$Sprite, 0, 0, packTextures$Sprite.frameWidth(), packTextures$Sprite.frameHeight());
    }

    public static PackPreview$Face sheet(PackTextures$Sprite packTextures$Sprite, int n, int n2, int n3, int n4) {
        float f = (float)packTextures$Sprite.width() / 64.0f;
        return new PackPreview$Face(packTextures$Sprite, Math.round((float)n * f), Math.round((float)n2 * f), Math.round((float)n3 * f), Math.round((float)n4 * f));
    }
}
