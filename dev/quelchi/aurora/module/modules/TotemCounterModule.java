package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.gui.HudScale;
import dev.quelchi.aurora.gui.HudStyle;
import dev.quelchi.aurora.gui.HudStyle$Look;
import dev.quelchi.aurora.mixins.MinecraftClientAccessor;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.ExtraHuds;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.ModuleManager;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import dev.quelchi.aurora.module.modules.TotemCounterModule$CounterIcon;
import dev.quelchi.aurora.module.modules.TotemCounterModule$CounterSide;
import dev.quelchi.aurora.module.modules.TotemCounterModule$CounterStyle;
import dev.quelchi.aurora.module.modules.TotemCounterModule$OwnDisplay;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.KeybindSetting;
import dev.quelchi.aurora.render.HudElement;
import dev.quelchi.aurora.util.KeyUtil;
import dev.quelchi.aurora.util.TotemPops;
import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.object.AtlasTextObjectContents;
import net.minecraft.text.object.TextObjectContents;
import net.minecraft.util.Atlases;
import net.minecraft.util.Identifier;
import org.joml.Matrix3x2fStack;

public final class TotemCounterModule
extends Module
implements DraggableHud,
ExtraHuds {
    private static final int MAX_ROWS;
    private static final int PAD;
    private static TotemCounterModule instance;
    private final BooleanSetting showHud;
    private final BooleanSetting onNametag;
    private final BooleanSetting onTabList;
    private final EnumSetting<TotemCounterModule$CounterSide> tagSide;
    private final BooleanSetting tagDivider;
    private final ColorSetting color;
    private final BooleanSetting ownTotems;
    private final EnumSetting<TotemCounterModule$OwnDisplay> ownDisplay;
    private final ColorSetting countColor;
    private final BooleanSetting ownBackground;
    private final EnumSetting<HudStyle$Look> panelStyle;
    private final EnumSetting<TotemCounterModule$CounterIcon> icon;
    private final EnumSetting<TotemCounterModule$CounterSide> side;
    private final EnumSetting<TotemCounterModule$CounterStyle> style;
    private final KeybindSetting resetKey;
    private int hudX;
    private int hudY;
    private int[] lastBounds;
    private boolean resetWasDown;
    private final HudElement ownTotemsHud;
    private static final Identifier TOTEM_SPRITE;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public static TotemCounterModule get() {
        return instance;
    }

    public TotemCounterModule() {
        String[] stringArray = b;
        super(stringArray[18], stringArray[24], Category.HUD, false);
        this.showHud = new BooleanSetting(stringArray[4], true);
        this.onNametag = new BooleanSetting(stringArray[22], true);
        this.onTabList = new BooleanSetting(stringArray[14], false);
        this.tagSide = new EnumSetting<TotemCounterModule$CounterSide>(stringArray[19], TotemCounterModule$CounterSide.Right);
        this.tagDivider = new BooleanSetting(stringArray[7], false);
        this.color = new ColorSetting(stringArray[17], TotemCounterModule.a(30544, 1967750556468586376L), false);
        this.ownTotems = new BooleanSetting(stringArray[20], false);
        this.ownDisplay = new EnumSetting<TotemCounterModule$OwnDisplay>(stringArray[13], TotemCounterModule$OwnDisplay.Hotbar);
        this.countColor = new ColorSetting(stringArray[25], TotemCounterModule.a(25512, 383663481476166514L), false);
        this.ownBackground = new BooleanSetting(stringArray[15], true);
        this.panelStyle = new EnumSetting<HudStyle$Look>(stringArray[0], HudStyle$Look.Glass);
        this.icon = new EnumSetting<TotemCounterModule$CounterIcon>(stringArray[12], TotemCounterModule$CounterIcon.Totem);
        this.side = new EnumSetting<TotemCounterModule$CounterSide>(stringArray[10], TotemCounterModule$CounterSide.Left);
        this.style = new EnumSetting<TotemCounterModule$CounterStyle>(stringArray[23], TotemCounterModule$CounterStyle.Boxed);
        this.resetKey = new KeybindSetting(stringArray[11], -1);
        this.hudX = TotemCounterModule.a(18242, 4513989110851770268L);
        this.hudY = TotemCounterModule.a(5232, 2735693557259715753L);
        this.lastBounds = new int[]{8, 60, 0, 0};
        this.ownTotemsHud = new HudElement(stringArray[8], 8, 90);
        instance = this;
        this.addSettings(this.showHud, this.onNametag, this.onTabList, this.tagSide, this.tagDivider, this.color, this.icon, this.side, this.style, this.panelStyle, this.ownTotems, this.ownDisplay, this.countColor, this.ownBackground, this.resetKey);
        this.showHud.children(this.side, this.style);
        this.onNametag.children(this.tagSide, this.tagDivider);
        this.ownTotems.children(this.ownDisplay, this.countColor, this.ownBackground);
        this.countColor.visibleWhen(this::lambda$new$0);
        this.ownBackground.visibleWhen(this::lambda$new$1);
        this.showHud.tab(stringArray[6]);
        this.ownTotems.tab(stringArray[9]);
        this.resetKey.tab(stringArray[9]);
        this.onNametag.tab(stringArray[27]);
        this.onTabList.tab(stringArray[26]);
    }

    @Override
    public void onTick() {
        block12: {
            int n;
            block15: {
                TotemCounterModule totemCounterModule;
                block14: {
                    int n2;
                    block13: {
                        int n3;
                        boolean bl;
                        block11: {
                            TotemPops.tick(this.mc);
                            int n4 = this.resetKey.getKey();
                            bl = AmbienceModule.b;
                            n3 = n4;
                            if (bl) break block11;
                            if (n3 == -1) break block12;
                            n3 = KeyUtil.isKeyPressedInGame(n4) ? 1 : 0;
                        }
                        n = n3;
                        n2 = n;
                        if (bl) break block13;
                        if (n2 == 0) break block14;
                        totemCounterModule = this;
                        if (bl) break block15;
                        n2 = totemCounterModule.resetWasDown ? 1 : 0;
                    }
                    if (n2 == 0) {
                        TotemPops.resetAll();
                    }
                }
                totemCounterModule = this;
            }
            totemCounterModule.resetWasDown = n;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Text tabListSegment(UUID uUID) {
        int n;
        int n2;
        block24: {
            TotemCounterModule totemCounterModule;
            block25: {
                UUID uUID2;
                boolean bl;
                block23: {
                    boolean bl2;
                    block21: {
                        block22: {
                            TotemCounterModule totemCounterModule2;
                            block20: {
                                block19: {
                                    totemCounterModule = instance;
                                    bl = AmbienceModule.b;
                                    totemCounterModule2 = totemCounterModule;
                                    if (bl) break block19;
                                    if (totemCounterModule2 == null) return null;
                                    totemCounterModule2 = totemCounterModule;
                                }
                                if (bl) break block20;
                                if (!totemCounterModule2.isEnabled()) return null;
                                totemCounterModule2 = totemCounterModule;
                            }
                            bl2 = totemCounterModule2.onTabList.getValue();
                            if (bl) break block21;
                            if (bl2) break block22;
                            return null;
                        }
                        uUID2 = uUID;
                        if (bl) break block23;
                        bl2 = TotemCounterModule.tracked(uUID2);
                    }
                    if (!bl2) {
                        return null;
                    }
                    uUID2 = uUID;
                }
                n2 = TotemPops.get(uUID2);
                n = n2;
                if (bl) break block24;
                if (n > 0) break block25;
                return null;
            }
            n = totemCounterModule.color.getRGB();
        }
        int n3 = n;
        return Text.method_43470((String)(b[21] + n2)).method_27694(arg_0 -> TotemCounterModule.lambda$tabListSegment$2(n3, arg_0));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Text popsSegment(UUID uUID) {
        TotemCounterModule totemCounterModule;
        int n;
        int n2;
        block21: {
            int n3;
            boolean bl;
            block19: {
                block20: {
                    int n4;
                    block17: {
                        block18: {
                            TotemCounterModule totemCounterModule2;
                            block16: {
                                bl = AmbienceModule.b;
                                totemCounterModule2 = this;
                                if (bl) break block16;
                                if (!totemCounterModule2.isEnabled()) return null;
                                totemCounterModule2 = this;
                            }
                            n4 = totemCounterModule2.onNametag.getValue();
                            if (bl) break block17;
                            if (n4 != 0) break block18;
                            return null;
                        }
                        n4 = TotemPops.get(uUID);
                    }
                    n2 = n4;
                    n3 = n2;
                    if (bl) break block19;
                    if (n3 > 0) break block20;
                    return null;
                }
                n3 = this.color.getRGB();
            }
            n = n3;
            totemCounterModule = this;
            if (bl) return Text.method_43470((String)(totemCounterModule.textSymbol() + n2)).method_27694(arg_0 -> TotemCounterModule.lambda$popsSegment$4(n, arg_0));
            if (totemCounterModule.icon.getValue() != TotemCounterModule$CounterIcon.Totem) break block21;
            return Text.method_43473().method_10852((Text)Text.method_74062((TextObjectContents)new AtlasTextObjectContents(Atlases.field_64477, TOTEM_SPRITE))).method_10852((Text)Text.method_43470((String)String.valueOf(n2)).method_27694(arg_0 -> TotemCounterModule.lambda$popsSegment$3(n, arg_0)));
        }
        totemCounterModule = this;
        return Text.method_43470((String)(totemCounterModule.textSymbol() + n2)).method_27694(arg_0 -> TotemCounterModule.lambda$popsSegment$4(n, arg_0));
    }

    public boolean nametagRight() {
        boolean bl = this.tagSide.getValue() == TotemCounterModule$CounterSide.Right;
        return bl;
    }

    public boolean nametagDivider() {
        return this.tagDivider.getValue();
    }

    
    private String textSymbol() {
        switch (this.icon.getValue().ordinal()) {
            case 2: {
                return "\u2605";
            }
            case 3: {
                return "\u2665";
            }
            case 4: {
                return "";
            }
        }
        return "\u2716";
    }

    @Override
    public void onHudRender(DrawContext drawContext) {
        block77: {
            TotemCounterModule totemCounterModule;
            int n;
            int n2;
            int n3;
            ArrayList<CallSite> arrayList;
            ArrayList<String> arrayList2;
            int n4;
            int n5;
            int n6;
            int n7;
            boolean bl;
            MinecraftClient minecraftClient;
            block75: {
                block76: {
                    block74: {
                        int n8;
                        block73: {
                            block83: {
                                String string;
                                List<Map.Entry<UUID, Integer>> list;
                                block72: {
                                    block71: {
                                        int n9;
                                        boolean bl2;
                                        block69: {
                                            block70: {
                                                int n10;
                                                block68: {
                                                    int n11;
                                                    block66: {
                                                        block67: {
                                                            block65: {
                                                                boolean bl3;
                                                                block64: {
                                                                    boolean bl4;
                                                                    block63: {
                                                                        block62: {
                                                                            block61: {
                                                                                TotemCounterModule totemCounterModule2;
                                                                                block60: {
                                                                                    boolean bl5;
                                                                                    block58: {
                                                                                        block59: {
                                                                                            block57: {
                                                                                                MinecraftClient minecraftClient2;
                                                                                                block56: {
                                                                                                    minecraftClient = MinecraftClient.method_1551();
                                                                                                    bl = AmbienceModule.b;
                                                                                                    minecraftClient2 = minecraftClient;
                                                                                                    if (bl) break block56;
                                                                                                    if (minecraftClient2.field_1724 == null) break block57;
                                                                                                    minecraftClient2 = minecraftClient;
                                                                                                }
                                                                                                bl5 = minecraftClient2.field_1690.field_1842;
                                                                                                if (bl) break block58;
                                                                                                if (!bl5) break block59;
                                                                                            }
                                                                                            return;
                                                                                        }
                                                                                        totemCounterModule2 = this;
                                                                                        if (bl) break block60;
                                                                                        totemCounterModule2.renderOwnTotems(drawContext, minecraftClient);
                                                                                        bl5 = this.showHud.getValue();
                                                                                    }
                                                                                    if (!bl5) {
                                                                                        this.lastBounds = new int[]{this.hudX, this.hudY, 0, 0};
                                                                                        return;
                                                                                    }
                                                                                    totemCounterModule2 = this;
                                                                                }
                                                                                list = totemCounterModule2.sortedPops();
                                                                                bl4 = list.isEmpty();
                                                                                if (bl) break block61;
                                                                                if (!bl4) break block62;
                                                                                bl4 = ModuleManager.editing();
                                                                            }
                                                                            if (bl) break block63;
                                                                            if (!bl4) break block62;
                                                                            bl4 = true;
                                                                            break block63;
                                                                        }
                                                                        bl4 = false;
                                                                    }
                                                                    bl2 = bl4;
                                                                    bl3 = list.isEmpty();
                                                                    if (bl) break block64;
                                                                    if (!bl3) break block65;
                                                                    bl3 = bl2;
                                                                }
                                                                if (!bl3) {
                                                                    this.lastBounds = new int[]{this.hudX, this.hudY, 0, 0};
                                                                    return;
                                                                }
                                                            }
                                                            int n12 = this.icon.getValue() == TotemCounterModule$CounterIcon.Totem ? 1 : 0;
                                                            n7 = n12;
                                                            Objects.requireNonNull(minecraftClient.field_1772);
                                                            n6 = 9;
                                                            n11 = n7;
                                                            if (bl) break block66;
                                                            if (n11 == 0) break block67;
                                                            n10 = 16;
                                                            break block68;
                                                        }
                                                        n11 = n6;
                                                    }
                                                    n10 = n11 + 2;
                                                }
                                                n5 = n10;
                                                n9 = n7;
                                                if (bl) break block69;
                                                if (n9 == 0) break block70;
                                                n9 = 18;
                                                break block69;
                                            }
                                            n9 = 0;
                                        }
                                        n4 = n9;
                                        String string2 = n7 != 0 ? "" : this.textSymbol();
                                        string = string2;
                                        arrayList2 = new ArrayList<String>();
                                        arrayList = new ArrayList<CallSite>();
                                        if (bl) break block71;
                                        if (!bl2) break block72;
                                        arrayList2.add(b[5]);
                                        arrayList.add((CallSite)((Object)(string + "3")));
                                    }
                                    if (!bl) break block83;
                                }
                                for (Map.Entry<UUID, Integer> entry : list) {
                                    n8 = arrayList2.add(TotemCounterModule.nameOf(minecraftClient, entry.getKey())) ? 1 : 0;
                                    if (!bl) {
                                        arrayList.add((CallSite)((Object)(string + String.valueOf(entry.getValue()))));
                                        if (!bl) continue;
                                        break;
                                    }
                                    break block73;
                                }
                            }
                            n8 = 0;
                        }
                        int n13 = n8;
                        for (n3 = 0; n3 < arrayList2.size(); ++n3) {
                            n13 = Math.max(n13, minecraftClient.field_1772.method_1727((String)arrayList2.get(n3) + " " + (String)arrayList.get(n3)));
                            if (!bl) {
                                if (!bl) continue;
                                break;
                            }
                            break block74;
                        }
                        n3 = n4 + n13 + 8;
                    }
                    n2 = arrayList2.size() * n5 + 8;
                    n = TotemCounterModule.a(11989, 7680138367864798734L) | this.color.getRGB();
                    totemCounterModule = this;
                    if (bl) break block75;
                    if (totemCounterModule.style.getValue() != TotemCounterModule$CounterStyle.Boxed) break block76;
                    HudStyle.panel(drawContext, this.hudX, this.hudY, n3, n2, n, this.panelStyle.getValue());
                }
                totemCounterModule = this;
            }
            int n14 = totemCounterModule.hudY + 4;
            for (int i = 0; i < arrayList2.size(); ++i) {
                int n15;
                int n16;
                String string;
                String string3;
                block81: {
                    block82: {
                        int n17;
                        block80: {
                            TotemCounterModule totemCounterModule3;
                            block78: {
                                block79: {
                                    string3 = (String)arrayList2.get(i);
                                    string = (String)arrayList.get(i);
                                    int n18 = n4 + minecraftClient.field_1772.method_1727(string3 + " " + string);
                                    if (bl) break block77;
                                    totemCounterModule3 = this;
                                    if (bl) break block78;
                                    if (totemCounterModule3.side.getValue() != TotemCounterModule$CounterSide.Right) break block79;
                                    n17 = this.hudX + n3 - 4 - n18;
                                    break block80;
                                }
                                totemCounterModule3 = this;
                            }
                            n17 = totemCounterModule3.hudX + 4;
                        }
                        n16 = n17;
                        n15 = n7;
                        if (bl) break block81;
                        if (n15 == 0) break block82;
                        Sprite sprite = ((MinecraftClientAccessor)minecraftClient).divine$getAtlasManager().method_73025(Atlases.field_64477).method_4608(TOTEM_SPRITE);
                        drawContext.method_52709(RenderPipelines.field_56883, sprite, n16, n14 + (n5 - 16) / 2, 16, 16);
                    }
                    n15 = n14 + (n5 - n6) / 2;
                }
                int n19 = n15;
                int n20 = n16 + n4;
                drawContext.method_51433(minecraftClient.field_1772, string3, n20, n19, TotemCounterModule.a(23984, 6710598052587572589L), true);
                drawContext.method_51433(minecraftClient.field_1772, string, n20 + minecraftClient.field_1772.method_1727(string3 + " "), n19, n, true);
                n14 += n5;
                if (!bl) continue;
            }
            this.lastBounds = new int[]{this.hudX, this.hudY, n3, n2};
        }
    }

    private void renderOwnTotems(DrawContext drawContext, MinecraftClient minecraftClient) {
        HudStyle$Look hudStyle$Look;
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        DrawContext drawContext2;
        Matrix3x2fStack matrix3x2fStack;
        int n6;
        int n7;
        int n8;
        int n9;
        int n10;
        int n11;
        int n12;
        String string;
        block50: {
            block49: {
                TotemCounterModule totemCounterModule;
                block48: {
                    boolean bl;
                    block47: {
                        block46: {
                            float f;
                            block44: {
                                block45: {
                                    int n13;
                                    block43: {
                                        TotemCounterModule totemCounterModule2;
                                        block41: {
                                            block42: {
                                                block37: {
                                                    block38: {
                                                        block40: {
                                                            MinecraftClient minecraftClient2;
                                                            block39: {
                                                                block35: {
                                                                    block36: {
                                                                        bl = AmbienceModule.b;
                                                                        totemCounterModule2 = this;
                                                                        if (bl) break block35;
                                                                        if (totemCounterModule2.ownTotems.getValue()) break block36;
                                                                        this.ownTotemsHud.clearBounds();
                                                                        return;
                                                                    }
                                                                    totemCounterModule2 = this;
                                                                }
                                                                if (bl) break block37;
                                                                if (totemCounterModule2.ownDisplay.getValue() != TotemCounterModule$OwnDisplay.Hotbar) break block38;
                                                                this.ownTotemsHud.clearBounds();
                                                                minecraftClient2 = minecraftClient;
                                                                if (bl) break block39;
                                                                if (minecraftClient2.field_1761 == null) break block40;
                                                                minecraftClient2 = minecraftClient;
                                                            }
                                                            if (minecraftClient2.field_1761.method_2908()) {
                                                                this.renderHotbarTotems(drawContext, minecraftClient);
                                                            }
                                                        }
                                                        return;
                                                    }
                                                    totemCounterModule2 = this;
                                                }
                                                if (bl) break block41;
                                                if (!totemCounterModule2.ownTotemsHud.hudHidden()) break block42;
                                                n13 = ModuleManager.editing() ? 1 : 0;
                                                if (bl) break block43;
                                                if (n13 != 0) break block42;
                                                this.ownTotemsHud.clearBounds();
                                                return;
                                            }
                                            totemCounterModule2 = this;
                                        }
                                        n13 = totemCounterModule2.countOwnTotems(minecraftClient);
                                    }
                                    int n14 = n13;
                                    string = String.valueOf(n14);
                                    Objects.requireNonNull(minecraftClient.field_1772);
                                    n12 = 9;
                                    n11 = this.ownTotemsHud.getHudX();
                                    n10 = this.ownTotemsHud.getHudY();
                                    n9 = TotemCounterModule.a(11318, 2734819249535810785L) | this.color.getRGB();
                                    int n15 = minecraftClient.field_1772.method_1727(string);
                                    n8 = 5;
                                    n7 = n8 + 16 + 4 + n15 + n8;
                                    n6 = 16 + n8 * 2;
                                    matrix3x2fStack = drawContext.method_51448();
                                    matrix3x2fStack.pushMatrix();
                                    float f2 = this.getHudScale();
                                    f = f2;
                                    if (bl) break block44;
                                    if (f == 1.0f) break block45;
                                    matrix3x2fStack.translate((float)this.hudX, (float)this.hudY);
                                    matrix3x2fStack.scale(1.0f / f2, 1.0f / f2);
                                    matrix3x2fStack.translate((float)(-this.hudX), (float)(-this.hudY));
                                }
                                f = this.ownTotemsHud.getHudScale();
                            }
                            float f3 = f;
                            if (bl) break block46;
                            if (f3 == 1.0f) break block47;
                            matrix3x2fStack.translate((float)n11, (float)n10);
                            matrix3x2fStack.scale(f3, f3);
                        }
                        matrix3x2fStack.translate((float)(-n11), (float)(-n10));
                    }
                    drawContext2 = drawContext;
                    n5 = n11;
                    n4 = n10;
                    n3 = n7;
                    n2 = n6;
                    n = n9;
                    totemCounterModule = this;
                    if (bl) break block48;
                    if (!totemCounterModule.ownBackground.getValue()) break block49;
                    totemCounterModule = this;
                }
                hudStyle$Look = totemCounterModule.panelStyle.getValue();
                break block50;
            }
            hudStyle$Look = HudStyle$Look.None;
        }
        HudStyle.panel(drawContext2, n5, n4, n3, n2, n, hudStyle$Look);
        Sprite sprite = ((MinecraftClientAccessor)minecraftClient).divine$getAtlasManager().method_73025(Atlases.field_64477).method_4608(TOTEM_SPRITE);
        drawContext.method_52709(RenderPipelines.field_56883, sprite, n11 + n8, n10 + n8, 16, 16);
        int n16 = n10 + n8 + (16 - n12) / 2;
        drawContext.method_51433(minecraftClient.field_1772, string, n11 + n8 + 16 + 4, n16, n9, true);
        matrix3x2fStack.popMatrix();
        this.ownTotemsHud.setBounds(n7, n6);
    }

    
    private void renderHotbarTotems(DrawContext drawContext, MinecraftClient minecraftClient) {
        float f2;
        int n = this.countOwnTotems(minecraftClient);
        boolean bl = AmbienceModule.b;
        if (n == 0) {
            return;
        }
        Matrix3x2fStack matrix3x2fStack = drawContext.method_51448();
        matrix3x2fStack.pushMatrix();
        float f = f2 = this.getHudScale();
        if (!bl) {
            if (f != 1.0f) {
                matrix3x2fStack.translate((float)this.hudX, (float)this.hudY);
                matrix3x2fStack.scale(1.0f / f2, 1.0f / f2);
                matrix3x2fStack.translate((float)(-this.hudX), (float)(-this.hudY));
            }
            f = HudScale.factor();
        }
        float f3 = f;
        matrix3x2fStack.scale(1.0f / f3, 1.0f / f3);
        GuiTweaksModule.pushOverlay(drawContext, GuiTweaksModule$Key.SURVIVAL);
        try {
            int n2 = drawContext.method_51421() / 2;
            int n3 = drawContext.method_51443() - 39;
            Sprite sprite = ((MinecraftClientAccessor)minecraftClient).divine$getAtlasManager().method_73025(Atlases.field_64477).method_4608(TOTEM_SPRITE);
            int n4 = n3 - 12;
            drawContext.method_52709(RenderPipelines.field_56883, sprite, n2 - 8, n4, 16, 16);
            String string = String.valueOf(n);
            drawContext.method_51433(minecraftClient.field_1772, string, n2 + 9 - minecraftClient.field_1772.method_1727(string), n4 + 7, TotemCounterModule.a(11318, 2734819249535810785L) | this.countColor.getRGB(), true);
            return;
        }
        finally {
            GuiTweaksModule.popOverlay(drawContext);
            matrix3x2fStack.popMatrix();
        }
    }

    private int countOwnTotems(MinecraftClient minecraftClient) {
        int n;
        block13: {
            int n2;
            block14: {
                ItemStack itemStack2;
                boolean bl;
                block10: {
                    bl = AmbienceModule.b;
                    if (minecraftClient.field_1724 == null) {
                        return 0;
                    }
                    n2 = 0;
                    for (ItemStack itemStack2 : minecraftClient.field_1724.method_31548().method_67533()) {
                        block12: {
                            int n3;
                            block11: {
                                if (bl) break block10;
                                ItemStack itemStack3 = itemStack2;
                                n3 = itemStack3.method_31574(Items.field_8288);
                                if (bl) break block11;
                                if (n3 == 0) break block12;
                                n3 = n2 + itemStack3.method_7947();
                            }
                            n2 = n3;
                        }
                        if (!bl) continue;
                    }
                    itemStack2 = minecraftClient.field_1724.method_6079();
                }
                Iterator iterator = itemStack2;
                n = iterator.method_31574(Items.field_8288);
                if (bl) break block13;
                if (n == 0) break block14;
                n2 += iterator.method_7947();
            }
            n = n2;
        }
        return n;
    }

    @Override
    public List<DraggableHud> extraHuds() {
        List<DraggableHud> list;
        block11: {
            block9: {
                TotemCounterModule totemCounterModule;
                block10: {
                    boolean bl;
                    block8: {
                        bl = AmbienceModule.b;
                        totemCounterModule = this;
                        if (bl) break block8;
                        if (!totemCounterModule.ownTotems.getValue()) break block9;
                        totemCounterModule = this;
                    }
                    if (bl) break block10;
                    if (totemCounterModule.ownDisplay.getValue() != TotemCounterModule$OwnDisplay.Panel) break block9;
                    totemCounterModule = this;
                }
                list = List.of(totemCounterModule.ownTotemsHud);
                break block11;
            }
            list = List.of();
        }
        return list;
    }

    private List<Map.Entry<UUID, Integer>> sortedPops() {
        List<Map.Entry<UUID, Integer>> list;
        block4: {
            ArrayList<Map.Entry<UUID, Integer>> arrayList;
            block5: {
                arrayList = new ArrayList<Map.Entry<UUID, Integer>>(TotemPops.all().entrySet());
                boolean bl = AmbienceModule.b;
                list = arrayList;
                if (bl) break block4;
                list.removeIf(TotemCounterModule::lambda$sortedPops$5);
                arrayList.sort(TotemCounterModule::lambda$sortedPops$6);
                if (arrayList.size() <= 8) break block5;
                list = arrayList.subList(0, 8);
                break block4;
            }
            list = arrayList;
        }
        return list;
    }

    private static boolean tracked(UUID uUID) {
        boolean bl;
        block8: {
            block7: {
                MinecraftClient minecraftClient;
                block6: {
                    MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                    boolean bl2 = AmbienceModule.b;
                    minecraftClient = minecraftClient2;
                    if (bl2) break block6;
                    if (minecraftClient.field_1687 == null) break block7;
                    minecraftClient = minecraftClient2;
                }
                if (minecraftClient.field_1687.method_18470(uUID) == null) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    private static String nameOf(MinecraftClient minecraftClient, UUID uUID) {
        block16: {
            PlayerListEntry playerListEntry;
            block17: {
                PlayerListEntry playerListEntry2;
                MinecraftClient minecraftClient2;
                boolean bl;
                block15: {
                    block13: {
                        block14: {
                            bl = AmbienceModule.b;
                            minecraftClient2 = minecraftClient;
                            if (bl) break block13;
                            if (minecraftClient2.field_1687 == null) break block14;
                            minecraftClient2 = minecraftClient;
                            if (bl) break block13;
                            playerListEntry2 = minecraftClient2.field_1687.method_18470(uUID);
                            if (playerListEntry2 != null) {
                                return playerListEntry2.method_7334().name();
                            }
                        }
                        minecraftClient2 = minecraftClient;
                    }
                    if (bl) break block15;
                    if (minecraftClient2.method_1562() == null) break block16;
                    minecraftClient2 = minecraftClient;
                }
                playerListEntry2 = minecraftClient2.method_1562().method_2871(uUID);
                playerListEntry = playerListEntry2;
                if (bl) break block17;
                if (playerListEntry == null) break block16;
                playerListEntry = playerListEntry2;
            }
            return playerListEntry.method_2966().name();
        }
        return "?";
    }

    @Override
    public int getHudX() {
        return this.hudX;
    }

    @Override
    public int getHudY() {
        return this.hudY;
    }

    @Override
    public void setHudPos(int n, int n2) {
        this.hudX = n;
        this.hudY = n2;
    }

    @Override
    public int[] getLastBounds() {
        return this.lastBounds;
    }

    @Override
    public void writeExtra(JsonObject jsonObject) {
        String[] stringArray = b;
        jsonObject.addProperty(stringArray[2], (Number)this.hudX);
        jsonObject.addProperty(stringArray[16], (Number)this.hudY);
        this.ownTotemsHud.write(jsonObject);
    }

    @Override
    public void readExtra(JsonObject jsonObject) {
        JsonObject jsonObject2;
        block7: {
            block8: {
                boolean bl = AmbienceModule.b;
                jsonObject2 = jsonObject;
                if (bl) break block7;
                if (!jsonObject2.has(b[3])) break block8;
                this.hudX = jsonObject.get(b[3]).getAsInt();
            }
            jsonObject2 = jsonObject;
        }
        if (jsonObject2.has(b[28])) {
            this.hudY = jsonObject.get(b[28]).getAsInt();
        }
        this.ownTotemsHud.read(jsonObject);
    }

    private static int lambda$sortedPops$6(Map.Entry entry, Map.Entry entry2) {
        return Integer.compare((Integer)entry2.getValue(), (Integer)entry.getValue());
    }

    private static boolean lambda$sortedPops$5(Map.Entry entry) {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = AmbienceModule.b;
                bl = ((Integer)entry.getValue()).intValue();
                if (bl2) break block2;
                if (bl > false) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private static Style lambda$popsSegment$4(int n, Style style) {
        return style.method_27703(TextColor.method_27717((int)n));
    }

    private static Style lambda$popsSegment$3(int n, Style style) {
        return style.method_27703(TextColor.method_27717((int)n));
    }

    private static Style lambda$tabListSegment$2(int n, Style style) {
        return style.method_27703(TextColor.method_27717((int)n));
    }

    private Boolean lambda$new$1() {
        boolean bl = this.ownDisplay.getValue() == TotemCounterModule$OwnDisplay.Panel;
        return bl;
    }

    private Boolean lambda$new$0() {
        boolean bl = this.ownDisplay.getValue() == TotemCounterModule$OwnDisplay.Hotbar;
        return bl;
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[29];
                        var11_1 = 0;
                        var10_2 = "K<\f\f#6Jo$\u000e\f\u0015r)\u0007\u0004`bVo8\u000f6 pfn3\u0006\u0010&x^\u0004s(\u00061\u0004s(\u00061\bH5\r\u001eo^l_\u0006K1\u0003\u0010*d\u0003S\b&\u000fU<\u000f\f;w^;\u0019\u000b\u001f&r\\i\tt*\f= b\\v.\u0003S\b&\u0004H4\u0006\f\tI8\u0011\f;6r~$\u0004R>\r\u0007\u0007_4\u0011\u0019#w@\u0010H5\r\u001eoYW;\t\u0003\u000boZPh)\nY<\u0001\u0002(dVn3\u0006\u0004s(\u00060\u0005X2\u000e\u0006=\rO2\u0016\f\"6zt(\f\u001d*d\fU<\u000f\f;w^;\u000e\u000b\r*\nT*\fI\u001byM~0\u0011\u0002;p\u000fH5\r\u001eoYW;\u0013\u0003\u0004*bX|\u0005H)\u001b\u0005*\u0015O2\u0016\f\"6It-\u0011I?sK;-\u000e\b6sK\u000bX2\u0017\u0007;6zt1\r\u001b\u0007U<\u000f\f;w^";
                        var12_3 = "K<\f\f#6Jo$\u000e\f\u0015r)\u0007\u0004`bVo8\u000f6 pfn3\u0006\u0010&x^\u0004s(\u00061\u0004s(\u00061\bH5\r\u001eo^l_\u0006K1\u0003\u0010*d\u0003S\b&\u000fU<\u000f\f;w^;\u0019\u000b\u001f&r\\i\tt*\f= b\\v.\u0003S\b&\u0004H4\u0006\f\tI8\u0011\f;6r~$\u0004R>\r\u0007\u0007_4\u0011\u0019#w@\u0010H5\r\u001eoYW;\t\u0003\u000boZPh)\nY<\u0001\u0002(dVn3\u0006\u0004s(\u00060\u0005X2\u000e\u0006=\rO2\u0016\f\"6zt(\f\u001d*d\fU<\u000f\f;w^;\u000e\u000b\r*\nT*\fI\u001byM~0\u0011\u0002;p\u000fH5\r\u001eoYW;\u0013\u0003\u0004*bX|\u0005H)\u001b\u0005*\u0015O2\u0016\f\"6It-\u0011I?sK;-\u000e\b6sK\u000bX2\u0017\u0007;6zt1\r\u001b\u0007U<\u000f\f;w^".length();
                        var9_4 = 11;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block25;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "U<\u000f\f;w^\u0004s(\u00060";
                            var12_3 = "U<\u000f\f;w^\u0004s(\u00060".length();
                            var9_4 = 7;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block25;
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
                            break block26;
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
                                v10 = 27;
                                break;
                            }
                            case 1: {
                                v10 = 93;
                                break;
                            }
                            case 2: {
                                v10 = 98;
                                break;
                            }
                            case 3: {
                                v10 = 105;
                                break;
                            }
                            case 4: {
                                v10 = 79;
                                break;
                            }
                            case 5: {
                                v10 = 22;
                                break;
                            }
                            default: {
                                v10 = 57;
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
                TotemCounterModule.b = var13;
                var0_7 = 5866677578165356353L;
                var6_8 = new long[9];
                var3_9 = 0;
                var4_10 = "J@\u009f\u0019O\u00ea\u00a6\u00ca!m\u00e3d\u0082\u0006\u0090\u00d5Qh\u00d3j\u00bfOe\u00db\u00a6\u00c1\u00aa\u00fb\u00e1\u00d4\u00ddR\u0097\u00cerr\u00d7&yO\u00a3\u00f9V\u000e\u000b\u00b3KfW \u00f0\u00d4d\u0081\u00c3\u00d4";
                var5_11 = "J@\u009f\u0019O\u00ea\u00a6\u00ca!m\u00e3d\u0082\u0006\u0090\u00d5Qh\u00d3j\u00bfOe\u00db\u00a6\u00c1\u00aa\u00fb\u00e1\u00d4\u00ddR\u0097\u00cerr\u00d7&yO\u00a3\u00f9V\u000e\u000b\u00b3KfW \u00f0\u00d4d\u0081\u00c3\u00d4".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v11 = var6_8;
                    v12 = var3_9++;
                    v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "\u0018\u00c3\u0019}\u00c1\b\u00fc6\u00efS\u001e|x0\u00fb\u00a0";
                    var5_11 = "\u0018\u00c3\u0019}\u00c1\b\u00fc6\u00efS\u001e|x0\u00fb\u00a0".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v11 = var6_8;
                        v12 = var3_9++;
                        v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    break block28;
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
        TotemCounterModule.d = var6_8;
        TotemCounterModule.e = new Integer[9];
        TotemCounterModule.MAX_ROWS = TotemCounterModule.a(29020, 6384993661479887235L);
        TotemCounterModule.PAD = TotemCounterModule.a(2763, 5271886329056025111L);
        TotemCounterModule.TOTEM_SPRITE = Identifier.method_60656((String)TotemCounterModule.b[1]);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x50DF;
        if (e[n2] == null) {
            TotemCounterModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
