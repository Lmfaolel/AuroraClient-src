package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.module.modules.HidePlayerModelModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={InventoryScreen.class, CreativeInventoryScreen.class})
public abstract class InventoryScreenMixin {
    @WrapOperation(method={"method_2389"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_490;method_2486(Lnet/minecraft/class_332;IIIIIFFFLnet/minecraft/class_1309;)V")})
    private void divine$hidePlayerPreview(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, float f, float f2, float f3, LivingEntity livingEntity, Operation<Void> operation) {
        HidePlayerModelModule hidePlayerModelModule = HidePlayerModelModule.get();
        if (hidePlayerModelModule != null && hidePlayerModelModule.isEnabled()) {
            if (!hidePlayerModelModule.transparent()) {
                drawContext.method_25294(n, n2, n3, n4, -16777216);
            }
            return;
        }
        operation.call(new Object[]{drawContext, n, n2, n3, n4, n5, Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), livingEntity});
    }
}
