package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.mixins.ParticleGravityAccessor;
import dev.quelchi.aurora.module.modules.TotemTweaksModule;
import dev.quelchi.aurora.module.modules.TotemTweaksModule$ParticleStyle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.particle.TotemParticle;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TotemParticle.class})
public abstract class TotemParticleMixin {
    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void aurora$customise(ClientWorld clientWorld, double d, double d2, double d3, double d4, double d5, double d6, SpriteProvider spriteProvider, CallbackInfo callbackInfo) {
        TotemTweaksModule$ParticleStyle totemTweaksModule$ParticleStyle;
        float f;
        if (!TotemTweaksModule.customParticles()) {
            return;
        }
        TotemParticle totemParticle = (TotemParticle)this;
        ParticleGravityAccessor particleGravityAccessor = (ParticleGravityAccessor)((Object)this);
        totemParticle.method_3093(TotemTweaksModule.particleColor());
        float f2 = TotemTweaksModule.particleSize();
        if (f2 != 1.0f) {
            totemParticle.method_3087(f2);
        }
        if ((f = TotemTweaksModule.particleLife()) != 1.0f) {
            totemParticle.method_3077(Math.max(1, Math.round((float)totemParticle.method_3082() * f)));
        }
        if ((totemTweaksModule$ParticleStyle = TotemTweaksModule.particleStyle()) == TotemTweaksModule$ParticleStyle.Vanilla) {
            return;
        }
        float f3 = TotemTweaksModule.particleSpeed();
        double d7 = Math.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
        double d8 = d7 < 1.0E-6 ? 0.0 : d4 / d7;
        double d9 = d7 < 1.0E-6 ? 0.0 : d5 / d7;
        double d10 = d7 < 1.0E-6 ? 0.0 : d6 / d7;
        double d11 = Math.sqrt(d8 * d8 + d10 * d10);
        double d12 = d11 < 1.0E-6 ? 1.0 : d8 / d11;
        double d13 = d11 < 1.0E-6 ? 0.0 : d10 / d11;
        switch (totemTweaksModule$ParticleStyle.name()) {
            case "Burst": {
                totemParticle.method_34753(d8 * 0.35 * (double)f3, d9 * 0.35 * (double)f3, d10 * 0.35 * (double)f3);
                particleGravityAccessor.aurora$setGravity(0.0f);
                break;
            }
            case "Fall": {
                totemParticle.method_34753(d8 * 0.05 * (double)f3, Math.abs(d9) * 0.05 * (double)f3, d10 * 0.05 * (double)f3);
                particleGravityAccessor.aurora$setGravity(1.6f);
                break;
            }
            case "Fountain": {
                totemParticle.method_34753(d8 * 0.08 * (double)f3, (0.3 + Math.abs(d9) * 0.2) * (double)f3, d10 * 0.08 * (double)f3);
                particleGravityAccessor.aurora$setGravity(0.8f);
                break;
            }
            case "Ring": {
                totemParticle.method_34753(d12 * 0.3 * (double)f3, 0.0, d13 * 0.3 * (double)f3);
                particleGravityAccessor.aurora$setGravity(0.0f);
                break;
            }
        }
    }
}
