package dev.quelchi.aurora.module;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.ExtraHuds;
import dev.quelchi.aurora.module.setting.KeybindSetting;
import dev.quelchi.aurora.module.setting.Setting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public abstract class Module {
    protected final MinecraftClient mc = MinecraftClient.method_1551();
    private final String name;
    private final String description;
    private final Category category;
    private final List<Setting> settings = new ArrayList<Setting>();
    private final KeybindSetting toggleKey;
    private boolean enabled;
    public static boolean a;
    private static final String c;

    protected Module(String string, String string2, Category category, boolean bl) {
        this.name = string;
        this.description = string2;
        this.category = category;
        this.enabled = bl;
        this.toggleKey = new KeybindSetting(c, -1);
        this.addSettings(this.toggleKey);
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onTick() {
    }

    public void onHudRender(DrawContext drawContext) {
    }

    public void onSettingsChanged() {
    }

    public void toggle() {
        boolean bl;
        Module module;
        block2: {
            block3: {
                int n = Category.a;
                module = this;
                bl = this.enabled;
                if (n != 0) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        module.setEnabled(bl);
    }

    public void setEnabled(boolean bl) {
        block14: {
            Module module;
            block12: {
                block13: {
                    int n;
                    block10: {
                        block11: {
                            n = Category.a;
                            module = this;
                            if (n != 0) break block10;
                            if (module.enabled != bl) break block11;
                            return;
                        }
                        module = this;
                    }
                    if (n != 0) break block12;
                    module.enabled = bl;
                    if (!bl) break block13;
                    this.onEnable();
                    if (n == 0) break block14;
                }
                module = this;
            }
            module.onDisable();
        }
    }

    
    public void resetDefaults(String var1_1) {
        this.resetHudDefaults(var1_1);
        var3_2 = Collections.newSetFromMap(new IdentityHashMap<K, V>());
        var2_3 = Category.a;
        var4_4 = this.settings.iterator();
        while (var4_4.hasNext()) {
            v0 = var4_4.next();
            if (var2_3 == 0) {
                var5_5 = v0;
                var3_2.addAll(var5_5.getChildren());
                if (var2_3 == 0) continue;
            }
        }
        var4_4 = this.settings.iterator();
        while (true) {
            block14: {
                block13: {
                    if (!var4_4.hasNext()) break;
                    v0 = var4_4.next();
lbl21:

                    var5_5 = v0;
                    if (var3_2.contains(var5_5)) {
                        continue;
                    }
                    v3 = var1_1;
                    if (var2_3 != 0) break block13;
                    if (v3 == null) break block14;
                    v3 = var1_1;
                }
                if (!v3.equals(var5_5.getTab())) {
                    continue;
                }
            }
            Module.resetDeep(var5_5);
            if (var2_3 != 0) break;
        }
    }

    public static void resetDeep(Setting setting) {
        setting.reset();
        Iterator<Setting> iterator = setting.getChildren().iterator();
        int n = Category.a;
        while (iterator.hasNext()) {
            Setting setting2 = iterator.next();
            Module.resetDeep(setting2);
            if (n == 0) continue;
        }
    }

    protected void resetHudDefaults(String string) {
        block8: {
            Module module;
            Object object;
            int n;
            block9: {
                Object object2;
                block6: {
                    block7: {
                        n = Category.a;
                        object2 = this;
                        if (n != 0) break block6;
                        if (!(object2 instanceof DraggableHud)) break block7;
                        object = (DraggableHud)object2;
                        object.resetHudPos();
                    }
                    module = this;
                    if (n != 0) break block9;
                    object2 = module;
                }
                if (!(object2 instanceof ExtraHuds)) break block8;
                module = object2;
            }
            object = (ExtraHuds)((Object)module);
            for (DraggableHud draggableHud : object.extraHuds()) {
                draggableHud.resetHudPos();
                if (n == 0) continue;
            }
        }
    }

    public void writeExtra(JsonObject jsonObject) {
    }

    public void readExtra(JsonObject jsonObject) {
    }

    protected void addSettings(Setting ... settingArray) {
        this.settings.addAll(Arrays.asList(settingArray));
    }

    public List<Setting> getSettings() {
        return this.settings;
    }

    public KeybindSetting getToggleKey() {
        return this.toggleKey;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "\u0006%iT\f\fp\u0019/w".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 82;
                    case 1 -> 74;
                    case 2 -> 14;
                    case 3 -> 51;
                    case 4 -> 96;
                    case 5 -> 105;
                    default -> 80;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        c = new String(cArray).intern();
    }
}
