package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.AmbienceModule;

public final class SkyModule$Style
extends Enum<SkyModule$Style> {
    public static final /* enum */ SkyModule$Style Painterly;
    public static final /* enum */ SkyModule$Style Fantasy;
    public static final /* enum */ SkyModule$Style Sketch;
    public static final /* enum */ SkyModule$Style Holographic;
    public static final /* enum */ SkyModule$Style Synthwave;
    public static final /* enum */ SkyModule$Style Paper;
    public static final /* enum */ SkyModule$Style Terminal;
    public static final /* enum */ SkyModule$Style Comic;
    public static final /* enum */ SkyModule$Style Stained_Glass;
    public static final /* enum */ SkyModule$Style Low_Poly;
    public final String id;
    private static final SkyModule$Style[] $VALUES;

    public static SkyModule$Style[] values() {
        return (SkyModule$Style[])$VALUES.clone();
    }

    public static SkyModule$Style valueOf(String string) {
        return Enum.valueOf(SkyModule$Style.class, string);
    }

    private SkyModule$Style(String string2) {
        this.id = string2;
    }

    public boolean generated() {
        boolean bl;
        block8: {
            block7: {
                SkyModule$Style skyModule$Style;
                block6: {
                    boolean bl2 = AmbienceModule.b;
                    skyModule$Style = this;
                    if (bl2) break block6;
                    if (skyModule$Style == Painterly) break block7;
                    skyModule$Style = this;
                }
                if (skyModule$Style == Fantasy) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    private static SkyModule$Style[] $values() {
        return new SkyModule$Style[]{Painterly, Fantasy, Sketch, Holographic, Synthwave, Paper, Terminal, Comic, Stained_Glass, Low_Poly};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[20];
                var4_1 = 0;
                var3_2 = "\t\u000eIm\u000e\u007fi5\u0016\u0005\u001a\u0000Mj\u0019\b\u0015\u0000W\\*uw \u0007\u001f\u000eNw\u001bib\u000b\u0011\u0000Ll\u001dhz)\u0007I`\b-\nRn\u0013tz5\u00075\u0000Ws\u0015vb\b\r\nRn\u0013tz5\u0005*\u0016Nw\u0012\u0006\n\u0004Ew\u0019r\u0007?\u000eNw\u001bib\u0005:\u0000Mj\u0019\u0006*\u0004Ew\u0019r\t)\u000eIm\u000e\u007fi5\u0016\u0005\t\u000ePf\b\u0005>\u0003Ap\t\r\n\u001bAj\u0014\u007f\u007f\u0006(Lb\ti\t\n\u0016Nw\u0012mz/\n";
                var5_3 = "\t\u000eIm\u000e\u007fi5\u0016\u0005\u001a\u0000Mj\u0019\b\u0015\u0000W\\*uw \u0007\u001f\u000eNw\u001bib\u000b\u0011\u0000Ll\u001dhz)\u0007I`\b-\nRn\u0013tz5\u00075\u0000Ws\u0015vb\b\r\nRn\u0013tz5\u0005*\u0016Nw\u0012\u0006\n\u0004Ew\u0019r\u0007?\u000eNw\u001bib\u0005:\u0000Mj\u0019\u0006*\u0004Ew\u0019r\t)\u000eIm\u000e\u007fi5\u0016\u0005\t\u000ePf\b\u0005>\u0003Ap\t\r\n\u001bAj\u0014\u007f\u007f\u0006(Lb\ti\t\n\u0016Nw\u0012mz/\n".length();
                var2_4 = 9;
                var1_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var1_5;
                    v1 = var3_2.substring(v0, v0 + var2_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        continue;
                    }
                    var3_2 = "1\u0000Ll\u0005)\u000ePf\b";
                    var5_3 = "1\u0000Ll\u0005)\u000ePf\b".length();
                    var2_4 = 4;
                    var1_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var1_5;
                        v1 = var3_2.substring(v4, v4 + var2_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
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
                        v10 = 89;
                        break;
                    }
                    case 1: {
                        v10 = 111;
                        break;
                    }
                    case 2: {
                        v10 = 32;
                        break;
                    }
                    case 3: {
                        v10 = 3;
                        break;
                    }
                    case 4: {
                        v10 = 122;
                        break;
                    }
                    case 5: {
                        v10 = 26;
                        break;
                    }
                    default: {
                        v10 = 27;
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
        SkyModule$Style.Painterly = new SkyModule$Style(var0[13]);
        SkyModule$Style.Fantasy = new SkyModule$Style(var0[10]);
        SkyModule$Style.Sketch = new SkyModule$Style(var0[12]);
        SkyModule$Style.Holographic = new SkyModule$Style(var0[18]);
        SkyModule$Style.Synthwave = new SkyModule$Style(var0[8]);
        SkyModule$Style.Paper = new SkyModule$Style(var0[19]);
        SkyModule$Style.Terminal = new SkyModule$Style(var0[5]);
        SkyModule$Style.Comic = new SkyModule$Style(var0[11]);
        SkyModule$Style.Stained_Glass = new SkyModule$Style(var0[15]);
        SkyModule$Style.Low_Poly = new SkyModule$Style(var0[6]);
        SkyModule$Style.$VALUES = SkyModule$Style.$values();
    }
}
