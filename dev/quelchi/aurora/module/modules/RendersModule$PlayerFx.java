package dev.quelchi.aurora.module.modules;

public final class RendersModule$PlayerFx
extends Enum<RendersModule$PlayerFx> {
    public static final /* enum */ RendersModule$PlayerFx Glow;
    public static final /* enum */ RendersModule$PlayerFx Glass;
    private static final RendersModule$PlayerFx[] $VALUES;

    public static RendersModule$PlayerFx[] values() {
        return (RendersModule$PlayerFx[])$VALUES.clone();
    }

    public static RendersModule$PlayerFx valueOf(String string) {
        return Enum.valueOf(RendersModule$PlayerFx.class, string);
    }

    private static RendersModule$PlayerFx[] $values() {
        return new RendersModule$PlayerFx[]{Glow, Glass};
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "5HN\u0018\u00055H@\u001c\u001f";
            var5_3 = "5HN\u0018\u00055H@\u001c\u001f".length();
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
                        v6 = 114;
                        break;
                    }
                    case 1: {
                        v6 = 36;
                        break;
                    }
                    case 2: {
                        v6 = 33;
                        break;
                    }
                    case 3: {
                        v6 = 111;
                        break;
                    }
                    case 4: {
                        v6 = 108;
                        break;
                    }
                    case 5: {
                        v6 = 26;
                        break;
                    }
                    default: {
                        v6 = 48;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        RendersModule$PlayerFx.Glow = new RendersModule$PlayerFx();
        RendersModule$PlayerFx.Glass = new RendersModule$PlayerFx();
        RendersModule$PlayerFx.$VALUES = RendersModule$PlayerFx.$values();
    }
}
