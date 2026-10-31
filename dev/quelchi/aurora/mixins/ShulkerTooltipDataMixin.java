package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.ShulkerTooltipsModule;
import java.util.Optional;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Item.class})
public abstract class ShulkerTooltipDataMixin {
    @Inject(method={"method_32346(Lnet/minecraft/class_1799;)Ljava/util/Optional;"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$shulkerGrid(ItemStack itemStack, CallbackInfoReturnable<Optional<TooltipData>> callbackInfoReturnable) {
        TooltipData tooltipData = ShulkerTooltipsModule.grid(itemStack);
        if (tooltipData != null) {
            callbackInfoReturnable.setReturnValue(Optional.of(tooltipData));
        }
    }
}
