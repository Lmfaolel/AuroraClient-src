package dev.quelchi.aurora.cosmetics.backend;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CapePacks$Got;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.util.Daemon;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

public final class CapePacks {
    private static final HttpClient HTTP;
    private static final Map<String, List<Runnable>> WAITING;
    private static final Set<String> RETRYING;
    private static int started;
    private static Set<String> keep;
    private static final String[] a;
    private static final long b;
    private static final long[] c;
    private static final Long[] d;

    private CapePacks() {
    }

    public static synchronized int pending() {
        return WAITING.size() - RETRYING.size();
    }

    public static synchronized int started() {
        return started;
    }

    private static Path root() {
        return FabricLoader.getInstance().getGameDir().resolve(a[6]);
    }

    public static boolean has(String string) {
        return Files.isDirectory(CapePacks.root().resolve(string), new LinkOption[0]);
    }

    public static Path file(String string) {
        Path path;
        block4: {
            Path path2;
            block5: {
                path2 = CapePacks.root().resolve(string).normalize();
                int n = CosmeticsSync.a;
                path = path2;
                if (n != 0) break block4;
                if (path.startsWith(CapePacks.root())) break block5;
                throw new IllegalArgumentException(a[15] + string);
            }
            path = path2;
        }
        return path;
    }

    public static synchronized void fetch(String string, Runnable runnable) {
        List<Runnable> list;
        block5: {
            block4: {
                int n = CosmeticsSync.a;
                list = WAITING.get(string);
                if (n != 0) break block4;
                if (list == null) break block5;
                list.add(runnable);
            }
            return;
        }
        list = new ArrayList<Runnable>();
        list.add(runnable);
        WAITING.put(string, list);
        ++started;
        Daemon.run(a[8], () -> CapePacks.lambda$fetch$1(string));
    }

