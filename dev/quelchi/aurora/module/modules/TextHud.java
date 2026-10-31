package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.gui.HudStyle;
import dev.quelchi.aurora.gui.HudStyle$Look;
import dev.quelchi.aurora.hud.HudLayout;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.ModuleManager;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import java.util.List;
import java.util.function.IntFunction;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public abstract class TextHud
extends Module
implements DraggableHud {
    protected final EnumSetting<HudStyle$Look> style;
    protected final BooleanSetting shadow;
    protected final ColorSetting accent;
    private final String key;
    protected int hudX;
    protected int hudY;
    private final HudLayout layout;
    private static final HudLayout SCRATCH;
    private int[] lastBounds;
    private static final String[] b;
    private static final long[] e;
    private static final Integer[] f;

    protected TextHud(String string, String string2, String string3, int n, int n2) {
        super(string, string2, Category.HUD, false);
        String[] stringArray = b;
        this.style = new EnumSetting<HudStyle$Look>(stringArray[0], HudStyle$Look.Glass);
        this.shadow = new BooleanSetting(stringArray[2], true);
        this.accent = new ColorSetting(stringArray[1], TextHud.a(674, 2903315315786387569L), false);
        this.layout = new HudLayout();
        this.key = string3;
        this.hudX = n;
        this.hudY = n2;
        this.lastBounds = this.layout.emptyAt(n, n2);
        this.addSettings(this.style, this.shadow, this.accent);
    }

    protected abstract List<String> lines();

    protected List<String> placeholder() {
        return List.of(this.getName());
    }

    protected Identifier icon(int n) {
        return null;
    }

    protected int iconWidth() {
        return 0;
    }

    @Override
    public void onHudRender(DrawContext drawContext) {
        block21: {
            block25: {
                TextHud textHud;
                List<String> list;
                block22: {
                    boolean bl;
                    block23: {
                        boolean bl2;
                        block24: {
                            block20: {
                                block18: {
                                    block19: {
                                        bl2 = AmbienceModule.b;
                                        MinecraftClient minecraftClient = MinecraftClient.method_1551();
                                        if (bl2) break block18;
                                        if (minecraftClient.field_1724 == null) break block19;
                                        if (!minecraftClient.field_1690.field_1842) break block20;
                                    }
                                    this.clearBounds();
                                }
                                return;
                            }
                            list = this.lines();
                            boolean bl3 = ModuleManager.editing();
                            if (bl2) break block21;
                            if (!list.isEmpty()) break block22;
                            bl = bl3;
                            if (bl2) break block23;
                            if (bl) break block24;
                            this.clearBounds();
                            return;
                        }
                        textHud = this;
                        if (bl2) break block25;
                        list = textHud.placeholder();
                        bl = list.isEmpty();
                    }
                    if (bl) {
                        this.clearBounds();
                        return;
                    }
                }
                this.layout.update(list, this.iconWidth());
                TextHud.paintMeasured(drawContext, this.hudX, this.hudY, list, this.layout, this.style.getValue(), this.shadow.getValue(), this.accent.getRGB(), this::icon, this.iconWidth());
                textHud = this;
            }
            textHud.lastBounds = this.layout.boundsAt(this.hudX, this.hudY);
        }
    }

    public static void paintMeasured(DrawContext drawContext, int n, int n2, List<String> list, HudLayout hudLayout, HudStyle$Look hudStyle$Look, boolean bl, int n3, IntFunction<Identifier> identifier, int n4) {
        TextRenderer textRenderer = MinecraftClient.method_1551().field_1772;
        int n5 = 5;
        int n6 = hudLayout.lineHeight();
        int n7 = hudLayout.width();
        int n8 = hudLayout.height();
        HudStyle.panel(drawContext, n, n2, n7, n8, TextHud.a(30085, 1728160109766993751L) | n3, hudStyle$Look);
        boolean bl2 = AmbienceModule.b;
        int n9 = n + n5;
        int n10 = n2 + n5;
        for (int i = 0; i < list.size(); ++i) {
            boolean bl3;
            int n11;
            int n12;
            int n13;
            String string;
            TextRenderer textRenderer2;
            DrawContext drawContext2;
            block19: {
                block21: {
                    block20: {
                        Identifier identifier2;
                        block18: {
                            Identifier identifier3;
                            block16: {
                                block17: {
                                    identifier3 = identifier;
                                    if (bl2) break block16;
                                    if (identifier3 != null) break block17;
                                    identifier2 = null;
                                    break block18;
                                }
                                identifier3 = identifier.apply(i);
                            }
                            identifier2 = identifier3;
                        }
                        Identifier identifier4 = identifier2;
                        if (identifier4 != null) {
                            drawContext.method_52706(RenderPipelines.field_56883, identifier4, n9, n10 - 2, 12, 12);
                        }
                        drawContext2 = drawContext;
                        textRenderer2 = textRenderer;
                        string = list.get(i);
                        int n14 = n9;
                        int n15 = identifier4 != null ? 15 : 0;
                        n13 = n14 + n15;
                        n12 = n10;
                        n11 = TextHud.a(3114, 3121360608083549947L);
                        bl3 = bl;
                        if (bl2) break block19;
                        if (bl3) break block20;
                        bl3 = HudStyle.wantsShadow(hudStyle$Look);
                        if (bl2) break block19;
                        if (!bl3) break block21;
                    }
                    bl3 = true;
                    break block19;
                }
                bl3 = false;
            }
            drawContext2.method_51433(textRenderer2, string, n13, n12, n11, bl3);
            n10 += n6;
            if (!bl2) continue;
        }
    }

    private void clearBounds() {
        this.lastBounds = this.layout.emptyAt(this.hudX, this.hudY);
    }

    @Override
    public void writeExtra(JsonObject jsonObject) {
        jsonObject.addProperty(this.key + "X", (Number)this.hudX);
        jsonObject.addProperty(this.key + "Y", (Number)this.hudY);
    }

    @Override
    public void readExtra(JsonObject jsonObject) {
        Object object;
        JsonObject jsonObject2;
        block7: {
            block8: {
                boolean bl = AmbienceModule.b;
                jsonObject2 = jsonObject;
                object = this.key;
                if (bl) break block7;
                if (!jsonObject2.has((String)object + "X")) break block8;
                this.hudX = jsonObject.get(this.key + "X").getAsInt();
            }
            jsonObject2 = jsonObject;
            object = this.key + "Y";
        }
        if (jsonObject2.has((String)object)) {
            this.hudY = jsonObject.get(this.key + "Y").getAsInt();
        }
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

    
    static {
        block16: {
            block15: {
                block14: {
                    var13 = new String[3];
                    var11_1 = 0;
                    var10_2 = "m\"o\u0005jr:I:m\u0005\u0006| b\u0005h&\u0006n+`\u0004i%";
                    var12_3 = "m\"o\u0005jr:I:m\u0005\u0006| b\u0005h&\u0006n+`\u0004i%".length();
                    var9_4 = 11;
                    var8_5 = -1;
lbl7:

                    while (true) {
                        continue;
                        break;
                    }
lbl9:

                    while (true) {
                        var13[var11_1++] = new String(v0).intern();
                        if ((var8_5 += var9_4) < var12_3) {
                            var9_4 = var10_2.charAt(var8_5);
                            continue;
                        }
                        break block14;
                        break;
                    }
                    v1 = ++var8_5;
                    v2 = var10_2.substring(v1, v1 + var9_4).toCharArray();
                    v0 = v2;
                    v3 = v2.length;
                    var14_6 = 0;
                    do {
                        v0 = v0;
                        v4 = var14_6;
                        v5 = v0[v4];
                        switch (var14_6 % 7) {
                            case 0: {
                                v6 = 61;
                                break;
                            }
                            case 1: {
                                v6 = 67;
                                break;
                            }
                            case 2: {
                                v6 = 1;
                                break;
                            }
                            case 3: {
                                v6 = 96;
                                break;
                            }
                            case 4: {
                                v6 = 6;
                                break;
                            }
                            case 5: {
                                v6 = 82;
                                break;
                            }
                            default: {
                                v6 = 73;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_6;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_6);
                    while (true)
                }
                TextHud.b = var13;
                var0_7 = 5847040836042674851L;
                var6_8 = new long[3];
                var3_9 = 0;
                var4_10 = "\u00d9\u00ed\u00b5\u00d7\u0018WV$\u00a6\u008d\u00b0\u00c8\u001d/\u00b5\u00f4\u00cd\u00ce\u00b6\u0090\u00d8\u00dc\u00a3\u00af";
                var5_11 = "\u00d9\u00ed\u00b5\u00d7\u0018WV$\u00a6\u008d\u00b0\u00c8\u001d/\u00b5\u00f4\u00cd\u00ce\u00b6\u0090\u00d8\u00dc\u00a3\u00af".length();
                var2_12 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl63:

                while (true) {
                    var6_8[v7] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block16;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v7 = var3_9++;
            while (true)
        }
        TextHud.e = var6_8;
        TextHud.f = new Integer[3];
        TextHud.SCRATCH = new HudLayout();
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x12D3;
        if (f[n2] == null) {
            TextHud.f[n2] = (int)(e[n2] ^ l);
        }
        return f[n2];
    }
}
