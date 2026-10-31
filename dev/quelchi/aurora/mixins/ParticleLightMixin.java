package dev.quelchi.aurora.mixins;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={Particle.class})
public abstract class ParticleLightMixin {
    @Shadow
    protected int field_3866;
    @Unique
    private int aurora$lightAge = -1;
    @Unique
    private long aurora$lightPos;
    @Unique
    private int aurora$light;

    @Redirect(method={"method_3068(F)I"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_761;method_23794(Lnet/minecraft/class_1920;Lnet/minecraft/class_2338;)I")})
    private int aurora$cachedLight(BlockRenderView blockRenderView, BlockPos blockPos) {
        long l = blockPos.method_10063();
        if (this.aurora$lightAge != this.field_3866 || this.aurora$lightPos != l) {
            this.aurora$light = WorldRenderer.method_23794((BlockRenderView)blockRenderView, (BlockPos)blockPos);
            this.aurora$lightAge = this.field_3866;
            this.aurora$lightPos = l;
        }
        return this.aurora$light;
    }
}
