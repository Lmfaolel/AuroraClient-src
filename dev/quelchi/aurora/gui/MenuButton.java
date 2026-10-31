package dev.quelchi.aurora.gui;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.gui.Anim;
import dev.quelchi.aurora.gui.AuroraSplash;
import dev.quelchi.aurora.gui.HudScale;
import dev.quelchi.aurora.gui.MenuButton$Ease;
import dev.quelchi.aurora.gui.MenuButton$Pin;
import dev.quelchi.aurora.gui.MenuButton$Px;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.Theme;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.client.gui.screen.ProgressScreen;
import net.minecraft.client.gui.screen.ReconfiguringScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.StatsScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.screen.world.LevelLoadingScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TabButtonWidget;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.text.MutableText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;
import org.joml.Matrix3x2fStack;

public final class MenuButton {
    private static final int V_FILL;
    private static final int V_HOVER;
    private static final int V_RIM;
    private static final int V_WELL;
    private static final int V_WELL_RIM;
    private static final int V_SUB;
    private static final int V_MUTE;
    private static final int V_OFF;
    private static final int GO_TOP;
    private static final int GO_FOOT;
    private static final int ACCENT;
    private static final Set<String> GO;
    static final int ROW;
    static final int NAME;
    static final int ICON;
    private static final Map<ClickableWidget, MenuButton$Ease> EASE;
    private static final Map<ClickableWidget, MenuButton$Pin> PINS;
    private static final Map<Integer, Identifier> SHEETS;
    private static final Map<Screen, Boolean> MENUS;
    private static Screen seen;
    private static boolean seenMenu;
    private static final Map<Integer, Identifier> TICKS;
    private static final Map<Integer, Identifier> PADLOCKS;
    private static final Map<ClickableWidget, Float> ORDER;
    private static final Map<Screen, Long> OPENED;
    private static final long BEAT;
    private static final long POP;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private MenuButton() {
    }

    static MenuButton$Pin begin(DrawContext drawContext, ClickableWidget clickableWidget) {
        MenuButton$Pin pin = PINS.get(clickableWidget);
        if (pin == null) {
            return null;
        }
        float f = HudScale.menuFactor();
        drawContext.method_51448().pushMatrix();
        drawContext.method_51448().scale(f, f);
        return pin;
    }

    static void end(DrawContext drawContext) {
        drawContext.method_51448().popMatrix();
    }

    public static void slab(DrawContext drawContext, ClickableWidget clickableWidget, int n, int n2, int n3, int n4, boolean bl) {
        MenuButton$Pin menuButton$Pin;
        block28: {
            DrawContext drawContext2;
            block34: {
                boolean bl2;
                ClickableWidget clickableWidget2;
                int n5;
                float f;
                int n6;
                block33: {
                    block32: {
                        block31: {
                            int n7;
                            block30: {
                                block29: {
                                    block27: {
                                        block26: {
                                            block25: {
                                                n6 = PreviewScene.a;
                                                menuButton$Pin = MenuButton.begin(drawContext, clickableWidget);
                                                if (n6 != 0) break block25;
                                                if (menuButton$Pin == null) break block26;
                                                n = menuButton$Pin.x();
                                                n2 = menuButton$Pin.y();
                                                n3 = menuButton$Pin.w();
                                            }
                                            n4 = menuButton$Pin.h();
                                        }
                                        f = MenuButton.entrance(clickableWidget);
                                        n7 = n3;
                                        if (n6 != 0) break block27;
                                        if (n7 <= 0) break block28;
                                        n7 = n4;
                                    }
                                    if (n6 != 0) break block29;
                                    if (n7 <= 0) break block28;
                                    float f2 = f - 0.0f;
                                    n7 = f2 == 0.0f ? (int)0 : (f2 > 0.0f ? (int)1 : -1);
                                }
                                if (n6 != 0) break block30;
                                if (n7 <= 0) break block28;
                                n7 = MenuButton.popIn(drawContext, f, n, n2, n3, n4, 14.0f) ? 1 : 0;
                            }
                            n5 = n7;
                            clickableWidget2 = clickableWidget;
                            bl2 = bl;
                            if (n6 != 0) break block31;
                            if (!bl2) break block32;
                            bl2 = clickableWidget.field_22763;
                        }
                        if (n6 != 0) break block33;
                        if (!bl2) break block32;
                        bl2 = true;
                        break block33;
                    }
                    bl2 = false;
                }
                float f3 = MenuButton.ease(clickableWidget2, bl2);
                float f4 = clickableWidget.method_75798() * Math.min(1.0f, f * 2.5f);
                drawContext2 = drawContext;
                if (n6 != 0) break block34;
                MenuButton.plain(drawContext2, clickableWidget, n, n2, n3, n4, f3, f4);
                if (n5 == 0) break block28;
                drawContext2 = drawContext;
            }
            drawContext2.method_51448().popMatrix();
        }
        if (menuButton$Pin != null) {
            MenuButton.end(drawContext);
        }
    }

    private static MenuButton$Px px(DrawContext drawContext, float f, float f2, float f3, float f4) {
        Matrix3x2fStack matrix3x2fStack = drawContext.method_51448();
        int n = Math.max(1, MinecraftClient.method_1551().method_22683().method_4495());
        float f5 = matrix3x2fStack.m00() * (float)n;
        return new MenuButton$Px(Math.round((matrix3x2fStack.m00() * f + matrix3x2fStack.m20()) * (float)n), Math.round((matrix3x2fStack.m11() * f2 + matrix3x2fStack.m21()) * (float)n), Math.round((matrix3x2fStack.m00() * (f + f3) + matrix3x2fStack.m20()) * (float)n), Math.round((matrix3x2fStack.m11() * (f2 + f4) + matrix3x2fStack.m21()) * (float)n), f5 * 20.0f / 34.0f);
    }

    private static void pixels(DrawContext drawContext) {
        Matrix3x2fStack matrix3x2fStack = drawContext.method_51448();
        matrix3x2fStack.pushMatrix();
        matrix3x2fStack.identity();
        matrix3x2fStack.scale(1.0f / (float)Math.max(1, MinecraftClient.method_1551().method_22683().method_4495()));
    }

    private static Identifier sheet(int n, int n2) {
        return SHEETS.computeIfAbsent(n * 64 + n2, arg_0 -> MenuButton.lambda$sheet$0(n, n2, arg_0));
    }

