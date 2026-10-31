package dev.quelchi.aurora.shield;

import dev.quelchi.aurora.shield.ShieldStates;
import java.io.InputStream;
import java.lang.invoke.LambdaMetafactory;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;

public final class GreyscaleTextures {
    private static final Map<Identifier, Identifier> cache;
    private static final String a;

    private GreyscaleTextures() {
    }

    public static Identifier get(Identifier identifier) {
        Identifier identifier2;
        block4: {
            block5: {
                boolean bl = ShieldStates.a;
                identifier2 = identifier;
                if (bl) break block4;
                if (identifier2 != null) break block5;
                return null;
            }
            identifier2 = cache.computeIfAbsent(identifier, GreyscaleTextures::convert);
        }
        return identifier2;
    }

    
    private static Identifier convert(Identifier var0) {
        block20: {
            block19: {
                block18: {
                    block22: {
                        var2_1 = GreyscaleTextures.read(var0);
                        var1_2 = ShieldStates.a;
                        v0 = var2_1;
                        if (var1_2) // goto
                        if (v0 != null) break block18;
                        break block22;
                        catch (Throwable v1) {
                            throw v1;
                        }
                    }
                    return var0;
                }
                v0 = var2_1;
lbl18:

                var3_3 = v0;
                try {
                    var4_5 = new NativeImage(var2_1.method_4307(), var2_1.method_4323(), false);
                    var5_7 = 0;
                    block15: while (true) {
                        v3 = var5_7;
                        block16: while (v3 < var2_1.method_4323()) {
                            for (var6_10 = 0; var6_10 < var2_1.method_4307(); ++var6_10) {
                                var7_12 = var2_1.method_61940(var6_10, var5_7);
                                v3 = var7_12 >>> 24;
                                if (var1_2) continue block16;
                                var8_13 = v3 & 255;
                                var9_14 = var7_12 >>> 16 & 255;
                                var10_15 = var7_12 >>> 8 & 255;
                                var11_16 = var7_12 & 255;
                                var12_17 = (int)((float)var9_14 * 0.299f + (float)var10_15 * 0.587f + (float)var11_16 * 0.114f);
                                var4_5.method_61941(var6_10, var5_7, var8_13 << 24 | var12_17 << 16 | var12_17 << 8 | var12_17);
                                if (!var1_2) continue;
                            }
                            ++var5_7;
                            if (!var1_2) continue block15;
                        }
                        break;
                    }
                    var5_8 = Identifier.method_60655((String)var0.method_12836(), (String)(GreyscaleTextures.a + var0.method_12832()));
                    MinecraftClient.method_1551().method_1531().method_4616(var5_8, (AbstractTexture)new NativeImageBackedTexture((Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, toString(), ()Ljava/lang/String;)((Identifier)var5_8), var4_5));
                    var6_11 = var5_8;
                }
                catch (Throwable var4_6) {
                    try {
                        block21: {
                            if (var1_2 || var3_3 == null) break block21;
                            try {
                                var3_3.close();
                            }
                            catch (Throwable var5_9) {
                                var4_6.addSuppressed(var5_9);
                            }
                        }
                        throw var4_6;
                    }
                    catch (Throwable var3_4) {
                        return var0;
                    }
                }
                v5 = var3_3;
                if (var1_2) break block19;
                if (v5 == null) break block20;
                v5 = var3_3;
            }
            v5.close();
        }
        return var6_11;
    }

    
    private static NativeImage read(Identifier var0) {
        block27: {
            block29: {
                block28: {
                    block26: {
                        block32: {
                            block25: {
                                block24: {
                                    block31: {
                                        var2_1 = MinecraftClient.method_1551();
                                        var3_2 = var2_1.method_1531();
                                        var5_3 = var3_2.method_4619(var0);
                                        var1_5 = ShieldStates.a;
                                        v0 = var5_3;
                                        if (var1_5) break block24;
                                        if (!(v0 instanceof NativeImageBackedTexture)) break block25;
                                        break block31;
                                        catch (Throwable v1) {
                                            throw v1;
                                        }
                                    }
                                    v0 = var5_3;
                                }
                                var4_6 = (NativeImageBackedTexture)v0;
                                v3 = var4_6;
                                if (!var1_5) {
                                    if (v3.method_4525() == null) break block25;
                                }
                                try {
                                    v3 = var4_6;
lbl30:

                                    var5_3 = v3.method_4525();
                                    var6_8 = new NativeImage(var5_3.method_4307(), var5_3.method_4323(), false);
                                    var6_8.method_4317((NativeImage)var5_3);
                                    return var6_8;
                                }
                                catch (Throwable var5_4) {
                                }
                            }
                            var4_6 = var2_1.method_1478().method_14486(var0);
                            v5 /* !! */  = var4_6;
                            if (var1_5) break block26;
                            if (!v5 /* !! */ .isPresent()) break block27;
                            break block32;
                            catch (Throwable v6) {
                                throw v6;
                            }
                        }
                        v5 /* !! */  = var4_6.get();
                    }
                    var5_3 = ((Resource)v5 /* !! */ ).method_14482();
                    var6_9 = NativeImage.method_4309((InputStream)var5_3);
                    v8 = var5_3;
                    if (var1_5) break block28;
                    if (v8 == null) break block29;
                    v8 = var5_3;
                }
                v8.close();
            }
            return var6_9;
            catch (Throwable var6_10) {
                try {
                    block30: {
                        if (var1_5 || var5_3 == null) break block30;
                        try {
                            var5_3.close();
                        }
                        catch (Throwable var7_11) {
                            var6_10.addSuppressed(var7_11);
                        }
                    }
                    throw var6_10;
                }
                catch (Throwable var4_7) {
                }
            }
        }
        return null;
    }

    public static void invalidate() {
        cache.clear();
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "#dLXj[$%c[NkY\u001a.t\u0011".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 66;
                    case 1 -> 17;
                    case 2 -> 62;
                    case 3 -> 55;
                    case 4 -> 24;
                    case 5 -> 58;
                    default -> 123;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
        cache = new HashMap<Identifier, Identifier>();
    }
}
