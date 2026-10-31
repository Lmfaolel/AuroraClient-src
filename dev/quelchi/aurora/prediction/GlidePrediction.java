package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.optimize.LocalIds;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public final class GlidePrediction {
    private static boolean ours;
    private static final int ROCKET_TIMEOUT;
    private static final double REAL_ROCKET_RANGE = 6.0;
    private static FireworkRocketEntity localRocket;
    private static int rocketTicksLeft;
    private static final long[] a;
    private static final Integer[] b;

    private GlidePrediction() {
    }

    private static boolean on() {
        return Prediction.active();
    }

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(GlidePrediction::lambda$register$0);
    }

    public static void onPacketSent(Packet<?> packet) {
        block8: {
            Packet<?> packet2;
            block7: {
                boolean bl = PredictionHandshake.a;
                packet2 = packet;
                if (bl) break block7;
                if (!(packet2 instanceof ClientCommandC2SPacket)) break block8;
                packet2 = packet;
            }
            ClientCommandC2SPacket clientCommandC2SPacket = (ClientCommandC2SPacket)packet2;
            if (clientCommandC2SPacket.method_12365() == ClientCommandC2SPacket.Mode.field_12982) {
                ours = true;
            }
        }
    }

    public static boolean shouldStop(PlayerEntity playerEntity) {
        boolean bl;
        block28: {
            block29: {
                PlayerEntity playerEntity2;
                boolean bl2;
                block26: {
                    block27: {
                        block24: {
                            block25: {
                                block22: {
                                    block23: {
                                        boolean bl3;
                                        block20: {
                                            block21: {
                                                bl2 = PredictionHandshake.a;
                                                bl3 = GlidePrediction.on();
                                                if (bl2) break block20;
                                                if (!bl3) break block21;
                                                bl3 = ours;
                                                if (bl2) break block20;
                                                if (!bl3) break block21;
                                                playerEntity2 = playerEntity;
                                                if (bl2) break block22;
                                                if (playerEntity2 != null) break block23;
                                            }
                                            bl3 = false;
                                        }
                                        return bl3;
                                    }
                                    playerEntity2 = playerEntity;
                                }
                                if (bl2) break block24;
                                if (playerEntity2 == MinecraftClient.method_1551().field_1724) break block25;
                                return false;
                            }
                            playerEntity2 = playerEntity;
                        }
                        if (bl2) break block26;
                        if (playerEntity2.method_6128()) break block27;
                        return false;
                    }
                    playerEntity2 = playerEntity;
                }
                bl = GlidePrediction.canGlide(playerEntity2);
                if (bl2) break block28;
                if (bl) break block29;
                bl = true;
                break block28;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean canGlide(PlayerEntity playerEntity) {
        boolean bl;
        block27: {
            boolean bl2;
            block26: {
                boolean bl3;
                block25: {
                    PlayerEntity playerEntity2;
                    block23: {
                        block24: {
                            boolean bl4;
                            block21: {
                                block22: {
                                    PlayerEntity playerEntity3;
                                    block19: {
                                        block20: {
                                            bl2 = PredictionHandshake.a;
                                            playerEntity3 = playerEntity;
                                            if (bl2) break block19;
                                            if (!playerEntity3.method_31549().field_7479) break block20;
                                            return false;
                                        }
                                        playerEntity3 = playerEntity;
                                    }
                                    bl4 = playerEntity3.method_24828();
                                    if (bl2) break block21;
                                    if (bl4) break block22;
                                    playerEntity2 = playerEntity;
                                    if (bl2) break block23;
                                    if (!playerEntity2.method_5765()) break block24;
                                }
                                bl4 = false;
                            }
                            return bl4;
                        }
                        playerEntity2 = playerEntity;
                    }
                    bl3 = playerEntity2.method_6059(StatusEffects.field_5902);
                    if (bl2) break block25;
                    if (!bl3) break block26;
                    bl3 = false;
                }
                return bl3;
            }
            for (EquipmentSlot equipmentSlot : EquipmentSlot.field_54086) {
                block29: {
                    boolean bl5;
                    block28: {
                        bl = LivingEntity.method_63624((ItemStack)playerEntity.method_6118(equipmentSlot), (EquipmentSlot)equipmentSlot);
                        if (bl2) break block27;
                        if (bl2) break block28;
                        if (!bl) break block29;
                        bl5 = true;
                    }
                    return bl5;
                }
                if (!bl2) continue;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onRocketUse(ClientWorld clientWorld, PlayerEntity playerEntity, Hand hand) {
        block20: {
            PlayerEntity playerEntity2;
            block19: {
                boolean bl;
                block17: {
                    block18: {
                        bl = PredictionHandshake.a;
                        if (!GlidePrediction.on()) return;
                        playerEntity2 = playerEntity;
                        if (bl) break block17;
                        if (playerEntity2 == MinecraftClient.method_1551().field_1724) break block18;
                        return;
                    }
                    playerEntity2 = playerEntity;
                }
                if (bl) break block19;
                if (!playerEntity2.method_6128()) return;
                playerEntity2 = playerEntity;
            }
            if (!playerEntity2.method_7325() && localRocket == null) break block20;
            return;
        }
        ItemStack itemStack = playerEntity.method_5998(hand);
        if (!itemStack.method_31574(Items.field_8639)) {
            return;
        }
        FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity((World)clientWorld, itemStack, (LivingEntity)playerEntity);
        fireworkRocketEntity.method_5838(LocalIds.next());
        clientWorld.method_53875((Entity)fireworkRocketEntity);
        localRocket = fireworkRocketEntity;
        rocketTicksLeft = GlidePrediction.a(30106, 1370419618888893802L);
    }

    private static void tick() {
        block35: {
            block31: {
                int n;
                block34: {
                    int n2;
                    ClientPlayerEntity clientPlayerEntity;
                    MinecraftClient minecraftClient;
                    block33: {
                        boolean bl;
                        block32: {
                            block30: {
                                boolean bl2;
                                block29: {
                                    block28: {
                                        ClientPlayerEntity clientPlayerEntity2;
                                        block27: {
                                            minecraftClient = MinecraftClient.method_1551();
                                            clientPlayerEntity = minecraftClient.field_1724;
                                            bl = PredictionHandshake.a;
                                            clientPlayerEntity2 = clientPlayerEntity;
                                            if (bl) break block27;
                                            if (clientPlayerEntity2 == null) break block28;
                                            clientPlayerEntity2 = clientPlayerEntity;
                                        }
                                        bl2 = clientPlayerEntity2.method_6128();
                                        if (bl) break block29;
                                        if (bl2) break block30;
                                    }
                                    bl2 = false;
                                }
                                ours = bl2;
                            }
                            if (localRocket == null) {
                                return;
                            }
                            if (clientPlayerEntity == null || minecraftClient.field_1687 == null) break block31;
                            n2 = localRocket.method_31481();
                            if (bl) break block32;
                            if (n2 != 0) break block31;
                            n2 = clientPlayerEntity.method_6128();
                        }
                        if (bl) break block33;
                        if (n2 == 0) break block31;
                        n = rocketTicksLeft;
                        if (bl) break block34;
                        n2 = rocketTicksLeft = n - 1;
                    }
                    if (n2 <= 0) break block31;
                    n = GlidePrediction.realRocketArrived(minecraftClient.field_1687, (PlayerEntity)clientPlayerEntity) ? 1 : 0;
                }
                if (n == 0) break block35;
            }
            GlidePrediction.dropRocket();
        }
    }

    private static boolean realRocketArrived(ClientWorld clientWorld, PlayerEntity playerEntity) {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = PredictionHandshake.a;
                bl = clientWorld.method_8390(FireworkRocketEntity.class, playerEntity.method_5829().method_1014(6.0), GlidePrediction::lambda$realRocketArrived$1).isEmpty();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private static void dropRocket() {
        block3: {
            FireworkRocketEntity fireworkRocketEntity;
            block2: {
                boolean bl = PredictionHandshake.a;
                fireworkRocketEntity = localRocket;
                if (bl) break block2;
                if (fireworkRocketEntity == null) break block3;
                fireworkRocketEntity = localRocket;
            }
            fireworkRocketEntity.method_31472();
        }
        localRocket = null;
        rocketTicksLeft = 0;
    }

    private static boolean lambda$realRocketArrived$1(FireworkRocketEntity fireworkRocketEntity) {
        boolean bl = fireworkRocketEntity != localRocket;
        return bl;
    }

    private static void lambda$register$0(MinecraftClient minecraftClient) {
        GlidePrediction.tick();
    }

    
    static {
        long l = 1738260639685081534L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "\u00b6\u00cc\u00a5\u0002\u0010\u008e\u0016\u00ea\u00c1\\\u00a72ba\u00bc\u00c0";
        int n2 = "\u00b6\u00cc\u00a5\u0002\u0010\u008e\u0016\u00ea\u00c1\\\u00a72ba\u00bc\u00c0".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Integer[2];
        ROCKET_TIMEOUT = GlidePrediction.a(24497, 5635192631542083392L);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x30F1;
        if (b[n2] == null) {
            GlidePrediction.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
