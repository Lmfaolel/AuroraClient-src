package dev.quelchi.aurora.glass.hud;

import dev.quelchi.aurora.glass.GlassKit;
import dev.quelchi.aurora.glass.hud.GlassHudElement;
import dev.quelchi.aurora.glass.hud.HudLayoutStore;
import dev.quelchi.aurora.glass.hud.HudRegistry;
import dev.quelchi.aurora.glass.render.GlassRenderer;
import dev.quelchi.aurora.glass.ui.UiBounds;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;

public final class HudEditorScreen
extends Screen {
    private final HudRegistry registry;
    private final HudLayoutStore layouts;
    private GlassHudElement selected;
    private int dragStartX;
    private int dragStartY;
    private int startOffsetX;
    private int startOffsetY;
    private int startWidth;
    private int startHeight;
    private boolean resizing;
    private int grid;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    public HudEditorScreen(HudRegistry hudRegistry, HudLayoutStore hudLayoutStore) {
        block7: {
            block6: {
                String[] stringArray = a;
                super((Text)Text.method_43470((String)stringArray[0]));
                int n = GlassHudElement.a;
                this.grid = HudEditorScreen.a(31426, 4103915245869778911L);
                if (hudRegistry == null || hudLayoutStore == null) break block6;
                this.registry = hudRegistry;
                this.layouts = hudLayoutStore;
                if (n == 0) break block7;
            }
            throw new IllegalArgumentException(a[1]);
        }
    }

    public GlassHudElement selected() {
        return this.selected;
    }

    public HudEditorScreen grid(int n) {
        this.grid = Math.max(1, n);
        return this;
    }

    public void method_25420(DrawContext drawContext, int n, int n2, float f) {
        drawContext.method_25294(0, 0, this.field_22789, this.field_22790, HudEditorScreen.a(23187, 7595286970366650250L));
    }

    public void method_25394(DrawContext drawContext, int n, int n2, float f) {
        block11: {
            block15: {
                HudEditorScreen hudEditorScreen;
                block14: {
                    GlassRenderer glassRenderer = this.registry.renderer();
                    int n3 = GlassHudElement.a;
                    glassRenderer.beginFrame(false, n, n2);
                    for (GlassHudElement glassHudElement : this.registry.elements()) {
                        block13: {
                            UiBounds uiBounds;
                            block12: {
                                glassHudElement.render(drawContext, glassRenderer, GlassKit.theme(), n, n2, f);
                                if (n3 != 0) break block11;
                                uiBounds = glassHudElement.placement().bounds(this.field_22789, this.field_22790, glassHudElement.width(), glassHudElement.height());
                                int n4 = glassHudElement == this.selected ? HudEditorScreen.a(19448, 809242475497682662L) : HudEditorScreen.a(13696, 5745702180556474527L);
                                int n5 = n4;
                                if (n3 != 0) break block12;
                                this.outline(drawContext, uiBounds, n5);
                                if (glassHudElement != this.selected) break block13;
                                drawContext.method_25294(uiBounds.right() - 4, uiBounds.bottom() - 4, uiBounds.right() + 1, uiBounds.bottom() + 1, HudEditorScreen.a(1329, 2960908558744205355L));
                            }
                            drawContext.method_51433(this.field_22793, glassHudElement.id().toString(), uiBounds.x(), Math.max(2, uiBounds.y() - 9 - 2), -1, true);
                        }
                        if (n3 == 0) continue;
                    }
                    hudEditorScreen = this;
                    if (n3 != 0) break block14;
                    if (hudEditorScreen.selected == null) break block15;
                    hudEditorScreen = this;
                }
                int n6 = hudEditorScreen.field_22789 / 2;
                int n7 = this.field_22790 / 2;
                drawContext.method_25294(n6, 0, n6 + 1, this.field_22790, HudEditorScreen.a(23325, 6268344534110529029L));
                drawContext.method_25294(0, n7, this.field_22789, n7 + 1, HudEditorScreen.a(18516, 3680849580073514312L));
            }
            drawContext.method_51433(this.field_22793, a[2], 8, this.field_22790 - 9 - 8, HudEditorScreen.a(28420, 2297660931116880415L), true);
        }
    }

    public boolean method_25402(Click click, boolean bl) {
        HudEditorScreen hudEditorScreen;
        block20: {
            block21: {
                double d;
                block24: {
                    block23: {
                        int n;
                        int n2;
                        double d2;
                        int n3;
                        block22: {
                            block19: {
                                boolean bl2;
                                block18: {
                                    n3 = GlassHudElement.a;
                                    bl2 = click.method_74245();
                                    if (n3 != 0) break block18;
                                    if (!bl2) break block19;
                                    bl2 = super.method_25402(click, bl);
                                }
                                return bl2;
                            }
                            this.selected = this.find(click.comp_4798(), click.comp_4799());
                            hudEditorScreen = this;
                            if (n3 != 0) break block20;
                            if (hudEditorScreen.selected == null) break block21;
                            hudEditorScreen = this;
                            if (n3 != 0) break block20;
                            if (hudEditorScreen.selected.locked()) break block21;
                            UiBounds uiBounds = this.selected.placement().bounds(this.field_22789, this.field_22790, this.selected.width(), this.selected.height());
                            HudEditorScreen hudEditorScreen2 = this;
                            d2 = click.comp_4798();
                            int n = uiBounds.right();
                            n = 7;
                            if (n3 != 0) break block22;
                            if (!(d2 >= (double)(n2 - n))) break block23;
                            d2 = click.comp_4799();
                            int n = uiBounds.bottom();
                            n = 7;
                        }
                        double d = d2 - (double)(n2 - n);
                        d = d == 0.0 ? 0 : (d > 0.0 ? 1 : -1);
                        if (n3 != 0) break block24;
                        if (d < 0) break block23;
                        d = 1.0;
                        break block24;
                    }
                    d = 0.0;
                }
                hudEditorScreen2.resizing = d;
                this.dragStartX = (int)Math.round(click.comp_4798());
                this.dragStartY = (int)Math.round(click.comp_4799());
                this.startOffsetX = this.selected.placement().offsetX();
                this.startOffsetY = this.selected.placement().offsetY();
                this.startWidth = this.selected.width();
                this.startHeight = this.selected.height();
                return true;
            }
            hudEditorScreen = this;
        }
        boolean bl3 = hudEditorScreen.selected != null;
        return bl3;
    }

    
    public boolean method_25403(Click click, double d, double d2) {
        int n;
        block11: {
            HudEditorScreen hudEditorScreen;
            block9: {
                block10: {
                    block14: {
                        HudEditorScreen hudEditorScreen2;
                        int n2;
                        int n3;
                        block12: {
                            block13: {
                                int n4 = GlassHudElement.a;
                                hudEditorScreen = this;
                                if (n4 != 0) break block9;
                                if (hudEditorScreen.selected == null) break block10;
                                hudEditorScreen = this;
                                if (n4 != 0) break block9;
                                if (hudEditorScreen.selected.locked()) break block10;
                                n = click.method_74245();
                                if (n4 != 0) break block11;
                                if (n != 0) break block10;
                                n3 = (int)Math.round(click.comp_4798());
                                n2 = (int)Math.round(click.comp_4799());
                                hudEditorScreen2 = this;
                                if (n4 != 0) break block12;
                                if (!hudEditorScreen2.resizing) break block13;
                                float f = this.selected.placement().scale();
                                int n5 = this.startWidth + Math.round((float)(n3 - this.dragStartX) / f);
                                int n6 = this.startHeight + Math.round((float)(n2 - this.dragStartY) / f);
                                this.selected.size(Math.max(8, this.snap(n5)), Math.max(8, this.snap(n6)));
                                if (n4 == 0) break block14;
                            }
                            hudEditorScreen2 = this;
                        }
                        hudEditorScreen2.selected.placement().offset(this.snap(this.startOffsetX + n3 - this.dragStartX), this.snap(this.startOffsetY + n2 - this.dragStartY));
                    }
                    return true;
                }
                hudEditorScreen = this;
            }
            n = super.method_25403(click, d, d2);
        }
        return n != 0;
    }

    public boolean method_25406(Click click) {
        int n;
        block10: {
            HudEditorScreen hudEditorScreen;
            block8: {
                block9: {
                    int n2 = GlassHudElement.a;
                    hudEditorScreen = this;
                    if (n2 != 0) break block8;
                    if (hudEditorScreen.selected == null) break block9;
                    n = click.method_74245();
                    if (n2 != 0) break block10;
                    if (n != 0) break block9;
                    this.resizing = false;
                    this.layouts.capture(this.selected);
                    this.layouts.save();
                    return true;
                }
                hudEditorScreen = this;
            }
            n = super.method_25406(click);
        }
        return n != 0;
    }

    public boolean method_25401(double d, double d2, double d3, double d4) {
        HudEditorScreen hudEditorScreen;
        block6: {
            block7: {
                int n = GlassHudElement.a;
                hudEditorScreen = this;
                if (n != 0) break block6;
                if (hudEditorScreen.selected == null) break block7;
                hudEditorScreen = this;
                if (n != 0) break block6;
                if (hudEditorScreen.selected.locked()) break block7;
                float f = this.selected.placement().scale();
                this.selected.placement().scale(f + (float)d4 * 0.05f);
                this.layouts.capture(this.selected);
                this.layouts.save();
                return true;
            }
            hudEditorScreen = this;
        }
        return super.method_25401(d, d2, d3, d4);
    }

    public boolean method_25404(KeyInput keyInput) {
        int n;
        block24: {
            block25: {
                boolean bl;
                GlassHudElement glassHudElement;
                block26: {
                    block27: {
                        KeyInput keyInput2;
                        int n2;
                        block22: {
                            block23: {
                                block21: {
                                    HudEditorScreen hudEditorScreen;
                                    block20: {
                                        block19: {
                                            int n3;
                                            block18: {
                                                n2 = GlassHudElement.a;
                                                n3 = keyInput.comp_4795();
                                                if (n2 != 0) break block18;
                                                if (n3 != 256) break block19;
                                                this.method_25419();
                                                n3 = 1;
                                            }
                                            return n3 != 0;
                                        }
                                        hudEditorScreen = this;
                                        if (n2 != 0) break block20;
                                        if (hudEditorScreen.selected != null) break block21;
                                        hudEditorScreen = this;
                                    }
                                    return super.method_25404(keyInput);
                                }
                                keyInput2 = keyInput;
                                if (n2 != 0) break block22;
                                if (keyInput2.comp_4795() != 82) break block23;
                                this.layouts.reset(this.selected.id());
                                this.selected.placement().reset();
                                this.layouts.capture(this.selected);
                                this.layouts.save();
                                return true;
                            }
                            keyInput2 = keyInput;
                        }
                        n = keyInput2.comp_4795();
                        if (n2 != 0) break block24;
                        if (n != 76) break block25;
                        glassHudElement = this.selected;
                        bl = this.selected.locked();
                        if (n2 != 0) break block26;
                        if (bl) break block27;
                        bl = true;
                        break block26;
                    }
                    bl = false;
                }
                glassHudElement.locked(bl);
                this.layouts.capture(this.selected);
                this.layouts.save();
                return true;
            }
            n = super.method_25404(keyInput) ? 1 : 0;
        }
        return n != 0;
    }

    private GlassHudElement find(double d, double d2) {
        int n = this.registry.elements().size() - 1;
        int n2 = GlassHudElement.a;
        while (n >= 0) {
            block7: {
                block8: {
                    GlassHudElement glassHudElement = this.registry.elements().get(n);
                    if (n2 != 0) break block7;
                    if (!glassHudElement.visible()) break block8;
                    UiBounds uiBounds = glassHudElement.placement().bounds(this.field_22789, this.field_22790, glassHudElement.width(), glassHudElement.height());
                    if (n2 != 0) break block7;
                    if (!uiBounds.contains(d, d2)) break block8;
                    return glassHudElement;
                }
                --n;
            }
            if (n2 == 0) continue;
        }
        return null;
    }

    private int snap(int n) {
        return Math.round((float)n / (float)this.grid) * this.grid;
    }

    private void outline(DrawContext drawContext, UiBounds uiBounds, int n) {
        drawContext.method_25294(uiBounds.x(), uiBounds.y(), uiBounds.right(), uiBounds.y() + 1, n);
        drawContext.method_25294(uiBounds.x(), uiBounds.bottom() - 1, uiBounds.right(), uiBounds.bottom(), n);
        drawContext.method_25294(uiBounds.x(), uiBounds.y(), uiBounds.x() + 1, uiBounds.bottom(), n);
        drawContext.method_25294(uiBounds.right() - 1, uiBounds.y(), uiBounds.right(), uiBounds.bottom(), n);
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var13 = new String[3];
                    var11_1 = 0;
                    var10_2 = "%gw)\"rK\u0019]A\n\u0005GW)\u0002rK\u0019]AB\t@RnG{M\u001bW\u0013)\u001b6\u0002\u000e]Ag\u0002d\u0002\u001fW@`\u001ds\u0002MN\u0013)\u0010~G\b^\u0013z\u0004wN\b\u0012\u0013uG6PM@Vz\u0002b\u0002MN\u0013)\u000b6N\u0002QX";
                    var12_3 = "%gw)\"rK\u0019]A\n\u0005GW)\u0002rK\u0019]AB\t@RnG{M\u001bW\u0013)\u001b6\u0002\u000e]Ag\u0002d\u0002\u001fW@`\u001ds\u0002MN\u0013)\u0010~G\b^\u0013z\u0004wN\b\u0012\u0013uG6PM@Vz\u0002b\u0002MN\u0013)\u000b6N\u0002QX".length();
                    var9_4 = 10;
                    var8_5 = -1;
lbl7:

                    while (true) {
                        continue;
                        break;
                    }
lbl9:

                    while (true) {
                        var13[var11_1++] = new String(v0).intern();
                        if ((var8_5 += var9_4) < var12_3) {
                            var9_4 = var10_2.charAt(var8_5);
                            continue;
                        }
                        break block19;
                        break;
                    }
                    v1 = ++var8_5;
                    v2 = var10_2.substring(v1, v1 + var9_4).toCharArray();
                    v0 = v2;
                    v3 = v2.length;
                    var14_6 = 0;
                    do {
                        v0 = v0;
                        v4 = var14_6;
                        v5 = v0[v4];
                        switch (var14_6 % 7) {
                            case 0: {
                                v6 = 109;
                                break;
                            }
                            case 1: {
                                v6 = 50;
                                break;
                            }
                            case 2: {
                                v6 = 51;
                                break;
                            }
                            case 3: {
                                v6 = 9;
                                break;
                            }
                            case 4: {
                                v6 = 103;
                                break;
                            }
                            case 5: {
                                v6 = 22;
                                break;
                            }
                            default: {
                                v6 = 34;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_6;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_6);
                    while (true)
                }
                HudEditorScreen.a = var13;
                var0_7 = 7552519623291243465L;
                var6_8 = new long[8];
                var3_9 = 0;
                var4_10 = "}\u0090R\u0016nQ\u001b\u001d\u00d6\u00b6\u00e6\u00b8\u00a8\r&){x\u00fd\u00f2\u00d8f\u00c53-\u009an\u00a8G\u00aalCk\u00f4\u0098\u00fa\u0097?\u00d5\u00d0D\u001b\u001fS`\u00cf|\u00a9";
                var5_11 = "}\u0090R\u0016nQ\u001b\u001d\u00d6\u00b6\u00e6\u00b8\u00a8\r&){x\u00fd\u00f2\u00d8f\u00c53-\u009an\u00a8G\u00aalCk\u00f4\u0098\u00fa\u0097?\u00d5\u00d0D\u001b\u001fS`\u00cf|\u00a9".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v7 = var6_8;
                    v8 = var3_9++;
                    v9 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v10 = -1;
                    break block20;
                    break;
                }
lbl68:

                while (true) {
                    v7[v8] = v11;
                    if (var2_12 < var5_11) continue;
                    var4_10 = ">(6F\u00b6\u00e0\u00d6~\u00b7Ys\u00b4+\u0011\u00cc\u0012";
                    var5_11 = ">(6F\u00b6\u00e0\u00d6~\u00b7Ys\u00b4+\u0011\u00cc\u0012".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v7 = var6_8;
                        v8 = var3_9++;
                        v9 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v10 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl81:

                while (true) {
                    v7[v8] = v11;
                    if (var2_12 < var5_11) continue;
                    break block21;
                    break;
                }
            }
            v11 = v9 ^ var0_7;
            switch (v10) {
                default: {
                    continue;
                }
                case 0:
lbl92:

                continue;
            }
        }
        HudEditorScreen.b = var6_8;
        HudEditorScreen.c = new Integer[8];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3D1A;
        if (c[n2] == null) {
            HudEditorScreen.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
