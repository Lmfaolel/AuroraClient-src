package dev.quelchi.aurora.ambience;

import dev.quelchi.aurora.ambience.AmbienceDimensions$SkyCategory;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

public final class AmbienceDimensions {
    public static int a;

    private AmbienceDimensions() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isOverworldLike(ClientWorld clientWorld) {
        AmbienceDimensions$SkyCategory ambienceDimensions$SkyCategory;
        block6: {
            AmbienceDimensions$SkyCategory ambienceDimensions$SkyCategory2 = AmbienceDimensions.categoryOf(clientWorld);
            int n = a;
            ambienceDimensions$SkyCategory = ambienceDimensions$SkyCategory2;
            if (n != 0) break block6;
            if (ambienceDimensions$SkyCategory == AmbienceDimensions$SkyCategory.OVERWORLD) return true;
            ambienceDimensions$SkyCategory = ambienceDimensions$SkyCategory2;
        }
        if (ambienceDimensions$SkyCategory != AmbienceDimensions$SkyCategory.OTHER) return false;
        return true;
    }

    public static boolean isNether(ClientWorld clientWorld) {
        boolean bl = AmbienceDimensions.categoryOf(clientWorld) == AmbienceDimensions$SkyCategory.NETHER;
        return bl;
    }

    public static boolean isEnd(ClientWorld clientWorld) {
        boolean bl = AmbienceDimensions.categoryOf(clientWorld) == AmbienceDimensions$SkyCategory.END;
        return bl;
    }

    private static AmbienceDimensions$SkyCategory categoryOf(ClientWorld clientWorld) {
        ClientWorld clientWorld2;
        block28: {
            boolean bl;
            block26: {
                int n;
                block27: {
                    ClientWorld clientWorld3;
                    RegistryKey registryKey;
                    block24: {
                        block25: {
                            block22: {
                                block23: {
                                    n = a;
                                    if (clientWorld == null) {
                                        return AmbienceDimensions$SkyCategory.OTHER;
                                    }
                                    registryKey = World.field_25179;
                                    if (n != 0) break block22;
                                    if (!registryKey.equals(clientWorld.method_27983())) break block23;
                                    return AmbienceDimensions$SkyCategory.OVERWORLD;
                                }
                                registryKey = World.field_25180;
                            }
                            clientWorld3 = clientWorld;
                            if (n != 0) break block24;
                            if (!registryKey.equals(clientWorld3.method_27983())) break block25;
                            return AmbienceDimensions$SkyCategory.NETHER;
                        }
                        registryKey = World.field_25181;
                        clientWorld3 = clientWorld;
                    }
                    bl = registryKey.equals(clientWorld3.method_27983());
                    if (n != 0) break block26;
                    if (!bl) break block27;
                    return AmbienceDimensions$SkyCategory.END;
                }
                clientWorld2 = clientWorld;
                if (n != 0) break block28;
                bl = clientWorld2.method_8597().comp_642();
            }
            if (bl) {
                return AmbienceDimensions$SkyCategory.OVERWORLD;
            }
            clientWorld2 = clientWorld;
        }
        AmbienceDimensions$SkyCategory ambienceDimensions$SkyCategory = clientWorld2.method_8597().comp_643() ? AmbienceDimensions$SkyCategory.NETHER : AmbienceDimensions$SkyCategory.OTHER;
        return ambienceDimensions$SkyCategory;
    }
}
