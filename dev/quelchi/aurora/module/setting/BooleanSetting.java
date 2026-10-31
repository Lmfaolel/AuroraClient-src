package dev.quelchi.aurora.module.setting;

import dev.quelchi.aurora.module.setting.Setting;

public class BooleanSetting
extends Setting {
    private boolean value;
    private final boolean defaultValue;
    public static int b;

    public BooleanSetting(String string, boolean bl) {
        super(string);
        this.value = bl;
        this.defaultValue = bl;
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(boolean bl) {
        this.value = bl;
    }

    public void toggle() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = Setting.a;
                BooleanSetting booleanSetting = this;
                bl = this.value;
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        booleanSetting.value = bl;
    }

    @Override
    public void reset() {
        this.setValue(this.defaultValue);
    }
}
