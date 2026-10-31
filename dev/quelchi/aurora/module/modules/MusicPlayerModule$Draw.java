package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.MusicPlayerModule$Font;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

record MusicPlayerModule$Draw(DrawContext ctx, TextRenderer tr, MusicPlayerModule$Font face, long pos, boolean shadow, boolean synced, int lit) {
}
