package dev.quelchi.aurora.module.modules;

public final class WeatherChangerModule$WeatherPreset
extends Enum<WeatherChangerModule$WeatherPreset> {
    public static final /* enum */ WeatherChangerModule$WeatherPreset Clear;
    public static final /* enum */ WeatherChangerModule$WeatherPreset Rain;
    public static final /* enum */ WeatherChangerModule$WeatherPreset Thunder;
    private static final WeatherChangerModule$WeatherPreset[] $VALUES;

    public static WeatherChangerModule$WeatherPreset[] values() {
        return (WeatherChangerModule$WeatherPreset[])$VALUES.clone();
    }

    public static WeatherChangerModule$WeatherPreset valueOf(String string) {
        return Enum.valueOf(WeatherChangerModule$WeatherPreset.class, string);
    }

    private static WeatherChangerModule$WeatherPreset[] $values() {
        return new WeatherChangerModule$WeatherPreset[]{Clear, Rain, Thunder};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "B]\u0010{<\u001d/\u0005UY\u0000t*\u0004DT\f{";
            var5_3 = "B]\u0010{<\u001d/\u0005UY\u0000t*\u0004DT\f{".length();
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
                        v6 = 22;
                        break;
                    }
                    case 1: {
                        v6 = 53;
                        break;
                    }
                    case 2: {
                        v6 = 101;
                        break;
                    }
                    case 3: {
                        v6 = 21;
                        break;
                    }
                    case 4: {
                        v6 = 88;
                        break;
                    }
                    case 5: {
                        v6 = 120;
                        break;
                    }
                    default: {
                        v6 = 93;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        WeatherChangerModule$WeatherPreset.Clear = new WeatherChangerModule$WeatherPreset();
        WeatherChangerModule$WeatherPreset.Rain = new WeatherChangerModule$WeatherPreset();
        WeatherChangerModule$WeatherPreset.Thunder = new WeatherChangerModule$WeatherPreset();
        WeatherChangerModule$WeatherPreset.$VALUES = WeatherChangerModule$WeatherPreset.$values();
    }
}
