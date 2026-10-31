package dev.quelchi.aurora.prediction.ac;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.prediction.ac.AntiCheat;
import dev.quelchi.aurora.prediction.ac.InputAudit;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

final class CombatAudit {
    private static final int TRIGGER_WINDOW;
    private static final int ANCHOR_WINDOW;
    static final long IDLE_MS;
    private static Entity target;
    private static long acquiredAt;
    private static long lastPress;
    private static long pressBeforeAcquire;
    private static boolean reacted;
    private static int presses;
    private static int onTarget;
    private static final List<Long> reactions;
    private static long anchorPlacedAt;
    private static final List<Long> cycles;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private CombatAudit() {
    }

    static void reset() {
        target = null;
        pressBeforeAcquire = 0L;
        lastPress = 0L;
        onTarget = 0;
        presses = 0;
        reactions.clear();
        anchorPlacedAt = 0L;
        cycles.clear();
    }

    static void frame() {
        block15: {
            PlayerEntity playerEntity;
            block16: {
                PlayerEntity playerEntity2;
                int n;
                block14: {
                    block13: {
                        Entity entity;
                        MinecraftClient minecraftClient;
                        block12: {
                            minecraftClient = MinecraftClient.method_1551();
                            Entity entity2 = minecraftClient.field_1692;
                            n = InputAudit.a;
                            entity = entity2;
                            if (n != 0) break block12;
                            if (!(entity instanceof PlayerEntity)) break block13;
                            entity = entity2;
                        }
                        PlayerEntity playerEntity3 = (PlayerEntity)entity;
                        playerEntity2 = playerEntity3;
                        if (n != 0) break block14;
                        if (playerEntity2 == minecraftClient.field_1724) break block13;
                        playerEntity2 = playerEntity3;
                        break block14;
                    }
                    playerEntity2 = null;
                }
                playerEntity = playerEntity2;
                if (n != 0) break block15;
                if (playerEntity != target) break block16;
                return;
            }
            target = playerEntity;
            acquiredAt = System.currentTimeMillis();
            pressBeforeAcquire = lastPress;
            reacted = false;
        }
    }

    static boolean isReaction(long l, long l2) {
        long l3;
        block2: {
            block3: {
                int n = InputAudit.a;
                long l4 = l - l2 - CombatAudit.a(14551, 4618837414966714137L);
                l3 = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                if (n != 0) break block2;
                if (l3 < 0) break block3;
                l3 = 1;
                break block2;
            }
            l3 = 0;
        }
        return (boolean)l3;
    }

    static void onAttackPress(long l) {
        block33: {
            int n;
            block35: {
                block34: {
                    int n2;
                    int n3;
                    block31: {
                        block32: {
                            block29: {
                                block30: {
                                    n3 = InputAudit.a;
                                    n2 = presses;
                                    if (n3 != 0) break block29;
                                    presses = n2 + 1;
                                    lastPress = l;
                                    if (target == null) break block30;
                                    n2 = onTarget;
                                    if (n3 != 0) break block29;
                                    onTarget = n2 + 1;
                                    if (reacted) break block30;
                                    n2 = 1;
                                    if (n3 != 0) break block29;
                                    reacted = n2;
                                    if (!CombatAudit.isReaction(acquiredAt, pressBeforeAcquire)) break block30;
                                    reactions.add(l - acquiredAt);
                                }
                                n2 = presses;
                            }
                            if (n3 != 0) break block31;
                            if (n2 >= 25) break block32;
                            return;
                        }
                        n2 = onTarget;
                    }
                    double d = (double)n2 / (double)presses;
                    long l2 = CombatAudit.median(reactions);
                    Aurora.LOG.info(a[3], new Object[]{Math.round(d * 100.0), l2, reactions.size(), reactions});
                    if (n3 != 0) break block33;
                    if (!(d >= 0.9)) break block34;
                    n = reactions.size();
                    if (n3 != 0) break block35;
                    if (n < 5) break block34;
                    long l3 = l2 - CombatAudit.a(13571, 3326961738211218127L);
                    n = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                    if (n3 != 0) break block35;
                    if (n < 0) {
                    }
                    break block34;
                    AntiCheat.flag(a[7], Math.round(d * 100.0) + a[0] + l2 + a[4]);
                }
                n = onTarget = 0;
            }
            presses = n;
            reactions.clear();
        }
    }

