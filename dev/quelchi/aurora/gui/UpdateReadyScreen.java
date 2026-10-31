package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.gui.Theme;
import dev.quelchi.aurora.module.Module;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class UpdateReadyScreen
extends Screen {
    private final Screen parent;
    private static final int W;
    private static final int H;
    private static final int BTN_H;
    private int panelX;
    private int panelY;
    private int restartX;
    private int laterX;
    private int btnY;
    private int btnW;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    public UpdateReadyScreen(Screen screen) {
        super((Text)Text.method_43470((String)a[2]));
        this.parent = screen;
    }

    private void layout() {
        this.panelX = (this.field_22789 - 260) / 2;
        this.panelY = (this.field_22790 - 112) / 2;
        this.btnW = UpdateReadyScreen.a(17561, 2942154046462396775L);
        this.btnY = this.panelY + 112 - 16 - 20;
        this.restartX = this.panelX + 16;
        this.laterX = this.restartX + this.btnW + 10;
    }

    public void method_25394(DrawContext drawContext, int n, int n2, float f) {
        this.method_25420(drawContext, n, n2, f);
        this.layout();
        Round.rect(drawContext, this.panelX, this.panelY, 260, 112, 14, Theme.alpha(UpdateReadyScreen.a(27702, 4582245270669926863L), 205));
        Round.framed(drawContext, this.panelX, this.panelY, 260, 112, 14, 0, Theme.alpha(-1, 140));
        int n3 = this.panelX + 16;
        int n4 = this.panelY + 16;
        String[] stringArray = a;
        drawContext.method_51433(this.field_22793, stringArray[3], n3, n4, -1, false);
        int n5 = PreviewScene.a;
        drawContext.method_51433(this.field_22793, stringArray[0], n3, n4 + 16, UpdateReadyScreen.a(1984, 9035301373148416571L), false);
        drawContext.method_51433(this.field_22793, stringArray[4], n3, n4 + 28, UpdateReadyScreen.a(32666, 4998151235496030821L), false);
        this.button(drawContext, this.restartX, stringArray[5], n, n2, true);
        this.button(drawContext, this.laterX, stringArray[1], n, n2, false);
        if (Module.a) {
            PreviewScene.a = ++n5;
        }
    }

    private void button(DrawContext drawContext, int n, String string, int n2, int n3, boolean n4) {
        int n5;
        int n6;
        int n7;
        String string2;
        TextRenderer textRenderer;
        DrawContext drawContext2;
        block41: {
            block43: {
                block42: {
                    int n8;
                    int n9;
                    int n10;
                    block44: {
                        int n11;
                        int n12;
                        block39: {
                            block40: {
                                block35: {
                                    block36: {
                                        int n13;
                                        int n14;
                                        block37: {
                                            block38: {
                                                int n15;
                                                block34: {
                                                    block31: {
                                                        block33: {
                                                            block32: {
                                                                block30: {
                                                                    n10 = PreviewScene.a;
                                                                    n15 = n2;
                                                                    if (n10 != 0) break block30;
                                                                    if (n15 < n) break block31;
                                                                    n15 = n2;
                                                                }
                                                                if (n10 != 0) break block32;
                                                                if (n15 > n + this.btnW) break block31;
                                                                n15 = n3;
                                                            }
                                                            if (n10 != 0) break block33;
                                                            if (n15 < this.btnY) break block31;
                                                            n15 = n3;
                                                        }
                                                        if (n10 != 0) break block34;
                                                        if (n15 > this.btnY + 20) break block31;
                                                        n15 = 1;
                                                        break block34;
                                                    }
                                                    n15 = 0;
                                                }
                                                n9 = n15;
                                                n12 = n4;
                                                if (n10 != 0) break block35;
                                                if (n12 == 0) break block36;
                                                n14 = -1;
                                                n13 = n9;
                                                if (n10 != 0) break block37;
                                                if (n13 == 0) break block38;
                                                n13 = 170;
                                                break block37;
                                            }
                                            n13 = 120;
                                        }
                                        n8 = Theme.alpha(n14, n13);
                                        break block44;
                                    }
                                    n12 = -1;
                                }
                                n11 = n9;
                                if (n10 != 0) break block39;
                                if (n11 == 0) break block40;
                                n11 = 45;
                                break block39;
                            }
                            n11 = 25;
                        }
                        n8 = Theme.alpha(n12, n11);
                    }
                    int n16 = n8;
                    Round.rect(drawContext, n, this.btnY, this.btnW, 20, 8, n16);
                    int n17 = this.field_22793.method_1727(string);
                    drawContext2 = drawContext;
                    textRenderer = this.field_22793;
                    string2 = string;
                    n7 = n;
                    n6 = this.btnW - n17;
                    n5 = 2;
                    if (n10 != 0) break block41;
                    n7 = n7 + n6 / n5;
                    n6 = this.btnY + 6;
                    if (n9 != 0) break block42;
                    n5 = n4;
                    if (n10 != 0) break block41;
                    if (n5 == 0) break block43;
                }
                n5 = UpdateReadyScreen.a(12325, 8141152300546915807L);
                break block41;
            }
            n5 = UpdateReadyScreen.a(14928, 6040870967142840230L);
        }
        drawContext2.method_51433(textRenderer, string2, n7, n6, n5, false);
    }

    public boolean method_25402(Click click, boolean bl) {
        double d;
        block28: {
            block29: {
                double d2;
                double d3;
                int n;
                double d4;
                block30: {
                    int n2;
                    block32: {
                        block31: {
                            block34: {
                                UpdateReadyScreen updateReadyScreen;
                                block33: {
                                    d4 = click.comp_4798();
                                    double d5 = click.comp_4799();
                                    n = PreviewScene.a;
                                    double d6 = d5 - (double)this.btnY;
                                    d = d6 == 0.0 ? 0 : (d6 > 0.0 ? 1 : -1);
                                    if (n != 0) break block28;
                                    if (d < 0) break block29;
                                    double d7 = d5 - (double)(this.btnY + 20);
                                    d = d7 == 0.0 ? 0 : (d7 < 0.0 ? -1 : 1);
                                    if (n != 0) break block28;
                                    if (d > 0) break block29;
                                    d3 = d4;
                                    d2 = this.restartX;
                                    if (n != 0) break block30;
                                    if (!(d3 >= d2)) break block31;
                                    d3 = d4;
                                    n2 = this.restartX + this.btnW;
                                    if (n != 0) break block32;
                                    if (!(d3 <= (double)n2)) break block31;
                                    updateReadyScreen = this;
                                    if (n != 0) break block33;
                                    if (updateReadyScreen.field_22787 == null) break block34;
                                    updateReadyScreen = this;
                                }
                                updateReadyScreen.field_22787.method_1592();
                            }
                            return true;
                        }
                        d3 = d4;
                        n2 = this.laterX;
                    }
                    d2 = n2;
                }
                double d8 = d3 - d2;
                d = d8 == 0.0 ? 0 : (d8 > 0.0 ? 1 : -1);
                if (n != 0) break block28;
                if (d < 0) break block29;
                double d9 = d4 - (double)(this.laterX + this.btnW);
                d = d9 == 0.0 ? 0 : (d9 < 0.0 ? -1 : 1);
                if (n != 0) break block28;
                if (d > 0) break block29;
                this.method_25419();
                return true;
            }
            d = (double)super.method_25402(click, bl);
        }
        return (boolean)d;
    }

    public void method_25419() {
        block5: {
            UpdateReadyScreen updateReadyScreen;
            block4: {
                int n = PreviewScene.a;
                updateReadyScreen = this;
                if (n != 0) break block4;
                if (updateReadyScreen.field_22787 == null) break block5;
                updateReadyScreen = this;
            }
            updateReadyScreen.field_22787.method_1507(this.parent);
        }
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[6];
                        var11_1 = 0;
                        var10_2 = "n?QY&\u0007^N5B\u0017+\u0006[\n&CE#\u000eVO4\b\u0005f1RR8\rk%TX8\t\u001f\u007f BV>\r\u0013k%TX8\t\u001f_ BV>\r\u001fX5GS3";
                        var12_3 = "n?QY&\u0007^N5B\u0017+\u0006[\n&CE#\u000eVO4\b\u0005f1RR8\rk%TX8\t\u001f\u007f BV>\r\u0013k%TX8\t\u001f_ BV>\r\u001fX5GS3".length();
                        var9_4 = 24;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block25;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "c$\u0006V:\u0018SC5U\u0017>\u0000Z\n>CO>HKC=C\u00173\u0007J\n<GB$\u000bW\u0004\u000bx5UC+\u001aK\n>I@";
                            var12_3 = "c$\u0006V:\u0018SC5U\u0017>\u0000Z\n>CO>HKC=C\u00173\u0007J\n<GB$\u000bW\u0004\u000bx5UC+\u001aK\n>I@".length();
                            var9_4 = 36;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block25;
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
                            break block26;
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
                                v10 = 42;
                                break;
                            }
                            case 1: {
                                v10 = 80;
                                break;
                            }
                            case 2: {
                                v10 = 38;
                                break;
                            }
                            case 3: {
                                v10 = 55;
                                break;
                            }
                            case 4: {
                                v10 = 74;
                                break;
                            }
                            case 5: {
                                v10 = 104;
                                break;
                            }
                            default: {
                                v10 = 63;
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
                UpdateReadyScreen.a = var13;
                var0_7 = 693430170311932354L;
                var6_8 = new long[9];
                var3_9 = 0;
                var4_10 = "?\u0098\u00f8\u00ea\u00bc%\u00b0\u00c8\u008a\u00e2\u0084\u008c#\u0005\u0017\rH\u001bD\u00b7\u00a6\u00db\u00d4\u00f8\u00e85&\u00bc\u00b8+\u00e1p\u00b9\u001f\u00f9w&y7\u00eaj6\u001f\u00ceO\u0016\u0080\u000e\u00bcuL\u00e1in\u00bc\u00de";
                var5_11 = "?\u0098\u00f8\u00ea\u00bc%\u00b0\u00c8\u008a\u00e2\u0084\u008c#\u0005\u0017\rH\u001bD\u00b7\u00a6\u00db\u00d4\u00f8\u00e85&\u00bc\u00b8+\u00e1p\u00b9\u001f\u00f9w&y7\u00eaj6\u001f\u00ceO\u0016\u0080\u000e\u00bcuL\u00e1in\u00bc\u00de".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v11 = var6_8;
                    v12 = var3_9++;
                    v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "\u0000|\u00det\u00a1\u0005\u0098\r\u0094\u00e7o\u00ce\u009c\u00bf\b\u00b6";
                    var5_11 = "\u0000|\u00det\u00a1\u0005\u0098\r\u0094\u00e7o\u00ce\u009c\u00bf\b\u00b6".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v11 = var6_8;
                        v12 = var3_9++;
                        v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var0_7;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        UpdateReadyScreen.b = var6_8;
        UpdateReadyScreen.c = new Integer[9];
        UpdateReadyScreen.H = UpdateReadyScreen.a(18580, 6273767407116191084L);
        UpdateReadyScreen.W = UpdateReadyScreen.a(5195, 7376218909156891062L);
        UpdateReadyScreen.BTN_H = UpdateReadyScreen.a(8402, 2443132567736610094L);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4DFE;
        if (c[n2] == null) {
            UpdateReadyScreen.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
