package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.StringSetting;
import java.util.Objects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public final class WatermarkModule
extends Module
implements DraggableHud {
    private static final int PAD_X;
    private static final int PAD_Y;
    private static WatermarkModule instance;
    private final StringSetting text;
    private final StringSetting subText;
    private final BooleanSetting rainbow;
    private final ColorSetting colorA;
    private final ColorSetting colorB;
    private final ColorSetting subColor;
    private final BooleanSetting background;
    private final ColorSetting bgColor;
    private final BooleanSetting shadow;
    private int hudX;
    private int hudY;
    private int[] lastBounds;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;
    private static final long f;

    public WatermarkModule() {
        String[] stringArray = b;
        super(stringArray[0], stringArray[14], Category.HUD, false);
        this.text = new StringSetting(stringArray[17], stringArray[12], 96);
        this.subText = new StringSetting(stringArray[15], "", 96);
        this.rainbow = new BooleanSetting(stringArray[6], false);
        this.colorA = new ColorSetting(stringArray[5], WatermarkModule.a(18769, 5547504771460685895L), false);
        this.colorB = new ColorSetting(stringArray[18], WatermarkModule.a(6330, 1645406911786332587L), false);
        this.subColor = new ColorSetting(stringArray[1], WatermarkModule.a(15237, 2318958278973723295L), false);
        this.background = new BooleanSetting(stringArray[4], true);
        this.bgColor = new ColorSetting(stringArray[2], WatermarkModule.a(22020, 3200204643567206167L), true);
        this.shadow = new BooleanSetting(stringArray[11], true);
        this.hudX = WatermarkModule.a(7043, 1550095681179562647L);
        this.hudY = WatermarkModule.a(7553, 1235188424490398865L);
        this.lastBounds = new int[]{4, 4, 0, 0};
        instance = this;
        this.addSettings(this.text, this.subText, this.rainbow, this.colorA, this.colorB, this.subColor, this.background, this.bgColor, this.shadow);
        this.text.children(this.rainbow, this.colorA, this.colorB);
        this.subText.children(this.subColor);
        this.background.children(this.bgColor);
        this.colorA.visibleWhen(this::lambda$new$0);
        this.colorB.visibleWhen(this::lambda$new$1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void onHudRender(DrawContext drawContext) {
        String string;
        int n;
        int n2;
        String[] stringArray;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        TextRenderer textRenderer;
        boolean bl;
        block34: {
            int n8;
            int n9;
            String[] stringArray2;
            String[] stringArray3;
            block33: {
                String string2;
                String[] stringArray4;
                int n10;
                block32: {
                    String[] stringArray5;
                    MinecraftClient minecraftClient;
                    block31: {
                        String string3;
                        block29: {
                            String string4;
                            block30: {
                                String[] stringArray6;
                                String string5;
                                block27: {
                                    String string6;
                                    block28: {
                                        MinecraftClient minecraftClient2;
                                        block26: {
                                            minecraftClient = MinecraftClient.method_1551();
                                            bl = AmbienceModule.b;
                                            minecraftClient2 = minecraftClient;
                                            if (bl) break block26;
                                            if (minecraftClient2.field_1724 == null) return;
                                            minecraftClient2 = minecraftClient;
                                        }
                                        if (minecraftClient2.field_1690.field_1842) {
                                            return;
                                        }
                                        string6 = this.text.getValue();
                                        string5 = string6;
                                        if (bl) break block27;
                                        if (!string5.isEmpty()) break block28;
                                        stringArray6 = b;
                                        string6 = stringArray6[13];
                                    }
                                    string5 = string6;
                                }
                                stringArray6 = b;
                                stringArray3 = string5.split(stringArray6[16], -1);
                                string4 = this.subText.getValue();
                                string3 = string4;
                                if (bl) break block29;
                                if (!string3.isEmpty()) break block30;
                                stringArray5 = new String[]{};
                                break block31;
                            }
                            string3 = string4;
                        }
                        stringArray5 = string3.split(b[7], -1);
                    }
                    stringArray2 = stringArray5;
                    textRenderer = minecraftClient.field_1772;
                    Objects.requireNonNull(textRenderer);
                    int n11 = 9;
                    n7 = n11 + 1;
                    n10 = 0;
                    stringArray4 = stringArray3;
                    n9 = stringArray4.length;
                    for (n6 = 0; n6 < n9; ++n6) {
                        string2 = stringArray4[n6];
                        if (!bl) {
                            n10 = Math.max(n10, textRenderer.method_1727(string2));
                            if (!bl) continue;
                        }
                        break block32;
                    }
                    stringArray4 = stringArray2;
                    n9 = stringArray4.length;
                }
                for (n6 = 0; n6 < n9; ++n6) {
                    string2 = stringArray4[n6];
                    n8 = n10;
                    if (!bl) {
                        n10 = Math.max(n8, textRenderer.method_1727(string2));
                        if (!bl) continue;
                    }
                    break block33;
                }
                n8 = n10 + 10;
            }
            n5 = n8;
            int n12 = n9 = stringArray3.length + stringArray2.length;
            if (!bl) {
                n6 = n12 * n7 - 1 + 6;
                if (this.background.getValue()) {
                    Round.rect(drawContext, this.hudX, this.hudY, n5, n6, 8, this.bgColor.getARGB());
                }
                n12 = this.shadow.getValue() ? 1 : 0;
            }
            n4 = n12;
            n3 = this.hudY + 3;
            stringArray = stringArray3;
            n2 = stringArray.length;
            for (n = 0; n < n2; ++n) {
                string = stringArray[n];
                if (!bl) {
                    this.gradientLine(drawContext, textRenderer, string, this.hudX + 5, n3, n4 != 0);
                    n3 += n7;
                    if (!bl) continue;
                }
                break block34;
            }
            stringArray = stringArray2;
            n2 = stringArray.length;
        }
        for (n = 0; n < n2; ++n) {
            string = stringArray[n];
            drawContext.method_51433(textRenderer, string, this.hudX + 5, n3, WatermarkModule.a(18928, 1615602922682245358L) | this.subColor.getRGB(), n4 != 0);
            n3 += n7;
            if (bl) return;
            if (!bl) continue;
            break;
        }
        this.lastBounds = new int[]{this.hudX, this.hudY, n5, n6};
    }

    private void gradientLine(DrawContext drawContext, TextRenderer textRenderer, String string, int n, int n2, boolean bl) {
        int n3 = string.length();
        int n4 = 0;
        boolean bl2 = AmbienceModule.b;
        while (n4 < n3) {
            block17: {
                int n5;
                String string2;
                block18: {
                    int n6;
                    block16: {
                        WatermarkModule watermarkModule;
                        float f;
                        block14: {
                            block15: {
                                float f2;
                                block13: {
                                    int n7;
                                    block11: {
                                        block12: {
                                            string2 = String.valueOf(string.charAt(n4));
                                            n7 = n3;
                                            if (bl2) break block11;
                                            if (n7 > 1) break block12;
                                            f2 = 0.0f;
                                            break block13;
                                        }
                                        n7 = n4;
                                    }
                                    f2 = (float)n7 / (float)(n3 - 1);
                                }
                                f = f2;
                                watermarkModule = this;
                                if (bl2) break block14;
                                if (!watermarkModule.rainbow.getValue()) break block15;
                                n6 = WatermarkModule.rainbow(n4);
                                break block16;
                            }
                            watermarkModule = this;
                        }
                        n6 = WatermarkModule.lerp(watermarkModule.colorA.getARGB(), this.colorB.getARGB(), f);
                    }
                    n5 = n6;
                    if (bl2) break block17;
                    if (!bl) break block18;
                    int n8 = WatermarkModule.a(11653, 6247077123478684824L) | n5 >> 2 & WatermarkModule.a(24068, 5065665584565286675L);
                    drawContext.method_51433(textRenderer, string2, n + 1, n2 + 1, n8, false);
                }
                drawContext.method_51433(textRenderer, string2, n, n2, n5, false);
                n += textRenderer.method_1727(string2);
                ++n4;
            }
            if (!bl2) continue;
        }
    }

    private static int rainbow(int n) {
        float f = ((float)(System.currentTimeMillis() % WatermarkModule.f) / 3000.0f + (float)n * 0.05f) % 1.0f;
        return WatermarkModule.a(29919, 6002448634826186186L) | MathHelper.method_15369((float)f, (float)0.7f, (float)1.0f) & WatermarkModule.a(30176, 2662197135471500530L);
    }

    private static int lerp(int n, int n2, float f) {
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        int n7 = n2 >>> 24 & 0xFF;
        int n8 = n2 >>> 16 & 0xFF;
        int n9 = n2 >>> 8 & 0xFF;
        int n10 = n2 & 0xFF;
        int n11 = Math.round((float)n3 + (float)(n7 - n3) * f);
        int n12 = Math.round((float)n4 + (float)(n8 - n4) * f);
        int n13 = Math.round((float)n5 + (float)(n9 - n5) * f);
        int n14 = Math.round((float)n6 + (float)(n10 - n6) * f);
        return n11 << 24 | n12 << 16 | n13 << 8 | n14;
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
        jsonObject.addProperty(stringArray[3], (Number)this.hudX);
        jsonObject.addProperty(stringArray[8], (Number)this.hudY);
    }

    @Override
    public void readExtra(JsonObject jsonObject) {
        JsonObject jsonObject2;
        block7: {
            block8: {
                boolean bl = AmbienceModule.b;
                jsonObject2 = jsonObject;
                if (bl) break block7;
                if (!jsonObject2.has(b[9])) break block8;
                this.hudX = jsonObject.get(b[3]).getAsInt();
            }
            jsonObject2 = jsonObject;
        }
        if (jsonObject2.has(b[10])) {
            this.hudY = jsonObject.get(b[8]).getAsInt();
        }
    }

    private Boolean lambda$new$1() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = AmbienceModule.b;
                bl = this.rainbow.getValue();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private Boolean lambda$new$0() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = AmbienceModule.b;
                bl = this.rainbow.getValue();
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
        block30: {
            block29: {
                block28: {
                    block27: {
                        block26: {
                            var15 = new String[19];
                            var13_1 = 0;
                            var12_2 = " |\u001a*\f?.\u0005v\u000e$h\f;\u0017&#\u0012=\r \u0012==\u00105|\r$\u0019  \u0002s\no\u001d=#\u0018o\u0004\u001fh\n\u0017\n5|\r$\u0019  \u0002s\n\f#x\u0016;^1 \u001br\u001coO\u0007%|\u0007!\u001c=8\u0002+a\u0004\u001fh\n\u0016\u0004\u001fh\n\u0017\u0004\u001fh\n\u0016\u0006$u\u000f+\u0011%\u00066h\u001c \f3\u00066h\u001c \f3\u00134h\u001d;\u0011?o\u0014q\u0007*\u0010&o\u0015|\n(\u001b\b$h\f;\u0017&#\u0012\u0002+a";
                            var14_3 = " |\u001a*\f?.\u0005v\u000e$h\f;\u0017&#\u0012=\r \u0012==\u00105|\r$\u0019  \u0002s\no\u001d=#\u0018o\u0004\u001fh\n\u0017\n5|\r$\u0019  \u0002s\n\f#x\u0016;^1 \u001br\u001coO\u0007%|\u0007!\u001c=8\u0002+a\u0004\u001fh\n\u0016\u0004\u001fh\n\u0017\u0004\u001fh\n\u0016\u0006$u\u000f+\u0011%\u00066h\u001c \f3\u00066h\u001c \f3\u00134h\u001d;\u0011?o\u0014q\u0007*\u0010&o\u0015|\n(\u001b\b$h\f;\u0017&#\u0012\u0002+a".length();
                            var11_4 = 9;
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
                                var12_2 = "#x\u0016;\f#x\u0016;^1 \u001br\u001coL";
                                var14_3 = "#x\u0016;\f#x\u0016;^1 \u001br\u001coL".length();
                                var11_4 = 4;
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
                                    v10 = 119;
                                    break;
                                }
                                case 1: {
                                    v10 = 29;
                                    break;
                                }
                                case 2: {
                                    v10 = 110;
                                    break;
                                }
                                case 3: {
                                    v10 = 79;
                                    break;
                                }
                                case 4: {
                                    v10 = 126;
                                    break;
                                }
                                case 5: {
                                    v10 = 82;
                                    break;
                                }
                                default: {
                                    v10 = 79;
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
                    WatermarkModule.b = var15;
                    var2_7 = 8210053194083789533L;
                    var8_8 = new long[13];
                    var5_9 = 0;
                    var6_10 = "r\u00f2G9\u00ffi\u009el\u0097\u0085\u00a6\u00c1\n\u00bc\u00a2\u00f1\u00bd\u00e7\u0003[\u00b0\u00ff\u00d8Nz,\u009d+F;7\u0017O]N\u0085\tDI\u00d0\u008f1\u00de\u00b4\u0004\u0000\u0019\u00c6\u0010\u0099\u00d5\u009at\u0000^R\u00baE;\u00d34\f\u00a1\u009c\u0083:\u0089\u00a5\u0003\u00e2\n3$\u0095\u00a2\u0014\u00e3\u0014\u00e2\u0005\u00c4\u00be\u0098_h$\u00ef[";
                    var7_11 = "r\u00f2G9\u00ffi\u009el\u0097\u0085\u00a6\u00c1\n\u00bc\u00a2\u00f1\u00bd\u00e7\u0003[\u00b0\u00ff\u00d8Nz,\u009d+F;7\u0017O]N\u0085\tDI\u00d0\u008f1\u00de\u00b4\u0004\u0000\u0019\u00c6\u0010\u0099\u00d5\u009at\u0000^R\u00baE;\u00d34\f\u00a1\u009c\u0083:\u0089\u00a5\u0003\u00e2\n3$\u0095\u00a2\u0014\u00e3\u0014\u00e2\u0005\u00c4\u00be\u0098_h$\u00ef[".length();
                    var4_12 = 0;
                    while (true) {
                        var9_13 = var6_10.substring(var4_12, var4_12 += 8).getBytes("ISO-8859-1");
                        v11 = var8_8;
                        v12 = var5_9++;
                        v13 = ((long)var9_13[0] & 255L) << 56 | ((long)var9_13[1] & 255L) << 48 | ((long)var9_13[2] & 255L) << 40 | ((long)var9_13[3] & 255L) << 32 | ((long)var9_13[4] & 255L) << 24 | ((long)var9_13[5] & 255L) << 16 | ((long)var9_13[6] & 255L) << 8 | (long)var9_13[7] & 255L;
                        v14 = -1;
                        break block28;
                        break;
                    }
lbl89:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_12 < var7_11) continue;
                        var6_10 = "\u00f7#g\u0082\u00c1\u0006nE\u00c0\u0013\u00eeE\u00d0\u00d8X\u00f6";
                        var7_11 = "\u00f7#g\u0082\u00c1\u0006nE\u00c0\u0013\u00eeE\u00d0\u00d8X\u00f6".length();
                        var4_12 = 0;
                        while (true) {
                            var9_13 = var6_10.substring(var4_12, var4_12 += 8).getBytes("ISO-8859-1");
                            v11 = var8_8;
                            v12 = var5_9++;
                            v13 = ((long)var9_13[0] & 255L) << 56 | ((long)var9_13[1] & 255L) << 48 | ((long)var9_13[2] & 255L) << 40 | ((long)var9_13[3] & 255L) << 32 | ((long)var9_13[4] & 255L) << 24 | ((long)var9_13[5] & 255L) << 16 | ((long)var9_13[6] & 255L) << 8 | (long)var9_13[7] & 255L;
                            v14 = 0;
                            break block28;
                            break;
                        }
                        break;
                    }
lbl102:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_12 < var7_11) continue;
                        break block29;
                        break;
                    }
                }
                v15 = v13 ^ var2_7;
                switch (v14) {
                    default: {
                        continue;
                    }
                    case 0:
lbl113:

                    continue;
                }
            }
            WatermarkModule.d = var8_8;
            WatermarkModule.e = new Integer[13];
            WatermarkModule.PAD_Y = WatermarkModule.a(11417, 2376620634081330565L);
            WatermarkModule.PAD_X = WatermarkModule.a(8642, 6028992762588170461L);
            break block30;
lbl120:

            while (true) {
                continue;
                break;
            }
        }
        var0_14 = 4080610575964854349L;
        while (true)
        WatermarkModule.f = 4080610575964853237L ^ var0_14;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2916;
        if (e[n2] == null) {
            WatermarkModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
