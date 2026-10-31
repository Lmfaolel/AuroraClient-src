package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.module.modules.OptimizersModule;
import dev.quelchi.aurora.optimize.ClientExplosion;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import dev.quelchi.aurora.prediction.Prediction;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Property;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public final class AnchorOptimizer {
    private static final float POWER = 5.0f;
    private static final List<Block> DESTROYS = List.of(Blocks.field_10171);
    private static final double SOUND_MATCH_SQ = 2.0;
    private static final Set<BlockPos> INTERACTED = Collections.newSetFromMap(new ConcurrentHashMap());
    private static final Set<BlockPos> PENDING_SOUNDS = Collections.newSetFromMap(new ConcurrentHashMap());

    private AnchorOptimizer() {
    }

    private static boolean on() {
        boolean bl;
        block11: {
            block9: {
                boolean bl2;
                block10: {
                    OptimizersModule optimizersModule;
                    block8: {
                        OptimizersModule optimizersModule2 = OptimizersModule.get();
                        bl2 = ProjectileOptimizer.a;
                        optimizersModule = optimizersModule2;
                        if (bl2) break block8;
                        if (optimizersModule == null) break block9;
                        optimizersModule = optimizersModule2;
                    }
                    bl = optimizersModule.anchors();
                    if (bl2) break block10;
                    if (!bl) break block9;
                    bl = MinecraftClient.method_1551().method_1542();
                }
                if (bl2) break block11;
                if (bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static void onDetonate(World world, BlockPos blockPos, BlockState blockState) {
        boolean bl;
        BlockPos blockPos2;
        ClientWorld clientWorld;
        block32: {
            block34: {
                block33: {
                    boolean bl2;
                    block30: {
                        block31: {
                            BlockState blockState2;
                            block29: {
                                boolean bl3;
                                block27: {
                                    block28: {
                                        block35: {
                                            World world2;
                                            block26: {
                                                boolean bl4;
                                                block24: {
                                                    block25: {
                                                        bl2 = ProjectileOptimizer.a;
                                                        bl4 = AnchorOptimizer.on();
                                                        if (bl2) break block24;
                                                        if (bl4) break block25;
                                                        return;
                                                    }
                                                    world2 = world;
                                                    if (bl2) break block26;
                                                    bl4 = world2 instanceof ClientWorld;
                                                }
                                                if (!bl4) break block35;
                                                world2 = world;
                                            }
                                            clientWorld = (ClientWorld)world2;
                                            bl3 = world.method_8608();
                                            if (bl2) break block27;
                                            if (bl3) break block28;
                                        }
                                        return;
                                    }
                                    blockState2 = blockState;
                                    if (bl2) break block29;
                                    bl3 = blockState2.method_28498((Property)RespawnAnchorBlock.field_23153);
                                }
                                if (!bl3) {
                                    return;
                                }
                                blockState2 = blockState;
                            }
                            if ((Integer)blockState2.method_11654((Property)RespawnAnchorBlock.field_23153) == 0) {
                                return;
                            }
                            blockPos2 = blockPos.method_10062();
                            clientWorld.method_8501(blockPos2, Blocks.field_10124.method_9564());
                            INTERACTED.add(blockPos2);
                            if (!AnchorOptimizer.clientExplosion()) {
                                return;
                            }
                            Box box = new Box(blockPos2);
                            bl = clientWorld.method_8335(null, box).isEmpty();
                            if (bl2) break block30;
                            if (bl) break block31;
                            return;
                        }
                        bl = Direction.Type.field_11062.method_29716().map(arg_0 -> ((BlockPos)blockPos2).method_10093(arg_0)).anyMatch(arg_0 -> AnchorOptimizer.lambda$onDetonate$0(clientWorld, arg_0));
                    }
                    if (bl2) break block32;
                    if (bl) break block33;
                    bl = clientWorld.method_8316(blockPos2.method_10084()).method_15767(FluidTags.field_15517);
                    if (bl2) break block32;
                    if (!bl) break block34;
                }
                bl = true;
                break block32;
            }
            bl = false;
        }
        boolean bl5 = bl;
        new ClientExplosion(clientWorld, blockPos2.method_46558(), 5.0f, blockPos2, bl5, DESTROYS).explode();
    }

    private static boolean clientExplosion() {
        boolean bl;
        block9: {
            block7: {
                boolean bl2;
                block8: {
                    OptimizersModule optimizersModule;
                    block6: {
                        OptimizersModule optimizersModule2 = OptimizersModule.get();
                        bl2 = ProjectileOptimizer.a;
                        optimizersModule = optimizersModule2;
                        if (bl2) break block6;
                        if (optimizersModule == null) break block7;
                        optimizersModule = optimizersModule2;
                    }
                    bl = optimizersModule.anchorClientExplosion();
                    if (bl2) break block8;
                    if (!bl) break block7;
                    bl = Prediction.active();
                }
                if (bl2) break block9;
                if (bl) break block7;
                bl = true;
                break block9;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean hasStillWater(BlockPos blockPos, World world) {
        boolean bl;
        block20: {
            block21: {
                FluidState fluidState;
                boolean bl2;
                block18: {
                    block19: {
                        block16: {
                            FluidState fluidState2;
                            block17: {
                                block14: {
                                    block15: {
                                        fluidState2 = world.method_8316(blockPos);
                                        bl2 = ProjectileOptimizer.a;
                                        fluidState = fluidState2;
                                        if (bl2) break block14;
                                        if (fluidState.method_15767(FluidTags.field_15517)) break block15;
                                        return false;
                                    }
                                    fluidState = fluidState2;
                                }
                                if (bl2) break block16;
                                if (!fluidState.method_15771()) break block17;
                                return true;
                            }
                            fluidState = fluidState2;
                        }
                        if (bl2) break block18;
                        if (fluidState.method_15761() >= 2) break block19;
                        return false;
                    }
                    fluidState = world.method_8316(blockPos.method_10074());
                }
                bl = fluidState.method_15767(FluidTags.field_15517);
                if (bl2) break block20;
                if (bl) break block21;
                bl = true;
                break block20;
            }
            bl = false;
        }
        return bl;
    }

    public static void onCharge(BlockPos blockPos, ClientWorld clientWorld) {
        block7: {
            block6: {
                if (!AnchorOptimizer.on() || clientWorld == null) break block6;
                if (blockPos != null) break block7;
            }
            return;
        }
        clientWorld.method_8486((double)blockPos.method_10263() + 0.5, (double)blockPos.method_10264() + 0.5, (double)blockPos.method_10260() + 0.5, SoundEvents.field_23116, SoundCategory.field_15245, 1.0f, 1.0f, false);
        PENDING_SOUNDS.add(blockPos.method_10062());
    }

    public static boolean suppressesSound(SoundEvent soundEvent, Vec3d vec3d) {
        block18: {
            boolean bl;
            block17: {
                boolean bl2;
                block16: {
                    boolean bl3;
                    block14: {
                        block15: {
                            bl2 = ProjectileOptimizer.a;
                            bl3 = AnchorOptimizer.on();
                            if (bl2) break block14;
                            if (!bl3) break block15;
                            bl3 = PENDING_SOUNDS.isEmpty();
                            if (bl2) break block14;
                            if (bl3) break block15;
                            if (soundEvent == null) break block15;
                            if (vec3d != null) break block16;
                        }
                        bl3 = false;
                    }
                    return bl3;
                }
                bl = soundEvent.equals((Object)SoundEvents.field_23116);
                if (bl2) break block17;
                if (bl) break block18;
                bl = false;
            }
            return bl;
        }
        BlockPos blockPos = new BlockPos((int)vec3d.method_10216(), (int)vec3d.method_10214(), (int)vec3d.method_10215());
        return PENDING_SOUNDS.stream().anyMatch(arg_0 -> AnchorOptimizer.lambda$suppressesSound$1(blockPos, arg_0));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static HitResult phantom(Entity entity, double d, float f) {
        Entity entity2;
        boolean bl;
        block18: {
            block19: {
                boolean bl2;
                block17: {
                    bl = ProjectileOptimizer.a;
                    bl2 = INTERACTED.isEmpty();
                    if (bl) break block17;
                    if (bl2) return null;
                    bl2 = AnchorOptimizer.on();
                }
                if (!bl2) return null;
                entity2 = entity;
                if (bl) break block18;
                if (entity2 != null) break block19;
                return null;
            }
            entity2 = entity;
        }
        Vec3d vec3d = entity2.method_5836(f);
        Vec3d vec3d2 = entity.method_5828(f);
        Vec3d vec3d3 = vec3d.method_1031(vec3d2.field_1352 * d, vec3d2.field_1351 * d, vec3d2.field_1350 * d);
        BlockHitResult blockHitResult = null;
        double d2 = Double.MAX_VALUE;
        for (BlockPos blockPos : INTERACTED) {
            Optional optional;
            block20: {
                optional = new Box(blockPos).method_992(vec3d, vec3d3);
                if (optional.isEmpty()) {
                    continue;
                }
                double d3 = vec3d.method_1025((Vec3d)optional.get());
                if (bl) break block20;
                if (d3 >= d2) {
                    continue;
                }
                d2 = d3;
            }
            Direction direction = Direction.method_10142((double)(((Vec3d)optional.get()).field_1352 - ((double)blockPos.method_10263() + 0.5)), (double)(((Vec3d)optional.get()).field_1351 - ((double)blockPos.method_10264() + 0.5)), (double)(((Vec3d)optional.get()).field_1350 - ((double)blockPos.method_10260() + 0.5)));
            blockHitResult = new BlockHitResult((Vec3d)optional.get(), direction, blockPos, false);
            if (!bl) continue;
        }
        return blockHitResult;
    }

    public static void onServerBlockUpdate(BlockPos blockPos) {
        Set<BlockPos> set;
        block7: {
            block8: {
                block6: {
                    boolean bl = ProjectileOptimizer.a;
                    if (blockPos == null) break block6;
                    set = INTERACTED;
                    if (bl) break block7;
                    if (!set.isEmpty()) break block8;
                }
                return;
            }
            set = INTERACTED;
        }
        set.remove(blockPos);
    }

    public static void clearAll() {
        INTERACTED.clear();
        PENDING_SOUNDS.clear();
    }

    private static boolean lambda$suppressesSound$1(BlockPos blockPos, BlockPos blockPos2) {
        double d;
        block2: {
            block3: {
                boolean bl = ProjectileOptimizer.a;
                double d2 = blockPos2.method_10262((Vec3i)blockPos) - 2.0;
                d = d2 == 0.0 ? 0 : (d2 < 0.0 ? -1 : 1);
                if (bl) break block2;
                if (d > 0) break block3;
                d = 1.0;
                break block2;
            }
            d = 0.0;
        }
        return (boolean)d;
    }

    private static boolean lambda$onDetonate$0(ClientWorld clientWorld, BlockPos blockPos) {
        return AnchorOptimizer.hasStillWater(blockPos, (World)clientWorld);
    }
}
