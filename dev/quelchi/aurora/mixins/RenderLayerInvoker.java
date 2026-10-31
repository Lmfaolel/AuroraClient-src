package dev.quelchi.aurora.mixins;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={RenderLayer.class})
public interface RenderLayerInvoker {
    @Invoker(value="method_75940")
    public static RenderLayer aurora$of(String string, RenderSetup renderSetup) {
        throw new AssertionError((Object)"mixin");
    }
}
