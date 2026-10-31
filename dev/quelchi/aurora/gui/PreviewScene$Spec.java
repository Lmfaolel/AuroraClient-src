package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene$Actor;
import dev.quelchi.aurora.gui.PreviewScene$Geometry;
import java.util.List;
import net.minecraft.client.render.entity.state.EntityRenderState;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public record PreviewScene$Spec(@Nullable EntityRenderState entity, Vector3f offset, float cx, float cy, float size, Vector3f translation, Quaternionf rotation, @Nullable Quaternionf cameraAngle, float yawDeg, float[] specks, int[] colors, int count, @Nullable PreviewScene$Geometry geometry, List<PreviewScene$Actor> actors) {
}
