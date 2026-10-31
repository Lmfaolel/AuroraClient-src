package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.module.modules.OptimizersModule;
import dev.quelchi.aurora.optimize.AnchorPrediction$Prediction;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.consume.UseAction;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class AnchorPrediction {
    private static final Map<BlockPos, AnchorPrediction$Prediction> PREDICTIONS;
    private static final int LIFETIME;
    private static int tickCounter;
    private static WeakReference<ClientWorld> lastWorld;

    private AnchorPrediction() {
    }

    public static void handleFastInteract(ClientWorld clientWorld, Hand hand, BlockHitResult blockHitResult) {
        block4: {
            AnchorPrediction$Prediction anchorPrediction$Prediction = AnchorPrediction.prepare(clientWorld, hand, blockHitResult);
            if (anchorPrediction$Prediction == null || AnchorPrediction.delayedByHeldItem(MinecraftClient.method_1551().field_1724.method_5998(hand))) break block4;
            AnchorPrediction.apply(clientWorld, anchorPrediction$Prediction);
        }
    }

    public static void handleInteract(ClientWorld clientWorld, Hand hand, BlockHitResult blockHitResult) {
        AnchorPrediction$Prediction anchorPrediction$Prediction = AnchorPrediction.prepare(clientWorld, hand, blockHitResult);
        if (anchorPrediction$Prediction != null) {
            AnchorPrediction.apply(clientWorld, anchorPrediction$Prediction);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static AnchorPrediction$Prediction prepare(ClientWorld clientWorld, Hand hand, BlockHitResult blockHitResult) {
        int n;
        BlockState blockState;
        BlockPos blockPos;
        block66: {
            MinecraftClient minecraftClient;
            block67: {
                IntProperty intProperty;
                BlockState blockState2;
                boolean bl;
                block64: {
                    block65: {
                        block62: {
                            block63: {
                                ClientWorld clientWorld2;
                                block60: {
                                    block61: {
                                        block58: {
                                            block59: {
                                                MinecraftClient minecraftClient2;
                                                block56: {
                                                    block57: {
                                                        block55: {
                                                            block54: {
                                                                OptimizersModule optimizersModule;
                                                                block53: {
                                                                    OptimizersModule optimizersModule2 = OptimizersModule.get();
                                                                    bl = ProjectileOptimizer.a;
                                                                    optimizersModule = optimizersModule2;
                                                                    if (bl) break block53;
                                                                    if (optimizersModule == null) return null;
                                                                    optimizersModule = optimizersModule2;
                                                                }
                                                                if (!optimizersModule.cutebowAnchors()) {
                                                                    return null;
                                                                }
                                                                if (clientWorld == null || hand == null) return null;
                                                                if (blockHitResult != null) break block54;
                                                                return null;
                                                            }
                                                            minecraftClient = MinecraftClient.method_1551();
                                                            minecraftClient2 = minecraftClient;
                                                            if (bl) break block55;
                                                            if (minecraftClient2.field_1724 == null) return null;
                                                            minecraftClient2 = minecraftClient;
                                                        }
                                                        if (bl) break block56;
                                                        if (!minecraftClient2.field_1724.method_7325()) break block57;
                                                        return null;
                                                    }
                                                    minecraftClient2 = minecraftClient;
                                                }
                                                if (minecraftClient2.field_1724.method_5715()) {
                                                    return null;
                                                }
                                                if (hand == Hand.field_5810) {
                                                    return null;
                                                }
                                                if (minecraftClient.field_1724.method_6079().method_31574(Items.field_23141)) {
                                                    return null;
                                                }
                                                blockPos = blockHitResult.method_17777();
                                                if (blockPos == null) return null;
                                                clientWorld2 = clientWorld;
                                                if (bl) break block58;
                                                if (clientWorld2.method_22340(blockPos)) break block59;
                                                return null;
                                            }
                                            clientWorld2 = clientWorld;
                                        }
                                        if (bl) break block60;
                                        if (!clientWorld2.method_27983().equals(World.field_25180)) break block61;
                                        return null;
                                    }
                                    clientWorld2 = clientWorld;
                                }
                                blockState = clientWorld2.method_8320(blockPos);
                                blockState2 = blockState;
                                if (bl) break block62;
                                if (blockState2.method_26204() instanceof RespawnAnchorBlock) break block63;
                                return null;
                            }
                            blockState2 = blockState;
                        }
                        intProperty = RespawnAnchorBlock.field_23153;
                        if (bl) break block64;
                        if (blockState2.method_28498((Property)intProperty)) break block65;
                        return null;
                    }
                    blockState2 = blockState;
                    intProperty = RespawnAnchorBlock.field_23153;
                }
                n = (Integer)blockState2.method_11654((Property)intProperty);
                if (bl) break block66;
                if (n > 0) break block67;
                return null;
            }
            n = minecraftClient.field_1724.method_5998(hand).method_31574(Items.field_8801) ? 1 : 0;
        }
        if (n != 0) {
            return null;
        }
        AnchorPrediction$Prediction anchorPrediction$Prediction = new AnchorPrediction$Prediction();
        anchorPrediction$Prediction.pos = blockPos.method_10062();
        anchorPrediction$Prediction.before = blockState;
        return anchorPrediction$Prediction;
    }

    private static void apply(ClientWorld clientWorld, AnchorPrediction$Prediction anchorPrediction$Prediction) {
        block6: {
            AnchorPrediction$Prediction anchorPrediction$Prediction2;
            block5: {
                AnchorPrediction$Prediction anchorPrediction$Prediction3 = PREDICTIONS.get(anchorPrediction$Prediction.pos);
                boolean bl = ProjectileOptimizer.a;
                anchorPrediction$Prediction2 = anchorPrediction$Prediction3;
                if (bl) break block5;
                if (anchorPrediction$Prediction2 == null) break block6;
                anchorPrediction$Prediction2 = anchorPrediction$Prediction3;
            }
            if (anchorPrediction$Prediction2.createdTick == tickCounter) {
                return;
            }
        }
        BlockState blockState = Blocks.field_10499.method_9564();
        clientWorld.method_8652(anchorPrediction$Prediction.pos, blockState, 0);
        anchorPrediction$Prediction.after = blockState;
        anchorPrediction$Prediction.createdTick = tickCounter;
        PREDICTIONS.put(anchorPrediction$Prediction.pos, anchorPrediction$Prediction);
        AnchorPrediction.rerender(anchorPrediction$Prediction.pos);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean delayedByHeldItem(ItemStack itemStack) {
        ItemStack itemStack2;
        boolean bl;
        block63: {
            block64: {
                ItemStack itemStack3;
                block61: {
                    block62: {
                        block59: {
                            block60: {
                                block57: {
                                    block58: {
                                        block56: {
                                            bl = ProjectileOptimizer.a;
                                            itemStack3 = itemStack;
                                            if (bl) break block56;
                                            if (itemStack3 == null) return false;
                                            itemStack3 = itemStack;
                                        }
                                        if (bl) break block57;
                                        if (!itemStack3.method_7960()) break block58;
                                        return false;
                                    }
                                    itemStack3 = itemStack;
                                }
                                if (bl) break block59;
                                if (!itemStack3.method_31574(Items.field_8801)) break block60;
                                return false;
                            }
                            itemStack3 = itemStack;
                        }
                        if (bl) break block61;
                        if (itemStack3.method_7909().method_7853(itemStack) == UseAction.field_8952) break block62;
                        return true;
                    }
                    itemStack3 = itemStack;
                }
                boolean bl2 = itemStack3.method_7909() instanceof BlockItem;
                if (bl) return bl2;
                if (bl2) return true;
                itemStack2 = itemStack;
                if (bl) break block63;
                if (!(itemStack2.method_7909() instanceof BucketItem)) break block64;
                return true;
            }
            itemStack2 = itemStack;
        }
        boolean bl3 = itemStack2.method_31574(Items.field_8634);
        if (bl) return bl3;
        if (bl3) return true;
        bl3 = itemStack.method_31574(Items.field_8449);
        if (bl) return bl3;
        if (bl3) return true;
        bl3 = itemStack.method_31574(Items.field_8287);
        if (bl) return bl3;
        if (bl3) return true;
        bl3 = itemStack.method_31574(Items.field_8436);
        if (bl) return bl3;
        if (bl3) return true;
        bl3 = itemStack.method_31574(Items.field_8150);
        if (bl) return bl3;
        if (bl3) return true;
        bl3 = itemStack.method_31574(Items.field_8803);
        if (bl) return bl3;
        if (bl3) return true;
        bl3 = itemStack.method_31574(Items.field_8543);
        if (bl) return bl3;
        if (bl3) return true;
        bl3 = itemStack.method_31574(Items.field_8639);
        if (bl) return bl3;
        if (bl3) return true;
        bl3 = itemStack.method_31574(Items.field_49098);
        if (bl) return bl3;
        if (!bl3) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean shouldBlockPlacement(Hand hand, BlockHitResult blockHitResult) {
        ItemStack itemStack;
        ItemStack itemStack2;
        block47: {
            ItemStack itemStack3;
            boolean bl;
            block45: {
                block46: {
                    MinecraftClient minecraftClient;
                    block44: {
                        BlockState blockState;
                        block42: {
                            AnchorPrediction$Prediction anchorPrediction$Prediction;
                            block43: {
                                AnchorPrediction$Prediction anchorPrediction$Prediction2;
                                block41: {
                                    Object object;
                                    block39: {
                                        BlockPos blockPos;
                                        block40: {
                                            block38: {
                                                MinecraftClient minecraftClient2;
                                                block37: {
                                                    block36: {
                                                        bl = ProjectileOptimizer.a;
                                                        boolean bl2 = PREDICTIONS.isEmpty();
                                                        if (bl) return bl2;
                                                        if (bl2) return false;
                                                        if (blockHitResult != null) break block36;
                                                        return false;
                                                    }
                                                    minecraftClient = MinecraftClient.method_1551();
                                                    minecraftClient2 = minecraftClient;
                                                    if (bl) break block37;
                                                    if (minecraftClient2.field_1724 == null) return false;
                                                    minecraftClient2 = minecraftClient;
                                                }
                                                boolean bl3 = minecraftClient2.field_1724.method_5715();
                                                if (bl) return bl3;
                                                if (!bl3) break block38;
                                                return false;
                                            }
                                            blockPos = blockHitResult.method_17777();
                                            object = blockPos;
                                            if (bl) break block39;
                                            if (AnchorPrediction.isSuppressed(object)) break block40;
                                            return false;
                                        }
                                        object = PREDICTIONS.get(blockPos);
                                    }
                                    anchorPrediction$Prediction = (AnchorPrediction$Prediction)object;
                                    anchorPrediction$Prediction2 = anchorPrediction$Prediction;
                                    if (bl) break block41;
                                    if (anchorPrediction$Prediction2 == null) return false;
                                    anchorPrediction$Prediction2 = anchorPrediction$Prediction;
                                }
                                blockState = anchorPrediction$Prediction2.before;
                                if (bl) break block42;
                                if (blockState.method_26204() instanceof RespawnAnchorBlock) break block43;
                                return false;
                            }
                            blockState = anchorPrediction$Prediction.before;
                        }
                        boolean bl4 = ((Integer)blockState.method_11654((Property)RespawnAnchorBlock.field_23153)).intValue();
                        if (bl) return bl4;
                        if (bl4 > false) break block44;
                        return false;
                    }
                    itemStack2 = minecraftClient.field_1724.method_5998(hand);
                    itemStack3 = itemStack2;
                    if (bl) break block45;
                    if (!itemStack3.method_7960()) break block46;
                    return false;
                }
                itemStack3 = itemStack2;
            }
            boolean bl5 = itemStack3.method_7909() instanceof BlockItem;
            if (bl) return bl5;
            if (bl5) return true;
            itemStack = itemStack2;
            if (bl) return AnchorPrediction.delayedByHeldItem(itemStack);
            if (!(itemStack.method_7909() instanceof BucketItem)) break block47;
            return true;
        }
        itemStack = itemStack2;
        return AnchorPrediction.delayedByHeldItem(itemStack);
    }

    public static void onServerBlockUpdate(ClientWorld clientWorld, BlockPos blockPos, BlockState blockState) {
        block31: {
            block30: {
                BlockState blockState2;
                BlockState blockState3;
                AnchorPrediction$Prediction anchorPrediction$Prediction;
                block28: {
                    boolean bl;
                    block29: {
                        block26: {
                            Object object;
                            block27: {
                                Object object2;
                                block24: {
                                    block25: {
                                        block23: {
                                            bl = ProjectileOptimizer.a;
                                            if (PREDICTIONS.isEmpty() || clientWorld == null) break block23;
                                            if (blockPos == null) break block23;
                                            object2 = blockState;
                                            if (bl) break block24;
                                            if (object2 != null) break block25;
                                        }
                                        return;
                                    }
                                    object2 = PREDICTIONS.get(blockPos);
                                }
                                anchorPrediction$Prediction = (AnchorPrediction$Prediction)object2;
                                if (anchorPrediction$Prediction == null) {
                                    return;
                                }
                                object = blockState;
                                if (bl) break block26;
                                if (object.method_26215()) break block27;
                                blockState3 = blockState;
                                blockState2 = anchorPrediction$Prediction.after;
                                if (bl) break block28;
                                if (!blockState3.equals((Object)blockState2)) break block29;
                            }
                            object = PREDICTIONS.remove(blockPos);
                        }
                        AnchorPrediction.rerender(blockPos);
                        return;
                    }
                    anchorPrediction$Prediction.lastServerState = blockState;
                    if (bl) break block30;
                    blockState3 = clientWorld.method_8320(blockPos);
                    blockState2 = anchorPrediction$Prediction.after;
                }
                if (blockState3.equals((Object)blockState2)) break block31;
                clientWorld.method_8652(blockPos, anchorPrediction$Prediction.after, 0);
            }
            AnchorPrediction.rerender(blockPos);
        }
    }

    public static boolean isSuppressed(BlockPos blockPos) {
        int n;
        block13: {
            block12: {
                Object object;
                boolean bl;
                block10: {
                    block11: {
                        bl = ProjectileOptimizer.a;
                        object = PREDICTIONS;
                        if (bl) break block10;
                        if (!object.isEmpty()) break block11;
                        return false;
                    }
                    object = PREDICTIONS.get(blockPos);
                }
                AnchorPrediction$Prediction anchorPrediction$Prediction = (AnchorPrediction$Prediction)object;
                if (anchorPrediction$Prediction == null) break block12;
                n = tickCounter;
                if (bl) break block13;
                if (n - anchorPrediction$Prediction.createdTick > 40) break block12;
                n = 1;
                break block13;
            }
            n = 0;
        }
        return n != 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean hidesOutline() {
        int n;
        BlockPos blockPos;
        MinecraftClient minecraftClient;
        block32: {
            MinecraftClient minecraftClient2;
            boolean bl;
            block30: {
                block31: {
                    block28: {
                        block29: {
                            block27: {
                                block26: {
                                    OptimizersModule optimizersModule;
                                    block25: {
                                        block24: {
                                            bl = ProjectileOptimizer.a;
                                            boolean bl2 = PREDICTIONS.isEmpty();
                                            if (bl) return bl2;
                                            if (!bl2) break block24;
                                            return false;
                                        }
                                        OptimizersModule optimizersModule2 = OptimizersModule.get();
                                        optimizersModule = optimizersModule2;
                                        if (bl) break block25;
                                        if (optimizersModule == null) return false;
                                        optimizersModule = optimizersModule2;
                                    }
                                    boolean bl3 = optimizersModule.cutebowRemoveOutline();
                                    if (bl) return bl3;
                                    if (bl3) break block26;
                                    return false;
                                }
                                minecraftClient = MinecraftClient.method_1551();
                                minecraftClient2 = minecraftClient;
                                if (bl) break block27;
                                if (minecraftClient2.field_1687 == null) return false;
                                minecraftClient2 = minecraftClient;
                            }
                            if (bl) break block28;
                            if (minecraftClient2.field_1765 != null) break block29;
                            return false;
                        }
                        minecraftClient2 = minecraftClient;
                    }
                    if (bl) break block30;
                    if (minecraftClient2.field_1765.method_17783() == HitResult.Type.field_1332) break block31;
                    return false;
                }
                minecraftClient2 = minecraftClient;
            }
            blockPos = ((BlockHitResult)minecraftClient2.field_1765).method_17777();
            AnchorPrediction$Prediction anchorPrediction$Prediction = PREDICTIONS.get(blockPos);
            if (anchorPrediction$Prediction == null) return false;
            n = tickCounter;
            if (bl) return n != 0;
            if (n - anchorPrediction$Prediction.createdTick <= 40) break block32;
            return false;
        }
        n = minecraftClient.field_1687.method_8320(blockPos).method_27852(Blocks.field_10499) ? 1 : 0;
        return n != 0;
    }

    public static void tick() {
        Map<BlockPos, AnchorPrediction$Prediction> map;
        ClientWorld clientWorld;
        boolean bl;
        block36: {
            boolean bl2;
            block35: {
                block34: {
                    MinecraftClient minecraftClient;
                    block33: {
                        MinecraftClient minecraftClient2;
                        block32: {
                            block41: {
                                block30: {
                                    block31: {
                                        minecraftClient2 = MinecraftClient.method_1551();
                                        bl = ProjectileOptimizer.a;
                                        clientWorld = minecraftClient2.field_1687;
                                        if (bl) break block30;
                                        if (clientWorld == lastWorld.get()) break block31;
                                        PREDICTIONS.clear();
                                        lastWorld = new WeakReference<ClientWorld>(clientWorld);
                                        return;
                                    }
                                    ++tickCounter;
                                    if (bl) break block41;
                                }
                                if (clientWorld != null) break block32;
                                PREDICTIONS.clear();
                            }
                            return;
                        }
                        minecraftClient = minecraftClient2;
                        if (bl) break block33;
                        if (minecraftClient.field_1724 == null) break block34;
                        minecraftClient = minecraftClient2;
                    }
                    bl2 = minecraftClient.field_1724.method_7325();
                    if (bl) break block35;
                    if (!bl2) break block34;
                    AnchorPrediction.restoreAll();
                    return;
                }
                map = PREDICTIONS;
                if (bl) break block36;
                bl2 = map.isEmpty();
            }
            if (bl2) {
                return;
            }
            map = PREDICTIONS;
        }
        Iterator<Map.Entry<BlockPos, AnchorPrediction$Prediction>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            int n;
            AnchorPrediction$Prediction anchorPrediction$Prediction;
            block39: {
                block40: {
                    block37: {
                        block38: {
                            anchorPrediction$Prediction = iterator.next().getValue();
                            if (bl) break block37;
                            if (anchorPrediction$Prediction.pos == null) break block38;
                            n = clientWorld.method_22340(anchorPrediction$Prediction.pos) ? 1 : 0;
                            if (bl) break block39;
                            if (n != 0) break block40;
                        }
                        iterator.remove();
                    }
                    if (!bl) continue;
                }
                n = tickCounter - anchorPrediction$Prediction.createdTick;
            }
            if (n > 40) {
                iterator.remove();
                AnchorPrediction.restore(clientWorld, anchorPrediction$Prediction);
                AnchorPrediction.rerender(anchorPrediction$Prediction.pos);
            }
            if (!bl) continue;
        }
    }

    public static void restoreAll() {
        ClientWorld clientWorld = MinecraftClient.method_1551().field_1687;
        ArrayList<AnchorPrediction$Prediction> arrayList = new ArrayList<AnchorPrediction$Prediction>(PREDICTIONS.values());
        PREDICTIONS.clear();
        boolean bl = ProjectileOptimizer.a;
        if (clientWorld == null) {
            return;
        }
        for (AnchorPrediction$Prediction anchorPrediction$Prediction : arrayList) {
            block9: {
                AnchorPrediction$Prediction anchorPrediction$Prediction2;
                block8: {
                    AnchorPrediction.restore(clientWorld, anchorPrediction$Prediction);
                    anchorPrediction$Prediction2 = anchorPrediction$Prediction;
                    if (bl) break block8;
                    if (anchorPrediction$Prediction2.pos == null) break block9;
                    anchorPrediction$Prediction2 = anchorPrediction$Prediction;
                }
                AnchorPrediction.rerender(anchorPrediction$Prediction2.pos);
            }
            if (!bl) continue;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void restore(ClientWorld clientWorld, AnchorPrediction$Prediction anchorPrediction$Prediction) {
        BlockState blockState;
        BlockState blockState2;
        BlockState blockState3;
        boolean bl;
        block30: {
            BlockState blockState4;
            block28: {
                block29: {
                    BlockState blockState5;
                    block27: {
                        AnchorPrediction$Prediction anchorPrediction$Prediction2;
                        block25: {
                            block26: {
                                AnchorPrediction$Prediction anchorPrediction$Prediction3;
                                block24: {
                                    bl = ProjectileOptimizer.a;
                                    if (clientWorld == null) return;
                                    anchorPrediction$Prediction3 = anchorPrediction$Prediction;
                                    if (bl) break block24;
                                    if (anchorPrediction$Prediction3 == null) return;
                                    anchorPrediction$Prediction3 = anchorPrediction$Prediction;
                                }
                                if (anchorPrediction$Prediction3.pos == null) {
                                    return;
                                }
                                if (!clientWorld.method_22340(anchorPrediction$Prediction.pos)) {
                                    return;
                                }
                                anchorPrediction$Prediction2 = anchorPrediction$Prediction;
                                if (bl) break block25;
                                if (anchorPrediction$Prediction2.lastServerState == null) break block26;
                                blockState5 = anchorPrediction$Prediction.lastServerState;
                                break block27;
                            }
                            anchorPrediction$Prediction2 = anchorPrediction$Prediction;
                        }
                        blockState5 = anchorPrediction$Prediction2.before;
                    }
                    blockState3 = blockState5;
                    blockState4 = blockState3;
                    if (bl) break block28;
                    if (blockState4 != null) break block29;
                    return;
                }
                blockState4 = clientWorld.method_8320(anchorPrediction$Prediction.pos);
            }
            BlockState blockState6 = blockState4;
            blockState2 = blockState6;
            blockState = anchorPrediction$Prediction.after;
            if (bl) break block30;
            if (!blockState2.equals((Object)blockState)) return;
            blockState2 = blockState6;
            blockState = blockState3;
        }
        boolean bl2 = blockState2.equals((Object)blockState);
        if (bl || bl2) return;
        bl2 = clientWorld.method_8652(anchorPrediction$Prediction.pos, blockState3, 0);
        return;
    }

    private static void rerender(BlockPos blockPos) {
        block4: {
            WorldRenderer worldRenderer;
            block5: {
                boolean bl = ProjectileOptimizer.a;
                worldRenderer = MinecraftClient.method_1551().field_1769;
                if (bl) break block4;
                if (worldRenderer != null) break block5;
                return;
            }
            worldRenderer.method_18146(blockPos.method_10263(), blockPos.method_10264(), blockPos.method_10260(), blockPos.method_10263(), blockPos.method_10264(), blockPos.method_10260());
        }
    }

    
    static {
        long l = 3609820015880900501L;
        long l2 = 0xCE83DFECC5AD0FBDL ^ l;
        LIFETIME = (int)l2;
        PREDICTIONS = new HashMap<BlockPos, AnchorPrediction$Prediction>();
        lastWorld = new WeakReference<Object>(null);
    }
}
