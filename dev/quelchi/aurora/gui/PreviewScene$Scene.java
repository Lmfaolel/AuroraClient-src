package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene$Figure;
import java.util.List;
import net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState;

public interface PreviewScene$Scene
extends SpecialGuiElementRenderState {
    public List<PreviewScene$Figure> figures();
}
