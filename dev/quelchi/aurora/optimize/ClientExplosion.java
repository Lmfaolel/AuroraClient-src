package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

public final class ClientExplosion {
    private final ClientWorld world;
    private final Vec3d pos;
    private final float power;
    private final BlockPos explodedPos;
    private final boolean checkWater;
    private final List<Block> focusedBlocks;

    public ClientExplosion(ClientWorld clientWorld, Vec3d vec3d, float f, BlockPos blockPos, boolean bl, List<Block> list) {
        this.world = clientWorld;
        this.pos = vec3d;
        this.power = f;
        this.explodedPos = blockPos;
        this.checkWater = bl;
        this.focusedBlocks = list;
    }

    public Map<BlockPos, BlockState> explode() {
        HashMap<BlockPos, BlockState> hashMap;
        block4: {
            List<BlockPos> list = this.getBlocksToDestroy();
            hashMap = new HashMap<BlockPos, BlockState>();
            Iterator<BlockPos> iterator = list.iterator();
            boolean bl = ProjectileOptimizer.a;
            while (iterator.hasNext()) {
                BlockPos blockPos = iterator.next();
                if (!bl) {
                    hashMap.put(blockPos, this.world.method_8320(blockPos));
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            this.destroyBlocks(list);
        }
        return hashMap;
    }

    private void destroyBlocks(List<BlockPos> list) {
        Util.method_43028(list, (Random)this.world.field_9229);
        boolean bl = ProjectileOptimizer.a;
        for (BlockPos blockPos : list) {
            block11: {
                ClientExplosion clientExplosion;
                block9: {
                    block10: {
                        Block block = this.world.method_8320(blockPos).method_26204();
                        clientExplosion = this;
                        if (bl) break block9;
                        if (clientExplosion.focusedBlocks == null) break block10;
                        clientExplosion = this;
                        if (bl) break block9;
                        if (!clientExplosion.focusedBlocks.contains(block)) break block11;
                    }
                    clientExplosion = this;
                }
                clientExplosion.world.method_8501(blockPos, Blocks.field_10124.method_9564());
            }
            if (!bl) continue;
        }
    }

    private List<BlockPos> getBlocksToDestroy() {
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
        boolean bl = ProjectileOptimizer.a;
        int n = 0;
        while (n < 16) {
            block44: {
                int n2 = 0;
                while (n2 < 16) {
                    block45: {
                        if (bl) break block44;
                        block38: for (int i = 0; i < 16; ++i) {
                            int n3;
                            block46: {
                                block47: {
                                    if (bl) break block45;
                                    n3 = n;
                                    if (bl) break block46;
                                    if (n3 == 0) break block47;
                                    n3 = n;
                                    if (bl) break block46;
                                    if (n3 == 15) break block47;
                                    n3 = n2;
                                    if (bl) break block46;
                                    if (n3 == 0) break block47;
                                    n3 = n2;
                                    if (bl) break block46;
                                    if (n3 == 15) break block47;
                                    n3 = i;
                                    if (bl) break block46;
                                    if (n3 == 0) break block47;
                                    n3 = i;
                                    if (bl) break block46;
                                    if (n3 != 15) {
                                        continue;
                                    }
                                }
                                n3 = n;
                            }
                            double d = (float)n3 / 15.0f * 2.0f - 1.0f;
                            double d2 = (float)n2 / 15.0f * 2.0f - 1.0f;
                            double d3 = (float)i / 15.0f * 2.0f - 1.0f;
                            double d4 = Math.sqrt(d * d + d2 * d2 + d3 * d3);
                            d /= d4;
                            d2 /= d4;
                            d3 /= d4;
                            float f = this.power * (0.7f + this.world.field_9229.method_43057() * 0.6f);
                            double d5 = this.pos.field_1352;
                            double d6 = this.pos.field_1351;
                            ClientExplosion clientExplosion = this;
                            block39: while (true) {
                                double d7 = clientExplosion.pos.field_1350;
                                while (f > 0.0f) {
                                    block52: {
                                        float f2;
                                        int n4;
                                        BlockPos blockPos;
                                        block50: {
                                            block51: {
                                                ClientExplosion clientExplosion2;
                                                FluidState fluidState;
                                                BlockState blockState;
                                                block48: {
                                                    blockPos = BlockPos.method_49637((double)d5, (double)d6, (double)d7);
                                                    blockState = this.world.method_8320(blockPos);
                                                    fluidState = this.world.method_8316(blockPos);
                                                    clientExplosion = this;
                                                    if (bl) continue block39;
                                                    if (bl) break block48;
                                                    if (!clientExplosion.world.method_24794(blockPos)) {
                                                        continue block38;
                                                    }
                                                    clientExplosion2 = this;
                                                }
                                                Optional<Float> optional = clientExplosion2.getBlastResistance(blockPos, blockState, fluidState);
                                                n4 = optional.isPresent();
                                                if (bl) break block50;
                                                if (n4 == 0) break block51;
                                                f -= (optional.get().floatValue() + 0.3f) * 0.3f;
                                            }
                                            f2 = f;
                                            if (bl) break block52;
                                            float f3 = f2 - 0.0f;
                                            n4 = f3 == 0.0f ? 0 : (f3 > 0.0f ? 1 : -1);
                                        }
                                        if (n4 > 0) {
                                            hashSet.add(blockPos);
                                        }
                                        d5 += d * (double)0.3f;
                                        d6 += d2 * (double)0.3f;
                                        d7 += d3 * (double)0.3f;
                                        f2 = f = f - 0.22500001f;
                                    }
                                    if (!bl) continue;
                                }
                                break;
                            }
                            if (!bl) continue;
                        }
                        ++n2;
                    }
                    if (!bl) continue;
                }
                ++n;
            }
            if (!bl) continue;
        }
        return new ArrayList<BlockPos>(hashSet);
    }

    private Optional<Float> getBlastResistance(BlockPos blockPos, BlockState blockState, FluidState fluidState) {
        Optional<Float> optional;
        block18: {
            BlockState blockState2;
            block16: {
                block17: {
                    boolean bl;
                    block14: {
                        boolean bl2;
                        block15: {
                            bl2 = ProjectileOptimizer.a;
                            bl = blockPos.equals((Object)this.explodedPos);
                            if (bl2) break block14;
                            if (!bl) break block15;
                            bl = this.checkWater;
                            if (bl2) break block14;
                            if (!bl) break block15;
                            return Optional.of(Float.valueOf(Blocks.field_10382.method_9520()));
                        }
                        blockState2 = blockState;
                        if (bl2) break block16;
                        bl = blockState2.method_26215();
                    }
                    if (!bl || !fluidState.method_15769()) break block17;
                    optional = Optional.empty();
                    break block18;
                }
                blockState2 = blockState;
            }
            optional = Optional.of(Float.valueOf(Math.max(blockState2.method_26204().method_9520(), fluidState.method_15760())));
        }
        return optional;
    }
}
