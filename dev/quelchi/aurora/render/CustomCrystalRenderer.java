package dev.quelchi.aurora.render;

import dev.quelchi.aurora.mixins.ModelPartAccessor;
import dev.quelchi.aurora.module.modules.CustomCrystalsModule;
import dev.quelchi.aurora.module.modules.CustomCrystalsModule$Mode;
import dev.quelchi.aurora.module.modules.CustomCrystalsModule$Piece;
import dev.quelchi.aurora.module.modules.NostalgiaModule;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.GhostCrystals;
import java.util.Map;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.EndCrystalEntityModel;
import net.minecraft.client.render.entity.state.EndCrystalEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;

public final class CustomCrystalRenderer {
    private static final float TILT = 1.0471976f;
    private static final float SIN_45;
    private static final float INNER_SCALE = 0.875f;
    private static final float CORE_SCALE = 0.6699219f;
    private static final CustomCrystalsModule$Piece[] PIECES;
    private static ModelPart outer;
    private static ModelPart inner;
    private static ModelPart core;
    private static ModelPart base;
    private static final String[] a;
    private static final long b;

    private CustomCrystalRenderer() {
    }

    private static void ensureParts() {
        ModelPart modelPart;
        block2: {
            block3: {
                int n = EffectEngine.a;
                modelPart = outer;
                if (n != 0) break block2;
                if (modelPart == null) break block3;
                return;
            }
            modelPart = EndCrystalEntityModel.method_62084().method_32109();
        }
        ModelPart modelPart2 = modelPart;
        String[] stringArray = a;
        ModelPart modelPart3 = modelPart2.method_32086(stringArray[3]);
        ModelPart modelPart4 = modelPart3.method_32086(stringArray[0]);
        ModelPart modelPart5 = modelPart4.method_32086(stringArray[2]);
        outer = CustomCrystalRenderer.standalone(modelPart3);
        inner = CustomCrystalRenderer.standalone(modelPart4);
        core = CustomCrystalRenderer.standalone(modelPart5);
        base = modelPart2.method_32086(stringArray[1]);
    }

    private static ModelPart standalone(ModelPart modelPart) {
        return new ModelPart(((ModelPartAccessor)modelPart).aurora$cuboids(), Map.of());
    }

    
    public static void submit(OrderedRenderCommandQueue orderedRenderCommandQueue, EndCrystalEntityRenderState endCrystalEntityRenderState, MatrixStack matrixStack, RenderLayer renderLayer, int n, int n2, int n3, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlayCommand, float f) {
        CustomCrystalRenderer.ensureParts();
        CustomCrystalsModule$Mode customCrystalsModule$Mode = CustomCrystalsModule.mode();
        float f2 = f * CustomCrystalsModule.opacity();
        int n4 = CustomCrystalsModule.lightLevel();
        int n5 = EffectEngine.a;
        int n6 = n4;
        if (n5 == 0) {
            if (n6 >= 0) {
                n = LightmapTextureManager.method_23687((int)n4, (int)n4);
            }
            n6 = Math.round(MathHelper.method_15363((float)f2, (float)0.0f, (float)1.0f) * 255.0f) << 24;
        }
        int n7 = n6;
        long l = Util.method_658();
        RenderLayer renderLayer2 = CustomCrystalRenderer.texturedLayer(renderLayer, f2);
        EndCrystalEntityRenderState endCrystalEntityRenderState2 = endCrystalEntityRenderState;
        if (n5 == 0) {
            if (endCrystalEntityRenderState2.field_53311) {
                RenderLayer renderLayer3 = customCrystalsModule$Mode == CustomCrystalsModule$Mode.DEFAULT ? renderLayer2 : renderLayer;
                orderedRenderCommandQueue.method_73494(base, matrixStack, renderLayer3, n, n2, null, false, false, n7 | (int)b, crumblingOverlayCommand, n3);
            }
            endCrystalEntityRenderState2 = endCrystalEntityRenderState;
        }
        float f3 = endCrystalEntityRenderState2.field_53328;
        float f4 = CustomCrystalsModule.scale();
        for (CustomCrystalsModule$Piece customCrystalsModule$Piece : PIECES) {
            if (n5 == 0) {
                if (!CustomCrystalsModule.shown(customCrystalsModule$Piece)) continue;
                matrixStack.method_22903();
                CustomCrystalRenderer.place(matrixStack, customCrystalsModule$Piece, f3, f4);
            }
            int n8 = n7 | CustomCrystalsModule.partRgb(customCrystalsModule$Piece, l);
            switch (customCrystalsModule$Mode) {
                case WIREFRAME: {
                    float f5 = CustomCrystalsModule.lineWidth();
                    orderedRenderCommandQueue.method_73483(matrixStack, RenderLayers.method_76015(), (arg_0, arg_1) -> CustomCrystalRenderer.lambda$submit$0(n8, f5, arg_0, arg_1));
                    if (n5 == 0) break;
                }
                case END_PORTAL: {
                    orderedRenderCommandQueue.method_73494(CustomCrystalRenderer.part(customCrystalsModule$Piece), matrixStack, RenderLayers.method_76011(), n, n2, null, false, false, -1, crumblingOverlayCommand, n3);
                    if (n5 == 0) break;
                }
                case END_GATEWAY: {
                    orderedRenderCommandQueue.method_73494(CustomCrystalRenderer.part(customCrystalsModule$Piece), matrixStack, RenderLayers.method_76013(), n, n2, null, false, false, -1, crumblingOverlayCommand, n3);
                    if (n5 == 0) break;
                }
                default: {
                    orderedRenderCommandQueue.method_73494(CustomCrystalRenderer.part(customCrystalsModule$Piece), matrixStack, renderLayer2, n, n2, null, false, false, n8, crumblingOverlayCommand, n3);
                    break;
                }
            }
            matrixStack.method_22909();
            if (n5 == 0) continue;
        }
    }

