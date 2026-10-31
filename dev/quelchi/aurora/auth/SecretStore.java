package dev.quelchi.aurora.auth;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.auth.MsAuth;
import dev.quelchi.aurora.auth.SecretStore$Dpapi;
import dev.quelchi.aurora.auth.SecretStore$Keychain;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class SecretStore {
    public static final String DPAPI;
    public static final String KEYCHAIN;
    private static final long KEYCHAIN_TIMEOUT_MS;
    private static final String KEYCHAIN_SERVICE;
    private static final String KEYCHAIN_ACCOUNT;
    private static final int GCM_NONCE_BYTES;
    private static final int GCM_TAG_BITS;
    private static final SecureRandom RANDOM;
    private static Boolean windows;
    private static Boolean mac;
    private static final String[] a;

    private SecretStore() {
    }

    private static boolean isWindows() {
        Boolean bl;
        block4: {
            block5: {
                int n = MsAuth.a;
                bl = windows;
                if (n != 0) break block4;
                if (bl != null) break block5;
                windows = System.getProperty(a[8], "").toLowerCase().contains(a[9]);
            }
            bl = windows;
        }
        return bl;
    }

    private static boolean isMac() {
        Boolean bl;
        block8: {
            block9: {
                boolean bl2;
                block10: {
                    block12: {
                        block11: {
                            int n = MsAuth.a;
                            bl = mac;
                            if (n != 0) break block8;
                            if (bl != null) break block9;
                            String[] stringArray = a;
                            String string = System.getProperty(stringArray[0], "").toLowerCase();
                            bl2 = string.contains(stringArray[7]);
                            if (n != 0) break block10;
                            if (bl2) break block11;
                            bl2 = string.contains(a[3]);
                            if (n != 0) break block10;
                            if (!bl2) break block12;
                        }
                        bl2 = true;
                        break block10;
                    }
                    bl2 = false;
                }
                mac = bl2;
            }
            bl = mac;
        }
        return bl;
    }

    public static synchronized String scheme() {
        block14: {
            String string;
            block16: {
                block15: {
                    boolean bl;
                    block10: {
                        block11: {
                            String string2;
                            block13: {
                                block12: {
                                    int n = MsAuth.a;
                                    bl = SecretStore.isWindows();
                                    if (n != 0) break block10;
                                    if (!bl) break block11;
                                    if (!SecretStore$Dpapi.usable()) break block12;
                                    string2 = a[1];
                                    break block13;
                                }
                                string2 = null;
                            }
                            return string2;
                        }
                        bl = SecretStore.isMac();
                    }
                    if (!bl) break block14;
                    if (SecretStore$Keychain.key() == null) break block15;
                    string = a[13];
                    break block16;
                }
                string = null;
            }
            return string;
        }
        return null;
    }

    public static byte[] protect(byte[] byArray) {
        int n = MsAuth.a;
        try {
            String string;
            String string2;
            block5: {
                block6: {
                    string2 = SecretStore.scheme();
                    string = a[5];
                    if (n != 0) break block5;
                    if (!string.equals(string2)) break block6;
                    return SecretStore$Dpapi.protect(byArray);
                }
                String[] stringArray = a;
                string = stringArray[12];
            }
            if (string.equals(string2)) {
                return SecretStore.encrypt(SecretStore$Keychain.key(), byArray);
            }
        }
        catch (Throwable throwable) {
            Aurora.LOG.warn(a[11], throwable);
        }
        return null;
    }

    
    public static byte[] unprotect(String string, byte[] byArray) {
        int n = MsAuth.a;
        try {
            byte[] byArray4;
            String[] stringArray;
            String string2 = a[5];
            String string3 = string;
            if (n == 0) {
                if (string2.equals(string3)) {
                    return SecretStore$Dpapi.unprotect(byArray);
                }
                stringArray = a;
                string2 = stringArray[12];
                string3 = string;
            }
            if (!string2.equals(string3)) {
                stringArray = a;
                Aurora.LOG.warn(stringArray[4], (Object)string);
                return null;
            }
            byte[] byArray2 = byArray4 = SecretStore$Keychain.key();
            if (n == 0) {
                if (byArray2 == null) {
                    return null;
                }
                byArray2 = byArray4;
            }
            byte[] byArray3 = SecretStore.decrypt(byArray2, byArray);
            return byArray3;
        }
        catch (Throwable throwable) {
            Aurora.LOG.warn(a[2], throwable);
        }
        return null;
    }

    public static synchronized boolean keychainTimedOut() {
        boolean bl;
        block6: {
            block5: {
                int n;
                block4: {
                    n = MsAuth.a;
                    bl = SecretStore.isMac();
                    if (n != 0) break block4;
                    if (!bl) break block5;
                    bl = SecretStore$Keychain.timedOut;
                }
                if (n != 0) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    private static byte[] encrypt(byte[] byArray, byte[] byArray2) throws Exception {
        byte[] byArray3 = new byte[12];
        RANDOM.nextBytes(byArray3);
        String[] stringArray = a;
        Cipher cipher = Cipher.getInstance(stringArray[15]);
        cipher.init(1, (Key)new SecretKeySpec(byArray, stringArray[17]), new GCMParameterSpec(128, byArray3));
        byte[] byArray4 = cipher.doFinal(byArray2);
        byte[] byArray5 = new byte[byArray3.length + byArray4.length];
        System.arraycopy(byArray3, 0, byArray5, 0, byArray3.length);
        System.arraycopy(byArray4, 0, byArray5, byArray3.length, byArray4.length);
        return byArray5;
    }

    private static byte[] decrypt(byte[] byArray, byte[] byArray2) throws Exception {
        if (byArray2.length <= 12) {
            throw new IllegalArgumentException(a[14]);
        }
        String[] stringArray = a;
        Cipher cipher = Cipher.getInstance(stringArray[18]);
        cipher.init(2, (Key)new SecretKeySpec(byArray, stringArray[10]), new GCMParameterSpec(128, Arrays.copyOf(byArray2, 12)));
        return cipher.doFinal(byArray2, 12, byArray2.length - 12);
    }

    
    static {
        block25: {
            block24: {
                block23: {
                    block22: {
                        block21: {
                            var17 = new String[19];
                            var15_1 = 0;
                            var14_2 = "7-X2e}0\u0005<.\u0017,m!\u001b1\u00030`0;7*V.aq1x.\u00043pu6,;\u0012|es67+\u0018(w\u0006<?\u0004+m~*\u000b?\u00009`04;=\u0019)jd&x+\u00059$e;30\u0019+j0%*1\u00029gd<70V{\u007fmr\u0005<.\u0017,m\u0012<;\u0000rue04=\u001e5*q *1\u0004=\u00035?\u0015\u00077-X2e}0\u0003/7\u0018\u0003\u0019\u001b%;\u001b1\u00030`0;7*V,v\u007f!==\u0002|wq#=:V=gs:-0\u0002/(0392\u001a5jwu:?\u00157$d:x.\u001a=m~!=&\u0002\u00103;\u000f?lq<6s\u00179w=2;3\u00103;\u000f?lq<6s\u00179w=2;3\t,,\u00032gq!=:\u0011\u0019\u001b%sCS\u0018w\u0010\u0019\fet110\u0011\f9=\u00153q~!+s\u001d9}";
                            var16_3 = "7-X2e}0\u0005<.\u0017,m!\u001b1\u00030`0;7*V.aq1x.\u00043pu6,;\u0012|es67+\u0018(w\u0006<?\u0004+m~*\u000b?\u00009`04;=\u0019)jd&x+\u00059$e;30\u0019+j0%*1\u00029gd<70V{\u007fmr\u0005<.\u0017,m\u0012<;\u0000rue04=\u001e5*q *1\u0004=\u00035?\u0015\u00077-X2e}0\u0003/7\u0018\u0003\u0019\u001b%;\u001b1\u00030`0;7*V,v\u007f!==\u0002|wq#=:V=gs:-0\u0002/(0392\u001a5jwu:?\u00157$d:x.\u001a=m~!=&\u0002\u00103;\u000f?lq<6s\u00179w=2;3\u00103;\u000f?lq<6s\u00179w=2;3\t,,\u00032gq!=:\u0011\u0019\u001b%sCS\u0018w\u0010\u0019\fet110\u0011\f9=\u00153q~!+s\u001d9}".length();
                            var13_4 = 7;
                            var12_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var12_5;
                                v1 = var14_2.substring(v0, v0 + var13_4);
                                v2 = -1;
                                break block21;
                                break;
                            }
lbl12:

                            while (true) {
                                var17[var15_1++] = v3.intern();
                                if ((var12_5 += var13_4) < var16_3) {
                                    var13_4 = var14_2.charAt(var12_5);
                                    continue;
                                }
                                var14_2 = "\u0019\u001b%\u0011\u0019\u001b%sCS\u0018w\u0010\u0019\fet110\u0011";
                                var16_3 = "\u0019\u001b%\u0011\u0019\u001b%sCS\u0018w\u0010\u0019\fet110\u0011".length();
                                var13_4 = 3;
                                var12_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var12_5;
                                    v1 = var14_2.substring(v4, v4 + var13_4);
                                    v2 = 0;
                                    break block21;
                                    break;
                                }
                                break;
                            }
lbl26:

                            while (true) {
                                var17[var15_1++] = v3.intern();
                                if ((var12_5 += var13_4) < var16_3) {
                                    var13_4 = var14_2.charAt(var12_5);
                                    continue;
                                }
                                break block22;
                                break;
                            }
                        }
                        v5 = v1.toCharArray();
                        v6 = v5;
                        v7 = v5.length;
                        var18_6 = 0;
                        do {
                            v6 = v6;
                            v8 = var18_6;
                            v9 = v6[v8];
                            switch (var18_6 % 7) {
                                case 0: {
                                    v10 = 88;
                                    break;
                                }
                                case 1: {
                                    v10 = 94;
                                    break;
                                }
                                case 2: {
                                    v10 = 118;
                                    break;
                                }
                                case 3: {
                                    v10 = 92;
                                    break;
                                }
                                case 4: {
                                    v10 = 4;
                                    break;
                                }
                                case 5: {
                                    v10 = 16;
                                    break;
                                }
                                default: {
                                    v10 = 85;
                                }
                            }
                            v6[v8] = (char)(v9 ^ v10);
                            ++var18_6;
lbl65:

                            v7 = v7;
                        } while (v7 > var18_6);
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
                    SecretStore.a = var17;
                    SecretStore.DPAPI = SecretStore.a[5];
                    SecretStore.KEYCHAIN_SERVICE = SecretStore.a[6];
                    SecretStore.KEYCHAIN_ACCOUNT = SecretStore.a[16];
                    SecretStore.KEYCHAIN = SecretStore.a[12];
                    var5_7 = 5108555117645966306L;
                    var4_8 = new long[2];
                    var8_9 = 0;
                    var9_10 = "\u0013\u0082q\u00c0.\u00ca\u00ab\u00ee\u001e#B..\u00ca\u00abb";
                    var10_11 = "\u0013\u0082q\u00c0.\u00ca\u00ab\u00ee\u001e#B..\u00ca\u00abb".length();
                    var7_12 = 0;
                    while (true) {
                        break block23;
                        break;
                    }
lbl89:

                    while (true) {
                        var4_8[v11] = (((long)var11_13[0] & 255L) << 56 | ((long)var11_13[1] & 255L) << 48 | ((long)var11_13[2] & 255L) << 40 | ((long)var11_13[3] & 255L) << 32 | ((long)var11_13[4] & 255L) << 24 | ((long)var11_13[5] & 255L) << 16 | ((long)var11_13[6] & 255L) << 8 | (long)var11_13[7] & 255L) ^ var5_7;
                        if (var7_12 < var10_11) continue;
                        break block24;
                        break;
                    }
                }
                var11_13 = var9_10.substring(var7_12, var7_12 += 8).getBytes("ISO-8859-1");
                v11 = var8_9++;
                while (true)
            }
            SecretStore.GCM_NONCE_BYTES = (int)var4_8[0];
            SecretStore.GCM_TAG_BITS = (int)var4_8[1];
            break block25;
lbl105:

            while (true) {
                continue;
                break;
            }
        }
        var2_14 = 1438856974889397911L;
        while (true)
        SecretStore.KEYCHAIN_TIMEOUT_MS = var0_15 = 1438856974889401631L ^ var2_14;
        SecretStore.RANDOM = new SecureRandom();
    }
}
