package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.gui.shell.SearchIndex$Span;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.setting.Setting;
import java.util.List;

public record SearchIndex$Result(Module module, int score, Setting setting, List<List<SearchIndex$Span>> why, int more) {
    static SearchIndex$Result of(Module module) {
        return new SearchIndex$Result(module, 0, null, List.of(), 0);
    }
}
