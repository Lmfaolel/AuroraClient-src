package dev.quelchi.aurora.render;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.render.BoxRenderer$1;
import dev.quelchi.aurora.render.EffectEngine;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.LayeringTransform;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

public final class BoxRenderer {
    private static final Direction[] SIDES;
    private static final RenderPipeline FILL_PIPELINE;
    private static final RenderLayer FILL_LAYER;

    private BoxRenderer() {
    }

    public static void filled(WorldRenderContext worldRenderContext, Box box, int n, int n2) {
        block4: {
            MatrixStack matrixStack;
            block5: {
                int n3 = EffectEngine.a;
                matrixStack = worldRenderContext.matrices();
                if (n3 != 0) break block4;
                if (matrixStack != null) break block5;
                return;
            }
            worldRenderContext.commandQueue().method_73483(matrixStack, FILL_LAYER, (arg_0, arg_1) -> BoxRenderer.lambda$filled$0(box, n, n2, arg_0, arg_1));
        }
    }

    public static void outline(WorldRenderContext worldRenderContext, Box box, int n, int n2, float f) {
        block4: {
            MatrixStack matrixStack;
            block5: {
                int n3 = EffectEngine.a;
                matrixStack = worldRenderContext.matrices();
                if (n3 != 0) break block4;
                if (matrixStack != null) break block5;
                return;
            }
            worldRenderContext.commandQueue().method_73483(matrixStack, RenderLayers.method_76015(), (arg_0, arg_1) -> BoxRenderer.lambda$outline$1(box, n, f, n2, arg_0, arg_1));
        }
    }

    private static void line(MatrixStack.Entry entry, VertexConsumer vertexConsumer, double d, double d2, double d3, double d4, double d5, double d6, int n, int n2, float f) {
        block4: {
            Vector3f vector3f;
            block5: {
                int n3 = EffectEngine.a;
                vector3f = new Vector3f((float)(d4 - d), (float)(d5 - d2), (float)(d6 - d3));
                if (n3 != 0) break block4;
                if (!(vector3f.lengthSquared() > 0.0f)) break block5;
                vector3f.normalize();
            }
            vertexConsumer.method_56824(entry, (float)d, (float)d2, (float)d3).method_39415(n).method_75298(f).method_61959(entry, vector3f);
            vertexConsumer.method_56824(entry, (float)d4, (float)d5, (float)d6).method_39415(n2).method_75298(f).method_61959(entry, vector3f);
        }
    }

    
    private static void face(Matrix4f var0, VertexConsumer var1_1, Direction var2_2, Box var3_3, int var4_4, int var5_5) {
        var7_6 = (float)var3_3.field_1323;
        var6_7 = EffectEngine.a;
        var8_8 = (float)var3_3.field_1322;
        var9_9 = (float)var3_3.field_1321;
        var10_10 = (float)var3_3.field_1320;
        var11_11 = (float)var3_3.field_1325;
        var12_12 = (float)var3_3.field_1324;
        if (var6_7 != 0) // goto
        switch (BoxRenderer$1.$SwitchMap$net$minecraft$util$math$Direction[var2_2.ordinal()]) {
            case 1: {
                var1_1.method_22918((Matrix4fc)var0, var7_6, var8_8, var9_9).method_39415(var4_4);
                var1_1.method_22918((Matrix4fc)var0, var10_10, var8_8, var9_9).method_39415(var4_4);
                var1_1.method_22918((Matrix4fc)var0, var10_10, var8_8, var12_12).method_39415(var4_4);
                var1_1.method_22918((Matrix4fc)var0, var7_6, var8_8, var12_12).method_39415(var4_4);
lbl19:

                if (var6_7 == 0) return;
            }
            case 2: {
                var1_1.method_22918((Matrix4fc)var0, var7_6, var11_11, var9_9).method_39415(var5_5);
                var1_1.method_22918((Matrix4fc)var0, var7_6, var11_11, var12_12).method_39415(var5_5);
                var1_1.method_22918((Matrix4fc)var0, var10_10, var11_11, var12_12).method_39415(var5_5);
                var1_1.method_22918((Matrix4fc)var0, var10_10, var11_11, var9_9).method_39415(var5_5);
                if (var6_7 == 0) return;
            }
            case 3: {
                var1_1.method_22918((Matrix4fc)var0, var7_6, var8_8, var9_9).method_39415(var4_4);
                var1_1.method_22918((Matrix4fc)var0, var7_6, var11_11, var9_9).method_39415(var5_5);
                var1_1.method_22918((Matrix4fc)var0, var10_10, var11_11, var9_9).method_39415(var5_5);
                var1_1.method_22918((Matrix4fc)var0, var10_10, var8_8, var9_9).method_39415(var4_4);
                if (var6_7 == 0) return;
            }
            case 4: {
                var1_1.method_22918((Matrix4fc)var0, var7_6, var8_8, var12_12).method_39415(var4_4);
                var1_1.method_22918((Matrix4fc)var0, var10_10, var8_8, var12_12).method_39415(var4_4);
                var1_1.method_22918((Matrix4fc)var0, var10_10, var11_11, var12_12).method_39415(var5_5);
                var1_1.method_22918((Matrix4fc)var0, var7_6, var11_11, var12_12).method_39415(var5_5);
                if (var6_7 == 0) return;
            }
            case 5: {
                var1_1.method_22918((Matrix4fc)var0, var7_6, var8_8, var9_9).method_39415(var4_4);
                var1_1.method_22918((Matrix4fc)var0, var7_6, var8_8, var12_12).method_39415(var4_4);
                var1_1.method_22918((Matrix4fc)var0, var7_6, var11_11, var12_12).method_39415(var5_5);
                var1_1.method_22918((Matrix4fc)var0, var7_6, var11_11, var9_9).method_39415(var5_5);
                if (var6_7 == 0) return;
            }
            case 6: {
                var1_1.method_22918((Matrix4fc)var0, var10_10, var8_8, var9_9).method_39415(var4_4);
                var1_1.method_22918((Matrix4fc)var0, var10_10, var11_11, var9_9).method_39415(var5_5);
                var1_1.method_22918((Matrix4fc)var0, var10_10, var11_11, var12_12).method_39415(var5_5);
                var1_1.method_22918((Matrix4fc)var0, var10_10, var8_8, var12_12).method_39415(var4_4);
                return;
            }
        }
    }

