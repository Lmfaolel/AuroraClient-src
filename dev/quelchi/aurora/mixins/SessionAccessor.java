package dev.quelchi.aurora.mixins;

import com.mojang.authlib.yggdrasil.ProfileResult;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={MinecraftClient.class})
public interface SessionAccessor {
    @Mutable
    @Accessor(value="field_1726")
    public void aurora$setSession(Session var1);

    @Mutable
    @Accessor(value="field_45899")
    public void aurora$setProfileFuture(CompletableFuture<ProfileResult> var1);
}
