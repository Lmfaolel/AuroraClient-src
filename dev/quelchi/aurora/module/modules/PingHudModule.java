package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.TextHud;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.query.QueryPingC2SPacket;
import net.minecraft.util.Util;

public final class PingHudModule
extends TextHud {
    private static final long STALE_MS;
    private static volatile int measuredMs;
    private static volatile long measuredAt;
    private int ticks;
    private static final String[] d;
    private static final long[] g;
    private static final Long[] h;

    public PingHudModule() {
        String[] stringArray = d;
        super(stringArray[3], stringArray[1], stringArray[0], 4, 28);
    }

    @Override
    public void onTick() {
        ClientPlayNetworkHandler clientPlayNetworkHandler = MinecraftClient.method_1551().method_1562();
        if (clientPlayNetworkHandler == null) {
            return;
        }
        if (++this.ticks % 20 != 0) {
            return;
        }
        clientPlayNetworkHandler.method_52787((Packet)new QueryPingC2SPacket(Util.method_658()));
    }

    public static void onPingResult(long l) {
        long l2 = Util.method_658();
        measuredMs = (int)Math.max(0L, l2 - l);
        measuredAt = l2;
    }

    @Override
    protected List<String> lines() {
        int n;
        block15: {
            int n2;
            block16: {
                ClientPlayNetworkHandler clientPlayNetworkHandler;
                MinecraftClient minecraftClient;
                block14: {
                    boolean bl;
                    block13: {
                        block12: {
                            minecraftClient = MinecraftClient.method_1551();
                            clientPlayNetworkHandler = minecraftClient.method_1562();
                            bl = AmbienceModule.b;
                            if (clientPlayNetworkHandler != null && minecraftClient.field_1724 != null) break block12;
                            return List.of();
                        }
                        n2 = measuredMs;
                        n = n2;
                        if (bl) break block13;
                        if (n < 0) break block14;
                        long l = Util.method_658() - measuredAt - PingHudModule.b(11582, 5765268852802714027L);
                        n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                    }
                    if (bl) break block15;
                    if (n <= 0) break block16;
                }
                PlayerListEntry playerListEntry = clientPlayNetworkHandler.method_2871(minecraftClient.field_1724.method_5667());
                int n3 = playerListEntry == null ? 0 : Math.max(0, playerListEntry.method_2959());
                n2 = n3;
            }
            n = n2;
        }
        return List.of(n + d[2]);
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[4];
                        var11_1 = 0;
                        var10_2 = "y*d\t\u000eZ&x\u0018\u0007\u0003|e\"~\u000b\f\u0012%";
                        var12_3 = "y*d\t\u000eZ&x\u0018\u0007\u0003|e\"~\u000b\f\u0012%".length();
                        var9_4 = 4;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = ").y\u0004Y*d\t";
                            var12_3 = ").y\u0004Y*d\t".length();
                            var9_4 = 3;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block20;
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
                            break block21;
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
                                v10 = 9;
                                break;
                            }
                            case 1: {
                                v10 = 67;
                                break;
                            }
                            case 2: {
                                v10 = 10;
                                break;
                            }
                            case 3: {
                                v10 = 110;
                                break;
                            }
                            case 4: {
                                v10 = 98;
                                break;
                            }
                            case 5: {
                                v10 = 113;
                                break;
                            }
                            default: {
                                v10 = 92;
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
                PingHudModule.d = var13;
                var0_7 = 7047159048884573590L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "rg\u0001\u00fb\u00d1B\u00fd\u001a1\u00ce\u00d0\u00a8\u009d\u009c?\u00b5";
                var5_11 = "rg\u0001\u00fb\u00d1B\u00fd\u001a1\u00ce\u00d0\u00a8\u009d\u009c?\u00b5".length();
                var2_12 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl84:

                while (true) {
                    var6_8[v11] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block23;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v11 = var3_9++;
            while (true)
        }
        PingHudModule.g = var6_8;
        PingHudModule.h = new Long[2];
        PingHudModule.STALE_MS = PingHudModule.b(28560, 1417376224278428420L);
        PingHudModule.measuredMs = -1;
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x5494) & Short.MAX_VALUE;
        if (h[n2] == null) {
            PingHudModule.h[n2] = g[n2] ^ l;
        }
        return h[n2];
    }
}
