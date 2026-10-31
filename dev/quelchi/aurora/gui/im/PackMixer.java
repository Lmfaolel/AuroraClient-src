package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.Anim;
import dev.quelchi.aurora.gui.PackMix;
import dev.quelchi.aurora.gui.PackMix$Entry;
import dev.quelchi.aurora.gui.PackMix$Version;
import dev.quelchi.aurora.gui.PackPreview$Face;
import dev.quelchi.aurora.gui.PackTextures$Sprite;
import dev.quelchi.aurora.gui.im.Edit;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Motion;
import dev.quelchi.aurora.gui.im.PackMixer$1;
import dev.quelchi.aurora.gui.im.Ui;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.type.ImString;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.joml.Matrix3f;
import org.joml.Matrix3fc;
import org.joml.Vector3f;

public final class PackMixer
extends ImScreen {
    private static final float PAD = 20.0f;
    private static final float PANE = 280.0f;
    private static final float TILE = 150.0f;
    private static final float[] NO_TINT;
    private static String category;
    private static int entry;
    private static String editing;
    private static final ImString name;
    private static final Map<String, PackMix$Version[]> copies;
    private static int copied;
    private final Screen parent;
    private boolean exporting;
    private boolean statusOk;
    private String status;
    private static final float[][] BOX;
    private static final float SIN_45;
    private static final String[] b;

    public PackMixer(Screen screen) {
        String[] stringArray = b;
        super(stringArray[30]);
        this.status = "";
        this.parent = screen;
        Motion.restart(stringArray[13]);
    }

    protected void method_25426() {
        block5: {
            String string;
            block4: {
                boolean bl = ImScreen.a;
                string = category;
                if (bl) break block4;
                if (PackMix.ready(string)) break block5;
                string = category;
            }
            PackMix.load(string);
        }
    }

    public void method_25432() {
        PackMix.close();
    }

    @Override
    public void method_25419() {
        this.field_22787.method_1507(this.parent);
    }

    @Override
    protected void draw() {
        block8: {
            float f;
            float f2;
            float f3;
            float f4;
            float f5;
            PackMixer packMixer;
            block6: {
                float f6;
                float f7;
                float f8;
                float f9;
                block7: {
                    float f10 = ImGui.getIO().getDisplaySizeX();
                    float f11 = ImGui.getIO().getDisplaySizeY();
                    String[] stringArray = b;
                    float f12 = Motion.out(Motion.since(stringArray[11]) / 0.2f);
                    ImGui.getBackgroundDrawList().addRectFilled(0.0f, 0.0f, f10, f11, Ui.col(Im.DIM, 0.62f * f12));
                    boolean bl = ImScreen.a;
                    ImGui.setNextWindowPos((float)0.0f, (float)0.0f);
                    ImGui.setNextWindowSize((float)f10, (float)f11);
                    ImGui.pushStyleVar((int)2, (float)0.0f, (float)0.0f);
                    ImGui.begin((String)stringArray[13], (int)8367);
                    ImGui.popStyleVar();
                    ImGui.pushStyleVar((int)0, (float)Math.max(0.02f, f12));
                    f9 = Math.min(1066.0f, f10 - 40.0f);
                    f8 = Math.min(611.0f, f11 - 40.0f);
                    f7 = (f10 - f9) / 2.0f;
                    f6 = (f11 - f8) / 2.0f + 16.0f * (1.0f - f12);
                    ImDrawList imDrawList = ImGui.getWindowDrawList();
                    imDrawList.addRectFilled(f7, f6, f7 + f9, f6 + f8, Menu.bg(), 14.0f);
                    Ui.rect(imDrawList, f7, f6, f7 + f9, f6 + f8, Ui.col(Im.RIM), 14.0f);
                    imDrawList.addLine(f7, f6 + 56.0f, f7 + f9, f6 + 56.0f, Ui.col(Im.LINE));
                    imDrawList.addLine(f7 + f9 - 280.0f, f6 + 56.0f, f7 + f9 - 280.0f, f6 + f8, Ui.col(Im.LINE));
                    this.bar(f7, f6, f9);
                    packMixer = this;
                    f5 = f7 + 20.0f;
                    f4 = f6 + 56.0f + 20.0f;
                    f3 = f9 - 280.0f - 40.0f;
                    f2 = f6;
                    f = f8;
                    if (bl) break block6;
                    packMixer.grid(f5, f4, f3, f2 + f - 1.0f);
                    if (editing == null) break block7;
                    this.editor(f7 + f9 - 280.0f + 14.0f, f6 + 56.0f + 14.0f, 252.0f, f6 + f8 - 14.0f);
                    if (!bl) break block8;
                }
                packMixer = this;
                f5 = f7 + f9 - 280.0f + 14.0f;
                f4 = f6 + 56.0f + 14.0f;
                f3 = 252.0f;
                f2 = f6 + f8;
                f = 14.0f;
            }
            packMixer.picks(f5, f4, f3, f2 - f);
        }
        ImGui.popStyleVar();
        ImGui.end();
    }

    private void bar(float f, float f2, float f3) {
        ImGui.setCursorScreenPos((float)(f + 20.0f), (float)(f2 + 14.0f));
        String[] stringArray = b;
        boolean bl = ImGui.invisibleButton((String)stringArray[36], (float)28.0f, (float)28.0f);
        boolean bl2 = ImGui.isItemHovered();
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        float f4 = f + 20.0f + 14.0f;
        ImDrawList imDrawList2 = imDrawList;
        float f5 = f4;
        float f6 = f2 + 28.0f;
        float f7 = 14.0f;
        float[] fArray = bl2 ? Im.QUIET_HI : Im.QUIET;
        imDrawList2.addCircleFilled(f5, f6, f7, Ui.col(fArray), 32);
        imDrawList.addCircle(f4, f2 + 28.0f, 14.0f, Ui.wash(0.16f), 32);
        float[] fArray2 = bl2 ? Im.INK : Im.SUB;
        int n = Ui.col(fArray2);
        imDrawList.addLine(f4 + 1.5f, f2 + 23.0f, f4 - 3.5f, f2 + 28.0f, n, 1.6f);
        imDrawList.addLine(f4 - 3.5f, f2 + 28.0f, f4 + 1.5f, f2 + 33.0f, n, 1.6f);
        stringArray = b;
        Ui.text(Im.sora13, f + 20.0f + 38.0f, Ui.mid(Im.sora13, f2, 56.0f), Im.INK, stringArray[10]);
        imDrawList.addLine(f + f3 - 55.0f, f2, f + f3 - 55.0f, f2 + 56.0f, Ui.col(Im.LINE));
        ImGui.setCursorScreenPos((float)(f + f3 - 54.0f), (float)(f2 + 1.0f));
        bl |= ImGui.invisibleButton((String)stringArray[2], (float)53.0f, (float)54.0f);
        Ui.icon(stringArray[2], f + f3 - 36.0f, f2 + 20.0f, 16.0f, Ui.mix(Im.SUB, Im.INK, Ui.hot()));
        if (bl) {
            this.method_25419();
        }
    }

    
    private static boolean chip(String var0, float var1_1, float var2_2, float var3_3, float var4_4, ImFont var5_5, boolean var6_6) {
        block18: {
            block16: {
                block17: {
                    block15: {
                        block14: {
                            ImGui.setCursorScreenPos((float)var1_1, (float)var2_2);
                            var8_7 = ImGui.invisibleButton((String)var0, (float)var3_3, (float)var4_4);
                            var9_8 = Ui.hot();
                            var7_9 = ImScreen.a;
                            var10_10 = ImGui.getWindowDrawList();
                            if (var7_9) break block14;
                            if (var6_6) {
                            }
                            Ui.fill(var10_10, var1_1, var2_2, var1_1 + var3_3, var2_2 + var4_4, Ui.col(Menu.ACCENT, 0.14f), var4_4 / 2.0f);
                        }
                        if (!var7_9) break block15;
lbl21:

                        Ui.fill(var10_10, var1_1, var2_2, var1_1 + var3_3, var2_2 + var4_4, Ui.col(Im.QUIET, var9_8), var4_4 / 2.0f);
                    }
                    v3 = var10_10;
                    v4 = var1_1;
                    v5 = var2_2;
                    v6 = var1_1 + var3_3;
                    v7 = var2_2;
                    v8 = var4_4;
                    if (var7_9) break block16;
                    v7 = v7 + v8;
                    if (!var6_6) break block17;
                    v10 = Ui.col(Menu.ACCENT);
                    break block18;
                }
                v8 = 0.18f;
            }
            v10 = Ui.wash(v8);
        }
        Ui.rect(v3, v4, v5, v6, v7, v10, var4_4 / 2.0f);
        v12 = var5_5;
        v13 = var1_1 + (float)Math.floor((var3_3 - Ui.width(var5_5, var0)) / 2.0f);
        v14 = Ui.mid(var5_5, var2_2, var4_4);
        v15 = var6_6 != false ? Im.INK : Ui.mix(Im.SUB, Im.INK, var9_8);
        Ui.text(v12, v13, v14, v15, var0);
        return var8_7;
    }

    
    private void grid(float f, float f2, float f3, float f4) {
        float f5;
        float f6;
        PackMix$Version packMix$Version;
        String string;
        List<PackMix$Version> list;
        Object object;
        boolean bl;
        block55: {
            float f7;
            String[] stringArray;
            float f8;
            block62: {
                block61: {
                    boolean bl2;
                    block60: {
                        Object object2;
                        float f9;
                        block53: {
                            f9 = f;
                            f8 = f2;
                            bl = ImScreen.a;
                            object2 = PackMix.CATEGORIES.keySet().iterator();
                            while (object2.hasNext()) {
                                float f10;
                                block58: {
                                    Object object3;
                                    block59: {
                                        block57: {
                                            float f11;
                                            block56: {
                                                object = object2.next();
                                                f10 = Ui.width(Im.sora12, (String)object) + 22.0f;
                                                if (bl) break block53;
                                                float f11 = f9 + f10 - (f + f3);
                                                f11 = f11 == 0.0f ? 0 : (f11 > 0.0f ? 1 : -1);
                                                if (bl) break block56;
                                                if (f11 > 0) {
                                                    f9 = f;
                                                    f8 += 32.0f;
                                                }
                                                object3 = object;
                                                if (bl) break block57;
                                                f11 = (float)PackMixer.chip((String)object3, f9, f8, f10, 26.0f, Im.sora12, ((String)object).equals(category));
                                            }
                                            if (f11 == false) break block58;
                                            object3 = object;
                                        }
                                        if (bl) break block59;
                                        if (((String)object3).equals(category)) break block58;
                                        category = object;
                                        entry = 0;
                                        object3 = object;
                                    }
                                    PackMix.load((String)object3);
                                }
                                f9 += f10 + 6.0f;
                                if (!bl) continue;
                            }
                            f8 += 38.0f;
                        }
                        Object object4 = object2 = PackMix.CATEGORIES.get(category);
                        if (!bl) {
                            if (object4.size() > 1) {
                                float f13;
                                block54: {
                                    f9 = f;
                                    stringArray = b;
                                    ImGui.pushID((String)stringArray[15]);
                                    for (int i = 0; i < object2.size(); f9 += f7 + 6.0f, ++i) {
                                        String string2 = ((PackMix$Entry)object2.get(i)).label();
                                        f13 = Ui.width(Im.label, string2);
                                        if (!bl) {
                                            f7 = f13 + 18.0f;
                                            int n = i;
                                            if (!bl) {
                                                n = n == entry ? 1 : 0;
                                            }
                                            int n2 = PackMixer.chip(string2, f9, f8, f7, 22.0f, Im.label, n != 0);
                                            if (!bl) {
                                                if (n2 == 0) continue;
                                                n2 = i;
                                            }
                                            entry = n2;
                                            if (!bl) continue;
                                        }
                                        break block54;
                                    }
                                    ImGui.popID();
                                    f13 = f8 + 34.0f;
                                }
                                f8 = f13;
                            }
                            object4 = object2.get(entry);
                        }
                        object = (PackMix$Entry)object4;
                        list = PackMix.versions((PackMix$Entry)object);
                        bl2 = PackMix.ready(category);
                        if (bl) break block60;
                        if (!bl2) break block61;
                        bl2 = list.isEmpty();
                    }
                    if (!bl2) break block62;
                }
                String string3 = category;
                if (!bl) {
                    if (PackMix.ready(string3)) {
                        string3 = b[25];
                    } else {
                        stringArray = b;
                        string3 = stringArray[4];
                    }
                }
                Ui.text(Im.sub, f + 2.0f, f8 + 6.0f, Im.MUTE, string3);
                return;
            }
            f7 = (float)Math.floor((f3 - 36.0f) / 4.0f);
            string = PackMix.picks.get(((PackMix$Entry)object).label());
            packMix$Version = null;
            stringArray = b;
            Edit.list(stringArray[22], f - 1.0f, f8, f3 + 2.0f, f4 - f8);
            f6 = ImGui.getCursorScreenPosY();
            ImDrawList imDrawList = ImGui.getWindowDrawList();
            int n = 0;
            while (n < list.size()) {
                PackMix$Version packMix$Version2 = list.get(n);
                float f14 = f + (float)(n % 4) * (f7 + 12.0f);
                f5 = f6 + (float)(n / 4) * 162.0f;
                if (!bl) {
                    float f15 = f5;
                    boolean bl3 = packMix$Version2.packId().equals(string);
                    String string4 = packMix$Version2.packId();
                    if (!bl) {
                        ImGui.pushID((String)string4);
                        if (!bl3) {
                            Ui.fill(imDrawList, f14, f15, f14 + f7, f15 + 150.0f, Ui.wash(0.03f), 10.0f);
                            Ui.rect(imDrawList, f14, f15, f14 + f7, f15 + 150.0f, Ui.wash(0.08f), 10.0f);
                        }
                        string4 = b[1];
                    }
                    if (Edit.row(string4, f14, f15, f7, 150.0f, bl3)) {
                        packMix$Version = packMix$Version2;
                    }
                    PackMixer.icon((PackMix$Entry)object, packMix$Version2, f14 + (float)Math.floor((f7 - 64.0f) / 2.0f), f15 + 28.0f, 64.0f);
                    PackMixer.fit(Im.sora12, f14 + 10.0f, f15 + 106.0f, f7 - 20.0f, true, bl3 ? Im.INK : Im.SUB, PackMixer.clean(packMix$Version2.packName()));
                    if (!bl) {
                        if (bl3) {
                            imDrawList.addCircleFilled(f14 + 15.0f, f15 + 15.0f, 7.0f, Ui.col(Menu.FILL), 24);
                            Menu.tick(f14 + 15.0f, f15 + 15.0f, 3.5f, Ui.col(Im.ON_FILL));
                        }
                        ImGui.popID();
                        ++n;
                    }
                    if (!bl) continue;
                }
                break block55;
            }
            f5 = f;
        }
        Edit.endList(f5, f6 + (float)((list.size() + 3) / 4) * 162.0f + 20.0f - 12.0f);
        if (packMix$Version == null) {
            return;
        }
        String string5 = ((PackMix$Entry)object).label();
        this.status = "";
        if (!bl) {
            if (packMix$Version.packId().equals(string)) {
                PackMixer.unpick(string5);
                return;
            }
            PackMixer.copy(string5, packMix$Version);
            PackMix.picks.put(string5, packMix$Version.packId());
            PackMix.pickNames.put(string5, PackMixer.clean(packMix$Version.packName()));
            PackMix.retint(string5);
            PackMixer.tintCopy(string5);
        }
    }

    private void picks(float f, float f2, float f3, float f4) {
        block91: {
            block90: {
                int n;
                float f5;
                boolean bl;
                block88: {
                    block89: {
                        int n2;
                        block87: {
                            block85: {
                                block86: {
                                    block84: {
                                        PackMixer packMixer;
                                        int n3;
                                        block80: {
                                            block81: {
                                                float[] fArray;
                                                float f6;
                                                float f7;
                                                ImFont imFont;
                                                block83: {
                                                    block82: {
                                                        String string;
                                                        block79: {
                                                            block66: {
                                                                float f8;
                                                                block67: {
                                                                    float f9;
                                                                    float f10;
                                                                    block78: {
                                                                        PackMix$Version[] packMix$VersionArray;
                                                                        float f11;
                                                                        block69: {
                                                                            String[] stringArray;
                                                                            Object object;
                                                                            float[] fArray2;
                                                                            float f12;
                                                                            float f13;
                                                                            ImFont imFont2;
                                                                            block63: {
                                                                                String string2;
                                                                                int n4;
                                                                                block65: {
                                                                                    block64: {
                                                                                        block61: {
                                                                                            block62: {
                                                                                                n3 = PackMix.picks.size();
                                                                                                bl = ImScreen.a;
                                                                                                Ui.text(Im.sora13, f, f2, Im.INK, b[26]);
                                                                                                imFont2 = Im.label;
                                                                                                float f12 = f;
                                                                                                f12 = f2 + 20.0f;
                                                                                                fArray2 = Im.MUTE;
                                                                                                n4 = n3;
                                                                                                if (bl) break block61;
                                                                                                if (n4 != 0) break block62;
                                                                                                object = b[34];
                                                                                                break block63;
                                                                                            }
                                                                                            n4 = n3;
                                                                                        }
                                                                                        if (n3 != 1) break block64;
                                                                                        string2 = b[31];
                                                                                        break block65;
                                                                                    }
                                                                                    stringArray = b;
                                                                                    string2 = stringArray[12];
                                                                                }
                                                                                object = n4 + string2;
                                                                            }
                                                                            Ui.text(imFont2, f13, f12, fArray2, (String)object);
                                                                            f5 = f4 - 36.0f;
                                                                            f8 = f2 + 46.0f;
                                                                            f11 = Math.min((float)(n3 * 44), f5 - 26.0f - 66.0f - f8);
                                                                            if (bl) break block66;
                                                                            if (n3 <= 0) break block67;
                                                                            ImDrawList imDrawList = ImGui.getWindowDrawList();
                                                                            Ui.fill(imDrawList, f, f8, f + f3, f8 + f11, Ui.wash(0.03f), 8.0f);
                                                                            Ui.rect(imDrawList, f, f8, f + f3, f8 + f11, Ui.wash(0.08f), 8.0f);
                                                                            stringArray = b;
                                                                            Edit.list(stringArray[23], f, f8, f3, f11);
                                                                            imDrawList = ImGui.getWindowDrawList();
                                                                            float f14 = ImGui.getCursorScreenPosY();
                                                                            packMix$VersionArray = null;
                                                                            for (PackMix$Version[] packMix$VersionArray2 : PackMix.picks.keySet()) {
                                                                                block77: {
                                                                                    Object object2;
                                                                                    block76: {
                                                                                        float f15;
                                                                                        float f16;
                                                                                        block74: {
                                                                                            block75: {
                                                                                                block71: {
                                                                                                    block72: {
                                                                                                        PackMix$Entry packMix$Entry;
                                                                                                        PackMix$Version[] packMix$VersionArray3;
                                                                                                        block73: {
                                                                                                            PackMix$Version[] packMix$VersionArray4;
                                                                                                            block68: {
                                                                                                                block70: {
                                                                                                                    packMix$VersionArray4 = packMix$VersionArray2;
                                                                                                                    if (bl) break block68;
                                                                                                                    ImGui.pushID((String)packMix$VersionArray4);
                                                                                                                    if (bl) break block69;
                                                                                                                    if (!(f14 > f8)) break block70;
                                                                                                                    imDrawList.addLine(f, f14, f + f3, f14, Ui.wash(0.06f));
                                                                                                                }
                                                                                                                packMix$VersionArray4 = copies.get(packMix$VersionArray2);
                                                                                                            }
                                                                                                            packMix$VersionArray3 = packMix$VersionArray4;
                                                                                                            PackMix$Entry packMix$Entry2 = PackMixer.entryOf((String)packMix$VersionArray2);
                                                                                                            if (bl) break block71;
                                                                                                            if (packMix$VersionArray3 == null) break block72;
                                                                                                            packMix$Entry = packMix$Entry2;
                                                                                                            if (bl) break block73;
                                                                                                            if (packMix$Entry == null) break block72;
                                                                                                            packMix$Entry = packMix$Entry2;
                                                                                                        }
                                                                                                        PackMixer.icon(packMix$Entry, packMix$VersionArray3[1], f + 12.0f, f14 + 8.0f, 28.0f);
                                                                                                    }
                                                                                                    PackMixer.fit(Im.sora12, f + 50.0f, f14 + 7.0f, f3 - 118.0f, false, Im.INK, (String)packMix$VersionArray2);
                                                                                                    PackMixer.fit(Im.label, f + 50.0f, f14 + 24.0f, f3 - 118.0f, false, Im.MUTE, PackMix.pickNames.getOrDefault(packMix$VersionArray2, "?"));
                                                                                                }
                                                                                                object2 = b[32];
                                                                                                float f15 = f;
                                                                                                f15 = f3;
                                                                                                if (bl) break block74;
                                                                                                if (!PackMixer.small((String)object2, f16 + f15 - 64.0f, f14 + 10.0f)) break block75;
                                                                                                editing = packMix$VersionArray2;
                                                                                            }
                                                                                            object2 = b[3];
                                                                                            if (bl) break block76;
                                                                                            float f15 = f + f3 - 36.0f;
                                                                                            f15 = f14 + 10.0f;
                                                                                        }
                                                                                        if (!PackMixer.small((String)object2, f16, f15)) break block77;
                                                                                        object2 = packMix$VersionArray2;
                                                                                    }
                                                                                    packMix$VersionArray = object2;
                                                                                }
                                                                                ImGui.popID();
                                                                                f14 += 44.0f;
                                                                                if (!bl) continue;
                                                                            }
                                                                            float f9 = f;
                                                                            f9 = f14;
                                                                            if (bl) break block78;
                                                                            Edit.endList(f10, f9 - 1.0f);
                                                                        }
                                                                        if (packMix$VersionArray != null) {
                                                                            PackMixer.unpick(packMix$VersionArray);
                                                                            this.status = "";
                                                                        }
                                                                        float f9 = f8;
                                                                        f9 = f11 + 14.0f;
                                                                    }
                                                                    f8 = f10 + f9;
                                                                }
                                                                Ui.label(f, f8, b[29]);
                                                                string = b[27];
                                                                if (bl) break block79;
                                                                Edit.field(string, b[16], name, f, f8 + 18.0f, f3, false);
                                                            }
                                                            if (ImGui.isItemEdited()) {
                                                                this.status = "";
                                                            }
                                                            packMixer = this;
                                                            if (bl) break block80;
                                                            string = packMixer.status;
                                                        }
                                                        if (string.isEmpty()) break block81;
                                                        imFont = Im.label;
                                                        float f6 = f + (float)Math.floor((f3 - Ui.width(Im.label, this.status)) / 2.0f);
                                                        f6 = f5 - 20.0f;
                                                        if (!this.statusOk) break block82;
                                                        fArray = Im.SUB;
                                                        break block83;
                                                    }
                                                    fArray = Im.DANGER;
                                                }
                                                Ui.text(imFont, f7, f6, fArray, this.status);
                                            }
                                            packMixer = this;
                                        }
                                        n2 = packMixer.exporting;
                                        if (bl) break block84;
                                        if (n2 != 0) break block85;
                                        n2 = n3;
                                    }
                                    if (bl) break block86;
                                    if (n2 <= 0) break block85;
                                    n2 = PackMix.fileName(name.get()).isEmpty();
                                }
                                if (bl) break block87;
                                if (n2 != 0) break block85;
                                n2 = 1;
                                break block87;
                            }
                            n2 = 0;
                        }
                        int n5 = n2;
                        n = n5;
                        if (bl) break block88;
                        if (n != 0) break block89;
                        n = 1;
                        break block88;
                    }
                    n = 0;
                }
                ImGui.beginDisabled(n != 0);
                String string = this.exporting ? b[21] : b[28];
                boolean bl2 = Menu.button(string, f, f5, f3, 36.0f, true);
                if (bl) break block90;
                ImGui.endDisabled();
                if (!bl2) break block91;
                this.exporting = true;
                this.status = "";
            }
            PackMix.export(name.get(), Formatting.field_1068, this::lambda$picks$0);
        }
    }

    private static boolean small(String string, float f, float f2) {
        ImGui.setCursorScreenPos((float)f, (float)f2);
        boolean bl = ImGui.invisibleButton((String)string, (float)24.0f, (float)24.0f);
        boolean bl2 = ImGui.isItemHovered();
        if (bl2) {
            Ui.fill(ImGui.getWindowDrawList(), f, f2, f + 24.0f, f2 + 24.0f, Ui.col(Im.QUIET_HI), 6.0f);
        }
        String string2 = string;
        float f3 = f + 6.0f;
        float f4 = f2 + 6.0f;
        float f5 = 12.0f;
        float[] fArray = bl2 ? Im.INK : Im.MUTE;
        Ui.icon(string2, f3, f4, f5, fArray);
        return bl;
    }

    
    private void editor(float f, float f2, float f3, float f4) {
        float f5;
        boolean bl;
        String string;
        block42: {
            boolean bl2;
            block43: {
                block46: {
                    float f6;
                    float f7;
                    block45: {
                        float f8;
                        float f9;
                        block44: {
                            block40: {
                                block41: {
                                    float[] fArray;
                                    block38: {
                                        block39: {
                                            float f10;
                                            block36: {
                                                block37: {
                                                    float f11;
                                                    float f12;
                                                    String[] stringArray;
                                                    block34: {
                                                        PackMix$Entry packMix$Entry;
                                                        PackMix$Version[] packMix$VersionArray;
                                                        block35: {
                                                            string = editing;
                                                            Ui.text(Im.sora13, f, f2, Im.INK, string);
                                                            bl = ImScreen.a;
                                                            stringArray = b;
                                                            PackMixer.fit(Im.label, f, f2 + 20.0f, f3, false, Im.MUTE, stringArray[8] + PackMix.pickNames.getOrDefault(string, "?"));
                                                            f12 = f + (f3 - 190.0f) / 2.0f;
                                                            f11 = f2 + 52.0f;
                                                            packMix$VersionArray = copies.get(string);
                                                            PackMix$Entry packMix$Entry2 = PackMixer.entryOf(string);
                                                            if (packMix$VersionArray == null) break block34;
                                                            packMix$Entry = packMix$Entry2;
                                                            if (bl) break block35;
                                                            if (packMix$Entry == null) break block34;
                                                            PackMixer.icon(packMix$Entry2, packMix$VersionArray[0], f12, f11, 72.0f);
                                                            packMix$Entry = packMix$Entry2;
                                                        }
                                                        PackMixer.icon(packMix$Entry, packMix$VersionArray[1], f12 + 118.0f, f11, 72.0f);
                                                    }
                                                    ImDrawList imDrawList = ImGui.getWindowDrawList();
                                                    imDrawList.addLine(f12 + 92.0f, f11 + 31.0f, f12 + 97.0f, f11 + 36.0f, Ui.col(Im.MUTE), 1.4f);
                                                    imDrawList.addLine(f12 + 97.0f, f11 + 36.0f, f12 + 92.0f, f11 + 41.0f, Ui.col(Im.MUTE), 1.4f);
                                                    stringArray = b;
                                                    Ui.text(Im.label, f12 + (float)Math.floor((72.0f - Ui.width(Im.label, stringArray[33])) / 2.0f), f11 + 80.0f, Im.MUTE, stringArray[37]);
                                                    Ui.text(Im.label, f12 + 118.0f + (float)Math.floor((72.0f - Ui.width(Im.label, stringArray[9])) / 2.0f), f11 + 80.0f, Im.MUTE, stringArray[20]);
                                                    fArray = PackMix.tints.getOrDefault(string, NO_TINT);
                                                    float f13 = f11 + 113.0f;
                                                    f7 = PackMixer.slider(stringArray[35], Math.round(fArray[0] * 360.0f) + "\u00b0", f, f13, f3, fArray[0], true);
                                                    bl2 = ImGui.isItemDeactivated();
                                                    f6 = PackMixer.slider(stringArray[5], Math.round(fArray[1] * 100.0f) + "%", f, f13 + 38.0f, f3, fArray[1] / 2.0f, false) * 2.0f;
                                                    bl2 |= ImGui.isItemDeactivated();
                                                    f10 = f7;
                                                    if (bl) break block36;
                                                    if (f10 < 0.005f) break block37;
                                                    f9 = f7;
                                                    if (bl) break block38;
                                                    if (!(f9 > 0.995f)) break block39;
                                                }
                                                f10 = 0.0f;
                                            }
                                            f7 = f10;
                                        }
                                        f9 = Math.abs(f6 - 1.0f);
                                    }
                                    f8 = 0.03f;
                                    if (!bl) {
                                        if (f9 < f8) {
                                            f6 = 1.0f;
                                        }
                                        f9 = f7;
                                        f8 = fArray[0];
                                    }
                                    if (bl) break block40;
                                    if (f9 != f8) break block41;
                                    float f14 = f6 - fArray[1];
                                    f5 = f14 == 0.0f ? 0 : (f14 > 0.0f ? 1 : -1);
                                    if (bl) break block42;
                                    if (f5 == false) break block43;
                                }
                                f9 = f7;
                                f8 = 0.0f;
                            }
                            if (bl) break block44;
                            if (f9 != f8) break block45;
                            f9 = f6;
                            f8 = 1.0f;
                        }
                        if (f9 != f8) break block45;
                        PackMix.tints.remove(string);
                        if (!bl) break block46;
                    }
                    PackMix.tints.put(string, new float[]{f7, f6});
                }
                PackMixer.tintCopy(string);
                this.status = "";
            }
            f5 = (float)bl2;
        }
        if (f5 != false) {
            PackMix.retint(string);
        }
        float f15 = (f3 - 8.0f) / 2.0f;
        String string2 = b[19];
        if (!bl) {
            if (Menu.button(string2, f, f4 - 36.0f, f15, 36.0f, false)) {
                PackMix.tints.remove(string);
                PackMix.retint(string);
                PackMixer.tintCopy(string);
                this.status = "";
            }
            string2 = b[0];
        }
        if (!Menu.button(string2, f + f15 + 8.0f, f4 - 36.0f, f15, 36.0f, true)) return;
        editing = null;
    }

    private static float slider(String string, String string2, float f, float f2, float f3, float f4, boolean n) {
        int n2;
        ImDrawList imDrawList;
        float f5;
        boolean bl;
        float f6;
        float f7;
        block24: {
            block25: {
                block23: {
                    float f8;
                    block22: {
                        Ui.text(Im.sora12r, f, Ui.mid(Im.sora12r, f2, 38.0f), Im.SUB, string);
                        f7 = f + 84.0f;
                        f6 = f3 - 84.0f - 50.0f;
                        bl = ImScreen.a;
                        f5 = f2 + 19.0f;
                        f8 = f7 - 7.0f;
                        if (bl) break block22;
                        ImGui.setCursorScreenPos((float)f8, (float)(f2 + 10.0f));
                        ImGui.invisibleButton((String)string, (float)(f6 + 14.0f), (float)18.0f);
                        if (!ImGui.isItemActive()) break block23;
                        f8 = Math.max(0.0f, Math.min(1.0f, (ImGui.getMousePosX() - f7) / f6));
                    }
                    f4 = f8;
                }
                imDrawList = ImGui.getWindowDrawList();
                n2 = n;
                if (bl) break block24;
                if (n2 == 0) break block25;
                n2 = 6;
                break block24;
            }
            n2 = 1;
        }
        int n3 = n2;
        for (int i = 0; i < n3; ++i) {
            int n4;
            float f9;
            float f10;
            int n5;
            int n6;
            block30: {
                block31: {
                    int n7;
                    block28: {
                        block29: {
                            int n8;
                            block26: {
                                block27: {
                                    n8 = n;
                                    if (bl) break block26;
                                    if (n8 == 0) break block27;
                                    n8 = Edit.u32(Color.HSBtoRGB((float)i / 6.0f, 1.0f, 1.0f));
                                    break block26;
                                }
                                n8 = ImGui.getColorU32((float)0.54f, (float)0.54f, (float)0.54f, (float)1.0f);
                            }
                            n6 = n8;
                            n7 = n;
                            if (bl) break block28;
                            if (n7 == 0) break block29;
                            n7 = Edit.u32(Color.HSBtoRGB((float)(i + 1) / 6.0f, 1.0f, 1.0f));
                            break block28;
                        }
                        n7 = Ui.col(Menu.ACCENT);
                    }
                    n5 = n7;
                    f10 = f7 + 4.0f + (f6 - 8.0f) * (float)i / (float)n3;
                    f9 = f7 + 4.0f + (f6 - 8.0f) * (float)(i + 1) / (float)n3;
                    n4 = i;
                    if (bl) break block30;
                    if (n4 != 0) break block31;
                    imDrawList.addCircleFilled(f7 + 4.0f, f5, 4.0f, n6, 16);
                }
                n4 = i;
            }
            if (n4 == n3 - 1) {
                imDrawList.addCircleFilled(f7 + f6 - 4.0f, f5, 4.0f, n5, 16);
            }
            imDrawList.addRectFilledMultiColor(f10, f5 - 4.0f, f9, f5 + 4.0f, n6, n5, n5, n6);
            if (!bl) continue;
        }
        float f11 = f7 + f4 * f6;
        imDrawList.addCircleFilled(f11, f5, 7.0f, ImGui.getColorU32((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f), 24);
        imDrawList.addCircle(f11, f5, 7.5f, ImGui.getColorU32((float)0.0f, (float)0.0f, (float)0.0f, (float)0.4f), 24);
        Edit.value(string2, f + f3, f2 + 9.0f);
        return f4;
    }

    private static void fit(ImFont imFont, float f, float f2, float f3, boolean n, float[] fArray, String string) {
        float f4;
        ImFont imFont2;
        ImDrawList imDrawList;
        block10: {
            block9: {
                int n2;
                float f5;
                block8: {
                    f5 = Ui.width(imFont, string);
                    imDrawList = ImGui.getWindowDrawList();
                    imDrawList.pushClipRect(f, f2 - 2.0f, f + f3, f2 + 20.0f, true);
                    boolean bl = ImScreen.a;
                    imFont2 = imFont;
                    n2 = n;
                    if (bl) break block8;
                    if (n2 == 0) break block9;
                    f4 = f5;
                    if (bl) break block10;
                    float f6 = f4 - f3;
                    n2 = f6 == 0.0f ? 0 : (f6 < 0.0f ? -1 : 1);
                }
                if (n2 >= 0) break block9;
                f4 = f + (float)Math.floor((f3 - f5) / 2.0f);
                break block10;
            }
            f4 = f;
        }
        Ui.text(imFont2, f4, f2, fArray, string);
        imDrawList.popClipRect();
    }

    private static String clean(String string) {
        String string2;
        block4: {
            String string3;
            block5: {
                string3 = Formatting.method_539((String)string);
                boolean bl = ImScreen.a;
                string2 = string3;
                if (bl) break block4;
                if (!string2.endsWith(b[24])) break block5;
                string2 = string3.substring(0, string3.length() - 4);
                break block4;
            }
            string2 = string3;
        }
        return string2;
    }

    private static PackMix$Entry entryOf(String string) {
        Iterator<List<PackMix$Entry>> iterator = PackMix.CATEGORIES.values().iterator();
        boolean bl = ImScreen.a;
        while (iterator.hasNext()) {
            Object object = iterator.next();
            block5: while (true) {
                List<PackMix$Entry> list = object;
                for (PackMix$Entry packMix$Entry : list) {
                    block8: {
                        PackMix$Entry packMix$Entry2;
                        block7: {
                            packMix$Entry2 = packMix$Entry;
                            if (bl) break block7;
                            object = packMix$Entry2.label();
                            if (bl) continue block5;
                            if (!((String)object).equals(string)) break block8;
                            packMix$Entry2 = packMix$Entry;
                        }
                        return packMix$Entry2;
                    }
                    if (!bl) continue;
                }
                break;
            }
            if (!bl) continue;
        }
        return null;
    }

    private static void unpick(String string) {
        block4: {
            block5: {
                PackMix.picks.remove(string);
                boolean bl = ImScreen.a;
                PackMix.tints.remove(string);
                if (bl) break block4;
                PackMix.hands.remove(string);
                if (!string.equals(editing)) break block5;
                editing = null;
            }
            PackMix.retint(string);
            PackMixer.drop(string);
        }
    }

    private static NativeImage image(Identifier identifier) {
        NativeImage nativeImage;
        block6: {
            block5: {
                AbstractTexture abstractTexture;
                block4: {
                    AbstractTexture abstractTexture2 = MinecraftClient.method_1551().method_1531().method_4619(identifier);
                    boolean bl = ImScreen.a;
                    abstractTexture = abstractTexture2;
                    if (bl) break block4;
                    if (!(abstractTexture instanceof NativeImageBackedTexture)) break block5;
                    abstractTexture = abstractTexture2;
                }
                NativeImageBackedTexture nativeImageBackedTexture = (NativeImageBackedTexture)abstractTexture;
                nativeImage = nativeImageBackedTexture.method_4525();
                break block6;
            }
            nativeImage = null;
        }
        return nativeImage;
    }

    private static void copy(String string, PackMix$Version packMix$Version) {
        block10: {
            PackMix$Version[] packMix$VersionArray;
            HashMap<String, PackTextures$Sprite> hashMap;
            PackMix$Version[] packMix$VersionArray2;
            block13: {
                PackMixer.drop(string);
                packMix$VersionArray2 = new HashMap();
                hashMap = new HashMap<String, PackTextures$Sprite>();
                Iterator<Map.Entry<String, PackTextures$Sprite>> iterator = packMix$Version.sprites().entrySet().iterator();
                boolean bl = ImScreen.a;
                while (iterator.hasNext()) {
                    block11: {
                        Map.Entry<String, PackTextures$Sprite> entry = iterator.next();
                        packMix$VersionArray = PackMixer.image(entry.getValue().id());
                        if (bl) break block10;
                        PackMix$Version[] packMix$VersionArray3 = packMix$VersionArray;
                        if (bl) break block11;
                        if (packMix$VersionArray3 == null) {
                            continue;
                        }
                        packMix$VersionArray2.put(entry.getKey(), PackMixer.copy(entry.getValue(), (NativeImage)packMix$VersionArray3));
                        hashMap.put(entry.getKey(), PackMixer.copy(entry.getValue(), (NativeImage)packMix$VersionArray3));
                    }
                    if (!bl) continue;
                }
                packMix$VersionArray = packMix$VersionArray2;
                if (bl) break block10;
                if (!packMix$VersionArray.isEmpty()) break block13;
                return;
            }
            packMix$VersionArray = copies.put(string, new PackMix$Version[]{new PackMix$Version(packMix$Version.packId(), packMix$Version.packName(), (Map<String, PackTextures$Sprite>)packMix$VersionArray2), new PackMix$Version(packMix$Version.packId(), packMix$Version.packName(), hashMap)});
        }
    }

    private static PackTextures$Sprite copy(PackTextures$Sprite packTextures$Sprite, NativeImage nativeImage) {
        NativeImage nativeImage2 = new NativeImage(nativeImage.method_4307(), nativeImage.method_4323(), false);
        nativeImage2.method_4317(nativeImage);
        String[] stringArray = b;
        Identifier identifier = Identifier.method_60655((String)stringArray[17], (String)(stringArray[14] + copied++));
        MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)new NativeImageBackedTexture(() -> ((Identifier)identifier).toString(), nativeImage2));
        return new PackTextures$Sprite(identifier, packTextures$Sprite.frameWidth(), packTextures$Sprite.frameHeight(), packTextures$Sprite.width(), packTextures$Sprite.height());
    }

    private static void drop(String string) {
        PackMix$Version[] packMix$VersionArray = copies.remove(string);
        boolean bl = ImScreen.a;
        if (packMix$VersionArray == null) {
            return;
        }
        TextureManager textureManager = MinecraftClient.method_1551().method_1531();
        PackMix$Version[] packMix$VersionArray2 = packMix$VersionArray;
        int n = packMix$VersionArray2.length;
        int n2 = 0;
        while (n2 < n) {
            block8: {
                PackMix$Version packMix$Version = packMix$VersionArray2[n2];
                for (PackTextures$Sprite packTextures$Sprite : packMix$Version.sprites().values()) {
                    if (!bl) {
                        textureManager.method_4615(packTextures$Sprite.id());
                        if (!bl) continue;
                        break;
                    }
                    break block8;
                }
                ++n2;
            }
            if (!bl) continue;
        }
    }

    private static void tintCopy(String string) {
        Object[] objectArray;
        boolean bl;
        PackMix$Version[] packMix$VersionArray;
        block11: {
            block12: {
                packMix$VersionArray = copies.get(string);
                bl = ImScreen.a;
                objectArray = packMix$VersionArray;
                if (bl) break block11;
                if (objectArray != null) break block12;
                return;
            }
            objectArray = PackMix.tints.get(string);
        }
        float[] fArray = (float[])objectArray;
        TextureManager textureManager = MinecraftClient.method_1551().method_1531();
        for (Map.Entry<String, PackTextures$Sprite> entry : packMix$VersionArray[0].sprites().entrySet()) {
            block14: {
                NativeImage nativeImage;
                NativeImage nativeImage2;
                NativeImage nativeImage3;
                Identifier identifier;
                block13: {
                    identifier = packMix$VersionArray[1].sprite(entry.getKey()).id();
                    nativeImage3 = PackMixer.image(entry.getValue().id());
                    nativeImage2 = PackMixer.image(identifier);
                    nativeImage = nativeImage3;
                    if (bl) break block13;
                    if (nativeImage == null) continue;
                    nativeImage = nativeImage2;
                }
                if (nativeImage == null) continue;
                int n = 0;
                while (n < nativeImage3.method_4323()) {
                    block15: {
                        if (bl) break block14;
                        for (int i = 0; i < nativeImage3.method_4307(); ++i) {
                            int n2;
                            int n3;
                            int n4;
                            NativeImage nativeImage4;
                            block17: {
                                int n5;
                                block16: {
                                    n5 = nativeImage3.method_61940(i, n);
                                    if (bl) break block15;
                                    nativeImage4 = nativeImage2;
                                    n4 = i;
                                    n3 = n;
                                    if (fArray != null) break block16;
                                    n2 = n5;
                                    break block17;
                                }
                                n2 = PackMix.tint(n5, fArray);
                            }
                            nativeImage4.method_61941(n4, n3, n2);
                            if (!bl) continue;
                        }
                        ++n;
                    }
                    if (!bl) continue;
                }
                ((NativeImageBackedTexture)textureManager.method_4619(identifier)).method_4524();
            }
            if (!bl) continue;
        }
    }

    
    private static void icon(PackMix$Entry var0, PackMix$Version var1_1, float var2_2, float var3_3, float var4_4) {
        var6_5 = var0.files();
        var5_6 = ImScreen.a;
        var7_7 = var1_1.sprite(var6_5.get(0));
        v0 = PackMixer$1.$SwitchMap$dev$quelchi$aurora$gui$PackMix$Look[var0.look().ordinal()];
        if (var5_6) // goto
        switch (v0) {
            case 1: {
                v0 = var6_5.size();
lbl9:

                var8_8 = v0 > 1 ? var1_1.sprite(var6_5.get(1)) : null;
                v1 = var7_7;
                if (!var5_6) {
                    if (v1 == null) {
                        var7_7 = var8_8;
                    }
                    v1 = var8_8;
                }
                if (!var5_6) {
                    if (v1 == null) {
                        var8_8 = var7_7;
                    }
                    v1 = var7_7;
                }
                if (v1 == null) {
                    return;
                }
                var9_11 = var4_4 / 2.0f;
                var10_13 = var9_11 * 1.7320508f;
                var11_15 = var2_2 + var4_4 / 2.0f;
                PackMixer.quad(PackPreview$Face.whole(var7_7), var11_15 - var10_13 / 2.0f, var3_3 + var9_11 / 2.0f, var10_13 / 2.0f, -var9_11 / 2.0f, var10_13 / 2.0f, var9_11 / 2.0f, 1.0f);
                PackMixer.quad(PackPreview$Face.whole(var8_8), var11_15 - var10_13 / 2.0f, var3_3 + var9_11 / 2.0f, var10_13 / 2.0f, var9_11 / 2.0f, 0.0f, var9_11, 0.8f);
                PackMixer.quad(PackPreview$Face.whole(var8_8), var11_15, var3_3 + var9_11, var10_13 / 2.0f, -var9_11 / 2.0f, 0.0f, var9_11, 0.6f);
                if (!var5_6) break;
            }
            case 2: {
                v2 = var7_7;
                if (!var5_6) {
                    if (v2 == null) break;
                    v2 = var7_7;
                }
                PackMixer.crystal(v2, var2_2 + var4_4 / 2.0f, var3_3 + var4_4 / 2.0f, var4_4);
                if (!var5_6) break;
            }
            case 3: {
                v3 = var7_7;
                if (!var5_6) {
                    if (v3 == null) {
                        var7_7 = var1_1.sprite(var6_5.get(1));
                    }
                    v3 = var7_7;
                }
                if (!var5_6) {
                    if (v3 == null) break;
                    v3 = var7_7;
                }
                PackMixer.flat(PackPreview$Face.sheet(v3, 1, 1, 12, 22), var2_2, var3_3, var4_4);
                if (!var5_6) break;
            }
            case 4: {
                var8_9 = false;
                for (var9_12 = 0; var9_12 < 4; ++var9_12) {
                    var10_14 = var1_1.sprite(var6_5.get(var9_12));
                    if (var5_6) // goto
                    if (var5_6) // goto
                    if (var10_14 == null) continue;
                    var8_9 = true;
lbl54:

                    PackMixer.flat(PackPreview$Face.whole(var10_14), var2_2 + (float)(var9_12 % 2) * var4_4 / 2.0f, var3_3 + (float)(var9_12 / 2) * var4_4 / 2.0f, var4_4 / 2.0f);
                    if (!var5_6) continue;
                }
                if (!var8_9) {
                    PackMixer.flat(PackPreview$Face.whole(var1_1.sprites().values().iterator().next()), var2_2, var3_3, var4_4);
                }
lbl59:

                if (!var5_6) break;
            }
            case 5: {
                v4 = var7_7;
                if (!var5_6) {
                    if (v4 == null) {
                        return;
                    }
                    v4 = var7_7;
                }
                var8_10 = (float)v4.width() / 256.0f;
                v5 = var6_5.get(0).endsWith(PackMixer.b[18]);
                if (!var5_6) {
                    v5 = v5 != 0 ? 222 : 166;
                }
                var9_12 = v5;
                PackMixer.flat(new PackPreview$Face(var7_7, 0, 0, Math.round(176.0f * var8_10), Math.round((float)var9_12 * var8_10)), var2_2, var3_3, var4_4);
                if (!var5_6) break;
            }
            default: {
                v6 = var7_7;
                if (!var5_6) {
                    if (v6 == null) {
                        var7_7 = var1_1.sprites().values().iterator().next();
                    }
                    v6 = var7_7;
                }
                PackMixer.flat(PackPreview$Face.whole(v6), var2_2, var3_3, var4_4);
            }
        }
    }

    private static void flat(PackPreview$Face packPreview$Face, float f, float f2, float f3) {
        float f4 = f3 / (float)Math.max(packPreview$Face.w(), packPreview$Face.h());
        float f5 = Math.round((float)packPreview$Face.w() * f4);
        float f6 = Math.round((float)packPreview$Face.h() * f4);
        PackMixer.quad(packPreview$Face, f + (float)Math.floor((f3 - f5) / 2.0f), f2 + (float)Math.floor((f3 - f6) / 2.0f), f5, 0.0f, 0.0f, f6, 1.0f);
    }

    private static void quad(PackPreview$Face packPreview$Face, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        long l;
        long l2;
        block4: {
            block5: {
                l2 = Ui.tex(packPreview$Face.s().id());
                boolean bl = ImScreen.a;
                l = l2;
                if (bl) break block4;
                if (l != 0L) break block5;
                return;
            }
            l = l2;
        }
        Ui.sharp(l);
        float f8 = (float)packPreview$Face.u() / (float)packPreview$Face.s().width();
        float f9 = (float)packPreview$Face.v() / (float)packPreview$Face.s().height();
        float f10 = (float)(packPreview$Face.u() + packPreview$Face.w()) / (float)packPreview$Face.s().width();
        float f11 = (float)(packPreview$Face.v() + packPreview$Face.h()) / (float)packPreview$Face.s().height();
        ImGui.getWindowDrawList().addImageQuad(l2, f, f2, f + f3, f2 + f4, f + f3 + f5, f2 + f4 + f6, f + f5, f2 + f6, f8, f9, f10, f9, f10, f11, f8, f11, ImGui.getColorU32((float)f7, (float)f7, (float)f7, (float)1.0f));
    }

    
    private static void crystal(PackTextures$Sprite var0, float var1_1, float var2_2, float var3_3) {
        var5_4 = (float)Math.toRadians((double)Anim.now() / 1.0E9 * 60.0 % 360.0);
        var6_5 = (float)Math.toRadians(60.0);
        var4_6 = ImScreen.a;
        var7_7 = new Matrix3f[3];
        var7_7[0] = var8_8 = new Matrix3f().rotateX((float)Math.toRadians(20.0)).rotateY(var5_4).rotate(var6_5, PackMixer.SIN_45, 0.0f, PackMixer.SIN_45);
        for (var9_9 = 1; var9_9 < 3; ++var9_9) {
            var7_7[var9_9] = var8_8 = new Matrix3f((Matrix3fc)var8_8).rotate(var6_5, PackMixer.SIN_45, 0.0f, PackMixer.SIN_45).rotateY(var5_4);
            if (!var4_6) continue;
        }
        var9_10 = var3_3 / 2.0f / 1.7320508f;
        var10_11 = new float[]{1.0f, 0.875f, 0.766f};
        for (var11_12 = 0; var11_12 < 3; ++var11_12) {
            block14: {
                block15: {
                    v0 = var0;
                    v1 = var7_7;
                    if (!var4_6) {
                        v2 = v1[var11_12];
                        v3 = var1_1;
                        v4 = var2_2;
                        v5 = var9_10 * var10_11[var11_12];
                        v6 = var11_12;
                        if (var4_6) break block14;
                    }
                    if (v6 != 2) break block15;
                    v6 = 32;
                    break block14;
                }
                v6 = 0;
            }
            PackMixer.box(v0, v2, v3, v4, v5, v6, false);
            if (!var4_6) continue;
        }
        for (var11_12 = 2; var11_12 >= 0; --var11_12) {
            block16: {
                block17: {
                    v0 = var0;
                    v1 = var7_7;
lbl46:

                    v10 = v1[var11_12];
                    v11 = var1_1;
                    v12 = var2_2;
                    v13 = var9_10 * var10_11[var11_12];
                    v14 = var11_12;
                    if (var4_6) break block16;
                    if (v14 != 2) break block17;
                    v14 = 32;
                    break block16;
                }
                v14 = 0;
            }
            PackMixer.box(v0, v10, v11, v12, v13, v14, true);
            if (!var4_6) continue;
        }
    }

    private static void box(PackTextures$Sprite packTextures$Sprite, Matrix3f matrix3f, float f, float f2, float f3, int n, boolean bl) {
        Vector3f vector3f = new Vector3f();
        Vector3f vector3f2 = new Vector3f();
        Vector3f vector3f3 = new Vector3f();
        Vector3f vector3f4 = new Vector3f();
        boolean bl2 = ImScreen.a;
        for (float[] fArray : BOX) {
            float f4;
            block6: {
                block7: {
                    matrix3f.transform(vector3f4.set(fArray[9], fArray[10], fArray[11]));
                    float f5 = vector3f4.z - 0.0f;
                    f4 = f5 == 0.0f ? 0 : (f5 > 0.0f ? 1 : -1);
                    if (bl2) break block6;
                    if (f4 <= 0) break block7;
                    f4 = 1.0f;
                    break block6;
                }
                f4 = 0.0f;
            }
            if (f4 != bl) {
                continue;
            }
            matrix3f.transform(vector3f.set(fArray[0], fArray[1], fArray[2]));
            matrix3f.transform(vector3f2.set(fArray[3], fArray[4], fArray[5]));
            matrix3f.transform(vector3f3.set(fArray[6], fArray[7], fArray[8]));
            PackMixer.quad(PackPreview$Face.sheet(packTextures$Sprite, n + (int)fArray[12], (int)fArray[13], 8, 8), f + vector3f.x * f3, f2 - vector3f.y * f3, vector3f2.x * f3, -vector3f2.y * f3, vector3f3.x * f3, -vector3f3.y * f3, Math.max(0.45f, 0.75f + 0.25f * vector3f4.y));
            if (!bl2) continue;
        }
    }

    private void lambda$picks$0(String string) {
        String string2;
        block8: {
            block9: {
                boolean bl;
                boolean bl2;
                block6: {
                    block7: {
                        bl2 = ImScreen.a;
                        PackMixer packMixer = this;
                        bl = false;
                        if (bl2) break block6;
                        packMixer.exporting = bl;
                        packMixer = this;
                        if (string != null) break block7;
                        bl = true;
                        break block6;
                    }
                    bl = false;
                }
                packMixer.statusOk = bl;
                PackMixer packMixer = this;
                string2 = string;
                if (bl2) break block8;
                if (string2 != null) break block9;
                string2 = b[6];
                break block8;
            }
            string2 = string;
        }
        packMixer.status = string2;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[38];
                var3_1 = 0;
                var2_2 = "xyu[\u0004H\u007fw[\u0005_ztM\u000f\u0005_ztM\u000f\u0015nszZ\u0003P\u001b\u001cotK\u0018\u001e\f]upMD\u0010R\nowoK\u0018_\bUyu\u0016owm[\u000e\u001e\bS6i[\u0019Q\tNu~N\u000b]\u0017O\n}ciQ\u0018_\\q\u007fc\u0005zdtSJ\u0005}po[\u0018\nlwxUJs\u0015Dsi\u0005Q\u007fc[\u0018\u0010\u001cb~F\u001eK\u000eYe;N\u0003]\u0017Yr\u0005Q\u007fc[\u0018\u000bLwxU5S\u0015Dsi\u0011\u0007YxoL\u0003[\u000f\tlwxUJP\u001dQs\u0006]ciQ\u0018_\n[su[\u0018W\u001fc#/\u0005nsh[\u001e\u0005}po[\u0018\fynkQ\u0018J\u0015Rq5\u0010D\u0005H\u007fw[\u0019\u0005L\u007fxU\u0019\u0004\u0012lrN\u001eryu[JQ\u001a\u001cotK\u0018\u001e\f]upMJ]\u0014]x|[JJ\u0014Ue\teynLJN\u001d_}\u0006\u001f5u_\u0007[\u000bynkQ\u0018J\\LwxU\u0004rwv[\nlwxUJs\u0015Dsi\u000f\u001cb~F\u001eK\u000eY6kW\tU\u0019X\u0004YrrJ\u0006~s}Q\u0018[\u0019\u007fzr]\u0001\u001e\u001d\u001cb~F\u001eK\u000eY6oQJ_\u0018X6rJ\u0003tc~";
                var4_3 = "xyu[\u0004H\u007fw[\u0005_ztM\u000f\u0005_ztM\u000f\u0015nszZ\u0003P\u001b\u001cotK\u0018\u001e\f]upMD\u0010R\nowoK\u0018_\bUyu\u0016owm[\u000e\u001e\bS6i[\u0019Q\tNu~N\u000b]\u0017O\n}ciQ\u0018_\\q\u007fc\u0005zdtSJ\u0005}po[\u0018\nlwxUJs\u0015Dsi\u0005Q\u007fc[\u0018\u0010\u001cb~F\u001eK\u000eYe;N\u0003]\u0017Yr\u0005Q\u007fc[\u0018\u000bLwxU5S\u0015Dsi\u0011\u0007YxoL\u0003[\u000f\tlwxUJP\u001dQs\u0006]ciQ\u0018_\n[su[\u0018W\u001fc#/\u0005nsh[\u001e\u0005}po[\u0018\fynkQ\u0018J\u0015Rq5\u0010D\u0005H\u007fw[\u0019\u0005L\u007fxU\u0019\u0004\u0012lrN\u001eryu[JQ\u001a\u001cotK\u0018\u001e\f]upMJ]\u0014]x|[JJ\u0014Ue\teynLJN\u001d_}\u0006\u001f5u_\u0007[\u000bynkQ\u0018J\\LwxU\u0004rwv[\nlwxUJs\u0015Dsi\u000f\u001cb~F\u001eK\u000eY6kW\tU\u0019X\u0004YrrJ\u0006~s}Q\u0018[\u0019\u007fzr]\u0001\u001e\u001d\u001cb~F\u001eK\u000eY6oQJ_\u0018X6rJ\u0003tc~".length();
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
                    var2_2 = "^wxU\u0006~s}Q\u0018[";
                    var4_3 = "^wxU\u0006~s}Q\u0018[".length();
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
                        v10 = 60;
                        break;
                    }
                    case 1: {
                        v10 = 22;
                        break;
                    }
                    case 2: {
                        v10 = 27;
                        break;
                    }
                    case 3: {
                        v10 = 62;
                        break;
                    }
                    case 4: {
                        v10 = 106;
                        break;
                    }
                    case 5: {
                        v10 = 62;
                        break;
                    }
                    default: {
                        v10 = 124;
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
        PackMixer.b = var5;
        PackMixer.NO_TINT = new float[]{0.0f, 1.0f};
        PackMixer.category = PackMix.CATEGORIES.keySet().iterator().next();
        PackMixer.name = new ImString(PackMixer.b[7], 41);
        PackMixer.copies = new HashMap<String, PackMix$Version[]>();
        PackMixer.BOX = new float[][]{{-1.0f, 1.0f, -1.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 1.0f, 0.0f, 8.0f, 0.0f}, {-1.0f, -1.0f, 1.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, -1.0f, 0.0f, 16.0f, 0.0f}, {1.0f, 1.0f, -1.0f, -2.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, -1.0f, 8.0f, 8.0f}, {-1.0f, 1.0f, 1.0f, 2.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 1.0f, 24.0f, 8.0f}, {-1.0f, 1.0f, -1.0f, 0.0f, 0.0f, 2.0f, 0.0f, -2.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 8.0f}, {1.0f, 1.0f, 1.0f, 0.0f, 0.0f, -2.0f, 0.0f, -2.0f, 0.0f, 1.0f, 0.0f, 0.0f, 16.0f, 8.0f}};
        PackMixer.SIN_45 = (float)Math.sin(0.7853981633974483);
    }
}
