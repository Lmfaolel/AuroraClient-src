package dev.quelchi.aurora.popchams;

import dev.quelchi.aurora.module.modules.PopChamsModule;
import dev.quelchi.aurora.popchams.CapturedPlayer;
import dev.quelchi.aurora.popchams.ChamConfigState;
import dev.quelchi.aurora.popchams.ChamExtractor$BodyPart;
import dev.quelchi.aurora.popchams.Layers;
import dev.quelchi.aurora.popchams.PositionHandler;
import dev.quelchi.aurora.popchams.WireframePartExtractor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.joml.Quaternionfc;

public final class ChamExtractor {
    private static final int LIGHT;
    private static final ChamExtractor INSTANCE;
    private final WireframePartExtractor wireframeExtractor = new WireframePartExtractor();
    private final ChamConfigState state = new ChamConfigState();
    private static final long[] a;
    private static final Integer[] b;

    public static ChamExtractor get() {
        return INSTANCE;
    }

    private ChamExtractor() {
    }

    public void extract(MatrixStack matrixStack, float f) {
        block22: {
            boolean bl;
            PopChamsModule popChamsModule;
            block27: {
                block20: {
                    boolean bl2;
                    block21: {
                        PopChamsModule popChamsModule2;
                        block19: {
                            popChamsModule = PopChamsModule.get();
                            bl = PositionHandler.a;
                            popChamsModule2 = popChamsModule;
                            if (bl) break block19;
                            if (popChamsModule2 == null) break block20;
                            popChamsModule2 = popChamsModule;
                        }
                        bl2 = popChamsModule2.isEnabled();
                        if (bl) break block21;
                        if (!bl2) break block20;
                        bl2 = PositionHandler.getPositions().isEmpty();
                    }
                    if (!bl2) break block27;
                }
                return;
            }
            VertexConsumerProvider.Immediate immediate = MinecraftClient.method_1551().method_22940().method_23000();
            for (CapturedPlayer capturedPlayer : PositionHandler.getPositions()) {
                block26: {
                    ChamExtractor chamExtractor;
                    block25: {
                        ChamConfigState chamConfigState;
                        block23: {
                            block24: {
                                this.state.updateConfigRenderState(popChamsModule, capturedPlayer, f);
                                if (bl) break block22;
                                chamConfigState = this.state;
                                if (bl) break block23;
                                if (!chamConfigState.renderFillModel) break block24;
                                this.renderPlayerModel(matrixStack, capturedPlayer, immediate);
                            }
                            chamExtractor = this;
                            if (bl) break block25;
                            chamConfigState = chamExtractor.state;
                        }
                        if (!chamConfigState.renderWireframe) break block26;
                        chamExtractor = this;
                    }
                    chamExtractor.renderWireframe(matrixStack, capturedPlayer, immediate);
                }
                if (!bl) continue;
            }
            immediate.method_22993();
        }
    }

    private void renderPlayerModel(MatrixStack matrixStack, CapturedPlayer capturedPlayer, VertexConsumerProvider.Immediate immediate) {
        block11: {
            block12: {
                int n;
                VertexConsumer vertexConsumer;
                boolean bl;
                PlayerEntityModel playerEntityModel;
                block10: {
                    block9: {
                        this.setupAndPush(matrixStack, capturedPlayer);
                        playerEntityModel = capturedPlayer.model;
                        bl = PositionHandler.a;
                        this.setupRenderState(capturedPlayer.state, playerEntityModel);
                        this.applyFlyingTransforms(capturedPlayer, matrixStack);
                        vertexConsumer = immediate.method_73477(Layers.FILL);
                        n = this.alphaColor(this.state.filledColor, this.state.alpha);
                        if (bl) break block9;
                        if (this.state.disperse) break block10;
                        playerEntityModel.method_62100(matrixStack, vertexConsumer, ChamExtractor.a(5604, 1010875614403450372L), OverlayTexture.field_21444, n);
                    }
                    if (!bl) break block12;
                }
                for (ChamExtractor$BodyPart chamExtractor$BodyPart : ChamExtractor$BodyPart.VALUES) {
                    Vec3d vec3d = this.getPartDisplacement(capturedPlayer, chamExtractor$BodyPart);
                    matrixStack.method_22903();
                    if (!bl) {
                        matrixStack.method_22904(vec3d.method_10216(), vec3d.method_10214(), vec3d.method_10215());
                        this.renderModelPart(playerEntityModel, chamExtractor$BodyPart, matrixStack, vertexConsumer, n);
                        matrixStack.method_22909();
                        if (!bl) continue;
                        break;
                    }
                    break block11;
                }
            }
            matrixStack.method_22909();
        }
    }

