package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.gui.im.Edit;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.sound.SoundOverrides;
import dev.quelchi.aurora.sound.SoundOverrides$Override;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.type.ImString;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public final class SoundEditor
extends ImScreen {
    private static final float MAX_VOLUME = 2.0f;
    private static final float MAX_PITCH = 4.0f;
    private static final int LIMIT;
    private Identifier selected;
    private boolean adding;
    private boolean focus;
    private final ImString search;
    private static final String[] b;

    public SoundEditor() {
        block5: {
            Identifier identifier;
            Identifier identifier2;
            block4: {
                super(b[27]);
                boolean bl = ImScreen.a;
                this.search = new ImString(48);
                Identifier identifier3 = SoundOverrides.all().keySet().iterator();
                identifier2 = identifier3;
                if (bl) break block4;
                if (!identifier2.hasNext()) break block5;
                identifier2 = identifier3.next();
            }
            this.selected = identifier = (Identifier)identifier2;
        }
    }

    
    @Override
    protected boolean key(int n) {
        block11: {
            boolean bl;
            block10: {
                SoundEditor soundEditor;
                boolean bl2;
                block9: {
                    bl = ImScreen.a;
                    int n2 = n;
                    if (bl) return n2 != 0;
                    if (n2 != 256) return 0 != 0;
                    bl2 = Im.typing();
                    if (bl) break block9;
                    if (bl2) {
                        return 0 != 0;
                    }
                    soundEditor = this;
                    if (bl) break block10;
                    bl2 = soundEditor.adding;
                }
                if (!bl2) break block11;
                soundEditor = this;
            }
            soundEditor.adding = false;
            if (!bl) return true;
        }
        Edit.back();
        return true;
    }

    private static String label(Identifier identifier) {
        String string;
        block4: {
            block5: {
                boolean bl = ImScreen.a;
                string = b[22];
                if (bl) break block4;
                if (!string.equals(identifier.method_12836())) break block5;
                string = identifier.method_12832();
                break block4;
            }
            string = identifier.toString();
        }
        return string;
    }

    private static String pct(float f) {
        return Math.round(f * 100.0f) + "%";
    }

    private static void play(Identifier identifier) {
        SoundEvent soundEvent = (SoundEvent)Registries.field_41172.method_63535(identifier);
        if (soundEvent != null) {
            MinecraftClient.method_1551().method_1483().method_4873((SoundInstance)PositionedSoundInstance.method_4757((SoundEvent)soundEvent, (float)1.0f, (float)1.0f));
        }
    }

    @Override
    protected void draw() {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        block8: {
            SoundEditor soundEditor;
            float f6;
            float f7;
            block6: {
                block7: {
                    String[] stringArray = b;
                    Menu.begin(stringArray[19], 0.0f);
                    Edit.bar(stringArray[10], stringArray[26]);
                    f5 = Menu.x + 144.0f;
                    f4 = Menu.y + 56.0f;
                    f3 = Menu.w - 144.0f;
                    f2 = Menu.h - 56.0f;
                    boolean bl = ImScreen.a;
                    f = Math.min(378.0f, f3 * 0.45f);
                    f7 = f5 + 20.0f;
                    f6 = f3 - f - 40.0f;
                    soundEditor = this;
                    if (bl) break block6;
                    if (!soundEditor.adding) break block7;
                    this.search(f7, f4 + 20.0f, f6, f4 + f2 - 1.0f);
                    if (!bl) break block8;
                }
                soundEditor = this;
            }
            soundEditor.list(f7, f4 + 20.0f, f6, f4 + f2 - 1.0f);
        }
        this.pane(f5 + f3 - f, f4, f, f2);
        Menu.end();
    }

    private void list(float f, float f2, float f3, float f4) {
        block20: {
            ArrayList<Identifier> arrayList;
            boolean bl;
            block18: {
                float f5;
                block19: {
                    bl = ImScreen.a;
                    if (Edit.add(b[1], f, f2, f3)) {
                        this.focus = true;
                        this.adding = true;
                        this.search.set("");
                    }
                    f5 = f2 + 46.0f + 8.0f;
                    arrayList = new ArrayList<Identifier>(SoundOverrides.all().keySet());
                    if (bl) break block18;
                    if (!arrayList.isEmpty()) break block19;
                    Ui.text(Im.sub, f + 2.0f, f5 + 8.0f, Im.MUTE, b[0]);
                    return;
                }
                String[] stringArray = b;
                Edit.list(stringArray[20], f, f5, f3, f4 - f5);
            }
            ImDrawList imDrawList = ImGui.getWindowDrawList();
            float f6 = ImGui.getCursorScreenPosY();
            int n = 0;
            while (n < arrayList.size()) {
                String string;
                SoundOverrides$Override soundOverrides$Override;
                Identifier identifier;
                block25: {
                    SoundOverrides$Override soundOverrides$Override2;
                    block23: {
                        block24: {
                            block21: {
                                block22: {
                                    if (bl) break block20;
                                    identifier = (Identifier)arrayList.get(n);
                                    if (bl) break block21;
                                    ImGui.pushID((int)n);
                                    if (!Edit.row(b[11], f, f6, f3, 46.0f, identifier.equals((Object)this.selected))) break block22;
                                    this.selected = identifier;
                                }
                                ImGui.popID();
                            }
                            soundOverrides$Override = SoundOverrides.get(identifier);
                            soundOverrides$Override2 = soundOverrides$Override;
                            if (bl) break block23;
                            if (!soundOverrides$Override2.muted()) break block24;
                            string = b[17];
                            break block25;
                        }
                        soundOverrides$Override2 = soundOverrides$Override;
                    }
                    string = SoundEditor.pct(soundOverrides$Override2.volume());
                }
                String string2 = string;
                float f7 = f + f3 - 14.0f - Ui.width(Im.label, string2);
                imDrawList.pushClipRect(f, f6, f7 - 10.0f, f6 + 46.0f, true);
                ImFont imFont = Im.sora13;
                float f8 = f + 12.0f;
                float f9 = Ui.mid(Im.sora13, f6, 46.0f);
                float[] fArray = soundOverrides$Override.muted() ? Im.MUTE : Im.INK;
                Ui.text(imFont, f8, f9, fArray, SoundEditor.label(identifier));
                imDrawList.popClipRect();
                Ui.text(Im.label, f7, Ui.mid(Im.label, f6, 46.0f), Im.MUTE, string2);
                ++n;
                f6 += 50.0f;
                if (!bl) continue;
            }
            Edit.endList(f, f6);
        }
    }

    
    private void search(float var1_1, float var2_2, float var3_3, float var4_4) {
        block44: {
            block42: {
                block43: {
                    block39: {
                        block40: {
                            block41: {
                                block38: {
                                    block36: {
                                        block37: {
                                            block34: {
                                                block35: {
                                                    var13_5 = SoundEditor.b;
                                                    var6_6 = Edit.buttonWidth(var13_5[33], false);
                                                    var5_7 = ImScreen.a;
                                                    Edit.field(var13_5[15], var13_5[5], this.search, var1_1, var2_2, var3_3 - var6_6 - 8.0f, this.focus);
                                                    v0 = this;
                                                    if (var5_7) break block34;
                                                    v0.focus = false;
                                                    if (!Edit.button(var13_5[28], null, var1_1 + var3_3, var2_2 + 3.0f, false)) break block35;
                                                    this.adding = false;
                                                }
                                                v0 = this;
                                            }
                                            var7_8 = v0.search.get().trim().toLowerCase(Locale.ROOT);
                                            v3 = var7_8;
                                            if (var5_7) break block36;
                                            if (!v3.isEmpty()) break block37;
                                            v5 = SoundOverrides.heard();
                                            break block38;
                                        }
                                        v3 = var7_8;
                                    }
                                    v5 = SoundEditor.find(v3);
                                }
                                var8_9 = v5;
                                var9_10 = var2_2 + 42.0f;
                                if (!var8_9.isEmpty()) break block39;
                                v7 = Im.sub;
                                v8 = var1_1 + 2.0f;
                                v9 = var9_10 + 8.0f;
                                v10 = Im.MUTE;
                                v11 = var7_8;
                                if (var5_7) break block40;
                                if (!v11.isEmpty()) break block41;
                                v11 = SoundEditor.b[14];
                                break block40;
                            }
                            var13_5 = SoundEditor.b;
                            v11 = var13_5[35];
                        }
                        Ui.text(v7, v8, v9, v10, v11);
                        return;
                    }
                    v15 = var1_1 + 2.0f;
                    v16 = var9_10 + 2.0f;
                    v17 = var7_8;
                    if (var5_7) break block42;
                    if (!v17.isEmpty()) break block43;
                    v17 = SoundEditor.b[21];
                    break block42;
                }
                var13_5 = SoundEditor.b;
                v17 = var13_5[23];
            }
            Ui.label(v15, v16, v17);
            var13_5 = SoundEditor.b;
            Edit.list(var13_5[24], var1_1, var9_10 += 22.0f, var3_3, var4_4 - var9_10);
            var10_11 = ImGui.getCursorScreenPosY();
            var11_12 = 0;
            while (var11_12 < var8_9.size()) {
                block48: {
                    block47: {
                        block45: {
                            block46: {
                                var12_13 = var8_9.get(var11_12);
                                if (var5_7) break block44;
                                if (var5_7) break block45;
                                ImGui.pushID((int)var11_12);
                                if (!Edit.row(SoundEditor.b[30], var1_1, var10_11, var3_3, 34.0f, false)) break block46;
                                SoundEditor.play(var12_13);
                            }
                            v23 = Im.sora12r;
                            v24 = var1_1 + 12.0f;
                            v25 = Im.sora12r;
                            v26 = var10_11;
                            v27 = 34.0f;
                            if (var5_7) break block47;
                            Ui.text(v23, v24, Ui.mid(v25, v26, v27), Im.INK, SoundEditor.label(var12_13));
                        }
                        if (SoundOverrides.forSound(var12_13) != null) {
                            v23 = Im.label;
                            v24 = var1_1 + var3_3 - 14.0f - Ui.width(Im.label, SoundEditor.b[25]);
                            v25 = Im.label;
                            v26 = var10_11;
                            v27 = 34.0f;
                        }
                    }
                    Ui.text(v23, v24, Ui.mid(v25, v26, v27), Im.MUTE, SoundEditor.b[12]);
                    if (!var5_7) break block48;
lbl140:

                    if (!Menu.tag(SoundEditor.b[31], var1_1 + var3_3 - 14.0f, var10_11 + 7.0f, false)) break block48;
                    this.selected = SoundOverrides.add(var12_13);
                    ConfigManager.save();
                    this.adding = false;
                }
                ImGui.popID();
                ++var11_12;
                var10_11 += 34.0f;
                if (!var5_7) continue;
            }
            Edit.endList(var1_1, var10_11);
        }
    }

    private static List<Identifier> find(String string) {
        ArrayList<Identifier> arrayList;
        block10: {
            arrayList = new ArrayList<Identifier>();
            boolean bl = ImScreen.a;
            for (Identifier identifier : Registries.field_41172.method_10235()) {
                int n;
                block11: {
                    block12: {
                        if (bl) break block10;
                        n = identifier.toString().toLowerCase(Locale.ROOT).contains(string);
                        if (bl) break block11;
                        if (n == 0) break block12;
                        arrayList.add(identifier);
                    }
                    n = arrayList.size();
                }
                if (n >= 400) {
                    break;
                }
                if (!bl) continue;
            }
            arrayList.sort(Comparator.comparing(Identifier::toString));
        }
        return arrayList;
    }

    private void pane(float f, float f2, float f3, float f4) {
        block67: {
            block69: {
                Identifier identifier;
                Identifier identifier2;
                block68: {
                    boolean bl;
                    block66: {
                        boolean bl2;
                        float f5;
                        float f6;
                        float f7;
                        block64: {
                            boolean bl3;
                            block65: {
                                block62: {
                                    block63: {
                                        float f8;
                                        block58: {
                                            float f9;
                                            block59: {
                                                boolean bl4;
                                                float f10;
                                                float f11;
                                                SoundOverrides$Override soundOverrides$Override;
                                                SoundOverrides$Override soundOverrides$Override2;
                                                Identifier identifier3;
                                                block61: {
                                                    block60: {
                                                        SoundOverrides$Override soundOverrides$Override3;
                                                        float f12;
                                                        SoundOverrides$Override soundOverrides$Override4;
                                                        block56: {
                                                            float f13;
                                                            block57: {
                                                                block54: {
                                                                    block55: {
                                                                        float f14;
                                                                        block52: {
                                                                            float f15;
                                                                            String[] stringArray;
                                                                            block53: {
                                                                                block50: {
                                                                                    block51: {
                                                                                        Identifier identifier4;
                                                                                        ImDrawList imDrawList;
                                                                                        block48: {
                                                                                            block49: {
                                                                                                block46: {
                                                                                                    block47: {
                                                                                                        imDrawList = ImGui.getWindowDrawList();
                                                                                                        bl = ImScreen.a;
                                                                                                        imDrawList.addLine(f, f2, f, f2 + f4, Ui.col(Im.LINE));
                                                                                                        f7 = f + 14.0f;
                                                                                                        f6 = f3 - 28.0f;
                                                                                                        f13 = f7 + f6 - 12.0f;
                                                                                                        if (bl) break block46;
                                                                                                        if (this.selected == null) break block47;
                                                                                                        identifier4 = this.selected;
                                                                                                        if (bl) break block48;
                                                                                                        if (SoundOverrides.forSound(identifier4) != null) break block49;
                                                                                                    }
                                                                                                    Ui.text(Im.sub, f + (f3 - Ui.width(Im.sub, b[6])) / 2.0f, f2 + 40.0f, Im.MUTE, b[2]);
                                                                                                }
                                                                                                return;
                                                                                            }
                                                                                            identifier4 = this.selected;
                                                                                        }
                                                                                        soundOverrides$Override4 = SoundOverrides.get(identifier4);
                                                                                        imDrawList.pushClipRect(f7, f2, f7 + f6, f2 + 40.0f, true);
                                                                                        Ui.text(Im.sora13, f7, f2 + 16.0f, Im.INK, SoundEditor.label(this.selected));
                                                                                        imDrawList.popClipRect();
                                                                                        stringArray = b;
                                                                                        Ui.text(Im.label, f7, f2 + 37.0f, Im.MUTE, stringArray[7]);
                                                                                        f9 = f2 + 64.0f;
                                                                                        Edit.group(f7, f9, f6, 3);
                                                                                        Edit.name(stringArray[18], f7, f9);
                                                                                        f15 = soundOverrides$Override4.volume() / 2.0f;
                                                                                        f14 = Edit.slider(stringArray[3], f13 - 160.0f, f9 + 8.0f, 110.0f, f15);
                                                                                        f12 = f14 == f15 ? 0 : (f14 > f15 ? 1 : -1);
                                                                                        if (bl) break block50;
                                                                                        if (f12 == false) break block51;
                                                                                        soundOverrides$Override4 = new SoundOverrides$Override(SoundEditor.step(f14, 2.0f), soundOverrides$Override4.pitch(), soundOverrides$Override4.muted());
                                                                                        SoundOverrides.set(this.selected, soundOverrides$Override4);
                                                                                    }
                                                                                    f12 = (float)ImGui.isItemDeactivated();
                                                                                }
                                                                                if (bl) break block52;
                                                                                if (f12 == false) break block53;
                                                                                ConfigManager.save();
                                                                            }
                                                                            Edit.value(SoundEditor.pct(soundOverrides$Override4.volume()), f13, f9 + 7.0f);
                                                                            stringArray = b;
                                                                            Edit.name(stringArray[32], f7, f9 + 34.0f);
                                                                            f15 = soundOverrides$Override4.pitch() / 4.0f;
                                                                            f14 = Edit.slider(stringArray[13], f13 - 160.0f, f9 + 34.0f + 8.0f, 110.0f, f15);
                                                                            f12 = f14 == f15 ? 0 : (f14 > f15 ? 1 : -1);
                                                                        }
                                                                        if (bl) break block54;
                                                                        if (f12 == false) break block55;
                                                                        soundOverrides$Override4 = new SoundOverrides$Override(soundOverrides$Override4.volume(), SoundEditor.step(f14, 4.0f), soundOverrides$Override4.muted());
                                                                        SoundOverrides.set(this.selected, soundOverrides$Override4);
                                                                    }
                                                                    f12 = (float)ImGui.isItemDeactivated();
                                                                }
                                                                if (bl) break block56;
                                                                if (f12 == false) break block57;
                                                                ConfigManager.save();
                                                            }
                                                            f8 = soundOverrides$Override4.pitch();
                                                            if (bl) break block58;
                                                            Edit.value(SoundEditor.pct(f8), f13, f9 + 34.0f + 7.0f);
                                                            Edit.name(b[29], f7, f9 + 68.0f);
                                                            f12 = (float)Ui.toggle(b[9], f13 - 32.0f, f9 + 68.0f + 9.0f, 32.0f, 16.0f, soundOverrides$Override4.muted());
                                                        }
                                                        if (f12 == false) break block59;
                                                        identifier3 = this.selected;
                                                        soundOverrides$Override2 = soundOverrides$Override3;
                                                        soundOverrides$Override = soundOverrides$Override3;
                                                        f11 = soundOverrides$Override4.volume();
                                                        f10 = soundOverrides$Override4.pitch();
                                                        if (soundOverrides$Override4.muted()) break block60;
                                                        bl4 = true;
                                                        break block61;
                                                    }
                                                    bl4 = false;
                                                }
                                                soundOverrides$Override2(f11, f10, bl4);
                                                SoundOverrides.set(identifier3, soundOverrides$Override);
                                                ConfigManager.save();
                                            }
                                            f8 = f9 + 102.0f + 12.0f;
                                        }
                                        f5 = f8;
                                        boolean bl5 = Registries.field_41172.method_63535(this.selected) != null;
                                        bl3 = bl5;
                                        bl2 = bl3;
                                        if (bl) break block62;
                                        if (!bl2) break block63;
                                        bl2 = Edit.button(b[8], b[4], f7 + f6, f5, true);
                                        if (bl) break block62;
                                        if (!bl2) break block63;
                                        SoundEditor.play(this.selected);
                                    }
                                    bl2 = bl3;
                                }
                                if (bl) break block64;
                                if (bl2) break block65;
                                Ui.text(Im.label, f7, Ui.mid(Im.label, f5, 28.0f), Im.MUTE, b[16]);
                            }
                            bl2 = bl3;
                        }
                        float f16 = bl2 ? f7 + f6 - Edit.buttonWidth(b[34], true) - 8.0f : f7 + f6;
                        float f17 = f16;
                        if (bl) break block66;
                        if (!Edit.button(b[36], null, f17, f5, false)) break block67;
                        SoundOverrides.remove(this.selected);
                        this.selected = null;
                    }
                    Identifier identifier5 = SoundOverrides.all().keySet().iterator();
                    identifier2 = identifier5;
                    if (bl) break block68;
                    if (!identifier2.hasNext()) break block69;
                    identifier2 = identifier5.next();
                }
                this.selected = identifier = (Identifier)identifier2;
            }
            ConfigManager.save();
        }
    }

    private static float step(float f, float f2) {
        return (float)((double)Math.round(f * f2 * 20.0f) / 20.0);
    }

    
    static {
        var9 = new String[37];
        var7_1 = 0;
        var6_2 = "F\u0016Y\u0018k)]l\nY\bl=]o\u001c\u001dK}9G&\u000bI\u001d\u001dKe|@g\f\u0017\u000f\fX\u0010\u001a\u0000$=\u0013{\u0016\f\u0005`\b+Z\u000f\u0004h)^m\u0004x\u0015\u0018\u0012\u0012[\u001c\u0018\u0019g4\u0013m\u000f\u001c\u0019}|@g\f\u0017\u000f\fX\u0010\u001a\u0000$=\u0013{\u0016\f\u0005`\u001e@\u0016\u000eKh3FlY\u0018\u0005`|[g\u000eY\u0003m;[(\u0010\rKt0Rq\n\u0004X\u0015\u0018\u0012\u0006+Z\u0014\u001ep9\u0010[\u0016\f\u0005`|pg\u0017\r\u0019k0_m\u000b\u0003z\u0016\u000e\u0005I\u001d\u001d\u000e`\u0007+Z\t\u0002p?[2F\u0016\r\u0003m2T(\u0011\u001c\nv8\u0013q\u001c\rE$\u0011Rc\u001cY\n$/\\}\u0017\u001dKl=Cx\u001c\u0017G$3A(\n\u001c\nv?[&\u0006+Z\u001f\u0002j8\u0011F\u0016\rKt0Rq\u0018\u001b\u0007a|[m\u000b\u001c\u0005E\f\r\u000e`\u0006^\u0016\u0015\u001ei9\u0007E\u0016\u001d\u001eh9@\u0006{\u0016\f\u0005`/\u000eZ\u001c\u001a\u000ej(_qY\u0011\u000ee.W\te\u0010\u0017\u000eg.Rn\r\u0007Z\u001c\n\u001eh(@\u0005n\u0016\f\u0005`\u0005I\u001d\u001d\u000e`\u0006[\u0016\f\u0005`/\u0010[\u0016\f\u0005`|pg\u0017\r\u0019k0_m\u000b\u0006K\u0018\u0017\ba0\u0004E\f\r\u000e\u0004`\u001c\u0018\u0019\u0003I\u001d\u001d\u0005X\u0010\r\bl\u0006K\u0018\u0017\ba0\u0004X\u0015\u0018\u0012";
        var8_3 = "F\u0016Y\u0018k)]l\nY\bl=]o\u001c\u001dK}9G&\u000bI\u001d\u001dKe|@g\f\u0017\u000f\fX\u0010\u001a\u0000$=\u0013{\u0016\f\u0005`\b+Z\u000f\u0004h)^m\u0004x\u0015\u0018\u0012\u0012[\u001c\u0018\u0019g4\u0013m\u000f\u001c\u0019}|@g\f\u0017\u000f\fX\u0010\u001a\u0000$=\u0013{\u0016\f\u0005`\u001e@\u0016\u000eKh3FlY\u0018\u0005`|[g\u000eY\u0003m;[(\u0010\rKt0Rq\n\u0004X\u0015\u0018\u0012\u0006+Z\u0014\u001ep9\u0010[\u0016\f\u0005`|pg\u0017\r\u0019k0_m\u000b\u0003z\u0016\u000e\u0005I\u001d\u001d\u000e`\u0007+Z\t\u0002p?[2F\u0016\r\u0003m2T(\u0011\u001c\nv8\u0013q\u001c\rE$\u0011Rc\u001cY\n$/\\}\u0017\u001dKl=Cx\u001c\u0017G$3A(\n\u001c\nv?[&\u0006+Z\u001f\u0002j8\u0011F\u0016\rKt0Rq\u0018\u001b\u0007a|[m\u000b\u001c\u0005E\f\r\u000e`\u0006^\u0016\u0015\u001ei9\u0007E\u0016\u001d\u001eh9@\u0006{\u0016\f\u0005`/\u000eZ\u001c\u001a\u000ej(_qY\u0011\u000ee.W\te\u0010\u0017\u000eg.Rn\r\u0007Z\u001c\n\u001eh(@\u0005n\u0016\f\u0005`\u0005I\u001d\u001d\u000e`\u0006[\u0016\f\u0005`/\u0010[\u0016\f\u0005`|pg\u0017\r\u0019k0_m\u000b\u0006K\u0018\u0017\ba0\u0004E\f\r\u000e\u0004`\u001c\u0018\u0019\u0003I\u001d\u001d\u0005X\u0010\r\bl\u0006K\u0018\u0017\ba0\u0004X\u0015\u0018\u0012".length();
        var5_4 = 22;
        var4_5 = -1;
        block11: while (true) {
            v0 = ++var4_5;
            v1 = var6_2.substring(v0, v0 + var5_4);
            v2 = -1;
            block12: while (true) {
                var6_2 = "F\u0016\r\u0003m2T(\u0014\u0018\u001fg4V{Y\r\u0003e(\u001d\u0006Z\u001c\u0014\u0004r9";
                var8_3 = "F\u0016\r\u0003m2T(\u0014\u0018\u001fg4V{Y\r\u0003e(\u001d\u0006Z\u001c\u0014\u0004r9".length();
                var5_4 = 21;
                var4_5 = -1;
                while (true) {
                    v3 = ++var4_5;
                    v1 = var6_2.substring(v3, v3 + var5_4);
                    v2 = 0;
lbl21:

                    v4 = v1.toCharArray();
                    v5 = v4;
                    v6 = v4.length;
                    var10_6 = 0;
                    do {
                        v5 = v5;
                        v7 = var10_6;
                        v8 = v5[v7];
                        switch (var10_6 % 7) {
                            case 0: {
                                v9 = 8;
                                break;
                            }
                            case 1: {
                                v9 = 121;
                                break;
                            }
                            case 2: {
                                v9 = 121;
                                break;
                            }
                            case 3: {
                                v9 = 107;
                                break;
                            }
                            case 4: {
                                v9 = 4;
                                break;
                            }
                            case 5: {
                                v9 = 92;
                                break;
                            }
                            default: {
                                v9 = 51;
                            }
                        }
                        v5[v7] = (char)(v8 ^ v9);
                        ++var10_6;
lbl53:

                        v6 = v6;
                    } while (v6 > var10_6);
                    v10 = new String(v5);
                    switch (v2) {
                        default: {
                            var9[var7_1++] = v10.intern();
                            if ((var4_5 += var5_4) >= var8_3) continue block12;
                            var5_4 = var6_2.charAt(var4_5);
                            continue block11;
                        }
                        case 0: 
                    }
                    var9[var7_1++] = v10.intern();
                    if ((var4_5 += var5_4) >= var8_3) {
                        SoundEditor.b = var9;
                        var2_7 = 8106821466330387311L;
                        var0_8 = 9118809400144974591L ^ var2_7;
                        SoundEditor.LIMIT = (int)var0_8;
                        return;
                    }
                    var5_4 = var6_2.charAt(var4_5);
                }
                break;
            }
            break;
        }
    }
}
