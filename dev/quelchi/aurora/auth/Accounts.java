package dev.quelchi.aurora.auth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.authlib.yggdrasil.ProfileResult;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.auth.Accounts$Saved;
import dev.quelchi.aurora.auth.MsAuth;
import dev.quelchi.aurora.auth.MsAuth$Account;
import dev.quelchi.aurora.auth.SecretStore;
import dev.quelchi.aurora.cosmetics.backend.AccountCapes;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.gui.PreviewPlayer;
import dev.quelchi.aurora.mixins.ProfileKeysAccessor;
import dev.quelchi.aurora.mixins.SessionAccessor;
import dev.quelchi.aurora.social.Badges;
import dev.quelchi.aurora.social.Plus;
import dev.quelchi.aurora.social.Social;
import dev.quelchi.aurora.util.SkinFetcher;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Base64;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.ProfileKeys;
import net.minecraft.client.session.Session;
import net.minecraft.util.Util;

public final class Accounts {
    private static final Gson GSON;
    private static final Path PATH;
    private static final List<Accounts$Saved> ACCOUNTS;
    private static volatile String status;
    private static volatile boolean statusFailed;
    private static boolean saveBlocked;
    private static final String SAVE_BLOCKED;
    private static final String[] a;
    private static final long b;

    private Accounts() {
    }

    public static synchronized List<Accounts$Saved> all() {
        return List.copyOf(ACCOUNTS);
    }

    public static String status() {
        return status;
    }

    public static boolean statusFailed() {
        return statusFailed;
    }

    public static void setStatus(String string) {
        String string2;
        block2: {
            block3: {
                int n = MsAuth.a;
                string2 = string;
                if (n != 0) break block2;
                if (string2 != null) break block3;
                string2 = "";
                break block2;
            }
            string2 = string;
        }
        status = string2;
        statusFailed = false;
    }

    public static void setError(String string) {
        String string2;
        block2: {
            block3: {
                int n = MsAuth.a;
                string2 = string;
                if (n != 0) break block2;
                if (string2 != null) break block3;
                string2 = "";
                break block2;
            }
            string2 = string;
        }
        status = string2;
        statusFailed = true;
    }

    public static UUID current() {
        UUID uUID;
        block6: {
            MinecraftClient minecraftClient;
            block4: {
                MinecraftClient minecraftClient2;
                block5: {
                    minecraftClient2 = MinecraftClient.method_1551();
                    int n = MsAuth.a;
                    minecraftClient = minecraftClient2;
                    if (n != 0) break block4;
                    if (minecraftClient.method_1548() != null) break block5;
                    uUID = null;
                    break block6;
                }
                minecraftClient = minecraftClient2;
            }
            uUID = minecraftClient.method_1548().method_44717();
        }
        return uUID;
    }

    public static synchronized void put(MsAuth$Account msAuth$Account) {
        ACCOUNTS.removeIf(arg_0 -> Accounts.lambda$put$0(msAuth$Account, arg_0));
        ACCOUNTS.add(new Accounts$Saved(msAuth$Account.uuid(), msAuth$Account.name(), msAuth$Account.refreshToken(), msAuth$Account.accessToken(), msAuth$Account.xuid(), System.currentTimeMillis() + b));
        Accounts.save();
    }

    public static synchronized void put(Accounts$Saved accounts$Saved) {
        ACCOUNTS.removeIf(arg_0 -> Accounts.lambda$put$1(accounts$Saved, arg_0));
        ACCOUNTS.add(accounts$Saved);
        Accounts.save();
    }

    public static synchronized void remove(UUID uUID) {
        ACCOUNTS.removeIf(arg_0 -> Accounts.lambda$remove$2(uUID, arg_0));
        Accounts.save();
    }

    public static void apply(MsAuth$Account msAuth$Account) {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        Session session = new Session(msAuth$Account.name(), msAuth$Account.uuid(), msAuth$Account.accessToken(), Optional.ofNullable(msAuth$Account.xuid()).filter(Accounts::lambda$apply$3), Optional.empty());
        minecraftClient.execute(() -> Accounts.lambda$apply$6(minecraftClient, session, msAuth$Account));
    }

