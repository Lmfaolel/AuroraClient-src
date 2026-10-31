package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.TotemTweaksModule;
import dev.quelchi.aurora.render.TotemFade;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={HeldItemRenderer.class})
public abstract class TotemHeldItemMixin {
    private static final String RENDER_FIRST_PERSON_ITEM = "renderFirstPersonItem(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;I)V";
    private static final String RENDER_ITEM = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;I)V";
    @Unique
    private boolean aurora$scaled;

    @ModifyVariable(method={"method_3224"}, at={@At(value="HEAD")}, argsOnly=true)
    private float aurora$noTotemEquip(float f, MatrixStack matrixStack, Arm arm, float f2) {
        TotemTweaksModule totemTweaksModule = TotemTweaksModule.get();
        if (totemTweaksModule == null || !totemTweaksModule.noEquipAnimation()) {
            return f;
        }
        ClientPlayerEntity clientPlayerEntity = MinecraftClient.method_1551().field_1724;
        if (clientPlayerEntity == null) {
            return f;
        }
        ItemStack itemStack = arm == clientPlayerEntity.method_6068() ? clientPlayerEntity.method_6047() : clientPlayerEntity.method_6079();
        return itemStack.method_31574(Items.field_8288) ? 0.0f : f;
    }

    @Inject(method={"method_3228(Lnet/minecraft/class_742;FFLnet/minecraft/class_1268;FLnet/minecraft/class_1799;FLnet/minecraft/class_4587;Lnet/minecraft/class_11659;I)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_759;method_3233(Lnet/minecraft/class_1309;Lnet/minecraft/class_1799;Lnet/minecraft/class_811;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;I)V")})
    private void aurora$totemSize(AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float f2, Hand hand, float f3, ItemStack itemStack, float f4, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, CallbackInfo callbackInfo) {
        boolean bl;
        TotemTweaksModule totemTweaksModule = TotemTweaksModule.get();
        boolean bl2 = bl = totemTweaksModule != null && itemStack.method_31574(Items.field_8288);
        if (bl && totemTweaksModule.fadesTotem()) {
            TotemFade.begin(totemTweaksModule.opacity());
        }
        boolean bl3 = this.aurora$scaled = bl && totemTweaksModule.scalesHand();
        if (!this.aurora$scaled) {
            return;
        }
        float f5 = totemTweaksModule.handScale();
        matrixStack.method_22903();
        matrixStack.method_22905(f5, f5, f5);
    }

    @Inject(method={"method_3228(Lnet/minecraft/class_742;FFLnet/minecraft/class_1268;FLnet/minecraft/class_1799;FLnet/minecraft/class_4587;Lnet/minecraft/class_11659;I)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_759;method_3233(Lnet/minecraft/class_1309;Lnet/minecraft/class_1799;Lnet/minecraft/class_811;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;I)V", shift=At.Shift.AFTER)})
    private void aurora$totemSizeEnd(AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float f2, Hand hand, float f3, ItemStack itemStack, float f4, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, CallbackInfo callbackInfo) {
        TotemFade.end();
        if (!this.aurora$scaled) {
            return;
        }
        this.aurora$scaled = false;
        matrixStack.method_22909();
    }
}
