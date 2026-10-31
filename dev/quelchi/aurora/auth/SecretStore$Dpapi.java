package dev.quelchi.aurora.auth;

import com.sun.jna.platform.win32.Crypt32Util;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.auth.MsAuth;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

final class SecretStore$Dpapi {
    private static Boolean usable;
    private static final String[] a;

    private SecretStore$Dpapi() {
    }

    static synchronized boolean usable() {
        Boolean bl;
        block4: {
            block5: {
                int n = MsAuth.a;
                bl = usable;
                if (n != 0) break block4;
                if (bl != null) break block5;
                try {
                    String[] stringArray = a;
                    byte[] byArray = stringArray[0].getBytes(StandardCharsets.UTF_8);
                    usable = Arrays.equals(byArray, SecretStore$Dpapi.unprotect(SecretStore$Dpapi.protect(byArray)));
                }
                catch (Throwable throwable) {
                    Aurora.LOG.debug(a[1], throwable);
                    usable = false;
                }
            }
            bl = usable;
        }
        return bl;
    }

    static byte[] protect(byte[] byArray) {
        return Crypt32Util.cryptProtectData((byte[])byArray);
    }

    static byte[] unprotect(byte[] byArray) {
        return Crypt32Util.cryptUnprotectData((byte[])byArray);
    }

    
    static {
        block12: {
            var5 = new String[2];
            var3_1 = 0;
            var2_2 = "\u0000Y%sd\u0012*%|\u0016L_SX\u000fM!}\u007f\u001fL\u0003@206\u0012N\u0002C\"rb\u0000\r\u0012X6e6\u0003A\u0000E9hs\u000bY";
            var4_3 = "\u0000Y%sd\u0012*%|\u0016L_SX\u000fM!}\u007f\u001fL\u0003@206\u0012N\u0002C\"rb\u0000\r\u0012X6e6\u0003A\u0000E9hs\u000bY".length();
            var1_4 = 6;
            var0_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var5[var3_1++] = new String(v0).intern();
                if ((var0_5 += var1_4) < var4_3) {
                    var1_4 = var2_2.charAt(var0_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var0_5;
            v2 = var2_2.substring(v1, v1 + var1_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 97;
                        break;
                    }
                    case 1: {
                        v6 = 44;
                        break;
                    }
                    case 2: {
                        v6 = 87;
                        break;
                    }
                    case 3: {
                        v6 = 28;
                        break;
                    }
                    case 4: {
                        v6 = 22;
                        break;
                    }
                    case 5: {
                        v6 = 115;
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
        SecretStore$Dpapi.a = var5;
    }
}
