package dev.quelchi.aurora.module.setting;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.setting.Setting;

public class ColorSetting
extends Setting {
    private int argb;
    private final boolean hasAlpha;
    private final int defaultArgb;
    private static final long[] b;
    private static final Integer[] c;

    public ColorSetting(String string, int n) {
        this(string, n, true);
    }

    public ColorSetting(String string, int n, boolean bl) {
        super(string);
        this.hasAlpha = bl;
        this.setARGB(n);
        this.defaultArgb = n;
    }

    public boolean hasAlpha() {
        return this.hasAlpha;
    }

    public int getARGB() {
        return this.argb;
    }

    public int getRGB() {
        return this.argb & ColorSetting.a(28589, 2532491692124353567L);
    }

    public int getR() {
        return this.argb >>> 16 & 0xFF;
    }

    public int getG() {
        return this.argb >>> 8 & 0xFF;
    }

    public int getB() {
        return this.argb & 0xFF;
    }

    public int getA() {
        return this.argb >>> 24 & 0xFF;
    }

    public void setARGB(int n) {
        block2: {
            ColorSetting colorSetting;
            block3: {
                boolean bl = Setting.a;
                colorSetting = this;
                if (bl) break block2;
                if (colorSetting.hasAlpha) break block3;
                n = ColorSetting.a(27876, 2515201745997608789L) | n & ColorSetting.a(6343, 3018535975400522612L);
            }
            colorSetting = this;
        }
        colorSetting.argb = n;
    }

    public void setRGB(int n, int n2, int n3) {
        block10: {
            boolean bl;
            block12: {
                block11: {
                    int n4;
                    boolean bl2;
                    block13: {
                        block9: {
                            ColorSetting colorSetting;
                            block8: {
                                bl2 = Setting.a;
                                colorSetting = this;
                                if (bl2) break block8;
                                if (!colorSetting.hasAlpha) break block9;
                                colorSetting = this;
                            }
                            n4 = colorSetting.getA();
                            break block13;
                        }
                        n4 = 255;
                    }
                    int n5 = n4;
                    this.setARGB(n5 << 24 | (n & 0xFF) << 16 | (n2 & 0xFF) << 8 | n3 & 0xFF);
                    if (!bl2) break block10;
                    if (!Module.a) break block11;
                    bl = false;
                    break block12;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    @Override
    public void reset() {
        this.setARGB(this.defaultArgb);
    }

    
    static {
        long l = 4297457529340520893L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "\u00d8\u0004&\u007f\u00aa(\u00d56\u00c5\u00c6\u00e3z\u0018\u00e7\u00a2]]c\u0084\u008c.\n^\u00e8";
        int n2 = "\u00d8\u0004&\u007f\u00aa(\u00d56\u00c5\u00c6\u00e3z\u0018\u00e7\u00a2]]c\u0084\u008c.\n^\u00e8".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        b = lArray;
        c = new Integer[3];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5BB3;
        if (c[n2] == null) {
            ColorSetting.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
