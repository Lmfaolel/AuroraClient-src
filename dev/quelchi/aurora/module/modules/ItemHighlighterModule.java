package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ItemHighlightEditor;
import dev.quelchi.aurora.hud.ItemHighlights;
import dev.quelchi.aurora.hud.ItemHighlights$Highlight;
import dev.quelchi.aurora.hud.ItemOutline;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.UnscaledHud;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import dev.quelchi.aurora.module.setting.ActionSetting;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

public final class ItemHighlighterModule
extends Module
implements UnscaledHud {
    private static ItemHighlighterModule instance;
    private final ActionSetting editor;
    private final BooleanSetting inInventories;
    private final BooleanSetting inHotbar;
    private static final String[] b;

    public ItemHighlighterModule() {
        String[] stringArray = b;
        super(stringArray[2], stringArray[7], Category.HUD, true);
        this.editor = new ActionSetting(stringArray[6], ItemHighlighterModule::lambda$new$0, ItemHighlighterModule::lambda$new$1);
        this.inInventories = new BooleanSetting(stringArray[1], true);
        this.inHotbar = new BooleanSetting(stringArray[3], true);
        instance = this;
        this.addSettings(this.editor, this.inInventories, this.inHotbar);
    }

    public static boolean inInventories() {
        boolean bl;
        block11: {
            block9: {
                ItemHighlighterModule itemHighlighterModule;
                boolean bl2;
                block10: {
                    ItemHighlighterModule itemHighlighterModule2;
                    block8: {
                        itemHighlighterModule2 = instance;
                        bl2 = AmbienceModule.b;
                        itemHighlighterModule = itemHighlighterModule2;
                        if (bl2) break block8;
                        if (itemHighlighterModule == null) break block9;
                        itemHighlighterModule = itemHighlighterModule2;
                    }
                    if (bl2) break block10;
                    if (!itemHighlighterModule.isEnabled()) break block9;
                    itemHighlighterModule = itemHighlighterModule2;
                }
                bl = itemHighlighterModule.inInventories.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    
    @Override
    public void onHudRender(DrawContext drawContext) {
        MinecraftClient minecraftClient;
        boolean bl = AmbienceModule.b;
        boolean bl2 = this.inHotbar.getValue();
        if (!bl) {
            if (!bl2) return;
            bl2 = ItemHighlights.isEmpty();
        }
        if (bl2) {
            return;
        }
        MinecraftClient minecraftClient2 = minecraftClient = MinecraftClient.method_1551();
        if (!bl) {
            if (minecraftClient2.field_1724 == null) return;
            minecraftClient2 = minecraftClient;
        }
        int n = minecraftClient2.field_1690.field_1842;
        if (!bl) {
            if (n != 0) {
                return;
            }
            n = GuiTweaksModule.partHidden(GuiTweaksModule$Key.HOTBAR);
        }
        if (!bl) {
            if (n != 0) {
                return;
            }
            n = drawContext.method_51421() / 2 - 88;
        }
        int n2 = n;
        int n3 = drawContext.method_51443() - 19;
        GuiTweaksModule.pushOverlay(drawContext, GuiTweaksModule$Key.HOTBAR);
        try {
            int n4 = 0;
            do {
                if (n4 >= 9) return;
                ItemHighlights$Highlight itemHighlights$Highlight = ItemHighlights.forStack(minecraftClient.field_1724.method_31548().method_5438(n4));
                if (bl) return;
                if (bl) continue;
                if (itemHighlights$Highlight != null) {
                    ItemOutline.draw(drawContext, n2 + n4 * 20, n3, itemHighlights$Highlight);
                }
                ++n4;
            } while (!bl);
            return;
        }
        finally {
            GuiTweaksModule.popOverlay(drawContext);
        }
    }

    @Override
    public void writeExtra(JsonObject jsonObject) {
        ItemHighlights.write(jsonObject);
    }

    @Override
    public void readExtra(JsonObject jsonObject) {
        block3: {
            JsonObject jsonObject2;
            block2: {
                boolean bl = AmbienceModule.b;
                jsonObject2 = jsonObject;
                if (bl) break block2;
                if (jsonObject2 == null) break block3;
                jsonObject2 = jsonObject;
            }
            ItemHighlights.read(jsonObject2);
        }
    }

    private static void lambda$new$1() {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (Im.available()) {
            minecraftClient.method_1507((Screen)new ItemHighlightEditor());
        }
    }

    private static String lambda$new$0() {
        Object object;
        block8: {
            int n;
            block6: {
                block7: {
                    boolean bl = AmbienceModule.b;
                    n = ItemHighlights.isEmpty();
                    if (bl) break block6;
                    if (n == 0) break block7;
                    object = b[5];
                    break block8;
                }
                n = ItemHighlights.all().size();
            }
            String string = ItemHighlights.all().size() == 1 ? b[0] : b[4];
            object = n + string;
        }
        return object;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[8];
                var3_1 = 0;
                var2_2 = "%{2\u0013R\u000eL|f\u001fQ=\u0017kf)\u0004V.\u0001\u0010Lf#\u001b\u001f\u0003\u001bbz*\u001fX#\u0006``\tL|f\u001eP?\u0010d`\u0006%{2\u0013R8\u0005@\u007f6\u0002F";
                var4_3 = "%{2\u0013R\u000eL|f\u001fQ=\u0017kf)\u0004V.\u0001\u0010Lf#\u001b\u001f\u0003\u001bbz*\u001fX#\u0006``\tL|f\u001eP?\u0010d`\u0006%{2\u0013R8\u0005@\u007f6\u0002F".length();
                var1_4 = 5;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "M{!\u001eS\"\u0015mf52Jg2\u001aV%\u0017%q.\u0019L.\u001c%{2\u0013R8Rl|f\u001fQ=\u0017kf)\u0004V.\u0001%s(\u0012\u001f?\u001a`2.\u0019K)\u0013w";
                    var4_3 = "M{!\u001eS\"\u0015mf52Jg2\u001aV%\u0017%q.\u0019L.\u001c%{2\u0013R8Rl|f\u001fQ=\u0017kf)\u0004V.\u0001%s(\u0012\u001f?\u001a`2.\u0019K)\u0013w".length();
                    var1_4 = 10;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 5;
                        break;
                    }
                    case 1: {
                        v10 = 18;
                        break;
                    }
                    case 2: {
                        v10 = 70;
                        break;
                    }
                    case 3: {
                        v10 = 118;
                        break;
                    }
                    case 4: {
                        v10 = 63;
                        break;
                    }
                    case 5: {
                        v10 = 75;
                        break;
                    }
                    default: {
                        v10 = 114;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_6;
lbl65:

                v7 = v7;
            } while (v7 > var6_6);
            v3 = new String(v6);
            switch (v2) {
                default: {
                    continue;
                }
                case 0:
lbl73:

                continue;
            }
        }
        ItemHighlighterModule.b = var5;
    }
}
