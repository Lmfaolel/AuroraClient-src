package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={FluidRenderer.class})
public abstract class AmbienceFluidRendererMixin {
    @Unique
    private static final ThreadLocal<FluidState> aurora$fluidState = new ThreadLocal();

    @Inject(method={"method_3347"}, at={@At(value="HEAD")})
    private void aurora$captureFluidState(BlockRenderView blockRenderView, BlockPos blockPos, VertexConsumer vertexConsumer, BlockState blockState, FluidState fluidState, CallbackInfo callbackInfo) {
        aurora$fluidState.set(fluidState);
    }

    @Inject(method={"method_3347"}, at={@At(value="RETURN")})
    private void aurora$clearFluidState(BlockRenderView blockRenderView, BlockPos blockPos, VertexConsumer vertexConsumer, BlockState blockState, FluidState fluidState, CallbackInfo callbackInfo) {
        aurora$fluidState.remove();
    }

    @ModifyArgs(method={"method_3347"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_775;method_23072(Lnet/minecraft/class_4588;FFFFFFFFI)V")})
    private void aurora$overrideFluidVertexColor(Args args) {
        int n;
        FluidState fluidState = aurora$fluidState.get();
        if (fluidState == null) {
            return;
        }
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule == null || !ambienceModule.isEnabled()) {
            return;
        }
        if (ambienceModule.water() && fluidState.method_15767(FluidTags.field_15517)) {
            n = ambienceModule.waterRgb();
        } else if (ambienceModule.lava() && fluidState.method_15767(FluidTags.field_15518)) {
            n = ambienceModule.lavaRgb();
        } else {
            return;
        }
        args.set(4, (Object)Float.valueOf((float)(n >> 16 & 0xFF) / 255.0f));
        args.set(5, (Object)Float.valueOf((float)(n >> 8 & 0xFF) / 255.0f));
        args.set(6, (Object)Float.valueOf((float)(n & 0xFF) / 255.0f));
    }
}
