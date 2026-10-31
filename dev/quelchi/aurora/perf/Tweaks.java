package dev.quelchi.aurora.perf;

import dev.quelchi.aurora.module.modules.OptimizersModule;
import java.util.function.Predicate;
import net.fabricmc.loader.api.FabricLoader;

public final class Tweaks {
    private static final boolean ALL;
    public static final boolean LANGUAGE;
    public static final boolean PACK_ICONS;
    public static final boolean PACK_SCAN;
    public static final boolean STATE_TABLES;
    public static final boolean ZIP_INDEX;
    public static final boolean SERVER_PINGS;
    public static final boolean CAMERA_SUBMERSION;
    public static final boolean ENTITY_CULL;
    public static final boolean BLOCK_ENTITY_CULL;
    public static final boolean PARTICLE_CULL;
    public static final boolean BACKGROUND_FPS;
    public static final boolean FRAME_PACING;
    public static final boolean FAR_DETAIL;
    public static final boolean LOW_LATENCY;
    public static final boolean WIN_TUNING;
    public static final boolean SIDEBAR_ROWS;
    public static final boolean INPUT_POLL;
    public static final boolean FULL_CUBE;
    public static final boolean PATH_TYPES;
    public static int a;
    private static final String[] b;

    private Tweaks() {
    }

