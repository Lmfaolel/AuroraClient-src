package dev.quelchi.aurora.perf;

import com.mojang.blaze3d.buffers.GpuFence;
import dev.quelchi.aurora.perf.SharedFences$Shared;
import dev.quelchi.aurora.perf.Tweaks;
import net.fabricmc.loader.api.FabricLoader;

public final class SharedFences {
    public static boolean on;
    public static volatile boolean BYPASS;
    private static final boolean VULKAN;
    private static boolean open;
    private static SharedFences$Shared shared;

    private SharedFences() {
    }

    public static boolean active() {
        boolean bl;
        block9: {
            block7: {
                int n;
                block8: {
                    block6: {
                        n = Tweaks.a;
                        bl = on;
                        if (n != 0) break block6;
                        if (!bl) break block7;
                        bl = BYPASS;
                    }
                    if (n != 0) break block8;
                    if (bl) break block7;
                    bl = VULKAN;
                }
                if (n != 0) break block9;
                if (bl) break block7;
                bl = true;
                break block9;
            }
            bl = false;
        }
        return bl;
    }

    public static void open() {
        open = SharedFences.active();
        shared = null;
    }

    public static void close() {
        open = false;
        shared = null;
    }

    public static GpuFence reuse() {
        SharedFences$Shared sharedFences$Shared;
        block7: {
            block8: {
                block6: {
                    int n = Tweaks.a;
                    if (!open) break block6;
                    sharedFences$Shared = shared;
                    if (n != 0) break block7;
                    if (sharedFences$Shared != null) break block8;
                }
                return null;
            }
            ++SharedFences.shared.refs;
            sharedFences$Shared = shared;
        }
        return sharedFences$Shared;
    }

    public static GpuFence adopt(GpuFence gpuFence) {
        block6: {
            GpuFence gpuFence2;
            block5: {
                block4: {
                    int n = Tweaks.a;
                    if (!open) break block4;
                    gpuFence2 = gpuFence;
                    if (n != 0) break block5;
                    if (gpuFence2 != null) break block6;
                }
                gpuFence2 = gpuFence;
            }
            return gpuFence2;
        }
        shared = new SharedFences$Shared(gpuFence);
        return shared;
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "`Y\\{r(\u0015yH".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 22;
                    case 1 -> 44;
                    case 2 -> 48;
                    case 3 -> 16;
                    case 4 -> 19;
                    case 5 -> 70;
                    default -> 120;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        String string = new String(cArray).intern();
        on = true;
        VULKAN = FabricLoader.getInstance().isModLoaded(string);
    }
}