    static void onAnchorPlaced(long l) {
        anchorPlacedAt = l;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static void onAnchorDetonated(long l) {
        block25: {
            List<Long> list;
            int n;
            block24: {
                block22: {
                    long l2;
                    block23: {
                        long l3;
                        block20: {
                            block21: {
                                block19: {
                                    n = InputAudit.a;
                                    l3 = anchorPlacedAt;
                                    if (n != 0) break block19;
                                    if (l3 == 0L) return;
                                    l3 = l;
                                }
                                if (n != 0) break block20;
                                if (l3 > anchorPlacedAt) break block21;
                                return;
                            }
                            l3 = l - anchorPlacedAt;
                        }
                        l2 = l3;
                        if (n != 0) break block22;
                        anchorPlacedAt = 0L;
                        if (l2 <= CombatAudit.a(26241, 6003660403962642764L)) break block23;
                        return;
                    }
                    list = cycles;
                    if (n != 0) break block24;
                    list.add(l2);
                }
                if (cycles.size() < 6) {
                    return;
                }
                list = cycles;
            }
            double d = list.stream().mapToLong(Long::longValue).average().orElse(0.0);
            double d2 = Math.sqrt(cycles.stream().mapToDouble(arg_0 -> CombatAudit.lambda$onAnchorDetonated$0(d, arg_0)).sum() / (double)cycles.size());
            if (n != 0) return;
            Aurora.LOG.info(a[8], new Object[]{Math.round(d), Math.round(d2), cycles});
            if (!(d2 < 8.0)) break block25;
            String[] stringArray = a;
            long l4 = Math.round(d2);
            long l5 = Math.round(d);
            AntiCheat.flag(stringArray[5], stringArray[1] + l5 + stringArray[2] + l4 + stringArray[6]);
        }
        cycles.clear();
    }

    private static long median(List<Long> list) {
        if (list.isEmpty()) {
            return CombatAudit.a(31017, 5962379769763733216L);
        }
        ArrayList<Long> arrayList = new ArrayList<Long>(list);
        arrayList.sort(null);
        return (Long)arrayList.get(arrayList.size() / 2);
    }

    private static double lambda$onAnchorDetonated$0(double d, Long l) {
        return ((double)l.longValue() - d) * ((double)l.longValue() - d);
    }

    
    static {
        block32: {
            block31: {
                block30: {
                    block29: {
                        block28: {
                            block27: {
                                var21 = new String[9];
                                var19_1 = 0;
                                var18_2 = "f\u0014Qt./5*WUa.#7cU\u001ebb- &F\u00122|)8 @W|il0-\u0014\r\"Z]za>y M]~kl\u0010.G\u00122}<+&UZ2a\"5:\u0014L\u0002AL}|-y\u0002w\u00042o8-\"WU2y%7'[I>.7$f\u0014Q|.-y3X_kk>ucY[vg-7cF[sm80,Z\u001eis!*c[Hw|l\">\u0014\u0016ise\u0002.G\n\u0002AJ}O\":+[L\u0002.G";
                                var20_3 = "f\u0014Qt./5*WUa.#7cU\u001ebb- &F\u00122|)8 @W|il0-\u0014\r\"Z]za>y M]~kl\u0010.G\u00122}<+&UZ2a\"5:\u0014L\u0002AL}|-y\u0002w\u00042o8-\"WU2y%7'[I>.7$f\u0014Q|.-y3X_kk>ucY[vg-7cF[sm80,Z\u001eis!*c[Hw|l\">\u0014\u0016ise\u0002.G\n\u0002AJ}O\":+[L\u0002.G".length();
                                var17_4 = 37;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block27;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "\u0017FWui)+\u0001[JG\u0002AL}|-y\u0002w\u00042o\":+[L2y%7'[I>.<5\"W[2z#y'QJ}`--&\u0014Eoc?y.Q_|\"l\">YM2}<+&UZ2&7$j";
                                    var20_3 = "\u0017FWui)+\u0001[JG\u0002AL}|-y\u0002w\u00042o\":+[L2y%7'[I>.<5\"W[2z#y'QJ}`--&\u0014Eoc?y.Q_|\"l\">YM2}<+&UZ2&7$j".length();
                                    var17_4 = 10;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block27;
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
                                    break block28;
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
                                        v10 = 67;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 52;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 62;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 18;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 14;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 76;
                                        break;
                                    }
                                    default: {
                                        v10 = 89;
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
                        CombatAudit.a = var21;
                        var9_7 = 3093908507522213662L;
                        var8_8 = new long[2];
                        var12_9 = 0;
                        var13_10 = "\u00e9\u00d0o\u00c06k\u00a7\u0007|\u001aW\u00136k\u00a7\u0018";
                        var14_11 = "\u00e9\u00d0o\u00c06k\u00a7\u0007|\u001aW\u00136k\u00a7\u0018".length();
                        var11_12 = 0;
                        while (true) {
                            break block29;
                            break;
                        }
lbl84:

                        while (true) {
                            var8_8[v11] = (((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L) ^ var9_7;
                            if (var11_12 < var14_11) continue;
                            break block30;
                            break;
                        }
                    }
                    var15_13 = var13_10.substring(var11_12, var11_12 += 8).getBytes("ISO-8859-1");
                    v11 = var12_9++;
                    while (true)
                }
                CombatAudit.TRIGGER_WINDOW = (int)var8_8[0];
                CombatAudit.ANCHOR_WINDOW = (int)var8_8[1];
                var0_14 = 5587161781782032210L;
                var6_15 = new long[5];
                var3_16 = 0;
                var4_17 = "\u001e\u00d8\u00df]\u00fb\u0084)\u00cec\u00a2(\u001b2\u00a4\u00fd\u00f3>\u00a6\u00cf\u00ed4[/\f";
                var5_18 = "\u001e\u00d8\u00df]\u00fb\u0084)\u00cec\u00a2(\u001b2\u00a4\u00fd\u00f3>\u00a6\u00cf\u00ed4[/\f".length();
                var2_19 = 0;
                while (true) {
                    var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                    v12 = var6_15;
                    v13 = var3_16++;
                    v14 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                    v15 = -1;
                    break block31;
                    break;
                }
lbl112:

                while (true) {
                    v12[v13] = v16;
                    if (var2_19 < var5_18) continue;
                    var4_17 = "\r\u0090\u00fe!\u009b\u009ep\u00b1\u00e0\u00c8\u00c99\u00a6\u0080NM";
                    var5_18 = "\r\u0090\u00fe!\u009b\u009ep\u00b1\u00e0\u00c8\u00c99\u00a6\u0080NM".length();
                    var2_19 = 0;
                    while (true) {
                        var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                        v12 = var6_15;
                        v13 = var3_16++;
                        v14 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                        v15 = 0;
                        break block31;
                        break;
                    }
                    break;
                }
lbl125:

                while (true) {
                    v12[v13] = v16;
                    if (var2_19 < var5_18) continue;
                    break block32;
                    break;
                }
            }
            v16 = v14 ^ var0_14;
            switch (v15) {
                default: {
                    continue;
                }
                case 0:
lbl136:

                continue;
            }
        }
        CombatAudit.b = var6_15;
        CombatAudit.c = new Long[5];
        CombatAudit.IDLE_MS = CombatAudit.a(26475, 8299951478040854692L);
        CombatAudit.reactions = new ArrayList<Long>();
        CombatAudit.cycles = new ArrayList<Long>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x67CD) & Short.MAX_VALUE;
        if (c[n2] == null) {
            CombatAudit.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
