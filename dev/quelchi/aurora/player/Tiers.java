package dev.quelchi.aurora.player;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.NametagsModule$Gamemode;
import dev.quelchi.aurora.player.Tiers$Entry;
import dev.quelchi.aurora.player.Tiers$Mode;
import dev.quelchi.aurora.player.Tiers$Source;
import java.lang.invoke.LambdaMetafactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;
import net.minecraft.text.StyleSpriteSource;
import net.minecraft.util.Identifier;

public final class Tiers {
    private static final Pattern NON_ALNUM;
    private static final String API;
    private static final Tiers$Mode[] MCTIERS_MODES;
    private static final Tiers$Mode[] PVPTIERS_MODES;
    private static final Tiers$Mode[] SUBTIERS_MODES;
    private static final Tiers$Mode[] PVPHQ_MODES;
    private static volatile Tiers$Source source;
    private static final String AGENT;
    public static final String PENDING = "\u2026";
    public static final String UNRANKED = "";
    private static final HttpClient HTTP;
    private static final Map<UUID, Map<String, Tiers$Entry>> CACHE;
    private static final Map<UUID, Boolean> INFLIGHT;
    private static final Map<UUID, Boolean> MISSING;
    private static final Map<UUID, Long> RETRY_AFTER;
    private static final long RETRY_BACKOFF_MS;
    private static final ExecutorService FETCHES;
    public static final NametagsModule$Gamemode[] RANKED;
    private static final Map<String, Map<NametagsModule$Gamemode, String>> OVERRIDES;
    private static final Map<String, NametagsModule$Gamemode> SLUGS;
    private static final Map<String, NametagsModule$Gamemode> PVPTIERS_SLUGS;
    private static final Map<String, NametagsModule$Gamemode> SUBTIERS_SLUGS;
    private static final Map<String, NametagsModule$Gamemode> PVPHQ_LADDERS;
    private static final Map<String, String> FALLBACK_ITEMS;
    private static final Map<String, String> PVPHQ_ART;
    private static final Map<String, String> SUBTIERS_ART;
    public static final StyleSpriteSource ICON_FONT;
    private static final String[] a;
    private static final long b;
    private static final long[] c;
    private static final Long[] d;

    private Tiers() {
    }

    
    public static Tiers$Mode[] modesFor(Tiers$Source tiers$Source) {
        Tiers$Mode[] tiers$ModeArray;
        switch (tiers$Source.ordinal()) {
            default: {
                throw new MatchException(null, null);
            }
            case 0: {
                tiers$ModeArray = MCTIERS_MODES;
                return tiers$ModeArray;
            }
            case 1: {
                tiers$ModeArray = PVPTIERS_MODES;
                return tiers$ModeArray;
            }
            case 2: {
                tiers$ModeArray = SUBTIERS_MODES;
                return tiers$ModeArray;
            }
            case 3: 
        }
        tiers$ModeArray = PVPHQ_MODES;
        return tiers$ModeArray;
    }

    public static Tiers$Mode[] modes() {
        return Tiers.modesFor(source);
    }

