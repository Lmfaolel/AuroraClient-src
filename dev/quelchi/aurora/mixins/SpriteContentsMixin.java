package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.cobweb.CobwebTextureResolver;
import dev.quelchi.aurora.cobweb.CobwebTint;
import java.lang.reflect.Field;
import net.minecraft.client.texture.SpriteContents;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SpriteContents.class})
public abstract class SpriteContentsMixin {
    @Shadow
    @Final
    Identifier field_40536;

    @Inject(method={"<init>(Lnet/minecraft/class_2960;Lnet/minecraft/class_7771;Lnet/minecraft/class_1011;Ljava/util/Optional;Ljava/util/List;Ljava/util/Optional;)V"}, at={@At(value="TAIL")})
    private void divine$fadeCobwebSprite(CallbackInfo callbackInfo) {
        if (!CobwebTextureResolver.isTarget(this.field_40536)) {
            return;
        }
        CobwebTint.bake((SpriteContents)this);
        try {
            for (Field field : this.getClass().getFields()) {
                if (field.getType() != Boolean.TYPE || !field.getName().endsWith("hasTranslucentPixels")) continue;
                field.setBoolean(this, true);
            }
        }
        catch (Exception exception) {
        }
    }

    @Inject(method={"method_45808"}, at={@At(value="HEAD")})
    private void aurora$cobwebBeforeMipmaps(int n, CallbackInfo callbackInfo) {
        if (!CobwebTextureResolver.isTarget(this.field_40536)) {
            return;
        }
        CobwebTint.unbake((SpriteContents)this);
    }

    @Inject(method={"method_45808"}, at={@At(value="RETURN")})
    private void aurora$cobwebAfterMipmaps(int n, CallbackInfo callbackInfo) {
        if (!CobwebTextureResolver.isTarget(this.field_40536)) {
            return;
        }
        CobwebTint.bake((SpriteContents)this);
    }
}
