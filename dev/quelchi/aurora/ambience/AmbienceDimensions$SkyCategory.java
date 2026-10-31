package dev.quelchi.aurora.ambience;

final class AmbienceDimensions$SkyCategory
extends Enum<AmbienceDimensions$SkyCategory> {
    public static final /* enum */ AmbienceDimensions$SkyCategory OVERWORLD;
    public static final /* enum */ AmbienceDimensions$SkyCategory NETHER;
    public static final /* enum */ AmbienceDimensions$SkyCategory END;
    public static final /* enum */ AmbienceDimensions$SkyCategory OTHER;
    private static final AmbienceDimensions$SkyCategory[] $VALUES;

    public static AmbienceDimensions$SkyCategory[] values() {
        return (AmbienceDimensions$SkyCategory[])$VALUES.clone();
    }

    public static AmbienceDimensions$SkyCategory valueOf(String string) {
        return Enum.valueOf(AmbienceDimensions$SkyCategory.class, string);
    }

    private static AmbienceDimensions$SkyCategory[] $values() {
        return new AmbienceDimensions$SkyCategory[]{OVERWORLD, NETHER, END, OTHER};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "\u0011B7!\u000fy\u0005\u0010S+,\u0018";
                var5_3 = "\u0011B7!\u000fy\u0005\u0010S+,\u0018".length();
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
                    var3_2 = "\u0010Q&;\u001ddf\u0013C\u0003\u001aI'";
                    var5_3 = "\u0010Q&;\u001ddf\u0013C\u0003\u001aI'".length();
                    var2_4 = 9;
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
                        v10 = 95;
                        break;
                    }
                    case 1: {
                        v10 = 7;
                        break;
                    }
                    case 2: {
                        v10 = 99;
                        break;
                    }
                    case 3: {
                        v10 = 105;
                        break;
                    }
                    case 4: {
                        v10 = 74;
                        break;
                    }
                    case 5: {
                        v10 = 43;
                        break;
                    }
                    default: {
                        v10 = 52;
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
        AmbienceDimensions$SkyCategory.OVERWORLD = new AmbienceDimensions$SkyCategory();
        AmbienceDimensions$SkyCategory.NETHER = new AmbienceDimensions$SkyCategory();
        AmbienceDimensions$SkyCategory.END = new AmbienceDimensions$SkyCategory();
        AmbienceDimensions$SkyCategory.OTHER = new AmbienceDimensions$SkyCategory();
        AmbienceDimensions$SkyCategory.$VALUES = AmbienceDimensions$SkyCategory.$values();
    }
}