    private static synchronized void finish(String string, boolean bl) {
        block5: {
            List<Runnable> list;
            int n;
            block6: {
                List<Runnable> list2 = WAITING.remove(string);
                RETRYING.remove(string);
                n = CosmeticsSync.a;
                if (!bl) break block5;
                list = list2;
                if (n != 0) break block6;
                if (list == null) break block5;
                list = list2;
            }
            for (Runnable runnable : list) {
                runnable.run();
                if (n == 0) continue;
            }
        }
    }

    
    public static void prune(Set<String> set) {
        Class<CapePacks> clazz = CapePacks.class;
        synchronized (CapePacks.class) {
            keep = set;
            Daemon.run(a[9], CapePacks::sweep);
            return;
        }
    }

    
    private static void sweep() {
        int n;
        block25: {
            Path path = CapePacks.root();
            int n2 = CosmeticsSync.a;
            int n3 = Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS);
            if (n2 == 0) {
                if (n3 == 0) {
                    return;
                }
                n3 = 0;
            }
            n = n3;
            try {
                Stream<Path> stream;
                block26: {
                    Stream<Path> stream2 = Files.list(path);
                    try {
                        for (Path path2 : stream2::iterator) {
                            String string;
                            block28: {
                                block27: {
                                    Class<CapePacks> clazz;
                                    string = path2.getFileName().toString();
                                    if (n2 != 0) break block25;
                                    boolean bl = string.matches(a[12]);
                                    if (n2 == 0) {
                                        if (!bl) continue;
                                        bl = Files.isDirectory(path2, LinkOption.NOFOLLOW_LINKS);
                                    }
                                    if (!bl) continue;
                                    Class<CapePacks> clazz2 = clazz = CapePacks.class;
                                    if (n2 != 0) break block27;
                                    // MONITORENTER : clazz2
                                    if (!keep.contains(string) && !WAITING.containsKey(string)) break block28;
                                    clazz2 = clazz;
                                }
                                if (n2 == 0) continue;
                            }
                            try {
                                CapePacks.deleteTree(path2);
                                ++n;
                            }
                            catch (Exception exception) {
                                String[] stringArray = a;
                                Aurora.LOG.debug(stringArray[14], (Object)string.substring(0, 8), (Object)exception);
                            }
                            if (n2 == 0) continue;
                        }
                        stream = stream2;
                        if (n2 != 0) break block26;
                    }
                    catch (Throwable throwable) {
                        if (n2 != 0) throw throwable;
                        if (stream2 == null) throw throwable;
                        try {
                            stream2.close();
                            throw throwable;
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                    if (stream == null) break block25;
                    stream = stream2;
                }
                stream.close();
            }
            catch (Exception exception) {
                String[] stringArray = a;
                Aurora.LOG.debug(stringArray[11], (Throwable)exception);
            }
        }
        if (n <= 0) return;
        Aurora.LOG.info(a[10], (Object)n);
    }

    
    private static CapePacks$Got download(String string) {
        String[] stringArray = a;
        Path path = CapePacks.root().resolve(string + stringArray[2]);
        int n = CosmeticsSync.a;
        Path path2 = CapePacks.root().resolve(string + stringArray[5]);
        try {
            Files.createDirectories(CapePacks.root(), new FileAttribute[0]);
            HttpResponse<Path> httpResponse = HTTP.send(HttpRequest.newBuilder().uri(URI.create(CosmeticsSync.baseUrl() + stringArray[4] + string + stringArray[17])).timeout(Duration.ofSeconds(CapePacks.a(26505, 556137823049101938L))).GET().build(), HttpResponse.BodyHandlers.ofFile(path));
            int n2 = httpResponse.statusCode();
            if (n == 0) {
                if (n2 / 100 != 2) {
                    stringArray = a;
                    Aurora.LOG.warn(stringArray[3], (Object)string.substring(0, 8), (Object)httpResponse.statusCode());
                    CapePacks$Got capePacks$Got = CapePacks$Got.RETRY;
                    return capePacks$Got;
                }
                n2 = string.equals(CapePacks.sha256(path)) ? 1 : 0;
            }
            if (n2 == 0) {
                stringArray = a;
                Aurora.LOG.warn(stringArray[1], (Object)string.substring(0, 8));
                CapePacks$Got capePacks$Got = CapePacks$Got.BAD;
                return capePacks$Got;
            }
            CapePacks.deleteTree(path2);
            Files.createDirectories(path2, new FileAttribute[0]);
            try (Object object = new ZipInputStream(Files.newInputStream(path, new OpenOption[0]));){
                ZipEntry zipEntry;
                while ((zipEntry = object.getNextEntry()) != null) {
                    if (n == 0) {
                        if (!zipEntry.getName().matches(a[13])) continue;
                        Files.copy(object, path2.resolve(zipEntry.getName()), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
                        if (n == 0) continue;
                    }
                }
            }
            Files.move(path2, CapePacks.root().resolve(string), StandardCopyOption.ATOMIC_MOVE);
            object = CapePacks$Got.OK;
            return object;
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[16], (Object)string.substring(0, 8), (Object)exception.toString());
            CapePacks$Got capePacks$Got = CapePacks$Got.RETRY;
            return capePacks$Got;
        }
        finally {
            try {
                Files.deleteIfExists(path);
            }
            catch (Exception exception) {}
        }
    }

    
    private static void deleteTree(Path path) throws Exception {
        Path path2;
        block17: {
            block19: {
                Stream<Path> stream;
                block18: {
                    int n = CosmeticsSync.a;
                    Path path3 = path;
                    if (n == 0) {
                        if (!Files.isDirectory(path3, new LinkOption[0])) {
                            return;
                        }
                        path3 = path;
                    }
                    Stream<Path> stream2 = Files.list(path3);
                    try {
                        Iterator iterator = ((Iterable)stream2::iterator).iterator();
                        while (iterator.hasNext()) {
                            Path path4;
                            path2 = path4 = (Path)iterator.next();
                            if (n == 0) {
                                Files.delete(path2);
                                if (n == 0) continue;
                            }
                            break block17;
                        }
                        stream = stream2;
                        if (n != 0) break block18;
                    }
                    catch (Throwable throwable) {
                        if (n != 0) throw throwable;
                        if (stream2 == null) throw throwable;
                        try {
                            stream2.close();
                            throw throwable;
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                    if (stream == null) break block19;
                    stream = stream2;
                }
                stream.close();
            }
            path2 = path;
        }
        Files.delete(path2);
    }

    
    private static String sha256(Path path) throws Exception {
        Object object;
        int n;
        Object object2;
        MessageDigest messageDigest;
        block15: {
            InputStream inputStream;
            block16: {
                String[] stringArray = a;
                messageDigest = MessageDigest.getInstance(stringArray[0]);
                object2 = Files.newInputStream(path, new OpenOption[0]);
                n = CosmeticsSync.a;
                try {
                    int n2;
                    byte[] byArray = new byte[(int)b];
                    while ((n2 = ((InputStream)object2).read(byArray)) > 0) {
                        messageDigest.update(byArray, 0, n2);
                        if (n == 0) {
                            if (n == 0) continue;
                        }
                        break block15;
                    }
                    inputStream = object2;
                    if (n != 0) break block16;
                }
                catch (Throwable throwable) {
                    if (n != 0) throw throwable;
                    if (object2 == null) throw throwable;
                    try {
                        ((InputStream)object2).close();
                        throw throwable;
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
                if (inputStream == null) break block15;
                inputStream = object2;
            }
            inputStream.close();
        }
        object2 = new StringBuilder();
        for (byte by : messageDigest.digest()) {
            object = object2;
            if (n != 0) return ((StringBuilder)object).toString();
            ((StringBuilder)object).append(String.format(a[7], by));
            if (n == 0) continue;
        }
        object = object2;
        return ((StringBuilder)object).toString();
    }

    
    private static void lambda$fetch$1(String string) {
        CapePacks$Got capePacks$Got;
        int n;
        block8: {
            CapePacks$Got capePacks$Got2 = CapePacks.download(string);
            int n2 = CosmeticsSync.a;
            for (n = 1; n < 3; ++n) {
                capePacks$Got = capePacks$Got2;
                if (n2 != 0 || n2 != 0) break block8;
                if (capePacks$Got != CapePacks$Got.RETRY) break;
                Class<CapePacks> clazz = CapePacks.class;
                // MONITORENTER : dev.quelchi.aurora.cosmetics.backend.CapePacks.class
                RETRYING.add(string);
                try {
                    Thread.sleep((long)n * CapePacks.a(24224, 5108805312566725465L));
                }
                catch (InterruptedException interruptedException) {
                    break;
                }
                capePacks$Got2 = CapePacks.download(string);
                if (n2 == 0) continue;
            }
            capePacks$Got = capePacks$Got2;
        }
        n = capePacks$Got == CapePacks$Got.OK ? 1 : 0;
        MinecraftClient.method_1551().execute(() -> CapePacks.lambda$fetch$0(string, n != 0));
    }

    private static void lambda$fetch$0(String string, boolean bl) {
        CapePacks.finish(string, bl);
    }

    
    static {
        block24: {
            block23: {
                block25: {
                    block22: {
                        block21: {
                            var15 = new String[18];
                            var13_1 = 0;
                            var12_2 = "\u001bO<5&E$ \u000bf\r}4\u0000s+l]ciPt)n\u0011}pPd-u\u0014~}\u0013s<n\u0012v\tf}\u0014h:\u0000s:s\u001c\u000bf\r}4\u0000s+l]ciPt)n\u0011}pJ2\u0000S)H4\u000bo\u0016gd\u0012ky\u0015f!d\u000e7a_q)w\u0018hu\u0013ye\u0004fs\u0010h\u0012)r\u000fwf\u0011=+f\u001epq_q)w\u0018k\u0004m7O`\u000f)r\u000fwf\u0011?+f\r}d\u0011q#\u0010)r\u000fwf\u0011?+f\r}d\u0002g&b\u001a\u0018u\bvq\u001423z]wx\u00142+f\r}4\u0000s+lUk=\u0016\u000bf\r}4\u0000s+l]hf\u0005|-'\u001by}\u001cw,\f\u00137P!u]t\u0015|K,i\u000f\u0013fPb$]+\u0017ZVD:\u0000|/\u001c\u000bh\btpP|'s]hf\u0005|-'\u001eyd\u001528f\u001es4\u000bo\u001d\u000bf\r}4\u0016{$b]wa\u0004a!c\u00188`\u0018whd\u001c{|\u0015(h";
                            var14_3 = "\u001bO<5&E$ \u000bf\r}4\u0000s+l]ciPt)n\u0011}pPd-u\u0014~}\u0013s<n\u0012v\tf}\u0014h:\u0000s:s\u001c\u000bf\r}4\u0000s+l]ciPt)n\u0011}pJ2\u0000S)H4\u000bo\u0016gd\u0012ky\u0015f!d\u000e7a_q)w\u0018hu\u0013ye\u0004fs\u0010h\u0012)r\u000fwf\u0011=+f\u001epq_q)w\u0018k\u0004m7O`\u000f)r\u000fwf\u0011?+f\r}d\u0011q#\u0010)r\u000fwf\u0011?+f\r}d\u0002g&b\u001a\u0018u\bvq\u001423z]wx\u00142+f\r}4\u0000s+lUk=\u0016\u000bf\r}4\u0000s+l]hf\u0005|-'\u001by}\u001cw,\f\u00137P!u]t\u0015|K,i\u000f\u0013fPb$]+\u0017ZVD:\u0000|/\u001c\u000bh\btpP|'s]hf\u0005|-'\u001eyd\u001528f\u001es4\u000bo\u001d\u000bf\r}4\u0016{$b]wa\u0004a!c\u00188`\u0018whd\u001c{|\u0015(h".length();
                            var11_4 = 7;
                            var10_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var10_5;
                                v1 = var12_2.substring(v0, v0 + var11_4);
                                v2 = -1;
                                break block21;
                                break;
                            }
lbl12:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                var12_2 = "\u000bf\r}4\u0000s+l]ciPt)n\u0011}pJ23z\u0004f}\u0014h";
                                var14_3 = "\u000bf\r}4\u0000s+l]ciPt)n\u0011}pJ23z\u0004f}\u0014h".length();
                                var11_4 = 23;
                                var10_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var10_5;
                                    v1 = var12_2.substring(v4, v4 + var11_4);
                                    v2 = 0;
                                    break block21;
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
                                break block22;
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
                                    v10 = 72;
                                    break;
                                }
                                case 1: {
                                    v10 = 7;
                                    break;
                                }
                                case 2: {
                                    v10 = 125;
                                    break;
                                }
                                case 3: {
                                    v10 = 24;
                                    break;
                                }
                                case 4: {
                                    v10 = 20;
                                    break;
                                }
                                case 5: {
                                    v10 = 112;
                                    break;
                                }
                                default: {
                                    v10 = 18;
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
                    CapePacks.a = var15;
                    break block25;
lbl77:

                    while (true) {
                        continue;
                        break;
                    }
                }
                var8_7 = 2945207351110125774L;
                while (true)
                CapePacks.b = 2911322356952123598L ^ var8_7;
                var0_8 = 2148828722312013490L;
                var6_9 = new long[3];
                var3_10 = 0;
                var4_11 = "\u001ae\u00e01\u00c9\u0090T\u00b8xw'yR#\u00d2\u00c9[43\u000b\u00be\u00bb\u0018\u00db";
                var5_12 = "\u001ae\u00e01\u00c9\u0090T\u00b8xw'yR#\u00d2\u00c9[43\u000b\u00be\u00bb\u0018\u00db".length();
                var2_13 = 0;
                while (true) {
                    break block23;
                    break;
                }
lbl94:

                while (true) {
                    var6_9[v11] = (((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L) ^ var0_8;
                    if (var2_13 < var5_12) continue;
                    break block24;
                    break;
                }
            }
            var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
            v11 = var3_10++;
            while (true)
        }
        CapePacks.c = var6_9;
        CapePacks.d = new Long[3];
        CapePacks.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(CapePacks.a(24971, 7324272669081449585L))).build();
        CapePacks.WAITING = new HashMap<String, List<Runnable>>();
        CapePacks.RETRYING = new HashSet<String>();
        CapePacks.keep = Set.of();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x55FB) & Short.MAX_VALUE;
        if (d[n2] == null) {
            CapePacks.d[n2] = c[n2] ^ l;
        }
        return d[n2];
    }
}
