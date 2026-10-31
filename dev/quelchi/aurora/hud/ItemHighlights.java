package dev.quelchi.aurora.hud;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.hud.ItemHighlights$Highlight;
import dev.quelchi.aurora.hud.ItemHighlights$Key;
import dev.quelchi.aurora.hud.ItemHighlights$Style;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public final class ItemHighlights {
    private static final Map<ItemHighlights$Key, ItemHighlights$Highlight> ENTRIES;
    private static final String[] a;

    private ItemHighlights() {
    }

    private static Identifier id(Item item) {
        return Registries.field_41178.method_10221((Object)item);
    }

    public static Map<ItemHighlights$Key, ItemHighlights$Highlight> all() {
        return ENTRIES;
    }

    public static boolean isEmpty() {
        return ENTRIES.isEmpty();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ItemHighlights$Highlight forStack(ItemStack itemStack) {
        boolean bl;
        int n;
        block26: {
            ItemStack itemStack2;
            block25: {
                n = ItemHighlights$Style.a;
                itemStack2 = itemStack;
                if (n != 0) break block25;
                if (itemStack2 == null) return null;
                itemStack2 = itemStack;
            }
            bl = itemStack2.method_7960();
            if (n != 0) break block26;
            if (bl) return null;
            bl = ENTRIES.isEmpty();
        }
        if (bl) {
            return null;
        }
        Identifier identifier = ItemHighlights.id(itemStack.method_7909());
        ItemHighlights$Highlight itemHighlights$Highlight = null;
        ItemEnchantmentsComponent itemEnchantmentsComponent = null;
        for (Map.Entry<ItemHighlights$Key, ItemHighlights$Highlight> entry : ENTRIES.entrySet()) {
            block33: {
                Object object;
                ItemHighlights$Key itemHighlights$Key;
                block31: {
                    block32: {
                        block29: {
                            block30: {
                                ItemHighlights$Key itemHighlights$Key2;
                                block27: {
                                    itemHighlights$Key = entry.getKey();
                                    itemHighlights$Key2 = itemHighlights$Key;
                                    if (n != 0) break block27;
                                    if (!itemHighlights$Key2.item().equals((Object)identifier)) {
                                        continue;
                                    }
                                    itemHighlights$Key2 = itemHighlights$Key;
                                }
                                if (itemHighlights$Key2.enchantment() != null) break block29;
                                ItemHighlights$Highlight itemHighlights$Highlight2 = itemHighlights$Highlight;
                                if (n != 0) break block30;
                                if (itemHighlights$Highlight2 != null) continue;
                                itemHighlights$Highlight2 = itemHighlights$Highlight = entry.getValue();
                            }
                            if (n == 0) continue;
                        }
                        object = itemEnchantmentsComponent;
                        if (n != 0) break block31;
                        if (object != null) break block32;
                        itemEnchantmentsComponent = EnchantmentHelper.method_57532((ItemStack)itemStack);
                    }
                    object = itemEnchantmentsComponent;
                }
                if (n != 0) return (ItemHighlights$Highlight)object;
                if (!ItemHighlights.hasEnchantment(object, itemHighlights$Key.enchantment())) break block33;
                object = entry.getValue();
                return (ItemHighlights$Highlight)object;
            }
            if (n == 0) continue;
        }
        return itemHighlights$Highlight;
    }

    private static boolean hasEnchantment(ItemEnchantmentsComponent itemEnchantmentsComponent, Identifier identifier) {
        boolean bl;
        block5: {
            Iterator iterator = itemEnchantmentsComponent.method_57534().iterator();
            int n = ItemHighlights$Style.a;
            while (iterator.hasNext()) {
                block7: {
                    boolean bl2;
                    block6: {
                        RegistryEntry registryEntry = (RegistryEntry)iterator.next();
                        bl = registryEntry.method_40230().map(RegistryKey::method_29177).filter(arg_0 -> ((Identifier)identifier).equals(arg_0)).isPresent();
                        if (n != 0) break block5;
                        if (n != 0) break block6;
                        if (!bl) break block7;
                        bl2 = true;
                    }
                    return bl2;
                }
                if (n == 0) continue;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean has(Item item) {
        boolean bl;
        block5: {
            Identifier identifier = ItemHighlights.id(item);
            int n = ItemHighlights$Style.a;
            for (ItemHighlights$Key itemHighlights$Key : ENTRIES.keySet()) {
                block7: {
                    boolean bl2;
                    block6: {
                        bl = itemHighlights$Key.item().equals((Object)identifier);
                        if (n != 0) break block5;
                        if (n != 0) break block6;
                        if (!bl) break block7;
                        bl2 = true;
                    }
                    return bl2;
                }
                if (n == 0) continue;
            }
            bl = false;
        }
        return bl;
    }

    public static ItemHighlights$Key add(Item item) {
        ItemHighlights$Key itemHighlights$Key = ItemHighlights$Key.of(item);
        ENTRIES.putIfAbsent(itemHighlights$Key, ItemHighlights$Highlight.DEFAULT);
        return itemHighlights$Key;
    }

    public static void remove(ItemHighlights$Key itemHighlights$Key) {
        ENTRIES.remove(itemHighlights$Key);
    }

    public static ItemHighlights$Highlight get(ItemHighlights$Key itemHighlights$Key) {
        return ENTRIES.getOrDefault(itemHighlights$Key, ItemHighlights$Highlight.DEFAULT);
    }

    public static void set(ItemHighlights$Key itemHighlights$Key, ItemHighlights$Highlight itemHighlights$Highlight) {
        block6: {
            int n = ItemHighlights$Style.a;
            if (itemHighlights$Key == null) break block6;
            ItemHighlights$Highlight itemHighlights$Highlight2 = itemHighlights$Highlight;
            if (n != 0) break block6;
            if (itemHighlights$Highlight2 == null) break block6;
            itemHighlights$Highlight2 = ENTRIES.put(itemHighlights$Key, itemHighlights$Highlight);
        }
    }

    public static ItemHighlights$Key rekey(ItemHighlights$Key itemHighlights$Key, ItemHighlights$Key itemHighlights$Key2) {
        block31: {
            int n;
            block30: {
                ItemHighlights$Key itemHighlights$Key3;
                block28: {
                    block29: {
                        n = ItemHighlights$Style.a;
                        itemHighlights$Key3 = itemHighlights$Key;
                        if (n != 0) break block28;
                        if (itemHighlights$Key3 == null) break block29;
                        itemHighlights$Key3 = itemHighlights$Key2;
                        if (n != 0) break block28;
                        if (itemHighlights$Key3 == null) break block29;
                        itemHighlights$Key3 = itemHighlights$Key;
                        if (n != 0) break block28;
                        if (itemHighlights$Key3.equals(itemHighlights$Key2)) break block29;
                        if (ENTRIES.containsKey(itemHighlights$Key)) break block30;
                    }
                    itemHighlights$Key3 = itemHighlights$Key;
                }
                return itemHighlights$Key3;
            }
            LinkedHashMap<ItemHighlights$Key, ItemHighlights$Highlight> linkedHashMap = new LinkedHashMap<ItemHighlights$Key, ItemHighlights$Highlight>();
            for (Map.Entry<ItemHighlights$Key, ItemHighlights$Highlight> entry : ENTRIES.entrySet()) {
                block34: {
                    Record record;
                    ItemHighlights$Key itemHighlights$Key4;
                    block32: {
                        block33: {
                            if (n != 0) break block31;
                            itemHighlights$Key4 = entry.getKey();
                            if (n != 0) break block32;
                            if (!itemHighlights$Key4.equals(itemHighlights$Key)) break block33;
                            linkedHashMap.put(itemHighlights$Key2, entry.getValue());
                            if (n == 0) break block34;
                        }
                        record = entry.getKey();
                        if (n != 0) break block34;
                        itemHighlights$Key4 = record;
                    }
                    if (!itemHighlights$Key4.equals(itemHighlights$Key2)) {
                        record = linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                if (n == 0) continue;
            }
            ENTRIES.clear();
            ENTRIES.putAll(linkedHashMap);
        }
        return itemHighlights$Key2;
    }

    public static void write(JsonObject jsonObject) {
        String string;
        JsonObject jsonObject2;
        JsonObject jsonObject3;
        block7: {
            int n = ItemHighlights$Style.a;
            if (ENTRIES.isEmpty()) {
                jsonObject.add(a[2], (JsonElement)new JsonObject());
                return;
            }
            jsonObject3 = new JsonObject();
            for (Map.Entry<ItemHighlights$Key, ItemHighlights$Highlight> entry : ENTRIES.entrySet()) {
                ItemHighlights$Highlight itemHighlights$Highlight = entry.getValue();
                JsonObject jsonObject4 = new JsonObject();
                jsonObject4.addProperty(a[4], itemHighlights$Highlight.style().name());
                jsonObject4.addProperty(a[6], (Number)itemHighlights$Highlight.rgb());
                jsonObject2 = jsonObject4;
                string = a[3];
                if (n == 0) {
                    jsonObject2.addProperty(string, (Number)itemHighlights$Highlight.pulse());
                    jsonObject3.add(entry.getKey().encode(), (JsonElement)jsonObject4);
                    if (n == 0) continue;
                    break;
                }
                break block7;
            }
            jsonObject2 = jsonObject;
            string = a[0];
        }
        jsonObject2.add(string, (JsonElement)jsonObject3);
    }

    
    public static void read(JsonObject jsonObject) {
        String[] stringArray;
        JsonObject jsonObject2;
        int n;
        block15: {
            block16: {
                block13: {
                    JsonObject jsonObject3;
                    block14: {
                        block12: {
                            n = ItemHighlights$Style.a;
                            jsonObject3 = jsonObject;
                            if (n != 0) break block12;
                            if (jsonObject3 == null) break block13;
                            jsonObject3 = jsonObject;
                        }
                        if (n != 0) break block14;
                        if (!jsonObject3.has(a[0])) break block13;
                        jsonObject2 = jsonObject;
                        if (n != 0) break block15;
                        jsonObject3 = jsonObject2.get(a[0]);
                    }
                    if (jsonObject3.isJsonObject()) break block16;
                }
                return;
            }
            ENTRIES.clear();
            stringArray = a;
            jsonObject2 = jsonObject.getAsJsonObject(stringArray[0]);
        }
        JsonObject jsonObject4 = jsonObject2;
        for (String string : jsonObject4.keySet()) {
            try {
                JsonObject jsonObject5;
                ItemHighlights$Key itemHighlights$Key = ItemHighlights$Key.decode(string);
                if ((itemHighlights$Key == null || !Registries.field_41178.method_10250(itemHighlights$Key.item())) && n == 0) continue;
                stringArray = a;
                int n2 = jsonObject5.has(stringArray[5]) ? jsonObject5.get(stringArray[5]).getAsInt() : ItemHighlights$Highlight.DEFAULT.rgb();
                stringArray = a;
                jsonObject5 = jsonObject4.getAsJsonObject(string);
                ENTRIES.put(itemHighlights$Key, new ItemHighlights$Highlight(ItemHighlights.styleOf(jsonObject5.has(a[1]) ? jsonObject5.get(a[1]).getAsString() : null), n2, jsonObject5.has(stringArray[7]) ? jsonObject5.get(stringArray[7]).getAsDouble() : ItemHighlights$Highlight.DEFAULT.pulse()));
            }
            catch (Exception exception) {
            }
            if (n == 0) continue;
        }
    }

    private static ItemHighlights$Style styleOf(String string) {
        ItemHighlights$Style itemHighlights$Style;
        block8: {
            int n = ItemHighlights$Style.a;
            if (string == null) {
                return ItemHighlights$Highlight.DEFAULT.style();
            }
            ItemHighlights$Style[] itemHighlights$StyleArray = ItemHighlights$Style.values();
            int n2 = itemHighlights$StyleArray.length;
            int n3 = 0;
            while (n3 < n2) {
                block9: {
                    block10: {
                        itemHighlights$Style = itemHighlights$StyleArray[n3];
                        if (n != 0) break block8;
                        ItemHighlights$Style itemHighlights$Style2 = itemHighlights$Style;
                        if (n != 0) break block9;
                        if (!itemHighlights$Style2.name().equalsIgnoreCase(string)) break block10;
                        return itemHighlights$Style2;
                    }
                    ++n3;
                }
                if (n == 0) continue;
            }
            itemHighlights$Style = ItemHighlights$Highlight.DEFAULT.style();
        }
        return itemHighlights$Style;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[8];
                var3_1 = 0;
                var2_2 = "yyr!7k\u007fydf\u0005bdl%>\nyyr!7k\u007fydf\u0005aey:>\u0005bdl%>\u0003cww";
                var4_3 = "yyr!7k\u007fydf\u0005bdl%>\nyyr!7k\u007fydf\u0005aey:>\u0005bdl%>\u0003cww".length();
                var1_4 = 10;
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
                    var2_2 = "cww\u0005aey:>";
                    var4_3 = "cww\u0005aey:>".length();
                    var1_4 = 3;
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
                        v10 = 17;
                        break;
                    }
                    case 1: {
                        v10 = 16;
                        break;
                    }
                    case 2: {
                        v10 = 21;
                        break;
                    }
                    case 3: {
                        v10 = 73;
                        break;
                    }
                    case 4: {
                        v10 = 91;
                        break;
                    }
                    case 5: {
                        v10 = 2;
                        break;
                    }
                    default: {
                        v10 = 24;
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
        ItemHighlights.a = var5;
        ItemHighlights.ENTRIES = new LinkedHashMap<ItemHighlights$Key, ItemHighlights$Highlight>();
        ItemHighlights.ENTRIES.put(ItemHighlights$Key.of(Items.field_8575), ItemHighlights$Highlight.DEFAULT);
    }
}
