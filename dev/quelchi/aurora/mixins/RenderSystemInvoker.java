package dev.quelchi.aurora.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={RenderSystem.class})
public interface RenderSystemInvoker {
    @Invoker(value="pollEvents")
    public static void aurora$pollEvents() {
        throw new AssertionError();
    }
}
