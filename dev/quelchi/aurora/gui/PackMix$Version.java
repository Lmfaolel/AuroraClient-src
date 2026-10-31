package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PackTextures$Sprite;
import java.util.Map;

public record PackMix$Version(String packId, String packName, Map<String, PackTextures$Sprite> sprites) {
    public PackTextures$Sprite sprite(String string) {
        return this.sprites.get(string);
    }
}
