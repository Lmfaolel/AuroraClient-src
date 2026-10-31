package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;

public final class InventoryHold {
    private static final long SLACK_MS;
    private static ItemStack beforeMain;
    private static ItemStack beforeOff;
    private static long until;
    private static int hotbarSlot;
    private static final Map<Integer, ItemStack> HELD;
    private static final long[] a;
    private static final Long[] b;

    private InventoryHold() {
    }

    public static void beforeClick() {
        block4: {
            ClientPlayerEntity clientPlayerEntity;
            block5: {
                boolean bl = PredictionHandshake.a;
                clientPlayerEntity = MinecraftClient.method_1551().field_1724;
                if (bl) break block4;
                if (clientPlayerEntity != null) break block5;
                return;
            }
            beforeMain = clientPlayerEntity.method_6047().method_7972();
            beforeOff = clientPlayerEntity.method_6079().method_7972();
        }
    }

    public static void afterClick() {
        block19: {
            ClientPlayerEntity clientPlayerEntity;
            block20: {
                boolean bl;
                boolean bl2;
                block16: {
                    block18: {
                        block17: {
                            bl2 = PredictionHandshake.a;
                            if (!Prediction.active()) {
                                return;
                            }
                            clientPlayerEntity = MinecraftClient.method_1551().field_1724;
                            if (clientPlayerEntity == null) {
                                return;
                            }
                            bl = ItemStack.method_7973((ItemStack)beforeMain, (ItemStack)clientPlayerEntity.method_6047());
                            if (bl2) break block16;
                            if (!bl) break block17;
                            bl = ItemStack.method_7973((ItemStack)beforeOff, (ItemStack)clientPlayerEntity.method_6079());
                            if (bl2) break block16;
                            if (bl) break block18;
                        }
                        bl = true;
                        break block16;
                    }
                    bl = false;
                }
                boolean bl3 = bl;
                if (bl2) break block19;
                if (bl3) break block20;
                return;
            }
            hotbarSlot = clientPlayerEntity.method_31548().method_67532();
            until = Util.method_658() + InventoryHold.selfLatency() * InventoryHold.a(5844, 1279232640257909878L) + InventoryHold.a(16823, 1316355759332471575L);
        }
    }

    public static boolean refuse(int n, ItemStack itemStack) {
        long l;
        block20: {
            block21: {
                boolean bl;
                block18: {
                    block19: {
                        long l2;
                        long l3;
                        block16: {
                            block17: {
                                bl = PredictionHandshake.a;
                                l3 = until;
                                l2 = 0L;
                                if (bl) break block16;
                                if (l3 >= l2) break block17;
                                return false;
                            }
                            l3 = Util.method_658();
                            l2 = until;
                        }
                        long l4 = l3 - l2;
                        l = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                        if (bl) break block18;
                        if (l <= 0) break block19;
                        InventoryHold.release();
                        return false;
                    }
                    l = n;
                }
                if (bl) break block20;
                if (l == 40) break block21;
                l = n;
                if (bl) break block20;
                if (l == hotbarSlot) break block21;
                return false;
            }
            HELD.put(n, itemStack.method_7972());
            l = 1;
        }
        return (boolean)l;
    }

    public static void onFullSync() {
        HELD.clear();
        until = InventoryHold.a(26396, 6022154383936230847L);
    }

    public static void tick() {
        block8: {
            long l;
            long l2;
            block7: {
                boolean bl = PredictionHandshake.a;
                l2 = until;
                l = 0L;
                if (bl) break block7;
                if (l2 < l) break block8;
                l2 = Util.method_658();
                l = until;
            }
            if (l2 > l) {
                InventoryHold.release();
            }
        }
    }

    private static void release() {
        block12: {
            block13: {
                until = InventoryHold.a(24526, 6927506066908949871L);
                boolean bl = PredictionHandshake.a;
                ClientPlayerEntity clientPlayerEntity = MinecraftClient.method_1551().field_1724;
                if (bl) break block12;
                if (clientPlayerEntity == null) break block13;
                for (Map.Entry<Integer, ItemStack> entry : HELD.entrySet()) {
                    block16: {
                        int n;
                        block15: {
                            int n2;
                            block14: {
                                n = entry.getKey();
                                ItemStack itemStack = clientPlayerEntity.method_31548().method_5438(n);
                                if (bl) break block12;
                                n2 = ItemStack.method_7973((ItemStack)itemStack, (ItemStack)entry.getValue());
                                if (bl) break block14;
                                if (n2 != 0) {
                                    continue;
                                }
                                n2 = n;
                            }
                            if (n2 != 40) break block15;
                            clientPlayerEntity.method_6122(Hand.field_5810, entry.getValue());
                            if (!bl) break block16;
                        }
                        clientPlayerEntity.method_31548().method_5447(n, entry.getValue());
                    }
                    if (!bl) continue;
                }
            }
            HELD.clear();
        }
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
        block8: {
            block7: {
                var0 = 713103729569070865L;
                var6_1 = new long[5];
                var3_2 = 0;
                var4_3 = "\u00969\u00f9\u00ff\u00de\u007f-\u0081\u001b\u00a1\u00d6\u0086\u00e1\u00f5L\u0090\u00a5\u0088vn\u00c2\u0013\u0015Q";
                var5_4 = "\u00969\u00f9\u00ff\u00de\u007f-\u0081\u001b\u00a1\u00d6\u0086\u00e1\u00f5L\u0090\u00a5\u0088vn\u00c2\u0013\u0015Q".length();
                var2_5 = 0;
                while (true) {
                    var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                    v0 = var6_1;
                    v1 = var3_2++;
                    v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                    v3 = -1;
                    break block7;
                    break;
                }
lbl14:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    var4_3 = "\u0018%\u00cb_[P\u001be\u0000\u00c9\u00ea\u00ae\u000e\u00ff\u00cb\u0016";
                    var5_4 = "\u0018%\u00cb_[P\u001be\u0000\u00c9\u00ea\u00ae\u000e\u00ff\u00cb\u0016".length();
                    var2_5 = 0;
                    while (true) {
                        var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                        v0 = var6_1;
                        v1 = var3_2++;
                        v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                        v3 = 0;
                        break block7;
                        break;
                    }
                    break;
                }
lbl27:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    break block8;
                    break;
                }
            }
            v4 = v2 ^ var0;
            switch (v3) {
                default: {
                    continue;
                }
                case 0:
lbl38:

                continue;
            }
        }
        InventoryHold.a = var6_1;
        InventoryHold.b = new Long[5];
        InventoryHold.SLACK_MS = InventoryHold.a(17972, 661077785640940689L);
        InventoryHold.beforeMain = ItemStack.field_8037;
        InventoryHold.beforeOff = ItemStack.field_8037;
        InventoryHold.until = InventoryHold.a(26396, 6022154383936230847L);
        InventoryHold.HELD = new HashMap<Integer, ItemStack>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x72A1) & Short.MAX_VALUE;
        if (b[n2] == null) {
            InventoryHold.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
