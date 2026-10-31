package dev.quelchi.aurora.duck;

import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.render.state.GuiRenderState;
import org.jetbrains.annotations.Nullable;

public interface GlassContext {
    public GuiRenderState aurora$guiState();

    @Nullable
    public ScreenRect aurora$scissor();
}
