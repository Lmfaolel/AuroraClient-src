package dev.quelchi.aurora.render;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.mixins.RenderLayerInvoker;
import dev.quelchi.aurora.render.EffectEngine;
import java.lang.invoke.LambdaMetafactory;
import java.util.LinkedHashSet;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.util.Identifier;

public final class GhostCrystals {
    private static final String PER_FACE_LIGHTING;
    private static final Identifier CRYSTAL_TEXTURE;
    private static RenderLayer plain;
    private static RenderLayer legacy;
    private static boolean failed;
    private static final String[] a;
    private static final long b;

    private GhostCrystals() {
    }

    
    public static RenderLayer layer(boolean var0) {
        block16: {
            block22: {
                var1_1 = EffectEngine.a;
                v0 = GhostCrystals.failed;
                if (var1_1 != 0) // goto
                if (!v0) break block16;
                break block22;
                catch (Throwable v1) {
                    throw v1;
                }
            }
            return null;
        }
        try {
            block20: {
                block21: {
                    block25: {
                        block17: {
                            block18: {
                                block19: {
                                    block24: {
                                        block23: {
                                            v0 = var0;
lbl18:

                                            if (!v0) break block17;
                                            v3 = GhostCrystals.legacy;
                                            if (var1_1 != 0) break block18;
                                            break block23;
                                            catch (Throwable v4) {
                                                throw v4;
                                            }
                                        }
                                        if (v3 != null) break block19;
                                        break block24;
                                        catch (Throwable v5) {
                                            throw v5;
                                        }
                                    }
                                    GhostCrystals.legacy = GhostCrystals.build(true);
                                }
                                v3 = GhostCrystals.legacy;
                            }
                            return v3;
                        }
                        v7 = GhostCrystals.plain;
                        if (var1_1 != 0) break block20;
                        if (v7 != null) break block21;
                        break block25;
                        catch (Throwable v8) {
                            throw v8;
                        }
                    }
                    GhostCrystals.plain = GhostCrystals.build(false);
                }
                v7 = GhostCrystals.plain;
            }
            return v7;
        }
        catch (Throwable var2_2) {
            GhostCrystals.failed = true;
            Aurora.LOG.warn(GhostCrystals.a[5], var2_2);
            return null;
        }
    }

