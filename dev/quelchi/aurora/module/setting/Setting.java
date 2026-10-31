package dev.quelchi.aurora.module.setting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class Setting {
    private final String name;
    private Supplier<Boolean> visibleWhen = Setting::lambda$new$0;
    private String tab;
    private final List<Setting> children = new ArrayList<Setting>();
    public static boolean a;

    protected Setting(String string) {
        this.name = string;
    }

    public String getName() {
        return this.name;
    }

    public abstract void reset();

    public String getTab() {
        return this.tab;
    }

    public Setting tab(String string) {
        this.tab = string;
        return this;
    }

    public List<Setting> getChildren() {
        return this.children;
    }

    public boolean hasChildren() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = a;
                bl = this.children.isEmpty();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    public Setting children(Setting ... settingArray) {
        Setting setting;
        block5: {
            Setting[] settingArray2 = settingArray;
            int n = settingArray2.length;
            boolean bl = a;
            int n2 = 0;
            while (n2 < n) {
                block6: {
                    block7: {
                        setting = settingArray2[n2];
                        if (bl) break block5;
                        Setting setting2 = setting;
                        if (bl) break block6;
                        if (setting2 == null) break block7;
                        this.children.add(setting2);
                    }
                    ++n2;
                }
                if (!bl) continue;
            }
            setting = this;
        }
        return setting;
    }

    public Setting visibleWhen(Supplier<Boolean> supplier) {
        this.visibleWhen = supplier;
        return this;
    }

    public boolean isVisible() {
        return this.visibleWhen.get();
    }

    private static Boolean lambda$new$0() {
        return true;
    }
}
