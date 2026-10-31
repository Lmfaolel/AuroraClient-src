package dev.quelchi.aurora.module.modules;

public final class TotemTweaksModule$ParticleStyle
extends Enum<TotemTweaksModule$ParticleStyle> {
    public static final /* enum */ TotemTweaksModule$ParticleStyle Vanilla;
    public static final /* enum */ TotemTweaksModule$ParticleStyle Burst;
    public static final /* enum */ TotemTweaksModule$ParticleStyle Fall;
    public static final /* enum */ TotemTweaksModule$ParticleStyle Fountain;
    public static final /* enum */ TotemTweaksModule$ParticleStyle Ring;
    private static final TotemTweaksModule$ParticleStyle[] $VALUES;

    public static TotemTweaksModule$ParticleStyle[] values() {
        return (TotemTweaksModule$ParticleStyle[])$VALUES.clone();
    }

    public static TotemTweaksModule$ParticleStyle valueOf(String string) {
        return Enum.valueOf(TotemTweaksModule$ParticleStyle.class, string);
    }

    private static TotemTweaksModule$ParticleStyle[] $values() {
        return new TotemTweaksModule$ParticleStyle[]{Vanilla, Burst, Fall, Fountain, Ring};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[5];
                var4_1 = 0;
                var3_2 = "?5\f\u00076b\u001d\u0017\u0007/;\u0017\u0000.o\u0015\u0004?;\u0015\u0005";
                var5_3 = "?5\f\u00076b\u001d\u0017\u0007/;\u0017\u0000.o\u0015\u0004?;\u0015\u0005".length();
                var2_4 = 8;
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
                    var3_2 = ";/\u000b\u001a6\u0004+3\u0017\u000e";
                    var5_3 = ";/\u000b\u001a6\u0004+3\u0017\u000e".length();
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
                        v10 = 121;
                        break;
                    }
                    case 1: {
                        v10 = 90;
                        break;
                    }
                    case 2: {
                        v10 = 121;
                        break;
                    }
                    case 3: {
                        v10 = 105;
                        break;
                    }
                    case 4: {
                        v10 = 66;
                        break;
                    }
                    case 5: {
                        v10 = 3;
                        break;
                    }
                    default: {
                        v10 = 116;
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
        TotemTweaksModule$ParticleStyle.Vanilla = new TotemTweaksModule$ParticleStyle();
        TotemTweaksModule$ParticleStyle.Burst = new TotemTweaksModule$ParticleStyle();
        TotemTweaksModule$ParticleStyle.Fall = new TotemTweaksModule$ParticleStyle();
        TotemTweaksModule$ParticleStyle.Fountain = new TotemTweaksModule$ParticleStyle();
        TotemTweaksModule$ParticleStyle.Ring = new TotemTweaksModule$ParticleStyle();
        TotemTweaksModule$ParticleStyle.$VALUES = TotemTweaksModule$ParticleStyle.$values();
    }
}
