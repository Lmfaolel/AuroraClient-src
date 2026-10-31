package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.TimeChangerModule$TimePreset;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

public final class TimeChangerModule
extends Module {
    private static TimeChangerModule instance;
    private final EnumSetting<TimeChangerModule$TimePreset> preset;
    private final NumberSetting custom;
    private static final String[] b;
    private static final long[] d;
    private static final Long[] e;

    public static TimeChangerModule get() {
        return instance;
    }

    public TimeChangerModule() {
        String[] stringArray = b;
        super(stringArray[2], stringArray[3], Category.RENDER, false);
        this.preset = new EnumSetting<TimeChangerModule$TimePreset>(stringArray[0], TimeChangerModule$TimePreset.Day);
        this.custom = new NumberSetting(stringArray[1], 0.0, 24000.0, 6000.0, 100.0);
        instance = this;
        this.addSettings(this.preset, this.custom);
        this.custom.visibleWhen(this::lambda$new$0);
    }

    
    @Override
    public void onTick() {
        long l;
        ClientWorld clientWorld;
        block12: {
            TimeChangerModule timeChangerModule;
            block11: {
                MinecraftClient minecraftClient = MinecraftClient.method_1551();
                clientWorld = minecraftClient.field_1687;
                boolean bl = AmbienceModule.b;
                if (clientWorld == null) {
                    return;
                }
                timeChangerModule = this;
                if (bl) break block11;
                switch (timeChangerModule.preset.getValue().ordinal()) {
                    default: {
                        throw new MatchException(null, null);
                    }
                    case 0: {
                        l = TimeChangerModule.a(18892, 7241299323636050160L);
                        break block12;
                    }
                    case 1: {
                        l = TimeChangerModule.a(20243, 5616173197547630126L);
                        break block12;
                    }
                    case 2: {
                        l = TimeChangerModule.a(22889, 8952447392572831831L);
                        break block12;
                    }
                    case 3: {
                        l = TimeChangerModule.a(4835, 6753876844327580636L);
                        break block12;
                    }
                    case 4: {
                        timeChangerModule = this;
                    }
                }
            }
            l = (long)timeChangerModule.custom.getValue();
        }
        long l2 = l;
        clientWorld.method_29089(clientWorld.method_75260(), l2, false);
    }

    private Boolean lambda$new$0() {
        boolean bl = this.preset.getValue() == TimeChangerModule$TimePreset.Custom;
        return bl;
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[4];
                        var11_1 = 0;
                        var10_2 = "Z\u0018\u001er\fM\u0004\u0000c\u001e\u001auz\u0018\u0010|\u0002";
                        var12_3 = "Z\u0018\u001er\fM\u0004\u0000c\u001e\u001auz\u0018\u0010|\u0002".length();
                        var9_4 = 4;
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
                            var10_2 = "Z\u0018\u001erQ4=o\u001f\u0014r\u0003\u0013A\u0007\u0016e\u0003\u001e1kQ\u0004x\u0003\u001b1.\u0005\u001az\u0014";
                            var12_3 = "Z\u0018\u001erQ4=o\u001f\u0014r\u0003\u0013A\u0007\u0016e\u0003\u001e1kQ\u0004x\u0003\u001b1.\u0005\u001az\u0014".length();
                            var9_4 = 12;
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
                                v10 = 14;
                                break;
                            }
                            case 1: {
                                v10 = 113;
                                break;
                            }
                            case 2: {
                                v10 = 115;
                                break;
                            }
                            case 3: {
                                v10 = 23;
                                break;
                            }
                            case 4: {
                                v10 = 113;
                                break;
                            }
                            case 5: {
                                v10 = 119;
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
                TimeChangerModule.b = var13;
                var0_7 = 3230571364494805311L;
                var6_8 = new long[4];
                var3_9 = 0;
                var4_10 = "a%\u00ecN^\u00a6paH\u00ab\b\u00d4C\u00d9b'";
                var5_11 = "a%\u00ecN^\u00a6paH\u00ab\b\u00d4C\u00d9b'".length();
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
                    var4_10 = "qo\u00dc\u00be\u00f8\u00a2\u00fc\u00b3P\u00e80\u00f2\u009a\u00ef\u00df\u0088";
                    var5_11 = "qo\u00dc\u00be\u00f8\u00a2\u00fc\u00b3P\u00e80\u00f2\u009a\u00ef\u00df\u0088".length();
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
        TimeChangerModule.d = var6_8;
        TimeChangerModule.e = new Long[4];
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x213D) & Short.MAX_VALUE;
        if (e[n2] == null) {
            TimeChangerModule.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
