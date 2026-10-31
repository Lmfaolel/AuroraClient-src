package dev.quelchi.aurora.cosmetics.renderer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zigythebird.playeranim.animation.AvatarAnimManager;
import com.zigythebird.playeranim.animation.PlayerAnimationController;
import com.zigythebird.playeranim.api.PlayerAnimationAccess;
import com.zigythebird.playeranimcore.animation.Animation;
import com.zigythebird.playeranimcore.animation.AnimationController;
import com.zigythebird.playeranimcore.animation.AnimationData;
import com.zigythebird.playeranimcore.animation.layered.IAnimation;
import com.zigythebird.playeranimcore.enums.PlayState;
import com.zigythebird.playeranimcore.loading.PlayerAnimatorLoader;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.content.Emotes;
import dev.quelchi.aurora.cosmetics.renderer.EffectList;
import dev.quelchi.aurora.gui.PreviewPlayer;
import dev.quelchi.aurora.module.Module;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.PlayerLikeEntity;

public final class EmoteEngine {
    private static final Map<String, Animation> SERVED;
    private static final Map<String, Animation> LOCAL;
    private static final Set<String> TRAVERSAL;
    private static final Map<AbstractClientPlayerEntity, PlayerAnimationController> LAYERS;
    private static final Map<AbstractClientPlayerEntity, String> PLAYING;
    private static WeakReference<AbstractClientPlayerEntity> lastSelf;
    private static final String[] a;

    private EmoteEngine() {
    }

    public static void setTraversal(Collection<String> collection) {
        TRAVERSAL.clear();
        TRAVERSAL.addAll(collection);
    }

