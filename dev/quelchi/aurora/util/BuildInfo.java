package dev.quelchi.aurora.util;

import dev.quelchi.aurora.util.KeyUtil;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public final class BuildInfo {
    private static final String VERSION;
    private static final String DEV;
    private static final String COMMIT;
    private static final String BUILT;
    private static final String[] a;

    private BuildInfo() {
    }

    public static String version() {
        return VERSION;
    }

    public static String dev() {
        return DEV;
    }

    public static String commit() {
        return COMMIT;
    }

    public static long builtMillis() {
        if (BUILT.isEmpty()) {
            return 0L;
        }
        try {
            return LocalDateTime.parse(BUILT, DateTimeFormatter.ofPattern(a[4])).toInstant(ZoneOffset.UTC).toEpochMilli();
        }
        catch (Exception exception) {
            return 0L;
        }
    }

    public static String label() {
        String string;
        block20: {
            block21: {
                int n;
                block18: {
                    block19: {
                        block16: {
                            block17: {
                                n = KeyUtil.a;
                                string = COMMIT;
                                if (n != 0) break block16;
                                if (string.isEmpty()) break block17;
                                string = BUILT;
                                if (n != 0) break block16;
                                if (string.isEmpty()) break block17;
                                return COMMIT + a[1] + BUILT + ")";
                            }
                            string = COMMIT;
                        }
                        if (n != 0) break block18;
                        if (string.isEmpty()) break block19;
                        return COMMIT;
                    }
                    string = BUILT;
                }
                if (n != 0) break block20;
                if (string.isEmpty()) break block21;
                return a[5] + BUILT;
            }
            string = "";
        }
        return string;
    }

    
    static {
        block29: {
            block28: {
                var5 = new String[8];
                var3_1 = 0;
                var2_2 = "`jx|`f\u0018\u00026'\u0003rj|\u0006u`gb`}\u0010ovsv$D;;kn/AAL{b\u0006tzcc})";
                var4_3 = "`jx|`f\u0018\u00026'\u0003rj|\u0006u`gb`}\u0010ovsv$D;;kn/AAL{b\u0006tzcc})".length();
                var1_4 = 7;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block28;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "w|yj}zYwzx`{hYwzx`{h[tzccm'\u0006d`zj{}\u001fs|\u0005tzcc}";
                    var4_3 = "w|yj}zYwzx`{hYwzx`{h[tzccm'\u0006d`zj{}\u001fs|\u0005tzcc}".length();
                    var1_4 = 37;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block28;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    break block29;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 22;
                        break;
                    }
                    case 1: {
                        v10 = 15;
                        break;
                    }
                    case 2: {
                        v10 = 10;
                        break;
                    }
                    case 3: {
                        v10 = 15;
                        break;
                    }
                    case 4: {
                        v10 = 9;
                        break;
                    }
                    case 5: {
                        v10 = 9;
                        break;
                    }
                    default: {
                        v10 = 118;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_7;
lbl65:

                v7 = v7;
            } while (v7 > var6_7);
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
        BuildInfo.a = var5;
        var7_8 = "";
        var8_9 = "";
        var9_10 = "";
        var10_11 = "";
        try {
            block30: {
                var0_6 = BuildInfo.a;
                var11_12 = BuildInfo.class.getClassLoader().getResourceAsStream(var0_6[6]);
                try {
                    if (var11_12 == null) break block30;
                    var12_14 = new Properties();
                    var12_14.load(var11_12);
                    var7_8 = var12_14.getProperty(var0_6[0], "").trim();
                    var8_9 = var12_14.getProperty(var0_6[2], "").trim();
                    var9_10 = var12_14.getProperty(var0_6[3], "").trim();
                    var10_11 = var12_14.getProperty(var0_6[7], "").trim();
                }
                catch (Throwable var12_15) {
                    if (var11_12 != null) {
                        try {
                            var11_12.close();
                        }
                        catch (Throwable var13_16) {
                            var12_15.addSuppressed(var13_16);
                        }
                    }
                    throw var12_15;
                }
            }
            if (var11_12 != null) {
                var11_12.close();
            }
        }
        catch (Exception var11_13) {
        }
        BuildInfo.VERSION = var7_8;
        BuildInfo.DEV = var8_9;
        BuildInfo.COMMIT = var9_10;
        BuildInfo.BUILT = var10_11;
    }
}
