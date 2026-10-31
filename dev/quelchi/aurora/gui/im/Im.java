package dev.quelchi.aurora.gui.im;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.gui.im.Im$Glyph;
import dev.quelchi.aurora.gui.im.Im$Lib;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.ImVk;
import dev.quelchi.aurora.gui.im.Themes;
import dev.quelchi.aurora.gui.im.Ui;
import imgui.ImDrawData;
import imgui.ImFont;
import imgui.ImFontAtlas;
import imgui.ImFontConfig;
import imgui.ImFontGlyph;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.ImGuiStyle;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.internal.ImGuiContext;
import imgui.type.ImInt;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.Buffer;
import java.nio.ShortBuffer;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public final class Im {
    public static final float[] INK;
    public static final float[] SUB;
    public static final float[] MUTE;
    public static final float[] GLASS;
    public static final float[] RIM;
    public static final float[] RIM_HI;
    public static final float[] WASH;
    public static final float[] WASH_HI;
    public static final float[] QUIET;
    public static final float[] QUIET_HI;
    public static final float[] WELL;
    public static final float[] GREEN;
    public static final float[] WARN;
    public static final float[] DANGER;
    public static final float[] BG;
    public static final float[] POP;
    public static final float[] DIM;
    public static final float[] TINT;
    public static final float[] TRACK;
    public static final float[] KNOB;
    public static final float[] ON_FILL;
    public static final float[] WINDOW;
    public static final float[] WIN_LINE;
    public static final float[] LINE;
    public static final float[] SURFACE;
    public static final float[] SURFACE_LINE;
    public static final float[] FIELD;
    public static final float[] FIELD_LINE;
    public static final float[] PANE;
    public static final float[] PANE_LINE;
    public static final float[] CHIP_ON;
    public static final float[] CHIP_ON_INK;
    private static float bake;
    private static float wanted;
    private static int settled;
    private static ImScreen baked;
    private static final double[] CURSOR_X;
    private static final double[] CURSOR_Y;
    private static float scale;
    private static ImGuiImplGlfw glfw;
    private static ImGuiImplGl3 gl3;
    private static final boolean GL;
    private static boolean up;
    public static ImFont body;
    public static ImFont bold;
    public static ImFont title;
    public static ImFont sub;
    public static ImFont bold13;
    public static ImFont label;
    public static ImFont huge;
    public static ImFont h3;
    public static ImFont head;
    public static ImFont word;
    public static ImFont sora16;
    public static ImFont sora14;
    public static ImFont sora14r;
    public static ImFont sora13;
    public static ImFont sora12;
    public static ImFont sora12r;
    public static ImFont mc;
    private static long frames;
    private static boolean drawing;
    private static ImGuiContext ctx;
    private static boolean flat;
    private static int guiScale;
    private static final short[] EXTRA;
    private static ShortBuffer ranges;
    private static final StringBuilder seen;
    private static final BitSet known;
    private static boolean grew;
    private static final String WIN;
    private static final String[] HAN;
    private static final String[] HANGUL;
    private static Path fontDir;
    public static boolean a;
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;
    private static final long[] e;
    private static final Long[] f;

    private Im() {
    }

    public static float bake() {
        return bake;
    }

    public static float scale() {
        return scale;
    }

    public static boolean available() {
        return Im$Lib.THERE;
    }

    public static boolean vulkan() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = ImScreen.a;
                bl = GL;
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    public static long frames() {
        return frames;
    }

    
    public static void frame() {
        Screen screen2;
        boolean bl = ImScreen.a;
        if (drawing) {
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        ++frames;
        Screen screen = screen2 = minecraftClient.field_1755;
        if (!bl) {
            if (!(screen instanceof ImScreen)) return;
            screen = screen2;
        }
        ImScreen imScreen = (ImScreen)screen;
        if (bl) {
            return;
        }
        if (!up) {
            Im.init(minecraftClient);
        }
        screen2 = Im.enter();
        try {
            Im.drawFrame(minecraftClient, imScreen);
            return;
        }
        finally {
            ImGui.setCurrentContext((ImGuiContext)screen2);
        }
    }

    
    private static void drawFrame(MinecraftClient var0, ImScreen var1_1) {
        block84: {
            block82: {
                block83: {
                    block80: {
                        block81: {
                            block79: {
                                block78: {
                                    block77: {
                                        block72: {
                                            block76: {
                                                block75: {
                                                    block73: {
                                                        block74: {
                                                            block70: {
                                                                block71: {
                                                                    block68: {
                                                                        block69: {
                                                                            block66: {
                                                                                block65: {
                                                                                    var3_2 = 0;
                                                                                    var2_3 = ImScreen.a;
                                                                                    var4_4 = 0;
                                                                                    if (var2_3) break block65;
                                                                                    if (Im.GL) {
                                                                                    }
                                                                                    var3_2 = GL30.glGetInteger((int)Im.a(27165, 9167926471390677535L));
                                                                                    var4_4 = GL30.glGetInteger((int)Im.a(14016, 6280846289376843463L));
                                                                                    GL30.glBindFramebuffer((int)Im.a(323, 2794478527218557253L), (int)0);
                                                                                    Im.gl3.newFrame();
                                                                                }
                                                                                if (!var2_3) break block66;
lbl18:

                                                                                Ui.frame();
                                                                            }
                                                                            Im.glfw.newFrame();
                                                                            var5_5 = ImGui.getIO();
                                                                            for (var6_6 = 0; var6_6 < 3; ++var6_6) {
                                                                                v2 = var0;
                                                                                if (!var2_3) {
                                                                                    if (GLFW.glfwGetMouseButton((long)v2.method_22683().method_4490(), (int)var6_6) != 0) continue;
                                                                                }
                                                                                var5_5.addMouseButtonEvent(var6_6, false);
                                                                                continue;
                                                                            }
                                                                            for (var6_6 = 340; var6_6 <= 347; ++var6_6) {
                                                                                block67: {
                                                                                    v2 = var0;
lbl42:

                                                                                    v5 = GLFW.glfwGetKey((long)v2.method_22683().method_4490(), (int)var6_6);
                                                                                    if (var2_3) break block67;
                                                                                    if (v5 != 0) continue;
                                                                                    v5 = var6_6;
                                                                                }
                                                                                Im.key(v5, 0, 0, 0);
                                                                                if (!var2_3) continue;
                                                                            }
                                                                            var6_7 = var5_5.getDisplaySizeX();
                                                                            var7_8 = var5_5.getDisplaySizeY();
                                                                            v7 = 0.5f;
                                                                            v8 = var6_7;
                                                                            v9 = 1280.0f;
                                                                            if (var2_3) break block68;
                                                                            Im.scale = Math.max(v7, Math.min(v8 / v9, var7_8 / 720.0f) * Themes.size());
                                                                            if (!var1_1.vanillaSize()) break block69;
                                                                            Im.scale = Math.max(0.5f, (float)var0.method_22683().method_4495() / var5_5.getDisplayFramebufferScaleX() / 1.7f);
                                                                        }
                                                                        v7 = 4.0f;
                                                                        v8 = Im.scale;
                                                                        v9 = var5_5.getDisplayFramebufferScaleX();
                                                                    }
                                                                    var8_9 = Math.min(v7, v8 * v9);
                                                                    v12 = var8_9;
                                                                    if (var2_3) break block70;
                                                                    if (!(v12 <= 0.0f)) break block71;
                                                                    var8_9 = Im.bake;
                                                                }
                                                                v12 = var8_9;
                                                            }
                                                            v14 = Im.wanted;
                                                            if (var2_3) break block72;
                                                            if (v12 != v14) {
                                                            }
                                                            v16 = Im.wanted;
                                                            if (var2_3) break block73;
                                                            if (v16 == 0.0f) break block74;
                                                            if (var1_1 == Im.baked) break block75;
                                                            v20 = var8_9;
                                                            if (var2_3) break block76;
                                                            if (!(v20 > 0.0f)) break block75;
                                                        }
                                                        v16 = var8_9;
                                                    }
                                                    Im.fonts(v16);
                                                }
                                                v20 = var8_9;
                                            }
                                            Im.wanted = v20;
                                            Im.settled = 0;
                                            if (!var2_3) break block77;
lbl136:

                                            v12 = Math.abs(var8_9 - Im.bake);
                                            v14 = 0.01f;
                                        }
                                        cfr_temp_0 = v12 - v14;
                                        v25 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
                                        if (var2_3) break block78;
                                        if (v25 <= 0) break block77;
                                        v25 = Im.settled + 1;
                                        if (var2_3) break block78;
                                        Im.settled = (int)v25;
                                        if (v25 < 8) break block77;
                                        Im.fonts(var8_9);
                                    }
                                    Im.baked = var1_1;
                                    v25 = (float)Im.grew;
                                }
                                if (var2_3) break block79;
                                if (v25 == false) {
                                }
                                v31 = 1;
                                if (var2_3) break block80;
                                v25 = Math.max(v31, var0.method_22683().method_4495());
                            }
                            if (v25 == Im.guiScale) break block81;
lbl192:

                            Im.fonts(Im.bake);
                        }
                        v31 = (cfr_temp_1 = Im.scale - 1.0f) == 0.0f ? 0 : (cfr_temp_1 > 0.0f ? 1 : -1);
                    }
                    if (var2_3) break block82;
                    if (v31 == 0) break block83;
                    var5_5.setDisplaySize(var6_7 / Im.scale, var7_8 / Im.scale);
                    var5_5.setDisplayFramebufferScale(var5_5.getDisplayFramebufferScaleX() * Im.scale, var5_5.getDisplayFramebufferScaleY() * Im.scale);
                    GLFW.glfwGetCursorPos((long)var0.method_22683().method_4490(), (double[])Im.CURSOR_X, (double[])Im.CURSOR_Y);
                    var5_5.addMousePosEvent((float)(Im.CURSOR_X[0] / (double)Im.scale), (float)(Im.CURSOR_Y[0] / (double)Im.scale));
                }
                ImGui.newFrame();
                Im.drawing = true;
                var1_1.draw();
                Im.drawing = false;
                if (var2_3) break block84;
                ImGui.render();
                Im.rows(ImGui.getDrawData());
                v31 = (int)Im.GL;
            }
            if (v31 == 0) {
                ImVk.draw(ImGui.getDrawData());
                return;
            }
            Im.gl3.renderDrawData(ImGui.getDrawData());
            GL30.glBindFramebuffer((int)Im.a(10248, 6022019416351606793L), (int)var3_2);
            GL30.glBindFramebuffer((int)Im.a(21860, 1918255304694855012L), (int)var4_4);
        }
    }

    private static void rows(ImDrawData imDrawData) {
        long l;
        float f;
        boolean bl;
        float f2;
        long l2;
        int n;
        block20: {
            long l3;
            block21: {
                block18: {
                    block19: {
                        long l4;
                        block17: {
                            l4 = imDrawData.ptr;
                            n = MemoryUtil.memGetInt((long)(l4 + Im.b(9310, 4317690803734964265L)));
                            l2 = MemoryUtil.memGetAddress((long)(l4 + Im.b(14046, 1747225816967510704L)));
                            f2 = MemoryUtil.memGetFloat((long)(l4 + Im.b(24177, 1196642293762953755L)));
                            bl = ImScreen.a;
                            f = MemoryUtil.memGetFloat((long)(l4 + Im.b(4013, 3305893552627106752L)));
                            long l5 = l2 - 0L;
                            l3 = l5 == 0L ? 0 : (l5 < 0L ? -1 : 1);
                            if (bl) break block17;
                            if (l3 == false) break block18;
                            l3 = n;
                        }
                        if (bl) break block19;
                        if (l3 != MemoryUtil.memGetInt((long)(l4 + Im.b(30064, 3107033248681146642L)))) break block18;
                        float f3 = f - 0.0f;
                        l3 = f3 == 0.0f ? 0 : (f3 > 0.0f ? 1 : -1);
                    }
                    if (bl) break block20;
                    if (l3 > 0) break block21;
                }
                return;
            }
            l3 = l = (long)0;
        }
        while (l < n) {
            long l6 = l2;
            long l7 = Im.b(2956, 6857458196781484005L);
            long l8 = l;
            block13: while (true) {
                long l9 = MemoryUtil.memGetAddress((long)(l6 + l7 * l8));
                int n2 = MemoryUtil.memGetInt((long)(l9 + Im.b(1868, 3214260758966848298L)));
                long l10 = MemoryUtil.memGetAddress((long)(l9 + Im.b(28215, 7613627163737104984L)));
                block14: while (true) {
                    long l11 = l10;
                    int n3 = 0;
                    while (n3 + 3 < n2) {
                        long l12;
                        long l13;
                        block22: {
                            block23: {
                                l10 = l13 = l11 + Im.b(24265, 3631208257310233253L) * (long)n3;
                                if (bl) continue block14;
                                if (bl) break block22;
                                if (Im.glyph(l10)) break block23;
                                ++n3;
                                if (!bl) continue;
                            }
                            l12 = l13 + Im.b(9310, 4317690803734964265L);
                        }
                        float f4 = (MemoryUtil.memGetFloat((long)l12) - f2) * f;
                        float f5 = ((float)Math.round(f4) - f4) / f;
                        for (int i = 0; i < 4; ++i) {
                            l6 = l13 + Im.b(24265, 3631208257310233253L) * (long)i + Im.b(9310, 4317690803734964265L);
                            l7 = l13;
                            l8 = Im.b(24265, 3631208257310233253L) * (long)i;
                            if (bl) continue block13;
                            MemoryUtil.memPutFloat((long)l6, (float)(MemoryUtil.memGetFloat((long)(l7 + l8 + Im.b(9310, 4317690803734964265L))) + f5));
                            if (!bl) continue;
                        }
                        n3 += 4;
                        if (!bl) continue;
                    }
                    break block13;
                    break;
                }
                break;
            }
            ++l;
            if (!bl) continue;
        }
    }

    private static boolean glyph(long l) {
        boolean bl;
        block48: {
            block39: {
                long l2;
                float f;
                boolean bl2;
                block47: {
                    float f2;
                    long l3;
                    long l4;
                    block46: {
                        float f3;
                        float f4;
                        block45: {
                            long l5;
                            float f5;
                            block44: {
                                float f6;
                                long l6;
                                block43: {
                                    float f7;
                                    block42: {
                                        block41: {
                                            block40: {
                                                block38: {
                                                    l4 = l + Im.b(7919, 5405771104586814092L);
                                                    l6 = l + Im.b(23770, 2779702885305487534L);
                                                    l3 = l + Im.b(1916, 4120933811361106701L);
                                                    f5 = MemoryUtil.memGetFloat((long)(l + Im.b(25075, 656606701283487126L)));
                                                    f6 = MemoryUtil.memGetFloat((long)(l4 + Im.b(2956, 6857458196781484005L)));
                                                    f4 = MemoryUtil.memGetFloat((long)(l + Im.b(23732, 5739449381297459408L)));
                                                    f2 = MemoryUtil.memGetFloat((long)(l6 + Im.b(3350, 666535394650079606L)));
                                                    bl2 = ImScreen.a;
                                                    f = f5;
                                                    if (bl2) break block38;
                                                    if (f == f6) break block39;
                                                    f = f4;
                                                }
                                                if (bl2) break block40;
                                                if (f == f2) break block39;
                                                f = MemoryUtil.memGetFloat((long)(l + Im.b(7716, 6083946485714244163L)));
                                            }
                                            if (bl2) break block41;
                                            if (f != MemoryUtil.memGetFloat((long)(l4 + Im.b(9310, 4317690803734964265L)))) break block39;
                                            f = MemoryUtil.memGetFloat((long)l4);
                                        }
                                        f7 = MemoryUtil.memGetFloat((long)l6);
                                        if (bl2) break block42;
                                        if (f != f7) break block39;
                                        f = MemoryUtil.memGetFloat((long)(l6 + Im.b(9310, 4317690803734964265L)));
                                        l5 = l3;
                                        if (bl2) break block43;
                                        f7 = MemoryUtil.memGetFloat((long)(l5 + Im.b(9310, 4317690803734964265L)));
                                    }
                                    if (f != f7) break block39;
                                    f = MemoryUtil.memGetFloat((long)l3);
                                    l5 = l;
                                }
                                if (bl2) break block44;
                                if (f != MemoryUtil.memGetFloat((long)l5)) break block39;
                                f = f6;
                                l5 = l6 + Im.b(2956, 6857458196781484005L);
                            }
                            f3 = MemoryUtil.memGetFloat((long)l5);
                            if (bl2) break block45;
                            if (f != f3) break block39;
                            f = f5;
                            if (bl2) break block46;
                            f3 = MemoryUtil.memGetFloat((long)(l3 + Im.b(2956, 6857458196781484005L)));
                        }
                        if (f != f3) break block39;
                        f = f4;
                    }
                    l2 = l4;
                    if (bl2) break block47;
                    if (f != MemoryUtil.memGetFloat((long)(l2 + Im.b(3350, 666535394650079606L)))) break block39;
                    f = f2;
                    l2 = l3 + Im.b(3350, 666535394650079606L);
                }
                float f8 = f - MemoryUtil.memGetFloat((long)l2);
                bl = f8 == 0.0f ? 0 : (f8 > 0.0f ? 1 : -1);
                if (bl2) break block48;
                if (bl) break block39;
                bl = true;
                break block48;
            }
            bl = false;
        }
        return bl;
    }

    private static ImGuiContext enter() {
        ImGuiContext imGuiContext = ImGui.getCurrentContext();
        ImGui.setCurrentContext((ImGuiContext)ctx);
        return imGuiContext;
    }

    private static void in(Runnable runnable) {
        if (!up) {
            return;
        }
        ImGuiContext imGuiContext = Im.enter();
        try {
            runnable.run();
        }
        finally {
            ImGui.setCurrentContext((ImGuiContext)imGuiContext);
        }
    }

    public static void mouse(int n, boolean bl) {
        Im.in(() -> Im.lambda$mouse$0(n, bl));
    }

    public static void scroll(double d, double d2) {
        Im.in(() -> Im.lambda$scroll$1(d, d2));
    }

    public static void key(int n, int n2, int n3, int n4) {
        Im.in(() -> Im.lambda$key$2(n, n2, n3, n4));
    }

    public static void character(int n) {
        Im.need(Character.toString(n));
        Im.in(() -> Im.lambda$character$3(n));
    }

    public static boolean typing() {
        block6: {
            boolean bl;
            block5: {
                boolean bl2 = ImScreen.a;
                bl = up;
                if (bl2) break block5;
                if (bl) break block6;
                bl = false;
            }
            return bl;
        }
        ImGuiContext imGuiContext = Im.enter();
        try {
            boolean bl = ImGui.getIO().getWantTextInput();
            return bl;
        }
        finally {
            ImGui.setCurrentContext((ImGuiContext)imGuiContext);
        }
    }

    
    private static void init(MinecraftClient minecraftClient) {
        ImGuiContext imGuiContext = ImGui.getCurrentContext();
        ctx = ImGui.createContext();
        boolean bl = ImScreen.a;
        ImGui.setCurrentContext((ImGuiContext)ctx);
        try {
            block10: {
                ImGuiIO imGuiIO;
                block8: {
                    block9: {
                        imGuiIO = ImGui.getIO();
                        if (bl) break block8;
                        imGuiIO.setIniFilename(null);
                        Im.fonts(bake);
                        glfw = new ImGuiImplGlfw();
                        glfw.init(minecraftClient.method_22683().method_4490(), false);
                        if (!GL) break block9;
                        gl3 = new ImGuiImplGl3();
                        gl3.init(b[20]);
                        if (!bl) break block10;
                    }
                    imGuiIO = ImGui.getIO();
                }
                imGuiIO.addBackendFlags(8);
            }
            Im.style();
        }
        finally {
            ImGui.setCurrentContext((ImGuiContext)imGuiContext);
        }
        up = true;
    }

    
    private static void fonts(float var0) {
        block85: {
            block84: {
                block82: {
                    block83: {
                        block81: {
                            block79: {
                                block80: {
                                    block74: {
                                        block73: {
                                            block66: {
                                                block64: {
                                                    block65: {
                                                        block86: {
                                                            Im.bake = var0;
                                                            var2_1 = Im.guiScale = Math.max(1, MinecraftClient.method_1551().method_22683().method_4495());
                                                            ImGui.getIO().setFontGlobalScale(1.0f / var0);
                                                            var1_2 = ImScreen.a;
                                                            var3_3 = ImGui.getIO().getFonts();
                                                            var3_3.clear();
                                                            Im.grew = false;
                                                            if (var1_2) break block64;
                                                            if (Im.ranges == null) break block65;
                                                            break block86;
                                                            catch (RuntimeException v0) {
                                                                throw v0;
                                                            }
                                                        }
                                                        MemoryUtil.memFree((Buffer)Im.ranges);
                                                    }
                                                    Im.ranges = MemoryUtil.memAllocShort((int)(Im.EXTRA.length + 2 * Im.seen.length() + 1));
                                                    Im.ranges.put(Im.EXTRA);
                                                }
                                                var4_4 = 0;
                                                var5_5 = 0;
                                                for (var6_6 = 0; var6_6 < Im.seen.length(); ++var6_6) {
                                                    block70: {
                                                        block72: {
                                                            block93: {
                                                                block71: {
                                                                    block92: {
                                                                        block69: {
                                                                            block91: {
                                                                                block90: {
                                                                                    block89: {
                                                                                        block68: {
                                                                                            block67: {
                                                                                                block88: {
                                                                                                    block87: {
                                                                                                        var7_8 = Im.seen.charAt(var6_6);
                                                                                                        v2 = Im.ranges;
                                                                                                        if (var1_2) break block66;
                                                                                                        v2.put((short)var7_8).put((short)var7_8);
                                                                                                        v3 = var7_8;
                                                                                                        if (var1_2) break block68;
                                                                                                        break block87;
                                                                                                        catch (RuntimeException v4) {
                                                                                                            throw v4;
                                                                                                        }
                                                                                                    }
                                                                                                    if (v3 < '\u2e80') break block67;
                                                                                                    break block88;
                                                                                                    catch (RuntimeException v5) {
                                                                                                        throw v5;
                                                                                                    }
                                                                                                }
                                                                                                v3 = '\u0001';
                                                                                                break block68;
                                                                                            }
                                                                                            v3 = '\u0000';
                                                                                        }
                                                                                        var4_4 = var4_4 | v3;
                                                                                        v7 = var5_5;
                                                                                        v8 = var7_8;
                                                                                        if (var1_2) break block69;
                                                                                        if (v8 < Im.a(25699, 4861749916613461100L)) // goto
                                                                                        break block89;
                                                                                        catch (RuntimeException v9) {
                                                                                            throw v9;
                                                                                        }
                                                                                    }
                                                                                    v8 = var7_8;
                                                                                    if (var1_2) break block70;
                                                                                    break block90;
                                                                                    catch (RuntimeException v10) {
                                                                                        throw v10;
                                                                                    }
                                                                                }
                                                                                if (v8 <= Im.a(3135, 8474655688342023228L)) // goto
                                                                                break block91;
                                                                                catch (RuntimeException v11) {
                                                                                    throw v11;
                                                                                }
                                                                            }
                                                                            v8 = var7_8;
                                                                        }
                                                                        if (var1_2) break block71;
                                                                        if (v8 < '\u3130') break block72;
                                                                        break block92;
                                                                        catch (RuntimeException v13) {
                                                                            throw v13;
                                                                        }
                                                                    }
                                                                    v8 = var7_8;
                                                                }
                                                                if (var1_2) break block70;
                                                                if (v8 > '\u318f') break block72;
                                                                break block93;
                                                                catch (RuntimeException v15) {
                                                                    throw v15;
                                                                }
                                                            }
                                                            v8 = '\u0001';
                                                            break block70;
                                                        }
                                                        v8 = '\u0000';
                                                    }
                                                    var5_5 = v7 | v8;
                                                    if (!var1_2) continue;
                                                }
                                                v2 = Im.ranges.put((short)0).flip();
                                            }
                                            v17 = var4_4 != 0 ? Im.first(Im.HAN) : null;
                                            var6_7 = v17;
                                            v19 = var5_5 != 0 ? Im.first(Im.HANGUL) : null;
                                            var7_9 = v19;
                                            v21 = var7_9;
                                            if (var1_2) break block73;
                                            if (v21 == null) break block74;
                                            v21 = var7_9;
                                        }
                                        if (v21.equals(var6_7)) {
                                            var7_9 = null;
                                        }
                                    }
                                    var8_10 = new ImFontConfig();
                                    try {
                                        block77: {
                                            block76: {
                                                block75: {
                                                    block95: {
                                                        block94: {
                                                            v23 = var8_10.ptr;
                                                            if (var1_2) break block75;
                                                            if (MemoryUtil.memGetFloat((long)(v23 + Im.b(8328, 3410967934255033571L))) != 0.0f) // goto
                                                            break block94;
                                                            catch (RuntimeException v24) {
                                                                throw v24;
                                                            }
                                                        }
                                                        v25 = var8_10.ptr;
                                                        if (var1_2) break block76;
                                                        break block95;
                                                        catch (RuntimeException v26) {
                                                            throw v26;
                                                        }
                                                    }
                                                    v23 = v25 + Im.b(22425, 5616493546082442223L);
                                                }
                                                if (MemoryUtil.memGetFloat((long)v23) == 3.4028235E38f) {
                                                    v25 = var8_10.ptr + Im.b(15602, 1506418315958448275L);
                                                }
                                            }
                                            MemoryUtil.memPutAddress((long)v25, (long)MemoryUtil.memAddress((ShortBuffer)Im.ranges));
                                            if (!var1_2) break block77;
lbl171:

                                            Aurora.LOG.warn(Im.b[7]);
                                        }
                                        var10_11 = Im.b;
                                        Im.body = Im.font(var3_3, var8_10, var10_11[21], 16.0f * var0, var6_7, var7_9);
                                        Im.bold = Im.font(var3_3, var8_10, var10_11[8], 16.0f * var0, var6_7, var7_9);
                                        Im.sub = Im.font(var3_3, var8_10, var10_11[31], 13.0f * var0, var6_7, var7_9);
                                        Im.bold13 = Im.font(var3_3, var8_10, var10_11[33], 13.0f * var0, var6_7, var7_9);
                                        Im.label = Im.font(var3_3, var8_10, var10_11[33], 11.0f * var0, var6_7, var7_9);
                                        Im.h3 = Im.font(var3_3, var8_10, var10_11[40], 20.0f * var0, var6_7, var7_9);
                                        Im.head = Im.font(var3_3, var8_10, var10_11[39], 22.0f * var0, var6_7, var7_9);
                                        Im.sora16 = Im.font(var3_3, var8_10, var10_11[39], 16.0f * var0, var6_7, var7_9);
                                        Im.sora14 = Im.font(var3_3, var8_10, var10_11[39], 14.0f * var0, var6_7, var7_9);
                                        Im.sora13 = Im.font(var3_3, var8_10, var10_11[39], 13.0f * var0, var6_7, var7_9);
                                        Im.sora12 = Im.font(var3_3, var8_10, var10_11[39], 12.0f * var0, var6_7, var7_9);
                                        Im.sora12r = Im.font(var3_3, var8_10, var10_11[17], 12.0f * var0, var6_7, var7_9);
                                        Im.sora14r = Im.font(var3_3, var8_10, var10_11[5], 14.0f * var0, var6_7, var7_9);
                                        Im.mc = Im.font(var3_3, var8_10, var10_11[31], 9 * var2_1, var6_7, var7_9);
                                    }
                                    finally {
                                        var8_10.destroy();
                                    }
                                    v30 = var3_3;
                                    if (var1_2) break block79;
                                    Im.title = v30.addFontFromFileTTF(Im.resource(Im.b[39]), 30.0f * var0);
                                    Im.huge = var3_3.addFontFromFileTTF(Im.resource(Im.b[39]), 54.0f * var0);
                                    Im.word = var3_3.addFontFromFileTTF(Im.resource(Im.b[39]), 18.0f * var0);
                                    if (!Im.flat) break block80;
                                    v32 /* !! */  = List.of();
                                    break block81;
                                }
                                v30 = var3_3;
                            }
                            v32 /* !! */  = Im.glyphs(v30, var2_1);
                        }
                        var9_13 = v32 /* !! */ ;
                        v34 = var3_3;
                        if (var1_2) break block82;
                        v34.build();
                        if (var9_13.isEmpty()) break block83;
                        Im.paint(var3_3, var9_13);
                    }
                    v34 = var3_3;
                }
                v34.clearInputData();
                if (var1_2) break block84;
                if (Im.gl3 == null) break block85;
                Im.gl3.destroyFontsTexture();
            }
            Im.gl3.createFontsTexture();
        }
        if (!Im.GL) {
            ImVk.atlas(var3_3);
        }
    }

    public static void reload() {
        grew = true;
    }

    private static List<Im$Glyph> glyphs(ImFontAtlas imFontAtlas, int n) {
        ArrayList<Im$Glyph> arrayList = new ArrayList<Im$Glyph>();
        BitSet bitSet = new BitSet();
        bitSet.set(32);
        arrayList.add(new Im$Glyph(32, 1, 1, null));
        imFontAtlas.addCustomRectFontGlyph(mc, (short)32, 1, 1, (float)(4 * n), 0.0f, 0.0f);
        try {
            String[] stringArray = b;
            Im.providers(MinecraftClient.method_1551().method_1478(), Identifier.method_60656((String)stringArray[48]), imFontAtlas, n, bitSet, arrayList, 0);
        }
        catch (Exception exception) {
            Aurora.LOG.warn(b[1], (Throwable)exception);
        }
        return arrayList;
    }

    private static void providers(ResourceManager resourceManager, Identifier identifier, ImFontAtlas imFontAtlas, int n, BitSet bitSet, List<Im$Glyph> list, int n2) throws IOException {
        boolean bl = ImScreen.a;
        if (n2 > 8) {
            return;
        }
        Iterator iterator = Lists.reverse((List)resourceManager.method_14489(identifier.method_45134(Im::lambda$providers$4))).iterator();
        block20: while (true) {
            Object object = iterator;
            block21: while (object.hasNext()) {
                String[] stringArray;
                JsonObject jsonObject;
                Object object2;
                block25: {
                    Resource resource = (Resource)iterator.next();
                    try {
                        object2 = resource.method_43039();
                        try {
                            jsonObject = JsonParser.parseReader((Reader)object2).getAsJsonObject();
                            if (bl) break block25;
                        }
                        catch (Throwable throwable) {
                            block26: {
                                if (bl || object2 == null) break block26;
                                try {
                                    ((Reader)object2).close();
                                }
                                catch (Throwable throwable2) {
                                    throwable.addSuppressed(throwable2);
                                }
                            }
                            throw throwable;
                        }
                        if (object2 == null) break block25;
                        ((Reader)object2).close();
                    }
                    catch (RuntimeException runtimeException) {
                        stringArray = b;
                        Aurora.LOG.warn(stringArray[11], new Object[]{identifier, resource.method_14480(), runtimeException.toString()});
                        continue block20;
                    }
                }
                stringArray = b;
                object2 = jsonObject.getAsJsonArray(stringArray[27]).iterator();
                while (object2.hasNext()) {
                    block29: {
                        String string;
                        JsonObject jsonObject2;
                        block27: {
                            String string2;
                            block28: {
                                object = object2;
                                if (bl) continue block21;
                                JsonElement jsonElement = (JsonElement)object.next();
                                jsonObject2 = jsonElement.getAsJsonObject();
                                stringArray = b;
                                string2 = jsonObject2.get(stringArray[29]).getAsString();
                                string = string2;
                                if (bl) break block27;
                                if (!string.equals(stringArray[41])) break block28;
                                Im.providers(resourceManager, Identifier.method_60654((String)jsonObject2.get(b[35]).getAsString()), imFontAtlas, n, bitSet, list, n2 + 1);
                                if (!bl) break block29;
                            }
                            string = string2;
                        }
                        if (string.equals(b[14])) {
                            Im.bitmap(resourceManager, jsonObject2, imFontAtlas, n, bitSet, list);
                        }
                    }
                    if (!bl) continue;
                }
                if (bl) break block20;
                continue block20;
            }
            break;
        }
    }

    
    private static void bitmap(ResourceManager resourceManager, JsonObject jsonObject, ImFontAtlas imFontAtlas, int n, BitSet bitSet, List<Im$Glyph> list) throws IOException {
        block89: {
            InputStream inputStream;
            block88: {
                InputStream inputStream2;
                boolean bl;
                block70: {
                    int n2;
                    Identifier identifier;
                    String[] stringArray;
                    block92: {
                        block69: {
                            String string;
                            JsonObject jsonObject2;
                            block68: {
                                block91: {
                                    stringArray = b;
                                    identifier = Identifier.method_60654((String)jsonObject.get(stringArray[28]).getAsString()).method_45138(stringArray[12]);
                                    bl = ImScreen.a;
                                    jsonObject2 = jsonObject;
                                    string = stringArray[22];
                                    if (bl) break block68;
                                    if (!jsonObject2.has(string)) break block69;
                                    break block91;
                                    catch (Throwable throwable) {
                                        throw throwable;
                                    }
                                }
                                jsonObject2 = jsonObject;
                                string = b[38];
                            }
                            n2 = jsonObject2.get(string).getAsInt();
                            break block92;
                        }
                        n2 = 8;
                    }
                    int n3 = n2;
                    stringArray = b;
                    int n4 = jsonObject.get(stringArray[43]).getAsInt();
                    JsonArray jsonArray = jsonObject.getAsJsonArray(stringArray[34]);
                    inputStream2 = resourceManager.open(identifier);
                    try {
                        NativeImage nativeImage;
                        block86: {
                            NativeImage nativeImage2 = NativeImage.method_4309((InputStream)inputStream2);
                            try {
                                int n5 = (int)jsonArray.get(0).getAsString().codePoints().count();
                                int n6 = nativeImage2.method_4307() / n5;
                                int n7 = nativeImage2.method_4323() / jsonArray.size();
                                float f = (float)n3 / (float)n7;
                                int n8 = 0;
                                block60: while (true) {
                                    int n9 = n8;
                                    block61: while (n9 < jsonArray.size()) {
                                        if (bl) break block70;
                                        int[] nArray = jsonArray.get(n8).getAsString().codePoints().toArray();
                                        int n10 = 0;
                                        while (n10 < nArray.length) {
                                            block72: {
                                                block73: {
                                                    int n11;
                                                    int n12;
                                                    int n13;
                                                    void var20_25;
                                                    block83: {
                                                        byte[] byArray;
                                                        block85: {
                                                            int n14;
                                                            block81: {
                                                                block82: {
                                                                    int n15;
                                                                    int n16;
                                                                    int n17;
                                                                    block103: {
                                                                        block102: {
                                                                            int n18;
                                                                            block78: {
                                                                                BitSet bitSet2;
                                                                                block76: {
                                                                                    block77: {
                                                                                        void v14;
                                                                                        block75: {
                                                                                            block98: {
                                                                                                boolean bl2;
                                                                                                block74: {
                                                                                                    block97: {
                                                                                                        block96: {
                                                                                                            block95: {
                                                                                                                block94: {
                                                                                                                    int n19;
                                                                                                                    block71: {
                                                                                                                        block93: {
                                                                                                                            n9 = n10;
                                                                                                                            if (bl) continue block61;
                                                                                                                            if (bl) break block71;
                                                                                                                            if (n9 >= n5) break;
                                                                                                                            break block93;
                                                                                                                            catch (Throwable throwable) {
                                                                                                                                throw throwable;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        n19 = nArray[n10];
                                                                                                                    }
                                                                                                                    var20_25 = n19;
                                                                                                                    if (bl) break block72;
                                                                                                                    if (var20_25 < 32) break block73;
                                                                                                                    break block94;
                                                                                                                    catch (Throwable throwable) {
                                                                                                                        throw throwable;
                                                                                                                    }
                                                                                                                }
                                                                                                                bl2 = var20_25;
                                                                                                                if (bl) break block74;
                                                                                                                break block95;
                                                                                                                catch (Throwable throwable) {
                                                                                                                    throw throwable;
                                                                                                                }
                                                                                                            }
                                                                                                            if (bl2 > Im.a(30001, 2466709555099194676L)) break block73;
                                                                                                            break block96;
                                                                                                            catch (Throwable throwable) {
                                                                                                                throw throwable;
                                                                                                            }
                                                                                                        }
                                                                                                        bitSet2 = bitSet;
                                                                                                        v14 = var20_25;
                                                                                                        if (bl) break block75;
                                                                                                        break block97;
                                                                                                        catch (Throwable throwable) {
                                                                                                            throw throwable;
                                                                                                        }
                                                                                                    }
                                                                                                    bl2 = bitSet2.get((int)v14);
                                                                                                }
                                                                                                if (bl2) break block73;
                                                                                                bitSet2 = known;
                                                                                                if (bl) break block76;
                                                                                                break block98;
                                                                                                catch (Throwable throwable) {
                                                                                                    throw throwable;
                                                                                                }
                                                                                            }
                                                                                            v14 = var20_25;
                                                                                        }
                                                                                        if (bitSet2.get((int)v14)) break block77;
                                                                                        break block73;
                                                                                    }
                                                                                    bitSet2 = bitSet;
                                                                                }
                                                                                bitSet2.set((int)var20_25);
                                                                                n17 = n10 * n6;
                                                                                n16 = n8 * n7;
                                                                                n15 = 0;
                                                                                n13 = n6 - 1;
                                                                                while (n13 >= 0) {
                                                                                    block79: {
                                                                                        block99: {
                                                                                            n18 = n15;
                                                                                            if (bl) break block78;
                                                                                            if (bl) break block78;
                                                                                            break block99;
                                                                                            catch (Throwable throwable) {
                                                                                                throw throwable;
                                                                                            }
                                                                                        }
                                                                                        if (n18 != 0) break;
                                                                                        for (n12 = 0; n12 < n7; ++n12) {
                                                                                            int n20;
                                                                                            block80: {
                                                                                                block101: {
                                                                                                    block100: {
                                                                                                        if (bl) break block79;
                                                                                                        n20 = nativeImage2.method_61940(n17 + n13, n16 + n12);
                                                                                                        if (bl) break block80;
                                                                                                        break block100;
                                                                                                        catch (Throwable throwable) {
                                                                                                            throw throwable;
                                                                                                        }
                                                                                                    }
                                                                                                    if (n20 >>> 24 == 0) continue;
                                                                                                    break block101;
                                                                                                    catch (Throwable throwable) {
                                                                                                        throw throwable;
                                                                                                    }
                                                                                                }
                                                                                                n20 = n13 + 1;
                                                                                            }
                                                                                            n15 = n20;
                                                                                            if (!bl) break;
                                                                                            if (!bl) continue;
                                                                                            break;
                                                                                        }
                                                                                        --n13;
                                                                                    }
                                                                                    if (!bl) continue;
                                                                                }
                                                                                n13 = (int)(0.5f + (float)n15 * f) + 1;
                                                                                n18 = Math.round((float)n15 * f * (float)n);
                                                                            }
                                                                            n12 = n18;
                                                                            n11 = Math.round((float)n7 * f * (float)n);
                                                                            byArray = null;
                                                                            n14 = n12;
                                                                            if (bl) break block81;
                                                                            if (n14 <= 0) break block82;
                                                                            break block102;
                                                                            catch (Throwable throwable) {
                                                                                throw throwable;
                                                                            }
                                                                        }
                                                                        n14 = n11;
                                                                        if (bl) break block81;
                                                                        break block103;
                                                                        catch (Throwable throwable) {
                                                                            throw throwable;
                                                                        }
                                                                    }
                                                                    if (n14 <= 0) break block82;
                                                                    byArray = new byte[n12 * n11];
                                                                    int n21 = 0;
                                                                    while (n21 < n11) {
                                                                        block84: {
                                                                            if (bl) break block83;
                                                                            for (int i = 0; i < n12; ++i) {
                                                                                byArray[n21 * n12 + i] = (byte)(nativeImage2.method_61940(n17 + i * n15 / n12, n16 + n21 * n7 / n11) >>> 24);
                                                                                if (!bl) {
                                                                                    if (!bl) continue;
                                                                                    break;
                                                                                }
                                                                                break block84;
                                                                            }
                                                                            ++n21;
                                                                        }
                                                                        if (!bl) continue;
                                                                        break block85;
                                                                    }
                                                                    break block85;
                                                                }
                                                                n14 = n11 = 1;
                                                            }
                                                            n12 = n14;
                                                        }
                                                        list.add(new Im$Glyph((int)var20_25, n12, n11, byArray));
                                                    }
                                                    imFontAtlas.addCustomRectFontGlyph(mc, (short)var20_25, n12, n11, (float)(n13 * n), 0.0f, (float)((7 - n4) * n));
                                                }
                                                ++n10;
                                            }
                                            if (!bl) continue;
                                        }
                                        ++n8;
                                        if (!bl) continue block60;
                                    }
                                    break;
                                }
                            }
                            catch (Throwable throwable) {
                                block87: {
                                    if (bl || nativeImage2 == null) break block87;
                                    try {
                                        nativeImage2.close();
                                    }
                                    catch (Throwable throwable2) {
                                        throwable.addSuppressed(throwable2);
                                    }
                                }
                                throw throwable;
                            }
                            nativeImage = nativeImage2;
                            if (bl) break block86;
                            if (nativeImage == null) break block70;
                            nativeImage = nativeImage2;
                        }
                        nativeImage.close();
                    }
                    catch (Throwable throwable) {
                        block90: {
                            if (bl || inputStream2 == null) break block90;
                            try {
                                inputStream2.close();
                            }
                            catch (Throwable throwable3) {
                                throwable.addSuppressed(throwable3);
                            }
                        }
                        throw throwable;
                    }
                }
                inputStream = inputStream2;
                if (bl) break block88;
                if (inputStream == null) break block89;
                inputStream = inputStream2;
            }
            inputStream.close();
        }
    }

    private static void paint(ImFontAtlas imFontAtlas, List<Im$Glyph> list) {
        long l;
        boolean bl;
        ImInt imInt;
        ImInt imInt2;
        block29: {
            block26: {
                int n;
                block28: {
                    block27: {
                        imInt2 = new ImInt();
                        imInt = new ImInt();
                        bl = ImScreen.a;
                        imFontAtlas.getTexDataAsRGBA32(imInt2, imInt);
                        l = MemoryUtil.memGetAddress((long)(imFontAtlas.ptr + Im.b(13886, 4257395425240578635L)));
                        if (bl) break block26;
                        if (l == 0L) break block27;
                        n = MemoryUtil.memGetInt((long)(imFontAtlas.ptr + Im.b(31774, 8302315798135910518L)));
                        if (bl) break block28;
                        if (n != imInt2.get()) break block27;
                        n = MemoryUtil.memGetInt((long)(imFontAtlas.ptr + Im.b(3880, 6604306912545227608L)));
                        if (bl) break block28;
                        if (n == imInt.get()) break block29;
                    }
                    Aurora.LOG.warn(b[49]);
                    flat = true;
                    n = 1;
                }
                grew = n;
            }
            return;
        }
        block20: for (Im$Glyph im$Glyph : list) {
            ImFontGlyph imFontGlyph;
            ImFontGlyph imFontGlyph2;
            block30: {
                if (im$Glyph.alpha == null) {
                    continue;
                }
                imFontGlyph2 = mc.findGlyphNoFallback(im$Glyph.cp);
                imFontGlyph = imFontGlyph2;
                if (bl) break block30;
                if (imFontGlyph.ptr == 0L) {
                    continue;
                }
                imFontGlyph = imFontGlyph2;
            }
            int n = Math.round(imFontGlyph.getU0() * (float)imInt2.get());
            int n2 = Math.round(imFontGlyph2.getV0() * (float)imInt.get());
            int n3 = 0;
            while (n3 < im$Glyph.h) {
                block32: {
                    if (bl) continue block20;
                    for (int i = 0; i < im$Glyph.w; ++i) {
                        MemoryUtil.memPutInt((long)(l + ((long)(n2 + n3) * (long)imInt2.get() + (long)n + (long)i) * Im.b(9310, 4317690803734964265L)), (int)((im$Glyph.alpha[n3 * im$Glyph.w + i] & 0xFF) << 24 | Im.a(5197, 4561445190371914825L)));
                        if (!bl) {
                            if (!bl) continue;
                            break;
                        }
                        break block32;
                    }
                    ++n3;
                }
                if (!bl) continue;
            }
            if (!bl) continue;
        }
    }

    public static String need(String string) {
        String string2;
        block16: {
            boolean bl = ImScreen.a;
            if (string == null) {
                return null;
            }
            int n = 0;
            while (n < string.length()) {
                block17: {
                    block18: {
                        block21: {
                            StringBuilder stringBuilder;
                            char c;
                            block22: {
                                int n2;
                                block19: {
                                    block20: {
                                        string2 = string;
                                        if (bl) break block16;
                                        c = string2.charAt(n);
                                        if (bl) break block17;
                                        if (known.get(c)) break block18;
                                        n2 = c;
                                        if (bl) break block19;
                                        if (!Character.isSurrogate((char)n2)) break block20;
                                        break block18;
                                    }
                                    stringBuilder = seen;
                                    if (bl) break block21;
                                    n2 = stringBuilder.length();
                                }
                                if (n2 < '\u0800') break block22;
                                break block18;
                            }
                            known.set(c);
                            stringBuilder = seen.append(c);
                        }
                        grew = true;
                    }
                    ++n;
                }
                if (!bl) continue;
            }
            string2 = string;
        }
        return string2;
    }

    private static String first(String[] stringArray) {
        String[] stringArray2 = stringArray;
        boolean bl = ImScreen.a;
        int n = stringArray2.length;
        int n2 = 0;
        while (n2 < n) {
            block5: {
                block6: {
                    String string = stringArray2[n2];
                    if (bl) break block5;
                    if (!new File(string).isFile()) break block6;
                    return string;
                }
                ++n2;
            }
            if (!bl) continue;
        }
        return null;
    }

    private static ImFont font(ImFontAtlas imFontAtlas, ImFontConfig imFontConfig, String string, float f, String string2, String string3) {
        String string4;
        ImFont imFont;
        block13: {
            block14: {
                boolean bl;
                block11: {
                    block12: {
                        imFontConfig.setMergeMode(false);
                        imFont = imFontAtlas.addFontFromFileTTF(Im.resource(string), f, imFontConfig);
                        imFontConfig.setMergeMode(true);
                        bl = ImScreen.a;
                        string4 = string;
                        if (bl) break block11;
                        if (!string4.startsWith(b[36])) break block12;
                        imFontAtlas.addFontFromFileTTF(Im.resource(string.replace(b[24], b[19])), f, imFontConfig);
                    }
                    string4 = string2;
                }
                if (bl) break block13;
                if (string4 == null) break block14;
                imFontAtlas.addFontFromFileTTF(string2, f, imFontConfig);
            }
            string4 = string3;
        }
        if (string4 != null) {
            imFontAtlas.addFontFromFileTTF(string3, f, imFontConfig);
        }
        return imFont;
    }

    
    private static String resource(String string) {
        boolean bl = ImScreen.a;
        try {
            InputStream inputStream;
            String string2;
            block18: {
                String[] stringArray = b;
                InputStream inputStream2 = Im.class.getResourceAsStream(stringArray[16] + string);
                try {
                    Path path2;
                    if (inputStream2 == null) {
                        throw new IllegalStateException(stringArray[6] + string);
                    }
                    Path path = fontDir;
                    if (!bl) {
                        if (path == null) {
                            fontDir = Files.createTempDirectory(b[3], new FileAttribute[0]);
                            fontDir.toFile().deleteOnExit();
                        }
                        path = fontDir.resolve(string);
                    }
                    Path path3 = path2 = path;
                    if (!bl) {
                        if (!Files.exists(path3, new LinkOption[0])) {
                            Files.copy(inputStream2, path2, new CopyOption[0]);
                            path2.toFile().deleteOnExit();
                        }
                        path3 = path2;
                    }
                    string2 = path3.toString();
                    inputStream = inputStream2;
                    if (bl) break block18;
                }
                catch (Throwable throwable) {
                    if (bl) throw throwable;
                    if (inputStream2 == null) throw throwable;
                    try {
                        inputStream2.close();
                        throw throwable;
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
                if (inputStream == null) return string2;
                inputStream = inputStream2;
            }
            inputStream.close();
            return string2;
        }
        catch (IOException iOException) {
            throw new UncheckedIOException(iOException);
        }
    }

    public static void color(ImGuiStyle imGuiStyle, int n, float[] fArray) {
        imGuiStyle.setColor(n, fArray[0], fArray[1], fArray[2], fArray[3]);
    }

    static void restyle() {
        Im.in(Im::style);
    }

    private static void style() {
        ImGuiStyle imGuiStyle = ImGui.getStyle();
        imGuiStyle.setWindowRounding(16.0f);
        imGuiStyle.setWindowBorderSize(1.0f);
        imGuiStyle.setWindowPadding(18.0f, 16.0f);
        imGuiStyle.setChildRounding(12.0f);
        imGuiStyle.setChildBorderSize(1.0f);
        imGuiStyle.setFrameRounding(8.0f);
        imGuiStyle.setFramePadding(12.0f, 7.0f);
        imGuiStyle.setItemSpacing(8.0f, 8.0f);
        imGuiStyle.setScrollbarSize(8.0f);
        imGuiStyle.setScrollbarRounding(8.0f);
        Im.color(imGuiStyle, 2, GLASS);
        Im.color(imGuiStyle, 3, QUIET);
        Im.color(imGuiStyle, 5, FIELD_LINE);
        Im.color(imGuiStyle, 0, INK);
        Im.color(imGuiStyle, 1, MUTE);
        Im.color(imGuiStyle, 7, FIELD);
        Im.color(imGuiStyle, 8, FIELD);
        Im.color(imGuiStyle, 9, FIELD);
        Im.color(imGuiStyle, 21, QUIET);
        Im.color(imGuiStyle, 22, QUIET_HI);
        Im.color(imGuiStyle, 23, WASH);
        Im.color(imGuiStyle, 18, INK);
        Im.color(imGuiStyle, 14, new float[]{0.0f, 0.0f, 0.0f, 0.0f});
        Im.color(imGuiStyle, 15, WASH);
        Im.color(imGuiStyle, 16, WASH_HI);
        Im.color(imGuiStyle, 17, WASH_HI);
        Im.color(imGuiStyle, 51, WASH_HI);
        imGuiStyle.setColor(56, DIM[0], DIM[1], DIM[2], Math.min(1.0f, 0.62f * DIM[3]));
        Im.color(imGuiStyle, 4, POP);
    }

    private static String lambda$providers$4(String string) {
        String[] stringArray = b;
        return stringArray[2] + string + stringArray[0];
    }

    private static void lambda$character$3(int n) {
        glfw.charCallback(MinecraftClient.method_1551().method_22683().method_4490(), n);
    }

    private static void lambda$key$2(int n, int n2, int n3, int n4) {
        glfw.keyCallback(MinecraftClient.method_1551().method_22683().method_4490(), n, n2, n3, n4);
    }

    private static void lambda$scroll$1(double d, double d2) {
        ImGui.getIO().addMouseWheelEvent((float)d, (float)d2);
    }

    private static void lambda$mouse$0(int n, boolean bl) {
        ImGui.getIO().addMouseButtonEvent(n, bl);
    }

    
    static {
        block42: {
            block41: {
                block40: {
                    block39: {
                        block38: {
                            block37: {
                                var21 = new String[50];
                                var19_1 = 0;
                                var18_2 = "tB'?#.7A:5.Wo<\\t6\"KzzL=4mKa.\b8?,A\"zE1>8V./[1p D`(G$5\u0005<G:$b\f;]&??D#<G:$>\n\u0019\u0012{\u0007$Kj5_'\f)G&1`\u0011>j\u0006 $+\r\u0017A'#$KizN;>9\u00052\u0013E\u0012?#QM5F29*\u0005b;Q;%9\u0005m2I:7(A\"zE1>8V.)\\5)mIo.A:p\"Kb#\u000f7I:\"\"Ukw\u001ed`cQz<\u000b\u0003]\u0013?9MCt\\ 36u]'\"bVf;Z1\u007f+J`.[{?=@`.Q$5bKa.G{\u001e\"Qa\tI:#\u000eoEwz178Io(\u0006 $.\u0019<G:$m^szA:p6X.)C= =@j`\b/-\t.M,$8Wk)\u0007\"u{-#9@cud=2?D|#\u0007\u0012?#Q}ux=>*co4Oz$9F\u00068A =,U4u{-#9@cud=2?D|#\u0007\u0012?#Q}u{! =Ik7M:$,I!\u001bZ=1!\u0005[4A7?)@ .\\2\u0015uI'#(Q}uI!\"\"WouN;>9V!\f)G&1`\u0011>j\u0006 $+\b7[-8cQz9\u00077I:\"\"Uk\fy^1\">La4\bee}\u000f7I:\"\"Ukw\u001cd`cQz<\u00062M=7%Q4u{-#9@cud=2?D|#\u0007\u0012?#Q}u{! =Ik7M:$,I!\u001bZ=1!\u0005[4A7?)@ .\\2\u0004)G&16u]'\"bVf;Z1\u007f+J`.[{?=@`.Q$5bKa.G{\u001e\"Qa\tI:#\u000eoEwz178Io(\u0006 $.8u]'\"bVf;Z1\u007f+J`.[{7\"Ji6My>\"QawK>;bka.G\u00071#VM\u0010cy\u0002(B{6I&~9Qm\t*Z;&$Ak([\u0004<A85\u0004.Q$5\n7I878K .\\2\u000f7I:\"\"Ukw\u001cd`cQz<\u0007un;>9V!\u000f7I:\"\"Ukw\u001ed`cQz<\u00059@5\">\u00023L\u0004)G&1*u{-#9@cud=2?D|#\u0007\u0012?#Q}ui$ !@]\u001eo;$%Lm\u0014M;~9Qm\u00062M=7%Q\f)G&1`\u0013>j\u0006 $+\f)G&1`\u0013>j\u0006 $+\t(M25?@`9M1u]'\"bVf;Z1\u007f+J`.[{>\"QawK>;bka.G\u00071#VM\u0010cy\u0002(B{6I&~9Qm\u0006;[75#Q1u]'\"bVf;Z1\u007f+J`.[{>\"QawK>;bka.G\u00071#VM\u0010cy\u0002(B{6I&~9Qm\t,]8;,Kc5L8u]'\"bVf;Z1\u007f+J`.[{7\"Ji6My>\"QawK>;bka.G\u00071#VM\u0010cy\u0002(B{6I&~9Qm\u0006\ra\u001a\u0014\u0004w";
                                var20_3 = "tB'?#.7A:5.Wo<\\t6\"KzzL=4mKa.\b8?,A\"zE1>8V./[1p D`(G$5\u0005<G:$b\f;]&??D#<G:$>\n\u0019\u0012{\u0007$Kj5_'\f)G&1`\u0011>j\u0006 $+\r\u0017A'#$KizN;>9\u00052\u0013E\u0012?#QM5F29*\u0005b;Q;%9\u0005m2I:7(A\"zE1>8V.)\\5)mIo.A:p\"Kb#\u000f7I:\"\"Ukw\u001ed`cQz<\u000b\u0003]\u0013?9MCt\\ 36u]'\"bVf;Z1\u007f+J`.[{?=@`.Q$5bKa.G{\u001e\"Qa\tI:#\u000eoEwz178Io(\u0006 $.\u0019<G:$m^szA:p6X.)C= =@j`\b/-\t.M,$8Wk)\u0007\"u{-#9@cud=2?D|#\u0007\u0012?#Q}ux=>*co4Oz$9F\u00068A =,U4u{-#9@cud=2?D|#\u0007\u0012?#Q}u{! =Ik7M:$,I!\u001bZ=1!\u0005[4A7?)@ .\\2\u0015uI'#(Q}uI!\"\"WouN;>9V!\f)G&1`\u0011>j\u0006 $+\b7[-8cQz9\u00077I:\"\"Uk\fy^1\">La4\bee}\u000f7I:\"\"Ukw\u001cd`cQz<\u00062M=7%Q4u{-#9@cud=2?D|#\u0007\u0012?#Q}u{! =Ik7M:$,I!\u001bZ=1!\u0005[4A7?)@ .\\2\u0004)G&16u]'\"bVf;Z1\u007f+J`.[{?=@`.Q$5bKa.G{\u001e\"Qa\tI:#\u000eoEwz178Io(\u0006 $.8u]'\"bVf;Z1\u007f+J`.[{7\"Ji6My>\"QawK>;bka.G\u00071#VM\u0010cy\u0002(B{6I&~9Qm\t*Z;&$Ak([\u0004<A85\u0004.Q$5\n7I878K .\\2\u000f7I:\"\"Ukw\u001cd`cQz<\u0007un;>9V!\u000f7I:\"\"Ukw\u001ed`cQz<\u00059@5\">\u00023L\u0004)G&1*u{-#9@cud=2?D|#\u0007\u0012?#Q}ui$ !@]\u001eo;$%Lm\u0014M;~9Qm\u00062M=7%Q\f)G&1`\u0013>j\u0006 $+\f)G&1`\u0013>j\u0006 $+\t(M25?@`9M1u]'\"bVf;Z1\u007f+J`.[{>\"QawK>;bka.G\u00071#VM\u0010cy\u0002(B{6I&~9Qm\u0006;[75#Q1u]'\"bVf;Z1\u007f+J`.[{>\"QawK>;bka.G\u00071#VM\u0010cy\u0002(B{6I&~9Qm\t,]8;,Kc5L8u]'\"bVf;Z1\u007f+J`.[{7\"Ji6My>\"QawK>;bka.G\u00071#VM\u0010cy\u0002(B{6I&~9Qm\u0006\ra\u001a\u0014\u0004w".length();
                                var17_4 = 5;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block37;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = ">M218IzD\u0013E\u0012?#QO.D5#mIo#G!$mFf;F35)\t..@1p L`?K&1+Q.<G:$mCo6D'p/Dm1\b ?mHo4Z; (";
                                    var20_3 = ">M218IzD\u0013E\u0012?#QO.D5#mIo#G!$mFf;F35)\t..@1p L`?K&1+Q.<G:$mCo6D'p/Dm1\b ?mHo4Z; (".length();
                                    var17_4 = 7;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block37;
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
                                    break block38;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_7 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_7;
                                v9 = v6[v8];
                                switch (var22_7 % 7) {
                                    case 0: {
                                        v10 = 90;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 40;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 84;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 80;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 77;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 37;
                                        break;
                                    }
                                    default: {
                                        v10 = 14;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_7;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_7);
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
                        Im.b = var21;
                        var8_8 = 8044991707257149431L;
                        var14_9 = new long[9];
                        var11_10 = 0;
                        var12_11 = "\u00ad\u009cD/[%\r\u009af\u0096\u00fbwOW\u00bb\u00f2\u00b7\u0092\u00f5\u00e2\u000f\u00b9\u00bd<:\u00c7D\u00c0\u00ceq\\AxSE\u0099\u00cdX\u00ecd\u0094\u00eb\u00f0C*\"QNO\u00eb\u0007z\u00aa\"\u0093W";
                        var13_12 = "\u00ad\u009cD/[%\r\u009af\u0096\u00fbwOW\u00bb\u00f2\u00b7\u0092\u00f5\u00e2\u000f\u00b9\u00bd<:\u00c7D\u00c0\u00ceq\\AxSE\u0099\u00cdX\u00ecd\u0094\u00eb\u00f0C*\"QNO\u00eb\u0007z\u00aa\"\u0093W".length();
                        var10_13 = 0;
                        while (true) {
                            var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                            v11 = var14_9;
                            v12 = var11_10++;
                            v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                            v14 = -1;
                            break block39;
                            break;
                        }
lbl89:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            var12_11 = "\u0083\u009a\u00eaC,\u00den;9\u00a1s\u008e:\u0081\u00ff\u009b";
                            var13_12 = "\u0083\u009a\u00eaC,\u00den;9\u00a1s\u008e:\u0081\u00ff\u009b".length();
                            var10_13 = 0;
                            while (true) {
                                var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                                v11 = var14_9;
                                v12 = var11_10++;
                                v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                                v14 = 0;
                                break block39;
                                break;
                            }
                            break;
                        }
lbl102:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            break block40;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_8;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl113:

                        continue;
                    }
                }
                Im.c = var14_9;
                Im.d = new Integer[9];
                var0_15 = 2624348129379614140L;
                var6_16 = new long[22];
                var3_17 = 0;
                var4_18 = "-w2\u00e8\u00c1\u0085P\"k\u00cd\u0010u\u00883\u00ed`p\u0005\u000b\u00a0\u001b\u00da/\u00fb\b\u00f0\u00d7\u0099C\u0013\u00b6\u00b60\u008cQ\u00c2?y\u008d\u0017-+\u008c\u00d0\u00d6\u00f6<\u00c6on\u00a6\u0095\u009bV/$\u000fu\u00e8\u00ff\u0006VD\u00be\t\u008bj\u0013\u00976>H\u0016\u000f*\u0013\u00e8\u00d0\u00ef\rM\u00c2\u0087)0q_\u00cc<T\u00ea'\u00b0\u001c\u0087\u0014{A\u001b]\u00eb\u00e1\u00baQW\\N\u0083;\u00a0\u00cd\u008a\u000b=\u00bf\u00fb\u00bf%\u0011\u001f4\u00f0\u00ddkXR\u00ef\u0083\u001f~\u00c0-\u0007w\u0087\u00cf\u0002\u00f8\u00f3\u00b3\u00d1b\u00ed:\u001f\u0080\nP\u00cc6\u0015\u0091i\u009aE\u00a7K%f\u0017";
                var5_19 = "-w2\u00e8\u00c1\u0085P\"k\u00cd\u0010u\u00883\u00ed`p\u0005\u000b\u00a0\u001b\u00da/\u00fb\b\u00f0\u00d7\u0099C\u0013\u00b6\u00b60\u008cQ\u00c2?y\u008d\u0017-+\u008c\u00d0\u00d6\u00f6<\u00c6on\u00a6\u0095\u009bV/$\u000fu\u00e8\u00ff\u0006VD\u00be\t\u008bj\u0013\u00976>H\u0016\u000f*\u0013\u00e8\u00d0\u00ef\rM\u00c2\u0087)0q_\u00cc<T\u00ea'\u00b0\u001c\u0087\u0014{A\u001b]\u00eb\u00e1\u00baQW\\N\u0083;\u00a0\u00cd\u008a\u000b=\u00bf\u00fb\u00bf%\u0011\u001f4\u00f0\u00ddkXR\u00ef\u0083\u001f~\u00c0-\u0007w\u0087\u00cf\u0002\u00f8\u00f3\u00b3\u00d1b\u00ed:\u001f\u0080\nP\u00cc6\u0015\u0091i\u009aE\u00a7K%f\u0017".length();
                var2_20 = 0;
                while (true) {
                    var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
                    v16 = var6_16;
                    v17 = var3_17++;
                    v18 = ((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L;
                    v19 = -1;
                    break block41;
                    break;
                }
lbl130:

                while (true) {
                    v16[v17] = v20;
                    if (var2_20 < var5_19) continue;
                    var4_18 = "\u001d[\u00f1\u00cf\u00e3\u00b2\u00b6\u008d\u007f\u00cc\u00b8\u009dwK\u00be\u00a0";
                    var5_19 = "\u001d[\u00f1\u00cf\u00e3\u00b2\u00b6\u008d\u007f\u00cc\u00b8\u009dwK\u00be\u00a0".length();
                    var2_20 = 0;
                    while (true) {
                        var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
                        v16 = var6_16;
                        v17 = var3_17++;
                        v18 = ((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L;
                        v19 = 0;
                        break block41;
                        break;
                    }
                    break;
                }
lbl143:

                while (true) {
                    v16[v17] = v20;
                    if (var2_20 < var5_19) continue;
                    break block42;
                    break;
                }
            }
            v20 = v18 ^ var0_15;
            switch (v19) {
                default: {
                    continue;
                }
                case 0:
lbl154:

                continue;
            }
        }
        Im.e = var6_16;
        Im.f = new Long[22];
        Im.INK = new float[]{0.945f, 0.953f, 0.969f, 1.0f};
        Im.SUB = new float[]{0.753f, 0.776f, 0.824f, 1.0f};
        Im.MUTE = new float[]{0.584f, 0.612f, 0.667f, 1.0f};
        Im.GLASS = new float[]{0.055f, 0.067f, 0.102f, 0.82f};
        Im.RIM = new float[]{1.0f, 1.0f, 1.0f, 0.13f};
        Im.RIM_HI = new float[]{1.0f, 1.0f, 1.0f, 0.55f};
        Im.WASH = new float[]{1.0f, 1.0f, 1.0f, 0.16f};
        Im.WASH_HI = new float[]{1.0f, 1.0f, 1.0f, 0.26f};
        Im.QUIET = new float[]{1.0f, 1.0f, 1.0f, 0.07f};
        Im.QUIET_HI = new float[]{1.0f, 1.0f, 1.0f, 0.12f};
        Im.WELL = new float[]{0.0f, 0.0f, 0.0f, 0.28f};
        Im.GREEN = new float[]{0.329f, 0.839f, 0.627f, 1.0f};
        Im.WARN = new float[]{0.965f, 0.757f, 0.467f, 1.0f};
        Im.DANGER = new float[]{0.878f, 0.443f, 0.373f, 1.0f};
        Im.BG = new float[]{0.043f, 0.051f, 0.078f, 1.0f};
        Im.POP = new float[]{0.063f, 0.071f, 0.102f, 1.0f};
        Im.DIM = new float[]{0.02f, 0.024f, 0.04f, 1.0f};
        Im.TINT = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
        Im.TRACK = new float[]{0.149f, 0.176f, 0.227f, 1.0f};
        Im.KNOB = new float[]{0.412f, 0.455f, 0.529f, 1.0f};
        Im.ON_FILL = new float[]{0.945f, 0.953f, 0.969f, 1.0f};
        Im.WINDOW = new float[]{0.043f, 0.051f, 0.078f, 0.96f};
        Im.WIN_LINE = new float[]{1.0f, 1.0f, 1.0f, 0.1f};
        Im.LINE = new float[]{1.0f, 1.0f, 1.0f, 0.07f};
        Im.SURFACE = new float[]{1.0f, 1.0f, 1.0f, 0.04f};
        Im.SURFACE_LINE = new float[]{1.0f, 1.0f, 1.0f, 0.07f};
        Im.FIELD = new float[]{1.0f, 1.0f, 1.0f, 0.06f};
        Im.FIELD_LINE = new float[]{1.0f, 1.0f, 1.0f, 0.09f};
        Im.PANE = new float[]{1.0f, 1.0f, 1.0f, 0.04f};
        Im.PANE_LINE = new float[]{1.0f, 1.0f, 1.0f, 0.08f};
        Im.CHIP_ON = new float[]{0.945f, 0.953f, 0.969f, 1.0f};
        Im.CHIP_ON_INK = new float[]{0.043f, 0.051f, 0.078f, 1.0f};
        Im.bake = 2.0f;
        Im.CURSOR_X = new double[1];
        Im.CURSOR_Y = new double[1];
        Im.scale = 1.0f;
        v21 = FabricLoader.getInstance().isModLoaded(Im.b[45]) == false;
        Im.GL = v21;
        Im.EXTRA = new short[]{32, 383, 880, 1023, 1024, 1119, 8192, 8303, 8364, 8364, 8482, 8482};
        Im.seen = new StringBuilder();
        Im.known = new BitSet();
        Im.known.set(0, 32);
        for (var23_22 = 0; var23_22 < Im.EXTRA.length; var23_22 += 2) {
            Im.known.set((int)Im.EXTRA[var23_22], Im.EXTRA[var23_22 + 1] + 1);
        }
        var16_6 = Im.b;
        Im.WIN = System.getenv().getOrDefault(var16_6[47], var16_6[4]) + var16_6[32];
        Im.HAN = new String[]{Im.WIN + var16_6[18], Im.WIN + var16_6[9], var16_6[23], var16_6[13], var16_6[25], var16_6[42], var16_6[26]};
        Im.HANGUL = new String[]{Im.WIN + var16_6[30], var16_6[15], var16_6[37], var16_6[10], var16_6[44], var16_6[46]};
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2007;
        if (d[n2] == null) {
            Im.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x4C65) & Short.MAX_VALUE;
        if (f[n2] == null) {
            Im.f[n2] = e[n2] ^ l;
        }
        return f[n2];
    }
}
