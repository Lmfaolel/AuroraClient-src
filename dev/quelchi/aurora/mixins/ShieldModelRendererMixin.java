package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.ShieldTweaksModule;
import dev.quelchi.aurora.shield.ShieldRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.model.special.ShieldModelRenderer;
import net.minecraft.client.texture.SpriteHolder;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.ComponentMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemDisplayContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ShieldModelRenderer.class})
public abstract class ShieldModelRendererMixin {
    @Shadow
    @Final
    private SpriteHolder field_61858;
    @Shadow
    @Final
    private ShieldEntityModel field_55441;

    @Inject(method={"method_65707(Lnet/minecraft/class_9323;Lnet/minecraft/class_811;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;IIZI)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$statusColor(ComponentMap componentMap, ItemDisplayContext itemDisplayContext, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, int n2, boolean bl, int n3, CallbackInfo callbackInfo) {
        ClientPlayerEntity clientPlayerEntity;
        if (!ShieldTweaksModule.colorsShields()) {
            return;
        }
        if (itemDisplayContext.method_29998() || itemDisplayContext == ItemDisplayContext.field_4317) {
            clientPlayerEntity = MinecraftClient.method_1551().field_1724;
        } else if (itemDisplayContext == ItemDisplayContext.field_4323 || itemDisplayContext == ItemDisplayContext.field_4320) {
            clientPlayerEntity = ShieldRenderer.consumeFocused();
        } else {
            return;
        }
        if (clientPlayerEntity == null) {
            return;
        }
        int n4 = ShieldTweaksModule.tintFor((PlayerEntity)clientPlayerEntity);
        if (n4 == -1) {
            return;
        }
        ShieldRenderer.render(componentMap, this.field_55441, this.field_61858, matrixStack, orderedRenderCommandQueue, n, n2, bl, n4);
        callbackInfo.cancel();
    }
}