    public static Tiers$Source source() {
        return source;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void setSource(Tiers$Source tiers$Source) {
        Tiers$Source tiers$Source2;
        block7: {
            block8: {
                boolean bl;
                block6: {
                    bl = Tiers$Mode.a;
                    tiers$Source2 = tiers$Source;
                    if (bl) break block6;
                    if (tiers$Source2 == null) return;
                    tiers$Source2 = tiers$Source;
                }
                if (bl) break block7;
                if (tiers$Source2 != source) break block8;
                return;
            }
            tiers$Source2 = tiers$Source;
        }
        source = tiers$Source2;
        Tiers.clear();
    }

    private static Map<String, Map<NametagsModule$Gamemode, String>> buildOverrides() {
        HashMap<String, Map<NametagsModule$Gamemode, String>> hashMap = new HashMap<String, Map<NametagsModule$Gamemode, String>>();
        String[] stringArray = a;
        Tiers.all(hashMap, stringArray[91], stringArray[111]);
        return hashMap;
    }

    private static void all(Map<String, Map<NametagsModule$Gamemode, String>> map, String string, String string2) {
        EnumMap<NametagsModule$Gamemode, String> enumMap = new EnumMap<NametagsModule$Gamemode, String>(NametagsModule$Gamemode.class);
        for (NametagsModule$Gamemode nametagsModule$Gamemode : RANKED) {
            enumMap.put(nametagsModule$Gamemode, string2);
        }
        map.put(string, enumMap);
    }

    public static String tierFor(UUID uUID, String string) {
        String string2;
        block9: {
            Tiers$Entry tiers$Entry;
            block6: {
                Tiers$Entry tiers$Entry2;
                block7: {
                    block8: {
                        tiers$Entry2 = Tiers.entryFor(uUID, string);
                        boolean bl = Tiers$Mode.a;
                        tiers$Entry = tiers$Entry2;
                        if (bl) break block6;
                        if (tiers$Entry != null) break block7;
                        if (!Tiers.pendingFor(uUID)) break block8;
                        string2 = PENDING;
                        break block9;
                    }
                    string2 = UNRANKED;
                    break block9;
                }
                tiers$Entry = tiers$Entry2;
            }
            string2 = tiers$Entry.tier();
        }
        return string2;
    }

    private static Tiers$Entry entryFor(UUID uUID, String string) {
        block27: {
            block28: {
                Object object;
                boolean bl;
                block25: {
                    block26: {
                        block23: {
                            block24: {
                                Object object2;
                                block21: {
                                    block22: {
                                        block20: {
                                            bl = Tiers$Mode.a;
                                            if (uUID == null) break block20;
                                            object2 = string;
                                            if (bl) break block21;
                                            if (object2 != null) break block22;
                                        }
                                        return null;
                                    }
                                    object2 = CACHE.get(uUID);
                                }
                                Map<UUID, Boolean> map = (Map<UUID, Boolean>)object2;
                                object = map;
                                if (bl) break block23;
                                if (object == null) break block24;
                                return (Tiers$Entry)((Object)map.get(Tiers.norm(string)));
                            }
                            object = MISSING;
                        }
                        if (bl) break block25;
                        if (!object.containsKey(uUID)) break block26;
                        return null;
                    }
                    object = RETRY_AFTER.get(uUID);
                }
                Long l = (Long)object;
                if (bl) break block27;
                if (l == null) break block28;
                if (System.currentTimeMillis() >= l) break block28;
                return null;
            }
            Tiers.fetch(uUID);
        }
        return null;
    }

    public static boolean failedFor(UUID uUID) {
        int n;
        block17: {
            boolean bl;
            block19: {
                block18: {
                    boolean bl2;
                    block16: {
                        block15: {
                            Long l;
                            block14: {
                                Comparable<UUID> comparable;
                                block12: {
                                    block13: {
                                        bl2 = Tiers$Mode.a;
                                        comparable = uUID;
                                        if (bl2) break block12;
                                        if (comparable != null) break block13;
                                        l = null;
                                        break block14;
                                    }
                                    comparable = RETRY_AFTER.get(uUID);
                                }
                                l = (Long)comparable;
                            }
                            Long l2 = l;
                            if (l2 == null) break block15;
                            long l3 = System.currentTimeMillis() - l2;
                            n = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                            if (bl2) break block16;
                            if (n >= 0) break block15;
                            n = 1;
                            break block16;
                        }
                        n = 0;
                    }
                    if (!bl2) break block17;
                    if (!Module.a) break block18;
                    bl = false;
                    break block19;
                }
                bl = true;
            }
            Module.a = bl;
        }
        return n != 0;
    }

    private static boolean pendingFor(UUID uUID) {
        boolean bl;
        block10: {
            block8: {
                Map<UUID, Object> map;
                boolean bl2;
                block9: {
                    bl2 = Tiers$Mode.a;
                    if (uUID == null) break block8;
                    map = CACHE;
                    if (bl2) break block9;
                    if (map.containsKey(uUID)) break block8;
                    map = MISSING;
                }
                bl = map.containsKey(uUID);
                if (bl2) break block10;
                if (bl) break block8;
                bl = true;
                break block10;
            }
            bl = false;
        }
        return bl;
    }

    private static void fetch(UUID uUID) {
        if (INFLIGHT.putIfAbsent(uUID, Boolean.TRUE) != null) {
            return;
        }
        Tiers$Source tiers$Source = source;
        FETCHES.execute(() -> Tiers.lambda$fetch$1(tiers$Source, uUID));
    }

    
    private static Map<String, Tiers$Entry> parse(String var0) {
        block46: {
            block47: {
                block45: {
                    block61: {
                        block60: {
                            var2_1 = new HashMap<String, Tiers$Entry>();
                            var1_2 = Tiers$Mode.a;
                            var3_3 = JsonParser.parseString((String)var0).getAsJsonObject();
                            v0 = var3_3;
                            if (var1_2) break block45;
                            if (!v0.has(Tiers.a[22])) // goto
                            break block60;
                            catch (Exception v1) {
                                throw v1;
                            }
                        }
                        v2 = var3_3;
                        if (var1_2) break block46;
                        break block61;
                        catch (Exception v3) {
                            throw v3;
                        }
                    }
                    v0 = v2.get(Tiers.a[52]);
                }
                if (v0.isJsonObject()) break block47;
lbl27:

                return var2_1;
            }
            var11_4 = Tiers.a;
            v2 = var3_3.getAsJsonObject(var11_4[52]);
        }
        var4_5 = v2;
        for (String var6_7 : var4_5.keySet()) {
            block50: {
                block48: {
                    block49: {
                        v6 = var4_5;
                        if (var1_2) break block48;
                        if (!v6.get(var6_7).isJsonObject()) {
                            continue;
                        }
                        break block49;
                        catch (Exception v7) {
                            throw v7;
                        }
                    }
                    v6 = var4_5.getAsJsonObject(var6_7);
                }
                var7_8 = v6;
                v9 = var7_8;
                if (var1_2) // goto
                if (!v9.has(Tiers.a[7])) {
                    continue;
                }
                break block50;
                catch (Exception v10) {
                    throw v10;
                }
            }
            try {
                block57: {
                    block59: {
                        block58: {
                            block56: {
                                block66: {
                                    block65: {
                                        block64: {
                                            block55: {
                                                block54: {
                                                    block63: {
                                                        block53: {
                                                            block52: {
                                                                block51: {
                                                                    block62: {
                                                                        v9 = var7_8;
lbl66:

                                                                        v12 = Tiers.a[100];
                                                                        if (var1_2) break block51;
                                                                        if (!v9.has(v12)) break block52;
                                                                        break block62;
                                                                        catch (Exception v13) {
                                                                            throw v13;
                                                                        }
                                                                    }
                                                                    v9 = var7_8;
                                                                    v12 = Tiers.a[105];
                                                                }
                                                                v15 = v9.get(v12).getAsBoolean();
                                                                if (var1_2) break block53;
                                                                if (!v15) break block52;
                                                                v15 = true;
                                                                break block53;
                                                            }
                                                            v15 = false;
                                                        }
                                                        var8_9 = v15;
                                                        var11_4 = Tiers.a;
                                                        var9_11 = var7_8.get(var11_4[109]).getAsInt();
                                                        v17 = var7_8;
                                                        if (var1_2) break block54;
                                                        if (!v17.has(var11_4[120])) break block55;
                                                        break block63;
                                                        catch (Exception v18) {
                                                            throw v18;
                                                        }
                                                    }
                                                    v17 = var7_8.get(Tiers.a[117]);
                                                }
                                                v20 = v17.getAsInt();
                                                break block64;
                                            }
                                            v20 = 0;
                                        }
                                        var10_12 = v20;
                                        v21 = var8_9;
                                        if (var1_2) break block56;
                                        if (!v21) break block57;
                                        break block65;
                                        catch (Exception v22) {
                                            throw v22;
                                        }
                                    }
                                    v23 = var7_8;
                                    if (var1_2) break block58;
                                    break block66;
                                    catch (Exception v24) {
                                        throw v24;
                                    }
                                }
                                v21 = Tiers.has(v23, Tiers.a[62]);
                            }
                            if (!v21) break block57;
                            v26 = var7_8;
                            v27 = Tiers.a[11];
                            if (var1_2) break block59;
                            var9_11 = v26.get(v27).getAsInt();
                            v23 = var7_8;
                        }
                        if (!Tiers.has(v23, Tiers.a[3])) break block57;
                        v26 = var7_8;
                        v27 = Tiers.a[115];
                    }
                    var10_12 = v26.get(v27).getAsInt();
                }
                v30 = var2_1;
                v31 = Tiers.norm(var6_7);
                v32 = v33;
                v34 = v33;
                v35 = var8_9 != false ? "R" : "";
                v32(v35 + (var10_12 == 0 ? "H" : "L") + "T" + var9_11, -1);
                v30.put(v31, v34);
            }
            catch (Exception var8_10) {
            }
            if (!var1_2) continue;
        }
        return var2_1;
    }

    private static boolean has(JsonObject jsonObject, String string) {
        boolean bl;
        block8: {
            block7: {
                String string2;
                JsonObject jsonObject2;
                boolean bl2;
                block6: {
                    bl2 = Tiers$Mode.a;
                    jsonObject2 = jsonObject;
                    string2 = string;
                    if (bl2) break block6;
                    if (!jsonObject2.has(string2)) break block7;
                    jsonObject2 = jsonObject;
                    string2 = string;
                }
                bl = jsonObject2.get(string2).isJsonNull();
                if (bl2) break block8;
                if (bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    
    private static String idFor(Tiers$Source tiers$Source, UUID uUID) {
        String string;
        switch (tiers$Source.ordinal()) {
            case 1: 
            case 2: {
                string = uUID.toString().replace("-", UNRANKED);
                return string;
            }
        }
        string = uUID.toString();
        return string;
    }

    
    private static Map<String, Tiers$Entry> parsePvpHq(String var0) {
        block34: {
            block35: {
                block33: {
                    block43: {
                        block42: {
                            block31: {
                                block32: {
                                    block41: {
                                        var2_1 = new HashMap<String, Tiers$Entry>();
                                        var1_2 = Tiers$Mode.a;
                                        var3_3 = JsonParser.parseString((String)var0);
                                        v0 = var3_3;
                                        if (var1_2) break block31;
                                        if (v0.isJsonObject()) break block32;
                                        break block41;
                                        catch (Exception v1) {
                                            throw v1;
                                        }
                                    }
                                    return var2_1;
                                }
                                v0 = var3_3;
                            }
                            var4_4 = v0.getAsJsonObject();
                            v3 = var4_4;
                            v4 = Tiers.a[42];
                            if (var1_2) break block33;
                            if (!v3.has(v4)) // goto
                            break block42;
                            catch (Exception v5) {
                                throw v5;
                            }
                        }
                        v3 = var4_4;
                        if (var1_2) break block34;
                        break block43;
                        catch (Exception v6) {
                            throw v6;
                        }
                    }
                    v4 = Tiers.a[12];
                }
                if (v3.get(v4).isJsonObject()) break block35;
lbl44:

                return var2_1;
            }
            var10_5 = Tiers.a;
            v3 = var4_4.getAsJsonObject(var10_5[12]);
        }
        var5_6 = v3;
        for (String var7_8 : var5_6.keySet()) {
            block38: {
                block36: {
                    block37: {
                        v9 = var5_6;
                        if (var1_2) break block36;
                        if (!v9.get(var7_8).isJsonObject()) {
                            continue;
                        }
                        break block37;
                        catch (Exception v10) {
                            throw v10;
                        }
                    }
                    v9 = var5_6.getAsJsonObject(var7_8);
                }
                var8_9 = v9;
                v12 = var8_9;
                if (var1_2) // goto
                if (!Tiers.has(v12, Tiers.a[88])) {
                    continue;
                }
                break block38;
                catch (Exception v13) {
                    throw v13;
                }
            }
            try {
                block45: {
                    block40: {
                        block39: {
                            block44: {
                                v12 = var8_9;
lbl83:

                                if (var1_2) break block39;
                                if (!Tiers.has(v12, Tiers.a[107])) break block40;
                                break block44;
                                catch (Exception v15) {
                                    throw v15;
                                }
                            }
                            v12 = var8_9.get(Tiers.a[101]);
                        }
                        v17 = v12.getAsInt();
                        break block45;
                    }
                    v17 = -1;
                }
                var9_10 = v17;
                var2_1.put(Tiers.norm(var7_8), new Tiers$Entry(var8_9.get(Tiers.a[30]).getAsString(), var9_10));
            }
            catch (Exception var9_11) {
            }
            if (!var1_2) continue;
        }
        return var2_1;
    }

    private static Map<String, NametagsModule$Gamemode> buildSlugs() {
        HashMap<String, NametagsModule$Gamemode> hashMap = new HashMap<String, NametagsModule$Gamemode>();
        String[] stringArray = a;
        hashMap.put(stringArray[133], NametagsModule$Gamemode.Vanilla);
        hashMap.put(stringArray[8], NametagsModule$Gamemode.UHC);
        hashMap.put(stringArray[97], NametagsModule$Gamemode.Pot);
        hashMap.put(stringArray[40], NametagsModule$Gamemode.NethPot);
        hashMap.put(stringArray[125], NametagsModule$Gamemode.SMP);
        hashMap.put(stringArray[28], NametagsModule$Gamemode.Sword);
        hashMap.put(stringArray[98], NametagsModule$Gamemode.Axe);
        hashMap.put(stringArray[70], NametagsModule$Gamemode.Mace);
        return hashMap;
    }

    
    private static NametagsModule$Gamemode artFor(String string) {
        Object object;
        block10: {
            String string2;
            block9: {
                boolean bl;
                block7: {
                    block8: {
                        string2 = Tiers.norm(string);
                        bl = Tiers$Mode.a;
                        object = string2;
                        if (bl) break block7;
                        if (!object.equals(a[134])) break block8;
                        String[] stringArray = a;
                        string2 = stringArray[133];
                        if (!bl) break block9;
                    }
                    object = string2;
                }
                if (bl) break block10;
                if (object.equals(a[79])) {
                    string2 = a[40];
                }
            }
            object = SLUGS.get(string2);
        }
        return (NametagsModule$Gamemode)((Object)object);
    }

    public static String glyphFor(String string) {
        String string2;
        block6: {
            NametagsModule$Gamemode nametagsModule$Gamemode;
            block4: {
                NametagsModule$Gamemode nametagsModule$Gamemode2;
                block5: {
                    nametagsModule$Gamemode2 = Tiers.artFor(string);
                    boolean bl = Tiers$Mode.a;
                    nametagsModule$Gamemode = nametagsModule$Gamemode2;
                    if (bl) break block4;
                    if (nametagsModule$Gamemode != null) break block5;
                    string2 = null;
                    break block6;
                }
                nametagsModule$Gamemode = nametagsModule$Gamemode2;
            }
            string2 = Tiers.glyph(nametagsModule$Gamemode);
        }
        return string2;
    }

    public static String glyph(NametagsModule$Gamemode nametagsModule$Gamemode) {
        String string;
        block6: {
            char c;
            block4: {
                char c2;
                block5: {
                    c2 = Tiers.indexOf(nametagsModule$Gamemode);
                    boolean bl = Tiers$Mode.a;
                    c = c2;
                    if (bl) break block4;
                    if (c >= '\u0000') break block5;
                    string = null;
                    break block6;
                }
                c = (char)((int)b + c2);
            }
            string = String.valueOf(c);
        }
        return string;
    }

    private static int indexOf(NametagsModule$Gamemode nametagsModule$Gamemode) {
        boolean bl = Tiers$Mode.a;
        for (int i = 0; i < RANKED.length; ++i) {
            if (RANKED[i] != nametagsModule$Gamemode) continue;
            return i;
        }
        return -1;
    }

    private static String norm(String string) {
        return NON_ALNUM.matcher(string.toLowerCase(Locale.ROOT)).replaceAll(UNRANKED);
    }

    private static void backOff(UUID uUID) {
        RETRY_AFTER.put(uUID, System.currentTimeMillis() + Tiers.a(9090, 2717429555951716946L) + ThreadLocalRandom.current().nextLong(Tiers.a(11261, 5274525692199060015L)));
    }

    public static void clear() {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    CACHE.clear();
                    boolean bl2 = Tiers$Mode.a;
                    MISSING.clear();
                    INFLIGHT.clear();
                    RETRY_AFTER.clear();
                    if (!Module.a) break block4;
                    if (!bl2) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            Tiers$Mode.a = bl;
        }
    }

    
    private static void lambda$fetch$1(Tiers$Source tiers$Source, UUID uUID) {
        boolean bl = Tiers$Mode.a;
        try {
            Map<String, Tiers$Entry> map;
            UUID uUID2;
            Map<UUID, Map<String, Tiers$Entry>> map2;
            block27: {
                Object object;
                block25: {
                    HttpResponse<String> httpResponse;
                    block26: {
                        block30: {
                            block29: {
                                block24: {
                                    Object object2;
                                    int n;
                                    block22: {
                                        block23: {
                                            block21: {
                                                block20: {
                                                    String[] stringArray = a;
                                                    HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(tiers$Source.api + Tiers.idFor(tiers$Source, uUID))).header(stringArray[106], stringArray[25]).timeout(Duration.ofSeconds(Tiers.a(23319, 8297121651525923526L))).GET().build();
                                                    httpResponse = HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                                                    if (bl) break block20;
                                                    if (tiers$Source == source) break block21;
                                                }
                                                return;
                                            }
                                            n = httpResponse.statusCode();
                                            if (bl) break block22;
                                            if (n != 404) break block23;
                                            MISSING.put(uUID, Boolean.TRUE);
                                            return;
                                        }
                                        object2 = httpResponse;
                                        if (bl) break block24;
                                        n = object2.statusCode() / 100;
                                    }
                                    if (n == 2) break block29;
                                    Tiers.backOff(uUID);
                                    object2 = INFLIGHT.remove(uUID);
                                }
                                return;
                            }
                            map2 = CACHE;
                            uUID2 = uUID;
                            object = tiers$Source;
                            if (bl) break block25;
                            if (object != Tiers$Source.PvPHQ) break block26;
                            break block30;
                            catch (Exception exception) {
                                throw exception;
                            }
                        }
                        map = Tiers.parsePvpHq(httpResponse.body());
                        break block27;
                    }
                    object = httpResponse.body();
                }
                map = Tiers.parse((String)object);
            }
            map2.put(uUID2, map);
            RETRY_AFTER.remove(uUID);
        }
        catch (Exception exception) {
            Tiers.backOff(uUID);
        }
        finally {
            INFLIGHT.remove(uUID);
        }
    }

    private static Thread lambda$static$0(Runnable runnable) {
        Thread thread = new Thread(runnable, a[92]);
        thread.setDaemon(true);
        return thread;
    }

    
    static {
        block29: {
            block28: {
                block30: {
                    block27: {
                        block26: {
                            var15 = new String[140];
                            var13_1 = 0;
                            var12_2 = "t;q\f\u000b\\4v6/=IJ.s\u0005\u0003l\u0012Q\bI?s\u0002\u0013?_J\u0003J7b\u000b]3s\u0004#!Tf)\u007f\u0019\u0007K?v6.*T\u0004M3w\u001b\u0003L2q\u0007O;|\u0000 #Q\u0012]3s\u0004#!Tf9z\f?;@U;f\f\tI?s\u0002\u0013;Y\\(\u0004];f\b\n}3s*>6CM;~\n]3s\n>6CM;~\u0004t\u001bQ,\u0006}?p\u001c*)\rJ*~\b?'oI5f\u0000#!\u0006Q.q\b>;\u0007o;|\u0000 #Q\u0005P9}\u0007?\u0007]3s6?\"@\bK;|\u0002%!WJ\u0005j*w\f(\u000b\\4v6/=IJ.s\u0005#x/`\u0006>.\u001f\bt\"IddXM.b\u001av`\u001fX/`\u0006>.]V><\u001155\u0019\u0003[5e\tV=d\b\"&\\U;\u0005J-}\u001b(\tv\u001dD\b\"&\\U;\u000b^(s\u00078*Tm3w\u001b\u000e]3s\u0004#!TO;|\u0000 #Q\tJ*w\b>\"QZ?\u0007z(k\u001a8.\\\bW?f\u0001\u0013?_M\u0006\\6k\u001d>.\u000b}\u0013S$\u0003\u0001tf\t_9\fW?f\u0001)=YM?b\u00068\u0003i5f\u0007z(w\f<*B\u0006W?f\u0001#?\u0003x\"w\u0004];f\b\u0003{5e\u0007Z5\u007f\u0019-<C\u0007Z(w\f<*B\u0006W?f\u0001#?\u0007w?f\u0001\u001c D\u0003{?v\n]3s\u0004#!TJ7b#x/`\u0006>.\u001f\bt\"IddXM.b\u001av`\u001fX/`\u0006>.]V><\u001155\u0019\u0005J*w\b>\bK;|\u0002%!WJ\u0007T;|\u00019!D\u0005J-}\u001b(\u0007t;|\u00019!D\n]3s\n>6CM;~\u0007w?f\u0001\u001c D\u0005J*w\f(\u0007M({\r)!D\u0005j-}\u001b(\u0007T;|\u00019!D\tI?s\u0002\u0013;Y\\(\tb\u0004sD6\u007f\u001d\u0000\u0007\u0003L2q\u0007q\u000eM*\r\u001dd\u0006]?p\u001c*)\u0004t;q\f\u000f]3s\u0004#!Tf,s\u0007%#\\X\nj\nW(\u001e\u0010}x\u0019W\u0004T;q\f\n]3s\u0004#!TJ7b\u0006]?p\u001c*)\u0006|6k\u001d>.\u0003[5e\u0003j\u0017B\u0003x\u0002W\tV=d\b\"&\\U;\u0007M({\r)!D\u0007W?f\u0001< D\u000b]3s6/=IJ.s\u0005\u0007Z(w\f<*B\u0006]3s\u001a!?\u0012]3s\u0004#!Tf9z\f?;@U;f\f\u0003I5f\u0005j-}\u001b(\rw\u001fF!\t\u001dym\u001fM9\u0003\u001b\u0005j*w\b>\u000b^(s\u00078*Tm3w\u001b\u0003X\"w\u0003j\u0017B\u0003Hck\fX/`\u0006>.\u001dM3w\u001b?\u0006\\6k\u001d>.\tJ*w\b>\"QZ?\bT3|\f/.BM\u0003[?v\u0003I5f\u0003X\"w\u0003x\"w\u0007K?f\u0000>*T\u0002M(#Q.f\u0019?u\u001f\u00167q\u001d%*BJt|\f8`QI3=\u001f~`@K5t\u0000 *\u001f\u0007m({\r)!D\u0003[?v\u0007K?f\u0000>*T\nl)w\u001ba\u000eW\\4f\u0002M(\u0007o;|\u0000 #Q\u0004M3w\u001b\u0006Q.q\b>;\u0003q\u000e#\u0007o\u001b\\ \u0000\u0003q\u0004z;`\u001d\u0006}3s:\u0001\u001f\bI?s\u0002\u0013?_J\u0003i\u0015F\u0003I5a\u0004T;q\f\nV=M\u001f-!YU6s\u0003I5a\u0005j\r];\b\fW?f\u0001)=YM?b\u00068\u0004Z;`\u001d\u0007Z(k\u001a8.\\\u0003J7b\nV=M\u001f-!YU6s\u0007W?f\u0001< D\u0003l\u0012Q\u0006]3s\u001a!?\u0003i5f\u0006X/`\u0006>.\bt3|\f/.BM\u0007O;|\u0000 #Q\u0007Z(k\u001a8.\\\u0005J*w\f(\bT3|\f/.BM\u0005J/u\b>";
                            var14_3 = "t;q\f\u000b\\4v6/=IJ.s\u0005\u0003l\u0012Q\bI?s\u0002\u0013?_J\u0003J7b\u000b]3s\u0004#!Tf)\u007f\u0019\u0007K?v6.*T\u0004M3w\u001b\u0003L2q\u0007O;|\u0000 #Q\u0012]3s\u0004#!Tf9z\f?;@U;f\f\tI?s\u0002\u0013;Y\\(\u0004];f\b\n}3s*>6CM;~\n]3s\n>6CM;~\u0004t\u001bQ,\u0006}?p\u001c*)\rJ*~\b?'oI5f\u0000#!\u0006Q.q\b>;\u0007o;|\u0000 #Q\u0005P9}\u0007?\u0007]3s6?\"@\bK;|\u0002%!WJ\u0005j*w\f(\u000b\\4v6/=IJ.s\u0005#x/`\u0006>.\u001f\bt\"IddXM.b\u001av`\u001fX/`\u0006>.]V><\u001155\u0019\u0003[5e\tV=d\b\"&\\U;\u0005J-}\u001b(\tv\u001dD\b\"&\\U;\u000b^(s\u00078*Tm3w\u001b\u000e]3s\u0004#!TO;|\u0000 #Q\tJ*w\b>\"QZ?\u0007z(k\u001a8.\\\bW?f\u0001\u0013?_M\u0006\\6k\u001d>.\u000b}\u0013S$\u0003\u0001tf\t_9\fW?f\u0001)=YM?b\u00068\u0003i5f\u0007z(w\f<*B\u0006W?f\u0001#?\u0003x\"w\u0004];f\b\u0003{5e\u0007Z5\u007f\u0019-<C\u0007Z(w\f<*B\u0006W?f\u0001#?\u0007w?f\u0001\u001c D\u0003{?v\n]3s\u0004#!TJ7b#x/`\u0006>.\u001f\bt\"IddXM.b\u001av`\u001fX/`\u0006>.]V><\u001155\u0019\u0005J*w\b>\bK;|\u0002%!WJ\u0007T;|\u00019!D\u0005J-}\u001b(\u0007t;|\u00019!D\n]3s\n>6CM;~\u0007w?f\u0001\u001c D\u0005J*w\f(\u0007M({\r)!D\u0005j-}\u001b(\u0007T;|\u00019!D\tI?s\u0002\u0013;Y\\(\tb\u0004sD6\u007f\u001d\u0000\u0007\u0003L2q\u0007q\u000eM*\r\u001dd\u0006]?p\u001c*)\u0004t;q\f\u000f]3s\u0004#!Tf,s\u0007%#\\X\nj\nW(\u001e\u0010}x\u0019W\u0004T;q\f\n]3s\u0004#!TJ7b\u0006]?p\u001c*)\u0006|6k\u001d>.\u0003[5e\u0003j\u0017B\u0003x\u0002W\tV=d\b\"&\\U;\u0007M({\r)!D\u0007W?f\u0001< D\u000b]3s6/=IJ.s\u0005\u0007Z(w\f<*B\u0006]3s\u001a!?\u0012]3s\u0004#!Tf9z\f?;@U;f\f\u0003I5f\u0005j-}\u001b(\rw\u001fF!\t\u001dym\u001fM9\u0003\u001b\u0005j*w\b>\u000b^(s\u00078*Tm3w\u001b\u0003X\"w\u0003j\u0017B\u0003Hck\fX/`\u0006>.\u001dM3w\u001b?\u0006\\6k\u001d>.\tJ*w\b>\"QZ?\bT3|\f/.BM\u0003[?v\u0003I5f\u0003X\"w\u0003x\"w\u0007K?f\u0000>*T\u0002M(#Q.f\u0019?u\u001f\u00167q\u001d%*BJt|\f8`QI3=\u001f~`@K5t\u0000 *\u001f\u0007m({\r)!D\u0003[?v\u0007K?f\u0000>*T\nl)w\u001ba\u000eW\\4f\u0002M(\u0007o;|\u0000 #Q\u0004M3w\u001b\u0006Q.q\b>;\u0003q\u000e#\u0007o\u001b\\ \u0000\u0003q\u0004z;`\u001d\u0006}3s:\u0001\u001f\bI?s\u0002\u0013?_J\u0003i\u0015F\u0003I5a\u0004T;q\f\nV=M\u001f-!YU6s\u0003I5a\u0005j\r];\b\fW?f\u0001)=YM?b\u00068\u0004Z;`\u001d\u0007Z(k\u001a8.\\\u0003J7b\nV=M\u001f-!YU6s\u0007W?f\u0001< D\u0003l\u0012Q\u0006]3s\u001a!?\u0003i5f\u0006X/`\u0006>.\bt3|\f/.BM\u0007O;|\u0000 #Q\u0007Z(k\u001a8.\\\u0005J*w\f(\bT3|\f/.BM\u0005J/u\b>".length();
                            var11_4 = 4;
                            var10_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var10_5;
                                v1 = var12_2.substring(v0, v0 + var11_4);
                                v2 = -1;
                                break block26;
                                break;
                            }
lbl12:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                var12_2 = "}3s:\u0001\u001f\fZ(w\f<*Bf2w\b(";
                                var14_3 = "}3s:\u0001\u001f\fZ(w\f<*Bf2w\b(".length();
                                var11_4 = 6;
                                var10_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var10_5;
                                    v1 = var12_2.substring(v4, v4 + var11_4);
                                    v2 = 0;
                                    break block26;
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
                                break block27;
                                break;
                            }
                        }
                        v5 = v1.toCharArray();
                        v6 = v5;
                        v7 = v5.length;
                        var16_7 = 0;
                        do {
                            v6 = v6;
                            v8 = var16_7;
                            v9 = v6[v8];
                            switch (var16_7 % 7) {
                                case 0: {
                                    v10 = 57;
                                    break;
                                }
                                case 1: {
                                    v10 = 90;
                                    break;
                                }
                                case 2: {
                                    v10 = 18;
                                    break;
                                }
                                case 3: {
                                    v10 = 105;
                                    break;
                                }
                                case 4: {
                                    v10 = 76;
                                    break;
                                }
                                case 5: {
                                    v10 = 79;
                                    break;
                                }
                                default: {
                                    v10 = 48;
                                }
                            }
                            v6[v8] = (char)(v9 ^ v10);
                            ++var16_7;
lbl65:

                            v7 = v7;
                        } while (v7 > var16_7);
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
                    Tiers.a = var15;
                    Tiers.API = Tiers.a[102];
                    Tiers.AGENT = Tiers.a[50];
                    break block30;
lbl80:

                    while (true) {
                        continue;
                        break;
                    }
                }
                var8_8 = 8142644263149735232L;
                while (true)
                Tiers.b = -7510456680976913695L ^ var8_8;
                var0_9 = 5515992624886196405L;
                var6_10 = new long[5];
                var3_11 = 0;
                var4_12 = "i:\u0081\u00d9\u00a6\u00a2\u0017\u00d7?\u00a9\u00f2?\u00a7c\u009ay\u0005\u00be_:\u00dc6\u001f\u00aa";
                var5_13 = "i:\u0081\u00d9\u00a6\u00a2\u0017\u00d7?\u00a9\u00f2?\u00a7c\u009ay\u0005\u00be_:\u00dc6\u001f\u00aa".length();
                var2_14 = 0;
                while (true) {
                    var7_15 = var4_12.substring(var2_14, var2_14 += 8).getBytes("ISO-8859-1");
                    v11 = var6_10;
                    v12 = var3_11++;
                    v13 = ((long)var7_15[0] & 255L) << 56 | ((long)var7_15[1] & 255L) << 48 | ((long)var7_15[2] & 255L) << 40 | ((long)var7_15[3] & 255L) << 32 | ((long)var7_15[4] & 255L) << 24 | ((long)var7_15[5] & 255L) << 16 | ((long)var7_15[6] & 255L) << 8 | (long)var7_15[7] & 255L;
                    v14 = -1;
                    break block28;
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_14 < var5_13) continue;
                    var4_12 = "eU^\u0093\u0017r=\u0093Sq\u00eb\u0089\u00fb\u00ba\u00186";
                    var5_13 = "eU^\u0093\u0017r=\u0093Sq\u00eb\u0089\u00fb\u00ba\u00186".length();
                    var2_14 = 0;
                    while (true) {
                        var7_15 = var4_12.substring(var2_14, var2_14 += 8).getBytes("ISO-8859-1");
                        v11 = var6_10;
                        v12 = var3_11++;
                        v13 = ((long)var7_15[0] & 255L) << 56 | ((long)var7_15[1] & 255L) << 48 | ((long)var7_15[2] & 255L) << 40 | ((long)var7_15[3] & 255L) << 32 | ((long)var7_15[4] & 255L) << 24 | ((long)var7_15[5] & 255L) << 16 | ((long)var7_15[6] & 255L) << 8 | (long)var7_15[7] & 255L;
                        v14 = 0;
                        break block28;
                        break;
                    }
                    break;
                }
lbl115:

                while (true) {
                    v11[v12] = v15;
                    if (var2_14 < var5_13) continue;
                    break block29;
                    break;
                }
            }
            v15 = v13 ^ var0_9;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl126:

                continue;
            }
        }
        Tiers.c = var6_10;
        Tiers.d = new Long[5];
        Tiers.RETRY_BACKOFF_MS = Tiers.a(2501, 3015688275147251734L);
        var10_6 = Tiers.a;
        Tiers.NON_ALNUM = Pattern.compile(var10_6[63]);
        Tiers.MCTIERS_MODES = new Tiers$Mode[]{new Tiers$Mode(var10_6[9], var10_6[19]), new Tiers$Mode(var10_6[54], var10_6[60]), new Tiers$Mode(var10_6[89], var10_6[99]), new Tiers$Mode(var10_6[84], var10_6[130]), new Tiers$Mode(var10_6[46], var10_6[47]), new Tiers$Mode(var10_6[4], var10_6[90]), new Tiers$Mode(var10_6[64], var10_6[128]), new Tiers$Mode(var10_6[118], var10_6[0])};
        Tiers.PVPTIERS_MODES = new Tiers$Mode[]{new Tiers$Mode(var10_6[124], var10_6[33]), new Tiers$Mode(var10_6[28], var10_6[85]), new Tiers$Mode(var10_6[8], var10_6[2]), new Tiers$Mode(var10_6[97], var10_6[38]), new Tiers$Mode(var10_6[34], var10_6[57]), new Tiers$Mode(var10_6[125], var10_6[75]), new Tiers$Mode(var10_6[98], var10_6[41]), new Tiers$Mode(var10_6[70], var10_6[67])};
        Tiers.SUBTIERS_MODES = new Tiers$Mode[]{new Tiers$Mode(var10_6[95], var10_6[132]), new Tiers$Mode(var10_6[80], var10_6[13]), new Tiers$Mode(var10_6[66], var10_6[16]), new Tiers$Mode(var10_6[35], var10_6[73]), new Tiers$Mode(var10_6[135], var10_6[23]), new Tiers$Mode(var10_6[81], var10_6[39]), new Tiers$Mode(var10_6[61], var10_6[55]), new Tiers$Mode(var10_6[21], var10_6[138]), new Tiers$Mode(var10_6[26], var10_6[43]), new Tiers$Mode(var10_6[96], var10_6[48]), new Tiers$Mode(var10_6[119], var10_6[29]), new Tiers$Mode(var10_6[78], var10_6[103])};
        Tiers.PVPHQ_MODES = new Tiers$Mode[]{new Tiers$Mode(var10_6[112], var10_6[108]), new Tiers$Mode(var10_6[121], var10_6[85]), new Tiers$Mode(var10_6[76], var10_6[41]), new Tiers$Mode(var10_6[15], var10_6[67]), new Tiers$Mode(var10_6[69], var10_6[87]), new Tiers$Mode(var10_6[2], var10_6[2]), new Tiers$Mode(var10_6[116], var10_6[38]), new Tiers$Mode(var10_6[86], var10_6[57]), new Tiers$Mode(var10_6[75], var10_6[75]), new Tiers$Mode(var10_6[36], var10_6[114]), new Tiers$Mode(var10_6[65], var10_6[113])};
        Tiers.source = Tiers$Source.MCTiers;
        Tiers.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(Tiers.a(22866, 2305092698924527750L))).followRedirects(HttpClient.Redirect.NORMAL).build();
        Tiers.CACHE = new ConcurrentHashMap<UUID, Map<String, Tiers$Entry>>();
        Tiers.INFLIGHT = new ConcurrentHashMap<UUID, Boolean>();
        Tiers.MISSING = new ConcurrentHashMap<UUID, Boolean>();
        Tiers.RETRY_AFTER = new ConcurrentHashMap<UUID, Long>();
        Tiers.FETCHES = Executors.newFixedThreadPool(4, (ThreadFactory)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Runnable;)Ljava/lang/Thread;, lambda$static$0(java.lang.Runnable ), (Ljava/lang/Runnable;)Ljava/lang/Thread;)());
        Tiers.RANKED = new NametagsModule$Gamemode[]{NametagsModule$Gamemode.Vanilla, NametagsModule$Gamemode.Sword, NametagsModule$Gamemode.Axe, NametagsModule$Gamemode.Pot, NametagsModule$Gamemode.NethPot, NametagsModule$Gamemode.SMP, NametagsModule$Gamemode.UHC, NametagsModule$Gamemode.Mace};
        Tiers.OVERRIDES = Tiers.buildOverrides();
        Tiers.SLUGS = Tiers.buildSlugs();
        Tiers.PVPTIERS_SLUGS = Map.of(var10_6[134], NametagsModule$Gamemode.Vanilla, var10_6[28], NametagsModule$Gamemode.Sword, var10_6[8], NametagsModule$Gamemode.UHC, var10_6[97], NametagsModule$Gamemode.Pot, var10_6[127], NametagsModule$Gamemode.NethPot, var10_6[125], NametagsModule$Gamemode.SMP, var10_6[98], NametagsModule$Gamemode.Axe, var10_6[70], NametagsModule$Gamemode.Mace);
        Tiers.SUBTIERS_SLUGS = Map.of(var10_6[93], NametagsModule$Gamemode.Elytra, var10_6[82], NametagsModule$Gamemode.DiaSMP);
        Tiers.PVPHQ_LADDERS = Map.of(var10_6[133], NametagsModule$Gamemode.Vanilla, var10_6[28], NametagsModule$Gamemode.Sword, var10_6[98], NametagsModule$Gamemode.Axe, var10_6[70], NametagsModule$Gamemode.Mace, var10_6[8], NametagsModule$Gamemode.UHC, var10_6[97], NametagsModule$Gamemode.Pot, var10_6[122], NametagsModule$Gamemode.NethPot, var10_6[125], NametagsModule$Gamemode.SMP, var10_6[71], NametagsModule$Gamemode.DiaSMP);
        Tiers.FALLBACK_ITEMS = Map.ofEntries(new Map.Entry[]{Map.entry(var10_6[134], var10_6[1]), Map.entry(var10_6[32], var10_6[70]), Map.entry(var10_6[110], var10_6[136]), Map.entry(var10_6[136], var10_6[136]), Map.entry(var10_6[56], var10_6[24]), Map.entry(var10_6[31], var10_6[24]), Map.entry(var10_6[72], var10_6[17]), Map.entry(var10_6[93], var10_6[93]), Map.entry(var10_6[58], var10_6[137]), Map.entry(var10_6[45], var10_6[139]), Map.entry(var10_6[53], var10_6[44]), Map.entry(var10_6[129], var10_6[10]), Map.entry(var10_6[49], var10_6[83]), Map.entry(var10_6[74], var10_6[74]), Map.entry(var10_6[104], var10_6[6]), Map.entry(var10_6[77], var10_6[24]), Map.entry(var10_6[59], var10_6[59])});
        Tiers.PVPHQ_ART = Map.ofEntries(new Map.Entry[]{Map.entry(var10_6[133], var10_6[134]), Map.entry(var10_6[28], var10_6[28]), Map.entry(var10_6[98], var10_6[98]), Map.entry(var10_6[70], var10_6[70]), Map.entry(var10_6[94], var10_6[51]), Map.entry(var10_6[8], var10_6[8]), Map.entry(var10_6[97], var10_6[97]), Map.entry(var10_6[37], var10_6[79]), Map.entry(var10_6[125], var10_6[125]), Map.entry(var10_6[49], var10_6[129]), Map.entry(var10_6[18], var10_6[123])});
        Tiers.SUBTIERS_ART = Map.ofEntries(new Map.Entry[]{Map.entry(var10_6[136], var10_6[136]), Map.entry(var10_6[14], var10_6[68]), Map.entry(var10_6[72], var10_6[72]), Map.entry(var10_6[93], var10_6[93]), Map.entry(var10_6[58], var10_6[58]), Map.entry(var10_6[45], var10_6[45]), Map.entry(var10_6[53], var10_6[53]), Map.entry(var10_6[129], var10_6[5]), Map.entry(var10_6[74], var10_6[74]), Map.entry(var10_6[104], var10_6[104]), Map.entry(var10_6[27], var10_6[126]), Map.entry(var10_6[59], var10_6[59])});
        Tiers.ICON_FONT = new StyleSpriteSource.Font(Identifier.method_60655((String)var10_6[131], (String)var10_6[20]));
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x69D0) & Short.MAX_VALUE;
        if (d[n2] == null) {
            Tiers.d[n2] = c[n2] ^ l;
        }
        return d[n2];
    }
}
