package dev.quelchi.aurora.prediction.ac;

import dev.quelchi.aurora.prediction.PredictionHandshake;
import dev.quelchi.aurora.prediction.ac.ClickAudit;
import dev.quelchi.aurora.prediction.ac.CombatAudit;
import dev.quelchi.aurora.prediction.ac.InputAudit;
import dev.quelchi.aurora.prediction.ac.MoveAudit;
import dev.quelchi.aurora.prediction.ac.PacketAudit;
import dev.quelchi.aurora.prediction.ac.Win32Memory;
import dev.quelchi.aurora.prediction.ac.Win32Modules;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.Util;
import sun.misc.Unsafe;

public final class AntiCheat {
    private static final Map<String, Integer> vl;
    private static final Map<String, Long> lastSent;
    private static final long SEND_GAP_MS;
    private static final int RESCAN_TICKS;
    private static String injection;
    private static int rescan;
    private static int baseClassPath;
    private static final Set<String> nativeSeen;
    private static boolean memoryScanning;
    private static final String[] CHEAT_MODS;
    private static final String[] CHEAT_CLASSES;
    private static final Class<?>[] WATCHED;
    private static final Pattern MIXIN_HANDLER;
    static final Pattern PROGRAM_FILES_ANY_DRIVE;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private AntiCheat() {
    }

    public static void register() {
        baseClassPath = AntiCheat.appClassPathSize();
        nativeSeen.addAll(Win32Modules.list());
        ClientTickEvents.START_CLIENT_TICK.register(AntiCheat::lambda$register$0);
        ClientTickEvents.END_CLIENT_TICK.register(AntiCheat::lambda$register$1);
        WorldRenderEvents.END_MAIN.register(AntiCheat::lambda$register$2);
        ClientPlayConnectionEvents.DISCONNECT.register(AntiCheat::lambda$register$3);
    }

