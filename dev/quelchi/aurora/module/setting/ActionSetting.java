package dev.quelchi.aurora.module.setting;

import dev.quelchi.aurora.module.setting.Setting;
import java.util.function.Supplier;

public final class ActionSetting
extends Setting {
    private final Runnable action;
    private final Supplier<String> label;

    public ActionSetting(String string, String string2, Runnable runnable) {
        this(string, () -> ActionSetting.lambda$new$0(string2), runnable);
    }

    public ActionSetting(String string, Supplier<String> supplier, Runnable runnable) {
        super(string);
        this.label = supplier;
        this.action = runnable;
    }

    public String getLabel() {
        String string;
        block8: {
            String string2;
            block9: {
                Object object;
                boolean bl;
                block6: {
                    block7: {
                        bl = Setting.a;
                        object = this;
                        if (bl) break block6;
                        if (((ActionSetting)object).label != null) break block7;
                        return "";
                    }
                    object = this.label.get();
                }
                string2 = (String)object;
                string = string2;
                if (bl) break block8;
                if (string != null) break block9;
                string = "";
                break block8;
            }
            string = string2;
        }
        return string;
    }

    public void run() {
        block5: {
            ActionSetting actionSetting;
            block4: {
                boolean bl = Setting.a;
                actionSetting = this;
                if (bl) break block4;
                if (actionSetting.action == null) break block5;
                actionSetting = this;
            }
            actionSetting.action.run();
        }
    }

    @Override
    public void reset() {
    }

    private static String lambda$new$0(String string) {
        return string;
    }
}
