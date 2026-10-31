package dev.quelchi.aurora.gui.im;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.mixins.NativeImageAccessor;
import imgui.ImDrawData;
import imgui.ImFontAtlas;
import imgui.ImVec4;
import imgui.type.ImInt;
import java.lang.invoke.LambdaMetafactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.OptionalInt;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.MappableRingBuffer;
import net.minecraft.client.render.ProjectionMatrix2;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;
import org.joml.Vector4fc;
import org.lwjgl.system.MemoryUtil;

final class ImVk {
    private static final RenderPipeline PIPELINE;
    private static final int VERTEX;
    private static final ImVec4 CLIP;
    private static ProjectionMatrix2 projection;
    private static MappableRingBuffer verts;
    private static MappableRingBuffer indices;
    private static NativeImageBackedTexture fonts;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private ImVk() {
    }

    static void atlas(ImFontAtlas imFontAtlas) {
        block4: {
            NativeImage nativeImage;
            block5: {
                ImInt imInt = new ImInt();
                boolean bl = ImScreen.a;
                ImInt imInt2 = new ImInt();
                ByteBuffer byteBuffer = imFontAtlas.getTexDataAsRGBA32(imInt, imInt2);
                nativeImage = new NativeImage(imInt.get(), imInt2.get(), false);
                MemoryUtil.memCopy((long)MemoryUtil.memAddress((ByteBuffer)byteBuffer), (long)((NativeImageAccessor)nativeImage).aurora$pointer(), (long)((long)imInt.get() * (long)imInt2.get() * ImVk.a(12749, 7187636508987113489L)));
                if (bl) break block4;
                if (fonts == null) break block5;
                fonts.close();
            }
            fonts = new NativeImageBackedTexture(ImVk::lambda$atlas$0, nativeImage);
            imFontAtlas.setTexID(1L);
        }
    }

    
    static void draw(ImDrawData var0) {
        block112: {
            block123: {
                block122: {
                    block110: {
                        block109: {
                            block106: {
                                block105: {
                                    block104: {
                                        block134: {
                                            block101: {
                                                block102: {
                                                    block103: {
                                                        block133: {
                                                            block100: {
                                                                block132: {
                                                                    block131: {
                                                                        block130: {
                                                                            block96: {
                                                                                block97: {
                                                                                    block98: {
                                                                                        block99: {
                                                                                            block129: {
                                                                                                block95: {
                                                                                                    block128: {
                                                                                                        block127: {
                                                                                                            block126: {
                                                                                                                block125: {
                                                                                                                    block92: {
                                                                                                                        block94: {
                                                                                                                            block93: {
                                                                                                                                block91: {
                                                                                                                                    var2_1 = var0.getFramebufferScaleX();
                                                                                                                                    var3_2 = var0.getFramebufferScaleY();
                                                                                                                                    var4_3 = var0.getDisplayPosX();
                                                                                                                                    var5_4 = var0.getDisplayPosY();
                                                                                                                                    var6_5 = (int)(var0.getDisplaySizeX() * var2_1);
                                                                                                                                    var7_6 = (int)(var0.getDisplaySizeY() * var3_2);
                                                                                                                                    var1_7 = ImScreen.a;
                                                                                                                                    var8_8 = var0.getTotalVtxCount();
                                                                                                                                    var9_9 = var0.getTotalIdxCount();
                                                                                                                                    v0 = var6_5;
                                                                                                                                    if (var1_7) break block91;
                                                                                                                                    if (v0 <= 0) break block92;
                                                                                                                                    v0 = var7_6;
                                                                                                                                }
                                                                                                                                if (var1_7) break block93;
                                                                                                                                if (v0 <= 0) break block92;
                                                                                                                                v0 = var8_8;
                                                                                                                            }
                                                                                                                            if (var1_7) break block94;
                                                                                                                            if (v0 == 0) break block92;
                                                                                                                            v0 = var9_9;
                                                                                                                        }
                                                                                                                        if (v0 != 0) break block125;
                                                                                                                    }
                                                                                                                    return;
                                                                                                                }
                                                                                                                v4 = ImVk.verts;
                                                                                                                if (var1_7) break block95;
                                                                                                                if (v4 == null) // goto
                                                                                                                break block126;
                                                                                                                catch (Throwable v5) {
                                                                                                                    throw v5;
                                                                                                                }
                                                                                                            }
                                                                                                            v6 = ImVk.verts;
                                                                                                            if (var1_7) break block96;
                                                                                                            break block127;
                                                                                                            catch (Throwable v7) {
                                                                                                                throw v7;
                                                                                                            }
                                                                                                        }
                                                                                                        if (v6.method_71312() >= var8_8 * 24) break block97;
                                                                                                        break block128;
                                                                                                        catch (Throwable v8) {
                                                                                                            throw v8;
                                                                                                        }
                                                                                                    }
                                                                                                    v4 = ImVk.verts;
                                                                                                }
                                                                                                if (var1_7) break block98;
                                                                                                if (v4 == null) break block99;
                                                                                                break block129;
                                                                                                catch (Throwable v10) {
                                                                                                    throw v10;
                                                                                                }
                                                                                            }
                                                                                            ImVk.verts.close();
                                                                                        }
                                                                                        v4 = new MappableRingBuffer((Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$draw$1(), ()Ljava/lang/String;)(), 34, Integer.highestOneBit(var8_8 * 24) * 2);
                                                                                    }
                                                                                    ImVk.verts = v4;
                                                                                }
                                                                                v6 = ImVk.indices;
                                                                            }
                                                                            if (var1_7) break block100;
                                                                            if (v6 == null) // goto
                                                                            break block130;
                                                                            catch (Throwable v12) {
                                                                                throw v12;
                                                                            }
                                                                        }
                                                                        v6 = ImVk.indices;
                                                                        if (var1_7) break block100;
                                                                        break block131;
                                                                        catch (Throwable v13) {
                                                                            throw v13;
                                                                        }
                                                                    }
                                                                    if (v6.method_71312() >= var9_9 * 2) break block101;
                                                                    break block132;
                                                                    catch (Throwable v14) {
                                                                        throw v14;
                                                                    }
                                                                }
                                                                v6 = ImVk.indices;
                                                            }
                                                            if (var1_7) break block102;
                                                            if (v6 == null) break block103;
                                                            break block133;
                                                            catch (Throwable v16) {
                                                                throw v16;
                                                            }
                                                        }
                                                        ImVk.indices.close();
                                                    }
                                                    v6 = new MappableRingBuffer((Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$draw$2(), ()Ljava/lang/String;)(), 66, Integer.highestOneBit(var9_9 * 2) * 2);
                                                }
                                                ImVk.indices = v6;
                                            }
                                            v18 = ImVk.projection;
                                            if (var1_7) break block104;
                                            if (v18 != null) break block105;
                                            break block134;
                                            catch (Throwable v19) {
                                                throw v19;
                                            }
                                        }
                                        v18 = new ProjectionMatrix2(ImVk.a[8], -1000.0f, 1000.0f, true);
                                    }
                                    ImVk.projection = v18;
                                }
                                var10_10 = RenderSystem.getDevice().createCommandEncoder();
                                var11_11 = ImVk.verts.method_71119();
                                var12_12 = ImVk.indices.method_71119();
                                var13_13 = var0.getCmdListsCount();
                                var14_14 = var10_10.mapBuffer(var11_11.slice(0L, (long)var8_8 * ImVk.a(6804, 6953614345108187977L)), false, true);
                                try {
                                    block107: {
                                        block135: {
                                            var15_15 = var10_10.mapBuffer(var12_12.slice(0L, (long)var9_9 * ImVk.a(13963, 7771465510399330133L)), false, true);
                                            try {
                                                var16_17 = var14_14.data().order(ByteOrder.nativeOrder());
                                                var17_20 = var15_15.data();
                                                block82: for (var18_22 = 0; var18_22 < var13_13; ++var18_22) {
                                                    if (!var1_7) {
                                                        var19_24 = var0.getCmdListVtxBufferData(var18_22);
                                                        for (var20_27 = 0; var20_27 < var19_24.limit(); var20_27 += 20) {
                                                            var16_17.putFloat(var19_24.getFloat(var20_27)).putFloat(var19_24.getFloat(var20_27 + 4)).putFloat(0.0f);
                                                            v21 = var16_17.putFloat(var19_24.getFloat(var20_27 + 8));
                                                            if (var1_7) continue block82;
                                                            v21.putFloat(var19_24.getFloat(var20_27 + 12)).putInt(var19_24.getInt(var20_27 + 16));
                                                            if (!var1_7) continue;
                                                            break;
                                                        }
                                                        v21 = var17_20.put(var0.getCmdListIdxBufferData(var18_22));
                                                        if (!var1_7) continue;
                                                    }
                                                }
                                            }
                                            catch (Throwable var16_18) {
                                                block108: {
                                                    if (var1_7 || var15_15 == null) break block108;
                                                    try {
                                                        var15_15.close();
                                                    }
                                                    catch (Throwable var17_21) {
                                                        var16_18.addSuppressed(var17_21);
                                                    }
                                                }
                                                throw var16_18;
                                            }
                                            v24 = var15_15;
                                            if (var1_7) break block106;
                                            if (v24 == null) break block107;
                                            break block135;
                                            catch (Throwable v25) {
                                                throw v25;
                                            }
                                        }
                                        var15_15.close();
                                    }
                                    v24 = var14_14;
                                }
                                catch (Throwable var15_16) {
                                    block111: {
                                        if (var1_7 || var14_14 == null) break block111;
                                        try {
                                            var14_14.close();
                                        }
                                        catch (Throwable var16_19) {
                                            var15_16.addSuppressed(var16_19);
                                        }
                                    }
                                    throw var15_16;
                                }
                            }
                            if (var1_7) break block109;
                            if (v24 == null) break block110;
                            v24 = var14_14;
                        }
                        v24.close();
                    }
                    var14_14 = ImVk.projection.method_71092(var0.getDisplaySizeX(), var0.getDisplaySizeY());
                    var15_15 = RenderSystem.getDynamicUniforms().method_71106((Matrix4fc)new Matrix4f(), (Vector4fc)new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), (Vector3fc)new Vector3f(), (Matrix4fc)new Matrix4f());
                    var16_17 = MinecraftClient.method_1551().method_1522().method_71639();
                    var17_20 = var10_10.createRenderPass((Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$draw$3(), ()Ljava/lang/String;)(), (GpuTextureView)var16_17, OptionalInt.empty());
                    try {
                        RenderSystem.bindDefaultUniforms((RenderPass)var17_20);
                        var29_28 = ImVk.a;
                        var17_20.setUniform(var29_28[6], (GpuBufferSlice)var14_14);
                        var17_20.setUniform(var29_28[2], (GpuBufferSlice)var15_15);
                        var17_20.setPipeline(ImVk.PIPELINE);
                        var17_20.setVertexBuffer(0, var11_11);
                        var17_20.setIndexBuffer(var12_12, VertexFormat.IndexType.field_27372);
                        var18_22 = 0;
                        var19_25 = 0;
                        for (var20_27 = 0; var20_27 < var13_13; ++var20_27) {
                            block113: {
                                var21_29 = var0.getCmdListCmdBufferSize(var20_27);
                                if (var1_7) break block112;
                                var22_30 = 0;
                                while (var22_30 < var21_29) {
                                    block114: {
                                        block115: {
                                            block121: {
                                                block139: {
                                                    block120: {
                                                        block118: {
                                                            block119: {
                                                                block138: {
                                                                    block116: {
                                                                        block117: {
                                                                            block137: {
                                                                                block136: {
                                                                                    var0.getCmdListCmdBufferClipRect(ImVk.CLIP, var20_27, var22_30);
                                                                                    var23_31 = Math.max(0, (int)((ImVk.CLIP.x - var4_3) * var2_1));
                                                                                    var24_32 = Math.max(0, (int)((ImVk.CLIP.y - var5_4) * var3_2));
                                                                                    v29 = var6_5;
                                                                                    if (var1_7) break block113;
                                                                                    var25_33 = Math.min(v29, (int)((ImVk.CLIP.z - var4_3) * var2_1));
                                                                                    var26_34 = Math.min(var7_6, (int)((ImVk.CLIP.w - var5_4) * var3_2));
                                                                                    if (var1_7) break block114;
                                                                                    if (var25_33 <= var23_31) break block115;
                                                                                    break block136;
                                                                                    catch (Throwable v30) {
                                                                                        throw v30;
                                                                                    }
                                                                                }
                                                                                v31 = var26_34;
                                                                                if (var1_7) break block116;
                                                                                break block137;
                                                                                catch (Throwable v32) {
                                                                                    throw v32;
                                                                                }
                                                                            }
                                                                            if (v31 > var24_32) break block117;
                                                                            break block115;
                                                                            catch (Throwable v33) {
                                                                                throw v33;
                                                                            }
                                                                        }
                                                                        v31 = (int)var0.getCmdListCmdBufferTextureId(var20_27, var22_30);
                                                                    }
                                                                    var27_35 = v31;
                                                                    v35 = var27_35;
                                                                    if (var1_7) break block118;
                                                                    if (v35 != 1) break block119;
                                                                    break block138;
                                                                    catch (Throwable v36) {
                                                                        throw v36;
                                                                    }
                                                                }
                                                                v37 = ImVk.fonts.method_71659();
                                                                break block120;
                                                            }
                                                            v35 = var27_35;
                                                        }
                                                        v37 = Ui.view(v35);
                                                    }
                                                    var28_36 = v37;
                                                    if (var1_7) break block114;
                                                    if (var28_36 == null) break block115;
                                                    break block139;
                                                    catch (Throwable v39) {
                                                        throw v39;
                                                    }
                                                }
                                                if (!var28_36.isClosed()) break block121;
                                                break block115;
                                                catch (Throwable v40) {
                                                    throw v40;
                                                }
                                            }
                                            v42 = var17_20;
                                            v43 = ImVk.a[3];
                                            v44 = var28_36;
                                            v45 = RenderSystem.getSamplerCache();
                                            v46 = Ui.nearest(var27_35) != false ? FilterMode.NEAREST : FilterMode.LINEAR;
                                            v42.bindTexture(v43, v44, v45.method_75294(v46));
                                            var17_20.enableScissor(var23_31, var7_6 - var26_34, var25_33 - var23_31, var26_34 - var24_32);
                                            var17_20.drawIndexed(var18_22 + var0.getCmdListCmdBufferVtxOffset(var20_27, var22_30), var19_25 + var0.getCmdListCmdBufferIdxOffset(var20_27, var22_30), var0.getCmdListCmdBufferElemCount(var20_27, var22_30), 1);
                                        }
                                        ++var22_30;
                                    }
                                    if (!var1_7) continue;
                                }
                                var18_22 += var0.getCmdListVtxBufferSize(var20_27);
                                v29 = var19_25 + var0.getCmdListIdxBufferSize(var20_27);
                            }
                            var19_25 = v29;
                            if (!var1_7) continue;
                        }
                    }
                    catch (Throwable var18_23) {
                        block124: {
                            if (var1_7 || var17_20 == null) break block124;
                            try {
                                var17_20.close();
                            }
                            catch (Throwable var19_26) {
                                var18_23.addSuppressed(var19_26);
                            }
                        }
                        throw var18_23;
                    }
                    v49 = var17_20;
                    if (var1_7) break block122;
                    if (v49 == null) break block123;
                    v49 = var17_20;
                }
                v49.close();
            }
            ImVk.verts.method_71121();
            ImVk.indices.method_71121();
        }
    }

    private static String lambda$draw$3() {
        return a[7];
    }

    private static String lambda$draw$2() {
        return a[5];
    }

    private static String lambda$draw$1() {
        return a[10];
    }

    private static String lambda$atlas$0() {
        return a[4];
    }

    /*
     * Exception decompiling
     */
    static {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: CONTINUE without a while class org.benf.cfr.reader.bytecode.analysis.parse.statement.AssignmentSimple
         *     at org.benf.cfr.reader.bytecode.analysis.parse.statement.GotoStatement.getTargetStartBlock(GotoStatement.java:102)
         *     at org.benf.cfr.reader.bytecode.analysis.parse.statement.GotoStatement.getStructuredStatement(GotoStatement.java:116)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.getStructuredStatementPlaceHolder(Op03SimpleStatement.java:550)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:727)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:845)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1042)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:929)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:73)
         *     at org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x71DC) & Short.MAX_VALUE;
        if (c[n2] == null) {
            ImVk.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
