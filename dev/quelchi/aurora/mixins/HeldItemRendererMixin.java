package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.module.modules.CleanViewModule;
import dev.quelchi.aurora.module.modules.HandTweaksModule;
import dev.quelchi.aurora.optimize.ConsumableOptimizer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionfc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={HeldItemRenderer.class})
public abstract class HeldItemRendererMixin {
    @Inject(method={"method_65910"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$consumableSwap(ItemStack itemStack, ItemStack itemStack2, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (ConsumableOptimizer.skipsSwapAnimation(itemStack, itemStack2)) {
            callbackInfoReturnable.setReturnValue((Object)true);
        }
    }

    @Inject(method={"method_22976(FLnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_746;I)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void divine$optimalHideHands(float f, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, ClientPlayerEntity clientPlayerEntity, int n, CallbackInfo callbackInfo) {
        CleanViewModule cleanViewModule = CleanViewModule.get();
        if (cleanViewModule != null && cleanViewModule.hideHands()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_3224"}, at={@At(value="RETURN")})
    private void aurora$handPlacement(MatrixStack matrixStack, Arm arm, float f, CallbackInfo callbackInfo) {
        ClientPlayerEntity clientPlayerEntity = MinecraftClient.method_1551().field_1724;
        if (clientPlayerEntity == null) {
            return;
        }
        HandTweaksModule.applyPlacement(matrixStack, arm == clientPlayerEntity.method_6068());
    }

    @WrapOperation(method={"method_3228"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_759;method_3233(Lnet/minecraft/class_1309;Lnet/minecraft/class_1799;Lnet/minecraft/class_811;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;I)V")})
    private void aurora$handScale(HeldItemRenderer heldItemRenderer, LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext itemDisplayContext, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, Operation<Void> operation) {
        boolean bl;
        boolean bl2 = itemDisplayContext == ItemDisplayContext.field_4321;
        boolean bl3 = bl = itemDisplayContext == ItemDisplayContext.field_4322;
        if (!bl2 && !bl) {
            operation.call(new Object[]{heldItemRenderer, livingEntity, itemStack, itemDisplayContext, matrixStack, orderedRenderCommandQueue, n});
            return;
        }
        ClientPlayerEntity clientPlayerEntity = MinecraftClient.method_1551().field_1724;
        boolean bl4 = clientPlayerEntity == null || (bl ? clientPlayerEntity.method_6068() == Arm.field_6183 : clientPlayerEntity.method_6068() == Arm.field_6182);
        float f = HandTweaksModule.scaleFor(bl4);
        float f2 = HandTweaksModule.spinAngle(bl4 ? Hand.field_5808 : Hand.field_5810, MinecraftClient.method_1551().method_61966().method_60637(true));
        if (f == 1.0f && f2 == 0.0f) {
            operation.call(new Object[]{heldItemRenderer, livingEntity, itemStack, itemDisplayContext, matrixStack, orderedRenderCommandQueue, n});
            return;
        }
        matrixStack.method_22903();
        if (f2 != 0.0f) {
            matrixStack.method_22907((Quaternionfc)RotationAxis.field_40718.rotationDegrees(bl ? -f2 : f2));
        }
        matrixStack.method_22905(f, f, f);
        operation.call(new Object[]{heldItemRenderer, livingEntity, itemStack, itemDisplayContext, matrixStack, orderedRenderCommandQueue, n});
        matrixStack.method_22909();
    }
}
