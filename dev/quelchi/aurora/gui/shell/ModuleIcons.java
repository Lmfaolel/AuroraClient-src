package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.module.Module;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class ModuleIcons {
    private static final Map<String, Boolean> PRESENT = new HashMap<String, Boolean>();

    private ModuleIcons() {
    }

    public static String slug(Module module) {
        return module.getName().toLowerCase(Locale.ROOT).replace(' ', '_');
    }
}
