package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.BlockOverlayModule$Style;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import net.minecraft.util.math.MathHelper;

public final class BlockOverlayModule
extends Module {
    private static BlockOverlayModule instance;
    private static final int VANILLA;
    private final EnumSetting<BlockOverlayModule$Style> style;
    private final ColorSetting glowColor;
    private final NumberSetting glowSize;
    private final ColorSetting gradientColor1;
    private final ColorSetting gradientColor2;
    private final NumberSetting gradientSpeed;
    private final BooleanSetting gradientFaces;
    private final NumberSetting gradientFaceOpacity;
    private final ColorSetting pulseColor;
    private final NumberSetting pulseSpeed;
    private final BooleanSetting outline;
    private final ColorSetting outlineColor;
    private final NumberSetting lineWidth;
    private final BooleanSetting outlineChroma;
    private final NumberSetting outlineSpeed;
    private final BooleanSetting faces;
    private final ColorSetting faceColor;
    private final BooleanSetting faceChroma;
    private final NumberSetting faceSpeed;
    private final BooleanSetting throughWalls;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public static BlockOverlayModule get() {
        return instance;
    }

    public BlockOverlayModule() {
        String[] stringArray = b;
        super(stringArray[14], stringArray[17], Category.RENDER, false);
        this.style = new EnumSetting<BlockOverlayModule$Style>(stringArray[9], BlockOverlayModule$Style.Default);
        this.glowColor = new ColorSetting(stringArray[8], BlockOverlayModule.a(18182, 6524371763905851796L));
        this.glowSize = new NumberSetting(stringArray[20], 1.0, 10.0, 4.0, 0.5);
        this.gradientColor1 = new ColorSetting(stringArray[6], BlockOverlayModule.a(26564, 8349605928282149201L));
        this.gradientColor2 = new ColorSetting(stringArray[15], BlockOverlayModule.a(16036, 5370411415798876213L));
        this.gradientSpeed = new NumberSetting(stringArray[10], 0.1, 3.0, 0.5, 0.1);
        this.gradientFaces = new BooleanSetting(stringArray[7], false);
        this.gradientFaceOpacity = new NumberSetting(stringArray[0], 0.05, 1.0, 0.3, 0.05);
        this.pulseColor = new ColorSetting(stringArray[2], -1);
        this.pulseSpeed = new NumberSetting(stringArray[12], 0.1, 3.0, 0.75, 0.05);
        this.outline = new BooleanSetting(stringArray[5], true);
        this.outlineColor = new ColorSetting(stringArray[18], BlockOverlayModule.a(32160, 1526362052379003699L));
        this.lineWidth = new NumberSetting(stringArray[3], 1.0, 10.0, 2.5, 0.5);
        this.outlineChroma = new BooleanSetting(stringArray[13], false);
        this.outlineSpeed = new NumberSetting(stringArray[16], 0.05, 1.0, 0.25, 0.05);
        this.faces = new BooleanSetting(stringArray[11], true);
        this.faceColor = new ColorSetting(stringArray[19], BlockOverlayModule.a(29469, 4695077856444892553L));
        this.faceChroma = new BooleanSetting(stringArray[4], false);
        this.faceSpeed = new NumberSetting(stringArray[1], 0.05, 1.0, 0.25, 0.05);
        this.throughWalls = new BooleanSetting(stringArray[21], false);
        instance = this;
        this.addSettings(this.style, this.glowColor, this.glowSize, this.gradientColor1, this.gradientColor2, this.gradientSpeed, this.gradientFaces, this.gradientFaceOpacity, this.pulseColor, this.pulseSpeed, this.outline, this.outlineColor, this.lineWidth, this.outlineChroma, this.outlineSpeed, this.faces, this.faceColor, this.faceChroma, this.faceSpeed, this.throughWalls);
        this.style.children(this.glowColor, this.glowSize, this.gradientColor1, this.gradientColor2, this.gradientSpeed, this.gradientFaces, this.pulseColor, this.pulseSpeed);
        this.gate(BlockOverlayModule$Style.Glow, this.glowColor, this.glowSize);
        this.gate(BlockOverlayModule$Style.Gradient, this.gradientColor1, this.gradientColor2, this.gradientSpeed, this.gradientFaces, this.gradientFaceOpacity);
        this.gradientFaces.children(this.gradientFaceOpacity);
        this.gate(BlockOverlayModule$Style.Pulse, this.pulseColor, this.pulseSpeed);
        this.gate(BlockOverlayModule$Style.Default, this.outline, this.faces);
        this.outline.children(this.outlineColor, this.lineWidth, this.outlineChroma);
        this.outlineChroma.children(this.outlineSpeed);
        this.faces.children(this.faceColor, this.faceChroma);
        this.faceChroma.children(this.faceSpeed);
    }

    private void gate(BlockOverlayModule$Style blockOverlayModule$Style, Setting ... settingArray) {
        Setting[] settingArray2 = settingArray;
        int n = settingArray2.length;
        boolean bl = AmbienceModule.b;
        for (int i = 0; i < n; ++i) {
            Setting setting = settingArray2[i];
            setting.visibleWhen(() -> this.lambda$gate$0(blockOverlayModule$Style));
            if (!bl) continue;
        }
    }

    
    public boolean draws() {
        block35: {
            var1_1 = AmbienceModule.b;
            v0 = this;
            if (var1_1) break block35;
            switch (v0.style.getValue().ordinal()) {
                default: {
                    throw new MatchException(null, null);
                }
                case 0: {
                    v1 = this;
                    if (var1_1) // goto
                    if (!v1.outline.getValue()) // goto
                    v1 = this;
lbl12:

                    v2 = v1.outlineChroma.getValue();
                    if (var1_1) // goto
                    if (v2 != 0) // goto
                    v2 = this.outlineColor.getARGB();
                    if (!var1_1) {
                        if (v2 >>> 24 == 0) goto lbl_neg_1000
                    }
lbl_neg_1000:

                    {
                        v2 = 1;
                    }
lbl_neg_1000:

                    {
                        v2 = 0;
                    }
lbl22:

                    var2_2 = v2;
                    v3 = this;
                    if (var1_1) // goto
                    if (!v3.faces.getValue()) // goto
                    v3 = this;
lbl27:

                    v4 = v3.faceChroma.getValue();
                    if (var1_1) // goto
                    if (v4 != 0) // goto
                    v4 = this.faceColor.getARGB();
                    if (!var1_1) {
                        if (v4 >>> 24 == 0) goto lbl_neg_1000
                    }
lbl_neg_1000:

                    {
                        v4 = 1;
                    }
lbl_neg_1000:

                    {
                        v4 = 0;
                    }
lbl37:

                    var3_3 = v4;
                    v5 = var2_2;
                    if (var1_1 != false) return (boolean)v5;
                    if (v5 != 0) return (boolean)1;
                    v5 = var3_3;
                    if (var1_1 != false) return (boolean)v5;
                    if (v5 != 0) return (boolean)1;
                    return (boolean)0;
                }
                case 1: {
                    v5 = this.glowColor.getA();
                    if (var1_1 != false) return (boolean)v5;
                    if (v5 == 0) return (boolean)0;
                    return (boolean)1;
                }
                case 2: {
                    v5 = this.gradientColor1.getA();
                    if (var1_1 != false) return (boolean)v5;
                    if (v5 != 0) return (boolean)1;
                    v5 = this.gradientColor2.getA();
                    if (var1_1 != false) return (boolean)v5;
                    if (v5 != 0) return (boolean)1;
                    return (boolean)0;
                }
                case 3: 
            }
            v0 = this;
        }
        v5 = v0.pulseColor.getA();
        if (var1_1 != false) return (boolean)v5;
        if (v5 == 0) return (boolean)0;
        return (boolean)1;
    }

    public BlockOverlayModule$Style style() {
        return this.style.getValue();
    }

    public boolean outlines() {
        return this.outline.getValue();
    }

    public boolean fills() {
        return this.faces.getValue();
    }

    public boolean throughWalls() {
        return this.throughWalls.getValue();
    }

    public float lineWidth() {
        return this.lineWidth.getValueFloat();
    }

    public int outlineArgb() {
        return BlockOverlayModule.argb(this.outlineColor, this.outlineChroma, this.outlineSpeed);
    }

    public int faceArgb() {
        return BlockOverlayModule.argb(this.faceColor, this.faceChroma, this.faceSpeed);
    }

    public int glowColor() {
        return this.glowColor.getARGB();
    }

    public float glowSize() {
        return this.glowSize.getValueFloat();
    }

    public int gradientColor1() {
        return this.gradientColor1.getARGB();
    }

    public int gradientColor2() {
        return this.gradientColor2.getARGB();
    }

    public boolean gradientFaces() {
        return this.gradientFaces.getValue();
    }

    public float gradientFaceOpacity() {
        return this.gradientFaceOpacity.getValueFloat();
    }

    public float gradientTime() {
        return (float)((double)System.currentTimeMillis() / 1000.0 * this.gradientSpeed.getValue() % 1.0);
    }

    public int pulseColor() {
        return this.pulseColor.getARGB();
    }

    public float pulse() {
        double d = (double)System.currentTimeMillis() / 1000.0 * this.pulseSpeed.getValue() % 1.0;
        return 0.5f - 0.5f * (float)Math.cos(d * 6.2831854820251465);
    }

    private static int argb(ColorSetting colorSetting, BooleanSetting booleanSetting, NumberSetting numberSetting) {
        block5: {
            int n;
            block4: {
                boolean bl = AmbienceModule.b;
                n = booleanSetting.getValue();
                if (bl) break block4;
                if (n != 0) break block5;
                n = colorSetting.getARGB();
            }
            return n;
        }
        float f = (float)((double)System.currentTimeMillis() * numberSetting.getValue() / 2.0 % 360.0 / 360.0);
        return MathHelper.method_60599((float)f, (float)1.0f, (float)1.0f, (int)colorSetting.getA());
    }

    private Boolean lambda$gate$0(BlockOverlayModule$Style blockOverlayModule$Style) {
        boolean bl = this.style.getValue() == blockOverlayModule$Style;
        return bl;
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[22];
                        var11_1 = 0;
                        var10_2 = "(wH6x\u0018\u001a\u000fuB'!\u0005=fN6<\u000b>cG =W\t\u0001zD!\n\"\u007fE6x\u0000\u0003\nbC\u0006-~Y<5\u0016\u0007!c_?1\u0019\u000f\u0007-yG<*W[\u000e)dJ71\u0012\u0004\u001a6M2;\u0012\u0019\n)zD$x\u0014\u0005\u0002yY\u0005=bR?=\u000e)dJ71\u0012\u0004\u001a6X#=\u0012\u000e\u0005(wH6+\u000b>cG =W\u0019\u001esN7\u0006-~Y<5\u0016\r,zD03W%\u0018sY?9\u000e\u0007-yG<*WX\u0005=fN6< -yG<*W\u000b\u0000r\u000b51\u001b\u0006NbC6x\u0015\u0006\u0001u@s7\u0002\u001e\u0002\u007fE6\u0005-yG<*\u0005-yG<*";
                        var12_3 = "(wH6x\u0018\u001a\u000fuB'!\u0005=fN6<\u000b>cG =W\t\u0001zD!\n\"\u007fE6x\u0000\u0003\nbC\u0006-~Y<5\u0016\u0007!c_?1\u0019\u000f\u0007-yG<*W[\u000e)dJ71\u0012\u0004\u001a6M2;\u0012\u0019\n)zD$x\u0014\u0005\u0002yY\u0005=bR?=\u000e)dJ71\u0012\u0004\u001a6X#=\u0012\u000e\u0005(wH6+\u000b>cG =W\u0019\u001esN7\u0006-~Y<5\u0016\r,zD03W%\u0018sY?9\u000e\u0007-yG<*WX\u0005=fN6< -yG<*W\u000b\u0000r\u000b51\u001b\u0006NbC6x\u0015\u0006\u0001u@s7\u0002\u001e\u0002\u007fE6\u0005-yG<*\u0005-yG<*".length();
                        var9_4 = 12;
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
                            var10_2 = ")zD$x\u0004\u0003\u0014s\r:~Y<-\u0010\u0002NaJ?4\u0004";
                            var12_3 = ")zD$x\u0004\u0003\u0014s\r:~Y<-\u0010\u0002NaJ?4\u0004".length();
                            var9_4 = 9;
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
                                v10 = 110;
                                break;
                            }
                            case 1: {
                                v10 = 22;
                                break;
                            }
                            case 2: {
                                v10 = 43;
                                break;
                            }
                            case 3: {
                                v10 = 83;
                                break;
                            }
                            case 4: {
                                v10 = 88;
                                break;
                            }
                            case 5: {
                                v10 = 119;
                                break;
                            }
                            default: {
                                v10 = 106;
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
                BlockOverlayModule.b = var13;
                var0_7 = 5297460962930976584L;
                var6_8 = new long[6];
                var3_9 = 0;
                var4_10 = "W#\"\u000f\u00e5.\u00a3\u00829\u00f6\u00ca\u00d6\u00ce@\u00ba*\u0004\u00af\u00db|\u00c6P,{\u00ee\u00f8\tH\u007f\u0098Z#";
                var5_11 = "W#\"\u000f\u00e5.\u00a3\u00829\u00f6\u00ca\u00d6\u00ce@\u00ba*\u0004\u00af\u00db|\u00c6P,{\u00ee\u00f8\tH\u007f\u0098Z#".length();
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
                    var4_10 = "i\u00f4\u00a5\u00ac\u00cc?\u00e2\u00e61\u00c1m\u00af\u008e\u00cc\u00a2\u00c1";
                    var5_11 = "i\u00f4\u00a5\u00ac\u00cc?\u00e2\u00e61\u00c1m\u00af\u008e\u00cc\u00a2\u00c1".length();
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
        BlockOverlayModule.d = var6_8;
        BlockOverlayModule.e = new Integer[6];
        BlockOverlayModule.VANILLA = BlockOverlayModule.a(27634, 5401449432673639778L);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3691;
        if (e[n2] == null) {
            BlockOverlayModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
