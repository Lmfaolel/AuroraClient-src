package dev.quelchi.aurora.module.modules;

public final class HitboxesModule$Kind
extends Enum<HitboxesModule$Kind> {
    public static final /* enum */ HitboxesModule$Kind PLAYERS;
    public static final /* enum */ HitboxesModule$Kind CRYSTALS;
    public static final /* enum */ HitboxesModule$Kind PEARLS;
    public static final /* enum */ HitboxesModule$Kind ARROWS;
    public static final /* enum */ HitboxesModule$Kind ENTITIES;
    public static final /* enum */ HitboxesModule$Kind MISC;
    private static final HitboxesModule$Kind[] $VALUES;

    public static HitboxesModule$Kind[] values() {
        return (HitboxesModule$Kind[])$VALUES.clone();
    }

    public static HitboxesModule$Kind valueOf(String string) {
        return Enum.valueOf(HitboxesModule$Kind.class, string);
    }

    private static HitboxesModule$Kind[] $values() {
        return new HitboxesModule$Kind[]{PLAYERS, CRYSTALS, PEARLS, ARROWS, ENTITIES, MISC};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[6];
                var4_1 = 0;
                var3_2 = "\u00033$)ly\u0006\u0012$74wy\b\u00013/5ok+\u0011\b\u0007/\"/oc\"\u0011";
                var5_3 = "\u00033$)ly\u0006\u0012$74wy\b\u00013/5ok+\u0011\b\u0007/\"/oc\"\u0011".length();
                var2_4 = 6;
                var1_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var1_5;
                    v1 = var3_2.substring(v0, v0 + var2_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        continue;
                    }
                    var3_2 = "\u000f(%%\u0007\u0012-7?~x4";
                    var5_3 = "\u000f(%%\u0007\u0012-7?~x4".length();
                    var2_4 = 4;
                    var1_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var1_5;
                        v1 = var3_2.substring(v4, v4 + var2_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
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
                        v10 = 66;
                        break;
                    }
                    case 1: {
                        v10 = 97;
                        break;
                    }
                    case 2: {
                        v10 = 118;
                        break;
                    }
                    case 3: {
                        v10 = 102;
                        break;
                    }
                    case 4: {
                        v10 = 59;
                        break;
                    }
                    case 5: {
                        v10 = 42;
                        break;
                    }
                    default: {
                        v10 = 103;
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
        HitboxesModule$Kind.PLAYERS = new HitboxesModule$Kind();
        HitboxesModule$Kind.CRYSTALS = new HitboxesModule$Kind();
        HitboxesModule$Kind.PEARLS = new HitboxesModule$Kind();
        HitboxesModule$Kind.ARROWS = new HitboxesModule$Kind();
        HitboxesModule$Kind.ENTITIES = new HitboxesModule$Kind();
        HitboxesModule$Kind.MISC = new HitboxesModule$Kind();
        HitboxesModule$Kind.$VALUES = HitboxesModule$Kind.$values();
    }
}
