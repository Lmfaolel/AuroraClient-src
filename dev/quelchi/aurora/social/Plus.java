package dev.quelchi.aurora.social;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.gui.im.Themes;
import dev.quelchi.aurora.social.Plus$Status;
import dev.quelchi.aurora.social.Social;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import net.minecraft.client.MinecraftClient;

public final class Plus {
    private static volatile Plus$Status status;
    private static volatile long asked;
    private static final HttpClient HTTP;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private Plus() {
    }

    public static Plus$Status parse(String string) {
        String string2;
        long l;
        JsonObject jsonObject;
        block23: {
            block20: {
                String string3;
                JsonObject jsonObject2;
                block21: {
                    JsonObject jsonObject3;
                    block19: {
                        long l2;
                        int n;
                        block22: {
                            block17: {
                                JsonObject jsonObject4;
                                block18: {
                                    JsonObject jsonObject5;
                                    block16: {
                                        jsonObject = JsonParser.parseString((String)string).getAsJsonObject();
                                        n = Social.a;
                                        jsonObject5 = jsonObject;
                                        if (n != 0) break block16;
                                        if (!jsonObject5.has(a[0])) break block17;
                                        jsonObject4 = jsonObject;
                                        if (n != 0) break block18;
                                        jsonObject5 = jsonObject4.get(a[4]);
                                    }
                                    if (!jsonObject5.isJsonPrimitive()) break block17;
                                    jsonObject4 = jsonObject.get(a[4]);
                                }
                                l2 = jsonObject4.getAsLong();
                                break block22;
                            }
                            l2 = 0L;
                        }
                        l = l2;
                        jsonObject3 = jsonObject;
                        if (n != 0) break block19;
                        if (!jsonObject3.has(a[8])) break block20;
                        jsonObject2 = jsonObject;
                        string3 = a[5];
                        if (n != 0) break block21;
                        jsonObject3 = jsonObject2.get(string3);
                    }
                    if (!jsonObject3.isJsonPrimitive()) break block20;
                    jsonObject2 = jsonObject;
                    string3 = a[5];
                }
                string2 = jsonObject2.get(string3).getAsString();
                break block23;
            }
            string2 = "";
        }
        String string4 = string2;
        String[] stringArray = a;
        return new Plus$Status(Plus.bool(jsonObject, stringArray[3]), Math.max(0L, l), string4, Plus.bool(jsonObject, stringArray[7]));
    }

