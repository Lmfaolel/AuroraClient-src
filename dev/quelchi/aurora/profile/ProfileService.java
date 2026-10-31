package dev.quelchi.aurora.profile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.profile.Profile;
import dev.quelchi.aurora.profile.ProfileManager;
import dev.quelchi.aurora.profile.ProfileService$Listed;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import net.minecraft.client.MinecraftClient;

public final class ProfileService {
    private static final HttpClient HTTP;
    public static volatile String status;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private ProfileService() {
    }

    public static void share(Profile profile, Consumer<String> consumer) {
        ProfileService.run(() -> ProfileService.lambda$share$1(profile, consumer));
    }

    public static void fetch(String string, Consumer<JsonObject> consumer) {
        ProfileService.run(() -> ProfileService.lambda$fetch$3(string, consumer));
    }

    public static void publicList(String string, String string2, Consumer<List<ProfileService$Listed>> consumer) {
        ProfileService.run(() -> ProfileService.lambda$publicList$5(string, string2, consumer));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void forget(String string) {
        String string2;
        block7: {
            block8: {
                int n;
                block6: {
                    n = Profile.a;
                    string2 = string;
                    if (n != 0) break block6;
                    if (string2 == null) return;
                    string2 = string;
                }
                if (n != 0) break block7;
                if (!string2.isEmpty()) break block8;
                return;
            }
            string2 = string;
        }
        ProfileService.run(() -> ProfileService.lambda$forget$6(string2));
    }

    private static List<ProfileService$Listed> parse(JsonArray jsonArray) {
        ArrayList<ProfileService$Listed> arrayList;
        block11: {
            JsonArray jsonArray2;
            int n;
            ArrayList<ProfileService$Listed> arrayList2;
            block9: {
                block10: {
                    arrayList2 = new ArrayList<ProfileService$Listed>();
                    n = Profile.a;
                    jsonArray2 = jsonArray;
                    if (n != 0) break block9;
                    if (jsonArray2 != null) break block10;
                    return arrayList2;
                }
                jsonArray2 = jsonArray;
            }
            for (JsonElement jsonElement : jsonArray2) {
                int n2;
                String string;
                String string2;
                String string3;
                String string4;
                String string5;
                ProfileService$Listed profileService$Listed;
                ProfileService$Listed profileService$Listed2;
                JsonObject jsonObject;
                block13: {
                    block12: {
                        ProfileService$Listed profileService$Listed3;
                        jsonObject = jsonElement.getAsJsonObject();
                        arrayList = arrayList2;
                        if (n != 0) break block11;
                        profileService$Listed2 = profileService$Listed3;
                        profileService$Listed = profileService$Listed3;
                        string5 = ProfileService.str(jsonObject, a[24]);
                        string4 = ProfileService.str(jsonObject, a[33]);
                        string3 = ProfileService.str(jsonObject, a[13]);
                        string2 = ProfileService.str(jsonObject, a[0]);
                        string = ProfileService.str(jsonObject, a[29]);
                        if (!jsonObject.has(a[11])) break block12;
                        n2 = jsonObject.get(a[7]).getAsInt();
                        break block13;
                    }
                    n2 = 0;
                }
                String[] stringArray = a;
                long l = jsonObject.has(stringArray[15]) ? jsonObject.get(stringArray[22]).getAsLong() : 0L;
                stringArray = a;
                profileService$Listed2(string5, string4, string3, string2, string, n2, l, jsonObject.has(stringArray[8]) && jsonObject.get(stringArray[10]).getAsBoolean());
                arrayList.add(profileService$Listed);
                if (n == 0) continue;
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    private static JsonObject authed() {
        String string;
        String string2;
        JsonObject jsonObject;
        JsonObject jsonObject2;
        block2: {
            String string3;
            block3: {
                jsonObject2 = new JsonObject();
                int n = Profile.a;
                string3 = CosmeticsSync.token();
                jsonObject = jsonObject2;
                string2 = a[31];
                string = string3;
                if (n != 0) break block2;
                if (string != null) break block3;
                string = "";
                break block2;
            }
            string = string3;
        }
        jsonObject.addProperty(string2, string);
        return jsonObject2;
    }

    private static HttpResponse<String> post(String string, String string2) throws Exception {
        String[] stringArray = a;
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(CosmeticsSync.baseUrl() + string)).timeout(Duration.ofSeconds(ProfileService.a(32281, 9000669668420983086L))).header(stringArray[19], stringArray[20]).POST(HttpRequest.BodyPublishers.ofString(string2, StandardCharsets.UTF_8)).build();
        return HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    
    private static String detail(HttpResponse<String> httpResponse) {
        int n = Profile.a;
        try {
            String string2;
            String[] stringArray = a;
            String string = string2 = ProfileService.str(JsonParser.parseString((String)httpResponse.body()).getAsJsonObject(), stringArray[16]);
            if (n != 0) return string;
            if (string.isEmpty()) return a[12] + httpResponse.statusCode() + ")";
            string = string2;
            return string;
        }
        catch (Exception exception) {
        }
        return a[12] + httpResponse.statusCode() + ")";
    }

    private static <T> void reply(Consumer<T> consumer, T t, String string) {
        String string2;
        block8: {
            block9: {
                int n;
                block6: {
                    block7: {
                        n = Profile.a;
                        string2 = string;
                        if (n != 0) break block6;
                        if (string2 == null) break block7;
                        Aurora.LOG.debug(a[30], (Object)string);
                    }
                    string2 = string;
                }
                if (n != 0) break block8;
                if (string2 != null) break block9;
                string2 = "";
                break block8;
            }
            string2 = string;
        }
        status = string2;
        MinecraftClient.method_1551().execute(() -> ProfileService.lambda$reply$7(consumer, t));
    }

    private static String str(JsonObject jsonObject, String string) {
        String string2;
        block11: {
            block9: {
                JsonObject jsonObject2;
                block10: {
                    JsonObject jsonObject3;
                    block8: {
                        int n = Profile.a;
                        jsonObject3 = jsonObject;
                        if (n != 0) break block8;
                        if (!jsonObject3.has(string)) break block9;
                        jsonObject2 = jsonObject;
                        if (n != 0) break block10;
                        jsonObject3 = jsonObject2.get(string);
                    }
                    if (jsonObject3.isJsonNull()) break block9;
                    jsonObject2 = jsonObject.get(string);
                }
                string2 = jsonObject2.getAsString();
                break block11;
            }
            string2 = "";
        }
        return string2;
    }

    private static String enc(String string) {
        String string2;
        block2: {
            block3: {
                int n = Profile.a;
                string2 = string;
                if (n != 0) break block2;
                if (string2 != null) break block3;
                string2 = "";
                break block2;
            }
            string2 = string;
        }
        return URLEncoder.encode(string2, StandardCharsets.UTF_8);
    }

    private static void run(Runnable runnable) {
        Thread thread = new Thread(runnable, a[9]);
        thread.setDaemon(true);
        thread.start();
    }

    private static void lambda$reply$7(Consumer consumer, Object object) {
        consumer.accept(object);
    }

    private static void lambda$forget$6(String string) {
        try {
            JsonObject jsonObject = ProfileService.authed();
            String[] stringArray = a;
            jsonObject.addProperty(stringArray[21], string);
            ProfileService.post(stringArray[32], jsonObject.toString());
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[34], (Throwable)exception);
        }
    }

    private static void lambda$publicList$5(String string, String string2, Consumer consumer) {
        int n = Profile.a;
        try {
            Object object;
            String[] stringArray;
            block4: {
                HttpResponse<String> httpResponse;
                block5: {
                    stringArray = a;
                    String string3 = CosmeticsSync.baseUrl() + stringArray[4] + ProfileService.enc(string) + stringArray[3] + ProfileService.enc(string2);
                    HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(string3)).timeout(Duration.ofSeconds(ProfileService.a(17679, 174749240516158011L))).GET().build();
                    httpResponse = HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                    object = httpResponse;
                    if (n != 0) break block4;
                    if (object.statusCode() / 100 == 2) break block5;
                    ProfileService.reply(consumer, List.of(), ProfileService.detail(httpResponse));
                    return;
                }
                object = httpResponse.body();
            }
            stringArray = a;
            List<ProfileService$Listed> list = ProfileService.parse(JsonParser.parseString((String)((String)object)).getAsJsonObject().getAsJsonArray(stringArray[5]));
            MinecraftClient.method_1551().execute(() -> ProfileService.lambda$publicList$4(consumer, list));
        }
        catch (Exception exception) {
            String[] stringArray = a;
            Aurora.LOG.warn(stringArray[18], (Throwable)exception);
            ProfileService.reply(consumer, List.of(), stringArray[17]);
        }
    }

    private static void lambda$publicList$4(Consumer consumer, List list) {
        consumer.accept(list);
    }

    
    private static void lambda$fetch$3(String string, Consumer consumer) {
        int n = Profile.a;
        try {
            Object object;
            String[] stringArray;
            block10: {
                HttpResponse<String> httpResponse;
                block11: {
                    String string2;
                    Object t;
                    Consumer consumer2;
                    block14: {
                        HttpResponse<String> httpResponse2;
                        block12: {
                            block13: {
                                block17: {
                                    block16: {
                                        block15: {
                                            stringArray = a;
                                            HttpRequest httpRequest = CosmeticsSync.bearer(HttpRequest.newBuilder(URI.create(CosmeticsSync.baseUrl() + stringArray[28] + string.trim().toUpperCase(Locale.ROOT))).timeout(Duration.ofSeconds(ProfileService.a(23217, 2963371409916968324L))).GET()).build();
                                            httpResponse = HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                                            object = httpResponse;
                                            if (n != 0) break block10;
                                            if (object.statusCode() / 100 == 2) break block11;
                                            break block15;
                                            catch (Exception exception) {
                                                throw exception;
                                            }
                                        }
                                        consumer2 = consumer;
                                        t = null;
                                        httpResponse2 = httpResponse;
                                        if (n != 0) break block12;
                                        break block16;
                                        catch (Exception exception) {
                                            throw exception;
                                        }
                                    }
                                    if (httpResponse2.statusCode() != 404) break block13;
                                    break block17;
                                    catch (Exception exception) {
                                        throw exception;
                                    }
                                }
                                string2 = a[35];
                                break block14;
                            }
                            httpResponse2 = httpResponse;
                        }
                        string2 = ProfileService.detail(httpResponse2);
                    }
                    ProfileService.reply(consumer2, t, string2);
                    return;
                }
                object = httpResponse.body();
            }
            JsonObject jsonObject = JsonParser.parseString((String)((String)object)).getAsJsonObject();
            JsonObject jsonObject2 = new JsonObject();
            stringArray = a;
            jsonObject2.addProperty(stringArray[27], ProfileService.str(jsonObject, stringArray[27]));
            jsonObject2.addProperty(stringArray[21], ProfileService.str(jsonObject, stringArray[21]));
            jsonObject2.addProperty(stringArray[2], ProfileService.str(jsonObject, stringArray[2]));
            jsonObject2.add(stringArray[1], JsonParser.parseString((String)ProfileService.str(jsonObject, stringArray[1])));
            MinecraftClient.method_1551().execute(() -> ProfileService.lambda$fetch$2(consumer, jsonObject2));
        }
        catch (Exception exception) {
            String[] stringArray = a;
            Aurora.LOG.warn(stringArray[25], (Throwable)exception);
            ProfileService.reply(consumer, null, stringArray[17]);
        }
    }

    private static void lambda$fetch$2(Consumer consumer, JsonObject jsonObject) {
        consumer.accept(jsonObject);
    }

    private static void lambda$share$1(Profile profile, Consumer consumer) {
        int n = Profile.a;
        try {
            Object object;
            String[] stringArray;
            block4: {
                HttpResponse<String> httpResponse;
                block5: {
                    JsonObject jsonObject = ProfileService.authed();
                    stringArray = a;
                    jsonObject.addProperty(stringArray[21], profile.code());
                    jsonObject.addProperty(stringArray[27], profile.name());
                    jsonObject.addProperty(stringArray[2], profile.summary());
                    jsonObject.addProperty(stringArray[26], ProfileManager.shareable(profile.data()).toString());
                    httpResponse = ProfileService.post(stringArray[23], jsonObject.toString());
                    object = httpResponse;
                    if (n != 0) break block4;
                    if (object.statusCode() / 100 == 2) break block5;
                    ProfileService.reply(consumer, null, ProfileService.detail(httpResponse));
                    return;
                }
                object = httpResponse.body();
            }
            stringArray = a;
            String string = JsonParser.parseString((String)((String)object)).getAsJsonObject().get(stringArray[21]).getAsString();
            MinecraftClient.method_1551().execute(() -> ProfileService.lambda$share$0(profile, string, consumer));
        }
        catch (Exception exception) {
            String[] stringArray = a;
            Aurora.LOG.warn(stringArray[14], (Throwable)exception);
            ProfileService.reply(consumer, null, stringArray[6]);
        }
    }

    private static void lambda$share$0(Profile profile, String string, Consumer consumer) {
        ProfileManager.withCode(profile, string);
        consumer.accept(string);
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[36];
                        var11_1 = 0;
                        var10_2 = "7#%E#!l51\u0004<5?A\u0007+!&M0\u001dt\u0003~%v\u001aw5;I~\u001f\u007f72\"L4\u001c\"(!)L8\f2+;9Tl\b(&$F8\u0003h+\u001b\u001b;>L5Oc7 kR4\u000en0t?H4O~=&=I2\n\u00054;*D\"\u000551/I0\u000f\u0019!9O#\u000e \b&$F8\u0003h+\u000551/I0\u00054;*D\"\u0013\f<*Tq\u000bd<t%O%Oz7& \u0000y\u00057#%E#\u0018\u000b<*R8\u0001jx5kP#\u0000k18.\u00007\u000ed41/\u0007-$/A%\ni\u0006<1?A8\u0003\u001b\u001b;>L5Oc7 kR4\u000en0t?H4O~=&=I2\n\u001e\u0014=8T8\u0001jx$>B=\u0006nx$9O7\u0006a='kF0\u0006a=0\f\u001b;%T4\u0001yu\u00002P4\u00109$;L8\fl,=$N~\u0005~7:\u0004;;/E\u0007-$/A%\ni\u0011w5;I~\u001f\u007f72\"L4\u001c\"(!?\u0004;;/E\u0019\u001e1?C9\u0006c?t*\u0000!\u001db>='Eq\tl18.D\u0004<5?A\u000465&E\u000ew5;I~\u001f\u007f72\"L4\u001c\"\u0007+!&M0\u001dt\f\b&$F8\u0003h+nk[,\u0005,; E?\u0014w5;I~\u001f\u007f72\"L4\u001c\"<1'E%\n\u000465&E";
                        var12_3 = "7#%E#!l51\u0004<5?A\u0007+!&M0\u001dt\u0003~%v\u001aw5;I~\u001f\u007f72\"L4\u001c\"(!)L8\f2+;9Tl\b(&$F8\u0003h+\u001b\u001b;>L5Oc7 kR4\u000en0t?H4O~=&=I2\n\u00054;*D\"\u000551/I0\u000f\u0019!9O#\u000e \b&$F8\u0003h+\u000551/I0\u00054;*D\"\u0013\f<*Tq\u000bd<t%O%Oz7& \u0000y\u00057#%E#\u0018\u000b<*R8\u0001jx5kP#\u0000k18.\u00007\u000ed41/\u0007-$/A%\ni\u0006<1?A8\u0003\u001b\u001b;>L5Oc7 kR4\u000en0t?H4O~=&=I2\n\u001e\u0014=8T8\u0001jx$>B=\u0006nx$9O7\u0006a='kF0\u0006a=0\f\u001b;%T4\u0001yu\u00002P4\u00109$;L8\fl,=$N~\u0005~7:\u0004;;/E\u0007-$/A%\ni\u0011w5;I~\u001f\u007f72\"L4\u001c\"(!?\u0004;;/E\u0019\u001e1?C9\u0006c?t*\u0000!\u001db>='Eq\tl18.D\u0004<5?A\u000465&E\u000ew5;I~\u001f\u007f72\"L4\u001c\"\u0007+!&M0\u001dt\f\b&$F8\u0003h+nk[,\u0005,; E?\u0014w5;I~\u001f\u007f72\"L4\u001c\"<1'E%\n\u000465&E".length();
                        var9_4 = 9;
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
                            var10_2 = "\u001c1'E%\u0006c?t*\u0000\"\u0007l*1/\u0000!\u001db>='Eq\tl18.D\u0019\u0016;kP#\u0000k18.\u0000&\u0006y0t?H0\u001b-;;/E";
                            var12_3 = "\u001c1'E%\u0006c?t*\u0000\"\u0007l*1/\u0000!\u001db>='Eq\tl18.D\u0019\u0016;kP#\u0000k18.\u0000&\u0006y0t?H0\u001b-;;/E".length();
                            var9_4 = 32;
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
                                v10 = 88;
                                break;
                            }
                            case 1: {
                                v10 = 84;
                                break;
                            }
                            case 2: {
                                v10 = 75;
                                break;
                            }
                            case 3: {
                                v10 = 32;
                                break;
                            }
                            case 4: {
                                v10 = 81;
                                break;
                            }
                            case 5: {
                                v10 = 111;
                                break;
                            }
                            default: {
                                v10 = 13;
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
                ProfileService.a = var13;
                var0_7 = 8907023738682951064L;
                var6_8 = new long[4];
                var3_9 = 0;
                var4_10 = "R\u00bc\u0018~\u00df\u008dx\u0013y\u00f0\u00cfd;\u0097g\u00ac";
                var5_11 = "R\u00bc\u0018~\u00df\u008dx\u0013y\u00f0\u00cfd;\u0097g\u00ac".length();
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
                    var4_10 = "\u0007t\u00d7\u0086(\u00f6\u00dc\u00a2P+\u00fe\u00e2\u00aeS3@";
                    var5_11 = "\u0007t\u00d7\u0086(\u00f6\u00dc\u00a2P+\u00fe\u00e2\u00aeS3@".length();
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
        ProfileService.b = var6_8;
        ProfileService.c = new Long[4];
        ProfileService.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(ProfileService.a(4580, 3150237173160638162L))).build();
        ProfileService.status = "";
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x6735) & Short.MAX_VALUE;
        if (c[n2] == null) {
            ProfileService.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
