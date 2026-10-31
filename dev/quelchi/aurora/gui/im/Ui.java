package dev.quelchi.aurora.gui.im;

import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.quelchi.aurora.gui.VectorIcons;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Motion;
import dev.quelchi.aurora.gui.im.Ui$Slide;
import dev.quelchi.aurora.util.SkinFetcher;
import imgui.ImDrawData;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.ImVec2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.GlTexture;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public final class Ui {
    private static final ImVec2 SIZE;
    private static final float PAIR_GAP = 6.0f;
    public static final float SW_W = 38.0f;
    public static final float SW_H = 22.0f;
    private static final float[] TRACK;
    private static final float[] KNOB;
    private static final float[] WHITE;
    private static final Map<String, Ui$Slide> SLIDES;
    private static final float[] PLUS_TOP;
    private static final float[] PLUS_FOOT;
    private static final List<GpuTextureView> VIEWS;
    private static final BitSet NEAREST;
    private static final long VTX;
    private static final boolean SHADE;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private Ui() {
    }

    public static int col(float[] fArray) {
        return ImGui.getColorU32((float)fArray[0], (float)fArray[1], (float)fArray[2], (float)fArray[3]);
    }

    public static int col(float[] fArray, float f) {
        return ImGui.getColorU32((float)fArray[0], (float)fArray[1], (float)fArray[2], (float)(fArray[3] * f));
    }

    public static float width(ImFont imFont, String string) {
        ImGui.pushFont((ImFont)imFont);
        ImGui.calcTextSize((ImVec2)SIZE, (String)Im.need(string));
        ImGui.popFont();
        return Ui.SIZE.x;
    }

    public static float mid(ImFont imFont, float f, float f2) {
        return f + (float)Math.round((f2 - imFont.getFontSize() / Im.bake()) / 2.0f);
    }

    public static float pair(ImFont imFont, ImFont imFont2, float f, float f2) {
        float f3 = Ui.base(imFont) + 6.0f - Ui.cap(imFont2) + Ui.base(imFont2) - Ui.cap(imFont);
        return f + (float)Math.round((f2 - f3) / 2.0f - Ui.cap(imFont));
    }

    public static float under(ImFont imFont, ImFont imFont2, float f) {
        return f + (float)Math.round(Ui.base(imFont) + 6.0f - Ui.cap(imFont2));
    }

    public static float ink(ImFont imFont, float f, float f2) {
        return f + (float)Math.round((f2 - Ui.base(imFont) + Ui.cap(imFont)) / 2.0f - Ui.cap(imFont));
    }

    public static float baseline(ImFont imFont, float f, ImFont imFont2) {
        return f + (float)Math.round(Ui.base(imFont) - Ui.base(imFont2));
    }

    static float cap(ImFont imFont) {
        return imFont.findGlyph(72).getY0() * Ui.drawn(imFont);
    }

    static float base(ImFont imFont) {
        return imFont.findGlyph(72).getY1() * Ui.drawn(imFont);
    }

    private static float drawn(ImFont imFont) {
        return (float)Math.round(imFont.getFontSize() / Im.bake()) / imFont.getFontSize();
    }

    public static void text(ImFont imFont, float f, float f2, float[] fArray, String string) {
        ImGui.getWindowDrawList().addText(imFont, Math.round(imFont.getFontSize() / Im.bake()), f, f2, Ui.col(fArray), Im.need(string));
    }

    public static float mcPx() {
        return Im.mc.getFontSize() / 9.0f / Im.bake();
    }

    public static void mcText(float f, float f2, int n, String string, boolean bl) {
        block4: {
            ImDrawList imDrawList;
            block5: {
                boolean bl2 = ImScreen.a;
                imDrawList = ImGui.getWindowDrawList();
                if (bl2) break block4;
                if (!bl) break block5;
                Ui.mcLine(imDrawList, f + Ui.mcPx(), f2 + Ui.mcPx(), n & Ui.a(9027, 8690888805812138450L) | n >> 2 & Ui.a(20251, 2155894738908224905L), string);
            }
            Ui.mcLine(imDrawList, f, f2, n, string);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void mcLine(ImDrawList imDrawList, float f, float f2, int n, String string) {
        float f3;
        float f4;
        boolean bl;
        block14: {
            float f5;
            float f6;
            block13: {
                boolean bl2;
                block12: {
                    float f7 = Im.bake();
                    bl = ImScreen.a;
                    f = (float)Math.round(f * f7) / f7;
                    f2 = (float)Math.round(f2 * f7) / f7;
                    f4 = (float)Math.floor(f);
                    f3 = (float)Math.floor(f2);
                    int n2 = Ui.vtx(imDrawList);
                    ImGui.pushFont((ImFont)Im.mc);
                    imDrawList.addText(f4, f3, n, Im.need(string));
                    ImGui.popFont();
                    bl2 = SHADE;
                    if (bl) break block12;
                    if (!bl2) return;
                    float f5 = f;
                    f5 = f4;
                    if (bl) break block13;
                    float f8 = f6 - f5;
                    bl2 = f8 == 0.0f ? 0 : (f8 > 0.0f ? 1 : -1);
                }
                if (bl2) break block14;
                float f5 = f2;
                f5 = f3;
            }
            if (f6 == f5) {
                return;
            }
        }
        long l = MemoryUtil.memGetAddress((long)(imDrawList.ptr + Ui.b(17828, 1594206001070542883L) + Ui.b(2239, 821836571642486067L)));
        int n3 = Ui.vtx(imDrawList);
        for (int i = n2; i < n3; ++i) {
            long l2 = l + (long)i * Ui.b(23550, 7269483135104585336L);
            MemoryUtil.memPutFloat((long)l2, (float)(MemoryUtil.memGetFloat((long)l2) + f - f4));
            MemoryUtil.memPutFloat((long)(l2 + Ui.b(20314, 7265341794566707924L)), (float)(MemoryUtil.memGetFloat((long)(l2 + Ui.b(27538, 1894997867898289695L))) + f2 - f3));
            if (!bl) continue;
        }
    }

    public static boolean toggle(String string, float f, float f2, float f3, float f4, boolean bl) {
        f += f3 - 38.0f;
        f2 += (f4 - 22.0f) / 2.0f;
        f3 = 38.0f;
        f4 = 22.0f;
        ImGui.setCursorScreenPos((float)f, (float)f2);
        boolean bl2 = ImGui.invisibleButton((String)string, (float)f3, (float)f4);
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        float f5 = f4 / 2.0f;
        float f6 = Motion.to(a[4] + ImGui.getItemID(), bl, 0.15f);
        int n = Ui.vtx(imDrawList);
        imDrawList.addRectFilled(f, f2, f + f3, f2 + f4, Ui.col(Ui.mix(TRACK, Menu.ACCENT, f6)), f5);
        Ui.across(imDrawList, n, f, f + f3, Menu.GRAD_A, Menu.GRAD_B, Menu.ACCENT, f6);
        float f7 = f + f5 + (f3 - f4) * f6;
        imDrawList.addCircleFilled(f7, f2 + f5 + 1.0f, f5 - 2.5f, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)0.12f), 24);
        imDrawList.addCircleFilled(f7, f2 + f5 + 0.5f, f5 - 3.0f, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)0.22f), 24);
        imDrawList.addCircleFilled(f7, f2 + f5, f5 - 3.0f, Ui.col(Ui.mix(KNOB, Menu.KNOB_ON, f6)), 24);
        return bl2;
    }

    public static float[] tint(float f) {
        return new float[]{Menu.ACCENT[0], Menu.ACCENT[1], Menu.ACCENT[2], f};
    }

    public static float[] soft() {
        return (float[])Menu.SOFT.clone();
    }

    public static void glow(ImDrawList imDrawList, float f, float f2, float f3, float f4, float[] fArray, float f5, float f6, float f7, float f8, float f9) {
        block18: {
            ImDrawList imDrawList2;
            block19: {
                int n;
                int n2;
                boolean bl;
                int n3;
                block17: {
                    block15: {
                        int n4 = 64;
                        int n5 = 24;
                        float f10 = ImGui.getFontTexUvWhitePixelX();
                        float f11 = ImGui.getFontTexUvWhitePixelY();
                        imDrawList.pushClipRect(f5, f6, f5 + f7, f6 + f8, true);
                        imDrawList.primReserve(9024, 1537);
                        n3 = imDrawList.getVtxCurrentIdx();
                        imDrawList.primWriteVtx(f, f2, f10, f11, Ui.glowCol(fArray, 0.0f, f, f2, f5, f6, f7, f8, f9));
                        bl = ImScreen.a;
                        for (n2 = 1; n2 <= 24; ++n2) {
                            if (bl) break block15;
                            float f12 = (float)n2 / 24.0f * ((float)n2 / 24.0f);
                            block9: while (true) {
                                float f13 = f12;
                                for (n = 0; n < 64; ++n) {
                                    double d = (double)n * Math.PI * 2.0 / 64.0;
                                    f12 = f + (float)Math.cos(d) * f3 * f13;
                                    if (bl) continue block9;
                                    float f14 = f12;
                                    float f15 = f2 + (float)Math.sin(d) * f4 * f13;
                                    imDrawList.primWriteVtx(f14, f15, f10, f11, Ui.glowCol(fArray, f13, f14, f15, f5, f6, f7, f8, f9));
                                    if (!bl) continue;
                                }
                                break;
                            }
                            if (!bl) continue;
                        }
                        n2 = 0;
                    }
                    do {
                        if (n2 >= 64) break;
                        imDrawList.primWriteIdx(n3);
                        imDrawList.primWriteIdx(n3 + 1 + n2);
                        imDrawList.primWriteIdx(n3 + 1 + (n2 + 1) % 64);
                        if (bl) break block17;
                        ++n2;
                    } while (!bl);
                    n2 = 1;
                }
                while (n2 < 24) {
                    int n6 = n3 + 1 + (n2 - 1) * 64;
                    if (bl) break block18;
                    n = n3 + 1 + n2 * 64;
                    for (int i = 0; i < 64; ++i) {
                        int n7 = (i + 1) % 64;
                        imDrawList2 = imDrawList;
                        if (!bl) {
                            imDrawList2.primWriteIdx(n6 + i);
                            imDrawList.primWriteIdx(n + i);
                            imDrawList.primWriteIdx(n + n7);
                            imDrawList.primWriteIdx(n6 + i);
                            imDrawList.primWriteIdx(n + n7);
                            imDrawList.primWriteIdx(n6 + n7);
                            if (!bl) continue;
                            break;
                        }
                        break block19;
                    }
                    ++n2;
                    if (!bl) continue;
                }
                imDrawList2 = imDrawList;
            }
            imDrawList2.popClipRect();
        }
    }

    private static int glowCol(float[] fArray, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        int n;
        block32: {
            float f9;
            float f10;
            float f11;
            float f12;
            block34: {
                float f13;
                block33: {
                    int n2;
                    boolean bl;
                    block31: {
                        block28: {
                            float f14;
                            float f15;
                            block30: {
                                block29: {
                                    block27: {
                                        float f16;
                                        float f17;
                                        block26: {
                                            float f18;
                                            block24: {
                                                block25: {
                                                    f17 = Math.min(1.0f, f / 0.55f);
                                                    bl = ImScreen.a;
                                                    f18 = f;
                                                    if (bl) break block24;
                                                    if (!(f18 < 0.55f)) break block25;
                                                    f16 = 1.0f;
                                                    break block26;
                                                }
                                                f18 = 1.0f;
                                            }
                                            f16 = f18 - (f - 0.55f) / 0.45f;
                                        }
                                        float f19 = f16;
                                        f13 = fArray[4] + (fArray[8] - fArray[4]) * f17;
                                        f12 = fArray[5] + (fArray[9] - fArray[5]) * f17;
                                        f11 = fArray[6] + (fArray[10] - fArray[6]) * f17;
                                        f10 = (fArray[7] + (fArray[11] - fArray[7]) * f17) * f19;
                                        f15 = f2;
                                        if (bl) break block27;
                                        if (!(f15 >= f4)) break block28;
                                        f15 = f2;
                                    }
                                    f14 = f4 + f6;
                                    if (bl) break block29;
                                    if (!(f15 <= f14)) break block28;
                                    f15 = f3;
                                    f14 = f5;
                                }
                                if (bl) break block30;
                                if (!(f15 >= f14)) break block28;
                                f15 = f3;
                                f14 = f5 + f7;
                            }
                            float f20 = f15 - f14;
                            n2 = f20 == 0.0f ? 0 : (f20 < 0.0f ? -1 : 1);
                            if (bl) break block31;
                            if (n2 > 0) break block28;
                            n2 = 1;
                            break block31;
                        }
                        n2 = 0;
                    }
                    int n3 = n2;
                    float f21 = Math.max(Math.max(f4 + f8 - f2, f2 - (f4 + f6 - f8)), 0.0f);
                    float f22 = Math.max(Math.max(f5 + f8 - f3, f3 - (f5 + f7 - f8)), 0.0f);
                    n = n3;
                    if (bl) break block32;
                    if (n == 0) break block33;
                    f9 = f21;
                    if (bl) break block34;
                    if (!(f9 * f21 + f22 * f22 > f8 * f8)) break block33;
                    f10 = 0.0f;
                }
                f9 = f13;
            }
            n = ImGui.getColorU32((float)f9, (float)f12, (float)f11, (float)f10);
        }
        return n;
    }

    public static void box(ImDrawList imDrawList, float f, float f2, float f3, float f4, float[] fArray, float[] fArray2, float f5, float f6) {
        block21: {
            float f7;
            block23: {
                float f8;
                block22: {
                    float[] fArray3;
                    boolean bl;
                    block20: {
                        block18: {
                            block19: {
                                bl = ImScreen.a;
                                fArray3 = fArray;
                                if (bl) break block18;
                                if (fArray3 == null) break block19;
                                fArray3 = fArray;
                                if (bl) break block18;
                                if (!(fArray3[3] > 0.0f)) break block19;
                                imDrawList.addRectFilled(f, f2, f3, f4, Ui.col(fArray), f5);
                            }
                            fArray3 = fArray2;
                        }
                        if (bl) break block20;
                        if (fArray3 == null) break block21;
                        fArray3 = fArray2;
                    }
                    f7 = fArray3[3];
                    f8 = 0.0f;
                    if (bl) break block22;
                    if (!(f7 > f8)) break block21;
                    f7 = f6;
                    if (bl) break block23;
                    f8 = 0.0f;
                }
                if (!(f7 > f8)) break block21;
                f7 = f6 / 2.0f;
            }
            float f9 = f7;
            imDrawList.addRect(f + f9, f2 + f9, f3 - f9, f4 - f9, Ui.col(fArray2), Math.max(0.0f, f5 - f9), 0, f6);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void badge(ImDrawList imDrawList, float f, float f2, float f3, float f4, float f5) {
        long l;
        long l2;
        int n;
        boolean bl;
        int n2;
        block16: {
            ImDrawList imDrawList2;
            block14: {
                block15: {
                    n2 = Ui.vtx(imDrawList);
                    bl = ImScreen.a;
                    imDrawList2 = imDrawList;
                    if (bl) break block14;
                    imDrawList2.addRectFilled(f, f2, f3, f4, Ui.col(Menu.ICON_TOP), f5);
                    if (SHADE) break block15;
                    return;
                }
                imDrawList2 = imDrawList;
            }
            n = Ui.vtx(imDrawList2);
            l2 = MemoryUtil.memGetAddress((long)(imDrawList.ptr + Ui.b(16394, 4324557519413548430L) + Ui.b(10277, 2182879982170570151L)));
            long l3 = l2 - 0L;
            l = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
            if (bl) break block16;
            if (l == false) return;
            l = n;
        }
        if (l <= n2) {
            return;
        }
        float f6 = 0.342f;
        float f7 = 0.94f;
        float f8 = (f + f3) / 2.0f;
        float f9 = (f2 + f4) / 2.0f;
        float f10 = Math.abs((f3 - f) * f6) + Math.abs((f4 - f2) * f7);
        float[] fArray = Menu.ICON_TOP;
        float[] fArray2 = Menu.ICON_FOOT;
        int n3 = n2;
        while (n3 < n) {
            block17: {
                long l4 = l2 + (long)n3 * Ui.b(20959, 116930771181536346L);
                float f11 = MemoryUtil.memGetFloat((long)l4);
                float f12 = MemoryUtil.memGetFloat((long)(l4 + Ui.b(27538, 1894997867898289695L)));
                float f13 = Math.max(0.0f, Math.min(1.0f, 0.5f + ((f11 - f8) * f6 + (f12 - f9) * f7) / f10));
                int n4 = MemoryUtil.memGetInt((long)(l4 + Ui.b(24142, 1459609721438571469L)));
                int n5 = n4 & Ui.a(2969, 935764462510987533L);
                for (int i = 0; i < 3; ++i) {
                    n5 |= Math.round((fArray[i] + (fArray2[i] - fArray[i]) * f13) * 255.0f) << i * 8;
                    if (!bl) {
                        if (!bl) continue;
                        break;
                    }
                    break block17;
                }
                MemoryUtil.memPutInt((long)(l4 + Ui.b(24142, 1459609721438571469L)), (int)n5);
                ++n3;
            }
            if (!bl) continue;
        }
    }

    public static int wash(float f) {
        return ImGui.getColorU32((float)Im.TINT[0], (float)Im.TINT[1], (float)Im.TINT[2], (float)(f * Im.TINT[3]));
    }

    public static float[] mix(float[] fArray, float[] fArray2, float f) {
        return new float[]{fArray[0] + (fArray2[0] - fArray[0]) * f, fArray[1] + (fArray2[1] - fArray[1]) * f, fArray[2] + (fArray2[2] - fArray[2]) * f, fArray[3] + (fArray2[3] - fArray[3]) * f};
    }

    public static float hot() {
        return Motion.to(a[2] + ImGui.getItemID(), ImGui.isItemHovered(), 0.09f);
    }

    
    public static boolean option(String string, boolean bl) {
        boolean bl2;
        boolean bl3;
        boolean bl4;
        block14: {
            ImDrawList imDrawList;
            float f;
            float f2;
            String string2;
            block15: {
                float f3;
                float f4;
                float f5;
                block13: {
                    block12: {
                        String[] stringArray = a;
                        int n = string.indexOf(stringArray[10]);
                        bl4 = ImScreen.a;
                        string2 = (n < 0 ? string : string.substring(0, n)).strip();
                        f2 = ImGui.getCursorScreenPosX();
                        f = ImGui.getCursorScreenPosY();
                        f5 = ImGui.getContentRegionAvailX();
                        f4 = ImGui.getTextLineHeight() + 10.0f;
                        bl3 = ImGui.invisibleButton((String)(a[9] + string), (float)f5, (float)f4);
                        f3 = Ui.hot();
                        imDrawList = ImGui.getWindowDrawList();
                        bl2 = bl;
                        if (bl4) break block12;
                        if (bl2) break block13;
                        float f6 = f3 - 0.01f;
                        bl2 = (f6 == 0.0f ? 0 : (f6 > 0.0f ? 1 : -1)) != 0;
                    }
                    if (bl4) break block14;
                    if (bl2 <= false) break block15;
                }
                Ui.fill(imDrawList, f2, f, f2 + f5, f + f4, ImGui.getColorU32((float)1.0f, (float)1.0f, (float)1.0f, (float)(bl ? 0.1f : 0.06f * f3)), 6.0f);
            }
            imDrawList.addText(f2 + 9.0f, f + 5.0f, ImGui.getColorU32((int)0), string2);
            bl2 = bl3;
        }
        if (!bl4) {
            if (bl2) {
                ImGui.closeCurrentPopup();
            }
            bl2 = bl3;
        }
        return bl2;
    }

    public static float held() {
        return Motion.to(a[8] + ImGui.getItemID(), ImGui.isItemActive(), 0.09f);
    }

    public static float slide(String string, float f, float f2) {
        float f3;
        block17: {
            float f4;
            block18: {
                boolean bl;
                long l;
                Ui$Slide ui$Slide;
                block14: {
                    block15: {
                        Ui$Slide ui$Slide2;
                        block16: {
                            block12: {
                                block13: {
                                    ui$Slide = SLIDES.get(string);
                                    l = Im.frames();
                                    bl = ImScreen.a;
                                    ui$Slide2 = ui$Slide;
                                    if (bl) break block12;
                                    if (ui$Slide2 == null) break block13;
                                    if (l - ui$Slide.seen <= 1L) break block14;
                                }
                                ui$Slide2 = ui$Slide;
                            }
                            if (bl) break block15;
                            if (ui$Slide2 != null) break block16;
                            ui$Slide = new Ui$Slide();
                            SLIDES.put(string, ui$Slide);
                        }
                        ui$Slide.seen = l;
                        ui$Slide2 = ui$Slide;
                    }
                    ui$Slide2.from = ui$Slide.to = f;
                    return ui$Slide.to;
                }
                ui$Slide.seen = l;
                f4 = ui$Slide.from + (ui$Slide.to - ui$Slide.from) * Motion.out(Motion.since(string) / f2);
                f3 = ui$Slide.to;
                if (bl) break block17;
                if (f3 == f) break block18;
                ui$Slide.from = f4;
                ui$Slide.to = f;
                Motion.restart(string);
            }
            f3 = f4;
        }
        return f3;
    }

    public static void underline(String string, float f, float f2, float f3, float f4) {
        float f5 = f + Ui.slide(string + "x", f2, 0.15f);
        float f6 = Ui.slide(string + "w", f3, 0.15f);
        ImGui.getWindowDrawList().addRectFilledMultiColor(f5, f4 - 2.0f, f5 + f6, f4, Ui.col(Menu.ICON_TOP), Ui.col(Menu.ICON_FOOT), Ui.col(Menu.ICON_FOOT), Ui.col(Menu.ICON_TOP));
    }

    public static void lift() {
        block4: {
            float f = ImGui.getWindowPosX();
            float f2 = ImGui.getWindowPosY();
            boolean bl = ImScreen.a;
            float f3 = f + ImGui.getWindowWidth();
            float f4 = f2 + ImGui.getWindowHeight();
            float f5 = ImGui.getStyle().getPopupRounding();
            ImDrawList imDrawList = ImGui.getWindowDrawList();
            imDrawList.pushClipRect(f - 14.0f, f2 - 14.0f, f3 + 14.0f, f4 + 14.0f, false);
            for (int i = 1; i <= 12; ++i) {
                float f6 = 1.0f - (float)i / 13.0f;
                imDrawList.addRect(f - (float)i, f2 - (float)i, f3 + (float)i, f4 + (float)i, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)(0.28f * f6 * f6)), f5 + (float)i, 0, 1.5f);
                if (!bl) {
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            imDrawList.popClipRect();
        }
    }

    public static float drop(String string) {
        float f = Motion.to(a[3] + ImGui.getID((String)string), ImGui.isPopupOpen((String)string), 0.15f);
        ImGui.pushStyleVar((int)0, (float)(ImGui.getStyle().getAlpha() * Math.max(0.02f, f)));
        return -4.0f * (1.0f - f);
    }

    public static void head(UUID uUID, float f, float f2, float f3) {
        long l;
        long l2;
        ImDrawList imDrawList;
        block6: {
            block7: {
                imDrawList = ImGui.getWindowDrawList();
                l2 = 0L;
                boolean bl = ImScreen.a;
                try {
                    l2 = Ui.tex(SkinFetcher.skinFor(uUID).comp_1626().comp_3627());
                }
                catch (RuntimeException runtimeException) {
                }
                l = l2;
                if (bl) break block6;
                if (l != 0L) break block7;
                Ui.fill(imDrawList, f, f2, f + f3, f2 + f3, Ui.wash(0.16f), f3 * 0.26f);
                return;
            }
            l = l2;
        }
        Ui.sharp(l);
        int n = Ui.col(WHITE);
        imDrawList.addImageRounded(l2, f, f2, f + f3, f2 + f3, 0.125f, 0.125f, 0.25f, 0.25f, n, f3 * 0.26f);
        imDrawList.addImageRounded(l2, f - 1.0f, f2 - 1.0f, f + f3 + 1.0f, f2 + f3 + 1.0f, 0.625f, 0.125f, 0.75f, 0.25f, n, f3 * 0.26f);
    }

    public static void plusLogo(float f, float f2, float f3, boolean bl) {
        String[] stringArray;
        String string;
        String string2;
        block6: {
            block5: {
                string2 = a[5];
                if (!bl) break block5;
                string = a[6];
                break block6;
            }
            stringArray = a;
            string = stringArray[7];
        }
        stringArray = a;
        long l = Ui.tex(Identifier.method_60655((String)string2, (String)(stringArray[0] + string + stringArray[1])));
        if (l != 0L) {
            ImGui.getWindowDrawList().addImage(l, f, f2, f + f3, f2 + f3, 0.0f, 0.0f, 1.0f, 1.0f, Ui.col(WHITE));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void plusText(ImFont imFont, float f, float f2, String string) {
        int n;
        int n2;
        long l;
        boolean bl;
        int n3;
        block13: {
            block12: {
                ImDrawList imDrawList = ImGui.getWindowDrawList();
                n3 = Ui.vtx(imDrawList);
                Ui.text(imFont, f, f2, PLUS_FOOT, string);
                bl = ImScreen.a;
                l = MemoryUtil.memGetAddress((long)(imDrawList.ptr + Ui.b(16394, 4324557519413548430L) + Ui.b(10277, 2182879982170570151L)));
                n2 = Ui.vtx(imDrawList);
                n = SHADE;
                if (bl) break block12;
                if (n == 0) return;
                long l2 = l - 0L;
                n = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
            }
            if (bl) break block13;
            if (n == 0) return;
            n = n2;
        }
        if (n <= n3) {
            return;
        }
        float f3 = f2 + Ui.cap(imFont);
        float f4 = f2 + Ui.base(imFont);
        int n4 = n3;
        block8: while (true) {
            int n5 = n4;
            int n6 = n2;
            block9: while (n5 < n6) {
                long l3 = l + (long)n4 * Ui.b(20959, 116930771181536346L);
                float f5 = Math.max(0.0f, Math.min(1.0f, (MemoryUtil.memGetFloat((long)(l3 + Ui.b(27538, 1894997867898289695L))) - f3) / (f4 - f3)));
                int n7 = MemoryUtil.memGetInt((long)(l3 + Ui.b(24142, 1459609721438571469L)));
                int n8 = n7 & Ui.a(2969, 935764462510987533L);
                for (int i = 0; i < 3; ++i) {
                    n5 = n8;
                    n6 = Math.round((PLUS_TOP[i] + (PLUS_FOOT[i] - PLUS_TOP[i]) * f5) * 255.0f);
                    if (bl) continue block9;
                    n8 = n5 | n6 << i * 8;
                    if (!bl) continue;
                }
                MemoryUtil.memPutInt((long)(l3 + Ui.b(24142, 1459609721438571469L)), (int)n8);
                ++n4;
                if (!bl) continue block8;
            }
            break;
        }
    }

    static void sharp(long l) {
        int n;
        block4: {
            block5: {
                boolean bl = ImScreen.a;
                n = Im.vulkan();
                if (bl) break block4;
                if (n == 0) break block5;
                NEAREST.set((int)l);
                return;
            }
            n = GL11.glGetInteger((int)Ui.a(13468, 6406976871218734607L));
        }
        int n2 = n;
        GL11.glBindTexture((int)3553, (int)((int)l));
        GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        GL11.glBindTexture((int)3553, (int)n2);
    }

    static void frame() {
        VIEWS.clear();
        NEAREST.clear();
    }

    static GpuTextureView view(int n) {
        GpuTextureView gpuTextureView;
        block8: {
            block7: {
                int n2;
                block6: {
                    boolean bl = ImScreen.a;
                    n2 = n;
                    if (bl) break block6;
                    if (n2 < 2) break block7;
                    n2 = n - 2;
                }
                if (n2 >= VIEWS.size()) break block7;
                gpuTextureView = VIEWS.get(n - 2);
                break block8;
            }
            gpuTextureView = null;
        }
        return gpuTextureView;
    }

    static boolean nearest(int n) {
        return NEAREST.get(n);
    }

    
    public static long tex(Identifier identifier) {
        boolean bl = ImScreen.a;
        try {
            GpuTexture gpuTexture2;
            if (identifier == null) {
                return 0L;
            }
            AbstractTexture abstractTexture = MinecraftClient.method_1551().method_1531().method_4619(identifier);
            if (bl) return VIEWS.size() + 1;
            if (Im.vulkan()) {
                VIEWS.add(abstractTexture.method_71659());
                return VIEWS.size() + 1;
            }
            GpuTexture gpuTexture = gpuTexture2 = abstractTexture.method_68004();
            if (!bl) {
                if (!(gpuTexture instanceof GlTexture)) return 0L;
                gpuTexture = gpuTexture2;
            }
            GlTexture glTexture = (GlTexture)gpuTexture;
            return glTexture.method_68427();
        }
        catch (RuntimeException runtimeException) {
        }
        return 0L;
    }

    public static long pixels(Identifier identifier) {
        long l;
        block4: {
            long l2;
            block5: {
                l2 = Ui.tex(identifier);
                boolean bl = ImScreen.a;
                l = l2;
                if (bl) break block4;
                if (l == 0L) break block5;
                Ui.sharp(l2);
            }
            l = l2;
        }
        return l;
    }

    public static void fill(ImDrawList imDrawList, float f, float f2, float f3, float f4, int n) {
        Ui.fill(imDrawList, f, f2, f3, f4, n, 0.0f, 0);
    }

    public static void fill(ImDrawList imDrawList, float f, float f2, float f3, float f4, int n, float f5) {
        Ui.fill(imDrawList, f, f2, f3, f4, n, f5, 0);
    }

    
    public static void fill(ImDrawList imDrawList, float f, float f2, float f3, float f4, int n, float f5, int n2) {
        int n3;
        block8: {
            block9: {
                block7: {
                    boolean bl;
                    block6: {
                        bl = ImScreen.a;
                        n3 = SHADE;
                        if (bl) break block6;
                        if (n3 == 0) break block7;
                        float f6 = f4 - f2 - 22.0f;
                        n3 = f6 == 0.0f ? 0 : (f6 < 0.0f ? -1 : 1);
                    }
                    if (bl) break block8;
                    if (n3 >= 0) break block9;
                }
                imDrawList.addRectFilled(f, f2, f3, f4, n, f5, n2);
                return;
            }
            n3 = Ui.vtx(imDrawList);
        }
        int n4 = n3;
        imDrawList.addRectFilled(f, f2, f3, f4, n, f5, n2);
        Ui.shade(imDrawList, n4, f2, f4, n, false);
    }

    public static void accent(ImDrawList imDrawList, float f, float f2, float f3, float f4, int n, float f5) {
        Ui.accent(imDrawList, f, f2, f3, f4, n, f5, 1.0f);
    }

    public static void accent(ImDrawList imDrawList, float f, float f2, float f3, float f4, int n, float f5, float f6) {
        int n2 = Ui.vtx(imDrawList);
        Ui.fill(imDrawList, f, f2, f3, f4, n, f5);
        Ui.across(imDrawList, n2, f, f3, Menu.GRAD_A, Menu.GRAD_B, Menu.FILL, f6);
    }

    public static void gradient(ImDrawList imDrawList, float f, float f2, float f3, float f4, float[] fArray, float[] fArray2, float f5) {
        int n = Ui.vtx(imDrawList);
        Ui.fill(imDrawList, f, f2, f3, f4, Ui.col(fArray), f5);
        Ui.across(imDrawList, n, f, f3, fArray, fArray2, fArray, 1.0f);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void across(ImDrawList imDrawList, int n, float f, float f2, float[] fArray, float[] fArray2, float[] fArray3, float f3) {
        long l;
        long l2;
        float f4;
        boolean bl;
        block29: {
            long l3;
            block30: {
                block28: {
                    float f5;
                    block26: {
                        block27: {
                            block25: {
                                float f6;
                                float f7;
                                block24: {
                                    int n2;
                                    block23: {
                                        bl = ImScreen.a;
                                        n2 = SHADE;
                                        if (bl) break block23;
                                        if (n2 == 0) return;
                                        f7 = f3;
                                        f6 = 0.0f;
                                        if (bl) break block24;
                                        float f8 = f7 - f6;
                                        n2 = f8 == 0.0f ? 0 : (f8 < 0.0f ? -1 : 1);
                                    }
                                    if (n2 <= 0) return;
                                    f7 = f2;
                                    f6 = f;
                                }
                                float f9 = f7 - f6;
                                f5 = f9 == 0.0f ? 0 : (f9 < 0.0f ? -1 : 1);
                                if (bl) break block25;
                                if (f5 <= 0) return;
                                f5 = (float)Arrays.equals(fArray, fArray2);
                            }
                            if (bl) break block26;
                            if (f5 == false) break block27;
                            return;
                        }
                        f5 = Ui.vtx(imDrawList);
                    }
                    f4 = f5;
                    l2 = MemoryUtil.memGetAddress((long)(imDrawList.ptr + Ui.b(16394, 4324557519413548430L) + Ui.b(10277, 2182879982170570151L)));
                    long l4 = l2 - 0L;
                    l3 = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                    if (bl) break block28;
                    if (l3 == false) return;
                    l3 = (long)f4;
                }
                if (bl) break block29;
                if (l3 > n) break block30;
                return;
            }
            l3 = l = (long)n;
        }
        while (l < f4) {
            block31: {
                long l5 = l2 + (long)l * Ui.b(20959, 116930771181536346L);
                float f10 = Math.max(0.0f, Math.min(1.0f, (MemoryUtil.memGetFloat((long)l5) - f) / (f2 - f)));
                int n3 = MemoryUtil.memGetInt((long)(l5 + Ui.b(24142, 1459609721438571469L)));
                int n4 = n3 & Ui.a(2969, 935764462510987533L);
                for (int i = 0; i < 3; ++i) {
                    float f11 = (fArray[i] + (fArray2[i] - fArray[i]) * f10 - fArray3[i]) * 255.0f * f3;
                    if (!bl) {
                        n4 |= Math.max(0, Math.min(255, Math.round((float)(n3 >>> i * 8 & 0xFF) + f11))) << i * 8;
                        if (!bl) continue;
                    }
                    break block31;
                }
                MemoryUtil.memPutInt((long)(l5 + Ui.b(24142, 1459609721438571469L)), (int)n4);
                ++l;
            }
            if (!bl) continue;
        }
    }

    public static void rect(ImDrawList imDrawList, float f, float f2, float f3, float f4, int n, float f5) {
        Ui.rect(imDrawList, f, f2, f3, f4, n, f5, 0, 1.0f);
    }

    
    public static void rect(ImDrawList imDrawList, float f, float f2, float f3, float f4, int n, float f5, int n2, float f6) {
        int n3;
        block8: {
            block9: {
                block7: {
                    boolean bl;
                    block6: {
                        bl = ImScreen.a;
                        n3 = SHADE;
                        if (bl) break block6;
                        if (n3 == 0) break block7;
                        float f7 = f4 - f2 - 22.0f;
                        n3 = f7 == 0.0f ? 0 : (f7 < 0.0f ? -1 : 1);
                    }
                    if (bl) break block8;
                    if (n3 >= 0) break block9;
                }
                imDrawList.addRect(f, f2, f3, f4, n, f5, n2, f6);
                return;
            }
            n3 = Ui.vtx(imDrawList);
        }
        int n4 = n3;
        imDrawList.addRect(f, f2, f3, f4, n, f5, n2, f6);
        Ui.shade(imDrawList, n4, f2, f4, n, true);
    }

    private static int vtx(ImDrawList imDrawList) {
        return MemoryUtil.memGetInt((long)(imDrawList.ptr + Ui.b(16394, 4324557519413548430L)));
    }

    
    private static void shade(ImDrawList var0, int var1_1, float var2_2, float var3_3, int var4_4, boolean var5_5) {
        block40: {
            block39: {
                block38: {
                    block36: {
                        block37: {
                            block33: {
                                block35: {
                                    block34: {
                                        block32: {
                                            var7_6 = Ui.vtx(var0);
                                            var8_7 = MemoryUtil.memGetAddress((long)(var0.ptr + Ui.b(16394, 4324557519413548430L) + Ui.b(10277, 2182879982170570151L)));
                                            var6_8 = ImScreen.a;
                                            var10_9 = var4_4 >>> 24;
                                            cfr_temp_0 = var8_7 - 0L;
                                            v0 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                            if (var6_8) break block32;
                                            if (v0 == false) break block33;
                                            v0 = var1_1;
                                        }
                                        if (var6_8) break block34;
                                        if (v0 < 0) break block33;
                                        v0 = var7_6;
                                    }
                                    if (var6_8) break block35;
                                    if (v0 <= var1_1) break block33;
                                    v0 = var10_9;
                                }
                                if (var6_8) break block36;
                                if (v0 != false) break block37;
                            }
                            return;
                        }
                        v0 = var4_4 & 255;
                    }
                    var11_10 = v0;
                    var12_11 = var4_4 >>> 8 & 255;
                    var13_12 = var4_4 >>> 16 & 255;
                    v6 = var10_9;
                    if (var6_8) break block38;
                    if (v6 >= 128) break block39;
                    cfr_temp_1 = 0.2126f * (float)var11_10 + 0.7152f * (float)var12_11 + 0.0722f * (float)var13_12 - 128.0f;
                    v6 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 > 0.0f ? 1 : -1);
                }
                if (var6_8) break block40;
                if (v6 <= 0) break block39;
                v6 = 1;
                break block40;
            }
            v6 = 0;
        }
        var14_13 = v6;
        var15_14 = Math.min(1.0f, 90.0f / (var3_3 - var2_2));
        var16_15 = ImGui.getStyle().getAlpha();
        var17_16 = var1_1;
        block24: while (true) {
            v10 = var17_16;
            block25: while (v10 < var7_6) {
                block42: {
                    block44: {
                        block43: {
                            block41: {
                                var18_17 = var8_7 + (long)var17_16 * Ui.b(20959, 116930771181536346L);
                                var20_18 = Math.max(0.0f, Math.min(1.0f, (MemoryUtil.memGetFloat((long)(var18_17 + Ui.b(27538, 1894997867898289695L))) - var2_2) / (var3_3 - var2_2)));
                                var21_19 = MemoryUtil.memGetInt((long)(var18_17 + Ui.b(2045, 1712957553235284604L)));
                                var22_20 = (float)(var21_19 >>> 24) / (float)var10_9;
                                v11 = var5_5;
                                if (var6_8) break block41;
                                if (v11 != 0) {
                                }
                                var23_21 = Math.min(255, Math.round((float)var10_9 * var22_20 * (1.3f - 0.6f * var20_18))) << 24 | var4_4 & Ui.a(10916, 8613004413137156145L);
                                if (!var6_8) break block42;
lbl96:

                                v11 = var14_13;
                            }
                            if (var6_8) break block43;
                            if (v11 == 0) break block44;
                            v11 = Math.min(255, Math.round(((float)var10_9 + 14.0f * var15_14 * var16_15 * (1.0f - var20_18)) * var22_20)) << 24 | var4_4 & Ui.a(18016, 2702821833562133744L);
                        }
                        var23_21 = v11;
                        if (!var6_8) break block42;
                    }
                    var24_22 = 0.05f * var15_14 * (1.0f - var20_18);
                    var23_21 = var21_19 & Ui.a(2969, 935764462510987533L);
                    for (var25_23 = 0; var25_23 < 24; var25_23 += 8) {
                        var26_24 = var4_4 >>> var25_23 & 255;
                        v10 = var23_21 | Math.round((float)var26_24 + (float)(255 - var26_24) * var24_22) << var25_23;
                        if (var6_8) continue block25;
                        var23_21 = v10;
                        if (!var6_8) continue;
                    }
                }
                MemoryUtil.memPutInt((long)(var18_17 + Ui.b(24142, 1459609721438571469L)), (int)var23_21);
                ++var17_16;
                if (!var6_8) continue block24;
            }
            break;
        }
    }

    public static boolean icon(String string, float f, float f2, float f3, float[] fArray) {
        block4: {
            long l;
            block5: {
                boolean bl = ImScreen.a;
                l = Ui.tex(VectorIcons.texture(string, Math.round(f3 * 2.0f)));
                if (bl) break block4;
                if (l != 0L) break block5;
                return false;
            }
            ImGui.getWindowDrawList().addImage(l, f, f2, f + f3, f2 + f3, 0.0f, 0.0f, 1.0f, 1.0f, Ui.col(fArray));
        }
        return true;
    }

    public static void rim(float f, float f2, float f3, float f4, float f5, float[] fArray, float[] fArray2) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        int n = 8;
        float f6 = Im.bake();
        boolean bl = ImScreen.a;
        for (int i = 0; i < n; ++i) {
            int n2;
            float f7;
            float f8;
            ImDrawList imDrawList2;
            float f9;
            float f10;
            block5: {
                block6: {
                    f10 = ((float)i + 0.5f) / (float)n;
                    float f11 = (float)Math.round((f2 + f4 * (float)i / (float)n) * f6) / f6;
                    f9 = (float)Math.round((f2 + f4 * (float)(i + 1) / (float)n) * f6) / f6;
                    imDrawList2 = imDrawList;
                    f8 = f - 1.0f;
                    f7 = f11;
                    n2 = i;
                    if (bl) break block5;
                    if (n2 != 0) break block6;
                    n2 = 1;
                    break block5;
                }
                n2 = 0;
            }
            float f12 = f7 - (float)n2;
            float f13 = f + f3 + 1.0f;
            float f14 = f9;
            float f15 = i == n - 1 ? 1.0f : 1.0f / f6;
            imDrawList2.pushClipRect(f8, f12, f13, f14 + f15, true);
            imDrawList.addRect(f, f2, f + f3, f2 + f4, ImGui.getColorU32((float)(fArray[0] + (fArray2[0] - fArray[0]) * f10), (float)(fArray[1] + (fArray2[1] - fArray[1]) * f10), (float)(fArray[2] + (fArray2[2] - fArray[2]) * f10), (float)(fArray[3] + (fArray2[3] - fArray[3]) * f10)), f5);
            imDrawList.popClipRect();
            if (!bl) continue;
        }
    }

    public static void icon(String string, float f, float f2, float f3, float[] fArray, float[] fArray2) {
        long l = Ui.tex(VectorIcons.texture(string, Math.round(f3 * 2.0f)));
        boolean bl = ImScreen.a;
        if (l == 0L) {
            return;
        }
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        int n = 20;
        for (int i = 0; i < n; ++i) {
            float f4 = (float)i / (float)n;
            float f5 = (float)(i + 1) / (float)n;
            float f6 = (f4 + f5) / 2.0f;
            int n2 = ImGui.getColorU32((float)(fArray[0] + (fArray2[0] - fArray[0]) * f6), (float)(fArray[1] + (fArray2[1] - fArray[1]) * f6), (float)(fArray[2] + (fArray2[2] - fArray[2]) * f6), (float)(fArray[3] + (fArray2[3] - fArray[3]) * f6));
            imDrawList.addImage(l, f, f2 + f3 * f4, f + f3, f2 + f3 * f5, 0.0f, f4, 1.0f, f5, n2);
            if (!bl) continue;
        }
    }

    public static boolean entered() {
        int n;
        block14: {
            block13: {
                block15: {
                    boolean bl;
                    boolean bl2;
                    block12: {
                        bl2 = ImScreen.a;
                        bl = ImGui.isItemDeactivated();
                        if (bl2) break block12;
                        if (!bl) break block13;
                        n = 525;
                        if (bl2) break block14;
                        bl = ImGui.isKeyPressed((int)n);
                    }
                    if (bl) break block15;
                    n = 627;
                    if (bl2) break block14;
                    if (!ImGui.isKeyPressed((int)n)) break block13;
                }
                n = 1;
                break block14;
            }
            n = 0;
        }
        return n != 0;
    }

    
    public static float wrap(ImFont var0, float var1_1, float var2_2, float var3_3, float[] var4_4, String var5_5, float var6_6) {
        block17: {
            var8_7 = "";
            var9_8 = var2_2;
            var10_9 = var5_5.split(" ");
            var11_10 = var10_9.length;
            var7_12 = ImScreen.a;
            for (var12_11 = 0; var12_11 < var11_10; ++var12_11) {
                block23: {
                    block21: {
                        block22: {
                            block20: {
                                block18: {
                                    block19: {
                                        var13_13 = var10_9[var12_11];
                                        if (var7_12) break block17;
                                        v0 = var8_7;
                                        if (var7_12) break block18;
                                        if (!v0.isEmpty()) break block19;
                                        v3 = var13_13;
                                        break block20;
                                    }
                                    v0 = var8_7;
                                }
                                v3 = v0 + " " + var13_13;
                            }
                            var14_14 = v3;
                            if (var7_12) break block21;
                            if (var8_7.isEmpty()) break block22;
                            v6 = Ui.width(var0, var14_14);
                            if (var7_12) break block23;
                            if (v6 <= var3_3) {
                            }
                        }
                        var8_7 = var14_14;
                    }
                    if (!var7_12) continue;
lbl54:

                    Ui.text(var0, var1_1, Ui.mid(var0, var9_8, var6_6), var4_4, (String)var8_7);
                    v6 = var9_8 + var6_6;
                }
                var9_8 = v6;
                var8_7 = var13_13;
                if (!var7_12) continue;
            }
            if (!var8_7.isEmpty()) {
                Ui.text(var0, var1_1, Ui.mid(var0, var9_8, var6_6), var4_4, (String)var8_7);
                var9_8 += var6_6;
            }
        }
        return var9_8 - var2_2;
    }

    public static void label(float f, float f2, String string) {
        Ui.text(Im.label, f, f2, Im.MUTE, string.toUpperCase(Locale.ROOT));
    }

    
    static {
        block39: {
            block38: {
                block37: {
                    block36: {
                        block35: {
                            block34: {
                                var21 = new String[11];
                                var19_1 = 0;
                                var18_2 = "~&K<aVHylT=}\u000b\u0004$3]/\u0003b,G\u0004n1\\8\u0002y4\u0006k6A'fE\nz/F;KFLn$V\tz/F;KHBm,\u0004b&_,";
                                var20_3 = "~&K<aVHylT=}\u000b\u0004$3]/\u0003b,G\u0004n1\\8\u0002y4\u0006k6A'fE\nz/F;KFLn$V\tz/F;KHBm,\u0004b&_,".length();
                                var17_4 = 13;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block34;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = ")`\\8`\u0002)`";
                                    var20_3 = ")`\\8`\u0002)`".length();
                                    var17_4 = 5;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block34;
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
                                    break block35;
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
                                        v10 = 10;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 67;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 51;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 72;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 20;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 36;
                                        break;
                                    }
                                    default: {
                                        v10 = 45;
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
                        Ui.a = var21;
                        var8_7 = 6667092846341233383L;
                        var14_8 = new long[6];
                        var11_9 = 0;
                        var12_10 = "`\u00f4\u00c02\u0086\u00ecq\u00e8p#\u0084 \u00ddnk5\\\u001dX\u0091\u0003J\u00b8Q\u0088r\u00c9*{\u0095\u00fc\u0081";
                        var13_11 = "`\u00f4\u00c02\u0086\u00ecq\u00e8p#\u0084 \u00ddnk5\\\u001dX\u0091\u0003J\u00b8Q\u0088r\u00c9*{\u0095\u00fc\u0081".length();
                        var10_12 = 0;
                        while (true) {
                            var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                            v11 = var14_8;
                            v12 = var11_9++;
                            v13 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                            v14 = -1;
                            break block36;
                            break;
                        }
lbl89:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_12 < var13_11) continue;
                            var12_10 = "\u00b3iNMT\u00d1C\u00ea\u0095\u0087#\u00c5\u00fb\u00a1\u001d)";
                            var13_11 = "\u00b3iNMT\u00d1C\u00ea\u0095\u0087#\u00c5\u00fb\u00a1\u001d)".length();
                            var10_12 = 0;
                            while (true) {
                                var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                                v11 = var14_8;
                                v12 = var11_9++;
                                v13 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                                v14 = 0;
                                break block36;
                                break;
                            }
                            break;
                        }
lbl102:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_12 < var13_11) continue;
                            break block37;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_7;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl113:

                        continue;
                    }
                }
                Ui.b = var14_8;
                Ui.c = new Integer[6];
                var0_14 = 7103676547726875271L;
                var6_15 = new long[11];
                var3_16 = 0;
                var4_17 = "^\u0096\u00be\b)\u00eb\u000f)c\n?\u00c4]\u001e\u001e\u00c9\u0006w0K\u008a\u0012\u0094\u00ebt\u008a\u0095\u00c7\u00b2\u0013\u008a\u0084\u0007,s\u009d\u008c\u00c5\u00e9\u0096uP\u00f1\u00ffAa\u00c8\u00eb|\u00deq\u00e9\u00ae\u001e\u00e7(v\u00d4\u00c7`\u0007\u0085\u0011Zi\u00f2\u0094/\u001e\u00a4G\u00bc";
                var5_18 = "^\u0096\u00be\b)\u00eb\u000f)c\n?\u00c4]\u001e\u001e\u00c9\u0006w0K\u008a\u0012\u0094\u00ebt\u008a\u0095\u00c7\u00b2\u0013\u008a\u0084\u0007,s\u009d\u008c\u00c5\u00e9\u0096uP\u00f1\u00ffAa\u00c8\u00eb|\u00deq\u00e9\u00ae\u001e\u00e7(v\u00d4\u00c7`\u0007\u0085\u0011Zi\u00f2\u0094/\u001e\u00a4G\u00bc".length();
                var2_19 = 0;
                while (true) {
                    var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                    v16 = var6_15;
                    v17 = var3_16++;
                    v18 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                    v19 = -1;
                    break block38;
                    break;
                }
lbl130:

                while (true) {
                    v16[v17] = v20;
                    if (var2_19 < var5_18) continue;
                    var4_17 = "x\u00d96\u00ab\u00a4\u00bb\u00a4\u009c\u0006F\u00f9\u00f0\u008ez\u0000W";
                    var5_18 = "x\u00d96\u00ab\u00a4\u00bb\u00a4\u009c\u0006F\u00f9\u00f0\u008ez\u0000W".length();
                    var2_19 = 0;
                    while (true) {
                        var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                        v16 = var6_15;
                        v17 = var3_16++;
                        v18 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                        v19 = 0;
                        break block38;
                        break;
                    }
                    break;
                }
lbl143:

                while (true) {
                    v16[v17] = v20;
                    if (var2_19 < var5_18) continue;
                    break block39;
                    break;
                }
            }
            v20 = v18 ^ var0_14;
            switch (v19) {
                default: {
                    continue;
                }
                case 0:
lbl154:

                continue;
            }
        }
        Ui.d = var6_15;
        Ui.e = new Long[11];
        Ui.VTX = Ui.b(9905, 7329933519991173937L);
        Ui.SIZE = new ImVec2();
        Ui.TRACK = Im.TRACK;
        Ui.KNOB = Im.KNOB;
        Ui.WHITE = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
        Ui.SLIDES = new HashMap<String, Ui$Slide>();
        Ui.PLUS_TOP = new float[]{1.0f, 0.761f, 0.925f, 1.0f};
        Ui.PLUS_FOOT = new float[]{0.627f, 0.361f, 1.0f, 1.0f};
        Ui.VIEWS = new ArrayList<GpuTextureView>();
        Ui.NEAREST = new BitSet();
        v21 = ImDrawData.sizeOfImDrawVert() == 20;
        Ui.SHADE = v21;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5290;
        if (c[n2] == null) {
            Ui.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x5584) & Short.MAX_VALUE;
        if (e[n2] == null) {
            Ui.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
