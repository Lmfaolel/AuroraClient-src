package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.duck.BlockingState;
import java.util.UUID;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value={LivingEntityRenderState.class})
public abstract class LivingEntityRenderStateMixin
implements BlockingState {
    @Unique
    private boolean divine$blocking;
    @Unique
    private boolean divine$localPlayer;
    @Unique
    private boolean divine$hurt;
    @Unique
    private UUID aurora$uuid;
    @Unique
    private boolean aurora$deathOverridden;

    @Override
    public boolean divine$isBlocking() {
        return this.divine$blocking;
    }

    @Override
    public void divine$setBlocking(boolean bl) {
        this.divine$blocking = bl;
    }

    @Override
    public boolean divine$isLocalPlayer() {
        return this.divine$localPlayer;
    }

    @Override
    public void divine$setLocalPlayer(boolean bl) {
        this.divine$localPlayer = bl;
    }

    @Override
    public UUID aurora$getUuid() {
        return this.aurora$uuid;
    }

    @Override
    public void aurora$setUuid(UUID uUID) {
        this.aurora$uuid = uUID;
    }

    @Override
    public boolean divine$isHurt() {
        return this.divine$hurt;
    }

    @Override
    public void divine$setHurt(boolean bl) {
        this.divine$hurt = bl;
    }

    @Override
    public boolean aurora$isDeathOverridden() {
        return this.aurora$deathOverridden;
    }

    @Override
    public void aurora$setDeathOverridden(boolean bl) {
        this.aurora$deathOverridden = bl;
    }
}
