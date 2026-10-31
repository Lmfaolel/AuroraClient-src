package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.CustomCrystalsModule$Mode;
import dev.quelchi.aurora.module.modules.CustomCrystalsModule$PartSettings;
import dev.quelchi.aurora.module.modules.CustomCrystalsModule$Piece;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import net.minecraft.util.math.MathHelper;

public final class CustomCrystalsModule
extends Module {
    private static CustomCrystalsModule instance;
    private final EnumSetting<CustomCrystalsModule$Mode> mode;
    private final NumberSetting lineWidth;
    private final NumberSetting scale;
    private final NumberSetting rotation;
    private final NumberSetting bounceHeight;
    private final NumberSetting bounceSpeed;
    private final NumberSetting heightOffset;
    private final NumberSetting opacity;
    private final BooleanSetting customLight;
    private final NumberSetting lightLevel;
    private final CustomCrystalsModule$PartSettings core;
    private final CustomCrystalsModule$PartSettings inner;
    private final CustomCrystalsModule$PartSettings outer;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public CustomCrystalsModule() {
        block4: {
            String[] stringArray = b;
            super(stringArray[0], stringArray[12], Category.RENDER, false);
            this.mode = new EnumSetting<CustomCrystalsModule$Mode>(stringArray[9], CustomCrystalsModule$Mode.DEFAULT);
            this.lineWidth = new NumberSetting(stringArray[5], 0.5, 5.0, 2.0, 0.5);
            this.scale = new NumberSetting(stringArray[18], 0.1, 3.0, 1.0, 0.05);
            this.rotation = new NumberSetting(stringArray[10], 1.0, 10.0, 1.0, 0.1);
            this.bounceHeight = new NumberSetting(stringArray[15], 0.0, 3.0, 1.0, 0.05);
            this.bounceSpeed = new NumberSetting(stringArray[1], 0.0, 3.0, 1.0, 0.05);
            this.heightOffset = new NumberSetting(stringArray[2], -1.0, 1.0, 0.0, 0.05);
            this.opacity = new NumberSetting(stringArray[8], 5.0, 100.0, 100.0, 5.0);
            this.customLight = new BooleanSetting(stringArray[16], false);
            this.lightLevel = new NumberSetting(stringArray[4], 0.0, 15.0, 15.0, 1.0);
            this.core = new CustomCrystalsModule$PartSettings(this, stringArray[3]);
            this.inner = new CustomCrystalsModule$PartSettings(this, stringArray[11]);
            this.outer = new CustomCrystalsModule$PartSettings(this, stringArray[7]);
            instance = this;
            this.addSettings(this.mode, this.lineWidth, this.scale, this.rotation, this.bounceHeight, this.bounceSpeed, this.heightOffset, this.opacity, this.customLight, this.lightLevel);
            this.lineWidth.visibleWhen(this::lambda$new$0);
            boolean bl = AmbienceModule.b;
            this.opacity.visibleWhen(this::tinted);
            this.customLight.visibleWhen(this::lambda$new$1);
            this.customLight.children(this.lightLevel);
            for (Setting setting : new Setting[]{this.mode, this.lineWidth, this.scale, this.rotation, this.bounceHeight, this.bounceSpeed, this.heightOffset, this.opacity, this.customLight, this.lightLevel}) {
                setting.tab(b[17]);
                if (!bl) {
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            stringArray = b;
            this.core.register(stringArray[14]);
            this.inner.register(stringArray[6]);
            this.outer.register(stringArray[13]);
        }
    }

    private static boolean on() {
        boolean bl;
        block6: {
            block5: {
                CustomCrystalsModule customCrystalsModule;
                boolean bl2;
                block4: {
                    bl2 = AmbienceModule.b;
                    customCrystalsModule = instance;
                    if (bl2) break block4;
                    if (customCrystalsModule == null) break block5;
                    customCrystalsModule = instance;
                }
                bl = customCrystalsModule.isEnabled();
                if (bl2) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean tinted() {
        CustomCrystalsModule$Mode customCrystalsModule$Mode;
        block6: {
            CustomCrystalsModule$Mode customCrystalsModule$Mode2 = this.mode.getValue();
            boolean bl = AmbienceModule.b;
            customCrystalsModule$Mode = customCrystalsModule$Mode2;
            if (bl) break block6;
            if (customCrystalsModule$Mode == CustomCrystalsModule$Mode.DEFAULT) return true;
            customCrystalsModule$Mode = customCrystalsModule$Mode2;
        }
        if (customCrystalsModule$Mode != CustomCrystalsModule$Mode.WIREFRAME) return false;
        return true;
    }

    public static boolean active() {
        return CustomCrystalsModule.on();
    }

    public static CustomCrystalsModule$Mode mode() {
        CustomCrystalsModule$Mode customCrystalsModule$Mode = CustomCrystalsModule.on() ? CustomCrystalsModule.instance.mode.getValue() : CustomCrystalsModule$Mode.DEFAULT;
        return customCrystalsModule$Mode;
    }

    public static float lineWidth() {
        return (float)CustomCrystalsModule.instance.lineWidth.getValue();
    }

    public static float scale() {
        float f = CustomCrystalsModule.on() ? (float)CustomCrystalsModule.instance.scale.getValue() : 1.0f;
        return f;
    }

    public static float rotation() {
        float f = CustomCrystalsModule.on() ? (float)CustomCrystalsModule.instance.rotation.getValue() : 1.0f;
        return f;
    }

    public static float bounceHeight() {
        float f = CustomCrystalsModule.on() ? (float)CustomCrystalsModule.instance.bounceHeight.getValue() : 1.0f;
        return f;
    }

    public static float bounceSpeed() {
        float f = CustomCrystalsModule.on() ? (float)CustomCrystalsModule.instance.bounceSpeed.getValue() : 1.0f;
        return f;
    }

    public static float heightOffset() {
        float f = CustomCrystalsModule.on() ? (float)CustomCrystalsModule.instance.heightOffset.getValue() : 0.0f;
        return f;
    }

    public static float opacity() {
        float f = CustomCrystalsModule.on() ? (float)(CustomCrystalsModule.instance.opacity.getValue() / 100.0) : 1.0f;
        return f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int lightLevel() {
        CustomCrystalsModule customCrystalsModule;
        block7: {
            boolean bl;
            block6: {
                boolean bl2 = AmbienceModule.b;
                bl = CustomCrystalsModule.on();
                if (bl2) break block6;
                if (!bl) return -1;
                customCrystalsModule = instance;
                if (bl2) break block7;
                bl = customCrystalsModule.customLight.getValue();
            }
            if (!bl) return -1;
            customCrystalsModule = instance;
        }
        int n = customCrystalsModule.lightLevel.getValueInt();
        return n;
    }

    
    private static CustomCrystalsModule$PartSettings part(CustomCrystalsModule$Piece customCrystalsModule$Piece) {
        CustomCrystalsModule$PartSettings customCrystalsModule$PartSettings;
        switch (customCrystalsModule$Piece.ordinal()) {
            default: {
                throw new MatchException(null, null);
            }
            case 0: {
                customCrystalsModule$PartSettings = CustomCrystalsModule.instance.core;
                return customCrystalsModule$PartSettings;
            }
            case 1: {
                customCrystalsModule$PartSettings = CustomCrystalsModule.instance.inner;
                return customCrystalsModule$PartSettings;
            }
            case 2: 
        }
        customCrystalsModule$PartSettings = CustomCrystalsModule.instance.outer;
        return customCrystalsModule$PartSettings;
    }

    public static boolean shown(CustomCrystalsModule$Piece customCrystalsModule$Piece) {
        return CustomCrystalsModule.part((CustomCrystalsModule$Piece)customCrystalsModule$Piece).show.getValue();
    }

    public static float partScale(CustomCrystalsModule$Piece customCrystalsModule$Piece) {
        return (float)CustomCrystalsModule.part((CustomCrystalsModule$Piece)customCrystalsModule$Piece).scale.getValue();
    }

    public static float partOffset(CustomCrystalsModule$Piece customCrystalsModule$Piece) {
        return (float)CustomCrystalsModule.part((CustomCrystalsModule$Piece)customCrystalsModule$Piece).offset.getValue();
    }

    public static float partRotation(CustomCrystalsModule$Piece customCrystalsModule$Piece) {
        return (float)CustomCrystalsModule.part((CustomCrystalsModule$Piece)customCrystalsModule$Piece).rotation.getValue();
    }

    public static float partBounceHeight(CustomCrystalsModule$Piece customCrystalsModule$Piece) {
        return (float)CustomCrystalsModule.part((CustomCrystalsModule$Piece)customCrystalsModule$Piece).bounceHeight.getValue();
    }

    public static float partBounceSpeed(CustomCrystalsModule$Piece customCrystalsModule$Piece) {
        return (float)CustomCrystalsModule.part((CustomCrystalsModule$Piece)customCrystalsModule$Piece).bounceSpeed.getValue();
    }

    public static int partRgb(CustomCrystalsModule$Piece customCrystalsModule$Piece, long l) {
        CustomCrystalsModule$PartSettings customCrystalsModule$PartSettings;
        block5: {
            CustomCrystalsModule$PartSettings customCrystalsModule$PartSettings2;
            block4: {
                customCrystalsModule$PartSettings = CustomCrystalsModule.part(customCrystalsModule$Piece);
                boolean bl = AmbienceModule.b;
                customCrystalsModule$PartSettings2 = customCrystalsModule$PartSettings;
                if (bl) break block4;
                if (customCrystalsModule$PartSettings2.chroma.getValue()) break block5;
                customCrystalsModule$PartSettings2 = customCrystalsModule$PartSettings;
            }
            return customCrystalsModule$PartSettings2.color.getRGB() & CustomCrystalsModule.a(23414, 7544671108471267232L);
        }
        float f = (float)(((double)l / 1000.0 * customCrystalsModule$PartSettings.chromaSpeed.getValue() + customCrystalsModule$PartSettings.chromaDelay.getValue()) % 1.0);
        return MathHelper.method_15369((float)f, (float)1.0f, (float)1.0f) & CustomCrystalsModule.a(22475, 5196039560732414748L);
    }

    private Boolean lambda$new$1() {
        boolean bl = this.mode.getValue() == CustomCrystalsModule$Mode.DEFAULT;
        return bl;
    }

    private Boolean lambda$new$0() {
        boolean bl = this.mode.getValue() == CustomCrystalsModule$Mode.WIREFRAME;
        return bl;
    }

    static void access$000(CustomCrystalsModule customCrystalsModule, Setting[] settingArray) {
        customCrystalsModule.addSettings(settingArray);
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[19];
                        var11_1 = 0;
                        var10_2 = "r8?.LATt#(z`^\rB9-6P\fs\"94@ITB=)?G\ry(%=KXT^+*)FX\u0004r\">?\u000b}$+2W\f\u0018T;)6\n}$\"?\u0003[\u001dU9$\u000bx#\"?Q\f\u0012C,!?\u000b~88?Q\f\u0012C,!?\u0007~=-9JX\r\u0004|\"(?\u000ec\"8;WE\u001b_m?*FI\u0010\u000bx#\"?Q\f\u0012C,!?!c(?.Z@\u0011\u00119$?\u0003O\u0006H>8;O\u0000TA,>.\u0003N\r\u0011=-(W\u000b~88?Q\f\u0012C,!?\u0004r\">?\rs\"94@ITY(%=KX\fr8?.LAT]$+2W";
                        var12_3 = "r8?.LATt#(z`^\rB9-6P\fs\"94@ITB=)?G\ry(%=KXT^+*)FX\u0004r\">?\u000b}$+2W\f\u0018T;)6\n}$\"?\u0003[\u001dU9$\u000bx#\"?Q\f\u0012C,!?\u000b~88?Q\f\u0012C,!?\u0007~=-9JX\r\u0004|\"(?\u000ec\"8;WE\u001b_m?*FI\u0010\u000bx#\"?Q\f\u0012C,!?!c(?.Z@\u0011\u00119$?\u0003O\u0006H>8;O\u0000TA,>.\u0003N\r\u0011=-(W\u000b~88?Q\f\u0012C,!?\u0004r\">?\rs\"94@ITY(%=KX\fr8?.LAT]$+2W".length();
                        var9_4 = 19;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "r?5)WM\u0018\u0005b.-6F";
                            var12_3 = "r?5)WM\u0018\u0005b.-6F".length();
                            var9_4 = 7;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block20;
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
                            break block21;
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
                                v10 = 49;
                                break;
                            }
                            case 1: {
                                v10 = 77;
                                break;
                            }
                            case 2: {
                                v10 = 76;
                                break;
                            }
                            case 3: {
                                v10 = 90;
                                break;
                            }
                            case 4: {
                                v10 = 35;
                                break;
                            }
                            case 5: {
                                v10 = 44;
                                break;
                            }
                            default: {
                                v10 = 116;
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
                CustomCrystalsModule.b = var13;
                var0_7 = 3876644551080056189L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "\u00b62\nM\\\u00feu\"\u008a\u0082{\u00d9k>y\u009e";
                var5_11 = "\u00b62\nM\\\u00feu\"\u008a\u0082{\u00d9k>y\u009e".length();
                var2_12 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl84:

                while (true) {
                    var6_8[v11] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block23;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v11 = var3_9++;
            while (true)
        }
        CustomCrystalsModule.d = var6_8;
        CustomCrystalsModule.e = new Integer[2];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x44D6;
        if (e[n2] == null) {
            CustomCrystalsModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
