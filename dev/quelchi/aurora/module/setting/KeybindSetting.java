package dev.quelchi.aurora.module.setting;

import dev.quelchi.aurora.module.setting.Setting;

public class KeybindSetting
extends Setting {
    private int key;
    private final int defaultKey;

    public KeybindSetting(String string, int n) {
        super(string);
        this.key = n;
        this.defaultKey = n;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int n) {
        this.key = n;
    }

    @Override
    public void reset() {
        this.setKey(this.defaultKey);
    }
}
