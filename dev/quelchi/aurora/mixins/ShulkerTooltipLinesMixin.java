package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import dev.quelchi.aurora.module.modules.ShulkerTooltipsModule;
import java.util.function.Consumer;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={ItemStack.class})
public abstract class ShulkerTooltipLinesMixin {
    @WrapWithCondition(method={"method_67194(Lnet/minecraft/class_1792$class_9635;Lnet/minecraft/class_10712;Lnet/minecraft/class_1657;Lnet/minecraft/class_1836;Ljava/util/function/Consumer;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1799;method_57369(Lnet/minecraft/class_9331;Lnet/minecraft/class_1792$class_9635;Lnet/minecraft/class_10712;Ljava/util/function/Consumer;Lnet/minecraft/class_1836;)V")})
    private boolean aurora$hideContainerLines(ItemStack itemStack, ComponentType<?> componentType, Item.TooltipContext tooltipContext, TooltipDisplayComponent tooltipDisplayComponent, Consumer<Text> consumer, TooltipType tooltipType) {
        return componentType != DataComponentTypes.field_49622 || !ShulkerTooltipsModule.active(itemStack);
    }
}
