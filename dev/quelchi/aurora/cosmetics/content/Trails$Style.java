package dev.quelchi.aurora.cosmetics.content;

public final class Trails$Style
extends Enum<Trails$Style> {
    public static final /* enum */ Trails$Style Helix;
    public static final /* enum */ Trails$Style Beads;
    public static final /* enum */ Trails$Style Comet;
    public static final /* enum */ Trails$Style Sparks;
    public static final /* enum */ Trails$Style Wings;
    public static final /* enum */ Trails$Style Plume;
    public static final /* enum */ Trails$Style Dash;
    public static final /* enum */ Trails$Style Halo;
    public static final /* enum */ Trails$Style Bolt;
    public static final /* enum */ Trails$Style Petals;
    public static final /* enum */ Trails$Style Prism;
    public static final /* enum */ Trails$Style Flame;
    public static final /* enum */ Trails$Style Constellation;
    public static final /* enum */ Trails$Style Rungs;
    private static final Trails$Style[] $VALUES;

    public static Trails$Style[] values() {
        return (Trails$Style[])$VALUES.clone();
    }

    public static Trails$Style valueOf(String string) {
        return Enum.valueOf(Trails$Style.class, string);
    }

    private static Trails$Style[] $values() {
        return new Trails$Style[]{Helix, Beads, Comet, Sparks, Wings, Plume, Dash, Halo, Bolt, Petals, Prism, Flame, Constellation, Rungs};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[14];
                var4_1 = 0;
                var3_2 = "bT\\u\u0016\u0005qIXc\u000f\u0005c^Pt\u0011\u0006q^Eq\u000en\u0004iZ]\u007f\u0005sN_w\u0011\u0005i^]y\u001a\u0004eZBx\u0005vR_w\u0011\u0004cT]d\rbT_c\u0016x?MZEy\rs\u0005gWP}\u0007";
                var5_3 = "bT\\u\u0016\u0005qIXc\u000f\u0005c^Pt\u0011\u0006q^Eq\u000en\u0004iZ]\u007f\u0005sN_w\u0011\u0005i^]y\u001a\u0004eZBx\u0005vR_w\u0011\u0004cT]d\rbT_c\u0016x?MZEy\rs\u0005gWP}\u0007".length();
                var2_4 = 5;
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
                    var3_2 = "qWD}\u0007\u0006rKPb\tn";
                    var5_3 = "qWD}\u0007\u0006rKPb\tn".length();
                    var2_4 = 5;
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
                        v10 = 33;
                        break;
                    }
                    case 1: {
                        v10 = 59;
                        break;
                    }
                    case 2: {
                        v10 = 49;
                        break;
                    }
                    case 3: {
                        v10 = 16;
                        break;
                    }
                    case 4: {
                        v10 = 98;
                        break;
                    }
                    case 5: {
                        v10 = 29;
                        break;
                    }
                    default: {
                        v10 = 83;
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
        Trails$Style.Helix = new Trails$Style();
        Trails$Style.Beads = new Trails$Style();
        Trails$Style.Comet = new Trails$Style();
        Trails$Style.Sparks = new Trails$Style();
        Trails$Style.Wings = new Trails$Style();
        Trails$Style.Plume = new Trails$Style();
        Trails$Style.Dash = new Trails$Style();
        Trails$Style.Halo = new Trails$Style();
        Trails$Style.Bolt = new Trails$Style();
        Trails$Style.Petals = new Trails$Style();
        Trails$Style.Prism = new Trails$Style();
        Trails$Style.Flame = new Trails$Style();
        Trails$Style.Constellation = new Trails$Style();
        Trails$Style.Rungs = new Trails$Style();
        Trails$Style.$VALUES = Trails$Style.$values();
    }
}
