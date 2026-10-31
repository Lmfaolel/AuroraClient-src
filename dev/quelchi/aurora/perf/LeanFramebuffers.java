package dev.quelchi.aurora.perf;

import dev.quelchi.aurora.mixins.GlStateManagerAccessor;
import dev.quelchi.aurora.perf.Tweaks;
import dev.quelchi.aurora.util.Gpu;
import net.fabricmc.loader.api.FabricLoader;
import org.lwjgl.opengl.GL30;

public final class LeanFramebuffers {
    private static final int FRAMEBUFFER;
    public static boolean on;
    public static volatile boolean BYPASS;
    private static final boolean VULKAN;
    private static boolean pending;
    private static final long[] a;
    private static final Integer[] b;

    private LeanFramebuffers() {
    }

    public static boolean active() {
        boolean bl;
        block12: {
            block9: {
                int n;
                block11: {
                    block10: {
                        block8: {
                            n = Tweaks.a;
                            bl = on;
                            if (n != 0) break block8;
                            if (!bl) break block9;
                            bl = BYPASS;
                        }
                        if (n != 0) break block10;
                        if (bl) break block9;
                        bl = VULKAN;
                    }
                    if (n != 0) break block11;
                    if (bl) break block9;
                    bl = Gpu.intel();
                }
                if (n != 0) break block12;
                if (bl) break block9;
                bl = true;
                break block12;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean pending() {
        return pending;
    }

    public static boolean bind(int n, int n2) {
        int n3;
        block35: {
            block36: {
                int n4;
                int n5;
                int n6;
                block30: {
                    block31: {
                        int n7;
                        block32: {
                            block34: {
                                block33: {
                                    int n8;
                                    block28: {
                                        block29: {
                                            boolean bl;
                                            block26: {
                                                block27: {
                                                    n6 = Tweaks.a;
                                                    bl = LeanFramebuffers.active();
                                                    if (n6 != 0) break block26;
                                                    if (!bl) break block27;
                                                    n8 = n;
                                                    if (n6 != 0) break block28;
                                                    if (n8 == LeanFramebuffers.a(11183, 8499707881801052283L)) break block29;
                                                }
                                                LeanFramebuffers.flush();
                                                bl = false;
                                            }
                                            return bl;
                                        }
                                        n8 = GlStateManagerAccessor.getReadFbo();
                                    }
                                    n5 = n8;
                                    n4 = GlStateManagerAccessor.getWriteFbo();
                                    if (n6 != 0) break block30;
                                    if (n2 != 0) break block31;
                                    n7 = n5;
                                    if (n6 != 0) break block32;
                                    if (n7 != 0) break block33;
                                    n7 = n4;
                                    if (n6 != 0) break block32;
                                    if (n7 == 0) break block34;
                                }
                                n7 = 1;
                                break block32;
                            }
                            n7 = 0;
                        }
                        pending = n7;
                        return true;
                    }
                    n3 = 0;
                    if (n6 != 0) break block35;
                    pending = n3;
                }
                if (n5 == n2) break block36;
                n3 = n4;
                if (n6 != 0) break block35;
                if (n3 == n2) break block36;
                GL30.glBindFramebuffer((int)LeanFramebuffers.a(11183, 8499707881801052283L), (int)n2);
                GlStateManagerAccessor.setReadFbo(n2);
                GlStateManagerAccessor.setWriteFbo(n2);
                return true;
            }
            n3 = 0;
        }
        return n3 != 0;
    }

    public static void flush() {
        block14: {
            int n;
            block12: {
                block13: {
                    int n2;
                    int n3;
                    block10: {
                        block11: {
                            n3 = Tweaks.a;
                            n2 = pending;
                            if (n3 != 0) break block10;
                            if (n2 != 0) break block11;
                            return;
                        }
                        n = 0;
                        if (n3 != 0) break block12;
                        pending = n;
                        n2 = GlStateManagerAccessor.getReadFbo();
                    }
                    if (n2 != 0) break block13;
                    n = GlStateManagerAccessor.getWriteFbo();
                    if (n3 != 0) break block12;
                    if (n == 0) break block14;
                }
                GL30.glBindFramebuffer((int)LeanFramebuffers.a(26231, 8948072211839597984L), (int)0);
                GlStateManagerAccessor.setReadFbo(0);
                n = 0;
            }
            GlStateManagerAccessor.setWriteFbo(n);
        }
    }

    
    static {
        char[] cArray;
        block12: {
            int n;
            int n2;
            block11: {
                char[] cArray2 = "\u0010\u0012z)/?g\t\u0003".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block11;
                n2 = n2;
                if (n2 <= n) break block12;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 102;
                    case 1 -> 103;
                    case 2 -> 22;
                    case 3 -> 66;
                    case 4 -> 78;
                    case 5 -> 81;
                    default -> 10;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        String string = new String(cArray).intern();
        long l = 834151913988436866L;
        long[] lArray = new long[3];
        int n = 0;
        String string2 = "\u00bb\u0004\u00e2\u00e3^\u00f7\u00cd\u008b\u00ed\u00ae\u00f0l\u00f4\u00a8N\u00b9\u00ff8R\u00b5\u0082\u00c0\u0083b";
        int n4 = "\u00bb\u0004\u00e2\u00e3^\u00f7\u00cd\u008b\u00ed\u00ae\u00f0l\u00f4\u00a8N\u00b9\u00ff8R\u00b5\u0082\u00c0\u0083b".length();
        int n5 = 0;
        do {
            byte[] byArray = string2.substring(n5, n5 += 8).getBytes("ISO-8859-1");
            int n6 = n++;
            lArray[n6] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n5 < n4);
        a = lArray;
        b = new Integer[3];
        FRAMEBUFFER = LeanFramebuffers.a(10396, 4747697540978799433L);
        on = true;
        VULKAN = FabricLoader.getInstance().isModLoaded(string);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5BD5;
        if (b[n2] == null) {
            LeanFramebuffers.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
