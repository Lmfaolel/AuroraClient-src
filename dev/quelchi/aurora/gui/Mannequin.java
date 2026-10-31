package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.renderer.CapeManager;
import dev.quelchi.aurora.gui.Mannequin$Act;
import dev.quelchi.aurora.gui.Mannequin$Specks;
import dev.quelchi.aurora.gui.PreviewPlayer;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.PreviewScene$Actor;
import dev.quelchi.aurora.gui.PreviewScene$Geometry;
import dev.quelchi.aurora.gui.PreviewScene$Spec;
import dev.quelchi.aurora.mixins.InventoryStateInvoker;
import dev.quelchi.aurora.render.LocalSpeckSink;
import dev.quelchi.aurora.util.SkinFetcher;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerSkinType;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;

public final class Mannequin {
    private static final float HEADROOM = 2.4f;
    private static final float SPAN = 2.7f;
    private static volatile boolean guiPass;
    private static final float[] EMPTY_SPECKS;
    private static final int[] EMPTY_COLORS;
    private static final int CAPE_U = 1;
    private static final int CAPE_V = 1;
    private static final int CAPE_TW;
    private static final int CAPE_TH;
    private static final float CAPE_TOP = 1.46f;
    private static final float CAPE_BOTTOM = 0.42f;
    private static final float CAPE_HALF_W = 0.3125f;
    private static final float CAPE_Z = 0.14f;
    private static Identifier capeOverride;
    private static final int FULL_BRIGHT;
    private static final float STANDING = 1.8f;
    private static final float SKIN_UNITS = 2.125f;
    private static final EntityModelLayer SLIM_LAYER;
    private static LoadedEntityModels builtFrom;
    private static PlayerEntityModel wide;
    private static PlayerEntityModel slim;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private Mannequin() {
    }

    public static void endGuiPass() {
        guiPass = false;
    }

