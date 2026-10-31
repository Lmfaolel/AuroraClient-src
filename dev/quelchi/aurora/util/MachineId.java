package dev.quelchi.aurora.util;

import dev.quelchi.aurora.util.KeyUtil;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class MachineId {
    private static volatile String cached;
    private static final String[] a;

    private MachineId() {
    }

    
    public static String get() {
        Object object;
        Object object2;
        String[] stringArray;
        StringBuilder stringBuilder;
        int n;
        block21: {
            String string = cached;
            n = KeyUtil.a;
            String string2 = string;
            if (n != 0) return string2;
            if (string2 != null) {
                string2 = string;
                return string2;
            }
            stringBuilder = new StringBuilder();
            stringArray = a;
            stringBuilder.append(System.getenv(stringArray[3])).append('|').append(System.getProperty(stringArray[6])).append('|').append(System.getProperty(stringArray[0])).append('|').append(System.getProperty(stringArray[4]));
            try {
                object2 = NetworkInterface.getNetworkInterfaces();
                while (object2.hasMoreElements() && n == 0) {
                    block23: {
                        Object object3;
                        byte[] byArray;
                        block24: {
                            object = object2.nextElement();
                            byArray = ((NetworkInterface)object).getHardwareAddress();
                            if (byArray == null) break block23;
                            object3 = object;
                            if (n != 0) break block24;
                            if (((NetworkInterface)object3).isLoopback()) break block23;
                            object3 = object;
                        }
                        if (!((NetworkInterface)object3).isVirtual()) {
                            for (byte by : byArray) {
                                if (n == 0) {
                                    stringBuilder.append(String.format(a[1], by));
                                    if (n == 0) continue;
                                }
                                break block21;
                            }
                            if (n == 0) break block21;
                        }
                    }
                    if (n == 0) continue;
                    break;
                }
            }
            catch (Exception exception) {
            }
        }
        try {
            Object object4;
            block22: {
                stringArray = a;
                object2 = MessageDigest.getInstance(stringArray[5]).digest(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
                object = new StringBuilder();
                for (int i = 0; i < 16; ++i) {
                    object4 = object;
                    if (n == 0) {
                        ((StringBuilder)object4).append(String.format(a[2], (byte)object2[i]));
                        if (n == 0) continue;
                    }
                    break block22;
                }
                object4 = object;
            }
            cached = ((StringBuilder)object4).toString();
            return cached;
        }
        catch (Exception exception) {
            cached = Integer.toHexString(stringBuilder.toString().hashCode());
        }
        return cached;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "\u001eN\\\u0015fYr\u0004T\r@\u0003\u0004T\r@\u0003\f2r?+R`R#s36B\u0007\u001eN\\\u001auW\u007f";
                var4_3 = "\u001eN\\\u0015fYr\u0004T\r@\u0003\u0004T\r@\u0003\f2r?+R`R#s36B\u0007\u001eN\\\u001auW\u007f".length();
                var1_4 = 7;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "\"u3V5\u0001!\t\u0004N\u0017\t)Zv\u001cX";
                    var4_3 = "\"u3V5\u0001!\t\u0004N\u0017\t)Zv\u001cX".length();
                    var1_4 = 7;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block18;
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
                        v10 = 113;
                        break;
                    }
                    case 1: {
                        v10 = 61;
                        break;
                    }
                    case 2: {
                        v10 = 114;
                        break;
                    }
                    case 3: {
                        v10 = 123;
                        break;
                    }
                    case 4: {
                        v10 = 7;
                        break;
                    }
                    case 5: {
                        v10 = 52;
                        break;
                    }
                    default: {
                        v10 = 23;
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
        MachineId.a = var5;
    }
}
