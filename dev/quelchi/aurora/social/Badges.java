package dev.quelchi.aurora.social;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.social.Badges$Badge;
import dev.quelchi.aurora.social.Badges$Group;
import dev.quelchi.aurora.social.Badges$Ladder;
import dev.quelchi.aurora.social.Social;
import java.io.Serializable;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public final class Badges {
    public static final String[] GRANTABLE;
    public static final Badges$Group[] CATALOGUE;
    private static final Map<String, String> LABELS;
    private static final HttpClient HTTP;
    private static final Map<UUID, List<Badges$Badge>> CACHE;
    private static final Set<UUID> INFLIGHT;
    private static final Map<UUID, Long> ASKED;
    private static final long TTL_MS;
    public static volatile String status;
    private static final Set<String> SEEN;
    private static boolean seeded;
    private static volatile Map<String, Badges$Ladder> progress;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private Badges() {
    }

    public static String labelOf(String string) {
        Object object;
        block18: {
            String string2;
            block16: {
                block17: {
                    String string3;
                    int n;
                    block14: {
                        block15: {
                            String string4;
                            block12: {
                                block13: {
                                    n = Social.a;
                                    string4 = string;
                                    if (n != 0) break block12;
                                    if (string4 == null) break block13;
                                    string3 = string;
                                    if (n != 0) break block14;
                                    if (!string3.isEmpty()) break block15;
                                }
                                string4 = "";
                            }
                            return string4;
                        }
                        string3 = LABELS.get(string);
                    }
                    String string5 = string3;
                    string2 = string5;
                    if (n != 0) break block16;
                    if (string2 == null) break block17;
                    object = string5;
                    break block18;
                }
                string2 = string;
            }
            object = Character.toUpperCase(string2.charAt(0)) + string.substring(1);
        }
        return object;
    }

    public static boolean ready() {
        boolean bl;
        block5: {
            block4: {
                if (!CosmeticsSync.enabled() || CosmeticsSync.token() == null) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public static List<Badges$Badge> of(UUID uUID) {
        Object object;
        block4: {
            block5: {
                int n = Social.a;
                object = uUID;
                if (n != 0) break block4;
                if (object != null) break block5;
                return List.of();
            }
            Badges.request(uUID, false);
            object = CACHE.get(uUID);
        }
        return (List)object;
    }

    public static List<Badges$Badge> mine() {
        UUID uUID;
        block4: {
            block5: {
                int n = Social.a;
                uUID = Social.selfUuid();
                if (n != 0) break block4;
                if (uUID != null) break block5;
                return null;
            }
            Badges.request(uUID, false);
        }
        return CACHE.get(uUID);
    }

    private static void request(UUID uUID, boolean n) {
        block16: {
            Object object;
            int n2;
            long l;
            block14: {
                int n3;
                block15: {
                    n3 = Social.a;
                    if (!Badges.ready()) {
                        return;
                    }
                    l = System.currentTimeMillis();
                    Long l2 = ASKED.get(uUID);
                    n2 = n;
                    if (n3 != 0) break block14;
                    if (n2 != 0) break block15;
                    if (l2 == null) break block15;
                    long l3 = l - l2 - Badges.a(3363, 1764178008043926037L);
                    n2 = l3 == 0L ? (byte)0 : (l3 < 0L ? (byte)-1 : 1);
                    if (n3 != 0) break block14;
                    if (n2 >= 0) break block15;
                    return;
                }
                object = INFLIGHT;
                if (n3 != 0) break block16;
                n2 = object.add(uUID) ? 1 : 0;
            }
            if (n2 == 0) {
                return;
            }
            object = ASKED.put(uUID, l);
        }
        Badges.run(() -> Badges.lambda$request$0(uUID));
    }

    public static List<String> seenList() {
        return List.copyOf(SEEN);
    }

    public static void accountChanged() {
        seeded = false;
        progress = Map.of();
    }

    public static void restoreSeen(Collection<String> collection) {
        block5: {
            boolean bl;
            block4: {
                int n = Social.a;
                bl = SEEN.addAll(collection);
                if (n != 0) break block4;
                if (collection.isEmpty()) break block5;
                bl = true;
            }
            seeded = bl;
        }
    }

    private static void announce(List<Badges$Badge> list) {
        block26: {
            boolean bl;
            Badges$Badge badges$Badge;
            ArrayList<Badges$Badge> arrayList;
            block27: {
                int n;
                block25: {
                    boolean bl2;
                    block24: {
                        boolean bl3;
                        block23: {
                            block20: {
                                arrayList = new ArrayList<Badges$Badge>();
                                Iterator<Badges$Badge> iterator = list.iterator();
                                n = Social.a;
                                while (iterator.hasNext()) {
                                    block22: {
                                        boolean bl4;
                                        block21: {
                                            badges$Badge = iterator.next();
                                            if (n != 0) break block20;
                                            bl4 = SEEN.add(badges$Badge.id());
                                            if (n != 0) break block21;
                                            if (!bl4) break block22;
                                            bl4 = seeded;
                                        }
                                        if (n != 0 || !bl4) break block22;
                                        bl4 = arrayList.add(badges$Badge);
                                    }
                                    if (n == 0) continue;
                                }
                                bl3 = seeded;
                                if (n != 0) break block23;
                                if (bl3) break block20;
                                bl3 = true;
                                break block23;
                            }
                            bl3 = false;
                        }
                        bl = bl3;
                        bl2 = true;
                        if (n != 0) break block24;
                        seeded = bl2;
                        if (bl) break block25;
                        bl2 = arrayList.isEmpty();
                    }
                    if (bl2) {
                        return;
                    }
                }
                badges$Badge = MinecraftClient.method_1551();
                if (n != 0) break block26;
                if (badges$Badge != null) break block27;
                return;
            }
            badges$Badge.execute(() -> Badges.lambda$announce$1(bl, arrayList, (MinecraftClient)badges$Badge));
        }
    }

    private static Map<String, Badges$Ladder> ladders(JsonObject jsonObject) {
        HashMap<String, Long> hashMap;
        block5: {
            HashMap<String, Long> hashMap2 = new HashMap<String, Long>();
            int n = Social.a;
            for (String string : jsonObject.keySet()) {
                block6: {
                    Serializable serializable;
                    JsonObject jsonObject2 = jsonObject.getAsJsonObject(string);
                    hashMap = new HashMap<String, Long>();
                    if (n != 0) break block5;
                    HashMap<String, Long> hashMap3 = hashMap;
                    String[] stringArray = a;
                    for (Map.Entry entry : jsonObject2.getAsJsonObject(stringArray[82]).entrySet()) {
                        serializable = hashMap3;
                        if (n == 0) {
                            serializable.put((String)((String)entry.getKey()), ((JsonElement)entry.getValue()).getAsLong());
                            if (n == 0) continue;
                            break;
                        }
                        break block6;
                    }
                    serializable = hashMap2.put(string, (Long)((Object)new Badges$Ladder(jsonObject2.get(a[74]).getAsLong(), hashMap3)));
                }
                if (n == 0) continue;
            }
            hashMap = hashMap2;
        }
        return hashMap;
    }

    private static List<Badges$Badge> parse(JsonObject jsonObject) {
        ArrayList<Badges$Badge> arrayList;
        block13: {
            JsonObject jsonObject2;
            int n;
            block12: {
                arrayList = new ArrayList<Badges$Badge>();
                n = Social.a;
                jsonObject2 = jsonObject;
                if (n != 0) break block12;
                if (!jsonObject2.has(a[20])) break block13;
                jsonObject2 = jsonObject;
            }
            String[] stringArray = a;
            for (JsonElement jsonElement : jsonObject2.getAsJsonArray(stringArray[54])) {
                Badges$Badge badges$Badge;
                String string;
                String string2;
                JsonObject jsonObject3;
                block14: {
                    jsonObject3 = jsonElement.getAsJsonObject();
                    stringArray = a;
                    string2 = Badges.str(jsonObject3, stringArray[10]);
                    string = string2;
                    if (n != 0) break block14;
                    if (string.isEmpty()) {
                        continue;
                    }
                    stringArray = a;
                    string = Badges.str(jsonObject3, stringArray[27]);
                }
                String string3 = string;
                ArrayList<Badges$Badge> arrayList2 = arrayList;
                Badges$Badge badges$Badge2 = badges$Badge;
                Badges$Badge badges$Badge3 = badges$Badge;
                String string4 = string2;
                String string5 = string3.isEmpty() ? string2 : string3;
                stringArray = a;
                badges$Badge2(string4, string5, Badges.str(jsonObject3, stringArray[55]), jsonObject3.has(stringArray[3]) && jsonObject3.get(stringArray[3]).getAsBoolean());
                arrayList2.add(badges$Badge3);
                if (n == 0) continue;
            }
        }
        return List.copyOf(arrayList);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void setHidden(String string, boolean bl) {
        UUID uUID;
        block22: {
            Object object;
            int n;
            block20: {
                block21: {
                    block19: {
                        uUID = Social.selfUuid();
                        n = Social.a;
                        if (!Badges.ready() || uUID == null) return;
                        object = string;
                        if (n != 0) break block19;
                        if (object == null) return;
                        object = string;
                    }
                    if (n != 0) break block20;
                    if (!((String)object).isEmpty()) break block21;
                    return;
                }
                object = CACHE.get(uUID);
            }
            List list = (List)object;
            if (n != 0) return;
            if (list == null) break block22;
            ArrayList<Badges$Badge> arrayList = new ArrayList<Badges$Badge>(list.size());
            for (Badges$Badge badges$Badge : list) {
                Badges$Badge badges$Badge2;
                ArrayList<Badges$Badge> arrayList2;
                block23: {
                    block24: {
                        if (n != 0) return;
                        arrayList2 = arrayList;
                        badges$Badge2 = badges$Badge;
                        if (n != 0) break block23;
                        if (!badges$Badge2.id().equals(string)) break block24;
                        badges$Badge2 = new Badges$Badge(badges$Badge.id(), badges$Badge.label(), badges$Badge.kind(), bl);
                        break block23;
                    }
                    badges$Badge2 = badges$Badge;
                }
                arrayList2.add(badges$Badge2);
                if (n == 0) continue;
            }
            CACHE.put(uUID, List.copyOf(arrayList));
        }
        Badges.run(() -> Badges.lambda$setHidden$2(string, bl, uUID));
    }

    private static String key(String string) {
        String string2;
        block6: {
            String string3;
            block4: {
                block5: {
                    int n = Social.a;
                    string3 = string;
                    if (n != 0) break block4;
                    if (string3 != null) break block5;
                    string2 = "";
                    break block6;
                }
                string3 = string.replace("-", "");
            }
            string2 = string3.toLowerCase(Locale.ROOT);
        }
        return string2;
    }

    private static String str(JsonObject jsonObject, String string) {
        String string2;
        block11: {
            block9: {
                String string3;
                JsonObject jsonObject2;
                block10: {
                    JsonObject jsonObject3;
                    block8: {
                        int n = Social.a;
                        jsonObject3 = jsonObject;
                        if (n != 0) break block8;
                        if (!jsonObject3.has(string)) break block9;
                        jsonObject2 = jsonObject;
                        string3 = string;
                        if (n != 0) break block10;
                        jsonObject3 = jsonObject2.get(string3);
                    }
                    if (jsonObject3.isJsonNull()) break block9;
                    jsonObject2 = jsonObject;
                    string3 = string;
                }
                string2 = jsonObject2.get(string3).getAsString();
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
                int n = Social.a;
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

    private static HttpResponse<String> get(String string) throws Exception {
        HttpRequest httpRequest = CosmeticsSync.bearer(HttpRequest.newBuilder(URI.create(CosmeticsSync.baseUrl() + string)).timeout(Duration.ofSeconds(Badges.a(17257, 8055093521489853530L))).GET()).build();
        return HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private static HttpResponse<String> post(String string, String string2) throws Exception {
        String[] stringArray = a;
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(CosmeticsSync.baseUrl() + string)).timeout(Duration.ofSeconds(Badges.a(29280, 7363428038374608209L))).header(stringArray[77], stringArray[45]).POST(HttpRequest.BodyPublishers.ofString(string2, StandardCharsets.UTF_8)).build();
        return HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private static void run(Runnable runnable) {
        Thread thread = new Thread(runnable, a[12]);
        thread.setDaemon(true);
        thread.start();
    }

    
    private static void lambda$setHidden$2(String string, boolean bl, UUID uUID) {
        int n = Social.a;
        try {
            block11: {
                block10: {
                    JsonObject jsonObject = new JsonObject();
                    String[] stringArray = a;
                    jsonObject.addProperty(stringArray[43], CosmeticsSync.token());
                    jsonObject.addProperty(stringArray[8], string);
                    jsonObject.addProperty(stringArray[34], Boolean.valueOf(bl));
                    HttpResponse<String> httpResponse = Badges.post(stringArray[40], jsonObject.toString());
                    if (n != 0) break block10;
                    if (httpResponse.statusCode() / 100 == 2) break block11;
                    status = a[35] + httpResponse.statusCode() + ")";
                }
                if (n == 0) return;
            }
            status = "";
            return;
        }
        catch (Exception exception) {
            String[] stringArray = a;
            status = stringArray[73];
            Aurora.LOG.debug(stringArray[47], (Throwable)exception);
            return;
        }
        finally {
            Badges.request(uUID, true);
        }
    }

    private static void lambda$announce$1(boolean bl, List list, MinecraftClient minecraftClient) {
        block11: {
            MinecraftClient minecraftClient2;
            block12: {
                ConfigManager.save();
                int n = Social.a;
                if (bl) {
                    return;
                }
                for (Badges$Badge badges$Badge : list) {
                    if (n == 0) {
                        SocialToast.system(a[6], badges$Badge.label() + a[59]);
                        if (n == 0) continue;
                        break;
                    }
                    break block11;
                }
                minecraftClient2 = minecraftClient;
                if (n != 0) break block12;
                if (minecraftClient2.method_1483() == null) break block11;
                minecraftClient2 = minecraftClient;
            }
            minecraftClient2.method_1483().method_4873((SoundInstance)PositionedSoundInstance.method_4757((SoundEvent)SoundEvents.field_14709, (float)1.4f, (float)0.5f));
        }
    }

    
    private static void lambda$request$0(UUID uUID) {
        int n = Social.a;
        try {
            boolean bl;
            List<Badges$Badge> list;
            block21: {
                boolean bl2;
                block22: {
                    bl2 = uUID.equals(Social.selfUuid());
                    String[] stringArray = a;
                    Object object2 = stringArray[21];
                    if (!bl2) {
                        object2 = (String)object2 + stringArray[2] + Badges.enc(uUID.toString());
                    }
                    HttpResponse<String> httpResponse = Badges.get((String)object2);
                    Object object = httpResponse;
                    if (n == 0) {
                        if (object.statusCode() / 100 != 2) {
                            return;
                        }
                        object = httpResponse.body();
                    }
                    JsonObject jsonObject = JsonParser.parseString((String)((String)object)).getAsJsonObject();
                    list = Badges.parse(jsonObject);
                    CACHE.put(uUID, list);
                    bl = bl2;
                    if (n != 0) break block21;
                    if (!bl) break block22;
                    bl = jsonObject.has(a[80]);
                    if (n != 0) break block21;
                    if (bl) {
                        progress = Badges.ladders(jsonObject.getAsJsonObject(a[18]));
                    }
                }
                bl = bl2;
            }
            if (n != 0) return;
            if (bl) {
                Badges.announce(list);
            }
            bl = INFLIGHT.remove(uUID);
            return;
        }
        catch (Exception exception) {
            Aurora.LOG.debug(a[67], (Throwable)exception);
            return;
        }
        finally {
            INFLIGHT.remove(uUID);
        }
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[84];
                        var11_1 = 0;
                        var10_2 = "A\u0001\u000bp6\u0007M\n\bm0\u0006<\b\u001f\u0011\u0007k?\u0010~\u001d\u0006H\f\u0002}=\u001b\u0005\u0012UV)0\u0003\u0015U\u000e\u000eb\u0004\u0002~=U\u007fN\t\tz3\u0010n\bH\n\u0013k+@:\u0010\u0005B\u0004\u0002~=\tH\n\u0013k+D:\u0010U\u0002I\u0001\u0007M\n\bm0\u0006<\ra\u0010\u0014v*\u0014'b\u0004\u0002~=\u0006\u0006s\u0011\u0007m-\u0006\tF\u0017\u000f|6\u0011y\u0012P\u0005S\u0011\u0007\u007f>\u0007f\u0017\u000f|6\u0011y\nF\u0017\u000f|6\u0011y\u0011UV\bP\u0017\t~*\u0010yS\tF\u0017\u000f|6\u0011y\u0012P\u0006B\u0004\u0002~=\u0006\u0012\u000f\u0004\u0016pw\u0006eC\f\u0007uw\u0017kD\u0002\u0003j\u0004B\u0000\u0012x\tH\n\u0013k+D:\u0010U\u0005O\u0012\b|*\u0004\u0012PVq\u0005M\u0000\u0002p9\u0005L\u0004\u0004|4\u0007S\u0000\u0012m4\u0010n\u000ba\u0006\u0005v-\u001b~\u0000\u0004\u0001|\u0007V\u0000\u0012|*\u0014d\u0007R\u0000\u0001l4\u0014x\tB\u0010\u0001m=\u0006~E\u0017\u0004\u0011UVq\u0006H\f\u0002}=\u001b\u0015c\n\u0013u<UdO\u0011Fj9\u0003o\u0000\u0011\u000ex,U\"\bH\n\u0013k+G?\u0010\u0006\u0011E\u001f|9\u0007\u0004B\u0000\u0012x\u0005A\t\u0016q9\u0017\u000f\u0004\u0016pw\u0006eC\f\u0007uw\u0017kD\u0002\u0003jw\u001dcD\u0000\t\u0015E\u0000k1\u0010dD\u0016\u0006M\n\bm0D\u0005T\n\r|6\tB\u0010\u0001m=\u0006~E\u0017\u0010A\u0015\u0016u1\u0016kT\f\tww\u001fyO\u000b\bH\n\u0013k+@:\u0010\u0011b\u0004\u0002~=UbI\u0001\u00039>\u0014cL\u0000\u0002\u0005A\t\u0016q9\u0006M\n\bm0D\bH\n\u0013k+G?\u0010\u0005Y\u0000\u0007ki\nb\u0010\u00019\f\u0010yT\u0000\u0014\bF\u0017\u000f|6\u0011y\u0015\u0006B\u0004\u0002~=\u0006\u0004K\f\b}\u0007\u0011E\u000bv6\u0001b\u0005Y\u0000\u0007ki\bF\u0017\u000f|6\u0011y\u0015\u0018\u0000\f\u001596\u001a}\u0000\n\b9!\u001a\u007fRE\u0016k7\u0013cL\u0000H\u0005M\u0000\u0002p9\bp\t\u0007`,\u001cgE\u0007S\u0000\u0012m4\u0010n\u0007V\u0000\u0012|*\u0014d\n\u0012PF\u007f*\u001coN\u0001\u0015\nF\u0017\u000f|6\u0011y\u0011UV\u0003\u0011U\u000e\u0012b\u0004\u0002~=UlE\u0011\u0005qx\u0013kI\t\u0003}\tH\n\u0013k+G:\u0010U\u0005S\u0011\u0007\u007f>\u0007M\u0004\bx?\u0010x\u0007R\u0000\u0001l4\u0014x\u0007P\u0004\u0014m6\u0010x\u001bc\n\u0013u<UdO\u0011Fk=\u0014iHE\u0012q=UyE\u0017\u0010p;\u0010\u0005V\u0004\nl=\u0007P\u0004\u0014m6\u0010x\u0005\u0011UV)0\fc\n\bm=\u001b~\r1\u001fi=\u0004\u0015UVq\u000b\u0011UV9>\u0007cE\u000b\u0002j\bP\u0017\t~*\u0010yS\tH\n\u0013k+G:\u0010U";
                        var12_3 = "A\u0001\u000bp6\u0007M\n\bm0\u0006<\b\u001f\u0011\u0007k?\u0010~\u001d\u0006H\f\u0002}=\u001b\u0005\u0012UV)0\u0003\u0015U\u000e\u000eb\u0004\u0002~=U\u007fN\t\tz3\u0010n\bH\n\u0013k+@:\u0010\u0005B\u0004\u0002~=\tH\n\u0013k+D:\u0010U\u0002I\u0001\u0007M\n\bm0\u0006<\ra\u0010\u0014v*\u0014'b\u0004\u0002~=\u0006\u0006s\u0011\u0007m-\u0006\tF\u0017\u000f|6\u0011y\u0012P\u0005S\u0011\u0007\u007f>\u0007f\u0017\u000f|6\u0011y\nF\u0017\u000f|6\u0011y\u0011UV\bP\u0017\t~*\u0010yS\tF\u0017\u000f|6\u0011y\u0012P\u0006B\u0004\u0002~=\u0006\u0012\u000f\u0004\u0016pw\u0006eC\f\u0007uw\u0017kD\u0002\u0003j\u0004B\u0000\u0012x\tH\n\u0013k+D:\u0010U\u0005O\u0012\b|*\u0004\u0012PVq\u0005M\u0000\u0002p9\u0005L\u0004\u0004|4\u0007S\u0000\u0012m4\u0010n\u000ba\u0006\u0005v-\u001b~\u0000\u0004\u0001|\u0007V\u0000\u0012|*\u0014d\u0007R\u0000\u0001l4\u0014x\tB\u0010\u0001m=\u0006~E\u0017\u0004\u0011UVq\u0006H\f\u0002}=\u001b\u0015c\n\u0013u<UdO\u0011Fj9\u0003o\u0000\u0011\u000ex,U\"\bH\n\u0013k+G?\u0010\u0006\u0011E\u001f|9\u0007\u0004B\u0000\u0012x\u0005A\t\u0016q9\u0017\u000f\u0004\u0016pw\u0006eC\f\u0007uw\u0017kD\u0002\u0003jw\u001dcD\u0000\t\u0015E\u0000k1\u0010dD\u0016\u0006M\n\bm0D\u0005T\n\r|6\tB\u0010\u0001m=\u0006~E\u0017\u0010A\u0015\u0016u1\u0016kT\f\tww\u001fyO\u000b\bH\n\u0013k+@:\u0010\u0011b\u0004\u0002~=UbI\u0001\u00039>\u0014cL\u0000\u0002\u0005A\t\u0016q9\u0006M\n\bm0D\bH\n\u0013k+G?\u0010\u0005Y\u0000\u0007ki\nb\u0010\u00019\f\u0010yT\u0000\u0014\bF\u0017\u000f|6\u0011y\u0015\u0006B\u0004\u0002~=\u0006\u0004K\f\b}\u0007\u0011E\u000bv6\u0001b\u0005Y\u0000\u0007ki\bF\u0017\u000f|6\u0011y\u0015\u0018\u0000\f\u001596\u001a}\u0000\n\b9!\u001a\u007fRE\u0016k7\u0013cL\u0000H\u0005M\u0000\u0002p9\bp\t\u0007`,\u001cgE\u0007S\u0000\u0012m4\u0010n\u0007V\u0000\u0012|*\u0014d\n\u0012PF\u007f*\u001coN\u0001\u0015\nF\u0017\u000f|6\u0011y\u0011UV\u0003\u0011U\u000e\u0012b\u0004\u0002~=UlE\u0011\u0005qx\u0013kI\t\u0003}\tH\n\u0013k+G:\u0010U\u0005S\u0011\u0007\u007f>\u0007M\u0004\bx?\u0010x\u0007R\u0000\u0001l4\u0014x\u0007P\u0004\u0014m6\u0010x\u001bc\n\u0013u<UdO\u0011Fk=\u0014iHE\u0012q=UyE\u0017\u0010p;\u0010\u0005V\u0004\nl=\u0007P\u0004\u0014m6\u0010x\u0005\u0011UV)0\fc\n\bm=\u001b~\r1\u001fi=\u0004\u0015UVq\u000b\u0011UV9>\u0007cE\u000b\u0002j\bP\u0017\t~*\u0010yS\tH\n\u0013k+G:\u0010U".length();
                        var9_4 = 5;
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
                            var10_2 = "R\u0010\b~+\b\u0016E\u000bv6\u0001bS";
                            var12_3 = "R\u0010\b~+\b\u0016E\u000bv6\u0001bS".length();
                            var9_4 = 5;
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
                    var14_7 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_7;
                        v9 = v6[v8];
                        switch (var14_7 % 7) {
                            case 0: {
                                v10 = 32;
                                break;
                            }
                            case 1: {
                                v10 = 101;
                                break;
                            }
                            case 2: {
                                v10 = 102;
                                break;
                            }
                            case 3: {
                                v10 = 25;
                                break;
                            }
                            case 4: {
                                v10 = 88;
                                break;
                            }
                            case 5: {
                                v10 = 117;
                                break;
                            }
                            default: {
                                v10 = 10;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_7;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_7);
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
                Badges.a = var13;
                var0_8 = 1584513879185655246L;
                var6_9 = new long[5];
                var3_10 = 0;
                var4_11 = "K;\u00c8\f8c1Wz4\"\u001b\u0012zy\u009b5\u00b1)\u00fb\u0093\u0010\u0015\u00f2";
                var5_12 = "K;\u00c8\f8c1Wz4\"\u001b\u0012zy\u009b5\u00b1)\u00fb\u0093\u0010\u0015\u00f2".length();
                var2_13 = 0;
                while (true) {
                    var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                    v11 = var6_9;
                    v12 = var3_10++;
                    v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    var4_11 = "s\u00cdt3\u00f2\u00f1\u00c8\u0090\r\u0086\u00ccw|\u008a\u00dd\u00bb";
                    var5_12 = "s\u00cdt3\u00f2\u00f1\u00c8\u0090\r\u0086\u00ccw|\u008a\u00dd\u00bb".length();
                    var2_13 = 0;
                    while (true) {
                        var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
                        v11 = var6_9;
                        v12 = var3_10++;
                        v13 = ((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_13 < var5_12) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var0_8;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        Badges.b = var6_9;
        Badges.c = new Long[5];
        Badges.TTL_MS = Badges.a(17772, 2327370355118275164L);
        v16 = new String[5];
        var8_6 = Badges.a;
        v16[0] = var8_6[48];
        v16[1] = var8_6[22];
        v16[2] = var8_6[15];
        v16[3] = var8_6[26];
        v16[4] = var8_6[72];
        Badges.GRANTABLE = v16;
        Badges.CATALOGUE = new Badges$Group[]{new Badges$Group(var8_6[13], new String[]{var8_6[24], var8_6[0], var8_6[70], var8_6[69], var8_6[60], var8_6[75], var8_6[44], var8_6[39], var8_6[38]}), new Badges$Group(var8_6[61], new String[]{var8_6[62], var8_6[71], var8_6[63], var8_6[36], var8_6[46], var8_6[23], var8_6[68]}), new Badges$Group(var8_6[16], new String[]{var8_6[53], var8_6[19], var8_6[65]}), new Badges$Group(var8_6[29], new String[]{var8_6[49], var8_6[11], var8_6[57]})};
        Badges.LABELS = Map.ofEntries(new Map.Entry[]{Map.entry(var8_6[30], var8_6[33]), Map.entry(var8_6[31], var8_6[5]), Map.entry(var8_6[28], var8_6[66]), Map.entry(var8_6[50], var8_6[25]), Map.entry(var8_6[7], var8_6[78]), Map.entry(var8_6[9], var8_6[76]), Map.entry(var8_6[81], var8_6[4]), Map.entry(var8_6[58], var8_6[41]), Map.entry(var8_6[14], var8_6[64]), Map.entry(var8_6[17], var8_6[79]), Map.entry(var8_6[42], var8_6[56]), Map.entry(var8_6[1], var8_6[83]), Map.entry(var8_6[51], var8_6[37]), Map.entry(var8_6[32], var8_6[52])});
        Badges.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(Badges.a(2977, 6829316268033225875L))).followRedirects(HttpClient.Redirect.NORMAL).build();
        Badges.CACHE = new ConcurrentHashMap<UUID, List<Badges$Badge>>();
        Badges.INFLIGHT = ConcurrentHashMap.newKeySet();
        Badges.ASKED = new ConcurrentHashMap<UUID, Long>();
        Badges.status = "";
        Badges.SEEN = ConcurrentHashMap.newKeySet();
        Badges.progress = Map.of();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x3F32) & Short.MAX_VALUE;
        if (c[n2] == null) {
            Badges.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
