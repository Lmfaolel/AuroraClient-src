package dev.quelchi.aurora.hud;

import dev.quelchi.aurora.hud.ItemHighlights$Style;
import dev.quelchi.aurora.mixins.HandledScreenSizeAccessor;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.Window;

public final class InventoryScale {
    private static boolean rendering;
    private static boolean containerRendering;
    private static float lastWanted;

    private InventoryScale() {
    }

    public static void tick() {
        block5: {
            block4: {
                int n = ItemHighlights$Style.a;
                float f = InventoryScale.wanted();
                if (n != 0) break block4;
                if (f == lastWanted) break block5;
                lastWanted = f;
            }
            InventoryScale.relayout();
        }
    }

    public static void invalidate() {
        lastWanted = -1.0f;
        InventoryScale.relayout();
    }

    private static float wanted() {
        float f;
        block6: {
            GuiTweaksModule guiTweaksModule;
            block4: {
                GuiTweaksModule guiTweaksModule2;
                block5: {
                    guiTweaksModule2 = GuiTweaksModule.get();
                    int n = ItemHighlights$Style.a;
                    guiTweaksModule = guiTweaksModule2;
                    if (n != 0) break block4;
                    if (guiTweaksModule != null) break block5;
                    f = 0.0f;
                    break block6;
                }
                guiTweaksModule = guiTweaksModule2;
            }
            f = (float)guiTweaksModule.inventoryScaleValue();
        }
        return f;
    }

    private static void relayout() {
        block4: {
            MinecraftClient minecraftClient = MinecraftClient.method_1551();
            Screen screen = minecraftClient.field_1755;
            if (!(screen instanceof HandledScreen) || minecraftClient.method_22683() == null) break block4;
            screen.method_25410(minecraftClient.method_22683().method_4486(), minecraftClient.method_22683().method_4502());
        }
    }

    public static float factor() {
        int n;
        float f;
        float f2;
        float f3;
        Window window;
        block14: {
            block15: {
                Window window2;
                int n2;
                float f4;
                block12: {
                    block13: {
                        block11: {
                            float f5;
                            block10: {
                                f4 = InventoryScale.wanted();
                                n2 = ItemHighlights$Style.a;
                                f5 = f4;
                                if (n2 != 0) break block10;
                                if (!(f5 <= 0.0f)) break block11;
                                f5 = 1.0f;
                            }
                            return f5;
                        }
                        window = MinecraftClient.method_1551().method_22683();
                        window2 = window;
                        if (n2 != 0) break block12;
                        if (window2 != null) break block13;
                        return 1.0f;
                    }
                    window2 = window;
                }
                float f6 = Math.min((float)window2.method_4489() / 1920.0f, (float)window.method_4506() / 1080.0f);
                f3 = f4 * f6 / (float)Math.max(1, window.method_4495());
                f2 = 176.0f;
                f = 166.0f;
                Object object = MinecraftClient.method_1551().field_1755;
                n = object instanceof HandledScreen;
                if (n2 != 0) break block14;
                if (n == 0) break block15;
                HandledScreen handledScreen = (HandledScreen)object;
                object = (HandledScreenSizeAccessor)handledScreen;
                f2 = Math.max(1, object.aurora$bgWidth());
                f = Math.max(1, object.aurora$bgHeight());
            }
            n = window.method_4486();
        }
        float f7 = Math.min((float)n / (f2 + 16.0f), (float)window.method_4502() / (f + 16.0f));
        return Math.max(0.25f, Math.min(f3, f7));
    }

    public static float atlasFactor() {
        Window window;
        Window window2;
        float f;
        block10: {
            block11: {
                int n;
                block9: {
                    float f2;
                    block8: {
                        f = InventoryScale.wanted();
                        n = ItemHighlights$Style.a;
                        f2 = f;
                        if (n != 0) break block8;
                        if (!(f2 <= 0.0f)) break block9;
                        f2 = 1.0f;
                    }
                    return f2;
                }
                window2 = MinecraftClient.method_1551().method_22683();
                window = window2;
                if (n != 0) break block10;
                if (window != null) break block11;
                return 1.0f;
            }
            window = window2;
        }
        float f3 = Math.min((float)window.method_4489() / 1920.0f, (float)window2.method_4506() / 1080.0f);
        return Math.max(1.0f, f * f3 / (float)Math.max(1, window2.method_4495()));
    }

    public static boolean applies() {
        boolean bl;
        block8: {
            block7: {
                int n;
                block6: {
                    n = ItemHighlights$Style.a;
                    bl = MinecraftClient.method_1551().field_1755 instanceof HandledScreen;
                    if (n != 0) break block6;
                    if (!bl) break block7;
                    float f = InventoryScale.factor() - 1.0f;
                    bl = f == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
                }
                if (n != 0) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static double toVirtual(double d) {
        return d / (double)InventoryScale.factor();
    }

    public static int toVirtualSize(int n) {
        return (int)Math.ceil((float)n / InventoryScale.factor());
    }

    public static Click toVirtual(Click click) {
        return new Click(click.comp_4798() / (double)InventoryScale.factor(), click.comp_4799() / (double)InventoryScale.factor(), click.comp_4800());
    }

    public static boolean isRendering() {
        return rendering;
    }

    public static void setRendering(boolean bl) {
        rendering = bl;
    }

    public static boolean isContainerRendering() {
        return containerRendering;
    }

    public static void setContainerRendering(boolean bl) {
        containerRendering = bl;
    }

    static {
        lastWanted = -1.0f;
    }
}
