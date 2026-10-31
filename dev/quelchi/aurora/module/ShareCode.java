package dev.quelchi.aurora.module;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.ShareCode$Decoded;
import java.io.ByteArrayOutputStream;
import java.lang.invoke.LambdaMetafactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import net.minecraft.client.MinecraftClient;

public final class ShareCode {
    private static final String HEAD;
    private static final int MAX;
    private static final Pattern SHORT;
    private static final HttpClient HTTP;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private ShareCode() {
    }

    public static String shortCode(String string) {
        String string2;
        block6: {
            block7: {
                int n = Category.a;
                string2 = string;
                if (n != 0) break block6;
                if (string2 != null) break block7;
                return null;
            }
            string2 = string.strip().toUpperCase(Locale.ROOT);
        }
        String string3 = string2;
        String string4 = SHORT.matcher(string3).matches() ? string3 : null;
        return string4;
    }

    public static void share(String string, JsonObject jsonObject, Consumer<String> consumer) {
        block4: {
            String string2;
            block5: {
                int n = Category.a;
                string2 = ShareCode.encode(string, jsonObject);
                if (n != 0) break block4;
                if (CosmeticsSync.enabled()) break block5;
                consumer.accept(string2);
                return;
            }
            ShareCode.run(() -> ShareCode.lambda$share$1(string2, string, jsonObject, consumer));
        }
    }

    public static void fetch(String string, BiConsumer<ShareCode$Decoded, String> biConsumer) {
        ShareCode.run(() -> ShareCode.lambda$fetch$3(string, biConsumer));
    }

    
    static ShareCode$Decoded fromService(String string) {
        int n = Category.a;
        try {
            block13: {
                String string2;
                JsonObject jsonObject;
                JsonObject jsonObject2;
                block15: {
                    block14: {
                        JsonObject jsonObject3;
                        block12: {
                            jsonObject3 = jsonObject2 = JsonParser.parseString((String)string).getAsJsonObject();
                            if (n != 0) break block12;
                            if (!jsonObject3.has(a[12])) break block13;
                            jsonObject = jsonObject2;
                            if (n != 0) break block14;
                            jsonObject3 = jsonObject.get(a[21]);
                        }
                        if (!jsonObject3.isJsonPrimitive()) break block13;
                        jsonObject = jsonObject2;
                    }
                    string2 = a[17];
                    if (n != 0) break block15;
                    if (!jsonObject.has(string2)) break block13;
                    jsonObject = jsonObject2;
                    string2 = a[4];
                }
                if (jsonObject.get(string2).isJsonObject()) {
                    String[] stringArray = a;
                    return new ShareCode$Decoded(jsonObject2.get(stringArray[21]).getAsString(), jsonObject2.getAsJsonObject(stringArray[4]));
                }
            }
            return null;
        }
        catch (Exception exception) {
            return null;
        }
    }

