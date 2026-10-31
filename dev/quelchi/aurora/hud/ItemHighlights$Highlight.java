package dev.quelchi.aurora.hud;

import dev.quelchi.aurora.hud.ItemHighlights$Style;

public record ItemHighlights$Highlight(ItemHighlights$Style style, int rgb, double pulse) {
    public static final ItemHighlights$Highlight DEFAULT;

    
    static {
        long l = 5728059432706887905L;
        long l2 = 0xBC16B74D9A2E3A8BL ^ l;
        DEFAULT = new ItemHighlights$Highlight(ItemHighlights$Style.Solid, (int)l2, 1.0);
    }
}
