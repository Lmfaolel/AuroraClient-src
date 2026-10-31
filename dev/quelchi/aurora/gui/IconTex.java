package dev.quelchi.aurora.gui;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.gui.IconTex$Layers;
import dev.quelchi.aurora.gui.IconTex$Lit;
import dev.quelchi.aurora.gui.PreviewScene;
import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public final class IconTex {
    private static final Map<int[][], IconTex$Layers> CACHE;
    private static final Map<int[][], Identifier> SOLID;
    private static int counter;
    private static final String[] a;

    private IconTex() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void drawCentred(DrawContext drawContext, int[][] nArray, int n, int n2, float f, int n3, int n4, int n5, boolean bl) {
        int[][] nArray2;
        block7: {
            block8: {
                int n6;
                block6: {
                    n6 = PreviewScene.a;
                    nArray2 = nArray;
                    if (n6 != 0) break block6;
                    if (nArray2 == null) return;
                    nArray2 = nArray;
                }
                if (n6 != 0) break block7;
                if (nArray2.length != 0) break block8;
                return;
            }
            nArray2 = nArray;
        }
        int n7 = Math.round((float)nArray2[0].length * f);
        int n8 = Math.round((float)nArray.length * f);
        int n9 = (n - n7) / 2 + n3;
        int n10 = (n2 - n8) / 2 + n4;
        IconTex.drawScaled(drawContext, nArray, n9, n10, n5, bl, f);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void drawCentredSolid(DrawContext drawContext, int[][] nArray, int n, int n2, float f, int n3, int n4, int n5) {
        Identifier identifier;
        int n6;
        int n7;
        int n8;
        int n9;
        block11: {
            int[][] nArray2;
            int n10;
            block9: {
                block10: {
                    block8: {
                        n10 = PreviewScene.a;
                        nArray2 = nArray;
                        if (n10 != 0) break block8;
                        if (nArray2 == null) return;
                        nArray2 = nArray;
                    }
                    if (n10 != 0) break block9;
                    if (nArray2.length != 0) break block10;
                    return;
                }
                nArray2 = nArray;
            }
            n9 = nArray2[0].length;
            n8 = nArray.length;
            n7 = Math.round((float)n9 * f);
            n6 = Math.round((float)n8 * f);
            identifier = SOLID.get(nArray);
            if (n10 != 0) return;
            if (identifier != null) break block11;
            identifier = IconTex.bake(nArray, n9, n8, IconTex$Lit.ANY);
            SOLID.put(nArray, identifier);
        }
        drawContext.method_25293(RenderPipelines.field_56883, identifier, (n - n7) / 2 + n3, (n2 - n6) / 2 + n4, 0.0f, 0.0f, n7, n6, n9, n8, n9, n8, n5);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void drawScaled(DrawContext drawContext, int[][] nArray, int n, int n2, int n3, boolean n4, float f) {
        IconTex$Layers iconTex$Layers;
        IconTex$Layers iconTex$Layers2;
        int n5;
        int n6;
        int n7;
        int n8;
        block19: {
            block20: {
                int n9;
                int n10;
                int n11;
                int n12;
                int n13;
                int n14;
                int n15;
                float f2;
                float f3;
                int n16;
                int n17;
                Identifier identifier;
                RenderPipeline renderPipeline;
                DrawContext drawContext2;
                block23: {
                    block24: {
                        int n18;
                        block22: {
                            block21: {
                                int[][] nArray2;
                                block17: {
                                    block18: {
                                        block16: {
                                            n18 = PreviewScene.a;
                                            nArray2 = nArray;
                                            if (n18 != 0) break block16;
                                            if (nArray2 == null) return;
                                            nArray2 = nArray;
                                        }
                                        if (n18 != 0) break block17;
                                        if (nArray2.length != 0) break block18;
                                        return;
                                    }
                                    nArray2 = nArray;
                                }
                                n8 = nArray2[0].length;
                                n7 = nArray.length;
                                n6 = Math.round((float)n8 * f);
                                n5 = Math.round((float)n7 * f);
                                iconTex$Layers2 = IconTex.layers(nArray);
                                iconTex$Layers = iconTex$Layers2;
                                if (n18 != 0) break block19;
                                if (iconTex$Layers.mask() == null) break block20;
                                drawContext2 = drawContext;
                                if (n4 == 0) break block21;
                                renderPipeline = RenderPipelines.field_56890;
                                break block22;
                            }
                            renderPipeline = RenderPipelines.field_56883;
                        }
                        identifier = iconTex$Layers2.mask();
                        n17 = n;
                        n16 = n2;
                        f3 = 0.0f;
                        f2 = 0.0f;
                        n15 = n6;
                        n14 = n5;
                        n13 = n8;
                        n12 = n7;
                        n11 = n8;
                        n10 = n7;
                        n9 = n4;
                        if (n18 != 0) break block23;
                        if (n9 == 0) break block24;
                        n9 = -1;
                        break block23;
                    }
                    n9 = n3;
                }
                drawContext2.method_25293(renderPipeline, identifier, n17, n16, f3, f2, n15, n14, n13, n12, n11, n10, n9);
            }
            iconTex$Layers = iconTex$Layers2;
        }
        if (iconTex$Layers.paint() == null) return;
        drawContext.method_25293(RenderPipelines.field_56883, iconTex$Layers2.paint(), n, n2, 0.0f, 0.0f, n6, n5, n8, n7, n8, n7, -1);
        return;
    }

    public static void invalidate(int[][] nArray) {
        block4: {
            IconTex$Layers iconTex$Layers;
            block5: {
                IconTex.drop(SOLID.remove(nArray));
                int n = PreviewScene.a;
                iconTex$Layers = CACHE.remove(nArray);
                if (n != 0) break block4;
                if (iconTex$Layers != null) break block5;
                return;
            }
            IconTex.drop(iconTex$Layers.mask());
            IconTex.drop(iconTex$Layers.paint());
        }
    }

    private static void drop(Identifier identifier) {
        if (identifier == null) {
            return;
        }
        try {
            MinecraftClient.method_1551().method_1531().method_4615(identifier);
        }
        catch (Throwable throwable) {
        }
    }

    
    private static IconTex$Layers layers(int[][] nArray) {
        int n;
        block15: {
            IconTex$Layers iconTex$Layers;
            block14: {
                IconTex$Layers iconTex$Layers2 = CACHE.get(nArray);
                n = PreviewScene.a;
                iconTex$Layers = iconTex$Layers2;
                if (n != 0) break block14;
                if (iconTex$Layers == null) break block15;
                iconTex$Layers = iconTex$Layers2;
            }
            return iconTex$Layers;
        }
        int n2 = nArray[0].length;
        int n3 = nArray.length;
        boolean bl = false;
        int n4 = 0;
        int[][] nArray2 = nArray;
        int n5 = nArray2.length;
        int n6 = 0;
        block5: while (true) {
            int n72 = n6;
            block6: while (n72 < n5) {
                int[] nArray3;
                for (int n72 : nArray3 = nArray2[n6]) {
                    int n8;
                    if (n != 0) continue block6;
                    int n9 = n8 = n72;
                    if (n == 0) {
                        if (n9 == 1) {
                            bl = true;
                            if (n == 0) continue;
                        }
                        n9 = n8;
                    }
                    if (n == 0) {
                        if (n9 == 0) continue;
                        n9 = 1;
                    }
                    n4 = n9;
                    if (n == 0) continue;
                }
                ++n6;
                if (n == 0) continue block5;
            }
            break;
        }
        IconTex$Layers iconTex$Layers = new IconTex$Layers(bl ? IconTex.bake(nArray, n2, n3, IconTex$Lit.MASK) : null, n4 != 0 ? IconTex.bake(nArray, n2, n3, IconTex$Lit.PAINT) : null);
        CACHE.put(nArray, iconTex$Layers);
        return iconTex$Layers;
    }

    /*
     * Exception decompiling
     */
    private static Identifier bake(int[][] var0, int var1_1, int var2_2, IconTex$Lit var3_3) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 12[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
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

    private static String lambda$bake$0() {
        return a[0];
    }

    
    static {
        block12: {
            var5 = new String[3];
            var3_1 = 0;
            var2_2 = "J\u0003'*w\u0001oB\u0015:+\u0006J\u0003'*w\u0001\u0005B\u0015:+*";
            var4_3 = "J\u0003'*w\u0001oB\u0015:+\u0006J\u0003'*w\u0001\u0005B\u0015:+*".length();
            var1_4 = 11;
            var0_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var5[var3_1++] = new String(v0).intern();
                if ((var0_5 += var1_4) < var4_3) {
                    var1_4 = var2_2.charAt(var0_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var0_5;
            v2 = var2_2.substring(v1, v1 + var1_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 43;
                        break;
                    }
                    case 1: {
                        v6 = 118;
                        break;
                    }
                    case 2: {
                        v6 = 85;
                        break;
                    }
                    case 3: {
                        v6 = 69;
                        break;
                    }
                    case 4: {
                        v6 = 5;
                        break;
                    }
                    case 5: {
                        v6 = 96;
                        break;
                    }
                    default: {
                        v6 = 64;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        IconTex.a = var5;
        IconTex.CACHE = new IdentityHashMap<int[][], IconTex$Layers>();
        IconTex.SOLID = new IdentityHashMap<int[][], Identifier>();
    }
}
