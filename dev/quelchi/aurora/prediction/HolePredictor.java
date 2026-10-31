package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.prediction.BlockPos3;
import dev.quelchi.aurora.prediction.ClientWorldQuery;
import dev.quelchi.aurora.prediction.DeterministicExplosion;
import dev.quelchi.aurora.prediction.HolePredictor$Hole;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.slf4j.Logger;

public final class HolePredictor {
    private static final long UNCONFIRMED_MS;
    private static final long GRACE_MS;
    private static final double MATCH_SQ = 0.01;
    private static final List<HolePredictor$Hole> HOLES;
    private static WeakReference<ClientWorld> lastWorld;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private HolePredictor() {
    }

    
    public static void predict(Vec3d var0, float var1_1, boolean var2_2, BlockPos var3_3) {
        block15: {
            var4_4 = PredictionHandshake.a;
            if (!Prediction.active()) {
                return;
            }
            var5_5 = MinecraftClient.method_1551().field_1687;
            if (var5_5 == null) {
                return;
            }
            var6_6 = new HolePredictor$Hole(var0, Util.method_658());
            if (var3_3 != null) {
                HolePredictor.set(var5_5, var6_6, var3_3.method_10062(), Blocks.field_10124.method_9564());
            }
            var7_7 = new ClientWorldQuery(var5_5);
            var8_8 = DeterministicExplosion.destroyedBlocks(var0.field_1352, var0.field_1351, var0.field_1350, var1_1, var7_7);
            v3 /* !! */  = var2_2 != false ? DeterministicExplosion.fireBlocks(var0.field_1352, var0.field_1351, var0.field_1350, var1_1, var7_7, var8_8) : Set.of();
            var9_9 = v3 /* !! */ ;
            var10_10 = var8_8.iterator();
            while (var10_10.hasNext()) {
                v5 = var10_10.next();
                if (!var4_4) {
                    var11_11 = v5;
                    HolePredictor.set(var5_5, var6_6, HolePredictor.pos(var11_11), Blocks.field_10124.method_9564());
                    if (!var4_4) continue;
                }
            }
            var10_10 = var9_9.iterator();
            while (var10_10.hasNext()) {
                v5 = var11_11 = var10_10.next();
lbl40:

                if (!var4_4) {
                    var12_12 = HolePredictor.pos(var11_11);
                    HolePredictor.set(var5_5, var6_6, var12_12, AbstractFireBlock.method_24416((BlockView)var5_5, (BlockPos)var12_12));
                    if (!var4_4) continue;
                }
                break block15;
            }
            HolePredictor.HOLES.add(var6_6);
            Aurora.LOG.info(HolePredictor.a[4], new Object[]{Float.valueOf(var1_1), var0.field_1352, var0.field_1351, var0.field_1350, var8_8.size(), Integer.toHexString(var8_8.hashCode()), var9_9.size(), Util.method_658() % HolePredictor.a(14499, 6295983339588008353L)});
        }
    }

    private static void set(ClientWorld clientWorld, HolePredictor$Hole holePredictor$Hole, BlockPos blockPos, BlockState blockState) {
        holePredictor$Hole.originals.putIfAbsent(blockPos, clientWorld.method_8320(blockPos));
        holePredictor$Hole.predicted.put(blockPos, blockState);
        clientWorld.method_8652(blockPos, blockState, 3);
    }

    private static BlockPos pos(BlockPos3 blockPos3) {
        return new BlockPos(blockPos3.x(), blockPos3.y(), blockPos3.z());
    }

    public static void confirm(Vec3d vec3d) {
        boolean bl;
        block13: {
            bl = PredictionHandshake.a;
            if (!HOLES.isEmpty() && vec3d != null) break block13;
            return;
        }
        for (HolePredictor$Hole holePredictor$Hole : HOLES) {
            block15: {
                block16: {
                    HolePredictor$Hole holePredictor$Hole2;
                    block14: {
                        holePredictor$Hole2 = holePredictor$Hole;
                        if (bl) break block14;
                        if (holePredictor$Hole2.confirmedAt >= 0L) break block15;
                        holePredictor$Hole2 = holePredictor$Hole;
                    }
                    if (bl) break block16;
                    if (!(holePredictor$Hole2.center.method_1025(vec3d) <= 0.01)) break block15;
                    holePredictor$Hole2 = holePredictor$Hole;
                }
                holePredictor$Hole2.confirmedAt = Util.method_658();
                return;
            }
            if (!bl) continue;
        }
    }

