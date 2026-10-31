package dev.quelchi.aurora.shield;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.ShieldTweaksModule;
import dev.quelchi.aurora.shield.GreyscaleTextures;
import dev.quelchi.aurora.shield.ShieldStates;
import java.util.Objects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.SpriteHolder;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

public final class ShieldRenderer {
    private static final Identifier PLAIN;
    private static final Identifier PATTERNED;
    private static PlayerEntity focused;

    private ShieldRenderer() {
    }

    public static void setFocused(PlayerEntity playerEntity) {
        focused = playerEntity;
    }

    public static PlayerEntity consumeFocused() {
        PlayerEntity playerEntity = focused;
        focused = null;
        return playerEntity;
    }

    public static void clearFocus() {
        focused = null;
    }

    public static void render(ComponentMap componentMap, ShieldEntityModel shieldEntityModel, SpriteHolder spriteHolder, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, int n2, boolean bl, int n3) {
        block32: {
            ShieldEntityModel shieldEntityModel2;
            boolean bl2;
            RenderLayer renderLayer;
            RenderLayer renderLayer2;
            MatrixStack matrixStack2;
            OrderedRenderCommandQueue orderedRenderCommandQueue2;
            block30: {
                RenderLayer renderLayer3;
                block31: {
                    boolean bl3;
                    block34: {
                        block33: {
                            Identifier identifier;
                            boolean bl4;
                            DyeColor dyeColor;
                            BannerPatternsComponent bannerPatternsComponent;
                            boolean bl5;
                            block27: {
                                block29: {
                                    block28: {
                                        DyeColor dyeColor2;
                                        block36: {
                                            block26: {
                                                Object object;
                                                block25: {
                                                    BannerPatternsComponent bannerPatternsComponent2;
                                                    block35: {
                                                        block24: {
                                                            Object object2;
                                                            block23: {
                                                                bl5 = ShieldStates.a;
                                                                object2 = componentMap;
                                                                if (bl5) break block23;
                                                                if (object2 == null) break block24;
                                                                object2 = componentMap.method_58695(DataComponentTypes.field_49619, (Object)BannerPatternsComponent.field_49404);
                                                            }
                                                            bannerPatternsComponent2 = (BannerPatternsComponent)object2;
                                                            break block35;
                                                        }
                                                        bannerPatternsComponent2 = BannerPatternsComponent.field_49404;
                                                    }
                                                    bannerPatternsComponent = bannerPatternsComponent2;
                                                    object = componentMap;
                                                    if (bl5) break block25;
                                                    if (object == null) break block26;
                                                    object = componentMap.method_58694(DataComponentTypes.field_49620);
                                                }
                                                dyeColor2 = (DyeColor)object;
                                                break block36;
                                            }
                                            dyeColor2 = null;
                                        }
                                        dyeColor = dyeColor2;
                                        bl4 = bannerPatternsComponent.comp_2428().isEmpty();
                                        if (bl5) break block27;
                                        if (!bl4) break block28;
                                        if (dyeColor == null) break block29;
                                    }
                                    bl4 = true;
                                    break block27;
                                }
                                bl4 = false;
                            }
                            boolean bl6 = bl4;
                            Identifier identifier2 = identifier = bl6 ? PATTERNED : PLAIN;
                            if (ShieldTweaksModule.desaturatesBeforeTint()) {
                                identifier = GreyscaleTextures.get(identifier);
                            }
                            Identifier identifier3 = identifier;
                            renderLayer3 = RenderLayers.method_76000((Identifier)identifier3);
                            matrixStack.method_22903();
                            matrixStack.method_22905(1.0f, -1.0f, -1.0f);
                            orderedRenderCommandQueue2 = orderedRenderCommandQueue;
                            matrixStack2 = matrixStack;
                            renderLayer2 = renderLayer3;
                            renderLayer = renderLayer3;
                            bl2 = bl;
                            shieldEntityModel2 = shieldEntityModel;
                            if (bl5) break block30;
                            orderedRenderCommandQueue2.method_73483(matrixStack2, renderLayer2, (arg_0, arg_1) -> ShieldRenderer.lambda$render$0(renderLayer, bl2, shieldEntityModel2, n, n2, n3, arg_0, arg_1));
                            if (!bl6) break block31;
                            DyeColor dyeColor3 = Objects.requireNonNullElse(dyeColor, DyeColor.field_7952);
                            orderedRenderCommandQueue.method_73483(matrixStack, renderLayer3, (arg_0, arg_1) -> ShieldRenderer.lambda$render$1(renderLayer3, bl, spriteHolder, shieldEntityModel, dyeColor3, bannerPatternsComponent, n, n2, n3, arg_0, arg_1));
                            if (!bl5) break block32;
                            if (!Module.a) break block33;
                            bl3 = false;
                            break block34;
                        }
                        bl3 = true;
                    }
                    Module.a = bl3;
                }
                orderedRenderCommandQueue2 = orderedRenderCommandQueue;
                matrixStack2 = matrixStack;
                renderLayer2 = renderLayer3;
                renderLayer = renderLayer3;
                bl2 = bl;
                shieldEntityModel2 = shieldEntityModel;
            }
            orderedRenderCommandQueue2.method_73483(matrixStack2, renderLayer2, (arg_0, arg_1) -> ShieldRenderer.lambda$render$2(renderLayer, bl2, shieldEntityModel2, n, n2, n3, arg_0, arg_1));
        }
        matrixStack.method_22909();
    }

