package dev.quelchi.aurora.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.yggdrasil.ProfileResult;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.util.KeyUtil;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.invoke.LambdaMetafactory;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.texture.PlayerSkinProvider;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.util.Util;

public final class SkinFetcher {
    private static final String NAME_TO_UUID;
    private static final Map<UUID, SkinTextures> RESOLVED;
    private static final Set<UUID> ASKED;
    private static final String[] a;
    private static final long b;

    private SkinFetcher() {
    }

    public static CompletableFuture<Optional<SkinTextures>> fetch(String string) {
        return ((CompletableFuture)CompletableFuture.supplyAsync(() -> SkinFetcher.lambda$fetch$0(string), (Executor)Util.method_55473()).thenComposeAsync(SkinFetcher::lambda$fetch$1, (Executor)MinecraftClient.method_1551())).exceptionally(arg_0 -> SkinFetcher.lambda$fetch$2(string, arg_0));
    }

    public static void forget(UUID uUID) {
        block2: {
            UUID uUID2;
            block3: {
                int n = KeyUtil.a;
                uUID2 = uUID;
                if (n != 0) break block2;
                if (uUID2 != null) break block3;
                return;
            }
            uUID2 = RESOLVED.remove(uUID);
        }
        ASKED.remove(uUID);
    }

    
    public static SkinTextures skinFor(UUID var0) {
        block33: {
            block35: {
                block34: {
                    block32: {
                        block31: {
                            block29: {
                                block30: {
                                    block27: {
                                        block28: {
                                            var2_1 = MinecraftClient.method_1551();
                                            var1_2 = KeyUtil.a;
                                            if (var0 == null) {
                                                return DefaultSkinHelper.method_62620();
                                            }
                                            v1 = var2_1;
                                            if (var1_2 != 0) break block27;
                                            if (v1.field_1687 == null) break block28;
                                            v1 = var2_1;
                                            if (var1_2 != 0) break block27;
                                            var4_3 = v1.field_1687.method_18470(var0);
                                            if (var4_3 instanceof AbstractClientPlayerEntity) {
                                                var3_4 = (AbstractClientPlayerEntity)var4_3;
                                                return var3_4.method_52814();
                                            }
                                        }
                                        v1 = var2_1;
                                    }
                                    if (var1_2 != 0) break block29;
                                    if (v1.method_1562() == null) break block30;
                                    v1 = var2_1.method_1562().method_2871(var0);
                                    if (var1_2 != 0) break block29;
                                    var3_5 = v1;
                                    if (var3_5 != null) {
                                        return var3_5.method_52810();
                                    }
                                }
                                v1 = SkinFetcher.RESOLVED.get(var0);
                            }
                            var3_5 = (SkinTextures)v1;
                            v7 = var3_5;
                            if (var1_2 != 0) break block31;
                            if (v7 == null) break block32;
                            v7 = var3_5;
                        }
                        return v7;
                    }
                    if (!SkinFetcher.ASKED.add(var0)) break block33;
                    if (var2_1.method_1548() != null) {
                    }
                    v10 = var0;
                    if (var1_2 != 0) break block34;
                    if (v10.equals(var2_1.method_1548().method_44717())) {
                    }
                    var4_3 = SkinFetcher.fetch(var2_1.method_1548().method_1676());
                    if (var1_2 == 0) break block35;
lbl85:

                    v10 = var0;
                }
                var4_3 = SkinFetcher.fetchProfileById(v10).thenCompose((Function<Optional, CompletionStage>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$skinFor$3(java.util.Optional ), (Ljava/util/Optional;)Ljava/util/concurrent/CompletionStage;)());
            }
            var4_3.thenAccept((Consumer<Optional>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$skinFor$5(java.util.UUID java.util.Optional ), (Ljava/util/Optional;)V)((UUID)var0));
        }
        return DefaultSkinHelper.method_4648((UUID)var0);
    }

    public static CompletableFuture<Optional<GameProfile>> fetchProfile(String string) {
        return CompletableFuture.supplyAsync(() -> SkinFetcher.lambda$fetchProfile$6(string), (Executor)Util.method_55473()).exceptionally(arg_0 -> SkinFetcher.lambda$fetchProfile$7(string, arg_0));
    }

    public static CompletableFuture<Optional<GameProfile>> fetchProfileById(UUID uUID) {
        return CompletableFuture.supplyAsync(() -> SkinFetcher.lambda$fetchProfileById$8(uUID), (Executor)Util.method_55473());
    }

