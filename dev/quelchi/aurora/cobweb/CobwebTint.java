package dev.quelchi.aurora.cobweb;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.quelchi.aurora.mixins.SpriteAccessor;
import dev.quelchi.aurora.mixins.SpriteContentsAccessor;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.CobwebModule;
import java.lang.invoke.LambdaMetafactory;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.texture.SpriteContents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CobwebTint {
    private static final Logger LOG;
    private static final Map<Identifier, NativeImage[]> PRISTINE;
    private static final Map<Identifier, SpriteContents> TRACKED;
    public static boolean a;
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;

    private CobwebTint() {
    }

    public static void reset() {
        block8: {
            Iterator<NativeImage[]> iterator = PRISTINE.values().iterator();
            boolean bl = a;
            block6: while (iterator.hasNext()) {
                NativeImage[] nativeImageArray = iterator.next();
                if (bl) break block8;
                NativeImage[] nativeImageArray2 = nativeImageArray;
                int n = nativeImageArray2.length;
                int n2 = 0;
                while (n2 < n) {
                    block9: {
                        block10: {
                            NativeImage nativeImage = nativeImageArray2[n2];
                            if (bl) continue block6;
                            if (bl) break block9;
                            if (nativeImage == null) break block10;
                            try {
                                nativeImage.close();
                            }
                            catch (Exception exception) {
                            }
                        }
                        ++n2;
                    }
                    if (!bl) continue;
                    boolean bl2 = Module.a = !Module.a;
                }
                if (!bl) continue;
            }
            PRISTINE.clear();
            TRACKED.clear();
        }
    }

    public static void bake(SpriteContents spriteContents) {
        block21: {
            CobwebModule cobwebModule;
            block20: {
                boolean bl;
                block19: {
                    NativeImage[] nativeImageArray;
                    NativeImage[] nativeImageArray2;
                    block17: {
                        Identifier identifier;
                        NativeImage[] nativeImageArray3;
                        block18: {
                            block15: {
                                block16: {
                                    nativeImageArray3 = CobwebTint.levelsOf(spriteContents);
                                    bl = a;
                                    if (nativeImageArray3 == null) {
                                        return;
                                    }
                                    identifier = spriteContents.method_45816();
                                    nativeImageArray2 = PRISTINE.get(identifier);
                                    if (bl) break block15;
                                    if (nativeImageArray2 == null) break block16;
                                    nativeImageArray = nativeImageArray2;
                                    if (bl) break block17;
                                    if (nativeImageArray.length == nativeImageArray3.length) break block18;
                                }
                                CobwebTint.closeAll(nativeImageArray2);
                                nativeImageArray2 = new NativeImage[nativeImageArray3.length];
                            }
                            for (int i = 0; i < nativeImageArray3.length; ++i) {
                                nativeImageArray2[i] = CobwebTint.copyOf(nativeImageArray3[i]);
                                if (!bl) {
                                    if (!bl) continue;
                                    break;
                                }
                                break block19;
                            }
                            PRISTINE.put(identifier, nativeImageArray2);
                        }
                        TRACKED.put(identifier, spriteContents);
                        nativeImageArray = nativeImageArray3;
                    }
                    CobwebTint.applyTo(nativeImageArray, nativeImageArray2);
                }
                CobwebModule cobwebModule2 = CobwebModule.get();
                cobwebModule = cobwebModule2;
                if (bl) break block20;
                if (cobwebModule == null) break block21;
                cobwebModule = cobwebModule2;
            }
            cobwebModule.markBaked();
        }
    }

    
    public static void unbake(SpriteContents var0) {
        block19: {
            block21: {
                block18: {
                    block20: {
                        block17: {
                            block16: {
                                var2_1 = CobwebTint.levelsOf(var0);
                                var3_2 = CobwebTint.PRISTINE.get(var0.method_45816());
                                var1_3 = CobwebTint.a;
                                v0 = var2_1;
                                if (var1_3) break block16;
                                if (v0 != null) {
                                }
                                v0 = var3_2;
                            }
                            if (var1_3) break block17;
                            if (v0 != null) {
                            }
                            v0 = var2_1;
                        }
                        if (var1_3) break block18;
                        if (v0.length == 0) // goto
                        break block20;
                        catch (Exception v3) {
                            throw v3;
                        }
                    }
                    v0 = var3_2;
                }
                if (var1_3) // goto
                if (v0.length != 0) break block19;
                break block21;
                catch (Exception v5) {
                    throw v5;
                }
            }
            return;
        }
        try {
            v0 = var2_1;
lbl50:

            v0[0].method_4317(var3_2[0]);
        }
        catch (Exception var4_4) {
            CobwebTint.LOG.warn(CobwebTint.b[3], (Object)var0.method_45816(), (Object)var4_4);
        }
    }

    
    public static boolean retint() {
        block55: {
            block49: {
                block56: {
                    block48: {
                        block47: {
                            block46: {
                                block60: {
                                    block45: {
                                        block58: {
                                            block43: {
                                                block44: {
                                                    block59: {
                                                        block41: {
                                                            block42: {
                                                                block57: {
                                                                    var0 = CobwebTint.a;
                                                                    v0 = CobwebTint.TRACKED.isEmpty();
                                                                    if (var0) break block41;
                                                                    if (!v0) break block42;
                                                                    break block57;
                                                                    catch (Exception v1) {
                                                                        throw v1;
                                                                    }
                                                                }
                                                                return false;
                                                            }
                                                            v0 = RenderSystem.isOnRenderThread();
                                                        }
                                                        if (v0) break block58;
                                                        var1_1 = MinecraftClient.method_1551();
                                                        if (var0) break block43;
                                                        if (var1_1 != null) break block44;
                                                        break block59;
                                                        catch (Exception v3) {
                                                            throw v3;
                                                        }
                                                    }
                                                    return false;
                                                }
                                                var1_1.execute((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, retint(), ()V)());
                                            }
                                            return true;
                                        }
                                        var1_2 = MinecraftClient.method_1551();
                                        v5 = var1_2;
                                        if (var0) break block45;
                                        if (v5 != null) {
                                        }
                                        v5 = var1_2;
                                    }
                                    if (var0) // goto
                                    if (v5.method_72703() != null) break block46;
                                    break block60;
                                    catch (Exception v7) {
                                        throw v7;
                                    }
                                }
                                return false;
                            }
                            try {
                                v5 = var1_2;
lbl60:

                                var9_3 = CobwebTint.b;
                                var2_4 = v5.method_72703().method_73025(Identifier.method_60656((String)var9_3[2]));
                            }
                            catch (Exception var3_5) {
                                CobwebTint.LOG.warn(CobwebTint.b[4], (Throwable)var3_5);
                                return false;
                            }
                            v9 = var2_4;
                            if (var0) break block47;
                            if (v9 != null) {
                            }
                            v9 = var2_4;
                        }
                        if (v9.method_68004() != null) break block48;
lbl78:

                        return false;
                    }
                    var3_6 = false;
                    for (Map.Entry<Identifier, SpriteContents> var5_8 : CobwebTint.TRACKED.entrySet()) {
                        block54: {
                            block53: {
                                block52: {
                                    block51: {
                                        block50: {
                                            if (var0) break block49;
                                            var6_9 = CobwebTint.levelsOf(var5_8.getValue());
                                            var7_10 = CobwebTint.PRISTINE.get(var5_8.getKey());
                                            v12 = var6_9;
                                            if (var0) break block50;
                                            if (v12 == null) continue;
                                            v12 = var7_10;
                                        }
                                        if (var0) break block51;
                                        if (v12 == null) {
                                            continue;
                                        }
                                        v12 = var6_9;
                                    }
                                    CobwebTint.applyTo(v12, var7_10);
                                    var8_11 = var2_4.method_4608(var5_8.getKey());
                                    v15 = var8_11;
                                    if (var0) break block52;
                                    if (v15 == null) continue;
                                    v15 = var5_8.getKey();
                                }
                                v17 = ((Identifier)v15).equals((Object)var8_11.method_45851().method_45816());
                                if (var0) break block53;
                                if (!v17) {
                                    continue;
                                }
                                v17 = CobwebTint.upload(var2_4, var8_11, var6_9);
                            }
                            if (var0 || !v17) break block54;
                            v17 = var3_6 = true;
                        }
                        if (!var0) continue;
                    }
                    v20 = var3_6;
                    if (var0) break block55;
                    if (!v20) break block49;
                    var4_7 = CobwebModule.get();
                    v22 = var4_7;
                    if (var0) break block56;
                    if (v22 == null) break block49;
                    v22 = var4_7;
                }
                v22.markBaked();
            }
            v20 = var3_6;
        }
        return v20;
    }

    
    private static boolean upload(SpriteAtlasTexture spriteAtlasTexture, Sprite sprite, NativeImage[] nativeImageArray) {
        boolean bl = a;
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            int n = ((SpriteAccessor)sprite).aurora$padding();
            int n2 = 0;
            do {
                block15: {
                    NativeImage nativeImage;
                    NativeImage nativeImage2;
                    if (n2 >= nativeImageArray.length) return 1 != 0;
                    NativeImage nativeImage3 = nativeImage2 = nativeImageArray[n2];
                    if (!bl) {
                        if (nativeImage3 == null) break;
                        nativeImage3 = nativeImage2;
                    }
                    int n3 = nativeImage3.method_4307();
                    if (bl) return n3 != 0;
                    if (n3 <= 0) return 1 != 0;
                    NativeImage nativeImage4 = nativeImage2;
                    if (!bl) {
                        if (nativeImage4.method_4323() <= 0) break;
                        nativeImage4 = nativeImage = CobwebTint.padded(nativeImage2, n >> n2);
                    }
                    if (bl) continue;
                    try {
                        commandEncoder.writeToTexture(spriteAtlasTexture.method_68004(), nativeImage, n2, 0, sprite.method_35806() >> n2, sprite.method_35807() >> n2, nativeImage.method_4307(), nativeImage.method_4323(), 0, 0);
                        if (nativeImage == null) break block15;
                    }
                    catch (Throwable throwable) {
                        if (bl) throw throwable;
                        if (nativeImage == null) throw throwable;
                        try {
                            nativeImage.close();
                            throw throwable;
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                    nativeImage.close();
                }
                ++n2;
            } while (!bl);
            return 1 != 0;
        }
        catch (Exception exception) {
            LOG.warn(b[1], (Throwable)exception);
            return false;
        }
    }

    private static NativeImage padded(NativeImage nativeImage, int n) {
        int n2;
        boolean bl;
        NativeImage nativeImage2;
        int n3;
        int n4;
        block7: {
            block8: {
                n4 = nativeImage.method_4307();
                n3 = nativeImage.method_4323();
                nativeImage2 = new NativeImage(nativeImage.method_4318(), n4 + 2 * n, n3 + 2 * n, false);
                bl = a;
                n2 = n;
                if (bl) break block7;
                if (n2 != 0) break block8;
                nativeImage2.method_4317(nativeImage);
                return nativeImage2;
            }
            n2 = 0;
        }
        int n5 = n2;
        block4: while (true) {
            int n6 = n5;
            block5: while (n6 < n3 + 2 * n) {
                int n7 = Math.clamp((long)(n5 - n), 0, n3 - 1);
                for (int i = 0; i < n4 + 2 * n; ++i) {
                    n6 = i - n;
                    if (bl) continue block5;
                    int n8 = Math.clamp((long)n6, 0, n4 - 1);
                    nativeImage2.method_61941(i, n5, nativeImage.method_61940(n8, n7));
                    if (!bl) continue;
                }
                ++n5;
                if (!bl) continue block4;
            }
            break;
        }
        return nativeImage2;
    }

    
    private static void applyTo(NativeImage[] var0, NativeImage[] var1_1) {
        block51: {
            block52: {
                block64: {
                    block50: {
                        block62: {
                            block49: {
                                block63: {
                                    block48: {
                                        block47: {
                                            block60: {
                                                block46: {
                                                    block61: {
                                                        block45: {
                                                            var3_2 = CobwebModule.get();
                                                            var2_3 = CobwebTint.a;
                                                            v0 = var3_2;
                                                            if (var2_3) break block45;
                                                            if (v0 != null) {
                                                            }
                                                            v0 = var3_2;
                                                        }
                                                        if (var2_3) break block60;
                                                        if (v0.isEnabled()) break block46;
                                                        break block61;
                                                        catch (Exception v2) {
                                                            throw v2;
                                                        }
                                                    }
                                                    v3 = 1.0;
                                                    break block47;
                                                }
                                                v0 = var3_2;
                                            }
                                            v3 = v0.getAlpha();
                                        }
                                        var4_4 = v3;
                                        v5 = var3_2;
                                        if (var2_3) break block48;
                                        if (v5 != null) {
                                        }
                                        v5 = var3_2;
                                    }
                                    if (var2_3) break block62;
                                    if (v5.isEnabled()) break block49;
                                    break block63;
                                    catch (Exception v7) {
                                        throw v7;
                                    }
                                }
                                v8 = CobwebTint.a(30679, 7095296216736909845L);
                                break block50;
                            }
                            v5 = var3_2;
                        }
                        v8 = v5.getTintRgb();
                    }
                    var6_5 = v8;
                    v10 = var6_5;
                    if (var2_3) break block51;
                    if (v10 == CobwebTint.a(15256, 3273581795470575195L)) break block52;
                    break block64;
                    catch (Exception v11) {
                        throw v11;
                    }
                }
                v10 = 1;
                break block51;
            }
            v10 = 0;
        }
        var7_6 = v10;
        var8_7 = var6_5 >> 16 & 255;
        var9_8 = var6_5 >> 8 & 255;
        var10_9 = var6_5 & 255;
        var11_10 = Math.min(var0.length, var1_1.length);
        var12_11 = 0;
        while (var12_11 < var11_10) {
            block53: {
                block54: {
                    block56: {
                        block67: {
                            block66: {
                                block55: {
                                    block65: {
                                        var13_12 = var0[var12_11];
                                        var14_13 = var1_1[var12_11];
                                        if (var2_3) break block53;
                                        if (var13_12 == null) break block54;
                                        break block65;
                                        catch (Exception v13) {
                                            throw v13;
                                        }
                                    }
                                    if (var14_13 != null) break block55;
                                    break block54;
                                    catch (Exception v14) {
                                        throw v14;
                                    }
                                }
                                cfr_temp_0 = var4_4 - 0.999;
                                v16 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 > 0.0 ? 1 : -1);
                                if (var2_3) // goto
                                if (v16 < 0) break block56;
                                break block66;
                                catch (Exception v17) {
                                    throw v17;
                                }
                            }
                            v16 = var7_6;
                            if (var2_3) // goto
                            break block67;
                            catch (Exception v18) {
                                throw v18;
                            }
                        }
                        if (v16 != false) break block56;
                        var13_12.method_4317(var14_13);
                        if (!var2_3) break block54;
                    }
                    try {
                        v16 = Math.min(var13_12.method_4307(), var14_13.method_4307());
lbl127:

                        var15_14 = v16;
                        var16_16 = Math.min(var13_12.method_4323(), var14_13.method_4323());
                        var17_17 = 0;
                        block40: while (true) {
                            v20 = var17_17;
                            block41: while (v20 < var16_16) {
                                if (var2_3) break block53;
                                for (var18_18 = 0; var18_18 < var15_14; ++var18_18) {
                                    block59: {
                                        block58: {
                                            block57: {
                                                block69: {
                                                    block68: {
                                                        v20 = var14_13.method_61940(var18_18, var17_17);
                                                        if (var2_3) continue block41;
                                                        var19_19 = v20;
                                                        var20_20 = var19_19 >>> 24;
                                                        v21 = var20_20;
                                                        if (var2_3) break block57;
                                                        if (v21 != 0) // goto
                                                        break block68;
                                                        catch (Exception v22) {
                                                            throw v22;
                                                        }
                                                    }
                                                    var13_12.method_61941(var18_18, var17_17, var19_19);
                                                    if (!var2_3) continue;
                                                    break block69;
                                                    catch (Exception v23) {
                                                        throw v23;
                                                    }
                                                }
                                                v21 = var19_19 >> 16 & 255;
                                            }
                                            var21_21 = v21;
                                            var22_22 = var19_19 >> 8 & 255;
                                            var23_23 = var19_19 & 255;
                                            if (var2_3) break block58;
                                            if (var7_6 == 0) break block59;
                                            var21_21 = var21_21 * var8_7 / 255;
                                            var22_22 = var22_22 * var9_8 / 255;
                                        }
                                        var23_23 = var23_23 * var10_9 / 255;
                                    }
                                    var13_12.method_61941(var18_18, var17_17, (int)((double)var20_20 * var4_4) << 24 | var21_21 << 16 | var22_22 << 8 | var23_23);
                                    if (!var2_3) continue;
                                }
                                ++var17_17;
                                if (!var2_3) continue block40;
                            }
                            break block54;
                            break;
                        }
                        {
                            break block54;
                            break;
                        }
                    }
                    catch (Exception var15_15) {
                        CobwebTint.LOG.warn(CobwebTint.b[0], (Object)var12_11, (Object)var15_15);
                    }
                }
                ++var12_11;
            }
            if (!var2_3) continue;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static NativeImage[] levelsOf(SpriteContents spriteContents) {
        boolean bl = a;
        try {
            NativeImage[] nativeImageArray;
            NativeImage[] nativeImageArray2;
            block7: {
                nativeImageArray2 = ((SpriteContentsAccessor)spriteContents).aurora$levels();
                nativeImageArray = nativeImageArray2;
                if (bl) break block7;
                if (nativeImageArray == null) return null;
                nativeImageArray = nativeImageArray2;
            }
            if (nativeImageArray.length == 0) {
                return null;
            }
            NativeImage[] nativeImageArray3 = nativeImageArray2;
            return nativeImageArray3;
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    private static NativeImage copyOf(NativeImage nativeImage) {
        NativeImage nativeImage2 = new NativeImage(nativeImage.method_4318(), nativeImage.method_4307(), nativeImage.method_4323(), false);
        nativeImage2.method_4317(nativeImage);
        return nativeImage2;
    }

    private static void closeAll(NativeImage[] nativeImageArray) {
        NativeImage[] nativeImageArray2;
        boolean bl;
        block7: {
            block8: {
                bl = a;
                nativeImageArray2 = nativeImageArray;
                if (bl) break block7;
                if (nativeImageArray2 != null) break block8;
                return;
            }
            nativeImageArray2 = nativeImageArray;
        }
        NativeImage[] nativeImageArray3 = nativeImageArray2;
        int n = nativeImageArray3.length;
        int n2 = 0;
        while (n2 < n) {
            block9: {
                block10: {
                    NativeImage nativeImage = nativeImageArray3[n2];
                    if (bl) break block9;
                    if (nativeImage == null) break block10;
                    try {
                        nativeImage.close();
                    }
                    catch (Exception exception) {
                    }
                }
                ++n2;
            }
            if (!bl) continue;
        }
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[6];
                        var11_1 = 0;
                        var10_2 = "\u001d:ah!+\u0011\u001byme#\"\u0017f7a~v:\u001a(-.f38\u0016*yuwzn\u001f#8xc8)S/-.k%n\u001f)8jo2?\u001d:ah!+\u0011\u001bygd{>\u001f':k*7:\u001f'*.\u007f&\"\u001c'=.l7'\u001f#=\"*0/\u001f*0`mv,\u0012%2.~9n\u0012f+kf9/\u0017\u0006$5ai==,\u001d:ah!+\u0011\u001byme#\"\u0017f7a~v<\u00165-ax3n\b;ylo0!\u0001#ycc&#\u00126*";
                        var12_3 = "\u001d:ah!+\u0011\u001byme#\"\u0017f7a~v:\u001a(-.f38\u0016*yuwzn\u001f#8xc8)S/-.k%n\u001f)8jo2?\u001d:ah!+\u0011\u001bygd{>\u001f':k*7:\u001f'*.\u007f&\"\u001c'=.l7'\u001f#=\"*0/\u001f*0`mv,\u0012%2.~9n\u0012f+kf9/\u0017\u0006$5ai==,\u001d:ah!+\u0011\u001byme#\"\u0017f7a~v<\u00165-ax3n\b;ylo0!\u0001#ycc&#\u00126*".length();
                        var9_4 = 54;
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
                            var10_2 = "\u001d:ah!+\u0011\u001byme#\"\u0017f7a~v<\u001656b|3n\u0007.<.h:!\u0010-yo~:/\u0000jyhk:\"\u001a(>.h7-\u0018f-a*7n\u0001#5ak2\r\u0007,|e$/\\\u00056l}3,";
                            var12_3 = "\u001d:ah!+\u0011\u001byme#\"\u0017f7a~v<\u001656b|3n\u0007.<.h:!\u0010-yo~:/\u0000jyhk:\"\u001a(>.h7-\u0018f-a*7n\u0001#5ak2\r\u0007,|e$/\\\u00056l}3,".length();
                            var9_4 = 68;
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
                                v10 = 70;
                                break;
                            }
                            case 1: {
                                v10 = 89;
                                break;
                            }
                            case 2: {
                                v10 = 14;
                                break;
                            }
                            case 3: {
                                v10 = 10;
                                break;
                            }
                            case 4: {
                                v10 = 86;
                                break;
                            }
                            case 5: {
                                v10 = 78;
                                break;
                            }
                            default: {
                                v10 = 115;
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
                CobwebTint.b = var13;
                var0_7 = 3203008841044279415L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "tWi\u00b9\u0018\u00d8u\u009d\u00c7\u0088 \u008fh+9\u00d3";
                var5_11 = "tWi\u00b9\u0018\u00d8u\u009d\u00c7\u0088 \u008fh+9\u00d3".length();
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
        CobwebTint.c = var6_8;
        CobwebTint.d = new Integer[2];
        CobwebTint.LOG = LoggerFactory.getLogger((String)CobwebTint.b[5]);
        CobwebTint.PRISTINE = new ConcurrentHashMap<Identifier, NativeImage[]>();
        CobwebTint.TRACKED = new ConcurrentHashMap<Identifier, SpriteContents>();
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3DC2;
        if (d[n2] == null) {
            CobwebTint.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }
}
