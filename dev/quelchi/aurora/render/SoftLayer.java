package dev.quelchi.aurora.render;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.VertexFormats;

public final class SoftLayer {
    private static final RenderPipeline PIPELINE;
    private static final RenderLayer LAYER;

    private SoftLayer() {
    }

    public static RenderLayer layer() {
        return LAYER;
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "{a2K\f\u000f/i{&P!\u001f\u0005{p3\u001a{a2K\f\u000f_j}0A\u0012\u0007\u001e\u007f;3K\u0018\u001a/ka!@\r";
            var5_3 = "{a2K\f\u000f/i{&P!\u001f\u0005{p3\u001a{a2K\f\u000f_j}0A\u0012\u0007\u001e\u007f;3K\u0018\u001a/ka!@\r".length();
            var2_4 = 17;
            var1_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var0[var4_1++] = new String(v0).intern();
                if ((var1_5 += var2_4) < var5_3) {
                    var2_4 = var3_2.charAt(var1_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var1_5;
            v2 = var3_2.substring(v1, v1 + var2_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 26;
                        break;
                    }
                    case 1: {
                        v6 = 20;
                        break;
                    }
                    case 2: {
                        v6 = 64;
                        break;
                    }
                    case 3: {
                        v6 = 36;
                        break;
                    }
                    case 4: {
                        v6 = 126;
                        break;
                    }
                    case 5: {
                        v6 = 110;
                        break;
                    }
                    default: {
                        v6 = 112;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        SoftLayer.PIPELINE = RenderPipelines.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{RenderPipelines.field_56860}).withLocation(var0[1]).withCull(false).withDepthWrite(false).withBlend(BlendFunction.TRANSLUCENT).withVertexFormat(VertexFormats.field_1576, VertexFormat.DrawMode.field_27382).build());
        SoftLayer.LAYER = RenderLayer.method_75940((String)var0[0], (RenderSetup)RenderSetup.method_75927((RenderPipeline)SoftLayer.PIPELINE).method_75938());
    }
}
