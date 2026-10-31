package dev.quelchi.aurora.popchams;

import com.mojang.authlib.GameProfile;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.popchams.PositionHandler;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public final class CapturedPlayer
extends OtherClientPlayerEntity {
    public float statePitch;
    public float glidingProcess;
    public float flyingRotation;
    public float animPos;
    public PlayerEntityModel model;
    public PlayerEntityRenderState state;
    private static final String a;

    public CapturedPlayer(ClientWorld clientWorld, PlayerEntity playerEntity) {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    super(clientWorld, new GameProfile(UUID.randomUUID(), a));
                    this.method_5719((Entity)playerEntity);
                    this.field_6014 = playerEntity.field_6014;
                    this.field_6036 = playerEntity.field_6036;
                    this.field_5969 = playerEntity.field_5969;
                    this.field_5982 = playerEntity.field_5982;
                    this.field_6004 = playerEntity.field_6004;
                    this.field_42108.method_48567(playerEntity.field_42108.method_48566());
                    this.animPos = playerEntity.field_42108.method_48569();
                    MinecraftClient minecraftClient = MinecraftClient.method_1551();
                    this.state = (PlayerEntityRenderState)minecraftClient.method_1561().method_3953((Entity)this).method_62425((Entity)this, 0.0f);
                    this.state.field_63604 = playerEntity.field_6251;
                    this.state.field_53334 = playerEntity.method_5715();
                    this.state.field_53534 = playerEntity.method_6003();
                    this.flyingRotation = this.state.field_53521;
                    this.statePitch = this.state.field_53448;
                    this.glidingProcess = this.state.method_64259();
                    boolean bl2 = PositionHandler.a;
                    PlayerEntityModel playerEntityModel = (PlayerEntityModel)((LivingEntityRenderer)minecraftClient.method_1561().method_3953((Entity)playerEntity)).method_4038();
                    this.model = new PlayerEntityModel(minecraftClient.method_31974().method_32072(EntityModelLayers.field_27577), false);
                    CapturedPlayer.copyModelPartTransform(this.model.field_3398, playerEntityModel.field_3398);
                    CapturedPlayer.copyModelPartTransform(this.model.field_3394, playerEntityModel.field_3394);
                    CapturedPlayer.copyModelPartTransform(this.model.field_3391, playerEntityModel.field_3391);
                    CapturedPlayer.copyModelPartTransform(this.model.field_27433, playerEntityModel.field_27433);
                    CapturedPlayer.copyModelPartTransform(this.model.field_3401, playerEntityModel.field_3401);
                    CapturedPlayer.copyModelPartTransform(this.model.field_3397, playerEntityModel.field_3397);
                    CapturedPlayer.copyModelPartTransform(this.model.field_3392, playerEntityModel.field_3392);
                    this.field_6283 = playerEntity.field_6283;
                    this.field_6220 = playerEntity.field_6220;
                    this.field_6241 = playerEntity.field_6241;
                    this.field_6259 = playerEntity.field_6259;
                    this.field_6251 = playerEntity.field_6251;
                    this.field_6279 = playerEntity.field_6279;
                    this.method_5660(playerEntity.method_5715());
                    this.method_18380(playerEntity.method_18376());
                    if (!bl2) break block4;
                    if (!Module.a) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    private static void copyModelPartTransform(ModelPart modelPart, ModelPart modelPart2) {
        modelPart.field_3654 = modelPart2.field_3654;
        modelPart.field_3675 = modelPart2.field_3675;
        modelPart.field_3674 = modelPart2.field_3674;
        modelPart.field_3657 = modelPart2.field_3657;
        modelPart.field_3656 = modelPart2.field_3656;
        modelPart.field_3655 = modelPart2.field_3655;
        modelPart.field_37938 = modelPart2.field_37938;
        modelPart.field_37939 = modelPart2.field_37939;
        modelPart.field_37940 = modelPart2.field_37940;
    }

    public void tickAge(int n) {
        block5: {
            CapturedPlayer capturedPlayer;
            block4: {
                boolean bl = PositionHandler.a;
                capturedPlayer = this;
                if (bl) break block4;
                ++capturedPlayer.field_6012;
                if (this.field_6012 < n) break block5;
                capturedPlayer = this;
            }
            capturedPlayer.method_5650(Entity.RemovalReason.field_26999);
            PositionHandler.getPositions().remove((Object)this);
        }
    }

    public boolean method_7325() {
        return false;
    }

    public boolean method_68878() {
        return false;
    }

    public boolean method_5810() {
        return false;
    }

    public boolean method_30948(Entity entity) {
        return false;
    }

    public boolean method_30949(Entity entity) {
        return false;
    }

    protected void method_6087(Entity entity) {
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "\u001eV)q\u0015U\u00059\u0017\ti\u0001^\u0005/".toCharArray();
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
                    case 0 -> 93;
                    case 1 -> 55;
                    case 2 -> 89;
                    case 3 -> 5;
                    case 4 -> 96;
                    case 5 -> 39;
                    default -> 96;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
    }
}
