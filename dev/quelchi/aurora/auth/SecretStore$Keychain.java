package dev.quelchi.aurora.auth;

import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.auth.MsAuth;
import dev.quelchi.aurora.auth.SecretStore;
import dev.quelchi.aurora.auth.SecretStore$Keychain$Security;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class SecretStore$Keychain {
    private static final int ERR_ITEM_NOT_FOUND;
    private static boolean tried;
    private static boolean timedOut;
    private static byte[] key;
    private static final String[] a;
    private static final long b;

    private SecretStore$Keychain() {
    }

    
    static synchronized byte[] key() {
        int n = MsAuth.a;
        boolean bl = tried;
        if (n == 0) {
            if (bl) {
                return key;
            }
            bl = true;
        }
        tried = bl;
        FutureTask<byte[]> futureTask = new FutureTask<byte[]>(SecretStore$Keychain::fetch);
        String[] stringArray = a;
        Thread thread = new Thread(futureTask, stringArray[1]);
        thread.setDaemon(true);
        thread.start();
        try {
            key = futureTask.get(b, TimeUnit.MILLISECONDS);
            return key;
        }
        catch (TimeoutException timeoutException) {
            timedOut = true;
            stringArray = a;
            Aurora.LOG.warn(stringArray[7]);
            return key;
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[3]);
        }
        return key;
    }

    
    private static byte[] fetch() {
        int n = MsAuth.a;
        try {
            int n2;
            String[] stringArray = a;
            SecretStore$Keychain$Security secretStore$Keychain$Security = (SecretStore$Keychain$Security)Native.load((String)stringArray[8], SecretStore$Keychain$Security.class);
            byte[] byArray = stringArray[0].getBytes(StandardCharsets.UTF_8);
            byte[] byArray2 = stringArray[4].getBytes(StandardCharsets.UTF_8);
            IntByReference intByReference = new IntByReference();
            PointerByReference pointerByReference = new PointerByReference();
            int n3 = n2 = secretStore$Keychain$Security.SecKeychainFindGenericPassword(null, byArray.length, byArray, byArray2.length, byArray2, intByReference, pointerByReference, null);
            if (n == 0) {
                if (n3 == 0) {
                    try {
                        byte[] byArray3 = pointerByReference.getValue().getByteArray(0L, intByReference.getValue());
                        return byArray3;
                    }
                    finally {
                        secretStore$Keychain$Security.SecKeychainItemFreeContent(null, pointerByReference.getValue());
                    }
                }
                n3 = n2;
            }
            if (n == 0) {
                if (n3 != -25300) {
                    stringArray = a;
                    Aurora.LOG.warn(stringArray[2], (Object)n2);
                    return null;
                }
                n3 = 32;
            }
            byte[] byArray4 = new byte[n3];
            SecretStore.RANDOM.nextBytes(byArray4);
            int n4 = secretStore$Keychain$Security.SecKeychainAddGenericPassword(null, byArray.length, byArray, byArray2.length, byArray2, byArray4.length, byArray4, null);
            if (n != 0) return byArray4;
            if (n4 != 0) {
                stringArray = a;
                Aurora.LOG.warn(stringArray[6], (Object)n4);
                return null;
            }
            stringArray = a;
            Aurora.LOG.info(stringArray[5]);
            return byArray4;
        }
        catch (Throwable throwable) {
            Aurora.LOG.debug(a[9], throwable);
            return null;
        }
    }

    
    static {
        var11 = new String[10];
        var9_1 = 0;
        var8_2 = "#.E\u0000\u0003\\6+([G\\H&5$AO\u000f\u0006>AA\u0000H~\f.JM\u001aH:)4\f.JM\u001aH:)k_A\u001dB&7kUO\u001bE6#k\u001bU\u000f\u0000\u007fg*PM\u001d\\=38\u0013]\u0006H*g;_O\u001bG'\"3G8\f.JM\u001aH:)kWG\u0016\t=(?\u0013O\u001cZ$\"9\u0013G\u001c\t'.&V\u0002RH0$$F@\u0006Zs4?RWRY?&\"]Z\u0017Q'\f&(PA\u0007G'4fXK\u000b,\u0014?\\\\\u0017Ms&%\u0013O\u0011J<2%G]RB6>kZ@R];\"k_A\u0015@=g VW\u0011A2.%<\u0004$FB\u0016\t=(?\u0013]\u0006F!\"kR\u000e\u0019L*$#RG\u001c\t8\"2\u0013\u0006\tTzkkRM\u0011F&)?@\u000e\u0001]2>kCB\u0013@=3.KZ8\f.JM\u001aH:)kWG\u0016\t=(?\u0013O\u001cZ$\"9\u0013G\u001c\t'.&V\u0002RH0$$F@\u0006Zs4?RWRY?&\"]Z\u0017Q'";
        var10_3 = "#.E\u0000\u0003\\6+([G\\H&5$AO\u000f\u0006>AA\u0000H~\f.JM\u001aH:)4\f.JM\u001aH:)k_A\u001dB&7kUO\u001bE6#k\u001bU\u000f\u0000\u007fg*PM\u001d\\=38\u0013]\u0006H*g;_O\u001bG'\"3G8\f.JM\u001aH:)kWG\u0016\t=(?\u0013O\u001cZ$\"9\u0013G\u001c\t'.&V\u0002RH0$$F@\u0006Zs4?RWRY?&\"]Z\u0017Q'\f&(PA\u0007G'4fXK\u000b,\u0014?\\\\\u0017Ms&%\u0013O\u0011J<2%G]RB6>kZ@R];\"k_A\u0015@=g VW\u0011A2.%<\u0004$FB\u0016\t=(?\u0013]\u0006F!\"kR\u000e\u0019L*$#RG\u001c\t8\"2\u0013\u0006\tTzkkRM\u0011F&)?@\u000e\u0001]2>kCB\u0013@=3.KZ8\f.JM\u001aH:)kWG\u0016\t=(?\u0013O\u001cZ$\"9\u0013G\u001c\t'.&V\u0002RH0$$F@\u0006Zs4?RWRY?&\"]Z\u0017Q'".length();
        var7_4 = 18;
        var6_5 = -1;
        block11: while (true) {
            v0 = ++var6_5;
            v1 = var8_2.substring(v0, v0 + var7_4);
            v2 = -1;
            block12: while (true) {
                var8_2 = "\u0014.P[\u0000@'>-\f.JM\u001aH:)kF@\u0013_2.'RL\u001eL\u007fg*PM\u001d\\=38\u0013]\u0006H*g;_O\u001bG'\"3G";
                var10_3 = "\u0014.P[\u0000@'>-\f.JM\u001aH:)kF@\u0013_2.'RL\u001eL\u007fg*PM\u001d\\=38\u0013]\u0006H*g;_O\u001bG'\"3G".length();
                var7_4 = 8;
                var6_5 = -1;
                while (true) {
                    v3 = ++var6_5;
                    v1 = var8_2.substring(v3, v3 + var7_4);
                    v2 = 0;
lbl21:

                    v4 = v1.toCharArray();
                    v5 = v4;
                    v6 = v4.length;
                    var12_6 = 0;
                    do {
                        v5 = v5;
                        v7 = var12_6;
                        v8 = v5[v7];
                        switch (var12_6 % 7) {
                            case 0: {
                                v9 = 71;
                                break;
                            }
                            case 1: {
                                v9 = 75;
                                break;
                            }
                            case 2: {
                                v9 = 51;
                                break;
                            }
                            case 3: {
                                v9 = 46;
                                break;
                            }
                            case 4: {
                                v9 = 114;
                                break;
                            }
                            case 5: {
                                v9 = 41;
                                break;
                            }
                            default: {
                                v9 = 83;
                            }
                        }
                        v5[v7] = (char)(v8 ^ v9);
                        ++var12_6;
lbl53:

                        v6 = v6;
                    } while (v6 > var12_6);
                    v10 = new String(v5);
                    switch (v2) {
                        default: {
                            var11[var9_1++] = v10.intern();
                            if ((var6_5 += var7_4) >= var10_3) continue block12;
                            var7_4 = var8_2.charAt(var6_5);
                            continue block11;
                        }
                        case 0: 
                    }
                    var11[var9_1++] = v10.intern();
                    if ((var6_5 += var7_4) >= var10_3) {
                        SecretStore$Keychain.a = var11;
                        var4_7 = 4892077784396700041L;
                        var2_8 = 1706802190715454629L ^ var4_7;
                        SecretStore$Keychain.ERR_ITEM_NOT_FOUND = (int)var2_8;
                        var0_9 = 6213342973326676784L;
                        SecretStore$Keychain.b = 6213342973326672056L ^ var0_9;
                        return;
                    }
                    var7_4 = var8_2.charAt(var6_5);
                }
                break;
            }
            break;
        }
    }
}
