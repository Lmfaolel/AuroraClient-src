package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.module.setting.Setting;
import java.util.List;

record SearchIndex$Doc(Setting setting, List<Setting> above, String tab, String[] own, String ownPhrase, String[] around, List<String> choices, List<String[]> choiceWords) {
}
