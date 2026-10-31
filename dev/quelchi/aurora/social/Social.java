package dev.quelchi.aurora.social;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.gui.im.SocialScreen;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.social.Social$Friend;
import dev.quelchi.aurora.social.Social$Msg;
import dev.quelchi.aurora.social.Social$Presence;
import dev.quelchi.aurora.social.Social$Roster;
import dev.quelchi.aurora.social.SocialEvents;
import dev.quelchi.aurora.social.WorldShare;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import net.minecraft.client.MinecraftClient;

public final class Social {
    private static volatile Social$Presence me;
    private static final HttpClient HTTP;
    private static volatile List<Social$Friend> friends;
    private static volatile int unread;
    private static volatile String error;
    private static final Map<UUID, List<Social$Msg>> CONVOS;
    private static volatile long cursor;
    private static volatile long lastPoll;
    private static volatile boolean polling;
    private static final long IDLE_MS;
    private static final long OPEN_MS;
    private static volatile int generation;
    private static final Map<UUID, List<String>> MUTUAL;
    private static final Set<UUID> MUTUAL_BUSY;
    private static final Set<UUID> MUTED;
    private static volatile long lastTyping;
    private static volatile Thread streamThread;
    public static int a;
    private static final String[] b;
    private static final long[] c;
    private static final Long[] d;

    private Social() {
    }

    public static Social$Presence me() {
        return me;
    }

    public static void setPresence(Social$Presence presence) {
        Social$Presence presence2;
        block4: {
            block5: {
                int n = a;
                presence2 = presence;
                if (n != 0) break block4;
                me = presence2;
                if (Social.ready()) break block5;
                return;
            }
            presence2 = presence;
        }
        Social.run(() -> Social.lambda$setPresence$0(presence2));
    }

    public static List<Social$Friend> friends() {
        return friends;
    }

    public static Social$Friend friend(UUID uUID) {
        int n = a;
        if (uUID == null) {
            return null;
        }
        for (Social$Friend social$Friend : friends) {
            block9: {
                Social$Friend social$Friend2;
                block8: {
                    social$Friend2 = social$Friend;
                    if (n != 0) break block8;
                    if (!social$Friend2.uuid().equals(uUID)) break block9;
                    social$Friend2 = social$Friend;
                }
                return social$Friend2;
            }
            if (n == 0) continue;
        }
        return null;
    }

    public static Social$Roster roster(List<Social$Friend> list) {
        ArrayList<Social$Friend> arrayList = new ArrayList<Social$Friend>();
        int n = a;
        ArrayList<Social$Friend> arrayList2 = new ArrayList<Social$Friend>();
        ArrayList<Social$Friend> arrayList3 = new ArrayList<Social$Friend>();
        ArrayList arrayList4 = new ArrayList();
        for (Social$Friend social$Friend : list) {
            block23: {
                Social$Friend social$Friend2;
                block28: {
                    block26: {
                        block27: {
                            block21: {
                                block22: {
                                    boolean bl;
                                    block25: {
                                        block24: {
                                            social$Friend2 = social$Friend;
                                            if (n != 0) break block21;
                                            if (!social$Friend2.incoming()) break block22;
                                            arrayList.add(social$Friend);
                                            if (n == 0) break block23;
                                            if (!Module.a) break block24;
                                            bl = false;
                                            break block25;
                                        }
                                        bl = true;
                                    }
                                    Module.a = bl;
                                }
                                social$Friend2 = social$Friend;
                            }
                            if (n != 0) break block26;
                            if (!social$Friend2.outgoing()) break block27;
                            arrayList2.add(social$Friend);
                            if (n == 0) break block23;
                        }
                        social$Friend2 = social$Friend;
                    }
                    if (n != 0) break block28;
                    if (!social$Friend2.accepted()) break block23;
                    social$Friend2 = social$Friend;
                }
                ArrayList<Social$Friend> arrayList5 = social$Friend2.online() ? arrayList3 : arrayList4;
                arrayList5.add(social$Friend);
            }
            if (n == 0) continue;
        }
        Comparator comparator = Social::lambda$roster$1;
        arrayList.sort(comparator);
        arrayList2.sort(comparator);
        arrayList3.sort(comparator);
        arrayList4.sort(comparator);
        return new Social$Roster(List.copyOf(arrayList), List.copyOf(arrayList2), List.copyOf(arrayList3), List.copyOf(arrayList4));
    }

