package dev.quelchi.aurora.module.setting;

import dev.quelchi.aurora.module.setting.Setting;

public class EnumSetting<E extends Enum<E>>
extends Setting {
    private final E[] values;
    private final int defaultIndex;
    private int index;

    public EnumSetting(String string, E e) {
        super(string);
        this.values = (Enum[])((Enum)e).getDeclaringClass().getEnumConstants();
        this.defaultIndex = this.index = ((Enum)e).ordinal();
    }

    public E getValue() {
        return this.values[this.index];
    }

    public String getValueName() {
        return ((Enum)this.values[this.index]).name();
    }

    public void setValue(E e) {
        this.index = ((Enum)e).ordinal();
    }

    public void setByName(String string) {
        boolean bl = Setting.a;
        for (int i = 0; i < this.values.length; ++i) {
            block5: {
                EnumSetting enumSetting = this;
                if (bl) break block5;
                if (!((Enum)enumSetting.values[i]).name().equals(string)) continue;
                enumSetting = this;
            }
            enumSetting.index = i;
            return;
        }
    }

    @Override
    public void reset() {
        this.index = this.defaultIndex;
    }
}
