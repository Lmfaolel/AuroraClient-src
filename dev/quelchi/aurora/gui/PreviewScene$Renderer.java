package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.PreviewScene$Actor;
import dev.quelchi.aurora.gui.PreviewScene$Figure;
import dev.quelchi.aurora.gui.PreviewScene$Scene;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.render.SpecialGuiElementRenderer;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.RenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

abstract class PreviewScene$Renderer<S extends PreviewScene$Scene>
extends SpecialGuiElementRenderer<S> {
    private static final String a;

    PreviewScene$Renderer(VertexConsumerProvider.Immediate immediate) {
        super(immediate);
    }

    protected float method_70907(int n, int n2) {
        return (float)n / 2.0f;
    }

    
    protected void render(S var1_1, MatrixStack var2_2) {
        block9: {
            var3_3 = PreviewScene.a;
            v0 = PreviewScene.broken;
            if (var3_3 == 0) {
                if (v0 == 0) break block9;
                return;
            }
        }
        try {
            v0 = var1_1.comp_4122() + var1_1.comp_4124();
lbl13:

            var4_4 = (float)v0 / 2.0f;
            var5_6 = (float)(var1_1.comp_4123() + var1_1.comp_4125()) / 2.0f;
            for (PreviewScene$Figure var7_8 : var1_1.figures()) {
                try {
                    var2_2.method_22903();
                    var2_2.method_46416(var7_8.cx() - var4_4, var7_8.cy() - var5_6, 0.0f);
                    var2_2.method_22905(var7_8.size(), var7_8.size(), var7_8.size());
                    if (var3_3 != 0) return;
                    this.draw(var7_8, var2_2);
                    var2_2.method_22909();
                    if (var3_3 == 0) continue;
                    return;
                }
                catch (Throwable v2) {
                    throw v2;
                    return;
                }
            }
        }
        catch (Throwable var4_5) {
            PreviewScene.broken = true;
            Aurora.LOG.error(PreviewScene$Renderer.a, var4_5);
        }
    }

    private void draw(PreviewScene$Figure previewScene$Figure, MatrixStack matrixStack) {
        block33: {
            block35: {
                PreviewScene$Figure previewScene$Figure2;
                VertexConsumer vertexConsumer;
                Vector3f vector3f;
                Object object;
                block34: {
                    PreviewScene$Actor previewScene$Actor2;
                    Quaternionf quaternionf;
                    int n;
                    block31: {
                        block32: {
                            PreviewScene$Figure previewScene$Figure3;
                            block28: {
                                block30: {
                                    RenderDispatcher renderDispatcher;
                                    block29: {
                                        CameraRenderState cameraRenderState;
                                        EntityRenderManager entityRenderManager;
                                        block26: {
                                            block27: {
                                                Quaternionf quaternionf2;
                                                block24: {
                                                    block25: {
                                                        MinecraftClient minecraftClient = MinecraftClient.method_1551();
                                                        minecraftClient.field_1773.method_71114().method_71034(DiffuseLighting.Type.field_60028);
                                                        matrixStack.method_46416(previewScene$Figure.translation().x, previewScene$Figure.translation().y, previewScene$Figure.translation().z);
                                                        matrixStack.method_22907((Quaternionfc)previewScene$Figure.rotation());
                                                        renderDispatcher = minecraftClient.field_1773.method_72911();
                                                        entityRenderManager = minecraftClient.method_1561();
                                                        n = PreviewScene.a;
                                                        cameraRenderState = new CameraRenderState();
                                                        quaternionf2 = previewScene$Figure.cameraAngle();
                                                        if (n != 0) break block24;
                                                        if (quaternionf2 == null) break block25;
                                                        cameraRenderState.field_63081 = previewScene$Figure.cameraAngle().conjugate(new Quaternionf()).rotateY((float)Math.PI);
                                                    }
                                                    quaternionf2 = RotationAxis.field_40716.rotationDegrees(-previewScene$Figure.yawDeg());
                                                }
                                                quaternionf = quaternionf2;
                                                previewScene$Figure3 = previewScene$Figure;
                                                if (n != 0) break block26;
                                                if (previewScene$Figure3.entity() == null) break block27;
                                                object = quaternionf.transform(new Vector3f((Vector3fc)previewScene$Figure.offset()));
                                                entityRenderManager.method_72976(previewScene$Figure.entity(), cameraRenderState, (double)((Vector3f)object).x, (double)((Vector3f)object).y, (double)((Vector3f)object).z, matrixStack, (OrderedRenderCommandQueue)renderDispatcher.method_73003());
                                            }
                                            previewScene$Figure3 = previewScene$Figure;
                                        }
                                        if (n != 0) break block28;
                                        if (previewScene$Figure3.actors().isEmpty()) break block29;
                                        matrixStack.method_22903();
                                        matrixStack.method_22907((Quaternionfc)quaternionf);
                                        for (PreviewScene$Actor previewScene$Actor2 : previewScene$Figure.actors()) {
                                            entityRenderManager.method_72976(previewScene$Actor2.entity(), cameraRenderState, (double)previewScene$Actor2.x(), (double)previewScene$Actor2.y(), (double)previewScene$Actor2.z(), matrixStack, (OrderedRenderCommandQueue)renderDispatcher.method_73003());
                                            if (n == 0) {
                                                if (n == 0) continue;
                                                break;
                                            }
                                            break block30;
                                        }
                                        matrixStack.method_22909();
                                    }
                                    renderDispatcher.method_73002();
                                    if (n != 0) break block31;
                                    this.field_59933.method_22993();
                                }
                                previewScene$Figure3 = previewScene$Figure;
                            }
                            if (previewScene$Figure3.count() != 0 || previewScene$Figure.geometry() != null) break block32;
                            return;
                        }
                        matrixStack.method_22903();
                        matrixStack.method_22907((Quaternionfc)quaternionf);
                    }
                    object = matrixStack.method_23760().method_23761();
                    previewScene$Actor2 = new Quaternionf((Quaternionfc)previewScene$Figure.rotation()).mul((Quaternionfc)quaternionf).conjugate();
                    Vector3f vector3f2 = previewScene$Actor2.transform(new Vector3f(1.0f, 0.0f, 0.0f));
                    Vector3f vector3f3 = previewScene$Actor2.transform(new Vector3f(0.0f, 1.0f, 0.0f));
                    vector3f = previewScene$Actor2.transform(new Vector3f(0.0f, 0.0f, -1.0f));
                    vertexConsumer = this.field_59933.method_73477(RenderLayers.method_76023());
                    float[] fArray = previewScene$Figure.specks();
                    int[] nArray = previewScene$Figure.colors();
                    for (int i = 0; i < previewScene$Figure.count(); ++i) {
                        int n2 = i * 4;
                        float f = fArray[n2];
                        float f2 = fArray[n2 + 1];
                        float f3 = fArray[n2 + 2];
                        float f4 = fArray[n2 + 3];
                        int n3 = nArray[i];
                        int n4 = n3 >>> 24 & 0xFF;
                        int n5 = n3 >>> 16 & 0xFF;
                        int n6 = n3 >>> 8 & 0xFF;
                        int n7 = n3 & 0xFF;
                        float f5 = vector3f2.x * f4;
                        float f6 = vector3f2.y * f4;
                        float f7 = vector3f2.z * f4;
                        if (n == 0) {
                            float f8 = vector3f3.x * f4;
                            float f9 = vector3f3.y * f4;
                            float f10 = vector3f3.z * f4;
                            vertexConsumer.method_22918((Matrix4fc)object, f - f5 - f8, f2 - f6 - f9, f3 - f7 - f10).method_1336(n5, n6, n7, n4);
                            vertexConsumer.method_22918((Matrix4fc)object, f - f5 + f8, f2 - f6 + f9, f3 - f7 + f10).method_1336(n5, n6, n7, n4);
                            vertexConsumer.method_22918((Matrix4fc)object, f + f5 + f8, f2 + f6 + f9, f3 + f7 + f10).method_1336(n5, n6, n7, n4);
                            vertexConsumer.method_22918((Matrix4fc)object, f + f5 - f8, f2 + f6 - f9, f3 + f7 - f10).method_1336(n5, n6, n7, n4);
                            if (n == 0) continue;
                        }
                        break block33;
                    }
                    previewScene$Figure2 = previewScene$Figure;
                    if (n != 0) break block34;
                    if (previewScene$Figure2.geometry() == null) break block35;
                    previewScene$Figure2 = previewScene$Figure;
                }
                previewScene$Figure2.geometry().draw(vertexConsumer, (Matrix4f)object, new Vec3d((double)vector3f.x * 1000.0, (double)vector3f.y * 1000.0, (double)vector3f.z * 1000.0));
            }
            matrixStack.method_22909();
            this.field_59933.method_22993();
        }
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "\u0002$yPDl\f0e{FSu\u000702+GUf\u00000emU_o\u000b1i+RWo\u0002<+l\u0014Tb\r>e\u007f[\u0016w\u00060emXWwN%7nB_f\u0019".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 85;
                    case 1 -> 69;
                    case 2 -> 11;
                    case 3 -> 52;
                    case 4 -> 54;
                    case 5 -> 3;
                    default -> 110;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
    }
}
