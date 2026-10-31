package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.modules.MotionBlurModule;
import dev.quelchi.aurora.module.modules.MotionBlurModule$Type;
import dev.quelchi.aurora.module.modules.NostalgiaModule;
import dev.quelchi.aurora.module.modules.RendersModule;
import dev.quelchi.aurora.module.modules.ZoomModule;
import dev.quelchi.aurora.popchams.ChamExtractor;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.MotionBlurGpu;
import dev.quelchi.aurora.render.StarField;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.DefaultFramebufferSet;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.ObjectAllocator;
import net.minecraft.client.util.Pool;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={GameRenderer.class})
public abstract class GameRendererMixin {
    @Shadow
    @Final
    private MinecraftClient field_4015;
    @Shadow
    @Final
    private Pool field_53066;
    private static boolean aurora$warnedStaleFramebuffer;

    @WrapOperation(method={"method_3188(Lnet/minecraft/class_9779;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_761;method_22710(Lnet/minecraft/class_9922;Lnet/minecraft/class_9779;ZLnet/minecraft/class_4184;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lorg/joml/Vector4f;Z)V")})
    private void aurora$motionBlurAfterWorld(WorldRenderer worldRenderer, ObjectAllocator objectAllocator, RenderTickCounter renderTickCounter, boolean bl, Camera camera, Matrix4f matrix4f, Matrix4f matrix4f2, Matrix4f matrix4f3, GpuBufferSlice gpuBufferSlice, Vector4f vector4f, boolean bl2, Operation<Void> operation) {
        operation.call(new Object[]{worldRenderer, objectAllocator, renderTickCounter, bl, camera, matrix4f, matrix4f2, matrix4f3, gpuBufferSlice, vector4f, bl2});
        EffectEngine.afterWorld(matrix4f, matrix4f3, camera);
        MotionBlurGpu.afterWorld(matrix4f, matrix4f3, camera.method_71156());
    }

    @Inject(method={"method_3188(Lnet/minecraft/class_9779;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_310;method_1560()Lnet/minecraft/class_1297;")})
    private void aurora$popChams(RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
        float f = renderTickCounter.method_60637(true);
        Quaternionf quaternionf = ((GameRenderer)this).method_19418().method_23767().conjugate(new Quaternionf());
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.method_34425((Matrix4fc)new Matrix4f().rotate((Quaternionfc)quaternionf));
        ChamExtractor.get().extract(matrixStack, f);
    }

    @ModifyArg(method={"method_3192(Lnet/minecraft/class_9779;Z)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_11284;method_71116(IIDJLnet/minecraft/class_9779;ILnet/minecraft/class_4184;Z)V")}, index=2)
    private double aurora$oldGlintStrength(double d) {
        return NostalgiaModule.oldGlint() ? 1.0 : d;
    }

    @Inject(method={"method_3196(Lnet/minecraft/class_4184;FZ)F"}, at={@At(value="RETURN")}, cancellable=true)
    private void divine$zoom(Camera camera, float f, boolean bl, CallbackInfoReturnable<Float> callbackInfoReturnable) {
        float f2 = callbackInfoReturnable.getReturnValueF();
        ZoomModule zoomModule = ZoomModule.get();
        if (zoomModule != null && zoomModule.isEnabled()) {
            callbackInfoReturnable.setReturnValue((Object)Float.valueOf(f2 /= zoomModule.divisor(f)));
        }
        if (bl) {
            StarField.viewFov = f2;
        }
    }

    @ModifyExpressionValue(method={"method_3188"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_757;method_3196(Lnet/minecraft/class_4184;FZ)F", ordinal=1)})
    private float aurora$keepHandFov(float f) {
        ZoomModule zoomModule = ZoomModule.get();
        if (zoomModule != null && zoomModule.isEnabled() && !zoomModule.affectsHandFov()) {
            return (float)((double)f * zoomModule.lastDivisor());
        }
        return f;
    }

    @ModifyExpressionValue(method={"method_3186"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_11902;method_74204(F)F")})
    private float aurora$relativeViewBobbing(float f) {
        ZoomModule zoomModule = ZoomModule.get();
        if (zoomModule == null || !zoomModule.isEnabled() || !zoomModule.relativeViewBobbing()) {
            return f;
        }
        return (float)((double)f / MathHelper.method_16436((double)0.2, (double)1.0, (double)zoomModule.lastDivisor()));
    }

    @Inject(method={"method_3192(Lnet/minecraft/class_9779;Z)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_761;method_3254()V", shift=At.Shift.AFTER)})
    private void aurora$motionBlur(RenderTickCounter renderTickCounter, boolean bl, CallbackInfo callbackInfo) {
        MotionBlurModule motionBlurModule = MotionBlurModule.get();
        if (motionBlurModule == null || !motionBlurModule.active() || motionBlurModule.type() != MotionBlurModule$Type.TRAIL) {
            return;
        }
        PostEffectProcessor postEffectProcessor = this.field_4015.method_62887().method_62941(motionBlurModule.effect(), DefaultFramebufferSet.field_53902);
        if (postEffectProcessor != null) {
            postEffectProcessor.method_1258(this.field_4015.method_1522(), (ObjectAllocator)this.field_53066);
        }
    }

    @Inject(method={"method_3192(Lnet/minecraft/class_9779;Z)V"}, at={@At(value="HEAD")})
    private void aurora$matchFramebufferToWindow(RenderTickCounter renderTickCounter, boolean bl, CallbackInfo callbackInfo) {
        Framebuffer framebuffer = this.field_4015.method_1522();
        int n = this.field_4015.method_22683().method_4489();
        int n2 = this.field_4015.method_22683().method_4506();
        if (n <= 0 || n2 <= 0 || framebuffer.field_1482 == n && framebuffer.field_1481 == n2) {
            return;
        }
        if (!aurora$warnedStaleFramebuffer) {
            aurora$warnedStaleFramebuffer = true;
            Aurora.LOG.warn("Main framebuffer was {}x{} in a {}x{} window, resizing. A resize handler threw earlier, look for its stack trace above.", new Object[]{framebuffer.field_1482, framebuffer.field_1481, n, n2});
        }
        framebuffer.method_1234(n, n2);
        ((GameRenderer)this).method_3169(n, n2);
    }

    @Inject(method={"method_3172"}, at={@At(value="RETURN")})
    private void aurora$handGlow(float f, boolean bl, Matrix4f matrix4f, CallbackInfo callbackInfo) {
        RendersModule.renderHandShaders();
    }
}
