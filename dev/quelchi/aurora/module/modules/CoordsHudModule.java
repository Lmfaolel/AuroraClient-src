package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.TextHud;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public final class CoordsHudModule
extends TextHud {
    private int lastX;
    private int lastY;
    private int lastZ;
    private List<String> cached;
    private static final String[] d;
    private static final long g;

    public CoordsHudModule() {
        String[] stringArray = d;
        super(stringArray[1], stringArray[2], stringArray[4], 4, 16);
        this.lastX = (int)g;
        this.cached = List.of();
    }

    
    @Override
    protected List<String> lines() {
        CoordsHudModule coordsHudModule;
        block19: {
            block20: {
                int n;
                int n2;
                int n3;
                block15: {
                    block16: {
                        int n4;
                        block18: {
                            boolean bl;
                            block17: {
                                ClientPlayerEntity clientPlayerEntity = MinecraftClient.method_1551().field_1724;
                                bl = AmbienceModule.b;
                                ClientPlayerEntity clientPlayerEntity2 = clientPlayerEntity;
                                if (!bl) {
                                    if (clientPlayerEntity2 == null) {
                                        return List.of();
                                    }
                                    clientPlayerEntity2 = clientPlayerEntity;
                                }
                                n3 = (int)Math.floor(clientPlayerEntity2.method_23317());
                                n2 = (int)Math.floor(clientPlayerEntity.method_23318());
                                n = (int)Math.floor(clientPlayerEntity.method_23321());
                                if (bl) break block15;
                                if (n3 != this.lastX) break block16;
                                n4 = n2;
                                if (bl) break block17;
                                if (n4 != this.lastY) break block16;
                                n4 = n;
                            }
                            if (bl) break block18;
                            if (n4 != this.lastZ) break block16;
                            coordsHudModule = this;
                            if (bl) break block19;
                            n4 = coordsHudModule.cached.isEmpty() ? 1 : 0;
                        }
                        if (n4 == 0) break block20;
                    }
                    this.lastX = n3;
                    this.lastY = n2;
                    this.lastZ = n;
                }
                int n5 = n;
                int n6 = n2;
                int n7 = n3;
                String[] stringArray = d;
                this.cached = List.of(stringArray[0] + n7 + stringArray[3] + n6 + stringArray[5] + n5);
            }
            coordsHudModule = this;
        }
        return coordsHudModule.cached;
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[6];
                    var5_1 = 0;
                    var4_2 = "b\n^OH\u000by<k\u001d\fZU['a\u001c\fb\n^O\u0018\\HS'm\u0000\u0006\u0002\u0016s";
                    var6_3 = "b\n^OH\u000by<k\u001d\fZU['a\u001c\fb\n^O\u0018\\HS'm\u0000\u0006\u0002\u0016s".length();
                    var3_4 = 5;
                    var2_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var2_5;
                        v1 = var4_2.substring(v0, v0 + var3_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        var4_2 = "Y<k\u001d\f@\u0002\u0016s";
                        var6_3 = "Y<k\u001d\f@\u0002\u0016s".length();
                        var3_4 = 6;
                        var2_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var2_5;
                            v1 = var4_2.substring(v4, v4 + var3_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var8_6 = 0;
                do {
                    v6 = v6;
                    v8 = var8_6;
                    v9 = v6[v8];
                    switch (var8_6 % 7) {
                        case 0: {
                            v10 = 58;
                            break;
                        }
                        case 1: {
                            v10 = 83;
                            break;
                        }
                        case 2: {
                            v10 = 4;
                            break;
                        }
                        case 3: {
                            v10 = 111;
                            break;
                        }
                        case 4: {
                            v10 = 104;
                            break;
                        }
                        case 5: {
                            v10 = 51;
                            break;
                        }
                        default: {
                            v10 = 59;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var8_6;
lbl65:

                    v7 = v7;
                } while (v7 > var8_6);
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
            CoordsHudModule.d = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 807655890029179095L;
        while (true)
        CoordsHudModule.g = -6502788752431058729L ^ var0_7;
    }
}
