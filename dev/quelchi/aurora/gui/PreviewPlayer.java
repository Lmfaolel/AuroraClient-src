package dev.quelchi.aurora.gui;

import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Lifecycle;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.gui.PreviewPlayer$Guarded;
import dev.quelchi.aurora.gui.PreviewPlayer$Skinned;
import dev.quelchi.aurora.gui.PreviewScene;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.network.ClientConnectionState;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.session.telemetry.TelemetrySender;
import net.minecraft.client.session.telemetry.WorldSession;
import net.minecraft.client.world.ClientChunkLoadProgress;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkSide;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.server.ServerLinks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.dimension.DimensionType;

public final class PreviewPlayer {
    private static boolean built;
    private static OtherClientPlayerEntity entity;
    private static ClientWorld world;
    private static final Map<String, OtherClientPlayerEntity> EXTRA;
    private static final String[] a;

    private PreviewPlayer() {
    }

    
    public static void reset() {
        EXTRA.clear();
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (!built) return;
        if (entity == null) return;
        if (world == null) return;
        if (minecraftClient.method_1548() == null) {
            return;
        }
        try {
            entity = new PreviewPlayer$Skinned(world, new GameProfile(minecraftClient.method_1548().method_44717(), minecraftClient.method_1548().method_1676()));
            return;
        }
        catch (Throwable throwable) {
            Aurora.LOG.warn(a[4], throwable);
            entity = null;
        }
    }

    
    public static OtherClientPlayerEntity get() {
        int n = PreviewScene.a;
        boolean bl = built;
        if (n == 0) {
            if (bl) {
                return entity;
            }
            bl = true;
        }
        built = bl;
        try {
            entity = PreviewPlayer.build();
            return entity;
        }
        catch (Throwable throwable) {
            Aurora.LOG.warn(a[2], throwable);
            entity = null;
        }
        return entity;
    }

    
    public static OtherClientPlayerEntity actor(String string, boolean bl) {
        PreviewPlayer$Guarded previewPlayer$Guarded;
        block17: {
            block19: {
                OtherClientPlayerEntity otherClientPlayerEntity;
                block18: {
                    OtherClientPlayerEntity otherClientPlayerEntity2 = EXTRA.get(string);
                    int n = PreviewScene.a;
                    otherClientPlayerEntity = otherClientPlayerEntity2;
                    if (n != 0) break block18;
                    if (otherClientPlayerEntity == null && !EXTRA.containsKey(string)) break block19;
                    otherClientPlayerEntity = otherClientPlayerEntity2;
                }
                return otherClientPlayerEntity;
            }
            previewPlayer$Guarded = null;
            try {
                GameProfile gameProfile;
                if (PreviewPlayer.get() == null) break block17;
                if (bl) {
                    v1 = MinecraftClient.method_1551().method_53462();
                } else {
                    String[] stringArray = a;
                    v1 = gameProfile = new GameProfile(UUID.nameUUIDFromBytes((stringArray[5] + string).getBytes(StandardCharsets.UTF_8)), stringArray[0]);
                }
                if (gameProfile != null && world != null) {
                    previewPlayer$Guarded = bl ? new PreviewPlayer$Skinned(world, gameProfile) : new PreviewPlayer$Guarded(world, gameProfile);
                }
            }
            catch (Throwable throwable) {
                Aurora.LOG.warn(a[6], (Object)string, (Object)throwable);
            }
        }
        EXTRA.put(string, previewPlayer$Guarded);
        return previewPlayer$Guarded;
    }

    public static boolean owns(Entity entity) {
        return entity instanceof PreviewPlayer$Guarded;
    }

    public static void disable(Throwable throwable) {
        if (entity == null) {
            return;
        }
        entity = null;
        Aurora.LOG.warn(a[3], throwable);
    }

    private static OtherClientPlayerEntity build() {
        ClientWorld clientWorld;
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        GameProfile gameProfile = minecraftClient.method_53462();
        if (gameProfile == null) {
            return null;
        }
        DynamicRegistryManager.Immutable immutable = PreviewPlayer.registries();
        ClientConnectionState clientConnectionState = new ClientConnectionState(new ClientChunkLoadProgress(), gameProfile, new WorldSession(TelemetrySender.field_41434, false, Duration.ZERO, null), immutable, FeatureFlags.field_40183, a[1], null, null, Map.of(), new ChatHud.ChatState(List.of(), List.of(), List.of()), Map.of(), ServerLinks.field_51977, new HashMap(), true);
        ClientPlayNetworkHandler clientPlayNetworkHandler = new ClientPlayNetworkHandler(minecraftClient, new ClientConnection(NetworkSide.field_11942), clientConnectionState);
        world = clientWorld = new ClientWorld(clientPlayNetworkHandler, new ClientWorld.Properties(Difficulty.field_5802, false, true), World.field_25179, RegistryEntry.method_40223((Object)PreviewPlayer.dimension()), 1, 1, null, false, 0L, 0);
        return new PreviewPlayer$Skinned(clientWorld, gameProfile);
    }