    private static void run(Runnable runnable) {
        Thread thread = new Thread(runnable, a[2]);
        thread.setDaemon(true);
        thread.start();
    }

    
    public static String encode(String string, JsonObject jsonObject) {
        String string2;
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("m", string);
        int n = Category.a;
        jsonObject2.add("s", (JsonElement)jsonObject);
        Deflater deflater = new Deflater(9);
        try {
            deflater.setInput(jsonObject2.toString().getBytes(StandardCharsets.UTF_8));
            deflater.finish();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] byArray = new byte[1024];
            while (!deflater.finished()) {
                byteArrayOutputStream.write(byArray, 0, deflater.deflate(byArray));
                if (n == 0) continue;
            }
            string2 = a[14] + Base64.getUrlEncoder().withoutPadding().encodeToString(byteArrayOutputStream.toByteArray());
        }
        catch (Throwable throwable) {
            deflater.end();
            throw throwable;
        }
        deflater.end();
        String string3 = string2;
        if (Module.a) {
            Category.a = ++n;
        }
        return string3;
    }

    
    public static ShareCode$Decoded decode(String string) {
        byte[] byArray;
        String string3;
        int n = Category.a;
        String string2 = string;
        if (n == 0) {
            if (string2 == null) {
                return null;
            }
            string2 = string.strip();
        }
        if (!(string3 = string2).startsWith(a[7])) {
            return null;
        }
        Inflater inflater = new Inflater();
        try {
            JsonObject jsonObject;
            block42: {
                block39: {
                    JsonObject jsonObject2;
                    block41: {
                        String string4;
                        block40: {
                            block38: {
                                inflater.setInput(Base64.getUrlDecoder().decode(string3.substring(a[7].length())));
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                byArray = new byte[1024];
                                while (!inflater.finished()) {
                                    int n2;
                                    block37: {
                                        block33: {
                                            int n3;
                                            block34: {
                                                block36: {
                                                    Inflater inflater2;
                                                    block35: {
                                                        n3 = inflater.inflate(byArray);
                                                        if (n != 0) break block33;
                                                        if (n3 != 0) break block34;
                                                        inflater2 = inflater;
                                                        if (n != 0) break block35;
                                                        if (inflater2.needsInput()) break block36;
                                                        inflater2 = inflater;
                                                    }
                                                    n2 = inflater2.needsDictionary() ? 1 : 0;
                                                    if (n != 0) break block37;
                                                    if (n2 == 0) break block34;
                                                }
                                                ShareCode$Decoded shareCode$Decoded = null;
                                                return shareCode$Decoded;
                                            }
                                            byteArrayOutputStream.write(byArray, 0, n3);
                                        }
                                        n2 = byteArrayOutputStream.size();
                                    }
                                    if (n2 > ShareCode.a(27843, 5866609967586208664L)) {
                                        ShareCode$Decoded shareCode$Decoded = null;
                                        return shareCode$Decoded;
                                    }
                                    if (n == 0) continue;
                                }
                                jsonObject2 = jsonObject = JsonParser.parseString((String)byteArrayOutputStream.toString(StandardCharsets.UTF_8)).getAsJsonObject();
                                string4 = "m";
                                if (n != 0) break block38;
                                if (!jsonObject2.has(string4)) break block39;
                                jsonObject2 = jsonObject;
                                string4 = "m";
                            }
                            if (n != 0) break block40;
                            if (!jsonObject2.get(string4).isJsonPrimitive()) break block39;
                            jsonObject2 = jsonObject;
                            if (n != 0) break block41;
                            string4 = "s";
                        }
                        if (!jsonObject2.has(string4)) break block39;
                        jsonObject2 = jsonObject.get("s");
                    }
                    if (jsonObject2.isJsonObject()) break block42;
                }
                ShareCode$Decoded shareCode$Decoded = null;
                return shareCode$Decoded;
            }
            ShareCode$Decoded shareCode$Decoded = new ShareCode$Decoded(jsonObject.get("m").getAsString(), jsonObject.getAsJsonObject("s"));
            return shareCode$Decoded;
        }
        catch (Exception exception) {
            byArray = null;
            return byArray;
        }
        finally {
            inflater.end();
        }
    }

    
    private static void lambda$fetch$3(String string, BiConsumer biConsumer) {
        Object object;
        Object object2;
        HttpRequest httpRequest;
        Object object3;
        block12: {
            object3 = null;
            int n = Category.a;
            try {
                String[] stringArray = a;
                httpRequest = CosmeticsSync.bearer(HttpRequest.newBuilder(URI.create(CosmeticsSync.baseUrl() + stringArray[8] + string)).timeout(Duration.ofSeconds(ShareCode.b(21492, 736827070876677400L))).GET()).build();
                Object object4 = object2 = HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                if (n == 0) {
                    if (object4.statusCode() == 404) {
                        stringArray = a;
                        object = stringArray[5];
                        if (n == 0) break block12;
                    }
                    object4 = object2;
                }
                if (n == 0) {
                    if (object4.statusCode() / 100 != 2) {
                        stringArray = a;
                        object = stringArray[15] + object2.statusCode() + stringArray[18];
                        if (n == 0) break block12;
                    }
                    object4 = object2.body();
                }
                object = (object3 = ShareCode.fromService((String)object4)) == null ? a[19] : null;
            }
            catch (Exception exception) {
                String[] stringArray = a;
                Aurora.LOG.debug(stringArray[9], (Throwable)exception);
                object = stringArray[13];
            }
        }
        httpRequest = object3;
        object2 = object;
        MinecraftClient.method_1551().execute(() -> ShareCode.lambda$fetch$2(biConsumer, (ShareCode$Decoded)((Object)httpRequest), (String)object2));
    }

    private static void lambda$fetch$2(BiConsumer biConsumer, ShareCode$Decoded shareCode$Decoded, String string) {
        biConsumer.accept(shareCode$Decoded, string);
    }

    
    private static void lambda$share$1(String var0, String var1_1, JsonObject var2_2, Consumer var3_3) {
        block18: {
            var5_4 = var0;
            var4_5 = Category.a;
            try {
                block17: {
                    block21: {
                        block16: {
                            block19: {
                                block20: {
                                    block14: {
                                        block15: {
                                            var6_6 /* !! */  = new JsonObject();
                                            var7_8 = CosmeticsSync.token();
                                            v0 = var6_6 /* !! */ ;
                                            v1 = ShareCode.a[10];
                                            v2 = var7_8;
                                            if (var4_5 != 0) break block14;
                                            if (v2 != null) break block15;
                                            v2 = "";
                                            break block14;
                                        }
                                        v2 = var7_8;
                                    }
                                    v0.addProperty(v1, v2);
                                    var11_9 = ShareCode.a;
                                    var6_6 /* !! */ .addProperty(var11_9[21], var1_1);
                                    var6_6 /* !! */ .add(var11_9[4], (JsonElement)var2_2);
                                    var8_10 = HttpRequest.newBuilder(URI.create(CosmeticsSync.baseUrl() + var11_9[20])).timeout(Duration.ofSeconds(ShareCode.b(23885, 3070555141211585443L))).header(var11_9[1], var11_9[0]).POST(HttpRequest.BodyPublishers.ofString(var6_6 /* !! */ .toString(), StandardCharsets.UTF_8)).build();
                                    var9_11 = ShareCode.HTTP.send(var8_10, HttpResponse.BodyHandlers.ofString());
                                    v4 = var9_11;
                                    if (var4_5 != 0) break block19;
                                    if (v4.statusCode() / 100 != 2) break block16;
                                    break block20;
                                    catch (Exception v5) {
                                        throw v5;
                                    }
                                }
                                v4 = var9_11.body();
                            }
                            var11_9 = ShareCode.a;
                            v7 = ShareCode.shortCode(JsonParser.parseString((String)((String)v4)).getAsJsonObject().get(var11_9[11]).getAsString());
                            break block21;
                        }
                        v7 = null;
                    }
                    var10_12 = v7;
                    if (var4_5 != 0) break block17;
                    if (var10_12 != null) {
                    }
                    var5_4 = var10_12;
                }
                if (var4_5 == 0) break block18;
lbl58:

                Aurora.LOG.debug(ShareCode.a[3], (Object)var9_11.statusCode());
            }
            catch (Exception var6_7) {
                Aurora.LOG.debug(ShareCode.a[6], (Throwable)var6_7);
            }
        }
        var6_6 /* !! */  = var5_4;
        MinecraftClient.method_1551().execute((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$share$0(java.util.function.Consumer java.lang.String ), ()V)((Consumer)var3_3, (String)var6_6 /* !! */ ));
    }

    private static void lambda$share$0(Consumer consumer, String string) {
        consumer.accept(string);
    }

    
    static {
        block27: {
            block26: {
                block25: {
                    block24: {
                        block23: {
                            block22: {
                                var21 = new String[22];
                                var19_1 = 0;
                                var18_2 = "+\u0007id.uP>\u001evfh|B%\u0019\f\t\u0018w|\"xEg#`x\"\u0012\u000b\u0002kg5w\u001c\u0007\u0018}}+sr%\u0013|{ \u0007\u0018}}+s\u0011)\u0018}m}6E\"\u00129{\"dG#\u0014|(4wX.Wbu\u0004.\u0016mi\u0019\u0004\u00189e(rD&\u00129k(rTj\u001bpc\"6E\"\u0016m&(\u0007\u0018}}+s\u0011)\u0018}m}6R%\u0002ulgx^>Wkm&uYj\u0003qmgeT8\u0001pk\"\u0004\u000b:(&\re\u0016iah{^.\u0002um49(\u0007\u0018}}+s\u0011)\u0018}m}6R%\u0002ulgx^>Wkm&uYj\u0003qmgeT8\u0001pk\"\u0005>\u0018rm)\u0004)\u0018}m\u0006'\u0018}}+s\u001c\t\u0018ld#6_%\u00039z\"wR\"Wm`\"6B/\u0005oa$s\u001f\u0004\u000b:(&\u0013\u001e\u001fx|grX.Wwg36F%\u0005r(o%\u0011E*<r \u0006rNXJ\u0004Rt\f0QB\fZ|\u0004'HZ\u0014Bd\u001c AQ\u001dKJ|\n\u0004.\u0016mi\u0002cY\u001a\u001e\u001fx|g\u007fBj\u0019v|gw\u0011'\u0018}}+s\u0011)\u0018}mi";
                                var20_3 = "+\u0007id.uP>\u001evfh|B%\u0019\f\t\u0018w|\"xEg#`x\"\u0012\u000b\u0002kg5w\u001c\u0007\u0018}}+sr%\u0013|{ \u0007\u0018}}+s\u0011)\u0018}m}6E\"\u00129{\"dG#\u0014|(4wX.Wbu\u0004.\u0016mi\u0019\u0004\u00189e(rD&\u00129k(rTj\u001bpc\"6E\"\u0016m&(\u0007\u0018}}+s\u0011)\u0018}m}6R%\u0002ulgx^>Wkm&uYj\u0003qmgeT8\u0001pk\"\u0004\u000b:(&\re\u0016iah{^.\u0002um49(\u0007\u0018}}+s\u0011)\u0018}m}6R%\u0002ulgx^>Wkm&uYj\u0003qmgeT8\u0001pk\"\u0005>\u0018rm)\u0004)\u0018}m\u0006'\u0018}}+s\u001c\t\u0018ld#6_%\u00039z\"wR\"Wm`\"6B/\u0005oa$s\u001f\u0004\u000b:(&\u0013\u001e\u001fx|grX.Wwg36F%\u0005r(o%\u0011E*<r \u0006rNXJ\u0004Rt\f0QB\fZ|\u0004'HZ\u0014Bd\u001c AQ\u001dKJ|\n\u0004.\u0016mi\u0002cY\u001a\u001e\u001fx|g\u007fBj\u0019v|gw\u0011'\u0018}}+s\u0011)\u0018}mi".length();
                                var17_4 = 16;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block22;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "e\u0016iah{^.\u0002um49A?\u0003\u0006'\u0018}}+s";
                                    var20_3 = "e\u0016iah{^.\u0002um49A?\u0003\u0006'\u0018}}+s".length();
                                    var17_4 = 16;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block22;
                                        break;
                                    }
                                    break;
                                }
lbl26:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    break block23;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_6 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_6;
                                v9 = v6[v8];
                                switch (var22_6 % 7) {
                                    case 0: {
                                        v10 = 74;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 119;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 25;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 8;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 71;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 22;
                                        break;
                                    }
                                    default: {
                                        v10 = 49;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_6;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_6);
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
                        ShareCode.a = var21;
                        ShareCode.HEAD = ShareCode.a[7];
                        var8_7 = 4039711992172582194L;
                        var14_8 = new long[2];
                        var11_9 = 0;
                        var12_10 = "\u00deP\u00db\n\u0086\u0083\u009e\b\u0088\u001cZ'\u0081\u00e9\u00fe\u00aa";
                        var13_11 = "\u00deP\u00db\n\u0086\u0083\u009e\b\u0088\u001cZ'\u0081\u00e9\u00fe\u00aa".length();
                        var10_12 = 0;
                        while (true) {
                            break block24;
                            break;
                        }
lbl86:

                        while (true) {
                            var14_8[v11] = (((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L) ^ var8_7;
                            if (var10_12 < var13_11) continue;
                            break block25;
                            break;
                        }
                    }
                    var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                    v11 = var11_9++;
                    while (true)
                }
                ShareCode.b = var14_8;
                ShareCode.c = new Integer[2];
                ShareCode.MAX = ShareCode.a(3168, 5058498814228794170L);
                var0_14 = 5435554772519432958L;
                var6_15 = new long[3];
                var3_16 = 0;
                var4_17 = "a\u00f25\u00e5\u00aa\u0019II\b\u00d7\u00dbf\u00bfHF%AWD\u0015aM\u00c7\u00e9";
                var5_18 = "a\u00f25\u00e5\u00aa\u0019II\b\u00d7\u00dbf\u00bfHF%AWD\u0015aM\u00c7\u00e9".length();
                var2_19 = 0;
                while (true) {
                    break block26;
                    break;
                }
lbl109:

                while (true) {
                    var6_15[v12] = (((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L) ^ var0_14;
                    if (var2_19 < var5_18) continue;
                    break block27;
                    break;
                }
            }
            var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
            v12 = var3_16++;
            while (true)
        }
        ShareCode.d = var6_15;
        ShareCode.e = new Long[3];
        ShareCode.SHORT = Pattern.compile(ShareCode.a[16]);
        ShareCode.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(ShareCode.b(21054, 4879970304236680401L))).build();
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x75A;
        if (c[n2] == null) {
            ShareCode.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x26EE) & Short.MAX_VALUE;
        if (e[n2] == null) {
            ShareCode.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
