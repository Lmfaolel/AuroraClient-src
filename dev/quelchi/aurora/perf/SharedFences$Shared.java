package dev.quelchi.aurora.perf;

import com.mojang.blaze3d.buffers.GpuFence;
import dev.quelchi.aurora.perf.Tweaks;

final class SharedFences$Shared
implements GpuFence {
    private final GpuFence real;
    private int refs = 1;
    private boolean signalled;

    SharedFences$Shared(GpuFence gpuFence) {
        this.real = gpuFence;
    }

    public boolean awaitCompletion(long l) {
        SharedFences$Shared sharedFences$Shared;
        block4: {
            block5: {
                int n = Tweaks.a;
                sharedFences$Shared = this;
                if (n != 0) break block4;
                if (!sharedFences$Shared.signalled) break block5;
                return true;
            }
            this.signalled = this.real.awaitCompletion(l);
            sharedFences$Shared = this;
        }
        return sharedFences$Shared.signalled;
    }

    public void close() {
        block11: {
            SharedFences$Shared sharedFences$Shared;
            block10: {
                int n;
                block8: {
                    block9: {
                        n = Tweaks.a;
                        sharedFences$Shared = this;
                        if (n != 0) break block8;
                        if (sharedFences$Shared.refs > 0) break block9;
                        return;
                    }
                    sharedFences$Shared = this;
                }
                if (n != 0) break block10;
                if (--sharedFences$Shared.refs != 0) break block11;
                sharedFences$Shared = this.real;
            }
            sharedFences$Shared.close();
        }
    }
}
