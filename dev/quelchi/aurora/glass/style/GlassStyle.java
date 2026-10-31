package dev.quelchi.aurora.glass.style;

import dev.quelchi.aurora.glass.render.GlassMath;

public record GlassStyle(int tint, float opacity, float blur, float refraction, float saturation, float contrast, float noise, int borderColor, int radius, int shadowColor, int shadowSize, int innerGlow, int outerGlow, float highlight, boolean mouseReactive, int topRightRadius, int bottomRightRadius, int bottomLeftRadius, int tintBottom, float chromaticAberration, float iridescence, float roughness, float lightAngle, float blurSpread, float edgeWidth, float tintAngle, int edgeLight) {
    public static int a;
    private static final String[] b;

    public GlassStyle(int n, float f, float f2, float f3, float f4, float f5, float f6, int n2, int n3, int n4, int n5, int n6, int n7, float f7, boolean bl) {
        this(n, f, f2, f3, f4, f5, f6, n2, n3, n4, n5, n6, n7, f7, bl, n3, n3, n3, n, 0.0f, 0.0f, 0.22f, 240.0f, 0.0f, 0.0f, 90.0f, -1);
    }

    public GlassStyle {
        String[] stringArray = b;
        f = GlassStyle.unit(f, stringArray[6]);
        f2 = GlassStyle.unit(f2, stringArray[5]);
        f3 = GlassStyle.unit(f3, stringArray[4]);
        f4 = GlassMath.clamp(GlassMath.finite(f4, stringArray[11]), 0.0f, 4.0f);
        f5 = GlassMath.clamp(GlassMath.finite(f5, stringArray[0]), 0.0f, 4.0f);
        f6 = GlassStyle.unit(f6, stringArray[7]);
        n3 = GlassStyle.clampRadius(n3);
        n8 = GlassStyle.clampRadius(n8);
        n9 = GlassStyle.clampRadius(n9);
        n10 = GlassStyle.clampRadius(n10);
        n5 = Math.max(0, Math.min(48, n5));
        f7 = GlassStyle.unit(f7, stringArray[8]);
        f8 = GlassStyle.unit(f8, stringArray[10]);
        f9 = GlassStyle.unit(f9, stringArray[9]);
        f10 = GlassStyle.unit(f10, stringArray[3]);
        f11 = GlassMath.finite(f11, stringArray[13]) % 360.0f;
        if (f11 < 0.0f) {
            f11 += 360.0f;
        }
        stringArray = b;
        f12 = GlassStyle.unit(f12, stringArray[12]);
        f13 = GlassMath.clamp(GlassMath.finite(f13, stringArray[2]), 0.0f, 24.0f);
        if ((f14 = GlassMath.finite(f14, stringArray[1]) % 360.0f) < 0.0f) {
            f14 += 360.0f;
        }
    }

    public GlassStyle opacity(float f) {
        return this.copy(this.tint, this.tintBottom, f, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, this.iridescence, this.roughness, this.lightAngle, this.blurSpread, this.edgeWidth, this.tintAngle, this.edgeLight);
    }

    public GlassStyle radius(int n) {
        int n2 = GlassStyle.clampRadius(n);
        return this.cornerRadii(n2, n2, n2, n2);
    }

    public GlassStyle cornerRadii(int n, int n2, int n3, int n4) {
        return this.copy(this.tint, this.tintBottom, this.opacity, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, n, n2, n3, n4, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, this.iridescence, this.roughness, this.lightAngle, this.blurSpread, this.edgeWidth, this.tintAngle, this.edgeLight);
    }

    public int topLeftRadius() {
        return this.radius;
    }

    public GlassStyle blur(float f) {
        return this.copy(this.tint, this.tintBottom, this.opacity, f, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, this.iridescence, this.roughness, this.lightAngle, this.blurSpread, this.edgeWidth, this.tintAngle, this.edgeLight);
    }

    public GlassStyle blurBlend(float f) {
        return this.blur(f);
    }

    public GlassStyle refraction(float f) {
        return this.copy(this.tint, this.tintBottom, this.opacity, this.blur, f, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, this.iridescence, this.roughness, this.lightAngle, this.blurSpread, this.edgeWidth, this.tintAngle, this.edgeLight);
    }

    public GlassStyle tint(int n) {
        return this.tintGradient(n, n);
    }

    public GlassStyle tintGradient(int n, int n2) {
        return this.copy(n, n2, this.opacity, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, this.iridescence, this.roughness, this.lightAngle, this.blurSpread, this.edgeWidth, this.tintAngle, this.edgeLight);
    }

    public GlassStyle highlight(float f) {
        return this.copy(this.tint, this.tintBottom, this.opacity, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, f, this.mouseReactive, this.chromaticAberration, this.iridescence, this.roughness, this.lightAngle, this.blurSpread, this.edgeWidth, this.tintAngle, this.edgeLight);
    }

