package dev.quelchi.aurora.perf;

import dev.quelchi.aurora.perf.Tweaks;

public final class FastModel {
    public static boolean on = true;
    public static volatile boolean BYPASS;

    private FastModel() {
    }

    public static boolean active() {
        boolean bl;
        block6: {
            block5: {
                int n;
                block4: {
                    n = Tweaks.a;
                    bl = on;
                    if (n != 0) break block4;
                    if (!bl) break block5;
                    bl = BYPASS;
                }
                if (n != 0) break block6;
                if (bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }
}
