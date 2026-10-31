package dev.quelchi.aurora.gui.lq;

import dev.quelchi.aurora.glass.GlassKit;
import dev.quelchi.aurora.glass.hud.GlassHudElement;
import dev.quelchi.aurora.glass.hud.HudEditorScreen;
import dev.quelchi.aurora.gui.HudScale;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.gui.Theme;
import dev.quelchi.aurora.gui.VectorIcons;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.ModsScreen;
import dev.quelchi.aurora.gui.lq.HudBridge;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.render.SdfText;
import dev.quelchi.aurora.render.SdfText$Face;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

final class HudEditor
extends Screen {
    private final HudEditorScreen ed = GlassKit.createHudEditor().grid(1);
    private int[] bar = new int[4];
    private int[] reset = new int[4];
    private int[] done = new int[4];
    private DraggableHud sizing;
    private int[] from;
    private float fromScale;
    private final Screen back;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private static int argb(float[] fArray) {
        return Math.round(Math.min(1.0f, fArray[3]) * 255.0f) << 24 | Math.round(fArray[0] * 255.0f) << 16 | Math.round(fArray[1] * 255.0f) << 8 | Math.round(fArray[2] * 255.0f);
    }

    HudEditor(Screen screen) {
        super((Text)Text.method_43470((String)a[1]));
        this.back = screen;
    }

    public void method_25419() {
        this.field_22787.method_1507(this.back);
    }

    protected void method_25426() {
        this.ed.method_25423(HudScale.width(), HudScale.height());
    }

    public boolean method_25421() {
        return false;
    }

    public void method_25420(DrawContext drawContext, int n, int n2, float f) {
        drawContext.method_25294(0, 0, this.field_22789, this.field_22790, HudEditor.a(30063, 2351542743385343676L));
    }

    private static boolean in(int[] nArray, double d, double d2) {
        int n;
        block18: {
            block15: {
                double d3;
                boolean bl;
                block17: {
                    block16: {
                        block14: {
                            bl = HudBridge.a;
                            d3 = d;
                            if (bl) break block14;
                            if (!(d3 >= (double)nArray[0])) break block15;
                            d3 = d;
                        }
                        if (bl) break block16;
                        if (!(d3 < (double)(nArray[0] + nArray[2]))) break block15;
                        d3 = d2;
                    }
                    if (bl) break block17;
                    if (!(d3 >= (double)nArray[1])) break block15;
                    d3 = d2;
                }
                double d4 = d3 - (double)(nArray[1] + nArray[3]);
                n = d4 == 0.0 ? 0 : (d4 < 0.0 ? -1 : 1);
                if (bl) break block18;
                if (n >= 0) break block15;
                n = 1;
                break block18;
            }
            n = 0;
        }
        return n != 0;
    }

    private static int right(int[] nArray) {
        return Math.min(nArray[0] + nArray[2] + 2, HudScale.width() - 7);
    }

    private static int left(int[] nArray) {
        return Math.max(nArray[0] - 2, 7);
    }

    private static boolean hasSettings(GlassHudElement glassHudElement) {
        boolean bl;
        block5: {
            block4: {
                if (!Im.available() || HudBridge.owner(glassHudElement) == null) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    private static int top(int[] nArray) {
        return Math.max(nArray[1] - 2, 7);
    }

    private static int bottom(int[] nArray) {
        return Math.min(nArray[1] + nArray[3] + 2, HudScale.height() - 5);
    }

    public void method_25394(DrawContext drawContext, int n, int n2, float f) {
        Object object;
        block33: {
            Object object2;
            DrawContext drawContext2;
            block37: {
                GlassHudElement glassHudElement;
                block35: {
                    boolean bl;
                    GlassHudElement glassHudElement2;
                    block36: {
                        Object object3;
                        Object object4;
                        Object object5;
                        Object object6;
                        Object object7;
                        DrawContext drawContext3;
                        block34: {
                            Object object8;
                            block32: {
                                int[] nArray;
                                block31: {
                                    GlassHudElement glassHudElement3;
                                    block29: {
                                        block30: {
                                            block27: {
                                                HudScale.push(drawContext);
                                                glassHudElement2 = this.ed.selected();
                                                object = HudBridge.pieces().iterator();
                                                bl = HudBridge.a;
                                                while (object.hasNext()) {
                                                    block28: {
                                                        glassHudElement3 = object.next();
                                                        if (bl) break block27;
                                                        GlassHudElement glassHudElement4 = glassHudElement3;
                                                        int[] nArray2 = HudBridge.box(glassHudElement4);
                                                        if (nArray2 == null || glassHudElement4 == glassHudElement2) break block28;
                                                        Round.outline(drawContext, nArray2[0] - 2, nArray2[1] - 2, nArray2[2] + 4, nArray2[3] + 4, 4, HudEditor.a(253, 8800288112774935343L));
                                                    }
                                                    if (!bl) continue;
                                                    boolean bl2 = !Module.a;
                                                    Module.a = bl2;
                                                    break;
                                                }
                                                glassHudElement3 = glassHudElement2;
                                            }
                                            if (bl) break block29;
                                            if (glassHudElement3 != null) break block30;
                                            nArray = null;
                                            break block31;
                                        }
                                        glassHudElement3 = glassHudElement2;
                                    }
                                    nArray = HudBridge.box(glassHudElement3);
                                }
                                object = nArray;
                                object8 = object;
                                if (bl) break block32;
                                if (object8 == null) break block33;
                                object8 = object;
                            }
                            reference var8_9 = object8[0] - 2;
                            reference var9_11 = object[1] - 2;
                            reference var10_12 = object[2] + 4;
                            reference var11_13 = object[3] + 4;
                            int n3 = 6;
                            for (int i = 0; i < n3; ++i) {
                                drawContext3 = drawContext;
                                object7 = var8_9;
                                object6 = var9_11 + var11_13 * i / n3;
                                object5 = var8_9 + var10_12;
                                object4 = var9_11;
                                object3 = var11_13 * (i + 1);
                                if (!bl) {
                                    drawContext3.method_44379((int)object7, (int)object6, (int)object5, (int)(object4 + object3 / n3));
                                    Round.outline(drawContext, (int)var8_9, (int)var9_11, (int)var10_12, (int)var11_13, 4, Theme.mix(HudEditor.argb(Menu.ICON_TOP), HudEditor.argb(Menu.ICON_FOOT), ((float)i + 0.5f) / (float)n3));
                                    drawContext.method_44380();
                                    if (!bl) continue;
                                    break;
                                }
                                break block34;
                            }
                            glassHudElement = glassHudElement2;
                            if (bl) break block35;
                            if (!HudBridge.hud(glassHudElement).hudScalable()) break block36;
                            Round.rect(drawContext, HudEditor.right((int[])object) - 5, HudEditor.bottom((int[])object) - 5, 10, 10, 3, HudEditor.argb(Menu.FILL));
                            drawContext3 = drawContext;
                            object7 = HudEditor.right((int[])object) - 4;
                            object6 = HudEditor.bottom((int[])object) - 4;
                            object5 = 8;
                            object4 = 8;
                            object3 = 2;
                        }
                        Round.rect(drawContext3, (int)object7, (int)object6, (int)object5, (int)object4, (int)object3, -1);
                    }
                    drawContext2 = drawContext;
                    object2 = object;
                    if (bl) break block37;
                    Round.circle(drawContext2, HudEditor.right((int[])object2), HudEditor.top((int[])object), 14, -1);
                    VectorIcons.draw(drawContext, a[3], HudEditor.right((int[])object) - 4, HudEditor.top((int[])object) - 4, 8, HudEditor.a(202, 730153060173869851L));
                    glassHudElement = glassHudElement2;
                }
                if (!HudEditor.hasSettings(glassHudElement)) break block33;
                drawContext2 = drawContext;
                object2 = object;
            }
            Round.circle(drawContext2, HudEditor.left((int[])object2), HudEditor.top((int[])object), 14, -1);
            VectorIcons.draw(drawContext, a[4], HudEditor.left((int[])object) - 4, HudEditor.top((int[])object) - 4, 8, HudEditor.a(24724, 4023414341000926020L));
        }
        HudEditor hudEditor = this;
        DrawContext drawContext4 = drawContext;
        int n3 = (int)HudScale.toVirtual(n);
        int n4 = (int)HudScale.toVirtual(n2);
        boolean bl = object != null;
        hudEditor.bar(drawContext4, n3, n4, bl);
        HudScale.pop(drawContext);
    }

    private void bar(DrawContext drawContext, int n, int n2, boolean bl) {
        float[] fArray;
        float f;
        float f2;
        float f3;
        String string;
        SdfText$Face sdfText$Face;
        DrawContext drawContext2;
        int n3;
        String string2;
        block21: {
            boolean bl2;
            block19: {
                block20: {
                    boolean bl3;
                    boolean bl4;
                    int n4;
                    String string3;
                    block18: {
                        block17: {
                            HudEditor hudEditor;
                            block16: {
                                String[] stringArray = a;
                                String string4 = stringArray[5];
                                string3 = stringArray[2];
                                string2 = stringArray[0];
                                int n5 = (int)Math.ceil(SdfText.width(SdfText.SORA_LIGHT, string4, 10.0f));
                                n4 = (int)Math.ceil(SdfText.width(SdfText.SORA, string3, 10.0f)) + 18;
                                n3 = (int)Math.ceil(SdfText.width(SdfText.SORA, string2, 10.0f)) + 18;
                                int n6 = 12 + n5 + 19 + n4 + 9 + n3 + 6;
                                int n7 = 34;
                                int n8 = (HudScale.width() - n6) / 2;
                                int n9 = (int)HudScale.toVirtual(this.field_22790 - 60) - n7;
                                this.bar = new int[]{n8, n9, n6, n7};
                                Round.rect(drawContext, n8, n9, n6, n7, 10, HudEditor.argb(Im.GLASS));
                                Round.outline(drawContext, n8, n9, n6, n7, 10, HudEditor.argb(Im.RIM));
                                SdfText.draw(drawContext, SdfText.SORA_LIGHT, string4, n8 + 12, n9 + 13, 10.0f, HudEditor.argb(Im.MUTE), false);
                                bl4 = HudBridge.a;
                                drawContext.method_25294(n8 + 21 + n5, n9 + 9, n8 + 22 + n5, n9 + n7 - 9, HudEditor.argb(Im.RIM));
                                this.reset = new int[]{n8 + 31 + n5, n9 + 7, n4, 20};
                                hudEditor = this;
                                if (bl4) break block16;
                                hudEditor.done = new int[]{this.reset[0] + n4 + 9, n9 + 7, n3, 20};
                                if (!bl) break block17;
                                hudEditor = this;
                            }
                            bl3 = HudEditor.in(hudEditor.reset, n, n2);
                            if (bl4) break block18;
                            if (!bl3) break block17;
                            bl3 = true;
                            break block18;
                        }
                        bl3 = false;
                    }
                    boolean bl5 = bl3;
                    DrawContext drawContext3 = drawContext;
                    int n10 = this.reset[0];
                    int n11 = this.reset[1];
                    int n12 = n4;
                    int n13 = 20;
                    int n14 = 6;
                    float[] fArray2 = bl5 ? Im.QUIET_HI : Im.QUIET;
                    Round.rect(drawContext3, n10, n11, n12, n13, n14, HudEditor.argb(fArray2));
                    Round.outline(drawContext, this.reset[0], this.reset[1], n4, 20, 6, HudEditor.argb(Im.WASH));
                    drawContext2 = drawContext;
                    sdfText$Face = SdfText.SORA;
                    string = string3;
                    f3 = this.reset[0] + 9;
                    f2 = this.reset[1] + 6;
                    f = 10.0f;
                    bl2 = bl5;
                    if (bl4) break block19;
                    if (!bl2) break block20;
                    fArray = Im.INK;
                    break block21;
                }
                bl2 = bl;
            }
            fArray = bl2 ? Im.SUB : Im.MUTE;
        }
        SdfText.draw(drawContext2, sdfText$Face, string, f3, f2, f, HudEditor.argb(fArray), false);
        DrawContext drawContext4 = drawContext;
        int n15 = this.done[0];
        int n16 = this.done[1];
        int n17 = n3;
        int n18 = 20;
        int n19 = 6;
        float[] fArray3 = HudEditor.in(this.done, n, n2) ? Menu.ICON_FOOT : Menu.FILL;
        Round.rect(drawContext4, n15, n16, n17, n18, n19, HudEditor.argb(fArray3));
        SdfText.draw(drawContext, SdfText.SORA, string2, this.done[0] + 9, this.done[1] + 6, 10.0f, HudEditor.argb(Im.ON_FILL), false);
    }

    private static Click virtual(Click click) {
        return new Click(HudScale.toVirtual(click.comp_4798()), HudScale.toVirtual(click.comp_4799()), click.comp_4800());
    }

    public boolean method_25402(Click click, boolean bl) {
        int n;
        block91: {
            boolean bl2;
            block93: {
                block92: {
                    boolean bl3;
                    block83: {
                        Click click2;
                        block84: {
                            GlassHudElement glassHudElement;
                            int[] nArray;
                            block87: {
                                GlassHudElement glassHudElement2;
                                block88: {
                                    double d;
                                    block90: {
                                        double d2;
                                        block89: {
                                            double d3;
                                            block85: {
                                                block86: {
                                                    block75: {
                                                        block76: {
                                                            boolean bl4;
                                                            block81: {
                                                                block80: {
                                                                    int[] nArray2;
                                                                    block82: {
                                                                        block79: {
                                                                            Click click3;
                                                                            block77: {
                                                                                block78: {
                                                                                    int[] nArray3;
                                                                                    block74: {
                                                                                        GlassHudElement glassHudElement3;
                                                                                        block72: {
                                                                                            block73: {
                                                                                                click2 = HudEditor.virtual(click);
                                                                                                glassHudElement2 = this.ed.selected();
                                                                                                bl3 = HudBridge.a;
                                                                                                glassHudElement3 = glassHudElement2;
                                                                                                if (bl3) break block72;
                                                                                                if (glassHudElement3 != null) break block73;
                                                                                                nArray3 = null;
                                                                                                break block74;
                                                                                            }
                                                                                            glassHudElement3 = glassHudElement2;
                                                                                        }
                                                                                        nArray3 = HudBridge.box(glassHudElement3);
                                                                                    }
                                                                                    nArray = nArray3;
                                                                                    n = HudEditor.in(this.bar, click2.comp_4798(), click2.comp_4799());
                                                                                    if (bl3) break block75;
                                                                                    if (n == 0) break block76;
                                                                                    click3 = click2;
                                                                                    if (bl3) break block77;
                                                                                    if (click3.method_74245() != 0) break block78;
                                                                                    bl4 = HudEditor.in(this.done, click2.comp_4798(), click2.comp_4799());
                                                                                    if (bl3) break block79;
                                                                                    if (!bl4) break block78;
                                                                                    this.method_25419();
                                                                                    if (!bl3) break block80;
                                                                                }
                                                                                click3 = click2;
                                                                            }
                                                                            bl4 = click3.method_74245();
                                                                        }
                                                                        if (bl3) break block81;
                                                                        if (bl4) break block80;
                                                                        nArray2 = nArray;
                                                                        if (bl3) break block82;
                                                                        if (nArray2 == null) break block80;
                                                                        nArray2 = this.reset;
                                                                    }
                                                                    bl4 = HudEditor.in(nArray2, click2.comp_4798(), click2.comp_4799());
                                                                    if (bl3) break block81;
                                                                    if (!bl4) break block80;
                                                                    HudBridge.hud(glassHudElement2).resetHudPos();
                                                                }
                                                                bl4 = true;
                                                            }
                                                            return bl4;
                                                        }
                                                        n = click2.method_74245();
                                                    }
                                                    if (bl3) break block83;
                                                    if (n != 0) break block84;
                                                    if (nArray == null) break block84;
                                                    double d3 = Math.abs(click2.comp_4798() - (double)HudEditor.right(nArray)) - 7.0;
                                                    d3 = d3 == 0.0 ? 0 : (d3 < 0.0 ? -1 : 1);
                                                    if (bl3) break block85;
                                                    if (d3 > 0) break block86;
                                                    double d3 = Math.abs(click2.comp_4799() - (double)HudEditor.top(nArray)) - 7.0;
                                                    d3 = d3 == 0.0 ? 0 : (d3 < 0.0 ? -1 : 1);
                                                    if (bl3) break block85;
                                                    if (d3 > 0) break block86;
                                                    HudBridge.remove(glassHudElement2);
                                                    return true;
                                                }
                                                glassHudElement = glassHudElement2;
                                                if (bl3) break block87;
                                                d3 = (double)HudEditor.hasSettings(glassHudElement);
                                            }
                                            if (d3 == false) break block88;
                                            d2 = Math.abs(click2.comp_4798() - (double)HudEditor.left(nArray));
                                            if (bl3) break block89;
                                            if (!(d2 <= 7.0)) break block88;
                                            d2 = Math.abs(click2.comp_4799() - (double)HudEditor.top(nArray));
                                        }
                                        double d = d2 - 7.0;
                                        d = d == 0.0 ? 0 : (d < 0.0 ? -1 : 1);
                                        if (bl3) break block90;
                                        if (d > 0) break block88;
                                        this.field_22787.method_1507((Screen)new ModsScreen(HudBridge.owner(glassHudElement2)));
                                        d = 1.0;
                                    }
                                    return (boolean)d;
                                }
                                glassHudElement = glassHudElement2;
                            }
                            DraggableHud draggableHud = HudBridge.hud(glassHudElement);
                            n = draggableHud.hudScalable() ? 1 : 0;
                            if (bl3) break block83;
                            if (n == 0) break block84;
                            double d = Math.abs(click2.comp_4798() - (double)HudEditor.right(nArray)) - 9.0;
                            n = d == 0.0 ? 0 : (d < 0.0 ? -1 : 1);
                            if (bl3) break block83;
                            if (n > 0) break block84;
                            double d7 = Math.abs(click2.comp_4799() - (double)HudEditor.bottom(nArray)) - 9.0;
                            n = d7 == 0.0 ? 0 : (d7 < 0.0 ? -1 : 1);
                            if (bl3) break block83;
                            if (n > 0) break block84;
                            this.sizing = draggableHud;
                            this.from = nArray;
                            this.fromScale = draggableHud.getHudScale();
                            return true;
                        }
                        n = this.ed.method_25402(click2, bl) ? 1 : 0;
                    }
                    if (!Module.a) break block91;
                    if (!bl3) break block92;
                    bl2 = false;
                    break block93;
                }
                bl2 = true;
            }
            HudBridge.a = bl2;
        }
        return n != 0;
    }

    public boolean method_25403(Click click, double d, double d2) {
        Click click2;
        block5: {
            HudEditor hudEditor;
            block4: {
                click2 = HudEditor.virtual(click);
                boolean bl = HudBridge.a;
                hudEditor = this;
                if (bl) break block4;
                if (hudEditor.sizing != null) break block5;
                hudEditor = this;
            }
            return hudEditor.ed.method_25403(click2, HudScale.toVirtual(d), HudScale.toVirtual(d2));
        }
        double d3 = Math.max((click2.comp_4798() - (double)this.from[0]) / (double)Math.max(1, this.from[2]), (click2.comp_4799() - (double)this.from[1]) / (double)Math.max(1, this.from[3]));
        this.sizing.setHudScale((float)Math.max(0.25, Math.min(6.0, (double)this.fromScale * d3)));
        return true;
    }

    public boolean method_25406(Click click) {
        block4: {
            HudEditor hudEditor;
            block5: {
                boolean bl = HudBridge.a;
                hudEditor = this;
                if (bl) break block4;
                if (hudEditor.sizing != null) break block5;
                return this.ed.method_25406(HudEditor.virtual(click));
            }
            hudEditor = this;
        }
        hudEditor.sizing = null;
        return true;
    }

    public boolean method_25401(double d, double d2, double d3, double d4) {
        return this.ed.method_25401(HudScale.toVirtual(d), HudScale.toVirtual(d2), d3, d4);
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[6];
                        var11_1 = 0;
                        var10_2 = "\u007f \u001dB\ns\u001a7\u0007bUsO \u0001\u0005i*\u0000BS\u0005X#\u001cTB";
                        var12_3 = "\u007f \u001dB\ns\u001a7\u0007bUsO \u0001\u0005i*\u0000BS\u0005X#\u001cTB".length();
                        var9_4 = 4;
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
                            var10_2 = "H*\u0007SN_}H\u001e\u007f=\u0012@\u0007Eu\u001b\"\u001cQB\u001d:H,\u0001HK]:O SUBBsA*";
                            var12_3 = "H*\u0007SN_}H\u001e\u007f=\u0012@\u0007Eu\u001b\"\u001cQB\u001d:H,\u0001HK]:O SUBBsA*".length();
                            var9_4 = 8;
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
                                v10 = 59;
                                break;
                            }
                            case 1: {
                                v10 = 79;
                                break;
                            }
                            case 2: {
                                v10 = 115;
                                break;
                            }
                            case 3: {
                                v10 = 39;
                                break;
                            }
                            case 4: {
                                v10 = 39;
                                break;
                            }
                            case 5: {
                                v10 = 49;
                                break;
                            }
                            default: {
                                v10 = 26;
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
                HudEditor.a = var13;
                var0_7 = 5843943401384851560L;
                var6_8 = new long[4];
                var3_9 = 0;
                var4_10 = "\u001e\u00ef\u000f&\u00d3\u009f\u00ad&\f\u0011\u00f5V\u0097^\u00cdy";
                var5_11 = "\u001e\u00ef\u000f&\u00d3\u009f\u00ad&\f\u0011\u00f5V\u0097^\u00cdy".length();
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
                    var4_10 = "<\u008d\u00fb({\u00fd4\u00b8\u00da%k\u00b2q\u00ac\u00b8\u00de";
                    var5_11 = "<\u008d\u00fb({\u00fd4\u00b8\u00da%k\u00b2q\u00ac\u00b8\u00de".length();
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
        HudEditor.b = var6_8;
        HudEditor.c = new Integer[4];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x73D0;
        if (c[n2] == null) {
            HudEditor.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
