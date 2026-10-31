package dev.quelchi.aurora.cosmetics.backend;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync$Release;
import dev.quelchi.aurora.cosmetics.backend.RemoteContent;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.renderer.CapeManager;
import dev.quelchi.aurora.cosmetics.renderer.MaceEffects;
import dev.quelchi.aurora.cosmetics.renderer.cape.CosmeticRegistry;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.social.Plus;
import dev.quelchi.aurora.social.Social;
import dev.quelchi.aurora.social.WorldShare;
import dev.quelchi.aurora.util.BuildInfo;
import dev.quelchi.aurora.util.MachineId;
import dev.quelchi.aurora.util.Updater;
import java.lang.invoke.LambdaMetafactory;
import java.math.BigInteger;
import java.net.Proxy;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.text.Text;

public final class CosmeticsSync {
    private static volatile String baseUrl;
    private static final HttpClient HTTP;
    private static volatile String token;
    private static volatile String status;
    private static volatile List<String> pushedCape;
    private static long lastPoll;
    private static long lastAuthAttempt;
    private static final long POLL_MS;
    private static final long AUTH_RETRY_MS;
    private static volatile UUID ownUuid;
    private static volatile boolean adopting;
    private static volatile String adoptRetry;
    private static volatile long adoptRetryAt;
    private static final ExecutorService PUSHES;
    private static volatile int eventsGeneration;
    private static volatile List<CosmeticsSync$Release> releases;
    private static volatile String contentTag;
    public static volatile boolean contentTried;
    private static volatile MinecraftSessionService sessionService;
    public static int a;
    private static final String[] b;
    private static final long[] c;
    private static final Long[] d;

    private CosmeticsSync() {
    }

    public static void configure(String string) {
        block12: {
            block11: {
                String string2;
                int n;
                block10: {
                    String string3;
                    block8: {
                        block9: {
                            n = a;
                            string3 = string;
                            if (n != 0) break block8;
                            if (string3 != null) break block9;
                            string2 = "";
                            break block10;
                        }
                        string3 = string.trim();
                    }
                    String[] stringArray = b;
                    string2 = string3.replaceAll(stringArray[145], "");
                }
                baseUrl = string2;
                if (n != 0) break block11;
                if (!baseUrl.isEmpty()) break block12;
                token = null;
            }
            status = b[77];
        }
    }