    public static void draw(DrawContext drawContext, int n, int n2, int n3, int n4, float f, float f2, float f3, float f4, float f5) {
        Mannequin.draw(drawContext, n, n2, n3, n4, f, f2, f3, f4, f5, null);
    }

    
    public static void draw(DrawContext var0, int var1_1, int var2_2, int var3_3, int var4_4, float var5_5, float var6_6, float var7_7, float var8_8, float var9_9, Mannequin$Act var10_10) {
        block59: {
            block60: {
                block57: {
                    block54: {
                        block56: {
                            block55: {
                                block65: {
                                    block52: {
                                        block50: {
                                            block51: {
                                                block63: {
                                                    block62: {
                                                        block49: {
                                                            block47: {
                                                                block48: {
                                                                    block61: {
                                                                        block45: {
                                                                            block46: {
                                                                                var12_11 = MinecraftClient.method_1551();
                                                                                var11_12 = PreviewScene.a;
                                                                                var13_13 = var12_11.field_1724;
                                                                                v0 = var13_13;
                                                                                if (var11_12 != 0) break block45;
                                                                                if (v0 == null) break block46;
                                                                                v0 = var13_13;
                                                                                break block45;
                                                                            }
                                                                            v0 = PreviewPlayer.get();
                                                                        }
                                                                        var14_14 = v0;
                                                                        var15_15 = Math.min((float)(var4_4 - 12) / 2.4f, (float)var3_3 / 2.7f) * var8_8;
                                                                        var16_16 = Math.max(8, Math.round(var15_15));
                                                                        var17_17 = var1_1 + var3_3 / 2;
                                                                        var18_18 = var2_2 + var4_4 / 2;
                                                                        v2 = var14_14;
                                                                        if (var11_12 != 0) break block47;
                                                                        if (v2 != null) break block48;
                                                                        break block61;
                                                                        catch (Throwable v3) {
                                                                            throw v3;
                                                                        }
                                                                    }
                                                                    v4 = 1.8f;
                                                                    break block49;
                                                                }
                                                                v2 = var14_14;
                                                            }
                                                            v4 = v2.method_18377(var14_14.method_18376()).comp_2186();
                                                        }
                                                        var19_19 = v4 / 2.0f;
                                                        var20_20 = (float)Math.toRadians(var6_6);
                                                        var21_21 = (float)Math.toRadians(var7_7);
                                                        if (var11_12 != 0) break block50;
                                                        var0.method_44379(var1_1, var2_2, var1_1 + var3_3, var2_2 + var4_4);
                                                        if (var14_14 == null) break block51;
                                                        break block62;
                                                        catch (Throwable v6) {
                                                            throw v6;
                                                        }
                                                    }
                                                    v7 = PreviewScene.usable();
                                                    if (var11_12 != 0) break block52;
                                                    break block63;
                                                    catch (Throwable v8) {
                                                        throw v8;
                                                    }
                                                }
                                                if (!v7) break block51;
                                                try {
                                                    Mannequin.scene(var0, var1_1, var2_2, var3_3, var4_4, var16_16, (AbstractClientPlayerEntity)var14_14, var6_6, var21_21, var9_9, var10_10, var5_5);
                                                    Mannequin.guiPass = true;
                                                    var0.method_44380();
                                                    return;
                                                }
                                                catch (Throwable var22_22) {
                                                    block53: {
                                                        block64: {
                                                            if (var11_12 != 0) break block53;
                                                            if (var13_13 != null) // goto
                                                            break block64;
                                                            catch (Throwable v10) {
                                                                throw v10;
                                                            }
                                                        }
                                                        PreviewPlayer.disable(var22_22);
                                                    }
                                                    if (var11_12 == 0) break block51;
lbl83:

                                                    throw var22_22;
                                                }
                                            }
                                            Mannequin.paint(var0, var10_10, var5_5, var17_17, var18_18, var16_16, var19_19 + var9_9, var20_20, var21_21, true);
                                        }
                                        v7 = false;
                                    }
                                    var22_23 = v7;
                                    if (var14_14 == null) break block54;
                                    v13 = var10_10;
                                    if (var11_12 != 0) break block55;
                                    break block65;
                                    catch (Throwable v14) {
                                        throw v14;
                                    }
                                }
                                if (v13 == null) break block56;
                                v13 = var10_10;
                            }
                            v16 = v13.visible();
                            if (var11_12 != 0) break block57;
                            if (!v16) break block54;
                        }
                        try {
                            Mannequin.entity(var0, var1_1, var2_2, var3_3, var4_4, var16_16, (AbstractClientPlayerEntity)var14_14, var6_6, var21_21, var9_9);
                            Mannequin.guiPass = true;
                            var22_23 = true;
                        }
                        catch (Throwable var23_24) {
                            block58: {
                                if (var11_12 != 0) break block58;
                                if (var13_13 == null) {
                                }
                                PreviewPlayer.disable(var23_24);
                            }
                            if (var11_12 == 0) break block54;
lbl136:

                            throw var23_24;
                        }
                    }
                    v16 = var22_23;
                }
                if (v16) break block59;
                v21 = var10_10;
                if (var11_12 != 0) break block60;
                if (v21 != null) {
                }
                v21 = var10_10;
            }
            if (!v21.visible()) break block59;
lbl159:

            Mannequin.cape(var0, var17_17, var18_18, var16_16, var19_19 + var9_9, var20_20, var21_21, true);
            Mannequin.skin(var0, var1_1, var2_2, var3_3, var4_4, var16_16, var9_9, var6_6, var7_7);
            Mannequin.cape(var0, var17_17, var18_18, var16_16, var19_19 + var9_9, var20_20, var21_21, false);
        }
        Mannequin.paint(var0, var10_10, var5_5, var17_17, var18_18, var16_16, var19_19 + var9_9, var20_20, var21_21, false);
        var0.method_44380();
    }

