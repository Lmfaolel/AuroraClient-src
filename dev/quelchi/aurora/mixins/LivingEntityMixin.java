package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.duck.UseState;
import dev.quelchi.aurora.module.modules.CleanViewModule;
import dev.quelchi.aurora.module.modules.DeathAnimationModule;
import dev.quelchi.aurora.module.modules.HandTweaksModule;
import dev.quelchi.aurora.module.modules.NostalgiaModule;
import dev.quelchi.aurora.optimize.ConsumableOptimizer;
import dev.quelchi.aurora.optimize.ElytraOptimizer;
import dev.quelchi.aurora.prediction.GlidePrediction;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={LivingEntity.class})
public abstract class LivingEntityMixin
implements UseState {
    @Shadow
    public int field_6213;
    @Shadow
    protected ItemStack field_6277;
    @Shadow
    protected int field_6222;
    @Shadow
    public int field_6279;
    @Shadow
    public float field_6283;

    @Override
    public void aurora$setActiveItem(ItemStack itemStack) {
        this.field_6277 = itemStack;
    }

    @Override
    public void aurora$setItemUseTime(int n) {
        this.field_6222 = n;
    }

    @Inject(method={"method_37119"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$consumableOptimizer(ItemStack itemStack, CallbackInfo callbackInfo) {
        if (this != MinecraftClient.method_1551().field_1724) {
            return;
        }
        if (ConsumableOptimizer.tickItemUse(itemStack)) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_6053"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$stopGliding(CallbackInfo callbackInfo) {
        LivingEntityMixin livingEntityMixin = this;
        if (!(livingEntityMixin instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity)livingEntityMixin;
        if (GlidePrediction.shouldStop(playerEntity) || ElytraOptimizer.stopGliding(playerEntity)) {
            playerEntity.method_66281();
            callbackInfo.cancel();
        }
    }

    @WrapOperation(method={"method_6050"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1937;method_8406(Lnet/minecraft/class_2394;DDDDDD)V")})
    private void divine$hidePotionSwirls(World world, ParticleEffect particleEffect, double d, double d2, double d3, double d4, double d5, double d6, Operation<Void> operation) {
        CleanViewModule cleanViewModule = CleanViewModule.get();
        if (cleanViewModule != null && cleanViewModule.hidePotionSwirl()) {
            return;
        }
        operation.call(new Object[]{world, particleEffect, d, d2, d3, d4, d5, d6});
    }

    @Inject(method={"method_6055"}, at={@At(value="HEAD")}, cancellable=true)
    private void divine$noSwing(float f, CallbackInfoReturnable<Float> callbackInfoReturnable) {
        CleanViewModule cleanViewModule = CleanViewModule.get();
        if (cleanViewModule != null && cleanViewModule.noSwing() && this == MinecraftClient.method_1551().field_1724) {
            callbackInfoReturnable.setReturnValue((Object)Float.valueOf(0.0f));
        }
    }

    @Inject(method={"method_6028"}, at={@At(value="RETURN")}, cancellable=true)
    private void aurora$swingDuration(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (this != MinecraftClient.method_1551().field_1724) {
            return;
        }
        HandTweaksModule handTweaksModule = HandTweaksModule.get();
        if (handTweaksModule == null || !handTweaksModule.isEnabled()) {
            return;
        }
        callbackInfoReturnable.setReturnValue((Object)HandTweaksModule.duration(callbackInfoReturnable.getReturnValueI()));
    }

    @Inject(method={"method_23667(Lnet/minecraft/class_1268;Z)V"}, at={@At(value="HEAD")})
    private void aurora$restartSwing(Hand hand, boolean bl, CallbackInfo callbackInfo) {
        if (this != MinecraftClient.method_1551().field_1724) {
            return;
        }
        if (HandTweaksModule.restartsImmediately()) {
            this.field_6279 = -1;
        }
        HandTweaksModule.onSwing(hand);
    }

    @Inject(method={"method_48157"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$oldHurtCam(CallbackInfoReturnable<Float> callbackInfoReturnable) {
        if (!NostalgiaModule.oldHurtCam()) {
            return;
        }
        if (this != MinecraftClient.method_1551().field_1724) {
            return;
        }
        callbackInfoReturnable.setReturnValue((Object)Float.valueOf(0.0f));
    }

    @WrapOperation(method={"method_5773()V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_3532;method_15379(F)F")})
    private float aurora$oldWalkNoFlip(float f, Operation<Float> operation) {
        if (this.aurora$oldWalk()) {
            return 0.0f;
        }
        return ((Float)operation.call(new Object[]{Float.valueOf(f)})).floatValue();
    }

    @WrapOperation(method={"method_6031(F)V"}, at={@At(value="INVOKE", target="Ljava/lang/Math;abs(F)F")})
    private float aurora$oldWalkBodyTurn(float f, Operation<Float> operation) {
        if (!this.aurora$oldWalk()) {
            return ((Float)operation.call(new Object[]{Float.valueOf(f)})).floatValue();
        }
        float f2 = MathHelper.method_15363((float)f, (float)-75.0f, (float)75.0f);
        this.field_6283 = ((LivingEntity)this).method_36454() - f2;
        if (Math.abs(f2) > 50.0f) {
            this.field_6283 += f2 * 0.2f;
        }
        return Float.MIN_VALUE;
    }

    private boolean aurora$oldWalk() {
        return NostalgiaModule.oldWalkAnimation() && ((LivingEntity)this).method_73183().method_8608();
    }

    @Inject(method={"method_6108"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$deathTiming(CallbackInfo callbackInfo) {
        LivingEntity livingEntity = (LivingEntity)this;
        if (!livingEntity.method_73183().method_8608()) {
            return;
        }
        if (!DeathAnimationModule.affects((Entity)livingEntity)) {
            return;
        }
        callbackInfo.cancel();
        ++this.field_6213;
        if (this.field_6213 >= DeathAnimationModule.deathTime() && !livingEntity.method_31481()) {
            livingEntity.method_5650(Entity.RemovalReason.field_26998);
        }
    }
}
