package dev.quelchi.aurora.render;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;

public final class SkyGlow {
    private static final RenderPipeline PIPELINE;
    private static final RenderLayer LAYER;

    private SkyGlow() {
    }

    public static RenderLayer layer() {
        return LAYER;
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[5];
                var4_1 = 0;
                var3_2 = ")\u0013Kc\u0013\u0011&8\u000fIi\r\u0019g-IJg\u0018/n$\tN\u0019+\tKiN\u0002l&\u0002\\~\u0015\ty-9Ue\u0006\u0018}&\u000fWk\r+\tKiN\u0003b19^`\u000e\u0007";
                var5_3 = ")\u0013Kc\u0013\u0011&8\u000fIi\r\u0019g-IJg\u0018/n$\tN\u0019+\tKiN\u0002l&\u0002\\~\u0015\ty-9Ue\u0006\u0018}&\u000fWk\r+\tKiN\u0003b19^`\u000e\u0007".length();
                var2_4 = 24;
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
                    var3_2 = ")\u0013Kc\u0013\u0011V;\r@S\u0006\u001cf?\u0006)\u0013Kc\u0013\u0011";
                    var5_3 = ")\u0013Kc\u0013\u0011V;\r@S\u0006\u001cf?\u0006)\u0013Kc\u0013\u0011".length();
                    var2_4 = 15;
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
                        v10 = 72;
                        break;
                    }
                    case 1: {
                        v10 = 102;
                        break;
                    }
                    case 2: {
                        v10 = 57;
                        break;
                    }
                    case 3: {
                        v10 = 12;
                        break;
                    }
                    case 4: {
                        v10 = 97;
                        break;
                    }
                    case 5: {
                        v10 = 112;
                        break;
                    }
                    default: {
                        v10 = 9;
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
        SkyGlow.PIPELINE = RenderPipelines.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{RenderPipelines.field_60127}).withLocation(var0[0]).withVertexShader(Identifier.method_60655((String)var0[4], (String)var0[2])).withFragmentShader(var0[1]).withDepthWrite(false).withCull(false).withBlend(BlendFunction.LIGHTNING).withVertexFormat(VertexFormats.field_1576, VertexFormat.DrawMode.field_27382).build());
        SkyGlow.LAYER = RenderLayer.method_75940((String)var0[3], (RenderSetup)RenderSetup.method_75927((RenderPipeline)SkyGlow.PIPELINE).method_75938());
    }
}
