package dev.quelchi.aurora.module.setting;

import dev.quelchi.aurora.module.setting.Setting;
import java.util.List;
import java.util.function.Supplier;

public class ChoiceSetting
extends Setting {
    private final Supplier<List<String>> options;
    private final String defaultValue;
    private String value;

    public ChoiceSetting(String string, String string2, Supplier<List<String>> supplier) {
        super(string);
        this.options = supplier;
        this.value = string2;
        this.defaultValue = string2;
    }

    public List<String> getOptions() {
        return this.options.get();
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String string) {
        this.value = string;
    }

    @Override
    public void reset() {
        this.value = this.defaultValue;
    }
}
