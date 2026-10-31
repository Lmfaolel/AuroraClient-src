package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.mixins.LivingEntityAccessor;
import dev.quelchi.aurora.module.modules.OptimizersModule;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.sound.SoundEvent;

public final class CrossbowOptimizer {
    private static final CopyOnWriteArrayList<SoundEvent> PLAYED;
    private static int sinceShot;
    private static final long[] a;
    private static final Integer[] b;

    private CrossbowOptimizer() {
    }

    public static boolean on() {
        boolean bl;
        block16: {
            block15: {
                MinecraftClient minecraftClient;
                boolean bl2;
                block14: {
                    block13: {
                        boolean bl3;
                        block12: {
                            block11: {
                                OptimizersModule optimizersModule;
                                block10: {
                                    OptimizersModule optimizersModule2 = OptimizersModule.get();
                                    bl2 = ProjectileOptimizer.a;
                                    optimizersModule = optimizersModule2;
                                    if (bl2) break block10;
                                    if (optimizersModule == null) break block11;
                                    optimizersModule = optimizersModule2;
                                }
                                bl3 = optimizersModule.crossbow();
                                if (bl2) break block12;
                                if (bl3) break block13;
                            }
                            bl3 = false;
                        }
                        return bl3;
                    }
                    MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                    minecraftClient = minecraftClient2;
                    if (bl2) break block14;
                    if (minecraftClient.field_1724 == null) break block15;
                    minecraftClient = minecraftClient2;
                }
                bl = minecraftClient.method_1542();
                if (bl2) break block16;
                if (bl) break block15;
                bl = true;
                break block16;
            }
            bl = false;
        }
        return bl;
    }

    public static void tick() {
        block5: {
            int n;
            block4: {
                boolean bl = ProjectileOptimizer.a;
                n = sinceShot;
                if (bl) break block4;
                if (n >= CrossbowOptimizer.a(5935, 6181542602706641982L)) break block5;
                n = sinceShot + 1;
            }
            sinceShot = n;
        }
    }

    public static void onShoot() {
        sinceShot = 0;
    }

    private static boolean shotRecently() {
        boolean bl;
        block4: {
            block5: {
                boolean bl2 = ProjectileOptimizer.a;
                bl = sinceShot;
                if (bl2) break block4;
                if (bl >= 10 != 0) break block5;
                bl = true;
                break block4;
            }
            bl = false;
        }
        return bl;
    }

    public static void played(SoundEvent soundEvent) {
        if (soundEvent != null) {
            PLAYED.add(soundEvent);
        }
    }

