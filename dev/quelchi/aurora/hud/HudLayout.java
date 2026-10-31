package dev.quelchi.aurora.hud;

import dev.quelchi.aurora.hud.ItemHighlights$Style;
import dev.quelchi.aurora.module.Module;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;

public final class HudLayout {
    public static final int PAD;
    public static boolean BYPASS;
    private final List<String> measured = new ArrayList<String>(4);
    private int width;
    private int height;
    private int lineHeight;
    private int iconWidth = -1;
    private double scale = -1.0;
    private final int[] bounds = new int[4];

    public boolean update(List<String> list, int n) {
        boolean bl;
        block6: {
            boolean bl2;
            int n2;
            double d;
            MinecraftClient minecraftClient;
            block5: {
                boolean bl3;
                block4: {
                    minecraftClient = MinecraftClient.method_1551();
                    d = minecraftClient.method_22683().method_4495();
                    n2 = ItemHighlights$Style.a;
                    bl3 = this.dirty(list, n, d);
                    if (n2 != 0) break block4;
                    if (bl3) break block5;
                    bl3 = false;
                }
                return bl3;
            }
            TextRenderer textRenderer = minecraftClient.field_1772;
            Objects.requireNonNull(textRenderer);
            this.lineHeight = 9 + 2;
            boolean n3 = false;
            for (int i = 0; i < list.size(); ++i) {
                bl = Math.max(bl2 ? 1 : 0, textRenderer.method_1727(list.get(i)));
                if (n2 == 0) {
                    bl2 = bl;
                    if (n2 == 0) continue;
                }
                break block6;
            }
            this.width = bl2 + 10 + n;
            this.height = list.size() * this.lineHeight - 2 + 10;
            this.measured.clear();
            this.measured.addAll(list);
            this.iconWidth = n;
            this.scale = d;
            bl = true;
        }
        return bl;
    }

    private boolean dirty(List<String> list, int n, double d) {
        boolean bl;
        block27: {
            int n2;
            double d2;
            int n3;
            block25: {
                int n4;
                block26: {
                    double d3;
                    block23: {
                        block24: {
                            int n5;
                            block21: {
                                block22: {
                                    block19: {
                                        block20: {
                                            n3 = ItemHighlights$Style.a;
                                            n5 = BYPASS;
                                            if (n3 != 0) break block19;
                                            if (n5 == 0) break block20;
                                            return true;
                                        }
                                        n5 = this.iconWidth;
                                    }
                                    if (n3 != 0) break block21;
                                    if (n5 != n) break block22;
                                    double d4 = this.scale - d;
                                    d3 = d4 == 0.0 ? 0 : (d4 > 0.0 ? 1 : -1);
                                    if (n3 != 0) break block23;
                                    if (d3 == false) break block24;
                                }
                                n5 = 1;
                            }
                            return n5 != 0;
                        }
                        d3 = list.size();
                    }
                    d2 = d3;
                    n4 = this.measured.size();
                    if (n3 != 0) break block25;
                    if (n4 == d2) break block26;
                    return true;
                }
                n4 = n2 = 0;
            }
            while (n2 < d2) {
                block29: {
                    boolean bl2;
                    block28: {
                        bl = this.measured.get(n2).equals(list.get(n2));
                        if (n3 != 0) break block27;
                        if (n3 != 0) break block28;
                        if (bl) break block29;
                        bl2 = true;
                    }
                    return bl2;
                }
                ++n2;
                if (n3 == 0) continue;
            }
            bl = false;
        }
        return bl;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public int lineHeight() {
        return this.lineHeight;
    }

    public int[] boundsAt(int n, int n2) {
        int[] nArray;
        block4: {
            boolean bl;
            block6: {
                block5: {
                    this.bounds[0] = n;
                    this.bounds[1] = n2;
                    this.bounds[2] = this.width;
                    this.bounds[3] = this.height;
                    int n3 = ItemHighlights$Style.a;
                    nArray = this.bounds;
                    if (n3 == 0) break block4;
                    if (!Module.a) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            Module.a = bl;
        }
        return nArray;
    }

    public int[] emptyAt(int n, int n2) {
        this.measured.clear();
        this.iconWidth = -1;
        this.width = 0;
        this.height = 0;
        return this.boundsAt(n, n2);
    }

    
    static {
        long l = 6068786520629717110L;
        long l2 = 0x88037019CBD02873L ^ l;
        PAD = (int)l2;
        BYPASS = false;
    }
}
