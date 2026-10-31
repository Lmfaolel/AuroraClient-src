package dev.quelchi.aurora.shield;

import dev.quelchi.aurora.duck.UseState;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.shield.ShieldStates$Swing;
import java.lang.invoke.LambdaMetafactory;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public final class ShieldStates {
    private static final int DISABLE_TICKS;
    private static final long ATTACK_TTL_MS;
    private static final double MATCH_RADIUS = 5.0;
    private static final Map<UUID, Integer> cooldowns;
    private static final Map<UUID, Integer> useTicks;
    private static final Map<UUID, ShieldStates$Swing> swings;
    private static boolean localDisabled;
    public static boolean a;
    private static final long[] b;
    private static final Long[] c;

    private ShieldStates() {
    }

    public static void clear() {
        cooldowns.clear();
        useTicks.clear();
        swings.clear();
        localDisabled = false;
    }

    
    public static void update(boolean var0) {
        block40: {
            block42: {
                block41: {
                    block39: {
                        block38: {
                            block37: {
                                block34: {
                                    block33: {
                                        block32: {
                                            var2_1 = MinecraftClient.method_1551();
                                            var1_2 = ShieldStates.a;
                                            var3_3 = var2_1.field_1687;
                                            if (var1_2) break block32;
                                            if (var3_3 != null) break block33;
                                            ShieldStates.clear();
                                        }
                                        return;
                                    }
                                    var4_4 = new HashSet();
                                    for (PlayerEntity var6_7 : var3_3.method_18456()) {
                                        block35: {
                                            block36: {
                                                var7_8 = var6_7.method_5667();
                                                if (var1_2) break block34;
                                                v2 /* !! */  = var4_4;
                                                if (var1_2) break block35;
                                                v2 /* !! */ .add(var7_8);
                                                if (var6_7.method_6115()) {
                                                }
                                                v2 /* !! */  = var6_7;
                                                if (var1_2) break block35;
                                                if (ShieldStates.holdsUsableShield(v2 /* !! */ )) {
                                                }
                                                ShieldStates.useTicks.merge(var7_8, 1, (BiFunction<Integer, Integer, Integer>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, sum(int int ), (Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;)());
                                                if (var1_2) break block36;
                                                if (!var0) break block35;
                                                ShieldStates.repairBlocking(var6_7, ShieldStates.useTicks.get(var7_8));
                                            }
                                            if (!var1_2) break block35;
lbl68:

                                            v2 /* !! */  = ShieldStates.useTicks.put(var7_8, 0);
                                        }
                                        if (!var1_2) continue;
                                    }
                                    ShieldStates.useTicks.keySet().retainAll((Collection<?>)var4_4);
                                }
                                var5_6 = System.currentTimeMillis();
                                ShieldStates.swings.values().removeIf((Predicate<ShieldStates$Swing>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$update$0(long dev.quelchi.aurora.shield.ShieldStates$Swing ), (Ldev/quelchi/aurora/shield/ShieldStates$Swing;)Z)((long)var5_6));
                                ShieldStates.tickCooldowns();
                                var7_8 = var2_1.field_1724;
                                v11 = var7_8;
                                if (var1_2) break block37;
                                if (v11 == null) break block38;
                                v11 = var7_8;
                            }
                            v13 = ShieldStates.selfCoolingDown((PlayerEntity)v11);
                            if (var1_2) break block39;
                            if (!v13) break block38;
                            v13 = true;
                            break block39;
                        }
                        v13 = false;
                    }
                    ShieldStates.localDisabled = v13;
                    if (!Module.a) break block40;
                    if (!var1_2) break block41;
                    v16 = false;
                    break block42;
                }
                v16 = true;
            }
            ShieldStates.a = v16;
        }
    }

    
    private static void tickCooldowns() {
        Iterator<Map.Entry<UUID, Integer>> iterator = cooldowns.entrySet().iterator();
        boolean bl = a;
        while (iterator.hasNext()) {
            block8: {
                int n;
                Map.Entry<UUID, Integer> entry;
                block7: {
                    block6: {
                        entry = iterator.next();
                        n = entry.getValue() - 1;
                        if (bl) break block6;
                        if (n > 0) break block7;
                        iterator.remove();
                    }
                    if (!bl) break block8;
                }
                entry.setValue(n);
            }
            if (!bl) continue;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void repairBlocking(PlayerEntity playerEntity, int n) {
        PlayerEntity playerEntity2;
        ItemStack itemStack;
        boolean bl;
        block21: {
            boolean bl2;
            block19: {
                block20: {
                    PlayerEntity playerEntity3;
                    block17: {
                        block18: {
                            block15: {
                                block16: {
                                    bl = a;
                                    playerEntity3 = playerEntity;
                                    if (bl) break block15;
                                    if (playerEntity3 != MinecraftClient.method_1551().field_1724) break block16;
                                    return;
                                }
                                playerEntity3 = playerEntity;
                            }
                            if (bl) break block17;
                            if (!playerEntity3.method_6030().method_31574(Items.field_8255)) break block18;
                            return;
                        }
                        playerEntity3 = playerEntity;
                    }
                    itemStack = playerEntity3.method_5998(playerEntity.method_6058());
                    bl2 = itemStack.method_31574(Items.field_8255);
                    if (bl) break block19;
                    if (bl2) break block20;
                    return;
                }
                playerEntity2 = playerEntity;
                if (bl) break block21;
                bl2 = playerEntity2 instanceof UseState;
            }
            if (!bl2) return;
            playerEntity2 = playerEntity;
        }
        UseState useState = (UseState)playerEntity2;
        if (bl) {
            return;
        }
        int n2 = itemStack.method_7935((LivingEntity)playerEntity);
        useState.aurora$setActiveItem(itemStack);
        useState.aurora$setItemUseTime(n2 - Math.min(n, n2));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onAttack(PlayerEntity playerEntity) {
        boolean bl;
        ClientPlayerEntity clientPlayerEntity;
        block19: {
            block20: {
                Object object;
                boolean bl2;
                block17: {
                    block18: {
                        ClientPlayerEntity clientPlayerEntity2;
                        block16: {
                            block15: {
                                MinecraftClient minecraftClient = MinecraftClient.method_1551();
                                clientPlayerEntity = minecraftClient.field_1724;
                                bl2 = a;
                                clientPlayerEntity2 = clientPlayerEntity;
                                if (bl2) break block15;
                                if (clientPlayerEntity2 == null) return;
                                clientPlayerEntity2 = playerEntity;
                            }
                            if (bl2) break block16;
                            if (clientPlayerEntity2 == null) return;
                            clientPlayerEntity2 = playerEntity;
                        }
                        if (clientPlayerEntity2 == clientPlayerEntity) {
                            return;
                        }
                        object = clientPlayerEntity;
                        if (bl2) break block17;
                        if (object.method_59958().method_7909() instanceof AxeItem) break block18;
                        return;
                    }
                    object = useTicks.getOrDefault(playerEntity.method_5667(), 0);
                }
                bl = ((Integer)object).intValue();
                if (bl2) break block19;
                if (bl < 3 != 0) break block20;
                bl = true;
                break block19;
            }
            bl = false;
        }
        boolean bl3 = bl;
        swings.put(playerEntity.method_5667(), new ShieldStates$Swing(clientPlayerEntity.method_73189(), playerEntity.method_73189(), System.currentTimeMillis(), bl3));
    }

    public static void onShieldBreakStatus(PlayerEntity playerEntity) {
        PlayerEntity playerEntity2;
        block8: {
            block9: {
                boolean bl;
                block6: {
                    block7: {
                        bl = a;
                        playerEntity2 = playerEntity;
                        if (bl) break block6;
                        if (playerEntity2 != null) break block7;
                        return;
                    }
                    playerEntity2 = playerEntity;
                }
                if (bl) break block8;
                if (playerEntity2 != MinecraftClient.method_1551().field_1724) break block9;
                return;
            }
            playerEntity2 = playerEntity;
        }
        ShieldStates.disable(playerEntity2.method_5667());
    }

    public static void onShieldBreakSound(double d, double d2, double d3) {
        int n;
        UUID uUID;
        block88: {
            int n2;
            block89: {
                double d4;
                block92: {
                    block94: {
                        block93: {
                            double d5;
                            double d6;
                            double d7;
                            boolean bl;
                            block91: {
                                block90: {
                                    block85: {
                                        block84: {
                                            double d8;
                                            double d9;
                                            double d10;
                                            block86: {
                                                double d11;
                                                block87: {
                                                    ShieldStates$Swing shieldStates$Swing;
                                                    block83: {
                                                        double d12;
                                                        block81: {
                                                            double d13;
                                                            block82: {
                                                                block80: {
                                                                    double d14;
                                                                    block79: {
                                                                        block69: {
                                                                            block68: {
                                                                                block67: {
                                                                                    MinecraftClient minecraftClient = MinecraftClient.method_1551();
                                                                                    bl = a;
                                                                                    ClientWorld clientWorld = minecraftClient.field_1687;
                                                                                    if (clientWorld == null || minecraftClient.field_1724 == null) break block67;
                                                                                    if (!swings.isEmpty()) break block68;
                                                                                }
                                                                                return;
                                                                            }
                                                                            long l = System.currentTimeMillis();
                                                                            d13 = 25.0;
                                                                            uUID = null;
                                                                            d7 = Double.NEGATIVE_INFINITY;
                                                                            d10 = Double.POSITIVE_INFINITY;
                                                                            d11 = 0.0;
                                                                            for (Map.Entry<UUID, ShieldStates$Swing> entry : swings.entrySet()) {
                                                                                block78: {
                                                                                    double d15;
                                                                                    int n3;
                                                                                    double d16;
                                                                                    double d17;
                                                                                    double d18;
                                                                                    block75: {
                                                                                        double d19;
                                                                                        double d20;
                                                                                        block77: {
                                                                                            block76: {
                                                                                                block73: {
                                                                                                    block74: {
                                                                                                        double d21;
                                                                                                        ShieldStates$Swing shieldStates$Swing2;
                                                                                                        block71: {
                                                                                                            shieldStates$Swing2 = entry.getValue();
                                                                                                            if (bl) break block69;
                                                                                                            if (l - shieldStates$Swing2.time() > ShieldStates.a(12565, 1607398368507139560L)) {
                                                                                                                continue;
                                                                                                            }
                                                                                                            d18 = shieldStates$Swing2.attacker().method_1028(d, d2, d3);
                                                                                                            d17 = shieldStates$Swing2.target().method_1028(d, d2, d3);
                                                                                                            d21 = d17;
                                                                                                            if (bl) break block71;
                                                                                                            if (d21 > d13) {
                                                                                                                continue;
                                                                                                            }
                                                                                                            d21 = Math.max(0.0, d13 - d17) / d13;
                                                                                                        }
                                                                                                        d16 = d21;
                                                                                                        n3 = shieldStates$Swing2.wasBlocking();
                                                                                                        if (bl) break block73;
                                                                                                        if (n3 == 0) break block74;
                                                                                                        d16 += 0.3;
                                                                                                    }
                                                                                                    n3 = localDisabled;
                                                                                                }
                                                                                                if (bl) break block75;
                                                                                                if (n3 != 0) break block76;
                                                                                                double d20 = d18;
                                                                                                d20 = 0.01;
                                                                                                if (bl) break block77;
                                                                                                if (!(d15 + d20 < d17)) break block76;
                                                                                                d16 -= 0.35;
                                                                                            }
                                                                                            d15 = d16;
                                                                                            if (bl) break block78;
                                                                                            d20 = d7;
                                                                                        }
                                                                                        n3 = (d19 = d15 - d20) == 0.0 ? 0 : (d19 > 0.0 ? 1 : -1);
                                                                                    }
                                                                                    if (n3 > 0) {
                                                                                        d7 = d16;
                                                                                        uUID = entry.getKey();
                                                                                        d10 = d18;
                                                                                        d15 = d11 = d17;
                                                                                    }
                                                                                }
                                                                                if (!bl) continue;
                                                                            }
                                                                            if (!localDisabled) break block69;
                                                                            d14 = 0.05;
                                                                            break block79;
                                                                        }
                                                                        d14 = 0.25;
                                                                    }
                                                                    double d22 = d14;
                                                                    if (uUID == null) break block80;
                                                                    d12 = d7;
                                                                    if (bl) break block81;
                                                                    if (!(d12 <= d22)) break block82;
                                                                }
                                                                return;
                                                            }
                                                            d12 = Math.max(0.0, d13 - d10) / d13;
                                                        }
                                                        d6 = d12;
                                                        n2 = 0;
                                                        ShieldStates$Swing shieldStates$Swing3 = swings.get(uUID);
                                                        shieldStates$Swing = shieldStates$Swing3;
                                                        if (bl) break block83;
                                                        if (shieldStates$Swing == null) break block84;
                                                        shieldStates$Swing = shieldStates$Swing3;
                                                    }
                                                    n = shieldStates$Swing.wasBlocking();
                                                    if (bl) break block85;
                                                    if (n == 0) break block84;
                                                    double d8 = d11;
                                                    d8 = d10;
                                                    if (bl) break block86;
                                                    if (!(d9 <= d8 + 0.05)) break block87;
                                                    n2 = 1;
                                                }
                                                double d8 = d11;
                                                d8 = 0.5;
                                            }
                                            double d22 = d9 - d8;
                                            n = d22 == 0.0 ? 0 : (d22 < 0.0 ? -1 : 1);
                                            if (bl) break block85;
                                            if (n > 0) break block84;
                                            double d23 = d10 - 0.5;
                                            n = d23 == 0.0 ? 0 : (d23 < 0.0 ? -1 : 1);
                                            if (bl) break block85;
                                            if (n > 0) break block84;
                                            n2 = 1;
                                        }
                                        n = n2;
                                    }
                                    if (bl) break block88;
                                    if (n != 0) break block89;
                                    if (!localDisabled) break block90;
                                    d5 = 1.02;
                                    break block91;
                                }
                                d5 = 1.05;
                            }
                            double d25 = d5;
                            double d24 = localDisabled ? 0.05 : 0.08;
                            double d27 = d24;
                            double d4 = d7 - d6 * d25;
                            d4 = d4 == 0.0 ? 0 : (d4 > 0.0 ? 1 : -1);
                            if (bl) break block92;
                            if (d4 > 0) break block93;
                            double d4 = d7 - d6 - d27;
                            d4 = d4 == 0.0 ? 0 : (d4 > 0.0 ? 1 : -1);
                            if (bl) break block92;
                            if (d4 <= 0) break block94;
                        }
                        d4 = 1.0;
                        break block92;
                    }
                    d4 = 0.0;
                }
                n2 = (int)d4;
            }
            n = n2;
        }
        if (n != 0) {
            ShieldStates.disable(uUID);
            swings.remove(uUID);
        }
    }

    private static void disable(UUID uUID) {
        block4: {
            boolean bl = a;
            Comparable<UUID> comparable = uUID;
            if (bl || comparable == null) break block4;
            comparable = cooldowns.put(uUID, 100);
        }
    }

    public static boolean isCoolingDown(PlayerEntity playerEntity) {
        boolean bl;
        block14: {
            block15: {
                Object object;
                boolean bl2;
                block12: {
                    block13: {
                        block10: {
                            block11: {
                                bl2 = a;
                                object = playerEntity;
                                if (bl2) break block10;
                                if (object != null) break block11;
                                return false;
                            }
                            object = playerEntity;
                        }
                        if (bl2) break block12;
                        if (object != MinecraftClient.method_1551().field_1724) break block13;
                        return ShieldStates.selfCoolingDown(playerEntity);
                    }
                    object = cooldowns.getOrDefault(playerEntity.method_5667(), 0);
                }
                bl = ((Integer)object).intValue();
                if (bl2) break block14;
                if (bl <= false) break block15;
                bl = true;
                break block14;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean selfCoolingDown(PlayerEntity playerEntity) {
        return playerEntity.method_7357().method_7904(new ItemStack((ItemConvertible)Items.field_8255));
    }

    public static boolean isBlocking(PlayerEntity playerEntity) {
        int n;
        block10: {
            block9: {
                Object object;
                boolean bl;
                block8: {
                    bl = a;
                    object = playerEntity;
                    if (bl) break block8;
                    if (object == null) break block9;
                    object = useTicks.getOrDefault(playerEntity.method_5667(), 0);
                }
                n = (Integer)object;
                if (bl) break block10;
                if (n < 5) break block9;
                n = 1;
                break block10;
            }
            n = 0;
        }
        return n != 0;
    }

    public static boolean holdsUsableShield(PlayerEntity playerEntity) {
        int n;
        block25: {
            block27: {
                block26: {
                    boolean bl;
                    block24: {
                        boolean bl2;
                        block23: {
                            boolean bl3;
                            block20: {
                                block22: {
                                    block21: {
                                        PlayerEntity playerEntity2;
                                        block18: {
                                            block19: {
                                                bl = a;
                                                playerEntity2 = playerEntity;
                                                if (bl) break block18;
                                                if (playerEntity2 != null) break block19;
                                                return false;
                                            }
                                            playerEntity2 = playerEntity;
                                        }
                                        bl3 = playerEntity2.method_6047().method_31574(Items.field_8255);
                                        if (bl) break block20;
                                        if (bl3) break block21;
                                        bl3 = playerEntity.method_6079().method_31574(Items.field_8255);
                                        if (bl) break block20;
                                        if (!bl3) break block22;
                                    }
                                    bl3 = true;
                                    break block20;
                                }
                                bl3 = false;
                            }
                            boolean bl4 = bl3;
                            bl2 = bl4;
                            if (bl) break block23;
                            if (bl2) break block24;
                            bl2 = false;
                        }
                        return bl2;
                    }
                    ItemStack itemStack = playerEntity.method_6047();
                    n = itemStack.method_31574(Items.field_8255);
                    if (bl) break block25;
                    if (n != 0) break block26;
                    n = itemStack.method_7935((LivingEntity)playerEntity);
                    if (bl) break block25;
                    if (n != 0) break block27;
                }
                n = 1;
                break block25;
            }
            n = 0;
        }
        return n != 0;
    }

    private static boolean lambda$update$0(long l, ShieldStates$Swing shieldStates$Swing) {
        long l2;
        block2: {
            block3: {
                boolean bl = a;
                long l3 = l - shieldStates$Swing.time() - ShieldStates.a(13981, 5289610870358250082L);
                l2 = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                if (bl) break block2;
                if (l2 <= 0) break block3;
                l2 = 1;
                break block2;
            }
            l2 = 0;
        }
        return (boolean)l2;
    }

    
    static {
        long l = 7181313053524960314L;
        long l2 = 0x39B3D3A3883B245EL ^ l;
        DISABLE_TICKS = (int)l2;
        long l3 = 5424615120071455401L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "\u0002 c\u00bad\u00ad\u00e3#\u001f\u000b\f\u00ed\u0006\u0086\u00a5\u00c9]\u0006\u00ba\u00f9D{d\u00a9";
        int n2 = "\u0002 c\u00bad\u00ad\u00e3#\u001f\u000b\f\u00ed\u0006\u0086\u00a5\u00c9]\u0006\u00ba\u00f9D{d\u00a9".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l3;
        } while (n3 < n2);
        b = lArray;
        c = new Long[3];
        ATTACK_TTL_MS = ShieldStates.a(28790, 6071721294092883080L);
        cooldowns = new HashMap<UUID, Integer>();
        useTicks = new HashMap<UUID, Integer>();
        swings = new HashMap<UUID, ShieldStates$Swing>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x24FF) & Short.MAX_VALUE;
        if (c[n2] == null) {
            ShieldStates.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