    public static int tint(float f) {
        int n = Math.round(Math.max(0.0f, Math.min(1.0f, f)) * 255.0f);
        return n << 24 | (int)b;
    }

    
    private static RenderLayer build(boolean var0) {
        block20: {
            block19: {
                block18: {
                    block17: {
                        var2_1 = RenderPipelines.field_56906;
                        var1_2 = EffectEngine.a;
                        v0 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]);
                        v1 = GhostCrystals.a[7];
                        if (!var0) break block17;
                        v2 = GhostCrystals.a[1];
                        break block18;
                    }
                    var8_3 = GhostCrystals.a;
                    v2 = var8_3[0];
                }
                var3_4 = v0.withLocation(Identifier.method_60655((String)v1, (String)v2)).withVertexShader(var2_1.getVertexShader()).withFragmentShader(var2_1.getFragmentShader()).withVertexFormat(var2_1.getVertexFormat(), var2_1.getVertexFormatMode()).withDepthTestFunction(var2_1.getDepthTestFunction()).withPolygonMode(var2_1.getPolygonMode()).withCull(var2_1.isCull()).withColorWrite(var2_1.isWriteColor(), var2_1.isWriteAlpha()).withDepthWrite(var2_1.isWriteDepth()).withColorLogic(var2_1.getColorLogic()).withDepthBias(var2_1.getDepthBiasScaleFactor(), var2_1.getDepthBiasConstant());
                var2_1.getBlendFunction().ifPresent((Consumer<BlendFunction>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, withBlend(com.mojang.blaze3d.pipeline.BlendFunction ), (Lcom/mojang/blaze3d/pipeline/BlendFunction;)V)((RenderPipeline.Builder)var3_4));
                var4_5 = var2_1.getSamplers().iterator();
                while (var4_5.hasNext()) {
                    var5_6 = (String)var4_5.next();
                    v4 /* !! */  = var3_4;
                    if (var1_2 == 0) {
                        v4 /* !! */ .withSampler((String)var5_6);
                        if (var1_2 == 0) continue;
                        break;
                    }
                }
                for (RenderPipeline.Builder v4 : var2_1.getUniforms()) lbl_neg_1000:

                {
                    var5_6 = (RenderPipeline.UniformDescription)v4 /* !! */ ;
                    var3_4.withUniform(var5_6.name(), var5_6.type());
                    if (var1_2 == 0) continue;
                }
                var4_5 = var2_1.getShaderDefines();
                var4_5.comp_3103().forEach((BiConsumer<String, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, lambda$build$0(boolean com.mojang.blaze3d.pipeline.RenderPipeline$Builder java.lang.String java.lang.String ), (Ljava/lang/String;Ljava/lang/String;)V)((boolean)var0, (RenderPipeline.Builder)var3_4));
                var5_6 = new LinkedHashSet<E>(var4_5.comp_3104());
                v6 = var0;
                if (var1_2 != 0 || !v6) break block19;
                v6 = var5_6.remove(GhostCrystals.a[9]);
            }
            var6_7 = var5_6.iterator();
            while (var6_7.hasNext()) {
                var7_8 = (String)var6_7.next();
                v9 = var3_4;
                if (var1_2 == 0) {
                    v9.withShaderDefine(var7_8);
                    if (var1_2 == 0) continue;
                    break;
                }
                break block20;
            }
            v9 = var3_4;
        }
        var8_3 = GhostCrystals.a;
        var6_7 = RenderSetup.method_75927((RenderPipeline)v9.build()).method_75934(var8_3[6], GhostCrystals.CRYSTAL_TEXTURE).method_75928().method_75935().method_75936().method_75937().method_75932(RenderSetup.OutlineMode.field_21855).method_75938();
        v11 = var0 != false ? var8_3[3] : GhostCrystals.a[8];
        return RenderLayerInvoker.aurora$of(v11, var6_7);
    }

    private static float parse(String string) {
        try {
            return Float.parseFloat(string);
        }
        catch (NumberFormatException numberFormatException) {
            return 0.0f;
        }
    }

    
    private static void lambda$build$0(boolean bl, RenderPipeline.Builder builder, String string, String string2) {
        block6: {
            boolean bl2;
            block5: {
                int n = EffectEngine.a;
                bl2 = bl;
                if (n != 0) break block5;
                if (!bl2) break block6;
                bl2 = a[4].equals(string);
            }
            if (bl2) return;
        }
        builder.withShaderDefine(string, GhostCrystals.parse(string2));
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[10];
                    var5_1 = 0;
                    var4_2 = "]dw\nP\u0007?H\"`\u0007S\u001d%rnu\u0016O\u001a0A\u001a]dw\nP\u0007?H\"`\u0007S\u001d%rnu\u0016O\u001a0ARh\u0003X+Yh\u007f\u001bI\u001c4^\"b\u0001H\u0007%T\"b\u0001X12_tt\u001b]\u0002~Hcc0_\u001c(^yf\u0003\u0012\u001e?J\u0018Lxu\u0000N\u000f\u000eJeh\u001cH12_tt\u001b]\u0002\u000eBac\u0011}HU0z/\u0012hRK&{&\u0005dC@'y\u007ff\u0001O\u0002$Nhi\u001b\u001c\r#T~s\u000ePN!_hq\u0006Y\u0019qXcf\u0019]\u0007=Lok\n\b~lj\u001fP\u000b#\u001d\u0006Lxu\u0000N\u000f";
                    var6_3 = "]dw\nP\u0007?H\"`\u0007S\u001d%rnu\u0016O\u001a0A\u001a]dw\nP\u0007?H\"`\u0007S\u001d%rnu\u0016O\u001a0ARh\u0003X+Yh\u007f\u001bI\u001c4^\"b\u0001H\u0007%T\"b\u0001X12_tt\u001b]\u0002~Hcc0_\u001c(^yf\u0003\u0012\u001e?J\u0018Lxu\u0000N\u000f\u000eJeh\u001cH12_tt\u001b]\u0002\u000eBac\u0011}HU0z/\u0012hRK&{&\u0005dC@'y\u007ff\u0001O\u0002$Nhi\u001b\u001c\r#T~s\u000ePN!_hq\u0006Y\u0019qXcf\u0019]\u0007=Lok\n\b~lj\u001fP\u000b#\u001d\u0006Lxu\u0000N\u000f".length();
                    var3_4 = 22;
                    var2_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var2_5;
                        v1 = var4_2.substring(v0, v0 + var3_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        var4_2 = "Lxu\u0000N\u000f\u000eJeh\u001cH12_tt\u001b]\u0002\u0011}HU0z/\u0012hRK&{&\u0005dC@";
                        var6_3 = "Lxu\u0000N\u000f\u000eJeh\u001cH12_tt\u001b]\u0002\u0011}HU0z/\u0012hRK&{&\u0005dC@".length();
                        var3_4 = 20;
                        var2_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var2_5;
                            v1 = var4_2.substring(v4, v4 + var3_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var8_6 = 0;
                do {
                    v6 = v6;
                    v8 = var8_6;
                    v9 = v6[v8];
                    switch (var8_6 % 7) {
                        case 0: {
                            v10 = 45;
                            break;
                        }
                        case 1: {
                            v10 = 13;
                            break;
                        }
                        case 2: {
                            v10 = 7;
                            break;
                        }
                        case 3: {
                            v10 = 111;
                            break;
                        }
                        case 4: {
                            v10 = 60;
                            break;
                        }
                        case 5: {
                            v10 = 110;
                            break;
                        }
                        default: {
                            v10 = 81;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var8_6;
lbl65:

                    v7 = v7;
                } while (v7 > var8_6);
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
            GhostCrystals.a = var7;
            GhostCrystals.PER_FACE_LIGHTING = GhostCrystals.a[4];
            break block21;
lbl79:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 1178193840348414506L;
        while (true)
        GhostCrystals.b = -4743836560641049131L ^ var0_7;
        GhostCrystals.CRYSTAL_TEXTURE = Identifier.method_60656((String)GhostCrystals.a[2]);
    }
}