    public GlassStyle chromaticAberration(float f) {
        return this.copy(this.tint, this.tintBottom, this.opacity, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, f, this.iridescence, this.roughness, this.lightAngle, this.blurSpread, this.edgeWidth, this.tintAngle, this.edgeLight);
    }

    public GlassStyle iridescence(float f) {
        return this.copy(this.tint, this.tintBottom, this.opacity, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, f, this.roughness, this.lightAngle, this.blurSpread, this.edgeWidth, this.tintAngle, this.edgeLight);
    }

    public GlassStyle roughness(float f) {
        return this.copy(this.tint, this.tintBottom, this.opacity, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, this.iridescence, f, this.lightAngle, this.blurSpread, this.edgeWidth, this.tintAngle, this.edgeLight);
    }

    public GlassStyle lightAngle(float f) {
        return this.copy(this.tint, this.tintBottom, this.opacity, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, this.iridescence, this.roughness, f, this.blurSpread, this.edgeWidth, this.tintAngle, this.edgeLight);
    }

    public GlassStyle blurSpread(float f) {
        return this.copy(this.tint, this.tintBottom, this.opacity, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, this.iridescence, this.roughness, this.lightAngle, f, this.edgeWidth, this.tintAngle, this.edgeLight);
    }

    public GlassStyle edgeWidth(float f) {
        return this.copy(this.tint, this.tintBottom, this.opacity, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, this.iridescence, this.roughness, this.lightAngle, this.blurSpread, f, this.tintAngle, this.edgeLight);
    }

    public GlassStyle tintAngle(float f) {
        return this.copy(this.tint, this.tintBottom, this.opacity, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, this.iridescence, this.roughness, this.lightAngle, this.blurSpread, this.edgeWidth, f, this.edgeLight);
    }

    public GlassStyle edgeLight(int n) {
        return this.copy(this.tint, this.tintBottom, this.opacity, this.blur, this.refraction, this.saturation, this.contrast, this.noise, this.borderColor, this.radius, this.topRightRadius, this.bottomRightRadius, this.bottomLeftRadius, this.shadowColor, this.shadowSize, this.innerGlow, this.outerGlow, this.highlight, this.mouseReactive, this.chromaticAberration, this.iridescence, this.roughness, this.lightAngle, this.blurSpread, this.edgeWidth, this.tintAngle, n);
    }

    private GlassStyle copy(int n, int n2, float f, float f2, float f3, float f4, float f5, float f6, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11, float f7, boolean bl, float f8, float f9, float f10, float f11, float f12, float f13, float f14, int n12) {
        return new GlassStyle(n, f, f2, f3, f4, f5, f6, n3, n4, n8, n9, n10, n11, f7, bl, n5, n6, n7, n2, f8, f9, f10, f11, f12, f13, f14, n12);
    }

    private static int clampRadius(int n) {
        return Math.max(0, Math.min(128, n));
    }

    private static float unit(float f, String string) {
        return GlassMath.clamp(GlassMath.finite(f, string), 0.0f, 1.0f);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[14];
                var3_1 = 0;
                var2_2 = "r`\bd#\u0001He\tef\bd\u0010\u000e\\}j\ttk\u0001u\u0006\t_eg\tc`\u0013w9\u000e^b|\ncj\u0000b0\u0003Ox`\b\u0004sc\u0013b\u0007~\u007f\u0007s8\u0014B\u0005\u007f`\u000fc4\tyf\u0001x=\t\\y{\u000bx}\u000ft4\u0013Xta\u0005u\u0013rg\u0014\u007f<\u0001Oxl'r4\u0012Ip{\u000f\u007f?\nbn\u0012e#\u0001Ox`\b";
                var4_3 = "r`\bd#\u0001He\tef\bd\u0010\u000e\\}j\ttk\u0001u\u0006\t_eg\tc`\u0013w9\u000e^b|\ncj\u0000b0\u0003Ox`\b\u0004sc\u0013b\u0007~\u007f\u0007s8\u0014B\u0005\u007f`\u000fc4\tyf\u0001x=\t\\y{\u000bx}\u000ft4\u0013Xta\u0005u\u0013rg\u0014\u007f<\u0001Oxl'r4\u0012Ip{\u000f\u007f?\nbn\u0012e#\u0001Ox`\b".length();
                var1_4 = 8;
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
                    var2_2 = "sc\u0013b\u0002\u0010Itn\u0002\n}f\u0001x%!Uvc\u0003";
                    var4_3 = "sc\u0013b\u0002\u0010Itn\u0002\n}f\u0001x%!Uvc\u0003".length();
                    var1_4 = 10;
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
                        v10 = 17;
                        break;
                    }
                    case 1: {
                        v10 = 15;
                        break;
                    }
                    case 2: {
                        v10 = 102;
                        break;
                    }
                    case 3: {
                        v10 = 16;
                        break;
                    }
                    case 4: {
                        v10 = 81;
                        break;
                    }
                    case 5: {
                        v10 = 96;
                        break;
                    }
                    default: {
                        v10 = 59;
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
        GlassStyle.b = var5;
    }
}
