package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.optimize.Ledger$Entry;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

public final class Ledger {
    private static final Map<Object, Ledger$Entry> ENTRIES = new LinkedHashMap<Object, Ledger$Entry>();
    private static int tick;
    private static WeakReference<ClientWorld> lastWorld;

    private Ledger() {
    }

    public static boolean isEmpty() {
        return ENTRIES.isEmpty();
    }

    
    public static void tick() {
        block28: {
            block29: {
                block23: {
                    block24: {
                        var1 = MinecraftClient.method_1551().field_1687;
                        var0_1 = ProjectileOptimizer.a;
                        v0 = var1;
                        if (var0_1) break block23;
                        if (v0 == Ledger.lastWorld.get()) break block24;
                        Ledger.ENTRIES.clear();
                        Ledger.lastWorld = new WeakReference<ClientWorld>(var1);
                        return;
                    }
                    v0 = var1;
                }
                if (v0 == null) {
                    Ledger.ENTRIES.clear();
                    return;
                }
                ++Ledger.tick;
                if (Ledger.ENTRIES.isEmpty()) {
                    return;
                }
                var2_2 = null;
                var3_3 = Ledger.ENTRIES.values().iterator();
                while (var3_3.hasNext()) {
                    v5 = var3_3.next();
                    if (!var0_1) {
                        block25: {
                            block26: {
                                block27: {
                                    var4_4 = v5;
                                    if (Ledger.tick < var4_4.expiresAt) break block25;
                                    v6 = var2_2;
                                    if (var0_1) break block26;
                                    if (v6 != null) break block27;
                                    var2_2 = new ArrayList<Ledger$Entry>(2);
                                }
                                v6 = var2_2;
                            }
                            v6.add(var4_4);
                        }
                        if (!var0_1) continue;
                    }
                }
                v9 = var2_2;
                if (var0_1) break block28;
                if (v9 != null) break block29;
                return;
            }
            v9 = var2_2;
        }
        var3_3 = v9.iterator();
        while (var3_3.hasNext()) {
            var4_4 = var3_3.next();
            v11 = Ledger.ENTRIES.remove(var4_4.key());
            if (!var0_1) {
                if (!var0_1) continue;
                break;
            }
        }
        for (Ledger$Entry v11 : var2_2) lbl_neg_1000:

        {
            v5 = v11;
lbl84:

            var4_4 = v5;
            var4_4.revert();
            if (!var0_1) continue;
        }
    }

    static {
        lastWorld = new WeakReference<Object>(null);
    }
}
