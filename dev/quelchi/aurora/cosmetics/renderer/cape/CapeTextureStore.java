package dev.quelchi.aurora.cosmetics.renderer.cape;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CapePacks;
import dev.quelchi.aurora.cosmetics.content.PremiumCapes;
import dev.quelchi.aurora.cosmetics.renderer.CapeManager;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeTextureStore$MippedTexture;
import dev.quelchi.aurora.cosmetics.renderer.cape.CosmeticRegistry;
import java.io.InputStream;
import java.lang.invoke.LambdaMetafactory;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;

public final class CapeTextureStore {
    private static final Map<String, Identifier> IDS;
    private static final Set<Identifier> PENDING;
    private static final Set<Identifier> UPLOADED;
    private static final Set<Identifier> FAILED;
    private static final Set<CapeLayer> REQUESTED;
    private static final Set<Identifier> ANIMATED;
    static final int ANIMATED_MAX_WIDTH;
    private static final ExecutorService LOADER;
    private static final String[] a;
    private static final long b;

    private CapeTextureStore() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Identifier get(String string) {
        String string2;
        String string3;
        boolean bl;
        block17: {
            block18: {
                String string4;
                block15: {
                    block16: {
                        block14: {
                            bl = CapeLayer.a;
                            string4 = string;
                            if (bl) break block14;
                            if (string4 == null) return null;
                            string4 = string;
                        }
                        if (bl) break block15;
                        if (!string4.isBlank()) break block16;
                        return null;
                    }
                    string4 = CapeManager.key(string);
                }
                string3 = string4;
                string2 = string3;
                if (bl) break block17;
                if (CosmeticRegistry.contains(string2)) break block18;
                return null;
            }
            string2 = IDS.computeIfAbsent(string3, CapeTextureStore::lambda$get$1);
        }
        Identifier identifier = (Identifier)string2;
        Identifier identifier2 = identifier;
        if (bl) return identifier2;
        if (!CapeTextureStore.ready(identifier2, () -> CapeTextureStore.lambda$get$2(string3))) return null;
        identifier2 = identifier;
        return identifier2;
    }

    public static boolean frame(Identifier identifier) {
        return CapeTextureStore.ready(identifier, () -> CapeTextureStore.lambda$frame$3(identifier));
    }

    private static InputStream open(Identifier identifier) throws Exception {
        Object object;
        block4: {
            block5: {
                boolean bl = CapeLayer.a;
                object = identifier;
                if (bl) break block4;
                if (!object.method_12832().startsWith(a[0])) break block5;
                return Files.newInputStream(CapePacks.file(identifier.method_12832().substring(9)), new OpenOption[0]);
            }
            object = MinecraftClient.method_1551().method_1478().method_14486(identifier).orElseThrow(() -> CapeTextureStore.lambda$open$4(identifier));
        }
        return ((Resource)object).method_14482();
    }

    public static void preload(CapeLayer capeLayer) {
        boolean bl = CapeLayer.a;
        if (!REQUESTED.add(capeLayer)) {
            return;
        }
        for (Identifier identifier : capeLayer.frames()) {
            CapeTextureStore.frame(identifier);
            if (!bl) continue;
        }
    }

    public static void animated(Collection<Identifier> collection) {
        ANIMATED.addAll(collection);
    }

    public static void forget(Identifier identifier) {
        UPLOADED.remove(identifier);
        FAILED.remove(identifier);
    }

    private static boolean ready(Identifier identifier, Callable<InputStream> callable) {
        boolean bl;
        block14: {
            block15: {
                Set<Identifier> set;
                boolean bl2;
                block12: {
                    block13: {
                        bl2 = CapeLayer.a;
                        set = UPLOADED;
                        if (bl2) break block12;
                        if (!set.contains(identifier)) break block13;
                        return true;
                    }
                    set = FAILED;
                }
                bl = set.contains(identifier);
                if (bl2) break block14;
                if (bl) break block15;
                bl = PENDING.add(identifier);
                if (bl2) break block14;
                if (!bl) break block15;
                CapeTextureStore.load(identifier, callable);
            }
            bl = false;
        }
        return bl;
    }

    private static void load(Identifier identifier, Callable<InputStream> callable) {
        LOADER.execute(() -> CapeTextureStore.lambda$load$6(callable, identifier));
    }

    private static InputStream openBase(String string) throws Exception {
        Identifier identifier;
        block4: {
            Identifier identifier2;
            block5: {
                identifier2 = PremiumCapes.base(string);
                boolean bl = CapeLayer.a;
                identifier = identifier2;
                if (bl) break block4;
                if (identifier != null) break block5;
                throw new IllegalStateException(a[7] + string);
            }
            identifier = identifier2;
        }
        return CapeTextureStore.open(identifier);
    }

