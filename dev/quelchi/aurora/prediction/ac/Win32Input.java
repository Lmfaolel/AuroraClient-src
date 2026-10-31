package dev.quelchi.aurora.prediction.ac;

import dev.quelchi.aurora.prediction.ac.InputAudit;
import java.nio.ByteBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.Platform;
import org.lwjgl.system.SharedLibrary;

final class Win32Input {
    static final int IMO_UNAVAILABLE = 0;
    static final int IMO_INJECTED;
    static final int IMO_SYSTEM;
    private static SharedLibrary user32;
    private static long fn;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private Win32Input() {
    }

    
    static int source() {
        block28: {
            block27: {
                block24: {
                    block26: {
                        block25: {
                            block23: {
                                block22: {
                                    block20: {
                                        block21: {
                                            block30: {
                                                var0 = InputAudit.a;
                                                v0 = Win32Input.fn;
                                                if (var0 != 0) break block20;
                                                if (v0 != Win32Input.a(7135, 3780927542610299480L)) break block21;
                                                break block30;
                                                catch (Throwable v1) {
                                                    throw v1;
                                                }
                                            }
                                            Win32Input.fn = Win32Input.lookup();
                                        }
                                        v0 = Win32Input.fn;
                                    }
                                    cfr_temp_0 = v0 - 0L;
                                    v3 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                    if (var0 != 0) break block22;
                                    if (v3 != false) break block23;
                                    v3 = -1;
                                }
                                return (int)v3;
                            }
                            var1_1 = MemoryStack.stackPush();
                            try {
                                var2_2 = var1_1.calloc(8);
                                v5 = var2_2;
                                if (var0 == 0) {
                                    if (JNI.callPI((long)MemoryUtil.memAddress((ByteBuffer)v5), (long)Win32Input.fn) != 0) break block24;
                                }
                                var3_4 = -1;
                            }
                            catch (Throwable var2_3) {
                                block29: {
                                    if (var0 != 0 || var1_1 == null) break block29;
                                    try {
                                        var1_1.close();
                                    }
                                    catch (Throwable var3_6) {
                                        var2_3.addSuppressed(var3_6);
                                    }
                                }
                                throw var2_3;
                            }
                            v8 = var1_1;
                            if (var0 != 0) break block25;
                            if (v8 == null) break block26;
                            v8 = var1_1;
                        }
                        v8.close();
                    }
                    return var3_4;
                }
                v5 = var2_2;
lbl70:

                var3_5 = v5.getInt(0) << 8 | var2_2.getInt(4) & 255;
                v10 = var1_1;
                if (var0 != 0) break block27;
                if (v10 == null) break block28;
                v10 = var1_1;
            }
            v10.close();
        }
        return var3_5;
    }

    private static long lookup() {
        if (Platform.get() != Platform.WINDOWS) {
            return 0L;
        }
        try {
            String[] stringArray = a;
            user32 = Library.loadNative(Win32Input.class, (String)stringArray[1], (String)stringArray[2]);
            return user32.getFunctionAddress((CharSequence)stringArray[0]);
        }
        catch (Throwable throwable) {
            return 0L;
        }
    }

    
    static {
        block20: {
            block19: {
                block18: {
                    block17: {
                        block16: {
                            var21 = new String[3];
                            var19_1 = 0;
                            var18_2 = "\u00063\u0010Zq#l$8\u0010Pj!k5\u001b\u0001jw0y$\u0005\u000blv2{\t.$\u00037h&t&:\u00064%\u0001k7c";
                            var20_3 = "\u00063\u0010Zq#l$8\u0010Pj!k5\u001b\u0001jw0y$\u0005\u000blv2{\t.$\u00037h&t&:\u00064%\u0001k7c".length();
                            var17_4 = 28;
                            var16_5 = -1;
lbl7:

                            while (true) {
                                continue;
                                break;
                            }
lbl9:

                            while (true) {
                                var21[var19_1++] = new String(v0).intern();
                                if ((var16_5 += var17_4) < var20_3) {
                                    var17_4 = var18_2.charAt(var16_5);
                                    continue;
                                }
                                break block16;
                                break;
                            }
                            v1 = ++var16_5;
                            v2 = var18_2.substring(v1, v1 + var17_4).toCharArray();
                            v0 = v2;
                            v3 = v2.length;
                            var22_6 = 0;
                            do {
                                v0 = v0;
                                v4 = var22_6;
                                v5 = v0[v4];
                                switch (var22_6 % 7) {
                                    case 0: {
                                        v6 = 65;
                                        break;
                                    }
                                    case 1: {
                                        v6 = 86;
                                        break;
                                    }
                                    case 2: {
                                        v6 = 100;
                                        break;
                                    }
                                    case 3: {
                                        v6 = 25;
                                        break;
                                    }
                                    case 4: {
                                        v6 = 4;
                                        break;
                                    }
                                    case 5: {
                                        v6 = 81;
                                        break;
                                    }
                                    default: {
                                        v6 = 30;
                                    }
                                }
                                v0[v4] = (char)(v5 ^ v6);
                                ++var22_6;
lbl48:

                                v3 = v3;
                            } while (v3 > var22_6);
                            while (true)
                        }
                        Win32Input.a = var21;
                        var9_7 = 7372094910493180204L;
                        var8_8 = new long[2];
                        var12_9 = 0;
                        var13_10 = "\u00f3~\u00e6[\u0016iA(SI&\u008c\u0016iA.";
                        var14_11 = "\u00f3~\u00e6[\u0016iA(SI&\u008c\u0016iA.".length();
                        var11_12 = 0;
                        while (true) {
                            break block17;
                            break;
                        }
lbl63:

                        while (true) {
                            var8_8[v7] = (((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L) ^ var9_7;
                            if (var11_12 < var14_11) continue;
                            break block18;
                            break;
                        }
                    }
                    var15_13 = var13_10.substring(var11_12, var11_12 += 8).getBytes("ISO-8859-1");
                    v7 = var12_9++;
                    while (true)
                }
                Win32Input.IMO_SYSTEM = (int)var8_8[0];
                Win32Input.IMO_INJECTED = (int)var8_8[1];
                var0_14 = 2415458299159958992L;
                var6_15 = new long[2];
                var3_16 = 0;
                var4_17 = "\u0098ti\u00b6\u00a8<4\n\u00ea\u0002\u001c\u00e8&\n w";
                var5_18 = "\u0098ti\u00b6\u00a8<4\n\u00ea\u0002\u001c\u00e8&\n w".length();
                var2_19 = 0;
                while (true) {
                    break block19;
                    break;
                }
lbl86:

                while (true) {
                    var6_15[v8] = (((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L) ^ var0_14;
                    if (var2_19 < var5_18) continue;
                    break block20;
                    break;
                }
            }
            var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
            v8 = var3_16++;
            while (true)
        }
        Win32Input.b = var6_15;
        Win32Input.c = new Long[2];
        Win32Input.fn = Win32Input.a(4003, 5048245803843395109L);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x1186) & Short.MAX_VALUE;
        if (c[n2] == null) {
            Win32Input.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
