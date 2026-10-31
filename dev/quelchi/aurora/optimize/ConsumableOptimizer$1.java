package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.util.LinkedHashMap;
import java.util.Map;

class ConsumableOptimizer$1
extends LinkedHashMap<String, Boolean> {
    ConsumableOptimizer$1(int n, float f, boolean bl) {
        super(n, f, bl);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Boolean> entry) {
        boolean bl;
        block4: {
            block5: {
                boolean bl2 = ProjectileOptimizer.a;
                bl = this.size();
                if (bl2) break block4;
                if (bl <= 10 != 0) break block5;
                bl = true;
                break block4;
            }
            bl = false;
        }
        return bl;
    }
}
