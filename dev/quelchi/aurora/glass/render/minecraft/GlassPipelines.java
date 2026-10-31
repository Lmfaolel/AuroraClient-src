package dev.quelchi.aurora.glass.render.minecraft;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;

final class GlassPipelines {
    static final RenderPipeline SURFACE;
    static final RenderPipeline BLUR;
    private static final String[] a;

    private GlassPipelines() {
    }

    private static Identifier id(String string) {
        return Identifier.method_60655((String)a[12], (String)string);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[14];
                var3_1 = 0;
                var2_2 = "\u0013\b(\"bXVr\n\u0010\u001b*8k^P)\u0006+\n#\u000677!ZH!\u001a6\b\u0013\b(\"bXVq\u00130\u000057bTJ%F\">oNW\u001f\u000b)'|\b\u0013\b(\"bXVp\u00160\u000057bTJ%F\">oNW\u001f\u001a0 h\\G%\u0011\u0004\u0010+3cTG\u0014\u001b$<}[K2\u00046\t#\u000677!_H5\u001b\t#\u000677!_H5\u001b\b\u0013\b(\"bXVp\n\u0002\u00050 MRJ&\u0000\"";
                var4_3 = "\u0013\b(\"bXVr\n\u0010\u001b*8k^P)\u0006+\n#\u000677!ZH!\u001a6\b\u0013\b(\"bXVq\u00130\u000057bTJ%F\">oNW\u001f\u000b)'|\b\u0013\b(\"bXVp\u00160\u000057bTJ%F\">oNW\u001f\u001a0 h\\G%\u0011\u0004\u0010+3cTG\u0014\u001b$<}[K2\u00046\t#\u000677!_H5\u001b\t#\u000677!_H5\u001b\b\u0013\b(\"bXVp\n\u0002\u00050 MRJ&\u0000\"".length();
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
                    var2_2 = "!\u001c7=|\\\n#\u000677!ZH!\u001a6";
                    var4_3 = "!\u001c7=|\\\n#\u000677!ZH!\u001a6".length();
                    var1_4 = 6;
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
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 64;
                        break;
                    }
                    case 1: {
                        v10 = 105;
                        break;
                    }
                    case 2: {
                        v10 = 69;
                        break;
                    }
                    case 3: {
                        v10 = 82;
                        break;
                    }
                    case 4: {
                        v10 = 14;
                        break;
                    }
                    case 5: {
                        v10 = 61;
                        break;
                    }
                    default: {
                        v10 = 36;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_7;
lbl65:

                v7 = v7;
            } while (v7 > var6_7);
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
        GlassPipelines.a = var5;
        var0_6 = GlassPipelines.a;
        GlassPipelines.SURFACE = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GlassPipelines.id(var0_6[6])).withVertexShader(GlassPipelines.id(var0_6[13])).withFragmentShader(GlassPipelines.id(var0_6[2])).withVertexFormat(VertexFormats.field_20888, VertexFormat.DrawMode.field_27382).withUniform(var0_6[7], UniformType.field_60031).withUniform(var0_6[1], UniformType.field_60031).withSampler(var0_6[10]).withSampler(var0_6[3]).withSampler(var0_6[0]).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build();
        GlassPipelines.BLUR = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GlassPipelines.id(var0_6[4])).withVertexShader(GlassPipelines.id(var0_6[8])).withFragmentShader(GlassPipelines.id(var0_6[9])).withVertexFormat(VertexFormats.field_60033, VertexFormat.DrawMode.field_27379).withUniform(var0_6[11], UniformType.field_60031).withSampler(var0_6[5]).withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build();
    }
}
