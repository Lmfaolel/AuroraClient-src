package dev.quelchi.aurora.cosmetics.renderer.cape;

import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer$Pass;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeTextureStore;
import dev.quelchi.aurora.cosmetics.renderer.cape.CosmeticRegistry;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public final class CapeRenderer {
    private static final int FULL_BRIGHT;
    private static final long[] a;
    private static final Integer[] b;
    private static final long c;

    private CapeRenderer() {
    }

    
    public static boolean render(MatrixStack var0, OrderedRenderCommandQueue var1_1, int var2_2, PlayerEntityRenderState var3_3, BipedEntityModel<PlayerEntityRenderState> var4_4) {
        block72: {
            block71: {
                block70: {
                    block69: {
                        block67: {
                            block68: {
                                block65: {
                                    block66: {
                                        block63: {
                                            block64: {
                                                block61: {
                                                    block62: {
                                                        var5_5 = CapeLayer.a;
                                                        v0 = var3_3.field_53532;
                                                        if (var5_5) break block61;
                                                        if (!v0) break block62;
                                                        v2 = var3_3;
                                                        if (var5_5) break block63;
                                                        if (!v2.field_53333) break block64;
                                                    }
                                                    v0 = false;
                                                }
                                                return v0;
                                            }
                                            v2 = var3_3;
                                        }
                                        if (var5_5) break block65;
                                        if (!v2.field_53418.method_31574(Items.field_8833)) break block66;
                                        return false;
                                    }
                                    v2 = var3_3;
                                }
                                var6_6 = v2.field_53520;
                                v8 = var6_6;
                                if (var5_5) break block67;
                                if (v8.comp_1627() != null) break block68;
                                return false;
                            }
                            v8 = var6_6;
                        }
                        var7_7 = v8.comp_1627().comp_3627();
                        var8_8 = CosmeticRegistry.capeFor(var7_7);
                        v11 = var8_8;
                        if (var5_5) break block69;
                        if (v11 != null) {
                        }
                        v11 = var8_8;
                    }
                    v13 = v11.layers().size();
                    if (var5_5) break block70;
                    if (v13 >= 2) break block71;
lbl79:

                    v13 = 0;
                }
                return (boolean)v13;
            }
            var9_9 = System.nanoTime() / CapeRenderer.c;
            var11_10 = var3_3.field_53528 * 31 + var7_7.hashCode();
            var0.method_22903();
            for (CapeLayer var13_12 : var8_8.layers()) {
                block84: {
                    block81: {
                        block82: {
                            block83: {
                                block79: {
                                    block80: {
                                        block78: {
                                            block77: {
                                                block76: {
                                                    block75: {
                                                        block85: {
                                                            block73: {
                                                                block74: {
                                                                    if (var5_5) break block72;
                                                                    v16 = var13_12;
                                                                    if (var5_5) break block73;
                                                                    if (v16.pass() == CapeLayer$Pass.BASE) break block74;
                                                                    CapeTextureStore.preload(var13_12);
                                                                }
                                                                v16 = var13_12;
                                                                if (var5_5) break block85;
                                                            }
                                                            if (v16.pass() != CapeLayer$Pass.EMISSIVE) {
                                                                v16 = var13_12;
                                                            }
                                                        }
                                                        if (v16.pass() != CapeLayer$Pass.GLOW) break block75;
lbl121:

                                                        v21 = CapeRenderer.a(13185, 31122288150937637L);
                                                        break block76;
                                                    }
                                                    v21 = var2_2;
                                                }
                                                var14_13 = v21;
                                                v23 = var13_12;
                                                if (var5_5) break block77;
                                                if (v23.blend()) {
                                                }
                                                v23 = var13_12;
                                            }
                                            if (var5_5) break block78;
                                            if (v23.frames().size() > 1) {
                                            }
                                            v23 = var13_12;
                                        }
                                        var15_14 = v23.blendAt(var9_9, var11_10);
                                        var16_15 = var13_12.colorAt(var9_9, var11_10);
                                        v28 = var15_14.current().equals((Object)var15_14.next());
                                        if (var5_5) break block79;
                                        if (v28 == 0) break block80;
                                        CapeRenderer.submit(var1_1, var4_4, var3_3, var0, var13_12, var15_14.current(), var14_13, var16_15);
                                        if (!var5_5) break block81;
                                    }
                                    v28 = var16_15 >>> 24 & 255;
                                }
                                var17_16 = v28;
                                var18_17 = var16_15 & CapeRenderer.a(32466, 1741434810562628983L);
                                var19_18 = Math.round((float)var17_16 * (1.0f - var15_14.progress()));
                                v32 = var19_18;
                                if (var5_5) break block82;
                                if (v32 <= 0) break block83;
                                CapeRenderer.submit(var1_1, var4_4, var3_3, var0, var13_12, var15_14.current(), var14_13, var19_18 << 24 | var18_17);
                            }
                            v32 = Math.round((float)var17_16 * var15_14.progress());
                        }
                        var20_19 = v32;
                        if (var20_19 > 0) {
                            CapeRenderer.submit(var1_1, var4_4, var3_3, var0, var13_12, var15_14.next(), var14_13, var20_19 << 24 | var18_17);
                        }
                    }
                    if (!var5_5) break block84;
lbl206:

                    CapeRenderer.submit(var1_1, var4_4, var3_3, var0, var13_12, var13_12.frameAt(var9_9, var11_10), var14_13, var13_12.colorAt(var9_9, var11_10));
                }
                if (!var5_5) continue;
            }
            var0.method_22909();
        }
        return true;
    }

    private static void submit(OrderedRenderCommandQueue orderedRenderCommandQueue, BipedEntityModel<PlayerEntityRenderState> bipedEntityModel, PlayerEntityRenderState playerEntityRenderState, MatrixStack matrixStack, CapeLayer capeLayer, Identifier identifier, int n, int n2) {
        block4: {
            if (capeLayer.pass() == CapeLayer$Pass.BASE || CapeTextureStore.frame(identifier)) break block4;
            return;
        }
        orderedRenderCommandQueue.method_73490(bipedEntityModel, (Object)playerEntityRenderState, matrixStack, CapeRenderer.renderLayer(capeLayer, identifier), n, OverlayTexture.field_21444, n2, null, playerEntityRenderState.field_61821, null);
    }

    
    private static RenderLayer renderLayer(CapeLayer capeLayer, Identifier identifier) {
        RenderLayer renderLayer;
        switch (capeLayer.pass()) {
            default: {
                throw new MatchException(null, null);
            }
            case BASE: {
                if (capeLayer.alpha() < 0.999f) {
                    renderLayer = RenderLayers.method_76000((Identifier)identifier);
                    return renderLayer;
                }
                renderLayer = RenderLayers.method_75982((Identifier)identifier);
                return renderLayer;
            }
            case OVERLAY: {
                renderLayer = RenderLayers.method_76000((Identifier)identifier);
                return renderLayer;
            }
            case EMISSIVE: 
            case GLOW: 
        }
        renderLayer = RenderLayers.method_76016((Identifier)identifier);
        return renderLayer;
    }

    
    static {
        long l = 7739323209614878806L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "=\u008ea\u00a46\"h\u0083\u00a1\u009fE\u0006\u0084\u00ed\u00da\u00de\u0089\u0089\u00c7Y\u00c9\u00c4'\u00cb";
        int n2 = "=\u008ea\u00a46\"h\u0083\u00a1\u009fE\u0006\u0084\u00ed\u00da\u00de\u0089\u0089\u00c7Y\u00c9\u00c4'\u00cb".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Integer[3];
        FULL_BRIGHT = CapeRenderer.a(31947, 5482043044623798125L);
        long l2 = 6502540407344367694L;
        c = 0x5A3DAA0C6D6DCA0EL ^ l2;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x33A4;
        if (b[n2] == null) {
            CapeRenderer.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