    private static Identifier texture(String string, NativeImage nativeImage) {
        Identifier identifier = Identifier.method_60655((String)a[6], (String)string);
        NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(() -> MenuButton.lambda$texture$1(string), nativeImage);
        nativeImageBackedTexture.method_4524();
        MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)nativeImageBackedTexture);
        return identifier;
    }

    private static void fillPx(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        block24: {
            block23: {
                int n8;
                int n9;
                int n10;
                block22: {
                    block25: {
                        block20: {
                            int n11;
                            block21: {
                                block19: {
                                    n10 = PreviewScene.a;
                                    n11 = n3;
                                    if (n10 != 0) break block19;
                                    if (n11 <= n) break block20;
                                    n11 = n4;
                                }
                                if (n10 != 0) break block21;
                                if (n11 <= n2) break block20;
                                n9 = n6;
                                n8 = n7;
                                if (n10 != 0) break block22;
                                n11 = (n9 | n8) >>> 24;
                            }
                            if (n11 != 0) break block25;
                        }
                        return;
                    }
                    n9 = 0;
                    n8 = Math.min(n5, Math.min(n3 - n, n4 - n2) / 2);
                }
                int n12 = Math.max(n9, n8);
                if (n10 == 0) {
                    n5 = n12;
                    if (n5 > 0) {
                        Identifier identifier = MenuButton.sheet(n5, 0);
                        drawContext.method_25293(RenderPipelines.field_56883, identifier, n, n2, 0.0f, 0.0f, n5, n5, n5, n5, n5 * 2, n5 * 2, n6);
                        drawContext.method_25293(RenderPipelines.field_56883, identifier, n3 - n5, n2, (float)n5, 0.0f, n5, n5, n5, n5, n5 * 2, n5 * 2, n6);
                        drawContext.method_25293(RenderPipelines.field_56883, identifier, n, n4 - n5, 0.0f, (float)n5, n5, n5, n5, n5, n5 * 2, n5 * 2, n7);
                        drawContext.method_25293(RenderPipelines.field_56883, identifier, n3 - n5, n4 - n5, (float)n5, (float)n5, n5, n5, n5, n5, n5 * 2, n5 * 2, n7);
                        drawContext.method_25294(n + n5, n2, n3 - n5, n2 + n5, n6);
                        drawContext.method_25294(n + n5, n4 - n5, n3 - n5, n4, n7);
                    }
                    n12 = n6;
                }
                if (n12 != n7) break block23;
                drawContext.method_25294(n, n2 + n5, n3, n4 - n5, n6);
                if (n10 == 0) break block24;
            }
            drawContext.method_25296(n, n2 + n5, n3, n4 - n5, n6, n7);
        }
    }

    private static void rimPx(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        int n8;
        block15: {
            block16: {
                block13: {
                    int n9;
                    block14: {
                        int n10;
                        block12: {
                            n10 = PreviewScene.a;
                            n9 = n3;
                            if (n10 != 0) break block12;
                            if (n9 <= n) break block13;
                            n9 = n4;
                        }
                        if (n10 != 0) break block14;
                        if (n9 <= n2) break block13;
                        n8 = n7;
                        if (n10 != 0) break block15;
                        n9 = n8 >>> 24;
                    }
                    if (n9 != 0) break block16;
                }
                return;
            }
            n8 = n5 = Math.max(n6, Math.min(n5, Math.min(n3 - n, n4 - n2) / 2));
        }
        Identifier identifier = MenuButton.sheet(n8, n6);
        drawContext.method_25293(RenderPipelines.field_56883, identifier, n, n2, 0.0f, 0.0f, n5, n5, n5, n5, n5 * 2, n5 * 2, n7);
        drawContext.method_25293(RenderPipelines.field_56883, identifier, n3 - n5, n2, (float)n5, 0.0f, n5, n5, n5, n5, n5 * 2, n5 * 2, n7);
        drawContext.method_25293(RenderPipelines.field_56883, identifier, n, n4 - n5, 0.0f, (float)n5, n5, n5, n5, n5, n5 * 2, n5 * 2, n7);
        drawContext.method_25293(RenderPipelines.field_56883, identifier, n3 - n5, n4 - n5, (float)n5, (float)n5, n5, n5, n5, n5, n5 * 2, n5 * 2, n7);
        drawContext.method_25294(n + n5, n2, n3 - n5, n2 + n6, n7);
        drawContext.method_25294(n + n5, n4 - n6, n3 - n5, n4, n7);
        drawContext.method_25294(n, n2 + n5, n + n6, n4 - n5, n7);
        drawContext.method_25294(n3 - n6, n2 + n5, n3, n4 - n5, n7);
    }

    private static int dp(MenuButton$Px menuButton$Px, float f) {
        return Math.max(1, Math.round(f * menuButton$Px.d()));
    }

    private static void panel(DrawContext drawContext, float f, float f2, float f3, float f4, float f5, int n, int n2, int n3) {
        MenuButton$Px menuButton$Px = MenuButton.px(drawContext, f, f2, f3, f4);
        MenuButton.pixels(drawContext);
        MenuButton.fillPx(drawContext, menuButton$Px.x0(), menuButton$Px.y0(), menuButton$Px.x1(), menuButton$Px.y1(), MenuButton.dp(menuButton$Px, f5), n, n2);
        MenuButton.rimPx(drawContext, menuButton$Px.x0(), menuButton$Px.y0(), menuButton$Px.x1(), menuButton$Px.y1(), MenuButton.dp(menuButton$Px, f5), MenuButton.dp(menuButton$Px, 1.0f), n3);
        drawContext.method_51448().popMatrix();
    }

    private static boolean primary(ClickableWidget clickableWidget) {
        boolean bl;
        block13: {
            block11: {
                TextContent textContent;
                int n;
                block12: {
                    ClickableWidget clickableWidget2;
                    block10: {
                        n = PreviewScene.a;
                        clickableWidget2 = clickableWidget;
                        if (n != 0) break block10;
                        if (!(clickableWidget2 instanceof ButtonWidget)) break block11;
                        clickableWidget2 = clickableWidget;
                    }
                    TextContent textContent2 = clickableWidget2.method_25369().method_10851();
                    textContent = textContent2;
                    if (n != 0) break block12;
                    if (!(textContent instanceof TranslatableTextContent)) break block11;
                    textContent = textContent2;
                }
                TranslatableTextContent translatableTextContent = (TranslatableTextContent)textContent;
                bl = GO.contains(translatableTextContent.method_11022());
                if (n != 0) break block13;
                if (!bl) break block11;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    private static void plain(DrawContext drawContext, ClickableWidget clickableWidget, int n, int n2, int n3, int n4, float f, float f2) {
        block14: {
            int n5;
            block12: {
                block13: {
                    ClickableWidget clickableWidget2;
                    int n6;
                    block10: {
                        block11: {
                            n6 = PreviewScene.a;
                            clickableWidget2 = clickableWidget;
                            if (n6 != 0) break block10;
                            if (clickableWidget2.field_22763) break block11;
                            f2 *= 0.4f;
                        }
                        clickableWidget2 = clickableWidget;
                    }
                    n5 = MenuButton.primary(clickableWidget2);
                    if (n6 != 0) break block12;
                    if (n5 == 0) break block13;
                    n5 = clickableWidget.field_22763;
                    if (n6 != 0) break block12;
                    if (n5 != 0) break block14;
                }
                n5 = MenuButton.fade(Theme.mix(MenuButton.a(712, 5697752445480926485L), MenuButton.a(32416, 6098433237183332712L), f), f2);
            }
            int n7 = n5;
            MenuButton.panel(drawContext, n, n2, n3, n4, 8.0f, n7, n7, MenuButton.fade(MenuButton.a(16861, 662640785834884644L), f2));
            return;
        }
        MenuButton.panel(drawContext, n, n2, n3, n4, 8.0f, MenuButton.fade(Theme.mix(MenuButton.a(28568, 8412757991055134804L), -1, f * 0.12f), f2), MenuButton.fade(Theme.mix(MenuButton.a(22759, 3740712484731594545L), -1, f * 0.12f), f2), 0);
    }

    public static void track(DrawContext drawContext, ClickableWidget clickableWidget, int n, int n2, int n3, int n4, double d, boolean bl) {
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        int n10;
        DrawContext drawContext2;
        float f;
        block18: {
            int n11;
            MenuButton$Px menuButton$Px;
            block19: {
                boolean bl2;
                ClickableWidget clickableWidget2;
                int n12;
                int n13;
                int n14;
                block17: {
                    block16: {
                        block15: {
                            n14 = PreviewScene.a;
                            if (PINS.containsKey(clickableWidget)) {
                                MenuButton.slab(drawContext, clickableWidget, n, n2, n3, n4, bl);
                                return;
                            }
                            float f2 = clickableWidget.method_75798();
                            float f3 = clickableWidget.field_22763 ? 1.0f : 0.4f;
                            f = f2 * f3;
                            n13 = MenuButton.a(28221, 5867209232504099275L);
                            n12 = MenuButton.a(5345, 82443429521451810L);
                            clickableWidget2 = clickableWidget;
                            bl2 = bl;
                            if (n14 != 0) break block15;
                            if (!bl2) break block16;
                            bl2 = clickableWidget.field_22763;
                        }
                        if (n14 != 0) break block17;
                        if (!bl2) break block16;
                        bl2 = true;
                        break block17;
                    }
                    bl2 = false;
                }
                int n15 = MenuButton.fade(Theme.mix(n13, n12, MenuButton.ease(clickableWidget2, bl2)), f);
                menuButton$Px = MenuButton.px(drawContext, n, n2, n3, n4);
                n11 = MenuButton.dp(menuButton$Px, 8.0f);
                int n16 = MenuButton.px(drawContext, n + (int)(d * (double)(n3 - 8)) + 4, n2, 0.0f, 0.0f).x0();
                MenuButton.pixels(drawContext);
                drawContext2 = drawContext;
                n10 = menuButton$Px.x0();
                n9 = menuButton$Px.y0();
                n8 = menuButton$Px.x1();
                n7 = menuButton$Px.y1();
                n6 = n11;
                n5 = n15;
                if (n14 != 0) break block18;
                MenuButton.fillPx(drawContext2, n10, n9, n8, n7, n6, n5, n15);
                if (!clickableWidget.field_22763) break block19;
                drawContext.method_44379(menuButton$Px.x0(), menuButton$Px.y0(), n16, menuButton$Px.y1());
                MenuButton.fillPx(drawContext, menuButton$Px.x0(), menuButton$Px.y0(), menuButton$Px.x1(), menuButton$Px.y1(), n11, MenuButton.fade(MenuButton.a(3695, 5562135538500301240L), f), MenuButton.fade(MenuButton.a(24857, 6135807475730095846L), f));
                drawContext.method_44380();
            }
            drawContext2 = drawContext;
            n10 = menuButton$Px.x0();
            n9 = menuButton$Px.y0();
            n8 = menuButton$Px.x1();
            n7 = menuButton$Px.y1();
            n6 = n11;
            n5 = MenuButton.dp(menuButton$Px, 1.0f);
        }
        MenuButton.rimPx(drawContext2, n10, n9, n8, n7, n6, n5, MenuButton.fade(MenuButton.a(3219, 3885209558656938840L), f));
        drawContext.method_51448().popMatrix();
    }

    public static boolean menu(Screen screen) {
        Object object;
        block10: {
            block11: {
                int n;
                block8: {
                    block9: {
                        n = PreviewScene.a;
                        object = screen;
                        if (n != 0) break block8;
                        if (object != null) break block9;
                        return false;
                    }
                    object = screen;
                }
                if (n != 0) break block10;
                if (object != seen) break block11;
                return seenMenu;
            }
            seen = screen;
            object = MENUS.computeIfAbsent(screen, MenuButton::lambda$menu$2);
        }
        seenMenu = (Boolean)object;
        return seenMenu;
    }

    private static boolean vanillaMenu(Screen screen, boolean bl) {
        boolean bl2;
        block64: {
            block66: {
                block65: {
                    int n;
                    block62: {
                        block63: {
                            block60: {
                                block61: {
                                    boolean bl3;
                                    block58: {
                                        block59: {
                                            Screen screen2;
                                            block56: {
                                                block57: {
                                                    n = PreviewScene.a;
                                                    screen2 = screen;
                                                    if (n != 0) break block56;
                                                    if (screen2.getClass().getName().startsWith(a[8])) break block57;
                                                    return false;
                                                }
                                                screen2 = screen;
                                            }
                                            bl3 = screen2 instanceof HandledScreen;
                                            if (n != 0) break block58;
                                            if (bl3) break block59;
                                            bl3 = screen instanceof TitleScreen;
                                            if (n != 0) break block58;
                                            if (bl3) break block59;
                                            bl2 = screen instanceof GameMenuScreen;
                                            if (n != 0) break block60;
                                            if (!bl2) break block61;
                                        }
                                        bl3 = false;
                                    }
                                    return bl3;
                                }
                                bl2 = bl;
                            }
                            if (n != 0) break block62;
                            if (bl2) break block63;
                            return true;
                        }
                        bl2 = screen instanceof GameOptionsScreen;
                    }
                    if (n != 0) break block64;
                    if (bl2) break block65;
                    bl2 = screen instanceof OptionsScreen;
                    if (n != 0) break block64;
                    if (bl2) break block65;
                    bl2 = screen instanceof StatsScreen;
                    if (n != 0) break block64;
                    if (bl2) break block65;
                    bl2 = screen instanceof AdvancementsScreen;
                    if (n != 0) break block64;
                    if (bl2) break block65;
                    bl2 = screen instanceof OpenToLanScreen;
                    if (n != 0) break block64;
                    if (bl2) break block65;
                    bl2 = screen instanceof PackScreen;
                    if (n != 0) break block64;
                    if (bl2) break block65;
                    bl2 = screen instanceof LevelLoadingScreen;
                    if (n != 0) break block64;
                    if (bl2) break block65;
                    bl2 = screen instanceof ProgressScreen;
                    if (n != 0) break block64;
                    if (bl2) break block65;
                    bl2 = screen instanceof MessageScreen;
                    if (n != 0) break block64;
                    if (bl2) break block65;
                    bl2 = screen instanceof ReconfiguringScreen;
                    if (n != 0) break block64;
                    if (!bl2) break block66;
                }
                bl2 = true;
                break block64;
            }
            bl2 = false;
        }
        return bl2;
    }

    public static Text label(ClickableWidget clickableWidget, boolean bl) {
        int n;
        Text text;
        block21: {
            block23: {
                block22: {
                    int n2;
                    block19: {
                        block20: {
                            text = clickableWidget.method_25369();
                            n2 = PreviewScene.a;
                            if (!clickableWidget.field_22763) {
                                return text.method_27661().method_10862(text.method_10866().method_36139(MenuButton.a(17436, 612366626566421459L)));
                            }
                            Object object = text.method_10851();
                            n = object instanceof TranslatableTextContent;
                            if (n2 != 0) break block19;
                            if (n == 0) break block20;
                            TranslatableTextContent translatableTextContent = (TranslatableTextContent)object;
                            n = translatableTextContent.method_11022().equals(a[3]) ? 1 : 0;
                            if (n2 != 0) break block19;
                            if (n == 0) break block20;
                            n = translatableTextContent.method_11023().length;
                            if (n2 != 0) break block19;
                            if (n != 2) break block20;
                            object = translatableTextContent.method_11023()[1];
                            n = object instanceof Text;
                            if (n2 != 0) break block19;
                            if (n == 0) break block20;
                            Text text2 = (Text)object;
                            text = Text.method_43469((String)translatableTextContent.method_11022(), (Object[])new Object[]{translatableTextContent.method_11023()[0], text2.method_27661().method_27696(Style.field_24360.method_36139(MenuButton.a(16688, 325914099599785707L)))}).method_10862(text.method_10866());
                        }
                        n = bl;
                    }
                    if (n2 != 0) break block21;
                    if (n != 0) break block22;
                    n = MenuButton.primary(clickableWidget);
                    if (n2 != 0) break block21;
                    if (n == 0) break block23;
                }
                n = MenuButton.a(16688, 325914099599785707L);
                break block21;
            }
            n = MenuButton.a(30059, 7607418982801934997L);
        }
        int n3 = n;
        return text.method_27661().method_10862(text.method_10866().method_27702(Style.field_24360.method_36139(n3)));
    }

    public static void well(DrawContext drawContext, int n, int n2, int n3, int n4, boolean bl) {
        int n5;
        int n6;
        int n7;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        DrawContext drawContext2;
        block2: {
            block3: {
                int n8 = PreviewScene.a;
                drawContext2 = drawContext;
                float f = n;
                f = n2;
                f = n3;
                f = n4;
                f = 8.0f;
                int n5 = MenuButton.a(7934, 8211113321962332471L);
                n5 = MenuButton.a(16354, 5426000667651457086L);
                n5 = bl;
                if (n8 != 0) break block2;
                if (n5 == 0) break block3;
                n5 = MenuButton.a(21411, 5641757049553726552L);
                break block2;
            }
            n5 = MenuButton.a(28006, 8965013378563117726L);
        }
        MenuButton.panel(drawContext2, f5, f4, f3, f2, f, n7, n6, n5);
    }

    public static void check(DrawContext drawContext, int n, int n2, int n3, boolean bl, boolean bl2, float f) {
        block10: {
            int n4;
            int n5;
            int n6;
            int n7;
            int n8;
            MenuButton$Px menuButton$Px;
            block11: {
                block12: {
                    int n9;
                    block8: {
                        block9: {
                            menuButton$Px = MenuButton.px(drawContext, n, n2, n3, n3);
                            n8 = MenuButton.dp(menuButton$Px, 18.0f);
                            n7 = MenuButton.dp(menuButton$Px, 5.0f);
                            n6 = menuButton$Px.x0() + (menuButton$Px.x1() - menuButton$Px.x0() - n8) / 2;
                            n5 = menuButton$Px.y0() + (menuButton$Px.y1() - menuButton$Px.y0() - n8) / 2;
                            n9 = PreviewScene.a;
                            MenuButton.pixels(drawContext);
                            n4 = bl;
                            if (n9 != 0) break block8;
                            if (n4 == 0) break block9;
                            MenuButton.fillPx(drawContext, n6, n5, n6 + n8, n5 + n8, n7, MenuButton.fade(MenuButton.a(17394, 4281871813718162471L), f), MenuButton.fade(MenuButton.a(17394, 4281871813718162471L), f));
                            drawContext.method_25291(RenderPipelines.field_56883, MenuButton.tick(n8), n6, n5, 0.0f, 0.0f, n8, n8, n8, n8, MenuButton.fade(-1, f));
                            if (n9 == 0) break block10;
                        }
                        n4 = bl2;
                    }
                    if (n9 != 0) break block11;
                    if (n4 == 0) break block12;
                    n4 = MenuButton.a(5345, 82443429521451810L);
                    break block11;
                }
                n4 = MenuButton.a(28221, 5867209232504099275L);
            }
            int n10 = MenuButton.fade(n4, f);
            MenuButton.fillPx(drawContext, n6, n5, n6 + n8, n5 + n8, n7, n10, n10);
            MenuButton.rimPx(drawContext, n6, n5, n6 + n8, n5 + n8, n7, MenuButton.dp(menuButton$Px, 1.0f), MenuButton.fade(MenuButton.a(3219, 3885209558656938840L), f));
        }
        drawContext.method_51448().popMatrix();
    }

    private static Identifier tick(int n) {
        return TICKS.computeIfAbsent(n, arg_0 -> MenuButton.lambda$tick$3(n, arg_0));
    }

    private static float segment(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f5 - f3;
        float f8 = f6 - f4;
        float f9 = Math.max(0.0f, Math.min(1.0f, ((f - f3) * f7 + (f2 - f4) * f8) / (f7 * f7 + f8 * f8)));
        return (float)Math.hypot(f - f3 - f7 * f9, f2 - f4 - f8 * f9);
    }

    public static void lock(DrawContext drawContext, ClickableWidget clickableWidget, int n, int n2, int n3, int n4, boolean bl) {
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        float f;
        float f2;
        int n10;
        int n11;
        Identifier identifier;
        RenderPipeline renderPipeline;
        DrawContext drawContext2;
        block17: {
            block18: {
                int n12;
                block15: {
                    int n13;
                    block16: {
                        int n14;
                        block14: {
                            block13: {
                                ClickableWidget clickableWidget2;
                                block12: {
                                    n12 = PreviewScene.a;
                                    clickableWidget2 = clickableWidget;
                                    if (n12 != 0) break block12;
                                    if (!clickableWidget2.field_22763) break block13;
                                    clickableWidget2 = clickableWidget;
                                }
                                n14 = clickableWidget2.method_25367();
                                if (n12 != 0) break block14;
                                if (n14 == 0) break block13;
                                n14 = 1;
                                break block14;
                            }
                            n14 = 0;
                        }
                        n13 = n14;
                        MenuButton.slab(drawContext, clickableWidget, n, n2, n3, n4, n13 != 0);
                        MenuButton$Px menuButton$Px = MenuButton.px(drawContext, n, n2, n3, n4);
                        int n15 = MenuButton.dp(menuButton$Px, 18.0f);
                        MenuButton.pixels(drawContext);
                        drawContext2 = drawContext;
                        renderPipeline = RenderPipelines.field_56883;
                        identifier = MenuButton.padlock(n15, bl);
                        n11 = menuButton$Px.x0() + (menuButton$Px.x1() - menuButton$Px.x0() - n15) / 2;
                        n10 = menuButton$Px.y0() + (menuButton$Px.y1() - menuButton$Px.y0() - n15) / 2;
                        f2 = 0.0f;
                        f = 0.0f;
                        n9 = n15;
                        n8 = n15;
                        n7 = n15;
                        n6 = n15;
                        n5 = clickableWidget.field_22763;
                        if (n12 != 0) break block15;
                        if (n5 != 0) break block16;
                        n5 = MenuButton.a(17408, 6997105939388414973L);
                        break block17;
                    }
                    n5 = n13;
                }
                if (n12 != 0) break block17;
                if (n5 == 0) break block18;
                n5 = -1;
                break block17;
            }
            n5 = MenuButton.a(16224, 2126009815242260629L);
        }
        drawContext2.method_25291(renderPipeline, identifier, n11, n10, f2, f, n9, n8, n7, n6, MenuButton.fade(n5, clickableWidget.method_75798()));
        drawContext.method_51448().popMatrix();
    }

    private static Identifier padlock(int n, boolean bl) {
        int n2;
        Map<Integer, Identifier> map;
        block6: {
            int n3;
            block4: {
                block5: {
                    int n4 = PreviewScene.a;
                    map = PADLOCKS;
                    n3 = bl;
                    if (n4 != 0) break block4;
                    if (n3 == 0) break block5;
                    n2 = n;
                    break block6;
                }
                n3 = n;
            }
            n2 = -n3;
        }
        return map.computeIfAbsent(n2, arg_0 -> MenuButton.lambda$padlock$4(bl, n, arg_0));
    }

    public static void tab(DrawContext drawContext, int n, int n2, int n3, int n4) {
        drawContext.method_25290(RenderPipelines.field_56883, Screen.field_49895, n, n2 + n4 - 2, 0.0f, 0.0f, n3, 2, 32, 2);
    }

    public static Text tabLabel(TabButtonWidget tabButtonWidget) {
        int n;
        Style style;
        Style style2;
        MutableText mutableText;
        block11: {
            block12: {
                TabButtonWidget tabButtonWidget2;
                int n2;
                Text text;
                block9: {
                    block10: {
                        text = tabButtonWidget.method_25369();
                        n2 = PreviewScene.a;
                        tabButtonWidget2 = tabButtonWidget;
                        if (n2 != 0) break block9;
                        if (tabButtonWidget2.field_22763) break block10;
                        return text;
                    }
                    tabButtonWidget2 = tabButtonWidget;
                }
                if (tabButtonWidget2.method_49611()) {
                    return text.method_27661().method_10862(text.method_10866().method_27702(Style.field_24360.method_36139(MenuButton.a(16688, 325914099599785707L))));
                }
                mutableText = text.method_27661();
                Style style = text.method_10866();
                style = Style.field_24360;
                n = tabButtonWidget.method_25367();
                if (n2 != 0) break block11;
                if (n == 0) break block12;
                n = MenuButton.a(11487, 4360113855021994765L);
                break block11;
            }
            n = MenuButton.a(15097, 6750639014849332531L);
        }
        return mutableText.method_10862(style2.method_27702(style.method_36139(n)));
    }

    public static void tabLine(DrawContext drawContext, TabButtonWidget tabButtonWidget) {
        int n = Math.min(MinecraftClient.method_1551().field_1772.method_27525((StringVisitable)MenuButton.tabLabel(tabButtonWidget)), tabButtonWidget.method_25368() - 4);
        MenuButton$Px menuButton$Px = MenuButton.px(drawContext, (float)tabButtonWidget.method_46426() + (float)(tabButtonWidget.method_25368() - n) / 2.0f, tabButtonWidget.method_46427(), n, tabButtonWidget.method_25364());
        MenuButton.pixels(drawContext);
        drawContext.method_25294(menuButton$Px.x0(), menuButton$Px.y1() - MenuButton.dp(menuButton$Px, 2.0f), menuButton$Px.x1(), menuButton$Px.y1(), MenuButton.a(13592, 8341883287380183769L));
        drawContext.method_51448().popMatrix();
    }

    public static void selected(DrawContext drawContext, int n, int n2, int n3, int n4) {
        MenuButton.panel(drawContext, n, n2, n3, n4, 8.0f, MenuButton.a(26378, 6381094022655154395L), MenuButton.a(134, 3742883049402105685L), MenuButton.a(6491, 7978230867327542943L));
    }

    public static void scroller(DrawContext drawContext, int n, int n2, int n3, int n4) {
        MenuButton$Px menuButton$Px = MenuButton.px(drawContext, n, n2, n3, n4);
        int n5 = Math.max(2, MenuButton.dp(menuButton$Px, 4.0f));
        int n6 = menuButton$Px.x1() - MenuButton.dp(menuButton$Px, 2.0f);
        MenuButton.pixels(drawContext);
        MenuButton.fillPx(drawContext, n6 - n5, menuButton$Px.y0(), n6, menuButton$Px.y1(), n5 / 2, MenuButton.a(24401, 1906768169816531092L), MenuButton.a(14894, 2744577688037017076L));
        drawContext.method_51448().popMatrix();
    }

    static float entrance(ClickableWidget clickableWidget) {
        float f;
        block6: {
            Float f2;
            block4: {
                Float f3;
                block5: {
                    f3 = ORDER.get(clickableWidget);
                    int n = PreviewScene.a;
                    f2 = f3;
                    if (n != 0) break block4;
                    if (f2 != null) break block5;
                    f = 1.0f;
                    break block6;
                }
                f2 = f3;
            }
            f = MenuButton.entranceAt(f2.floatValue());
        }
        return f;
    }

    static float entranceAt(float f) {
        long l;
        long l2;
        block14: {
            Long l3;
            block12: {
                Long l4;
                block13: {
                    int n;
                    Screen screen;
                    block11: {
                        block10: {
                            MinecraftClient minecraftClient = MinecraftClient.method_1551();
                            screen = minecraftClient.field_1755;
                            n = PreviewScene.a;
                            if (screen == null) break block10;
                            if (minecraftClient.method_18506() == null) break block11;
                            if (AuroraSplash.revealing) break block11;
                        }
                        return 0.0f;
                    }
                    long l5 = Anim.now();
                    l4 = OPENED.putIfAbsent(screen, l5);
                    l2 = l5;
                    l3 = l4;
                    if (n != 0) break block12;
                    if (l3 != null) break block13;
                    l = l5;
                    break block14;
                }
                l3 = l4;
            }
            l = l3;
        }
        long l6 = l2 - l - (long)(f * 5.5E7f);
        return Math.max(0.0f, Math.min(1.0f, (float)l6 / 4.5E8f));
    }

    public static float settle(float f) {
        float f2 = 1.7f;
        float f3 = f - 1.0f;
        return 1.0f + (f2 + 1.0f) * f3 * f3 * f3 + f2 * f3 * f3;
    }

    static boolean popIn(DrawContext drawContext, float f, float f2, float f3, float f4, float f5, float f6) {
        float f7;
        block4: {
            block5: {
                int n = PreviewScene.a;
                f7 = f;
                if (n != 0) break block4;
                if (!(f7 >= 1.0f)) break block5;
                return false;
            }
            f7 = MenuButton.settle(f);
        }
        float f8 = f7;
        float f9 = f2 + f4 / 2.0f;
        float f10 = f3 + f5 / 2.0f;
        Matrix3x2fStack matrix3x2fStack = drawContext.method_51448();
        matrix3x2fStack.pushMatrix();
        matrix3x2fStack.translate(f9, f10 + (1.0f - f8) * f6);
        matrix3x2fStack.scale(0.9f + 0.1f * f8);
        matrix3x2fStack.translate(-f9, -f10);
        return true;
    }

    public static void knob(DrawContext drawContext, ClickableWidget clickableWidget, int n, int n2, int n3, int n4) {
        int n5;
        int n6;
        int n7;
        int n8;
        MenuButton$Px menuButton$Px;
        block20: {
            block21: {
                int n9;
                block18: {
                    int n10;
                    block19: {
                        int n11;
                        block16: {
                            block17: {
                                block22: {
                                    block15: {
                                        int n12;
                                        block14: {
                                            n9 = PreviewScene.a;
                                            n12 = n3;
                                            if (n9 != 0) break block14;
                                            if (n12 <= 0) break block15;
                                            n12 = n4;
                                        }
                                        if (n12 > 0) break block22;
                                    }
                                    return;
                                }
                                menuButton$Px = MenuButton.px(drawContext, (float)n + (float)n3 / 2.0f, n2, 0.0f, n4);
                                n8 = Math.max(2, MenuButton.dp(menuButton$Px, 3.0f));
                                n7 = MenuButton.dp(menuButton$Px, 6.0f);
                                n6 = menuButton$Px.x0() - n8 / 2;
                                int n13 = MinecraftClient.method_1551().field_1772.method_27525((StringVisitable)clickableWidget.method_25369());
                                n11 = n;
                                if (n9 != 0) break block16;
                                if (!(Math.abs((float)n11 + (float)n3 / 2.0f - ((float)clickableWidget.method_46426() + (float)clickableWidget.method_25368() / 2.0f)) < (float)n13 / 2.0f + 2.0f)) break block17;
                                n11 = 1;
                                break block16;
                            }
                            n11 = 0;
                        }
                        n10 = n11;
                        MenuButton.pixels(drawContext);
                        n5 = clickableWidget.field_22763;
                        if (n9 != 0) break block18;
                        if (n5 != 0) break block19;
                        n5 = MenuButton.a(4692, 8313520816988679572L);
                        break block20;
                    }
                    n5 = n10;
                }
                if (n9 != 0) break block20;
                if (n5 == 0) break block21;
                n5 = MenuButton.a(19379, 4224527059546584170L);
                break block20;
            }
            n5 = -1;
        }
        int n14 = MenuButton.fade(n5, clickableWidget.method_75798());
        MenuButton.fillPx(drawContext, n6, menuButton$Px.y0() + n7, n6 + n8, menuButton$Px.y1() - n7, n8 / 2, n14, n14);
        drawContext.method_51448().popMatrix();
    }

    static float ease(ClickableWidget clickableWidget, boolean bl) {
        float f;
        block18: {
            MenuButton$Ease menuButton$Ease;
            block16: {
                MenuButton$Ease menuButton$Ease2;
                block17: {
                    float f2;
                    int n;
                    block15: {
                        block14: {
                            n = PreviewScene.a;
                            float f3 = bl ? 1.0f : 0.0f;
                            f2 = f3;
                            menuButton$Ease2 = EASE.get(clickableWidget);
                            if (n != 0) break block14;
                            if (menuButton$Ease2 != null) break block15;
                            EASE.put(clickableWidget, new MenuButton$Ease(f2));
                        }
                        return f2;
                    }
                    long l = Anim.now();
                    float f4 = Math.min(0.1f, (float)(l - menuButton$Ease2.nanos) / 1.0E9f);
                    menuButton$Ease = menuButton$Ease2;
                    if (n != 0) break block16;
                    menuButton$Ease.nanos = l;
                    if (!(f4 > 0.0f)) break block17;
                    menuButton$Ease2.value += (f2 - menuButton$Ease2.value) * (1.0f - (float)Math.exp(-16.0f * f4));
                    f = f2;
                    if (n != 0) break block18;
                    if (!(Math.abs(f - menuButton$Ease2.value) < 0.002f)) break block17;
                    menuButton$Ease2.value = f2;
                }
                menuButton$Ease = menuButton$Ease2;
            }
            f = menuButton$Ease.value;
        }
        return f;
    }

    static int fade(int n, float f) {
        float f2;
        block4: {
            block5: {
                int n2 = PreviewScene.a;
                float f3 = f - 1.0f;
                f2 = f3 == 0.0f ? 0 : (f3 > 0.0f ? 1 : -1);
                if (n2 != 0) break block4;
                if (f2 < 0) break block5;
                return n;
            }
            f2 = Theme.alpha(n, Math.round((float)(n >>> 24 & 0xFF) * Math.max(0.0f, f)));
        }
        return (int)f2;
    }

    private static Identifier lambda$padlock$4(boolean bl, int n, Integer n2) {
        String string;
        NativeImage nativeImage;
        block23: {
            block16: {
                int n3 = PreviewScene.a;
                float f = bl ? 0.0f : 0.09f * (float)n;
                float f2 = f;
                float f3 = 0.5f * (float)n;
                float f4 = 0.4f * (float)n - f2;
                float f5 = 0.17f * (float)n;
                float f6 = Math.max(0.6f, 0.05f * (float)n);
                nativeImage = new NativeImage(NativeImage.Format.field_4997, n, n, true);
                int n4 = 0;
                while (n4 < n) {
                    block17: {
                        if (n3 != 0) break block16;
                        for (int i = 0; i < n; ++i) {
                            float f7;
                            float f8;
                            float f9;
                            float f10;
                            float f11;
                            float f12;
                            float f13;
                            float f14;
                            float f15;
                            block22: {
                                float f16;
                                block20: {
                                    block21: {
                                        float f17;
                                        float f18;
                                        float f19;
                                        block18: {
                                            block19: {
                                                f19 = (float)i + 0.5f;
                                                f18 = (float)n4 + 0.5f;
                                                float f20 = Math.abs(f19 - f3) - (0.27f * (float)n - 0.06f * (float)n);
                                                float f21 = Math.abs(f18 - 0.68f * (float)n) - (0.18f * (float)n - 0.06f * (float)n);
                                                f15 = (float)Math.hypot(Math.max(f20, 0.0f), Math.max(f21, 0.0f)) + Math.min(Math.max(f20, f21), 0.0f) - 0.06f * (float)n;
                                                if (n3 != 0) break block17;
                                                f17 = f18;
                                                if (n3 != 0) break block18;
                                                if (!(f17 <= f4)) break block19;
                                                f17 = Math.abs((float)Math.hypot(f19 - f3, f18 - f4) - f5);
                                                break block18;
                                            }
                                            f17 = Float.MAX_VALUE;
                                        }
                                        f14 = f17;
                                        f13 = f19;
                                        f12 = f18;
                                        f11 = f3 - f5;
                                        f10 = f4;
                                        f9 = f3 - f5;
                                        f8 = 0.52f;
                                        f16 = n;
                                        if (n3 != 0) break block20;
                                        f13 = MenuButton.segment(f13, f12, f11, f10, f9, f8 * f16);
                                        f12 = f19;
                                        f11 = f18;
                                        f10 = f3 + f5;
                                        f9 = f4;
                                        f8 = f3 + f5;
                                        if (!bl) break block21;
                                        f7 = 0.52f * (float)n;
                                        break block22;
                                    }
                                    f16 = f4;
                                }
                                f7 = f16 + 0.05f * (float)n;
                            }
                            float f22 = Math.min(f13, MenuButton.segment(f12, f11, f10, f9, f8, f7));
                            float f23 = Math.max(0.5f - f15, f6 + 0.5f - Math.min(f14, f22));
                            nativeImage.method_61941(i, n4, Math.round(255.0f * Math.max(0.0f, Math.min(1.0f, f23))) << 24 | MenuButton.a(16688, 325914099599785707L));
                            if (n3 == 0) continue;
                        }
                        ++n4;
                    }
                    if (n3 == 0) continue;
                }
                if (!bl) break block16;
                string = a[12];
                break block23;
            }
            String[] stringArray = a;
            string = stringArray[9];
        }
        int n5 = n;
        String string2 = string;
        return MenuButton.texture(a[10] + string2 + n5, nativeImage);
    }

    private static Identifier lambda$tick$3(int n, Integer n2) {
        float f = (float)n / 18.0f;
        int n3 = PreviewScene.a;
        float f2 = Math.max(0.6f, 0.9f * f);
        float f3 = 5.5f * f;
        float f4 = 9.25f * f;
        float f5 = 7.75f * f;
        float f6 = 11.5f * f;
        float f7 = 12.5f * f;
        float f8 = 6.75f * f;
        NativeImage nativeImage = new NativeImage(NativeImage.Format.field_4997, n, n, true);
        int n4 = 0;
        while (n4 < n) {
            block5: {
                for (int i = 0; i < n; ++i) {
                    float f9 = Math.min(MenuButton.segment((float)i + 0.5f, (float)n4 + 0.5f, f3, f4, f5, f6), MenuButton.segment((float)i + 0.5f, (float)n4 + 0.5f, f5, f6, f7, f8));
                    if (n3 == 0) {
                        nativeImage.method_61941(i, n4, Math.round(255.0f * Math.max(0.0f, Math.min(1.0f, f2 + 0.5f - f9))) << 24 | MenuButton.a(16688, 325914099599785707L));
                        if (n3 == 0) continue;
                        break;
                    }
                    break block5;
                }
                ++n4;
            }
            if (n3 == 0) continue;
        }
        return MenuButton.texture(a[0] + n2, nativeImage);
    }

    private static Boolean lambda$menu$2(Screen screen) {
        Screen screen2 = screen;
        boolean bl = MinecraftClient.method_1551().field_1687 != null;
        return MenuButton.vanillaMenu(screen2, bl);
    }

    private static String lambda$texture$1(String string) {
        return a[5] + string;
    }

    private static Identifier lambda$sheet$0(int n, int n2, Integer n3) {
        NativeImage nativeImage = new NativeImage(NativeImage.Format.field_4997, n * 2, n * 2, true);
        int n4 = PreviewScene.a;
        int n5 = 0;
        while (n5 < n * 2) {
            block5: {
                for (int i = 0; i < n * 2; ++i) {
                    if (n4 == 0) {
                        float f = (float)Math.hypot((float)i + 0.5f - (float)n, (float)n5 + 0.5f - (float)n) - (float)n;
                        float f2 = n2 > 0 ? Math.min(0.5f - f, f + (float)n2 + 0.5f) : 0.5f - f;
                        float f3 = f2;
                        nativeImage.method_61941(i, n5, Math.round(255.0f * Math.max(0.0f, Math.min(1.0f, f3))) << 24 | MenuButton.a(28753, 8829205142720712604L));
                        if (n4 == 0) continue;
                    }
                    break block5;
                }
                ++n5;
            }
            if (n4 == 0) continue;
        }
        return MenuButton.texture(a[1] + n3, nativeImage);
    }

    
    static {
        block32: {
            block31: {
                block30: {
                    block29: {
                        block28: {
                            block27: {
                                var21 = new String[13];
                                var19_1 = 0;
                                var18_2 = "T.~G\u001b\u0018tZ ?\fT.~G\u001b\u000frK%u@k\u0012J.|W'\u0018JV9|Vj\u001fxU.sF\u0015V;d[+\u0002n\u0017,u\\!\u001etZ\u0014fS(\u0019x\rX/ta!\u001ek\\9>S \b\u0007X>b]6\r2\u0006X>b]6\r\u0013J.|W'\u0018N\\9fW6Bn\\'uQ0\u000eW.d\u001c)\u0005s\\(bS\"\u00183\u0003V-v\nT.~G\u001b\u0000rZ ?";
                                var20_3 = "T.~G\u001b\u0018tZ ?\fT.~G\u001b\u000frK%u@k\u0012J.|W'\u0018JV9|Vj\u001fxU.sF\u0015V;d[+\u0002n\u0017,u\\!\u001etZ\u0014fS(\u0019x\rX/ta!\u001ek\\9>S \b\u0007X>b]6\r2\u0006X>b]6\r\u0013J.|W'\u0018N\\9fW6Bn\\'uQ0\u000eW.d\u001c)\u0005s\\(bS\"\u00183\u0003V-v\nT.~G\u001b\u0000rZ ?".length();
                                var17_4 = 10;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block27;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "^>y\u001c \u0003s\\\u0002V%";
                                    var20_3 = "^>y\u001c \u0003s\\\u0002V%".length();
                                    var17_4 = 8;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block27;
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
                                    break block28;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_7 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_7;
                                v9 = v6[v8];
                                switch (var22_7 % 7) {
                                    case 0: {
                                        v10 = 57;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 75;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 16;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 50;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 68;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 108;
                                        break;
                                    }
                                    default: {
                                        v10 = 29;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_7;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_7);
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
                        MenuButton.a = var21;
                        var8_8 = 1018326666896403859L;
                        var14_9 = new long[45];
                        var11_10 = 0;
                        var12_11 = "!\u00b6\u00fb\u00cc^$l\u00ff\u000b\u00fe\u00f8\u00fb\u008br#\u00f8N\u000e\u00f4I\u00e0\u00cf-\u00adw\u0096\u00f4\u00b4\u001cU\u00efy\u00ee\u00b0\u0084\u0003\u00a6\u00bd\u00d7\u0098\u00c3V\u008f\u00dc\u00d8\u00ba\u00ac\u0087\u0012\u00ff[\u00d8\u00a1\u00da\u00e8^\u0015Y\u0017@e\u0019\u00a6\u0006\u00b0p,\u009d9\u00b8\b}P_\u008a\u001f\u00ba\u0011^\u00f4\u00e2_#~\u00c2\u008d\u00a3\u00f0\u00f0iik\u00dc\u009e\u0013k\u00d3\u001e\u00ae\u0096\t\u00b2xLy\u00e11\u00e5\u00d1P\u00ee0Ap\u00cd\u0097\u00fd\u0004\"Fi\u00b7\u00c4z\u00d0\u009b\t\u00beQ\u00ca\u00e8/p;iG<K!\u001d\u009d\u000b9:_W\u00a0\u0083D\u00f6\u001c3\u00a9-\u00a1:\u0019\u00be\u009d\u00f0P\u0001\u0086\u00cf\u00ab\u00c3\u00b4\n\u00d1@\u0086\u00ff!\u00e7\u00e14G\u00e7\u008d\u00b9\u00b6\u00ea\u0093\u0004p\u0004SV\u00d4\u00a0\f\u00a4\u008f\u00ac\u0086k<\u00e9\u0005\u00ca\u0003N\u00a9?\u00deO\u0085.\u0085M{\u00e2\u009b<\u00f7\u00fa\u009c^\u00d7\u0084I\u00ba\u00b2\u00f8i\u00f9\u00f2\\\u00af\u0096~\u00a0\u00d6F\u001b\u0084\u0090\u00e5\u00f9N\u000b^\u00f5\u000fk\u008cOg\u00aa\u00a2\u00c1\u0012\u00a4\u00cd\u00c6\u00bed\u001c\u00e0\u0003\u0002\u0013!\u00d4\u00b1z\u00f6\u00a4\u00d7\u00e7\u00b1\u00aa\u0083E\u00f8\u0084\u00cd\u0081\u00d5\u00ad\u00ad\u00afx\u008a'\u00c59\u0014\u00af\u0082\u00f3\u00f7\t\u00fd\u00bdJ\u0013\u0012\u00f3\u00e2\u009d\u0095==>\u0082\u00b7u'\u00b4\u0000\u00f2\u001b\u000fJX\u00c9\n\u00acH\u00ff\u00cf\u007f\u0001Q\u0019\u0083\u00a7\u0096\u00e9X\u00b3\u0006#y~o\u00fc\u00a6\u00e4&`gP";
                        var13_12 = "!\u00b6\u00fb\u00cc^$l\u00ff\u000b\u00fe\u00f8\u00fb\u008br#\u00f8N\u000e\u00f4I\u00e0\u00cf-\u00adw\u0096\u00f4\u00b4\u001cU\u00efy\u00ee\u00b0\u0084\u0003\u00a6\u00bd\u00d7\u0098\u00c3V\u008f\u00dc\u00d8\u00ba\u00ac\u0087\u0012\u00ff[\u00d8\u00a1\u00da\u00e8^\u0015Y\u0017@e\u0019\u00a6\u0006\u00b0p,\u009d9\u00b8\b}P_\u008a\u001f\u00ba\u0011^\u00f4\u00e2_#~\u00c2\u008d\u00a3\u00f0\u00f0iik\u00dc\u009e\u0013k\u00d3\u001e\u00ae\u0096\t\u00b2xLy\u00e11\u00e5\u00d1P\u00ee0Ap\u00cd\u0097\u00fd\u0004\"Fi\u00b7\u00c4z\u00d0\u009b\t\u00beQ\u00ca\u00e8/p;iG<K!\u001d\u009d\u000b9:_W\u00a0\u0083D\u00f6\u001c3\u00a9-\u00a1:\u0019\u00be\u009d\u00f0P\u0001\u0086\u00cf\u00ab\u00c3\u00b4\n\u00d1@\u0086\u00ff!\u00e7\u00e14G\u00e7\u008d\u00b9\u00b6\u00ea\u0093\u0004p\u0004SV\u00d4\u00a0\f\u00a4\u008f\u00ac\u0086k<\u00e9\u0005\u00ca\u0003N\u00a9?\u00deO\u0085.\u0085M{\u00e2\u009b<\u00f7\u00fa\u009c^\u00d7\u0084I\u00ba\u00b2\u00f8i\u00f9\u00f2\\\u00af\u0096~\u00a0\u00d6F\u001b\u0084\u0090\u00e5\u00f9N\u000b^\u00f5\u000fk\u008cOg\u00aa\u00a2\u00c1\u0012\u00a4\u00cd\u00c6\u00bed\u001c\u00e0\u0003\u0002\u0013!\u00d4\u00b1z\u00f6\u00a4\u00d7\u00e7\u00b1\u00aa\u0083E\u00f8\u0084\u00cd\u0081\u00d5\u00ad\u00ad\u00afx\u008a'\u00c59\u0014\u00af\u0082\u00f3\u00f7\t\u00fd\u00bdJ\u0013\u0012\u00f3\u00e2\u009d\u0095==>\u0082\u00b7u'\u00b4\u0000\u00f2\u001b\u000fJX\u00c9\n\u00acH\u00ff\u00cf\u007f\u0001Q\u0019\u0083\u00a7\u0096\u00e9X\u00b3\u0006#y~o\u00fc\u00a6\u00e4&`gP".length();
                        var10_13 = 0;
                        while (true) {
                            var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                            v11 = var14_9;
                            v12 = var11_10++;
                            v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                            v14 = -1;
                            break block29;
                            break;
                        }
lbl89:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            var12_11 = " \u00fc\u00d24\u0000\u00e8k\u00d4\u009f\u0019\u0001\u00c6R+x(";
                            var13_12 = " \u00fc\u00d24\u0000\u00e8k\u00d4\u009f\u0019\u0001\u00c6R+x(".length();
                            var10_13 = 0;
                            while (true) {
                                var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                                v11 = var14_9;
                                v12 = var11_10++;
                                v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                                v14 = 0;
                                break block29;
                                break;
                            }
                            break;
                        }
lbl102:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            break block30;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_8;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl113:

                        continue;
                    }
                }
                MenuButton.b = var14_9;
                MenuButton.c = new Integer[45];
                MenuButton.GO_TOP = MenuButton.a(7137, 291907594325812281L);
                MenuButton.ICON = MenuButton.a(16267, 3587627975532527729L);
                MenuButton.V_WELL_RIM = MenuButton.a(26720, 3710768594531653542L);
                MenuButton.V_OFF = MenuButton.a(1120, 7078407521591098263L);
                MenuButton.V_HOVER = MenuButton.a(20040, 8957623798293115292L);
                MenuButton.V_SUB = MenuButton.a(272, 7082340084731346668L);
                MenuButton.V_FILL = MenuButton.a(1253, 8404968300025379627L);
                MenuButton.V_MUTE = MenuButton.a(3024, 6283741120223573015L);
                MenuButton.ROW = MenuButton.a(32109, 5439859026967233183L);
                MenuButton.NAME = MenuButton.a(26452, 9011919278727370891L);
                MenuButton.V_WELL = MenuButton.a(32434, 31907115892113772L);
                MenuButton.GO_FOOT = MenuButton.a(29402, 1756262525822274826L);
                MenuButton.ACCENT = MenuButton.a(2497, 4464013439588759093L);
                MenuButton.V_RIM = MenuButton.a(4878, 2257733769689787596L);
                var1_15 = 341907135778691741L;
                var0_16 = new long[2];
                var4_17 = 0;
                var5_18 = "\u0004\u00be\u00b2\u00ba}\u0005\u00a1]\u0004\u00be\u00b2\u00bad\u0090\u00ee\u001d";
                var6_19 = "\u0004\u00be\u00b2\u00ba}\u0005\u00a1]\u0004\u00be\u00b2\u00bad\u0090\u00ee\u001d".length();
                var3_20 = 0;
                while (true) {
                    break block31;
                    break;
                }
lbl139:

                while (true) {
                    var0_16[v16] = (((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L) ^ var1_15;
                    if (var3_20 < var6_19) continue;
                    break block32;
                    break;
                }
            }
            var7_21 = var5_18.substring(var3_20, var3_20 += 8).getBytes("ISO-8859-1");
            v16 = var4_17++;
            while (true)
        }
        MenuButton.POP = var0_16[1];
        MenuButton.BEAT = var0_16[0];
        var16_6 = MenuButton.a;
        MenuButton.GO = Set.of(var16_6[11], var16_6[2], var16_6[7], var16_6[4]);
        MenuButton.EASE = new WeakHashMap<ClickableWidget, MenuButton$Ease>();
        MenuButton.PINS = new WeakHashMap<ClickableWidget, MenuButton$Pin>();
        MenuButton.SHEETS = new HashMap<Integer, Identifier>();
        MenuButton.MENUS = new WeakHashMap<Screen, Boolean>();
        MenuButton.TICKS = new HashMap<Integer, Identifier>();
        MenuButton.PADLOCKS = new HashMap<Integer, Identifier>();
        MenuButton.ORDER = new WeakHashMap<ClickableWidget, Float>();
        MenuButton.OPENED = new WeakHashMap<Screen, Long>();
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7FDE;
        if (c[n2] == null) {
            MenuButton.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
