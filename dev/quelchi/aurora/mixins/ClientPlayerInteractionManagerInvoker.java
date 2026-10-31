package dev.quelchi.aurora.mixins;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={ClientPlayerInteractionManager.class})
public interface ClientPlayerInteractionManagerInvoker {
    @Invoker(value="method_2911")
    public void aurora$syncSelectedSlot();
}