    public static boolean suppressesSound(SoundEvent soundEvent) {
        block8: {
            boolean bl;
            block6: {
                block7: {
                    boolean bl2 = ProjectileOptimizer.a;
                    bl = CrossbowOptimizer.on();
                    if (bl2) break block6;
                    if (!bl) break block7;
                    if (soundEvent != null) break block8;
                }
                bl = false;
            }
            return bl;
        }
        return PLAYED.remove(soundEvent);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean filtersSlotUpdate(ScreenHandlerSlotUpdateS2CPacket screenHandlerSlotUpdateS2CPacket) {
        boolean bl;
        List list;
        ChargedProjectilesComponent chargedProjectilesComponent;
        block42: {
            ChargedProjectilesComponent chargedProjectilesComponent2;
            block43: {
                Object object;
                ItemStack itemStack;
                boolean bl2;
                block40: {
                    ItemStack itemStack2;
                    block41: {
                        byte n;
                        MinecraftClient minecraftClient;
                        block39: {
                            MinecraftClient minecraftClient2;
                            block38: {
                                block37: {
                                    bl2 = ProjectileOptimizer.a;
                                    boolean bl3 = CrossbowOptimizer.on();
                                    if (bl2) return bl3;
                                    if (bl3) break block37;
                                    return false;
                                }
                                minecraftClient = MinecraftClient.method_1551();
                                boolean bl4 = screenHandlerSlotUpdateS2CPacket.method_11452();
                                if (bl2) return bl4;
                                if (bl4) return false;
                                minecraftClient2 = minecraftClient;
                                if (bl2) break block38;
                                if (minecraftClient2.field_1755 != null) return false;
                                minecraftClient2 = minecraftClient;
                            }
                            if (minecraftClient2.field_1724 == null) {
                                return false;
                            }
                            n = screenHandlerSlotUpdateS2CPacket.method_11450();
                            byte n2 = n;
                            if (bl2) return n2 != 0;
                            if (n2 < 0) return 0 != 0;
                            n2 = n;
                            if (bl2) return n2 != 0;
                            if (n2 < minecraftClient.field_1724.field_7512.field_7761.size()) break block39;
                            return 0 != 0;
                        }
                        itemStack2 = screenHandlerSlotUpdateS2CPacket.method_11449();
                        itemStack = minecraftClient.field_1724.field_7512.method_7611(n).method_7677();
                        boolean bl5 = itemStack2.method_7909() instanceof CrossbowItem;
                        if (bl2) return bl5;
                        if (!bl5) return false;
                        object = itemStack;
                        if (bl2) break block40;
                        if (object.method_7909() instanceof CrossbowItem) break block41;
                        return false;
                    }
                    CrossbowOptimizer.syncExceptCharge(itemStack, itemStack2);
                    object = itemStack2.method_58695(DataComponentTypes.field_49649, (Object)ChargedProjectilesComponent.field_49298);
                }
                chargedProjectilesComponent2 = (ChargedProjectilesComponent)object;
                chargedProjectilesComponent = (ChargedProjectilesComponent)itemStack.method_58695(DataComponentTypes.field_49649, (Object)ChargedProjectilesComponent.field_49298);
                list = chargedProjectilesComponent2.method_57437();
                if (bl2) break block42;
                if (list.isEmpty()) break block43;
                bl = CrossbowOptimizer.shotRecently();
                if (bl2) return bl;
                if (!bl) break block43;
                return true;
            }
            list = chargedProjectilesComponent2.method_57437();
        }
        bl = list.equals(chargedProjectilesComponent.method_57437());
        return bl;
    }

    private static void syncExceptCharge(ItemStack itemStack, ItemStack itemStack2) {
        block6: {
            Object object;
            ChargedProjectilesComponent chargedProjectilesComponent;
            block7: {
                chargedProjectilesComponent = (ChargedProjectilesComponent)itemStack.method_58695(DataComponentTypes.field_49649, (Object)ChargedProjectilesComponent.field_49298);
                itemStack.method_7939(itemStack2.method_7947());
                itemStack.method_7974(itemStack2.method_7919());
                boolean bl = ProjectileOptimizer.a;
                EnchantmentHelper.method_57530((ItemStack)itemStack, (ItemEnchantmentsComponent)itemStack2.method_58657());
                CrossbowOptimizer.copy(itemStack, itemStack2, DataComponentTypes.field_49631);
                CrossbowOptimizer.copy(itemStack, itemStack2, DataComponentTypes.field_49632);
                object = itemStack;
                if (bl) break block6;
                CrossbowOptimizer.copy(object, itemStack2, DataComponentTypes.field_49637);
                if (!chargedProjectilesComponent.method_57442()) break block7;
                itemStack.method_57381(DataComponentTypes.field_49649);
                if (!bl) break block6;
            }
            object = itemStack.method_57379(DataComponentTypes.field_49649, (Object)chargedProjectilesComponent);
        }
    }

    private static <T> void copy(ItemStack itemStack, ItemStack itemStack2, ComponentType<T> componentType) {
        block6: {
            Object object;
            block7: {
                boolean bl = ProjectileOptimizer.a;
                object = itemStack2;
                if (bl) break block6;
                if (!object.method_57826(componentType)) break block7;
                itemStack.method_57379(componentType, itemStack2.method_58694(componentType));
                if (!bl) break block6;
            }
            object = itemStack.method_57381(componentType);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean filtersTracker(EntityTrackerUpdateS2CPacket entityTrackerUpdateS2CPacket) {
        List<DataTracker.SerializedEntry> list;
        MinecraftClient minecraftClient;
        block34: {
            boolean bl;
            block33: {
                MinecraftClient minecraftClient2;
                block31: {
                    block32: {
                        block29: {
                            block30: {
                                block28: {
                                    block27: {
                                        bl = ProjectileOptimizer.a;
                                        boolean bl2 = CrossbowOptimizer.on();
                                        if (bl) return bl2;
                                        if (bl2) break block27;
                                        return false;
                                    }
                                    minecraftClient = MinecraftClient.method_1551();
                                    minecraftClient2 = minecraftClient;
                                    if (bl) break block28;
                                    if (minecraftClient2.field_1687 == null) return false;
                                    minecraftClient2 = minecraftClient;
                                }
                                if (bl) break block29;
                                if (minecraftClient2.field_1724 != null) break block30;
                                return false;
                            }
                            minecraftClient2 = minecraftClient;
                        }
                        if (bl) break block31;
                        if (minecraftClient2.field_1687.method_8469(entityTrackerUpdateS2CPacket.comp_1127()) == minecraftClient.field_1724) break block32;
                        return false;
                    }
                    minecraftClient2 = minecraftClient;
                }
                boolean bl3 = minecraftClient2.field_1724.method_6115();
                if (bl) return bl3;
                if (!bl3) return false;
                bl3 = minecraftClient.field_1724.method_6030().method_7909() instanceof CrossbowItem;
                if (bl) return bl3;
                if (bl3) break block33;
                return false;
            }
            List list2 = entityTrackerUpdateS2CPacket.comp_1128();
            if (list2 == null) {
                return false;
            }
            int n = LivingEntityAccessor.aurora$livingFlags().comp_2327();
            list = list2.stream().filter(arg_0 -> CrossbowOptimizer.lambda$filtersTracker$0(n, arg_0)).toList();
            if (bl) return true;
            if (list.size() != list2.size()) break block34;
            return false;
        }
        minecraftClient.field_1724.method_5841().method_12779(list);
        return true;
    }

    public static void reset() {
        PLAYED.clear();
        sinceShot = CrossbowOptimizer.a(15247, 5723567924866762911L);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean lambda$filtersTracker$0(int n, DataTracker.SerializedEntry serializedEntry) {
        Object object;
        boolean bl;
        block16: {
            block17: {
                block14: {
                    block15: {
                        bl = ProjectileOptimizer.a;
                        object = serializedEntry;
                        if (bl) break block14;
                        if (object != null) break block15;
                        return false;
                    }
                    object = serializedEntry;
                }
                if (bl) break block16;
                if (object.comp_1115() == n) break block17;
                return true;
            }
            object = serializedEntry.comp_1117();
        }
        DataTracker.SerializedEntry serializedEntry2 = object;
        byte by = serializedEntry2 instanceof Byte;
        if (bl) return by != 0;
        if (by == 0) return 1 != 0;
        Byte by2 = (Byte)serializedEntry2;
        by = by2;
        if (bl) return by != 0;
        if ((by & 1) == 0) return 0 != 0;
        return 1 != 0;
    }

    
    static {
        long l = 5403211275023534254L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "\u0010H%\u000f8V\u00ff\u00ce2\u00c6<Z?\u0093\u00d3o";
        int n2 = "\u0010H%\u000f8V\u00ff\u00ce2\u00c6<Z?\u0093\u00d3o".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Integer[2];
        PLAYED = new CopyOnWriteArrayList();
        sinceShot = CrossbowOptimizer.a(15247, 5723567924866762911L);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7310;
        if (b[n2] == null) {
            CrossbowOptimizer.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