    public static boolean enabled() {
        boolean bl;
        block2: {
            block3: {
                int n = a;
                bl = baseUrl.isEmpty();
                if (n != 0) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    public static String baseUrl() {
        return baseUrl;
    }

    public static String token() {
        return token;
    }

    public static UUID ownUuid() {
        return ownUuid;
    }

    public static String status() {
        return status;
    }

    public static void backgroundTick() {
        block9: {
            long l;
            block10: {
                int n;
                block8: {
                    n = a;
                    if (CosmeticsSync.enabled() && token != null) break block8;
                    return;
                }
                l = System.currentTimeMillis();
                if (n != 0) break block9;
                if (l - lastPoll > CosmeticsSync.a(4825, 2863688996672320778L)) break block10;
                return;
            }
            lastPoll = l;
            CosmeticsSync.run(CosmeticsSync::poll);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void ensureAuth() {
        long l;
        block31: {
            long l2;
            block32: {
                String string;
                int n;
                block28: {
                    block29: {
                        UUID uUID;
                        UUID uUID2;
                        MinecraftClient minecraftClient;
                        block30: {
                            MinecraftClient minecraftClient2;
                            block26: {
                                block27: {
                                    block25: {
                                        n = a;
                                        if (!CosmeticsSync.enabled()) {
                                            return;
                                        }
                                        minecraftClient = MinecraftClient.method_1551();
                                        minecraftClient2 = minecraftClient;
                                        if (n != 0) break block25;
                                        if (minecraftClient2.method_1548() == null) return;
                                        minecraftClient2 = minecraftClient;
                                    }
                                    if (n != 0) break block26;
                                    if (minecraftClient2.method_1548().method_44717() != null) break block27;
                                    return;
                                }
                                minecraftClient2 = minecraftClient;
                            }
                            uUID2 = minecraftClient2.method_1548().method_44717();
                            string = token;
                            if (n != 0) break block28;
                            if (string == null) break block29;
                            uUID = ownUuid;
                            if (n != 0) break block30;
                            if (uUID == null) break block29;
                            uUID = ownUuid;
                        }
                        if (!uUID.equals(uUID2)) {
                            Aurora.LOG.info(b[3], (Object)minecraftClient.method_1548().method_1676());
                            CosmeticsSync.reset();
                            Social.clear();
                        }
                    }
                    string = token;
                }
                if (string != null) {
                    return;
                }
                l2 = System.currentTimeMillis();
                l = l2;
                if (n != 0) break block31;
                if (l - lastAuthAttempt >= CosmeticsSync.a(27424, 692148948138544376L)) break block32;
                return;
            }
            l = l2;
        }
        lastAuthAttempt = l;
        CosmeticsSync.run(CosmeticsSync::authenticate);
    }

    public static String getStatus() {
        return status;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void tick() {
        Runnable runnable;
        block52: {
            long l;
            int n;
            block51: {
                long l2;
                long l3;
                block50: {
                    block49: {
                        String string;
                        block47: {
                            List<String> list;
                            block48: {
                                boolean bl;
                                List<String> list2;
                                block46: {
                                    String string2;
                                    block43: {
                                        block44: {
                                            long l4;
                                            block45: {
                                                MinecraftClient minecraftClient;
                                                block42: {
                                                    n = a;
                                                    if (!CosmeticsSync.enabled()) {
                                                        return;
                                                    }
                                                    MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                                                    minecraftClient = minecraftClient2;
                                                    if (n != 0) break block42;
                                                    if (minecraftClient.field_1724 == null) return;
                                                    minecraftClient = minecraftClient2;
                                                }
                                                if (minecraftClient.method_1562() == null) {
                                                    return;
                                                }
                                                l3 = System.currentTimeMillis();
                                                string2 = token;
                                                if (n != 0) break block43;
                                                if (string2 != null) break block44;
                                                l4 = l3;
                                                if (n != 0) break block45;
                                                if (l4 - lastAuthAttempt <= CosmeticsSync.a(2787, 6098532375168003377L)) return;
                                                l4 = l3;
                                            }
                                            lastAuthAttempt = l4;
                                            CosmeticsSync.run(CosmeticsSync::authenticate);
                                            return;
                                        }
                                        string2 = Cosmetics.getOwnCape();
                                    }
                                    String string3 = string2;
                                    list2 = List.of(string3, Cosmetics.getOwnCapeUrl(), Cosmetics.getOwnWings(), Cosmetics.getOwnTrail(), Cosmetics.getOwnKillEffect(), Cosmetics.getOwnMaceEffect(), Cosmetics.getOwnEmote(), Cosmetics.getOwnHitEffect());
                                    bl = adopting;
                                    if (n != 0) break block46;
                                    if (bl) break block47;
                                    list = list2;
                                    if (n != 0) break block48;
                                    bl = list.equals(pushedCape);
                                }
                                if (bl) break block47;
                                list = list2;
                            }
                            pushedCape = list;
                            string = token;
                            PUSHES.execute(() -> CosmeticsSync.lambda$tick$0(string));
                        }
                        string = adoptRetry;
                        if (string == null) break block49;
                        l = l3;
                        l2 = adoptRetryAt;
                        if (n != 0) break block50;
                        if (l < l2) break block49;
                        adoptRetry = null;
                        CosmeticsSync.run(() -> CosmeticsSync.lambda$tick$1(string));
                    }
                    l = l3 - lastPoll;
                    if (n != 0) break block51;
                    l2 = CosmeticsSync.a(19243, 9138840367975918844L);
                }
                if (l <= l2) return;
                l = l3;
            }
            lastPoll = l;
            runnable = CosmeticsSync::poll;
            if (n != 0) break block52;
            CosmeticsSync.run(runnable);
            if (RemoteContent.loaded()) return;
            runnable = CosmeticsSync::fetchContentNow;
        }
        CosmeticsSync.run(runnable);
    }

    public static void reset() {
        String string;
        int n;
        block7: {
            block8: {
                RemoteContent.markUnloaded();
                token = null;
                n = a;
                ownUuid = null;
                pushedCape = null;
                adopting = true;
                adoptRetry = null;
                string = "";
                if (n != 0) break block7;
                contentTag = string;
                Cosmetics.setCreator(false);
                ++eventsGeneration;
                Cosmetics.clearRemote();
                Cosmetics.clearOwnership();
                if (!CosmeticsSync.enabled()) break block8;
                string = b[113];
                break block7;
            }
            string = b[52];
        }
        status = string;
        if (Module.a) {
            a = ++n;
        }
    }

    private static void authenticate() {
        adopting = true;
        adoptRetry = null;
        int n = a;
        try {
            Object object;
            String[] stringArray;
            block6: {
                HttpResponse<String> httpResponse;
                block7: {
                    MinecraftClient minecraftClient = MinecraftClient.method_1551();
                    String string = minecraftClient.method_1548().method_1676();
                    String string2 = CosmeticsSync.sha1Hex(UUID.randomUUID().toString());
                    CosmeticsSync.sessionService().joinServer(minecraftClient.method_1548().method_44717(), minecraftClient.method_1548().method_1674(), string2);
                    JsonObject jsonObject = new JsonObject();
                    stringArray = b;
                    jsonObject.addProperty(stringArray[84], string);
                    jsonObject.addProperty(stringArray[37], string2);
                    httpResponse = CosmeticsSync.post(stringArray[98], jsonObject.toString());
                    object = httpResponse;
                    if (n != 0) break block6;
                    if (object.statusCode() / 100 == 2) break block7;
                    stringArray = b;
                    status = stringArray[22] + httpResponse.statusCode() + ")";
                    return;
                }
                object = httpResponse.body();
            }
            JsonObject jsonObject = JsonParser.parseString((String)((String)object)).getAsJsonObject();
            stringArray = b;
            token = jsonObject.get(stringArray[23]).getAsString();
            status = stringArray[26];
            String string = jsonObject.get(stringArray[136]).getAsString();
            try {
                ownUuid = UUID.fromString(string);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                ownUuid = null;
            }
            CosmeticsSync.fetchManifest(string.replace("-", ""));
            Plus.refresh(true);
            CosmeticsSync.startEvents();
            CosmeticsSync.adoptOwn(string);
        }
        catch (Exception exception) {
            String[] stringArray = b;
            status = stringArray[83];
            Aurora.LOG.debug(stringArray[6], (Throwable)exception);
            token = null;
        }
    }

    
    private static void adoptOwn(String string) {
        int n = a;
        try {
            JsonObject jsonObject2;
            String[] stringArray = b;
            HttpResponse<String> httpResponse = CosmeticsSync.get(stringArray[92] + string);
            Object object = httpResponse;
            if (n == 0) {
                if (object.statusCode() / 100 != 2) {
                    CosmeticsSync.retryAdopt(string);
                    return;
                }
                object = httpResponse.body();
            }
            stringArray = b;
            JsonObject jsonObject = jsonObject2 = JsonParser.parseString((String)((String)object)).getAsJsonObject().getAsJsonObject(stringArray[4]);
            if (n == 0) {
                jsonObject = jsonObject.has(string) ? jsonObject2.getAsJsonObject(string) : new JsonObject();
            }
            JsonObject jsonObject3 = jsonObject;
            stringArray = b;
            String string9 = CosmeticsSync.str(jsonObject3, stringArray[139]);
            String string8 = CosmeticsSync.str(jsonObject3, stringArray[122]);
            String string7 = CosmeticsSync.str(jsonObject3, stringArray[74]);
            String string6 = CosmeticsSync.str(jsonObject3, stringArray[103]);
            String string5 = CosmeticsSync.str(jsonObject3, stringArray[58]);
            String string4 = CosmeticsSync.str(jsonObject3, stringArray[0]);
            String string3 = CosmeticsSync.str(jsonObject3, stringArray[32]);
            String string2 = CosmeticsSync.resolveRemoteCape(string9, string8);
            if (n != 0) return;
            if (!CosmeticsSync.isOwn(string)) {
                return;
            }
            MinecraftClient.method_1551().execute(() -> CosmeticsSync.lambda$adoptOwn$3(string2, string8, string7, string6, string5, string4, string3));
            pushedCape = List.of(string2, string8, string7, string6, string5, string4, "", string3);
            adopting = false;
            return;
        }
        catch (Exception exception) {
            Aurora.LOG.debug(b[101], (Throwable)exception);
            CosmeticsSync.retryAdopt(string);
        }
    }

    private static void retryAdopt(String string) {
        String string2;
        block4: {
            block5: {
                int n = a;
                string2 = string;
                if (n != 0) break block4;
                if (CosmeticsSync.isOwn(string2)) break block5;
                return;
            }
            adoptRetryAt = System.currentTimeMillis() + CosmeticsSync.a(14068, 7651462570665087269L);
            string2 = string;
        }
        adoptRetry = string2;
    }

    private static boolean isOwn(String string) {
        boolean bl;
        block6: {
            block5: {
                UUID uUID;
                int n;
                block4: {
                    UUID uUID2 = ownUuid;
                    n = a;
                    uUID = uUID2;
                    if (n != 0) break block4;
                    if (uUID == null) break block5;
                    uUID = uUID2;
                }
                bl = uUID.toString().replace("-", "").equalsIgnoreCase(string.replace("-", ""));
                if (n != 0) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    
    private static void push(String var0) {
        block29: {
            block24: {
                block31: {
                    block23: {
                        block30: {
                            var1_1 = CosmeticsSync.a;
                            if (CosmeticsSync.adopting) // goto
                            v0 = var0;
                            if (var1_1 != 0) break block23;
                            break block30;
                            catch (Exception v1) {
                                throw v1;
                            }
                        }
                        if (v0 != null) {
                        }
                        v0 = var0;
                    }
                    if (var1_1 != 0) // goto
                    if (v0.equals(CosmeticsSync.token)) break block24;
                    break block31;
                    catch (Exception v3) {
                        throw v3;
                    }
                }
                return;
            }
            try {
                block34: {
                    block35: {
                        block27: {
                            block28: {
                                block33: {
                                    block25: {
                                        block26: {
                                            block32: {
                                                v0 = Cosmetics.getOwnCape();
lbl32:

                                                var2_2 = v0;
                                                var3_4 = new JsonObject();
                                                v5 = var3_4;
                                                v6 = CosmeticsSync.b[142];
                                                v7 = var0;
                                                if (var1_1 != 0) break block25;
                                                v5.addProperty(v6, v7);
                                                v5 = var3_4;
                                                v6 = CosmeticsSync.b[82];
                                                if (var2_2 != null) break block26;
                                                break block32;
                                                catch (Exception v8) {
                                                    throw v8;
                                                }
                                            }
                                            v7 = "";
                                            break block25;
                                        }
                                        v7 = var2_2;
                                    }
                                    v5.addProperty(v6, v7);
                                    var4_5 = Cosmetics.getOwnCapeUrl();
                                    if (var1_1 != 0) break block27;
                                    if (var4_5.isBlank()) break block28;
                                    break block33;
                                    catch (Exception v10) {
                                        throw v10;
                                    }
                                }
                                var3_4.addProperty(CosmeticsSync.b[75], CosmeticsSync.relative(var4_5));
                            }
                            var6_6 = CosmeticsSync.b;
                            var3_4.addProperty(var6_6[108], Cosmetics.getOwnWings());
                            var3_4.addProperty(var6_6[76], "");
                            var3_4.addProperty(var6_6[123], Cosmetics.getOwnTrail());
                            var3_4.addProperty(var6_6[20], Cosmetics.getOwnKillEffect());
                            var3_4.addProperty(var6_6[110], Cosmetics.getOwnMaceEffect());
                            var3_4.addProperty(var6_6[70], Cosmetics.getOwnHitEffect());
                            var3_4.addProperty(var6_6[8], "");
                            var3_4.addProperty(var6_6[44], Cosmetics.getOwnEmote());
                        }
                        var6_6 = CosmeticsSync.b;
                        var5_7 = CosmeticsSync.post(var6_6[90], var3_4.toString());
                        if (var1_1 != 0) break block34;
                        if (var5_7.statusCode() != 401) break block29;
                        break block35;
                        catch (Exception v12) {
                            throw v12;
                        }
                    }
                    CosmeticsSync.token = null;
                }
                var6_6 = CosmeticsSync.b;
                CosmeticsSync.status = var6_6[146];
            }
            catch (Exception var2_3) {
                Aurora.LOG.debug(CosmeticsSync.b[10], (Throwable)var2_3);
            }
        }
    }

    private static String currentServer() {
        int n = a;
        try {
            ServerInfo serverInfo;
            block21: {
                ServerInfo serverInfo2;
                block22: {
                    block20: {
                        block19: {
                            MinecraftClient minecraftClient;
                            block17: {
                                MinecraftClient minecraftClient2;
                                block18: {
                                    block16: {
                                        block15: {
                                            boolean bl;
                                            block13: {
                                                block14: {
                                                    bl = WorldShare.hosting();
                                                    if (n != 0) break block13;
                                                    if (!bl) break block14;
                                                    return WorldShare.presence();
                                                }
                                                bl = WorldShare.joined();
                                            }
                                            if (bl) {
                                                return "";
                                            }
                                            minecraftClient2 = MinecraftClient.method_1551();
                                            minecraftClient = minecraftClient2;
                                            if (n != 0) break block15;
                                            if (minecraftClient == null) break block16;
                                            minecraftClient = minecraftClient2;
                                        }
                                        if (n != 0) break block17;
                                        if (!minecraftClient.method_1542()) break block18;
                                    }
                                    return "";
                                }
                                minecraftClient = minecraftClient2;
                            }
                            serverInfo2 = minecraftClient.method_1558();
                            serverInfo = serverInfo2;
                            if (n != 0) break block19;
                            if (serverInfo == null) break block20;
                            serverInfo = serverInfo2;
                        }
                        if (n != 0) break block21;
                        if (serverInfo.field_3761 != null) break block22;
                    }
                    return "";
                }
                serverInfo = serverInfo2;
            }
            return serverInfo.field_3761.replaceAll(b[38], "").trim();
        }
        catch (Exception exception) {
            return "";
        }
    }

    
    private static void heartbeat() {
        block32: {
            var0 = CosmeticsSync.a;
            if (CosmeticsSync.token == null) {
                return;
            }
            try {
                block34: {
                    block39: {
                        block33: {
                            block31: {
                                block38: {
                                    block37: {
                                        block36: {
                                            block30: {
                                                block28: {
                                                    block29: {
                                                        block35: {
                                                            block27: {
                                                                var1_1 = new JsonObject();
                                                                var5_3 = CosmeticsSync.b;
                                                                var1_1.addProperty(var5_3[23], CosmeticsSync.token);
                                                                var1_1.addProperty(var5_3[5], CosmeticsSync.currentServer());
                                                                var2_4 = MinecraftClient.method_1551();
                                                                v1 = var1_1;
                                                                v2 = var5_3[124];
                                                                v3 = var2_4;
                                                                if (var0 != 0) break block27;
                                                                if (v3 == null) break block28;
                                                                v3 = var2_4;
                                                            }
                                                            if (var0 != 0) break block29;
                                                            if (v3.field_1687 == null) break block28;
                                                            break block35;
                                                            catch (Exception v5) {
                                                                throw v5;
                                                            }
                                                        }
                                                        v3 = var2_4;
                                                    }
                                                    if (v3.field_1724 == null) break block28;
                                                    v7 = true;
                                                    break block30;
                                                }
                                                v7 = false;
                                            }
                                            v1.addProperty(v2, Boolean.valueOf(v7));
                                            var5_3 = CosmeticsSync.b;
                                            var1_1.addProperty(var5_3[79], Updater.selfSha());
                                            var1_1.addProperty(var5_3[7], BuildInfo.label());
                                            var3_5 = CosmeticsSync.post(var5_3[149], var1_1.toString());
                                            v9 = var3_5.statusCode();
                                            if (var0 != 0) break block31;
                                            if (v9 != 401) // goto
                                            break block36;
                                            catch (Exception v10) {
                                                throw v10;
                                            }
                                        }
                                        CosmeticsSync.token = null;
                                        CosmeticsSync.status = CosmeticsSync.b[16];
                                        if (var0 == 0) break block32;
                                        break block37;
                                        catch (Exception v11) {
                                            throw v11;
                                        }
                                    }
                                    v12 = var3_5;
                                    if (var0 != 0) break block33;
                                    break block38;
                                    catch (Exception v13) {
                                        throw v13;
                                    }
                                }
                                v9 = v12.statusCode() / 100;
                            }
                            if (v9 != 2) break block32;
                            v12 = var3_5.body();
                        }
                        var4_6 = JsonParser.parseString((String)((String)v12)).getAsJsonObject();
                        v16 = var4_6;
                        if (var0 != 0) break block34;
                        if (!v16.has(CosmeticsSync.b[94])) break block32;
                        break block39;
                        catch (Exception v17) {
                            throw v17;
                        }
                    }
                    v16 = var4_6.get(CosmeticsSync.b[94]);
                }
                Cosmetics.setShards(v16.getAsLong());
            }
            catch (Exception var1_2) {
            }
        }
    }

    
    private static void fetchManifest(String string) {
        block58: {
            int n = a;
            try {
                boolean bl;
                Set<String> set;
                long l;
                HashMap hashMap;
                HashMap hashMap2;
                HashSet<String> hashSet;
                block72: {
                    block71: {
                        String string2;
                        JsonObject jsonObject;
                        block70: {
                            JsonObject jsonObject2;
                            block88: {
                                block87: {
                                    block69: {
                                        JsonObject jsonObject3;
                                        String[] stringArray;
                                        block85: {
                                            block86: {
                                                block84: {
                                                    block68: {
                                                        JsonObject jsonObject4;
                                                        block67: {
                                                            block83: {
                                                                Set<String> set2;
                                                                block82: {
                                                                    block66: {
                                                                        Object object;
                                                                        block80: {
                                                                            block81: {
                                                                                block62: {
                                                                                    long l2;
                                                                                    HashMap<String, Map<String, Long>> hashMap3;
                                                                                    block79: {
                                                                                        block65: {
                                                                                            JsonObject jsonObject5;
                                                                                            block64: {
                                                                                                block78: {
                                                                                                    HashMap<String, Map<String, Long>> hashMap42222;
                                                                                                    block61: {
                                                                                                        JsonObject jsonObject6;
                                                                                                        Object object22222;
                                                                                                        block60: {
                                                                                                            block77: {
                                                                                                                JsonElement jsonElement2222;
                                                                                                                Map<String, Set<String>> map;
                                                                                                                block76: {
                                                                                                                    block57: {
                                                                                                                        JsonObject jsonObject7;
                                                                                                                        block56: {
                                                                                                                            block75: {
                                                                                                                                Map<String, Set<String>> map2;
                                                                                                                                block74: {
                                                                                                                                    block55: {
                                                                                                                                        JsonObject jsonObject8;
                                                                                                                                        block54: {
                                                                                                                                            block73: {
                                                                                                                                                Object object3;
                                                                                                                                                block52: {
                                                                                                                                                    HttpResponse<String> httpResponse;
                                                                                                                                                    block53: {
                                                                                                                                                        stringArray = b;
                                                                                                                                                        httpResponse = CosmeticsSync.get(stringArray[135] + string);
                                                                                                                                                        object3 = httpResponse;
                                                                                                                                                        if (n != 0) break block52;
                                                                                                                                                        if (object3.statusCode() / 100 == 2) break block53;
                                                                                                                                                        return;
                                                                                                                                                    }
                                                                                                                                                    object3 = httpResponse.body();
                                                                                                                                                }
                                                                                                                                                jsonObject2 = JsonParser.parseString((String)((String)object3)).getAsJsonObject();
                                                                                                                                                jsonObject8 = jsonObject2;
                                                                                                                                                if (n != 0) break block54;
                                                                                                                                                Cosmetics.setDisabled(CosmeticsSync.idMap(jsonObject8.getAsJsonObject(b[116])));
                                                                                                                                                if (!jsonObject2.has(b[61])) break block55;
                                                                                                                                                break block73;
                                                                                                                                                catch (Exception exception) {
                                                                                                                                                    throw exception;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                            jsonObject8 = jsonObject2.getAsJsonObject(b[89]);
                                                                                                                                        }
                                                                                                                                        map2 = CosmeticsSync.idMap(jsonObject8);
                                                                                                                                        break block74;
                                                                                                                                    }
                                                                                                                                    map2 = Map.of();
                                                                                                                                }
                                                                                                                                Cosmetics.setLocked(map2);
                                                                                                                                jsonObject7 = jsonObject2;
                                                                                                                                if (n != 0) break block56;
                                                                                                                                if (!jsonObject7.has(b[143])) break block57;
                                                                                                                                break block75;
                                                                                                                                catch (Exception exception) {
                                                                                                                                    throw exception;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            jsonObject7 = jsonObject2.getAsJsonObject(b[121]);
                                                                                                                        }
                                                                                                                        map = CosmeticsSync.idMap(jsonObject7);
                                                                                                                        break block76;
                                                                                                                    }
                                                                                                                    map = Map.of();
                                                                                                                }
                                                                                                                Cosmetics.setPlusOnly(map);
                                                                                                                stringArray = b;
                                                                                                                if (!jsonObject2.has(stringArray[42])) break block58;
                                                                                                                hashSet = new HashSet<String>();
                                                                                                                for (JsonElement jsonElement2222 : jsonObject2.getAsJsonArray(stringArray[150])) {
                                                                                                                    if (n == 0) {
                                                                                                                        hashSet.add(jsonElement2222.getAsString());
                                                                                                                        if (n == 0) continue;
                                                                                                                        break;
                                                                                                                    }
                                                                                                                    break block58;
                                                                                                                }
                                                                                                                hashMap2 = new HashMap();
                                                                                                                stringArray = b;
                                                                                                                jsonElement2222 = jsonObject2.getAsJsonObject(stringArray[153]);
                                                                                                                for (HashMap<String, Map<String, Long>> hashMap42222 : jsonElement2222.keySet()) {
                                                                                                                    block59: {
                                                                                                                        Object object42;
                                                                                                                        object22222 = new HashMap();
                                                                                                                        if (n != 0) break block58;
                                                                                                                        for (Object object42 : jsonElement2222.getAsJsonObject((String)((Object)hashMap42222)).entrySet()) {
                                                                                                                            if (n == 0) {
                                                                                                                                Object object5 = (Map.Entry)object42;
                                                                                                                                object22222.put((String)object5.getKey(), ((JsonElement)object5.getValue()).getAsString());
                                                                                                                                if (n == 0) continue;
                                                                                                                            }
                                                                                                                            break block59;
                                                                                                                        }
                                                                                                                        object42 = hashMap2.put(hashMap42222, object22222);
                                                                                                                    }
                                                                                                                    if (n == 0) continue;
                                                                                                                }
                                                                                                                hashMap = new HashMap();
                                                                                                                stringArray = b;
                                                                                                                for (Object object22222 : jsonObject2.getAsJsonObject(stringArray[40]).entrySet()) {
                                                                                                                    hashMap.put((String)object22222.getKey(), ((JsonElement)object22222.getValue()).getAsInt());
                                                                                                                    if (n == 0) {
                                                                                                                        if (n == 0) continue;
                                                                                                                        break;
                                                                                                                    }
                                                                                                                    break block58;
                                                                                                                }
                                                                                                                hashMap42222 = new HashMap<String, Map<String, Long>>();
                                                                                                                jsonObject6 = jsonObject2;
                                                                                                                if (n != 0) break block60;
                                                                                                                if (!jsonObject6.has(b[66])) break block61;
                                                                                                                break block77;
                                                                                                                catch (Exception exception) {
                                                                                                                    throw exception;
                                                                                                                }
                                                                                                            }
                                                                                                            jsonObject6 = jsonObject2.getAsJsonObject(b[148]);
                                                                                                        }
                                                                                                        object22222 = jsonObject6;
                                                                                                        for (Object object5 : object22222.keySet()) {
                                                                                                            block63: {
                                                                                                                Object object6;
                                                                                                                HashMap<String, Long> hashMap5 = new HashMap<String, Long>();
                                                                                                                object = object22222;
                                                                                                                if (n != 0) break block62;
                                                                                                                Iterator iterator = object.getAsJsonObject((String)object5).entrySet().iterator();
                                                                                                                while (iterator.hasNext()) {
                                                                                                                    object6 = iterator;
                                                                                                                    if (n == 0) {
                                                                                                                        Map.Entry entry = (Map.Entry)object6.next();
                                                                                                                        hashMap5.put((String)entry.getKey(), ((JsonElement)entry.getValue()).getAsLong());
                                                                                                                        if (n == 0) continue;
                                                                                                                    }
                                                                                                                    break block63;
                                                                                                                }
                                                                                                                object6 = hashMap42222.put((String)object5, hashMap5);
                                                                                                            }
                                                                                                            if (n == 0) continue;
                                                                                                        }
                                                                                                    }
                                                                                                    hashMap3 = hashMap42222;
                                                                                                    jsonObject5 = jsonObject2;
                                                                                                    if (n != 0) break block64;
                                                                                                    if (!jsonObject5.has(b[117])) break block65;
                                                                                                    break block78;
                                                                                                    catch (Exception exception) {
                                                                                                        throw exception;
                                                                                                    }
                                                                                                }
                                                                                                jsonObject5 = jsonObject2.get(b[68]);
                                                                                            }
                                                                                            l2 = jsonObject5.getAsLong();
                                                                                            break block79;
                                                                                        }
                                                                                        l2 = 0L;
                                                                                    }
                                                                                    Cosmetics.setReleased(hashMap3, l2);
                                                                                    object = jsonObject2;
                                                                                }
                                                                                if (n != 0) break block80;
                                                                                if (!object.has(b[41])) break block66;
                                                                                break block81;
                                                                                catch (Exception exception) {
                                                                                    throw exception;
                                                                                }
                                                                            }
                                                                            object = jsonObject2;
                                                                        }
                                                                        stringArray = b;
                                                                        set2 = CosmeticsSync.stringSet(object.getAsJsonArray(stringArray[81]));
                                                                        break block82;
                                                                    }
                                                                    set2 = Set.of();
                                                                }
                                                                Cosmetics.setClosed(set2);
                                                                jsonObject4 = jsonObject2;
                                                                if (n != 0) break block67;
                                                                if (!jsonObject4.has(b[94])) break block68;
                                                                break block83;
                                                                catch (Exception exception) {
                                                                    throw exception;
                                                                }
                                                            }
                                                            jsonObject4 = jsonObject2.get(b[94]);
                                                        }
                                                        l = jsonObject4.getAsLong();
                                                        break block84;
                                                    }
                                                    l = 0L;
                                                }
                                                jsonObject3 = jsonObject2;
                                                if (n != 0) break block85;
                                                if (!jsonObject3.has(b[49])) break block69;
                                                break block86;
                                                catch (Exception exception) {
                                                    throw exception;
                                                }
                                            }
                                            jsonObject3 = jsonObject2;
                                        }
                                        stringArray = b;
                                        set = CosmeticsSync.stringSet(jsonObject3.getAsJsonArray(stringArray[49]));
                                        break block87;
                                    }
                                    set = Set.of();
                                }
                                jsonObject = jsonObject2;
                                string2 = b[48];
                                if (n != 0) break block70;
                                if (!jsonObject.has(string2)) break block71;
                                break block88;
                                catch (Exception exception) {
                                    throw exception;
                                }
                            }
                            jsonObject = jsonObject2;
                            string2 = b[114];
                        }
                        bl = jsonObject.get(string2).getAsBoolean();
                        if (n != 0) break block72;
                        if (!bl) break block71;
                        bl = true;
                        break block72;
                    }
                    bl = false;
                }
                Cosmetics.setShop(l, set, bl, hashSet, hashMap2, hashMap);
            }
            catch (Exception exception) {
                Aurora.LOG.debug(b[102], (Throwable)exception);
            }
        }
        CosmeticsSync.fetchContentNow();
    }

    private static void startEvents() {
        int n = ++eventsGeneration;
        Thread thread = new Thread(() -> CosmeticsSync.lambda$startEvents$4(n), b[53]);
        thread.setDaemon(true);
        thread.start();
    }

    
    private static void eventsLoop(int n) {
        int n2 = a;
        while (n == eventsGeneration) {
            block34: {
                if (token == null) return;
                if (!CosmeticsSync.enabled()) return;
                try {
                    Stream stream;
                    block33: {
                        String[] stringArray = b;
                        HttpResponse<Stream<String>> httpResponse = HTTP.send(CosmeticsSync.bearer(HttpRequest.newBuilder().uri(URI.create(baseUrl + stringArray[112])).GET()).build(), HttpResponse.BodyHandlers.ofLines());
                        Object object = httpResponse;
                        if (n2 == 0) {
                            if (object.statusCode() / 100 != 2) {
                                httpResponse.body().close();
                                if (n2 == 0) {
                                    if (httpResponse.statusCode() == 401) {
                                        if (n != eventsGeneration) return;
                                        token = null;
                                        status = b[16];
                                        return;
                                    }
                                    Thread.sleep(CosmeticsSync.a(19432, 1540354223756897336L));
                                }
                                if (n2 == 0) continue;
                            }
                            object = httpResponse.body();
                        }
                        Stream stream2 = (Stream)object;
                        try {
                            Iterator iterator;
                            Iterator iterator2 = iterator = stream2.iterator();
                            while (n == eventsGeneration) {
                                block36: {
                                    String string;
                                    block35: {
                                        String string2;
                                        Object t;
                                        iterator2 = iterator;
                                        if (n2 != 0) continue;
                                        if (n2 == 0) {
                                            if (!iterator2.hasNext()) break;
                                            t = iterator.next();
                                        }
                                        string = string2 = (String)t;
                                        if (n2 != 0) break block35;
                                        if (!string.startsWith(b[14])) break block36;
                                        string = string2.substring(6);
                                    }
                                    CosmeticsSync.applyEvent(JsonParser.parseString((String)string).getAsJsonObject());
                                }
                                if (n2 == 0) continue;
                            }
                            stream = stream2;
                            if (n2 != 0) break block33;
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
                        if (stream == null) break block34;
                        stream = stream2;
                    }
                    stream.close();
                }
                catch (InterruptedException interruptedException) {
                    return;
                }
                catch (Exception exception) {
                    Aurora.LOG.debug(b[134], (Throwable)exception);
                }
            }
            try {
                Thread.sleep(CosmeticsSync.a(9953, 2438953546819141944L));
            }
            catch (InterruptedException interruptedException) {
                return;
            }
        }
    }

    
    private static void applyAdminCommand(JsonObject var0) {
        block52: {
            block51: {
                block48: {
                    block50: {
                        block49: {
                            block47: {
                                block46: {
                                    var2_1 = MinecraftClient.method_1551();
                                    var1_2 = CosmeticsSync.a;
                                    v0 = var0;
                                    if (var1_2 != 0) // goto
                                    if (v0.has(CosmeticsSync.b[9])) {
                                        v0 = var0.get(CosmeticsSync.b[71]);
lbl7:

                                        v1 = v0.getAsString();
                                    } else {
                                        v1 = "";
                                    }
                                    var3_3 = v1;
                                    var4_4 = "";
                                    if (var1_2 != 0) break block46;
                                    if (CosmeticsSync.ownUuid == null) break block47;
                                    var4_4 = CosmeticsSync.ownUuid.toString().replace("-", "");
                                }
                                if (var1_2 == 0) break block48;
                            }
                            v2 = var2_1;
                            if (var1_2 != 0) break block49;
                            if (v2.method_1548() == null) break block48;
                            v2 = var2_1;
                        }
                        v3 = v2.method_1548();
                        if (var1_2 != 0) break block50;
                        if (v3.method_44717() == null) break block48;
                        v3 = var2_1.method_1548();
                    }
                    var4_4 = v3.method_44717().toString().replace("-", "");
                }
                v4 = CosmeticsSync.b[62];
                v5 = new Object[3];
                v5[0] = var3_3;
                v6 = v5;
                v7 = v5;
                v8 = 1;
                v9 = var4_4;
                if (var1_2 == 0) {
                    v6[v8] = v9;
                    v6 = v7;
                    v7 = v7;
                    v8 = 2;
                    v9 = var0.has(CosmeticsSync.b[18]) != false ? var0.get(CosmeticsSync.b[50]).getAsString() : "";
                }
                v6[v8] = v9;
                Aurora.LOG.info(v4, v7);
                v10 = var4_4;
                if (var1_2 == 0) {
                    if (v10.isEmpty() != false) return;
                    v10 = var4_4;
                }
                v11 = v10.equalsIgnoreCase(var3_3);
                if (var1_2 != 0) break block51;
                if (!v11) {
                    return;
                }
                v12 = var0;
                if (var1_2 != 0) // goto
                v11 = v12.has(CosmeticsSync.b[50]);
            }
            if (v11) {
                v12 = var0.get(CosmeticsSync.b[50]);
lbl59:

                v13 = v12.getAsString();
            } else {
                v13 = "";
            }
            var5_5 = v13;
            v14 = var0;
            if (var1_2 != 0) // goto
            if (v14.has(CosmeticsSync.b[11])) {
                v14 = var0.get(CosmeticsSync.b[132]);
lbl67:

                v15 = v14.getAsString();
            } else {
                var9_6 = CosmeticsSync.b;
                v15 = var9_6[54];
            }
            var6_7 = v15;
            var9_6 = CosmeticsSync.b;
            Aurora.LOG.info(var9_6[55], (Object)var5_5, (Object)var6_7);
            var7_8 = var5_5;
            var8_9 = -1;
            v16 = var7_8.hashCode();
            if (var1_2 != 0) break block52;
            switch (v16) {
                case 3482191: {
                    v16 = (int)var7_8.equals(CosmeticsSync.b[127]);
                    if (var1_2 == 0) {
                        if (v16 == 0) break;
                        var8_9 = 0;
                        if (var1_2 == 0) break;
                    }
                    break block52;
                }
                case 1085444827: {
                    v16 = (int)var7_8.equals(CosmeticsSync.b[67]);
                    if (var1_2 != 0) break block52;
                    if (v16 == 0) break;
                    var8_9 = 1;
                }
            }
            v16 = var8_9;
        }
        switch (v16) {
            case 0: {
                var2_1.execute((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$applyAdminCommand$5(net.minecraft.client.MinecraftClient java.lang.String ), ()V)((MinecraftClient)var2_1, (String)var6_7));
                if (var1_2 == 0) return;
            }
            case 1: {
                var2_1.execute((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$applyAdminCommand$6(), ()V)());
                return;
            }
        }
    }

    
    private static void applyEvent(JsonObject jsonObject) {
        block24: {
            int n = a;
            try {
                MinecraftClient minecraftClient;
                UUID uUID;
                block25: {
                    block29: {
                        String string;
                        block23: {
                            JsonObject jsonObject2;
                            block21: {
                                block22: {
                                    block28: {
                                        boolean bl;
                                        block20: {
                                            block18: {
                                                block19: {
                                                    block27: {
                                                        block26: {
                                                            jsonObject2 = jsonObject;
                                                            if (n != 0) break block18;
                                                            if (!jsonObject2.has(b[107])) break block19;
                                                            break block26;
                                                            catch (Exception exception) {
                                                                throw exception;
                                                            }
                                                        }
                                                        bl = b[104].equals(jsonObject.get(b[80]).getAsString());
                                                        if (n != 0) break block20;
                                                        break block27;
                                                        catch (Exception exception) {
                                                            throw exception;
                                                        }
                                                    }
                                                    if (!bl) break block19;
                                                    MinecraftClient.method_1551().execute(RemoteContent::markUnloaded);
                                                    return;
                                                }
                                                jsonObject2 = jsonObject;
                                            }
                                            if (n != 0) break block21;
                                            bl = jsonObject2.has(b[80]);
                                        }
                                        if (!bl) break block22;
                                        string = b[15];
                                        if (n != 0) break block23;
                                        break block28;
                                        catch (Exception exception) {
                                            throw exception;
                                        }
                                    }
                                    if (!string.equals(jsonObject.get(b[80]).getAsString())) break block22;
                                    CosmeticsSync.applyAdminCommand(jsonObject);
                                    return;
                                }
                                jsonObject2 = jsonObject.get(b[78]);
                            }
                            string = jsonObject2.getAsString();
                        }
                        uUID = UUID.fromString(string);
                        minecraftClient = MinecraftClient.method_1551();
                        if (n != 0) break block24;
                        if (minecraftClient.field_1724 == null) break block25;
                        break block29;
                        catch (Exception exception) {
                            throw exception;
                        }
                    }
                    if (!uUID.equals(minecraftClient.field_1724.method_5667())) break block25;
                    return;
                }
                minecraftClient.execute(() -> CosmeticsSync.lambda$applyEvent$7(uUID, jsonObject));
            }
            catch (Exception exception) {
            }
        }
    }

    private static String ev(JsonObject jsonObject, String string) {
        String string2;
        block6: {
            block5: {
                JsonObject jsonObject2;
                block4: {
                    int n = a;
                    jsonObject2 = jsonObject;
                    if (n != 0) break block4;
                    if (!jsonObject2.has(string)) break block5;
                    jsonObject2 = jsonObject.get(string);
                }
                string2 = jsonObject2.getAsString();
                break block6;
            }
            string2 = "";
        }
        return string2;
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

    private static long num(JsonObject jsonObject, String string) {
        long l;
        block11: {
            block9: {
                String string2;
                JsonObject jsonObject2;
                block10: {
                    int n;
                    block8: {
                        n = a;
                        jsonObject2 = jsonObject;
                        string2 = string;
                        if (n != 0) break block8;
                        if (!jsonObject2.has(string2)) break block9;
                        jsonObject2 = jsonObject;
                        string2 = string;
                    }
                    if (n != 0) break block10;
                    if (jsonObject2.get(string2).isJsonNull()) break block9;
                    jsonObject2 = jsonObject;
                    string2 = string;
                }
                l = jsonObject2.get(string2).getAsLong();
                break block11;
            }
            l = 0L;
        }
        return l;
    }

    public static String myVersion() {
        String string;
        block13: {
            int n;
            String string2;
            block12: {
                String string3;
                block11: {
                    string2 = Updater.selfSha();
                    n = a;
                    string3 = string2;
                    if (n != 0) break block11;
                    if (!string3.isEmpty()) break block12;
                    string3 = "";
                }
                return string3;
            }
            for (CosmeticsSync$Release cosmeticsSync$Release : releases) {
                block15: {
                    String string4;
                    block14: {
                        string = string2;
                        if (n != 0) break block13;
                        if (n != 0) break block14;
                        if (!string.equals(cosmeticsSync$Release.sha())) break block15;
                        string4 = cosmeticsSync$Release.version();
                    }
                    return string4;
                }
                if (n == 0) continue;
            }
            string = "";
        }
        return string;
    }

    public static List<CosmeticsSync$Release> releases() {
        return releases;
    }

    public static void fetchReleases() {
        CosmeticsSync.run(CosmeticsSync::lambda$fetchReleases$8);
    }

    
    private static void fetchContentNow() {
        block27: {
            int n = a;
            try {
                JsonObject jsonObject;
                String[] stringArray;
                block28: {
                    boolean bl;
                    JsonObject jsonObject2;
                    block26: {
                        block25: {
                            JsonObject jsonObject3;
                            block24: {
                                block29: {
                                    Object object;
                                    block23: {
                                        int n2;
                                        HttpResponse<String> httpResponse;
                                        block21: {
                                            block22: {
                                                String string;
                                                block19: {
                                                    Object object2;
                                                    block20: {
                                                        stringArray = b;
                                                        object2 = stringArray[2];
                                                        string = token;
                                                        if (n != 0) break block19;
                                                        if (string == null) break block20;
                                                        stringArray = b;
                                                        object2 = (String)object2 + stringArray[25] + MachineId.get();
                                                    }
                                                    string = object2;
                                                }
                                                httpResponse = CosmeticsSync.get(string, contentTag);
                                                n2 = httpResponse.statusCode();
                                                if (n != 0) break block21;
                                                if (n2 != 304) break block22;
                                                MinecraftClient.method_1551().execute(RemoteContent::markLoaded);
                                                return;
                                            }
                                            object = httpResponse;
                                            if (n != 0) break block23;
                                            n2 = object.statusCode() / 100;
                                        }
                                        if (n2 != 2) {
                                            return;
                                        }
                                        stringArray = b;
                                        httpResponse.headers().firstValue(stringArray[17]).ifPresent(CosmeticsSync::lambda$fetchContentNow$9);
                                        object = httpResponse.body();
                                    }
                                    jsonObject2 = JsonParser.parseString((String)((String)object)).getAsJsonObject();
                                    jsonObject3 = jsonObject2;
                                    if (n != 0) break block24;
                                    if (!jsonObject3.has(b[91])) break block25;
                                    break block29;
                                    catch (Exception exception) {
                                        throw exception;
                                    }
                                }
                                jsonObject3 = jsonObject2.get(b[64]);
                            }
                            bl = jsonObject3.getAsBoolean();
                            if (n != 0) break block26;
                            if (!bl) break block25;
                            bl = true;
                            break block26;
                        }
                        bl = false;
                    }
                    boolean bl2 = bl;
                    Cosmetics.setCreator(bl2);
                    stringArray = b;
                    jsonObject = jsonObject2.getAsJsonObject(stringArray[126]);
                    if (n != 0) break block27;
                    if (jsonObject != null) break block28;
                    return;
                }
                MinecraftClient.method_1551().execute(() -> CosmeticsSync.lambda$fetchContentNow$10(jsonObject));
                try {
                    Files.createDirectories(CosmeticsSync.contentCache().getParent(), new FileAttribute[0]);
                    Files.writeString(CosmeticsSync.contentCache(), (CharSequence)jsonObject.toString(), new OpenOption[0]);
                }
                catch (Exception exception) {
                    stringArray = b;
                    Aurora.LOG.debug(stringArray[86], (Throwable)exception);
                }
            }
            catch (Exception exception) {
                Aurora.LOG.debug(b[144], (Throwable)exception);
            }
        }
    }

    private static Path contentCache() {
        return FabricLoader.getInstance().getGameDir().resolve(b[45]);
    }

    public static void loadCachedContent() {
        CosmeticsSync.run(CosmeticsSync::lambda$loadCachedContent$13);
    }

    private static Set<String> stringSet(JsonArray jsonArray) {
        HashSet<String> hashSet;
        block4: {
            HashSet<String> hashSet2 = new HashSet<String>();
            Iterator iterator = jsonArray.iterator();
            int n = a;
            while (iterator.hasNext()) {
                JsonElement jsonElement = (JsonElement)iterator.next();
                hashSet = hashSet2;
                if (n == 0) {
                    hashSet.add(jsonElement.getAsString());
                    if (n == 0) continue;
                    break;
                }
                break block4;
            }
            hashSet = hashSet2;
        }
        return hashSet;
    }

    public static void buy(String string, String string2, String string3, int n, Consumer<String> consumer) {
        String string4;
        block4: {
            block5: {
                int n2 = a;
                string4 = token;
                if (n2 != 0) break block4;
                if (string4 != null) break block5;
                consumer.accept(b[60]);
                return;
            }
            string4 = string;
        }
        CosmeticsSync.run(() -> CosmeticsSync.lambda$buy$15(string4, string2, string3, n, consumer));
    }

    public static void fetch(String string, Consumer<JsonObject> consumer) {
        String string2;
        block4: {
            block5: {
                int n = a;
                string2 = token;
                if (n != 0) break block4;
                if (string2 != null) break block5;
                consumer.accept(null);
                return;
            }
            string2 = string;
        }
        CosmeticsSync.run(() -> CosmeticsSync.lambda$fetch$17(string2, consumer));
    }

    public static void claim(String string, Consumer<String> consumer) {
        String string2;
        block4: {
            block5: {
                int n = a;
                string2 = token;
                if (n != 0) break block4;
                if (string2 != null) break block5;
                consumer.accept(b[109]);
                return;
            }
            string2 = string;
        }
        CosmeticsSync.run(() -> CosmeticsSync.lambda$claim$19(string2, consumer));
    }

    private static Map<String, Set<String>> idMap(JsonObject jsonObject) {
        HashMap<String, Set<String>> hashMap;
        block8: {
            JsonObject jsonObject2;
            int n;
            block7: {
                hashMap = new HashMap<String, Set<String>>();
                n = a;
                jsonObject2 = jsonObject;
                if (n != 0) break block7;
                if (jsonObject2 == null) break block8;
                jsonObject2 = jsonObject;
            }
            for (String string : jsonObject2.keySet()) {
                block9: {
                    HashSet<String> hashSet;
                    HashSet hashSet2 = new HashSet();
                    for (JsonElement jsonElement : jsonObject.getAsJsonArray(string)) {
                        hashSet = hashSet2;
                        if (n == 0) {
                            hashSet.add(jsonElement.getAsString());
                            if (n == 0) continue;
                            break;
                        }
                        break block9;
                    }
                    hashSet = hashMap.put(string, hashSet2);
                }
                if (n == 0) continue;
            }
        }
        return hashMap;
    }

    
    private static void poll() {
        block107: {
            var0 = CosmeticsSync.a;
            try {
                block105: {
                    block106: {
                        block102: {
                            block101: {
                                block100: {
                                    block99: {
                                        block141: {
                                            var1_1 = MinecraftClient.method_1551();
                                            CosmeticsSync.heartbeat();
                                            var2_3 = new LinkedHashSet<String>();
                                            if (CosmeticsSync.ownUuid != null) {
                                                var2_3.add(CosmeticsSync.ownUuid.toString());
                                            }
                                            var3_4 = Social.knownUuids().iterator();
                                            while (var3_4.hasNext()) {
                                                v1 = var3_4.next();
                                                if (var0 == 0) {
                                                    var4_5 = v1;
                                                    var2_3.add(var4_5.toString());
                                                    if (var0 == 0) continue;
                                                }
                                            }
                                            v2 = var1_1;
                                            if (var0 != 0) break block99;
                                            if (v2.method_1562() == null) break block100;
                                            break block141;
                                            catch (IllegalArgumentException v3) {
                                                throw v3;
                                            }
                                        }
                                        v2 = var1_1;
                                    }
                                    for (UUID v1 : v2.method_1562().method_2880()) lbl_neg_1000:

                                    {
                                        var4_5 = (PlayerListEntry)v1;
                                        var2_3.add(var4_5.method_2966().id().toString());
                                        if (var0 == 0) {
                                            if (var0 == 0) continue;
                                            break;
                                        }
                                        break block101;
                                    }
                                }
                                if (var2_3.isEmpty()) {
                                    return;
                                }
                            }
                            var3_4 = new StringBuilder();
                            var4_6 = 0;
                            for (Object v6 : var2_3) {
                                block103: {
                                    block104: {
                                        block142: {
                                            if (var0 != 0) break block102;
                                            var6_8 = v6;
                                            if (var0 != 0) break block103;
                                            if (var4_6++ <= 0) break block104;
                                            break block142;
                                            catch (IllegalArgumentException v7) {
                                                throw v7;
                                            }
                                        }
                                        var3_4.append(',');
                                    }
                                    var3_4.append(var6_8);
                                }
                                if (var4_6 >= 200) {
                                    break;
                                }
                                if (var0 == 0) continue;
                            }
                            var15_9 = CosmeticsSync.b;
                            v6 = var15_9[129] + String.valueOf(var3_4);
                        }
                        var5_7 = CosmeticsSync.get((String)v6);
                        v10 = var5_7;
                        if (var0 != 0) break block105;
                        if (v10.statusCode() / 100 == 2) break block106;
                        return;
                    }
                    v10 = var5_7.body();
                }
                var15_9 = CosmeticsSync.b;
                var6_8 = JsonParser.parseString((String)((String)v10)).getAsJsonObject().getAsJsonObject(var15_9[21]);
                var7_10 = new HashMap<UUID, String[]>();
                for (String var9_12 : var6_8.keySet()) {
                    block114: {
                        block113: {
                            block112: {
                                block147: {
                                    block146: {
                                        block111: {
                                            block110: {
                                                block145: {
                                                    block144: {
                                                        block109: {
                                                            block108: {
                                                                block143: {
                                                                    if (var0 != 0) break block107;
                                                                    var10_13 = var6_8.getAsJsonObject(var9_12);
                                                                    v12 = var10_13;
                                                                    if (var0 != 0) break block108;
                                                                    if (!v12.has(CosmeticsSync.b[139])) break block109;
                                                                    break block143;
                                                                    catch (IllegalArgumentException v13) {
                                                                        throw v13;
                                                                    }
                                                                }
                                                                v12 = var10_13.get(CosmeticsSync.b[139]);
                                                            }
                                                            v15 = v12.getAsString();
                                                            break block144;
                                                        }
                                                        v15 = "";
                                                    }
                                                    var11_14 = v15;
                                                    v16 = var10_13;
                                                    if (var0 != 0) break block110;
                                                    if (!v16.has(CosmeticsSync.b[122])) break block111;
                                                    break block145;
                                                    catch (IllegalArgumentException v17) {
                                                        throw v17;
                                                    }
                                                }
                                                v16 = var10_13.get(CosmeticsSync.b[122]);
                                            }
                                            v19 = v16.getAsString();
                                            break block146;
                                        }
                                        v19 = "";
                                    }
                                    var12_15 = v19;
                                    v20 = var10_13;
                                    if (var0 != 0) break block112;
                                    if (!v20.has(CosmeticsSync.b[47])) break block113;
                                    break block147;
                                    catch (IllegalArgumentException v21) {
                                        throw v21;
                                    }
                                }
                                v20 = var10_13.get(CosmeticsSync.b[119]);
                            }
                            v23 = v20.getAsBoolean();
                            if (var0 != 0) break block114;
                            if (!v23) break block113;
                            v23 = true;
                            break block114;
                        }
                        v23 = false;
                    }
                    var13_16 = v23;
                    try {
                        block140: {
                            block139: {
                                block138: {
                                    block169: {
                                        block168: {
                                            block136: {
                                                block137: {
                                                    block135: {
                                                        block167: {
                                                            block166: {
                                                                block165: {
                                                                    block134: {
                                                                        block133: {
                                                                            block164: {
                                                                                block163: {
                                                                                    block132: {
                                                                                        block131: {
                                                                                            block162: {
                                                                                                block129: {
                                                                                                    block130: {
                                                                                                        block161: {
                                                                                                            block160: {
                                                                                                                block128: {
                                                                                                                    block127: {
                                                                                                                        block159: {
                                                                                                                            block158: {
                                                                                                                                block126: {
                                                                                                                                    block125: {
                                                                                                                                        block157: {
                                                                                                                                            block156: {
                                                                                                                                                block124: {
                                                                                                                                                    block123: {
                                                                                                                                                        block155: {
                                                                                                                                                            block154: {
                                                                                                                                                                block122: {
                                                                                                                                                                    block121: {
                                                                                                                                                                        block153: {
                                                                                                                                                                            block152: {
                                                                                                                                                                                block120: {
                                                                                                                                                                                    block119: {
                                                                                                                                                                                        block151: {
                                                                                                                                                                                            block150: {
                                                                                                                                                                                                block118: {
                                                                                                                                                                                                    block117: {
                                                                                                                                                                                                        block149: {
                                                                                                                                                                                                            block115: {
                                                                                                                                                                                                                block116: {
                                                                                                                                                                                                                    block148: {
                                                                                                                                                                                                                        var14_17 = UUID.fromString(var9_12);
                                                                                                                                                                                                                        v25 = var7_10;
                                                                                                                                                                                                                        v26 = var14_17;
                                                                                                                                                                                                                        v27 = new String[15];
                                                                                                                                                                                                                        v28 = v27;
                                                                                                                                                                                                                        v29 = v27;
                                                                                                                                                                                                                        v30 = 0;
                                                                                                                                                                                                                        v31 = CosmeticsSync.resolveRemoteCape(var11_14, var12_15);
                                                                                                                                                                                                                        if (var0 != 0) break block115;
                                                                                                                                                                                                                        v28[v30] = v31;
                                                                                                                                                                                                                        v28 = v29;
                                                                                                                                                                                                                        v29 = v29;
                                                                                                                                                                                                                        v30 = 1;
                                                                                                                                                                                                                        if (!var10_13.has(CosmeticsSync.b[74])) break block116;
                                                                                                                                                                                                                        break block148;
                                                                                                                                                                                                                        catch (IllegalArgumentException v32) {
                                                                                                                                                                                                                            throw v32;
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    v31 = var10_13.get(CosmeticsSync.b[74]).getAsString();
                                                                                                                                                                                                                    break block115;
                                                                                                                                                                                                                }
                                                                                                                                                                                                                v31 = "";
                                                                                                                                                                                                            }
                                                                                                                                                                                                            v28[v30] = v31;
                                                                                                                                                                                                            v34 = v29;
                                                                                                                                                                                                            v35 = v29;
                                                                                                                                                                                                            v36 = 2;
                                                                                                                                                                                                            v37 = var13_16 != false ? "1" : "";
                                                                                                                                                                                                            v34[v36] = v37;
                                                                                                                                                                                                            v39 = v35;
                                                                                                                                                                                                            v40 = v35;
                                                                                                                                                                                                            v41 = 3;
                                                                                                                                                                                                            v42 = var10_13;
                                                                                                                                                                                                            if (var0 != 0) break block117;
                                                                                                                                                                                                            if (!v42.has(CosmeticsSync.b[103])) break block118;
                                                                                                                                                                                                            break block149;
                                                                                                                                                                                                            catch (IllegalArgumentException v43) {
                                                                                                                                                                                                                throw v43;
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                        v42 = var10_13.get(CosmeticsSync.b[103]);
                                                                                                                                                                                                    }
                                                                                                                                                                                                    v45 = v42.getAsString();
                                                                                                                                                                                                    break block150;
                                                                                                                                                                                                }
                                                                                                                                                                                                v45 = "";
                                                                                                                                                                                            }
                                                                                                                                                                                            v39[v41] = v45;
                                                                                                                                                                                            v46 = v40;
                                                                                                                                                                                            v47 = v40;
                                                                                                                                                                                            v48 = 4;
                                                                                                                                                                                            v49 = var10_13;
                                                                                                                                                                                            if (var0 != 0) break block119;
                                                                                                                                                                                            if (!v49.has(CosmeticsSync.b[58])) break block120;
                                                                                                                                                                                            break block151;
                                                                                                                                                                                            catch (IllegalArgumentException v50) {
                                                                                                                                                                                                throw v50;
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                        v49 = var10_13.get(CosmeticsSync.b[58]);
                                                                                                                                                                                    }
                                                                                                                                                                                    v52 = v49.getAsString();
                                                                                                                                                                                    break block152;
                                                                                                                                                                                }
                                                                                                                                                                                v52 = "";
                                                                                                                                                                            }
                                                                                                                                                                            v46[v48] = v52;
                                                                                                                                                                            v53 = v47;
                                                                                                                                                                            v54 = v47;
                                                                                                                                                                            v55 = 5;
                                                                                                                                                                            v56 = var10_13;
                                                                                                                                                                            if (var0 != 0) break block121;
                                                                                                                                                                            if (!v56.has(CosmeticsSync.b[0])) break block122;
                                                                                                                                                                            break block153;
                                                                                                                                                                            catch (IllegalArgumentException v57) {
                                                                                                                                                                                throw v57;
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                        v56 = var10_13.get(CosmeticsSync.b[0]);
                                                                                                                                                                    }
                                                                                                                                                                    v59 = v56.getAsString();
                                                                                                                                                                    break block154;
                                                                                                                                                                }
                                                                                                                                                                v59 = "";
                                                                                                                                                            }
                                                                                                                                                            v53[v55] = v59;
                                                                                                                                                            v60 = v54;
                                                                                                                                                            v61 = v54;
                                                                                                                                                            v62 = 6;
                                                                                                                                                            v63 = var10_13;
                                                                                                                                                            if (var0 != 0) break block123;
                                                                                                                                                            if (!v63.has(CosmeticsSync.b[100])) break block124;
                                                                                                                                                            break block155;
                                                                                                                                                            catch (IllegalArgumentException v64) {
                                                                                                                                                                throw v64;
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                        v63 = var10_13.get(CosmeticsSync.b[24]);
                                                                                                                                                    }
                                                                                                                                                    v66 = String.valueOf(v63.getAsLong());
                                                                                                                                                    break block156;
                                                                                                                                                }
                                                                                                                                                v66 = "0";
                                                                                                                                            }
                                                                                                                                            v60[v62] = v66;
                                                                                                                                            v67 = v61;
                                                                                                                                            v68 = v61;
                                                                                                                                            v69 = 7;
                                                                                                                                            v70 = var10_13;
                                                                                                                                            if (var0 != 0) break block125;
                                                                                                                                            if (!v70.has(CosmeticsSync.b[51])) break block126;
                                                                                                                                            break block157;
                                                                                                                                            catch (IllegalArgumentException v71) {
                                                                                                                                                throw v71;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        v70 = var10_13.get(CosmeticsSync.b[36]);
                                                                                                                                    }
                                                                                                                                    v73 = String.valueOf(v70.getAsLong());
                                                                                                                                    break block158;
                                                                                                                                }
                                                                                                                                v73 = "0";
                                                                                                                            }
                                                                                                                            v67[v69] = v73;
                                                                                                                            v74 = v68;
                                                                                                                            v75 = v68;
                                                                                                                            v76 = 8;
                                                                                                                            v77 = var10_13;
                                                                                                                            if (var0 != 0) break block127;
                                                                                                                            if (!v77.has(CosmeticsSync.b[32])) break block128;
                                                                                                                            break block159;
                                                                                                                            catch (IllegalArgumentException v78) {
                                                                                                                                throw v78;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        v77 = var10_13.get(CosmeticsSync.b[32]);
                                                                                                                    }
                                                                                                                    v80 = v77.getAsString();
                                                                                                                    break block160;
                                                                                                                }
                                                                                                                v80 = "";
                                                                                                            }
                                                                                                            v74[v76] = v80;
                                                                                                            v81 = v75;
                                                                                                            v82 = v75;
                                                                                                            v83 = 9;
                                                                                                            v84 = "";
                                                                                                            if (var0 != 0) break block129;
                                                                                                            v81[v83] = v84;
                                                                                                            v81 = v82;
                                                                                                            v82 = v82;
                                                                                                            v83 = 10;
                                                                                                            if (!var10_13.has(CosmeticsSync.b[39])) break block130;
                                                                                                            break block161;
                                                                                                            catch (IllegalArgumentException v85) {
                                                                                                                throw v85;
                                                                                                            }
                                                                                                        }
                                                                                                        v84 = var10_13.get(CosmeticsSync.b[39]).getAsString();
                                                                                                        break block129;
                                                                                                    }
                                                                                                    v84 = "";
                                                                                                }
                                                                                                v81[v83] = v84;
                                                                                                v87 = v82;
                                                                                                v88 = v82;
                                                                                                v89 = 11;
                                                                                                v90 = var10_13;
                                                                                                if (var0 != 0) break block131;
                                                                                                if (!v90.has(CosmeticsSync.b[28])) break block132;
                                                                                                break block162;
                                                                                                catch (IllegalArgumentException v91) {
                                                                                                    throw v91;
                                                                                                }
                                                                                            }
                                                                                            v90 = var10_13.get(CosmeticsSync.b[56]);
                                                                                        }
                                                                                        v93 = String.valueOf(v90.getAsLong());
                                                                                        break block163;
                                                                                    }
                                                                                    v93 = "0";
                                                                                }
                                                                                v87[v89] = v93;
                                                                                v94 = v88;
                                                                                v95 = v88;
                                                                                v96 = 12;
                                                                                v97 = var10_13;
                                                                                if (var0 != 0) break block133;
                                                                                if (!v97.has(CosmeticsSync.b[141])) break block134;
                                                                                break block164;
                                                                                catch (IllegalArgumentException v98) {
                                                                                    throw v98;
                                                                                }
                                                                            }
                                                                            v97 = var10_13.get(CosmeticsSync.b[19]);
                                                                        }
                                                                        v100 = String.valueOf(v97.getAsInt());
                                                                        break block165;
                                                                    }
                                                                    v100 = "0";
                                                                }
                                                                v94[v96] = v100;
                                                                v101 = v95;
                                                                v102 = v95;
                                                                v103 = 13;
                                                                v104 = var10_13;
                                                                if (var0 != 0) break block135;
                                                                if (!v104.has(CosmeticsSync.b[140])) break block136;
                                                                break block166;
                                                                catch (IllegalArgumentException v105) {
                                                                    throw v105;
                                                                }
                                                            }
                                                            v106 = var10_13;
                                                            if (var0 != 0) break block137;
                                                            break block167;
                                                            catch (IllegalArgumentException v107) {
                                                                throw v107;
                                                            }
                                                        }
                                                        v104 = v106.get(CosmeticsSync.b[29]);
                                                    }
                                                    if (v104.isJsonNull()) break block136;
                                                    v106 = var10_13.get(CosmeticsSync.b[29]);
                                                }
                                                v110 = v106.getAsString();
                                                break block168;
                                            }
                                            v110 = "";
                                        }
                                        v101[v103] = v110;
                                        v111 = v102;
                                        v112 = v102;
                                        v113 = 14;
                                        v114 = var10_13;
                                        v115 = CosmeticsSync.b[106];
                                        if (var0 != 0) break block138;
                                        if (!v114.has(v115)) break block139;
                                        break block169;
                                        catch (IllegalArgumentException v116) {
                                            throw v116;
                                        }
                                    }
                                    v114 = var10_13;
                                    v115 = CosmeticsSync.b[125];
                                }
                                if (!v114.get(v115).getAsBoolean()) break block139;
                                v118 = "1";
                                break block140;
                            }
                            v118 = "";
                        }
                        v111[v113] = v118;
                        v25.put(v26, v112);
                    }
                    catch (IllegalArgumentException var14_18) {
                    }
                    if (var0 == 0) continue;
                }
                Cosmetics.clearRemote();
                var7_10.forEach((BiConsumer<UUID, String[]>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, lambda$poll$20(java.util.UUID java.lang.String[] ), (Ljava/util/UUID;[Ljava/lang/String;)V)());
            }
            catch (Exception var1_2) {
                Aurora.LOG.debug(CosmeticsSync.b[96], (Throwable)var1_2);
            }
        }
    }

    private static String resolveRemoteCape(String string, String string2) {
        String string3;
        block22: {
            block23: {
                String string4;
                int n;
                block20: {
                    block21: {
                        String string5;
                        block18: {
                            block19: {
                                block16: {
                                    block17: {
                                        n = a;
                                        string5 = b[65];
                                        if (n != 0) break block16;
                                        if (!string5.equals(string)) break block17;
                                        return b[35];
                                    }
                                    string5 = string;
                                }
                                if (n != 0) break block18;
                                if (string5 == null) break block19;
                                string4 = string;
                                if (n != 0) break block20;
                                if (!string4.isBlank()) break block21;
                            }
                            string5 = "";
                        }
                        return string5;
                    }
                    string4 = CapeManager.key(string);
                }
                String string6 = string4;
                string3 = string6;
                if (n != 0) break block22;
                if (!CosmeticRegistry.contains(string3)) break block23;
                string3 = string6;
                break block22;
            }
            string3 = "";
        }
        return string3;
    }

    public static HttpRequest.Builder bearer(HttpRequest.Builder builder) {
        block6: {
            String string;
            String string2;
            block5: {
                string2 = token;
                int n = a;
                string = string2;
                if (n != 0) break block5;
                if (string == null) break block6;
                string = string2;
            }
            if (!string.isEmpty()) {
                builder.header(b[69], b[151] + string2);
            }
        }
        return builder;
    }

    private static String relative(String string) {
        String string2;
        block4: {
            block5: {
                int n = a;
                string2 = string;
                if (n != 0) break block4;
                if (!string2.startsWith(baseUrl)) break block5;
                string2 = string.substring(baseUrl.length());
                break block4;
            }
            string2 = string;
        }
        return string2;
    }

    private static HttpResponse<String> get(String string) throws Exception {
        return CosmeticsSync.get(string, null);
    }

    private static HttpResponse<String> get(String string, String string2) throws Exception {
        HttpRequest.Builder builder;
        block12: {
            String string3;
            block11: {
                int n;
                block9: {
                    block10: {
                        builder = HttpRequest.newBuilder(URI.create(baseUrl + string)).timeout(Duration.ofSeconds(CosmeticsSync.a(24281, 415598823664056579L))).GET();
                        n = a;
                        string3 = token;
                        if (n != 0) break block9;
                        if (string3 == null) break block10;
                        builder.header(b[85], b[130] + token);
                    }
                    string3 = string2;
                }
                if (n != 0) break block11;
                if (string3 == null) break block12;
                string3 = string2;
            }
            if (!string3.isEmpty()) {
                builder.header(b[27], string2);
            }
        }
        return HTTP.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    private static JsonObject authed() {
        String string;
        String string2;
        JsonObject jsonObject;
        JsonObject jsonObject2;
        block2: {
            block3: {
                jsonObject2 = new JsonObject();
                int n = a;
                jsonObject = jsonObject2;
                string2 = b[23];
                string = token;
                if (n != 0) break block2;
                if (string != null) break block3;
                string = "";
                break block2;
            }
            string = token;
        }
        jsonObject.addProperty(string2, string);
        jsonObject2.addProperty(b[87], MachineId.get());
        return jsonObject2;
    }

    private static HttpResponse<String> post(String string, String string2) throws Exception {
        String[] stringArray = b;
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(baseUrl + string)).timeout(Duration.ofSeconds(CosmeticsSync.a(15255, 6077791793880112195L))).header(stringArray[152], stringArray[111]).POST(HttpRequest.BodyPublishers.ofString(string2, StandardCharsets.UTF_8)).build();
        return HTTP.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private static synchronized MinecraftSessionService sessionService() {
        MinecraftSessionService minecraftSessionService;
        block4: {
            block5: {
                int n = a;
                minecraftSessionService = sessionService;
                if (n != 0) break block4;
                if (minecraftSessionService != null) break block5;
                sessionService = new YggdrasilAuthenticationService(Proxy.NO_PROXY).createMinecraftSessionService();
            }
            minecraftSessionService = sessionService;
        }
        return minecraftSessionService;
    }

    private static String sha1Hex(String string) throws Exception {
        byte[] byArray = MessageDigest.getInstance(b[43]).digest(string.getBytes(StandardCharsets.UTF_8));
        return new BigInteger(byArray).toString(16);
    }

    private static void run(Runnable runnable) {
        Thread thread = new Thread(runnable, b[105]);
        thread.setDaemon(true);
        thread.start();
    }

    
    private static void lambda$poll$20(UUID uUID, String[] stringArray) {
        String[] stringArray2;
        int n2;
        block22: {
            int n;
            block21: {
                n2 = a;
                String string = stringArray[0];
                boolean bl = stringArray[2].isEmpty();
                if (n2 == 0) {
                    bl = !bl;
                }
                Cosmetics.setRemote(uUID, string, bl);
                Cosmetics.setRemoteWings(uUID, stringArray[1]);
                Cosmetics.setRemoteTrail(uUID, stringArray[3]);
                Cosmetics.setRemoteKillEffect(uUID, stringArray[4]);
                n = stringArray.length;
                if (n2 != 0) break block21;
                if (n > 10) {
                    Cosmetics.setRemoteEmote(uUID, stringArray[10]);
                }
                stringArray2 = stringArray;
                if (n2 != 0) break block22;
                n = stringArray2.length;
            }
            if (n > 12) {
                try {
                    Cosmetics.setRemoteFirstSeen(uUID, Long.parseLong(stringArray[11]));
                    Cosmetics.setRemoteFriends(uUID, Integer.parseInt(stringArray[12]));
                }
                catch (NumberFormatException numberFormatException) {
                }
            }
            stringArray2 = stringArray;
        }
        int n = stringArray2.length;
        if (n2 == 0) {
            if (n > 13) {
                Cosmetics.setRemoteRole(uUID, stringArray[13]);
            }
            n = stringArray.length;
        }
        if (n > 14) {
            boolean bl = stringArray[14].isEmpty();
            if (n2 == 0) {
                bl = !bl;
            }
            Cosmetics.setRemotePlus(uUID, bl);
        }
        MaceEffects.setRemote(uUID, stringArray[5]);
        Cosmetics.setRemoteHitEffect(uUID, stringArray[8]);
        try {
            Cosmetics.setRemotePlaytime(uUID, Long.parseLong(stringArray[6]));
            Cosmetics.setRemoteOnlineFor(uUID, Long.parseLong(stringArray[7]));
            return;
        }
        catch (NumberFormatException numberFormatException) {
        }
    }

    
    private static void lambda$claim$19(String var0, Consumer var1_1) {
        block12: {
            var2_2 = CosmeticsSync.a;
            try {
                block16: {
                    block14: {
                        block13: {
                            block11: {
                                block15: {
                                    var4_3 /* !! */  = new JsonObject();
                                    var7_5 = CosmeticsSync.b;
                                    var4_3 /* !! */ .addProperty(var7_5[23], CosmeticsSync.token);
                                    var4_3 /* !! */ .addProperty(var7_5[147], var0);
                                    var5_6 = CosmeticsSync.post(var7_5[95], var4_3 /* !! */ .toString());
                                    var6_7 = JsonParser.parseString((String)var5_6.body()).getAsJsonObject();
                                    v0 = var5_6.statusCode();
                                    if (var2_2 != 0) break block11;
                                    if (v0 / 100 == 2) {
                                    }
                                    var7_5 = CosmeticsSync.b;
                                    Cosmetics.setShards(var6_7.get(var7_5[94]).getAsLong());
                                    var3_8 = null;
                                    if (var2_2 == 0) break block12;
lbl23:

                                    v2 = var6_7;
                                    if (var2_2 != 0) break block13;
                                    break block15;
                                    catch (Exception v3) {
                                        throw v3;
                                    }
                                }
                                v0 = (int)v2.has(CosmeticsSync.b[1]);
                            }
                            if (v0 == 0) break block14;
                            v2 = var6_7.get(CosmeticsSync.b[1]);
                        }
                        v6 = v2.getAsString();
                        break block16;
                    }
                    var7_5 = CosmeticsSync.b;
                    v6 = var7_5[115];
                }
                var3_8 = v6;
            }
            catch (Exception var4_4) {
                var3_8 = CosmeticsSync.b[63];
            }
        }
        var4_3 /* !! */  = var3_8;
        MinecraftClient.method_1551().execute((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$claim$18(java.util.function.Consumer java.lang.String ), ()V)((Consumer)var1_1, (String)var4_3 /* !! */ ));
    }

    private static void lambda$claim$18(Consumer consumer, String string) {
        consumer.accept(string);
    }

    
    private static void lambda$fetch$17(String string, Consumer consumer) {
        JsonObject jsonObject;
        JsonObject jsonObject2;
        block6: {
            jsonObject2 = null;
            int n = a;
            try {
                Object object = jsonObject = CosmeticsSync.get(string);
                if (n == 0) {
                    if (object.statusCode() / 100 != 2) break block6;
                    object = jsonObject.body();
                }
                jsonObject2 = JsonParser.parseString((String)((String)object)).getAsJsonObject();
            }
            catch (Exception exception) {
                Aurora.LOG.debug(b[46], (Object)string, (Object)exception);
            }
        }
        jsonObject = jsonObject2;
        MinecraftClient.method_1551().execute(() -> CosmeticsSync.lambda$fetch$16(consumer, jsonObject));
    }

    private static void lambda$fetch$16(Consumer consumer, JsonObject jsonObject) {
        consumer.accept(jsonObject);
    }

    
    private static void lambda$buy$15(String var0, String var1_1, String var2_2, int var3_3, Consumer var4_4) {
        block12: {
            var5_5 = CosmeticsSync.a;
            try {
                block16: {
                    block14: {
                        block13: {
                            block11: {
                                block15: {
                                    var7_6 /* !! */  = new JsonObject();
                                    var10_8 = CosmeticsSync.b;
                                    var7_6 /* !! */ .addProperty(var10_8[23], CosmeticsSync.token);
                                    var7_6 /* !! */ .addProperty(var10_8[128], var0);
                                    var7_6 /* !! */ .addProperty(var10_8[12], var1_1);
                                    var7_6 /* !! */ .addProperty(var10_8[72], var2_2);
                                    var7_6 /* !! */ .addProperty(var10_8[131], (Number)var3_3);
                                    var8_9 = CosmeticsSync.post(var10_8[34], var7_6 /* !! */ .toString());
                                    var9_10 = JsonParser.parseString((String)var8_9.body()).getAsJsonObject();
                                    v0 = var8_9.statusCode();
                                    if (var5_5 != 0) break block11;
                                    if (v0 / 100 == 2) {
                                    }
                                    var10_8 = CosmeticsSync.b;
                                    Cosmetics.setShards(var9_10.get(var10_8[30]).getAsLong());
                                    Cosmetics.setOwned(CosmeticsSync.stringSet(var9_10.getAsJsonArray(var10_8[93])));
                                    var6_11 = null;
                                    if (var5_5 == 0) break block12;
lbl27:

                                    v2 = var9_10;
                                    if (var5_5 != 0) break block13;
                                    break block15;
                                    catch (Exception v3) {
                                        throw v3;
                                    }
                                }
                                v0 = (int)v2.has(CosmeticsSync.b[13]);
                            }
                            if (v0 == 0) break block14;
                            v2 = var9_10.get(CosmeticsSync.b[1]);
                        }
                        v6 = v2.getAsString();
                        break block16;
                    }
                    var10_8 = CosmeticsSync.b;
                    v6 = var10_8[88];
                }
                var6_11 = v6;
            }
            catch (Exception var7_7) {
                var6_11 = CosmeticsSync.b[118];
            }
        }
        var7_6 /* !! */  = var6_11;
        MinecraftClient.method_1551().execute((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$buy$14(java.util.function.Consumer java.lang.String ), ()V)((Consumer)var4_4, (String)var7_6 /* !! */ ));
    }

    private static void lambda$buy$14(Consumer consumer, String string) {
        consumer.accept(string);
    }

    
    private static void lambda$loadCachedContent$13() {
        block6: {
            int n = a;
            try {
                Path path = CosmeticsSync.contentCache();
                if (n == 0) {
                    if (!Files.isRegularFile(path, new LinkOption[0])) break block6;
                    path = CosmeticsSync.contentCache();
                }
                JsonObject jsonObject = JsonParser.parseString((String)Files.readString(path)).getAsJsonObject();
                MinecraftClient.method_1551().execute(() -> CosmeticsSync.lambda$loadCachedContent$11(jsonObject));
            }
            catch (Exception exception) {
                Aurora.LOG.debug(b[99], (Throwable)exception);
            }
        }
        CosmeticsSync.fetchContentNow();
        MinecraftClient.method_1551().execute(CosmeticsSync::lambda$loadCachedContent$12);
    }

    private static void lambda$loadCachedContent$12() {
        contentTried = true;
    }

    private static void lambda$loadCachedContent$11(JsonObject jsonObject) {
        if (RemoteContent.loaded()) {
            return;
        }
        RemoteContent.apply(jsonObject);
        RemoteContent.markUnloaded();
    }

    private static void lambda$fetchContentNow$10(JsonObject jsonObject) {
        RemoteContent.apply(jsonObject);
    }

    private static void lambda$fetchContentNow$9(String string) {
        contentTag = string;
    }

    private static void lambda$fetchReleases$8() {
        int n = a;
        try {
            ArrayList<CosmeticsSync$Release> arrayList;
            block10: {
                Object object;
                String[] stringArray;
                block8: {
                    HttpResponse<String> httpResponse;
                    block9: {
                        stringArray = b;
                        httpResponse = CosmeticsSync.get(stringArray[133]);
                        object = httpResponse;
                        if (n != 0) break block8;
                        if (object.statusCode() / 100 == 2) break block9;
                        return;
                    }
                    object = httpResponse.body();
                }
                stringArray = b;
                JsonArray jsonArray = JsonParser.parseString((String)((String)object)).getAsJsonObject().getAsJsonArray(stringArray[120]);
                ArrayList<CosmeticsSync$Release> arrayList2 = new ArrayList<CosmeticsSync$Release>();
                for (JsonElement jsonElement : jsonArray) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    arrayList = arrayList2;
                    if (n == 0) {
                        arrayList.add(new CosmeticsSync$Release(CosmeticsSync.str(jsonObject, b[97]), CosmeticsSync.str(jsonObject, b[137]), CosmeticsSync.num(jsonObject, b[57]), CosmeticsSync.str(jsonObject, b[59])));
                        if (n == 0) continue;
                        break;
                    }
                    break block10;
                }
                arrayList = arrayList2;
            }
            releases = arrayList;
        }
        catch (Exception exception) {
            Aurora.LOG.debug(b[33], (Throwable)exception);
        }
    }

    private static void lambda$applyEvent$7(UUID uUID, JsonObject jsonObject) {
        String[] stringArray = b;
        Cosmetics.setRemoteEmote(uUID, CosmeticsSync.ev(jsonObject, stringArray[39]));
        Cosmetics.setRemoteWings(uUID, CosmeticsSync.ev(jsonObject, stringArray[74]));
        Cosmetics.setRemoteTrail(uUID, CosmeticsSync.ev(jsonObject, stringArray[103]));
        Cosmetics.setRemoteKillEffect(uUID, CosmeticsSync.ev(jsonObject, stringArray[58]));
        MaceEffects.setRemote(uUID, CosmeticsSync.ev(jsonObject, stringArray[0]));
        Cosmetics.setRemoteHitEffect(uUID, CosmeticsSync.ev(jsonObject, stringArray[32]));
    }

    private static void lambda$applyAdminCommand$6() {
        CosmeticsSync.reset();
        RemoteContent.markUnloaded();
    }

    private static void lambda$applyAdminCommand$5(MinecraftClient minecraftClient, String string) {
        String[] stringArray = b;
        SystemToast.method_1990((ToastManager)minecraftClient.method_1566(), (SystemToast.Type)SystemToast.Type.field_47588, (Text)Text.method_43470((String)stringArray[138]), (Text)Text.method_43470((String)(string + stringArray[73])));
    }

    private static void lambda$startEvents$4(int n) {
        CosmeticsSync.eventsLoop(n);
    }

    private static void lambda$adoptOwn$3(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
        block4: {
            block5: {
                int n = a;
                Cosmetics.setOwnCape(string);
                if (n != 0) break block4;
                if (string2.isBlank()) break block5;
                Cosmetics.setOwnCapeUrl(string2);
            }
            Cosmetics.setOwnWings(string3);
            Cosmetics.setOwnTrail(string4);
            Cosmetics.setOwnKillEffect(string5);
            Cosmetics.setOwnMaceEffect(string6);
            Cosmetics.setOwnHitEffect(string7);
            Cosmetics.setOwnEmote("");
        }
    }

    private static Thread lambda$static$2(Runnable runnable) {
        Thread thread = new Thread(runnable, b[31]);
        thread.setDaemon(true);
        return thread;
    }

    private static void lambda$tick$1(String string) {
        CosmeticsSync.adoptOwn(string);
    }

    private static void lambda$tick$0(String string) {
        CosmeticsSync.push(string);
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[154];
                        var11_1 = 0;
                        var10_2 = "\u001c\u0011\u0006Q5Jq\u0014\u0013\u0011\u0006\u0015\u0015\u0011U\u0019@\u0016^\u0011\u0015]_Ox\u0002\u001d\u0000@\u0019Od^\u0013\nZ\u0004Iy\u0005'0\u0013\u0006[\u0005BcQ\u0013\rU\u001eKr\u0015\\EG\u0019Ky\u0018\u001e\u0002\u0014\u0019B7\u0010\u0017\u0004]\u001e\fv\u0002P\u001eI\u0007\u0001\u001c\u0004M\u0015^d\u0006\u0002\u0015\u0017B\u0015^\u00152\u001f\u0016Y\u0015X~\u0012\u0003EU\u0005X\u007fQ\u0016\u0004]\u001cIs\t\u0013\u0005\fX\u0014bv\u001c\u0015\u0003\u0001\u0015\u0011\u0006\u0005\u0011\u0017S\u0015X\u00152\u001f\u0016Y\u0015X~\u0012\u0003ED\u0005_\u007fQ\u0016\u0004]\u001cIs\u0002\u0013\t\u0002\u0018\u0014\u0006\u0015\u0015\u0011U\u0019@\u0006\u0015\u0011\u0011UJ\f\u0005\u0010\u0014\b]\u001e\u0011#\u0015HU\u0005X\u007f\u0014\u001e\u0011]\u0013Mc\u0018\u001e\u0002\u0004\u0014\u0004\u0004S\u0006\u0010\u0013\u0011]\u001fB\u0007\u0017\u0002\fQ\u001eHd\n\u001a\u0019\tX5Jq\u0014\u0013\u0011\u0007\u0001\u001c\u0004M\u0015^d\r0\u0005\u0011\\PJv\u0018\u001c\u0000PP\u0004\u0005\u0005\u001f\u000eQ\u001e\b\u0001\u001c\u0004M\u0004Ez\u0014\tN\u001d\u0004W\u0018Ey\u0014M\t2\u001f\u000bZ\u0015Oc\u0014\u0014\r8\u0016Hz\u001fBr\\=\u0004@\u0013D\t\u0017\u0019\u0017G\u0004\u007fr\u0014\u001e\u0004\u0003\u001f\tQ\u0006\u0002\u0018\u0004F\u0014_\u00140\u0005\u0017[\u0002M:2\u001f\u0016Y\u0015X~\u0012\u00035A\u0003D\t\u0019\u0019\u0011q\u0016Jr\u0012\u0004\u0015#\u0015\tQ\u0011_r\u0002P\u0003Q\u0004O\u007fQ\u0016\u0004]\u001cIs\u000e^\u0011\u0015]__c\u001e\u0002\u0000\u001b\u0012Yn\u0006\\\u001e\nZ\u0015\u0001\t\u001e\u001e\t]\u001eIQ\u001e\u0002\b\u0002\u0015\u0017B\u0015^^\u0015\u0004*,Gi\u0005\u0014\u001d\n@\u0015\u0006\u0001\u0002\fW\u0015_\u0006\u0012\u001c\nG\u0015H\u0004\u0002\u001f\tP\u0005\"8$\u0019A\u0005\u0014\u001d\n@\u0015\u0019\u0010\u0005\u0017[\u0002M8\u0012\u0011\u0006\\\u0015\u0003t\u001e\u001e\u0011Q\u001eX9\u001b\u0003\nZ\u000f7\u0015\u0011W\u0018\fl\fP\u0003U\u0019@r\u0015\u0006\u0010\u0005\u0017[\u0002M\u0007\u001e\u0007\u000bG1@{\u0005\u001e\u0007\u000bQ\u0014\u0006\u0010\u0013\u0011]\u001fB\t\u001e\u001e\t]\u001eIQ\u001e\u0002\u0007>\u0016\u0003X\u0019Br\r\u0010\u0005\u0017[\u0002M:\u0014\u0006\u0000Z\u0004_\b\u0010\u001eEU\u0014A~\u001f\"0\u0005\u0017[\u0002M-Q\u0011\u0001Y\u0019B7\u0012\u001f\bY\u0011BsQW\u001eIW\fq\u0003\u001f\b\u0014\u000bQ\t\u0017\u0019\u0017G\u0004\u007fr\u0014\u001e\u0002\u0010\u0004\n\u001a\u0019\tX5Jq\u0014\u0013\u0011\u0006\u0002\u0018\u0004\u0006E\u001a\u0017?\u001f\u0011\u0014\u0003Ep\u001f\u0015\u0001\u0014\u0019B7\u0005\u001fEu\u0005^x\u0003\u0011\u0006\u001d\u001f\u0006_\u0015H/0\u0005\u0017[\u0002M-Q\u0011\u0001Y\u0019B7\u0014\u0006\u0000Z\u0004\fc\u0010\u0002\u0002Q\u0004\u0011l\fP\b]\u001eI*\n\rEU\u0013X~\u001e\u001eXO\r\f2\u001c\u0004]\u001d\fq\u0010\u0019\tQ\u0014\u0007\u0012\u0002\u0000U\u0004Ce\u0006\\\u001e\nZ\u0015\u0001\b\u0003\u0015\tQ\u0011_r\u0015\u0007\u0003\u0015\u0003F\u0015_\u007f\u0006\u001f\u0015\u0012r\u001f^\r0\u0005\u0011\\\u001f^~\u000b\u0011\u0011]\u001fB\t\u0019\u0019\u0011q\u0016Jr\u0012\u0004\u0006\u0005\u0011\u0017S\u0015X\u0006\u0013\u0005\u000bP\u001cI\u001cQ\u0011\u0016_\u0015H7\b\u001f\u0010\u0014\u0004C7\u0012\u001c\nG\u0015\fc\u0019\u0015ES\u0011Ar\u0005\u0006\u0019\u000bS\u0003\u0007\u0012\u0011\u0015Q%^{\u0003\u0019\u0011\u0011\u0007>\u0016\u0003X\u0019Br\u0004\u0004\u0005\fP\u0007\u0007\u0015\u0017G\u0019Cy\u0004\u0005\t\u0015Q\u0006\u0012\u001c\nG\u0015H\u0004\u0012\u0011\u0015Q\n0\u0005\u0011\\PIe\u0003\u001f\u0017\b\u0004\u0003\u0000F\u001eMz\u0014\r0\u0005\u0011\\\u001f^~\u000b\u0011\u0011]\u001fB\u001b2\u001f\u0010X\u0014\fy\u001e\u0004EW\u0011O\u007f\u0014P\u0011\\\u0015\ft\u001e\u001e\u0011Q\u001eX\u0007\u001c\u0011\u0006\\\u0019Br\u000f!\u0005\u0017W\u0018Md\u0014P\u0003U\u0019@r\u0015\u0006\u001d\u001f\u0006_\u0015H\u0012^\u0011\u0015]_Ox\u0002\u001d\u0000@\u0019Od^\u0003\u0000@\u0007\u0012\u0002\u0000U\u0004Ce\u0015^\u0011\u0015]_Ox\u0002\u001d\u0000@\u0019OdN\u0005\u0010]\u0014_*\u0005\u001e\u0007\u000bQ\u0014\u0006\u0002\u0018\u0004F\u0014_\u0012^\u0011\u0015]_^r\u0006\u0011\u0017P\u0003\u0003t\u001d\u0011\fY\u00152\u001f\u0016Y\u0015X~\u0012\u0003ED\u001f@{Q\u0016\u0004]\u001cIs\u0007\u0007\u0015\u0017G\u0019Cy\t^\u0011\u0015]_Mb\u0005\u0018!2\u001f\u0010X\u0014\fy\u001e\u0004EF\u0015MsQ\u0004\rQPOv\u0012\u0018\u0000PPOx\u001f\u0004\u0000Z\u0004\b\u0001\u001c\u0004M\u0004Ez\u0014\u001c2\u001f\u0010X\u0014\fy\u001e\u0004EX\u001fMsQ\u001f\u0012ZPOx\u0002\u001d\u0000@\u0019Od\u0015<\u0011\u000b]\u0016Id\u0005P\u0003Q\u0004O\u007fQ\u0016\u0004]\u001cIs\u0005\u0005\u0002\u0004]\u001c\u0007\u0012\u001f\u000b@\u0015Bc\u00100\u0005\u0017[\u0002M:2\u001f\u0016Y\u0015X~\u0012\u0003\u0004\u0001\u001c\u0010G\u0004\u0005\t\u0015Q\u0005\u0006\u0019\u000bS\u0003\u0017?\u001f\u0011\u0014\u0003Ep\u001f\u0015\u0001\u0014\u0019B7\u0005\u001fEu\u0005^x\u0003\u0011\n\u001c\u0011\u0006Q5Jq\u0014\u0013\u0011\u0010\u0010\u0000\u0015X\u0019Ov\u0005\u0019\nZ_Fd\u001e\u001e\u000b^\u0011\u0015]_Ia\u0014\u001e\u0011G\n?\u001f\u0011\u0014\u001cEy\u001a\u0015\u0001\u0007\u001e\u0007\u000bG1@{\f2\u001c\u0004]\u001d\fq\u0010\u0019\tQ\u0014\b\u0015\u0019\u0016U\u0012@r\u0015\u0006\u001f\u0015\u0012r\u001f^\u000f!\u0005\u0017W\u0018Md\u0014P\u0003U\u0019@r\u0015\u0006\u0010\u0005\u0017[\u0002M\b\u0003\u0015\tQ\u0011_r\u0002\b\u0001\u001c\u0010G?B{\b\u0007\u0012\u0011\u0015Q%^{\u0005\u0005\u0002\u0004]\u001c\u0006\u0018\u001e\"U\u001dI\u0004\u0001\u001c\u0010G\u0007\u0012\u001f\u000b@\u0015Bc\u0004\u0000\u0005\f@\u0003\u0012\u0011\u0011\u0015^\u0011\u0015]_Ox\u0002\u001d\u0000@\u0019OdN\u0005\u0010]\u0014_*\u00073\u0015\u0004F\u0015^7\u0005\u0001\u0002\fW\u0015\u0002\u0013\t\u0014^\u0011\u0015]_O{\u0018\u0015\u000b@_^r\u001d\u0015\u0004G\u0015_\u00144\u0006\u0000Z\u0004\fd\u0005\u0002\u0000U\u001d\fs\u0003\u001f\u0015D\u0015H\u001d^\u0011\u0015]_Ox\u0002\u001d\u0000@\u0019Od^\u001d\u0004Z\u0019Jr\u0002\u0004ZA\u0005EsL\u0004\u0004\u0005\fP\u0005\u001f\u001f\u0011Q\u0003\u00060\u0005\u0017[\u0002M\u0004\u0012\u0011\u0015Q\u0004\u0003\u001f\tQ\u0007\u0017\u0002\fQ\u001eHd\u0005\u0005\u001f\u000eQ\u001e\b\u0001\u001c\u0010G?B{\b\u00142\u001f\u000b@\u0015BcQ\u0016\u0000@\u0013D7\u0017\u0011\fX\u0015H\u0003^[A\u0011#\u0015HU\u0005X\u007f\u0014\u001e\u0011]\u0013Mc\u0018\u001e\u0002\u0002\u0018\u0014\b\u0003\u0015\tQ\u0011_r\u0015\u000e^\u0011\u0015]_Dr\u0010\u0002\u0011V\u0015Mc\u0004\u0002\u001f\tP\u00073\u0015\u0004F\u0015^7";
                        var12_3 = "\u001c\u0011\u0006Q5Jq\u0014\u0013\u0011\u0006\u0015\u0015\u0011U\u0019@\u0016^\u0011\u0015]_Ox\u0002\u001d\u0000@\u0019Od^\u0013\nZ\u0004Iy\u0005'0\u0013\u0006[\u0005BcQ\u0013\rU\u001eKr\u0015\\EG\u0019Ky\u0018\u001e\u0002\u0014\u0019B7\u0010\u0017\u0004]\u001e\fv\u0002P\u001eI\u0007\u0001\u001c\u0004M\u0015^d\u0006\u0002\u0015\u0017B\u0015^\u00152\u001f\u0016Y\u0015X~\u0012\u0003EU\u0005X\u007fQ\u0016\u0004]\u001cIs\t\u0013\u0005\fX\u0014bv\u001c\u0015\u0003\u0001\u0015\u0011\u0006\u0005\u0011\u0017S\u0015X\u00152\u001f\u0016Y\u0015X~\u0012\u0003ED\u0005_\u007fQ\u0016\u0004]\u001cIs\u0002\u0013\t\u0002\u0018\u0014\u0006\u0015\u0015\u0011U\u0019@\u0006\u0015\u0011\u0011UJ\f\u0005\u0010\u0014\b]\u001e\u0011#\u0015HU\u0005X\u007f\u0014\u001e\u0011]\u0013Mc\u0018\u001e\u0002\u0004\u0014\u0004\u0004S\u0006\u0010\u0013\u0011]\u001fB\u0007\u0017\u0002\fQ\u001eHd\n\u001a\u0019\tX5Jq\u0014\u0013\u0011\u0007\u0001\u001c\u0004M\u0015^d\r0\u0005\u0011\\PJv\u0018\u001c\u0000PP\u0004\u0005\u0005\u001f\u000eQ\u001e\b\u0001\u001c\u0004M\u0004Ez\u0014\tN\u001d\u0004W\u0018Ey\u0014M\t2\u001f\u000bZ\u0015Oc\u0014\u0014\r8\u0016Hz\u001fBr\\=\u0004@\u0013D\t\u0017\u0019\u0017G\u0004\u007fr\u0014\u001e\u0004\u0003\u001f\tQ\u0006\u0002\u0018\u0004F\u0014_\u00140\u0005\u0017[\u0002M:2\u001f\u0016Y\u0015X~\u0012\u00035A\u0003D\t\u0019\u0019\u0011q\u0016Jr\u0012\u0004\u0015#\u0015\tQ\u0011_r\u0002P\u0003Q\u0004O\u007fQ\u0016\u0004]\u001cIs\u000e^\u0011\u0015]__c\u001e\u0002\u0000\u001b\u0012Yn\u0006\\\u001e\nZ\u0015\u0001\t\u001e\u001e\t]\u001eIQ\u001e\u0002\b\u0002\u0015\u0017B\u0015^^\u0015\u0004*,Gi\u0005\u0014\u001d\n@\u0015\u0006\u0001\u0002\fW\u0015_\u0006\u0012\u001c\nG\u0015H\u0004\u0002\u001f\tP\u0005\"8$\u0019A\u0005\u0014\u001d\n@\u0015\u0019\u0010\u0005\u0017[\u0002M8\u0012\u0011\u0006\\\u0015\u0003t\u001e\u001e\u0011Q\u001eX9\u001b\u0003\nZ\u000f7\u0015\u0011W\u0018\fl\fP\u0003U\u0019@r\u0015\u0006\u0010\u0005\u0017[\u0002M\u0007\u001e\u0007\u000bG1@{\u0005\u001e\u0007\u000bQ\u0014\u0006\u0010\u0013\u0011]\u001fB\t\u001e\u001e\t]\u001eIQ\u001e\u0002\u0007>\u0016\u0003X\u0019Br\r\u0010\u0005\u0017[\u0002M:\u0014\u0006\u0000Z\u0004_\b\u0010\u001eEU\u0014A~\u001f\"0\u0005\u0017[\u0002M-Q\u0011\u0001Y\u0019B7\u0012\u001f\bY\u0011BsQW\u001eIW\fq\u0003\u001f\b\u0014\u000bQ\t\u0017\u0019\u0017G\u0004\u007fr\u0014\u001e\u0002\u0010\u0004\n\u001a\u0019\tX5Jq\u0014\u0013\u0011\u0006\u0002\u0018\u0004\u0006E\u001a\u0017?\u001f\u0011\u0014\u0003Ep\u001f\u0015\u0001\u0014\u0019B7\u0005\u001fEu\u0005^x\u0003\u0011\u0006\u001d\u001f\u0006_\u0015H/0\u0005\u0017[\u0002M-Q\u0011\u0001Y\u0019B7\u0014\u0006\u0000Z\u0004\fc\u0010\u0002\u0002Q\u0004\u0011l\fP\b]\u001eI*\n\rEU\u0013X~\u001e\u001eXO\r\f2\u001c\u0004]\u001d\fq\u0010\u0019\tQ\u0014\u0007\u0012\u0002\u0000U\u0004Ce\u0006\\\u001e\nZ\u0015\u0001\b\u0003\u0015\tQ\u0011_r\u0015\u0007\u0003\u0015\u0003F\u0015_\u007f\u0006\u001f\u0015\u0012r\u001f^\r0\u0005\u0011\\\u001f^~\u000b\u0011\u0011]\u001fB\t\u0019\u0019\u0011q\u0016Jr\u0012\u0004\u0006\u0005\u0011\u0017S\u0015X\u0006\u0013\u0005\u000bP\u001cI\u001cQ\u0011\u0016_\u0015H7\b\u001f\u0010\u0014\u0004C7\u0012\u001c\nG\u0015\fc\u0019\u0015ES\u0011Ar\u0005\u0006\u0019\u000bS\u0003\u0007\u0012\u0011\u0015Q%^{\u0003\u0019\u0011\u0011\u0007>\u0016\u0003X\u0019Br\u0004\u0004\u0005\fP\u0007\u0007\u0015\u0017G\u0019Cy\u0004\u0005\t\u0015Q\u0006\u0012\u001c\nG\u0015H\u0004\u0012\u0011\u0015Q\n0\u0005\u0011\\PIe\u0003\u001f\u0017\b\u0004\u0003\u0000F\u001eMz\u0014\r0\u0005\u0011\\\u001f^~\u000b\u0011\u0011]\u001fB\u001b2\u001f\u0010X\u0014\fy\u001e\u0004EW\u0011O\u007f\u0014P\u0011\\\u0015\ft\u001e\u001e\u0011Q\u001eX\u0007\u001c\u0011\u0006\\\u0019Br\u000f!\u0005\u0017W\u0018Md\u0014P\u0003U\u0019@r\u0015\u0006\u001d\u001f\u0006_\u0015H\u0012^\u0011\u0015]_Ox\u0002\u001d\u0000@\u0019Od^\u0003\u0000@\u0007\u0012\u0002\u0000U\u0004Ce\u0015^\u0011\u0015]_Ox\u0002\u001d\u0000@\u0019OdN\u0005\u0010]\u0014_*\u0005\u001e\u0007\u000bQ\u0014\u0006\u0002\u0018\u0004F\u0014_\u0012^\u0011\u0015]_^r\u0006\u0011\u0017P\u0003\u0003t\u001d\u0011\fY\u00152\u001f\u0016Y\u0015X~\u0012\u0003ED\u001f@{Q\u0016\u0004]\u001cIs\u0007\u0007\u0015\u0017G\u0019Cy\t^\u0011\u0015]_Mb\u0005\u0018!2\u001f\u0010X\u0014\fy\u001e\u0004EF\u0015MsQ\u0004\rQPOv\u0012\u0018\u0000PPOx\u001f\u0004\u0000Z\u0004\b\u0001\u001c\u0004M\u0004Ez\u0014\u001c2\u001f\u0010X\u0014\fy\u001e\u0004EX\u001fMsQ\u001f\u0012ZPOx\u0002\u001d\u0000@\u0019Od\u0015<\u0011\u000b]\u0016Id\u0005P\u0003Q\u0004O\u007fQ\u0016\u0004]\u001cIs\u0005\u0005\u0002\u0004]\u001c\u0007\u0012\u001f\u000b@\u0015Bc\u00100\u0005\u0017[\u0002M:2\u001f\u0016Y\u0015X~\u0012\u0003\u0004\u0001\u001c\u0010G\u0004\u0005\t\u0015Q\u0005\u0006\u0019\u000bS\u0003\u0017?\u001f\u0011\u0014\u0003Ep\u001f\u0015\u0001\u0014\u0019B7\u0005\u001fEu\u0005^x\u0003\u0011\n\u001c\u0011\u0006Q5Jq\u0014\u0013\u0011\u0010\u0010\u0000\u0015X\u0019Ov\u0005\u0019\nZ_Fd\u001e\u001e\u000b^\u0011\u0015]_Ia\u0014\u001e\u0011G\n?\u001f\u0011\u0014\u001cEy\u001a\u0015\u0001\u0007\u001e\u0007\u000bG1@{\f2\u001c\u0004]\u001d\fq\u0010\u0019\tQ\u0014\b\u0015\u0019\u0016U\u0012@r\u0015\u0006\u001f\u0015\u0012r\u001f^\u000f!\u0005\u0017W\u0018Md\u0014P\u0003U\u0019@r\u0015\u0006\u0010\u0005\u0017[\u0002M\b\u0003\u0015\tQ\u0011_r\u0002\b\u0001\u001c\u0010G?B{\b\u0007\u0012\u0011\u0015Q%^{\u0005\u0005\u0002\u0004]\u001c\u0006\u0018\u001e\"U\u001dI\u0004\u0001\u001c\u0010G\u0007\u0012\u001f\u000b@\u0015Bc\u0004\u0000\u0005\f@\u0003\u0012\u0011\u0011\u0015^\u0011\u0015]_Ox\u0002\u001d\u0000@\u0019OdN\u0005\u0010]\u0014_*\u00073\u0015\u0004F\u0015^7\u0005\u0001\u0002\fW\u0015\u0002\u0013\t\u0014^\u0011\u0015]_O{\u0018\u0015\u000b@_^r\u001d\u0015\u0004G\u0015_\u00144\u0006\u0000Z\u0004\fd\u0005\u0002\u0000U\u001d\fs\u0003\u001f\u0015D\u0015H\u001d^\u0011\u0015]_Ox\u0002\u001d\u0000@\u0019Od^\u001d\u0004Z\u0019Jr\u0002\u0004ZA\u0005EsL\u0004\u0004\u0005\fP\u0005\u001f\u001f\u0011Q\u0003\u00060\u0005\u0017[\u0002M\u0004\u0012\u0011\u0015Q\u0004\u0003\u001f\tQ\u0007\u0017\u0002\fQ\u001eHd\u0005\u0005\u001f\u000eQ\u001e\b\u0001\u001c\u0010G?B{\b\u00142\u001f\u000b@\u0015BcQ\u0016\u0000@\u0013D7\u0017\u0011\fX\u0015H\u0003^[A\u0011#\u0015HU\u0005X\u007f\u0014\u001e\u0011]\u0013Mc\u0018\u001e\u0002\u0002\u0018\u0014\b\u0003\u0015\tQ\u0011_r\u0015\u000e^\u0011\u0015]_Dr\u0010\u0002\u0011V\u0015Mc\u0004\u0002\u001f\tP\u00073\u0015\u0004F\u0015^7".length();
                        var9_4 = 10;
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
                            var10_2 = "2\u001f\u000b@\u0015Bc\\$\u001cD\u0015\u0006\u0003\u0011\u0017]\u0004U";
                            var12_3 = "2\u001f\u000b@\u0015Bc\\$\u001cD\u0015\u0006\u0003\u0011\u0017]\u0004U".length();
                            var9_4 = 12;
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
                                v10 = 113;
                                break;
                            }
                            case 1: {
                                v10 = 112;
                                break;
                            }
                            case 2: {
                                v10 = 101;
                                break;
                            }
                            case 3: {
                                v10 = 52;
                                break;
                            }
                            case 4: {
                                v10 = 112;
                                break;
                            }
                            case 5: {
                                v10 = 44;
                                break;
                            }
                            default: {
                                v10 = 23;
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
                CosmeticsSync.b = var13;
                var0_7 = 6877877784420875542L;
                var6_8 = new long[12];
                var3_9 = 0;
                var4_10 = "5\\U\u00cc\u00bcD\u0086SJ\u0013Qf\u00db\u00b9\u0082\u00a6x\u00ce\u00fc\u009f\u00d5-\u00f2\u0084\u000b\u00d1w\u0085\u00c5\u00ef\u00baG\u0016\u00f0\u0095\u00b3Z\u008f\b.\u000b+\u0086\b\u00d9\u00f6\u00e1Z!\u00a0\u008f\u0082\u00a4\u00c0\u00abrj\u00dc\u00b8\u0014\u0092\u00a1N\u001c~\u00ab\u00c63\u00a1!w\u0096V\u00e8 \u00aa\u009d\u0081\u00ae\u00ae";
                var5_11 = "5\\U\u00cc\u00bcD\u0086SJ\u0013Qf\u00db\u00b9\u0082\u00a6x\u00ce\u00fc\u009f\u00d5-\u00f2\u0084\u000b\u00d1w\u0085\u00c5\u00ef\u00baG\u0016\u00f0\u0095\u00b3Z\u008f\b.\u000b+\u0086\b\u00d9\u00f6\u00e1Z!\u00a0\u008f\u0082\u00a4\u00c0\u00abrj\u00dc\u00b8\u0014\u0092\u00a1N\u001c~\u00ab\u00c63\u00a1!w\u0096V\u00e8 \u00aa\u009d\u0081\u00ae\u00ae".length();
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
                    var4_10 = "[\u00ba\u00f7TR\u00e1\u009e\u00f3Z\u00b7\u00a1\u008a\u0019d\u0004\u001a";
                    var5_11 = "[\u00ba\u00f7TR\u00e1\u009e\u00f3Z\u00b7\u00a1\u008a\u0019d\u0004\u001a".length();
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
        CosmeticsSync.c = var6_8;
        CosmeticsSync.d = new Long[12];
        CosmeticsSync.POLL_MS = CosmeticsSync.a(32422, 345042364593953149L);
        CosmeticsSync.AUTH_RETRY_MS = CosmeticsSync.a(14477, 5297276413076512600L);
        CosmeticsSync.baseUrl = "";
        CosmeticsSync.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(CosmeticsSync.a(5334, 3868479324509549312L))).followRedirects(HttpClient.Redirect.NORMAL).build();
        CosmeticsSync.status = CosmeticsSync.b[77];
        CosmeticsSync.adopting = true;
        CosmeticsSync.PUSHES = Executors.newSingleThreadExecutor((ThreadFactory)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Runnable;)Ljava/lang/Thread;, lambda$static$2(java.lang.Runnable ), (Ljava/lang/Runnable;)Ljava/lang/Thread;)());
        CosmeticsSync.releases = List.of();
        CosmeticsSync.contentTag = "";
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x27D1) & Short.MAX_VALUE;
        if (d[n2] == null) {
            CosmeticsSync.d[n2] = c[n2] ^ l;
        }
        return d[n2];
    }
}
