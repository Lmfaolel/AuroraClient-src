package dev.quelchi.aurora.auth;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.auth.MsAuth$Account;
import dev.quelchi.aurora.auth.MsAuth$AuthException;
import dev.quelchi.aurora.auth.MsAuth$DeviceCode;
import dev.quelchi.aurora.auth.MsAuth$SignInAgain;
import dev.quelchi.aurora.module.Module;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import org.slf4j.Logger;

public final class MsAuth {
    private static final String CLIENT_ID;
    private static final String SCOPE;
    private static final String DEVICE_URL;
    private static final String TOKEN_URL;
    private static final String XBL_URL;
    private static final String XSTS_URL;
    private static final String MC_LOGIN;
    private static final String MC_PROFILE;
    private static final HttpClient HTTP;
    public static int a;
    private static final String[] b;
    private static final long[] c;
    private static final Long[] d;

    private MsAuth() {
    }

    public static MsAuth$DeviceCode begin() throws Exception {
        String string;
        String string2;
        String string3;
        MsAuth$DeviceCode msAuth$DeviceCode;
        MsAuth$DeviceCode msAuth$DeviceCode2;
        JsonObject jsonObject;
        String[] stringArray;
        block6: {
            block5: {
                MsAuth$DeviceCode msAuth$DeviceCode3;
                String[] stringArray2 = new String[6];
                stringArray = b;
                stringArray2[0] = stringArray[27];
                stringArray2[1] = stringArray[90];
                stringArray2[2] = stringArray[117];
                stringArray2[3] = stringArray[116];
                stringArray2[4] = stringArray[81];
                stringArray2[5] = stringArray[28];
                String string4 = MsAuth.form(stringArray2);
                jsonObject = MsAuth.postForm(stringArray[50], string4);
                if (!jsonObject.has(stringArray[15])) {
                    throw new MsAuth$AuthException(MsAuth.describe(jsonObject));
                }
                msAuth$DeviceCode2 = msAuth$DeviceCode3;
                msAuth$DeviceCode = msAuth$DeviceCode3;
                string3 = jsonObject.get(b[15]).getAsString();
                string2 = jsonObject.get(b[93]).getAsString();
                if (!jsonObject.has(b[96])) break block5;
                string = jsonObject.get(b[111]).getAsString();
                break block6;
            }
            stringArray = b;
            string = stringArray[18];
        }
        stringArray = b;
        int n = jsonObject.has(stringArray[49]) ? jsonObject.get(stringArray[0]).getAsInt() : 5;
        stringArray = b;
        msAuth$DeviceCode2(string3, string2, string, n, System.currentTimeMillis() + (jsonObject.has(stringArray[6]) ? jsonObject.get(stringArray[99]).getAsLong() : MsAuth.a(27979, 988297507933743915L)) * MsAuth.a(21476, 4067424341454607746L));
        return msAuth$DeviceCode;
    }

    
    public static Optional<MsAuth$Account> tryComplete(MsAuth$DeviceCode var0) throws Exception {
        block19: {
            block21: {
                block22: {
                    block20: {
                        block26: {
                            block25: {
                                v0 = new String[6];
                                var6_1 = MsAuth.b;
                                v0[0] = var6_1[74];
                                v0[1] = var6_1[89];
                                v0[2] = var6_1[15];
                                v0[3] = var0.deviceCode();
                                v0[4] = var6_1[40];
                                v0[5] = var6_1[113];
                                var2_2 = MsAuth.form(v0);
                                var3_3 = MsAuth.rawPostForm(var6_1[54], var2_2);
                                var4_4 = JsonParser.parseString((String)var3_3.body()).getAsJsonObject();
                                var1_5 = MsAuth.a;
                                v1 = var4_4;
                                if (var1_5 == 0) {
                                    if (!v1.has(var6_1[100])) break block19;
                                }
                                var6_1 = MsAuth.b;
                                var5_6 = var4_4.get(var6_1[100]).getAsString();
                                v3 = var6_1[119];
                                v4 = var5_6;
                                if (var1_5 != 0) break block20;
                                if (v3.equals(v4)) // goto
                                break block25;
                                catch (MsAuth$AuthException v5) {
                                    throw v5;
                                }
                            }
                            v3 = MsAuth.b[70];
                            if (var1_5 != 0) break block21;
                            break block26;
                            catch (MsAuth$AuthException v6) {
                                throw v6;
                            }
                        }
                        v4 = var5_6;
                    }
                    if (!v3.equals(v4)) break block22;
lbl47:

                    return Optional.empty();
                }
                v3 = var5_6;
            }
            throw MsAuth.deviceError(v3);
        }
        try {
            block28: {
                block24: {
                    block23: {
                        block27: {
                            var6_1 = MsAuth.b;
                            v1 = var4_4.get(var6_1[57]);
lbl59:

                            v9 = v1.getAsString();
                            v10 = var4_4;
                            if (var1_5 != 0) break block23;
                            if (!v10.has(MsAuth.b[25])) break block24;
                            break block27;
                            catch (MsAuth$AuthException v11) {
                                throw v11;
                            }
                        }
                        v10 = var4_4.get(MsAuth.b[25]);
                    }
                    v13 = v10.getAsString();
                    break block28;
                }
                v13 = null;
            }
            return Optional.of(MsAuth.finishFrom(v9, v13));
        }
        catch (MsAuth$AuthException var5_7) {
            throw var5_7;
        }
        catch (Exception var5_8) {
            var6_1 = MsAuth.b;
            throw new MsAuth$AuthException(var6_1[36] + var5_8.getClass().getSimpleName() + var6_1[76]);
        }
    }

    
    static MsAuth$AuthException deviceError(String string) {
        MsAuth$AuthException msAuth$AuthException;
        int n;
        block24: {
            String string2 = string;
            int n2 = a;
            int n3 = -1;
            n = string2.hashCode();
            if (n2 != 0) break block24;
            switch (n) {
                case 1612125279: {
                    n = string2.equals(b[87]) ? 1 : 0;
                    if (n2 == 0) {
                        if (n == 0) break;
                        n3 = 0;
                        if (n2 == 0) break;
                    }
                    break block24;
                }
                case -1290617133: {
                    n = string2.equals(b[5]) ? 1 : 0;
                    if (n2 == 0) {
                        if (n == 0) break;
                        n3 = 1;
                        if (n2 == 0) break;
                    }
                    break block24;
                }
                case 967558452: {
                    n = string2.equals(b[44]) ? 1 : 0;
                    if (n2 == 0) {
                        if (n == 0) break;
                        n3 = 2;
                        if (n2 == 0) break;
                    }
                    break block24;
                }
                case -847806252: {
                    n = string2.equals(b[102]) ? 1 : 0;
                    if (n2 != 0) break block24;
                    if (n == 0) break;
                    n3 = 3;
                }
            }
            n = n3;
        }
        switch (n) {
            case 0: 
            case 1: {
                msAuth$AuthException = new MsAuth$AuthException(b[48]);
                return msAuth$AuthException;
            }
            case 2: {
                String[] stringArray = b;
                MsAuth$AuthException msAuth$AuthException2 = new MsAuth$AuthException(stringArray[85]);
                msAuth$AuthException = msAuth$AuthException2;
                return msAuth$AuthException;
            }
            case 3: {
                String[] stringArray = b;
                MsAuth$AuthException msAuth$AuthException3 = new MsAuth$AuthException(stringArray[98]);
                msAuth$AuthException = msAuth$AuthException3;
                return msAuth$AuthException;
            }
        }
        msAuth$AuthException = new MsAuth$AuthException(b[7] + string);
        return msAuth$AuthException;
    }

