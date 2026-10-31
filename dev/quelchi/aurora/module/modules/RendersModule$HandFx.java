package dev.quelchi.aurora.module.modules;

public final class RendersModule$HandFx
extends Enum<RendersModule$HandFx> {
    public static final /* enum */ RendersModule$HandFx None;
    public static final /* enum */ RendersModule$HandFx Solid;
    public static final /* enum */ RendersModule$HandFx Chroma;
    public static final /* enum */ RendersModule$HandFx Balatro;
    public static final /* enum */ RendersModule$HandFx Smoke;
    public static final /* enum */ RendersModule$HandFx Stripes;
    public static final /* enum */ RendersModule$HandFx Glow;
    public static final /* enum */ RendersModule$HandFx Glass;
    public static final /* enum */ RendersModule$HandFx Invert;
    public static final /* enum */ RendersModule$HandFx Snow;
    private static final RendersModule$HandFx[] $VALUES;

    public static RendersModule$HandFx[] values() {
        return (RendersModule$HandFx[])$VALUES.clone();
    }

    public static RendersModule$HandFx valueOf(String string) {
        return Enum.valueOf(RendersModule$HandFx.class, string);
    }

    private static RendersModule$HandFx[] $values() {
        return new RendersModule$HandFx[]{None, Solid, Chroma, Balatro, Smoke, Stripes, Glow, Glass, Invert, Snow};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[10];
                var4_1 = 0;
                var3_2 = "b\f\u001dT\u0006x\f\u0004F\u0005\u001e\u0006r\n\u0000L\u001a\u000b\u0005b\r\u001eJ\u0013\u0004\u007f\r\u001cF\u0005v\u000e\u0013P\u0004\u0005b\u000f\u001dH\u0012\u0007b\u0016\u0000J\u0007\u000f&";
                var5_3 = "b\f\u001dT\u0006x\f\u0004F\u0005\u001e\u0006r\n\u0000L\u001a\u000b\u0005b\r\u001eJ\u0013\u0004\u007f\r\u001cF\u0005v\u000e\u0013P\u0004\u0005b\u000f\u001dH\u0012\u0007b\u0016\u0000J\u0007\u000f&".length();
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
                    var3_2 = "v\u000e\u001dT\u0007s\u0003\u001eB\u0003\u0018:";
                    var5_3 = "v\u000e\u001dT\u0007s\u0003\u001eB\u0003\u0018:".length();
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
                        v10 = 49;
                        break;
                    }
                    case 1: {
                        v10 = 98;
                        break;
                    }
                    case 2: {
                        v10 = 114;
                        break;
                    }
                    case 3: {
                        v10 = 35;
                        break;
                    }
                    case 4: {
                        v10 = 119;
                        break;
                    }
                    case 5: {
                        v10 = 106;
                        break;
                    }
                    default: {
                        v10 = 85;
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
        RendersModule$HandFx.None = new RendersModule$HandFx();
        RendersModule$HandFx.Solid = new RendersModule$HandFx();
        RendersModule$HandFx.Chroma = new RendersModule$HandFx();
        RendersModule$HandFx.Balatro = new RendersModule$HandFx();
        RendersModule$HandFx.Smoke = new RendersModule$HandFx();
        RendersModule$HandFx.Stripes = new RendersModule$HandFx();
        RendersModule$HandFx.Glow = new RendersModule$HandFx();
        RendersModule$HandFx.Glass = new RendersModule$HandFx();
        RendersModule$HandFx.Invert = new RendersModule$HandFx();
        RendersModule$HandFx.Snow = new RendersModule$HandFx();
        RendersModule$HandFx.$VALUES = RendersModule$HandFx.$values();
    }
}
