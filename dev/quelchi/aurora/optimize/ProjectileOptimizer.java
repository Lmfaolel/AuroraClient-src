package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.mixins.PersistentProjectileEntityInvoker;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.optimize.LocalIds;
import dev.quelchi.aurora.optimize.ProjectileOptimizer$ExplosionEntry;
import dev.quelchi.aurora.optimize.ProjectileOptimizer$Shooter;
import dev.quelchi.aurora.optimize.ProjectileOptimizer$SimArrow;
import dev.quelchi.aurora.optimize.ProjectileOptimizer$SpawnEntry;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.ComponentType;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public final class ProjectileOptimizer {
    private static final int EXPIRY;
    private static final int ENTITY_EXPIRY;
    private static final double MATCH_RANGE_SQ = 25.0;
    private static final int SIM_MAX_TICKS;
    private static final Map<Identifier, ArrayDeque<Integer>> SOUNDS;
    private static final Map<Integer, ArrayDeque<Integer>> WORLD_EVENTS;
    private static final List<ProjectileOptimizer$SpawnEntry> SPAWNED;
    private static final Map<Integer, Integer> ACTIVE;
    private static final List<ProjectileOptimizer$ExplosionEntry> EXPLOSIONS;
    private static final Map<Integer, EntitySpawnS2CPacket> PENDING_ARROWS;
    private static final Map<Integer, Vec3d> LANDED;
    private static final List<ProjectileOptimizer$SimArrow> SIMULATED;
    private static final Map<Integer, Integer> HIT_BEFORE_PACKET;
    private static final Map<UUID, Integer> SUPPRESSED_UUIDS;
    private static int tickCounter;
    private static WeakReference<ClientWorld> lastWorld;
    private static Vec3d serverMovement;
    private static final Map<Identifier, Long> LOCAL_COOLDOWNS;
    private static final long COOLDOWN_ECHO_MS;
    private static Vec3d unsentTeleport;
    public static boolean a;
    private static final long[] b;
    private static final Long[] c;

    private ProjectileOptimizer() {
    }

    public static boolean on() {
        return Prediction.active();
    }

    public static void throwSound(ClientWorld clientWorld, LivingEntity livingEntity, SoundEvent soundEvent, SoundCategory soundCategory) {
        block4: {
            block5: {
                boolean bl = a;
                Identifier identifier = Registries.field_41172.method_10221((Object)soundEvent);
                if (bl) break block4;
                if (identifier == null) break block5;
                SOUNDS.computeIfAbsent(identifier, ProjectileOptimizer::lambda$throwSound$0).addLast(tickCounter);
            }
            clientWorld.method_8486(livingEntity.method_23317(), livingEntity.method_23318(), livingEntity.method_23321(), soundEvent, soundCategory, 0.5f, 0.4f / (clientWorld.method_8409().method_43057() * 0.4f + 0.8f), false);
        }
    }

    public static void worldEvent(ClientWorld clientWorld, int n, BlockPos blockPos, int n2) {
        WORLD_EVENTS.computeIfAbsent(n, ProjectileOptimizer::lambda$worldEvent$1).addLast(tickCounter);
        clientWorld.method_20290(n, blockPos, n2);
    }

    public static void explosion(double d, double d2, double d3) {
        EXPLOSIONS.add(new ProjectileOptimizer$ExplosionEntry(d, d2, d3, tickCounter));
    }

    public static void spawn(ClientWorld clientWorld, ProjectileEntity projectileEntity, LivingEntity livingEntity, float f, float f2) {
        projectileEntity.method_24919((Entity)livingEntity, livingEntity.method_36455(), livingEntity.method_36454(), f, f2, 0.0f);
        ProjectileOptimizer.useServerMovement(projectileEntity, livingEntity);
        ProjectileOptimizer.add(clientWorld, projectileEntity);
    }

    public static void localCooldownUse(PlayerEntity playerEntity, Hand hand) {
        if (!Prediction.active()) {
            return;
        }
        LOCAL_COOLDOWNS.put(playerEntity.method_7357().method_62836(playerEntity.method_5998(hand)), Util.method_658());
    }

    public static boolean ignoresServerCooldown(Identifier identifier) {
        int n;
        block9: {
            block8: {
                boolean bl;
                block7: {
                    boolean bl2;
                    block6: {
                        bl = a;
                        bl2 = Prediction.active();
                        if (bl) break block6;
                        if (bl2) break block7;
                        bl2 = false;
                    }
                    return bl2;
                }
                Long l = LOCAL_COOLDOWNS.get(identifier);
                if (l == null) break block8;
                long l2 = Util.method_658() - l - ProjectileOptimizer.a(20192, 2495151796323964019L);
                n = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                if (bl) break block9;
                if (n >= 0) break block8;
                n = 1;
                break block9;
            }
            n = 0;
        }
        return n != 0;
    }

    public static void onMovementSent(Vec3d vec3d) {
        Vec3d vec3d2;
        block6: {
            block7: {
                boolean bl = a;
                vec3d2 = unsentTeleport;
                if (bl) break block6;
                if (vec3d2 == null) break block7;
                vec3d2 = vec3d;
                if (bl) break block6;
                if (!(vec3d2.method_1027() > 0.0)) break block7;
                vec3d = vec3d.method_1020(unsentTeleport);
                unsentTeleport = null;
            }
            vec3d2 = vec3d;
        }
        serverMovement = vec3d2;
    }

    public static void onLocalTeleport(Vec3d vec3d, Vec3d vec3d2) {
        Vec3d vec3d3;
        block6: {
            Vec3d vec3d4;
            Vec3d vec3d5;
            block4: {
                block5: {
                    vec3d5 = vec3d2.method_1020(vec3d);
                    boolean bl = a;
                    vec3d4 = unsentTeleport;
                    if (bl) break block4;
                    if (vec3d4 != null) break block5;
                    vec3d3 = vec3d5;
                    break block6;
                }
                vec3d4 = unsentTeleport;
            }
            vec3d3 = vec3d4.method_1019(vec3d5);
        }
        unsentTeleport = vec3d3;
        serverMovement = Vec3d.field_1353;
    }

    private static void useServerMovement(ProjectileEntity projectileEntity, LivingEntity livingEntity) {
        double d;
        double d2;
        Vec3d vec3d;
        ProjectileEntity projectileEntity2;
        Vec3d vec3d2;
        Vec3d vec3d3;
        block12: {
            Vec3d vec3d4;
            block10: {
                block11: {
                    LivingEntity livingEntity2;
                    boolean bl;
                    block8: {
                        block9: {
                            bl = a;
                            livingEntity2 = livingEntity;
                            if (bl) break block8;
                            if (livingEntity2 == MinecraftClient.method_1551().field_1724) break block9;
                            return;
                        }
                        livingEntity2 = livingEntity;
                    }
                    vec3d3 = livingEntity2.method_18798();
                    vec3d2 = serverMovement;
                    boolean bl2 = livingEntity.method_24828();
                    projectileEntity2 = projectileEntity;
                    vec3d = projectileEntity.method_18798();
                    d2 = vec3d2.field_1352;
                    vec3d4 = vec3d3;
                    if (bl) break block10;
                    d2 = d2 - vec3d4.field_1352;
                    if (!bl2) break block11;
                    d = 0.0;
                    break block12;
                }
                vec3d4 = vec3d2;
            }
            d = vec3d4.field_1351 - vec3d3.field_1351;
        }
        projectileEntity2.method_18799(vec3d.method_1031(d2, d, vec3d2.field_1350 - vec3d3.field_1350));
    }

    public static void add(ClientWorld clientWorld, ProjectileEntity projectileEntity) {
        block4: {
            Object object;
            block5: {
                boolean bl = a;
                object = projectileEntity;
                if (bl) break block4;
                object.method_5838(LocalIds.next());
                if (!Prediction.active()) break block5;
                PredictionHandshake.sendLaunch(projectileEntity.method_18798());
            }
            SPAWNED.add(new ProjectileOptimizer$SpawnEntry(projectileEntity.method_5628(), projectileEntity.method_5864(), projectileEntity.method_23317(), projectileEntity.method_23318(), projectileEntity.method_23321(), tickCounter));
            object = ACTIVE.put(projectileEntity.method_5628(), tickCounter);
        }
        clientWorld.method_53875((Entity)projectileEntity);
    }

    public static void shootAll(ClientWorld clientWorld, LivingEntity livingEntity, Hand hand, ItemStack itemStack, List<ItemStack> list, float f, float f2, boolean bl, LivingEntity livingEntity2, ProjectileOptimizer$Shooter projectileOptimizer$Shooter) {
        boolean bl2 = a;
        float f3 = EnchantmentHelper.method_60142((ItemStack)itemStack, (ComponentType)EnchantmentEffectComponentTypes.field_51676) ? 10.0f : 0.0f;
        float f4 = f3;
        float f5 = list.size() == 1 ? 0.0f : 2.0f * f4 / (float)(list.size() - 1);
        float f6 = f5;
        float f7 = (float)((list.size() - 1) % 2) * f6 / 2.0f;
        float f8 = 1.0f;
        int n = 0;
        while (n < list.size()) {
            block32: {
                block25: {
                    int n2;
                    ItemStack itemStack2;
                    block30: {
                        block31: {
                            ItemStack itemStack3;
                            block34: {
                                ArrowItem arrowItem;
                                block28: {
                                    float f9;
                                    block29: {
                                        ArrowItem arrowItem2;
                                        block33: {
                                            Item item;
                                            block26: {
                                                block27: {
                                                    block24: {
                                                        itemStack3 = list.get(n);
                                                        if (!itemStack3.method_7960()) break block24;
                                                        break block25;
                                                    }
                                                    f9 = f7 + f8 * (float)((n + 1) / 2) * f6;
                                                    f8 = -f8;
                                                    Item item2 = itemStack3.method_7909();
                                                    item = item2;
                                                    if (bl2) break block26;
                                                    if (!(item instanceof ArrowItem)) break block27;
                                                    arrowItem2 = arrowItem = (ArrowItem)item2;
                                                    break block33;
                                                }
                                                item = Items.field_8107;
                                            }
                                            arrowItem2 = (ArrowItem)item;
                                        }
                                        ArrowItem arrowItem3 = arrowItem2;
                                        arrowItem = arrowItem3.method_7702((World)clientWorld, itemStack3, livingEntity, itemStack);
                                        if (bl2) break block28;
                                        if (!bl) break block29;
                                        arrowItem.method_7439(true);
                                    }
                                    projectileOptimizer$Shooter.shoot(livingEntity, (ProjectileEntity)arrowItem, n, f, 0.0f, f9, livingEntity2);
                                    if (bl2) break block34;
                                }
                                if (!(itemStack.method_7909() instanceof CrossbowItem)) {
                                    ProjectileOptimizer.useServerMovement((ProjectileEntity)arrowItem, livingEntity);
                                }
                                ProjectileOptimizer.add(clientWorld, (ProjectileEntity)arrowItem);
                            }
                            itemStack2 = itemStack;
                            n2 = itemStack3.method_31574(Items.field_8639);
                            if (bl2) break block30;
                            if (n2 == 0) break block31;
                            n2 = 3;
                            break block30;
                        }
                        n2 = 1;
                    }
                    itemStack2.method_71012(n2, livingEntity, hand);
                    if (bl2) break block32;
                    if (itemStack.method_7960()) {
                        break;
                    }
                }
                ++n;
            }
            if (!bl2) continue;
        }
    }

    public static boolean buffered(int n) {
        return ACTIVE.containsKey(n);
    }

    public static void remove(int n) {
        block3: {
            ClientWorld clientWorld;
            block2: {
                ACTIVE.remove(n);
                boolean bl = a;
                PENDING_ARROWS.remove(n);
                LANDED.remove(n);
                ClientWorld clientWorld2 = MinecraftClient.method_1551().field_1687;
                clientWorld = clientWorld2;
                if (bl) break block2;
                if (clientWorld == null) break block3;
                clientWorld = clientWorld2;
            }
            clientWorld.method_2945(n, Entity.RemovalReason.field_26999);
        }
    }

    public static void removeIfBuffered(int n) {
        block5: {
            int n2;
            block4: {
                boolean bl = a;
                n2 = n;
                if (bl) break block4;
                if (!ProjectileOptimizer.buffered(n2)) break block5;
                n2 = n;
            }
            ProjectileOptimizer.remove(n2);
        }
    }

    public static void pearlPushed(ClientWorld clientWorld, Vec3d vec3d, Vec3d vec3d2) {
        block7: {
            EnderPearlEntity enderPearlEntity;
            block11: {
                EnderPearlEntity enderPearlEntity2;
                block12: {
                    enderPearlEntity2 = null;
                    double d = 4096.0;
                    Iterator<Integer> iterator = ACTIVE.keySet().iterator();
                    boolean bl = a;
                    while (iterator.hasNext()) {
                        block10: {
                            double d2;
                            block9: {
                                Entity entity;
                                block8: {
                                    int n = iterator.next();
                                    if (bl) break block7;
                                    Entity entity2 = clientWorld.method_8469(n);
                                    entity = entity2;
                                    if (bl) break block8;
                                    if (!(entity instanceof EnderPearlEntity)) continue;
                                    entity = entity2;
                                }
                                EnderPearlEntity enderPearlEntity3 = (EnderPearlEntity)entity;
                                d2 = enderPearlEntity3.method_73189().method_1025(vec3d);
                                if (bl) break block9;
                                if (!(d2 < d)) break block10;
                                enderPearlEntity2 = enderPearlEntity3;
                            }
                            d = d2;
                        }
                        if (!bl) continue;
                    }
                    enderPearlEntity = enderPearlEntity2;
                    if (bl) break block11;
                    if (enderPearlEntity != null) break block12;
                    return;
                }
                enderPearlEntity2.method_33574(vec3d);
                enderPearlEntity = enderPearlEntity2;
            }
            enderPearlEntity.method_18799(vec3d2);
        }
    }

    public static void arrowHitEntity(int n) {
        block4: {
            EntitySpawnS2CPacket entitySpawnS2CPacket;
            block5: {
                boolean bl = a;
                entitySpawnS2CPacket = PENDING_ARROWS.remove(n);
                if (bl) break block4;
                LANDED.remove(n);
                if (entitySpawnS2CPacket != null) break block5;
                HIT_BEFORE_PACKET.put(n, tickCounter);
                return;
            }
            SIMULATED.add(new ProjectileOptimizer$SimArrow(entitySpawnS2CPacket));
        }
    }

    public static EntitySpawnS2CPacket consumePendingArrow(int n) {
        return PENDING_ARROWS.remove(n);
    }

    public static void markLanded(int n, Vec3d vec3d) {
        LANDED.put(n, vec3d);
    }

    public static void addServerArrow(ClientWorld clientWorld, EntitySpawnS2CPacket entitySpawnS2CPacket, Vec3d vec3d, Vec3d vec3d2) {
        block12: {
            Entity entity;
            Entity entity2;
            block13: {
                boolean bl;
                block10: {
                    block11: {
                        bl = a;
                        entity2 = entitySpawnS2CPacket.method_11169().method_5883((World)clientWorld, SpawnReason.field_52444);
                        if (bl) break block10;
                        if (entity2 != null) break block11;
                        return;
                    }
                    entity = entity2;
                    if (bl) break block12;
                    entity.method_31471(entitySpawnS2CPacket);
                    entity2.method_33574(vec3d);
                    entity2.method_18799(vec3d2);
                }
                if (!vec3d2.equals((Object)Vec3d.field_1353)) break block13;
                entity = entity2;
                if (bl) break block12;
                if (!(entity instanceof PersistentProjectileEntityInvoker)) break block13;
                PersistentProjectileEntityInvoker persistentProjectileEntityInvoker = (PersistentProjectileEntityInvoker)entity2;
                persistentProjectileEntityInvoker.aurora$setInGround(true);
            }
            clientWorld.method_53875(entity2);
            entity2.field_6014 = vec3d.field_1352;
            entity2.field_6036 = vec3d.field_1351;
            entity = entity2;
        }
        entity.field_5969 = vec3d.field_1350;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean suppressesSound(RegistryEntry<SoundEvent> registryEntry) {
        ArrayDeque arrayDeque;
        boolean bl;
        Optional optional;
        block17: {
            ArrayDeque arrayDeque2;
            block18: {
                block16: {
                    Object object;
                    block14: {
                        block15: {
                            optional = registryEntry.method_40230();
                            bl = a;
                            object = optional;
                            if (bl) break block14;
                            if (!((Optional)object).isEmpty()) break block15;
                            return false;
                        }
                        object = SOUNDS.get(((RegistryKey)optional.get()).method_29177());
                    }
                    arrayDeque2 = (ArrayDeque)object;
                    arrayDeque = arrayDeque2;
                    if (bl) break block16;
                    if (arrayDeque == null) return false;
                    arrayDeque = arrayDeque2;
                }
                if (bl) break block17;
                if (!arrayDeque.isEmpty()) break block18;
                return false;
            }
            arrayDeque2.pollFirst();
            arrayDeque = arrayDeque2;
        }
        boolean bl2 = arrayDeque.isEmpty();
        if (bl) return bl2;
        if (!bl2) return true;
        SOUNDS.remove(((RegistryKey)optional.get()).method_29177());
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean swallowsWorldEvent(int n) {
        ArrayDeque<Integer> arrayDeque;
        boolean bl;
        block11: {
            ArrayDeque<Integer> arrayDeque2;
            block12: {
                block10: {
                    arrayDeque2 = WORLD_EVENTS.get(n);
                    bl = a;
                    arrayDeque = arrayDeque2;
                    if (bl) break block10;
                    if (arrayDeque == null) return false;
                    arrayDeque = arrayDeque2;
                }
                if (bl) break block11;
                if (!arrayDeque.isEmpty()) break block12;
                return false;
            }
            arrayDeque2.pollFirst();
            arrayDeque = arrayDeque2;
        }
        boolean bl2 = arrayDeque.isEmpty();
        if (bl) return bl2;
        if (!bl2) return true;
        WORLD_EVENTS.remove(n);
        return true;
    }

    public static boolean explosionFromServer(double d, double d2, double d3) {
        int n;
        block8: {
            Iterator<ProjectileOptimizer$ExplosionEntry> iterator = EXPLOSIONS.iterator();
            boolean bl = a;
            while (iterator.hasNext()) {
                block9: {
                    block7: {
                        ProjectileOptimizer$ExplosionEntry projectileOptimizer$ExplosionEntry = iterator.next();
                        double d4 = projectileOptimizer$ExplosionEntry.x() - d;
                        double d5 = projectileOptimizer$ExplosionEntry.y() - d2;
                        double d6 = projectileOptimizer$ExplosionEntry.z() - d3;
                        if (bl) break block7;
                        double d7 = d4 * d4 + d5 * d5 + d6 * d6 - 25.0;
                        n = d7 == 0.0 ? 0 : (d7 < 0.0 ? -1 : 1);
                        if (bl) break block8;
                        if (n > 0) break block9;
                        iterator.remove();
                    }
                    return true;
                }
                if (!bl) continue;
            }
            n = 0;
        }
        return n != 0;
    }

    
    public static boolean swallowsSpawn(EntitySpawnS2CPacket var0) {
        block44: {
            block43: {
                block42: {
                    block40: {
                        block41: {
                            block39: {
                                block38: {
                                    block37: {
                                        var1_1 = ProjectileOptimizer.a;
                                        v0 = ProjectileOptimizer.SUPPRESSED_UUIDS.containsKey(var0.method_11164());
                                        if (var1_1) break block37;
                                        if (!v0) break block38;
                                        v0 = true;
                                    }
                                    return v0;
                                }
                                var2_2 = MinecraftClient.method_1551().field_1724;
                                if (var2_2 == null) break block39;
                                v2 = var0;
                                if (var1_1) break block40;
                                if (v2.method_11166() == var2_2.method_5628()) break block41;
                            }
                            return false;
                        }
                        v2 = var0;
                    }
                    var3_3 = v2.method_11169();
                    v6 = var3_3;
                    if (var1_1) break block42;
                    if (v6 != EntityType.field_6122) {
                    }
                    v6 = var3_3;
                }
                if (v6 != EntityType.field_6135) break block43;
lbl51:

                v9 = true;
                break block44;
            }
            v9 = false;
        }
        var4_4 = v9;
        var5_5 = var0.method_11175();
        var7_6 = var0.method_11174();
        var9_7 = var0.method_11176();
        var11_8 = ProjectileOptimizer.SPAWNED.iterator();
        while (var11_8.hasNext()) {
            block52: {
                block51: {
                    block50: {
                        block48: {
                            block49: {
                                block47: {
                                    block45: {
                                        var12_9 = var11_8.next();
                                        v11 = var12_9;
                                        if (var1_1) break block45;
                                        if (v11.type() != var3_3) {
                                            continue;
                                        }
                                        v11 = var12_9;
                                    }
                                    var13_10 = v11.x() - var5_5;
                                    var15_11 = var12_9.y() - var7_6;
                                    var17_12 = var12_9.z() - var9_7;
                                    cfr_temp_0 = var13_10 * var13_10 + var15_11 * var15_11 + var17_12 * var17_12 - 25.0;
                                    v14 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 > 0.0 ? 1 : -1);
                                    if (var1_1) break block47;
                                    if (v14 > 0) {
                                        continue;
                                    }
                                    v14 = (double)var4_4;
                                }
                                if (var1_1) break block48;
                                if (v14 != false) break block49;
                                var11_8.remove();
                                ProjectileOptimizer.SUPPRESSED_UUIDS.put(var0.method_11164(), ProjectileOptimizer.tickCounter);
                                return true;
                            }
                            var11_8.remove();
                            v18 = ProjectileOptimizer.ACTIVE;
                            v19 = var12_9.id();
                            if (var1_1) break block50;
                            v14 = (double)v18.containsKey(v19);
                        }
                        if (v14 != false) {
                            ProjectileOptimizer.PENDING_ARROWS.put(var12_9.id(), var0);
                            ProjectileOptimizer.SUPPRESSED_UUIDS.put(var0.method_11164(), ProjectileOptimizer.tickCounter);
                            return true;
                        }
                        v18 = ProjectileOptimizer.HIT_BEFORE_PACKET;
                        if (var1_1) break block51;
                        v19 = var12_9.id();
                    }
                    if (v18.remove(v19) == null) break block52;
                    ProjectileOptimizer.SIMULATED.add(new ProjectileOptimizer$SimArrow(var0));
                    v18 = ProjectileOptimizer.SUPPRESSED_UUIDS.put(var0.method_11164(), ProjectileOptimizer.tickCounter);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(ProjectileOptimizer::lambda$register$2);
    }

    public static void tick() {
        block21: {
            boolean bl;
            block23: {
                block22: {
                    boolean bl2;
                    block24: {
                        ClientWorld clientWorld;
                        block20: {
                            boolean bl3;
                            ClientWorld clientWorld2;
                            block18: {
                                block19: {
                                    block16: {
                                        block17: {
                                            MinecraftClient minecraftClient = MinecraftClient.method_1551();
                                            bl2 = a;
                                            clientWorld2 = minecraftClient.field_1687;
                                            if (bl2) break block16;
                                            if (clientWorld2 == lastWorld.get()) break block17;
                                            ProjectileOptimizer.clearAll();
                                            lastWorld = new WeakReference<ClientWorld>(clientWorld2);
                                        }
                                        ++tickCounter;
                                        SOUNDS.values().forEach(ProjectileOptimizer::lambda$tick$4);
                                        SOUNDS.entrySet().removeIf(ProjectileOptimizer::lambda$tick$5);
                                        WORLD_EVENTS.values().forEach(ProjectileOptimizer::lambda$tick$7);
                                        WORLD_EVENTS.entrySet().removeIf(ProjectileOptimizer::lambda$tick$8);
                                        SPAWNED.removeIf(ProjectileOptimizer::lambda$tick$9);
                                        EXPLOSIONS.removeIf(ProjectileOptimizer::lambda$tick$10);
                                        HIT_BEFORE_PACKET.entrySet().removeIf(ProjectileOptimizer::lambda$tick$11);
                                        SUPPRESSED_UUIDS.entrySet().removeIf(ProjectileOptimizer::lambda$tick$12);
                                    }
                                    ClientWorld clientWorld3 = clientWorld2;
                                    bl3 = ACTIVE.entrySet().removeIf(arg_0 -> ProjectileOptimizer.lambda$tick$13(clientWorld3, arg_0));
                                    if (bl2) break block18;
                                    if (clientWorld2 != null) break block19;
                                    return;
                                }
                                clientWorld = clientWorld2;
                                if (bl2) break block20;
                                ProjectileOptimizer.drainLandedArrows(clientWorld);
                                ProjectileOptimizer.tickSimulations(clientWorld2);
                                bl3 = ProjectileOptimizer.on();
                            }
                            if (!bl3) break block24;
                            clientWorld = clientWorld2;
                        }
                        ProjectileOptimizer.checkPearlWindChargeCollisions(clientWorld);
                    }
                    if (!bl2) break block21;
                    if (!Module.a) break block22;
                    bl = false;
                    break block23;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void drainLandedArrows(ClientWorld clientWorld) {
        Map<Integer, EntitySpawnS2CPacket> map;
        boolean bl;
        block15: {
            block16: {
                block14: {
                    bl = a;
                    map = PENDING_ARROWS;
                    if (bl) break block14;
                    if (map.isEmpty()) return;
                    map = LANDED;
                }
                if (bl) break block15;
                if (!map.isEmpty()) break block16;
                return;
            }
            map = LANDED;
        }
        Iterator<Map.Entry<Integer, EntitySpawnS2CPacket>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            block17: {
                Map.Entry<Integer, EntitySpawnS2CPacket> entry = iterator.next();
                EntitySpawnS2CPacket entitySpawnS2CPacket = PENDING_ARROWS.remove(entry.getKey());
                if (bl) break block17;
                if (entitySpawnS2CPacket == null) {
                    continue;
                }
                iterator.remove();
                ACTIVE.remove(entry.getKey());
                ProjectileOptimizer.addServerArrow(clientWorld, entitySpawnS2CPacket, (Vec3d)entry.getValue(), Vec3d.field_1353);
                clientWorld.method_2945(entry.getKey().intValue(), Entity.RemovalReason.field_26999);
            }
            if (!bl) continue;
        }
    }

    
    private static void tickSimulations(ClientWorld clientWorld) {
        boolean bl = a;
        List<ProjectileOptimizer$SimArrow> list = SIMULATED;
        if (!bl) {
            if (list.isEmpty()) {
                return;
            }
            list = SIMULATED;
        }
        Iterator<ProjectileOptimizer$SimArrow> iterator = list.iterator();
        block8: while (iterator.hasNext()) {
            boolean bl2;
            Vec3d vec3d;
            Vec3d vec3d2;
            ProjectileOptimizer$SimArrow projectileOptimizer$SimArrow;
            ProjectileOptimizer$SimArrow projectileOptimizer$SimArrow2 = projectileOptimizer$SimArrow = iterator.next();
            block9: while (true) {
                block26: {
                    block25: {
                        block24: {
                            block23: {
                                if (bl) break block23;
                                if (++projectileOptimizer$SimArrow.age <= 80) break block24;
                                iterator.remove();
                            }
                            if (!bl) continue block8;
                        }
                        vec3d2 = new Vec3d(projectileOptimizer$SimArrow.vel.field_1352 * 0.99, projectileOptimizer$SimArrow.vel.field_1351 * 0.99 - 0.05, projectileOptimizer$SimArrow.vel.field_1350 * 0.99);
                        vec3d = projectileOptimizer$SimArrow.pos.method_1019(vec3d2);
                        BlockHitResult blockHitResult = clientWorld.method_17742(new RaycastContext(projectileOptimizer$SimArrow.pos, vec3d, RaycastContext.ShapeType.field_17558, RaycastContext.FluidHandling.field_1348, ShapeContext.method_16194()));
                        if (bl) break block25;
                        if (blockHitResult.method_17783() != HitResult.Type.field_1332) break block26;
                        iterator.remove();
                        ProjectileOptimizer.addServerArrow(clientWorld, projectileOptimizer$SimArrow.packet, blockHitResult.method_17784(), Vec3d.field_1353);
                    }
                    if (!bl) continue block8;
                }
                Box box = new Box(projectileOptimizer$SimArrow.pos, vec3d).method_1014(0.5);
                bl2 = false;
                for (LivingEntity livingEntity : clientWorld.method_8390(LivingEntity.class, box, ProjectileOptimizer::lambda$tickSimulations$14)) {
                    block28: {
                        block27: {
                            if (bl) break block27;
                            projectileOptimizer$SimArrow2 = livingEntity.method_5829().method_1014(0.3);
                            if (bl) continue block9;
                            if (!projectileOptimizer$SimArrow2.method_992(projectileOptimizer$SimArrow.pos, vec3d).isPresent()) break block28;
                            bl2 = true;
                        }
                        if (!bl) break block9;
                    }
                    if (!bl) continue;
                }
                break;
            }
            if (bl2) {
                iterator.remove();
                if (!bl) continue;
            }
            projectileOptimizer$SimArrow.pos = vec3d;
            projectileOptimizer$SimArrow.vel = vec3d2;
            if (!bl) continue;
        }
    }

    
    private static void checkPearlWindChargeCollisions(ClientWorld clientWorld) {
        ArrayList<WindChargeEntity> arrayList;
        WindChargeEntity windChargeEntity;
        ArrayList<EnderPearlEntity> arrayList2;
        boolean bl;
        block36: {
            ArrayList<WindChargeEntity> arrayList3;
            block37: {
                block30: {
                    block35: {
                        bl = a;
                        if (ACTIVE.isEmpty()) {
                            return;
                        }
                        arrayList3 = new ArrayList<WindChargeEntity>();
                        arrayList2 = new ArrayList<EnderPearlEntity>();
                        for (Entity entity : clientWorld.method_18112()) {
                            block33: {
                                Entity entity2;
                                block34: {
                                    block31: {
                                        block32: {
                                            if (bl) break block30;
                                            entity2 = entity;
                                            if (!bl) {
                                                if (!ProjectileOptimizer.buffered(entity2.method_5628())) continue;
                                                entity2 = entity;
                                            }
                                            if (bl) break block31;
                                            if (!(entity2 instanceof WindChargeEntity)) break block32;
                                            windChargeEntity = (WindChargeEntity)entity;
                                            arrayList3.add(windChargeEntity);
                                            if (!bl) break block33;
                                        }
                                        entity2 = entity;
                                    }
                                    if (bl) break block34;
                                    if (!(entity2 instanceof EnderPearlEntity)) break block33;
                                    entity2 = entity;
                                }
                                EnderPearlEntity enderPearlEntity = (EnderPearlEntity)entity2;
                                arrayList2.add(enderPearlEntity);
                            }
                            if (!bl) continue;
                        }
                        arrayList = arrayList3;
                        if (bl) break block35;
                        if (arrayList.isEmpty()) break block30;
                        arrayList = arrayList2;
                    }
                    if (bl) break block36;
                    if (!arrayList.isEmpty()) break block37;
                }
                return;
            }
            arrayList = arrayList3;
        }
        Iterator<Object> iterator = arrayList.iterator();
        block17: while (iterator.hasNext()) {
            WindChargeEntity windChargeEntity2;
            WindChargeEntity windChargeEntity3 = windChargeEntity2 = (WindChargeEntity)iterator.next();
            if (!bl) {
                if (!windChargeEntity3.method_5805()) continue;
                windChargeEntity3 = windChargeEntity2;
            }
            windChargeEntity = windChargeEntity3.method_5829().method_18804(windChargeEntity2.method_18798());
            for (EnderPearlEntity enderPearlEntity : arrayList2) {
                if (bl) continue block17;
                int n = enderPearlEntity.method_5805();
                if (!bl) {
                    if (n == 0) continue;
                    n = windChargeEntity.method_994(enderPearlEntity.method_5829().method_18804(enderPearlEntity.method_18798())) ? 1 : 0;
                }
                if (!bl) {
                    if (n == 0 && !bl) continue;
                    ProjectileOptimizer.remove(windChargeEntity2.method_5628());
                    n = enderPearlEntity.method_5628();
                }
                ProjectileOptimizer.remove(n);
                break;
            }
            if (!bl) continue;
        }
    }

    public static void clearAll() {
        block6: {
            block7: {
                boolean bl = a;
                ClientWorld clientWorld = MinecraftClient.method_1551().field_1687;
                if (bl) break block6;
                if (clientWorld == null) break block7;
                for (Integer n : ACTIVE.keySet()) {
                    clientWorld.method_2945(n.intValue(), Entity.RemovalReason.field_26999);
                    if (!bl) {
                        if (!bl) continue;
                        break;
                    }
                    break block6;
                }
            }
            SOUNDS.clear();
            WORLD_EVENTS.clear();
            SPAWNED.clear();
            ACTIVE.clear();
            EXPLOSIONS.clear();
            PENDING_ARROWS.clear();
            LANDED.clear();
            SIMULATED.clear();
            HIT_BEFORE_PACKET.clear();
            SUPPRESSED_UUIDS.clear();
        }
    }

    private static boolean lambda$tickSimulations$14(LivingEntity livingEntity) {
        return true;
    }

    private static boolean lambda$tick$13(ClientWorld clientWorld, Map.Entry entry) {
        block10: {
            ClientWorld clientWorld2;
            block11: {
                boolean bl;
                block9: {
                    int n;
                    block8: {
                        bl = a;
                        n = tickCounter;
                        if (bl) break block8;
                        if (n - (Integer)entry.getValue() >= 150) break block9;
                        n = 0;
                    }
                    return n != 0;
                }
                clientWorld2 = clientWorld;
                if (bl) break block10;
                if (clientWorld2 == null) break block11;
                clientWorld.method_2945(((Integer)entry.getKey()).intValue(), Entity.RemovalReason.field_26999);
            }
            PENDING_ARROWS.remove(entry.getKey());
            clientWorld2 = LANDED.remove(entry.getKey());
        }
        return true;
    }

    private static boolean lambda$tick$12(Map.Entry entry) {
        int n;
        block4: {
            block5: {
                boolean bl = a;
                n = tickCounter;
                if (bl) break block4;
                if (n - (Integer)entry.getValue() < 20) break block5;
                n = 1;
                break block4;
            }
            n = 0;
        }
        return n != 0;
    }

    private static boolean lambda$tick$11(Map.Entry entry) {
        int n;
        block4: {
            block5: {
                boolean bl = a;
                n = tickCounter;
                if (bl) break block4;
                if (n - (Integer)entry.getValue() < 20) break block5;
                n = 1;
                break block4;
            }
            n = 0;
        }
        return n != 0;
    }

    private static boolean lambda$tick$10(ProjectileOptimizer$ExplosionEntry projectileOptimizer$ExplosionEntry) {
        int n;
        block4: {
            block5: {
                boolean bl = a;
                n = tickCounter;
                if (bl) break block4;
                if (n - projectileOptimizer$ExplosionEntry.tick() < 20) break block5;
                n = 1;
                break block4;
            }
            n = 0;
        }
        return n != 0;
    }

    private static boolean lambda$tick$9(ProjectileOptimizer$SpawnEntry projectileOptimizer$SpawnEntry) {
        int n;
        block4: {
            block5: {
                boolean bl = a;
                n = tickCounter;
                if (bl) break block4;
                if (n - projectileOptimizer$SpawnEntry.tick() < 20) break block5;
                n = 1;
                break block4;
            }
            n = 0;
        }
        return n != 0;
    }

    private static boolean lambda$tick$8(Map.Entry entry) {
        return ((ArrayDeque)entry.getValue()).isEmpty();
    }

    private static void lambda$tick$7(ArrayDeque arrayDeque) {
        arrayDeque.removeIf(ProjectileOptimizer::lambda$tick$6);
    }

    private static boolean lambda$tick$6(Integer n) {
        int n2;
        block4: {
            block5: {
                boolean bl = a;
                n2 = tickCounter;
                if (bl) break block4;
                if (n2 - n < 20) break block5;
                n2 = 1;
                break block4;
            }
            n2 = 0;
        }
        return n2 != 0;
    }

    private static boolean lambda$tick$5(Map.Entry entry) {
        return ((ArrayDeque)entry.getValue()).isEmpty();
    }

    private static void lambda$tick$4(ArrayDeque arrayDeque) {
        arrayDeque.removeIf(ProjectileOptimizer::lambda$tick$3);
    }

    private static boolean lambda$tick$3(Integer n) {
        int n2;
        block4: {
            block5: {
                boolean bl = a;
                n2 = tickCounter;
                if (bl) break block4;
                if (n2 - n < 20) break block5;
                n2 = 1;
                break block4;
            }
            n2 = 0;
        }
        return n2 != 0;
    }

    private static void lambda$register$2(MinecraftClient minecraftClient) {
        ProjectileOptimizer.tick();
    }

    private static ArrayDeque lambda$worldEvent$1(Integer n) {
        return new ArrayDeque();
    }

    private static ArrayDeque lambda$throwSound$0(Identifier identifier) {
        return new ArrayDeque();
    }

    
    static {
        long l = 2550428501295381133L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "\u00ca)\u0097:\u008c\u00be:\u001b\u0017Ue\u009e\u008c\u00be:\u00dd\u00d6\u00b2\u00165\u008c\u00be:\u0099";
        int n2 = "\u00ca)\u0097:\u008c\u00be:\u001b\u0017Ue\u009e\u008c\u00be:\u00dd\u00d6\u00b2\u00165\u008c\u00be:\u0099".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        EXPIRY = (int)lArray[2];
        ENTITY_EXPIRY = (int)lArray[0];
        SIM_MAX_TICKS = (int)lArray[1];
        long l2 = 3710991632046131047L;
        long[] lArray2 = new long[2];
        int n5 = 0;
        String string2 = "?\u00c8\u0097\u00e2\u00a0\u0098S\u0084\u0011 \u0098l\u0088\u001f\u00d6\u00c8";
        int n6 = "?\u00c8\u0097\u00e2\u00a0\u0098S\u0084\u0011 \u0098l\u0088\u001f\u00d6\u00c8".length();
        int n7 = 0;
        do {
            byte[] byArray = string2.substring(n7, n7 += 8).getBytes("ISO-8859-1");
            int n8 = n5++;
            lArray2[n8] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l2;
        } while (n7 < n6);
        b = lArray2;
        c = new Long[2];
        COOLDOWN_ECHO_MS = ProjectileOptimizer.a(19373, 885098995873403199L);
        SOUNDS = new HashMap<Identifier, ArrayDeque<Integer>>();
        WORLD_EVENTS = new HashMap<Integer, ArrayDeque<Integer>>();
        SPAWNED = new ArrayList<ProjectileOptimizer$SpawnEntry>();
        ACTIVE = new HashMap<Integer, Integer>();
        EXPLOSIONS = new ArrayList<ProjectileOptimizer$ExplosionEntry>();
        PENDING_ARROWS = new HashMap<Integer, EntitySpawnS2CPacket>();
        LANDED = new HashMap<Integer, Vec3d>();
        SIMULATED = new ArrayList<ProjectileOptimizer$SimArrow>();
        HIT_BEFORE_PACKET = new HashMap<Integer, Integer>();
        SUPPRESSED_UUIDS = new HashMap<UUID, Integer>();
        lastWorld = new WeakReference<Object>(null);
        serverMovement = Vec3d.field_1353;
        LOCAL_COOLDOWNS = new HashMap<Identifier, Long>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x1A92) & Short.MAX_VALUE;
        if (c[n2] == null) {
            ProjectileOptimizer.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