    private static void paintElytra(NativeImage nativeImage) {
        int n;
        int n2;
        int n3;
        float f;
        boolean bl;
        block23: {
            NativeImage nativeImage2;
            block21: {
                block22: {
                    bl = CapeLayer.a;
                    nativeImage2 = nativeImage;
                    if (bl) break block21;
                    if (nativeImage2.method_4307() == nativeImage.method_4323() * 2) break block22;
                    return;
                }
                nativeImage2 = nativeImage;
            }
            f = (float)nativeImage2.method_4307() / 64.0f;
            int n4 = (int)(22.0f * f);
            n3 = (int)(46.0f * f);
            n2 = (int)(22.0f * f);
            n = 0;
            block16: while (true) {
                int n5 = n;
                block17: while (n5 < n2) {
                    if (bl) break block23;
                    for (int i = n4; i < n3; ++i) {
                        n5 = nativeImage.method_61940(i, n);
                        if (bl) continue block17;
                        if (n5 >>> 24 == 0) continue;
                        return;
                    }
                    ++n;
                    if (!bl) continue block16;
                }
                break;
            }
            n = 0;
        }
        while (n < n2) {
            block24: {
                float f2 = Math.clamp(((float)n - 2.0f * f) / (20.0f * f), 0.0f, 1.0f);
                int n6 = Math.min((int)((1.0f + f2 * 16.0f) * f), (int)(17.0f * f) - 1);
                for (int i = n4; i < n3; ++i) {
                    int n7;
                    block29: {
                        float f3;
                        block30: {
                            int n8;
                            float f4;
                            int n9;
                            block27: {
                                block28: {
                                    int n10;
                                    block25: {
                                        block26: {
                                            if (bl) break block24;
                                            n10 = i;
                                            if (bl) break block25;
                                            if (!((float)n10 >= 35.0f * f)) break block26;
                                            n10 = 1;
                                            break block25;
                                        }
                                        n10 = 0;
                                    }
                                    n9 = n10;
                                    f4 = i;
                                    n8 = n9;
                                    if (bl) break block27;
                                    if (n8 == 0) break block28;
                                    n8 = 36;
                                    break block27;
                                }
                                n8 = 24;
                            }
                            f3 = Math.clamp((f4 - (float)n8 * f) / (10.0f * f), 0.0f, 1.0f);
                            n7 = n9;
                            if (bl) break block29;
                            if (n7 == 0) break block30;
                            f3 = 1.0f - f3;
                        }
                        n7 = Math.min((int)((1.0f + f3 * 10.0f) * f), (int)(11.0f * f) - 1);
                    }
                    int n11 = n7;
                    nativeImage.method_61941(i, n, nativeImage.method_61940(n11, n6));
                    if (!bl) continue;
                }
                ++n;
            }
            if (!bl) continue;
        }
    }

