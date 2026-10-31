package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.hud.ItemHighlights;
import dev.quelchi.aurora.hud.ItemHighlights$Highlight;
import dev.quelchi.aurora.hud.ItemOutline;
import dev.quelchi.aurora.module.modules.CrossbowTweaksModule;
import dev.quelchi.aurora.module.modules.ItemHighlighterModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={HandledScreen.class})
public abstract class HandledScreenMixin {
    @Inject(method={"method_2385(Lnet/minecraft/class_332;Lnet/minecraft/class_1735;II)V"}, at={@At(value="TAIL")})
    private void divine$glowHeads(DrawContext drawContext, Slot slot, int n, int n2, CallbackInfo callbackInfo) {
        if (slot != null && CrossbowTweaksModule.showsInInventory()) {
            int n3 = CrossbowTweaksModule.overlayFor(slot.method_7677());
            CrossbowTweaksModule.drawOverlay(drawContext, slot.field_7873, slot.field_7872, n3);
        }
        if (slot == null) {
            return;
        }
        if (!ItemHighlighterModule.inInventories()) {
            return;
        }
        ItemHighlights$Highlight itemHighlights$Highlight = ItemHighlights.forStack(slot.method_7677());
        if (itemHighlights$Highlight != null) {
            ItemOutline.draw(drawContext, slot.field_7873, slot.field_7872, itemHighlights$Highlight);
        }
    }
}
