package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.duck.GhostCrystal;
import net.minecraft.client.render.entity.state.EndCrystalEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value={EndCrystalEntityRenderState.class})
public abstract class EndCrystalEntityRenderStateMixin
implements GhostCrystal {
    @Unique
    private boolean aurora$ghost;

    @Override
    public boolean aurora$isGhost() {
        return this.aurora$ghost;
    }

    @Override
    public void aurora$setGhost(boolean bl) {
        this.aurora$ghost = bl;
    }
}
