package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.mixins.LivingEntityAccessor;
import dev.quelchi.aurora.module.modules.OptimizersModule;
import dev.quelchi.aurora.optimize.ConsumableOptimizer$1;
import dev.quelchi.aurora.optimize.ConsumableOptimizer$Disable;
import dev.quelchi.aurora.optimize.ConsumableOptimizer$Handshake;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.consume.UseAction;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

public final class ConsumableOptimizer {
    private static final long WAIT_MS;
    private static final long TRACKER_MS;
    private static volatile boolean serverDisabled;
    private static final Map<String, Boolean> OPTED_OUT_SERVERS;
    private static boolean waiting;
    private static long waitSince;
    private static boolean suppressBurp;
    private static SoundEvent suppressedSound;
    private static boolean suppressSwapAnimation;
    private static int trackerState;
    private static long trackerAt;
    private static final String NAMESPACE;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private ConsumableOptimizer() {
    }

    private static String serverKey(MinecraftClient minecraftClient) {
        String string;
        block13: {
            ServerInfo serverInfo;
            block11: {
                ServerInfo serverInfo2;
                block12: {
                    ServerInfo serverInfo3;
                    boolean bl;
                    block10: {
                        MinecraftClient minecraftClient2;
                        block8: {
                            block9: {
                                bl = ProjectileOptimizer.a;
                                minecraftClient2 = minecraftClient;
                                if (bl) break block8;
                                if (minecraftClient2 != null) break block9;
                                serverInfo3 = null;
                                break block10;
                            }
                            minecraftClient2 = minecraftClient;
                        }
                        serverInfo3 = minecraftClient2.method_1558();
                    }
                    serverInfo2 = serverInfo3;
                    serverInfo = serverInfo2;
                    if (bl) break block11;
                    if (serverInfo != null) break block12;
                    string = null;
                    break block13;
                }
                serverInfo = serverInfo2;
            }
            string = serverInfo.field_3761;
        }
        return string;
    }

