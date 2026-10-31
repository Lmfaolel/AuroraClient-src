package dev.quelchi.aurora.mixins;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.quelchi.aurora.render.PaintedSky;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FrameGraphBuilder;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.SkyRendering;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.state.SkyRenderState;
import net.minecraft.client.util.ObjectAllocator;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={WorldRenderer.class})
public abstract class PaintedSkyMixin {
    @Inject(method={"method_22710(Lnet/minecraft/class_9922;Lnet/minecraft/class_9779;ZLnet/minecraft/class_4184;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lorg/joml/Vector4f;Z)V"}, at={@At(value="HEAD")})
    private void aurora$skyMatrices(ObjectAllocator objectAllocator, RenderTickCounter renderTickCounter, boolean bl, Camera camera, Matrix4f matrix4f, Matrix4f matrix4f2, Matrix4f matrix4f3, GpuBufferSlice gpuBufferSlice, Vector4f vector4f, boolean bl2, CallbackInfo callbackInfo) {
        PaintedSky.capture(matrix4f, matrix4f2);
    }

    @Inject(method={"method_62215"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$paintedSky(GpuBufferSlice gpuBufferSlice, SkyRenderState skyRenderState, SkyRendering skyRendering, CallbackInfo callbackInfo) {
        if (PaintedSky.render(skyRenderState)) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_62204(Lnet/minecraft/class_9909;Lnet/minecraft/class_4063;Lnet/minecraft/class_243;JFIF)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$paintedClouds(FrameGraphBuilder frameGraphBuilder, CloudRenderMode cloudRenderMode, Vec3d vec3d, long l, float f, int n, float f2, CallbackInfo callbackInfo) {
        if (PaintedSky.hidesClouds()) {
            callbackInfo.cancel();
        }
    }
}
