package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.duck.BlockingState;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import java.lang.invoke.LambdaMetafactory;
import java.util.function.Supplier;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;

public final class TransparentPlayersModule
extends Module {
    private static TransparentPlayersModule instance;
    private final NumberSetting opacity;
    private final NumberSetting armorOpacity;
    private final BooleanSetting self;
    private final BooleanSetting onlyDamaged;
    private static final ThreadLocal<Boolean> FADING;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public TransparentPlayersModule() {
        String[] stringArray = b;
        super(stringArray[1], stringArray[3], Category.RENDER, false);
        this.opacity = new NumberSetting(stringArray[4], 5.0, 100.0, 55.0, 5.0);
        this.armorOpacity = new NumberSetting(stringArray[5], 5.0, 100.0, 100.0, 5.0);
        this.self = new BooleanSetting(stringArray[2], false);
        this.onlyDamaged = new BooleanSetting(stringArray[0], false);
        instance = this;
        this.addSettings(this.opacity, this.armorOpacity, this.self, this.onlyDamaged);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean applies(Object object) {
        boolean bl;
        BlockingState blockingState;
        boolean bl2;
        block43: {
            TransparentPlayersModule transparentPlayersModule;
            block41: {
                TransparentPlayersModule transparentPlayersModule2;
                block42: {
                    Object object2;
                    block39: {
                        block40: {
                            Object object3;
                            block37: {
                                block38: {
                                    block36: {
                                        transparentPlayersModule2 = instance;
                                        bl2 = AmbienceModule.b;
                                        object3 = transparentPlayersModule2;
                                        if (bl2) break block36;
                                        if (object3 == null) return false;
                                        object3 = transparentPlayersModule2;
                                    }
                                    if (bl2) break block37;
                                    if (((Module)object3).isEnabled()) break block38;
                                    return false;
                                }
                                object3 = object;
                            }
                            boolean bl3 = object3 instanceof PlayerEntityRenderState;
                            if (bl2) return bl3;
                            if (!bl3) return false;
                            PlayerEntityRenderState playerEntityRenderState = (PlayerEntityRenderState)object;
                            if (bl2) {
                                return false;
                            }
                            object2 = playerEntityRenderState;
                            if (bl2) break block39;
                            if (!object2.field_53333) break block40;
                            return false;
                        }
                        object2 = object;
                    }
                    boolean bl4 = object2 instanceof BlockingState;
                    if (bl2) return bl4;
                    if (!bl4) return true;
                    blockingState = (BlockingState)object;
                    if (bl2) {
                        return true;
                    }
                    transparentPlayersModule = transparentPlayersModule2;
                    if (bl2) break block41;
                    if (transparentPlayersModule.self.getValue()) break block42;
                    bl = blockingState.divine$isLocalPlayer();
                    if (bl2) break block43;
                    if (!bl) break block42;
                    return false;
                }
                transparentPlayersModule = transparentPlayersModule2;
            }
            bl = transparentPlayersModule.onlyDamaged.getValue();
        }
        if (bl2) return bl;
        if (!bl) return true;
        bl = blockingState.divine$isHurt();
        if (bl2) return bl;
        if (bl) return true;
        return false;
    }

    public static int alpha() {
        TransparentPlayersModule transparentPlayersModule;
        block4: {
            TransparentPlayersModule transparentPlayersModule2;
            block5: {
                transparentPlayersModule2 = instance;
                boolean bl = AmbienceModule.b;
                transparentPlayersModule = transparentPlayersModule2;
                if (bl) break block4;
                if (transparentPlayersModule != null) break block5;
                return 255;
            }
            transparentPlayersModule = transparentPlayersModule2;
        }
        return TransparentPlayersModule.toAlpha(transparentPlayersModule.opacity.getValue());
    }

    public static int armorAlpha() {
        TransparentPlayersModule transparentPlayersModule;
        block4: {
            TransparentPlayersModule transparentPlayersModule2;
            block5: {
                transparentPlayersModule2 = instance;
                boolean bl = AmbienceModule.b;
                transparentPlayersModule = transparentPlayersModule2;
                if (bl) break block4;
                if (transparentPlayersModule != null) break block5;
                return 255;
            }
            transparentPlayersModule = transparentPlayersModule2;
        }
        return TransparentPlayersModule.toAlpha(transparentPlayersModule.armorOpacity.getValue());
    }

    public static void setFading(boolean bl) {
        FADING.set(bl);
    }

    public static boolean isFading() {
        return FADING.get();
    }

    public static boolean fadesArmor() {
        boolean bl;
        block16: {
            block13: {
                Object object;
                boolean bl2;
                block15: {
                    block14: {
                        TransparentPlayersModule transparentPlayersModule;
                        block12: {
                            transparentPlayersModule = instance;
                            bl2 = AmbienceModule.b;
                            object = transparentPlayersModule;
                            if (bl2) break block12;
                            if (object == null) break block13;
                            object = transparentPlayersModule;
                        }
                        if (bl2) break block14;
                        if (!((Module)object).isEnabled()) break block13;
                        object = transparentPlayersModule;
                    }
                    if (bl2) break block15;
                    if (!(((TransparentPlayersModule)object).armorOpacity.getValue() < 100.0)) break block13;
                    object = FADING.get();
                }
                bl = (Boolean)object;
                if (bl2) break block16;
                if (!bl) break block13;
                bl = true;
                break block16;
            }
            bl = false;
        }
        return bl;
    }

    private static int toAlpha(double d) {
        return Math.max(1, Math.min(255, (int)(d / 100.0 * 255.0)));
    }

    public static int withAlpha(int n) {
        return TransparentPlayersModule.alpha() << 24 | n & TransparentPlayersModule.a(2298, 5337785962167890318L);
    }

    public static int withArmorAlpha(int n) {
        return TransparentPlayersModule.armorAlpha() << 24 | n & TransparentPlayersModule.a(25460, 6905797855992486401L);
    }

    private static Boolean lambda$static$0() {
        return Boolean.FALSE;
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[6];
                        var11_1 = 0;
                        var10_2 = "^&}\u0014\u0002\\\u0002t&1\tCF\u000bv-u\u0013E:p\u0003Q[\u000bc-\u007f\u0019\u0002{\u0006p1t\u001fQ\u0004B-}\u000b\u0019B-t@VC\u0018~=v\u0005\u0002[\u0006p1t\u001f\u0002F\u0005u-}\u001e";
                        var12_3 = "^&}\u0014\u0002\\\u0002t&1\tCF\u000bv-u\u0013E:p\u0003Q[\u000bc-\u007f\u0019\u0002{\u0006p1t\u001fQ\u0004B-}\u000b\u0019B-t@VC\u0018~=v\u0005\u0002[\u0006p1t\u001f\u0002F\u0005u-}\u001e".length();
                        var9_4 = 17;
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
                            var10_2 = "S'u\u0014\u0002D\u001ap+x\u0019[\rP:|\u0002P\u000b\u0005a)r\u0004VR";
                            var12_3 = "S'u\u0014\u0002D\u001ap+x\u0019[\rP:|\u0002P\u000b\u0005a)r\u0004VR".length();
                            var9_4 = 12;
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
                                v10 = 17;
                                break;
                            }
                            case 1: {
                                v10 = 72;
                                break;
                            }
                            case 2: {
                                v10 = 17;
                                break;
                            }
                            case 3: {
                                v10 = 109;
                                break;
                            }
                            case 4: {
                                v10 = 34;
                                break;
                            }
                            case 5: {
                                v10 = 43;
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
                TransparentPlayersModule.b = var13;
                var0_7 = 6868316972859099406L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "i>\u00f1M]\u00e4l\u00f0\u00b1\u00c2\u0091\u001f\u00de\u00e7\u0007\u007f";
                var5_11 = "i>\u00f1M]\u00e4l\u00f0\u00b1\u00c2\u0091\u001f\u00de\u00e7\u0007\u007f".length();
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
        TransparentPlayersModule.d = var6_8;
        TransparentPlayersModule.e = new Integer[2];
        TransparentPlayersModule.FADING = ThreadLocal.withInitial((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$static$0(), ()Ljava/lang/Boolean;)());
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7975;
        if (e[n2] == null) {
            TransparentPlayersModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
