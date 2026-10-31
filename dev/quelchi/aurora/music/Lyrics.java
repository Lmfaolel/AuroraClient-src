package dev.quelchi.aurora.music;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.music.Lyrics$Line;
import dev.quelchi.aurora.music.Lyrics$Song;
import dev.quelchi.aurora.music.Lyrics$Word;
import dev.quelchi.aurora.music.NowPlaying;
import dev.quelchi.aurora.music.SmtcClient;
import java.io.IOException;
import java.lang.invoke.CallSite;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Lyrics {
    public static final Lyrics$Song NONE;
    private static final Lyrics$Song PENDING;
    private static final String API;
    private static final String NETEASE;
    private static final String UA;
    private static final Pattern STAMP;
    private static final Pattern WORD;
    private static final Pattern TAG;
    private static final Pattern DASH_TAG;
    private static final Pattern FEAT;
    private static final Pattern ARTISTS;
    private static final Pattern SPLIT;
    private static final Pattern QUOTED;
    private static final Pattern CHANNEL;
    private static final Pattern CREDIT;
    private static final long RETRY_MS;
    private static final long RETRY_ERR_MS;
    private static final int TOL;
    private static final Map<String, Lyrics$Song> CACHE;
    private static final Map<String, Long> MISSED;
    private static volatile String wanted;
    private static final ExecutorService POOL;
    public static boolean a;
    private static final String[] b;
    private static final long[] c;
    private static final Long[] d;

    private Lyrics() {
    }

    public static Lyrics$Song get(NowPlaying nowPlaying) {
        block28: {
            String string;
            block29: {
                Object object;
                boolean bl;
                block26: {
                    block27: {
                        Lyrics$Song lyrics$Song;
                        block24: {
                            block25: {
                                NowPlaying nowPlaying2;
                                block22: {
                                    block23: {
                                        bl = a;
                                        nowPlaying2 = nowPlaying;
                                        if (bl) break block22;
                                        if (!nowPlaying2.title().isBlank()) break block23;
                                        return NONE;
                                    }
                                    nowPlaying2 = nowPlaying;
                                }
                                wanted = string = nowPlaying2.artKey();
                                lyrics$Song = CACHE.get(string);
                                object = lyrics$Song;
                                if (bl) break block24;
                                if (object != PENDING) break block25;
                                return NONE;
                            }
                            object = lyrics$Song;
                        }
                        if (bl) break block26;
                        if (object == null) break block27;
                        object = lyrics$Song;
                        if (bl) break block26;
                        if (object == NONE) break block27;
                        return lyrics$Song;
                    }
                    object = MISSED.get(string);
                }
                Long l = (Long)object;
                if (bl) break block28;
                if (l == null) break block29;
                if (System.currentTimeMillis() >= l) break block29;
                return NONE;
            }
            CACHE.put(string, PENDING);
            POOL.submit(() -> Lyrics.lambda$get$1(string, nowPlaying));
        }
        return NONE;
    }

    private static Lyrics$Song fetch(NowPlaying nowPlaying) throws Exception {
        Lyrics$Song lyrics$Song;
        block24: {
            Lyrics$Song lyrics$Song2;
            block25: {
                Exception exception;
                block26: {
                    Exception exception2;
                    boolean bl;
                    block23: {
                        Object object;
                        long l = Math.round((double)nowPlaying.durationMs() / 1000.0);
                        String[] stringArray = Lyrics.query(nowPlaying.title(), nowPlaying.artist());
                        String string = stringArray[0];
                        String string2 = stringArray[1];
                        lyrics$Song2 = NONE;
                        bl = a;
                        exception2 = null;
                        try {
                            int n;
                            String[] stringArray2;
                            block20: {
                                block21: {
                                    block18: {
                                        block19: {
                                            object = Lyrics.lrclib(nowPlaying, string, string2, l);
                                            stringArray2 = object;
                                            if (bl) break block18;
                                            if (stringArray2 != null) break block19;
                                            return NONE;
                                        }
                                        stringArray2 = object;
                                    }
                                    n = 0;
                                    if (bl) break block20;
                                    if (stringArray2[n] == null) break block21;
                                    return new Lyrics$Song(Lyrics.parse(object[0]), true);
                                }
                                stringArray2 = object;
                                n = 1;
                            }
                            if (stringArray2[n] != null) {
                                lyrics$Song2 = new Lyrics$Song(Lyrics.plain(object[1], nowPlaying.durationMs()), false);
                            }
                        }
                        catch (Exception exception3) {
                            exception2 = exception3;
                        }
                        try {
                            object = Lyrics.netease(string, string2, l);
                            if (object != null) {
                                return new Lyrics$Song(Lyrics.parse((String)object), true);
                            }
                        }
                        catch (Exception exception4) {
                            Exception exception5;
                            block22: {
                                exception5 = exception2;
                                if (bl) break block22;
                                if (exception5 != null) break block23;
                                exception5 = exception4;
                            }
                            exception2 = exception5;
                        }
                    }
                    lyrics$Song = lyrics$Song2;
                    if (bl) break block24;
                    if (lyrics$Song != NONE) break block25;
                    exception = exception2;
                    if (bl) break block26;
                    if (exception == null) break block25;
                    exception = exception2;
                }
                throw exception;
            }
            lyrics$Song = lyrics$Song2;
        }
        return lyrics$Song;
    }

    
    private static String[] lrclib(NowPlaying var0, String var1_1, String var2_2, long var3_3) throws Exception {
        block96: {
            block97: {
                block98: {
                    block85: {
                        block101: {
                            block73: {
                                block99: {
                                    block100: {
                                        var5_4 = Lyrics.a;
                                        v0 = var3_3;
                                        if (var5_4) break block99;
                                        if (v0 <= 0L) break block73;
                                        break block100;
                                        catch (IOException v1) {
                                            throw v1;
                                        }
                                    }
                                    v0 = var3_3;
                                }
                                var23_5 = Lyrics.b;
                                v3 = var23_5[38] + v0;
                                break block101;
                            }
                            v3 = "";
                        }
                        var6_6 = v3;
                        var7_7 = new LinkedHashSet<CallSite>();
                        var17_8 = var6_6;
                        var18_9 = Lyrics.enc(var0.artist());
                        var19_10 = Lyrics.enc(var0.title());
                        var23_5 = Lyrics.b;
                        var7_7.add((CallSite)(var23_5[39] + var19_10 + var23_5[50] + var18_9 + var17_8));
                        var20_11 = var6_6;
                        var21_12 = Lyrics.enc(var2_2);
                        var22_13 = Lyrics.enc(var1_1);
                        var7_7.add((CallSite)(var23_5[37] + var22_13 + var23_5[26] + var21_12 + var20_11));
                        var8_14 = null;
                        var9_15 = null;
                        var10_16 = null;
                        var11_17 = 0;
                        var12_18 = var7_7.iterator();
                        while (var12_18.hasNext()) {
                            block84: {
                                block83: {
                                    block108: {
                                        block81: {
                                            block82: {
                                                block79: {
                                                    block80: {
                                                        block107: {
                                                            block106: {
                                                                block105: {
                                                                    block77: {
                                                                        block78: {
                                                                            block104: {
                                                                                block103: {
                                                                                    block102: {
                                                                                        block75: {
                                                                                            block76: {
                                                                                                block74: {
                                                                                                    var13_19 = (String)var12_18.next();
                                                                                                    try {
                                                                                                        var23_5 = Lyrics.b;
                                                                                                        var14_20 = Lyrics.request(var23_5[52] + var13_19);
                                                                                                        v4 = 1;
                                                                                                        if (!var5_4) {
                                                                                                            var11_17 = v4;
                                                                                                        }
                                                                                                    }
                                                                                                    catch (IOException var15_22) {
                                                                                                        var10_16 = var15_22;
                                                                                                        continue;
                                                                                                    }
                                                                                                    v5 = var14_20;
                                                                                                    if (var5_4) break block74;
                                                                                                    if (v5 == null) continue;
                                                                                                    v5 = var14_20;
                                                                                                }
                                                                                                if (var5_4) break block75;
                                                                                                if (!v5.isJsonObject()) {
                                                                                                    continue;
                                                                                                }
                                                                                                break block76;
                                                                                                catch (IOException v7) {
                                                                                                    throw v7;
                                                                                                }
                                                                                            }
                                                                                            v5 = var14_20;
                                                                                        }
                                                                                        var15_21 = v5.getAsJsonObject();
                                                                                        v9 = var15_21;
                                                                                        if (var5_4) break block77;
                                                                                        if (!v9.has(Lyrics.b[24])) break block78;
                                                                                        break block102;
                                                                                        catch (IOException v10) {
                                                                                            throw v10;
                                                                                        }
                                                                                    }
                                                                                    v9 = var15_21;
                                                                                    if (var5_4) break block77;
                                                                                    break block103;
                                                                                    catch (IOException v11) {
                                                                                        throw v11;
                                                                                    }
                                                                                }
                                                                                if (!v9.get(Lyrics.b[1]).getAsBoolean()) break block78;
                                                                                break block104;
                                                                                catch (IOException v12) {
                                                                                    throw v12;
                                                                                }
                                                                            }
                                                                            return null;
                                                                        }
                                                                        v9 = var15_21;
                                                                    }
                                                                    var16_24 = Lyrics.synced(v9, var3_3);
                                                                    v14 = var16_24;
                                                                    if (var5_4) break block79;
                                                                    if (v14 == null) break block80;
                                                                    break block105;
                                                                    catch (IOException v15) {
                                                                        throw v15;
                                                                    }
                                                                }
                                                                v14 = var16_24;
                                                                if (var5_4) break block79;
                                                                break block106;
                                                                catch (IOException v16) {
                                                                    throw v16;
                                                                }
                                                            }
                                                            if (Lyrics.latin((String)v14) != Lyrics.latin(var1_1)) break block80;
                                                            break block107;
                                                            catch (IOException v17) {
                                                                throw v17;
                                                            }
                                                        }
                                                        return new String[]{var16_24, null};
                                                    }
                                                    v14 = var9_15;
                                                }
                                                if (var5_4) break block81;
                                                if (v14 != null) break block82;
                                                var9_15 = var16_24;
                                            }
                                            v14 = var8_14;
                                        }
                                        if (var5_4) break block83;
                                        if (v14 != null) break block84;
                                        break block108;
                                        catch (IOException v20) {
                                            throw v20;
                                        }
                                    }
                                    v14 = Lyrics.text(var15_21, Lyrics.b[32]);
                                }
                                var8_14 = v14;
                            }
                            if (!var5_4) continue;
                        }
                        var23_5 = Lyrics.b;
                        var12_18 = List.of(var23_5[37] + Lyrics.enc(var1_1) + var23_5[26] + Lyrics.enc(var2_2), var23_5[6] + Lyrics.enc(var2_2 + " " + var1_1)).iterator();
                        while (true) {
                            block95: {
                                block94: {
                                    block92: {
                                        block93: {
                                            block90: {
                                                block91: {
                                                    block89: {
                                                        block87: {
                                                            block86: {
                                                                v4 = (int)var12_18.hasNext();
lbl165:

                                                                if (v4 == 0) break;
                                                                var13_19 = (String)var12_18.next();
                                                                try {
                                                                    var23_5 = Lyrics.b;
                                                                    v22 = var23_5[35] + var13_19;
                                                                    if (var5_4) break block85;
                                                                    var14_20 = Lyrics.request(v22);
                                                                    var11_17 = 1;
                                                                }
                                                                catch (IOException var15_23) {
                                                                    var10_16 = var15_23;
                                                                    continue;
                                                                }
                                                                v23 = var14_20;
                                                                if (var5_4) break block86;
                                                                if (v23 == null) continue;
                                                                v23 = var14_20;
                                                            }
                                                            if (var5_4) break block87;
                                                            if (!v23.isJsonArray()) {
                                                                continue;
                                                            }
                                                            v23 = var14_20;
                                                        }
                                                        var15_21 = Lyrics.pick(v23.getAsJsonArray(), var1_1, var2_2, var3_3);
                                                        v27 = var15_21;
                                                        if (var5_4) break block89;
                                                        if (v27 == null) {
                                                            continue;
                                                        }
                                                        v27 = var15_21;
                                                    }
                                                    var16_24 = Lyrics.synced(v27, var3_3);
                                                    v29 = var16_24;
                                                    if (var5_4) break block90;
                                                    if (v29 == null) break block91;
                                                    v29 = var16_24;
                                                    if (var5_4) break block90;
                                                    if (Lyrics.latin((String)v29) != Lyrics.latin(var1_1)) break block91;
                                                    return new String[]{var16_24, null};
                                                }
                                                v29 = var9_15;
                                            }
                                            if (var5_4) break block92;
                                            if (v29 != null) break block93;
                                            var9_15 = var16_24;
                                        }
                                        v29 = var8_14;
                                    }
                                    if (var5_4) break block94;
                                    if (v29 != null) break block95;
                                    v29 = Lyrics.text(var15_21, Lyrics.b[32]);
                                }
                                var8_14 = v29;
                            }
                            if (var5_4) break;
                        }
                        v22 = var9_15;
                    }
                    if (v22 != null) {
                        return new String[]{var9_15, null};
                    }
                    v38 = var11_17;
                    if (var5_4) break block96;
                    if (v38 != 0) break block97;
                    v40 = var10_16;
                    if (var5_4) break block98;
                    if (v40 == null) break block97;
                    v40 = var10_16;
                }
                throw v40;
            }
            v38 = 2;
        }
        v43 = new String[v38];
        v43[0] = null;
        v43[1] = var8_14;
        return v43;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String netease(String string, String string2, long l) throws Exception {
        Iterator iterator;
        block88: {
            Iterator iterator2;
            String[] stringArray;
            block89: {
                boolean bl;
                block87: {
                    JsonElement jsonElement;
                    block85: {
                        JsonElement jsonElement2;
                        block86: {
                            block90: {
                                JsonElement jsonElement3;
                                block71: {
                                    long l2;
                                    block83: {
                                        long l3;
                                        block84: {
                                            JsonObject jsonObject;
                                            JsonObject jsonObject2;
                                            block70: {
                                                JsonObject jsonObject3;
                                                block68: {
                                                    block69: {
                                                        JsonElement jsonElement4;
                                                        block67: {
                                                            block66: {
                                                                stringArray = b;
                                                                jsonElement2 = Lyrics.request(stringArray[36] + Lyrics.enc(string2 + " " + string));
                                                                bl = a;
                                                                jsonElement4 = jsonElement2;
                                                                if (bl) break block66;
                                                                if (jsonElement4 == null) return null;
                                                                jsonElement4 = jsonElement2;
                                                            }
                                                            if (bl) break block67;
                                                            if (!jsonElement4.isJsonObject()) return null;
                                                            jsonElement4 = jsonElement2;
                                                        }
                                                        jsonObject3 = jsonElement4.getAsJsonObject();
                                                        if (bl) break block68;
                                                        if (jsonObject3.has(b[12])) break block69;
                                                        return null;
                                                    }
                                                    stringArray = b;
                                                    jsonObject3 = jsonElement2.getAsJsonObject().getAsJsonObject(stringArray[8]);
                                                }
                                                jsonObject2 = jsonObject3;
                                                jsonObject = jsonObject2;
                                                if (bl) break block70;
                                                if (jsonObject == null) return null;
                                                jsonObject = jsonObject2;
                                            }
                                            if (!jsonObject.has(b[22])) {
                                                return null;
                                            }
                                            l3 = Lyrics.a(14821, 44651470026372156L);
                                            double d = 0.0;
                                            stringArray = b;
                                            iterator2 = jsonObject2.getAsJsonArray(stringArray[34]).iterator();
                                            while (iterator2.hasNext()) {
                                                block82: {
                                                    double d2;
                                                    JsonObject jsonObject4;
                                                    block81: {
                                                        double d3;
                                                        double d4;
                                                        double d5;
                                                        block79: {
                                                            block78: {
                                                                double d6;
                                                                block77: {
                                                                    double d7;
                                                                    block76: {
                                                                        block75: {
                                                                            int n;
                                                                            block74: {
                                                                                block73: {
                                                                                    String string3;
                                                                                    JsonObject jsonObject5;
                                                                                    block72: {
                                                                                        JsonElement jsonElement5;
                                                                                        jsonElement3 = jsonElement5 = (JsonElement)iterator2.next();
                                                                                        if (bl) break block71;
                                                                                        jsonObject4 = jsonElement3.getAsJsonObject();
                                                                                        jsonObject5 = jsonObject4;
                                                                                        string3 = b[10];
                                                                                        if (bl) break block72;
                                                                                        if (!jsonObject5.has(string3)) continue;
                                                                                        jsonObject5 = jsonObject4;
                                                                                        stringArray = b;
                                                                                        string3 = stringArray[0];
                                                                                    }
                                                                                    n = jsonObject5.has(string3);
                                                                                    if (bl) break block73;
                                                                                    if (n == 0) {
                                                                                        continue;
                                                                                    }
                                                                                    long l4 = l - 0L;
                                                                                    n = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                                                                                }
                                                                                if (bl) break block74;
                                                                                if (n <= 0) break block75;
                                                                                d7 = Lyrics.duration(jsonObject4);
                                                                                if (bl) break block76;
                                                                                double d8 = Math.abs(d7 / 1000.0 - (double)l) - 3.0;
                                                                                n = d8 == 0.0 ? 0 : (d8 > 0.0 ? 1 : -1);
                                                                            }
                                                                            if (n > 0) continue;
                                                                        }
                                                                        d7 = 0.0;
                                                                    }
                                                                    d5 = d7;
                                                                    stringArray = b;
                                                                    for (JsonElement jsonElement6 : jsonObject4.getAsJsonArray(stringArray[45])) {
                                                                        d6 = d5;
                                                                        if (!bl) {
                                                                            stringArray = b;
                                                                            d5 = Math.max(d6, Lyrics.similarity(string2, Lyrics.orEmpty(jsonElement6.getAsJsonObject(), stringArray[11])));
                                                                            if (!bl) continue;
                                                                        }
                                                                        break block77;
                                                                    }
                                                                    stringArray = b;
                                                                    d6 = Lyrics.similarity(string, Lyrics.cleanTitle(Lyrics.orEmpty(jsonObject4, stringArray[28])));
                                                                }
                                                                d4 = d6;
                                                                d3 = d4;
                                                                if (bl) break block78;
                                                                if (d3 < 0.7) continue;
                                                                d3 = d5;
                                                            }
                                                            if (bl) break block79;
                                                            if (d3 < 0.5) {
                                                                continue;
                                                            }
                                                            d2 = d4;
                                                            if (bl) break block81;
                                                            d3 = d2 + d5;
                                                        }
                                                        if (!(d3 > d)) break block82;
                                                        d2 = d4 + d5;
                                                    }
                                                    d = d2;
                                                    stringArray = b;
                                                    l3 = jsonObject4.get(stringArray[54]).getAsLong();
                                                }
                                                if (!bl) continue;
                                            }
                                            l2 = l3;
                                            if (bl) break block83;
                                            if (l2 >= 0L) break block84;
                                            return null;
                                        }
                                        l2 = l3;
                                    }
                                    stringArray = b;
                                    jsonElement = Lyrics.request(stringArray[19] + l2);
                                    if (bl) break block90;
                                    jsonElement3 = jsonElement2 = jsonElement;
                                }
                                if (jsonElement3 == null) return null;
                                jsonElement = jsonElement2;
                            }
                            if (bl) break block85;
                            if (jsonElement.isJsonObject()) break block86;
                            return null;
                        }
                        jsonElement = jsonElement2;
                    }
                    iterator2 = jsonElement.getAsJsonObject();
                    iterator = iterator2;
                    if (bl) break block87;
                    if (!iterator.has(b[47])) return null;
                    iterator = iterator2;
                }
                if (bl) break block88;
                if (!iterator.has(b[46])) break block89;
                iterator = iterator2;
                if (bl) break block88;
                if (!iterator.get(b[49]).getAsBoolean()) break block89;
                return null;
            }
            stringArray = b;
            iterator = iterator2.getAsJsonObject(stringArray[27]);
        }
        String string4 = Lyrics.orEmpty((JsonObject)iterator, b[21]);
        if (!STAMP.matcher(string4).find()) return null;
        String string5 = string4;
        return string5;
    }

    static String[] query(String string, String string2) {
        int n;
        block31: {
            block32: {
                String string3;
                block33: {
                    Matcher matcher;
                    block34: {
                        Matcher matcher2;
                        boolean bl;
                        block27: {
                            block28: {
                                Matcher matcher3;
                                block29: {
                                    block30: {
                                        string2 = CHANNEL.matcher(string2.trim()).replaceAll("");
                                        matcher = QUOTED.matcher(string);
                                        bl = a;
                                        matcher2 = matcher;
                                        if (bl) break block27;
                                        if (!matcher2.matches()) break block28;
                                        matcher3 = matcher;
                                        if (bl) break block29;
                                        if (matcher3.group(1).isBlank()) break block30;
                                        string2 = matcher.group(1);
                                    }
                                    matcher3 = matcher;
                                }
                                string = matcher3.group(2);
                                if (!bl) break block32;
                            }
                            matcher2 = matcher = SPLIT.matcher(string);
                        }
                        n = matcher2.matches();
                        if (bl) break block31;
                        if (n == 0) break block32;
                        String string4 = Lyrics.squash(string2);
                        String string5 = Lyrics.squash(matcher.group(1));
                        n = string4.isEmpty() ? 1 : 0;
                        if (bl) break block31;
                        if (n != 0) break block32;
                        n = string5.isEmpty() ? 1 : 0;
                        if (bl) break block31;
                        if (n != 0) break block32;
                        string3 = string5;
                        if (bl) break block33;
                        if (string3.contains(string4)) break block34;
                        string3 = string4;
                        if (bl) break block33;
                        if (string3.contains(string5)) break block34;
                        n = Lyrics.squash(matcher.group(2)).contains(string4) ? 1 : 0;
                        if (bl) break block31;
                        if (n == 0) break block32;
                    }
                    string2 = matcher.group(1);
                    string3 = matcher.group(2);
                }
                string = string3;
            }
            n = 2;
        }
        String[] stringArray = new String[n];
        stringArray[0] = Lyrics.cleanTitle(string);
        stringArray[1] = Lyrics.mainArtist(string2);
        return stringArray;
    }

    private static String squash(String string) {
        return string.toLowerCase(Locale.ROOT).replaceAll(b[55], "");
    }

    private static String synced(JsonObject jsonObject, long l) {
        block12: {
            double d;
            double d2;
            block13: {
                long l2;
                double d3;
                block11: {
                    d3 = Lyrics.duration(jsonObject);
                    boolean bl = a;
                    long l3 = l - 0L;
                    l2 = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                    if (bl) break block11;
                    if (l2 <= 0) break block12;
                    d2 = d3;
                    d = 0.0;
                    if (bl) break block13;
                    double d4 = d2 - d;
                    l2 = d4 == 0.0 ? 0 : (d4 > 0.0 ? 1 : -1);
                }
                if (l2 <= 0) break block12;
                d2 = Math.abs(d3 - (double)l);
                d = 3.0;
            }
            if (d2 > d) {
                return null;
            }
        }
        return Lyrics.text(jsonObject, b[9]);
    }

    static JsonObject pick(JsonArray jsonArray, String string, String string2, long l) {
        JsonObject jsonObject = null;
        double d = 0.0;
        boolean bl = a;
        for (JsonElement jsonElement : jsonArray) {
            block67: {
                double d2;
                block66: {
                    double d3;
                    double d4;
                    boolean bl2;
                    JsonObject jsonObject2;
                    block64: {
                        block65: {
                            boolean bl3;
                            long l2;
                            block62: {
                                block63: {
                                    boolean bl4;
                                    double d5;
                                    double d6;
                                    boolean bl5;
                                    block61: {
                                        block60: {
                                            String string3;
                                            String string4;
                                            block59: {
                                                double d7;
                                                block58: {
                                                    double d8;
                                                    block57: {
                                                        String string5;
                                                        block56: {
                                                            block55: {
                                                                boolean bl6;
                                                                block54: {
                                                                    block53: {
                                                                        long l3;
                                                                        block50: {
                                                                            block52: {
                                                                                block51: {
                                                                                    JsonElement jsonElement2;
                                                                                    block48: {
                                                                                        jsonElement2 = jsonElement;
                                                                                        if (bl) break block48;
                                                                                        if (!jsonElement2.isJsonObject()) {
                                                                                            continue;
                                                                                        }
                                                                                        jsonElement2 = jsonElement;
                                                                                    }
                                                                                    jsonObject2 = jsonElement2.getAsJsonObject();
                                                                                    double d9 = Lyrics.duration(jsonObject2);
                                                                                    long l4 = l - 0L;
                                                                                    l3 = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                                                                                    if (bl) break block50;
                                                                                    if (l3 <= 0) break block51;
                                                                                    double d10 = d9 - 0.0;
                                                                                    l3 = d10 == 0.0 ? 0 : (d10 < 0.0 ? -1 : 1);
                                                                                    if (bl) break block50;
                                                                                    if (l3 <= 0) break block51;
                                                                                    double d11 = Math.abs(d9 - (double)l) - 3.0;
                                                                                    l3 = d11 == 0.0 ? 0 : (d11 < 0.0 ? -1 : 1);
                                                                                    if (bl) break block50;
                                                                                    if (l3 > 0) break block52;
                                                                                }
                                                                                l3 = 1;
                                                                                break block50;
                                                                            }
                                                                            l3 = 0;
                                                                        }
                                                                        l2 = l3;
                                                                        if (l2 == false || Lyrics.text(jsonObject2, b[14]) == null) break block53;
                                                                        bl6 = true;
                                                                        break block54;
                                                                    }
                                                                    bl6 = false;
                                                                }
                                                                bl5 = bl6;
                                                                if (bl5) break block55;
                                                                string5 = Lyrics.text(jsonObject2, b[42]);
                                                                if (bl) break block56;
                                                                if (string5 == null) {
                                                                    continue;
                                                                }
                                                            }
                                                            string5 = string;
                                                        }
                                                        String[] stringArray = b;
                                                        d6 = Lyrics.similarity(string5, Lyrics.cleanTitle(Lyrics.orEmpty(jsonObject2, stringArray[29])));
                                                        d5 = Lyrics.similarity(string2, Lyrics.mainArtist(Lyrics.orEmpty(jsonObject2, stringArray[43])));
                                                        d8 = d6;
                                                        if (bl) break block57;
                                                        if (d8 < 0.7) continue;
                                                        d8 = d5;
                                                    }
                                                    double d12 = d8 - 0.5;
                                                    d7 = d12 == 0.0 ? 0 : (d12 < 0.0 ? -1 : 1);
                                                    if (bl) break block58;
                                                    if (d7 < 0) {
                                                        continue;
                                                    }
                                                    d7 = (double)bl5;
                                                }
                                                String string6 = d7 != false ? Lyrics.text(jsonObject2, b[9]) : Lyrics.text(jsonObject2, b[32]);
                                                string4 = string6;
                                                string3 = string4;
                                                if (bl) break block59;
                                                if (string3 == null) break block60;
                                                string3 = string;
                                            }
                                            bl4 = Lyrics.latin(string3);
                                            if (bl) break block61;
                                            if (bl4 != Lyrics.latin(string4)) break block60;
                                            bl4 = true;
                                            break block61;
                                        }
                                        bl4 = false;
                                    }
                                    bl2 = bl4;
                                    d4 = d6 + d5;
                                    bl3 = bl5;
                                    if (bl) break block62;
                                    if (!bl3) break block63;
                                    bl3 = true;
                                    break block62;
                                }
                                bl3 = false;
                            }
                            d3 = (double)bl3;
                            if (bl) break block64;
                            d4 = d4 + d3;
                            if (l2 == false) break block65;
                            d3 = 0.5;
                            break block64;
                        }
                        d3 = 0.0;
                    }
                    double d13 = d4 + d3;
                    double d14 = bl2 ? 0.25 : 0.0;
                    d2 = d13 + d14;
                    if (bl) break block66;
                    if (!(d2 > d)) break block67;
                    jsonObject = jsonObject2;
                }
                d = d2;
            }
            if (!bl) continue;
        }
        return jsonObject;
    }

    static boolean latin(String string) {
        int n;
        block16: {
            int n2;
            block13: {
                int n3 = 0;
                boolean bl = a;
                int n4 = 0;
                int n5 = 0;
                while (n5 < string.length()) {
                    block15: {
                        block14: {
                            int n6;
                            block12: {
                                int n7 = string.codePointAt(n5);
                                n6 = n5;
                                if (bl) break block12;
                                n2 = n7;
                                if (bl) break block13;
                                n5 = n6 + Character.charCount(n2);
                                if (!Character.isLetter(n7)) {
                                    continue;
                                }
                                n6 = n7;
                            }
                            if (Character.UnicodeScript.of(n6) != Character.UnicodeScript.LATIN) break block14;
                            ++n3;
                            if (!bl) break block15;
                        }
                        ++n4;
                    }
                    if (!bl) continue;
                }
                n = n3;
                if (bl) break block16;
                n2 = n4;
            }
            n = n >= n2 ? 1 : 0;
        }
        return n != 0;
    }

    static String cleanTitle(String string) {
        String string2 = TAG.matcher(string).replaceAll("");
        boolean bl = a;
        String string3 = DASH_TAG.matcher(string2).replaceAll("");
        if (!bl) {
            string2 = string3;
            string2 = FEAT.matcher(string2).replaceAll("").replaceAll(b[2], " ").trim();
            string3 = string2.isEmpty() ? string.trim() : string2;
        }
        return string3;
    }

    static String mainArtist(String string) {
        String string2;
        block4: {
            String string3;
            block5: {
                string3 = ARTISTS.split(string, 2)[0].trim();
                boolean bl = a;
                string2 = string3;
                if (bl) break block4;
                if (!string2.isEmpty()) break block5;
                string2 = string.trim();
                break block4;
            }
            string2 = string3;
        }
        return string2;
    }

    
    static double similarity(String var0, String var1_1) {
        block25: {
            block26: {
                block24: {
                    var2_2 = Lyrics.a;
                    v0 = Lyrics.squash(var0);
                    if (!var2_2) {
                        var0 = v0;
                        var1_1 = Lyrics.squash(var1_1);
                        if (var0.equals(var1_1)) {
                            return 1.0;
                        }
                        v0 = var0;
                    }
                    if (var2_2) break block24;
                    if (!v0.isEmpty()) {
                    }
                    v0 = var1_1;
                }
                if (var2_2) break block25;
                if (!v0.isEmpty()) break block26;
lbl33:

                return 0.0;
            }
            v0 = var1_1;
        }
        var3_3 = new int[v0.length() + 1];
        var4_4 = 0;
        while (var4_4 <= var1_1.length()) {
            v6 = var3_3;
            v7 = var4_4;
            if (!var2_2) {
                v6[v7] = var4_4++;
                if (!var2_2) continue;
                break;
            }
        }
        for (var4_4 = 1; var4_4 <= var0.length(); ++var4_4) {
            v6 = var3_3;
            v7 = 0;
lbl55:

            var5_5 = v6[v7];
            v9 = var3_3;
            v10 = 0;
            v11 = var4_4;
            block18: while (true) {
                v9[v10] = v11;
                for (var6_6 = 1; var6_6 <= var1_1.length(); ++var6_6) {
                    block27: {
                        block28: {
                            var7_7 = var3_3[var6_6];
                            v9 = var3_3;
                            v10 = var6_6;
                            v11 = var3_3[var6_6];
                            if (var2_2) continue block18;
                            v12 = v11 + 1;
                            v13 = var3_3[var6_6 - 1];
                            v14 = 1;
                            if (var2_2) break block27;
                            v12 = Math.min(v12, v13 + v14);
                            v13 = var5_5;
                            if (var0.charAt(var4_4 - 1) != var1_1.charAt(var6_6 - 1)) break block28;
                            v14 = 0;
                            break block27;
                        }
                        v14 = 1;
                    }
                    v9[v10] = Math.min(v12, v13 + v14);
                    var5_5 = var7_7;
                    if (!var2_2) continue;
                }
                break;
            }
            if (!var2_2) continue;
        }
        return 1.0 - (double)var3_3[var1_1.length()] / (double)Math.max(var0.length(), var1_1.length());
    }

    static List<Lyrics$Line> plain(String string, long l) {
        ArrayList<Lyrics$Line> arrayList;
        block20: {
            long l2;
            boolean bl;
            ArrayList<String> arrayList2;
            block19: {
                long l3;
                long l4;
                block18: {
                    block16: {
                        arrayList2 = new ArrayList<String>();
                        String[] stringArray = string.split("\n");
                        bl = a;
                        int n = stringArray.length;
                        int n2 = 0;
                        while (n2 < n) {
                            block15: {
                                block17: {
                                    String string2 = stringArray[n2];
                                    if (bl) break block15;
                                    if (bl) break block16;
                                    if (string2.isBlank()) break block17;
                                    arrayList2.add(string2.trim());
                                }
                                ++n2;
                            }
                            if (!bl) continue;
                        }
                        l4 = l;
                        l3 = 0L;
                        if (bl) break block18;
                        if (l4 <= l3) break block16;
                        l2 = l;
                        break block19;
                    }
                    l4 = arrayList2.size();
                    l3 = Lyrics.a(3178, 6687613253205967280L);
                }
                l2 = l4 * l3;
            }
            long l5 = l2;
            ArrayList<Lyrics$Line> arrayList3 = new ArrayList<Lyrics$Line>(arrayList2.size());
            for (int i = 0; i < arrayList2.size(); ++i) {
                arrayList = arrayList3;
                if (!bl) {
                    arrayList.add(Lyrics.words(l5 * (long)i / (long)arrayList2.size(), (String)arrayList2.get(i), l5 * (long)(i + 1) / (long)arrayList2.size()));
                    if (!bl) continue;
                    break;
                }
                break block20;
            }
            arrayList = arrayList3;
        }
        return arrayList;
    }

    private static String text(JsonObject jsonObject, String string) {
        String string2 = Lyrics.orEmpty(jsonObject, string);
        String string3 = string2.isBlank() ? null : string2;
        return string3;
    }

    private static String orEmpty(JsonObject jsonObject, String string) {
        String string2;
        block11: {
            block9: {
                JsonObject jsonObject2;
                block10: {
                    JsonObject jsonObject3;
                    block8: {
                        boolean bl = a;
                        jsonObject3 = jsonObject;
                        if (bl) break block8;
                        if (!jsonObject3.has(string)) break block9;
                        jsonObject2 = jsonObject;
                        if (bl) break block10;
                        jsonObject3 = jsonObject2.get(string);
                    }
                    if (!jsonObject3.isJsonPrimitive()) break block9;
                    jsonObject2 = jsonObject.get(string);
                }
                string2 = jsonObject2.getAsString();
                break block11;
            }
            string2 = "";
        }
        return string2;
    }

    private static double duration(JsonObject jsonObject) {
        double d;
        block11: {
            block9: {
                String string;
                JsonObject jsonObject2;
                block10: {
                    JsonObject jsonObject3;
                    block8: {
                        boolean bl = a;
                        jsonObject3 = jsonObject;
                        if (bl) break block8;
                        if (!jsonObject3.has(b[16])) break block9;
                        jsonObject2 = jsonObject;
                        string = b[30];
                        if (bl) break block10;
                        jsonObject3 = jsonObject2.get(string);
                    }
                    if (!jsonObject3.isJsonPrimitive()) break block9;
                    jsonObject2 = jsonObject;
                    string = b[30];
                }
                d = jsonObject2.get(string).getAsDouble();
                break block11;
            }
            d = 0.0;
        }
        return d;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static JsonElement request(String string) throws Exception {
        int n = 0;
        boolean bl = a;
        while (true) {
            block20: {
                int n2;
                HttpResponse<String> httpResponse;
                block18: {
                    int n3;
                    block19: {
                        int n4;
                        block17: {
                            block16: {
                                String[] stringArray = b;
                                httpResponse = SmtcClient.HTTP.send(HttpRequest.newBuilder(URI.create(string)).header(stringArray[44], stringArray[25]).timeout(Duration.ofSeconds(Lyrics.a(13012, 7124839049261842177L))).GET().build(), HttpResponse.BodyHandlers.ofString());
                                n3 = httpResponse.statusCode();
                                if (n3 == 429) break block16;
                                n4 = n3;
                                if (bl) break block17;
                                if (bl) break block18;
                                if (n4 < 500) break block19;
                            }
                            n4 = n;
                        }
                        if (n4 != 0) throw new IOException(b[17] + n3 + b[48] + string);
                        Thread.sleep(Lyrics.a(16053, 1911292612234933098L));
                        if (bl) {
                            throw new IOException(b[17] + n3 + b[48] + string);
                        }
                        break block20;
                    }
                    n2 = n3;
                }
                if (n2 != 200) return null;
                JsonElement jsonElement = JsonParser.parseString((String)httpResponse.body());
                return jsonElement;
            }
            ++n;
        }
    }

    private static String enc(String string) {
        return URLEncoder.encode(string, StandardCharsets.UTF_8).replace("+", b[53]);
    }

    public static List<Lyrics$Line> parse(String string) {
        ArrayList<Lyrics$Line> arrayList = new ArrayList<Lyrics$Line>();
        for (String string2 : string.split("\n")) {
            Matcher matcher = STAMP.matcher(string2);
            ArrayList<Long> arrayList2 = new ArrayList<Long>();
            int n = 0;
            while (true) {
                if (!matcher.find() || matcher.start() != n) break;
                arrayList2.add(Lyrics.ms(matcher.group(1), matcher.group(2)));
                n = matcher.end();
            }
            String string3 = string2.substring(n).trim();
            if (CREDIT.matcher(string3).find()) {
                continue;
            }
            Iterator iterator = arrayList2.iterator();
            while (iterator.hasNext()) {
                long l = (Long)iterator.next();
                arrayList.add(new Lyrics$Line(l, string3, List.of()));
            }
        }
        arrayList.sort(Lyrics::lambda$parse$2);
        ArrayList arrayList3 = new ArrayList(arrayList.size());
        for (int i = 0; i < arrayList.size(); ++i) {
            Lyrics$Line lyrics$Line = (Lyrics$Line)arrayList.get(i);
            long l = i + 1 < arrayList.size() ? ((Lyrics$Line)arrayList.get(i + 1)).ms() : lyrics$Line.ms() + Lyrics.a(15042, 5959946542086616863L);
            long l2 = l;
            arrayList3.add(Lyrics.words(lyrics$Line.ms(), lyrics$Line.text(), l2));
        }
        return arrayList3;
    }

    private static Lyrics$Line words(long l, String string, long l2) {
        ArrayList<Lyrics$Word> arrayList = new ArrayList<Lyrics$Word>();
        ArrayList<Long> arrayList2 = new ArrayList<Long>();
        ArrayList<String> arrayList3 = new ArrayList<String>();
        Matcher matcher = WORD.matcher(string);
        while (matcher.find()) {
            arrayList2.add(Lyrics.ms(matcher.group(1), matcher.group(2)));
            arrayList3.add(matcher.group(3).trim());
        }
        if (!arrayList2.isEmpty()) {
            int n = 0;
            while (true) {
                block17: {
                    block16: {
                        if (n >= arrayList3.size()) break;
                        if (!((String)arrayList3.get(n)).isEmpty()) break block16;
                        break block17;
                    }
                    long l3 = n + 1 < arrayList2.size() ? (Long)arrayList2.get(n + 1) : l2;
                    long l4 = l3;
                    arrayList.add(new Lyrics$Word((String)arrayList3.get(n), (Long)arrayList2.get(n), l4));
                }
                ++n;
            }
            ArrayList<String> arrayList4 = new ArrayList<String>();
            for (Lyrics$Word lyrics$Word : arrayList) {
                arrayList4.add(lyrics$Word.text());
            }
            return new Lyrics$Line(l, String.join((CharSequence)" ", arrayList4), arrayList);
        }
        String[] stringArray = string.isEmpty() ? new String[]{} : string.split(b[41]);
        String[] stringArray2 = stringArray;
        int n = 0;
        for (String stringArray3 : stringArray2) {
            n += stringArray3.length();
        }
        long l5 = Math.min(Math.max(1L, l2 - l) * Lyrics.a(26204, 72379255070927751L) / Lyrics.a(31743, 2644871972039996968L), (long)n * Lyrics.a(28226, 3954910083305907098L));
        int n2 = 0;
        for (String string2 : stringArray2) {
            long l4 = l + l5 * (long)n2 / (long)Math.max(1, n);
            arrayList.add(new Lyrics$Word(string2, l4, l + l5 * (long)(n2 += string2.length()) / (long)Math.max(1, n)));
        }
        return new Lyrics$Line(l, string, arrayList);
    }

    private static long ms(String string, String string2) {
        return Long.parseLong(string) * Lyrics.a(26308, 3310091195276602138L) + Math.round(Double.parseDouble(string2) * 1000.0);
    }

    public static int at(List<Lyrics$Line> list, long l) {
        int n;
        block7: {
            int n2 = -1;
            boolean bl = a;
            while (n2 + 1 < list.size()) {
                long l2 = list.get(n2 + 1).ms() - l;
                n = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                if (bl || bl) break block7;
                if (n > 0) break;
                ++n2;
                if (!bl) continue;
                break;
            }
            n = n2;
        }
        return n;
    }

    private static int lambda$parse$2(Lyrics$Line lyrics$Line, Lyrics$Line lyrics$Line2) {
        return Long.compare(lyrics$Line.ms(), lyrics$Line2.ms());
    }

    
    private static void lambda$get$1(String string, NowPlaying nowPlaying) {
        Lyrics$Song lyrics$Song;
        block13: {
            Object object;
            block14: {
                boolean bl;
                block12: {
                    block11: {
                        bl = a;
                        Object object2 = string;
                        if (bl) break block11;
                        if (((String)object2).equals(wanted)) break block12;
                        object2 = CACHE.remove(string);
                    }
                    return;
                }
                lyrics$Song = NONE;
                long l = Lyrics.a(9508, 4544370802464516340L);
                try {
                    lyrics$Song = Lyrics.fetch(nowPlaying);
                }
                catch (Exception exception) {
                    l = Lyrics.a(26875, 8565840261826520365L);
                    Aurora.LOG.debug(b[7], (Throwable)exception);
                }
                object = lyrics$Song;
                if (bl) break block13;
                if (object != NONE) break block14;
                MISSED.put(string, System.currentTimeMillis() + l);
                if (!bl) break block13;
            }
            object = MISSED.remove(string);
        }
        CACHE.put(string, lyrics$Song);
    }

    private static Thread lambda$static$0(Runnable runnable) {
        Thread thread = new Thread(runnable, b[15]);
        thread.setDaemon(true);
        return thread;
    }

    /*
     * Exception decompiling
     */
    static {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: CONTINUE without a while class org.benf.cfr.reader.bytecode.analysis.parse.statement.AssignmentSimple
         *     at org.benf.cfr.reader.bytecode.analysis.parse.statement.GotoStatement.getTargetStartBlock(GotoStatement.java:102)
         *     at org.benf.cfr.reader.bytecode.analysis.parse.statement.GotoStatement.getStructuredStatement(GotoStatement.java:116)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.getStructuredStatementPlaceHolder(Op03SimpleStatement.java:550)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:727)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:845)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1042)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:929)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:73)
         *     at org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x4DDC) & Short.MAX_VALUE;
        if (d[n2] == null) {
            Lyrics.d[n2] = c[n2] ^ l;
        }
        return d[n2];
    }
}
