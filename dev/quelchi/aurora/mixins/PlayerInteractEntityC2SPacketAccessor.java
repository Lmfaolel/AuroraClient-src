package dev.quelchi.aurora.mixins;

import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={PlayerInteractEntityC2SPacket.class})
public interface PlayerInteractEntityC2SPacketAccessor {
    @Accessor(value="field_12870")
    public int aurora$entityId();
}