    private static void place(MatrixStack matrixStack, CustomCrystalsModule$Piece customCrystalsModule$Piece, float f, float f2) {
        float f3 = f * 3.0f * CustomCrystalsModule.rotation() * CustomCrystalsModule.partRotation(customCrystalsModule$Piece);
        float f4 = CustomCrystalsModule.bounceHeight() * CustomCrystalsModule.partBounceHeight(customCrystalsModule$Piece);
        float f5 = CustomCrystalsModule.bounceSpeed() * CustomCrystalsModule.partBounceSpeed(customCrystalsModule$Piece);
        float f6 = CustomCrystalRenderer.wave(f * f5) * f4 - 1.4f;
        float f7 = 24.0f + f6 * 16.0f / 2.0f;
        float f8 = CustomCrystalsModule.heightOffset() / (2.0f * f2) + CustomCrystalsModule.partOffset(customCrystalsModule$Piece) / 2.0f;
        matrixStack.method_46416(0.0f, f7 / 16.0f + f8, 0.0f);
        matrixStack.method_22907((Quaternionfc)CustomCrystalRenderer.rotation(customCrystalsModule$Piece, f3));
        float f9 = CustomCrystalRenderer.vanillaScale(customCrystalsModule$Piece) * CustomCrystalsModule.partScale(customCrystalsModule$Piece);
        matrixStack.method_22905(f9, f9, f9);
    }

    private static Quaternionf rotation(CustomCrystalsModule$Piece customCrystalsModule$Piece, float f) {
        Quaternionf quaternionf;
        Quaternionf quaternionf2;
        block7: {
            Quaternionf quaternionf3;
            Quaternionf quaternionf4;
            block8: {
                float f2 = f * ((float)Math.PI / 180);
                int n = EffectEngine.a;
                quaternionf4 = new Quaternionf().rotationY(f2).rotateAxis(1.0471976f, SIN_45, 0.0f, SIN_45);
                if (customCrystalsModule$Piece == CustomCrystalsModule$Piece.OUTER) {
                    return quaternionf4;
                }
                quaternionf3 = new Quaternionf().setAngleAxis(1.0471976f, SIN_45, 0.0f, SIN_45).rotateY(f2);
                quaternionf2 = quaternionf4;
                quaternionf = quaternionf3;
                if (n != 0) break block7;
                quaternionf2.mul((Quaternionfc)quaternionf);
                if (customCrystalsModule$Piece != CustomCrystalsModule$Piece.INNER) break block8;
                return quaternionf4;
            }
            quaternionf2 = quaternionf4;
            quaternionf = quaternionf3;
        }
        return quaternionf2.mul((Quaternionfc)quaternionf);
    }

    
    private static float vanillaScale(CustomCrystalsModule$Piece customCrystalsModule$Piece) {
        switch (customCrystalsModule$Piece) {
            default: {
                throw new MatchException(null, null);
            }
            case OUTER: {
                return 1.0f;
            }
            case INNER: {
                return 0.875f;
            }
            case CORE: 
        }
        return 0.6699219f;
    }

    
    private static ModelPart part(CustomCrystalsModule$Piece customCrystalsModule$Piece) {
        ModelPart modelPart;
        switch (customCrystalsModule$Piece) {
            default: {
                throw new MatchException(null, null);
            }
            case OUTER: {
                modelPart = outer;
                return modelPart;
            }
            case INNER: {
                modelPart = inner;
                return modelPart;
            }
            case CORE: 
        }
        modelPart = core;
        return modelPart;
    }

    private static float wave(float f) {
        float f2 = MathHelper.method_15374((double)(f * 0.2f)) / 2.0f + 0.5f;
        return (f2 * f2 + f2) * 0.4f;
    }

    private static RenderLayer texturedLayer(RenderLayer renderLayer, float f) {
        RenderLayer renderLayer2;
        block8: {
            block9: {
                float f2;
                int n;
                block6: {
                    block7: {
                        n = EffectEngine.a;
                        float f3 = f - 1.0f;
                        f2 = f3 == 0.0f ? 0 : (f3 > 0.0f ? 1 : -1);
                        if (n != 0) break block6;
                        if (f2 < 0) break block7;
                        return renderLayer;
                    }
                    f2 = (float)NostalgiaModule.oldCrystals();
                }
                RenderLayer renderLayer3 = GhostCrystals.layer((boolean)f2);
                renderLayer2 = renderLayer3;
                if (n != 0) break block8;
                if (renderLayer2 == null) break block9;
                renderLayer2 = renderLayer3;
                break block8;
            }
            renderLayer2 = renderLayer;
        }
        return renderLayer2;
    }

