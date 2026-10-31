package dev.quelchi.aurora.social;

import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.social.Social;
import dev.quelchi.aurora.social.Social$Friend;
import dev.quelchi.aurora.social.SocialEvents$Event;
import dev.quelchi.aurora.social.SocialEvents$Kind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.client.MinecraftClient;

public final class SocialEvents {
    private static List<Social$Friend> lastFriends;
    private static long epoch;
    private static final String[] a;

    private SocialEvents() {
    }

    public static synchronized void reset() {
        lastFriends = null;
        ++epoch;
    }

    public static synchronized long epoch() {
        return epoch;
    }

    public static void friends(long l, List<Social$Friend> list) {
        SocialEvents.tell(SocialEvents.observeFriends(l, list));
    }

    static synchronized List<SocialEvents$Event> observeFriends(long l, List<Social$Friend> list) {
        List<SocialEvents$Event> list2;
        block9: {
            List<Social$Friend> list3;
            block7: {
                List<Social$Friend> list4;
                block8: {
                    int n = Social.a;
                    if (l != epoch) {
                        return List.of();
                    }
                    list4 = lastFriends;
                    list3 = list;
                    if (n != 0) break block7;
                    lastFriends = list3;
                    if (list4 != null) break block8;
                    list2 = List.of();
                    break block9;
                }
                list3 = list4;
            }
            list2 = SocialEvents.friendEvents(list3, list);
        }
        return list2;
    }

    
    static List<SocialEvents$Event> friendEvents(List<Social$Friend> list, List<Social$Friend> list2) {
        HashMap<UUID, Object> hashMap = new HashMap<UUID, Object>();
        Object object = list.iterator();
        int n = Social.a;
        while (object.hasNext()) {
            Social$Friend social$Friend = object.next();
            hashMap.put(social$Friend.uuid(), social$Friend);
            if (n == 0) continue;
        }
        object = new ArrayList();
        for (Social$Friend social$Friend : list2) {
            block25: {
                Social$Friend social$Friend2;
                block29: {
                    Social$Friend social$Friend3;
                    block28: {
                        block27: {
                            block26: {
                                block21: {
                                    block22: {
                                        block24: {
                                            block23: {
                                                social$Friend3 = (Social$Friend)hashMap.get(social$Friend.uuid());
                                                social$Friend2 = social$Friend;
                                                if (n != 0) break block21;
                                                if (!social$Friend2.incoming()) break block22;
                                                social$Friend2 = social$Friend3;
                                                if (n != 0) break block23;
                                                if (social$Friend2 == null) break block24;
                                                social$Friend2 = social$Friend3;
                                            }
                                            if (n != 0) break block21;
                                            if (social$Friend2.incoming()) break block22;
                                        }
                                        object.add(new SocialEvents$Event(SocialEvents$Kind.REQUEST, social$Friend.uuid(), social$Friend.name(), a[1]));
                                        if (n == 0) break block25;
                                    }
                                    social$Friend2 = social$Friend;
                                }
                                if (n != 0) break block26;
                                if (!social$Friend2.accepted()) break block25;
                                social$Friend2 = social$Friend;
                            }
                            if (n != 0) break block27;
                            if (!social$Friend2.online()) break block25;
                            social$Friend2 = social$Friend3;
                        }
                        if (n != 0) break block28;
                        if (social$Friend2 == null) break block25;
                        social$Friend2 = social$Friend3;
                    }
                    if (n != 0) break block29;
                    if (!social$Friend2.accepted()) break block25;
                    social$Friend2 = social$Friend3;
                }
                boolean bl = social$Friend2.online();
                if (n == 0 && !bl) {
                    bl = object.add(new SocialEvents$Event(SocialEvents$Kind.ONLINE, social$Friend.uuid(), social$Friend.name(), a[0]));
                }
            }
            if (n == 0) continue;
        }
        return object;
    }

    static List<SocialEvents$Event> audible(List<SocialEvents$Event> list, Predicate<UUID> predicate) {
        ArrayList<SocialEvents$Event> arrayList = new ArrayList<SocialEvents$Event>();
        int n = Social.a;
        for (SocialEvents$Event socialEvents$Event : list) {
            block5: {
                boolean bl = predicate.test(socialEvents$Event.who());
                if (n != 0 || bl) break block5;
                bl = arrayList.add(socialEvents$Event);
            }
            if (n == 0) continue;
        }
        return arrayList;
    }

    private static void tell(List<SocialEvents$Event> list) {
        if (list.isEmpty()) {
            return;
        }
        MinecraftClient.method_1551().execute(() -> SocialEvents.lambda$tell$0(list));
    }

    private static void lambda$tell$0(List list) {
        Iterator<SocialEvents$Event> iterator = SocialEvents.audible(list, Social::muted).iterator();
        int n = Social.a;
        while (iterator.hasNext()) {
            SocialEvents$Event socialEvents$Event = iterator.next();
            SocialToast.event(socialEvents$Event.kind(), socialEvents$Event.name(), socialEvents$Event.body(), socialEvents$Event.who());
            if (n == 0) continue;
        }
    }

    
    static {
        block12: {
            var5 = new String[2];
            var3_1 = 0;
            var2_2 = "DK9M\fm<C]\u0013ZYwV\u0011!!B\u0018{GBg'D]wF\u0011";
            var4_3 = "DK9M\fm<C]\u0013ZYwV\u0011!!B\u0018{GBg'D]wF\u0011".length();
            var1_4 = 9;
            var0_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var5[var3_1++] = new String(v0).intern();
                if ((var0_5 += var1_4) < var4_3) {
                    var1_4 = var2_2.charAt(var0_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var0_5;
            v2 = var2_2.substring(v1, v1 + var1_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 45;
                        break;
                    }
                    case 1: {
                        v6 = 56;
                        break;
                    }
                    case 2: {
                        v6 = 25;
                        break;
                    }
                    case 3: {
                        v6 = 34;
                        break;
                    }
                    case 4: {
                        v6 = 98;
                        break;
                    }
                    case 5: {
                        v6 = 1;
                        break;
                    }
                    default: {
                        v6 = 85;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        SocialEvents.a = var5;
    }
}
