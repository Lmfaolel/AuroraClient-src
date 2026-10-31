package dev.quelchi.aurora.mixins;

import net.minecraft.block.Block;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={ClientWorld.class})
public interface ClientWorldInvoker {
    @Invoker(value="method_23789")
    public int aurora$lightningTicksLeft();

    @Invoker(value="method_35752")
    public Block aurora$blockParticle();
}
