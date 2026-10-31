package dev.quelchi.aurora.gui.shell;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.gui.shell.LockerScreen;
import dev.quelchi.aurora.gui.shell.RewardsBadge;
import dev.quelchi.aurora.gui.shell.RewardsScreen$Entry;
import dev.quelchi.aurora.gui.shell.RewardsScreen$Milestone;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.gui.shell.Shop;
import dev.quelchi.aurora.gui.shell.StoreScreen;
import dev.quelchi.aurora.gui.shell.WardrobeBar;
import imgui.ImDrawList;
import imgui.ImGui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class RewardsScreen
extends ImScreen {
    private static final float PAD = 20.0f;
    private static final float DAY_H = 104.0f;
    private static final float MILE_H = 50.0f;
    private static final float ROW_H = 34.0f;
    private static final List<Integer> AMOUNTS;
    private static final List<RewardsScreen$Milestone> MILESTONES;
    private static final Map<String, String> NAMES;
    private static final List<RewardsScreen$Entry> HISTORY;
    private static int day;
    private static boolean claimedToday;
    private static boolean loaded;
    private static boolean failed;
    private static boolean loading;
    private static boolean stale;
    private static long nextAt;
    private static long fetchedAt;
    private static UUID loadedFor;
    private String status = "";
    private String claiming;
    private static final String[] b;

    public RewardsScreen() {
        super(b[16]);
    }

    @Override
    public boolean shutsOnMenuKey() {
        return true;
    }

    private static void refresh() {
        block6: {
            boolean bl;
            block5: {
                boolean bl2 = SearchIndex.a;
                bl = loading;
                if (bl2) break block5;
                if (!bl) break block6;
                bl = true;
            }
            stale = bl;
            return;
        }
        if (CosmeticsSync.token() == null) {
            return;
        }
        loading = true;
        UUID uUID = CosmeticsSync.ownUuid();
        CosmeticsSync.fetch(b[6], arg_0 -> RewardsScreen.lambda$refresh$0(uUID, arg_0));
    }

    
    private static void apply(JsonObject var0, UUID var1_1) {
        block30: {
            block29: {
                block28: {
                    block24: {
                        block25: {
                            block26: {
                                block27: {
                                    RewardsScreen.loading = false;
                                    var2_2 = SearchIndex.a;
                                    v0 = RewardsScreen.stale != false ? 0L : System.currentTimeMillis();
                                    RewardsScreen.fetchedAt = v0;
                                    if (var2_2) break block24;
                                    RewardsScreen.stale = false;
                                    if (var0 != null) break block25;
                                    v3 = RewardsScreen.loaded;
                                    if (var2_2) break block26;
                                    if (v3) break block27;
                                    v3 = true;
                                    break block26;
                                }
                                v3 = false;
                            }
                            RewardsScreen.failed = v3;
                            return;
                        }
                        RewardsScreen.loadedFor = var1_1;
                        RewardsScreen.failed = false;
                        v6 = true;
                        if (var2_2) break block28;
                        RewardsScreen.loaded = v6;
                    }
                    if (var1_1 == null) break block29;
                    v6 = var1_1.equals(CosmeticsSync.ownUuid());
                }
                if (v6) {
                    RewardsBadge.seen(var0);
                }
            }
            var8_3 = RewardsScreen.b;
            var3_4 = var0.getAsJsonObject(var8_3[20]);
            RewardsScreen.day = var3_4.get(var8_3[37]).getAsInt();
            RewardsScreen.claimedToday = var3_4.get(var8_3[44]).getAsBoolean();
            RewardsScreen.nextAt = var3_4.get(var8_3[5]).getAsLong();
            RewardsScreen.AMOUNTS.clear();
            for (JsonElement var5_6 : var3_4.getAsJsonArray(var8_3[2])) {
                if (!var2_2) {
                    RewardsScreen.AMOUNTS.add(var5_6.getAsInt());
                    if (!var2_2) continue;
                    break;
                }
                break block30;
            }
            RewardsScreen.MILESTONES.clear();
        }
        var8_3 = RewardsScreen.b;
        var4_5 = var0.getAsJsonArray(var8_3[7]).iterator();
        while (var4_5.hasNext()) {
            var5_6 = (JsonElement)var4_5.next();
            var6_7 = var5_6.getAsJsonObject();
            var8_3 = RewardsScreen.b;
            var7_8 = new RewardsScreen$Milestone(var6_7.get(var8_3[47]).getAsString(), var6_7.get(var8_3[8]).getAsString(), var6_7.get(var8_3[24]).getAsLong(), var6_7.get(var8_3[48]).getAsLong(), var6_7.get(var8_3[25]).getAsInt(), var6_7.get(var8_3[33]).getAsBoolean());
            RewardsScreen.MILESTONES.add(var7_8);
            v11 = RewardsScreen.NAMES;
            if (!var2_2) {
                v11.put((String)var7_8.id, (String)var7_8.name);
                if (!var2_2) continue;
                break;
            }
        }
        RewardsScreen.HISTORY.clear();
        var8_3 = RewardsScreen.b;
        for (Object v11 : var0.getAsJsonArray(var8_3[27])) lbl_neg_1000:

        {
            var5_6 = (JsonElement)v11;
            var6_7 = var5_6.getAsJsonObject();
            var8_3 = RewardsScreen.b;
            RewardsScreen.HISTORY.add(new RewardsScreen$Entry(var6_7.get(var8_3[55]).getAsInt(), var6_7.get(var8_3[45]).getAsString()));
            if (!var2_2) continue;
        }
    }

    private void claim(String string) {
        block4: {
            RewardsScreen rewardsScreen;
            block5: {
                boolean bl = SearchIndex.a;
                rewardsScreen = this;
                if (bl) break block4;
                if (rewardsScreen.claiming == null) break block5;
                return;
            }
            rewardsScreen = this;
        }
        rewardsScreen.claiming = string;
        CosmeticsSync.claim(string, this::lambda$claim$1);
    }

    @Override
    protected void draw() {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        block36: {
            block37: {
                float f7;
                float f8;
                boolean bl;
                float f9;
                float f10;
                String[] stringArray;
                block32: {
                    String string;
                    block33: {
                        block35: {
                            block34: {
                                int n;
                                block28: {
                                    block29: {
                                        int n2;
                                        long l;
                                        block30: {
                                            block31: {
                                                block26: {
                                                    block27: {
                                                        stringArray = b;
                                                        Menu.begin(stringArray[23], 0.0f);
                                                        f10 = Menu.x + 144.0f;
                                                        f9 = Menu.y;
                                                        bl = SearchIndex.a;
                                                        f8 = Menu.w - 144.0f;
                                                        f7 = Menu.h;
                                                        Ui.text(Im.sora13, f10 + 20.0f, Ui.mid(Im.sora13, f9, 56.0f), Im.INK, stringArray[29]);
                                                        n = 2;
                                                        if (bl) break block26;
                                                        WardrobeBar.draw(n, f9);
                                                        if (!loaded) break block27;
                                                        n = Objects.equals(loadedFor, CosmeticsSync.ownUuid()) ? 1 : 0;
                                                        if (bl) break block26;
                                                        if (n != 0) break block27;
                                                        loaded = false;
                                                    }
                                                    n = loading ? 1 : 0;
                                                }
                                                if (bl) break block28;
                                                if (n != 0) break block29;
                                                l = System.currentTimeMillis() - fetchedAt;
                                                n2 = loaded;
                                                if (bl) break block30;
                                                if (n2 == 0) break block31;
                                                n2 = 30000;
                                                break block30;
                                            }
                                            n2 = 5000;
                                        }
                                        long l2 = l - (long)n2;
                                        n = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                                        if (bl) break block28;
                                        if (n <= 0) break block29;
                                        RewardsScreen.refresh();
                                    }
                                    n = loaded ? 1 : 0;
                                }
                                if (n != 0) break block32;
                                string = CosmeticsSync.token();
                                if (bl) break block33;
                                if (string != null) break block34;
                                stringArray = b;
                                string = stringArray[51];
                                break block33;
                            }
                            if (!failed) break block35;
                            string = b[52];
                            break block33;
                        }
                        stringArray = b;
                        string = stringArray[35];
                    }
                    String string2 = string;
                    Ui.text(Im.sub, f10 + (f8 - Ui.width(Im.sub, string2)) / 2.0f, f9 + 56.0f + (f7 - 56.0f) / 2.0f - 8.0f, Im.MUTE, string2);
                    Menu.end();
                    return;
                }
                f6 = f10 + 20.0f;
                f5 = f9 + 56.0f + 20.0f;
                float f11 = f8 - 40.0f;
                stringArray = b;
                Ui.label(f6, f5, stringArray[15]);
                String string = stringArray[54] + StoreScreen.left(nextAt);
                Ui.text(Im.label, f6 + f11 - Ui.width(Im.label, string), f5, Im.MUTE, string);
                this.days(f6, f5 += 19.0f, f11);
                f4 = (f11 - 20.0f) / 2.0f;
                f3 = f9 + f7 - 20.0f - (f5 += 118.0f) - 19.0f;
                f2 = f6;
                f = f5;
                if (bl) break block36;
                Ui.label(f2, f, stringArray[39]);
                if (this.status.isEmpty()) break block37;
                Ui.text(Im.label, f6 + f4 - Ui.width(Im.label, this.status), f5, Im.WARN, this.status);
            }
            Ui.label(f6 + f4 + 20.0f, f5, b[50]);
            f2 = f5;
            f = 19.0f;
        }
        f5 = f2 + f;
        ImGui.pushStyleColor((int)3, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        ImGui.pushStyleVar((int)8, (float)0.0f);
        this.milestones(f6, f5, f4, f3);
        this.history(f6 + f4 + 20.0f, f5, f4, f3);
        ImGui.popStyleVar();
        ImGui.popStyleColor();
        Menu.end();
    }

    
    private void days(float var1_1, float var2_2, float var3_3) {
        var5_4 = ImGui.getWindowDrawList();
        var6_5 = RewardsScreen.AMOUNTS.size();
        var4_6 = SearchIndex.a;
        var7_7 = (float)Math.floor((var3_3 - (float)(10 * (var6_5 - 1))) / (float)Math.max(1, var6_5));
        for (var8_8 = 0; var8_8 < var6_5; ++var8_8) {
            block69: {
                block65: {
                    block68: {
                        block66: {
                            block67: {
                                block63: {
                                    block64: {
                                        block60: {
                                            block61: {
                                                block62: {
                                                    block58: {
                                                        block59: {
                                                            block57: {
                                                                block56: {
                                                                    block55: {
                                                                        block51: {
                                                                            block54: {
                                                                                block52: {
                                                                                    block53: {
                                                                                        var9_9 = var1_1 + (float)var8_8 * (var7_7 + 10.0f);
                                                                                        var10_10 = var8_8 + 1;
                                                                                        v0 = var10_10;
                                                                                        if (var4_6) break block51;
                                                                                        if (v0 < RewardsScreen.day) break block52;
                                                                                        v0 = var10_10;
                                                                                        if (var4_6) break block53;
                                                                                        if (v0 != RewardsScreen.day) break block54;
                                                                                        v0 = (int)RewardsScreen.claimedToday;
                                                                                    }
                                                                                    if (var4_6) break block51;
                                                                                    if (v0 == 0) break block54;
                                                                                }
                                                                                v0 = 1;
                                                                                break block51;
                                                                            }
                                                                            v0 = 0;
                                                                        }
                                                                        var11_11 = v0;
                                                                        v6 = var10_10;
                                                                        if (var4_6) break block55;
                                                                        if (v6 != RewardsScreen.day) break block56;
                                                                        v6 = (int)RewardsScreen.claimedToday;
                                                                    }
                                                                    if (var4_6) break block57;
                                                                    if (v6 != 0) break block56;
                                                                    v6 = 1;
                                                                    break block57;
                                                                }
                                                                v6 = 0;
                                                            }
                                                            var12_12 = v6;
                                                            v10 = 0;
                                                            v11 = var11_11 != 0 ? 0.55f : 1.0f;
                                                            ImGui.pushStyleVar((int)v10, (float)v11);
                                                            v13 = var12_12;
                                                            if (var4_6) break block58;
                                                            if (v13 == 0) break block59;
                                                            var5_4.addRectFilled(var9_9, var2_2, var9_9 + var7_7, var2_2 + 104.0f, Ui.col(Menu.ACCENT, 0.12f), 12.0f);
                                                            var5_4.addRect(var9_9, var2_2, var9_9 + var7_7, var2_2 + 104.0f, Ui.col(Menu.ACCENT, 0.85f), 12.0f);
                                                            if (!var4_6) break block60;
                                                        }
                                                        v13 = var11_11;
                                                    }
                                                    if (v13 != 0) {
                                                        var5_4.addRectFilled(var9_9, var2_2, var9_9 + var7_7, var2_2 + 104.0f, Ui.wash(0.03f), 12.0f);
                                                    }
                                                    v18 = var5_4;
                                                    v19 = var9_9;
                                                    v20 = var2_2;
                                                    v21 = var9_9 + var7_7;
                                                    v22 = var2_2;
                                                    v23 = 104.0f;
                                                    if (var4_6) break block61;
                                                    v22 = v22 + v23;
                                                    if (var11_11 == 0) break block62;
                                                    v23 = 0.08f;
                                                    break block61;
                                                }
                                                v23 = 0.16f;
                                            }
                                            v18.addRect(v19, v20, v21, v22, Ui.wash(v23), 12.0f);
                                        }
                                        v26 = var2_2;
                                        v27 = var12_12;
                                        if (var4_6) break block63;
                                        if (v27 == 0) break block64;
                                        v27 = 15;
                                        break block63;
                                    }
                                    v27 = 19;
                                }
                                var13_13 = v26 + (float)v27;
                                var18_18 = RewardsScreen.b;
                                var14_14 = var18_18[53] + var10_10;
                                var15_15 = Shop.fmt(RewardsScreen.AMOUNTS.get(var8_8).intValue());
                                v29 = Im.label;
                                v30 = var9_9 + (float)Math.floor((var7_7 - Ui.width(Im.label, var14_14)) / 2.0f);
                                v31 = var13_13;
                                v32 = Im.MUTE;
                                if (var4_6) break block65;
                                Ui.text(v29, v30, v31, v32, var14_14);
                                WardrobeBar.gem(var9_9 + (var7_7 - 22.0f) / 2.0f, var13_13 + 21.0f, 22.0f);
                                if (var12_12 != 0) {
                                }
                                v34 = RewardsScreen.b[22];
                                if (var4_6) break block66;
                                if (!v34.equals(this.claiming)) break block67;
                                v37 = RewardsScreen.b[19];
                                break block68;
                            }
                            v34 = var15_15;
                        }
                        var18_18 = RewardsScreen.b;
                        v37 = var18_18[42] + v34;
                    }
                    var16_16 = v37;
                    var17_17 = Ui.width(Im.sora12r, var16_16) + 24.0f;
                    if (Menu.button(var16_16 + RewardsScreen.b[40], var9_9 + (float)Math.floor((var7_7 - var17_17) / 2.0f), var13_13 + 49.0f, var17_17, 24.0f, true)) {
                        this.claim(RewardsScreen.b[1]);
                    }
                    if (!var4_6) break block69;
lbl188:

                    v29 = Im.sora13;
                    v30 = var9_9 + (float)Math.floor((var7_7 - Ui.width(Im.sora13, var15_15)) / 2.0f);
                    v31 = var13_13 + 49.0f;
                    v32 = Im.INK;
                }
                Ui.text(v29, v30, v31, v32, var15_15);
            }
            if (var11_11 != 0) {
                var5_4.addCircleFilled(var9_9 + var7_7 - 15.0f, var2_2 + 15.0f, 7.0f, Ui.col(Menu.FILL), 20);
                Menu.tick(var9_9 + var7_7 - 15.0f, var2_2 + 15.0f, 3.2f, ImGui.getColorU32((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f));
            }
            ImGui.popStyleVar();
            if (!var4_6) continue;
        }
    }

    private static float group(String string, float f, float f2, float f3, float f4, float f5) {
        float f6 = Math.min(f4, f5);
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        imDrawList.addRectFilled(f, f2, f + f3, f2 + f6, Ui.wash(0.03f), 8.0f);
        imDrawList.addRect(f, f2, f + f3, f2 + f6, Ui.wash(0.08f), 8.0f);
        ImGui.setCursorScreenPos((float)f, (float)f2);
        ImGui.beginChild((String)string, (float)f3, (float)f6, (boolean)false);
        return ImGui.getCursorScreenPosY();
    }

    
    private void milestones(float var1_1, float var2_2, float var3_3, float var4_4) {
        block41: {
            var5_5 = SearchIndex.a;
            if (RewardsScreen.MILESTONES.isEmpty()) {
                Ui.text(Im.sub, var1_1, var2_2, Im.MUTE, RewardsScreen.b[9]);
                return;
            }
            var17_6 = RewardsScreen.b;
            var7_8 = var6_7 = RewardsScreen.group(var17_6[30], var1_1, var2_2, var3_3, (float)RewardsScreen.MILESTONES.size() * 50.0f, var4_4);
            var8_9 = ImGui.getWindowDrawList();
            for (RewardsScreen$Milestone var10_11 : RewardsScreen.MILESTONES) {
                block49: {
                    block50: {
                        block48: {
                            block51: {
                                block44: {
                                    block47: {
                                        block45: {
                                            block46: {
                                                block42: {
                                                    block43: {
                                                        if (var5_5) break block41;
                                                        if (var5_5) break block42;
                                                        if (!(var6_7 > var7_8)) break block43;
                                                        var8_9.addLine(var1_1, var6_7, var1_1 + var3_3, var6_7, Ui.wash(0.06f));
                                                    }
                                                    Ui.text(Im.sora12r, var1_1 + 14.0f, Ui.mid(Im.sora12r, var6_7, 50.0f), Im.INK, LockerScreen.fit(Im.sora12r, var10_11.name, 130.0f));
                                                }
                                                var11_12 = Shop.fmt(var10_11.reward);
                                                var12_13 = var1_1 + var3_3 - 14.0f;
                                                v4 = var10_11;
                                                if (var5_5) break block44;
                                                if (!v4.claimed) {
                                                }
                                                v4 = var10_11;
                                                if (var5_5) break block44;
                                                if (v4.have >= var10_11.need) {
                                                }
                                                v8 = var10_11.id;
                                                if (var5_5) break block45;
                                                if (!v8.equals(this.claiming)) break block46;
                                                v11 = RewardsScreen.b[13];
                                                break block47;
                                            }
                                            v8 = var11_12;
                                        }
                                        var17_6 = RewardsScreen.b;
                                        v11 = var17_6[56] + v8;
                                    }
                                    var14_16 = v11;
                                    var15_17 = Ui.width(Im.sora12r, var14_16) + 24.0f;
                                    var13_14 = var12_13 - var15_17;
                                    if (Menu.button(var14_16 + RewardsScreen.b[31] + var10_11.id, var13_14, var6_7 + 11.0f, var15_17, 28.0f, true)) {
                                        this.claim(var10_11.id);
                                    }
                                    if (!var5_5) break block48;
lbl87:

                                    v4 = var10_11;
                                }
                                if (!v4.claimed) break block51;
                                var17_6 = RewardsScreen.b;
                                var13_14 = var12_13 - Ui.width(Im.label, var17_6[41]);
                                Ui.text(Im.label, var13_14, Ui.mid(Im.label, var6_7, 50.0f), Im.MUTE, var17_6[36]);
                                if (!var5_5) break block48;
                            }
                            var13_14 = WardrobeBar.amount(var11_12, var12_13, var6_7, 50.0f, Im.INK);
                        }
                        var14_15 = var1_1 + 158.0f;
                        var15_17 = var13_14 - 14.0f - var14_15;
                        v15 = var10_11.need == 0L ? 1.0f : Math.min(1.0f, (float)var10_11.have / (float)var10_11.need);
                        var16_18 = v15;
                        var8_9.addRectFilled(var14_15, var6_7 + 22.5f, var14_15 + var15_17, var6_7 + 27.5f, Ui.wash(0.1f), 3.0f);
                        if (var5_5) break block49;
                        if (!(var16_18 > 0.0f)) break block50;
                        var8_9.addRectFilled(var14_15, var6_7 + 22.5f, var14_15 + Math.max(5.0f, var15_17 * var16_18), var6_7 + 27.5f, Ui.col(Menu.ICON_FOOT), 3.0f);
                    }
                    var6_7 += 50.0f;
                }
                if (!var5_5) continue;
            }
            ImGui.setCursorScreenPos((float)var1_1, (float)(var6_7 - 1.0f));
            ImGui.dummy((float)1.0f, (float)1.0f);
            ImGui.endChild();
        }
    }

    private void history(float f, float f2, float f3, float f4) {
        block15: {
            float f5;
            boolean bl = SearchIndex.a;
            if (HISTORY.isEmpty()) {
                Ui.text(Im.sub, f, f2, Im.MUTE, b[12]);
                return;
            }
            float f6 = f5 = RewardsScreen.group(b[0], f, f2, f3, (float)HISTORY.size() * 34.0f, f4);
            ImDrawList imDrawList = ImGui.getWindowDrawList();
            for (RewardsScreen$Entry rewardsScreen$Entry : HISTORY) {
                block14: {
                    block16: {
                        if (bl) break block14;
                        if (bl) break block15;
                        if (!(f5 > f6)) break block16;
                        imDrawList.addLine(f, f5, f + f3, f5, Ui.wash(0.06f));
                    }
                    Ui.text(Im.sora12r, f + 12.0f, Ui.mid(Im.sora12r, f5, 34.0f), Im.INK, LockerScreen.fit(Im.sora12r, RewardsScreen.describe(rewardsScreen$Entry.reason), f3 - 110.0f));
                }
                String string = rewardsScreen$Entry.delta > 0 ? "+" : "";
                String string2 = string + Shop.fmt(rewardsScreen$Entry.delta);
                float f7 = f + f3 - 12.0f;
                float f8 = f5;
                float f9 = 34.0f;
                float[] fArray = rewardsScreen$Entry.delta > 0 ? Im.GREEN : Im.INK;
                WardrobeBar.amount(string2, f7, f8, f9, fArray);
                f5 += 34.0f;
                if (!bl) continue;
            }
            ImGui.setCursorScreenPos((float)f, (float)(f5 - 1.0f));
            ImGui.dummy((float)1.0f, (float)1.0f);
            ImGui.endChild();
        }
    }

    
    private static String describe(String var0) {
        block56: {
            var2_1 = var0.split(":", 3);
            var3_2 = var2_1[0];
            var1_3 = SearchIndex.a;
            var4_4 = -1;
            v0 = var3_2.hashCode();
            if (var1_3) break block56;
            switch (v0) {
                case 95346201: {
                    v0 = (int)var3_2.equals(RewardsScreen.b[1]);
                    if (!var1_3) {
                        if (v0 == 0) break;
                        var4_4 = 0;
                        if (!var1_3) break;
                    }
                    break block56;
                }
                case 1879712769: {
                    v0 = (int)var3_2.equals(RewardsScreen.b[28]);
                    if (!var1_3) {
                        if (v0 == 0) break;
                        var4_4 = 1;
                        if (!var1_3) break;
                    }
                    break block56;
                }
                case 695623622: {
                    v0 = (int)var3_2.equals(RewardsScreen.b[26]);
                    if (!var1_3) {
                        if (v0 == 0) break;
                        var4_4 = 2;
                        if (!var1_3) break;
                    }
                    break block56;
                }
                case 97926: {
                    v0 = (int)var3_2.equals(RewardsScreen.b[34]);
                    if (!var1_3) {
                        if (v0 == 0) break;
                        var4_4 = 3;
                        if (!var1_3) break;
                    }
                    break block56;
                }
                case -1377881982: {
                    v0 = (int)var3_2.equals(RewardsScreen.b[17]);
                    if (!var1_3) {
                        if (v0 == 0) break;
                        var4_4 = 4;
                        if (!var1_3) break;
                    }
                    break block56;
                }
                case -1065084560: {
                    v0 = (int)var3_2.equals(RewardsScreen.b[38]);
                    if (!var1_3) {
                        if (v0 == 0) break;
                        var4_4 = 5;
                        if (!var1_3) break;
                    }
                    break block56;
                }
                case 92668751: {
                    v0 = (int)var3_2.equals(RewardsScreen.b[57]);
                    if (var1_3) break block56;
                    if (v0 == 0) break;
                    var4_4 = 6;
                }
            }
            v0 = var4_4;
        }
        if (var1_3) // goto
        switch (v0) {
            case 0: {
                v1 = var2_1;
                if (var1_3) // goto
                v0 = v1.length;
lbl63:

                if (v0 <= 1) {
                    var5_6 = RewardsScreen.b;
                    v2 = var5_6[3];
                    return v2;
                }
                v1 = var2_1;
lbl68:

                var5_5 = RewardsScreen.b;
                v2 = var5_5[49] + v1[1];
                return v2;
            }
            case 1: {
                var5_7 = RewardsScreen.b;
                v2 = var5_7[43];
                return v2;
            }
            case 2: {
                var5_8 = RewardsScreen.b;
                v2 = var5_8[21];
                return v2;
            }
            case 3: {
                v3 = var2_1;
                if (!var1_3) {
                    if (v3.length <= 2) {
                        var5_10 = RewardsScreen.b;
                        v2 = var5_10[32];
                        return v2;
                    }
                    v3 = var2_1;
                }
                var5_9 = RewardsScreen.b;
                v2 = var5_9[11] + Shop.label(v3[1], var2_1[2]);
                return v2;
            }
            case 4: {
                var5_11 = RewardsScreen.b;
                v2 = var5_11[4];
                return v2;
            }
            case 5: {
                v4 = var2_1;
                if (var1_3) // goto
                if (v4.length > 1) {
                    v4 = var2_1;
lbl99:

                    v5 = v4[1];
                } else {
                    v5 = "";
                }
                var5_12 = RewardsScreen.b;
                v2 = var5_12[14] + RewardsScreen.NAMES.getOrDefault(v5, var5_12[18]);
                return v2;
            }
            case 6: {
                var5_13 = RewardsScreen.b;
                v2 = var5_13[46];
                return v2;
            }
        }
        v2 = RewardsScreen.b[10];
        return v2;
    }

    private void lambda$claim$1(String string) {
        String string2;
        block2: {
            block3: {
                this.claiming = null;
                boolean bl = SearchIndex.a;
                RewardsScreen rewardsScreen = this;
                string2 = string;
                if (bl) break block2;
                if (string2 != null) break block3;
                string2 = "";
                break block2;
            }
            string2 = string;
        }
        rewardsScreen.status = string2;
        RewardsScreen.refresh();
    }

    private static void lambda$refresh$0(UUID uUID, JsonObject jsonObject) {
        RewardsScreen.apply(jsonObject, uUID);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[58];
                var3_1 = 0;
                var2_2 = "QaZ\u0010YRw\u0005]i@\bO\u0007XeF\u0011XT}\f}i@\bO\u0000|\\\u007fH\u0016R\u000f{g\\\u0003^T.X(K\u0011XDb\\\u0006WmQ\u0010wT\f\u0016iY\r\u0019RkNi[\u0000E\nTaE\u0001ETaWmZ\u0004WiD\u0001\fwg]\f_Ni\u0019qL\u0010\u0018\nxlC\u0011ETc\\f]\u0007{g\\\u0003^T.\fwg]\f_Ni\u0019qL\u0010\u0018\u0003\u0017&\u0007\u000btaE\u0001ETaWm\u0013D\f}i@\bO\u0000|\\\u007fH\u0016R\u0007km^\u0005DD}\u0006[}G\u0000ZE\u0006Km^\u0005DD\u0003\u0017&\u0007\u0006J|[\u0001WK\u0017idH\u001dBIc\\(Z\fWRjJ([\u0001[Ox\\l\u0005]i@\bO\bni[\u0000DOl\\\u0004Qi_\u0001\u0006Km^\u0005DD\bZdH\u0013TAmR\u0007QaZ\u0010YRw\bIdH\u001dBIc\\\u0007km^\u0005DD}\u0005TaE\u0001E\u0002\u001a+\bi}[\u0007^A}\\\u0007ZdH\r[Ej\u0003[}P\u0012ugH\u0000_Ni\u0019zL\u0013WRjJ&\u0007J\u0007zdH\r[Ej\u0003]iP\tTaE\u0001ETaWm\ntaE\u0001ETaWmZ\u0007\u001a+M\u0005_Lw\u0007zdH\r[Ej\u0006zdH\r[\u0000\bidH\u001dBIc\\\fZdH\r[EjmgM\u0005O\u0006KmH\u0017YN\u0011\u007fzF\t\u0016a{Kg[\u0005\u0016SzXnO\u0002Pl\u0004WmL\u0000\u0012}i@\bO\u0000|\\\u007fH\u0016R\f.]iPD\u0007qaZ\u0010YRw\u0017wg]DEIiWmMD_N.Mg\t%CRaKi$km^\u0005DD}\u0019i[\u0001\u0016NaM([\u0001WCfXjE\u0001\u0016Rg^`]DXOy\u0017\u0004}iPD\bwmQ\u0010\u0016I`\u0019\u0005]mE\u0010W";
                var4_3 = "QaZ\u0010YRw\u0005]i@\bO\u0007XeF\u0011XT}\f}i@\bO\u0000|\\\u007fH\u0016R\u000f{g\\\u0003^T.X(K\u0011XDb\\\u0006WmQ\u0010wT\f\u0016iY\r\u0019RkNi[\u0000E\nTaE\u0001ETaWmZ\u0004WiD\u0001\fwg]\f_Ni\u0019qL\u0010\u0018\nxlC\u0011ETc\\f]\u0007{g\\\u0003^T.\fwg]\f_Ni\u0019qL\u0010\u0018\u0003\u0017&\u0007\u000btaE\u0001ETaWm\u0013D\f}i@\bO\u0000|\\\u007fH\u0016R\u0007km^\u0005DD}\u0006[}G\u0000ZE\u0006Km^\u0005DD\u0003\u0017&\u0007\u0006J|[\u0001WK\u0017idH\u001dBIc\\(Z\fWRjJ([\u0001[Ox\\l\u0005]i@\bO\bni[\u0000DOl\\\u0004Qi_\u0001\u0006Km^\u0005DD\bZdH\u0013TAmR\u0007QaZ\u0010YRw\bIdH\u001dBIc\\\u0007km^\u0005DD}\u0005TaE\u0001E\u0002\u001a+\bi}[\u0007^A}\\\u0007ZdH\r[Ej\u0003[}P\u0012ugH\u0000_Ni\u0019zL\u0013WRjJ&\u0007J\u0007zdH\r[Ej\u0003]iP\tTaE\u0001ETaWm\ntaE\u0001ETaWmZ\u0007\u001a+M\u0005_Lw\u0007zdH\r[Ej\u0006zdH\r[\u0000\bidH\u001dBIc\\\fZdH\r[EjmgM\u0005O\u0006KmH\u0017YN\u0011\u007fzF\t\u0016a{Kg[\u0005\u0016SzXnO\u0002Pl\u0004WmL\u0000\u0012}i@\bO\u0000|\\\u007fH\u0016R\f.]iPD\u0007qaZ\u0010YRw\u0017wg]DEIiWmMD_N.Mg\t%CRaKi$km^\u0005DD}\u0019i[\u0001\u0016NaM([\u0001WCfXjE\u0001\u0016Rg^`]DXOy\u0017\u0004}iPD\bwmQ\u0010\u0016I`\u0019\u0005]mE\u0010W".length();
                var1_4 = 7;
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
                    var2_2 = "zdH\r[\u0000\u0005XlD\rX";
                    var4_3 = "zdH\r[\u0000\u0005XlD\rX".length();
                    var1_4 = 6;
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
                        v10 = 57;
                        break;
                    }
                    case 1: {
                        v10 = 8;
                        break;
                    }
                    case 2: {
                        v10 = 41;
                        break;
                    }
                    case 3: {
                        v10 = 100;
                        break;
                    }
                    case 4: {
                        v10 = 54;
                        break;
                    }
                    case 5: {
                        v10 = 32;
                        break;
                    }
                    default: {
                        v10 = 14;
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
        RewardsScreen.b = var5;
        RewardsScreen.AMOUNTS = new ArrayList<Integer>();
        RewardsScreen.MILESTONES = new ArrayList<RewardsScreen$Milestone>();
        RewardsScreen.NAMES = new HashMap<String, String>();
        RewardsScreen.HISTORY = new ArrayList<RewardsScreen$Entry>();
    }
}