    private static void rebuildIdentity(MinecraftClient minecraftClient, Session session) {
        try {
            YggdrasilAuthenticationService yggdrasilAuthenticationService = new YggdrasilAuthenticationService(minecraftClient.method_1487());
            UserApiService userApiService = yggdrasilAuthenticationService.createUserApiService(session.method_1674());
            ProfileKeys profileKeys = ProfileKeys.method_46532((UserApiService)userApiService, (Session)session, (Path)minecraftClient.field_1697.toPath());
            ProfileKeysAccessor profileKeysAccessor = (ProfileKeysAccessor)minecraftClient;
            profileKeysAccessor.aurora$setUserApiService(userApiService);
            profileKeysAccessor.aurora$setProfileKeys(profileKeys);
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[6], (Throwable)exception);
        }
    }

    
    private static void preserve() {
        var3 = Accounts.a;
        var1_1 = Accounts.PATH.resolveSibling(var3[32]);
        var0_2 = MsAuth.a;
        var2_3 = 2;
        while (Files.exists(var1_1, new LinkOption[0])) {
            var3 = Accounts.a;
            v0 = Accounts.PATH.resolveSibling(var3[8] + var2_3 + var3[9]);
            if (var0_2 == 0) {
                var1_1 = v0;
                ++var2_3;
                if (var0_2 == 0) continue;
            }
        }
        try {
            v0 = Files.move(Accounts.PATH, var1_1, new CopyOption[0]);
lbl15:

            var3 = Accounts.a;
            Aurora.LOG.warn(var3[29], (Object)var1_1);
        }
        catch (IOException var2_4) {
            Aurora.LOG.warn(Accounts.a[34], (Throwable)var2_4);
        }
    }

    
    public static synchronized void load() {
        block50: {
            ACCOUNTS.clear();
            int n = MsAuth.a;
            try {
                block59: {
                    boolean bl;
                    block51: {
                        block58: {
                            JsonArray jsonArray;
                            String[] stringArray;
                            boolean bl2;
                            block48: {
                                JsonArray jsonArray2;
                                block49: {
                                    JsonObject jsonObject;
                                    block41: {
                                        JsonObject jsonObject2;
                                        block42: {
                                            block44: {
                                                block46: {
                                                    boolean bl3;
                                                    block47: {
                                                        block45: {
                                                            block57: {
                                                                byte[] byArray;
                                                                block56: {
                                                                    block43: {
                                                                        JsonObject jsonObject3;
                                                                        block53: {
                                                                            block55: {
                                                                                block54: {
                                                                                    block52: {
                                                                                        boolean bl4;
                                                                                        block39: {
                                                                                            block40: {
                                                                                                JsonElement jsonElement;
                                                                                                block37: {
                                                                                                    JsonElement jsonElement2;
                                                                                                    block38: {
                                                                                                        Path path = PATH;
                                                                                                        if (n == 0) {
                                                                                                            if (!Files.exists(path, new LinkOption[0])) {
                                                                                                                return;
                                                                                                            }
                                                                                                            path = PATH;
                                                                                                        }
                                                                                                        String string = Files.readString(path, StandardCharsets.UTF_8);
                                                                                                        jsonElement2 = JsonParser.parseString((String)string);
                                                                                                        jsonElement = jsonElement2;
                                                                                                        if (n != 0) break block37;
                                                                                                        if (jsonElement.isJsonObject()) break block38;
                                                                                                        return;
                                                                                                    }
                                                                                                    jsonElement = jsonElement2;
                                                                                                }
                                                                                                jsonObject2 = jsonElement.getAsJsonObject();
                                                                                                bl4 = jsonObject2.has(a[10]);
                                                                                                if (n != 0) break block39;
                                                                                                if (bl4) break block40;
                                                                                                bl4 = true;
                                                                                                break block39;
                                                                                            }
                                                                                            bl4 = false;
                                                                                        }
                                                                                        bl2 = bl4;
                                                                                        jsonObject = jsonObject2;
                                                                                        if (n != 0) break block41;
                                                                                        if (!jsonObject.has(a[10])) break block42;
                                                                                        break block52;
                                                                                        catch (Exception exception) {
                                                                                            throw exception;
                                                                                        }
                                                                                    }
                                                                                    jsonObject3 = jsonObject2;
                                                                                    if (n != 0) break block53;
                                                                                    break block54;
                                                                                    catch (Exception exception) {
                                                                                        throw exception;
                                                                                    }
                                                                                }
                                                                                if (!jsonObject3.has(a[13])) break block43;
                                                                                break block55;
                                                                                catch (Exception exception) {
                                                                                    throw exception;
                                                                                }
                                                                            }
                                                                            jsonObject3 = jsonObject2.get(a[10]);
                                                                        }
                                                                        stringArray = a;
                                                                        byArray = SecretStore.unprotect(jsonObject3.getAsString(), Base64.getDecoder().decode(jsonObject2.get(stringArray[13]).getAsString()));
                                                                        break block56;
                                                                    }
                                                                    byArray = null;
                                                                }
                                                                jsonArray2 = byArray;
                                                                if (jsonArray2 != null) break block44;
                                                                bl3 = a[24].equals(jsonObject2.get(a[10]).getAsString());
                                                                if (n != 0) break block45;
                                                                break block57;
                                                                catch (Exception exception) {
                                                                    throw exception;
                                                                }
                                                            }
                                                            if (!bl3) break block46;
                                                            bl3 = SecretStore.keychainTimedOut();
                                                        }
                                                        if (n != 0) break block47;
                                                        if (!bl3) break block46;
                                                        bl3 = true;
                                                    }
                                                    saveBlocked = bl3;
                                                    stringArray = a;
                                                    Accounts.setError(stringArray[4]);
                                                    Aurora.LOG.warn(stringArray[25]);
                                                    return;
                                                }
                                                Accounts.preserve();
                                                return;
                                            }
                                            jsonObject2 = JsonParser.parseString((String)new String((byte[])jsonArray2, StandardCharsets.UTF_8)).getAsJsonObject();
                                        }
                                        jsonObject = jsonObject2;
                                    }
                                    stringArray = a;
                                    jsonArray2 = jsonObject.getAsJsonArray(stringArray[15]);
                                    jsonArray = jsonArray2;
                                    if (n != 0) break block48;
                                    if (jsonArray != null) break block49;
                                    return;
                                }
                                jsonArray = jsonArray2;
                            }
                            for (JsonElement jsonElement : jsonArray) {
                                JsonObject jsonObject = jsonElement.getAsJsonObject();
                                if (n != 0) break block50;
                                try {
                                    Accounts$Saved accounts$Saved;
                                    List<Accounts$Saved> list = ACCOUNTS;
                                    Accounts$Saved accounts$Saved2 = accounts$Saved;
                                    Accounts$Saved accounts$Saved3 = accounts$Saved;
                                    UUID uUID = UUID.fromString(jsonObject.get(a[20]).getAsString());
                                    String string = jsonObject.get(a[1]).getAsString();
                                    String string2 = jsonObject.get(a[35]).getAsString();
                                    String string3 = jsonObject.has(a[22]) ? jsonObject.get(a[22]).getAsString() : "";
                                    stringArray = a;
                                    String string4 = jsonObject.has(stringArray[30]) ? jsonObject.get(stringArray[30]).getAsString() : "";
                                    stringArray = a;
                                    accounts$Saved2(uUID, string, string2, string3, string4, jsonObject.has(stringArray[0]) ? jsonObject.get(stringArray[0]).getAsLong() : 0L);
                                    list.add(accounts$Saved3);
                                }
                                catch (Exception exception) {
                                }
                                if (n == 0) continue;
                            }
                            bl = bl2;
                            if (n != 0) break block51;
                            if (!bl) break block50;
                            break block58;
                            catch (Exception exception) {
                                throw exception;
                            }
                        }
                        bl = ACCOUNTS.isEmpty();
                    }
                    if (bl) break block50;
                    if (SecretStore.scheme() == null) break block50;
                    break block59;
                    catch (Exception exception) {
                        throw exception;
                    }
                }
                Aurora.LOG.info(a[18]);
                Accounts.save();
            }
            catch (Exception exception) {
                Aurora.LOG.warn(a[14], (Throwable)exception);
            }
        }
    }

    
    private static void restrict(Path path) {
        int n = MsAuth.a;
        try {
            PosixFileAttributeView posixFileAttributeView = Files.getFileAttributeView(path, PosixFileAttributeView.class, new LinkOption[0]);
            if (n != 0) return;
            if (posixFileAttributeView != null) {
                posixFileAttributeView.setPermissions(EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE));
                return;
            }
            File file = path.toFile();
            file.setReadable(false, false);
            file.setReadable(true, true);
            file.setWritable(false, false);
            file.setWritable(true, true);
            return;
        }
        catch (Exception exception) {
            Aurora.LOG.debug(a[2], (Object)path, (Object)exception);
        }
    }

    
    private static void save() {
        block24: {
            int n = MsAuth.a;
            if (saveBlocked) {
                Accounts.setError(a[23]);
                return;
            }
            try {
                Object object;
                String[] stringArray;
                Accounts$Saved accounts$Saved2222;
                block31: {
                    block32: {
                        Object object2;
                        JsonArray jsonArray = new JsonArray();
                        for (Accounts$Saved accounts$Saved2222 : ACCOUNTS) {
                            String string;
                            String string2;
                            block30: {
                                Accounts$Saved accounts$Saved3;
                                block38: {
                                    block29: {
                                        block39: {
                                            String string3;
                                            String string4;
                                            Object object3;
                                            block28: {
                                                Accounts$Saved accounts$Saved4;
                                                block36: {
                                                    block27: {
                                                        block37: {
                                                            String string5;
                                                            String string6;
                                                            JsonObject jsonObject;
                                                            block26: {
                                                                Accounts$Saved accounts$Saved5;
                                                                block33: {
                                                                    block25: {
                                                                        block35: {
                                                                            block34: {
                                                                                object2 = new JsonObject();
                                                                                jsonObject = object2;
                                                                                string6 = a[21];
                                                                                accounts$Saved5 = accounts$Saved2222;
                                                                                if (n != 0) break block33;
                                                                                jsonObject.addProperty(string6, accounts$Saved5.uuid().toString());
                                                                                if (n != 0) break block24;
                                                                                break block34;
                                                                                catch (IOException iOException) {
                                                                                    throw iOException;
                                                                                }
                                                                            }
                                                                            object2.addProperty(a[19], accounts$Saved2222.name());
                                                                            jsonObject = object2;
                                                                            string6 = a[17];
                                                                            if (accounts$Saved2222.refreshToken() != null) break block25;
                                                                            break block35;
                                                                            catch (IOException iOException) {
                                                                                throw iOException;
                                                                            }
                                                                        }
                                                                        string5 = "";
                                                                        break block26;
                                                                    }
                                                                    accounts$Saved5 = accounts$Saved2222;
                                                                }
                                                                string5 = accounts$Saved5.refreshToken();
                                                            }
                                                            jsonObject.addProperty(string6, string5);
                                                            object3 = object2;
                                                            string4 = a[12];
                                                            accounts$Saved4 = accounts$Saved2222;
                                                            if (n != 0) break block36;
                                                            if (accounts$Saved4.sessionToken() != null) break block27;
                                                            break block37;
                                                            catch (IOException iOException) {
                                                                throw iOException;
                                                            }
                                                        }
                                                        string3 = "";
                                                        break block28;
                                                    }
                                                    accounts$Saved4 = accounts$Saved2222;
                                                }
                                                string3 = accounts$Saved4.sessionToken();
                                            }
                                            object3.addProperty(string4, string3);
                                            string2 = a[26];
                                            accounts$Saved3 = accounts$Saved2222;
                                            if (n != 0) break block38;
                                            if (accounts$Saved3.xuid() != null) break block29;
                                            break block39;
                                            catch (IOException iOException) {
                                                throw iOException;
                                            }
                                        }
                                        string = "";
                                        break block30;
                                    }
                                    accounts$Saved3 = accounts$Saved2222;
                                }
                                string = accounts$Saved3.xuid();
                            }
                            object2.addProperty(string2, string);
                            stringArray = a;
                            object2.addProperty(stringArray[33], (Number)accounts$Saved2222.sessionExpiry());
                            jsonArray.add((JsonElement)object2);
                            if (n == 0) continue;
                        }
                        JsonObject jsonObject = new JsonObject();
                        stringArray = a;
                        jsonObject.add(stringArray[27], (JsonElement)jsonArray);
                        accounts$Saved2222 = jsonObject;
                        object2 = SecretStore.scheme();
                        if (n != 0) break block31;
                        if (object2 == null) break block32;
                        object = SecretStore.protect(GSON.toJson((JsonElement)jsonObject).getBytes(StandardCharsets.UTF_8));
                        if (n != 0) break block31;
                        if (object == null) break block32;
                        accounts$Saved2222 = new JsonObject();
                        stringArray = a;
                        accounts$Saved2222.addProperty(stringArray[7], (String)object2);
                        accounts$Saved2222.addProperty(stringArray[3], Base64.getEncoder().encodeToString((byte[])object));
                    }
                    Files.createDirectories(PATH.getParent(), new FileAttribute[0]);
                }
                stringArray = a;
                object = PATH.resolveSibling(String.valueOf(PATH.getFileName()) + stringArray[31]);
                Files.deleteIfExists((Path)object);
                Files.createFile((Path)object, new FileAttribute[0]);
                Accounts.restrict((Path)object);
                Files.writeString((Path)object, (CharSequence)GSON.toJson((JsonElement)accounts$Saved2222), StandardCharsets.UTF_8, new OpenOption[0]);
                Files.move((Path)object, PATH, StandardCopyOption.REPLACE_EXISTING);
                Accounts.restrict(PATH);
            }
            catch (IOException iOException) {
                Aurora.LOG.warn(a[28], (Throwable)iOException);
            }
        }
    }

    private static void lambda$apply$6(MinecraftClient minecraftClient, Session session, MsAuth$Account msAuth$Account) {
        ((SessionAccessor)minecraftClient).aurora$setSession(session);
        SessionAccessor sessionAccessor = (SessionAccessor)minecraftClient;
        sessionAccessor.aurora$setProfileFuture(CompletableFuture.completedFuture(null));
        UUID uUID = msAuth$Account.uuid();
        CompletableFuture.runAsync(() -> Accounts.lambda$apply$5(minecraftClient, uUID, sessionAccessor), (Executor)Util.method_55473());
        Accounts.rebuildIdentity(minecraftClient, session);
        CosmeticsSync.reset();
        AccountCapes.reset();
        Social.clear();
        Badges.accountChanged();
        Plus.accountChanged();
        PreviewPlayer.reset();
        Cosmetics.setChosenBetaCape("");
        SkinFetcher.forget(msAuth$Account.uuid());
        String string = msAuth$Account.name();
        String string2 = saveBlocked ? a[5] : "";
        String string3 = string2;
        String string4 = string;
        status = a[16] + string4 + string3;
    }

    private static void lambda$apply$5(MinecraftClient minecraftClient, UUID uUID, SessionAccessor sessionAccessor) {
        try {
            ProfileResult profileResult = minecraftClient.method_73361().comp_837().fetchProfile(uUID, true);
            if (profileResult != null) {
                minecraftClient.execute(() -> Accounts.lambda$apply$4(uUID, minecraftClient, sessionAccessor, profileResult));
            }
        }
        catch (Exception exception) {
        }
    }

    private static void lambda$apply$4(UUID uUID, MinecraftClient minecraftClient, SessionAccessor sessionAccessor, ProfileResult profileResult) {
        if (uUID.equals(minecraftClient.method_1548().method_44717())) {
            sessionAccessor.aurora$setProfileFuture(CompletableFuture.completedFuture(profileResult));
        }
    }

    private static boolean lambda$apply$3(String string) {
        boolean bl;
        block2: {
            block3: {
                int n = MsAuth.a;
                bl = string.isEmpty();
                if (n != 0) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean lambda$remove$2(UUID uUID, Accounts$Saved accounts$Saved) {
        return accounts$Saved.uuid().equals(uUID);
    }

    private static boolean lambda$put$1(Accounts$Saved accounts$Saved, Accounts$Saved accounts$Saved2) {
        return accounts$Saved2.uuid().equals(accounts$Saved.uuid());
    }

    private static boolean lambda$put$0(MsAuth$Account msAuth$Account, Accounts$Saved accounts$Saved) {
        return accounts$Saved.uuid().equals(msAuth$Account.uuid());
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[36];
                    var5_1 = 0;
                    var4_2 = "2(V\u000f3MG\u00045U\u0015([\u0004/,H\u0019$\u0002\"P\u0010>\u0002G.9\u0005\u000e?Q]3$F\bzRL3 L\u000f)KF/>\u0005\u00134\u0002R<\u0004%,Q\u001d@\n(\\\u001f2C@/mA\u0015>\u0002G.9\u0005\u001d4Q^$?\t\\4G^a>L\u001b4\u000f@/>\u0005\u001d(G\t/\"Q\\)C_$)\u0005\b2KZa>@\u000f)KF/\u0018mmK\u0013.\u0002Z ;@\u0018zVA(>\u0005\u000f?QZ(\"K8\u0012:L\b9JL%mD\u001f9M\\/9\u0005\u001e/V\t\"\"P\u0010>\u0002G.9\u0005\u000e?@\\(!A\\9JH5mV\u0015=L@/*\u0005\u0017?[Z\u0003$#F\u001b 8W\u0013(Cv .F\u0013/L]2cP\u0012(GH%,G\u0010?\u000f\u0005o'V\u00134\u0003$#F\u0014 8W\u0013(Cv .F\u0013/L]2cO\u000f5L\u00072(V\u000f3MG\u0004%,Q\u001d\u001d\u0002\"P\u0010>\u0002G.9\u0005\u000e?CMa>D\n?F\t .F\u0013/L]2\b .F\u0013/L]2\u000b\u0011!D\u00053LNa,V\\\u00073(C\u000e?QA-\f\"S\u00154E\t2,S\u0019>\u0002H\".J\t4VZa/@\u00143LMa9M\u0019zmza&@\u0005zQ].?@\u0004/,H\u0019\u000448L\u0018\u000448L\u0018\u00072(V\u000f3MG@\n(\\\u001f2C@/mA\u0015>\u0002G.9\u0005\u001d4Q^$?\t\\4G^a>L\u001b4\u000f@/>\u0005\u001d(G\t/\"Q\\)C_$)\u0005\b2KZa>@\u000f)KF/\u0010*(\\\u001f2C@/`D\u0019)\u000fN\" h\n(\\\u001f2C@/mA\u0015>\u0002G.9\u0005\u001d4Q^$?\t\\)C_$)\u0005\u001d9AF4#Q\u000fzC[$mI\u0019<V\t >\u0005\b2GPa,W\u0019zCG%mK\u0013.J@/*\u0005\u0015)\u0002Z ;@\u0018zM_$?\u0005\b2GDa9M\u0015)\u0002Z$>V\u00155L\u000498L\u0018\b .F\u0013/L]2\u0017\u0002\"P\u0010>\u0002G.9\u0005\u000f;TLa,F\u001f5WG5>\u009d\u0012,S\u0019>\u0002H\".J\t4VZa:@\u000e?\u0002Y3\"Q\u00199VL%mG\u0005zC\t*(\\\\.J@2mH\u001d9J@/(\u0005\u001f;LG.9\u0005\u001b?V\t#,F\u0017t\u0002b$=Q\\;V\t:0\t\\)M\t2$B\u0012zKGa,B\u001d3L\t.?\u0005\u000e?Q].?@\\.JLa&@\u00059JH(#\u0005\u0013(\u0002~(#A\u0013-Q\t1?J\u001a3NLa9M\u001d.\u0002^3\"Q\u0019zVA$ \u000b\u000498L\u0018\u0004o9H\f\u001f 8W\u0013(Cv .F\u0013/L]2cP\u0012(GH%,G\u0010?\fC2\"K\r2(V\u000f3MG\u00045U\u0015([";
                    var6_3 = "2(V\u000f3MG\u00045U\u0015([\u0004/,H\u0019$\u0002\"P\u0010>\u0002G.9\u0005\u000e?Q]3$F\bzRL3 L\u000f)KF/>\u0005\u00134\u0002R<\u0004%,Q\u001d@\n(\\\u001f2C@/mA\u0015>\u0002G.9\u0005\u001d4Q^$?\t\\4G^a>L\u001b4\u000f@/>\u0005\u001d(G\t/\"Q\\)C_$)\u0005\b2KZa>@\u000f)KF/\u0018mmK\u0013.\u0002Z ;@\u0018zVA(>\u0005\u000f?QZ(\"K8\u0012:L\b9JL%mD\u001f9M\\/9\u0005\u001e/V\t\"\"P\u0010>\u0002G.9\u0005\u000e?@\\(!A\\9JH5mV\u0015=L@/*\u0005\u0017?[Z\u0003$#F\u001b 8W\u0013(Cv .F\u0013/L]2cP\u0012(GH%,G\u0010?\u000f\u0005o'V\u00134\u0003$#F\u0014 8W\u0013(Cv .F\u0013/L]2cO\u000f5L\u00072(V\u000f3MG\u0004%,Q\u001d\u001d\u0002\"P\u0010>\u0002G.9\u0005\u000e?CMa>D\n?F\t .F\u0013/L]2\b .F\u0013/L]2\u000b\u0011!D\u00053LNa,V\\\u00073(C\u000e?QA-\f\"S\u00154E\t2,S\u0019>\u0002H\".J\t4VZa/@\u00143LMa9M\u0019zmza&@\u0005zQ].?@\u0004/,H\u0019\u000448L\u0018\u000448L\u0018\u00072(V\u000f3MG@\n(\\\u001f2C@/mA\u0015>\u0002G.9\u0005\u001d4Q^$?\t\\4G^a>L\u001b4\u000f@/>\u0005\u001d(G\t/\"Q\\)C_$)\u0005\b2KZa>@\u000f)KF/\u0010*(\\\u001f2C@/`D\u0019)\u000fN\" h\n(\\\u001f2C@/mA\u0015>\u0002G.9\u0005\u001d4Q^$?\t\\)C_$)\u0005\u001d9AF4#Q\u000fzC[$mI\u0019<V\t >\u0005\b2GPa,W\u0019zCG%mK\u0013.J@/*\u0005\u0015)\u0002Z ;@\u0018zM_$?\u0005\b2GDa9M\u0015)\u0002Z$>V\u00155L\u000498L\u0018\b .F\u0013/L]2\u0017\u0002\"P\u0010>\u0002G.9\u0005\u000f;TLa,F\u001f5WG5>\u009d\u0012,S\u0019>\u0002H\".J\t4VZa:@\u000e?\u0002Y3\"Q\u00199VL%mG\u0005zC\t*(\\\\.J@2mH\u001d9J@/(\u0005\u001f;LG.9\u0005\u001b?V\t#,F\u0017t\u0002b$=Q\\;V\t:0\t\\)M\t2$B\u0012zKGa,B\u001d3L\t.?\u0005\u000e?Q].?@\\.JLa&@\u00059JH(#\u0005\u0013(\u0002~(#A\u0013-Q\t1?J\u001a3NLa9M\u001d.\u0002^3\"Q\u0019zVA$ \u000b\u000498L\u0018\u0004o9H\f\u001f 8W\u0013(Cv .F\u0013/L]2cP\u0012(GH%,G\u0010?\fC2\"K\r2(V\u000f3MG\u00045U\u0015([".length();
                    var3_4 = 13;
                    var2_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var2_5;
                        v1 = var4_2.substring(v0, v0 + var3_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        var4_2 = "\u0002\"P\u0010>\u0002G.9\u0005\u000f?V\t >L\u0018?\u0002])(\u0005\t4PL )D\u001e6G\t .F\u0013/L]2mC\u00156G\u00073(C\u000e?QA";
                        var6_3 = "\u0002\"P\u0010>\u0002G.9\u0005\u000f?V\t >L\u0018?\u0002])(\u0005\t4PL )D\u001e6G\t .F\u0013/L]2mC\u00156G\u00073(C\u000e?QA".length();
                        var3_4 = 48;
                        var2_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var2_5;
                            v1 = var4_2.substring(v4, v4 + var3_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var8_6 = 0;
                do {
                    v6 = v6;
                    v8 = var8_6;
                    v9 = v6[v8];
                    switch (var8_6 % 7) {
                        case 0: {
                            v10 = 65;
                            break;
                        }
                        case 1: {
                            v10 = 77;
                            break;
                        }
                        case 2: {
                            v10 = 37;
                            break;
                        }
                        case 3: {
                            v10 = 124;
                            break;
                        }
                        case 4: {
                            v10 = 90;
                            break;
                        }
                        case 5: {
                            v10 = 34;
                            break;
                        }
                        default: {
                            v10 = 41;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var8_6;
lbl65:

                    v7 = v7;
                } while (v7 > var8_6);
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
            Accounts.a = var7;
            Accounts.SAVE_BLOCKED = Accounts.a[4];
            break block21;
lbl79:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 713109928386973890L;
        while (true)
        Accounts.b = 713109928315056834L ^ var0_7;
        Accounts.GSON = new GsonBuilder().setPrettyPrinting().create();
        Accounts.PATH = FabricLoader.getInstance().getConfigDir().resolve(Accounts.a[11]);
        Accounts.ACCOUNTS = new ArrayList<Accounts$Saved>();
        Accounts.status = "";
    }
}
