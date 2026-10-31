package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.ShotThumbs$1;
import dev.quelchi.aurora.gui.ShotThumbs$Picture;
import dev.quelchi.aurora.gui.ShotThumbs$Smooth;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.invoke.LambdaMetafactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;

public final class ShotThumbs {
    private static final int THUMB_EDGE;
    private static final int MAX_THUMBS;
    private static final ExecutorService LOADER;
    private static final Map<String, ShotThumbs$Picture> THUMBS;
    private static final Set<String> QUEUED;
    private static final Set<String> FAILED;
    private static int locals;
    private static final String[] a;

    private ShotThumbs() {
    }

    private static ShotThumbs$Picture upload(String string, NativeImage nativeImage) {
        try {
            String[] stringArray = a;
            Identifier identifier = Identifier.method_60655((String)stringArray[5], (String)(stringArray[4] + string));
            ShotThumbs$Smooth shotThumbs$Smooth = new ShotThumbs$Smooth(() -> ShotThumbs.lambda$upload$1(string), nativeImage);
            shotThumbs$Smooth.method_4524();
            MinecraftClient.method_1551().method_1531().method_4616(identifier, (AbstractTexture)shotThumbs$Smooth);
            return new ShotThumbs$Picture(identifier, nativeImage.method_4307(), nativeImage.method_4323());
        }
        catch (RuntimeException runtimeException) {
            nativeImage.close();
            throw runtimeException;
        }
    }

