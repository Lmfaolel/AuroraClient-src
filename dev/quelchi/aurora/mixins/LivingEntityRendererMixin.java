package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.quelchi.aurora.duck.BlockingState;
import dev.quelchi.aurora.module.modules.AntiAltModule;
import dev.quelchi.aurora.module.modules.DamageTintModule;
import dev.quelchi.aurora.module.modules.DeathAnimationModule;
import dev.quelchi.aurora.module.modules.HideArmorModule;
import dev.quelchi.aurora.module.modules.HitboxesModule;
import dev.quelchi.aurora.module.modules.NametagsModule;
import dev.quelchi.aurora.module.modules.RendersModule;
import dev.quelchi.aurora.popchams.CapturedPlayer;
import dev.quelchi.aurora.render.TintQueue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Model;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={LivingEntityRenderer.class})
public abstract class LivingEntityRendererMixin {
    @Unique
    private static final EquipmentSlot[] AURORA$ARMOR = new EquipmentSlot[]{EquipmentSlot.field_6169, EquipmentSlot.field_6174, EquipmentSlot.field_6172, EquipmentSlot.field_6166};

    @WrapOperation(method={"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_11659;method_73490(Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_4587;Lnet/minecraft/class_1921;IIILnet/minecraft/class_1058;ILnet/minecraft/class_11683$class_11792;)V")})
    private void divine$optimalHideBody(OrderedRenderCommandQueue orderedRenderCommandQueue, Model model, Object object, MatrixStack matrixStack, RenderLayer renderLayer, int n, int n2, int n3, Sprite sprite, int n4, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlayCommand, Operation<Void> operation) {
        HitboxesModule hitboxesModule = HitboxesModule.get();
        if (hitboxesModule != null && hitboxesModule.hidesModel() && object instanceof PlayerEntityRenderState) {
            PlayerEntityRenderState playerEntityRenderState = (PlayerEntityRenderState)object;
            if (!playerEntityRenderState.field_53461) {
                return;
            }
        }
        operation.call(new Object[]{orderedRenderCommandQueue, model, object, matrixStack, renderLayer, n, n2, n3, sprite, n4, crumblingOverlayCommand});
    }

    @ModifyVariable(method={"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=0)
    private OrderedRenderCommandQueue aurora$chams(OrderedRenderCommandQueue orderedRenderCommandQueue, LivingEntityRenderState livingEntityRenderState) {
        int n = livingEntityRenderState.field_53449 > 0.0f && ((BlockingState)livingEntityRenderState).aurora$isDeathOverridden() ? DeathAnimationModule.fadeTint(livingEntityRenderState.field_53449) : -1;
        int n2 = RendersModule.chamsColor(livingEntityRenderState);
        return TintQueue.wrap(orderedRenderCommandQueue, n != -1 ? n : n2, true);
    }

    @Inject(method={"method_4055(Lnet/minecraft/class_1309;D)Z"}, at={@At(value="RETURN")}, cancellable=true)
    private void divine$showOwnLabel(LivingEntity livingEntity, double d, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (callbackInfoReturnable.getReturnValueZ()) {
            return;
        }
        NametagsModule nametagsModule = NametagsModule.get();
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (nametagsModule != null && nametagsModule.showOwnNametag() && livingEntity == minecraftClient.field_1724 && !minecraftClient.field_1690.method_31044().method_31034() && MinecraftClient.method_1498() && !livingEntity.method_5782()) {
            callbackInfoReturnable.setReturnValue((Object)true);
        }
    }

    @Inject(method={"method_62355(Lnet/minecraft/class_1309;Lnet/minecraft/class_10042;F)V"}, at={@At(value="TAIL")})
    private void divine$captureBlocking(LivingEntity livingEntity, LivingEntityRenderState livingEntityRenderState, float f, CallbackInfo callbackInfo) {
        HideArmorModule hideArmorModule;
        ((BlockingState)livingEntityRenderState).divine$setBlocking(livingEntity.method_6039());
        ((BlockingState)livingEntityRenderState).divine$setLocalPlayer(livingEntity == MinecraftClient.method_1551().field_1724);
        ((BlockingState)livingEntityRenderState).aurora$setUuid(livingEntity.method_5667());
        ((BlockingState)livingEntityRenderState).divine$setHurt(livingEntity.field_6235 > 0);
        if (livingEntity instanceof PlayerEntity && livingEntityRenderState.field_53333) {
            boolean bl;
            hideArmorModule = HideArmorModule.get();
            boolean bl2 = bl = livingEntity == MinecraftClient.method_1551().field_1724;
            if (hideArmorModule != null && hideArmorModule.hidesAny(bl)) {
                for (EquipmentSlot equipmentSlot : AURORA$ARMOR) {
                    if (!hideArmorModule.hides(equipmentSlot, bl) || livingEntity.method_6118(equipmentSlot).method_7960()) continue;
                    livingEntityRenderState.field_53333 = false;
                    break;
                }
            }
        }
        if (livingEntity instanceof AbstractClientPlayerEntity) {
            hideArmorModule = (AbstractClientPlayerEntity)livingEntity;
            if (livingEntityRenderState.field_53337 != null && !(livingEntity instanceof CapturedPlayer)) {
                NametagsModule nametagsModule;
                Text text;
                Text text2 = livingEntityRenderState.field_53337;
                EquipmentSlot[] equipmentSlotArray = AntiAltModule.get();
                if (equipmentSlotArray != null && hideArmorModule == MinecraftClient.method_1551().field_1724 && (text = equipmentSlotArray.spoofedName()) != null) {
                    text2 = text;
                }
                if ((nametagsModule = NametagsModule.get()) != null) {
                    text2 = nametagsModule.decorate(text2, hideArmorModule.method_5667());
                }
                livingEntityRenderState.field_53337 = text2;
            }
        }
    }

    @Inject(method={"method_62355(Lnet/minecraft/class_1309;Lnet/minecraft/class_10042;F)V"}, at={@At(value="RETURN")})
    private void aurora$markDeathOverride(LivingEntity livingEntity, LivingEntityRenderState livingEntityRenderState, float f, CallbackInfo callbackInfo) {
        ((BlockingState)livingEntityRenderState).aurora$setDeathOverridden(DeathAnimationModule.affects((Entity)livingEntity));
    }

    @ModifyExpressionValue(method={"method_4058(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;FF)V"}, at={@At(value="FIELD", target="Lnet/minecraft/class_10042;field_53449:F", ordinal=0)})
    private float aurora$noDeathFlop(float f, @Local(argsOnly=true) LivingEntityRenderState livingEntityRenderState) {
        if (f <= 0.0f) {
            return f;
        }
        return ((BlockingState)livingEntityRenderState).aurora$isDeathOverridden() ? 0.0f : f;
    }

    @Inject(method={"method_23622"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$deathOverlay(LivingEntityRenderState livingEntityRenderState, float f, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        boolean bl;
        boolean bl2 = ((BlockingState)livingEntityRenderState).aurora$isDeathOverridden();
        boolean bl3 = bl = livingEntityRenderState instanceof PlayerEntityRenderState && DamageTintModule.armorOnly();
        if (!bl2 && !bl) {
            return;
        }
        boolean bl4 = !bl && livingEntityRenderState.field_53449 > 0.0f && livingEntityRenderState.field_53449 <= (float)DeathAnimationModule.overlayTime();
        callbackInfoReturnable.setReturnValue((Object)OverlayTexture.method_23624((float)f, (boolean)bl4));
    }
}
