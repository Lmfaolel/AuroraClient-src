package dev.quelchi.aurora.popchams;

import dev.quelchi.aurora.mixins.ModelPartAccessor;
import dev.quelchi.aurora.popchams.PositionHandler;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

final class WireframePartExtractor {
    private static final int[][] EDGES = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}, {4, 5}, {5, 6}, {6, 7}, {7, 4}, {0, 4}, {1, 5}, {2, 6}, {3, 7}};

    WireframePartExtractor() {
    }

    void extractModelPart(ModelPart modelPart, MatrixStack matrixStack, VertexConsumer vertexConsumer, int n, double d) {
        block4: {
            matrixStack.method_22903();
            boolean bl = PositionHandler.a;
            modelPart.method_22703(matrixStack);
            Matrix4f matrix4f = matrixStack.method_23760().method_23761();
            for (ModelPart.Cuboid cuboid : ((ModelPartAccessor)modelPart).aurora$cuboids()) {
                float f = cuboid.field_3645 / 16.0f;
                float f2 = cuboid.field_3644 / 16.0f;
                float f3 = cuboid.field_3643 / 16.0f;
                float f4 = cuboid.field_3648 / 16.0f;
                float f5 = cuboid.field_3647 / 16.0f;
                float f6 = cuboid.field_3646 / 16.0f;
                WireframePartExtractor.boxLines(vertexConsumer, matrix4f, (float)d, f, f2, f3, f4, f5, f6, n);
                if (!bl) {
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            matrixStack.method_22909();
        }
    }

    private static void boxLines(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, float f7, int n) {
        Vector3f[] vector3fArray = new Vector3f[]{new Vector3f(f2, f3, f4), new Vector3f(f5, f3, f4), new Vector3f(f5, f6, f4), new Vector3f(f2, f6, f4), new Vector3f(f2, f3, f7), new Vector3f(f5, f3, f7), new Vector3f(f5, f6, f7), new Vector3f(f2, f6, f7)};
        int n2 = n >> 24 & 0xFF;
        boolean bl = PositionHandler.a;
        int n3 = n >> 16 & 0xFF;
        int n4 = n >> 8 & 0xFF;
        int n5 = n & 0xFF;
        for (int[] nArray : EDGES) {
            Vector3f vector3f = vector3fArray[nArray[0]];
            Vector3f vector3f2 = vector3fArray[nArray[1]];
            Vector3f vector3f3 = new Vector3f((Vector3fc)vector3f2).sub((Vector3fc)vector3f).normalize();
            vertexConsumer.method_22918((Matrix4fc)matrix4f, vector3f.x, vector3f.y, vector3f.z).method_1336(n3, n4, n5, n2).method_75298(f).method_22914(vector3f3.x, vector3f3.y, vector3f3.z);
            vertexConsumer.method_22918((Matrix4fc)matrix4f, vector3f2.x, vector3f2.y, vector3f2.z).method_1336(n3, n4, n5, n2).method_75298(f).method_22914(vector3f3.x, vector3f3.y, vector3f3.z);
            if (!bl) continue;
        }
    }
}
