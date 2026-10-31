package dev.quelchi.aurora.gui.lq;

import dev.quelchi.aurora.glass.render.GlassRenderer;
import dev.quelchi.aurora.glass.style.ComponentStyle;
import dev.quelchi.aurora.glass.style.GlassPresets;
import dev.quelchi.aurora.glass.style.GlassStyle;
import dev.quelchi.aurora.glass.style.LiquidGlassTheme;
import dev.quelchi.aurora.glass.ui.Insets;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

public final class AuroraGlass {
    public static final GlassStyle WINDOW;
    public static final GlassStyle WELL;
    public static final GlassStyle CONTROL;
    public static final GlassStyle SELECTED;
    public static final GlassStyle BUTTON;
    private static final GlassRenderer SHARED;
    public static final GlassStyle MENU;
    public static final GlassStyle TOOLTIP;
    private static final int HOVER;
    private static final int PRESSED;
    private static final int DISABLED;
    public static final LiquidGlassTheme THEME;

    private AuroraGlass() {
    }

    public static void init() {
        ScreenEvents.BEFORE_INIT.register(AuroraGlass::lambda$init$1);
    }

    private static GlassRenderer renderer() {
        return SHARED;
    }

    private static GlassStyle clean(GlassStyle glassStyle) {
        return new GlassStyle(glassStyle.tint(), glassStyle.opacity(), glassStyle.blur(), glassStyle.refraction(), glassStyle.saturation(), glassStyle.contrast(), 0.0f, glassStyle.borderColor(), glassStyle.radius(), glassStyle.shadowColor(), glassStyle.shadowSize(), glassStyle.innerGlow(), glassStyle.outerGlow(), glassStyle.highlight(), glassStyle.mouseReactive());
    }

    private static void lambda$init$1(MinecraftClient minecraftClient, Screen screen, int n, int n2) {
        ScreenEvents.beforeRender((Screen)screen).register((arg_0, arg_1, arg_2, arg_3, arg_4) -> AuroraGlass.lambda$init$0(minecraftClient, arg_0, arg_1, arg_2, arg_3, arg_4));
    }

