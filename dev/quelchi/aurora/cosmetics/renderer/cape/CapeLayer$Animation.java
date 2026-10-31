package dev.quelchi.aurora.cosmetics.renderer.cape;

public final class CapeLayer$Animation
extends Enum<CapeLayer$Animation> {
    public static final /* enum */ CapeLayer$Animation STATIC;
    public static final /* enum */ CapeLayer$Animation LOOP;
    public static final /* enum */ CapeLayer$Animation PING_PONG;
    private static final CapeLayer$Animation[] $VALUES;

    public static CapeLayer$Animation[] values() {
        return (CapeLayer$Animation[])$VALUES.clone();
    }

    public static CapeLayer$Animation valueOf(String string) {
        return Enum.valueOf(CapeLayer$Animation.class, string);
    }

    private static CapeLayer$Animation[] $values() {
        return new CapeLayer$Animation[]{STATIC, LOOP, PING_PONG};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "[e\u0016G\tGc\u0017P\u0011VbYm\u0006D~\u0018C\u0007E";
            var5_3 = "[e\u0016G\tGc\u0017P\u0011VbYm\u0006D~\u0018C\u0007E".length();
            var2_4 = 4;
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
                        v6 = 23;
                        break;
                    }
                    case 1: {
                        v6 = 42;
                        break;
                    }
                    case 2: {
                        v6 = 89;
                        break;
                    }
                    case 3: {
                        v6 = 23;
                        break;
                    }
                    case 4: {
                        v6 = 78;
                        break;
                    }
                    case 5: {
                        v6 = 6;
                        break;
                    }
                    default: {
                        v6 = 45;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        CapeLayer$Animation.STATIC = new CapeLayer$Animation();
        CapeLayer$Animation.LOOP = new CapeLayer$Animation();
        CapeLayer$Animation.PING_PONG = new CapeLayer$Animation();
        CapeLayer$Animation.$VALUES = CapeLayer$Animation.$values();
    }
}
