package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.quelchi.aurora.module.modules.SoundControllerModule;
import dev.quelchi.aurora.sound.SoundOverrides;
import dev.quelchi.aurora.sound.SoundOverrides$Override;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={SoundSystem.class})
public abstract class SoundSystemMixin {
    @ModifyReturnValue(method={"method_4853(Lnet/minecraft/class_1113;)F"}, at={@At(value="RETURN")})
    private float aurora$soundVolume(float f, SoundInstance soundInstance) {
        SoundOverrides$Override soundOverrides$Override = SoundSystemMixin.lookup(soundInstance);
        if (soundOverrides$Override == null) {
            return f;
        }
        return Math.max(0.0f, Math.min(2.0f, f * soundOverrides$Override.volume()));
    }

    @ModifyReturnValue(method={"method_4849(Lnet/minecraft/class_1113;)F"}, at={@At(value="RETURN")})
    private float aurora$soundPitch(float f, SoundInstance soundInstance) {
        SoundOverrides$Override soundOverrides$Override = SoundSystemMixin.lookup(soundInstance);
        if (soundOverrides$Override == null) {
            return f;
        }
        return Math.max(0.1f, Math.min(4.0f, f * soundOverrides$Override.pitch()));
    }

    @Inject(method={"method_4854(Lnet/minecraft/class_1113;)Lnet/minecraft/class_1140$class_11518;"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$soundMuted(SoundInstance soundInstance, CallbackInfoReturnable<SoundSystem.PlayResult> callbackInfoReturnable) {
        boolean bl;
        if (!SoundControllerModule.active() || soundInstance == null) {
            return;
        }
        Identifier identifier = soundInstance.method_4775();
        SoundOverrides.noteHeard(identifier);
        SoundOverrides$Override soundOverrides$Override = SoundOverrides.forSound(identifier);
        boolean bl2 = bl = SoundControllerModule.replacesTotemSound() && identifier.equals((Object)SoundEvents.field_14931.comp_3319());
        if (bl || soundOverrides$Override != null && soundOverrides$Override.muted()) {
            callbackInfoReturnable.setReturnValue((Object)SoundSystem.PlayResult.field_60956);
        }
    }

    private static SoundOverrides$Override lookup(SoundInstance soundInstance) {
        if (!SoundControllerModule.active() || soundInstance == null) {
            return null;
        }
        SoundOverrides$Override soundOverrides$Override = SoundOverrides.forSound(soundInstance.method_4775());
        return soundOverrides$Override == null || soundOverrides$Override.isDefault() ? null : soundOverrides$Override;
    }
}