    private void renderWireframe(MatrixStack matrixStack, CapturedPlayer capturedPlayer, VertexConsumerProvider.Immediate immediate) {
        block4: {
            this.setupAndPush(matrixStack, capturedPlayer);
            PlayerEntityModel playerEntityModel = capturedPlayer.model;
            this.setupRenderState(capturedPlayer.state, playerEntityModel);
            this.applyFlyingTransforms(capturedPlayer, matrixStack);
            VertexConsumer vertexConsumer = immediate.method_73477(Layers.WIREFRAME);
            int n = this.alphaColor(this.state.wireframeColor, this.state.alpha);
            ChamExtractor$BodyPart[] chamExtractor$BodyPartArray = ChamExtractor$BodyPart.VALUES;
            int n2 = chamExtractor$BodyPartArray.length;
            boolean bl = PositionHandler.a;
            for (int i = 0; i < n2; ++i) {
                ChamExtractor$BodyPart chamExtractor$BodyPart = chamExtractor$BodyPartArray[i];
                Vec3d vec3d = this.getPartDisplacement(capturedPlayer, chamExtractor$BodyPart);
                matrixStack.method_22903();
                matrixStack.method_22904(vec3d.method_10216(), vec3d.method_10214(), vec3d.method_10215());
                if (!bl) {
                    this.renderWireframePart(playerEntityModel, chamExtractor$BodyPart, matrixStack, vertexConsumer, n);
                    matrixStack.method_22909();
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            matrixStack.method_22909();
        }
    }

    private int alphaColor(int n, float f) {
        int n2 = MathHelper.method_15340((int)((int)((float)(n >> 24 & 0xFF) * f)), (int)0, (int)255);
        return n2 << 24 | n & ChamExtractor.a(11916, 529012759789759853L);
    }

    
    private void renderModelPart(PlayerEntityModel playerEntityModel, ChamExtractor$BodyPart chamExtractor$BodyPart, MatrixStack matrixStack, VertexConsumer vertexConsumer, int n) {
        boolean bl = PositionHandler.a;
        switch (chamExtractor$BodyPart.ordinal()) {
            case 0: {
                playerEntityModel.field_3398.method_22699(matrixStack, vertexConsumer, ChamExtractor.a(7712, 8224150507594603971L), OverlayTexture.field_21444, n);
                if (!bl) return;
            }
            case 1: {
                playerEntityModel.field_3391.method_22699(matrixStack, vertexConsumer, ChamExtractor.a(7712, 8224150507594603971L), OverlayTexture.field_21444, n);
                if (!bl) return;
            }
            case 2: {
                playerEntityModel.field_27433.method_22699(matrixStack, vertexConsumer, ChamExtractor.a(7712, 8224150507594603971L), OverlayTexture.field_21444, n);
                if (!bl) return;
            }
            case 3: {
                playerEntityModel.field_3401.method_22699(matrixStack, vertexConsumer, ChamExtractor.a(7712, 8224150507594603971L), OverlayTexture.field_21444, n);
                if (!bl) return;
            }
            case 4: {
                playerEntityModel.field_3397.method_22699(matrixStack, vertexConsumer, ChamExtractor.a(7712, 8224150507594603971L), OverlayTexture.field_21444, n);
                if (!bl) return;
            }
            case 5: {
                playerEntityModel.field_3392.method_22699(matrixStack, vertexConsumer, ChamExtractor.a(7712, 8224150507594603971L), OverlayTexture.field_21444, n);
                return;
            }
        }
    }

    
    private void renderWireframePart(PlayerEntityModel var1_1, ChamExtractor$BodyPart var2_2, MatrixStack var3_3, VertexConsumer var4_4, int var5_5) {
        var6_6 = PositionHandler.a;
        var7_7 = this.state.wireframeThickness;
        if (var6_6) // goto
        switch (var2_2.ordinal()) {
            case 0: {
                this.wireframeExtractor.extractModelPart(var1_1.field_3398, var3_3, var4_4, var5_5, var7_7);
lbl7:

                if (var6_6 == false) return;
            }
            case 1: {
                this.wireframeExtractor.extractModelPart(var1_1.field_3391, var3_3, var4_4, var5_5, var7_7);
                if (var6_6 == false) return;
            }
            case 2: {
                this.wireframeExtractor.extractModelPart(var1_1.field_27433, var3_3, var4_4, var5_5, var7_7);
                if (var6_6 == false) return;
            }
            case 3: {
                this.wireframeExtractor.extractModelPart(var1_1.field_3401, var3_3, var4_4, var5_5, var7_7);
                if (var6_6 == false) return;
            }
            case 4: {
                this.wireframeExtractor.extractModelPart(var1_1.field_3397, var3_3, var4_4, var5_5, var7_7);
                if (var6_6 == false) return;
            }
            case 5: {
                this.wireframeExtractor.extractModelPart(var1_1.field_3392, var3_3, var4_4, var5_5, var7_7);
                return;
            }
        }
    }

    
    private Vec3d getPartDisplacement(CapturedPlayer capturedPlayer, ChamExtractor$BodyPart chamExtractor$BodyPart) {
        if (!this.state.disperse) {
            return Vec3d.field_1353;
        }
        long l = capturedPlayer.method_5667().getMostSignificantBits() ^ capturedPlayer.method_5667().getLeastSignificantBits() ^ (long)chamExtractor$BodyPart.ordinal();
        Random random = Random.method_43049((long)l);
        Vec3d vec3d = switch (chamExtractor$BodyPart.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> new Vec3d(0.0, -1.0, 0.0);
            case 1 -> Vec3d.field_1353;
            case 2 -> new Vec3d(1.0, 0.0, 0.0);
            case 3 -> new Vec3d(-1.0, 0.0, 0.0);
            case 4 -> new Vec3d(0.3, 1.0, 0.0);
            case 5 -> new Vec3d(-0.3, 1.0, 0.0);
        };
        Vec3d vec3d2 = new Vec3d((random.method_43058() - 0.5) * 0.5, (random.method_43058() - 0.5) * 0.5, (random.method_43058() - 0.5) * 0.5);
        return vec3d.method_1019(vec3d2).method_1029().method_1021((double)this.state.displacementAmount);
    }

    private void setupRenderState(PlayerEntityRenderState playerEntityRenderState, PlayerEntityModel playerEntityModel) {
        playerEntityRenderState.field_53545 = false;
        playerEntityRenderState.field_53546 = false;
        playerEntityRenderState.field_53530 = false;
        playerEntityRenderState.field_53531 = false;
        playerEntityRenderState.field_53544 = false;
        playerEntityRenderState.field_53543 = false;
        playerEntityModel.field_3482.field_3665 = false;
        playerEntityModel.field_3479.field_3665 = false;
        playerEntityModel.field_3484.field_3665 = false;
        playerEntityModel.field_3486.field_3665 = false;
        playerEntityModel.field_3483.field_3665 = false;
        playerEntityModel.field_3394.field_3665 = false;
    }

    private void setupAndPush(MatrixStack matrixStack, CapturedPlayer capturedPlayer) {
        matrixStack.method_22903();
        Vec3d vec3d = MinecraftClient.method_1551().method_1561().field_4686.method_71156();
        matrixStack.method_22904(capturedPlayer.method_23317() - vec3d.field_1352, capturedPlayer.method_23318() - vec3d.field_1351, capturedPlayer.method_23321() - vec3d.field_1350);
        matrixStack.method_22907((Quaternionfc)RotationAxis.field_40716.rotationDegrees(180.0f - capturedPlayer.field_6283));
        matrixStack.method_22905(-1.0f, -1.0f, 1.0f);
        matrixStack.method_22905(0.8f, 0.9f, 0.8f);
        matrixStack.method_46416(0.0f, -1.5f, 0.0f);
    }

    private void applyFlyingTransforms(CapturedPlayer capturedPlayer, MatrixStack matrixStack) {
        PlayerEntityRenderState playerEntityRenderState;
        block7: {
            block8: {
                boolean bl = PositionHandler.a;
                playerEntityRenderState = capturedPlayer.state;
                if (bl) break block7;
                if (playerEntityRenderState.field_53459) break block8;
                matrixStack.method_22907((Quaternionfc)RotationAxis.field_40714.rotationDegrees(capturedPlayer.glidingProcess * (90.0f - capturedPlayer.statePitch)));
            }
            playerEntityRenderState = capturedPlayer.state;
        }
        if (playerEntityRenderState.field_53535) {
            matrixStack.method_22907((Quaternionfc)RotationAxis.field_40716.rotation(capturedPlayer.flyingRotation));
        }
    }

    
    static {
        block8: {
            block7: {
                var0 = 8917031961692950235L;
                var6_1 = new long[4];
                var3_2 = 0;
                var4_3 = "\u0083x\u00d1q\u00eb\u00ec\u0018/\u00c3\u0081\u008b;C\u0096\\I";
                var5_4 = "\u0083x\u00d1q\u00eb\u00ec\u0018/\u00c3\u0081\u008b;C\u0096\\I".length();
                var2_5 = 0;
                while (true) {
                    var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                    v0 = var6_1;
                    v1 = var3_2++;
                    v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                    v3 = -1;
                    break block7;
                    break;
                }
lbl14:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    var4_3 = "\r^\u00ad\u00db\"m\u00e6\u00ff\u00fe\u00810\u00d4/\u00f5\u0013\u00e8";
                    var5_4 = "\r^\u00ad\u00db\"m\u00e6\u00ff\u00fe\u00810\u00d4/\u00f5\u0013\u00e8".length();
                    var2_5 = 0;
                    while (true) {
                        var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                        v0 = var6_1;
                        v1 = var3_2++;
                        v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                        v3 = 0;
                        break block7;
                        break;
                    }
                    break;
                }
lbl27:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    break block8;
                    break;
                }
            }
            v4 = v2 ^ var0;
            switch (v3) {
                default: {
                    continue;
                }
                case 0:
lbl38:

                continue;
            }
        }
        ChamExtractor.a = var6_1;
        ChamExtractor.b = new Integer[4];
        ChamExtractor.LIGHT = ChamExtractor.a(27446, 7165347146997082324L);
        ChamExtractor.INSTANCE = new ChamExtractor();
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x43E0;
        if (b[n2] == null) {
            ChamExtractor.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
