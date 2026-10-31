package dev.quelchi.aurora.mixins;

import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={Window.class})
public interface WindowRegionInvoker {
    @Invoker(value="method_4479")
    public void aurora$updateWindowRegion();
}
