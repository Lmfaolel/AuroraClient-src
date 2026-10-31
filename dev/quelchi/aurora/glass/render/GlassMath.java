package dev.quelchi.aurora.glass.render;

import dev.quelchi.aurora.glass.render.GlassRenderer;

public final class GlassMath {
    private static final String a;
    private static final long b;

    private GlassMath() {
    }

    public static float finite(float f, String string) {
        if (!Float.isFinite(f)) {
            throw new IllegalArgumentException(string + a);
        }
        return f;
    }

    public static float clamp(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f3, f));
    }

    public static float radius(float f, float f2, float f3) {
        return Math.max(0.0f, Math.min(f, Math.min(f2, f3) * 0.5f));
    }

    public static int leftInset(int n, int n2, int n3, int n4) {
        int n5;
        int n6;
        block17: {
            block18: {
                int n7;
                block15: {
                    block16: {
                        int n8;
                        block14: {
                            int n9;
                            block12: {
                                block13: {
                                    n7 = GlassRenderer.a;
                                    n9 = n;
                                    if (n7 != 0) break block12;
                                    if (n9 >= n3) break block13;
                                    n8 = n;
                                    break block14;
                                }
                                n9 = n2 - 1;
                            }
                            n8 = n9 - n;
                        }
                        n6 = n8;
                        n5 = n;
                        if (n7 != 0) break block15;
                        if (n5 >= n3) break block16;
                        n5 = n3;
                        break block17;
                    }
                    n5 = n;
                }
                if (n7 != 0) break block17;
                if (n5 < n2 - n4) break block18;
                n5 = n4;
                break block17;
            }
            n5 = 0;
        }
        int n10 = n5;
        return GlassMath.cornerInset(n6, n10);
    }

    public static int rightInset(int n, int n2, int n3, int n4) {
        int n5;
        int n6;
        block17: {
            block18: {
                int n7;
                block15: {
                    block16: {
                        int n8;
                        block14: {
                            int n9;
                            block12: {
                                block13: {
                                    n7 = GlassRenderer.a;
                                    n9 = n;
                                    if (n7 != 0) break block12;
                                    if (n9 >= n3) break block13;
                                    n8 = n;
                                    break block14;
                                }
                                n9 = n2 - 1;
                            }
                            n8 = n9 - n;
                        }
                        n6 = n8;
                        n5 = n;
                        if (n7 != 0) break block15;
                        if (n5 >= n3) break block16;
                        n5 = n3;
                        break block17;
                    }
                    n5 = n;
                }
                if (n7 != 0) break block17;
                if (n5 < n2 - n4) break block18;
                n5 = n4;
                break block17;
            }
            n5 = 0;
        }
        int n10 = n5;
        return GlassMath.cornerInset(n6, n10);
    }

    private static int cornerInset(int n, int n2) {
        float f = Math.max(0, n2);
        float f2 = Math.max(0.0f, f - ((float)n + 0.5f));
        return Math.max(0, (int)Math.ceil((double)f - Math.sqrt(Math.max(0.0f, f * f - f2 * f2)) - 0.5));
    }

    public static int alpha(int n, float f) {
        return Math.round((float)(n >>> 24) * GlassMath.clamp(f, 0.0f, 1.0f)) << 24 | n & (int)b;
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "lo?Vb\u001f])\",LxVK)".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 76;
                    case 1 -> 2;
                    case 2 -> 74;
                    case 3 -> 37;
                    case 4 -> 22;
                    case 5 -> 63;
                    default -> 63;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
        long l = 3580189157084387567L;
        b = 0xD3041A43D847A710L ^ l;
    }
}
