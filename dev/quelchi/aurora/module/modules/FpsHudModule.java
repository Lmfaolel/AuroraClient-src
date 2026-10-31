package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.TextHud;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.perf.FrameStats;
import java.util.List;
import net.minecraft.client.MinecraftClient;

public final class FpsHudModule
extends TextHud {
    private final BooleanSetting lows;
    private int lastFps;
    private int lastLow;
    private boolean lastLows;
    private List<String> cached;
    private static final String[] d;

    public FpsHudModule() {
        String[] stringArray = d;
        super(stringArray[3], stringArray[4], stringArray[2], 4, 4);
        this.lows = new BooleanSetting(stringArray[1], false);
        this.lastFps = -1;
        this.lastLow = -1;
        this.cached = List.of();
        this.addSettings(this.lows);
    }

    
    @Override
    protected List<String> lines() {
        block26: {
            block29: {
                block27: {
                    block28: {
                        block24: {
                            block25: {
                                block22: {
                                    block23: {
                                        var2_1 = MinecraftClient.method_1551().method_47599();
                                        var3_2 = this.lows.getValue();
                                        var1_3 = AmbienceModule.b;
                                        v0 = var3_2;
                                        if (var1_3) break block22;
                                        if (v0 == 0) break block23;
                                        v0 = FrameStats.onePercentLow();
                                        break block22;
                                    }
                                    v0 = -1;
                                }
                                var4_4 = v0;
                                if (var1_3) break block24;
                                if (var2_1 == this.lastFps) {
                                }
                                v3 = var4_4;
                                if (var1_3) break block25;
                                if (v3 == this.lastLow) {
                                }
                                v3 = var3_2;
                            }
                            if (v3 == this.lastLows) break block26;
lbl45:

                            this.lastFps = var2_1;
                            v7 = this;
                            v8 = var4_4;
                            if (var1_3) break block27;
                            v7.lastLow = v8;
                            this.lastLows = var3_2;
                        }
                        v7 = this;
                        if (var3_2 == 0) break block28;
                        v8 = var4_4;
                        if (var1_3) break block27;
                        if (v8 <= 0) break block28;
                        v13 = var2_1 + FpsHudModule.d[6] + var4_4 + FpsHudModule.d[5];
                        break block29;
                    }
                    v8 = var2_1;
                }
                v13 = v8 + FpsHudModule.d[0];
            }
            v7.cached = List.of(v13);
        }
        return this.cached;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "1]\u001fg\u0006 >oX8%\u0003wk<\u0003WK\u001c\u0011Wi.Y2 \u0001e~oF23\u0004~n;";
                var4_3 = "1]\u001fg\u0006 >oX8%\u0003wk<\u0003WK\u001c\u0011Wi.Y2 \u0001e~oF23\u0004~n;".length();
                var1_4 = 4;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "1w C\u00061]\u001fgwr";
                    var4_3 = "1w C\u00061]\u001fgwr".length();
                    var1_4 = 4;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 17;
                        break;
                    }
                    case 1: {
                        v10 = 27;
                        break;
                    }
                    case 2: {
                        v10 = 79;
                        break;
                    }
                    case 3: {
                        v10 = 52;
                        break;
                    }
                    case 4: {
                        v10 = 87;
                        break;
                    }
                    case 5: {
                        v10 = 82;
                        break;
                    }
                    default: {
                        v10 = 96;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_6;
lbl65:

                v7 = v7;
            } while (v7 > var6_6);
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
        FpsHudModule.d = var5;
    }
}
