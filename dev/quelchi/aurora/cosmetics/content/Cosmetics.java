package dev.quelchi.aurora.cosmetics.content;

import dev.quelchi.aurora.cosmetics.backend.AccountCapes;
import dev.quelchi.aurora.cosmetics.content.PremiumCapes;
import dev.quelchi.aurora.cosmetics.renderer.MaceEffects;
import dev.quelchi.aurora.cosmetics.renderer.cape.CosmeticRegistry;
import dev.quelchi.aurora.social.Badges;
import dev.quelchi.aurora.social.Badges$Badge;
import dev.quelchi.aurora.social.Plus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;

public final class Cosmetics {
    private static volatile boolean showOthers;
    private static final List<Map<UUID, ?>> REMOTE;
    private static final Map<UUID, String> CAPES;
    private static volatile String ownKillEffect;
    private static final Map<UUID, String> KILL_FX;
    private static volatile String ownHitEffect;
    private static final Map<UUID, String> HIT_FX;
    private static volatile String ownMaceEffect;
    private static volatile String ownTrail;
    private static final Map<UUID, String> TRAILS;
    private static final Map<UUID, String> WINGS;
    private static volatile String ownWings;
    private static volatile Map<String, Set<String>> DISABLED;
    private static volatile Map<String, Set<String>> LOCKED;
    private static volatile Map<String, Set<String>> PLUS_ONLY;
    public static final String[] RARITIES;
    private static volatile long shards;
    private static volatile Set<String> owned;
    private static volatile boolean ownsAll;
    private static volatile Set<String> sold;
    private static volatile Map<String, Map<String, String>> rarity;
    private static volatile Map<String, Integer> prices;
    private static volatile Set<String> closed;
    private static volatile Map<String, Integer> deals;
    private static volatile Map<String, Map<String, Long>> released;
    private static volatile long newFor;
    private static volatile String tryCat;
    private static volatile String tryId;
    private static volatile long tryUntil;
    public static final int WHEEL_SLOTS;
    private static final String[] WHEEL;
    private static volatile boolean creator;
    private static final Map<UUID, String> EMOTES;
    private static volatile String ownEmote;
    private static final Map<UUID, Long> PLAYTIME;
    private static final Map<UUID, Long> ONLINE_FOR;
    private static final Map<UUID, Long> FIRST_SEEN;
    private static final Map<UUID, Integer> FRIENDS;
    private static final Map<UUID, Boolean> USERS;
    private static final Map<UUID, String> ROLES;
    private static final Map<UUID, Boolean> PLUS;
    public static final String NO_CAPE;
    private static volatile String ownCape;
    private static volatile String chosenBetaCape;
    private static volatile String ownCapeUrl;
    private static final Map<String, String> CAPE_IDS;
    public static final String[] RANKS;
    public static int a;
    private static final String[] b;
    private static final long c;

    private Cosmetics() {
    }

    public static boolean showOthers() {
        return showOthers;
    }

    public static void setShowOthers(boolean bl) {
        showOthers = bl;
    }