    public static List<UUID> knownUuids() {
        ArrayList<UUID> arrayList;
        block4: {
            ArrayList<UUID> arrayList2 = new ArrayList<UUID>(friends.size());
            int n = a;
            for (Social$Friend social$Friend : friends) {
                arrayList = arrayList2;
                if (n == 0) {
                    arrayList.add(social$Friend.uuid());
                    if (n == 0) continue;
                    break;
                }
                break block4;
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public static int unread() {
        return unread;
    }

    public static String error() {
        return error;
    }

    public static UUID selfUuid() {
        UUID uUID;
        block10: {
            MinecraftClient minecraftClient;
            block8: {
                MinecraftClient minecraftClient2;
                block9: {
                    int n;
                    block7: {
                        UUID uUID2;
                        block6: {
                            UUID uUID3 = CosmeticsSync.ownUuid();
                            n = a;
                            uUID2 = uUID3;
                            if (n != 0) break block6;
                            if (uUID2 == null) break block7;
                            uUID2 = uUID3;
                        }
                        return uUID2;
                    }
                    minecraftClient2 = MinecraftClient.method_1551();
                    minecraftClient = minecraftClient2;
                    if (n != 0) break block8;
                    if (minecraftClient.method_1548() != null) break block9;
                    uUID = null;
                    break block10;
                }
                minecraftClient = minecraftClient2;
            }
            uUID = minecraftClient.method_1548().method_44717();
        }
        return uUID;
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

    public static String signInStatus() {
        String string;
        block14: {
            String string2;
            block16: {
                block15: {
                    int n = a;
                    if (!CosmeticsSync.enabled()) {
                        return b[17];
                    }
                    MinecraftClient minecraftClient = MinecraftClient.method_1551();
                    if (minecraftClient.method_1548() == null) {
                        return b[46];
                    }
                    string2 = CosmeticsSync.status();
                    string = string2;
                    if (n != 0) break block14;
                    if (string == null) break block15;
                    string = string2;
                    if (n != 0) break block14;
                    if (!string.isBlank()) break block16;
                }
                string = b[9];
                break block14;
            }
            string = string2;
        }
        return string;
    }

    public static List<Social$Msg> conversation(UUID uUID) {
        return CONVOS.getOrDefault(uUID, List.of());
    }

    public static void tick(boolean bl) {
        long l;
        block14: {
            long l2;
            block15: {
                long l3;
                long l4;
                int n;
                block12: {
                    block13: {
                        block16: {
                            block11: {
                                boolean bl2;
                                block10: {
                                    CosmeticsSync.ensureAuth();
                                    n = a;
                                    bl2 = Social.ready();
                                    if (n != 0) break block10;
                                    if (!bl2) break block11;
                                    bl2 = polling;
                                }
                                if (!bl2) break block16;
                            }
                            return;
                        }
                        CosmeticsSync.backgroundTick();
                        Social.ensureStream();
                        l2 = System.currentTimeMillis();
                        l4 = l2;
                        l3 = lastPoll;
                        if (n != 0) break block12;
                        l4 = l4 - l3;
                        if (!bl) break block13;
                        l3 = Social.a(675, 3664210002534032639L);
                        break block12;
                    }
                    l3 = Social.a(30773, 8354650051381304934L);
                }
                long l5 = l4 - l3;
                l = l5 == 0L ? (byte)0 : (l5 < 0L ? (byte)-1 : 1);
                if (n != 0) break block14;
                if (l >= 0) break block15;
                return;
            }
            lastPoll = l2;
            polling = true;
            l = generation;
        }
        long l6 = l;
        Social.run(() -> Social.lambda$tick$2((int)l6));
    }

    public static void clear() {
        block4: {
            block5: {
                SocialEvents.reset();
                ++generation;
                int n = a;
                Thread thread = streamThread;
                if (n != 0) break block4;
                if (thread == null) break block5;
                thread.interrupt();
            }
            SocialToast.clear();
            Social.wipe();
            MUTUAL.clear();
            MUTUAL_BUSY.clear();
            lastPoll = 0L;
        }
    }

    private static void wipe() {
        friends = List.of();
        CONVOS.clear();
        cursor = 0L;
        unread = 0;
        error = null;
    }

    public static void request(String string, Runnable runnable) {
        String[] stringArray = b;
        Social.act(stringArray[52], stringArray[44], string, runnable);
    }

    public static void accept(String string, Runnable runnable) {
        String[] stringArray = b;
        Social.act(stringArray[2], stringArray[6], string, runnable);
    }

    public static void remove(String string, Runnable runnable) {
        String[] stringArray = b;
        Social.act(stringArray[10], stringArray[6], string, runnable);
    }

    public static boolean muted(UUID uUID) {
        boolean bl;
        block5: {
            block4: {
                int n = a;
                if (uUID == null) break block4;
                bl = MUTED.contains(uUID);
                if (n != 0) break block5;
                if (!bl) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public static void setMuted(UUID uUID, boolean bl) {
        block9: {
            boolean bl2;
            block10: {
                int n = a;
                if (uUID == null) {
                    return;
                }
                bl2 = bl;
                if (n != 0) break block9;
                if (!bl2) break block10;
                MUTED.add(uUID);
                if (n == 0) break block9;
            }
            bl2 = MUTED.remove(uUID);
        }
    }

    public static List<UUID> mutedList() {
        return List.copyOf(MUTED);
    }

    private static void act(String string, String string2, String string3, Runnable runnable) {
        if (!Social.ready()) {
            error = b[4];
            return;
        }
        Social.run(() -> Social.lambda$act$3(string2, string3, string, runnable));
    }

    public static void send(UUID uUID, String string, Consumer<String> consumer) {
        block7: {
            block6: {
                if (!Social.ready() || uUID == null) break block6;
                if (!string.isBlank()) break block7;
            }
            return;
        }
        Social$Msg social$Msg = new Social$Msg(Social.a(13475, 6033081451135390460L), Social.selfUuid(), string.trim(), System.currentTimeMillis() / Social.a(4586, 3547866804283537341L));
        Social.append(uUID, social$Msg);
        Social.run(() -> Social.lambda$send$4(uUID, string, social$Msg, consumer));
    }

    public static void typing(UUID uUID) {
        long l;
        block9: {
            long l2;
            block10: {
                int n;
                block8: {
                    n = a;
                    if (Social.ready() && uUID != null) break block8;
                    return;
                }
                l2 = System.currentTimeMillis();
                l = l2;
                if (n != 0) break block9;
                if (l - lastTyping >= Social.a(25686, 3503139718606914051L)) break block10;
                return;
            }
            l = l2;
        }
        lastTyping = l;
        Social.run(() -> Social.lambda$typing$5(uUID));
    }

    public static void open(UUID uUID) {
        UUID uUID2;
        block5: {
            block6: {
                block4: {
                    int n = a;
                    if (!Social.ready()) break block4;
                    uUID2 = uUID;
                    if (n != 0) break block5;
                    if (uUID2 != null) break block6;
                }
                return;
            }
            uUID2 = uUID;
        }
        Social.run(() -> Social.lambda$open$6(uUID2));
    }

    
    private static void pollFriends() throws Exception {
        block38: {
            JsonObject jsonObject;
            String[] stringArray;
            block37: {
                int n;
                JsonObject jsonObject2;
                int n2;
                block43: {
                    block36: {
                        JsonObject jsonObject3;
                        block35: {
                            block31: {
                                Object object;
                                long l;
                                block29: {
                                    HttpResponse<String> httpResponse;
                                    block30: {
                                        block39: {
                                            l = SocialEvents.epoch();
                                            n2 = a;
                                            stringArray = b;
                                            httpResponse = Social.get(stringArray[50]);
                                            object = httpResponse;
                                            if (n2 != 0) break block29;
                                            if (object.statusCode() / 100 == 2) break block30;
                                            break block39;
                                            catch (IllegalArgumentException illegalArgumentException) {
                                                throw illegalArgumentException;
                                            }
                                        }
                                        return;
                                    }
                                    object = httpResponse.body();
                                }
                                jsonObject2 = JsonParser.parseString((String)((String)object)).getAsJsonObject();
                                ArrayList<Social$Friend> arrayList = new ArrayList<Social$Friend>();
                                HashSet<UUID> hashSet = new HashSet<UUID>();
                                stringArray = b;
                                for (JsonElement jsonElement : jsonObject2.getAsJsonArray(stringArray[66])) {
                                    JsonObject jsonObject4 = jsonElement.getAsJsonObject();
                                    if (n2 != 0) break block31;
                                    try {
                                        String string;
                                        boolean bl;
                                        String string2;
                                        String string3;
                                        UUID uUID;
                                        Social$Friend social$Friend;
                                        Social$Friend social$Friend2;
                                        ArrayList<Social$Friend> arrayList2;
                                        block42: {
                                            block34: {
                                                Social$Friend social$Friend3;
                                                block33: {
                                                    boolean bl2;
                                                    block41: {
                                                        JsonObject jsonObject5;
                                                        block32: {
                                                            block40: {
                                                                jsonObject5 = jsonObject4;
                                                                if (n2 != 0) break block32;
                                                                if (!jsonObject5.has(b[24])) break block33;
                                                                break block40;
                                                                catch (IllegalArgumentException illegalArgumentException) {
                                                                    throw illegalArgumentException;
                                                                }
                                                            }
                                                            jsonObject5 = jsonObject4.get(b[39]);
                                                        }
                                                        bl2 = jsonObject5.getAsBoolean();
                                                        if (n2 != 0) break block33;
                                                        if (!bl2) break block33;
                                                        break block41;
                                                        catch (IllegalArgumentException illegalArgumentException) {
                                                            throw illegalArgumentException;
                                                        }
                                                    }
                                                    bl2 = hashSet.add(UUID.fromString(jsonObject4.get(b[63]).getAsString()));
                                                }
                                                arrayList2 = arrayList;
                                                social$Friend2 = social$Friend3;
                                                social$Friend = social$Friend3;
                                                uUID = UUID.fromString(jsonObject4.get(b[49]).getAsString());
                                                string3 = Social.str(jsonObject4, b[56]);
                                                string2 = Social.str(jsonObject4, b[47]);
                                                if (!jsonObject4.has(b[27]) || !jsonObject4.get(b[27]).getAsBoolean()) break block34;
                                                bl = true;
                                                break block42;
                                            }
                                            bl = false;
                                        }
                                        stringArray = b;
                                        long l2 = jsonObject4.has(stringArray[28]) ? jsonObject4.get(stringArray[0]).getAsLong() : 0L;
                                        stringArray = b;
                                        int n3 = jsonObject4.has(stringArray[32]) ? jsonObject4.get(stringArray[65]).getAsInt() : 0;
                                        stringArray = b;
                                        boolean bl3 = jsonObject4.has(stringArray[51]) && jsonObject4.get(stringArray[61]).getAsBoolean();
                                        stringArray = b;
                                        String string4 = Social.str(jsonObject4, stringArray[55]);
                                        long l3 = jsonObject4.has(stringArray[33]) ? jsonObject4.get(stringArray[22]).getAsLong() : 0L;
                                        stringArray = b;
                                        if (jsonObject4.has(stringArray[71])) {
                                            string = Social.str(jsonObject4, stringArray[71]);
                                        } else {
                                            stringArray = b;
                                            if (jsonObject4.has(stringArray[27]) && jsonObject4.get(stringArray[27]).getAsBoolean()) {
                                                string = stringArray[27];
                                            } else {
                                                stringArray = b;
                                                string = stringArray[36];
                                            }
                                        }
                                        social$Friend2(uUID, string3, string2, bl, l2, n3, bl3, string4, l3, string);
                                        arrayList2.add(social$Friend);
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                    }
                                    if (n2 == 0) continue;
                                }
                                arrayList.sort(Social::lambda$pollFriends$7);
                                friends = List.copyOf(arrayList);
                                WorldShare.invites(hashSet);
                                SocialEvents.friends(l, friends);
                            }
                            jsonObject3 = jsonObject2;
                            if (n2 != 0) break block35;
                            if (!jsonObject3.has(b[65])) break block36;
                            jsonObject3 = jsonObject2.get(b[65]);
                        }
                        n = jsonObject3.getAsInt();
                        break block43;
                    }
                    n = 0;
                }
                unread = n;
                jsonObject = jsonObject2;
                if (n2 != 0) break block37;
                if (!jsonObject.has(b[57])) break block38;
                jsonObject = jsonObject2.getAsJsonObject(b[7]);
            }
            Iterator iterator = jsonObject;
            stringArray = b;
            me = new Social$Presence(Social.str((JsonObject)iterator, stringArray[71]), iterator.get(stringArray[11]).getAsBoolean(), iterator.get(stringArray[53]).getAsBoolean());
        }
    }

    private static void pollMessages() throws Exception {
        block4: {
            HttpResponse<String> httpResponse;
            block5: {
                int n = a;
                httpResponse = Social.get(b[68] + cursor);
                if (n != 0) break block4;
                if (httpResponse.statusCode() / 100 == 2) break block5;
                return;
            }
            Social.ingest(httpResponse.body());
        }
    }

    private static synchronized void ingest(String string) {
        block16: {
            long l;
            JsonArray jsonArray;
            String[] stringArray;
            int n;
            JsonObject jsonObject;
            block14: {
                block15: {
                    jsonObject = JsonParser.parseString((String)string).getAsJsonObject();
                    n = a;
                    stringArray = b;
                    jsonArray = jsonObject.getAsJsonArray(stringArray[41]);
                    long l2 = cursor - 0L;
                    l = l2 == 0L ? (byte)0 : (l2 < 0L ? (byte)-1 : 1);
                    if (n != 0) break block14;
                    if (l != false) break block15;
                    l = 1;
                    break block14;
                }
                l = 0;
            }
            long l3 = l;
            for (JsonElement jsonElement : jsonArray) {
                long l4;
                JsonObject jsonObject2;
                block17: {
                    jsonObject2 = jsonElement.getAsJsonObject();
                    stringArray = b;
                    l4 = jsonObject2.get(stringArray[59]).getAsLong();
                    if (n != 0) break block16;
                    if (l4 > cursor) break block17;
                    if (n == 0) continue;
                }
                try {
                    Social$Msg social$Msg;
                    stringArray = b;
                    UUID uUID = UUID.fromString(jsonObject2.get(stringArray[1]).getAsString());
                    String string2 = Social.str(jsonObject2, stringArray[37]);
                    UUID uUID2 = uUID;
                    Social$Msg social$Msg2 = social$Msg;
                    Social$Msg social$Msg3 = social$Msg;
                    long l5 = l4;
                    UUID uUID3 = uUID;
                    String string3 = string2;
                    long l6 = jsonObject2.has(stringArray[67]) ? jsonObject2.get(stringArray[18]).getAsLong() : 0L;
                    social$Msg2(l5, uUID3, string3, l6);
                    Social.append(uUID2, social$Msg3);
                    cursor = l4;
                    if (l3 == false) {
                        stringArray = b;
                        Social.notify(Social.str(jsonObject2, stringArray[3]), string2, uUID);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                }
                if (n == 0) continue;
            }
            if (jsonObject.has(b[60])) {
                cursor = Math.max(cursor, jsonObject.get(b[20]).getAsLong());
            }
        }
    }

    private static void ensureStream() {
        Thread thread;
        block11: {
            block12: {
                int n = a;
                if (!Social.ready()) {
                    return;
                }
                Thread thread2 = streamThread;
                thread = thread2;
                if (n != 0) break block11;
                if (thread == null) break block12;
                thread = thread2;
                if (n != 0) break block11;
                if (!thread.isAlive()) break block12;
                return;
            }
            thread = new Thread(Social::streamLoop, b[26]);
        }
        Thread thread3 = thread;
        thread3.setDaemon(true);
        streamThread = thread3;
        thread3.start();
    }

    
    private static void streamLoop() {
        block23: {
            var0 = Social.a;
            try {
                while (true) {
                    block24: {
                        if (!Social.ready() || Thread.currentThread().isInterrupted()) break block23;
                        var3_5 = Social.b;
                        var1_1 = Social.getHeld(var3_5[16] + Social.cursor + var3_5[38]);
                        v1 = var1_1.statusCode();
                        if (var0 == 0) {
                            if (v1 != 401) break block24;
                        }
                        return;
                    }
                    try {
                        block25: {
                            v3 = var1_1;
                            if (var0 != 0) break block25;
                            v1 = v3.statusCode() / 100;
lbl32:

                            if (v1 == 2) {
                                v3 = var1_1.body();
                            }
                        }
                        Social.ingest((String)v3);
                        Social.error = null;
                        if (var0 == 0) continue;
lbl43:

                        Thread.sleep(Social.a(3801, 4588648388791101576L));
                        continue;
                    }
                    catch (InterruptedException var1_2) {
                        Social.streamThread = null;
                        return;
                    }
                    catch (Exception var1_3) {
                        try {
                            Thread.sleep(Social.a(19851, 6000889234316176345L));
                            if (var0 != 0) break;
                            continue;
                        }
                        catch (InterruptedException var1_4) {
                        }
                        catch (Throwable var2_6) {
                            throw var2_6;
                        }
                    }
                    break;
                }
            }
            finally {
                Social.streamThread = null;
            }
        }
    }

    private static void history(UUID uUID) throws Exception {
        block10: {
            Object object3;
            Object object2;
            int n;
            String[] stringArray;
            block8: {
                HttpResponse<String> httpResponse;
                block9: {
                    stringArray = b;
                    httpResponse = Social.get(stringArray[48] + Social.enc(uUID.toString()));
                    n = a;
                    object2 = httpResponse;
                    if (n != 0) break block8;
                    if (object2.statusCode() / 100 == 2) break block9;
                    return;
                }
                object2 = httpResponse.body();
            }
            JsonObject jsonObject = JsonParser.parseString((String)((String)object2)).getAsJsonObject();
            ArrayList<Social$Msg> arrayList = new ArrayList<Social$Msg>();
            stringArray = b;
            for (Object object3 : jsonObject.getAsJsonArray(stringArray[12])) {
                if (n == 0) {
                    Social$Msg social$Msg;
                    Object object4 = object3;
                    JsonObject jsonObject2 = object4.getAsJsonObject();
                    stringArray = b;
                    UUID uUID2 = UUID.fromString(jsonObject2.get(stringArray[54]).getAsString());
                    ArrayList<Social$Msg> arrayList2 = arrayList;
                    Social$Msg social$Msg2 = social$Msg;
                    Social$Msg social$Msg3 = social$Msg;
                    long l = jsonObject2.get(stringArray[70]).getAsLong();
                    UUID uUID3 = uUID2;
                    String string = Social.str(jsonObject2, stringArray[37]);
                    long l2 = jsonObject2.has(stringArray[18]) ? jsonObject2.get(stringArray[18]).getAsLong() : 0L;
                    social$Msg2(l, uUID3, string, l2);
                    arrayList2.add(social$Msg3);
                    if (n == 0) continue;
                }
                break block10;
            }
            object3 = CONVOS.put(uUID, List.copyOf(arrayList));
        }
    }

    private static void notify(String string, String string2, UUID uUID) {
        Social.onClient(() -> Social.lambda$notify$8(uUID, string, string2));
    }

    private static void append(UUID uUID, Social$Msg social$Msg) {
        CONVOS.compute(uUID, (arg_0, arg_1) -> Social.lambda$append$9(social$Msg, arg_0, arg_1));
    }

    private static void unappend(UUID uUID, Social$Msg social$Msg) {
        CONVOS.computeIfPresent(uUID, (arg_0, arg_1) -> Social.lambda$unappend$10(social$Msg, arg_0, arg_1));
    }

    
    private static String detail(String string) {
        int n = a;
        try {
            JsonObject jsonObject2;
            JsonObject jsonObject = jsonObject2 = JsonParser.parseString((String)string).getAsJsonObject();
            if (n != 0) return jsonObject.getAsString();
            if (!jsonObject.has(b[34])) return b[5];
            jsonObject = jsonObject2.get(b[19]);
            return jsonObject.getAsString();
        }
        catch (Exception exception) {
        }
        return b[5];
    }

    private static String str(JsonObject jsonObject, String string) {
        String string2;
        block11: {
            block9: {
                JsonObject jsonObject2;
                block10: {
                    JsonObject jsonObject3;
                    block8: {
                        int n = a;
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
                int n = a;
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

    private static HttpResponse<String> getHeld(String string) throws Exception {
        HttpRequest httpRequest = CosmeticsSync.bearer(HttpRequest.newBuilder(URI.create(CosmeticsSync.baseUrl() + string)).timeout(Duration.ofSeconds(Social.a(8627, 6161514736319285221L))).GET()).build();
        return HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private static HttpResponse<String> get(String string) throws Exception {
        HttpRequest httpRequest = CosmeticsSync.bearer(HttpRequest.newBuilder(URI.create(CosmeticsSync.baseUrl() + string)).timeout(Duration.ofSeconds(Social.a(32513, 6293784141977000273L))).GET()).build();
        return HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    static HttpResponse<String> post(String string, String string2) throws Exception {
        String[] stringArray = b;
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(CosmeticsSync.baseUrl() + string)).timeout(Duration.ofSeconds(Social.a(17631, 6455459159040828034L))).header(stringArray[42], stringArray[21]).POST(HttpRequest.BodyPublishers.ofString(string2, StandardCharsets.UTF_8)).build();
        return HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private static void run(Runnable runnable) {
        Thread thread = new Thread(runnable, b[8]);
        thread.setDaemon(true);
        thread.start();
    }

    private static void onClient(Runnable runnable) {
        block3: {
            MinecraftClient minecraftClient;
            block2: {
                MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                int n = a;
                minecraftClient = minecraftClient2;
                if (n != 0) break block2;
                if (minecraftClient == null) break block3;
                minecraftClient = minecraftClient2;
            }
            minecraftClient.execute(runnable);
        }
    }

    private static void onClient(Consumer<String> consumer, String string) {
        block3: {
            MinecraftClient minecraftClient;
            block2: {
                MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                int n = a;
                minecraftClient = minecraftClient2;
                if (n != 0) break block2;
                if (minecraftClient == null) break block3;
                minecraftClient = minecraftClient2;
            }
            minecraftClient.execute(() -> Social.lambda$onClient$11(consumer, string));
        }
    }

    private static void lambda$onClient$11(Consumer consumer, String string) {
        consumer.accept(string);
    }

    private static List lambda$unappend$10(Social$Msg social$Msg, UUID uUID, List list) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.remove(social$Msg);
        return List.copyOf(arrayList);
    }

    private static List lambda$append$9(Social$Msg social$Msg, UUID uUID, List list) {
        ArrayList<Social$Msg> arrayList;
        block12: {
            ArrayList<Social$Msg> arrayList2;
            int n = a;
            ArrayList<Social$Msg> arrayList3 = arrayList2;
            ArrayList<Social$Msg> arrayList4 = arrayList2;
            List list2 = list == null ? List.of() : list;
            arrayList3(list2);
            arrayList = arrayList4;
            for (Social$Msg social$Msg2 : arrayList) {
                block14: {
                    long l;
                    block13: {
                        if (n != 0) break block12;
                        l = social$Msg2.id();
                        if (n != 0) break block13;
                        if (l != social$Msg.id()) break block14;
                        l = social$Msg2.id();
                    }
                    if (l != Social.a(7620, 8594709695757122461L)) {
                        return arrayList;
                    }
                }
                if (n == 0) continue;
            }
            arrayList.add(social$Msg);
        }
        return List.copyOf(arrayList);
    }

    private static void lambda$notify$8(UUID uUID, String string, String string2) {
        String string3;
        block17: {
            block19: {
                block18: {
                    UUID uUID2;
                    int n;
                    block15: {
                        block16: {
                            MinecraftClient minecraftClient = MinecraftClient.method_1551();
                            n = a;
                            uUID2 = uUID;
                            if (n != 0) break block15;
                            if (!uUID2.equals(SocialScreen.viewing())) break block16;
                            return;
                        }
                        uUID2 = uUID;
                    }
                    if (Social.muted(uUID2)) {
                        return;
                    }
                    string3 = string;
                    if (n != 0) break block17;
                    if (string3 == null) break block18;
                    string3 = string;
                    if (n != 0) break block17;
                    if (!string3.isBlank()) break block19;
                }
                string3 = b[69];
                break block17;
            }
            string3 = string;
        }
        SocialToast.push(string3, string2, uUID);
    }

    private static int lambda$pollFriends$7(Social$Friend social$Friend, Social$Friend social$Friend2) {
        Social$Friend social$Friend3;
        block58: {
            block59: {
                int n;
                block54: {
                    block55: {
                        int n2;
                        block56: {
                            block57: {
                                int n3;
                                block52: {
                                    block53: {
                                        block50: {
                                            block51: {
                                                int n4;
                                                int n5;
                                                block46: {
                                                    block49: {
                                                        Social$Friend social$Friend4;
                                                        block47: {
                                                            block48: {
                                                                block44: {
                                                                    block45: {
                                                                        int n6;
                                                                        block40: {
                                                                            block43: {
                                                                                Social$Friend social$Friend5;
                                                                                block41: {
                                                                                    block42: {
                                                                                        block38: {
                                                                                            block39: {
                                                                                                n = a;
                                                                                                social$Friend5 = social$Friend;
                                                                                                if (n != 0) break block38;
                                                                                                if (!social$Friend5.incoming()) break block39;
                                                                                                n6 = 2;
                                                                                                break block40;
                                                                                            }
                                                                                            social$Friend5 = social$Friend;
                                                                                        }
                                                                                        if (n != 0) break block41;
                                                                                        if (!social$Friend5.outgoing()) break block42;
                                                                                        n6 = 3;
                                                                                        break block40;
                                                                                    }
                                                                                    social$Friend5 = social$Friend;
                                                                                }
                                                                                n6 = social$Friend5.online() ? 1 : 0;
                                                                                if (n != 0) break block40;
                                                                                if (n6 == 0) break block43;
                                                                                n6 = 0;
                                                                                break block40;
                                                                            }
                                                                            n6 = 1;
                                                                        }
                                                                        n5 = n6;
                                                                        social$Friend4 = social$Friend2;
                                                                        if (n != 0) break block44;
                                                                        if (!social$Friend4.incoming()) break block45;
                                                                        n4 = 2;
                                                                        break block46;
                                                                    }
                                                                    social$Friend4 = social$Friend2;
                                                                }
                                                                if (n != 0) break block47;
                                                                if (!social$Friend4.outgoing()) break block48;
                                                                n4 = 3;
                                                                break block46;
                                                            }
                                                            social$Friend4 = social$Friend2;
                                                        }
                                                        n4 = social$Friend4.online() ? 1 : 0;
                                                        if (n != 0) break block46;
                                                        if (n4 == 0) break block49;
                                                        n4 = 0;
                                                        break block46;
                                                    }
                                                    n4 = 1;
                                                }
                                                int n7 = n4;
                                                n3 = n5;
                                                if (n != 0) break block50;
                                                if (n3 == n7) break block51;
                                                return Integer.compare(n5, n7);
                                            }
                                            n3 = social$Friend.unread();
                                        }
                                        if (n != 0) break block52;
                                        if (n3 == social$Friend2.unread()) break block53;
                                        return Integer.compare(social$Friend2.unread(), social$Friend.unread());
                                    }
                                    social$Friend3 = social$Friend;
                                    if (n != 0) break block54;
                                    n3 = social$Friend3.talked() ? 1 : 0;
                                }
                                if (n3 == social$Friend2.talked()) break block55;
                                n2 = social$Friend.talked();
                                if (n != 0) break block56;
                                if (n2 == 0) break block57;
                                n2 = -1;
                                break block56;
                            }
                            n2 = 1;
                        }
                        return n2;
                    }
                    social$Friend3 = social$Friend;
                }
                if (n != 0) break block58;
                if (social$Friend3.lastAt() == social$Friend2.lastAt()) break block59;
                return Long.compare(social$Friend2.lastAt(), social$Friend.lastAt());
            }
            social$Friend3 = social$Friend;
        }
        return social$Friend3.name().compareToIgnoreCase(social$Friend2.name());
    }

    private static void lambda$open$6(UUID uUID) {
        try {
            Social.history(uUID);
            JsonObject jsonObject = new JsonObject();
            String[] stringArray = b;
            jsonObject.addProperty(stringArray[30], CosmeticsSync.token());
            jsonObject.addProperty(stringArray[31], uUID.toString());
            Social.post(stringArray[35], jsonObject.toString());
            Social.pollFriends();
        }
        catch (Exception exception) {
        }
    }

    private static void lambda$typing$5(UUID uUID) {
        try {
            JsonObject jsonObject = new JsonObject();
            String[] stringArray = b;
            jsonObject.addProperty(stringArray[30], CosmeticsSync.token());
            jsonObject.addProperty(stringArray[64], uUID.toString());
            Social.post(stringArray[25], jsonObject.toString());
        }
        catch (Exception exception) {
        }
    }

    private static void lambda$send$4(UUID uUID, String string, Social$Msg social$Msg, Consumer consumer) {
        block11: {
            int n = a;
            try {
                Consumer consumer2;
                String string2;
                block10: {
                    block13: {
                        block8: {
                            block9: {
                                JsonObject jsonObject = new JsonObject();
                                String[] stringArray = b;
                                jsonObject.addProperty(stringArray[30], CosmeticsSync.token());
                                jsonObject.addProperty(stringArray[6], uUID.toString());
                                jsonObject.addProperty(stringArray[23], string.trim());
                                HttpResponse<String> httpResponse = Social.post(stringArray[58], jsonObject.toString());
                                string2 = null;
                                if (n != 0) break block8;
                                if (httpResponse.statusCode() / 100 == 2) break block9;
                                string2 = error = Social.detail(httpResponse.body());
                                Social.unappend(uUID, social$Msg);
                                break block13;
                            }
                            error = null;
                        }
                        Social.history(uUID);
                    }
                    consumer2 = consumer;
                    if (n != 0) break block10;
                    if (consumer2 == null) break block11;
                    consumer2 = consumer;
                }
                Social.onClient(consumer2, string2);
            }
            catch (Exception exception) {
                Consumer consumer3;
                block12: {
                    error = b[15];
                    Social.unappend(uUID, social$Msg);
                    consumer3 = consumer;
                    if (n != 0) break block12;
                    if (consumer3 == null) break block11;
                    consumer3 = consumer;
                }
                Social.onClient(consumer3, error);
            }
        }
    }

    
    private static void lambda$act$3(String string, String string2, String string3, Runnable runnable) {
        block15: {
            Runnable runnable2;
            block14: {
                int n;
                block13: {
                    n = a;
                    try {
                        JsonObject jsonObject = new JsonObject();
                        String[] stringArray = b;
                        jsonObject.addProperty(stringArray[30], CosmeticsSync.token());
                        jsonObject.addProperty(string, string2);
                        HttpResponse<String> httpResponse = Social.post(string3, jsonObject.toString());
                        if (n == 0) {
                            if (httpResponse.statusCode() / 100 != 2) {
                                error = Social.detail(httpResponse.body());
                                if (n == 0) break block13;
                            }
                            error = null;
                        }
                        Social.pollFriends();
                    }
                    catch (Exception exception) {
                        error = b[13];
                    }
                }
                runnable2 = runnable;
                if (n != 0) break block14;
                if (runnable2 == null) break block15;
                runnable2 = runnable;
            }
            Social.onClient(runnable2);
        }
    }

    private static void lambda$tick$2(int n) {
        block11: {
            block12: {
                int n2 = a;
                try {
                    Social.pollFriends();
                    Social.pollMessages();
                    if (n2 != 0) break block11;
                    error = null;
                    if (n == generation) break block12;
                }
                catch (Exception exception) {
                    try {
                        error = b[13];
                        if (n2 != 0) break block11;
                    }
                    catch (Throwable throwable) {
                        block13: {
                            block14: {
                                if (n2 != 0) break block13;
                                if (n == generation) break block14;
                                Social.wipe();
                            }
                            polling = false;
                        }
                        throw throwable;
                    }
                    if (n != generation) {
                        Social.wipe();
                    }
                    polling = false;
                }
                Social.wipe();
            }
            polling = false;
        }
    }

    private static int lambda$roster$1(Social$Friend social$Friend, Social$Friend social$Friend2) {
        return social$Friend.name().compareToIgnoreCase(social$Friend2.name());
    }

    
    private static void lambda$setPresence$0(Social$Presence social$Presence) {
        int n = a;
        try {
            String string;
            block8: {
                Object object;
                block6: {
                    HttpResponse<String> httpResponse;
                    block7: {
                        block9: {
                            JsonObject jsonObject = new JsonObject();
                            String[] stringArray = b;
                            jsonObject.addProperty(stringArray[40], CosmeticsSync.token());
                            jsonObject.addProperty(stringArray[72], social$Presence.status());
                            jsonObject.addProperty(stringArray[62], Boolean.valueOf(social$Presence.showServer()));
                            jsonObject.addProperty(stringArray[43], Boolean.valueOf(social$Presence.showStatus()));
                            httpResponse = Social.post(stringArray[29], jsonObject.toString());
                            object = httpResponse;
                            if (n != 0) break block6;
                            if (object.statusCode() / 100 != 2) break block7;
                            break block9;
                            catch (Exception exception) {
                                throw exception;
                            }
                        }
                        string = null;
                        break block8;
                    }
                    object = httpResponse.body();
                }
                string = Social.detail((String)object);
            }
            error = string;
        }
        catch (Exception exception) {
            error = b[45];
        }
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[73];
                        var11_1 = 0;
                        var10_2 = "\u0010*dYL:\n\u0005\u0004\u00064jM\u0019O'uI\u0017 \b\u0003/dL\u00175\u0015\t#kD\u00172\u0004\u0003#uT\u0004\u000e'hE\r.)q\u0000K:\u0000\u000e#a\u0000Q=\u00114.dT\u00187\u000e\u0004fkOLs\u0010\u000f4n\u0006\u0014'wG]'\u0002\r#\r!3wOJ2J3)fIY?\u000b3/bNQ=\u0000@/k\u2006\u0019O'uI\u0017 \b\u0003/dL\u00175\u0015\t#kD\u0017!\u0002\r)sE\n\u0013.jWk6\u0015\u0016#w\b\r#vSY4\u0002\u0013\u0018#'kNW'G\u0012#dCPs\u0013\b#%S]!\u0011\t%`\u0006\u000f(iIV6\u0010-#vSY4\u0002@(jT\u0018 \u0002\u000e2\u0019O'uI\u0017 \b\u0003/dL\u0017 \u0013\u0012#dM\u0007 \u000e\u000e%`\u001d\u0018#)vM]'\u000e\u00035%S]!\u0011\t%`\u0000Q G\u000f c\u0002\u00012\u0006\u0004#qAQ?\u0006\u00033wSW!\u0010\u00016uLQ0\u0006\u0014/jN\u00179\u0014\u000f(\u0007\f'vTg2\u0013\u0004\u0002)aY\u0007\t(sIL6\u0003\u0012O'uI\u0017 \b\u0003/dL\u0017'\u001e\u0010/kG\u0014!3wOJ2J3)fIY?J32wEY>\u0006\u000f(iIV6\b\u0010*dYL:\n\u0005\u0014O'uI\u0017 \b\u0003/dL\u0017#\u0015\u00055`N[6\u0005\u0014)nEV\u0004\u0010#`R\u0006\u0015(wEY7\u0007\f'vTg2\u0013\u0006\u0004#qAQ?\u0010O'uI\u0017 \b\u0003/dL\u0017!\u0002\u0001\"\u0007\u000f cLQ=\u0002\u0004\u0002)aY\bF1dILnVX\u0007\t(sIL6\u0003\u0005\u0014)nEV\b\r#vSY4\u0002\u0013\f#)kT]=\u0013M\u0012|P]\n\u0013.jWk'\u0006\u00143v\u0006\u0014'wG]'\u0018#'kNW'G\u0012#dCPs\u0013\b#%S]!\u0011\t%`\u0014.)%mQ=\u0002\u00034dFLs\u0014\u00055vIW=\u0005\u00132dT]\u0019O'uI\u0017 \b\u0003/dL\u0017;\u000e\u00132jRAl\u0017\u0005#w\u001d\u0004\u00153lD\u0013O'uI\u0017 \b\u0003/dL\u00175\u0015\t#kDK\u0006\u0014?uIV4\u001aO'uI\u0017 \b\u0003/dL\u00175\u0015\t#kD\u0017!\u0002\u00113`SL\n\u0013.jWk'\u0006\u00143v\u0004\u00064jM\u0006\u0013#wV]!\u0004\u000e'hE\u0002\r#\u0013O'uI\u0017 \b\u0003/dL\u0017>\u0002\u00135dG]\u0002\t\"\u0006\u00033wSW!\u0006\u0014?uIV4\n\u0013.jWk6\u0015\u0016#w\u0004\u00153lD\u0004\u0010#`R\u0006\u0015(wEY7\u0007\u00064lEV7\u0014\u0002\u00012\u001bO'uI\u0017 \b\u0003/dL\u0017>\u0002\u00135dG] X\u0013/kC]n\u0007-#vSY4\u0002\u0002\t\"";
                        var12_3 = "\u0010*dYL:\n\u0005\u0004\u00064jM\u0019O'uI\u0017 \b\u0003/dL\u00175\u0015\t#kD\u00172\u0004\u0003#uT\u0004\u000e'hE\r.)q\u0000K:\u0000\u000e#a\u0000Q=\u00114.dT\u00187\u000e\u0004fkOLs\u0010\u000f4n\u0006\u0014'wG]'\u0002\r#\r!3wOJ2J3)fIY?\u000b3/bNQ=\u0000@/k\u2006\u0019O'uI\u0017 \b\u0003/dL\u00175\u0015\t#kD\u0017!\u0002\r)sE\n\u0013.jWk6\u0015\u0016#w\b\r#vSY4\u0002\u0013\u0018#'kNW'G\u0012#dCPs\u0013\b#%S]!\u0011\t%`\u0006\u000f(iIV6\u0010-#vSY4\u0002@(jT\u0018 \u0002\u000e2\u0019O'uI\u0017 \b\u0003/dL\u0017 \u0013\u0012#dM\u0007 \u000e\u000e%`\u001d\u0018#)vM]'\u000e\u00035%S]!\u0011\t%`\u0000Q G\u000f c\u0002\u00012\u0006\u0004#qAQ?\u0006\u00033wSW!\u0010\u00016uLQ0\u0006\u0014/jN\u00179\u0014\u000f(\u0007\f'vTg2\u0013\u0004\u0002)aY\u0007\t(sIL6\u0003\u0012O'uI\u0017 \b\u0003/dL\u0017'\u001e\u0010/kG\u0014!3wOJ2J3)fIY?J32wEY>\u0006\u000f(iIV6\b\u0010*dYL:\n\u0005\u0014O'uI\u0017 \b\u0003/dL\u0017#\u0015\u00055`N[6\u0005\u0014)nEV\u0004\u0010#`R\u0006\u0015(wEY7\u0007\f'vTg2\u0013\u0006\u0004#qAQ?\u0010O'uI\u0017 \b\u0003/dL\u0017!\u0002\u0001\"\u0007\u000f cLQ=\u0002\u0004\u0002)aY\bF1dILnVX\u0007\t(sIL6\u0003\u0005\u0014)nEV\b\r#vSY4\u0002\u0013\f#)kT]=\u0013M\u0012|P]\n\u0013.jWk'\u0006\u00143v\u0006\u0014'wG]'\u0018#'kNW'G\u0012#dCPs\u0013\b#%S]!\u0011\t%`\u0014.)%mQ=\u0002\u00034dFLs\u0014\u00055vIW=\u0005\u00132dT]\u0019O'uI\u0017 \b\u0003/dL\u0017;\u000e\u00132jRAl\u0017\u0005#w\u001d\u0004\u00153lD\u0013O'uI\u0017 \b\u0003/dL\u00175\u0015\t#kDK\u0006\u0014?uIV4\u001aO'uI\u0017 \b\u0003/dL\u00175\u0015\t#kD\u0017!\u0002\u00113`SL\n\u0013.jWk'\u0006\u00143v\u0004\u00064jM\u0006\u0013#wV]!\u0004\u000e'hE\u0002\r#\u0013O'uI\u0017 \b\u0003/dL\u0017>\u0002\u00135dG]\u0002\t\"\u0006\u00033wSW!\u0006\u0014?uIV4\n\u0013.jWk6\u0015\u0016#w\u0004\u00153lD\u0004\u0010#`R\u0006\u0015(wEY7\u0007\u00064lEV7\u0014\u0002\u00012\u001bO'uI\u0017 \b\u0003/dL\u0017>\u0002\u00135dG] X\u0013/kC]n\u0007-#vSY4\u0002\u0002\t\"".length();
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
                            var10_2 = "\u00132dTM \u0006\u00132dTM ";
                            var12_3 = "\u00132dTM \u0006\u00132dTM ".length();
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
                                v10 = 96;
                                break;
                            }
                            case 1: {
                                v10 = 70;
                                break;
                            }
                            case 2: {
                                v10 = 5;
                                break;
                            }
                            case 3: {
                                v10 = 32;
                                break;
                            }
                            case 4: {
                                v10 = 56;
                                break;
                            }
                            case 5: {
                                v10 = 83;
                                break;
                            }
                            default: {
                                v10 = 103;
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
                Social.b = var13;
                var0_7 = 5715875471268993415L;
                var6_8 = new long[14];
                var3_9 = 0;
                var4_10 = "\u007f\u00cfyv{{\u00d0<\r\u00ad\u00d4tFu\u00b2\u00b8~nPz\u00a6\u00e3-\u00d2\u001a\u00d0\u00c7t\u00f5\u00fe\u001eJp\u00fc\u00f9\u0010\u00d8T\u00ba\u00b7\u0018\n\u00dd!\u00ce\u00dd\u00c0\u00d9<\u00a3s\u0081\u00f2\u0013\t\u00c1\u001c\u0015\u00ae\u0083\u00ee\u00ba\u00e1\u00d6\u0016\u00c4\u00bb\u0092\u0011\u00f0\u00fb\n}\u008b=\u00e0Z\":\u00a8c\u0014\u00ee\u0017\u0081H\u00f4\u0084w\u00d1e\u00e9h!3\u0093";
                var5_11 = "\u007f\u00cfyv{{\u00d0<\r\u00ad\u00d4tFu\u00b2\u00b8~nPz\u00a6\u00e3-\u00d2\u001a\u00d0\u00c7t\u00f5\u00fe\u001eJp\u00fc\u00f9\u0010\u00d8T\u00ba\u00b7\u0018\n\u00dd!\u00ce\u00dd\u00c0\u00d9<\u00a3s\u0081\u00f2\u0013\t\u00c1\u001c\u0015\u00ae\u0083\u00ee\u00ba\u00e1\u00d6\u0016\u00c4\u00bb\u0092\u0011\u00f0\u00fb\n}\u008b=\u00e0Z\":\u00a8c\u0014\u00ee\u0017\u0081H\u00f4\u0084w\u00d1e\u00e9h!3\u0093".length();
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
                    var4_10 = "G\u00eb\u00abd\u0081\u0091\u00dd\u00e5\u0017\u009f\u00d6\u009c\u00f4\u00a3\u0017\u00f9";
                    var5_11 = "G\u00eb\u00abd\u0081\u0091\u00dd\u00e5\u0017\u009f\u00d6\u009c\u00f4\u00a3\u0017\u00f9".length();
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
        Social.c = var6_8;
        Social.d = new Long[14];
        Social.IDLE_MS = Social.a(26118, 6398780446443307102L);
        Social.OPEN_MS = Social.a(2970, 4072304768599092676L);
        Social.me = new Social$Presence(Social.b[14], true, true);
        Social.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(Social.a(3425, 4827588326102854453L))).followRedirects(HttpClient.Redirect.NORMAL).build();
        Social.friends = List.of();
        Social.CONVOS = new ConcurrentHashMap<UUID, List<Social$Msg>>();
        Social.MUTUAL = new ConcurrentHashMap<UUID, List<String>>();
        Social.MUTUAL_BUSY = ConcurrentHashMap.newKeySet();
        Social.MUTED = ConcurrentHashMap.newKeySet();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x6655) & Short.MAX_VALUE;
        if (d[n2] == null) {
            Social.d[n2] = c[n2] ^ l;
        }
        return d[n2];
    }
}
