package dev.quelchi.aurora.prediction;

import net.minecraft.item.ItemStack;

final class OffhandSwap$Pending {
    final ItemStack offhand;
    final int hotbarSlot;
    final long expires;
    ItemStack heldOffhand;
    ItemStack heldHotbar;

    OffhandSwap$Pending(ItemStack itemStack, int n, long l) {
        this.offhand = itemStack;
        this.hotbarSlot = n;
        this.expires = l;
    }
}
