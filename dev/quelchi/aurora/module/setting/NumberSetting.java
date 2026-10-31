package dev.quelchi.aurora.module.setting;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.setting.Setting;

public class NumberSetting
extends Setting {
    private final double min;
    private final double max;
    private final double increment;
    private double value;
    private final double defaultValue;
    public static boolean b;

    public NumberSetting(String string, double d, double d2, double d3, double d4) {
        super(string);
        this.min = d;
        this.max = d2;
        this.increment = d4;
        this.value = d3;
        this.defaultValue = d3;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public double getIncrement() {
        return this.increment;
    }

    public double getValue() {
        return this.value;
    }

    public int getValueInt() {
        return (int)Math.round(this.value);
    }

    public float getValueFloat() {
        return (float)this.value;
    }

    public void setValue(double d) {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    double d2 = Math.max(this.min, Math.min(this.max, d));
                    double d3 = 1.0 / this.increment;
                    boolean bl2 = Setting.a;
                    this.value = (double)Math.round(d2 * d3) / d3;
                    if (!Module.a) break block4;
                    if (!bl2) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            Setting.a = bl;
        }
    }

    public void setExact(double d) {
        double d2;
        block6: {
            double d3;
            block4: {
                double d4;
                block5: {
                    d4 = Math.max(this.min, Math.min(this.max, d));
                    boolean bl = Setting.a;
                    NumberSetting numberSetting = this;
                    d3 = this.increment;
                    if (bl) break block4;
                    if (!(d3 >= 1.0)) break block5;
                    d2 = Math.round(d4);
                    break block6;
                }
                d3 = Math.round(d4 * 100.0);
            }
            d2 = d3 / 100.0;
        }
        numberSetting.value = d2;
    }

    public double getFraction() {
        double d;
        block4: {
            block5: {
                boolean bl = Setting.a;
                d = this.max;
                if (bl) break block4;
                if (d != this.min) break block5;
                return 0.0;
            }
            d = (this.value - this.min) / (this.max - this.min);
        }
        return d;
    }

    @Override
    public void reset() {
        this.setValue(this.defaultValue);
    }
}