    private static boolean loaded(MinecraftClient minecraftClient) {
        boolean bl;
        block8: {
            block7: {
                MinecraftClient minecraftClient2;
                int n;
                block6: {
                    n = InputAudit.a;
                    minecraftClient2 = minecraftClient;
                    if (n != 0) break block6;
                    if (minecraftClient2.method_1562() == null) break block7;
                    minecraftClient2 = minecraftClient;
                }
                bl = minecraftClient2.method_1562().method_76760();
                if (n != 0) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static void onPacketSent(Packet<?> packet) {
        MoveAudit.onOwnAction(packet);
        PacketAudit.onSent(packet);
    }

    public static void onServerSlot() {
        PacketAudit.onServerSlot();
    }

    public static void onActivated() {
        PacketAudit.reset();
        ClickAudit.reset();
        InputAudit.reset();
        CombatAudit.reset();
        MoveAudit.reset();
        AntiCheat.checkInjection();
        AntiCheat.checkNative();
    }

    public static void flag(String string, String string2) {
        block10: {
            long l;
            int n;
            block11: {
                int n2;
                int n3;
                block8: {
                    block9: {
                        n3 = InputAudit.a;
                        n2 = PredictionHandshake.isActive();
                        if (n3 != 0) break block8;
                        if (n2 != 0) break block9;
                        return;
                    }
                    n2 = vl.merge(string, 1, Integer::sum);
                }
                n = n2;
                l = System.currentTimeMillis();
                Long l2 = lastSent.get(string);
                if (n3 != 0) break block10;
                if (l2 == null) break block11;
                if (l - l2 >= AntiCheat.a(25416, 7133782347342546015L)) break block11;
                return;
            }
            lastSent.put(string, l);
            PredictionHandshake.sendFlag(string, string2, n);
        }
    }

    private static void checkInjection() {
        boolean bl;
        block18: {
            block19: {
                block17: {
                    int n;
                    block16: {
                        block15: {
                            String string;
                            block14: {
                                block12: {
                                    block13: {
                                        n = InputAudit.a;
                                        string = injection;
                                        if (n != 0) break block12;
                                        if (string != null) break block13;
                                        injection = AntiCheat.findInjection();
                                    }
                                    string = injection;
                                }
                                if (n != 0) break block14;
                                if (string == null) break block15;
                                string = a[38];
                            }
                            AntiCheat.flag(string, injection);
                            return;
                        }
                        bl = memoryScanning;
                        if (n != 0) break block16;
                        if (bl) break block17;
                        bl = FabricLoader.getInstance().isDevelopmentEnvironment();
                    }
                    if (n != 0) break block18;
                    if (!bl) break block19;
                }
                return;
            }
            bl = true;
        }
        memoryScanning = bl;
        Util.method_27958().execute(AntiCheat::lambda$checkInjection$5);
    }

    
    private static String findInjection() {
        block38: {
            block37: {
                block36: {
                    block34: {
                        block33: {
                            block42: {
                                var1 = FabricLoader.getInstance();
                                var0_1 = InputAudit.a;
                                v0 = var1;
                                if (var0_1 != 0) break block33;
                                if (v0.isDevelopmentEnvironment()) // goto
                                break block42;
                                catch (ClassNotFoundException v1) {
                                    throw v1;
                                }
                            }
                            v0 = var1;
                        }
                        if (v0.getEnvironmentType() == EnvType.CLIENT) break block34;
lbl19:

                        return null;
                    }
                    var2_2 = AntiCheat.CHEAT_MODS;
                    var3_4 = var2_2.length;
                    var4_6 = 0;
                    while (var4_6 < var3_4) {
                        block43: {
                            block35: {
                                block44: {
                                    v4 = var2_2[var4_6];
                                    if (var0_1 != 0) // goto
                                    var5_7 = v4;
                                    if (var0_1 != 0) break block43;
                                    if (!var1.isModLoaded(var5_7)) break block35;
                                    break block44;
                                    catch (ClassNotFoundException v5) {
                                        throw v5;
                                    }
                                }
                                return AntiCheat.a[42] + var5_7;
                            }
                            ++var4_6;
                        }
                        if (var0_1 == 0) continue;
                    }
                    var2_2 = AntiCheat.CHEAT_CLASSES;
                    var3_4 = var2_2.length;
                    var4_6 = 0;
                    while (true) {
                        block45: {
                            v7 = var4_6;
                            if (var0_1 != 0) break block36;
                            if (v7 >= var3_4) break;
                            break block45;
                            catch (ClassNotFoundException v8) {
                                throw v8;
                            }
                        }
                        v4 = var2_2[var4_6];
lbl62:

                        var5_7 = v4;
                        try {
                            Class.forName(var5_7, false, AntiCheat.class.getClassLoader());
                            var11_11 = AntiCheat.a;
                            return var11_11[3] + var5_7;
                        }
                        catch (ClassNotFoundException | LinkageError var6_9) {
                            ++var4_6;
                            if (var0_1 == 0) continue;
                        }
                        break;
                    }
                    v7 = AntiCheat.appClassPathSize();
                }
                var2_3 = v7;
                v10 = AntiCheat.baseClassPath;
                if (var0_1 != 0) break block37;
                if (v10 < 0) break block38;
                v10 = var2_3;
            }
            if (v10 > AntiCheat.baseClassPath) {
                return AntiCheat.a[5];
            }
        }
        var3_5 = AntiCheat.WATCHED;
        var4_6 = var3_5.length;
        var5_8 = 0;
        while (var5_8 < var4_6) {
            block40: {
                var6_10 = var3_5[var5_8];
                var7_12 = var6_10.getDeclaredMethods();
                var8_13 = var7_12.length;
                var9_14 = 0;
                while (var9_14 < var8_13) {
                    block39: {
                        block41: {
                            var10_15 = var7_12[var9_14];
                            if (var0_1 != 0) break block39;
                            if (var0_1 != 0) break block40;
                            if (!AntiCheat.MIXIN_HANDLER.matcher(var10_15.getName()).matches()) break block41;
                            return AntiCheat.a[25] + var6_10.getSimpleName();
                        }
                        ++var9_14;
                    }
                    if (var0_1 == 0) continue;
                }
                ++var5_8;
            }
            if (var0_1 == 0) continue;
        }
        return null;
    }

    private static void checkNative() {
        int n = InputAudit.a;
        if (!PredictionHandshake.isActive()) {
            return;
        }
        for (String string : Win32Modules.list()) {
            String string2;
            block9: {
                boolean bl;
                block8: {
                    bl = nativeSeen.add(string);
                    if (n != 0) break block8;
                    if (!bl) continue;
                    string2 = string;
                    if (n != 0) break block9;
                    bl = AntiCheat.trustedNative(string2);
                }
                if (bl) continue;
                string2 = a[32];
            }
            AntiCheat.flag(string2, string);
            if (n == 0) continue;
        }
    }

    private static boolean trustedNative(String string) {
        boolean bl;
        block15: {
            String[] stringArray = new String[13];
            String[] stringArray2 = a;
            stringArray[0] = AntiCheat.env(stringArray2[43]);
            stringArray[1] = AntiCheat.env(stringArray2[29]);
            stringArray[2] = AntiCheat.env(stringArray2[26]);
            stringArray[3] = AntiCheat.env(stringArray2[15]);
            stringArray[4] = AntiCheat.env(stringArray2[14]);
            stringArray[5] = AntiCheat.env(stringArray2[20]) + stringArray2[17];
            stringArray[6] = AntiCheat.env(stringArray2[47]) + stringArray2[48];
            stringArray[7] = AntiCheat.env(stringArray2[47]) + stringArray2[21];
            stringArray[8] = System.getProperty(stringArray2[10]) + stringArray2[16];
            stringArray[9] = System.getProperty(stringArray2[0]);
            stringArray[10] = System.getProperty(stringArray2[4]) + stringArray2[40];
            stringArray[11] = System.getProperty(stringArray2[11]) + stringArray2[18];
            stringArray[12] = FabricLoader.getInstance().getGameDir().toString();
            ArrayList<String> arrayList = new ArrayList<String>(List.of(stringArray));
            String[] stringArray3 = new String[]{stringArray2[7], stringArray2[27]};
            int n = stringArray3.length;
            int n2 = 0;
            int n3 = InputAudit.a;
            while (n2 < n) {
                block12: {
                    block13: {
                        String string2 = stringArray3[n2];
                        String string3 = System.getProperty(string2);
                        if (n3 != 0) break block12;
                        if (string3 == null) break block13;
                        arrayList.addAll(List.of(string3.split(File.pathSeparator)));
                    }
                    ++n2;
                }
                if (n3 == 0) continue;
            }
            for (String string4 : arrayList) {
                block16: {
                    boolean bl2;
                    block17: {
                        String string5;
                        block14: {
                            string5 = string4;
                            if (n3 != 0) break block14;
                            bl = string5.isBlank();
                            if (n3 != 0) break block15;
                            if (bl) break block16;
                            string5 = string;
                        }
                        bl2 = string5.startsWith(string4.toLowerCase());
                        if (n3 != 0) break block17;
                        if (!bl2) break block16;
                        bl2 = true;
                    }
                    return bl2;
                }
                if (n3 == 0) continue;
            }
            bl = PROGRAM_FILES_ANY_DRIVE.matcher(string).matches();
        }
        return bl;
    }

    private static String env(String string) {
        String string2;
        block2: {
            String string3;
            block3: {
                string3 = System.getenv(string);
                int n = InputAudit.a;
                string2 = string3;
                if (n != 0) break block2;
                if (string2 != null) break block3;
                string2 = "";
                break block2;
            }
            string2 = string3;
        }
        return string2;
    }

    private static int appClassPathSize() {
        int n = InputAudit.a;
        try {
            Object object;
            block4: {
                Object object2;
                Unsafe unsafe;
                block5: {
                    String[] stringArray = a;
                    Field field = Unsafe.class.getDeclaredField(stringArray[22]);
                    field.setAccessible(true);
                    unsafe = (Unsafe)field.get(null);
                    object2 = unsafe.getObject(ClassLoader.getSystemClassLoader(), unsafe.objectFieldOffset(Class.forName(stringArray[8]).getDeclaredField(stringArray[44])));
                    object = object2;
                    if (n != 0) break block4;
                    if (object != null) break block5;
                    return -1;
                }
                object = unsafe.getObject(object2, unsafe.objectFieldOffset(object2.getClass().getDeclaredField(a[41])));
            }
            return ((List)object).size();
        }
        catch (Throwable throwable) {
            return -1;
        }
    }

    private static void lambda$checkInjection$5() {
        String string = Win32Memory.scan();
        MinecraftClient.method_1551().execute(() -> AntiCheat.lambda$checkInjection$4(string));
    }

    private static void lambda$checkInjection$4(String string) {
        String string2;
        block6: {
            block7: {
                block5: {
                    int n;
                    block4: {
                        memoryScanning = false;
                        n = InputAudit.a;
                        string2 = string;
                        if (n != 0) break block4;
                        if (string2 == null) break block5;
                        string2 = injection;
                    }
                    if (n != 0) break block6;
                    if (string2 == null) break block7;
                }
                return;
            }
            injection = string;
            string2 = a[2];
        }
        AntiCheat.flag(string2, string);
    }

    private static void lambda$register$3(ClientPlayNetworkHandler clientPlayNetworkHandler, MinecraftClient minecraftClient) {
        vl.clear();
        lastSent.clear();
        PacketAudit.reset();
        ClickAudit.reset();
        InputAudit.reset();
        CombatAudit.reset();
        MoveAudit.reset();
    }

    private static void lambda$register$2(WorldRenderContext worldRenderContext) {
        CombatAudit.frame();
    }

    
    private static void lambda$register$1(MinecraftClient minecraftClient) {
        block20: {
            int n;
            block21: {
                int n2;
                block19: {
                    block18: {
                        block17: {
                            MinecraftClient minecraftClient2;
                            block16: {
                                n2 = InputAudit.a;
                                minecraftClient2 = minecraftClient;
                                if (n2 == 0) {
                                    if (minecraftClient2.field_1724 == null) {
                                        return;
                                    }
                                    minecraftClient2 = minecraftClient;
                                }
                                if (n2 != 0) break block16;
                                if (!AntiCheat.loaded(minecraftClient2)) break block17;
                                PacketAudit.tick(minecraftClient);
                                minecraftClient2 = minecraftClient;
                            }
                            MoveAudit.endTick(minecraftClient2.field_1724);
                            if (n2 == 0) break block18;
                        }
                        MoveAudit.reset();
                    }
                    n = rescan + 1;
                    if (n2 != 0) break block19;
                    rescan = n;
                    if (n < 600) break block20;
                    n = PredictionHandshake.isActive() ? 1 : 0;
                }
                if (n2 != 0) break block21;
                if (n == 0) break block20;
                n = 0;
            }
            rescan = n;
            AntiCheat.checkInjection();
            AntiCheat.checkNative();
        }
    }

    private static void lambda$register$0(MinecraftClient minecraftClient) {
        block9: {
            MinecraftClient minecraftClient2;
            block10: {
                int n;
                block8: {
                    n = InputAudit.a;
                    minecraftClient2 = minecraftClient;
                    if (n != 0) break block8;
                    if (minecraftClient2.field_1724 == null) break block9;
                    minecraftClient2 = minecraftClient;
                }
                if (n != 0) break block10;
                if (!AntiCheat.loaded(minecraftClient2)) break block9;
                minecraftClient2 = minecraftClient;
            }
            MoveAudit.startTick(minecraftClient2.field_1724);
        }
    }

    /*
     * Exception decompiling
     */
    static {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: CONTINUE without a while class org.benf.cfr.reader.bytecode.analysis.parse.statement.AssignmentSimple
         *     at org.benf.cfr.reader.bytecode.analysis.parse.statement.GotoStatement.getTargetStartBlock(GotoStatement.java:102)
         *     at org.benf.cfr.reader.bytecode.analysis.parse.statement.GotoStatement.getStructuredStatement(GotoStatement.java:116)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.getStructuredStatementPlaceHolder(Op03SimpleStatement.java:550)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:727)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:845)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1042)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:929)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:73)
         *     at org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x6717) & Short.MAX_VALUE;
        if (c[n2] == null) {
            AntiCheat.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
