package dev.quelchi.aurora.glass.style;

import dev.quelchi.aurora.glass.style.ComponentStyle;
import dev.quelchi.aurora.glass.style.GlassPresets;
import dev.quelchi.aurora.glass.ui.Insets;

public record LiquidGlassTheme(ComponentStyle panel, ComponentStyle control, ComponentStyle tooltip, int backgroundDim) {
    private static final long[] a;
    private static final Integer[] b;

    public static LiquidGlassTheme dark() {
        ComponentStyle componentStyle = new ComponentStyle(GlassPresets.DARK, LiquidGlassTheme.a(18297, 6479825348893946383L), LiquidGlassTheme.a(12439, 3620751353975326180L), LiquidGlassTheme.a(5087, 6525099372051031716L), LiquidGlassTheme.a(25676, 5503353794549518648L), LiquidGlassTheme.a(8436, 6843270729613694347L), LiquidGlassTheme.a(11087, 341373561264234045L), Insets.all(12), 8, 140);
        ComponentStyle componentStyle2 = new ComponentStyle(GlassPresets.SUBTLE, LiquidGlassTheme.a(18406, 5499149046970036887L), LiquidGlassTheme.a(14012, 6611980785739252673L), LiquidGlassTheme.a(11224, 4493909335384546991L), LiquidGlassTheme.a(32191, 8771837826662828229L), LiquidGlassTheme.a(13592, 665710272316555364L), LiquidGlassTheme.a(24545, 2878941810619198097L), Insets.xy(9, 6), 6, 120);
        ComponentStyle componentStyle3 = new ComponentStyle(GlassPresets.STRONG.radius(7), -1, LiquidGlassTheme.a(16484, 767145039192175889L), LiquidGlassTheme.a(11224, 4493909335384546991L), 0, 0, LiquidGlassTheme.a(24545, 2878941810619198097L), Insets.xy(8, 6), 4, 80);
        return new LiquidGlassTheme(componentStyle, componentStyle2, componentStyle3, LiquidGlassTheme.a(5325, 4203076485333311923L));
    }

    
    static {
        block8: {
            block7: {
                var0 = 5356594279725400139L;
                var6_1 = new long[14];
                var3_2 = 0;
                var4_3 = "\u0004@M\u0003\u001b\u00f8\u00f6\u001b\u0084|\u008b\u008e\u00b0|\u00c5\u00be\u008a\u009e\u0007)O\u0017w\u0094=8\u00d9\u00e3\u00ed\u007f\u00ee\u008c\u00d3\u00b3n?N\u00f4sk\u00aa\u00b7\u0089\fhH\u00df\u00f9\u0089\u00f9\u00da\u00bd(\u008aE&\u00f1\u0017q\u0097Xo\u00abU^|`\u0004n`*?\u00ee\u00b7\u0085\u0092\u00ca\u00fa\u00e1\u00f8\u0015*\u00bc\u00b3$\u00f0\u00f5NC\u001e\u00e0F\u00fa\u00d2?\u00d0";
                var5_4 = "\u0004@M\u0003\u001b\u00f8\u00f6\u001b\u0084|\u008b\u008e\u00b0|\u00c5\u00be\u008a\u009e\u0007)O\u0017w\u0094=8\u00d9\u00e3\u00ed\u007f\u00ee\u008c\u00d3\u00b3n?N\u00f4sk\u00aa\u00b7\u0089\fhH\u00df\u00f9\u0089\u00f9\u00da\u00bd(\u008aE&\u00f1\u0017q\u0097Xo\u00abU^|`\u0004n`*?\u00ee\u00b7\u0085\u0092\u00ca\u00fa\u00e1\u00f8\u0015*\u00bc\u00b3$\u00f0\u00f5NC\u001e\u00e0F\u00fa\u00d2?\u00d0".length();
                var2_5 = 0;
                while (true) {
                    var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                    v0 = var6_1;
                    v1 = var3_2++;
                    v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                    v3 = -1;
                    break block7;
                    break;
                }
lbl14:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    var4_3 = "\u0003\u009aE]\u00bc\u00a9N\u0010^\u00b7I\u0083\u00ed\u0081wq";
                    var5_4 = "\u0003\u009aE]\u00bc\u00a9N\u0010^\u00b7I\u0083\u00ed\u0081wq".length();
                    var2_5 = 0;
                    while (true) {
                        var7_6 = var4_3.substring(var2_5, var2_5 += 8).getBytes("ISO-8859-1");
                        v0 = var6_1;
                        v1 = var3_2++;
                        v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                        v3 = 0;
                        break block7;
                        break;
                    }
                    break;
                }
lbl27:

                while (true) {
                    v0[v1] = v4;
                    if (var2_5 < var5_4) continue;
                    break block8;
                    break;
                }
            }
            v4 = v2 ^ var0;
            switch (v3) {
                default: {
                    continue;
                }
                case 0:
lbl38:

                continue;
            }
        }
        LiquidGlassTheme.a = var6_1;
        LiquidGlassTheme.b = new Integer[14];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7577;
        if (b[n2] == null) {
            LiquidGlassTheme.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
