package dev.quelchi.aurora.module.modules;

public final class MusicPlayerModule$Backdrop
extends Enum<MusicPlayerModule$Backdrop> {
    public static final /* enum */ MusicPlayerModule$Backdrop None;
    public static final /* enum */ MusicPlayerModule$Backdrop Dark;
    public static final /* enum */ MusicPlayerModule$Backdrop Glass;
    private static final MusicPlayerModule$Backdrop[] $VALUES;

    public static MusicPlayerModule$Backdrop[] values() {
        return (MusicPlayerModule$Backdrop[])$VALUES.clone();
    }

    public static MusicPlayerModule$Backdrop valueOf(String string) {
        return Enum.valueOf(MusicPlayerModule$Backdrop.class, string);
    }

    private static MusicPlayerModule$Backdrop[] $values() {
        return new MusicPlayerModule$Backdrop[]{None, Dark, Glass};
    }

    
    static {
        block12: {
            var0 = new String[3];
            var4_1 = 0;
            var3_2 = "/y\u0016R\n\u0004&z\u0019D\u0004,t\u0005J";
            var5_3 = "/y\u0016R\n\u0004&z\u0019D\u0004,t\u0005J".length();
            var2_4 = 5;
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
                        v6 = 104;
                        break;
                    }
                    case 1: {
                        v6 = 21;
                        break;
                    }
                    case 2: {
                        v6 = 119;
                        break;
                    }
                    case 3: {
                        v6 = 33;
                        break;
                    }
                    case 4: {
                        v6 = 121;
                        break;
                    }
                    case 5: {
                        v6 = 98;
                        break;
                    }
                    default: {
                        v6 = 24;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        MusicPlayerModule$Backdrop.None = new MusicPlayerModule$Backdrop();
        MusicPlayerModule$Backdrop.Dark = new MusicPlayerModule$Backdrop();
        MusicPlayerModule$Backdrop.Glass = new MusicPlayerModule$Backdrop();
        MusicPlayerModule$Backdrop.$VALUES = MusicPlayerModule$Backdrop.$values();
    }
}
