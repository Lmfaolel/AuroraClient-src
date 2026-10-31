package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.cosmetics.renderer.KillEffects;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.StringSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public final class AutoTextModule
extends Module {
    private static final long COMBAT_WINDOW_MS;
    private static AutoTextModule instance;
    private final StringSetting text;
    private final NumberSetting delay;
    private final NumberSetting cooldown;
    private final NumberSetting range;
    private final StringSetting trigger;
    private int pending;
    private long lastCombat;
    private long lastSent;
    private static final String[] b;
    private static final long[] d;
    private static final Long[] e;

    public static AutoTextModule get() {
        return instance;
    }

    public AutoTextModule() {
        String[] stringArray = b;
        super(stringArray[1], stringArray[0], Category.PLAYER, false);
        this.text = new StringSetting(stringArray[5], stringArray[2], 100);
        this.delay = new NumberSetting(stringArray[7], 0.0, 60.0, 5.0, 1.0);
        this.cooldown = new NumberSetting(stringArray[4], 0.0, 30.0, 3.0, 0.5);
        this.range = new NumberSetting(stringArray[3], 4.0, 50.0, 24.0, 1.0);
        this.trigger = new StringSetting(stringArray[6], "", 64);
        this.pending = -1;
        instance = this;
        this.addSettings(this.text, this.delay, this.cooldown, this.range, this.trigger);
    }

    private long cooldownMs() {
        return Math.round(this.cooldown.getValue() * 1000.0);
    }

    @Override
    public void onDisable() {
        this.pending = -1;
    }

    @Override
    public void onTick() {
        block14: {
            AutoTextModule autoTextModule;
            block12: {
                block13: {
                    boolean bl;
                    block10: {
                        block11: {
                            bl = AmbienceModule.b;
                            autoTextModule = this;
                            if (bl) break block10;
                            if (autoTextModule.pending >= 0) break block11;
                            return;
                        }
                        autoTextModule = this;
                    }
                    if (bl) break block12;
                    if (autoTextModule.pending != 0) break block13;
                    AutoTextModule.send(this.text.getValue());
                    this.pending = -1;
                    if (!bl) break block14;
                }
                autoTextModule = this;
            }
            --autoTextModule.pending;
        }
    }

    public void onCombat() {
        block5: {
            block4: {
                boolean bl = AmbienceModule.b;
                AutoTextModule autoTextModule = this;
                if (bl) break block4;
                if (!autoTextModule.isEnabled()) break block5;
                autoTextModule = this;
            }
            autoTextModule.lastCombat = System.currentTimeMillis();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onPlayerDeath(PlayerEntity playerEntity) {
        AutoTextModule autoTextModule;
        long l;
        block33: {
            long l2;
            block31: {
                boolean bl;
                block32: {
                    block29: {
                        block30: {
                            block27: {
                                block28: {
                                    ClientPlayerEntity clientPlayerEntity;
                                    block26: {
                                        AutoTextModule autoTextModule2;
                                        block25: {
                                            block24: {
                                                bl = AmbienceModule.b;
                                                autoTextModule2 = this;
                                                if (bl) break block24;
                                                if (!autoTextModule2.isEnabled()) return;
                                                autoTextModule2 = this;
                                            }
                                            if (bl) break block25;
                                            if (autoTextModule2.pending >= 0) return;
                                            autoTextModule2 = this;
                                        }
                                        clientPlayerEntity = autoTextModule2.mc.field_1724;
                                        if (bl) break block26;
                                        if (clientPlayerEntity == null) return;
                                        clientPlayerEntity = playerEntity;
                                    }
                                    if (clientPlayerEntity == this.mc.field_1724) {
                                        return;
                                    }
                                    l = System.currentTimeMillis();
                                    long l3 = l - this.lastSent - this.cooldownMs();
                                    l2 = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                                    if (bl) break block27;
                                    if (l2 >= 0) break block28;
                                    return;
                                }
                                l2 = (long)KillEffects.localPlayerBlamed(playerEntity);
                            }
                            if (bl) break block29;
                            if (l2 == false) break block30;
                            this.queueSend(l);
                            return;
                        }
                        long l4 = l - this.lastCombat - AutoTextModule.a(17644, 3991451889907778264L);
                        l2 = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                    }
                    if (bl) break block31;
                    if (l2 <= 0) break block32;
                    return;
                }
                autoTextModule = this;
                if (bl) break block33;
                float f = autoTextModule.mc.field_1724.method_5739((Entity)playerEntity) - this.range.getValueFloat();
                l2 = f == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
            }
            if (l2 > 0) {
                return;
            }
            autoTextModule = this;
        }
        autoTextModule.queueSend(l);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onSystemMessage(String string) {
        long l;
        block28: {
            String string2;
            String string3;
            boolean bl;
            block27: {
                String string4;
                block25: {
                    block26: {
                        block24: {
                            AutoTextModule autoTextModule;
                            block23: {
                                bl = AmbienceModule.b;
                                autoTextModule = this;
                                if (bl) break block23;
                                if (!autoTextModule.isEnabled()) return;
                                autoTextModule = this;
                            }
                            if (autoTextModule.pending >= 0) return;
                            string4 = string;
                            if (bl) break block24;
                            if (string4 == null) return;
                            string4 = string;
                        }
                        if (bl) break block25;
                        if (!string4.isEmpty()) break block26;
                        return;
                    }
                    string4 = this.trigger.getValue().trim();
                }
                string3 = string4;
                string2 = string3;
                if (bl) break block27;
                if (string2.isEmpty()) return;
                string2 = string.toLowerCase();
            }
            if (!string2.contains(string3.toLowerCase())) {
                return;
            }
            l = System.currentTimeMillis();
            if (bl) return;
            if (l - this.lastSent >= this.cooldownMs()) break block28;
            return;
        }
        this.queueSend(l);
    }

    private void queueSend(long l) {
        this.lastSent = l;
        this.pending = Math.max(0, (int)this.delay.getValue());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void send(String string) {
        MinecraftClient minecraftClient;
        boolean bl;
        MinecraftClient minecraftClient2;
        block17: {
            String string2;
            block16: {
                minecraftClient2 = MinecraftClient.method_1551();
                bl = AmbienceModule.b;
                string2 = string;
                if (bl) break block16;
                if (string2 == null) return;
                string2 = string;
            }
            if (string2.isBlank()) return;
            minecraftClient = minecraftClient2;
            if (bl) break block17;
            if (minecraftClient.method_1562() == null) return;
            minecraftClient = minecraftClient2;
        }
        if (minecraftClient.field_1724 == null) {
            return;
        }
        if (string.startsWith("/")) {
            minecraftClient2.method_1562().method_45730(string.substring(1));
            if (!bl) return;
        }
        minecraftClient2.method_1562().method_45729(string);
        return;
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[8];
                        var11_1 = 0;
                        var10_2 = "7\u001a\u0017W'\u00180\u0007\u0001\u0017DbU:\u001aR\u001dJk\u0019\t5\u0007\u0002L'!0\f\u0006\u0002\u0013\u0015\n?\u001b\u001aO''4\u001a\u0015\u0013\f7\u001d\u0019Oc\u001a\"\u001aR^P.\u0004 \u0017\u000eW";
                        var12_3 = "7\u001a\u0017W'\u00180\u0007\u0001\u0017DbU:\u001aR\u001dJk\u0019\t5\u0007\u0002L'!0\f\u0006\u0002\u0013\u0015\n?\u001b\u001aO''4\u001a\u0015\u0013\f7\u001d\u0019Oc\u001a\"\u001aR^P.\u0004 \u0017\u000eW".length();
                        var9_4 = 20;
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
                            var10_2 = "7\u001a\u0017W'!'\u001d\u0015\u0011Fu\r0\u0017\u001aB~U}\u0000\u001b\u0015Ht\\";
                            var12_3 = "7\u001a\u0017W'!'\u001d\u0015\u0011Fu\r0\u0017\u001aB~U}\u0000\u001b\u0015Ht\\".length();
                            var9_4 = 12;
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
                                v10 = 116;
                                break;
                            }
                            case 1: {
                                v10 = 114;
                                break;
                            }
                            case 2: {
                                v10 = 118;
                                break;
                            }
                            case 3: {
                                v10 = 35;
                                break;
                            }
                            case 4: {
                                v10 = 7;
                                break;
                            }
                            case 5: {
                                v10 = 117;
                                break;
                            }
                            default: {
                                v10 = 85;
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
                AutoTextModule.b = var13;
                var0_7 = 3416781810081931070L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "\u0018\u000e\u00a4\u00ac\u00b4\u00f0\u00d2F9\u00f3[\u0083\u00c5\u00db\u0088\u00ce";
                var5_11 = "\u0018\u000e\u00a4\u00ac\u00b4\u00f0\u00d2F9\u00f3[\u0083\u00c5\u00db\u0088\u00ce".length();
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
        AutoTextModule.d = var6_8;
        AutoTextModule.e = new Long[2];
        AutoTextModule.COMBAT_WINDOW_MS = AutoTextModule.a(7781, 1628477290971699280L);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x634) & Short.MAX_VALUE;
        if (e[n2] == null) {
            AutoTextModule.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