    private static void lambda$outline$1(Box box, int n, float f, int n2, MatrixStack.Entry entry, VertexConsumer vertexConsumer) {
        double d = box.field_1323;
        double d2 = box.field_1322;
        double d3 = box.field_1321;
        double d4 = box.field_1320;
        double d5 = box.field_1325;
        double d6 = box.field_1324;
        BoxRenderer.line(entry, vertexConsumer, d, d2, d3, d4, d2, d3, n, n, f);
        BoxRenderer.line(entry, vertexConsumer, d4, d2, d3, d4, d2, d6, n, n, f);
        BoxRenderer.line(entry, vertexConsumer, d4, d2, d6, d, d2, d6, n, n, f);
        BoxRenderer.line(entry, vertexConsumer, d, d2, d6, d, d2, d3, n, n, f);
        BoxRenderer.line(entry, vertexConsumer, d, d5, d3, d4, d5, d3, n2, n2, f);
        BoxRenderer.line(entry, vertexConsumer, d4, d5, d3, d4, d5, d6, n2, n2, f);
        BoxRenderer.line(entry, vertexConsumer, d4, d5, d6, d, d5, d6, n2, n2, f);
        BoxRenderer.line(entry, vertexConsumer, d, d5, d6, d, d5, d3, n2, n2, f);
        BoxRenderer.line(entry, vertexConsumer, d, d2, d3, d, d5, d3, n, n2, f);
        BoxRenderer.line(entry, vertexConsumer, d4, d2, d3, d4, d5, d3, n, n2, f);
        BoxRenderer.line(entry, vertexConsumer, d4, d2, d6, d4, d5, d6, n, n2, f);
        BoxRenderer.line(entry, vertexConsumer, d, d2, d6, d, d5, d6, n, n2, f);
    }

    private static void lambda$filled$0(Box box, int n, int n2, MatrixStack.Entry entry, VertexConsumer vertexConsumer) {
        Matrix4f matrix4f = entry.method_23761();
        int n3 = EffectEngine.a;
        for (Direction direction : SIDES) {
            BoxRenderer.face(matrix4f, vertexConsumer, direction, box, n, n2);
            if (n3 == 0) continue;
        }
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "+=\tHK\u0005\"\"!\u000fEV\u001c\",!\u0017K\u001b+=\tHK\u0005R:!\u000bBU\r\u0013/g\u0013NM\u0006\u00122\u0017\u001dNU\b";
            var5_3 = "+=\tHK\u0005\"\"!\u000fEV\u001c\",!\u0017K\u001b+=\tHK\u0005R:!\u000bBU\r\u0013/g\u0013NM\u0006\u00122\u0017\u001dNU\b".length();
            var2_4 = 18;
            var1_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var0[var4_1++] = new String(v0).intern();
                if ((var1_5 += var2_4) < var5_3) {
                    var2_4 = var3_2.charAt(var1_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var1_5;
            v2 = var3_2.substring(v1, v1 + var2_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 74;
                        break;
                    }
                    case 1: {
                        v6 = 72;
                        break;
                    }
                    case 2: {
                        v6 = 123;
                        break;
                    }
                    case 3: {
                        v6 = 39;
                        break;
                    }
                    case 4: {
                        v6 = 57;
                        break;
                    }
                    case 5: {
                        v6 = 100;
                        break;
                    }
                    default: {
                        v6 = 125;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        BoxRenderer.SIDES = Direction.values();
        BoxRenderer.FILL_PIPELINE = RenderPipelines.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{RenderPipelines.field_56860}).withLocation(var0[1]).withDepthWrite(true).withCull(true).build());
        BoxRenderer.FILL_LAYER = RenderLayer.method_75940((String)var0[0], (RenderSetup)RenderSetup.method_75927((RenderPipeline)BoxRenderer.FILL_PIPELINE).method_75937().method_75930(LayeringTransform.field_63976).method_75938());
    }
}
