package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.cosmetics.renderer.KillEffects;
import dev.quelchi.aurora.module.modules.AutoDeafenModule;
import dev.quelchi.aurora.module.modules.AutoTextModule;
import dev.quelchi.aurora.module.modules.CrosshairAddonsModule;
import dev.quelchi.aurora.optimize.AnchorPrediction;
import dev.quelchi.aurora.optimize.CrystalOptimizer;
import dev.quelchi.aurora.prediction.AnchorTickHold;
import dev.quelchi.aurora.prediction.InventoryHold;
import dev.quelchi.aurora.prediction.MeleePrediction;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.ac.InputAudit;
import dev.quelchi.aurora.shield.ShieldStates;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ClientPlayerInteractionManager.class})
public abstract class ClientPlayerInteractionManagerMixin {
    @Inject(method={"method_2906(IIILnet/minecraft/class_1713;Lnet/minecraft/class_1657;)V"}, at={@At(value="HEAD")})
    private void aurora$handsBeforeClick(int n, int n2, int n3, SlotActionType slotActionType, PlayerEntity playerEntity, CallbackInfo callbackInfo) {
        InventoryHold.beforeClick();
        InputAudit.onClickSlot(n2, n3, slotActionType, playerEntity);
    }

    @Inject(method={"method_2906(IIILnet/minecraft/class_1713;Lnet/minecraft/class_1657;)V"}, at={@At(value="TAIL")})
    private void aurora$handsAfterClick(int n, int n2, int n3, SlotActionType slotActionType, PlayerEntity playerEntity, CallbackInfo callbackInfo) {
        InventoryHold.afterClick();
    }

    @Inject(method={"method_2918"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1657;method_7324(Lnet/minecraft/class_1297;)V", shift=At.Shift.AFTER)})
    private void aurora$crystalOptimizer(PlayerEntity playerEntity, Entity entity, CallbackInfo callbackInfo) {
        Prediction.onAttack(entity);
        CrystalOptimizer.onAttack(entity);
        MeleePrediction.onMelee(playerEntity, entity);
    }

    @Inject(method={"method_2918"}, at={@At(value="HEAD")})
    private void divine$attackCombat(PlayerEntity playerEntity, Entity entity, CallbackInfo callbackInfo) {
        Prediction.syncSelectedSlot();
        AutoTextModule autoTextModule = AutoTextModule.get();
        if (autoTextModule != null && autoTextModule.isEnabled()) {
            autoTextModule.onCombat();
        }
        CrosshairAddonsModule.onMeleeHit();
        KillEffects.onCombat();
        if (entity instanceof PlayerEntity) {
            PlayerEntity playerEntity2 = (PlayerEntity)entity;
            ShieldStates.onAttack(playerEntity2);
            AutoDeafenModule.onFight(playerEntity2);
            KillEffects.onAttack(playerEntity2.method_5667());
        }
    }

    @Inject(method={"method_2896"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$anchorPredictHead(ClientPlayerEntity clientPlayerEntity, Hand hand, BlockHitResult blockHitResult, CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        if (AnchorTickHold.hold(clientPlayerEntity, hand, blockHitResult)) {
            callbackInfoReturnable.setReturnValue((Object)ActionResult.field_5814);
            return;
        }
        Prediction.syncSelectedSlot();
        if (AnchorPrediction.shouldBlockPlacement(hand, blockHitResult)) {
            callbackInfoReturnable.setReturnValue((Object)ActionResult.field_5814);
            return;
        }
        Prediction.onInteractHead((PlayerEntity)clientPlayerEntity, hand, blockHitResult);
        ClientWorld clientWorld = MinecraftClient.method_1551().field_1687;
        if (clientWorld != null && blockHitResult != null) {
            AnchorPrediction.handleFastInteract(clientWorld, hand, blockHitResult);
        }
    }

    @Inject(method={"method_2896"}, at={@At(value="RETURN")})
    private void aurora$anchorPredictReturn(ClientPlayerEntity clientPlayerEntity, Hand hand, BlockHitResult blockHitResult, CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        ActionResult actionResult = (ActionResult)callbackInfoReturnable.getReturnValue();
        Prediction.onInteractReturn(actionResult);
        if (actionResult == null || !actionResult.method_23665()) {
            return;
        }
        ClientWorld clientWorld = MinecraftClient.method_1551().field_1687;
        if (clientWorld != null && blockHitResult != null) {
            AnchorPrediction.handleInteract(clientWorld, hand, blockHitResult);
        }
    }

    @Inject(method={"method_2919"}, at={@At(value="HEAD")})
    private void aurora$syncSlotBeforeUse(PlayerEntity playerEntity, Hand hand, CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        Prediction.syncSelectedSlot();
    }

    @Inject(method={"method_2910"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$noBreakPredicted(BlockPos blockPos, Direction direction, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (ClientPlayerInteractionManagerMixin.aurora$creativeOnPrediction(blockPos)) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }

    @Inject(method={"method_2902"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$noBreakProgressPredicted(BlockPos blockPos, Direction direction, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (ClientPlayerInteractionManagerMixin.aurora$creativeOnPrediction(blockPos)) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }

    @Unique
    private static boolean aurora$creativeOnPrediction(BlockPos blockPos) {
        ClientPlayerEntity clientPlayerEntity = MinecraftClient.method_1551().field_1724;
        if (clientPlayerEntity == null || !clientPlayerEntity.method_31549().field_7477) {
            return false;
        }
        return AnchorPrediction.isSuppressed(blockPos);
    }

    @Inject(method={"method_2896"}, at={@At(value="HEAD")})
    private void divine$anchorCombat(ClientPlayerEntity clientPlayerEntity, Hand hand, BlockHitResult blockHitResult, CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        AutoTextModule autoTextModule;
        ClientWorld clientWorld = MinecraftClient.method_1551().field_1687;
        if (clientWorld != null && clientWorld.method_8320(blockHitResult.method_17777()).method_27852(Blocks.field_23152)) {
            KillEffects.onCombat();
        }
        if ((autoTextModule = AutoTextModule.get()) == null || !autoTextModule.isEnabled()) {
            return;
        }
        if (clientWorld != null && clientWorld.method_8320(blockHitResult.method_17777()).method_27852(Blocks.field_23152)) {
            autoTextModule.onCombat();
        }
    }
}
