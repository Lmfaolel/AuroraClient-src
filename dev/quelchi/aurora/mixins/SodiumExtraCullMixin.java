package dev.quelchi.aurora.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(targets={"net/caffeinemc/mods/sodium/client/render/chunk/occlusion/OcclusionCuller"}, priority=1100)
public abstract class SodiumExtraCullMixin {
}