    public static CompletableFuture<Optional<SkinTextures>> skinOf(GameProfile gameProfile) {
        if (gameProfile == null) {
            return CompletableFuture.completedFuture(Optional.empty());
        }
        return MinecraftClient.method_1551().method_1582().method_52863(gameProfile).exceptionally(SkinFetcher::lambda$skinOf$9);
    }

    private static GameProfile resolveProfile(String string) {
        int n = KeyUtil.a;
        try {
            GameProfile gameProfile;
            block13: {
                block12: {
                    ProfileResult profileResult;
                    block11: {
                        JsonObject jsonObject;
                        String[] stringArray = a;
                        HttpURLConnection httpURLConnection = (HttpURLConnection)URI.create(stringArray[4] + string).toURL().openConnection();
                        httpURLConnection.setConnectTimeout(5000);
                        httpURLConnection.setReadTimeout(5000);
                        if (httpURLConnection.getResponseCode() != 200) {
                            return null;
                        }
                        try (Object object = new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8);){
                            jsonObject = JsonParser.parseReader((Reader)object).getAsJsonObject();
                        }
                        stringArray = a;
                        object = jsonObject.get(stringArray[1]).getAsString();
                        UUID uUID = UUID.fromString(((String)object).replaceFirst(stringArray[6], stringArray[7]));
                        ProfileResult profileResult2 = MinecraftClient.method_1551().method_73361().comp_837().fetchProfile(uUID, false);
                        profileResult = profileResult2;
                        if (n != 0) break block11;
                        if (profileResult == null) break block12;
                        profileResult = profileResult2;
                    }
                    gameProfile = profileResult.profile();
                    break block13;
                }
                gameProfile = null;
            }
            return gameProfile;
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[3], (Object)string, (Object)exception);
            return null;
        }
    }

    private static Optional lambda$skinOf$9(Throwable throwable) {
        return Optional.empty();
    }

    
    private static Optional lambda$fetchProfileById$8(UUID uUID) {
        int n = KeyUtil.a;
        try {
            GameProfile gameProfile;
            ProfileResult profileResult2;
            ProfileResult profileResult = profileResult2 = MinecraftClient.method_1551().method_73361().comp_837().fetchProfile(uUID, false);
            if (n == 0) {
                if (profileResult == null) {
                    gameProfile = null;
                    return Optional.ofNullable(gameProfile);
                }
                profileResult = profileResult2;
            }
            gameProfile = profileResult.profile();
            return Optional.ofNullable(gameProfile);
        }
        catch (Exception exception) {
            return Optional.empty();
        }
    }

    private static Optional lambda$fetchProfile$7(String string, Throwable throwable) {
        Aurora.LOG.warn(a[2], (Object)string, (Object)throwable);
        return Optional.empty();
    }

    private static Optional lambda$fetchProfile$6(String string) {
        return Optional.ofNullable(SkinFetcher.resolveProfile(string));
    }

    
    private static void lambda$skinFor$5(UUID uUID, Optional optional) {
        block6: {
            int n;
            block5: {
                n = KeyUtil.a;
                Optional optional2 = optional;
                if (n != 0) break block5;
                if (!optional2.isPresent()) break block6;
                optional2 = RESOLVED.put(uUID, (SkinTextures)optional.get());
            }
            if (n == 0) return;
        }
        CompletableFuture.delayedExecutor(b, TimeUnit.SECONDS).execute(() -> SkinFetcher.lambda$skinFor$4(uUID));
    }

    private static void lambda$skinFor$4(UUID uUID) {
        ASKED.remove(uUID);
    }

    private static CompletionStage lambda$skinFor$3(Optional optional) {
        CompletableFuture<Optional> completableFuture;
        block6: {
            Optional optional2;
            block4: {
                block5: {
                    int n = KeyUtil.a;
                    optional2 = optional;
                    if (n != 0) break block4;
                    if (!optional2.isPresent()) break block5;
                    completableFuture = SkinFetcher.skinOf((GameProfile)optional.get());
                    break block6;
                }
                optional2 = Optional.empty();
            }
            completableFuture = CompletableFuture.completedFuture(optional2);
        }
        return completableFuture;
    }

    private static Optional lambda$fetch$2(String string, Throwable throwable) {
        Aurora.LOG.warn(a[0], (Object)string, (Object)throwable);
        return Optional.empty();
    }

    private static CompletionStage lambda$fetch$1(GameProfile gameProfile) {
        PlayerSkinProvider playerSkinProvider;
        block7: {
            PlayerSkinProvider playerSkinProvider2;
            block8: {
                block6: {
                    playerSkinProvider2 = MinecraftClient.method_1551().method_1582();
                    int n = KeyUtil.a;
                    if (gameProfile == null) break block6;
                    playerSkinProvider = playerSkinProvider2;
                    if (n != 0) break block7;
                    if (playerSkinProvider != null) break block8;
                }
                return CompletableFuture.completedFuture(Optional.empty());
            }
            playerSkinProvider = playerSkinProvider2;
        }
        return playerSkinProvider.method_52863(gameProfile);
    }

    private static GameProfile lambda$fetch$0(String string) {
        return SkinFetcher.resolveProfile(string);
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[8];
                    var5_1 = 0;
                    var4_2 = "}\u0003|\u0014\tGjZ\u000b}ZO@fB\rqZON}\u000eOn\u0007\u000e\u0002G\f\u001e~\u001az\u001c@Mj\u000e\u0004z\u0015BT\u007f\u000e\u000et\u0013EDk\u000e\u000ez\b\t\u0006tSO\"m\u0007`\u0016M\u0001aA\u001c5\bLR`B\u001epZYS`H\u0001y\u001f\tG`\\H2\u0001T\u00060F\u001ca\nZ\u001b \u0001\te\u0013\u0007L`D\t{\u001d\u0007B`CG`\tLS|\u0001\u0018g\u0015OHcK\u001b:\u0017@OjM\u001at\u001c]\u000e0F\u001ca\nZ\u001b \u0001\te\u0013\u0007L`D\t{\u001d\u0007B`CG`\tLS|\u0001\u0018g\u0015OHcK\u001b:\u0017@OjM\u001at\u001c]\u000e";
                    var6_3 = "}\u0003|\u0014\tGjZ\u000b}ZO@fB\rqZON}\u000eOn\u0007\u000e\u0002G\f\u001e~\u001az\u001c@Mj\u000e\u0004z\u0015BT\u007f\u000e\u000et\u0013EDk\u000e\u000ez\b\t\u0006tSO\"m\u0007`\u0016M\u0001aA\u001c5\bLR`B\u001epZYS`H\u0001y\u001f\tG`\\H2\u0001T\u00060F\u001ca\nZ\u001b \u0001\te\u0013\u0007L`D\t{\u001d\u0007B`CG`\tLS|\u0001\u0018g\u0015OHcK\u001b:\u0017@OjM\u001at\u001c]\u000e0F\u001ca\nZ\u001b \u0001\te\u0013\u0007L`D\t{\u001d\u0007B`CG`\tLS|\u0001\u0018g\u0015OHcK\u001b:\u0017@OjM\u001at\u001c]\u000e".length();
                    var3_4 = 26;
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
                        var4_2 = "\u00064b\u0001\u0011\\&\u00064b\u0001\u001d\\&\u00064b\u0001\u001d\\&\u00064b\u0001\u001d\\&\u00064b\u0001\u0018\u0013r\u0007\u000e\nY8^\u001b\f+\u001dE1N\u0004\u0005:";
                        var6_3 = "\u00064b\u0001\u0011\\&\u00064b\u0001\u001d\\&\u00064b\u0001\u001d\\&\u00064b\u0001\u001d\\&\u00064b\u0001\u0018\u0013r\u0007\u000e\nY8^\u001b\f+\u001dE1N\u0004\u0005:".length();
                        var3_4 = 36;
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
                            v10 = 46;
                            break;
                        }
                        case 1: {
                            v10 = 104;
                            break;
                        }
                        case 2: {
                            v10 = 21;
                            break;
                        }
                        case 3: {
                            v10 = 122;
                            break;
                        }
                        case 4: {
                            v10 = 41;
                            break;
                        }
                        case 5: {
                            v10 = 33;
                            break;
                        }
                        default: {
                            v10 = 15;
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
            SkinFetcher.a = var7;
            SkinFetcher.NAME_TO_UUID = SkinFetcher.a[5];
            break block21;
lbl79:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 3567554344044523851L;
        while (true)
        SkinFetcher.b = 3567554344044523861L ^ var0_7;
        SkinFetcher.RESOLVED = new ConcurrentHashMap<UUID, SkinTextures>();
        SkinFetcher.ASKED = ConcurrentHashMap.newKeySet();
    }
}
