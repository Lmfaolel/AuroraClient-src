package dev.quelchi.aurora.mixins;

import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={InventoryScreen.class})
public interface InventoryStateInvoker {
    @Invoker(value="method_48472")
    public static EntityRenderState aurora$state(LivingEntity livingEntity) {
        throw new AssertionError();
    }
}
