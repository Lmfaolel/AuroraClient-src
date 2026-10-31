package dev.quelchi.aurora.cosmetics.content;

import dev.quelchi.aurora.cosmetics.content.Colorways;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.content.Trails$Trail;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Trails {
    private static final List<Trails$Trail> EXTRA = new CopyOnWriteArrayList<Trails$Trail>();

    private Trails() {
    }

    public static void registerRemote(List<Trails$Trail> list) {
        EXTRA.clear();
        EXTRA.addAll(list);
    }

    public static List<Trails$Trail> all() {
        return EXTRA;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Trails$Trail design(String string) {
        String string2;
        int n;
        block12: {
            block13: {
                block11: {
                    n = Cosmetics.a;
                    string2 = string;
                    if (n != 0) break block11;
                    if (string2 == null) return null;
                    string2 = string;
                }
                if (n != 0) break block12;
                if (!string2.isEmpty()) break block13;
                return null;
            }
            string2 = Colorways.designOf(string);
        }
        String string3 = string2;
        for (Trails$Trail trails$Trail : EXTRA) {
            block14: {
                Trails$Trail trails$Trail2 = trails$Trail;
                if (n != 0) return trails$Trail2;
                if (!trails$Trail2.id().equals(string3)) break block14;
                trails$Trail2 = trails$Trail;
                return trails$Trail2;
            }
            if (n == 0) continue;
        }
        return null;
    }

    public static Trails$Trail byId(String string) {
        Trails$Trail trails$Trail;
        block6: {
            Trails$Trail trails$Trail2;
            block4: {
                Trails$Trail trails$Trail3;
                block5: {
                    trails$Trail3 = Trails.design(string);
                    int n = Cosmetics.a;
                    trails$Trail2 = trails$Trail3;
                    if (n != 0) break block4;
                    if (trails$Trail2 != null) break block5;
                    trails$Trail = null;
                    break block6;
                }
                trails$Trail2 = trails$Trail3;
            }
            trails$Trail = trails$Trail2.inWay(Colorways.wayOf(string));
        }
        return trails$Trail;
    }
}
