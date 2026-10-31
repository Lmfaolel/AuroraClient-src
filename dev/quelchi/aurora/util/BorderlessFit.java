package dev.quelchi.aurora.util;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.mixins.WindowRegionInvoker;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.util.KeyUtil;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Util;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public final class BorderlessFit {
    private static final boolean WINDOWS;
    private static final boolean VULKAN;
    private static boolean enabled;
    private static boolean borderless;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private BorderlessFit() {
    }

    public static boolean enabled() {
        return enabled;
    }

    public static void setEnabled(boolean bl) {
        enabled = bl;
        BorderlessFit.reapply(MinecraftClient.method_1551());
    }

    public static boolean vanilla() {
        boolean bl;
        block9: {
            block7: {
                int n;
                block8: {
                    block6: {
                        n = KeyUtil.a;
                        bl = enabled;
                        if (n != 0) break block6;
                        if (!bl) break block7;
                        bl = WINDOWS;
                    }
                    if (n != 0) break block8;
                    if (!bl) break block7;
                    bl = VULKAN;
                }
                if (n != 0) break block9;
                if (bl) break block7;
                bl = true;
                break block9;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean borderless() {
        return borderless;
    }

    public static void fullscreen(long l, long l2, int n, int n2, int n3, int n4, int n5) {
        int n6;
        block23: {
            block24: {
                GLFWVidMode gLFWVidMode;
                block22: {
                    block20: {
                        block21: {
                            GLFWVidMode gLFWVidMode2;
                            block19: {
                                long l3;
                                block17: {
                                    block18: {
                                        n6 = KeyUtil.a;
                                        l3 = l2;
                                        if (n6 != 0) break block17;
                                        if (l3 != 0L) break block18;
                                        gLFWVidMode2 = null;
                                        break block19;
                                    }
                                    l3 = l2;
                                }
                                gLFWVidMode2 = GLFW.glfwGetVideoMode((long)l3);
                            }
                            gLFWVidMode = gLFWVidMode2;
                            if (n6 != 0) break block20;
                            if (!BorderlessFit.vanilla()) break block21;
                            if (gLFWVidMode != null) break block22;
                        }
                        borderless = false;
                        GLFW.glfwSetWindowMonitor((long)l, (long)l2, (int)n, (int)n2, (int)n3, (int)n4, (int)n5);
                    }
                    return;
                }
                int[] nArray = new int[1];
                int[] nArray2 = new int[1];
                GLFW.glfwGetMonitorPos((long)l2, (int[])nArray, (int[])nArray2);
                GLFW.glfwSetWindowAttrib((long)l, (int)BorderlessFit.a(22418, 9076721517183974567L), (int)0);
                if (n6 != 0) break block23;
                GLFW.glfwSetWindowMonitor((long)l, (long)0L, (int)nArray[0], (int)nArray2[0], (int)gLFWVidMode.width(), (int)(gLFWVidMode.height() + 1), (int)-1);
                if (borderless) break block24;
                Aurora.LOG.info(a[0], (Object)gLFWVidMode.width(), (Object)gLFWVidMode.height());
            }
            borderless = true;
        }
        if (Module.a) {
            KeyUtil.a = ++n6;
        }
    }

    
    public static void windowed(long l, long l2, int n, int n2, int n3, int n4, int n5) {
        boolean bl;
        block9: {
            block10: {
                block8: {
                    int n6;
                    block7: {
                        n6 = KeyUtil.a;
                        bl = borderless;
                        if (n6 != 0) break block7;
                        if (bl) break block8;
                        bl = WINDOWS;
                    }
                    if (n6 != 0) break block9;
                    if (!bl) break block10;
                    bl = VULKAN;
                    if (n6 != 0) break block9;
                    if (bl) break block10;
                }
                GLFW.glfwSetWindowAttrib((long)l, (int)BorderlessFit.a(22418, 9076721517183974567L), (int)1);
            }
            bl = false;
        }
        borderless = bl;
        GLFW.glfwSetWindowMonitor((long)l, (long)l2, (int)n, (int)n2, (int)n3, (int)n4, (int)n5);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void tick(MinecraftClient minecraftClient) {
        long l;
        long l2;
        int n;
        block49: {
            boolean bl;
            block45: {
                block46: {
                    boolean bl2;
                    block48: {
                        long l3;
                        block47: {
                            n = KeyUtil.a;
                            if (minecraftClient.method_22683() == null) {
                                return;
                            }
                            bl = VULKAN;
                            if (n != 0) break block45;
                            if (bl) break block46;
                            l3 = minecraftClient.method_22683().method_4490();
                            bl2 = BorderlessFit.vanilla();
                            if (n != 0) break block47;
                            if (!bl2) return;
                            bl2 = minecraftClient.method_22683().method_4498();
                        }
                        if (n != 0) break block48;
                        if (!bl2) return;
                        long l4 = GLFW.glfwGetWindowMonitor((long)l3) - 0L;
                        bl2 = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                    }
                    if (!bl2) return;
                    BorderlessFit.reapply(minecraftClient);
                    return;
                }
                bl = WINDOWS;
            }
            if (!bl) {
                return;
            }
            l2 = minecraftClient.method_22683().method_4490();
            l = GLFW.glfwGetWindowMonitor((long)l2);
            if (n != 0) break block49;
            if (l != 0L) return;
            l = l2;
        }
        if (GLFW.glfwGetWindowAttrib((long)l, (int)BorderlessFit.a(2372, 4745152949955093104L)) != 0) {
            return;
        }
        PointerBuffer pointerBuffer = GLFW.glfwGetMonitors();
        if (pointerBuffer == null) {
            return;
        }
        int[] nArray = new int[1];
        int[] nArray2 = new int[1];
        int[] nArray3 = new int[1];
        int[] nArray4 = new int[1];
        GLFW.glfwGetWindowPos((long)l2, (int[])nArray, (int[])nArray2);
        GLFW.glfwGetWindowSize((long)l2, (int[])nArray3, (int[])nArray4);
        int[] nArray5 = new int[1];
        int[] nArray6 = new int[1];
        int n2 = 0;
        while (n2 < pointerBuffer.limit()) {
            block55: {
                block52: {
                    int n3;
                    GLFWVidMode gLFWVidMode;
                    block54: {
                        int[] nArray7;
                        block53: {
                            block50: {
                                long l5;
                                block51: {
                                    l5 = pointerBuffer.get(n2);
                                    gLFWVidMode = GLFW.glfwGetVideoMode((long)l5);
                                    if (n != 0) break block50;
                                    if (gLFWVidMode != null) break block51;
                                    break block52;
                                }
                                GLFW.glfwGetMonitorPos((long)l5, (int[])nArray5, (int[])nArray6);
                                if (n != 0) break block55;
                            }
                            if (nArray[0] != nArray5[0]) break block52;
                            nArray7 = nArray2;
                            if (n != 0) break block53;
                            if (nArray7[0] != nArray6[0]) break block52;
                            nArray7 = nArray3;
                        }
                        n3 = nArray7[0];
                        if (n != 0) break block54;
                        if (n3 != gLFWVidMode.width()) break block52;
                        n3 = nArray4[0];
                    }
                    if (n3 == gLFWVidMode.height()) {
                        GLFW.glfwSetWindowSize((long)l2, (int)nArray3[0], (int)(nArray4[0] + 1));
                        return;
                    }
                }
                ++n2;
            }
            if (n == 0) continue;
        }
    }

    private static void reapply(MinecraftClient minecraftClient) {
        MinecraftClient minecraftClient2;
        block18: {
            block19: {
                block15: {
                    boolean bl;
                    block17: {
                        int n;
                        block16: {
                            MinecraftClient minecraftClient3;
                            block14: {
                                n = KeyUtil.a;
                                minecraftClient3 = minecraftClient;
                                if (n != 0) break block14;
                                if (minecraftClient3 == null) break block15;
                                minecraftClient3 = minecraftClient;
                            }
                            if (minecraftClient3.method_22683() == null) break block15;
                            bl = VULKAN;
                            if (n != 0) break block16;
                            if (bl) break block15;
                            bl = WINDOWS;
                        }
                        if (n != 0) break block17;
                        if (!bl) break block15;
                        minecraftClient2 = minecraftClient;
                        if (n != 0) break block18;
                        bl = minecraftClient2.method_22683().method_4498();
                    }
                    if (bl) break block19;
                }
                return;
            }
            minecraftClient2 = minecraftClient;
        }
        minecraftClient2.execute(() -> BorderlessFit.lambda$reapply$0(minecraftClient));
    }

    public static void write(JsonObject jsonObject) {
        jsonObject.addProperty(a[1], Boolean.valueOf(enabled));
    }

    public static void read(JsonObject jsonObject) {
        block5: {
            JsonObject jsonObject2;
            block4: {
                int n = KeyUtil.a;
                jsonObject2 = jsonObject;
                if (n != 0) break block4;
                if (!jsonObject2.has(a[3])) break block5;
                jsonObject2 = jsonObject.get(a[1]);
            }
            enabled = jsonObject2.getAsBoolean();
        }
    }

    private static void lambda$reapply$0(MinecraftClient minecraftClient) {
        ((WindowRegionInvoker)minecraftClient.method_22683()).aurora$updateWindowRegion();
    }

    
    static {
        block25: {
            block24: {
                block23: {
                    block22: {
                        var13 = new String[4];
                        var11_1 = 0;
                        var10_2 = "myKcFI\u001dNiI/TYOJ,\\rMQ\u0012\u000bnH}QO\u001dGiT|\u0015]\u0006EhHx\u0019\n\tD~\u0007|VX\nNb\u0007|]K\u001dBb@\u000fXdF}Pl\u001aG`TlGO\nE";
                        var12_3 = "myKcFI\u001dNiI/TYOJ,\\rMQ\u0012\u000bnH}QO\u001dGiT|\u0015]\u0006EhHx\u0019\n\tD~\u0007|VX\nNb\u0007|]K\u001dBb@\u000fXdF}Pl\u001aG`TlGO\nE".length();
                        var9_4 = 59;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block22;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "]yKdTD\u0002Dh\u000fXdF}Pl\u001aG`TlGO\nE";
                            var12_3 = "]yKdTD\u0002Dh\u000fXdF}Pl\u001aG`TlGO\nE".length();
                            var9_4 = 9;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block22;
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
                            break block23;
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
                                v10 = 43;
                                break;
                            }
                            case 1: {
                                v10 = 12;
                                break;
                            }
                            case 2: {
                                v10 = 39;
                                break;
                            }
                            case 3: {
                                v10 = 15;
                                break;
                            }
                            case 4: {
                                v10 = 53;
                                break;
                            }
                            case 5: {
                                v10 = 42;
                                break;
                            }
                            default: {
                                v10 = 111;
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
                BorderlessFit.a = var13;
                var0_7 = 3390671956787262679L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "%\u0085\u00ca\u0011\u008c\u00d4\u0096\u00a29j\u0006`\u0002\u00c9\u00c8u";
                var5_11 = "%\u0085\u00ca\u0011\u008c\u00d4\u0096\u00a29j\u0006`\u0002\u00c9\u00c8u".length();
                var2_12 = 0;
                while (true) {
                    break block24;
                    break;
                }
lbl84:

                while (true) {
                    var6_8[v11] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block25;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v11 = var3_9++;
            while (true)
        }
        BorderlessFit.b = var6_8;
        BorderlessFit.c = new Integer[2];
        v12 = Util.method_668() == Util.OperatingSystem.field_1133;
        BorderlessFit.WINDOWS = v12;
        BorderlessFit.VULKAN = FabricLoader.getInstance().isModLoaded(BorderlessFit.a[2]);
        BorderlessFit.enabled = false;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6B34;
        if (c[n2] == null) {
            BorderlessFit.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