    public static MsAuth$Account refresh(String string, Consumer<String> consumer) throws Exception {
        String string2;
        String string3;
        block16: {
            JsonObject jsonObject;
            block17: {
                String string4;
                int n;
                block18: {
                    block15: {
                        JsonObject jsonObject2;
                        block14: {
                            block12: {
                                block13: {
                                    String[] stringArray = new String[8];
                                    String[] stringArray2 = b;
                                    stringArray[0] = stringArray2[74];
                                    stringArray[1] = stringArray2[89];
                                    stringArray[2] = stringArray2[47];
                                    stringArray[3] = string;
                                    stringArray[4] = stringArray2[72];
                                    stringArray[5] = stringArray2[25];
                                    stringArray[6] = stringArray2[59];
                                    stringArray[7] = stringArray2[66];
                                    String string5 = MsAuth.form(stringArray);
                                    jsonObject = MsAuth.postForm(stringArray2[41], string5);
                                    n = a;
                                    jsonObject2 = jsonObject;
                                    if (n != 0) break block12;
                                    if (!jsonObject2.has(stringArray2[100])) break block13;
                                    throw MsAuth.refreshError(jsonObject);
                                }
                                jsonObject2 = jsonObject;
                            }
                            if (n != 0) break block14;
                            if (!jsonObject2.has(b[25])) break block15;
                            jsonObject2 = jsonObject.get(b[25]);
                        }
                        string4 = jsonObject2.getAsString();
                        break block18;
                    }
                    string4 = string;
                }
                string3 = string4;
                string2 = string3;
                if (n != 0) break block16;
                if (string2.equals(string)) break block17;
                consumer.accept(string3);
            }
            string2 = jsonObject.get(b[86]).getAsString();
        }
        return MsAuth.finishFrom(string2, string3);
    }

    static MsAuth$AuthException refreshError(JsonObject jsonObject) {
        block8: {
            boolean bl;
            block7: {
                int n = a;
                bl = jsonObject.has(b[100]);
                if (n != 0) break block7;
                if (!bl) break block8;
                bl = b[80].equals(jsonObject.get(b[100]).getAsString());
            }
            if (bl) {
                return new MsAuth$SignInAgain();
            }
        }
        return new MsAuth$AuthException(MsAuth.describe(jsonObject));
    }

    
    static MsAuth$Account finishFrom(String var0, String var1_1) throws Exception {
        block31: {
            block30: {
                var3_2 = MsAuth.xboxAuth(var0, false);
                var2_3 = MsAuth.a;
                v0 = var3_2;
                if (var2_3 == 0) {
                    if (v0 == null) {
                        var3_2 = MsAuth.xboxAuth(var0, true);
                    }
                    v0 = var3_2;
                }
                if (var2_3 == 0) {
                    if (v0 == null) {
                        throw new MsAuth$AuthException(MsAuth.b[103]);
                    }
                    var21_4 = MsAuth.b;
                    v0 = var3_2.get(var21_4[75]);
                }
                var4_5 = v0.getAsString();
                var21_4 = MsAuth.b;
                var5_6 = var3_2.getAsJsonObject(var21_4[17]).getAsJsonArray(var21_4[53]).get(0).getAsJsonObject().get(var21_4[52]).getAsString();
                var6_7 = new JsonObject();
                var7_8 = new JsonObject();
                var7_8.addProperty(var21_4[104], var21_4[107]);
                var8_9 = new JsonArray();
                var8_9.add(var4_5);
                var7_8.add(var21_4[55], (JsonElement)var8_9);
                var6_7.add(var21_4[69], (JsonElement)var7_8);
                var6_7.addProperty(var21_4[61], var21_4[109]);
                var6_7.addProperty(var21_4[34], var21_4[97]);
                var9_10 = MsAuth.rawPostJson(var21_4[94], var6_7.toString());
                var10_11 = JsonParser.parseString((String)var9_10.body()).getAsJsonObject();
                v1 = var9_10.statusCode();
                if (var2_3 != 0) break block30;
                if (v1 / 100 == 2) break block31;
                v2 = var10_11;
                if (var2_3 != 0) // goto
                v1 = (int)v2.has(MsAuth.b[43]);
            }
            if (v1 != 0) {
                v2 = var10_11.get(MsAuth.b[23]);
lbl36:

                v3 = v2.getAsLong();
            } else {
                v3 = 0L;
            }
            var11_12 = v3;
            switch ((int)(var11_12 % MsAuth.a(1525, 934929505627734932L))) {
                case 16233: {
                    v4 = MsAuth.b[68];
                    throw new MsAuth$AuthException((String)v4);
                }
                case 16235: {
                    var21_4 = MsAuth.b;
                    v4 = var21_4[38];
                    throw new MsAuth$AuthException((String)v4);
                }
                case 16238: {
                    var21_4 = MsAuth.b;
                    v4 = var21_4[95];
                    throw new MsAuth$AuthException((String)v4);
                }
            }
            var21_4 = MsAuth.b;
            v4 = var21_4[30] + var9_10.statusCode() + var21_4[14];
            throw new MsAuth$AuthException((String)v4);
        }
        var21_4 = MsAuth.b;
        var11_13 = var10_11.get(var21_4[3]).getAsString();
        var12_14 = "";
        try {
            var12_14 = var10_11.getAsJsonObject(var21_4[64]).getAsJsonArray(var21_4[51]).get(0).getAsJsonObject().get(var21_4[20]).getAsString();
        }
        catch (Exception var13_15) {
        }
        var13_16 = new JsonObject();
        var21_4 = MsAuth.b;
        var13_16.addProperty(var21_4[31], var21_4[21] + var5_6 + ";" + var11_13);
        var14_17 = MsAuth.postJson(var21_4[33], var21_4[71], var13_16.toString());
        var15_18 = var14_17.get(var21_4[57]).getAsString();
        var16_19 = HttpRequest.newBuilder(URI.create(var21_4[118])).header(var21_4[56], var21_4[73] + var15_18).timeout(Duration.ofSeconds(MsAuth.a(28912, 4293753681074124436L))).GET().build();
        var17_20 = MsAuth.HTTP.send(var16_19, HttpResponse.BodyHandlers.ofString());
        v5 = var17_20;
        if (var2_3 == 0) {
            if (v5.statusCode() == 404) {
                throw new MsAuth$AuthException(MsAuth.b[1]);
            }
            v5 = var17_20;
        }
        if (var2_3 == 0) {
            if (v5.statusCode() / 100 != 2) {
                throw new MsAuth$AuthException(MsAuth.b[35]);
            }
            v5 = var17_20.body();
        }
        var18_21 = JsonParser.parseString((String)((String)v5)).getAsJsonObject();
        var21_4 = MsAuth.b;
        var19_22 = MsAuth.dashed(var18_21.get(var21_4[8]).getAsString());
        var20_23 = var18_21.get(var21_4[63]).getAsString();
        Aurora.LOG.info(var21_4[12], (Object)var20_23);
        v6 = new MsAuth$Account(var19_22, var20_23, var15_18, var1_1, var12_14);
        if (Module.a == false) return v6;
        MsAuth.a = ++var2_3;
        return v6;
    }