    static NativeImage[] mips(NativeImage nativeImage) {
        NativeImage[] nativeImageArray;
        block4: {
            int n = 32 - Integer.numberOfLeadingZeros(Math.min(nativeImage.method_4307(), nativeImage.method_4323()));
            boolean bl = CapeLayer.a;
            NativeImage[] nativeImageArray2 = new NativeImage[n];
            nativeImageArray2[0] = nativeImage;
            for (int i = 1; i < n; ++i) {
                nativeImageArray = nativeImageArray2;
                if (!bl) {
                    nativeImageArray[i] = CapeTextureStore.half(nativeImageArray2[i - 1]);
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            nativeImageArray = nativeImageArray2;
        }
        return nativeImageArray;
    }

    static NativeImage[] shrink(NativeImage[] nativeImageArray, int n) {
        NativeImage[] nativeImageArray2;
        block7: {
            int n2 = 0;
            boolean bl = CapeLayer.a;
            while (n2 < nativeImageArray.length - 1) {
                nativeImageArray2 = nativeImageArray;
                if (bl || bl) break block7;
                if (nativeImageArray2[n2].method_4307() <= n) break;
                nativeImageArray[n2++].close();
                if (!bl) continue;
                break;
            }
            nativeImageArray2 = Arrays.copyOfRange(nativeImageArray, n2, nativeImageArray.length);
        }
        return nativeImageArray2;
    }

    private static NativeImage half(NativeImage nativeImage) {
        int n = nativeImage.method_4307();
        int n2 = nativeImage.method_4323();
        int n3 = Math.max(1, n >> 1);
        int n4 = Math.max(1, n2 >> 1);
        boolean bl = CapeLayer.a;
        int[] nArray = nativeImage.method_61942();
        NativeImage nativeImage2 = new NativeImage(n3, n4, false);
        int n5 = 0;
        while (n5 < n4) {
            block9: {
                int n6 = 0;
                while (n6 < n3) {
                    block10: {
                        int n7;
                        int n8;
                        int n9;
                        NativeImage nativeImage3;
                        block13: {
                            long l;
                            long l2;
                            long l3;
                            long l4;
                            long l5;
                            long l6;
                            block11: {
                                block12: {
                                    l6 = 0L;
                                    if (bl) break block9;
                                    l5 = 0L;
                                    l4 = 0L;
                                    l3 = 0L;
                                    int n10 = 0;
                                    block6: while (true) {
                                        int n11 = n10;
                                        block7: while (n11 < 2) {
                                            if (bl) break block10;
                                            for (int i = 0; i < 2; ++i) {
                                                n11 = nArray[Math.min(n2 - 1, n5 * 2 + n10) * n + Math.min(n - 1, n6 * 2 + i)];
                                                if (bl) continue block7;
                                                int n12 = n11;
                                                int n13 = n12 >>> 24;
                                                l6 += (long)n13;
                                                l5 += (long)((n12 >> 16 & 0xFF) * n13);
                                                l4 += (long)((n12 >> 8 & 0xFF) * n13);
                                                l3 += (long)((n12 & 0xFF) * n13);
                                                if (!bl) continue;
                                            }
                                            ++n10;
                                            if (!bl) continue block6;
                                        }
                                        break;
                                    }
                                    nativeImage3 = nativeImage2;
                                    n9 = n6;
                                    n8 = n5;
                                    l2 = l6;
                                    l = 0L;
                                    if (bl) break block11;
                                    if (l2 != l) break block12;
                                    n7 = 0;
                                    break block13;
                                }
                                l2 = l6;
                                l = b;
                            }
                            n7 = (int)(l2 / l) << 24 | (int)(l5 / l6) << 16 | (int)(l4 / l6) << 8 | (int)(l3 / l6);
                        }
                        nativeImage3.method_61941(n9, n8, n7);
                        ++n6;
                    }
                    if (!bl) continue;
                }
                ++n5;
            }
            if (!bl) continue;
        }
        return nativeImage2;
    }

    
    private static void upload(Identifier identifier, NativeImage[] nativeImageArray) {
        block16: {
            boolean bl = CapeLayer.a;
            try {
                MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)new CapeTextureStore$MippedTexture(identifier, nativeImageArray));
                UPLOADED.add(identifier);
            }
            catch (Exception exception) {
                try {
                    FAILED.add(identifier);
                    Aurora.LOG.warn(a[2], (Object)identifier, (Object)exception);
                }
                catch (Throwable throwable) {
                    block17: {
                        for (NativeImage nativeImage : nativeImageArray) {
                            nativeImage.close();
                            if (!bl) {
                                if (!bl) continue;
                                break;
                            }
                            break block17;
                        }
                        PENDING.remove(identifier);
                    }
                    throw throwable;
                }
                for (NativeImage nativeImage : nativeImageArray) {
                    if (!bl) {
                        nativeImage.close();
                        if (!bl) continue;
                        break;
                    }
                }
                PENDING.remove(identifier);
            }
            for (NativeImage nativeImage : nativeImageArray) {
                nativeImage.close();
                if (!bl) {
                    if (!bl) continue;
                    break;
                }
                break block16;
            }
            PENDING.remove(identifier);
        }
    }

    
    private static void lambda$load$6(Callable var0, Identifier var1_1) {
        block39: {
            block38: {
                block36: {
                    var3_2 = null;
                    var2_3 = CapeLayer.a;
                    var4_4 = (NativeImage[])var0.call();
                    try {
                        var5_7 = NativeImage.method_4309((InputStream)var4_4);
                        if (var1_1.method_12832().startsWith(CapeTextureStore.a[8])) {
                            CapeTextureStore.paintElytra(var5_7);
                        }
                        var3_2 = CapeTextureStore.mips(var5_7);
                        if (CapeTextureStore.ANIMATED.contains(var1_1)) {
                            var3_2 = CapeTextureStore.shrink(var3_2, 512);
                        }
                        var6_11 = var3_2;
                        var3_2 = null;
                        if (var2_3) break block36;
                        MinecraftClient.method_1551().execute((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$load$5(net.minecraft.util.Identifier net.minecraft.client.texture.NativeImage[] ), ()V)((Identifier)var1_1, (NativeImage[])var6_11));
                        if (var4_4 == null) goto lbl_neg_1000
                    }
                    catch (Throwable var5_8) {
                        block37: {
                            if (var2_3 || var4_4 == null) break block37;
                            try {
                                var4_4.close();
                            }
                            catch (Throwable var6_12) {
                                var5_8.addSuppressed(var6_12);
                            }
                        }
                        throw var5_8;
                    }
lbl_neg_1000:

                    {
                        var4_4.close();
                    }
lbl_neg_1000:

                    {
                    }
                }
                v2 = var3_2;
                if (var2_3) break block38;
                if (v2 == null) break block39;
                v2 = var3_2;
            }
            var4_4 = v2;
            var5_9 = var4_4.length;
            var6_13 = 0;
            while (var6_13 < var5_9) {
                block47: {
                    block40: {
                        block48: {
                            var7_15 = var4_4[var6_13];
                            if (var2_3) break block47;
                            if (var7_15 == null) break block40;
                            break block48;
                            catch (Throwable v4) {
                                throw v4;
                            }
                        }
                        var7_15.close();
                    }
                    ++var6_13;
                }
                if (!var2_3) continue;
                break block39;
            }
            break block39;
            catch (Exception var4_5) {
                block41: {
                    try {
                        CapeTextureStore.FAILED.add(var1_1);
                        CapeTextureStore.PENDING.remove(var1_1);
                        Aurora.LOG.warn(CapeTextureStore.a[6], (Object)var1_1, (Object)var4_5);
                        v6 = var3_2;
                        if (var2_3) break block41;
                    }
                    catch (Throwable var8_17) {
                        block44: {
                            block43: {
                                v10 = var3_2;
                                if (var2_3) break block43;
                                if (v10 == null) break block44;
                                v10 = var3_2;
                            }
                            var9_18 = v10;
                            var10_19 = var9_18.length;
                            var11_20 = 0;
                            while (var11_20 < var10_19) {
                                block45: {
                                    block46: {
                                        var12_21 = var9_18[var11_20];
                                        if (var2_3) break block45;
                                        if (var12_21 == null) break block46;
                                        var12_21.close();
                                    }
                                    ++var11_20;
                                }
                                if (!var2_3) continue;
                            }
                        }
                        throw var8_17;
                    }
                    if (v6 == null) break block39;
                    v6 = var3_2;
                }
                var4_6 = v6;
                var5_10 = var4_6.length;
                var6_14 = 0;
                while (var6_14 < var5_10) {
                    block49: {
                        block42: {
                            block51: {
                                block50: {
                                    var7_16 = var4_6[var6_14];
                                    if (var2_3) break block39;
                                    if (var2_3) break block49;
                                    break block50;
                                    catch (Throwable v7) {
                                        throw v7;
                                    }
                                }
                                if (var7_16 == null) break block42;
                                break block51;
                                catch (Throwable v8) {
                                    throw v8;
                                }
                            }
                            var7_16.close();
                        }
                        ++var6_14;
                    }
                    if (!var2_3) continue;
                }
            }
        }
    }

