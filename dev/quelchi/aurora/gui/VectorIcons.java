package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.VectorIcons$Shape;
import dev.quelchi.aurora.gui.VectorIcons$Svg;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.lang.invoke.LambdaMetafactory;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;

public final class VectorIcons {
    private static final Map<String, Identifier> BAKED;
    private static final Map<String, VectorIcons$Svg> PARSED;
    private static final VectorIcons$Svg BROKEN;
    private static final Pattern ELEMENT;
    private static final Pattern ATTR;
    private static final Pattern TOKEN;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private VectorIcons() {
    }

    public static boolean draw(DrawContext drawContext, String string, int n, int n2, int n3, int n4) {
        block11: {
            Identifier identifier;
            int n5;
            block12: {
                int n6;
                block10: {
                    int n7;
                    block9: {
                        block8: {
                            n6 = PreviewScene.a;
                            if (string == null) break block8;
                            n7 = n3;
                            if (n6 != 0) break block9;
                            if (n7 > 0) break block10;
                        }
                        n7 = 0;
                    }
                    return n7 != 0;
                }
                MinecraftClient minecraftClient = MinecraftClient.method_1551();
                float f = drawContext.method_51448().m00() * (float)Math.max(1, minecraftClient.method_22683().method_4495());
                n5 = Math.max(4, Math.round((float)n3 * f));
                identifier = VectorIcons.texture(string, n5);
                if (n6 != 0) break block11;
                if (identifier != null) break block12;
                return false;
            }
            drawContext.method_25293(RenderPipelines.field_56883, identifier, n, n2, 0.0f, 0.0f, n3, n3, n5, n5, n5, n5, n4);
        }
        return true;
    }

    public static Identifier texture(String string, int n) {
        Identifier identifier;
        block24: {
            Identifier identifier2;
            block25: {
                Object object;
                String string2;
                int n2;
                block22: {
                    block23: {
                        Identifier identifier3;
                        block20: {
                            block21: {
                                block19: {
                                    n2 = PreviewScene.a;
                                    if (string != null && n > 0) break block19;
                                    return null;
                                }
                                string2 = string + "_" + n;
                                Identifier identifier4 = BAKED.get(string2);
                                identifier3 = identifier4;
                                if (n2 != 0) break block20;
                                if (identifier3 == null) break block21;
                                return identifier4;
                            }
                            identifier3 = VectorIcons.loadPng(string, string2, n);
                        }
                        Identifier identifier5 = identifier3;
                        object = identifier5;
                        if (n2 != 0) break block22;
                        if (object == null) break block23;
                        BAKED.put(string2, identifier5);
                        return identifier5;
                    }
                    object = PARSED.computeIfAbsent(string, VectorIcons::load);
                }
                VectorIcons$Svg vectorIcons$Svg = (VectorIcons$Svg)object;
                if (vectorIcons$Svg == BROKEN) {
                    return null;
                }
                identifier2 = VectorIcons.bake(string2, vectorIcons$Svg, n);
                identifier = identifier2;
                if (n2 != 0) break block24;
                if (identifier == null) break block25;
                BAKED.put(string2, identifier2);
            }
            identifier = identifier2;
        }
        return identifier;
    }

    
    private static Identifier loadPng(String var0, String var1_1, int var2_2) {
        var19_3 = VectorIcons.a;
        var4_4 = Identifier.method_60655((String)var19_3[27], (String)(var19_3[26] + var0 + var19_3[10]));
        var3_5 = PreviewScene.a;
        try {
            block36: {
                block37: {
                    block34: {
                        block33: {
                            block31: {
                                block32: {
                                    block44: {
                                        block43: {
                                            var5_6 = MinecraftClient.method_1551().method_1478().method_14486(var4_4);
                                            v0 /* !! */  = var5_6;
                                            if (var3_5 != 0) break block31;
                                            if (!v0 /* !! */ .isEmpty()) break block32;
                                            break block43;
                                            catch (Throwable v1) {
                                                throw v1;
                                            }
                                        }
                                        v2 = var0.contains("/");
                                        if (var3_5 != 0) break block33;
                                        break block44;
                                        catch (Throwable v3) {
                                            throw v3;
                                        }
                                    }
                                    if (v2) break block32;
                                    var19_3 = VectorIcons.a;
                                    var6_8 = Identifier.method_60655((String)var19_3[27], (String)(var19_3[13] + var0 + var19_3[2]));
                                    var5_6 = MinecraftClient.method_1551().method_1478().method_14486((Identifier)var6_8);
                                }
                                v0 /* !! */  = var5_6;
                            }
                            if (var3_5 != 0) break block34;
                            v2 = v0 /* !! */ .isEmpty();
                        }
                        if (v2) {
                            return null;
                        }
                        v0 /* !! */  = var5_6.get();
                    }
                    var7_9 = ((Resource)v0 /* !! */ ).method_14482();
                    try {
                        var6_8 = ImageIO.read((InputStream)var7_9);
                        if (var3_5 != 0) goto lbl_neg_1000
                    }
                    catch (Throwable var8_10) {
                        block35: {
                            if (var3_5 != 0 || var7_9 == null) break block35;
                            try {
                                var7_9.close();
                            }
                            catch (Throwable var9_12) {
                                var8_10.addSuppressed(var9_12);
                            }
                        }
                        throw var8_10;
                    }
lbl_neg_1000:

                    {
                        if (var7_9 == null) // goto
                        var7_9.close();
                    }
lbl_neg_1000:

                    {
                    }
lbl68:

                    v8 = var6_8;
                    if (var3_5 != 0) break block36;
                    if (v8 != null) break block37;
                    return null;
                }
                v8 = new BufferedImage(var2_2, var2_2, 2);
            }
            var7_9 = v8;
            var8_11 = var7_9.createGraphics();
            try {
                var8_11.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                var8_11.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                var8_11.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                var8_11.drawImage(var6_8, 0, 0, var2_2, var2_2, null);
            }
            finally {
                var8_11.dispose();
            }
            var9_13 = new NativeImage(NativeImage.Format.field_4997, var2_2, var2_2, true);
            var10_15 = 0;
            while (var10_15 < var2_2) {
                block39: {
                    for (var11_17 = 0; var11_17 < var2_2; ++var11_17) {
                        block42: {
                            block40: {
                                block41: {
                                    block45: {
                                        var12_19 = var7_9.getRGB(var11_17, var10_15);
                                        if (var3_5 != 0) break block39;
                                        var13_20 = var12_19 >>> 24 & 255;
                                        var14_21 = var12_19 >>> 16 & 255;
                                        var15_22 = var12_19 >>> 8 & 255;
                                        var16_23 = var12_19 & 255;
                                        var17_24 = (var14_21 * 299 + var15_22 * 587 + var16_23 * 114) / 1000;
                                        var18_25 = var13_20 * var17_24 / 255;
                                        v10 = var9_13;
                                        v11 = var11_17;
                                        v12 = var10_15;
                                        v13 = var18_25;
                                        if (var3_5 != 0) break block40;
                                        if (v13 != 0) break block41;
                                        break block45;
                                        catch (Throwable v14) {
                                            throw v14;
                                        }
                                    }
                                    v15 = 0;
                                    break block42;
                                }
                                v13 = var18_25 << 24;
                            }
                            v15 = v13 | VectorIcons.a(27857, 9031714820587255675L);
                        }
                        v10.method_61941(v11, v12, v15);
                        if (var3_5 == 0) continue;
                    }
                    ++var10_15;
                }
                if (var3_5 == 0) continue;
            }
            var19_3 = VectorIcons.a;
            var10_16 = Identifier.method_60655((String)var19_3[27], (String)(var19_3[1] + var1_1.toLowerCase(Locale.ROOT)));
            var11_18 = new NativeImageBackedTexture((Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$loadPng$0(java.lang.String ), ()Ljava/lang/String;)((String)var1_1), var9_13);
            var11_18.method_4524();
            MinecraftClient.method_1551().method_1531().method_4616(var10_16, (AbstractTexture)var11_18);
            return var10_16;
        }
        catch (Throwable var5_7) {
            Aurora.LOG.warn(VectorIcons.a[11], (Object)var4_4, (Object)var5_7);
            return null;
        }
    }

