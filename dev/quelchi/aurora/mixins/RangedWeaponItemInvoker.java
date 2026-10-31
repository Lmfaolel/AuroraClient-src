package dev.quelchi.aurora.mixins;

import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={RangedWeaponItem.class})
public interface RangedWeaponItemInvoker {
    @Invoker(value="method_57390")
    public static List<ItemStack> aurora$load(ItemStack itemStack, ItemStack itemStack2, LivingEntity livingEntity) {
        throw new AssertionError((Object)"RangedWeaponItemInvoker mixin did not apply");
    }
}
