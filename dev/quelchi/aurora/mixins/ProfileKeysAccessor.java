package dev.quelchi.aurora.mixins;

import com.mojang.authlib.minecraft.UserApiService;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.ProfileKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={MinecraftClient.class})
public interface ProfileKeysAccessor {
    @Mutable
    @Accessor(value="field_39068")
    public void aurora$setProfileKeys(ProfileKeys var1);

    @Mutable
    @Accessor(value="field_26902")
    public void aurora$setUserApiService(UserApiService var1);
}
