package dev.quelchi.aurora.cosmetics.renderer;

import dev.quelchi.aurora.cosmetics.content.Colorways;
import dev.quelchi.aurora.cosmetics.renderer.HitEffects;
import dev.quelchi.aurora.cosmetics.renderer.KillEffects;
import dev.quelchi.aurora.cosmetics.renderer.MaceEffects;
import dev.quelchi.aurora.render.EffectEngine$Effect;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class EffectList {
    private final List<EffectEngine$Effect> items = new CopyOnWriteArrayList<EffectEngine$Effect>();
    public static int a;
    private static final String[] b;

    public void set(List<EffectEngine$Effect> list) {
        this.items.clear();
        this.items.addAll(list);
    }

    public List<EffectEngine$Effect> all() {
        return this.items;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public EffectEngine$Effect design(String string) {
        String string2;
        int n;
        block12: {
            block13: {
                block11: {
                    n = a;
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
        for (EffectEngine$Effect effectEngine$Effect : this.items) {
            block14: {
                EffectEngine$Effect effectEngine$Effect2 = effectEngine$Effect;
                if (n != 0) return effectEngine$Effect2;
                if (!effectEngine$Effect2.id().equals(string3)) break block14;
                effectEngine$Effect2 = effectEngine$Effect;
                return effectEngine$Effect2;
            }
            if (n == 0) continue;
        }
        return null;
    }

    public EffectEngine$Effect byId(String string) {
        EffectEngine$Effect effectEngine$Effect;
        block6: {
            EffectEngine$Effect effectEngine$Effect2;
            block4: {
                EffectEngine$Effect effectEngine$Effect3;
                block5: {
                    effectEngine$Effect3 = this.design(string);
                    int n = a;
                    effectEngine$Effect2 = effectEngine$Effect3;
                    if (n != 0) break block4;
                    if (effectEngine$Effect2 != null) break block5;
                    effectEngine$Effect = null;
                    break block6;
                }
                effectEngine$Effect2 = effectEngine$Effect3;
            }
            effectEngine$Effect = effectEngine$Effect2.inWay(Colorways.wayOf(string));
        }
        return effectEngine$Effect;
    }

    
    public static EffectList of(String string) {
        int n;
        block20: {
            String string2 = string;
            int n2 = a;
            int n3 = -1;
            n = string2.hashCode();
            if (n2 != 0) break block20;
            switch (n) {
                case -1972411761: {
                    n = string2.equals(b[1]) ? 1 : 0;
                    if (n2 == 0) {
                        if (n == 0) break;
                        n3 = 0;
                        if (n2 == 0) break;
                    }
                    break block20;
                }
                case -1213687257: {
                    n = string2.equals(b[2]) ? 1 : 0;
                    if (n2 == 0) {
                        if (n == 0) break;
                        n3 = 1;
                        if (n2 == 0) break;
                    }
                    break block20;
                }
                case 1258749700: {
                    n = string2.equals(b[0]) ? 1 : 0;
                    if (n2 != 0) break block20;
                    if (n == 0) break;
                    n3 = 2;
                }
            }
            n = n3;
        }
        switch (n) {
            case 0: {
                EffectList effectList = KillEffects.LIST;
                return effectList;
            }
            case 1: {
                EffectList effectList = MaceEffects.LIST;
                return effectList;
            }
            case 2: {
                EffectList effectList = HitEffects.LIST;
                return effectList;
            }
        }
        return null;
    }

    
    static {
        block12: {
            var5 = new String[3];
            var3_1 = 0;
            var2_2 = "U</\u000f'E\u0012^!\nV<7&\u0004E\u0011X6/\nP48/\u0004E\u0011X6/";
            var4_3 = "U</\u000f'E\u0012^!\nV<7&\u0004E\u0011X6/\nP48/\u0004E\u0011X6/".length();
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
                        v6 = 61;
                        break;
                    }
                    case 1: {
                        v6 = 85;
                        break;
                    }
                    case 2: {
                        v6 = 91;
                        break;
                    }
                    case 3: {
                        v6 = 74;
                        break;
                    }
                    case 4: {
                        v6 = 65;
                        break;
                    }
                    case 5: {
                        v6 = 35;
                        break;
                    }
                    default: {
                        v6 = 119;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        EffectList.b = var5;
    }
}
