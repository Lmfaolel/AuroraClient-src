package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.util.BorderlessFit;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={Window.class})
public abstract class WindowFullscreenMixin {
    @Redirect(method={"method_4479"}, require=0, at={@At(value="INVOKE", target="Lorg/lwjgl/glfw/GLFW;glfwGetWindowMonitor(J)J", remap=false)})
    private long aurora$monitor(long l) {
        long l2 = GLFW.glfwGetWindowMonitor((long)l);
        return l2 == 0L && BorderlessFit.borderless() ? 1L : l2;
    }

    @Redirect(method={"method_4479"}, require=0, at={@At(value="INVOKE", target="Lorg/lwjgl/glfw/GLFW;glfwSetWindowMonitor(JJIIIII)V", ordinal=0, remap=false)})
    private void aurora$fullscreen(long l, long l2, int n, int n2, int n3, int n4, int n5) {
        BorderlessFit.fullscreen(l, l2, n, n2, n3, n4, n5);
    }

    @Redirect(method={"method_4479"}, require=0, at={@At(value="INVOKE", target="Lorg/lwjgl/glfw/GLFW;glfwSetWindowMonitor(JJIIIII)V", ordinal=1, remap=false)})
    private void aurora$windowed(long l, long l2, int n, int n2, int n3, int n4, int n5) {
        BorderlessFit.windowed(l, l2, n, n2, n3, n4, n5);
    }
}
