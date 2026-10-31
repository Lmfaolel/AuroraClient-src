package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.duck.BlockingState;
import dev.quelchi.aurora.module.modules.HideArmorModule;
import dev.quelchi.aurora.render.ArmorRenderContext;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ArmorFeatureRenderer.class})
public abstract class ArmorFeatureRendererMixin {
    @Inject(method={"method_17157(Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_10034;FF)V"}, at={@At(value="HEAD")})
    private void divine$pushContext(MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, BipedEntityRenderState bipedEntityRenderState, float f, float f2, CallbackInfo callbackInfo) {
        ArmorRenderContext.set(((BlockingState)bipedEntityRenderState).divine$isBlocking(), bipedEntityRenderState.field_53460);
    }

    @Inject(method={"method_17157(Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_10034;FF)V"}, at={@At(value="RETURN")})
    private void divine$popContext(MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, BipedEntityRenderState bipedEntityRenderState, float f, float f2, CallbackInfo callbackInfo) {
        ArmorRenderContext.clear();
    }

    @Inject(method={"method_4169(Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_1799;Lnet/minecraft/class_1304;ILnet/minecraft/class_10034;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$hideArmor(MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, ItemStack itemStack, EquipmentSlot equipmentSlot, int n, BipedEntityRenderState bipedEntityRenderState, CallbackInfo callbackInfo) {
        HideArmorModule hideArmorModule = HideArmorModule.get();
        if (hideArmorModule != null && hideArmorModule.hides(equipmentSlot, ((BlockingState)bipedEntityRenderState).divine$isLocalPlayer())) {
            callbackInfo.cancel();
        }
    }
}