    private static DynamicRegistryManager.Immutable registries() {
        ArrayList<Registry> arrayList = new ArrayList<Registry>();
        HashSet<RegistryKey> hashSet = new HashSet<RegistryKey>();
        int n = PreviewScene.a;
        for (Registry registry : Registries.field_41167) {
            arrayList.add(registry);
            hashSet.add(registry.method_46765());
            if (n == 0) continue;
        }
        Iterator iterator = BuiltinRegistries.method_46817();
        iterator.method_55282().forEach(arg_0 -> PreviewPlayer.lambda$registries$0(hashSet, arrayList, (RegistryWrapper.WrapperLookup)iterator, arg_0));
        return new DynamicRegistryManager.ImmutableImpl(arrayList).method_40316();
    }

    private static Registry<?> copy(RegistryWrapper.WrapperLookup wrapperLookup, RegistryKey registryKey) {
        SimpleRegistry simpleRegistry = new SimpleRegistry(registryKey, Lifecycle.stable());
        wrapperLookup.method_46762(registryKey).method_42017().forEach(arg_0 -> PreviewPlayer.lambda$copy$1(simpleRegistry, arg_0));
        return simpleRegistry.method_40276();
    }

    private static DimensionType dimension() {
        return new DimensionType(true, false, false, 1.0, 0, 256, 256, BlockTags.field_25588, 1.0f, new DimensionType.MonsterSettings((IntProvider)ConstantIntProvider.method_34998((int)0), 0), DimensionType.Skybox.field_64385, DimensionType.CardinalLightType.field_64380, EnvironmentAttributeMap.field_63724, RegistryEntryList.method_58563());
    }

    private static void lambda$copy$1(SimpleRegistry simpleRegistry, Object object) {
        RegistryEntry.Reference reference = (RegistryEntry.Reference)object;
        Registry.method_39197((Registry)simpleRegistry, (RegistryKey)reference.method_40237(), (Object)reference.comp_349());
    }

    private static void lambda$registries$0(Set set, List list, RegistryWrapper.WrapperLookup wrapperLookup, RegistryKey registryKey) {
        block4: {
            int n = PreviewScene.a;
            boolean bl = set.add(registryKey);
            if (n != 0 || !bl) break block4;
            bl = list.add(PreviewPlayer.copy(wrapperLookup, registryKey));
        }
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "\u001eM=m$)_%\u00060H?m8-9\u0010H?m8-\u000bqS\"\":>T'T(uj<]0D(pflW0Q!k$+\u00113\\.ij8^qI%gj.P#Xmo%(T=L\u0010H?m8-\u000bqM?g<%T&\u001d=n+5T#\u001d9j8)FqJ%k&)\u0011#X#f/>X?Za\",-]=T#ej.P2Vmv%lE9Xm`+>TqP\"f/ ,\u0010H?m8-\u000bq^\"w&(\u0011?R9\"8)S$T!fj8Y4\u001d=p/:X4Jmr&-H4O";
                var4_3 = "\u001eM=m$)_%\u00060H?m8-9\u0010H?m8-\u000bqS\"\":>T'T(uj<]0D(pflW0Q!k$+\u00113\\.ij8^qI%gj.P#Xmo%(T=L\u0010H?m8-\u000bqM?g<%T&\u001d=n+5T#\u001d9j8)FqJ%k&)\u0011#X#f/>X?Za\",-]=T#ej.P2Vmv%lE9Xm`+>TqP\"f/ ,\u0010H?m8-\u000bq^\"w&(\u0011?R9\"8)S$T!fj8Y4\u001d=p/:X4Jmr&-H4O".length();
                var1_4 = 8;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "0H?m8-\u000b\u001f\u0010H?m8-\u000bqS\"\":>T'T(uj-R%R?\",#CqF0";
                    var4_3 = "0H?m8-\u000b\u001f\u0010H?m8-\u000bqS\"\":>T'T(uj-R%R?\",#CqF0".length();
                    var1_4 = 7;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 81;
                        break;
                    }
                    case 1: {
                        v10 = 61;
                        break;
                    }
                    case 2: {
                        v10 = 77;
                        break;
                    }
                    case 3: {
                        v10 = 2;
                        break;
                    }
                    case 4: {
                        v10 = 74;
                        break;
                    }
                    case 5: {
                        v10 = 76;
                        break;
                    }
                    default: {
                        v10 = 49;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_6;
lbl65:

                v7 = v7;
            } while (v7 > var6_6);
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
        PreviewPlayer.a = var5;
        PreviewPlayer.EXTRA = new HashMap<String, OtherClientPlayerEntity>();
    }
}
