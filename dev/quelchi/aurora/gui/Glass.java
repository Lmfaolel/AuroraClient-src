package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.Glass$Kind;
import net.minecraft.client.gui.DrawContext;

public final class Glass {
    public static final Glass$Kind WINDOW = Glass$Kind.WINDOW;
    public static final Glass$Kind CARD = Glass$Kind.CARD;
    public static final Glass$Kind CARD_ON = Glass$Kind.CARD_ON;
    public static final Glass$Kind CARD_OFF = Glass$Kind.CARD_OFF;
    public static final Glass$Kind WELL = Glass$Kind.WELL;
    public static final Glass$Kind LIVE = Glass$Kind.LIVE;
    public static final Glass$Kind POPOVER = Glass$Kind.POPOVER;

    private Glass() {
    }

    public static void mark(DrawContext drawContext) {
    }
}
