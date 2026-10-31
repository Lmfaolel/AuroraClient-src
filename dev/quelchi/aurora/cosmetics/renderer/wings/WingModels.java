package dev.quelchi.aurora.cosmetics.renderer.wings;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel;
import dev.quelchi.aurora.util.Daemon;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

public final class WingModels {
    private static final HttpClient HTTP;
    private static final Map<String, WingModel> READY;
    private static final Set<String> LOADING;
    private static final Set<String> FAILED;
    private static final Map<String, Long> USED;
    private static final long IDLE;
    private static long swept;
    private static volatile Set<String> keep;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private WingModels() {
    }

    
    public static WingModel get(String string) {
        WingModel wingModel;
        block30: {
            WingModel wingModel2;
            block31: {
                String string2;
                Set<String> set;
                block32: {
                    Object object;
                    boolean bl;
                    long l;
                    block28: {
                        block29: {
                            block27: {
                                block26: {
                                    block25: {
                                        long l2;
                                        block24: {
                                            l = System.currentTimeMillis();
                                            bl = WingModel.a;
                                            l2 = l;
                                            if (bl) break block24;
                                            if (l2 - swept <= WingModels.a(24940, 6953209106997764188L)) break block25;
                                            l2 = l;
                                        }
                                        WingModels.sweep(l2);
                                    }
                                    object = string;
                                    if (bl) break block26;
                                    if (object == null) break block27;
                                    object = string;
                                }
                                if (bl) break block28;
                                if (((String)object).matches(a[4])) break block29;
                            }
                            return null;
                        }
                        object = READY.get(string);
                    }
                    wingModel = wingModel2 = (WingModel)object;
                    if (!bl) {
                        if (wingModel != null) {
                            USED.put(string, l);
                        }
                        wingModel = wingModel2;
                    }
                    if (bl) break block30;
                    if (wingModel != null) break block31;
                    set = FAILED;
                    string2 = string;
                    if (bl) break block32;
                    if (set.contains(string2)) break block31;
                    set = LOADING;
                    string2 = string;
                }
                if (set.add(string2)) {
                    Daemon.run(a[13], () -> WingModels.lambda$get$1(string));
                    return null;
                }
            }
            wingModel = wingModel2;
        }
        return wingModel;
    }

    public static void retry(String string) {
        FAILED.remove(string);
    }

    private static void sweep(long l) {
        swept = l;
        boolean bl = WingModel.a;
        Iterator<Map.Entry<String, WingModel>> iterator = READY.entrySet().iterator();
        while (iterator.hasNext()) {
            block6: {
                Map.Entry<String, WingModel> entry = iterator.next();
                if (bl) break block6;
                if (l - USED.getOrDefault(entry.getKey(), 0L) < WingModels.a(8807, 8761222323089220437L)) {
                    continue;
                }
                MinecraftClient.method_1551().method_1531().method_4615(entry.getValue().texture);
                USED.remove(entry.getKey());
                iterator.remove();
            }
            if (!bl) continue;
        }
    }