    private static <V> Map<UUID, V> remote() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        REMOTE.add(concurrentHashMap);
        return concurrentHashMap;
    }

    public static String getOwnKillEffect() {
        return ownKillEffect;
    }

    public static void setOwnKillEffect(String string) {
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
        ownKillEffect = string2;
    }

    public static String killEffectFor(UUID uUID, boolean bl) {
        boolean bl2;
        block7: {
            block8: {
                int n = a;
                bl2 = bl;
                if (n != 0) break block7;
                if (!bl2) break block8;
                return ownKillEffect;
            }
            bl2 = showOthers;
        }
        if (!bl2) {
            return "";
        }
        return KILL_FX.getOrDefault(uUID, "");
    }

    
    public static void setRemoteKillEffect(UUID uUID, String string) {
        block12: {
            String string2;
            block13: {
                int n;
                block10: {
                    String string3;
                    block11: {
                        n = a;
                        if (uUID == null) {
                            return;
                        }
                        string3 = string;
                        if (n != 0) break block10;
                        if (string3 == null) break block11;
                        string2 = string;
                        if (n != 0) break block12;
                        if (!string2.isEmpty()) break block13;
                    }
                    string3 = KILL_FX.remove(uUID);
                }
                if (n == 0) break block12;
            }
            string2 = KILL_FX.put(uUID, string);
        }
    }

    public static String getOwnHitEffect() {
        return ownHitEffect;
    }

    public static void setOwnHitEffect(String string) {
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
        ownHitEffect = string2;
    }

    public static String hitEffectFor(UUID uUID, boolean bl) {
        boolean bl2;
        block7: {
            block8: {
                int n = a;
                bl2 = bl;
                if (n != 0) break block7;
                if (!bl2) break block8;
                return ownHitEffect;
            }
            bl2 = showOthers;
        }
        if (!bl2) {
            return "";
        }
        return HIT_FX.getOrDefault(uUID, "");
    }

    
    public static void setRemoteHitEffect(UUID uUID, String string) {
        block12: {
            String string2;
            block13: {
                int n;
                block10: {
                    String string3;
                    block11: {
                        n = a;
                        if (uUID == null) {
                            return;
                        }
                        string3 = string;
                        if (n != 0) break block10;
                        if (string3 == null) break block11;
                        string2 = string;
                        if (n != 0) break block12;
                        if (!string2.isEmpty()) break block13;
                    }
                    string3 = HIT_FX.remove(uUID);
                }
                if (n == 0) break block12;
            }
            string2 = HIT_FX.put(uUID, string);
        }
    }

    public static String getOwnMaceEffect() {
        return ownMaceEffect;
    }

    public static void setOwnMaceEffect(String string) {
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
        ownMaceEffect = string2;
    }

    public static String getOwnTrail() {
        return ownTrail;
    }

    public static void setOwnTrail(String string) {
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
        ownTrail = string2;
    }

    public static String trailFor(UUID uUID, boolean bl) {
        boolean bl2;
        block7: {
            block8: {
                int n = a;
                bl2 = bl;
                if (n != 0) break block7;
                if (!bl2) break block8;
                return Cosmetics.shownTrail();
            }
            bl2 = showOthers;
        }
        if (!bl2) {
            return "";
        }
        return TRAILS.getOrDefault(uUID, "");
    }

    
    public static void setRemoteTrail(UUID uUID, String string) {
        block12: {
            String string2;
            block13: {
                int n;
                block10: {
                    String string3;
                    block11: {
                        n = a;
                        if (uUID == null) {
                            return;
                        }
                        string3 = string;
                        if (n != 0) break block10;
                        if (string3 == null) break block11;
                        string2 = string;
                        if (n != 0) break block12;
                        if (!string2.isEmpty()) break block13;
                    }
                    string3 = TRAILS.remove(uUID);
                }
                if (n == 0) break block12;
            }
            string2 = TRAILS.put(uUID, string);
        }
    }

    public static String getOwnWings() {
        return ownWings;
    }

    public static void setOwnWings(String string) {
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
        ownWings = string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String wingsFor(UUID uUID) {
        boolean bl;
        block14: {
            block13: {
                MinecraftClient minecraftClient;
                int n;
                block12: {
                    MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                    n = a;
                    if (Cosmetics.isMe(uUID)) return Cosmetics.shownWings();
                    minecraftClient = minecraftClient2;
                    if (n != 0) break block12;
                    if (minecraftClient.field_1724 == null) break block13;
                    minecraftClient = minecraftClient2;
                }
                bl = minecraftClient.field_1724.method_5667().equals(uUID);
                if (n != 0) break block14;
                if (!bl) break block13;
                return Cosmetics.shownWings();
            }
            bl = showOthers;
        }
        if (bl) return WINGS.get(uUID);
        return null;
    }

    
    public static void setRemoteWings(UUID uUID, String string) {
        block12: {
            String string2;
            block13: {
                int n;
                block10: {
                    String string3;
                    block11: {
                        n = a;
                        if (uUID == null) {
                            return;
                        }
                        string3 = string;
                        if (n != 0) break block10;
                        if (string3 == null) break block11;
                        string2 = string;
                        if (n != 0) break block12;
                        if (!string2.isBlank()) break block13;
                    }
                    string3 = WINGS.remove(uUID);
                }
                if (n == 0) break block12;
            }
            string2 = WINGS.put(uUID, string);
        }
    }

    public static void setDisabled(Map<String, Set<String>> map) {
        Map<String, Set<String>> map2;
        block2: {
            block3: {
                int n = a;
                map2 = map;
                if (n != 0) break block2;
                if (map2 != null) break block3;
                map2 = Map.of();
                break block2;
            }
            map2 = map;
        }
        DISABLED = map2;
    }

    public static void setLocked(Map<String, Set<String>> map) {
        Map<String, Set<String>> map2;
        block2: {
            block3: {
                int n = a;
                map2 = map;
                if (n != 0) break block2;
                if (map2 != null) break block3;
                map2 = Map.of();
                break block2;
            }
            map2 = map;
        }
        LOCKED = map2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean offered(String string, String string2) {
        boolean bl;
        block7: {
            Set<String> set;
            int n;
            block6: {
                Set<String> set2 = DISABLED.get(string);
                n = a;
                set = set2;
                if (n != 0) break block6;
                if (set == null) return true;
                set = set2;
            }
            bl = set.contains(string2);
            if (n != 0) return bl;
            if (bl) break block7;
            return true;
        }
        bl = Cosmetics.grantOnly(string, string2);
        return bl;
    }

    public static boolean grantOnly(String string, String string2) {
        boolean bl;
        block6: {
            block5: {
                Set<String> set;
                int n;
                block4: {
                    Set<String> set2 = LOCKED.get(string);
                    n = a;
                    set = set2;
                    if (n != 0) break block4;
                    if (set == null) break block5;
                    set = set2;
                }
                bl = set.contains(string2);
                if (n != 0) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    public static void setPlusOnly(Map<String, Set<String>> map) {
        Map<String, Set<String>> map2;
        block2: {
            block3: {
                int n = a;
                map2 = map;
                if (n != 0) break block2;
                if (map2 != null) break block3;
                map2 = Map.of();
                break block2;
            }
            map2 = map;
        }
        PLUS_ONLY = map2;
    }

    public static boolean plusOnly(String string, String string2) {
        boolean bl;
        block6: {
            block5: {
                Set<String> set;
                int n;
                block4: {
                    Set<String> set2 = PLUS_ONLY.get(string);
                    n = a;
                    set = set2;
                    if (n != 0) break block4;
                    if (set == null) break block5;
                    set = set2;
                }
                bl = set.contains(string2);
                if (n != 0) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    public static void setShop(long l, Set<String> set, boolean bl, Set<String> set2, Map<String, Map<String, String>> map, Map<String, Integer> map2) {
        shards = l;
        owned = set;
        ownsAll = bl;
        sold = set2;
        rarity = map;
        prices = map2;
    }

    public static void setShards(long l) {
        shards = l;
    }

    public static void clearOwnership() {
        shards = 0L;
        owned = Set.of();
        ownsAll = false;
        deals = Map.of();
    }

    public static void setOwned(Set<String> set) {
        owned = set;
    }

    public static long shards() {
        return shards;
    }

    public static void setClosed(Set<String> set) {
        closed = set;
    }

    public static String rarity(String string, String string2) {
        String string3;
        block6: {
            Object object;
            block4: {
                Map<String, String> map;
                block5: {
                    map = rarity.get(string);
                    int n = a;
                    object = map;
                    if (n != 0) break block4;
                    if (object != null) break block5;
                    string3 = b[10];
                    break block6;
                }
                object = map.getOrDefault(string2, b[14]);
            }
            string3 = (String)object;
        }
        return string3;
    }

    public static boolean forSale(String string, String string2) {
        boolean bl;
        block14: {
            block12: {
                Map map;
                int n;
                block13: {
                    Object object;
                    block10: {
                        block11: {
                            n = a;
                            object = sold;
                            if (n != 0) break block10;
                            if (!object.contains(string)) break block11;
                            return true;
                        }
                        object = rarity.get(string);
                    }
                    Map map2 = (Map)object;
                    if (!b[20].equals(string)) break block12;
                    map = map2;
                    if (n != 0) break block13;
                    if (map == null) break block12;
                    map = map2;
                }
                bl = map.containsKey(string2);
                if (n != 0) break block14;
                if (!bl) break block12;
                bl = true;
                break block14;
            }
            bl = false;
        }
        return bl;
    }

    public static int price(String string, String string2) {
        Integer n;
        block6: {
            Integer n2;
            block4: {
                block5: {
                    Integer n3 = deals.get(string + ":" + string2);
                    int n4 = a;
                    n2 = n3;
                    if (n4 != 0) break block4;
                    if (n2 == null) break block5;
                    n = n3;
                    break block6;
                }
                n2 = prices.getOrDefault(Cosmetics.rarity(string, string2), 0);
            }
            n = n2;
        }
        return n;
    }

    public static void setDeals(Map<String, Integer> map) {
        deals = map;
    }

    public static boolean owns(String string, String string2) {
        boolean bl;
        block12: {
            block15: {
                block13: {
                    int n;
                    block14: {
                        n = a;
                        bl = ownsAll;
                        if (n != 0) break block12;
                        if (bl) break block13;
                        bl = owned.contains(string + ":" + string2);
                        if (n != 0) break block12;
                        if (bl) break block13;
                        bl = Cosmetics.plusOnly(string, string2);
                        if (n != 0) break block14;
                        if (!bl) break block15;
                        bl = Plus.active();
                    }
                    if (n != 0) break block12;
                    if (!bl) break block15;
                }
                bl = true;
                break block12;
            }
            bl = false;
        }
        return bl;
    }

    public static void setReleased(Map<String, Map<String, Long>> map, long l) {
        released = map;
        newFor = l;
    }

    public static long released(String string, String string2) {
        long l;
        block6: {
            Object object;
            block4: {
                Map<String, Long> map;
                block5: {
                    map = released.get(string);
                    int n = a;
                    object = map;
                    if (n != 0) break block4;
                    if (object != null) break block5;
                    l = 0L;
                    break block6;
                }
                object = map.getOrDefault(string2, 0L);
            }
            l = (Long)object;
        }
        return l;
    }

    public static boolean isNew(String string, String string2) {
        long l;
        block8: {
            block7: {
                long l2;
                int n;
                block6: {
                    long l3 = Cosmetics.released(string, string2);
                    n = a;
                    l2 = l3;
                    if (n != 0) break block6;
                    if (l2 <= 0L) break block7;
                    l2 = System.currentTimeMillis() / c - l3;
                }
                long l4 = l2 - newFor;
                l = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                if (n != 0) break block8;
                if (l >= 0) break block7;
                l = 1;
                break block8;
            }
            l = 0;
        }
        return (boolean)l;
    }

    private static String shown(String string, String string2) {
        String string3;
        block8: {
            block7: {
                int n;
                block6: {
                    int n2 = a;
                    n = string.equals(tryCat);
                    if (n2 != 0) break block6;
                    if (n == 0) break block7;
                    long l = System.currentTimeMillis() - tryUntil;
                    n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                }
                if (n >= 0) break block7;
                string3 = tryId;
                break block8;
            }
            string3 = string2;
        }
        return string3;
    }

    private static boolean tryingOn(String string) {
        int n;
        block8: {
            block7: {
                int n2;
                block6: {
                    n2 = a;
                    n = string.equals(tryCat);
                    if (n2 != 0) break block6;
                    if (n == 0) break block7;
                    long l = System.currentTimeMillis() - tryUntil;
                    n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                }
                if (n2 != 0) break block8;
                if (n >= 0) break block7;
                n = 1;
                break block8;
            }
            n = 0;
        }
        return n != 0;
    }

    public static String shownWings() {
        return Cosmetics.shown(b[27], ownWings);
    }

    public static String shownTrail() {
        return Cosmetics.shown(b[15], ownTrail);
    }

    public static String shownCape() {
        return Cosmetics.shown(b[3], ownCape);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String wheelSlot(int n) {
        String[] stringArray;
        block9: {
            block10: {
                int n2;
                int n3;
                block8: {
                    n3 = a;
                    n2 = n;
                    if (n3 != 0) break block8;
                    if (n2 < 0) return "";
                    n2 = n;
                }
                if (n2 >= 8) return "";
                stringArray = WHEEL;
                if (n3 != 0) break block9;
                if (stringArray[n] != null) break block10;
                return "";
            }
            stringArray = WHEEL;
        }
        String string = stringArray[n];
        return string;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void setWheelSlot(int n, String string) {
        String string2;
        block8: {
            block9: {
                int n2;
                int n3;
                block7: {
                    n3 = a;
                    n2 = n;
                    if (n3 != 0) break block7;
                    if (n2 < 0) return;
                    n2 = n;
                }
                if (n2 >= 8) {
                    return;
                }
                String[] stringArray = WHEEL;
                int n4 = n;
                string2 = string;
                if (n3 != 0) break block8;
                if (string2 != null) break block9;
                string2 = "";
                break block8;
            }
            string2 = string;
        }
        stringArray[n4] = string2;
    }

    public static boolean locked(String string, String string2) {
        boolean bl;
        block16: {
            block18: {
                block17: {
                    String string3;
                    String string4;
                    int n;
                    block14: {
                        block15: {
                            n = a;
                            string4 = string;
                            string3 = string2;
                            if (n != 0) break block14;
                            if (!Cosmetics.owns(string4, string3)) break block15;
                            return false;
                        }
                        string4 = string;
                        string3 = string2;
                    }
                    bl = Cosmetics.grantOnly(string4, string3);
                    if (n != 0) break block16;
                    if (bl) break block17;
                    bl = Cosmetics.forSale(string, string2);
                    if (n != 0) break block16;
                    if (bl) break block17;
                    bl = Cosmetics.plusOnly(string, string2);
                    if (n != 0) break block16;
                    if (!bl) break block18;
                }
                bl = true;
                break block16;
            }
            bl = false;
        }
        return bl;
    }

    public static void setCreator(boolean bl) {
        creator = bl;
    }

    public static String getOwnEmote() {
        return ownEmote;
    }

    public static void setOwnEmote(String string) {
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
        ownEmote = string2;
    }

    public static String emoteFor(UUID uUID) {
        boolean bl;
        block13: {
            block12: {
                MinecraftClient minecraftClient;
                int n;
                block11: {
                    MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                    n = a;
                    minecraftClient = minecraftClient2;
                    if (n != 0) break block11;
                    if (minecraftClient.field_1724 == null) break block12;
                    minecraftClient = minecraftClient2;
                }
                bl = minecraftClient.field_1724.method_5667().equals(uUID);
                if (n != 0) break block13;
                if (!bl) break block12;
                return ownEmote;
            }
            bl = showOthers;
        }
        if (!bl) {
            return null;
        }
        return EMOTES.get(uUID);
    }

    
    public static void setRemoteEmote(UUID uUID, String string) {
        block12: {
            String string2;
            block13: {
                int n;
                block10: {
                    String string3;
                    block11: {
                        n = a;
                        if (uUID == null) {
                            return;
                        }
                        string3 = string;
                        if (n != 0) break block10;
                        if (string3 == null) break block11;
                        string2 = string;
                        if (n != 0) break block12;
                        if (!string2.isBlank()) break block13;
                    }
                    string3 = EMOTES.remove(uUID);
                }
                if (n == 0) break block12;
            }
            string2 = EMOTES.put(uUID, string);
        }
    }

    public static void setRemoteOnlineFor(UUID uUID, long l) {
        block8: {
            block7: {
                int n = a;
                if (uUID == null) {
                    return;
                }
                if (l > 0L) break block7;
                ONLINE_FOR.remove(uUID);
                if (n == 0) break block8;
            }
            ONLINE_FOR.put(uUID, l);
        }
    }

    public static void setRemoteFirstSeen(UUID uUID, long l) {
        block8: {
            block7: {
                int n = a;
                if (uUID == null) {
                    return;
                }
                if (l > 0L) break block7;
                FIRST_SEEN.remove(uUID);
                if (n == 0) break block8;
            }
            FIRST_SEEN.put(uUID, l);
        }
    }

    public static void setRemoteFriends(UUID uUID, int n) {
        block8: {
            block7: {
                int n2 = a;
                if (uUID == null) {
                    return;
                }
                if (n > 0) break block7;
                FRIENDS.remove(uUID);
                if (n2 == 0) break block8;
            }
            FRIENDS.put(uUID, n);
        }
    }

    public static void setRemotePlaytime(UUID uUID, long l) {
        block8: {
            block7: {
                int n = a;
                if (uUID == null) {
                    return;
                }
                if (l > 0L) break block7;
                PLAYTIME.remove(uUID);
                if (n == 0) break block8;
            }
            PLAYTIME.put(uUID, l);
        }
    }

    public static String roleOf(UUID uUID) {
        String string;
        block6: {
            Object object;
            block4: {
                block5: {
                    int n = a;
                    object = uUID;
                    if (n != 0) break block4;
                    if (object != null) break block5;
                    string = "";
                    break block6;
                }
                object = ROLES.getOrDefault(uUID, "");
            }
            string = (String)object;
        }
        return string;
    }

    public static boolean isStaff(UUID uUID) {
        boolean bl;
        block41: {
            block40: {
                List<Badges$Badge> list;
                int n;
                block39: {
                    boolean bl2;
                    block37: {
                        block38: {
                            boolean bl3;
                            block35: {
                                block36: {
                                    String string = Cosmetics.roleOf(uUID);
                                    n = a;
                                    bl3 = b[9].equals(string);
                                    if (n != 0) break block35;
                                    if (bl3) break block36;
                                    bl3 = b[25].equals(string);
                                    if (n != 0) break block35;
                                    if (bl3) break block36;
                                    bl3 = b[26].equals(string);
                                    if (n != 0) break block35;
                                    if (bl3) break block36;
                                    bl2 = b[24].equals(string);
                                    if (n != 0) break block37;
                                    if (!bl2) break block38;
                                }
                                bl3 = true;
                            }
                            return bl3;
                        }
                        bl2 = Cosmetics.isMe(uUID);
                    }
                    List<Badges$Badge> list2 = bl2 ? Badges.mine() : null;
                    List<Badges$Badge> list3 = list2;
                    list = list3;
                    if (n != 0) break block39;
                    if (list == null) break block40;
                    list = list3;
                }
                for (Badges$Badge badges$Badge : list) {
                    block44: {
                        boolean bl4;
                        block42: {
                            block43: {
                                bl = b[6].equalsIgnoreCase(badges$Badge.id());
                                if (n != 0) break block41;
                                if (n != 0) break block42;
                                if (bl) break block43;
                                bl4 = b[21].equalsIgnoreCase(badges$Badge.id());
                                if (n != 0) break block42;
                                if (bl4) break block43;
                                bl4 = b[0].equalsIgnoreCase(badges$Badge.id());
                                if (n != 0) break block42;
                                if (bl4) break block43;
                                bl4 = b[12].equalsIgnoreCase(badges$Badge.id());
                                if (n != 0) break block42;
                                if (!bl4) break block44;
                            }
                            bl4 = true;
                        }
                        return bl4;
                    }
                    if (n == 0) continue;
                }
            }
            bl = false;
        }
        return bl;
    }

    public static boolean hasBeta(UUID uUID) {
        boolean bl;
        block29: {
            block28: {
                List<Badges$Badge> list;
                int n;
                block27: {
                    boolean bl2;
                    block25: {
                        block26: {
                            block23: {
                                block24: {
                                    UUID uUID2;
                                    block21: {
                                        block22: {
                                            n = a;
                                            uUID2 = uUID;
                                            if (n != 0) break block21;
                                            if (uUID2 != null) break block22;
                                            return false;
                                        }
                                        uUID2 = uUID;
                                    }
                                    bl2 = Cosmetics.isStaff(uUID2);
                                    if (n != 0) break block23;
                                    if (!bl2) break block24;
                                    return true;
                                }
                                String[] stringArray = b;
                                bl2 = stringArray[5].equalsIgnoreCase(Cosmetics.roleOf(uUID));
                            }
                            if (n != 0) break block25;
                            if (!bl2) break block26;
                            return true;
                        }
                        bl2 = Cosmetics.isMe(uUID);
                    }
                    List<Badges$Badge> list2 = bl2 ? Badges.mine() : null;
                    List<Badges$Badge> list3 = list2;
                    list = list3;
                    if (n != 0) break block27;
                    if (list == null) break block28;
                    list = list3;
                }
                for (Badges$Badge badges$Badge : list) {
                    block31: {
                        boolean bl3;
                        block30: {
                            bl = b[22].equalsIgnoreCase(badges$Badge.id());
                            if (n != 0) break block29;
                            if (n != 0) break block30;
                            if (!bl) break block31;
                            bl3 = true;
                        }
                        return bl3;
                    }
                    if (n == 0) continue;
                }
            }
            bl = false;
        }
        return bl;
    }

    private static boolean isMe(UUID uUID) {
        boolean bl;
        block7: {
            block6: {
                MinecraftClient minecraftClient = MinecraftClient.method_1551();
                int n = a;
                if (uUID == null || minecraftClient.method_1548() == null) break block6;
                bl = uUID.equals(minecraftClient.method_1548().method_44717());
                if (n != 0) break block7;
                if (!bl) break block6;
                bl = true;
                break block7;
            }
            bl = false;
        }
        return bl;
    }

    public static void setRemotePlus(UUID uUID, boolean bl) {
        block8: {
            block7: {
                int n = a;
                if (uUID == null) {
                    return;
                }
                if (!bl) break block7;
                PLUS.put(uUID, true);
                if (n == 0) break block8;
            }
            PLUS.remove(uUID);
        }
    }

    public static boolean plusOf(UUID uUID) {
        boolean bl;
        block10: {
            block11: {
                UUID uUID2;
                int n;
                block8: {
                    block9: {
                        n = a;
                        uUID2 = uUID;
                        if (n != 0) break block8;
                        if (!Cosmetics.isMe(uUID2)) break block9;
                        bl = Plus.active();
                        break block10;
                    }
                    uUID2 = uUID;
                }
                if (uUID2 == null) break block11;
                bl = PLUS.containsKey(uUID);
                if (n != 0) break block10;
                if (!bl) break block11;
                bl = true;
                break block10;
            }
            bl = false;
        }
        return bl;
    }

    
    public static void setRemoteRole(UUID uUID, String string) {
        block12: {
            String string2;
            block13: {
                int n;
                block10: {
                    String string3;
                    block11: {
                        n = a;
                        if (uUID == null) {
                            return;
                        }
                        string3 = string;
                        if (n != 0) break block10;
                        if (string3 == null) break block11;
                        string2 = string;
                        if (n != 0) break block12;
                        if (!string2.isEmpty()) break block13;
                    }
                    string3 = ROLES.remove(uUID);
                }
                if (n == 0) break block12;
            }
            string2 = ROLES.put(uUID, string);
        }
    }

    public static String getChosenBetaCape() {
        String string;
        block8: {
            block9: {
                int n = a;
                string = chosenBetaCape;
                if (n != 0) break block8;
                if (!string.isEmpty()) break block9;
                string = ownCape;
                if (n != 0) break block8;
                if (!PremiumCapes.isBetaCape(string)) break block9;
                chosenBetaCape = ownCape;
            }
            string = chosenBetaCape;
        }
        return string;
    }

    public static void setChosenBetaCape(String string) {
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
        chosenBetaCape = string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isCapeLocked(UUID uUID, String string, boolean bl) {
        String string2;
        String string3;
        int n;
        block91: {
            block92: {
                block90: {
                    boolean bl2;
                    block88: {
                        block89: {
                            block86: {
                                block87: {
                                    String string4;
                                    block84: {
                                        block85: {
                                            String string5;
                                            block81: {
                                                block82: {
                                                    boolean bl3;
                                                    block83: {
                                                        boolean bl4;
                                                        block80: {
                                                            block79: {
                                                                UUID uUID2;
                                                                block78: {
                                                                    block77: {
                                                                        boolean bl5;
                                                                        block75: {
                                                                            block76: {
                                                                                block73: {
                                                                                    block74: {
                                                                                        String string6;
                                                                                        block71: {
                                                                                            block72: {
                                                                                                String string7;
                                                                                                block70: {
                                                                                                    n = a;
                                                                                                    string7 = string;
                                                                                                    if (n != 0) break block70;
                                                                                                    if (string7 == null) return false;
                                                                                                    string7 = string;
                                                                                                }
                                                                                                boolean bl6 = string7.isEmpty();
                                                                                                if (n != 0) return bl6;
                                                                                                if (bl6) return false;
                                                                                                string6 = b[16];
                                                                                                if (n != 0) break block71;
                                                                                                if (!string6.equals(string)) break block72;
                                                                                                return false;
                                                                                            }
                                                                                            string6 = string;
                                                                                        }
                                                                                        bl5 = AccountCapes.isAccount(string6);
                                                                                        if (n != 0) break block73;
                                                                                        if (!bl5) break block74;
                                                                                        boolean bl7 = bl;
                                                                                        if (n != 0) return bl7;
                                                                                        if (!bl7) return true;
                                                                                        if (AccountCapes.find(string) != null) return false;
                                                                                        return true;
                                                                                    }
                                                                                    bl5 = PremiumCapes.ids().contains(string);
                                                                                }
                                                                                if (n != 0) break block75;
                                                                                if (bl5) break block76;
                                                                                return true;
                                                                            }
                                                                            String[] stringArray = b;
                                                                            bl5 = stringArray[2].equals(string);
                                                                        }
                                                                        if (n != 0) return bl5;
                                                                        if (!bl5) break block77;
                                                                        return false;
                                                                    }
                                                                    uUID2 = uUID;
                                                                    if (n != 0) break block78;
                                                                    if (uUID2 == null) break block79;
                                                                    uUID2 = uUID;
                                                                }
                                                                bl4 = Cosmetics.isStaff(uUID2);
                                                                if (n != 0) break block80;
                                                                if (!bl4) break block79;
                                                                return false;
                                                            }
                                                            string4 = b[3];
                                                            string5 = string;
                                                            if (n != 0) break block81;
                                                            bl4 = Cosmetics.plusOnly(string4, string5);
                                                        }
                                                        if (!bl4) break block82;
                                                        bl3 = bl;
                                                        if (n != 0) break block83;
                                                        if (!bl3) return false;
                                                        bl3 = Cosmetics.owns(b[3], string);
                                                    }
                                                    if (n != 0) return bl3;
                                                    if (bl3) return false;
                                                    return true;
                                                }
                                                string4 = b[3];
                                                if (n != 0) break block84;
                                                string5 = string;
                                            }
                                            if (!Cosmetics.forSale(string4, string5)) break block85;
                                            boolean bl8 = bl;
                                            if (n != 0) return bl8;
                                            if (!bl8) return false;
                                            string4 = b[3];
                                            if (n != 0) break block84;
                                            if (!Cosmetics.owns(string4, string)) break block85;
                                            return false;
                                        }
                                        string4 = string;
                                    }
                                    bl2 = PremiumCapes.isBetaCape(string4);
                                    if (n != 0) break block86;
                                    if (bl2) break block87;
                                    return true;
                                }
                                bl2 = Cosmetics.hasBeta(uUID);
                            }
                            if (n != 0) break block88;
                            if (bl2) break block89;
                            return true;
                        }
                        bl2 = bl;
                    }
                    if (n != 0) return bl2;
                    if (bl2) break block90;
                    return false;
                }
                string3 = Cosmetics.getChosenBetaCape();
                string2 = string3;
                if (n != 0) break block91;
                if (!string2.isEmpty()) break block92;
                return false;
            }
            string2 = string;
        }
        boolean bl9 = string2.equals(string3);
        if (n != 0) return bl9;
        if (bl9) return false;
        return true;
    }

    public static String getOwnCape() {
        return ownCape;
    }

    public static String getOwnCapeUrl() {
        return ownCapeUrl;
    }

    public static void setOwnCapeUrl(String string) {
        block13: {
            String string2;
            int n;
            block12: {
                String string3;
                block10: {
                    block11: {
                        n = a;
                        string3 = string;
                        if (n != 0) break block10;
                        if (string3 != null) break block11;
                        string3 = "";
                        break block10;
                    }
                    string3 = string;
                }
                ownCapeUrl = string3;
                string2 = ownCape;
                if (n != 0) break block12;
                if (string2.isBlank()) break block13;
                string2 = ownCapeUrl;
            }
            if (n != 0 || string2.isBlank()) break block13;
            string2 = CAPE_IDS.put(ownCape, ownCapeUrl);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void setCapeId(String string, String string2) {
        String string3;
        block21: {
            int n;
            block19: {
                block20: {
                    block18: {
                        block17: {
                            block16: {
                                n = a;
                                string3 = string;
                                if (n != 0) break block16;
                                if (string3 == null) return;
                                string3 = string;
                            }
                            if (n != 0) break block17;
                            if (string3.isBlank()) return;
                            string3 = string2;
                        }
                        if (n != 0) break block18;
                        if (string3 == null) return;
                        string3 = string2;
                    }
                    if (n != 0) break block19;
                    if (!string3.isBlank()) break block20;
                    return;
                }
                CAPE_IDS.put(string, string2);
                string3 = string;
            }
            if (n != 0) break block21;
            if (!string3.equals(ownCape)) return;
            string3 = string2;
        }
        ownCapeUrl = string3;
    }

    public static Map<String, String> capeIds() {
        return CAPE_IDS;
    }

    public static void setOwnCape(String string) {
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
        ownCape = string2;
        ownCapeUrl = CAPE_IDS.getOrDefault(ownCape, "");
    }

    
    public static void setRemote(UUID uUID, String string, boolean bl) {
        int n;
        block15: {
            String string2;
            block16: {
                block13: {
                    String string3;
                    block14: {
                        n = a;
                        if (uUID == null) {
                            return;
                        }
                        string3 = string;
                        if (n != 0) break block13;
                        if (string3 == null) break block14;
                        string2 = string;
                        if (n != 0) break block15;
                        if (!string2.isBlank()) break block16;
                    }
                    string3 = CAPES.remove(uUID);
                }
                if (n == 0) break block15;
            }
            string2 = CAPES.put(uUID, string);
        }
        if (bl) {
            USERS.put(uUID, Boolean.TRUE);
            if (n == 0) return;
        }
        USERS.remove(uUID);
    }

    public static void clearRemote() {
        MaceEffects.clearRemote();
        REMOTE.forEach(Map::clear);
    }

    public static String capeFor(UUID uUID, boolean bl) {
        String string;
        block24: {
            UUID uUID2;
            String string2;
            block26: {
                block25: {
                    boolean bl2;
                    block22: {
                        int n;
                        block23: {
                            String string3;
                            block21: {
                                boolean bl3;
                                block19: {
                                    block20: {
                                        n = a;
                                        bl3 = bl;
                                        if (n != 0) break block19;
                                        if (!bl3) break block20;
                                        string3 = Cosmetics.shownCape();
                                        break block21;
                                    }
                                    bl3 = showOthers;
                                }
                                string3 = bl3 ? CAPES.get(uUID) : null;
                            }
                            string2 = string3;
                            bl2 = bl;
                            if (n != 0) break block22;
                            if (!bl2) break block23;
                            string = b[3];
                            if (n != 0) break block24;
                            if (Cosmetics.tryingOn(string)) break block25;
                        }
                        uUID2 = uUID;
                        if (n != 0) break block26;
                        bl2 = Cosmetics.isCapeLocked(uUID2, string2, bl);
                    }
                    if (bl2) {
                        return null;
                    }
                }
                uUID2 = uUID;
            }
            string = Cosmetics.variant(uUID2, string2, bl);
        }
        return string;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String rankOf(UUID uUID, boolean bl) {
        String string = Cosmetics.roleOf(uUID);
        int n = a;
        List<Badges$Badge> list = bl ? Badges.mine() : Badges.of(uUID);
        List<Badges$Badge> list2 = list;
        String[] stringArray = RANKS;
        int n2 = stringArray.length;
        int n3 = 0;
        do {
            if (n3 >= n2) return "";
            String string2 = stringArray[n3];
            if (n != 0) return string2;
            String string3 = string2;
            String string4 = string3;
            if (n != 0) return string4;
            String string5 = string;
            block11: while (true) {
                List<Badges$Badge> list3;
                block14: {
                    if (string4.equals(string5)) {
                        string4 = string3;
                        return string4;
                    }
                    list3 = list2;
                    if (n != 0) break block14;
                    if (list3 == null) break;
                    list3 = list2;
                }
                for (Badges$Badge badges$Badge : list3) {
                    block15: {
                        String string6 = string3;
                        if (n != 0) return string6;
                        string5 = badges$Badge.id();
                        if (n != 0) continue block11;
                        if (!string6.equalsIgnoreCase(string5)) break block15;
                        string6 = string3;
                        return string6;
                    }
                    if (n == 0) continue;
                }
                break;
            }
            ++n3;
        } while (n == 0);
        return "";
    }

    public static String variant(UUID uUID, String string, boolean bl) {
        Object object;
        block16: {
            String string2;
            block17: {
                String string3;
                block14: {
                    block15: {
                        String string4;
                        int n;
                        block12: {
                            block13: {
                                n = a;
                                string4 = b[11];
                                if (n != 0) break block12;
                                if (string4.equals(string)) break block13;
                                return string;
                            }
                            string4 = Cosmetics.rankOf(uUID, bl);
                        }
                        string2 = string4;
                        string3 = string2;
                        if (n != 0) break block14;
                        if (string3.isEmpty()) break block15;
                        object = string2;
                        if (n != 0) break block16;
                        if (CosmeticRegistry.contains(b[18] + (String)object)) break block17;
                    }
                    string3 = string;
                }
                return string3;
            }
            object = b[4] + string2;
        }
        return object;
    }

    public static boolean isAuroraUser(UUID uUID, boolean bl) {
        boolean bl2;
        block6: {
            block8: {
                block7: {
                    int n = a;
                    bl2 = bl;
                    if (n != 0) break block6;
                    if (bl2) break block7;
                    bl2 = USERS.containsKey(uUID);
                    if (n != 0) break block6;
                    if (!bl2) break block8;
                }
                bl2 = true;
                break block6;
            }
            bl2 = false;
        }
        return bl2;
    }

    
    static {
        var11 = new String[28];
        var9_1 = 0;
        var8_2 = "|2A$:\tq3K(:\u001bfo/\u0006|#^\"&\u001e\u0004~7\\(\u0007|#^\"&\u001eX\u0004\u007f3X,\u0005n\"M+2\u0007m7^9:\u001au\u0005p3H$5\u0005n\"M+2\u0006~9A ;\u0011\u0006|#^\"&\u001e\u0005r!B(&\t\u007f#K91\fsx$\u0006~9A ;\u0011\u0005i$M$8\u000608C#1R\bh8O\"9\u0012hs\u0007|#^\"&\u001eX\u000608C#1R\u0004~7\\(\u0007p7B,3\u001au\u0004\u007f3X,\u0004o7^(\u0005r!B(&\u0007p7B,3\u001au";
        var10_3 = "|2A$:\tq3K(:\u001bfo/\u0006|#^\"&\u001e\u0004~7\\(\u0007|#^\"&\u001eX\u0004\u007f3X,\u0005n\"M+2\u0007m7^9:\u001au\u0005p3H$5\u0005n\"M+2\u0006~9A ;\u0011\u0006|#^\"&\u001e\u0005r!B(&\t\u007f#K91\fsx$\u0006~9A ;\u0011\u0005i$M$8\u000608C#1R\bh8O\"9\u0012hs\u0007|#^\"&\u001eX\u000608C#1R\u0004~7\\(\u0007p7B,3\u001au\u0004\u007f3X,\u0004o7^(\u0005r!B(&\u0007p7B,3\u001au".length();
        var7_4 = 5;
        var6_5 = -1;
        block11: while (true) {
            v0 = ++var6_5;
            v1 = var8_2.substring(v0, v0 + var7_4);
            v2 = -1;
            block12: while (true) {
                var8_2 = "|2A$:\u0005j?B*'";
                var10_3 = "|2A$:\u0005j?B*'".length();
                var7_4 = 5;
                var6_5 = -1;
                while (true) {
                    v3 = ++var6_5;
                    v1 = var8_2.substring(v3, v3 + var7_4);
                    v2 = 0;
lbl21:

                    v4 = v1.toCharArray();
                    v5 = v4;
                    v6 = v4.length;
                    var12_7 = 0;
                    do {
                        v5 = v5;
                        v7 = var12_7;
                        v8 = v5[v7];
                        switch (var12_7 % 7) {
                            case 0: {
                                v9 = 29;
                                break;
                            }
                            case 1: {
                                v9 = 86;
                                break;
                            }
                            case 2: {
                                v9 = 44;
                                break;
                            }
                            case 3: {
                                v9 = 77;
                                break;
                            }
                            case 4: {
                                v9 = 84;
                                break;
                            }
                            case 5: {
                                v9 = 127;
                                break;
                            }
                            default: {
                                v9 = 7;
                            }
                        }
                        v5[v7] = (char)(v8 ^ v9);
                        ++var12_7;
lbl53:

                        v6 = v6;
                    } while (v6 > var12_7);
                    v10 = new String(v5);
                    switch (v2) {
                        default: {
                            var11[var9_1++] = v10.intern();
                            if ((var6_5 += var7_4) >= var10_3) continue block12;
                            var7_4 = var8_2.charAt(var6_5);
                            continue block11;
                        }
                        case 0: 
                    }
                    var11[var9_1++] = v10.intern();
                    if ((var6_5 += var7_4) >= var10_3) {
                        Cosmetics.b = var11;
                        Cosmetics.NO_CAPE = Cosmetics.b[19];
                        var4_8 = 7448697091868812314L;
                        var2_9 = 424609324318549010L ^ var4_8;
                        Cosmetics.WHEEL_SLOTS = (int)var2_9;
                        var0_10 = 5856474930365758800L;
                        Cosmetics.c = 5856474930365759160L ^ var0_10;
                        Cosmetics.showOthers = true;
                        Cosmetics.REMOTE = new ArrayList<Map<UUID, ?>>();
                        Cosmetics.CAPES = Cosmetics.remote();
                        Cosmetics.ownKillEffect = "";
                        Cosmetics.KILL_FX = Cosmetics.remote();
                        Cosmetics.ownHitEffect = "";
                        Cosmetics.HIT_FX = Cosmetics.remote();
                        Cosmetics.ownMaceEffect = "";
                        Cosmetics.ownTrail = "";
                        Cosmetics.TRAILS = Cosmetics.remote();
                        Cosmetics.WINGS = Cosmetics.remote();
                        Cosmetics.ownWings = "";
                        Cosmetics.DISABLED = Map.of();
                        Cosmetics.LOCKED = Map.of();
                        Cosmetics.PLUS_ONLY = Map.of();
                        v11 = new String[4];
                        var6_6 = Cosmetics.b;
                        v11[0] = var6_6[14];
                        v11[1] = var6_6[17];
                        v11[2] = var6_6[23];
                        v11[3] = var6_6[1];
                        Cosmetics.RARITIES = v11;
                        Cosmetics.owned = Set.of();
                        Cosmetics.sold = Set.of();
                        Cosmetics.rarity = Map.of();
                        Cosmetics.prices = Map.of();
                        Cosmetics.closed = Set.of();
                        Cosmetics.deals = Map.of();
                        Cosmetics.released = Map.of();
                        Cosmetics.tryCat = "";
                        Cosmetics.tryId = "";
                        Cosmetics.WHEEL = new String[8];
                        Cosmetics.EMOTES = Cosmetics.remote();
                        Cosmetics.ownEmote = "";
                        Cosmetics.PLAYTIME = Cosmetics.remote();
                        Cosmetics.ONLINE_FOR = Cosmetics.remote();
                        Cosmetics.FIRST_SEEN = Cosmetics.remote();
                        Cosmetics.FRIENDS = Cosmetics.remote();
                        Cosmetics.USERS = Cosmetics.remote();
                        Cosmetics.ROLES = Cosmetics.remote();
                        Cosmetics.PLUS = Cosmetics.remote();
                        Cosmetics.ownCape = "";
                        Cosmetics.chosenBetaCape = "";
                        Cosmetics.ownCapeUrl = "";
                        Cosmetics.CAPE_IDS = new ConcurrentHashMap<String, String>();
                        Cosmetics.RANKS = new String[]{var6_6[12], var6_6[0], var6_6[21], var6_6[6], var6_6[8], var6_6[7], var6_6[13]};
                        return;
                    }
                    var7_4 = var8_2.charAt(var6_5);
                }
                break;
            }
            break;
        }
    }
}
