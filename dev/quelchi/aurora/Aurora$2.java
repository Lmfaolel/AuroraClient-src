package dev.quelchi.aurora;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.modules.NostalgiaModule;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

class Aurora$2
implements ClientTickEvents.EndTick {
    private boolean done;

    Aurora$2(Aurora aurora) {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onEndTick(MinecraftClient minecraftClient) {
        MinecraftClient minecraftClient2;
        block9: {
            boolean bl = Aurora.a;
            if (this.done) return;
            minecraftClient2 = minecraftClient;
            if (bl) break block9;
            if (minecraftClient2.field_1690 == null) return;
            minecraftClient2 = minecraftClient;
        }
        if (minecraftClient2.method_1520() == null) {
            return;
        }
        this.done = true;
        NostalgiaModule.reconcile();
    }
}
