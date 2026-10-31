package dev.quelchi.aurora.cosmetics.content;

import dev.quelchi.aurora.cosmetics.content.Colorways;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.content.Wings$Wing;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class Wings {
    private static final Map<String, Wings$Wing> BY_ID = new LinkedHashMap<String, Wings$Wing>();
    private static final Set<String> REMOTE_IDS = ConcurrentHashMap.newKeySet();

    private Wings() {
    }

    
    public static void registerRemote(List<Wings$Wing> var0) {
        var2_1 = Wings.REMOTE_IDS.iterator();
        var1_2 = Cosmetics.a;
        while (var2_1.hasNext()) {
            var3_3 = var2_1.next();
            v0 = Wings.BY_ID.remove(var3_3);
            if (var1_2 == 0) {
                if (var1_2 == 0) continue;
                break;
            }
        }
        Wings.REMOTE_IDS.clear();
        for (Object v0 : var0) lbl_neg_1000:

        {
            var3_3 = (Wings$Wing)v0;
            Wings.BY_ID.put(var3_3.id(), (Wings$Wing)var3_3);
            Wings.REMOTE_IDS.add(var3_3.id());
            if (var1_2 == 0) continue;
        }
    }

    public static Iterable<Wings$Wing> all() {
        return BY_ID.values();
    }

    public static Wings$Wing design(String string) {
        Wings$Wing wings$Wing;
        block6: {
            Object object;
            block4: {
                block5: {
                    int n = Cosmetics.a;
                    object = string;
                    if (n != 0) break block4;
                    if (object != null) break block5;
                    wings$Wing = null;
                    break block6;
                }
                object = BY_ID.get(string);
            }
            wings$Wing = (Wings$Wing)object;
        }
        return wings$Wing;
    }

    public static Wings$Wing resolve(String string) {
        return Wings.design(Colorways.designOf(string));
    }

    public static String modelFor(String string) {
        String string2;
        block6: {
            Wings$Wing wings$Wing;
            block4: {
                Wings$Wing wings$Wing2;
                block5: {
                    wings$Wing2 = Wings.resolve(string);
                    int n = Cosmetics.a;
                    wings$Wing = wings$Wing2;
                    if (n != 0) break block4;
                    if (wings$Wing != null) break block5;
                    string2 = null;
                    break block6;
                }
                wings$Wing = wings$Wing2;
            }
            string2 = wings$Wing.model(Colorways.wayOf(string));
        }
        return string2;
    }
}