    private static void cubeEdges(MatrixStack.Entry entry, VertexConsumer vertexConsumer, int n, float f) {
        float f2 = 0.25f;
        CustomCrystalRenderer.line(entry, vertexConsumer, -f2, -f2, -f2, f2, -f2, -f2, n, f);
        CustomCrystalRenderer.line(entry, vertexConsumer, f2, -f2, -f2, f2, f2, -f2, n, f);
        CustomCrystalRenderer.line(entry, vertexConsumer, f2, f2, -f2, -f2, f2, -f2, n, f);
        CustomCrystalRenderer.line(entry, vertexConsumer, -f2, f2, -f2, -f2, -f2, -f2, n, f);
        CustomCrystalRenderer.line(entry, vertexConsumer, -f2, -f2, f2, f2, -f2, f2, n, f);
        CustomCrystalRenderer.line(entry, vertexConsumer, f2, -f2, f2, f2, f2, f2, n, f);
        CustomCrystalRenderer.line(entry, vertexConsumer, f2, f2, f2, -f2, f2, f2, n, f);
        CustomCrystalRenderer.line(entry, vertexConsumer, -f2, f2, f2, -f2, -f2, f2, n, f);
        CustomCrystalRenderer.line(entry, vertexConsumer, -f2, -f2, -f2, -f2, -f2, f2, n, f);
        CustomCrystalRenderer.line(entry, vertexConsumer, f2, -f2, -f2, f2, -f2, f2, n, f);
        CustomCrystalRenderer.line(entry, vertexConsumer, f2, f2, -f2, f2, f2, f2, n, f);
        CustomCrystalRenderer.line(entry, vertexConsumer, -f2, f2, -f2, -f2, f2, f2, n, f);
    }

    private static void line(MatrixStack.Entry entry, VertexConsumer vertexConsumer, float f, float f2, float f3, float f4, float f5, float f6, int n, float f7) {
        float f8 = f4 - f;
        float f9 = f5 - f2;
        float f10 = f6 - f3;
        float f11 = MathHelper.method_15355((float)(f8 * f8 + f9 * f9 + f10 * f10));
        vertexConsumer.method_56824(entry, f, f2, f3).method_39415(n).method_75298(f7).method_60831(entry, f8 /= f11, f9 /= f11, f10 /= f11);
        vertexConsumer.method_56824(entry, f4, f5, f6).method_39415(n).method_75298(f7).method_60831(entry, f8, f9, f10);
    }

    private static void lambda$submit$0(int n, float f, MatrixStack.Entry entry, VertexConsumer vertexConsumer) {
        CustomCrystalRenderer.cubeEdges(entry, vertexConsumer, n, f);
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[4];
                    var5_1 = 0;
                    var4_2 = ",\fj_h\u001f7)\u0003wI\u0004'\u0003w_";
                    var6_3 = ",\fj_h\u001f7)\u0003wI\u0004'\u0003w_".length();
                    var3_4 = 11;
                    var2_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var2_5;
                        v1 = var4_2.substring(v0, v0 + var3_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        var4_2 = "&\u0017f_\u000b*\u0017p_h\u001f7)\u0003wI";
                        var6_3 = "&\u0017f_\u000b*\u0017p_h\u001f7)\u0003wI".length();
                        var3_4 = 4;
                        var2_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var2_5;
                            v1 = var4_2.substring(v4, v4 + var3_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var8_6 = 0;
                do {
                    v6 = v6;
                    v8 = var8_6;
                    v9 = v6[v8];
                    switch (var8_6 % 7) {
                        case 0: {
                            v10 = 69;
                            break;
                        }
                        case 1: {
                            v10 = 98;
                            break;
                        }
                        case 2: {
                            v10 = 4;
                            break;
                        }
                        case 3: {
                            v10 = 58;
                            break;
                        }
                        case 4: {
                            v10 = 26;
                            break;
                        }
                        case 5: {
                            v10 = 64;
                            break;
                        }
                        default: {
                            v10 = 80;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var8_6;
lbl65:

                    v7 = v7;
                } while (v7 > var8_6);
                v3 = new String(v6);
                switch (v2) {
                    default: {
                        continue;
                    }
                    case 0:
lbl73:

                    continue;
                }
            }
            CustomCrystalRenderer.a = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 6915116094087154382L;
        while (true)
        CustomCrystalRenderer.b = 953925796196070705L ^ var0_7;
        CustomCrystalRenderer.SIN_45 = MathHelper.method_15374((double)0.7853981852531433);
        CustomCrystalRenderer.PIECES = new CustomCrystalsModule$Piece[]{CustomCrystalsModule$Piece.OUTER, CustomCrystalsModule$Piece.INNER, CustomCrystalsModule$Piece.CORE};
    }
}
