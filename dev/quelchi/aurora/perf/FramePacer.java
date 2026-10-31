package dev.quelchi.aurora.perf;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.perf.Tweaks;
import org.lwjgl.glfw.GLFW;

public final class FramePacer {
    private static final double MIN_MARGIN = 3.0E-4;
    private static final double MAX_MARGIN = 0.004;
    private static double deadline;
    private static double margin;

    private FramePacer() {
    }

    
    public static void limit(int var0) {
        block15: {
            var2_1 = 1.0 / (double)Math.max(var0, 1);
            var1_2 = Tweaks.a;
            v0 = var4_3 = GLFW.glfwGetTime();
            if (var1_2 != 0) // goto
            if (v0 - FramePacer.deadline > var2_1) {
                v1 = var4_3 + var2_1;
            } else {
                v0 = FramePacer.deadline;
lbl9:

                v1 = v0 + var2_1;
            }
            FramePacer.deadline = v1;
            var6_4 = false;
            var7_5 = FramePacer.deadline - var4_3;
            while (var7_5 > FramePacer.margin) {
                v2 = var7_5;
                if (var1_2 == 0) {
                    GLFW.glfwWaitEventsTimeout((double)(v2 - FramePacer.margin));
                    var4_3 = GLFW.glfwGetTime();
                    var7_5 = FramePacer.deadline - var4_3;
                    if (var1_2 == 0) continue;
                    Module.a = Module.a == false;
                    break;
                }
                break block15;
            }
            v2 = var7_5;
        }
        v3 = 0.0;
        if (var1_2 != 0) // goto
        if (v2 < v3) {
            var6_4 = true;
        }
        do {
            v2 = GLFW.glfwGetTime();
            v3 = FramePacer.deadline;
lbl32:

            if (!(v2 < v3)) break;
            Thread.onSpinWait();
            if (var1_2 != 0) // goto
        } while (var1_2 == 0);
        if (var6_4) {
            v4 = Math.min(0.004, FramePacer.margin * 1.5 + 2.0E-4);
        } else lbl_neg_1000:

        {
            v4 = Math.max(3.0E-4, FramePacer.margin * 0.99);
        }
        FramePacer.margin = v4;
    }

    static {
        margin = 0.0012;
    }
}