    public static boolean entityCull() {
        boolean bl;
        block8: {
            block7: {
                int n;
                block6: {
                    n = a;
                    bl = ENTITY_CULL;
                    if (n != 0) break block6;
                    if (!bl) break block7;
                    bl = Tweaks.live(OptimizersModule::entityCull);
                }
                if (n != 0) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean blockEntityCull() {
        boolean bl;
        block8: {
            block7: {
                int n;
                block6: {
                    n = a;
                    bl = BLOCK_ENTITY_CULL;
                    if (n != 0) break block6;
                    if (!bl) break block7;
                    bl = Tweaks.live(OptimizersModule::blockEntityCull);
                }
                if (n != 0) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean particleCull() {
        boolean bl;
        block8: {
            block7: {
                int n;
                block6: {
                    n = a;
                    bl = PARTICLE_CULL;
                    if (n != 0) break block6;
                    if (!bl) break block7;
                    bl = Tweaks.live(OptimizersModule::particleCull);
                }
                if (n != 0) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean backgroundFps() {
        boolean bl;
        block8: {
            block7: {
                int n;
                block6: {
                    n = a;
                    bl = BACKGROUND_FPS;
                    if (n != 0) break block6;
                    if (!bl) break block7;
                    bl = Tweaks.live(OptimizersModule::backgroundFps);
                }
                if (n != 0) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean framePacing() {
        boolean bl;
        block8: {
            block7: {
                int n;
                block6: {
                    n = a;
                    bl = FRAME_PACING;
                    if (n != 0) break block6;
                    if (!bl) break block7;
                    bl = Tweaks.live(OptimizersModule::framePacing);
                }
                if (n != 0) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean farDetail() {
        boolean bl;
        block8: {
            block7: {
                int n;
                block6: {
                    n = a;
                    bl = FAR_DETAIL;
                    if (n != 0) break block6;
                    if (!bl) break block7;
                    bl = Tweaks.live(OptimizersModule::farDetail);
                }
                if (n != 0) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean lowLatency() {
        boolean bl;
        block8: {
            block7: {
                int n;
                block6: {
                    n = a;
                    bl = LOW_LATENCY;
                    if (n != 0) break block6;
                    if (!bl) break block7;
                    bl = Tweaks.live(OptimizersModule::lowLatency);
                }
                if (n != 0) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean live(Predicate<OptimizersModule> predicate) {
        boolean bl;
        block5: {
            block6: {
                block4: {
                    OptimizersModule optimizersModule = OptimizersModule.get();
                    int n = a;
                    if (optimizersModule == null) break block4;
                    bl = predicate.test(optimizersModule);
                    if (n != 0) break block5;
                    if (!bl) break block6;
                }
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean on(String string, String ... stringArray) {
        block7: {
            if (ALL && !b[0].equalsIgnoreCase(System.getProperty(b[30] + string))) break block7;
            return false;
        }
        for (String string2 : stringArray) {
            if (!FabricLoader.getInstance().isModLoaded(string2)) continue;
            return false;
        }
        return true;
    }

    
    static {
        block27: {
            block26: {
                block25: {
                    block24: {
                        var5 = new String[40];
                        var3_1 = 0;
                        var2_2 = "nP$\rdX6 \u000b\u0011\u0012tZ. \u0011\u000f\u000f`E;'\u001cE\u0001`U)d\f\u000b\u0010o\bmW,.\n\t\u0016d\u0010cW&&\u000f\u001c\u0018l_8(\u000b\u0001\u001eoE\tqW!\"R\u001b\u0012`X\u0006fX'=\n\u0005\u000ecW!\"\u0018\u001a\u001etX&d\u0019\u0018\u0002\tpC+*\u0014\u0018\u0010b]\tgC.%R\u000b\u0004cS\nqW!\"R\u0001\u0012nX1\u000elY&,\r\u0006\u0017hNo$\t\u001d\u0002\u0007m_6!\u0016\u001d\u001c\u000bdX6 \u000b\u0011\\bC.%\tlY&,\r\u0006\u0017hN\fr_&,\u001d\t\u0003,D->\f\frS0?\u001a\u001a\\q_,.\f\rqW0=\u0016\u000b\u001dd\u001b!<\u0013\u0004\rdX6 \u000b\u0011\u0012tZ. \u0011\u000f\u0003nP$\frB#=\u001aE\u0005`T.,\f\t{_2d\u0016\u0006\u0015dN\u000fgW1=\f\r\u0003wS09\u0016\u0006\u0016r\ngW0d\u001b\r\u0005`_.\u000bgS0;\u0016\u001c\u0014bY0,\u000beO,(\u0012\u0001\u0012^P2:\nv_,d\u000b\u001d\u001fhX%\r`C0&\r\t_uA'(\u0014\u001b\u000f`E;'\u001cE\u0001`U)d\f\u000b\u0010o\u000fh[/,\u001b\u0001\u0010uS.0\u0019\t\u0002u\u000e`C0&\r\t_uA'(\u0014\u001b_\u0014mW80R\u0004\u0010oQ7(\u0018\r\\mY#-\u001a\u001a\u0010`C0&\r\t_hX2<\u000b\u0018\u001emZ\nhX2<\u000bE\u0001nZ.\u000bmY5d\u0013\t\u0005dX!0\nqW6!R\u001c\bqS1\u0011cZ-*\u0014E\u0014oB+=\u0006E\u0012tZ.\fgD#$\u001aE\u0001`U+'\u0018";
                        var4_3 = "nP$\rdX6 \u000b\u0011\u0012tZ. \u0011\u000f\u000f`E;'\u001cE\u0001`U)d\f\u000b\u0010o\bmW,.\n\t\u0016d\u0010cW&&\u000f\u001c\u0018l_8(\u000b\u0001\u001eoE\tqW!\"R\u001b\u0012`X\u0006fX'=\n\u0005\u000ecW!\"\u0018\u001a\u001etX&d\u0019\u0018\u0002\tpC+*\u0014\u0018\u0010b]\tgC.%R\u000b\u0004cS\nqW!\"R\u0001\u0012nX1\u000elY&,\r\u0006\u0017hNo$\t\u001d\u0002\u0007m_6!\u0016\u001d\u001c\u000bdX6 \u000b\u0011\\bC.%\tlY&,\r\u0006\u0017hN\fr_&,\u001d\t\u0003,D->\f\frS0?\u001a\u001a\\q_,.\f\rqW0=\u0016\u000b\u001dd\u001b!<\u0013\u0004\rdX6 \u000b\u0011\u0012tZ. \u0011\u000f\u0003nP$\frB#=\u001aE\u0005`T.,\f\t{_2d\u0016\u0006\u0015dN\u000fgW1=\f\r\u0003wS09\u0016\u0006\u0016r\ngW0d\u001b\r\u0005`_.\u000bgS0;\u0016\u001c\u0014bY0,\u000beO,(\u0012\u0001\u0012^P2:\nv_,d\u000b\u001d\u001fhX%\r`C0&\r\t_uA'(\u0014\u001b\u000f`E;'\u001cE\u0001`U)d\f\u000b\u0010o\u000fh[/,\u001b\u0001\u0010uS.0\u0019\t\u0002u\u000e`C0&\r\t_uA'(\u0014\u001b_\u0014mW80R\u0004\u0010oQ7(\u0018\r\\mY#-\u001a\u001a\u0010`C0&\r\t_hX2<\u000b\u0018\u001emZ\nhX2<\u000bE\u0001nZ.\u000bmY5d\u0013\t\u0005dX!0\nqW6!R\u001c\bqS1\u0011cZ-*\u0014E\u0014oB+=\u0006E\u0012tZ.\fgD#$\u001aE\u0001`U+'\u0018".length();
                        var1_4 = 3;
                        var0_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var0_5;
                            v1 = var2_2.substring(v0, v0 + var1_4);
                            v2 = -1;
                            break block24;
                            break;
                        }
lbl12:

                        while (true) {
                            var5[var3_1++] = v3.intern();
                            if ((var0_5 += var1_4) < var4_3) {
                                var1_4 = var2_2.charAt(var0_5);
                                continue;
                            }
                            var2_2 = "m_6!\u0016\u001d\u001c\u0011bW/,\r\t\\rC $\u001a\u001a\u0002hY,";
                            var4_3 = "m_6!\u0016\u001d\u001c\u0011bW/,\r\t\\rC $\u001a\u001a\u0002hY,".length();
                            var1_4 = 7;
                            var0_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var0_5;
                                v1 = var2_2.substring(v4, v4 + var1_4);
                                v2 = 0;
                                break block24;
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
                            break block25;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var6_7 = 0;
                    do {
                        v6 = v6;
                        v8 = var6_7;
                        v9 = v6[v8];
                        switch (var6_7 % 7) {
                            case 0: {
                                v10 = 1;
                                break;
                            }
                            case 1: {
                                v10 = 54;
                                break;
                            }
                            case 2: {
                                v10 = 66;
                                break;
                            }
                            case 3: {
                                v10 = 73;
                                break;
                            }
                            case 4: {
                                v10 = 127;
                                break;
                            }
                            case 5: {
                                v10 = 104;
                                break;
                            }
                            default: {
                                v10 = 113;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var6_7;
lbl65:

                        v7 = v7;
                    } while (v7 > var6_7);
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
                Tweaks.b = var5;
                v11 = Tweaks.b[19].equalsIgnoreCase(System.getProperty(Tweaks.b[27])) == false;
                Tweaks.ALL = v11;
                Tweaks.LANGUAGE = Tweaks.on(Tweaks.b[3], new String[]{Tweaks.b[31]});
                Tweaks.PACK_ICONS = Tweaks.on(Tweaks.b[10], new String[]{Tweaks.b[2]});
                Tweaks.PACK_SCAN = Tweaks.on(Tweaks.b[5], new String[]{Tweaks.b[28]});
                Tweaks.STATE_TABLES = Tweaks.on(Tweaks.b[20], new String[]{Tweaks.b[24]});
                Tweaks.ZIP_INDEX = Tweaks.on(Tweaks.b[21], new String[]{Tweaks.b[8], Tweaks.b[14], Tweaks.b[11]});
                Tweaks.SERVER_PINGS = Tweaks.on(Tweaks.b[16], new String[]{Tweaks.b[22]});
                Tweaks.CAMERA_SUBMERSION = Tweaks.on(Tweaks.b[39], new String[]{Tweaks.b[4]});
                Tweaks.ENTITY_CULL = Tweaks.on(Tweaks.b[13], new String[]{Tweaks.b[18]});
                Tweaks.BLOCK_ENTITY_CULL = Tweaks.on(Tweaks.b[36], new String[]{Tweaks.b[1]});
                Tweaks.PARTICLE_CULL = Tweaks.on(Tweaks.b[17], new String[0]);
                Tweaks.BACKGROUND_FPS = Tweaks.on(Tweaks.b[7], new String[]{Tweaks.b[25]});
                Tweaks.FRAME_PACING = Tweaks.on(Tweaks.b[37], new String[0]);
                Tweaks.FAR_DETAIL = Tweaks.on(Tweaks.b[23], new String[0]);
                Tweaks.LOW_LATENCY = Tweaks.on(Tweaks.b[34], new String[0]);
                Tweaks.WIN_TUNING = Tweaks.on(Tweaks.b[26], new String[0]);
                Tweaks.SIDEBAR_ROWS = Tweaks.on(Tweaks.b[15], new String[]{Tweaks.b[29], Tweaks.b[6]});
                if (!Tweaks.on(Tweaks.b[33], new String[0]) || !Boolean.getBoolean(Tweaks.b[32])) break block26;
                v14 = true;
                break block27;
            }
            v14 = false;
        }
        Tweaks.INPUT_POLL = v14;
        var0_6 = Tweaks.b;
        Tweaks.FULL_CUBE = Tweaks.on(var0_6[9], new String[]{var0_6[12]});
        Tweaks.PATH_TYPES = Tweaks.on(var0_6[35], new String[]{var0_6[38]});
    }
}
