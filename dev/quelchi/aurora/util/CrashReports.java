package dev.quelchi.aurora.util;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.util.KeyUtil;
import java.lang.invoke.LambdaMetafactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import java.util.regex.Pattern;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

public final class CrashReports {
    private static final int MAX;
    private static final long RECENT_MS;
    private static volatile Path found;
    private static volatile String redacted;
    private static final Pattern IPV4;
    private static final Pattern WIN_USER;
    private static final Pattern NIX_USER;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private CrashReports() {
    }

    public static void dismiss() {
        found = null;
        redacted = null;
    }

    
    public static void scan() {
        block37: {
            var0 = KeyUtil.a;
            try {
                block39: {
                    block38: {
                        block43: {
                            block42: {
                                block35: {
                                    block36: {
                                        block33: {
                                            block34: {
                                                block31: {
                                                    block30: {
                                                        block40: {
                                                            block41: {
                                                                block28: {
                                                                    block29: {
                                                                        var8_1 = CrashReports.a;
                                                                        var1_2 = FabricLoader.getInstance().getGameDir().resolve(var8_1[7]);
                                                                        v0 = var1_2;
                                                                        if (var0 != 0) break block28;
                                                                        if (Files.isDirectory(v0, new LinkOption[0])) break block29;
                                                                        return;
                                                                    }
                                                                    var8_1 = CrashReports.a;
                                                                    v0 = FabricLoader.getInstance().getConfigDir().resolve(var8_1[14]);
                                                                }
                                                                var2_4 = v0;
                                                                v2 = var2_4;
                                                                if (var0 != 0) break block40;
                                                                if (!Files.exists(v2, new LinkOption[0])) break block30;
                                                                break block41;
                                                                catch (Throwable v3) {
                                                                    throw v3;
                                                                }
                                                            }
                                                            v2 = var2_4;
                                                        }
                                                        v5 = Files.readString(v2, StandardCharsets.UTF_8).trim();
                                                        break block31;
                                                    }
                                                    v5 = "";
                                                }
                                                var3_5 = v5;
                                                var5_6 = Files.list(var1_2);
                                                try {
                                                    var4_7 = var5_6.filter((Predicate<Path>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$scan$0(java.nio.file.Path ), (Ljava/nio/file/Path;)Z)()).max(Comparator.comparingLong((ToLongFunction<Path>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)J, lambda$scan$1(java.nio.file.Path ), (Ljava/nio/file/Path;)J)())).orElse(null);
                                                    if (var0 != 0) goto lbl_neg_1000
                                                }
                                                catch (Throwable var6_8) {
                                                    block32: {
                                                        if (var0 != 0 || var5_6 == null) break block32;
                                                        try {
                                                            var5_6.close();
                                                        }
                                                        catch (Throwable var7_9) {
                                                            var6_8.addSuppressed(var7_9);
                                                        }
                                                    }
                                                    throw var6_8;
                                                }
lbl_neg_1000:

                                                {
                                                    if (var5_6 == null) // goto
                                                    var5_6.close();
                                                }
lbl_neg_1000:

                                                {
                                                }
lbl63:

                                                v8 = var4_7;
                                                if (var0 != 0) break block33;
                                                if (v8 != null) break block34;
                                                return;
                                            }
                                            v8 = var4_7.getFileName();
                                        }
                                        v10 = v8.toString().equals(var3_5);
                                        if (var0 != 0) break block35;
                                        if (v10 == 0) break block36;
                                        return;
                                    }
                                    cfr_temp_0 = System.currentTimeMillis() - var4_7.toFile().lastModified() - CrashReports.b(22787, 6482851025647906872L);
                                    v10 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                }
                                if (v10 > 0) {
                                    return;
                                }
                                var5_6 = Files.readString(var4_7, StandardCharsets.UTF_8);
                                if (var0 != 0) break block37;
                                if (var5_6.contains(CrashReports.a[15])) break block38;
                                break block42;
                                catch (Throwable v12) {
                                    throw v12;
                                }
                            }
                            v13 = var5_6;
                            if (var0 != 0) break block39;
                            break block43;
                            catch (Throwable v14) {
                                throw v14;
                            }
                        }
                        if (v13.contains(CrashReports.a[8])) break block38;
                        Files.writeString(var2_4, (CharSequence)var4_7.getFileName().toString(), StandardCharsets.UTF_8, new OpenOption[0]);
                        return;
                    }
                    CrashReports.found = var4_7;
                    v13 = CrashReports.redact((String)var5_6);
                }
                CrashReports.redacted = v13;
                Files.writeString(var2_4, (CharSequence)var4_7.getFileName().toString(), StandardCharsets.UTF_8, new OpenOption[0]);
                var8_1 = CrashReports.a;
                Aurora.LOG.info(var8_1[6]);
            }
            catch (Exception var1_3) {
                Aurora.LOG.debug(CrashReports.a[0], (Throwable)var1_3);
            }
        }
    }

    
    static String redact(String string) {
        int n;
        String string2;
        block20: {
            string2 = string;
            String[] stringArray = a;
            string2 = WIN_USER.matcher(string2).replaceAll(stringArray[4]);
            n = KeyUtil.a;
            string2 = NIX_USER.matcher(string2).replaceAll(stringArray[12]);
            string2 = IPV4.matcher(string2).replaceAll(stringArray[5]);
            try {
                UUID uUID;
                MinecraftClient minecraftClient;
                block22: {
                    String string3;
                    String string4;
                    block21: {
                        MinecraftClient minecraftClient2 = minecraftClient = MinecraftClient.method_1551();
                        if (n == 0) {
                            if (minecraftClient2 == null) break block20;
                            minecraftClient2 = minecraftClient;
                        }
                        if (n == 0) {
                            if (minecraftClient2.method_1548() == null) break block20;
                            minecraftClient2 = minecraftClient;
                        }
                        string3 = string4 = minecraftClient2.method_1548().method_1676();
                        if (n != 0) break block21;
                        if (string3 == null) break block22;
                        string3 = string4;
                    }
                    if (n == 0 && string3.length() > 2) {
                        string3 = string2 = string2.replace(string4, a[9]);
                    }
                }
                if ((uUID = minecraftClient.method_1548().method_44717()) != null) {
                    stringArray = a;
                    string2 = string2.replace(uUID.toString(), stringArray[3]).replace(uUID.toString().replace("-", ""), stringArray[1]);
                }
            }
            catch (Exception exception) {
            }
        }
        Object object = string2;
        if (n != 0) return object;
        if (((String)object).length() > CrashReports.a(18883, 8910248659798813491L)) {
            object = string2.substring(0, CrashReports.a(9095, 9017737048634425717L)) + a[16];
            return object;
        }
        object = string2;
        return object;
    }

    private static long lambda$scan$1(Path path) {
        return path.toFile().lastModified();
    }

    private static boolean lambda$scan$0(Path path) {
        return path.getFileName().toString().endsWith(a[10]);
    }

    
    static {
        block27: {
            block26: {
                block25: {
                    block24: {
                        block23: {
                            block22: {
                                var21 = new String[17];
                                var19_1 = 0;
                                var18_2 = "\f8bm_\u0004M*:llC\u0004L,+m>QEV#/g\u0006s?vwS\u001a\u001b\u0013(+!\rx[4{/-Jx\u0011f10ck@D~f0ckF\u0006s?vwS\u001a\bk{?kDAMq\u0004s#s =\u000e?qqEE\u0005o+#}EEL'jq{GKM;jelXI\u001f#+pj\u0017WZ<9jqY\u0004V<jbhVMS.(o{\u0017PPo9fpS\r,8bm_\tM*:llCW\r.?qqEE\u0011\"#{wYW\bs:o\u007fNAMq\u0004a>{j\u0019ge+!\rLP\"/\u007fKDAM<c,7lz\u0010\u00139^5\bk{?kDAMq\u001fguj7\u001f\u007f~b\u0010^$kxj</qmkx\u0016\u0014\u0014_BkVc!\u0017(\u0015.?qqEE`#+pjhGM.9k0C\\K";
                                var20_3 = "\f8bm_\u0004M*:llC\u0004L,+m>QEV#/g\u0006s?vwS\u001a\u001b\u0013(+!\rx[4{/-Jx\u0011f10ck@D~f0ckF\u0006s?vwS\u001a\bk{?kDAMq\u0004s#s =\u000e?qqEE\u0005o+#}EEL'jq{GKM;jelXI\u001f#+pj\u0017WZ<9jqY\u0004V<jbhVMS.(o{\u0017PPo9fpS\r,8bm_\tM*:llCW\r.?qqEE\u0011\"#{wYW\bs:o\u007fNAMq\u0004a>{j\u0019ge+!\rLP\"/\u007fKDAM<c,7lz\u0010\u00139^5\bk{?kDAMq\u001fguj7\u001f\u007f~b\u0010^$kxj</qmkx\u0016\u0014\u0014_BkVc!\u0017(\u0015.?qqEE`#+pjhGM.9k0C\\K".length();
                                var17_4 = 24;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block22;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "+/u0FQZ#)kw\u0019EJ=%q\u007f\fE\u206c#jEQQ,+w{S";
                                    var20_3 = "+/u0FQZ#)kw\u0019EJ=%q\u007f\fE\u206c#jEQQ,+w{S".length();
                                    var17_4 = 18;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block22;
                                        break;
                                    }
                                    break;
                                }
lbl26:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    break block23;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_7 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_7;
                                v9 = v6[v8];
                                switch (var22_7 % 7) {
                                    case 0: {
                                        v10 = 79;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 74;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 3;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 30;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 55;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 36;
                                        break;
                                    }
                                    default: {
                                        v10 = 63;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_7;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_7);
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
                        CrashReports.a = var21;
                        var8_8 = 7868003682280056016L;
                        var14_9 = new long[3];
                        var11_10 = 0;
                        var12_11 = "\u00cb\u0012e\u00f4\u000ep\u0090s9z\u000e`\u00fee\u000f6\u00d8\u008c\"\u0000\u00c8y\u00fa5";
                        var13_12 = "\u00cb\u0012e\u00f4\u000ep\u0090s9z\u000e`\u00fee\u000f6\u00d8\u008c\"\u0000\u00c8y\u00fa5".length();
                        var10_13 = 0;
                        while (true) {
                            break block24;
                            break;
                        }
lbl84:

                        while (true) {
                            var14_9[v11] = (((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L) ^ var8_8;
                            if (var10_13 < var13_12) continue;
                            break block25;
                            break;
                        }
                    }
                    var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                    v11 = var11_10++;
                    while (true)
                }
                CrashReports.b = var14_9;
                CrashReports.c = new Integer[3];
                CrashReports.MAX = CrashReports.a(22151, 8347941447426700406L);
                var0_15 = 1117462442392214478L;
                var6_16 = new long[2];
                var3_17 = 0;
                var4_18 = "Vu\u00b0\u0082\u00be\u0003\u00b7\u00f6rv\u00a3HC\u00c24<";
                var5_19 = "Vu\u00b0\u0082\u00be\u0003\u00b7\u00f6rv\u00a3HC\u00c24<".length();
                var2_20 = 0;
                while (true) {
                    break block26;
                    break;
                }
lbl107:

                while (true) {
                    var6_16[v12] = (((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L) ^ var0_15;
                    if (var2_20 < var5_19) continue;
                    break block27;
                    break;
                }
            }
            var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
            v12 = var3_17++;
            while (true)
        }
        CrashReports.d = var6_16;
        CrashReports.e = new Long[2];
        CrashReports.RECENT_MS = CrashReports.b(23240, 9076060997944652786L);
        var16_6 = CrashReports.a;
        CrashReports.IPV4 = Pattern.compile(var16_6[2]);
        CrashReports.WIN_USER = Pattern.compile(var16_6[13]);
        CrashReports.NIX_USER = Pattern.compile(var16_6[11]);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x22F0;
        if (c[n2] == null) {
            CrashReports.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x693B) & Short.MAX_VALUE;
        if (e[n2] == null) {
            CrashReports.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
