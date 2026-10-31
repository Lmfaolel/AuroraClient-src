package dev.quelchi.aurora.cosmetics.renderer.cape;

public final class CapeLayer$Pass
extends Enum<CapeLayer$Pass> {
    public static final /* enum */ CapeLayer$Pass BASE;
    public static final /* enum */ CapeLayer$Pass OVERLAY;
    public static final /* enum */ CapeLayer$Pass EMISSIVE;
    public static final /* enum */ CapeLayer$Pass GLOW;
    private static final CapeLayer$Pass[] $VALUES;

    public static CapeLayer$Pass[] values() {
        return (CapeLayer$Pass[])$VALUES.clone();
    }

    public static CapeLayer$Pass valueOf(String string) {
        return Enum.valueOf(CapeLayer$Pass.class, string);
    }

    private static CapeLayer$Pass[] $values() {
        return new CapeLayer$Pass[]{BASE, OVERLAY, EMISSIVE, GLOW};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "If$x\bNj>n>A&N";
                var5_3 = "If$x\bNj>n>A&N".length();
                var2_4 = 4;
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
                    var3_2 = "Dq2o!I)\u0004Lk8j";
                    var5_3 = "Dq2o!I)\u0004Lk8j".length();
                    var2_4 = 7;
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
                        v10 = 11;
                        break;
                    }
                    case 1: {
                        v10 = 39;
                        break;
                    }
                    case 2: {
                        v10 = 119;
                        break;
                    }
                    case 3: {
                        v10 = 61;
                        break;
                    }
                    case 4: {
                        v10 = 109;
                        break;
                    }
                    case 5: {
                        v10 = 8;
                        break;
                    }
                    default: {
                        v10 = 112;
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
        CapeLayer$Pass.BASE = new CapeLayer$Pass();
        CapeLayer$Pass.OVERLAY = new CapeLayer$Pass();
        CapeLayer$Pass.EMISSIVE = new CapeLayer$Pass();
        CapeLayer$Pass.GLOW = new CapeLayer$Pass();
        CapeLayer$Pass.$VALUES = CapeLayer$Pass.$values();
    }
}