    private static void renderPatterned(MatrixStack matrixStack, SpriteHolder spriteHolder, VertexConsumerProvider vertexConsumerProvider, VertexConsumer vertexConsumer, ModelPart modelPart, DyeColor dyeColor, BannerPatternsComponent bannerPatternsComponent, int n, int n2, int n3) {
        modelPart.method_22699(matrixStack, vertexConsumer, n, n2, n3);
        boolean bl = ShieldStates.a;
        ShieldRenderer.renderLayer(matrixStack, spriteHolder, vertexConsumerProvider, modelPart, TexturedRenderLayers.field_49770, dyeColor, n, n2, n3);
        int n4 = Math.min(16, bannerPatternsComponent.comp_2428().size());
        for (int i = 0; i < n4; ++i) {
            BannerPatternsComponent.Layer layer = (BannerPatternsComponent.Layer)bannerPatternsComponent.comp_2428().get(i);
            SpriteIdentifier spriteIdentifier = TexturedRenderLayers.method_33083((RegistryEntry)layer.comp_2429());
            ShieldRenderer.renderLayer(matrixStack, spriteHolder, vertexConsumerProvider, modelPart, spriteIdentifier, layer.comp_2430(), n, n2, n3);
            if (!bl) continue;
        }
    }

    private static void renderLayer(MatrixStack matrixStack, SpriteHolder spriteHolder, VertexConsumerProvider vertexConsumerProvider, ModelPart modelPart, SpriteIdentifier spriteIdentifier, DyeColor dyeColor, int n, int n2, int n3) {
        int n4 = ColorHelper.method_61330((int)ColorHelper.method_61320((int)n3), (int)dyeColor.method_7787());
        modelPart.method_22699(matrixStack, spriteIdentifier.method_24145(spriteHolder, vertexConsumerProvider, RenderLayers::method_76000), n, n2, n4);
    }

    private static MatrixStack localStack(MatrixStack.Entry entry) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.method_23760().method_66521(entry);
        return matrixStack;
    }

    private static VertexConsumerProvider.Immediate immediate() {
        return MinecraftClient.method_1551().method_22940().method_23000();
    }

    private static void lambda$render$2(RenderLayer renderLayer, boolean bl, ShieldEntityModel shieldEntityModel, int n, int n2, int n3, MatrixStack.Entry entry, VertexConsumer vertexConsumer) {
        MatrixStack matrixStack = ShieldRenderer.localStack(entry);
        VertexConsumerProvider.Immediate immediate = ShieldRenderer.immediate();
        VertexConsumer vertexConsumer2 = ItemRenderer.method_23181((VertexConsumerProvider)immediate, (RenderLayer)renderLayer, (boolean)false, (boolean)bl);
        shieldEntityModel.method_23774().method_22699(matrixStack, vertexConsumer2, n, n2, n3);
        immediate.method_22993();
    }

    private static void lambda$render$1(RenderLayer renderLayer, boolean bl, SpriteHolder spriteHolder, ShieldEntityModel shieldEntityModel, DyeColor dyeColor, BannerPatternsComponent bannerPatternsComponent, int n, int n2, int n3, MatrixStack.Entry entry, VertexConsumer vertexConsumer) {
        MatrixStack matrixStack = ShieldRenderer.localStack(entry);
        VertexConsumerProvider.Immediate immediate = ShieldRenderer.immediate();
        VertexConsumer vertexConsumer2 = ItemRenderer.method_23181((VertexConsumerProvider)immediate, (RenderLayer)renderLayer, (boolean)false, (boolean)bl);
        ShieldRenderer.renderPatterned(matrixStack, spriteHolder, (VertexConsumerProvider)immediate, vertexConsumer2, shieldEntityModel.method_23774(), dyeColor, bannerPatternsComponent, n, n2, n3);
        immediate.method_22993();
    }

    private static void lambda$render$0(RenderLayer renderLayer, boolean bl, ShieldEntityModel shieldEntityModel, int n, int n2, int n3, MatrixStack.Entry entry, VertexConsumer vertexConsumer) {
        MatrixStack matrixStack = ShieldRenderer.localStack(entry);
        matrixStack.method_46416(0.0f, 0.0f, 1.0E-4f);
        VertexConsumerProvider.Immediate immediate = ShieldRenderer.immediate();
        VertexConsumer vertexConsumer2 = ItemRenderer.method_23181((VertexConsumerProvider)immediate, (RenderLayer)renderLayer, (boolean)false, (boolean)bl);
        shieldEntityModel.method_23775().method_22699(matrixStack, vertexConsumer2, n, n2, n3);
        immediate.method_22993();
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "m\t'\u0017=/\u0016jC:\r<4\u0007`C,\u000b!8\u001f}3=\u0002;8]i\u00028)m\t'\u0017=/\u0016jC:\r<4\u0007`C,\u000b!8\u001f}3=\u0002;8,w\u0003/\u0002<)\u0016k\u0002q\u0013&:";
            var5_3 = "m\t'\u0017=/\u0016jC:\r<4\u0007`C,\u000b!8\u001f}3=\u0002;8]i\u00028)m\t'\u0017=/\u0016jC:\r<4\u0007`C,\u000b!8\u001f}3=\u0002;8,w\u0003/\u0002<)\u0016k\u0002q\u0013&:".length();
            var2_4 = 31;
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
                        v6 = 25;
                        break;
                    }
                    case 1: {
                        v6 = 108;
                        break;
                    }
                    case 2: {
                        v6 = 95;
                        break;
                    }
                    case 3: {
                        v6 = 99;
                        break;
                    }
                    case 4: {
                        v6 = 72;
                        break;
                    }
                    case 5: {
                        v6 = 93;
                        break;
                    }
                    default: {
                        v6 = 115;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        ShieldRenderer.PLAIN = Identifier.method_60656((String)var0[1]);
        ShieldRenderer.PATTERNED = Identifier.method_60656((String)var0[0]);
    }
}