    private static boolean bool(JsonObject jsonObject, String string) {
        boolean bl;
        block13: {
            block11: {
                JsonObject jsonObject2;
                int n;
                block12: {
                    block10: {
                        n = Social.a;
                        jsonObject2 = jsonObject;
                        if (n != 0) break block10;
                        if (!jsonObject2.has(string)) break block11;
                        jsonObject2 = jsonObject.get(string);
                    }
                    if (n != 0) break block12;
                    if (!jsonObject2.isJsonPrimitive()) break block11;
                    jsonObject2 = jsonObject.get(string);
                }
                bl = jsonObject2.getAsBoolean();
                if (n != 0) break block13;
                if (!bl) break block11;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    public static Plus$Status status() {
        return status;
    }

    public static boolean active() {
        int n;
        block12: {
            block11: {
                block13: {
                    Plus$Status plus$Status;
                    int n2;
                    Plus$Status plus$Status2;
                    block10: {
                        plus$Status2 = status;
                        n2 = Social.a;
                        plus$Status = plus$Status2;
                        if (n2 != 0) break block10;
                        if (!plus$Status.plus) break block11;
                        plus$Status = plus$Status2;
                    }
                    long l = plus$Status.expires - 0L;
                    n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                    if (n2 != 0) break block12;
                    if (n == 0) break block13;
                    long l2 = plus$Status2.expires - System.currentTimeMillis() / Plus.a(22080, 14588871797706379L);
                    n = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                    if (n2 != 0) break block12;
                    if (n <= 0) break block11;
                }
                n = 1;
                break block12;
            }
            n = 0;
        }
        return n != 0;
    }

    public static void refresh(boolean bl) {
        block13: {
            long l;
            block15: {
                long l2;
                block14: {
                    int n;
                    block12: {
                        n = Social.a;
                        if (CosmeticsSync.enabled() && CosmeticsSync.token() != null) break block12;
                        return;
                    }
                    l2 = System.currentTimeMillis();
                    if (n != 0) break block13;
                    if (bl) break block14;
                    l = l2;
                    if (n != 0) break block15;
                    if (l - asked >= Plus.a(2494, 4038511881756365174L)) break block14;
                    return;
                }
                l = l2;
            }
            asked = l;
        }
        Thread thread = new Thread(Plus::lambda$refresh$1, a[6]);
        thread.setDaemon(true);
        thread.start();
    }

    public static void accountChanged() {
        asked = 0L;
        MinecraftClient.method_1551().execute(Plus::lambda$accountChanged$2);
    }

    private static void apply(Plus$Status plus$Status) {
        status = plus$Status;
        Themes.plusChanged();
    }

    private static void lambda$accountChanged$2() {
        Plus.apply(Plus$Status.NONE);
    }

    private static void lambda$refresh$1() {
        int n = Social.a;
        try {
            Object object;
            block4: {
                HttpResponse<String> httpResponse;
                block5: {
                    String[] stringArray = a;
                    HttpRequest httpRequest = CosmeticsSync.bearer(HttpRequest.newBuilder(URI.create(CosmeticsSync.baseUrl() + stringArray[1])).timeout(Duration.ofSeconds(Plus.a(24138, 1423259714249655939L))).GET()).build();
                    httpResponse = HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                    object = httpResponse;
                    if (n != 0) break block4;
                    if (object.statusCode() / 100 == 2) break block5;
                    return;
                }
                object = httpResponse.body();
            }
            Plus$Status plus$Status = Plus.parse((String)object);
            MinecraftClient.method_1551().execute(() -> Plus.lambda$refresh$0(plus$Status));
        }
        catch (Exception exception) {
            Aurora.LOG.debug(a[2], (Throwable)exception);
        }
    }

    private static void lambda$refresh$0(Plus$Status plus$Status) {
        Plus.apply(plus$Status);
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[9];
                        var11_1 = 0;
                        var10_2 = "g=\u0016hbDw\t-$\u0016h?Qhw6\u0018R)\u0013r0Rpc1\u0013r0Gav&\u000e!v@mn \u0002\u0004r)\u0013r\u0007g=\u0016hbDw\u0006q*\u0013ssD\u000bC0\u0014nb@)R)\u0013r";
                        var12_3 = "g=\u0016hbDw\t-$\u0016h?Qhw6\u0018R)\u0013r0Rpc1\u0013r0Gav&\u000e!v@mn \u0002\u0004r)\u0013r\u0007g=\u0016hbDw\u0006q*\u0013ssD\u000bC0\u0014nb@)R)\u0013r".length();
                        var9_4 = 7;
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
                            var10_2 = "p \bdgR\u0006q*\u0013ssD";
                            var12_3 = "p \bdgR\u0006q*\u0013ssD".length();
                            var9_4 = 6;
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
                                v10 = 2;
                                break;
                            }
                            case 1: {
                                v10 = 69;
                                break;
                            }
                            case 2: {
                                v10 = 102;
                                break;
                            }
                            case 3: {
                                v10 = 1;
                                break;
                            }
                            case 4: {
                                v10 = 16;
                                break;
                            }
                            case 5: {
                                v10 = 33;
                                break;
                            }
                            default: {
                                v10 = 4;
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
                Plus.a = var13;
                var0_7 = 3245470631489655259L;
                var6_8 = new long[4];
                var3_9 = 0;
                var4_10 = "_\u0001!\u00a6\u00df\u0084\u0092\u000b-9\u00ee\u00d8\u007f\u00ca\\\u00b8";
                var5_11 = "_\u0001!\u00a6\u00df\u0084\u0092\u000b-9\u00ee\u00d8\u007f\u00ca\\\u00b8".length();
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
                    var4_10 = "\u0015\u0001\u0097p\u00c3\u00af\u00ea\u00cd>\u00caUsL\u001e\u00d7W";
                    var5_11 = "\u0015\u0001\u0097p\u00c3\u00af\u00ea\u00cd>\u00caUsL\u001e\u00d7W".length();
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
        Plus.b = var6_8;
        Plus.c = new Long[4];
        Plus.status = Plus$Status.NONE;
        Plus.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(Plus.a(6928, 8217692726182003674L))).followRedirects(HttpClient.Redirect.NORMAL).build();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x58CA) & Short.MAX_VALUE;
        if (c[n2] == null) {
            Plus.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