    public static boolean isTraversal(String string) {
        boolean bl;
        block5: {
            block4: {
                int n = EffectList.a;
                if (string == null) break block4;
                bl = TRAVERSAL.contains(string);
                if (n != 0) break block5;
                if (!bl) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    private static Animation clip(String string) {
        Animation animation;
        block12: {
            Animation animation2;
            block10: {
                block11: {
                    String string2;
                    int n;
                    block8: {
                        block9: {
                            n = EffectList.a;
                            string2 = string;
                            if (n != 0) break block8;
                            if (string2 != null) break block9;
                            return null;
                        }
                        string2 = LOCAL.get(string);
                    }
                    Animation animation3 = (Animation)string2;
                    animation2 = animation3;
                    if (n != 0) break block10;
                    if (animation2 == null) break block11;
                    animation = animation3;
                    break block12;
                }
                animation2 = SERVED.get(string);
            }
            animation = animation2;
        }
        return animation;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean loops(String string) {
        Animation animation;
        block4: {
            Animation animation2 = EmoteEngine.clip(string);
            int n = EffectList.a;
            animation = animation2;
            if (n != 0) break block4;
            if (animation == null) return true;
            animation = animation2;
        }
        if (animation.loopType() == Animation.LoopType.PLAY_ONCE) return false;
        return true;
    }

    public static float lengthSeconds(String string) {
        float f;
        block6: {
            Animation animation;
            block4: {
                Animation animation2;
                block5: {
                    animation2 = EmoteEngine.clip(string);
                    int n = EffectList.a;
                    animation = animation2;
                    if (n != 0) break block4;
                    if (animation != null) break block5;
                    f = 0.0f;
                    break block6;
                }
                animation = animation2;
            }
            f = animation.length() / 20.0f;
        }
        return f;
    }

    private static PlayerAnimationController layerFor(AbstractClientPlayerEntity abstractClientPlayerEntity) {
        block6: {
            PlayerAnimationController playerAnimationController;
            block5: {
                PlayerAnimationController playerAnimationController2 = LAYERS.get(abstractClientPlayerEntity);
                int n = EffectList.a;
                playerAnimationController = playerAnimationController2;
                if (n != 0) break block5;
                if (playerAnimationController == null) break block6;
                playerAnimationController = playerAnimationController2;
            }
            return playerAnimationController;
        }
        try {
            AvatarAnimManager avatarAnimManager = PlayerAnimationAccess.getPlayerAnimManager((PlayerLikeEntity)abstractClientPlayerEntity);
            if (avatarAnimManager == null) {
                return null;
            }
            PlayerAnimationController playerAnimationController = new PlayerAnimationController((PlayerLikeEntity)abstractClientPlayerEntity, EmoteEngine::lambda$layerFor$0);
            avatarAnimManager.addAnimLayer(1000, (IAnimation)playerAnimationController);
            LAYERS.put(abstractClientPlayerEntity, playerAnimationController);
            return playerAnimationController;
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[3], (Object)abstractClientPlayerEntity.method_5477().getString(), (Object)exception.toString());
            return null;
        }
    }

    public static void setServed(Map<String, JsonObject> map) {
        EmoteEngine.load(SERVED, map, a[1]);
    }

    public static void setLocal(Map<String, JsonObject> map) {
        EmoteEngine.load(LOCAL, map, a[2]);
    }

    private static void load(Map<String, Animation> map, Map<String, JsonObject> map2, String string) {
        block3: {
            map.clear();
            Iterator<Map.Entry<String, JsonObject>> iterator = map2.entrySet().iterator();
            int n = EffectList.a;
            while (iterator.hasNext()) {
                Map.Entry<String, JsonObject> entry = iterator.next();
                try {
                    map.put(entry.getKey(), (Animation)PlayerAnimatorLoader.GSON.fromJson((JsonElement)entry.getValue(), Animation.class));
                    if (n != 0) break block3;
                }
                catch (Exception exception) {
                    String[] stringArray = a;
                    Aurora.LOG.warn(stringArray[5], (Object)entry.getKey(), (Object)exception.toString());
                }
                if (n == 0) continue;
            }
            Aurora.LOG.info(a[0], (Object)map.size(), (Object)string);
        }
    }

    public static boolean has(String string) {
        boolean bl = EmoteEngine.clip(string) != null;
        return bl;
    }

    
    public static void preview(AbstractClientPlayerEntity abstractClientPlayerEntity, String string, float f) {
        int n;
        Animation animation;
        block44: {
            PlayerAnimationController playerAnimationController;
            Animation animation2;
            block43: {
                animation = EmoteEngine.clip(string);
                n = EffectList.a;
                if (abstractClientPlayerEntity == null) {
                    return;
                }
                animation2 = animation;
                if (n != 0) break block43;
                if (animation2 != null) break block44;
                animation2 = LAYERS.get(abstractClientPlayerEntity);
            }
            Object object = playerAnimationController = (PlayerAnimationController)animation2;
            if (n == 0) {
                if (object == null) return;
                object = PLAYING.remove(abstractClientPlayerEntity);
            }
            if (object == null) return;
            playerAnimationController.stop();
            return;
        }
        try {
            AvatarAnimManager avatarAnimManager;
            block52: {
                PlayerAnimationController playerAnimationController;
                block51: {
                    PlayerAnimationController playerAnimationController2;
                    block50: {
                        int n2;
                        block49: {
                            int n3;
                            int n4;
                            block47: {
                                block48: {
                                    int n5;
                                    block42: {
                                        AbstractClientPlayerEntity abstractClientPlayerEntity2;
                                        playerAnimationController2 = EmoteEngine.layerFor(abstractClientPlayerEntity);
                                        if (playerAnimationController2 == null) {
                                            return;
                                        }
                                        avatarAnimManager = PlayerAnimationAccess.getPlayerAnimManager((PlayerLikeEntity)abstractClientPlayerEntity);
                                        if (avatarAnimManager == null) {
                                            return;
                                        }
                                        int n6 = (int)(f * 20.0f);
                                        int n7 = n6 - abstractClientPlayerEntity.field_6012;
                                        boolean bl = true;
                                        do {
                                            block46: {
                                                block45: {
                                                    if (!bl || (bl = false)) break block45;
                                                    if (n != 0) break block46;
                                                    if (Math.abs(n7) > 40) {
                                                        abstractClientPlayerEntity.field_6012 = n6;
                                                    }
                                                }
                                                n7 = abstractClientPlayerEntity.field_6012;
                                            }
                                            if (n7 >= n6) break;
                                            AbstractClientPlayerEntity abstractClientPlayerEntity3 = abstractClientPlayerEntity;
                                            abstractClientPlayerEntity2 = abstractClientPlayerEntity3;
                                            n5 = abstractClientPlayerEntity3.field_6012;
                                            if (n != 0) break block42;
                                            abstractClientPlayerEntity2.field_6012 = n5 + 1;
                                            avatarAnimManager.handleAnimations(0.0f, true, false);
                                        } while (n == 0);
                                        n4 = abstractClientPlayerEntity.field_6012;
                                        if (n != 0) break block47;
                                        if (n4 <= n6) break block48;
                                        abstractClientPlayerEntity2 = abstractClientPlayerEntity;
                                        n5 = n6;
                                    }
                                    abstractClientPlayerEntity2.field_6012 = n5;
                                }
                                n4 = string.equals(PLAYING.put(abstractClientPlayerEntity, string)) ? 1 : 0;
                            }
                            if (n == 0) {
                                n4 = n4 == 0 ? 1 : 0;
                            }
                            n2 = n3 = n4;
                            if (n == 0) {
                                if (n2 != 0) {
                                    playerAnimationController2.stop();
                                }
                                n2 = n3;
                            }
                            if (n != 0) break block49;
                            if (n2 != 0) break block50;
                            playerAnimationController = playerAnimationController2;
                            if (n != 0) break block51;
                            n2 = playerAnimationController.isActive() ? 1 : 0;
                        }
                        if (n2 == 0) break block50;
                        playerAnimationController = playerAnimationController2;
                        if (n != 0) break block51;
                        if (!playerAnimationController.hasAnimationFinished()) break block52;
                    }
                    playerAnimationController = playerAnimationController2;
                }
                playerAnimationController.triggerAnimation(animation);
            }
            avatarAnimManager.setTickDelta(f * 20.0f - (float)abstractClientPlayerEntity.field_6012);
            return;
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[4], (Object)string, (Object)exception.toString());
        }
    }

    private static void dropWorldPlayers() {
        PLAYING.keySet().removeIf(EmoteEngine::lambda$dropWorldPlayers$1);
        LAYERS.keySet().removeIf(EmoteEngine::lambda$dropWorldPlayers$2);
    }

    private static void dropGonePlayers(ClientWorld clientWorld) {
        LAYERS.entrySet().removeIf(arg_0 -> EmoteEngine.lambda$dropGonePlayers$3(clientWorld, arg_0));
    }

    public static void tick() {
        ClientWorld clientWorld;
        int n;
        MinecraftClient minecraftClient;
        block29: {
            block28: {
                minecraftClient = MinecraftClient.method_1551();
                n = EffectList.a;
                clientWorld = minecraftClient.field_1687;
                if (n != 0) break block28;
                if (clientWorld != null) break block29;
                EmoteEngine.dropWorldPlayers();
                lastSelf = new WeakReference<Object>(null);
            }
            return;
        }
        if (minecraftClient.field_1724 != lastSelf.get()) {
            lastSelf = new WeakReference<ClientPlayerEntity>(minecraftClient.field_1724);
            EmoteEngine.dropGonePlayers(clientWorld);
        }
        for (AbstractClientPlayerEntity abstractClientPlayerEntity : clientWorld.method_18456()) {
            block32: {
                String string;
                block33: {
                    String string2;
                    PlayerAnimationController playerAnimationController;
                    block30: {
                        block31: {
                            string = Emotes.activeId(abstractClientPlayerEntity);
                            if (Objects.equals(string, PLAYING.get(abstractClientPlayerEntity))) {
                                continue;
                            }
                            playerAnimationController = EmoteEngine.layerFor(abstractClientPlayerEntity);
                            if (playerAnimationController == null) {
                                continue;
                            }
                            string2 = string;
                            if (n != 0) break block30;
                            if (string2 != null) break block31;
                            playerAnimationController.stop();
                            PLAYING.remove(abstractClientPlayerEntity);
                            if (n == 0) break block32;
                        }
                        string2 = string;
                    }
                    Animation animation = EmoteEngine.clip(string2);
                    if (n != 0) break block33;
                    if (animation == null) {
                        continue;
                    }
                    playerAnimationController.stop();
                    playerAnimationController.triggerAnimation(animation);
                }
                PLAYING.put(abstractClientPlayerEntity, string);
            }
            if (n == 0) continue;
        }
        if (Module.a) {
            EffectList.a = ++n;
        }
    }

    private static boolean lambda$dropGonePlayers$3(ClientWorld clientWorld, Map.Entry entry) {
        block14: {
            Object object;
            AbstractClientPlayerEntity abstractClientPlayerEntity;
            block15: {
                boolean bl;
                block12: {
                    block13: {
                        abstractClientPlayerEntity = (AbstractClientPlayerEntity)entry.getKey();
                        int n = EffectList.a;
                        bl = PreviewPlayer.owns((Entity)abstractClientPlayerEntity);
                        if (n != 0) break block12;
                        if (bl) break block13;
                        object = abstractClientPlayerEntity;
                        if (n != 0) break block14;
                        if (object.method_31481()) break block15;
                        object = abstractClientPlayerEntity;
                        if (n != 0) break block14;
                        if (object.method_73183() != clientWorld) break block15;
                    }
                    bl = false;
                }
                return bl;
            }
            ((PlayerAnimationController)entry.getValue()).stop();
            object = PLAYING.remove(abstractClientPlayerEntity);
        }
        return true;
    }

    private static boolean lambda$dropWorldPlayers$2(AbstractClientPlayerEntity abstractClientPlayerEntity) {
        boolean bl;
        block2: {
            block3: {
                int n = EffectList.a;
                bl = PreviewPlayer.owns((Entity)abstractClientPlayerEntity);
                if (n != 0) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean lambda$dropWorldPlayers$1(AbstractClientPlayerEntity abstractClientPlayerEntity) {
        boolean bl;
        block2: {
            block3: {
                int n = EffectList.a;
                bl = PreviewPlayer.owns((Entity)abstractClientPlayerEntity);
                if (n != 0) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private static PlayState lambda$layerFor$0(AnimationController animationController, AnimationData animationData, AnimationController.AnimationSetter animationSetter) {
        return PlayState.STOP;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "*^\u0012\u001f61PKP\u001dP?-J\u000eF\u000f\u0004!p\t\u0007B\u0010X7yJ\u0007D\u0001\u0014!4\u0006\u0018N\u0012\u0006!4\u0005\u0007D\u0003\u0011(!*^\u0012\u001f61PKE\u000fP!=\u0005\u001fN@\u001c%)\u000f\u0019\u000b\u0006\u001f6p\u0011\u0016\u0011@\u000b9";
                var4_3 = "*^\u0012\u001f61PKP\u001dP?-J\u000eF\u000f\u0004!p\t\u0007B\u0010X7yJ\u0007D\u0001\u0014!4\u0006\u0018N\u0012\u0006!4\u0005\u0007D\u0003\u0011(!*^\u0012\u001f61PKE\u000fP!=\u0005\u001fN@\u001c%)\u000f\u0019\u000b\u0006\u001f6p\u0011\u0016\u0011@\u000b9".length();
                var1_4 = 34;
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
                    var2_2 = "*^\u0012\u001f61PKN\r\u001f05J\u001bY\u0005\u0006-5\u001dKM\u0001\u0019(5\u000eKM\u000f\u0002d+\u0017Q\u000b\u001b\r\u001b.F\u000f\u0004!p\t\u0007B\u0010Pc+\u0017L\u000b\u0013\u001b- \u001a\u000eOZP?-";
                    var4_3 = "*^\u0012\u001f61PKN\r\u001f05J\u001bY\u0005\u0006-5\u001dKM\u0001\u0019(5\u000eKM\u000f\u0002d+\u0017Q\u000b\u001b\r\u001b.F\u000f\u0004!p\t\u0007B\u0010Pc+\u0017L\u000b\u0013\u001b- \u001a\u000eOZP?-".length();
                    var1_4 = 39;
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
                        v10 = 107;
                        break;
                    }
                    case 1: {
                        v10 = 43;
                        break;
                    }
                    case 2: {
                        v10 = 96;
                        break;
                    }
                    case 3: {
                        v10 = 112;
                        break;
                    }
                    case 4: {
                        v10 = 68;
                        break;
                    }
                    case 5: {
                        v10 = 80;
                        break;
                    }
                    default: {
                        v10 = 106;
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
        EmoteEngine.a = var5;
        EmoteEngine.SERVED = new ConcurrentHashMap<String, Animation>();
        EmoteEngine.LOCAL = new ConcurrentHashMap<String, Animation>();
        EmoteEngine.TRAVERSAL = ConcurrentHashMap.newKeySet();
        EmoteEngine.LAYERS = new WeakHashMap<AbstractClientPlayerEntity, PlayerAnimationController>();
        EmoteEngine.PLAYING = new WeakHashMap<AbstractClientPlayerEntity, String>();
        EmoteEngine.lastSelf = new WeakReference<Object>(null);
    }
}
