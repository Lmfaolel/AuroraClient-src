package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.gui.HudScale;
import dev.quelchi.aurora.gui.HudStyle;
import dev.quelchi.aurora.gui.HudStyle$Look;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.ModuleManager;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.ArmorHudModule$ArmorLayout;
import dev.quelchi.aurora.module.modules.ArmorHudModule$DurabilityStyle;
import dev.quelchi.aurora.module.modules.ArmorHudModule$TextSide;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;

public final class ArmorHudModule
extends Module
implements DraggableHud {
    private static ArmorHudModule instance;
    private final EnumSetting<ArmorHudModule$DurabilityStyle> style;
    private final EnumSetting<ArmorHudModule$ArmorLayout> layout;
    private final EnumSetting<ArmorHudModule$TextSide> textSide;
    private final NumberSetting barWidth;
    private final NumberSetting barHeight;
    private final BooleanSetting colorByDurability;
    private final ColorSetting durabilityColor;
    private final BooleanSetting breakWarning;
    private final EnumSetting<HudStyle$Look> panelStyle;
    private int hudX;
    private int hudY;
    private boolean placed;
    private int[] lastBounds;
    private final ItemStack[] iconSource;
    private final ItemStack[] iconCache;
    private int iconSlot;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;
    private static final long[] f;
    private static final Long[] g;

    public ArmorHudModule() {
        String[] stringArray = b;
        super(stringArray[14], stringArray[5], Category.HUD, false);
        this.style = new EnumSetting<ArmorHudModule$DurabilityStyle>(stringArray[17], ArmorHudModule$DurabilityStyle.Bars);
        this.layout = new EnumSetting<ArmorHudModule$ArmorLayout>(stringArray[7], ArmorHudModule$ArmorLayout.Horizontal);
        this.textSide = new EnumSetting<ArmorHudModule$TextSide>(stringArray[15], ArmorHudModule$TextSide.Right);
        this.barWidth = new NumberSetting(stringArray[2], 6.0, 182.0, 80.0, 1.0);
        this.barHeight = new NumberSetting(stringArray[9], 1.0, 6.0, 2.0, 1.0);
        this.colorByDurability = new BooleanSetting(stringArray[18], true);
        this.durabilityColor = new ColorSetting(stringArray[3], ArmorHudModule.a(9849, 3668532612380218037L), false);
        this.breakWarning = new BooleanSetting(stringArray[1], true);
        this.panelStyle = new EnumSetting<HudStyle$Look>(stringArray[11], HudStyle$Look.None);
        this.hudX = -1;
        this.hudY = -1;
        this.placed = false;
        this.lastBounds = new int[]{0, 0, 0, 0};
        this.iconSource = new ItemStack[4];
        this.iconCache = new ItemStack[4];
        instance = this;
        this.addSettings(this.style, this.layout, this.textSide, this.barWidth, this.barHeight, this.colorByDurability, this.durabilityColor, this.panelStyle, this.breakWarning);
        this.style.children(this.barWidth, this.barHeight, this.layout, this.textSide);
        this.durabilityColor.visibleWhen(this::lambda$new$0);
    }

    
    @Override
    public void onHudRender(DrawContext var1_1) {
        block116: {
            block115: {
                block114: {
                    block111: {
                        block112: {
                            block113: {
                                block109: {
                                    block110: {
                                        block107: {
                                            block108: {
                                                block102: {
                                                    block105: {
                                                        block106: {
                                                            block103: {
                                                                block104: {
                                                                    block89: {
                                                                        block117: {
                                                                            block98: {
                                                                                block99: {
                                                                                    block100: {
                                                                                        block101: {
                                                                                            block96: {
                                                                                                block97: {
                                                                                                    block94: {
                                                                                                        block95: {
                                                                                                            block93: {
                                                                                                                block92: {
                                                                                                                    block91: {
                                                                                                                        block90: {
                                                                                                                            block82: {
                                                                                                                                block88: {
                                                                                                                                    block86: {
                                                                                                                                        block87: {
                                                                                                                                            block85: {
                                                                                                                                                block83: {
                                                                                                                                                    block84: {
                                                                                                                                                        block80: {
                                                                                                                                                            block81: {
                                                                                                                                                                block79: {
                                                                                                                                                                    block78: {
                                                                                                                                                                        block77: {
                                                                                                                                                                            block76: {
                                                                                                                                                                                block75: {
                                                                                                                                                                                    var3_2 = MinecraftClient.method_1551();
                                                                                                                                                                                    var2_3 = AmbienceModule.b;
                                                                                                                                                                                    v0 = var3_2;
                                                                                                                                                                                    if (var2_3) break block75;
                                                                                                                                                                                    if (v0.field_1724 != null) {
                                                                                                                                                                                    }
                                                                                                                                                                                    v0 = var3_2;
                                                                                                                                                                                }
                                                                                                                                                                                if (!v0.field_1690.field_1842) break block76;
lbl19:

                                                                                                                                                                                return;
                                                                                                                                                                            }
                                                                                                                                                                            this.iconSlot = 0;
                                                                                                                                                                            var4_4 = this.collect((PlayerEntity)var3_2.field_1724);
                                                                                                                                                                            v4 = var4_4.isEmpty();
                                                                                                                                                                            if (var2_3) break block77;
                                                                                                                                                                            if (!v4) break block78;
                                                                                                                                                                            v4 = ModuleManager.editing();
                                                                                                                                                                        }
                                                                                                                                                                        if (var2_3) break block79;
                                                                                                                                                                        if (!v4) break block78;
                                                                                                                                                                        v4 = true;
                                                                                                                                                                        break block79;
                                                                                                                                                                    }
                                                                                                                                                                    v4 = false;
                                                                                                                                                                }
                                                                                                                                                                var5_5 = v4;
                                                                                                                                                                v7 = var5_5;
                                                                                                                                                                if (var2_3) break block80;
                                                                                                                                                                if (!v7) break block81;
                                                                                                                                                                var6_6 = new ItemStack((ItemConvertible)Items.field_8058);
                                                                                                                                                                var6_6.method_7974(var6_6.method_7936() / 3);
                                                                                                                                                                var4_4 = List.of(var6_6);
                                                                                                                                                            }
                                                                                                                                                            v7 = var4_4.isEmpty();
                                                                                                                                                        }
                                                                                                                                                        if (!v7) break block82;
                                                                                                                                                        v9 = this;
                                                                                                                                                        v10 = new int[4];
                                                                                                                                                        v11 = v10;
                                                                                                                                                        v12 = v10;
                                                                                                                                                        v13 = 0;
                                                                                                                                                        v14 = this;
                                                                                                                                                        if (var2_3) break block83;
                                                                                                                                                        if (v14.hudX >= 0) break block84;
                                                                                                                                                        v17 = 0;
                                                                                                                                                        break block85;
                                                                                                                                                    }
                                                                                                                                                    v14 = this;
                                                                                                                                                }
                                                                                                                                                v17 = v14.hudX;
                                                                                                                                            }
                                                                                                                                            v11[v13] = v17;
                                                                                                                                            v19 = v12;
                                                                                                                                            v20 = v12;
                                                                                                                                            v21 = 1;
                                                                                                                                            v22 = this;
                                                                                                                                            if (var2_3) break block86;
                                                                                                                                            if (v22.hudY >= 0) break block87;
                                                                                                                                            v24 = 0;
                                                                                                                                            break block88;
                                                                                                                                        }
                                                                                                                                        v22 = this;
                                                                                                                                    }
                                                                                                                                    v24 = v22.hudY;
                                                                                                                                }
                                                                                                                                v19[v21] = v24;
                                                                                                                                v20[2] = 0;
                                                                                                                                v20[3] = 0;
                                                                                                                                v9.lastBounds = v20;
                                                                                                                                return;
                                                                                                                            }
                                                                                                                            v26 = this.style.getValue() != ArmorHudModule$DurabilityStyle.Bars ? 1 : 0;
                                                                                                                            var9_9 = var8_8 = v26;
                                                                                                                            Objects.requireNonNull(var3_2.field_1772);
                                                                                                                            var10_10 = 9;
                                                                                                                            var11_11 = (int)this.barWidth.getValue();
                                                                                                                            var12_12 = (int)this.barHeight.getValue();
                                                                                                                            v28 = var8_8;
                                                                                                                            if (var2_3) break block89;
                                                                                                                            if (v28 != 0) {
                                                                                                                            }
                                                                                                                            if (this.layout.getValue() != ArmorHudModule$ArmorLayout.Horizontal) break block90;
                                                                                                                            v31 = 1;
                                                                                                                            break block91;
                                                                                                                        }
                                                                                                                        v31 = 0;
                                                                                                                    }
                                                                                                                    var13_13 = v31;
                                                                                                                    var14_14 = 16;
                                                                                                                    var15_15 = 4;
                                                                                                                    v33 = var3_2.field_1772;
                                                                                                                    if (this.style.getValue() != ArmorHudModule$DurabilityStyle.Percentage) break block92;
                                                                                                                    v34 = ArmorHudModule.b[6];
                                                                                                                    break block93;
                                                                                                                }
                                                                                                                var19_16 = ArmorHudModule.b;
                                                                                                                v34 = var19_16[0];
                                                                                                            }
                                                                                                            var16_17 = v33.method_1727(v34);
                                                                                                            v36 = var13_13;
                                                                                                            if (var2_3) break block94;
                                                                                                            if (v36 == 0) break block95;
                                                                                                            v38 = var4_4.size() * (var14_14 + var15_15) - var15_15;
                                                                                                            break block96;
                                                                                                        }
                                                                                                        v36 = var14_14;
                                                                                                        v40 = 4;
                                                                                                        if (var2_3) break block97;
                                                                                                        v36 = v36 + v40;
                                                                                                    }
                                                                                                    v40 = var9_9 != 0 ? var16_17 : 0;
                                                                                                }
                                                                                                v38 = v36 + v40;
                                                                                            }
                                                                                            var6_7 = v38;
                                                                                            v43 = var13_13;
                                                                                            if (var2_3) break block98;
                                                                                            if (v43 == 0) break block99;
                                                                                            v45 = var14_14;
                                                                                            v46 = var9_9;
                                                                                            if (var2_3) break block100;
                                                                                            if (v46 == 0) break block101;
                                                                                            v46 = var10_10 + 2;
                                                                                            break block100;
                                                                                        }
                                                                                        v46 = 0;
                                                                                    }
                                                                                    v50 = v45 + v46;
                                                                                    break block117;
                                                                                }
                                                                                v43 = var4_4.size() * (var14_14 + 2);
                                                                            }
                                                                            v50 = v43 - 2;
                                                                        }
                                                                        var7_18 = v50;
                                                                        if (!var2_3) break block102;
lbl226:

                                                                        v28 = 1;
                                                                    }
                                                                    var13_13 = v28;
                                                                    v52 = var12_12;
                                                                    v53 = var9_9;
                                                                    if (var2_3) break block103;
                                                                    if (v53 == 0) break block104;
                                                                    v53 = var10_10;
                                                                    break block103;
                                                                }
                                                                v53 = var12_12;
                                                            }
                                                            var14_14 = Math.max(v52, v53) + var13_13;
                                                            var7_18 = var4_4.size() * var14_14;
                                                            v55 = var11_11;
                                                            v56 = var9_9;
                                                            if (var2_3) break block105;
                                                            if (v56 == 0) break block106;
                                                            v56 = 3 + var3_2.field_1772.method_1727(ArmorHudModule.b[8]);
                                                            break block105;
                                                        }
                                                        v56 = 0;
                                                    }
                                                    var6_7 = v55 + v56;
                                                }
                                                v59 = this.placed;
                                                if (var2_3) break block107;
                                                if (v59 != 0) break block108;
                                                var13_13 = HudScale.width();
                                                var14_14 = HudScale.height();
                                                this.hudX = (var13_13 - var6_7) / 2;
                                                this.hudY = var14_14 - 22 - var7_18 - 2;
                                                this.placed = true;
                                                this.rememberHome();
                                            }
                                            HudStyle.panel(var1_1, this.hudX - 4, this.hudY - 4, var6_7 + 8, var7_18 + 8, ArmorHudModule.a(29575, 2767175345641719631L) | this.durabilityColor.getRGB(), this.panelStyle.getValue());
                                            if (var2_3) break block109;
                                            v59 = var8_8;
                                        }
                                        if (v59 == 0) break block110;
                                        this.drawClassic(var1_1, var3_2, var4_4, (boolean)var9_9);
                                        if (!var2_3) break block111;
                                    }
                                    var13_13 = this.hudX;
                                    var14_14 = this.hudY;
                                }
                                var15_15 = 1;
                                v63 = var12_12;
                                v64 = var9_9;
                                if (var2_3) break block112;
                                if (v64 == 0) break block113;
                                v64 = var10_10;
                                break block112;
                            }
                            v64 = var12_12;
                        }
                        var16_17 = Math.max(v63, v64) + var15_15;
                        for (ItemStack var18_20 : var4_4) {
                            if (!var2_3) {
                                this.drawBar(var1_1, var3_2, var13_13, var14_14, var11_11, var12_12, var18_20, (boolean)var9_9);
                                var14_14 += var16_17;
                                if (!var2_3) continue;
                            }
                            break block114;
                        }
                    }
                    this.lastBounds = new int[]{this.hudX, this.hudY, var6_7, var7_18};
                }
                v66 = this.breakWarning.getValue();
                if (var2_3) break block115;
                if (!v66) break block116;
                v66 = var5_5;
            }
            if (!v66) {
                this.drawBreakWarning(var1_1, var3_2, var4_4);
            }
        }
    }

    
    private void drawClassic(DrawContext var1_1, MinecraftClient var2_2, List<ItemStack> var3_3, boolean var4_4) {
        block50: {
            block51: {
                block49: {
                    block48: {
                        block47: {
                            var5_5 = AmbienceModule.b;
                            v0 = this.layout.getValue() == ArmorHudModule$ArmorLayout.Horizontal ? 1 : 0;
                            var6_6 = v0;
                            var7_7 = 16;
                            var8_8 = 4;
                            Objects.requireNonNull(var2_2.field_1772);
                            var9_9 = 9;
                            v2 = var6_6;
                            if (var5_5) break block47;
                            if (v2 != 0) break block48;
                            v2 = var4_4;
                        }
                        if (v2 == 0 || this.textSide.getValue() != ArmorHudModule$TextSide.Left) break block48;
                        v5 = 1;
                        break block49;
                    }
                    v5 = 0;
                }
                var10_10 = v5;
                v7 = var2_2.field_1772;
                v8 = this.style.getValue() == ArmorHudModule$DurabilityStyle.Percentage ? ArmorHudModule.b[8] : ArmorHudModule.b[13];
                var11_11 = v7.method_1727(v8);
                v10 = var10_10;
                if (var5_5) break block50;
                if (v10 == 0) break block51;
                v10 = this.hudX + var11_11 + 4;
                break block50;
            }
            v10 = this.hudX;
        }
        var12_12 = v10;
        var13_13 = this.hudY;
        for (ItemStack var15_15 : var3_3) {
            block66: {
                block65: {
                    block52: {
                        block53: {
                            block64: {
                                block62: {
                                    block63: {
                                        block61: {
                                            block59: {
                                                block60: {
                                                    block58: {
                                                        block56: {
                                                            block57: {
                                                                block67: {
                                                                    block55: {
                                                                        block54: {
                                                                            var16_16 = this.glintless(var15_15);
                                                                            var1_1.method_51427(var16_16, var12_12, var13_13);
                                                                            var1_1.method_51431(var2_2.field_1772, var16_16, var12_12, var13_13);
                                                                            v13 = var4_4;
                                                                            if (var5_5) break block52;
                                                                            if (v13 == 0) break block53;
                                                                            v15 = var15_15;
                                                                            if (var5_5) break block54;
                                                                            if (!v15.method_7963()) break block55;
                                                                            v15 = var15_15;
                                                                        }
                                                                        v19 = (float)(v15.method_7936() - var15_15.method_7919()) / (float)var15_15.method_7936();
                                                                        break block67;
                                                                    }
                                                                    v19 = 1.0f;
                                                                }
                                                                var17_17 = v19;
                                                                var17_17 = MathHelper.method_15363((float)var17_17, (float)0.0f, (float)1.0f);
                                                                v20 = this.colorByDurability.getValue();
                                                                if (var5_5) break block56;
                                                                if (v20 == 0) break block57;
                                                                v22 = ArmorHudModule.a(27229, 1123235125480300176L) | MathHelper.method_15369((float)(var17_17 / 3.0f), (float)1.0f, (float)1.0f);
                                                                break block58;
                                                            }
                                                            v20 = ArmorHudModule.a(27229, 1123235125480300176L);
                                                        }
                                                        v22 = var18_18 = v20 | this.durabilityColor.getRGB();
                                                    }
                                                    if (var15_15.method_7963()) // goto
                                                    var19_19 = "";
                                                    if (!var5_5) break block59;
lbl114:

                                                    if (this.style.getValue() != ArmorHudModule$DurabilityStyle.Percentage) break block60;
                                                    var19_19 = Math.round(var17_17 * 100.0f) + "%";
                                                    if (!var5_5) break block59;
                                                }
                                                var19_19 = String.valueOf(var15_15.method_7936() - var15_15.method_7919());
                                            }
                                            v25 = var6_6;
                                            if (var5_5) break block61;
                                            if (v25 != 0) {
                                            }
                                            var20_20 = var2_2.field_1772.method_1727((String)var19_19);
                                            var1_1.method_51433(var2_2.field_1772, (String)var19_19, var12_12 + (var7_7 - var20_20) / 2, var13_13 + var7_7 + 2, var18_18, true);
                                            if (!var5_5) break block53;
lbl135:

                                            v25 = var10_10;
                                        }
                                        if (var5_5) break block62;
                                        if (v25 == 0) break block63;
                                        v29 = var12_12 - 4 - var2_2.field_1772.method_1727((String)var19_19);
                                        break block64;
                                    }
                                    v25 = var12_12 + var7_7;
                                }
                                v29 = v25 + 4;
                            }
                            var20_20 = v29;
                            var1_1.method_51433(var2_2.field_1772, (String)var19_19, var20_20, var13_13 + (var7_7 - var9_9) / 2, var18_18, true);
                        }
                        v13 = var6_6;
                    }
                    if (var5_5) break block65;
                    if (v13 != 0) {
                    }
                    var12_12 += var7_7 + var8_8;
                    if (!var5_5) break block66;
lbl171:

                    v13 = var13_13 + (var7_7 + 2);
                }
                var13_13 = v13;
            }
            if (!var5_5) continue;
        }
    }

    private void drawBar(DrawContext drawContext, MinecraftClient minecraftClient, int n, int n2, int n3, int n4, ItemStack itemStack, boolean bl) {
        block18: {
            int n5;
            int n6;
            block17: {
                int n7;
                float f;
                boolean bl2;
                block16: {
                    int n8;
                    block14: {
                        block15: {
                            float f2;
                            block19: {
                                block13: {
                                    ItemStack itemStack2;
                                    block12: {
                                        bl2 = AmbienceModule.b;
                                        itemStack2 = itemStack;
                                        if (bl2) break block12;
                                        if (!itemStack2.method_7963()) break block13;
                                        itemStack2 = itemStack;
                                    }
                                    f2 = (float)(itemStack2.method_7936() - itemStack.method_7919()) / (float)itemStack.method_7936();
                                    break block19;
                                }
                                f2 = 1.0f;
                            }
                            f = f2;
                            f = MathHelper.method_15363((float)f, (float)0.0f, (float)1.0f);
                            n8 = this.colorByDurability.getValue();
                            if (bl2) break block14;
                            if (n8 == 0) break block15;
                            n7 = ArmorHudModule.a(27229, 1123235125480300176L) | MathHelper.method_15369((float)(f / 3.0f), (float)1.0f, (float)1.0f);
                            break block16;
                        }
                        n8 = ArmorHudModule.a(27229, 1123235125480300176L);
                    }
                    n7 = n8 | this.durabilityColor.getRGB();
                }
                n6 = n7;
                drawContext.method_25294(n - 1, n2 - 1, n + n3 + 1, n2 + n4 + 1, ArmorHudModule.a(12500, 4322490243608757278L));
                drawContext.method_25294(n, n2, n + n3, n2 + n4, ArmorHudModule.a(18044, 7546867659554861749L));
                drawContext.method_25294(n, n2, n + Math.round((float)n3 * f), n2 + n4, n6);
                n5 = bl;
                if (bl2) break block17;
                if (n5 == 0) break block18;
                n5 = Math.round(f * 100.0f);
            }
            String string = n5 + "%";
            Objects.requireNonNull(minecraftClient.field_1772);
            drawContext.method_51433(minecraftClient.field_1772, string, n + n3 + 3, n2 + (n4 - 9) / 2, n6, true);
        }
    }

    
    private void drawBreakWarning(DrawContext var1_1, MinecraftClient var2_2, List<ItemStack> var3_3) {
        block26: {
            block27: {
                block25: {
                    block22: {
                        var5_4 = 0;
                        var6_5 = var3_3.iterator();
                        var4_6 = AmbienceModule.b;
                        while (var6_5.hasNext()) {
                            block24: {
                                block23: {
                                    var7_7 = var6_5.next();
                                    if (var4_6) break block22;
                                    v0 = var7_7;
                                    if (var4_6) break block23;
                                    if (v0.method_7963()) {
                                    }
                                    v0 = var7_7;
                                }
                                v4 = v0.method_7936();
                                if (var4_6) break block24;
                                if ((float)(v4 - var7_7.method_7919()) / (float)var7_7.method_7936() <= 0.1f) {
                                }
                                v4 = 1;
                            }
                            var5_4 = v4;
                            if (!var4_6) break;
lbl42:

                            if (!var4_6) continue;
                            break;
                        }
                        v8 = var5_4;
                        if (var4_6) break block25;
                        if (v8 != 0) break block22;
                        return;
                    }
                    v8 = (cfr_temp_0 = System.currentTimeMillis() / ArmorHudModule.b(21476, 8450402736469545354L) % ArmorHudModule.b(5948, 4388998331148916051L) - 0L) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                }
                if (v8 == 0) {
                    return;
                }
                var6_5 = var1_1.method_51448();
                var6_5.pushMatrix();
                var7_8 = this.getHudScale();
                v10 = var7_8;
                if (var4_6) break block26;
                if (v10 == 1.0f) break block27;
                var6_5.translate((float)this.getHudX(), (float)this.getHudY());
                var6_5.scale(1.0f / var7_8, 1.0f / var7_8);
                var6_5.translate((float)(-this.getHudX()), (float)(-this.getHudY()));
            }
            v10 = HudScale.factor();
        }
        var8_9 = v10;
        var6_5.scale(1.0f / var8_9, 1.0f / var8_9);
        var9_10 = var1_1.method_51421() / 2;
        var10_11 = var1_1.method_51443() / 2;
        var1_1.method_51433(var2_2.field_1772, "!", var9_10 + 10, var10_11 - 4, ArmorHudModule.a(32193, 3558136544488491274L), true);
        var6_5.popMatrix();
    }

    private ItemStack glintless(ItemStack itemStack) {
        int n;
        ItemStack[] itemStackArray;
        block10: {
            int n2;
            block11: {
                block8: {
                    ArmorHudModule armorHudModule;
                    block9: {
                        n2 = this.iconSlot++ & 3;
                        boolean bl = AmbienceModule.b;
                        armorHudModule = this;
                        if (bl) break block8;
                        if (armorHudModule.iconSource[n2] == null) break block9;
                        itemStackArray = this.iconSource;
                        n = n2;
                        if (bl) break block10;
                        if (ItemStack.method_7973((ItemStack)itemStackArray[n], (ItemStack)itemStack)) break block11;
                    }
                    armorHudModule = this;
                }
                armorHudModule.iconSource[n2] = itemStack.method_7972();
                ItemStack itemStack2 = itemStack.method_7972();
                itemStack2.method_57379(DataComponentTypes.field_49641, (Object)false);
                this.iconCache[n2] = itemStack2;
            }
            itemStackArray = this.iconCache;
            n = n2;
        }
        return itemStackArray[n];
    }

    private List<ItemStack> collect(PlayerEntity playerEntity) {
        EquipmentSlot[] equipmentSlotArray;
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        EquipmentSlot[] equipmentSlotArray2 = equipmentSlotArray = new EquipmentSlot[]{EquipmentSlot.field_6169, EquipmentSlot.field_6174, EquipmentSlot.field_6172, EquipmentSlot.field_6166};
        int n = equipmentSlotArray2.length;
        int n2 = 0;
        boolean bl = AmbienceModule.b;
        while (n2 < n) {
            block9: {
                block10: {
                    EquipmentSlot equipmentSlot = equipmentSlotArray2[n2];
                    ItemStack itemStack = playerEntity.method_6118(equipmentSlot);
                    if (bl) break block9;
                    if (itemStack.method_7960()) break block10;
                    boolean bl2 = itemStack.method_7963();
                    if (bl) break block10;
                    if (!bl2) break block10;
                    bl2 = arrayList.add(itemStack);
                }
                ++n2;
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    @Override
    public int getHudX() {
        int n;
        block6: {
            ArmorHudModule armorHudModule;
            block4: {
                block5: {
                    boolean bl = AmbienceModule.b;
                    armorHudModule = this;
                    if (bl) break block4;
                    if (armorHudModule.hudX >= 0) break block5;
                    n = 0;
                    break block6;
                }
                armorHudModule = this;
            }
            n = armorHudModule.hudX;
        }
        return n;
    }

    @Override
    public int getHudY() {
        int n;
        block6: {
            ArmorHudModule armorHudModule;
            block4: {
                block5: {
                    boolean bl = AmbienceModule.b;
                    armorHudModule = this;
                    if (bl) break block4;
                    if (armorHudModule.hudY >= 0) break block5;
                    n = 0;
                    break block6;
                }
                armorHudModule = this;
            }
            n = armorHudModule.hudY;
        }
        return n;
    }

    @Override
    public void rememberHome() {
        block9: {
            ArmorHudModule armorHudModule;
            block10: {
                boolean bl;
                block8: {
                    bl = AmbienceModule.b;
                    armorHudModule = this;
                    if (bl) break block8;
                    if (armorHudModule.hudX < 0) break block9;
                    armorHudModule = this;
                }
                if (bl) break block10;
                if (armorHudModule.hudY < 0) break block9;
                armorHudModule = this;
            }
            DraggableHud.super.rememberHome();
        }
    }

    @Override
    public void resetHudPos() {
        this.placed = false;
        this.hudY = -1;
        this.hudX = -1;
        this.setHudScale(1.0f);
        this.setHudHidden(false);
    }

    @Override
    public void setHudPos(int n, int n2) {
        this.hudX = n;
        this.hudY = n2;
        this.placed = true;
    }

    @Override
    public int[] getLastBounds() {
        return this.lastBounds;
    }

    @Override
    public void writeExtra(JsonObject jsonObject) {
        if (this.placed) {
            jsonObject.addProperty(b[4], (Number)this.hudX);
            jsonObject.addProperty(b[10], (Number)this.hudY);
        }
    }

    @Override
    public void readExtra(JsonObject jsonObject) {
        block8: {
            JsonObject jsonObject2;
            block7: {
                boolean bl = AmbienceModule.b;
                jsonObject2 = jsonObject;
                if (bl) break block7;
                if (!jsonObject2.has(b[16])) break block8;
                jsonObject2 = jsonObject;
            }
            if (jsonObject2.has(b[12])) {
                this.hudX = jsonObject.get(b[16]).getAsInt();
                this.hudY = jsonObject.get(b[12]).getAsInt();
                this.placed = true;
            }
        }
    }

    private Boolean lambda$new$0() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = AmbienceModule.b;
                bl = this.colorByDurability.getValue();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    
    static {
        block32: {
            block31: {
                block30: {
                    block29: {
                        block28: {
                            block27: {
                                var21 = new String[19];
                                var19_1 = 0;
                                var18_2 = "\u0003\u0005\u000fQ\rsGY\u0001\t_\u0016PGR\t\f\u0018\tsTN@\u0015\u0016\u0005E]\u0010u@N\u0001\u0000\u0016\rXAE@\u0001\u0010\r^G\u0004Y@X8\u0018pGQ\u000f\u0010_\u0005DG]\u0002\u000b\u0013\bEL\u001c\u0004\u000b\f\u0011]TE\u0004\u0000\u0005\fE\u0006}TE\u000f\u0017\u000b\u0004\u0000\u0005\fE\nsTN@\n\u001a\bV]H\u0004Y@X9\u000baTR\u0005\u000e_\u0012ELP\u0005\u0004Y@X9\u0004\u0003\u0005\u000fQ\tpGQ\u000f\u0010_)dq\tePD\u0014B\f\bUP\u0004Y@X8";
                                var20_3 = "\u0003\u0005\u000fQ\rsGY\u0001\t_\u0016PGR\t\f\u0018\tsTN@\u0015\u0016\u0005E]\u0010u@N\u0001\u0000\u0016\rXAE@\u0001\u0010\r^G\u0004Y@X8\u0018pGQ\u000f\u0010_\u0005DG]\u0002\u000b\u0013\bEL\u001c\u0004\u000b\f\u0011]TE\u0004\u0000\u0005\fE\u0006}TE\u000f\u0017\u000b\u0004\u0000\u0005\fE\nsTN@\n\u001a\bV]H\u0004Y@X9\u000baTR\u0005\u000e_\u0012ELP\u0005\u0004Y@X9\u0004\u0003\u0005\u000fQ\tpGQ\u000f\u0010_)dq\tePD\u0014B\f\bUP\u0004Y@X8".length();
                                var17_4 = 4;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block27;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "u@N\u0001\u0000\u0016\rXAE@\u0011\u000b\u0018]P\u0013rZP\u000f\u0010_\u0003H\u0015X\u0015\u0010\u001e\u0003XYU\u0014\u001b";
                                    var20_3 = "u@N\u0001\u0000\u0016\rXAE@\u0011\u000b\u0018]P\u0013rZP\u000f\u0010_\u0003H\u0015X\u0015\u0010\u001e\u0003XYU\u0014\u001b".length();
                                    var17_4 = 16;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block27;
                                        break;
                                    }
                                    break;
                                }
lbl26:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    break block28;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_6 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_6;
                                v9 = v6[v8];
                                switch (var22_6 % 7) {
                                    case 0: {
                                        v10 = 49;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 53;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 60;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 96;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 98;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 127;
                                        break;
                                    }
                                    default: {
                                        v10 = 97;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_6;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_6);
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
                        ArmorHudModule.b = var21;
                        var8_7 = 4610235369340546264L;
                        var14_8 = new long[6];
                        var11_9 = 0;
                        var12_10 = "~_Z\u00a1[M\u00ab\u0097x\u00e5f]1\u00e1\u00a5V\u00c4(x\u00ca+\u0018h\u00c65\u00ca+)S\u0011\u0095\u00e2";
                        var13_11 = "~_Z\u00a1[M\u00ab\u0097x\u00e5f]1\u00e1\u00a5V\u00c4(x\u00ca+\u0018h\u00c65\u00ca+)S\u0011\u0095\u00e2".length();
                        var10_12 = 0;
                        while (true) {
                            var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                            v11 = var14_8;
                            v12 = var11_9++;
                            v13 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                            v14 = -1;
                            break block29;
                            break;
                        }
lbl89:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_12 < var13_11) continue;
                            var12_10 = "\u00f4\u0002O\u00e9\u00e8\u0010j\u009b\u00df\u00c03\u000f\u00b2\u0002\u00b2H";
                            var13_11 = "\u00f4\u0002O\u00e9\u00e8\u0010j\u009b\u00df\u00c03\u000f\u00b2\u0002\u00b2H".length();
                            var10_12 = 0;
                            while (true) {
                                var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                                v11 = var14_8;
                                v12 = var11_9++;
                                v13 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                                v14 = 0;
                                break block29;
                                break;
                            }
                            break;
                        }
lbl102:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_12 < var13_11) continue;
                            break block30;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_7;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl113:

                        continue;
                    }
                }
                ArmorHudModule.d = var14_8;
                ArmorHudModule.e = new Integer[6];
                var0_14 = 414455324968250844L;
                var6_15 = new long[2];
                var3_16 = 0;
                var4_17 = "p\u0085\u00achPA\u00fd\u00c69(\u00aaV{\u00e9\u00b8\u008d";
                var5_18 = "p\u0085\u00achPA\u00fd\u00c69(\u00aaV{\u00e9\u00b8\u008d".length();
                var2_19 = 0;
                while (true) {
                    break block31;
                    break;
                }
lbl125:

                while (true) {
                    var6_15[v16] = (((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L) ^ var0_14;
                    if (var2_19 < var5_18) continue;
                    break block32;
                    break;
                }
            }
            var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
            v16 = var3_16++;
            while (true)
        }
        ArmorHudModule.f = var6_15;
        ArmorHudModule.g = new Long[2];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x74C8;
        if (e[n2] == null) {
            ArmorHudModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x426E) & Short.MAX_VALUE;
        if (g[n2] == null) {
            ArmorHudModule.g[n2] = f[n2] ^ l;
        }
        return g[n2];
    }
}
