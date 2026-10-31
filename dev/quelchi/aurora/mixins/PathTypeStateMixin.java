package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.duck.PathTyped;
import dev.quelchi.aurora.perf.PathTypes$Entry;
import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value={AbstractBlock.AbstractBlockState.class})
public abstract class PathTypeStateMixin
implements PathTyped {
    @Unique
    private PathTypes$Entry aurora$pathType;

    @Override
    public PathTypes$Entry aurora$pathType() {
        return this.aurora$pathType;
    }

    @Override
    public void aurora$pathType(PathTypes$Entry pathTypes$Entry) {
        this.aurora$pathType = pathTypes$Entry;
    }
}