    public static void warm() {
        Thread thread = new Thread(VectorIcons::lambda$warm$1, a[22]);
        thread.setDaemon(true);
        thread.setPriority(1);
        thread.start();
    }

    
    private static Identifier bake(String string, VectorIcons$Svg vectorIcons$Svg, int n) {
        int n2 = PreviewScene.a;
        try {
            BufferedImage bufferedImage;
            block21: {
                bufferedImage = new BufferedImage(n, n, 2);
                Graphics2D graphics2D = bufferedImage.createGraphics();
                try {
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    graphics2D.scale((float)n / vectorIcons$Svg.viewBox(), (float)n / vectorIcons$Svg.viewBox());
                    graphics2D.setColor(Color.WHITE);
                    for (VectorIcons$Shape vectorIcons$Shape : vectorIcons$Svg.shapes()) {
                        VectorIcons$Shape vectorIcons$Shape2;
                        block22: {
                            block23: {
                                block28: {
                                    block27: {
                                        if (n2 != 0) break block21;
                                        vectorIcons$Shape2 = vectorIcons$Shape;
                                        if (n2 != 0) break block22;
                                        break block27;
                                        catch (Throwable throwable) {
                                            throw throwable;
                                        }
                                    }
                                    if (!vectorIcons$Shape2.fill()) break block23;
                                    break block28;
                                    catch (Throwable throwable) {
                                        throw throwable;
                                    }
                                }
                                graphics2D.fill(vectorIcons$Shape.path());
                            }
                            vectorIcons$Shape2 = vectorIcons$Shape;
                        }
                        if (vectorIcons$Shape2.strokeWidth() > 0.0f) {
                            graphics2D.setStroke(new BasicStroke(vectorIcons$Shape.strokeWidth(), vectorIcons$Shape.cap(), vectorIcons$Shape.join()));
                            graphics2D.draw(vectorIcons$Shape.path());
                        }
                        if (n2 == 0) continue;
                    }
                }
                catch (Throwable throwable) {
                    graphics2D.dispose();
                    throw throwable;
                }
                graphics2D.dispose();
            }
            NativeImage nativeImage = new NativeImage(NativeImage.Format.field_4997, n, n, true);
            int n3 = 0;
            block17: while (true) {
                int n4 = n3;
                block18: while (n4 < n) {
                    for (int i = 0; i < n; ++i) {
                        int n5;
                        int n6;
                        int n7;
                        NativeImage nativeImage2;
                        block26: {
                            int n8;
                            block24: {
                                int n9;
                                block25: {
                                    block29: {
                                        n4 = bufferedImage.getRGB(i, n3) >>> 24;
                                        if (n2 != 0) continue block18;
                                        n9 = n4;
                                        nativeImage2 = nativeImage;
                                        n7 = i;
                                        n6 = n3;
                                        n8 = n9;
                                        if (n2 != 0) break block24;
                                        if (n8 != 0) break block25;
                                        break block29;
                                        catch (Throwable throwable) {
                                            throw throwable;
                                        }
                                    }
                                    n5 = 0;
                                    break block26;
                                }
                                n8 = n9 << 24;
                            }
                            n5 = n8 | VectorIcons.a(566, 8692732728044263837L);
                        }
                        nativeImage2.method_61941(n7, n6, n5);
                        if (n2 == 0) continue;
                    }
                    ++n3;
                    if (n2 == 0) continue block17;
                }
                break;
            }
            String[] stringArray = a;
            Identifier identifier = Identifier.method_60655((String)stringArray[21], (String)(stringArray[41] + string.toLowerCase(Locale.ROOT)));
            NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(() -> VectorIcons.lambda$bake$2(string), nativeImage);
            nativeImageBackedTexture.method_4524();
            MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)nativeImageBackedTexture);
            return identifier;
        }
        catch (Throwable throwable) {
            Aurora.LOG.warn(a[34], (Object)string, (Object)throwable);
            return null;
        }
    }

    
    private static VectorIcons$Svg load(String string) {
        String[] stringArray = a;
        Identifier identifier = Identifier.method_60655((String)stringArray[27], (String)(stringArray[45] + string + stringArray[46]));
        int n = PreviewScene.a;
        try {
            String string2;
            Optional optional;
            block20: {
                boolean bl;
                Optional optional2;
                block19: {
                    block17: {
                        block18: {
                            optional = optional2 = MinecraftClient.method_1551().method_1478().method_14486(identifier);
                            if (n != 0) break block17;
                            if (!optional.isEmpty()) break block18;
                            bl = string.contains("/");
                            if (n != 0) break block19;
                            if (!bl) {
                                stringArray = a;
                                string2 = Identifier.method_60655((String)stringArray[27], (String)(stringArray[25] + string + stringArray[33]));
                                optional2 = MinecraftClient.method_1551().method_1478().method_14486((Identifier)string2);
                            }
                        }
                        optional = optional2;
                    }
                    if (n != 0) break block20;
                    bl = optional.isEmpty();
                }
                if (bl) {
                    stringArray = a;
                    Aurora.LOG.warn(stringArray[15], (Object)identifier);
                    return BROKEN;
                }
                optional = optional2.get();
            }
            InputStream inputStream = ((Resource)optional).method_14482();
            try {
                string2 = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                if (n != 0) return VectorIcons.parse(string2);
                if (inputStream == null) return VectorIcons.parse(string2);
            }
            catch (Throwable throwable) {
                if (n != 0) throw throwable;
                if (inputStream == null) throw throwable;
                try {
                    inputStream.close();
                    throw throwable;
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            inputStream.close();
            return VectorIcons.parse(string2);
        }
        catch (Throwable throwable) {
            Aurora.LOG.warn(a[30], (Object)identifier, (Object)throwable);
            return BROKEN;
        }
    }

    
    private static VectorIcons$Svg parse(String var0) {
        var2_1 = 24.0f;
        var3_2 = Map.of();
        var1_3 = PreviewScene.a;
        var4_4 = new ArrayList<VectorIcons$Shape>();
        var5_5 = VectorIcons.ELEMENT.matcher(var0);
        while (var5_5.find()) {
            block30: {
                var6_6 = VectorIcons.attrs(var5_5.group(2));
                var7_7 = var5_5.group(1);
                var8_8 = -1;
                v0 = var7_7.hashCode();
                if (var1_3 != 0) break block30;
                switch (v0) {
                    case 114276: {
                        v0 = (int)var7_7.equals(VectorIcons.a[29]);
                        if (var1_3 == 0) {
                            if (v0 == 0) break;
                            var8_8 = 0;
                            if (var1_3 == 0) break;
                        }
                        break block30;
                    }
                    case 3433509: {
                        v0 = (int)var7_7.equals(VectorIcons.a[6]);
                        if (var1_3 == 0) {
                            if (v0 == 0) break;
                            var8_8 = 1;
                            if (var1_3 == 0) break;
                        }
                        break block30;
                    }
                    case -1360216880: {
                        v0 = (int)var7_7.equals(VectorIcons.a[32]);
                        if (var1_3 != 0) break block30;
                        if (v0 == 0) break;
                        var8_8 = 2;
                    }
                }
                v0 = var8_8;
            }
            switch (v0) {
                case 0: {
                    var3_2 = var6_6;
                    var13_15 = VectorIcons.a;
                    v1 = var9_9 = var6_6.get(var13_15[36]);
                    if (var1_3 != 0) // goto
                    if (v1 == null) // goto
                    v1 = var9_9.trim();
lbl42:

                    var13_15 = VectorIcons.a;
                    v2 = var10_11 = v1.split(var13_15[8]);
                    if (var1_3 != 0) // goto
                    if (v2.length != 4) // goto
                    v2 = var10_11;
lbl47:

                    var2_1 = Float.parseFloat(v2[2]);
lbl48:

                    if (var1_3 == 0) break;
                }
                case 1: {
                    var9_9 = var6_6.get("d");
                    if (var9_9 != null) {
                        var4_4.add(VectorIcons.shape(VectorIcons.path(var9_9), var6_6, var3_2));
                    }
                    if (var1_3 == 0) break;
                }
                case 2: {
                    var13_15 = VectorIcons.a;
                    var9_10 = VectorIcons.num(var6_6.get(var13_15[47]));
                    var10_12 = VectorIcons.num(var6_6.get(var13_15[5]));
                    var11_13 = VectorIcons.num(var6_6.get("r"));
                    var12_14 = new Path2D.Float();
                    var12_14.append(new Ellipse2D.Float(var9_10 - var11_13, var10_12 - var11_13, var11_13 * 2.0f, var11_13 * 2.0f), false);
                    var4_4.add(VectorIcons.shape(var12_14, var6_6, var3_2));
                    break;
                }
            }
            if (var1_3 == 0) continue;
        }
        return new VectorIcons$Svg(var2_1, var4_4);
    }

    
    private static VectorIcons$Shape shape(Path2D var0, Map<String, String> var1_1, Map<String, String> var2_2) {
        block39: {
            block38: {
                var11_3 = VectorIcons.a;
                var4_4 = var1_1.getOrDefault(var11_3[7], var2_2.getOrDefault(var11_3[23], var11_3[20]));
                var3_5 = PreviewScene.a;
                v0 = var5_6 = var1_1.getOrDefault(var11_3[39], var2_2.getOrDefault(var11_3[42], var11_3[38]));
                if (var3_5 != 0) // goto
                if (v0.equals(var11_3[16])) {
                    v1 = 0.0f;
                } else {
                    var11_3 = VectorIcons.a;
                    v0 = var1_1.getOrDefault(var11_3[31], var2_2.getOrDefault(var11_3[24], "1"));
lbl11:

                    v1 = VectorIcons.num(v0);
                }
                var6_7 = v1;
                var11_3 = VectorIcons.a;
                var8_8 = var1_1.getOrDefault(var11_3[4], var2_2.getOrDefault(var11_3[0], var11_3[35]));
                var9_10 = -1;
                v2 = var8_8.hashCode();
                if (var3_5 != 0) break block38;
                switch (v2) {
                    case 108704142: {
                        v2 = (int)var8_8.equals(VectorIcons.a[14]);
                        if (var3_5 == 0) {
                            if (v2 == 0) break;
                            var9_10 = 0;
                            if (var3_5 == 0) break;
                        }
                        break block38;
                    }
                    case -894674659: {
                        v2 = (int)var8_8.equals(VectorIcons.a[17]);
                        if (var3_5 != 0) break block38;
                        if (v2 == 0) break;
                        var9_10 = 1;
                    }
                }
                v2 = var9_10;
            }
            if (var3_5 == 0) {
                switch (v2) {
                    case 0: {
                        v2 = 1;
                        break;
                    }
                    case 1: {
                        v2 = 2;
                        break;
                    }
                    default: {
                        v2 = 0;
                    }
                }
            }
            var7_12 = v2;
            var11_3 = VectorIcons.a;
            var9_11 = var1_1.getOrDefault(var11_3[18], var2_2.getOrDefault(var11_3[28], var11_3[40]));
            var10_13 = -1;
            v3 = var9_11.hashCode();
            if (var3_5 != 0) break block39;
            switch (v3) {
                case 108704142: {
                    v3 = (int)var9_11.equals(VectorIcons.a[9]);
                    if (var3_5 == 0) {
                        if (v3 == 0) break;
                        var10_13 = 0;
                        if (var3_5 == 0) break;
                    }
                    break block39;
                }
                case 93630586: {
                    v3 = (int)var9_11.equals(VectorIcons.a[19]);
                    if (var3_5 != 0) break block39;
                    if (v3 == 0) break;
                    var10_13 = 1;
                }
            }
            v3 = var10_13;
        }
        if (var3_5 == 0) {
            switch (v3) {
                case 0: {
                    v3 = 1;
                    break;
                }
                case 1: {
                    v3 = 2;
                    break;
                }
                default: {
                    v3 = var8_9 = 0;
                }
            }
        }
        if (!var4_4.equals(VectorIcons.a[16])) {
            v4 = true;
            return new VectorIcons$Shape(var0, v4, var6_7, var7_12, var8_9);
        }
        v4 = false;
        return new VectorIcons$Shape(var0, v4, var6_7, var7_12, var8_9);
    }

    private static Map<String, String> attrs(String string) {
        HashMap<String, String> hashMap;
        block4: {
            HashMap<String, String> hashMap2 = new HashMap<String, String>();
            int n = PreviewScene.a;
            Matcher matcher = ATTR.matcher(string);
            while (matcher.find()) {
                hashMap = hashMap2;
                if (n == 0) {
                    hashMap.put(matcher.group(1), matcher.group(2));
                    if (n == 0) continue;
                    break;
                }
                break block4;
            }
            hashMap = hashMap2;
        }
        return hashMap;
    }

    private static float num(String string) {
        float f;
        block6: {
            String string2;
            block4: {
                block5: {
                    int n = PreviewScene.a;
                    string2 = string;
                    if (n != 0) break block4;
                    if (string2 != null) break block5;
                    f = 0.0f;
                    break block6;
                }
                string2 = string.trim();
            }
            f = Float.parseFloat(string2);
        }
        return f;
    }

    
    private static Path2D path(String var0) {
        var2_1 = new ArrayList<String>();
        var3_2 = VectorIcons.TOKEN.matcher(var0);
        var1_3 = PreviewScene.a;
        while (var3_2.find()) {
            var2_1.add(var3_2.group());
            if (var1_3 == 0) continue;
        }
        var4_4 = new Path2D.Float();
        var5_5 = 0.0f;
        var6_6 = 0.0f;
        var7_7 = 0.0f;
        var8_8 = 0.0f;
        var9_9 = 0.0f;
        var10_10 = 0.0f;
        var11_11 = 77;
        var12_12 = 32;
        var13_13 = 0;
        while (var13_13 < var2_1.size()) {
            block32: {
                block33: {
                    block34: {
                        block35: {
                            var14_14 = (String)var2_1.get(var13_13);
                            v0 = var14_14.charAt(0);
                            if (var1_3 != 0) break block32;
                            if (!Character.isLetter((char)v0)) break block33;
                            v1 = var14_14.charAt(0);
                            if (var1_3 != 0) break block34;
                            var11_11 = v1;
                            ++var13_13;
                            if (var11_11 == 90) break block35;
                            v0 = var11_11;
                            if (var1_3 != 0) break block32;
                            if (v0 != 122) break block33;
                        }
                        var4_4.closePath();
                        var5_5 = var7_7;
                        var6_6 = var8_8;
                        v1 = var11_11;
                    }
                    var12_12 = v1;
                    if (var1_3 == 0) continue;
                }
                v0 = (int)Character.isLowerCase((char)var11_11);
            }
            var16_16 = (var15_15 = v0) != 0 ? var5_5 : 0.0f;
            v2 = var17_17 = var15_15 != 0 ? var6_6 : 0.0f;
            if (var1_3 != 0) // goto
            switch (Character.toUpperCase((char)var11_11)) {
                case 'M': {
                    var5_5 = var16_16 + VectorIcons.f(var2_1, var13_13++);
                    var6_6 = var17_17 + VectorIcons.f(var2_1, var13_13++);
                    var4_4.moveTo((double)var5_5, (double)var6_6);
                    var7_7 = var5_5;
                    var8_8 = var6_6;
lbl52:

                    v3 = var15_15;
                    if (var1_3 == 0) {
                        v3 = var11_11 = v3 != 0 ? 108 : 76;
                    }
                    if (var1_3 == 0) break;
                }
                case 'L': {
                    var5_5 = var16_16 + VectorIcons.f(var2_1, var13_13++);
                    var6_6 = var17_17 + VectorIcons.f(var2_1, var13_13++);
                    var4_4.lineTo((double)var5_5, (double)var6_6);
                    if (var1_3 == 0) break;
                }
                case 'H': {
                    var5_5 = var16_16 + VectorIcons.f(var2_1, var13_13++);
                    var4_4.lineTo((double)var5_5, (double)var6_6);
                    if (var1_3 == 0) break;
                }
                case 'V': {
                    var6_6 = var17_17 + VectorIcons.f(var2_1, var13_13++);
                    var4_4.lineTo((double)var5_5, (double)var6_6);
                    if (var1_3 == 0) break;
                }
                case 'C': {
                    var18_18 = var16_16 + VectorIcons.f(var2_1, var13_13++);
                    var19_21 = var17_17 + VectorIcons.f(var2_1, var13_13++);
                    var20_22 = var16_16 + VectorIcons.f(var2_1, var13_13++);
                    var21_23 = var17_17 + VectorIcons.f(var2_1, var13_13++);
                    var5_5 = var16_16 + VectorIcons.f(var2_1, var13_13++);
                    var6_6 = var17_17 + VectorIcons.f(var2_1, var13_13++);
                    var4_4.curveTo((double)var18_18, (double)var19_21, (double)var20_22, (double)var21_23, (double)var5_5, (double)var6_6);
                    var9_9 = var20_22;
                    var10_10 = var21_23;
                    if (var1_3 == 0) break;
                }
                case 'S': {
                    v4 = VectorIcons.a[44].indexOf(var12_12);
                    if (var1_3 == 0) {
                        v4 = v4 >= 0 ? 1 : 0;
                    }
                    var18_19 = v4;
                    var19_21 = var18_19 != 0 ? 2.0f * var5_5 - var9_9 : var5_5;
                    var20_22 = var18_19 != 0 ? 2.0f * var6_6 - var10_10 : var6_6;
                    var21_23 = var16_16 + VectorIcons.f(var2_1, var13_13++);
                    var22_25 = var17_17 + VectorIcons.f(var2_1, var13_13++);
                    var5_5 = var16_16 + VectorIcons.f(var2_1, var13_13++);
                    var6_6 = var17_17 + VectorIcons.f(var2_1, var13_13++);
                    var4_4.curveTo((double)var19_21, (double)var20_22, (double)var21_23, (double)var22_25, (double)var5_5, (double)var6_6);
                    var9_9 = var21_23;
                    var10_10 = var22_25;
                    if (var1_3 == 0) break;
                }
                case 'A': {
                    var18_20 = VectorIcons.f(var2_1, var13_13++);
                    var19_21 = VectorIcons.f(var2_1, var13_13++);
                    var20_22 = VectorIcons.f(var2_1, var13_13++);
                    v5 = (cfr_temp_0 = VectorIcons.f(var2_1, var13_13++) - 0.0f) == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
                    if (var1_3 == 0) {
                        v5 = v5 != false ? (float)true : (float)false;
                    }
                    var21_24 = v5;
                    v6 = (cfr_temp_1 = VectorIcons.f(var2_1, var13_13++) - 0.0f) == 0.0f ? 0 : (cfr_temp_1 > 0.0f ? 1 : -1);
                    if (var1_3 == 0) {
                        v6 = v6 != false ? (float)true : (float)false;
                    }
                    var22_26 = v6;
                    var23_27 = var16_16 + VectorIcons.f(var2_1, var13_13++);
                    var24_28 = var17_17 + VectorIcons.f(var2_1, var13_13++);
                    VectorIcons.arc(var4_4, var5_5, var6_6, var18_20, var19_21, var20_22, (boolean)var21_24, (boolean)var22_26, var23_27, var24_28);
                    var5_5 = var23_27;
                    var6_6 = var24_28;
                    if (var1_3 == 0) break;
                }
                default: {
                    ++var13_13;
                }
            }
            var12_12 = var11_11;
            if (var1_3 == 0) continue;
        }
        return var4_4;
    }

    private static float f(List<String> list, int n) {
        float f = n < list.size() ? Float.parseFloat(list.get(n)) : 0.0f;
        return f;
    }

    
    private static void arc(Path2D path2D, float f, float f2, float f3, float f4, float f5, boolean n, boolean n2, float f6, float f7) {
        int n3;
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        double d6;
        int n4;
        block36: {
            block35: {
                block33: {
                    block34: {
                        double d7;
                        double d8;
                        float f8;
                        block31: {
                            block32: {
                                block28: {
                                    block29: {
                                        float f9;
                                        block30: {
                                            n4 = PreviewScene.a;
                                            f3 = Math.abs(f3);
                                            if (n4 != 0) break block28;
                                            f4 = Math.abs(f4);
                                            if (f3 == 0.0f) break block29;
                                            f8 = f4;
                                            f9 = 0.0f;
                                            if (n4 != 0) break block30;
                                            if (f8 == f9) break block29;
                                            f8 = f;
                                            if (n4 != 0) break block31;
                                            f9 = f6;
                                        }
                                        if (f8 != f9) break block32;
                                        f8 = f2;
                                        if (n4 != 0) break block31;
                                        if (f8 != f7) break block32;
                                    }
                                    path2D.lineTo(f6, f7);
                                }
                                return;
                            }
                            f8 = f5;
                        }
                        double d9 = Math.toRadians(f8);
                        d6 = Math.cos(d9);
                        d5 = Math.sin(d9);
                        double d10 = (double)(f - f6) / 2.0;
                        double d11 = (double)(f2 - f7) / 2.0;
                        double d12 = d6 * d10 + d5 * d11;
                        double d13 = -d5 * d10 + d6 * d11;
                        double d14 = d8 = d12 * d12 / (double)(f3 * f3) + d13 * d13 / (double)(f4 * f4);
                        if (n4 == 0) {
                            if (d14 > 1.0) {
                                d7 = Math.sqrt(d8);
                                f3 = (float)((double)f3 * d7);
                                f4 = (float)((double)f4 * d7);
                            }
                            d14 = (double)(f3 * f3 * f4 * f4) - (double)(f3 * f3) * d13 * d13 - (double)(f4 * f4) * d12 * d12;
                        }
                        d7 = d14;
                        double d15 = (double)(f3 * f3) * d13 * d13 + (double)(f4 * f4) * d12 * d12;
                        int n5 = n;
                        if (n4 == 0) {
                            n5 = n5 == n2 ? -1 : 1;
                        }
                        double d16 = (double)n5 * Math.sqrt(Math.max(0.0, d7 / d15));
                        double d17 = d16 * (double)f3 * d13 / (double)f4;
                        double d18 = -d16 * (double)f4 * d12 / (double)f3;
                        d4 = d6 * d17 - d5 * d18 + (double)(f + f6) / 2.0;
                        d3 = d5 * d17 + d6 * d18 + (double)(f2 + f7) / 2.0;
                        double d19 = (d12 - d17) / (double)f3;
                        double d20 = (d13 - d18) / (double)f4;
                        double d21 = (-d12 - d17) / (double)f3;
                        double d22 = (-d13 - d18) / (double)f4;
                        d2 = Math.atan2(d20, d19);
                        d = Math.atan2(d19 * d22 - d20 * d21, d19 * d21 + d20 * d22);
                        n3 = n2;
                        if (n4 != 0) break block33;
                        if (n3 != 0) break block34;
                        double d23 = d - 0.0;
                        n3 = d23 == 0.0 ? 0 : (d23 > 0.0 ? 1 : -1);
                        if (n4 != 0) break block33;
                        if (n3 <= 0) break block34;
                        d -= Math.PI * 2;
                        if (n4 == 0) break block35;
                    }
                    n3 = n2;
                }
                if (n4 != 0) break block36;
                if (n3 == 0) break block35;
                double d24 = d - 0.0;
                n3 = d24 == 0.0 ? 0 : (d24 < 0.0 ? -1 : 1);
                if (n4 != 0) break block36;
                if (n3 < 0) {
                    d += Math.PI * 2;
                }
            }
            n3 = Math.max(4, (int)Math.ceil(Math.abs(d) / Math.PI * 16.0));
        }
        int n6 = n3;
        for (int i = 1; i <= n6; ++i) {
            double d25 = d2 + d * (double)i / (double)n6;
            double d26 = d4 + (double)f3 * Math.cos(d25) * d6 - (double)f4 * Math.sin(d25) * d5;
            double d27 = d3 + (double)f3 * Math.cos(d25) * d5 + (double)f4 * Math.sin(d25) * d6;
            path2D.lineTo(d26, d27);
            if (n4 == 0) continue;
        }
    }

    private static String lambda$bake$2(String string) {
        return a[12] + string;
    }

    private static void lambda$warm$1() {
        Path2D.Float float_ = new Path2D.Float();
        ((Path2D)float_).moveTo(2.0, 2.0);
        ((Path2D)float_).curveTo(20.0, 4.0, 10.0, 30.0, 30.0, 30.0);
        int n = PreviewScene.a;
        ((Path2D)float_).lineTo(4.0, 28.0);
        float_.closePath();
        BasicStroke basicStroke = new BasicStroke(2.5f, 1, 1);
        int n2 = 0;
        block0: while (true) {
            int n3 = n2;
            block1: while (n3 < 400) {
                BufferedImage bufferedImage;
                int n4 = 16 + n2 % 48;
                BufferedImage bufferedImage2 = bufferedImage = new BufferedImage(n4, n4, 2);
                block2: while (true) {
                    Graphics2D graphics2D = bufferedImage2.createGraphics();
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    graphics2D.scale((float)n4 / 32.0f, (float)n4 / 32.0f);
                    graphics2D.fill(float_);
                    graphics2D.setStroke(basicStroke);
                    graphics2D.draw(float_);
                    graphics2D.draw(new Ellipse2D.Float(8.0f, 8.0f, 14.0f, 14.0f));
                    graphics2D.dispose();
                    for (int i = 0; i < n4; ++i) {
                        n3 = 0;
                        if (n != 0) continue block1;
                        for (int j = v1506071; j < n4; ++j) {
                            bufferedImage2 = bufferedImage;
                            if (n != 0) continue block2;
                            bufferedImage2.getRGB(j, i);
                            if (n == 0) continue;
                        }
                        if (n == 0) continue;
                    }
                    break;
                }
                ++n2;
                if (n == 0) continue block0;
            }
            break;
        }
    }

    private static String lambda$loadPng$0(String string) {
        return a[3] + string;
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[49];
                        var11_1 = 0;
                        var10_2 = "yd\u0018/w gfy\u0004%\u007f$:\u0007|u\t4s7e\u0004$`\u0004'\u000eke\u0018/n$e|u\t4s7e\u000eyd\u0018/w gfy\u0004%\u007f$:\u0002ii\u0004zq\u001e(\u0004ly\u0006,\u0006QL\u0019lAn\u0005x\u007f\u001f.x\u0004$`\u0004'\"Ke\u0018/n$p*s\u00055p!jd\u007f\u001e`n +n0\u001a.{e#i\u007f\u0004`g8\u000eke\u0018/n$e|u\t4s7e\u000ecs\u0005.oj'et\u001f,y6e\u0005x\u007f\u001f.x\u0017Ke\u0018/n$p*}\u00033o,$m0\u0003#s+jqm\u0004d\u007f\u0004%\u0006ya\u001f!n \u000fyd\u0018/w gfy\u0004%v*#d\u0005hu\u001c%p\u0004) Zp\u0006ke\u0018/n$\u0012Ke\u0018/n$jcs\u0005.<2+x}\u001f0\u0004ly\u0006,\fyd\u0018/w g}y\u000e4t\u000ecs\u0005.oj'et\u001f,y6e\u0006cs\u0005.oj\u0006ke\u0018/n$\u000fyd\u0018/w gfy\u0004%v*#d\u0003yf\r\u001eKe\u0018/n$p*s\u00055p!jd\u007f\u001e`n +n0\u0003#s+jqm\fyd\u0018/w g}y\u000e4t\u0006iy\u0018#p \u0004$c\u001c'\u001eKe\u0018/n$p*s\u00055p!jd\u007f\u001e`x7+}0\u0003#s+jqm\u0004he\u001e4\u0007|y\u000f7^*2<Q]\u0007\fp\r\"\\f)#O6\u000bkJ\u0010\u001d`\u001eg!MUh#\u007f\u0016n;6n#\u0019. l6n@!a#8UzG \u000fWKGkAz\u0016n;C\u007f\u0004d\u007f\u0004%\u0006yd\u0018/w \u0005gy\u001e%n\u0007|u\t4s7e\u0006yd\u0018/w \u001c68\u00196{9:kd\u0002<\u007f,8i|\u000fi@'bQNT\u001d6lt\u0004Is93\u0006cs\u0005.oj\u0004$c\u001c'";
                        var12_3 = "yd\u0018/w gfy\u0004%\u007f$:\u0007|u\t4s7e\u0004$`\u0004'\u000eke\u0018/n$e|u\t4s7e\u000eyd\u0018/w gfy\u0004%\u007f$:\u0002ii\u0004zq\u001e(\u0004ly\u0006,\u0006QL\u0019lAn\u0005x\u007f\u001f.x\u0004$`\u0004'\"Ke\u0018/n$p*s\u00055p!jd\u007f\u001e`n +n0\u001a.{e#i\u007f\u0004`g8\u000eke\u0018/n$e|u\t4s7e\u000ecs\u0005.oj'et\u001f,y6e\u0005x\u007f\u001f.x\u0017Ke\u0018/n$p*}\u00033o,$m0\u0003#s+jqm\u0004d\u007f\u0004%\u0006ya\u001f!n \u000fyd\u0018/w gfy\u0004%v*#d\u0005hu\u001c%p\u0004) Zp\u0006ke\u0018/n$\u0012Ke\u0018/n$jcs\u0005.<2+x}\u001f0\u0004ly\u0006,\fyd\u0018/w g}y\u000e4t\u000ecs\u0005.oj'et\u001f,y6e\u0006cs\u0005.oj\u0006ke\u0018/n$\u000fyd\u0018/w gfy\u0004%v*#d\u0003yf\r\u001eKe\u0018/n$p*s\u00055p!jd\u007f\u001e`n +n0\u0003#s+jqm\fyd\u0018/w g}y\u000e4t\u0006iy\u0018#p \u0004$c\u001c'\u001eKe\u0018/n$p*s\u00055p!jd\u007f\u001e`x7+}0\u0003#s+jqm\u0004he\u001e4\u0007|y\u000f7^*2<Q]\u0007\fp\r\"\\f)#O6\u000bkJ\u0010\u001d`\u001eg!MUh#\u007f\u0016n;6n#\u0019. l6n@!a#8UzG \u000fWKGkAz\u0016n;C\u007f\u0004d\u007f\u0004%\u0006yd\u0018/w \u0005gy\u001e%n\u0007|u\t4s7e\u0006yd\u0018/w \u001c68\u00196{9:kd\u0002<\u007f,8i|\u000fi@'bQNT\u001d6lt\u0004Is93\u0006cs\u0005.oj\u0004$c\u001c'".length();
                        var9_4 = 14;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "ih\u001c\"K\u000bmf\u0004gP=7k5\u00199 -636gbQNH\u001d6lh";
                            var12_3 = "ih\u001c\"K\u000bmf\u0004gP=7k5\u00199 -636gbQNH\u001d6lh".length();
                            var9_4 = 2;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block20;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            break block21;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_7 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_7;
                        v9 = v6[v8];
                        switch (var14_7 % 7) {
                            case 0: {
                                v10 = 10;
                                break;
                            }
                            case 1: {
                                v10 = 16;
                                break;
                            }
                            case 2: {
                                v10 = 106;
                                break;
                            }
                            case 3: {
                                v10 = 64;
                                break;
                            }
                            case 4: {
                                v10 = 28;
                                break;
                            }
                            case 5: {
                                v10 = 69;
                                break;
                            }
                            default: {
                                v10 = 74;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_7;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_7);
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
                VectorIcons.a = var13;
                var0_8 = 3753403932338018871L;
                var6_9 = new long[2];
                var3_10 = 0;
                var4_11 = "\u0097\u0084lX[\r\u00bcUZ!\u0089\u00ef\u008c\u00cb\u00d2\u00b3";
                var5_12 = "\u0097\u0084lX[\r\u00bcUZ!\u0089\u00ef\u008c\u00cb\u00d2\u00b3".length();
                var2_13 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl84:

                while (true) {
                    var6_9[v11] = (((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L) ^ var0_8;
                    if (var2_13 < var5_12) continue;
                    break block23;
                    break;
                }
            }
            var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
            v11 = var3_10++;
            while (true)
        }
        VectorIcons.b = var6_9;
        VectorIcons.c = new Integer[2];
        VectorIcons.BAKED = new HashMap<String, Identifier>();
        VectorIcons.PARSED = new HashMap<String, VectorIcons$Svg>();
        VectorIcons.BROKEN = new VectorIcons$Svg(0.0f, List.of());
        var8_6 = VectorIcons.a;
        VectorIcons.ELEMENT = Pattern.compile(var8_6[43]);
        VectorIcons.ATTR = Pattern.compile(var8_6[48]);
        VectorIcons.TOKEN = Pattern.compile(var8_6[37]);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1BAB;
        if (c[n2] == null) {
            VectorIcons.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
