package dev.quelchi.aurora.social;

public final class SocialEvents$Kind
extends Enum<SocialEvents$Kind> {
    public static final /* enum */ SocialEvents$Kind REQUEST;
    public static final /* enum */ SocialEvents$Kind ONLINE;
    private static final SocialEvents$Kind[] $VALUES;

    public static SocialEvents$Kind[] values() {
        return (SocialEvents$Kind[])$VALUES.clone();
    }

    public static SocialEvents$Kind valueOf(String string) {
        return Enum.valueOf(SocialEvents$Kind.class, string);
    }

    private static SocialEvents$Kind[] $values() {
        return new SocialEvents$Kind[]{REQUEST, ONLINE};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "\u000eB,\u0007>7\u0007\u0006\u0013I1\u001b5!";
            var5_3 = "\u000eB,\u0007>7\u0007\u0006\u0013I1\u001b5!".length();
            var2_4 = 7;
            var1_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var0[var4_1++] = new String(v0).intern();
                if ((var1_5 += var2_4) < var5_3) {
                    var2_4 = var3_2.charAt(var1_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var1_5;
            v2 = var3_2.substring(v1, v1 + var2_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 92;
                        break;
                    }
                    case 1: {
                        v6 = 7;
                        break;
                    }
                    case 2: {
                        v6 = 125;
                        break;
                    }
                    case 3: {
                        v6 = 82;
                        break;
                    }
                    case 4: {
                        v6 = 123;
                        break;
                    }
                    case 5: {
                        v6 = 100;
                        break;
                    }
                    default: {
                        v6 = 83;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        SocialEvents$Kind.REQUEST = new SocialEvents$Kind();
        SocialEvents$Kind.ONLINE = new SocialEvents$Kind();
        SocialEvents$Kind.$VALUES = SocialEvents$Kind.$values();
    }
}
