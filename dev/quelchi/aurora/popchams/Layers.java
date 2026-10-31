package dev.quelchi.aurora.popchams;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;

final class Layers {
    private static final RenderPipeline FILL_PIPELINE;
    static final RenderLayer FILL;
    private static final RenderPipeline WIREFRAME_PIPELINE;
    static final RenderLayer WIREFRAME;

    private Layers() {
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "\"G$:\u0015_\u00193]&\n\u0004V'.A\t3\u000eR*#\"G$:\u0015_i3[&0\u000bW(&\u001d&:\u0017a%+S;&8I/1W0'\u0006S#";
                var5_3 = "\"G$:\u0015_\u00193]&\n\u0004V'.A\t3\u000eR*#\"G$:\u0015_i3[&0\u000bW(&\u001d&:\u0017a%+S;&8I/1W0'\u0006S#".length();
                var2_4 = 21;
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
                    var3_2 = "\"G$:\u0015_\u00193]&\n\u0004V'.A\t\"\u000eL#%@78\u0002\u001e\"G$:\u0015_i3[&0\u000bW(&\u001d&:\u0017a%+S;&8X//^";
                    var5_3 = "\"G$:\u0015_\u00193]&\n\u0004V'.A\t\"\u000eL#%@78\u0002\u001e\"G$:\u0015_i3[&0\u000bW(&\u001d&:\u0017a%+S;&8X//^".length();
                    var2_4 = 26;
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
                        v10 = 67;
                        break;
                    }
                    case 1: {
                        v10 = 50;
                        break;
                    }
                    case 2: {
                        v10 = 86;
                        break;
                    }
                    case 3: {
                        v10 = 85;
                        break;
                    }
                    case 4: {
                        v10 = 103;
                        break;
                    }
                    case 5: {
                        v10 = 62;
                        break;
                    }
                    default: {
                        v10 = 70;
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
        Layers.FILL_PIPELINE = RenderPipelines.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{RenderPipelines.field_56860}).withCull(true).withLocation(var0[3]).build());
        Layers.FILL = RenderLayer.method_75940((String)var0[0], (RenderSetup)RenderSetup.method_75927((RenderPipeline)Layers.FILL_PIPELINE).method_75937().method_75929(256).method_75938());
        Layers.WIREFRAME_PIPELINE = RenderPipelines.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{RenderPipelines.field_56859}).withCull(false).withLocation(var0[1]).build());
        Layers.WIREFRAME = RenderLayer.method_75940((String)var0[2], (RenderSetup)RenderSetup.method_75927((RenderPipeline)Layers.WIREFRAME_PIPELINE).method_75937().method_75929(256).method_75938());
    }
}
