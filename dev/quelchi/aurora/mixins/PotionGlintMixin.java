package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.NostalgiaModule;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ItemStack.class})
public abstract class PotionGlintMixin {
    @Inject(method={"method_7958"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$potionGlint(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (!NostalgiaModule.potionGlint()) {
            return;
        }
        ItemStack itemStack = (ItemStack)this;
        if (itemStack.method_31574(Items.field_8574) || itemStack.method_31574(Items.field_8436) || itemStack.method_31574(Items.field_8150)) {
            callbackInfoReturnable.setReturnValue((Object)true);
        }
    }
}
