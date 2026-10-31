package dev.quelchi.aurora.hud;

import dev.quelchi.aurora.hud.ItemHighlights$Style;

class ItemOutline$1 {
    static final int[] $SwitchMap$dev$quelchi$aurora$hud$ItemHighlights$Style = new int[ItemHighlights$Style.values().length];

    static {
        try {
            ItemOutline$1.$SwitchMap$dev$quelchi$aurora$hud$ItemHighlights$Style[ItemHighlights$Style.Border.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
        try {
            ItemOutline$1.$SwitchMap$dev$quelchi$aurora$hud$ItemHighlights$Style[ItemHighlights$Style.Corners.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
        try {
            ItemOutline$1.$SwitchMap$dev$quelchi$aurora$hud$ItemHighlights$Style[ItemHighlights$Style.Solid.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
    }
}