    public static void cover(DrawContext drawContext, ShotThumbs$Picture shotThumbs$Picture, int n, int n2, int n3, int n4) {
        int n5;
        block17: {
            block18: {
                block13: {
                    ShotThumbs$Picture shotThumbs$Picture2;
                    int n6;
                    block16: {
                        int n7;
                        block15: {
                            block14: {
                                n6 = PreviewScene.a;
                                if (shotThumbs$Picture == null) break block13;
                                n7 = n3;
                                if (n6 != 0) break block14;
                                if (n7 <= 0) break block13;
                                n7 = n4;
                            }
                            if (n6 != 0) break block15;
                            if (n7 <= 0) break block13;
                            shotThumbs$Picture2 = shotThumbs$Picture;
                            if (n6 != 0) break block16;
                            n7 = shotThumbs$Picture2.width();
                        }
                        if (n7 <= 0) break block13;
                        shotThumbs$Picture2 = shotThumbs$Picture;
                    }
                    n5 = shotThumbs$Picture2.height();
                    if (n6 != 0) break block17;
                    if (n5 > 0) break block18;
                }
                return;
            }
            n5 = n3;
        }
        float f = Math.max((float)n5 / (float)shotThumbs$Picture.width(), (float)n4 / (float)shotThumbs$Picture.height());
        int n8 = Math.max(1, Math.min(shotThumbs$Picture.width(), Math.round((float)n3 / f)));
        int n9 = Math.max(1, Math.min(shotThumbs$Picture.height(), Math.round((float)n4 / f)));
        float f2 = (float)(shotThumbs$Picture.width() - n8) / 2.0f;
        float f3 = (float)(shotThumbs$Picture.height() - n9) / 2.0f;
        drawContext.method_25293(RenderPipelines.field_56883, shotThumbs$Picture.id(), n, n2, f2, f3, n3, n4, n8, n9, shotThumbs$Picture.width(), shotThumbs$Picture.height(), -1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ShotThumbs$Picture thumbnail(Path path) {
        Set<String> set;
        String string;
        block10: {
            int n;
            block9: {
                string = path.toAbsolutePath().toString();
                n = PreviewScene.a;
                ShotThumbs$Picture shotThumbs$Picture = THUMBS.get(string);
                ShotThumbs$Picture shotThumbs$Picture2 = shotThumbs$Picture;
                if (n != 0) return shotThumbs$Picture2;
                if (shotThumbs$Picture2 == null) break block9;
                shotThumbs$Picture2 = shotThumbs$Picture;
                return shotThumbs$Picture2;
            }
            set = FAILED;
            if (n != 0) break block10;
            if (set.contains(string)) return null;
            set = QUEUED;
        }
        if (!set.add(string)) {
            return null;
        }
        LOADER.execute(() -> ShotThumbs.lambda$thumbnail$3(path, string));
        return null;
    }

    private static NativeImage box(NativeImage nativeImage, int n, int n2) {
        NativeImage nativeImage2 = new NativeImage(NativeImage.Format.field_4997, n, n2, false);
        int n3 = nativeImage.method_4307();
        int n4 = PreviewScene.a;
        int n5 = nativeImage.method_4323();
        int n6 = 0;
        while (n6 < n2) {
            block15: {
                int n7 = n6 * n5 / n2;
                int n8 = Math.max(n7 + 1, (n6 + 1) * n5 / n2);
                for (int i = 0; i < n; ++i) {
                    int n9;
                    int n10;
                    int n11;
                    int n12;
                    int n13;
                    block19: {
                        int n14;
                        block18: {
                            block16: {
                                int n15 = i * n3 / n;
                                int n16 = Math.max(n15 + 1, (i + 1) * n3 / n);
                                n13 = 0;
                                n12 = 0;
                                n11 = 0;
                                n10 = 0;
                                if (n4 != 0) break block15;
                                n9 = 0;
                                int n17 = n7;
                                block12: while (true) {
                                    int n18 = n17;
                                    block13: while (n18 < n8) {
                                        n14 = n17;
                                        if (n4 != 0 || n4 != 0) break block16;
                                        if (n14 >= n5) break block12;
                                        for (int j = n15; j < n16; ++j) {
                                            int n19;
                                            block17: {
                                                n18 = j;
                                                if (n4 != 0) continue block13;
                                                if (n4 != 0) break block17;
                                                if (n18 >= n3) break;
                                                n19 = nativeImage.method_61940(j, n17);
                                            }
                                            void var20_20 = n19;
                                            n13 += var20_20 >>> 24 & 0xFF;
                                            n12 += var20_20 >>> 16 & 0xFF;
                                            n11 += var20_20 >>> 8 & 0xFF;
                                            n10 += var20_20 & 0xFF;
                                            ++n9;
                                            if (n4 == 0) continue;
                                        }
                                        ++n17;
                                        if (n4 == 0) continue block12;
                                    }
                                    break;
                                }
                                n14 = n9;
                            }
                            if (n4 != 0) break block18;
                            if (n14 != 0) break block19;
                            n14 = 1;
                        }
                        n9 = n14;
                    }
                    nativeImage2.method_61941(i, n6, n13 / n9 << 24 | n12 / n9 << 16 | n11 / n9 << 8 | n10 / n9);
                    if (n4 == 0) continue;
                }
                ++n6;
            }
            if (n4 == 0) continue;
        }
        return nativeImage2;
    }

    
    private static NativeImage scaled(byte[] var0, int var1_1) throws Exception {
        block13: {
            block14: {
                var4_2 = new ByteArrayInputStream(var0);
                var2_4 = PreviewScene.a;
                try {
                    var3_5 = NativeImage.method_4310((NativeImage.Format)NativeImage.Format.field_4997, (InputStream)var4_2);
                }
                finally {
                    var4_2.close();
                }
                var4_3 = Math.max(var3_5.method_4307(), var3_5.method_4323());
                v0 = var4_3;
                if (var2_4 != 0) // goto
                if (v0 > var1_1) break block13;
                break block14;
                catch (Throwable v1) {
                    throw v1;
                }
            }
            return var3_5;
        }
        try {
            v0 = Math.max(1, var3_5.method_4307() * var1_1 / var4_3);
lbl26:

            var5_7 = v0;
            var6_9 = Math.max(1, var3_5.method_4323() * var1_1 / var4_3);
            var7_10 = ShotThumbs.box(var3_5, var5_7, var6_9);
            return var7_10;
        }
        finally {
            var3_5.close();
        }
    }

    private static void lambda$thumbnail$3(Path path, String string) {
        NativeImage nativeImage = null;
        try {
            nativeImage = ShotThumbs.scaled(Files.readAllBytes(path), 256);
        }
        catch (Exception exception) {
            Aurora.LOG.debug(a[2], (Object)path, (Object)exception);
        }
        NativeImage nativeImage2 = nativeImage;
        MinecraftClient.method_1551().execute(() -> ShotThumbs.lambda$thumbnail$2(string, nativeImage2, path));
    }

    
    private static void lambda$thumbnail$2(String string, NativeImage nativeImage, Path path) {
        int n = PreviewScene.a;
        QUEUED.remove(string);
        if (n != 0) return;
        if (nativeImage == null) {
            FAILED.add(string);
            return;
        }
        try {
            String[] stringArray = a;
            THUMBS.put(string, ShotThumbs.upload(stringArray[3] + locals++, nativeImage));
            return;
        }
        catch (RuntimeException runtimeException) {
            FAILED.add(string);
            Aurora.LOG.debug(a[0], (Object)path, (Object)runtimeException);
        }
    }

    private static String lambda$upload$1(String string) {
        return a[6] + string;
    }

    private static Thread lambda$static$0(Runnable runnable) {
        Thread thread = new Thread(runnable, a[1]);
        thread.setDaemon(true);
        return thread;
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[7];
                        var11_1 = 0;
                        var10_2 = "\u0013\u0001{/O;F?\u001a.6[wG1\n.7CnE2\u0000o*G;N?\u001c.8V\u00121\u001b|,Yz\u0005#\u0006a7\u0006o@%\u0003l0\u0011\u0013\u0001{/O;F?\u001a.1NzLp\u0015s\u0005<\u0001m\"G\u0005#\u0006a7\u0004";
                        var12_3 = "\u0013\u0001{/O;F?\u001a.6[wG1\n.7CnE2\u0000o*G;N?\u001c.8V\u00121\u001b|,Yz\u0005#\u0006a7\u0006o@%\u0003l0\u0011\u0013\u0001{/O;F?\u001a.1NzLp\u0015s\u0005<\u0001m\"G\u0005#\u0006a7\u0004".length();
                        var9_4 = 33;
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
                            var10_2 = "1\u001b|,Yz\u00071\u001b|,Yz\u0007";
                            var12_3 = "1\u001b|,Yz\u00071\u001b|,Yz\u0007".length();
                            var9_4 = 6;
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
                    var14_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_6;
                        v9 = v6[v8];
                        switch (var14_6 % 7) {
                            case 0: {
                                v10 = 80;
                                break;
                            }
                            case 1: {
                                v10 = 110;
                                break;
                            }
                            case 2: {
                                v10 = 14;
                                break;
                            }
                            case 3: {
                                v10 = 67;
                                break;
                            }
                            case 4: {
                                v10 = 43;
                                break;
                            }
                            case 5: {
                                v10 = 27;
                                break;
                            }
                            default: {
                                v10 = 40;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_6;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_6);
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
                ShotThumbs.a = var13;
                var1_7 = 6634482662232997283L;
                var0_8 = new long[2];
                var4_9 = 0;
                var5_10 = "\u00ca\u001a\u0087\u0012\u008e\u00e2\u00bc\u00a3\u00d5\u00f5`C\u008e\u00e2\u00bd\u00e3";
                var6_11 = "\u00ca\u001a\u0087\u0012\u008e\u00e2\u00bc\u00a3\u00d5\u00f5`C\u008e\u00e2\u00bd\u00e3".length();
                var3_12 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl84:

                while (true) {
                    var0_8[v11] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var1_7;
                    if (var3_12 < var6_11) continue;
                    break block23;
                    break;
                }
            }
            var7_13 = var5_10.substring(var3_12, var3_12 += 8).getBytes("ISO-8859-1");
            v11 = var4_9++;
            while (true)
        }
        ShotThumbs.THUMB_EDGE = (int)var0_8[0];
        ShotThumbs.MAX_THUMBS = (int)var0_8[1];
        ShotThumbs.LOADER = Executors.newSingleThreadExecutor((ThreadFactory)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Runnable;)Ljava/lang/Thread;, lambda$static$0(java.lang.Runnable ), (Ljava/lang/Runnable;)Ljava/lang/Thread;)());
        ShotThumbs.THUMBS = new ShotThumbs$1(16, 0.75f, true);
        ShotThumbs.QUEUED = new HashSet<String>();
        ShotThumbs.FAILED = new HashSet<String>();
    }
}
