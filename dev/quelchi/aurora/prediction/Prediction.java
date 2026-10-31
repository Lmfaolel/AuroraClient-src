package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.mixins.ClientPlayerInteractionManagerInvoker;
import dev.quelchi.aurora.optimize.AnchorPrediction;
import dev.quelchi.aurora.optimize.Blast;
import dev.quelchi.aurora.optimize.CrystalOptimizer;
import dev.quelchi.aurora.optimize.CrystalPrediction;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import dev.quelchi.aurora.prediction.AnchorTickHold;
import dev.quelchi.aurora.prediction.HolePredictor;
import dev.quelchi.aurora.prediction.InventoryHold;
import dev.quelchi.aurora.prediction.OffhandSwap;
import dev.quelchi.aurora.prediction.PearlPlaceHold;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import dev.quelchi.aurora.prediction.SelfDamagePrediction;
import dev.quelchi.aurora.prediction.ac.MoveAudit;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public final class Prediction {
    private static BlockPos pendingAnchor;
    private static final double PLAYER_MARGIN = 0.3;
    private static final long BUFFER_MS;
    private static final Map<Long, ArrayDeque<Long>> BUFFERED_BREAKS;
    private static final long PEARL_ECHO_MS;
    private static Vec3d pearlTarget;
    private static long pearlTargetUntil;
    private static final long PEARL_VELOCITY_MUTE_MS;
    private static long pearlVelocityMuteUntil;
    private static final long[] a;
    private static final Long[] b;

    private Prediction() {
    }

    public static boolean active() {
        return PredictionHandshake.isActive();
    }

    public static void register() {
        ClientTickEvents.START_CLIENT_TICK.register(AnchorTickHold::tick);
        ClientTickEvents.END_CLIENT_TICK.register(Prediction::lambda$register$0);
    }

    public static boolean otherPlayerTouching(World world, Box box, PlayerEntity playerEntity) {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = PredictionHandshake.a;
                bl = world.method_8390(PlayerEntity.class, box.method_1014(0.3), arg_0 -> Prediction.lambda$otherPlayerTouching$1(playerEntity, arg_0)).isEmpty();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onAttack(Entity entity) {
        boolean bl;
        float f;
        BlockPos blockPos;
        BlockPos blockPos2;
        block32: {
            block33: {
                EndCrystalEntity endCrystalEntity;
                MinecraftClient minecraftClient;
                EndCrystalEntity endCrystalEntity2;
                boolean bl2;
                block31: {
                    boolean bl3;
                    block29: {
                        block30: {
                            MinecraftClient minecraftClient2;
                            block27: {
                                block28: {
                                    block26: {
                                        Entity entity2;
                                        block25: {
                                            boolean bl4;
                                            block24: {
                                                bl2 = PredictionHandshake.a;
                                                bl4 = Prediction.active();
                                                if (bl2) break block24;
                                                if (!bl4) return;
                                                entity2 = entity;
                                                if (bl2) break block25;
                                                bl4 = entity2 instanceof EndCrystalEntity;
                                            }
                                            if (!bl4) return;
                                            entity2 = entity;
                                        }
                                        endCrystalEntity2 = (EndCrystalEntity)entity2;
                                        if (endCrystalEntity2.method_31481()) {
                                            return;
                                        }
                                        minecraftClient = MinecraftClient.method_1551();
                                        minecraftClient2 = minecraftClient;
                                        if (bl2) break block26;
                                        if (minecraftClient2.field_1724 == null) return;
                                        minecraftClient2 = minecraftClient;
                                    }
                                    if (bl2) break block27;
                                    if (minecraftClient2.field_1687 != null) break block28;
                                    return;
                                }
                                minecraftClient2 = minecraftClient;
                            }
                            bl3 = CrystalOptimizer.breaks((LivingEntity)minecraftClient2.field_1724);
                            if (bl2) break block29;
                            if (bl3) break block30;
                            return;
                        }
                        endCrystalEntity = endCrystalEntity2;
                        if (bl2) break block31;
                        bl3 = CrystalPrediction.isLocal((Entity)endCrystalEntity);
                    }
                    if (bl3) {
                        blockPos2 = endCrystalEntity2.method_24515();
                        PredictionHandshake.sendBreakAt(blockPos2);
                        BUFFERED_BREAKS.computeIfAbsent(blockPos2.method_10063(), Prediction::lambda$onAttack$2).addLast(Util.method_658() + Prediction.a(14096, 2094051244837094939L));
                    }
                    endCrystalEntity = endCrystalEntity2;
                }
                blockPos2 = endCrystalEntity.method_73189();
                Prediction.detonateLocally(minecraftClient, endCrystalEntity2);
                Blast.playLocalWithParticle(minecraftClient.field_1687, (Vec3d)blockPos2);
                blockPos = blockPos2;
                f = 6.0f;
                bl = CrystalPrediction.isLocal((Entity)endCrystalEntity2);
                if (bl2) break block32;
                if (bl) break block33;
                bl = true;
                break block32;
            }
            bl = false;
        }
        SelfDamagePrediction.onLocalExplosion((Vec3d)blockPos, f, bl);
        HolePredictor.predict((Vec3d)blockPos2, 6.0f, false, null);
    }

    private static void expireBufferedBreaks() {
        if (BUFFERED_BREAKS.isEmpty()) {
            return;
        }
        long l = Util.method_658();
        BUFFERED_BREAKS.values().forEach(arg_0 -> Prediction.lambda$expireBufferedBreaks$4(l, arg_0));
        BUFFERED_BREAKS.values().removeIf(ArrayDeque::isEmpty);
    }

    public static void onRefused(BlockPos blockPos) {
        block8: {
            block9: {
                boolean bl = PredictionHandshake.a;
                ArrayDeque<Long> arrayDeque = BUFFERED_BREAKS.get(blockPos.method_10063());
                if (bl) break block8;
                if (arrayDeque == null) break block9;
                arrayDeque.pollFirst();
                if (bl) break block8;
                if (!arrayDeque.isEmpty()) break block9;
                BUFFERED_BREAKS.remove(blockPos.method_10063());
            }
            HolePredictor.refuse(blockPos);
        }
    }

    public static void onCrystalLoaded(EndCrystalEntity endCrystalEntity) {
        block24: {
            Long l;
            block23: {
                ArrayDeque<Long> arrayDeque;
                long l2;
                long l3;
                block22: {
                    boolean bl = PredictionHandshake.a;
                    if (BUFFERED_BREAKS.isEmpty()) {
                        return;
                    }
                    l3 = endCrystalEntity.method_24515().method_10063();
                    ArrayDeque<Long> arrayDeque2 = BUFFERED_BREAKS.get(l3);
                    if (arrayDeque2 == null) {
                        return;
                    }
                    long l4 = Util.method_658();
                    while (true) {
                        l = arrayDeque2.pollFirst();
                        if (l == null) break;
                        long l5 = l4 - l;
                        l2 = l5 == 0L ? 0 : (l5 < 0L ? -1 : 1);
                        if (bl) break block22;
                        if (bl) break block22;
                        if (l2 <= 0) break;
                    }
                    arrayDeque = arrayDeque2;
                    if (bl) break block23;
                    l2 = (long)arrayDeque.isEmpty();
                }
                if (l2 != false) {
                    arrayDeque = BUFFERED_BREAKS.remove(l3);
                }
            }
            if (l != null && Prediction.active()) break block24;
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        minecraftClient.method_63588(() -> Prediction.lambda$onCrystalLoaded$5(endCrystalEntity, minecraftClient));
    }

    private static void detonateLocally(MinecraftClient minecraftClient, EndCrystalEntity endCrystalEntity) {
        block5: {
            MinecraftClient minecraftClient2;
            block4: {
                endCrystalEntity.method_5650(Entity.RemovalReason.field_26998);
                boolean bl = PredictionHandshake.a;
                endCrystalEntity.method_32876((RegistryEntry)GameEvent.field_37676);
                minecraftClient2 = minecraftClient;
                if (bl) break block4;
                if (minecraftClient2.field_1692 != endCrystalEntity) break block5;
                minecraftClient2 = minecraftClient;
            }
            minecraftClient2.field_1773.method_3190(1.0f);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onLocalPearlLanded(Vec3d vec3d) {
        MinecraftClient minecraftClient;
        block20: {
            ClientPlayerEntity clientPlayerEntity;
            MinecraftClient minecraftClient2;
            block19: {
                MinecraftClient minecraftClient3;
                boolean bl;
                block18: {
                    block17: {
                        bl = PredictionHandshake.a;
                        if (Prediction.active() && vec3d != null) break block17;
                        return;
                    }
                    minecraftClient2 = MinecraftClient.method_1551();
                    minecraftClient3 = minecraftClient2;
                    if (bl) break block18;
                    if (minecraftClient3.field_1724 == null) return;
                    minecraftClient3 = minecraftClient2;
                }
                clientPlayerEntity = minecraftClient3.field_1724;
                if (bl) break block19;
                if (clientPlayerEntity.method_7325()) return;
                minecraftClient = minecraftClient2;
                if (bl) break block20;
                clientPlayerEntity = minecraftClient.field_1724;
            }
            if (!clientPlayerEntity.method_5805()) {
                return;
            }
            PredictionHandshake.sendPearlLanded(vec3d);
            ProjectileOptimizer.onLocalTeleport(minecraftClient2.field_1724.method_73189(), vec3d);
            minecraftClient2.field_1724.method_29495(vec3d);
            MoveAudit.onOwnTeleport();
            minecraftClient = minecraftClient2;
        }
        minecraftClient.field_1724.method_38785();
        PearlPlaceHold.arm();
        pearlTarget = vec3d;
        pearlTargetUntil = Util.method_658() + Prediction.a(19202, 2847283860487944717L);
    }

    public static boolean isOwnPearlTeleport(PlayerPositionLookS2CPacket playerPositionLookS2CPacket) {
        boolean bl;
        block25: {
            block26: {
                boolean bl2;
                block23: {
                    block24: {
                        boolean bl3;
                        block21: {
                            block22: {
                                block20: {
                                    int n;
                                    block19: {
                                        block18: {
                                            bl2 = PredictionHandshake.a;
                                            if (pearlTarget == null) break block18;
                                            long l = Util.method_658() - pearlTargetUntil;
                                            n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                                            if (bl2) break block19;
                                            if (n <= 0) break block20;
                                        }
                                        n = 0;
                                    }
                                    return n != 0;
                                }
                                Set set = playerPositionLookS2CPacket.comp_3229();
                                bl3 = set.contains(PositionFlag.field_12400);
                                if (bl2) break block21;
                                if (bl3) break block22;
                                bl3 = set.contains(PositionFlag.field_12398);
                                if (bl2) break block21;
                                if (bl3) break block22;
                                bl = set.contains(PositionFlag.field_12403);
                                if (bl2) break block23;
                                if (!bl) break block24;
                            }
                            bl3 = false;
                        }
                        return bl3;
                    }
                    double d = playerPositionLookS2CPacket.comp_3228().comp_3148().method_1025(pearlTarget) - 0.01;
                    bl = d == 0.0 ? 0 : (d > 0.0 ? 1 : -1);
                }
                if (bl2) break block25;
                if (bl <= false) break block26;
                return false;
            }
            pearlTarget = null;
            pearlVelocityMuteUntil = Util.method_658() + Prediction.a(23713, 3235004673206857131L);
            bl = true;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onServerCorrection(PlayerPositionLookS2CPacket playerPositionLookS2CPacket) {
        PositionFlag positionFlag;
        Set set;
        block16: {
            Set set2;
            block15: {
                PlayerPositionLookS2CPacket playerPositionLookS2CPacket2;
                boolean bl;
                block13: {
                    block14: {
                        bl = PredictionHandshake.a;
                        if (!Prediction.active()) return;
                        playerPositionLookS2CPacket2 = playerPositionLookS2CPacket;
                        if (bl) break block13;
                        if (playerPositionLookS2CPacket2 != null) break block14;
                        return;
                    }
                    playerPositionLookS2CPacket2 = playerPositionLookS2CPacket;
                }
                set2 = playerPositionLookS2CPacket2.comp_3229();
                set = set2;
                positionFlag = PositionFlag.field_12400;
                if (bl) break block15;
                if (set.contains(positionFlag)) return;
                set = set2;
                positionFlag = PositionFlag.field_12398;
                if (bl) break block16;
            }
            if (set.contains(positionFlag)) return;
            set = set2;
            positionFlag = PositionFlag.field_12403;
        }
        if (set.contains(positionFlag)) {
            return;
        }
        HolePredictor.reconcileTo(playerPositionLookS2CPacket.comp_3228().comp_3148());
    }

    public static boolean swallowsPearlDamageVelocity(EntityVelocityUpdateS2CPacket entityVelocityUpdateS2CPacket) {
        int n;
        block16: {
            block17: {
                block15: {
                    boolean bl;
                    block14: {
                        long l;
                        block12: {
                            block13: {
                                bl = PredictionHandshake.a;
                                long l2 = pearlVelocityMuteUntil - 0L;
                                l = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                                if (bl) break block12;
                                if (l == false) break block13;
                                long l3 = Util.method_658() - pearlVelocityMuteUntil;
                                l = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                                if (bl) break block12;
                                if (l <= 0) break block14;
                            }
                            l = 0;
                        }
                        return (boolean)l;
                    }
                    MinecraftClient minecraftClient = MinecraftClient.method_1551();
                    if (minecraftClient.field_1724 == null) break block15;
                    n = entityVelocityUpdateS2CPacket.method_11818();
                    if (bl) break block16;
                    if (n == minecraftClient.field_1724.method_5628()) break block17;
                }
                return false;
            }
            pearlVelocityMuteUntil = 0L;
            n = 1;
        }
        return n != 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void syncSelectedSlot() {
        MinecraftClient minecraftClient;
        block12: {
            MinecraftClient minecraftClient2;
            block13: {
                boolean bl;
                block11: {
                    bl = PredictionHandshake.a;
                    if (!Prediction.active()) {
                        return;
                    }
                    minecraftClient2 = MinecraftClient.method_1551();
                    minecraftClient = minecraftClient2;
                    if (bl) break block11;
                    if (minecraftClient.field_1724 == null) return;
                    minecraftClient = minecraftClient2;
                }
                if (bl) break block12;
                if (minecraftClient.field_1761 != null) break block13;
                return;
            }
            minecraftClient = minecraftClient2;
        }
        ((ClientPlayerInteractionManagerInvoker)minecraftClient.field_1761).aurora$syncSelectedSlot();
    }

    public static void onInteractHead(PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
        BlockPos blockPos;
        block67: {
            int n;
            block65: {
                int n2;
                block66: {
                    int n3;
                    boolean bl;
                    block63: {
                        block64: {
                            block61: {
                                int n4;
                                block62: {
                                    int n5;
                                    block58: {
                                        block60: {
                                            block59: {
                                                block56: {
                                                    block57: {
                                                        block54: {
                                                            World world;
                                                            block55: {
                                                                BlockState blockState;
                                                                block52: {
                                                                    BlockState blockState2;
                                                                    block53: {
                                                                        PlayerEntity playerEntity2;
                                                                        block50: {
                                                                            block51: {
                                                                                block49: {
                                                                                    pendingAnchor = null;
                                                                                    bl = PredictionHandshake.a;
                                                                                    if (!Prediction.active() || playerEntity == null) break block49;
                                                                                    if (blockHitResult == null) break block49;
                                                                                    playerEntity2 = playerEntity;
                                                                                    if (bl) break block50;
                                                                                    if (!playerEntity2.method_7325()) break block51;
                                                                                }
                                                                                return;
                                                                            }
                                                                            playerEntity2 = playerEntity;
                                                                        }
                                                                        world = playerEntity2.method_73183();
                                                                        if (world.method_27983() == World.field_25180) {
                                                                            return;
                                                                        }
                                                                        blockPos = blockHitResult.method_17777();
                                                                        blockState2 = world.method_8320(blockPos);
                                                                        blockState = blockState2;
                                                                        if (bl) break block52;
                                                                        if (blockState.method_26204() instanceof RespawnAnchorBlock) break block53;
                                                                        return;
                                                                    }
                                                                    blockState = blockState2;
                                                                }
                                                                n4 = (Integer)blockState.method_11654((Property)RespawnAnchorBlock.field_23153);
                                                                n5 = n4;
                                                                if (bl) break block54;
                                                                if (n5 > 0) break block55;
                                                                return;
                                                            }
                                                            n5 = Prediction.otherPlayerTouching(world, new Box(blockPos), playerEntity) ? 1 : 0;
                                                        }
                                                        if (bl) break block56;
                                                        if (n5 == 0) break block57;
                                                        return;
                                                    }
                                                    n5 = playerEntity.method_6047().method_7960() ? 1 : 0;
                                                }
                                                if (bl) break block58;
                                                if (n5 == 0) break block59;
                                                n5 = playerEntity.method_6079().method_7960() ? 1 : 0;
                                                if (bl) break block58;
                                                if (n5 != 0) break block60;
                                            }
                                            n5 = 1;
                                            break block58;
                                        }
                                        n5 = 0;
                                    }
                                    int n6 = n5;
                                    n3 = playerEntity.method_21823();
                                    if (bl) break block61;
                                    if (n3 == 0) break block62;
                                    n3 = n6;
                                    if (bl) break block61;
                                    if (n3 == 0) break block62;
                                    return;
                                }
                                n3 = n4;
                            }
                            if (bl) break block63;
                            if (n3 >= 4) break block64;
                            n3 = 1;
                            break block63;
                        }
                        n3 = 0;
                    }
                    n2 = n3;
                    n = n2;
                    if (bl) break block65;
                    if (n == 0) break block66;
                    n = playerEntity.method_5998(hand).method_31574(Items.field_8801) ? 1 : 0;
                    if (bl) break block65;
                    if (n == 0) break block66;
                    return;
                }
                n = n2;
            }
            if (n == 0 || hand != Hand.field_5808) break block67;
            if (!playerEntity.method_6079().method_31574(Items.field_8801)) break block67;
            return;
        }
        pendingAnchor = blockPos.method_10062();
    }

    
    public static void onInteractReturn(ActionResult actionResult) {
        boolean bl;
        ClientWorld clientWorld;
        BlockPos blockPos;
        block16: {
            block17: {
                boolean bl2;
                block15: {
                    ActionResult actionResult2;
                    block14: {
                        blockPos = pendingAnchor;
                        bl2 = PredictionHandshake.a;
                        pendingAnchor = null;
                        if (blockPos == null) return;
                        if (!Prediction.active()) {
                            return;
                        }
                        clientWorld = MinecraftClient.method_1551().field_1687;
                        if (clientWorld == null) {
                            return;
                        }
                        actionResult2 = actionResult;
                        if (bl2) break block14;
                        if (actionResult2 == null) break block15;
                        actionResult2 = actionResult;
                    }
                    bl = actionResult2.method_23665();
                    if (bl2) break block16;
                    if (bl) break block17;
                }
                bl = AnchorPrediction.isSuppressed(blockPos);
                if (bl2) break block16;
                if (!bl) return;
            }
            bl = true;
        }
        boolean bl3 = bl;
        if (!bl3) {
            return;
        }
        Vec3d vec3d = blockPos.method_46558();
        Blast.playLocalWithParticle(clientWorld, vec3d);
        SelfDamagePrediction.onLocalExplosion(vec3d, 5.0f, true);
        HolePredictor.predict(vec3d, 5.0f, true, blockPos);
        PredictionHandshake.sendAnchorAt(blockPos);
    }

    private static void lambda$onCrystalLoaded$5(EndCrystalEntity endCrystalEntity, MinecraftClient minecraftClient) {
        if (endCrystalEntity.method_31481()) {
            return;
        }
        Prediction.detonateLocally(minecraftClient, endCrystalEntity);
    }

    private static void lambda$expireBufferedBreaks$4(long l, ArrayDeque arrayDeque) {
        arrayDeque.removeIf(arg_0 -> Prediction.lambda$expireBufferedBreaks$3(l, arg_0));
    }

    private static boolean lambda$expireBufferedBreaks$3(long l, Long l2) {
        long l3;
        block2: {
            block3: {
                boolean bl = PredictionHandshake.a;
                long l4 = l - l2;
                l3 = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                if (bl) break block2;
                if (l3 <= 0) break block3;
                l3 = 1;
                break block2;
            }
            l3 = 0;
        }
        return (boolean)l3;
    }

    private static ArrayDeque lambda$onAttack$2(Long l) {
        return new ArrayDeque();
    }

    private static boolean lambda$otherPlayerTouching$1(PlayerEntity playerEntity, PlayerEntity playerEntity2) {
        boolean bl;
        block8: {
            block7: {
                PlayerEntity playerEntity3;
                boolean bl2;
                block6: {
                    bl2 = PredictionHandshake.a;
                    playerEntity3 = playerEntity2;
                    if (bl2) break block6;
                    if (playerEntity3 == playerEntity) break block7;
                    playerEntity3 = playerEntity2;
                }
                bl = playerEntity3.method_7325();
                if (bl2) break block8;
                if (bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    private static void lambda$register$0(MinecraftClient minecraftClient) {
        block6: {
            MinecraftClient minecraftClient2;
            block7: {
                HolePredictor.tick();
                OffhandSwap.tick();
                InventoryHold.tick();
                PearlPlaceHold.tick();
                Prediction.expireBufferedBreaks();
                boolean bl = PredictionHandshake.a;
                if (!Prediction.active()) break block6;
                minecraftClient2 = minecraftClient;
                if (bl) break block7;
                if (minecraftClient2.field_1724 == null) break block6;
                minecraftClient2 = minecraftClient;
            }
            PredictionHandshake.sendMotion((PlayerEntity)minecraftClient2.field_1724);
        }
    }

    
    static {
        block8: {
            block7: {
                var0 = 2327822383367263307L;
                var6_1 = new long[6];
                var3_2 = 0;
                var4_3 = "d\u00ef\u00d6\u00e9Wm\u00bfZ\u0007\u00cd\u0083\u008a3\u00d9%\u00fexh\u00e40\u00bf\u00c5\u00e1\u00da(X\u00c1C\u00b6\u000bG\u00c4";
                var5_4 = "d\u00ef\u00d6\u00e9Wm\u00bfZ\u0007\u00cd\u0083\u008a3\u00d9%\u00fexh\u00e40\u00bf\u00c5\u00e1\u00da(X\u00c1C\u00b6\u000bG\u00c4".length();
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
                    var4_3 = "\f\u00ab\u001d\u0083\u009d\u00f69\u001a=A\u0086\u009c\u00f95\u00d1\u00b8";
                    var5_4 = "\f\u00ab\u001d\u0083\u009d\u00f69\u001a=A\u0086\u009c\u00f95\u00d1\u00b8".length();
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
        Prediction.a = var6_1;
        Prediction.b = new Long[6];
        Prediction.PEARL_ECHO_MS = Prediction.a(20903, 4945446030832476329L);
        Prediction.PEARL_VELOCITY_MUTE_MS = Prediction.a(8824, 582889993585054581L);
        Prediction.BUFFER_MS = Prediction.a(1909, 6352030609925928569L);
        Prediction.BUFFERED_BREAKS = new HashMap<Long, ArrayDeque<Long>>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x710E) & Short.MAX_VALUE;
        if (b[n2] == null) {
            Prediction.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
