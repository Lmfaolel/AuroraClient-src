package dev.quelchi.aurora.glass.ui;

import dev.quelchi.aurora.glass.render.GlassRenderer;
import dev.quelchi.aurora.glass.style.LiquidGlassTheme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public record UiContext(DrawContext graphics, GlassRenderer glass, LiquidGlassTheme theme, int mouseX, int mouseY, float delta, long now) {
    public TextRenderer font() {
        return MinecraftClient.method_1551().field_1772;
    }
}
