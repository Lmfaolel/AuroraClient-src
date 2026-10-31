package dev.quelchi.aurora.util;

import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.util.KeyUtil;
import java.util.Locale;

public final class Gpu {
    private static Boolean intel;
    private static final String[] a;

    private Gpu() {
    }

    public static boolean intel() {
        Boolean bl;
        block23: {
            block24: {
                boolean bl2;
                int n;
                block22: {
                    block21: {
                        String string;
                        block20: {
                            GpuDevice gpuDevice;
                            block18: {
                                GpuDevice gpuDevice2;
                                block19: {
                                    block17: {
                                        Boolean bl3;
                                        block16: {
                                            n = KeyUtil.a;
                                            bl3 = intel;
                                            if (n != 0) break block16;
                                            if (bl3 == null) break block17;
                                            bl3 = intel;
                                        }
                                        return bl3;
                                    }
                                    gpuDevice2 = RenderSystem.tryGetDevice();
                                    gpuDevice = gpuDevice2;
                                    if (n != 0) break block18;
                                    if (gpuDevice != null) break block19;
                                    return false;
                                }
                                gpuDevice = gpuDevice2;
                            }
                            String string2 = gpuDevice.getVendor();
                            string = string2;
                            if (n != 0) break block20;
                            if (string == null) break block21;
                            string = string2.toLowerCase(Locale.ROOT);
                        }
                        bl2 = string.contains(a[0]);
                        if (n != 0) break block22;
                        if (!bl2) break block21;
                        bl2 = true;
                        break block22;
                    }
                    bl2 = false;
                }
                bl = intel = Boolean.valueOf(bl2);
                if (n != 0) break block23;
                if (!bl.booleanValue()) break block24;
                Aurora.LOG.info(a[1]);
            }
            bl = intel;
        }
        return bl;
    }

    
    static {
        block12: {
            var5 = new String[2];
            var3_1 = 0;
            var2_2 = "\\\u0018\u0014X::|\u0018\u0014X:B@e#L\u001d1\u000efF\u0005@^7\u0012s@\u0004\u0005\u001d7\fc\u0015\u0010\u0012\\;\u0007e@\u0010\u0006X$Be\\\u0018\u0004\u001d%\tnE\u0006\tS1BhS\u0010";
            var4_3 = "\\\u0018\u0014X::|\u0018\u0014X:B@e#L\u001d1\u000efF\u0005@^7\u0012s@\u0004\u0005\u001d7\fc\u0015\u0010\u0012\\;\u0007e@\u0010\u0006X$Be\\\u0018\u0004\u001d%\tnE\u0006\tS1BhS\u0010".length();
            var1_4 = 5;
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
                        v6 = 53;
                        break;
                    }
                    case 1: {
                        v6 = 118;
                        break;
                    }
                    case 2: {
                        v6 = 96;
                        break;
                    }
                    case 3: {
                        v6 = 61;
                        break;
                    }
                    case 4: {
                        v6 = 86;
                        break;
                    }
                    case 5: {
                        v6 = 98;
                        break;
                    }
                    default: {
                        v6 = 7;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        Gpu.a = var5;
    }
}
