package dev.quelchi.aurora.duck;

import java.util.UUID;

public interface BlockingState {
    public boolean divine$isBlocking();

    public void divine$setBlocking(boolean var1);

    public UUID aurora$getUuid();

    public void aurora$setUuid(UUID var1);

    public boolean divine$isLocalPlayer();

    public void divine$setLocalPlayer(boolean var1);

    public boolean divine$isHurt();

    public void divine$setHurt(boolean var1);

    public boolean aurora$isDeathOverridden();

    public void aurora$setDeathOverridden(boolean var1);
}
