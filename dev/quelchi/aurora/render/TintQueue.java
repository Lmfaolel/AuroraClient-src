package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.EffectEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.MovingBlockRenderState;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.RenderCommandQueue;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;

public final class TintQueue
implements OrderedRenderCommandQueue {
    private final RenderCommandQueue delegate;
    private final int tint;
    private final boolean flat;
    private static final Identifier WHITE;

    private TintQueue(RenderCommandQueue renderCommandQueue, int n, boolean bl) {
        this.delegate = renderCommandQueue;
        this.tint = n;
        this.flat = bl;
    }

    private RenderLayer layer(RenderLayer renderLayer) {
        RenderLayer renderLayer2 = this.flat ? RenderLayers.method_75994((Identifier)WHITE) : renderLayer;
        return renderLayer2;
    }

    public static OrderedRenderCommandQueue wrap(OrderedRenderCommandQueue orderedRenderCommandQueue, int n, boolean bl) {
        block8: {
            OrderedRenderCommandQueue orderedRenderCommandQueue2;
            block6: {
                block7: {
                    int n2 = EffectEngine.a;
                    orderedRenderCommandQueue2 = orderedRenderCommandQueue;
                    if (n2 != 0) break block6;
                    if (orderedRenderCommandQueue2 == null) break block7;
                    if (n != -1) break block8;
                }
                orderedRenderCommandQueue2 = orderedRenderCommandQueue;
            }
            return orderedRenderCommandQueue2;
        }
        return new TintQueue((RenderCommandQueue)orderedRenderCommandQueue, n, bl);
    }

    public <S> void method_73490(Model<? super S> model, S s, MatrixStack matrixStack, RenderLayer renderLayer, int n, int n2, int n3, Sprite sprite, int n4, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlayCommand) {
        this.delegate.method_73490(model, s, matrixStack, this.layer(renderLayer), n, n2, ColorHelper.method_61322((int)n3, (int)this.tint), sprite, n4, crumblingOverlayCommand);
    }

    public void method_73494(ModelPart modelPart, MatrixStack matrixStack, RenderLayer renderLayer, int n, int n2, Sprite sprite, boolean bl, boolean bl2, int n3, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlayCommand, int n4) {
        this.delegate.method_73494(modelPart, matrixStack, this.layer(renderLayer), n, n2, sprite, bl, bl2, ColorHelper.method_61322((int)n3, (int)this.tint), crumblingOverlayCommand, n4);
    }

    public void method_73484(MatrixStack matrixStack, RenderLayer renderLayer, BlockStateModel blockStateModel, float f, float f2, float f3, int n, int n2, int n3) {
        this.delegate.method_73484(matrixStack, renderLayer, blockStateModel, f * (float)ColorHelper.method_61327((int)this.tint) / 255.0f, f2 * (float)ColorHelper.method_61329((int)this.tint) / 255.0f, f3 * (float)ColorHelper.method_61331((int)this.tint) / 255.0f, n, n2, n3);
    }

    public void method_73480(MatrixStack matrixStack, ItemDisplayContext itemDisplayContext, int n, int n2, int n3, int[] nArray, List<BakedQuad> list, RenderLayer renderLayer, ItemRenderState.Glint glint) {
        int n4;
        int n5;
        int[] nArray2;
        block11: {
            int n6;
            int[] nArray3;
            int[] nArray4;
            int n7;
            block10: {
                int[] nArray5;
                block8: {
                    block9: {
                        n7 = EffectEngine.a;
                        nArray5 = nArray;
                        if (n7 != 0) break block8;
                        if (nArray5 != null) break block9;
                        nArray4 = new int[1];
                        break block10;
                    }
                    nArray5 = nArray;
                }
                nArray4 = Arrays.copyOf(nArray5, nArray.length + 1);
            }
            nArray2 = nArray4;
            n5 = nArray2.length - 1;
            for (int i = 0; i < n5; ++i) {
                nArray3 = nArray2;
                n6 = i;
                n4 = ColorHelper.method_61322((int)nArray2[i], (int)this.tint);
                if (n7 == 0) {
                    nArray3[n6] = n4;
                    if (n7 == 0) continue;
                    break;
                }
                break block11;
            }
            nArray3 = nArray2;
            n6 = n5;
            n4 = this.tint;
        }
        nArray3[n6] = n4;
        this.delegate.method_73480(matrixStack, itemDisplayContext, n, n2, n3, nArray2, TintQueue.retint(list, n5), this.layer(renderLayer), glint);
    }

    private static List<BakedQuad> retint(List<BakedQuad> list, int n) {
        ArrayList<BakedQuad> arrayList;
        block7: {
            ArrayList<BakedQuad> arrayList2 = new ArrayList<BakedQuad>(list.size());
            Iterator<BakedQuad> iterator = list.iterator();
            int n2 = EffectEngine.a;
            while (iterator.hasNext()) {
                BakedQuad bakedQuad;
                block8: {
                    BakedQuad bakedQuad2;
                    block9: {
                        bakedQuad2 = iterator.next();
                        arrayList = arrayList2;
                        if (n2 != 0) break block7;
                        bakedQuad = bakedQuad2;
                        if (n2 != 0) break block8;
                        if (!bakedQuad.method_3360()) break block9;
                        bakedQuad = bakedQuad2;
                        break block8;
                    }
                    bakedQuad = new BakedQuad(bakedQuad2.comp_5238(), bakedQuad2.comp_5239(), bakedQuad2.comp_5240(), bakedQuad2.comp_5241(), bakedQuad2.comp_5242(), bakedQuad2.comp_5243(), bakedQuad2.comp_5244(), bakedQuad2.comp_5245(), n, bakedQuad2.comp_3723(), bakedQuad2.comp_3724(), bakedQuad2.comp_3725(), bakedQuad2.comp_3726());
                }
                arrayList.add(bakedQuad);
                if (n2 == 0) continue;
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public RenderCommandQueue method_73529(int n) {
        block5: {
            RenderCommandQueue renderCommandQueue;
            block4: {
                RenderCommandQueue renderCommandQueue2 = this.delegate;
                int n2 = EffectEngine.a;
                renderCommandQueue = renderCommandQueue2;
                if (n2 != 0) break block4;
                if (!(renderCommandQueue instanceof OrderedRenderCommandQueue)) break block5;
                renderCommandQueue = renderCommandQueue2;
            }
            OrderedRenderCommandQueue orderedRenderCommandQueue = (OrderedRenderCommandQueue)renderCommandQueue;
            return new TintQueue(orderedRenderCommandQueue.method_73529(n), this.tint, this.flat);
        }
        return this;
    }

    public void method_73479(MatrixStack matrixStack, float f, List<EntityRenderState.ShadowPiece> list) {
        this.delegate.method_73479(matrixStack, f, list);
    }

    public void method_73482(MatrixStack matrixStack, Vec3d vec3d, int n, Text text, boolean bl, int n2, double d, CameraRenderState cameraRenderState) {
        this.delegate.method_73482(matrixStack, vec3d, n, text, bl, n2, d, cameraRenderState);
    }

    public void method_73478(MatrixStack matrixStack, float f, float f2, OrderedText orderedText, boolean bl, TextRenderer.TextLayerType textLayerType, int n, int n2, int n3, int n4) {
        this.delegate.method_73478(matrixStack, f, f2, orderedText, bl, textLayerType, n, n2, n3, n4);
    }

    public void method_73488(MatrixStack matrixStack, EntityRenderState entityRenderState, Quaternionf quaternionf) {
        this.delegate.method_73488(matrixStack, entityRenderState, quaternionf);
    }

    public void method_73486(MatrixStack matrixStack, EntityRenderState.LeashData leashData) {
        this.delegate.method_73486(matrixStack, leashData);
    }

    public void method_73481(MatrixStack matrixStack, BlockState blockState, int n, int n2, int n3) {
        this.delegate.method_73481(matrixStack, blockState, n, n2, n3);
    }

    public void method_73485(MatrixStack matrixStack, MovingBlockRenderState movingBlockRenderState) {
        this.delegate.method_73485(matrixStack, movingBlockRenderState);
    }

    public void method_73483(MatrixStack matrixStack, RenderLayer renderLayer, OrderedRenderCommandQueue.Custom custom) {
        this.delegate.method_73483(matrixStack, renderLayer, custom);
    }

    public void method_74315(OrderedRenderCommandQueue.LayeredCustom layeredCustom) {
        this.delegate.method_74315(layeredCustom);
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "\u001cb\u0017CW\u001f\u0012\tr\u001dXP\fo\u000e8\u0012DL\noSg\u000bK";
            var5_3 = "\u001cb\u0017CW\u001f\u0012\tr\u001dXP\fo\u000e8\u0012DL\noSg\u000bK".length();
            var2_4 = 6;
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
                        v6 = 125;
                        break;
                    }
                    case 1: {
                        v6 = 23;
                        break;
                    }
                    case 2: {
                        v6 = 101;
                        break;
                    }
                    case 3: {
                        v6 = 44;
                        break;
                    }
                    case 4: {
                        v6 = 37;
                        break;
                    }
                    case 5: {
                        v6 = 126;
                        break;
                    }
                    default: {
                        v6 = 10;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        TintQueue.WHITE = Identifier.method_60655((String)var0[0], (String)var0[1]);
    }
}