    private static void lambda$load$5(Identifier identifier, NativeImage[] nativeImageArray) {
        CapeTextureStore.upload(identifier, nativeImageArray);
    }

    private static IllegalStateException lambda$open$4(Identifier identifier) {
        return new IllegalStateException(a[3] + String.valueOf(identifier));
    }

    private static InputStream lambda$frame$3(Identifier identifier) throws Exception {
        return CapeTextureStore.open(identifier);
    }

    private static InputStream lambda$get$2(String string) throws Exception {
        return CapeTextureStore.openBase(string);
    }

    private static Identifier lambda$get$1(String string) {
        String[] stringArray = a;
        return Identifier.method_60655((String)stringArray[1], (String)(stringArray[4] + string));
    }

    private static Thread lambda$static$0(Runnable runnable) {
        Thread thread = new Thread(runnable, a[5]);
        thread.setDaemon(true);
        return thread;
    }

    
    static {
        var11 = new String[9];
        var9_1 = 0;
        var8_2 = "\u000e\u0005\u0010\u0007S\b@\u0006K\u0006\f\u0011\u0012\rQ\b\".\u000b\u0015\u000eGIM\u0002\u0010@\u0017S\u0005L\f\u0000@\u0001B\u0019FM\u0010\u0005\u001aW\u001cQ\bDG\u0019^N\u0013 \r\u0013\u0011J\u0007DM\u0007\u0001\u0012FIE\u001f\u0005\r\u0007\u0003\u0005\u000e\u0005\u0010\u0007\f\u0012,\u0011\u0012\rQ\b\u000e.\u0005\u0010\u0007w\f[\u0019\u0011\u0012\u0007 .\u000b\u0015\u000eGIM\u0002\u0010@\u000eL\bGM\u0007\u0001\u0012FIW\b\u001c\u0014\u0017Q\f\u0003J\u001f\u001dE";
        var10_3 = "\u000e\u0005\u0010\u0007S\b@\u0006K\u0006\f\u0011\u0012\rQ\b\".\u000b\u0015\u000eGIM\u0002\u0010@\u0017S\u0005L\f\u0000@\u0001B\u0019FM\u0010\u0005\u001aW\u001cQ\bDG\u0019^N\u0013 \r\u0013\u0011J\u0007DM\u0007\u0001\u0012FIE\u001f\u0005\r\u0007\u0003\u0005\u000e\u0005\u0010\u0007\f\u0012,\u0011\u0012\rQ\b\u000e.\u0005\u0010\u0007w\f[\u0019\u0011\u0012\u0007 .\u000b\u0015\u000eGIM\u0002\u0010@\u000eL\bGM\u0007\u0001\u0012FIW\b\u001c\u0014\u0017Q\f\u0003J\u001f\u001dE".length();
        var7_4 = 9;
        var6_5 = -1;
        block11: while (true) {
            v0 = ++var6_5;
            v1 = var8_2.substring(v0, v0 + var7_4);
            v2 = -1;
            block12: while (true) {
                var8_2 = "#\u000b@\u0003Q\u001d\u0003\u000b\u000b\u0012B@\bS\bD\u0005\u000e\u0005\u0010\u0007\f";
                var10_3 = "#\u000b@\u0003Q\u001d\u0003\u000b\u000b\u0012B@\bS\bD\u0005\u000e\u0005\u0010\u0007\f".length();
                var7_4 = 16;
                var6_5 = -1;
                while (true) {
                    v3 = ++var6_5;
                    v1 = var8_2.substring(v3, v3 + var7_4);
                    v2 = 0;
lbl21:

                    v4 = v1.toCharArray();
                    v5 = v4;
                    v6 = v4.length;
                    var12_6 = 0;
                    do {
                        v5 = v5;
                        v7 = var12_6;
                        v8 = v5[v7];
                        switch (var12_6 % 7) {
                            case 0: {
                                v9 = 109;
                                break;
                            }
                            case 1: {
                                v9 = 100;
                                break;
                            }
                            case 2: {
                                v9 = 96;
                                break;
                            }
                            case 3: {
                                v9 = 98;
                                break;
                            }
                            case 4: {
                                v9 = 35;
                                break;
                            }
                            case 5: {
                                v9 = 105;
                                break;
                            }
                            default: {
                                v9 = 35;
                            }
                        }
                        v5[v7] = (char)(v8 ^ v9);
                        ++var12_6;
lbl53:

                        v6 = v6;
                    } while (v6 > var12_6);
                    v10 = new String(v5);
                    switch (v2) {
                        default: {
                            var11[var9_1++] = v10.intern();
                            if ((var6_5 += var7_4) >= var10_3) continue block12;
                            var7_4 = var8_2.charAt(var6_5);
                            continue block11;
                        }
                        case 0: 
                    }
                    var11[var9_1++] = v10.intern();
                    if ((var6_5 += var7_4) >= var10_3) {
                        CapeTextureStore.a = var11;
                        var4_7 = 7709085112926355796L;
                        var2_8 = -1315482164925938860L ^ var4_7;
                        CapeTextureStore.ANIMATED_MAX_WIDTH = (int)var2_8;
                        var0_9 = 110794595259900494L;
                        CapeTextureStore.b = 110794595259900490L ^ var0_9;
                        CapeTextureStore.IDS = new ConcurrentHashMap<String, Identifier>();
                        CapeTextureStore.PENDING = ConcurrentHashMap.newKeySet();
                        CapeTextureStore.UPLOADED = ConcurrentHashMap.newKeySet();
                        CapeTextureStore.FAILED = ConcurrentHashMap.newKeySet();
                        CapeTextureStore.REQUESTED = ConcurrentHashMap.newKeySet();
                        CapeTextureStore.ANIMATED = ConcurrentHashMap.newKeySet();
                        CapeTextureStore.LOADER = Executors.newSingleThreadExecutor((ThreadFactory)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Runnable;)Ljava/lang/Thread;, lambda$static$0(java.lang.Runnable ), (Ljava/lang/Runnable;)Ljava/lang/Thread;)());
                        return;
                    }
                    var7_4 = var8_2.charAt(var6_5);
                }
                break;
            }
            break;
        }
    }
}
