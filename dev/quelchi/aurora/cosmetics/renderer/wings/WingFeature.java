package dev.quelchi.aurora.cosmetics.renderer.wings;

import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.content.Wings;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingFeature$1;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingFeature$Flap;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel$Bone;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel$Motion;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel$Pose;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModels;
import dev.quelchi.aurora.duck.BlockingState;
import dev.quelchi.aurora.module.Module;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionfc;
import org.joml.Vector3f;

public final class WingFeature
extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private static final Map<UUID, String> ON = new HashMap<UUID, String>();
    private static final Map<UUID, WingFeature$Flap> FLAPS = new HashMap<UUID, WingFeature$Flap>();

    public WingFeature(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> featureRendererContext) {
        super(featureRendererContext);
    }

    public static void register() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(WingFeature::lambda$register$0);
    }

    public static WingModel worn(UUID uUID) {
        String string;
        block23: {
            String string2;
            block22: {
                block24: {
                    Object object;
                    block25: {
                        boolean bl;
                        block20: {
                            block21: {
                                UUID uUID2;
                                block18: {
                                    block19: {
                                        bl = WingModel.a;
                                        uUID2 = uUID;
                                        if (bl) break block18;
                                        if (uUID2 != null) break block19;
                                        return null;
                                    }
                                    uUID2 = uUID;
                                }
                                string2 = Wings.modelFor(Cosmetics.wingsFor(uUID2));
                                string = string2;
                                if (bl) break block20;
                                if (string != null) break block21;
                                ON.remove(uUID);
                                if (!bl) break block22;
                            }
                            string = string2;
                        }
                        if (bl) break block23;
                        if (string.equals(ON.get(uUID))) break block22;
                        object = ON;
                        if (bl) break block24;
                        if (object.size() <= 256) break block25;
                        ON.clear();
                    }
                    object = ON.put(uUID, string2);
                }
                WingModels.retry(string2);
            }
            string = string2;
        }
        return WingModels.get(string);
    }

    private static WingFeature$Flap flap(UUID uUID, float f, float f2) {
        WingFeature$Flap wingFeature$Flap;
        long l;
        block6: {
            Object object;
            block7: {
                l = System.nanoTime();
                boolean bl = WingModel.a;
                wingFeature$Flap = FLAPS.get(uUID);
                if (wingFeature$Flap != null) break block6;
                object = FLAPS;
                if (bl) break block6;
                if (object.size() <= 256) break block7;
                FLAPS.clear();
            }
            wingFeature$Flap = new WingFeature$Flap();
            wingFeature$Flap.phase = (float)(uUID.hashCode() & 0x3FF) * 0.37f;
            wingFeature$Flap.speed = f;
            wingFeature$Flap.amp = f2;
            wingFeature$Flap.last = l;
            object = FLAPS.put(uUID, wingFeature$Flap);
        }
        float f3 = Math.min(0.1f, (float)(l - wingFeature$Flap.last) / 1.0E9f);
        wingFeature$Flap.last = l;
        float f4 = 1.0f - (float)Math.exp(-f3 * 4.0f);
        wingFeature$Flap.speed += (f - wingFeature$Flap.speed) * f4;
        wingFeature$Flap.amp += (f2 - wingFeature$Flap.amp) * f4;
        wingFeature$Flap.phase = (wingFeature$Flap.phase + wingFeature$Flap.speed * f3) % 6283.1855f;
        return wingFeature$Flap;
    }

    private static float skew(float f) {
        return (float)Math.sin((double)f + (double)0.4f * Math.sin(f));
    }

    public void render(MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, PlayerEntityRenderState playerEntityRenderState, float f, float f2) {
        boolean bl;
        boolean bl2;
        WingModel$Pose wingModel$Pose;
        WingModel wingModel;
        block31: {
            block30: {
                PlayerEntityRenderState playerEntityRenderState2;
                boolean bl3;
                boolean bl4;
                block29: {
                    boolean bl5;
                    block28: {
                        block27: {
                            block26: {
                                WingModel wingModel2;
                                UUID uUID;
                                block24: {
                                    block25: {
                                        PlayerEntityRenderState playerEntityRenderState3;
                                        block22: {
                                            block23: {
                                                bl4 = WingModel.a;
                                                playerEntityRenderState3 = playerEntityRenderState;
                                                if (bl4) break block22;
                                                if (!playerEntityRenderState3.field_53333) break block23;
                                                return;
                                            }
                                            playerEntityRenderState3 = playerEntityRenderState;
                                        }
                                        uUID = ((BlockingState)playerEntityRenderState3).aurora$getUuid();
                                        wingModel = WingFeature.worn(uUID);
                                        wingModel2 = wingModel;
                                        if (bl4) break block24;
                                        if (wingModel2 != null) break block25;
                                        return;
                                    }
                                    wingModel2 = wingModel;
                                }
                                wingModel$Pose = WingFeature.pose(wingModel2, uUID, Math.min(1.0f, playerEntityRenderState.field_53451), playerEntityRenderState.field_53411, playerEntityRenderState.field_53410);
                                matrixStack.method_22903();
                                ((PlayerEntityModel)this.method_17165()).field_3391.method_22703(matrixStack);
                                matrixStack.method_22905(0.0625f, 0.0625f, 0.0625f);
                                matrixStack.method_22907((Quaternionfc)RotationAxis.field_40718.rotationDegrees(180.0f));
                                bl3 = playerEntityRenderState.field_53418.method_31574(Items.field_8833);
                                bl5 = playerEntityRenderState.field_53418.method_7960();
                                if (bl4) break block26;
                                if (bl5) break block27;
                                bl5 = bl3;
                            }
                            if (bl4) break block28;
                            if (bl5) break block27;
                            bl5 = true;
                            break block28;
                        }
                        bl5 = false;
                    }
                    bl2 = bl5;
                    playerEntityRenderState2 = playerEntityRenderState;
                    if (bl4) break block29;
                    if (!playerEntityRenderState2.field_53532) break block30;
                    playerEntityRenderState2 = playerEntityRenderState;
                }
                if (playerEntityRenderState2.field_53520.comp_1627() == null) break block30;
                bl = bl3;
                if (bl4) break block31;
                if (bl) break block30;
                bl = true;
                break block31;
            }
            bl = false;
        }
        boolean bl6 = bl;
        MatrixStack matrixStack2 = matrixStack;
        float f3 = 0.0f;
        float f4 = -24.0f;
        float f5 = bl2 ? 3.0f : 2.0f;
        float f6 = bl6 ? 2.0f : 0.0f;
        matrixStack2.method_46416(f3, f4, f5 + f6 - wingModel.front());
        orderedRenderCommandQueue.method_73483(matrixStack, RenderLayers.method_76000((Identifier)wingModel.texture()), (arg_0, arg_1) -> WingFeature.lambda$render$1(wingModel, n, wingModel$Pose, arg_0, arg_1));
        matrixStack.method_22909();
    }

    public static WingModel$Pose pose(WingModel wingModel, UUID uUID, float f, boolean bl, boolean bl2) {
        WingModel$Pose wingModel$Pose;
        block15: {
            boolean bl3;
            block17: {
                block16: {
                    float f2;
                    float f3;
                    float f4;
                    boolean bl4;
                    WingModel$Motion wingModel$Motion;
                    block12: {
                        float f5;
                        block14: {
                            block13: {
                                wingModel$Motion = wingModel.motion;
                                bl4 = WingModel.a;
                                switch (WingFeature$1.$SwitchMap$dev$quelchi$aurora$cosmetics$renderer$wings$WingModel$Motion[wingModel$Motion.ordinal()]) {
                                    case 1: {
                                        double d = (double)System.nanoTime() / 1.0E9 + (double)(uUID.hashCode() & 0xFF) * 0.1;
                                        f4 = Math.max(f, (float)Math.max(0.0, Math.min(1.0, (Math.sin(d * 1.3) + 0.15) * 3.0)));
                                        f3 = 13.0f + f * 4.0f;
                                        f2 = 50.0f * f4;
                                        if (!bl4) break;
                                    }
                                    case 2: 
                                    case 3: {
                                        f3 = 1.0f + f * 1.5f;
                                        f2 = 16.0f + f * 16.0f;
                                        if (!bl4) break;
                                    }
                                    default: {
                                        f3 = 1.1f + f * 1.7f;
                                        f2 = 10.0f + f * 14.0f;
                                    }
                                }
                                if (!bl) break block12;
                                if (wingModel$Motion != WingModel$Motion.FLUTTER) break block13;
                                f5 = 4.0f;
                                break block14;
                            }
                            f5 = 1.4f;
                        }
                        f3 = f5;
                        f2 = 3.0f;
                    }
                    WingFeature$Flap wingFeature$Flap = WingFeature.flap(uUID, f3, f2);
                    float f6 = wingFeature$Flap.phase;
                    f4 = wingFeature$Flap.amp;
                    wingModel$Pose = (arg_0, arg_1) -> WingFeature.lambda$pose$2(bl, bl2, wingModel$Motion, f4, f6, arg_0, arg_1);
                    if (!Module.a) break block15;
                    if (!bl4) break block16;
                    bl3 = false;
                    break block17;
                }
                bl3 = true;
            }
            WingModel.a = bl3;
        }
        return wingModel$Pose;
    }

    
    private static void lambda$pose$2(boolean var0, boolean var1_1, WingModel$Motion var2_2, float var3_3, float var4_4, WingModel$Bone var5_5, Vector3f var6_6) {
        block44: {
            var7_7 = WingModel.a;
            v0 = var5_5;
            if (!var7_7) {
                if (v0.side == 0) {
                    return;
                }
                v0 = var5_5;
            }
            var8_8 = v0.side;
            var6_6.y += (var0 != false ? var8_8 * Math.abs(var5_5.rest.y) * 0.85f : 0.0f) + (var1_1 != false ? -var8_8 * 18.0f : 0.0f);
            v1 = WingFeature$1.$SwitchMap$dev$quelchi$aurora$cosmetics$renderer$wings$WingModel$Motion[var2_2.ordinal()];
            if (var7_7) // goto
            switch (v1) {
                case 1: {
                    v1 = (int)var5_5.tip;
lbl14:

                    if (v1 != 0) {
                        var6_6.y -= var8_8 * var3_3 * 0.15f * (float)Math.sin(var4_4 - 1.2f);
                        if (!var7_7) break;
                    }
                    var6_6.y -= var8_8 * var3_3 * (0.5f + 0.5f * (float)Math.sin(var4_4));
                    if (!var7_7) break;
                }
                case 2: {
                    var9_9 = (float)Math.sin((double)(var4_4 - (var5_5.tip != false ? 1.1f : 0.0f)) + 0.75 * Math.sin(var4_4 - (var5_5.tip != false ? 1.1f : 0.0f)));
                    if (var7_7) // goto
                    if (!var5_5.tip) // goto
                    var6_6.y -= var8_8 * var3_3 * 1.1f * var9_9;
                    if (!var7_7) // goto
lbl25:

                    var6_6.y -= var8_8 * var3_3 * var9_9;
lbl26:

                    var6_6.z += var8_8 * var3_3 * 0.6f * WingFeature.skew(var4_4 - 0.4f);
lbl27:

                    if (!var7_7) break;
                }
                case 3: {
                    var9_9 = 0.5f - 0.5f * WingFeature.skew(var4_4 - (var5_5.tip != false ? 0.7f : 0.0f));
                    if (var7_7) // goto
                    if (!var5_5.tip) // goto
                    var6_6.y -= var8_8 * var3_3 * 0.25f * var9_9;
                    var6_6.z += var8_8 * var3_3 * 1.2f * var9_9;
lbl34:

                    if (!var7_7) // goto
lbl35:

                    var6_6.y -= var8_8 * var3_3 * 0.5f * var9_9;
lbl36:

                    if (!var7_7) break;
                }
                default: {
                    var9_9 = WingFeature.skew(var4_4 - (var5_5.tip != false ? 0.9f : 0.0f));
                    v2 = var8_8;
                    v3 = var3_3;
                    if (!var7_7) {
                        v2 = v2 * v3 * var9_9;
                        v3 = var5_5.tip != false ? 0.8f : 1.0f;
                    }
                    var6_6.y -= v2 * v3;
                    v4 = var5_5.tip;
                    if (!var7_7) {
                        if (v4 != 0) break;
                        var6_6.z += var8_8 * var3_3 * 0.5f * WingFeature.skew(var4_4 - 0.4f);
                        break;
                    }
                    break block44;
                }
            }
            v4 = (cfr_temp_0 = var8_8 * (var5_5.rest.y + var6_6.y) - 5.0f) == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
        }
        if (v4 <= 0) return;
        var6_6.y = var8_8 * 5.0f - var5_5.rest.y;
    }

    private static void lambda$render$1(WingModel wingModel, int n, WingModel$Pose wingModel$Pose, MatrixStack.Entry entry, VertexConsumer vertexConsumer) {
        wingModel.emit(entry, vertexConsumer, n, wingModel$Pose);
    }

    private static void lambda$register$0(EntityType entityType, LivingEntityRenderer livingEntityRenderer, LivingEntityFeatureRendererRegistrationCallback.RegistrationHelper registrationHelper, EntityRendererFactory.Context context) {
        block5: {
            LivingEntityRenderer livingEntityRenderer2;
            block4: {
                boolean bl = WingModel.a;
                livingEntityRenderer2 = livingEntityRenderer;
                if (bl) break block4;
                if (!(livingEntityRenderer2 instanceof PlayerEntityRenderer)) break block5;
                livingEntityRenderer2 = livingEntityRenderer;
            }
            PlayerEntityRenderer playerEntityRenderer = (PlayerEntityRenderer)livingEntityRenderer2;
            registrationHelper.register((FeatureRenderer)new WingFeature((FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel>)((FeatureRendererContext)playerEntityRenderer)));
        }
    }
}
