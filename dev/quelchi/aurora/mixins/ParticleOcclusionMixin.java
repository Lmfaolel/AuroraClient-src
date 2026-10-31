package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.perf.ParticleCull;
import dev.quelchi.aurora.perf.Tweaks;
import net.minecraft.client.particle.BillboardParticleRenderer;
import net.minecraft.client.render.Frustum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={BillboardParticleRenderer.class})
public abstract class ParticleOcclusionMixin {
    @WrapOperation(method={"method_74276"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_4604;method_74404(DDD)Z")})
    private boolean aurora$cullHidden(Frustum frustum, double d, double d2, double d3, Operation<Boolean> operation) {
        return (Boolean)operation.call(new Object[]{frustum, d, d2, d3}) != false && (!Tweaks.particleCull() || !ParticleCull.hidden(d, d2, d3));
    }
}
