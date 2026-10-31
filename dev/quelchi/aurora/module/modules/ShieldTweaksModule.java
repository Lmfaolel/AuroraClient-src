package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.ShieldTweaksModule$ShieldState;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.shield.ShieldRenderer;
import dev.quelchi.aurora.shield.ShieldStates;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public final class ShieldTweaksModule
extends Module {
    public static final String CREDIT;
    private static ShieldTweaksModule instance;
    private final BooleanSetting ready;
    private final ColorSetting readyColor;
    private final BooleanSetting blocking;
    private final ColorSetting blockingColor;
    private final BooleanSetting disabled;
    private final ColorSetting disabledColor;
    private final NumberSetting statusOpacity;
    private final BooleanSetting selfOnly;
    private final BooleanSetting blockingDisplay;
    private final ColorSetting markerColor;
    private final NumberSetting markerOpacity;
    private final ColorSetting behindColor;
    private final BooleanSetting armorTint;
    private final ColorSetting armorColor;
    private final NumberSetting armorOpacity;
    private final BooleanSetting fixAnimation;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public ShieldTweaksModule() {
        String[] stringArray = b;
        super(stringArray[7], stringArray[17], Category.COMBAT, false);
        this.ready = new BooleanSetting(stringArray[15], true);
        this.readyColor = new ColorSetting(stringArray[1], ShieldTweaksModule.a(9635, 8560497904934549944L), false);
        this.blocking = new BooleanSetting(stringArray[14], false);
        this.blockingColor = new ColorSetting(stringArray[16], ShieldTweaksModule.a(23028, 5153158177283966445L), false);
        this.disabled = new BooleanSetting(stringArray[2], true);
        this.disabledColor = new ColorSetting(stringArray[16], ShieldTweaksModule.a(28494, 6247294484517794643L), false);
        this.statusOpacity = new NumberSetting(stringArray[0], 5.0, 100.0, 100.0, 5.0);
        this.selfOnly = new BooleanSetting(stringArray[13], false);
        this.blockingDisplay = new BooleanSetting(stringArray[3], true);
        this.markerColor = new ColorSetting(stringArray[16], ShieldTweaksModule.a(16592, 6223063406771084490L), false);
        this.markerOpacity = new NumberSetting(stringArray[18], 0.0, 100.0, 80.0, 1.0);
        this.behindColor = new ColorSetting(stringArray[9], ShieldTweaksModule.a(20956, 4875188899994832324L), false);
        this.armorTint = new BooleanSetting(stringArray[11], true);
        this.armorColor = new ColorSetting(stringArray[16], ShieldTweaksModule.a(32557, 642877223605642033L), false);
        this.armorOpacity = new NumberSetting(stringArray[12], 0.0, 100.0, 60.0, 1.0);
        this.fixAnimation = new BooleanSetting(stringArray[5], true);
        instance = this;
        this.addSettings(this.ready, this.readyColor, this.blocking, this.blockingColor, this.disabled, this.disabledColor, this.statusOpacity, this.selfOnly, this.blockingDisplay, this.markerColor, this.markerOpacity, this.behindColor, this.armorTint, this.armorColor, this.armorOpacity, this.fixAnimation);
        this.ready.children(this.readyColor);
        this.blocking.children(this.blockingColor);
        this.disabled.children(this.disabledColor);
        this.blockingDisplay.children(this.markerColor, this.markerOpacity, this.behindColor);
        this.armorTint.children(this.armorColor, this.armorOpacity);
        this.ready.tab(stringArray[10]);
        this.blocking.tab(stringArray[4]);
        this.disabled.tab(stringArray[4]);
        this.statusOpacity.tab(stringArray[4]);
        this.selfOnly.tab(stringArray[4]);
        this.blockingDisplay.tab(stringArray[4]);
        this.armorTint.tab(stringArray[4]);
        this.fixAnimation.tab(stringArray[8]);
    }

    public static boolean isOn() {
        boolean bl;
        block6: {
            block5: {
                ShieldTweaksModule shieldTweaksModule;
                boolean bl2;
                block4: {
                    ShieldTweaksModule shieldTweaksModule2 = instance;
                    bl2 = AmbienceModule.b;
                    shieldTweaksModule = shieldTweaksModule2;
                    if (bl2) break block4;
                    if (shieldTweaksModule == null) break block5;
                    shieldTweaksModule = shieldTweaksModule2;
                }
                bl = shieldTweaksModule.isEnabled();
                if (bl2) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean on() {
        boolean bl;
        block6: {
            block5: {
                ShieldTweaksModule shieldTweaksModule;
                boolean bl2;
                block4: {
                    ShieldTweaksModule shieldTweaksModule2 = instance;
                    bl2 = AmbienceModule.b;
                    shieldTweaksModule = shieldTweaksModule2;
                    if (bl2) break block4;
                    if (shieldTweaksModule == null) break block5;
                    shieldTweaksModule = shieldTweaksModule2;
                }
                bl = shieldTweaksModule.isEnabled();
                if (bl2) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean colorsShields() {
        boolean bl;
        block16: {
            block15: {
                block17: {
                    boolean bl2;
                    block14: {
                        bl2 = AmbienceModule.b;
                        bl = ShieldTweaksModule.on();
                        if (bl2) break block14;
                        if (!bl) break block15;
                        bl = ShieldTweaksModule.instance.ready.getValue();
                    }
                    if (bl2) break block16;
                    if (bl) break block17;
                    bl = ShieldTweaksModule.instance.blocking.getValue();
                    if (bl2) break block16;
                    if (bl) break block17;
                    bl = ShieldTweaksModule.instance.disabled.getValue();
                    if (bl2) break block16;
                    if (!bl) break block15;
                }
                bl = true;
                break block16;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean desaturatesBeforeTint() {
        return ShieldTweaksModule.colorsShields();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean skips(PlayerEntity playerEntity) {
        ClientPlayerEntity clientPlayerEntity;
        MinecraftClient minecraftClient;
        block14: {
            boolean bl;
            block13: {
                block12: {
                    bl = AmbienceModule.b;
                    boolean bl2 = ShieldTweaksModule.colorsShields();
                    if (bl) return bl2;
                    if (!bl2) return true;
                    if (playerEntity != null) break block12;
                    return true;
                }
                boolean bl3 = ShieldTweaksModule.instance.selfOnly.getValue();
                if (bl) return bl3;
                if (bl3) break block13;
                return false;
            }
            minecraftClient = MinecraftClient.method_1551();
            clientPlayerEntity = minecraftClient.field_1724;
            if (bl) break block14;
            if (clientPlayerEntity == null) return true;
            clientPlayerEntity = playerEntity;
        }
        if (clientPlayerEntity == minecraftClient.field_1724) return false;
        return true;
    }

    public static ShieldTweaksModule$ShieldState stateOf(PlayerEntity playerEntity) {
        PlayerEntity playerEntity2;
        block13: {
            block14: {
                boolean bl;
                block11: {
                    block12: {
                        bl = AmbienceModule.b;
                        playerEntity2 = playerEntity;
                        if (bl) break block11;
                        if (playerEntity2 != null) break block12;
                        return ShieldTweaksModule$ShieldState.READY;
                    }
                    playerEntity2 = playerEntity;
                }
                if (bl) break block13;
                if (!ShieldStates.isCoolingDown(playerEntity2)) break block14;
                return ShieldTweaksModule$ShieldState.DISABLED;
            }
            playerEntity2 = playerEntity;
        }
        if (ShieldStates.isBlocking(playerEntity2)) {
            return ShieldTweaksModule$ShieldState.BLOCKING;
        }
        return ShieldTweaksModule$ShieldState.READY;
    }

    public static int tintFor(PlayerEntity playerEntity) {
        PlayerEntity playerEntity2;
        block11: {
            boolean bl;
            block9: {
                boolean bl2;
                block10: {
                    bl2 = AmbienceModule.b;
                    bl = ShieldTweaksModule.on();
                    if (bl2) break block9;
                    if (bl) break block10;
                    return -1;
                }
                playerEntity2 = playerEntity;
                if (bl2) break block11;
                bl = ShieldTweaksModule.skips(playerEntity2);
            }
            if (bl) {
                return -1;
            }
            playerEntity2 = playerEntity;
        }
        return ShieldTweaksModule.colorFor(ShieldTweaksModule.stateOf(playerEntity2));
    }

    
    public static int colorFor(ShieldTweaksModule$ShieldState shieldTweaksModule$ShieldState) {
        ShieldTweaksModule shieldTweaksModule;
        int n;
        block20: {
            int n2;
            block19: {
                boolean bl = AmbienceModule.b;
                n2 = ShieldTweaksModule.on();
                if (!bl) {
                    if (n2 == 0) {
                        return -1;
                    }
                    n2 = shieldTweaksModule$ShieldState.ordinal();
                }
                if (bl) break block19;
                switch (n2) {
                    default: {
                        throw new MatchException(null, null);
                    }
                    case 0: {
                        n = ShieldTweaksModule.readyTint();
                        return n;
                    }
                    case 1: {
                        n = ShieldTweaksModule.instance.blocking.getValue() ? 1 : 0;
                        if (bl) return n;
                        if (n != 0) {
                            n = ShieldTweaksModule.tint(ShieldTweaksModule.instance.blockingColor);
                            return n;
                        }
                        n = ShieldTweaksModule.readyTint();
                        return n;
                    }
                    case 2: 
                }
                shieldTweaksModule = instance;
                if (bl) break block20;
                n2 = shieldTweaksModule.disabled.getValue() ? 1 : 0;
            }
            if (n2 == 0) {
                n = ShieldTweaksModule.readyTint();
                return n;
            }
            shieldTweaksModule = instance;
        }
        n = ShieldTweaksModule.tint(shieldTweaksModule.disabledColor);
        return n;
    }

    private static int readyTint() {
        int n;
        block6: {
            block5: {
                ShieldTweaksModule shieldTweaksModule;
                block4: {
                    boolean bl = AmbienceModule.b;
                    shieldTweaksModule = instance;
                    if (bl) break block4;
                    if (!shieldTweaksModule.ready.getValue()) break block5;
                    shieldTweaksModule = instance;
                }
                n = ShieldTweaksModule.tint(shieldTweaksModule.readyColor);
                break block6;
            }
            n = -1;
        }
        return n;
    }

    private static int tint(ColorSetting colorSetting) {
        int n = Math.round((float)ShieldTweaksModule.clampPct(ShieldTweaksModule.instance.statusOpacity.getValueInt()) * 255.0f / 100.0f);
        return n << 24 | colorSetting.getRGB();
    }

    @Override
    public void onTick() {
        ShieldStates.update(ShieldTweaksModule.fixesAnimation());
    }

    @Override
    public void onDisable() {
        ShieldStates.clear();
        ShieldRenderer.clearFocus();
    }

    public static boolean markerEnabled() {
        boolean bl;
        block8: {
            block7: {
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    bl = ShieldTweaksModule.on();
                    if (bl2) break block6;
                    if (!bl) break block7;
                    bl = ShieldTweaksModule.instance.blockingDisplay.getValue();
                }
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean armorTintEnabled() {
        boolean bl;
        block8: {
            block7: {
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    bl = ShieldTweaksModule.on();
                    if (bl2) break block6;
                    if (!bl) break block7;
                    bl = ShieldTweaksModule.instance.armorTint.getValue();
                }
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static int markerArgb(LivingEntity livingEntity, Vec3d vec3d) {
        int n;
        int n2;
        block16: {
            block17: {
                int n3;
                boolean bl;
                block12: {
                    double d;
                    block14: {
                        block15: {
                            Vec3d vec3d2;
                            block13: {
                                n2 = Math.round((float)ShieldTweaksModule.clampPct(ShieldTweaksModule.instance.markerOpacity.getValueInt()) * 255.0f / 100.0f);
                                bl = AmbienceModule.b;
                                n3 = 1;
                                if (livingEntity == null) break block12;
                                vec3d2 = vec3d;
                                if (bl) break block13;
                                if (vec3d2 == null) break block12;
                                vec3d2 = livingEntity.method_5828(1.0f);
                            }
                            Vec3d vec3d3 = vec3d2;
                            Vec3d vec3d4 = vec3d.method_1035(livingEntity.method_73189()).method_1029();
                            vec3d4 = new Vec3d(vec3d4.field_1352, 0.0, vec3d4.field_1350);
                            double d2 = vec3d4.method_1026(vec3d3) - 0.0;
                            d = d2 == 0.0 ? 0 : (d2 < 0.0 ? -1 : 1);
                            if (bl) break block14;
                            if (d >= 0) break block15;
                            d = 1.0;
                            break block14;
                        }
                        d = 0.0;
                    }
                    n3 = (int)d;
                }
                n = n3;
                if (bl) break block16;
                if (n == 0) break block17;
                n = ShieldTweaksModule.instance.markerColor.getRGB();
                break block16;
            }
            n = ShieldTweaksModule.instance.behindColor.getRGB();
        }
        int n4 = n;
        return n2 << 24 | n4;
    }

    public static int blendArmor(int n) {
        int n2;
        int n3;
        float f;
        block2: {
            block3: {
                f = (float)ShieldTweaksModule.clampPct(ShieldTweaksModule.instance.armorOpacity.getValueInt()) / 100.0f;
                n3 = n >>> 24 & 0xFF;
                boolean bl = AmbienceModule.b;
                n2 = n3;
                if (bl) break block2;
                if (n2 != 0) break block3;
                n3 = 255;
            }
            n2 = n >>> 16 & 0xFF;
        }
        int n4 = n2;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        int n7 = Math.round((float)n4 + (float)(ShieldTweaksModule.instance.armorColor.getR() - n4) * f);
        int n8 = Math.round((float)n5 + (float)(ShieldTweaksModule.instance.armorColor.getG() - n5) * f);
        int n9 = Math.round((float)n6 + (float)(ShieldTweaksModule.instance.armorColor.getB() - n6) * f);
        return n3 << 24 | n7 << 16 | n8 << 8 | n9;
    }

    private static int clampPct(int n) {
        return Math.max(0, Math.min(100, n));
    }

    public static boolean fixesAnimation() {
        boolean bl;
        block8: {
            block7: {
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    bl = ShieldTweaksModule.on();
                    if (bl2) break block6;
                    if (!bl) break block7;
                    bl = ShieldTweaksModule.instance.fixAnimation.getValue();
                }
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[19];
                        var11_1 = 0;
                        var10_2 = "$xb\u0001\u0005t<\u0018`j\u0007\u0000de\u00054\u007fg\u000b\u001b\b3yx\u0005\u000b|y\u0013\u00105|d\u0007\u0002yr\u00100o\r\u001a`p\u0016i\u0006$dj\u0010\u001cc\u00161ysD\u000b|s\u0014{b\n\u000e0}\u0019yf\u0005\u001dys\u0019,$xb\u0001\u0005t<$dj\u0010\u001cc<\\0X\f\u0000up\u00130M\r\u0011uoWrrD>qp\u001ccrDA]U#9\r$xb\u0001\u0005t<#gn\u0005\u0002c\u0006#gn\u0005\u0002c\u00124\u007fg\u000b\u001b0k\u001fueD\fhl\u0018cn\u0000\u0006$dj\u0010\u001cc\n6bf\u000b\u001b0h\u001e~\u007f\u00078`j\u0007\u0000de\u000f8geD\u001axu\u0012|oD\u0006~p\u000e\b5|d\u0007\u0002yr\u0010\u0005%uj\u0000\u0010\u00054\u007fg\u000b\u001b";
                        var12_3 = "$xb\u0001\u0005t<\u0018`j\u0007\u0000de\u00054\u007fg\u000b\u001b\b3yx\u0005\u000b|y\u0013\u00105|d\u0007\u0002yr\u00100o\r\u001a`p\u0016i\u0006$dj\u0010\u001cc\u00161ysD\u000b|s\u0014{b\n\u000e0}\u0019yf\u0005\u001dys\u0019,$xb\u0001\u0005t<$dj\u0010\u001cc<\\0X\f\u0000up\u00130M\r\u0011uoWrrD>qp\u001ccrDA]U#9\r$xb\u0001\u0005t<#gn\u0005\u0002c\u0006#gn\u0005\u0002c\u00124\u007fg\u000b\u001b0k\u001fueD\fhl\u0018cn\u0000\u0006$dj\u0010\u001cc\n6bf\u000b\u001b0h\u001e~\u007f\u00078`j\u0007\u0000de\u000f8geD\u001axu\u0012|oD\u0006~p\u000e\b5|d\u0007\u0002yr\u0010\u0005%uj\u0000\u0010\u00054\u007fg\u000b\u001b".length();
                        var9_4 = 14;
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
                            var10_2 = "$xb\u0001\u0005t<\u0004dj\u0010\f0}\u0019t+\u0007\u0006|s\u0005c\u00078`j\u0007\u0000de";
                            var12_3 = "$xb\u0001\u0005t<\u0004dj\u0010\f0}\u0019t+\u0007\u0006|s\u0005c\u00078`j\u0007\u0000de".length();
                            var9_4 = 23;
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
                                v10 = 119;
                                break;
                            }
                            case 1: {
                                v10 = 16;
                                break;
                            }
                            case 2: {
                                v10 = 11;
                                break;
                            }
                            case 3: {
                                v10 = 100;
                                break;
                            }
                            case 4: {
                                v10 = 105;
                                break;
                            }
                            case 5: {
                                v10 = 16;
                                break;
                            }
                            default: {
                                v10 = 28;
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
                ShieldTweaksModule.b = var13;
                ShieldTweaksModule.CREDIT = ShieldTweaksModule.b[6];
                var0_7 = 2736912800541601349L;
                var6_8 = new long[6];
                var3_9 = 0;
                var4_10 = ")\u00eel\u0013B^\u00ff^\u0019\u001b\u00a5d\u0083\u00d5\u001c\u00fc\u00cch\u00a9\u00fa2\u00d2\u00e8\u00b6=\u009b\u0090)y\u00d2Xp";
                var5_11 = ")\u00eel\u0013B^\u00ff^\u0019\u001b\u00a5d\u0083\u00d5\u001c\u00fc\u00cch\u00a9\u00fa2\u00d2\u00e8\u00b6=\u009b\u0090)y\u00d2Xp".length();
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
lbl91:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "\\\u00e9\u0005\u00ae\u00ea\u008f\u0096]jc\u00b1\u00a0\u00c5\u00b4\u00e7\u008b";
                    var5_11 = "\\\u00e9\u0005\u00ae\u00ea\u008f\u0096]jc\u00b1\u00a0\u00c5\u00b4\u00e7\u008b".length();
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
lbl104:

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
lbl115:

                continue;
            }
        }
        ShieldTweaksModule.d = var6_8;
        ShieldTweaksModule.e = new Integer[6];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5C19;
        if (e[n2] == null) {
            ShieldTweaksModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
