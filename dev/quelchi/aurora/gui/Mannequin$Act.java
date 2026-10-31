package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene$Actor;
import dev.quelchi.aurora.gui.PreviewScene$Geometry;
import dev.quelchi.aurora.render.LocalSpeckSink;
import java.util.List;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.joml.Vector3f;

public interface Mannequin$Act {
    default public void pose(LivingEntityRenderState livingEntityRenderState) {
    }

    default public boolean visible() {
        return true;
    }

    default public void offset(Vector3f vector3f) {
    }

    default public void specks(LocalSpeckSink localSpeckSink, boolean bl) {
    }

    default public PreviewScene$Geometry geometry() {
        return null;
    }

    default public List<PreviewScene$Actor> actors() {
        return List.of();
    }
}
