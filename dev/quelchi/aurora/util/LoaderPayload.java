package dev.quelchi.aurora.util;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.util.KeyUtil;

public final class LoaderPayload {
    private static final Class<?> API;
    private static final String[] a;

    private LoaderPayload() {
    }

    private static Class<?> find() {
        try {
            return Class.forName(a[5]);
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    
    private static Object call(String var0, Class<?>[] var1_1, Object ... var2_2) {
        block6: {
            block7: {
                var3_3 = KeyUtil.a;
                v0 = LoaderPayload.API;
                if (var3_3 != 0) // goto
                if (v0 != null) break block6;
                break block7;
                catch (Throwable v1) {
                    throw v1;
                }
            }
            return null;
        }
        try {
            v0 = LoaderPayload.API;
lbl17:

            var4_4 = v0.getMethod(var0, var1_1);
            return var4_4.invoke(null, var2_2);
        }
        catch (Throwable var4_5) {
            Aurora.LOG.debug(LoaderPayload.a[6], (Object)var0, (Object)var4_5);
            return null;
        }
    }

    public static boolean inMemory() {
        return Boolean.TRUE.equals(LoaderPayload.call(a[2], new Class[0], new Object[0]));
    }

    public static String sha256() {
        return (String)LoaderPayload.call(a[4], new Class[0], new Object[0]);
    }

    public static byte[] jar() {
        return (byte[])LoaderPayload.call(a[3], new Class[0], new Object[0]);
    }

    public static boolean stage(byte[] byArray, String string) {
        boolean bl;
        block4: {
            boolean bl2;
            block6: {
                block5: {
                    int n = KeyUtil.a;
                    bl = Boolean.TRUE.equals(LoaderPayload.call(a[1], new Class[]{byte[].class, String.class}, byArray, string));
                    if (n == 0) break block4;
                    if (!Module.a) break block5;
                    bl2 = false;
                    break block6;
                }
                bl2 = true;
            }
            Module.a = bl2;
        }
        return bl;
    }

    public static void unstage() {
        LoaderPayload.call(a[0], new Class[0], new Object[0]);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "\\\u00164,\u0006G\u007f\u0005Z\f&?\u0002\b@\u0016\n=\nOhP\u0003C\u00195\u0006Z\u0010&jR\u0016";
                var4_3 = "\\\u00164,\u0006G\u007f\u0005Z\f&?\u0002\b@\u0016\n=\nOhP\u0003C\u00195\u0006Z\u0010&jR\u0016".length();
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
                    var2_2 = "M\u001d1v\u0016U\u007fE\u001b/1IAo[\u001759ILuH\u001c\"*Ip{P\u0014(9\u0003\u0015e\u0017&<\u0002R:J\u0019+4G[g\t\u001e&1\u000bE~";
                    var4_3 = "M\u001d1v\u0016U\u007fE\u001b/1IAo[\u001759ILuH\u001c\"*Ip{P\u0014(9\u0003\u0015e\u0017&<\u0002R:J\u0019+4G[g\t\u001e&1\u000bE~".length();
                    var1_4 = 33;
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
                        v10 = 41;
                        break;
                    }
                    case 1: {
                        v10 = 120;
                        break;
                    }
                    case 2: {
                        v10 = 71;
                        break;
                    }
                    case 3: {
                        v10 = 88;
                        break;
                    }
                    case 4: {
                        v10 = 103;
                        break;
                    }
                    case 5: {
                        v10 = 32;
                        break;
                    }
                    default: {
                        v10 = 26;
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
        LoaderPayload.a = var5;
        LoaderPayload.API = LoaderPayload.find();
    }
}
