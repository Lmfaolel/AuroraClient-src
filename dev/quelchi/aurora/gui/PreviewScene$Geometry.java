package dev.quelchi.aurora.gui;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public interface PreviewScene$Geometry {
    public void draw(VertexConsumer var1, Matrix4f var2, Vec3d var3);
}
