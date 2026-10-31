package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.mixins.EntityAgeAccessor;
import dev.quelchi.aurora.module.modules.OptimizersModule;
import dev.quelchi.aurora.optimize.CrystalPrediction$Local;
import dev.quelchi.aurora.optimize.LocalIds;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import dev.quelchi.aurora.optimize.SeamlessAges;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public final class CrystalPrediction {
    private static final int TIMEOUT;
    private static final int AGE_MEMORY;
    private static final Map<Long, CrystalPrediction$Local> LOCAL;
    private static final Map<Long, float[]> RECENT_AGE;
    private static int tick;
    private static int lastPlacedTick;

    private CrystalPrediction() {
    }

    
    public static boolean on() {
        boolean bl;
        boolean bl2;
        block7: {
            OptimizersModule optimizersModule;
            block6: {
                OptimizersModule optimizersModule2 = OptimizersModule.get();
                bl2 = ProjectileOptimizer.a;
                optimizersModule = optimizersModule2;
                if (bl2) break block6;
                if (optimizersModule == null) break block7;
                optimizersModule = optimizersModule2;
            }
            bl = optimizersModule.clientCrystals();
            if (bl2) return bl;
            if (bl) return true;
        }
        bl = CrystalPrediction.fastPath();
        if (bl2) return bl;
        if (bl) return true;
        return false;
    }

    public static void register() {
        UseBlockCallback.EVENT.register(CrystalPrediction::lambda$register$0);
        ClientEntityEvents.ENTITY_LOAD.register(CrystalPrediction::lambda$register$1);
        ClientEntityEvents.ENTITY_UNLOAD.register(CrystalPrediction::lambda$register$2);
        ClientTickEvents.END_CLIENT_TICK.register(CrystalPrediction::lambda$register$3);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onUseBlock(Hand hand, BlockHitResult blockHitResult) {
        MinecraftClient minecraftClient;
        BlockPos blockPos;
        block42: {
            MinecraftClient minecraftClient2;
            block43: {
                BlockPos blockPos2;
                boolean bl;
                block40: {
                    BlockPos blockPos3;
                    block41: {
                        block39: {
                            int n;
                            block38: {
                                block36: {
                                    block37: {
                                        MinecraftClient minecraftClient3;
                                        block34: {
                                            block35: {
                                                block32: {
                                                    block33: {
                                                        block31: {
                                                            bl = ProjectileOptimizer.a;
                                                            if (!CrystalPrediction.on()) {
                                                                return;
                                                            }
                                                            minecraftClient2 = MinecraftClient.method_1551();
                                                            minecraftClient3 = minecraftClient2;
                                                            if (bl) break block31;
                                                            if (minecraftClient3.field_1724 == null) return;
                                                            minecraftClient3 = minecraftClient2;
                                                        }
                                                        if (bl) break block32;
                                                        if (minecraftClient3.field_1687 != null) break block33;
                                                        return;
                                                    }
                                                    minecraftClient3 = minecraftClient2;
                                                }
                                                if (bl) break block34;
                                                if (!minecraftClient3.field_1724.method_7325()) break block35;
                                                return;
                                            }
                                            minecraftClient3 = minecraftClient2;
                                        }
                                        n = CrystalPrediction.holdingCrystal(minecraftClient3);
                                        if (bl) break block36;
                                        if (n != 0) break block37;
                                        return;
                                    }
                                    n = tick;
                                }
                                if (bl) break block38;
                                if (n != lastPlacedTick) break block39;
                                n = CrystalPrediction.fastPath() ? 1 : 0;
                            }
                            if (n == 0) {
                                return;
                            }
                        }
                        blockPos3 = CrystalPrediction.baseOf(minecraftClient2, blockHitResult);
                        blockPos2 = blockPos3;
                        if (bl) break block40;
                        if (blockPos2 != null) break block41;
                        return;
                    }
                    blockPos2 = blockPos3.method_10084();
                }
                blockPos = blockPos2;
                if (LOCAL.containsKey(blockPos.method_10063())) {
                    return;
                }
                minecraftClient = minecraftClient2;
                if (bl) break block42;
                if (CrystalPrediction.realNear(minecraftClient, blockPos) == null) break block43;
                return;
            }
            minecraftClient = minecraftClient2;
        }
        CrystalPrediction.spawn(minecraftClient, blockPos);
        lastPlacedTick = tick;
    }

    private static void spawn(MinecraftClient minecraftClient, BlockPos blockPos) {
        ClientWorld clientWorld = minecraftClient.field_1687;
        if (clientWorld == null) {
            return;
        }
        double d = (double)blockPos.method_10263() + 0.5;
        double d2 = blockPos.method_10264();
        double d3 = (double)blockPos.method_10260() + 0.5;
        EndCrystalEntity endCrystalEntity = new EndCrystalEntity((World)clientWorld, d, d2, d3);
        endCrystalEntity.method_5826(UUID.randomUUID());
        endCrystalEntity.method_5838(LocalIds.next());
        endCrystalEntity.method_6839(false);
        endCrystalEntity.field_5960 = true;
        endCrystalEntity.method_5808(d, d2, d3, 0.0f, 0.0f);
        clientWorld.method_53875((Entity)endCrystalEntity);
        LOCAL.put(blockPos.method_10063(), new CrystalPrediction$Local(endCrystalEntity, tick + 12));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onEntityLoaded(Entity entity) {
        int n;
        float[] fArray;
        long l;
        block39: {
            Object object;
            EndCrystalEntity endCrystalEntity;
            boolean bl;
            block37: {
                block38: {
                    Entity entity2;
                    block36: {
                        boolean bl2;
                        block35: {
                            block33: {
                                block34: {
                                    Entity entity3;
                                    block31: {
                                        block32: {
                                            bl = ProjectileOptimizer.a;
                                            entity3 = entity;
                                            if (bl) break block31;
                                            if (entity3.method_5628() >= 0) break block32;
                                            return;
                                        }
                                        entity3 = entity;
                                    }
                                    bl2 = entity3 instanceof EndCrystalEntity;
                                    if (bl) break block33;
                                    if (!bl2) break block34;
                                    endCrystalEntity = (EndCrystalEntity)entity;
                                    Prediction.onCrystalLoaded(endCrystalEntity);
                                }
                                bl2 = CrystalPrediction.on();
                            }
                            if (bl) break block35;
                            if (!bl2) return;
                            entity2 = entity;
                            if (bl) break block36;
                            bl2 = entity2 instanceof EndCrystalEntity;
                        }
                        if (!bl2) return;
                        entity2 = entity;
                    }
                    endCrystalEntity = (EndCrystalEntity)entity2;
                    if (bl) {
                        return;
                    }
                    l = endCrystalEntity.method_24515().method_10063();
                    CrystalPrediction$Local crystalPrediction$Local = LOCAL.remove(l);
                    object = crystalPrediction$Local;
                    if (bl) break block37;
                    if (object == null) break block38;
                    object = crystalPrediction$Local;
                    if (bl) break block37;
                    if (((CrystalPrediction$Local)object).entity() == null) break block38;
                    object = crystalPrediction$Local;
                    if (bl) break block37;
                    if (!((CrystalPrediction$Local)object).entity().method_5805()) break block38;
                    float f = ((EntityAgeAccessor)crystalPrediction$Local.entity()).aurora$age();
                    SeamlessAges.link(endCrystalEntity.method_5628(), SeamlessAges.lastRendered(crystalPrediction$Local.entity().method_5628(), f));
                    SeamlessAges.forget(crystalPrediction$Local.entity().method_5628());
                    crystalPrediction$Local.entity().method_31472();
                    return;
                }
                object = RECENT_AGE.get(l);
            }
            fArray = (float[])object;
            if (fArray == null) return;
            n = tick;
            if (bl) break block39;
            if (!((float)n <= fArray[1])) return;
            n = endCrystalEntity.method_5628();
        }
        SeamlessAges.link(n, fArray[0]);
        RECENT_AGE.remove(l);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onEntityUnloaded(Entity entity) {
        CrystalPrediction$Local crystalPrediction$Local;
        long l;
        EndCrystalEntity endCrystalEntity;
        block12: {
            Entity entity2;
            boolean bl;
            block11: {
                bl = ProjectileOptimizer.a;
                entity2 = entity;
                if (bl) break block11;
                if (!(entity2 instanceof EndCrystalEntity)) return;
                entity2 = entity;
            }
            endCrystalEntity = (EndCrystalEntity)entity2;
            if (bl) {
                return;
            }
            SeamlessAges.forget(endCrystalEntity.method_5628());
            l = endCrystalEntity.method_24515().method_10063();
            CrystalPrediction$Local crystalPrediction$Local2 = LOCAL.get(l);
            crystalPrediction$Local = crystalPrediction$Local2;
            if (bl) break block12;
            if (crystalPrediction$Local == null) return;
            crystalPrediction$Local = crystalPrediction$Local2;
        }
        if (crystalPrediction$Local.entity() != endCrystalEntity) return;
        CrystalPrediction.remove(l);
        return;
    }

    public static boolean isLocal(Entity entity) {
        Map<Long, CrystalPrediction$Local> map;
        boolean bl;
        block8: {
            block9: {
                bl = ProjectileOptimizer.a;
                map = LOCAL;
                if (bl) break block8;
                if (!map.isEmpty()) break block9;
                return false;
            }
            map = LOCAL;
        }
        for (CrystalPrediction$Local crystalPrediction$Local : map.values()) {
            if (crystalPrediction$Local.entity() == entity) {
                return true;
            }
            if (!bl) continue;
        }
        return false;
    }

    
    public static void tick() {
        block32: {
            MinecraftClient minecraftClient;
            block44: {
                boolean bl;
                MinecraftClient minecraftClient2;
                block38: {
                    boolean bl2;
                    block37: {
                        boolean bl3;
                        block33: {
                            block34: {
                                MinecraftClient minecraftClient3;
                                block36: {
                                    block35: {
                                        minecraftClient2 = MinecraftClient.method_1551();
                                        bl = ProjectileOptimizer.a;
                                        bl3 = CrystalPrediction.on();
                                        if (bl) break block33;
                                        if (!bl3) break block34;
                                        minecraftClient3 = minecraftClient2;
                                        if (bl) break block35;
                                        if (minecraftClient3.field_1687 == null) break block34;
                                        minecraftClient3 = minecraftClient2;
                                    }
                                    if (bl) break block36;
                                    if (minecraftClient3.field_1724 == null) break block37;
                                    minecraftClient3 = minecraftClient2;
                                }
                                bl2 = minecraftClient3.field_1724.method_7325();
                                if (bl) break block38;
                                if (!bl2) break block37;
                            }
                            bl3 = LOCAL.isEmpty();
                        }
                        if (!bl3) {
                            CrystalPrediction.clearAll();
                        }
                        return;
                    }
                    ++tick;
                    bl2 = RECENT_AGE.entrySet().removeIf(CrystalPrediction::lambda$tick$4);
                }
                Long[] longArray = LOCAL.keySet().toArray(new Long[0]);
                int n = longArray.length;
                int n2 = 0;
                while (n2 < n) {
                    block43: {
                        block42: {
                            CrystalPrediction$Local crystalPrediction$Local;
                            Long l;
                            block41: {
                                block39: {
                                    CrystalPrediction$Local crystalPrediction$Local2;
                                    block40: {
                                        l = longArray[n2];
                                        if (bl) break block32;
                                        crystalPrediction$Local = LOCAL.get(l);
                                        if (bl) break block39;
                                        if (crystalPrediction$Local == null) break block40;
                                        crystalPrediction$Local2 = crystalPrediction$Local;
                                        if (bl) break block39;
                                        if (crystalPrediction$Local2.entity() == null) break block40;
                                        crystalPrediction$Local2 = crystalPrediction$Local;
                                        if (bl) break block39;
                                        if (crystalPrediction$Local2.entity().method_31481()) break block40;
                                        crystalPrediction$Local2 = crystalPrediction$Local;
                                        if (bl) break block39;
                                        if (crystalPrediction$Local2.entity().method_5805()) break block41;
                                    }
                                    crystalPrediction$Local2 = LOCAL.remove(l);
                                }
                                if (!bl) break block42;
                            }
                            BlockPos blockPos = BlockPos.method_10092((long)l);
                            crystalPrediction$Local.entity().method_5808((double)blockPos.method_10263() + 0.5, (double)blockPos.method_10264(), (double)blockPos.method_10260() + 0.5, 0.0f, 0.0f);
                            if (bl) break block43;
                            crystalPrediction$Local.entity().method_6839(false);
                            crystalPrediction$Local.entity().field_5960 = true;
                            if (tick >= crystalPrediction$Local.expires()) {
                                CrystalPrediction.remove(l);
                            }
                        }
                        ++n2;
                    }
                    if (!bl) continue;
                }
                minecraftClient = minecraftClient2;
                if (bl) break block44;
                if (!minecraftClient.field_1690.field_1886.method_1434()) break block32;
                minecraftClient = minecraftClient2;
            }
            CrystalPrediction.breakUnderCrosshair(minecraftClient);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void breakUnderCrosshair(MinecraftClient minecraftClient) {
        HitResult hitResult;
        boolean bl;
        block17: {
            HitResult hitResult2;
            block18: {
                block16: {
                    hitResult2 = minecraftClient.field_1765;
                    bl = ProjectileOptimizer.a;
                    hitResult = hitResult2;
                    if (bl) break block16;
                    if (hitResult == null) return;
                    hitResult = hitResult2;
                }
                if (bl) break block17;
                if (hitResult.method_17783() == HitResult.Type.field_1331) break block18;
                return;
            }
            hitResult = hitResult2;
        }
        Entity entity = ((EntityHitResult)hitResult).method_17782();
        if (!(entity instanceof EndCrystalEntity)) {
            return;
        }
        Long[] longArray = LOCAL.keySet().toArray(new Long[0]);
        int n = longArray.length;
        int n2 = 0;
        while (n2 < n) {
            block19: {
                block20: {
                    Long l = longArray[n2];
                    CrystalPrediction$Local crystalPrediction$Local = LOCAL.get(l);
                    if (bl) break block19;
                    if (crystalPrediction$Local == null) break block20;
                    if (crystalPrediction$Local.entity() != entity) break block20;
                    CrystalPrediction.remove(l);
                    return;
                }
                ++n2;
            }
            if (!bl) continue;
        }
    }

    public static void clearAll() {
        block4: {
            Long[] longArray = LOCAL.keySet().toArray(new Long[0]);
            boolean bl = ProjectileOptimizer.a;
            for (Long l : longArray) {
                CrystalPrediction.remove(l);
                if (!bl) {
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            LOCAL.clear();
            RECENT_AGE.clear();
            SeamlessAges.clearAll();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void remove(long l) {
        CrystalPrediction$Local crystalPrediction$Local;
        block13: {
            CrystalPrediction$Local crystalPrediction$Local2;
            boolean bl;
            CrystalPrediction$Local crystalPrediction$Local3;
            block11: {
                block12: {
                    block10: {
                        crystalPrediction$Local3 = LOCAL.remove(l);
                        bl = ProjectileOptimizer.a;
                        crystalPrediction$Local2 = crystalPrediction$Local3;
                        if (bl) break block10;
                        if (crystalPrediction$Local2 == null) return;
                        crystalPrediction$Local2 = crystalPrediction$Local3;
                    }
                    if (bl) break block11;
                    if (crystalPrediction$Local2.entity() != null) break block12;
                    return;
                }
                crystalPrediction$Local2 = crystalPrediction$Local3;
            }
            int n = crystalPrediction$Local2.entity().method_5628();
            float f = SeamlessAges.lastRendered(n, ((EntityAgeAccessor)crystalPrediction$Local3.entity()).aurora$age());
            RECENT_AGE.put(l, new float[]{f, tick + 20});
            SeamlessAges.forget(n);
            crystalPrediction$Local = crystalPrediction$Local3;
            if (bl) break block13;
            if (!crystalPrediction$Local.entity().method_5805()) return;
            crystalPrediction$Local = crystalPrediction$Local3;
        }
        crystalPrediction$Local.entity().method_31472();
    }

    private static boolean fastPath() {
        return PredictionHandshake.isActive();
    }

    private static boolean holdingCrystal(MinecraftClient minecraftClient) {
        boolean bl;
        block6: {
            block8: {
                block7: {
                    boolean bl2 = ProjectileOptimizer.a;
                    bl = minecraftClient.field_1724.method_6047().method_31574(Items.field_8301);
                    if (bl2) break block6;
                    if (bl) break block7;
                    bl = minecraftClient.field_1724.method_6079().method_31574(Items.field_8301);
                    if (bl2) break block6;
                    if (!bl) break block8;
                }
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static BlockPos baseOf(MinecraftClient minecraftClient, BlockHitResult blockHitResult) {
        BlockHitResult blockHitResult2;
        block15: {
            block16: {
                boolean bl;
                block13: {
                    block14: {
                        block12: {
                            bl = ProjectileOptimizer.a;
                            blockHitResult2 = blockHitResult;
                            if (bl) break block12;
                            if (blockHitResult2 == null) return null;
                            blockHitResult2 = blockHitResult;
                        }
                        if (bl) break block13;
                        if (blockHitResult2.method_17783() == HitResult.Type.field_1332) break block14;
                        return null;
                    }
                    blockHitResult2 = blockHitResult;
                }
                if (bl) break block15;
                if (blockHitResult2.method_17780() != Direction.field_11033) break block16;
                return null;
            }
            blockHitResult2 = blockHitResult;
        }
        BlockPos blockPos = blockHitResult2.method_17777();
        if (!CrystalPrediction.validBase(minecraftClient, blockPos)) return null;
        BlockPos blockPos2 = blockPos;
        return blockPos2;
    }

    private static boolean validBase(MinecraftClient minecraftClient, BlockPos blockPos) {
        ClientWorld clientWorld;
        BlockPos blockPos2;
        block26: {
            BlockPos blockPos3;
            ClientWorld clientWorld2;
            block24: {
                boolean bl;
                block25: {
                    block22: {
                        boolean bl2;
                        block23: {
                            ClientWorld clientWorld3;
                            block21: {
                                block19: {
                                    block20: {
                                        clientWorld2 = minecraftClient.field_1687;
                                        bl = ProjectileOptimizer.a;
                                        clientWorld3 = clientWorld2;
                                        if (bl) break block19;
                                        if (clientWorld3 != null) break block20;
                                        return false;
                                    }
                                    clientWorld3 = clientWorld2;
                                }
                                if (bl) break block21;
                                if (clientWorld3.method_8320(blockPos).method_27852(Blocks.field_10540)) break block22;
                                clientWorld3 = clientWorld2;
                            }
                            bl2 = clientWorld3.method_8320(blockPos).method_27852(Blocks.field_9987);
                            if (bl) break block23;
                            if (bl2) break block22;
                            bl2 = false;
                        }
                        return bl2;
                    }
                    blockPos2 = blockPos.method_10084();
                    clientWorld = clientWorld2;
                    blockPos3 = blockPos2;
                    if (bl) break block24;
                    if (clientWorld.method_8320(blockPos3).method_26215()) break block25;
                    return false;
                }
                clientWorld = clientWorld2;
                if (bl) break block26;
                blockPos3 = blockPos2;
            }
            if (Prediction.otherPlayerTouching((World)clientWorld, CrystalPrediction.boxAt(blockPos3), (PlayerEntity)minecraftClient.field_1724)) {
                return false;
            }
            clientWorld = clientWorld2;
        }
        return clientWorld.method_8333(null, CrystalPrediction.boxAt(blockPos2), CrystalPrediction::lambda$validBase$5).isEmpty();
    }

    private static EndCrystalEntity realNear(MinecraftClient minecraftClient, BlockPos blockPos) {
        ClientWorld clientWorld;
        block4: {
            ClientWorld clientWorld2;
            block5: {
                clientWorld2 = minecraftClient.field_1687;
                boolean bl = ProjectileOptimizer.a;
                clientWorld = clientWorld2;
                if (bl) break block4;
                if (clientWorld != null) break block5;
                return null;
            }
            clientWorld = clientWorld2.method_18023((TypeFilter)EntityType.field_6110, CrystalPrediction.boxAt(blockPos), CrystalPrediction::lambda$realNear$6).stream().findFirst().orElse(null);
        }
        return (EndCrystalEntity)clientWorld;
    }

    private static Box boxAt(BlockPos blockPos) {
        Vec3d vec3d = Vec3d.method_24953((Vec3i)blockPos);
        return new Box(vec3d.method_1031(-0.5, -0.5, -0.5), vec3d.method_1031(0.5, 1.5, 0.5));
    }

    private static boolean lambda$realNear$6(EndCrystalEntity endCrystalEntity) {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = ProjectileOptimizer.a;
                bl = CrystalPrediction.isLocal((Entity)endCrystalEntity);
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean lambda$validBase$5(Entity entity) {
        boolean bl;
        block12: {
            block11: {
                block13: {
                    Entity entity2;
                    boolean bl2;
                    block10: {
                        bl2 = ProjectileOptimizer.a;
                        entity2 = entity;
                        if (bl2) break block10;
                        if (!entity2.method_5805()) break block11;
                        entity2 = entity;
                    }
                    bl = entity2 instanceof EndCrystalEntity;
                    if (bl2) break block12;
                    if (!bl) break block13;
                    bl = CrystalPrediction.isLocal(entity);
                    if (bl2) break block12;
                    if (bl) break block11;
                }
                bl = true;
                break block12;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean lambda$tick$4(Map.Entry entry) {
        boolean bl;
        block4: {
            block5: {
                boolean bl2 = ProjectileOptimizer.a;
                bl = tick;
                if (bl2) break block4;
                if (!((float)bl > ((float[])entry.getValue())[1])) break block5;
                bl = true;
                break block4;
            }
            bl = false;
        }
        return bl;
    }

    private static void lambda$register$3(MinecraftClient minecraftClient) {
        CrystalPrediction.tick();
    }

    private static void lambda$register$2(Entity entity, ClientWorld clientWorld) {
        CrystalPrediction.onEntityUnloaded(entity);
    }

    private static void lambda$register$1(Entity entity, ClientWorld clientWorld) {
        CrystalPrediction.onEntityLoaded(entity);
    }

    private static ActionResult lambda$register$0(PlayerEntity playerEntity, World world, Hand hand, BlockHitResult blockHitResult) {
        block10: {
            boolean bl;
            block11: {
                boolean bl2;
                block9: {
                    bl2 = ProjectileOptimizer.a;
                    bl = world instanceof ClientWorld;
                    if (bl2) break block9;
                    if (!bl) break block10;
                    bl = CrystalPrediction.on();
                }
                if (bl2) break block11;
                if (!bl) break block10;
                bl = playerEntity.method_5998(hand).method_31574(Items.field_8301);
            }
            if (bl) {
                CrystalPrediction.onUseBlock(hand, blockHitResult);
            }
        }
        return ActionResult.field_5811;
    }

    
    static {
        long l = 2384864177932190088L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "\u00d4\u00a8h\u00bb\u00933\u00c1\u0084BJ\u00b7\u00ef\u00933\u00c1\u009c";
        int n2 = "\u00d4\u00a8h\u00bb\u00933\u00c1\u0084BJ\u00b7\u00ef\u00933\u00c1\u009c".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        TIMEOUT = (int)lArray[0];
        AGE_MEMORY = (int)lArray[1];
        LOCAL = new HashMap<Long, CrystalPrediction$Local>();
        RECENT_AGE = new HashMap<Long, float[]>();
        lastPlacedTick = -1;
    }
}
