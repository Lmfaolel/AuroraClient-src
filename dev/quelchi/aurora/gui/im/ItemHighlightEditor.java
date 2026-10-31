package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.gui.im.Edit;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.ItemHighlightEditor$Icon;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.hud.ItemHighlights;
import dev.quelchi.aurora.hud.ItemHighlights$Highlight;
import dev.quelchi.aurora.hud.ItemHighlights$Key;
import dev.quelchi.aurora.hud.ItemHighlights$Style;
import dev.quelchi.aurora.hud.ItemOutline;
import imgui.ImDrawList;
import imgui.ImGui;
import imgui.type.ImString;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.joml.Matrix3x2fStack;

public final class ItemHighlightEditor
extends ImScreen {
    private static final int[] SWATCHES;
    private static final String[] STYLES;
    private ItemHighlights$Key selected;
    private boolean adding;
    private boolean focus;
    private final ImString search;
    private String asked;
    private List<Item> found;
    private final List<ItemHighlightEditor$Icon> icons;
    private float clipY;
    private float clipB;
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;

    public ItemHighlightEditor() {
        block5: {
            ItemHighlights$Key itemHighlights$Key;
            Object object;
            block4: {
                super(b[9]);
                this.search = new ImString(32);
                this.found = List.of();
                boolean bl = ImScreen.a;
                this.icons = new ArrayList<ItemHighlightEditor$Icon>();
                Iterator<ItemHighlights$Key> iterator = ItemHighlights.all().keySet().iterator();
                object = iterator;
                if (bl) break block4;
                if (!object.hasNext()) break block5;
                object = iterator.next();
            }
            this.selected = itemHighlights$Key = (ItemHighlights$Key)object;
        }
    }

    
    @Override
    protected boolean key(int n) {
        block11: {
            boolean bl;
            block10: {
                ItemHighlightEditor itemHighlightEditor;
                boolean bl2;
                block9: {
                    bl = ImScreen.a;
                    int n2 = n;
                    if (bl) return n2 != 0;
                    if (n2 != 256) return 0 != 0;
                    bl2 = Im.typing();
                    if (bl) break block9;
                    if (bl2) {
                        return 0 != 0;
                    }
                    itemHighlightEditor = this;
                    if (bl) break block10;
                    bl2 = itemHighlightEditor.adding;
                }
                if (!bl2) break block11;
                itemHighlightEditor = this;
            }
            itemHighlightEditor.adding = false;
            if (!bl) return true;
        }
        Edit.back();
        return true;
    }

    public void method_25394(DrawContext drawContext, int n, int n2, float f) {
        block10: {
            int n3;
            int n4;
            int n5;
            DrawContext drawContext2;
            boolean bl;
            block8: {
                block9: {
                    bl = ImScreen.a;
                    drawContext2 = drawContext;
                    n5 = 0;
                    n4 = 0;
                    n3 = this.field_22789;
                    if (bl) break block8;
                    drawContext2.method_25294(n5, n4, n3, this.field_22790, ItemHighlightEditor.a(27545, 6796224884995888961L));
                    if (!(Menu.w <= 0.0f)) break block9;
                    return;
                }
                drawContext.method_25294((int)Math.floor(Edit.gui(Menu.x + 144.0f)) - 1, (int)Math.floor(Edit.gui(Menu.y + 56.0f)) - 1, (int)Math.ceil(Edit.gui(Menu.x + Menu.w - 14.0f)) + 1, Math.round(Edit.gui(Menu.y + Menu.h)), ItemHighlightEditor.a(13596, 5387121555152390607L));
                drawContext2 = drawContext;
                n5 = 0;
                n4 = (int)Math.ceil(Edit.gui(this.clipY));
                n3 = this.field_22789;
            }
            drawContext2.method_44379(n5, n4, n3, (int)Math.floor(Edit.gui(this.clipB)));
            float f2 = Edit.gui(20.0f) / 16.0f;
            Matrix3x2fStack matrix3x2fStack = drawContext.method_51448();
            for (ItemHighlightEditor$Icon itemHighlightEditor$Icon : this.icons) {
                if (!bl) {
                    matrix3x2fStack.pushMatrix();
                    matrix3x2fStack.translate(Edit.gui(itemHighlightEditor$Icon.x()), Edit.gui(itemHighlightEditor$Icon.y()));
                    matrix3x2fStack.scale(f2, f2);
                    drawContext.method_51427(itemHighlightEditor$Icon.stack(), 0, 0);
                    ItemOutline.draw(drawContext, 0, 0, itemHighlightEditor$Icon.look());
                    matrix3x2fStack.popMatrix();
                    if (!bl) continue;
                    break;
                }
                break block10;
            }
            drawContext.method_44380();
        }
    }

    @Override
    protected void draw() {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        block8: {
            ItemHighlightEditor itemHighlightEditor;
            float f6;
            float f7;
            block6: {
                block7: {
                    String[] stringArray = b;
                    Menu.begin(stringArray[2], 144.0f);
                    Edit.bar(stringArray[4], stringArray[33]);
                    f5 = Menu.x + 144.0f;
                    f4 = Menu.y + 56.0f;
                    f3 = Menu.w - 144.0f;
                    f2 = Menu.h - 56.0f;
                    f = Math.min(378.0f, f3 * 0.45f);
                    f7 = f5 + 20.0f;
                    boolean bl = ImScreen.a;
                    f6 = f3 - f - 40.0f;
                    itemHighlightEditor = this;
                    if (bl) break block6;
                    itemHighlightEditor.icons.clear();
                    if (!this.adding) break block7;
                    this.search(f7, f4 + 20.0f, f6, f4 + f2);
                    if (!bl) break block8;
                }
                itemHighlightEditor = this;
            }
            itemHighlightEditor.list(f7, f4 + 20.0f, f6, f4 + f2);
        }
        this.pane(f5 + f3 - f, f4, f, f2);
        Menu.end();
    }

    private void list(float f, float f2, float f3, float f4) {
        block23: {
            ArrayList<ItemHighlights$Key> arrayList;
            float f5;
            boolean bl;
            block20: {
                block21: {
                    bl = ImScreen.a;
                    if (Edit.add(b[0], f, f2, f3)) {
                        this.focus = true;
                        this.adding = true;
                        this.search.set("");
                        this.asked = null;
                    }
                    f5 = f2 + 46.0f + 8.0f;
                    arrayList = new ArrayList<ItemHighlights$Key>(ItemHighlights.all().keySet());
                    if (bl) break block20;
                    if (!arrayList.isEmpty()) break block21;
                    Ui.text(Im.sub, f + 2.0f, f5 + 8.0f, Im.MUTE, b[3]);
                    return;
                }
                this.clipY = f5;
                this.clipB = f4;
                String[] stringArray = b;
                Edit.list(stringArray[5], f, f5, f3, f4 - f5);
            }
            ImDrawList imDrawList = ImGui.getWindowDrawList();
            float f6 = ImGui.getCursorScreenPosY();
            int n = 0;
            while (n < arrayList.size()) {
                block25: {
                    float f7;
                    block26: {
                        ItemHighlights$Key itemHighlights$Key;
                        block27: {
                            block22: {
                                block24: {
                                    itemHighlights$Key = (ItemHighlights$Key)arrayList.get(n);
                                    if (bl) break block22;
                                    ImGui.pushID((int)n);
                                    if (bl) break block23;
                                    if (!Edit.row(b[15], f, f6, f3, 46.0f, itemHighlights$Key.equals(this.selected))) break block24;
                                    this.selected = itemHighlights$Key;
                                }
                                ImGui.popID();
                            }
                            f7 = f6 + 46.0f;
                            if (bl) break block25;
                            if (f7 < f5) break block26;
                            if (!(f6 > f4)) break block27;
                            break block26;
                        }
                        ItemHighlights$Highlight itemHighlights$Highlight = ItemHighlights.get(itemHighlights$Key);
                        Ui.fill(imDrawList, f + 12.0f, f6 + 9.0f, f + 40.0f, f6 + 37.0f, Ui.wash(0.05f), 7.0f);
                        this.icons.add(new ItemHighlightEditor$Icon(new ItemStack((ItemConvertible)Registries.field_41178.method_63535(itemHighlights$Key.item())), f + 16.0f, f6 + 13.0f, itemHighlights$Highlight));
                        imDrawList.pushClipRect(f, f6, f + f3 - 44.0f, f6 + 46.0f, true);
                        Ui.text(Im.sora13, f + 52.0f, Ui.mid(Im.sora13, f6, 46.0f), Im.INK, ItemHighlightEditor.label(itemHighlights$Key));
                        imDrawList.popClipRect();
                        Ui.fill(imDrawList, f + f3 - 32.0f, f6 + 13.0f, f + f3 - 12.0f, f6 + 33.0f, Edit.u32(itemHighlights$Highlight.rgb()), 4.0f);
                        Ui.rect(imDrawList, f + f3 - 32.0f, f6 + 13.0f, f + f3 - 12.0f, f6 + 33.0f, Ui.wash(0.16f), 4.0f);
                    }
                    ++n;
                    f7 = f6 = f6 + 50.0f;
                }
                if (!bl) continue;
            }
            Edit.endList(f, f6);
        }
    }

    private void search(float f, float f2, float f3, float f4) {
        block27: {
            float f5;
            boolean bl;
            String[] stringArray;
            block25: {
                block26: {
                    block24: {
                        String string;
                        block23: {
                            String string2;
                            block21: {
                                block22: {
                                    stringArray = b;
                                    float f6 = Edit.buttonWidth(stringArray[28], false);
                                    bl = ImScreen.a;
                                    string2 = stringArray[30];
                                    if (bl) break block21;
                                    Edit.field(string2, stringArray[26], this.search, f, f2, f3 - f6 - 8.0f, this.focus);
                                    this.focus = false;
                                    if (!Edit.button(stringArray[23], null, f + f3, f2 + 3.0f, false)) break block22;
                                    this.adding = false;
                                }
                                string2 = this.search.get().trim().toLowerCase(Locale.ROOT);
                            }
                            string = string2;
                            if (bl) break block23;
                            if (string.equals(this.asked)) break block24;
                            this.asked = string;
                        }
                        this.found = ItemHighlightEditor.find(string);
                    }
                    f5 = f2 + 42.0f;
                    if (bl) break block25;
                    if (!this.found.isEmpty()) break block26;
                    Ui.text(Im.sub, f + 2.0f, f5 + 8.0f, Im.MUTE, b[25]);
                    return;
                }
                this.clipY = f5;
                this.clipB = f4;
                stringArray = b;
                Edit.list(stringArray[1], f, f5, f3, f4 - f5);
            }
            ImDrawList imDrawList = ImGui.getWindowDrawList();
            float f7 = ImGui.getCursorScreenPosY();
            int n = Math.max(0, (int)(ImGui.getScrollY() / 34.0f));
            int n2 = Math.min(this.found.size(), n + (int)((f4 - f5) / 34.0f) + 2);
            for (int i = n; i < n2; ++i) {
                boolean bl2;
                Item item;
                block28: {
                    boolean bl3;
                    block29: {
                        item = this.found.get(i);
                        float f8 = f7 + (float)i * 34.0f;
                        ImGui.pushID((int)i);
                        stringArray = b;
                        bl3 = Edit.row(stringArray[13], f, f8, f3, 34.0f, false);
                        if (bl) break block27;
                        ImGui.popID();
                        bl2 = this.icons.add(new ItemHighlightEditor$Icon(new ItemStack((ItemConvertible)item), f + 10.0f, f8 + 7.0f, null));
                        if (bl) break block28;
                        Ui.text(Im.sora12r, f + 40.0f, Ui.mid(Im.sora12r, f8, 34.0f), Im.INK, item.method_63680().getString());
                        if (!ItemHighlights.has(item)) break block29;
                        Ui.text(Im.label, f + f3 - 14.0f - Ui.width(Im.label, b[24]), Ui.mid(Im.label, f8, 34.0f), Im.MUTE, b[34]);
                    }
                    bl2 = bl3;
                }
                if (!bl2) continue;
                this.selected = ItemHighlights.add(item);
                ConfigManager.save();
                this.adding = false;
                continue;
            }
            Edit.endList(f, f7 + (float)this.found.size() * 34.0f);
        }
    }

    private static List<Item> find(String string) {
        ArrayList<Item> arrayList = new ArrayList<Item>();
        boolean bl = ImScreen.a;
        for (Item item : Registries.field_41178) {
            block27: {
                int n;
                block26: {
                    String string2;
                    block24: {
                        block25: {
                            if (item == Items.field_8162) {
                                continue;
                            }
                            string2 = string;
                            if (bl) break block24;
                            if (string2.isEmpty()) break block25;
                            string2 = item.method_63680().getString().toLowerCase(Locale.ROOT);
                            if (bl) break block24;
                            if (string2.contains(string)) break block25;
                            string2 = Registries.field_41178.method_10221((Object)item).toString();
                            if (bl) break block24;
                            if (!string2.contains(string)) {
                                continue;
                            }
                        }
                        n = arrayList.add(item) ? 1 : 0;
                        if (bl) break block26;
                        string2 = string;
                    }
                    if (string2.isEmpty()) break block27;
                    n = arrayList.size();
                }
                if (n >= 300) {
                    break;
                }
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    private void pane(float f, float f2, float f3, float f4) {
        Object object;
        int n;
        boolean bl;
        block66: {
            float f5;
            float f6;
            float f7;
            block67: {
                block70: {
                    block71: {
                        Identifier identifier;
                        ItemHighlights$Key itemHighlights$Key;
                        block69: {
                            int n2;
                            List<Identifier> list;
                            block68: {
                                int n3;
                                String string;
                                String[] stringArray;
                                String[] stringArray2;
                                float f8;
                                block63: {
                                    block65: {
                                        List<Identifier> list2;
                                        block64: {
                                            block62: {
                                                String string2;
                                                block61: {
                                                    ItemHighlights$Key itemHighlights$Key2;
                                                    block60: {
                                                        block59: {
                                                            float f9;
                                                            block57: {
                                                                block58: {
                                                                    ItemHighlights$Highlight itemHighlights$Highlight;
                                                                    block55: {
                                                                        block56: {
                                                                            float f10;
                                                                            String string3;
                                                                            block53: {
                                                                                block54: {
                                                                                    ImDrawList imDrawList;
                                                                                    block52: {
                                                                                        block50: {
                                                                                            block51: {
                                                                                                imDrawList = ImGui.getWindowDrawList();
                                                                                                imDrawList.addLine(f, f2, f, f2 + f4, Ui.col(Im.LINE));
                                                                                                f7 = f + 14.0f;
                                                                                                bl = ImScreen.a;
                                                                                                f6 = f3 - 28.0f;
                                                                                                f8 = f7 + f6 - 12.0f;
                                                                                                if (bl) break block50;
                                                                                                if (this.selected == null) break block51;
                                                                                                if (ItemHighlights.all().containsKey(this.selected)) break block52;
                                                                                            }
                                                                                            Ui.text(Im.sub, f + (f3 - Ui.width(Im.sub, b[19])) / 2.0f, f2 + 40.0f, Im.MUTE, b[6]);
                                                                                        }
                                                                                        return;
                                                                                    }
                                                                                    itemHighlights$Highlight = ItemHighlights.get(this.selected);
                                                                                    imDrawList.pushClipRect(f7, f2, f7 + f6, f2 + 40.0f, true);
                                                                                    Ui.text(Im.sora13, f7, f2 + 16.0f, Im.INK, ItemHighlightEditor.label(this.selected));
                                                                                    imDrawList.popClipRect();
                                                                                    stringArray2 = b;
                                                                                    Ui.text(Im.label, f7, f2 + 37.0f, Im.MUTE, stringArray2[27]);
                                                                                    f5 = f2 + 64.0f;
                                                                                    Edit.group(f7, f5, f6, 4);
                                                                                    Edit.name(stringArray2[22], f7, f5);
                                                                                    string3 = stringArray2[29];
                                                                                    f10 = f8 - 20.0f;
                                                                                    if (bl) break block53;
                                                                                    if (!Edit.swatch(string3, f10, f5 + 7.0f, 20.0f, itemHighlights$Highlight.rgb(), ImGui.isPopupOpen((String)stringArray2[29]))) break block54;
                                                                                    ImGui.openPopup((String)b[29]);
                                                                                }
                                                                                this.palette(itemHighlights$Highlight, f8, f5 + 30.0f);
                                                                                stringArray2 = b;
                                                                                string3 = stringArray2[32];
                                                                                f10 = f7;
                                                                            }
                                                                            Edit.name(string3, f10, f5 + 34.0f);
                                                                            stringArray2 = b;
                                                                            int n4 = Edit.dropdown(stringArray2[21], itemHighlights$Highlight.style().name(), STYLES, itemHighlights$Highlight.style().ordinal(), f8, f5 + 34.0f + 7.0f, 120.0f);
                                                                            if (bl) break block55;
                                                                            if (n4 < 0) break block56;
                                                                            ItemHighlights.set(this.selected, new ItemHighlights$Highlight(ItemHighlights$Style.values()[n4], itemHighlights$Highlight.rgb(), itemHighlights$Highlight.pulse()));
                                                                            ConfigManager.save();
                                                                        }
                                                                        stringArray2 = b;
                                                                        Edit.name(stringArray2[18], f7, f5 + 68.0f);
                                                                    }
                                                                    float f11 = (float)Math.min(1.0, itemHighlights$Highlight.pulse() / 3.0);
                                                                    stringArray2 = b;
                                                                    float f12 = Edit.slider(stringArray2[12], f8 - 160.0f, f5 + 68.0f + 8.0f, 110.0f, f11);
                                                                    f9 = f12 == f11 ? 0 : (f12 > f11 ? 1 : -1);
                                                                    if (bl) break block57;
                                                                    if (f9 == false) break block58;
                                                                    ItemHighlights.set(this.selected, new ItemHighlights$Highlight(itemHighlights$Highlight.style(), itemHighlights$Highlight.rgb(), (double)Math.round((double)f12 * 3.0 * 20.0) / 20.0));
                                                                }
                                                                f9 = (float)ImGui.isItemDeactivated();
                                                            }
                                                            if (f9 != false) {
                                                                ConfigManager.save();
                                                            }
                                                            stringArray2 = b;
                                                            Edit.value(String.format(Locale.ROOT, stringArray2[14], ItemHighlights.get(this.selected).pulse()), f8, f5 + 68.0f + 7.0f);
                                                            Edit.name(stringArray2[7], f7, f5 + 102.0f);
                                                            list = ItemHighlightEditor.enchantments((Item)Registries.field_41178.method_63535(this.selected.item()));
                                                            stringArray = new String[list.size() + 1];
                                                            stringArray[0] = stringArray2[16];
                                                            for (int i = 0; i < list.size(); ++i) {
                                                                stringArray[i + 1] = ItemHighlightEditor.enchantName(list.get(i));
                                                                if (!bl) {
                                                                    if (!bl) continue;
                                                                    break;
                                                                }
                                                                break block59;
                                                            }
                                                            itemHighlights$Key2 = this.selected;
                                                            if (bl) break block60;
                                                            if (itemHighlights$Key2.enchantment() != null) break block59;
                                                            string2 = b[10];
                                                            break block61;
                                                        }
                                                        itemHighlights$Key2 = this.selected;
                                                    }
                                                    string2 = ItemHighlightEditor.enchantName(itemHighlights$Key2.enchantment());
                                                }
                                                string = string2;
                                                if (this.selected.enchantment() != null) break block62;
                                                n3 = 0;
                                                break block63;
                                            }
                                            list2 = list;
                                            if (bl) break block64;
                                            if (!list2.contains(this.selected.enchantment())) break block65;
                                            list2 = list;
                                        }
                                        n3 = list2.indexOf(this.selected.enchantment()) + 1;
                                        break block63;
                                    }
                                    n3 = -1;
                                }
                                int n5 = n3;
                                stringArray2 = b;
                                n2 = Edit.dropdown(stringArray2[20], string, stringArray, n5, f8, f5 + 102.0f + 7.0f, 170.0f);
                                n = n2;
                                if (bl) break block66;
                                if (n < 0) break block67;
                                itemHighlights$Key = this.selected;
                                if (n2 != 0) break block68;
                                identifier = null;
                                break block69;
                            }
                            identifier = list.get(n2 - 1);
                        }
                        object = itemHighlights$Key.with(identifier);
                        if (bl) break block70;
                        if (!ItemHighlights.all().containsKey(object)) break block71;
                        this.selected = object;
                        if (!bl) break block67;
                    }
                    this.selected = ItemHighlights.rekey(this.selected, (ItemHighlights$Key)object);
                }
                ConfigManager.save();
            }
            n = Edit.button(b[11], null, f7 + f6, f5 + 136.0f + 12.0f, false) ? 1 : 0;
        }
        if (n != 0) {
            block73: {
                ItemHighlights$Key itemHighlights$Key;
                Object object2;
                block72: {
                    ItemHighlights.remove(this.selected);
                    this.selected = null;
                    object = ItemHighlights.all().keySet().iterator();
                    object2 = object;
                    if (bl) break block72;
                    if (!object2.hasNext()) break block73;
                    object2 = object.next();
                }
                this.selected = itemHighlights$Key = (ItemHighlights$Key)object2;
            }
            ConfigManager.save();
        }
    }

    private void palette(ItemHighlights$Highlight itemHighlights$Highlight, float f, float f2) {
        block12: {
            block13: {
                block14: {
                    ImGui.setNextWindowPos((float)(f - (float)(SWATCHES.length * 22) - 14.0f), (float)f2);
                    boolean bl = ImScreen.a;
                    Edit.popup();
                    if (bl) break block12;
                    ImGui.pushStyleVar((int)2, (float)10.0f, (float)10.0f);
                    if (!ImGui.beginPopup((String)b[8])) break block13;
                    Ui.lift();
                    float f3 = ImGui.getCursorScreenPosX();
                    float f4 = ImGui.getCursorScreenPosY();
                    for (int i = 0; i < SWATCHES.length; ++i) {
                        int n;
                        int n2;
                        float f5;
                        float f6;
                        float f7;
                        String string;
                        block15: {
                            block16: {
                                if (bl) break block14;
                                string = b[17] + i;
                                f7 = f3 + (float)(i * 22);
                                f6 = f4;
                                f5 = 16.0f;
                                n2 = SWATCHES[i];
                                n = itemHighlights$Highlight.rgb();
                                if (bl) break block15;
                                if ((n & ItemHighlightEditor.a(20503, 1504781583285431489L)) != SWATCHES[i]) break block16;
                                n = 1;
                                break block15;
                            }
                            n = 0;
                        }
                        if (!Edit.swatch(string, f7, f6, f5, n2, n != 0)) {
                            continue;
                        }
                        ItemHighlights.set(this.selected, new ItemHighlights$Highlight(itemHighlights$Highlight.style(), SWATCHES[i], itemHighlights$Highlight.pulse()));
                        ConfigManager.save();
                        ImGui.closeCurrentPopup();
                        if (!bl) continue;
                    }
                    ImGui.setCursorScreenPos((float)f3, (float)f4);
                    ImGui.dummy((float)(SWATCHES.length * 22 - 6), (float)16.0f);
                }
                ImGui.endPopup();
            }
            ImGui.popStyleVar();
            Edit.unpopup();
        }
    }

    private static String label(ItemHighlights$Key itemHighlights$Key) {
        Item item = (Item)Registries.field_41178.method_63535(itemHighlights$Key.item());
        String string = item.method_63680().getString();
        Object object = itemHighlights$Key.enchantment() == null ? string : string + b[31] + ItemHighlightEditor.enchantName(itemHighlights$Key.enchantment());
        return object;
    }

    private static String enchantName(Identifier identifier) {
        block7: {
            Enchantment enchantment;
            block8: {
                Object object;
                boolean bl;
                block6: {
                    ClientWorld clientWorld = MinecraftClient.method_1551().field_1687;
                    bl = ImScreen.a;
                    object = clientWorld;
                    if (bl) break block6;
                    if (object == null) break block7;
                    object = clientWorld.method_30349().method_30530(RegistryKeys.field_41265).method_63535(identifier);
                }
                Enchantment enchantment2 = (Enchantment)object;
                enchantment = enchantment2;
                if (bl) break block8;
                if (enchantment == null) break block7;
                enchantment = enchantment2;
            }
            return enchantment.comp_2686().getString();
        }
        return identifier.method_12832();
    }

    private static List<Identifier> enchantments(Item item) {
        ArrayList<Identifier> arrayList;
        block10: {
            ClientWorld clientWorld = MinecraftClient.method_1551().field_1687;
            boolean bl = ImScreen.a;
            if (clientWorld == null) {
                return List.of();
            }
            RegistryEntry.Reference reference = item.method_40131();
            arrayList = new ArrayList<Identifier>();
            for (Map.Entry entry : clientWorld.method_30349().method_30530(RegistryKeys.field_41265).method_29722()) {
                block11: {
                    if (bl) break block10;
                    boolean bl2 = ((Enchantment)entry.getValue()).method_56109().method_40241((RegistryEntry)reference);
                    if (bl) break block11;
                    if (!bl2) break block11;
                    bl2 = arrayList.add(((RegistryKey)entry.getKey()).method_29177());
                }
                if (!bl) continue;
            }
            arrayList.sort(Comparator.comparing(ItemHighlightEditor::enchantName));
        }
        return arrayList;
    }

    
    static {
        block29: {
            block28: {
                block27: {
                    block26: {
                        var13 = new String[35];
                        var11_1 = 0;
                        var10_2 = "b>v_r,DJ.w\u0012\u0005E5g\u0011w\u0007n5v\n\u007f'\u0017\u0019m52\u0016g'\tPzz\u0016t*\bJ=z\u000bv&DZ?fQ\u0010j.w\u00123\n\rD2~\u0016t*\u0010F(\u0005J.w\u0012`\rs3q\u00143#\n\u0003?|\u000ba;\nb*b\u0013z'\u0017\u0003.}\u0007\u0000yq\u0010\u007f-\u0016\u0010j.w\u00123\n\rD2~\u0016t*\u0010F(\u0003b4k\u0006q?\u007f\u0010e'\u0007\u0000yb\n\u007f1\u0001\u0004S3q\u0014\u0004\u0006t \u0019\u0003Q5e\u0003b4k\u0003\u0000ya\u0005s/~\fv\rs3q\u00143#\n\u0003?|\u000ba;\t\u0000ys\u000fc.\rF)\u0007\u0000ya\u000bj.\u0001\u0005`5~\u0010a\u0006`;|\u001cv.\u0005b>v\u001aw\u0015m5f\u0017z,\u0003\u00037s\u000bp*\u0001Pzf\u0017r6J\u0011p?s\rp*DF,w\rjb\rW?\u007f\u001ck5e_g*\rPz{\u000bv/DJ)2\u0017z%\fO3u\u0017g'\u0000\u0006`;|\u001cv.\u0007\u0000yq\u0010\u007f-\u0016\u0006\u0000yt\u0016}&\u0002\u0003z\u0005p.k\u0013v";
                        var12_3 = "b>v_r,DJ.w\u0012\u0005E5g\u0011w\u0007n5v\n\u007f'\u0017\u0019m52\u0016g'\tPzz\u0016t*\bJ=z\u000bv&DZ?fQ\u0010j.w\u00123\n\rD2~\u0016t*\u0010F(\u0005J.w\u0012`\rs3q\u00143#\n\u0003?|\u000ba;\nb*b\u0013z'\u0017\u0003.}\u0007\u0000yq\u0010\u007f-\u0016\u0010j.w\u00123\n\rD2~\u0016t*\u0010F(\u0003b4k\u0006q?\u007f\u0010e'\u0007\u0000yb\n\u007f1\u0001\u0004S3q\u0014\u0004\u0006t \u0019\u0003Q5e\u0003b4k\u0003\u0000ya\u0005s/~\fv\rs3q\u00143#\n\u0003?|\u000ba;\t\u0000ys\u000fc.\rF)\u0007\u0000ya\u000bj.\u0001\u0005`5~\u0010a\u0006`;|\u001cv.\u0005b>v\u001aw\u0015m5f\u0017z,\u0003\u00037s\u000bp*\u0001Pzf\u0017r6J\u0011p?s\rp*DF,w\rjb\rW?\u007f\u001ck5e_g*\rPz{\u000bv/DJ)2\u0017z%\fO3u\u0017g'\u0000\u0006`;|\u001cv.\u0007\u0000yq\u0010\u007f-\u0016\u0006\u0000yt\u0016}&\u0002\u0003z\u0005p.k\u0013v".length();
                        var9_4 = 11;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block26;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "j.w\u0012`\u0005b>v\u001aw";
                            var12_3 = "j.w\u0012`\u0005b>v\u001aw".length();
                            var9_4 = 5;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block26;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            break block27;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_6;
                        v9 = v6[v8];
                        switch (var14_6 % 7) {
                            case 0: {
                                v10 = 35;
                                break;
                            }
                            case 1: {
                                v10 = 90;
                                break;
                            }
                            case 2: {
                                v10 = 18;
                                break;
                            }
                            case 3: {
                                v10 = 127;
                                break;
                            }
                            case 4: {
                                v10 = 19;
                                break;
                            }
                            case 5: {
                                v10 = 66;
                                break;
                            }
                            default: {
                                v10 = 100;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_6;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_6);
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
                ItemHighlightEditor.b = var13;
                var0_7 = 4671172553502640981L;
                var6_8 = new long[11];
                var3_9 = 0;
                var4_10 = "5\u00d1\u00a5\u0003\u00e5A\r@\u00ad\u00fc\u0091\u009b\u00bd\u00bc\u0086\"\u00a4\u00ba\u00ab\n\u00b9\u00aa\u000f\u008e\u00f2\u00d4.\u00fb!\u00bc\u00f1at\u00fa\u001f[\u0005\u00ady\u001f\re\u0007\u00fb'\u0083\u00b5\u0002\u00abm\u00b6Sg\u008aP\u00a8\u001c\u008fX,\u00d8\u00ac\u0098k\u00f9n\u00c3\u0007\u00d1\u0013\u0010\"";
                var5_11 = "5\u00d1\u00a5\u0003\u00e5A\r@\u00ad\u00fc\u0091\u009b\u00bd\u00bc\u0086\"\u00a4\u00ba\u00ab\n\u00b9\u00aa\u000f\u008e\u00f2\u00d4.\u00fb!\u00bc\u00f1at\u00fa\u001f[\u0005\u00ady\u001f\re\u0007\u00fb'\u0083\u00b5\u0002\u00abm\u00b6Sg\u008aP\u00a8\u001c\u008fX,\u00d8\u00ac\u0098k\u00f9n\u00c3\u0007\u00d1\u0013\u0010\"".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v11 = var6_8;
                    v12 = var3_9++;
                    v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v14 = -1;
                    break block28;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "\u00de$N\u0099\u00bd\u00c8\u00da\u001e\u00eaKp\u00d3\u00fas\u00b6\u00b5";
                    var5_11 = "\u00de$N\u0099\u00bd\u00c8\u00da\u001e\u00eaKp\u00d3\u00fas\u00b6\u00b5".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v11 = var6_8;
                        v12 = var3_9++;
                        v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v14 = 0;
                        break block28;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    break block29;
                    break;
                }
            }
            v15 = v13 ^ var0_7;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        ItemHighlightEditor.c = var6_8;
        ItemHighlightEditor.d = new Integer[11];
        ItemHighlightEditor.SWATCHES = new int[]{ItemHighlightEditor.a(26857, 9097446075667059773L), ItemHighlightEditor.a(3137, 442529701146226838L), ItemHighlightEditor.a(22561, 1166284537578956024L), ItemHighlightEditor.a(32571, 7320819712114197482L), ItemHighlightEditor.a(19737, 8123400136247292363L), ItemHighlightEditor.a(5371, 4880674696235280427L), ItemHighlightEditor.a(32452, 8872537803915264543L), ItemHighlightEditor.a(23864, 2708061749842715117L)};
        ItemHighlightEditor.STYLES = new String[ItemHighlights$Style.values().length];
        for (ItemHighlights$Style var18_17 : ItemHighlights$Style.values()) {
            ItemHighlightEditor.STYLES[var18_17.ordinal()] = var18_17.name();
        }
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7CD1;
        if (d[n2] == null) {
            ItemHighlightEditor.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }
}
