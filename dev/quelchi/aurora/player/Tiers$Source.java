package dev.quelchi.aurora.player;

public final class Tiers$Source
extends Enum<Tiers$Source> {
    public static final /* enum */ Tiers$Source MCTiers;
    public static final /* enum */ Tiers$Source PvPTiers;
    public static final /* enum */ Tiers$Source Subtiers;
    public static final /* enum */ Tiers$Source PvPHQ;
    public final String api;
    private static final Tiers$Source[] $VALUES;

    public static Tiers$Source[] values() {
        return (Tiers$Source[])$VALUES.clone();
    }

    public static Tiers$Source valueOf(String string) {
        return Enum.valueOf(Tiers$Source.class, string);
    }

    private Tiers$Source(String string2) {
        this.api = string2;
    }

    private static Tiers$Source[] $values() {
        return new Tiers$Source[]{MCTiers, PvPTiers, Subtiers, PvPHQ};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[8];
                var4_1 = 0;
                var3_2 = "oBs*D-\u007f(Fq*C~5uE)9Xz\u007ffFnuGe?a_k?\u0018\bW@W\u000e^r\"t\u0007JuS3Re#\u001doBs*D-\u007f(Fq*_f~dYjuVg9(Df4\\r4(\bTCe.^r\"t\u0005W@W\u0012f";
                var5_3 = "oBs*D-\u007f(Fq*C~5uE)9Xz\u007ffFnuGe?a_k?\u0018\bW@W\u000e^r\"t\u0007JuS3Re#\u001doBs*D-\u007f(Fq*_f~dYjuVg9(Df4\\r4(\bTCe.^r\"t\u0005W@W\u0012f".length();
                var2_4 = 33;
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
                    var3_2 = "oBs*D-\u007f(Er8C~5uE)4Rc\u007ffFnuGe?a_k?\u0018#oBs*D-\u007f([d.^r\"t\u0018i?C81w_(,\u00058 uYa3[r\u007f";
                    var5_3 = "oBs*D-\u007f(Er8C~5uE)4Rc\u007ffFnuGe?a_k?\u0018#oBs*D-\u007f([d.^r\"t\u0018i?C81w_(,\u00058 uYa3[r\u007f".length();
                    var2_4 = 33;
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
                        v10 = 7;
                        break;
                    }
                    case 1: {
                        v10 = 54;
                        break;
                    }
                    case 2: {
                        v10 = 7;
                        break;
                    }
                    case 3: {
                        v10 = 90;
                        break;
                    }
                    case 4: {
                        v10 = 55;
                        break;
                    }
                    case 5: {
                        v10 = 23;
                        break;
                    }
                    default: {
                        v10 = 80;
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
        Tiers$Source.MCTiers = new Tiers$Source(var0[7]);
        Tiers$Source.PvPTiers = new Tiers$Source(var0[0]);
        Tiers$Source.Subtiers = new Tiers$Source(var0[6]);
        Tiers$Source.PvPHQ = new Tiers$Source(var0[3]);
        Tiers$Source.$VALUES = Tiers$Source.$values();
    }
}
