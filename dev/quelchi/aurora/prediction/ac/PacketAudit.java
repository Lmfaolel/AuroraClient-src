package dev.quelchi.aurora.prediction.ac;

import dev.quelchi.aurora.mixins.PlayerInteractEntityC2SPacketAccessor;
import dev.quelchi.aurora.prediction.ac.AntiCheat;
import dev.quelchi.aurora.prediction.ac.InputAudit;
import dev.quelchi.aurora.prediction.ac.MoveAudit;
import dev.quelchi.aurora.prediction.ac.PacketAudit$1;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientTickEndC2SPacket;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public final class PacketAudit {
    private static final double FLOOR_MS = -300.0;
    private static final double LIMIT_MS = 300.0;
    private static double balance;
    private static long lastMove;
    private static final int BLINK_TICKS;
    private static int ticksSinceMove;
    private static int attacksThisTick;
    private static boolean swungThisTick;
    private static String actionThisTick;
    private static int lastAttackedId;
    private static boolean lastAttackedCrystal;
    private static boolean teleportThisTick;
    private static boolean teleportLastTick;
    private static int lastSlot;
    private static PlayerInput lastInput;
    private static Entity lastInputPlayer;
    private static final String[] a;

    private PacketAudit() {
    }

    static void reset() {
        balance = 0.0;
        lastMove = 0L;
        ticksSinceMove = 0;
        PacketAudit.endTick();
        teleportLastTick = false;
        lastSlot = -1;
        lastInput = null;
        lastInputPlayer = null;
    }

    static void onServerSlot() {
        lastSlot = -1;
        InputAudit.onServerSlot();
    }

    private static void endTick() {
        int n;
        block8: {
            block9: {
                int n2 = InputAudit.a;
                n = attacksThisTick;
                if (n2 != 0) break block8;
                if (n <= 0) break block9;
                n = swungThisTick ? 1 : 0;
                if (n2 != 0) break block8;
                if (n != 0) break block9;
                AntiCheat.flag(a[9], attacksThisTick + a[22]);
            }
            attacksThisTick = 0;
            swungThisTick = false;
            actionThisTick = null;
            lastAttackedId = -1;
            lastAttackedCrystal = false;
            InputAudit.endTick();
            teleportLastTick = teleportThisTick;
            n = 0;
        }
        teleportThisTick = n;
    }

    public static void onTeleport() {
        teleportThisTick = true;
    }

    
    static void onSent(Packet<?> packet) {
        Entity entity;
        Entity entity2;
        MinecraftClient minecraftClient;
        block142: {
            int n;
            block140: {
                int n2;
                block141: {
                    Packet<?> packet2;
                    int n3;
                    block128: {
                        block129: {
                            int n4;
                            block138: {
                                int n5;
                                block139: {
                                    block137: {
                                        block136: {
                                            block134: {
                                                block135: {
                                                    block132: {
                                                        block133: {
                                                            block131: {
                                                                int n6;
                                                                UpdateSelectedSlotC2SPacket updateSelectedSlotC2SPacket;
                                                                block130: {
                                                                    block126: {
                                                                        block127: {
                                                                            block120: {
                                                                                block121: {
                                                                                    block125: {
                                                                                        PlayerMoveC2SPacket playerMoveC2SPacket;
                                                                                        PlayerMoveC2SPacket playerMoveC2SPacket2;
                                                                                        block124: {
                                                                                            block122: {
                                                                                                long l;
                                                                                                block123: {
                                                                                                    minecraftClient = MinecraftClient.method_1551();
                                                                                                    n3 = InputAudit.a;
                                                                                                    MinecraftClient minecraftClient2 = minecraftClient;
                                                                                                    if (n3 == 0) {
                                                                                                        if (minecraftClient2.field_1724 == null) return;
                                                                                                        minecraftClient2 = minecraftClient;
                                                                                                    }
                                                                                                    if (minecraftClient2.field_1687 == null) {
                                                                                                        return;
                                                                                                    }
                                                                                                    packet2 = packet;
                                                                                                    if (n3 != 0) break block120;
                                                                                                    if (!(packet2 instanceof PlayerMoveC2SPacket)) break block121;
                                                                                                    playerMoveC2SPacket2 = (PlayerMoveC2SPacket)packet;
                                                                                                    MoveAudit.onMove(playerMoveC2SPacket2);
                                                                                                    ticksSinceMove = 0;
                                                                                                    l = System.nanoTime();
                                                                                                    if (n3 != 0) break block122;
                                                                                                    if (lastMove == 0L) break block123;
                                                                                                    balance = Math.max(-300.0, balance + 50.0 - (double)(l - lastMove) / 1000000.0);
                                                                                                    if (n3 != 0) break block122;
                                                                                                    if (balance > 300.0) {
                                                                                                        AntiCheat.flag(a[18], "+" + Math.round(balance) + a[19]);
                                                                                                        balance = 0.0;
                                                                                                    }
                                                                                                }
                                                                                                lastMove = l;
                                                                                            }
                                                                                            playerMoveC2SPacket = playerMoveC2SPacket2;
                                                                                            if (n3 != 0) break block124;
                                                                                            if (!playerMoveC2SPacket.method_36172()) break block125;
                                                                                            playerMoveC2SPacket = playerMoveC2SPacket2;
                                                                                        }
                                                                                        if (Math.abs(playerMoveC2SPacket.method_12270(0.0f)) > 90.001f) {
                                                                                            AntiCheat.flag(a[20], a[10] + String.format(a[21], Float.valueOf(playerMoveC2SPacket2.method_12270(0.0f))));
                                                                                        }
                                                                                    }
                                                                                    if (n3 == 0) return;
                                                                                }
                                                                                packet2 = packet;
                                                                            }
                                                                            if (n3 == 0) {
                                                                                if (packet2 instanceof ClientTickEndC2SPacket) {
                                                                                    PacketAudit.endTick();
                                                                                    if (n3 == 0) return;
                                                                                }
                                                                                packet2 = packet;
                                                                            }
                                                                            if (n3 == 0) {
                                                                                if (packet2 instanceof ClickSlotC2SPacket) {
                                                                                    InputAudit.onClickPacket();
                                                                                    if (n3 == 0) return;
                                                                                }
                                                                                packet2 = packet;
                                                                            }
                                                                            if (n3 != 0) break block126;
                                                                            if (!(packet2 instanceof PlayerActionC2SPacket)) break block127;
                                                                            packet2 = packet;
                                                                            if (n3 != 0) break block126;
                                                                            PlayerActionC2SPacket playerActionC2SPacket = (PlayerActionC2SPacket)packet2;
                                                                            if (playerActionC2SPacket.method_12363() == PlayerActionC2SPacket.Action.field_12969) {
                                                                                InputAudit.onOffhandSwap();
                                                                                if (n3 == 0) return;
                                                                            }
                                                                        }
                                                                        packet2 = packet;
                                                                    }
                                                                    if (n3 == 0) {
                                                                        if (packet2 instanceof HandSwingC2SPacket) {
                                                                            swungThisTick = true;
                                                                            if (n3 == 0) return;
                                                                        }
                                                                        packet2 = packet;
                                                                    }
                                                                    if (n3 != 0) break block128;
                                                                    if (!(packet2 instanceof UpdateSelectedSlotC2SPacket)) break block129;
                                                                    updateSelectedSlotC2SPacket = (UpdateSelectedSlotC2SPacket)packet;
                                                                    if (actionThisTick == null) break block130;
                                                                    n6 = teleportThisTick;
                                                                    if (n3 != 0) break block131;
                                                                    if (n6 != 0) break block130;
                                                                    n6 = teleportLastTick ? 1 : 0;
                                                                    if (n3 != 0) break block131;
                                                                    if (n6 == 0) {
                                                                        AntiCheat.flag(a[17], a[4] + actionThisTick);
                                                                    }
                                                                }
                                                                n6 = n5 = updateSelectedSlotC2SPacket.method_12442();
                                                            }
                                                            if (n3 != 0) break block132;
                                                            if (n5 < 0) break block133;
                                                            n4 = n5;
                                                            if (n3 != 0) break block134;
                                                            if (n4 <= 8) break block135;
                                                        }
                                                        AntiCheat.flag(a[8], a[14] + n5);
                                                    }
                                                    if (n3 == 0) break block136;
                                                }
                                                n4 = n5;
                                            }
                                            if (n3 != 0) break block137;
                                            if (n4 == lastSlot) {
                                                AntiCheat.flag(a[7], a[23] + n5);
                                            }
                                        }
                                        n4 = n5;
                                    }
                                    if (n3 != 0) break block138;
                                    if (n4 < 0) break block139;
                                    n4 = n5;
                                    if (n3 != 0) break block138;
                                    if (n4 <= 8) {
                                        InputAudit.onSlotPacket(lastSlot, n5);
                                    }
                                }
                                n4 = n5;
                            }
                            lastSlot = n4;
                            if (n3 == 0) return;
                        }
                        packet2 = packet;
                    }
                    if (n3 == 0) {
                        if (packet2 instanceof PlayerInputC2SPacket) {
                            PlayerInputC2SPacket playerInputC2SPacket = (PlayerInputC2SPacket)packet;
                            if (minecraftClient.field_1724 != lastInputPlayer) {
                                lastInput = null;
                            }
                            PlayerInputC2SPacket playerInputC2SPacket2 = playerInputC2SPacket;
                            if (n3 == 0) {
                                if (playerInputC2SPacket2.comp_3139().equals((Object)lastInput)) {
                                    AntiCheat.flag(a[13], a[0]);
                                }
                                playerInputC2SPacket2 = playerInputC2SPacket;
                            }
                            lastInput = playerInputC2SPacket2.comp_3139();
                            lastInputPlayer = minecraftClient.field_1724;
                            if (n3 == 0) return;
                        }
                        packet2 = packet;
                    }
                    if (n3 == 0) {
                        if (packet2 instanceof PlayerInteractBlockC2SPacket) {
                            PlayerInteractBlockC2SPacket playerInteractBlockC2SPacket = (PlayerInteractBlockC2SPacket)packet;
                            if (n3 == 0) {
                                if (!InputAudit.replaying()) {
                                    actionThisTick = a[6];
                                }
                                InputAudit.onBlockInteract(playerInteractBlockC2SPacket);
                            }
                            if (n3 == 0) return;
                        }
                        packet2 = packet;
                    }
                    if (n3 == 0) {
                        if (packet2 instanceof PlayerInteractItemC2SPacket) {
                            PlayerInteractItemC2SPacket playerInteractItemC2SPacket = (PlayerInteractItemC2SPacket)packet;
                            InputAudit.onItemUse(playerInteractItemC2SPacket);
                            if (n3 == 0) return;
                        }
                        packet2 = packet;
                    }
                    if (n3 == 0) {
                        if (!(packet2 instanceof PlayerInteractEntityC2SPacket)) return;
                        packet2 = packet;
                    }
                    PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket = (PlayerInteractEntityC2SPacket)packet2;
                    boolean[] blArray = new boolean[]{false};
                    playerInteractEntityC2SPacket.method_34209((PlayerInteractEntityC2SPacket.Handler)new PacketAudit$1(blArray));
                    if (n3 == 0) {
                        if (!blArray[0]) {
                            actionThisTick = a[12];
                            InputAudit.onEntityInteract();
                            return;
                        }
                        String[] stringArray = a;
                        actionThisTick = stringArray[16];
                    }
                    int n7 = minecraftClient.field_1724.method_7325();
                    if (n3 == 0) {
                        if (n7 == 0) {
                            ++attacksThisTick;
                        }
                        n7 = ((PlayerInteractEntityC2SPacketAccessor)playerInteractEntityC2SPacket).aurora$entityId();
                    }
                    n2 = n7;
                    entity2 = minecraftClient.field_1687.method_8469(n2);
                    n = lastAttackedId;
                    if (n3 != 0) break block140;
                    if (!PacketAudit.twoTargets(n, lastAttackedCrystal, n2)) break block141;
                    entity = minecraftClient.method_1560();
                    if (n3 != 0) break block142;
                    if (entity != minecraftClient.field_1724) break block141;
                    entity = minecraftClient.field_1724;
                    if (n3 != 0) break block142;
                    if (!entity.method_7325()) {
                        AntiCheat.flag(a[15], attacksThisTick + a[1]);
                    }
                }
                lastAttackedId = n2;
                n = entity2 instanceof EndCrystalEntity;
            }
            lastAttackedCrystal = n;
            InputAudit.onAttack(entity2);
            entity = entity2;
        }
        if (entity == null) {
            return;
        }
        ClientPlayerEntity clientPlayerEntity = minecraftClient.field_1724;
        double d = PacketAudit.reach(clientPlayerEntity.method_33571(), clientPlayerEntity.method_5836(0.0f), entity2.method_5829());
        if (!(d > minecraftClient.field_1724.method_55755() + 0.3)) return;
        AntiCheat.flag(a[5], String.format(a[2], d));
    }

    
    static void tick(MinecraftClient var0) {
        block26: {
            block22: {
                block23: {
                    block25: {
                        block24: {
                            var1_1 = InputAudit.a;
                            var2_2 = var0.method_1562();
                            if (var1_1 != 0) break block22;
                            if (var0.field_1724.method_5805()) {
                            }
                            v1 = (int)var0.field_1724.method_5765();
                            if (var1_1 != 0) break block23;
                            if (v1 == 0) {
                            }
                            v4 = var2_2;
                            if (var1_1 != 0) break block24;
                            if (v4 != null) {
                            }
                            v4 = var2_2;
                        }
                        v1 = (int)v4.method_76760();
                        if (var1_1 != 0) break block23;
                        if (v1 == 0) break block25;
                        if (var0.method_1560() == var0.field_1724) break block26;
                    }
                    v1 = 0;
                }
                PacketAudit.ticksSinceMove = v1;
            }
            return;
        }
        if (++PacketAudit.ticksSinceMove == 60) {
            AntiCheat.flag(PacketAudit.a[3], PacketAudit.a[11]);
        }
    }

    static boolean twoTargets(int n, boolean bl, int n2) {
        int n3;
        block13: {
            block11: {
                int n4;
                block12: {
                    block10: {
                        n4 = InputAudit.a;
                        n3 = n;
                        if (n4 != 0) break block10;
                        if (n3 == -1) break block11;
                        n3 = n2;
                    }
                    if (n4 != 0) break block12;
                    if (n3 == n) break block11;
                    n3 = bl ? 1 : 0;
                }
                if (n4 != 0) break block13;
                if (n3 != 0) break block11;
                n3 = 1;
                break block13;
            }
            n3 = 0;
        }
        return n3 != 0;
    }

    static double reach(Vec3d vec3d, Vec3d vec3d2, Box box) {
        return Math.min(PacketAudit.distance(vec3d, box), PacketAudit.distance(vec3d2, box));
    }

    private static double distance(Vec3d vec3d, Box box) {
        double d = Math.max(box.field_1323, Math.min(vec3d.field_1352, box.field_1320));
        double d2 = Math.max(box.field_1322, Math.min(vec3d.field_1351, box.field_1325));
        double d3 = Math.max(box.field_1321, Math.min(vec3d.field_1350, box.field_1324));
        return vec3d.method_1022(new Vec3d(d, d2, d3));
    }

    
    static {
        var9 = new String[24];
        var7_1 = 0;
        var6_2 = "ByVg7f_Ri\u0006b0uKR\u0014\u0006xGy9`JU,Oe~jPC,Rb=n\u000b\u0003\"\u0014m~gRIoMx\u0005d`Oe5\u0012U`I\u007f~fVGbAn~dXRiT+\u0005tiGh6\u0005V`Gh;\tv~I\u007f1fQJN\tv~I\u007f1fQJN\u0007hcu|7kY\u0006VeRh6%\u0018Hc\u0006{1vWReIe~cQT,\u0010;~qWEgU\bObRn,d]R\tv~I\u007f1fQJI\u0005U`I\u007f~\tv~I\u007f1fQJH\u0006GxRj=n\tv~I\u007f1fQJM\u0005reKn,\u0002K\u007f\tv~I\u007f1fQJJ\u0004\u0003\"\u0017m";
        var8_3 = "ByVg7f_Ri\u0006b0uKR\u0014\u0006xGy9`JU,Oe~jPC,Rb=n\u000b\u0003\"\u0014m~gRIoMx\u0005d`Oe5\u0012U`I\u007f~fVGbAn~dXRiT+\u0005tiGh6\u0005V`Gh;\tv~I\u007f1fQJN\tv~I\u007f1fQJN\u0007hcu|7kY\u0006VeRh6%\u0018Hc\u0006{1vWReIe~cQT,\u0010;~qWEgU\bObRn,d]R\tv~I\u007f1fQJI\u0005U`I\u007f~\tv~I\u007f1fQJH\u0006GxRj=n\tv~I\u007f1fQJM\u0005reKn,\u0002K\u007f\tv~I\u007f1fQJJ\u0004\u0003\"\u0017m".length();
        var5_4 = 15;
        var4_5 = -1;
        block11: while (true) {
            v0 = ++var4_5;
            v1 = var6_2.substring(v0, v0 + var5_4);
            v2 = -1;
            block12: while (true) {
                var6_2 = "\u0006dO\u007fvv\u0017\nUmKn~vRIx\u0006";
                var8_3 = "\u0006dO\u007fvv\u0017\nUmKn~vRIx\u0006".length();
                var5_4 = 7;
                var4_5 = -1;
                while (true) {
                    v3 = ++var4_5;
                    v1 = var6_2.substring(v3, v3 + var5_4);
                    v2 = 0;
lbl21:

                    v4 = v1.toCharArray();
                    v5 = v4;
                    v6 = v4.length;
                    var10_6 = 0;
                    do {
                        v5 = v5;
                        v7 = var10_6;
                        v8 = v5[v7];
                        switch (var10_6 % 7) {
                            case 0: {
                                v9 = 38;
                                break;
                            }
                            case 1: {
                                v9 = 12;
                                break;
                            }
                            case 2: {
                                v9 = 38;
                                break;
                            }
                            case 3: {
                                v9 = 11;
                                break;
                            }
                            case 4: {
                                v9 = 94;
                                break;
                            }
                            case 5: {
                                v9 = 5;
                                break;
                            }
                            default: {
                                v9 = 62;
                            }
                        }
                        v5[v7] = (char)(v8 ^ v9);
                        ++var10_6;
lbl53:

                        v6 = v6;
                    } while (v6 > var10_6);
                    v10 = new String(v5);
                    switch (v2) {
                        default: {
                            var9[var7_1++] = v10.intern();
                            if ((var4_5 += var5_4) >= var8_3) continue block12;
                            var5_4 = var6_2.charAt(var4_5);
                            continue block11;
                        }
                        case 0: 
                    }
                    var9[var7_1++] = v10.intern();
                    if ((var4_5 += var5_4) >= var8_3) {
                        PacketAudit.a = var9;
                        var2_7 = 3526140865370922783L;
                        var0_8 = 8524283127649804067L ^ var2_7;
                        PacketAudit.BLINK_TICKS = (int)var0_8;
                        PacketAudit.lastAttackedId = -1;
                        PacketAudit.lastSlot = -1;
                        return;
                    }
                    var5_4 = var6_2.charAt(var4_5);
                }
                break;
            }
            break;
        }
    }
}
