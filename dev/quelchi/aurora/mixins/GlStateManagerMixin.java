package dev.quelchi.aurora.mixins;

import com.mojang.blaze3d.opengl.GlStateManager;
import dev.quelchi.aurora.perf.LeanFramebuffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={GlStateManager.class})
public abstract class GlStateManagerMixin {
    @Inject(method={"_glBindFramebuffer(II)V"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$bindFramebuffer(int n, int n2, CallbackInfo callbackInfo) {
        if (LeanFramebuffers.bind(n, n2)) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"getFrameBuffer(I)I"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$logicalFramebuffer(int n, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (LeanFramebuffers.pending()) {
            callbackInfoReturnable.setReturnValue((Object)0);
        }
    }

    @Inject(method={"_clear(I)V"}, at={@At(value="HEAD")})
    private static void aurora$flushBeforeClear(CallbackInfo callbackInfo) {
        LeanFramebuffers.flush();
    }

    @Inject(method={"_drawElements(IIIJ)V"}, at={@At(value="HEAD")})
    private static void aurora$flushBeforeDrawElements(CallbackInfo callbackInfo) {
        LeanFramebuffers.flush();
    }

    @Inject(method={"_drawArrays(III)V"}, at={@At(value="HEAD")})
    private static void aurora$flushBeforeDrawArrays(CallbackInfo callbackInfo) {
        LeanFramebuffers.flush();
    }

    @Inject(method={"_readPixels(IIIIIIJ)V"}, at={@At(value="HEAD")})
    private static void aurora$flushBeforeReadPixels(CallbackInfo callbackInfo) {
        LeanFramebuffers.flush();
    }

    @Inject(method={"_glBlitFrameBuffer(IIIIIIIIII)V"}, at={@At(value="HEAD")})
    private static void aurora$flushBeforeBlit(CallbackInfo callbackInfo) {
        LeanFramebuffers.flush();
    }

    @Inject(method={"_glFramebufferTexture2D(IIIII)V"}, at={@At(value="HEAD")})
    private static void aurora$flushBeforeAttach(CallbackInfo callbackInfo) {
        LeanFramebuffers.flush();
    }
}