    private static void finish(String string, WingModel wingModel) {
        block5: {
            String string2;
            Set<String> set;
            block4: {
                boolean bl = WingModel.a;
                set = LOADING;
                string2 = string;
                if (bl) break block4;
                set.remove(string2);
                if (wingModel != null) break block5;
                set = FAILED;
                string2 = string;
            }
            set.add(string2);
            return;
        }
        String[] stringArray = a;
        Identifier identifier = Identifier.method_60655((String)stringArray[14], (String)(stringArray[0] + string));
        MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)new NativeImageBackedTexture(() -> ((Identifier)identifier).toString(), wingModel.image));
        wingModel.image = null;
        wingModel.texture = identifier;
        READY.put(string, wingModel);
        USED.put(string, System.currentTimeMillis());
    }

    public static void prune(Set<String> set) {
        keep = set;
        Daemon.run(a[9], WingModels::lambda$prune$2);
    }

    private static Path cached(String string) {
        String[] stringArray = a;
        return FabricLoader.getInstance().getGameDir().resolve(stringArray[15] + string + stringArray[6]);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static byte[] bytes(String string) throws Exception {
        int n;
        byte[] byArray;
        Path path;
        block16: {
            Object object;
            boolean bl;
            block15: {
                Path path2;
                block14: {
                    path = WingModels.cached(string);
                    bl = WingModel.a;
                    path2 = path;
                    if (bl) break block14;
                    if (!Files.exists(path2, new LinkOption[0])) break block15;
                    path2 = path;
                }
                object = Files.readAllBytes(path2);
                if (string.equals(WingModels.sha256((byte[])object))) {
                    return object;
                }
            }
            String[] stringArray = a;
            object = HTTP.send(HttpRequest.newBuilder().uri(URI.create(CosmeticsSync.baseUrl() + stringArray[2] + string + stringArray[5])).timeout(Duration.ofSeconds(WingModels.a(10740, 8734190272784771267L))).GET().build(), HttpResponse.BodyHandlers.ofByteArray());
            byArray = (byte[])object.body();
            n = object.statusCode();
            if (bl) break block16;
            if (n / 100 != 2) throw new IllegalStateException(a[8]);
            n = string.equals(WingModels.sha256(byArray)) ? 1 : 0;
        }
        if (n == 0) {
            throw new IllegalStateException(a[8]);
        }
        Files.createDirectories(path.getParent(), new FileAttribute[0]);
        Files.write(path, byArray, new OpenOption[0]);
        return byArray;
    }

    private static String sha256(byte[] byArray) throws Exception {
        StringBuilder stringBuilder;
        block4: {
            StringBuilder stringBuilder2 = new StringBuilder();
            String[] stringArray = a;
            byte[] byArray2 = MessageDigest.getInstance(stringArray[10]).digest(byArray);
            boolean bl = WingModel.a;
            for (byte by : byArray2) {
                stringBuilder = stringBuilder2;
                if (!bl) {
                    stringBuilder.append(String.format(a[3], by));
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            stringBuilder = stringBuilder2;
        }
        return stringBuilder.toString();
    }

    private static void lambda$prune$2() {
        int n;
        block24: {
            int n2;
            boolean bl;
            Path path;
            String[] stringArray;
            block22: {
                block23: {
                    stringArray = a;
                    path = FabricLoader.getInstance().getGameDir().resolve(stringArray[1]);
                    bl = WingModel.a;
                    n2 = Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS);
                    if (bl) break block22;
                    if (n2 != 0) break block23;
                    return;
                }
                n2 = 0;
            }
            n = n2;
            try {
                Stream<Path> stream;
                block27: {
                    Stream<Path> stream2 = Files.list(path);
                    try {
                        for (Path path2 : stream2::iterator) {
                            boolean bl2;
                            block26: {
                                block25: {
                                    if (bl) break block24;
                                    String string = path2.getFileName().toString();
                                    bl2 = string.matches(a[12]);
                                    if (bl) break block25;
                                    if (!bl2) continue;
                                    bl2 = keep.contains(string.substring(0, 64));
                                }
                                if (bl) break block26;
                                if (bl2) {
                                    continue;
                                }
                                bl2 = Files.deleteIfExists(path2);
                            }
                            if (bl2) {
                                ++n;
                            }
                            if (!bl) continue;
                        }
                    }
                    catch (Throwable throwable) {
                        block28: {
                            if (bl || stream2 == null) break block28;
                            try {
                                stream2.close();
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        }
                        throw throwable;
                    }
                    stream = stream2;
                    if (bl) break block27;
                    if (stream == null) break block24;
                    stream = stream2;
                }
                stream.close();
            }
            catch (Exception exception) {
                stringArray = a;
                Aurora.LOG.debug(stringArray[16], (Throwable)exception);
            }
        }
        if (n > 0) {
            Aurora.LOG.info(a[7], (Object)n);
        }
    }

    private static void lambda$get$1(String string) {
        WingModel wingModel = null;
        try {
            wingModel = WingModel.parse(new String(WingModels.bytes(string), StandardCharsets.UTF_8));
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[11], (Object)string.substring(0, 8), (Object)exception.toString());
        }
        WingModel wingModel2 = wingModel;
        MinecraftClient.method_1551().execute(() -> WingModels.lambda$get$0(string, wingModel2));
    }

    private static void lambda$get$0(String string, WingModel wingModel) {
        WingModels.finish(string, wingModel);
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[17];
                        var11_1 = 0;
                        var10_2 = "\u0013{}%\u00002\u0012\u0005ga-\u0001|b\u0007sp*\u00162:\r|t1\u0012Kq|1\u001ex9\rq`m\u00062:\r|to\u0004A\"!:\f?\">{\u00120+9i%v\u000e\bJpq/\u001cy(\b\bJpq/\u001cy(\b\u001b4`f,\u0016ym\u001fo3-\u001fym\u0013{}%Sp\"\u0000w\u007fj\u00004\u0013\u0002sz.\u0016ym\u0012wa+\u0015t.\u0005fz-\u001d\u0010\u0005ga-\u0001|`\u0013{}%\u0003o8\nw\u00077ZRoA({\u00123{}%Sf0Dtr+\u001fx)^2h?\u0015?\">{\u00120+9i%v\u000eAc\u0006p~-\u0017x!\u000b\u0005ga-\u0001|`\u0013{}%\u0006\u0005ga-\u0001|";
                        var12_3 = "\u0013{}%\u00002\u0012\u0005ga-\u0001|b\u0007sp*\u00162:\r|t1\u0012Kq|1\u001ex9\rq`m\u00062:\r|to\u0004A\"!:\f?\">{\u00120+9i%v\u000e\bJpq/\u001cy(\b\bJpq/\u001cy(\b\u001b4`f,\u0016ym\u001fo3-\u001fym\u0013{}%Sp\"\u0000w\u007fj\u00004\u0013\u0002sz.\u0016ym\u0012wa+\u0015t.\u0005fz-\u001d\u0010\u0005ga-\u0001|`\u0013{}%\u0003o8\nw\u00077ZRoA({\u00123{}%Sf0Dtr+\u001fx)^2h?\u0015?\">{\u00120+9i%v\u000eAc\u0006p~-\u0017x!\u000b\u0005ga-\u0001|`\u0013{}%\u0006\u0005ga-\u0001|".length();
                        var9_4 = 6;
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
                            var10_2 = "\u0005ga-\u0001|b\u0007sp*\u00162:\r|t1\\\u00173{}%Sp\"\u0000w\u007fb\u0003o8\nw3$\u0012t!\u0001v";
                            var12_3 = "\u0005ga-\u0001|b\u0007sp*\u00162:\r|t1\\\u00173{}%Sp\"\u0000w\u007fb\u0003o8\nw3$\u0012t!\u0001v".length();
                            var9_4 = 19;
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
                                v10 = 100;
                                break;
                            }
                            case 1: {
                                v10 = 18;
                                break;
                            }
                            case 2: {
                                v10 = 19;
                                break;
                            }
                            case 3: {
                                v10 = 66;
                                break;
                            }
                            case 4: {
                                v10 = 115;
                                break;
                            }
                            case 5: {
                                v10 = 29;
                                break;
                            }
                            default: {
                                v10 = 77;
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
                WingModels.a = var13;
                var0_7 = 1558452424213696372L;
                var6_8 = new long[5];
                var3_9 = 0;
                var4_10 = "r\u00bdI\u009e}\u0089\u00b2Yl6\u00a1q\u00a5l\u0093\u0001B\u0091\u0084\u0007I\u0090\u00ba ";
                var5_11 = "r\u00bdI\u009e}\u0089\u00b2Yl6\u00a1q\u00a5l\u0093\u0001B\u0091\u0084\u0007I\u0090\u00ba ".length();
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
                    var4_10 = "u\u00de~\u00ee\u00df\u0006\u001a\u0018l\u0096\u00af\u00fe\n\u0098\u00a7\u00a9";
                    var5_11 = "u\u00de~\u00ee\u00df\u0006\u001a\u0018l\u0096\u00af\u00fe\n\u0098\u00a7\u00a9".length();
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
        WingModels.b = var6_8;
        WingModels.c = new Long[5];
        WingModels.IDLE = WingModels.a(2885, 6282864795188759156L);
        WingModels.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(WingModels.a(15380, 7430365059803725095L))).build();
        WingModels.READY = new HashMap<String, WingModel>();
        WingModels.LOADING = new HashSet<String>();
        WingModels.FAILED = new HashSet<String>();
        WingModels.USED = new HashMap<String, Long>();
        WingModels.keep = Set.of();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x133) & Short.MAX_VALUE;
        if (c[n2] == null) {
            WingModels.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