    public static void refuse(BlockPos blockPos) {
        ClientWorld clientWorld = MinecraftClient.method_1551().field_1687;
        boolean bl = PredictionHandshake.a;
        if (clientWorld == null) {
            return;
        }
        Iterator<HolePredictor$Hole> iterator = HOLES.iterator();
        while (iterator.hasNext()) {
            HolePredictor$Hole holePredictor$Hole;
            HolePredictor$Hole holePredictor$Hole2;
            block9: {
                holePredictor$Hole2 = iterator.next();
                holePredictor$Hole = holePredictor$Hole2;
                if (bl) break block9;
                if (holePredictor$Hole.confirmedAt >= 0L) continue;
                holePredictor$Hole = holePredictor$Hole2;
            }
            if (!BlockPos.method_49638((Position)holePredictor$Hole.center).equals((Object)blockPos)) {
                continue;
            }
            iterator.remove();
            HolePredictor.restore(clientWorld, holePredictor$Hole2, true);
            return;
        }
    }

    public static void reconcileTo(Vec3d vec3d) {
        block24: {
            boolean bl;
            block23: {
                bl = PredictionHandshake.a;
                if (!HOLES.isEmpty() && vec3d != null) break block23;
                return;
            }
            ClientWorld clientWorld = MinecraftClient.method_1551().field_1687;
            if (clientWorld == null) {
                return;
            }
            int n = MathHelper.method_15357((double)(vec3d.field_1352 - 0.3));
            int n2 = MathHelper.method_15357((double)(vec3d.field_1352 + 0.3));
            int n3 = MathHelper.method_15357((double)(vec3d.field_1350 - 0.3));
            int n4 = MathHelper.method_15357((double)(vec3d.field_1350 + 0.3));
            int n5 = MathHelper.method_15357((double)vec3d.field_1351) - 1;
            int n6 = MathHelper.method_15357((double)(vec3d.field_1351 + 1.8));
            int n7 = 0;
            block16: for (HolePredictor$Hole holePredictor$Hole : HOLES) {
                long l;
                block25: {
                    if (bl) break block24;
                    long l2 = holePredictor$Hole.confirmedAt - 0L;
                    long l3 = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                    if (bl) break block25;
                    if (l3 >= 0) {
                        continue;
                    }
                    l3 = l = (long)n;
                }
                while (l <= n2) {
                    block26: {
                        if (bl) continue block16;
                        int n8 = n5;
                        while (n8 <= n6) {
                            block27: {
                                if (bl) break block26;
                                for (int i = n3; i <= n4; ++i) {
                                    if (bl) break block27;
                                    if (!HolePredictor.giveBack(clientWorld, holePredictor$Hole, new BlockPos((int)l, n8, i))) continue;
                                    ++n7;
                                    continue;
                                }
                                ++n8;
                            }
                            if (!bl) continue;
                        }
                        ++l;
                    }
                    if (!bl) continue;
                }
                if (!bl) continue;
            }
            if (n7 > 0) {
                Aurora.LOG.info(a[2], new Object[]{vec3d.field_1352, vec3d.field_1351, vec3d.field_1350, n7});
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean giveBack(ClientWorld clientWorld, HolePredictor$Hole holePredictor$Hole, BlockPos blockPos) {
        BlockPos blockPos2;
        ClientWorld clientWorld2;
        BlockState blockState;
        block20: {
            BlockState blockState2;
            BlockState blockState3;
            block19: {
                BlockState blockState4;
                boolean bl;
                block18: {
                    BlockState blockState5;
                    block16: {
                        block17: {
                            blockState3 = holePredictor$Hole.predicted.remove(blockPos);
                            bl = PredictionHandshake.a;
                            if (blockState3 == null) {
                                return false;
                            }
                            blockState5 = holePredictor$Hole.held;
                            if (bl) break block16;
                            if (!blockState5.containsKey(blockPos)) break block17;
                            blockState4 = holePredictor$Hole.held.remove(blockPos);
                            break block18;
                        }
                        blockState5 = holePredictor$Hole.originals.get(blockPos);
                    }
                    blockState4 = blockState5;
                }
                blockState = blockState4;
                blockState2 = blockState;
                if (bl) break block19;
                if (blockState2 == null) return false;
                clientWorld2 = clientWorld;
                blockPos2 = blockPos;
                if (bl) break block20;
                blockState2 = clientWorld2.method_8320(blockPos2);
            }
            if (!blockState2.equals((Object)blockState3)) {
                return false;
            }
            clientWorld2 = clientWorld;
            blockPos2 = blockPos;
        }
        clientWorld2.method_8652(blockPos2, blockState, 3);
        return true;
    }

    public static boolean serverWrite(BlockPos blockPos, BlockState blockState) {
        boolean bl;
        block28: {
            boolean bl2;
            block34: {
                block27: {
                    boolean bl3;
                    block25: {
                        block26: {
                            bl = PredictionHandshake.a;
                            bl3 = HOLES.isEmpty();
                            if (bl) break block25;
                            if (bl3) break block26;
                            if (blockPos == null) break block26;
                            if (blockState != null) break block27;
                        }
                        bl3 = false;
                    }
                    return bl3;
                }
                boolean bl4 = false;
                for (HolePredictor$Hole holePredictor$Hole : HOLES) {
                    block32: {
                        block33: {
                            BlockState blockState2;
                            block31: {
                                HolePredictor$Hole holePredictor$Hole2;
                                block29: {
                                    if (bl) break block28;
                                    holePredictor$Hole2 = holePredictor$Hole;
                                    if (bl) break block29;
                                    if (holePredictor$Hole2.confirmedAt >= 0L) {
                                        continue;
                                    }
                                    holePredictor$Hole2 = holePredictor$Hole.predicted.get(blockPos);
                                }
                                BlockState blockState3 = (BlockState)holePredictor$Hole2;
                                blockState2 = blockState3;
                                if (bl) break block31;
                                if (blockState2 == null) break block32;
                                blockState2 = blockState3;
                            }
                            if (bl) break block33;
                            if (blockState2.equals((Object)blockState)) break block32;
                            blockState2 = holePredictor$Hole.held.put(blockPos.method_10062(), blockState);
                        }
                        bl4 = true;
                    }
                    if (!bl) continue;
                }
                bl2 = bl4;
                if (bl) break block34;
                if (!bl2) break block28;
                bl2 = true;
            }
            return bl2;
        }
        for (HolePredictor$Hole holePredictor$Hole : HOLES) {
            holePredictor$Hole.predicted.remove(blockPos);
            if (!bl) continue;
        }
        return false;
    }

    public static void tick() {
        boolean bl;
        ClientWorld clientWorld;
        block22: {
            ClientWorld clientWorld2;
            block20: {
                block21: {
                    clientWorld = MinecraftClient.method_1551().field_1687;
                    bl = PredictionHandshake.a;
                    clientWorld2 = clientWorld;
                    if (bl) break block20;
                    if (clientWorld2 == lastWorld.get()) break block21;
                    HOLES.clear();
                    lastWorld = new WeakReference<ClientWorld>(clientWorld);
                    return;
                }
                clientWorld2 = clientWorld;
            }
            if (clientWorld2 != null && !HOLES.isEmpty()) break block22;
            return;
        }
        long l = Util.method_658();
        Iterator<HolePredictor$Hole> iterator = HOLES.iterator();
        while (iterator.hasNext()) {
            long l2;
            HolePredictor$Hole holePredictor$Hole;
            ClientWorld clientWorld3;
            block28: {
                block29: {
                    HolePredictor$Hole holePredictor$Hole2;
                    block26: {
                        long l3;
                        block25: {
                            HolePredictor$Hole holePredictor$Hole3;
                            block23: {
                                block24: {
                                    holePredictor$Hole2 = iterator.next();
                                    holePredictor$Hole3 = holePredictor$Hole2;
                                    if (bl) break block23;
                                    if (holePredictor$Hole3.confirmedAt >= 0L) break block24;
                                    l3 = holePredictor$Hole2.created + HolePredictor.a(6569, 570011849611122863L);
                                    break block25;
                                }
                                holePredictor$Hole3 = holePredictor$Hole2;
                            }
                            l3 = holePredictor$Hole3.confirmedAt + HolePredictor.a(31698, 4407335290590984918L);
                        }
                        long l4 = l3;
                        if (bl) break block26;
                        if (l <= l4) {
                            continue;
                        }
                        iterator.remove();
                    }
                    clientWorld3 = clientWorld;
                    holePredictor$Hole = holePredictor$Hole2;
                    long l5 = holePredictor$Hole2.confirmedAt - 0L;
                    l2 = l5 == 0L ? 0 : (l5 < 0L ? -1 : 1);
                    if (bl) break block28;
                    if (l2 >= 0) break block29;
                    l2 = 1;
                    break block28;
                }
                l2 = 0;
            }
            HolePredictor.restore(clientWorld3, holePredictor$Hole, (boolean)l2);
            if (!bl) continue;
        }
    }

    private static void restore(ClientWorld clientWorld, HolePredictor$Hole holePredictor$Hole, boolean bl) {
        block29: {
            String string;
            Object[] objectArray;
            String string2;
            Logger logger;
            StringBuilder stringBuilder;
            block40: {
                block39: {
                    int n = 0;
                    stringBuilder = new StringBuilder();
                    boolean bl2 = PredictionHandshake.a;
                    for (Map.Entry<BlockPos, BlockState> entry : holePredictor$Hole.predicted.entrySet()) {
                        block41: {
                            int n2;
                            BlockState blockState;
                            BlockPos blockPos;
                            block38: {
                                block35: {
                                    block36: {
                                        BlockState blockState2;
                                        block37: {
                                            BlockState blockState3;
                                            block34: {
                                                HolePredictor$Hole holePredictor$Hole2;
                                                block33: {
                                                    block32: {
                                                        boolean bl3;
                                                        block31: {
                                                            block30: {
                                                                if (bl2) break block29;
                                                                blockPos = entry.getKey();
                                                                bl3 = clientWorld.method_8320(blockPos).equals((Object)entry.getValue());
                                                                if (bl2) break block30;
                                                                if (!bl3) {
                                                                    continue;
                                                                }
                                                                bl3 = bl;
                                                            }
                                                            if (bl2) break block31;
                                                            if (!bl3) break block32;
                                                            holePredictor$Hole2 = holePredictor$Hole;
                                                            if (bl2) break block33;
                                                            bl3 = holePredictor$Hole2.held.containsKey(blockPos);
                                                        }
                                                        if (!bl3) break block32;
                                                        blockState3 = holePredictor$Hole.held.get(blockPos);
                                                        break block34;
                                                    }
                                                    holePredictor$Hole2 = holePredictor$Hole.originals.get(blockPos);
                                                }
                                                blockState3 = (BlockState)holePredictor$Hole2;
                                            }
                                            blockState = blockState3;
                                            if (bl2) break block35;
                                            if (bl) break block36;
                                            blockState2 = blockState;
                                            if (bl2) break block37;
                                            if (blockState2 == null) break block36;
                                            blockState2 = blockState;
                                        }
                                        n2 = blockState2.method_26204() instanceof AbstractFireBlock;
                                        if (bl2) break block38;
                                        if (n2 != 0) {
                                            continue;
                                        }
                                    }
                                    clientWorld.method_8652(blockPos, blockState, 3);
                                    if (bl2) break block41;
                                }
                                n2 = n;
                            }
                            if (n2 < 6) {
                                stringBuilder.append(' ').append(blockPos.method_23854()).append('=').append(blockState);
                            }
                            ++n;
                        }
                        if (!bl2) continue;
                    }
                    if (n <= 0) break block29;
                    logger = Aurora.LOG;
                    string2 = a[0];
                    Object[] objectArray2 = new Object[7];
                    objectArray2[0] = n;
                    objectArray2[1] = holePredictor$Hole.center.field_1352;
                    objectArray2[2] = holePredictor$Hole.center.field_1351;
                    objectArray2[3] = holePredictor$Hole.center.field_1350;
                    Object[] objectArray3 = objectArray2;
                    objectArray = objectArray2;
                    int n3 = 4;
                    if (!bl) break block39;
                    string = a[3];
                    break block40;
                }
                string = a[1];
            }
            objectArray3[n3] = string;
            objectArray[5] = Util.method_658() % HolePredictor.a(9805, 4053322607262410568L);
            objectArray[6] = stringBuilder;
            logger.info(string2, objectArray);
        }
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[5];
                        var11_1 = 0;
                        var10_2 = "P\u0005P]q\u0018Og\bPGX\u0018^n\u0012AF^]H+\u001aH\tO]@g\u0012\u0015HX\u0018WvMNT\u0000CQ+INT\u0005\u0018X6\u001aH\t_YA{\rP\u0013WE\u001bx\u0004G_IJ\fo\bQ\tBWX+\u0005PZXJCrAAAIU?P\u0005P]q\u0018Od\u0013GLOLEd\u000f\u0015]C\u0018WvMNT\u0000CQ+\u0011@]\fZMh\n\u0015RQ\u0018On\rYZ\fMBo\u0004G\tXPI+\u0011YHU]^";
                        var12_3 = "P\u0005P]q\u0018Og\bPGX\u0018^n\u0012AF^]H+\u001aH\tO]@g\u0012\u0015HX\u0018WvMNT\u0000CQ+INT\u0005\u0018X6\u001aH\t_YA{\rP\u0013WE\u001bx\u0004G_IJ\fo\bQ\tBWX+\u0005PZXJCrAAAIU?P\u0005P]q\u0018Od\u0013GLOLEd\u000f\u0015]C\u0018WvMNT\u0000CQ+\u0011@]\fZMh\n\u0015RQ\u0018On\rYZ\fMBo\u0004G\tXPI+\u0011YHU]^".length();
                        var9_4 = 62;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block25;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "x\u0004G_IJ\fe\u0004CL^\u0018Is\u0011YFH]H?P\u0005P]q\u0018Og\bPGX\u0018^6\u001aH\tO]B\u007f\u0004G\u0014WE\u0000p\u001c\u0019RQ\u0018Od\u0014[]\u0011CQ+\tTZD\u0005WvAS@^]\u0011p\u001c\u0015]\u0011CQ";
                            var12_3 = "x\u0004G_IJ\fe\u0004CL^\u0018Is\u0011YFH]H?P\u0005P]q\u0018Og\bPGX\u0018^6\u001aH\tO]B\u007f\u0004G\u0014WE\u0000p\u001c\u0019RQ\u0018Od\u0014[]\u0011CQ+\tTZD\u0005WvAS@^]\u0011p\u001c\u0015]\u0011CQ".length();
                            var9_4 = 21;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block25;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            break block26;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_6;
                        v9 = v6[v8];
                        switch (var14_6 % 7) {
                            case 0: {
                                v10 = 11;
                                break;
                            }
                            case 1: {
                                v10 = 97;
                                break;
                            }
                            case 2: {
                                v10 = 53;
                                break;
                            }
                            case 3: {
                                v10 = 41;
                                break;
                            }
                            case 4: {
                                v10 = 44;
                                break;
                            }
                            case 5: {
                                v10 = 56;
                                break;
                            }
                            default: {
                                v10 = 44;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_6;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_6);
                    v3 = new String(v6);
                    switch (v2) {
                        default: {
                            continue;
                        }
                        case 0:
lbl73:

                        continue;
                    }
                }
                HolePredictor.a = var13;
                var0_7 = 8463018308428399074L;
                var6_8 = new long[6];
                var3_9 = 0;
                var4_10 = "e3R\u00f0\u00170\u00e5\u0092r\u009b\u00b8\u0091\u00d4U\u001c\u0091M2\u00e5?\u0094`d\u00eaHX\u00aeH\u00807z\u00c0";
                var5_11 = "e3R\u00f0\u00170\u00e5\u0092r\u009b\u00b8\u0091\u00d4U\u001c\u0091M2\u00e5?\u0094`d\u00eaHX\u00aeH\u00807z\u00c0".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v11 = var6_8;
                    v12 = var3_9++;
                    v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "~\u00db+[\u0005R\u0016\u00d0\"-}-bdz\u0003";
                    var5_11 = "~\u00db+[\u0005R\u0016\u00d0\"-}-bdz\u0003".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v11 = var6_8;
                        v12 = var3_9++;
                        v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var0_7;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        HolePredictor.b = var6_8;
        HolePredictor.c = new Long[6];
        HolePredictor.GRACE_MS = HolePredictor.a(25731, 1171495142598665604L);
        HolePredictor.UNCONFIRMED_MS = HolePredictor.a(5101, 840349087853351662L);
        HolePredictor.HOLES = new ArrayList<HolePredictor$Hole>();
        HolePredictor.lastWorld = new WeakReference<Object>(null);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x107) & Short.MAX_VALUE;
        if (c[n2] == null) {
            HolePredictor.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
