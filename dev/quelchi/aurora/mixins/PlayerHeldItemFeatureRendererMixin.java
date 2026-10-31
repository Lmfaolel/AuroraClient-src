package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.ShieldTweaksModule;
import dev.quelchi.aurora.shield.ShieldRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.PlayerHeldItemFeatureRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PlayerHeldItemFeatureRenderer.class})
public abstract class PlayerHeldItemFeatureRendererMixin {
    @Inject(method={"method_62594(Lnet/minecraft/class_10055;Lnet/minecraft/class_10444;Lnet/minecraft/class_1799;Lnet/minecraft/class_1306;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;I)V"}, at={@At(value="HEAD")})
    private void aurora$noteShieldOwner(PlayerEntityRenderState playerEntityRenderState, ItemRenderState itemRenderState, ItemStack itemStack, Arm arm, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, CallbackInfo callbackInfo) {
        if (!ShieldTweaksModule.colorsShields() || !(itemStack.method_7909() instanceof ShieldItem)) {
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (minecraftClient.field_1687 == null) {
            return;
        }
        Entity entity = minecraftClient.field_1687.method_8469(playerEntityRenderState.field_53528);
        if (entity instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)entity;
            ShieldRenderer.setFocused(playerEntity);
        }
    }
}