    private static UUID dashed(String string) {
        Object object;
        block4: {
            block5: {
                int n = a;
                object = string;
                if (n != 0) break block4;
                if (((String)object).length() == 32) break block5;
                return UUID.fromString(string);
            }
            object = string.substring(0, 8) + "-" + string.substring(8, 12) + "-" + string.substring(12, 16) + "-" + string.substring(16, 20) + "-" + string.substring(20);
        }
        return UUID.fromString((String)object);
    }

    static String form(String ... stringArray) {
        StringBuilder stringBuilder = new StringBuilder();
        int n = a;
        for (int i = 0; i < stringArray.length; i += 2) {
            if (i > 0) {
                stringBuilder.append('&');
            }
            stringBuilder.append(URLEncoder.encode(stringArray[i], StandardCharsets.UTF_8)).append('=').append(URLEncoder.encode(stringArray[i + 1], StandardCharsets.UTF_8));
            if (n == 0) continue;
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static JsonObject xboxAuth(String string, boolean bl) throws Exception {
        int n;
        int n2;
        HttpResponse<String> httpResponse;
        block17: {
            block18: {
                int n3;
                block16: {
                    Object object;
                    String string2;
                    JsonObject jsonObject;
                    JsonObject jsonObject2;
                    block14: {
                        block15: {
                            jsonObject2 = new JsonObject();
                            n3 = a;
                            jsonObject = jsonObject2;
                            string2 = b[88];
                            object = b[26];
                            if (n3 != 0) break block14;
                            jsonObject.addProperty(string2, (String)object);
                            jsonObject2.addProperty(b[9], b[45]);
                            jsonObject = jsonObject2;
                            string2 = b[32];
                            if (!bl) break block15;
                            object = b[4] + string;
                            break block14;
                        }
                        object = string;
                    }
                    jsonObject.addProperty(string2, (String)object);
                    JsonObject jsonObject3 = new JsonObject();
                    String[] stringArray = b;
                    jsonObject3.add(stringArray[91], (JsonElement)jsonObject2);
                    jsonObject3.addProperty(stringArray[10], stringArray[108]);
                    jsonObject3.addProperty(stringArray[19], stringArray[13]);
                    httpResponse = MsAuth.rawPostJson(stringArray[46], jsonObject3.toString());
                    n2 = httpResponse.statusCode();
                    n = n2;
                    if (n3 != 0) break block16;
                    if (n == 400) return null;
                    n = n2;
                }
                if (n3 != 0) break block17;
                if (n != 401) break block18;
                return null;
            }
            n = n2 / 100;
        }
        if (n == 2) return JsonParser.parseString((String)httpResponse.body()).getAsJsonObject();
        throw new MsAuth$AuthException(b[121] + n2 + ")");
    }

    private static String describe(JsonObject jsonObject) {
        block18: {
            JsonObject jsonObject2;
            block17: {
                int n;
                block12: {
                    block13: {
                        Object object;
                        block16: {
                            int n2;
                            String string;
                            block14: {
                                block15: {
                                    n = a;
                                    jsonObject2 = jsonObject;
                                    if (n != 0) break block12;
                                    if (!jsonObject2.has(b[60])) break block13;
                                    String[] stringArray = b;
                                    string = jsonObject.get(stringArray[42]).getAsString();
                                    int n3 = string.indexOf(10);
                                    n2 = n3;
                                    if (n != 0) break block14;
                                    if (n2 <= 0) break block15;
                                    string = string.substring(0, n3);
                                }
                                object = string;
                                if (n != 0) break block16;
                                n2 = ((String)object).length();
                            }
                            object = n2 > 90 ? string.substring(0, 90) + "\u2026" : string;
                        }
                        return object;
                    }
                    jsonObject2 = jsonObject;
                }
                if (n != 0) break block17;
                if (!jsonObject2.has(b[100])) break block18;
                jsonObject2 = jsonObject.get(b[100]);
            }
            String[] stringArray = b;
            return stringArray[78] + jsonObject2.getAsString() + stringArray[101];
        }
        return b[120];
    }

    static JsonObject postFormChecked(String string, String string2) throws Exception {
        JsonObject jsonObject;
        block4: {
            JsonObject jsonObject2;
            block5: {
                jsonObject2 = MsAuth.postForm(string, string2);
                int n = a;
                jsonObject = jsonObject2;
                if (n != 0) break block4;
                if (!jsonObject.has(b[114])) break block5;
                throw new MsAuth$AuthException(MsAuth.describe(jsonObject2));
            }
            jsonObject = jsonObject2;
        }
        return jsonObject;
    }

    private static HttpResponse<String> rawPostForm(String string, String string2) throws Exception {
        String[] stringArray = b;
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(string)).header(stringArray[112], stringArray[106]).timeout(Duration.ofSeconds(MsAuth.a(24495, 6678986968905505226L))).POST(HttpRequest.BodyPublishers.ofString(string2, StandardCharsets.UTF_8)).build();
        return HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private static JsonObject postForm(String string, String string2) throws Exception {
        return JsonParser.parseString((String)MsAuth.rawPostForm(string, string2).body()).getAsJsonObject();
    }

    private static HttpResponse<String> rawPostJson(String string, String string2) throws Exception {
        String[] stringArray = b;
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(string)).header(stringArray[11], stringArray[105]).header(stringArray[115], stringArray[39]).timeout(Duration.ofSeconds(MsAuth.a(28912, 4293753681074124436L))).POST(HttpRequest.BodyPublishers.ofString(string2, StandardCharsets.UTF_8)).build();
        return HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private static JsonObject postJson(String string, String string2, String string3) throws Exception {
        Object object;
        block31: {
            HttpResponse<String> httpResponse;
            block32: {
                block40: {
                    String string4;
                    block44: {
                        Object object2;
                        block42: {
                            block43: {
                                String string5;
                                block41: {
                                    int n;
                                    int n2;
                                    block39: {
                                        HttpResponse<String> httpResponse2;
                                        block37: {
                                            block38: {
                                                String string6;
                                                Object[] objectArray;
                                                String string7;
                                                Logger logger;
                                                block33: {
                                                    HttpResponse<String> httpResponse3;
                                                    block35: {
                                                        block36: {
                                                            block34: {
                                                                httpResponse = MsAuth.rawPostJson(string2, string3);
                                                                n2 = a;
                                                                object = httpResponse;
                                                                if (n2 != 0) break block31;
                                                                if (object.statusCode() / 100 == 2) break block32;
                                                                logger = Aurora.LOG;
                                                                string7 = b[77];
                                                                Object[] objectArray2 = new Object[3];
                                                                Object[] objectArray3 = objectArray2;
                                                                objectArray = objectArray2;
                                                                int n3 = 0;
                                                                string6 = string;
                                                                if (n2 != 0) break block33;
                                                                objectArray3[n3] = string6;
                                                                objectArray[1] = httpResponse.statusCode();
                                                                objectArray3 = objectArray;
                                                                objectArray = objectArray;
                                                                n3 = 2;
                                                                if (httpResponse.body() == null) break block34;
                                                                httpResponse3 = httpResponse;
                                                                if (n2 != 0) break block35;
                                                                if (httpResponse3.body().length() <= 600) break block36;
                                                            }
                                                            string6 = b[16];
                                                            break block33;
                                                        }
                                                        httpResponse3 = httpResponse;
                                                    }
                                                    string6 = httpResponse3.body();
                                                }
                                                objectArray3[n3] = string6;
                                                logger.warn(string7, objectArray);
                                                httpResponse2 = httpResponse;
                                                if (n2 != 0) break block37;
                                                if (httpResponse2.statusCode() != 429) break block38;
                                                throw new MsAuth$AuthException(b[58] + MsAuth.retryHint(httpResponse) + b[2]);
                                            }
                                            httpResponse2 = httpResponse;
                                        }
                                        n = httpResponse2.statusCode();
                                        if (n2 != 0) break block39;
                                        if (n != 403) break block40;
                                        string5 = b[65];
                                        if (n2 != 0) break block41;
                                        n = string5.equals(string) ? 1 : 0;
                                    }
                                    if (n == 0) break block40;
                                    object2 = httpResponse;
                                    if (n2 != 0) break block42;
                                    string5 = object2.body();
                                }
                                if (string5 != null) break block43;
                                string4 = "";
                                break block44;
                            }
                            object2 = httpResponse.body();
                        }
                        string4 = (String)object2;
                    }
                    String string8 = string4;
                    if (string8.contains(b[67])) {
                        throw new MsAuth$AuthException(b[83]);
                    }
                    String[] stringArray = b;
                    throw new MsAuth$AuthException(stringArray[92]);
                }
                throw new MsAuth$AuthException(string + b[79] + httpResponse.statusCode() + ")");
            }
            object = httpResponse.body();
        }
        return JsonParser.parseString((String)((String)object)).getAsJsonObject();
    }

    private static String retryHint(HttpResponse<String> httpResponse) {
        return httpResponse.headers().firstValue(b[82]).map(MsAuth::lambda$retryHint$0).orElse("");
    }

    private static String lambda$retryHint$0(String string) {
        try {
            String[] stringArray = b;
            return stringArray[84] + Integer.parseInt(string.trim()) + stringArray[62];
        }
        catch (NumberFormatException numberFormatException) {
            return "";
        }
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[122];
                        var11_1 = 0;
                        var10_2 = "\u0001~\u0005\u000bp\u0015D\u0004$<x\u0010\u001a\"\u0002F\u000b\u007f\u0004\u0000vCA\u0007u\u0002Nl\fQH\u007f\u0006\u0000\".L\u0006u\u0012\u001cc\u0005QF\u000fHq\u001f\n\"\u0017W\u00110\u0010\tc\nKF\u0005<\u007f\u001a\u000bl\u0002\f-\f\u000b\u007f\u0015\u000b]\u0006]\u0018y\u0003\u000bf\n\rh\u0001\u0007p\u0006V7y\u001f\u0010%y\u0012\u001cm\u0010J\u000edQ\u001dc\nAR0\u0002\u0001t\b;y\u0005\u000bL\u0002H\r\f:u\u001d\u0017k\rB8q\u0003\u001a{\f+\u007f\u001f\u001ag\rQED\b\u001eg\u000f;y\u0016\u0000g\u0007\u0005\u0001~Q\u000fqC^\u0015\u0003\"G%\u0002A>\u000b\fu\u0007\u0007a\u0006z\u000b\u007f\u0015\u000b\t@\u007f\u001c\u0007v\u0017@\f9\r,y\u0002\u001en\u0002\\+|\u0010\u0007o\u0010\u001e\u0000d\u0005\u001eqY\nGg\u0006\u0019,\u000eL\u000bb\u001e\u001dm\u0005QFs\u001e\u0003-\u000fL\u0006{\t<\u007f\u001a\u000bl7\\\u0018u\u0003\u0010y\u0015\t0R=],S\u0005\u0010-@\u0000d\u0005\u001eqY\nGq\u0001\u0007,\u000eL\u0006u\u0012\u001cc\u0005Q\u001bu\u0003\u0018k\u0000@\u001b>\u0012\u0001oLD\u001dd\u0019\u000bl\u0017L\u000bq\u0005\u0007m\r\n\u0004\u007f\u0016\u0007l<R\u0001d\u00191z\u0001J\u0010\u00040U\u0003\u001c0\u0000d\u0005\u001eqY\nGe\u0002\u000bpMD\u001dd\u0019@z\u0001J\u0010|\u0018\u0018gMF\u0007}^\u001bq\u0006WGq\u0004\u001aj\u0006K\u001cy\u0012\u000fv\u0006\r\u001au\u0017\u001cg\u0010M7d\u001e\u0005g\r\u0003:@\"\t\u000b|\u0018\u000bl\u0017z\u0001t\u000b\fu\u0007\u0007a\u0006z\u000b\u007f\u0015\u000b-\u0000d\u0005\u001eqY\nGh\u0002\u001aqMD\u001dd\u0019@z\u0001J\u0010|\u0018\u0018gMF\u0007}^\u0016q\u0017VGq\u0004\u001aj\fW\u0001j\u0014\u001b0r\u001e\u0016\"\u0011@\u0002u\u0012\u001ag\u0007\u0005\u001cx\u0014Nq\nB\u0006=\u0018\u0000\"K\r\u0001t\u0014\u0000v\nQ\u0011D\u001e\u0005g\r\t:`\u0002:k\u0000N\rd\t%y\u001f\u000ba\u0011D\u000ed\t<\u007f\u001a\u000bl7\\\u0018u%+\u007f\u0004\u0002fCK\u0007dQ\u001cg\u0002AHd\u0019\u000b\".L\u0006u\u0012\u001cc\u0005QH`\u0003\u0001d\nI\r>\u0010;y\u0016\u0000/\nKHv\u0010\u0007n\u0006AH8*\u0000d\u0005\u001eqY\nG|\u001e\tk\r\u000b\u0004y\u0007\u000b,\u0000J\u0005?\u001e\u000fw\u0017MZ .\rm\rK\rs\u0005@q\u0011C40r\u001e\u0016\"/L\u001euQ\u0007qCK\u0007dQ\u000ft\u0002L\u0004q\u0013\u0002gCL\u00060\u0005\u0006c\u0017\u0005\ts\u0012\u0001w\rQOcQ\u001cg\u0004L\u0007~_\u0010\t`\u0001\u0002k\u0000D\u001cy\u001e\u0000-\tV\u0007~\n\u000fb\u0010\u0000v<Q\u0011`\u0014(\u0000d\u0005\u001eqY\nG|\u001e\tk\r\u000b\u0004y\u0007\u000b,\u0000J\u0005?\u001e\u000fw\u0017MZ .\u001am\b@\u0006>\u0002\u001cd\u0011\rb\u0003\u0001p<A\rc\u0012\u001ck\u0013Q\u0001\u007f\u001f\u00040U\u0003\u001c\u0016\te\u0005\u0006m\u0011L\u0012q\u0005\u0007m\rz\fu\u0012\u0002k\r@\f\u0016\u001dc\u0014\u001c,\u0002P\u001cx_\u0016`\f]\u0004y\u0007\u000b,\u0000J\u00050\u0000d\u0005\u001eqY\nGe\u0002\u000bpMD\u001dd\u0019@z\u0001J\u0010|\u0018\u0018gMF\u0007}^\u001bq\u0006WGq\u0004\u001aj\u0006K\u001cy\u0012\u000fv\u0006\r\u001au\u0017\u001cg\u0010M7d\u001e\u0005g\r\u001f<x\u0010\u001a\"\u0000J\fuQ\u000bz\u0013L\u001au\u0015@\"0Q\tb\u0005Nc\u0004D\u0001~_\b\u0001~\u0005\u000bp\u0015D\u0004*\u0000d\u0005\u001eqY\nG|\u001e\tk\r\u000b\u0004y\u0007\u000b,\u0000J\u0005?\u001e\u000fw\u0017MZ .\rm\rK\rs\u0005@q\u0011C\u0003\u0010e\u0018\u0003\u001dx\u0002\u0003\u0010e\u0018(\u0000d\u0005\u001eqY\nG|\u001e\tk\r\u000b\u0004y\u0007\u000b,\u0000J\u0005?\u001e\u000fw\u0017MZ .\u001am\b@\u0006>\u0002\u001cd\n=c\u0014\u001cV\fN\r~\u0002\r)e\u0005\u0006m\u0011L\u0012q\u0005\u0007m\r\f\ts\u0012\u000bq\u0010z\u001c\u007f\u001a\u000bl%<\u007f\u001eNo\u0002K\u00110\u0002\u0007e\r\b\u0001~\u0002@\"4D\u0001dQ\u000f\"\u0005@\u001f0\u001c\u0007l\u0016Q\rc\u0005\u001bs\u001e\u001eg\u0011\rb\u0003\u0001p<A\rc\u0012\u001ck\u0013Q\u0001\u007f\u001f\f:u\u001d\u0017k\rB8q\u0003\u001a{\u0002\u001b9\u0004\u0006q\u001c\u000b\r,y\u0002\u001en\u0002\\+|\u0010\u0007o\u0010\t%y\u001f\u000ba\u0011D\u000ed(\u001bu\u0003\u0018k\u0000@R*\u0004\u001dg\u0011\u000b\te\u0005\u0006,\u001bG\u0007h\u001d\u0007t\u0006\u000b\u000b\u007f\u001cT8.g!O\"=N\u0018!~\u0007\u000fn\nAHq\u0001\u001e\"\u0011@\u000fy\u0002\u001ap\u0002Q\u0001\u007f\u001f!<x\u0010\u001a\"\u0002F\u000b\u007f\u0004\u0000vCM\tcQ\u0000mC}\n\u007f\tNr\u0011J\u000ey\u001d\u000b,\n8b\u001e\u001eg\u0011Q\u0001u\u0002\t\u001b|\u001e\u0019]\u0007J\u001f~@\u0000d\u0005\u001eqY\nGq\u0001\u0007,\u000eL\u0006u\u0012\u001cc\u0005Q\u001bu\u0003\u0018k\u0000@\u001b>\u0012\u0001oLD\u001dd\u0019\u000bl\u0017L\u000bq\u0005\u0007m\r\n\u0004\u007f\u0016\u0007l<R\u0001d\u00191z\u0001J\u0010\n\u000fb\u0010\u0000v<Q\u0011`\u0014\u0007*u\u0010\u001cg\u0011\u0005\t\u000b|\u0018\u000bl\u0017z\u0001t\u0005<\u007f\u001a\u000bl\rA>Q:p\u001a\u0005\tw\u0010\u0007lM&)e\u0003\u0001p\u0002\u0005\u001by\u0016\u0000/\nKR0\n\u0013\"\u0011@\u001ce\u0003\u0000g\u0007\u0005\u0013mQ\fm\u0007\\Uk\f\u001f%y\u0012\u001cm\u0010J\u000edQ\u001cg\u0005P\u001bu\u0015Nv\u000b@Hc\u0018\tlNL\u00060Y\u0016Hb\u0014\bw\u0010@\f0\u0005\u0006gCV\u0001w\u001fCk\r\u0005@\r\u0001~\u0007\u000fn\nA7w\u0003\u000fl\u0017\r\u001au\u0002\u001em\rV\rO\u0005\u0017r\u0006\u000b\u001au\u0005\u001c{ND\u000ed\u0014\u001c\u009a<x\u0018\u001d\"\u0001P\u0001|\u0015IqCh\u0001s\u0003\u0001q\fC\u001c0\u0010\u001erCL\u001b0\u001f\u0001vCD\u0018`\u0003\u0001t\u0006AHv\u001e\u001c\".L\u0006u\u0012\u001cc\u0005QHc\u0018\tlNL\u0006>Q'vCK\ru\u0015\u001d\"\u0002U\u0018b\u001e\u0018c\u000f\u0005@q\u001a\u000f,\u000eVGQ\u0001\u001eP\u0006B!~\u0017\u0001+O\u0005\u0007bQ\u000flCD\u0018`\u0003\u0001t\u0006AHq\u0001\u001e\"\nAHy\u001fNa\fK\u000ey\u0016Ac\u0016W\u0007b\u00101c\u0019P\u001au_\u001az\u0017\u000b\bH8\u0010\fm\u0016QH\u0015;y\u0016\u0000/\nKHg\u0010\u001d\"\u0007@\u000b|\u0018\u0000g\u0007\u000b\f\ts\u0012\u000bq\u0010z\u001c\u007f\u001a\u000bl\r\rh\u0001\u0007p\u0006A7d\u001e\u0005g\r\n)e\u0005\u0006O\u0006Q\u0000\u007f\u0015\u0010X A^2S\u0015X$A\\`V\u0016Z(\u0010X A^2S\u0015X$A\\`V\u0016Z(\n8b\u001e\u001eg\u0011Q\u0001u\u0002b%y\u001f\u000ba\u0011D\u000edQ\u0007qCW\rv\u0004\u001dk\rBHc\u0018\tlNL\u0006cQ\bp\fHHx\u0014\u001cgCC\u0007bQ\u0000m\u0014\u000bHE\u0002\u001bc\u000fI\u00110\u0005\u0001mCH\t~\bNk\r\u0005\t0\u0003\u0001uM\u0005?q\u0018\u001a.CQ\u0000u\u001fNv\u0011\\Hq\u0016\u000fk\r\u000b\t\u001dc\u0014\u001c]\u0000J\fu-\u0000d\u0005\u001eqY\nGh\u0002\u001aqMD\u001dd\u0019@z\u0001J\u0010|\u0018\u0018gMF\u0007}^\u0016q\u0017VGq\u0004\u001aj\fW\u0001j\u00145<x\u0010\u001a\"\u0002F\u000b\u007f\u0004\u0000vCL\u001b0\u0010Na\u000bL\u0004tQ\u000fl\u0007\u0005\u0006u\u0014\nqCD\ft\u0018\u0000eCQ\u00070\u0010Nd\u0002H\u0001|\b@\u0010\u001eu\u0003\u0007d\nF\td\u0018\u0001l<P\u001ay\u0003\"G%\"<x\u0010\u001a\"\u0010L\u000f~\\\u0007lC@\u0010`\u0018\u001cg\u0007\u000bHC\u0005\u000fp\u0017\u0005\tw\u0010\u0007lM\n\rh\u0001\u0007p\u0006V7y\u001f\u0005\rb\u0003\u0001p\u0002A>\r\u0001~\u0007\u000fn\nA7w\u0003\u000fl\u0017'0r\u001e\u0016\"/L\u001euQ\u0019m\u0016I\f0\u001f\u0001vCD\u000bs\u0014\u001evCQ\u0000uQ\u001dk\u0004KEy\u001f@\t;q\u001f\n`\f]!t\u0010\t`\u0001\u0002k\u0000D\u001cy\u001e\u0000-\tV\u0007~!\t`\u0001\u0002k\u0000D\u001cy\u001e\u0000-\u001b\b\u001fg\u0006Cd\fW\u0005=\u0004\u001cn\u0006K\u000b\u007f\u0015\u000bf\u0006:U%/K/\u0018\u0000d\u0005\u001e8L\n\te\u0005\u0006,\u001bG\u0007h\u001d\u0007t\u0006\u000b\u000b\u007f\u001c\u001f\u001a`KA-\u0002U\u0001>\u001c\u0007l\u0006F\u001aq\u0017\u001aq\u0006W\u001ey\u0012\u000bqMF\u0007}^3\u0000d\u0005\u001eqY\nGq\u0001\u0007,\u000eL\u0006u\u0012\u001cc\u0005Q\u001bu\u0003\u0018k\u0000@\u001b>\u0012\u0001oLH\u0001~\u0014\rp\u0002C\u001c?\u0001\u001cm\u0005L\u0004u\u0010\u001eu\u0003\u0007d\nF\td\u0018\u0001l<P\u001ay\f+\u007f\u001f\u001ag\rQED\b\u001eg,\u001db\u001fTk\u0006Q\u000e*\u0001\u000fp\u0002H\u001b*\u001e\u000fw\u0017MRw\u0003\u000fl\u0017\b\u001ci\u0001\u000b8\u0007@\u001ey\u0012\u000b]\u0000J\fu\u0005\rb\u0003\u0001p\u0006)s\u0012\u000br\u0017(\u001bu\u0003\u0018k\u0000@R*\u0004\u001dg\u0011\u000b\te\u0005\u0006,\u001bG\u0007h\u001d\u0007t\u0006\u000b\u000b\u007f\u001cT8.g!O\"=N\u0005\u001bs\u001e\u001eg3\u0000d\u0005\u001eqY\nGq\u0001\u0007,\u000eL\u0006u\u0012\u001cc\u0005Q\u001bu\u0003\u0018k\u0000@\u001b>\u0012\u0001oLH\u0001~\u0014\rp\u0002C\u001c?\u0001\u001cm\u0005L\u0004u\u0015\te\u0005\u0006m\u0011L\u0012q\u0005\u0007m\rz\u0018u\u001f\nk\rB";
                        var12_3 = "\u0001~\u0005\u000bp\u0015D\u0004$<x\u0010\u001a\"\u0002F\u000b\u007f\u0004\u0000vCA\u0007u\u0002Nl\fQH\u007f\u0006\u0000\".L\u0006u\u0012\u001cc\u0005QF\u000fHq\u001f\n\"\u0017W\u00110\u0010\tc\nKF\u0005<\u007f\u001a\u000bl\u0002\f-\f\u000b\u007f\u0015\u000b]\u0006]\u0018y\u0003\u000bf\n\rh\u0001\u0007p\u0006V7y\u001f\u0010%y\u0012\u001cm\u0010J\u000edQ\u001dc\nAR0\u0002\u0001t\b;y\u0005\u000bL\u0002H\r\f:u\u001d\u0017k\rB8q\u0003\u001a{\f+\u007f\u001f\u001ag\rQED\b\u001eg\u000f;y\u0016\u0000g\u0007\u0005\u0001~Q\u000fqC^\u0015\u0003\"G%\u0002A>\u000b\fu\u0007\u0007a\u0006z\u000b\u007f\u0015\u000b\t@\u007f\u001c\u0007v\u0017@\f9\r,y\u0002\u001en\u0002\\+|\u0010\u0007o\u0010\u001e\u0000d\u0005\u001eqY\nGg\u0006\u0019,\u000eL\u000bb\u001e\u001dm\u0005QFs\u001e\u0003-\u000fL\u0006{\t<\u007f\u001a\u000bl7\\\u0018u\u0003\u0010y\u0015\t0R=],S\u0005\u0010-@\u0000d\u0005\u001eqY\nGq\u0001\u0007,\u000eL\u0006u\u0012\u001cc\u0005Q\u001bu\u0003\u0018k\u0000@\u001b>\u0012\u0001oLD\u001dd\u0019\u000bl\u0017L\u000bq\u0005\u0007m\r\n\u0004\u007f\u0016\u0007l<R\u0001d\u00191z\u0001J\u0010\u00040U\u0003\u001c0\u0000d\u0005\u001eqY\nGe\u0002\u000bpMD\u001dd\u0019@z\u0001J\u0010|\u0018\u0018gMF\u0007}^\u001bq\u0006WGq\u0004\u001aj\u0006K\u001cy\u0012\u000fv\u0006\r\u001au\u0017\u001cg\u0010M7d\u001e\u0005g\r\u0003:@\"\t\u000b|\u0018\u000bl\u0017z\u0001t\u000b\fu\u0007\u0007a\u0006z\u000b\u007f\u0015\u000b-\u0000d\u0005\u001eqY\nGh\u0002\u001aqMD\u001dd\u0019@z\u0001J\u0010|\u0018\u0018gMF\u0007}^\u0016q\u0017VGq\u0004\u001aj\fW\u0001j\u0014\u001b0r\u001e\u0016\"\u0011@\u0002u\u0012\u001ag\u0007\u0005\u001cx\u0014Nq\nB\u0006=\u0018\u0000\"K\r\u0001t\u0014\u0000v\nQ\u0011D\u001e\u0005g\r\t:`\u0002:k\u0000N\rd\t%y\u001f\u000ba\u0011D\u000ed\t<\u007f\u001a\u000bl7\\\u0018u%+\u007f\u0004\u0002fCK\u0007dQ\u001cg\u0002AHd\u0019\u000b\".L\u0006u\u0012\u001cc\u0005QH`\u0003\u0001d\nI\r>\u0010;y\u0016\u0000/\nKHv\u0010\u0007n\u0006AH8*\u0000d\u0005\u001eqY\nG|\u001e\tk\r\u000b\u0004y\u0007\u000b,\u0000J\u0005?\u001e\u000fw\u0017MZ .\rm\rK\rs\u0005@q\u0011C40r\u001e\u0016\"/L\u001euQ\u0007qCK\u0007dQ\u000ft\u0002L\u0004q\u0013\u0002gCL\u00060\u0005\u0006c\u0017\u0005\ts\u0012\u0001w\rQOcQ\u001cg\u0004L\u0007~_\u0010\t`\u0001\u0002k\u0000D\u001cy\u001e\u0000-\tV\u0007~\n\u000fb\u0010\u0000v<Q\u0011`\u0014(\u0000d\u0005\u001eqY\nG|\u001e\tk\r\u000b\u0004y\u0007\u000b,\u0000J\u0005?\u001e\u000fw\u0017MZ .\u001am\b@\u0006>\u0002\u001cd\u0011\rb\u0003\u0001p<A\rc\u0012\u001ck\u0013Q\u0001\u007f\u001f\u00040U\u0003\u001c\u0016\te\u0005\u0006m\u0011L\u0012q\u0005\u0007m\rz\fu\u0012\u0002k\r@\f\u0016\u001dc\u0014\u001c,\u0002P\u001cx_\u0016`\f]\u0004y\u0007\u000b,\u0000J\u00050\u0000d\u0005\u001eqY\nGe\u0002\u000bpMD\u001dd\u0019@z\u0001J\u0010|\u0018\u0018gMF\u0007}^\u001bq\u0006WGq\u0004\u001aj\u0006K\u001cy\u0012\u000fv\u0006\r\u001au\u0017\u001cg\u0010M7d\u001e\u0005g\r\u001f<x\u0010\u001a\"\u0000J\fuQ\u000bz\u0013L\u001au\u0015@\"0Q\tb\u0005Nc\u0004D\u0001~_\b\u0001~\u0005\u000bp\u0015D\u0004*\u0000d\u0005\u001eqY\nG|\u001e\tk\r\u000b\u0004y\u0007\u000b,\u0000J\u0005?\u001e\u000fw\u0017MZ .\rm\rK\rs\u0005@q\u0011C\u0003\u0010e\u0018\u0003\u001dx\u0002\u0003\u0010e\u0018(\u0000d\u0005\u001eqY\nG|\u001e\tk\r\u000b\u0004y\u0007\u000b,\u0000J\u0005?\u001e\u000fw\u0017MZ .\u001am\b@\u0006>\u0002\u001cd\n=c\u0014\u001cV\fN\r~\u0002\r)e\u0005\u0006m\u0011L\u0012q\u0005\u0007m\r\f\ts\u0012\u000bq\u0010z\u001c\u007f\u001a\u000bl%<\u007f\u001eNo\u0002K\u00110\u0002\u0007e\r\b\u0001~\u0002@\"4D\u0001dQ\u000f\"\u0005@\u001f0\u001c\u0007l\u0016Q\rc\u0005\u001bs\u001e\u001eg\u0011\rb\u0003\u0001p<A\rc\u0012\u001ck\u0013Q\u0001\u007f\u001f\f:u\u001d\u0017k\rB8q\u0003\u001a{\u0002\u001b9\u0004\u0006q\u001c\u000b\r,y\u0002\u001en\u0002\\+|\u0010\u0007o\u0010\t%y\u001f\u000ba\u0011D\u000ed(\u001bu\u0003\u0018k\u0000@R*\u0004\u001dg\u0011\u000b\te\u0005\u0006,\u001bG\u0007h\u001d\u0007t\u0006\u000b\u000b\u007f\u001cT8.g!O\"=N\u0018!~\u0007\u000fn\nAHq\u0001\u001e\"\u0011@\u000fy\u0002\u001ap\u0002Q\u0001\u007f\u001f!<x\u0010\u001a\"\u0002F\u000b\u007f\u0004\u0000vCM\tcQ\u0000mC}\n\u007f\tNr\u0011J\u000ey\u001d\u000b,\n8b\u001e\u001eg\u0011Q\u0001u\u0002\t\u001b|\u001e\u0019]\u0007J\u001f~@\u0000d\u0005\u001eqY\nGq\u0001\u0007,\u000eL\u0006u\u0012\u001cc\u0005Q\u001bu\u0003\u0018k\u0000@\u001b>\u0012\u0001oLD\u001dd\u0019\u000bl\u0017L\u000bq\u0005\u0007m\r\n\u0004\u007f\u0016\u0007l<R\u0001d\u00191z\u0001J\u0010\n\u000fb\u0010\u0000v<Q\u0011`\u0014\u0007*u\u0010\u001cg\u0011\u0005\t\u000b|\u0018\u000bl\u0017z\u0001t\u0005<\u007f\u001a\u000bl\rA>Q:p\u001a\u0005\tw\u0010\u0007lM&)e\u0003\u0001p\u0002\u0005\u001by\u0016\u0000/\nKR0\n\u0013\"\u0011@\u001ce\u0003\u0000g\u0007\u0005\u0013mQ\fm\u0007\\Uk\f\u001f%y\u0012\u001cm\u0010J\u000edQ\u001cg\u0005P\u001bu\u0015Nv\u000b@Hc\u0018\tlNL\u00060Y\u0016Hb\u0014\bw\u0010@\f0\u0005\u0006gCV\u0001w\u001fCk\r\u0005@\r\u0001~\u0007\u000fn\nA7w\u0003\u000fl\u0017\r\u001au\u0002\u001em\rV\rO\u0005\u0017r\u0006\u000b\u001au\u0005\u001c{ND\u000ed\u0014\u001c\u009a<x\u0018\u001d\"\u0001P\u0001|\u0015IqCh\u0001s\u0003\u0001q\fC\u001c0\u0010\u001erCL\u001b0\u001f\u0001vCD\u0018`\u0003\u0001t\u0006AHv\u001e\u001c\".L\u0006u\u0012\u001cc\u0005QHc\u0018\tlNL\u0006>Q'vCK\ru\u0015\u001d\"\u0002U\u0018b\u001e\u0018c\u000f\u0005@q\u001a\u000f,\u000eVGQ\u0001\u001eP\u0006B!~\u0017\u0001+O\u0005\u0007bQ\u000flCD\u0018`\u0003\u0001t\u0006AHq\u0001\u001e\"\nAHy\u001fNa\fK\u000ey\u0016Ac\u0016W\u0007b\u00101c\u0019P\u001au_\u001az\u0017\u000b\bH8\u0010\fm\u0016QH\u0015;y\u0016\u0000/\nKHg\u0010\u001d\"\u0007@\u000b|\u0018\u0000g\u0007\u000b\f\ts\u0012\u000bq\u0010z\u001c\u007f\u001a\u000bl\r\rh\u0001\u0007p\u0006A7d\u001e\u0005g\r\n)e\u0005\u0006O\u0006Q\u0000\u007f\u0015\u0010X A^2S\u0015X$A\\`V\u0016Z(\u0010X A^2S\u0015X$A\\`V\u0016Z(\n8b\u001e\u001eg\u0011Q\u0001u\u0002b%y\u001f\u000ba\u0011D\u000edQ\u0007qCW\rv\u0004\u001dk\rBHc\u0018\tlNL\u0006cQ\bp\fHHx\u0014\u001cgCC\u0007bQ\u0000m\u0014\u000bHE\u0002\u001bc\u000fI\u00110\u0005\u0001mCH\t~\bNk\r\u0005\t0\u0003\u0001uM\u0005?q\u0018\u001a.CQ\u0000u\u001fNv\u0011\\Hq\u0016\u000fk\r\u000b\t\u001dc\u0014\u001c]\u0000J\fu-\u0000d\u0005\u001eqY\nGh\u0002\u001aqMD\u001dd\u0019@z\u0001J\u0010|\u0018\u0018gMF\u0007}^\u0016q\u0017VGq\u0004\u001aj\fW\u0001j\u00145<x\u0010\u001a\"\u0002F\u000b\u007f\u0004\u0000vCL\u001b0\u0010Na\u000bL\u0004tQ\u000fl\u0007\u0005\u0006u\u0014\nqCD\ft\u0018\u0000eCQ\u00070\u0010Nd\u0002H\u0001|\b@\u0010\u001eu\u0003\u0007d\nF\td\u0018\u0001l<P\u001ay\u0003\"G%\"<x\u0010\u001a\"\u0010L\u000f~\\\u0007lC@\u0010`\u0018\u001cg\u0007\u000bHC\u0005\u000fp\u0017\u0005\tw\u0010\u0007lM\n\rh\u0001\u0007p\u0006V7y\u001f\u0005\rb\u0003\u0001p\u0002A>\r\u0001~\u0007\u000fn\nA7w\u0003\u000fl\u0017'0r\u001e\u0016\"/L\u001euQ\u0019m\u0016I\f0\u001f\u0001vCD\u000bs\u0014\u001evCQ\u0000uQ\u001dk\u0004KEy\u001f@\t;q\u001f\n`\f]!t\u0010\t`\u0001\u0002k\u0000D\u001cy\u001e\u0000-\tV\u0007~!\t`\u0001\u0002k\u0000D\u001cy\u001e\u0000-\u001b\b\u001fg\u0006Cd\fW\u0005=\u0004\u001cn\u0006K\u000b\u007f\u0015\u000bf\u0006:U%/K/\u0018\u0000d\u0005\u001e8L\n\te\u0005\u0006,\u001bG\u0007h\u001d\u0007t\u0006\u000b\u000b\u007f\u001c\u001f\u001a`KA-\u0002U\u0001>\u001c\u0007l\u0006F\u001aq\u0017\u001aq\u0006W\u001ey\u0012\u000bqMF\u0007}^3\u0000d\u0005\u001eqY\nGq\u0001\u0007,\u000eL\u0006u\u0012\u001cc\u0005Q\u001bu\u0003\u0018k\u0000@\u001b>\u0012\u0001oLH\u0001~\u0014\rp\u0002C\u001c?\u0001\u001cm\u0005L\u0004u\u0010\u001eu\u0003\u0007d\nF\td\u0018\u0001l<P\u001ay\f+\u007f\u001f\u001ag\rQED\b\u001eg,\u001db\u001fTk\u0006Q\u000e*\u0001\u000fp\u0002H\u001b*\u001e\u000fw\u0017MRw\u0003\u000fl\u0017\b\u001ci\u0001\u000b8\u0007@\u001ey\u0012\u000b]\u0000J\fu\u0005\rb\u0003\u0001p\u0006)s\u0012\u000br\u0017(\u001bu\u0003\u0018k\u0000@R*\u0004\u001dg\u0011\u000b\te\u0005\u0006,\u001bG\u0007h\u001d\u0007t\u0006\u000b\u000b\u007f\u001cT8.g!O\"=N\u0005\u001bs\u001e\u001eg3\u0000d\u0005\u001eqY\nGq\u0001\u0007,\u000eL\u0006u\u0012\u001cc\u0005Q\u001bu\u0003\u0018k\u0000@\u001b>\u0012\u0001oLH\u0001~\u0014\rp\u0002C\u001c?\u0001\u001cm\u0005L\u0004u\u0015\te\u0005\u0006m\u0011L\u0012q\u0005\u0007m\rz\u0018u\u001f\nk\rB".length();
                        var9_4 = 8;
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
                            var10_2 = "%y\u0012\u001cm\u0010J\u000edQ\u001dg\rQHr\u0010\riCV\u0007}\u0014\u001aj\nK\u000f0\u0004\u0000g\u001bU\rs\u0005\u000bfM\u00150r\u001e\u0016\"\u0010L\u000f~\\\u0007lCC\ty\u001d\u000bfC\r";
                            var12_3 = "%y\u0012\u001cm\u0010J\u000edQ\u001dg\rQHr\u0010\riCV\u0007}\u0014\u001aj\nK\u000f0\u0004\u0000g\u001bU\rs\u0005\u000bfM\u00150r\u001e\u0016\"\u0010L\u000f~\\\u0007lCC\ty\u001d\u000bfC\r".length();
                            var9_4 = 41;
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
                                v10 = 104;
                                break;
                            }
                            case 1: {
                                v10 = 16;
                                break;
                            }
                            case 2: {
                                v10 = 113;
                                break;
                            }
                            case 3: {
                                v10 = 110;
                                break;
                            }
                            case 4: {
                                v10 = 2;
                                break;
                            }
                            case 5: {
                                v10 = 99;
                                break;
                            }
                            default: {
                                v10 = 37;
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
                MsAuth.b = var13;
                MsAuth.SCOPE = MsAuth.b[66];
                MsAuth.TOKEN_URL = MsAuth.b[54];
                MsAuth.XSTS_URL = MsAuth.b[29];
                MsAuth.XBL_URL = MsAuth.b[24];
                MsAuth.MC_PROFILE = MsAuth.b[110];
                MsAuth.CLIENT_ID = MsAuth.b[89];
                MsAuth.DEVICE_URL = MsAuth.b[37];
                MsAuth.MC_LOGIN = MsAuth.b[22];
                var0_7 = 9194788344157799013L;
                var6_8 = new long[6];
                var3_9 = 0;
                var4_10 = "#*\u00f4\n\u0083~\u00db\u00bbD\f\tq/\u001ft\u00e5jS\u00deo\u00cdcG;G\u00e8\u0017\u00ab\u0001@T\u000f";
                var5_11 = "#*\u00f4\n\u0083~\u00db\u00bbD\f\tq/\u001ft\u00e5jS\u00deo\u00cdcG;G\u00e8\u0017\u00ab\u0001@T\u000f".length();
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
lbl98:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "sc\u00fa&\u00fe\u0095\u0007Qr-P\u0010\u008a\u00a6\u00ea\u00ca";
                    var5_11 = "sc\u00fa&\u00fe\u0095\u0007Qr-P\u0010\u008a\u00a6\u00ea\u00ca".length();
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
lbl111:

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
lbl122:

                continue;
            }
        }
        MsAuth.c = var6_8;
        MsAuth.d = new Long[6];
        MsAuth.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(MsAuth.a(17206, 1569976330866312529L))).followRedirects(HttpClient.Redirect.NORMAL).build();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x665) & Short.MAX_VALUE;
        if (d[n2] == null) {
            MsAuth.d[n2] = c[n2] ^ l;
        }
        return d[n2];
    }
}