    private static void lambda$init$0(MinecraftClient minecraftClient, Screen screen, DrawContext drawContext, int n, int n2, float f) {
        GlassRenderer glassRenderer = SHARED;
        boolean bl = minecraftClient.field_1687 != null;
        glassRenderer.beginFrame(bl, n, n2);
    }

    
    static {
        block8: {
            block7: {
                var1 = 1376165727450246042L;
                var0_1 = new long[21];
                var4_2 = 0;
                var5_3 = " U+?>B\u00d4e\u00c1\u00d1\u0088\u00ba\u0082\u00ba#\u0094\t\u00a4t\u0004\u00f6\u00b2:\u0082\u00d1\u00f0\u001b\"\u00e7}\u00edH\u0091h7\u0092\u00e7\u00d3A\u001c\u00b319\u00c1\u00e7L\u00d8m\u0019G\u00eaf4{\u00f9~L\u00bfT\u0011`\u00b0$\u008d\u0000M\u00e0\u00e9({\u00f9~\u00f1\u009b\u00a0\u0080\u00e7\u00d3A\u001c;\u0096\u001b\u00bd\u00e7\u00d3A\u001c\u0086#=\u00c0.B\u00d4e\u00ba\u001e,R4{\u00f9~(\u00b4\u00e5\b+B\u00d4e\u00f4\u00b9u\u00b5~\u00bd+\u009a\u00e1H\u00c694{\u00f9~\u0097^\u008a\u0012+B\u00d4eJ\u00a6\u0000+\u0094\u00b0$\u008d@\u0014\u00d0\u00c7\u00e7L\u00d8m";
                var6_4 = " U+?>B\u00d4e\u00c1\u00d1\u0088\u00ba\u0082\u00ba#\u0094\t\u00a4t\u0004\u00f6\u00b2:\u0082\u00d1\u00f0\u001b\"\u00e7}\u00edH\u0091h7\u0092\u00e7\u00d3A\u001c\u00b319\u00c1\u00e7L\u00d8m\u0019G\u00eaf4{\u00f9~L\u00bfT\u0011`\u00b0$\u008d\u0000M\u00e0\u00e9({\u00f9~\u00f1\u009b\u00a0\u0080\u00e7\u00d3A\u001c;\u0096\u001b\u00bd\u00e7\u00d3A\u001c\u0086#=\u00c0.B\u00d4e\u00ba\u001e,R4{\u00f9~(\u00b4\u00e5\b+B\u00d4e\u00f4\u00b9u\u00b5~\u00bd+\u009a\u00e1H\u00c694{\u00f9~\u0097^\u008a\u0012+B\u00d4eJ\u00a6\u0000+\u0094\u00b0$\u008d@\u0014\u00d0\u00c7\u00e7L\u00d8m".length();
                var3_5 = 0;
                while (true) {
                    var7_6 = var5_3.substring(var3_5, var3_5 += 8).getBytes("ISO-8859-1");
                    v0 = var0_1;
                    v1 = var4_2++;
                    v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                    v3 = -1;
                    break block7;
                    break;
                }
lbl14:

                while (true) {
                    v0[v1] = v4;
                    if (var3_5 < var6_4) continue;
                    var5_3 = "]\u00cc\u0092\u00e7\u00e7}\u00edH3#\u0090=+B\u00d4e";
                    var6_4 = "]\u00cc\u0092\u00e7\u00e7}\u00edH3#\u0090=+B\u00d4e".length();
                    var3_5 = 0;
                    while (true) {
                        var7_6 = var5_3.substring(var3_5, var3_5 += 8).getBytes("ISO-8859-1");
                        v0 = var0_1;
                        v1 = var4_2++;
                        v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                        v3 = 0;
                        break block7;
                        break;
                    }
                    break;
                }
lbl27:

                while (true) {
                    v0[v1] = v4;
                    if (var3_5 < var6_4) continue;
                    break block8;
                    break;
                }
            }
            v4 = v2 ^ var1;
            switch (v3) {
                default: {
                    continue;
                }
                case 0:
lbl38:

                continue;
            }
        }
        AuroraGlass.HOVER = (int)var0_1[6];
        AuroraGlass.DISABLED = (int)var0_1[10];
        AuroraGlass.PRESSED = (int)var0_1[16];
        AuroraGlass.WINDOW = AuroraGlass.clean(GlassPresets.DARK.tint((int)var0_1[7]).blur(0.9f).refraction(0.55f).highlight(0.7f).radius(14));
        AuroraGlass.WELL = AuroraGlass.clean(GlassPresets.DARK.tint((int)var0_1[1]).blur(0.6f).refraction(0.15f).highlight(0.35f).radius(14));
        AuroraGlass.CONTROL = GlassPresets.SUBTLE.tint((int)var0_1[8]).blur(0.0f).refraction(0.0f).highlight(0.3f).radius(8);
        AuroraGlass.SELECTED = AuroraGlass.clean(GlassPresets.LIGHT.tint((int)var0_1[0]).blur(0.35f).refraction(0.35f).highlight(0.6f).radius(8));
        AuroraGlass.BUTTON = AuroraGlass.clean(GlassPresets.LIGHT.tint((int)var0_1[11]).blur(0.35f).refraction(0.3f).highlight(0.65f).radius(8));
        AuroraGlass.SHARED = new GlassRenderer();
        AuroraGlass.MENU = AuroraGlass.clean(GlassPresets.DARK.tint((int)var0_1[17]).blur(0.9f).refraction(0.4f).highlight(0.55f).radius(8));
        AuroraGlass.TOOLTIP = GlassPresets.DARK.tint((int)var0_1[2]).radius(8);
        AuroraGlass.THEME = new LiquidGlassTheme(new ComponentStyle(AuroraGlass.WINDOW, (int)var0_1[5], (int)var0_1[19], -1, (int)var0_1[15], (int)var0_1[20], (int)var0_1[9], Insets.all(12), 8, 140), new ComponentStyle(AuroraGlass.CONTROL, (int)var0_1[18], (int)var0_1[3], -1, (int)var0_1[12], (int)var0_1[13], (int)var0_1[4], Insets.xy(8, 5), 6, 120), new ComponentStyle(AuroraGlass.TOOLTIP, (int)var0_1[18], (int)var0_1[3], -1, (int)var0_1[12], (int)var0_1[13], (int)var0_1[4], Insets.xy(6, 4), 4, 100), (int)var0_1[14]);
    }
}
