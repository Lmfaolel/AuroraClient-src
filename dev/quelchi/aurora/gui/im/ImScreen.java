package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.Mc;
import dev.quelchi.aurora.gui.im.Menu;
import imgui.ImGui;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.input.AbstractInput;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.texture.CubemapTexture;
import net.minecraft.client.texture.ReloadableTexture;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;

public abstract class ImScreen
extends Screen {
    private static final Identifier PANORAMA;
    private static RotatingCubeMapRenderer panorama;
    protected final List<PressableWidget> added = new ArrayList<PressableWidget>();
    protected final List<PressableWidget> icons = new ArrayList<PressableWidget>();
    protected final List<PressableWidget> hidden = new ArrayList<PressableWidget>();
    private final StringBuilder typed = new StringBuilder();
    private long typedAt;
    private boolean kept;
    public static boolean a;
    private static final long[] e;
    private static final Long[] f;

    protected ImScreen(String string) {
        super((Text)Text.method_43470((String)string));
    }

    protected abstract void draw();

    public void method_25420(DrawContext drawContext, int n, int n2, float f) {
        ImScreen imScreen;
        block4: {
            block5: {
                boolean bl = a;
                imScreen = this;
                if (bl) break block4;
                if (imScreen.field_22787.field_1687 == null) break block5;
                super.method_25420(drawContext, n, n2, f);
                return;
            }
            ImScreen.panorama(drawContext, this.field_22789, this.field_22790);
            imScreen = this;
        }
        imScreen.method_57734(drawContext);
    }

    public static void panorama(DrawContext drawContext, int n, int n2) {
        block4: {
            block5: {
                boolean bl = a;
                MinecraftClient minecraftClient = MinecraftClient.method_1551();
                if (bl) break block4;
                if (panorama != null) break block5;
                minecraftClient.method_1531().method_65876(PANORAMA, (ReloadableTexture)new CubemapTexture(PANORAMA));
                panorama = new RotatingCubeMapRenderer(new CubeMapRenderer(PANORAMA));
            }
            panorama.method_3317(drawContext, n, n2, true);
        }
    }

    protected boolean vanillaSize() {
        return false;
    }

    
    protected void host(Screen var1_1, Set<String> var2_2) {
        this.added.clear();
        var3_3 = ImScreen.a;
        this.icons.clear();
        this.hidden.clear();
        var1_1.method_25423(this.field_22789, this.field_22790);
        for (Element var5_5 : var1_1.method_25396()) {
            block31: {
                block29: {
                    block30: {
                        block27: {
                            block28: {
                                block25: {
                                    block24: {
                                        v0 = var5_5;
                                        if (var3_3) break block24;
                                        if (!(v0 instanceof PressableWidget)) continue;
                                        v0 = var5_5;
                                    }
                                    var6_6 = (PressableWidget)v0;
                                    v2 = var6_6;
                                    if (var3_3) break block25;
                                    if (!v2.field_22764) {
                                        continue;
                                    }
                                    v2 = var6_6;
                                }
                                var8_8 = v2.method_25369().method_10851();
                                v5 = var8_8 instanceof TranslatableTextContent;
                                if (var3_3) break block27;
                                if (!v5) break block28;
                                var7_7 = (TranslatableTextContent)var8_8;
                                v5 = var2_2.contains(var7_7.method_11022());
                                if (var3_3) break block27;
                                if (!v5) break block28;
                                this.hidden.add(var6_6);
                                if (!var3_3) continue;
                            }
                            v9 = var6_6;
                            if (var3_3) break block29;
                            v5 = Mc.self((ClickableWidget)v9);
                        }
                        if (v5) break block30;
                        v11 = Mc.latin(var6_6.method_25369().getString());
                        if (var3_3) break block31;
                        if (!v11) {
                        }
                    }
                    v9 = var6_6;
                }
                v9.method_48229(6, 6 + 24 * this.icons.size());
                this.icons.add(var6_6);
                if (!var3_3) break block31;
lbl84:

                v11 = this.added.add(var6_6);
            }
            if (!var3_3) continue;
        }
    }

    protected void icons(DrawContext drawContext, int n, int n2, float f) {
        Iterator<PressableWidget> iterator = this.icons.iterator();
        boolean bl = a;
        while (iterator.hasNext()) {
            PressableWidget pressableWidget = iterator.next();
            pressableWidget.method_25394(drawContext, n, n2, f);
            if (!bl) continue;
        }
    }

    protected void press(PressableWidget pressableWidget) {
        ClickableWidget.method_62888((SoundManager)this.field_22787.method_1483());
        pressableWidget.method_25306((AbstractInput)Mc.click(0.0));
    }

    public void method_25419() {
        if (!Menu.leave()) {
            super.method_25419();
        }
    }

    public boolean method_25421() {
        return false;
    }

    public boolean method_25402(Click click, boolean bl) {
        block5: {
            Iterator<PressableWidget> iterator = this.icons.iterator();
            boolean bl2 = a;
            while (iterator.hasNext()) {
                block7: {
                    boolean bl3;
                    block6: {
                        PressableWidget pressableWidget = iterator.next();
                        if (bl2) break block5;
                        bl3 = pressableWidget.method_25402(click, bl);
                        if (bl2) break block6;
                        if (!bl3) break block7;
                        bl3 = true;
                    }
                    return bl3;
                }
                if (!bl2) continue;
            }
            Im.mouse(click.method_74245(), true);
        }
        return true;
    }

    public boolean method_25406(Click click) {
        Im.mouse(click.method_74245(), false);
        return true;
    }

    public boolean method_25401(double d, double d2, double d3, double d4) {
        Im.scroll(d3, d4);
        return true;
    }

    public boolean shutsOnMenuKey() {
        return false;
    }

    protected boolean key(int n) {
        return false;
    }

    protected boolean typedAhead() {
        int n;
        block8: {
            block7: {
                boolean bl;
                block6: {
                    bl = a;
                    n = this.typed.length();
                    if (bl) break block6;
                    if (n <= 0) break block7;
                    long l = Im.frames() - this.typedAt - ImScreen.b(13493, 2207310700625477981L);
                    n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                }
                if (bl) break block8;
                if (n > 0) break block7;
                n = 1;
                break block8;
            }
            n = 0;
        }
        return n != 0;
    }

    
    protected void typeToSearch() {
        int n;
        block14: {
            block15: {
                block13: {
                    boolean bl;
                    block12: {
                        bl = a;
                        n = this.typed.length();
                        if (!bl) {
                            if (n == 0) {
                                return;
                            }
                            long l = Im.frames() - this.typedAt - ImScreen.b(24460, 2945199561550277221L);
                            n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                        }
                        if (bl) break block12;
                        if (n > 0) break block13;
                        n = ImGui.isPopupOpen((String)"", (int)3072) ? 1 : 0;
                    }
                    if (bl) break block14;
                    if (n == 0) break block15;
                }
                this.typed.setLength(0);
                return;
            }
            n = Im.typing() ? 1 : 0;
        }
        if (n == 0) {
            ImGui.setKeyboardFocusHere();
            return;
        }
        this.typed.codePoints().forEach(Im::character);
        this.typed.setLength(0);
    }

    public boolean method_25404(KeyInput keyInput) {
        int n;
        block14: {
            block15: {
                boolean bl;
                block12: {
                    block13: {
                        bl = a;
                        this.kept = this.key(keyInput.comp_4795());
                        if (bl) break block12;
                        if (!this.kept) break block13;
                        return true;
                    }
                    n = keyInput.comp_4795();
                    if (bl) break block14;
                    Im.key(n, keyInput.comp_4796(), 1, keyInput.comp_4797());
                }
                if (!Im.typing()) break block15;
                n = keyInput.comp_4795();
                if (bl) break block14;
                if (n != 256) break block15;
                return true;
            }
            n = super.method_25404(keyInput) ? 1 : 0;
        }
        return n != 0;
    }

    public boolean method_16803(KeyInput keyInput) {
        Im.key(keyInput.comp_4795(), keyInput.comp_4796(), 0, keyInput.comp_4797());
        return true;
    }

    public boolean method_25400(CharInput charInput) {
        int n;
        block17: {
            block16: {
                boolean bl;
                block14: {
                    block15: {
                        bl = a;
                        n = Im.typing();
                        if (bl) break block14;
                        if (n == 0) break block15;
                        Im.character(charInput.comp_4793());
                        if (!bl) break block16;
                    }
                    n = this.kept;
                }
                if (bl) break block17;
                if (n != 0) break block16;
                n = this.typed.length();
                if (bl) break block17;
                if (n >= 64) break block16;
                this.typed.appendCodePoint(charInput.comp_4793());
                this.typedAt = Im.frames();
            }
            n = 1;
        }
        return n != 0;
    }

    
    static {
        block16: {
            block15: {
                block14: {
                    var8 = new String[2];
                    var12_1 = 0;
                    var11_2 = "\fI/m\u0019'w\u000b\u00030l\u0005zf\u0011X;|C7s\u001bG0k\u0003 |\u001c\u0003'x\u0002:`\u0019A6\u0006\u0019Y%v\u001e4";
                    var13_3 = "\fI/m\u0019'w\u000b\u00030l\u0005zf\u0011X;|C7s\u001bG0k\u0003 |\u001c\u0003'x\u0002:`\u0019A6\u0006\u0019Y%v\u001e4".length();
                    var10_4 = 38;
                    var9_5 = -1;
lbl7:

                    while (true) {
                        continue;
                        break;
                    }
lbl9:

                    while (true) {
                        var8[var12_1++] = new String(v0).intern();
                        if ((var9_5 += var10_4) < var13_3) {
                            var10_4 = var11_2.charAt(var9_5);
                            continue;
                        }
                        break block14;
                        break;
                    }
                    v1 = ++var9_5;
                    v2 = var11_2.substring(v1, v1 + var10_4).toCharArray();
                    v0 = v2;
                    v3 = v2.length;
                    var14_6 = 0;
                    do {
                        v0 = v0;
                        v4 = var14_6;
                        v5 = v0[v4];
                        switch (var14_6 % 7) {
                            case 0: {
                                v6 = 120;
                                break;
                            }
                            case 1: {
                                v6 = 44;
                                break;
                            }
                            case 2: {
                                v6 = 87;
                                break;
                            }
                            case 3: {
                                v6 = 25;
                                break;
                            }
                            case 4: {
                                v6 = 108;
                                break;
                            }
                            case 5: {
                                v6 = 85;
                                break;
                            }
                            default: {
                                v6 = 18;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_6;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_6);
                    while (true)
                }
                var0_7 = 61024564683809983L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "(\u0007\u00be\u00e16Z\u000e\u00d0\u001ey<\u00e7\u0087>e\u00e8";
                var5_11 = "(\u0007\u00be\u00e16Z\u000e\u00d0\u001ey<\u00e7\u0087>e\u00e8".length();
                var2_12 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl62:

                while (true) {
                    var6_8[v7] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block16;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v7 = var3_9++;
            while (true)
        }
        ImScreen.e = var6_8;
        ImScreen.f = new Long[2];
        ImScreen.PANORAMA = Identifier.method_60655((String)var8[1], (String)var8[0]);
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x75E9) & Short.MAX_VALUE;
        if (f[n2] == null) {
            ImScreen.f[n2] = e[n2] ^ l;
        }
        return f[n2];
    }
}
