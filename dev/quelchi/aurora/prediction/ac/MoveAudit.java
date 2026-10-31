package dev.quelchi.aurora.prediction.ac;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.prediction.ac.AntiCheat;
import dev.quelchi.aurora.prediction.ac.InputAudit;
import dev.quelchi.aurora.prediction.ac.MoveAudit$1;
import dev.quelchi.aurora.prediction.ac.MoveAudit$Knock;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UseEffectsComponent;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public final class MoveAudit {
    private static final long JUDGE_MS;
    private static final ConcurrentLinkedQueue<MoveAudit$Knock> KNOCKS;
    private static Vec3d lastTickVelocity;
    private static int ticksSinceKnock;
    private static int hover;
    private static int slowTicks;
    private static double slowDistance;
    private static int rising;
    private static int strafeHits;
    private static int screenTicks;
    private static int sinceBounce;
    private static int ownHits;
    private static int stabTicks;
    private static boolean wasAir;
    private static boolean lastCollided;
    private static boolean bounced;
    private static double lastDx;
    private static double lastDz;
    private static final double AIR_ACCEL = 0.026;
    private static final double PUSH = 0.05;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private MoveAudit() {
    }

    static void reset() {
        KNOCKS.clear();
        ticksSinceKnock = MoveAudit.a(16007, 4784841863493425133L);
        slowTicks = 0;
        hover = 0;
        slowDistance = 0.0;
        stabTicks = 0;
        ownHits = 0;
        sinceBounce = 0;
        screenTicks = 0;
        strafeHits = 0;
        rising = 0;
        bounced = false;
        lastCollided = false;
        wasAir = false;
    }

    public static void onServerTeleport() {
        ticksSinceKnock = 0;
        KNOCKS.clear();
    }

    
    static void onOwnAction(Packet<?> packet) {
        block10: {
            Packet<?> packet2;
            block11: {
                int n;
                block8: {
                    block9: {
                        n = InputAudit.a;
                        packet2 = packet;
                        if (n != 0) break block8;
                        if (!(packet2 instanceof PlayerActionC2SPacket)) break block9;
                        packet2 = packet;
                        if (n != 0) break block8;
                        PlayerActionC2SPacket playerActionC2SPacket = (PlayerActionC2SPacket)packet2;
                        if (playerActionC2SPacket.method_12363() != PlayerActionC2SPacket.Action.field_63165) break block9;
                        stabTicks = MoveAudit.a(20816, 1852918612997503033L);
                        if (n == 0) break block10;
                    }
                    packet2 = packet;
                }
                if (n != 0) break block11;
                if (!(packet2 instanceof PlayerInteractEntityC2SPacket)) break block10;
                packet2 = packet;
            }
            PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket = (PlayerInteractEntityC2SPacket)packet2;
            playerInteractEntityC2SPacket.method_34209((PlayerInteractEntityC2SPacket.Handler)new MoveAudit$1());
        }
    }

    public static void onKnockback(Vec3d vec3d, boolean bl) {
        KNOCKS.add(new MoveAudit$Knock(vec3d, bl, System.currentTimeMillis()));
    }

    public static void onOwnDrop(Vec3d vec3d) {
        KNOCKS.removeIf(arg_0 -> MoveAudit.lambda$onOwnDrop$0(vec3d, arg_0));
    }

    public static void onOwnTeleport() {
        ticksSinceKnock = 0;
    }

    
    static void startTick(ClientPlayerEntity var0) {
        block69: {
            block70: {
                block68: {
                    var1_1 = InputAudit.a;
                    if (MoveAudit.KNOCKS.isEmpty()) {
                        return;
                    }
                    var2_2 = var0.method_18798();
                    var3_3 = System.currentTimeMillis();
                    var5_4 = MoveAudit.KNOCKS.toArray(new MoveAudit$Knock[0]);
                    var6_5 = MoveAudit.lastTickVelocity;
                    var7_6 = 0;
                    var8_7 = 0;
                    while (var8_7 < var5_4.length) {
                        block66: {
                            block67: {
                                block65: {
                                    block63: {
                                        block64: {
                                            v1 = var5_4;
                                            v2 = var8_7;
                                            if (var1_1 != 0) break block63;
                                            v3 = v1[v2];
                                            if (var1_1 == 0) {
                                            }
                                            if (!v3.add) break block64;
                                            v6 = var6_5.method_1019(var5_4[var8_7].v);
                                            break block65;
                                        }
                                        v1 = var5_4;
                                        v2 = var8_7;
                                    }
                                    v6 = v1[v2].v;
                                }
                                var6_5 = v6;
                                if (var1_1 != 0) break block66;
                                if (!MoveAudit.close(var2_2, var6_5)) break block67;
                                var7_6 = var8_7 + 1;
                            }
                            ++var8_7;
                        }
                        if (var1_1 == 0) continue;
                    }
                    for (var8_7 = 0; var8_7 < var7_6; ++var8_7) {
                        v9 = (int)MoveAudit.KNOCKS.remove(var5_4[var8_7]);
                        if (var1_1 == 0) {
                            if (var1_1 == 0) continue;
                            break;
                        }
                        break block68;
                    }
                    v9 = var7_6;
                }
                if (var1_1 != 0) break block69;
                if (v9 <= 0) break block70;
                v9 = 0;
                if (var1_1 != 0) break block69;
                MoveAudit.ticksSinceKnock = v9;
                if (!(var2_2.field_1351 > 1.0)) break block70;
                MoveAudit.bounced = true;
                MoveAudit.sinceBounce = 0;
            }
            v9 = var8_7 = var7_6;
        }
        while (var8_7 < var5_4.length) {
            block76: {
                block75: {
                    block83: {
                        block81: {
                            block82: {
                                block80: {
                                    block79: {
                                        block78: {
                                            block77: {
                                                block73: {
                                                    block74: {
                                                        block72: {
                                                            block71: {
                                                                v3 = var5_4[var8_7];
lbl93:

                                                                var9_8 = v3;
                                                                var10_9 = var9_8.v.method_37267();
                                                                cfr_temp_0 = var10_9 - 1.0E-6;
                                                                v15 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 > 0.0 ? 1 : -1);
                                                                if (var1_1 != 0) break block71;
                                                                if (v15 > 0) {
                                                                }
                                                                v15 = (double)var9_8.add;
                                                            }
                                                            v18 = v15 != false ? var2_2.method_1020(MoveAudit.lastTickVelocity) : var2_2;
                                                            var12_10 = v18;
                                                            var9_8.bestKept = Math.max(var9_8.bestKept, (var12_10.field_1352 * var9_8.v.field_1352 + var12_10.field_1350 * var9_8.v.field_1350) / (var10_9 * var10_9));
                                                            if (var1_1 == 0) break block72;
lbl118:

                                                            var9_8.bestKept = 1.0;
                                                        }
                                                        cfr_temp_1 = var3_3 - var9_8.at - MoveAudit.b(15182, 208684816868027870L);
                                                        v21 = cfr_temp_1 == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1);
                                                        if (var1_1 != 0) break block73;
                                                        if (v21 >= 0) break block74;
                                                        break block75;
                                                    }
                                                    MoveAudit.KNOCKS.remove(var9_8);
                                                    if (var1_1 != 0) break block76;
                                                    MoveAudit.ticksSinceKnock = 0;
                                                    cfr_temp_2 = var10_9 - 0.1;
                                                    v21 = cfr_temp_2 == 0.0 ? 0 : (cfr_temp_2 < 0.0 ? -1 : 1);
                                                }
                                                if (v21 < 0) break block75;
                                                cfr_temp_3 = var9_8.bestKept - 0.5;
                                                v24 = cfr_temp_3 == 0.0 ? 0 : (cfr_temp_3 > 0.0 ? 1 : -1);
                                                if (var1_1 != 0) break block77;
                                                if (v24 >= 0) break block75;
                                                v27 = var0;
                                                if (var1_1 != 0) break block78;
                                                v24 = (double)v27.method_5765();
                                            }
                                            if (v24 != false) break block75;
                                            v27 = var0;
                                        }
                                        if (var1_1 != 0) break block79;
                                        if (v27.method_7325()) break block75;
                                        v27 = var0;
                                    }
                                    if (!v27.method_31549().field_7479) break block80;
                                    break block75;
                                }
                                v33 = MoveAudit.a[1];
                                v34 = var9_8;
                                if (var1_1 != 0) break block81;
                                if (!(v34.bestKept < 0.05)) break block82;
                                v36 = MoveAudit.a[20];
                                break block83;
                            }
                            v34 = var9_8;
                        }
                        var13_11 = MoveAudit.a;
                        v36 = var13_11[14] + Math.round(v34.bestKept * 100.0) + "%";
                    }
                    v38 = MoveAudit.a[6];
                    v39 = new Object[2];
                    v40 = v39;
                    v41 = v39;
                    v42 = 0;
                    v43 = var9_8.add != false ? MoveAudit.a[18] : MoveAudit.a[3];
                    v40[v42] = v43;
                    v41[1] = var10_9;
                    AntiCheat.flag(v33, (String)v36 + String.format(v38, v41));
                }
                ++var8_7;
            }
            if (var1_1 == 0) continue;
        }
    }

    private static boolean close(Vec3d vec3d, Vec3d vec3d2) {
        double d;
        block2: {
            block3: {
                double d2 = 0.02 + 0.12 * vec3d2.method_1033();
                int n = InputAudit.a;
                double d3 = vec3d.method_1025(vec3d2) - d2 * d2;
                d = d3 == 0.0 ? 0 : (d3 < 0.0 ? -1 : 1);
                if (n != 0) break block2;
                if (d >= 0) break block3;
                d = 1.0;
                break block2;
            }
            d = 0.0;
        }
        return (boolean)d;
    }

    
    static void endTick(ClientPlayerEntity var0) {
        block145: {
            block142: {
                block140: {
                    block141: {
                        block138: {
                            block139: {
                                block135: {
                                    block130: {
                                        block131: {
                                            block137: {
                                                block136: {
                                                    block134: {
                                                        block132: {
                                                            block133: {
                                                                block126: {
                                                                    block127: {
                                                                        block128: {
                                                                            block129: {
                                                                                block124: {
                                                                                    block125: {
                                                                                        block122: {
                                                                                            block123: {
                                                                                                block121: {
                                                                                                    block120: {
                                                                                                        block119: {
                                                                                                            block114: {
                                                                                                                block115: {
                                                                                                                    block116: {
                                                                                                                        block118: {
                                                                                                                            block117: {
                                                                                                                                block112: {
                                                                                                                                    block113: {
                                                                                                                                        block110: {
                                                                                                                                            block111: {
                                                                                                                                                MoveAudit.lastTickVelocity = var0.method_18798();
                                                                                                                                                ++MoveAudit.ticksSinceKnock;
                                                                                                                                                MoveAudit.inventoryMove(var0);
                                                                                                                                                var2_1 = var0.method_23317() - var0.field_6014;
                                                                                                                                                var1_2 = InputAudit.a;
                                                                                                                                                var4_3 = var0.method_23321() - var0.field_5969;
                                                                                                                                                var6_4 = MoveAudit.ownHits;
                                                                                                                                                v0 = MoveAudit.stabTicks;
                                                                                                                                                if (var1_2 != 0) break block110;
                                                                                                                                                if (v0 <= false) break block111;
                                                                                                                                                v0 = true;
                                                                                                                                                break block110;
                                                                                                                                            }
                                                                                                                                            v0 = false;
                                                                                                                                        }
                                                                                                                                        var7_5 = v0;
                                                                                                                                        v2 = 0;
                                                                                                                                        if (var1_2 != 0) break block112;
                                                                                                                                        MoveAudit.ownHits = v2;
                                                                                                                                        if (MoveAudit.stabTicks <= 0) break block113;
                                                                                                                                        --MoveAudit.stabTicks;
                                                                                                                                    }
                                                                                                                                    v5 = var0;
                                                                                                                                    if (var1_2 != 0) break block114;
                                                                                                                                    v2 = (int)MoveAudit.exempt(v5);
                                                                                                                                }
                                                                                                                                if (v2 == 0) break block115;
                                                                                                                                MoveAudit.hover = 0;
                                                                                                                                MoveAudit.slowTicks = 0;
                                                                                                                                MoveAudit.slowDistance = 0.0;
                                                                                                                                MoveAudit.sinceBounce = 0;
                                                                                                                                MoveAudit.strafeHits = 0;
                                                                                                                                MoveAudit.rising = 0;
                                                                                                                                v7 = 0;
                                                                                                                                if (var1_2 != 0) break block116;
                                                                                                                                MoveAudit.wasAir = v7;
                                                                                                                                if (var0.method_73183().method_8320(var0.method_24515().method_10074()).method_27852(Blocks.field_10030)) break block117;
                                                                                                                                cfr_temp_0 = var0.method_18798().field_1351 - 0.0;
                                                                                                                                v7 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 > 0.0 ? 1 : -1);
                                                                                                                                if (var1_2 != 0) break block116;
                                                                                                                                if (v7 <= 0) break block118;
                                                                                                                            }
                                                                                                                            v7 = 1;
                                                                                                                            break block116;
                                                                                                                        }
                                                                                                                        v7 = 0;
                                                                                                                    }
                                                                                                                    MoveAudit.bounced = v7;
                                                                                                                    return;
                                                                                                                }
                                                                                                                MoveAudit.strafe(var0, var2_1, var4_3, var6_4, var7_5);
                                                                                                                v5 = var0;
                                                                                                            }
                                                                                                            var8_6 = v5.method_23318() - var0.field_6036;
                                                                                                            v12 = var0.method_24828();
                                                                                                            if (var1_2 != 0) break block119;
                                                                                                            if (v12 != 0) break block120;
                                                                                                            cfr_temp_1 = var8_6 - 0.01;
                                                                                                            v12 = cfr_temp_1 == 0.0 ? 0 : (cfr_temp_1 > 0.0 ? 1 : -1);
                                                                                                        }
                                                                                                        if (var1_2 != 0) break block121;
                                                                                                        if (v12 <= 0) break block120;
                                                                                                        v12 = 1;
                                                                                                        break block121;
                                                                                                    }
                                                                                                    v12 = 0;
                                                                                                }
                                                                                                var10_7 = v12;
                                                                                                v16 = var10_7;
                                                                                                if (var1_2 != 0) break block122;
                                                                                                if (v16 == 0) break block123;
                                                                                                v18 = MoveAudit.sinceBounce + 1;
                                                                                                if (var1_2 != 0) break block124;
                                                                                                MoveAudit.sinceBounce = v18;
                                                                                                if (v18 <= 40) break block125;
                                                                                            }
                                                                                            v16 = 0;
                                                                                        }
                                                                                        MoveAudit.bounced = v16;
                                                                                    }
                                                                                    v18 = var10_7;
                                                                                }
                                                                                if (var1_2 != 0) break block126;
                                                                                if (v18 != 0) break block127;
                                                                                cfr_temp_2 = var0.method_18798().field_1351 - 0.0;
                                                                                v18 = cfr_temp_2 == 0.0 ? 0 : (cfr_temp_2 > 0.0 ? 1 : -1);
                                                                                if (var1_2 != 0) break block126;
                                                                                if (v18 <= 0) break block127;
                                                                                v25 = var0.method_73183().method_8320(var0.method_24515()).method_26164(BlockTags.field_16443);
                                                                                if (var1_2 != 0) break block128;
                                                                                if (v25) break block129;
                                                                                v18 = (int)var0.method_73183().method_8320(var0.method_24515().method_10074()).method_26164(BlockTags.field_16443);
                                                                                if (var1_2 != 0) break block126;
                                                                                if (v18 == 0) break block127;
                                                                            }
                                                                            MoveAudit.bounced = true;
                                                                            v25 = false;
                                                                        }
                                                                        MoveAudit.sinceBounce = (int)v25;
                                                                    }
                                                                    v18 = var10_7;
                                                                }
                                                                if (var1_2 != 0) break block130;
                                                                if (v18 == 0) break block131;
                                                                v18 = MoveAudit.ticksSinceKnock;
                                                                if (var1_2 != 0) break block130;
                                                                if (v18 <= 20) break block131;
                                                                v18 = (int)MoveAudit.bounced;
                                                                if (var1_2 != 0) break block130;
                                                                if (v18 != 0) break block131;
                                                                var11_8 = var0.method_6112(StatusEffects.field_5913);
                                                                v36 = 12;
                                                                v37 = var11_8;
                                                                if (var1_2 != 0) break block132;
                                                                if (v37 != null) break block133;
                                                                v39 = 0;
                                                                break block134;
                                                            }
                                                            v37 = var11_8;
                                                        }
                                                        v39 = (v37.method_5578() + 1) * 3;
                                                    }
                                                    var12_10 = v36 + v39;
                                                    if (++MoveAudit.rising == var12_10) {
                                                        AntiCheat.flag(MoveAudit.a[12], MoveAudit.a[19] + var12_10 + MoveAudit.a[2]);
                                                    }
                                                    if (var1_2 == 0) break block135;
                                                    if (!Module.a) break block136;
                                                    v43 = false;
                                                    break block137;
                                                }
                                                v43 = true;
                                            }
                                            Module.a = v43;
                                        }
                                        v18 = 0;
                                    }
                                    MoveAudit.rising = v18;
                                }
                                v45 = var0.method_24828();
                                if (var1_2 != 0) break block138;
                                if (v45 != 0) break block139;
                                cfr_temp_3 = Math.abs(var8_6) - 0.03;
                                v45 = cfr_temp_3 == 0.0 ? 0 : (cfr_temp_3 < 0.0 ? -1 : 1);
                                if (var1_2 != 0) break block138;
                                if (v45 >= 0) break block139;
                                v45 = MoveAudit.ticksSinceKnock;
                                if (var1_2 != 0) break block138;
                                if (v45 <= 20) break block139;
                                v51 = MoveAudit.hover;
                                if (var1_2 != 0) break block140;
                                MoveAudit.hover = v51 + 1;
                                if (MoveAudit.hover != 20) break block141;
                                AntiCheat.flag(MoveAudit.a[15], MoveAudit.a[13]);
                                if (var1_2 == 0) break block141;
                            }
                            v45 = 0;
                        }
                        MoveAudit.hover = v45;
                    }
                    v51 = var0.method_6115();
                }
                if (var1_2 != 0) break block142;
                if (v51 != 0) {
                }
                v51 = var0.method_24828();
                if (var1_2 != 0) break block142;
                if (v51 != 0) {
                }
                v51 = MoveAudit.ticksSinceKnock;
                if (var1_2 != 0) break block142;
                if (v51 > 20) {
                }
                cfr_temp_4 = var0.method_73183().method_8320(var0.method_23314()).method_26204().method_9499() - 0.6f;
                v51 = cfr_temp_4 == 0.0f ? 0 : (cfr_temp_4 < 0.0f ? -1 : 1);
                if (var1_2 != 0) break block142;
                if (v51 <= 0) {
                }
                MoveAudit.slowDistance += Math.hypot(var0.method_23317() - var0.field_6014, var0.method_23321() - var0.field_5969);
                var11_9 = Math.max(0.2f, ((UseEffectsComponent)var0.method_6030().method_58695(DataComponentTypes.field_63634, (Object)UseEffectsComponent.field_63416)).comp_4978());
                if (++MoveAudit.slowTicks == 10) {
                    block143: {
                        block144: {
                            var12_11 = MoveAudit.slowDistance / 10.0;
                            if (var1_2 != 0) break block143;
                            if (!(var12_11 > 0.13 * (double)var11_9 / 0.20000000298023224)) break block144;
                            AntiCheat.flag(MoveAudit.a[17], String.format(MoveAudit.a[0], new Object[]{var12_11, Registries.field_41178.method_10221((Object)var0.method_6030().method_7909()).method_12832()}));
                        }
                        MoveAudit.slowTicks = 0;
                    }
                    MoveAudit.slowDistance = 0.0;
                }
                if (var1_2 == 0) break block145;
lbl393:

                v51 = 0;
            }
            MoveAudit.slowTicks = v51;
            MoveAudit.slowDistance = 0.0;
        }
    }

    
    private static void inventoryMove(ClientPlayerEntity var0) {
        block49: {
            block48: {
                block46: {
                    block47: {
                        block45: {
                            block42: {
                                block44: {
                                    block43: {
                                        block41: {
                                            block40: {
                                                var2_1 = MinecraftClient.method_1551();
                                                var1_2 = InputAudit.a;
                                                var4_3 = var2_1.field_1755;
                                                if (var1_2 != 0) break block40;
                                                if (var4_3 instanceof HandledScreen) {
                                                }
                                                var3_4 = (HandledScreen)var4_3;
                                                if (var1_2 == 0) break block41;
lbl14:

                                                MoveAudit.screenTicks = 0;
                                            }
                                            return;
                                        }
                                        var4_3 = var0.field_3913.field_54155;
                                        v2 = var4_3.comp_3159();
                                        if (var1_2 != 0) break block42;
                                        if (v2 != 0) break block43;
                                        v2 = var4_3.comp_3160();
                                        if (var1_2 != 0) break block42;
                                        if (v2 != 0) break block43;
                                        v2 = var4_3.comp_3161();
                                        if (var1_2 != 0) break block42;
                                        if (v2 != 0) break block43;
                                        v2 = (int)var4_3.comp_3162();
                                        if (var1_2 != 0) break block42;
                                        if (v2 != 0) break block43;
                                        v2 = (int)var4_3.comp_3163();
                                        if (var1_2 != 0) break block42;
                                        if (v2 == 0) break block44;
                                    }
                                    v2 = 1;
                                    break block42;
                                }
                                v2 = 0;
                            }
                            var5_5 = v2;
                            v12 = var5_5;
                            if (var1_2 != 0) break block45;
                            if (v12 != 0) {
                            }
                            v12 = MoveAudit.screenTicks = MoveAudit.screenTicks + 1;
                        }
                        if (var1_2 != 0) break block46;
                        if (v12 >= 3) break block47;
lbl101:

                        return;
                    }
                    v12 = MoveAudit.screenTicks;
                }
                if (var1_2 != 0) break block48;
                if (v12 != 3) {
                }
                v12 = MoveAudit.screenTicks % 40;
            }
            if (v12 != 0) break block49;
lbl122:

            AntiCheat.flag(MoveAudit.a[9], MoveAudit.a[5] + var3_4.getClass().getSimpleName().replace(MoveAudit.a[7], "").toLowerCase() + MoveAudit.a[4]);
        }
    }

    private static void strafe(ClientPlayerEntity clientPlayerEntity, double d, double d2, int n, boolean bl) {
        int n2;
        block51: {
            int n3;
            int n4;
            block52: {
                int n5;
                block48: {
                    block49: {
                        double d3;
                        double d4;
                        block50: {
                            int n6;
                            block46: {
                                block47: {
                                    n5 = InputAudit.a;
                                    n6 = clientPlayerEntity.method_24828();
                                    if (n5 != 0) break block46;
                                    if (n6 != 0) break block47;
                                    n6 = 1;
                                    break block46;
                                }
                                n6 = 0;
                            }
                            n4 = n6;
                            n3 = clientPlayerEntity.field_5976;
                            n2 = n4;
                            if (n5 != 0) break block48;
                            if (n2 == 0) break block49;
                            n2 = wasAir;
                            if (n5 != 0) break block48;
                            if (n2 == 0) break block49;
                            n2 = n3;
                            if (n5 != 0) break block48;
                            if (n2 != 0) break block49;
                            n2 = lastCollided;
                            if (n5 != 0) break block48;
                            if (n2 != 0) break block49;
                            n2 = ticksSinceKnock;
                            if (n5 != 0) break block48;
                            if (n2 <= 10) break block49;
                            n2 = bl ? 1 : 0;
                            if (n5 != 0) break block48;
                            if (n2 != 0) break block49;
                            double d5 = Math.hypot(d, d2) - 3.0;
                            n2 = d5 == 0.0 ? 0 : (d5 < 0.0 ? -1 : 1);
                            if (n5 != 0) break block48;
                            if (n2 >= 0) break block49;
                            double d6 = Math.hypot(lastDx, lastDz) - 3.0;
                            n2 = d6 == 0.0 ? 0 : (d6 < 0.0 ? -1 : 1);
                            if (n5 != 0) break block48;
                            if (n2 >= 0) break block49;
                            d4 = Double.MAX_VALUE;
                            for (int i = 0; i <= n; ++i) {
                                double d7 = 0.91 * Math.pow(0.6, i);
                                d3 = d4;
                                if (n5 == 0) {
                                    d4 = Math.min(d3, Math.hypot(d - lastDx * d7, d2 - lastDz * d7));
                                    if (n5 == 0) continue;
                                }
                                break block50;
                            }
                            d3 = d4;
                        }
                        double d7 = d3 - 0.076;
                        n2 = d7 == 0.0 ? 0 : (d7 > 0.0 ? 1 : -1);
                        if (n5 != 0) break block48;
                        if (n2 <= 0) break block49;
                        n2 = strafeHits;
                        if (n5 != 0) break block48;
                        strafeHits = n2 + 1;
                        if (strafeHits != 3) break block49;
                        AntiCheat.flag(a[8], String.format(a[16], d4, 0.026));
                    }
                    n2 = n4;
                }
                if (n5 != 0) break block51;
                if (n2 != 0) break block52;
                strafeHits = 0;
            }
            wasAir = n4;
            n2 = n3;
        }
        lastCollided = n2;
        lastDx = d;
        lastDz = d2;
    }

    static void onMove(PlayerMoveC2SPacket playerMoveC2SPacket) {
        block31: {
            ClientPlayerEntity clientPlayerEntity;
            ClientPlayerEntity clientPlayerEntity2;
            block32: {
                boolean bl;
                block30: {
                    int n;
                    block28: {
                        block29: {
                            block25: {
                                ClientPlayerEntity clientPlayerEntity3;
                                block27: {
                                    block26: {
                                        block24: {
                                            clientPlayerEntity2 = MinecraftClient.method_1551().field_1724;
                                            n = InputAudit.a;
                                            clientPlayerEntity3 = clientPlayerEntity2;
                                            if (n != 0) break block24;
                                            if (clientPlayerEntity3 == null) break block25;
                                            clientPlayerEntity3 = clientPlayerEntity2;
                                        }
                                        if (n != 0) break block26;
                                        if (clientPlayerEntity3.method_5765()) break block25;
                                        clientPlayerEntity3 = clientPlayerEntity2;
                                    }
                                    if (n != 0) break block27;
                                    if (clientPlayerEntity3.method_31549().field_7479) break block25;
                                    clientPlayerEntity3 = clientPlayerEntity2;
                                }
                                bl = clientPlayerEntity3.method_7325();
                                if (n != 0) break block28;
                                if (!bl) break block29;
                            }
                            return;
                        }
                        bl = playerMoveC2SPacket.method_12273();
                    }
                    if (n != 0) break block30;
                    if (!bl) break block31;
                    clientPlayerEntity = clientPlayerEntity2;
                    if (n != 0) break block32;
                    bl = clientPlayerEntity.method_24828();
                }
                if (bl) break block31;
                clientPlayerEntity = clientPlayerEntity2;
            }
            if (clientPlayerEntity.field_6017 > 2.0) {
                AntiCheat.flag(a[10], String.format(a[11], clientPlayerEntity2.field_6017));
            }
        }
    }

    private static boolean exempt(ClientPlayerEntity clientPlayerEntity) {
        boolean bl;
        block94: {
            block96: {
                block95: {
                    BlockState blockState;
                    BlockState blockState2;
                    int n;
                    block92: {
                        block93: {
                            World world;
                            BlockPos blockPos;
                            World world2;
                            block90: {
                                block91: {
                                    ClientPlayerEntity clientPlayerEntity2;
                                    block88: {
                                        block89: {
                                            boolean bl2;
                                            block86: {
                                                block87: {
                                                    ClientPlayerEntity clientPlayerEntity3;
                                                    block84: {
                                                        block85: {
                                                            boolean bl3;
                                                            block82: {
                                                                block83: {
                                                                    ClientPlayerEntity clientPlayerEntity4;
                                                                    block80: {
                                                                        block81: {
                                                                            boolean bl4;
                                                                            block78: {
                                                                                block79: {
                                                                                    n = InputAudit.a;
                                                                                    bl4 = clientPlayerEntity.method_5765();
                                                                                    if (n != 0) break block78;
                                                                                    if (bl4) break block79;
                                                                                    bl4 = clientPlayerEntity.method_7325();
                                                                                    if (n != 0) break block78;
                                                                                    if (bl4) break block79;
                                                                                    bl4 = clientPlayerEntity.method_31549().field_7479;
                                                                                    if (n != 0) break block78;
                                                                                    if (bl4) break block79;
                                                                                    clientPlayerEntity4 = clientPlayerEntity;
                                                                                    if (n != 0) break block80;
                                                                                    if (!clientPlayerEntity4.method_31549().field_7478) break block81;
                                                                                }
                                                                                bl4 = true;
                                                                            }
                                                                            return bl4;
                                                                        }
                                                                        clientPlayerEntity4 = clientPlayerEntity;
                                                                    }
                                                                    bl3 = clientPlayerEntity4.method_6128();
                                                                    if (n != 0) break block82;
                                                                    if (bl3) break block83;
                                                                    bl3 = clientPlayerEntity.method_6101();
                                                                    if (n != 0) break block82;
                                                                    if (bl3) break block83;
                                                                    bl3 = clientPlayerEntity.method_5799();
                                                                    if (n != 0) break block82;
                                                                    if (bl3) break block83;
                                                                    bl3 = clientPlayerEntity.method_5771();
                                                                    if (n != 0) break block82;
                                                                    if (bl3) break block83;
                                                                    clientPlayerEntity3 = clientPlayerEntity;
                                                                    if (n != 0) break block84;
                                                                    if (!clientPlayerEntity3.method_6123()) break block85;
                                                                }
                                                                bl3 = true;
                                                            }
                                                            return bl3;
                                                        }
                                                        clientPlayerEntity3 = clientPlayerEntity;
                                                    }
                                                    bl2 = clientPlayerEntity3.method_6059(StatusEffects.field_5902);
                                                    if (n != 0) break block86;
                                                    if (bl2) break block87;
                                                    clientPlayerEntity2 = clientPlayerEntity;
                                                    if (n != 0) break block88;
                                                    if (!clientPlayerEntity2.method_6059(StatusEffects.field_5906)) break block89;
                                                }
                                                bl2 = true;
                                            }
                                            return bl2;
                                        }
                                        clientPlayerEntity2 = clientPlayerEntity;
                                    }
                                    world2 = clientPlayerEntity2.method_73183();
                                    blockPos = clientPlayerEntity.method_24515();
                                    world = world2;
                                    if (n != 0) break block90;
                                    if (world.method_8393(blockPos.method_10263() >> 4, blockPos.method_10260() >> 4)) break block91;
                                    return true;
                                }
                                world = world2;
                            }
                            blockState2 = world.method_8320(blockPos);
                            blockState = world2.method_8320(blockPos.method_10074());
                            bl = BlockPos.method_29715((Box)clientPlayerEntity.method_5829().method_1011(0.001)).anyMatch(arg_0 -> MoveAudit.lambda$exempt$1(world2, arg_0));
                            if (n != 0) break block92;
                            if (!bl) break block93;
                            return true;
                        }
                        bl = blockState2.method_27852(Blocks.field_27879);
                    }
                    if (n != 0) break block94;
                    if (bl) break block95;
                    bl = blockState2.method_27852(Blocks.field_16492);
                    if (n != 0) break block94;
                    if (bl) break block95;
                    bl = blockState2.method_27852(Blocks.field_10422);
                    if (n != 0) break block94;
                    if (bl) break block95;
                    bl = blockState2.method_27852(Blocks.field_21211);
                    if (n != 0) break block94;
                    if (bl) break block95;
                    bl = blockState.method_27852(Blocks.field_21211);
                    if (n != 0) break block94;
                    if (bl) break block95;
                    bl = blockState.method_27852(Blocks.field_10030);
                    if (n != 0) break block94;
                    if (bl) break block95;
                    bl = blockState.method_27852(Blocks.field_16492);
                    if (n != 0) break block94;
                    if (!bl) break block96;
                }
                bl = true;
                break block94;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean lambda$exempt$1(World world, BlockPos blockPos) {
        return world.method_8320(blockPos).method_27852(Blocks.field_10343);
    }

    private static boolean lambda$onOwnDrop$0(Vec3d vec3d, MoveAudit$Knock moveAudit$Knock) {
        int n;
        block8: {
            block7: {
                MoveAudit$Knock moveAudit$Knock2;
                int n2;
                block6: {
                    n2 = InputAudit.a;
                    moveAudit$Knock2 = moveAudit$Knock;
                    if (n2 != 0) break block6;
                    if (moveAudit$Knock2.add) break block7;
                    moveAudit$Knock2 = moveAudit$Knock;
                }
                double d = moveAudit$Knock2.v.method_1025(vec3d) - 1.0E-6;
                n = d == 0.0 ? 0 : (d < 0.0 ? -1 : 1);
                if (n2 != 0) break block8;
                if (n >= 0) break block7;
                n = 1;
                break block8;
            }
            n = 0;
        }
        return n != 0;
    }

    
    static {
        block27: {
            block26: {
                block25: {
                    block24: {
                        block23: {
                            block22: {
                                var21 = new String[21];
                                var19_1 = 0;
                                var18_2 = "[?14:a-\u0011rh!5w(\u001dz#%rj-\u001b1v!sm&^4p\b(to=yj5\u0007\u001e^ej1qpa\txw::m.\nyj<}#-\u0017ww;tda\u0017e\u0003\u0016xw\u0005^~s7t\r\tpo9sm&^fj&r#\u0015R1&!:&oLw#0vl\"\u0015b,&s`*\u0006-rq7\u007fm\u0006-eq3|f\r7\u007fu7tw.\fhN=lf\u00060~E3vo*\u001d}b;wf%^ek7:d3\u0011dm6:&oOw#0vl\"\u0015b#;tw.^p#4{o-\u00038}z0\u0016~u7hj/\u00191e=h# ^bf1um%^fj&r#/\u0011ek;tda\u0016~o6sm&^xwros\u0011\u0015\u007fl1qa \u001dz#1owa\n~#\u00038}z5\ndq<\u007fga[?14:a-\u0011rh!5w(\u001dz#;t#5\u0016t#3sqm^ek7:o(\u0013xwrspa[?04\u00060~P>ut\t\u001bis>up(\u0011\u007f";
                                var20_3 = "[?14:a-\u0011rh!5w(\u001dz#%rj-\u001b1v!sm&^4p\b(to=yj5\u0007\u001e^ej1qpa\txw::m.\nyj<}#-\u0017ww;tda\u0017e\u0003\u0016xw\u0005^~s7t\r\tpo9sm&^fj&r#\u0015R1&!:&oLw#0vl\"\u0015b,&s`*\u0006-rq7\u007fm\u0006-eq3|f\r7\u007fu7tw.\fhN=lf\u00060~E3vo*\u001d}b;wf%^ek7:d3\u0011dm6:&oOw#0vl\"\u0015b#;tw.^p#4{o-\u00038}z0\u0016~u7hj/\u00191e=h# ^bf1um%^fj&r#/\u0011ek;tda\u0016~o6sm&^xwros\u0011\u0015\u007fl1qa \u001dz#1owa\n~#\u00038}z5\ndq<\u007fga[?14:a-\u0011rh!5w(\u001dz#;t#5\u0016t#3sqm^ek7:o(\u0013xwrspa[?04\u00060~P>ut\t\u001bis>up(\u0011\u007f".length();
                                var17_4 = 31;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block22;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "\fxp;tda\u0018~qr\u0011\u0015\u007fl1qa \u001dz#;}m.\ftg";
                                    var20_3 = "\fxp;tda\u0018~qr\u0011\u0015\u007fl1qa \u001dz#;}m.\ftg".length();
                                    var17_4 = 11;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block22;
                                        break;
                                    }
                                    break;
                                }
lbl26:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    break block23;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_6 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_6;
                                v9 = v6[v8];
                                switch (var22_6 % 7) {
                                    case 0: {
                                        v10 = 126;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 17;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 3;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 82;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 26;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 3;
                                        break;
                                    }
                                    default: {
                                        v10 = 65;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_6;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_6);
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
                        MoveAudit.a = var21;
                        var8_7 = 4816806217663383308L;
                        var14_8 = new long[3];
                        var11_9 = 0;
                        var12_10 = "1\u00c9\u0002T\u0097\u00ff\u00f9Z\u00e3)\u00d2\u0019\u00d8I\u0090\u0085\u00ad\u00dd\u00fe=N\u00d4\u00ff7";
                        var13_11 = "1\u00c9\u0002T\u0097\u00ff\u00f9Z\u00e3)\u00d2\u0019\u00d8I\u0090\u0085\u00ad\u00dd\u00fe=N\u00d4\u00ff7".length();
                        var10_12 = 0;
                        while (true) {
                            break block24;
                            break;
                        }
lbl84:

                        while (true) {
                            var14_8[v11] = (((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L) ^ var8_7;
                            if (var10_12 < var13_11) continue;
                            break block25;
                            break;
                        }
                    }
                    var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                    v11 = var11_9++;
                    while (true)
                }
                MoveAudit.b = var14_8;
                MoveAudit.c = new Integer[3];
                var0_14 = 303516280784203393L;
                var6_15 = new long[2];
                var3_16 = 0;
                var4_17 = "\u0006\u00d3+\u00cf8\u00df\u0006\u0083\u007f\u00d2\u009f\u009a\u0097N\u001c>";
                var5_18 = "\u0006\u00d3+\u00cf8\u00df\u0006\u0083\u007f\u00d2\u009f\u009a\u0097N\u001c>".length();
                var2_19 = 0;
                while (true) {
                    break block26;
                    break;
                }
lbl106:

                while (true) {
                    var6_15[v12] = (((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L) ^ var0_14;
                    if (var2_19 < var5_18) continue;
                    break block27;
                    break;
                }
            }
            var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
            v12 = var3_16++;
            while (true)
        }
        MoveAudit.d = var6_15;
        MoveAudit.e = new Long[2];
        MoveAudit.JUDGE_MS = MoveAudit.b(8690, 8927491162512471907L);
        MoveAudit.KNOCKS = new ConcurrentLinkedQueue<E>();
        MoveAudit.lastTickVelocity = Vec3d.field_1353;
        MoveAudit.ticksSinceKnock = MoveAudit.a(22361, 6511700005576244786L);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x296B;
        if (c[n2] == null) {
            MoveAudit.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x1690) & Short.MAX_VALUE;
        if (e[n2] == null) {
            MoveAudit.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