    public static boolean refused() {
        return serverDisabled;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean on() {
        boolean bl;
        boolean bl2;
        block15: {
            block16: {
                block13: {
                    block14: {
                        OptimizersModule optimizersModule;
                        block12: {
                            OptimizersModule optimizersModule2 = OptimizersModule.get();
                            bl2 = ProjectileOptimizer.a;
                            optimizersModule = optimizersModule2;
                            if (bl2) break block12;
                            if (optimizersModule == null) return false;
                            optimizersModule = optimizersModule2;
                        }
                        bl = optimizersModule.consumables();
                        if (bl2) break block13;
                        if (bl) break block14;
                        return false;
                    }
                    bl = serverDisabled;
                }
                if (bl2) break block15;
                if (!bl) break block16;
                return false;
            }
            bl = MinecraftClient.method_1551().method_1542();
        }
        if (bl2) return bl;
        if (bl) return false;
        return true;
    }

    
    public static boolean tickItemUse(ItemStack itemStack) {
        ClientPlayerEntity clientPlayerEntity;
        ConsumableComponent consumableComponent;
        ClientPlayerEntity clientPlayerEntity2;
        boolean bl;
        block25: {
            block26: {
                MinecraftClient minecraftClient;
                block27: {
                    MinecraftClient minecraftClient2;
                    block24: {
                        boolean bl2;
                        block23: {
                            bl = ProjectileOptimizer.a;
                            boolean bl3 = ConsumableOptimizer.on();
                            if (bl) return bl3;
                            if (!bl3) {
                                return false;
                            }
                            minecraftClient2 = MinecraftClient.method_1551();
                            Object object = clientPlayerEntity2 = minecraftClient2.field_1724;
                            if (!bl) {
                                if (object == null) {
                                    return false;
                                }
                                object = itemStack.method_58694(DataComponentTypes.field_53964);
                            }
                            ConsumableComponent consumableComponent2 = consumableComponent = (ConsumableComponent)object;
                            if (!bl) {
                                if (consumableComponent2 == null) {
                                    return false;
                                }
                                consumableComponent2 = consumableComponent;
                            }
                            bl2 = consumableComponent2.method_62844((LivingEntity)clientPlayerEntity2, itemStack);
                            if (bl) break block23;
                            if (!bl2) break block24;
                            clientPlayerEntity = clientPlayerEntity2;
                            if (bl) break block25;
                            bl2 = clientPlayerEntity.method_7357().method_7904(itemStack);
                        }
                        if (!bl2) break block26;
                    }
                    minecraftClient = minecraftClient2;
                    if (bl) break block27;
                    if (minecraftClient.field_1761 == null) break block26;
                    minecraftClient = minecraftClient2;
                }
                minecraftClient.field_1761.method_2897((PlayerEntity)clientPlayerEntity2);
            }
            clientPlayerEntity = clientPlayerEntity2;
        }
        if (!bl) {
            if (clientPlayerEntity.method_6014() > 0) {
                return false;
            }
            ConsumableOptimizer.playFinishSounds(clientPlayerEntity2, itemStack, consumableComponent);
            clientPlayerEntity = clientPlayerEntity2;
        }
        ConsumableOptimizer.finishLocally(clientPlayerEntity);
        return true;
    }

    private static void finishLocally(ClientPlayerEntity clientPlayerEntity) {
        block25: {
            block26: {
                Object object;
                ItemStack itemStack;
                boolean bl;
                block23: {
                    block24: {
                        ClientPlayerEntity clientPlayerEntity2;
                        block19: {
                            block20: {
                                block22: {
                                    MinecraftClient minecraftClient;
                                    block21: {
                                        bl = ProjectileOptimizer.a;
                                        if (!clientPlayerEntity.method_6115()) {
                                            return;
                                        }
                                        MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                                        Hand hand = clientPlayerEntity.method_6058();
                                        clientPlayerEntity2 = clientPlayerEntity;
                                        if (bl) break block19;
                                        if (clientPlayerEntity2.method_6030().equals(clientPlayerEntity.method_5998(hand))) break block20;
                                        minecraftClient = minecraftClient2;
                                        if (bl) break block21;
                                        if (minecraftClient.field_1761 == null) break block22;
                                        minecraftClient = minecraftClient2;
                                    }
                                    minecraftClient.field_1761.method_2897((PlayerEntity)clientPlayerEntity);
                                }
                                return;
                            }
                            clientPlayerEntity2 = clientPlayerEntity;
                        }
                        itemStack = clientPlayerEntity2.method_6030();
                        object = itemStack;
                        if (bl) break block23;
                        if (!object.method_7960()) break block24;
                        return;
                    }
                    object = itemStack.method_58694(DataComponentTypes.field_53964);
                }
                ConsumableComponent consumableComponent = (ConsumableComponent)object;
                if (bl) break block25;
                if (consumableComponent == null) break block26;
                consumableComponent.method_62843(clientPlayerEntity.method_59922(), (LivingEntity)clientPlayerEntity, itemStack, 16);
                suppressSwapAnimation = true;
            }
            clientPlayerEntity.method_6021();
            waiting = true;
            waitSince = System.currentTimeMillis();
            trackerState = 1;
            trackerAt = System.currentTimeMillis();
        }
    }

    private static void playFinishSounds(ClientPlayerEntity clientPlayerEntity, ItemStack itemStack, ConsumableComponent consumableComponent) {
        block11: {
            boolean bl;
            block10: {
                SoundEvent soundEvent;
                ClientPlayerEntity clientPlayerEntity2;
                block8: {
                    block9: {
                        SoundEvent soundEvent2;
                        suppressedSound = soundEvent2 = (SoundEvent)consumableComponent.comp_3087().comp_349();
                        boolean bl2 = ProjectileOptimizer.a;
                        clientPlayerEntity2 = clientPlayerEntity;
                        soundEvent = soundEvent2;
                        if (bl2) break block8;
                        ConsumableOptimizer.play(clientPlayerEntity2, soundEvent, 1.0f, clientPlayerEntity.method_59922().method_62816(1.0f, 0.4f));
                        if (itemStack.method_7976() == UseAction.field_8946) break block9;
                        bl = itemStack.method_31574(Items.field_20417);
                        if (bl2) break block10;
                        if (!bl) break block11;
                    }
                    clientPlayerEntity2 = clientPlayerEntity;
                    soundEvent = SoundEvents.field_19149;
                }
                ConsumableOptimizer.play(clientPlayerEntity2, soundEvent, 0.5f, MathHelper.method_32750((Random)clientPlayerEntity.method_59922(), (float)0.9f, (float)1.0f));
                bl = true;
            }
            suppressBurp = bl;
        }
    }

    private static void play(ClientPlayerEntity clientPlayerEntity, SoundEvent soundEvent, float f, float f2) {
        ClientPlayerEntity clientPlayerEntity2;
        block4: {
            block5: {
                boolean bl = ProjectileOptimizer.a;
                clientPlayerEntity2 = clientPlayerEntity;
                if (bl) break block4;
                if (clientPlayerEntity2.method_73183() != null) break block5;
                return;
            }
            clientPlayerEntity2 = clientPlayerEntity;
        }
        clientPlayerEntity2.method_73183().method_43128((Entity)clientPlayerEntity, clientPlayerEntity.method_23317(), clientPlayerEntity.method_23318(), clientPlayerEntity.method_23321(), soundEvent, SoundCategory.field_15248, f, f2);
    }

    public static boolean suppressesSound(SoundEvent soundEvent) {
        boolean bl;
        block25: {
            block24: {
                SoundEvent soundEvent2;
                boolean bl2;
                block23: {
                    block22: {
                        block21: {
                            block20: {
                                boolean bl3;
                                block18: {
                                    block19: {
                                        bl2 = ProjectileOptimizer.a;
                                        bl3 = ConsumableOptimizer.on();
                                        if (bl2) break block18;
                                        if (!bl3) break block19;
                                        if (soundEvent != null) break block20;
                                    }
                                    bl3 = false;
                                }
                                return bl3;
                            }
                            if (!suppressBurp) break block21;
                            soundEvent2 = soundEvent;
                            if (bl2) break block22;
                            if (soundEvent2 != SoundEvents.field_19149) break block21;
                            suppressBurp = false;
                            return true;
                        }
                        soundEvent2 = suppressedSound;
                    }
                    if (bl2) break block23;
                    if (soundEvent2 == null) break block24;
                    soundEvent2 = suppressedSound;
                }
                bl = Objects.equals(soundEvent2, soundEvent);
                if (bl2) break block25;
                if (!bl) break block24;
                suppressedSound = null;
                return true;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean filtersTracker(EntityTrackerUpdateS2CPacket entityTrackerUpdateS2CPacket) {
        int n;
        List list;
        MinecraftClient minecraftClient;
        block42: {
            DataTracker.SerializedEntry serializedEntry;
            boolean bl;
            block40: {
                DataTracker.SerializedEntry serializedEntry2;
                block41: {
                    MinecraftClient minecraftClient2;
                    block38: {
                        block39: {
                            block36: {
                                block37: {
                                    block34: {
                                        block35: {
                                            block33: {
                                                block32: {
                                                    bl = ProjectileOptimizer.a;
                                                    boolean bl2 = ConsumableOptimizer.on();
                                                    if (bl) return bl2;
                                                    if (bl2) break block32;
                                                    return false;
                                                }
                                                minecraftClient = MinecraftClient.method_1551();
                                                minecraftClient2 = minecraftClient;
                                                if (bl) break block33;
                                                if (minecraftClient2.field_1687 == null) return false;
                                                minecraftClient2 = minecraftClient;
                                            }
                                            if (bl) break block34;
                                            if (minecraftClient2.field_1724 != null) break block35;
                                            return false;
                                        }
                                        minecraftClient2 = minecraftClient;
                                    }
                                    if (bl) break block36;
                                    if (minecraftClient2.field_1755 == null) break block37;
                                    return false;
                                }
                                minecraftClient2 = minecraftClient;
                            }
                            if (bl) break block38;
                            if (ConsumableOptimizer.holdingConsumable(minecraftClient2)) break block39;
                            return false;
                        }
                        minecraftClient2 = minecraftClient;
                    }
                    if (minecraftClient2.field_1687.method_8469(entityTrackerUpdateS2CPacket.comp_1127()) != minecraftClient.field_1724) {
                        return false;
                    }
                    list = entityTrackerUpdateS2CPacket.comp_1128();
                    if (list == null) {
                        return false;
                    }
                    n = ConsumableOptimizer.livingFlagsId();
                    serializedEntry2 = list.stream().filter(arg_0 -> ConsumableOptimizer.lambda$filtersTracker$0(n, arg_0)).findFirst().orElse(null);
                    serializedEntry = serializedEntry2;
                    if (bl) break block40;
                    if (serializedEntry != null) break block41;
                    return false;
                }
                serializedEntry = serializedEntry2;
            }
            boolean bl3 = ConsumableOptimizer.swallowsFlags(serializedEntry);
            if (bl) return bl3;
            if (bl3) break block42;
            return false;
        }
        minecraftClient.field_1724.method_5841().method_12779(list.stream().filter(arg_0 -> ConsumableOptimizer.lambda$filtersTracker$1(n, arg_0)).toList());
        return true;
    }

    private static int livingFlagsId() {
        return LivingEntityAccessor.aurora$livingFlags().comp_2327();
    }

    private static boolean swallowsFlags(DataTracker.SerializedEntry<?> serializedEntry) {
        boolean bl;
        block88: {
            block89: {
                boolean bl2;
                boolean bl3;
                block84: {
                    block85: {
                        boolean bl4;
                        boolean bl5;
                        block86: {
                            block87: {
                                block82: {
                                    block83: {
                                        block80: {
                                            block81: {
                                                block78: {
                                                    block79: {
                                                        block76: {
                                                            block75: {
                                                                ConsumableComponent consumableComponent;
                                                                ItemStack itemStack;
                                                                Object object;
                                                                block77: {
                                                                    Object object2;
                                                                    block74: {
                                                                        block73: {
                                                                            boolean bl6;
                                                                            block72: {
                                                                                boolean bl7;
                                                                                block69: {
                                                                                    block71: {
                                                                                        block70: {
                                                                                            boolean bl8;
                                                                                            boolean bl9;
                                                                                            block66: {
                                                                                                block68: {
                                                                                                    block67: {
                                                                                                        boolean bl10;
                                                                                                        block64: {
                                                                                                            block63: {
                                                                                                                block65: {
                                                                                                                    Object object3;
                                                                                                                    block90: {
                                                                                                                        block61: {
                                                                                                                            block62: {
                                                                                                                                bl3 = ProjectileOptimizer.a;
                                                                                                                                object = serializedEntry.comp_1117();
                                                                                                                                if (bl3) break block61;
                                                                                                                                if (!(object instanceof Number)) break block62;
                                                                                                                                Number number = (Number)object;
                                                                                                                                bl10 = number.intValue();
                                                                                                                                break block64;
                                                                                                                            }
                                                                                                                            object3 = serializedEntry;
                                                                                                                            if (bl3) break block90;
                                                                                                                            object = object3.comp_1117();
                                                                                                                        }
                                                                                                                        if (!(object instanceof Boolean)) break block63;
                                                                                                                        object3 = object;
                                                                                                                    }
                                                                                                                    Boolean bl11 = (Boolean)object3;
                                                                                                                    bl10 = bl11;
                                                                                                                    if (bl3) break block64;
                                                                                                                    if (!bl10) break block65;
                                                                                                                    bl10 = true;
                                                                                                                    break block64;
                                                                                                                }
                                                                                                                bl10 = false;
                                                                                                                break block64;
                                                                                                            }
                                                                                                            bl10 = -1 != 0;
                                                                                                        }
                                                                                                        bl9 = bl10;
                                                                                                        bl8 = bl9;
                                                                                                        if (bl3) break block66;
                                                                                                        if (!bl8) break block67;
                                                                                                        bl8 = bl9;
                                                                                                        if (bl3) break block66;
                                                                                                        if (bl8 != 2) break block68;
                                                                                                    }
                                                                                                    bl8 = true;
                                                                                                    break block66;
                                                                                                }
                                                                                                bl8 = false;
                                                                                            }
                                                                                            bl5 = bl8;
                                                                                            bl7 = bl9;
                                                                                            if (bl3) break block69;
                                                                                            if (bl7) break block70;
                                                                                            bl7 = bl9;
                                                                                            if (bl3) break block69;
                                                                                            if (bl7 != 3) break block71;
                                                                                        }
                                                                                        bl7 = true;
                                                                                        break block69;
                                                                                    }
                                                                                    bl7 = false;
                                                                                }
                                                                                bl2 = bl7;
                                                                                bl6 = bl2;
                                                                                if (bl3) break block72;
                                                                                if (!bl6) break block73;
                                                                                bl6 = false;
                                                                            }
                                                                            waiting = bl6;
                                                                        }
                                                                        object = MinecraftClient.method_1551();
                                                                        object2 = object;
                                                                        if (bl3) break block74;
                                                                        if (((MinecraftClient)object2).field_1724 == null) break block75;
                                                                        object2 = object;
                                                                    }
                                                                    bl = ((MinecraftClient)object2).field_1724.method_6115();
                                                                    if (bl3) break block76;
                                                                    if (!bl) break block75;
                                                                    itemStack = ((MinecraftClient)object).field_1724.method_6030();
                                                                    ConsumableComponent consumableComponent2 = (ConsumableComponent)itemStack.method_58694(DataComponentTypes.field_53964);
                                                                    consumableComponent = consumableComponent2;
                                                                    if (bl3) break block77;
                                                                    if (consumableComponent == null) break block75;
                                                                    consumableComponent = consumableComponent2;
                                                                }
                                                                bl = consumableComponent.method_62844((LivingEntity)((MinecraftClient)object).field_1724, itemStack);
                                                                if (bl3) break block76;
                                                                if (!bl) break block75;
                                                                return true;
                                                            }
                                                            bl = bl2;
                                                        }
                                                        if (bl3) break block78;
                                                        if (!bl) break block79;
                                                        suppressSwapAnimation = false;
                                                    }
                                                    bl = trackerState;
                                                }
                                                if (bl3) break block80;
                                                if (bl) break block81;
                                                return false;
                                            }
                                            long l = System.currentTimeMillis() - trackerAt - ConsumableOptimizer.a(4764, 395857980653364451L);
                                            bl = (l == 0L ? 0 : (l < 0L ? -1 : 1)) != 0;
                                        }
                                        if (bl3) break block82;
                                        if (bl <= false) break block83;
                                        trackerState = 0;
                                        return false;
                                    }
                                    bl = trackerState;
                                }
                                if (bl3) break block84;
                                if (!bl) break block85;
                                bl4 = bl5;
                                if (bl3) break block86;
                                if (!bl4) break block87;
                                bl4 = 2 != 0;
                                break block86;
                            }
                            bl4 = false;
                        }
                        trackerState = bl4;
                        return bl5;
                    }
                    bl = trackerState;
                }
                if (bl3) break block88;
                if (!bl) break block89;
                trackerState = 0;
                return bl2;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean swallowsStopUsing(byte by) {
        boolean bl;
        block16: {
            block17: {
                boolean bl2;
                block14: {
                    block15: {
                        boolean bl3;
                        block12: {
                            block13: {
                                bl2 = ProjectileOptimizer.a;
                                bl3 = ConsumableOptimizer.on();
                                if (bl2) break block12;
                                if (!bl3) break block13;
                                bl = by;
                                if (bl2) break block14;
                                if (bl) break block15;
                            }
                            bl3 = false;
                        }
                        return bl3;
                    }
                    bl = ConsumableOptimizer.isWaiting();
                }
                if (bl2) break block16;
                if (bl) break block17;
                return false;
            }
            waiting = false;
            bl = true;
        }
        return bl;
    }

    public static boolean skipsSwapAnimation(ItemStack itemStack, ItemStack itemStack2) {
        ItemStack itemStack3;
        block27: {
            ComponentType componentType;
            block26: {
                Object object;
                boolean bl;
                block25: {
                    boolean bl2;
                    block23: {
                        block24: {
                            boolean bl3;
                            block21: {
                                block22: {
                                    bl = ProjectileOptimizer.a;
                                    bl3 = ConsumableOptimizer.on();
                                    if (bl) break block21;
                                    if (!bl3) break block22;
                                    bl2 = suppressSwapAnimation;
                                    if (bl) break block23;
                                    if (bl2) break block24;
                                }
                                bl3 = false;
                            }
                            return bl3;
                        }
                        object = MinecraftClient.method_1551();
                        if (bl) break block25;
                        bl2 = ConsumableOptimizer.holdingConsumable(object);
                    }
                    if (!bl2) {
                        return false;
                    }
                    itemStack3 = itemStack;
                    componentType = DataComponentTypes.field_53964;
                    if (bl) break block26;
                    object = itemStack3.method_58694(componentType);
                }
                if (object == null) {
                    return false;
                }
                itemStack3 = itemStack2;
                if (bl) break block27;
                componentType = DataComponentTypes.field_53964;
            }
            if (itemStack3.method_58694(componentType) == null) {
                return false;
            }
            itemStack3 = itemStack;
        }
        return itemStack3.method_31574(itemStack2.method_7909());
    }

    public static boolean holdsUseSlowdown() {
        block13: {
            ClientPlayerEntity clientPlayerEntity;
            block14: {
                boolean bl;
                block12: {
                    boolean bl2;
                    block10: {
                        block11: {
                            bl = ProjectileOptimizer.a;
                            bl2 = ConsumableOptimizer.on();
                            if (bl) break block10;
                            if (!bl2) break block11;
                            bl2 = ConsumableOptimizer.isWaiting();
                            if (bl) break block10;
                            if (bl2) break block12;
                        }
                        bl2 = false;
                    }
                    return bl2;
                }
                clientPlayerEntity = MinecraftClient.method_1551().field_1724;
                if (bl) break block13;
                if (clientPlayerEntity != null) break block14;
                return false;
            }
            clientPlayerEntity.method_5728(false);
        }
        return true;
    }

    private static boolean isWaiting() {
        int n;
        block8: {
            block9: {
                boolean bl = ProjectileOptimizer.a;
                n = waiting;
                if (bl) break block8;
                if (n == 0) break block9;
                long l = System.currentTimeMillis() - waitSince - ConsumableOptimizer.a(25618, 5271535994782093935L);
                n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                if (bl) break block8;
                if (n <= 0) break block9;
                waiting = false;
            }
            n = waiting;
        }
        return n != 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean holdingConsumable(MinecraftClient minecraftClient) {
        Object object;
        block12: {
            Object object2;
            block10: {
                boolean bl;
                block11: {
                    bl = ProjectileOptimizer.a;
                    object2 = minecraftClient;
                    if (bl) break block10;
                    if (object2.field_1724 != null) break block11;
                    return false;
                }
                object = minecraftClient.field_1724.method_6047();
                if (bl) break block12;
                object2 = object.method_58694(DataComponentTypes.field_53964);
            }
            if (object2 != null) return true;
            object = minecraftClient.field_1724.method_6079().method_58694(DataComponentTypes.field_53964);
        }
        if (object == null) return false;
        return true;
    }

    public static void reset() {
        waiting = false;
        suppressBurp = false;
        suppressedSound = null;
        suppressSwapAnimation = false;
        trackerState = 0;
    }

    public static void register() {
        PayloadTypeRegistry.playS2C().register(ConsumableOptimizer$Disable.ID, ConsumableOptimizer$Disable.CODEC);
        PayloadTypeRegistry.playC2S().register(ConsumableOptimizer$Handshake.ID, ConsumableOptimizer$Handshake.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(ConsumableOptimizer$Disable.ID, ConsumableOptimizer::lambda$register$3);
        ClientPlayConnectionEvents.JOIN.register(ConsumableOptimizer::lambda$register$4);
        ClientPlayConnectionEvents.DISCONNECT.register(ConsumableOptimizer::lambda$register$5);
        Aurora.LOG.debug(a[1]);
    }

    private static void lambda$register$5(ClientPlayNetworkHandler clientPlayNetworkHandler, MinecraftClient minecraftClient) {
        serverDisabled = false;
        ConsumableOptimizer.reset();
    }

    
    private static void lambda$register$4(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient) {
        block14: {
            block15: {
                block18: {
                    block17: {
                        boolean bl;
                        boolean bl2;
                        block12: {
                            block13: {
                                block16: {
                                    String string = ConsumableOptimizer.serverKey(minecraftClient);
                                    bl2 = ProjectileOptimizer.a;
                                    if (string == null) break block13;
                                    bl = OPTED_OUT_SERVERS.containsKey(string);
                                    if (bl2) break block12;
                                    break block16;
                                    catch (Exception exception) {
                                        throw exception;
                                    }
                                }
                                if (!bl) break block13;
                                bl = true;
                                break block12;
                            }
                            bl = false;
                        }
                        serverDisabled = bl;
                        if (bl2) break block14;
                        ConsumableOptimizer.reset();
                        if (minecraftClient == null) break block15;
                        break block17;
                        catch (Exception exception) {
                            throw exception;
                        }
                    }
                    if (!minecraftClient.method_1542()) break block15;
                    break block18;
                    catch (Exception exception) {
                        throw exception;
                    }
                }
                return;
            }
            try {
                packetSender.sendPacket((CustomPayload)new ConsumableOptimizer$Handshake());
            }
            catch (Exception exception) {
            }
        }
    }

    private static void lambda$register$3(ConsumableOptimizer$Disable consumableOptimizer$Disable, ClientPlayNetworking.Context context) {
        context.client().execute(() -> ConsumableOptimizer.lambda$register$2(context));
    }

    private static void lambda$register$2(ClientPlayNetworking.Context context) {
        block11: {
            ClientPlayNetworking.Context context2;
            block10: {
                boolean bl;
                block8: {
                    block9: {
                        serverDisabled = true;
                        bl = ProjectileOptimizer.a;
                        String string = ConsumableOptimizer.serverKey(context.client());
                        if (bl) break block8;
                        if (string == null) break block9;
                        OPTED_OUT_SERVERS.put(string, Boolean.TRUE);
                    }
                    ConsumableOptimizer.reset();
                }
                context2 = context;
                if (bl) break block10;
                if (context2.client().field_1724 == null) break block11;
                context2 = context;
            }
            context2.client().field_1724.method_7353((Text)Text.method_43470((String)a[0]).method_27692(Formatting.field_1061), false);
        }
    }

    private static boolean lambda$filtersTracker$1(int n, DataTracker.SerializedEntry serializedEntry) {
        int n2;
        block8: {
            block7: {
                DataTracker.SerializedEntry serializedEntry2;
                boolean bl;
                block6: {
                    bl = ProjectileOptimizer.a;
                    serializedEntry2 = serializedEntry;
                    if (bl) break block6;
                    if (serializedEntry2 == null) break block7;
                    serializedEntry2 = serializedEntry;
                }
                n2 = serializedEntry2.comp_1115();
                if (bl) break block8;
                if (n2 == n) break block7;
                n2 = 1;
                break block8;
            }
            n2 = 0;
        }
        return n2 != 0;
    }

    private static boolean lambda$filtersTracker$0(int n, DataTracker.SerializedEntry serializedEntry) {
        int n2;
        block8: {
            block7: {
                DataTracker.SerializedEntry serializedEntry2;
                boolean bl;
                block6: {
                    bl = ProjectileOptimizer.a;
                    serializedEntry2 = serializedEntry;
                    if (bl) break block6;
                    if (serializedEntry2 == null) break block7;
                    serializedEntry2 = serializedEntry;
                }
                n2 = serializedEntry2.comp_1115();
                if (bl) break block8;
                if (n2 != n) break block7;
                n2 = 1;
                break block8;
            }
            n2 = 0;
        }
        return n2 != 0;
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var13 = new String[3];
                    var11_1 = 0;
                    var10_2 = "/Rl\u0011liB\u000eQgBvtW\u0005Pk\u0018|v\u0003\bTq\u0003{hF\b\u0007\"\u0016qmPLNg\u0010oaQL\\q\t|`\u0003\nRpBpp\u0003\u0003[dL(/Rl\u0011liB\u000eQgBvtW\u0005Pk\u0018|v\u0003\u001cOm\u0016vgL\u0000\u001dp\u0007~mP\u0018Xp\u0007}\u0014\u000fRl\u0011liB\u000eQg=vtW\u0005Pk\u0018|v";
                    var12_3 = "/Rl\u0011liB\u000eQgBvtW\u0005Pk\u0018|v\u0003\bTq\u0003{hF\b\u0007\"\u0016qmPLNg\u0010oaQL\\q\t|`\u0003\nRpBpp\u0003\u0003[dL(/Rl\u0011liB\u000eQgBvtW\u0005Pk\u0018|v\u0003\u001cOm\u0016vgL\u0000\u001dp\u0007~mP\u0018Xp\u0007}\u0014\u000fRl\u0011liB\u000eQg=vtW\u0005Pk\u0018|v".length();
                    var9_4 = 60;
                    var8_5 = -1;
lbl7:

                    while (true) {
                        continue;
                        break;
                    }
lbl9:

                    while (true) {
                        var13[var11_1++] = new String(v0).intern();
                        if ((var8_5 += var9_4) < var12_3) {
                            var9_4 = var10_2.charAt(var8_5);
                            continue;
                        }
                        break block19;
                        break;
                    }
                    v1 = ++var8_5;
                    v2 = var10_2.substring(v1, v1 + var9_4).toCharArray();
                    v0 = v2;
                    v3 = v2.length;
                    var14_6 = 0;
                    do {
                        v0 = v0;
                        v4 = var14_6;
                        v5 = v0[v4];
                        switch (var14_6 % 7) {
                            case 0: {
                                v6 = 108;
                                break;
                            }
                            case 1: {
                                v6 = 61;
                                break;
                            }
                            case 2: {
                                v6 = 2;
                                break;
                            }
                            case 3: {
                                v6 = 98;
                                break;
                            }
                            case 4: {
                                v6 = 25;
                                break;
                            }
                            case 5: {
                                v6 = 4;
                                break;
                            }
                            default: {
                                v6 = 35;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_6;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_6);
                    while (true)
                }
                ConsumableOptimizer.a = var13;
                ConsumableOptimizer.NAMESPACE = ConsumableOptimizer.a[2];
                var0_7 = 8575693623355620022L;
                var6_8 = new long[4];
                var3_9 = 0;
                var4_10 = ">*\u00b9\u00e4\u00af\u001d\u00a9-vH\u00be\u00ab\u009e\u008f\u00f7\u0090";
                var5_11 = ">*\u00b9\u00e4\u00af\u001d\u00a9-vH\u00be\u00ab\u009e\u008f\u00f7\u0090".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v7 = var6_8;
                    v8 = var3_9++;
                    v9 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v10 = -1;
                    break block20;
                    break;
                }
lbl70:

                while (true) {
                    v7[v8] = v11;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "r|\u00a5Vt\u00e9[\u0089 g\u00b8\u00b0\u00beh\u009c\u001b";
                    var5_11 = "r|\u00a5Vt\u00e9[\u0089 g\u00b8\u00b0\u00beh\u009c\u001b".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v7 = var6_8;
                        v8 = var3_9++;
                        v9 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v10 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl83:

                while (true) {
                    v7[v8] = v11;
                    if (var2_12 < var5_11) continue;
                    break block21;
                    break;
                }
            }
            v11 = v9 ^ var0_7;
            switch (v10) {
                default: {
                    continue;
                }
                case 0:
lbl94:

                continue;
            }
        }
        ConsumableOptimizer.b = var6_8;
        ConsumableOptimizer.c = new Long[4];
        ConsumableOptimizer.WAIT_MS = ConsumableOptimizer.a(15022, 92962890008382674L);
        ConsumableOptimizer.TRACKER_MS = ConsumableOptimizer.a(21775, 6297513710539318129L);
        ConsumableOptimizer.OPTED_OUT_SERVERS = Collections.synchronizedMap(new ConsumableOptimizer$1(16, 0.75f, false));
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x767D) & Short.MAX_VALUE;
        if (c[n2] == null) {
            ConsumableOptimizer.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
