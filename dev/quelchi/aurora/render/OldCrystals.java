package dev.quelchi.aurora.render;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.mixins.RenderLayerInvoker;
import dev.quelchi.aurora.render.EffectEngine;
import java.util.LinkedHashSet;
import net.minecraft.client.gl.Defines;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.util.Identifier;

public final class OldCrystals {
    private static final String PER_FACE_LIGHTING;
    private static final Identifier CRYSTAL_TEXTURE;
    private static RenderLayer layer;
    private static boolean failed;
    private static final String[] a;

    private OldCrystals() {
    }

    
    public static RenderLayer layer() {
        int n = EffectEngine.a;
        RenderLayer renderLayer = layer;
        if (n != 0) return renderLayer;
        if (renderLayer != null || failed) {
            renderLayer = layer;
            return renderLayer;
        }
        try {
            layer = OldCrystals.build();
            return layer;
        }
        catch (Throwable throwable) {
            failed = true;
            Aurora.LOG.warn(a[1], throwable);
        }
        return layer;
    }

    private static RenderLayer build() {
        RenderPipeline.Builder builder;
        RenderSetup renderSetup;
        String[] stringArray;
        block8: {
            Object object;
            Defines defines;
            int n;
            RenderPipeline.Builder builder2;
            RenderPipeline renderPipeline;
            block7: {
                Defines defines2;
                renderPipeline = RenderPipelines.field_56904;
                stringArray = a;
                builder2 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(Identifier.method_60655((String)stringArray[4], (String)stringArray[0])).withVertexShader(renderPipeline.getVertexShader()).withFragmentShader(renderPipeline.getFragmentShader()).withVertexFormat(renderPipeline.getVertexFormat(), renderPipeline.getVertexFormatMode()).withDepthTestFunction(renderPipeline.getDepthTestFunction()).withPolygonMode(renderPipeline.getPolygonMode()).withCull(renderPipeline.isCull()).withColorWrite(renderPipeline.isWriteColor(), renderPipeline.isWriteAlpha()).withDepthWrite(renderPipeline.isWriteDepth()).withColorLogic(renderPipeline.getColorLogic()).withDepthBias(renderPipeline.getDepthBiasScaleFactor(), renderPipeline.getDepthBiasConstant());
                renderPipeline.getBlendFunction().ifPresent(arg_0 -> ((RenderPipeline.Builder)builder2).withBlend(arg_0));
                n = EffectEngine.a;
                defines = renderPipeline.getSamplers().iterator();
                while (defines.hasNext()) {
                    defines2 = defines;
                    if (n == 0) {
                        object = (String)defines2.next();
                        builder2.withSampler((String)object);
                        if (n == 0) continue;
                    }
                    break block7;
                }
                defines2 = defines = renderPipeline.getUniforms().iterator();
            }
            while (defines.hasNext()) {
                object = (RenderPipeline.UniformDescription)defines.next();
                builder2.withUniform(object.name(), object.type());
                if (n == 0) continue;
            }
            defines = renderPipeline.getShaderDefines();
            defines.comp_3103().forEach((arg_0, arg_1) -> OldCrystals.lambda$build$0(builder2, arg_0, arg_1));
            object = new LinkedHashSet(defines.comp_3104());
            stringArray = a;
            object.remove(stringArray[6]);
            renderSetup = object.iterator();
            while (renderSetup.hasNext()) {
                String string = (String)renderSetup.next();
                builder = builder2;
                if (n == 0) {
                    builder.withShaderDefine(string);
                    if (n == 0) continue;
                    break;
                }
                break block8;
            }
            builder = builder2;
        }
        stringArray = a;
        renderSetup = RenderSetup.method_75927((RenderPipeline)builder.build()).method_75934(stringArray[3], CRYSTAL_TEXTURE).method_75928().method_75935().method_75936().method_75932(RenderSetup.OutlineMode.field_21855).method_75938();
        return RenderLayerInvoker.aurora$of(stringArray[5], renderSetup);
    }

    private static float parse(String string) {
        try {
            return Float.parseFloat(string);
        }
        catch (NumberFormatException numberFormatException) {
            return 0.0f;
        }
    }

    private static void lambda$build$0(RenderPipeline.Builder builder, String string, String string2) {
        if (!a[7].equals(string)) {
            builder.withShaderDefine(string, OldCrystals.parse(string2));
        }
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[8];
                var3_1 = 0;
                var2_2 = "\u001de\u0019b\u0001^_\b#\u0006k\thR\u001fu\u001as\f[\u001f\"`\r'\u000eEH\u001ex\bkMDY\fh\u0000i\n\u0017D\u0003m\u001ff\u0004[P\u000f`\f+\u0019i\u0011s\u0018ET\u001e#\fi\u0019^E\u0014#\fi\thR\u001fu\u001as\f[\u001e\bb\rX\u000eEH\u001ex\bkCG_\n\b>m\u0004w\u0001RC]\u0006\fy\u001bh\u001fV\u0012\fy\u001bh\u001fVn\u0002`\rX\u000eEH\u001ex\bk";
                var4_3 = "\u001de\u0019b\u0001^_\b#\u0006k\thR\u001fu\u001as\f[\u001f\"`\r'\u000eEH\u001ex\bkMDY\fh\u0000i\n\u0017D\u0003m\u001ff\u0004[P\u000f`\f+\u0019i\u0011s\u0018ET\u001e#\fi\u0019^E\u0014#\fi\thR\u001fu\u001as\f[\u001e\bb\rX\u000eEH\u001ex\bkCG_\n\b>m\u0004w\u0001RC]\u0006\fy\u001bh\u001fV\u0012\fy\u001bh\u001fVn\u0002`\rX\u000eEH\u001ex\bk".length();
                var1_4 = 20;
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
                    var2_2 = "=I;X+vr(S%N*\u007fe$B.\u0011=I;X+vr(S%N*\u007fe$B.";
                    var4_3 = "=I;X+vr(S%N*\u007fe$B.\u0011=I;X+vr(S%N*\u007fe$B.".length();
                    var1_4 = 17;
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
                        v10 = 109;
                        break;
                    }
                    case 1: {
                        v10 = 12;
                        break;
                    }
                    case 2: {
                        v10 = 105;
                        break;
                    }
                    case 3: {
                        v10 = 7;
                        break;
                    }
                    case 4: {
                        v10 = 109;
                        break;
                    }
                    case 5: {
                        v10 = 55;
                        break;
                    }
                    default: {
                        v10 = 49;
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
        OldCrystals.a = var5;
        OldCrystals.PER_FACE_LIGHTING = OldCrystals.a[6];
        OldCrystals.CRYSTAL_TEXTURE = Identifier.method_60656((String)OldCrystals.a[2]);
    }
}
