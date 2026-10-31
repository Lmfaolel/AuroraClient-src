package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.SettingsScreen;
import dev.quelchi.aurora.gui.im.Themes;
import dev.quelchi.aurora.gui.im.Themes$Look;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.social.Plus;
import dev.quelchi.aurora.social.Plus$Status;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public final class PlusScreen
extends ImScreen {
    private static final float PAD = 20.0f;
    private static final DateTimeFormatter DAY;
    private static final String[][] PERKS;
    private String note = "";
    private static final String[] b;

    public PlusScreen() {
        super(b[12]);
        Plus.refresh(false);
    }

    @Override
    protected void draw() {
        String[] stringArray = b;
        Menu.begin(stringArray[6], 0.0f);
        float f = Menu.x + 144.0f;
        float f2 = Menu.y;
        float f3 = Menu.w - 144.0f;
        Ui.text(Im.sora16, f + 20.0f, Ui.mid(Im.sora16, f2, 56.0f), Im.INK, stringArray[12]);
        float f4 = f + 20.0f;
        float f5 = f3 - 40.0f;
        float f6 = f2 + 56.0f + 20.0f;
        float f7 = Plus.active() ? this.member(f4, f6, f5) : this.pitch(f4, f6, f5);
        f6 = f7;
        PlusScreen.perks(f4, f6, f5);
        Menu.end();
    }

    private float member(float f, float f2, float f3) {
        boolean bl;
        float f4;
        float f5;
        float f6;
        float f7;
        String string;
        MinecraftClient minecraftClient;
        block5: {
            block6: {
                ImDrawList imDrawList = ImGui.getWindowDrawList();
                minecraftClient = MinecraftClient.method_1551();
                Ui.box(imDrawList, f, f2, f + f3, f2 + 104.0f, Im.SURFACE, Im.SURFACE_LINE, 14.0f, 1.0f);
                Ui.plusLogo(f + 22.0f, f2 + 26.0f, 52.0f, true);
                float f8 = Ui.pair(Im.head, Im.sub, f2, 104.0f);
                String[] stringArray = b;
                Ui.text(Im.head, f + 92.0f, f8, Im.INK, stringArray[8]);
                Ui.text(Im.sub, f + 92.0f, Ui.under(Im.head, Im.sub, f8), Im.MUTE, PlusScreen.standing(Plus.status()));
                float f9 = 130.0f;
                boolean bl2 = ImScreen.a;
                string = stringArray[11];
                f7 = f + f3 - 22.0f - f9;
                f6 = f2 + 36.0f;
                f5 = f9;
                f4 = 32.0f;
                bl = Themes.look().plus;
                if (bl2) break block5;
                if (bl) break block6;
                bl = true;
                break block5;
            }
            bl = false;
        }
        if (Menu.button(string, f7, f6, f5, f4, bl)) {
            minecraftClient.method_1507((Screen)new SettingsScreen());
        }
        return f2 + 120.0f;
    }

    private static String standing(Plus$Status plus$Status) {
        Plus$Status plus$Status2;
        String string;
        block11: {
            block12: {
                boolean bl = ImScreen.a;
                String string2 = plus$Status.given() ? b[27] : "";
                String string3 = string2;
                if (plus$Status.lifetime()) {
                    return b[30] + string3 + ".";
                }
                string = DAY.format(Instant.ofEpochSecond(plus$Status.expires()).atZone(ZoneId.systemDefault()));
                plus$Status2 = plus$Status;
                if (bl) break block11;
                if (!plus$Status2.renews()) break block12;
                return b[21] + string + ".";
            }
            plus$Status2 = plus$Status;
        }
        String string4 = plus$Status2.given() ? b[31] + string + "." : b[24] + string + ".";
        return string4;
    }

    private float pitch(float f, float f2, float f3) {
        String string;
        float f4;
        float f5;
        block7: {
            block8: {
                ImDrawList imDrawList = ImGui.getWindowDrawList();
                Ui.box(imDrawList, f, f2, f + f3, f2 + 104.0f, Im.SURFACE, Im.SURFACE_LINE, 14.0f, 1.0f);
                Ui.plusLogo(f + 22.0f, f2 + 26.0f, 52.0f, true);
                float f6 = Ui.pair(Im.head, Im.sub, f2, 104.0f);
                String[] stringArray = b;
                Ui.text(Im.head, f + 92.0f, f6, Im.INK, stringArray[0]);
                Ui.text(Im.sub, f + 92.0f, Ui.under(Im.head, Im.sub, f6), Im.MUTE, stringArray[1]);
                f5 = f + f3 - 22.0f;
                f4 = 120.0f;
                String string2 = stringArray[26];
                String string3 = stringArray[33];
                float f7 = Ui.width(Im.h3, string2) + Ui.width(Im.sub, string3);
                Ui.text(Im.h3, f5 - f7, Ui.baseline(Im.sub, f2 + 22.0f, Im.h3), Im.INK, string2);
                Ui.text(Im.sub, f5 - Ui.width(Im.sub, string3), f2 + 22.0f, Im.MUTE, string3);
                boolean bl = ImScreen.a;
                string = stringArray[2];
                if (bl) break block7;
                if (!Menu.button(string, f5 - f4, f2 + 52.0f, f4, 32.0f, true)) break block8;
                this.note = b[23];
            }
            string = this.note;
        }
        if (!string.isEmpty()) {
            Ui.text(Im.label, f5 - f4 - 14.0f - Ui.width(Im.label, this.note), Ui.mid(Im.label, f2 + 52.0f, 32.0f), Im.SUB, this.note);
        }
        return f2 + 120.0f;
    }

    private static void perks(float f, float f2, float f3) {
        block5: {
            ImDrawList imDrawList = ImGui.getWindowDrawList();
            boolean bl = ImScreen.a;
            float f4 = 12.0f;
            float f5 = (f3 - f4 * 2.0f) / 3.0f;
            float f6 = 112.0f;
            for (int i = 0; i < PERKS.length; ++i) {
                String string;
                float[] fArray;
                float f7;
                float f8;
                float f9;
                ImFont imFont;
                block8: {
                    block7: {
                        int n;
                        String[][] stringArray;
                        block6: {
                            float f10 = f + (float)(i % 3) * (f5 + f4);
                            if (bl) break block5;
                            float f11 = f2 + (float)(i / 3) * (f6 + f4);
                            Ui.box(imDrawList, f10, f11, f10 + f5, f11 + f6, Im.SURFACE, Im.SURFACE_LINE, 14.0f, 1.0f);
                            imDrawList.addCircleFilled(f10 + 34.0f, f11 + 34.0f, 17.0f, Ui.col(Im.FIELD), 32);
                            imDrawList.addCircle(f10 + 34.0f, f11 + 34.0f, 16.5f, Ui.col(Ui.tint(0.28f)), 32, 1.0f);
                            Ui.icon(PERKS[i][0], f10 + 25.5f, f11 + 25.5f, 17.0f, Ui.soft());
                            Ui.text(Im.sora14, f10 + 62.0f, Ui.mid(Im.sora14, f11 + 17.0f, 34.0f), Im.INK, PERKS[i][1]);
                            imFont = Im.sub;
                            f9 = f10 + 18.0f;
                            f8 = f11 + 62.0f;
                            f7 = f5 - 36.0f;
                            fArray = Im.MUTE;
                            stringArray = PERKS;
                            n = i;
                            if (bl) break block6;
                            if (stringArray[n][2] == null) break block7;
                            stringArray = PERKS;
                            n = i;
                        }
                        string = stringArray[n][2];
                        break block8;
                    }
                    string = PlusScreen.plusLooks();
                }
                Ui.wrap(imFont, f9, f8, f7, fArray, string, 18.0f);
                if (!bl) continue;
            }
            Ui.wrap(Im.sub, f + 2.0f, f2 += (float)((PERKS.length + 2) / 3) * (f6 + f4) + 6.0f, f3 - 4.0f, Im.MUTE, b[20], 19.5f);
        }
    }

    private static String plusLooks() {
        String string;
        Object object;
        block16: {
            String[] stringArray;
            block15: {
                Object object2;
                Object object3;
                ArrayList<String> arrayList;
                block14: {
                    block11: {
                        arrayList = new ArrayList<String>();
                        object3 = Themes$Look.values();
                        boolean bl = ImScreen.a;
                        int n = ((Themes$Look[])object3).length;
                        int n2 = 0;
                        while (n2 < n) {
                            block12: {
                                block13: {
                                    Themes$Look themes$Look = object3[n2];
                                    if (bl) break block11;
                                    if (bl) break block12;
                                    if (!themes$Look.plus) break block13;
                                    arrayList.add(themes$Look.label);
                                }
                                ++n2;
                            }
                            if (!bl) continue;
                        }
                        if (arrayList.size() >= 2) break block11;
                        object2 = String.join((CharSequence)"", arrayList);
                        break block14;
                    }
                    stringArray = b;
                    object2 = String.join((CharSequence)stringArray[7], arrayList.subList(0, arrayList.size() - 1)) + stringArray[4] + (String)arrayList.get(arrayList.size() - 1);
                }
                object3 = object2;
                object = object3;
                if (arrayList.size() != 1) break block15;
                string = b[16];
                break block16;
            }
            stringArray = b;
            string = stringArray[10];
        }
        return (String)object + string + b[29];
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[36];
                var3_1 = 0;
                var2_2 = "vd\n\u0007*gOg}\r\u001b8ri\f\u001a9&\u0003X~\u0013\u001bx`\u0000E19\u001d*i\u001dV=X\t6bOV1\u000f\t!&\u001bX1\u0010\r4vO\\t\u001d\u0018xo\u001b\u0017v\u0017\u00016aA\bpt\fH\bj\u001aD\u000bD~\u001b\u00019j0Ty\u0019\u001c\u0005\u0017p\u0016\fx\tT~\u000b\u0005=r\u0006Tb\u0004g}\r\u001b\u0002\u001b1\u0014n~\rH0g\u0019R19\u001d*i\u001dV1(\u0004-u\u000eg}\r\u001bxe\u0000D|\u001d\u001c1e\u001c\r\u001b1\u0015\r6sOCy\u001d\u0005=u\u000bg}\r\u001bxr\u0007R|\u001d\u001b\u000bvd\n\u0007*gOg}\r\u001b\nD}\u0017\u001c\u0007d\u000eSv\u001d\rsx\u000b\u000b7t\u000b\u0017a\u001d\u001a3u0cc\u0001H6c\u0018\u0017g\u001d\u001a+o\u0000YbX\u0007>&.Bc\u0017\u001a9&\rRw\u0017\u001a=&\nAt\n\u00117h\n\u0017t\u0014\u001b=(\u000e\u001b1\u0019H5c\u0001B1\f\u0000=k\n\u000bS15%\u0015KONh\u0001\u0011\u000ecy\u001dH\bj\u001aD1\u001a\t<a\n9v1(\u0004-uOE~\u0014\rxg\u0001S1\u0019H5c\u0002Ut\n\u001b\u007f&\f_p\u0016\u0006=jOX\u007fX\u001c0cOvd\n\u0007*gOsx\u000b\u000b7t\u000b\u0019\u00c7g}\r\u001bxo\u001c\u0017~\u0016\u0004!&\u000eU~\r\u001cxj\u0000Xz\u000bFxC\u0019Rc\u0001H5i\u000bB}\u001dDxg\u0001S1\u0019\u0006!r\u0007^\u007f\u001fH,n\u000eC1\u001b\u00009h\bRbX\u00007qON~\rH(j\u000eN=X\u001b,g\u0016D1\u001e\u001a=cOQ~\nH=p\nEh\u0017\u0006=(Og}\r\u001bxe\u0000De\u000bH|2A\u000e(X\txk\u0000Ye\u0010H9h\u000b\u0017x\u000bH(g\u0006S1\u0015\u00076r\u0007[hVH\u0011`ON~\rH9t\n\u0017d\u0016\f=tO\u0006)TH9u\u0004\u0017pX\u00189t\nYeX\n=`\u0000EtX\n-\u007f\u0006YvV\net\u0016\r/uOX\u007fX\u000bg}\r\u001bxr\u0007R|\u001d\u001b*t~\u0015\u00016aOD~\u0017\u0006v&?[d\u000bH1uOY~\fH7hODp\u0014\rxl\u001aDeX\u0011=rA\br\u007f\u001c\u001bxi\u0001\u0017\frp\n\u0004!&\u000eTr\u001d\u001b+\u0005\u0013%VQa\u001a\u001b1\u001f\u0001.c\u0001\u0017s\u0001H,n\n\u0017P\r\u001a7t\u000e\u0017e\u001d\t5Czt\u0015\n=t\u001c\u001a~\u0016\u0004!&\fXb\u0015\r,o\fD=X\u00117s\u001dD1\f\u0007xq\nVcX\u000e7tOVbX\u00047h\b\u0017p\u000bH!i\u001a\u0017y\u0019\u001e=&?[d\u000bF\u0012\u0017~\u0016\u0004!&\u0002R|\u001a\r*uOPt\fF\u0013{x\u001e\r,o\u0002R1\u0015\r5d\nEb\u0010\u0001( px\u000e\r6&\rN1\f\u0000=&.Bc\u0017\u001a9&\u001bRp\u0015Dxs\u0001Cx\u0014H\u0004G}\u0019\u0011\b\u0017pX\u00057h\u001b_";
                var4_3 = "vd\n\u0007*gOg}\r\u001b8ri\f\u001a9&\u0003X~\u0013\u001bx`\u0000E19\u001d*i\u001dV=X\t6bOV1\u000f\t!&\u001bX1\u0010\r4vO\\t\u001d\u0018xo\u001b\u0017v\u0017\u00016aA\bpt\fH\bj\u001aD\u000bD~\u001b\u00019j0Ty\u0019\u001c\u0005\u0017p\u0016\fx\tT~\u000b\u0005=r\u0006Tb\u0004g}\r\u001b\u0002\u001b1\u0014n~\rH0g\u0019R19\u001d*i\u001dV1(\u0004-u\u000eg}\r\u001bxe\u0000D|\u001d\u001c1e\u001c\r\u001b1\u0015\r6sOCy\u001d\u0005=u\u000bg}\r\u001bxr\u0007R|\u001d\u001b\u000bvd\n\u0007*gOg}\r\u001b\nD}\u0017\u001c\u0007d\u000eSv\u001d\rsx\u000b\u000b7t\u000b\u0017a\u001d\u001a3u0cc\u0001H6c\u0018\u0017g\u001d\u001a+o\u0000YbX\u0007>&.Bc\u0017\u001a9&\rRw\u0017\u001a=&\nAt\n\u00117h\n\u0017t\u0014\u001b=(\u000e\u001b1\u0019H5c\u0001B1\f\u0000=k\n\u000bS15%\u0015KONh\u0001\u0011\u000ecy\u001dH\bj\u001aD1\u001a\t<a\n9v1(\u0004-uOE~\u0014\rxg\u0001S1\u0019H5c\u0002Ut\n\u001b\u007f&\f_p\u0016\u0006=jOX\u007fX\u001c0cOvd\n\u0007*gOsx\u000b\u000b7t\u000b\u0019\u00c7g}\r\u001bxo\u001c\u0017~\u0016\u0004!&\u000eU~\r\u001cxj\u0000Xz\u000bFxC\u0019Rc\u0001H5i\u000bB}\u001dDxg\u0001S1\u0019\u0006!r\u0007^\u007f\u001fH,n\u000eC1\u001b\u00009h\bRbX\u00007qON~\rH(j\u000eN=X\u001b,g\u0016D1\u001e\u001a=cOQ~\nH=p\nEh\u0017\u0006=(Og}\r\u001bxe\u0000De\u000bH|2A\u000e(X\txk\u0000Ye\u0010H9h\u000b\u0017x\u000bH(g\u0006S1\u0015\u00076r\u0007[hVH\u0011`ON~\rH9t\n\u0017d\u0016\f=tO\u0006)TH9u\u0004\u0017pX\u00189t\nYeX\n=`\u0000EtX\n-\u007f\u0006YvV\net\u0016\r/uOX\u007fX\u000bg}\r\u001bxr\u0007R|\u001d\u001b*t~\u0015\u00016aOD~\u0017\u0006v&?[d\u000bH1uOY~\fH7hODp\u0014\rxl\u001aDeX\u0011=rA\br\u007f\u001c\u001bxi\u0001\u0017\frp\n\u0004!&\u000eTr\u001d\u001b+\u0005\u0013%VQa\u001a\u001b1\u001f\u0001.c\u0001\u0017s\u0001H,n\n\u0017P\r\u001a7t\u000e\u0017e\u001d\t5Czt\u0015\n=t\u001c\u001a~\u0016\u0004!&\fXb\u0015\r,o\fD=X\u00117s\u001dD1\f\u0007xq\nVcX\u000e7tOVbX\u00047h\b\u0017p\u000bH!i\u001a\u0017y\u0019\u001e=&?[d\u000bF\u0012\u0017~\u0016\u0004!&\u0002R|\u001a\r*uOPt\fF\u0013{x\u001e\r,o\u0002R1\u0015\r5d\nEb\u0010\u0001( px\u000e\r6&\rN1\f\u0000=&.Bc\u0017\u001a9&\u001bRp\u0015Dxs\u0001Cx\u0014H\u0004G}\u0019\u0011\b\u0017pX\u00057h\u001b_".length();
                var1_4 = 11;
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
                    var2_2 = "Yp\u000e7+c\u001bCx\u0016\u000f+Hv1(\u0004-uOZp\n\u0003xh\nOeX\u001c7&\u0016Xd\nH6g\u0002R1\u0011\u0006xe\u0007VeTH,n\n\u0017e\u0019\nxj\u0006DeX\t6bOVs\u0017\u001e=&\u0016Xd\nH0c\u000eS?";
                    var4_3 = "Yp\u000e7+c\u001bCx\u0016\u000f+Hv1(\u0004-uOZp\n\u0003xh\nOeX\u001c7&\u0016Xd\nH6g\u0002R1\u0011\u0006xe\u0007VeTH,n\n\u0017e\u0019\nxj\u0006DeX\t6bOVs\u0017\u001e=&\u0016Xd\nH0c\u000eS?".length();
                    var1_4 = 12;
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
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 55;
                        break;
                    }
                    case 1: {
                        v10 = 17;
                        break;
                    }
                    case 2: {
                        v10 = 120;
                        break;
                    }
                    case 3: {
                        v10 = 104;
                        break;
                    }
                    case 4: {
                        v10 = 88;
                        break;
                    }
                    case 5: {
                        v10 = 6;
                        break;
                    }
                    default: {
                        v10 = 111;
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
        PlusScreen.b = var5;
        var0_6 = PlusScreen.b;
        PlusScreen.DAY = DateTimeFormatter.ofPattern(var0_6[17], Locale.ENGLISH);
        PlusScreen.PERKS = new String[][]{{var0_6[34], var0_6[22], null}, {var0_6[13], var0_6[18], var0_6[35]}, {var0_6[5], var0_6[9], var0_6[28]}, {var0_6[32], var0_6[25], var0_6[15]}, {var0_6[3], var0_6[14], var0_6[19]}};
    }
}
