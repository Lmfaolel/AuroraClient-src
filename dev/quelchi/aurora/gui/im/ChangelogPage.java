package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync$Release;
import dev.quelchi.aurora.gui.im.ChangelogPage$Line;
import dev.quelchi.aurora.gui.im.Edit;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.SettingsScreen;
import dev.quelchi.aurora.gui.im.Ui;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public final class ChangelogPage
extends ImScreen {
    private static final float PAD = 20.0f;
    private static final float MAX = 660.0f;
    private static final float LINE = 19.0f;
    private static final DateTimeFormatter WHEN;
    private final List<List<ChangelogPage$Line>> notes = new ArrayList<List<ChangelogPage$Line>>();
    private List<CosmeticsSync$Release> wrapped;
    private float wrappedTo;
    private static final String[] b;

    public ChangelogPage() {
        super(b[13]);
    }

    protected void method_25426() {
        CosmeticsSync.fetchReleases();
    }

    @Override
    public boolean shutsOnMenuKey() {
        return true;
    }

    @Override
    protected boolean key(int n) {
        int n2;
        block4: {
            block5: {
                boolean bl = ImScreen.a;
                n2 = n;
                if (bl) break block4;
                if (n2 == 256) break block5;
                return false;
            }
            ChangelogPage.back();
            n2 = 1;
        }
        return n2 != 0;
    }

    private static void back() {
        MinecraftClient.method_1551().method_1507((Screen)new SettingsScreen());
    }

    
    @Override
    protected void draw() {
        String[] stringArray = b;
        Menu.begin(stringArray[14], 0.0f);
        boolean bl = ImScreen.a;
        ChangelogPage.bar();
        float f = Menu.x + 144.0f;
        float f2 = Menu.y + 56.0f;
        float f3 = Menu.w - 144.0f;
        float f4 = Menu.h - 56.0f;
        List<CosmeticsSync$Release> list = CosmeticsSync.releases();
        if (bl) return;
        if (list.isEmpty()) {
            Ui.text(Im.sub, f + 20.0f, f2 + 20.0f, Im.MUTE, b[4]);
            Ui.text(Im.sub, f + 20.0f, f2 + 20.0f + 19.0f, Im.MUTE, b[1]);
            Menu.end();
            return;
        }
        float f5 = Math.min(660.0f, f3 - 40.0f);
        if (list != this.wrapped || f5 != this.wrappedTo) {
            this.wrap(list, f5);
        }
        String string = CosmeticsSync.myVersion();
        stringArray = b;
        Edit.list(stringArray[15], f + 1.0f, f2 + 1.0f, f3 - 2.0f, f4 - 2.0f);
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        float f6 = f + 20.0f;
        float f7 = ImGui.getCursorScreenPosY() + 20.0f - 1.0f;
        for (int i = 0; i < list.size(); ++i) {
            Record record = list.get(i);
            block13: while (true) {
                int n;
                CosmeticsSync$Release cosmeticsSync$Release = record;
                if (bl) return;
                if (!bl) {
                    if (i > 0) {
                        imDrawList.addLine(f6, f7 + 16.0f, f6 + f5, f7 + 16.0f, Ui.wash(0.06f));
                        f7 += 33.0f;
                    }
                    Ui.text(Im.sora13, f6, Ui.mid(Im.sora13, f7, 20.0f), Im.INK, cosmeticsSync$Release.version());
                }
                Object object = cosmeticsSync$Release;
                if (!bl) {
                    if (((CosmeticsSync$Release)object).version().equals(string)) {
                        float f8 = f6 + Ui.width(Im.sora13, cosmeticsSync$Release.version()) + 10.0f;
                        stringArray = b;
                        float f9 = Ui.width(Im.label, stringArray[12]) + 14.0f;
                        Ui.accent(imDrawList, f8, f7 + 1.0f, f8 + f9, f7 + 19.0f, Ui.col(Menu.FILL), 5.0f);
                        Ui.text(Im.label, f8 + 7.0f, Ui.mid(Im.label, f7 + 1.0f, 18.0f), Im.ON_FILL, stringArray[7]);
                    }
                    object = cosmeticsSync$Release;
                }
                if (!bl) {
                    if (((CosmeticsSync$Release)object).at() > 0L) {
                        String string2 = WHEN.format(Instant.ofEpochSecond(cosmeticsSync$Release.at()));
                        Ui.text(Im.label, f6 + f5 - Ui.width(Im.label, string2), Ui.mid(Im.label, f7, 20.0f), Im.MUTE, string2);
                    }
                    f7 += 28.0f;
                    object = this.notes.get(i);
                }
                List list2 = (List)object;
                int n2 = list2.isEmpty();
                if (!bl) {
                    if (n2 != 0) {
                        Ui.text(Im.sub, f6, Ui.mid(Im.sub, f7, 24.0f), Im.MUTE, b[10]);
                        f7 += 24.0f;
                    }
                    n2 = n = 0;
                }
                while (n < list2.size()) {
                    int n3;
                    ChangelogPage$Line changelogPage$Line;
                    block32: {
                        ChangelogPage$Line changelogPage$Line2;
                        block30: {
                            block31: {
                                changelogPage$Line = (ChangelogPage$Line)list2.get(n);
                                record = changelogPage$Line;
                                if (bl) continue block13;
                                if (bl) break block30;
                                if (!((ChangelogPage$Line)record).first()) break block31;
                                n3 = n;
                                if (bl) break block32;
                                if (n3 > 0) {
                                    f7 += 5.0f;
                                }
                            }
                            changelogPage$Line2 = changelogPage$Line;
                        }
                        n3 = changelogPage$Line2.first();
                    }
                    if (n3 != 0) {
                        imDrawList.addCircleFilled(f6 + 2.0f, f7 + 9.5f, 2.0f, Ui.col(Menu.ACCENT), 12);
                    }
                    Ui.text(Im.sub, f6 + 14.0f, Ui.mid(Im.sub, f7, 19.0f), Im.SUB, changelogPage$Line.text());
                    f7 += 19.0f;
                    ++n;
                    if (!bl) continue;
                }
                break;
            }
            if (!bl) continue;
        }
        Edit.endList(f6, f7 + 20.0f);
        Menu.end();
    }

    private static void bar() {
        float f = Menu.x + 144.0f + 20.0f;
        float f2 = Menu.y;
        float f3 = Ui.mid(Im.sora13, f2, 56.0f);
        ImGui.setCursorScreenPos((float)f, (float)(f2 + 14.0f));
        String[] stringArray = b;
        boolean bl = ImGui.invisibleButton((String)stringArray[5], (float)28.0f, (float)28.0f);
        boolean bl2 = ImGui.isItemHovered();
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        ImDrawList imDrawList2 = imDrawList;
        float f4 = f + 14.0f;
        float f5 = f2 + 28.0f;
        float f6 = 14.0f;
        float[] fArray = bl2 ? Im.QUIET_HI : Im.QUIET;
        imDrawList2.addCircleFilled(f4, f5, f6, Ui.col(fArray), 32);
        imDrawList.addCircle(f + 14.0f, f2 + 28.0f, 14.0f, Ui.wash(0.16f), 32);
        float[] fArray2 = bl2 ? Im.INK : Im.SUB;
        int n = Ui.col(fArray2);
        imDrawList.addLine(f + 15.5f, f2 + 23.0f, f + 10.5f, f2 + 28.0f, n, 1.6f);
        imDrawList.addLine(f + 10.5f, f2 + 28.0f, f + 15.5f, f2 + 33.0f, n, 1.6f);
        ImGui.setCursorScreenPos((float)(f += 38.0f), (float)(f2 + 18.0f));
        stringArray = b;
        bl |= ImGui.invisibleButton((String)stringArray[3], (float)Ui.width(Im.sora13, stringArray[9]), (float)20.0f);
        ImFont imFont = Im.sora13;
        float f7 = f;
        float f8 = f3;
        float[] fArray3 = ImGui.isItemHovered() ? Im.INK : Im.SUB;
        stringArray = b;
        Ui.text(imFont, f7, f8, fArray3, stringArray[14]);
        f = Menu.chevron(f + Ui.width(Im.sora13, stringArray[14]) + 8.0f, f3);
        Ui.text(Im.sora13, f, f3, Im.INK, stringArray[16]);
        if (bl) {
            ChangelogPage.back();
        }
    }

    private void wrap(List<CosmeticsSync$Release> list, float f) {
        this.wrapped = list;
        boolean bl = ImScreen.a;
        this.wrappedTo = f;
        this.notes.clear();
        for (CosmeticsSync$Release cosmeticsSync$Release : list) {
            this.notes.add(ChangelogPage.lines(cosmeticsSync$Release.notes(), f));
            if (!bl) continue;
        }
    }

    static List<ChangelogPage$Line> lines(String string, float f) {
        ArrayList<ChangelogPage$Line> arrayList = new ArrayList<ChangelogPage$Line>();
        String[] stringArray = string.split("\n");
        boolean bl = ImScreen.a;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            block38: {
                block37: {
                    String string2;
                    String string3;
                    block35: {
                        block36: {
                            block33: {
                                block34: {
                                    String string4;
                                    block32: {
                                        string3 = stringArray[n2];
                                        string4 = string3.strip();
                                        if (!bl) {
                                            block31: {
                                                string3 = string4;
                                                if (string3.startsWith(b[2])) break block31;
                                                string4 = string3;
                                                if (bl) break block32;
                                                if (string4.startsWith(b[8])) break block31;
                                                string2 = string3;
                                                if (bl) break block33;
                                                if (!string2.startsWith(b[11])) break block34;
                                            }
                                            string4 = string3.substring(2).strip();
                                        }
                                    }
                                    string3 = string4;
                                }
                                string2 = string3;
                            }
                            if (bl) break block35;
                            if (!string2.isEmpty()) break block36;
                            break block37;
                        }
                        string2 = "";
                    }
                    String string5 = string2;
                    boolean bl2 = true;
                    for (String string6 : string3.split(b[6])) {
                        Object object;
                        block42: {
                            String string7;
                            block43: {
                                float f2;
                                block44: {
                                    Object object2;
                                    block41: {
                                        String string8;
                                        block39: {
                                            block40: {
                                                if (bl) break block38;
                                                string8 = string5;
                                                if (bl) break block39;
                                                if (!string8.isEmpty()) break block40;
                                                object2 = string6;
                                                break block41;
                                            }
                                            string8 = string5;
                                        }
                                        object2 = string8 + " " + string6;
                                    }
                                    string7 = object2;
                                    object = string5;
                                    if (bl) break block42;
                                    if (((String)object).isEmpty()) break block43;
                                    float f3 = Ui.width(Im.sub, string7) - (f - 14.0f);
                                    f2 = f3 == 0.0f ? 0 : (f3 > 0.0f ? 1 : -1);
                                    if (bl) break block44;
                                    if (f2 <= 0) break block43;
                                    arrayList.add(new ChangelogPage$Line(string5, bl2));
                                    f2 = 0.0f;
                                }
                                bl2 = f2;
                                string5 = string6;
                                if (!bl) continue;
                            }
                            object = string7;
                        }
                        string5 = object;
                        if (!bl) continue;
                    }
                    arrayList.add(new ChangelogPage$Line(string5, bl2));
                }
                ++n2;
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[17];
                var3_1 = 0;
                var2_2 = "/3\u0001>z\u0019H2j59\u0019v \u0016VJT83?\u001bXN\u0011>cl\u001bRKTk|\"\u0010R\u0019Pkq9\u001a[]\u0011\"`l\u0003BJY.wl\u0004^MYkrl\u001dVTTe\u0002f3\u0005(a9\u001eU\u0016\u0005|8\u001b^WVkc9\u0011[PB#v(SN\\Ee\u0004)r/\u0018\u0003\u0017`g\u0007\bF\u001e!rwe\u0002a3\b\u0018v8\u0007^WV8\u0016\u0005|l\u001dXMT83*\u001cE\u0019E#z?SXWTe\u0002\u20693\u0007\bF\u001e!rwe\t\b{-\u001dP\\]$t\b\u0018v8\u0007^WV8";
                var4_3 = "/3\u0001>z\u0019H2j59\u0019v \u0016VJT83?\u001bXN\u0011>cl\u001bRKTk|\"\u0010R\u0019Pkq9\u001a[]\u0011\"`l\u0003BJY.wl\u0004^MYkrl\u001dVTTe\u0002f3\u0005(a9\u001eU\u0016\u0005|8\u001b^WVkc9\u0011[PB#v(SN\\Ee\u0004)r/\u0018\u0003\u0017`g\u0007\bF\u001e!rwe\u0002a3\b\u0018v8\u0007^WV8\u0016\u0005|l\u001dXMT83*\u001cE\u0019E#z?SXWTe\u0002\u20693\u0007\bF\u001e!rwe\t\b{-\u001dP\\]$t\b\u0018v8\u0007^WV8".length();
                var1_4 = 10;
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
                    var2_2 = "9v \u0016VJT8\t\b{-\u001dP\\]$t";
                    var4_3 = "9v \u0016VJT8\t\b{-\u001dP\\]$t".length();
                    var1_4 = 8;
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
                        v10 = 75;
                        break;
                    }
                    case 1: {
                        v10 = 19;
                        break;
                    }
                    case 2: {
                        v10 = 76;
                        break;
                    }
                    case 3: {
                        v10 = 115;
                        break;
                    }
                    case 4: {
                        v10 = 55;
                        break;
                    }
                    case 5: {
                        v10 = 57;
                        break;
                    }
                    default: {
                        v10 = 49;
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
        ChangelogPage.b = var5;
        ChangelogPage.WHEN = DateTimeFormatter.ofPattern(ChangelogPage.b[0]).withZone(ZoneId.systemDefault());
    }
}
