package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.NostalgiaModule;
import dev.quelchi.aurora.nostalgia.OldPotions;
import net.minecraft.component.type.PotionContentsComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={PotionContentsComponent.class})
public abstract class PotionColorMixin {
    @Inject(method={"method_65361(I)I"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$oldPotionColor(int n, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (!NostalgiaModule.oldPotions()) {
            return;
        }
        PotionContentsComponent potionContentsComponent = (PotionContentsComponent)this;
        if (potionContentsComponent.comp_2379().isPresent()) {
            return;
        }
        callbackInfoReturnable.setReturnValue((Object)OldPotions.mix(potionContentsComponent.method_57397()).orElse(n));
    }
}