    private static void scene(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float f2, float f3, Mannequin$Act mannequin$Act, float f4) {
        PreviewScene$Spec previewScene$Spec;
        boolean bl;
        List<PreviewScene$Actor> list;
        PreviewScene$Geometry previewScene$Geometry;
        Vector3f vector3f;
        Mannequin$Specks mannequin$Specks;
        Vector3f vector3f2;
        Quaternionf quaternionf;
        LivingEntityRenderState livingEntityRenderState;
        EntityRenderState entityRenderState;
        block15: {
            block14: {
                int n6;
                block12: {
                    EntityRenderState entityRenderState2;
                    block13: {
                        entityRenderState = Mannequin.pose(abstractClientPlayerEntity, f);
                        n6 = PreviewScene.a;
                        if (mannequin$Act == null) break block12;
                        entityRenderState2 = entityRenderState;
                        if (n6 != 0) break block13;
                        if (!(entityRenderState2 instanceof LivingEntityRenderState)) break block12;
                        entityRenderState2 = entityRenderState;
                    }
                    livingEntityRenderState = (LivingEntityRenderState)entityRenderState2;
                    mannequin$Act.pose(livingEntityRenderState);
                }
                livingEntityRenderState = new Quaternionf().rotateX(f2);
                quaternionf = new Quaternionf().rotateZ((float)Math.PI).mul((Quaternionfc)livingEntityRenderState);
                vector3f2 = new Vector3f(0.0f, entityRenderState.field_53330 / 2.0f + f3, 0.0f);
                mannequin$Specks = new Mannequin$Specks();
                vector3f = new Vector3f();
                previewScene$Geometry = null;
                list = List.of();
                bl = true;
                if (n6 != 0) break block14;
                if (mannequin$Act == null) break block15;
                mannequin$Act.specks(mannequin$Specks, true);
                mannequin$Act.offset(vector3f);
            }
            previewScene$Geometry = mannequin$Act.geometry();
            list = mannequin$Act.actors();
            bl = mannequin$Act.visible();
        }
        DrawContext drawContext2 = drawContext;
        PreviewScene$Spec previewScene$Spec2 = previewScene$Spec;
        PreviewScene$Spec previewScene$Spec3 = previewScene$Spec;
        Object object = bl ? entityRenderState : null;
        previewScene$Spec2((EntityRenderState)object, vector3f, (float)n + (float)n3 / 2.0f, (float)n2 + (float)n4 / 2.0f, n5, vector3f2, quaternionf, (Quaternionf)livingEntityRenderState, f, mannequin$Specks.xyz, mannequin$Specks.colors, mannequin$Specks.count, previewScene$Geometry, list);
        PreviewScene.main(drawContext2, List.of(previewScene$Spec3), n, n2, n + n3, n2 + n4);
    }

    private static void entity(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float f2, float f3) {
        EntityRenderState entityRenderState = Mannequin.pose(abstractClientPlayerEntity, f);
        Quaternionf quaternionf = new Quaternionf().rotateX(f2);
        Quaternionf quaternionf2 = new Quaternionf().rotateZ((float)Math.PI).mul((Quaternionfc)quaternionf);
        Vector3f vector3f = new Vector3f(0.0f, entityRenderState.field_53330 / 2.0f + f3, 0.0f);
        drawContext.method_70856(entityRenderState, (float)n5, vector3f, quaternionf2, quaternionf, n, n2, n + n3, n2 + n4);
    }

    public static EntityRenderState actor(AbstractClientPlayerEntity abstractClientPlayerEntity, float f) {
        return Mannequin.pose(abstractClientPlayerEntity, f - 180.0f);
    }

    private static EntityRenderState pose(AbstractClientPlayerEntity abstractClientPlayerEntity, float f) {
        float f2;
        LivingEntityRenderState livingEntityRenderState;
        EntityRenderState entityRenderState;
        block14: {
            block13: {
                LivingEntityRenderState livingEntityRenderState2;
                block12: {
                    int n;
                    block10: {
                        EntityRenderState entityRenderState2;
                        block11: {
                            block8: {
                                block9: {
                                    entityRenderState = InventoryStateInvoker.aurora$state((LivingEntity)abstractClientPlayerEntity);
                                    n = PreviewScene.a;
                                    entityRenderState2 = entityRenderState;
                                    if (n != 0) break block8;
                                    if (!(entityRenderState2 instanceof LivingEntityRenderState)) break block9;
                                    livingEntityRenderState = (LivingEntityRenderState)entityRenderState;
                                    livingEntityRenderState.field_53446 = 180.0f + f;
                                    livingEntityRenderState.field_53447 = 0.0f;
                                    livingEntityRenderState.field_53448 = 0.0f;
                                    livingEntityRenderState.field_53457 = false;
                                    livingEntityRenderState.field_53453 = 1.0f;
                                    livingEntityRenderState.field_53454 = 1.0f;
                                    livingEntityRenderState.field_53455 = false;
                                    livingEntityRenderState.field_53449 = 0.0f;
                                    livingEntityRenderState.field_53451 = 0.0f;
                                    livingEntityRenderState.field_53450 = 0.0f;
                                    livingEntityRenderState.field_53459 = false;
                                    livingEntityRenderState.field_53456 = false;
                                }
                                entityRenderState2 = entityRenderState;
                            }
                            if (n != 0) break block10;
                            if (!(entityRenderState2 instanceof PlayerEntityRenderState)) break block11;
                            livingEntityRenderState = (PlayerEntityRenderState)entityRenderState;
                            livingEntityRenderState.field_53543 = true;
                            livingEntityRenderState.field_53544 = true;
                            livingEntityRenderState.field_53530 = true;
                            livingEntityRenderState.field_53531 = true;
                            livingEntityRenderState.field_53545 = true;
                            livingEntityRenderState.field_53546 = true;
                            livingEntityRenderState.field_53532 = true;
                            livingEntityRenderState.field_53536 = 0.0f;
                            livingEntityRenderState.field_53537 = 0.0f;
                            livingEntityRenderState.field_53538 = 0.0f;
                        }
                        entityRenderState2 = entityRenderState;
                    }
                    entityRenderState2.field_61820 = Mannequin.a(111, 801615781781552681L);
                    livingEntityRenderState = abstractClientPlayerEntity.method_18377(abstractClientPlayerEntity.method_18376());
                    livingEntityRenderState2 = livingEntityRenderState;
                    if (n != 0) break block12;
                    if (!(livingEntityRenderState2.comp_2186() > 0.1f)) break block13;
                    livingEntityRenderState2 = livingEntityRenderState;
                }
                f2 = livingEntityRenderState2.comp_2186();
                break block14;
            }
            f2 = 1.8f;
        }
        float f3 = f2;
        entityRenderState.field_53329 = livingEntityRenderState.comp_2185();
        entityRenderState.field_53330 = f3;
        return entityRenderState;
    }

