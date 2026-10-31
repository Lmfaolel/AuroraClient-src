package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.ArmorRenderContext$State;

public final class ArmorRenderContext {
    private static final ThreadLocal<ArmorRenderContext$State> CURRENT = new ThreadLocal();

    private ArmorRenderContext() {
    }

    public static void set(boolean bl, boolean bl2) {
        CURRENT.set(new ArmorRenderContext$State(bl, bl2));
    }

    public static void clear() {
        CURRENT.remove();
    }

    public static ArmorRenderContext$State get() {
        return CURRENT.get();
    }
}
