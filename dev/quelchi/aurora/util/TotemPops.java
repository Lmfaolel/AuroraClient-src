package dev.quelchi.aurora.util;

import dev.quelchi.aurora.module.modules.SoundControllerModule;
import dev.quelchi.aurora.util.KeyUtil;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;

public final class TotemPops {
    private static final Map<UUID, Integer> pops = new ConcurrentHashMap<UUID, Integer>();
    private static WeakReference<ClientWorld> lastWorld = new WeakReference<Object>(null);
    private static boolean selfWasDead;

    private TotemPops() {
    }

    public static void onPop(UUID uUID) {
        pops.merge(uUID, 1, Integer::sum);
        SoundControllerModule.onPop(uUID);
    }

    public static int get(UUID uUID) {
        return pops.getOrDefault(uUID, 0);
    }

    public static Map<UUID, Integer> all() {
        return pops;
    }

    public static void resetAll() {
        pops.clear();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void tick(MinecraftClient minecraftClient) {
        int n;
        block30: {
            boolean bl;
            block32: {
                boolean bl2;
                block31: {
                    MinecraftClient minecraftClient2;
                    block28: {
                        block29: {
                            block27: {
                                block25: {
                                    block26: {
                                        n = KeyUtil.a;
                                        minecraftClient2 = minecraftClient;
                                        if (n != 0) break block25;
                                        if (minecraftClient2.field_1687 == lastWorld.get()) break block26;
                                        lastWorld = new WeakReference<ClientWorld>(minecraftClient.field_1687);
                                        TotemPops.resetAll();
                                        selfWasDead = false;
                                        return;
                                    }
                                    minecraftClient2 = minecraftClient;
                                }
                                if (n != 0) break block27;
                                if (minecraftClient2.field_1687 == null) return;
                                minecraftClient2 = minecraftClient;
                            }
                            if (n != 0) break block28;
                            if (minecraftClient2.field_1724 != null) break block29;
                            return;
                        }
                        minecraftClient2 = minecraftClient;
                    }
                    bl2 = minecraftClient2.field_1724.method_29504();
                    if (n != 0) break block30;
                    if (!bl2) break block31;
                    bl = selfWasDead;
                    if (n != 0) break block32;
                    if (bl) break block31;
                    TotemPops.resetAll();
                }
                bl = bl2;
            }
            selfWasDead = bl;
        }
        for (UUID uUID : pops.keySet()) {
            block34: {
                Object object;
                block33: {
                    PlayerEntity playerEntity = minecraftClient.field_1687.method_18470(uUID);
                    object = playerEntity;
                    if (n != 0) break block33;
                    if (object == null) break block34;
                    object = playerEntity;
                }
                if (n != 0 || !object.method_29504()) break block34;
                object = pops.remove(uUID);
            }
            if (n == 0) continue;
        }
    }
}
