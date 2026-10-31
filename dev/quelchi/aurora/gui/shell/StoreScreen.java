package dev.quelchi.aurora.gui.shell;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.gui.shell.LockerScreen;
import dev.quelchi.aurora.gui.shell.RewardsScreen;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.gui.shell.Shop;
import dev.quelchi.aurora.gui.shell.StoreScreen$Item;
import dev.quelchi.aurora.gui.shell.Wardrobe$Section;
import dev.quelchi.aurora.gui.shell.WardrobeBar;
import imgui.ImDrawList;
import imgui.ImGui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import net.minecraft.client.gui.screen.Screen;

public final class StoreScreen
extends ImScreen {
    private static final float PAD = 20.0f;
    private static final float GAP = 12.0f;
    private static final float BIG_H = 210.0f;
    private static final float SMALL_H = 136.0f;
    private static final List<StoreScreen$Item> FEATURED;
    private static final List<StoreScreen$Item> DAILY;
    private static long dailyEnds;
    private static long fetchedAt;
    private static boolean loaded;
    private static boolean failed;
    private static boolean loading;
    private static UUID loadedFor;
    private StoreScreen$Item offer;
    private boolean ask;
    private boolean esc;
    private boolean buying;
    private boolean wearNow;
    private String error;
    private static final String[] b;
    private static final long c;
    private static final long[] d;
    private static final Long[] g;

    public StoreScreen() {
        super(b[10]);
    }

    StoreScreen(String string, String string2) {
        this();
        this.open(new StoreScreen$Item(string, string2, Cosmetics.price(string, string2)));
    }

    @Override
    public boolean shutsOnMenuKey() {
        return true;
    }

    @Override
    protected boolean key(int n) {
        block10: {
            StoreScreen storeScreen;
            block11: {
                int n2;
                block8: {
                    block9: {
                        boolean bl = SearchIndex.a;
                        n2 = n;
                        if (bl) break block8;
                        if (n2 != 256) break block9;
                        storeScreen = this;
                        if (bl) break block10;
                        if (storeScreen.offer != null) break block11;
                    }
                    n2 = 0;
                }
                return n2 != 0;
            }
            storeScreen = this;
        }
        storeScreen.esc = true;
        return true;
    }

    static void refresh() {
        block4: {
            if (!loading && CosmeticsSync.token() != null) break block4;
            return;
        }
        loading = true;
        UUID uUID = CosmeticsSync.ownUuid();
        CosmeticsSync.fetch(b[21], arg_0 -> StoreScreen.lambda$refresh$0(uUID, arg_0));
    }

    
    private static void apply(JsonObject var0, UUID var1_1) {
        block19: {
            block20: {
                block21: {
                    block22: {
                        StoreScreen.loading = false;
                        var2_2 = SearchIndex.a;
                        StoreScreen.fetchedAt = System.currentTimeMillis();
                        if (var2_2) break block19;
                        if (var0 != null) break block20;
                        v1 = StoreScreen.loaded;
                        if (var2_2) break block21;
                        if (v1) break block22;
                        v1 = true;
                        break block21;
                    }
                    v1 = false;
                }
                StoreScreen.failed = v1;
                return;
            }
            StoreScreen.loadedFor = var1_1;
            StoreScreen.failed = false;
            StoreScreen.loaded = true;
            var7_3 = StoreScreen.b;
            StoreScreen.dailyEnds = var0.get(var7_3[12]).getAsLong();
            StoreScreen.FEATURED.clear();
            StoreScreen.DAILY.clear();
        }
        var3_4 = new HashMap<String, Integer>();
        var7_3 = StoreScreen.b;
        StoreScreen.FEATURED.add(StoreScreen.item(var0.getAsJsonObject(var7_3[22])));
        var3_4.put(StoreScreen.FEATURED.get((int)0).cat + ":" + StoreScreen.FEATURED.get((int)0).id, StoreScreen.FEATURED.get((int)0).price);
        var4_5 = var0.getAsJsonArray(var7_3[27]).iterator();
        while (var4_5.hasNext()) {
            var5_6 = (JsonElement)var4_5.next();
            var6_7 = StoreScreen.item(var5_6.getAsJsonObject());
            StoreScreen.DAILY.add(var6_7);
            v4 = var3_4;
            v5 = var6_7.cat + ":" + var6_7.id;
            if (!var2_2) {
                v4.put(v5, var6_7.price);
                if (!var2_2) continue;
                break;
            }
        }
        Cosmetics.setDeals(var3_4);
        for (JsonElement var5_6 : var0.getAsJsonArray(StoreScreen.b[23])) {
            block24: {
                block23: {
                    var6_7 = StoreScreen.item(var5_6.getAsJsonObject());
                    v7 = StoreScreen.FEATURED.size();
                    if (var2_2) break block23;
                    if (v7 >= 3) break block24;
                    v4 = var3_4;
                    v5 = var6_7.cat + ":" + var6_7.id;
lbl76:

                    v7 = v4.containsKey(v5);
                }
                if (var2_2 || v7 != 0) break block24;
                v7 = (int)StoreScreen.FEATURED.add(var6_7);
            }
            if (!var2_2) continue;
        }
    }

    private static StoreScreen$Item item(JsonObject jsonObject) {
        String[] stringArray = b;
        return new StoreScreen$Item(jsonObject.get(stringArray[33]).getAsString(), jsonObject.get(stringArray[7]).getAsString(), jsonObject.get(stringArray[8]).getAsInt());
    }

    static String left(long l) {
        String string;
        block7: {
            long l2;
            block5: {
                long l3;
                block6: {
                    l3 = Math.max(0L, l - System.currentTimeMillis() / StoreScreen.a(31033, 2963282174479436760L));
                    boolean bl = SearchIndex.a;
                    l2 = l3;
                    if (bl) break block5;
                    if (l2 >= StoreScreen.a(10968, 794300592969013307L)) {
                    }
                    break block6;
                    string = l3 / StoreScreen.a(11918, 8175741212376423528L) + b[16] + l3 / StoreScreen.a(8475, 2420132733326775289L) % StoreScreen.a(25411, 1676573146895880615L) + "m";
                    break block7;
                }
                l2 = Math.max(1L, l3 / StoreScreen.a(25411, 1676573146895880615L));
            }
            string = l2 + "m";
        }
        return string;
    }

    
    @Override
    protected void draw() {
        boolean bl;
        block43: {
            float f;
            float f2;
            float f3;
            block42: {
                int n;
                float f4;
                String[] stringArray;
                block38: {
                    block39: {
                        block41: {
                            long l;
                            long l2;
                            block40: {
                                long l3;
                                block36: {
                                    int n2;
                                    block37: {
                                        block35: {
                                            stringArray = b;
                                            Menu.begin(stringArray[6], 0.0f);
                                            f3 = Menu.x + 144.0f;
                                            f2 = Menu.y;
                                            bl = SearchIndex.a;
                                            f = Menu.w - 144.0f;
                                            f4 = Menu.h;
                                            Ui.text(Im.sora13, f3 + 20.0f, Ui.mid(Im.sora13, f2, 56.0f), Im.INK, stringArray[26]);
                                            n2 = 1;
                                            if (bl) break block35;
                                            WardrobeBar.draw(n2, f2);
                                            if (!loaded) break block36;
                                            n2 = Objects.equals(loadedFor, CosmeticsSync.ownUuid()) ? 1 : 0;
                                        }
                                        if (bl) break block37;
                                        if (n2 != 0) break block36;
                                        n2 = 0;
                                    }
                                    loaded = n2;
                                }
                                l2 = System.currentTimeMillis();
                                l = l2 - fetchedAt;
                                n = loading;
                                if (bl) break block38;
                                if (n != 0) break block39;
                                int n3 = loaded;
                                if (!bl) {
                                    n3 = n3 != 0 ? (int)c : 5000;
                                }
                                n = (l3 = l - (long)n3) == 0L ? 0 : (l3 < 0L ? -1 : 1);
                                if (bl) break block40;
                                if (n > 0) break block41;
                                n = loaded ? 1 : 0;
                            }
                            if (bl) break block38;
                            if (n == 0) break block39;
                            long l4 = l2 / StoreScreen.a(28436, 7108034130770763251L) - dailyEnds;
                            n = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                            if (bl) break block38;
                            if (n < 0) break block39;
                            long l5 = l - StoreScreen.a(21562, 472821626504324831L);
                            n = l5 == 0L ? 0 : (l5 < 0L ? -1 : 1);
                            if (bl) break block38;
                            if (n <= 0) break block39;
                        }
                        StoreScreen.refresh();
                    }
                    n = loaded;
                }
                if (n != 0) break block42;
                String string = CosmeticsSync.token();
                if (!bl) {
                    if (string == null) {
                        stringArray = b;
                        string = stringArray[13];
                    } else if (failed) {
                        string = b[3];
                    } else {
                        stringArray = b;
                        string = stringArray[9];
                    }
                }
                String string2 = string;
                Ui.text(Im.sub, f3 + (f - Ui.width(Im.sub, string2)) / 2.0f, f2 + 56.0f + (f4 - 56.0f) / 2.0f - 8.0f, Im.MUTE, string2);
                if (!bl) break block43;
            }
            this.shelf(f3 + 20.0f, f2 + 56.0f + 20.0f, f - 40.0f);
        }
        StoreScreen storeScreen = this;
        if (!bl) {
            if (storeScreen.ask) {
                ImGui.openPopup((String)b[14]);
                this.ask = false;
            }
            this.buyBox();
            storeScreen = this;
        }
        storeScreen.esc = false;
        Menu.end();
    }

    private void shelf(float f, float f2, float f3) {
        boolean bl;
        block5: {
            String[] stringArray = b;
            Ui.label(f, f2, stringArray[18]);
            f2 += 19.0f;
            float f4 = (float)Math.floor((f3 - 24.0f) / 3.0f);
            bl = SearchIndex.a;
            for (int i = 0; i < FEATURED.size(); ++i) {
                this.big(FEATURED.get(i), f + (float)i * (f4 + 12.0f), f2, f4);
                if (!bl) {
                    if (!bl) continue;
                    break;
                }
                break block5;
            }
            stringArray = b;
            Ui.label(f, f2 += 224.0f, stringArray[24]);
        }
        String string = b[11] + StoreScreen.left(dailyEnds);
        Ui.text(Im.label, f + f3 - Ui.width(Im.label, string), f2, Im.MUTE, string);
        f2 += 19.0f;
        int n = Math.max(6, DAILY.size());
        float f5 = (float)Math.floor((f3 - 12.0f * (float)(n - 1)) / (float)n);
        for (int i = 0; i < DAILY.size(); ++i) {
            this.small(DAILY.get(i), f + (float)i * (f5 + 12.0f), f2, f5);
            if (!bl) continue;
        }
    }

    private void open(StoreScreen$Item storeScreen$Item) {
        this.offer = storeScreen$Item;
        this.error = null;
        this.buying = false;
        this.wearNow = true;
        this.ask = true;
    }

    private void big(StoreScreen$Item storeScreen$Item, float f, float f2, float f3) {
        float f4;
        block32: {
            float f5;
            float f6;
            String string;
            ImDrawList imDrawList;
            block30: {
                block31: {
                    int n;
                    float f7;
                    boolean bl;
                    int n2;
                    block28: {
                        block29: {
                            String string2;
                            block27: {
                                int n3;
                                block25: {
                                    block26: {
                                        block23: {
                                            block24: {
                                                n2 = Cosmetics.owns(storeScreen$Item.cat, storeScreen$Item.id);
                                                bl = SearchIndex.a;
                                                imDrawList = ImGui.getWindowDrawList();
                                                int n4 = 0;
                                                float f8 = n2 != 0 ? 0.6f : 1.0f;
                                                ImGui.pushStyleVar((int)n4, (float)f8);
                                                imDrawList.addRectFilled(f, f2, f + f3, f2 + 210.0f, Ui.wash(0.03f), 12.0f);
                                                imDrawList.addRect(f, f2, f + f3, f2 + 210.0f, Ui.wash(0.08f), 12.0f);
                                                StoreScreen.art(storeScreen$Item, f + f3 / 2.0f, f2 + 18.0f, 134.0f);
                                                n3 = Cosmetics.isNew(storeScreen$Item.cat, storeScreen$Item.id);
                                                if (bl) break block23;
                                                if (n3 == 0) break block24;
                                                imDrawList.addRectFilled(f + 10.0f, f2 + 10.0f, f + 10.0f + Ui.width(Im.label, b[31]) + 14.0f, f2 + 28.0f, Ui.col(Menu.FILL), 5.0f);
                                                Ui.text(Im.label, f + 17.0f, Ui.mid(Im.label, f2 + 10.0f, 18.0f), Im.ON_FILL, b[4]);
                                            }
                                            n3 = n2;
                                        }
                                        if (bl) break block25;
                                        if (n3 == 0) break block26;
                                        string2 = b[28];
                                        break block27;
                                    }
                                    n3 = storeScreen$Item.price;
                                }
                                string2 = Shop.fmt(n3);
                            }
                            string = string2;
                            f7 = Ui.width(Im.sora12r, string);
                            n = n2;
                            if (bl) break block28;
                            if (n == 0) break block29;
                            n = 24;
                            break block28;
                        }
                        n = 44;
                    }
                    f6 = f7 + (float)n;
                    f4 = f + f3 - 14.0f - f6;
                    f5 = f2 + 210.0f - 14.0f - 28.0f;
                    if (bl) break block30;
                    if (n2 == 0) break block31;
                    imDrawList.addRectFilled(f4, f5, f4 + f6, f5 + 28.0f, Ui.col(Im.QUIET), 8.0f);
                    imDrawList.addRect(f4, f5, f4 + f6, f5 + 28.0f, Ui.wash(0.16f), 8.0f);
                    Ui.text(Im.sora12r, f4 + 12.0f, Ui.mid(Im.sora12r, f5, 28.0f), Im.SUB, string);
                    if (!bl) break block32;
                }
                ImGui.setCursorScreenPos((float)f4, (float)f5);
            }
            String string3 = storeScreen$Item.id;
            String string4 = storeScreen$Item.cat;
            if (ImGui.invisibleButton((String)(b[5] + string4 + string3), (float)f6, (float)28.0f)) {
                this.open(storeScreen$Item);
            }
            ImDrawList imDrawList2 = imDrawList;
            float f12 = f4;
            f12 = f5;
            f12 = f4 + f6;
            f12 = f5 + 28.0f;
            float[] fArray = ImGui.isItemHovered() ? Menu.ICON_FOOT : Menu.FILL;
            imDrawList2.addRectFilled(f9, f10, f11, f12, Ui.col(fArray), 8.0f);
            WardrobeBar.gem(f4 + 12.0f, f5 + 7.5f, 13.0f);
            Ui.text(Im.sora12r, f4 + 32.0f, Ui.mid(Im.sora12r, f5, 28.0f), Im.ON_FILL, string);
        }
        Ui.text(Im.sora13, f + 14.0f, f2 + 210.0f - 47.0f, Im.INK, LockerScreen.fit(Im.sora13, Shop.label(storeScreen$Item.cat, storeScreen$Item.id), f4 - f - 24.0f));
        Ui.text(Im.label, f + 14.0f, f2 + 210.0f - 29.0f, Im.MUTE, Shop.kind(storeScreen$Item.cat));
        ImGui.popStyleVar();
    }

    private void small(StoreScreen$Item storeScreen$Item, float f, float f2, float f3) {
        block32: {
            int n;
            block30: {
                block31: {
                    float f4;
                    float f5;
                    float f6;
                    float f7;
                    float f8;
                    ImDrawList imDrawList;
                    boolean bl;
                    int n2;
                    block28: {
                        block29: {
                            float f9;
                            float f10;
                            float f11;
                            float f12;
                            float f13;
                            ImDrawList imDrawList2;
                            ImDrawList imDrawList3;
                            boolean bl2;
                            block26: {
                                block27: {
                                    int n3;
                                    block22: {
                                        block23: {
                                            boolean bl3;
                                            block24: {
                                                block25: {
                                                    n2 = Cosmetics.owns(storeScreen$Item.cat, storeScreen$Item.id);
                                                    bl2 = false;
                                                    imDrawList3 = ImGui.getWindowDrawList();
                                                    bl = SearchIndex.a;
                                                    n3 = n2;
                                                    if (bl) break block22;
                                                    if (n3 != 0) break block23;
                                                    ImGui.setCursorScreenPos((float)f, (float)f2);
                                                    String string = storeScreen$Item.id;
                                                    String string2 = storeScreen$Item.cat;
                                                    bl3 = ImGui.invisibleButton((String)(b[25] + string2 + string), (float)f3, (float)136.0f);
                                                    if (bl) break block24;
                                                    if (!bl3) break block25;
                                                    this.open(storeScreen$Item);
                                                }
                                                bl3 = ImGui.isItemHovered();
                                            }
                                            bl2 = bl3;
                                        }
                                        n3 = 0;
                                    }
                                    float f14 = n2 != 0 ? 0.6f : 1.0f;
                                    ImGui.pushStyleVar((int)n3, (float)f14);
                                    imDrawList2 = imDrawList3;
                                    f13 = f;
                                    f12 = f2;
                                    f11 = f + f3;
                                    f10 = f2;
                                    f9 = 136.0f;
                                    if (bl) break block26;
                                    f10 = f10 + f9;
                                    if (!bl2) break block27;
                                    f9 = 0.07f;
                                    break block26;
                                }
                                f9 = 0.03f;
                            }
                            imDrawList2.addRectFilled(f13, f12, f11, f10, Ui.wash(f9), 12.0f);
                            imDrawList = imDrawList3;
                            f8 = f;
                            f7 = f2;
                            f6 = f + f3;
                            f5 = f2;
                            f4 = 136.0f;
                            if (bl) break block28;
                            f5 = f5 + f4;
                            if (!bl2) break block29;
                            f4 = 0.16f;
                            break block28;
                        }
                        f4 = 0.08f;
                    }
                    imDrawList.addRect(f8, f7, f6, f5, Ui.wash(f4), 12.0f);
                    StoreScreen.art(storeScreen$Item, f + f3 / 2.0f, f2 + 12.0f, 64.0f);
                    String string = LockerScreen.fit(Shop.label(storeScreen$Item.cat, storeScreen$Item.id), f3 - 16.0f);
                    Ui.text(Im.sora12, f + (float)Math.floor((f3 - Ui.width(Im.sora12, string)) / 2.0f), f2 + 136.0f - 44.0f, Im.INK, string);
                    n = n2;
                    if (bl) break block30;
                    if (n == 0) break block31;
                    Ui.text(Im.sora12, f + (float)Math.floor((f3 - Ui.width(Im.sora12, b[30])) / 2.0f), f2 + 136.0f - 27.0f, Im.MUTE, b[28]);
                    if (!bl) break block32;
                }
                n = storeScreen$Item.price;
            }
            String string = Shop.fmt(n);
            WardrobeBar.amount(string, f + (float)Math.floor((f3 + WardrobeBar.amountWidth(string)) / 2.0f), f2 + 136.0f - 28.0f, 16.0f, Im.INK);
        }
        ImGui.popStyleVar();
    }

    private static void art(StoreScreen$Item storeScreen$Item, float f, float f2, float f3) {
        boolean bl = SearchIndex.a;
        if (LockerScreen.art(storeScreen$Item.cat, storeScreen$Item.id, null, f, f2, f3)) {
            return;
        }
        Wardrobe$Section[] wardrobe$SectionArray = Wardrobe$Section.values();
        int n = wardrobe$SectionArray.length;
        int n2 = 0;
        while (n2 < n) {
            block8: {
                block9: {
                    Wardrobe$Section wardrobe$Section = wardrobe$SectionArray[n2];
                    if (bl) break block8;
                    if (!wardrobe$Section.cat.equals(storeScreen$Item.cat)) break block9;
                    Ui.icon(wardrobe$Section.icon, f - f3 * 0.2f, f2 + f3 * 0.3f, f3 * 0.4f, Menu.ICON_TOP, Menu.ICON_FOOT);
                }
                ++n2;
            }
            if (!bl) continue;
        }
    }

    private void buy(StoreScreen$Item storeScreen$Item) {
        block8: {
            block7: {
                int n;
                block6: {
                    boolean bl = SearchIndex.a;
                    n = this.buying;
                    if (bl) break block6;
                    if (n != 0) break block7;
                    long l = Cosmetics.shards() - (long)storeScreen$Item.price;
                    n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                }
                if (n >= 0) break block8;
            }
            return;
        }
        this.buying = true;
        CosmeticsSync.buy(storeScreen$Item.cat, storeScreen$Item.id, "", storeScreen$Item.price, arg_0 -> this.lambda$buy$1(storeScreen$Item, arg_0));
    }

    private static void row(float f, float f2, String string, long l, float[] fArray) {
        Ui.text(Im.sora12r, f + 12.0f, Ui.mid(Im.sora12r, f2, 34.0f), Im.INK, string);
        WardrobeBar.amount(Shop.fmt(l), f + 288.0f, f2, 34.0f, fArray);
    }

    
    private void buyBox() {
        block82: {
            block83: {
                block87: {
                    block110: {
                        block116: {
                            block115: {
                                block114: {
                                    block111: {
                                        block113: {
                                            block112: {
                                                block108: {
                                                    block109: {
                                                        block106: {
                                                            block107: {
                                                                block104: {
                                                                    block105: {
                                                                        block97: {
                                                                            block103: {
                                                                                block102: {
                                                                                    block98: {
                                                                                        block99: {
                                                                                            block100: {
                                                                                                block101: {
                                                                                                    block96: {
                                                                                                        block94: {
                                                                                                            block95: {
                                                                                                                block93: {
                                                                                                                    block92: {
                                                                                                                        block90: {
                                                                                                                            block91: {
                                                                                                                                block89: {
                                                                                                                                    block88: {
                                                                                                                                        block86: {
                                                                                                                                            block84: {
                                                                                                                                                block85: {
                                                                                                                                                    var2_1 = ImGui.getIO().getDisplaySizeX();
                                                                                                                                                    var3_2 = ImGui.getIO().getDisplaySizeY();
                                                                                                                                                    ImGui.setNextWindowPos((float)(var2_1 / 2.0f - 170.0f), (float)(var3_2 / 2.0f - 125.0f));
                                                                                                                                                    ImGui.setNextWindowSize((float)340.0f, (float)0.0f);
                                                                                                                                                    var1_3 = SearchIndex.a;
                                                                                                                                                    ImGui.pushStyleVar((int)2, (float)20.0f, (float)20.0f);
                                                                                                                                                    v0 = 9;
                                                                                                                                                    if (var1_3) break block82;
                                                                                                                                                    ImGui.pushStyleVar((int)v0, (float)14.0f);
                                                                                                                                                    ImGui.pushStyleColor((int)4, (float)Im.POP[0], (float)Im.POP[1], (float)Im.POP[2], (float)Im.POP[3]);
                                                                                                                                                    ImGui.pushStyleColor((int)56, (float)Im.DIM[0], (float)Im.DIM[1], (float)Im.DIM[2], (float)Math.min(1.0f, 0.62f * Im.DIM[3]));
                                                                                                                                                    if (!ImGui.beginPopupModal((String)StoreScreen.b[14], (int)47)) break block83;
                                                                                                                                                    Ui.lift();
                                                                                                                                                    var4_4 = this.offer;
                                                                                                                                                    if (var1_3) break block84;
                                                                                                                                                    if (var4_4 == null) break block85;
                                                                                                                                                    v3 = this.esc;
                                                                                                                                                    if (var1_3) break block86;
                                                                                                                                                    if (v3 != 0) {
                                                                                                                                                    }
                                                                                                                                                    v3 = (int)this.buying;
                                                                                                                                                    if (var1_3) break block86;
                                                                                                                                                    if (v3 == 0) {
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                this.offer = null;
                                                                                                                                                ImGui.closeCurrentPopup();
                                                                                                                                            }
                                                                                                                                            if (!var1_3) break block87;
lbl59:

                                                                                                                                            v3 = Cosmetics.price(var4_4.cat, var4_4.id);
                                                                                                                                        }
                                                                                                                                        var5_5 = v3;
                                                                                                                                        v10 = this;
                                                                                                                                        if (var1_3) break block88;
                                                                                                                                        if (v10.buying) break block89;
                                                                                                                                        v10 = this;
                                                                                                                                    }
                                                                                                                                    if (v10.error == null || var5_5 == var4_4.price) break block89;
                                                                                                                                    this.offer = var4_4 = new StoreScreen$Item(var4_4.cat, var4_4.id, var5_5);
                                                                                                                                }
                                                                                                                                var6_6 = Cosmetics.shards();
                                                                                                                                cfr_temp_0 = var6_6 - (long)var4_4.price;
                                                                                                                                v14 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                                                                                                                if (var1_3) break block90;
                                                                                                                                if (v14 >= 0) break block91;
                                                                                                                                v14 = 1;
                                                                                                                                break block90;
                                                                                                                            }
                                                                                                                            v14 = 0;
                                                                                                                        }
                                                                                                                        var8_7 = v14;
                                                                                                                        var9_8 = ImGui.getCursorScreenPosX();
                                                                                                                        var10_9 = ImGui.getCursorScreenPosY();
                                                                                                                        var11_10 = ImGui.getWindowDrawList();
                                                                                                                        var12_11 = Shop.label(var4_4.cat, var4_4.id) + " " + Shop.kind(var4_4.cat).toLowerCase(Locale.ROOT);
                                                                                                                        v16 = Im.bold;
                                                                                                                        v17 = var9_8;
                                                                                                                        v18 = var10_9;
                                                                                                                        v19 = Im.INK;
                                                                                                                        v20 = Im.bold;
                                                                                                                        if (var8_7 == false) break block92;
                                                                                                                        v21 = var12_11;
                                                                                                                        break block93;
                                                                                                                    }
                                                                                                                    var16_12 = StoreScreen.b;
                                                                                                                    v21 = var16_12[34] + var12_11 + "?";
                                                                                                                }
                                                                                                                Ui.text(v16, v17, v18, v19, LockerScreen.fit(v20, v21, 300.0f));
                                                                                                                var13_13 = var10_9 + 36.0f;
                                                                                                                var11_10.addRectFilled(var9_8, var13_13, var9_8 + 300.0f, var13_13 + 102.0f, Ui.wash(0.03f), 8.0f);
                                                                                                                var11_10.addRect(var9_8, var13_13, var9_8 + 300.0f, var13_13 + 102.0f, Ui.wash(0.08f), 8.0f);
                                                                                                                var11_10.addLine(var9_8, var13_13 + 34.0f, var9_8 + 300.0f, var13_13 + 34.0f, Ui.wash(0.06f));
                                                                                                                var11_10.addLine(var9_8, var13_13 + 68.0f, var9_8 + 300.0f, var13_13 + 68.0f, Ui.wash(0.06f));
                                                                                                                v23 = var9_8;
                                                                                                                v24 = var13_13;
                                                                                                                v25 = StoreScreen.b[32];
                                                                                                                if (var1_3) break block94;
                                                                                                                StoreScreen.row(v23, v24, v25, var4_4.price, Im.INK);
                                                                                                                StoreScreen.row(var9_8, var13_13 + 34.0f, StoreScreen.b[19], var6_6, Im.INK);
                                                                                                                v23 = var9_8;
                                                                                                                v24 = var13_13 + 68.0f;
                                                                                                                if (var8_7 == false) break block95;
                                                                                                                v25 = StoreScreen.b[36];
                                                                                                                break block94;
                                                                                                            }
                                                                                                            var16_12 = StoreScreen.b;
                                                                                                            v25 = var16_12[35];
                                                                                                        }
                                                                                                        v28 = Math.abs(var6_6 - (long)var4_4.price);
                                                                                                        v29 = var8_7 != false ? Im.DANGER : Im.INK;
                                                                                                        StoreScreen.row(v23, v24, v25, v28, v29);
                                                                                                        var14_14 = var13_13 + 114.0f;
                                                                                                        if (var1_3) break block96;
                                                                                                        if (var8_7 != false) break block97;
                                                                                                        ImGui.setCursorScreenPos((float)var9_8, (float)var14_14);
                                                                                                    }
                                                                                                    v33 = ImGui.invisibleButton((String)StoreScreen.b[29], (float)300.0f, (float)18.0f);
                                                                                                    if (var1_3) break block98;
                                                                                                    if (!v33) break block99;
                                                                                                    v35 = this;
                                                                                                    v36 = this.wearNow;
                                                                                                    if (var1_3) break block100;
                                                                                                    if (v36) break block101;
                                                                                                    v36 = true;
                                                                                                    break block100;
                                                                                                }
                                                                                                v36 = false;
                                                                                            }
                                                                                            v35.wearNow = v36;
                                                                                        }
                                                                                        v33 = this.wearNow;
                                                                                    }
                                                                                    if (!v33) break block102;
                                                                                    var11_10.addRectFilled(var9_8, var14_14, var9_8 + 18.0f, var14_14 + 18.0f, Ui.col(Menu.FILL), 5.0f);
                                                                                    Menu.tick(var9_8 + 9.0f, var14_14 + 9.0f, 3.6f, ImGui.getColorU32((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f));
                                                                                    if (!var1_3) break block103;
                                                                                }
                                                                                var11_10.addRectFilled(var9_8, var14_14, var9_8 + 18.0f, var14_14 + 18.0f, Ui.col(Im.QUIET), 5.0f);
                                                                                var11_10.addRect(var9_8, var14_14, var9_8 + 18.0f, var14_14 + 18.0f, Ui.wash(0.16f), 5.0f);
                                                                            }
                                                                            var16_12 = StoreScreen.b;
                                                                            Ui.text(Im.sora12r, var9_8 + 28.0f, Ui.mid(Im.sora12r, var14_14, 18.0f), Im.INK, var16_12[2]);
                                                                            var14_14 += 18.0f;
                                                                        }
                                                                        v41 = this.error;
                                                                        if (var1_3) break block104;
                                                                        if (v41 == null) break block105;
                                                                        Ui.text(Im.label, var9_8, var14_14 + 8.0f, Im.DANGER, LockerScreen.fit(Im.label, this.error, 300.0f));
                                                                        var14_14 += 22.0f;
                                                                    }
                                                                    var14_14 += 16.0f;
                                                                    var16_12 = StoreScreen.b;
                                                                    v41 = var16_12[0];
                                                                }
                                                                v43 = Menu.button(v41, var9_8, var14_14, 146.0f, 34.0f, false);
                                                                if (var1_3) break block106;
                                                                if (!v43) break block107;
                                                                v43 = this.buying;
                                                                if (var1_3) break block106;
                                                                if (v43) break block107;
                                                                this.offer = null;
                                                                ImGui.closeCurrentPopup();
                                                            }
                                                            v43 = var8_7;
                                                        }
                                                        if (var1_3) break block108;
                                                        if (!v43) break block109;
                                                        if (!Menu.button(StoreScreen.b[20], var9_8 + 154.0f, var14_14, 146.0f, 34.0f, true)) break block110;
                                                        this.offer = null;
                                                        ImGui.closeCurrentPopup();
                                                        this.field_22787.method_1507((Screen)new RewardsScreen());
                                                        if (!var1_3) break block110;
                                                    }
                                                    v51 = 525;
                                                    if (var1_3) break block111;
                                                    v43 = ImGui.isKeyPressed((int)v51);
                                                }
                                                if (v43) break block112;
                                                v51 = 627;
                                                if (var1_3) break block111;
                                                if (!ImGui.isKeyPressed((int)v51)) break block113;
                                            }
                                            v51 = 1;
                                            break block111;
                                        }
                                        v51 = 0;
                                    }
                                    var15_15 = v51;
                                    if (!this.buying) break block114;
                                    v57 = StoreScreen.b[17];
                                    break block115;
                                }
                                var16_12 = StoreScreen.b;
                                v57 = var16_12[15];
                            }
                            v59 = Menu.button(v57 + StoreScreen.b[1], var9_8 + 154.0f, var14_14, 146.0f, 34.0f, true);
                            if (var1_3) break block116;
                            if (v59 == 0) {
                            }
                            v59 = var15_15;
                        }
                        if (v59 == 0) break block110;
lbl331:

                        this.buy(var4_4);
                    }
                    ImGui.setCursorScreenPos((float)var9_8, (float)var10_9);
                    ImGui.dummy((float)300.0f, (float)(var14_14 + 34.0f - var10_9));
                }
                ImGui.endPopup();
            }
            ImGui.popStyleColor((int)2);
            v0 = 2;
        }
        ImGui.popStyleVar((int)v0);
    }

    private void lambda$buy$1(StoreScreen$Item storeScreen$Item, String string) {
        this.buying = false;
        if (this.offer != storeScreen$Item) {
            return;
        }
        if (string != null) {
            this.error = string;
            StoreScreen.refresh();
            return;
        }
        this.offer = null;
        if (this.wearNow) {
            Shop.wear(storeScreen$Item.cat, storeScreen$Item.id);
        }
    }

    private static void lambda$refresh$0(UUID uUID, JsonObject jsonObject) {
        StoreScreen.apply(jsonObject, uUID);
    }

    
    static {
        block29: {
            block28: {
                block30: {
                    block27: {
                        block26: {
                            var15 = new String[37];
                            var13_1 = 0;
                            var12_2 = "&\u000b\u0005*7apF\b\u001e0\u0004FI\f&\u0016 \u001b\u001e \"-:\u0011J\u0018= l:\u0002\u0002\u001fi3z2\u001c%1\u0002\u000ei!y<\u0017\u000fK !-=\n\u001eK;7l0\r\u000b\t%7-!\f\r\u0003=rc<\u0012D\u0003+/<\u0003\u0007\u001f\u0012\b2\u000b\u0019- b1\u0000\u0002\f\u000e\u0005\u0015\u0018\u0002*7\u0014)\u0005\n-;c4E\u001e\u0003,r~'\n\u0018\u000eg|#\u00056\u001e\u0004;7\u0007+\u000f\u001ci;cs\u000b\u0001\u000b\u0002%+H=\u0001\u0019*=\u0017+\u0005\u001fi!d4\u000b\u000f\u000fi;cs\u0011\u0005K\b'\u007f<\u0017\u000b\u0003\u0007\u001f\u0012\u0003'\u001f\u0012\u0002\rJ\t'\u001f\u0012 <j}KD\b#\u000f\n='\u007f6\u0001\b<\u0005\u001ei:l%\u0000\f7\u000f\u001c( i FI\t<+\nJ\u000b\u001b }~'\n\u0018\u000e\b\u0003\u000f\n='\u007f6\u0001\u0006\t\u000b\u001f,!y\u000b!\u000b\u0002%+-#\f\t\u0000:\u0004\u0015\u0003\b\"\u00056\u001e\u0004;7\u0005\u0001\u000b\u0002%+\u0005*\u001d\u0005,6\u0004\u0012\u000f\n;\u0005*\u001d\u0005,6\u0003+/<\u00055\u0018\u0002*7\u0003\u0006\u000b\u001f\u0004'\u001f\u0012i";
                            var14_3 = "&\u000b\u0005*7apF\b\u001e0\u0004FI\f&\u0016 \u001b\u001e \"-:\u0011J\u0018= l:\u0002\u0002\u001fi3z2\u001c%1\u0002\u000ei!y<\u0017\u000fK !-=\n\u001eK;7l0\r\u000b\t%7-!\f\r\u0003=rc<\u0012D\u0003+/<\u0003\u0007\u001f\u0012\b2\u000b\u0019- b1\u0000\u0002\f\u000e\u0005\u0015\u0018\u0002*7\u0014)\u0005\n-;c4E\u001e\u0003,r~'\n\u0018\u000eg|#\u00056\u001e\u0004;7\u0007+\u000f\u001ci;cs\u000b\u0001\u000b\u0002%+H=\u0001\u0019*=\u0017+\u0005\u001fi!d4\u000b\u000f\u000fi;cs\u0011\u0005K\b'\u007f<\u0017\u000b\u0003\u0007\u001f\u0012\u0003'\u001f\u0012\u0002\rJ\t'\u001f\u0012 <j}KD\b#\u000f\n='\u007f6\u0001\b<\u0005\u001ei:l%\u0000\f7\u000f\u001c( i FI\t<+\nJ\u000b\u001b }~'\n\u0018\u000e\b\u0003\u000f\n='\u007f6\u0001\u0006\t\u000b\u001f,!y\u000b!\u000b\u0002%+-#\f\t\u0000:\u0004\u0015\u0003\b\"\u00056\u001e\u0004;7\u0005\u0001\u000b\u0002%+\u0005*\u001d\u0005,6\u0004\u0012\u000f\n;\u0005*\u001d\u0005,6\u0003+/<\u00055\u0018\u0002*7\u0003\u0006\u000b\u001f\u0004'\u001f\u0012i".length();
                            var11_4 = 11;
                            var10_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var10_5;
                                v1 = var12_2.substring(v0, v0 + var11_4);
                                v2 = -1;
                                break block26;
                                break;
                            }
lbl12:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                var12_2 = ")\u000f\r=rl5\u0011\u000f\u0019\f6\u001e\u0002%>-=\u0000\u000f\u000f,6";
                                var14_3 = ")\u000f\r=rl5\u0011\u000f\u0019\f6\u001e\u0002%>-=\u0000\u000f\u000f,6".length();
                                var11_4 = 10;
                                var10_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var10_5;
                                    v1 = var12_2.substring(v4, v4 + var11_4);
                                    v2 = 0;
                                    break block26;
                                    break;
                                }
                                break;
                            }
lbl26:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                break block27;
                                break;
                            }
                        }
                        v5 = v1.toCharArray();
                        v6 = v5;
                        v7 = v5.length;
                        var16_6 = 0;
                        do {
                            v6 = v6;
                            v8 = var16_6;
                            v9 = v6[v8];
                            switch (var16_6 % 7) {
                                case 0: {
                                    v10 = 101;
                                    break;
                                }
                                case 1: {
                                    v10 = 106;
                                    break;
                                }
                                case 2: {
                                    v10 = 107;
                                    break;
                                }
                                case 3: {
                                    v10 = 73;
                                    break;
                                }
                                case 4: {
                                    v10 = 82;
                                    break;
                                }
                                case 5: {
                                    v10 = 13;
                                    break;
                                }
                                default: {
                                    v10 = 83;
                                }
                            }
                            v6[v8] = (char)(v9 ^ v10);
                            ++var16_6;
lbl65:

                            v7 = v7;
                        } while (v7 > var16_6);
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
                    StoreScreen.b = var15;
                    break block30;
lbl77:

                    while (true) {
                        continue;
                        break;
                    }
                }
                var8_7 = 923867321434832580L;
                while (true)
                StoreScreen.c = 6942267923733288100L ^ var8_7;
                var0_8 = 8494110375745701838L;
                var6_9 = new long[7];
                var3_10 = 0;
                var4_11 = "\u0017E\u00eb\u0003\u00ca\u00b9\u00d9\u00d5\u0004\u009752\u00de\u007f\u0095\u00b6sn\u00e8\u00a1\u00ac\u00a1\u00f2\u0099b\u00a5F\u0006\u00e1\u00f8VU~\u00e4\u00c8L]\u0084\u0091\u00e5";
                var5_12 = "\u0017E\u00eb\u0003\u00ca\u00b9\u00d9\u00d5\u0004\u009752\u00de\u007f\u0095\u00b6sn\u00e8\u00a1\u00ac\u00a1\u00f2\u0099b\u00a5F\u0006\u00e1\u00f8VU~\u00e4\u00c8L]\u0084\u0091\u00e5".length();
                var2_13 = 0;
                while (true) {
                    var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                    v11 = var6_9;
                    v12 = var3_10++;
                    v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                    v14 = -1;
                    break block28;
                    break;
                }
lbl99:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    var4_11 = "Tw-\u0084\nt\u0094\u000b\\\u00fe\u0095\u0012\u000ePO\u00fe";
                    var5_12 = "Tw-\u0084\nt\u0094\u000b\\\u00fe\u0095\u0012\u000ePO\u00fe".length();
                    var2_13 = 0;
                    while (true) {
                        var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                        v11 = var6_9;
                        v12 = var3_10++;
                        v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                        v14 = 0;
                        break block28;
                        break;
                    }
                    break;
                }
lbl112:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    break block29;
                    break;
                }
            }
            v15 = v13 ^ var0_8;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl123:

                continue;
            }
        }
        StoreScreen.d = var6_9;
        StoreScreen.g = new Long[7];
        StoreScreen.FEATURED = new ArrayList<StoreScreen$Item>();
        StoreScreen.DAILY = new ArrayList<StoreScreen$Item>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x72E7) & Short.MAX_VALUE;
        if (g[n2] == null) {
            StoreScreen.g[n2] = d[n2] ^ l;
        }
        return g[n2];
    }
}
