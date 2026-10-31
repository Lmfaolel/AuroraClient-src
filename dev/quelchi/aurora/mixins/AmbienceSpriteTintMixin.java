package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.ambience.AmbienceTextures;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.SpriteContents;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SpriteContents.class})
public abstract class AmbienceSpriteTintMixin {
    @Shadow
    @Final
    Identifier field_40536;
    @Shadow
    @Final
    private NativeImage field_40539;

    @Inject(method={"<init>(Lnet/minecraft/class_2960;Lnet/minecraft/class_7771;Lnet/minecraft/class_1011;Ljava/util/Optional;Ljava/util/List;Ljava/util/Optional;)V"}, at={@At(value="RETURN")})
    private void aurora$tintOnLoad(CallbackInfo callbackInfo) {
        AmbienceTextures.maybeTint(this.field_40536, this.field_40539);
    }

    @Inject(method={"method_45808"}, at={@At(value="HEAD")})
    private void aurora$tintBeforeMipmaps(int n, CallbackInfo callbackInfo) {
        AmbienceTextures.maybeTint(this.field_40536, this.field_40539);
    }
}
