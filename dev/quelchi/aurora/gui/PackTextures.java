package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PackPreview;
import dev.quelchi.aurora.gui.PackTextures$Sprite;
import dev.quelchi.aurora.gui.PackTextures$Stats;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.util.Daemon;
import java.io.InputStream;
import java.lang.invoke.LambdaMetafactory;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public final class PackTextures {
    private static String loadedName;
    private static String loadingName;
    private static Map<String, PackTextures$Sprite> sprites;
    private static PackTextures$Stats stats;
    private static String pendingName;
    private static long pendingSince;
    private static final long HOVER_MS;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private PackTextures() {
    }

    public static PackTextures$Sprite sprite(String string) {
        return sprites.get(string);
    }

    public static PackTextures$Stats stats() {
        return stats;
    }

    public static boolean ready(String string) {
        return string.equals(loadedName);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void want(String string, Supplier<ResourcePack> supplier) {
        int n;
        block13: {
            long l;
            block14: {
                String string2;
                String string3;
                int n2;
                block12: {
                    n2 = PreviewScene.a;
                    string3 = string;
                    string2 = loadedName;
                    if (n2 != 0) break block12;
                    if (string3.equals(string2)) return;
                    string3 = string;
                    string2 = loadingName;
                }
                if (string3.equals(string2)) {
                    return;
                }
                l = Util.method_658();
                n = string.equals(pendingName);
                if (n2 != 0) break block13;
                if (n != 0) break block14;
                pendingName = string;
                pendingSince = l;
                return;
            }
            long l2 = l - pendingSince - PackTextures.a(11539, 2504740066807236906L);
            n = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
        }
        if (n < 0) {
            return;
        }
        loadingName = string;
        Daemon.run(a[0], () -> PackTextures.lambda$want$0(string, supplier));
    }

    
    private static void load(String string, Supplier<ResourcePack> supplier) {
        ResourcePack resourcePack;
        PackTextures$Stats packTextures$Stats;
        LinkedHashMap<String, byte[]> linkedHashMap;
        block14: {
            linkedHashMap = new LinkedHashMap<String, byte[]>();
            packTextures$Stats = null;
            int n = PreviewScene.a;
            try {
                resourcePack = supplier.get();
                try {
                    ResourcePack resourcePack2;
                    block13: {
                        for (String string2 : PackPreview.wantedTextures()) {
                            resourcePack2 = resourcePack;
                            if (n == 0) {
                                byte[] byArray2;
                                byte[] byArray = byArray2 = PackTextures.readAll((InputSupplier<InputStream>)resourcePack2.method_14405(ResourceType.field_14188, PackTextures.texture(string2)));
                                if (n == 0 && byArray != null) {
                                    byArray = linkedHashMap.put(string2, byArray2);
                                }
                                if (n == 0) continue;
                            }
                            break block13;
                        }
                        resourcePack2 = resourcePack;
                    }
                    packTextures$Stats = PackTextures.measure(resourcePack2, linkedHashMap);
                    if (n != 0 || resourcePack == null) break block14;
                }
                catch (Throwable throwable) {
                    if (n != 0) throw throwable;
                    if (resourcePack == null) throw throwable;
                    try {
                        resourcePack.close();
                        throw throwable;
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
                resourcePack.close();
            }
            catch (Exception exception) {
            }
        }
        resourcePack = packTextures$Stats;
        MinecraftClient.method_1551().execute(() -> PackTextures.lambda$load$1(string, linkedHashMap, (PackTextures$Stats)resourcePack));
    }

    private static Identifier texture(String string) {
        String[] stringArray = a;
        return Identifier.method_60656((String)(stringArray[2] + string + stringArray[5]));
    }

    
    private static byte[] readAll(InputSupplier<InputStream> var0) {
        block17: {
            block16: {
                block15: {
                    block19: {
                        var1_1 = PreviewScene.a;
                        v0 /* !! */  = var0;
                        if (var1_1 != 0) // goto
                        if (v0 /* !! */  != null) break block15;
                        break block19;
                        catch (Throwable v1) {
                            throw v1;
                        }
                    }
                    return null;
                }
                v0 /* !! */  = var0.get();
lbl17:

                var2_2 = (InputStream)v0 /* !! */ ;
                try {
                    var3_4 = var2_2.readAllBytes();
                }
                catch (Throwable var3_5) {
                    try {
                        block18: {
                            if (var1_1 != 0 || var2_2 == null) break block18;
                            try {
                                var2_2.close();
                            }
                            catch (Throwable var4_6) {
                                var3_5.addSuppressed(var4_6);
                            }
                        }
                        throw var3_5;
                    }
                    catch (Exception var2_3) {
                        return null;
                    }
                }
                v4 = var2_2;
                if (var1_1 != 0) break block16;
                if (v4 == null) break block17;
                v4 = var2_2;
            }
            v4.close();
        }
        return var3_4;
    }

    
    private static PackTextures$Stats measure(ResourcePack var0, Map<String, byte[]> var1_1) {
        block11: {
            block9: {
                block8: {
                    var9_2 = PackTextures.a;
                    var3_3 = var1_1.get(var9_2[9]);
                    var2_4 = PreviewScene.a;
                    v0 = var3_3;
                    if (var2_4 != 0) break block8;
                    if (v0 == null) break block9;
                    v0 = var3_3;
                }
                v2 = PackTextures.pngWidth(v0);
                break block11;
            }
            v2 = 0;
        }
        var4_5 = v2;
        var5_6 = false;
        for (Map.Entry<String, byte[]> var7_8 : var1_1.entrySet()) {
            block10: {
                var9_2 = PackTextures.a;
                var8_9 = Identifier.method_60656((String)(var9_2[1] + var7_8.getKey() + var9_2[10]));
                if (var2_4 != 0) break block10;
                if (var0.method_14405(ResourceType.field_14188, var8_9) != null) {
                }
                var5_6 = true;
            }
            if (var2_4 == 0) break;
lbl34:

            if (var2_4 == 0) continue;
            break;
        }
        var6_7 = new boolean[]{false, false};
        var9_2 = PackTextures.a;
        var0.method_14408(ResourceType.field_14188, var9_2[6], var9_2[8], (ResourcePack.ResultConsumer)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, lambda$measure$2(boolean[] net.minecraft.util.Identifier net.minecraft.resource.InputSupplier ), (Lnet/minecraft/util/Identifier;Lnet/minecraft/resource/InputSupplier;)V)((boolean[])var6_7));
        var0.method_14408(ResourceType.field_14188, var9_2[11], var9_2[4], (ResourcePack.ResultConsumer)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, lambda$measure$3(boolean[] net.minecraft.util.Identifier net.minecraft.resource.InputSupplier ), (Lnet/minecraft/util/Identifier;Lnet/minecraft/resource/InputSupplier;)V)((boolean[])var6_7));
        return new PackTextures$Stats(var4_5, var5_6, (boolean)var6_7[0], (boolean)var6_7[1]);
    }

    private static int pngWidth(byte[] byArray) {
        int n;
        block4: {
            block5: {
                int n2 = PreviewScene.a;
                n = byArray.length;
                if (n2 != 0) break block4;
                if (n >= 24) break block5;
                return 0;
            }
            n = (byArray[16] & 0xFF) << 24 | (byArray[17] & 0xFF) << 16 | (byArray[18] & 0xFF) << 8 | byArray[19] & 0xFF;
        }
        return n;
    }

    private static void upload(String string, Map<String, byte[]> map, PackTextures$Stats packTextures$Stats) {
        block3: {
            PackTextures.free();
            HashMap<String, PackTextures$Sprite> hashMap = new HashMap<String, PackTextures$Sprite>();
            TextureManager textureManager = MinecraftClient.method_1551().method_1531();
            Iterator<Map.Entry<String, byte[]>> iterator = map.entrySet().iterator();
            int n = PreviewScene.a;
            while (iterator.hasNext()) {
                NativeImage nativeImage;
                Map.Entry<String, byte[]> entry = iterator.next();
                try {
                    nativeImage = NativeImage.method_49277((byte[])entry.getValue());
                    if (n != 0) break block3;
                }
                catch (Exception exception) {
                    continue;
                }
                String[] stringArray = a;
                Identifier identifier = Identifier.method_60655((String)stringArray[7], (String)(stringArray[3] + entry.getKey().replace('/', '_')));
                textureManager.method_4616(identifier, (AbstractTexture)new NativeImageBackedTexture(() -> PackTextures.lambda$upload$4(identifier), nativeImage));
                int n2 = Math.min(nativeImage.method_4307(), nativeImage.method_4323());
                hashMap.put(entry.getKey(), new PackTextures$Sprite(identifier, n2, n2, nativeImage.method_4307(), nativeImage.method_4323()));
                if (n == 0) continue;
            }
            sprites = hashMap;
            stats = packTextures$Stats;
            loadedName = string;
            loadingName = null;
        }
    }

    private static void free() {
        block4: {
            TextureManager textureManager = MinecraftClient.method_1551().method_1531();
            int n = PreviewScene.a;
            for (PackTextures$Sprite packTextures$Sprite : sprites.values()) {
                if (n == 0) {
                    textureManager.method_4615(packTextures$Sprite.id());
                    if (n == 0) continue;
                    break;
                }
                break block4;
            }
            sprites = Map.of();
            stats = null;
            loadedName = null;
        }
    }

    private static String lambda$upload$4(Identifier identifier) {
        return identifier.toString();
    }

    private static void lambda$measure$3(boolean[] blArray, Identifier identifier, InputSupplier inputSupplier) {
        blArray[1] = true;
    }

    private static void lambda$measure$2(boolean[] blArray, Identifier identifier, InputSupplier inputSupplier) {
        blArray[0] = true;
    }

    private static void lambda$load$1(String string, Map map, PackTextures$Stats packTextures$Stats) {
        PackTextures.upload(string, map, packTextures$Stats);
    }

    private static void lambda$want$0(String string, Supplier supplier) {
        PackTextures.load(string, supplier);
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[12];
                        var11_1 = 0;
                        var10_2 = "m\u0018!\u0013\u000b/#|\f0\u0017T>|i\u001b:\u0019\u000e\tx\b+\b\f<k\u007fB\tx\b+\b\f<k\u007fB\r|\f0\u0017&>|i\u001b:\u0019\u000ea\u0006\u007f\u0002&\u0012\u001d=\u0004\"\u001d=\u001b\ta\u0004=\u0019\u001a<oj\u0019\u0006m\u0018!\u0013\u000b/\u0011c\u001d'\u0015\u001f'`iB?\u0015\u001e&za\f#\u0012e\u00196\u0011V*gm\u0000<\u0012\u001d\u0011}{\u0002!\u0018";
                        var12_3 = "m\u0018!\u0013\u000b/#|\f0\u0017T>|i\u001b:\u0019\u000e\tx\b+\b\f<k\u007fB\tx\b+\b\f<k\u007fB\r|\f0\u0017&>|i\u001b:\u0019\u000ea\u0006\u007f\u0002&\u0012\u001d=\u0004\"\u001d=\u001b\ta\u0004=\u0019\u001a<oj\u0019\u0006m\u0018!\u0013\u000b/\u0011c\u001d'\u0015\u001f'`iB?\u0015\u001e&za\f#\u0012e\u00196\u0011V*gm\u0000<\u0012\u001d\u0011}{\u0002!\u0018".length();
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
                            var10_2 = "\"\u001d=\u001bW#ma\b'\u001d\ta\u0004=\u0019\u001a<oj\u0019";
                            var12_3 = "\"\u001d=\u001bW#ma\b'\u001d\ta\u0004=\u0019\u001a<oj\u0019".length();
                            var9_4 = 11;
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
                                v10 = 12;
                                break;
                            }
                            case 1: {
                                v10 = 109;
                                break;
                            }
                            case 2: {
                                v10 = 83;
                                break;
                            }
                            case 3: {
                                v10 = 124;
                                break;
                            }
                            case 4: {
                                v10 = 121;
                                break;
                            }
                            case 5: {
                                v10 = 78;
                                break;
                            }
                            default: {
                                v10 = 14;
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
                PackTextures.a = var13;
                var0_7 = 2272159313318715777L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "c\u007f?\u00879\u00ac\u00b8\u0090=J\u00cb\n\u0097\u00ce\u00e8c";
                var5_11 = "c\u007f?\u00879\u00ac\u00b8\u0090=J\u00cb\n\u0097\u00ce\u00e8c".length();
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
        PackTextures.b = var6_8;
        PackTextures.c = new Long[2];
        PackTextures.HOVER_MS = PackTextures.a(32225, 9004784024423078361L);
        PackTextures.sprites = Map.of();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x6438) & Short.MAX_VALUE;
        if (c[n2] == null) {
            PackTextures.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
