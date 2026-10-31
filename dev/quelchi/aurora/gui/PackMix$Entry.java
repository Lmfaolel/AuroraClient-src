package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PackMix$Look;
import java.util.List;

public record PackMix$Entry(String label, PackMix$Look look, List<String> files) {
    String icon() {
        return this.files.get(0);
    }
}
