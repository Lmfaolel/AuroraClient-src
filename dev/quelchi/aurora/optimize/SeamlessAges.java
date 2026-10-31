package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.util.HashMap;
import java.util.Map;

public final class SeamlessAges {
    private static final Map<Integer, Float> OFFSET = new HashMap<Integer, Float>();
    private static final Map<Integer, Float> PENDING = new HashMap<Integer, Float>();
    private static final Map<Integer, Float> RENDERED = new HashMap<Integer, Float>();

    private SeamlessAges() {
    }

    public static void recordRendered(int n, float f) {
        RENDERED.put(n, Float.valueOf(f));
    }

    public static float lastRendered(int n, float f) {
        return RENDERED.getOrDefault(n, Float.valueOf(f)).floatValue();
    }

    public static void link(int n, float f) {
        PENDING.put(n, Float.valueOf(f));
    }

    public static float apply(int n, float f) {
        block3: {
            Float f2;
            block2: {
                Float f3 = PENDING.remove(n);
                boolean bl = ProjectileOptimizer.a;
                f2 = f3;
                if (bl) break block2;
                if (f2 == null) break block3;
                f2 = f3;
            }
            float f4 = f2.floatValue() - f;
            OFFSET.put(n, Float.valueOf(f4));
            return f + f4;
        }
        return f + OFFSET.getOrDefault(n, Float.valueOf(0.0f)).floatValue();
    }

    public static void forget(int n) {
        OFFSET.remove(n);
        PENDING.remove(n);
        RENDERED.remove(n);
    }

    public static void clearAll() {
        OFFSET.clear();
        PENDING.clear();
        RENDERED.clear();
    }
}
