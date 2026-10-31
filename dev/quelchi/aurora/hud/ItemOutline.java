package dev.quelchi.aurora.hud;

import dev.quelchi.aurora.hud.ItemHighlights$Highlight;
import dev.quelchi.aurora.hud.ItemHighlights$Style;
import dev.quelchi.aurora.module.Module;
import net.minecraft.client.gui.DrawContext;

public final class ItemOutline {
    private ItemOutline() {
    }

    
    public static void draw(DrawContext drawContext, int n, int n2, ItemHighlights$Highlight itemHighlights$Highlight) {
        int n3 = ItemHighlights$Style.a;
        ItemHighlights$Highlight itemHighlights$Highlight2 = itemHighlights$Highlight;
        if (n3 == 0) {
            if (itemHighlights$Highlight2 == null) {
                return;
            }
            itemHighlights$Highlight2 = itemHighlights$Highlight;
        }
        int n4 = itemHighlights$Highlight2.rgb();
        float f = ItemOutline.pulse(itemHighlights$Highlight.pulse());
        int n5 = Math.round(64.0f * f);
        DrawContext drawContext2 = drawContext;
        int n6 = n;
        if (n3 == 0) {
            drawContext2.method_25294(n6, n2, n + 16, n2 + 16, n5 << 24 | n4);
            switch (itemHighlights$Highlight.style()) {
                case Border: {
                    ItemOutline.border(drawContext, n, n2, n4, f);
                    if (n3 == 0) return;
                }
                case Corners: {
                    ItemOutline.corners(drawContext, n, n2, n4, f);
                    if (n3 == 0) return;
                }
                case Solid: {
                    if (n3 == 0) return;
                }
                default: {
                    drawContext2 = drawContext;
                    n6 = n;
                    break;
                }
            }
        }
        ItemOutline.glow(drawContext2, n6, n2, n4, f);
    }

    public static float pulse(double d) {
        double d2 = Math.max(0.0, d);
        double d3 = (double)System.currentTimeMillis() * d2 / 400.0;
        int n = ItemHighlights$Style.a;
        float f = 0.6f + 0.4f * (0.5f + 0.5f * (float)Math.sin(d3));
        if (Module.a) {
            ItemHighlights$Style.a = ++n;
        }
        return f;
    }

    private static void border(DrawContext drawContext, int n, int n2, int n3, float f) {
        int n4 = Math.round(255.0f * f) << 24 | n3;
        drawContext.method_25294(n - 1, n2 - 1, n + 17, n2, n4);
        drawContext.method_25294(n - 1, n2 + 16, n + 17, n2 + 17, n4);
        drawContext.method_25294(n - 1, n2, n, n2 + 16, n4);
        drawContext.method_25294(n + 16, n2, n + 17, n2 + 16, n4);
    }

    private static void glow(DrawContext drawContext, int n, int n2, int n3, float f) {
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        DrawContext drawContext2;
        block4: {
            int n9 = 4;
            int n10 = ItemHighlights$Style.a;
            while (n9 >= 1) {
                int n11 = Math.round((float)Math.max(0, 150 - n9 * 32) * f);
                drawContext2 = drawContext;
                n8 = n - n9;
                n7 = n2 - n9;
                n6 = n + 16 + n9;
                n5 = n2 + 16;
                n4 = n9--;
                if (n10 == 0) {
                    ItemOutline.ring(drawContext2, n8, n7, n6, n5 + n4, n11 << 24 | n3);
                    if (n10 == 0) continue;
                    break;
                }
                break block4;
            }
            drawContext2 = drawContext;
            n8 = n - 1;
            n7 = n2 - 1;
            n6 = n + 17;
            n5 = n2 + 17;
            n4 = Math.round(255.0f * f) << 24 | n3;
        }
        ItemOutline.ring(drawContext2, n8, n7, n6, n5, n4);
    }

    private static void corners(DrawContext drawContext, int n, int n2, int n3, float f) {
        int n4 = Math.round(255.0f * f) << 24 | n3;
        int n5 = 5;
        int n6 = 1;
        int n7 = n - 1;
        int n8 = n2 - 1;
        int n9 = n + 17;
        int n10 = n2 + 17;
        drawContext.method_25294(n7, n8, n7 + n5, n8 + n6, n4);
        drawContext.method_25294(n7, n8, n7 + n6, n8 + n5, n4);
        drawContext.method_25294(n9 - n5, n8, n9, n8 + n6, n4);
        drawContext.method_25294(n9 - n6, n8, n9, n8 + n5, n4);
        drawContext.method_25294(n7, n10 - n6, n7 + n5, n10, n4);
        drawContext.method_25294(n7, n10 - n5, n7 + n6, n10, n4);
        drawContext.method_25294(n9 - n5, n10 - n6, n9, n10, n4);
        drawContext.method_25294(n9 - n6, n10 - n5, n9, n10, n4);
    }

    private static void ring(DrawContext drawContext, int n, int n2, int n3, int n4, int n5) {
        drawContext.method_25294(n, n2, n3, n2 + 1, n5);
        drawContext.method_25294(n, n4 - 1, n3, n4, n5);
        drawContext.method_25294(n, n2, n + 1, n4, n5);
        drawContext.method_25294(n3 - 1, n2, n3, n4, n5);
    }
}