    private static void paint(DrawContext drawContext, Mannequin$Act mannequin$Act, float f, int n, int n2, int n3, float f2, float f3, float f4, boolean bl) {
        block3: {
            Mannequin$Act mannequin$Act2;
            LocalSpeckSink localSpeckSink;
            block2: {
                float f5 = MathHelper.method_15362((double)f3);
                int n4 = PreviewScene.a;
                float f6 = MathHelper.method_15374((double)f3);
                float f7 = MathHelper.method_15362((double)f4);
                float f8 = MathHelper.method_15374((double)f4);
                localSpeckSink = (arg_0, arg_1, arg_2, arg_3, arg_4) -> Mannequin.lambda$paint$0(f5, f6, f7, f8, bl, drawContext, n, n2, n3, f2, arg_0, arg_1, arg_2, arg_3, arg_4);
                mannequin$Act2 = mannequin$Act;
                if (n4 != 0) break block2;
                if (mannequin$Act2 == null) break block3;
                mannequin$Act2 = mannequin$Act;
            }
            mannequin$Act2.specks(localSpeckSink, false);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void cape(DrawContext drawContext, int n, int n2, int n3, float f, float f2, float f3, boolean bl) {
        int n4;
        int n5;
        int n6;
        int n7;
        Identifier identifier;
        block36: {
            int n8;
            block37: {
                int n9;
                float f4;
                float f5;
                float f6;
                int n10;
                block34: {
                    float f7;
                    block35: {
                        float f8;
                        float f9;
                        block32: {
                            block33: {
                                Identifier identifier2;
                                block26: {
                                    block27: {
                                        String string;
                                        block30: {
                                            String string2;
                                            block31: {
                                                block29: {
                                                    block28: {
                                                        identifier = capeOverride;
                                                        n10 = PreviewScene.a;
                                                        identifier2 = identifier;
                                                        if (n10 != 0) break block26;
                                                        if (identifier2 != null) break block27;
                                                        string2 = Cosmetics.shownCape();
                                                        string = string2;
                                                        if (n10 != 0) break block28;
                                                        if (string == null) return;
                                                        string = string2;
                                                    }
                                                    if (n10 != 0) break block29;
                                                    if (string.isBlank()) return;
                                                    string = string2;
                                                }
                                                if (n10 != 0) break block30;
                                                if (!string.equals(a[2])) break block31;
                                                return;
                                            }
                                            string = string2;
                                        }
                                        identifier = CapeManager.texture(string);
                                    }
                                    identifier2 = identifier;
                                }
                                if (identifier2 == null) {
                                    return;
                                }
                                float f10 = MathHelper.method_15374((double)f2);
                                f9 = MathHelper.method_15362((double)f2);
                                f7 = -0.14f * f10;
                                f6 = 0.14f * f9;
                                f5 = MathHelper.method_15362((double)f3);
                                f4 = MathHelper.method_15374((double)f3);
                                float f11 = f10 * 0.0f + f6 * f5 + 1.46f * f4 - 0.0f;
                                f8 = f11 == 0.0f ? 0 : (f11 > 0.0f ? 1 : -1);
                                if (n10 != 0) break block32;
                                if (f8 <= 0) break block33;
                                f8 = 1.0f;
                                break block32;
                            }
                            f8 = 0.0f;
                        }
                        if (f8 != bl) {
                            return;
                        }
                        n7 = Math.round((float)n3 * 0.3125f * Math.abs(f9));
                        n9 = n7;
                        if (n10 != 0) break block34;
                        if (n9 >= 1) break block35;
                        return;
                    }
                    n9 = n - Math.round(f7 * (float)n3);
                }
                n8 = n9;
                n6 = n2 + Math.round((f - (1.46f * f5 - f6 * f4)) * (float)n3);
                n5 = n2 + Math.round((f - (0.42f * f5 - f6 * f4)) * (float)n3);
                n4 = n5;
                if (n10 != 0) break block36;
                if (n4 > n6) break block37;
                return;
            }
            n4 = n8 - n7;
        }
        int n11 = n4;
        int n12 = n7 * 2;
        drawContext.method_25302(RenderPipelines.field_56883, identifier, n11, n6, 1.0f, 1.0f, n12, n5 - n6, 10, 16, 64, 32);
    }

    
    private static void skin(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, float f, float f2, float f3) {
        PlayerEntityModel playerEntityModel;
        SkinTextures skinTextures;
        block23: {
            LoadedEntityModels loadedEntityModels;
            block22: {
                UUID uUID;
                int n6;
                MinecraftClient minecraftClient;
                block20: {
                    UUID uUID2;
                    block21: {
                        UUID uUID3;
                        block19: {
                            MinecraftClient minecraftClient2;
                            block17: {
                                block18: {
                                    block24: {
                                        minecraftClient = MinecraftClient.method_1551();
                                        n6 = PreviewScene.a;
                                        minecraftClient2 = minecraftClient;
                                        if (n6 != 0) break block17;
                                        if (minecraftClient2.method_1548() != null) break block18;
                                        break block24;
                                        catch (IllegalArgumentException illegalArgumentException) {
                                            throw illegalArgumentException;
                                        }
                                    }
                                    uUID3 = null;
                                    break block19;
                                }
                                minecraftClient2 = minecraftClient;
                            }
                            uUID3 = minecraftClient2.method_1548().method_44717();
                        }
                        uUID2 = uUID3;
                        uUID = uUID2;
                        if (n6 != 0) break block20;
                        if (uUID != null) break block21;
                        return;
                    }
                    uUID = uUID2;
                }
                skinTextures = SkinFetcher.skinFor(uUID);
                if (skinTextures == null) {
                    return;
                }
                loadedEntityModels = minecraftClient.method_31974();
                if (n6 != 0) break block22;
                if (loadedEntityModels == builtFrom) break block23;
                try {
                    wide = new PlayerEntityModel(loadedEntityModels.method_32072(EntityModelLayers.field_27577), false);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    return;
                }
            }
            builtFrom = loadedEntityModels;
            try {
                playerEntityModel = new PlayerEntityModel(loadedEntityModels.method_32072(SLIM_LAYER), true);
            }
            catch (RuntimeException runtimeException) {
                playerEntityModel = wide;
            }
            slim = playerEntityModel;
        }
        PlayerEntityModel playerEntityModel2 = skinTextures.comp_1629() == PlayerSkinType.field_41122 ? slim : wide;
        playerEntityModel = playerEntityModel2;
        float f4 = (float)n5 * 1.8f / 2.125f;
        float f5 = f * 2.125f / 1.8f;
        drawContext.method_70854(playerEntityModel, skinTextures.comp_1626().comp_3627(), f4, f3, f2, f5, n, n2, n + n3, n2 + n4);
    }

    private static void project(DrawContext drawContext, int n, int n2, int n3, float f, float f2, float f3, float f4, int n4) {
        int n5 = n - Math.round(f2 * (float)n3);
        int n6 = n2 + Math.round((f - f3) * (float)n3);
        int n7 = Math.max(1, Math.round(f4 * (float)n3 / 2.0f));
        drawContext.method_25294(n5 - n7, n6 - n7, n5 + n7, n6 + n7, n4);
    }

    private static void lambda$paint$0(float f, float f2, float f3, float f4, boolean bl, DrawContext drawContext, int n, int n2, int n3, float f5, float f6, float f7, float f8, float f9, int n4) {
        float f10;
        float f11;
        float f12;
        block5: {
            block6: {
                f12 = f6 * f - f8 * f2;
                float f13 = f6 * f2 + f8 * f;
                f11 = f7 * f3 - f13 * f4;
                int n5 = PreviewScene.a;
                float f14 = f7 * f4 + f13 * f3;
                float f15 = f14 - 0.0f;
                f10 = f15 == 0.0f ? 0 : (f15 > 0.0f ? 1 : -1);
                if (n5 != 0) break block5;
                if (f10 <= 0) break block6;
                f10 = 1.0f;
                break block5;
            }
            f10 = 0.0f;
        }
        if (f10 != bl) {
            return;
        }
        Mannequin.project(drawContext, n, n2, n3, f5, f12, f11, f9, n4);
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var13 = new String[3];
                    var11_1 = 0;
                    var10_2 = "H*\u001e\u000e$\u0005]K*\u0016\u001a\u0004U'\u0016\u0019\u0006\u0015(\u0010\u0019$Z";
                    var12_3 = "H*\u001e\u000e$\u0005]K*\u0016\u001a\u0004U'\u0016\u0019\u0006\u0015(\u0010\u0019$Z".length();
                    var9_4 = 11;
                    var8_5 = -1;
lbl7:

                    while (true) {
                        continue;
                        break;
                    }
lbl9:

                    while (true) {
                        var13[var11_1++] = new String(v0).intern();
                        if ((var8_5 += var9_4) < var12_3) {
                            var9_4 = var10_2.charAt(var8_5);
                            continue;
                        }
                        break block19;
                        break;
                    }
                    v1 = ++var8_5;
                    v2 = var10_2.substring(v1, v1 + var9_4).toCharArray();
                    v0 = v2;
                    v3 = v2.length;
                    var14_7 = 0;
                    do {
                        v0 = v0;
                        v4 = var14_7;
                        v5 = v0[v4];
                        switch (var14_7 % 7) {
                            case 0: {
                                v6 = 56;
                                break;
                            }
                            case 1: {
                                v6 = 70;
                                break;
                            }
                            case 2: {
                                v6 = 127;
                                break;
                            }
                            case 3: {
                                v6 = 119;
                                break;
                            }
                            case 4: {
                                v6 = 65;
                                break;
                            }
                            case 5: {
                                v6 = 119;
                                break;
                            }
                            default: {
                                v6 = 2;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_7;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_7);
                    while (true)
                }
                Mannequin.a = var13;
                var0_8 = 553822437164846628L;
                var6_9 = new long[4];
                var3_10 = 0;
                var4_11 = "\u00d4\u0094m\u0092n\u00c1$\u00d7\u00e9Up\u00ce\u00ecE\u00b0\u00fd";
                var5_12 = "\u00d4\u0094m\u0092n\u00c1$\u00d7\u00e9Up\u00ce\u00ecE\u00b0\u00fd".length();
                var2_13 = 0;
                while (true) {
                    var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                    v7 = var6_9;
                    v8 = var3_10++;
                    v9 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                    v10 = -1;
                    break block20;
                    break;
                }
lbl68:

                while (true) {
                    v7[v8] = v11;
                    if (var2_13 < var5_12) continue;
                    var4_11 = "\u00ddz\u009e\u001d\u0092@D\u00eb\u009c\u00a5\u00d0\u00d3\u0005\u00fd\u00c2L";
                    var5_12 = "\u00ddz\u009e\u001d\u0092@D\u00eb\u009c\u00a5\u00d0\u00d3\u0005\u00fd\u00c2L".length();
                    var2_13 = 0;
                    while (true) {
                        var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                        v7 = var6_9;
                        v8 = var3_10++;
                        v9 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                        v10 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl81:

                while (true) {
                    v7[v8] = v11;
                    if (var2_13 < var5_12) continue;
                    break block21;
                    break;
                }
            }
            v11 = v9 ^ var0_8;
            switch (v10) {
                default: {
                    continue;
                }
                case 0:
lbl92:

                continue;
            }
        }
        Mannequin.b = var6_9;
        Mannequin.c = new Integer[4];
        Mannequin.FULL_BRIGHT = Mannequin.a(29404, 1485197658279064L);
        Mannequin.CAPE_TH = Mannequin.a(5284, 1242900897342368483L);
        Mannequin.CAPE_TW = Mannequin.a(29824, 6629303329004079813L);
        Mannequin.EMPTY_SPECKS = new float[0];
        Mannequin.EMPTY_COLORS = new int[0];
        var8_6 = Mannequin.a;
        Mannequin.SLIM_LAYER = new EntityModelLayer(Identifier.method_60656((String)var8_6[0]), var8_6[1]);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3E47;
        if (c[n2] == null) {
            Mannequin.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
