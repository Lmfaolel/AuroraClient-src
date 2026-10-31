package dev.quelchi.aurora.module.setting;

import dev.quelchi.aurora.module.setting.Setting;

public class StringSetting
extends Setting {
    private final int maxLength;
    private String value;
    private final String defaultValue;

    public StringSetting(String string, String string2) {
        this(string, string2, 32);
    }

    public StringSetting(String string, String string2, int n) {
        String string3;
        block2: {
            block3: {
                super(string);
                boolean bl = Setting.a;
                this.maxLength = n;
                StringSetting stringSetting = this;
                string3 = string2;
                if (bl) break block2;
                if (string3 != null) break block3;
                string3 = "";
                break block2;
            }
            string3 = string2;
        }
        stringSetting.value = string3;
        this.defaultValue = this.value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String string) {
        block6: {
            String string2;
            block7: {
                String string3;
                boolean bl;
                block4: {
                    block5: {
                        bl = Setting.a;
                        string3 = string;
                        if (bl) break block4;
                        if (string3 != null) break block5;
                        string3 = "";
                        break block4;
                    }
                    string3 = string;
                }
                string2 = string3;
                if (bl) break block6;
                if (string2.length() <= this.maxLength) break block7;
                string2 = string2.substring(0, this.maxLength);
            }
            this.value = string2;
        }
    }

    public boolean isEmpty() {
        return this.value.isBlank();
    }

    @Override
    public void reset() {
        this.setValue(this.defaultValue);
    }
}
