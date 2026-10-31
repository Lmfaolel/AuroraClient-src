package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={BlockModelRenderer.class})
public abstract class AmbienceBlockFireMixin {
    @Unique
    private static final ThreadLocal<BlockState> aurora$blockState = new ThreadLocal();

    @Inject(method={"method_23073"}, at={@At(value="HEAD")})
    private void aurora$captureBlockState(BlockRenderView blockRenderView, BlockState blockState, BlockPos blockPos, VertexConsumer vertexConsumer, MatrixStack.Entry entry, BakedQuad bakedQuad, @Coerce Object object, int n, CallbackInfo callbackInfo) {
        aurora$blockState.set(blockState);
    }

    @Inject(method={"method_23073"}, at={@At(value="RETURN")})
    private void aurora$clearBlockState(BlockRenderView blockRenderView, BlockState blockState, BlockPos blockPos, VertexConsumer vertexConsumer, MatrixStack.Entry entry, BakedQuad bakedQuad, @Coerce Object object, int n, CallbackInfo callbackInfo) {
        aurora$blockState.remove();
    }

    @ModifyArgs(method={"method_23073"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_4588;method_22920(Lnet/minecraft/class_4587$class_4665;Lnet/minecraft/class_777;[FFFFF[II)V")})
    private void aurora$overrideFireColor(Args args) {
        BlockState blockState = aurora$blockState.get();
        if (blockState == null) {
            return;
        }
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule == null || !ambienceModule.isEnabled() || !ambienceModule.fire()) {
            return;
        }
        if (!blockState.method_27852(Blocks.field_10036) && !blockState.method_27852(Blocks.field_22089)) {
            return;
        }
        int n = ambienceModule.fireRgb();
        args.set(3, (Object)Float.valueOf((float)(n >> 16 & 0xFF) / 255.0f));
        args.set(4, (Object)Float.valueOf((float)(n >> 8 & 0xFF) / 255.0f));
        args.set(5, (Object)Float.valueOf((float)(n & 0xFF) / 255.0f));
    }
}
