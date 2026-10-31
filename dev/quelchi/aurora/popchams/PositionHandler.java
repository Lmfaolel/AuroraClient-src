package dev.quelchi.aurora.popchams;

import dev.quelchi.aurora.module.modules.PopChamsModule;
import dev.quelchi.aurora.popchams.CapturedPlayer;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public final class PositionHandler {
    private static final Set<CapturedPlayer> positions = Collections.newSetFromMap(new ConcurrentHashMap());
    public static boolean a;

    private PositionHandler() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onTotemStatus(PlayerEntity playerEntity) {
        PlayerEntity playerEntity2;
        block18: {
            block17: {
                MinecraftClient minecraftClient;
                boolean bl;
                PopChamsModule popChamsModule;
                block16: {
                    PopChamsModule popChamsModule2;
                    block15: {
                        popChamsModule = PopChamsModule.get();
                        bl = a;
                        popChamsModule2 = popChamsModule;
                        if (bl) break block15;
                        if (popChamsModule2 == null) return;
                        popChamsModule2 = popChamsModule;
                    }
                    if (!popChamsModule2.isEnabled()) {
                        return;
                    }
                    minecraftClient = MinecraftClient.method_1551();
                    if (minecraftClient.field_1687 != null && playerEntity != null) break block16;
                    return;
                }
                if (popChamsModule.showOwnPops()) break block17;
                playerEntity2 = playerEntity;
                if (bl) break block18;
                if (playerEntity2 != minecraftClient.field_1724) break block17;
                return;
            }
            playerEntity2 = playerEntity;
        }
        PositionHandler.handleTotem(playerEntity2);
    }

    public static void handleTotem(PlayerEntity playerEntity) {
        positions.add(new CapturedPlayer(MinecraftClient.method_1551().field_1687, playerEntity));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void tick() {
        PopChamsModule popChamsModule;
        block7: {
            PopChamsModule popChamsModule2;
            block8: {
                boolean bl;
                block6: {
                    popChamsModule2 = PopChamsModule.get();
                    bl = a;
                    popChamsModule = popChamsModule2;
                    if (bl) break block6;
                    if (popChamsModule == null) return;
                    popChamsModule = popChamsModule2;
                }
                if (bl) break block7;
                if (popChamsModule.isEnabled()) break block8;
                return;
            }
            popChamsModule = popChamsModule2;
        }
        int n = popChamsModule.lifeTime();
        positions.forEach(arg_0 -> PositionHandler.lambda$tick$0(n, arg_0));
    }

    public static Set<CapturedPlayer> getPositions() {
        return positions;
    }

    public static void clear() {
        positions.clear();
    }

    private static void lambda$tick$0(int n, CapturedPlayer capturedPlayer) {
        capturedPlayer.tickAge(n);
    }
}
