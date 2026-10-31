package dev.quelchi.aurora.hud;

import dev.quelchi.aurora.hud.ItemHighlights$Style;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public record ItemHighlights$Key(Identifier item, Identifier enchantment) {
    public static ItemHighlights$Key of(Item item) {
        return new ItemHighlights$Key(Registries.field_41178.method_10221((Object)item), null);
    }

    public ItemHighlights$Key with(Identifier identifier) {
        return new ItemHighlights$Key(this.item, identifier);
    }

    public String encode() {
        Object object;
        block6: {
            ItemHighlights$Key itemHighlights$Key;
            block4: {
                block5: {
                    int n = ItemHighlights$Style.a;
                    itemHighlights$Key = this;
                    if (n != 0) break block4;
                    if (itemHighlights$Key.enchantment != null) break block5;
                    object = this.item.toString();
                    break block6;
                }
                itemHighlights$Key = this;
            }
            object = String.valueOf(itemHighlights$Key.item) + "@" + String.valueOf(this.enchantment);
        }
        return object;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ItemHighlights$Key decode(String string) {
        Identifier identifier;
        Identifier identifier2;
        ItemHighlights$Key itemHighlights$Key;
        ItemHighlights$Key itemHighlights$Key2;
        block19: {
            Identifier identifier3;
            block18: {
                ItemHighlights$Key itemHighlights$Key3;
                String string2;
                block16: {
                    block17: {
                        int n;
                        block15: {
                            n = ItemHighlights$Style.a;
                            string2 = string;
                            if (n != 0) break block15;
                            if (string2 == null) return null;
                            string2 = string;
                        }
                        if (n != 0) break block16;
                        if (!string2.isEmpty()) break block17;
                        return null;
                    }
                    string2 = string;
                }
                int n = string2.indexOf(64);
                String string3 = n < 0 ? string : string.substring(0, n);
                Identifier identifier4 = Identifier.method_12829((String)string3);
                if (identifier4 == null) {
                    return null;
                }
                Identifier identifier5 = n < 0 ? null : Identifier.method_12829((String)string.substring(n + 1));
                identifier3 = identifier5;
                itemHighlights$Key2 = itemHighlights$Key3;
                itemHighlights$Key = itemHighlights$Key3;
                identifier2 = identifier4;
                if (n < 0 || identifier3 != null) break block18;
                identifier = null;
                break block19;
            }
            identifier = identifier3;
        }
        itemHighlights$Key2(identifier2, identifier);
        return itemHighlights$Key;
    }
}
