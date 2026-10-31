package dev.quelchi.aurora.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.perf.FramePacer;
import dev.quelchi.aurora.perf.FrameQueue;
import dev.quelchi.aurora.perf.FrameStats;
import dev.quelchi.aurora.perf.LeanFramebuffers;
import dev.quelchi.aurora.perf.SharedFences;
import dev.quelchi.aurora.perf.Tweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderSystem.class})
public abstract class RenderSystemMixin {
    private static final String FLIP = "flipFrame(Lnet/minecraft/client/util/Window;Lnet/minecraft/client/util/tracy/TracyFrameCapturer;)V";

    @Inject(method={"flipFrame(Lnet/minecraft/class_1041;Lnet/minecraft/class_10219;)V"}, at={@At(value="HEAD")})
    private static void aurora$flushBeforeSwap(CallbackInfo callbackInfo) {
        LeanFramebuffers.flush();
        Im.frame();
    }

    @Inject(method={"flipFrame(Lnet/minecraft/class_1041;Lnet/minecraft/class_10219;)V"}, at={@At(value="INVOKE", target="Lorg/lwjgl/glfw/GLFW;glfwSwapBuffers(J)V", shift=At.Shift.AFTER)}, require=0)
    private static void aurora$openFenceBatch(CallbackInfo callbackInfo) {
        SharedFences.open();
    }

    @Inject(method={"flipFrame(Lnet/minecraft/class_1041;Lnet/minecraft/class_10219;)V"}, at={@At(value="RETURN")})
    private static void aurora$closeFenceBatch(CallbackInfo callbackInfo) {
        SharedFences.close();
        FrameStats.frame();
        FrameQueue.swapped();
        Round.newFrame();
    }

    @Inject(method={"limitDisplayFPS(I)V"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$pace(int n, CallbackInfo callbackInfo) {
        if (!Tweaks.framePacing()) {
            return;
        }
        FramePacer.limit(n);
        callbackInfo.cancel();
    }
}
