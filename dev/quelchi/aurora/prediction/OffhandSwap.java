package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.prediction.OffhandSwap$Pending;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;

public final class OffhandSwap {
    private static final long SLACK_MS;
    private static OffhandSwap$Pending pending;
    private static final long[] a;
    private static final Long[] b;

    private OffhandSwap() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onSent(Packet<?> packet) {
        ClientPlayerEntity clientPlayerEntity;
        ClientPlayerEntity clientPlayerEntity2;
        block24: {
            block25: {
                boolean bl;
                block23: {
                    MinecraftClient minecraftClient;
                    block21: {
                        MinecraftClient minecraftClient2;
                        block22: {
                            Packet<?> packet2;
                            block20: {
                                bl = PredictionHandshake.a;
                                packet2 = packet;
                                if (bl) break block20;
                                if (!(packet2 instanceof PlayerActionC2SPacket)) return;
                                packet2 = packet;
                            }
                            PlayerActionC2SPacket playerActionC2SPacket = (PlayerActionC2SPacket)packet2;
                            if (playerActionC2SPacket.method_12363() != PlayerActionC2SPacket.Action.field_12969) {
                                return;
                            }
                            if (!Prediction.active()) {
                                return;
                            }
                            minecraftClient2 = MinecraftClient.method_1551();
                            minecraftClient = minecraftClient2;
                            if (bl) break block21;
                            if (minecraftClient.method_18854()) break block22;
                            return;
                        }
                        minecraftClient = minecraftClient2;
                    }
                    clientPlayerEntity2 = minecraftClient.field_1724;
                    clientPlayerEntity = clientPlayerEntity2;
                    if (bl) break block23;
                    if (clientPlayerEntity == null) return;
                    clientPlayerEntity = clientPlayerEntity2;
                }
                if (bl) break block24;
                if (!clientPlayerEntity.method_7325()) break block25;
                return;
            }
            clientPlayerEntity = clientPlayerEntity2;
        }
        ItemStack itemStack = clientPlayerEntity.method_6047();
        ItemStack itemStack2 = clientPlayerEntity2.method_6079();
        clientPlayerEntity2.method_6122(Hand.field_5810, itemStack);
        clientPlayerEntity2.method_6122(Hand.field_5808, itemStack2);
        clientPlayerEntity2.method_6021();
        pending = new OffhandSwap$Pending(itemStack.method_7972(), clientPlayerEntity2.method_31548().method_67532(), Util.method_658() + OffhandSwap.selfLatency() * OffhandSwap.a(13543, 4496613164843961855L) + OffhandSwap.a(28396, 5065443842136758261L));
    }

    public static boolean refuse(int n, ItemStack itemStack) {
        int n2;
        block19: {
            block20: {
                boolean bl;
                OffhandSwap$Pending offhandSwap$Pending;
                block15: {
                    block16: {
                        boolean bl2;
                        block17: {
                            block18: {
                                offhandSwap$Pending = pending;
                                bl = PredictionHandshake.a;
                                if (offhandSwap$Pending == null) {
                                    return false;
                                }
                                n2 = n;
                                if (bl) break block15;
                                if (n2 != 40) break block16;
                                bl2 = ItemStack.method_7973((ItemStack)itemStack, (ItemStack)offhandSwap$Pending.offhand);
                                if (bl) break block17;
                                if (!bl2) break block18;
                                pending = null;
                                OffhandSwap.applyHeldHotbar(offhandSwap$Pending);
                                return false;
                            }
                            offhandSwap$Pending.heldOffhand = itemStack.method_7972();
                            bl2 = true;
                        }
                        return bl2;
                    }
                    n2 = n;
                }
                if (bl) break block19;
                if (n2 != offhandSwap$Pending.hotbarSlot) break block20;
                offhandSwap$Pending.heldHotbar = itemStack.method_7972();
                return true;
            }
            n2 = 0;
        }
        return n2 != 0;
    }

    public static void tick() {
        OffhandSwap$Pending offhandSwap$Pending;
        block12: {
            OffhandSwap$Pending offhandSwap$Pending2;
            block13: {
                boolean bl;
                block11: {
                    offhandSwap$Pending2 = pending;
                    bl = PredictionHandshake.a;
                    if (offhandSwap$Pending2 != null && Util.method_658() > offhandSwap$Pending2.expires) break block11;
                    return;
                }
                pending = null;
                ClientPlayerEntity clientPlayerEntity = MinecraftClient.method_1551().field_1724;
                if (clientPlayerEntity == null) {
                    return;
                }
                offhandSwap$Pending = offhandSwap$Pending2;
                if (bl) break block12;
                if (offhandSwap$Pending.heldOffhand == null) break block13;
                clientPlayerEntity.method_6122(Hand.field_5810, offhandSwap$Pending2.heldOffhand);
            }
            offhandSwap$Pending = offhandSwap$Pending2;
        }
        OffhandSwap.applyHeldHotbar(offhandSwap$Pending);
    }

    private static void applyHeldHotbar(OffhandSwap$Pending offhandSwap$Pending) {
        ClientPlayerEntity clientPlayerEntity;
        block4: {
            clientPlayerEntity = MinecraftClient.method_1551().field_1724;
            if (clientPlayerEntity != null && offhandSwap$Pending.heldHotbar != null) break block4;
            return;
        }
        clientPlayerEntity.method_31548().method_5447(offhandSwap$Pending.hotbarSlot, offhandSwap$Pending.heldHotbar);
    }

    private static long selfLatency() {
        ClientPlayNetworkHandler clientPlayNetworkHandler;
        MinecraftClient minecraftClient;
        block6: {
            minecraftClient = MinecraftClient.method_1551();
            clientPlayNetworkHandler = minecraftClient.method_1562();
            if (clientPlayNetworkHandler != null && minecraftClient.field_1724 != null) break block6;
            return 0L;
        }
        PlayerListEntry playerListEntry = clientPlayNetworkHandler.method_2871(minecraftClient.field_1724.method_5667());
        long l = playerListEntry == null ? 0L : (long)Math.max(0, playerListEntry.method_2959());
        return l;
    }

    
    static {
        long l = 390504457064380055L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "C'K\u00ea>\u00c7 N;\ftexY\u00fbjF\u00f6\u000bF\u00a8\u0084\u00eb\u00c9";
        int n2 = "C'K\u00ea>\u00c7 N;\ftexY\u00fbjF\u00f6\u000bF\u00a8\u0084\u00eb\u00c9".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Long[3];
        SLACK_MS = OffhandSwap.a(9577, 4872141608767954034L);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x3919) & Short.MAX_VALUE;
        if (b[n2] == null) {
            OffhandSwap.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
