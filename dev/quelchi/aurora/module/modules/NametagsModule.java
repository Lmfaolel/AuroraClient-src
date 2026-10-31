package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.mixins.TextRendererAccessor;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.NametagsModule$TagSide;
import dev.quelchi.aurora.module.modules.TierTaggerModule;
import dev.quelchi.aurora.module.modules.TotemCounterModule;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.util.TotemPops;
import java.lang.invoke.LambdaMetafactory;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.EffectGlyph;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.MutableText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.StyleSpriteSource;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public final class NametagsModule
extends Module {
    private static NametagsModule instance;
    private final NumberSetting scale;
    private final BooleanSetting showPing;
    private final EnumSetting<NametagsModule$TagSide> pingSide;
    private final BooleanSetting pingDivider;
    private final BooleanSetting showSelf;
    private final BooleanSetting background;
    private final ColorSetting bgColor;
    private final NumberSetting bgOpacity;
    private final BooleanSetting rounded;
    private final BooleanSetting border;
    private final ColorSetting borderColor;
    private final BooleanSetting textShadow;
    private static final StyleSpriteSource BADGE_FONT;
    private static final String BADGE_GLYPH = "\ue000";
    private static final String OWNER_GLYPH = "\ue002";
    private static final String STAFF_GLYPH = "\ue003";
    private static final String MEDIA_GLYPH = "\ue004";
    private static final String PARTNER_GLYPH = "\ue005";
    private static final String ADMIN_GLYPH = "\ue006";
    private static final String BUGTESTER_GLYPH = "\ue007";
    private static final String MANAGER_GLYPH = "\ue008";
    private static final Map<String, Integer> TIER_COLORS;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public static NametagsModule get() {
        return instance;
    }

    public NametagsModule() {
        String[] stringArray = b;
        super(stringArray[29], stringArray[1], Category.RENDER, false);
        this.scale = new NumberSetting(stringArray[17], 0.5, 2.5, 1.0, 0.05);
        this.showPing = new BooleanSetting(stringArray[38], true);
        this.pingSide = new EnumSetting<NametagsModule$TagSide>(stringArray[4], NametagsModule$TagSide.Right);
        this.pingDivider = new BooleanSetting(stringArray[9], false);
        this.showSelf = new BooleanSetting(stringArray[10], false);
        this.background = new BooleanSetting(stringArray[5], true);
        this.bgColor = new ColorSetting(stringArray[2], NametagsModule.a(25341, 6362913933557054763L), false);
        this.bgOpacity = new NumberSetting(stringArray[20], 0.0, 100.0, 25.0, 1.0);
        this.rounded = new BooleanSetting(stringArray[13], false);
        this.border = new BooleanSetting(stringArray[31], false);
        this.borderColor = new ColorSetting(stringArray[37], -1);
        this.textShadow = new BooleanSetting(stringArray[34], false);
        instance = this;
        this.addSettings(this.scale, this.showPing, this.pingSide, this.pingDivider, this.showSelf, this.background, this.bgColor, this.bgOpacity, this.rounded, this.border, this.borderColor, this.textShadow);
        this.showPing.children(this.pingSide, this.pingDivider);
        this.scale.tab(stringArray[23]);
        this.showPing.tab(stringArray[35]);
        this.showSelf.tab(stringArray[35]);
        this.background.children(this.bgColor, this.bgOpacity);
        this.border.children(this.borderColor);
        this.background.tab(stringArray[8]);
        this.rounded.tab(stringArray[21]);
        this.border.tab(stringArray[21]);
        this.textShadow.tab(stringArray[21]);
    }

    
    @Override
    public void onTick() {
        block5: {
            TotemCounterModule totemCounterModule;
            block4: {
                TotemCounterModule totemCounterModule2 = TotemCounterModule.get();
                boolean bl = AmbienceModule.b;
                totemCounterModule = totemCounterModule2;
                if (bl) break block4;
                if (totemCounterModule == null) break block5;
                totemCounterModule = totemCounterModule2;
            }
            if (totemCounterModule.isEnabled()) return;
        }
        TotemPops.tick(this.mc);
    }

    public float labelScale() {
        float f;
        block6: {
            block5: {
                NametagsModule nametagsModule;
                block4: {
                    boolean bl = AmbienceModule.b;
                    nametagsModule = this;
                    if (bl) break block4;
                    if (!nametagsModule.isEnabled()) break block5;
                    nametagsModule = this;
                }
                f = nametagsModule.scale.getValueFloat();
                break block6;
            }
            f = 1.0f;
        }
        return f;
    }

    public boolean showOwnNametag() {
        boolean bl;
        block8: {
            block7: {
                NametagsModule nametagsModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    nametagsModule = this;
                    if (bl2) break block6;
                    if (!nametagsModule.isEnabled()) break block7;
                    nametagsModule = this;
                }
                bl = nametagsModule.showSelf.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public boolean textShadow() {
        boolean bl;
        block8: {
            block7: {
                NametagsModule nametagsModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    nametagsModule = this;
                    if (bl2) break block6;
                    if (!nametagsModule.isEnabled()) break block7;
                    nametagsModule = this;
                }
                bl = nametagsModule.textShadow.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public boolean plainBackground() {
        boolean bl;
        block8: {
            block7: {
                NametagsModule nametagsModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    nametagsModule = this;
                    if (bl2) break block6;
                    if (nametagsModule.rounded.getValue()) break block7;
                    nametagsModule = this;
                }
                bl = nametagsModule.border.getValue();
                if (bl2) break block8;
                if (bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public int backgroundColor() {
        NametagsModule nametagsModule;
        block4: {
            block5: {
                boolean bl = AmbienceModule.b;
                nametagsModule = this;
                if (bl) break block4;
                if (nametagsModule.background.getValue()) break block5;
                return 0;
            }
            nametagsModule = this;
        }
        return (int)(nametagsModule.bgOpacity.getValueFloat() / 100.0f * 255.0f) << 24 | this.bgColor.getRGB();
    }

    
    public void drawBackground(TextRenderer textRenderer, Text text, float f, float f2, Matrix4f matrix4f, VertexConsumerProvider vertexConsumerProvider, TextRenderer.TextLayerType textLayerType, int n) {
        int n2;
        float[] fArray;
        int n3;
        int n4;
        float[] fArray2;
        int n5;
        int n6;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        TextRenderer.GlyphDrawer glyphDrawer;
        boolean bl;
        EffectGlyph effectGlyph;
        block20: {
            effectGlyph = ((TextRendererAccessor)textRenderer).aurora$fonts().method_73366();
            bl = AmbienceModule.b;
            glyphDrawer = TextRenderer.GlyphDrawer.method_71799((VertexConsumerProvider)vertexConsumerProvider, (Matrix4f)matrix4f, (TextRenderer.TextLayerType)textLayerType, (int)n);
            f8 = f - 1.0f;
            f7 = f2 - 1.0f;
            f6 = f + (float)textRenderer.method_27525((StringVisitable)text);
            f5 = f2 + 9.0f;
            f4 = this.rounded.getValue() ? 3.0f : 0.0f;
            f3 = this.border.getValue() ? 1.0f : 0.0f;
            n6 = this.backgroundColor();
            n5 = this.borderColor.getARGB();
            int n7 = 6;
            fArray2 = new float[4 * n7 + 8];
            n4 = 0;
            fArray2[n4++] = f7;
            fArray2[n4++] = f5;
            fArray2[n4++] = f7 + f3;
            fArray2[n4++] = f5 - f3;
            for (n3 = 0; n3 <= n7; ++n3) {
                fArray = fArray2;
                n2 = n4++;
                if (!bl) {
                    fArray[n2] = f7 + f4 * (float)n3 / (float)n7;
                    fArray2[n4++] = f5 - f4 * (float)n3 / (float)n7;
                    if (!bl) continue;
                }
                break block20;
            }
            fArray = fArray2;
            n2 = 0;
        }
        Arrays.sort(fArray, n2, n4);
        n3 = 0;
        while (n3 + 1 < n4) {
            block22: {
                float f9;
                float f10;
                float f11;
                float f12;
                float f13;
                float f14;
                block27: {
                    float f15;
                    block28: {
                        block26: {
                            float f16;
                            block25: {
                                float f17;
                                block23: {
                                    block24: {
                                        float f18;
                                        float f19;
                                        block21: {
                                            f14 = fArray2[n3];
                                            f19 = f13 = fArray2[n3 + 1];
                                            f18 = f14;
                                            if (bl) break block21;
                                            if (f19 - f18 < 1.0E-4f) break block22;
                                            f19 = f14 + f13;
                                            f18 = 2.0f;
                                        }
                                        f15 = f19 / f18;
                                        float f20 = NametagsModule.inset(f15, f7, f5, f4);
                                        f12 = f8 + f20;
                                        f11 = f6 - f20;
                                        f10 = f3;
                                        f17 = 0.0f;
                                        if (bl) break block23;
                                        if (f10 != f17) break block24;
                                        NametagsModule.rect(glyphDrawer, effectGlyph, f12, f14, f11, f13, n6);
                                        if (!bl) break block22;
                                    }
                                    f10 = f15;
                                    f9 = f7;
                                    f16 = f3;
                                    if (bl) break block25;
                                    f17 = f9 + f16;
                                }
                                if (f10 < f17) break block26;
                                f10 = f15;
                                f9 = f5;
                                if (bl) break block27;
                                f16 = f3;
                            }
                            if (!(f10 > f9 - f16)) break block28;
                        }
                        NametagsModule.rect(glyphDrawer, effectGlyph, f12, f14, f11, f13, n5);
                        if (!bl) break block22;
                    }
                    f10 = f15;
                    f9 = f7 + f3;
                }
                float f21 = NametagsModule.inset(f10, f9, f5 - f3, Math.max(f4 - f3, 0.0f));
                float f22 = f8 + f3 + f21;
                float f23 = f6 - f3 - f21;
                NametagsModule.rect(glyphDrawer, effectGlyph, f12, f14, f22, f13, n5);
                NametagsModule.rect(glyphDrawer, effectGlyph, f23, f14, f11, f13, n5);
                NametagsModule.rect(glyphDrawer, effectGlyph, f22, f14, f23, f13, n6);
            }
            ++n3;
            if (!bl) continue;
        }
    }

    private static float inset(float f, float f2, float f3, float f4) {
        float f5;
        block10: {
            float f6;
            block11: {
                float f7;
                block8: {
                    block9: {
                        f6 = Math.max(f2 + f4 - f, f - (f3 - f4));
                        boolean bl = AmbienceModule.b;
                        f7 = f4;
                        if (bl) break block8;
                        if (f7 <= 0.0f) break block9;
                        f5 = f6;
                        if (bl) break block10;
                        if (!(f5 <= 0.0f)) break block11;
                    }
                    f7 = 0.0f;
                }
                return f7;
            }
            f6 = Math.min(f6, f4);
            f5 = f4 - (float)Math.sqrt(f4 * f4 - f6 * f6);
        }
        return f5;
    }

    private static void rect(TextRenderer.GlyphDrawer glyphDrawer, EffectGlyph effectGlyph, float f, float f2, float f3, float f4, int n) {
        block8: {
            block7: {
                float f5;
                block6: {
                    boolean bl = AmbienceModule.b;
                    f5 = f3 == f ? 0 : (f3 < f ? -1 : 1);
                    if (bl) break block6;
                    if (f5 <= 0) break block7;
                    f5 = n >>> 24;
                }
                if (f5 != false) break block8;
            }
            return;
        }
        glyphDrawer.method_71798(effectGlyph.method_73406(f, f2, f3, f4, -0.01f, n, 0, 0.0f));
    }

    public Text decorate(Text text, UUID uUID) {
        MutableText mutableText;
        block109: {
            MutableText mutableText2;
            boolean bl;
            Text text2;
            block107: {
                block108: {
                    boolean bl2;
                    Text text3;
                    boolean bl3;
                    block105: {
                        block106: {
                            MutableText mutableText3;
                            MutableText mutableText4;
                            boolean bl4;
                            boolean bl5;
                            Text text4;
                            block104: {
                                Text text5;
                                block102: {
                                    block103: {
                                        Text text6;
                                        block101: {
                                            Text text7;
                                            block99: {
                                                block100: {
                                                    block97: {
                                                        block98: {
                                                            block95: {
                                                                block96: {
                                                                    Text text8;
                                                                    Text text9;
                                                                    block94: {
                                                                        Text text10;
                                                                        block92: {
                                                                            block93: {
                                                                                block90: {
                                                                                    block91: {
                                                                                        boolean bl6;
                                                                                        block89: {
                                                                                            block88: {
                                                                                                TierTaggerModule tierTaggerModule;
                                                                                                block87: {
                                                                                                    Text text11;
                                                                                                    TierTaggerModule tierTaggerModule2;
                                                                                                    block86: {
                                                                                                        TierTaggerModule tierTaggerModule3;
                                                                                                        block84: {
                                                                                                            block85: {
                                                                                                                boolean bl7;
                                                                                                                block83: {
                                                                                                                    TotemCounterModule totemCounterModule;
                                                                                                                    TotemCounterModule totemCounterModule2;
                                                                                                                    block82: {
                                                                                                                        block81: {
                                                                                                                            Text text12;
                                                                                                                            block80: {
                                                                                                                                bl3 = AmbienceModule.b;
                                                                                                                                text12 = text;
                                                                                                                                if (bl3) break block80;
                                                                                                                                if (text12 != null) break block81;
                                                                                                                                text12 = text;
                                                                                                                            }
                                                                                                                            return text12;
                                                                                                                        }
                                                                                                                        bl7 = this.isEnabled();
                                                                                                                        text4 = null;
                                                                                                                        bl5 = true;
                                                                                                                        bl4 = false;
                                                                                                                        totemCounterModule2 = TotemCounterModule.get();
                                                                                                                        totemCounterModule = totemCounterModule2;
                                                                                                                        if (bl3) break block82;
                                                                                                                        if (totemCounterModule == null) break block83;
                                                                                                                        totemCounterModule = totemCounterModule2;
                                                                                                                    }
                                                                                                                    text4 = totemCounterModule.popsSegment(uUID);
                                                                                                                    bl5 = totemCounterModule2.nametagRight();
                                                                                                                    bl4 = totemCounterModule2.nametagDivider();
                                                                                                                }
                                                                                                                Text text13 = bl7 ? this.pingSegment(uUID) : null;
                                                                                                                text3 = text13;
                                                                                                                boolean bl8 = this.pingSide.getValue() == NametagsModule$TagSide.Right;
                                                                                                                bl2 = bl8;
                                                                                                                tierTaggerModule2 = TierTaggerModule.get();
                                                                                                                tierTaggerModule3 = tierTaggerModule2;
                                                                                                                if (bl3) break block84;
                                                                                                                if (tierTaggerModule3 != null) break block85;
                                                                                                                text11 = null;
                                                                                                                break block86;
                                                                                                            }
                                                                                                            tierTaggerModule3 = tierTaggerModule2;
                                                                                                        }
                                                                                                        text11 = tierTaggerModule3.segment(uUID, this.ignOf(uUID));
                                                                                                    }
                                                                                                    text2 = text11;
                                                                                                    tierTaggerModule = tierTaggerModule2;
                                                                                                    if (bl3) break block87;
                                                                                                    if (tierTaggerModule == null) break block88;
                                                                                                    tierTaggerModule = tierTaggerModule2;
                                                                                                }
                                                                                                bl6 = tierTaggerModule.onRight();
                                                                                                if (bl3) break block89;
                                                                                                if (!bl6) break block88;
                                                                                                bl6 = true;
                                                                                                break block89;
                                                                                            }
                                                                                            bl6 = false;
                                                                                        }
                                                                                        bl = bl6;
                                                                                        text9 = this.auroraBadge(uUID);
                                                                                        text10 = text4;
                                                                                        if (bl3) break block90;
                                                                                        if (text10 != null) break block91;
                                                                                        text10 = text3;
                                                                                        if (bl3) break block90;
                                                                                        if (text10 != null) break block91;
                                                                                        text10 = text2;
                                                                                        if (bl3) break block90;
                                                                                        if (text10 != null) break block91;
                                                                                        text10 = text9;
                                                                                        if (bl3) break block90;
                                                                                        if (text10 != null) break block91;
                                                                                        return text;
                                                                                    }
                                                                                    text10 = text9;
                                                                                }
                                                                                if (bl3) break block92;
                                                                                if (text10 != null) break block93;
                                                                                text8 = null;
                                                                                break block94;
                                                                            }
                                                                            text10 = text;
                                                                        }
                                                                        text8 = NametagsModule.beforeName(text10, this.ignOf(uUID), uUID, text9);
                                                                    }
                                                                    text6 = text8;
                                                                    mutableText = Text.method_43473();
                                                                    text7 = text9;
                                                                    if (bl3) break block95;
                                                                    if (text7 == null) break block96;
                                                                    text7 = text6;
                                                                    if (bl3) break block95;
                                                                    if (text7 != null) break block96;
                                                                    mutableText.method_10852(text9).method_10852(NametagsModule.badgeGap());
                                                                }
                                                                text7 = text2;
                                                            }
                                                            if (bl3) break block97;
                                                            if (text7 == null) break block98;
                                                            if (bl) break block98;
                                                            mutableText.method_10852(text2).method_10852((Text)Text.method_43470((String)" "));
                                                        }
                                                        text7 = text3;
                                                    }
                                                    if (bl3) break block99;
                                                    if (text7 == null) break block100;
                                                    if (bl2) break block100;
                                                    mutableText.method_10852(text3).method_10852(NametagsModule.joiner(this.pingDivider.getValue()));
                                                }
                                                text7 = text4;
                                            }
                                            if (text7 == null || bl5) break block101;
                                            mutableText.method_10852(text4).method_10852(NametagsModule.joiner(bl4));
                                        }
                                        mutableText4 = mutableText;
                                        text5 = text6;
                                        if (bl3) break block102;
                                        if (text5 == null) break block103;
                                        mutableText3 = text6;
                                        break block104;
                                    }
                                    text5 = text;
                                }
                                mutableText3 = text5.method_27661();
                            }
                            mutableText2 = mutableText4.method_10852((Text)mutableText3);
                            if (bl3) break block105;
                            if (text4 == null) break block106;
                            if (!bl5) break block106;
                            mutableText.method_10852(NametagsModule.joiner(bl4)).method_10852(text4);
                        }
                        mutableText2 = text3;
                    }
                    if (bl3) break block107;
                    if (mutableText2 == null) break block108;
                    if (!bl2) break block108;
                    mutableText.method_10852(NametagsModule.joiner(this.pingDivider.getValue())).method_10852(text3);
                }
                mutableText2 = text2;
            }
            if (mutableText2 == null || !bl) break block109;
            mutableText.method_10852((Text)Text.method_43470((String)" ")).method_10852(text2);
        }
        return mutableText;
    }

    
    public static Text beforeName(Text var0, String var1_1, UUID var2_2, Text var3_3) {
        block47: {
            block46: {
                block44: {
                    block45: {
                        block42: {
                            block43: {
                                block41: {
                                    block39: {
                                        block40: {
                                            block38: {
                                                var4_4 = AmbienceModule.b;
                                                if (var0 == null) {
                                                    return null;
                                                }
                                                var5_5 = new StringBuilder();
                                                var0.method_27658((StringVisitable.StyledVisitor)LambdaMetafactory.metafactory(null, null, null, (Lnet/minecraft/text/Style;Ljava/lang/String;)Ljava/util/Optional;, lambda$beforeName$0(java.lang.StringBuilder net.minecraft.text.Style java.lang.String ), (Lnet/minecraft/text/Style;Ljava/lang/String;)Ljava/util/Optional;)((StringBuilder)var5_5), Style.field_24360);
                                                var6_6 = var5_5.toString();
                                                v1 = var1_1;
                                                if (var4_4) break block38;
                                                if (v1 != null) {
                                                }
                                                v1 = var1_1;
                                            }
                                            if (var4_4) break block39;
                                            if (!v1.isEmpty()) break block40;
lbl29:

                                            v4 = -1;
                                            break block41;
                                        }
                                        v1 = var6_6;
                                    }
                                    v4 = v1.indexOf(var1_1);
                                }
                                var7_7 = v4;
                                v6 = var7_7;
                                if (var4_4) break block42;
                                if (v6 >= 0) break block43;
                                var8_8 = NametagsModule.teamPrefix(var2_2);
                                v6 = (int)var8_8.isEmpty();
                                if (var4_4) break block42;
                                if (v6 != 0) break block43;
                                v6 = (int)var6_6.startsWith(var8_8);
                                if (var4_4) break block42;
                                if (v6 == 0) break block43;
                                var7_7 = var8_8.length();
                            }
                            v6 = var7_7;
                        }
                        if (var4_4) break block44;
                        if (v6 <= 0) break block45;
                        v6 = var6_6.charAt(var7_7 - 1);
                        if (var4_4) break block44;
                        if (v6 == 60) {
                            // empty if block
                        }
                    }
                    v6 = --var7_7;
                }
                if (var4_4) break block46;
                if (v6 > 0) {
                }
                v6 = (int)var6_6.substring(0, var7_7).strip().replace("<", "").isEmpty();
            }
            if (v6 == 0) break block47;
lbl108:

            return null;
        }
        var8_8 = Text.method_43473();
        var9_9 = new int[]{0};
        var10_10 = new boolean[]{false};
        var11_11 = var7_7;
        var0.method_27658((StringVisitable.StyledVisitor)LambdaMetafactory.metafactory(null, null, null, (Lnet/minecraft/text/Style;Ljava/lang/String;)Ljava/util/Optional;, lambda$beforeName$1(int int[] boolean[] net.minecraft.text.MutableText net.minecraft.text.Text net.minecraft.text.Style java.lang.String ), (Lnet/minecraft/text/Style;Ljava/lang/String;)Ljava/util/Optional;)((int)var11_11, (int[])var9_9, (boolean[])var10_10, (MutableText)var8_8, (Text)var3_3), Style.field_24360);
        v18 = var10_10[0] != false ? var8_8 : null;
        return v18;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int nameLine(List<DisplayEntity.TextDisplayEntity.TextLine> list, String string) {
        int n;
        boolean bl;
        block12: {
            block13: {
                String string2;
                block11: {
                    bl = AmbienceModule.b;
                    string2 = string;
                    if (bl) break block11;
                    if (string2 == null) return -1;
                    string2 = string;
                }
                n = string2.isEmpty();
                if (bl) break block12;
                if (n == 0) break block13;
                return -1;
            }
            n = 0;
        }
        int n2 = n;
        do {
            block14: {
                int n3;
                if (n2 >= list.size()) return -1;
                int n4 = NametagsModule.textOf(list.get(n2).comp_1249()).getString().contains(string) ? 1 : 0;
                if (bl) return n4;
                if (bl) return n3;
                if (n4 == 0) break block14;
                n3 = n2;
                return n3;
            }
            ++n2;
        } while (!bl);
        return -1;
    }

    public static Text textOf(OrderedText orderedText) {
        MutableText mutableText = Text.method_43473();
        StringBuilder stringBuilder = new StringBuilder();
        Style[] styleArray = new Style[]{null};
        orderedText.accept((arg_0, arg_1, arg_2) -> NametagsModule.lambda$textOf$2(styleArray, mutableText, stringBuilder, arg_0, arg_1, arg_2));
        if (styleArray[0] != null) {
            mutableText.method_10852((Text)Text.method_43470((String)stringBuilder.toString()).method_10862(styleArray[0]));
        }
        return mutableText;
    }

    private static String teamPrefix(UUID uUID) {
        String string;
        block22: {
            Team team;
            block20: {
                Team team2;
                block21: {
                    Team team3;
                    boolean bl;
                    block19: {
                        PlayerListEntry playerListEntry;
                        block17: {
                            PlayerListEntry playerListEntry2;
                            block18: {
                                MinecraftClient minecraftClient;
                                block15: {
                                    MinecraftClient minecraftClient2;
                                    block16: {
                                        block14: {
                                            minecraftClient2 = MinecraftClient.method_1551();
                                            bl = AmbienceModule.b;
                                            if (uUID == null) break block14;
                                            minecraftClient = minecraftClient2;
                                            if (bl) break block15;
                                            if (minecraftClient.method_1562() != null) break block16;
                                        }
                                        return "";
                                    }
                                    minecraftClient = minecraftClient2;
                                }
                                playerListEntry2 = minecraftClient.method_1562().method_2871(uUID);
                                playerListEntry = playerListEntry2;
                                if (bl) break block17;
                                if (playerListEntry != null) break block18;
                                team3 = null;
                                break block19;
                            }
                            playerListEntry = playerListEntry2;
                        }
                        team3 = playerListEntry.method_2955();
                    }
                    team2 = team3;
                    team = team2;
                    if (bl) break block20;
                    if (team != null) break block21;
                    string = "";
                    break block22;
                }
                team = team2;
            }
            string = team.method_1144().getString();
        }
        return string;
    }

    public static Text badgeFor(UUID uUID) {
        Text text;
        block6: {
            NametagsModule nametagsModule;
            block4: {
                NametagsModule nametagsModule2;
                block5: {
                    nametagsModule2 = NametagsModule.get();
                    boolean bl = AmbienceModule.b;
                    nametagsModule = nametagsModule2;
                    if (bl) break block4;
                    if (nametagsModule != null) break block5;
                    text = null;
                    break block6;
                }
                nametagsModule = nametagsModule2;
            }
            text = nametagsModule.auroraBadge(uUID);
        }
        return text;
    }

    public static Text badgeGap() {
        return Text.method_43470((String)"\ue001").method_27694(NametagsModule::lambda$badgeGap$3);
    }

    /*
     * Exception decompiling
     */
    private Text auroraBadge(UUID var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [6[TRYBLOCK]], but top level block is 30[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:845)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1042)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:929)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:73)
         *     at org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private String ignOf(UUID uUID) {
        String string;
        block12: {
            PlayerListEntry playerListEntry;
            block10: {
                PlayerListEntry playerListEntry2;
                block11: {
                    NametagsModule nametagsModule;
                    boolean bl;
                    block8: {
                        block9: {
                            bl = AmbienceModule.b;
                            nametagsModule = this;
                            if (bl) break block8;
                            if (nametagsModule.mc.method_1562() != null) break block9;
                            return null;
                        }
                        nametagsModule = this;
                    }
                    playerListEntry2 = nametagsModule.mc.method_1562().method_2871(uUID);
                    playerListEntry = playerListEntry2;
                    if (bl) break block10;
                    if (playerListEntry != null) break block11;
                    string = null;
                    break block12;
                }
                playerListEntry = playerListEntry2;
            }
            string = playerListEntry.method_2966().name();
        }
        return string;
    }

    public static int tierColorOf(String string) {
        return NametagsModule.tierColor(string);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static int tierColor(String string) {
        int n;
        Integer n2;
        block17: {
            Integer n3;
            block18: {
                Object object;
                boolean bl;
                block15: {
                    String string2;
                    block16: {
                        String string3;
                        block13: {
                            block14: {
                                block12: {
                                    bl = AmbienceModule.b;
                                    string3 = string;
                                    if (bl) break block12;
                                    if (string3 == null) return NametagsModule.a(24220, 5637791644226912583L);
                                    string3 = string;
                                }
                                if (bl) break block13;
                                if (!string3.isEmpty()) break block14;
                                return NametagsModule.a(24220, 5637791644226912583L);
                            }
                            string3 = string.toUpperCase(Locale.ROOT);
                        }
                        string2 = string3;
                        object = string2;
                        if (bl) break block15;
                        if (!((String)object).startsWith("R")) break block16;
                        string2 = string2.substring(1);
                    }
                    object = TIER_COLORS.get(string2);
                }
                n3 = (Integer)object;
                n2 = n3;
                if (bl) break block17;
                if (n2 != null) break block18;
                n = NametagsModule.a(24220, 5637791644226912583L);
                return n;
            }
            n2 = n3;
        }
        n = n2;
        return n;
    }

    private Text pingSegment(UUID uUID) {
        PlayerListEntry playerListEntry;
        block16: {
            PlayerListEntry playerListEntry2;
            block17: {
                PlayerListEntry playerListEntry3;
                boolean bl;
                block18: {
                    block15: {
                        MinecraftClient minecraftClient;
                        block14: {
                            NametagsModule nametagsModule;
                            block12: {
                                block13: {
                                    bl = AmbienceModule.b;
                                    nametagsModule = this;
                                    if (bl) break block12;
                                    if (nametagsModule.showPing.getValue()) break block13;
                                    return null;
                                }
                                nametagsModule = this;
                            }
                            minecraftClient = nametagsModule.mc;
                            if (bl) break block14;
                            if (minecraftClient.method_1562() == null) break block15;
                            minecraftClient = this.mc;
                        }
                        playerListEntry3 = minecraftClient.method_1562().method_2871(uUID);
                        break block18;
                    }
                    playerListEntry3 = null;
                }
                playerListEntry2 = playerListEntry3;
                playerListEntry = playerListEntry2;
                if (bl) break block16;
                if (playerListEntry != null) break block17;
                return null;
            }
            playerListEntry = playerListEntry2;
        }
        int n = playerListEntry.method_2959();
        return Text.method_43470((String)(n + b[19])).method_27692(NametagsModule.pingColor(n));
    }

    private static Text joiner(boolean bl) {
        MutableText mutableText = bl ? Text.method_43470((String)b[32]).method_27692(Formatting.field_1063) : Text.method_43470((String)" ");
        return mutableText;
    }

    public static Formatting pingColor(int n) {
        int n2;
        block13: {
            block14: {
                boolean bl;
                block11: {
                    block12: {
                        bl = AmbienceModule.b;
                        n2 = n;
                        if (bl) break block11;
                        if (n2 >= 50) break block12;
                        return Formatting.field_1060;
                    }
                    n2 = n;
                }
                if (bl) break block13;
                if (n2 >= 100) break block14;
                return Formatting.field_1054;
            }
            n2 = n;
        }
        if (n2 < 150) {
            return Formatting.field_1065;
        }
        return Formatting.field_1061;
    }

    private static Style lambda$auroraBadge$4(Style style) {
        return style.method_27704(BADGE_FONT).method_27703(TextColor.method_27717((int)NametagsModule.a(13050, 2567885387367968037L)));
    }

    private static Style lambda$badgeGap$3(Style style) {
        return style.method_27704(BADGE_FONT);
    }

    private static boolean lambda$textOf$2(Style[] styleArray, MutableText mutableText, StringBuilder stringBuilder, int n, Style style, int n2) {
        boolean bl;
        block10: {
            block8: {
                Style[] styleArray2;
                block9: {
                    boolean bl2 = AmbienceModule.b;
                    styleArray2 = styleArray;
                    if (bl2) break block8;
                    if (styleArray2[0] == null) break block9;
                    bl = style.equals((Object)styleArray[0]);
                    if (bl2) break block10;
                    if (bl) break block9;
                    mutableText.method_10852((Text)Text.method_43470((String)stringBuilder.toString()).method_10862(styleArray[0]));
                    stringBuilder.setLength(0);
                }
                styleArray2 = styleArray;
            }
            styleArray2[0] = style;
            stringBuilder.appendCodePoint(n2);
            bl = true;
        }
        return bl;
    }

    
    private static Optional lambda$beforeName$1(int n, int[] nArray, boolean[] blArray, MutableText mutableText, Text text, Style style, String string) {
        block14: {
            block11: {
                int n2;
                boolean bl;
                int n3;
                block13: {
                    block12: {
                        block10: {
                            n3 = n - nArray[0];
                            bl = AmbienceModule.b;
                            n2 = blArray[0];
                            if (bl) break block10;
                            if (n2 != 0) break block11;
                            n2 = n3;
                        }
                        if (bl) break block12;
                        if (n2 < 0) break block11;
                        n2 = n3;
                    }
                    if (bl) break block13;
                    if (n2 >= string.length()) break block11;
                    n2 = n3;
                }
                if (n2 > 0) {
                    mutableText.method_10852((Text)Text.method_43470((String)string.substring(0, n3)).method_10862(style));
                }
                mutableText.method_10852(text).method_10852(NametagsModule.badgeGap());
                mutableText.method_10852((Text)Text.method_43470((String)string.substring(n3)).method_10862(style));
                blArray[0] = true;
                if (!bl) break block14;
            }
            mutableText.method_10852((Text)Text.method_43470((String)string).method_10862(style));
        }
        nArray[0] = nArray[0] + string.length();
        return Optional.empty();
    }

    private static Optional lambda$beforeName$0(StringBuilder stringBuilder, Style style, String string) {
        stringBuilder.append(string);
        return Optional.empty();
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[39];
                        var11_1 = 0;
                        var10_2 = "\u0006%\u0011\u001f1\n%\u0016(1\f\n9\u0002w\u0005%\u0012\u001b\"\u000e0K \u0016\r&\u00036\u0012\u0005(+\u0013\u0011$\u0003'\u0010N\t;-\u0011\u0019v<>\u000f!\n)%\u001c\u00151\u001d8\u001e*\u001b\u0006\n1\r\u0011$\u000e\u0003'\u0010J\u000580\u0006\u00123\f;-\u0011\u0019v+>\u001d-\u001b\u001b$\u00108,\u0010\tv  \u0005d1\u001f;\n#\n#\u0003'\u0010K\u0005\u00043\u0011\u001b$\u000f9+\n\u00102\n3K\u0007\u0010\f8\n%\u0018\u0005\u00180\u001e\u00180\t\t1\u0018\n3\u001c#\u000e6\u0003#\u0010M\u00058'\u001e\u00123\u0003#\u0010L\u0002\u00067\u0007$4\u001e\u001d?\u001b.\u000580\u0006\u00123\u0003#\u0010J\u0007%%\u0012\u001b\"\u000e0\u0005\u0006!\u001b\u00177\u0003'\u0010L\u0005\n \u0012\u00178\u0007\u001b%\r\n8\n%\u0003'\u0010M\b%%\u0012\u001b\"\u000e0\u0018\u0005\u0002'\u0010\u0010%\u0006)+\r\u001a3\u001d\u0003K8_\u0003#\u0010N\u000b?!\u0007\nv<?\n \u0010\t\u0007%%\u0012\u001b\"\u000e0\u0003#\u0010K";
                        var12_3 = "\u0006%\u0011\u001f1\n%\u0016(1\f\n9\u0002w\u0005%\u0012\u001b\"\u000e0K \u0016\r&\u00036\u0012\u0005(+\u0013\u0011$\u0003'\u0010N\t;-\u0011\u0019v<>\u000f!\n)%\u001c\u00151\u001d8\u001e*\u001b\u0006\n1\r\u0011$\u000e\u0003'\u0010J\u000580\u0006\u00123\f;-\u0011\u0019v+>\u001d-\u001b\u001b$\u00108,\u0010\tv  \u0005d1\u001f;\n#\n#\u0003'\u0010K\u0005\u00043\u0011\u001b$\u000f9+\n\u00102\n3K\u0007\u0010\f8\n%\u0018\u0005\u00180\u001e\u00180\t\t1\u0018\n3\u001c#\u000e6\u0003#\u0010M\u00058'\u001e\u00123\u0003#\u0010L\u0002\u00067\u0007$4\u001e\u001d?\u001b.\u000580\u0006\u00123\u0003#\u0010J\u0007%%\u0012\u001b\"\u000e0\u0005\u0006!\u001b\u00177\u0003'\u0010L\u0005\n \u0012\u00178\u0007\u001b%\r\n8\n%\u0003'\u0010M\b%%\u0012\u001b\"\u000e0\u0018\u0005\u0002'\u0010\u0010%\u0006)+\r\u001a3\u001d\u0003K8_\u0003#\u0010N\u000b?!\u0007\nv<?\n \u0010\t\u0007%%\u0012\u001b\"\u000e0\u0003#\u0010K".length();
                        var9_4 = 7;
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
                            var10_2 = "(+\u0013\u0011$\t8,\u0010\tv?>\u0005#";
                            var12_3 = "(+\u0013\u0011$\t8,\u0010\tv?>\u0005#".length();
                            var9_4 = 5;
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
                    var14_7 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_7;
                        v9 = v6[v8];
                        switch (var14_7 % 7) {
                            case 0: {
                                v10 = 107;
                                break;
                            }
                            case 1: {
                                v10 = 68;
                                break;
                            }
                            case 2: {
                                v10 = 127;
                                break;
                            }
                            case 3: {
                                v10 = 126;
                                break;
                            }
                            case 4: {
                                v10 = 86;
                                break;
                            }
                            case 5: {
                                v10 = 111;
                                break;
                            }
                            default: {
                                v10 = 87;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_7;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_7);
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
                NametagsModule.b = var13;
                var0_8 = 3433150568644595699L;
                var6_9 = new long[13];
                var3_10 = 0;
                var4_11 = "\u00f7\u00d1\u00c0-\u00f4}\u0015\u00b2\u00a5>b\u0005\u00dd\u0083\u0094`\u009c\u00cc\u008a\u00bb\u00cc\u00e86U\b\u00a5\u00a7\u00a7\u00d7h\u0093\u00fb\u00a5c\u0095H)\u00e9\u009b6\u00a1f\u00ab#\u00fdp\u00f2\u00d8v\u0089\u0095>\u00caX\u00c1\u00a6\u00f8d\u0001\u00f9\u00b2r\u00d1x1\u00143=\u00e7M\u00b1Kd^%j8\u001boR^\u00f2\u00fa#\u00c6\u00e1Zt";
                var5_12 = "\u00f7\u00d1\u00c0-\u00f4}\u0015\u00b2\u00a5>b\u0005\u00dd\u0083\u0094`\u009c\u00cc\u008a\u00bb\u00cc\u00e86U\b\u00a5\u00a7\u00a7\u00d7h\u0093\u00fb\u00a5c\u0095H)\u00e9\u009b6\u00a1f\u00ab#\u00fdp\u00f2\u00d8v\u0089\u0095>\u00caX\u00c1\u00a6\u00f8d\u0001\u00f9\u00b2r\u00d1x1\u00143=\u00e7M\u00b1Kd^%j8\u001boR^\u00f2\u00fa#\u00c6\u00e1Zt".length();
                var2_13 = 0;
                while (true) {
                    var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                    v11 = var6_9;
                    v12 = var3_10++;
                    v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    var4_11 = "\u0004\u0087)\u00ac\u001e\u001f\u001c7\u0091l\u001b 9y\u00dd)";
                    var5_12 = "\u0004\u0087)\u00ac\u001e\u001f\u001c7\u0091l\u001b 9y\u00dd)".length();
                    var2_13 = 0;
                    while (true) {
                        var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                        v11 = var6_9;
                        v12 = var3_10++;
                        v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var0_8;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        NametagsModule.d = var6_9;
        NametagsModule.e = new Integer[13];
        var8_6 = NametagsModule.b;
        NametagsModule.BADGE_FONT = new StyleSpriteSource.Font(Identifier.method_60655((String)var8_6[6], (String)var8_6[30]));
        NametagsModule.TIER_COLORS = Map.ofEntries(new Map.Entry[]{Map.entry(var8_6[33], NametagsModule.a(7245, 1757209703894303644L)), Map.entry(var8_6[3], NametagsModule.a(25301, 7961158859063606528L)), Map.entry(var8_6[16], NametagsModule.a(11420, 2729700054043620166L)), Map.entry(var8_6[28], NametagsModule.a(9322, 6091180620568198074L)), Map.entry(var8_6[18], NametagsModule.a(24069, 5272816274692866513L)), Map.entry(var8_6[25], NametagsModule.a(32464, 8860758330545971459L)), Map.entry(var8_6[36], NametagsModule.a(16068, 9219268897530568989L)), Map.entry(var8_6[11], NametagsModule.a(20587, 7969011889456877500L)), Map.entry(var8_6[22], NametagsModule.a(1769, 9074923701194053947L)), Map.entry(var8_6[7], NametagsModule.a(22373, 2321207630703855805L))});
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5FD3;
        if (e[n2] == null) {
            NametagsModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
