package dev.quelchi.aurora.glass.style;

import dev.quelchi.aurora.glass.style.GlassStyle;
import dev.quelchi.aurora.glass.ui.Insets;

public record ComponentStyle(GlassStyle glass, int text, int secondaryText, int accent, int hover, int pressed, int disabled, Insets padding, int gap, int animationMs) {
    public ComponentStyle {
        insets = insets == null ? Insets.ZERO : insets;
        n7 = Math.max(0, n7);
        n8 = Math.max(0, n8);
    }
}
