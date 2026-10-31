package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.module.modules.OptimizersModule;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.util.Locale;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public final class ElytraOptimizer {
    private static final String[] SERVER_GLIDE_DOMAINS;

    private ElytraOptimizer() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean serverFliesYou() {
        String string;
        int n;
        ServerInfo serverInfo;
        boolean bl;
        block17: {
            ServerInfo serverInfo2;
            block18: {
                block16: {
                    serverInfo2 = MinecraftClient.method_1551().method_1558();
                    bl = ProjectileOptimizer.a;
                    serverInfo = serverInfo2;
                    if (bl) break block16;
                    if (serverInfo == null) return false;
                    serverInfo = serverInfo2;
                }
                if (bl) break block17;
                if (serverInfo.field_3761 != null) break block18;
                return false;
            }
            serverInfo = serverInfo2;
        }
        if ((n = (string = serverInfo.field_3761.toLowerCase(Locale.ROOT)).indexOf(58)) >= 0) {
            string = string.substring(0, n);
        }
        String[] stringArray = SERVER_GLIDE_DOMAINS;
        int n2 = stringArray.length;
        int n3 = 0;
        do {
            block19: {
                boolean bl2;
                if (n3 >= n2) return false;
                String string2 = stringArray[n3];
                boolean bl3 = string.equals(string2);
                if (bl) return bl3;
                if (bl) return bl2;
                if (bl3) return true;
                bl2 = string.endsWith("." + string2);
                if (bl) return bl2;
                if (!bl2) break block19;
                return true;
            }
            ++n3;
        } while (!bl);
        return false;
    }

    public static boolean stopGliding(PlayerEntity playerEntity) {
        boolean bl;
        block30: {
            boolean bl2;
            block29: {
                boolean bl3;
                block28: {
                    block26: {
                        PlayerEntity playerEntity2;
                        block27: {
                            block25: {
                                block24: {
                                    boolean bl4;
                                    block23: {
                                        block22: {
                                            OptimizersModule optimizersModule;
                                            block21: {
                                                OptimizersModule optimizersModule2 = OptimizersModule.get();
                                                bl2 = ProjectileOptimizer.a;
                                                optimizersModule = optimizersModule2;
                                                if (bl2) break block21;
                                                if (optimizersModule == null) break block22;
                                                optimizersModule = optimizersModule2;
                                            }
                                            bl4 = optimizersModule.elytra();
                                            if (bl2) break block23;
                                            if (!bl4) break block22;
                                            bl4 = ElytraOptimizer.serverFliesYou();
                                            if (bl2) break block23;
                                            if (!bl4) break block24;
                                        }
                                        bl4 = false;
                                    }
                                    return bl4;
                                }
                                playerEntity2 = playerEntity;
                                if (bl2) break block25;
                                if (playerEntity2 == null) break block26;
                                playerEntity2 = playerEntity;
                            }
                            if (bl2) break block27;
                            if (playerEntity2 != MinecraftClient.method_1551().field_1724) break block26;
                            playerEntity2 = playerEntity;
                        }
                        bl3 = playerEntity2.method_6128();
                        if (bl2) break block28;
                        if (bl3) break block29;
                    }
                    bl3 = false;
                }
                return bl3;
            }
            for (EquipmentSlot equipmentSlot : EquipmentSlot.field_54086) {
                block32: {
                    boolean bl5;
                    block31: {
                        bl = LivingEntity.method_63624((ItemStack)playerEntity.method_6118(equipmentSlot), (EquipmentSlot)equipmentSlot);
                        if (bl2) break block30;
                        if (bl2) break block31;
                        if (!bl) break block32;
                        bl5 = false;
                    }
                    return bl5;
                }
                if (!bl2) continue;
            }
            bl = true;
        }
        return bl;
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = ">\u0011Q`I\bA".toCharArray();
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
                    case 0 -> 93;
                    case 1 -> 97;
                    case 2 -> 39;
                    case 3 -> 16;
                    case 4 -> 103;
                    case 5 -> 111;
                    default -> 38;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        String string = new String(cArray).intern();
        SERVER_GLIDE_DOMAINS = new String[]{string};
    }
}
